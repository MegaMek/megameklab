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

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;

import javax.swing.ImageIcon;

/**
 * This class has been automatically generated using <a
 * href="http://englishjavadrinker.blogspot.com/search/label/SVGRoundTrip"
 * >SVGRoundTrip</a>.
 */
public class NavalData {
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
        Composite origComposite = g.getComposite();
        if (origComposite instanceof AlphaComposite) {
            AlphaComposite origAlphaComposite = (AlphaComposite) origComposite;
            if (origAlphaComposite.getRule() == AlphaComposite.SRC_OVER) {
                origAlpha = origAlphaComposite.getAlpha();
            }
        }

        Shape clip_ = g.getClip();
        AffineTransform defaultTransform_ = g.getTransform();
        // is CompositeGraphicsNode
        float alpha__0 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0 = g.getClip();
        AffineTransform defaultTransform__0 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, -0.0f, -0.0f));
        clip = new Area(g.getClip());
        clip.intersect(new Area(new Rectangle2D.Double(0.0, 0.0, 612.0, 792.0)));
        g.setClip(clip);
        // _0 is CompositeGraphicsNode
        float alpha__0_0 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_0 = g.getClip();
        AffineTransform defaultTransform__0_0 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_0 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(114.142, 78.93);
        ((GeneralPath) shape).lineTo(114.142, 78.93);
        ((GeneralPath) shape).lineTo(116.706, 85.841);
        ((GeneralPath) shape).lineTo(120.691, 85.841);
        ((GeneralPath) shape).lineTo(120.691, 76.841);
        ((GeneralPath) shape).lineTo(118.446, 76.841);
        ((GeneralPath) shape).lineTo(118.446, 83.752);
        ((GeneralPath) shape).lineTo(115.844, 76.841);
        ((GeneralPath) shape).lineTo(111.896, 76.841);
        ((GeneralPath) shape).lineTo(111.896, 85.841);
        ((GeneralPath) shape).lineTo(114.142, 85.841);
        ((GeneralPath) shape).lineTo(114.142, 78.93);
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
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(127.029, 82.654);
        ((GeneralPath) shape).lineTo(124.821, 82.654);
        ((GeneralPath) shape).lineTo(125.9, 78.64);
        ((GeneralPath) shape).lineTo(125.924, 78.64);
        ((GeneralPath) shape).lineTo(127.029, 82.654);
        ((GeneralPath) shape).moveTo(127.502, 84.341);
        ((GeneralPath) shape).lineTo(127.964, 85.841);
        ((GeneralPath) shape).lineTo(130.385, 85.841);
        ((GeneralPath) shape).lineTo(127.68199, 76.841);
        ((GeneralPath) shape).lineTo(124.07399, 76.841);
        ((GeneralPath) shape).lineTo(121.40499, 85.841);
        ((GeneralPath) shape).lineTo(123.87299, 85.841);
        ((GeneralPath) shape).lineTo(124.311, 84.341);
        ((GeneralPath) shape).lineTo(127.502, 84.341);
        g.setPaint(paint);
        g.fill(shape);
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
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(134.392, 83.776);
        ((GeneralPath) shape).lineTo(134.319, 83.776);
        ((GeneralPath) shape).lineTo(132.36, 76.841);
        ((GeneralPath) shape).lineTo(129.907, 76.841);
        ((GeneralPath) shape).lineTo(132.471, 85.841);
        ((GeneralPath) shape).lineTo(136.243, 85.841);
        ((GeneralPath) shape).lineTo(138.889, 76.841);
        ((GeneralPath) shape).lineTo(136.386, 76.841);
        ((GeneralPath) shape).lineTo(134.392, 83.776);
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
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(143.771, 82.654);
        ((GeneralPath) shape).lineTo(141.563, 82.654);
        ((GeneralPath) shape).lineTo(142.642, 78.64);
        ((GeneralPath) shape).lineTo(142.66699, 78.64);
        ((GeneralPath) shape).lineTo(143.771, 82.654);
        ((GeneralPath) shape).moveTo(144.245, 84.341);
        ((GeneralPath) shape).lineTo(144.707, 85.841);
        ((GeneralPath) shape).lineTo(147.128, 85.841);
        ((GeneralPath) shape).lineTo(144.423, 76.841);
        ((GeneralPath) shape).lineTo(140.817, 76.841);
        ((GeneralPath) shape).lineTo(138.148, 85.841);
        ((GeneralPath) shape).lineTo(140.616, 85.841);
        ((GeneralPath) shape).lineTo(141.054, 84.341);
        ((GeneralPath) shape).lineTo(144.245, 84.341);
        g.setPaint(paint);
        g.fill(shape);
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
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(150.305, 76.841);
        ((GeneralPath) shape).lineTo(147.872, 76.841);
        ((GeneralPath) shape).lineTo(147.872, 85.841);
        ((GeneralPath) shape).lineTo(154.053, 85.841);
        ((GeneralPath) shape).lineTo(154.053, 83.752);
        ((GeneralPath) shape).lineTo(150.305, 83.752);
        ((GeneralPath) shape).lineTo(150.305, 76.841);
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
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(162.763, 83.776);
        ((GeneralPath) shape).lineTo(162.69, 83.776);
        ((GeneralPath) shape).lineTo(160.731, 76.841);
        ((GeneralPath) shape).lineTo(158.278, 76.841);
        ((GeneralPath) shape).lineTo(160.842, 85.841);
        ((GeneralPath) shape).lineTo(164.614, 85.841);
        ((GeneralPath) shape).lineTo(167.26, 76.841);
        ((GeneralPath) shape).lineTo(164.757, 76.841);
        ((GeneralPath) shape).lineTo(162.763, 83.776);
        g.setPaint(paint);
        g.fill(shape);
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
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(170.173, 78.81);
        ((GeneralPath) shape).lineTo(174.257, 78.81);
        ((GeneralPath) shape).lineTo(174.257, 76.841);
        ((GeneralPath) shape).lineTo(167.74, 76.841);
        ((GeneralPath) shape).lineTo(167.74, 85.841);
        ((GeneralPath) shape).lineTo(174.339, 85.841);
        ((GeneralPath) shape).lineTo(174.339, 83.873);
        ((GeneralPath) shape).lineTo(170.173, 83.873);
        ((GeneralPath) shape).lineTo(170.173, 82.091);
        ((GeneralPath) shape).lineTo(174.006, 82.091);
        ((GeneralPath) shape).lineTo(174.006, 80.404);
        ((GeneralPath) shape).lineTo(170.173, 80.404);
        ((GeneralPath) shape).lineTo(170.173, 78.81);
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
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(183.918, 76.841);
        ((GeneralPath) shape).lineTo(181.485, 76.841);
        ((GeneralPath) shape).lineTo(181.485, 80.216);
        ((GeneralPath) shape).lineTo(178.305, 80.216);
        ((GeneralPath) shape).lineTo(178.305, 76.841);
        ((GeneralPath) shape).lineTo(175.872, 76.841);
        ((GeneralPath) shape).lineTo(175.872, 85.841);
        ((GeneralPath) shape).lineTo(178.305, 85.841);
        ((GeneralPath) shape).lineTo(178.305, 82.279);
        ((GeneralPath) shape).lineTo(181.485, 82.279);
        ((GeneralPath) shape).lineTo(181.485, 85.841);
        ((GeneralPath) shape).lineTo(183.918, 85.841);
        ((GeneralPath) shape).lineTo(183.918, 76.841);
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
        paint = new Color(34, 31, 31, 255);
        shape = new Rectangle2D.Double(185.3209991455078, 76.84100341796875,
                2.433000087738037, 9.0);
        g.setPaint(paint);
        g.fill(shape);
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
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(194.266, 82.419);
        ((GeneralPath) shape).lineTo(194.266, 82.677);
        ((GeneralPath) shape).curveTo(194.266, 83.708, 193.78401, 83.779,
                192.787, 83.779);
        ((GeneralPath) shape).curveTo(191.33301, 83.779, 191.272, 83.275,
                191.272, 81.965);
        ((GeneralPath) shape).lineTo(191.272, 80.597);
        ((GeneralPath) shape).curveTo(191.272, 79.343, 191.406, 78.933,
                192.787, 78.933);
        ((GeneralPath) shape).curveTo(193.61101, 78.933, 194.183, 78.992,
                194.266, 79.777);
        ((GeneralPath) shape).lineTo(194.266, 80.117);
        ((GeneralPath) shape).lineTo(196.699, 80.117);
        ((GeneralPath) shape).lineTo(196.699, 79.77);
        ((GeneralPath) shape).curveTo(196.699, 77.091995, 195.541, 76.842995,
                193.05301, 76.842995);
        ((GeneralPath) shape).curveTo(190.42801, 76.842995, 188.83801, 77.27,
                188.83801, 80.22099);
        ((GeneralPath) shape).lineTo(188.83801, 82.49199);
        ((GeneralPath) shape).curveTo(188.83801, 85.72499, 190.6, 85.844986,
                193.059, 85.844986);
        ((GeneralPath) shape).curveTo(194.108, 85.844986, 195.089, 85.844986,
                195.89401, 85.18599);
        ((GeneralPath) shape).curveTo(196.69801, 84.51499, 196.69801, 83.64199,
                196.69801, 82.68099);
        ((GeneralPath) shape).lineTo(196.69801, 82.42299);
        ((GeneralPath) shape).lineTo(194.266, 82.42299);
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
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(200.376, 76.841);
        ((GeneralPath) shape).lineTo(197.944, 76.841);
        ((GeneralPath) shape).lineTo(197.944, 85.841);
        ((GeneralPath) shape).lineTo(204.125, 85.841);
        ((GeneralPath) shape).lineTo(204.125, 83.752);
        ((GeneralPath) shape).lineTo(200.376, 83.752);
        ((GeneralPath) shape).lineTo(200.376, 76.841);
        g.setPaint(paint);
        g.fill(shape);
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
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(207.394, 78.81);
        ((GeneralPath) shape).lineTo(211.479, 78.81);
        ((GeneralPath) shape).lineTo(211.479, 76.841);
        ((GeneralPath) shape).lineTo(204.961, 76.841);
        ((GeneralPath) shape).lineTo(204.961, 85.841);
        ((GeneralPath) shape).lineTo(211.561, 85.841);
        ((GeneralPath) shape).lineTo(211.561, 83.873);
        ((GeneralPath) shape).lineTo(207.394, 83.873);
        ((GeneralPath) shape).lineTo(207.394, 82.091);
        ((GeneralPath) shape).lineTo(211.228, 82.091);
        ((GeneralPath) shape).lineTo(211.228, 80.404);
        ((GeneralPath) shape).lineTo(207.394, 80.404);
        ((GeneralPath) shape).lineTo(207.394, 78.81);
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
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(217.417, 85.841);
        ((GeneralPath) shape).lineTo(219.852, 85.841);
        ((GeneralPath) shape).lineTo(219.852, 83.403);
        ((GeneralPath) shape).lineTo(221.688, 83.403);
        ((GeneralPath) shape).curveTo(222.379, 83.403, 222.659, 83.871,
                222.659, 84.554);
        ((GeneralPath) shape).lineTo(222.659, 85.840004);
        ((GeneralPath) shape).lineTo(225.092, 85.840004);
        ((GeneralPath) shape).lineTo(225.092, 84.01);
        ((GeneralPath) shape).curveTo(225.092, 82.979004, 224.44499, 82.434006,
                223.442, 82.434006);
        ((GeneralPath) shape).lineTo(223.442, 82.33401);
        ((GeneralPath) shape).curveTo(225.092, 81.93201, 225.092, 81.045006,
                225.092, 79.560005);
        ((GeneralPath) shape).curveTo(225.092, 77.289, 224.092, 76.841,
                222.056, 76.841);
        ((GeneralPath) shape).lineTo(217.42, 76.841);
        ((GeneralPath) shape).lineTo(217.417, 85.841);
        ((GeneralPath) shape).moveTo(219.849, 81.341);
        ((GeneralPath) shape).lineTo(219.849, 78.93);
        ((GeneralPath) shape).lineTo(221.677, 78.93);
        ((GeneralPath) shape).curveTo(222.453, 78.93, 222.656, 79.155, 222.656,
                79.952);
        ((GeneralPath) shape).curveTo(222.656, 80.961006, 222.69101, 81.341,
                221.68501, 81.341);
        ((GeneralPath) shape).lineTo(219.849, 81.341);
        g.setPaint(paint);
        g.fill(shape);
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
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(228.748, 78.81);
        ((GeneralPath) shape).lineTo(232.832, 78.81);
        ((GeneralPath) shape).lineTo(232.832, 76.841);
        ((GeneralPath) shape).lineTo(226.315, 76.841);
        ((GeneralPath) shape).lineTo(226.315, 85.841);
        ((GeneralPath) shape).lineTo(232.914, 85.841);
        ((GeneralPath) shape).lineTo(232.914, 83.873);
        ((GeneralPath) shape).lineTo(228.748, 83.873);
        ((GeneralPath) shape).lineTo(228.748, 82.091);
        ((GeneralPath) shape).lineTo(232.581, 82.091);
        ((GeneralPath) shape).lineTo(232.581, 80.404);
        ((GeneralPath) shape).lineTo(228.748, 80.404);
        ((GeneralPath) shape).lineTo(228.748, 78.81);
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
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(239.499, 82.419);
        ((GeneralPath) shape).lineTo(239.499, 82.677);
        ((GeneralPath) shape).curveTo(239.499, 83.708, 239.019, 83.779,
                238.01999, 83.779);
        ((GeneralPath) shape).curveTo(236.566, 83.779, 236.50299, 83.275,
                236.50299, 81.965);
        ((GeneralPath) shape).lineTo(236.50299, 80.597);
        ((GeneralPath) shape).curveTo(236.50299, 79.343, 236.63899, 78.933,
                238.01999, 78.933);
        ((GeneralPath) shape).curveTo(238.844, 78.933, 239.415, 78.992,
                239.499, 79.777);
        ((GeneralPath) shape).lineTo(239.499, 80.117);
        ((GeneralPath) shape).lineTo(241.93399, 80.117);
        ((GeneralPath) shape).lineTo(241.93399, 79.77);
        ((GeneralPath) shape).curveTo(241.93399, 77.091995, 240.77599,
                76.842995, 238.288, 76.842995);
        ((GeneralPath) shape).curveTo(235.663, 76.842995, 234.075, 77.27,
                234.075, 80.22099);
        ((GeneralPath) shape).lineTo(234.075, 82.49199);
        ((GeneralPath) shape).curveTo(234.075, 85.72499, 235.83499, 85.844986,
                238.29399, 85.844986);
        ((GeneralPath) shape).curveTo(239.34299, 85.844986, 240.32599,
                85.844986, 241.12999, 85.18599);
        ((GeneralPath) shape).curveTo(241.93599, 84.51499, 241.93599, 83.64199,
                241.93599, 82.68099);
        ((GeneralPath) shape).lineTo(241.93599, 82.42299);
        ((GeneralPath) shape).lineTo(239.5, 82.42299);
        g.setPaint(paint);
        g.fill(shape);
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
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(246.902, 83.779);
        ((GeneralPath) shape).curveTo(245.64499, 83.779, 245.236, 83.65,
                245.236, 82.34);
        ((GeneralPath) shape).lineTo(245.236, 80.36);
        ((GeneralPath) shape).curveTo(245.236, 79.059, 245.64499, 78.93,
                246.902, 78.93);
        ((GeneralPath) shape).curveTo(248.157, 78.93, 248.60399, 79.059,
                248.60399, 80.36);
        ((GeneralPath) shape).lineTo(248.60399, 82.339);
        ((GeneralPath) shape).curveTo(248.604, 83.65, 248.157, 83.779, 246.902,
                83.779);
        ((GeneralPath) shape).moveTo(246.902, 85.841);
        ((GeneralPath) shape).curveTo(249.31999, 85.841, 251.03699, 85.32,
                251.03699, 82.595);
        ((GeneralPath) shape).lineTo(251.03699, 80.114);
        ((GeneralPath) shape).curveTo(251.03699, 77.366, 249.32098, 76.843,
                246.902, 76.843);
        ((GeneralPath) shape).curveTo(244.50499, 76.843, 242.803, 77.366005,
                242.803, 80.114);
        ((GeneralPath) shape).lineTo(242.803, 82.595);
        ((GeneralPath) shape).curveTo(242.803, 85.323, 244.505, 85.841,
                246.902, 85.841);
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
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(252.291, 85.841);
        ((GeneralPath) shape).lineTo(254.726, 85.841);
        ((GeneralPath) shape).lineTo(254.726, 83.403);
        ((GeneralPath) shape).lineTo(256.562, 83.403);
        ((GeneralPath) shape).curveTo(257.25302, 83.403, 257.53302, 83.871,
                257.53302, 84.554);
        ((GeneralPath) shape).lineTo(257.53302, 85.840004);
        ((GeneralPath) shape).lineTo(259.96603, 85.840004);
        ((GeneralPath) shape).lineTo(259.96603, 84.01);
        ((GeneralPath) shape).curveTo(259.96603, 82.979004, 259.31903,
                82.434006, 258.31604, 82.434006);
        ((GeneralPath) shape).lineTo(258.31604, 82.33401);
        ((GeneralPath) shape).curveTo(259.96603, 81.93201, 259.96603,
                81.045006, 259.96603, 79.560005);
        ((GeneralPath) shape).curveTo(259.96603, 77.289, 258.96603, 76.841,
                256.93002, 76.841);
        ((GeneralPath) shape).lineTo(252.29402, 76.841);
        ((GeneralPath) shape).lineTo(252.291, 85.841);
        ((GeneralPath) shape).moveTo(254.723, 81.341);
        ((GeneralPath) shape).lineTo(254.723, 78.93);
        ((GeneralPath) shape).lineTo(256.549, 78.93);
        ((GeneralPath) shape).curveTo(257.32703, 78.93, 257.528, 79.155,
                257.528, 79.952);
        ((GeneralPath) shape).curveTo(257.528, 80.961006, 257.56302, 81.341,
                256.557, 81.341);
        ((GeneralPath) shape).lineTo(254.723, 81.341);
        g.setPaint(paint);
        g.fill(shape);
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
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(263.435, 78.93);
        ((GeneralPath) shape).lineTo(265.569, 78.93);
        ((GeneralPath) shape).curveTo(266.423, 78.93, 266.803, 79.17, 266.803,
                80.333);
        ((GeneralPath) shape).lineTo(266.803, 82.217);
        ((GeneralPath) shape).curveTo(266.803, 83.163, 266.47302, 83.75201,
                265.569, 83.75201);
        ((GeneralPath) shape).lineTo(263.435, 83.75201);
        ((GeneralPath) shape).lineTo(263.435, 78.93);
        ((GeneralPath) shape).moveTo(261.002, 85.841);
        ((GeneralPath) shape).lineTo(265.96002, 85.841);
        ((GeneralPath) shape).curveTo(268.44803, 85.841, 269.23502, 84.678,
                269.23502, 82.217);
        ((GeneralPath) shape).lineTo(269.23502, 80.333);
        ((GeneralPath) shape).curveTo(269.23502, 77.776, 268.12402, 76.841,
                265.63, 76.841);
        ((GeneralPath) shape).lineTo(261.002, 76.841);
        ((GeneralPath) shape).lineTo(261.002, 85.841);
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
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(277.87, 80.333);
        ((GeneralPath) shape).curveTo(277.223, 80.333, 276.865, 80.31, 276.865,
                79.583);
        ((GeneralPath) shape).curveTo(276.865, 79.076, 276.97598, 78.81,
                278.298, 78.81);
        ((GeneralPath) shape).curveTo(279.28, 78.81, 279.672, 78.81, 279.672,
                79.741);
        ((GeneralPath) shape).lineTo(281.918, 79.741);
        ((GeneralPath) shape).lineTo(281.918, 79.364);
        ((GeneralPath) shape).curveTo(281.918, 76.843, 280.309, 76.843,
                278.298, 76.843);
        ((GeneralPath) shape).curveTo(275.909, 76.843, 274.435, 77.01, 274.435,
                79.597);
        ((GeneralPath) shape).curveTo(274.435, 82.131, 275.704, 82.119,
                278.006, 82.281);
        ((GeneralPath) shape).lineTo(278.55402, 82.281);
        ((GeneralPath) shape).curveTo(279.72403, 82.281, 279.86102, 82.269,
                279.86102, 83.004);
        ((GeneralPath) shape).curveTo(279.86102, 83.715, 279.47504, 83.781,
                278.29803, 83.781);
        ((GeneralPath) shape).curveTo(277.03802, 83.781, 276.68002, 83.689995,
                276.68002, 82.811);
        ((GeneralPath) shape).lineTo(274.43503, 82.811);
        ((GeneralPath) shape).curveTo(274.43503, 85.842995, 275.91504,
                85.842995, 278.29803, 85.842995);
        ((GeneralPath) shape).curveTo(282.29202, 85.842995, 282.29202,
                84.38999, 282.29202, 82.909996);
        ((GeneralPath) shape).curveTo(282.29202, 80.856995, 281.61703,
                80.522995, 278.89102, 80.335);
        ((GeneralPath) shape).lineTo(277.87, 80.333);
        g.setPaint(paint);
        g.fill(shape);
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
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(291.438, 76.841);
        ((GeneralPath) shape).lineTo(289.006, 76.841);
        ((GeneralPath) shape).lineTo(289.006, 80.216);
        ((GeneralPath) shape).lineTo(285.825, 80.216);
        ((GeneralPath) shape).lineTo(285.825, 76.841);
        ((GeneralPath) shape).lineTo(283.392, 76.841);
        ((GeneralPath) shape).lineTo(283.392, 85.841);
        ((GeneralPath) shape).lineTo(285.825, 85.841);
        ((GeneralPath) shape).lineTo(285.825, 82.279);
        ((GeneralPath) shape).lineTo(289.006, 82.279);
        ((GeneralPath) shape).lineTo(289.006, 85.841);
        ((GeneralPath) shape).lineTo(291.438, 85.841);
        ((GeneralPath) shape).lineTo(291.438, 76.841);
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
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(295.274, 78.81);
        ((GeneralPath) shape).lineTo(299.358, 78.81);
        ((GeneralPath) shape).lineTo(299.358, 76.841);
        ((GeneralPath) shape).lineTo(292.841, 76.841);
        ((GeneralPath) shape).lineTo(292.841, 85.841);
        ((GeneralPath) shape).lineTo(299.44, 85.841);
        ((GeneralPath) shape).lineTo(299.44, 83.873);
        ((GeneralPath) shape).lineTo(295.274, 83.873);
        ((GeneralPath) shape).lineTo(295.274, 82.091);
        ((GeneralPath) shape).lineTo(299.107, 82.091);
        ((GeneralPath) shape).lineTo(299.107, 80.404);
        ((GeneralPath) shape).lineTo(295.274, 80.404);
        ((GeneralPath) shape).lineTo(295.274, 78.81);
        g.setPaint(paint);
        g.fill(shape);
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
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(303.406, 78.81);
        ((GeneralPath) shape).lineTo(307.49, 78.81);
        ((GeneralPath) shape).lineTo(307.49, 76.841);
        ((GeneralPath) shape).lineTo(300.973, 76.841);
        ((GeneralPath) shape).lineTo(300.973, 85.841);
        ((GeneralPath) shape).lineTo(307.572, 85.841);
        ((GeneralPath) shape).lineTo(307.572, 83.873);
        ((GeneralPath) shape).lineTo(303.406, 83.873);
        ((GeneralPath) shape).lineTo(303.406, 82.091);
        ((GeneralPath) shape).lineTo(307.239, 82.091);
        ((GeneralPath) shape).lineTo(307.239, 80.404);
        ((GeneralPath) shape).lineTo(303.406, 80.404);
        ((GeneralPath) shape).lineTo(303.406, 78.81);
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
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(313.034, 78.93);
        ((GeneralPath) shape).lineTo(315.453, 78.93);
        ((GeneralPath) shape).lineTo(315.453, 76.841);
        ((GeneralPath) shape).lineTo(308.304, 76.841);
        ((GeneralPath) shape).lineTo(308.304, 78.93);
        ((GeneralPath) shape).lineTo(310.602, 78.93);
        ((GeneralPath) shape).lineTo(310.602, 85.841);
        ((GeneralPath) shape).lineTo(313.034, 85.841);
        ((GeneralPath) shape).lineTo(313.034, 78.93);
        g.setPaint(paint);
        g.fill(shape);
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
        paint = new Color(204, 206, 207, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(246.515, 386.315);
        ((GeneralPath) shape).lineTo(239.428, 397.652);
        ((GeneralPath) shape).lineTo(40.988, 397.653);
        ((GeneralPath) shape).lineTo(33.916, 387.634);
        ((GeneralPath) shape).lineTo(33.916, 102.849);
        ((GeneralPath) shape).lineTo(39.585, 94.344);
        ((GeneralPath) shape).lineTo(124.625, 94.344);
        ((GeneralPath) shape).lineTo(130.293, 102.849);
        ((GeneralPath) shape).lineTo(246.515, 102.849);
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
        paint = new Color(204, 206, 207, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(389.666, 189.306);
        ((GeneralPath) shape).lineTo(396.754, 196.392);
        ((GeneralPath) shape).lineTo(396.754, 271.51);
        ((GeneralPath) shape).lineTo(389.666, 278.597);
        ((GeneralPath) shape).lineTo(314.549, 278.597);
        ((GeneralPath) shape).lineTo(310.297, 271.51);
        ((GeneralPath) shape).lineTo(255.019, 271.51);
        ((GeneralPath) shape).lineTo(255.019, 189.306);
        ((GeneralPath) shape).lineTo(260.688, 180.802);
        ((GeneralPath) shape).lineTo(366.987, 180.802);
        ((GeneralPath) shape).lineTo(372.657, 189.306);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_24;
        g.setTransform(defaultTransform__0_24);
        g.setClip(clip__0_24);
        float alpha__0_25 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_25 = g.getClip();
        AffineTransform defaultTransform__0_25 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_25 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(242.263, 382.064);
        ((GeneralPath) shape).lineTo(235.176, 393.401);
        ((GeneralPath) shape).lineTo(36.736, 393.402);
        ((GeneralPath) shape).lineTo(29.664, 383.383);
        ((GeneralPath) shape).lineTo(29.664, 98.597);
        ((GeneralPath) shape).lineTo(35.333, 90.094);
        ((GeneralPath) shape).lineTo(120.373, 90.094);
        ((GeneralPath) shape).lineTo(126.042, 98.597);
        ((GeneralPath) shape).lineTo(242.263, 98.597);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_25;
        g.setTransform(defaultTransform__0_25);
        g.setClip(clip__0_25);
        float alpha__0_26 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_26 = g.getClip();
        AffineTransform defaultTransform__0_26 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_26 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        stroke = new BasicStroke(2.0f, 0, 1, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(242.263, 382.064);
        ((GeneralPath) shape).lineTo(235.176, 393.401);
        ((GeneralPath) shape).lineTo(36.736, 393.402);
        ((GeneralPath) shape).lineTo(29.664, 383.383);
        ((GeneralPath) shape).lineTo(29.664, 98.597);
        ((GeneralPath) shape).lineTo(35.333, 90.094);
        ((GeneralPath) shape).lineTo(120.373, 90.094);
        ((GeneralPath) shape).lineTo(126.042, 98.597);
        ((GeneralPath) shape).lineTo(242.263, 98.597);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_26;
        g.setTransform(defaultTransform__0_26);
        g.setClip(clip__0_26);
        float alpha__0_27 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_27 = g.getClip();
        AffineTransform defaultTransform__0_27 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_27 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(118.968, 108.519);
        ((GeneralPath) shape).lineTo(37.118, 108.519);
        ((GeneralPath) shape).lineTo(31.733, 100.439);
        ((GeneralPath) shape).lineTo(37.105, 92.36);
        ((GeneralPath) shape).lineTo(118.955, 92.36);
        ((GeneralPath) shape).lineTo(124.342, 100.439);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_27;
        g.setTransform(defaultTransform__0_27);
        g.setClip(clip__0_27);
        float alpha__0_28 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_28 = g.getClip();
        AffineTransform defaultTransform__0_28 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_28 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        stroke = new BasicStroke(1.0f, 0, 1, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(118.968, 108.52);
        ((GeneralPath) shape).lineTo(37.118, 108.52);
        ((GeneralPath) shape).lineTo(31.733, 100.44);
        ((GeneralPath) shape).lineTo(37.105, 92.36);
        ((GeneralPath) shape).lineTo(118.955, 92.36);
        ((GeneralPath) shape).lineTo(124.342, 100.44);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_28;
        g.setTransform(defaultTransform__0_28);
        g.setClip(clip__0_28);
        float alpha__0_29 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_29 = g.getClip();
        AffineTransform defaultTransform__0_29 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_29 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(43.98, 96.269);
        ((GeneralPath) shape).lineTo(45.583, 96.269);
        ((GeneralPath) shape).lineTo(43.010002, 104.268);
        ((GeneralPath) shape).lineTo(40.684002, 104.268);
        ((GeneralPath) shape).lineTo(38.132004, 96.269);
        ((GeneralPath) shape).lineTo(39.706005, 96.269);
        ((GeneralPath) shape).lineTo(41.203007, 100.798996);
        ((GeneralPath) shape).curveTo(41.343006, 101.232994, 41.551006,
                101.959, 41.828007, 102.979);
        ((GeneralPath) shape).lineTo(41.869007, 102.979);
        ((GeneralPath) shape).lineTo(42.021008, 102.434);
        ((GeneralPath) shape).curveTo(42.208008, 101.754, 42.36601, 101.212,
                42.500008, 100.805);
        ((GeneralPath) shape).lineTo(43.98, 96.269);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_29;
        g.setTransform(defaultTransform__0_29);
        g.setClip(clip__0_29);
        float alpha__0_30 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_30 = g.getClip();
        AffineTransform defaultTransform__0_30 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_30 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(47.605, 97.546);
        ((GeneralPath) shape).lineTo(47.605, 99.609);
        ((GeneralPath) shape).lineTo(51.348, 99.609);
        ((GeneralPath) shape).lineTo(51.348, 100.729);
        ((GeneralPath) shape).lineTo(47.605, 100.729);
        ((GeneralPath) shape).lineTo(47.605, 102.99);
        ((GeneralPath) shape).lineTo(51.588, 102.99);
        ((GeneralPath) shape).lineTo(51.588, 104.268);
        ((GeneralPath) shape).lineTo(46.09, 104.268);
        ((GeneralPath) shape).lineTo(46.09, 96.269);
        ((GeneralPath) shape).lineTo(51.553, 96.269);
        ((GeneralPath) shape).lineTo(51.553, 97.546);
        ((GeneralPath) shape).lineTo(47.605, 97.546);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_30;
        g.setTransform(defaultTransform__0_30);
        g.setClip(clip__0_30);
        float alpha__0_31 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_31 = g.getClip();
        AffineTransform defaultTransform__0_31 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_31 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(59.49, 96.269);
        ((GeneralPath) shape).lineTo(59.49, 104.268);
        ((GeneralPath) shape).lineTo(57.976, 104.268);
        ((GeneralPath) shape).lineTo(57.976, 100.811);
        ((GeneralPath) shape).lineTo(54.232, 100.811);
        ((GeneralPath) shape).lineTo(54.232, 104.268);
        ((GeneralPath) shape).lineTo(52.718, 104.268);
        ((GeneralPath) shape).lineTo(52.718, 96.269);
        ((GeneralPath) shape).lineTo(54.232, 96.269);
        ((GeneralPath) shape).lineTo(54.232, 99.533);
        ((GeneralPath) shape).lineTo(57.976, 99.533);
        ((GeneralPath) shape).lineTo(57.976, 96.269);
        ((GeneralPath) shape).lineTo(59.49, 96.269);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_31;
        g.setTransform(defaultTransform__0_31);
        g.setClip(clip__0_31);
        float alpha__0_32 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_32 = g.getClip();
        AffineTransform defaultTransform__0_32 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_32 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new Rectangle2D.Double(60.89899826049805, 96.26899719238281,
                1.5149999856948853, 7.999000072479248);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_32;
        g.setTransform(defaultTransform__0_32);
        g.setClip(clip__0_32);
        float alpha__0_33 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_33 = g.getClip();
        AffineTransform defaultTransform__0_33 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_33 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(68.765, 101.414);
        ((GeneralPath) shape).lineTo(70.286, 101.414);
        ((GeneralPath) shape).lineTo(70.286, 101.688);
        ((GeneralPath) shape).curveTo(70.286, 102.801, 70.084, 103.519005,
                69.68, 103.847);
        ((GeneralPath) shape).curveTo(69.276, 104.173, 68.376, 104.337, 66.989,
                104.337);
        ((GeneralPath) shape).curveTo(65.416, 104.337, 64.446, 104.078995,
                64.083, 103.563995);
        ((GeneralPath) shape).curveTo(63.721, 103.048996, 63.539, 101.671,
                63.539, 99.426994);
        ((GeneralPath) shape).curveTo(63.539, 98.107994, 63.785, 97.23799,
                64.276, 96.823);
        ((GeneralPath) shape).curveTo(64.767, 96.406, 65.793, 96.2, 67.358,
                96.2);
        ((GeneralPath) shape).curveTo(68.495, 96.2, 69.254005, 96.369995,
                69.639, 96.712);
        ((GeneralPath) shape).curveTo(70.019, 97.051994, 70.21, 97.731995,
                70.21, 98.749);
        ((GeneralPath) shape).lineTo(70.215996, 98.931);
        ((GeneralPath) shape).lineTo(68.69499, 98.931);
        ((GeneralPath) shape).lineTo(68.69499, 98.726);
        ((GeneralPath) shape).curveTo(68.69499, 98.204994, 68.59899, 97.869995,
                68.399994, 97.721);
        ((GeneralPath) shape).curveTo(68.203995, 97.575, 67.75399, 97.501,
                67.049995, 97.501);
        ((GeneralPath) shape).curveTo(66.11199, 97.501, 65.547, 97.613,
                65.35899, 97.847);
        ((GeneralPath) shape).curveTo(65.17099, 98.076, 65.075, 98.761, 65.075,
                99.898);
        ((GeneralPath) shape).curveTo(65.075, 101.427, 65.159, 102.336006,
                65.328995, 102.619);
        ((GeneralPath) shape).curveTo(65.49799, 102.898, 66.045, 103.041,
                66.975, 103.041);
        ((GeneralPath) shape).curveTo(67.726, 103.041, 68.212, 102.964,
                68.439995, 102.807);
        ((GeneralPath) shape).curveTo(68.661995, 102.652, 68.77599, 102.309,
                68.77599, 101.776);
        ((GeneralPath) shape).lineTo(68.765, 101.414);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_33;
        g.setTransform(defaultTransform__0_33);
        g.setClip(clip__0_33);
        float alpha__0_34 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_34 = g.getClip();
        AffineTransform defaultTransform__0_34 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_34 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(72.753, 96.269);
        ((GeneralPath) shape).lineTo(72.753, 102.908);
        ((GeneralPath) shape).lineTo(76.496, 102.908);
        ((GeneralPath) shape).lineTo(76.496, 104.268);
        ((GeneralPath) shape).lineTo(71.238, 104.268);
        ((GeneralPath) shape).lineTo(71.238, 96.269);
        ((GeneralPath) shape).lineTo(72.753, 96.269);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_34;
        g.setTransform(defaultTransform__0_34);
        g.setClip(clip__0_34);
        float alpha__0_35 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_35 = g.getClip();
        AffineTransform defaultTransform__0_35 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_35 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(78.621, 97.546);
        ((GeneralPath) shape).lineTo(78.621, 99.609);
        ((GeneralPath) shape).lineTo(82.363, 99.609);
        ((GeneralPath) shape).lineTo(82.363, 100.729);
        ((GeneralPath) shape).lineTo(78.621, 100.729);
        ((GeneralPath) shape).lineTo(78.621, 102.99);
        ((GeneralPath) shape).lineTo(82.604, 102.99);
        ((GeneralPath) shape).lineTo(82.604, 104.268);
        ((GeneralPath) shape).lineTo(77.105, 104.268);
        ((GeneralPath) shape).lineTo(77.105, 96.269);
        ((GeneralPath) shape).lineTo(82.568, 96.269);
        ((GeneralPath) shape).lineTo(82.568, 97.546);
        ((GeneralPath) shape).lineTo(78.621, 97.546);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_35;
        g.setTransform(defaultTransform__0_35);
        g.setClip(clip__0_35);
        float alpha__0_36 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_36 = g.getClip();
        AffineTransform defaultTransform__0_36 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_36 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(88.242, 102.99);
        ((GeneralPath) shape).lineTo(90.4, 102.99);
        ((GeneralPath) shape).curveTo(91.125, 102.99, 91.591, 102.823, 91.806,
                102.489);
        ((GeneralPath) shape).curveTo(92.016, 102.156, 92.123, 101.422, 92.123,
                100.277);
        ((GeneralPath) shape).curveTo(92.123, 99.1, 92.03, 98.35, 91.836,
                98.027);
        ((GeneralPath) shape).curveTo(91.647995, 97.71, 91.199, 97.548004,
                90.491, 97.548004);
        ((GeneralPath) shape).lineTo(88.24, 97.548004);
        ((GeneralPath) shape).lineTo(88.242, 102.99);
        ((GeneralPath) shape).moveTo(86.727, 104.268);
        ((GeneralPath) shape).lineTo(86.727, 96.269);
        ((GeneralPath) shape).lineTo(90.651, 96.269);
        ((GeneralPath) shape).curveTo(91.766, 96.269, 92.547005, 96.51199,
                92.994, 97.002);
        ((GeneralPath) shape).curveTo(93.438, 97.486, 93.663, 98.344, 93.663,
                99.566);
        ((GeneralPath) shape).curveTo(93.663, 101.561005, 93.485, 102.844,
                93.125, 103.414);
        ((GeneralPath) shape).curveTo(92.77, 103.981, 91.958, 104.267, 90.698,
                104.267);
        ((GeneralPath) shape).lineTo(86.727, 104.268);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_36;
        g.setTransform(defaultTransform__0_36);
        g.setClip(clip__0_36);
        float alpha__0_37 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_37 = g.getClip();
        AffineTransform defaultTransform__0_37 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_37 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(99.244, 101.613);
        ((GeneralPath) shape).lineTo(97.864006, 97.447);
        ((GeneralPath) shape).lineTo(96.508, 101.613);
        ((GeneralPath) shape).lineTo(99.244, 101.613);
        ((GeneralPath) shape).moveTo(99.595, 102.732);
        ((GeneralPath) shape).lineTo(96.15, 102.732);
        ((GeneralPath) shape).lineTo(95.653, 104.267006);
        ((GeneralPath) shape).lineTo(94.05, 104.267006);
        ((GeneralPath) shape).lineTo(96.705, 96.268005);
        ((GeneralPath) shape).lineTo(98.98, 96.268005);
        ((GeneralPath) shape).lineTo(101.674, 104.267006);
        ((GeneralPath) shape).lineTo(100.103004, 104.267006);
        ((GeneralPath) shape).lineTo(99.595, 102.732);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_37;
        g.setTransform(defaultTransform__0_37);
        g.setClip(clip__0_37);
        float alpha__0_38 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_38 = g.getClip();
        AffineTransform defaultTransform__0_38 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_38 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(105.553, 97.628);
        ((GeneralPath) shape).lineTo(105.553, 104.268);
        ((GeneralPath) shape).lineTo(104.038, 104.268);
        ((GeneralPath) shape).lineTo(104.038, 97.628);
        ((GeneralPath) shape).lineTo(101.733, 97.628);
        ((GeneralPath) shape).lineTo(101.733, 96.269);
        ((GeneralPath) shape).lineTo(107.939, 96.269);
        ((GeneralPath) shape).lineTo(107.939, 97.628);
        ((GeneralPath) shape).lineTo(105.553, 97.628);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_38;
        g.setTransform(defaultTransform__0_38);
        g.setClip(clip__0_38);
        float alpha__0_39 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_39 = g.getClip();
        AffineTransform defaultTransform__0_39 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_39 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(113.188, 101.613);
        ((GeneralPath) shape).lineTo(111.80801, 97.447);
        ((GeneralPath) shape).lineTo(110.452, 101.613);
        ((GeneralPath) shape).lineTo(113.188, 101.613);
        ((GeneralPath) shape).moveTo(113.539, 102.732);
        ((GeneralPath) shape).lineTo(110.096, 102.732);
        ((GeneralPath) shape).lineTo(109.599, 104.267006);
        ((GeneralPath) shape).lineTo(107.995, 104.267006);
        ((GeneralPath) shape).lineTo(110.65, 96.268005);
        ((GeneralPath) shape).lineTo(112.923004, 96.268005);
        ((GeneralPath) shape).lineTo(115.619, 104.267006);
        ((GeneralPath) shape).lineTo(114.046005, 104.267006);
        ((GeneralPath) shape).lineTo(113.539, 102.732);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_39;
        g.setTransform(defaultTransform__0_39);
        g.setClip(clip__0_39);
        float alpha__0_40 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_40 = g.getClip();
        AffineTransform defaultTransform__0_40 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_40 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(271.987, 283.167);
        ((GeneralPath) shape).curveTo(270.969, 283.167, 270.376, 283.296,
                270.203, 283.56);
        ((GeneralPath) shape).curveTo(270.034, 283.82098, 269.946, 284.727,
                269.946, 286.279);
        ((GeneralPath) shape).curveTo(269.946, 287.427, 270.045, 288.121,
                270.247, 288.353);
        ((GeneralPath) shape).curveTo(270.446, 288.587, 271.039, 288.707,
                272.022, 288.707);
        ((GeneralPath) shape).curveTo(272.96, 288.707, 273.525, 288.574,
                273.71, 288.311);
        ((GeneralPath) shape).curveTo(273.89798, 288.048, 273.99, 287.24802,
                273.99, 285.914);
        ((GeneralPath) shape).curveTo(273.99, 284.573, 273.90298, 283.782,
                273.724, 283.538);
        ((GeneralPath) shape).curveTo(273.551, 283.291, 272.97, 283.167,
                271.987, 283.167);
        ((GeneralPath) shape).moveTo(272.104, 281.867);
        ((GeneralPath) shape).curveTo(273.555, 281.867, 274.487, 282.105,
                274.906, 282.59);
        ((GeneralPath) shape).curveTo(275.322, 283.069, 275.531, 284.152,
                275.531, 285.828);
        ((GeneralPath) shape).curveTo(275.531, 287.659, 275.323, 288.811,
                274.903, 289.29);
        ((GeneralPath) shape).curveTo(274.48502, 289.765, 273.47, 290.005,
                271.859, 290.005);
        ((GeneralPath) shape).curveTo(270.409, 290.005, 269.474, 289.771,
                269.04602, 289.299);
        ((GeneralPath) shape).curveTo(268.622, 288.83002, 268.40802, 287.793,
                268.40802, 286.19);
        ((GeneralPath) shape).curveTo(268.40802, 284.288, 268.61603, 283.088,
                269.03403, 282.598);
        ((GeneralPath) shape).curveTo(269.449, 282.113, 270.472, 281.867,
                272.104, 281.867);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_40;
        g.setTransform(defaultTransform__0_40);
        g.setClip(clip__0_40);
        float alpha__0_41 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_41 = g.getClip();
        AffineTransform defaultTransform__0_41 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_41 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(279.73, 283.296);
        ((GeneralPath) shape).lineTo(279.73, 289.935);
        ((GeneralPath) shape).lineTo(278.216, 289.935);
        ((GeneralPath) shape).lineTo(278.216, 283.296);
        ((GeneralPath) shape).lineTo(275.911, 283.296);
        ((GeneralPath) shape).lineTo(275.911, 281.937);
        ((GeneralPath) shape).lineTo(282.117, 281.937);
        ((GeneralPath) shape).lineTo(282.117, 283.296);
        ((GeneralPath) shape).lineTo(279.73, 283.296);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_41;
        g.setTransform(defaultTransform__0_41);
        g.setClip(clip__0_41);
        float alpha__0_42 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_42 = g.getClip();
        AffineTransform defaultTransform__0_42 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_42 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(284.242, 283.214);
        ((GeneralPath) shape).lineTo(284.242, 285.277);
        ((GeneralPath) shape).lineTo(287.985, 285.277);
        ((GeneralPath) shape).lineTo(287.985, 286.396);
        ((GeneralPath) shape).lineTo(284.242, 286.396);
        ((GeneralPath) shape).lineTo(284.242, 288.656);
        ((GeneralPath) shape).lineTo(288.225, 288.656);
        ((GeneralPath) shape).lineTo(288.225, 289.935);
        ((GeneralPath) shape).lineTo(282.727, 289.935);
        ((GeneralPath) shape).lineTo(282.727, 281.937);
        ((GeneralPath) shape).lineTo(288.19, 281.937);
        ((GeneralPath) shape).lineTo(288.19, 283.214);
        ((GeneralPath) shape).lineTo(284.242, 283.214);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_42;
        g.setTransform(defaultTransform__0_42);
        g.setClip(clip__0_42);
        float alpha__0_43 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_43 = g.getClip();
        AffineTransform defaultTransform__0_43 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_43 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(295.277, 284.269);
        ((GeneralPath) shape).lineTo(293.798, 284.269);
        ((GeneralPath) shape).curveTo(293.792, 284.196, 293.786, 284.14102,
                293.786, 284.105);
        ((GeneralPath) shape).curveTo(293.751, 283.657, 293.651, 283.376,
                293.488, 283.25803);
        ((GeneralPath) shape).curveTo(293.324, 283.144, 292.93802, 283.08502,
                292.33002, 283.08502);
        ((GeneralPath) shape).curveTo(291.613, 283.08502, 291.14502, 283.148,
                290.923, 283.28503);
        ((GeneralPath) shape).curveTo(290.704, 283.41702, 290.592, 283.69302,
                290.592, 284.12204);
        ((GeneralPath) shape).curveTo(290.592, 284.62604, 290.682, 284.92804,
                290.863, 285.02905);
        ((GeneralPath) shape).curveTo(291.04102, 285.13007, 291.634, 285.21005,
                292.64102, 285.26306);
        ((GeneralPath) shape).curveTo(293.829, 285.32706, 294.59702, 285.49707,
                294.94803, 285.77206);
        ((GeneralPath) shape).curveTo(295.29602, 286.04407, 295.47104,
                286.61505, 295.47104, 287.47705);
        ((GeneralPath) shape).curveTo(295.47104, 288.54004, 295.26605,
                289.22705, 294.85703, 289.53906);
        ((GeneralPath) shape).curveTo(294.44803, 289.85007, 293.545, 290.00507,
                292.14902, 290.00507);
        ((GeneralPath) shape).curveTo(290.89502, 290.00507, 290.062, 289.85306,
                289.64902, 289.54807);
        ((GeneralPath) shape).curveTo(289.24002, 289.24307, 289.032, 288.62805,
                289.032, 287.69406);
        ((GeneralPath) shape).lineTo(289.026, 287.40106);
        ((GeneralPath) shape).lineTo(290.5, 287.40106);
        ((GeneralPath) shape).lineTo(290.506, 287.57205);
        ((GeneralPath) shape).curveTo(290.506, 288.12906, 290.605, 288.47006,
                290.798, 288.59604);
        ((GeneralPath) shape).curveTo(290.991, 288.72003, 291.525, 288.78403,
                292.397, 288.78403);
        ((GeneralPath) shape).curveTo(293.075, 288.78403, 293.506, 288.71402,
                293.695, 288.56503);
        ((GeneralPath) shape).curveTo(293.88202, 288.42102, 293.976, 288.08804,
                293.976, 287.56302);
        ((GeneralPath) shape).curveTo(293.976, 287.17603, 293.906, 286.92102,
                293.76202, 286.79202);
        ((GeneralPath) shape).curveTo(293.62103, 286.66602, 293.31503,
                286.59003, 292.84003, 286.56104);
        ((GeneralPath) shape).lineTo(292.00403, 286.50803);
        ((GeneralPath) shape).curveTo(290.74103, 286.43503, 289.93604,
                286.25903, 289.584, 285.98102);
        ((GeneralPath) shape).curveTo(289.233, 285.708, 289.05902, 285.10803,
                289.05902, 284.19403);
        ((GeneralPath) shape).curveTo(289.05902, 283.26202, 289.27002,
                282.63803, 289.69403, 282.32904);
        ((GeneralPath) shape).curveTo(290.11502, 282.01703, 290.96002,
                281.86304, 292.22903, 281.86304);
        ((GeneralPath) shape).curveTo(293.42603, 281.86304, 294.23804,
                282.00403, 294.65604, 282.29105);
        ((GeneralPath) shape).curveTo(295.07202, 282.57504, 295.28204,
                283.13205, 295.28204, 283.96106);
        ((GeneralPath) shape).lineTo(295.277, 284.269);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_43;
        g.setTransform(defaultTransform__0_43);
        g.setClip(clip__0_43);
        float alpha__0_44 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_44 = g.getClip();
        AffineTransform defaultTransform__0_44 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_44 is ShapeNode
        paint = new Color(204, 206, 207, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(389.666, 102.849);
        ((GeneralPath) shape).lineTo(396.754, 112.771);
        ((GeneralPath) shape).lineTo(396.754, 165.211);
        ((GeneralPath) shape).lineTo(389.666, 172.298);
        ((GeneralPath) shape).lineTo(255.019, 172.298);
        ((GeneralPath) shape).lineTo(255.019, 102.849);
        ((GeneralPath) shape).lineTo(260.688, 94.345);
        ((GeneralPath) shape).lineTo(332.973, 94.345);
        ((GeneralPath) shape).lineTo(338.643, 102.849);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_44;
        g.setTransform(defaultTransform__0_44);
        g.setClip(clip__0_44);
        float alpha__0_45 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_45 = g.getClip();
        AffineTransform defaultTransform__0_45 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_45 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(385.414, 98.597);
        ((GeneralPath) shape).lineTo(392.498, 108.519);
        ((GeneralPath) shape).lineTo(392.498, 160.959);
        ((GeneralPath) shape).lineTo(385.414, 168.046);
        ((GeneralPath) shape).lineTo(250.767, 168.046);
        ((GeneralPath) shape).lineTo(250.767, 98.597);
        ((GeneralPath) shape).lineTo(256.437, 90.094);
        ((GeneralPath) shape).lineTo(328.72, 90.094);
        ((GeneralPath) shape).lineTo(334.39, 98.597);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_45;
        g.setTransform(defaultTransform__0_45);
        g.setClip(clip__0_45);
        float alpha__0_46 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_46 = g.getClip();
        AffineTransform defaultTransform__0_46 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_46 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        stroke = new BasicStroke(2.0f, 0, 0, 10.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(385.412, 98.597);
        ((GeneralPath) shape).lineTo(392.498, 108.52);
        ((GeneralPath) shape).lineTo(392.498, 160.959);
        ((GeneralPath) shape).lineTo(385.412, 168.047);
        ((GeneralPath) shape).lineTo(250.767, 168.047);
        ((GeneralPath) shape).lineTo(250.767, 98.597);
        ((GeneralPath) shape).lineTo(256.435, 90.094);
        ((GeneralPath) shape).lineTo(328.719, 90.094);
        ((GeneralPath) shape).lineTo(334.389, 98.597);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_46;
        g.setTransform(defaultTransform__0_46);
        g.setClip(clip__0_46);
        float alpha__0_47 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_47 = g.getClip();
        AffineTransform defaultTransform__0_47 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_47 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(327.315, 108.519);
        ((GeneralPath) shape).lineTo(257.569, 108.519);
        ((GeneralPath) shape).lineTo(252.184, 100.439);
        ((GeneralPath) shape).lineTo(257.556, 92.36);
        ((GeneralPath) shape).lineTo(327.303, 92.36);
        ((GeneralPath) shape).lineTo(332.689, 100.439);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_47;
        g.setTransform(defaultTransform__0_47);
        g.setClip(clip__0_47);
        float alpha__0_48 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_48 = g.getClip();
        AffineTransform defaultTransform__0_48 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_48 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        stroke = new BasicStroke(1.0f, 0, 1, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(327.315, 108.52);
        ((GeneralPath) shape).lineTo(257.569, 108.52);
        ((GeneralPath) shape).lineTo(252.184, 100.44);
        ((GeneralPath) shape).lineTo(257.556, 92.36);
        ((GeneralPath) shape).lineTo(327.303, 92.36);
        ((GeneralPath) shape).lineTo(332.689, 100.44);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_48;
        g.setTransform(defaultTransform__0_48);
        g.setClip(clip__0_48);
        float alpha__0_49 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_49 = g.getClip();
        AffineTransform defaultTransform__0_49 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_49 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(264.903, 101.414);
        ((GeneralPath) shape).lineTo(266.424, 101.414);
        ((GeneralPath) shape).lineTo(266.424, 101.688);
        ((GeneralPath) shape).curveTo(266.424, 102.801, 266.224, 103.519005,
                265.81802, 103.847);
        ((GeneralPath) shape).curveTo(265.41403, 104.173, 264.51602, 104.337,
                263.127, 104.337);
        ((GeneralPath) shape).curveTo(261.556, 104.337, 260.584, 104.078995,
                260.22302, 103.563995);
        ((GeneralPath) shape).curveTo(259.86002, 103.048996, 259.67703,
                101.671, 259.67703, 99.426994);
        ((GeneralPath) shape).curveTo(259.67703, 98.107994, 259.92303,
                97.23799, 260.41403, 96.823);
        ((GeneralPath) shape).curveTo(260.90503, 96.406, 261.93304, 96.2,
                263.49603, 96.2);
        ((GeneralPath) shape).curveTo(264.63403, 96.2, 265.39404, 96.369995,
                265.77704, 96.712);
        ((GeneralPath) shape).curveTo(266.15704, 97.051994, 266.35004,
                97.731995, 266.35004, 98.749);
        ((GeneralPath) shape).lineTo(266.35605, 98.931);
        ((GeneralPath) shape).lineTo(264.83505, 98.931);
        ((GeneralPath) shape).lineTo(264.83505, 98.726);
        ((GeneralPath) shape).curveTo(264.83505, 98.204994, 264.73807,
                97.869995, 264.54004, 97.721);
        ((GeneralPath) shape).curveTo(264.34402, 97.575, 263.89404, 97.501,
                263.19205, 97.501);
        ((GeneralPath) shape).curveTo(262.25406, 97.501, 261.68906, 97.613,
                261.49905, 97.847);
        ((GeneralPath) shape).curveTo(261.31204, 98.076, 261.21506, 98.761,
                261.21506, 99.898);
        ((GeneralPath) shape).curveTo(261.21506, 101.427, 261.30005,
                102.336006, 261.47006, 102.619);
        ((GeneralPath) shape).curveTo(261.64008, 102.898, 262.18707, 103.041,
                263.11606, 103.041);
        ((GeneralPath) shape).curveTo(263.86707, 103.041, 264.35507, 102.964,
                264.58105, 102.807);
        ((GeneralPath) shape).curveTo(264.80304, 102.652, 264.91705, 102.309,
                264.91705, 101.776);
        ((GeneralPath) shape).lineTo(264.903, 101.414);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_49;
        g.setTransform(defaultTransform__0_49);
        g.setClip(clip__0_49);
        float alpha__0_50 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_50 = g.getClip();
        AffineTransform defaultTransform__0_50 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_50 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(268.89, 100.295);
        ((GeneralPath) shape).lineTo(271.065, 100.295);
        ((GeneralPath) shape).curveTo(271.583, 100.295, 271.928, 100.203995,
                272.098, 100.016);
        ((GeneralPath) shape).curveTo(272.267, 99.831, 272.352, 99.46, 272.352,
                98.9);
        ((GeneralPath) shape).curveTo(272.352, 98.333, 272.279, 97.962,
                272.13098, 97.796005);
        ((GeneralPath) shape).curveTo(271.985, 97.632, 271.666, 97.546005,
                271.16898, 97.546005);
        ((GeneralPath) shape).lineTo(268.89096, 97.546005);
        ((GeneralPath) shape).lineTo(268.89, 100.295);
        ((GeneralPath) shape).moveTo(267.375, 104.268);
        ((GeneralPath) shape).lineTo(267.375, 96.269);
        ((GeneralPath) shape).lineTo(271.313, 96.269);
        ((GeneralPath) shape).curveTo(272.28998, 96.269, 272.96298, 96.438995,
                273.33398, 96.779);
        ((GeneralPath) shape).curveTo(273.70297, 97.121, 273.892, 97.734,
                273.892, 98.626);
        ((GeneralPath) shape).curveTo(273.892, 99.435, 273.799, 99.983,
                273.61398, 100.284996);
        ((GeneralPath) shape).curveTo(273.43, 100.579994, 273.05, 100.785995,
                272.477, 100.897995);
        ((GeneralPath) shape).lineTo(272.477, 100.952995);
        ((GeneralPath) shape).curveTo(273.36, 101.005, 273.805, 101.521996,
                273.805, 102.505);
        ((GeneralPath) shape).lineTo(273.805, 104.269);
        ((GeneralPath) shape).lineTo(272.288, 104.269);
        ((GeneralPath) shape).lineTo(272.288, 102.81);
        ((GeneralPath) shape).curveTo(272.288, 101.986, 271.886, 101.574,
                271.072, 101.574);
        ((GeneralPath) shape).lineTo(268.891, 101.574);
        ((GeneralPath) shape).lineTo(268.891, 104.269);
        ((GeneralPath) shape).lineTo(267.375, 104.269);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_50;
        g.setTransform(defaultTransform__0_50);
        g.setClip(clip__0_50);
        float alpha__0_51 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_51 = g.getClip();
        AffineTransform defaultTransform__0_51 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_51 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(276.457, 97.546);
        ((GeneralPath) shape).lineTo(276.457, 99.609);
        ((GeneralPath) shape).lineTo(280.199, 99.609);
        ((GeneralPath) shape).lineTo(280.199, 100.729);
        ((GeneralPath) shape).lineTo(276.457, 100.729);
        ((GeneralPath) shape).lineTo(276.457, 102.99);
        ((GeneralPath) shape).lineTo(280.439, 102.99);
        ((GeneralPath) shape).lineTo(280.439, 104.268);
        ((GeneralPath) shape).lineTo(274.941, 104.268);
        ((GeneralPath) shape).lineTo(274.941, 96.269);
        ((GeneralPath) shape).lineTo(280.404, 96.269);
        ((GeneralPath) shape).lineTo(280.404, 97.546);
        ((GeneralPath) shape).lineTo(276.457, 97.546);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_51;
        g.setTransform(defaultTransform__0_51);
        g.setClip(clip__0_51);
        float alpha__0_52 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_52 = g.getClip();
        AffineTransform defaultTransform__0_52 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_52 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(292.791, 96.269);
        ((GeneralPath) shape).lineTo(290.65598, 104.268);
        ((GeneralPath) shape).lineTo(288.416, 104.268);
        ((GeneralPath) shape).lineTo(287.30698, 100.166);
        ((GeneralPath) shape).curveTo(287.205, 99.796, 287.08597, 99.299,
                286.94998, 98.678);
        ((GeneralPath) shape).lineTo(286.839, 98.186005);
        ((GeneralPath) shape).lineTo(286.787, 98.186005);
        ((GeneralPath) shape).lineTo(286.66998, 98.684006);
        ((GeneralPath) shape).lineTo(286.56097, 99.176);
        ((GeneralPath) shape).curveTo(286.47897, 99.507, 286.39096, 99.838005,
                286.29596, 100.172005);
        ((GeneralPath) shape).lineTo(285.15497, 104.268005);
        ((GeneralPath) shape).lineTo(282.93896, 104.268005);
        ((GeneralPath) shape).lineTo(280.86896, 96.269005);
        ((GeneralPath) shape).lineTo(282.41296, 96.269005);
        ((GeneralPath) shape).lineTo(283.56497, 100.66);
        ((GeneralPath) shape).curveTo(283.63297, 100.939, 283.72296, 101.328,
                283.82797, 101.824005);
        ((GeneralPath) shape).lineTo(283.95096, 102.412);
        ((GeneralPath) shape).lineTo(284.06897, 102.998);
        ((GeneralPath) shape).lineTo(284.12097, 102.998);
        ((GeneralPath) shape).curveTo(284.18396, 102.738, 284.22897,
                102.547005, 284.26398, 102.412);
        ((GeneralPath) shape).lineTo(284.404, 101.832);
        ((GeneralPath) shape).curveTo(284.477, 101.525, 284.57898, 101.141,
                284.714, 100.665);
        ((GeneralPath) shape).lineTo(285.943, 96.269);
        ((GeneralPath) shape).lineTo(287.719, 96.269);
        ((GeneralPath) shape).lineTo(288.917, 100.66499);
        ((GeneralPath) shape).curveTo(289.01898, 101.03999, 289.116,
                101.426994, 289.215, 101.83199);
        ((GeneralPath) shape).lineTo(289.352, 102.411995);
        ((GeneralPath) shape).lineTo(289.492, 102.99799);
        ((GeneralPath) shape).lineTo(289.539, 102.99799);
        ((GeneralPath) shape).lineTo(289.668, 102.411995);
        ((GeneralPath) shape).lineTo(289.791, 101.824);
        ((GeneralPath) shape).curveTo(289.892, 101.352, 289.98297, 100.965,
                290.06497, 100.652);
        ((GeneralPath) shape).lineTo(291.24597, 96.269);
        ((GeneralPath) shape).lineTo(292.791, 96.269);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_52;
        g.setTransform(defaultTransform__0_52);
        g.setClip(clip__0_52);
        float alpha__0_53 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_53 = g.getClip();
        AffineTransform defaultTransform__0_53 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_53 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(298.008, 102.99);
        ((GeneralPath) shape).lineTo(300.164, 102.99);
        ((GeneralPath) shape).curveTo(300.891, 102.99, 301.357, 102.823,
                301.571, 102.489);
        ((GeneralPath) shape).curveTo(301.781, 102.156, 301.889, 101.422,
                301.889, 100.277);
        ((GeneralPath) shape).curveTo(301.889, 99.1, 301.79602, 98.35, 301.603,
                98.027);
        ((GeneralPath) shape).curveTo(301.413, 97.71, 300.966, 97.548004,
                300.258, 97.548004);
        ((GeneralPath) shape).lineTo(298.007, 97.548004);
        ((GeneralPath) shape).lineTo(298.007, 102.99);
        ((GeneralPath) shape).moveTo(296.491, 104.268);
        ((GeneralPath) shape).lineTo(296.491, 96.269);
        ((GeneralPath) shape).lineTo(300.415, 96.269);
        ((GeneralPath) shape).curveTo(301.52902, 96.269, 302.311, 96.51199,
                302.759, 97.002);
        ((GeneralPath) shape).curveTo(303.202, 97.486, 303.428, 98.344,
                303.428, 99.566);
        ((GeneralPath) shape).curveTo(303.428, 101.561005, 303.24802, 102.844,
                302.89, 103.414);
        ((GeneralPath) shape).curveTo(302.53302, 103.981, 301.72302, 104.267,
                300.463, 104.267);
        ((GeneralPath) shape).lineTo(296.491, 104.268);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_53;
        g.setTransform(defaultTransform__0_53);
        g.setClip(clip__0_53);
        float alpha__0_54 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_54 = g.getClip();
        AffineTransform defaultTransform__0_54 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_54 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(309.01, 101.613);
        ((GeneralPath) shape).lineTo(307.629, 97.447);
        ((GeneralPath) shape).lineTo(306.273, 101.613);
        ((GeneralPath) shape).lineTo(309.01, 101.613);
        ((GeneralPath) shape).moveTo(309.358, 102.732);
        ((GeneralPath) shape).lineTo(305.913, 102.732);
        ((GeneralPath) shape).lineTo(305.416, 104.267006);
        ((GeneralPath) shape).lineTo(303.814, 104.267006);
        ((GeneralPath) shape).lineTo(306.469, 96.268005);
        ((GeneralPath) shape).lineTo(308.741, 96.268005);
        ((GeneralPath) shape).lineTo(311.437, 104.267006);
        ((GeneralPath) shape).lineTo(309.864, 104.267006);
        ((GeneralPath) shape).lineTo(309.358, 102.732);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_54;
        g.setTransform(defaultTransform__0_54);
        g.setClip(clip__0_54);
        float alpha__0_55 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_55 = g.getClip();
        AffineTransform defaultTransform__0_55 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_55 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(315.314, 97.628);
        ((GeneralPath) shape).lineTo(315.314, 104.268);
        ((GeneralPath) shape).lineTo(313.802, 104.268);
        ((GeneralPath) shape).lineTo(313.802, 97.628);
        ((GeneralPath) shape).lineTo(311.495, 97.628);
        ((GeneralPath) shape).lineTo(311.495, 96.269);
        ((GeneralPath) shape).lineTo(317.701, 96.269);
        ((GeneralPath) shape).lineTo(317.701, 97.628);
        ((GeneralPath) shape).lineTo(315.314, 97.628);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_55;
        g.setTransform(defaultTransform__0_55);
        g.setClip(clip__0_55);
        float alpha__0_56 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_56 = g.getClip();
        AffineTransform defaultTransform__0_56 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_56 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(322.949, 101.613);
        ((GeneralPath) shape).lineTo(321.568, 97.447);
        ((GeneralPath) shape).lineTo(320.214, 101.613);
        ((GeneralPath) shape).lineTo(322.949, 101.613);
        ((GeneralPath) shape).moveTo(323.302, 102.732);
        ((GeneralPath) shape).lineTo(319.858, 102.732);
        ((GeneralPath) shape).lineTo(319.361, 104.267006);
        ((GeneralPath) shape).lineTo(317.758, 104.267006);
        ((GeneralPath) shape).lineTo(320.411, 96.268005);
        ((GeneralPath) shape).lineTo(322.686, 96.268005);
        ((GeneralPath) shape).lineTo(325.381, 104.267006);
        ((GeneralPath) shape).lineTo(323.808, 104.267006);
        ((GeneralPath) shape).lineTo(323.302, 102.732);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_56;
        g.setTransform(defaultTransform__0_56);
        g.setClip(clip__0_56);
        float alpha__0_57 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_57 = g.getClip();
        AffineTransform defaultTransform__0_57 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_57 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(385.414, 185.055);
        ((GeneralPath) shape).lineTo(392.498, 192.142);
        ((GeneralPath) shape).lineTo(392.498, 267.259);
        ((GeneralPath) shape).lineTo(385.414, 274.347);
        ((GeneralPath) shape).lineTo(310.297, 274.347);
        ((GeneralPath) shape).lineTo(306.044, 267.259);
        ((GeneralPath) shape).lineTo(250.767, 267.259);
        ((GeneralPath) shape).lineTo(250.767, 185.055);
        ((GeneralPath) shape).lineTo(256.437, 176.55);
        ((GeneralPath) shape).lineTo(362.734, 176.55);
        ((GeneralPath) shape).lineTo(368.404, 185.055);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_57;
        g.setTransform(defaultTransform__0_57);
        g.setClip(clip__0_57);
        float alpha__0_58 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_58 = g.getClip();
        AffineTransform defaultTransform__0_58 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_58 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        stroke = new BasicStroke(2.0f, 0, 0, 10.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(385.412, 185.055);
        ((GeneralPath) shape).lineTo(392.498, 192.142);
        ((GeneralPath) shape).lineTo(392.498, 267.259);
        ((GeneralPath) shape).lineTo(385.412, 274.347);
        ((GeneralPath) shape).lineTo(310.297, 274.347);
        ((GeneralPath) shape).lineTo(306.044, 267.259);
        ((GeneralPath) shape).lineTo(250.767, 267.259);
        ((GeneralPath) shape).lineTo(250.767, 185.055);
        ((GeneralPath) shape).lineTo(256.435, 176.55);
        ((GeneralPath) shape).lineTo(362.734, 176.55);
        ((GeneralPath) shape).lineTo(368.404, 185.055);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_58;
        g.setTransform(defaultTransform__0_58);
        g.setClip(clip__0_58);
        float alpha__0_59 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_59 = g.getClip();
        AffineTransform defaultTransform__0_59 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_59 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(361.332, 194.976);
        ((GeneralPath) shape).lineTo(257.569, 194.976);
        ((GeneralPath) shape).lineTo(252.184, 186.896);
        ((GeneralPath) shape).lineTo(257.556, 178.817);
        ((GeneralPath) shape).lineTo(361.317, 178.817);
        ((GeneralPath) shape).lineTo(366.704, 186.896);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_59;
        g.setTransform(defaultTransform__0_59);
        g.setClip(clip__0_59);
        float alpha__0_60 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_60 = g.getClip();
        AffineTransform defaultTransform__0_60 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_60 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        stroke = new BasicStroke(1.0f, 0, 1, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(361.332, 194.976);
        ((GeneralPath) shape).lineTo(257.569, 194.976);
        ((GeneralPath) shape).lineTo(252.184, 186.896);
        ((GeneralPath) shape).lineTo(257.556, 178.817);
        ((GeneralPath) shape).lineTo(361.317, 178.817);
        ((GeneralPath) shape).lineTo(366.705, 186.896);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_60;
        g.setTransform(defaultTransform__0_60);
        g.setClip(clip__0_60);
        float alpha__0_61 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_61 = g.getClip();
        AffineTransform defaultTransform__0_61 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_61 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(264.903, 187.872);
        ((GeneralPath) shape).lineTo(266.424, 187.872);
        ((GeneralPath) shape).lineTo(266.424, 188.14499);
        ((GeneralPath) shape).curveTo(266.424, 189.258, 266.224, 189.97598,
                265.81802, 190.30399);
        ((GeneralPath) shape).curveTo(265.41403, 190.62898, 264.51602,
                190.79298, 263.127, 190.79298);
        ((GeneralPath) shape).curveTo(261.556, 190.79298, 260.584, 190.53499,
                260.22302, 190.01999);
        ((GeneralPath) shape).curveTo(259.86002, 189.50499, 259.67703,
                188.12698, 259.67703, 185.883);
        ((GeneralPath) shape).curveTo(259.67703, 184.565, 259.92303, 183.69499,
                260.41403, 183.27899);
        ((GeneralPath) shape).curveTo(260.90503, 182.86198, 261.93304, 182.655,
                263.49603, 182.655);
        ((GeneralPath) shape).curveTo(264.63403, 182.655, 265.39404, 182.826,
                265.77704, 183.169);
        ((GeneralPath) shape).curveTo(266.15704, 183.509, 266.35004, 184.18901,
                266.35004, 185.205);
        ((GeneralPath) shape).lineTo(266.35605, 185.38701);
        ((GeneralPath) shape).lineTo(264.83505, 185.38701);
        ((GeneralPath) shape).lineTo(264.83505, 185.18102);
        ((GeneralPath) shape).curveTo(264.83505, 184.66002, 264.73807,
                184.32301, 264.54004, 184.17601);
        ((GeneralPath) shape).curveTo(264.34402, 184.03001, 263.89404,
                183.95601, 263.19205, 183.95601);
        ((GeneralPath) shape).curveTo(262.25406, 183.95601, 261.68906,
                184.06801, 261.49905, 184.302);
        ((GeneralPath) shape).curveTo(261.31204, 184.531, 261.21506, 185.216,
                261.21506, 186.353);
        ((GeneralPath) shape).curveTo(261.21506, 187.883, 261.30005, 188.791,
                261.47006, 189.07199);
        ((GeneralPath) shape).curveTo(261.64008, 189.353, 262.18707, 189.49599,
                263.11606, 189.49599);
        ((GeneralPath) shape).curveTo(263.86707, 189.49599, 264.35507,
                189.41998, 264.58105, 189.262);
        ((GeneralPath) shape).curveTo(264.80304, 189.107, 264.91705, 188.76399,
                264.91705, 188.23099);
        ((GeneralPath) shape).lineTo(264.903, 187.872);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_61;
        g.setTransform(defaultTransform__0_61);
        g.setClip(clip__0_61);
        float alpha__0_62 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_62 = g.getClip();
        AffineTransform defaultTransform__0_62 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_62 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(268.89, 186.752);
        ((GeneralPath) shape).lineTo(271.065, 186.752);
        ((GeneralPath) shape).curveTo(271.583, 186.752, 271.928, 186.661,
                272.098, 186.474);
        ((GeneralPath) shape).curveTo(272.267, 186.289, 272.352, 185.918,
                272.352, 185.358);
        ((GeneralPath) shape).curveTo(272.352, 184.792, 272.279, 184.42,
                272.13098, 184.254);
        ((GeneralPath) shape).curveTo(271.985, 184.09, 271.666, 184.004,
                271.16898, 184.004);
        ((GeneralPath) shape).lineTo(268.89096, 184.004);
        ((GeneralPath) shape).lineTo(268.89, 186.752);
        ((GeneralPath) shape).moveTo(267.375, 190.725);
        ((GeneralPath) shape).lineTo(267.375, 182.727);
        ((GeneralPath) shape).lineTo(271.313, 182.727);
        ((GeneralPath) shape).curveTo(272.28998, 182.727, 272.96298, 182.897,
                273.33398, 183.237);
        ((GeneralPath) shape).curveTo(273.70297, 183.579, 273.892, 184.192,
                273.892, 185.085);
        ((GeneralPath) shape).curveTo(273.892, 185.89401, 273.799, 186.442,
                273.61398, 186.74101);
        ((GeneralPath) shape).curveTo(273.43, 187.03902, 273.05, 187.24402,
                272.477, 187.35602);
        ((GeneralPath) shape).lineTo(272.477, 187.41101);
        ((GeneralPath) shape).curveTo(273.36, 187.464, 273.805, 187.98102,
                273.805, 188.964);
        ((GeneralPath) shape).lineTo(273.805, 190.72801);
        ((GeneralPath) shape).lineTo(272.288, 190.72801);
        ((GeneralPath) shape).lineTo(272.288, 189.27002);
        ((GeneralPath) shape).curveTo(272.288, 188.44601, 271.886, 188.03302,
                271.072, 188.03302);
        ((GeneralPath) shape).lineTo(268.891, 188.03302);
        ((GeneralPath) shape).lineTo(268.891, 190.72803);
        ((GeneralPath) shape).lineTo(267.375, 190.725);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_62;
        g.setTransform(defaultTransform__0_62);
        g.setClip(clip__0_62);
        float alpha__0_63 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_63 = g.getClip();
        AffineTransform defaultTransform__0_63 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_63 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new Rectangle2D.Double(275.1000061035156, 182.7270050048828,
                1.5149999856948853, 7.998000144958496);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_63;
        g.setTransform(defaultTransform__0_63);
        g.setClip(clip__0_63);
        float alpha__0_64 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_64 = g.getClip();
        AffineTransform defaultTransform__0_64 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_64 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(281.215, 184.086);
        ((GeneralPath) shape).lineTo(281.215, 190.725);
        ((GeneralPath) shape).lineTo(279.701, 190.725);
        ((GeneralPath) shape).lineTo(279.701, 184.086);
        ((GeneralPath) shape).lineTo(277.396, 184.086);
        ((GeneralPath) shape).lineTo(277.396, 182.727);
        ((GeneralPath) shape).lineTo(283.602, 182.727);
        ((GeneralPath) shape).lineTo(283.602, 184.086);
        ((GeneralPath) shape).lineTo(281.215, 184.086);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_64;
        g.setTransform(defaultTransform__0_64);
        g.setClip(clip__0_64);
        float alpha__0_65 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_65 = g.getClip();
        AffineTransform defaultTransform__0_65 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_65 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new Rectangle2D.Double(284.3699951171875, 182.7270050048828,
                1.5169999599456787, 7.998000144958496);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_65;
        g.setTransform(defaultTransform__0_65);
        g.setClip(clip__0_65);
        float alpha__0_66 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_66 = g.getClip();
        AffineTransform defaultTransform__0_66 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_66 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(292.235, 187.872);
        ((GeneralPath) shape).lineTo(293.75598, 187.872);
        ((GeneralPath) shape).lineTo(293.75598, 188.14499);
        ((GeneralPath) shape).curveTo(293.75598, 189.258, 293.55597, 189.97598,
                293.15, 190.30399);
        ((GeneralPath) shape).curveTo(292.746, 190.62898, 291.849, 190.79298,
                290.46, 190.79298);
        ((GeneralPath) shape).curveTo(288.888, 190.79298, 287.916, 190.53499,
                287.555, 190.01999);
        ((GeneralPath) shape).curveTo(287.193, 189.50499, 287.00998, 188.12698,
                287.00998, 185.883);
        ((GeneralPath) shape).curveTo(287.00998, 184.565, 287.25598, 183.69499,
                287.74698, 183.27899);
        ((GeneralPath) shape).curveTo(288.23798, 182.86198, 289.26697, 182.655,
                290.82898, 182.655);
        ((GeneralPath) shape).curveTo(291.96597, 182.655, 292.72598, 182.826,
                293.11, 183.169);
        ((GeneralPath) shape).curveTo(293.49, 183.509, 293.68298, 184.18901,
                293.68298, 185.205);
        ((GeneralPath) shape).lineTo(293.689, 185.38701);
        ((GeneralPath) shape).lineTo(292.168, 185.38701);
        ((GeneralPath) shape).lineTo(292.168, 185.18102);
        ((GeneralPath) shape).curveTo(292.168, 184.66002, 292.072, 184.32301,
                291.873, 184.17601);
        ((GeneralPath) shape).curveTo(291.67697, 184.03001, 291.227, 183.95601,
                290.525, 183.95601);
        ((GeneralPath) shape).curveTo(289.587, 183.95601, 289.022, 184.06801,
                288.832, 184.302);
        ((GeneralPath) shape).curveTo(288.645, 184.531, 288.549, 185.216,
                288.549, 186.353);
        ((GeneralPath) shape).curveTo(288.549, 187.883, 288.63303, 188.791,
                288.80402, 189.07199);
        ((GeneralPath) shape).curveTo(288.97403, 189.353, 289.52103, 189.49599,
                290.45, 189.49599);
        ((GeneralPath) shape).curveTo(291.20102, 189.49599, 291.68903,
                189.41998, 291.915, 189.262);
        ((GeneralPath) shape).curveTo(292.137, 189.107, 292.251, 188.76399,
                292.251, 188.23099);
        ((GeneralPath) shape).lineTo(292.235, 187.872);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_66;
        g.setTransform(defaultTransform__0_66);
        g.setClip(clip__0_66);
        float alpha__0_67 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_67 = g.getClip();
        AffineTransform defaultTransform__0_67 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_67 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(299.187, 188.071);
        ((GeneralPath) shape).lineTo(297.807, 183.90399);
        ((GeneralPath) shape).lineTo(296.45, 188.07098);
        ((GeneralPath) shape).lineTo(299.187, 188.07098);
        ((GeneralPath) shape).moveTo(299.537, 189.19);
        ((GeneralPath) shape).lineTo(296.09198, 189.19);
        ((GeneralPath) shape).lineTo(295.597, 190.725);
        ((GeneralPath) shape).lineTo(293.99298, 190.725);
        ((GeneralPath) shape).lineTo(296.64798, 182.727);
        ((GeneralPath) shape).lineTo(298.92297, 182.727);
        ((GeneralPath) shape).lineTo(301.61697, 190.725);
        ((GeneralPath) shape).lineTo(300.04596, 190.725);
        ((GeneralPath) shape).lineTo(299.537, 189.19);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_67;
        g.setTransform(defaultTransform__0_67);
        g.setClip(clip__0_67);
        float alpha__0_68 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_68 = g.getClip();
        AffineTransform defaultTransform__0_68 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_68 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(303.827, 182.727);
        ((GeneralPath) shape).lineTo(303.827, 189.365);
        ((GeneralPath) shape).lineTo(307.569, 189.365);
        ((GeneralPath) shape).lineTo(307.569, 190.725);
        ((GeneralPath) shape).lineTo(302.312, 190.725);
        ((GeneralPath) shape).lineTo(302.312, 182.727);
        ((GeneralPath) shape).lineTo(303.827, 182.727);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_68;
        g.setTransform(defaultTransform__0_68);
        g.setClip(clip__0_68);
        float alpha__0_69 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_69 = g.getClip();
        AffineTransform defaultTransform__0_69 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_69 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(312.847, 189.448);
        ((GeneralPath) shape).lineTo(315.00497, 189.448);
        ((GeneralPath) shape).curveTo(315.731, 189.448, 316.19598, 189.28099,
                316.40897, 188.94699);
        ((GeneralPath) shape).curveTo(316.61996, 188.614, 316.72696, 187.87999,
                316.72696, 186.73499);
        ((GeneralPath) shape).curveTo(316.72696, 185.55699, 316.63397,
                184.80699, 316.44095, 184.48499);
        ((GeneralPath) shape).curveTo(316.25296, 184.16798, 315.80396,
                184.00598, 315.09595, 184.00598);
        ((GeneralPath) shape).lineTo(312.84393, 184.00598);
        ((GeneralPath) shape).lineTo(312.847, 189.448);
        ((GeneralPath) shape).moveTo(311.332, 190.725);
        ((GeneralPath) shape).lineTo(311.332, 182.727);
        ((GeneralPath) shape).lineTo(315.258, 182.727);
        ((GeneralPath) shape).curveTo(316.37, 182.727, 317.154, 182.97,
                317.60098, 183.459);
        ((GeneralPath) shape).curveTo(318.04498, 183.944, 318.271, 184.801,
                318.271, 186.023);
        ((GeneralPath) shape).curveTo(318.271, 188.01799, 318.092, 189.302,
                317.733, 189.872);
        ((GeneralPath) shape).curveTo(317.378, 190.439, 316.566, 190.72499,
                315.306, 190.72499);
        ((GeneralPath) shape).lineTo(311.332, 190.72499);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_69;
        g.setTransform(defaultTransform__0_69);
        g.setClip(clip__0_69);
        float alpha__0_70 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_70 = g.getClip();
        AffineTransform defaultTransform__0_70 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_70 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(323.849, 188.071);
        ((GeneralPath) shape).lineTo(322.469, 183.90399);
        ((GeneralPath) shape).lineTo(321.112, 188.07098);
        ((GeneralPath) shape).lineTo(323.849, 188.07098);
        ((GeneralPath) shape).moveTo(324.199, 189.19);
        ((GeneralPath) shape).lineTo(320.75702, 189.19);
        ((GeneralPath) shape).lineTo(320.26, 190.725);
        ((GeneralPath) shape).lineTo(318.656, 190.725);
        ((GeneralPath) shape).lineTo(321.311, 182.727);
        ((GeneralPath) shape).lineTo(323.586, 182.727);
        ((GeneralPath) shape).lineTo(326.279, 190.725);
        ((GeneralPath) shape).lineTo(324.70798, 190.725);
        ((GeneralPath) shape).lineTo(324.199, 189.19);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_70;
        g.setTransform(defaultTransform__0_70);
        g.setClip(clip__0_70);
        float alpha__0_71 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_71 = g.getClip();
        AffineTransform defaultTransform__0_71 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_71 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(336.309, 182.727);
        ((GeneralPath) shape).lineTo(336.309, 190.725);
        ((GeneralPath) shape).lineTo(334.79398, 190.725);
        ((GeneralPath) shape).lineTo(334.79398, 186.36801);
        ((GeneralPath) shape).curveTo(334.79398, 186.02, 334.80197, 185.62701,
                334.81998, 185.182);
        ((GeneralPath) shape).lineTo(334.84998, 184.584);
        ((GeneralPath) shape).lineTo(334.878, 183.993);
        ((GeneralPath) shape).lineTo(334.83, 183.993);
        ((GeneralPath) shape).lineTo(334.64697, 184.549);
        ((GeneralPath) shape).lineTo(334.472, 185.106);
        ((GeneralPath) shape).curveTo(334.309, 185.604, 334.18298, 185.974,
                334.09198, 186.213);
        ((GeneralPath) shape).lineTo(332.33698, 190.72499);
        ((GeneralPath) shape).lineTo(330.95596, 190.72499);
        ((GeneralPath) shape).lineTo(329.18497, 186.24799);
        ((GeneralPath) shape).curveTo(329.08798, 186.00198, 328.95996,
                185.63298, 328.79898, 185.14099);
        ((GeneralPath) shape).lineTo(328.615, 184.58398);
        ((GeneralPath) shape).lineTo(328.434, 184.03099);
        ((GeneralPath) shape).lineTo(328.387, 184.03099);
        ((GeneralPath) shape).lineTo(328.416, 184.61198);
        ((GeneralPath) shape).lineTo(328.443, 185.19798);
        ((GeneralPath) shape).curveTo(328.466, 185.64899, 328.478, 186.03998,
                328.478, 186.36398);
        ((GeneralPath) shape).lineTo(328.478, 190.72298);
        ((GeneralPath) shape).lineTo(326.96298, 190.72298);
        ((GeneralPath) shape).lineTo(326.96298, 182.72498);
        ((GeneralPath) shape).lineTo(329.43097, 182.72498);
        ((GeneralPath) shape).lineTo(330.85797, 186.42897);
        ((GeneralPath) shape).curveTo(330.95697, 186.68597, 331.08597,
                187.05498, 331.24396, 187.53596);
        ((GeneralPath) shape).lineTo(331.42197, 188.09297);
        ((GeneralPath) shape).lineTo(331.60397, 188.64297);
        ((GeneralPath) shape).lineTo(331.65698, 188.64297);
        ((GeneralPath) shape).lineTo(331.826, 188.09297);
        ((GeneralPath) shape).lineTo(332.004, 187.54198);
        ((GeneralPath) shape).curveTo(332.148, 187.07898, 332.274, 186.70998,
                332.378, 186.43797);
        ((GeneralPath) shape).lineTo(333.779, 182.72298);
        ((GeneralPath) shape).lineTo(336.306, 182.72298);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_71;
        g.setTransform(defaultTransform__0_71);
        g.setClip(clip__0_71);
        float alpha__0_72 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_72 = g.getClip();
        AffineTransform defaultTransform__0_72 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_72 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(342.186, 188.071);
        ((GeneralPath) shape).lineTo(340.806, 183.90399);
        ((GeneralPath) shape).lineTo(339.449, 188.07098);
        ((GeneralPath) shape).lineTo(342.186, 188.07098);
        ((GeneralPath) shape).moveTo(342.536, 189.19);
        ((GeneralPath) shape).lineTo(339.09402, 189.19);
        ((GeneralPath) shape).lineTo(338.59702, 190.725);
        ((GeneralPath) shape).lineTo(336.993, 190.725);
        ((GeneralPath) shape).lineTo(339.648, 182.727);
        ((GeneralPath) shape).lineTo(341.923, 182.727);
        ((GeneralPath) shape).lineTo(344.616, 190.725);
        ((GeneralPath) shape).lineTo(343.046, 190.725);
        ((GeneralPath) shape).lineTo(342.536, 189.19);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_72;
        g.setTransform(defaultTransform__0_72);
        g.setClip(clip__0_72);
        float alpha__0_73 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_73 = g.getClip();
        AffineTransform defaultTransform__0_73 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_73 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(348.452, 186.471);
        ((GeneralPath) shape).lineTo(351.969, 186.471);
        ((GeneralPath) shape).lineTo(351.99, 188.05899);
        ((GeneralPath) shape).curveTo(351.99, 189.213, 351.77298, 189.96098,
                351.34, 190.29399);
        ((GeneralPath) shape).curveTo(350.905, 190.62799, 349.93698, 190.795,
                348.431, 190.795);
        ((GeneralPath) shape).curveTo(347.05, 190.795, 346.138, 190.56999,
                345.688, 190.127);
        ((GeneralPath) shape).curveTo(345.241, 189.68199, 345.01498, 188.773,
                345.01498, 187.401);
        ((GeneralPath) shape).curveTo(345.01498, 185.651, 345.103, 184.547,
                345.28497, 184.086);
        ((GeneralPath) shape).curveTo(345.50897, 183.523, 345.84598, 183.146,
                346.29898, 182.95);
        ((GeneralPath) shape).curveTo(346.74698, 182.756, 347.51797, 182.655,
                348.60297, 182.655);
        ((GeneralPath) shape).curveTo(350.02097, 182.655, 350.93698, 182.804,
                351.35196, 183.109);
        ((GeneralPath) shape).curveTo(351.76395, 183.413, 351.97195, 184.084,
                351.97195, 185.124);
        ((GeneralPath) shape).lineTo(350.43994, 185.124);
        ((GeneralPath) shape).curveTo(350.41394, 184.603, 350.29895, 184.27599,
                350.09793, 184.14699);
        ((GeneralPath) shape).curveTo(349.89893, 184.02098, 349.39294, 183.958,
                348.58392, 183.958);
        ((GeneralPath) shape).curveTo(347.70593, 183.958, 347.14993, 184.06499,
                346.9139, 184.28699);
        ((GeneralPath) shape).curveTo(346.68192, 184.50299, 346.5599,
                185.01698, 346.5599, 185.82799);
        ((GeneralPath) shape).lineTo(346.55292, 186.63098);
        ((GeneralPath) shape).lineTo(346.5649, 187.65398);
        ((GeneralPath) shape).curveTo(346.5649, 188.44598, 346.68192,
                188.95398, 346.9169, 189.17097);
        ((GeneralPath) shape).curveTo(347.1499, 189.38797, 347.6909, 189.49597,
                348.5419, 189.49597);
        ((GeneralPath) shape).curveTo(349.3669, 189.49597, 349.8959, 189.40297,
                350.1179, 189.22098);
        ((GeneralPath) shape).curveTo(350.3429, 189.03897, 350.4549, 188.60498,
                350.4549, 187.91998);
        ((GeneralPath) shape).lineTo(350.4609, 187.59198);
        ((GeneralPath) shape).lineTo(348.4549, 187.59198);
        ((GeneralPath) shape).lineTo(348.452, 186.471);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_73;
        g.setTransform(defaultTransform__0_73);
        g.setClip(clip__0_73);
        float alpha__0_74 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_74 = g.getClip();
        AffineTransform defaultTransform__0_74 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_74 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(354.405, 184.004);
        ((GeneralPath) shape).lineTo(354.405, 186.067);
        ((GeneralPath) shape).lineTo(358.148, 186.067);
        ((GeneralPath) shape).lineTo(358.148, 187.186);
        ((GeneralPath) shape).lineTo(354.405, 187.186);
        ((GeneralPath) shape).lineTo(354.405, 189.448);
        ((GeneralPath) shape).lineTo(358.388, 189.448);
        ((GeneralPath) shape).lineTo(358.388, 190.725);
        ((GeneralPath) shape).lineTo(352.891, 190.725);
        ((GeneralPath) shape).lineTo(352.891, 182.727);
        ((GeneralPath) shape).lineTo(358.354, 182.727);
        ((GeneralPath) shape).lineTo(358.354, 184.004);
        ((GeneralPath) shape).lineTo(354.405, 184.004);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_74;
        g.setTransform(defaultTransform__0_74);
        g.setClip(clip__0_74);
        float alpha__0_75 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_75 = g.getClip();
        AffineTransform defaultTransform__0_75 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_75 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(37.151, 112.908);
        ((GeneralPath) shape).lineTo(37.151, 118.44);
        ((GeneralPath) shape).lineTo(35.89, 118.44);
        ((GeneralPath) shape).lineTo(35.89, 112.908);
        ((GeneralPath) shape).lineTo(33.969, 112.908);
        ((GeneralPath) shape).lineTo(33.969, 111.775);
        ((GeneralPath) shape).lineTo(39.14, 111.775);
        ((GeneralPath) shape).lineTo(39.14, 112.908);
        ((GeneralPath) shape).lineTo(37.151, 112.908);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_75;
        g.setTransform(defaultTransform__0_75);
        g.setClip(clip__0_75);
        float alpha__0_76 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_76 = g.getClip();
        AffineTransform defaultTransform__0_76 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_76 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(43.583, 113.772);
        ((GeneralPath) shape).lineTo(42.36, 118.733);
        ((GeneralPath) shape).curveTo(42.190002, 119.436005, 41.975002,
                119.907005, 41.719, 120.151);
        ((GeneralPath) shape).curveTo(41.464, 120.393, 41.056004, 120.515,
                40.491, 120.515);
        ((GeneralPath) shape).curveTo(40.365, 120.515, 40.233, 120.51, 40.096,
                120.495);
        ((GeneralPath) shape).lineTo(40.096, 119.670006);
        ((GeneralPath) shape).curveTo(40.194, 119.674, 40.274002, 119.68001,
                40.338, 119.68001);
        ((GeneralPath) shape).curveTo(40.813, 119.68001, 41.113003, 119.267006,
                41.24, 118.44001);
        ((GeneralPath) shape).lineTo(40.670002, 118.44001);
        ((GeneralPath) shape).lineTo(39.147003, 113.77201);
        ((GeneralPath) shape).lineTo(40.346004, 113.77201);
        ((GeneralPath) shape).lineTo(40.930004, 115.75101);
        ((GeneralPath) shape).lineTo(41.222004, 116.743004);
        ((GeneralPath) shape).curveTo(41.237003, 116.80601, 41.283005, 116.972,
                41.359005, 117.239006);
        ((GeneralPath) shape).lineTo(41.500004, 117.732);
        ((GeneralPath) shape).lineTo(41.524002, 117.732);
        ((GeneralPath) shape).lineTo(41.626003, 117.239006);
        ((GeneralPath) shape).curveTo(41.680004, 116.98501, 41.711002,
                116.81901, 41.730003, 116.743004);
        ((GeneralPath) shape).lineTo(41.959003, 115.75101);
        ((GeneralPath) shape).lineTo(42.407005, 113.77201);
        ((GeneralPath) shape).lineTo(43.583, 113.77201);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_76;
        g.setTransform(defaultTransform__0_76);
        g.setClip(clip__0_76);
        float alpha__0_77 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_77 = g.getClip();
        AffineTransform defaultTransform__0_77 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_77 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(46.29, 114.598);
        ((GeneralPath) shape).curveTo(45.866, 114.598, 45.588, 114.693, 45.457,
                114.886);
        ((GeneralPath) shape).curveTo(45.328, 115.076004, 45.264, 115.484,
                45.264, 116.107);
        ((GeneralPath) shape).curveTo(45.264, 116.712006, 45.33, 117.113,
                45.474, 117.313);
        ((GeneralPath) shape).curveTo(45.613, 117.511, 45.898, 117.611, 46.327,
                117.611);
        ((GeneralPath) shape).curveTo(46.760998, 117.611, 47.043, 117.518,
                47.175, 117.33);
        ((GeneralPath) shape).curveTo(47.306, 117.142, 47.37, 116.734, 47.37,
                116.112);
        ((GeneralPath) shape).curveTo(47.37, 115.474, 47.305, 115.062,
                47.171997, 114.879);
        ((GeneralPath) shape).curveTo(47.038, 114.69, 46.746, 114.598, 46.29,
                114.598);
        ((GeneralPath) shape).moveTo(44.092, 113.772);
        ((GeneralPath) shape).lineTo(45.229, 113.772);
        ((GeneralPath) shape).lineTo(45.185, 114.465004);
        ((GeneralPath) shape).lineTo(45.207, 114.469);
        ((GeneralPath) shape).curveTo(45.477, 113.967, 45.978, 113.713005,
                46.713, 113.713005);
        ((GeneralPath) shape).curveTo(47.393, 113.713005, 47.859, 113.881004,
                48.114002, 114.218);
        ((GeneralPath) shape).curveTo(48.367, 114.554, 48.497, 115.168, 48.497,
                116.061005);
        ((GeneralPath) shape).curveTo(48.497, 116.994, 48.371002, 117.629005,
                48.117, 117.975006);
        ((GeneralPath) shape).curveTo(47.864002, 118.32001, 47.396, 118.495,
                46.709, 118.495);
        ((GeneralPath) shape).curveTo(45.98, 118.495, 45.501, 118.251, 45.276,
                117.763);
        ((GeneralPath) shape).lineTo(45.255, 117.763);
        ((GeneralPath) shape).lineTo(45.255, 120.444);
        ((GeneralPath) shape).lineTo(44.09, 120.444);
        ((GeneralPath) shape).lineTo(44.092, 113.772);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_77;
        g.setTransform(defaultTransform__0_77);
        g.setClip(clip__0_77);
        float alpha__0_78 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_78 = g.getClip();
        AffineTransform defaultTransform__0_78 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_78 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(52.446, 115.623);
        ((GeneralPath) shape).lineTo(52.440998, 115.435);
        ((GeneralPath) shape).curveTo(52.440998, 115.063995, 52.378,
                114.826996, 52.247997, 114.712);
        ((GeneralPath) shape).curveTo(52.121998, 114.602, 51.842, 114.546,
                51.413, 114.546);
        ((GeneralPath) shape).curveTo(50.997997, 114.546, 50.727997, 114.609,
                50.600998, 114.745995);
        ((GeneralPath) shape).curveTo(50.476997, 114.878, 50.413, 115.171,
                50.413, 115.619995);
        ((GeneralPath) shape).lineTo(52.446, 115.623);
        ((GeneralPath) shape).moveTo(52.436, 116.946);
        ((GeneralPath) shape).lineTo(53.558002, 116.946);
        ((GeneralPath) shape).lineTo(53.558002, 117.128);
        ((GeneralPath) shape).curveTo(53.558002, 118.039, 52.873, 118.494995,
                51.511, 118.494995);
        ((GeneralPath) shape).curveTo(50.583, 118.494995, 49.980003, 118.339,
                49.693, 118.02);
        ((GeneralPath) shape).curveTo(49.408, 117.70699, 49.264, 117.037994,
                49.264, 116.013);
        ((GeneralPath) shape).curveTo(49.264, 115.105, 49.41, 114.495, 49.71,
                114.182);
        ((GeneralPath) shape).curveTo(50.008, 113.868996, 50.593998, 113.713,
                51.46, 113.713);
        ((GeneralPath) shape).curveTo(52.291, 113.713, 52.850998, 113.862,
                53.131, 114.17);
        ((GeneralPath) shape).curveTo(53.414, 114.473, 53.557, 115.069, 53.557,
                115.964);
        ((GeneralPath) shape).lineTo(53.557, 116.306);
        ((GeneralPath) shape).lineTo(50.404, 116.306);
        ((GeneralPath) shape).curveTo(50.399, 116.409996, 50.394, 116.477,
                50.394, 116.512);
        ((GeneralPath) shape).curveTo(50.394, 116.97, 50.462, 117.276,
                50.606003, 117.43);
        ((GeneralPath) shape).curveTo(50.747, 117.581, 51.027004, 117.659,
                51.451004, 117.659);
        ((GeneralPath) shape).curveTo(51.861004, 117.659, 52.126003, 117.613,
                52.250004, 117.521996);
        ((GeneralPath) shape).curveTo(52.373, 117.437, 52.436, 117.244, 52.436,
                116.946);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_78;
        g.setTransform(defaultTransform__0_78);
        g.setClip(clip__0_78);
        float alpha__0_79 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_79 = g.getClip();
        AffineTransform defaultTransform__0_79 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_79 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(55.553, 113.772);
        ((GeneralPath) shape).lineTo(55.553, 114.973);
        ((GeneralPath) shape).lineTo(54.437, 114.973);
        ((GeneralPath) shape).lineTo(54.437, 113.772);
        ((GeneralPath) shape).lineTo(55.553, 113.772);
        ((GeneralPath) shape).moveTo(55.553, 117.239);
        ((GeneralPath) shape).lineTo(55.553, 118.439995);
        ((GeneralPath) shape).lineTo(54.437, 118.439995);
        ((GeneralPath) shape).lineTo(54.437, 117.239);
        ((GeneralPath) shape).lineTo(55.553, 117.239);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_79;
        g.setTransform(defaultTransform__0_79);
        g.setClip(clip__0_79);
        float alpha__0_80 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_80 = g.getClip();
        AffineTransform defaultTransform__0_80 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_80 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(46.504, 127.656);
        ((GeneralPath) shape).lineTo(46.504, 132.98799);
        ((GeneralPath) shape).lineTo(45.494003, 132.98799);
        ((GeneralPath) shape).lineTo(45.494003, 130.08398);
        ((GeneralPath) shape).curveTo(45.494003, 129.85399, 45.500004,
                129.58998, 45.515003, 129.29498);
        ((GeneralPath) shape).lineTo(45.535004, 128.89699);
        ((GeneralPath) shape).lineTo(45.556004, 128.50198);
        ((GeneralPath) shape).lineTo(45.525005, 128.50198);
        ((GeneralPath) shape).lineTo(45.404007, 128.87299);
        ((GeneralPath) shape).lineTo(45.287006, 129.24399);
        ((GeneralPath) shape).curveTo(45.179005, 129.57599, 45.095005,
                129.81999, 45.034008, 129.97998);
        ((GeneralPath) shape).lineTo(43.86501, 132.98798);
        ((GeneralPath) shape).lineTo(42.94501, 132.98798);
        ((GeneralPath) shape).lineTo(41.763012, 130.00598);
        ((GeneralPath) shape).curveTo(41.699013, 129.84198, 41.61301,
                129.59598, 41.504013, 129.26797);
        ((GeneralPath) shape).lineTo(41.383015, 128.89697);
        ((GeneralPath) shape).lineTo(41.264015, 128.52997);
        ((GeneralPath) shape).lineTo(41.233017, 128.52997);
        ((GeneralPath) shape).lineTo(41.253017, 128.91898);
        ((GeneralPath) shape).lineTo(41.274017, 129.30998);
        ((GeneralPath) shape).curveTo(41.289017, 129.61098, 41.295017,
                129.87299, 41.295017, 130.08698);
        ((GeneralPath) shape).lineTo(41.295017, 132.99098);
        ((GeneralPath) shape).lineTo(40.28502, 132.99098);
        ((GeneralPath) shape).lineTo(40.28502, 127.65898);
        ((GeneralPath) shape).lineTo(41.93102, 127.65898);
        ((GeneralPath) shape).lineTo(42.88002, 130.12997);
        ((GeneralPath) shape).curveTo(42.94402, 130.30197, 43.03002, 130.54797,
                43.13902, 130.86597);
        ((GeneralPath) shape).lineTo(43.25402, 131.23697);
        ((GeneralPath) shape).lineTo(43.37502, 131.60397);
        ((GeneralPath) shape).lineTo(43.41002, 131.60397);
        ((GeneralPath) shape).lineTo(43.523018, 131.23697);
        ((GeneralPath) shape).lineTo(43.64002, 130.87196);
        ((GeneralPath) shape).curveTo(43.73602, 130.56096, 43.81902, 130.31496,
                43.89002, 130.13797);
        ((GeneralPath) shape).lineTo(44.82602, 127.66097);
        ((GeneralPath) shape).lineTo(46.50402, 127.66097);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_80;
        g.setTransform(defaultTransform__0_80);
        g.setClip(clip__0_80);
        float alpha__0_81 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_81 = g.getClip();
        AffineTransform defaultTransform__0_81 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_81 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(49.038, 129.919);
        ((GeneralPath) shape).curveTo(48.668, 129.919, 48.432, 129.983, 48.329,
                130.12001);
        ((GeneralPath) shape).curveTo(48.227997, 130.253, 48.177, 130.56502,
                48.177, 131.05801);
        ((GeneralPath) shape).curveTo(48.177, 131.62102, 48.225998, 131.97202,
                48.322998, 132.113);
        ((GeneralPath) shape).curveTo(48.418, 132.25601, 48.657997, 132.326,
                49.044, 132.326);
        ((GeneralPath) shape).curveTo(49.414997, 132.326, 49.649998, 132.252,
                49.747997, 132.101);
        ((GeneralPath) shape).curveTo(49.847996, 131.955, 49.893997, 131.597,
                49.893997, 131.03099);
        ((GeneralPath) shape).curveTo(49.893997, 130.55399, 49.840996,
                130.25198, 49.741997, 130.11899);
        ((GeneralPath) shape).curveTo(49.638, 129.985, 49.404, 129.919, 49.038,
                129.919);
        ((GeneralPath) shape).moveTo(49.046, 129.212);
        ((GeneralPath) shape).curveTo(49.767002, 129.212, 50.236, 129.32701,
                50.461002, 129.558);
        ((GeneralPath) shape).curveTo(50.685, 129.788, 50.796, 130.275, 50.796,
                131.017);
        ((GeneralPath) shape).curveTo(50.796, 131.845, 50.689003, 132.388,
                50.471, 132.646);
        ((GeneralPath) shape).curveTo(50.251, 132.90399, 49.79, 133.03299,
                49.089, 133.03299);
        ((GeneralPath) shape).curveTo(48.329002, 133.03299, 47.836002,
                132.91599, 47.61, 132.676);
        ((GeneralPath) shape).curveTo(47.386, 132.43799, 47.273, 131.90799,
                47.273, 131.094);
        ((GeneralPath) shape).curveTo(47.273, 130.31099, 47.379997, 129.801,
                47.605, 129.567);
        ((GeneralPath) shape).curveTo(47.824, 129.329, 48.305, 129.212, 49.046,
                129.212);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_81;
        g.setTransform(defaultTransform__0_81);
        g.setClip(clip__0_81);
        float alpha__0_82 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_82 = g.getClip();
        AffineTransform defaultTransform__0_82 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_82 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(54.651, 129.255);
        ((GeneralPath) shape).lineTo(53.583, 132.987);
        ((GeneralPath) shape).lineTo(52.187, 132.987);
        ((GeneralPath) shape).lineTo(51.064, 129.255);
        ((GeneralPath) shape).lineTo(52.012, 129.255);
        ((GeneralPath) shape).lineTo(52.501, 130.97801);
        ((GeneralPath) shape).curveTo(52.567, 131.21602, 52.628998, 131.449,
                52.689, 131.67302);
        ((GeneralPath) shape).lineTo(52.779, 132.02103);
        ((GeneralPath) shape).lineTo(52.868, 132.37103);
        ((GeneralPath) shape).lineTo(52.889, 132.37103);
        ((GeneralPath) shape).lineTo(52.971, 132.02103);
        ((GeneralPath) shape).lineTo(53.053, 131.67703);
        ((GeneralPath) shape).curveTo(53.116, 131.41704, 53.172, 131.18503,
                53.228, 130.98203);
        ((GeneralPath) shape).lineTo(53.688, 129.25502);
        ((GeneralPath) shape).lineTo(54.651, 129.25502);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_82;
        g.setTransform(defaultTransform__0_82);
        g.setClip(clip__0_82);
        float alpha__0_83 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_83 = g.getClip();
        AffineTransform defaultTransform__0_83 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_83 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(57.458, 130.735);
        ((GeneralPath) shape).lineTo(57.454, 130.589);
        ((GeneralPath) shape).curveTo(57.454, 130.292, 57.404, 130.101, 57.3,
                130.011);
        ((GeneralPath) shape).curveTo(57.197998, 129.923, 56.976997, 129.878,
                56.632, 129.878);
        ((GeneralPath) shape).curveTo(56.3, 129.878, 56.084, 129.931, 55.985,
                130.03801);
        ((GeneralPath) shape).curveTo(55.884, 130.14201, 55.835, 130.378,
                55.835, 130.73502);
        ((GeneralPath) shape).lineTo(57.458, 130.73502);
        ((GeneralPath) shape).moveTo(57.45, 131.794);
        ((GeneralPath) shape).lineTo(58.347, 131.794);
        ((GeneralPath) shape).lineTo(58.347, 131.93901);
        ((GeneralPath) shape).curveTo(58.347, 132.66801, 57.799, 133.035,
                56.708, 133.035);
        ((GeneralPath) shape).curveTo(55.969, 133.035, 55.483, 132.91, 55.253,
                132.654);
        ((GeneralPath) shape).curveTo(55.024998, 132.40201, 54.91, 131.869,
                54.91, 131.05);
        ((GeneralPath) shape).curveTo(54.91, 130.321, 55.029, 129.835, 55.268,
                129.585);
        ((GeneralPath) shape).curveTo(55.504, 129.335, 55.974003, 129.21,
                56.666, 129.21);
        ((GeneralPath) shape).curveTo(57.331, 129.21, 57.777, 129.33101,
                58.003002, 129.57501);
        ((GeneralPath) shape).curveTo(58.231003, 129.81502, 58.344, 130.296,
                58.344, 131.01102);
        ((GeneralPath) shape).lineTo(58.344, 131.28401);
        ((GeneralPath) shape).lineTo(55.822002, 131.28401);
        ((GeneralPath) shape).curveTo(55.818, 131.36401, 55.814003, 131.421,
                55.814003, 131.44601);
        ((GeneralPath) shape).curveTo(55.814003, 131.81302, 55.871002, 132.057,
                55.983, 132.18001);
        ((GeneralPath) shape).curveTo(56.096, 132.30101, 56.320004, 132.36401,
                56.659, 132.36401);
        ((GeneralPath) shape).curveTo(56.987, 132.36401, 57.2, 132.33101,
                57.299, 132.25902);
        ((GeneralPath) shape).curveTo(57.4, 132.187, 57.45, 132.031, 57.45,
                131.794);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_83;
        g.setTransform(defaultTransform__0_83);
        g.setClip(clip__0_83);
        float alpha__0_84 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_84 = g.getClip();
        AffineTransform defaultTransform__0_84 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_84 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(59.089, 129.255);
        ((GeneralPath) shape).lineTo(59.984, 129.255);
        ((GeneralPath) shape).lineTo(59.961002, 129.82901);
        ((GeneralPath) shape).lineTo(59.982002, 129.83301);
        ((GeneralPath) shape).curveTo(60.163002, 129.419, 60.543003, 129.212,
                61.120003, 129.212);
        ((GeneralPath) shape).curveTo(61.793003, 129.212, 62.167004, 129.44101,
                62.243004, 129.901);
        ((GeneralPath) shape).lineTo(62.259003, 129.901);
        ((GeneralPath) shape).curveTo(62.435, 129.44, 62.819004, 129.212,
                63.409004, 129.212);
        ((GeneralPath) shape).curveTo(64.270004, 129.212, 64.703, 129.64601,
                64.703, 130.517);
        ((GeneralPath) shape).lineTo(64.703, 132.99);
        ((GeneralPath) shape).lineTo(63.81, 132.99);
        ((GeneralPath) shape).lineTo(63.81, 130.71501);
        ((GeneralPath) shape).curveTo(63.81, 130.18802, 63.592003, 129.92201,
                63.159, 129.92201);
        ((GeneralPath) shape).curveTo(62.617, 129.92201, 62.344, 130.21701,
                62.344, 130.809);
        ((GeneralPath) shape).lineTo(62.344, 132.99501);
        ((GeneralPath) shape).lineTo(61.45, 132.99501);
        ((GeneralPath) shape).lineTo(61.45, 130.682);
        ((GeneralPath) shape).curveTo(61.45, 130.373, 61.409, 130.168, 61.327,
                130.07101);
        ((GeneralPath) shape).curveTo(61.245, 129.97401, 61.072, 129.92502,
                60.807, 129.92502);
        ((GeneralPath) shape).curveTo(60.257, 129.92502, 59.983997, 130.22601,
                59.983997, 130.83102);
        ((GeneralPath) shape).lineTo(59.983997, 132.99503);
        ((GeneralPath) shape).lineTo(59.09, 132.99503);
        ((GeneralPath) shape).lineTo(59.089, 129.255);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_84;
        g.setTransform(defaultTransform__0_84);
        g.setClip(clip__0_84);
        float alpha__0_85 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_85 = g.getClip();
        AffineTransform defaultTransform__0_85 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_85 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(68.021, 130.735);
        ((GeneralPath) shape).lineTo(68.017006, 130.589);
        ((GeneralPath) shape).curveTo(68.017006, 130.292, 67.964005, 130.101,
                67.86301, 130.011);
        ((GeneralPath) shape).curveTo(67.76201, 129.923, 67.53801, 129.878,
                67.19501, 129.878);
        ((GeneralPath) shape).curveTo(66.86301, 129.878, 66.64701, 129.931,
                66.546005, 130.03801);
        ((GeneralPath) shape).curveTo(66.44701, 130.14201, 66.398, 130.378,
                66.398, 130.73502);
        ((GeneralPath) shape).lineTo(68.021, 130.73502);
        ((GeneralPath) shape).moveTo(68.013, 131.794);
        ((GeneralPath) shape).lineTo(68.909004, 131.794);
        ((GeneralPath) shape).lineTo(68.909004, 131.93901);
        ((GeneralPath) shape).curveTo(68.909004, 132.66801, 68.36301, 133.035,
                67.271, 133.035);
        ((GeneralPath) shape).curveTo(66.53001, 133.035, 66.045006, 132.91,
                65.817, 132.654);
        ((GeneralPath) shape).curveTo(65.588005, 132.40201, 65.474, 131.869,
                65.474, 131.05);
        ((GeneralPath) shape).curveTo(65.474, 130.321, 65.591995, 129.835,
                65.831, 129.585);
        ((GeneralPath) shape).curveTo(66.069, 129.335, 66.537, 129.21, 67.23,
                129.21);
        ((GeneralPath) shape).curveTo(67.895004, 129.21, 68.340004, 129.33101,
                68.567, 129.57501);
        ((GeneralPath) shape).curveTo(68.793, 129.81502, 68.906, 130.296,
                68.906, 131.01102);
        ((GeneralPath) shape).lineTo(68.906, 131.28401);
        ((GeneralPath) shape).lineTo(66.384995, 131.28401);
        ((GeneralPath) shape).curveTo(66.379, 131.36401, 66.37499, 131.421,
                66.37499, 131.44601);
        ((GeneralPath) shape).curveTo(66.37499, 131.81302, 66.43299, 132.057,
                66.54399, 132.18001);
        ((GeneralPath) shape).curveTo(66.65699, 132.30101, 66.88099, 132.36401,
                67.22199, 132.36401);
        ((GeneralPath) shape).curveTo(67.54799, 132.36401, 67.76199, 132.33101,
                67.86099, 132.25902);
        ((GeneralPath) shape).curveTo(67.962, 132.187, 68.013, 132.031, 68.013,
                131.794);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_85;
        g.setTransform(defaultTransform__0_85);
        g.setClip(clip__0_85);
        float alpha__0_86 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_86 = g.getClip();
        AffineTransform defaultTransform__0_86 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_86 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(69.651, 129.255);
        ((GeneralPath) shape).lineTo(70.536, 129.255);
        ((GeneralPath) shape).lineTo(70.501, 129.884);
        ((GeneralPath) shape).lineTo(70.522, 129.888);
        ((GeneralPath) shape).curveTo(70.69601, 129.439, 71.08501, 129.212,
                71.692, 129.212);
        ((GeneralPath) shape).curveTo(72.573, 129.212, 73.013, 129.62201,
                73.013, 130.444);
        ((GeneralPath) shape).lineTo(73.013, 132.987);
        ((GeneralPath) shape).lineTo(72.118004, 132.987);
        ((GeneralPath) shape).lineTo(72.118004, 130.596);
        ((GeneralPath) shape).lineTo(72.09801, 130.334);
        ((GeneralPath) shape).curveTo(72.05701, 130.057, 71.837006, 129.916,
                71.44301, 129.916);
        ((GeneralPath) shape).curveTo(70.84301, 129.916, 70.54301, 130.201,
                70.54301, 130.77);
        ((GeneralPath) shape).lineTo(70.54301, 132.985);
        ((GeneralPath) shape).lineTo(69.65001, 132.985);
        ((GeneralPath) shape).lineTo(69.65001, 129.255);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_86;
        g.setTransform(defaultTransform__0_86);
        g.setClip(clip__0_86);
        float alpha__0_87 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_87 = g.getClip();
        AffineTransform defaultTransform__0_87 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_87 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(76.289, 129.255);
        ((GeneralPath) shape).lineTo(76.289, 129.935);
        ((GeneralPath) shape).lineTo(74.854004, 129.935);
        ((GeneralPath) shape).lineTo(74.854004, 131.81);
        ((GeneralPath) shape).curveTo(74.854004, 132.15599, 74.985, 132.331,
                75.248, 132.331);
        ((GeneralPath) shape).curveTo(75.537, 132.331, 75.683, 132.122, 75.683,
                131.702);
        ((GeneralPath) shape).lineTo(75.683, 131.554);
        ((GeneralPath) shape).lineTo(76.443, 131.554);
        ((GeneralPath) shape).lineTo(76.443, 131.742);
        ((GeneralPath) shape).curveTo(76.443, 131.914, 76.439, 132.058, 76.425,
                132.181);
        ((GeneralPath) shape).curveTo(76.37701, 132.751, 75.953, 133.036,
                75.154, 133.036);
        ((GeneralPath) shape).curveTo(74.359, 133.036, 73.961, 132.67099,
                73.961, 131.93599);
        ((GeneralPath) shape).lineTo(73.961, 129.93199);
        ((GeneralPath) shape).lineTo(73.479996, 129.93199);
        ((GeneralPath) shape).lineTo(73.479996, 129.252);
        ((GeneralPath) shape).lineTo(73.961, 129.252);
        ((GeneralPath) shape).lineTo(73.961, 128.416);
        ((GeneralPath) shape).lineTo(74.855995, 128.416);
        ((GeneralPath) shape).lineTo(74.855995, 129.252);
        ((GeneralPath) shape).lineTo(76.289, 129.255);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_87;
        g.setTransform(defaultTransform__0_87);
        g.setClip(clip__0_87);
        float alpha__0_88 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_88 = g.getClip();
        AffineTransform defaultTransform__0_88 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_88 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(79.966, 130.415);
        ((GeneralPath) shape).lineTo(81.116005, 130.415);
        ((GeneralPath) shape).curveTo(81.57201, 130.415, 81.86301, 130.36,
                81.98701, 130.25699);
        ((GeneralPath) shape).curveTo(82.11001, 130.152, 82.173004, 129.90298,
                82.173004, 129.50899);
        ((GeneralPath) shape).curveTo(82.173004, 129.05998, 82.122, 128.77998,
                82.021, 128.67099);
        ((GeneralPath) shape).curveTo(81.91701, 128.564, 81.652, 128.50899,
                81.218, 128.50899);
        ((GeneralPath) shape).lineTo(79.967, 128.50899);
        ((GeneralPath) shape).lineTo(79.966, 130.415);
        ((GeneralPath) shape).moveTo(78.956, 132.989);
        ((GeneralPath) shape).lineTo(78.956, 127.657);
        ((GeneralPath) shape).lineTo(81.365, 127.657);
        ((GeneralPath) shape).curveTo(82.084, 127.657, 82.57, 127.782, 82.822,
                128.036);
        ((GeneralPath) shape).curveTo(83.072, 128.286, 83.198, 128.76999,
                83.198, 129.489);
        ((GeneralPath) shape).curveTo(83.198, 130.204, 83.078995, 130.677,
                82.839, 130.913);
        ((GeneralPath) shape).curveTo(82.604, 131.14499, 82.118, 131.265,
                81.391, 131.265);
        ((GeneralPath) shape).lineTo(81.157, 131.269);
        ((GeneralPath) shape).lineTo(79.968994, 131.269);
        ((GeneralPath) shape).lineTo(79.968994, 132.993);
        ((GeneralPath) shape).lineTo(78.956, 132.989);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_88;
        g.setTransform(defaultTransform__0_88);
        g.setClip(clip__0_88);
        float alpha__0_89 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_89 = g.getClip();
        AffineTransform defaultTransform__0_89 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_89 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(85.445, 129.919);
        ((GeneralPath) shape).curveTo(85.074, 129.919, 84.837, 129.983, 84.735,
                130.12001);
        ((GeneralPath) shape).curveTo(84.632, 130.253, 84.583, 130.56502,
                84.583, 131.05801);
        ((GeneralPath) shape).curveTo(84.583, 131.62102, 84.63, 131.97202,
                84.729004, 132.113);
        ((GeneralPath) shape).curveTo(84.825005, 132.25601, 85.064, 132.326,
                85.450005, 132.326);
        ((GeneralPath) shape).curveTo(85.82001, 132.326, 86.05601, 132.252,
                86.15401, 132.101);
        ((GeneralPath) shape).curveTo(86.25201, 131.954, 86.30001, 131.597,
                86.30001, 131.03099);
        ((GeneralPath) shape).curveTo(86.30001, 130.55399, 86.24901, 130.25198,
                86.15001, 130.11899);
        ((GeneralPath) shape).curveTo(86.046, 129.985, 85.812, 129.919, 85.445,
                129.919);
        ((GeneralPath) shape).moveTo(85.453, 129.212);
        ((GeneralPath) shape).curveTo(86.172005, 129.212, 86.644005, 129.32701,
                86.868004, 129.558);
        ((GeneralPath) shape).curveTo(87.090004, 129.788, 87.203, 130.275,
                87.203, 131.017);
        ((GeneralPath) shape).curveTo(87.203, 131.845, 87.096, 132.388, 86.875,
                132.646);
        ((GeneralPath) shape).curveTo(86.656, 132.90399, 86.197, 133.03299,
                85.495, 133.03299);
        ((GeneralPath) shape).curveTo(84.734, 133.03299, 84.242004, 132.91599,
                84.01601, 132.676);
        ((GeneralPath) shape).curveTo(83.78901, 132.43799, 83.67901, 131.90799,
                83.67901, 131.094);
        ((GeneralPath) shape).curveTo(83.67901, 130.31099, 83.78601, 129.801,
                84.01101, 129.567);
        ((GeneralPath) shape).curveTo(84.23, 129.329, 84.712, 129.212, 85.453,
                129.212);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_89;
        g.setTransform(defaultTransform__0_89);
        g.setClip(clip__0_89);
        float alpha__0_90 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_90 = g.getClip();
        AffineTransform defaultTransform__0_90 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_90 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(88.844, 129.255);
        ((GeneralPath) shape).lineTo(88.844, 132.987);
        ((GeneralPath) shape).lineTo(87.95, 132.987);
        ((GeneralPath) shape).lineTo(87.95, 129.255);
        ((GeneralPath) shape).lineTo(88.844, 129.255);
        ((GeneralPath) shape).moveTo(88.844, 127.656);
        ((GeneralPath) shape).lineTo(88.844, 128.402);
        ((GeneralPath) shape).lineTo(87.95, 128.402);
        ((GeneralPath) shape).lineTo(87.95, 127.65599);
        ((GeneralPath) shape).lineTo(88.844, 127.65599);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_90;
        g.setTransform(defaultTransform__0_90);
        g.setClip(clip__0_90);
        float alpha__0_91 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_91 = g.getClip();
        AffineTransform defaultTransform__0_91 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_91 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(89.724, 129.255);
        ((GeneralPath) shape).lineTo(90.609, 129.255);
        ((GeneralPath) shape).lineTo(90.574, 129.884);
        ((GeneralPath) shape).lineTo(90.595, 129.888);
        ((GeneralPath) shape).curveTo(90.768, 129.439, 91.158005, 129.212,
                91.764, 129.212);
        ((GeneralPath) shape).curveTo(92.645, 129.212, 93.083, 129.62201,
                93.083, 130.444);
        ((GeneralPath) shape).lineTo(93.083, 132.987);
        ((GeneralPath) shape).lineTo(92.19, 132.987);
        ((GeneralPath) shape).lineTo(92.19, 130.596);
        ((GeneralPath) shape).lineTo(92.169, 130.334);
        ((GeneralPath) shape).curveTo(92.128, 130.057, 91.909996, 129.916,
                91.516, 129.916);
        ((GeneralPath) shape).curveTo(90.914, 129.916, 90.616, 130.201, 90.616,
                130.77);
        ((GeneralPath) shape).lineTo(90.616, 132.985);
        ((GeneralPath) shape).lineTo(89.723, 132.985);
        ((GeneralPath) shape).lineTo(89.724, 129.255);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_91;
        g.setTransform(defaultTransform__0_91);
        g.setClip(clip__0_91);
        float alpha__0_92 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_92 = g.getClip();
        AffineTransform defaultTransform__0_92 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_92 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(96.36, 129.255);
        ((GeneralPath) shape).lineTo(96.36, 129.935);
        ((GeneralPath) shape).lineTo(94.923004, 129.935);
        ((GeneralPath) shape).lineTo(94.923004, 131.81);
        ((GeneralPath) shape).curveTo(94.923004, 132.15599, 95.05601, 132.331,
                95.31901, 132.331);
        ((GeneralPath) shape).curveTo(95.60801, 132.331, 95.75201, 132.122,
                95.75201, 131.702);
        ((GeneralPath) shape).lineTo(95.75201, 131.554);
        ((GeneralPath) shape).lineTo(96.51401, 131.554);
        ((GeneralPath) shape).lineTo(96.51401, 131.742);
        ((GeneralPath) shape).curveTo(96.51401, 131.914, 96.50901, 132.058,
                96.49601, 132.181);
        ((GeneralPath) shape).curveTo(96.44801, 132.751, 96.02501, 133.036,
                95.225006, 133.036);
        ((GeneralPath) shape).curveTo(94.43001, 133.036, 94.032005, 132.67099,
                94.032005, 131.93599);
        ((GeneralPath) shape).lineTo(94.032005, 129.93199);
        ((GeneralPath) shape).lineTo(93.549, 129.93199);
        ((GeneralPath) shape).lineTo(93.549, 129.252);
        ((GeneralPath) shape).lineTo(94.032005, 129.252);
        ((GeneralPath) shape).lineTo(94.032005, 128.416);
        ((GeneralPath) shape).lineTo(94.925, 128.416);
        ((GeneralPath) shape).lineTo(94.925, 129.252);
        ((GeneralPath) shape).lineTo(96.36, 129.255);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_92;
        g.setTransform(defaultTransform__0_92);
        g.setClip(clip__0_92);
        float alpha__0_93 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_93 = g.getClip();
        AffineTransform defaultTransform__0_93 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_93 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(100.116, 130.281);
        ((GeneralPath) shape).lineTo(99.242, 130.281);
        ((GeneralPath) shape).curveTo(99.239, 130.252, 99.23499, 130.22601,
                99.230995, 130.21301);
        ((GeneralPath) shape).curveTo(99.211, 130.031, 99.162994, 129.92201,
                99.076996, 129.87701);
        ((GeneralPath) shape).curveTo(98.993, 129.83401, 98.787994, 129.81102,
                98.463, 129.81102);
        ((GeneralPath) shape).curveTo(97.999, 129.81102, 97.767, 129.96101,
                97.767, 130.26402);
        ((GeneralPath) shape).curveTo(97.767, 130.46902, 97.808, 130.59203,
                97.89, 130.63103);
        ((GeneralPath) shape).curveTo(97.972, 130.67003, 98.247, 130.70103,
                98.723, 130.72502);
        ((GeneralPath) shape).curveTo(99.358, 130.75603, 99.773, 130.84003,
                99.965, 130.97902);
        ((GeneralPath) shape).curveTo(100.156, 131.11601, 100.254, 131.39902,
                100.254, 131.82903);
        ((GeneralPath) shape).curveTo(100.254, 132.28203, 100.127, 132.60002,
                99.869995, 132.77403);
        ((GeneralPath) shape).curveTo(99.615, 132.95003, 99.155, 133.04004,
                98.492, 133.04004);
        ((GeneralPath) shape).curveTo(97.855995, 133.04004, 97.42, 132.96204,
                97.186, 132.80203);
        ((GeneralPath) shape).curveTo(96.951996, 132.64403, 96.832, 132.34703,
                96.832, 131.91103);
        ((GeneralPath) shape).lineTo(96.832, 131.81503);
        ((GeneralPath) shape).lineTo(97.762, 131.81503);
        ((GeneralPath) shape).curveTo(97.748, 131.86603, 97.741, 131.91103,
                97.739, 131.94003);
        ((GeneralPath) shape).curveTo(97.703995, 132.27003, 97.957, 132.43604,
                98.503, 132.43604);
        ((GeneralPath) shape).curveTo(99.066, 132.43604, 99.351, 132.27203,
                99.351, 131.94403);
        ((GeneralPath) shape).curveTo(99.351, 131.63103, 99.173996, 131.47102,
                98.821, 131.47102);
        ((GeneralPath) shape).curveTo(98.024, 131.47102, 97.497, 131.39702,
                97.243996, 131.24402);
        ((GeneralPath) shape).curveTo(96.991, 131.09402, 96.864, 130.77702,
                96.864, 130.30302);
        ((GeneralPath) shape).curveTo(96.864, 129.87703, 96.979, 129.58803,
                97.211, 129.43602);
        ((GeneralPath) shape).curveTo(97.441, 129.28802, 97.885, 129.20901,
                98.544, 129.20901);
        ((GeneralPath) shape).curveTo(99.164, 129.20901, 99.583, 129.27902,
                99.797, 129.42801);
        ((GeneralPath) shape).curveTo(100.009, 129.571, 100.116, 129.856,
                100.116, 130.281);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_93;
        g.setTransform(defaultTransform__0_93);
        g.setClip(clip__0_93);
        float alpha__0_94 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_94 = g.getClip();
        AffineTransform defaultTransform__0_94 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_94 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(101.811, 129.255);
        ((GeneralPath) shape).lineTo(101.811, 130.216);
        ((GeneralPath) shape).lineTo(100.916, 130.216);
        ((GeneralPath) shape).lineTo(100.916, 129.255);
        ((GeneralPath) shape).lineTo(101.811, 129.255);
        ((GeneralPath) shape).moveTo(101.811, 132.028);
        ((GeneralPath) shape).lineTo(101.811, 132.989);
        ((GeneralPath) shape).lineTo(100.916, 132.989);
        ((GeneralPath) shape).lineTo(100.916, 132.028);
        ((GeneralPath) shape).lineTo(101.811, 132.028);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_94;
        g.setTransform(defaultTransform__0_94);
        g.setClip(clip__0_94);
        float alpha__0_95 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_95 = g.getClip();
        AffineTransform defaultTransform__0_95 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_95 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(151.256, 139.379);
        ((GeneralPath) shape).lineTo(151.256, 143.805);
        ((GeneralPath) shape).lineTo(150.247, 143.805);
        ((GeneralPath) shape).lineTo(150.247, 139.379);
        ((GeneralPath) shape).lineTo(148.71, 139.379);
        ((GeneralPath) shape).lineTo(148.71, 138.473);
        ((GeneralPath) shape).lineTo(152.847, 138.473);
        ((GeneralPath) shape).lineTo(152.847, 139.379);
        ((GeneralPath) shape).lineTo(151.256, 139.379);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_95;
        g.setTransform(defaultTransform__0_95);
        g.setClip(clip__0_95);
        float alpha__0_96 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_96 = g.getClip();
        AffineTransform defaultTransform__0_96 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_96 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(155.741, 141.552);
        ((GeneralPath) shape).lineTo(155.737, 141.403);
        ((GeneralPath) shape).curveTo(155.737, 141.107, 155.687, 140.917,
                155.583, 140.827);
        ((GeneralPath) shape).curveTo(155.48099, 140.737, 155.25699, 140.693,
                154.915, 140.693);
        ((GeneralPath) shape).curveTo(154.583, 140.693, 154.36699, 140.747,
                154.26799, 140.853);
        ((GeneralPath) shape).curveTo(154.16699, 140.959, 154.118, 141.193,
                154.118, 141.553);
        ((GeneralPath) shape).lineTo(155.741, 141.552);
        ((GeneralPath) shape).moveTo(155.733, 142.61);
        ((GeneralPath) shape).lineTo(156.629, 142.61);
        ((GeneralPath) shape).lineTo(156.629, 142.756);
        ((GeneralPath) shape).curveTo(156.629, 143.485, 156.083, 143.84999,
                154.991, 143.84999);
        ((GeneralPath) shape).curveTo(154.25, 143.84999, 153.767, 143.72499,
                153.537, 143.471);
        ((GeneralPath) shape).curveTo(153.309, 143.219, 153.194, 142.68399,
                153.194, 141.866);
        ((GeneralPath) shape).curveTo(153.194, 141.14, 153.313, 140.651,
                153.552, 140.401);
        ((GeneralPath) shape).curveTo(153.788, 140.151, 154.256, 140.026,
                154.95, 140.026);
        ((GeneralPath) shape).curveTo(155.61499, 140.026, 156.06099, 140.147,
                156.287, 140.391);
        ((GeneralPath) shape).curveTo(156.515, 140.63301, 156.626, 141.112,
                156.626, 141.82901);
        ((GeneralPath) shape).lineTo(156.626, 142.1);
        ((GeneralPath) shape).lineTo(154.10501, 142.1);
        ((GeneralPath) shape).curveTo(154.10101, 142.182, 154.09702, 142.237,
                154.09702, 142.265);
        ((GeneralPath) shape).curveTo(154.09702, 142.632, 154.15302, 142.87599,
                154.26602, 142.999);
        ((GeneralPath) shape).curveTo(154.37701, 143.12, 154.60303, 143.18199,
                154.94202, 143.18199);
        ((GeneralPath) shape).curveTo(155.27002, 143.18199, 155.48201,
                143.14699, 155.58102, 143.07599);
        ((GeneralPath) shape).curveTo(155.683, 143.002, 155.733, 142.848,
                155.733, 142.61);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_96;
        g.setTransform(defaultTransform__0_96);
        g.setClip(clip__0_96);
        float alpha__0_97 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_97 = g.getClip();
        AffineTransform defaultTransform__0_97 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_97 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(159.746, 142.379);
        ((GeneralPath) shape).lineTo(160.63701, 142.379);
        ((GeneralPath) shape).lineTo(160.63701, 142.508);
        ((GeneralPath) shape).lineTo(160.58, 143.07199);
        ((GeneralPath) shape).curveTo(160.473, 143.59, 159.939, 143.84499,
                158.982, 143.84499);
        ((GeneralPath) shape).curveTo(158.28, 143.84499, 157.814, 143.71799,
                157.583, 143.46399);
        ((GeneralPath) shape).curveTo(157.35399, 143.20999, 157.236, 142.69998,
                157.236, 141.93098);
        ((GeneralPath) shape).curveTo(157.236, 141.18098, 157.351, 140.67699,
                157.583, 140.41498);
        ((GeneralPath) shape).curveTo(157.812, 140.15298, 158.265, 140.02197,
                158.93399, 140.02197);
        ((GeneralPath) shape).curveTo(159.577, 140.02197, 160.01399, 140.11797,
                160.24399, 140.30498);
        ((GeneralPath) shape).curveTo(160.47299, 140.49298, 160.58699,
                140.85197, 160.58699, 141.38197);
        ((GeneralPath) shape).lineTo(159.7, 141.38197);
        ((GeneralPath) shape).curveTo(159.7, 140.94698, 159.45, 140.72897,
                158.94699, 140.72897);
        ((GeneralPath) shape).curveTo(158.59299, 140.72897, 158.368, 140.79697,
                158.277, 140.93697);
        ((GeneralPath) shape).curveTo(158.18799, 141.07497, 158.14099,
                141.41496, 158.14099, 141.95497);
        ((GeneralPath) shape).curveTo(158.14099, 142.47397, 158.18999,
                142.80197, 158.28899, 142.93497);
        ((GeneralPath) shape).curveTo(158.39099, 143.06796, 158.63599,
                143.13597, 159.02399, 143.13597);
        ((GeneralPath) shape).curveTo(159.32999, 143.13597, 159.527, 143.09097,
                159.61499, 142.99696);
        ((GeneralPath) shape).curveTo(159.701, 142.911, 159.746, 142.704,
                159.746, 142.379);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_97;
        g.setTransform(defaultTransform__0_97);
        g.setClip(clip__0_97);
        float alpha__0_98 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_98 = g.getClip();
        AffineTransform defaultTransform__0_98 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_98 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(162.185, 138.473);
        ((GeneralPath) shape).lineTo(162.185, 140.688);
        ((GeneralPath) shape).lineTo(162.205, 140.692);
        ((GeneralPath) shape).curveTo(162.371, 140.252, 162.753, 140.02701,
                163.351, 140.02701);
        ((GeneralPath) shape).curveTo(164.221, 140.02701, 164.657, 140.43901,
                164.657, 141.26501);
        ((GeneralPath) shape).lineTo(164.657, 143.80402);
        ((GeneralPath) shape).lineTo(163.76399, 143.80402);
        ((GeneralPath) shape).lineTo(163.76399, 141.39001);
        ((GeneralPath) shape).curveTo(163.76399, 140.95201, 163.54, 140.73401,
                163.09698, 140.73401);
        ((GeneralPath) shape).curveTo(162.48898, 140.73401, 162.18498,
                141.05101, 162.18498, 141.68301);
        ((GeneralPath) shape).lineTo(162.18498, 143.80402);
        ((GeneralPath) shape).lineTo(161.28998, 143.80402);
        ((GeneralPath) shape).lineTo(161.28998, 138.47202);
        ((GeneralPath) shape).lineTo(162.185, 138.473);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_98;
        g.setTransform(defaultTransform__0_98);
        g.setClip(clip__0_98);
        float alpha__0_99 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_99 = g.getClip();
        AffineTransform defaultTransform__0_99 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        paint4(g, clip_, defaultTransform_, alpha__0, clip__0,
                defaultTransform__0, alpha__0_99, clip__0_99,
                defaultTransform__0_99);

    }

    private static void paint4(Graphics2D g, Shape clip_,
            AffineTransform defaultTransform_, float alpha__0, Shape clip__0,
            AffineTransform defaultTransform__0, float alpha__0_99,
            Shape clip__0_99, AffineTransform defaultTransform__0_99) {
        Shape shape;
        Paint paint;
        Stroke stroke;
        float origAlpha;
        // _0_99 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(168.564, 142.954);
        ((GeneralPath) shape).lineTo(169.66899, 142.954);
        ((GeneralPath) shape).lineTo(170.023, 142.944);
        ((GeneralPath) shape).curveTo(170.362, 142.944, 170.586, 142.897,
                170.696, 142.8);
        ((GeneralPath) shape).curveTo(170.803, 142.70401, 170.858, 142.501,
                170.858, 142.19101);
        ((GeneralPath) shape).curveTo(170.858, 141.87001, 170.801, 141.667,
                170.689, 141.58401);
        ((GeneralPath) shape).curveTo(170.57599, 141.50102, 170.295, 141.45901,
                169.853, 141.45901);
        ((GeneralPath) shape).lineTo(168.563, 141.45901);
        ((GeneralPath) shape).lineTo(168.563, 142.954);
        ((GeneralPath) shape).moveTo(168.564, 140.711);
        ((GeneralPath) shape).lineTo(169.797, 140.711);
        ((GeneralPath) shape).curveTo(170.19699, 140.711, 170.452, 140.668,
                170.565, 140.578);
        ((GeneralPath) shape).curveTo(170.672, 140.488, 170.731, 140.286,
                170.731, 139.965);
        ((GeneralPath) shape).curveTo(170.731, 139.539, 170.489, 139.32199,
                170.004, 139.32199);
        ((GeneralPath) shape).lineTo(168.564, 139.32199);
        ((GeneralPath) shape).lineTo(168.564, 140.711);
        ((GeneralPath) shape).moveTo(167.555, 143.805);
        ((GeneralPath) shape).lineTo(167.555, 138.47299);
        ((GeneralPath) shape).lineTo(170.16899, 138.47299);
        ((GeneralPath) shape).curveTo(170.78099, 138.47299, 171.2, 138.571,
                171.422, 138.76799);
        ((GeneralPath) shape).curveTo(171.646, 138.963, 171.75499, 139.33398,
                171.75499, 139.87898);
        ((GeneralPath) shape).curveTo(171.75499, 140.53699, 171.49399,
                140.92598, 170.97299, 141.04298);
        ((GeneralPath) shape).lineTo(170.97299, 141.06299);
        ((GeneralPath) shape).curveTo(171.579, 141.15698, 171.88599, 141.581,
                171.88599, 142.33398);
        ((GeneralPath) shape).curveTo(171.88599, 142.87599, 171.76898,
                143.25899, 171.53299, 143.47798);
        ((GeneralPath) shape).curveTo(171.297, 143.69698, 170.89099, 143.80598,
                170.30899, 143.80598);
        ((GeneralPath) shape).lineTo(167.555, 143.805);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_99;
        g.setTransform(defaultTransform__0_99);
        g.setClip(clip__0_99);
        float alpha__0_100 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_100 = g.getClip();
        AffineTransform defaultTransform__0_100 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_100 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(174.113, 142.133);
        ((GeneralPath) shape).curveTo(173.61, 142.133, 173.35701, 142.30699,
                173.35701, 142.65399);
        ((GeneralPath) shape).curveTo(173.35701, 142.896, 173.408, 143.05399,
                173.51302, 143.133);
        ((GeneralPath) shape).curveTo(173.61601, 143.208, 173.83202, 143.247,
                174.16602, 143.247);
        ((GeneralPath) shape).curveTo(174.70802, 143.247, 174.98102, 143.06099,
                174.98102, 142.694);
        ((GeneralPath) shape).curveTo(174.983, 142.321, 174.694, 142.133,
                174.113, 142.133);
        ((GeneralPath) shape).moveTo(173.517, 141.153);
        ((GeneralPath) shape).lineTo(172.605, 141.153);
        ((GeneralPath) shape).curveTo(172.605, 140.708, 172.708, 140.407,
                172.91699, 140.257);
        ((GeneralPath) shape).curveTo(173.124, 140.10701, 173.53699, 140.028,
                174.15298, 140.028);
        ((GeneralPath) shape).curveTo(174.82399, 140.028, 175.27599, 140.122,
                175.51398, 140.305);
        ((GeneralPath) shape).curveTo(175.74797, 140.489, 175.86798, 140.842,
                175.86798, 141.364);
        ((GeneralPath) shape).lineTo(175.86798, 143.805);
        ((GeneralPath) shape).lineTo(174.97498, 143.805);
        ((GeneralPath) shape).lineTo(175.01797, 143.293);
        ((GeneralPath) shape).lineTo(174.99498, 143.289);
        ((GeneralPath) shape).curveTo(174.82397, 143.661, 174.42998, 143.848,
                173.80698, 143.848);
        ((GeneralPath) shape).curveTo(172.90598, 143.848, 172.45297, 143.466,
                172.45297, 142.69601);
        ((GeneralPath) shape).curveTo(172.45297, 141.92302, 172.91298,
                141.53201, 173.83698, 141.53201);
        ((GeneralPath) shape).curveTo(174.45297, 141.53201, 174.82698,
                141.67502, 174.95998, 141.96);
        ((GeneralPath) shape).lineTo(174.97798, 141.96);
        ((GeneralPath) shape).lineTo(174.97798, 141.354);
        ((GeneralPath) shape).curveTo(174.97798, 141.063, 174.92499, 140.87201,
                174.82599, 140.774);
        ((GeneralPath) shape).curveTo(174.72398, 140.68001, 174.51799, 140.631,
                174.202, 140.631);
        ((GeneralPath) shape).curveTo(173.745, 140.629, 173.517, 140.803,
                173.517, 141.153);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_100;
        g.setTransform(defaultTransform__0_100);
        g.setClip(clip__0_100);
        float alpha__0_101 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_101 = g.getClip();
        AffineTransform defaultTransform__0_101 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_101 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(179.868, 141.098);
        ((GeneralPath) shape).lineTo(178.995, 141.098);
        ((GeneralPath) shape).curveTo(178.991, 141.067, 178.987, 141.04301,
                178.983, 141.028);
        ((GeneralPath) shape).curveTo(178.963, 140.848, 178.91501, 140.737,
                178.829, 140.693);
        ((GeneralPath) shape).curveTo(178.746, 140.65, 178.541, 140.62799,
                178.215, 140.62799);
        ((GeneralPath) shape).curveTo(177.75099, 140.62799, 177.519, 140.77599,
                177.519, 141.081);
        ((GeneralPath) shape).curveTo(177.519, 141.286, 177.56, 141.407,
                177.642, 141.446);
        ((GeneralPath) shape).curveTo(177.724, 141.485, 177.998, 141.516,
                178.474, 141.54199);
        ((GeneralPath) shape).curveTo(179.109, 141.571, 179.525, 141.655,
                179.716, 141.79599);
        ((GeneralPath) shape).curveTo(179.907, 141.93298, 180.005, 142.21599,
                180.005, 142.644);
        ((GeneralPath) shape).curveTo(180.005, 143.099, 179.878, 143.415,
                179.621, 143.591);
        ((GeneralPath) shape).curveTo(179.366, 143.767, 178.906, 143.85501,
                178.243, 143.85501);
        ((GeneralPath) shape).curveTo(177.607, 143.85501, 177.17099, 143.77701,
                176.937, 143.61801);
        ((GeneralPath) shape).curveTo(176.703, 143.46101, 176.583, 143.16402,
                176.583, 142.72601);
        ((GeneralPath) shape).lineTo(176.583, 142.63202);
        ((GeneralPath) shape).lineTo(177.51299, 142.63202);
        ((GeneralPath) shape).curveTo(177.49898, 142.68301, 177.49199,
                142.72601, 177.48999, 142.75702);
        ((GeneralPath) shape).curveTo(177.45499, 143.08702, 177.708, 143.25302,
                178.254, 143.25302);
        ((GeneralPath) shape).curveTo(178.817, 143.25302, 179.102, 143.08902,
                179.102, 142.76102);
        ((GeneralPath) shape).curveTo(179.102, 142.44702, 178.925, 142.28902,
                178.572, 142.28902);
        ((GeneralPath) shape).curveTo(177.77501, 142.28902, 177.248, 142.21402,
                176.99501, 142.06001);
        ((GeneralPath) shape).curveTo(176.742, 141.91202, 176.615, 141.59502,
                176.615, 141.12102);
        ((GeneralPath) shape).curveTo(176.615, 140.69402, 176.73001, 140.40402,
                176.962, 140.25401);
        ((GeneralPath) shape).curveTo(177.192, 140.10402, 177.636, 140.02501,
                178.295, 140.02501);
        ((GeneralPath) shape).curveTo(178.915, 140.02501, 179.334, 140.09702,
                179.548, 140.246);
        ((GeneralPath) shape).curveTo(179.761, 140.388, 179.868, 140.672,
                179.868, 141.098);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_101;
        g.setTransform(defaultTransform__0_101);
        g.setClip(clip__0_101);
        float alpha__0_102 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_102 = g.getClip();
        AffineTransform defaultTransform__0_102 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_102 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(183.118, 141.552);
        ((GeneralPath) shape).lineTo(183.114, 141.403);
        ((GeneralPath) shape).curveTo(183.114, 141.107, 183.063, 140.917,
                182.95999, 140.827);
        ((GeneralPath) shape).curveTo(182.859, 140.737, 182.635, 140.693,
                182.29199, 140.693);
        ((GeneralPath) shape).curveTo(181.95999, 140.693, 181.74399, 140.747,
                181.64499, 140.853);
        ((GeneralPath) shape).curveTo(181.54399, 140.959, 181.495, 141.193,
                181.495, 141.553);
        ((GeneralPath) shape).lineTo(183.118, 141.552);
        ((GeneralPath) shape).moveTo(183.11, 142.61);
        ((GeneralPath) shape).lineTo(184.006, 142.61);
        ((GeneralPath) shape).lineTo(184.006, 142.756);
        ((GeneralPath) shape).curveTo(184.006, 143.485, 183.45999, 143.84999,
                182.368, 143.84999);
        ((GeneralPath) shape).curveTo(181.627, 143.84999, 181.144, 143.72499,
                180.914, 143.471);
        ((GeneralPath) shape).curveTo(180.686, 143.219, 180.571, 142.68399,
                180.571, 141.866);
        ((GeneralPath) shape).curveTo(180.571, 141.14, 180.69, 140.651, 180.93,
                140.401);
        ((GeneralPath) shape).curveTo(181.16599, 140.151, 181.63599, 140.026,
                182.32799, 140.026);
        ((GeneralPath) shape).curveTo(182.99298, 140.026, 183.43898, 140.147,
                183.665, 140.391);
        ((GeneralPath) shape).curveTo(183.89299, 140.63301, 184.004, 141.112,
                184.004, 141.82901);
        ((GeneralPath) shape).lineTo(184.004, 142.1);
        ((GeneralPath) shape).lineTo(181.483, 142.1);
        ((GeneralPath) shape).curveTo(181.479, 142.182, 181.475, 142.237,
                181.475, 142.265);
        ((GeneralPath) shape).curveTo(181.475, 142.632, 181.531, 142.87599,
                181.64401, 142.999);
        ((GeneralPath) shape).curveTo(181.755, 143.12, 181.98102, 143.18199,
                182.32, 143.18199);
        ((GeneralPath) shape).curveTo(182.64801, 143.18199, 182.86, 143.14699,
                182.95901, 143.07599);
        ((GeneralPath) shape).curveTo(183.06, 143.002, 183.11, 142.848, 183.11,
                142.61);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_102;
        g.setTransform(defaultTransform__0_102);
        g.setClip(clip__0_102);
        float alpha__0_103 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_103 = g.getClip();
        AffineTransform defaultTransform__0_103 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_103 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(185.603, 140.071);
        ((GeneralPath) shape).lineTo(185.603, 141.032);
        ((GeneralPath) shape).lineTo(184.708, 141.032);
        ((GeneralPath) shape).lineTo(184.708, 140.071);
        ((GeneralPath) shape).lineTo(185.603, 140.071);
        ((GeneralPath) shape).moveTo(185.603, 142.844);
        ((GeneralPath) shape).lineTo(185.603, 143.805);
        ((GeneralPath) shape).lineTo(184.708, 143.805);
        ((GeneralPath) shape).lineTo(184.708, 142.844);
        ((GeneralPath) shape).lineTo(185.603, 142.844);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_103;
        g.setTransform(defaultTransform__0_103);
        g.setClip(clip__0_103);
        float alpha__0_104 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_104 = g.getClip();
        AffineTransform defaultTransform__0_104 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_104 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        stroke = new BasicStroke(2.0f, 1, 1, 10.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(32.499, 372.142);
        ((GeneralPath) shape).lineTo(238.057, 372.142);
        ((GeneralPath) shape).moveTo(32.499, 183.637);
        ((GeneralPath) shape).lineTo(238.057, 183.637);
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_104;
        g.setTransform(defaultTransform__0_104);
        g.setClip(clip__0_104);
        float alpha__0_105 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_105 = g.getClip();
        AffineTransform defaultTransform__0_105 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_105 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(43.853, 187.542);
        ((GeneralPath) shape).lineTo(42.074, 194.207);
        ((GeneralPath) shape).lineTo(40.207, 194.207);
        ((GeneralPath) shape).lineTo(39.281002, 190.789);
        ((GeneralPath) shape).curveTo(39.198, 190.479, 39.098003, 190.064,
                38.984, 189.549);
        ((GeneralPath) shape).lineTo(38.891003, 189.13899);
        ((GeneralPath) shape).lineTo(38.848003, 189.13899);
        ((GeneralPath) shape).lineTo(38.748005, 189.555);
        ((GeneralPath) shape).lineTo(38.656006, 189.965);
        ((GeneralPath) shape).curveTo(38.588005, 190.239, 38.514008, 190.51599,
                38.436005, 190.795);
        ((GeneralPath) shape).lineTo(37.486004, 194.208);
        ((GeneralPath) shape).lineTo(35.64, 194.208);
        ((GeneralPath) shape).lineTo(33.913, 187.543);
        ((GeneralPath) shape).lineTo(35.199997, 187.543);
        ((GeneralPath) shape).lineTo(36.159996, 191.2);
        ((GeneralPath) shape).curveTo(36.219997, 191.43199, 36.290997, 191.757,
                36.378998, 192.172);
        ((GeneralPath) shape).lineTo(36.482998, 192.66);
        ((GeneralPath) shape).lineTo(36.580997, 193.146);
        ((GeneralPath) shape).lineTo(36.623997, 193.146);
        ((GeneralPath) shape).curveTo(36.675995, 192.931, 36.713997, 192.76999,
                36.740997, 192.66);
        ((GeneralPath) shape).lineTo(36.857998, 192.17601);
        ((GeneralPath) shape).curveTo(36.919, 191.92201, 37.003998, 191.6,
                37.116997, 191.20401);
        ((GeneralPath) shape).lineTo(38.139996, 187.542);
        ((GeneralPath) shape).lineTo(39.618996, 187.542);
        ((GeneralPath) shape).lineTo(40.618996, 191.20401);
        ((GeneralPath) shape).curveTo(40.701996, 191.51701, 40.784996, 191.839,
                40.866997, 192.17601);
        ((GeneralPath) shape).lineTo(40.978996, 192.66);
        ((GeneralPath) shape).lineTo(41.095997, 193.146);
        ((GeneralPath) shape).lineTo(41.135, 193.146);
        ((GeneralPath) shape).lineTo(41.239998, 192.66);
        ((GeneralPath) shape).lineTo(41.343998, 192.172);
        ((GeneralPath) shape).curveTo(41.427, 191.776, 41.503, 191.454,
                41.572998, 191.193);
        ((GeneralPath) shape).lineTo(42.557, 187.543);
        ((GeneralPath) shape).lineTo(43.853, 187.542);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_105;
        g.setTransform(defaultTransform__0_105);
        g.setClip(clip__0_105);
        float alpha__0_106 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_106 = g.getClip();
        AffineTransform defaultTransform__0_106 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_106 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(47.419, 191.39);
        ((GeneralPath) shape).lineTo(47.413998, 191.204);
        ((GeneralPath) shape).curveTo(47.413998, 190.833, 47.350998, 190.594,
                47.222996, 190.47899);
        ((GeneralPath) shape).curveTo(47.096996, 190.37099, 46.815994,
                190.31299, 46.387997, 190.31299);
        ((GeneralPath) shape).curveTo(45.972996, 190.31299, 45.700996,
                190.37799, 45.574997, 190.51299);
        ((GeneralPath) shape).curveTo(45.450996, 190.64499, 45.386997,
                190.93799, 45.386997, 191.38799);
        ((GeneralPath) shape).lineTo(47.419, 191.39);
        ((GeneralPath) shape).moveTo(47.409, 192.713);
        ((GeneralPath) shape).lineTo(48.531002, 192.713);
        ((GeneralPath) shape).lineTo(48.531002, 192.894);
        ((GeneralPath) shape).curveTo(48.531002, 193.805, 47.848003, 194.261,
                46.484, 194.261);
        ((GeneralPath) shape).curveTo(45.558002, 194.261, 44.953003, 194.105,
                44.666, 193.787);
        ((GeneralPath) shape).curveTo(44.381, 193.473, 44.237, 192.80301,
                44.237, 191.778);
        ((GeneralPath) shape).curveTo(44.237, 190.872, 44.383, 190.259, 44.685,
                189.947);
        ((GeneralPath) shape).curveTo(44.983, 189.634, 45.567, 189.47801,
                46.435, 189.47801);
        ((GeneralPath) shape).curveTo(47.266003, 189.47801, 47.824, 189.62901,
                48.106003, 189.93501);
        ((GeneralPath) shape).curveTo(48.389004, 190.238, 48.530003, 190.835,
                48.530003, 191.72902);
        ((GeneralPath) shape).lineTo(48.530003, 192.07101);
        ((GeneralPath) shape).lineTo(45.377003, 192.07101);
        ((GeneralPath) shape).curveTo(45.372, 192.17502, 45.367004, 192.24402,
                45.367004, 192.27701);
        ((GeneralPath) shape).curveTo(45.367004, 192.735, 45.437004, 193.04301,
                45.579006, 193.195);
        ((GeneralPath) shape).curveTo(45.722008, 193.34601, 46.000008,
                193.42401, 46.424007, 193.42401);
        ((GeneralPath) shape).curveTo(46.834007, 193.42401, 47.101006, 193.38,
                47.225006, 193.29001);
        ((GeneralPath) shape).curveTo(47.346, 193.204, 47.409, 193.011, 47.409,
                192.713);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_106;
        g.setTransform(defaultTransform__0_106);
        g.setClip(clip__0_106);
        float alpha__0_107 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_107 = g.getClip();
        AffineTransform defaultTransform__0_107 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_107 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(51.363, 192.117);
        ((GeneralPath) shape).curveTo(50.733997, 192.117, 50.418, 192.334,
                50.418, 192.769);
        ((GeneralPath) shape).curveTo(50.418, 193.07199, 50.481, 193.269,
                50.613, 193.367);
        ((GeneralPath) shape).curveTo(50.742, 193.46, 51.015, 193.509, 51.432,
                193.509);
        ((GeneralPath) shape).curveTo(52.11, 193.509, 52.45, 193.279, 52.45,
                192.82);
        ((GeneralPath) shape).curveTo(52.45, 192.352, 52.09, 192.117, 51.363,
                192.117);
        ((GeneralPath) shape).moveTo(50.618, 190.892);
        ((GeneralPath) shape).lineTo(49.478, 190.892);
        ((GeneralPath) shape).curveTo(49.478, 190.333, 49.607002, 189.959,
                49.867, 189.769);
        ((GeneralPath) shape).curveTo(50.126, 189.581, 50.642002, 189.486,
                51.412, 189.486);
        ((GeneralPath) shape).curveTo(52.25, 189.486, 52.815, 189.601, 53.113,
                189.83499);
        ((GeneralPath) shape).curveTo(53.406998, 190.064, 53.556, 190.50699,
                53.556, 191.15599);
        ((GeneralPath) shape).lineTo(53.556, 194.20999);
        ((GeneralPath) shape).lineTo(52.44, 194.20999);
        ((GeneralPath) shape).lineTo(52.494, 193.568);
        ((GeneralPath) shape).lineTo(52.465, 193.56299);
        ((GeneralPath) shape).curveTo(52.251, 194.027, 51.756, 194.26099,
                50.981, 194.26099);
        ((GeneralPath) shape).curveTo(49.855, 194.26099, 49.29, 193.78198,
                49.29, 192.82098);
        ((GeneralPath) shape).curveTo(49.29, 191.85199, 49.865, 191.36598,
                51.02, 191.36598);
        ((GeneralPath) shape).curveTo(51.79, 191.36598, 52.255, 191.54398,
                52.421, 191.89899);
        ((GeneralPath) shape).lineTo(52.442, 191.89899);
        ((GeneralPath) shape).lineTo(52.442, 191.14198);
        ((GeneralPath) shape).curveTo(52.442, 190.77898, 52.379, 190.53798,
                52.252003, 190.41698);
        ((GeneralPath) shape).curveTo(52.125004, 190.29997, 51.867004,
                190.23598, 51.474003, 190.23598);
        ((GeneralPath) shape).curveTo(50.903, 190.237, 50.618, 190.455, 50.618,
                190.892);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_107;
        g.setTransform(defaultTransform__0_107);
        g.setClip(clip__0_107);
        float alpha__0_108 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_108 = g.getClip();
        AffineTransform defaultTransform__0_108 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_108 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(56.866, 190.365);
        ((GeneralPath) shape).curveTo(56.442, 190.365, 56.164, 190.462, 56.033,
                190.653);
        ((GeneralPath) shape).curveTo(55.904, 190.843, 55.838, 191.253, 55.838,
                191.876);
        ((GeneralPath) shape).curveTo(55.838, 192.48001, 55.906002, 192.882,
                56.048, 193.082);
        ((GeneralPath) shape).curveTo(56.189, 193.278, 56.474, 193.38, 56.902,
                193.38);
        ((GeneralPath) shape).curveTo(57.336, 193.38, 57.618, 193.28801, 57.75,
                193.097);
        ((GeneralPath) shape).curveTo(57.879, 192.909, 57.945, 192.504, 57.945,
                191.882);
        ((GeneralPath) shape).curveTo(57.945, 191.244, 57.878998, 190.832,
                57.746998, 190.64601);
        ((GeneralPath) shape).curveTo(57.615, 190.459, 57.322, 190.365, 56.866,
                190.365);
        ((GeneralPath) shape).moveTo(54.668, 189.539);
        ((GeneralPath) shape).lineTo(55.805, 189.539);
        ((GeneralPath) shape).lineTo(55.760002, 190.23);
        ((GeneralPath) shape).lineTo(55.784, 190.235);
        ((GeneralPath) shape).curveTo(56.054, 189.732, 56.555, 189.478, 57.289,
                189.478);
        ((GeneralPath) shape).curveTo(57.967003, 189.478, 58.435, 189.646,
                58.690002, 189.984);
        ((GeneralPath) shape).curveTo(58.943, 190.31999, 59.072002, 190.933,
                59.072002, 191.829);
        ((GeneralPath) shape).curveTo(59.072002, 192.76, 58.946003, 193.394,
                58.692, 193.743);
        ((GeneralPath) shape).curveTo(58.439003, 194.088, 57.971, 194.261,
                57.284, 194.261);
        ((GeneralPath) shape).curveTo(56.555, 194.261, 56.075, 194.017, 55.851,
                193.529);
        ((GeneralPath) shape).lineTo(55.83, 193.529);
        ((GeneralPath) shape).lineTo(55.83, 196.21101);
        ((GeneralPath) shape).lineTo(54.665, 196.21101);
        ((GeneralPath) shape).lineTo(54.665, 189.54102);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_108;
        g.setTransform(defaultTransform__0_108);
        g.setClip(clip__0_108);
        float alpha__0_109 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_109 = g.getClip();
        AffineTransform defaultTransform__0_109 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_109 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(62.045, 190.369);
        ((GeneralPath) shape).curveTo(61.581997, 190.369, 61.285, 190.452,
                61.157997, 190.621);
        ((GeneralPath) shape).curveTo(61.031998, 190.787, 60.968, 191.18001,
                60.968, 191.795);
        ((GeneralPath) shape).curveTo(60.968, 192.498, 61.029, 192.938,
                61.150997, 193.111);
        ((GeneralPath) shape).curveTo(61.269997, 193.28899, 61.569996, 193.377,
                62.049995, 193.377);
        ((GeneralPath) shape).curveTo(62.514996, 193.377, 62.809994, 193.285,
                62.931995, 193.099);
        ((GeneralPath) shape).curveTo(63.053997, 192.913, 63.114994, 192.469,
                63.114994, 191.761);
        ((GeneralPath) shape).curveTo(63.114994, 191.166, 63.051994, 190.785,
                62.924995, 190.618);
        ((GeneralPath) shape).curveTo(62.796, 190.453, 62.503, 190.369, 62.045,
                190.369);
        ((GeneralPath) shape).moveTo(62.055, 189.485);
        ((GeneralPath) shape).curveTo(62.952, 189.485, 63.541, 189.631, 63.824,
                189.92);
        ((GeneralPath) shape).curveTo(64.102005, 190.208, 64.243004, 190.816,
                64.243004, 191.744);
        ((GeneralPath) shape).curveTo(64.243004, 192.77701, 64.106, 193.457,
                63.833004, 193.778);
        ((GeneralPath) shape).curveTo(63.562004, 194.101, 62.985004, 194.261,
                62.108006, 194.261);
        ((GeneralPath) shape).curveTo(61.157005, 194.261, 60.541004, 194.112,
                60.256004, 193.812);
        ((GeneralPath) shape).curveTo(59.978004, 193.51399, 59.835003, 192.855,
                59.835003, 191.834);
        ((GeneralPath) shape).curveTo(59.835003, 190.855, 59.973003, 190.218,
                60.250004, 189.925);
        ((GeneralPath) shape).curveTo(60.527, 189.632, 61.128, 189.485, 62.055,
                189.485);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_109;
        g.setTransform(defaultTransform__0_109);
        g.setClip(clip__0_109);
        float alpha__0_110 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_110 = g.getClip();
        AffineTransform defaultTransform__0_110 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_110 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(65.178, 189.539);
        ((GeneralPath) shape).lineTo(66.282, 189.539);
        ((GeneralPath) shape).lineTo(66.238, 190.325);
        ((GeneralPath) shape).lineTo(66.262, 190.33);
        ((GeneralPath) shape).curveTo(66.479004, 189.767, 66.964005, 189.483,
                67.724, 189.483);
        ((GeneralPath) shape).curveTo(68.826, 189.483, 69.374, 189.998, 69.374,
                191.026);
        ((GeneralPath) shape).lineTo(69.374, 194.206);
        ((GeneralPath) shape).lineTo(68.26, 194.206);
        ((GeneralPath) shape).lineTo(68.26, 191.21799);
        ((GeneralPath) shape).lineTo(68.236, 190.89099);
        ((GeneralPath) shape).curveTo(68.185, 190.54399, 67.912, 190.37,
                67.417, 190.37);
        ((GeneralPath) shape).curveTo(66.667, 190.37, 66.292, 190.724, 66.292,
                191.43799);
        ((GeneralPath) shape).lineTo(66.292, 194.208);
        ((GeneralPath) shape).lineTo(65.178, 194.208);
        ((GeneralPath) shape).lineTo(65.178, 189.539);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_110;
        g.setTransform(defaultTransform__0_110);
        g.setClip(clip__0_110);
        float alpha__0_111 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_111 = g.getClip();
        AffineTransform defaultTransform__0_111 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_111 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(74.356, 190.823);
        ((GeneralPath) shape).lineTo(73.264, 190.823);
        ((GeneralPath) shape).curveTo(73.258, 190.786, 73.253, 190.757, 73.248,
                190.735);
        ((GeneralPath) shape).curveTo(73.227, 190.51, 73.163, 190.373, 73.057,
                190.316);
        ((GeneralPath) shape).curveTo(72.953, 190.262, 72.697, 190.233, 72.288,
                190.233);
        ((GeneralPath) shape).curveTo(71.708, 190.233, 71.415, 190.421, 71.415,
                190.799);
        ((GeneralPath) shape).curveTo(71.415, 191.055, 71.466, 191.209, 71.569,
                191.258);
        ((GeneralPath) shape).curveTo(71.671, 191.30699, 72.017, 191.346,
                72.61, 191.375);
        ((GeneralPath) shape).curveTo(73.404, 191.414, 73.923004, 191.52,
                74.164, 191.69);
        ((GeneralPath) shape).curveTo(74.403, 191.86101, 74.525, 192.215,
                74.525, 192.752);
        ((GeneralPath) shape).curveTo(74.525, 193.319, 74.367004, 193.717,
                74.045, 193.936);
        ((GeneralPath) shape).curveTo(73.728, 194.156, 73.15, 194.266, 72.322,
                194.266);
        ((GeneralPath) shape).curveTo(71.528, 194.266, 70.982, 194.168, 70.69,
                193.968);
        ((GeneralPath) shape).curveTo(70.398, 193.77, 70.251, 193.399, 70.251,
                192.855);
        ((GeneralPath) shape).lineTo(70.251, 192.73799);
        ((GeneralPath) shape).lineTo(71.411, 192.73799);
        ((GeneralPath) shape).curveTo(71.396, 192.801, 71.389, 192.855, 71.382,
                192.894);
        ((GeneralPath) shape).curveTo(71.338005, 193.30699, 71.65501,
                193.51399, 72.338005, 193.51399);
        ((GeneralPath) shape).curveTo(73.04201, 193.51399, 73.397, 193.30899,
                73.397, 192.89899);
        ((GeneralPath) shape).curveTo(73.397, 192.50598, 73.178, 192.30899,
                72.734, 192.30899);
        ((GeneralPath) shape).curveTo(71.737, 192.30899, 71.079, 192.21599,
                70.763, 192.025);
        ((GeneralPath) shape).curveTo(70.446, 191.83699, 70.285, 191.444,
                70.285, 190.84799);
        ((GeneralPath) shape).curveTo(70.285, 190.316, 70.43101, 189.954,
                70.721, 189.76399);
        ((GeneralPath) shape).curveTo(71.007, 189.57599, 71.564, 189.48099,
                72.388, 189.48099);
        ((GeneralPath) shape).curveTo(73.16, 189.48099, 73.686, 189.57098,
                73.952, 189.75398);
        ((GeneralPath) shape).curveTo(74.223, 189.935, 74.356, 190.291, 74.356,
                190.823);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_111;
        g.setTransform(defaultTransform__0_111);
        g.setClip(clip__0_111);
        float alpha__0_112 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_112 = g.getClip();
        AffineTransform defaultTransform__0_112 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_112 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(79.814, 191.019);
        ((GeneralPath) shape).curveTo(79.324005, 191.09, 79.079, 191.459,
                79.079, 192.123);
        ((GeneralPath) shape).curveTo(79.079, 192.635, 79.162, 192.962, 79.328,
                193.102);
        ((GeneralPath) shape).curveTo(79.494, 193.242, 79.878006, 193.31201,
                80.48801, 193.31201);
        ((GeneralPath) shape).curveTo(81.00901, 193.31201, 81.34801, 193.29102,
                81.504005, 193.24301);
        ((GeneralPath) shape).curveTo(81.657005, 193.195, 81.823006, 193.06302,
                81.99901, 192.84601);
        ((GeneralPath) shape).lineTo(79.814, 191.019);
        ((GeneralPath) shape).moveTo(82.31, 190.994);
        ((GeneralPath) shape).lineTo(83.392, 190.994);
        ((GeneralPath) shape).lineTo(83.392, 191.209);
        ((GeneralPath) shape).lineTo(83.38, 191.791);
        ((GeneralPath) shape).curveTo(83.38, 192.067, 83.340996, 192.367,
                83.257996, 192.684);
        ((GeneralPath) shape).lineTo(84.213, 193.49701);
        ((GeneralPath) shape).lineTo(83.613, 194.225);
        ((GeneralPath) shape).lineTo(82.813995, 193.55101);
        ((GeneralPath) shape).curveTo(82.519, 194.03001, 81.835, 194.26901,
                80.767, 194.26901);
        ((GeneralPath) shape).curveTo(79.577995, 194.26901, 78.799995,
                194.13202, 78.43, 193.85901);
        ((GeneralPath) shape).curveTo(78.062004, 193.58801, 77.875, 193.007,
                77.875, 192.12302);
        ((GeneralPath) shape).curveTo(77.875, 191.12502, 78.296, 190.56102,
                79.144, 190.43701);
        ((GeneralPath) shape).curveTo(78.781, 190.12102, 78.598, 189.71901,
                78.598, 189.23502);
        ((GeneralPath) shape).curveTo(78.598, 188.62201, 78.745, 188.19502,
                79.047, 187.96301);
        ((GeneralPath) shape).curveTo(79.349, 187.72902, 79.893, 187.60901,
                80.679, 187.60901);
        ((GeneralPath) shape).curveTo(81.501, 187.60901, 82.066, 187.729,
                82.367004, 187.968);
        ((GeneralPath) shape).curveTo(82.66701, 188.207, 82.818, 188.656,
                82.818, 189.31801);
        ((GeneralPath) shape).lineTo(82.813, 189.667);
        ((GeneralPath) shape).lineTo(81.692, 189.667);
        ((GeneralPath) shape).lineTo(81.692, 189.472);
        ((GeneralPath) shape).curveTo(81.692, 189.072, 81.638, 188.82, 81.528,
                188.717);
        ((GeneralPath) shape).curveTo(81.421, 188.61299, 81.155, 188.564,
                80.731, 188.564);
        ((GeneralPath) shape).curveTo(80.107, 188.564, 79.793, 188.795, 79.793,
                189.262);
        ((GeneralPath) shape).curveTo(79.793, 189.575, 79.954, 189.87299,
                80.28, 190.151);
        ((GeneralPath) shape).lineTo(82.302, 191.889);
        ((GeneralPath) shape).curveTo(82.332, 191.72101, 82.351006, 191.56201,
                82.351006, 191.416);
        ((GeneralPath) shape).curveTo(82.358, 191.29, 82.344, 191.15, 82.31,
                190.994);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_112;
        g.setTransform(defaultTransform__0_112);
        g.setClip(clip__0_112);
        float alpha__0_113 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_113 = g.getClip();
        AffineTransform defaultTransform__0_113 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_113 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(88.491, 188.606);
        ((GeneralPath) shape).lineTo(88.491, 190.325);
        ((GeneralPath) shape).lineTo(91.609, 190.325);
        ((GeneralPath) shape).lineTo(91.609, 191.258);
        ((GeneralPath) shape).lineTo(88.491, 191.258);
        ((GeneralPath) shape).lineTo(88.491, 193.143);
        ((GeneralPath) shape).lineTo(91.81, 193.143);
        ((GeneralPath) shape).lineTo(91.81, 194.207);
        ((GeneralPath) shape).lineTo(87.228, 194.207);
        ((GeneralPath) shape).lineTo(87.228, 187.542);
        ((GeneralPath) shape).lineTo(91.78, 187.542);
        ((GeneralPath) shape).lineTo(91.78, 188.606);
        ((GeneralPath) shape).lineTo(88.491, 188.606);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_113;
        g.setTransform(defaultTransform__0_113);
        g.setClip(clip__0_113);
        float alpha__0_114 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_114 = g.getClip();
        AffineTransform defaultTransform__0_114 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_114 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(94.675, 190.369);
        ((GeneralPath) shape).curveTo(94.272, 190.369, 94.00201, 190.459,
                93.871, 190.643);
        ((GeneralPath) shape).curveTo(93.742004, 190.828, 93.676, 191.207,
                93.676, 191.785);
        ((GeneralPath) shape).curveTo(93.676, 192.476, 93.739006, 192.918,
                93.861, 193.101);
        ((GeneralPath) shape).curveTo(93.983, 193.289, 94.27, 193.379, 94.726,
                193.379);
        ((GeneralPath) shape).curveTo(95.138, 193.379, 95.412994, 193.284,
                95.549995, 193.091);
        ((GeneralPath) shape).curveTo(95.687996, 192.901, 95.756996, 192.517,
                95.756996, 191.944);
        ((GeneralPath) shape).curveTo(95.756996, 191.28, 95.69399, 190.853,
                95.562, 190.659);
        ((GeneralPath) shape).curveTo(95.428, 190.467, 95.132, 190.369, 94.675,
                190.369);
        ((GeneralPath) shape).moveTo(96.887, 189.539);
        ((GeneralPath) shape).lineTo(96.887, 196.209);
        ((GeneralPath) shape).lineTo(95.771, 196.209);
        ((GeneralPath) shape).lineTo(95.771, 193.521);
        ((GeneralPath) shape).lineTo(95.75, 193.51599);
        ((GeneralPath) shape).curveTo(95.545, 194.01399, 95.068, 194.26299,
                94.317, 194.26299);
        ((GeneralPath) shape).curveTo(93.630005, 194.26299, 93.165, 194.08998,
                92.916, 193.74298);
        ((GeneralPath) shape).curveTo(92.668, 193.39398, 92.543, 192.74399,
                92.543, 191.78699);
        ((GeneralPath) shape).curveTo(92.543, 190.90298, 92.669, 190.29999,
                92.925, 189.97299);
        ((GeneralPath) shape).curveTo(93.18, 189.64499, 93.651, 189.482,
                94.336006, 189.482);
        ((GeneralPath) shape).curveTo(95.04001, 189.482, 95.51501, 189.73099,
                95.754005, 190.234);
        ((GeneralPath) shape).lineTo(95.784004, 190.22499);
        ((GeneralPath) shape).lineTo(95.769005, 189.53699);
        ((GeneralPath) shape).lineTo(96.887, 189.539);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_114;
        g.setTransform(defaultTransform__0_114);
        g.setClip(clip__0_114);
        float alpha__0_115 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_115 = g.getClip();
        AffineTransform defaultTransform__0_115 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_115 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(102.123, 189.539);
        ((GeneralPath) shape).lineTo(102.123, 194.207);
        ((GeneralPath) shape).lineTo(101.007, 194.207);
        ((GeneralPath) shape).lineTo(101.07001, 193.404);
        ((GeneralPath) shape).lineTo(101.049, 193.40001);
        ((GeneralPath) shape).curveTo(100.832, 193.97101, 100.332, 194.259,
                99.548004, 194.259);
        ((GeneralPath) shape).curveTo(98.493004, 194.259, 97.965004, 193.73201,
                97.965004, 192.672);
        ((GeneralPath) shape).lineTo(97.965004, 189.537);
        ((GeneralPath) shape).lineTo(99.081, 189.537);
        ((GeneralPath) shape).lineTo(99.081, 192.401);
        ((GeneralPath) shape).curveTo(99.081, 192.797, 99.137, 193.058, 99.247,
                193.185);
        ((GeneralPath) shape).curveTo(99.356, 193.31, 99.585, 193.373,
                99.935005, 193.373);
        ((GeneralPath) shape).curveTo(100.649, 193.373, 101.005005, 192.941,
                101.005005, 192.084);
        ((GeneralPath) shape).lineTo(101.005005, 189.535);
        ((GeneralPath) shape).lineTo(102.121, 189.535);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_115;
        g.setTransform(defaultTransform__0_115);
        g.setClip(clip__0_115);
        float alpha__0_116 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_116 = g.getClip();
        AffineTransform defaultTransform__0_116 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_116 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(104.371, 189.539);
        ((GeneralPath) shape).lineTo(104.371, 194.207);
        ((GeneralPath) shape).lineTo(103.255005, 194.207);
        ((GeneralPath) shape).lineTo(103.255005, 189.539);
        ((GeneralPath) shape).lineTo(104.371, 189.539);
        ((GeneralPath) shape).moveTo(104.371, 187.542);
        ((GeneralPath) shape).lineTo(104.371, 188.475);
        ((GeneralPath) shape).lineTo(103.255005, 188.475);
        ((GeneralPath) shape).lineTo(103.255005, 187.542);
        ((GeneralPath) shape).lineTo(104.371, 187.542);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_116;
        g.setTransform(defaultTransform__0_116);
        g.setClip(clip__0_116);
        float alpha__0_117 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_117 = g.getClip();
        AffineTransform defaultTransform__0_117 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_117 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(107.669, 190.365);
        ((GeneralPath) shape).curveTo(107.244995, 190.365, 106.966995, 190.462,
                106.836, 190.653);
        ((GeneralPath) shape).curveTo(106.707, 190.843, 106.643, 191.253,
                106.643, 191.876);
        ((GeneralPath) shape).curveTo(106.643, 192.48001, 106.709, 192.882,
                106.852, 193.082);
        ((GeneralPath) shape).curveTo(106.991, 193.278, 107.276, 193.38,
                107.704994, 193.38);
        ((GeneralPath) shape).curveTo(108.14099, 193.38, 108.421, 193.28801,
                108.55299, 193.097);
        ((GeneralPath) shape).curveTo(108.68399, 192.909, 108.74799, 192.504,
                108.74799, 191.882);
        ((GeneralPath) shape).curveTo(108.74799, 191.244, 108.68399, 190.832,
                108.550995, 190.64601);
        ((GeneralPath) shape).curveTo(108.418, 190.459, 108.125, 190.365,
                107.669, 190.365);
        ((GeneralPath) shape).moveTo(105.471, 189.539);
        ((GeneralPath) shape).lineTo(106.606, 189.539);
        ((GeneralPath) shape).lineTo(106.563, 190.23);
        ((GeneralPath) shape).lineTo(106.58501, 190.235);
        ((GeneralPath) shape).curveTo(106.85601, 189.732, 107.35601, 189.478,
                108.091, 189.478);
        ((GeneralPath) shape).curveTo(108.771, 189.478, 109.23701, 189.646,
                109.492004, 189.984);
        ((GeneralPath) shape).curveTo(109.745, 190.31999, 109.87401, 190.933,
                109.87401, 191.829);
        ((GeneralPath) shape).curveTo(109.87401, 192.76, 109.74801, 193.394,
                109.49401, 193.743);
        ((GeneralPath) shape).curveTo(109.24101, 194.088, 108.77201, 194.261,
                108.08601, 194.261);
        ((GeneralPath) shape).curveTo(107.35702, 194.261, 106.877014, 194.017,
                106.653015, 193.529);
        ((GeneralPath) shape).lineTo(106.63201, 193.529);
        ((GeneralPath) shape).lineTo(106.63201, 196.21101);
        ((GeneralPath) shape).lineTo(105.46801, 196.21101);
        ((GeneralPath) shape).lineTo(105.471, 189.539);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_117;
        g.setTransform(defaultTransform__0_117);
        g.setClip(clip__0_117);
        float alpha__0_118 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_118 = g.getClip();
        AffineTransform defaultTransform__0_118 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_118 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(110.811, 189.539);
        ((GeneralPath) shape).lineTo(111.924995, 189.539);
        ((GeneralPath) shape).lineTo(111.897995, 190.257);
        ((GeneralPath) shape).lineTo(111.92, 190.26201);
        ((GeneralPath) shape).curveTo(112.148994, 189.744, 112.622, 189.48601,
                113.345, 189.48601);
        ((GeneralPath) shape).curveTo(114.186005, 189.48601, 114.654, 189.774,
                114.746, 190.35);
        ((GeneralPath) shape).lineTo(114.767006, 190.35);
        ((GeneralPath) shape).curveTo(114.98401, 189.774, 115.462006,
                189.48601, 116.20501, 189.48601);
        ((GeneralPath) shape).curveTo(117.28201, 189.48601, 117.82301,
                190.02802, 117.82301, 191.119);
        ((GeneralPath) shape).lineTo(117.82301, 194.209);
        ((GeneralPath) shape).lineTo(116.707016, 194.209);
        ((GeneralPath) shape).lineTo(116.707016, 191.36);
        ((GeneralPath) shape).curveTo(116.707016, 190.703, 116.43601, 190.371,
                115.89401, 190.371);
        ((GeneralPath) shape).curveTo(115.21501, 190.371, 114.87301, 190.738,
                114.87301, 191.478);
        ((GeneralPath) shape).lineTo(114.87301, 194.207);
        ((GeneralPath) shape).lineTo(113.75701, 194.207);
        ((GeneralPath) shape).lineTo(113.75701, 191.314);
        ((GeneralPath) shape).curveTo(113.75701, 190.928, 113.70601, 190.672,
                113.60501, 190.54999);
        ((GeneralPath) shape).curveTo(113.501015, 190.42699, 113.28601,
                190.36598, 112.95501, 190.36598);
        ((GeneralPath) shape).curveTo(112.27001, 190.36598, 111.92801,
                190.74199, 111.92801, 191.49898);
        ((GeneralPath) shape).lineTo(111.92801, 194.20398);
        ((GeneralPath) shape).lineTo(110.81301, 194.20398);
        ((GeneralPath) shape).lineTo(110.81301, 189.53798);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_118;
        g.setTransform(defaultTransform__0_118);
        g.setClip(clip__0_118);
        float alpha__0_119 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_119 = g.getClip();
        AffineTransform defaultTransform__0_119 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_119 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(121.978, 191.39);
        ((GeneralPath) shape).lineTo(121.973, 191.204);
        ((GeneralPath) shape).curveTo(121.973, 190.833, 121.909996, 190.594,
                121.78, 190.47899);
        ((GeneralPath) shape).curveTo(121.654, 190.37099, 121.373, 190.31299,
                120.945, 190.31299);
        ((GeneralPath) shape).curveTo(120.53, 190.31299, 120.26, 190.37799,
                120.131996, 190.51299);
        ((GeneralPath) shape).curveTo(120.007996, 190.64499, 119.94399,
                190.93799, 119.94399, 191.38799);
        ((GeneralPath) shape).lineTo(121.978, 191.39);
        ((GeneralPath) shape).moveTo(121.967, 192.713);
        ((GeneralPath) shape).lineTo(123.089005, 192.713);
        ((GeneralPath) shape).lineTo(123.089005, 192.894);
        ((GeneralPath) shape).curveTo(123.089005, 193.805, 122.406006, 194.261,
                121.04201, 194.261);
        ((GeneralPath) shape).curveTo(120.115005, 194.261, 119.51301, 194.105,
                119.226006, 193.787);
        ((GeneralPath) shape).curveTo(118.941, 193.473, 118.795006, 192.80301,
                118.795006, 191.778);
        ((GeneralPath) shape).curveTo(118.795006, 190.872, 118.94301, 190.259,
                119.243004, 189.947);
        ((GeneralPath) shape).curveTo(119.54, 189.634, 120.12501, 189.47801,
                120.993004, 189.47801);
        ((GeneralPath) shape).curveTo(121.824005, 189.47801, 122.382,
                189.62901, 122.664, 189.93501);
        ((GeneralPath) shape).curveTo(122.947, 190.238, 123.088005, 190.835,
                123.088005, 191.72902);
        ((GeneralPath) shape).lineTo(123.088005, 192.07101);
        ((GeneralPath) shape).lineTo(119.936005, 192.07101);
        ((GeneralPath) shape).curveTo(119.93101, 192.17502, 119.924, 192.24402,
                119.924, 192.27701);
        ((GeneralPath) shape).curveTo(119.924, 192.735, 119.995, 193.04301,
                120.138, 193.195);
        ((GeneralPath) shape).curveTo(120.279, 193.34601, 120.559, 193.42401,
                120.983, 193.42401);
        ((GeneralPath) shape).curveTo(121.393005, 193.42401, 121.658005,
                193.38, 121.782005, 193.29001);
        ((GeneralPath) shape).curveTo(121.904, 193.204, 121.967, 193.011,
                121.967, 192.713);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_119;
        g.setTransform(defaultTransform__0_119);
        g.setClip(clip__0_119);
        float alpha__0_120 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_120 = g.getClip();
        AffineTransform defaultTransform__0_120 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_120 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(124.016, 189.539);
        ((GeneralPath) shape).lineTo(125.119995, 189.539);
        ((GeneralPath) shape).lineTo(125.076, 190.325);
        ((GeneralPath) shape).lineTo(125.1, 190.33);
        ((GeneralPath) shape).curveTo(125.317, 189.767, 125.804, 189.483,
                126.562, 189.483);
        ((GeneralPath) shape).curveTo(127.663994, 189.483, 128.21199, 189.998,
                128.21199, 191.026);
        ((GeneralPath) shape).lineTo(128.21199, 194.206);
        ((GeneralPath) shape).lineTo(127.1, 194.206);
        ((GeneralPath) shape).lineTo(127.1, 191.21799);
        ((GeneralPath) shape).lineTo(127.076, 190.89099);
        ((GeneralPath) shape).curveTo(127.024994, 190.54399, 126.752, 190.37,
                126.256996, 190.37);
        ((GeneralPath) shape).curveTo(125.506996, 190.37, 125.131996, 190.724,
                125.131996, 191.43799);
        ((GeneralPath) shape).lineTo(125.131996, 194.208);
        ((GeneralPath) shape).lineTo(124.018, 194.208);
        ((GeneralPath) shape).lineTo(124.016, 189.539);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_120;
        g.setTransform(defaultTransform__0_120);
        g.setClip(clip__0_120);
        float alpha__0_121 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_121 = g.getClip();
        AffineTransform defaultTransform__0_121 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_121 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(132.313, 189.539);
        ((GeneralPath) shape).lineTo(132.313, 190.389);
        ((GeneralPath) shape).lineTo(130.52, 190.389);
        ((GeneralPath) shape).lineTo(130.52, 192.733);
        ((GeneralPath) shape).curveTo(130.52, 193.167, 130.684, 193.382,
                131.01201, 193.382);
        ((GeneralPath) shape).curveTo(131.37001, 193.382, 131.55301, 193.121,
                131.55301, 192.59601);
        ((GeneralPath) shape).lineTo(131.55301, 192.41);
        ((GeneralPath) shape).lineTo(132.501, 192.41);
        ((GeneralPath) shape).lineTo(132.501, 192.642);
        ((GeneralPath) shape).curveTo(132.501, 192.857, 132.496, 193.04,
                132.481, 193.194);
        ((GeneralPath) shape).curveTo(132.418, 193.909, 131.892, 194.263,
                130.892, 194.263);
        ((GeneralPath) shape).curveTo(129.898, 194.263, 129.401, 193.806,
                129.401, 192.891);
        ((GeneralPath) shape).lineTo(129.401, 190.386);
        ((GeneralPath) shape).lineTo(128.797, 190.386);
        ((GeneralPath) shape).lineTo(128.797, 189.536);
        ((GeneralPath) shape).lineTo(129.401, 189.536);
        ((GeneralPath) shape).lineTo(129.401, 188.491);
        ((GeneralPath) shape).lineTo(130.517, 188.491);
        ((GeneralPath) shape).lineTo(130.517, 189.536);
        ((GeneralPath) shape).lineTo(132.313, 189.539);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_121;
        g.setTransform(defaultTransform__0_121);
        g.setClip(clip__0_121);
        float alpha__0_122 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_122 = g.getClip();
        AffineTransform defaultTransform__0_122 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_122 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new Rectangle2D.Double(135.64700317382812, 187.54200744628906,
                1.2630000114440918, 6.664999961853027);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_122;
        g.setTransform(defaultTransform__0_122);
        g.setClip(clip__0_122);
        float alpha__0_123 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_123 = g.getClip();
        AffineTransform defaultTransform__0_123 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_123 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(138.059, 189.539);
        ((GeneralPath) shape).lineTo(139.16301, 189.539);
        ((GeneralPath) shape).lineTo(139.119, 190.325);
        ((GeneralPath) shape).lineTo(139.144, 190.33);
        ((GeneralPath) shape).curveTo(139.36, 189.767, 139.84799, 189.483,
                140.606, 189.483);
        ((GeneralPath) shape).curveTo(141.70801, 189.483, 142.256, 189.998,
                142.256, 191.026);
        ((GeneralPath) shape).lineTo(142.256, 194.206);
        ((GeneralPath) shape).lineTo(141.142, 194.206);
        ((GeneralPath) shape).lineTo(141.142, 191.21799);
        ((GeneralPath) shape).lineTo(141.117, 190.89099);
        ((GeneralPath) shape).curveTo(141.06601, 190.54399, 140.793, 190.37,
                140.29901, 190.37);
        ((GeneralPath) shape).curveTo(139.54901, 190.37, 139.173, 190.724,
                139.173, 191.43799);
        ((GeneralPath) shape).lineTo(139.173, 194.208);
        ((GeneralPath) shape).lineTo(138.059, 194.208);
        ((GeneralPath) shape).lineTo(138.059, 189.539);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_123;
        g.setTransform(defaultTransform__0_123);
        g.setClip(clip__0_123);
        float alpha__0_124 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_124 = g.getClip();
        AffineTransform defaultTransform__0_124 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_124 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(147.233, 189.539);
        ((GeneralPath) shape).lineTo(145.898, 194.207);
        ((GeneralPath) shape).lineTo(144.153, 194.207);
        ((GeneralPath) shape).lineTo(142.75, 189.539);
        ((GeneralPath) shape).lineTo(143.936, 189.539);
        ((GeneralPath) shape).lineTo(144.548, 191.69);
        ((GeneralPath) shape).curveTo(144.631, 191.988, 144.709, 192.279,
                144.781, 192.559);
        ((GeneralPath) shape).lineTo(144.893, 192.996);
        ((GeneralPath) shape).lineTo(145.003, 193.431);
        ((GeneralPath) shape).lineTo(145.02701, 193.431);
        ((GeneralPath) shape).lineTo(145.13, 192.996);
        ((GeneralPath) shape).lineTo(145.23401, 192.564);
        ((GeneralPath) shape).curveTo(145.31201, 192.24199, 145.384, 191.95099,
                145.453, 191.69499);
        ((GeneralPath) shape).lineTo(146.028, 189.53899);
        ((GeneralPath) shape).lineTo(147.233, 189.53899);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_124;
        g.setTransform(defaultTransform__0_124);
        g.setClip(clip__0_124);
        float alpha__0_125 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_125 = g.getClip();
        AffineTransform defaultTransform__0_125 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_125 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(150.743, 191.39);
        ((GeneralPath) shape).lineTo(150.73799, 191.204);
        ((GeneralPath) shape).curveTo(150.73799, 190.833, 150.67499, 190.594,
                150.545, 190.47899);
        ((GeneralPath) shape).curveTo(150.41899, 190.37099, 150.14, 190.31299,
                149.70999, 190.31299);
        ((GeneralPath) shape).curveTo(149.295, 190.31299, 149.025, 190.37799,
                148.898, 190.51299);
        ((GeneralPath) shape).curveTo(148.774, 190.64499, 148.70999, 190.93799,
                148.70999, 191.38799);
        ((GeneralPath) shape).lineTo(150.743, 191.39);
        ((GeneralPath) shape).moveTo(150.732, 192.713);
        ((GeneralPath) shape).lineTo(151.85399, 192.713);
        ((GeneralPath) shape).lineTo(151.85399, 192.894);
        ((GeneralPath) shape).curveTo(151.85399, 193.805, 151.16899, 194.261,
                149.80699, 194.261);
        ((GeneralPath) shape).curveTo(148.879, 194.261, 148.27599, 194.105,
                147.989, 193.787);
        ((GeneralPath) shape).curveTo(147.704, 193.473, 147.56, 192.80301,
                147.56, 191.778);
        ((GeneralPath) shape).curveTo(147.56, 190.872, 147.706, 190.259,
                148.006, 189.947);
        ((GeneralPath) shape).curveTo(148.306, 189.63501, 148.89, 189.47801,
                149.756, 189.47801);
        ((GeneralPath) shape).curveTo(150.58699, 189.47801, 151.147, 189.62901,
                151.429, 189.93501);
        ((GeneralPath) shape).curveTo(151.71, 190.238, 151.853, 190.835,
                151.853, 191.72902);
        ((GeneralPath) shape).lineTo(151.853, 192.07101);
        ((GeneralPath) shape).lineTo(148.7, 192.07101);
        ((GeneralPath) shape).curveTo(148.69499, 192.17502, 148.69, 192.24402,
                148.69, 192.27701);
        ((GeneralPath) shape).curveTo(148.69, 192.735, 148.76001, 193.04301,
                148.90201, 193.195);
        ((GeneralPath) shape).curveTo(149.04301, 193.34601, 149.32301,
                193.42401, 149.74701, 193.42401);
        ((GeneralPath) shape).curveTo(150.15701, 193.42401, 150.42201, 193.38,
                150.546, 193.29001);
        ((GeneralPath) shape).curveTo(150.669, 193.204, 150.732, 193.011,
                150.732, 192.713);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_125;
        g.setTransform(defaultTransform__0_125);
        g.setClip(clip__0_125);
        float alpha__0_126 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_126 = g.getClip();
        AffineTransform defaultTransform__0_126 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_126 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(152.781, 189.539);
        ((GeneralPath) shape).lineTo(153.88701, 189.539);
        ((GeneralPath) shape).lineTo(153.841, 190.325);
        ((GeneralPath) shape).lineTo(153.865, 190.33);
        ((GeneralPath) shape).curveTo(154.082, 189.767, 154.569, 189.483,
                155.32701, 189.483);
        ((GeneralPath) shape).curveTo(156.43001, 189.483, 156.979, 189.998,
                156.979, 191.026);
        ((GeneralPath) shape).lineTo(156.979, 194.206);
        ((GeneralPath) shape).lineTo(155.863, 194.206);
        ((GeneralPath) shape).lineTo(155.863, 191.21799);
        ((GeneralPath) shape).lineTo(155.839, 190.89099);
        ((GeneralPath) shape).curveTo(155.78801, 190.54399, 155.517, 190.37,
                155.022, 190.37);
        ((GeneralPath) shape).curveTo(154.272, 190.37, 153.897, 190.724,
                153.897, 191.43799);
        ((GeneralPath) shape).lineTo(153.897, 194.208);
        ((GeneralPath) shape).lineTo(152.781, 194.208);
        ((GeneralPath) shape).lineTo(152.781, 189.539);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_126;
        g.setTransform(defaultTransform__0_126);
        g.setClip(clip__0_126);
        float alpha__0_127 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_127 = g.getClip();
        AffineTransform defaultTransform__0_127 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_127 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(161.078, 189.539);
        ((GeneralPath) shape).lineTo(161.078, 190.389);
        ((GeneralPath) shape).lineTo(159.285, 190.389);
        ((GeneralPath) shape).lineTo(159.285, 192.733);
        ((GeneralPath) shape).curveTo(159.285, 193.167, 159.449, 193.382,
                159.77701, 193.382);
        ((GeneralPath) shape).curveTo(160.138, 193.382, 160.31801, 193.121,
                160.31801, 192.59601);
        ((GeneralPath) shape).lineTo(160.31801, 192.41);
        ((GeneralPath) shape).lineTo(161.268, 192.41);
        ((GeneralPath) shape).lineTo(161.268, 192.642);
        ((GeneralPath) shape).curveTo(161.268, 192.857, 161.264, 193.04,
                161.24701, 193.194);
        ((GeneralPath) shape).curveTo(161.186, 193.909, 160.658, 194.263,
                159.658, 194.263);
        ((GeneralPath) shape).curveTo(158.664, 194.263, 158.167, 193.806,
                158.167, 192.891);
        ((GeneralPath) shape).lineTo(158.167, 190.386);
        ((GeneralPath) shape).lineTo(157.563, 190.386);
        ((GeneralPath) shape).lineTo(157.563, 189.536);
        ((GeneralPath) shape).lineTo(158.167, 189.536);
        ((GeneralPath) shape).lineTo(158.167, 188.491);
        ((GeneralPath) shape).lineTo(159.283, 188.491);
        ((GeneralPath) shape).lineTo(159.283, 189.536);
        ((GeneralPath) shape).lineTo(161.078, 189.539);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_127;
        g.setTransform(defaultTransform__0_127);
        g.setClip(clip__0_127);
        float alpha__0_128 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_128 = g.getClip();
        AffineTransform defaultTransform__0_128 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_128 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(163.92, 190.369);
        ((GeneralPath) shape).curveTo(163.458, 190.369, 163.16, 190.452,
                163.033, 190.621);
        ((GeneralPath) shape).curveTo(162.907, 190.787, 162.843, 191.18001,
                162.843, 191.795);
        ((GeneralPath) shape).curveTo(162.843, 192.498, 162.904, 192.938,
                163.026, 193.111);
        ((GeneralPath) shape).curveTo(163.145, 193.28899, 163.445, 193.377,
                163.925, 193.377);
        ((GeneralPath) shape).curveTo(164.39, 193.377, 164.685, 193.285,
                164.806, 193.099);
        ((GeneralPath) shape).curveTo(164.926, 192.913, 164.989, 192.469,
                164.989, 191.761);
        ((GeneralPath) shape).curveTo(164.989, 191.166, 164.925, 190.785,
                164.799, 190.618);
        ((GeneralPath) shape).curveTo(164.671, 190.453, 164.379, 190.369,
                163.92, 190.369);
        ((GeneralPath) shape).moveTo(163.931, 189.485);
        ((GeneralPath) shape).curveTo(164.828, 189.485, 165.418, 189.631,
                165.7, 189.92);
        ((GeneralPath) shape).curveTo(165.97699, 190.208, 166.119, 190.816,
                166.119, 191.744);
        ((GeneralPath) shape).curveTo(166.119, 192.77701, 165.983, 193.457,
                165.71, 193.778);
        ((GeneralPath) shape).curveTo(165.43901, 194.101, 164.862, 194.261,
                163.985, 194.261);
        ((GeneralPath) shape).curveTo(163.034, 194.261, 162.418, 194.112,
                162.133, 193.812);
        ((GeneralPath) shape).curveTo(161.85399, 193.51399, 161.711, 192.855,
                161.711, 191.834);
        ((GeneralPath) shape).curveTo(161.711, 190.855, 161.85, 190.218,
                162.12599, 189.925);
        ((GeneralPath) shape).curveTo(162.40198, 189.632, 163.004, 189.485,
                163.931, 189.485);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_128;
        g.setTransform(defaultTransform__0_128);
        g.setClip(clip__0_128);
        float alpha__0_129 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_129 = g.getClip();
        AffineTransform defaultTransform__0_129 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_129 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(167.083, 189.539);
        ((GeneralPath) shape).lineTo(168.19899, 189.539);
        ((GeneralPath) shape).lineTo(168.131, 190.191);
        ((GeneralPath) shape).lineTo(168.155, 190.196);
        ((GeneralPath) shape).curveTo(168.421, 189.71, 168.84, 189.466,
                169.413, 189.466);
        ((GeneralPath) shape).curveTo(170.23099, 189.466, 170.642, 189.98401,
                170.642, 191.018);
        ((GeneralPath) shape).lineTo(170.642, 191.345);
        ((GeneralPath) shape).lineTo(169.587, 191.345);
        ((GeneralPath) shape).curveTo(169.602, 191.218, 169.608, 191.135,
                169.608, 191.09601);
        ((GeneralPath) shape).curveTo(169.608, 190.59702, 169.416, 190.348,
                169.028, 190.348);
        ((GeneralPath) shape).curveTo(168.477, 190.348, 168.199, 190.71701,
                168.199, 191.457);
        ((GeneralPath) shape).lineTo(168.199, 194.206);
        ((GeneralPath) shape).lineTo(167.08301, 194.206);
        ((GeneralPath) shape).lineTo(167.083, 189.539);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_129;
        g.setTransform(defaultTransform__0_129);
        g.setClip(clip__0_129);
        float alpha__0_130 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_130 = g.getClip();
        AffineTransform defaultTransform__0_130 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_130 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(175.091, 189.539);
        ((GeneralPath) shape).lineTo(173.868, 194.5);
        ((GeneralPath) shape).curveTo(173.69699, 195.203, 173.483, 195.674,
                173.22699, 195.918);
        ((GeneralPath) shape).curveTo(172.97398, 196.16, 172.564, 196.28,
                171.999, 196.28);
        ((GeneralPath) shape).curveTo(171.872, 196.28, 171.741, 196.275,
                171.60399, 196.26);
        ((GeneralPath) shape).lineTo(171.60399, 195.435);
        ((GeneralPath) shape).curveTo(171.702, 195.44, 171.78198, 195.44499,
                171.84799, 195.44499);
        ((GeneralPath) shape).curveTo(172.321, 195.44499, 172.62099, 195.03499,
                172.74799, 194.20699);
        ((GeneralPath) shape).lineTo(172.18, 194.20699);
        ((GeneralPath) shape).lineTo(170.655, 189.53899);
        ((GeneralPath) shape).lineTo(171.854, 189.53899);
        ((GeneralPath) shape).lineTo(172.438, 191.51698);
        ((GeneralPath) shape).lineTo(172.73001, 192.50798);
        ((GeneralPath) shape).curveTo(172.74501, 192.57098, 172.79102,
                192.73698, 172.867, 193.00598);
        ((GeneralPath) shape).lineTo(173.00801, 193.49898);
        ((GeneralPath) shape).lineTo(173.03201, 193.49898);
        ((GeneralPath) shape).lineTo(173.13602, 193.00598);
        ((GeneralPath) shape).curveTo(173.18802, 192.75198, 173.22102,
                192.58598, 173.23901, 192.50798);
        ((GeneralPath) shape).lineTo(173.46802, 191.51698);
        ((GeneralPath) shape).lineTo(173.91602, 189.53899);
        ((GeneralPath) shape).lineTo(175.091, 189.53899);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_130;
        g.setTransform(defaultTransform__0_130);
        g.setClip(clip__0_130);
        float alpha__0_131 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_131 = g.getClip();
        AffineTransform defaultTransform__0_131 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_131 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(195.222, 195.46);
        ((GeneralPath) shape).lineTo(195.222, 196.35701);
        ((GeneralPath) shape).curveTo(194.358, 196.35701, 193.835, 196.18001,
                193.662, 195.82101);
        ((GeneralPath) shape).curveTo(193.489, 195.46701, 193.403, 194.389,
                193.403, 192.59502);
        ((GeneralPath) shape).curveTo(193.403, 190.80301, 193.489, 189.72801,
                193.662, 189.37102);
        ((GeneralPath) shape).curveTo(193.835, 189.01701, 194.353, 188.83702,
                195.222, 188.83702);
        ((GeneralPath) shape).lineTo(195.222, 189.73402);
        ((GeneralPath) shape).curveTo(194.87, 189.73402, 194.659, 189.82703,
                194.588, 190.00603);
        ((GeneralPath) shape).curveTo(194.52, 190.18803, 194.484, 190.73503,
                194.484, 191.64703);
        ((GeneralPath) shape).lineTo(194.484, 193.54303);
        ((GeneralPath) shape).curveTo(194.484, 194.45503, 194.51799, 195.00203,
                194.588, 195.18404);
        ((GeneralPath) shape).curveTo(194.659, 195.369, 194.87, 195.46,
                195.222, 195.46);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_131;
        g.setTransform(defaultTransform__0_131);
        g.setClip(clip__0_131);
        float alpha__0_132 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_132 = g.getClip();
        AffineTransform defaultTransform__0_132 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_132 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(197.092, 188.851);
        ((GeneralPath) shape).lineTo(197.092, 191.247);
        ((GeneralPath) shape).lineTo(197.11299, 191.25099);
        ((GeneralPath) shape).curveTo(197.29199, 190.77199, 197.70499,
                190.53099, 198.35199, 190.53099);
        ((GeneralPath) shape).curveTo(199.29, 190.53099, 199.76399, 190.976,
                199.76399, 191.872);
        ((GeneralPath) shape).lineTo(199.76399, 194.61499);
        ((GeneralPath) shape).lineTo(198.797, 194.61499);
        ((GeneralPath) shape).lineTo(198.797, 192.00699);
        ((GeneralPath) shape).curveTo(198.797, 191.53299, 198.55899, 191.29799,
                198.076, 191.29799);
        ((GeneralPath) shape).curveTo(197.419, 191.29799, 197.09001, 191.63998,
                197.09001, 192.32199);
        ((GeneralPath) shape).lineTo(197.09001, 194.61499);
        ((GeneralPath) shape).lineTo(196.12502, 194.61499);
        ((GeneralPath) shape).lineTo(196.12502, 188.85098);
        ((GeneralPath) shape).lineTo(197.092, 188.851);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_132;
        g.setTransform(defaultTransform__0_132);
        g.setClip(clip__0_132);
        float alpha__0_133 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_133 = g.getClip();
        AffineTransform defaultTransform__0_133 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_133 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(203.315, 192.179);
        ((GeneralPath) shape).lineTo(203.311, 192.018);
        ((GeneralPath) shape).curveTo(203.311, 191.697, 203.255, 191.49,
                203.144, 191.393);
        ((GeneralPath) shape).curveTo(203.035, 191.298, 202.79, 191.25,
                202.41899, 191.25);
        ((GeneralPath) shape).curveTo(202.06299, 191.25, 201.827, 191.307,
                201.719, 191.423);
        ((GeneralPath) shape).curveTo(201.612, 191.537, 201.55699, 191.791,
                201.55699, 192.179);
        ((GeneralPath) shape).lineTo(203.315, 192.179);
        ((GeneralPath) shape).moveTo(203.307, 193.323);
        ((GeneralPath) shape).lineTo(204.27701, 193.323);
        ((GeneralPath) shape).lineTo(204.27701, 193.479);
        ((GeneralPath) shape).curveTo(204.27701, 194.267, 203.68701, 194.664,
                202.50601, 194.664);
        ((GeneralPath) shape).curveTo(201.70502, 194.664, 201.184, 194.52701,
                200.936, 194.254);
        ((GeneralPath) shape).curveTo(200.68901, 193.982, 200.565, 193.401,
                200.565, 192.519);
        ((GeneralPath) shape).curveTo(200.565, 191.734, 200.69301, 191.206,
                200.953, 190.936);
        ((GeneralPath) shape).curveTo(201.21, 190.66501, 201.716, 190.53,
                202.466, 190.53);
        ((GeneralPath) shape).curveTo(203.186, 190.53, 203.667, 190.663,
                203.91101, 190.926);
        ((GeneralPath) shape).curveTo(204.156, 191.18799, 204.27802, 191.705,
                204.27802, 192.478);
        ((GeneralPath) shape).lineTo(204.27802, 192.773);
        ((GeneralPath) shape).lineTo(201.54901, 192.773);
        ((GeneralPath) shape).curveTo(201.54501, 192.862, 201.54102, 192.92099,
                201.54102, 192.952);
        ((GeneralPath) shape).curveTo(201.54102, 193.34799, 201.60402, 193.612,
                201.72702, 193.747);
        ((GeneralPath) shape).curveTo(201.84901, 193.87799, 202.08902, 193.943,
                202.45602, 193.943);
        ((GeneralPath) shape).curveTo(202.81003, 193.943, 203.04002, 193.905,
                203.14702, 193.82799);
        ((GeneralPath) shape).curveTo(203.252, 193.748, 203.307, 193.581,
                203.307, 193.323);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_133;
        g.setTransform(defaultTransform__0_133);
        g.setClip(clip__0_133);
        float alpha__0_134 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_134 = g.getClip();
        AffineTransform defaultTransform__0_134 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_134 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(208.375, 190.578);
        ((GeneralPath) shape).lineTo(207.203, 192.479);
        ((GeneralPath) shape).lineTo(208.505, 194.615);
        ((GeneralPath) shape).lineTo(207.35, 194.615);
        ((GeneralPath) shape).lineTo(206.541, 193.15);
        ((GeneralPath) shape).lineTo(205.753, 194.615);
        ((GeneralPath) shape).lineTo(204.568, 194.615);
        ((GeneralPath) shape).lineTo(205.888, 192.49);
        ((GeneralPath) shape).lineTo(204.724, 190.578);
        ((GeneralPath) shape).lineTo(205.875, 190.578);
        ((GeneralPath) shape).lineTo(206.541, 191.857);
        ((GeneralPath) shape).lineTo(207.224, 190.578);
        ((GeneralPath) shape).lineTo(208.375, 190.578);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_134;
        g.setTransform(defaultTransform__0_134);
        g.setClip(clip__0_134);
        float alpha__0_135 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_135 = g.getClip();
        AffineTransform defaultTransform__0_135 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_135 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(211.559, 192.179);
        ((GeneralPath) shape).lineTo(211.55501, 192.018);
        ((GeneralPath) shape).curveTo(211.55501, 191.697, 211.498, 191.49,
                211.388, 191.393);
        ((GeneralPath) shape).curveTo(211.278, 191.298, 211.034, 191.25,
                210.663, 191.25);
        ((GeneralPath) shape).curveTo(210.30699, 191.25, 210.071, 191.307,
                209.961, 191.423);
        ((GeneralPath) shape).curveTo(209.856, 191.537, 209.799, 191.791,
                209.799, 192.179);
        ((GeneralPath) shape).lineTo(211.559, 192.179);
        ((GeneralPath) shape).moveTo(211.55, 193.323);
        ((GeneralPath) shape).lineTo(212.522, 193.323);
        ((GeneralPath) shape).lineTo(212.522, 193.479);
        ((GeneralPath) shape).curveTo(212.522, 194.267, 211.932, 194.664,
                210.751, 194.664);
        ((GeneralPath) shape).curveTo(209.95001, 194.664, 209.427, 194.52701,
                209.181, 194.254);
        ((GeneralPath) shape).curveTo(208.934, 193.982, 208.81, 193.401,
                208.81, 192.519);
        ((GeneralPath) shape).curveTo(208.81, 191.734, 208.938, 191.206,
                209.198, 190.936);
        ((GeneralPath) shape).curveTo(209.455, 190.66501, 209.961, 190.53,
                210.711, 190.53);
        ((GeneralPath) shape).curveTo(211.431, 190.53, 211.912, 190.663,
                212.157, 190.926);
        ((GeneralPath) shape).curveTo(212.401, 191.18799, 212.524, 191.705,
                212.524, 192.478);
        ((GeneralPath) shape).lineTo(212.524, 192.773);
        ((GeneralPath) shape).lineTo(209.795, 192.773);
        ((GeneralPath) shape).curveTo(209.791, 192.862, 209.787, 192.92099,
                209.787, 192.952);
        ((GeneralPath) shape).curveTo(209.787, 193.34799, 209.85, 193.612,
                209.972, 193.747);
        ((GeneralPath) shape).curveTo(210.092, 193.87799, 210.335, 193.943,
                210.701, 193.943);
        ((GeneralPath) shape).curveTo(211.05501, 193.943, 211.285, 193.905,
                211.392, 193.82799);
        ((GeneralPath) shape).curveTo(211.496, 193.748, 211.55, 193.581,
                211.55, 193.323);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_135;
        g.setTransform(defaultTransform__0_135);
        g.setClip(clip__0_135);
        float alpha__0_136 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_136 = g.getClip();
        AffineTransform defaultTransform__0_136 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_136 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(216.685, 191.688);
        ((GeneralPath) shape).lineTo(215.741, 191.688);
        ((GeneralPath) shape).curveTo(215.737, 191.655, 215.733, 191.629,
                215.728, 191.612);
        ((GeneralPath) shape).curveTo(215.707, 191.42, 215.65399, 191.299,
                215.56099, 191.251);
        ((GeneralPath) shape).curveTo(215.46999, 191.20401, 215.24799, 191.179,
                214.89699, 191.179);
        ((GeneralPath) shape).curveTo(214.39598, 191.179, 214.14299, 191.342,
                214.14299, 191.669);
        ((GeneralPath) shape).curveTo(214.14299, 191.89, 214.187, 192.02301,
                214.27599, 192.065);
        ((GeneralPath) shape).curveTo(214.36398, 192.10701, 214.66399, 192.141,
                215.17398, 192.169);
        ((GeneralPath) shape).curveTo(215.86198, 192.20201, 216.31198, 192.294,
                216.51997, 192.44101);
        ((GeneralPath) shape).curveTo(216.72697, 192.589, 216.83197, 192.895,
                216.83197, 193.35802);
        ((GeneralPath) shape).curveTo(216.83197, 193.85002, 216.69498,
                194.19202, 216.41698, 194.38202);
        ((GeneralPath) shape).curveTo(216.14297, 194.57002, 215.64598,
                194.66702, 214.92697, 194.66702);
        ((GeneralPath) shape).curveTo(214.23997, 194.66702, 213.76997,
                194.58302, 213.51697, 194.40703);
        ((GeneralPath) shape).curveTo(213.26396, 194.23602, 213.13496,
                193.91702, 213.13496, 193.44403);
        ((GeneralPath) shape).lineTo(213.13496, 193.34203);
        ((GeneralPath) shape).lineTo(214.13997, 193.34203);
        ((GeneralPath) shape).curveTo(214.12497, 193.39702, 214.11897,
                193.44403, 214.11497, 193.47702);
        ((GeneralPath) shape).curveTo(214.07698, 193.83401, 214.34998,
                194.01402, 214.94098, 194.01402);
        ((GeneralPath) shape).curveTo(215.54797, 194.01402, 215.85597,
                193.83702, 215.85597, 193.48203);
        ((GeneralPath) shape).curveTo(215.85597, 193.14203, 215.66597,
                192.97102, 215.28297, 192.97102);
        ((GeneralPath) shape).curveTo(214.42097, 192.97102, 213.85197,
                192.89102, 213.57797, 192.72603);
        ((GeneralPath) shape).curveTo(213.30498, 192.56303, 213.16698,
                192.22403, 213.16698, 191.70802);
        ((GeneralPath) shape).curveTo(213.16698, 191.24802, 213.29198,
                190.93503, 213.54198, 190.77002);
        ((GeneralPath) shape).curveTo(213.79097, 190.60703, 214.27098,
                190.52502, 214.98398, 190.52502);
        ((GeneralPath) shape).curveTo(215.65398, 190.52502, 216.10698,
                190.60303, 216.33798, 190.76202);
        ((GeneralPath) shape).curveTo(216.569, 190.92, 216.685, 191.229,
                216.685, 191.688);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_136;
        g.setTransform(defaultTransform__0_136);
        g.setClip(clip__0_136);
        float alpha__0_137 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_137 = g.getClip();
        AffineTransform defaultTransform__0_137 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_137 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(217.544, 189.738);
        ((GeneralPath) shape).lineTo(217.544, 188.841);
        ((GeneralPath) shape).curveTo(218.408, 188.841, 218.931, 189.018,
                219.104, 189.375);
        ((GeneralPath) shape).curveTo(219.27701, 189.729, 219.363, 190.804,
                219.363, 192.599);
        ((GeneralPath) shape).curveTo(219.363, 194.391, 219.27701, 195.467,
                219.104, 195.825);
        ((GeneralPath) shape).curveTo(218.931, 196.183, 218.41301, 196.361,
                217.544, 196.361);
        ((GeneralPath) shape).lineTo(217.544, 195.46399);
        ((GeneralPath) shape).curveTo(218.024, 195.46399, 218.26701, 195.204,
                218.26701, 194.68199);
        ((GeneralPath) shape).lineTo(218.28401, 193.551);
        ((GeneralPath) shape).lineTo(218.28401, 191.655);
        ((GeneralPath) shape).lineTo(218.26701, 190.523);
        ((GeneralPath) shape).curveTo(218.265, 190.0, 218.024, 189.738,
                217.544, 189.738);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_137;
        g.setTransform(defaultTransform__0_137);
        g.setClip(clip__0_137);
        float alpha__0_138 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_138 = g.getClip();
        AffineTransform defaultTransform__0_138 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_138 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(37.338, 204.056);
        ((GeneralPath) shape).lineTo(37.759003, 203.447);
        ((GeneralPath) shape).lineTo(38.861004, 204.19301);
        ((GeneralPath) shape).curveTo(38.880005, 204.00401, 38.892002,
                203.87502, 38.892002, 203.80602);
        ((GeneralPath) shape).lineTo(38.892002, 202.60701);
        ((GeneralPath) shape).curveTo(38.892002, 202.11002, 38.812, 201.794,
                38.65, 201.65102);
        ((GeneralPath) shape).curveTo(38.49, 201.51201, 38.124, 201.44202,
                37.555, 201.44202);
        ((GeneralPath) shape).curveTo(36.875, 201.44202, 36.481, 201.52802,
                36.366, 201.70602);
        ((GeneralPath) shape).curveTo(36.253002, 201.88002, 36.194, 202.48203,
                36.194, 203.51903);
        ((GeneralPath) shape).curveTo(36.194, 204.30003, 36.260002, 204.76503,
                36.403, 204.91502);
        ((GeneralPath) shape).curveTo(36.541, 205.06102, 36.976, 205.13702,
                37.707, 205.13702);
        ((GeneralPath) shape).curveTo(38.077, 205.13702, 38.37, 205.05902,
                38.581, 204.89902);
        ((GeneralPath) shape).lineTo(37.338, 204.056);
        ((GeneralPath) shape).moveTo(40.368, 205.24);
        ((GeneralPath) shape).lineTo(39.951, 205.849);
        ((GeneralPath) shape).lineTo(39.444, 205.497);
        ((GeneralPath) shape).curveTo(39.115, 205.833, 38.485, 206.00099,
                37.557, 206.00099);
        ((GeneralPath) shape).curveTo(36.538998, 206.00099, 35.889, 205.849,
                35.6, 205.54199);
        ((GeneralPath) shape).curveTo(35.310997, 205.23499, 35.164997,
                204.54399, 35.164997, 203.46199);
        ((GeneralPath) shape).curveTo(35.164997, 202.204, 35.303997, 201.409,
                35.576996, 201.07599);
        ((GeneralPath) shape).curveTo(35.849995, 200.74298, 36.504997,
                200.57599, 37.539997, 200.57599);
        ((GeneralPath) shape).curveTo(38.559998, 200.57599, 39.207996, 200.73,
                39.490997, 201.04599);
        ((GeneralPath) shape).curveTo(39.771996, 201.359, 39.913998, 202.083,
                39.913998, 203.21599);
        ((GeneralPath) shape).curveTo(39.913998, 204.02098, 39.878998,
                204.56699, 39.809998, 204.857);
        ((GeneralPath) shape).lineTo(40.368, 205.24);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_138;
        g.setTransform(defaultTransform__0_138);
        g.setClip(clip__0_138);
        float alpha__0_139 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_139 = g.getClip();
        AffineTransform defaultTransform__0_139 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_139 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(43.141, 202.22);
        ((GeneralPath) shape).lineTo(43.141, 202.9);
        ((GeneralPath) shape).lineTo(41.704, 202.9);
        ((GeneralPath) shape).lineTo(41.704, 204.775);
        ((GeneralPath) shape).curveTo(41.704, 205.123, 41.834, 205.29599, 42.1,
                205.29599);
        ((GeneralPath) shape).curveTo(42.389, 205.29599, 42.532997, 205.08699,
                42.532997, 204.66699);
        ((GeneralPath) shape).lineTo(42.532997, 204.519);
        ((GeneralPath) shape).lineTo(43.292995, 204.519);
        ((GeneralPath) shape).lineTo(43.292995, 204.707);
        ((GeneralPath) shape).curveTo(43.292995, 204.879, 43.288994, 205.023,
                43.276997, 205.147);
        ((GeneralPath) shape).curveTo(43.227997, 205.71701, 42.804996, 206.002,
                42.005997, 206.002);
        ((GeneralPath) shape).curveTo(41.211, 206.002, 40.814995, 205.637,
                40.814995, 204.902);
        ((GeneralPath) shape).lineTo(40.814995, 202.9);
        ((GeneralPath) shape).lineTo(40.33, 202.9);
        ((GeneralPath) shape).lineTo(40.33, 202.22);
        ((GeneralPath) shape).lineTo(40.813004, 202.22);
        ((GeneralPath) shape).lineTo(40.813004, 201.384);
        ((GeneralPath) shape).lineTo(41.706005, 201.384);
        ((GeneralPath) shape).lineTo(41.706005, 202.22);
        ((GeneralPath) shape).lineTo(43.141, 202.22);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_139;
        g.setTransform(defaultTransform__0_139);
        g.setClip(clip__0_139);
        float alpha__0_140 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_140 = g.getClip();
        AffineTransform defaultTransform__0_140 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_140 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(46.856, 202.22);
        ((GeneralPath) shape).lineTo(45.877, 206.189);
        ((GeneralPath) shape).curveTo(45.739998, 206.752, 45.569, 207.12799,
                45.364, 207.32399);
        ((GeneralPath) shape).curveTo(45.161, 207.51698, 44.834, 207.616,
                44.384, 207.616);
        ((GeneralPath) shape).curveTo(44.281998, 207.616, 44.177, 207.612,
                44.07, 207.59999);
        ((GeneralPath) shape).lineTo(44.07, 206.93999);
        ((GeneralPath) shape).curveTo(44.148, 206.94398, 44.213, 206.94798,
                44.265, 206.94798);
        ((GeneralPath) shape).curveTo(44.642998, 206.94798, 44.883, 206.61998,
                44.986, 205.95798);
        ((GeneralPath) shape).lineTo(44.53, 205.95798);
        ((GeneralPath) shape).lineTo(43.309998, 202.22398);
        ((GeneralPath) shape).lineTo(44.268997, 202.22398);
        ((GeneralPath) shape).lineTo(44.736996, 203.80598);
        ((GeneralPath) shape).lineTo(44.970997, 204.59898);
        ((GeneralPath) shape).curveTo(44.982998, 204.64998, 45.019997,
                204.78299, 45.077995, 204.99698);
        ((GeneralPath) shape).lineTo(45.190994, 205.39198);
        ((GeneralPath) shape).lineTo(45.210995, 205.39198);
        ((GeneralPath) shape).lineTo(45.292995, 204.99698);
        ((GeneralPath) shape).curveTo(45.333996, 204.79398, 45.360996,
                204.66098, 45.374996, 204.59898);
        ((GeneralPath) shape).lineTo(45.559998, 203.80598);
        ((GeneralPath) shape).lineTo(45.915997, 202.22398);
        ((GeneralPath) shape).lineTo(46.855995, 202.22398);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_140;
        g.setTransform(defaultTransform__0_140);
        g.setClip(clip__0_140);
        float alpha__0_141 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_141 = g.getClip();
        AffineTransform defaultTransform__0_141 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_141 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(53.605, 201.528);
        ((GeneralPath) shape).lineTo(53.605, 205.954);
        ((GeneralPath) shape).lineTo(52.596, 205.954);
        ((GeneralPath) shape).lineTo(52.596, 201.528);
        ((GeneralPath) shape).lineTo(51.06, 201.528);
        ((GeneralPath) shape).lineTo(51.06, 200.622);
        ((GeneralPath) shape).lineTo(55.196, 200.622);
        ((GeneralPath) shape).lineTo(55.196, 201.528);
        ((GeneralPath) shape).lineTo(53.605, 201.528);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_141;
        g.setTransform(defaultTransform__0_141);
        g.setClip(clip__0_141);
        float alpha__0_142 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_142 = g.getClip();
        AffineTransform defaultTransform__0_142 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_142 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(58.751, 202.22);
        ((GeneralPath) shape).lineTo(57.772, 206.189);
        ((GeneralPath) shape).curveTo(57.633, 206.752, 57.461998, 207.12799,
                57.257, 207.32399);
        ((GeneralPath) shape).curveTo(57.056, 207.51698, 56.729, 207.616,
                56.278, 207.616);
        ((GeneralPath) shape).curveTo(56.176, 207.616, 56.071, 207.612,
                55.961998, 207.59999);
        ((GeneralPath) shape).lineTo(55.961998, 206.93999);
        ((GeneralPath) shape).curveTo(56.039997, 206.94398, 56.107, 206.94798,
                56.156998, 206.94798);
        ((GeneralPath) shape).curveTo(56.534996, 206.94798, 56.776997,
                206.61998, 56.878, 205.95798);
        ((GeneralPath) shape).lineTo(56.421997, 205.95798);
        ((GeneralPath) shape).lineTo(55.201996, 202.22398);
        ((GeneralPath) shape).lineTo(56.160995, 202.22398);
        ((GeneralPath) shape).lineTo(56.628994, 203.80598);
        ((GeneralPath) shape).lineTo(56.862995, 204.59898);
        ((GeneralPath) shape).curveTo(56.874996, 204.64998, 56.911995,
                204.78299, 56.971996, 204.99698);
        ((GeneralPath) shape).lineTo(57.082996, 205.39198);
        ((GeneralPath) shape).lineTo(57.103996, 205.39198);
        ((GeneralPath) shape).lineTo(57.185997, 204.99698);
        ((GeneralPath) shape).curveTo(57.226997, 204.79398, 57.252, 204.66098,
                57.267998, 204.59898);
        ((GeneralPath) shape).lineTo(57.450996, 203.80598);
        ((GeneralPath) shape).lineTo(57.809, 202.22398);
        ((GeneralPath) shape).lineTo(58.746998, 202.22398);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_142;
        g.setTransform(defaultTransform__0_142);
        g.setClip(clip__0_142);
        float alpha__0_143 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_143 = g.getClip();
        AffineTransform defaultTransform__0_143 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_143 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(60.916, 202.88);
        ((GeneralPath) shape).curveTo(60.577, 202.88, 60.354, 202.95601,
                60.249, 203.10901);
        ((GeneralPath) shape).curveTo(60.145, 203.261, 60.093002, 203.58801,
                60.093002, 204.08801);
        ((GeneralPath) shape).curveTo(60.093002, 204.57, 60.149002, 204.893,
                60.260002, 205.05301);
        ((GeneralPath) shape).curveTo(60.371002, 205.21101, 60.601, 205.29102,
                60.945004, 205.29102);
        ((GeneralPath) shape).curveTo(61.292004, 205.29102, 61.518005,
                205.21701, 61.623005, 205.06201);
        ((GeneralPath) shape).curveTo(61.727005, 204.91402, 61.779003, 204.587,
                61.779003, 204.09001);
        ((GeneralPath) shape).curveTo(61.779003, 203.58002, 61.726, 203.25002,
                61.621002, 203.10101);
        ((GeneralPath) shape).curveTo(61.514, 202.954, 61.28, 202.88, 60.916,
                202.88);
        ((GeneralPath) shape).moveTo(59.158, 202.22);
        ((GeneralPath) shape).lineTo(60.066, 202.22);
        ((GeneralPath) shape).lineTo(60.031002, 202.775);
        ((GeneralPath) shape).lineTo(60.051003, 202.77899);
        ((GeneralPath) shape).curveTo(60.267002, 202.379, 60.667004, 202.17499,
                61.256004, 202.17499);
        ((GeneralPath) shape).curveTo(61.798004, 202.17499, 62.172005,
                202.30998, 62.377003, 202.579);
        ((GeneralPath) shape).curveTo(62.58, 202.849, 62.685, 203.33899,
                62.685, 204.054);
        ((GeneralPath) shape).curveTo(62.685, 204.798, 62.582, 205.308, 62.379,
                205.585);
        ((GeneralPath) shape).curveTo(62.176003, 205.858, 61.804, 205.99901,
                61.253002, 205.99901);
        ((GeneralPath) shape).curveTo(60.669003, 205.99901, 60.286003, 205.804,
                60.107002, 205.414);
        ((GeneralPath) shape).lineTo(60.09, 205.414);
        ((GeneralPath) shape).lineTo(60.09, 207.558);
        ((GeneralPath) shape).lineTo(59.158, 207.558);
        ((GeneralPath) shape).lineTo(59.158, 202.22);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_143;
        g.setTransform(defaultTransform__0_143);
        g.setClip(clip__0_143);
        float alpha__0_144 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_144 = g.getClip();
        AffineTransform defaultTransform__0_144 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_144 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(65.84, 203.701);
        ((GeneralPath) shape).lineTo(65.836, 203.552);
        ((GeneralPath) shape).curveTo(65.836, 203.256, 65.785995, 203.064,
                65.684, 202.974);
        ((GeneralPath) shape).curveTo(65.58, 202.886, 65.359, 202.842, 65.016,
                202.842);
        ((GeneralPath) shape).curveTo(64.684, 202.842, 64.465996, 202.894,
                64.367, 203.002);
        ((GeneralPath) shape).curveTo(64.268, 203.106, 64.216995, 203.342,
                64.216995, 203.702);
        ((GeneralPath) shape).lineTo(65.84, 203.701);
        ((GeneralPath) shape).moveTo(65.832, 204.759);
        ((GeneralPath) shape).lineTo(66.729004, 204.759);
        ((GeneralPath) shape).lineTo(66.729004, 204.904);
        ((GeneralPath) shape).curveTo(66.729004, 205.63301, 66.18301, 206.0,
                65.090004, 206.0);
        ((GeneralPath) shape).curveTo(64.351006, 206.0, 63.865005, 205.875,
                63.637005, 205.621);
        ((GeneralPath) shape).curveTo(63.408005, 205.369, 63.292004, 204.834,
                63.292004, 204.016);
        ((GeneralPath) shape).curveTo(63.292004, 203.29001, 63.411003,
                202.80101, 63.651005, 202.55101);
        ((GeneralPath) shape).curveTo(63.889004, 202.30101, 64.357, 202.17601,
                65.051, 202.17601);
        ((GeneralPath) shape).curveTo(65.714005, 202.17601, 66.16, 202.29701,
                66.388, 202.54102);
        ((GeneralPath) shape).curveTo(66.614, 202.78102, 66.727, 203.26201,
                66.727, 203.97702);
        ((GeneralPath) shape).lineTo(66.727, 204.25002);
        ((GeneralPath) shape).lineTo(64.204994, 204.25002);
        ((GeneralPath) shape).curveTo(64.201, 204.33202, 64.19699, 204.38701,
                64.19699, 204.41501);
        ((GeneralPath) shape).curveTo(64.19699, 204.78001, 64.25299, 205.024,
                64.36599, 205.147);
        ((GeneralPath) shape).curveTo(64.47899, 205.268, 64.70299, 205.332,
                65.04199, 205.332);
        ((GeneralPath) shape).curveTo(65.369995, 205.332, 65.58199, 205.297,
                65.68399, 205.225);
        ((GeneralPath) shape).curveTo(65.781, 205.151, 65.832, 204.997, 65.832,
                204.759);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_144;
        g.setTransform(defaultTransform__0_144);
        g.setClip(clip__0_144);
        float alpha__0_145 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_145 = g.getClip();
        AffineTransform defaultTransform__0_145 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_145 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(137.439, 200.622);
        ((GeneralPath) shape).lineTo(137.439, 205.048);
        ((GeneralPath) shape).lineTo(139.934, 205.048);
        ((GeneralPath) shape).lineTo(139.934, 205.954);
        ((GeneralPath) shape).lineTo(136.429, 205.954);
        ((GeneralPath) shape).lineTo(136.429, 200.622);
        ((GeneralPath) shape).lineTo(137.439, 200.622);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_145;
        g.setTransform(defaultTransform__0_145);
        g.setClip(clip__0_145);
        float alpha__0_146 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_146 = g.getClip();
        AffineTransform defaultTransform__0_146 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_146 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(142.049, 202.884);
        ((GeneralPath) shape).curveTo(141.681, 202.884, 141.443, 202.951,
                141.34, 203.085);
        ((GeneralPath) shape).curveTo(141.239, 203.218, 141.19, 203.53001,
                141.19, 204.02301);
        ((GeneralPath) shape).curveTo(141.19, 204.58601, 141.237, 204.93701,
                141.335, 205.08002);
        ((GeneralPath) shape).curveTo(141.432, 205.22102, 141.67001, 205.29102,
                142.05801, 205.29102);
        ((GeneralPath) shape).curveTo(142.42702, 205.29102, 142.66202,
                205.21701, 142.76001, 205.06601);
        ((GeneralPath) shape).curveTo(142.86002, 204.92001, 142.906, 204.56201,
                142.906, 203.99802);
        ((GeneralPath) shape).curveTo(142.906, 203.52002, 142.85501, 203.21701,
                142.75401, 203.08401);
        ((GeneralPath) shape).curveTo(142.649, 202.951, 142.416, 202.884,
                142.049, 202.884);
        ((GeneralPath) shape).moveTo(142.057, 202.177);
        ((GeneralPath) shape).curveTo(142.776, 202.177, 143.246, 202.292,
                143.472, 202.523);
        ((GeneralPath) shape).curveTo(143.694, 202.75299, 143.807, 203.23999,
                143.807, 203.982);
        ((GeneralPath) shape).curveTo(143.807, 204.81, 143.69801, 205.353,
                143.48001, 205.61);
        ((GeneralPath) shape).curveTo(143.26201, 205.869, 142.79901, 205.99901,
                142.1, 205.99901);
        ((GeneralPath) shape).curveTo(141.33801, 205.99901, 140.847, 205.88,
                140.621, 205.64001);
        ((GeneralPath) shape).curveTo(140.397, 205.40201, 140.284, 204.87401,
                140.284, 204.05801);
        ((GeneralPath) shape).curveTo(140.284, 203.27501, 140.39299, 202.76501,
                140.616, 202.53102);
        ((GeneralPath) shape).curveTo(140.835, 202.294, 141.316, 202.177,
                142.057, 202.177);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_146;
        g.setTransform(defaultTransform__0_146);
        g.setClip(clip__0_146);
        float alpha__0_147 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_147 = g.getClip();
        AffineTransform defaultTransform__0_147 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_147 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(146.931, 204.528);
        ((GeneralPath) shape).lineTo(147.82, 204.528);
        ((GeneralPath) shape).lineTo(147.82, 204.657);
        ((GeneralPath) shape).lineTo(147.76501, 205.22299);
        ((GeneralPath) shape).curveTo(147.65602, 205.739, 147.12201, 205.99599,
                146.16402, 205.99599);
        ((GeneralPath) shape).curveTo(145.46202, 205.99599, 144.99701,
                205.86899, 144.76701, 205.61299);
        ((GeneralPath) shape).curveTo(144.53702, 205.359, 144.41801, 204.84898,
                144.41801, 204.08199);
        ((GeneralPath) shape).curveTo(144.41801, 203.33199, 144.53302,
                202.82799, 144.76701, 202.56398);
        ((GeneralPath) shape).curveTo(144.99602, 202.30399, 145.44801,
                202.17297, 146.11601, 202.17297);
        ((GeneralPath) shape).curveTo(146.76201, 202.17297, 147.19601,
                202.26697, 147.42902, 202.45598);
        ((GeneralPath) shape).curveTo(147.65701, 202.64398, 147.77202,
                203.00298, 147.77202, 203.53297);
        ((GeneralPath) shape).lineTo(146.88702, 203.53297);
        ((GeneralPath) shape).curveTo(146.88702, 203.09697, 146.63702,
                202.87997, 146.13202, 202.87997);
        ((GeneralPath) shape).curveTo(145.77802, 202.87997, 145.55502,
                202.94597, 145.46202, 203.08797);
        ((GeneralPath) shape).curveTo(145.37302, 203.22397, 145.32602,
                203.56396, 145.32602, 204.10397);
        ((GeneralPath) shape).curveTo(145.32602, 204.62497, 145.37402,
                204.95297, 145.47601, 205.08597);
        ((GeneralPath) shape).curveTo(145.57602, 205.21896, 145.82101,
                205.28697, 146.20901, 205.28697);
        ((GeneralPath) shape).curveTo(146.51701, 205.28697, 146.71202,
                205.24197, 146.80202, 205.14597);
        ((GeneralPath) shape).curveTo(146.886, 205.06, 146.931, 204.853,
                146.931, 204.528);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_147;
        g.setTransform(defaultTransform__0_147);
        g.setClip(clip__0_147);
        float alpha__0_148 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_148 = g.getClip();
        AffineTransform defaultTransform__0_148 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_148 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(155.313, 205.103);
        ((GeneralPath) shape).lineTo(156.751, 205.103);
        ((GeneralPath) shape).curveTo(157.23401, 205.103, 157.546, 204.994,
                157.68901, 204.769);
        ((GeneralPath) shape).curveTo(157.82901, 204.54599, 157.90102, 204.056,
                157.90102, 203.29399);
        ((GeneralPath) shape).curveTo(157.90102, 202.50899, 157.83801,
                202.00899, 157.71002, 201.79399);
        ((GeneralPath) shape).curveTo(157.58302, 201.581, 157.28502, 201.47398,
                156.81403, 201.47398);
        ((GeneralPath) shape).lineTo(155.31403, 201.47398);
        ((GeneralPath) shape).lineTo(155.313, 205.103);
        ((GeneralPath) shape).moveTo(154.303, 205.954);
        ((GeneralPath) shape).lineTo(154.303, 200.622);
        ((GeneralPath) shape).lineTo(156.91699, 200.622);
        ((GeneralPath) shape).curveTo(157.65999, 200.622, 158.18199, 200.784,
                158.48, 201.10799);
        ((GeneralPath) shape).curveTo(158.776, 201.43298, 158.924, 202.00398,
                158.924, 202.81898);
        ((GeneralPath) shape).curveTo(158.924, 204.14899, 158.80699, 205.00699,
                158.567, 205.38298);
        ((GeneralPath) shape).curveTo(158.329, 205.76198, 157.789, 205.95097,
                156.949, 205.95097);
        ((GeneralPath) shape).lineTo(154.303, 205.954);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_148;
        g.setTransform(defaultTransform__0_148);
        g.setClip(clip__0_148);
        float alpha__0_149 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_149 = g.getClip();
        AffineTransform defaultTransform__0_149 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_149 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(159.626, 202.22);
        ((GeneralPath) shape).lineTo(160.52101, 202.22);
        ((GeneralPath) shape).lineTo(160.49802, 202.794);
        ((GeneralPath) shape).lineTo(160.51901, 202.798);
        ((GeneralPath) shape).curveTo(160.70001, 202.384, 161.08002, 202.177,
                161.65701, 202.177);
        ((GeneralPath) shape).curveTo(162.33002, 202.177, 162.70401, 202.406,
                162.78001, 202.868);
        ((GeneralPath) shape).lineTo(162.79602, 202.868);
        ((GeneralPath) shape).curveTo(162.97102, 202.405, 163.35602, 202.177,
                163.94601, 202.177);
        ((GeneralPath) shape).curveTo(164.807, 202.177, 165.24002, 202.61101,
                165.24002, 203.482);
        ((GeneralPath) shape).lineTo(165.24002, 205.956);
        ((GeneralPath) shape).lineTo(164.34702, 205.956);
        ((GeneralPath) shape).lineTo(164.34702, 203.679);
        ((GeneralPath) shape).curveTo(164.34702, 203.15201, 164.12901, 202.886,
                163.69601, 202.886);
        ((GeneralPath) shape).curveTo(163.154, 202.886, 162.88101, 203.181,
                162.88101, 203.773);
        ((GeneralPath) shape).lineTo(162.88101, 205.958);
        ((GeneralPath) shape).lineTo(161.988, 205.958);
        ((GeneralPath) shape).lineTo(161.988, 203.64499);
        ((GeneralPath) shape).curveTo(161.988, 203.33598, 161.947, 203.13098,
                161.865, 203.034);
        ((GeneralPath) shape).curveTo(161.783, 202.937, 161.61, 202.888,
                161.34401, 202.888);
        ((GeneralPath) shape).curveTo(160.796, 202.888, 160.52301, 203.189,
                160.52301, 203.794);
        ((GeneralPath) shape).lineTo(160.52301, 205.95801);
        ((GeneralPath) shape).lineTo(159.628, 205.95801);
        ((GeneralPath) shape).lineTo(159.626, 202.22);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_149;
        g.setTransform(defaultTransform__0_149);
        g.setClip(clip__0_149);
        float alpha__0_150 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_150 = g.getClip();
        AffineTransform defaultTransform__0_150 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_150 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(167.726, 202.884);
        ((GeneralPath) shape).curveTo(167.381, 202.884, 167.163, 202.952,
                167.066, 203.093);
        ((GeneralPath) shape).curveTo(166.96599, 203.23201, 166.92, 203.548,
                166.92, 204.044);
        ((GeneralPath) shape).curveTo(166.92, 204.567, 166.971, 204.903,
                167.066, 205.052);
        ((GeneralPath) shape).curveTo(167.163, 205.197, 167.387, 205.271,
                167.73799, 205.271);
        ((GeneralPath) shape).curveTo(168.08899, 205.271, 168.31499, 205.196,
                168.42, 205.04599);
        ((GeneralPath) shape).curveTo(168.524, 204.898, 168.576, 204.566,
                168.576, 204.05199);
        ((GeneralPath) shape).curveTo(168.576, 203.55998, 168.52501, 203.24298,
                168.42, 203.09898);
        ((GeneralPath) shape).curveTo(168.317, 202.956, 168.085, 202.884,
                167.726, 202.884);
        ((GeneralPath) shape).moveTo(169.485, 202.22);
        ((GeneralPath) shape).lineTo(169.485, 206.025);
        ((GeneralPath) shape).curveTo(169.485, 206.63399, 169.362, 207.05199,
                169.116, 207.27899);
        ((GeneralPath) shape).curveTo(168.87, 207.508, 168.418, 207.62099,
                167.758, 207.62099);
        ((GeneralPath) shape).curveTo(167.11899, 207.62099, 166.694, 207.53299,
                166.47699, 207.361);
        ((GeneralPath) shape).curveTo(166.26299, 207.189, 166.15298, 206.849,
                166.15298, 206.342);
        ((GeneralPath) shape).lineTo(167.01799, 206.342);
        ((GeneralPath) shape).curveTo(167.01799, 206.584, 167.06299, 206.73799,
                167.15498, 206.808);
        ((GeneralPath) shape).curveTo(167.24498, 206.87599, 167.44699, 206.912,
                167.76299, 206.912);
        ((GeneralPath) shape).curveTo(168.31598, 206.912, 168.59198, 206.683,
                168.59198, 206.224);
        ((GeneralPath) shape).lineTo(168.59198, 205.384);
        ((GeneralPath) shape).lineTo(168.57198, 205.381);
        ((GeneralPath) shape).curveTo(168.41397, 205.777, 168.04997, 205.978,
                167.48097, 205.978);
        ((GeneralPath) shape).curveTo(166.91797, 205.978, 166.53098, 205.842,
                166.32497, 205.568);
        ((GeneralPath) shape).curveTo(166.11797, 205.297, 166.01497, 204.78699,
                166.01497, 204.03899);
        ((GeneralPath) shape).curveTo(166.01497, 203.33499, 166.11797,
                202.85098, 166.32497, 202.581);
        ((GeneralPath) shape).curveTo(166.53096, 202.31099, 166.90596, 202.179,
                167.44997, 202.179);
        ((GeneralPath) shape).curveTo(168.04196, 202.179, 168.42996, 202.398,
                168.61597, 202.839);
        ((GeneralPath) shape).lineTo(168.63496, 202.839);
        ((GeneralPath) shape).lineTo(168.59296, 202.222);
        ((GeneralPath) shape).lineTo(169.485, 202.22);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_150;
        g.setTransform(defaultTransform__0_150);
        g.setClip(clip__0_150);
        float alpha__0_151 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_151 = g.getClip();
        AffineTransform defaultTransform__0_151 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_151 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(182.024, 200.622);
        ((GeneralPath) shape).lineTo(182.024, 205.954);
        ((GeneralPath) shape).lineTo(181.014, 205.954);
        ((GeneralPath) shape).lineTo(181.014, 203.04799);
        ((GeneralPath) shape).curveTo(181.014, 202.818, 181.02, 202.55399,
                181.03401, 202.25899);
        ((GeneralPath) shape).lineTo(181.05501, 201.861);
        ((GeneralPath) shape).lineTo(181.07501, 201.467);
        ((GeneralPath) shape).lineTo(181.044, 201.467);
        ((GeneralPath) shape).lineTo(180.923, 201.838);
        ((GeneralPath) shape).lineTo(180.808, 202.209);
        ((GeneralPath) shape).curveTo(180.699, 202.541, 180.615, 202.788,
                180.555, 202.94499);
        ((GeneralPath) shape).lineTo(179.383, 205.95499);
        ((GeneralPath) shape).lineTo(178.463, 205.95499);
        ((GeneralPath) shape).lineTo(177.282, 202.971);
        ((GeneralPath) shape).curveTo(177.218, 202.80699, 177.132, 202.56099,
                177.025, 202.23299);
        ((GeneralPath) shape).lineTo(176.90399, 201.861);
        ((GeneralPath) shape).lineTo(176.78299, 201.49599);
        ((GeneralPath) shape).lineTo(176.75198, 201.49599);
        ((GeneralPath) shape).lineTo(176.77298, 201.88298);
        ((GeneralPath) shape).lineTo(176.79298, 202.27399);
        ((GeneralPath) shape).curveTo(176.80998, 202.57399, 176.81598,
                202.83598, 176.81598, 203.05098);
        ((GeneralPath) shape).lineTo(176.81598, 205.95699);
        ((GeneralPath) shape).lineTo(175.80598, 205.95699);
        ((GeneralPath) shape).lineTo(175.80598, 200.62498);
        ((GeneralPath) shape).lineTo(177.45099, 200.62498);
        ((GeneralPath) shape).lineTo(178.402, 203.09398);
        ((GeneralPath) shape).curveTo(178.465, 203.26498, 178.54999, 203.51099,
                178.659, 203.83199);
        ((GeneralPath) shape).lineTo(178.776, 204.20398);
        ((GeneralPath) shape).lineTo(178.897, 204.56898);
        ((GeneralPath) shape).lineTo(178.932, 204.56898);
        ((GeneralPath) shape).lineTo(179.043, 204.20398);
        ((GeneralPath) shape).lineTo(179.161, 203.83598);
        ((GeneralPath) shape).curveTo(179.256, 203.52498, 179.34, 203.27898,
                179.411, 203.10199);
        ((GeneralPath) shape).lineTo(180.34799, 200.62498);
        ((GeneralPath) shape).lineTo(182.024, 200.622);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_151;
        g.setTransform(defaultTransform__0_151);
        g.setClip(clip__0_151);
        float alpha__0_152 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_152 = g.getClip();
        AffineTransform defaultTransform__0_152 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_152 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(183.819, 202.22);
        ((GeneralPath) shape).lineTo(183.819, 205.954);
        ((GeneralPath) shape).lineTo(182.924, 205.954);
        ((GeneralPath) shape).lineTo(182.924, 202.22);
        ((GeneralPath) shape).lineTo(183.819, 202.22);
        ((GeneralPath) shape).moveTo(183.819, 200.622);
        ((GeneralPath) shape).lineTo(183.819, 201.368);
        ((GeneralPath) shape).lineTo(182.924, 201.368);
        ((GeneralPath) shape).lineTo(182.924, 200.622);
        ((GeneralPath) shape).lineTo(183.819, 200.622);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_152;
        g.setTransform(defaultTransform__0_152);
        g.setClip(clip__0_152);
        float alpha__0_153 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_153 = g.getClip();
        AffineTransform defaultTransform__0_153 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_153 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(184.697, 202.22);
        ((GeneralPath) shape).lineTo(185.582, 202.22);
        ((GeneralPath) shape).lineTo(185.547, 202.849);
        ((GeneralPath) shape).lineTo(185.568, 202.853);
        ((GeneralPath) shape).curveTo(185.741, 202.40399, 186.131, 202.177,
                186.737, 202.177);
        ((GeneralPath) shape).curveTo(187.618, 202.177, 188.059, 202.587,
                188.059, 203.412);
        ((GeneralPath) shape).lineTo(188.059, 205.95401);
        ((GeneralPath) shape).lineTo(187.164, 205.95401);
        ((GeneralPath) shape).lineTo(187.164, 203.561);
        ((GeneralPath) shape).lineTo(187.144, 203.29901);
        ((GeneralPath) shape).curveTo(187.103, 203.02202, 186.885, 202.88101,
                186.489, 202.88101);
        ((GeneralPath) shape).curveTo(185.88899, 202.88101, 185.589, 203.16602,
                185.589, 203.73502);
        ((GeneralPath) shape).lineTo(185.589, 205.95102);
        ((GeneralPath) shape).lineTo(184.696, 205.95102);
        ((GeneralPath) shape).lineTo(184.696, 202.22);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_153;
        g.setTransform(defaultTransform__0_153);
        g.setClip(clip__0_153);
        float alpha__0_154 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_154 = g.getClip();
        AffineTransform defaultTransform__0_154 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_154 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(195.593, 202.177);
        ((GeneralPath) shape).lineTo(194.60901, 202.177);
        ((GeneralPath) shape).curveTo(194.60501, 202.128, 194.60101, 202.092,
                194.60101, 202.06801);
        ((GeneralPath) shape).curveTo(194.57802, 201.76901, 194.50902,
                201.58401, 194.40201, 201.505);
        ((GeneralPath) shape).curveTo(194.29301, 201.429, 194.035, 201.39,
                193.63, 201.39);
        ((GeneralPath) shape).curveTo(193.15201, 201.39, 192.84001, 201.433,
                192.692, 201.523);
        ((GeneralPath) shape).curveTo(192.546, 201.61, 192.472, 201.79599,
                192.472, 202.083);
        ((GeneralPath) shape).curveTo(192.472, 202.41899, 192.531, 202.62,
                192.651, 202.687);
        ((GeneralPath) shape).curveTo(192.77, 202.75299, 193.166, 202.806,
                193.836, 202.843);
        ((GeneralPath) shape).curveTo(194.628, 202.88501, 195.142, 202.99901,
                195.374, 203.182);
        ((GeneralPath) shape).curveTo(195.60599, 203.36401, 195.72499,
                203.74501, 195.72499, 204.319);
        ((GeneralPath) shape).curveTo(195.72499, 205.026, 195.58598, 205.485,
                195.31499, 205.692);
        ((GeneralPath) shape).curveTo(195.04298, 205.899, 194.441, 206.005,
                193.50798, 206.005);
        ((GeneralPath) shape).curveTo(192.67198, 206.005, 192.11598, 205.901,
                191.84097, 205.698);
        ((GeneralPath) shape).curveTo(191.56998, 205.497, 191.42998, 205.087,
                191.42998, 204.466);
        ((GeneralPath) shape).lineTo(191.42598, 204.271);
        ((GeneralPath) shape).lineTo(192.40797, 204.271);
        ((GeneralPath) shape).lineTo(192.41197, 204.385);
        ((GeneralPath) shape).curveTo(192.41197, 204.756, 192.47597, 204.984,
                192.60698, 205.068);
        ((GeneralPath) shape).curveTo(192.73598, 205.15099, 193.08798, 205.193,
                193.66998, 205.193);
        ((GeneralPath) shape).curveTo(194.12198, 205.193, 194.41098, 205.146,
                194.53499, 205.04799);
        ((GeneralPath) shape).curveTo(194.65999, 204.952, 194.72299, 204.73,
                194.72299, 204.37999);
        ((GeneralPath) shape).curveTo(194.72299, 204.122, 194.674, 203.952,
                194.57799, 203.86398);
        ((GeneralPath) shape).curveTo(194.48499, 203.77998, 194.27998,
                203.72899, 193.96399, 203.71199);
        ((GeneralPath) shape).lineTo(193.40599, 203.67699);
        ((GeneralPath) shape).curveTo(192.564, 203.62799, 192.02599, 203.51099,
                191.79199, 203.32298);
        ((GeneralPath) shape).curveTo(191.56, 203.13898, 191.43999, 202.74098,
                191.43999, 202.13399);
        ((GeneralPath) shape).curveTo(191.43999, 201.512, 191.581, 201.096,
                191.86299, 200.88899);
        ((GeneralPath) shape).curveTo(192.144, 200.68199, 192.70699, 200.579,
                193.553, 200.579);
        ((GeneralPath) shape).curveTo(194.35199, 200.579, 194.892, 200.672,
                195.16899, 200.862);
        ((GeneralPath) shape).curveTo(195.44598, 201.051, 195.586, 201.425,
                195.586, 201.975);
        ((GeneralPath) shape).lineTo(195.586, 202.17801);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_154;
        g.setTransform(defaultTransform__0_154);
        g.setClip(clip__0_154);
        float alpha__0_155 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_155 = g.getClip();
        AffineTransform defaultTransform__0_155 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_155 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(197.298, 200.622);
        ((GeneralPath) shape).lineTo(197.298, 202.83699);
        ((GeneralPath) shape).lineTo(197.319, 202.84099);
        ((GeneralPath) shape).curveTo(197.485, 202.39899, 197.867, 202.176,
                198.465, 202.176);
        ((GeneralPath) shape).curveTo(199.33499, 202.176, 199.773, 202.588,
                199.773, 203.414);
        ((GeneralPath) shape).lineTo(199.773, 205.953);
        ((GeneralPath) shape).lineTo(198.87799, 205.953);
        ((GeneralPath) shape).lineTo(198.87799, 203.539);
        ((GeneralPath) shape).curveTo(198.87799, 203.101, 198.65599, 202.883,
                198.21098, 202.883);
        ((GeneralPath) shape).curveTo(197.60498, 202.883, 197.29898, 203.2,
                197.29898, 203.832);
        ((GeneralPath) shape).lineTo(197.29898, 205.953);
        ((GeneralPath) shape).lineTo(196.40598, 205.953);
        ((GeneralPath) shape).lineTo(196.40598, 200.621);
        ((GeneralPath) shape).lineTo(197.298, 200.622);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_155;
        g.setTransform(defaultTransform__0_155);
        g.setClip(clip__0_155);
        float alpha__0_156 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_156 = g.getClip();
        AffineTransform defaultTransform__0_156 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_156 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(203.047, 202.22);
        ((GeneralPath) shape).lineTo(203.047, 202.9);
        ((GeneralPath) shape).lineTo(201.612, 202.9);
        ((GeneralPath) shape).lineTo(201.612, 204.775);
        ((GeneralPath) shape).curveTo(201.612, 205.123, 201.743, 205.29599,
                202.006, 205.29599);
        ((GeneralPath) shape).curveTo(202.295, 205.29599, 202.441, 205.08699,
                202.441, 204.66699);
        ((GeneralPath) shape).lineTo(202.441, 204.519);
        ((GeneralPath) shape).lineTo(203.20099, 204.519);
        ((GeneralPath) shape).lineTo(203.20099, 204.707);
        ((GeneralPath) shape).curveTo(203.20099, 204.879, 203.19798, 205.023,
                203.18498, 205.147);
        ((GeneralPath) shape).curveTo(203.13498, 205.71701, 202.71098, 206.002,
                201.91399, 206.002);
        ((GeneralPath) shape).curveTo(201.11899, 206.002, 200.72299, 205.637,
                200.72299, 204.902);
        ((GeneralPath) shape).lineTo(200.72299, 202.9);
        ((GeneralPath) shape).lineTo(200.23999, 202.9);
        ((GeneralPath) shape).lineTo(200.23999, 202.22);
        ((GeneralPath) shape).lineTo(200.72299, 202.22);
        ((GeneralPath) shape).lineTo(200.72299, 201.384);
        ((GeneralPath) shape).lineTo(201.616, 201.384);
        ((GeneralPath) shape).lineTo(201.616, 202.22);
        ((GeneralPath) shape).lineTo(203.047, 202.22);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_156;
        g.setTransform(defaultTransform__0_156);
        g.setClip(clip__0_156);
        float alpha__0_157 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_157 = g.getClip();
        AffineTransform defaultTransform__0_157 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_157 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(212.837, 200.622);
        ((GeneralPath) shape).lineTo(212.837, 205.954);
        ((GeneralPath) shape).lineTo(211.828, 205.954);
        ((GeneralPath) shape).lineTo(211.828, 203.04799);
        ((GeneralPath) shape).curveTo(211.828, 202.818, 211.83301, 202.55399,
                211.849, 202.25899);
        ((GeneralPath) shape).lineTo(211.869, 201.861);
        ((GeneralPath) shape).lineTo(211.89, 201.467);
        ((GeneralPath) shape).lineTo(211.859, 201.467);
        ((GeneralPath) shape).lineTo(211.73799, 201.838);
        ((GeneralPath) shape).lineTo(211.622, 202.209);
        ((GeneralPath) shape).curveTo(211.51399, 202.541, 211.43, 202.788,
                211.368, 202.94499);
        ((GeneralPath) shape).lineTo(210.19899, 205.95499);
        ((GeneralPath) shape).lineTo(209.27899, 205.95499);
        ((GeneralPath) shape).lineTo(208.096, 202.971);
        ((GeneralPath) shape).curveTo(208.03299, 202.80699, 207.94699,
                202.56099, 207.838, 202.23299);
        ((GeneralPath) shape).lineTo(207.717, 201.861);
        ((GeneralPath) shape).lineTo(207.597, 201.49599);
        ((GeneralPath) shape).lineTo(207.567, 201.49599);
        ((GeneralPath) shape).lineTo(207.587, 201.88298);
        ((GeneralPath) shape).lineTo(207.608, 202.27399);
        ((GeneralPath) shape).curveTo(207.623, 202.57399, 207.629, 202.83598,
                207.629, 203.05098);
        ((GeneralPath) shape).lineTo(207.629, 205.95699);
        ((GeneralPath) shape).lineTo(206.619, 205.95699);
        ((GeneralPath) shape).lineTo(206.619, 200.62498);
        ((GeneralPath) shape).lineTo(208.265, 200.62498);
        ((GeneralPath) shape).lineTo(209.214, 203.09398);
        ((GeneralPath) shape).curveTo(209.278, 203.26498, 209.364, 203.51099,
                209.473, 203.83199);
        ((GeneralPath) shape).lineTo(209.58801, 204.20398);
        ((GeneralPath) shape).lineTo(209.70901, 204.56898);
        ((GeneralPath) shape).lineTo(209.74402, 204.56898);
        ((GeneralPath) shape).lineTo(209.85703, 204.20398);
        ((GeneralPath) shape).lineTo(209.97403, 203.83598);
        ((GeneralPath) shape).curveTo(210.07002, 203.52498, 210.15303,
                203.27898, 210.22403, 203.10199);
        ((GeneralPath) shape).lineTo(211.16003, 200.62498);
        ((GeneralPath) shape).lineTo(212.837, 200.622);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_157;
        g.setTransform(defaultTransform__0_157);
        g.setClip(clip__0_157);
        float alpha__0_158 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_158 = g.getClip();
        AffineTransform defaultTransform__0_158 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_158 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(216.149, 203.701);
        ((GeneralPath) shape).lineTo(216.145, 203.552);
        ((GeneralPath) shape).curveTo(216.145, 203.256, 216.095, 203.064,
                215.991, 202.974);
        ((GeneralPath) shape).curveTo(215.88899, 202.886, 215.666, 202.842,
                215.323, 202.842);
        ((GeneralPath) shape).curveTo(214.991, 202.842, 214.775, 202.894,
                214.674, 203.002);
        ((GeneralPath) shape).curveTo(214.575, 203.106, 214.526, 203.342,
                214.526, 203.702);
        ((GeneralPath) shape).lineTo(216.149, 203.701);
        ((GeneralPath) shape).moveTo(216.141, 204.759);
        ((GeneralPath) shape).lineTo(217.037, 204.759);
        ((GeneralPath) shape).lineTo(217.037, 204.904);
        ((GeneralPath) shape).curveTo(217.037, 205.63301, 216.491, 206.0,
                215.40001, 206.0);
        ((GeneralPath) shape).curveTo(214.65901, 206.0, 214.173, 205.875,
                213.945, 205.621);
        ((GeneralPath) shape).curveTo(213.716, 205.369, 213.602, 204.834,
                213.602, 204.016);
        ((GeneralPath) shape).curveTo(213.602, 203.29001, 213.72101, 202.80101,
                213.95801, 202.55101);
        ((GeneralPath) shape).curveTo(214.19601, 202.30101, 214.664, 202.17601,
                215.358, 202.17601);
        ((GeneralPath) shape).curveTo(216.023, 202.17601, 216.469, 202.29701,
                216.695, 202.54102);
        ((GeneralPath) shape).curveTo(216.921, 202.78102, 217.03401, 203.26201,
                217.03401, 203.97702);
        ((GeneralPath) shape).lineTo(217.03401, 204.25002);
        ((GeneralPath) shape).lineTo(214.51302, 204.25002);
        ((GeneralPath) shape).curveTo(214.50902, 204.33202, 214.50302,
                204.38701, 214.50302, 204.41501);
        ((GeneralPath) shape).curveTo(214.50302, 204.78001, 214.56203, 205.024,
                214.67302, 205.147);
        ((GeneralPath) shape).curveTo(214.78502, 205.268, 215.01102, 205.332,
                215.35002, 205.332);
        ((GeneralPath) shape).curveTo(215.67802, 205.332, 215.89102, 205.297,
                215.99002, 205.225);
        ((GeneralPath) shape).curveTo(216.08902, 205.15302, 216.141, 204.997,
                216.141, 204.759);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_158;
        g.setTransform(defaultTransform__0_158);
        g.setClip(clip__0_158);
        float alpha__0_159 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_159 = g.getClip();
        AffineTransform defaultTransform__0_159 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_159 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(219.345, 202.884);
        ((GeneralPath) shape).curveTo(219.013, 202.884, 218.795, 202.95401,
                218.697, 203.097);
        ((GeneralPath) shape).curveTo(218.6, 203.238, 218.54901, 203.544,
                218.54901, 204.013);
        ((GeneralPath) shape).curveTo(218.54901, 204.554, 218.6, 204.904,
                218.697, 205.06);
        ((GeneralPath) shape).curveTo(218.794, 205.21599, 219.01701, 205.29399,
                219.36, 205.29399);
        ((GeneralPath) shape).curveTo(219.719, 205.29399, 219.947, 205.21799,
                220.048, 205.064);
        ((GeneralPath) shape).curveTo(220.149, 204.914, 220.198, 204.556,
                220.198, 204.00099);
        ((GeneralPath) shape).curveTo(220.198, 203.54399, 220.143, 203.243,
                220.032, 203.099);
        ((GeneralPath) shape).curveTo(219.924, 202.956, 219.694, 202.884,
                219.345, 202.884);
        ((GeneralPath) shape).moveTo(221.095, 200.622);
        ((GeneralPath) shape).lineTo(221.095, 205.954);
        ((GeneralPath) shape).lineTo(220.225, 205.954);
        ((GeneralPath) shape).lineTo(220.255, 205.34999);
        ((GeneralPath) shape).lineTo(220.239, 205.346);
        ((GeneralPath) shape).curveTo(220.065, 205.78, 219.685, 205.99799,
                219.09999, 205.99799);
        ((GeneralPath) shape).curveTo(218.512, 205.99799, 218.12, 205.861,
                217.92899, 205.59398);
        ((GeneralPath) shape).curveTo(217.73799, 205.32498, 217.64198,
                204.77798, 217.64198, 203.95097);
        ((GeneralPath) shape).curveTo(217.64198, 203.28497, 217.74399,
                202.82097, 217.94998, 202.55997);
        ((GeneralPath) shape).curveTo(218.15498, 202.30197, 218.51898,
                202.17096, 219.04498, 202.17096);
        ((GeneralPath) shape).curveTo(219.65298, 202.17096, 220.03098,
                202.35995, 220.18599, 202.74995);
        ((GeneralPath) shape).lineTo(220.202, 202.74596);
        ((GeneralPath) shape).lineTo(220.202, 200.61696);
        ((GeneralPath) shape).lineTo(221.095, 200.622);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_159;
        g.setTransform(defaultTransform__0_159);
        g.setClip(clip__0_159);
        float alpha__0_160 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_160 = g.getClip();
        AffineTransform defaultTransform__0_160 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_160 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(226.269, 200.622);
        ((GeneralPath) shape).lineTo(226.269, 205.048);
        ((GeneralPath) shape).lineTo(228.764, 205.048);
        ((GeneralPath) shape).lineTo(228.764, 205.954);
        ((GeneralPath) shape).lineTo(225.259, 205.954);
        ((GeneralPath) shape).lineTo(225.259, 200.622);
        ((GeneralPath) shape).lineTo(226.269, 200.622);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_160;
        g.setTransform(defaultTransform__0_160);
        g.setClip(clip__0_160);
        float alpha__0_161 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_161 = g.getClip();
        AffineTransform defaultTransform__0_161 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_161 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(229.247, 202.22);
        ((GeneralPath) shape).lineTo(230.13199, 202.22);
        ((GeneralPath) shape).lineTo(230.09898, 202.849);
        ((GeneralPath) shape).lineTo(230.11899, 202.853);
        ((GeneralPath) shape).curveTo(230.29199, 202.40399, 230.68199, 202.177,
                231.288, 202.177);
        ((GeneralPath) shape).curveTo(232.16899, 202.177, 232.61, 202.587,
                232.61, 203.412);
        ((GeneralPath) shape).lineTo(232.61, 205.95401);
        ((GeneralPath) shape).lineTo(231.715, 205.95401);
        ((GeneralPath) shape).lineTo(231.715, 203.561);
        ((GeneralPath) shape).lineTo(231.69499, 203.29901);
        ((GeneralPath) shape).curveTo(231.65399, 203.02202, 231.43599,
                202.88101, 231.04, 202.88101);
        ((GeneralPath) shape).curveTo(230.43999, 202.88101, 230.142, 203.16602,
                230.142, 203.73502);
        ((GeneralPath) shape).lineTo(230.142, 205.95102);
        ((GeneralPath) shape).lineTo(229.247, 205.95102);
        ((GeneralPath) shape).lineTo(229.247, 202.22);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_161;
        g.setTransform(defaultTransform__0_161);
        g.setClip(clip__0_161);
        float alpha__0_162 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_162 = g.getClip();
        AffineTransform defaultTransform__0_162 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_162 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(235.053, 202.884);
        ((GeneralPath) shape).curveTo(234.708, 202.884, 234.48999, 202.952,
                234.392, 203.093);
        ((GeneralPath) shape).curveTo(234.29199, 203.23201, 234.246, 203.548,
                234.246, 204.044);
        ((GeneralPath) shape).curveTo(234.246, 204.567, 234.295, 204.903,
                234.392, 205.052);
        ((GeneralPath) shape).curveTo(234.489, 205.197, 234.711, 205.271,
                235.064, 205.271);
        ((GeneralPath) shape).curveTo(235.415, 205.271, 235.64099, 205.196,
                235.746, 205.04599);
        ((GeneralPath) shape).curveTo(235.85, 204.898, 235.90201, 204.566,
                235.90201, 204.05199);
        ((GeneralPath) shape).curveTo(235.90201, 203.55998, 235.85101,
                203.24298, 235.746, 203.09898);
        ((GeneralPath) shape).curveTo(235.644, 202.956, 235.411, 202.884,
                235.053, 202.884);
        ((GeneralPath) shape).moveTo(236.811, 202.22);
        ((GeneralPath) shape).lineTo(236.811, 206.025);
        ((GeneralPath) shape).curveTo(236.811, 206.63399, 236.688, 207.05199,
                236.44301, 207.27899);
        ((GeneralPath) shape).curveTo(236.197, 207.508, 235.74501, 207.62099,
                235.08401, 207.62099);
        ((GeneralPath) shape).curveTo(234.445, 207.62099, 234.02002, 207.53299,
                233.80101, 207.361);
        ((GeneralPath) shape).curveTo(233.587, 207.189, 233.48001, 206.849,
                233.48001, 206.342);
        ((GeneralPath) shape).lineTo(234.34502, 206.342);
        ((GeneralPath) shape).curveTo(234.34502, 206.584, 234.39001, 206.73799,
                234.48102, 206.808);
        ((GeneralPath) shape).curveTo(234.57101, 206.87599, 234.77303, 206.912,
                235.08702, 206.912);
        ((GeneralPath) shape).curveTo(235.64302, 206.912, 235.91801, 206.683,
                235.91801, 206.224);
        ((GeneralPath) shape).lineTo(235.91801, 205.384);
        ((GeneralPath) shape).lineTo(235.89801, 205.381);
        ((GeneralPath) shape).curveTo(235.74, 205.777, 235.376, 205.978,
                234.80602, 205.978);
        ((GeneralPath) shape).curveTo(234.24301, 205.978, 233.85701, 205.842,
                233.65001, 205.568);
        ((GeneralPath) shape).curveTo(233.44301, 205.297, 233.33801, 204.78699,
                233.33801, 204.03899);
        ((GeneralPath) shape).curveTo(233.33801, 203.33499, 233.44202,
                202.85098, 233.65001, 202.581);
        ((GeneralPath) shape).curveTo(233.858, 202.311, 234.231, 202.179,
                234.77501, 202.179);
        ((GeneralPath) shape).curveTo(235.36801, 202.179, 235.75401, 202.398,
                235.94101, 202.839);
        ((GeneralPath) shape).lineTo(235.96, 202.839);
        ((GeneralPath) shape).lineTo(235.918, 202.222);
        ((GeneralPath) shape).lineTo(236.811, 202.22);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_162;
        g.setTransform(defaultTransform__0_162);
        g.setClip(clip__0_162);
        float alpha__0_163 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_163 = g.getClip();
        AffineTransform defaultTransform__0_163 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_163 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(151.262, 127.995);
        ((GeneralPath) shape).lineTo(151.262, 132.421);
        ((GeneralPath) shape).lineTo(150.252, 132.421);
        ((GeneralPath) shape).lineTo(150.252, 127.995);
        ((GeneralPath) shape).lineTo(148.716, 127.995);
        ((GeneralPath) shape).lineTo(148.716, 127.089);
        ((GeneralPath) shape).lineTo(152.852, 127.089);
        ((GeneralPath) shape).lineTo(152.852, 127.995);
        ((GeneralPath) shape).lineTo(151.262, 127.995);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_163;
        g.setTransform(defaultTransform__0_163);
        g.setClip(clip__0_163);
        float alpha__0_164 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_164 = g.getClip();
        AffineTransform defaultTransform__0_164 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_164 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(154.965, 129.351);
        ((GeneralPath) shape).curveTo(154.597, 129.351, 154.359, 129.41699,
                154.256, 129.552);
        ((GeneralPath) shape).curveTo(154.155, 129.685, 154.106, 129.99901,
                154.106, 130.49);
        ((GeneralPath) shape).curveTo(154.106, 131.05301, 154.153, 131.404,
                154.251, 131.54701);
        ((GeneralPath) shape).curveTo(154.348, 131.68802, 154.58601, 131.75801,
                154.97401, 131.75801);
        ((GeneralPath) shape).curveTo(155.34302, 131.75801, 155.57802, 131.684,
                155.67601, 131.535);
        ((GeneralPath) shape).curveTo(155.77602, 131.38701, 155.822, 131.03,
                155.822, 130.465);
        ((GeneralPath) shape).curveTo(155.822, 129.986, 155.77101, 129.68399,
                155.67001, 129.55);
        ((GeneralPath) shape).curveTo(155.566, 129.417, 155.332, 129.351,
                154.965, 129.351);
        ((GeneralPath) shape).moveTo(154.974, 128.644);
        ((GeneralPath) shape).curveTo(155.693, 128.644, 156.162, 128.76,
                156.38899, 128.98999);
        ((GeneralPath) shape).curveTo(156.611, 129.21999, 156.724, 129.70898,
                156.724, 130.44899);
        ((GeneralPath) shape).curveTo(156.724, 131.277, 156.615, 131.81999,
                156.396, 132.07999);
        ((GeneralPath) shape).curveTo(156.178, 132.33798, 155.716, 132.46599,
                155.01599, 132.46599);
        ((GeneralPath) shape).curveTo(154.254, 132.46599, 153.76299, 132.34698,
                153.53699, 132.107);
        ((GeneralPath) shape).curveTo(153.31299, 131.871, 153.19998, 131.342,
                153.19998, 130.525);
        ((GeneralPath) shape).curveTo(153.19998, 129.74199, 153.30898, 129.232,
                153.53198, 128.999);
        ((GeneralPath) shape).curveTo(153.751, 128.761, 154.232, 128.644,
                154.974, 128.644);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_164;
        g.setTransform(defaultTransform__0_164);
        g.setClip(clip__0_164);
        float alpha__0_165 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_165 = g.getClip();
        AffineTransform defaultTransform__0_165 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_165 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(157.471, 128.687);
        ((GeneralPath) shape).lineTo(158.35599, 128.687);
        ((GeneralPath) shape).lineTo(158.31999, 129.315);
        ((GeneralPath) shape).lineTo(158.34, 129.319);
        ((GeneralPath) shape).curveTo(158.515, 128.871, 158.903, 128.643,
                159.509, 128.643);
        ((GeneralPath) shape).curveTo(160.392, 128.643, 160.83, 129.054,
                160.83, 129.878);
        ((GeneralPath) shape).lineTo(160.83, 132.421);
        ((GeneralPath) shape).lineTo(159.937, 132.421);
        ((GeneralPath) shape).lineTo(159.937, 130.028);
        ((GeneralPath) shape).lineTo(159.916, 129.766);
        ((GeneralPath) shape).curveTo(159.875, 129.49101, 159.657, 129.349,
                159.263, 129.349);
        ((GeneralPath) shape).curveTo(158.661, 129.349, 158.363, 129.634,
                158.363, 130.204);
        ((GeneralPath) shape).lineTo(158.363, 132.41899);
        ((GeneralPath) shape).lineTo(157.47, 132.41899);
        ((GeneralPath) shape).lineTo(157.47, 128.685);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_165;
        g.setTransform(defaultTransform__0_165);
        g.setClip(clip__0_165);
        float alpha__0_166 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_166 = g.getClip();
        AffineTransform defaultTransform__0_166 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_166 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(161.702, 128.687);
        ((GeneralPath) shape).lineTo(162.58699, 128.687);
        ((GeneralPath) shape).lineTo(162.551, 129.315);
        ((GeneralPath) shape).lineTo(162.57199, 129.319);
        ((GeneralPath) shape).curveTo(162.745, 128.871, 163.135, 128.643,
                163.741, 128.643);
        ((GeneralPath) shape).curveTo(164.622, 128.643, 165.062, 129.054,
                165.062, 129.878);
        ((GeneralPath) shape).lineTo(165.062, 132.421);
        ((GeneralPath) shape).lineTo(164.16699, 132.421);
        ((GeneralPath) shape).lineTo(164.16699, 130.028);
        ((GeneralPath) shape).lineTo(164.14699, 129.766);
        ((GeneralPath) shape).curveTo(164.10599, 129.49101, 163.88799, 129.349,
                163.49199, 129.349);
        ((GeneralPath) shape).curveTo(162.89198, 129.349, 162.592, 129.634,
                162.592, 130.204);
        ((GeneralPath) shape).lineTo(162.592, 132.41899);
        ((GeneralPath) shape).lineTo(161.7, 132.41899);
        ((GeneralPath) shape).lineTo(161.7, 128.685);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_166;
        g.setTransform(defaultTransform__0_166);
        g.setClip(clip__0_166);
        float alpha__0_167 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_167 = g.getClip();
        AffineTransform defaultTransform__0_167 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_167 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(167.457, 130.75);
        ((GeneralPath) shape).curveTo(166.954, 130.75, 166.701, 130.924,
                166.701, 131.271);
        ((GeneralPath) shape).curveTo(166.701, 131.513, 166.752, 131.67099,
                166.85701, 131.75);
        ((GeneralPath) shape).curveTo(166.96101, 131.822, 167.17902, 131.861,
                167.51201, 131.861);
        ((GeneralPath) shape).curveTo(168.05402, 131.861, 168.32501, 131.67699,
                168.32501, 131.31);
        ((GeneralPath) shape).curveTo(168.326, 130.937, 168.038, 130.75,
                167.457, 130.75);
        ((GeneralPath) shape).moveTo(166.86, 129.769);
        ((GeneralPath) shape).lineTo(165.947, 129.769);
        ((GeneralPath) shape).curveTo(165.947, 129.32199, 166.05101, 129.023,
                166.259, 128.873);
        ((GeneralPath) shape).curveTo(166.466, 128.723, 166.879, 128.644,
                167.495, 128.644);
        ((GeneralPath) shape).curveTo(168.165, 128.644, 168.618, 128.736,
                168.855, 128.922);
        ((GeneralPath) shape).curveTo(169.08899, 129.106, 169.209, 129.459,
                169.209, 129.98);
        ((GeneralPath) shape).lineTo(169.209, 132.422);
        ((GeneralPath) shape).lineTo(168.316, 132.422);
        ((GeneralPath) shape).lineTo(168.358, 131.91);
        ((GeneralPath) shape).lineTo(168.335, 131.905);
        ((GeneralPath) shape).curveTo(168.164, 132.276, 167.77, 132.465,
                167.147, 132.465);
        ((GeneralPath) shape).curveTo(166.24701, 132.465, 165.793, 132.083,
                165.793, 131.313);
        ((GeneralPath) shape).curveTo(165.793, 130.54001, 166.253, 130.149,
                167.179, 130.149);
        ((GeneralPath) shape).curveTo(167.793, 130.149, 168.167, 130.292,
                168.3, 130.577);
        ((GeneralPath) shape).lineTo(168.31801, 130.577);
        ((GeneralPath) shape).lineTo(168.31801, 129.972);
        ((GeneralPath) shape).curveTo(168.31801, 129.681, 168.26701, 129.488,
                168.16602, 129.392);
        ((GeneralPath) shape).curveTo(168.06401, 129.298, 167.85802, 129.249,
                167.54202, 129.249);
        ((GeneralPath) shape).curveTo(167.088, 129.245, 166.86, 129.419,
                166.86, 129.769);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_167;
        g.setTransform(defaultTransform__0_167);
        g.setClip(clip__0_167);
        float alpha__0_168 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_168 = g.getClip();
        AffineTransform defaultTransform__0_168 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_168 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(171.674, 129.351);
        ((GeneralPath) shape).curveTo(171.331, 129.351, 171.111, 129.41899,
                171.015, 129.56);
        ((GeneralPath) shape).curveTo(170.915, 129.699, 170.869, 130.015,
                170.869, 130.511);
        ((GeneralPath) shape).curveTo(170.869, 131.037, 170.918, 131.373,
                171.015, 131.519);
        ((GeneralPath) shape).curveTo(171.112, 131.665, 171.334, 131.73799,
                171.687, 131.73799);
        ((GeneralPath) shape).curveTo(172.038, 131.73799, 172.26399, 131.66599,
                172.369, 131.51299);
        ((GeneralPath) shape).curveTo(172.473, 131.36499, 172.52501, 131.03299,
                172.52501, 130.51799);
        ((GeneralPath) shape).curveTo(172.52501, 130.02599, 172.47401,
                129.70999, 172.369, 129.56499);
        ((GeneralPath) shape).curveTo(172.265, 129.423, 172.033, 129.351,
                171.674, 129.351);
        ((GeneralPath) shape).moveTo(173.433, 128.687);
        ((GeneralPath) shape).lineTo(173.433, 132.494);
        ((GeneralPath) shape).curveTo(173.433, 133.101, 173.31, 133.519,
                173.064, 133.74701);
        ((GeneralPath) shape).curveTo(172.818, 133.97401, 172.366, 134.087,
                171.706, 134.087);
        ((GeneralPath) shape).curveTo(171.06699, 134.087, 170.642, 134.001,
                170.42299, 133.82901);
        ((GeneralPath) shape).curveTo(170.20898, 133.65501, 170.10199, 133.315,
                170.10199, 132.80801);
        ((GeneralPath) shape).lineTo(170.965, 132.80801);
        ((GeneralPath) shape).curveTo(170.965, 133.05002, 171.01, 133.20401,
                171.103, 133.27501);
        ((GeneralPath) shape).curveTo(171.193, 133.343, 171.395, 133.37901,
                171.709, 133.37901);
        ((GeneralPath) shape).curveTo(172.265, 133.37901, 172.54, 133.15001,
                172.54, 132.69101);
        ((GeneralPath) shape).lineTo(172.54, 131.852);
        ((GeneralPath) shape).lineTo(172.51999, 131.848);
        ((GeneralPath) shape).curveTo(172.36198, 132.246, 171.99799, 132.44601,
                171.42699, 132.44601);
        ((GeneralPath) shape).curveTo(170.86398, 132.44601, 170.47899,
                132.30902, 170.27199, 132.03702);
        ((GeneralPath) shape).curveTo(170.06499, 131.76402, 169.95999,
                131.25401, 169.95999, 130.50601);
        ((GeneralPath) shape).curveTo(169.95999, 129.80202, 170.064, 129.31801,
                170.27199, 129.04901);
        ((GeneralPath) shape).curveTo(170.47899, 128.779, 170.85298, 128.64601,
                171.39699, 128.64601);
        ((GeneralPath) shape).curveTo(171.98999, 128.64601, 172.37599, 128.865,
                172.56299, 129.307);
        ((GeneralPath) shape).lineTo(172.58199, 129.307);
        ((GeneralPath) shape).lineTo(172.53998, 128.69);
        ((GeneralPath) shape).lineTo(173.433, 128.687);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_168;
        g.setTransform(defaultTransform__0_168);
        g.setClip(clip__0_168);
        float alpha__0_169 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_169 = g.getClip();
        AffineTransform defaultTransform__0_169 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_169 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(176.715, 130.167);
        ((GeneralPath) shape).lineTo(176.711, 130.018);
        ((GeneralPath) shape).curveTo(176.711, 129.722, 176.66, 129.53,
                176.559, 129.44);
        ((GeneralPath) shape).curveTo(176.45601, 129.353, 176.23401, 129.308,
                175.891, 129.308);
        ((GeneralPath) shape).curveTo(175.559, 129.308, 175.341, 129.361,
                175.242, 129.468);
        ((GeneralPath) shape).curveTo(175.142, 129.572, 175.09201, 129.808,
                175.09201, 130.167);
        ((GeneralPath) shape).lineTo(176.715, 130.167);
        ((GeneralPath) shape).moveTo(176.707, 131.226);
        ((GeneralPath) shape).lineTo(177.604, 131.226);
        ((GeneralPath) shape).lineTo(177.604, 131.372);
        ((GeneralPath) shape).curveTo(177.604, 132.101, 177.058, 132.46599,
                175.965, 132.46599);
        ((GeneralPath) shape).curveTo(175.226, 132.46599, 174.73999, 132.34099,
                174.513, 132.08798);
        ((GeneralPath) shape).curveTo(174.284, 131.83598, 174.168, 131.30098,
                174.168, 130.48198);
        ((GeneralPath) shape).curveTo(174.168, 129.75598, 174.287, 129.26797,
                174.527, 129.01797);
        ((GeneralPath) shape).curveTo(174.765, 128.76797, 175.23299, 128.64297,
                175.926, 128.64297);
        ((GeneralPath) shape).curveTo(176.58899, 128.64297, 177.03499,
                128.76398, 177.263, 129.00798);
        ((GeneralPath) shape).curveTo(177.489, 129.24998, 177.602, 129.72897,
                177.602, 130.44298);
        ((GeneralPath) shape).lineTo(177.602, 130.71698);
        ((GeneralPath) shape).lineTo(175.08, 130.71698);
        ((GeneralPath) shape).curveTo(175.076, 130.79898, 175.072, 130.85397,
                175.072, 130.88098);
        ((GeneralPath) shape).curveTo(175.072, 131.24799, 175.12901, 131.48997,
                175.242, 131.61298);
        ((GeneralPath) shape).curveTo(175.35501, 131.73398, 175.57901,
                131.79898, 175.918, 131.79898);
        ((GeneralPath) shape).curveTo(176.246, 131.79898, 176.458, 131.76299,
                176.559, 131.69199);
        ((GeneralPath) shape).curveTo(176.657, 131.619, 176.707, 131.464,
                176.707, 131.226);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_169;
        g.setTransform(defaultTransform__0_169);
        g.setClip(clip__0_169);
        float alpha__0_170 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_170 = g.getClip();
        AffineTransform defaultTransform__0_170 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_170 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(179.199, 128.687);
        ((GeneralPath) shape).lineTo(179.199, 129.648);
        ((GeneralPath) shape).lineTo(178.306, 129.648);
        ((GeneralPath) shape).lineTo(178.306, 128.687);
        ((GeneralPath) shape).lineTo(179.199, 128.687);
        ((GeneralPath) shape).moveTo(179.199, 131.46);
        ((GeneralPath) shape).lineTo(179.199, 132.421);
        ((GeneralPath) shape).lineTo(178.306, 132.421);
        ((GeneralPath) shape).lineTo(178.306, 131.46);
        ((GeneralPath) shape).lineTo(179.199, 131.46);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_170;
        g.setTransform(defaultTransform__0_170);
        g.setClip(clip__0_170);
        float alpha__0_171 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_171 = g.getClip();
        AffineTransform defaultTransform__0_171 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_171 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(46.504, 159.739);
        ((GeneralPath) shape).lineTo(46.504, 165.071);
        ((GeneralPath) shape).lineTo(45.494003, 165.071);
        ((GeneralPath) shape).lineTo(45.494003, 162.16699);
        ((GeneralPath) shape).curveTo(45.494003, 161.937, 45.500004, 161.67299,
                45.515003, 161.37799);
        ((GeneralPath) shape).lineTo(45.535004, 160.98);
        ((GeneralPath) shape).lineTo(45.556004, 160.58499);
        ((GeneralPath) shape).lineTo(45.525005, 160.58499);
        ((GeneralPath) shape).lineTo(45.404007, 160.956);
        ((GeneralPath) shape).lineTo(45.287006, 161.327);
        ((GeneralPath) shape).curveTo(45.179005, 161.657, 45.095005, 161.903,
                45.034008, 162.06299);
        ((GeneralPath) shape).lineTo(43.86501, 165.07098);
        ((GeneralPath) shape).lineTo(42.94501, 165.07098);
        ((GeneralPath) shape).lineTo(41.763012, 162.08899);
        ((GeneralPath) shape).curveTo(41.699013, 161.92499, 41.61301,
                161.67899, 41.504013, 161.35098);
        ((GeneralPath) shape).lineTo(41.383015, 160.97998);
        ((GeneralPath) shape).lineTo(41.264015, 160.61298);
        ((GeneralPath) shape).lineTo(41.233017, 160.61298);
        ((GeneralPath) shape).lineTo(41.253017, 161.00198);
        ((GeneralPath) shape).lineTo(41.274017, 161.39299);
        ((GeneralPath) shape).curveTo(41.289017, 161.69398, 41.295017, 161.956,
                41.295017, 162.16998);
        ((GeneralPath) shape).lineTo(41.295017, 165.07399);
        ((GeneralPath) shape).lineTo(40.28502, 165.07399);
        ((GeneralPath) shape).lineTo(40.28502, 159.74199);
        ((GeneralPath) shape).lineTo(41.93102, 159.74199);
        ((GeneralPath) shape).lineTo(42.88002, 162.21298);
        ((GeneralPath) shape).curveTo(42.94402, 162.38498, 43.03002, 162.63098,
                43.13902, 162.94897);
        ((GeneralPath) shape).lineTo(43.25402, 163.31998);
        ((GeneralPath) shape).lineTo(43.37502, 163.68698);
        ((GeneralPath) shape).lineTo(43.41002, 163.68698);
        ((GeneralPath) shape).lineTo(43.523018, 163.31998);
        ((GeneralPath) shape).lineTo(43.64002, 162.95497);
        ((GeneralPath) shape).curveTo(43.73602, 162.64397, 43.81902, 162.39796,
                43.89002, 162.22098);
        ((GeneralPath) shape).lineTo(44.82602, 159.74197);
        ((GeneralPath) shape).lineTo(46.504, 159.739);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_171;
        g.setTransform(defaultTransform__0_171);
        g.setClip(clip__0_171);
        float alpha__0_172 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_172 = g.getClip();
        AffineTransform defaultTransform__0_172 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_172 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(49.038, 162.001);
        ((GeneralPath) shape).curveTo(48.668, 162.001, 48.432, 162.065, 48.329,
                162.20201);
        ((GeneralPath) shape).curveTo(48.227997, 162.335, 48.177, 162.64702,
                48.177, 163.14001);
        ((GeneralPath) shape).curveTo(48.177, 163.70302, 48.225998, 164.05402,
                48.322998, 164.195);
        ((GeneralPath) shape).curveTo(48.418, 164.33801, 48.657997, 164.406,
                49.044, 164.406);
        ((GeneralPath) shape).curveTo(49.414997, 164.406, 49.649998, 164.334,
                49.747997, 164.183);
        ((GeneralPath) shape).curveTo(49.847996, 164.037, 49.893997, 163.679,
                49.893997, 163.11299);
        ((GeneralPath) shape).curveTo(49.893997, 162.63599, 49.840996,
                162.33398, 49.741997, 162.19899);
        ((GeneralPath) shape).curveTo(49.638, 162.067, 49.404, 162.001, 49.038,
                162.001);
        ((GeneralPath) shape).moveTo(49.046, 161.294);
        ((GeneralPath) shape).curveTo(49.767002, 161.294, 50.236, 161.40901,
                50.461002, 161.64);
        ((GeneralPath) shape).curveTo(50.685, 161.87, 50.796, 162.357, 50.796,
                163.099);
        ((GeneralPath) shape).curveTo(50.796, 163.927, 50.689003, 164.47,
                50.471, 164.728);
        ((GeneralPath) shape).curveTo(50.251, 164.986, 49.79, 165.11499,
                49.089, 165.11499);
        ((GeneralPath) shape).curveTo(48.329002, 165.11499, 47.836002,
                164.99799, 47.61, 164.758);
        ((GeneralPath) shape).curveTo(47.386, 164.51999, 47.273, 163.98999,
                47.273, 163.176);
        ((GeneralPath) shape).curveTo(47.273, 162.39299, 47.379997, 161.883,
                47.605, 161.649);
        ((GeneralPath) shape).curveTo(47.824, 161.411, 48.305, 161.294, 49.046,
                161.294);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_172;
        g.setTransform(defaultTransform__0_172);
        g.setClip(clip__0_172);
        float alpha__0_173 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_173 = g.getClip();
        AffineTransform defaultTransform__0_173 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_173 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(54.651, 161.337);
        ((GeneralPath) shape).lineTo(53.583, 165.069);
        ((GeneralPath) shape).lineTo(52.187, 165.069);
        ((GeneralPath) shape).lineTo(51.064, 161.337);
        ((GeneralPath) shape).lineTo(52.012, 161.337);
        ((GeneralPath) shape).lineTo(52.501, 163.06001);
        ((GeneralPath) shape).curveTo(52.567, 163.29802, 52.628998, 163.531,
                52.689, 163.75502);
        ((GeneralPath) shape).lineTo(52.779, 164.10303);
        ((GeneralPath) shape).lineTo(52.868, 164.45303);
        ((GeneralPath) shape).lineTo(52.889, 164.45303);
        ((GeneralPath) shape).lineTo(52.971, 164.10303);
        ((GeneralPath) shape).lineTo(53.053, 163.75903);
        ((GeneralPath) shape).curveTo(53.116, 163.49904, 53.172, 163.26703,
                53.228, 163.06403);
        ((GeneralPath) shape).lineTo(53.688, 161.33702);
        ((GeneralPath) shape).lineTo(54.651, 161.33702);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_173;
        g.setTransform(defaultTransform__0_173);
        g.setClip(clip__0_173);
        float alpha__0_174 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_174 = g.getClip();
        AffineTransform defaultTransform__0_174 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_174 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(57.458, 162.817);
        ((GeneralPath) shape).lineTo(57.454, 162.671);
        ((GeneralPath) shape).curveTo(57.454, 162.37401, 57.404, 162.183, 57.3,
                162.093);
        ((GeneralPath) shape).curveTo(57.197998, 162.005, 56.976997, 161.96,
                56.632, 161.96);
        ((GeneralPath) shape).curveTo(56.3, 161.96, 56.084, 162.013, 55.985,
                162.12001);
        ((GeneralPath) shape).curveTo(55.884, 162.22401, 55.835, 162.46,
                55.835, 162.81702);
        ((GeneralPath) shape).lineTo(57.458, 162.81702);
        ((GeneralPath) shape).moveTo(57.45, 163.876);
        ((GeneralPath) shape).lineTo(58.347, 163.876);
        ((GeneralPath) shape).lineTo(58.347, 164.02101);
        ((GeneralPath) shape).curveTo(58.347, 164.75002, 57.799, 165.115,
                56.708, 165.115);
        ((GeneralPath) shape).curveTo(55.969, 165.115, 55.483, 164.99, 55.253,
                164.73601);
        ((GeneralPath) shape).curveTo(55.024998, 164.48401, 54.91, 163.949,
                54.91, 163.132);
        ((GeneralPath) shape).curveTo(54.91, 162.403, 55.029, 161.917, 55.268,
                161.667);
        ((GeneralPath) shape).curveTo(55.504, 161.417, 55.974003, 161.292,
                56.666, 161.292);
        ((GeneralPath) shape).curveTo(57.331, 161.292, 57.777, 161.41301,
                58.003002, 161.65501);
        ((GeneralPath) shape).curveTo(58.231003, 161.89702, 58.344, 162.37802,
                58.344, 163.09302);
        ((GeneralPath) shape).lineTo(58.344, 163.36401);
        ((GeneralPath) shape).lineTo(55.822002, 163.36401);
        ((GeneralPath) shape).curveTo(55.818, 163.44601, 55.814003, 163.50302,
                55.814003, 163.52802);
        ((GeneralPath) shape).curveTo(55.814003, 163.89502, 55.871002, 164.139,
                55.983, 164.26201);
        ((GeneralPath) shape).curveTo(56.096, 164.38301, 56.320004, 164.44601,
                56.659, 164.44601);
        ((GeneralPath) shape).curveTo(56.987, 164.44601, 57.2, 164.41301,
                57.299, 164.34102);
        ((GeneralPath) shape).curveTo(57.4, 164.269, 57.45, 164.114, 57.45,
                163.876);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_174;
        g.setTransform(defaultTransform__0_174);
        g.setClip(clip__0_174);
        float alpha__0_175 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_175 = g.getClip();
        AffineTransform defaultTransform__0_175 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_175 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(59.089, 161.337);
        ((GeneralPath) shape).lineTo(59.984, 161.337);
        ((GeneralPath) shape).lineTo(59.961002, 161.91101);
        ((GeneralPath) shape).lineTo(59.982002, 161.91501);
        ((GeneralPath) shape).curveTo(60.163002, 161.501, 60.543003, 161.294,
                61.120003, 161.294);
        ((GeneralPath) shape).curveTo(61.793003, 161.294, 62.167004, 161.52301,
                62.243004, 161.983);
        ((GeneralPath) shape).lineTo(62.259003, 161.983);
        ((GeneralPath) shape).curveTo(62.435, 161.522, 62.819004, 161.294,
                63.409004, 161.294);
        ((GeneralPath) shape).curveTo(64.270004, 161.294, 64.703, 161.72801,
                64.703, 162.599);
        ((GeneralPath) shape).lineTo(64.703, 165.072);
        ((GeneralPath) shape).lineTo(63.81, 165.072);
        ((GeneralPath) shape).lineTo(63.81, 162.79701);
        ((GeneralPath) shape).curveTo(63.81, 162.27002, 63.592003, 162.00401,
                63.159, 162.00401);
        ((GeneralPath) shape).curveTo(62.617, 162.00401, 62.344, 162.29901,
                62.344, 162.891);
        ((GeneralPath) shape).lineTo(62.344, 165.07501);
        ((GeneralPath) shape).lineTo(61.45, 165.07501);
        ((GeneralPath) shape).lineTo(61.45, 162.76201);
        ((GeneralPath) shape).curveTo(61.45, 162.453, 61.409, 162.248, 61.327,
                162.15102);
        ((GeneralPath) shape).curveTo(61.245, 162.05402, 61.072, 162.00502,
                60.807, 162.00502);
        ((GeneralPath) shape).curveTo(60.257, 162.00502, 59.983997, 162.30602,
                59.983997, 162.91103);
        ((GeneralPath) shape).lineTo(59.983997, 165.07303);
        ((GeneralPath) shape).lineTo(59.09, 165.07303);
        ((GeneralPath) shape).lineTo(59.089, 161.337);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_175;
        g.setTransform(defaultTransform__0_175);
        g.setClip(clip__0_175);
        float alpha__0_176 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_176 = g.getClip();
        AffineTransform defaultTransform__0_176 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_176 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(68.021, 162.817);
        ((GeneralPath) shape).lineTo(68.017006, 162.671);
        ((GeneralPath) shape).curveTo(68.017006, 162.37401, 67.964005, 162.183,
                67.86301, 162.093);
        ((GeneralPath) shape).curveTo(67.76201, 162.005, 67.53801, 161.96,
                67.19501, 161.96);
        ((GeneralPath) shape).curveTo(66.86301, 161.96, 66.64701, 162.013,
                66.546005, 162.12001);
        ((GeneralPath) shape).curveTo(66.44701, 162.22401, 66.398, 162.46,
                66.398, 162.81702);
        ((GeneralPath) shape).lineTo(68.021, 162.81702);
        ((GeneralPath) shape).moveTo(68.013, 163.876);
        ((GeneralPath) shape).lineTo(68.909004, 163.876);
        ((GeneralPath) shape).lineTo(68.909004, 164.02101);
        ((GeneralPath) shape).curveTo(68.909004, 164.75002, 68.36301, 165.115,
                67.271, 165.115);
        ((GeneralPath) shape).curveTo(66.53001, 165.115, 66.045006, 164.99,
                65.817, 164.73601);
        ((GeneralPath) shape).curveTo(65.588005, 164.48401, 65.474, 163.949,
                65.474, 163.132);
        ((GeneralPath) shape).curveTo(65.474, 162.403, 65.591995, 161.917,
                65.831, 161.667);
        ((GeneralPath) shape).curveTo(66.069, 161.417, 66.537, 161.292, 67.23,
                161.292);
        ((GeneralPath) shape).curveTo(67.895004, 161.292, 68.340004, 161.41301,
                68.567, 161.65501);
        ((GeneralPath) shape).curveTo(68.793, 161.89702, 68.906, 162.37802,
                68.906, 163.09302);
        ((GeneralPath) shape).lineTo(68.906, 163.36401);
        ((GeneralPath) shape).lineTo(66.384995, 163.36401);
        ((GeneralPath) shape).curveTo(66.379, 163.44601, 66.37499, 163.50302,
                66.37499, 163.52802);
        ((GeneralPath) shape).curveTo(66.37499, 163.89502, 66.43299, 164.139,
                66.54399, 164.26201);
        ((GeneralPath) shape).curveTo(66.65699, 164.38301, 66.88099, 164.44601,
                67.22199, 164.44601);
        ((GeneralPath) shape).curveTo(67.54799, 164.44601, 67.76199, 164.41301,
                67.86099, 164.34102);
        ((GeneralPath) shape).curveTo(67.962, 164.269, 68.013, 164.114, 68.013,
                163.876);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_176;
        g.setTransform(defaultTransform__0_176);
        g.setClip(clip__0_176);
        float alpha__0_177 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_177 = g.getClip();
        AffineTransform defaultTransform__0_177 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_177 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(69.651, 161.337);
        ((GeneralPath) shape).lineTo(70.536, 161.337);
        ((GeneralPath) shape).lineTo(70.501, 161.966);
        ((GeneralPath) shape).lineTo(70.522, 161.97);
        ((GeneralPath) shape).curveTo(70.69601, 161.521, 71.08501, 161.294,
                71.692, 161.294);
        ((GeneralPath) shape).curveTo(72.573, 161.294, 73.013, 161.70401,
                73.013, 162.526);
        ((GeneralPath) shape).lineTo(73.013, 165.069);
        ((GeneralPath) shape).lineTo(72.118004, 165.069);
        ((GeneralPath) shape).lineTo(72.118004, 162.678);
        ((GeneralPath) shape).lineTo(72.09801, 162.416);
        ((GeneralPath) shape).curveTo(72.05701, 162.139, 71.837006, 161.998,
                71.44301, 161.998);
        ((GeneralPath) shape).curveTo(70.84301, 161.998, 70.54301, 162.283,
                70.54301, 162.852);
        ((GeneralPath) shape).lineTo(70.54301, 165.067);
        ((GeneralPath) shape).lineTo(69.65001, 165.067);
        ((GeneralPath) shape).lineTo(69.65001, 161.337);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_177;
        g.setTransform(defaultTransform__0_177);
        g.setClip(clip__0_177);
        float alpha__0_178 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_178 = g.getClip();
        AffineTransform defaultTransform__0_178 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_178 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(76.289, 161.337);
        ((GeneralPath) shape).lineTo(76.289, 162.017);
        ((GeneralPath) shape).lineTo(74.854004, 162.017);
        ((GeneralPath) shape).lineTo(74.854004, 163.892);
        ((GeneralPath) shape).curveTo(74.854004, 164.23799, 74.985, 164.413,
                75.248, 164.413);
        ((GeneralPath) shape).curveTo(75.537, 164.413, 75.683, 164.204, 75.683,
                163.784);
        ((GeneralPath) shape).lineTo(75.683, 163.636);
        ((GeneralPath) shape).lineTo(76.443, 163.636);
        ((GeneralPath) shape).lineTo(76.443, 163.824);
        ((GeneralPath) shape).curveTo(76.443, 163.998, 76.439, 164.142, 76.425,
                164.265);
        ((GeneralPath) shape).curveTo(76.37701, 164.835, 75.953, 165.12,
                75.154, 165.12);
        ((GeneralPath) shape).curveTo(74.359, 165.12, 73.961, 164.75499,
                73.961, 164.01999);
        ((GeneralPath) shape).lineTo(73.961, 162.01599);
        ((GeneralPath) shape).lineTo(73.479996, 162.01599);
        ((GeneralPath) shape).lineTo(73.479996, 161.336);
        ((GeneralPath) shape).lineTo(73.961, 161.336);
        ((GeneralPath) shape).lineTo(73.961, 160.5);
        ((GeneralPath) shape).lineTo(74.855995, 160.5);
        ((GeneralPath) shape).lineTo(74.855995, 161.336);
        ((GeneralPath) shape).lineTo(76.289, 161.336);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_178;
        g.setTransform(defaultTransform__0_178);
        g.setClip(clip__0_178);
        float alpha__0_179 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_179 = g.getClip();
        AffineTransform defaultTransform__0_179 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_179 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(81.077, 160.646);
        ((GeneralPath) shape).lineTo(81.077, 165.071);
        ((GeneralPath) shape).lineTo(80.067, 165.071);
        ((GeneralPath) shape).lineTo(80.067, 160.646);
        ((GeneralPath) shape).lineTo(78.531, 160.646);
        ((GeneralPath) shape).lineTo(78.531, 159.739);
        ((GeneralPath) shape).lineTo(82.667, 159.739);
        ((GeneralPath) shape).lineTo(82.667, 160.646);
        ((GeneralPath) shape).lineTo(81.077, 160.646);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_179;
        g.setTransform(defaultTransform__0_179);
        g.setClip(clip__0_179);
        float alpha__0_180 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_180 = g.getClip();
        AffineTransform defaultTransform__0_180 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_180 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(86.22, 161.337);
        ((GeneralPath) shape).lineTo(85.241005, 165.306);
        ((GeneralPath) shape).curveTo(85.104004, 165.869, 84.93301, 166.245,
                84.728004, 166.441);
        ((GeneralPath) shape).curveTo(84.525, 166.63399, 84.198006, 166.732,
                83.748, 166.732);
        ((GeneralPath) shape).curveTo(83.646, 166.732, 83.541, 166.728, 83.434,
                166.71599);
        ((GeneralPath) shape).lineTo(83.434, 166.05598);
        ((GeneralPath) shape).curveTo(83.512, 166.05998, 83.576, 166.06398,
                83.627, 166.06398);
        ((GeneralPath) shape).curveTo(84.005, 166.06398, 84.247, 165.73398,
                84.35, 165.07198);
        ((GeneralPath) shape).lineTo(83.894, 165.07198);
        ((GeneralPath) shape).lineTo(82.672, 161.33998);
        ((GeneralPath) shape).lineTo(83.631, 161.33998);
        ((GeneralPath) shape).lineTo(84.101, 162.92198);
        ((GeneralPath) shape).lineTo(84.333, 163.71498);
        ((GeneralPath) shape).curveTo(84.347, 163.76598, 84.382, 163.89899,
                84.442, 164.11098);
        ((GeneralPath) shape).lineTo(84.555, 164.50697);
        ((GeneralPath) shape).lineTo(84.575, 164.50697);
        ((GeneralPath) shape).lineTo(84.657, 164.11098);
        ((GeneralPath) shape).curveTo(84.698, 163.90797, 84.725, 163.77498,
                84.739, 163.71498);
        ((GeneralPath) shape).lineTo(84.922, 162.92198);
        ((GeneralPath) shape).lineTo(85.281, 161.33998);
        ((GeneralPath) shape).lineTo(86.22, 161.337);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_180;
        g.setTransform(defaultTransform__0_180);
        g.setClip(clip__0_180);
        float alpha__0_181 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_181 = g.getClip();
        AffineTransform defaultTransform__0_181 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_181 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(88.384, 161.997);
        ((GeneralPath) shape).curveTo(88.045006, 161.997, 87.823006, 162.071,
                87.718, 162.226);
        ((GeneralPath) shape).curveTo(87.614006, 162.37799, 87.564, 162.705,
                87.564, 163.205);
        ((GeneralPath) shape).curveTo(87.564, 163.687, 87.618004, 164.01,
                87.731, 164.17);
        ((GeneralPath) shape).curveTo(87.840004, 164.328, 88.07, 164.40599,
                88.414, 164.40599);
        ((GeneralPath) shape).curveTo(88.761, 164.40599, 88.987, 164.33398,
                89.092, 164.17899);
        ((GeneralPath) shape).curveTo(89.196, 164.02899, 89.248, 163.70398,
                89.248, 163.20598);
        ((GeneralPath) shape).curveTo(89.248, 162.69398, 89.195, 162.36497,
                89.090004, 162.21797);
        ((GeneralPath) shape).curveTo(88.984, 162.07, 88.749, 161.997, 88.384,
                161.997);
        ((GeneralPath) shape).moveTo(86.626, 161.337);
        ((GeneralPath) shape).lineTo(87.534, 161.337);
        ((GeneralPath) shape).lineTo(87.49899, 161.892);
        ((GeneralPath) shape).lineTo(87.52, 161.896);
        ((GeneralPath) shape).curveTo(87.73399, 161.496, 88.133995, 161.29199,
                88.725, 161.29199);
        ((GeneralPath) shape).curveTo(89.267, 161.29199, 89.641, 161.42699,
                89.846, 161.69398);
        ((GeneralPath) shape).curveTo(90.047, 161.96498, 90.152, 162.45598,
                90.152, 163.17099);
        ((GeneralPath) shape).curveTo(90.152, 163.915, 90.049, 164.42499,
                89.848, 164.702);
        ((GeneralPath) shape).curveTo(89.645, 164.97499, 89.270996, 165.114,
                88.722, 165.114);
        ((GeneralPath) shape).curveTo(88.137, 165.114, 87.753, 164.921, 87.576,
                164.528);
        ((GeneralPath) shape).lineTo(87.56, 164.528);
        ((GeneralPath) shape).lineTo(87.56, 166.674);
        ((GeneralPath) shape).lineTo(86.626, 166.674);
        ((GeneralPath) shape).lineTo(86.626, 161.337);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_181;
        g.setTransform(defaultTransform__0_181);
        g.setClip(clip__0_181);
        float alpha__0_182 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_182 = g.getClip();
        AffineTransform defaultTransform__0_182 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_182 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(93.307, 162.817);
        ((GeneralPath) shape).lineTo(93.303, 162.671);
        ((GeneralPath) shape).curveTo(93.303, 162.37401, 93.251, 162.183,
                93.149, 162.093);
        ((GeneralPath) shape).curveTo(93.045006, 162.005, 92.824005, 161.96,
                92.481, 161.96);
        ((GeneralPath) shape).curveTo(92.149, 161.96, 91.93301, 162.013,
                91.832, 162.12001);
        ((GeneralPath) shape).curveTo(91.733, 162.22401, 91.684, 162.46,
                91.684, 162.81702);
        ((GeneralPath) shape).lineTo(93.307, 162.81702);
        ((GeneralPath) shape).moveTo(93.298, 163.876);
        ((GeneralPath) shape).lineTo(94.195, 163.876);
        ((GeneralPath) shape).lineTo(94.195, 164.02101);
        ((GeneralPath) shape).curveTo(94.195, 164.75002, 93.649, 165.115,
                92.558, 165.115);
        ((GeneralPath) shape).curveTo(91.817, 165.115, 91.331, 164.99, 91.103,
                164.73601);
        ((GeneralPath) shape).curveTo(90.874, 164.48401, 90.757996, 163.949,
                90.757996, 163.132);
        ((GeneralPath) shape).curveTo(90.757996, 162.403, 90.877, 161.917,
                91.116, 161.667);
        ((GeneralPath) shape).curveTo(91.354, 161.417, 91.822, 161.292, 92.516,
                161.292);
        ((GeneralPath) shape).curveTo(93.181, 161.292, 93.625, 161.41301,
                93.853, 161.65501);
        ((GeneralPath) shape).curveTo(94.078995, 161.89702, 94.19199,
                162.37802, 94.19199, 163.09302);
        ((GeneralPath) shape).lineTo(94.19199, 163.36401);
        ((GeneralPath) shape).lineTo(91.66999, 163.36401);
        ((GeneralPath) shape).curveTo(91.66599, 163.44601, 91.66199, 163.50302,
                91.66199, 163.52802);
        ((GeneralPath) shape).curveTo(91.66199, 163.89502, 91.720985, 164.139,
                91.830986, 164.26201);
        ((GeneralPath) shape).curveTo(91.943985, 164.38301, 92.167984,
                164.44601, 92.50699, 164.44601);
        ((GeneralPath) shape).curveTo(92.83499, 164.44601, 93.04699, 164.41301,
                93.14899, 164.34102);
        ((GeneralPath) shape).curveTo(93.248, 164.269, 93.298, 164.114, 93.298,
                163.876);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_182;
        g.setTransform(defaultTransform__0_182);
        g.setClip(clip__0_182);
        float alpha__0_183 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_183 = g.getClip();
        AffineTransform defaultTransform__0_183 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_183 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(95.791, 161.337);
        ((GeneralPath) shape).lineTo(95.791, 162.298);
        ((GeneralPath) shape).lineTo(94.898, 162.298);
        ((GeneralPath) shape).lineTo(94.898, 161.337);
        ((GeneralPath) shape).lineTo(95.791, 161.337);
        ((GeneralPath) shape).moveTo(95.791, 164.11);
        ((GeneralPath) shape).lineTo(95.791, 165.071);
        ((GeneralPath) shape).lineTo(94.898, 165.071);
        ((GeneralPath) shape).lineTo(94.898, 164.11);
        ((GeneralPath) shape).lineTo(95.791, 164.11);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_183;
        g.setTransform(defaultTransform__0_183);
        g.setClip(clip__0_183);
        float alpha__0_184 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_184 = g.getClip();
        AffineTransform defaultTransform__0_184 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_184 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(41.187, 171.93);
        ((GeneralPath) shape).lineTo(41.187, 173.305);
        ((GeneralPath) shape).lineTo(43.682, 173.305);
        ((GeneralPath) shape).lineTo(43.682, 174.051);
        ((GeneralPath) shape).lineTo(41.187, 174.051);
        ((GeneralPath) shape).lineTo(41.187, 175.559);
        ((GeneralPath) shape).lineTo(43.841, 175.559);
        ((GeneralPath) shape).lineTo(43.841, 176.41);
        ((GeneralPath) shape).lineTo(40.177, 176.41);
        ((GeneralPath) shape).lineTo(40.177, 171.078);
        ((GeneralPath) shape).lineTo(43.818, 171.078);
        ((GeneralPath) shape).lineTo(43.818, 171.93);
        ((GeneralPath) shape).lineTo(41.187, 171.93);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_184;
        g.setTransform(defaultTransform__0_184);
        g.setClip(clip__0_184);
        float alpha__0_185 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_185 = g.getClip();
        AffineTransform defaultTransform__0_185 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_185 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(44.565, 172.676);
        ((GeneralPath) shape).lineTo(45.449997, 172.676);
        ((GeneralPath) shape).lineTo(45.414997, 173.305);
        ((GeneralPath) shape).lineTo(45.435997, 173.30899);
        ((GeneralPath) shape).curveTo(45.608997, 172.85999, 45.998997, 172.633,
                46.604996, 172.633);
        ((GeneralPath) shape).curveTo(47.485996, 172.633, 47.924995, 173.043,
                47.924995, 173.86699);
        ((GeneralPath) shape).lineTo(47.924995, 176.40999);
        ((GeneralPath) shape).lineTo(47.031994, 176.40999);
        ((GeneralPath) shape).lineTo(47.031994, 174.01698);
        ((GeneralPath) shape).lineTo(47.011993, 173.75499);
        ((GeneralPath) shape).curveTo(46.970993, 173.478, 46.75099, 173.33699,
                46.356995, 173.33699);
        ((GeneralPath) shape).curveTo(45.754993, 173.33699, 45.456993, 173.622,
                45.456993, 174.191);
        ((GeneralPath) shape).lineTo(45.456993, 176.40799);
        ((GeneralPath) shape).lineTo(44.56399, 176.40799);
        ((GeneralPath) shape).lineTo(44.56399, 172.676);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_185;
        g.setTransform(defaultTransform__0_185);
        g.setClip(clip__0_185);
        float alpha__0_186 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_186 = g.getClip();
        AffineTransform defaultTransform__0_186 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_186 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(50.371, 173.34);
        ((GeneralPath) shape).curveTo(50.028, 173.34, 49.808, 173.40599, 49.71,
                173.549);
        ((GeneralPath) shape).curveTo(49.612, 173.688, 49.564, 174.004, 49.564,
                174.5);
        ((GeneralPath) shape).curveTo(49.564, 175.023, 49.612, 175.359, 49.71,
                175.508);
        ((GeneralPath) shape).curveTo(49.809, 175.653, 50.031998, 175.72699,
                50.382, 175.72699);
        ((GeneralPath) shape).curveTo(50.735, 175.72699, 50.959, 175.65298,
                51.066, 175.50198);
        ((GeneralPath) shape).curveTo(51.170002, 175.35399, 51.222, 175.02199,
                51.222, 174.50798);
        ((GeneralPath) shape).curveTo(51.222, 174.01598, 51.169, 173.69698,
                51.066, 173.55498);
        ((GeneralPath) shape).curveTo(50.961, 173.412, 50.729, 173.34, 50.371,
                173.34);
        ((GeneralPath) shape).moveTo(52.129, 172.676);
        ((GeneralPath) shape).lineTo(52.129, 176.48099);
        ((GeneralPath) shape).curveTo(52.129, 177.08998, 52.006, 177.50798,
                51.763, 177.73499);
        ((GeneralPath) shape).curveTo(51.517002, 177.96399, 51.065, 178.07498,
                50.405, 178.07498);
        ((GeneralPath) shape).curveTo(49.765, 178.07498, 49.338997, 177.98898,
                49.121998, 177.81699);
        ((GeneralPath) shape).curveTo(48.907997, 177.64499, 48.798996, 177.305,
                48.798996, 176.79698);
        ((GeneralPath) shape).lineTo(49.663998, 176.79698);
        ((GeneralPath) shape).curveTo(49.663998, 177.03899, 49.708996,
                177.19298, 49.801, 177.26398);
        ((GeneralPath) shape).curveTo(49.892998, 177.33197, 50.093, 177.36798,
                50.409, 177.36798);
        ((GeneralPath) shape).curveTo(50.963, 177.36798, 51.24, 177.13898,
                51.24, 176.67998);
        ((GeneralPath) shape).lineTo(51.24, 175.83998);
        ((GeneralPath) shape).lineTo(51.219, 175.83598);
        ((GeneralPath) shape).curveTo(51.063004, 176.23198, 50.698, 176.43399,
                50.127003, 176.43399);
        ((GeneralPath) shape).curveTo(49.564003, 176.43399, 49.180004, 176.297,
                48.973003, 176.02399);
        ((GeneralPath) shape).curveTo(48.766003, 175.75299, 48.661003,
                175.24098, 48.661003, 174.49498);
        ((GeneralPath) shape).curveTo(48.661003, 173.79198, 48.765003,
                173.30698, 48.973003, 173.03798);
        ((GeneralPath) shape).curveTo(49.180004, 172.76797, 49.552002,
                172.63599, 50.098003, 172.63599);
        ((GeneralPath) shape).curveTo(50.691, 172.63599, 51.077003, 172.85498,
                51.264004, 173.29599);
        ((GeneralPath) shape).lineTo(51.284004, 173.29599);
        ((GeneralPath) shape).lineTo(51.241005, 172.67899);
        ((GeneralPath) shape).lineTo(52.129, 172.676);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_186;
        g.setTransform(defaultTransform__0_186);
        g.setClip(clip__0_186);
        float alpha__0_187 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_187 = g.getClip();
        AffineTransform defaultTransform__0_187 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_187 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(53.896, 172.676);
        ((GeneralPath) shape).lineTo(53.896, 176.40999);
        ((GeneralPath) shape).lineTo(53.001, 176.40999);
        ((GeneralPath) shape).lineTo(53.001, 172.676);
        ((GeneralPath) shape).lineTo(53.896, 172.676);
        ((GeneralPath) shape).moveTo(53.896, 171.078);
        ((GeneralPath) shape).lineTo(53.896, 171.822);
        ((GeneralPath) shape).lineTo(53.001, 171.822);
        ((GeneralPath) shape).lineTo(53.001, 171.078);
        ((GeneralPath) shape).lineTo(53.896, 171.078);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_187;
        g.setTransform(defaultTransform__0_187);
        g.setClip(clip__0_187);
        float alpha__0_188 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_188 = g.getClip();
        AffineTransform defaultTransform__0_188 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_188 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(54.774, 172.676);
        ((GeneralPath) shape).lineTo(55.658997, 172.676);
        ((GeneralPath) shape).lineTo(55.623997, 173.305);
        ((GeneralPath) shape).lineTo(55.644997, 173.30899);
        ((GeneralPath) shape).curveTo(55.817997, 172.85999, 56.207996, 172.633,
                56.813995, 172.633);
        ((GeneralPath) shape).curveTo(57.694996, 172.633, 58.133995, 173.043,
                58.133995, 173.86699);
        ((GeneralPath) shape).lineTo(58.133995, 176.40999);
        ((GeneralPath) shape).lineTo(57.240993, 176.40999);
        ((GeneralPath) shape).lineTo(57.240993, 174.01698);
        ((GeneralPath) shape).lineTo(57.220993, 173.75499);
        ((GeneralPath) shape).curveTo(57.179993, 173.478, 56.95999, 173.33699,
                56.565994, 173.33699);
        ((GeneralPath) shape).curveTo(55.963993, 173.33699, 55.665993, 173.622,
                55.665993, 174.191);
        ((GeneralPath) shape).lineTo(55.665993, 176.40799);
        ((GeneralPath) shape).lineTo(54.77299, 176.40799);
        ((GeneralPath) shape).lineTo(54.77299, 172.676);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_188;
        g.setTransform(defaultTransform__0_188);
        g.setClip(clip__0_188);
        float alpha__0_189 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_189 = g.getClip();
        AffineTransform defaultTransform__0_189 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_189 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(61.415, 174.156);
        ((GeneralPath) shape).lineTo(61.411, 174.01001);
        ((GeneralPath) shape).curveTo(61.411, 173.71301, 61.36, 173.522,
                61.257, 173.432);
        ((GeneralPath) shape).curveTo(61.156, 173.34401, 60.934, 173.29901,
                60.589, 173.29901);
        ((GeneralPath) shape).curveTo(60.257, 173.29901, 60.041, 173.352,
                59.942, 173.45901);
        ((GeneralPath) shape).curveTo(59.84, 173.56302, 59.792, 173.79901,
                59.792, 174.15602);
        ((GeneralPath) shape).lineTo(61.415, 174.15602);
        ((GeneralPath) shape).moveTo(61.406, 175.215);
        ((GeneralPath) shape).lineTo(62.302998, 175.215);
        ((GeneralPath) shape).lineTo(62.302998, 175.36);
        ((GeneralPath) shape).curveTo(62.302998, 176.089, 61.754997, 176.456,
                60.663998, 176.456);
        ((GeneralPath) shape).curveTo(59.922997, 176.456, 59.439, 176.331,
                59.21, 176.077);
        ((GeneralPath) shape).curveTo(58.982, 175.823, 58.867, 175.29, 58.867,
                174.472);
        ((GeneralPath) shape).curveTo(58.867, 173.745, 58.986, 173.257, 59.226,
                173.007);
        ((GeneralPath) shape).curveTo(59.462, 172.757, 59.932003, 172.632,
                60.623, 172.632);
        ((GeneralPath) shape).curveTo(61.288002, 172.632, 61.734, 172.753,
                61.960003, 172.99701);
        ((GeneralPath) shape).curveTo(62.189003, 173.23701, 62.301003, 173.718,
                62.301003, 174.43301);
        ((GeneralPath) shape).lineTo(62.301003, 174.70601);
        ((GeneralPath) shape).lineTo(59.78, 174.70601);
        ((GeneralPath) shape).curveTo(59.775997, 174.78801, 59.772, 174.843,
                59.772, 174.87001);
        ((GeneralPath) shape).curveTo(59.772, 175.23502, 59.829, 175.479,
                59.941998, 175.602);
        ((GeneralPath) shape).curveTo(60.054996, 175.723, 60.279, 175.78801,
                60.617996, 175.78801);
        ((GeneralPath) shape).curveTo(60.945995, 175.78801, 61.157997, 175.753,
                61.256996, 175.68102);
        ((GeneralPath) shape).curveTo(61.356, 175.608, 61.406, 175.453, 61.406,
                175.215);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_189;
        g.setTransform(defaultTransform__0_189);
        g.setClip(clip__0_189);
        float alpha__0_190 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_190 = g.getClip();
        AffineTransform defaultTransform__0_190 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_190 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(67.188, 171.984);
        ((GeneralPath) shape).lineTo(67.188, 176.41);
        ((GeneralPath) shape).lineTo(66.178, 176.41);
        ((GeneralPath) shape).lineTo(66.178, 171.984);
        ((GeneralPath) shape).lineTo(64.642, 171.984);
        ((GeneralPath) shape).lineTo(64.642, 171.078);
        ((GeneralPath) shape).lineTo(68.778, 171.078);
        ((GeneralPath) shape).lineTo(68.778, 171.984);
        ((GeneralPath) shape).lineTo(67.188, 171.984);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_190;
        g.setTransform(defaultTransform__0_190);
        g.setClip(clip__0_190);
        float alpha__0_191 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_191 = g.getClip();
        AffineTransform defaultTransform__0_191 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_191 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(72.331, 172.676);
        ((GeneralPath) shape).lineTo(71.352005, 176.64499);
        ((GeneralPath) shape).curveTo(71.213005, 177.208, 71.04201, 177.58398,
                70.837006, 177.77998);
        ((GeneralPath) shape).curveTo(70.63601, 177.97298, 70.309006,
                178.07098, 69.85801, 178.07098);
        ((GeneralPath) shape).curveTo(69.75401, 178.07098, 69.65101, 178.06699,
                69.54201, 178.05498);
        ((GeneralPath) shape).lineTo(69.54201, 177.39497);
        ((GeneralPath) shape).curveTo(69.62001, 177.39897, 69.687004,
                177.40297, 69.73701, 177.40297);
        ((GeneralPath) shape).curveTo(70.115005, 177.40297, 70.35701,
                177.07297, 70.45801, 176.41296);
        ((GeneralPath) shape).lineTo(70.00201, 176.41296);
        ((GeneralPath) shape).lineTo(68.782005, 172.67897);
        ((GeneralPath) shape).lineTo(69.741005, 172.67897);
        ((GeneralPath) shape).lineTo(70.20901, 174.26097);
        ((GeneralPath) shape).lineTo(70.44301, 175.05397);
        ((GeneralPath) shape).curveTo(70.45501, 175.10497, 70.491005,
                175.23798, 70.55201, 175.44997);
        ((GeneralPath) shape).lineTo(70.66301, 175.84596);
        ((GeneralPath) shape).lineTo(70.68401, 175.84596);
        ((GeneralPath) shape).lineTo(70.766014, 175.44997);
        ((GeneralPath) shape).curveTo(70.807014, 175.24896, 70.832016,
                175.11397, 70.848015, 175.05397);
        ((GeneralPath) shape).lineTo(71.03101, 174.26097);
        ((GeneralPath) shape).lineTo(71.389015, 172.67897);
        ((GeneralPath) shape).lineTo(72.331, 172.676);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_191;
        g.setTransform(defaultTransform__0_191);
        g.setClip(clip__0_191);
        float alpha__0_192 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_192 = g.getClip();
        AffineTransform defaultTransform__0_192 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_192 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(74.493, 173.336);
        ((GeneralPath) shape).curveTo(74.154, 173.336, 73.931, 173.412, 73.826,
                173.565);
        ((GeneralPath) shape).curveTo(73.722, 173.717, 73.67, 174.044, 73.67,
                174.544);
        ((GeneralPath) shape).curveTo(73.67, 175.026, 73.727, 175.349, 73.837,
                175.509);
        ((GeneralPath) shape).curveTo(73.948, 175.667, 74.175995, 175.74701,
                74.521996, 175.74701);
        ((GeneralPath) shape).curveTo(74.868996, 175.74701, 75.092995, 175.673,
                75.2, 175.518);
        ((GeneralPath) shape).curveTo(75.30399, 175.37001, 75.355995, 175.043,
                75.355995, 174.545);
        ((GeneralPath) shape).curveTo(75.355995, 174.035, 75.300995, 173.705,
                75.198, 173.55699);
        ((GeneralPath) shape).curveTo(75.092, 173.41, 74.857, 173.336, 74.493,
                173.336);
        ((GeneralPath) shape).moveTo(72.735, 172.676);
        ((GeneralPath) shape).lineTo(73.643, 172.676);
        ((GeneralPath) shape).lineTo(73.607994, 173.23099);
        ((GeneralPath) shape).lineTo(73.62699, 173.23499);
        ((GeneralPath) shape).curveTo(73.84199, 172.83499, 74.24299, 172.63098,
                74.83199, 172.63098);
        ((GeneralPath) shape).curveTo(75.37399, 172.63098, 75.74899, 172.76598,
                75.952995, 173.03499);
        ((GeneralPath) shape).curveTo(76.156, 173.305, 76.258995, 173.79498,
                76.258995, 174.51);
        ((GeneralPath) shape).curveTo(76.258995, 175.254, 76.158, 175.76399,
                75.954994, 176.041);
        ((GeneralPath) shape).curveTo(75.75199, 176.314, 75.37799, 176.455,
                74.827995, 176.455);
        ((GeneralPath) shape).curveTo(74.243996, 176.455, 73.86099, 176.26,
                73.68199, 175.869);
        ((GeneralPath) shape).lineTo(73.663994, 175.869);
        ((GeneralPath) shape).lineTo(73.663994, 178.014);
        ((GeneralPath) shape).lineTo(72.731995, 178.014);
        ((GeneralPath) shape).lineTo(72.735, 172.676);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_192;
        g.setTransform(defaultTransform__0_192);
        g.setClip(clip__0_192);
        float alpha__0_193 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_193 = g.getClip();
        AffineTransform defaultTransform__0_193 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_193 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(79.415, 174.156);
        ((GeneralPath) shape).lineTo(79.411, 174.01001);
        ((GeneralPath) shape).curveTo(79.411, 173.71301, 79.36, 173.522,
                79.257, 173.432);
        ((GeneralPath) shape).curveTo(79.156006, 173.34401, 78.934006,
                173.29901, 78.589005, 173.29901);
        ((GeneralPath) shape).curveTo(78.257, 173.29901, 78.04101, 173.352,
                77.942, 173.45901);
        ((GeneralPath) shape).curveTo(77.840004, 173.56302, 77.792, 173.79901,
                77.792, 174.15602);
        ((GeneralPath) shape).lineTo(79.415, 174.15602);
        ((GeneralPath) shape).moveTo(79.407, 175.215);
        ((GeneralPath) shape).lineTo(80.304, 175.215);
        ((GeneralPath) shape).lineTo(80.304, 175.36);
        ((GeneralPath) shape).curveTo(80.304, 176.089, 79.756004, 176.456,
                78.665, 176.456);
        ((GeneralPath) shape).curveTo(77.924, 176.456, 77.44, 176.331, 77.211,
                176.077);
        ((GeneralPath) shape).curveTo(76.983, 175.823, 76.868, 175.29, 76.868,
                174.472);
        ((GeneralPath) shape).curveTo(76.868, 173.745, 76.987, 173.257, 77.227,
                173.007);
        ((GeneralPath) shape).curveTo(77.463, 172.757, 77.933, 172.632, 78.624,
                172.632);
        ((GeneralPath) shape).curveTo(79.289, 172.632, 79.735, 172.753, 79.961,
                172.99701);
        ((GeneralPath) shape).curveTo(80.188995, 173.23701, 80.302, 173.718,
                80.302, 174.43301);
        ((GeneralPath) shape).lineTo(80.302, 174.70601);
        ((GeneralPath) shape).lineTo(77.78, 174.70601);
        ((GeneralPath) shape).curveTo(77.776, 174.78801, 77.771996, 174.843,
                77.771996, 174.87001);
        ((GeneralPath) shape).curveTo(77.771996, 175.23502, 77.828995, 175.479,
                77.94199, 175.602);
        ((GeneralPath) shape).curveTo(78.05499, 175.723, 78.27899, 175.78801,
                78.618, 175.78801);
        ((GeneralPath) shape).curveTo(78.946, 175.78801, 79.158, 175.753,
                79.256996, 175.68102);
        ((GeneralPath) shape).curveTo(79.356, 175.607, 79.407, 175.453, 79.407,
                175.215);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_193;
        g.setTransform(defaultTransform__0_193);
        g.setClip(clip__0_193);
        float alpha__0_194 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_194 = g.getClip();
        AffineTransform defaultTransform__0_194 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_194 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(81.898, 172.676);
        ((GeneralPath) shape).lineTo(81.898, 173.637);
        ((GeneralPath) shape).lineTo(81.003006, 173.637);
        ((GeneralPath) shape).lineTo(81.003006, 172.676);
        ((GeneralPath) shape).lineTo(81.898, 172.676);
        ((GeneralPath) shape).moveTo(81.898, 175.448);
        ((GeneralPath) shape).lineTo(81.898, 176.411);
        ((GeneralPath) shape).lineTo(81.003006, 176.411);
        ((GeneralPath) shape).lineTo(81.003006, 175.448);
        ((GeneralPath) shape).lineTo(81.898, 175.448);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_194;
        g.setTransform(defaultTransform__0_194);
        g.setClip(clip__0_194);
        float alpha__0_195 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_195 = g.getClip();
        AffineTransform defaultTransform__0_195 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_195 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(48.684, 141.2);
        ((GeneralPath) shape).lineTo(49.697998, 141.2);
        ((GeneralPath) shape).lineTo(49.697998, 141.384);
        ((GeneralPath) shape).curveTo(49.697998, 142.126, 49.563, 142.605,
                49.292, 142.822);
        ((GeneralPath) shape).curveTo(49.023, 143.041, 48.424, 143.14801,
                47.499, 143.14801);
        ((GeneralPath) shape).curveTo(46.451, 143.14801, 45.805, 142.97601,
                45.561, 142.632);
        ((GeneralPath) shape).curveTo(45.317, 142.288, 45.199, 141.37001,
                45.199, 139.87401);
        ((GeneralPath) shape).curveTo(45.199, 138.99501, 45.363003, 138.41501,
                45.690002, 138.13802);
        ((GeneralPath) shape).curveTo(46.017002, 137.86002, 46.702003,
                137.72202, 47.744003, 137.72202);
        ((GeneralPath) shape).curveTo(48.502003, 137.72202, 49.009003,
                137.83502, 49.265003, 138.06302);
        ((GeneralPath) shape).curveTo(49.518, 138.29202, 49.647003, 138.74503,
                49.647003, 139.42001);
        ((GeneralPath) shape).lineTo(49.651005, 139.542);
        ((GeneralPath) shape).lineTo(48.637005, 139.542);
        ((GeneralPath) shape).lineTo(48.637005, 139.403);
        ((GeneralPath) shape).curveTo(48.637005, 139.056, 48.573006, 138.833,
                48.440006, 138.733);
        ((GeneralPath) shape).curveTo(48.310005, 138.636, 48.007008, 138.587,
                47.542007, 138.587);
        ((GeneralPath) shape).curveTo(46.917007, 138.587, 46.54001, 138.66301,
                46.41401, 138.81601);
        ((GeneralPath) shape).curveTo(46.28901, 138.96901, 46.22601, 139.425,
                46.22601, 140.18301);
        ((GeneralPath) shape).curveTo(46.22601, 141.20401, 46.28201, 141.80801,
                46.395008, 141.99602);
        ((GeneralPath) shape).curveTo(46.50601, 142.18402, 46.87301, 142.27502,
                47.493008, 142.27502);
        ((GeneralPath) shape).curveTo(47.994007, 142.27502, 48.319008,
                142.22403, 48.472008, 142.12102);
        ((GeneralPath) shape).curveTo(48.618008, 142.01701, 48.696007,
                141.78902, 48.696007, 141.43301);
        ((GeneralPath) shape).lineTo(48.684, 141.2);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_195;
        g.setTransform(defaultTransform__0_195);
        g.setClip(clip__0_195);
        float alpha__0_196 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_196 = g.getClip();
        AffineTransform defaultTransform__0_196 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_196 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(50.327, 139.368);
        ((GeneralPath) shape).lineTo(51.222, 139.368);
        ((GeneralPath) shape).lineTo(51.166, 139.88899);
        ((GeneralPath) shape).lineTo(51.187, 139.89299);
        ((GeneralPath) shape).curveTo(51.4, 139.50299, 51.735, 139.30798,
                52.193, 139.30798);
        ((GeneralPath) shape).curveTo(52.847, 139.30798, 53.173, 139.72198,
                53.173, 140.54999);
        ((GeneralPath) shape).lineTo(53.173, 140.81198);
        ((GeneralPath) shape).lineTo(52.33, 140.81198);
        ((GeneralPath) shape).curveTo(52.34, 140.70998, 52.348003, 140.64398,
                52.348003, 140.61298);
        ((GeneralPath) shape).curveTo(52.348003, 140.21597, 52.195004,
                140.01497, 51.883003, 140.01497);
        ((GeneralPath) shape).curveTo(51.445004, 140.01497, 51.222004,
                140.30997, 51.222004, 140.90196);
        ((GeneralPath) shape).lineTo(51.222004, 143.10097);
        ((GeneralPath) shape).lineTo(50.327003, 143.10097);
        ((GeneralPath) shape).lineTo(50.327003, 139.368);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_196;
        g.setTransform(defaultTransform__0_196);
        g.setClip(clip__0_196);
        float alpha__0_197 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_197 = g.getClip();
        AffineTransform defaultTransform__0_197 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_197 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(56.975, 139.368);
        ((GeneralPath) shape).lineTo(56.975, 143.09999);
        ((GeneralPath) shape).lineTo(56.079998, 143.09999);
        ((GeneralPath) shape).lineTo(56.130997, 142.45898);
        ((GeneralPath) shape).lineTo(56.114998, 142.45598);
        ((GeneralPath) shape).curveTo(55.941998, 142.91298, 55.541996,
                143.14297, 54.913998, 143.14297);
        ((GeneralPath) shape).curveTo(54.069996, 143.14297, 53.647, 142.72197,
                53.647, 141.87297);
        ((GeneralPath) shape).lineTo(53.647, 139.36298);
        ((GeneralPath) shape).lineTo(54.54, 139.36298);
        ((GeneralPath) shape).lineTo(54.54, 141.65797);
        ((GeneralPath) shape).curveTo(54.54, 141.97197, 54.582, 142.18198,
                54.672, 142.28497);
        ((GeneralPath) shape).curveTo(54.762, 142.38498, 54.943, 142.43297,
                55.224, 142.43297);
        ((GeneralPath) shape).curveTo(55.793, 142.43297, 56.079, 142.08897,
                56.079, 141.40196);
        ((GeneralPath) shape).lineTo(56.079, 139.36296);
        ((GeneralPath) shape).lineTo(56.975, 139.36296);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_197;
        g.setTransform(defaultTransform__0_197);
        g.setClip(clip__0_197);
        float alpha__0_198 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_198 = g.getClip();
        AffineTransform defaultTransform__0_198 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_198 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(58.773, 139.368);
        ((GeneralPath) shape).lineTo(58.773, 143.09999);
        ((GeneralPath) shape).lineTo(57.88, 143.09999);
        ((GeneralPath) shape).lineTo(57.88, 139.368);
        ((GeneralPath) shape).lineTo(58.773, 139.368);
        ((GeneralPath) shape).moveTo(58.773, 137.77);
        ((GeneralPath) shape).lineTo(58.773, 138.516);
        ((GeneralPath) shape).lineTo(57.88, 138.516);
        ((GeneralPath) shape).lineTo(57.88, 137.77);
        ((GeneralPath) shape).lineTo(58.773, 137.77);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_198;
        g.setTransform(defaultTransform__0_198);
        g.setClip(clip__0_198);
        float alpha__0_199 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_199 = g.getClip();
        AffineTransform defaultTransform__0_199 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_199 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(62.764, 140.395);
        ((GeneralPath) shape).lineTo(61.891, 140.395);
        ((GeneralPath) shape).curveTo(61.886997, 140.364, 61.881, 140.34001,
                61.877, 140.32701);
        ((GeneralPath) shape).curveTo(61.858997, 140.145, 61.810997, 140.03601,
                61.725, 139.99);
        ((GeneralPath) shape).curveTo(61.641, 139.947, 61.435997, 139.925,
                61.111, 139.925);
        ((GeneralPath) shape).curveTo(60.647, 139.925, 60.413, 140.073, 60.413,
                140.378);
        ((GeneralPath) shape).curveTo(60.413, 140.58301, 60.454, 140.70601,
                60.536, 140.74501);
        ((GeneralPath) shape).curveTo(60.618, 140.78201, 60.894, 140.813,
                61.369, 140.839);
        ((GeneralPath) shape).curveTo(62.003998, 140.87001, 62.419, 140.955,
                62.612, 141.093);
        ((GeneralPath) shape).curveTo(62.803, 141.23, 62.901, 141.513, 62.901,
                141.94101);
        ((GeneralPath) shape).curveTo(62.901, 142.39601, 62.775, 142.71301,
                62.517002, 142.88802);
        ((GeneralPath) shape).curveTo(62.262, 143.06401, 61.799004, 143.15202,
                61.139004, 143.15202);
        ((GeneralPath) shape).curveTo(60.501003, 143.15202, 60.067005,
                143.07402, 59.831005, 142.91603);
        ((GeneralPath) shape).curveTo(59.599007, 142.75803, 59.480003,
                142.46103, 59.480003, 142.02303);
        ((GeneralPath) shape).lineTo(59.480003, 141.92903);
        ((GeneralPath) shape).lineTo(60.408005, 141.92903);
        ((GeneralPath) shape).curveTo(60.396004, 141.98003, 60.388004,
                142.02303, 60.387005, 142.05403);
        ((GeneralPath) shape).curveTo(60.351006, 142.38503, 60.605003,
                142.55003, 61.151005, 142.55003);
        ((GeneralPath) shape).curveTo(61.714005, 142.55003, 61.997005,
                142.38603, 61.997005, 142.05803);
        ((GeneralPath) shape).curveTo(61.997005, 141.74503, 61.821007,
                141.58603, 61.467007, 141.58603);
        ((GeneralPath) shape).curveTo(60.670006, 141.58603, 60.145008,
                141.51103, 59.892006, 141.35703);
        ((GeneralPath) shape).curveTo(59.639008, 141.20903, 59.512005,
                140.89203, 59.512005, 140.41803);
        ((GeneralPath) shape).curveTo(59.512005, 139.99203, 59.625004,
                139.70303, 59.859005, 139.55103);
        ((GeneralPath) shape).curveTo(60.088005, 139.40103, 60.534004,
                139.32202, 61.192005, 139.32202);
        ((GeneralPath) shape).curveTo(61.812004, 139.32202, 62.231007,
                139.39403, 62.445004, 139.54301);
        ((GeneralPath) shape).curveTo(62.657, 139.685, 62.764, 139.969, 62.764,
                140.395);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_199;
        g.setTransform(defaultTransform__0_199);
        g.setClip(clip__0_199);
        float alpha__0_200 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_200 = g.getClip();
        AffineTransform defaultTransform__0_200 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_200 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(64.498, 139.368);
        ((GeneralPath) shape).lineTo(64.498, 143.09999);
        ((GeneralPath) shape).lineTo(63.605, 143.09999);
        ((GeneralPath) shape).lineTo(63.605, 139.368);
        ((GeneralPath) shape).lineTo(64.498, 139.368);
        ((GeneralPath) shape).moveTo(64.498, 137.77);
        ((GeneralPath) shape).lineTo(64.498, 138.516);
        ((GeneralPath) shape).lineTo(63.605, 138.516);
        ((GeneralPath) shape).lineTo(63.605, 137.77);
        ((GeneralPath) shape).lineTo(64.498, 137.77);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_200;
        g.setTransform(defaultTransform__0_200);
        g.setClip(clip__0_200);
        float alpha__0_201 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_201 = g.getClip();
        AffineTransform defaultTransform__0_201 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_201 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(65.378, 139.368);
        ((GeneralPath) shape).lineTo(66.265, 139.368);
        ((GeneralPath) shape).lineTo(66.229996, 139.997);
        ((GeneralPath) shape).lineTo(66.24999, 140.00099);
        ((GeneralPath) shape).curveTo(66.42299, 139.55199, 66.812996, 139.325,
                67.41899, 139.325);
        ((GeneralPath) shape).curveTo(68.29999, 139.325, 68.74099, 139.733,
                68.74099, 140.558);
        ((GeneralPath) shape).lineTo(68.74099, 143.1);
        ((GeneralPath) shape).lineTo(67.84799, 143.1);
        ((GeneralPath) shape).lineTo(67.84799, 140.709);
        ((GeneralPath) shape).lineTo(67.82699, 140.44499);
        ((GeneralPath) shape).curveTo(67.78599, 140.17, 67.567986, 140.027,
                67.17199, 140.027);
        ((GeneralPath) shape).curveTo(66.57199, 140.027, 66.27399, 140.312,
                66.27399, 140.883);
        ((GeneralPath) shape).lineTo(66.27399, 143.097);
        ((GeneralPath) shape).lineTo(65.37899, 143.097);
        ((GeneralPath) shape).lineTo(65.37899, 139.368);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_201;
        g.setTransform(defaultTransform__0_201);
        g.setClip(clip__0_201);
        float alpha__0_202 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_202 = g.getClip();
        AffineTransform defaultTransform__0_202 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_202 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(71.184, 140.031);
        ((GeneralPath) shape).curveTo(70.839, 140.031, 70.620995, 140.1,
                70.522995, 140.24);
        ((GeneralPath) shape).curveTo(70.423, 140.38101, 70.37699, 140.695,
                70.37699, 141.19101);
        ((GeneralPath) shape).curveTo(70.37699, 141.71701, 70.425995,
                142.05301, 70.522995, 142.201);
        ((GeneralPath) shape).curveTo(70.619995, 142.34601, 70.84499, 142.42,
                71.19499, 142.42);
        ((GeneralPath) shape).curveTo(71.54599, 142.42, 71.771996, 142.345,
                71.87699, 142.193);
        ((GeneralPath) shape).curveTo(71.98099, 142.047, 72.03299, 141.71399,
                72.03299, 141.198);
        ((GeneralPath) shape).curveTo(72.03299, 140.708, 71.98199, 140.38899,
                71.87699, 140.247);
        ((GeneralPath) shape).curveTo(71.775, 140.104, 71.542, 140.031, 71.184,
                140.031);
        ((GeneralPath) shape).moveTo(72.942, 139.368);
        ((GeneralPath) shape).lineTo(72.942, 143.17299);
        ((GeneralPath) shape).curveTo(72.942, 143.77998, 72.819, 144.19798,
                72.574005, 144.42699);
        ((GeneralPath) shape).curveTo(72.328, 144.65399, 71.87601, 144.76698,
                71.215004, 144.76698);
        ((GeneralPath) shape).curveTo(70.576004, 144.76698, 70.151, 144.68098,
                69.934006, 144.50899);
        ((GeneralPath) shape).curveTo(69.718, 144.33699, 69.61101, 143.997,
                69.61101, 143.48799);
        ((GeneralPath) shape).lineTo(70.476006, 143.48799);
        ((GeneralPath) shape).curveTo(70.476006, 143.73, 70.521, 143.88399,
                70.61301, 143.956);
        ((GeneralPath) shape).curveTo(70.702, 144.02199, 70.90501, 144.06,
                71.22101, 144.06);
        ((GeneralPath) shape).curveTo(71.77501, 144.06, 72.05001, 143.831,
                72.05001, 143.372);
        ((GeneralPath) shape).lineTo(72.05001, 142.53);
        ((GeneralPath) shape).lineTo(72.030014, 142.527);
        ((GeneralPath) shape).curveTo(71.87202, 142.92499, 71.50801, 143.12599,
                70.93801, 143.12599);
        ((GeneralPath) shape).curveTo(70.37501, 143.12599, 69.98901, 142.98799,
                69.78201, 142.71599);
        ((GeneralPath) shape).curveTo(69.57501, 142.443, 69.472015, 141.93298,
                69.472015, 141.18498);
        ((GeneralPath) shape).curveTo(69.472015, 140.48099, 69.57601,
                139.99698, 69.78201, 139.72699);
        ((GeneralPath) shape).curveTo(69.98901, 139.45999, 70.363014, 139.327,
                70.90701, 139.327);
        ((GeneralPath) shape).curveTo(71.500015, 139.327, 71.88702, 139.54599,
                72.07301, 139.985);
        ((GeneralPath) shape).lineTo(72.09201, 139.985);
        ((GeneralPath) shape).lineTo(72.05001, 139.37);
        ((GeneralPath) shape).lineTo(72.942, 139.368);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_202;
        g.setTransform(defaultTransform__0_202);
        g.setClip(clip__0_202);
        float alpha__0_203 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_203 = g.getClip();
        AffineTransform defaultTransform__0_203 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_203 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(74.67, 139.368);
        ((GeneralPath) shape).lineTo(74.67, 140.329);
        ((GeneralPath) shape).lineTo(73.777, 140.329);
        ((GeneralPath) shape).lineTo(73.777, 139.368);
        ((GeneralPath) shape).lineTo(74.67, 139.368);
        ((GeneralPath) shape).moveTo(74.67, 142.141);
        ((GeneralPath) shape).lineTo(74.67, 143.102);
        ((GeneralPath) shape).lineTo(73.777, 143.102);
        ((GeneralPath) shape).lineTo(73.777, 142.141);
        ((GeneralPath) shape).lineTo(74.67, 142.141);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_203;
        g.setTransform(defaultTransform__0_203);
        g.setClip(clip__0_203);
        float alpha__0_204 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_204 = g.getClip();
        AffineTransform defaultTransform__0_204 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_204 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(46.407, 149.122);
        ((GeneralPath) shape).lineTo(46.407, 150.56);
        ((GeneralPath) shape).lineTo(48.792, 150.56);
        ((GeneralPath) shape).lineTo(48.792, 151.411);
        ((GeneralPath) shape).lineTo(46.407, 151.411);
        ((GeneralPath) shape).lineTo(46.407, 153.602);
        ((GeneralPath) shape).lineTo(45.397, 153.602);
        ((GeneralPath) shape).lineTo(45.397, 148.27);
        ((GeneralPath) shape).lineTo(48.921, 148.27);
        ((GeneralPath) shape).lineTo(48.921, 149.122);
        ((GeneralPath) shape).lineTo(46.407, 149.122);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_204;
        g.setTransform(defaultTransform__0_204);
        g.setClip(clip__0_204);
        float alpha__0_205 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_205 = g.getClip();
        AffineTransform defaultTransform__0_205 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_205 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new Rectangle2D.Double(49.51300048828125, 148.27000427246094,
                0.8930000066757202, 5.331999778747559);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_205;
        g.setTransform(defaultTransform__0_205);
        g.setClip(clip__0_205);
        float alpha__0_206 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_206 = g.getClip();
        AffineTransform defaultTransform__0_206 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_206 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(52.809, 151.93);
        ((GeneralPath) shape).curveTo(52.306, 151.93, 52.052998, 152.10399,
                52.052998, 152.45299);
        ((GeneralPath) shape).curveTo(52.052998, 152.693, 52.103996, 152.85098,
                52.206997, 152.93);
        ((GeneralPath) shape).curveTo(52.310997, 153.00499, 52.527996,
                153.04399, 52.861996, 153.04399);
        ((GeneralPath) shape).curveTo(53.403996, 153.04399, 53.674995,
                152.85799, 53.674995, 152.493);
        ((GeneralPath) shape).curveTo(53.678, 152.118, 53.39, 151.93, 52.809,
                151.93);
        ((GeneralPath) shape).moveTo(52.212, 150.95);
        ((GeneralPath) shape).lineTo(51.3, 150.95);
        ((GeneralPath) shape).curveTo(51.3, 150.50299, 51.404, 150.204, 51.613,
                150.052);
        ((GeneralPath) shape).curveTo(51.82, 149.90201, 52.232998, 149.825,
                52.849, 149.825);
        ((GeneralPath) shape).curveTo(53.518997, 149.825, 53.972, 149.91699,
                54.208, 150.09999);
        ((GeneralPath) shape).curveTo(54.444, 150.286, 54.562, 150.63799,
                54.562, 151.161);
        ((GeneralPath) shape).lineTo(54.562, 153.59999);
        ((GeneralPath) shape).lineTo(53.67, 153.59999);
        ((GeneralPath) shape).lineTo(53.711998, 153.088);
        ((GeneralPath) shape).lineTo(53.690998, 153.084);
        ((GeneralPath) shape).curveTo(53.518997, 153.456, 53.123997, 153.643,
                52.502, 153.643);
        ((GeneralPath) shape).curveTo(51.603, 153.643, 51.149, 153.261, 51.149,
                152.49301);
        ((GeneralPath) shape).curveTo(51.149, 151.71802, 51.608997, 151.32901,
                52.532997, 151.32901);
        ((GeneralPath) shape).curveTo(53.148, 151.32901, 53.523, 151.47002,
                53.656, 151.755);
        ((GeneralPath) shape).lineTo(53.671997, 151.755);
        ((GeneralPath) shape).lineTo(53.671997, 151.149);
        ((GeneralPath) shape).curveTo(53.671997, 150.858, 53.621, 150.667,
                53.521996, 150.569);
        ((GeneralPath) shape).curveTo(53.418995, 150.475, 53.211994, 150.426,
                52.897995, 150.426);
        ((GeneralPath) shape).curveTo(52.44, 150.426, 52.212, 150.601, 52.212,
                150.95);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_206;
        g.setTransform(defaultTransform__0_206);
        g.setClip(clip__0_206);
        float alpha__0_207 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_207 = g.getClip();
        AffineTransform defaultTransform__0_207 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_207 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(55.451, 149.868);
        ((GeneralPath) shape).lineTo(56.336, 149.868);
        ((GeneralPath) shape).lineTo(56.301, 150.497);
        ((GeneralPath) shape).lineTo(56.322, 150.50099);
        ((GeneralPath) shape).curveTo(56.495, 150.05199, 56.885, 149.825,
                57.490997, 149.825);
        ((GeneralPath) shape).curveTo(58.371998, 149.825, 58.812996, 150.233,
                58.812996, 151.058);
        ((GeneralPath) shape).lineTo(58.812996, 153.6);
        ((GeneralPath) shape).lineTo(57.917995, 153.6);
        ((GeneralPath) shape).lineTo(57.917995, 151.209);
        ((GeneralPath) shape).lineTo(57.897995, 150.94499);
        ((GeneralPath) shape).curveTo(57.856995, 150.67, 57.638996, 150.527,
                57.242996, 150.527);
        ((GeneralPath) shape).curveTo(56.642998, 150.527, 56.344997, 150.812,
                56.344997, 151.383);
        ((GeneralPath) shape).lineTo(56.344997, 153.597);
        ((GeneralPath) shape).lineTo(55.449997, 153.597);
        ((GeneralPath) shape).lineTo(55.449997, 149.868);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_207;
        g.setTransform(defaultTransform__0_207);
        g.setClip(clip__0_207);
        float alpha__0_208 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_208 = g.getClip();
        AffineTransform defaultTransform__0_208 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_208 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(60.575, 148.27);
        ((GeneralPath) shape).lineTo(60.575, 151.321);
        ((GeneralPath) shape).lineTo(60.805, 151.321);
        ((GeneralPath) shape).lineTo(61.811, 149.868);
        ((GeneralPath) shape).lineTo(62.847, 149.868);
        ((GeneralPath) shape).lineTo(61.549, 151.625);
        ((GeneralPath) shape).lineTo(63.112, 153.602);
        ((GeneralPath) shape).lineTo(62.005, 153.602);
        ((GeneralPath) shape).lineTo(60.793, 151.946);
        ((GeneralPath) shape).lineTo(60.575, 151.946);
        ((GeneralPath) shape).lineTo(60.575, 153.602);
        ((GeneralPath) shape).lineTo(59.682, 153.602);
        ((GeneralPath) shape).lineTo(59.682, 148.27);
        ((GeneralPath) shape).lineTo(60.575, 148.27);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_208;
        g.setTransform(defaultTransform__0_208);
        g.setClip(clip__0_208);
        float alpha__0_209 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_209 = g.getClip();
        AffineTransform defaultTransform__0_209 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_209 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(64.367, 149.868);
        ((GeneralPath) shape).lineTo(64.367, 150.829);
        ((GeneralPath) shape).lineTo(63.473995, 150.829);
        ((GeneralPath) shape).lineTo(63.473995, 149.868);
        ((GeneralPath) shape).lineTo(64.367, 149.868);
        ((GeneralPath) shape).moveTo(64.367, 152.641);
        ((GeneralPath) shape).lineTo(64.367, 153.602);
        ((GeneralPath) shape).lineTo(63.473995, 153.602);
        ((GeneralPath) shape).lineTo(63.473995, 152.641);
        ((GeneralPath) shape).lineTo(64.367, 152.641);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_209;
        g.setTransform(defaultTransform__0_209);
        g.setClip(clip__0_209);
        float alpha__0_210 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_210 = g.getClip();
        AffineTransform defaultTransform__0_210 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_210 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(260.188, 116.54);
        ((GeneralPath) shape).lineTo(261.202, 116.54);
        ((GeneralPath) shape).lineTo(261.202, 116.724);
        ((GeneralPath) shape).curveTo(261.202, 117.465996, 261.067, 117.945,
                260.796, 118.163);
        ((GeneralPath) shape).curveTo(260.52698, 118.380005, 259.93, 118.487,
                259.003, 118.487);
        ((GeneralPath) shape).curveTo(257.955, 118.487, 257.309, 118.315,
                257.065, 117.971);
        ((GeneralPath) shape).curveTo(256.821, 117.627, 256.702, 116.709,
                256.702, 115.213);
        ((GeneralPath) shape).curveTo(256.702, 114.334, 256.866, 113.754,
                257.194, 113.477);
        ((GeneralPath) shape).curveTo(257.521, 113.201996, 258.205, 113.061,
                259.248, 113.061);
        ((GeneralPath) shape).curveTo(260.00598, 113.061, 260.51498,
                113.173996, 260.76898, 113.403);
        ((GeneralPath) shape).curveTo(261.02197, 113.631996, 261.15097,
                114.085, 261.15097, 114.76);
        ((GeneralPath) shape).lineTo(261.15497, 114.881004);
        ((GeneralPath) shape).lineTo(260.14096, 114.881004);
        ((GeneralPath) shape).lineTo(260.14096, 114.744);
        ((GeneralPath) shape).curveTo(260.14096, 114.394005, 260.07697,
                114.172005, 259.94397, 114.074005);
        ((GeneralPath) shape).curveTo(259.81396, 113.97401, 259.51297, 113.928,
                259.04596, 113.928);
        ((GeneralPath) shape).curveTo(258.42096, 113.928, 258.04395, 114.004,
                257.91797, 114.158005);
        ((GeneralPath) shape).curveTo(257.79297, 114.30801, 257.72998,
                114.76501, 257.72998, 115.523);
        ((GeneralPath) shape).curveTo(257.72998, 116.54401, 257.78598, 117.148,
                257.899, 117.336006);
        ((GeneralPath) shape).curveTo(258.012, 117.52401, 258.37698,
                117.617004, 258.99698, 117.617004);
        ((GeneralPath) shape).curveTo(259.49698, 117.617004, 259.82297,
                117.564, 259.97498, 117.461006);
        ((GeneralPath) shape).curveTo(260.12097, 117.35701, 260.19897,
                117.129005, 260.19897, 116.773);
        ((GeneralPath) shape).lineTo(260.188, 116.54);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_210;
        g.setTransform(defaultTransform__0_210);
        g.setClip(clip__0_210);
        float alpha__0_211 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_211 = g.getClip();
        AffineTransform defaultTransform__0_211 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_211 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(261.829, 114.708);
        ((GeneralPath) shape).lineTo(262.724, 114.708);
        ((GeneralPath) shape).lineTo(262.667, 115.229004);
        ((GeneralPath) shape).lineTo(262.688, 115.233);
        ((GeneralPath) shape).curveTo(262.901, 114.842, 263.236, 114.647,
                263.694, 114.647);
        ((GeneralPath) shape).curveTo(264.349, 114.647, 264.674, 115.061005,
                264.674, 115.889);
        ((GeneralPath) shape).lineTo(264.674, 116.151);
        ((GeneralPath) shape).lineTo(263.832, 116.151);
        ((GeneralPath) shape).curveTo(263.843, 116.049, 263.85, 115.983,
                263.85, 115.954);
        ((GeneralPath) shape).curveTo(263.85, 115.556, 263.696, 115.354004,
                263.38602, 115.354004);
        ((GeneralPath) shape).curveTo(262.946, 115.354004, 262.72302, 115.649,
                262.72302, 116.243004);
        ((GeneralPath) shape).lineTo(262.72302, 118.44);
        ((GeneralPath) shape).lineTo(261.82803, 118.44);
        ((GeneralPath) shape).lineTo(261.829, 114.708);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_211;
        g.setTransform(defaultTransform__0_211);
        g.setClip(clip__0_211);
        float alpha__0_212 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_212 = g.getClip();
        AffineTransform defaultTransform__0_212 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_212 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(267.573, 116.188);
        ((GeneralPath) shape).lineTo(267.569, 116.042);
        ((GeneralPath) shape).curveTo(267.569, 115.745, 267.518, 115.554,
                267.415, 115.464);
        ((GeneralPath) shape).curveTo(267.314, 115.376, 267.09, 115.33099,
                266.747, 115.33099);
        ((GeneralPath) shape).curveTo(266.415, 115.33099, 266.199, 115.383995,
                266.1, 115.48899);
        ((GeneralPath) shape).curveTo(265.999, 115.593994, 265.95, 115.83099,
                265.95, 116.18799);
        ((GeneralPath) shape).lineTo(267.573, 116.18799);
        ((GeneralPath) shape).moveTo(267.565, 117.247);
        ((GeneralPath) shape).lineTo(268.461, 117.247);
        ((GeneralPath) shape).lineTo(268.461, 117.392);
        ((GeneralPath) shape).curveTo(268.461, 118.120995, 267.915, 118.486,
                266.823, 118.486);
        ((GeneralPath) shape).curveTo(266.082, 118.486, 265.599, 118.361,
                265.369, 118.107);
        ((GeneralPath) shape).curveTo(265.141, 117.855, 265.026, 117.32,
                265.026, 116.503006);
        ((GeneralPath) shape).curveTo(265.026, 115.77401, 265.145, 115.28801,
                265.385, 115.03801);
        ((GeneralPath) shape).curveTo(265.621, 114.78801, 266.08902, 114.66301,
                266.782, 114.66301);
        ((GeneralPath) shape).curveTo(267.44702, 114.66301, 267.893, 114.78401,
                268.11902, 115.02601);
        ((GeneralPath) shape).curveTo(268.34702, 115.268005, 268.458,
                115.74901, 268.458, 116.46401);
        ((GeneralPath) shape).lineTo(268.458, 116.735016);
        ((GeneralPath) shape).lineTo(265.937, 116.735016);
        ((GeneralPath) shape).curveTo(265.933, 116.81702, 265.92902,
                116.874016, 265.92902, 116.89902);
        ((GeneralPath) shape).curveTo(265.92902, 117.266014, 265.98502,
                117.51002, 266.09802, 117.63302);
        ((GeneralPath) shape).curveTo(266.209, 117.75402, 266.43503, 117.81702,
                266.77402, 117.81702);
        ((GeneralPath) shape).curveTo(267.10202, 117.81702, 267.31403,
                117.78402, 267.41302, 117.71201);
        ((GeneralPath) shape).curveTo(267.514, 117.64, 267.565, 117.485,
                267.565, 117.247);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_212;
        g.setTransform(defaultTransform__0_212);
        g.setClip(clip__0_212);
        float alpha__0_213 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_213 = g.getClip();
        AffineTransform defaultTransform__0_213 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_213 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(274.42, 114.708);
        ((GeneralPath) shape).lineTo(273.586, 118.44);
        ((GeneralPath) shape).lineTo(272.271, 118.44);
        ((GeneralPath) shape).lineTo(271.896, 116.768005);
        ((GeneralPath) shape).curveTo(271.832, 116.479004, 271.783, 116.26,
                271.75, 116.108);
        ((GeneralPath) shape).lineTo(271.686, 115.78);
        ((GeneralPath) shape).lineTo(271.62, 115.454);
        ((GeneralPath) shape).lineTo(271.593, 115.454);
        ((GeneralPath) shape).lineTo(271.529, 115.78);
        ((GeneralPath) shape).lineTo(271.46298, 116.112);
        ((GeneralPath) shape).curveTo(271.438, 116.258, 271.38998, 116.475,
                271.32297, 116.768);
        ((GeneralPath) shape).lineTo(270.94797, 118.439995);
        ((GeneralPath) shape).lineTo(269.61096, 118.439995);
        ((GeneralPath) shape).lineTo(268.78098, 114.70799);
        ((GeneralPath) shape).lineTo(269.679, 114.70799);
        ((GeneralPath) shape).lineTo(270.038, 116.43699);
        ((GeneralPath) shape).curveTo(270.081, 116.651985, 270.126, 116.88399,
                270.171, 117.13199);
        ((GeneralPath) shape).lineTo(270.23398, 117.47999);
        ((GeneralPath) shape).lineTo(270.29797, 117.82999);
        ((GeneralPath) shape).lineTo(270.31598, 117.82999);
        ((GeneralPath) shape).lineTo(270.39398, 117.47999);
        ((GeneralPath) shape).lineTo(270.46597, 117.13199);
        ((GeneralPath) shape).curveTo(270.50497, 116.957985, 270.55997,
                116.72799, 270.62598, 116.44099);
        ((GeneralPath) shape).lineTo(271.03198, 114.708984);
        ((GeneralPath) shape).lineTo(272.16998, 114.708984);
        ((GeneralPath) shape).lineTo(272.57498, 116.44099);
        ((GeneralPath) shape).curveTo(272.649, 116.764984, 272.70398,
                116.997986, 272.735, 117.13199);
        ((GeneralPath) shape).lineTo(272.809, 117.47999);
        ((GeneralPath) shape).lineTo(272.88098, 117.82999);
        ((GeneralPath) shape).lineTo(272.90198, 117.82999);
        ((GeneralPath) shape).lineTo(272.96597, 117.47999);
        ((GeneralPath) shape).lineTo(273.02896, 117.13199);
        ((GeneralPath) shape).curveTo(273.07095, 116.89399, 273.11795,
                116.66099, 273.16495, 116.43699);
        ((GeneralPath) shape).lineTo(273.52795, 114.70799);
        ((GeneralPath) shape).lineTo(274.42, 114.70799);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_213;
        g.setTransform(defaultTransform__0_213);
        g.setClip(clip__0_213);
        float alpha__0_214 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_214 = g.getClip();
        AffineTransform defaultTransform__0_214 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_214 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(275.719, 114.708);
        ((GeneralPath) shape).lineTo(275.719, 115.669);
        ((GeneralPath) shape).lineTo(274.824, 115.669);
        ((GeneralPath) shape).lineTo(274.824, 114.708);
        ((GeneralPath) shape).lineTo(275.719, 114.708);
        ((GeneralPath) shape).moveTo(275.719, 117.481);
        ((GeneralPath) shape).lineTo(275.719, 118.442);
        ((GeneralPath) shape).lineTo(274.824, 118.442);
        ((GeneralPath) shape).lineTo(274.824, 117.481);
        ((GeneralPath) shape).lineTo(275.719, 117.481);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_214;
        g.setTransform(defaultTransform__0_214);
        g.setClip(clip__0_214);
        float alpha__0_215 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_215 = g.getClip();
        AffineTransform defaultTransform__0_215 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_215 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(258.996, 128.358);
        ((GeneralPath) shape).lineTo(261.339, 128.358);
        ((GeneralPath) shape).lineTo(261.357, 129.418);
        ((GeneralPath) shape).curveTo(261.357, 130.188, 261.211, 130.686,
                260.91998, 130.907);
        ((GeneralPath) shape).curveTo(260.632, 131.132, 259.98398, 131.242,
                258.982, 131.242);
        ((GeneralPath) shape).curveTo(258.06198, 131.242, 257.453, 131.095,
                257.15298, 130.798);
        ((GeneralPath) shape).curveTo(256.85498, 130.501, 256.70398, 129.895,
                256.70398, 128.98201);
        ((GeneralPath) shape).curveTo(256.70398, 127.81601, 256.76498,
                127.08201, 256.88397, 126.77101);
        ((GeneralPath) shape).curveTo(257.03198, 126.39601, 257.25598,
                126.14501, 257.55997, 126.01401);
        ((GeneralPath) shape).curveTo(257.85995, 125.88501, 258.37296,
                125.81901, 259.09396, 125.81901);
        ((GeneralPath) shape).curveTo(260.03897, 125.81901, 260.65097,
                125.921005, 260.92596, 126.12401);
        ((GeneralPath) shape).curveTo(261.20096, 126.323006, 261.33997,
                126.77001, 261.33997, 127.46601);
        ((GeneralPath) shape).lineTo(260.31897, 127.46601);
        ((GeneralPath) shape).curveTo(260.29898, 127.11601, 260.22498,
                126.90201, 260.08997, 126.81601);
        ((GeneralPath) shape).curveTo(259.95798, 126.73201, 259.61996,
                126.68901, 259.08197, 126.68901);
        ((GeneralPath) shape).curveTo(258.49796, 126.68901, 258.12698,
                126.76201, 257.97098, 126.90801);
        ((GeneralPath) shape).curveTo(257.81497, 127.054016, 257.735,
                127.39601, 257.735, 127.93501);
        ((GeneralPath) shape).lineTo(257.731, 128.47002);
        ((GeneralPath) shape).lineTo(257.73898, 129.15402);
        ((GeneralPath) shape).curveTo(257.73898, 129.68102, 257.817, 130.01903,
                257.973, 130.16602);
        ((GeneralPath) shape).curveTo(258.12698, 130.31102, 258.48898,
                130.38301, 259.056, 130.38301);
        ((GeneralPath) shape).curveTo(259.606, 130.38301, 259.955, 130.32,
                260.107, 130.197);
        ((GeneralPath) shape).curveTo(260.253, 130.07701, 260.331, 129.78801,
                260.331, 129.33101);
        ((GeneralPath) shape).lineTo(260.335, 129.11101);
        ((GeneralPath) shape).lineTo(258.998, 129.11101);
        ((GeneralPath) shape).lineTo(258.996, 128.358);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_215;
        g.setTransform(defaultTransform__0_215);
        g.setClip(clip__0_215);
        float alpha__0_216 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_216 = g.getClip();
        AffineTransform defaultTransform__0_216 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_216 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(265.342, 127.459);
        ((GeneralPath) shape).lineTo(265.342, 131.191);
        ((GeneralPath) shape).lineTo(264.449, 131.191);
        ((GeneralPath) shape).lineTo(264.5, 130.54999);
        ((GeneralPath) shape).lineTo(264.484, 130.54599);
        ((GeneralPath) shape).curveTo(264.311, 131.00299, 263.911, 131.23299,
                263.28302, 131.23299);
        ((GeneralPath) shape).curveTo(262.43903, 131.23299, 262.01602,
                130.81198, 262.01602, 129.96399);
        ((GeneralPath) shape).lineTo(262.01602, 127.455986);
        ((GeneralPath) shape).lineTo(262.90903, 127.455986);
        ((GeneralPath) shape).lineTo(262.90903, 129.74799);
        ((GeneralPath) shape).curveTo(262.90903, 130.06299, 262.95203,
                130.27199, 263.04202, 130.37498);
        ((GeneralPath) shape).curveTo(263.12903, 130.47499, 263.31302,
                130.52298, 263.59103, 130.52298);
        ((GeneralPath) shape).curveTo(264.16205, 130.52298, 264.44904,
                130.17899, 264.44904, 129.49397);
        ((GeneralPath) shape).lineTo(264.44904, 127.45497);
        ((GeneralPath) shape).lineTo(265.34305, 127.45497);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_216;
        g.setTransform(defaultTransform__0_216);
        g.setClip(clip__0_216);
        float alpha__0_217 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_217 = g.getClip();
        AffineTransform defaultTransform__0_217 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_217 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(266.248, 127.459);
        ((GeneralPath) shape).lineTo(267.133, 127.459);
        ((GeneralPath) shape).lineTo(267.099, 128.087);
        ((GeneralPath) shape).lineTo(267.119, 128.091);
        ((GeneralPath) shape).curveTo(267.292, 127.64101, 267.68198, 127.415,
                268.288, 127.415);
        ((GeneralPath) shape).curveTo(269.169, 127.415, 269.609, 127.826004,
                269.609, 128.648);
        ((GeneralPath) shape).lineTo(269.609, 131.191);
        ((GeneralPath) shape).lineTo(268.71402, 131.191);
        ((GeneralPath) shape).lineTo(268.71402, 128.79999);
        ((GeneralPath) shape).lineTo(268.69403, 128.538);
        ((GeneralPath) shape).curveTo(268.65305, 128.261, 268.43503, 128.12099,
                268.03903, 128.12099);
        ((GeneralPath) shape).curveTo(267.43903, 128.12099, 267.14102,
                128.40399, 267.14102, 128.97499);
        ((GeneralPath) shape).lineTo(267.14102, 131.18999);
        ((GeneralPath) shape).lineTo(266.24603, 131.18999);
        ((GeneralPath) shape).lineTo(266.24603, 127.457985);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_217;
        g.setTransform(defaultTransform__0_217);
        g.setClip(clip__0_217);
        float alpha__0_218 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_218 = g.getClip();
        AffineTransform defaultTransform__0_218 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_218 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(270.479, 127.459);
        ((GeneralPath) shape).lineTo(271.364, 127.459);
        ((GeneralPath) shape).lineTo(271.329, 128.087);
        ((GeneralPath) shape).lineTo(271.35, 128.091);
        ((GeneralPath) shape).curveTo(271.523, 127.64101, 271.913, 127.415,
                272.519, 127.415);
        ((GeneralPath) shape).curveTo(273.40002, 127.415, 273.838, 127.826004,
                273.838, 128.648);
        ((GeneralPath) shape).lineTo(273.838, 131.191);
        ((GeneralPath) shape).lineTo(272.945, 131.191);
        ((GeneralPath) shape).lineTo(272.945, 128.79999);
        ((GeneralPath) shape).lineTo(272.92502, 128.538);
        ((GeneralPath) shape).curveTo(272.88403, 128.261, 272.66403, 128.12099,
                272.27002, 128.12099);
        ((GeneralPath) shape).curveTo(271.66803, 128.12099, 271.37003,
                128.40399, 271.37003, 128.97499);
        ((GeneralPath) shape).lineTo(271.37003, 131.18999);
        ((GeneralPath) shape).lineTo(270.47702, 131.18999);
        ((GeneralPath) shape).lineTo(270.479, 127.459);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_218;
        g.setTransform(defaultTransform__0_218);
        g.setClip(clip__0_218);
        float alpha__0_219 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_219 = g.getClip();
        AffineTransform defaultTransform__0_219 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_219 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(277.12, 128.94);
        ((GeneralPath) shape).lineTo(277.116, 128.793);
        ((GeneralPath) shape).curveTo(277.116, 128.497, 277.066, 128.305,
                276.962, 128.215);
        ((GeneralPath) shape).curveTo(276.86002, 128.12799, 276.638, 128.083,
                276.294, 128.083);
        ((GeneralPath) shape).curveTo(275.962, 128.083, 275.746, 128.13599,
                275.647, 128.241);
        ((GeneralPath) shape).curveTo(275.546, 128.346, 275.497, 128.583,
                275.497, 128.94);
        ((GeneralPath) shape).lineTo(277.12, 128.94);
        ((GeneralPath) shape).moveTo(277.112, 129.999);
        ((GeneralPath) shape).lineTo(278.008, 129.999);
        ((GeneralPath) shape).lineTo(278.008, 130.14299);
        ((GeneralPath) shape).curveTo(278.008, 130.872, 277.462, 131.23698,
                276.371, 131.23698);
        ((GeneralPath) shape).curveTo(275.63, 131.23698, 275.146, 131.11198,
                274.91602, 130.85898);
        ((GeneralPath) shape).curveTo(274.68802, 130.60698, 274.57303,
                130.07198, 274.57303, 129.25497);
        ((GeneralPath) shape).curveTo(274.57303, 128.52698, 274.69202,
                128.04097, 274.93103, 127.79098);
        ((GeneralPath) shape).curveTo(275.16702, 127.54098, 275.63702,
                127.41598, 276.32904, 127.41598);
        ((GeneralPath) shape).curveTo(276.99405, 127.41598, 277.44003,
                127.53698, 277.66605, 127.77898);
        ((GeneralPath) shape).curveTo(277.89404, 128.02098, 278.00504,
                128.50198, 278.00504, 129.21597);
        ((GeneralPath) shape).lineTo(278.00504, 129.48697);
        ((GeneralPath) shape).lineTo(275.48404, 129.48697);
        ((GeneralPath) shape).curveTo(275.48004, 129.56996, 275.47604,
                129.62598, 275.47604, 129.65196);
        ((GeneralPath) shape).curveTo(275.47604, 130.01897, 275.53204,
                130.26295, 275.64505, 130.38596);
        ((GeneralPath) shape).curveTo(275.75806, 130.50696, 275.98206,
                130.56996, 276.32104, 130.56996);
        ((GeneralPath) shape).curveTo(276.64905, 130.56996, 276.86105,
                130.53496, 276.96005, 130.46497);
        ((GeneralPath) shape).curveTo(277.062, 130.391, 277.112, 130.236,
                277.112, 129.999);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_219;
        g.setTransform(defaultTransform__0_219);
        g.setClip(clip__0_219);
        float alpha__0_220 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_220 = g.getClip();
        AffineTransform defaultTransform__0_220 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_220 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(278.774, 127.459);
        ((GeneralPath) shape).lineTo(279.667, 127.459);
        ((GeneralPath) shape).lineTo(279.612, 127.98);
        ((GeneralPath) shape).lineTo(279.631, 127.983);
        ((GeneralPath) shape).curveTo(279.846, 127.593, 280.181, 127.398,
                280.639, 127.398);
        ((GeneralPath) shape).curveTo(281.29202, 127.398, 281.61902,
                127.812004, 281.61902, 128.64);
        ((GeneralPath) shape).lineTo(281.61902, 128.902);
        ((GeneralPath) shape).lineTo(280.777, 128.902);
        ((GeneralPath) shape).curveTo(280.78702, 128.79999, 280.793, 128.734,
                280.793, 128.70499);
        ((GeneralPath) shape).curveTo(280.793, 128.30598, 280.641, 128.10498,
                280.329, 128.10498);
        ((GeneralPath) shape).curveTo(279.89102, 128.10498, 279.66602,
                128.39998, 279.66602, 128.99399);
        ((GeneralPath) shape).lineTo(279.66602, 131.191);
        ((GeneralPath) shape).lineTo(278.773, 131.191);
        ((GeneralPath) shape).lineTo(278.774, 127.459);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_220;
        g.setTransform(defaultTransform__0_220);
        g.setClip(clip__0_220);
        float alpha__0_221 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_221 = g.getClip();
        AffineTransform defaultTransform__0_221 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_221 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(285.18, 127.459);
        ((GeneralPath) shape).lineTo(284.201, 131.428);
        ((GeneralPath) shape).curveTo(284.065, 131.991, 283.89297, 132.36699,
                283.688, 132.56299);
        ((GeneralPath) shape).curveTo(283.485, 132.75598, 283.158, 132.85399,
                282.70798, 132.85399);
        ((GeneralPath) shape).curveTo(282.60696, 132.85399, 282.50098,
                132.84999, 282.39398, 132.83798);
        ((GeneralPath) shape).lineTo(282.39398, 132.17798);
        ((GeneralPath) shape).curveTo(282.472, 132.18098, 282.537, 132.18597,
                282.58698, 132.18597);
        ((GeneralPath) shape).curveTo(282.96497, 132.18597, 283.20697,
                131.85597, 283.31097, 131.19397);
        ((GeneralPath) shape).lineTo(282.85397, 131.19397);
        ((GeneralPath) shape).lineTo(281.632, 127.46197);
        ((GeneralPath) shape).lineTo(282.591, 127.46197);
        ((GeneralPath) shape).lineTo(283.061, 129.04396);
        ((GeneralPath) shape).lineTo(283.293, 129.83696);
        ((GeneralPath) shape).curveTo(283.307, 129.88795, 283.341, 130.01996,
                283.402, 130.23296);
        ((GeneralPath) shape).lineTo(283.515, 130.62895);
        ((GeneralPath) shape).lineTo(283.535, 130.62895);
        ((GeneralPath) shape).lineTo(283.617, 130.23296);
        ((GeneralPath) shape).curveTo(283.658, 130.02995, 283.685, 129.89696,
                283.699, 129.83696);
        ((GeneralPath) shape).lineTo(283.88202, 129.04396);
        ((GeneralPath) shape).lineTo(284.24002, 127.46196);
        ((GeneralPath) shape).lineTo(285.18, 127.459);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_221;
        g.setTransform(defaultTransform__0_221);
        g.setClip(clip__0_221);
        float alpha__0_222 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_222 = g.getClip();
        AffineTransform defaultTransform__0_222 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_222 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(291.558, 127.416);
        ((GeneralPath) shape).lineTo(290.57202, 127.416);
        ((GeneralPath) shape).curveTo(290.56802, 127.368004, 290.56403,
                127.331, 290.56403, 127.307);
        ((GeneralPath) shape).curveTo(290.54102, 127.009, 290.47403, 126.821,
                290.36502, 126.743996);
        ((GeneralPath) shape).curveTo(290.256, 126.66699, 289.99802, 126.628,
                289.59402, 126.628);
        ((GeneralPath) shape).curveTo(289.11502, 126.628, 288.80402, 126.671,
                288.65604, 126.761);
        ((GeneralPath) shape).curveTo(288.51004, 126.849, 288.43604,
                127.034004, 288.43604, 127.32);
        ((GeneralPath) shape).curveTo(288.43604, 127.656, 288.49503, 127.857,
                288.61603, 127.925);
        ((GeneralPath) shape).curveTo(288.73502, 127.989006, 289.13104,
                128.044, 289.80203, 128.08101);
        ((GeneralPath) shape).curveTo(290.59402, 128.12401, 291.10703,
                128.23502, 291.33902, 128.421);
        ((GeneralPath) shape).curveTo(291.571, 128.60301, 291.69003, 128.98201,
                291.69003, 129.557);
        ((GeneralPath) shape).curveTo(291.69003, 130.265, 291.55203, 130.72401,
                291.28003, 130.931);
        ((GeneralPath) shape).curveTo(291.00803, 131.13799, 290.40704, 131.243,
                289.47504, 131.243);
        ((GeneralPath) shape).curveTo(288.63904, 131.243, 288.08102, 131.14,
                287.80804, 130.937);
        ((GeneralPath) shape).curveTo(287.53506, 130.734, 287.39606, 130.323,
                287.39606, 129.705);
        ((GeneralPath) shape).lineTo(287.39307, 129.51);
        ((GeneralPath) shape).lineTo(288.37308, 129.51);
        ((GeneralPath) shape).lineTo(288.37607, 129.623);
        ((GeneralPath) shape).curveTo(288.37607, 129.994, 288.44107, 130.223,
                288.57108, 130.307);
        ((GeneralPath) shape).curveTo(288.69907, 130.389, 289.05408, 130.432,
                289.63507, 130.432);
        ((GeneralPath) shape).curveTo(290.08508, 130.432, 290.37308, 130.38501,
                290.49808, 130.287);
        ((GeneralPath) shape).curveTo(290.62308, 130.19, 290.68607, 129.96901,
                290.68607, 129.62);
        ((GeneralPath) shape).curveTo(290.68607, 129.36, 290.63907, 129.19,
                290.54205, 129.10399);
        ((GeneralPath) shape).curveTo(290.44806, 129.01999, 290.24405, 128.969,
                289.93005, 128.94899);
        ((GeneralPath) shape).lineTo(289.37006, 128.91599);
        ((GeneralPath) shape).curveTo(288.52805, 128.86499, 287.99106,
                128.74998, 287.75806, 128.56198);
        ((GeneralPath) shape).curveTo(287.52505, 128.37798, 287.40506,
                127.97998, 287.40506, 127.37298);
        ((GeneralPath) shape).curveTo(287.40506, 126.751976, 287.54605,
                126.335976, 287.82806, 126.128975);
        ((GeneralPath) shape).curveTo(288.10907, 125.921974, 288.67206,
                125.81597, 289.51807, 125.81597);
        ((GeneralPath) shape).curveTo(290.31708, 125.81597, 290.85706,
                125.90997, 291.13608, 126.100975);
        ((GeneralPath) shape).curveTo(291.41107, 126.28998, 291.55307,
                126.66398, 291.55307, 127.213974);
        ((GeneralPath) shape).lineTo(291.55307, 127.41698);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_222;
        g.setTransform(defaultTransform__0_222);
        g.setClip(clip__0_222);
        float alpha__0_223 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_223 = g.getClip();
        AffineTransform defaultTransform__0_223 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_223 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(293.256, 125.861);
        ((GeneralPath) shape).lineTo(293.256, 128.913);
        ((GeneralPath) shape).lineTo(293.487, 128.913);
        ((GeneralPath) shape).lineTo(294.492, 127.459);
        ((GeneralPath) shape).lineTo(295.529, 127.459);
        ((GeneralPath) shape).lineTo(294.231, 129.217);
        ((GeneralPath) shape).lineTo(295.794, 131.194);
        ((GeneralPath) shape).lineTo(294.687, 131.194);
        ((GeneralPath) shape).lineTo(293.475, 129.538);
        ((GeneralPath) shape).lineTo(293.256, 129.538);
        ((GeneralPath) shape).lineTo(293.256, 131.194);
        ((GeneralPath) shape).lineTo(292.364, 131.194);
        ((GeneralPath) shape).lineTo(292.364, 125.861);
        ((GeneralPath) shape).lineTo(293.256, 125.861);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_223;
        g.setTransform(defaultTransform__0_223);
        g.setClip(clip__0_223);
        float alpha__0_224 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_224 = g.getClip();
        AffineTransform defaultTransform__0_224 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_224 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(297.088, 127.459);
        ((GeneralPath) shape).lineTo(297.088, 131.191);
        ((GeneralPath) shape).lineTo(296.195, 131.191);
        ((GeneralPath) shape).lineTo(296.195, 127.45899);
        ((GeneralPath) shape).lineTo(297.088, 127.45899);
        ((GeneralPath) shape).moveTo(297.088, 125.861);
        ((GeneralPath) shape).lineTo(297.088, 126.608);
        ((GeneralPath) shape).lineTo(296.195, 126.608);
        ((GeneralPath) shape).lineTo(296.195, 125.861);
        ((GeneralPath) shape).lineTo(297.088, 125.861);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_224;
        g.setTransform(defaultTransform__0_224);
        g.setClip(clip__0_224);
        float alpha__0_225 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_225 = g.getClip();
        AffineTransform defaultTransform__0_225 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_225 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new Rectangle2D.Double(297.968994140625, 125.86100006103516,
                0.8930000066757202, 5.333000183105469);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_225;
        g.setTransform(defaultTransform__0_225);
        g.setClip(clip__0_225);
        float alpha__0_226 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_226 = g.getClip();
        AffineTransform defaultTransform__0_226 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_226 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new Rectangle2D.Double(299.7409973144531, 125.86100006103516,
                0.8949999809265137, 5.333000183105469);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_226;
        g.setTransform(defaultTransform__0_226);
        g.setClip(clip__0_226);
        float alpha__0_227 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_227 = g.getClip();
        AffineTransform defaultTransform__0_227 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_227 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(302.367, 127.459);
        ((GeneralPath) shape).lineTo(302.367, 128.42);
        ((GeneralPath) shape).lineTo(301.474, 128.42);
        ((GeneralPath) shape).lineTo(301.474, 127.459);
        ((GeneralPath) shape).lineTo(302.367, 127.459);
        ((GeneralPath) shape).moveTo(302.367, 130.233);
        ((GeneralPath) shape).lineTo(302.367, 131.194);
        ((GeneralPath) shape).lineTo(301.474, 131.194);
        ((GeneralPath) shape).lineTo(301.474, 130.233);
        ((GeneralPath) shape).lineTo(302.367, 130.233);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_227;
        g.setTransform(defaultTransform__0_227);
        g.setClip(clip__0_227);
        float alpha__0_228 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_228 = g.getClip();
        AffineTransform defaultTransform__0_228 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_228 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(330.199, 130.342);
        ((GeneralPath) shape).lineTo(331.637, 130.342);
        ((GeneralPath) shape).curveTo(332.11798, 130.342, 332.432, 130.231,
                332.57498, 130.008);
        ((GeneralPath) shape).curveTo(332.71796, 129.78499, 332.787, 129.295,
                332.787, 128.53099);
        ((GeneralPath) shape).curveTo(332.787, 127.74799, 332.72498, 127.24799,
                332.599, 127.03099);
        ((GeneralPath) shape).curveTo(332.472, 126.81899, 332.174, 126.71299,
                331.703, 126.71299);
        ((GeneralPath) shape).lineTo(330.2, 126.71299);
        ((GeneralPath) shape).lineTo(330.199, 130.342);
        ((GeneralPath) shape).moveTo(329.189, 131.194);
        ((GeneralPath) shape).lineTo(329.189, 125.861);
        ((GeneralPath) shape).lineTo(331.804, 125.861);
        ((GeneralPath) shape).curveTo(332.547, 125.861, 333.068, 126.024,
                333.366, 126.35);
        ((GeneralPath) shape).curveTo(333.663, 126.673996, 333.812, 127.246,
                333.812, 128.061);
        ((GeneralPath) shape).curveTo(333.812, 129.391, 333.694, 130.24901,
                333.454, 130.625);
        ((GeneralPath) shape).curveTo(333.21802, 131.004, 332.67902, 131.191,
                331.83902, 131.191);
        ((GeneralPath) shape).lineTo(329.189, 131.194);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_228;
        g.setTransform(defaultTransform__0_228);
        g.setClip(clip__0_228);
        float alpha__0_229 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_229 = g.getClip();
        AffineTransform defaultTransform__0_229 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_229 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(334.544, 127.459);
        ((GeneralPath) shape).lineTo(335.437, 127.459);
        ((GeneralPath) shape).lineTo(335.38303, 127.98);
        ((GeneralPath) shape).lineTo(335.40204, 127.983);
        ((GeneralPath) shape).curveTo(335.61505, 127.593, 335.95004, 127.398,
                336.40903, 127.398);
        ((GeneralPath) shape).curveTo(337.06302, 127.398, 337.39102,
                127.812004, 337.39102, 128.64);
        ((GeneralPath) shape).lineTo(337.39102, 128.902);
        ((GeneralPath) shape).lineTo(336.54803, 128.902);
        ((GeneralPath) shape).curveTo(336.55804, 128.79999, 336.56403, 128.734,
                336.56403, 128.70499);
        ((GeneralPath) shape).curveTo(336.56403, 128.30598, 336.411, 128.10498,
                336.10004, 128.10498);
        ((GeneralPath) shape).curveTo(335.66003, 128.10498, 335.43704,
                128.39998, 335.43704, 128.99399);
        ((GeneralPath) shape).lineTo(335.43704, 131.191);
        ((GeneralPath) shape).lineTo(334.54404, 131.191);
        ((GeneralPath) shape).lineTo(334.54404, 127.459);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_229;
        g.setTransform(defaultTransform__0_229);
        g.setClip(clip__0_229);
        float alpha__0_230 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_230 = g.getClip();
        AffineTransform defaultTransform__0_230 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_230 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(338.773, 127.459);
        ((GeneralPath) shape).lineTo(338.773, 131.191);
        ((GeneralPath) shape).lineTo(337.88, 131.191);
        ((GeneralPath) shape).lineTo(337.88, 127.45899);
        ((GeneralPath) shape).lineTo(338.773, 127.45899);
        ((GeneralPath) shape).moveTo(338.773, 125.861);
        ((GeneralPath) shape).lineTo(338.773, 126.608);
        ((GeneralPath) shape).lineTo(337.88, 126.608);
        ((GeneralPath) shape).lineTo(337.88, 125.861);
        ((GeneralPath) shape).lineTo(338.773, 125.861);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_230;
        g.setTransform(defaultTransform__0_230);
        g.setClip(clip__0_230);
        float alpha__0_231 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_231 = g.getClip();
        AffineTransform defaultTransform__0_231 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_231 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(342.76, 127.459);
        ((GeneralPath) shape).lineTo(341.69202, 131.191);
        ((GeneralPath) shape).lineTo(340.29602, 131.191);
        ((GeneralPath) shape).lineTo(339.17303, 127.45899);
        ((GeneralPath) shape).lineTo(340.11905, 127.45899);
        ((GeneralPath) shape).lineTo(340.60904, 129.18098);
        ((GeneralPath) shape).curveTo(340.67505, 129.41998, 340.73804,
                129.65198, 340.79703, 129.87599);
        ((GeneralPath) shape).lineTo(340.88702, 130.224);
        ((GeneralPath) shape).lineTo(340.976, 130.571);
        ((GeneralPath) shape).lineTo(340.997, 130.571);
        ((GeneralPath) shape).lineTo(341.079, 130.224);
        ((GeneralPath) shape).lineTo(341.16, 129.88);
        ((GeneralPath) shape).curveTo(341.223, 129.62001, 341.281, 129.388,
                341.338, 129.185);
        ((GeneralPath) shape).lineTo(341.798, 127.458);
        ((GeneralPath) shape).lineTo(342.76, 127.459);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_231;
        g.setTransform(defaultTransform__0_231);
        g.setClip(clip__0_231);
        float alpha__0_232 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_232 = g.getClip();
        AffineTransform defaultTransform__0_232 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        paint3(g, clip_, defaultTransform_, alpha__0, clip__0,
                defaultTransform__0, alpha__0_232, clip__0_232,
                defaultTransform__0_232);
    }

    private static void paint3(Graphics2D g, Shape clip_,
            AffineTransform defaultTransform_, float alpha__0, Shape clip__0,
            AffineTransform defaultTransform__0, float alpha__0_232,
            Shape clip__0_232, AffineTransform defaultTransform__0_232) {
        Shape shape;
        Paint paint;
        Stroke stroke;
        float origAlpha;
        // _0_232 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(344.052, 127.459);
        ((GeneralPath) shape).lineTo(344.052, 131.191);
        ((GeneralPath) shape).lineTo(343.157, 131.191);
        ((GeneralPath) shape).lineTo(343.157, 127.45899);
        ((GeneralPath) shape).lineTo(344.052, 127.45899);
        ((GeneralPath) shape).moveTo(344.052, 125.861);
        ((GeneralPath) shape).lineTo(344.052, 126.608);
        ((GeneralPath) shape).lineTo(343.157, 126.608);
        ((GeneralPath) shape).lineTo(343.157, 125.861);
        ((GeneralPath) shape).lineTo(344.052, 125.861);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_232;
        g.setTransform(defaultTransform__0_232);
        g.setClip(clip__0_232);
        float alpha__0_233 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_233 = g.getClip();
        AffineTransform defaultTransform__0_233 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_233 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(344.931, 127.459);
        ((GeneralPath) shape).lineTo(345.816, 127.459);
        ((GeneralPath) shape).lineTo(345.781, 128.087);
        ((GeneralPath) shape).lineTo(345.802, 128.091);
        ((GeneralPath) shape).curveTo(345.975, 127.64101, 346.365, 127.415,
                346.971, 127.415);
        ((GeneralPath) shape).curveTo(347.853, 127.415, 348.293, 127.826004,
                348.293, 128.648);
        ((GeneralPath) shape).lineTo(348.293, 131.191);
        ((GeneralPath) shape).lineTo(347.398, 131.191);
        ((GeneralPath) shape).lineTo(347.398, 128.79999);
        ((GeneralPath) shape).lineTo(347.377, 128.538);
        ((GeneralPath) shape).curveTo(347.337, 128.261, 347.11603, 128.12099,
                346.72302, 128.12099);
        ((GeneralPath) shape).curveTo(346.12003, 128.12099, 345.82303,
                128.40399, 345.82303, 128.97499);
        ((GeneralPath) shape).lineTo(345.82303, 131.18999);
        ((GeneralPath) shape).lineTo(344.93002, 131.18999);
        ((GeneralPath) shape).lineTo(344.93002, 127.459);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_233;
        g.setTransform(defaultTransform__0_233);
        g.setClip(clip__0_233);
        float alpha__0_234 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_234 = g.getClip();
        AffineTransform defaultTransform__0_234 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_234 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(350.736, 128.124);
        ((GeneralPath) shape).curveTo(350.393, 128.124, 350.173, 128.18999,
                350.076, 128.333);
        ((GeneralPath) shape).curveTo(349.978, 128.472, 349.93, 128.788,
                349.93, 129.284);
        ((GeneralPath) shape).curveTo(349.93, 129.808, 349.979, 130.144,
                350.076, 130.29199);
        ((GeneralPath) shape).curveTo(350.175, 130.437, 350.397, 130.51099,
                350.748, 130.51099);
        ((GeneralPath) shape).curveTo(351.102, 130.51099, 351.32498, 130.43698,
                351.43298, 130.28598);
        ((GeneralPath) shape).curveTo(351.537, 130.13799, 351.589, 129.80598,
                351.589, 129.29099);
        ((GeneralPath) shape).curveTo(351.589, 128.79898, 351.53598, 128.48099,
                351.43298, 128.33798);
        ((GeneralPath) shape).curveTo(351.327, 128.196, 351.095, 128.124,
                350.736, 128.124);
        ((GeneralPath) shape).moveTo(352.495, 127.459);
        ((GeneralPath) shape).lineTo(352.495, 131.26399);
        ((GeneralPath) shape).curveTo(352.495, 131.87299, 352.372, 132.29099,
                352.129, 132.517);
        ((GeneralPath) shape).curveTo(351.883, 132.746, 351.431, 132.857,
                350.77, 132.857);
        ((GeneralPath) shape).curveTo(350.13098, 132.857, 349.705, 132.771,
                349.48798, 132.599);
        ((GeneralPath) shape).curveTo(349.274, 132.427, 349.16498, 132.087,
                349.16498, 131.579);
        ((GeneralPath) shape).lineTo(350.02997, 131.579);
        ((GeneralPath) shape).curveTo(350.02997, 131.819, 350.07498, 131.97499,
                350.16696, 132.04599);
        ((GeneralPath) shape).curveTo(350.25897, 132.11198, 350.45895, 132.15,
                350.77396, 132.15);
        ((GeneralPath) shape).curveTo(351.32794, 132.15, 351.60495, 131.92099,
                351.60495, 131.46199);
        ((GeneralPath) shape).lineTo(351.60495, 130.62299);
        ((GeneralPath) shape).lineTo(351.58395, 130.61899);
        ((GeneralPath) shape).curveTo(351.42795, 131.01498, 351.06296, 131.217,
                350.49194, 131.217);
        ((GeneralPath) shape).curveTo(349.92896, 131.217, 349.54294, 131.08,
                349.33594, 130.806);
        ((GeneralPath) shape).curveTo(349.13193, 130.535, 349.02594, 130.023,
                349.02594, 129.275);
        ((GeneralPath) shape).curveTo(349.02594, 128.571, 349.12994, 128.08699,
                349.33594, 127.81799);
        ((GeneralPath) shape).curveTo(349.54294, 127.549995, 349.91693,
                127.41699, 350.46094, 127.41699);
        ((GeneralPath) shape).curveTo(351.05594, 127.41699, 351.44095,
                127.635994, 351.62692, 128.07799);
        ((GeneralPath) shape).lineTo(351.64792, 128.07799);
        ((GeneralPath) shape).lineTo(351.60593, 127.46099);
        ((GeneralPath) shape).lineTo(352.495, 127.459);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_234;
        g.setTransform(defaultTransform__0_234);
        g.setClip(clip__0_234);
        float alpha__0_235 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_235 = g.getClip();
        AffineTransform defaultTransform__0_235 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_235 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(359.344, 127.416);
        ((GeneralPath) shape).lineTo(358.358, 127.416);
        ((GeneralPath) shape).curveTo(358.354, 127.368004, 358.35, 127.331,
                358.35, 127.307);
        ((GeneralPath) shape).curveTo(358.327, 127.009, 358.26, 126.821,
                358.151, 126.743996);
        ((GeneralPath) shape).curveTo(358.044, 126.66699, 357.785, 126.628,
                357.38, 126.628);
        ((GeneralPath) shape).curveTo(356.901, 126.628, 356.59, 126.671,
                356.44202, 126.761);
        ((GeneralPath) shape).curveTo(356.29602, 126.849, 356.221, 127.034004,
                356.221, 127.32);
        ((GeneralPath) shape).curveTo(356.221, 127.656, 356.28, 127.857,
                356.401, 127.925);
        ((GeneralPath) shape).curveTo(356.52, 127.989006, 356.91602, 128.044,
                357.588, 128.08101);
        ((GeneralPath) shape).curveTo(358.38, 128.12401, 358.893, 128.23502,
                359.12402, 128.421);
        ((GeneralPath) shape).curveTo(359.35602, 128.60301, 359.476, 128.98201,
                359.476, 129.557);
        ((GeneralPath) shape).curveTo(359.476, 130.265, 359.33902, 130.72401,
                359.066, 130.931);
        ((GeneralPath) shape).curveTo(358.795, 131.138, 358.19302, 131.243,
                357.26102, 131.243);
        ((GeneralPath) shape).curveTo(356.42502, 131.243, 355.867, 131.14,
                355.59402, 130.937);
        ((GeneralPath) shape).curveTo(355.32202, 130.734, 355.183, 130.323,
                355.183, 129.705);
        ((GeneralPath) shape).lineTo(355.17902, 129.51);
        ((GeneralPath) shape).lineTo(356.15903, 129.51);
        ((GeneralPath) shape).lineTo(356.16302, 129.623);
        ((GeneralPath) shape).curveTo(356.16302, 129.994, 356.22702, 130.223,
                356.35803, 130.307);
        ((GeneralPath) shape).curveTo(356.48703, 130.389, 356.84103, 130.432,
                357.42203, 130.432);
        ((GeneralPath) shape).curveTo(357.87204, 130.432, 358.16003, 130.38501,
                358.28503, 130.287);
        ((GeneralPath) shape).curveTo(358.41003, 130.19, 358.47302, 129.96901,
                358.47302, 129.62);
        ((GeneralPath) shape).curveTo(358.47302, 129.36, 358.42603, 129.19,
                358.32803, 129.10399);
        ((GeneralPath) shape).curveTo(358.23404, 129.01999, 358.02902, 128.969,
                357.71603, 128.94899);
        ((GeneralPath) shape).lineTo(357.15604, 128.91599);
        ((GeneralPath) shape).curveTo(356.31403, 128.86499, 355.77603,
                128.74998, 355.54504, 128.56198);
        ((GeneralPath) shape).curveTo(355.31104, 128.37798, 355.19104,
                127.97998, 355.19104, 127.37298);
        ((GeneralPath) shape).curveTo(355.19104, 126.751976, 355.33203,
                126.335976, 355.61505, 126.128975);
        ((GeneralPath) shape).curveTo(355.89505, 125.921974, 356.46106,
                125.81597, 357.30405, 125.81597);
        ((GeneralPath) shape).curveTo(358.10306, 125.81597, 358.64304,
                125.90997, 358.92206, 126.100975);
        ((GeneralPath) shape).curveTo(359.19604, 126.28998, 359.33905,
                126.66398, 359.33905, 127.213974);
        ((GeneralPath) shape).lineTo(359.344, 127.416);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_235;
        g.setTransform(defaultTransform__0_235);
        g.setClip(clip__0_235);
        float alpha__0_236 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_236 = g.getClip();
        AffineTransform defaultTransform__0_236 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_236 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(361.041, 125.861);
        ((GeneralPath) shape).lineTo(361.041, 128.913);
        ((GeneralPath) shape).lineTo(361.271, 128.913);
        ((GeneralPath) shape).lineTo(362.276, 127.459);
        ((GeneralPath) shape).lineTo(363.313, 127.459);
        ((GeneralPath) shape).lineTo(362.016, 129.217);
        ((GeneralPath) shape).lineTo(363.578, 131.194);
        ((GeneralPath) shape).lineTo(362.471, 131.194);
        ((GeneralPath) shape).lineTo(361.26, 129.538);
        ((GeneralPath) shape).lineTo(361.041, 129.538);
        ((GeneralPath) shape).lineTo(361.041, 131.194);
        ((GeneralPath) shape).lineTo(360.148, 131.194);
        ((GeneralPath) shape).lineTo(360.148, 125.861);
        ((GeneralPath) shape).lineTo(361.041, 125.861);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_236;
        g.setTransform(defaultTransform__0_236);
        g.setClip(clip__0_236);
        float alpha__0_237 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_237 = g.getClip();
        AffineTransform defaultTransform__0_237 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_237 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(364.873, 127.459);
        ((GeneralPath) shape).lineTo(364.873, 131.191);
        ((GeneralPath) shape).lineTo(363.97998, 131.191);
        ((GeneralPath) shape).lineTo(363.97998, 127.45899);
        ((GeneralPath) shape).lineTo(364.873, 127.45899);
        ((GeneralPath) shape).moveTo(364.873, 125.861);
        ((GeneralPath) shape).lineTo(364.873, 126.608);
        ((GeneralPath) shape).lineTo(363.97998, 126.608);
        ((GeneralPath) shape).lineTo(363.97998, 125.861);
        ((GeneralPath) shape).lineTo(364.873, 125.861);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_237;
        g.setTransform(defaultTransform__0_237);
        g.setClip(clip__0_237);
        float alpha__0_238 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_238 = g.getClip();
        AffineTransform defaultTransform__0_238 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_238 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new Rectangle2D.Double(365.7550048828125, 125.86100006103516,
                0.8930000066757202, 5.333000183105469);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_238;
        g.setTransform(defaultTransform__0_238);
        g.setClip(clip__0_238);
        float alpha__0_239 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_239 = g.getClip();
        AffineTransform defaultTransform__0_239 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_239 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new Rectangle2D.Double(367.5249938964844, 125.86100006103516,
                0.8949999809265137, 5.333000183105469);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_239;
        g.setTransform(defaultTransform__0_239);
        g.setClip(clip__0_239);
        float alpha__0_240 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_240 = g.getClip();
        AffineTransform defaultTransform__0_240 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_240 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(370.151, 127.459);
        ((GeneralPath) shape).lineTo(370.151, 128.42);
        ((GeneralPath) shape).lineTo(369.258, 128.42);
        ((GeneralPath) shape).lineTo(369.258, 127.459);
        ((GeneralPath) shape).lineTo(370.151, 127.459);
        ((GeneralPath) shape).moveTo(370.151, 130.233);
        ((GeneralPath) shape).lineTo(370.151, 131.194);
        ((GeneralPath) shape).lineTo(369.258, 131.194);
        ((GeneralPath) shape).lineTo(369.258, 130.233);
        ((GeneralPath) shape).lineTo(370.151, 130.233);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_240;
        g.setTransform(defaultTransform__0_240);
        g.setClip(clip__0_240);
        float alpha__0_241 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_241 = g.getClip();
        AffineTransform defaultTransform__0_241 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_241 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(258.798, 200.371);
        ((GeneralPath) shape).lineTo(258.798, 205.144);
        ((GeneralPath) shape).lineTo(258.198, 205.144);
        ((GeneralPath) shape).lineTo(258.198, 200.371);
        ((GeneralPath) shape).lineTo(256.463, 200.371);
        ((GeneralPath) shape).lineTo(256.463, 199.812);
        ((GeneralPath) shape).lineTo(260.518, 199.812);
        ((GeneralPath) shape).lineTo(260.518, 200.371);
        ((GeneralPath) shape).lineTo(258.798, 200.371);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_241;
        g.setTransform(defaultTransform__0_241);
        g.setClip(clip__0_241);
        float alpha__0_242 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_242 = g.getClip();
        AffineTransform defaultTransform__0_242 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_242 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(264.068, 201.41);
        ((GeneralPath) shape).lineTo(264.068, 205.142);
        ((GeneralPath) shape).lineTo(263.52298, 205.142);
        ((GeneralPath) shape).lineTo(263.56198, 204.65399);
        ((GeneralPath) shape).lineTo(263.54797, 204.642);
        ((GeneralPath) shape).curveTo(263.36, 205.011, 262.95398, 205.19699,
                262.33197, 205.19699);
        ((GeneralPath) shape).curveTo(261.46198, 205.19699, 261.02597,
                204.76399, 261.02597, 203.892);
        ((GeneralPath) shape).lineTo(261.02597, 201.41);
        ((GeneralPath) shape).lineTo(261.57196, 201.41);
        ((GeneralPath) shape).lineTo(261.57196, 203.892);
        ((GeneralPath) shape).curveTo(261.57196, 204.23, 261.62695, 204.456,
                261.73895, 204.57199);
        ((GeneralPath) shape).curveTo(261.84995, 204.685, 262.07095, 204.74599,
                262.39795, 204.74599);
        ((GeneralPath) shape).curveTo(262.82693, 204.74599, 263.12094,
                204.66199, 263.28094, 204.48799);
        ((GeneralPath) shape).curveTo(263.44095, 204.31898, 263.52094, 204.008,
                263.52094, 203.54999);
        ((GeneralPath) shape).lineTo(263.52094, 201.40898);
        ((GeneralPath) shape).lineTo(264.068, 201.40898);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_242;
        g.setTransform(defaultTransform__0_242);
        g.setClip(clip__0_242);
        float alpha__0_243 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_243 = g.getClip();
        AffineTransform defaultTransform__0_243 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_243 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(265.129, 201.41);
        ((GeneralPath) shape).lineTo(265.675, 201.41);
        ((GeneralPath) shape).lineTo(265.62, 201.84);
        ((GeneralPath) shape).lineTo(265.63098, 201.85199);
        ((GeneralPath) shape).curveTo(265.847, 201.49998, 266.202, 201.32399,
                266.69897, 201.32399);
        ((GeneralPath) shape).curveTo(267.38696, 201.32399, 267.72797, 201.678,
                267.72797, 202.387);
        ((GeneralPath) shape).lineTo(267.72498, 202.64499);
        ((GeneralPath) shape).lineTo(267.18597, 202.64499);
        ((GeneralPath) shape).lineTo(267.19797, 202.551);
        ((GeneralPath) shape).curveTo(267.20596, 202.45299, 267.20996, 202.387,
                267.20996, 202.35199);
        ((GeneralPath) shape).curveTo(267.20996, 201.969, 267.00296, 201.777,
                266.58597, 201.777);
        ((GeneralPath) shape).curveTo(265.97998, 201.777, 265.67398, 202.152,
                265.67398, 202.90599);
        ((GeneralPath) shape).lineTo(265.67398, 205.144);
        ((GeneralPath) shape).lineTo(265.128, 205.144);
        ((GeneralPath) shape).lineTo(265.129, 201.41);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_243;
        g.setTransform(defaultTransform__0_243);
        g.setClip(clip__0_243);
        float alpha__0_244 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_244 = g.getClip();
        AffineTransform defaultTransform__0_244 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_244 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(268.307, 201.41);
        ((GeneralPath) shape).lineTo(268.85202, 201.41);
        ((GeneralPath) shape).lineTo(268.79602, 201.84);
        ((GeneralPath) shape).lineTo(268.80902, 201.85199);
        ((GeneralPath) shape).curveTo(269.023, 201.49998, 269.37802, 201.32399,
                269.87503, 201.32399);
        ((GeneralPath) shape).curveTo(270.56302, 201.32399, 270.90402, 201.678,
                270.90402, 202.387);
        ((GeneralPath) shape).lineTo(270.90002, 202.64499);
        ((GeneralPath) shape).lineTo(270.36203, 202.64499);
        ((GeneralPath) shape).lineTo(270.37402, 202.551);
        ((GeneralPath) shape).curveTo(270.38202, 202.45299, 270.38803, 202.387,
                270.38803, 202.35199);
        ((GeneralPath) shape).curveTo(270.38803, 201.969, 270.18103, 201.777,
                269.76404, 201.777);
        ((GeneralPath) shape).curveTo(269.15604, 201.777, 268.85104, 202.152,
                268.85104, 202.90599);
        ((GeneralPath) shape).lineTo(268.85104, 205.144);
        ((GeneralPath) shape).lineTo(268.30603, 205.144);
        ((GeneralPath) shape).lineTo(268.307, 201.41);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_244;
        g.setTransform(defaultTransform__0_244);
        g.setClip(clip__0_244);
        float alpha__0_245 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_245 = g.getClip();
        AffineTransform defaultTransform__0_245 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_245 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(274.021, 202.953);
        ((GeneralPath) shape).lineTo(274.017, 202.77501);
        ((GeneralPath) shape).curveTo(274.017, 202.37502, 273.951, 202.10901,
                273.818, 201.988);
        ((GeneralPath) shape).curveTo(273.685, 201.869, 273.401, 201.806,
                272.96, 201.806);
        ((GeneralPath) shape).curveTo(272.522, 201.806, 272.233, 201.876,
                272.103, 202.019);
        ((GeneralPath) shape).curveTo(271.973, 202.16199, 271.906, 202.47,
                271.906, 202.953);
        ((GeneralPath) shape).lineTo(274.021, 202.953);
        ((GeneralPath) shape).moveTo(274.021, 204.016);
        ((GeneralPath) shape).lineTo(274.579, 204.016);
        ((GeneralPath) shape).lineTo(274.583, 204.15201);
        ((GeneralPath) shape).curveTo(274.583, 204.54102, 274.466, 204.81001,
                274.23, 204.966);
        ((GeneralPath) shape).curveTo(273.996, 205.12001, 273.583, 205.196,
                272.992, 205.196);
        ((GeneralPath) shape).curveTo(272.306, 205.196, 271.855, 205.071,
                271.641, 204.81999);
        ((GeneralPath) shape).curveTo(271.42398, 204.56999, 271.319, 204.04199,
                271.319, 203.236);
        ((GeneralPath) shape).curveTo(271.319, 202.49399, 271.424, 201.99399,
                271.642, 201.736);
        ((GeneralPath) shape).curveTo(271.857, 201.482, 272.28, 201.353,
                272.909, 201.353);
        ((GeneralPath) shape).curveTo(273.597, 201.353, 274.044, 201.46199,
                274.259, 201.685);
        ((GeneralPath) shape).curveTo(274.473, 201.90599, 274.578, 202.373,
                274.578, 203.084);
        ((GeneralPath) shape).lineTo(274.578, 203.376);
        ((GeneralPath) shape).lineTo(271.895, 203.376);
        ((GeneralPath) shape).curveTo(271.895, 203.964, 271.95798, 204.339,
                272.08298, 204.501);
        ((GeneralPath) shape).curveTo(272.20798, 204.66101, 272.50198,
                204.74301, 272.96597, 204.74301);
        ((GeneralPath) shape).curveTo(273.40396, 204.74301, 273.692, 204.70601,
                273.82196, 204.628);
        ((GeneralPath) shape).curveTo(273.95297, 204.552, 274.01895, 204.386,
                274.01895, 204.13);
        ((GeneralPath) shape).lineTo(274.021, 204.016);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_245;
        g.setTransform(defaultTransform__0_245);
        g.setClip(clip__0_245);
        float alpha__0_246 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_246 = g.getClip();
        AffineTransform defaultTransform__0_246 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_246 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(277.413, 201.41);
        ((GeneralPath) shape).lineTo(277.413, 201.86101);
        ((GeneralPath) shape).lineTo(275.978, 201.86101);
        ((GeneralPath) shape).lineTo(275.978, 204.14601);
        ((GeneralPath) shape).curveTo(275.978, 204.54501, 276.15298, 204.74602,
                276.506, 204.74602);
        ((GeneralPath) shape).curveTo(276.859, 204.74602, 277.032, 204.56602,
                277.032, 204.21101);
        ((GeneralPath) shape).lineTo(277.036, 204.02501);
        ((GeneralPath) shape).lineTo(277.044, 203.81801);
        ((GeneralPath) shape).lineTo(277.553, 203.81801);
        ((GeneralPath) shape).lineTo(277.557, 204.095);
        ((GeneralPath) shape).curveTo(277.557, 204.829, 277.208, 205.197,
                276.512, 205.197);
        ((GeneralPath) shape).curveTo(275.791, 205.197, 275.432, 204.89201,
                275.432, 204.28);
        ((GeneralPath) shape).lineTo(275.432, 201.862);
        ((GeneralPath) shape).lineTo(274.915, 201.862);
        ((GeneralPath) shape).lineTo(274.915, 201.411);
        ((GeneralPath) shape).lineTo(275.432, 201.411);
        ((GeneralPath) shape).lineTo(275.432, 200.513);
        ((GeneralPath) shape).lineTo(275.978, 200.513);
        ((GeneralPath) shape).lineTo(275.978, 201.411);
        ((GeneralPath) shape).lineTo(277.413, 201.41);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_246;
        g.setTransform(defaultTransform__0_246);
        g.setClip(clip__0_246);
        float alpha__0_247 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_247 = g.getClip();
        AffineTransform defaultTransform__0_247 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_247 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(280.663, 199.812);
        ((GeneralPath) shape).lineTo(280.663, 204.586);
        ((GeneralPath) shape).lineTo(283.4, 204.586);
        ((GeneralPath) shape).lineTo(283.4, 205.144);
        ((GeneralPath) shape).lineTo(280.063, 205.144);
        ((GeneralPath) shape).lineTo(280.063, 199.812);
        ((GeneralPath) shape).lineTo(280.663, 199.812);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_247;
        g.setTransform(defaultTransform__0_247);
        g.setClip(clip__0_247);
        float alpha__0_248 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_248 = g.getClip();
        AffineTransform defaultTransform__0_248 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_248 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(285.447, 201.808);
        ((GeneralPath) shape).curveTo(284.964, 201.808, 284.666, 201.886,
                284.54898, 202.04399);
        ((GeneralPath) shape).curveTo(284.43597, 202.2, 284.37497, 202.60999,
                284.37497, 203.27399);
        ((GeneralPath) shape).curveTo(284.37497, 203.93799, 284.43396,
                204.34898, 284.54898, 204.50699);
        ((GeneralPath) shape).curveTo(284.66397, 204.663, 284.964, 204.74298,
                285.447, 204.74298);
        ((GeneralPath) shape).curveTo(285.93198, 204.74298, 286.233, 204.66498,
                286.35, 204.50699);
        ((GeneralPath) shape).curveTo(286.463, 204.35098, 286.523, 203.93799,
                286.523, 203.27399);
        ((GeneralPath) shape).curveTo(286.523, 202.60999, 286.465, 202.20198,
                286.35, 202.04399);
        ((GeneralPath) shape).curveTo(286.235, 201.888, 285.935, 201.808,
                285.447, 201.808);
        ((GeneralPath) shape).moveTo(285.447, 201.355);
        ((GeneralPath) shape).curveTo(286.13498, 201.355, 286.582, 201.474,
                286.788, 201.71399);
        ((GeneralPath) shape).curveTo(286.99298, 201.952, 287.098, 202.47398,
                287.098, 203.277);
        ((GeneralPath) shape).curveTo(287.098, 204.08, 286.994, 204.599,
                286.788, 204.84);
        ((GeneralPath) shape).curveTo(286.583, 205.078, 286.139, 205.19699,
                285.447, 205.19699);
        ((GeneralPath) shape).curveTo(284.761, 205.19699, 284.317, 205.07999,
                284.11, 204.84);
        ((GeneralPath) shape).curveTo(283.905, 204.60199, 283.8, 204.081,
                283.8, 203.277);
        ((GeneralPath) shape).curveTo(283.8, 202.47699, 283.90298, 201.95699,
                284.11, 201.71399);
        ((GeneralPath) shape).curveTo(284.315, 201.477, 284.761, 201.355,
                285.447, 201.355);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_248;
        g.setTransform(defaultTransform__0_248);
        g.setClip(clip__0_248);
        float alpha__0_249 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_249 = g.getClip();
        AffineTransform defaultTransform__0_249 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_249 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(290.508, 203.797);
        ((GeneralPath) shape).lineTo(291.056, 203.797);
        ((GeneralPath) shape).lineTo(291.059, 204.0);
        ((GeneralPath) shape).curveTo(291.059, 204.799, 290.537, 205.197,
                289.492, 205.197);
        ((GeneralPath) shape).curveTo(288.822, 205.197, 288.379, 205.072,
                288.16202, 204.819);
        ((GeneralPath) shape).curveTo(287.94803, 204.567, 287.838, 204.048,
                287.838, 203.256);
        ((GeneralPath) shape).curveTo(287.838, 202.51799, 287.94702, 202.01599,
                288.168, 201.75099);
        ((GeneralPath) shape).curveTo(288.386, 201.48499, 288.807, 201.35399,
                289.421, 201.35399);
        ((GeneralPath) shape).curveTo(290.025, 201.35399, 290.44098, 201.44199,
                290.66098, 201.624);
        ((GeneralPath) shape).curveTo(290.88098, 201.80199, 290.99298, 202.138,
                290.99298, 202.62999);
        ((GeneralPath) shape).lineTo(290.447, 202.62999);
        ((GeneralPath) shape).lineTo(290.447, 202.53099);
        ((GeneralPath) shape).curveTo(290.447, 202.243, 290.37698, 202.05199,
                290.232, 201.954);
        ((GeneralPath) shape).curveTo(290.08798, 201.85599, 289.806, 201.808,
                289.378, 201.808);
        ((GeneralPath) shape).curveTo(288.968, 201.808, 288.703, 201.898,
                288.586, 202.081);
        ((GeneralPath) shape).curveTo(288.469, 202.263, 288.411, 202.67099,
                288.411, 203.308);
        ((GeneralPath) shape).curveTo(288.411, 203.927, 288.47702, 204.322,
                288.612, 204.494);
        ((GeneralPath) shape).curveTo(288.745, 204.662, 289.058, 204.748,
                289.55, 204.748);
        ((GeneralPath) shape).curveTo(289.96597, 204.748, 290.231, 204.691,
                290.34198, 204.582);
        ((GeneralPath) shape).curveTo(290.453, 204.471, 290.508, 204.208,
                290.508, 203.797);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_249;
        g.setTransform(defaultTransform__0_249);
        g.setClip(clip__0_249);
        float alpha__0_250 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_250 = g.getClip();
        AffineTransform defaultTransform__0_250 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_250 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(292.364, 199.812);
        ((GeneralPath) shape).lineTo(292.364, 202.91);
        ((GeneralPath) shape).lineTo(292.614, 202.91);
        ((GeneralPath) shape).lineTo(293.932, 201.41);
        ((GeneralPath) shape).lineTo(294.618, 201.41);
        ((GeneralPath) shape).lineTo(293.023, 203.125);
        ((GeneralPath) shape).lineTo(294.906, 205.144);
        ((GeneralPath) shape).lineTo(294.169, 205.144);
        ((GeneralPath) shape).lineTo(292.586, 203.336);
        ((GeneralPath) shape).lineTo(292.364, 203.336);
        ((GeneralPath) shape).lineTo(292.364, 205.144);
        ((GeneralPath) shape).lineTo(291.819, 205.144);
        ((GeneralPath) shape).lineTo(291.819, 199.812);
        ((GeneralPath) shape).lineTo(292.364, 199.812);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_250;
        g.setTransform(defaultTransform__0_250);
        g.setClip(clip__0_250);
        float alpha__0_251 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_251 = g.getClip();
        AffineTransform defaultTransform__0_251 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_251 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(297.844, 202.953);
        ((GeneralPath) shape).lineTo(297.84, 202.77501);
        ((GeneralPath) shape).curveTo(297.84, 202.37502, 297.774, 202.10901,
                297.641, 201.988);
        ((GeneralPath) shape).curveTo(297.509, 201.869, 297.224, 201.806,
                296.783, 201.806);
        ((GeneralPath) shape).curveTo(296.343, 201.806, 296.056, 201.876,
                295.92398, 202.019);
        ((GeneralPath) shape).curveTo(295.79297, 202.162, 295.72897, 202.47,
                295.72897, 202.953);
        ((GeneralPath) shape).lineTo(297.844, 202.953);
        ((GeneralPath) shape).moveTo(297.844, 204.016);
        ((GeneralPath) shape).lineTo(298.402, 204.016);
        ((GeneralPath) shape).lineTo(298.406, 204.15201);
        ((GeneralPath) shape).curveTo(298.406, 204.54102, 298.289, 204.81001,
                298.053, 204.966);
        ((GeneralPath) shape).curveTo(297.819, 205.12001, 297.406, 205.196,
                296.815, 205.196);
        ((GeneralPath) shape).curveTo(296.127, 205.196, 295.678, 205.071,
                295.462, 204.81999);
        ((GeneralPath) shape).curveTo(295.247, 204.56999, 295.142, 204.04199,
                295.142, 203.236);
        ((GeneralPath) shape).curveTo(295.142, 202.49399, 295.247, 201.99399,
                295.464, 201.736);
        ((GeneralPath) shape).curveTo(295.68, 201.482, 296.103, 201.353,
                296.733, 201.353);
        ((GeneralPath) shape).curveTo(297.42, 201.353, 297.868, 201.46199,
                298.085, 201.685);
        ((GeneralPath) shape).curveTo(298.297, 201.90599, 298.40198, 202.373,
                298.40198, 203.084);
        ((GeneralPath) shape).lineTo(298.40198, 203.376);
        ((GeneralPath) shape).lineTo(295.71896, 203.376);
        ((GeneralPath) shape).curveTo(295.71896, 203.964, 295.78195, 204.339,
                295.90695, 204.501);
        ((GeneralPath) shape).curveTo(296.03195, 204.66101, 296.32596,
                204.74301, 296.78995, 204.74301);
        ((GeneralPath) shape).curveTo(297.22794, 204.74301, 297.51596,
                204.70601, 297.64594, 204.628);
        ((GeneralPath) shape).curveTo(297.77695, 204.552, 297.84293, 204.386,
                297.84293, 204.13);
        ((GeneralPath) shape).lineTo(297.844, 204.016);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_251;
        g.setTransform(defaultTransform__0_251);
        g.setClip(clip__0_251);
        float alpha__0_252 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_252 = g.getClip();
        AffineTransform defaultTransform__0_252 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_252 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(300.733, 201.808);
        ((GeneralPath) shape).curveTo(300.331, 201.808, 300.063, 201.9, 299.93,
                202.088);
        ((GeneralPath) shape).curveTo(299.797, 202.273, 299.731, 202.651,
                299.731, 203.218);
        ((GeneralPath) shape).curveTo(299.731, 203.843, 299.79498, 204.253,
                299.92798, 204.45);
        ((GeneralPath) shape).curveTo(300.05798, 204.64299, 300.32898, 204.745,
                300.74197, 204.745);
        ((GeneralPath) shape).curveTo(301.18997, 204.745, 301.48297, 204.64499,
                301.619, 204.45);
        ((GeneralPath) shape).curveTo(301.75598, 204.25499, 301.822, 203.827,
                301.822, 203.16699);
        ((GeneralPath) shape).curveTo(301.822, 202.63399, 301.748, 202.273,
                301.597, 202.08699);
        ((GeneralPath) shape).curveTo(301.452, 201.902, 301.162, 201.808,
                300.733, 201.808);
        ((GeneralPath) shape).moveTo(302.374, 199.812);
        ((GeneralPath) shape).lineTo(302.374, 205.144);
        ((GeneralPath) shape).lineTo(301.828, 205.144);
        ((GeneralPath) shape).lineTo(301.855, 204.662);
        ((GeneralPath) shape).lineTo(301.84, 204.657);
        ((GeneralPath) shape).curveTo(301.668, 205.01799, 301.271, 205.199,
                300.643, 205.199);
        ((GeneralPath) shape).curveTo(300.072, 205.199, 299.682, 205.06,
                299.47202, 204.782);
        ((GeneralPath) shape).curveTo(299.263, 204.506, 299.15802, 203.985,
                299.15802, 203.22299);
        ((GeneralPath) shape).curveTo(299.15802, 202.52399, 299.26303,
                202.03699, 299.47403, 201.76399);
        ((GeneralPath) shape).curveTo(299.68402, 201.493, 300.06003, 201.35599,
                300.60403, 201.35599);
        ((GeneralPath) shape).curveTo(301.28204, 201.35599, 301.68604,
                201.53198, 301.81604, 201.88298);
        ((GeneralPath) shape).lineTo(301.82803, 201.87598);
        ((GeneralPath) shape).lineTo(301.82803, 199.81297);
        ((GeneralPath) shape).lineTo(302.374, 199.812);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_252;
        g.setTransform(defaultTransform__0_252);
        g.setClip(clip__0_252);
        float alpha__0_253 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_253 = g.getClip();
        AffineTransform defaultTransform__0_253 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_253 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        stroke = new BasicStroke(1.0f, 0, 0, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(385.412, 204.773);
        ((GeneralPath) shape).curveTo(385.412, 205.398, 384.90298, 205.907,
                384.27698, 205.907);
        ((GeneralPath) shape).lineTo(378.50797, 205.907);
        ((GeneralPath) shape).curveTo(377.88196, 205.907, 377.37296, 205.398,
                377.37296, 204.773);
        ((GeneralPath) shape).lineTo(377.37296, 198.946);
        ((GeneralPath) shape).curveTo(377.37296, 198.31999, 377.88196, 197.812,
                378.50797, 197.812);
        ((GeneralPath) shape).lineTo(384.27698, 197.812);
        ((GeneralPath) shape).curveTo(384.90298, 197.812, 385.412, 198.31999,
                385.412, 198.946);
        ((GeneralPath) shape).lineTo(385.412, 204.773);
        ((GeneralPath) shape).closePath();
        ((GeneralPath) shape).moveTo(316.913, 204.773);
        ((GeneralPath) shape).curveTo(316.913, 205.398, 316.405, 205.907,
                315.777, 205.907);
        ((GeneralPath) shape).lineTo(310.008, 205.907);
        ((GeneralPath) shape).curveTo(309.382, 205.907, 308.873, 205.398,
                308.873, 204.773);
        ((GeneralPath) shape).lineTo(308.873, 198.946);
        ((GeneralPath) shape).curveTo(308.873, 198.318, 309.382, 197.812,
                310.008, 197.812);
        ((GeneralPath) shape).lineTo(315.777, 197.812);
        ((GeneralPath) shape).curveTo(316.405, 197.812, 316.913, 198.318,
                316.913, 198.946);
        ((GeneralPath) shape).lineTo(316.913, 204.773);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_253;
        g.setTransform(defaultTransform__0_253);
        g.setClip(clip__0_253);
        float alpha__0_254 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_254 = g.getClip();
        AffineTransform defaultTransform__0_254 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_254 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(329.611, 200.321);
        ((GeneralPath) shape).lineTo(329.611, 202.138);
        ((GeneralPath) shape).lineTo(332.313, 202.138);
        ((GeneralPath) shape).lineTo(332.313, 202.646);
        ((GeneralPath) shape).lineTo(329.611, 202.646);
        ((GeneralPath) shape).lineTo(329.611, 204.638);
        ((GeneralPath) shape).lineTo(332.431, 204.638);
        ((GeneralPath) shape).lineTo(332.431, 205.146);
        ((GeneralPath) shape).lineTo(329.011, 205.146);
        ((GeneralPath) shape).lineTo(329.011, 199.813);
        ((GeneralPath) shape).lineTo(332.431, 199.813);
        ((GeneralPath) shape).lineTo(332.431, 200.321);
        ((GeneralPath) shape).lineTo(329.611, 200.321);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_254;
        g.setTransform(defaultTransform__0_254);
        g.setClip(clip__0_254);
        float alpha__0_255 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_255 = g.getClip();
        AffineTransform defaultTransform__0_255 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_255 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(333.177, 201.411);
        ((GeneralPath) shape).lineTo(333.701, 201.411);
        ((GeneralPath) shape).lineTo(333.685, 201.91899);
        ((GeneralPath) shape).lineTo(333.701, 201.93098);
        ((GeneralPath) shape).curveTo(333.866, 201.54799, 334.28, 201.35698,
                334.94, 201.35698);
        ((GeneralPath) shape).curveTo(335.47202, 201.35698, 335.836, 201.45297,
                336.022, 201.63799);
        ((GeneralPath) shape).curveTo(336.21, 201.82599, 336.305, 202.18498,
                336.305, 202.71599);
        ((GeneralPath) shape).lineTo(336.305, 205.14598);
        ((GeneralPath) shape).lineTo(335.759, 205.14598);
        ((GeneralPath) shape).lineTo(335.759, 202.62498);
        ((GeneralPath) shape).curveTo(335.759, 202.30498, 335.696, 202.08998,
                335.573, 201.97798);
        ((GeneralPath) shape).curveTo(335.453, 201.87099, 335.219, 201.81198,
                334.868, 201.81198);
        ((GeneralPath) shape).curveTo(334.101, 201.81198, 333.72, 202.17499,
                333.72, 202.90099);
        ((GeneralPath) shape).lineTo(333.72, 205.14798);
        ((GeneralPath) shape).lineTo(333.174, 205.14798);
        ((GeneralPath) shape).lineTo(333.177, 201.411);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_255;
        g.setTransform(defaultTransform__0_255);
        g.setClip(clip__0_255);
        float alpha__0_256 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_256 = g.getClip();
        AffineTransform defaultTransform__0_256 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_256 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(338.79, 201.81);
        ((GeneralPath) shape).curveTo(338.36102, 201.81, 338.086, 201.899,
                337.956, 202.081);
        ((GeneralPath) shape).curveTo(337.82898, 202.26099, 337.767, 202.655,
                337.767, 203.269);
        ((GeneralPath) shape).curveTo(337.767, 203.886, 337.83, 204.288,
                337.956, 204.47);
        ((GeneralPath) shape).curveTo(338.082, 204.653, 338.361, 204.745,
                338.79, 204.745);
        ((GeneralPath) shape).curveTo(339.225, 204.745, 339.512, 204.64299,
                339.648, 204.44);
        ((GeneralPath) shape).curveTo(339.784, 204.24, 339.854, 203.819,
                339.854, 203.181);
        ((GeneralPath) shape).curveTo(339.854, 202.618, 339.786, 202.249,
                339.648, 202.073);
        ((GeneralPath) shape).curveTo(339.512, 201.899, 339.225, 201.81,
                338.79, 201.81);
        ((GeneralPath) shape).moveTo(340.416, 201.411);
        ((GeneralPath) shape).lineTo(340.416, 205.388);
        ((GeneralPath) shape).curveTo(340.416, 205.929, 340.301, 206.302,
                340.06497, 206.505);
        ((GeneralPath) shape).curveTo(339.82895, 206.70801, 339.40198, 206.81,
                338.77798, 206.81);
        ((GeneralPath) shape).curveTo(338.222, 206.81, 337.84198, 206.724,
                337.63898, 206.552);
        ((GeneralPath) shape).curveTo(337.43698, 206.38, 337.335, 206.06,
                337.335, 205.587);
        ((GeneralPath) shape).lineTo(337.85898, 205.587);
        ((GeneralPath) shape).curveTo(337.85898, 205.905, 337.91998, 206.112,
                338.037, 206.212);
        ((GeneralPath) shape).curveTo(338.15298, 206.308, 338.413, 206.35701,
                338.814, 206.35701);
        ((GeneralPath) shape).curveTo(339.229, 206.35701, 339.508, 206.294,
                339.65298, 206.169);
        ((GeneralPath) shape).curveTo(339.797, 206.044, 339.86697, 205.8,
                339.86697, 205.43501);
        ((GeneralPath) shape).lineTo(339.86697, 204.68501);
        ((GeneralPath) shape).lineTo(339.85498, 204.68102);
        ((GeneralPath) shape).curveTo(339.714, 205.02701, 339.309, 205.20201,
                338.63797, 205.20201);
        ((GeneralPath) shape).curveTo(338.08896, 205.20201, 337.70697,
                205.06502, 337.49997, 204.794);
        ((GeneralPath) shape).curveTo(337.29297, 204.523, 337.18698, 204.024,
                337.18698, 203.304);
        ((GeneralPath) shape).curveTo(337.18698, 202.558, 337.292, 202.046,
                337.50497, 201.771);
        ((GeneralPath) shape).curveTo(337.71497, 201.5, 338.10898, 201.359,
                338.68396, 201.359);
        ((GeneralPath) shape).curveTo(339.29395, 201.359, 339.69495, 201.547,
                339.88095, 201.922);
        ((GeneralPath) shape).lineTo(339.89294, 201.918);
        ((GeneralPath) shape).lineTo(339.86594, 201.415);
        ((GeneralPath) shape).lineTo(340.41394, 201.415);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_256;
        g.setTransform(defaultTransform__0_256);
        g.setClip(clip__0_256);
        float alpha__0_257 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_257 = g.getClip();
        AffineTransform defaultTransform__0_257 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_257 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(341.916, 201.411);
        ((GeneralPath) shape).lineTo(341.916, 205.14299);
        ((GeneralPath) shape).lineTo(341.37, 205.14299);
        ((GeneralPath) shape).lineTo(341.37, 201.411);
        ((GeneralPath) shape).lineTo(341.916, 201.411);
        ((GeneralPath) shape).moveTo(341.916, 199.813);
        ((GeneralPath) shape).lineTo(341.916, 200.427);
        ((GeneralPath) shape).lineTo(341.37, 200.427);
        ((GeneralPath) shape).lineTo(341.37, 199.813);
        ((GeneralPath) shape).lineTo(341.916, 199.813);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_257;
        g.setTransform(defaultTransform__0_257);
        g.setClip(clip__0_257);
        float alpha__0_258 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_258 = g.getClip();
        AffineTransform defaultTransform__0_258 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_258 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(342.872, 201.411);
        ((GeneralPath) shape).lineTo(343.398, 201.411);
        ((GeneralPath) shape).lineTo(343.38202, 201.91899);
        ((GeneralPath) shape).lineTo(343.398, 201.93098);
        ((GeneralPath) shape).curveTo(343.56302, 201.54799, 343.976, 201.35698,
                344.63702, 201.35698);
        ((GeneralPath) shape).curveTo(345.16904, 201.35698, 345.53003,
                201.45297, 345.71902, 201.63799);
        ((GeneralPath) shape).curveTo(345.907, 201.82599, 346.002, 202.18498,
                346.002, 202.71599);
        ((GeneralPath) shape).lineTo(346.002, 205.14598);
        ((GeneralPath) shape).lineTo(345.454, 205.14598);
        ((GeneralPath) shape).lineTo(345.454, 202.62498);
        ((GeneralPath) shape).curveTo(345.454, 202.30498, 345.393, 202.08998,
                345.27002, 201.97798);
        ((GeneralPath) shape).curveTo(345.14902, 201.87099, 344.915, 201.81198,
                344.56403, 201.81198);
        ((GeneralPath) shape).curveTo(343.79803, 201.81198, 343.41702,
                202.17499, 343.41702, 202.90099);
        ((GeneralPath) shape).lineTo(343.41702, 205.14798);
        ((GeneralPath) shape).lineTo(342.86902, 205.14798);
        ((GeneralPath) shape).lineTo(342.872, 201.411);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_258;
        g.setTransform(defaultTransform__0_258);
        g.setClip(clip__0_258);
        float alpha__0_259 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_259 = g.getClip();
        AffineTransform defaultTransform__0_259 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_259 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(349.587, 202.954);
        ((GeneralPath) shape).lineTo(349.581, 202.777);
        ((GeneralPath) shape).curveTo(349.581, 202.377, 349.518, 202.111,
                349.38498, 201.989);
        ((GeneralPath) shape).curveTo(349.24997, 201.87, 348.968, 201.808,
                348.52698, 201.808);
        ((GeneralPath) shape).curveTo(348.089, 201.808, 347.8, 201.878,
                347.66898, 202.02);
        ((GeneralPath) shape).curveTo(347.537, 202.16301, 347.47397, 202.472,
                347.47397, 202.95401);
        ((GeneralPath) shape).lineTo(349.587, 202.95401);
        ((GeneralPath) shape).moveTo(349.587, 204.017);
        ((GeneralPath) shape).lineTo(350.14502, 204.017);
        ((GeneralPath) shape).lineTo(350.14902, 204.15399);
        ((GeneralPath) shape).curveTo(350.14902, 204.54199, 350.032, 204.812,
                349.79602, 204.96799);
        ((GeneralPath) shape).curveTo(349.562, 205.12299, 349.14902, 205.20099,
                348.558, 205.20099);
        ((GeneralPath) shape).curveTo(347.87003, 205.20099, 347.42203,
                205.07599, 347.20502, 204.82199);
        ((GeneralPath) shape).curveTo(346.99002, 204.57199, 346.88602, 204.045,
                346.88602, 203.23799);
        ((GeneralPath) shape).curveTo(346.88602, 202.49599, 346.99002,
                201.99599, 347.20703, 201.73799);
        ((GeneralPath) shape).curveTo(347.42404, 201.484, 347.84705, 201.355,
                348.47604, 201.355);
        ((GeneralPath) shape).curveTo(349.16306, 201.355, 349.61105, 201.46399,
                349.82605, 201.687);
        ((GeneralPath) shape).curveTo(350.03906, 201.91, 350.14505, 202.375,
                350.14505, 203.086);
        ((GeneralPath) shape).lineTo(350.14505, 203.379);
        ((GeneralPath) shape).lineTo(347.46204, 203.379);
        ((GeneralPath) shape).curveTo(347.46204, 203.966, 347.52502, 204.341,
                347.65002, 204.504);
        ((GeneralPath) shape).curveTo(347.77502, 204.664, 348.06903, 204.746,
                348.53302, 204.746);
        ((GeneralPath) shape).curveTo(348.971, 204.746, 349.25903, 204.70801,
                349.389, 204.63);
        ((GeneralPath) shape).curveTo(349.52002, 204.554, 349.583, 204.388,
                349.583, 204.132);
        ((GeneralPath) shape).lineTo(349.583, 204.015);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_259;
        g.setTransform(defaultTransform__0_259);
        g.setClip(clip__0_259);
        float alpha__0_260 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_260 = g.getClip();
        AffineTransform defaultTransform__0_260 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_260 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(357.339, 199.813);
        ((GeneralPath) shape).lineTo(357.339, 205.146);
        ((GeneralPath) shape).lineTo(356.737, 205.146);
        ((GeneralPath) shape).lineTo(356.737, 202.673);
        ((GeneralPath) shape).lineTo(353.628, 202.673);
        ((GeneralPath) shape).lineTo(353.628, 205.146);
        ((GeneralPath) shape).lineTo(353.025, 205.146);
        ((GeneralPath) shape).lineTo(353.025, 199.813);
        ((GeneralPath) shape).lineTo(353.628, 199.813);
        ((GeneralPath) shape).lineTo(353.628, 202.165);
        ((GeneralPath) shape).lineTo(356.737, 202.165);
        ((GeneralPath) shape).lineTo(356.737, 199.813);
        ((GeneralPath) shape).lineTo(357.339, 199.813);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_260;
        g.setTransform(defaultTransform__0_260);
        g.setClip(clip__0_260);
        float alpha__0_261 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_261 = g.getClip();
        AffineTransform defaultTransform__0_261 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_261 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(358.87, 201.411);
        ((GeneralPath) shape).lineTo(358.87, 205.14299);
        ((GeneralPath) shape).lineTo(358.324, 205.14299);
        ((GeneralPath) shape).lineTo(358.324, 201.411);
        ((GeneralPath) shape).lineTo(358.87, 201.411);
        ((GeneralPath) shape).moveTo(358.87, 199.813);
        ((GeneralPath) shape).lineTo(358.87, 200.427);
        ((GeneralPath) shape).lineTo(358.324, 200.427);
        ((GeneralPath) shape).lineTo(358.324, 199.813);
        ((GeneralPath) shape).lineTo(358.87, 199.813);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_261;
        g.setTransform(defaultTransform__0_261);
        g.setClip(clip__0_261);
        float alpha__0_262 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_262 = g.getClip();
        AffineTransform defaultTransform__0_262 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_262 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(361.803, 201.411);
        ((GeneralPath) shape).lineTo(361.803, 201.862);
        ((GeneralPath) shape).lineTo(360.366, 201.862);
        ((GeneralPath) shape).lineTo(360.366, 204.147);
        ((GeneralPath) shape).curveTo(360.366, 204.546, 360.544, 204.74701,
                360.896, 204.74701);
        ((GeneralPath) shape).curveTo(361.248, 204.74701, 361.422, 204.56702,
                361.422, 204.212);
        ((GeneralPath) shape).lineTo(361.426, 204.026);
        ((GeneralPath) shape).lineTo(361.434, 203.819);
        ((GeneralPath) shape).lineTo(361.94098, 203.819);
        ((GeneralPath) shape).lineTo(361.94498, 204.096);
        ((GeneralPath) shape).curveTo(361.94498, 204.82999, 361.598, 205.19899,
                360.89996, 205.19899);
        ((GeneralPath) shape).curveTo(360.17996, 205.19899, 359.81998,
                204.89299, 359.81998, 204.27998);
        ((GeneralPath) shape).lineTo(359.81998, 201.86198);
        ((GeneralPath) shape).lineTo(359.30496, 201.86198);
        ((GeneralPath) shape).lineTo(359.30496, 201.41098);
        ((GeneralPath) shape).lineTo(359.81998, 201.41098);
        ((GeneralPath) shape).lineTo(359.81998, 200.51299);
        ((GeneralPath) shape).lineTo(360.36597, 200.51299);
        ((GeneralPath) shape).lineTo(360.36597, 201.41098);
        ((GeneralPath) shape).lineTo(361.803, 201.41098);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_262;
        g.setTransform(defaultTransform__0_262);
        g.setClip(clip__0_262);
        float alpha__0_263 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_263 = g.getClip();
        AffineTransform defaultTransform__0_263 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_263 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(260.588, 235.027);
        ((GeneralPath) shape).lineTo(259.992, 235.027);
        ((GeneralPath) shape).curveTo(259.992, 234.597, 259.922, 234.32799,
                259.778, 234.211);
        ((GeneralPath) shape).curveTo(259.635, 234.096, 259.299, 234.037,
                258.768, 234.037);
        ((GeneralPath) shape).curveTo(258.141, 234.037, 257.73502, 234.092,
                257.552, 234.203);
        ((GeneralPath) shape).curveTo(257.368, 234.31, 257.279, 234.558,
                257.279, 234.94101);
        ((GeneralPath) shape).curveTo(257.279, 235.37302, 257.349, 235.632,
                257.494, 235.72601);
        ((GeneralPath) shape).curveTo(257.638, 235.82, 258.06097, 235.88202,
                258.76797, 235.917);
        ((GeneralPath) shape).curveTo(259.597, 235.95201, 260.12198, 236.05801,
                260.34897, 236.231);
        ((GeneralPath) shape).curveTo(260.57397, 236.405, 260.68597, 236.795,
                260.68597, 237.403);
        ((GeneralPath) shape).curveTo(260.68597, 238.059, 260.55798, 238.483,
                260.29596, 238.676);
        ((GeneralPath) shape).curveTo(260.03897, 238.86699, 259.46695, 238.965,
                258.58197, 238.965);
        ((GeneralPath) shape).curveTo(257.81598, 238.965, 257.30698, 238.869,
                257.05396, 238.674);
        ((GeneralPath) shape).curveTo(256.79895, 238.481, 256.67197, 238.09,
                256.67197, 237.5);
        ((GeneralPath) shape).lineTo(256.66797, 237.262);
        ((GeneralPath) shape).lineTo(257.26697, 237.262);
        ((GeneralPath) shape).lineTo(257.26697, 237.39499);
        ((GeneralPath) shape).curveTo(257.26697, 237.874, 257.33698, 238.16599,
                257.48596, 238.286);
        ((GeneralPath) shape).curveTo(257.62997, 238.399, 258.00397, 238.45999,
                258.60495, 238.45999);
        ((GeneralPath) shape).curveTo(259.29294, 238.45999, 259.71396,
                238.40099, 259.87595, 238.286);
        ((GeneralPath) shape).curveTo(260.03394, 238.17099, 260.11395, 237.86,
                260.11395, 237.356);
        ((GeneralPath) shape).curveTo(260.11395, 237.034, 260.06094, 236.815,
                259.95197, 236.70801);
        ((GeneralPath) shape).curveTo(259.84695, 236.60101, 259.61896,
                236.53601, 259.27496, 236.51501);
        ((GeneralPath) shape).lineTo(258.65097, 236.48401);
        ((GeneralPath) shape).lineTo(258.05798, 236.455);
        ((GeneralPath) shape).curveTo(257.158, 236.392, 256.70398, 235.924,
                256.70398, 235.049);
        ((GeneralPath) shape).curveTo(256.70398, 234.444, 256.83398, 234.037,
                257.09998, 233.834);
        ((GeneralPath) shape).curveTo(257.36096, 233.631, 257.88498, 233.527,
                258.66898, 233.527);
        ((GeneralPath) shape).curveTo(259.464, 233.527, 259.98196, 233.62299,
                260.227, 233.81);
        ((GeneralPath) shape).curveTo(260.468, 233.998, 260.588, 234.404,
                260.588, 235.027);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_263;
        g.setTransform(defaultTransform__0_263);
        g.setClip(clip__0_263);
        float alpha__0_264 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_264 = g.getClip();
        AffineTransform defaultTransform__0_264 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_264 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(263.359, 235.179);
        ((GeneralPath) shape).lineTo(263.359, 235.632);
        ((GeneralPath) shape).lineTo(261.922, 235.632);
        ((GeneralPath) shape).lineTo(261.922, 237.917);
        ((GeneralPath) shape).curveTo(261.922, 238.313, 262.099, 238.51501,
                262.452, 238.51501);
        ((GeneralPath) shape).curveTo(262.805, 238.51501, 262.978, 238.33702,
                262.978, 237.98001);
        ((GeneralPath) shape).lineTo(262.982, 237.796);
        ((GeneralPath) shape).lineTo(262.99, 237.589);
        ((GeneralPath) shape).lineTo(263.49698, 237.589);
        ((GeneralPath) shape).lineTo(263.50098, 237.864);
        ((GeneralPath) shape).curveTo(263.50098, 238.59799, 263.154, 238.968,
                262.45596, 238.968);
        ((GeneralPath) shape).curveTo(261.73697, 238.968, 261.37598, 238.66301,
                261.37598, 238.05);
        ((GeneralPath) shape).lineTo(261.37598, 235.632);
        ((GeneralPath) shape).lineTo(260.86096, 235.632);
        ((GeneralPath) shape).lineTo(260.86096, 235.179);
        ((GeneralPath) shape).lineTo(261.37598, 235.179);
        ((GeneralPath) shape).lineTo(261.37598, 234.281);
        ((GeneralPath) shape).lineTo(261.92197, 234.281);
        ((GeneralPath) shape).lineTo(261.92197, 235.179);
        ((GeneralPath) shape).lineTo(263.359, 235.179);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_264;
        g.setTransform(defaultTransform__0_264);
        g.setClip(clip__0_264);
        float alpha__0_265 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_265 = g.getClip();
        AffineTransform defaultTransform__0_265 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_265 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(265.431, 237.066);
        ((GeneralPath) shape).curveTo(265.014, 237.066, 264.741, 237.109,
                264.614, 237.19899);
        ((GeneralPath) shape).curveTo(264.489, 237.28899, 264.42502, 237.47598,
                264.42502, 237.769);
        ((GeneralPath) shape).curveTo(264.42502, 238.06999, 264.488, 238.271,
                264.613, 238.371);
        ((GeneralPath) shape).curveTo(264.738, 238.46901, 264.991, 238.517,
                265.37302, 238.517);
        ((GeneralPath) shape).curveTo(266.143, 238.517, 266.527, 238.283,
                266.527, 237.814);
        ((GeneralPath) shape).curveTo(266.527, 237.521, 266.453, 237.32199,
                266.302, 237.22);
        ((GeneralPath) shape).curveTo(266.154, 237.117, 265.864, 237.066,
                265.431, 237.066);
        ((GeneralPath) shape).moveTo(264.554, 236.226);
        ((GeneralPath) shape).lineTo(264.012, 236.226);
        ((GeneralPath) shape).curveTo(264.012, 235.79199, 264.11, 235.499,
                264.306, 235.349);
        ((GeneralPath) shape).curveTo(264.499, 235.203, 264.885, 235.124,
                265.456, 235.124);
        ((GeneralPath) shape).curveTo(266.076, 235.124, 266.49698, 235.21599,
                266.715, 235.39699);
        ((GeneralPath) shape).curveTo(266.935, 235.583, 267.042, 235.93,
                267.042, 236.44598);
        ((GeneralPath) shape).lineTo(267.042, 238.91298);
        ((GeneralPath) shape).lineTo(266.496, 238.91298);
        ((GeneralPath) shape).lineTo(266.539, 238.51099);
        ((GeneralPath) shape).lineTo(266.527, 238.50699);
        ((GeneralPath) shape).curveTo(266.32, 238.814, 265.898, 238.96799,
                265.26, 238.96799);
        ((GeneralPath) shape).curveTo(264.32, 238.96799, 263.84802, 238.58899,
                263.84802, 237.831);
        ((GeneralPath) shape).curveTo(263.84802, 237.38199, 263.95203,
                237.06699, 264.16202, 236.89499);
        ((GeneralPath) shape).curveTo(264.372, 236.72299, 264.752, 236.637,
                265.30603, 236.637);
        ((GeneralPath) shape).curveTo(265.95905, 236.637, 266.35303, 236.76599,
                266.48303, 237.02399);
        ((GeneralPath) shape).lineTo(266.49503, 237.01999);
        ((GeneralPath) shape).lineTo(266.49503, 236.56699);
        ((GeneralPath) shape).curveTo(266.49503, 236.14099, 266.437, 235.86398,
                266.32004, 235.73698);
        ((GeneralPath) shape).curveTo(266.20303, 235.61198, 265.94803,
                235.54898, 265.55203, 235.54898);
        ((GeneralPath) shape).curveTo(264.88104, 235.54898, 264.54404,
                235.73698, 264.54404, 236.11298);
        ((GeneralPath) shape).curveTo(264.55, 236.134, 264.55, 236.171,
                264.554, 236.226);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_265;
        g.setTransform(defaultTransform__0_265);
        g.setClip(clip__0_265);
        float alpha__0_266 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_266 = g.getClip();
        AffineTransform defaultTransform__0_266 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_266 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(269.697, 235.578);
        ((GeneralPath) shape).curveTo(269.268, 235.578, 268.982, 235.67,
                268.836, 235.851);
        ((GeneralPath) shape).curveTo(268.691, 236.037, 268.617, 236.4,
                268.617, 236.94699);
        ((GeneralPath) shape).curveTo(268.617, 237.62299, 268.681, 238.051,
                268.814, 238.236);
        ((GeneralPath) shape).curveTo(268.945, 238.422, 269.247, 238.51299,
                269.72, 238.51299);
        ((GeneralPath) shape).curveTo(270.118, 238.51299, 270.385, 238.41899,
                270.513, 238.22998);
        ((GeneralPath) shape).curveTo(270.641, 238.04198, 270.704, 237.65398,
                270.704, 237.06798);
        ((GeneralPath) shape).curveTo(270.704, 236.45297, 270.64102, 236.04797,
                270.515, 235.85898);
        ((GeneralPath) shape).curveTo(270.39, 235.673, 270.117, 235.578,
                269.697, 235.578);
        ((GeneralPath) shape).moveTo(268.056, 238.914);
        ((GeneralPath) shape).lineTo(268.056, 233.582);
        ((GeneralPath) shape).lineTo(268.602, 233.582);
        ((GeneralPath) shape).lineTo(268.602, 235.654);
        ((GeneralPath) shape).lineTo(268.61398, 235.66801);
        ((GeneralPath) shape).curveTo(268.73898, 235.30702, 269.146, 235.12502,
                269.83798, 235.12502);
        ((GeneralPath) shape).curveTo(270.38397, 235.12502, 270.75998,
                235.26602, 270.968, 235.55101);
        ((GeneralPath) shape).curveTo(271.175, 235.83401, 271.28098, 236.348,
                271.28098, 237.09401);
        ((GeneralPath) shape).curveTo(271.28098, 237.79901, 271.17197, 238.287,
                270.95197, 238.561);
        ((GeneralPath) shape).curveTo(270.73297, 238.832, 270.33597, 238.96901,
                269.76096, 238.96901);
        ((GeneralPath) shape).curveTo(269.17596, 238.96901, 268.78595,
                238.79701, 268.59195, 238.44801);
        ((GeneralPath) shape).lineTo(268.57596, 238.45401);
        ((GeneralPath) shape).lineTo(268.60297, 238.91501);
        ((GeneralPath) shape).lineTo(268.056, 238.91501);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_266;
        g.setTransform(defaultTransform__0_266);
        g.setClip(clip__0_266);
        float alpha__0_267 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_267 = g.getClip();
        AffineTransform defaultTransform__0_267 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_267 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(272.682, 235.179);
        ((GeneralPath) shape).lineTo(272.682, 238.913);
        ((GeneralPath) shape).lineTo(272.13602, 238.913);
        ((GeneralPath) shape).lineTo(272.13602, 235.179);
        ((GeneralPath) shape).lineTo(272.682, 235.179);
        ((GeneralPath) shape).moveTo(272.682, 233.582);
        ((GeneralPath) shape).lineTo(272.682, 234.193);
        ((GeneralPath) shape).lineTo(272.13602, 234.193);
        ((GeneralPath) shape).lineTo(272.13602, 233.582);
        ((GeneralPath) shape).lineTo(272.682, 233.582);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_267;
        g.setTransform(defaultTransform__0_267);
        g.setClip(clip__0_267);
        float alpha__0_268 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_268 = g.getClip();
        AffineTransform defaultTransform__0_268 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_268 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new Rectangle2D.Double(273.635986328125, 233.58200073242188,
                0.5460000038146973, 5.331999778747559);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_268;
        g.setTransform(defaultTransform__0_268);
        g.setClip(clip__0_268);
        float alpha__0_269 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_269 = g.getClip();
        AffineTransform defaultTransform__0_269 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_269 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(275.683, 235.179);
        ((GeneralPath) shape).lineTo(275.683, 238.913);
        ((GeneralPath) shape).lineTo(275.13702, 238.913);
        ((GeneralPath) shape).lineTo(275.13702, 235.179);
        ((GeneralPath) shape).lineTo(275.683, 235.179);
        ((GeneralPath) shape).moveTo(275.683, 233.582);
        ((GeneralPath) shape).lineTo(275.683, 234.193);
        ((GeneralPath) shape).lineTo(275.13702, 234.193);
        ((GeneralPath) shape).lineTo(275.13702, 233.582);
        ((GeneralPath) shape).lineTo(275.683, 233.582);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_269;
        g.setTransform(defaultTransform__0_269);
        g.setClip(clip__0_269);
        float alpha__0_270 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_270 = g.getClip();
        AffineTransform defaultTransform__0_270 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_270 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(276.516, 235.179);
        ((GeneralPath) shape).lineTo(279.202, 235.179);
        ((GeneralPath) shape).lineTo(279.202, 235.71);
        ((GeneralPath) shape).lineTo(276.979, 238.46);
        ((GeneralPath) shape).lineTo(279.202, 238.46);
        ((GeneralPath) shape).lineTo(279.202, 238.914);
        ((GeneralPath) shape).lineTo(276.329, 238.914);
        ((GeneralPath) shape).lineTo(276.329, 238.394);
        ((GeneralPath) shape).lineTo(278.559, 235.632);
        ((GeneralPath) shape).lineTo(276.516, 235.632);
        ((GeneralPath) shape).lineTo(276.516, 235.179);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_270;
        g.setTransform(defaultTransform__0_270);
        g.setClip(clip__0_270);
        float alpha__0_271 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_271 = g.getClip();
        AffineTransform defaultTransform__0_271 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_271 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(282.446, 236.722);
        ((GeneralPath) shape).lineTo(282.44202, 236.546);
        ((GeneralPath) shape).curveTo(282.44202, 236.14401, 282.376, 235.88,
                282.243, 235.759);
        ((GeneralPath) shape).curveTo(282.11002, 235.638, 281.82602, 235.577,
                281.38702, 235.577);
        ((GeneralPath) shape).curveTo(280.946, 235.577, 280.65802, 235.64499,
                280.528, 235.79);
        ((GeneralPath) shape).curveTo(280.397, 235.931, 280.33102, 236.239,
                280.33102, 236.722);
        ((GeneralPath) shape).lineTo(282.446, 236.722);
        ((GeneralPath) shape).moveTo(282.446, 237.785);
        ((GeneralPath) shape).lineTo(283.00403, 237.785);
        ((GeneralPath) shape).lineTo(283.00803, 237.922);
        ((GeneralPath) shape).curveTo(283.00803, 238.30899, 282.89304, 238.58,
                282.65503, 238.735);
        ((GeneralPath) shape).curveTo(282.42102, 238.889, 282.00803, 238.967,
                281.41702, 238.967);
        ((GeneralPath) shape).curveTo(280.73, 238.967, 280.28003, 238.842,
                280.066, 238.59);
        ((GeneralPath) shape).curveTo(279.85202, 238.34, 279.745, 237.81099,
                279.745, 237.006);
        ((GeneralPath) shape).curveTo(279.745, 236.262, 279.852, 235.762,
                280.068, 235.506);
        ((GeneralPath) shape).curveTo(280.283, 235.252, 280.706, 235.123,
                281.335, 235.123);
        ((GeneralPath) shape).curveTo(282.02298, 235.123, 282.472, 235.23,
                282.686, 235.455);
        ((GeneralPath) shape).curveTo(282.898, 235.676, 283.004, 236.143,
                283.004, 236.851);
        ((GeneralPath) shape).lineTo(283.004, 237.144);
        ((GeneralPath) shape).lineTo(280.32098, 237.144);
        ((GeneralPath) shape).curveTo(280.32098, 237.732, 280.38397, 238.107,
                280.50897, 238.269);
        ((GeneralPath) shape).curveTo(280.63397, 238.429, 280.929, 238.511,
                281.39398, 238.511);
        ((GeneralPath) shape).curveTo(281.83197, 238.511, 282.11697, 238.474,
                282.248, 238.396);
        ((GeneralPath) shape).curveTo(282.378, 238.31999, 282.44498, 238.15399,
                282.44498, 237.898);
        ((GeneralPath) shape).lineTo(282.446, 237.785);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_271;
        g.setTransform(defaultTransform__0_271);
        g.setClip(clip__0_271);
        float alpha__0_272 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_272 = g.getClip();
        AffineTransform defaultTransform__0_272 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_272 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(283.916, 235.179);
        ((GeneralPath) shape).lineTo(284.46198, 235.179);
        ((GeneralPath) shape).lineTo(284.40698, 235.609);
        ((GeneralPath) shape).lineTo(284.41898, 235.623);
        ((GeneralPath) shape).curveTo(284.63297, 235.269, 284.99, 235.09601,
                285.48697, 235.09601);
        ((GeneralPath) shape).curveTo(286.17297, 235.09601, 286.51596,
                235.45001, 286.51596, 236.15901);
        ((GeneralPath) shape).lineTo(286.51196, 236.41801);
        ((GeneralPath) shape).lineTo(285.97296, 236.41801);
        ((GeneralPath) shape).lineTo(285.98495, 236.32202);
        ((GeneralPath) shape).curveTo(285.99295, 236.22401, 285.99695,
                236.15903, 285.99695, 236.12502);
        ((GeneralPath) shape).curveTo(285.99695, 235.74002, 285.78995,
                235.55101, 285.37296, 235.55101);
        ((GeneralPath) shape).curveTo(284.76697, 235.55101, 284.46097,
                235.92601, 284.46097, 236.68001);
        ((GeneralPath) shape).lineTo(284.46097, 238.91801);
        ((GeneralPath) shape).lineTo(283.91498, 238.91801);
        ((GeneralPath) shape).lineTo(283.916, 235.179);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_272;
        g.setTransform(defaultTransform__0_272);
        g.setClip(clip__0_272);
        float alpha__0_273 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_273 = g.getClip();
        AffineTransform defaultTransform__0_273 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_273 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(289.928, 236.156);
        ((GeneralPath) shape).lineTo(289.38, 236.156);
        ((GeneralPath) shape).curveTo(289.38, 235.89601, 289.327, 235.73401,
                289.224, 235.67401);
        ((GeneralPath) shape).curveTo(289.119, 235.61101, 288.848, 235.58002,
                288.411, 235.58002);
        ((GeneralPath) shape).curveTo(288.005, 235.58002, 287.74802, 235.61102,
                287.64, 235.68002);
        ((GeneralPath) shape).curveTo(287.531, 235.74602, 287.476, 235.90503,
                287.476, 236.15903);
        ((GeneralPath) shape).curveTo(287.476, 236.54202, 287.65903, 236.73903,
                288.026, 236.76103);
        ((GeneralPath) shape).lineTo(288.465, 236.78403);
        ((GeneralPath) shape).lineTo(289.024, 236.80902);
        ((GeneralPath) shape).curveTo(289.698, 236.84203, 290.038, 237.19601,
                290.038, 237.87202);
        ((GeneralPath) shape).curveTo(290.038, 238.29002, 289.929, 238.57903,
                289.703, 238.73302);
        ((GeneralPath) shape).curveTo(289.48102, 238.88902, 289.072, 238.96701,
                288.475, 238.96701);
        ((GeneralPath) shape).curveTo(287.86502, 238.96701, 287.444, 238.893,
                287.212, 238.744);
        ((GeneralPath) shape).curveTo(286.983, 238.595, 286.867, 238.324,
                286.867, 237.92801);
        ((GeneralPath) shape).lineTo(286.871, 237.725);
        ((GeneralPath) shape).lineTo(287.436, 237.725);
        ((GeneralPath) shape).lineTo(287.44, 237.901);
        ((GeneralPath) shape).curveTo(287.44, 238.145, 287.503, 238.309,
                287.627, 238.391);
        ((GeneralPath) shape).curveTo(287.752, 238.473, 287.99802, 238.514,
                288.364, 238.514);
        ((GeneralPath) shape).curveTo(288.81302, 238.514, 289.109, 238.47101,
                289.251, 238.38501);
        ((GeneralPath) shape).curveTo(289.394, 238.29901, 289.465, 238.119,
                289.465, 237.84601);
        ((GeneralPath) shape).curveTo(289.465, 237.453, 289.286, 237.25601,
                288.931, 237.25601);
        ((GeneralPath) shape).curveTo(288.103, 237.25601, 287.557, 237.186,
                287.294, 237.04501);
        ((GeneralPath) shape).curveTo(287.03302, 236.90201, 286.902, 236.61101,
                286.902, 236.17001);
        ((GeneralPath) shape).curveTo(286.902, 235.75201, 287.006, 235.473,
                287.212, 235.33202);
        ((GeneralPath) shape).curveTo(287.419, 235.19101, 287.83002, 235.12302,
                288.446, 235.12302);
        ((GeneralPath) shape).curveTo(289.432, 235.12302, 289.928, 235.42001,
                289.928, 236.01802);
        ((GeneralPath) shape).lineTo(289.928, 236.156);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_273;
        g.setTransform(defaultTransform__0_273);
        g.setClip(clip__0_273);
        float alpha__0_274 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_274 = g.getClip();
        AffineTransform defaultTransform__0_274 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_274 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        stroke = new BasicStroke(1.0f, 0, 0, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(295.654, 249.118);
        ((GeneralPath) shape).curveTo(295.654, 249.744, 295.146, 250.25099,
                294.51898, 250.25099);
        ((GeneralPath) shape).lineTo(288.75098, 250.25099);
        ((GeneralPath) shape).curveTo(288.12396, 250.25099, 287.61597, 249.745,
                287.61597, 249.118);
        ((GeneralPath) shape).lineTo(287.61597, 243.289);
        ((GeneralPath) shape).curveTo(287.61597, 242.664, 288.12396, 242.153,
                288.75098, 242.153);
        ((GeneralPath) shape).lineTo(294.51898, 242.153);
        ((GeneralPath) shape).curveTo(295.146, 242.153, 295.654, 242.664,
                295.654, 243.289);
        ((GeneralPath) shape).lineTo(295.654, 249.118);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_274;
        g.setTransform(defaultTransform__0_274);
        g.setClip(clip__0_274);
        float alpha__0_275 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_275 = g.getClip();
        AffineTransform defaultTransform__0_275 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_275 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(263.212, 243.713);
        ((GeneralPath) shape).lineTo(263.212, 245.576);
        ((GeneralPath) shape).lineTo(265.805, 245.576);
        ((GeneralPath) shape).lineTo(265.805, 246.084);
        ((GeneralPath) shape).lineTo(263.212, 246.084);
        ((GeneralPath) shape).lineTo(263.212, 248.537);
        ((GeneralPath) shape).lineTo(262.612, 248.537);
        ((GeneralPath) shape).lineTo(262.612, 243.205);
        ((GeneralPath) shape).lineTo(265.875, 243.205);
        ((GeneralPath) shape).lineTo(265.875, 243.713);
        ((GeneralPath) shape).lineTo(263.212, 243.713);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_275;
        g.setTransform(defaultTransform__0_275);
        g.setClip(clip__0_275);
        float alpha__0_276 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_276 = g.getClip();
        AffineTransform defaultTransform__0_276 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_276 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(266.544, 244.803);
        ((GeneralPath) shape).lineTo(267.09, 244.803);
        ((GeneralPath) shape).lineTo(267.035, 245.23299);
        ((GeneralPath) shape).lineTo(267.046, 245.247);
        ((GeneralPath) shape).curveTo(267.25998, 244.89299, 267.617, 244.719,
                268.11398, 244.719);
        ((GeneralPath) shape).curveTo(268.801, 244.719, 269.14297, 245.073,
                269.14297, 245.782);
        ((GeneralPath) shape).lineTo(269.13998, 246.04199);
        ((GeneralPath) shape).lineTo(268.60098, 246.04199);
        ((GeneralPath) shape).lineTo(268.61298, 245.946);
        ((GeneralPath) shape).curveTo(268.62097, 245.84799, 268.62497, 245.782,
                268.62497, 245.749);
        ((GeneralPath) shape).curveTo(268.62497, 245.364, 268.41797, 245.174,
                268.00098, 245.174);
        ((GeneralPath) shape).curveTo(267.395, 245.174, 267.089, 245.549,
                267.089, 246.303);
        ((GeneralPath) shape).lineTo(267.089, 248.541);
        ((GeneralPath) shape).lineTo(266.543, 248.541);
        ((GeneralPath) shape).lineTo(266.544, 244.803);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_276;
        g.setTransform(defaultTransform__0_276);
        g.setClip(clip__0_276);
        float alpha__0_277 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_277 = g.getClip();
        AffineTransform defaultTransform__0_277 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_277 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(271.208, 245.201);
        ((GeneralPath) shape).curveTo(270.72702, 245.201, 270.42902, 245.27701,
                270.312, 245.435);
        ((GeneralPath) shape).curveTo(270.19702, 245.591, 270.138, 246.00299,
                270.138, 246.66699);
        ((GeneralPath) shape).curveTo(270.138, 247.331, 270.195, 247.74199,
                270.312, 247.89699);
        ((GeneralPath) shape).curveTo(270.42502, 248.053, 270.72702, 248.13298,
                271.208, 248.13298);
        ((GeneralPath) shape).curveTo(271.693, 248.13298, 271.99402, 248.05498,
                272.11102, 247.89699);
        ((GeneralPath) shape).curveTo(272.226, 247.74298, 272.28403, 247.331,
                272.28403, 246.66699);
        ((GeneralPath) shape).curveTo(272.28403, 246.00299, 272.22803,
                245.59299, 272.11102, 245.435);
        ((GeneralPath) shape).curveTo(271.996, 245.281, 271.696, 245.201,
                271.208, 245.201);
        ((GeneralPath) shape).moveTo(271.208, 244.748);
        ((GeneralPath) shape).curveTo(271.896, 244.748, 272.345, 244.867,
                272.549, 245.105);
        ((GeneralPath) shape).curveTo(272.754, 245.343, 272.859, 245.86699,
                272.859, 246.668);
        ((GeneralPath) shape).curveTo(272.859, 247.469, 272.755, 247.99,
                272.549, 248.231);
        ((GeneralPath) shape).curveTo(272.346, 248.46901, 271.90002, 248.59,
                271.208, 248.59);
        ((GeneralPath) shape).curveTo(270.522, 248.59, 270.078, 248.471,
                269.871, 248.231);
        ((GeneralPath) shape).curveTo(269.66602, 247.99501, 269.563, 247.47101,
                269.563, 246.668);
        ((GeneralPath) shape).curveTo(269.563, 245.868, 269.664, 245.346,
                269.871, 245.105);
        ((GeneralPath) shape).curveTo(270.076, 244.87, 270.522, 244.748,
                271.208, 244.748);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_277;
        g.setTransform(defaultTransform__0_277);
        g.setClip(clip__0_277);
        float alpha__0_278 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_278 = g.getClip();
        AffineTransform defaultTransform__0_278 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_278 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(273.709, 244.803);
        ((GeneralPath) shape).lineTo(274.23502, 244.803);
        ((GeneralPath) shape).lineTo(274.22, 245.31099);
        ((GeneralPath) shape).lineTo(274.23502, 245.32298);
        ((GeneralPath) shape).curveTo(274.401, 244.93999, 274.81403, 244.74998,
                275.475, 244.74998);
        ((GeneralPath) shape).curveTo(276.005, 244.74998, 276.368, 244.84398,
                276.557, 245.02899);
        ((GeneralPath) shape).curveTo(276.74402, 245.217, 276.837, 245.57799,
                276.837, 246.107);
        ((GeneralPath) shape).lineTo(276.837, 248.539);
        ((GeneralPath) shape).lineTo(276.29102, 248.539);
        ((GeneralPath) shape).lineTo(276.29102, 246.016);
        ((GeneralPath) shape).curveTo(276.29102, 245.696, 276.23102, 245.481,
                276.108, 245.37001);
        ((GeneralPath) shape).curveTo(275.987, 245.26102, 275.751, 245.20401,
                275.402, 245.20401);
        ((GeneralPath) shape).curveTo(274.63602, 245.20401, 274.252, 245.56601,
                274.252, 246.294);
        ((GeneralPath) shape).lineTo(274.252, 248.54001);
        ((GeneralPath) shape).lineTo(273.70602, 248.54001);
        ((GeneralPath) shape).lineTo(273.709, 244.803);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_278;
        g.setTransform(defaultTransform__0_278);
        g.setClip(clip__0_278);
        float alpha__0_279 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_279 = g.getClip();
        AffineTransform defaultTransform__0_279 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_279 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(279.807, 244.803);
        ((GeneralPath) shape).lineTo(279.807, 245.256);
        ((GeneralPath) shape).lineTo(278.37, 245.256);
        ((GeneralPath) shape).lineTo(278.37, 247.541);
        ((GeneralPath) shape).curveTo(278.37, 247.938, 278.54498, 248.139,
                278.9, 248.139);
        ((GeneralPath) shape).curveTo(279.251, 248.139, 279.426, 247.96101,
                279.426, 247.604);
        ((GeneralPath) shape).lineTo(279.43, 247.42);
        ((GeneralPath) shape).lineTo(279.438, 247.213);
        ((GeneralPath) shape).lineTo(279.94498, 247.213);
        ((GeneralPath) shape).lineTo(279.94897, 247.48799);
        ((GeneralPath) shape).curveTo(279.94897, 248.22198, 279.602, 248.592,
                278.90396, 248.592);
        ((GeneralPath) shape).curveTo(278.18497, 248.592, 277.82397, 248.287,
                277.82397, 247.67499);
        ((GeneralPath) shape).lineTo(277.82397, 245.25699);
        ((GeneralPath) shape).lineTo(277.30896, 245.25699);
        ((GeneralPath) shape).lineTo(277.30896, 244.80399);
        ((GeneralPath) shape).lineTo(277.82397, 244.80399);
        ((GeneralPath) shape).lineTo(277.82397, 243.90599);
        ((GeneralPath) shape).lineTo(278.36996, 243.90599);
        ((GeneralPath) shape).lineTo(278.36996, 244.80399);
        ((GeneralPath) shape).lineTo(279.807, 244.803);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_279;
        g.setTransform(defaultTransform__0_279);
        g.setClip(clip__0_279);
        float alpha__0_280 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_280 = g.getClip();
        AffineTransform defaultTransform__0_280 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_280 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        stroke = new BasicStroke(1.0f, 0, 0, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(342.427, 249.118);
        ((GeneralPath) shape).curveTo(342.427, 249.744, 341.92, 250.252,
                341.29102, 250.252);
        ((GeneralPath) shape).lineTo(335.522, 250.252);
        ((GeneralPath) shape).curveTo(334.896, 250.252, 334.388, 249.745,
                334.388, 249.118);
        ((GeneralPath) shape).lineTo(334.388, 243.289);
        ((GeneralPath) shape).curveTo(334.388, 242.664, 334.896, 242.153,
                335.522, 242.153);
        ((GeneralPath) shape).lineTo(341.29102, 242.153);
        ((GeneralPath) shape).curveTo(341.92, 242.153, 342.427, 242.664,
                342.427, 243.289);
        ((GeneralPath) shape).lineTo(342.427, 249.118);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_280;
        g.setTransform(defaultTransform__0_280);
        g.setClip(clip__0_280);
        float alpha__0_281 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_281 = g.getClip();
        AffineTransform defaultTransform__0_281 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_281 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(309.985, 243.205);
        ((GeneralPath) shape).lineTo(309.985, 247.979);
        ((GeneralPath) shape).lineTo(312.722, 247.979);
        ((GeneralPath) shape).lineTo(312.722, 248.537);
        ((GeneralPath) shape).lineTo(309.385, 248.537);
        ((GeneralPath) shape).lineTo(309.385, 243.205);
        ((GeneralPath) shape).lineTo(309.985, 243.205);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_281;
        g.setTransform(defaultTransform__0_281);
        g.setClip(clip__0_281);
        float alpha__0_282 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_282 = g.getClip();
        AffineTransform defaultTransform__0_282 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_282 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(315.822, 246.346);
        ((GeneralPath) shape).lineTo(315.818, 246.17);
        ((GeneralPath) shape).curveTo(315.818, 245.768, 315.754, 245.504,
                315.619, 245.383);
        ((GeneralPath) shape).curveTo(315.486, 245.262, 315.202, 245.20099,
                314.76398, 245.20099);
        ((GeneralPath) shape).curveTo(314.32297, 245.20099, 314.03497,
                245.26898, 313.90497, 245.41399);
        ((GeneralPath) shape).curveTo(313.77396, 245.555, 313.70798, 245.86299,
                313.70798, 246.346);
        ((GeneralPath) shape).lineTo(315.822, 246.346);
        ((GeneralPath) shape).moveTo(315.822, 247.409);
        ((GeneralPath) shape).lineTo(316.382, 247.409);
        ((GeneralPath) shape).lineTo(316.386, 247.545);
        ((GeneralPath) shape).curveTo(316.386, 247.93199, 316.26898, 248.203,
                316.03198, 248.358);
        ((GeneralPath) shape).curveTo(315.801, 248.51201, 315.386, 248.59,
                314.797, 248.59);
        ((GeneralPath) shape).curveTo(314.109, 248.59, 313.658, 248.465,
                313.443, 248.213);
        ((GeneralPath) shape).curveTo(313.229, 247.963, 313.12198, 247.43399,
                313.12198, 246.629);
        ((GeneralPath) shape).curveTo(313.12198, 245.885, 313.22897, 245.385,
                313.44498, 245.129);
        ((GeneralPath) shape).curveTo(313.65997, 244.875, 314.08298, 244.746,
                314.71497, 244.746);
        ((GeneralPath) shape).curveTo(315.40198, 244.746, 315.84998, 244.853,
                316.06396, 245.078);
        ((GeneralPath) shape).curveTo(316.27698, 245.299, 316.38397, 245.766,
                316.38397, 246.475);
        ((GeneralPath) shape).lineTo(316.38397, 246.76701);
        ((GeneralPath) shape).lineTo(313.69897, 246.76701);
        ((GeneralPath) shape).curveTo(313.69897, 247.35501, 313.76196,
                247.73001, 313.88797, 247.89201);
        ((GeneralPath) shape).curveTo(314.01196, 248.05202, 314.30698,
                248.13402, 314.77097, 248.13402);
        ((GeneralPath) shape).curveTo(315.20895, 248.13402, 315.49396,
                248.09702, 315.62497, 248.01901);
        ((GeneralPath) shape).curveTo(315.75797, 247.94301, 315.82196,
                247.77701, 315.82196, 247.52101);
        ((GeneralPath) shape).lineTo(315.822, 247.409);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_282;
        g.setTransform(defaultTransform__0_282);
        g.setClip(clip__0_282);
        float alpha__0_283 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_283 = g.getClip();
        AffineTransform defaultTransform__0_283 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_283 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(317.383, 248.537);
        ((GeneralPath) shape).lineTo(317.383, 245.256);
        ((GeneralPath) shape).lineTo(316.814, 245.256);
        ((GeneralPath) shape).lineTo(316.814, 244.803);
        ((GeneralPath) shape).lineTo(317.383, 244.803);
        ((GeneralPath) shape).lineTo(317.383, 244.248);
        ((GeneralPath) shape).curveTo(317.383, 243.518, 317.757, 243.148,
                318.51, 243.148);
        ((GeneralPath) shape).curveTo(318.61902, 243.148, 318.752, 243.15599,
                318.90402, 243.17499);
        ((GeneralPath) shape).lineTo(318.90402, 243.62799);
        ((GeneralPath) shape).curveTo(318.72803, 243.60799, 318.60004, 243.601,
                318.51804, 243.601);
        ((GeneralPath) shape).curveTo(318.12305, 243.601, 317.92905, 243.798,
                317.92905, 244.194);
        ((GeneralPath) shape).lineTo(317.92905, 244.799);
        ((GeneralPath) shape).lineTo(318.90405, 244.799);
        ((GeneralPath) shape).lineTo(318.90405, 245.252);
        ((GeneralPath) shape).lineTo(317.92905, 245.252);
        ((GeneralPath) shape).lineTo(317.92905, 248.533);
        ((GeneralPath) shape).lineTo(317.38306, 248.533);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_283;
        g.setTransform(defaultTransform__0_283);
        g.setClip(clip__0_283);
        float alpha__0_284 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_284 = g.getClip();
        AffineTransform defaultTransform__0_284 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_284 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(321.329, 244.803);
        ((GeneralPath) shape).lineTo(321.329, 245.256);
        ((GeneralPath) shape).lineTo(319.893, 245.256);
        ((GeneralPath) shape).lineTo(319.893, 247.541);
        ((GeneralPath) shape).curveTo(319.893, 247.938, 320.069, 248.139,
                320.423, 248.139);
        ((GeneralPath) shape).curveTo(320.775, 248.139, 320.948, 247.96101,
                320.948, 247.604);
        ((GeneralPath) shape).lineTo(320.951, 247.42);
        ((GeneralPath) shape).lineTo(320.961, 247.213);
        ((GeneralPath) shape).lineTo(321.468, 247.213);
        ((GeneralPath) shape).lineTo(321.473, 247.48799);
        ((GeneralPath) shape).curveTo(321.473, 248.22198, 321.123, 248.592,
                320.427, 248.592);
        ((GeneralPath) shape).curveTo(319.708, 248.592, 319.348, 248.287,
                319.348, 247.67499);
        ((GeneralPath) shape).lineTo(319.348, 245.25699);
        ((GeneralPath) shape).lineTo(318.831, 245.25699);
        ((GeneralPath) shape).lineTo(318.831, 244.80399);
        ((GeneralPath) shape).lineTo(319.348, 244.80399);
        ((GeneralPath) shape).lineTo(319.348, 243.90599);
        ((GeneralPath) shape).lineTo(319.893, 243.90599);
        ((GeneralPath) shape).lineTo(319.893, 244.80399);
        ((GeneralPath) shape).lineTo(321.329, 244.803);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_284;
        g.setTransform(defaultTransform__0_284);
        g.setClip(clip__0_284);
        float alpha__0_285 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_285 = g.getClip();
        AffineTransform defaultTransform__0_285 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_285 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        stroke = new BasicStroke(1.0f, 0, 0, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(384.945, 249.118);
        ((GeneralPath) shape).curveTo(384.945, 249.744, 384.437, 250.25099,
                383.81, 250.25099);
        ((GeneralPath) shape).lineTo(378.043, 250.25099);
        ((GeneralPath) shape).curveTo(377.414, 250.25099, 376.906, 249.745,
                376.906, 249.118);
        ((GeneralPath) shape).lineTo(376.906, 243.289);
        ((GeneralPath) shape).curveTo(376.906, 242.664, 377.414, 242.153,
                378.043, 242.153);
        ((GeneralPath) shape).lineTo(383.81, 242.153);
        ((GeneralPath) shape).curveTo(384.437, 242.153, 384.945, 242.664,
                384.945, 243.289);
        ((GeneralPath) shape).lineTo(384.945, 249.118);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_285;
        g.setTransform(defaultTransform__0_285);
        g.setClip(clip__0_285);
        float alpha__0_286 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_286 = g.getClip();
        AffineTransform defaultTransform__0_286 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_286 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(352.506, 245.87);
        ((GeneralPath) shape).lineTo(354.239, 245.87);
        ((GeneralPath) shape).curveTo(354.695, 245.87, 355.009, 245.801,
                355.177, 245.661);
        ((GeneralPath) shape).curveTo(355.343, 245.51999, 355.427, 245.261,
                355.427, 244.874);
        ((GeneralPath) shape).curveTo(355.427, 244.394, 355.364, 244.079,
                355.239, 243.933);
        ((GeneralPath) shape).curveTo(355.11603, 243.788, 354.851, 243.715,
                354.445, 243.715);
        ((GeneralPath) shape).lineTo(352.506, 243.715);
        ((GeneralPath) shape).lineTo(352.506, 245.87);
        ((GeneralPath) shape).moveTo(351.903, 248.537);
        ((GeneralPath) shape).lineTo(351.903, 243.205);
        ((GeneralPath) shape).lineTo(354.437, 243.205);
        ((GeneralPath) shape).curveTo(355.00803, 243.205, 355.41302, 243.316,
                355.64603, 243.545);
        ((GeneralPath) shape).curveTo(355.88004, 243.772, 355.99704, 244.164,
                355.99704, 244.72499);
        ((GeneralPath) shape).curveTo(355.99704, 245.217, 355.93503, 245.55899,
                355.80905, 245.754);
        ((GeneralPath) shape).curveTo(355.68204, 245.946, 355.43805, 246.071,
                355.08005, 246.131);
        ((GeneralPath) shape).lineTo(355.08005, 246.14299);
        ((GeneralPath) shape).curveTo(355.64304, 246.18399, 355.92905, 246.527,
                355.92905, 247.174);
        ((GeneralPath) shape).lineTo(355.92905, 248.537);
        ((GeneralPath) shape).lineTo(355.32605, 248.537);
        ((GeneralPath) shape).lineTo(355.32605, 247.308);
        ((GeneralPath) shape).curveTo(355.32605, 246.687, 355.05606, 246.374,
                354.51804, 246.374);
        ((GeneralPath) shape).lineTo(352.50604, 246.374);
        ((GeneralPath) shape).lineTo(352.50604, 248.534);
        ((GeneralPath) shape).lineTo(351.903, 248.537);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_286;
        g.setTransform(defaultTransform__0_286);
        g.setClip(clip__0_286);
        float alpha__0_287 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_287 = g.getClip();
        AffineTransform defaultTransform__0_287 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_287 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(357.396, 244.803);
        ((GeneralPath) shape).lineTo(357.396, 248.53699);
        ((GeneralPath) shape).lineTo(356.85, 248.53699);
        ((GeneralPath) shape).lineTo(356.85, 244.803);
        ((GeneralPath) shape).lineTo(357.396, 244.803);
        ((GeneralPath) shape).moveTo(357.396, 243.205);
        ((GeneralPath) shape).lineTo(357.396, 243.816);
        ((GeneralPath) shape).lineTo(356.85, 243.816);
        ((GeneralPath) shape).lineTo(356.85, 243.205);
        ((GeneralPath) shape).lineTo(357.396, 243.205);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_287;
        g.setTransform(defaultTransform__0_287);
        g.setClip(clip__0_287);
        float alpha__0_288 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_288 = g.getClip();
        AffineTransform defaultTransform__0_288 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_288 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(359.844, 245.201);
        ((GeneralPath) shape).curveTo(359.413, 245.201, 359.139, 245.291,
                359.00998, 245.472);
        ((GeneralPath) shape).curveTo(358.88297, 245.652, 358.81897, 246.049,
                358.81897, 246.661);
        ((GeneralPath) shape).curveTo(358.81897, 247.276, 358.88196, 247.679,
                359.00998, 247.86);
        ((GeneralPath) shape).curveTo(359.137, 248.046, 359.413, 248.135,
                359.844, 248.135);
        ((GeneralPath) shape).curveTo(360.27798, 248.135, 360.564, 248.03499,
                360.69998, 247.832);
        ((GeneralPath) shape).curveTo(360.83798, 247.631, 360.90598, 247.211,
                360.90598, 246.57);
        ((GeneralPath) shape).curveTo(360.90598, 246.007, 360.83997, 245.638,
                360.69998, 245.46301);
        ((GeneralPath) shape).curveTo(360.565, 245.291, 360.279, 245.201,
                359.844, 245.201);
        ((GeneralPath) shape).moveTo(361.47, 244.803);
        ((GeneralPath) shape).lineTo(361.47, 248.78);
        ((GeneralPath) shape).curveTo(361.47, 249.321, 361.353, 249.694,
                361.117, 249.897);
        ((GeneralPath) shape).curveTo(360.885, 250.1, 360.454, 250.20401,
                359.83002, 250.20401);
        ((GeneralPath) shape).curveTo(359.27402, 250.20401, 358.894, 250.11601,
                358.691, 249.94402);
        ((GeneralPath) shape).curveTo(358.489, 249.77202, 358.38702, 249.45401,
                358.38702, 248.98001);
        ((GeneralPath) shape).lineTo(358.911, 248.98001);
        ((GeneralPath) shape).curveTo(358.911, 249.30002, 358.97202, 249.507,
                359.091, 249.60501);
        ((GeneralPath) shape).curveTo(359.208, 249.70201, 359.467, 249.751,
                359.866, 249.751);
        ((GeneralPath) shape).curveTo(360.28198, 249.751, 360.56, 249.688,
                360.707, 249.563);
        ((GeneralPath) shape).curveTo(360.849, 249.438, 360.921, 249.194,
                360.921, 248.83101);
        ((GeneralPath) shape).lineTo(360.921, 248.08101);
        ((GeneralPath) shape).lineTo(360.90698, 248.07701);
        ((GeneralPath) shape).curveTo(360.766, 248.423, 360.361, 248.59702,
                359.69098, 248.59702);
        ((GeneralPath) shape).curveTo(359.141, 248.59702, 358.75998, 248.46202,
                358.55197, 248.18901);
        ((GeneralPath) shape).curveTo(358.34497, 247.91801, 358.23898, 247.421,
                358.23898, 246.699);
        ((GeneralPath) shape).curveTo(358.23898, 245.95401, 358.344, 245.44101,
                358.55698, 245.168);
        ((GeneralPath) shape).curveTo(358.76697, 244.895, 359.16098, 244.756,
                359.73596, 244.756);
        ((GeneralPath) shape).curveTo(360.34595, 244.756, 360.74796, 244.944,
                360.93295, 245.319);
        ((GeneralPath) shape).lineTo(360.94495, 245.315);
        ((GeneralPath) shape).lineTo(360.91995, 244.811);
        ((GeneralPath) shape).lineTo(361.46796, 244.811);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_288;
        g.setTransform(defaultTransform__0_288);
        g.setClip(clip__0_288);
        float alpha__0_289 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_289 = g.getClip();
        AffineTransform defaultTransform__0_289 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_289 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(362.969, 243.205);
        ((GeneralPath) shape).lineTo(362.969, 245.264);
        ((GeneralPath) shape).lineTo(362.981, 245.272);
        ((GeneralPath) shape).curveTo(363.11798, 244.925, 363.503, 244.751,
                364.13498, 244.751);
        ((GeneralPath) shape).curveTo(365.07098, 244.751, 365.53598, 245.167,
                365.53598, 246.001);
        ((GeneralPath) shape).lineTo(365.53598, 248.54001);
        ((GeneralPath) shape).lineTo(364.99, 248.54001);
        ((GeneralPath) shape).lineTo(364.99, 246.048);
        ((GeneralPath) shape).curveTo(364.99, 245.485, 364.703, 245.20401,
                364.13098, 245.20401);
        ((GeneralPath) shape).curveTo(363.66797, 245.20401, 363.35898, 245.287,
                363.201, 245.45201);
        ((GeneralPath) shape).curveTo(363.047, 245.61401, 362.969, 245.94402,
                362.969, 246.436);
        ((GeneralPath) shape).lineTo(362.969, 248.54001);
        ((GeneralPath) shape).lineTo(362.423, 248.54001);
        ((GeneralPath) shape).lineTo(362.423, 243.20801);
        ((GeneralPath) shape).lineTo(362.969, 243.205);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_289;
        g.setTransform(defaultTransform__0_289);
        g.setClip(clip__0_289);
        float alpha__0_290 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_290 = g.getClip();
        AffineTransform defaultTransform__0_290 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_290 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(368.504, 244.803);
        ((GeneralPath) shape).lineTo(368.504, 245.256);
        ((GeneralPath) shape).lineTo(367.067, 245.256);
        ((GeneralPath) shape).lineTo(367.067, 247.541);
        ((GeneralPath) shape).curveTo(367.067, 247.938, 367.24197, 248.139,
                367.597, 248.139);
        ((GeneralPath) shape).curveTo(367.948, 248.139, 368.12097, 247.96101,
                368.12097, 247.604);
        ((GeneralPath) shape).lineTo(368.12698, 247.42);
        ((GeneralPath) shape).lineTo(368.13498, 247.213);
        ((GeneralPath) shape).lineTo(368.64197, 247.213);
        ((GeneralPath) shape).lineTo(368.64597, 247.48799);
        ((GeneralPath) shape).curveTo(368.64597, 248.22198, 368.29797, 248.592,
                367.60095, 248.592);
        ((GeneralPath) shape).curveTo(366.88095, 248.592, 366.52097, 248.287,
                366.52097, 247.67499);
        ((GeneralPath) shape).lineTo(366.52097, 245.25699);
        ((GeneralPath) shape).lineTo(366.00595, 245.25699);
        ((GeneralPath) shape).lineTo(366.00595, 244.80399);
        ((GeneralPath) shape).lineTo(366.52097, 244.80399);
        ((GeneralPath) shape).lineTo(366.52097, 243.90599);
        ((GeneralPath) shape).lineTo(367.06696, 243.90599);
        ((GeneralPath) shape).lineTo(367.06696, 244.80399);
        ((GeneralPath) shape).lineTo(368.504, 244.803);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_290;
        g.setTransform(defaultTransform__0_290);
        g.setClip(clip__0_290);
        float alpha__0_291 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_291 = g.getClip();
        AffineTransform defaultTransform__0_291 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_291 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        stroke = new BasicStroke(1.0f, 0, 0, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(342.892, 260.457);
        ((GeneralPath) shape).curveTo(342.892, 261.082, 342.384, 261.59,
                341.757, 261.59);
        ((GeneralPath) shape).lineTo(335.987, 261.59);
        ((GeneralPath) shape).curveTo(335.36, 261.59, 334.853, 261.082,
                334.853, 260.457);
        ((GeneralPath) shape).lineTo(334.853, 254.628);
        ((GeneralPath) shape).curveTo(334.853, 254.003, 335.36, 253.49501,
                335.987, 253.49501);
        ((GeneralPath) shape).lineTo(341.757, 253.49501);
        ((GeneralPath) shape).curveTo(342.384, 253.49501, 342.892, 254.003,
                342.892, 254.628);
        ((GeneralPath) shape).lineTo(342.892, 260.457);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_291;
        g.setTransform(defaultTransform__0_291);
        g.setClip(clip__0_291);
        float alpha__0_292 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_292 = g.getClip();
        AffineTransform defaultTransform__0_292 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_292 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(311.705, 255.104);
        ((GeneralPath) shape).lineTo(311.705, 259.877);
        ((GeneralPath) shape).lineTo(311.105, 259.877);
        ((GeneralPath) shape).lineTo(311.105, 255.104);
        ((GeneralPath) shape).lineTo(309.37, 255.104);
        ((GeneralPath) shape).lineTo(309.37, 254.545);
        ((GeneralPath) shape).lineTo(313.427, 254.545);
        ((GeneralPath) shape).lineTo(313.427, 255.104);
        ((GeneralPath) shape).lineTo(311.705, 255.104);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_292;
        g.setTransform(defaultTransform__0_292);
        g.setClip(clip__0_292);
        float alpha__0_293 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_293 = g.getClip();
        AffineTransform defaultTransform__0_293 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_293 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(316.974, 256.143);
        ((GeneralPath) shape).lineTo(316.974, 259.877);
        ((GeneralPath) shape).lineTo(316.428, 259.877);
        ((GeneralPath) shape).lineTo(316.467, 259.389);
        ((GeneralPath) shape).lineTo(316.453, 259.377);
        ((GeneralPath) shape).curveTo(316.265, 259.746, 315.858, 259.932,
                315.236, 259.932);
        ((GeneralPath) shape).curveTo(314.367, 259.932, 313.93, 259.49802,
                313.93, 258.627);
        ((GeneralPath) shape).lineTo(313.93, 256.143);
        ((GeneralPath) shape).lineTo(314.47598, 256.143);
        ((GeneralPath) shape).lineTo(314.47598, 258.627);
        ((GeneralPath) shape).curveTo(314.47598, 258.96402, 314.53098, 259.19,
                314.64297, 259.307);
        ((GeneralPath) shape).curveTo(314.75397, 259.42, 314.97498, 259.479,
                315.30197, 259.479);
        ((GeneralPath) shape).curveTo(315.73096, 259.479, 316.02597, 259.395,
                316.18497, 259.223);
        ((GeneralPath) shape).curveTo(316.34497, 259.054, 316.42496, 258.741,
                316.42496, 258.285);
        ((GeneralPath) shape).lineTo(316.42496, 256.142);
        ((GeneralPath) shape).lineTo(316.974, 256.142);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_293;
        g.setTransform(defaultTransform__0_293);
        g.setClip(clip__0_293);
        float alpha__0_294 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_294 = g.getClip();
        AffineTransform defaultTransform__0_294 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_294 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(318.033, 256.143);
        ((GeneralPath) shape).lineTo(318.57898, 256.143);
        ((GeneralPath) shape).lineTo(318.524, 256.573);
        ((GeneralPath) shape).lineTo(318.53598, 256.587);
        ((GeneralPath) shape).curveTo(318.74997, 256.233, 319.106, 256.06,
                319.60297, 256.06);
        ((GeneralPath) shape).curveTo(320.28998, 256.06, 320.63196, 256.414,
                320.63196, 257.123);
        ((GeneralPath) shape).lineTo(320.62897, 257.38098);
        ((GeneralPath) shape).lineTo(320.08997, 257.38098);
        ((GeneralPath) shape).lineTo(320.10297, 257.287);
        ((GeneralPath) shape).curveTo(320.11096, 257.18698, 320.11496, 257.123,
                320.11496, 257.08798);
        ((GeneralPath) shape).curveTo(320.11496, 256.705, 319.90796, 256.51398,
                319.49097, 256.51398);
        ((GeneralPath) shape).curveTo(318.88498, 256.51398, 318.57898,
                256.88898, 318.57898, 257.64297);
        ((GeneralPath) shape).lineTo(318.57898, 259.88098);
        ((GeneralPath) shape).lineTo(318.033, 259.88098);
        ((GeneralPath) shape).lineTo(318.033, 256.143);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_294;
        g.setTransform(defaultTransform__0_294);
        g.setClip(clip__0_294);
        float alpha__0_295 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_295 = g.getClip();
        AffineTransform defaultTransform__0_295 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_295 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(321.21, 256.143);
        ((GeneralPath) shape).lineTo(321.75598, 256.143);
        ((GeneralPath) shape).lineTo(321.69897, 256.573);
        ((GeneralPath) shape).lineTo(321.71298, 256.587);
        ((GeneralPath) shape).curveTo(321.92798, 256.233, 322.28198, 256.06,
                322.779, 256.06);
        ((GeneralPath) shape).curveTo(323.46698, 256.06, 323.80798, 256.414,
                323.80798, 257.123);
        ((GeneralPath) shape).lineTo(323.804, 257.38098);
        ((GeneralPath) shape).lineTo(323.266, 257.38098);
        ((GeneralPath) shape).lineTo(323.27798, 257.287);
        ((GeneralPath) shape).curveTo(323.28598, 257.18698, 323.28998, 257.123,
                323.28998, 257.08798);
        ((GeneralPath) shape).curveTo(323.28998, 256.705, 323.08298, 256.51398,
                322.666, 256.51398);
        ((GeneralPath) shape).curveTo(322.06097, 256.51398, 321.75598,
                256.88898, 321.75598, 257.64297);
        ((GeneralPath) shape).lineTo(321.75598, 259.88098);
        ((GeneralPath) shape).lineTo(321.20898, 259.88098);
        ((GeneralPath) shape).lineTo(321.21, 256.143);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_295;
        g.setTransform(defaultTransform__0_295);
        g.setClip(clip__0_295);
        float alpha__0_296 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_296 = g.getClip();
        AffineTransform defaultTransform__0_296 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_296 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(326.924, 257.686);
        ((GeneralPath) shape).lineTo(326.92, 257.51102);
        ((GeneralPath) shape).curveTo(326.92, 257.109, 326.854, 256.84503,
                326.721, 256.72302);
        ((GeneralPath) shape).curveTo(326.58902, 256.60202, 326.30402,
                256.54202, 325.864, 256.54202);
        ((GeneralPath) shape).curveTo(325.42603, 256.54202, 325.13702,
                256.61002, 325.00702, 256.75403);
        ((GeneralPath) shape).curveTo(324.87402, 256.89502, 324.81003,
                257.20602, 324.81003, 257.68604);
        ((GeneralPath) shape).lineTo(326.924, 257.68604);
        ((GeneralPath) shape).moveTo(326.924, 258.749);
        ((GeneralPath) shape).lineTo(327.48203, 258.749);
        ((GeneralPath) shape).lineTo(327.48602, 258.886);
        ((GeneralPath) shape).curveTo(327.48602, 259.27197, 327.36902,
                259.54398, 327.13303, 259.69897);
        ((GeneralPath) shape).curveTo(326.89902, 259.85397, 326.48602,
                259.93198, 325.89502, 259.93198);
        ((GeneralPath) shape).curveTo(325.20703, 259.93198, 324.75803,
                259.80698, 324.544, 259.55496);
        ((GeneralPath) shape).curveTo(324.327, 259.30496, 324.223, 258.77597,
                324.223, 257.97095);
        ((GeneralPath) shape).curveTo(324.223, 257.22696, 324.327, 256.72696,
                324.546, 256.47095);
        ((GeneralPath) shape).curveTo(324.75998, 256.21695, 325.184, 256.08795,
                325.813, 256.08795);
        ((GeneralPath) shape).curveTo(326.5, 256.08795, 326.948, 256.19495,
                327.163, 256.41995);
        ((GeneralPath) shape).curveTo(327.375, 256.64096, 327.482, 257.10794,
                327.482, 257.81696);
        ((GeneralPath) shape).lineTo(327.482, 258.10995);
        ((GeneralPath) shape).lineTo(324.79898, 258.10995);
        ((GeneralPath) shape).curveTo(324.79898, 258.69696, 324.86197,
                259.07196, 324.98697, 259.23495);
        ((GeneralPath) shape).curveTo(325.11197, 259.39496, 325.40598,
                259.47696, 325.86996, 259.47696);
        ((GeneralPath) shape).curveTo(326.30795, 259.47696, 326.59598,
                259.43896, 326.72595, 259.36096);
        ((GeneralPath) shape).curveTo(326.85596, 259.28696, 326.92197,
                259.12097, 326.92197, 258.86298);
        ((GeneralPath) shape).lineTo(326.924, 258.749);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_296;
        g.setTransform(defaultTransform__0_296);
        g.setClip(clip__0_296);
        float alpha__0_297 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_297 = g.getClip();
        AffineTransform defaultTransform__0_297 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_297 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(330.312, 256.143);
        ((GeneralPath) shape).lineTo(330.312, 256.596);
        ((GeneralPath) shape).lineTo(328.877, 256.596);
        ((GeneralPath) shape).lineTo(328.877, 258.881);
        ((GeneralPath) shape).curveTo(328.877, 259.278, 329.053, 259.479,
                329.40503, 259.479);
        ((GeneralPath) shape).curveTo(329.75705, 259.479, 329.93103, 259.301,
                329.93103, 258.944);
        ((GeneralPath) shape).lineTo(329.93503, 258.76);
        ((GeneralPath) shape).lineTo(329.94302, 258.553);
        ((GeneralPath) shape).lineTo(330.45, 258.553);
        ((GeneralPath) shape).lineTo(330.454, 258.83002);
        ((GeneralPath) shape).curveTo(330.454, 259.562, 330.10703, 259.932,
                329.409, 259.932);
        ((GeneralPath) shape).curveTo(328.689, 259.932, 328.329, 259.627,
                328.329, 259.014);
        ((GeneralPath) shape).lineTo(328.329, 256.596);
        ((GeneralPath) shape).lineTo(327.814, 256.596);
        ((GeneralPath) shape).lineTo(327.814, 256.143);
        ((GeneralPath) shape).lineTo(328.329, 256.143);
        ((GeneralPath) shape).lineTo(328.329, 255.24701);
        ((GeneralPath) shape).lineTo(328.877, 255.24701);
        ((GeneralPath) shape).lineTo(328.877, 256.143);
        ((GeneralPath) shape).lineTo(330.312, 256.143);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_297;
        g.setTransform(defaultTransform__0_297);
        g.setClip(clip__0_297);
        float alpha__0_298 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_298 = g.getClip();
        AffineTransform defaultTransform__0_298 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_298 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(260.591, 212.639);
        ((GeneralPath) shape).lineTo(259.99402, 212.639);
        ((GeneralPath) shape).curveTo(259.99402, 212.20901, 259.924, 211.938,
                259.78003, 211.82101);
        ((GeneralPath) shape).curveTo(259.63803, 211.70801, 259.30103,
                211.64702, 258.77002, 211.64702);
        ((GeneralPath) shape).curveTo(258.14203, 211.64702, 257.73703,
                211.70403, 257.55402, 211.81302);
        ((GeneralPath) shape).curveTo(257.37003, 211.92201, 257.281, 212.17001,
                257.281, 212.55302);
        ((GeneralPath) shape).curveTo(257.281, 212.98302, 257.351, 213.24402,
                257.496, 213.33803);
        ((GeneralPath) shape).curveTo(257.64, 213.43202, 258.063, 213.49403,
                258.771, 213.52702);
        ((GeneralPath) shape).curveTo(259.6, 213.56203, 260.125, 213.67003,
                260.352, 213.84302);
        ((GeneralPath) shape).curveTo(260.576, 214.01701, 260.689, 214.40701,
                260.689, 215.01501);
        ((GeneralPath) shape).curveTo(260.689, 215.67102, 260.56, 216.09502,
                260.29898, 216.28801);
        ((GeneralPath) shape).curveTo(260.042, 216.477, 259.472, 216.57701,
                258.58597, 216.57701);
        ((GeneralPath) shape).curveTo(257.81998, 216.57701, 257.31097, 216.479,
                257.05795, 216.28601);
        ((GeneralPath) shape).curveTo(256.80396, 216.09302, 256.67795,
                215.70201, 256.67795, 215.11002);
        ((GeneralPath) shape).lineTo(256.67194, 214.87402);
        ((GeneralPath) shape).lineTo(257.27094, 214.87402);
        ((GeneralPath) shape).lineTo(257.27094, 215.00702);
        ((GeneralPath) shape).curveTo(257.27094, 215.48402, 257.34094,
                215.77802, 257.48892, 215.89603);
        ((GeneralPath) shape).curveTo(257.63293, 216.01103, 258.00693,
                216.07002, 258.6079, 216.07002);
        ((GeneralPath) shape).curveTo(259.2959, 216.07002, 259.71692,
                216.01302, 259.8789, 215.89603);
        ((GeneralPath) shape).curveTo(260.0369, 215.78302, 260.1169, 215.47203,
                260.1169, 214.96803);
        ((GeneralPath) shape).curveTo(260.1169, 214.64403, 260.0639, 214.42703,
                259.95493, 214.31804);
        ((GeneralPath) shape).curveTo(259.8499, 214.21304, 259.62192,
                214.14604, 259.27893, 214.12704);
        ((GeneralPath) shape).lineTo(258.65494, 214.09604);
        ((GeneralPath) shape).lineTo(258.06195, 214.06503);
        ((GeneralPath) shape).curveTo(257.16196, 214.00203, 256.70795,
                213.53603, 256.70795, 212.66103);
        ((GeneralPath) shape).curveTo(256.70795, 212.05603, 256.83896,
                211.64702, 257.10394, 211.44403);
        ((GeneralPath) shape).curveTo(257.36493, 211.24303, 257.88995,
                211.13904, 258.67294, 211.13904);
        ((GeneralPath) shape).curveTo(259.46893, 211.13904, 259.98694,
                211.23303, 260.23096, 211.42204);
        ((GeneralPath) shape).curveTo(260.47, 211.61, 260.591, 212.016,
                260.591, 212.639);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_298;
        g.setTransform(defaultTransform__0_298);
        g.setClip(clip__0_298);
        float alpha__0_299 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_299 = g.getClip();
        AffineTransform defaultTransform__0_299 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_299 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(263.979, 214.334);
        ((GeneralPath) shape).lineTo(263.975, 214.158);
        ((GeneralPath) shape).curveTo(263.975, 213.75601, 263.909, 213.492,
                263.776, 213.371);
        ((GeneralPath) shape).curveTo(263.644, 213.25, 263.359, 213.187,
                262.918, 213.187);
        ((GeneralPath) shape).curveTo(262.48, 213.187, 262.191, 213.257,
                262.061, 213.4);
        ((GeneralPath) shape).curveTo(261.931, 213.54298, 261.864, 213.851,
                261.864, 214.334);
        ((GeneralPath) shape).lineTo(263.979, 214.334);
        ((GeneralPath) shape).moveTo(263.979, 215.397);
        ((GeneralPath) shape).lineTo(264.53702, 215.397);
        ((GeneralPath) shape).lineTo(264.54102, 215.53601);
        ((GeneralPath) shape).curveTo(264.54102, 215.923, 264.424, 216.19202,
                264.18802, 216.34901);
        ((GeneralPath) shape).curveTo(263.954, 216.50302, 263.54102, 216.58101,
                262.95, 216.58101);
        ((GeneralPath) shape).curveTo(262.264, 216.58101, 261.81403, 216.45601,
                261.599, 216.20401);
        ((GeneralPath) shape).curveTo(261.382, 215.95401, 261.277, 215.425,
                261.277, 214.62001);
        ((GeneralPath) shape).curveTo(261.277, 213.876, 261.38202, 213.376,
                261.601, 213.12001);
        ((GeneralPath) shape).curveTo(261.815, 212.86401, 262.238, 212.73502,
                262.868, 212.73502);
        ((GeneralPath) shape).curveTo(263.556, 212.73502, 264.00302, 212.84401,
                264.22, 213.06702);
        ((GeneralPath) shape).curveTo(264.432, 213.29002, 264.537, 213.75502,
                264.537, 214.46501);
        ((GeneralPath) shape).lineTo(264.537, 214.75801);
        ((GeneralPath) shape).lineTo(261.85397, 214.75801);
        ((GeneralPath) shape).curveTo(261.85397, 215.34601, 261.91696,
                215.72101, 262.04196, 215.88301);
        ((GeneralPath) shape).curveTo(262.16696, 216.04301, 262.46097,
                216.12502, 262.92496, 216.12502);
        ((GeneralPath) shape).curveTo(263.36395, 216.12502, 263.65097,
                216.08801, 263.78094, 216.01001);
        ((GeneralPath) shape).curveTo(263.91196, 215.934, 263.97794, 215.768,
                263.97794, 215.51201);
        ((GeneralPath) shape).lineTo(263.979, 215.397);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_299;
        g.setTransform(defaultTransform__0_299);
        g.setClip(clip__0_299);
        float alpha__0_300 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_300 = g.getClip();
        AffineTransform defaultTransform__0_300 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_300 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(265.396, 212.792);
        ((GeneralPath) shape).lineTo(265.91998, 212.792);
        ((GeneralPath) shape).lineTo(265.904, 213.3);
        ((GeneralPath) shape).lineTo(265.91998, 213.312);
        ((GeneralPath) shape).curveTo(266.08597, 212.929, 266.499, 212.73799,
                267.15997, 212.73799);
        ((GeneralPath) shape).curveTo(267.692, 212.73799, 268.05496, 212.83398,
                268.24197, 213.019);
        ((GeneralPath) shape).curveTo(268.42996, 213.207, 268.52496, 213.566,
                268.52496, 214.097);
        ((GeneralPath) shape).lineTo(268.52496, 216.527);
        ((GeneralPath) shape).lineTo(267.97897, 216.527);
        ((GeneralPath) shape).lineTo(267.97897, 214.006);
        ((GeneralPath) shape).curveTo(267.97897, 213.68599, 267.916, 213.471,
                267.79398, 213.36);
        ((GeneralPath) shape).curveTo(267.67297, 213.253, 267.43896, 213.194,
                267.08798, 213.194);
        ((GeneralPath) shape).curveTo(266.322, 213.194, 265.93997, 213.557,
                265.93997, 214.284);
        ((GeneralPath) shape).lineTo(265.93997, 216.53);
        ((GeneralPath) shape).lineTo(265.39496, 216.53);
        ((GeneralPath) shape).lineTo(265.39496, 212.792);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_300;
        g.setTransform(defaultTransform__0_300);
        g.setClip(clip__0_300);
        float alpha__0_301 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_301 = g.getClip();
        AffineTransform defaultTransform__0_301 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_301 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(272.406, 213.768);
        ((GeneralPath) shape).lineTo(271.861, 213.768);
        ((GeneralPath) shape).curveTo(271.861, 213.50801, 271.80798, 213.34601,
                271.703, 213.28601);
        ((GeneralPath) shape).curveTo(271.599, 213.223, 271.327, 213.19002,
                270.89, 213.19002);
        ((GeneralPath) shape).curveTo(270.484, 213.19002, 270.22702, 213.22302,
                270.118, 213.29202);
        ((GeneralPath) shape).curveTo(270.00803, 213.35602, 269.954, 213.51703,
                269.954, 213.77103);
        ((GeneralPath) shape).curveTo(269.954, 214.15402, 270.139, 214.35303,
                270.504, 214.37503);
        ((GeneralPath) shape).lineTo(270.944, 214.39603);
        ((GeneralPath) shape).lineTo(271.502, 214.42302);
        ((GeneralPath) shape).curveTo(272.178, 214.45602, 272.518, 214.81001,
                272.518, 215.48602);
        ((GeneralPath) shape).curveTo(272.518, 215.90402, 272.407, 216.19302,
                272.183, 216.34903);
        ((GeneralPath) shape).curveTo(271.959, 216.50504, 271.55103, 216.58302,
                270.954, 216.58302);
        ((GeneralPath) shape).curveTo(270.346, 216.58302, 269.923, 216.50902,
                269.69302, 216.35802);
        ((GeneralPath) shape).curveTo(269.463, 216.21101, 269.34802, 215.93802,
                269.34802, 215.54402);
        ((GeneralPath) shape).lineTo(269.35202, 215.34102);
        ((GeneralPath) shape).lineTo(269.915, 215.34102);
        ((GeneralPath) shape).lineTo(269.919, 215.51701);
        ((GeneralPath) shape).curveTo(269.919, 215.76102, 269.982, 215.92502,
                270.107, 216.00702);
        ((GeneralPath) shape).curveTo(270.232, 216.08902, 270.478, 216.13002,
                270.844, 216.13002);
        ((GeneralPath) shape).curveTo(271.291, 216.13002, 271.587, 216.08702,
                271.731, 216.00102);
        ((GeneralPath) shape).curveTo(271.871, 215.91502, 271.944, 215.73302,
                271.944, 215.46202);
        ((GeneralPath) shape).curveTo(271.944, 215.06702, 271.766, 214.87202,
                271.409, 214.87202);
        ((GeneralPath) shape).curveTo(270.581, 214.87202, 270.035, 214.80202,
                269.774, 214.66103);
        ((GeneralPath) shape).curveTo(269.513, 214.51802, 269.37997, 214.22702,
                269.37997, 213.78603);
        ((GeneralPath) shape).curveTo(269.37997, 213.36803, 269.48398,
                213.08902, 269.692, 212.94603);
        ((GeneralPath) shape).curveTo(269.899, 212.80702, 270.31, 212.73703,
                270.926, 212.73703);
        ((GeneralPath) shape).curveTo(271.91, 212.73703, 272.405, 213.03603,
                272.405, 213.63303);
        ((GeneralPath) shape).lineTo(272.405, 213.768);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_301;
        g.setTransform(defaultTransform__0_301);
        g.setClip(clip__0_301);
        float alpha__0_302 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_302 = g.getClip();
        AffineTransform defaultTransform__0_302 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_302 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(274.843, 213.19);
        ((GeneralPath) shape).curveTo(274.361, 213.19, 274.063, 213.268,
                273.94598, 213.426);
        ((GeneralPath) shape).curveTo(273.831, 213.582, 273.77197, 213.99399,
                273.77197, 214.65599);
        ((GeneralPath) shape).curveTo(273.77197, 215.31999, 273.82898, 215.73,
                273.94598, 215.88799);
        ((GeneralPath) shape).curveTo(274.06097, 216.04399, 274.361, 216.12398,
                274.843, 216.12398);
        ((GeneralPath) shape).curveTo(275.32797, 216.12398, 275.629, 216.04597,
                275.745, 215.88799);
        ((GeneralPath) shape).curveTo(275.86, 215.73198, 275.918, 215.31999,
                275.918, 214.65599);
        ((GeneralPath) shape).curveTo(275.918, 213.99399, 275.862, 213.58398,
                275.745, 213.426);
        ((GeneralPath) shape).curveTo(275.63, 213.27, 275.33, 213.19, 274.843,
                213.19);
        ((GeneralPath) shape).moveTo(274.843, 212.737);
        ((GeneralPath) shape).curveTo(275.53098, 212.737, 275.97897, 212.856,
                276.18597, 213.096);
        ((GeneralPath) shape).curveTo(276.39096, 213.334, 276.494, 213.85599,
                276.494, 214.659);
        ((GeneralPath) shape).curveTo(276.494, 215.45999, 276.392, 215.979,
                276.18597, 216.222);
        ((GeneralPath) shape).curveTo(275.981, 216.46, 275.53497, 216.581,
                274.843, 216.581);
        ((GeneralPath) shape).curveTo(274.15598, 216.581, 273.71198, 216.46199,
                273.50497, 216.222);
        ((GeneralPath) shape).curveTo(273.3, 215.984, 273.19696, 215.462,
                273.19696, 214.659);
        ((GeneralPath) shape).curveTo(273.19696, 213.85599, 273.29895,
                213.33699, 273.50497, 213.096);
        ((GeneralPath) shape).curveTo(273.71, 212.858, 274.156, 212.737,
                274.843, 212.737);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_302;
        g.setTransform(defaultTransform__0_302);
        g.setClip(clip__0_302);
        float alpha__0_303 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_303 = g.getClip();
        AffineTransform defaultTransform__0_303 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_303 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(277.398, 212.792);
        ((GeneralPath) shape).lineTo(277.944, 212.792);
        ((GeneralPath) shape).lineTo(277.889, 213.222);
        ((GeneralPath) shape).lineTo(277.901, 213.234);
        ((GeneralPath) shape).curveTo(278.115, 212.88199, 278.47202, 212.709,
                278.969, 212.709);
        ((GeneralPath) shape).curveTo(279.656, 212.709, 279.996, 213.063,
                279.996, 213.772);
        ((GeneralPath) shape).lineTo(279.993, 214.03);
        ((GeneralPath) shape).lineTo(279.454, 214.03);
        ((GeneralPath) shape).lineTo(279.466, 213.936);
        ((GeneralPath) shape).curveTo(279.476, 213.838, 279.48, 213.772,
                279.48, 213.737);
        ((GeneralPath) shape).curveTo(279.48, 213.354, 279.273, 213.165,
                278.85602, 213.165);
        ((GeneralPath) shape).curveTo(278.24802, 213.165, 277.94403, 213.54,
                277.94403, 214.29399);
        ((GeneralPath) shape).lineTo(277.94403, 216.53);
        ((GeneralPath) shape).lineTo(277.39804, 216.53);
        ((GeneralPath) shape).lineTo(277.39804, 212.792);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_303;
        g.setTransform(defaultTransform__0_303);
        g.setClip(clip__0_303);
        float alpha__0_304 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_304 = g.getClip();
        AffineTransform defaultTransform__0_304 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_304 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(286.858, 211.194);
        ((GeneralPath) shape).lineTo(286.858, 216.526);
        ((GeneralPath) shape).lineTo(286.257, 216.526);
        ((GeneralPath) shape).lineTo(286.257, 214.053);
        ((GeneralPath) shape).lineTo(283.146, 214.053);
        ((GeneralPath) shape).lineTo(283.146, 216.526);
        ((GeneralPath) shape).lineTo(282.546, 216.526);
        ((GeneralPath) shape).lineTo(282.546, 211.194);
        ((GeneralPath) shape).lineTo(283.146, 211.194);
        ((GeneralPath) shape).lineTo(283.146, 213.545);
        ((GeneralPath) shape).lineTo(286.257, 213.545);
        ((GeneralPath) shape).lineTo(286.257, 211.194);
        ((GeneralPath) shape).lineTo(286.858, 211.194);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_304;
        g.setTransform(defaultTransform__0_304);
        g.setClip(clip__0_304);
        float alpha__0_305 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_305 = g.getClip();
        AffineTransform defaultTransform__0_305 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_305 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(288.391, 212.792);
        ((GeneralPath) shape).lineTo(288.391, 216.524);
        ((GeneralPath) shape).lineTo(287.845, 216.524);
        ((GeneralPath) shape).lineTo(287.845, 212.792);
        ((GeneralPath) shape).lineTo(288.391, 212.792);
        ((GeneralPath) shape).moveTo(288.391, 211.194);
        ((GeneralPath) shape).lineTo(288.391, 211.807);
        ((GeneralPath) shape).lineTo(287.845, 211.807);
        ((GeneralPath) shape).lineTo(287.845, 211.194);
        ((GeneralPath) shape).lineTo(288.391, 211.194);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_305;
        g.setTransform(defaultTransform__0_305);
        g.setClip(clip__0_305);
        float alpha__0_306 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_306 = g.getClip();
        AffineTransform defaultTransform__0_306 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_306 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(291.323, 212.792);
        ((GeneralPath) shape).lineTo(291.323, 213.24501);
        ((GeneralPath) shape).lineTo(289.888, 213.24501);
        ((GeneralPath) shape).lineTo(289.888, 215.52802);
        ((GeneralPath) shape).curveTo(289.888, 215.92601, 290.063, 216.12802,
                290.41602, 216.12802);
        ((GeneralPath) shape).curveTo(290.769, 216.12802, 290.94202, 215.95003,
                290.94202, 215.59302);
        ((GeneralPath) shape).lineTo(290.946, 215.40901);
        ((GeneralPath) shape).lineTo(290.954, 215.20201);
        ((GeneralPath) shape).lineTo(291.461, 215.20201);
        ((GeneralPath) shape).lineTo(291.465, 215.477);
        ((GeneralPath) shape).curveTo(291.465, 216.211, 291.118, 216.58101,
                290.41998, 216.58101);
        ((GeneralPath) shape).curveTo(289.701, 216.58101, 289.34, 216.274,
                289.34, 215.66301);
        ((GeneralPath) shape).lineTo(289.34, 213.24501);
        ((GeneralPath) shape).lineTo(288.82498, 213.24501);
        ((GeneralPath) shape).lineTo(288.82498, 212.792);
        ((GeneralPath) shape).lineTo(289.34, 212.792);
        ((GeneralPath) shape).lineTo(289.34, 211.89401);
        ((GeneralPath) shape).lineTo(289.888, 211.89401);
        ((GeneralPath) shape).lineTo(289.888, 212.792);
        ((GeneralPath) shape).lineTo(291.323, 212.792);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_306;
        g.setTransform(defaultTransform__0_306);
        g.setClip(clip__0_306);
        float alpha__0_307 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_307 = g.getClip();
        AffineTransform defaultTransform__0_307 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_307 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(294.84, 213.768);
        ((GeneralPath) shape).lineTo(294.294, 213.768);
        ((GeneralPath) shape).curveTo(294.294, 213.50801, 294.241, 213.34601,
                294.138, 213.28601);
        ((GeneralPath) shape).curveTo(294.033, 213.223, 293.762, 213.19002,
                293.325, 213.19002);
        ((GeneralPath) shape).curveTo(292.919, 213.19002, 292.66202, 213.22302,
                292.55402, 213.29202);
        ((GeneralPath) shape).curveTo(292.44403, 213.35602, 292.39, 213.51703,
                292.39, 213.77103);
        ((GeneralPath) shape).curveTo(292.39, 214.15402, 292.57303, 214.35303,
                292.94, 214.37503);
        ((GeneralPath) shape).lineTo(293.379, 214.39603);
        ((GeneralPath) shape).lineTo(293.938, 214.42302);
        ((GeneralPath) shape).curveTo(294.612, 214.45602, 294.952, 214.81001,
                294.952, 215.48602);
        ((GeneralPath) shape).curveTo(294.952, 215.90402, 294.841, 216.19302,
                294.617, 216.34903);
        ((GeneralPath) shape).curveTo(294.39502, 216.50504, 293.986, 216.58302,
                293.389, 216.58302);
        ((GeneralPath) shape).curveTo(292.77902, 216.58302, 292.358, 216.50902,
                292.126, 216.35802);
        ((GeneralPath) shape).curveTo(291.897, 216.21101, 291.781, 215.93802,
                291.781, 215.54402);
        ((GeneralPath) shape).lineTo(291.785, 215.34102);
        ((GeneralPath) shape).lineTo(292.35, 215.34102);
        ((GeneralPath) shape).lineTo(292.354, 215.51701);
        ((GeneralPath) shape).curveTo(292.354, 215.76102, 292.417, 215.92502,
                292.54102, 216.00702);
        ((GeneralPath) shape).curveTo(292.66602, 216.08902, 292.91202,
                216.13002, 293.278, 216.13002);
        ((GeneralPath) shape).curveTo(293.72702, 216.13002, 294.023, 216.08702,
                294.165, 216.00102);
        ((GeneralPath) shape).curveTo(294.307, 215.91502, 294.37802, 215.73302,
                294.37802, 215.46202);
        ((GeneralPath) shape).curveTo(294.37802, 215.06702, 294.2, 214.87202,
                293.84503, 214.87202);
        ((GeneralPath) shape).curveTo(293.01703, 214.87202, 292.47104,
                214.80202, 292.20804, 214.66103);
        ((GeneralPath) shape).curveTo(291.94705, 214.51802, 291.81604,
                214.22702, 291.81604, 213.78603);
        ((GeneralPath) shape).curveTo(291.81604, 213.36803, 291.92004,
                213.08902, 292.12604, 212.94603);
        ((GeneralPath) shape).curveTo(292.33304, 212.80702, 292.74405,
                212.73703, 293.36005, 212.73703);
        ((GeneralPath) shape).curveTo(294.34604, 212.73703, 294.84204,
                213.03603, 294.84204, 213.63303);
        ((GeneralPath) shape).lineTo(294.84204, 213.76903);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_307;
        g.setTransform(defaultTransform__0_307);
        g.setClip(clip__0_307);
        float alpha__0_308 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_308 = g.getClip();
        AffineTransform defaultTransform__0_308 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_308 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(262.782, 222.121);
        ((GeneralPath) shape).lineTo(262.782, 227.453);
        ((GeneralPath) shape).lineTo(262.181, 227.453);
        ((GeneralPath) shape).lineTo(262.181, 222.867);
        ((GeneralPath) shape).lineTo(262.185, 222.707);
        ((GeneralPath) shape).lineTo(262.188, 222.547);
        ((GeneralPath) shape).lineTo(262.171, 222.547);
        ((GeneralPath) shape).lineTo(262.124, 222.672);
        ((GeneralPath) shape).curveTo(262.103, 222.735, 262.085, 222.774,
                262.077, 222.793);
        ((GeneralPath) shape).lineTo(261.975, 223.043);
        ((GeneralPath) shape).lineTo(260.16202, 227.453);
        ((GeneralPath) shape).lineTo(259.562, 227.453);
        ((GeneralPath) shape).lineTo(257.74503, 223.09401);
        ((GeneralPath) shape).lineTo(257.64102, 222.848);
        ((GeneralPath) shape).lineTo(257.59402, 222.723);
        ((GeneralPath) shape).curveTo(257.58102, 222.69601, 257.56702,
                222.65501, 257.54602, 222.602);
        ((GeneralPath) shape).lineTo(257.53003, 222.602);
        ((GeneralPath) shape).lineTo(257.53403, 222.748);
        ((GeneralPath) shape).lineTo(257.53802, 222.895);
        ((GeneralPath) shape).lineTo(257.53802, 227.455);
        ((GeneralPath) shape).lineTo(256.937, 227.455);
        ((GeneralPath) shape).lineTo(256.937, 222.123);
        ((GeneralPath) shape).lineTo(257.978, 222.123);
        ((GeneralPath) shape).lineTo(259.397, 225.57);
        ((GeneralPath) shape).lineTo(259.62302, 226.13101);
        ((GeneralPath) shape).lineTo(259.73602, 226.406);
        ((GeneralPath) shape).lineTo(259.84503, 226.684);
        ((GeneralPath) shape).lineTo(259.86102, 226.684);
        ((GeneralPath) shape).lineTo(259.97003, 226.406);
        ((GeneralPath) shape).curveTo(260.02502, 226.27301, 260.06003, 226.181,
                260.07703, 226.13101);
        ((GeneralPath) shape).lineTo(260.30704, 225.57602);
        ((GeneralPath) shape).lineTo(261.71902, 222.12202);
        ((GeneralPath) shape).lineTo(262.782, 222.121);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_308;
        g.setTransform(defaultTransform__0_308);
        g.setClip(clip__0_308);
        float alpha__0_309 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_309 = g.getClip();
        AffineTransform defaultTransform__0_309 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_309 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(265.306, 224.117);
        ((GeneralPath) shape).curveTo(264.823, 224.117, 264.525, 224.19301,
                264.408, 224.351);
        ((GeneralPath) shape).curveTo(264.293, 224.507, 264.23398, 224.91899,
                264.23398, 225.583);
        ((GeneralPath) shape).curveTo(264.23398, 226.247, 264.291, 226.65799,
                264.408, 226.814);
        ((GeneralPath) shape).curveTo(264.52298, 226.97, 264.823, 227.04999,
                265.306, 227.04999);
        ((GeneralPath) shape).curveTo(265.789, 227.04999, 266.09, 226.97198,
                266.207, 226.814);
        ((GeneralPath) shape).curveTo(266.322, 226.65999, 266.38, 226.247,
                266.38, 225.583);
        ((GeneralPath) shape).curveTo(266.38, 224.91899, 266.324, 224.50899,
                266.207, 224.351);
        ((GeneralPath) shape).curveTo(266.094, 224.197, 265.794, 224.117,
                265.306, 224.117);
        ((GeneralPath) shape).moveTo(265.306, 223.664);
        ((GeneralPath) shape).curveTo(265.994, 223.664, 266.441, 223.783,
                266.647, 224.021);
        ((GeneralPath) shape).curveTo(266.852, 224.259, 266.95502, 224.78099,
                266.95502, 225.584);
        ((GeneralPath) shape).curveTo(266.95502, 226.385, 266.85303, 226.905,
                266.647, 227.147);
        ((GeneralPath) shape).curveTo(266.44202, 227.38501, 265.996, 227.506,
                265.306, 227.506);
        ((GeneralPath) shape).curveTo(264.618, 227.506, 264.174, 227.387,
                263.969, 227.147);
        ((GeneralPath) shape).curveTo(263.764, 226.91101, 263.659, 226.388,
                263.659, 225.584);
        ((GeneralPath) shape).curveTo(263.659, 224.784, 263.762, 224.262,
                263.969, 224.021);
        ((GeneralPath) shape).curveTo(264.174, 223.786, 264.62, 223.664,
                265.306, 223.664);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_309;
        g.setTransform(defaultTransform__0_309);
        g.setClip(clip__0_309);
        float alpha__0_310 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_310 = g.getClip();
        AffineTransform defaultTransform__0_310 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_310 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(269.784, 223.719);
        ((GeneralPath) shape).lineTo(269.784, 224.172);
        ((GeneralPath) shape).lineTo(268.349, 224.172);
        ((GeneralPath) shape).lineTo(268.349, 226.457);
        ((GeneralPath) shape).curveTo(268.349, 226.854, 268.524, 227.05501,
                268.877, 227.05501);
        ((GeneralPath) shape).curveTo(269.23, 227.05501, 269.40402, 226.87701,
                269.40402, 226.52);
        ((GeneralPath) shape).lineTo(269.407, 226.336);
        ((GeneralPath) shape).lineTo(269.415, 226.129);
        ((GeneralPath) shape).lineTo(269.922, 226.129);
        ((GeneralPath) shape).lineTo(269.928, 226.40399);
        ((GeneralPath) shape).curveTo(269.928, 227.13799, 269.579, 227.508,
                268.881, 227.508);
        ((GeneralPath) shape).curveTo(268.16202, 227.508, 267.803, 227.203,
                267.803, 226.59099);
        ((GeneralPath) shape).lineTo(267.803, 224.17299);
        ((GeneralPath) shape).lineTo(267.286, 224.17299);
        ((GeneralPath) shape).lineTo(267.286, 223.71999);
        ((GeneralPath) shape).lineTo(267.803, 223.71999);
        ((GeneralPath) shape).lineTo(267.803, 222.82199);
        ((GeneralPath) shape).lineTo(268.34802, 222.82199);
        ((GeneralPath) shape).lineTo(268.34802, 223.71999);
        ((GeneralPath) shape).lineTo(269.784, 223.719);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_310;
        g.setTransform(defaultTransform__0_310);
        g.setClip(clip__0_310);
        float alpha__0_311 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_311 = g.getClip();
        AffineTransform defaultTransform__0_311 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_311 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(270.957, 223.719);
        ((GeneralPath) shape).lineTo(270.957, 227.45299);
        ((GeneralPath) shape).lineTo(270.412, 227.45299);
        ((GeneralPath) shape).lineTo(270.412, 223.719);
        ((GeneralPath) shape).lineTo(270.957, 223.719);
        ((GeneralPath) shape).moveTo(270.957, 222.121);
        ((GeneralPath) shape).lineTo(270.957, 222.732);
        ((GeneralPath) shape).lineTo(270.412, 222.732);
        ((GeneralPath) shape).lineTo(270.412, 222.121);
        ((GeneralPath) shape).lineTo(270.957, 222.121);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_311;
        g.setTransform(defaultTransform__0_311);
        g.setClip(clip__0_311);
        float alpha__0_312 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_312 = g.getClip();
        AffineTransform defaultTransform__0_312 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_312 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(274.674, 223.719);
        ((GeneralPath) shape).lineTo(273.426, 227.453);
        ((GeneralPath) shape).lineTo(272.642, 227.453);
        ((GeneralPath) shape).lineTo(271.406, 223.719);
        ((GeneralPath) shape).lineTo(271.968, 223.719);
        ((GeneralPath) shape).lineTo(272.626, 225.75);
        ((GeneralPath) shape).lineTo(272.833, 226.387);
        ((GeneralPath) shape).lineTo(272.931, 226.707);
        ((GeneralPath) shape).lineTo(273.032, 227.027);
        ((GeneralPath) shape).lineTo(273.048, 227.027);
        ((GeneralPath) shape).lineTo(273.141, 226.711);
        ((GeneralPath) shape).lineTo(273.235, 226.391);
        ((GeneralPath) shape).lineTo(273.434, 225.758);
        ((GeneralPath) shape).lineTo(274.065, 223.719);
        ((GeneralPath) shape).lineTo(274.674, 223.719);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_312;
        g.setTransform(defaultTransform__0_312);
        g.setClip(clip__0_312);
        float alpha__0_313 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_313 = g.getClip();
        AffineTransform defaultTransform__0_313 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_313 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(277.716, 225.262);
        ((GeneralPath) shape).lineTo(277.712, 225.086);
        ((GeneralPath) shape).curveTo(277.712, 224.684, 277.648, 224.42,
                277.515, 224.299);
        ((GeneralPath) shape).curveTo(277.38, 224.178, 277.09802, 224.11699,
                276.657, 224.11699);
        ((GeneralPath) shape).curveTo(276.217, 224.11699, 275.93002, 224.18498,
                275.798, 224.32999);
        ((GeneralPath) shape).curveTo(275.667, 224.471, 275.603, 224.77899,
                275.603, 225.262);
        ((GeneralPath) shape).lineTo(277.716, 225.262);
        ((GeneralPath) shape).moveTo(277.716, 226.325);
        ((GeneralPath) shape).lineTo(278.276, 226.325);
        ((GeneralPath) shape).lineTo(278.28, 226.461);
        ((GeneralPath) shape).curveTo(278.28, 226.84799, 278.163, 227.119,
                277.926, 227.274);
        ((GeneralPath) shape).curveTo(277.694, 227.42801, 277.28, 227.506,
                276.69, 227.506);
        ((GeneralPath) shape).curveTo(276.002, 227.506, 275.551, 227.381,
                275.337, 227.12999);
        ((GeneralPath) shape).curveTo(275.12302, 226.87999, 275.018, 226.34999,
                275.018, 225.54599);
        ((GeneralPath) shape).curveTo(275.018, 224.80199, 275.12302, 224.30199,
                275.33902, 224.04599);
        ((GeneralPath) shape).curveTo(275.55603, 223.79199, 275.97903, 223.663,
                276.60803, 223.663);
        ((GeneralPath) shape).curveTo(277.29504, 223.663, 277.74304, 223.76999,
                277.95703, 223.995);
        ((GeneralPath) shape).curveTo(278.16904, 224.21599, 278.27704, 224.683,
                278.27704, 225.392);
        ((GeneralPath) shape).lineTo(278.27704, 225.684);
        ((GeneralPath) shape).lineTo(275.59204, 225.684);
        ((GeneralPath) shape).curveTo(275.59204, 226.272, 275.65503, 226.647,
                275.78104, 226.809);
        ((GeneralPath) shape).curveTo(275.90604, 226.96901, 276.20004,
                227.05101, 276.66403, 227.05101);
        ((GeneralPath) shape).curveTo(277.10202, 227.05101, 277.38803, 227.014,
                277.52002, 226.936);
        ((GeneralPath) shape).curveTo(277.65103, 226.86, 277.71503, 226.694,
                277.71503, 226.438);
        ((GeneralPath) shape).lineTo(277.716, 226.325);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_313;
        g.setTransform(defaultTransform__0_313);
        g.setClip(clip__0_313);
        float alpha__0_314 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_314 = g.getClip();
        AffineTransform defaultTransform__0_314 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_314 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(284.806, 223.566);
        ((GeneralPath) shape).lineTo(284.208, 223.566);
        ((GeneralPath) shape).curveTo(284.208, 223.136, 284.14, 222.86499,
                283.993, 222.75);
        ((GeneralPath) shape).curveTo(283.851, 222.634, 283.514, 222.574,
                282.983, 222.574);
        ((GeneralPath) shape).curveTo(282.355, 222.574, 281.95, 222.63101,
                281.767, 222.74);
        ((GeneralPath) shape).curveTo(281.583, 222.849, 281.494, 223.097,
                281.494, 223.48001);
        ((GeneralPath) shape).curveTo(281.494, 223.91202, 281.564, 224.171,
                281.70898, 224.26501);
        ((GeneralPath) shape).curveTo(281.853, 224.35901, 282.27597, 224.42102,
                282.98398, 224.45601);
        ((GeneralPath) shape).curveTo(283.813, 224.49, 284.33798, 224.59702,
                284.56497, 224.77);
        ((GeneralPath) shape).curveTo(284.78897, 224.944, 284.90198, 225.334,
                284.90198, 225.942);
        ((GeneralPath) shape).curveTo(284.90198, 226.598, 284.77298, 227.022,
                284.51398, 227.215);
        ((GeneralPath) shape).curveTo(284.25497, 227.40599, 283.68497, 227.504,
                282.79898, 227.504);
        ((GeneralPath) shape).curveTo(282.033, 227.504, 281.524, 227.409,
                281.27097, 227.213);
        ((GeneralPath) shape).curveTo(281.01697, 227.019, 280.89096, 226.629,
                280.89096, 226.039);
        ((GeneralPath) shape).lineTo(280.88696, 225.801);
        ((GeneralPath) shape).lineTo(281.48395, 225.801);
        ((GeneralPath) shape).lineTo(281.48395, 225.93399);
        ((GeneralPath) shape).curveTo(281.48395, 226.413, 281.55594, 226.706,
                281.70193, 226.82399);
        ((GeneralPath) shape).curveTo(281.84793, 226.93799, 282.22293,
                226.99799, 282.82092, 226.99799);
        ((GeneralPath) shape).curveTo(283.5089, 226.99799, 283.93192,
                226.93999, 284.09192, 226.82399);
        ((GeneralPath) shape).curveTo(284.2499, 226.70898, 284.32993,
                226.39899, 284.32993, 225.89499);
        ((GeneralPath) shape).curveTo(284.32993, 225.57098, 284.27692,
                225.35399, 284.16794, 225.247);
        ((GeneralPath) shape).curveTo(284.06293, 225.13899, 283.83493,
                225.07399, 283.49194, 225.053);
        ((GeneralPath) shape).lineTo(282.86795, 225.02199);
        ((GeneralPath) shape).lineTo(282.27496, 224.99098);
        ((GeneralPath) shape).curveTo(281.37497, 224.92798, 280.92096,
                224.46198, 280.92096, 223.58698);
        ((GeneralPath) shape).curveTo(280.92096, 222.98198, 281.05396,
                222.57397, 281.31696, 222.37198);
        ((GeneralPath) shape).curveTo(281.57794, 222.16898, 282.10297,
                222.06497, 282.88797, 222.06497);
        ((GeneralPath) shape).curveTo(283.68298, 222.06497, 284.20096,
                222.16096, 284.44397, 222.34897);
        ((GeneralPath) shape).curveTo(284.685, 222.538, 284.806, 222.944,
                284.806, 223.566);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_314;
        g.setTransform(defaultTransform__0_314);
        g.setClip(clip__0_314);
        float alpha__0_315 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_315 = g.getClip();
        AffineTransform defaultTransform__0_315 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_315 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(288.249, 223.719);
        ((GeneralPath) shape).lineTo(287.192, 227.668);
        ((GeneralPath) shape).curveTo(287.04398, 228.225, 286.879, 228.606,
                286.69897, 228.814);
        ((GeneralPath) shape).curveTo(286.52, 229.021, 286.261, 229.127,
                285.925, 229.127);
        ((GeneralPath) shape).curveTo(285.861, 229.127, 285.77298, 229.119,
                285.664, 229.099);
        ((GeneralPath) shape).lineTo(285.664, 228.646);
        ((GeneralPath) shape).curveTo(285.738, 228.664, 285.805, 228.66899,
                285.855, 228.67299);
        ((GeneralPath) shape).curveTo(286.147, 228.689, 286.366, 228.44998,
                286.51, 227.958);
        ((GeneralPath) shape).lineTo(286.614, 227.59099);
        ((GeneralPath) shape).curveTo(286.61603, 227.579, 286.627, 227.53299,
                286.64902, 227.44998);
        ((GeneralPath) shape).lineTo(286.44202, 227.44998);
        ((GeneralPath) shape).lineTo(285.09302, 223.71599);
        ((GeneralPath) shape).lineTo(285.67203, 223.71599);
        ((GeneralPath) shape).lineTo(286.24503, 225.37999);
        ((GeneralPath) shape).lineTo(286.53403, 226.208);
        ((GeneralPath) shape).lineTo(286.67502, 226.62599);
        ((GeneralPath) shape).lineTo(286.821, 227.04);
        ((GeneralPath) shape).lineTo(286.837, 227.04);
        ((GeneralPath) shape).lineTo(286.94, 226.62599);
        ((GeneralPath) shape).lineTo(287.038, 226.208);
        ((GeneralPath) shape).lineTo(287.248, 225.37999);
        ((GeneralPath) shape).lineTo(287.66898, 223.71599);
        ((GeneralPath) shape).lineTo(288.249, 223.719);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_315;
        g.setTransform(defaultTransform__0_315);
        g.setClip(clip__0_315);
        float alpha__0_316 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_316 = g.getClip();
        AffineTransform defaultTransform__0_316 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_316 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(291.597, 224.695);
        ((GeneralPath) shape).lineTo(291.04898, 224.695);
        ((GeneralPath) shape).curveTo(291.04898, 224.436, 290.99597, 224.27301,
                290.89297, 224.21301);
        ((GeneralPath) shape).curveTo(290.78796, 224.15001, 290.51697,
                224.11902, 290.08, 224.11902);
        ((GeneralPath) shape).curveTo(289.67398, 224.11902, 289.417, 224.15002,
                289.309, 224.21902);
        ((GeneralPath) shape).curveTo(289.19998, 224.28502, 289.145, 224.44302,
                289.145, 224.69702);
        ((GeneralPath) shape).curveTo(289.145, 225.08202, 289.32898, 225.27902,
                289.69498, 225.30103);
        ((GeneralPath) shape).lineTo(290.13297, 225.32202);
        ((GeneralPath) shape).lineTo(290.69296, 225.34901);
        ((GeneralPath) shape).curveTo(291.36697, 225.38301, 291.70697,
                225.73601, 291.70697, 226.41202);
        ((GeneralPath) shape).curveTo(291.70697, 226.82903, 291.59796,
                227.11902, 291.37198, 227.27301);
        ((GeneralPath) shape).curveTo(291.15, 227.42902, 290.74, 227.507,
                290.14398, 227.507);
        ((GeneralPath) shape).curveTo(289.534, 227.507, 289.11298, 227.433,
                288.88098, 227.285);
        ((GeneralPath) shape).curveTo(288.65198, 227.136, 288.53598, 226.863,
                288.53598, 226.468);
        ((GeneralPath) shape).lineTo(288.53998, 226.265);
        ((GeneralPath) shape).lineTo(289.10498, 226.265);
        ((GeneralPath) shape).lineTo(289.10898, 226.441);
        ((GeneralPath) shape).curveTo(289.10898, 226.68599, 289.17197,
                226.84999, 289.296, 226.931);
        ((GeneralPath) shape).curveTo(289.421, 227.01399, 289.667, 227.054,
                290.033, 227.054);
        ((GeneralPath) shape).curveTo(290.482, 227.054, 290.77798, 227.011,
                290.91998, 226.925);
        ((GeneralPath) shape).curveTo(291.06198, 226.84, 291.133, 226.66,
                291.133, 226.386);
        ((GeneralPath) shape).curveTo(291.133, 225.993, 290.955, 225.796,
                290.6, 225.796);
        ((GeneralPath) shape).curveTo(289.771, 225.796, 289.226, 225.726,
                288.962, 225.585);
        ((GeneralPath) shape).curveTo(288.70102, 225.442, 288.57, 225.15201,
                288.57, 224.71);
        ((GeneralPath) shape).curveTo(288.57, 224.292, 288.674, 224.013,
                288.88, 223.873);
        ((GeneralPath) shape).curveTo(289.087, 223.732, 289.49802, 223.664,
                290.114, 223.664);
        ((GeneralPath) shape).curveTo(291.1, 223.664, 291.595, 223.961,
                291.595, 224.559);
        ((GeneralPath) shape).lineTo(291.597, 224.695);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_316;
        g.setTransform(defaultTransform__0_316);
        g.setClip(clip__0_316);
        float alpha__0_317 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_317 = g.getClip();
        AffineTransform defaultTransform__0_317 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_317 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(294.471, 223.719);
        ((GeneralPath) shape).lineTo(294.471, 224.172);
        ((GeneralPath) shape).lineTo(293.034, 224.172);
        ((GeneralPath) shape).lineTo(293.034, 226.457);
        ((GeneralPath) shape).curveTo(293.034, 226.854, 293.20898, 227.05501,
                293.564, 227.05501);
        ((GeneralPath) shape).curveTo(293.915, 227.05501, 294.08798, 226.87701,
                294.08798, 226.52);
        ((GeneralPath) shape).lineTo(294.094, 226.336);
        ((GeneralPath) shape).lineTo(294.102, 226.129);
        ((GeneralPath) shape).lineTo(294.60898, 226.129);
        ((GeneralPath) shape).lineTo(294.61298, 226.40399);
        ((GeneralPath) shape).curveTo(294.61298, 227.13799, 294.266, 227.508,
                293.56796, 227.508);
        ((GeneralPath) shape).curveTo(292.84897, 227.508, 292.48798, 227.203,
                292.48798, 226.59099);
        ((GeneralPath) shape).lineTo(292.48798, 224.17299);
        ((GeneralPath) shape).lineTo(291.97296, 224.17299);
        ((GeneralPath) shape).lineTo(291.97296, 223.71999);
        ((GeneralPath) shape).lineTo(292.48798, 223.71999);
        ((GeneralPath) shape).lineTo(292.48798, 222.82199);
        ((GeneralPath) shape).lineTo(293.03397, 222.82199);
        ((GeneralPath) shape).lineTo(293.03397, 223.71999);
        ((GeneralPath) shape).lineTo(294.471, 223.719);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_317;
        g.setTransform(defaultTransform__0_317);
        g.setClip(clip__0_317);
        float alpha__0_318 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_318 = g.getClip();
        AffineTransform defaultTransform__0_318 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_318 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(297.69, 225.262);
        ((GeneralPath) shape).lineTo(297.686, 225.086);
        ((GeneralPath) shape).curveTo(297.686, 224.684, 297.62, 224.42,
                297.487, 224.299);
        ((GeneralPath) shape).curveTo(297.354, 224.178, 297.07, 224.11699,
                296.629, 224.11699);
        ((GeneralPath) shape).curveTo(296.191, 224.11699, 295.902, 224.18498,
                295.772, 224.32999);
        ((GeneralPath) shape).curveTo(295.641, 224.471, 295.575, 224.77899,
                295.575, 225.262);
        ((GeneralPath) shape).lineTo(297.69, 225.262);
        ((GeneralPath) shape).moveTo(297.69, 226.325);
        ((GeneralPath) shape).lineTo(298.24802, 226.325);
        ((GeneralPath) shape).lineTo(298.25403, 226.461);
        ((GeneralPath) shape).curveTo(298.25403, 226.84799, 298.13702, 227.119,
                297.90002, 227.274);
        ((GeneralPath) shape).curveTo(297.66602, 227.42801, 297.25302, 227.506,
                296.66202, 227.506);
        ((GeneralPath) shape).curveTo(295.976, 227.506, 295.52502, 227.381,
                295.311, 227.12999);
        ((GeneralPath) shape).curveTo(295.09702, 226.87999, 294.99, 226.34999,
                294.99, 225.54599);
        ((GeneralPath) shape).curveTo(294.99, 224.80199, 295.097, 224.30199,
                295.313, 224.04599);
        ((GeneralPath) shape).curveTo(295.52798, 223.79199, 295.951, 223.663,
                296.58, 223.663);
        ((GeneralPath) shape).curveTo(297.26797, 223.663, 297.71698, 223.76999,
                297.931, 223.995);
        ((GeneralPath) shape).curveTo(298.144, 224.21599, 298.249, 224.683,
                298.249, 225.392);
        ((GeneralPath) shape).lineTo(298.249, 225.684);
        ((GeneralPath) shape).lineTo(295.56598, 225.684);
        ((GeneralPath) shape).curveTo(295.56598, 226.272, 295.62897, 226.647,
                295.75397, 226.809);
        ((GeneralPath) shape).curveTo(295.87897, 226.96901, 296.17297,
                227.05101, 296.63898, 227.05101);
        ((GeneralPath) shape).curveTo(297.07697, 227.05101, 297.36197, 227.014,
                297.49298, 226.936);
        ((GeneralPath) shape).curveTo(297.624, 226.86, 297.68997, 226.694,
                297.68997, 226.438);
        ((GeneralPath) shape).lineTo(297.69, 226.325);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_318;
        g.setTransform(defaultTransform__0_318);
        g.setClip(clip__0_318);
        float alpha__0_319 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_319 = g.getClip();
        AffineTransform defaultTransform__0_319 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_319 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(299.106, 223.719);
        ((GeneralPath) shape).lineTo(299.65198, 223.719);
        ((GeneralPath) shape).lineTo(299.63998, 224.282);
        ((GeneralPath) shape).lineTo(299.65198, 224.296);
        ((GeneralPath) shape).curveTo(299.86197, 223.878, 300.26797, 223.667,
                300.869, 223.667);
        ((GeneralPath) shape).curveTo(301.5, 223.667, 301.88498, 223.876,
                302.01898, 224.296);
        ((GeneralPath) shape).lineTo(302.03497, 224.296);
        ((GeneralPath) shape).curveTo(302.26898, 223.878, 302.69598, 223.667,
                303.317, 223.667);
        ((GeneralPath) shape).curveTo(304.194, 223.667, 304.63498, 224.115,
                304.63498, 225.01501);
        ((GeneralPath) shape).lineTo(304.63498, 227.45601);
        ((GeneralPath) shape).lineTo(304.089, 227.45601);
        ((GeneralPath) shape).lineTo(304.089, 224.964);
        ((GeneralPath) shape).curveTo(304.089, 224.636, 304.029, 224.414,
                303.912, 224.296);
        ((GeneralPath) shape).curveTo(303.797, 224.179, 303.57498, 224.12001,
                303.25098, 224.12001);
        ((GeneralPath) shape).curveTo(302.81796, 224.12001, 302.52597,
                224.19801, 302.37198, 224.36201);
        ((GeneralPath) shape).curveTo(302.222, 224.52402, 302.14297, 224.83702,
                302.14297, 225.29602);
        ((GeneralPath) shape).lineTo(302.14297, 227.45602);
        ((GeneralPath) shape).lineTo(301.597, 227.45602);
        ((GeneralPath) shape).lineTo(301.597, 225.01503);
        ((GeneralPath) shape).lineTo(301.589, 224.84402);
        ((GeneralPath) shape).curveTo(301.589, 224.36302, 301.31598, 224.12102,
                300.766, 224.12102);
        ((GeneralPath) shape).curveTo(300.02298, 224.12102, 299.651, 224.52402,
                299.651, 225.33601);
        ((GeneralPath) shape).lineTo(299.651, 227.45702);
        ((GeneralPath) shape).lineTo(299.105, 227.45702);
        ((GeneralPath) shape).lineTo(299.105, 223.719);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_319;
        g.setTransform(defaultTransform__0_319);
        g.setClip(clip__0_319);
        float alpha__0_320 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_320 = g.getClip();
        AffineTransform defaultTransform__0_320 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_320 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(312.038, 222.121);
        ((GeneralPath) shape).lineTo(312.038, 227.453);
        ((GeneralPath) shape).lineTo(311.438, 227.453);
        ((GeneralPath) shape).lineTo(311.438, 224.981);
        ((GeneralPath) shape).lineTo(308.326, 224.981);
        ((GeneralPath) shape).lineTo(308.326, 227.453);
        ((GeneralPath) shape).lineTo(307.727, 227.453);
        ((GeneralPath) shape).lineTo(307.727, 222.121);
        ((GeneralPath) shape).lineTo(308.326, 222.121);
        ((GeneralPath) shape).lineTo(308.326, 224.473);
        ((GeneralPath) shape).lineTo(311.438, 224.473);
        ((GeneralPath) shape).lineTo(311.438, 222.121);
        ((GeneralPath) shape).lineTo(312.038, 222.121);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_320;
        g.setTransform(defaultTransform__0_320);
        g.setClip(clip__0_320);
        float alpha__0_321 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_321 = g.getClip();
        AffineTransform defaultTransform__0_321 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_321 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(313.571, 223.719);
        ((GeneralPath) shape).lineTo(313.571, 227.45299);
        ((GeneralPath) shape).lineTo(313.02502, 227.45299);
        ((GeneralPath) shape).lineTo(313.02502, 223.719);
        ((GeneralPath) shape).lineTo(313.571, 223.719);
        ((GeneralPath) shape).moveTo(313.571, 222.121);
        ((GeneralPath) shape).lineTo(313.571, 222.732);
        ((GeneralPath) shape).lineTo(313.02502, 222.732);
        ((GeneralPath) shape).lineTo(313.02502, 222.121);
        ((GeneralPath) shape).lineTo(313.571, 222.121);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_321;
        g.setTransform(defaultTransform__0_321);
        g.setClip(clip__0_321);
        float alpha__0_322 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_322 = g.getClip();
        AffineTransform defaultTransform__0_322 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_322 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(316.506, 223.719);
        ((GeneralPath) shape).lineTo(316.506, 224.172);
        ((GeneralPath) shape).lineTo(315.069, 224.172);
        ((GeneralPath) shape).lineTo(315.069, 226.457);
        ((GeneralPath) shape).curveTo(315.069, 226.854, 315.245, 227.05501,
                315.599, 227.05501);
        ((GeneralPath) shape).curveTo(315.951, 227.05501, 316.123, 226.87701,
                316.123, 226.52);
        ((GeneralPath) shape).lineTo(316.129, 226.336);
        ((GeneralPath) shape).lineTo(316.137, 226.129);
        ((GeneralPath) shape).lineTo(316.64398, 226.129);
        ((GeneralPath) shape).lineTo(316.64798, 226.40399);
        ((GeneralPath) shape).curveTo(316.64798, 227.13799, 316.301, 227.508,
                315.60297, 227.508);
        ((GeneralPath) shape).curveTo(314.88397, 227.508, 314.52298, 227.203,
                314.52298, 226.59099);
        ((GeneralPath) shape).lineTo(314.52298, 224.17299);
        ((GeneralPath) shape).lineTo(314.00797, 224.17299);
        ((GeneralPath) shape).lineTo(314.00797, 223.71999);
        ((GeneralPath) shape).lineTo(314.52298, 223.71999);
        ((GeneralPath) shape).lineTo(314.52298, 222.82199);
        ((GeneralPath) shape).lineTo(315.06897, 222.82199);
        ((GeneralPath) shape).lineTo(315.06897, 223.71999);
        ((GeneralPath) shape).lineTo(316.506, 223.719);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_322;
        g.setTransform(defaultTransform__0_322);
        g.setClip(clip__0_322);
        float alpha__0_323 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_323 = g.getClip();
        AffineTransform defaultTransform__0_323 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_323 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(320.021, 224.695);
        ((GeneralPath) shape).lineTo(319.475, 224.695);
        ((GeneralPath) shape).curveTo(319.475, 224.436, 319.422, 224.27301,
                319.31702, 224.21301);
        ((GeneralPath) shape).curveTo(319.213, 224.15001, 318.941, 224.11902,
                318.50403, 224.11902);
        ((GeneralPath) shape).curveTo(318.09802, 224.11902, 317.84103,
                224.15002, 317.73203, 224.21902);
        ((GeneralPath) shape).curveTo(317.62302, 224.28502, 317.56802,
                224.44302, 317.56802, 224.69702);
        ((GeneralPath) shape).curveTo(317.56802, 225.08202, 317.75403,
                225.27902, 318.118, 225.30103);
        ((GeneralPath) shape).lineTo(318.55902, 225.32202);
        ((GeneralPath) shape).lineTo(319.11603, 225.34901);
        ((GeneralPath) shape).curveTo(319.79102, 225.38301, 320.13202,
                225.73601, 320.13202, 226.41202);
        ((GeneralPath) shape).curveTo(320.13202, 226.82903, 320.02203,
                227.11902, 319.79703, 227.27301);
        ((GeneralPath) shape).curveTo(319.57303, 227.42902, 319.16302, 227.507,
                318.56802, 227.507);
        ((GeneralPath) shape).curveTo(317.96002, 227.507, 317.53702, 227.433,
                317.30704, 227.285);
        ((GeneralPath) shape).curveTo(317.07703, 227.136, 316.96103, 226.863,
                316.96103, 226.468);
        ((GeneralPath) shape).lineTo(316.96603, 226.265);
        ((GeneralPath) shape).lineTo(317.52902, 226.265);
        ((GeneralPath) shape).lineTo(317.53302, 226.441);
        ((GeneralPath) shape).curveTo(317.53302, 226.68599, 317.596, 226.84999,
                317.721, 226.931);
        ((GeneralPath) shape).curveTo(317.846, 227.01399, 318.091, 227.054,
                318.45502, 227.054);
        ((GeneralPath) shape).curveTo(318.90402, 227.054, 319.2, 227.011,
                319.34503, 226.925);
        ((GeneralPath) shape).curveTo(319.48602, 226.84, 319.55804, 226.66,
                319.55804, 226.386);
        ((GeneralPath) shape).curveTo(319.55804, 225.993, 319.38004, 225.796,
                319.02405, 225.796);
        ((GeneralPath) shape).curveTo(318.19504, 225.796, 317.64905, 225.726,
                317.38806, 225.585);
        ((GeneralPath) shape).curveTo(317.12607, 225.442, 316.99307, 225.15201,
                316.99307, 224.71);
        ((GeneralPath) shape).curveTo(316.99307, 224.292, 317.09708, 224.013,
                317.30606, 223.873);
        ((GeneralPath) shape).curveTo(317.51205, 223.732, 317.92407, 223.664,
                318.53705, 223.664);
        ((GeneralPath) shape).curveTo(319.52304, 223.664, 320.01904, 223.961,
                320.01904, 224.559);
        ((GeneralPath) shape).lineTo(320.021, 224.695);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_323;
        g.setTransform(defaultTransform__0_323);
        g.setClip(clip__0_323);
        float alpha__0_324 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_324 = g.getClip();
        AffineTransform defaultTransform__0_324 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_324 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        stroke = new BasicStroke(1.0f, 0, 0, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(354.199, 216.52);
        ((GeneralPath) shape).curveTo(354.199, 217.145, 353.691, 217.654,
                353.065, 217.654);
        ((GeneralPath) shape).lineTo(347.296, 217.654);
        ((GeneralPath) shape).curveTo(346.66898, 217.654, 346.16, 217.145,
                346.16, 216.52);
        ((GeneralPath) shape).lineTo(346.16, 210.692);
        ((GeneralPath) shape).curveTo(346.16, 210.066, 346.669, 209.559,
                347.296, 209.559);
        ((GeneralPath) shape).lineTo(353.065, 209.559);
        ((GeneralPath) shape).curveTo(353.691, 209.559, 354.199, 210.065,
                354.199, 210.692);
        ((GeneralPath) shape).lineTo(354.199, 216.52);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_324;
        g.setTransform(defaultTransform__0_324);
        g.setClip(clip__0_324);
        float alpha__0_325 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_325 = g.getClip();
        AffineTransform defaultTransform__0_325 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_325 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(348.14, 215.323);
        ((GeneralPath) shape).lineTo(348.14, 214.13);
        ((GeneralPath) shape).lineTo(346.963, 214.13);
        ((GeneralPath) shape).lineTo(346.963, 213.75);
        ((GeneralPath) shape).lineTo(348.14, 213.75);
        ((GeneralPath) shape).lineTo(348.14, 212.552);
        ((GeneralPath) shape).lineTo(348.551, 212.552);
        ((GeneralPath) shape).lineTo(348.551, 213.75);
        ((GeneralPath) shape).lineTo(349.728, 213.75);
        ((GeneralPath) shape).lineTo(349.728, 214.13);
        ((GeneralPath) shape).lineTo(348.551, 214.13);
        ((GeneralPath) shape).lineTo(348.551, 215.323);
        ((GeneralPath) shape).lineTo(348.14, 215.323);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_325;
        g.setTransform(defaultTransform__0_325);
        g.setClip(clip__0_325);
        float alpha__0_326 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_326 = g.getClip();
        AffineTransform defaultTransform__0_326 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_326 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(352.548, 211.939);
        ((GeneralPath) shape).lineTo(352.548, 215.938);
        ((GeneralPath) shape).lineTo(352.096, 215.938);
        ((GeneralPath) shape).lineTo(352.096, 212.276);
        ((GeneralPath) shape).lineTo(351.075, 213.41);
        ((GeneralPath) shape).lineTo(350.791, 213.14);
        ((GeneralPath) shape).lineTo(351.896, 211.939);
        ((GeneralPath) shape).lineTo(352.548, 211.939);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_326;
        g.setTransform(defaultTransform__0_326);
        g.setClip(clip__0_326);
        float alpha__0_327 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_327 = g.getClip();
        AffineTransform defaultTransform__0_327 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_327 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        stroke = new BasicStroke(1.0f, 0, 0, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(364.276, 216.52);
        ((GeneralPath) shape).curveTo(364.276, 217.145, 363.769, 217.654,
                363.141, 217.654);
        ((GeneralPath) shape).lineTo(357.373, 217.654);
        ((GeneralPath) shape).curveTo(356.745, 217.654, 356.236, 217.145,
                356.236, 216.52);
        ((GeneralPath) shape).lineTo(356.236, 210.692);
        ((GeneralPath) shape).curveTo(356.236, 210.066, 356.745, 209.558,
                357.373, 209.558);
        ((GeneralPath) shape).lineTo(363.141, 209.558);
        ((GeneralPath) shape).curveTo(363.76898, 209.558, 364.276, 210.066,
                364.276, 210.692);
        ((GeneralPath) shape).lineTo(364.276, 216.52);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_327;
        g.setTransform(defaultTransform__0_327);
        g.setClip(clip__0_327);
        float alpha__0_328 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_328 = g.getClip();
        AffineTransform defaultTransform__0_328 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_328 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(358.219, 215.323);
        ((GeneralPath) shape).lineTo(358.219, 214.13);
        ((GeneralPath) shape).lineTo(357.039, 214.13);
        ((GeneralPath) shape).lineTo(357.039, 213.75);
        ((GeneralPath) shape).lineTo(358.219, 213.75);
        ((GeneralPath) shape).lineTo(358.219, 212.552);
        ((GeneralPath) shape).lineTo(358.629, 212.552);
        ((GeneralPath) shape).lineTo(358.629, 213.75);
        ((GeneralPath) shape).lineTo(359.806, 213.75);
        ((GeneralPath) shape).lineTo(359.806, 214.13);
        ((GeneralPath) shape).lineTo(358.629, 214.13);
        ((GeneralPath) shape).lineTo(358.629, 215.323);
        ((GeneralPath) shape).lineTo(358.219, 215.323);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_328;
        g.setTransform(defaultTransform__0_328);
        g.setClip(clip__0_328);
        float alpha__0_329 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_329 = g.getClip();
        AffineTransform defaultTransform__0_329 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_329 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(363.454, 215.558);
        ((GeneralPath) shape).lineTo(363.454, 215.939);
        ((GeneralPath) shape).lineTo(360.579, 215.939);
        ((GeneralPath) shape).lineTo(360.579, 215.18599);
        ((GeneralPath) shape).curveTo(360.579, 214.76399, 360.661, 214.48898,
                360.825, 214.357);
        ((GeneralPath) shape).curveTo(360.989, 214.22699, 361.38, 214.12799,
                361.992, 214.055);
        ((GeneralPath) shape).curveTo(362.483, 214.00099, 362.779, 213.92699,
                362.884, 213.833);
        ((GeneralPath) shape).curveTo(362.987, 213.737, 363.035, 213.49599,
                363.035, 213.10399);
        ((GeneralPath) shape).curveTo(363.035, 212.76099, 362.978, 212.53899,
                362.864, 212.43498);
        ((GeneralPath) shape).curveTo(362.75302, 212.33199, 362.50403,
                212.28098, 362.117, 212.28098);
        ((GeneralPath) shape).curveTo(361.638, 212.28098, 361.337, 212.32198,
                361.211, 212.40598);
        ((GeneralPath) shape).curveTo(361.086, 212.48798, 361.023, 212.68898,
                361.023, 213.00697);
        ((GeneralPath) shape).lineTo(361.02902, 213.30597);
        ((GeneralPath) shape).lineTo(360.59003, 213.30597);
        ((GeneralPath) shape).lineTo(360.59302, 213.09798);
        ((GeneralPath) shape).curveTo(360.59302, 212.61998, 360.69403,
                212.30098, 360.89603, 212.14098);
        ((GeneralPath) shape).curveTo(361.09802, 211.98297, 361.50104,
                211.90198, 362.10803, 211.90198);
        ((GeneralPath) shape).curveTo(362.64603, 211.90198, 363.00702,
                211.98698, 363.19302, 212.16098);
        ((GeneralPath) shape).curveTo(363.38004, 212.33298, 363.47104,
                212.66898, 363.47104, 213.16698);
        ((GeneralPath) shape).curveTo(363.47104, 213.64398, 363.38403,
                213.96498, 363.21204, 214.12598);
        ((GeneralPath) shape).curveTo(363.03705, 214.28598, 362.66302,
                214.39598, 362.08704, 214.45097);
        ((GeneralPath) shape).curveTo(361.57904, 214.50298, 361.27603,
                214.57097, 361.18103, 214.66298);
        ((GeneralPath) shape).curveTo(361.08203, 214.75298, 361.03503,
                215.00697, 361.03503, 215.42798);
        ((GeneralPath) shape).lineTo(361.03503, 215.56297);
        ((GeneralPath) shape).lineTo(363.454, 215.558);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_329;
        g.setTransform(defaultTransform__0_329);
        g.setClip(clip__0_329);
        float alpha__0_330 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_330 = g.getClip();
        AffineTransform defaultTransform__0_330 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_330 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        stroke = new BasicStroke(1.0f, 0, 0, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(374.354, 216.52);
        ((GeneralPath) shape).curveTo(374.354, 217.145, 373.845, 217.654,
                373.21802, 217.654);
        ((GeneralPath) shape).lineTo(367.449, 217.654);
        ((GeneralPath) shape).curveTo(366.822, 217.654, 366.31302, 217.145,
                366.31302, 216.52);
        ((GeneralPath) shape).lineTo(366.31302, 210.692);
        ((GeneralPath) shape).curveTo(366.31302, 210.066, 366.82202, 209.559,
                367.449, 209.559);
        ((GeneralPath) shape).lineTo(373.21802, 209.559);
        ((GeneralPath) shape).curveTo(373.84503, 209.559, 374.354, 210.065,
                374.354, 210.692);
        ((GeneralPath) shape).lineTo(374.354, 216.52);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_330;
        g.setTransform(defaultTransform__0_330);
        g.setClip(clip__0_330);
        float alpha__0_331 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_331 = g.getClip();
        AffineTransform defaultTransform__0_331 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_331 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(368.298, 215.323);
        ((GeneralPath) shape).lineTo(368.298, 214.13);
        ((GeneralPath) shape).lineTo(367.117, 214.13);
        ((GeneralPath) shape).lineTo(367.117, 213.75);
        ((GeneralPath) shape).lineTo(368.298, 213.75);
        ((GeneralPath) shape).lineTo(368.298, 212.552);
        ((GeneralPath) shape).lineTo(368.705, 212.552);
        ((GeneralPath) shape).lineTo(368.705, 213.75);
        ((GeneralPath) shape).lineTo(369.885, 213.75);
        ((GeneralPath) shape).lineTo(369.885, 214.13);
        ((GeneralPath) shape).lineTo(368.705, 214.13);
        ((GeneralPath) shape).lineTo(368.705, 215.323);
        ((GeneralPath) shape).lineTo(368.298, 215.323);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_331;
        g.setTransform(defaultTransform__0_331);
        g.setClip(clip__0_331);
        float alpha__0_332 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_332 = g.getClip();
        AffineTransform defaultTransform__0_332 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_332 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(371.797, 214.084);
        ((GeneralPath) shape).lineTo(371.797, 213.7);
        ((GeneralPath) shape).lineTo(371.947, 213.702);
        ((GeneralPath) shape).curveTo(372.485, 213.702, 372.813, 213.66899,
                372.93298, 213.59799);
        ((GeneralPath) shape).curveTo(373.05298, 213.53, 373.11298, 213.34299,
                373.11298, 213.038);
        ((GeneralPath) shape).curveTo(373.11298, 212.68399, 373.06198,
                212.47299, 372.95697, 212.392);
        ((GeneralPath) shape).curveTo(372.85297, 212.314, 372.56497, 212.275,
                372.09497, 212.275);
        ((GeneralPath) shape).curveTo(371.65897, 212.275, 371.39197, 212.31299,
                371.28696, 212.38899);
        ((GeneralPath) shape).curveTo(371.18497, 212.465, 371.13596, 212.66399,
                371.13596, 212.98999);
        ((GeneralPath) shape).lineTo(371.13596, 213.16399);
        ((GeneralPath) shape).lineTo(370.70697, 213.16399);
        ((GeneralPath) shape).lineTo(370.71097, 213.00598);
        ((GeneralPath) shape).curveTo(370.71097, 212.54898, 370.79996,
                212.24998, 370.97897, 212.10799);
        ((GeneralPath) shape).curveTo(371.15598, 211.96698, 371.52997,
                211.89499, 372.10098, 211.89499);
        ((GeneralPath) shape).curveTo(372.688, 211.89499, 373.076, 211.96098,
                373.26398, 212.09299);
        ((GeneralPath) shape).curveTo(373.451, 212.22499, 373.54297, 212.49599,
                373.54297, 212.90898);
        ((GeneralPath) shape).curveTo(373.54297, 213.25798, 373.49997,
                213.49799, 373.40897, 213.62698);
        ((GeneralPath) shape).curveTo(373.31998, 213.75598, 373.13898,
                213.83598, 372.87097, 213.86998);
        ((GeneralPath) shape).lineTo(372.87097, 213.89098);
        ((GeneralPath) shape).curveTo(373.17398, 213.93198, 373.37198,
                214.01398, 373.47397, 214.13698);
        ((GeneralPath) shape).curveTo(373.57196, 214.25998, 373.61996,
                214.48898, 373.61996, 214.82298);
        ((GeneralPath) shape).curveTo(373.61996, 215.28899, 373.52496,
                215.59598, 373.33197, 215.75098);
        ((GeneralPath) shape).curveTo(373.14297, 215.90097, 372.75598,
                215.97998, 372.17096, 215.97998);
        ((GeneralPath) shape).curveTo(371.54095, 215.97998, 371.13095,
                215.91199, 370.93597, 215.76898);
        ((GeneralPath) shape).curveTo(370.74097, 215.62798, 370.64297,
                215.33098, 370.64297, 214.87498);
        ((GeneralPath) shape).lineTo(370.64297, 214.63799);
        ((GeneralPath) shape).lineTo(371.08096, 214.63799);
        ((GeneralPath) shape).lineTo(371.08096, 214.86899);
        ((GeneralPath) shape).curveTo(371.08096, 215.20898, 371.14096,
                215.41599, 371.25595, 215.48698);
        ((GeneralPath) shape).curveTo(371.36795, 215.56099, 371.69495,
                215.59698, 372.23495, 215.59698);
        ((GeneralPath) shape).curveTo(372.65195, 215.59698, 372.91696,
                215.55098, 373.02795, 215.45699);
        ((GeneralPath) shape).curveTo(373.13794, 215.36299, 373.19394,
                215.14299, 373.19394, 214.78998);
        ((GeneralPath) shape).curveTo(373.19394, 214.49597, 373.14093,
                214.30399, 373.03693, 214.21397);
        ((GeneralPath) shape).curveTo(372.93292, 214.12598, 372.7029,
                214.08098, 372.34894, 214.08098);
        ((GeneralPath) shape).lineTo(371.79993, 214.08098);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_332;
        g.setTransform(defaultTransform__0_332);
        g.setClip(clip__0_332);
        float alpha__0_333 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_333 = g.getClip();
        AffineTransform defaultTransform__0_333 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_333 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        stroke = new BasicStroke(1.0f, 0, 0, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(384.43, 216.52);
        ((GeneralPath) shape).curveTo(384.43, 217.145, 383.922, 217.654,
                383.296, 217.654);
        ((GeneralPath) shape).lineTo(377.526, 217.654);
        ((GeneralPath) shape).curveTo(376.898, 217.654, 376.391, 217.145,
                376.391, 216.52);
        ((GeneralPath) shape).lineTo(376.391, 210.69301);
        ((GeneralPath) shape).curveTo(376.391, 210.067, 376.89798, 209.559,
                377.526, 209.559);
        ((GeneralPath) shape).lineTo(383.296, 209.559);
        ((GeneralPath) shape).curveTo(383.922, 209.559, 384.43, 210.06601,
                384.43, 210.69301);
        ((GeneralPath) shape).lineTo(384.43, 216.52);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_333;
        g.setTransform(defaultTransform__0_333);
        g.setClip(clip__0_333);
        float alpha__0_334 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_334 = g.getClip();
        AffineTransform defaultTransform__0_334 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_334 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(379.286, 215.558);
        ((GeneralPath) shape).lineTo(380.59, 215.558);
        ((GeneralPath) shape).curveTo(381.067, 215.558, 381.373, 215.463,
                381.514, 215.271);
        ((GeneralPath) shape).curveTo(381.652, 215.083, 381.721, 214.653,
                381.721, 213.991);
        ((GeneralPath) shape).curveTo(381.721, 213.26399, 381.659, 212.804,
                381.539, 212.611);
        ((GeneralPath) shape).curveTo(381.422, 212.42, 381.135, 212.321,
                380.68, 212.321);
        ((GeneralPath) shape).lineTo(379.28598, 212.321);
        ((GeneralPath) shape).lineTo(379.28598, 215.558);
        ((GeneralPath) shape).moveTo(378.838, 215.938);
        ((GeneralPath) shape).lineTo(378.838, 211.938);
        ((GeneralPath) shape).lineTo(380.686, 211.938);
        ((GeneralPath) shape).curveTo(381.256, 211.938, 381.644, 212.06401,
                381.84702, 212.31601);
        ((GeneralPath) shape).curveTo(382.049, 212.56801, 382.151, 213.04901,
                382.151, 213.76302);
        ((GeneralPath) shape).curveTo(382.151, 214.63002, 382.062, 215.20901,
                381.881, 215.50002);
        ((GeneralPath) shape).curveTo(381.699, 215.79102, 381.341, 215.93701,
                380.80002, 215.93701);
        ((GeneralPath) shape).lineTo(378.838, 215.938);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_334;
        g.setTransform(defaultTransform__0_334);
        g.setClip(clip__0_334);
        float alpha__0_335 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_335 = g.getClip();
        AffineTransform defaultTransform__0_335 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_335 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        stroke = new BasicStroke(1.0f, 0, 0, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(354.199, 227.448);
        ((GeneralPath) shape).curveTo(354.199, 228.076, 353.691, 228.584,
                353.065, 228.584);
        ((GeneralPath) shape).lineTo(347.296, 228.584);
        ((GeneralPath) shape).curveTo(346.66898, 228.584, 346.16, 228.077,
                346.16, 227.448);
        ((GeneralPath) shape).lineTo(346.16, 221.623);
        ((GeneralPath) shape).curveTo(346.16, 220.998, 346.669, 220.487,
                347.296, 220.487);
        ((GeneralPath) shape).lineTo(353.065, 220.487);
        ((GeneralPath) shape).curveTo(353.691, 220.487, 354.199, 220.998,
                354.199, 221.623);
        ((GeneralPath) shape).lineTo(354.199, 227.448);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_335;
        g.setTransform(defaultTransform__0_335);
        g.setClip(clip__0_335);
        float alpha__0_336 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_336 = g.getClip();
        AffineTransform defaultTransform__0_336 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_336 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(348.14, 226.255);
        ((GeneralPath) shape).lineTo(348.14, 225.063);
        ((GeneralPath) shape).lineTo(346.963, 225.063);
        ((GeneralPath) shape).lineTo(346.963, 224.682);
        ((GeneralPath) shape).lineTo(348.14, 224.682);
        ((GeneralPath) shape).lineTo(348.14, 223.483);
        ((GeneralPath) shape).lineTo(348.551, 223.483);
        ((GeneralPath) shape).lineTo(348.551, 224.682);
        ((GeneralPath) shape).lineTo(349.728, 224.682);
        ((GeneralPath) shape).lineTo(349.728, 225.063);
        ((GeneralPath) shape).lineTo(348.551, 225.063);
        ((GeneralPath) shape).lineTo(348.551, 226.255);
        ((GeneralPath) shape).lineTo(348.14, 226.255);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_336;
        g.setTransform(defaultTransform__0_336);
        g.setClip(clip__0_336);
        float alpha__0_337 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_337 = g.getClip();
        AffineTransform defaultTransform__0_337 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_337 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(352.548, 222.871);
        ((GeneralPath) shape).lineTo(352.548, 226.87);
        ((GeneralPath) shape).lineTo(352.096, 226.87);
        ((GeneralPath) shape).lineTo(352.096, 223.208);
        ((GeneralPath) shape).lineTo(351.075, 224.342);
        ((GeneralPath) shape).lineTo(350.791, 224.072);
        ((GeneralPath) shape).lineTo(351.896, 222.871);
        ((GeneralPath) shape).lineTo(352.548, 222.871);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_337;
        g.setTransform(defaultTransform__0_337);
        g.setClip(clip__0_337);
        float alpha__0_338 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_338 = g.getClip();
        AffineTransform defaultTransform__0_338 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_338 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        stroke = new BasicStroke(1.0f, 0, 0, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(364.276, 227.448);
        ((GeneralPath) shape).curveTo(364.276, 228.076, 363.769, 228.584,
                363.141, 228.584);
        ((GeneralPath) shape).lineTo(357.373, 228.584);
        ((GeneralPath) shape).curveTo(356.745, 228.584, 356.236, 228.077,
                356.236, 227.448);
        ((GeneralPath) shape).lineTo(356.236, 221.622);
        ((GeneralPath) shape).curveTo(356.236, 220.997, 356.745, 220.486,
                357.373, 220.486);
        ((GeneralPath) shape).lineTo(363.141, 220.486);
        ((GeneralPath) shape).curveTo(363.76898, 220.486, 364.276, 220.997,
                364.276, 221.622);
        ((GeneralPath) shape).lineTo(364.276, 227.448);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_338;
        g.setTransform(defaultTransform__0_338);
        g.setClip(clip__0_338);
        float alpha__0_339 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_339 = g.getClip();
        AffineTransform defaultTransform__0_339 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_339 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(358.219, 226.255);
        ((GeneralPath) shape).lineTo(358.219, 225.063);
        ((GeneralPath) shape).lineTo(357.039, 225.063);
        ((GeneralPath) shape).lineTo(357.039, 224.682);
        ((GeneralPath) shape).lineTo(358.219, 224.682);
        ((GeneralPath) shape).lineTo(358.219, 223.483);
        ((GeneralPath) shape).lineTo(358.629, 223.483);
        ((GeneralPath) shape).lineTo(358.629, 224.682);
        ((GeneralPath) shape).lineTo(359.806, 224.682);
        ((GeneralPath) shape).lineTo(359.806, 225.063);
        ((GeneralPath) shape).lineTo(358.629, 225.063);
        ((GeneralPath) shape).lineTo(358.629, 226.255);
        ((GeneralPath) shape).lineTo(358.219, 226.255);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_339;
        g.setTransform(defaultTransform__0_339);
        g.setClip(clip__0_339);
        float alpha__0_340 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_340 = g.getClip();
        AffineTransform defaultTransform__0_340 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_340 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(363.454, 226.489);
        ((GeneralPath) shape).lineTo(363.454, 226.872);
        ((GeneralPath) shape).lineTo(360.579, 226.872);
        ((GeneralPath) shape).lineTo(360.579, 226.11899);
        ((GeneralPath) shape).curveTo(360.579, 225.69499, 360.661, 225.42198,
                360.825, 225.29);
        ((GeneralPath) shape).curveTo(360.989, 225.159, 361.38, 225.05899,
                361.992, 224.986);
        ((GeneralPath) shape).curveTo(362.483, 224.93199, 362.779, 224.857,
                362.884, 224.76299);
        ((GeneralPath) shape).curveTo(362.987, 224.66899, 363.035, 224.42598,
                363.035, 224.03398);
        ((GeneralPath) shape).curveTo(363.035, 223.68898, 362.978, 223.46698,
                362.864, 223.36298);
        ((GeneralPath) shape).curveTo(362.75302, 223.25998, 362.50403,
                223.21098, 362.117, 223.21098);
        ((GeneralPath) shape).curveTo(361.638, 223.21098, 361.337, 223.25198,
                361.211, 223.33598);
        ((GeneralPath) shape).curveTo(361.086, 223.41798, 361.023, 223.61899,
                361.023, 223.93498);
        ((GeneralPath) shape).lineTo(361.02902, 224.23398);
        ((GeneralPath) shape).lineTo(360.59003, 224.23398);
        ((GeneralPath) shape).lineTo(360.59302, 224.02599);
        ((GeneralPath) shape).curveTo(360.59302, 223.54799, 360.69403,
                223.22899, 360.89603, 223.06898);
        ((GeneralPath) shape).curveTo(361.09802, 222.91298, 361.50104,
                222.83199, 362.10803, 222.83199);
        ((GeneralPath) shape).curveTo(362.64603, 222.83199, 363.00702,
                222.91699, 363.19302, 223.08998);
        ((GeneralPath) shape).curveTo(363.38004, 223.26099, 363.47104,
                223.59698, 363.47104, 224.09499);
        ((GeneralPath) shape).curveTo(363.47104, 224.57298, 363.38403,
                224.89299, 363.21204, 225.05399);
        ((GeneralPath) shape).curveTo(363.03705, 225.21498, 362.66302,
                225.32399, 362.08704, 225.38098);
        ((GeneralPath) shape).curveTo(361.57904, 225.43098, 361.27603,
                225.50098, 361.18103, 225.59198);
        ((GeneralPath) shape).curveTo(361.08203, 225.68098, 361.03503,
                225.93597, 361.03503, 226.35698);
        ((GeneralPath) shape).lineTo(361.03503, 226.49197);
        ((GeneralPath) shape).lineTo(363.454, 226.489);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_340;
        g.setTransform(defaultTransform__0_340);
        g.setClip(clip__0_340);
        float alpha__0_341 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_341 = g.getClip();
        AffineTransform defaultTransform__0_341 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_341 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        stroke = new BasicStroke(1.0f, 0, 0, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(374.354, 227.448);
        ((GeneralPath) shape).curveTo(374.354, 228.076, 373.845, 228.584,
                373.21802, 228.584);
        ((GeneralPath) shape).lineTo(367.449, 228.584);
        ((GeneralPath) shape).curveTo(366.822, 228.584, 366.31302, 228.077,
                366.31302, 227.448);
        ((GeneralPath) shape).lineTo(366.31302, 221.623);
        ((GeneralPath) shape).curveTo(366.31302, 220.998, 366.82202, 220.487,
                367.449, 220.487);
        ((GeneralPath) shape).lineTo(373.21802, 220.487);
        ((GeneralPath) shape).curveTo(373.84503, 220.487, 374.354, 220.998,
                374.354, 221.623);
        ((GeneralPath) shape).lineTo(374.354, 227.448);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_341;
        g.setTransform(defaultTransform__0_341);
        g.setClip(clip__0_341);
        float alpha__0_342 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_342 = g.getClip();
        AffineTransform defaultTransform__0_342 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_342 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(368.298, 226.255);
        ((GeneralPath) shape).lineTo(368.298, 225.063);
        ((GeneralPath) shape).lineTo(367.117, 225.063);
        ((GeneralPath) shape).lineTo(367.117, 224.682);
        ((GeneralPath) shape).lineTo(368.298, 224.682);
        ((GeneralPath) shape).lineTo(368.298, 223.483);
        ((GeneralPath) shape).lineTo(368.705, 223.483);
        ((GeneralPath) shape).lineTo(368.705, 224.682);
        ((GeneralPath) shape).lineTo(369.885, 224.682);
        ((GeneralPath) shape).lineTo(369.885, 225.063);
        ((GeneralPath) shape).lineTo(368.705, 225.063);
        ((GeneralPath) shape).lineTo(368.705, 226.255);
        ((GeneralPath) shape).lineTo(368.298, 226.255);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_342;
        g.setTransform(defaultTransform__0_342);
        g.setClip(clip__0_342);
        float alpha__0_343 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_343 = g.getClip();
        AffineTransform defaultTransform__0_343 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_343 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(371.797, 225.016);
        ((GeneralPath) shape).lineTo(371.797, 224.632);
        ((GeneralPath) shape).lineTo(371.947, 224.63501);
        ((GeneralPath) shape).curveTo(372.485, 224.63501, 372.813, 224.60101,
                372.93298, 224.531);
        ((GeneralPath) shape).curveTo(373.05298, 224.466, 373.11298, 224.276,
                373.11298, 223.97101);
        ((GeneralPath) shape).curveTo(373.11298, 223.619, 373.06198, 223.406,
                372.95697, 223.326);
        ((GeneralPath) shape).curveTo(372.85297, 223.248, 372.56497, 223.209,
                372.09497, 223.209);
        ((GeneralPath) shape).curveTo(371.65897, 223.209, 371.39197, 223.247,
                371.28696, 223.321);
        ((GeneralPath) shape).curveTo(371.18497, 223.397, 371.13596, 223.596,
                371.13596, 223.924);
        ((GeneralPath) shape).lineTo(371.13596, 224.097);
        ((GeneralPath) shape).lineTo(370.70697, 224.097);
        ((GeneralPath) shape).lineTo(370.71097, 223.939);
        ((GeneralPath) shape).curveTo(370.71097, 223.482, 370.79996, 223.183,
                370.97897, 223.043);
        ((GeneralPath) shape).curveTo(371.15598, 222.9, 371.52997, 222.831,
                372.10098, 222.831);
        ((GeneralPath) shape).curveTo(372.688, 222.831, 373.076, 222.89499,
                373.26398, 223.027);
        ((GeneralPath) shape).curveTo(373.451, 223.161, 373.54297, 223.43,
                373.54297, 223.84299);
        ((GeneralPath) shape).curveTo(373.54297, 224.19199, 373.49997,
                224.43199, 373.40897, 224.56099);
        ((GeneralPath) shape).curveTo(373.31998, 224.68999, 373.13898,
                224.76999, 372.87097, 224.80399);
        ((GeneralPath) shape).lineTo(372.87097, 224.82498);
        ((GeneralPath) shape).curveTo(373.17398, 224.86497, 373.37198,
                224.94598, 373.47397, 225.06898);
        ((GeneralPath) shape).curveTo(373.57196, 225.19199, 373.61996,
                225.42299, 373.61996, 225.75699);
        ((GeneralPath) shape).curveTo(373.61996, 226.221, 373.52496, 226.52798,
                373.33197, 226.68298);
        ((GeneralPath) shape).curveTo(373.14297, 226.83498, 372.75598,
                226.91199, 372.17096, 226.91199);
        ((GeneralPath) shape).curveTo(371.54095, 226.91199, 371.13095,
                226.84198, 370.93597, 226.70099);
        ((GeneralPath) shape).curveTo(370.74097, 226.55798, 370.64297,
                226.26299, 370.64297, 225.805);
        ((GeneralPath) shape).lineTo(370.64297, 225.568);
        ((GeneralPath) shape).lineTo(371.08096, 225.568);
        ((GeneralPath) shape).lineTo(371.08096, 225.799);
        ((GeneralPath) shape).curveTo(371.08096, 226.13899, 371.14096, 226.345,
                371.25595, 226.41899);
        ((GeneralPath) shape).curveTo(371.36795, 226.49199, 371.69495, 226.527,
                372.23495, 226.527);
        ((GeneralPath) shape).curveTo(372.65195, 226.527, 372.91696, 226.482,
                373.02795, 226.38799);
        ((GeneralPath) shape).curveTo(373.13794, 226.29599, 373.19394,
                226.07498, 373.19394, 225.72098);
        ((GeneralPath) shape).curveTo(373.19394, 225.42697, 373.14093,
                225.23499, 373.03693, 225.14497);
        ((GeneralPath) shape).curveTo(372.93292, 225.05698, 372.7029,
                225.01198, 372.34894, 225.01198);
        ((GeneralPath) shape).lineTo(371.797, 225.016);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_343;
        g.setTransform(defaultTransform__0_343);
        g.setClip(clip__0_343);
        float alpha__0_344 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_344 = g.getClip();
        AffineTransform defaultTransform__0_344 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_344 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        stroke = new BasicStroke(1.0f, 0, 0, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(374.075, 132.613);
        ((GeneralPath) shape).lineTo(388.247, 132.613);
        ((GeneralPath) shape).moveTo(306.044, 132.613);
        ((GeneralPath) shape).lineTo(320.216, 132.613);
        ((GeneralPath) shape).moveTo(281.452, 119.857);
        ((GeneralPath) shape).lineTo(307.45898, 119.857);
        ((GeneralPath) shape).moveTo(295.654, 260.457);
        ((GeneralPath) shape).curveTo(295.654, 261.082, 295.146, 261.591,
                294.51898, 261.591);
        ((GeneralPath) shape).lineTo(288.75098, 261.591);
        ((GeneralPath) shape).curveTo(288.12396, 261.591, 287.61597, 261.082,
                287.61597, 260.457);
        ((GeneralPath) shape).lineTo(287.61597, 254.628);
        ((GeneralPath) shape).curveTo(287.61597, 254.003, 288.12396, 253.49501,
                288.75098, 253.49501);
        ((GeneralPath) shape).lineTo(294.51898, 253.49501);
        ((GeneralPath) shape).curveTo(295.146, 253.49501, 295.654, 254.003,
                295.654, 254.628);
        ((GeneralPath) shape).lineTo(295.654, 260.457);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_344;
        g.setTransform(defaultTransform__0_344);
        g.setClip(clip__0_344);
        float alpha__0_345 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_345 = g.getClip();
        AffineTransform defaultTransform__0_345 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_345 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(263.212, 257.208);
        ((GeneralPath) shape).lineTo(264.947, 257.208);
        ((GeneralPath) shape).curveTo(265.404, 257.208, 265.716, 257.14,
                265.883, 256.999);
        ((GeneralPath) shape).curveTo(266.04898, 256.858, 266.133, 256.598,
                266.133, 256.211);
        ((GeneralPath) shape).curveTo(266.133, 255.731, 266.073, 255.416,
                265.948, 255.27);
        ((GeneralPath) shape).curveTo(265.825, 255.125, 265.56, 255.052,
                265.155, 255.052);
        ((GeneralPath) shape).lineTo(263.213, 255.052);
        ((GeneralPath) shape).lineTo(263.212, 257.208);
        ((GeneralPath) shape).moveTo(262.612, 259.875);
        ((GeneralPath) shape).lineTo(262.612, 254.543);
        ((GeneralPath) shape).lineTo(265.146, 254.543);
        ((GeneralPath) shape).curveTo(265.715, 254.543, 266.121, 254.65399,
                266.354, 254.883);
        ((GeneralPath) shape).curveTo(266.585, 255.11, 266.70502, 255.502,
                266.70502, 256.063);
        ((GeneralPath) shape).curveTo(266.70502, 256.555, 266.64203, 256.897,
                266.51602, 257.093);
        ((GeneralPath) shape).curveTo(266.39, 257.28598, 266.14603, 257.41098,
                265.78702, 257.469);
        ((GeneralPath) shape).lineTo(265.78702, 257.481);
        ((GeneralPath) shape).curveTo(266.35, 257.52197, 266.634, 257.866,
                266.634, 258.512);
        ((GeneralPath) shape).lineTo(266.634, 259.875);
        ((GeneralPath) shape).lineTo(266.033, 259.875);
        ((GeneralPath) shape).lineTo(266.033, 258.646);
        ((GeneralPath) shape).curveTo(266.033, 258.025, 265.76398, 257.712,
                265.22598, 257.712);
        ((GeneralPath) shape).lineTo(263.21198, 257.712);
        ((GeneralPath) shape).lineTo(263.21198, 259.872);
        ((GeneralPath) shape).lineTo(262.612, 259.875);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_345;
        g.setTransform(defaultTransform__0_345);
        g.setClip(clip__0_345);
        float alpha__0_346 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_346 = g.getClip();
        AffineTransform defaultTransform__0_346 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_346 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(270.151, 257.684);
        ((GeneralPath) shape).lineTo(270.147, 257.508);
        ((GeneralPath) shape).curveTo(270.147, 257.106, 270.081, 256.842,
                269.948, 256.721);
        ((GeneralPath) shape).curveTo(269.816, 256.6, 269.531, 256.539, 269.09,
                256.539);
        ((GeneralPath) shape).curveTo(268.652, 256.539, 268.363, 256.607,
                268.233, 256.752);
        ((GeneralPath) shape).curveTo(268.102, 256.893, 268.036, 257.203,
                268.036, 257.68402);
        ((GeneralPath) shape).lineTo(270.151, 257.68402);
        ((GeneralPath) shape).moveTo(270.151, 258.747);
        ((GeneralPath) shape).lineTo(270.709, 258.747);
        ((GeneralPath) shape).lineTo(270.713, 258.883);
        ((GeneralPath) shape).curveTo(270.713, 259.27, 270.59802, 259.541,
                270.36002, 259.69598);
        ((GeneralPath) shape).curveTo(270.126, 259.84998, 269.713, 259.92798,
                269.122, 259.92798);
        ((GeneralPath) shape).curveTo(268.436, 259.92798, 267.98602, 259.80298,
                267.771, 259.55197);
        ((GeneralPath) shape).curveTo(267.556, 259.30197, 267.449, 258.77197,
                267.449, 257.96796);
        ((GeneralPath) shape).curveTo(267.449, 257.22397, 267.556, 256.72397,
                267.773, 256.46796);
        ((GeneralPath) shape).curveTo(267.987, 256.21396, 268.41, 256.08496,
                269.04, 256.08496);
        ((GeneralPath) shape).curveTo(269.728, 256.08496, 270.17502, 256.19196,
                270.392, 256.41696);
        ((GeneralPath) shape).curveTo(270.604, 256.63797, 270.70898, 257.10495,
                270.70898, 257.81396);
        ((GeneralPath) shape).lineTo(270.70898, 258.10596);
        ((GeneralPath) shape).lineTo(268.02597, 258.10596);
        ((GeneralPath) shape).curveTo(268.02597, 258.69397, 268.08896,
                259.06897, 268.21396, 259.23096);
        ((GeneralPath) shape).curveTo(268.33896, 259.39096, 268.63297,
                259.47296, 269.09897, 259.47296);
        ((GeneralPath) shape).curveTo(269.53696, 259.47296, 269.82297,
                259.43597, 269.95297, 259.35797);
        ((GeneralPath) shape).curveTo(270.08398, 259.28296, 270.14996,
                259.11798, 270.14996, 258.86);
        ((GeneralPath) shape).lineTo(270.151, 258.747);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_346;
        g.setTransform(defaultTransform__0_346);
        g.setClip(clip__0_346);
        float alpha__0_347 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_347 = g.getClip();
        AffineTransform defaultTransform__0_347 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_347 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(273.014, 258.028);
        ((GeneralPath) shape).curveTo(272.59702, 258.028, 272.324, 258.07,
                272.19702, 258.16302);
        ((GeneralPath) shape).curveTo(272.07202, 258.25104, 272.00803,
                258.43802, 272.00803, 258.73102);
        ((GeneralPath) shape).curveTo(272.00803, 259.031, 272.071, 259.23303,
                272.196, 259.33203);
        ((GeneralPath) shape).curveTo(272.321, 259.43002, 272.574, 259.47803,
                272.95602, 259.47803);
        ((GeneralPath) shape).curveTo(273.726, 259.47803, 274.11002, 259.24603,
                274.11002, 258.77502);
        ((GeneralPath) shape).curveTo(274.11002, 258.48303, 274.036, 258.28302,
                273.885, 258.18103);
        ((GeneralPath) shape).curveTo(273.737, 258.078, 273.446, 258.028,
                273.014, 258.028);
        ((GeneralPath) shape).moveTo(272.137, 257.188);
        ((GeneralPath) shape).lineTo(271.595, 257.188);
        ((GeneralPath) shape).curveTo(271.595, 256.754, 271.693, 256.461,
                271.889, 256.31198);
        ((GeneralPath) shape).curveTo(272.082, 256.166, 272.46802, 256.08698,
                273.039, 256.08698);
        ((GeneralPath) shape).curveTo(273.659, 256.08698, 274.08, 256.179,
                274.298, 256.36);
        ((GeneralPath) shape).curveTo(274.518, 256.546, 274.625, 256.89297,
                274.625, 257.41098);
        ((GeneralPath) shape).lineTo(274.625, 259.87497);
        ((GeneralPath) shape).lineTo(274.08, 259.87497);
        ((GeneralPath) shape).lineTo(274.12198, 259.47296);
        ((GeneralPath) shape).lineTo(274.11, 259.46896);
        ((GeneralPath) shape).curveTo(273.90298, 259.77597, 273.481, 259.92996,
                272.843, 259.92996);
        ((GeneralPath) shape).curveTo(271.90298, 259.92996, 271.431, 259.55197,
                271.431, 258.79297);
        ((GeneralPath) shape).curveTo(271.431, 258.34396, 271.535, 258.02896,
                271.745, 257.85696);
        ((GeneralPath) shape).curveTo(271.955, 257.68497, 272.335, 257.59897,
                272.889, 257.59897);
        ((GeneralPath) shape).curveTo(273.54202, 257.59897, 273.936, 257.72797,
                274.066, 257.98596);
        ((GeneralPath) shape).lineTo(274.078, 257.98196);
        ((GeneralPath) shape).lineTo(274.078, 257.52896);
        ((GeneralPath) shape).curveTo(274.078, 257.10397, 274.019, 256.82797,
                273.902, 256.69995);
        ((GeneralPath) shape).curveTo(273.785, 256.57495, 273.53, 256.51196,
                273.134, 256.51196);
        ((GeneralPath) shape).curveTo(272.462, 256.51196, 272.126, 256.69897,
                272.126, 257.07797);
        ((GeneralPath) shape).curveTo(272.132, 257.096, 272.132, 257.133,
                272.137, 257.188);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_347;
        g.setTransform(defaultTransform__0_347);
        g.setClip(clip__0_347);
        float alpha__0_348 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_348 = g.getClip();
        AffineTransform defaultTransform__0_348 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_348 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(275.694, 256.141);
        ((GeneralPath) shape).lineTo(276.24, 256.141);
        ((GeneralPath) shape).lineTo(276.185, 256.57098);
        ((GeneralPath) shape).lineTo(276.197, 256.585);
        ((GeneralPath) shape).curveTo(276.41098, 256.231, 276.768, 256.05698,
                277.26498, 256.05698);
        ((GeneralPath) shape).curveTo(277.951, 256.05698, 278.292, 256.41098,
                278.292, 257.11996);
        ((GeneralPath) shape).lineTo(278.289, 257.37897);
        ((GeneralPath) shape).lineTo(277.75, 257.37897);
        ((GeneralPath) shape).lineTo(277.764, 257.28296);
        ((GeneralPath) shape).curveTo(277.772, 257.18497, 277.776, 257.11996,
                277.776, 257.08597);
        ((GeneralPath) shape).curveTo(277.776, 256.70096, 277.569, 256.51096,
                277.152, 256.51096);
        ((GeneralPath) shape).curveTo(276.544, 256.51096, 276.24002, 256.88596,
                276.24002, 257.63995);
        ((GeneralPath) shape).lineTo(276.24002, 259.87796);
        ((GeneralPath) shape).lineTo(275.69403, 259.87796);
        ((GeneralPath) shape).lineTo(275.69403, 256.141);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_348;
        g.setTransform(defaultTransform__0_348);
        g.setClip(clip__0_348);
        float alpha__0_349 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_349 = g.getClip();
        AffineTransform defaultTransform__0_349 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_349 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(260.19, 116.538);
        ((GeneralPath) shape).lineTo(261.203, 116.538);
        ((GeneralPath) shape).lineTo(261.203, 116.722);
        ((GeneralPath) shape).curveTo(261.203, 117.464, 261.069, 117.943,
                260.798, 118.161);
        ((GeneralPath) shape).curveTo(260.529, 118.378006, 259.932, 118.485,
                259.005, 118.485);
        ((GeneralPath) shape).curveTo(257.956, 118.485, 257.311, 118.313,
                257.06702, 117.969);
        ((GeneralPath) shape).curveTo(256.82303, 117.625, 256.704, 116.707,
                256.704, 115.211);
        ((GeneralPath) shape).curveTo(256.704, 114.332, 256.868, 113.752,
                257.196, 113.475);
        ((GeneralPath) shape).curveTo(257.523, 113.2, 258.207, 113.059, 259.25,
                113.059);
        ((GeneralPath) shape).curveTo(260.008, 113.059, 260.517, 113.172,
                260.771, 113.401);
        ((GeneralPath) shape).curveTo(261.024, 113.63, 261.15298, 114.083,
                261.15298, 114.758);
        ((GeneralPath) shape).lineTo(261.15698, 114.879005);
        ((GeneralPath) shape).lineTo(260.14297, 114.879005);
        ((GeneralPath) shape).lineTo(260.14297, 114.742004);
        ((GeneralPath) shape).curveTo(260.14297, 114.392006, 260.07898,
                114.170006, 259.94598, 114.07001);
        ((GeneralPath) shape).curveTo(259.81598, 113.97201, 259.51498, 113.924,
                259.04797, 113.924);
        ((GeneralPath) shape).curveTo(258.42297, 113.924, 258.04596, 114.0,
                257.91898, 114.153);
        ((GeneralPath) shape).curveTo(257.79398, 114.304, 257.731, 114.761,
                257.731, 115.519);
        ((GeneralPath) shape).curveTo(257.731, 116.54, 257.788, 117.144,
                257.901, 117.332);
        ((GeneralPath) shape).curveTo(258.014, 117.520004, 258.379, 117.613,
                258.998, 117.613);
        ((GeneralPath) shape).curveTo(259.499, 117.613, 259.82397, 117.56,
                259.977, 117.457);
        ((GeneralPath) shape).curveTo(260.123, 117.353004, 260.201, 117.125,
                260.201, 116.769);
        ((GeneralPath) shape).lineTo(260.19, 116.538);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_349;
        g.setTransform(defaultTransform__0_349);
        g.setClip(clip__0_349);
        float alpha__0_350 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_350 = g.getClip();
        AffineTransform defaultTransform__0_350 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_350 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(261.831, 114.706);
        ((GeneralPath) shape).lineTo(262.72598, 114.706);
        ((GeneralPath) shape).lineTo(262.66898, 115.227005);
        ((GeneralPath) shape).lineTo(262.68997, 115.231);
        ((GeneralPath) shape).curveTo(262.90298, 114.840004, 263.23798,
                114.645004, 263.69598, 114.645004);
        ((GeneralPath) shape).curveTo(264.34998, 114.645004, 264.67798,
                115.059006, 264.67798, 115.887);
        ((GeneralPath) shape).lineTo(264.67798, 116.149);
        ((GeneralPath) shape).lineTo(263.83298, 116.149);
        ((GeneralPath) shape).curveTo(263.84497, 116.047005, 263.85098,
                115.981, 263.85098, 115.952);
        ((GeneralPath) shape).curveTo(263.85098, 115.554, 263.697, 115.352005,
                263.38797, 115.352005);
        ((GeneralPath) shape).curveTo(262.94797, 115.352005, 262.72498,
                115.647, 262.72498, 116.241005);
        ((GeneralPath) shape).lineTo(262.72498, 118.438);
        ((GeneralPath) shape).lineTo(261.83, 118.438);
        ((GeneralPath) shape).lineTo(261.83, 114.706);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_350;
        g.setTransform(defaultTransform__0_350);
        g.setClip(clip__0_350);
        float alpha__0_351 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_351 = g.getClip();
        AffineTransform defaultTransform__0_351 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_351 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(267.575, 116.187);
        ((GeneralPath) shape).lineTo(267.571, 116.03999);
        ((GeneralPath) shape).curveTo(267.571, 115.743996, 267.52002,
                115.551994, 267.41702, 115.46199);
        ((GeneralPath) shape).curveTo(267.316, 115.37499, 267.092, 115.32999,
                266.74902, 115.32999);
        ((GeneralPath) shape).curveTo(266.41702, 115.32999, 266.20102,
                115.38299, 266.10202, 115.48798);
        ((GeneralPath) shape).curveTo(266.00003, 115.59299, 265.95203,
                115.82999, 265.95203, 116.18698);
        ((GeneralPath) shape).lineTo(267.575, 116.18698);
        ((GeneralPath) shape).moveTo(267.566, 117.245);
        ((GeneralPath) shape).lineTo(268.462, 117.245);
        ((GeneralPath) shape).lineTo(268.462, 117.389);
        ((GeneralPath) shape).curveTo(268.462, 118.118, 267.91602, 118.483,
                266.825, 118.483);
        ((GeneralPath) shape).curveTo(266.084, 118.483, 265.6, 118.358,
                265.371, 118.105);
        ((GeneralPath) shape).curveTo(265.143, 117.853004, 265.028, 117.318,
                265.028, 116.50101);
        ((GeneralPath) shape).curveTo(265.028, 115.77301, 265.147, 115.28701,
                265.38702, 115.03701);
        ((GeneralPath) shape).curveTo(265.62302, 114.78701, 266.09302,
                114.66201, 266.78403, 114.66201);
        ((GeneralPath) shape).curveTo(267.44904, 114.66201, 267.89502,
                114.78101, 268.12103, 115.02501);
        ((GeneralPath) shape).curveTo(268.35004, 115.267006, 268.46002,
                115.74801, 268.46002, 116.462006);
        ((GeneralPath) shape).lineTo(268.46002, 116.73401);
        ((GeneralPath) shape).lineTo(265.93903, 116.73401);
        ((GeneralPath) shape).curveTo(265.93503, 116.81601, 265.93103,
                116.87301, 265.93103, 116.89801);
        ((GeneralPath) shape).curveTo(265.93103, 117.26501, 265.98804,
                117.50901, 266.10104, 117.63201);
        ((GeneralPath) shape).curveTo(266.21204, 117.75301, 266.43805,
                117.81601, 266.77704, 117.81601);
        ((GeneralPath) shape).curveTo(267.10504, 117.81601, 267.31705,
                117.78001, 267.41605, 117.711006);
        ((GeneralPath) shape).curveTo(267.516, 117.638, 267.566, 117.483,
                267.566, 117.245);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_351;
        g.setTransform(defaultTransform__0_351);
        g.setClip(clip__0_351);
        float alpha__0_352 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_352 = g.getClip();
        AffineTransform defaultTransform__0_352 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_352 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(274.422, 114.706);
        ((GeneralPath) shape).lineTo(273.58798, 118.438);
        ((GeneralPath) shape).lineTo(272.27298, 118.438);
        ((GeneralPath) shape).lineTo(271.89798, 116.76601);
        ((GeneralPath) shape).curveTo(271.83398, 116.477005, 271.78497,
                116.258, 271.75198, 116.106);
        ((GeneralPath) shape).lineTo(271.688, 115.777);
        ((GeneralPath) shape).lineTo(271.62198, 115.451004);
        ((GeneralPath) shape).lineTo(271.59497, 115.451004);
        ((GeneralPath) shape).lineTo(271.53098, 115.777);
        ((GeneralPath) shape).lineTo(271.46497, 116.11);
        ((GeneralPath) shape).curveTo(271.43997, 116.256004, 271.39197,
                116.473, 271.32397, 116.766);
        ((GeneralPath) shape).lineTo(270.94998, 118.437996);
        ((GeneralPath) shape).lineTo(269.61298, 118.437996);
        ((GeneralPath) shape).lineTo(268.783, 114.70599);
        ((GeneralPath) shape).lineTo(269.68298, 114.70599);
        ((GeneralPath) shape).lineTo(270.03998, 116.43499);
        ((GeneralPath) shape).curveTo(270.08197, 116.64999, 270.12698,
                116.88199, 270.17197, 117.12999);
        ((GeneralPath) shape).lineTo(270.23495, 117.47799);
        ((GeneralPath) shape).lineTo(270.29895, 117.82799);
        ((GeneralPath) shape).lineTo(270.31696, 117.82799);
        ((GeneralPath) shape).lineTo(270.39496, 117.47799);
        ((GeneralPath) shape).lineTo(270.46695, 117.12999);
        ((GeneralPath) shape).curveTo(270.50595, 116.955986, 270.55994,
                116.72599, 270.62695, 116.43899);
        ((GeneralPath) shape).lineTo(271.03296, 114.706985);
        ((GeneralPath) shape).lineTo(272.17096, 114.706985);
        ((GeneralPath) shape).lineTo(272.57596, 116.43899);
        ((GeneralPath) shape).curveTo(272.64996, 116.762985, 272.70395,
                116.99599, 272.73596, 117.12999);
        ((GeneralPath) shape).lineTo(272.80997, 117.47799);
        ((GeneralPath) shape).lineTo(272.88196, 117.82799);
        ((GeneralPath) shape).lineTo(272.90295, 117.82799);
        ((GeneralPath) shape).lineTo(272.96896, 117.47799);
        ((GeneralPath) shape).lineTo(273.03195, 117.12999);
        ((GeneralPath) shape).curveTo(273.07394, 116.89199, 273.12094,
                116.65899, 273.16794, 116.43499);
        ((GeneralPath) shape).lineTo(273.53094, 114.70599);
        ((GeneralPath) shape).lineTo(274.422, 114.70599);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_352;
        g.setTransform(defaultTransform__0_352);
        g.setClip(clip__0_352);
        float alpha__0_353 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_353 = g.getClip();
        AffineTransform defaultTransform__0_353 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        paint2(g, clip_, defaultTransform_, alpha__0, clip__0,
                defaultTransform__0, alpha__0_353, clip__0_353,
                defaultTransform__0_353);
    }

    private static void paint2(Graphics2D g, Shape clip_,
            AffineTransform defaultTransform_, float alpha__0, Shape clip__0,
            AffineTransform defaultTransform__0, float alpha__0_353,
            Shape clip__0_353, AffineTransform defaultTransform__0_353) {
        Shape shape;
        Paint paint;
        Stroke stroke;
        Area clip;
        float origAlpha;
        // _0_353 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(275.721, 114.706);
        ((GeneralPath) shape).lineTo(275.721, 115.667);
        ((GeneralPath) shape).lineTo(274.82602, 115.667);
        ((GeneralPath) shape).lineTo(274.82602, 114.706);
        ((GeneralPath) shape).lineTo(275.721, 114.706);
        ((GeneralPath) shape).moveTo(275.721, 117.479);
        ((GeneralPath) shape).lineTo(275.721, 118.439995);
        ((GeneralPath) shape).lineTo(274.82602, 118.439995);
        ((GeneralPath) shape).lineTo(274.82602, 117.479);
        ((GeneralPath) shape).lineTo(275.721, 117.479);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_353;
        g.setTransform(defaultTransform__0_353);
        g.setClip(clip__0_353);
        float alpha__0_354 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_354 = g.getClip();
        AffineTransform defaultTransform__0_354 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_354 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(258.997, 128.356);
        ((GeneralPath) shape).lineTo(261.34, 128.356);
        ((GeneralPath) shape).lineTo(261.357, 129.417);
        ((GeneralPath) shape).curveTo(261.357, 130.18701, 261.211, 130.68501,
                260.91998, 130.90501);
        ((GeneralPath) shape).curveTo(260.63098, 131.13002, 259.98398,
                131.23901, 258.982, 131.23901);
        ((GeneralPath) shape).curveTo(258.06198, 131.23901, 257.453, 131.09302,
                257.15298, 130.79602);
        ((GeneralPath) shape).curveTo(256.85498, 130.49902, 256.70398,
                129.89403, 256.70398, 128.98003);
        ((GeneralPath) shape).curveTo(256.70398, 127.81303, 256.76498,
                127.079025, 256.88297, 126.76903);
        ((GeneralPath) shape).curveTo(257.03098, 126.39403, 257.25595,
                126.14203, 257.55997, 126.012024);
        ((GeneralPath) shape).curveTo(257.85995, 125.883026, 258.37296,
                125.817024, 259.09396, 125.817024);
        ((GeneralPath) shape).curveTo(260.03897, 125.817024, 260.65097,
                125.91802, 260.92596, 126.121025);
        ((GeneralPath) shape).curveTo(261.20096, 126.32002, 261.33997,
                126.76703, 261.33997, 127.46303);
        ((GeneralPath) shape).lineTo(260.31897, 127.46303);
        ((GeneralPath) shape).curveTo(260.29898, 127.11303, 260.22498,
                126.899025, 260.08997, 126.81303);
        ((GeneralPath) shape).curveTo(259.95697, 126.72903, 259.61996,
                126.68603, 259.08197, 126.68603);
        ((GeneralPath) shape).curveTo(258.49698, 126.68603, 258.12698,
                126.759026, 257.97098, 126.90503);
        ((GeneralPath) shape).curveTo(257.81497, 127.05103, 257.735, 127.39303,
                257.735, 127.93203);
        ((GeneralPath) shape).lineTo(257.731, 128.46703);
        ((GeneralPath) shape).lineTo(257.73898, 129.15103);
        ((GeneralPath) shape).curveTo(257.73898, 129.67903, 257.817, 130.01703,
                257.973, 130.16203);
        ((GeneralPath) shape).curveTo(258.12698, 130.30803, 258.49, 130.38004,
                259.057, 130.38004);
        ((GeneralPath) shape).curveTo(259.607, 130.38004, 259.956, 130.31703,
                260.108, 130.19504);
        ((GeneralPath) shape).curveTo(260.254, 130.07304, 260.332, 129.78404,
                260.332, 129.32803);
        ((GeneralPath) shape).lineTo(260.336, 129.10904);
        ((GeneralPath) shape).lineTo(258.999, 129.10904);
        ((GeneralPath) shape).lineTo(258.997, 128.356);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_354;
        g.setTransform(defaultTransform__0_354);
        g.setClip(clip__0_354);
        float alpha__0_355 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_355 = g.getClip();
        AffineTransform defaultTransform__0_355 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_355 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(265.344, 127.458);
        ((GeneralPath) shape).lineTo(265.344, 131.19);
        ((GeneralPath) shape).lineTo(264.449, 131.19);
        ((GeneralPath) shape).lineTo(264.5, 130.549);
        ((GeneralPath) shape).lineTo(264.484, 130.54599);
        ((GeneralPath) shape).curveTo(264.311, 131.00299, 263.911, 131.23299,
                263.28302, 131.23299);
        ((GeneralPath) shape).curveTo(262.43903, 131.23299, 262.01602,
                130.81198, 262.01602, 129.96298);
        ((GeneralPath) shape).lineTo(262.01602, 127.45498);
        ((GeneralPath) shape).lineTo(262.90903, 127.45498);
        ((GeneralPath) shape).lineTo(262.90903, 129.74799);
        ((GeneralPath) shape).curveTo(262.90903, 130.06198, 262.95203,
                130.27199, 263.04202, 130.37498);
        ((GeneralPath) shape).curveTo(263.12903, 130.47499, 263.31302,
                130.52298, 263.592, 130.52298);
        ((GeneralPath) shape).curveTo(264.16202, 130.52298, 264.449, 130.17899,
                264.449, 129.49397);
        ((GeneralPath) shape).lineTo(264.449, 127.45497);
        ((GeneralPath) shape).lineTo(265.344, 127.458);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_355;
        g.setTransform(defaultTransform__0_355);
        g.setClip(clip__0_355);
        float alpha__0_356 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_356 = g.getClip();
        AffineTransform defaultTransform__0_356 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_356 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(266.25, 127.458);
        ((GeneralPath) shape).lineTo(267.137, 127.458);
        ((GeneralPath) shape).lineTo(267.102, 128.087);
        ((GeneralPath) shape).lineTo(267.12198, 128.091);
        ((GeneralPath) shape).curveTo(267.29498, 127.642006, 267.68497,
                127.415, 268.291, 127.415);
        ((GeneralPath) shape).curveTo(269.172, 127.415, 269.612, 127.825005,
                269.612, 128.648);
        ((GeneralPath) shape).lineTo(269.612, 131.19);
        ((GeneralPath) shape).lineTo(268.717, 131.19);
        ((GeneralPath) shape).lineTo(268.717, 128.8);
        ((GeneralPath) shape).lineTo(268.69702, 128.53801);
        ((GeneralPath) shape).curveTo(268.65604, 128.26102, 268.43802,
                128.12001, 268.04202, 128.12001);
        ((GeneralPath) shape).curveTo(267.44202, 128.12001, 267.144, 128.40302,
                267.144, 128.97401);
        ((GeneralPath) shape).lineTo(267.144, 131.18802);
        ((GeneralPath) shape).lineTo(266.24902, 131.18802);
        ((GeneralPath) shape).lineTo(266.25, 127.458);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_356;
        g.setTransform(defaultTransform__0_356);
        g.setClip(clip__0_356);
        float alpha__0_357 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_357 = g.getClip();
        AffineTransform defaultTransform__0_357 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_357 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(270.481, 127.458);
        ((GeneralPath) shape).lineTo(271.366, 127.458);
        ((GeneralPath) shape).lineTo(271.331, 128.087);
        ((GeneralPath) shape).lineTo(271.352, 128.091);
        ((GeneralPath) shape).curveTo(271.526, 127.642006, 271.91498, 127.415,
                272.522, 127.415);
        ((GeneralPath) shape).curveTo(273.403, 127.415, 273.841, 127.825005,
                273.841, 128.648);
        ((GeneralPath) shape).lineTo(273.841, 131.19);
        ((GeneralPath) shape).lineTo(272.948, 131.19);
        ((GeneralPath) shape).lineTo(272.948, 128.8);
        ((GeneralPath) shape).lineTo(272.928, 128.53801);
        ((GeneralPath) shape).curveTo(272.88702, 128.26102, 272.66702,
                128.12001, 272.273, 128.12001);
        ((GeneralPath) shape).curveTo(271.67102, 128.12001, 271.37302,
                128.40302, 271.37302, 128.97401);
        ((GeneralPath) shape).lineTo(271.37302, 131.18802);
        ((GeneralPath) shape).lineTo(270.48, 131.18802);
        ((GeneralPath) shape).lineTo(270.48, 127.458);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_357;
        g.setTransform(defaultTransform__0_357);
        g.setClip(clip__0_357);
        float alpha__0_358 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_358 = g.getClip();
        AffineTransform defaultTransform__0_358 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_358 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(277.122, 128.939);
        ((GeneralPath) shape).lineTo(277.118, 128.79199);
        ((GeneralPath) shape).curveTo(277.118, 128.49599, 277.06702, 128.30399,
                276.96402, 128.21399);
        ((GeneralPath) shape).curveTo(276.863, 128.12599, 276.64102, 128.08199,
                276.29602, 128.08199);
        ((GeneralPath) shape).curveTo(275.96402, 128.08199, 275.74802,
                128.13399, 275.64902, 128.23999);
        ((GeneralPath) shape).curveTo(275.548, 128.346, 275.49902, 128.58199,
                275.49902, 128.93999);
        ((GeneralPath) shape).lineTo(277.122, 128.939);
        ((GeneralPath) shape).moveTo(277.114, 129.997);
        ((GeneralPath) shape).lineTo(278.01102, 129.997);
        ((GeneralPath) shape).lineTo(278.01102, 130.142);
        ((GeneralPath) shape).curveTo(278.01102, 130.871, 277.463, 131.236,
                276.371, 131.236);
        ((GeneralPath) shape).curveTo(275.63, 131.236, 275.147, 131.111,
                274.917, 130.857);
        ((GeneralPath) shape).curveTo(274.689, 130.60599, 274.574, 130.06999,
                274.574, 129.25299);
        ((GeneralPath) shape).curveTo(274.574, 128.525, 274.693, 128.038,
                274.933, 127.787994);
        ((GeneralPath) shape).curveTo(275.169, 127.537994, 275.639, 127.412994,
                276.33102, 127.412994);
        ((GeneralPath) shape).curveTo(276.99603, 127.412994, 277.44202,
                127.534, 277.66803, 127.77699);
        ((GeneralPath) shape).curveTo(277.89603, 128.019, 278.00903, 128.499,
                278.00903, 129.21399);
        ((GeneralPath) shape).lineTo(278.00903, 129.48499);
        ((GeneralPath) shape).lineTo(275.48703, 129.48499);
        ((GeneralPath) shape).curveTo(275.48303, 129.56699, 275.47903, 129.624,
                275.47903, 129.64998);
        ((GeneralPath) shape).curveTo(275.47903, 130.01698, 275.53503,
                130.26097, 275.64804, 130.38397);
        ((GeneralPath) shape).curveTo(275.76105, 130.50497, 275.98505,
                130.56697, 276.32404, 130.56697);
        ((GeneralPath) shape).curveTo(276.65204, 130.56697, 276.86404,
                130.53197, 276.96304, 130.46198);
        ((GeneralPath) shape).curveTo(277.063, 130.391, 277.114, 130.235,
                277.114, 129.997);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_358;
        g.setTransform(defaultTransform__0_358);
        g.setClip(clip__0_358);
        float alpha__0_359 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_359 = g.getClip();
        AffineTransform defaultTransform__0_359 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_359 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(278.776, 127.458);
        ((GeneralPath) shape).lineTo(279.669, 127.458);
        ((GeneralPath) shape).lineTo(279.61502, 127.979004);
        ((GeneralPath) shape).lineTo(279.63602, 127.983);
        ((GeneralPath) shape).curveTo(279.84903, 127.593, 280.18402, 127.398,
                280.64203, 127.398);
        ((GeneralPath) shape).curveTo(281.29404, 127.398, 281.62204,
                127.812004, 281.62204, 128.64);
        ((GeneralPath) shape).lineTo(281.62204, 128.902);
        ((GeneralPath) shape).lineTo(280.78003, 128.902);
        ((GeneralPath) shape).curveTo(280.79004, 128.79999, 280.79504, 128.734,
                280.79504, 128.70499);
        ((GeneralPath) shape).curveTo(280.79504, 128.30598, 280.64404,
                128.10498, 280.33203, 128.10498);
        ((GeneralPath) shape).curveTo(279.89404, 128.10498, 279.66904,
                128.39998, 279.66904, 128.99399);
        ((GeneralPath) shape).lineTo(279.66904, 131.191);
        ((GeneralPath) shape).lineTo(278.77603, 131.191);
        ((GeneralPath) shape).lineTo(278.77603, 127.458);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_359;
        g.setTransform(defaultTransform__0_359);
        g.setClip(clip__0_359);
        float alpha__0_360 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_360 = g.getClip();
        AffineTransform defaultTransform__0_360 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_360 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(285.182, 127.458);
        ((GeneralPath) shape).lineTo(284.203, 131.427);
        ((GeneralPath) shape).curveTo(284.066, 131.99, 283.895, 132.365,
                283.69, 132.562);
        ((GeneralPath) shape).curveTo(283.487, 132.75499, 283.16, 132.854,
                282.71, 132.854);
        ((GeneralPath) shape).curveTo(282.608, 132.854, 282.503, 132.85,
                282.396, 132.838);
        ((GeneralPath) shape).lineTo(282.396, 132.178);
        ((GeneralPath) shape).curveTo(282.474, 132.18199, 282.539, 132.18599,
                282.589, 132.18599);
        ((GeneralPath) shape).curveTo(282.96698, 132.18599, 283.20898,
                131.85599, 283.31198, 131.19398);
        ((GeneralPath) shape).lineTo(282.856, 131.19398);
        ((GeneralPath) shape).lineTo(281.634, 127.46198);
        ((GeneralPath) shape).lineTo(282.594, 127.46198);
        ((GeneralPath) shape).lineTo(283.063, 129.04398);
        ((GeneralPath) shape).lineTo(283.29398, 129.83698);
        ((GeneralPath) shape).curveTo(283.30798, 129.88797, 283.34396,
                130.02098, 283.40298, 130.23297);
        ((GeneralPath) shape).lineTo(283.516, 130.62897);
        ((GeneralPath) shape).lineTo(283.53598, 130.62897);
        ((GeneralPath) shape).lineTo(283.61798, 130.23297);
        ((GeneralPath) shape).curveTo(283.65897, 130.02997, 283.68597,
                129.89697, 283.69998, 129.83698);
        ((GeneralPath) shape).lineTo(283.883, 129.04398);
        ((GeneralPath) shape).lineTo(284.241, 127.461975);
        ((GeneralPath) shape).lineTo(285.182, 127.461975);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_360;
        g.setTransform(defaultTransform__0_360);
        g.setClip(clip__0_360);
        float alpha__0_361 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_361 = g.getClip();
        AffineTransform defaultTransform__0_361 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_361 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(291.56, 127.415);
        ((GeneralPath) shape).lineTo(290.574, 127.415);
        ((GeneralPath) shape).curveTo(290.57, 127.364, 290.566, 127.33,
                290.566, 127.306);
        ((GeneralPath) shape).curveTo(290.543, 127.006996, 290.476, 126.82,
                290.367, 126.743);
        ((GeneralPath) shape).curveTo(290.258, 126.66599, 290.0, 126.628,
                289.596, 126.628);
        ((GeneralPath) shape).curveTo(289.117, 126.628, 288.806, 126.671,
                288.65802, 126.761);
        ((GeneralPath) shape).curveTo(288.51202, 126.849, 288.43802,
                127.034004, 288.43802, 127.319);
        ((GeneralPath) shape).curveTo(288.43802, 127.655, 288.497, 127.857,
                288.618, 127.925);
        ((GeneralPath) shape).curveTo(288.737, 127.989006, 289.13202, 128.044,
                289.80502, 128.08101);
        ((GeneralPath) shape).curveTo(290.59702, 128.12302, 291.109, 128.23502,
                291.34103, 128.42001);
        ((GeneralPath) shape).curveTo(291.57303, 128.60202, 291.69205,
                128.98102, 291.69205, 129.557);
        ((GeneralPath) shape).curveTo(291.69205, 130.264, 291.55505, 130.72401,
                291.28204, 130.93001);
        ((GeneralPath) shape).curveTo(291.01004, 131.138, 290.40906, 131.242,
                289.47705, 131.242);
        ((GeneralPath) shape).curveTo(288.64105, 131.242, 288.08304, 131.139,
                287.81006, 130.936);
        ((GeneralPath) shape).curveTo(287.53708, 130.733, 287.39807, 130.323,
                287.39807, 129.70401);
        ((GeneralPath) shape).lineTo(287.39508, 129.509);
        ((GeneralPath) shape).lineTo(288.3751, 129.509);
        ((GeneralPath) shape).lineTo(288.37808, 129.623);
        ((GeneralPath) shape).curveTo(288.37808, 129.994, 288.44208, 130.222,
                288.5731, 130.306);
        ((GeneralPath) shape).curveTo(288.7021, 130.38899, 289.0561, 130.431,
                289.6371, 130.431);
        ((GeneralPath) shape).curveTo(290.08807, 130.431, 290.3761, 130.384,
                290.5011, 130.286);
        ((GeneralPath) shape).curveTo(290.6261, 130.18799, 290.6891, 129.968,
                290.6891, 129.616);
        ((GeneralPath) shape).curveTo(290.6891, 129.358, 290.6421, 129.188,
                290.54507, 129.10199);
        ((GeneralPath) shape).curveTo(290.45108, 129.01799, 290.24707, 128.967,
                289.93307, 128.94798);
        ((GeneralPath) shape).lineTo(289.37308, 128.91498);
        ((GeneralPath) shape).curveTo(288.53107, 128.86497, 287.99307,
                128.74898, 287.76108, 128.56097);
        ((GeneralPath) shape).curveTo(287.52707, 128.37697, 287.40808,
                127.97897, 287.40808, 127.37197);
        ((GeneralPath) shape).curveTo(287.40808, 126.74997, 287.54907,
                126.33497, 287.8311, 126.12697);
        ((GeneralPath) shape).curveTo(288.1121, 125.91997, 288.6761, 125.81497,
                289.5211, 125.81497);
        ((GeneralPath) shape).curveTo(290.3201, 125.81497, 290.86008,
                125.907974, 291.1391, 126.099976);
        ((GeneralPath) shape).curveTo(291.4141, 126.28898, 291.5561, 126.66298,
                291.5561, 127.212975);
        ((GeneralPath) shape).lineTo(291.5561, 127.41598);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_361;
        g.setTransform(defaultTransform__0_361);
        g.setClip(clip__0_361);
        float alpha__0_362 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_362 = g.getClip();
        AffineTransform defaultTransform__0_362 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_362 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(293.258, 125.86);
        ((GeneralPath) shape).lineTo(293.258, 128.911);
        ((GeneralPath) shape).lineTo(293.488, 128.911);
        ((GeneralPath) shape).lineTo(294.494, 127.458);
        ((GeneralPath) shape).lineTo(295.531, 127.458);
        ((GeneralPath) shape).lineTo(294.233, 129.216);
        ((GeneralPath) shape).lineTo(295.796, 131.192);
        ((GeneralPath) shape).lineTo(294.689, 131.192);
        ((GeneralPath) shape).lineTo(293.477, 129.536);
        ((GeneralPath) shape).lineTo(293.258, 129.536);
        ((GeneralPath) shape).lineTo(293.258, 131.192);
        ((GeneralPath) shape).lineTo(292.366, 131.192);
        ((GeneralPath) shape).lineTo(292.366, 125.86);
        ((GeneralPath) shape).lineTo(293.258, 125.86);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_362;
        g.setTransform(defaultTransform__0_362);
        g.setClip(clip__0_362);
        float alpha__0_363 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_363 = g.getClip();
        AffineTransform defaultTransform__0_363 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_363 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(297.09, 127.458);
        ((GeneralPath) shape).lineTo(297.09, 131.19);
        ((GeneralPath) shape).lineTo(296.197, 131.19);
        ((GeneralPath) shape).lineTo(296.197, 127.458);
        ((GeneralPath) shape).lineTo(297.09, 127.458);
        ((GeneralPath) shape).moveTo(297.09, 125.86);
        ((GeneralPath) shape).lineTo(297.09, 126.606);
        ((GeneralPath) shape).lineTo(296.197, 126.606);
        ((GeneralPath) shape).lineTo(296.197, 125.86);
        ((GeneralPath) shape).lineTo(297.09, 125.86);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_363;
        g.setTransform(defaultTransform__0_363);
        g.setClip(clip__0_363);
        float alpha__0_364 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_364 = g.getClip();
        AffineTransform defaultTransform__0_364 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_364 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new Rectangle2D.Double(297.97100830078125, 125.86000061035156,
                0.8930000066757202, 5.331999778747559);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_364;
        g.setTransform(defaultTransform__0_364);
        g.setClip(clip__0_364);
        float alpha__0_365 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_365 = g.getClip();
        AffineTransform defaultTransform__0_365 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_365 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new Rectangle2D.Double(299.7430114746094, 125.86000061035156,
                0.8949999809265137, 5.331999778747559);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_365;
        g.setTransform(defaultTransform__0_365);
        g.setClip(clip__0_365);
        float alpha__0_366 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_366 = g.getClip();
        AffineTransform defaultTransform__0_366 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_366 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(302.369, 127.458);
        ((GeneralPath) shape).lineTo(302.369, 128.419);
        ((GeneralPath) shape).lineTo(301.47598, 128.419);
        ((GeneralPath) shape).lineTo(301.47598, 127.45801);
        ((GeneralPath) shape).lineTo(302.369, 127.45801);
        ((GeneralPath) shape).moveTo(302.369, 130.231);
        ((GeneralPath) shape).lineTo(302.369, 131.192);
        ((GeneralPath) shape).lineTo(301.47598, 131.192);
        ((GeneralPath) shape).lineTo(301.47598, 130.231);
        ((GeneralPath) shape).lineTo(302.369, 130.231);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_366;
        g.setTransform(defaultTransform__0_366);
        g.setClip(clip__0_366);
        float alpha__0_367 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_367 = g.getClip();
        AffineTransform defaultTransform__0_367 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_367 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(330.2, 130.341);
        ((GeneralPath) shape).lineTo(331.638, 130.341);
        ((GeneralPath) shape).curveTo(332.119, 130.341, 332.434, 130.23001,
                332.576, 130.007);
        ((GeneralPath) shape).curveTo(332.719, 129.785, 332.789, 129.294,
                332.789, 128.53);
        ((GeneralPath) shape).curveTo(332.789, 127.747, 332.726, 127.247,
                332.6, 127.03);
        ((GeneralPath) shape).curveTo(332.474, 126.817, 332.17502, 126.712,
                331.704, 126.712);
        ((GeneralPath) shape).lineTo(330.2, 126.712);
        ((GeneralPath) shape).lineTo(330.2, 130.341);
        ((GeneralPath) shape).moveTo(329.19, 131.192);
        ((GeneralPath) shape).lineTo(329.19, 125.86);
        ((GeneralPath) shape).lineTo(331.806, 125.86);
        ((GeneralPath) shape).curveTo(332.548, 125.86, 333.069, 126.022,
                333.368, 126.348);
        ((GeneralPath) shape).curveTo(333.664, 126.673, 333.81403, 127.244,
                333.81403, 128.059);
        ((GeneralPath) shape).curveTo(333.81403, 129.389, 333.69504, 130.24701,
                333.45602, 130.623);
        ((GeneralPath) shape).curveTo(333.22003, 131.002, 332.68002, 131.189,
                331.84003, 131.189);
        ((GeneralPath) shape).lineTo(329.19, 131.192);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_367;
        g.setTransform(defaultTransform__0_367);
        g.setClip(clip__0_367);
        float alpha__0_368 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_368 = g.getClip();
        AffineTransform defaultTransform__0_368 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_368 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(334.546, 127.458);
        ((GeneralPath) shape).lineTo(335.439, 127.458);
        ((GeneralPath) shape).lineTo(335.384, 127.979004);
        ((GeneralPath) shape).lineTo(335.404, 127.983);
        ((GeneralPath) shape).curveTo(335.617, 127.593, 335.952, 127.398,
                336.41098, 127.398);
        ((GeneralPath) shape).curveTo(337.06497, 127.398, 337.39297,
                127.812004, 337.39297, 128.64);
        ((GeneralPath) shape).lineTo(337.39297, 128.902);
        ((GeneralPath) shape).lineTo(336.55, 128.902);
        ((GeneralPath) shape).curveTo(336.56, 128.79999, 336.56598, 128.734,
                336.56598, 128.70499);
        ((GeneralPath) shape).curveTo(336.56598, 128.30598, 336.412, 128.10498,
                336.102, 128.10498);
        ((GeneralPath) shape).curveTo(335.662, 128.10498, 335.439, 128.39998,
                335.439, 128.99399);
        ((GeneralPath) shape).lineTo(335.439, 131.191);
        ((GeneralPath) shape).lineTo(334.546, 131.191);
        ((GeneralPath) shape).lineTo(334.546, 127.458);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_368;
        g.setTransform(defaultTransform__0_368);
        g.setClip(clip__0_368);
        float alpha__0_369 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_369 = g.getClip();
        AffineTransform defaultTransform__0_369 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_369 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(338.774, 127.458);
        ((GeneralPath) shape).lineTo(338.774, 131.19);
        ((GeneralPath) shape).lineTo(337.88098, 131.19);
        ((GeneralPath) shape).lineTo(337.88098, 127.458);
        ((GeneralPath) shape).lineTo(338.774, 127.458);
        ((GeneralPath) shape).moveTo(338.774, 125.86);
        ((GeneralPath) shape).lineTo(338.774, 126.606);
        ((GeneralPath) shape).lineTo(337.88098, 126.606);
        ((GeneralPath) shape).lineTo(337.88098, 125.86);
        ((GeneralPath) shape).lineTo(338.774, 125.86);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_369;
        g.setTransform(defaultTransform__0_369);
        g.setClip(clip__0_369);
        float alpha__0_370 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_370 = g.getClip();
        AffineTransform defaultTransform__0_370 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_370 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(342.762, 127.458);
        ((GeneralPath) shape).lineTo(341.694, 131.19);
        ((GeneralPath) shape).lineTo(340.298, 131.19);
        ((GeneralPath) shape).lineTo(339.17502, 127.458);
        ((GeneralPath) shape).lineTo(340.12003, 127.458);
        ((GeneralPath) shape).lineTo(340.61102, 129.181);
        ((GeneralPath) shape).curveTo(340.67703, 129.419, 340.74002, 129.652,
                340.799, 129.876);
        ((GeneralPath) shape).lineTo(340.889, 130.22401);
        ((GeneralPath) shape).lineTo(340.978, 130.57202);
        ((GeneralPath) shape).lineTo(341.0, 130.57202);
        ((GeneralPath) shape).lineTo(341.082, 130.22401);
        ((GeneralPath) shape).lineTo(341.164, 129.88002);
        ((GeneralPath) shape).curveTo(341.227, 129.62102, 341.285, 129.38802,
                341.342, 129.18501);
        ((GeneralPath) shape).lineTo(341.802, 127.458015);
        ((GeneralPath) shape).lineTo(342.762, 127.458015);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_370;
        g.setTransform(defaultTransform__0_370);
        g.setClip(clip__0_370);
        float alpha__0_371 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_371 = g.getClip();
        AffineTransform defaultTransform__0_371 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_371 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(344.052, 127.458);
        ((GeneralPath) shape).lineTo(344.052, 131.19);
        ((GeneralPath) shape).lineTo(343.157, 131.19);
        ((GeneralPath) shape).lineTo(343.157, 127.458);
        ((GeneralPath) shape).lineTo(344.052, 127.458);
        ((GeneralPath) shape).moveTo(344.052, 125.86);
        ((GeneralPath) shape).lineTo(344.052, 126.606);
        ((GeneralPath) shape).lineTo(343.157, 126.606);
        ((GeneralPath) shape).lineTo(343.157, 125.86);
        ((GeneralPath) shape).lineTo(344.052, 125.86);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_371;
        g.setTransform(defaultTransform__0_371);
        g.setClip(clip__0_371);
        float alpha__0_372 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_372 = g.getClip();
        AffineTransform defaultTransform__0_372 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_372 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(344.932, 127.458);
        ((GeneralPath) shape).lineTo(345.81702, 127.458);
        ((GeneralPath) shape).lineTo(345.782, 128.087);
        ((GeneralPath) shape).lineTo(345.803, 128.091);
        ((GeneralPath) shape).curveTo(345.97702, 127.642006, 346.366, 127.415,
                346.97302, 127.415);
        ((GeneralPath) shape).curveTo(347.85403, 127.415, 348.29404,
                127.825005, 348.29404, 128.648);
        ((GeneralPath) shape).lineTo(348.29404, 131.19);
        ((GeneralPath) shape).lineTo(347.4, 131.19);
        ((GeneralPath) shape).lineTo(347.4, 128.8);
        ((GeneralPath) shape).lineTo(347.38, 128.53801);
        ((GeneralPath) shape).curveTo(347.33902, 128.26102, 347.11902,
                128.12001, 346.725, 128.12001);
        ((GeneralPath) shape).curveTo(346.124, 128.12001, 345.825, 128.40302,
                345.825, 128.97401);
        ((GeneralPath) shape).lineTo(345.825, 131.18802);
        ((GeneralPath) shape).lineTo(344.932, 131.18802);
        ((GeneralPath) shape).lineTo(344.932, 127.458);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_372;
        g.setTransform(defaultTransform__0_372);
        g.setClip(clip__0_372);
        float alpha__0_373 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_373 = g.getClip();
        AffineTransform defaultTransform__0_373 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_373 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(350.738, 128.122);
        ((GeneralPath) shape).curveTo(350.39502, 128.122, 350.17502, 128.189,
                350.078, 128.331);
        ((GeneralPath) shape).curveTo(349.98, 128.47, 349.932, 128.786,
                349.932, 129.28);
        ((GeneralPath) shape).curveTo(349.932, 129.806, 349.98102, 130.142,
                350.078, 130.29);
        ((GeneralPath) shape).curveTo(350.177, 130.435, 350.39902, 130.50899,
                350.75, 130.50899);
        ((GeneralPath) shape).curveTo(351.104, 130.50899, 351.327, 130.43399,
                351.435, 130.28398);
        ((GeneralPath) shape).curveTo(351.539, 130.13599, 351.591, 129.80298,
                351.591, 129.28899);
        ((GeneralPath) shape).curveTo(351.591, 128.79698, 351.538, 128.47798,
                351.435, 128.33598);
        ((GeneralPath) shape).curveTo(351.329, 128.194, 351.097, 128.122,
                350.738, 128.122);
        ((GeneralPath) shape).moveTo(352.497, 127.458);
        ((GeneralPath) shape).lineTo(352.497, 131.263);
        ((GeneralPath) shape).curveTo(352.497, 131.872, 352.375, 132.29,
                352.131, 132.517);
        ((GeneralPath) shape).curveTo(351.885, 132.746, 351.433, 132.857,
                350.772, 132.857);
        ((GeneralPath) shape).curveTo(350.133, 132.857, 349.706, 132.771,
                349.49, 132.599);
        ((GeneralPath) shape).curveTo(349.275, 132.427, 349.167, 132.087,
                349.167, 131.58);
        ((GeneralPath) shape).lineTo(350.03198, 131.58);
        ((GeneralPath) shape).curveTo(350.03198, 131.82, 350.077, 131.976,
                350.16797, 132.046);
        ((GeneralPath) shape).curveTo(350.25998, 132.112, 350.46198, 132.15001,
                350.77597, 132.15001);
        ((GeneralPath) shape).curveTo(351.32996, 132.15001, 351.60696, 131.921,
                351.60696, 131.462);
        ((GeneralPath) shape).lineTo(351.60696, 130.62201);
        ((GeneralPath) shape).lineTo(351.58698, 130.619);
        ((GeneralPath) shape).curveTo(351.429, 131.015, 351.06497, 131.216,
                350.49298, 131.216);
        ((GeneralPath) shape).curveTo(349.93, 131.216, 349.546, 131.08,
                349.339, 130.806);
        ((GeneralPath) shape).curveTo(349.133, 130.535, 349.02698, 130.023,
                349.02698, 129.275);
        ((GeneralPath) shape).curveTo(349.02698, 128.571, 349.13098, 128.08699,
                349.339, 127.81699);
        ((GeneralPath) shape).curveTo(349.546, 127.549995, 349.918, 127.41699,
                350.464, 127.41699);
        ((GeneralPath) shape).curveTo(351.05698, 127.41699, 351.443,
                127.635994, 351.62997, 128.077);
        ((GeneralPath) shape).lineTo(351.649, 128.077);
        ((GeneralPath) shape).lineTo(351.607, 127.46);
        ((GeneralPath) shape).lineTo(352.497, 127.458);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_373;
        g.setTransform(defaultTransform__0_373);
        g.setClip(clip__0_373);
        float alpha__0_374 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_374 = g.getClip();
        AffineTransform defaultTransform__0_374 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_374 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(359.344, 127.415);
        ((GeneralPath) shape).lineTo(358.358, 127.415);
        ((GeneralPath) shape).curveTo(358.354, 127.364, 358.35, 127.33, 358.35,
                127.306);
        ((GeneralPath) shape).curveTo(358.327, 127.006996, 358.26, 126.82,
                358.151, 126.743);
        ((GeneralPath) shape).curveTo(358.045, 126.667, 357.785, 126.628,
                357.38, 126.628);
        ((GeneralPath) shape).curveTo(356.901, 126.628, 356.59, 126.671,
                356.44202, 126.761);
        ((GeneralPath) shape).curveTo(356.29602, 126.849, 356.221, 127.034004,
                356.221, 127.319);
        ((GeneralPath) shape).curveTo(356.221, 127.655, 356.28, 127.857,
                356.401, 127.925);
        ((GeneralPath) shape).curveTo(356.519, 127.989006, 356.91602, 128.044,
                357.588, 128.08101);
        ((GeneralPath) shape).curveTo(358.38, 128.12302, 358.893, 128.23502,
                359.12402, 128.42001);
        ((GeneralPath) shape).curveTo(359.35602, 128.60202, 359.47504,
                128.98102, 359.47504, 129.557);
        ((GeneralPath) shape).curveTo(359.47504, 130.264, 359.33905, 130.72401,
                359.06604, 130.93001);
        ((GeneralPath) shape).curveTo(358.79504, 131.138, 358.19305, 131.242,
                357.26105, 131.242);
        ((GeneralPath) shape).curveTo(356.42505, 131.242, 355.86703, 131.139,
                355.59406, 130.936);
        ((GeneralPath) shape).curveTo(355.32205, 130.733, 355.18304, 130.323,
                355.18304, 129.70401);
        ((GeneralPath) shape).lineTo(355.17905, 129.509);
        ((GeneralPath) shape).lineTo(356.15906, 129.509);
        ((GeneralPath) shape).lineTo(356.16306, 129.623);
        ((GeneralPath) shape).curveTo(356.16306, 129.994, 356.22705, 130.222,
                356.35806, 130.306);
        ((GeneralPath) shape).curveTo(356.48706, 130.38899, 356.84106, 130.431,
                357.42206, 130.431);
        ((GeneralPath) shape).curveTo(357.87207, 130.431, 358.16006, 130.384,
                358.28506, 130.286);
        ((GeneralPath) shape).curveTo(358.41006, 130.18799, 358.47305, 129.968,
                358.47305, 129.616);
        ((GeneralPath) shape).curveTo(358.47305, 129.358, 358.42606, 129.188,
                358.32806, 129.10199);
        ((GeneralPath) shape).curveTo(358.23407, 129.01799, 358.02905, 128.967,
                357.71606, 128.94798);
        ((GeneralPath) shape).lineTo(357.15607, 128.91498);
        ((GeneralPath) shape).curveTo(356.31406, 128.86497, 355.77606,
                128.74898, 355.54507, 128.56097);
        ((GeneralPath) shape).curveTo(355.31107, 128.37697, 355.19107,
                127.97897, 355.19107, 127.37197);
        ((GeneralPath) shape).curveTo(355.19107, 126.74997, 355.33206,
                126.33497, 355.61407, 126.12697);
        ((GeneralPath) shape).curveTo(355.89508, 125.91997, 356.46106,
                125.81497, 357.30408, 125.81497);
        ((GeneralPath) shape).curveTo(358.1031, 125.81497, 358.64307,
                125.907974, 358.9221, 126.099976);
        ((GeneralPath) shape).curveTo(359.19608, 126.28898, 359.33908,
                126.66298, 359.33908, 127.212975);
        ((GeneralPath) shape).lineTo(359.344, 127.415);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_374;
        g.setTransform(defaultTransform__0_374);
        g.setClip(clip__0_374);
        float alpha__0_375 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_375 = g.getClip();
        AffineTransform defaultTransform__0_375 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_375 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(361.044, 125.86);
        ((GeneralPath) shape).lineTo(361.044, 128.911);
        ((GeneralPath) shape).lineTo(361.272, 128.911);
        ((GeneralPath) shape).lineTo(362.277, 127.458);
        ((GeneralPath) shape).lineTo(363.314, 127.458);
        ((GeneralPath) shape).lineTo(362.017, 129.216);
        ((GeneralPath) shape).lineTo(363.58, 131.192);
        ((GeneralPath) shape).lineTo(362.473, 131.192);
        ((GeneralPath) shape).lineTo(361.261, 129.536);
        ((GeneralPath) shape).lineTo(361.044, 129.536);
        ((GeneralPath) shape).lineTo(361.044, 131.192);
        ((GeneralPath) shape).lineTo(360.149, 131.192);
        ((GeneralPath) shape).lineTo(360.149, 125.86);
        ((GeneralPath) shape).lineTo(361.044, 125.86);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_375;
        g.setTransform(defaultTransform__0_375);
        g.setClip(clip__0_375);
        float alpha__0_376 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_376 = g.getClip();
        AffineTransform defaultTransform__0_376 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_376 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(364.875, 127.458);
        ((GeneralPath) shape).lineTo(364.875, 131.19);
        ((GeneralPath) shape).lineTo(363.982, 131.19);
        ((GeneralPath) shape).lineTo(363.982, 127.458);
        ((GeneralPath) shape).lineTo(364.875, 127.458);
        ((GeneralPath) shape).moveTo(364.875, 125.86);
        ((GeneralPath) shape).lineTo(364.875, 126.606);
        ((GeneralPath) shape).lineTo(363.982, 126.606);
        ((GeneralPath) shape).lineTo(363.982, 125.86);
        ((GeneralPath) shape).lineTo(364.875, 125.86);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_376;
        g.setTransform(defaultTransform__0_376);
        g.setClip(clip__0_376);
        float alpha__0_377 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_377 = g.getClip();
        AffineTransform defaultTransform__0_377 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_377 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new Rectangle2D.Double(365.7560119628906, 125.86000061035156,
                0.8930000066757202, 5.331999778747559);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_377;
        g.setTransform(defaultTransform__0_377);
        g.setClip(clip__0_377);
        float alpha__0_378 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_378 = g.getClip();
        AffineTransform defaultTransform__0_378 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_378 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new Rectangle2D.Double(367.5260009765625, 125.86000061035156,
                0.8949999809265137, 5.331999778747559);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_378;
        g.setTransform(defaultTransform__0_378);
        g.setClip(clip__0_378);
        float alpha__0_379 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_379 = g.getClip();
        AffineTransform defaultTransform__0_379 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_379 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(370.153, 127.458);
        ((GeneralPath) shape).lineTo(370.153, 128.419);
        ((GeneralPath) shape).lineTo(369.26, 128.419);
        ((GeneralPath) shape).lineTo(369.26, 127.45801);
        ((GeneralPath) shape).lineTo(370.153, 127.45801);
        ((GeneralPath) shape).moveTo(370.153, 130.231);
        ((GeneralPath) shape).lineTo(370.153, 131.192);
        ((GeneralPath) shape).lineTo(369.26, 131.192);
        ((GeneralPath) shape).lineTo(369.26, 130.231);
        ((GeneralPath) shape).lineTo(370.153, 130.231);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_379;
        g.setTransform(defaultTransform__0_379);
        g.setClip(clip__0_379);
        float alpha__0_380 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_380 = g.getClip();
        AffineTransform defaultTransform__0_380 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_380 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(331.242, 147.696);
        ((GeneralPath) shape).lineTo(332.976, 147.696);
        ((GeneralPath) shape).curveTo(333.612, 147.696, 334.02402, 147.569,
                334.208, 147.313);
        ((GeneralPath) shape).curveTo(334.392, 147.06, 334.483, 146.489,
                334.483, 145.606);
        ((GeneralPath) shape).curveTo(334.483, 144.63701, 334.403, 144.02301,
                334.243, 143.766);
        ((GeneralPath) shape).curveTo(334.08502, 143.509, 333.70102, 143.38,
                333.09702, 143.38);
        ((GeneralPath) shape).lineTo(331.242, 143.38);
        ((GeneralPath) shape).lineTo(331.242, 147.696);
        ((GeneralPath) shape).moveTo(330.642, 148.204);
        ((GeneralPath) shape).lineTo(330.642, 142.872);
        ((GeneralPath) shape).lineTo(333.106, 142.872);
        ((GeneralPath) shape).curveTo(333.86697, 142.872, 334.383, 143.04,
                334.654, 143.37599);
        ((GeneralPath) shape).curveTo(334.925, 143.71199, 335.059, 144.355,
                335.059, 145.30598);
        ((GeneralPath) shape).curveTo(335.059, 146.46199, 334.94098, 147.23398,
                334.699, 147.62198);
        ((GeneralPath) shape).curveTo(334.462, 148.00897, 333.98, 148.20398,
                333.26102, 148.20398);
        ((GeneralPath) shape).lineTo(330.642, 148.20398);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_380;
        g.setTransform(defaultTransform__0_380);
        g.setClip(clip__0_380);
        float alpha__0_381 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_381 = g.getClip();
        AffineTransform defaultTransform__0_381 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_381 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(335.885, 144.47);
        ((GeneralPath) shape).lineTo(336.431, 144.47);
        ((GeneralPath) shape).lineTo(336.374, 144.9);
        ((GeneralPath) shape).lineTo(336.388, 144.914);
        ((GeneralPath) shape).curveTo(336.602, 144.56, 336.956, 144.386,
                337.454, 144.386);
        ((GeneralPath) shape).curveTo(338.142, 144.386, 338.483, 144.74,
                338.483, 145.449);
        ((GeneralPath) shape).lineTo(338.479, 145.709);
        ((GeneralPath) shape).lineTo(337.94, 145.709);
        ((GeneralPath) shape).lineTo(337.953, 145.613);
        ((GeneralPath) shape).curveTo(337.963, 145.515, 337.967, 145.449,
                337.967, 145.416);
        ((GeneralPath) shape).curveTo(337.967, 145.031, 337.76, 144.841,
                337.34302, 144.841);
        ((GeneralPath) shape).curveTo(336.73502, 144.841, 336.43103, 145.216,
                336.43103, 145.97);
        ((GeneralPath) shape).lineTo(336.43103, 148.20801);
        ((GeneralPath) shape).lineTo(335.88504, 148.20801);
        ((GeneralPath) shape).lineTo(335.88504, 144.47);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_381;
        g.setTransform(defaultTransform__0_381);
        g.setClip(clip__0_381);
        float alpha__0_382 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_382 = g.getClip();
        AffineTransform defaultTransform__0_382 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_382 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(339.552, 144.47);
        ((GeneralPath) shape).lineTo(339.552, 148.204);
        ((GeneralPath) shape).lineTo(339.006, 148.204);
        ((GeneralPath) shape).lineTo(339.006, 144.47);
        ((GeneralPath) shape).lineTo(339.552, 144.47);
        ((GeneralPath) shape).moveTo(339.552, 142.872);
        ((GeneralPath) shape).lineTo(339.552, 143.48299);
        ((GeneralPath) shape).lineTo(339.006, 143.48299);
        ((GeneralPath) shape).lineTo(339.006, 142.872);
        ((GeneralPath) shape).lineTo(339.552, 142.872);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_382;
        g.setTransform(defaultTransform__0_382);
        g.setClip(clip__0_382);
        float alpha__0_383 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_383 = g.getClip();
        AffineTransform defaultTransform__0_383 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_383 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(343.266, 144.47);
        ((GeneralPath) shape).lineTo(342.018, 148.204);
        ((GeneralPath) shape).lineTo(341.234, 148.204);
        ((GeneralPath) shape).lineTo(339.998, 144.47);
        ((GeneralPath) shape).lineTo(340.56, 144.47);
        ((GeneralPath) shape).lineTo(341.219, 146.501);
        ((GeneralPath) shape).lineTo(341.427, 147.138);
        ((GeneralPath) shape).lineTo(341.522, 147.458);
        ((GeneralPath) shape).lineTo(341.624, 147.778);
        ((GeneralPath) shape).lineTo(341.64, 147.778);
        ((GeneralPath) shape).lineTo(341.733, 147.462);
        ((GeneralPath) shape).lineTo(341.827, 147.142);
        ((GeneralPath) shape).lineTo(342.025, 146.509);
        ((GeneralPath) shape).lineTo(342.657, 144.47);
        ((GeneralPath) shape).lineTo(343.266, 144.47);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_383;
        g.setTransform(defaultTransform__0_383);
        g.setClip(clip__0_383);
        float alpha__0_384 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_384 = g.getClip();
        AffineTransform defaultTransform__0_384 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_384 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(346.308, 146.013);
        ((GeneralPath) shape).lineTo(346.303, 145.837);
        ((GeneralPath) shape).curveTo(346.303, 145.43501, 346.238, 145.171,
                346.105, 145.05);
        ((GeneralPath) shape).curveTo(345.97202, 144.929, 345.68802, 144.868,
                345.24802, 144.868);
        ((GeneralPath) shape).curveTo(344.80902, 144.868, 344.52002, 144.93599,
                344.39, 145.081);
        ((GeneralPath) shape).curveTo(344.26, 145.222, 344.194, 145.53,
                344.194, 146.013);
        ((GeneralPath) shape).lineTo(346.308, 146.013);
        ((GeneralPath) shape).moveTo(346.308, 147.076);
        ((GeneralPath) shape).lineTo(346.86603, 147.076);
        ((GeneralPath) shape).lineTo(346.87003, 147.212);
        ((GeneralPath) shape).curveTo(346.87003, 147.599, 346.75504, 147.87001,
                346.51602, 148.02501);
        ((GeneralPath) shape).curveTo(346.28302, 148.17902, 345.87003, 148.257,
                345.27902, 148.257);
        ((GeneralPath) shape).curveTo(344.592, 148.257, 344.14203, 148.132,
                343.92703, 147.88);
        ((GeneralPath) shape).curveTo(343.71304, 147.63, 343.60602, 147.101,
                343.60602, 146.296);
        ((GeneralPath) shape).curveTo(343.60602, 145.552, 343.713, 145.052,
                343.92902, 144.796);
        ((GeneralPath) shape).curveTo(344.144, 144.542, 344.56702, 144.41301,
                345.19702, 144.41301);
        ((GeneralPath) shape).curveTo(345.885, 144.41301, 346.33203, 144.52,
                346.54803, 144.74501);
        ((GeneralPath) shape).curveTo(346.76105, 144.966, 346.86603, 145.43301,
                346.86603, 146.14201);
        ((GeneralPath) shape).lineTo(346.86603, 146.43402);
        ((GeneralPath) shape).lineTo(344.183, 146.43402);
        ((GeneralPath) shape).curveTo(344.183, 147.02202, 344.246, 147.39702,
                344.371, 147.55902);
        ((GeneralPath) shape).curveTo(344.496, 147.71902, 344.79, 147.80103,
                345.257, 147.80103);
        ((GeneralPath) shape).curveTo(345.69498, 147.80103, 345.97998,
                147.76402, 346.111, 147.68602);
        ((GeneralPath) shape).curveTo(346.242, 147.61002, 346.30798, 147.44402,
                346.30798, 147.18802);
        ((GeneralPath) shape).lineTo(346.308, 147.076);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_384;
        g.setTransform(defaultTransform__0_384);
        g.setClip(clip__0_384);
        float alpha__0_385 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_385 = g.getClip();
        AffineTransform defaultTransform__0_385 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_385 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(347.776, 144.47);
        ((GeneralPath) shape).lineTo(348.322, 144.47);
        ((GeneralPath) shape).lineTo(348.267, 144.9);
        ((GeneralPath) shape).lineTo(348.279, 144.914);
        ((GeneralPath) shape).curveTo(348.494, 144.56, 348.85, 144.386,
                349.347, 144.386);
        ((GeneralPath) shape).curveTo(350.034, 144.386, 350.37598, 144.74,
                350.37598, 145.449);
        ((GeneralPath) shape).lineTo(350.36996, 145.709);
        ((GeneralPath) shape).lineTo(349.83197, 145.709);
        ((GeneralPath) shape).lineTo(349.84598, 145.613);
        ((GeneralPath) shape).curveTo(349.85397, 145.515, 349.85797, 145.449,
                349.85797, 145.416);
        ((GeneralPath) shape).curveTo(349.85797, 145.031, 349.65097, 144.841,
                349.23398, 144.841);
        ((GeneralPath) shape).curveTo(348.62897, 144.841, 348.322, 145.216,
                348.322, 145.97);
        ((GeneralPath) shape).lineTo(348.322, 148.20801);
        ((GeneralPath) shape).lineTo(347.776, 148.20801);
        ((GeneralPath) shape).lineTo(347.776, 144.47);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_385;
        g.setTransform(defaultTransform__0_385);
        g.setClip(clip__0_385);
        float alpha__0_386 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_386 = g.getClip();
        AffineTransform defaultTransform__0_386 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_386 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(357.234, 142.872);
        ((GeneralPath) shape).lineTo(357.234, 148.204);
        ((GeneralPath) shape).lineTo(356.635, 148.204);
        ((GeneralPath) shape).lineTo(356.635, 145.732);
        ((GeneralPath) shape).lineTo(353.523, 145.732);
        ((GeneralPath) shape).lineTo(353.523, 148.204);
        ((GeneralPath) shape).lineTo(352.925, 148.204);
        ((GeneralPath) shape).lineTo(352.925, 142.872);
        ((GeneralPath) shape).lineTo(353.523, 142.872);
        ((GeneralPath) shape).lineTo(353.523, 145.224);
        ((GeneralPath) shape).lineTo(356.635, 145.224);
        ((GeneralPath) shape).lineTo(356.635, 142.872);
        ((GeneralPath) shape).lineTo(357.234, 142.872);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_386;
        g.setTransform(defaultTransform__0_386);
        g.setClip(clip__0_386);
        float alpha__0_387 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_387 = g.getClip();
        AffineTransform defaultTransform__0_387 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_387 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(358.767, 144.47);
        ((GeneralPath) shape).lineTo(358.767, 148.204);
        ((GeneralPath) shape).lineTo(358.221, 148.204);
        ((GeneralPath) shape).lineTo(358.221, 144.47);
        ((GeneralPath) shape).lineTo(358.767, 144.47);
        ((GeneralPath) shape).moveTo(358.767, 142.872);
        ((GeneralPath) shape).lineTo(358.767, 143.48299);
        ((GeneralPath) shape).lineTo(358.221, 143.48299);
        ((GeneralPath) shape).lineTo(358.221, 142.872);
        ((GeneralPath) shape).lineTo(358.767, 142.872);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_387;
        g.setTransform(defaultTransform__0_387);
        g.setClip(clip__0_387);
        float alpha__0_388 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_388 = g.getClip();
        AffineTransform defaultTransform__0_388 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_388 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(361.697, 144.47);
        ((GeneralPath) shape).lineTo(361.697, 144.923);
        ((GeneralPath) shape).lineTo(360.261, 144.923);
        ((GeneralPath) shape).lineTo(360.261, 147.20801);
        ((GeneralPath) shape).curveTo(360.261, 147.60501, 360.43698, 147.80602,
                360.789, 147.80602);
        ((GeneralPath) shape).curveTo(361.143, 147.80602, 361.316, 147.62802,
                361.316, 147.27101);
        ((GeneralPath) shape).lineTo(361.319, 147.087);
        ((GeneralPath) shape).lineTo(361.327, 146.88);
        ((GeneralPath) shape).lineTo(361.836, 146.88);
        ((GeneralPath) shape).lineTo(361.841, 147.155);
        ((GeneralPath) shape).curveTo(361.841, 147.88899, 361.491, 148.259,
                360.795, 148.259);
        ((GeneralPath) shape).curveTo(360.074, 148.259, 359.716, 147.95401,
                359.716, 147.342);
        ((GeneralPath) shape).lineTo(359.716, 144.924);
        ((GeneralPath) shape).lineTo(359.2, 144.924);
        ((GeneralPath) shape).lineTo(359.2, 144.471);
        ((GeneralPath) shape).lineTo(359.71802, 144.471);
        ((GeneralPath) shape).lineTo(359.71802, 143.573);
        ((GeneralPath) shape).lineTo(360.26303, 143.573);
        ((GeneralPath) shape).lineTo(360.26303, 144.471);
        ((GeneralPath) shape).lineTo(361.697, 144.47);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_388;
        g.setTransform(defaultTransform__0_388);
        g.setClip(clip__0_388);
        float alpha__0_389 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_389 = g.getClip();
        AffineTransform defaultTransform__0_389 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_389 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(260.432, 146.423);
        ((GeneralPath) shape).lineTo(261.029, 146.423);
        ((GeneralPath) shape).lineTo(261.029, 146.626);
        ((GeneralPath) shape).curveTo(261.029, 147.35301, 260.906, 147.806,
                260.659, 147.985);
        ((GeneralPath) shape).curveTo(260.413, 148.167, 259.791, 148.256,
                258.79498, 148.256);
        ((GeneralPath) shape).curveTo(257.904, 148.256, 257.33698, 148.09799,
                257.097, 147.777);
        ((GeneralPath) shape).curveTo(256.85498, 147.461, 256.73398, 146.711,
                256.73398, 145.525);
        ((GeneralPath) shape).curveTo(256.73398, 144.601, 256.75897, 144.019,
                256.81598, 143.78099);
        ((GeneralPath) shape).curveTo(256.87097, 143.545, 257.02698, 143.33199,
                257.28397, 143.14299);
        ((GeneralPath) shape).curveTo(257.58395, 142.924, 258.23596, 142.814,
                259.23697, 142.814);
        ((GeneralPath) shape).curveTo(259.91595, 142.814, 260.37198, 142.91899,
                260.60797, 143.12999);
        ((GeneralPath) shape).curveTo(260.83997, 143.34099, 260.96097, 143.747,
                260.96097, 144.353);
        ((GeneralPath) shape).lineTo(260.96497, 144.499);
        ((GeneralPath) shape).lineTo(260.36798, 144.499);
        ((GeneralPath) shape).lineTo(260.36398, 144.33499);
        ((GeneralPath) shape).curveTo(260.36398, 143.90298, 260.296, 143.62999,
                260.15598, 143.50699);
        ((GeneralPath) shape).curveTo(260.01697, 143.38599, 259.70398,
                143.32199, 259.214, 143.32199);
        ((GeneralPath) shape).curveTo(258.35898, 143.32199, 257.83398, 143.392,
                257.645, 143.538);
        ((GeneralPath) shape).curveTo(257.456, 143.681, 257.36, 144.077,
                257.36, 144.728);
        ((GeneralPath) shape).curveTo(257.36, 146.224, 257.422, 147.103,
                257.54898, 147.361);
        ((GeneralPath) shape).curveTo(257.676, 147.62, 258.11197, 147.749,
                258.857, 147.749);
        ((GeneralPath) shape).curveTo(259.563, 147.749, 260.003, 147.69199,
                260.18, 147.581);
        ((GeneralPath) shape).curveTo(260.356, 147.47, 260.444, 147.18599,
                260.444, 146.736);
        ((GeneralPath) shape).lineTo(260.432, 146.423);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_389;
        g.setTransform(defaultTransform__0_389);
        g.setClip(clip__0_389);
        float alpha__0_390 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_390 = g.getClip();
        AffineTransform defaultTransform__0_390 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_390 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(263.223, 144.868);
        ((GeneralPath) shape).curveTo(262.74, 144.868, 262.442, 144.944,
                262.32498, 145.10199);
        ((GeneralPath) shape).curveTo(262.21, 145.258, 262.15097, 145.66998,
                262.15097, 146.33398);
        ((GeneralPath) shape).curveTo(262.15097, 146.99799, 262.20798,
                147.40898, 262.32498, 147.56398);
        ((GeneralPath) shape).curveTo(262.43997, 147.71999, 262.74, 147.79997,
                263.223, 147.79997);
        ((GeneralPath) shape).curveTo(263.706, 147.79997, 264.007, 147.72197,
                264.124, 147.56398);
        ((GeneralPath) shape).curveTo(264.23898, 147.40997, 264.297, 146.99799,
                264.297, 146.33398);
        ((GeneralPath) shape).curveTo(264.297, 145.66998, 264.241, 145.25998,
                264.124, 145.10199);
        ((GeneralPath) shape).curveTo(264.01, 144.948, 263.71, 144.868,
                263.223, 144.868);
        ((GeneralPath) shape).moveTo(263.223, 144.415);
        ((GeneralPath) shape).curveTo(263.91098, 144.415, 264.358, 144.534,
                264.564, 144.77199);
        ((GeneralPath) shape).curveTo(264.76898, 145.01, 264.872, 145.53398,
                264.872, 146.33499);
        ((GeneralPath) shape).curveTo(264.872, 147.13599, 264.77002, 147.657,
                264.564, 147.898);
        ((GeneralPath) shape).curveTo(264.359, 148.136, 263.913, 148.25699,
                263.223, 148.25699);
        ((GeneralPath) shape).curveTo(262.535, 148.25699, 262.091, 148.13799,
                261.886, 147.898);
        ((GeneralPath) shape).curveTo(261.681, 147.662, 261.576, 147.138,
                261.576, 146.33499);
        ((GeneralPath) shape).curveTo(261.576, 145.53499, 261.679, 145.01299,
                261.886, 144.77199);
        ((GeneralPath) shape).curveTo(262.09, 144.537, 262.537, 144.415,
                263.223, 144.415);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_390;
        g.setTransform(defaultTransform__0_390);
        g.setClip(clip__0_390);
        float alpha__0_391 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_391 = g.getClip();
        AffineTransform defaultTransform__0_391 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_391 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(265.724, 144.47);
        ((GeneralPath) shape).lineTo(266.27, 144.47);
        ((GeneralPath) shape).lineTo(266.25598, 145.033);
        ((GeneralPath) shape).lineTo(266.27, 145.045);
        ((GeneralPath) shape).curveTo(266.47998, 144.627, 266.886, 144.416,
                267.486, 144.416);
        ((GeneralPath) shape).curveTo(268.117, 144.416, 268.5, 144.625,
                268.636, 145.045);
        ((GeneralPath) shape).lineTo(268.65198, 145.045);
        ((GeneralPath) shape).curveTo(268.886, 144.627, 269.313, 144.416,
                269.93497, 144.416);
        ((GeneralPath) shape).curveTo(270.81198, 144.416, 271.25098, 144.864,
                271.25098, 145.764);
        ((GeneralPath) shape).lineTo(271.25098, 148.205);
        ((GeneralPath) shape).lineTo(270.705, 148.205);
        ((GeneralPath) shape).lineTo(270.705, 145.713);
        ((GeneralPath) shape).curveTo(270.705, 145.385, 270.64697, 145.163,
                270.53, 145.045);
        ((GeneralPath) shape).curveTo(270.413, 144.928, 270.193, 144.869,
                269.867, 144.869);
        ((GeneralPath) shape).curveTo(269.436, 144.869, 269.144, 144.947,
                268.99, 145.11101);
        ((GeneralPath) shape).curveTo(268.83798, 145.27301, 268.761, 145.58601,
                268.761, 146.04501);
        ((GeneralPath) shape).lineTo(268.761, 148.20502);
        ((GeneralPath) shape).lineTo(268.21298, 148.20502);
        ((GeneralPath) shape).lineTo(268.21298, 145.76402);
        ((GeneralPath) shape).lineTo(268.205, 145.59302);
        ((GeneralPath) shape).curveTo(268.205, 145.11201, 267.934, 144.87001,
                267.382, 144.87001);
        ((GeneralPath) shape).curveTo(266.641, 144.87001, 266.26898, 145.272,
                266.26898, 146.085);
        ((GeneralPath) shape).lineTo(266.26898, 148.20601);
        ((GeneralPath) shape).lineTo(265.723, 148.20601);
        ((GeneralPath) shape).lineTo(265.723, 144.47);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_391;
        g.setTransform(defaultTransform__0_391);
        g.setClip(clip__0_391);
        float alpha__0_392 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_392 = g.getClip();
        AffineTransform defaultTransform__0_392 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_392 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(272.32, 144.47);
        ((GeneralPath) shape).lineTo(272.866, 144.47);
        ((GeneralPath) shape).lineTo(272.854, 145.033);
        ((GeneralPath) shape).lineTo(272.866, 145.045);
        ((GeneralPath) shape).curveTo(273.076, 144.627, 273.482, 144.416,
                274.082, 144.416);
        ((GeneralPath) shape).curveTo(274.713, 144.416, 275.098, 144.625,
                275.232, 145.045);
        ((GeneralPath) shape).lineTo(275.248, 145.045);
        ((GeneralPath) shape).curveTo(275.482, 144.627, 275.909, 144.416,
                276.53098, 144.416);
        ((GeneralPath) shape).curveTo(277.408, 144.416, 277.84897, 144.864,
                277.84897, 145.764);
        ((GeneralPath) shape).lineTo(277.84897, 148.205);
        ((GeneralPath) shape).lineTo(277.30298, 148.205);
        ((GeneralPath) shape).lineTo(277.30298, 145.713);
        ((GeneralPath) shape).curveTo(277.30298, 145.385, 277.24298, 145.163,
                277.12598, 145.045);
        ((GeneralPath) shape).curveTo(277.011, 144.928, 276.78897, 144.869,
                276.46298, 144.869);
        ((GeneralPath) shape).curveTo(276.03198, 144.869, 275.73898, 144.947,
                275.58597, 145.11101);
        ((GeneralPath) shape).curveTo(275.43597, 145.27301, 275.35696,
                145.58601, 275.35696, 146.04501);
        ((GeneralPath) shape).lineTo(275.35696, 148.20502);
        ((GeneralPath) shape).lineTo(274.81097, 148.20502);
        ((GeneralPath) shape).lineTo(274.81097, 145.76402);
        ((GeneralPath) shape).lineTo(274.80298, 145.59302);
        ((GeneralPath) shape).curveTo(274.80298, 145.11201, 274.52997,
                144.87001, 273.97998, 144.87001);
        ((GeneralPath) shape).curveTo(273.23697, 144.87001, 272.865, 145.272,
                272.865, 146.085);
        ((GeneralPath) shape).lineTo(272.865, 148.20601);
        ((GeneralPath) shape).lineTo(272.319, 148.20601);
        ((GeneralPath) shape).lineTo(272.319, 144.47);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_392;
        g.setTransform(defaultTransform__0_392);
        g.setClip(clip__0_392);
        float alpha__0_393 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_393 = g.getClip();
        AffineTransform defaultTransform__0_393 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_393 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(280.363, 146.357);
        ((GeneralPath) shape).curveTo(279.946, 146.357, 279.673, 146.399,
                279.54602, 146.48999);
        ((GeneralPath) shape).curveTo(279.42102, 146.579, 279.35803, 146.76698,
                279.35803, 147.06);
        ((GeneralPath) shape).curveTo(279.35803, 147.36, 279.42102, 147.561,
                279.54504, 147.661);
        ((GeneralPath) shape).curveTo(279.67004, 147.759, 279.92303, 147.80699,
                280.30804, 147.80699);
        ((GeneralPath) shape).curveTo(281.07605, 147.80699, 281.46005, 147.573,
                281.46005, 147.10399);
        ((GeneralPath) shape).curveTo(281.46005, 146.81198, 281.38806,
                146.61198, 281.23605, 146.51);
        ((GeneralPath) shape).curveTo(281.086, 146.406, 280.795, 146.357,
                280.363, 146.357);
        ((GeneralPath) shape).moveTo(279.486, 145.517);
        ((GeneralPath) shape).lineTo(278.944, 145.517);
        ((GeneralPath) shape).curveTo(278.944, 145.083, 279.042, 144.79,
                279.238, 144.64);
        ((GeneralPath) shape).curveTo(279.433, 144.494, 279.81702, 144.415,
                280.39, 144.415);
        ((GeneralPath) shape).curveTo(281.01, 144.415, 281.431, 144.50699,
                281.64902, 144.68799);
        ((GeneralPath) shape).curveTo(281.867, 144.87299, 281.97702, 145.221,
                281.97702, 145.73698);
        ((GeneralPath) shape).lineTo(281.97702, 148.20299);
        ((GeneralPath) shape).lineTo(281.43103, 148.20299);
        ((GeneralPath) shape).lineTo(281.47403, 147.801);
        ((GeneralPath) shape).lineTo(281.46002, 147.797);
        ((GeneralPath) shape).curveTo(281.25302, 148.104, 280.83002, 148.258,
                280.19302, 148.258);
        ((GeneralPath) shape).curveTo(279.25403, 148.258, 278.78104, 147.87999,
                278.78104, 147.121);
        ((GeneralPath) shape).curveTo(278.78104, 146.672, 278.88605, 146.357,
                279.09705, 146.185);
        ((GeneralPath) shape).curveTo(279.30804, 146.013, 279.68805, 145.927,
                280.23904, 145.927);
        ((GeneralPath) shape).curveTo(280.89404, 145.927, 281.28604, 146.056,
                281.41705, 146.314);
        ((GeneralPath) shape).lineTo(281.43106, 146.31);
        ((GeneralPath) shape).lineTo(281.43106, 145.857);
        ((GeneralPath) shape).curveTo(281.43106, 145.43199, 281.37106,
                145.15399, 281.25305, 145.027);
        ((GeneralPath) shape).curveTo(281.13806, 144.902, 280.88306, 144.83899,
                280.48505, 144.83899);
        ((GeneralPath) shape).curveTo(279.81406, 144.83899, 279.47903,
                145.02599, 279.47903, 145.40298);
        ((GeneralPath) shape).curveTo(279.482, 145.425, 279.482, 145.462,
                279.486, 145.517);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_393;
        g.setTransform(defaultTransform__0_393);
        g.setClip(clip__0_393);
        float alpha__0_394 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_394 = g.getClip();
        AffineTransform defaultTransform__0_394 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_394 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(282.99, 144.47);
        ((GeneralPath) shape).lineTo(283.516, 144.47);
        ((GeneralPath) shape).lineTo(283.498, 144.978);
        ((GeneralPath) shape).lineTo(283.516, 144.98999);
        ((GeneralPath) shape).curveTo(283.68198, 144.607, 284.095, 144.41699,
                284.754, 144.41699);
        ((GeneralPath) shape).curveTo(285.286, 144.41699, 285.649, 144.51099,
                285.836, 144.696);
        ((GeneralPath) shape).curveTo(286.024, 144.884, 286.119, 145.245,
                286.119, 145.774);
        ((GeneralPath) shape).lineTo(286.119, 148.20601);
        ((GeneralPath) shape).lineTo(285.573, 148.20601);
        ((GeneralPath) shape).lineTo(285.573, 145.68301);
        ((GeneralPath) shape).curveTo(285.573, 145.363, 285.512, 145.14801,
                285.38998, 145.03702);
        ((GeneralPath) shape).curveTo(285.26898, 144.92802, 285.033, 144.87102,
                284.684, 144.87102);
        ((GeneralPath) shape).curveTo(283.916, 144.87102, 283.534, 145.23302,
                283.534, 145.96101);
        ((GeneralPath) shape).lineTo(283.534, 148.20702);
        ((GeneralPath) shape).lineTo(282.98898, 148.20702);
        ((GeneralPath) shape).lineTo(282.98898, 144.47);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_394;
        g.setTransform(defaultTransform__0_394);
        g.setClip(clip__0_394);
        float alpha__0_395 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_395 = g.getClip();
        AffineTransform defaultTransform__0_395 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_395 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(288.584, 144.868);
        ((GeneralPath) shape).curveTo(288.18402, 144.868, 287.914, 144.95999,
                287.781, 145.14499);
        ((GeneralPath) shape).curveTo(287.648, 145.33199, 287.582, 145.708,
                287.582, 146.27599);
        ((GeneralPath) shape).curveTo(287.582, 146.90099, 287.646, 147.31099,
                287.779, 147.50798);
        ((GeneralPath) shape).curveTo(287.909, 147.70299, 288.182, 147.80298,
                288.59598, 147.80298);
        ((GeneralPath) shape).curveTo(289.042, 147.80298, 289.335, 147.70497,
                289.473, 147.50798);
        ((GeneralPath) shape).curveTo(289.608, 147.31297, 289.67398, 146.88498,
                289.67398, 146.22498);
        ((GeneralPath) shape).curveTo(289.67398, 145.69197, 289.602, 145.33197,
                289.44998, 145.14497);
        ((GeneralPath) shape).curveTo(289.304, 144.962, 289.013, 144.868,
                288.584, 144.868);
        ((GeneralPath) shape).moveTo(290.226, 142.872);
        ((GeneralPath) shape).lineTo(290.226, 148.204);
        ((GeneralPath) shape).lineTo(289.681, 148.204);
        ((GeneralPath) shape).lineTo(289.706, 147.72);
        ((GeneralPath) shape).lineTo(289.69, 147.717);
        ((GeneralPath) shape).curveTo(289.518, 148.07799, 289.121, 148.259,
                288.493, 148.259);
        ((GeneralPath) shape).curveTo(287.922, 148.259, 287.532, 148.12,
                287.32202, 147.842);
        ((GeneralPath) shape).curveTo(287.114, 147.564, 287.00903, 147.045,
                287.00903, 146.28099);
        ((GeneralPath) shape).curveTo(287.00903, 145.58398, 287.11304,
                145.09499, 287.32303, 144.82199);
        ((GeneralPath) shape).curveTo(287.53302, 144.551, 287.90903, 144.41599,
                288.45303, 144.41599);
        ((GeneralPath) shape).curveTo(289.13403, 144.41599, 289.53503,
                144.59198, 289.66504, 144.94098);
        ((GeneralPath) shape).lineTo(289.67905, 144.93398);
        ((GeneralPath) shape).lineTo(289.67905, 142.87097);
        ((GeneralPath) shape).lineTo(290.226, 142.87097);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_395;
        g.setTransform(defaultTransform__0_395);
        g.setClip(clip__0_395);
        float alpha__0_396 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_396 = g.getClip();
        AffineTransform defaultTransform__0_396 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_396 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(293.776, 146.013);
        ((GeneralPath) shape).lineTo(293.772, 145.837);
        ((GeneralPath) shape).curveTo(293.772, 145.43501, 293.706, 145.171,
                293.573, 145.05);
        ((GeneralPath) shape).curveTo(293.44, 144.929, 293.156, 144.868,
                292.715, 144.868);
        ((GeneralPath) shape).curveTo(292.276, 144.868, 291.988, 144.93599,
                291.858, 145.081);
        ((GeneralPath) shape).curveTo(291.727, 145.222, 291.661, 145.53,
                291.661, 146.013);
        ((GeneralPath) shape).lineTo(293.776, 146.013);
        ((GeneralPath) shape).moveTo(293.776, 147.076);
        ((GeneralPath) shape).lineTo(294.334, 147.076);
        ((GeneralPath) shape).lineTo(294.338, 147.212);
        ((GeneralPath) shape).curveTo(294.338, 147.599, 294.22302, 147.87001,
                293.98502, 148.02501);
        ((GeneralPath) shape).curveTo(293.751, 148.17902, 293.338, 148.257,
                292.747, 148.257);
        ((GeneralPath) shape).curveTo(292.061, 148.257, 291.61002, 148.132,
                291.396, 147.88);
        ((GeneralPath) shape).curveTo(291.182, 147.63, 291.07498, 147.101,
                291.07498, 146.296);
        ((GeneralPath) shape).curveTo(291.07498, 145.552, 291.18198, 145.052,
                291.39798, 144.796);
        ((GeneralPath) shape).curveTo(291.61298, 144.542, 292.03598, 144.41301,
                292.66498, 144.41301);
        ((GeneralPath) shape).curveTo(293.35297, 144.41301, 293.8, 144.52,
                294.016, 144.74501);
        ((GeneralPath) shape).curveTo(294.228, 144.966, 294.33398, 145.43301,
                294.33398, 146.14201);
        ((GeneralPath) shape).lineTo(294.33398, 146.43402);
        ((GeneralPath) shape).lineTo(291.65097, 146.43402);
        ((GeneralPath) shape).curveTo(291.65097, 147.02202, 291.71396,
                147.39702, 291.83896, 147.55902);
        ((GeneralPath) shape).curveTo(291.96396, 147.71902, 292.25797,
                147.80103, 292.72397, 147.80103);
        ((GeneralPath) shape).curveTo(293.16196, 147.80103, 293.44696,
                147.76402, 293.57797, 147.68602);
        ((GeneralPath) shape).curveTo(293.70798, 147.61002, 293.77496,
                147.44402, 293.77496, 147.18802);
        ((GeneralPath) shape).lineTo(293.776, 147.076);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_396;
        g.setTransform(defaultTransform__0_396);
        g.setClip(clip__0_396);
        float alpha__0_397 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_397 = g.getClip();
        AffineTransform defaultTransform__0_397 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_397 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(295.248, 144.47);
        ((GeneralPath) shape).lineTo(295.79398, 144.47);
        ((GeneralPath) shape).lineTo(295.73898, 144.9);
        ((GeneralPath) shape).lineTo(295.75098, 144.914);
        ((GeneralPath) shape).curveTo(295.96497, 144.56, 296.322, 144.386,
                296.81897, 144.386);
        ((GeneralPath) shape).curveTo(297.50696, 144.386, 297.84796, 144.74,
                297.84796, 145.449);
        ((GeneralPath) shape).lineTo(297.84497, 145.709);
        ((GeneralPath) shape).lineTo(297.30597, 145.709);
        ((GeneralPath) shape).lineTo(297.31796, 145.613);
        ((GeneralPath) shape).curveTo(297.32596, 145.515, 297.32996, 145.449,
                297.32996, 145.416);
        ((GeneralPath) shape).curveTo(297.32996, 145.031, 297.12296, 144.841,
                296.70596, 144.841);
        ((GeneralPath) shape).curveTo(296.09998, 144.841, 295.79398, 145.216,
                295.79398, 145.97);
        ((GeneralPath) shape).lineTo(295.79398, 148.20801);
        ((GeneralPath) shape).lineTo(295.248, 148.20801);
        ((GeneralPath) shape).lineTo(295.248, 144.47);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_397;
        g.setTransform(defaultTransform__0_397);
        g.setClip(clip__0_397);
        float alpha__0_398 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_398 = g.getClip();
        AffineTransform defaultTransform__0_398 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_398 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(304.707, 142.872);
        ((GeneralPath) shape).lineTo(304.707, 148.204);
        ((GeneralPath) shape).lineTo(304.106, 148.204);
        ((GeneralPath) shape).lineTo(304.106, 145.732);
        ((GeneralPath) shape).lineTo(300.995, 145.732);
        ((GeneralPath) shape).lineTo(300.995, 148.204);
        ((GeneralPath) shape).lineTo(300.395, 148.204);
        ((GeneralPath) shape).lineTo(300.395, 142.872);
        ((GeneralPath) shape).lineTo(300.995, 142.872);
        ((GeneralPath) shape).lineTo(300.995, 145.224);
        ((GeneralPath) shape).lineTo(304.106, 145.224);
        ((GeneralPath) shape).lineTo(304.106, 142.872);
        ((GeneralPath) shape).lineTo(304.707, 142.872);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_398;
        g.setTransform(defaultTransform__0_398);
        g.setClip(clip__0_398);
        float alpha__0_399 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_399 = g.getClip();
        AffineTransform defaultTransform__0_399 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_399 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(306.24, 144.47);
        ((GeneralPath) shape).lineTo(306.24, 148.204);
        ((GeneralPath) shape).lineTo(305.694, 148.204);
        ((GeneralPath) shape).lineTo(305.694, 144.47);
        ((GeneralPath) shape).lineTo(306.24, 144.47);
        ((GeneralPath) shape).moveTo(306.24, 142.872);
        ((GeneralPath) shape).lineTo(306.24, 143.48299);
        ((GeneralPath) shape).lineTo(305.694, 143.48299);
        ((GeneralPath) shape).lineTo(305.694, 142.872);
        ((GeneralPath) shape).lineTo(306.24, 142.872);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_399;
        g.setTransform(defaultTransform__0_399);
        g.setClip(clip__0_399);
        float alpha__0_400 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_400 = g.getClip();
        AffineTransform defaultTransform__0_400 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_400 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(309.175, 144.47);
        ((GeneralPath) shape).lineTo(309.175, 144.923);
        ((GeneralPath) shape).lineTo(307.73798, 144.923);
        ((GeneralPath) shape).lineTo(307.73798, 147.20801);
        ((GeneralPath) shape).curveTo(307.73798, 147.60501, 307.91296,
                147.80602, 308.26797, 147.80602);
        ((GeneralPath) shape).curveTo(308.619, 147.80602, 308.79398, 147.62802,
                308.79398, 147.27101);
        ((GeneralPath) shape).lineTo(308.79797, 147.087);
        ((GeneralPath) shape).lineTo(308.80597, 146.88);
        ((GeneralPath) shape).lineTo(309.31296, 146.88);
        ((GeneralPath) shape).lineTo(309.31696, 147.155);
        ((GeneralPath) shape).curveTo(309.31696, 147.88899, 308.96997, 148.259,
                308.27194, 148.259);
        ((GeneralPath) shape).curveTo(307.55194, 148.259, 307.19196, 147.95401,
                307.19196, 147.342);
        ((GeneralPath) shape).lineTo(307.19196, 144.924);
        ((GeneralPath) shape).lineTo(306.67694, 144.924);
        ((GeneralPath) shape).lineTo(306.67694, 144.471);
        ((GeneralPath) shape).lineTo(307.19196, 144.471);
        ((GeneralPath) shape).lineTo(307.19196, 143.573);
        ((GeneralPath) shape).lineTo(307.73795, 143.573);
        ((GeneralPath) shape).lineTo(307.73795, 144.471);
        ((GeneralPath) shape).lineTo(309.175, 144.47);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_400;
        g.setTransform(defaultTransform__0_400);
        g.setClip(clip__0_400);
        float alpha__0_401 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_401 = g.getClip();
        AffineTransform defaultTransform__0_401 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_401 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(334.896, 150.961);
        ((GeneralPath) shape).lineTo(334.896, 154.961);
        ((GeneralPath) shape).lineTo(334.44598, 154.961);
        ((GeneralPath) shape).lineTo(334.44598, 151.522);
        ((GeneralPath) shape).lineTo(334.44897, 151.401);
        ((GeneralPath) shape).lineTo(334.45197, 151.281);
        ((GeneralPath) shape).lineTo(334.43997, 151.281);
        ((GeneralPath) shape).lineTo(334.40497, 151.377);
        ((GeneralPath) shape).curveTo(334.38696, 151.424, 334.37695, 151.453,
                334.36996, 151.467);
        ((GeneralPath) shape).lineTo(334.29596, 151.655);
        ((GeneralPath) shape).lineTo(332.93695, 154.965);
        ((GeneralPath) shape).lineTo(332.48694, 154.965);
        ((GeneralPath) shape).lineTo(331.12393, 151.694);
        ((GeneralPath) shape).lineTo(331.04492, 151.509);
        ((GeneralPath) shape).lineTo(331.01193, 151.41501);
        ((GeneralPath) shape).curveTo(331.00192, 151.39401, 330.99094,
                151.36201, 330.97693, 151.32301);
        ((GeneralPath) shape).lineTo(330.96393, 151.32301);
        ((GeneralPath) shape).lineTo(330.96893, 151.43102);
        ((GeneralPath) shape).lineTo(330.97095, 151.54301);
        ((GeneralPath) shape).lineTo(330.97095, 154.96202);
        ((GeneralPath) shape).lineTo(330.52094, 154.96202);
        ((GeneralPath) shape).lineTo(330.52094, 150.96202);
        ((GeneralPath) shape).lineTo(331.30194, 150.96202);
        ((GeneralPath) shape).lineTo(332.36493, 153.54903);
        ((GeneralPath) shape).lineTo(332.53394, 153.96803);
        ((GeneralPath) shape).lineTo(332.61893, 154.17603);
        ((GeneralPath) shape).lineTo(332.70093, 154.38402);
        ((GeneralPath) shape).lineTo(332.71292, 154.38402);
        ((GeneralPath) shape).lineTo(332.79492, 154.17603);
        ((GeneralPath) shape).curveTo(332.8359, 154.07602, 332.8619, 154.00803,
                332.87692, 153.96803);
        ((GeneralPath) shape).lineTo(333.0519, 153.55203);
        ((GeneralPath) shape).lineTo(334.10992, 150.96204);
        ((GeneralPath) shape).lineTo(334.896, 150.96204);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_401;
        g.setTransform(defaultTransform__0_401);
        g.setClip(clip__0_401);
        float alpha__0_402 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_402 = g.getClip();
        AffineTransform defaultTransform__0_402 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_402 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(336.787, 152.458);
        ((GeneralPath) shape).curveTo(336.427, 152.458, 336.201, 152.517,
                336.11298, 152.635);
        ((GeneralPath) shape).curveTo(336.02597, 152.752, 335.98196, 153.06099,
                335.98196, 153.55899);
        ((GeneralPath) shape).curveTo(335.98196, 154.05699, 336.02496,
                154.36499, 336.11298, 154.48299);
        ((GeneralPath) shape).curveTo(336.19897, 154.59999, 336.42596,
                154.66199, 336.787, 154.66199);
        ((GeneralPath) shape).curveTo(337.151, 154.66199, 337.378, 154.60098,
                337.46597, 154.48299);
        ((GeneralPath) shape).curveTo(337.55197, 154.36598, 337.59598,
                154.05699, 337.59598, 153.55899);
        ((GeneralPath) shape).curveTo(337.59598, 153.06099, 337.554, 152.75299,
                337.46597, 152.635);
        ((GeneralPath) shape).curveTo(337.38, 152.519, 337.152, 152.458,
                336.787, 152.458);
        ((GeneralPath) shape).moveTo(336.787, 152.119);
        ((GeneralPath) shape).curveTo(337.305, 152.119, 337.63998, 152.20801,
                337.79498, 152.389);
        ((GeneralPath) shape).curveTo(337.94598, 152.567, 338.024, 152.95901,
                338.024, 153.561);
        ((GeneralPath) shape).curveTo(338.024, 154.163, 337.948, 154.55301,
                337.79498, 154.733);
        ((GeneralPath) shape).curveTo(337.64197, 154.913, 337.30698, 155.004,
                336.787, 155.004);
        ((GeneralPath) shape).curveTo(336.27197, 155.004, 335.939, 154.915,
                335.784, 154.733);
        ((GeneralPath) shape).curveTo(335.633, 154.554, 335.555, 154.16501,
                335.555, 153.561);
        ((GeneralPath) shape).curveTo(335.555, 152.96, 335.63098, 152.569,
                335.784, 152.389);
        ((GeneralPath) shape).curveTo(335.938, 152.209, 336.272, 152.119,
                336.787, 152.119);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_402;
        g.setTransform(defaultTransform__0_402);
        g.setClip(clip__0_402);
        float alpha__0_403 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_403 = g.getClip();
        AffineTransform defaultTransform__0_403 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_403 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(339.768, 152.458);
        ((GeneralPath) shape).curveTo(339.466, 152.458, 339.265, 152.525,
                339.164, 152.66699);
        ((GeneralPath) shape).curveTo(339.064, 152.806, 339.017, 153.08899,
                339.017, 153.515);
        ((GeneralPath) shape).curveTo(339.017, 153.984, 339.066, 154.29199,
                339.163, 154.44);
        ((GeneralPath) shape).curveTo(339.264, 154.586, 339.468, 154.66301,
                339.776, 154.66301);
        ((GeneralPath) shape).curveTo(340.112, 154.66301, 340.332, 154.589,
                340.433, 154.44);
        ((GeneralPath) shape).curveTo(340.535, 154.294, 340.58502, 153.972,
                340.58502, 153.478);
        ((GeneralPath) shape).curveTo(340.58502, 153.08, 340.527, 152.80899,
                340.41602, 152.668);
        ((GeneralPath) shape).curveTo(340.307, 152.529, 340.091, 152.458,
                339.768, 152.458);
        ((GeneralPath) shape).moveTo(340.998, 150.961);
        ((GeneralPath) shape).lineTo(340.998, 154.961);
        ((GeneralPath) shape).lineTo(340.59097, 154.961);
        ((GeneralPath) shape).lineTo(340.61197, 154.59799);
        ((GeneralPath) shape).lineTo(340.59998, 154.59499);
        ((GeneralPath) shape).curveTo(340.47098, 154.86598, 340.17297,
                155.00198, 339.70297, 155.00198);
        ((GeneralPath) shape).curveTo(339.274, 155.00198, 338.98196, 154.89798,
                338.82397, 154.68898);
        ((GeneralPath) shape).curveTo(338.66898, 154.48099, 338.59097,
                154.09097, 338.59097, 153.51997);
        ((GeneralPath) shape).curveTo(338.59097, 152.99698, 338.66898,
                152.63097, 338.82596, 152.42598);
        ((GeneralPath) shape).curveTo(338.98297, 152.22198, 339.26495,
                152.11998, 339.67596, 152.11998);
        ((GeneralPath) shape).curveTo(340.18497, 152.11998, 340.48798,
                152.25198, 340.58298, 152.51498);
        ((GeneralPath) shape).lineTo(340.59396, 152.50998);
        ((GeneralPath) shape).lineTo(340.59396, 150.96298);
        ((GeneralPath) shape).lineTo(340.99997, 150.96298);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_403;
        g.setTransform(defaultTransform__0_403);
        g.setClip(clip__0_403);
        float alpha__0_404 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_404 = g.getClip();
        AffineTransform defaultTransform__0_404 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_404 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(342.125, 152.16);
        ((GeneralPath) shape).lineTo(342.125, 154.961);
        ((GeneralPath) shape).lineTo(341.718, 154.961);
        ((GeneralPath) shape).lineTo(341.718, 152.16);
        ((GeneralPath) shape).lineTo(342.125, 152.16);
        ((GeneralPath) shape).moveTo(342.125, 150.961);
        ((GeneralPath) shape).lineTo(342.125, 151.421);
        ((GeneralPath) shape).lineTo(341.718, 151.421);
        ((GeneralPath) shape).lineTo(341.718, 150.961);
        ((GeneralPath) shape).lineTo(342.125, 150.961);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_404;
        g.setTransform(defaultTransform__0_404);
        g.setClip(clip__0_404);
        float alpha__0_405 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_405 = g.getClip();
        AffineTransform defaultTransform__0_405 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_405 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(342.949, 154.96);
        ((GeneralPath) shape).lineTo(342.949, 152.5);
        ((GeneralPath) shape).lineTo(342.522, 152.5);
        ((GeneralPath) shape).lineTo(342.522, 152.16);
        ((GeneralPath) shape).lineTo(342.949, 152.16);
        ((GeneralPath) shape).lineTo(342.949, 151.744);
        ((GeneralPath) shape).curveTo(342.949, 151.194, 343.229, 150.92,
                343.796, 150.92);
        ((GeneralPath) shape).curveTo(343.88, 150.92, 343.978, 150.926,
                344.09198, 150.941);
        ((GeneralPath) shape).lineTo(344.09198, 151.28099);
        ((GeneralPath) shape).curveTo(343.96, 151.26799, 343.86298, 151.26,
                343.80197, 151.26);
        ((GeneralPath) shape).curveTo(343.50797, 151.26, 343.36096, 151.40599,
                343.36096, 151.705);
        ((GeneralPath) shape).lineTo(343.36096, 152.16);
        ((GeneralPath) shape).lineTo(344.09195, 152.16);
        ((GeneralPath) shape).lineTo(344.09195, 152.5);
        ((GeneralPath) shape).lineTo(343.36096, 152.5);
        ((GeneralPath) shape).lineTo(343.36096, 154.961);
        ((GeneralPath) shape).lineTo(342.949, 154.96);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_405;
        g.setTransform(defaultTransform__0_405);
        g.setClip(clip__0_405);
        float alpha__0_406 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_406 = g.getClip();
        AffineTransform defaultTransform__0_406 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_406 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(344.839, 152.16);
        ((GeneralPath) shape).lineTo(344.839, 154.961);
        ((GeneralPath) shape).lineTo(344.43, 154.961);
        ((GeneralPath) shape).lineTo(344.43, 152.16);
        ((GeneralPath) shape).lineTo(344.839, 152.16);
        ((GeneralPath) shape).moveTo(344.839, 150.961);
        ((GeneralPath) shape).lineTo(344.839, 151.421);
        ((GeneralPath) shape).lineTo(344.43, 151.421);
        ((GeneralPath) shape).lineTo(344.43, 150.961);
        ((GeneralPath) shape).lineTo(344.839, 150.961);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_406;
        g.setTransform(defaultTransform__0_406);
        g.setClip(clip__0_406);
        float alpha__0_407 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_407 = g.getClip();
        AffineTransform defaultTransform__0_407 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_407 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(347.497, 153.316);
        ((GeneralPath) shape).lineTo(347.49402, 153.185);
        ((GeneralPath) shape).curveTo(347.49402, 152.883, 347.44403, 152.685,
                347.34702, 152.595);
        ((GeneralPath) shape).curveTo(347.246, 152.505, 347.03403, 152.459,
                346.70102, 152.459);
        ((GeneralPath) shape).curveTo(346.37103, 152.459, 346.156, 152.512,
                346.05603, 152.619);
        ((GeneralPath) shape).curveTo(345.95804, 152.723, 345.90903, 152.957,
                345.90903, 153.315);
        ((GeneralPath) shape).lineTo(347.497, 153.315);
        ((GeneralPath) shape).moveTo(347.497, 154.114);
        ((GeneralPath) shape).lineTo(347.915, 154.114);
        ((GeneralPath) shape).lineTo(347.92, 154.218);
        ((GeneralPath) shape).curveTo(347.92, 154.508, 347.83002, 154.712,
                347.653, 154.829);
        ((GeneralPath) shape).curveTo(347.47702, 154.943, 347.17, 155.00299,
                346.72403, 155.00299);
        ((GeneralPath) shape).curveTo(346.20804, 155.00299, 345.87003, 154.907,
                345.71103, 154.71999);
        ((GeneralPath) shape).curveTo(345.55002, 154.53198, 345.47003,
                154.13599, 345.47003, 153.53198);
        ((GeneralPath) shape).curveTo(345.47003, 152.97398, 345.55002,
                152.59898, 345.71204, 152.40698);
        ((GeneralPath) shape).curveTo(345.87103, 152.21799, 346.19104,
                152.12198, 346.66003, 152.12198);
        ((GeneralPath) shape).curveTo(347.17703, 152.12198, 347.51404,
                152.20398, 347.67404, 152.37097);
        ((GeneralPath) shape).curveTo(347.83304, 152.53596, 347.91205,
                152.88698, 347.91205, 153.41997);
        ((GeneralPath) shape).lineTo(347.91205, 153.63997);
        ((GeneralPath) shape).lineTo(345.90005, 153.63997);
        ((GeneralPath) shape).curveTo(345.90005, 154.08096, 345.94705,
                154.36197, 346.04407, 154.48396);
        ((GeneralPath) shape).curveTo(346.13806, 154.60396, 346.35706,
                154.66797, 346.70407, 154.66797);
        ((GeneralPath) shape).curveTo(347.03308, 154.66797, 347.24808,
                154.63997, 347.34808, 154.58197);
        ((GeneralPath) shape).curveTo(347.44708, 154.52296, 347.49408,
                154.39796, 347.49408, 154.20798);
        ((GeneralPath) shape).lineTo(347.497, 154.114);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_407;
        g.setTransform(defaultTransform__0_407);
        g.setClip(clip__0_407);
        float alpha__0_408 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_408 = g.getClip();
        AffineTransform defaultTransform__0_408 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_408 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(348.599, 152.16);
        ((GeneralPath) shape).lineTo(349.008, 152.16);
        ((GeneralPath) shape).lineTo(348.967, 152.48001);
        ((GeneralPath) shape).lineTo(348.976, 152.49);
        ((GeneralPath) shape).curveTo(349.13702, 152.225, 349.405, 152.09401,
                349.778, 152.09401);
        ((GeneralPath) shape).curveTo(350.295, 152.09401, 350.549, 152.35901,
                350.549, 152.891);
        ((GeneralPath) shape).lineTo(350.54602, 153.084);
        ((GeneralPath) shape).lineTo(350.143, 153.084);
        ((GeneralPath) shape).lineTo(350.152, 153.01399);
        ((GeneralPath) shape).curveTo(350.15802, 152.93999, 350.161, 152.89099,
                350.161, 152.86499);
        ((GeneralPath) shape).curveTo(350.161, 152.579, 350.008, 152.43399,
                349.69302, 152.43399);
        ((GeneralPath) shape).curveTo(349.23703, 152.43399, 349.00803, 152.715,
                349.00803, 153.28099);
        ((GeneralPath) shape).lineTo(349.00803, 154.96199);
        ((GeneralPath) shape).lineTo(348.59903, 154.96199);
        ((GeneralPath) shape).lineTo(348.59903, 152.16);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_408;
        g.setTransform(defaultTransform__0_408);
        g.setClip(clip__0_408);
        float alpha__0_409 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_409 = g.getClip();
        AffineTransform defaultTransform__0_409 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_409 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(353.923, 152.16);
        ((GeneralPath) shape).lineTo(353.923, 152.5);
        ((GeneralPath) shape).lineTo(352.84702, 152.5);
        ((GeneralPath) shape).lineTo(352.84702, 154.213);
        ((GeneralPath) shape).curveTo(352.84702, 154.512, 352.97803, 154.662,
                353.243, 154.662);
        ((GeneralPath) shape).curveTo(353.509, 154.662, 353.639, 154.52701,
                353.639, 154.261);
        ((GeneralPath) shape).lineTo(353.642, 154.123);
        ((GeneralPath) shape).lineTo(353.648, 153.968);
        ((GeneralPath) shape).lineTo(354.028, 153.968);
        ((GeneralPath) shape).lineTo(354.032, 154.176);
        ((GeneralPath) shape).curveTo(354.032, 154.72699, 353.77103, 155.002,
                353.24802, 155.002);
        ((GeneralPath) shape).curveTo(352.708, 155.002, 352.43802, 154.773,
                352.43802, 154.314);
        ((GeneralPath) shape).lineTo(352.43802, 152.5);
        ((GeneralPath) shape).lineTo(352.05103, 152.5);
        ((GeneralPath) shape).lineTo(352.05103, 152.16);
        ((GeneralPath) shape).lineTo(352.43802, 152.16);
        ((GeneralPath) shape).lineTo(352.43802, 151.48401);
        ((GeneralPath) shape).lineTo(352.84702, 151.48401);
        ((GeneralPath) shape).lineTo(352.84702, 152.16);
        ((GeneralPath) shape).lineTo(353.923, 152.16);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_409;
        g.setTransform(defaultTransform__0_409);
        g.setClip(clip__0_409);
        float alpha__0_410 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_410 = g.getClip();
        AffineTransform defaultTransform__0_410 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_410 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(355.547, 152.458);
        ((GeneralPath) shape).curveTo(355.184, 152.458, 354.95898, 152.517,
                354.87, 152.635);
        ((GeneralPath) shape).curveTo(354.784, 152.752, 354.74, 153.06099,
                354.74, 153.55899);
        ((GeneralPath) shape).curveTo(354.74, 154.05699, 354.783, 154.36499,
                354.87, 154.48299);
        ((GeneralPath) shape).curveTo(354.957, 154.59999, 355.18298, 154.66199,
                355.547, 154.66199);
        ((GeneralPath) shape).curveTo(355.908, 154.66199, 356.136, 154.60098,
                356.224, 154.48299);
        ((GeneralPath) shape).curveTo(356.31, 154.36598, 356.354, 154.05699,
                356.354, 153.55899);
        ((GeneralPath) shape).curveTo(356.354, 153.06099, 356.312, 152.75299,
                356.224, 152.635);
        ((GeneralPath) shape).curveTo(356.135, 152.519, 355.91, 152.458,
                355.547, 152.458);
        ((GeneralPath) shape).moveTo(355.547, 152.119);
        ((GeneralPath) shape).curveTo(356.063, 152.119, 356.398, 152.20801,
                356.553, 152.389);
        ((GeneralPath) shape).curveTo(356.704, 152.567, 356.782, 152.95901,
                356.782, 153.561);
        ((GeneralPath) shape).curveTo(356.782, 154.163, 356.70602, 154.55301,
                356.553, 154.733);
        ((GeneralPath) shape).curveTo(356.39902, 154.913, 356.064, 155.004,
                355.547, 155.004);
        ((GeneralPath) shape).curveTo(355.03, 155.004, 354.69598, 154.915,
                354.541, 154.733);
        ((GeneralPath) shape).curveTo(354.38998, 154.554, 354.31198, 154.16501,
                354.31198, 153.561);
        ((GeneralPath) shape).curveTo(354.31198, 152.96, 354.38797, 152.569,
                354.541, 152.389);
        ((GeneralPath) shape).curveTo(354.695, 152.209, 355.03, 152.119,
                355.547, 152.119);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_410;
        g.setTransform(defaultTransform__0_410);
        g.setClip(clip__0_410);
        float alpha__0_411 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_411 = g.getClip();
        AffineTransform defaultTransform__0_411 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_411 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(359.388, 154.58);
        ((GeneralPath) shape).lineTo(360.689, 154.58);
        ((GeneralPath) shape).curveTo(361.166, 154.58, 361.474, 154.483,
                361.61398, 154.293);
        ((GeneralPath) shape).curveTo(361.753, 154.103, 361.82098, 153.675,
                361.82098, 153.013);
        ((GeneralPath) shape).curveTo(361.82098, 152.286, 361.761, 151.826,
                361.641, 151.633);
        ((GeneralPath) shape).curveTo(361.521, 151.43999, 361.235, 151.343,
                360.78, 151.343);
        ((GeneralPath) shape).lineTo(359.388, 151.343);
        ((GeneralPath) shape).lineTo(359.388, 154.58);
        ((GeneralPath) shape).moveTo(358.938, 154.96);
        ((GeneralPath) shape).lineTo(358.938, 150.96);
        ((GeneralPath) shape).lineTo(360.78598, 150.96);
        ((GeneralPath) shape).curveTo(361.356, 150.96, 361.744, 151.08601,
                361.947, 151.33801);
        ((GeneralPath) shape).curveTo(362.14798, 151.59001, 362.253, 152.07,
                362.253, 152.78502);
        ((GeneralPath) shape).curveTo(362.253, 153.65002, 362.162, 154.22902,
                361.982, 154.52002);
        ((GeneralPath) shape).curveTo(361.802, 154.81102, 361.443, 154.95802,
                360.902, 154.95802);
        ((GeneralPath) shape).lineTo(358.938, 154.96);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_411;
        g.setTransform(defaultTransform__0_411);
        g.setClip(clip__0_411);
        float alpha__0_412 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_412 = g.getClip();
        AffineTransform defaultTransform__0_412 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_412 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(362.867, 152.16);
        ((GeneralPath) shape).lineTo(363.277, 152.16);
        ((GeneralPath) shape).lineTo(363.23602, 152.48001);
        ((GeneralPath) shape).lineTo(363.24503, 152.49);
        ((GeneralPath) shape).curveTo(363.40503, 152.225, 363.67502, 152.09401,
                364.04803, 152.09401);
        ((GeneralPath) shape).curveTo(364.56305, 152.09401, 364.81903,
                152.35901, 364.81903, 152.891);
        ((GeneralPath) shape).lineTo(364.81702, 153.084);
        ((GeneralPath) shape).lineTo(364.41302, 153.084);
        ((GeneralPath) shape).lineTo(364.424, 153.01399);
        ((GeneralPath) shape).curveTo(364.43002, 152.93999, 364.433, 152.89099,
                364.433, 152.86499);
        ((GeneralPath) shape).curveTo(364.433, 152.579, 364.278, 152.43399,
                363.96503, 152.43399);
        ((GeneralPath) shape).curveTo(363.50903, 152.43399, 363.27902, 152.715,
                363.27902, 153.28099);
        ((GeneralPath) shape).lineTo(363.27902, 154.96199);
        ((GeneralPath) shape).lineTo(362.86902, 154.96199);
        ((GeneralPath) shape).lineTo(362.867, 152.16);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_412;
        g.setTransform(defaultTransform__0_412);
        g.setClip(clip__0_412);
        float alpha__0_413 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_413 = g.getClip();
        AffineTransform defaultTransform__0_413 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_413 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(365.619, 152.16);
        ((GeneralPath) shape).lineTo(365.619, 154.961);
        ((GeneralPath) shape).lineTo(365.20898, 154.961);
        ((GeneralPath) shape).lineTo(365.20898, 152.16);
        ((GeneralPath) shape).lineTo(365.619, 152.16);
        ((GeneralPath) shape).moveTo(365.619, 150.961);
        ((GeneralPath) shape).lineTo(365.619, 151.421);
        ((GeneralPath) shape).lineTo(365.20898, 151.421);
        ((GeneralPath) shape).lineTo(365.20898, 150.961);
        ((GeneralPath) shape).lineTo(365.619, 150.961);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_413;
        g.setTransform(defaultTransform__0_413);
        g.setClip(clip__0_413);
        float alpha__0_414 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_414 = g.getClip();
        AffineTransform defaultTransform__0_414 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_414 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(368.405, 152.16);
        ((GeneralPath) shape).lineTo(367.47, 154.96);
        ((GeneralPath) shape).lineTo(366.884, 154.96);
        ((GeneralPath) shape).lineTo(365.954, 152.16);
        ((GeneralPath) shape).lineTo(366.378, 152.16);
        ((GeneralPath) shape).lineTo(366.87, 153.683);
        ((GeneralPath) shape).lineTo(367.024, 154.16);
        ((GeneralPath) shape).lineTo(367.098, 154.4);
        ((GeneralPath) shape).lineTo(367.176, 154.641);
        ((GeneralPath) shape).lineTo(367.186, 154.641);
        ((GeneralPath) shape).lineTo(367.258, 154.404);
        ((GeneralPath) shape).lineTo(367.326, 154.164);
        ((GeneralPath) shape).lineTo(367.475, 153.689);
        ((GeneralPath) shape).lineTo(367.949, 152.16);
        ((GeneralPath) shape).lineTo(368.405, 152.16);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_414;
        g.setTransform(defaultTransform__0_414);
        g.setClip(clip__0_414);
        float alpha__0_415 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_415 = g.getClip();
        AffineTransform defaultTransform__0_415 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_415 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(369.15, 152.16);
        ((GeneralPath) shape).lineTo(369.15, 154.961);
        ((GeneralPath) shape).lineTo(368.741, 154.961);
        ((GeneralPath) shape).lineTo(368.741, 152.16);
        ((GeneralPath) shape).lineTo(369.15, 152.16);
        ((GeneralPath) shape).moveTo(369.15, 150.961);
        ((GeneralPath) shape).lineTo(369.15, 151.421);
        ((GeneralPath) shape).lineTo(368.741, 151.421);
        ((GeneralPath) shape).lineTo(368.741, 150.961);
        ((GeneralPath) shape).lineTo(369.15, 150.961);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_415;
        g.setTransform(defaultTransform__0_415);
        g.setClip(clip__0_415);
        float alpha__0_416 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_416 = g.getClip();
        AffineTransform defaultTransform__0_416 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_416 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(369.867, 152.16);
        ((GeneralPath) shape).lineTo(370.263, 152.16);
        ((GeneralPath) shape).lineTo(370.249, 152.541);
        ((GeneralPath) shape).lineTo(370.263, 152.55);
        ((GeneralPath) shape).curveTo(370.387, 152.263, 370.697, 152.119,
                371.193, 152.119);
        ((GeneralPath) shape).curveTo(371.59198, 152.119, 371.862, 152.187,
                372.00598, 152.33);
        ((GeneralPath) shape).curveTo(372.14697, 152.47101, 372.218, 152.738,
                372.218, 153.139);
        ((GeneralPath) shape).lineTo(372.218, 154.96101);
        ((GeneralPath) shape).lineTo(371.809, 154.96101);
        ((GeneralPath) shape).lineTo(371.809, 153.06601);
        ((GeneralPath) shape).curveTo(371.809, 152.828, 371.763, 152.667,
                371.671, 152.58301);
        ((GeneralPath) shape).curveTo(371.578, 152.501, 371.401, 152.45801,
                371.142, 152.45801);
        ((GeneralPath) shape).curveTo(370.568, 152.45801, 370.28, 152.729,
                370.28, 153.27301);
        ((GeneralPath) shape).lineTo(370.28, 154.96);
        ((GeneralPath) shape).lineTo(369.871, 154.96);
        ((GeneralPath) shape).lineTo(369.871, 152.16);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_416;
        g.setTransform(defaultTransform__0_416);
        g.setClip(clip__0_416);
        float alpha__0_417 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_417 = g.getClip();
        AffineTransform defaultTransform__0_417 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_417 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(374.076, 152.458);
        ((GeneralPath) shape).curveTo(373.757, 152.458, 373.54898, 152.523,
                373.44998, 152.66199);
        ((GeneralPath) shape).curveTo(373.35498, 152.79698, 373.30597, 153.094,
                373.30597, 153.55399);
        ((GeneralPath) shape).curveTo(373.30597, 154.01698, 373.35297,
                154.31699, 373.44998, 154.45499);
        ((GeneralPath) shape).curveTo(373.54697, 154.59299, 373.757, 154.66199,
                374.076, 154.66199);
        ((GeneralPath) shape).curveTo(374.4, 154.66199, 374.615, 154.58699,
                374.722, 154.43298);
        ((GeneralPath) shape).curveTo(374.82397, 154.28398, 374.874, 153.96698,
                374.874, 153.48798);
        ((GeneralPath) shape).curveTo(374.874, 153.06598, 374.823, 152.79097,
                374.722, 152.65598);
        ((GeneralPath) shape).curveTo(374.617, 152.525, 374.402, 152.458,
                374.076, 152.458);
        ((GeneralPath) shape).moveTo(375.298, 152.16);
        ((GeneralPath) shape).lineTo(375.298, 155.14);
        ((GeneralPath) shape).curveTo(375.298, 155.546, 375.209, 155.828,
                375.032, 155.978);
        ((GeneralPath) shape).curveTo(374.85602, 156.12999, 374.535, 156.207,
                374.06702, 156.207);
        ((GeneralPath) shape).curveTo(373.65002, 156.207, 373.36502, 156.143,
                373.213, 156.014);
        ((GeneralPath) shape).curveTo(373.061, 155.88501, 372.984, 155.645,
                372.984, 155.29001);
        ((GeneralPath) shape).lineTo(373.38, 155.29001);
        ((GeneralPath) shape).curveTo(373.38, 155.52802, 373.424, 155.686,
                373.514, 155.759);
        ((GeneralPath) shape).curveTo(373.601, 155.83101, 373.795, 155.867,
                374.09702, 155.867);
        ((GeneralPath) shape).curveTo(374.40903, 155.867, 374.618, 155.81801,
                374.726, 155.724);
        ((GeneralPath) shape).curveTo(374.83002, 155.63, 374.88602, 155.449,
                374.88602, 155.173);
        ((GeneralPath) shape).lineTo(374.88602, 154.61);
        ((GeneralPath) shape).lineTo(374.877, 154.607);
        ((GeneralPath) shape).curveTo(374.773, 154.868, 374.46802, 154.999,
                373.96503, 154.999);
        ((GeneralPath) shape).curveTo(373.55304, 154.999, 373.26804, 154.896,
                373.11102, 154.691);
        ((GeneralPath) shape).curveTo(372.95602, 154.487, 372.87903, 154.11699,
                372.87903, 153.575);
        ((GeneralPath) shape).curveTo(372.87903, 153.013, 372.95602, 152.632,
                373.11502, 152.425);
        ((GeneralPath) shape).curveTo(373.273, 152.22, 373.56802, 152.116,
                374.00003, 152.116);
        ((GeneralPath) shape).curveTo(374.45905, 152.116, 374.76004, 152.257,
                374.89703, 152.538);
        ((GeneralPath) shape).lineTo(374.90604, 152.53499);
        ((GeneralPath) shape).lineTo(374.88504, 152.15799);
        ((GeneralPath) shape).lineTo(375.298, 152.16);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_417;
        g.setTransform(defaultTransform__0_417);
        g.setClip(clip__0_417);
        float alpha__0_418 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_418 = g.getClip();
        AffineTransform defaultTransform__0_418 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_418 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(333.254, 159.245);
        ((GeneralPath) shape).lineTo(332.807, 159.245);
        ((GeneralPath) shape).curveTo(332.807, 158.92299, 332.754, 158.719,
                332.646, 158.631);
        ((GeneralPath) shape).curveTo(332.54, 158.545, 332.287, 158.50099,
                331.889, 158.50099);
        ((GeneralPath) shape).curveTo(331.41602, 158.50099, 331.114, 158.54199,
                330.97702, 158.62599);
        ((GeneralPath) shape).curveTo(330.84003, 158.708, 330.77203, 158.894,
                330.77203, 159.18098);
        ((GeneralPath) shape).curveTo(330.77203, 159.50299, 330.82504,
                159.69998, 330.93304, 159.76999);
        ((GeneralPath) shape).curveTo(331.03705, 159.84, 331.35803, 159.887,
                331.88904, 159.91599);
        ((GeneralPath) shape).curveTo(332.51004, 159.93999, 332.90503,
                160.01999, 333.07504, 160.15099);
        ((GeneralPath) shape).curveTo(333.24304, 160.28099, 333.32803,
                160.57399, 333.32803, 161.02998);
        ((GeneralPath) shape).curveTo(333.32803, 161.52199, 333.23105,
                161.84198, 333.03705, 161.98499);
        ((GeneralPath) shape).curveTo(332.84604, 162.13098, 332.41705,
                162.20398, 331.75406, 162.20398);
        ((GeneralPath) shape).curveTo(331.18005, 162.20398, 330.79605,
                162.13197, 330.60706, 161.98398);
        ((GeneralPath) shape).curveTo(330.41806, 161.83897, 330.32205,
                161.54597, 330.32205, 161.10397);
        ((GeneralPath) shape).lineTo(330.31906, 160.92497);
        ((GeneralPath) shape).lineTo(330.76605, 160.92497);
        ((GeneralPath) shape).lineTo(330.76605, 161.02498);
        ((GeneralPath) shape).curveTo(330.76605, 161.38197, 330.82004,
                161.60397, 330.93005, 161.69199);
        ((GeneralPath) shape).curveTo(331.03506, 161.77798, 331.31906,
                161.82199, 331.76904, 161.82199);
        ((GeneralPath) shape).curveTo(332.28503, 161.82199, 332.60205,
                161.77998, 332.72205, 161.69199);
        ((GeneralPath) shape).curveTo(332.84006, 161.60599, 332.90103,
                161.37599, 332.90103, 160.99799);
        ((GeneralPath) shape).curveTo(332.90103, 160.75499, 332.86102,
                160.59198, 332.77902, 160.50998);
        ((GeneralPath) shape).curveTo(332.69904, 160.42899, 332.53003,
                160.38098, 332.27203, 160.36398);
        ((GeneralPath) shape).lineTo(331.80405, 160.34299);
        ((GeneralPath) shape).lineTo(331.36005, 160.31999);
        ((GeneralPath) shape).curveTo(330.68405, 160.273, 330.34503, 159.924,
                330.34503, 159.265);
        ((GeneralPath) shape).curveTo(330.34503, 158.811, 330.44302, 158.506,
                330.64005, 158.354);
        ((GeneralPath) shape).curveTo(330.83606, 158.203, 331.22903, 158.125,
                331.81903, 158.125);
        ((GeneralPath) shape).curveTo(332.41602, 158.125, 332.80402, 158.193,
                332.98602, 158.337);
        ((GeneralPath) shape).curveTo(333.161, 158.473, 333.254, 158.778,
                333.254, 159.245);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_418;
        g.setTransform(defaultTransform__0_418);
        g.setClip(clip__0_418);
        float alpha__0_419 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_419 = g.getClip();
        AffineTransform defaultTransform__0_419 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_419 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(334.258, 158.161);
        ((GeneralPath) shape).lineTo(334.258, 160.484);
        ((GeneralPath) shape).lineTo(334.443, 160.484);
        ((GeneralPath) shape).lineTo(335.432, 159.359);
        ((GeneralPath) shape).lineTo(335.946, 159.359);
        ((GeneralPath) shape).lineTo(334.75, 160.646);
        ((GeneralPath) shape).lineTo(336.162, 162.16);
        ((GeneralPath) shape).lineTo(335.609, 162.16);
        ((GeneralPath) shape).lineTo(334.425, 160.804);
        ((GeneralPath) shape).lineTo(334.258, 160.804);
        ((GeneralPath) shape).lineTo(334.258, 162.16);
        ((GeneralPath) shape).lineTo(333.847, 162.16);
        ((GeneralPath) shape).lineTo(333.847, 158.161);
        ((GeneralPath) shape).lineTo(334.258, 158.161);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_419;
        g.setTransform(defaultTransform__0_419);
        g.setClip(clip__0_419);
        float alpha__0_420 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_420 = g.getClip();
        AffineTransform defaultTransform__0_420 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_420 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(336.829, 159.359);
        ((GeneralPath) shape).lineTo(336.829, 162.16199);
        ((GeneralPath) shape).lineTo(336.422, 162.16199);
        ((GeneralPath) shape).lineTo(336.422, 159.359);
        ((GeneralPath) shape).lineTo(336.829, 159.359);
        ((GeneralPath) shape).moveTo(336.829, 158.161);
        ((GeneralPath) shape).lineTo(336.829, 158.621);
        ((GeneralPath) shape).lineTo(336.422, 158.621);
        ((GeneralPath) shape).lineTo(336.422, 158.161);
        ((GeneralPath) shape).lineTo(336.829, 158.161);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_420;
        g.setTransform(defaultTransform__0_420);
        g.setClip(clip__0_420);
        float alpha__0_421 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_421 = g.getClip();
        AffineTransform defaultTransform__0_421 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_421 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new Rectangle2D.Double(337.5459899902344, 158.16099548339844,
                0.40700000524520874, 3.999000072479248);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_421;
        g.setTransform(defaultTransform__0_421);
        g.setClip(clip__0_421);
        float alpha__0_422 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_422 = g.getClip();
        AffineTransform defaultTransform__0_422 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_422 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new Rectangle2D.Double(338.6700134277344, 158.16099548339844,
                0.40700000524520874, 3.999000072479248);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_422;
        g.setTransform(defaultTransform__0_422);
        g.setClip(clip__0_422);
        float alpha__0_423 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_423 = g.getClip();
        AffineTransform defaultTransform__0_423 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_423 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(341.33, 159.359);
        ((GeneralPath) shape).lineTo(341.73898, 159.359);
        ((GeneralPath) shape).lineTo(341.698, 159.681);
        ((GeneralPath) shape).lineTo(341.707, 159.69);
        ((GeneralPath) shape).curveTo(341.868, 159.426, 342.138, 159.294,
                342.51, 159.294);
        ((GeneralPath) shape).curveTo(343.02502, 159.294, 343.281, 159.559,
                343.281, 160.091);
        ((GeneralPath) shape).lineTo(343.278, 160.284);
        ((GeneralPath) shape).lineTo(342.875, 160.284);
        ((GeneralPath) shape).lineTo(342.886, 160.21399);
        ((GeneralPath) shape).curveTo(342.892, 160.14099, 342.895, 160.09099,
                342.895, 160.06499);
        ((GeneralPath) shape).curveTo(342.895, 159.77798, 342.74, 159.63399,
                342.427, 159.63399);
        ((GeneralPath) shape).curveTo(341.971, 159.63399, 341.74, 159.915,
                341.74, 160.48099);
        ((GeneralPath) shape).lineTo(341.74, 162.16199);
        ((GeneralPath) shape).lineTo(341.331, 162.16199);
        ((GeneralPath) shape).lineTo(341.331, 159.359);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_423;
        g.setTransform(defaultTransform__0_423);
        g.setClip(clip__0_423);
        float alpha__0_424 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_424 = g.getClip();
        AffineTransform defaultTransform__0_424 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_424 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(344.825, 159.658);
        ((GeneralPath) shape).curveTo(344.46503, 159.658, 344.239, 159.71701,
                344.151, 159.835);
        ((GeneralPath) shape).curveTo(344.065, 159.95201, 344.021, 160.261,
                344.021, 160.759);
        ((GeneralPath) shape).curveTo(344.021, 161.257, 344.064, 161.565,
                344.151, 161.683);
        ((GeneralPath) shape).curveTo(344.237, 161.8, 344.464, 161.86, 344.825,
                161.86);
        ((GeneralPath) shape).curveTo(345.18903, 161.86, 345.415, 161.801,
                345.505, 161.683);
        ((GeneralPath) shape).curveTo(345.591, 161.566, 345.635, 161.257,
                345.635, 160.759);
        ((GeneralPath) shape).curveTo(345.635, 160.261, 345.59302, 159.953,
                345.505, 159.835);
        ((GeneralPath) shape).curveTo(345.416, 159.718, 345.191, 159.658,
                344.825, 159.658);
        ((GeneralPath) shape).moveTo(344.825, 159.318);
        ((GeneralPath) shape).curveTo(345.34402, 159.318, 345.67902, 159.407,
                345.832, 159.58899);
        ((GeneralPath) shape).curveTo(345.98502, 159.76799, 346.062, 160.159,
                346.062, 160.76099);
        ((GeneralPath) shape).curveTo(346.062, 161.36198, 345.98602, 161.75299,
                345.832, 161.93298);
        ((GeneralPath) shape).curveTo(345.68, 162.11198, 345.345, 162.20398,
                344.825, 162.20398);
        ((GeneralPath) shape).curveTo(344.31, 162.20398, 343.97702, 162.11298,
                343.82202, 161.93298);
        ((GeneralPath) shape).curveTo(343.67102, 161.75398, 343.59302,
                161.36298, 343.59302, 160.76099);
        ((GeneralPath) shape).curveTo(343.59302, 160.15999, 343.669, 159.76898,
                343.82202, 159.58899);
        ((GeneralPath) shape).curveTo(343.977, 159.409, 344.311, 159.318,
                344.825, 159.318);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_424;
        g.setTransform(defaultTransform__0_424);
        g.setClip(clip__0_424);
        float alpha__0_425 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_425 = g.getClip();
        AffineTransform defaultTransform__0_425 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_425 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new Rectangle2D.Double(346.6990051269531, 158.16099548339844,
                0.4090000092983246, 3.999000072479248);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_425;
        g.setTransform(defaultTransform__0_425);
        g.setClip(clip__0_425);
        float alpha__0_426 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_426 = g.getClip();
        AffineTransform defaultTransform__0_426 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_426 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new Rectangle2D.Double(347.822998046875, 158.16099548339844,
                0.4090000092983246, 3.999000072479248);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_426;
        g.setTransform(defaultTransform__0_426);
        g.setClip(clip__0_426);
        float alpha__0_427 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_427 = g.getClip();
        AffineTransform defaultTransform__0_427 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_427 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(351.114, 160.092);
        ((GeneralPath) shape).lineTo(350.70502, 160.092);
        ((GeneralPath) shape).curveTo(350.70502, 159.89699, 350.665, 159.776,
                350.588, 159.72899);
        ((GeneralPath) shape).curveTo(350.509, 159.68199, 350.306, 159.661,
                349.979, 159.661);
        ((GeneralPath) shape).curveTo(349.67502, 159.661, 349.482, 159.68399,
                349.4, 159.734);
        ((GeneralPath) shape).curveTo(349.318, 159.786, 349.277, 159.902,
                349.277, 160.09299);
        ((GeneralPath) shape).curveTo(349.277, 160.37999, 349.415, 160.52998,
                349.69, 160.54399);
        ((GeneralPath) shape).lineTo(350.02, 160.562);
        ((GeneralPath) shape).lineTo(350.438, 160.583);
        ((GeneralPath) shape).curveTo(350.944, 160.60599, 351.19897, 160.87299,
                351.19897, 161.37999);
        ((GeneralPath) shape).curveTo(351.19897, 161.693, 351.11496, 161.90999,
                350.94696, 162.02599);
        ((GeneralPath) shape).curveTo(350.78098, 162.14299, 350.47296,
                162.20398, 350.02597, 162.20398);
        ((GeneralPath) shape).curveTo(349.56796, 162.20398, 349.25497,
                162.14598, 349.07996, 162.03697);
        ((GeneralPath) shape).curveTo(348.90796, 161.92598, 348.82095,
                161.72197, 348.82095, 161.42497);
        ((GeneralPath) shape).lineTo(348.82394, 161.27298);
        ((GeneralPath) shape).lineTo(349.24796, 161.27298);
        ((GeneralPath) shape).lineTo(349.25095, 161.40498);
        ((GeneralPath) shape).curveTo(349.25095, 161.58998, 349.29996,
                161.71298, 349.39395, 161.77298);
        ((GeneralPath) shape).curveTo(349.48795, 161.83598, 349.67096,
                161.86497, 349.94696, 161.86497);
        ((GeneralPath) shape).curveTo(350.28296, 161.86497, 350.50696,
                161.83498, 350.61197, 161.76797);
        ((GeneralPath) shape).curveTo(350.71698, 161.70497, 350.77197,
                161.56897, 350.77197, 161.36397);
        ((GeneralPath) shape).curveTo(350.77197, 161.06996, 350.63797,
                160.92397, 350.37097, 160.92397);
        ((GeneralPath) shape).curveTo(349.74997, 160.92397, 349.34296,
                160.87097, 349.14398, 160.76596);
        ((GeneralPath) shape).curveTo(348.94897, 160.66196, 348.84998,
                160.44196, 348.84998, 160.10995);
        ((GeneralPath) shape).curveTo(348.84998, 159.79695, 348.92798,
                159.58896, 349.08, 159.48195);
        ((GeneralPath) shape).curveTo(349.235, 159.37794, 349.54398, 159.32495,
                350.008, 159.32495);
        ((GeneralPath) shape).curveTo(350.745, 159.32495, 351.117, 159.54996,
                351.117, 159.99695);
        ((GeneralPath) shape).lineTo(351.114, 160.092);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_427;
        g.setTransform(defaultTransform__0_427);
        g.setClip(clip__0_427);
        float alpha__0_428 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_428 = g.getClip();
        AffineTransform defaultTransform__0_428 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_428 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(261.194, 152.713);
        ((GeneralPath) shape).lineTo(261.194, 156.71199);
        ((GeneralPath) shape).lineTo(260.744, 156.71199);
        ((GeneralPath) shape).lineTo(260.744, 153.273);
        ((GeneralPath) shape).lineTo(260.74698, 153.153);
        ((GeneralPath) shape).lineTo(260.74997, 153.035);
        ((GeneralPath) shape).lineTo(260.73798, 153.035);
        ((GeneralPath) shape).lineTo(260.70297, 153.129);
        ((GeneralPath) shape).curveTo(260.68497, 153.176, 260.67398, 153.205,
                260.66797, 153.22);
        ((GeneralPath) shape).lineTo(260.59198, 153.408);
        ((GeneralPath) shape).lineTo(259.23398, 156.718);
        ((GeneralPath) shape).lineTo(258.78397, 156.718);
        ((GeneralPath) shape).lineTo(257.42197, 153.447);
        ((GeneralPath) shape).lineTo(257.34296, 153.26201);
        ((GeneralPath) shape).lineTo(257.30997, 153.16801);
        ((GeneralPath) shape).curveTo(257.29898, 153.14702, 257.28897,
                153.11502, 257.27496, 153.07602);
        ((GeneralPath) shape).lineTo(257.26096, 153.07602);
        ((GeneralPath) shape).lineTo(257.26596, 153.18301);
        ((GeneralPath) shape).lineTo(257.26895, 153.294);
        ((GeneralPath) shape).lineTo(257.26895, 156.71301);
        ((GeneralPath) shape).lineTo(256.81894, 156.71301);
        ((GeneralPath) shape).lineTo(256.81894, 152.71402);
        ((GeneralPath) shape).lineTo(257.59995, 152.71402);
        ((GeneralPath) shape).lineTo(258.66293, 155.30103);
        ((GeneralPath) shape).lineTo(258.83295, 155.72003);
        ((GeneralPath) shape).lineTo(258.91794, 155.92802);
        ((GeneralPath) shape).lineTo(258.99994, 156.13602);
        ((GeneralPath) shape).lineTo(259.01193, 156.13602);
        ((GeneralPath) shape).lineTo(259.09393, 155.92802);
        ((GeneralPath) shape).curveTo(259.13492, 155.82802, 259.16092,
                155.76003, 259.17593, 155.72003);
        ((GeneralPath) shape).lineTo(259.34793, 155.30403);
        ((GeneralPath) shape).lineTo(260.40894, 152.71404);
        ((GeneralPath) shape).lineTo(261.194, 152.71404);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_428;
        g.setTransform(defaultTransform__0_428);
        g.setClip(clip__0_428);
        float alpha__0_429 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_429 = g.getClip();
        AffineTransform defaultTransform__0_429 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_429 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(263.085, 154.21);
        ((GeneralPath) shape).curveTo(262.725, 154.21, 262.499, 154.26901,
                262.41098, 154.38701);
        ((GeneralPath) shape).curveTo(262.32498, 154.50401, 262.28098, 154.813,
                262.28098, 155.311);
        ((GeneralPath) shape).curveTo(262.28098, 155.809, 262.32297, 156.119,
                262.41098, 156.235);
        ((GeneralPath) shape).curveTo(262.49698, 156.352, 262.72397, 156.414,
                263.085, 156.414);
        ((GeneralPath) shape).curveTo(263.449, 156.414, 263.67398, 156.353,
                263.76398, 156.235);
        ((GeneralPath) shape).curveTo(263.84998, 156.12, 263.89398, 155.809,
                263.89398, 155.311);
        ((GeneralPath) shape).curveTo(263.89398, 154.813, 263.852, 154.505,
                263.76398, 154.38701);
        ((GeneralPath) shape).curveTo(263.676, 154.27, 263.451, 154.21,
                263.085, 154.21);
        ((GeneralPath) shape).moveTo(263.085, 153.87);
        ((GeneralPath) shape).curveTo(263.603, 153.87, 263.938, 153.959,
                264.091, 154.14);
        ((GeneralPath) shape).curveTo(264.24402, 154.319, 264.322, 154.71,
                264.322, 155.312);
        ((GeneralPath) shape).curveTo(264.322, 155.915, 264.245, 156.304,
                264.091, 156.484);
        ((GeneralPath) shape).curveTo(263.94, 156.665, 263.605, 156.75499,
                263.085, 156.75499);
        ((GeneralPath) shape).curveTo(262.56998, 156.75499, 262.237, 156.66599,
                262.082, 156.484);
        ((GeneralPath) shape).curveTo(261.931, 156.305, 261.853, 155.916,
                261.853, 155.312);
        ((GeneralPath) shape).curveTo(261.853, 154.711, 261.929, 154.31999,
                262.082, 154.14);
        ((GeneralPath) shape).curveTo(262.235, 153.961, 262.57, 153.87,
                263.085, 153.87);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_429;
        g.setTransform(defaultTransform__0_429);
        g.setClip(clip__0_429);
        float alpha__0_430 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_430 = g.getClip();
        AffineTransform defaultTransform__0_430 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_430 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(266.064, 154.21);
        ((GeneralPath) shape).curveTo(265.763, 154.21, 265.561, 154.27701,
                265.46, 154.419);
        ((GeneralPath) shape).curveTo(265.36, 154.55801, 265.313, 154.841,
                265.313, 155.26701);
        ((GeneralPath) shape).curveTo(265.313, 155.73601, 265.361, 156.04301,
                265.45898, 156.19101);
        ((GeneralPath) shape).curveTo(265.559, 156.337, 265.76398, 156.41402,
                266.072, 156.41402);
        ((GeneralPath) shape).curveTo(266.408, 156.41402, 266.628, 156.34102,
                266.72998, 156.19101);
        ((GeneralPath) shape).curveTo(266.831, 156.04501, 266.87997, 155.72401,
                266.87997, 155.229);
        ((GeneralPath) shape).curveTo(266.87997, 154.83101, 266.82397, 154.56,
                266.71198, 154.419);
        ((GeneralPath) shape).curveTo(266.604, 154.28, 266.386, 154.21,
                266.064, 154.21);
        ((GeneralPath) shape).moveTo(267.295, 152.713);
        ((GeneralPath) shape).lineTo(267.295, 156.71199);
        ((GeneralPath) shape).lineTo(266.888, 156.71199);
        ((GeneralPath) shape).lineTo(266.909, 156.34898);
        ((GeneralPath) shape).lineTo(266.897, 156.34598);
        ((GeneralPath) shape).curveTo(266.768, 156.61697, 266.47, 156.75298,
                265.999, 156.75298);
        ((GeneralPath) shape).curveTo(265.57098, 156.75298, 265.27798,
                156.64897, 265.121, 156.43997);
        ((GeneralPath) shape).curveTo(264.964, 156.23198, 264.88602, 155.84196,
                264.88602, 155.27097);
        ((GeneralPath) shape).curveTo(264.88602, 154.74896, 264.96402,
                154.38196, 265.122, 154.17697);
        ((GeneralPath) shape).curveTo(265.28, 153.97298, 265.562, 153.87097,
                265.97202, 153.87097);
        ((GeneralPath) shape).curveTo(266.48102, 153.87097, 266.78403,
                154.00298, 266.87903, 154.26697);
        ((GeneralPath) shape).lineTo(266.89, 154.26097);
        ((GeneralPath) shape).lineTo(266.89, 152.71397);
        ((GeneralPath) shape).lineTo(267.295, 152.713);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_430;
        g.setTransform(defaultTransform__0_430);
        g.setClip(clip__0_430);
        float alpha__0_431 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_431 = g.getClip();
        AffineTransform defaultTransform__0_431 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_431 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(268.421, 153.911);
        ((GeneralPath) shape).lineTo(268.421, 156.71199);
        ((GeneralPath) shape).lineTo(268.01398, 156.71199);
        ((GeneralPath) shape).lineTo(268.01398, 153.911);
        ((GeneralPath) shape).lineTo(268.421, 153.911);
        ((GeneralPath) shape).moveTo(268.421, 152.713);
        ((GeneralPath) shape).lineTo(268.421, 153.173);
        ((GeneralPath) shape).lineTo(268.01398, 153.173);
        ((GeneralPath) shape).lineTo(268.01398, 152.713);
        ((GeneralPath) shape).lineTo(268.421, 152.713);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_431;
        g.setTransform(defaultTransform__0_431);
        g.setClip(clip__0_431);
        float alpha__0_432 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_432 = g.getClip();
        AffineTransform defaultTransform__0_432 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_432 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(269.245, 156.712);
        ((GeneralPath) shape).lineTo(269.245, 154.251);
        ((GeneralPath) shape).lineTo(268.818, 154.251);
        ((GeneralPath) shape).lineTo(268.818, 153.91101);
        ((GeneralPath) shape).lineTo(269.245, 153.91101);
        ((GeneralPath) shape).lineTo(269.245, 153.49501);
        ((GeneralPath) shape).curveTo(269.245, 152.945, 269.526, 152.67201,
                270.09, 152.67201);
        ((GeneralPath) shape).curveTo(270.173, 152.67201, 270.273, 152.67801,
                270.387, 152.69301);
        ((GeneralPath) shape).lineTo(270.387, 153.035);
        ((GeneralPath) shape).curveTo(270.253, 153.02, 270.158, 153.014,
                270.097, 153.014);
        ((GeneralPath) shape).curveTo(269.801, 153.014, 269.65598, 153.162,
                269.65598, 153.45901);
        ((GeneralPath) shape).lineTo(269.65598, 153.91301);
        ((GeneralPath) shape).lineTo(270.38498, 153.91301);
        ((GeneralPath) shape).lineTo(270.38498, 154.253);
        ((GeneralPath) shape).lineTo(269.65598, 154.253);
        ((GeneralPath) shape).lineTo(269.65598, 156.714);
        ((GeneralPath) shape).lineTo(269.245, 156.712);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_432;
        g.setTransform(defaultTransform__0_432);
        g.setClip(clip__0_432);
        float alpha__0_433 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_433 = g.getClip();
        AffineTransform defaultTransform__0_433 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_433 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(271.132, 153.911);
        ((GeneralPath) shape).lineTo(271.132, 156.71199);
        ((GeneralPath) shape).lineTo(270.72498, 156.71199);
        ((GeneralPath) shape).lineTo(270.72498, 153.911);
        ((GeneralPath) shape).lineTo(271.132, 153.911);
        ((GeneralPath) shape).moveTo(271.132, 152.713);
        ((GeneralPath) shape).lineTo(271.132, 153.173);
        ((GeneralPath) shape).lineTo(270.72498, 153.173);
        ((GeneralPath) shape).lineTo(270.72498, 152.713);
        ((GeneralPath) shape).lineTo(271.132, 152.713);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_433;
        g.setTransform(defaultTransform__0_433);
        g.setClip(clip__0_433);
        float alpha__0_434 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_434 = g.getClip();
        AffineTransform defaultTransform__0_434 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_434 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(273.792, 155.068);
        ((GeneralPath) shape).lineTo(273.789, 154.93599);
        ((GeneralPath) shape).curveTo(273.789, 154.63399, 273.739, 154.43599,
                273.642, 154.346);
        ((GeneralPath) shape).curveTo(273.541, 154.25499, 273.329, 154.20999,
                272.997, 154.20999);
        ((GeneralPath) shape).curveTo(272.665, 154.20999, 272.452, 154.26299,
                272.35202, 154.37);
        ((GeneralPath) shape).curveTo(272.252, 154.474, 272.20502, 154.708,
                272.20502, 155.067);
        ((GeneralPath) shape).lineTo(273.792, 155.067);
        ((GeneralPath) shape).moveTo(273.792, 155.865);
        ((GeneralPath) shape).lineTo(274.21, 155.865);
        ((GeneralPath) shape).lineTo(274.21298, 155.96901);
        ((GeneralPath) shape).curveTo(274.21298, 156.259, 274.12497, 156.46301,
                273.94797, 156.58);
        ((GeneralPath) shape).curveTo(273.77298, 156.694, 273.46298, 156.754,
                273.01996, 156.754);
        ((GeneralPath) shape).curveTo(272.50296, 156.754, 272.16595, 156.66,
                272.00494, 156.471);
        ((GeneralPath) shape).curveTo(271.84595, 156.28299, 271.76593, 155.887,
                271.76593, 155.28299);
        ((GeneralPath) shape).curveTo(271.76593, 154.72499, 271.84592,
                154.34999, 272.00693, 154.15799);
        ((GeneralPath) shape).curveTo(272.16794, 153.96999, 272.48593,
                153.87299, 272.95694, 153.87299);
        ((GeneralPath) shape).curveTo(273.47394, 153.87299, 273.80994,
                153.95499, 273.97095, 154.12198);
        ((GeneralPath) shape).curveTo(274.12894, 154.28798, 274.20895,
                154.63799, 274.20895, 155.17097);
        ((GeneralPath) shape).lineTo(274.20895, 155.39098);
        ((GeneralPath) shape).lineTo(272.19696, 155.39098);
        ((GeneralPath) shape).curveTo(272.19696, 155.83197, 272.24396,
                156.11298, 272.33896, 156.23497);
        ((GeneralPath) shape).curveTo(272.43497, 156.35497, 272.65295,
                156.41898, 273.00195, 156.41898);
        ((GeneralPath) shape).curveTo(273.33096, 156.41898, 273.54395,
                156.39098, 273.64597, 156.33298);
        ((GeneralPath) shape).curveTo(273.74396, 156.27397, 273.79196,
                156.14897, 273.79196, 155.95898);
        ((GeneralPath) shape).lineTo(273.79196, 155.86899);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_434;
        g.setTransform(defaultTransform__0_434);
        g.setClip(clip__0_434);
        float alpha__0_435 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_435 = g.getClip();
        AffineTransform defaultTransform__0_435 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_435 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(274.893, 153.911);
        ((GeneralPath) shape).lineTo(275.302, 153.911);
        ((GeneralPath) shape).lineTo(275.26102, 154.231);
        ((GeneralPath) shape).lineTo(275.269, 154.242);
        ((GeneralPath) shape).curveTo(275.43002, 153.976, 275.69702, 153.84601,
                276.07, 153.84601);
        ((GeneralPath) shape).curveTo(276.58502, 153.84601, 276.841, 154.11101,
                276.841, 154.643);
        ((GeneralPath) shape).lineTo(276.838, 154.836);
        ((GeneralPath) shape).lineTo(276.436, 154.836);
        ((GeneralPath) shape).lineTo(276.445, 154.76599);
        ((GeneralPath) shape).curveTo(276.45102, 154.693, 276.454, 154.64299,
                276.454, 154.61899);
        ((GeneralPath) shape).curveTo(276.454, 154.33199, 276.299, 154.18599,
                275.98602, 154.18599);
        ((GeneralPath) shape).curveTo(275.52902, 154.18599, 275.30002, 154.467,
                275.30002, 155.03299);
        ((GeneralPath) shape).lineTo(275.30002, 156.71399);
        ((GeneralPath) shape).lineTo(274.89203, 156.71399);
        ((GeneralPath) shape).lineTo(274.89203, 153.913);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_435;
        g.setTransform(defaultTransform__0_435);
        g.setClip(clip__0_435);
        float alpha__0_436 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_436 = g.getClip();
        AffineTransform defaultTransform__0_436 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_436 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(280.214, 153.911);
        ((GeneralPath) shape).lineTo(280.214, 154.25099);
        ((GeneralPath) shape).lineTo(279.13998, 154.25099);
        ((GeneralPath) shape).lineTo(279.13998, 155.965);
        ((GeneralPath) shape).curveTo(279.13998, 156.26399, 279.271, 156.413,
                279.53598, 156.413);
        ((GeneralPath) shape).curveTo(279.79898, 156.413, 279.93198, 156.278,
                279.93198, 156.012);
        ((GeneralPath) shape).lineTo(279.93497, 155.874);
        ((GeneralPath) shape).lineTo(279.94098, 155.719);
        ((GeneralPath) shape).lineTo(280.32098, 155.719);
        ((GeneralPath) shape).lineTo(280.32397, 155.92699);
        ((GeneralPath) shape).curveTo(280.32397, 156.47798, 280.06396,
                156.75299, 279.53998, 156.75299);
        ((GeneralPath) shape).curveTo(279.00098, 156.75299, 278.72998,
                156.52399, 278.72998, 156.06499);
        ((GeneralPath) shape).lineTo(278.72998, 154.25198);
        ((GeneralPath) shape).lineTo(278.344, 154.25198);
        ((GeneralPath) shape).lineTo(278.344, 153.91199);
        ((GeneralPath) shape).lineTo(278.72998, 153.91199);
        ((GeneralPath) shape).lineTo(278.72998, 153.236);
        ((GeneralPath) shape).lineTo(279.13898, 153.236);
        ((GeneralPath) shape).lineTo(279.13898, 153.91199);
        ((GeneralPath) shape).lineTo(280.214, 153.911);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_436;
        g.setTransform(defaultTransform__0_436);
        g.setClip(clip__0_436);
        float alpha__0_437 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_437 = g.getClip();
        AffineTransform defaultTransform__0_437 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_437 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(281.838, 154.21);
        ((GeneralPath) shape).curveTo(281.47702, 154.21, 281.252, 154.26901,
                281.164, 154.38701);
        ((GeneralPath) shape).curveTo(281.078, 154.50401, 281.034, 154.813,
                281.034, 155.311);
        ((GeneralPath) shape).curveTo(281.034, 155.809, 281.076, 156.119,
                281.164, 156.235);
        ((GeneralPath) shape).curveTo(281.25, 156.352, 281.477, 156.414,
                281.838, 156.414);
        ((GeneralPath) shape).curveTo(282.20203, 156.414, 282.42902, 156.353,
                282.517, 156.235);
        ((GeneralPath) shape).curveTo(282.603, 156.12, 282.647, 155.809,
                282.647, 155.311);
        ((GeneralPath) shape).curveTo(282.647, 154.813, 282.605, 154.505,
                282.517, 154.38701);
        ((GeneralPath) shape).curveTo(282.429, 154.27, 282.204, 154.21,
                281.838, 154.21);
        ((GeneralPath) shape).moveTo(281.838, 153.87);
        ((GeneralPath) shape).curveTo(282.35602, 153.87, 282.691, 153.959,
                282.846, 154.14);
        ((GeneralPath) shape).curveTo(282.997, 154.319, 283.075, 154.71,
                283.075, 155.312);
        ((GeneralPath) shape).curveTo(283.075, 155.915, 282.99902, 156.304,
                282.846, 156.484);
        ((GeneralPath) shape).curveTo(282.693, 156.665, 282.358, 156.75499,
                281.838, 156.75499);
        ((GeneralPath) shape).curveTo(281.323, 156.75499, 280.99002, 156.66599,
                280.83502, 156.484);
        ((GeneralPath) shape).curveTo(280.68402, 156.305, 280.60602, 155.916,
                280.60602, 155.312);
        ((GeneralPath) shape).curveTo(280.60602, 154.711, 280.682, 154.31999,
                280.83502, 154.14);
        ((GeneralPath) shape).curveTo(280.988, 153.961, 281.323, 153.87,
                281.838, 153.87);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_437;
        g.setTransform(defaultTransform__0_437);
        g.setClip(clip__0_437);
        float alpha__0_438 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_438 = g.getClip();
        AffineTransform defaultTransform__0_438 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_438 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(286.294, 155.326);
        ((GeneralPath) shape).curveTo(285.98102, 155.326, 285.776, 155.356,
                285.683, 155.42601);
        ((GeneralPath) shape).curveTo(285.587, 155.492, 285.539, 155.634,
                285.539, 155.854);
        ((GeneralPath) shape).curveTo(285.539, 156.082, 285.586, 156.23001,
                285.681, 156.304);
        ((GeneralPath) shape).curveTo(285.775, 156.377, 285.963, 156.414,
                286.249, 156.414);
        ((GeneralPath) shape).curveTo(286.82498, 156.414, 287.115, 156.23601,
                287.115, 155.88701);
        ((GeneralPath) shape).curveTo(287.115, 155.667, 287.059, 155.518,
                286.947, 155.442);
        ((GeneralPath) shape).curveTo(286.836, 155.364, 286.618, 155.326,
                286.294, 155.326);
        ((GeneralPath) shape).moveTo(285.636, 154.696);
        ((GeneralPath) shape).lineTo(285.22897, 154.696);
        ((GeneralPath) shape).curveTo(285.22897, 154.373, 285.3, 154.151,
                285.44998, 154.04);
        ((GeneralPath) shape).curveTo(285.59598, 153.929, 285.88397, 153.872,
                286.314, 153.872);
        ((GeneralPath) shape).curveTo(286.779, 153.872, 287.095, 153.939,
                287.25598, 154.079);
        ((GeneralPath) shape).curveTo(287.41898, 154.217, 287.50198, 154.47699,
                287.50198, 154.864);
        ((GeneralPath) shape).lineTo(287.50198, 156.715);
        ((GeneralPath) shape).lineTo(287.09497, 156.715);
        ((GeneralPath) shape).lineTo(287.12497, 156.413);
        ((GeneralPath) shape).lineTo(287.11697, 156.409);
        ((GeneralPath) shape).curveTo(286.96198, 156.638, 286.64398, 156.756,
                286.166, 156.756);
        ((GeneralPath) shape).curveTo(285.461, 156.756, 285.107, 156.472,
                285.107, 155.902);
        ((GeneralPath) shape).curveTo(285.107, 155.56499, 285.186, 155.333,
                285.344, 155.204);
        ((GeneralPath) shape).curveTo(285.5, 155.073, 285.785, 155.011,
                286.198, 155.011);
        ((GeneralPath) shape).curveTo(286.689, 155.011, 286.983, 155.108,
                287.081, 155.301);
        ((GeneralPath) shape).lineTo(287.09198, 155.29799);
        ((GeneralPath) shape).lineTo(287.09198, 154.958);
        ((GeneralPath) shape).curveTo(287.09198, 154.63899, 287.046, 154.431,
                286.95798, 154.33499);
        ((GeneralPath) shape).curveTo(286.87097, 154.239, 286.68097, 154.191,
                286.38397, 154.191);
        ((GeneralPath) shape).curveTo(285.87997, 154.191, 285.62796, 154.334,
                285.62796, 154.616);
        ((GeneralPath) shape).curveTo(285.633, 154.627, 285.633, 154.655,
                285.636, 154.696);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_438;
        g.setTransform(defaultTransform__0_438);
        g.setClip(clip__0_438);
        float alpha__0_439 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_439 = g.getClip();
        AffineTransform defaultTransform__0_439 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_439 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new Rectangle2D.Double(288.2619934082031, 152.71299743652344,
                0.40700000524520874, 3.999000072479248);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_439;
        g.setTransform(defaultTransform__0_439);
        g.setClip(clip__0_439);
        float alpha__0_440 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_440 = g.getClip();
        AffineTransform defaultTransform__0_440 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_440 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new Rectangle2D.Double(289.3869934082031, 152.71299743652344,
                0.40700000524520874, 3.999000072479248);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_440;
        g.setTransform(defaultTransform__0_440);
        g.setClip(clip__0_440);
        float alpha__0_441 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_441 = g.getClip();
        AffineTransform defaultTransform__0_441 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_441 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(294.766, 153.797);
        ((GeneralPath) shape).lineTo(294.319, 153.797);
        ((GeneralPath) shape).curveTo(294.319, 153.47499, 294.266, 153.271,
                294.158, 153.183);
        ((GeneralPath) shape).curveTo(294.054, 153.097, 293.79898, 153.053,
                293.401, 153.053);
        ((GeneralPath) shape).curveTo(292.93, 153.053, 292.626, 153.094,
                292.489, 153.178);
        ((GeneralPath) shape).curveTo(292.35303, 153.26, 292.28403, 153.446,
                292.28403, 153.73299);
        ((GeneralPath) shape).curveTo(292.28403, 154.055, 292.33603, 154.25398,
                292.44504, 154.32199);
        ((GeneralPath) shape).curveTo(292.55203, 154.392, 292.87103, 154.439,
                293.40103, 154.46799);
        ((GeneralPath) shape).curveTo(294.02304, 154.49399, 294.41702,
                154.57199, 294.58704, 154.70499);
        ((GeneralPath) shape).curveTo(294.75504, 154.83499, 294.84003,
                155.12799, 294.84003, 155.58398);
        ((GeneralPath) shape).curveTo(294.84003, 156.07399, 294.74304,
                156.39398, 294.54904, 156.53899);
        ((GeneralPath) shape).curveTo(294.35803, 156.68298, 293.92905,
                156.75598, 293.26605, 156.75598);
        ((GeneralPath) shape).curveTo(292.69104, 156.75598, 292.30804,
                156.68398, 292.11905, 156.53798);
        ((GeneralPath) shape).curveTo(291.93106, 156.39198, 291.83405,
                156.09998, 291.83405, 155.65598);
        ((GeneralPath) shape).lineTo(291.83105, 155.47697);
        ((GeneralPath) shape).lineTo(292.27905, 155.47697);
        ((GeneralPath) shape).lineTo(292.27905, 155.57898);
        ((GeneralPath) shape).curveTo(292.27905, 155.93398, 292.33304,
                156.15598, 292.44305, 156.24599);
        ((GeneralPath) shape).curveTo(292.54904, 156.33199, 292.83206,
                156.37599, 293.28204, 156.37599);
        ((GeneralPath) shape).curveTo(293.79803, 156.37599, 294.11505,
                156.33398, 294.23505, 156.24599);
        ((GeneralPath) shape).curveTo(294.35403, 156.15799, 294.41306, 155.928,
                294.41306, 155.54999);
        ((GeneralPath) shape).curveTo(294.41306, 155.30699, 294.37305,
                155.14398, 294.29205, 155.06198);
        ((GeneralPath) shape).curveTo(294.21106, 154.98099, 294.04205,
                154.93298, 293.78506, 154.91698);
        ((GeneralPath) shape).lineTo(293.31708, 154.89398);
        ((GeneralPath) shape).lineTo(292.87308, 154.87299);
        ((GeneralPath) shape).curveTo(292.1981, 154.82399, 291.85806,
                154.47499, 291.85806, 153.81598);
        ((GeneralPath) shape).curveTo(291.85806, 153.36198, 291.95605,
                153.05698, 292.15308, 152.90498);
        ((GeneralPath) shape).curveTo(292.3491, 152.75499, 292.74207,
                152.67598, 293.33206, 152.67598);
        ((GeneralPath) shape).curveTo(293.93005, 152.67598, 294.31705,
                152.74599, 294.49905, 152.88799);
        ((GeneralPath) shape).curveTo(294.675, 153.025, 294.766, 153.33,
                294.766, 153.797);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_441;
        g.setTransform(defaultTransform__0_441);
        g.setClip(clip__0_441);
        float alpha__0_442 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_442 = g.getClip();
        AffineTransform defaultTransform__0_442 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_442 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(295.77, 152.713);
        ((GeneralPath) shape).lineTo(295.77, 155.036);
        ((GeneralPath) shape).lineTo(295.958, 155.036);
        ((GeneralPath) shape).lineTo(296.945, 153.911);
        ((GeneralPath) shape).lineTo(297.46, 153.911);
        ((GeneralPath) shape).lineTo(296.264, 155.197);
        ((GeneralPath) shape).lineTo(297.677, 156.712);
        ((GeneralPath) shape).lineTo(297.124, 156.712);
        ((GeneralPath) shape).lineTo(295.937, 155.355);
        ((GeneralPath) shape).lineTo(295.77, 155.355);
        ((GeneralPath) shape).lineTo(295.77, 156.712);
        ((GeneralPath) shape).lineTo(295.361, 156.712);
        ((GeneralPath) shape).lineTo(295.361, 152.713);
        ((GeneralPath) shape).lineTo(295.77, 152.713);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_442;
        g.setTransform(defaultTransform__0_442);
        g.setClip(clip__0_442);
        float alpha__0_443 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_443 = g.getClip();
        AffineTransform defaultTransform__0_443 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_443 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(298.344, 153.911);
        ((GeneralPath) shape).lineTo(298.344, 156.71199);
        ((GeneralPath) shape).lineTo(297.934, 156.71199);
        ((GeneralPath) shape).lineTo(297.934, 153.911);
        ((GeneralPath) shape).lineTo(298.344, 153.911);
        ((GeneralPath) shape).moveTo(298.344, 152.713);
        ((GeneralPath) shape).lineTo(298.344, 153.173);
        ((GeneralPath) shape).lineTo(297.934, 153.173);
        ((GeneralPath) shape).lineTo(297.934, 152.713);
        ((GeneralPath) shape).lineTo(298.344, 152.713);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_443;
        g.setTransform(defaultTransform__0_443);
        g.setClip(clip__0_443);
        float alpha__0_444 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_444 = g.getClip();
        AffineTransform defaultTransform__0_444 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_444 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new Rectangle2D.Double(299.0589904785156, 152.71299743652344,
                0.4099999964237213, 3.999000072479248);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_444;
        g.setTransform(defaultTransform__0_444);
        g.setClip(clip__0_444);
        float alpha__0_445 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_445 = g.getClip();
        AffineTransform defaultTransform__0_445 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_445 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new Rectangle2D.Double(300.1839904785156, 152.71299743652344,
                0.4099999964237213, 3.999000072479248);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_445;
        g.setTransform(defaultTransform__0_445);
        g.setClip(clip__0_445);
        float alpha__0_446 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_446 = g.getClip();
        AffineTransform defaultTransform__0_446 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_446 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(302.847, 153.911);
        ((GeneralPath) shape).lineTo(303.257, 153.911);
        ((GeneralPath) shape).lineTo(303.216, 154.231);
        ((GeneralPath) shape).lineTo(303.225, 154.242);
        ((GeneralPath) shape).curveTo(303.38602, 153.976, 303.653, 153.84601,
                304.026, 153.84601);
        ((GeneralPath) shape).curveTo(304.54102, 153.84601, 304.798, 154.11101,
                304.798, 154.643);
        ((GeneralPath) shape).lineTo(304.795, 154.836);
        ((GeneralPath) shape).lineTo(304.39102, 154.836);
        ((GeneralPath) shape).lineTo(304.40002, 154.76599);
        ((GeneralPath) shape).curveTo(304.40604, 154.693, 304.40903, 154.64299,
                304.40903, 154.61899);
        ((GeneralPath) shape).curveTo(304.40903, 154.33199, 304.256, 154.18599,
                303.94104, 154.18599);
        ((GeneralPath) shape).curveTo(303.48505, 154.18599, 303.25705, 154.467,
                303.25705, 155.03299);
        ((GeneralPath) shape).lineTo(303.25705, 156.71399);
        ((GeneralPath) shape).lineTo(302.84705, 156.71399);
        ((GeneralPath) shape).lineTo(302.84705, 153.911);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_446;
        g.setTransform(defaultTransform__0_446);
        g.setClip(clip__0_446);
        float alpha__0_447 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_447 = g.getClip();
        AffineTransform defaultTransform__0_447 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_447 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(306.344, 154.21);
        ((GeneralPath) shape).curveTo(305.982, 154.21, 305.758, 154.26901,
                305.66998, 154.38701);
        ((GeneralPath) shape).curveTo(305.58197, 154.50401, 305.538, 154.813,
                305.538, 155.311);
        ((GeneralPath) shape).curveTo(305.538, 155.809, 305.58, 156.119,
                305.66998, 156.235);
        ((GeneralPath) shape).curveTo(305.75598, 156.352, 305.982, 156.414,
                306.344, 156.414);
        ((GeneralPath) shape).curveTo(306.706, 156.414, 306.934, 156.353,
                307.022, 156.235);
        ((GeneralPath) shape).curveTo(307.108, 156.12, 307.152, 155.809,
                307.152, 155.311);
        ((GeneralPath) shape).curveTo(307.152, 154.813, 307.109, 154.505,
                307.022, 154.38701);
        ((GeneralPath) shape).curveTo(306.934, 154.27, 306.711, 154.21,
                306.344, 154.21);
        ((GeneralPath) shape).moveTo(306.344, 153.87);
        ((GeneralPath) shape).curveTo(306.861, 153.87, 307.19598, 153.959,
                307.35, 154.14);
        ((GeneralPath) shape).curveTo(307.50302, 154.319, 307.579, 154.71,
                307.579, 155.312);
        ((GeneralPath) shape).curveTo(307.579, 155.915, 307.505, 156.304,
                307.35, 156.484);
        ((GeneralPath) shape).curveTo(307.197, 156.665, 306.862, 156.75499,
                306.344, 156.75499);
        ((GeneralPath) shape).curveTo(305.828, 156.75499, 305.494, 156.66599,
                305.341, 156.484);
        ((GeneralPath) shape).curveTo(305.188, 156.305, 305.11002, 155.916,
                305.11002, 155.312);
        ((GeneralPath) shape).curveTo(305.11002, 154.711, 305.186, 154.31999,
                305.341, 154.14);
        ((GeneralPath) shape).curveTo(305.494, 153.961, 305.829, 153.87,
                306.344, 153.87);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_447;
        g.setTransform(defaultTransform__0_447);
        g.setClip(clip__0_447);
        float alpha__0_448 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_448 = g.getClip();
        AffineTransform defaultTransform__0_448 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_448 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new Rectangle2D.Double(308.218994140625, 152.71299743652344,
                0.4099999964237213, 3.999000072479248);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_448;
        g.setTransform(defaultTransform__0_448);
        g.setClip(clip__0_448);
        float alpha__0_449 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_449 = g.getClip();
        AffineTransform defaultTransform__0_449 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_449 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new Rectangle2D.Double(309.343994140625, 152.71299743652344,
                0.4090000092983246, 3.999000072479248);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_449;
        g.setTransform(defaultTransform__0_449);
        g.setClip(clip__0_449);
        float alpha__0_450 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_450 = g.getClip();
        AffineTransform defaultTransform__0_450 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_450 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(312.635, 154.644);
        ((GeneralPath) shape).lineTo(312.226, 154.644);
        ((GeneralPath) shape).curveTo(312.226, 154.44899, 312.186, 154.33,
                312.108, 154.28099);
        ((GeneralPath) shape).curveTo(312.029, 154.234, 311.826, 154.213,
                311.49802, 154.213);
        ((GeneralPath) shape).curveTo(311.19403, 154.213, 311.001, 154.236,
                310.92203, 154.288);
        ((GeneralPath) shape).curveTo(310.83902, 154.338, 310.79904, 154.456,
                310.79904, 154.64499);
        ((GeneralPath) shape).curveTo(310.79904, 154.93199, 310.93604, 155.083,
                311.21005, 155.096);
        ((GeneralPath) shape).lineTo(311.53906, 155.114);
        ((GeneralPath) shape).lineTo(311.95706, 155.135);
        ((GeneralPath) shape).curveTo(312.46506, 155.15999, 312.72006,
                155.42499, 312.72006, 155.93199);
        ((GeneralPath) shape).curveTo(312.72006, 156.245, 312.63705, 156.46199,
                312.46805, 156.579);
        ((GeneralPath) shape).curveTo(312.30206, 156.694, 311.99203, 156.75499,
                311.54605, 156.75499);
        ((GeneralPath) shape).curveTo(311.09006, 156.75499, 310.77405,
                156.69699, 310.60205, 156.58798);
        ((GeneralPath) shape).curveTo(310.42905, 156.47699, 310.34204,
                156.27298, 310.34204, 155.97598);
        ((GeneralPath) shape).lineTo(310.34604, 155.82399);
        ((GeneralPath) shape).lineTo(310.77005, 155.82399);
        ((GeneralPath) shape).lineTo(310.77304, 155.958);
        ((GeneralPath) shape).curveTo(310.77304, 156.14099, 310.82004,
                156.26399, 310.91306, 156.32399);
        ((GeneralPath) shape).curveTo(311.00906, 156.387, 311.19107, 156.41798,
                311.46805, 156.41798);
        ((GeneralPath) shape).curveTo(311.80505, 156.41798, 312.02704,
                156.38599, 312.13403, 156.31898);
        ((GeneralPath) shape).curveTo(312.23804, 156.25598, 312.29105,
                156.12198, 312.29105, 155.91699);
        ((GeneralPath) shape).curveTo(312.29105, 155.62299, 312.15805,
                155.47499, 311.89304, 155.47499);
        ((GeneralPath) shape).curveTo(311.27103, 155.47499, 310.86203, 155.422,
                310.66403, 155.31699);
        ((GeneralPath) shape).curveTo(310.47104, 155.21298, 310.37103,
                154.99298, 310.37103, 154.66199);
        ((GeneralPath) shape).curveTo(310.37103, 154.34898, 310.44803,
                154.13998, 310.60303, 154.03299);
        ((GeneralPath) shape).curveTo(310.75604, 153.92899, 311.06604,
                153.87799, 311.528, 153.87799);
        ((GeneralPath) shape).curveTo(312.268, 153.87799, 312.639, 154.101,
                312.639, 154.549);
        ((GeneralPath) shape).lineTo(312.635, 154.644);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_450;
        g.setTransform(defaultTransform__0_450);
        g.setClip(clip__0_450);
        float alpha__0_451 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_451 = g.getClip();
        AffineTransform defaultTransform__0_451 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_451 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(314.154, 146.173);
        ((GeneralPath) shape).lineTo(314.154, 144.98);
        ((GeneralPath) shape).lineTo(312.976, 144.98);
        ((GeneralPath) shape).lineTo(312.976, 144.6);
        ((GeneralPath) shape).lineTo(314.154, 144.6);
        ((GeneralPath) shape).lineTo(314.154, 143.401);
        ((GeneralPath) shape).lineTo(314.563, 143.401);
        ((GeneralPath) shape).lineTo(314.563, 144.6);
        ((GeneralPath) shape).lineTo(315.742, 144.6);
        ((GeneralPath) shape).lineTo(315.742, 144.98);
        ((GeneralPath) shape).lineTo(314.563, 144.98);
        ((GeneralPath) shape).lineTo(314.563, 146.173);
        ((GeneralPath) shape).lineTo(314.154, 146.173);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_451;
        g.setTransform(defaultTransform__0_451);
        g.setClip(clip__0_451);
        float alpha__0_452 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_452 = g.getClip();
        AffineTransform defaultTransform__0_452 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_452 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(318.561, 142.789);
        ((GeneralPath) shape).lineTo(318.561, 146.788);
        ((GeneralPath) shape).lineTo(318.11, 146.788);
        ((GeneralPath) shape).lineTo(318.11, 143.126);
        ((GeneralPath) shape).lineTo(317.092, 144.26);
        ((GeneralPath) shape).lineTo(316.806, 143.99);
        ((GeneralPath) shape).lineTo(317.911, 142.789);
        ((GeneralPath) shape).lineTo(318.561, 142.789);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_452;
        g.setTransform(defaultTransform__0_452);
        g.setClip(clip__0_452);
        float alpha__0_453 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_453 = g.getClip();
        AffineTransform defaultTransform__0_453 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_453 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        stroke = new BasicStroke(1.0f, 0, 0, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(374.075, 132.613);
        ((GeneralPath) shape).lineTo(388.247, 132.613);
        ((GeneralPath) shape).moveTo(306.044, 132.613);
        ((GeneralPath) shape).lineTo(320.216, 132.613);
        ((GeneralPath) shape).moveTo(281.452, 119.857);
        ((GeneralPath) shape).lineTo(307.45898, 119.857);
        ((GeneralPath) shape).moveTo(320.217, 147.831);
        ((GeneralPath) shape).curveTo(320.217, 148.457, 319.71002, 148.96599,
                319.08102, 148.96599);
        ((GeneralPath) shape).lineTo(313.312, 148.96599);
        ((GeneralPath) shape).curveTo(312.685, 148.96599, 312.177, 148.45699,
                312.177, 147.831);
        ((GeneralPath) shape).lineTo(312.177, 142.00499);
        ((GeneralPath) shape).curveTo(312.177, 141.37799, 312.685, 140.87099,
                313.312, 140.87099);
        ((GeneralPath) shape).lineTo(319.08102, 140.87099);
        ((GeneralPath) shape).curveTo(319.709, 140.87099, 320.217, 141.37698,
                320.217, 142.00499);
        ((GeneralPath) shape).lineTo(320.217, 147.831);
        ((GeneralPath) shape).closePath();
        ((GeneralPath) shape).moveTo(382.578, 147.831);
        ((GeneralPath) shape).curveTo(382.578, 148.457, 382.069, 148.96599,
                381.44202, 148.96599);
        ((GeneralPath) shape).lineTo(375.67502, 148.96599);
        ((GeneralPath) shape).curveTo(375.04703, 148.96599, 374.53903,
                148.45699, 374.53903, 147.831);
        ((GeneralPath) shape).lineTo(374.53903, 142.00499);
        ((GeneralPath) shape).curveTo(374.53903, 141.37799, 375.04703,
                140.87099, 375.67502, 140.87099);
        ((GeneralPath) shape).lineTo(381.44202, 140.87099);
        ((GeneralPath) shape).curveTo(382.06903, 140.87099, 382.578, 141.37698,
                382.578, 142.00499);
        ((GeneralPath) shape).lineTo(382.578, 147.831);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_453;
        g.setTransform(defaultTransform__0_453);
        g.setClip(clip__0_453);
        float alpha__0_454 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_454 = g.getClip();
        AffineTransform defaultTransform__0_454 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_454 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(376.519, 146.173);
        ((GeneralPath) shape).lineTo(376.519, 144.98);
        ((GeneralPath) shape).lineTo(375.342, 144.98);
        ((GeneralPath) shape).lineTo(375.342, 144.6);
        ((GeneralPath) shape).lineTo(376.519, 144.6);
        ((GeneralPath) shape).lineTo(376.519, 143.401);
        ((GeneralPath) shape).lineTo(376.928, 143.401);
        ((GeneralPath) shape).lineTo(376.928, 144.6);
        ((GeneralPath) shape).lineTo(378.106, 144.6);
        ((GeneralPath) shape).lineTo(378.106, 144.98);
        ((GeneralPath) shape).lineTo(376.928, 144.98);
        ((GeneralPath) shape).lineTo(376.928, 146.173);
        ((GeneralPath) shape).lineTo(376.519, 146.173);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_454;
        g.setTransform(defaultTransform__0_454);
        g.setClip(clip__0_454);
        float alpha__0_455 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_455 = g.getClip();
        AffineTransform defaultTransform__0_455 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_455 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(381.757, 146.406);
        ((GeneralPath) shape).lineTo(381.757, 146.789);
        ((GeneralPath) shape).lineTo(378.883, 146.789);
        ((GeneralPath) shape).lineTo(378.883, 146.037);
        ((GeneralPath) shape).curveTo(378.883, 145.613, 378.965, 145.34,
                379.129, 145.207);
        ((GeneralPath) shape).curveTo(379.29, 145.077, 379.681, 144.976,
                380.29498, 144.903);
        ((GeneralPath) shape).curveTo(380.784, 144.849, 381.081, 144.775,
                381.18597, 144.681);
        ((GeneralPath) shape).curveTo(381.28897, 144.587, 381.339, 144.344,
                381.339, 143.952);
        ((GeneralPath) shape).curveTo(381.339, 143.608, 381.28, 143.385,
                381.166, 143.28099);
        ((GeneralPath) shape).curveTo(381.054, 143.178, 380.806, 143.129,
                380.421, 143.129);
        ((GeneralPath) shape).curveTo(379.942, 143.129, 379.63898, 143.17,
                379.513, 143.254);
        ((GeneralPath) shape).curveTo(379.387, 143.336, 379.325, 143.536,
                379.325, 143.853);
        ((GeneralPath) shape).lineTo(379.33102, 144.152);
        ((GeneralPath) shape).lineTo(378.89203, 144.152);
        ((GeneralPath) shape).lineTo(378.89502, 143.944);
        ((GeneralPath) shape).curveTo(378.89502, 143.466, 378.99603, 143.147,
                379.19803, 142.987);
        ((GeneralPath) shape).curveTo(379.39902, 142.831, 379.80304, 142.75,
                380.41003, 142.75);
        ((GeneralPath) shape).curveTo(380.94803, 142.75, 381.30902, 142.835,
                381.49503, 143.008);
        ((GeneralPath) shape).curveTo(381.68204, 143.179, 381.77203, 143.51399,
                381.77203, 144.012);
        ((GeneralPath) shape).curveTo(381.77203, 144.48999, 381.68604, 144.81,
                381.51303, 144.972);
        ((GeneralPath) shape).curveTo(381.34103, 145.133, 380.96603, 145.243,
                380.38904, 145.299);
        ((GeneralPath) shape).curveTo(379.88303, 145.34799, 379.57803,
                145.41899, 379.48303, 145.509);
        ((GeneralPath) shape).curveTo(379.38803, 145.59901, 379.33704, 145.853,
                379.33704, 146.274);
        ((GeneralPath) shape).lineTo(379.33704, 146.41);
        ((GeneralPath) shape).lineTo(381.75803, 146.41);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_455;
        g.setTransform(defaultTransform__0_455);
        g.setClip(clip__0_455);
        float alpha__0_456 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_456 = g.getClip();
        AffineTransform defaultTransform__0_456 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_456 is ShapeNode
        paint = new Color(199, 200, 202, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(396.719, 69.24);
        ((GeneralPath) shape).lineTo(390.599, 75.36);
        ((GeneralPath) shape).lineTo(39.24, 75.36);
        ((GeneralPath) shape).lineTo(33.84, 68.4);
        ((GeneralPath) shape).lineTo(33.84, 30.72);
        ((GeneralPath) shape).lineTo(39.24, 26.04);
        ((GeneralPath) shape).lineTo(135.48, 26.04);
        ((GeneralPath) shape).lineTo(137.88, 29.16);
        ((GeneralPath) shape).lineTo(318.841, 29.16);
        ((GeneralPath) shape).lineTo(332.04, 22.2);
        ((GeneralPath) shape).lineTo(389.76, 22.2);
        ((GeneralPath) shape).lineTo(396.719, 27.6);
        ((GeneralPath) shape).lineTo(396.719, 69.24);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_456;
        g.setTransform(defaultTransform__0_456);
        g.setClip(clip__0_456);
        float alpha__0_457 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_457 = g.getClip();
        AffineTransform defaultTransform__0_457 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_457 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(392.4, 65.4);
        ((GeneralPath) shape).lineTo(386.28, 71.64);
        ((GeneralPath) shape).lineTo(34.56, 71.64);
        ((GeneralPath) shape).lineTo(29.16, 64.68);
        ((GeneralPath) shape).lineTo(29.16, 26.88);
        ((GeneralPath) shape).lineTo(34.56, 22.2);
        ((GeneralPath) shape).lineTo(51.0, 22.2);
        ((GeneralPath) shape).lineTo(53.28, 25.32);
        ((GeneralPath) shape).lineTo(314.519, 25.32);
        ((GeneralPath) shape).lineTo(327.719, 18.36);
        ((GeneralPath) shape).lineTo(385.559, 18.36);
        ((GeneralPath) shape).lineTo(392.4, 23.76);
        ((GeneralPath) shape).lineTo(392.4, 65.4);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_457;
        g.setTransform(defaultTransform__0_457);
        g.setClip(clip__0_457);
        float alpha__0_458 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_458 = g.getClip();
        AffineTransform defaultTransform__0_458 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_458 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(392.4, 65.4);
        ((GeneralPath) shape).lineTo(393.12, 66.121);
        ((GeneralPath) shape).lineTo(387.0, 72.36);
        ((GeneralPath) shape).curveTo(386.761, 72.48, 386.521, 72.6, 386.28,
                72.6);
        ((GeneralPath) shape).lineTo(34.56, 72.6);
        ((GeneralPath) shape).curveTo(34.32, 72.6, 33.959, 72.479996, 33.839,
                72.24);
        ((GeneralPath) shape).lineTo(28.44, 65.28);
        ((GeneralPath) shape).curveTo(28.32, 65.160995, 28.2, 64.921, 28.2,
                64.68);
        ((GeneralPath) shape).lineTo(28.2, 26.880001);
        ((GeneralPath) shape).curveTo(28.2, 26.640001, 28.320002, 26.279001,
                28.560001, 26.160002);
        ((GeneralPath) shape).lineTo(33.959, 21.479002);
        ((GeneralPath) shape).curveTo(34.079, 21.359001, 34.319, 21.239002,
                34.56, 21.239002);
        ((GeneralPath) shape).lineTo(51.0, 21.239002);
        ((GeneralPath) shape).curveTo(51.24, 21.239002, 51.601, 21.359003,
                51.721, 21.599003);
        ((GeneralPath) shape).lineTo(53.760002, 24.359003);
        ((GeneralPath) shape).lineTo(314.28, 24.359003);
        ((GeneralPath) shape).lineTo(327.24, 17.519003);
        ((GeneralPath) shape).curveTo(327.36, 17.399002, 327.47998, 17.399002,
                327.719, 17.399002);
        ((GeneralPath) shape).lineTo(385.559, 17.399002);
        ((GeneralPath) shape).curveTo(385.679, 17.399002, 385.91898, 17.519003,
                386.16, 17.639002);
        ((GeneralPath) shape).lineTo(393.0, 23.039001);
        ((GeneralPath) shape).curveTo(393.24, 23.159002, 393.48, 23.518002,
                393.48, 23.759);
        ((GeneralPath) shape).lineTo(393.48, 65.4);
        ((GeneralPath) shape).curveTo(393.48, 65.64, 393.36002, 66.001,
                393.12003, 66.121);
        ((GeneralPath) shape).lineTo(392.4, 65.4);
        ((GeneralPath) shape).lineTo(391.44, 65.4);
        ((GeneralPath) shape).lineTo(391.44, 24.24);
        ((GeneralPath) shape).lineTo(385.2, 19.439999);
        ((GeneralPath) shape).lineTo(327.961, 19.439999);
        ((GeneralPath) shape).lineTo(315.0, 26.16);
        ((GeneralPath) shape).curveTo(314.881, 26.279, 314.761, 26.279,
                314.521, 26.279);
        ((GeneralPath) shape).lineTo(53.28, 26.279);
        ((GeneralPath) shape)
                .curveTo(52.92, 26.279, 52.68, 26.16, 52.44, 25.92);
        ((GeneralPath) shape).lineTo(50.4, 23.28);
        ((GeneralPath) shape).lineTo(34.92, 23.28);
        ((GeneralPath) shape).lineTo(30.239998, 27.36);
        ((GeneralPath) shape).lineTo(30.239998, 64.32);
        ((GeneralPath) shape).lineTo(35.039997, 70.56);
        ((GeneralPath) shape).lineTo(385.92102, 70.56);
        ((GeneralPath) shape).lineTo(391.80103, 64.68);
        ((GeneralPath) shape).lineTo(392.4, 65.4);
        ((GeneralPath) shape).lineTo(391.44, 65.4);
        ((GeneralPath) shape).lineTo(392.4, 65.4);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_458;
        g.setTransform(defaultTransform__0_458);
        g.setClip(clip__0_458);
        float alpha__0_459 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_459 = g.getClip();
        AffineTransform defaultTransform__0_459 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_459 is CompositeGraphicsNode
        float alpha__0_459_0 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_459_0 = g.getClip();
        AffineTransform defaultTransform__0_459_0 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_459_0 is CompositeGraphicsNode
        float alpha__0_459_0_0 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_459_0_0 = g.getClip();
        AffineTransform defaultTransform__0_459_0_0 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f,
                -1.896817957458552E-5f, -5.159325269232795E-7f));
        clip = new Area(g.getClip());
        clip.intersect(new Area(new Rectangle2D.Double(309.84100341796875,
                19.31999969482422, 78.3590087890625, 49.31999969482422)));
        g.setClip(clip);
        // _0_459_0_0 is CompositeGraphicsNode
        float alpha__0_459_0_0_0 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_459_0_0_0 = g.getClip();
        AffineTransform defaultTransform__0_459_0_0_0 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f,
                -7951.01953125f, -7806.48046875f));
        // _0_459_0_0_0 is CompositeGraphicsNode
        Shape clip__0_459_0_0_0_0 = g.getClip();
        AffineTransform defaultTransform__0_459_0_0_0_0 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, -0.0f, -0.0f));
        g.setTransform(defaultTransform__0_459_0_0_0_0);
        g.setClip(clip__0_459_0_0_0_0);
        origAlpha = alpha__0_459_0_0_0;
        g.setTransform(defaultTransform__0_459_0_0_0);
        g.setClip(clip__0_459_0_0_0);
        origAlpha = alpha__0_459_0_0;
        g.setTransform(defaultTransform__0_459_0_0);
        g.setClip(clip__0_459_0_0);
        origAlpha = alpha__0_459_0;
        g.setTransform(defaultTransform__0_459_0);
        g.setClip(clip__0_459_0);
        origAlpha = alpha__0_459;
        g.setTransform(defaultTransform__0_459);
        g.setClip(clip__0_459);
        float alpha__0_460 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_460 = g.getClip();
        AffineTransform defaultTransform__0_460 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_460 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(57.24, 37.2);
        ((GeneralPath) shape).curveTo(60.24, 37.2, 62.280003, 37.679, 63.24,
                38.760002);
        ((GeneralPath) shape).curveTo(64.32, 39.72, 64.8, 40.920002, 64.8,
                42.120003);
        ((GeneralPath) shape).curveTo(64.8, 43.319004, 64.8, 44.519, 64.8,
                45.599003);
        ((GeneralPath) shape).curveTo(64.8, 46.679005, 64.32101, 47.639004,
                63.361004, 48.359);
        ((GeneralPath) shape).curveTo(65.281006, 49.319, 66.36101, 50.88,
                66.36101, 53.04);
        ((GeneralPath) shape).curveTo(66.36101, 57.479, 64.32101, 59.760002,
                60.24101, 59.760002);
        ((GeneralPath) shape).lineTo(57.361008, 59.760002);
        ((GeneralPath) shape).lineTo(57.24101, 55.320004);
        ((GeneralPath) shape).curveTo(58.32101, 55.320004, 59.041008,
                54.841003, 59.64101, 54.121002);
        ((GeneralPath) shape).curveTo(60.24101, 53.4, 60.24101, 52.56,
                59.64101, 51.841003);
        ((GeneralPath) shape).curveTo(59.04001, 51.121002, 58.32101, 50.761,
                57.24101, 50.641003);
        ((GeneralPath) shape).lineTo(57.24101, 46.2);
        ((GeneralPath) shape).curveTo(58.32101, 46.2, 58.92101, 45.84,
                59.40101, 45.12);
        ((GeneralPath) shape).curveTo(59.76001, 44.399, 59.76001, 43.68,
                59.40101, 42.84);
        ((GeneralPath) shape).curveTo(58.92101, 42.12, 58.321007, 41.76,
                57.24101, 41.76);
        ((GeneralPath) shape).lineTo(57.24101, 37.2);
        ((GeneralPath) shape).moveTo(46.68, 55.2);
        ((GeneralPath) shape).lineTo(57.24, 55.2);
        ((GeneralPath) shape).lineTo(57.24, 59.760002);
        ((GeneralPath) shape).lineTo(46.68, 59.760002);
        ((GeneralPath) shape).lineTo(46.68, 55.2);
        ((GeneralPath) shape).closePath();
        ((GeneralPath) shape).moveTo(46.68, 37.2);
        ((GeneralPath) shape).lineTo(57.24, 37.2);
        ((GeneralPath) shape).lineTo(57.24, 41.760002);
        ((GeneralPath) shape).lineTo(46.68, 41.760002);
        ((GeneralPath) shape).lineTo(46.68, 37.2);
        ((GeneralPath) shape).closePath();
        ((GeneralPath) shape).moveTo(44.52, 46.2);
        ((GeneralPath) shape).lineTo(57.24, 46.2);
        ((GeneralPath) shape).lineTo(57.24, 50.760002);
        ((GeneralPath) shape).lineTo(44.52, 50.760002);
        ((GeneralPath) shape).lineTo(44.52, 46.2);
        ((GeneralPath) shape).closePath();
        ((GeneralPath) shape).moveTo(37.68, 37.2);
        ((GeneralPath) shape).lineTo(44.52, 37.2);
        ((GeneralPath) shape).lineTo(44.52, 59.760002);
        ((GeneralPath) shape).lineTo(37.68, 59.760002);
        ((GeneralPath) shape).lineTo(37.68, 37.2);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_460;
        g.setTransform(defaultTransform__0_460);
        g.setClip(clip__0_460);
        float alpha__0_461 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_461 = g.getClip();
        AffineTransform defaultTransform__0_461 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_461 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(68.52, 59.76);
        ((GeneralPath) shape).lineTo(80.4, 37.2);
        ((GeneralPath) shape).lineTo(89.52, 37.2);
        ((GeneralPath) shape).lineTo(101.52, 59.76);
        ((GeneralPath) shape).lineTo(93.96, 59.76);
        ((GeneralPath) shape).lineTo(84.96, 41.64);
        ((GeneralPath) shape).lineTo(75.96, 59.76);
        ((GeneralPath) shape).lineTo(68.52, 59.76);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_461;
        g.setTransform(defaultTransform__0_461);
        g.setClip(clip__0_461);
        float alpha__0_462 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_462 = g.getClip();
        AffineTransform defaultTransform__0_462 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_462 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(108.48, 59.76);
        ((GeneralPath) shape).lineTo(114.48, 59.76);
        ((GeneralPath) shape).lineTo(114.48, 43.92);
        ((GeneralPath) shape).lineTo(108.48, 43.92);
        ((GeneralPath) shape).lineTo(108.48, 59.76);
        ((GeneralPath) shape).moveTo(98.64, 37.2);
        ((GeneralPath) shape).lineTo(124.32, 37.2);
        ((GeneralPath) shape).lineTo(124.32, 41.639);
        ((GeneralPath) shape).lineTo(98.64, 41.639);
        ((GeneralPath) shape).lineTo(98.64, 37.2);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_462;
        g.setTransform(defaultTransform__0_462);
        g.setClip(clip__0_462);
        float alpha__0_463 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_463 = g.getClip();
        AffineTransform defaultTransform__0_463 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_463 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(136.92, 59.76);
        ((GeneralPath) shape).lineTo(142.92, 59.76);
        ((GeneralPath) shape).lineTo(142.92, 43.92);
        ((GeneralPath) shape).lineTo(136.92, 43.92);
        ((GeneralPath) shape).lineTo(136.92, 59.76);
        ((GeneralPath) shape).moveTo(127.2, 37.2);
        ((GeneralPath) shape).lineTo(152.76, 37.2);
        ((GeneralPath) shape).lineTo(152.76, 41.639);
        ((GeneralPath) shape).lineTo(127.2, 41.639);
        ((GeneralPath) shape).lineTo(127.2, 37.2);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_463;
        g.setTransform(defaultTransform__0_463);
        g.setClip(clip__0_463);
        float alpha__0_464 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_464 = g.getClip();
        AffineTransform defaultTransform__0_464 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_464 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(165.36, 53.76);
        ((GeneralPath) shape).lineTo(180.36, 53.76);
        ((GeneralPath) shape).lineTo(180.36, 59.76);
        ((GeneralPath) shape).lineTo(165.36, 59.76);
        ((GeneralPath) shape).lineTo(165.36, 53.76);
        ((GeneralPath) shape).moveTo(156.24, 37.2);
        ((GeneralPath) shape).lineTo(163.08, 37.2);
        ((GeneralPath) shape).lineTo(163.08, 59.760002);
        ((GeneralPath) shape).lineTo(156.24, 59.760002);
        ((GeneralPath) shape).lineTo(156.24, 37.2);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_464;
        g.setTransform(defaultTransform__0_464);
        g.setClip(clip__0_464);
        float alpha__0_465 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_465 = g.getClip();
        AffineTransform defaultTransform__0_465 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_465 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new Rectangle2D.Double(182.52000427246094, 37.20000076293945,
                6.71999979019165, 22.559999465942383);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_465;
        g.setTransform(defaultTransform__0_465);
        g.setClip(clip__0_465);
        float alpha__0_466 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_466 = g.getClip();
        AffineTransform defaultTransform__0_466 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_466 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new Rectangle2D.Double(189.24000549316406, 46.20000076293945,
                15.84000015258789, 4.559999942779541);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_466;
        g.setTransform(defaultTransform__0_466);
        g.setClip(clip__0_466);
        float alpha__0_467 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_467 = g.getClip();
        AffineTransform defaultTransform__0_467 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_467 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new Rectangle2D.Double(191.52000427246094, 37.20000076293945,
                15.119999885559082, 6.0);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_467;
        g.setTransform(defaultTransform__0_467);
        g.setClip(clip__0_467);
        float alpha__0_468 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_468 = g.getClip();
        AffineTransform defaultTransform__0_468 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_468 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new Rectangle2D.Double(191.52000427246094, 53.7599983215332,
                15.119999885559082, 6.0);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_468;
        g.setTransform(defaultTransform__0_468);
        g.setClip(clip__0_468);
        float alpha__0_469 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_469 = g.getClip();
        AffineTransform defaultTransform__0_469 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_469 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(220.08, 59.76);
        ((GeneralPath) shape).lineTo(226.08, 59.76);
        ((GeneralPath) shape).lineTo(226.08, 43.92);
        ((GeneralPath) shape).lineTo(220.08, 43.92);
        ((GeneralPath) shape).lineTo(220.08, 59.76);
        ((GeneralPath) shape).moveTo(210.24, 37.2);
        ((GeneralPath) shape).lineTo(235.92001, 37.2);
        ((GeneralPath) shape).lineTo(235.92001, 41.639);
        ((GeneralPath) shape).lineTo(210.24002, 41.639);
        ((GeneralPath) shape).lineTo(210.24002, 37.2);
        ((GeneralPath) shape).lineTo(210.24, 37.2);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_469;
        g.setTransform(defaultTransform__0_469);
        g.setClip(clip__0_469);
        float alpha__0_470 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_470 = g.getClip();
        AffineTransform defaultTransform__0_470 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_470 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new Rectangle2D.Double(240.60000610351562, 37.20000076293945,
                6.840000152587891, 22.559999465942383);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_470;
        g.setTransform(defaultTransform__0_470);
        g.setClip(clip__0_470);
        float alpha__0_471 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_471 = g.getClip();
        AffineTransform defaultTransform__0_471 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_471 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new Rectangle2D.Double(247.32000732421875, 46.20000076293945,
                16.31999969482422, 4.559999942779541);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_471;
        g.setTransform(defaultTransform__0_471);
        g.setClip(clip__0_471);
        float alpha__0_472 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_472 = g.getClip();
        AffineTransform defaultTransform__0_472 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_472 is CompositeGraphicsNode
        float alpha__0_472_0 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_472_0 = g.getClip();
        AffineTransform defaultTransform__0_472_0 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f,
                -1.896817957458552E-5f, -5.159325269232795E-7f));
        clip = new Area(g.getClip());
        clip.intersect(new Area(new Rectangle2D.Double(309.8399963378906,
                19.31999969482422, 78.36001586914062, 49.31999969482422)));
        g.setClip(clip);
        // _0_472_0 is CompositeGraphicsNode
        float alpha__0_472_0_0 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_472_0_0 = g.getClip();
        AffineTransform defaultTransform__0_472_0_0 = g.getTransform();
        g.transform(new AffineTransform(0.23119999468326569f, 0.0f, 0.0f,
                0.23149999976158142f, 309.839599609375f, 19.320100784301758f));
        // _0_472_0_0 is CompositeGraphicsNode
        Shape clip__0_472_0_0_0 = g.getClip();
        AffineTransform defaultTransform__0_472_0_0_0 = g.getTransform();
        g.transform(new AffineTransform(0.9681817889213562f, 0.0f, 0.0f,
                0.9681817889213562f, 4.424996745151475f, -0.0f));
        // _0_472_0_0_0 is RasterImageNode
        g.drawImage(new ImageIcon(
                "./data/images/recordsheets/NavalData.png").getImage(), (int) 0.0, (int) 0.0, null);
        g.setTransform(defaultTransform__0_472_0_0_0);
        g.setClip(clip__0_472_0_0_0);
        origAlpha = alpha__0_472_0_0;
        g.setTransform(defaultTransform__0_472_0_0);
        g.setClip(clip__0_472_0_0);
        origAlpha = alpha__0_472_0;
        g.setTransform(defaultTransform__0_472_0);
        g.setClip(clip__0_472_0);
        origAlpha = alpha__0_472;
        g.setTransform(defaultTransform__0_472);
        g.setClip(clip__0_472);
        float alpha__0_473 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_473 = g.getClip();
        AffineTransform defaultTransform__0_473 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_473 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new Rectangle2D.Double(249.60000610351562, 37.20000076293945,
                15.119999885559082, 6.0);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_473;
        g.setTransform(defaultTransform__0_473);
        g.setClip(clip__0_473);
        float alpha__0_474 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_474 = g.getClip();
        AffineTransform defaultTransform__0_474 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_474 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new Rectangle2D.Double(249.60000610351562, 53.7599983215332,
                15.119999885559082, 6.0);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_474;
        g.setTransform(defaultTransform__0_474);
        g.setClip(clip__0_474);
        float alpha__0_475 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_475 = g.getClip();
        AffineTransform defaultTransform__0_475 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_475 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(269.88, 49.56);
        ((GeneralPath) shape).lineTo(269.88, 54.480003);
        ((GeneralPath) shape).curveTo(269.88, 57.960003, 272.401, 59.760002,
                277.441, 59.760002);
        ((GeneralPath) shape).lineTo(289.441, 59.760002);
        ((GeneralPath) shape).curveTo(295.561, 59.760002, 299.04102, 57.960003,
                300.001, 54.480003);
        ((GeneralPath) shape).lineTo(300.001, 50.760002);
        ((GeneralPath) shape).lineTo(292.562, 50.760002);
        ((GeneralPath) shape).curveTo(292.562, 53.760002, 291.001, 55.2,
                288.001, 55.2);
        ((GeneralPath) shape).lineTo(281.161, 55.2);
        ((GeneralPath) shape).curveTo(278.161, 55.2, 276.72202, 54.0,
                276.72202, 51.479);
        ((GeneralPath) shape).lineTo(276.72202, 49.559002);
        ((GeneralPath) shape).lineTo(269.88, 49.559002);
        ((GeneralPath) shape).moveTo(269.88, 47.4);
        ((GeneralPath) shape).lineTo(269.88, 42.480003);
        ((GeneralPath) shape).curveTo(269.88, 39.001003, 272.401, 37.201004,
                277.441, 37.201004);
        ((GeneralPath) shape).lineTo(291.721, 37.201004);
        ((GeneralPath) shape).curveTo(296.76102, 37.201004, 299.282, 38.881004,
                299.282, 42.480003);
        ((GeneralPath) shape).lineTo(299.282, 46.2);
        ((GeneralPath) shape).lineTo(292.562, 46.2);
        ((GeneralPath) shape).curveTo(292.562, 43.2, 291.001, 41.639, 288.001,
                41.639);
        ((GeneralPath) shape).lineTo(281.161, 41.639);
        ((GeneralPath) shape).curveTo(278.161, 41.639, 276.72202, 42.959,
                276.72202, 45.479);
        ((GeneralPath) shape).lineTo(276.72202, 47.399);
        ((GeneralPath) shape).lineTo(269.88, 47.399);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_475;
        g.setTransform(defaultTransform__0_475);
        g.setClip(clip__0_475);
        float alpha__0_476 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_476 = g.getClip();
        AffineTransform defaultTransform__0_476 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_476 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(325.679, 37.2);
        ((GeneralPath) shape).lineTo(332.4, 37.2);
        ((GeneralPath) shape).lineTo(332.4, 59.760002);
        ((GeneralPath) shape).lineTo(325.679, 59.760002);
        ((GeneralPath) shape).lineTo(325.679, 37.2);
        ((GeneralPath) shape).moveTo(312.841, 45.48);
        ((GeneralPath) shape).lineTo(323.401, 45.48);
        ((GeneralPath) shape).lineTo(323.401, 51.48);
        ((GeneralPath) shape).lineTo(312.841, 51.48);
        ((GeneralPath) shape).lineTo(312.841, 45.48);
        ((GeneralPath) shape).closePath();
        ((GeneralPath) shape).moveTo(303.841, 37.2);
        ((GeneralPath) shape).lineTo(310.561, 37.2);
        ((GeneralPath) shape).lineTo(310.561, 59.760002);
        ((GeneralPath) shape).lineTo(303.841, 59.760002);
        ((GeneralPath) shape).lineTo(303.841, 37.2);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_476;
        g.setTransform(defaultTransform__0_476);
        g.setClip(clip__0_476);
        float alpha__0_477 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_477 = g.getClip();
        AffineTransform defaultTransform__0_477 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_477 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(389.881, 17.64);
        ((GeneralPath) shape).lineTo(389.881, 19.92);
        ((GeneralPath) shape).lineTo(389.4, 19.92);
        ((GeneralPath) shape).lineTo(389.4, 17.64);
        ((GeneralPath) shape).lineTo(388.559, 17.64);
        ((GeneralPath) shape).lineTo(388.559, 17.16);
        ((GeneralPath) shape).lineTo(390.719, 17.16);
        ((GeneralPath) shape).lineTo(390.719, 17.64);
        ((GeneralPath) shape).lineTo(389.881, 17.64);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_477;
        g.setTransform(defaultTransform__0_477);
        g.setClip(clip__0_477);
        float alpha__0_478 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_478 = g.getClip();
        AffineTransform defaultTransform__0_478 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_478 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(394.2, 17.16);
        ((GeneralPath) shape).lineTo(394.2, 19.92);
        ((GeneralPath) shape).lineTo(393.721, 19.92);
        ((GeneralPath) shape).lineTo(393.721, 18.48);
        ((GeneralPath) shape).curveTo(393.721, 18.359999, 393.721, 18.24,
                393.721, 18.001);
        ((GeneralPath) shape).lineTo(393.721, 17.640999);
        ((GeneralPath) shape).lineTo(393.601, 17.761);
        ((GeneralPath) shape).lineTo(393.601, 18.0);
        ((GeneralPath) shape).curveTo(393.48203, 18.12, 393.48203, 18.239,
                393.48203, 18.359);
        ((GeneralPath) shape).lineTo(392.881, 19.92);
        ((GeneralPath) shape).lineTo(392.401, 19.92);
        ((GeneralPath) shape).lineTo(391.8, 18.36);
        ((GeneralPath) shape).curveTo(391.68, 18.36, 391.68, 18.24, 391.68,
                18.001001);
        ((GeneralPath) shape).lineTo(391.56, 17.881);
        ((GeneralPath) shape).lineTo(391.56, 17.641);
        ((GeneralPath) shape).lineTo(391.441, 17.641);
        ((GeneralPath) shape).lineTo(391.56, 17.881);
        ((GeneralPath) shape).lineTo(391.56, 18.0);
        ((GeneralPath) shape).curveTo(391.56, 18.239, 391.56, 18.359, 391.56,
                18.479);
        ((GeneralPath) shape).lineTo(391.56, 19.919);
        ((GeneralPath) shape).lineTo(390.95898, 19.919);
        ((GeneralPath) shape).lineTo(390.95898, 17.159);
        ((GeneralPath) shape).lineTo(391.8, 17.159);
        ((GeneralPath) shape).lineTo(392.401, 18.478);
        ((GeneralPath) shape).curveTo(392.401, 18.598001, 392.401, 18.718,
                392.52, 18.838001);
        ((GeneralPath) shape).lineTo(392.52, 19.078001);
        ((GeneralPath) shape).lineTo(392.63998, 19.198002);
        ((GeneralPath) shape).lineTo(392.63998, 19.078001);
        ((GeneralPath) shape).lineTo(392.75998, 18.838001);
        ((GeneralPath) shape).curveTo(392.75998, 18.718, 392.87997, 18.598001,
                392.87997, 18.478);
        ((GeneralPath) shape).lineTo(393.35898, 17.159);
        ((GeneralPath) shape).lineTo(394.2, 17.159);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_478;
        g.setTransform(defaultTransform__0_478);
        g.setClip(clip__0_478);
        float alpha__0_479 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_479 = g.getClip();
        AffineTransform defaultTransform__0_479 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_479 is CompositeGraphicsNode
        float alpha__0_479_0 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_479_0 = g.getClip();
        AffineTransform defaultTransform__0_479_0 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_479_0 is CompositeGraphicsNode
        float alpha__0_479_0_0 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_479_0_0 = g.getClip();
        AffineTransform defaultTransform__0_479_0_0 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        clip = new Area(g.getClip());
        clip.intersect(new Area(new Rectangle2D.Double(28.200000762939453,
                17.399999618530273, 365.2800102233887, 55.19999885559082)));
        g.setClip(clip);
        // _0_479_0_0 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(386.28, 72.6);
        ((GeneralPath) shape).lineTo(34.56, 72.6);
        ((GeneralPath) shape).curveTo(34.32, 72.6, 33.959, 72.479996, 33.839,
                72.24);
        ((GeneralPath) shape).lineTo(28.44, 65.28);
        ((GeneralPath) shape).curveTo(28.32, 65.160995, 28.2, 64.921, 28.2,
                64.68);
        ((GeneralPath) shape).lineTo(28.2, 26.880001);
        ((GeneralPath) shape).curveTo(28.2, 26.640001, 28.320002, 26.279001,
                28.560001, 26.160002);
        ((GeneralPath) shape).lineTo(33.959, 21.479002);
        ((GeneralPath) shape).curveTo(34.079, 21.359001, 34.319, 21.239002,
                34.56, 21.239002);
        ((GeneralPath) shape).lineTo(51.0, 21.239002);
        ((GeneralPath) shape).curveTo(51.24, 21.239002, 51.601, 21.359003,
                51.721, 21.599003);
        ((GeneralPath) shape).lineTo(53.760002, 24.359003);
        ((GeneralPath) shape).lineTo(314.28, 24.359003);
        ((GeneralPath) shape).lineTo(327.24, 17.519003);
        ((GeneralPath) shape).curveTo(327.36, 17.399002, 327.47998, 17.399002,
                327.719, 17.399002);
        ((GeneralPath) shape).lineTo(385.559, 17.399002);
        ((GeneralPath) shape).curveTo(385.679, 17.399002, 385.91898, 17.519003,
                386.16, 17.639002);
        ((GeneralPath) shape).lineTo(393.0, 23.039001);
        ((GeneralPath) shape).curveTo(393.24, 23.159002, 393.48, 23.518002,
                393.48, 23.759);
        ((GeneralPath) shape).lineTo(393.48, 65.4);
        ((GeneralPath) shape).curveTo(393.48, 65.64, 393.36002, 66.001,
                393.12003, 66.121);
        ((GeneralPath) shape).lineTo(392.4, 65.4);
        ((GeneralPath) shape).lineTo(391.44, 65.4);
        ((GeneralPath) shape).lineTo(392.4, 65.4);
        ((GeneralPath) shape).lineTo(393.12, 66.121);
        ((GeneralPath) shape).lineTo(392.4, 65.4);
        ((GeneralPath) shape).lineTo(391.44, 65.4);
        ((GeneralPath) shape).lineTo(392.4, 65.4);
        ((GeneralPath) shape).lineTo(393.12, 66.121);
        ((GeneralPath) shape).lineTo(387.0, 72.36);
        ((GeneralPath) shape).curveTo(386.76, 72.48, 386.519, 72.6, 386.28,
                72.6);
        ((GeneralPath) shape).moveTo(30.24, 64.32);
        ((GeneralPath) shape).lineTo(30.24, 64.32);
        ((GeneralPath) shape).lineTo(35.04, 70.56);
        ((GeneralPath) shape).lineTo(385.92102, 70.56);
        ((GeneralPath) shape).lineTo(389.40002, 67.08099);
        ((GeneralPath) shape).lineTo(391.44003, 65.04099);
        ((GeneralPath) shape).lineTo(391.44003, 24.24);
        ((GeneralPath) shape).lineTo(385.20004, 19.439999);
        ((GeneralPath) shape).lineTo(327.96106, 19.439999);
        ((GeneralPath) shape).lineTo(315.0, 26.16);
        ((GeneralPath) shape).curveTo(314.881, 26.279, 314.761, 26.279,
                314.521, 26.279);
        ((GeneralPath) shape).lineTo(53.28, 26.279);
        ((GeneralPath) shape)
                .curveTo(52.92, 26.279, 52.68, 26.16, 52.44, 25.92);
        ((GeneralPath) shape).lineTo(50.4, 23.28);
        ((GeneralPath) shape).lineTo(34.92, 23.28);
        ((GeneralPath) shape).lineTo(30.239998, 27.36);
        ((GeneralPath) shape).lineTo(30.239998, 64.32);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_479_0_0;
        g.setTransform(defaultTransform__0_479_0_0);
        g.setClip(clip__0_479_0_0);
        origAlpha = alpha__0_479_0;
        g.setTransform(defaultTransform__0_479_0);
        g.setClip(clip__0_479_0);
        origAlpha = alpha__0_479;
        g.setTransform(defaultTransform__0_479);
        g.setClip(clip__0_479);
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
        return 29;
    }

    /**
     * Returns the Y of the bounding box of the original SVG image.
     *
     * @return The Y of the bounding box of the original SVG image.
     */
    public static int getOrigY() {
        return 18;
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
