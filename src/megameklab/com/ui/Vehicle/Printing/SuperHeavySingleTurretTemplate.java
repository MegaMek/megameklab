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
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

/**
 * This class has been automatically generated using <a
 * href="http://englishjavadrinker.blogspot.com/search/label/SVGRoundTrip"
 * >SVGRoundTrip</a>.
 */
public class SuperHeavySingleTurretTemplate {
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
        paint = new Color(204, 206, 207, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(390.768, 196.306);
        ((GeneralPath) shape).lineTo(397.853, 203.393);
        ((GeneralPath) shape).lineTo(397.853, 270.084);
        ((GeneralPath) shape).lineTo(390.768, 277.172);
        ((GeneralPath) shape).lineTo(256.121, 277.172);
        ((GeneralPath) shape).lineTo(256.121, 196.306);
        ((GeneralPath) shape).lineTo(261.789, 187.802);
        ((GeneralPath) shape).lineTo(368.089, 187.802);
        ((GeneralPath) shape).lineTo(373.759, 196.306);
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(386.514, 192.055);
        ((GeneralPath) shape).lineTo(393.6, 199.142);
        ((GeneralPath) shape).lineTo(393.6, 265.833);
        ((GeneralPath) shape).lineTo(386.514, 272.92);
        ((GeneralPath) shape).lineTo(251.868, 272.92);
        ((GeneralPath) shape).lineTo(251.868, 192.055);
        ((GeneralPath) shape).lineTo(257.536, 183.55);
        ((GeneralPath) shape).lineTo(363.836, 183.55);
        ((GeneralPath) shape).lineTo(369.506, 192.055);
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
        paint = new Color(35, 31, 32, 255);
        stroke = new BasicStroke(2.0f, 0, 0, 10.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(386.514, 192.055);
        ((GeneralPath) shape).lineTo(393.6, 199.142);
        ((GeneralPath) shape).lineTo(393.6, 265.834);
        ((GeneralPath) shape).lineTo(386.514, 272.92);
        ((GeneralPath) shape).lineTo(251.868, 272.92);
        ((GeneralPath) shape).lineTo(251.868, 192.055);
        ((GeneralPath) shape).lineTo(257.536, 183.55);
        ((GeneralPath) shape).lineTo(363.836, 183.55);
        ((GeneralPath) shape).lineTo(369.506, 192.055);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(362.434, 201.976);
        ((GeneralPath) shape).lineTo(258.67, 201.976);
        ((GeneralPath) shape).lineTo(253.285, 193.896);
        ((GeneralPath) shape).lineTo(258.657, 185.817);
        ((GeneralPath) shape).lineTo(362.419, 185.817);
        ((GeneralPath) shape).lineTo(367.806, 193.896);
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
        paint = new Color(255, 255, 255, 255);
        stroke = new BasicStroke(1.0f, 0, 1, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(362.434, 201.976);
        ((GeneralPath) shape).lineTo(258.669, 201.976);
        ((GeneralPath) shape).lineTo(253.285, 193.896);
        ((GeneralPath) shape).lineTo(258.657, 185.817);
        ((GeneralPath) shape).lineTo(362.419, 185.817);
        ((GeneralPath) shape).lineTo(367.806, 193.896);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(266.004, 194.87);
        ((GeneralPath) shape).lineTo(267.525, 194.87);
        ((GeneralPath) shape).lineTo(267.525, 195.14499);
        ((GeneralPath) shape).curveTo(267.525, 196.258, 267.323, 196.97598,
                266.917, 197.30399);
        ((GeneralPath) shape).curveTo(266.513, 197.62898, 265.615, 197.79298,
                264.22598, 197.79298);
        ((GeneralPath) shape).curveTo(262.65298, 197.79298, 261.68198,
                197.53499, 261.31998, 197.01999);
        ((GeneralPath) shape).curveTo(260.95697, 196.50398, 260.77597,
                195.12698, 260.77597, 192.883);
        ((GeneralPath) shape).curveTo(260.77597, 191.565, 261.02197, 190.69499,
                261.51297, 190.27899);
        ((GeneralPath) shape).curveTo(262.00397, 189.86198, 263.02997, 189.655,
                264.59497, 189.655);
        ((GeneralPath) shape).curveTo(265.73196, 189.655, 266.49298, 189.824,
                266.87598, 190.16699);
        ((GeneralPath) shape).curveTo(267.25598, 190.50699, 267.44897, 191.187,
                267.44897, 192.20299);
        ((GeneralPath) shape).lineTo(267.455, 192.385);
        ((GeneralPath) shape).lineTo(265.934, 192.385);
        ((GeneralPath) shape).lineTo(265.934, 192.179);
        ((GeneralPath) shape).curveTo(265.934, 191.658, 265.83798, 191.321,
                265.63898, 191.174);
        ((GeneralPath) shape).curveTo(265.44296, 191.028, 264.99298, 190.954,
                264.291, 190.954);
        ((GeneralPath) shape).curveTo(263.353, 190.954, 262.788, 191.068,
                262.598, 191.29999);
        ((GeneralPath) shape).curveTo(262.41, 191.52899, 262.314, 192.21399,
                262.314, 193.35098);
        ((GeneralPath) shape).curveTo(262.314, 194.88098, 262.399, 195.78899,
                262.568, 196.06998);
        ((GeneralPath) shape).curveTo(262.737, 196.35097, 263.284, 196.49197,
                264.214, 196.49197);
        ((GeneralPath) shape).curveTo(264.965, 196.49197, 265.453, 196.41597,
                265.679, 196.25798);
        ((GeneralPath) shape).curveTo(265.90097, 196.10298, 266.01498,
                195.75998, 266.01498, 195.22697);
        ((GeneralPath) shape).lineTo(266.004, 194.87);
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(269.99, 193.75);
        ((GeneralPath) shape).lineTo(272.16498, 193.75);
        ((GeneralPath) shape).curveTo(272.68298, 193.75, 273.02798, 193.659,
                273.19797, 193.472);
        ((GeneralPath) shape).curveTo(273.36697, 193.287, 273.45197, 192.916,
                273.45197, 192.356);
        ((GeneralPath) shape).curveTo(273.45197, 191.78801, 273.37897, 191.418,
                273.23297, 191.252);
        ((GeneralPath) shape).curveTo(273.08698, 191.088, 272.76797, 191.002,
                272.27097, 191.002);
        ((GeneralPath) shape).lineTo(269.99097, 191.002);
        ((GeneralPath) shape).lineTo(269.99097, 193.75);
        ((GeneralPath) shape).moveTo(268.475, 197.723);
        ((GeneralPath) shape).lineTo(268.475, 189.725);
        ((GeneralPath) shape).lineTo(272.411, 189.725);
        ((GeneralPath) shape).curveTo(273.388, 189.725, 274.06403, 189.895,
                274.435, 190.235);
        ((GeneralPath) shape).curveTo(274.803, 190.575, 274.991, 191.19,
                274.991, 192.081);
        ((GeneralPath) shape).curveTo(274.991, 192.89, 274.9, 193.43999,
                274.71298, 193.739);
        ((GeneralPath) shape).curveTo(274.529, 194.035, 274.149, 194.24,
                273.57498, 194.354);
        ((GeneralPath) shape).lineTo(273.57498, 194.407);
        ((GeneralPath) shape).curveTo(274.45798, 194.45999, 274.90298, 194.979,
                274.90298, 195.95999);
        ((GeneralPath) shape).lineTo(274.90298, 197.724);
        ((GeneralPath) shape).lineTo(273.38797, 197.724);
        ((GeneralPath) shape).lineTo(273.38797, 196.266);
        ((GeneralPath) shape).curveTo(273.38797, 195.442, 272.98495, 195.029,
                272.17197, 195.029);
        ((GeneralPath) shape).lineTo(269.99097, 195.029);
        ((GeneralPath) shape).lineTo(269.99097, 197.72401);
        ((GeneralPath) shape).lineTo(268.475, 197.72401);
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
        paint = new Color(255, 255, 255, 255);
        shape = new Rectangle2D.Double(276.20098876953125, 189.72500610351562,
                1.5149999856948853, 7.998000144958496);
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(282.316, 191.084);
        ((GeneralPath) shape).lineTo(282.316, 197.723);
        ((GeneralPath) shape).lineTo(280.802, 197.723);
        ((GeneralPath) shape).lineTo(280.802, 191.084);
        ((GeneralPath) shape).lineTo(278.498, 191.084);
        ((GeneralPath) shape).lineTo(278.498, 189.725);
        ((GeneralPath) shape).lineTo(284.703, 189.725);
        ((GeneralPath) shape).lineTo(284.703, 191.084);
        ((GeneralPath) shape).lineTo(282.316, 191.084);
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
        paint = new Color(255, 255, 255, 255);
        shape = new Rectangle2D.Double(285.47100830078125, 189.72500610351562,
                1.5149999856948853, 7.998000144958496);
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(293.336, 194.87);
        ((GeneralPath) shape).lineTo(294.857, 194.87);
        ((GeneralPath) shape).lineTo(294.857, 195.14499);
        ((GeneralPath) shape).curveTo(294.857, 196.258, 294.655, 196.97598,
                294.249, 197.30399);
        ((GeneralPath) shape).curveTo(293.845, 197.62898, 292.947, 197.79298,
                291.559, 197.79298);
        ((GeneralPath) shape).curveTo(289.986, 197.79298, 289.01498, 197.53499,
                288.65298, 197.01999);
        ((GeneralPath) shape).curveTo(288.28998, 196.50398, 288.10898,
                195.12698, 288.10898, 192.883);
        ((GeneralPath) shape).curveTo(288.10898, 191.565, 288.35498, 190.69499,
                288.84598, 190.27899);
        ((GeneralPath) shape).curveTo(289.33698, 189.86198, 290.36398, 189.655,
                291.92798, 189.655);
        ((GeneralPath) shape).curveTo(293.06497, 189.655, 293.82498, 189.824,
                294.20898, 190.16699);
        ((GeneralPath) shape).curveTo(294.589, 190.50699, 294.78198, 191.187,
                294.78198, 192.20299);
        ((GeneralPath) shape).lineTo(294.788, 192.385);
        ((GeneralPath) shape).lineTo(293.267, 192.385);
        ((GeneralPath) shape).lineTo(293.267, 192.179);
        ((GeneralPath) shape).curveTo(293.267, 191.658, 293.171, 191.321,
                292.972, 191.174);
        ((GeneralPath) shape).curveTo(292.77698, 191.028, 292.326, 190.954,
                291.624, 190.954);
        ((GeneralPath) shape).curveTo(290.686, 190.954, 290.121, 191.068,
                289.931, 191.29999);
        ((GeneralPath) shape).curveTo(289.744, 191.52899, 289.648, 192.21399,
                289.648, 193.35098);
        ((GeneralPath) shape).curveTo(289.648, 194.88098, 289.73203, 195.78899,
                289.902, 196.06998);
        ((GeneralPath) shape).curveTo(290.071, 196.35098, 290.618, 196.49197,
                291.548, 196.49197);
        ((GeneralPath) shape).curveTo(292.299, 196.49197, 292.78702, 196.41597,
                293.013, 196.25798);
        ((GeneralPath) shape).curveTo(293.235, 196.10298, 293.349, 195.75998,
                293.349, 195.22697);
        ((GeneralPath) shape).lineTo(293.336, 194.87);
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(300.287, 195.069);
        ((GeneralPath) shape).lineTo(298.90698, 190.90201);
        ((GeneralPath) shape).lineTo(297.55, 195.069);
        ((GeneralPath) shape).lineTo(300.287, 195.069);
        ((GeneralPath) shape).moveTo(300.638, 196.188);
        ((GeneralPath) shape).lineTo(297.193, 196.188);
        ((GeneralPath) shape).lineTo(296.69598, 197.723);
        ((GeneralPath) shape).lineTo(295.094, 197.723);
        ((GeneralPath) shape).lineTo(297.749, 189.725);
        ((GeneralPath) shape).lineTo(300.024, 189.725);
        ((GeneralPath) shape).lineTo(302.72, 197.723);
        ((GeneralPath) shape).lineTo(301.147, 197.723);
        ((GeneralPath) shape).lineTo(300.638, 196.188);
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(304.928, 189.725);
        ((GeneralPath) shape).lineTo(304.928, 196.364);
        ((GeneralPath) shape).lineTo(308.671, 196.364);
        ((GeneralPath) shape).lineTo(308.671, 197.723);
        ((GeneralPath) shape).lineTo(303.413, 197.723);
        ((GeneralPath) shape).lineTo(303.413, 189.725);
        ((GeneralPath) shape).lineTo(304.928, 189.725);
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(313.946, 196.446);
        ((GeneralPath) shape).lineTo(316.104, 196.446);
        ((GeneralPath) shape).curveTo(316.829, 196.446, 317.297, 196.27899,
                317.51, 195.94499);
        ((GeneralPath) shape).curveTo(317.721, 195.612, 317.828, 194.87599,
                317.828, 193.73299);
        ((GeneralPath) shape).curveTo(317.828, 192.555, 317.73502, 191.805,
                317.542, 191.48299);
        ((GeneralPath) shape).curveTo(317.353, 191.16399, 316.905, 191.00299,
                316.197, 191.00299);
        ((GeneralPath) shape).lineTo(313.94498, 191.00299);
        ((GeneralPath) shape).lineTo(313.94498, 196.446);
        ((GeneralPath) shape).moveTo(312.432, 197.723);
        ((GeneralPath) shape).lineTo(312.432, 189.725);
        ((GeneralPath) shape).lineTo(316.35602, 189.725);
        ((GeneralPath) shape).curveTo(317.47003, 189.725, 318.252, 189.968,
                318.699, 190.457);
        ((GeneralPath) shape).curveTo(319.143, 190.944, 319.368, 191.799,
                319.368, 193.023);
        ((GeneralPath) shape).curveTo(319.368, 195.01799, 319.19, 196.302,
                318.83102, 196.87);
        ((GeneralPath) shape).curveTo(318.47403, 197.439, 317.66403, 197.72299,
                316.403, 197.72299);
        ((GeneralPath) shape).lineTo(312.432, 197.72299);
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(324.948, 195.069);
        ((GeneralPath) shape).lineTo(323.568, 190.90201);
        ((GeneralPath) shape).lineTo(322.211, 195.069);
        ((GeneralPath) shape).lineTo(324.948, 195.069);
        ((GeneralPath) shape).moveTo(325.299, 196.188);
        ((GeneralPath) shape).lineTo(321.855, 196.188);
        ((GeneralPath) shape).lineTo(321.358, 197.723);
        ((GeneralPath) shape).lineTo(319.755, 197.723);
        ((GeneralPath) shape).lineTo(322.41, 189.725);
        ((GeneralPath) shape).lineTo(324.685, 189.725);
        ((GeneralPath) shape).lineTo(327.38, 197.723);
        ((GeneralPath) shape).lineTo(325.807, 197.723);
        ((GeneralPath) shape).lineTo(325.299, 196.188);
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(337.407, 189.725);
        ((GeneralPath) shape).lineTo(337.407, 197.723);
        ((GeneralPath) shape).lineTo(335.893, 197.723);
        ((GeneralPath) shape).lineTo(335.893, 193.36401);
        ((GeneralPath) shape).curveTo(335.893, 193.01802, 335.901, 192.62302,
                335.92102, 192.18001);
        ((GeneralPath) shape).lineTo(335.95, 191.582);
        ((GeneralPath) shape).lineTo(335.979, 190.991);
        ((GeneralPath) shape).lineTo(335.932, 190.991);
        ((GeneralPath) shape).lineTo(335.75, 191.547);
        ((GeneralPath) shape).lineTo(335.575, 192.104);
        ((GeneralPath) shape).curveTo(335.41202, 192.602, 335.286, 192.972,
                335.195, 193.211);
        ((GeneralPath) shape).lineTo(333.44, 197.72299);
        ((GeneralPath) shape).lineTo(332.06, 197.72299);
        ((GeneralPath) shape).lineTo(330.288, 193.24599);
        ((GeneralPath) shape).curveTo(330.191, 192.99998, 330.063, 192.63098,
                329.902, 192.13899);
        ((GeneralPath) shape).lineTo(329.72, 191.58199);
        ((GeneralPath) shape).lineTo(329.538, 191.03099);
        ((GeneralPath) shape).lineTo(329.492, 191.03099);
        ((GeneralPath) shape).lineTo(329.521, 191.61198);
        ((GeneralPath) shape).lineTo(329.55, 192.19798);
        ((GeneralPath) shape).curveTo(329.572, 192.64899, 329.585, 193.03798,
                329.585, 193.36398);
        ((GeneralPath) shape).lineTo(329.585, 197.72298);
        ((GeneralPath) shape).lineTo(328.06998, 197.72298);
        ((GeneralPath) shape).lineTo(328.06998, 189.72498);
        ((GeneralPath) shape).lineTo(330.53796, 189.72498);
        ((GeneralPath) shape).lineTo(331.96497, 193.42897);
        ((GeneralPath) shape).curveTo(332.06195, 193.68597, 332.18997,
                194.05498, 332.35095, 194.53596);
        ((GeneralPath) shape).lineTo(332.52695, 195.09297);
        ((GeneralPath) shape).lineTo(332.70895, 195.64297);
        ((GeneralPath) shape).lineTo(332.76096, 195.64297);
        ((GeneralPath) shape).lineTo(332.93097, 195.09297);
        ((GeneralPath) shape).lineTo(333.10696, 194.54198);
        ((GeneralPath) shape).curveTo(333.25098, 194.07898, 333.37598,
                193.70998, 333.48096, 193.43997);
        ((GeneralPath) shape).lineTo(334.88397, 189.72498);
        ((GeneralPath) shape).lineTo(337.407, 189.72498);
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(343.285, 195.069);
        ((GeneralPath) shape).lineTo(341.905, 190.90201);
        ((GeneralPath) shape).lineTo(340.548, 195.069);
        ((GeneralPath) shape).lineTo(343.285, 195.069);
        ((GeneralPath) shape).moveTo(343.636, 196.188);
        ((GeneralPath) shape).lineTo(340.192, 196.188);
        ((GeneralPath) shape).lineTo(339.69498, 197.723);
        ((GeneralPath) shape).lineTo(338.09198, 197.723);
        ((GeneralPath) shape).lineTo(340.74698, 189.725);
        ((GeneralPath) shape).lineTo(343.02197, 189.725);
        ((GeneralPath) shape).lineTo(345.71698, 197.723);
        ((GeneralPath) shape).lineTo(344.14398, 197.723);
        ((GeneralPath) shape).lineTo(343.636, 196.188);
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(349.552, 193.469);
        ((GeneralPath) shape).lineTo(353.06702, 193.469);
        ((GeneralPath) shape).lineTo(353.09003, 195.05699);
        ((GeneralPath) shape).curveTo(353.09003, 196.211, 352.87302, 196.95898,
                352.43802, 197.29199);
        ((GeneralPath) shape).curveTo(352.005, 197.62599, 351.035, 197.793,
                349.52902, 197.793);
        ((GeneralPath) shape).curveTo(348.148, 197.793, 347.23602, 197.56999,
                346.786, 197.125);
        ((GeneralPath) shape).curveTo(346.33902, 196.68, 346.113, 195.771,
                346.113, 194.401);
        ((GeneralPath) shape).curveTo(346.113, 192.651, 346.20102, 191.547,
                346.38202, 191.084);
        ((GeneralPath) shape).curveTo(346.605, 190.521, 346.941, 190.144,
                347.39502, 189.948);
        ((GeneralPath) shape).curveTo(347.84503, 189.754, 348.614, 189.655,
                349.699, 189.655);
        ((GeneralPath) shape).curveTo(351.117, 189.655, 352.032, 189.804,
                352.448, 190.109);
        ((GeneralPath) shape).curveTo(352.86, 190.411, 353.068, 191.082,
                353.068, 192.122);
        ((GeneralPath) shape).lineTo(351.53598, 192.122);
        ((GeneralPath) shape).curveTo(351.50998, 191.601, 351.395, 191.276,
                351.193, 191.14699);
        ((GeneralPath) shape).curveTo(350.995, 191.02098, 350.48898, 190.956,
                349.682, 190.956);
        ((GeneralPath) shape).curveTo(348.80402, 190.956, 348.24802, 191.06499,
                348.012, 191.28499);
        ((GeneralPath) shape).curveTo(347.77798, 191.50099, 347.658, 192.01698,
                347.658, 192.82599);
        ((GeneralPath) shape).lineTo(347.65198, 193.62898);
        ((GeneralPath) shape).lineTo(347.66397, 194.65398);
        ((GeneralPath) shape).curveTo(347.66397, 195.44598, 347.78098,
                195.95198, 348.01596, 196.16898);
        ((GeneralPath) shape).curveTo(348.24896, 196.38597, 348.78995,
                196.49397, 349.64096, 196.49397);
        ((GeneralPath) shape).curveTo(350.46597, 196.49397, 350.99295,
                196.40297, 351.21695, 196.21898);
        ((GeneralPath) shape).curveTo(351.43994, 196.03697, 351.55396,
                195.60298, 351.55396, 194.91798);
        ((GeneralPath) shape).lineTo(351.55997, 194.58998);
        ((GeneralPath) shape).lineTo(349.55396, 194.58998);
        ((GeneralPath) shape).lineTo(349.55396, 193.469);
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(355.505, 191.002);
        ((GeneralPath) shape).lineTo(355.505, 193.065);
        ((GeneralPath) shape).lineTo(359.248, 193.065);
        ((GeneralPath) shape).lineTo(359.248, 194.184);
        ((GeneralPath) shape).lineTo(355.505, 194.184);
        ((GeneralPath) shape).lineTo(355.505, 196.446);
        ((GeneralPath) shape).lineTo(359.487, 196.446);
        ((GeneralPath) shape).lineTo(359.487, 197.723);
        ((GeneralPath) shape).lineTo(353.99, 197.723);
        ((GeneralPath) shape).lineTo(353.99, 189.725);
        ((GeneralPath) shape).lineTo(359.452, 189.725);
        ((GeneralPath) shape).lineTo(359.452, 191.002);
        ((GeneralPath) shape).lineTo(355.505, 191.002);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(259.898, 207.371);
        ((GeneralPath) shape).lineTo(259.898, 212.144);
        ((GeneralPath) shape).lineTo(259.298, 212.144);
        ((GeneralPath) shape).lineTo(259.298, 207.371);
        ((GeneralPath) shape).lineTo(257.563, 207.371);
        ((GeneralPath) shape).lineTo(257.563, 206.812);
        ((GeneralPath) shape).lineTo(261.618, 206.812);
        ((GeneralPath) shape).lineTo(261.618, 207.371);
        ((GeneralPath) shape).lineTo(259.898, 207.371);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(265.168, 208.41);
        ((GeneralPath) shape).lineTo(265.168, 212.144);
        ((GeneralPath) shape).lineTo(264.622, 212.144);
        ((GeneralPath) shape).lineTo(264.661, 211.65599);
        ((GeneralPath) shape).lineTo(264.64902, 211.644);
        ((GeneralPath) shape).curveTo(264.46002, 212.013, 264.05402, 212.19899,
                263.433, 212.19899);
        ((GeneralPath) shape).curveTo(262.56302, 212.19899, 262.127, 211.76599,
                262.127, 210.894);
        ((GeneralPath) shape).lineTo(262.127, 208.41);
        ((GeneralPath) shape).lineTo(262.673, 208.41);
        ((GeneralPath) shape).lineTo(262.673, 210.894);
        ((GeneralPath) shape).curveTo(262.673, 211.232, 262.728, 211.458,
                262.84, 211.57399);
        ((GeneralPath) shape).curveTo(262.951, 211.687, 263.172, 211.74599,
                263.499, 211.74599);
        ((GeneralPath) shape).curveTo(263.92798, 211.74599, 264.222, 211.66199,
                264.382, 211.48999);
        ((GeneralPath) shape).curveTo(264.542, 211.32098, 264.62198, 211.008,
                264.62198, 210.551);
        ((GeneralPath) shape).lineTo(264.62198, 208.40999);
        ((GeneralPath) shape).lineTo(265.168, 208.40999);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(266.229, 208.41);
        ((GeneralPath) shape).lineTo(266.775, 208.41);
        ((GeneralPath) shape).lineTo(266.721, 208.84);
        ((GeneralPath) shape).lineTo(266.732, 208.85199);
        ((GeneralPath) shape).curveTo(266.94598, 208.49998, 267.303, 208.32399,
                267.8, 208.32399);
        ((GeneralPath) shape).curveTo(268.486, 208.32399, 268.82898, 208.678,
                268.82898, 209.387);
        ((GeneralPath) shape).lineTo(268.82498, 209.64499);
        ((GeneralPath) shape).lineTo(268.287, 209.64499);
        ((GeneralPath) shape).lineTo(268.29898, 209.551);
        ((GeneralPath) shape).curveTo(268.30698, 209.45299, 268.31097, 209.387,
                268.31097, 209.35199);
        ((GeneralPath) shape).curveTo(268.31097, 208.969, 268.10397, 208.777,
                267.68698, 208.777);
        ((GeneralPath) shape).curveTo(267.07898, 208.777, 266.774, 209.152,
                266.774, 209.90599);
        ((GeneralPath) shape).lineTo(266.774, 212.144);
        ((GeneralPath) shape).lineTo(266.228, 212.144);
        ((GeneralPath) shape).lineTo(266.228, 208.41);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(269.406, 208.41);
        ((GeneralPath) shape).lineTo(269.952, 208.41);
        ((GeneralPath) shape).lineTo(269.897, 208.84);
        ((GeneralPath) shape).lineTo(269.909, 208.85199);
        ((GeneralPath) shape).curveTo(270.123, 208.49998, 270.48, 208.32399,
                270.977, 208.32399);
        ((GeneralPath) shape).curveTo(271.663, 208.32399, 272.00598, 208.678,
                272.00598, 209.387);
        ((GeneralPath) shape).lineTo(272.00198, 209.64499);
        ((GeneralPath) shape).lineTo(271.464, 209.64499);
        ((GeneralPath) shape).lineTo(271.47598, 209.551);
        ((GeneralPath) shape).curveTo(271.48398, 209.45299, 271.48798, 209.387,
                271.48798, 209.35199);
        ((GeneralPath) shape).curveTo(271.48798, 208.969, 271.28098, 208.777,
                270.86398, 208.777);
        ((GeneralPath) shape).curveTo(270.25598, 208.777, 269.951, 209.152,
                269.951, 209.90599);
        ((GeneralPath) shape).lineTo(269.951, 212.144);
        ((GeneralPath) shape).lineTo(269.405, 212.144);
        ((GeneralPath) shape).lineTo(269.405, 208.41);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(275.122, 209.953);
        ((GeneralPath) shape).lineTo(275.118, 209.77701);
        ((GeneralPath) shape).curveTo(275.118, 209.37502, 275.052, 209.11101,
                274.919, 208.99);
        ((GeneralPath) shape).curveTo(274.786, 208.869, 274.502, 208.808,
                274.061, 208.808);
        ((GeneralPath) shape).curveTo(273.62, 208.808, 273.334, 208.878,
                273.202, 209.021);
        ((GeneralPath) shape).curveTo(273.07098, 209.162, 273.005, 209.472,
                273.005, 209.953);
        ((GeneralPath) shape).lineTo(275.122, 209.953);
        ((GeneralPath) shape).moveTo(275.122, 211.016);
        ((GeneralPath) shape).lineTo(275.68002, 211.016);
        ((GeneralPath) shape).lineTo(275.68402, 211.15201);
        ((GeneralPath) shape).curveTo(275.68402, 211.539, 275.56702, 211.81001,
                275.33102, 211.966);
        ((GeneralPath) shape).curveTo(275.09702, 212.12001, 274.68402, 212.198,
                274.09302, 212.198);
        ((GeneralPath) shape).curveTo(273.407, 212.198, 272.95602, 212.073,
                272.742, 211.82199);
        ((GeneralPath) shape).curveTo(272.528, 211.57199, 272.421, 211.04199,
                272.421, 210.23799);
        ((GeneralPath) shape).curveTo(272.421, 209.49399, 272.52798, 208.99399,
                272.744, 208.73799);
        ((GeneralPath) shape).curveTo(272.95898, 208.484, 273.382, 208.355,
                274.011, 208.355);
        ((GeneralPath) shape).curveTo(274.698, 208.355, 275.146, 208.46399,
                275.36, 208.687);
        ((GeneralPath) shape).curveTo(275.572, 208.90799, 275.68, 209.375,
                275.68, 210.086);
        ((GeneralPath) shape).lineTo(275.68, 210.378);
        ((GeneralPath) shape).lineTo(272.99698, 210.378);
        ((GeneralPath) shape).curveTo(272.99698, 210.966, 273.05997, 211.341,
                273.18597, 211.503);
        ((GeneralPath) shape).curveTo(273.31097, 211.66301, 273.60498,
                211.74501, 274.06897, 211.74501);
        ((GeneralPath) shape).curveTo(274.50696, 211.74501, 274.79196,
                211.70801, 274.92496, 211.63);
        ((GeneralPath) shape).curveTo(275.05597, 211.554, 275.12195, 211.388,
                275.12195, 211.132);
        ((GeneralPath) shape).lineTo(275.12195, 211.016);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(278.513, 208.41);
        ((GeneralPath) shape).lineTo(278.513, 208.863);
        ((GeneralPath) shape).lineTo(277.078, 208.863);
        ((GeneralPath) shape).lineTo(277.078, 211.14801);
        ((GeneralPath) shape).curveTo(277.078, 211.54701, 277.253, 211.74602,
                277.608, 211.74602);
        ((GeneralPath) shape).curveTo(277.959, 211.74602, 278.134, 211.56802,
                278.134, 211.21101);
        ((GeneralPath) shape).lineTo(278.138, 211.02701);
        ((GeneralPath) shape).lineTo(278.146, 210.82);
        ((GeneralPath) shape).lineTo(278.65298, 210.82);
        ((GeneralPath) shape).lineTo(278.65698, 211.097);
        ((GeneralPath) shape).curveTo(278.65698, 211.831, 278.31, 212.199,
                277.61197, 212.199);
        ((GeneralPath) shape).curveTo(276.89197, 212.199, 276.53198, 211.89401,
                276.53198, 211.282);
        ((GeneralPath) shape).lineTo(276.53198, 208.864);
        ((GeneralPath) shape).lineTo(276.01697, 208.864);
        ((GeneralPath) shape).lineTo(276.01697, 208.411);
        ((GeneralPath) shape).lineTo(276.53198, 208.411);
        ((GeneralPath) shape).lineTo(276.53198, 207.513);
        ((GeneralPath) shape).lineTo(277.07797, 207.513);
        ((GeneralPath) shape).lineTo(277.07797, 208.411);
        ((GeneralPath) shape).lineTo(278.513, 208.411);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(281.763, 206.812);
        ((GeneralPath) shape).lineTo(281.763, 211.586);
        ((GeneralPath) shape).lineTo(284.5, 211.586);
        ((GeneralPath) shape).lineTo(284.5, 212.144);
        ((GeneralPath) shape).lineTo(281.162, 212.144);
        ((GeneralPath) shape).lineTo(281.162, 206.812);
        ((GeneralPath) shape).lineTo(281.763, 206.812);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(286.547, 208.808);
        ((GeneralPath) shape).curveTo(286.064, 208.808, 285.76498, 208.886,
                285.648, 209.04399);
        ((GeneralPath) shape).curveTo(285.53302, 209.2, 285.475, 209.61198,
                285.475, 210.27599);
        ((GeneralPath) shape).curveTo(285.475, 210.93999, 285.532, 211.35098,
                285.648, 211.50899);
        ((GeneralPath) shape).curveTo(285.763, 211.665, 286.064, 211.74498,
                286.547, 211.74498);
        ((GeneralPath) shape).curveTo(287.03198, 211.74498, 287.333, 211.66698,
                287.449, 211.50899);
        ((GeneralPath) shape).curveTo(287.564, 211.35298, 287.62302, 210.93999,
                287.62302, 210.27599);
        ((GeneralPath) shape).curveTo(287.62302, 209.61198, 287.566, 209.20198,
                287.449, 209.04399);
        ((GeneralPath) shape).curveTo(287.334, 208.888, 287.034, 208.808,
                286.547, 208.808);
        ((GeneralPath) shape).moveTo(286.547, 208.355);
        ((GeneralPath) shape).curveTo(287.235, 208.355, 287.682, 208.474,
                287.888, 208.71399);
        ((GeneralPath) shape).curveTo(288.093, 208.952, 288.197, 209.47398,
                288.197, 210.277);
        ((GeneralPath) shape).curveTo(288.197, 211.07799, 288.095, 211.599,
                287.888, 211.84);
        ((GeneralPath) shape).curveTo(287.683, 212.078, 287.237, 212.19899,
                286.547, 212.19899);
        ((GeneralPath) shape).curveTo(285.86, 212.19899, 285.416, 212.07999,
                285.21, 211.84);
        ((GeneralPath) shape).curveTo(285.005, 211.60199, 284.90198, 211.081,
                284.90198, 210.277);
        ((GeneralPath) shape).curveTo(284.90198, 209.47699, 285.00397,
                208.95499, 285.21, 208.71399);
        ((GeneralPath) shape).curveTo(285.414, 208.477, 285.86, 208.355,
                286.547, 208.355);
        g.setPaint(paint);
        g.fill(shape);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(291.608, 210.797);
        ((GeneralPath) shape).lineTo(292.154, 210.797);
        ((GeneralPath) shape).lineTo(292.158, 211.0);
        ((GeneralPath) shape).curveTo(292.158, 211.799, 291.636, 212.199,
                290.591, 212.199);
        ((GeneralPath) shape).curveTo(289.921, 212.199, 289.478, 212.074,
                289.26102, 211.821);
        ((GeneralPath) shape).curveTo(289.04703, 211.569, 288.93802, 211.048,
                288.93802, 210.258);
        ((GeneralPath) shape).curveTo(288.93802, 209.51999, 289.04703,
                209.01799, 289.26703, 208.75299);
        ((GeneralPath) shape).curveTo(289.48602, 208.48898, 289.90402,
                208.35599, 290.52103, 208.35599);
        ((GeneralPath) shape).curveTo(291.12503, 208.35599, 291.53802,
                208.44398, 291.76004, 208.624);
        ((GeneralPath) shape).curveTo(291.98105, 208.80199, 292.09204, 209.138,
                292.09204, 209.62999);
        ((GeneralPath) shape).lineTo(291.54605, 209.62999);
        ((GeneralPath) shape).lineTo(291.54605, 209.53299);
        ((GeneralPath) shape).curveTo(291.54605, 209.243, 291.47604, 209.05199,
                291.33206, 208.954);
        ((GeneralPath) shape).curveTo(291.19006, 208.858, 290.90506, 208.80899,
                290.47806, 208.80899);
        ((GeneralPath) shape).curveTo(290.06805, 208.80899, 289.80307,
                208.89899, 289.68607, 209.08199);
        ((GeneralPath) shape).curveTo(289.56906, 209.26399, 289.51007,
                209.67198, 289.51007, 210.30899);
        ((GeneralPath) shape).curveTo(289.51007, 210.928, 289.57608, 211.323,
                289.71106, 211.493);
        ((GeneralPath) shape).curveTo(289.84305, 211.661, 290.15707, 211.747,
                290.64905, 211.747);
        ((GeneralPath) shape).curveTo(291.06406, 211.747, 291.32706, 211.692,
                291.44003, 211.581);
        ((GeneralPath) shape).curveTo(291.551, 211.471, 291.608, 211.208,
                291.608, 210.797);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(293.464, 206.812);
        ((GeneralPath) shape).lineTo(293.464, 209.91);
        ((GeneralPath) shape).lineTo(293.714, 209.91);
        ((GeneralPath) shape).lineTo(295.031, 208.41);
        ((GeneralPath) shape).lineTo(295.718, 208.41);
        ((GeneralPath) shape).lineTo(294.123, 210.125);
        ((GeneralPath) shape).lineTo(296.006, 212.144);
        ((GeneralPath) shape).lineTo(295.269, 212.144);
        ((GeneralPath) shape).lineTo(293.687, 210.336);
        ((GeneralPath) shape).lineTo(293.464, 210.336);
        ((GeneralPath) shape).lineTo(293.464, 212.144);
        ((GeneralPath) shape).lineTo(292.918, 212.144);
        ((GeneralPath) shape).lineTo(292.918, 206.812);
        ((GeneralPath) shape).lineTo(293.464, 206.812);
        g.setPaint(paint);
        g.fill(shape);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(298.944, 209.953);
        ((GeneralPath) shape).lineTo(298.94, 209.77701);
        ((GeneralPath) shape).curveTo(298.94, 209.37502, 298.874, 209.11101,
                298.741, 208.99);
        ((GeneralPath) shape).curveTo(298.609, 208.869, 298.324, 208.808,
                297.884, 208.808);
        ((GeneralPath) shape).curveTo(297.443, 208.808, 297.157, 208.878,
                297.02402, 209.021);
        ((GeneralPath) shape).curveTo(296.894, 209.162, 296.82703, 209.472,
                296.82703, 209.953);
        ((GeneralPath) shape).lineTo(298.944, 209.953);
        ((GeneralPath) shape).moveTo(298.944, 211.016);
        ((GeneralPath) shape).lineTo(299.502, 211.016);
        ((GeneralPath) shape).lineTo(299.505, 211.15201);
        ((GeneralPath) shape).curveTo(299.505, 211.539, 299.388, 211.81001,
                299.152, 211.966);
        ((GeneralPath) shape).curveTo(298.918, 212.12001, 298.505, 212.198,
                297.914, 212.198);
        ((GeneralPath) shape).curveTo(297.227, 212.198, 296.777, 212.073,
                296.563, 211.82199);
        ((GeneralPath) shape).curveTo(296.349, 211.57199, 296.241, 211.04199,
                296.241, 210.23799);
        ((GeneralPath) shape).curveTo(296.241, 209.49399, 296.348, 208.99399,
                296.565, 208.73799);
        ((GeneralPath) shape).curveTo(296.779, 208.484, 297.203, 208.355,
                297.832, 208.355);
        ((GeneralPath) shape).curveTo(298.519, 208.355, 298.966, 208.46399,
                299.181, 208.687);
        ((GeneralPath) shape).curveTo(299.393, 208.90799, 299.501, 209.375,
                299.501, 210.086);
        ((GeneralPath) shape).lineTo(299.501, 210.378);
        ((GeneralPath) shape).lineTo(296.818, 210.378);
        ((GeneralPath) shape).curveTo(296.818, 210.966, 296.88098, 211.341,
                297.007, 211.503);
        ((GeneralPath) shape).curveTo(297.132, 211.66301, 297.426, 211.74501,
                297.88998, 211.74501);
        ((GeneralPath) shape).curveTo(298.32797, 211.74501, 298.61298,
                211.70801, 298.74597, 211.63);
        ((GeneralPath) shape).curveTo(298.87598, 211.554, 298.94296, 211.388,
                298.94296, 211.132);
        ((GeneralPath) shape).lineTo(298.94296, 211.016);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(301.832, 208.808);
        ((GeneralPath) shape).curveTo(301.431, 208.808, 301.162, 208.9,
                301.029, 209.088);
        ((GeneralPath) shape).curveTo(300.896, 209.273, 300.83, 209.651,
                300.83, 210.218);
        ((GeneralPath) shape).curveTo(300.83, 210.843, 300.89398, 211.253,
                301.02698, 211.45);
        ((GeneralPath) shape).curveTo(301.158, 211.645, 301.43097, 211.745,
                301.84396, 211.745);
        ((GeneralPath) shape).curveTo(302.29196, 211.745, 302.58496, 211.64699,
                302.72098, 211.45);
        ((GeneralPath) shape).curveTo(302.856, 211.25499, 302.92398, 210.827,
                302.92398, 210.16699);
        ((GeneralPath) shape).curveTo(302.92398, 209.63399, 302.84998, 209.275,
                302.69998, 209.08699);
        ((GeneralPath) shape).curveTo(302.551, 208.902, 302.261, 208.808,
                301.832, 208.808);
        ((GeneralPath) shape).moveTo(303.474, 206.812);
        ((GeneralPath) shape).lineTo(303.474, 212.144);
        ((GeneralPath) shape).lineTo(302.928, 212.144);
        ((GeneralPath) shape).lineTo(302.95502, 211.66);
        ((GeneralPath) shape).lineTo(302.93903, 211.657);
        ((GeneralPath) shape).curveTo(302.76703, 212.01799, 302.37003, 212.199,
                301.74203, 212.199);
        ((GeneralPath) shape).curveTo(301.17102, 212.199, 300.78104, 212.06,
                300.57104, 211.782);
        ((GeneralPath) shape).curveTo(300.36304, 211.504, 300.25705, 210.985,
                300.25705, 210.22299);
        ((GeneralPath) shape).curveTo(300.25705, 209.52399, 300.36206,
                209.03699, 300.57306, 208.76399);
        ((GeneralPath) shape).curveTo(300.78305, 208.493, 301.15906, 208.35599,
                301.70306, 208.35599);
        ((GeneralPath) shape).curveTo(302.38205, 208.35599, 302.78506,
                208.53198, 302.91507, 208.88298);
        ((GeneralPath) shape).lineTo(302.92706, 208.87598);
        ((GeneralPath) shape).lineTo(302.92706, 206.81297);
        ((GeneralPath) shape).lineTo(303.474, 206.81297);
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
        paint = new Color(35, 31, 32, 255);
        stroke = new BasicStroke(1.0f, 0, 0, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(387.315, 212.356);
        ((GeneralPath) shape).curveTo(387.315, 213.10701, 386.707, 213.717,
                385.95502, 213.717);
        ((GeneralPath) shape).lineTo(379.03403, 213.717);
        ((GeneralPath) shape).curveTo(378.28003, 213.717, 377.67303, 213.108,
                377.67303, 212.356);
        ((GeneralPath) shape).lineTo(377.67303, 205.364);
        ((GeneralPath) shape).curveTo(377.67303, 204.612, 378.28003, 204.002,
                379.03403, 204.002);
        ((GeneralPath) shape).lineTo(385.95502, 204.002);
        ((GeneralPath) shape).curveTo(386.70703, 204.002, 387.315, 204.612,
                387.315, 205.364);
        ((GeneralPath) shape).lineTo(387.315, 212.356);
        ((GeneralPath) shape).closePath();
        ((GeneralPath) shape).moveTo(318.816, 212.355);
        ((GeneralPath) shape).curveTo(318.816, 213.105, 318.208, 213.717,
                317.45502, 213.717);
        ((GeneralPath) shape).lineTo(310.53403, 213.717);
        ((GeneralPath) shape).curveTo(309.782, 213.717, 309.17404, 213.105,
                309.17404, 212.355);
        ((GeneralPath) shape).lineTo(309.17404, 205.362);
        ((GeneralPath) shape).curveTo(309.17404, 204.61, 309.78204, 204.001,
                310.53403, 204.001);
        ((GeneralPath) shape).lineTo(317.45502, 204.001);
        ((GeneralPath) shape).curveTo(318.208, 204.001, 318.816, 204.60901,
                318.816, 205.362);
        ((GeneralPath) shape).lineTo(318.816, 212.355);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(330.712, 207.321);
        ((GeneralPath) shape).lineTo(330.712, 209.138);
        ((GeneralPath) shape).lineTo(333.414, 209.138);
        ((GeneralPath) shape).lineTo(333.414, 209.646);
        ((GeneralPath) shape).lineTo(330.712, 209.646);
        ((GeneralPath) shape).lineTo(330.712, 211.638);
        ((GeneralPath) shape).lineTo(333.53, 211.638);
        ((GeneralPath) shape).lineTo(333.53, 212.146);
        ((GeneralPath) shape).lineTo(330.111, 212.146);
        ((GeneralPath) shape).lineTo(330.111, 206.813);
        ((GeneralPath) shape).lineTo(333.53, 206.813);
        ((GeneralPath) shape).lineTo(333.53, 207.321);
        ((GeneralPath) shape).lineTo(330.712, 207.321);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(334.276, 208.411);
        ((GeneralPath) shape).lineTo(334.801, 208.411);
        ((GeneralPath) shape).lineTo(334.785, 208.91899);
        ((GeneralPath) shape).lineTo(334.801, 208.93098);
        ((GeneralPath) shape).curveTo(334.96698, 208.54799, 335.38, 208.35698,
                336.041, 208.35698);
        ((GeneralPath) shape).curveTo(336.573, 208.35698, 336.934, 208.45097,
                337.123, 208.63799);
        ((GeneralPath) shape).curveTo(337.31097, 208.82599, 337.405, 209.18498,
                337.405, 209.71599);
        ((GeneralPath) shape).lineTo(337.405, 212.14598);
        ((GeneralPath) shape).lineTo(336.859, 212.14598);
        ((GeneralPath) shape).lineTo(336.859, 209.62299);
        ((GeneralPath) shape).curveTo(336.859, 209.30298, 336.798, 209.08798,
                336.676, 208.97598);
        ((GeneralPath) shape).curveTo(336.555, 208.86699, 336.319, 208.80998,
                335.97, 208.80998);
        ((GeneralPath) shape).curveTo(335.204, 208.80998, 334.82, 209.17299,
                334.82, 209.89899);
        ((GeneralPath) shape).lineTo(334.82, 212.14598);
        ((GeneralPath) shape).lineTo(334.275, 212.14598);
        ((GeneralPath) shape).lineTo(334.275, 208.411);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(339.891, 208.81);
        ((GeneralPath) shape).curveTo(339.461, 208.81, 339.185, 208.899,
                339.056, 209.081);
        ((GeneralPath) shape).curveTo(338.93, 209.26099, 338.865, 209.657,
                338.865, 210.26999);
        ((GeneralPath) shape).curveTo(338.865, 210.887, 338.92798, 211.28699,
                339.056, 211.471);
        ((GeneralPath) shape).curveTo(339.183, 211.65399, 339.461, 211.74599,
                339.891, 211.74599);
        ((GeneralPath) shape).curveTo(340.324, 211.74599, 340.61, 211.64598,
                340.748, 211.443);
        ((GeneralPath) shape).curveTo(340.88498, 211.24199, 340.95398,
                210.82199, 340.95398, 210.18399);
        ((GeneralPath) shape).curveTo(340.95398, 209.62099, 340.887, 209.25198,
                340.748, 209.075);
        ((GeneralPath) shape).curveTo(340.611, 208.899, 340.325, 208.81,
                339.891, 208.81);
        ((GeneralPath) shape).moveTo(341.517, 208.411);
        ((GeneralPath) shape).lineTo(341.517, 212.388);
        ((GeneralPath) shape).curveTo(341.517, 212.929, 341.399, 213.302,
                341.165, 213.505);
        ((GeneralPath) shape).curveTo(340.931, 213.70801, 340.502, 213.81,
                339.87802, 213.81);
        ((GeneralPath) shape).curveTo(339.32303, 213.81, 338.94302, 213.724,
                338.74002, 213.552);
        ((GeneralPath) shape).curveTo(338.53702, 213.38, 338.43503, 213.06,
                338.43503, 212.587);
        ((GeneralPath) shape).lineTo(338.96103, 212.587);
        ((GeneralPath) shape).curveTo(338.96103, 212.90701, 339.02002, 213.114,
                339.13904, 213.212);
        ((GeneralPath) shape).curveTo(339.25604, 213.308, 339.51505, 213.35701,
                339.91605, 213.35701);
        ((GeneralPath) shape).curveTo(340.33203, 213.35701, 340.61005, 213.294,
                340.75504, 213.169);
        ((GeneralPath) shape).curveTo(340.89804, 213.044, 340.96902, 212.8,
                340.96902, 212.43501);
        ((GeneralPath) shape).lineTo(340.96902, 211.68501);
        ((GeneralPath) shape).lineTo(340.95804, 211.68102);
        ((GeneralPath) shape).curveTo(340.81705, 212.02701, 340.41205,
                212.20102, 339.74103, 212.20102);
        ((GeneralPath) shape).curveTo(339.19104, 212.20102, 338.81104,
                212.06602, 338.60202, 211.79301);
        ((GeneralPath) shape).curveTo(338.39603, 211.52202, 338.29, 211.02501,
                338.29, 210.30301);
        ((GeneralPath) shape).curveTo(338.29, 209.557, 338.39502, 209.04501,
                338.607, 208.77);
        ((GeneralPath) shape).curveTo(338.818, 208.49701, 339.211, 208.358,
                339.787, 208.358);
        ((GeneralPath) shape).curveTo(340.396, 208.358, 340.797, 208.546,
                340.983, 208.921);
        ((GeneralPath) shape).lineTo(340.995, 208.917);
        ((GeneralPath) shape).lineTo(340.968, 208.414);
        ((GeneralPath) shape).lineTo(341.517, 208.414);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(343.016, 208.411);
        ((GeneralPath) shape).lineTo(343.016, 212.14499);
        ((GeneralPath) shape).lineTo(342.47, 212.14499);
        ((GeneralPath) shape).lineTo(342.47, 208.411);
        ((GeneralPath) shape).lineTo(343.016, 208.411);
        ((GeneralPath) shape).moveTo(343.016, 206.813);
        ((GeneralPath) shape).lineTo(343.016, 207.427);
        ((GeneralPath) shape).lineTo(342.47, 207.427);
        ((GeneralPath) shape).lineTo(342.47, 206.813);
        ((GeneralPath) shape).lineTo(343.016, 206.813);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(343.972, 208.411);
        ((GeneralPath) shape).lineTo(344.498, 208.411);
        ((GeneralPath) shape).lineTo(344.482, 208.91899);
        ((GeneralPath) shape).lineTo(344.498, 208.93098);
        ((GeneralPath) shape).curveTo(344.66397, 208.54799, 345.077, 208.35698,
                345.73798, 208.35698);
        ((GeneralPath) shape).curveTo(346.27, 208.35698, 346.63098, 208.45097,
                346.81998, 208.63799);
        ((GeneralPath) shape).curveTo(347.007, 208.82599, 347.102, 209.18498,
                347.102, 209.71599);
        ((GeneralPath) shape).lineTo(347.102, 212.14598);
        ((GeneralPath) shape).lineTo(346.556, 212.14598);
        ((GeneralPath) shape).lineTo(346.556, 209.62299);
        ((GeneralPath) shape).curveTo(346.556, 209.30298, 346.495, 209.08798,
                346.373, 208.97598);
        ((GeneralPath) shape).curveTo(346.25198, 208.86699, 346.016, 208.80998,
                345.667, 208.80998);
        ((GeneralPath) shape).curveTo(344.9, 208.80998, 344.517, 209.17299,
                344.517, 209.89899);
        ((GeneralPath) shape).lineTo(344.517, 212.14598);
        ((GeneralPath) shape).lineTo(343.971, 212.14598);
        ((GeneralPath) shape).lineTo(343.971, 208.411);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(350.686, 209.954);
        ((GeneralPath) shape).lineTo(350.682, 209.77899);
        ((GeneralPath) shape).curveTo(350.682, 209.377, 350.616, 209.11299,
                350.483, 208.991);
        ((GeneralPath) shape).curveTo(350.35, 208.87, 350.066, 208.81, 349.626,
                208.81);
        ((GeneralPath) shape).curveTo(349.186, 208.81, 348.898, 208.88,
                348.767, 209.022);
        ((GeneralPath) shape).curveTo(348.636, 209.16301, 348.57, 209.474,
                348.57, 209.95401);
        ((GeneralPath) shape).lineTo(350.686, 209.95401);
        ((GeneralPath) shape).moveTo(350.686, 211.017);
        ((GeneralPath) shape).lineTo(351.24402, 211.017);
        ((GeneralPath) shape).lineTo(351.24802, 211.15399);
        ((GeneralPath) shape).curveTo(351.24802, 211.54, 351.131, 211.812,
                350.894, 211.96799);
        ((GeneralPath) shape).curveTo(350.661, 212.12299, 350.24802, 212.20099,
                349.657, 212.20099);
        ((GeneralPath) shape).curveTo(348.97, 212.20099, 348.52002, 212.07599,
                348.30502, 211.82399);
        ((GeneralPath) shape).curveTo(348.09103, 211.57399, 347.984, 211.04498,
                347.984, 210.23999);
        ((GeneralPath) shape).curveTo(347.984, 209.49599, 348.091, 208.99599,
                348.307, 208.73999);
        ((GeneralPath) shape).curveTo(348.522, 208.486, 348.945, 208.357,
                349.575, 208.357);
        ((GeneralPath) shape).curveTo(350.26202, 208.357, 350.71002, 208.46599,
                350.924, 208.689);
        ((GeneralPath) shape).curveTo(351.13702, 208.90999, 351.24402, 209.377,
                351.24402, 210.088);
        ((GeneralPath) shape).lineTo(351.24402, 210.381);
        ((GeneralPath) shape).lineTo(348.561, 210.381);
        ((GeneralPath) shape).curveTo(348.561, 210.968, 348.624, 211.343,
                348.75, 211.506);
        ((GeneralPath) shape).curveTo(348.874, 211.666, 349.169, 211.748,
                349.633, 211.748);
        ((GeneralPath) shape).curveTo(350.07098, 211.748, 350.356, 211.71,
                350.488, 211.632);
        ((GeneralPath) shape).curveTo(350.61902, 211.556, 350.685, 211.39,
                350.685, 211.134);
        ((GeneralPath) shape).lineTo(350.685, 211.017);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(358.438, 206.813);
        ((GeneralPath) shape).lineTo(358.438, 212.146);
        ((GeneralPath) shape).lineTo(357.837, 212.146);
        ((GeneralPath) shape).lineTo(357.837, 209.673);
        ((GeneralPath) shape).lineTo(354.726, 209.673);
        ((GeneralPath) shape).lineTo(354.726, 212.146);
        ((GeneralPath) shape).lineTo(354.125, 212.146);
        ((GeneralPath) shape).lineTo(354.125, 206.813);
        ((GeneralPath) shape).lineTo(354.726, 206.813);
        ((GeneralPath) shape).lineTo(354.726, 209.165);
        ((GeneralPath) shape).lineTo(357.837, 209.165);
        ((GeneralPath) shape).lineTo(357.837, 206.813);
        ((GeneralPath) shape).lineTo(358.438, 206.813);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(359.971, 208.411);
        ((GeneralPath) shape).lineTo(359.971, 212.14499);
        ((GeneralPath) shape).lineTo(359.42502, 212.14499);
        ((GeneralPath) shape).lineTo(359.42502, 208.411);
        ((GeneralPath) shape).lineTo(359.971, 208.411);
        ((GeneralPath) shape).moveTo(359.971, 206.813);
        ((GeneralPath) shape).lineTo(359.971, 207.427);
        ((GeneralPath) shape).lineTo(359.42502, 207.427);
        ((GeneralPath) shape).lineTo(359.42502, 206.813);
        ((GeneralPath) shape).lineTo(359.971, 206.813);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(362.903, 208.411);
        ((GeneralPath) shape).lineTo(362.903, 208.864);
        ((GeneralPath) shape).lineTo(361.467, 208.864);
        ((GeneralPath) shape).lineTo(361.467, 211.149);
        ((GeneralPath) shape).curveTo(361.467, 211.548, 361.643, 211.74701,
                361.997, 211.74701);
        ((GeneralPath) shape).curveTo(362.349, 211.74701, 362.52402, 211.56902,
                362.52402, 211.212);
        ((GeneralPath) shape).lineTo(362.527, 211.028);
        ((GeneralPath) shape).lineTo(362.535, 210.821);
        ((GeneralPath) shape).lineTo(363.042, 210.821);
        ((GeneralPath) shape).lineTo(363.047, 211.09799);
        ((GeneralPath) shape).curveTo(363.047, 211.83199, 362.699, 212.2,
                362.001, 212.2);
        ((GeneralPath) shape).curveTo(361.282, 212.2, 360.92102, 211.895,
                360.92102, 211.282);
        ((GeneralPath) shape).lineTo(360.92102, 208.864);
        ((GeneralPath) shape).lineTo(360.407, 208.864);
        ((GeneralPath) shape).lineTo(360.407, 208.411);
        ((GeneralPath) shape).lineTo(360.92102, 208.411);
        ((GeneralPath) shape).lineTo(360.92102, 207.513);
        ((GeneralPath) shape).lineTo(361.467, 207.513);
        ((GeneralPath) shape).lineTo(361.467, 208.411);
        ((GeneralPath) shape).lineTo(362.903, 208.411);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(307.051, 241.219);
        ((GeneralPath) shape).lineTo(306.455, 241.219);
        ((GeneralPath) shape).curveTo(306.455, 240.789, 306.38498, 240.51799,
                306.24, 240.401);
        ((GeneralPath) shape).curveTo(306.097, 240.286, 305.761, 240.227,
                305.22998, 240.227);
        ((GeneralPath) shape).curveTo(304.602, 240.227, 304.197, 240.282,
                304.01398, 240.393);
        ((GeneralPath) shape).curveTo(303.83197, 240.502, 303.74097, 240.75,
                303.74097, 241.13301);
        ((GeneralPath) shape).curveTo(303.74097, 241.563, 303.81097, 241.824,
                303.95596, 241.91801);
        ((GeneralPath) shape).curveTo(304.09796, 242.01201, 304.52295,
                242.07402, 305.23096, 242.10901);
        ((GeneralPath) shape).curveTo(306.05896, 242.145, 306.58496, 242.25002,
                306.81094, 242.42601);
        ((GeneralPath) shape).curveTo(307.03595, 242.6, 307.14896, 242.99,
                307.14896, 243.598);
        ((GeneralPath) shape).curveTo(307.14896, 244.25401, 307.01996,
                244.67801, 306.76096, 244.871);
        ((GeneralPath) shape).curveTo(306.50397, 245.062, 305.93195, 245.16,
                305.04697, 245.16);
        ((GeneralPath) shape).curveTo(304.28098, 245.16, 303.76996, 245.06401,
                303.51697, 244.869);
        ((GeneralPath) shape).curveTo(303.26398, 244.67601, 303.13696, 244.285,
                303.13696, 243.695);
        ((GeneralPath) shape).lineTo(303.13297, 243.457);
        ((GeneralPath) shape).lineTo(303.72995, 243.457);
        ((GeneralPath) shape).lineTo(303.72995, 243.59);
        ((GeneralPath) shape).curveTo(303.72995, 244.067, 303.80194, 244.361,
                303.94894, 244.479);
        ((GeneralPath) shape).curveTo(304.09296, 244.59401, 304.46695, 244.653,
                305.06793, 244.653);
        ((GeneralPath) shape).curveTo(305.75592, 244.653, 306.17892, 244.596,
                306.33893, 244.479);
        ((GeneralPath) shape).curveTo(306.49692, 244.364, 306.57593, 244.05501,
                306.57593, 243.55101);
        ((GeneralPath) shape).curveTo(306.57593, 243.227, 306.52292, 243.01001,
                306.41492, 242.90102);
        ((GeneralPath) shape).curveTo(306.30792, 242.79402, 306.0809,
                242.72902, 305.7369, 242.70802);
        ((GeneralPath) shape).lineTo(305.11392, 242.67702);
        ((GeneralPath) shape).lineTo(304.52094, 242.64601);
        ((GeneralPath) shape).curveTo(303.62094, 242.58301, 303.16794, 242.115,
                303.16794, 241.24);
        ((GeneralPath) shape).curveTo(303.16794, 240.63501, 303.29794,
                240.22801, 303.56195, 240.02501);
        ((GeneralPath) shape).curveTo(303.82294, 239.822, 304.34796, 239.72002,
                305.13297, 239.72002);
        ((GeneralPath) shape).curveTo(305.92798, 239.72002, 306.44595,
                239.81401, 306.68896, 240.00401);
        ((GeneralPath) shape).curveTo(306.93, 240.19, 307.051, 240.596,
                307.051, 241.219);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(309.822, 241.372);
        ((GeneralPath) shape).lineTo(309.822, 241.825);
        ((GeneralPath) shape).lineTo(308.386, 241.825);
        ((GeneralPath) shape).lineTo(308.386, 244.11);
        ((GeneralPath) shape).curveTo(308.386, 244.509, 308.56198, 244.70801,
                308.916, 244.70801);
        ((GeneralPath) shape).curveTo(309.267, 244.70801, 309.442, 244.53001,
                309.442, 244.173);
        ((GeneralPath) shape).lineTo(309.44598, 243.989);
        ((GeneralPath) shape).lineTo(309.45398, 243.782);
        ((GeneralPath) shape).lineTo(309.96097, 243.782);
        ((GeneralPath) shape).lineTo(309.96497, 244.05899);
        ((GeneralPath) shape).curveTo(309.96497, 244.79298, 309.61798, 245.161,
                308.91995, 245.161);
        ((GeneralPath) shape).curveTo(308.19995, 245.161, 307.83997, 244.856,
                307.83997, 244.243);
        ((GeneralPath) shape).lineTo(307.83997, 241.825);
        ((GeneralPath) shape).lineTo(307.32495, 241.825);
        ((GeneralPath) shape).lineTo(307.32495, 241.372);
        ((GeneralPath) shape).lineTo(307.83997, 241.372);
        ((GeneralPath) shape).lineTo(307.83997, 240.474);
        ((GeneralPath) shape).lineTo(308.38596, 240.474);
        ((GeneralPath) shape).lineTo(308.38596, 241.372);
        ((GeneralPath) shape).lineTo(309.822, 241.372);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(311.894, 243.258);
        ((GeneralPath) shape).curveTo(311.47702, 243.258, 311.204, 243.301,
                311.078, 243.39099);
        ((GeneralPath) shape).curveTo(310.953, 243.47899, 310.889, 243.66798,
                310.889, 243.961);
        ((GeneralPath) shape).curveTo(310.889, 244.262, 310.952, 244.463,
                311.077, 244.561);
        ((GeneralPath) shape).curveTo(311.202, 244.65901, 311.455, 244.707,
                311.83798, 244.707);
        ((GeneralPath) shape).curveTo(312.606, 244.707, 312.991, 244.473,
                312.991, 244.004);
        ((GeneralPath) shape).curveTo(312.991, 243.711, 312.917, 243.512,
                312.767, 243.41);
        ((GeneralPath) shape).curveTo(312.617, 243.309, 312.326, 243.258,
                311.894, 243.258);
        ((GeneralPath) shape).moveTo(311.017, 242.418);
        ((GeneralPath) shape).lineTo(310.475, 242.418);
        ((GeneralPath) shape).curveTo(310.475, 241.984, 310.573, 241.691,
                310.77002, 241.541);
        ((GeneralPath) shape).curveTo(310.96402, 241.393, 311.34802, 241.316,
                311.92102, 241.316);
        ((GeneralPath) shape).curveTo(312.54102, 241.316, 312.962, 241.40799,
                313.18103, 241.59099);
        ((GeneralPath) shape).curveTo(313.39902, 241.775, 313.50803, 242.124,
                313.50803, 242.63998);
        ((GeneralPath) shape).lineTo(313.50803, 245.10498);
        ((GeneralPath) shape).lineTo(312.96204, 245.10498);
        ((GeneralPath) shape).lineTo(313.00504, 244.70198);
        ((GeneralPath) shape).lineTo(312.99304, 244.69897);
        ((GeneralPath) shape).curveTo(312.78604, 245.00598, 312.36304,
                245.15997, 311.72604, 245.15997);
        ((GeneralPath) shape).curveTo(310.78705, 245.15997, 310.31406,
                244.78098, 310.31406, 244.02298);
        ((GeneralPath) shape).curveTo(310.31406, 243.57397, 310.41907,
                243.26198, 310.63007, 243.08998);
        ((GeneralPath) shape).curveTo(310.84106, 242.91798, 311.22107,
                242.83199, 311.77206, 242.83199);
        ((GeneralPath) shape).curveTo(312.42706, 242.83199, 312.82007,
                242.96098, 312.95007, 243.21799);
        ((GeneralPath) shape).lineTo(312.96207, 243.21498);
        ((GeneralPath) shape).lineTo(312.96207, 242.76198);
        ((GeneralPath) shape).curveTo(312.96207, 242.33598, 312.90405,
                242.05898, 312.78607, 241.93198);
        ((GeneralPath) shape).curveTo(312.67007, 241.80698, 312.41406,
                241.74298, 312.01807, 241.74298);
        ((GeneralPath) shape).curveTo(311.34708, 241.74298, 311.01205,
                241.92998, 311.01205, 242.30898);
        ((GeneralPath) shape).curveTo(311.013, 242.327, 311.013, 242.364,
                311.017, 242.418);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(316.16, 241.77);
        ((GeneralPath) shape).curveTo(315.73102, 241.77, 315.444, 241.862,
                315.299, 242.045);
        ((GeneralPath) shape).curveTo(315.15402, 242.229, 315.08002, 242.593,
                315.08002, 243.14099);
        ((GeneralPath) shape).curveTo(315.08002, 243.81499, 315.144, 244.245,
                315.277, 244.43);
        ((GeneralPath) shape).curveTo(315.407, 244.614, 315.709, 244.708,
                316.183, 244.708);
        ((GeneralPath) shape).curveTo(316.582, 244.708, 316.84702, 244.614,
                316.976, 244.42499);
        ((GeneralPath) shape).curveTo(317.105, 244.23698, 317.169, 243.84898,
                317.169, 243.26299);
        ((GeneralPath) shape).curveTo(317.169, 242.64598, 317.10602, 242.24298,
                316.978, 242.05399);
        ((GeneralPath) shape).curveTo(316.852, 241.866, 316.579, 241.77,
                316.16, 241.77);
        ((GeneralPath) shape).moveTo(314.519, 245.106);
        ((GeneralPath) shape).lineTo(314.519, 239.77301);
        ((GeneralPath) shape).lineTo(315.065, 239.77301);
        ((GeneralPath) shape).lineTo(315.065, 241.848);
        ((GeneralPath) shape).lineTo(315.077, 241.85901);
        ((GeneralPath) shape).curveTo(315.202, 241.49802, 315.609, 241.317,
                316.301, 241.317);
        ((GeneralPath) shape).curveTo(316.847, 241.317, 317.224, 241.45801,
                317.432, 241.743);
        ((GeneralPath) shape).curveTo(317.639, 242.026, 317.745, 242.539,
                317.745, 243.286);
        ((GeneralPath) shape).curveTo(317.745, 243.991, 317.636, 244.47899,
                317.415, 244.75299);
        ((GeneralPath) shape).curveTo(317.19702, 245.02399, 316.80002, 245.161,
                316.225, 245.161);
        ((GeneralPath) shape).curveTo(315.64, 245.161, 315.25, 244.989,
                315.055, 244.64099);
        ((GeneralPath) shape).lineTo(315.039, 244.64499);
        ((GeneralPath) shape).lineTo(315.066, 245.10599);
        ((GeneralPath) shape).lineTo(314.519, 245.10599);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(319.144, 241.372);
        ((GeneralPath) shape).lineTo(319.144, 245.10599);
        ((GeneralPath) shape).lineTo(318.59802, 245.10599);
        ((GeneralPath) shape).lineTo(318.59802, 241.372);
        ((GeneralPath) shape).lineTo(319.144, 241.372);
        ((GeneralPath) shape).moveTo(319.144, 239.773);
        ((GeneralPath) shape).lineTo(319.144, 240.387);
        ((GeneralPath) shape).lineTo(318.59802, 240.387);
        ((GeneralPath) shape).lineTo(318.59802, 239.773);
        ((GeneralPath) shape).lineTo(319.144, 239.773);
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
        paint = new Color(35, 31, 32, 255);
        shape = new Rectangle2D.Double(320.0989990234375, 239.7729949951172,
                0.5460000038146973, 5.333000183105469);
        g.setPaint(paint);
        g.fill(shape);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(322.145, 241.372);
        ((GeneralPath) shape).lineTo(322.145, 245.10599);
        ((GeneralPath) shape).lineTo(321.599, 245.10599);
        ((GeneralPath) shape).lineTo(321.599, 241.372);
        ((GeneralPath) shape).lineTo(322.145, 241.372);
        ((GeneralPath) shape).moveTo(322.145, 239.773);
        ((GeneralPath) shape).lineTo(322.145, 240.387);
        ((GeneralPath) shape).lineTo(321.599, 240.387);
        ((GeneralPath) shape).lineTo(321.599, 239.773);
        ((GeneralPath) shape).lineTo(322.145, 239.773);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(322.979, 241.372);
        ((GeneralPath) shape).lineTo(325.664, 241.372);
        ((GeneralPath) shape).lineTo(325.664, 241.903);
        ((GeneralPath) shape).lineTo(323.441, 244.653);
        ((GeneralPath) shape).lineTo(325.664, 244.653);
        ((GeneralPath) shape).lineTo(325.664, 245.106);
        ((GeneralPath) shape).lineTo(322.791, 245.106);
        ((GeneralPath) shape).lineTo(322.791, 244.586);
        ((GeneralPath) shape).lineTo(325.021, 241.825);
        ((GeneralPath) shape).lineTo(322.979, 241.825);
        ((GeneralPath) shape).lineTo(322.979, 241.372);
        g.setPaint(paint);
        g.fill(shape);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(328.908, 242.914);
        ((GeneralPath) shape).lineTo(328.904, 242.739);
        ((GeneralPath) shape).curveTo(328.904, 242.337, 328.839, 242.073,
                328.706, 241.952);
        ((GeneralPath) shape).curveTo(328.573, 241.831, 328.289, 241.76999,
                327.848, 241.76999);
        ((GeneralPath) shape).curveTo(327.408, 241.76999, 327.121, 241.84,
                326.98898, 241.98299);
        ((GeneralPath) shape).curveTo(326.85797, 242.124, 326.79297, 242.43399,
                326.79297, 242.91399);
        ((GeneralPath) shape).lineTo(328.908, 242.91399);
        ((GeneralPath) shape).moveTo(328.908, 243.977);
        ((GeneralPath) shape).lineTo(329.46698, 243.977);
        ((GeneralPath) shape).lineTo(329.46997, 244.114);
        ((GeneralPath) shape).curveTo(329.46997, 244.5, 329.35397, 244.772,
                329.11697, 244.928);
        ((GeneralPath) shape).curveTo(328.88397, 245.082, 328.46997, 245.15999,
                327.87897, 245.15999);
        ((GeneralPath) shape).curveTo(327.19296, 245.15999, 326.74298,
                245.03499, 326.52795, 244.78299);
        ((GeneralPath) shape).curveTo(326.31296, 244.53299, 326.20694,
                244.00398, 326.20694, 243.19899);
        ((GeneralPath) shape).curveTo(326.20694, 242.45499, 326.31293,
                241.95499, 326.52994, 241.69899);
        ((GeneralPath) shape).curveTo(326.74493, 241.44499, 327.16794, 241.316,
                327.79694, 241.316);
        ((GeneralPath) shape).curveTo(328.48395, 241.316, 328.93195, 241.42499,
                329.14694, 241.648);
        ((GeneralPath) shape).curveTo(329.35995, 241.86899, 329.46695, 242.336,
                329.46695, 243.047);
        ((GeneralPath) shape).lineTo(329.46695, 243.34);
        ((GeneralPath) shape).lineTo(326.78296, 243.34);
        ((GeneralPath) shape).curveTo(326.78296, 243.928, 326.84595, 244.303,
                326.97195, 244.465);
        ((GeneralPath) shape).curveTo(327.09695, 244.625, 327.39096, 244.707,
                327.85495, 244.707);
        ((GeneralPath) shape).curveTo(328.29395, 244.707, 328.57895, 244.67,
                328.71094, 244.592);
        ((GeneralPath) shape).curveTo(328.84195, 244.51599, 328.90695,
                244.34999, 328.90695, 244.093);
        ((GeneralPath) shape).lineTo(328.90695, 243.977);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(330.379, 241.372);
        ((GeneralPath) shape).lineTo(330.924, 241.372);
        ((GeneralPath) shape).lineTo(330.87003, 241.80199);
        ((GeneralPath) shape).lineTo(330.881, 241.81398);
        ((GeneralPath) shape).curveTo(331.096, 241.46198, 331.45203, 241.28699,
                331.949, 241.28699);
        ((GeneralPath) shape).curveTo(332.63602, 241.28699, 332.978, 241.64099,
                332.978, 242.34999);
        ((GeneralPath) shape).lineTo(332.974, 242.60799);
        ((GeneralPath) shape).lineTo(332.436, 242.60799);
        ((GeneralPath) shape).lineTo(332.448, 242.51399);
        ((GeneralPath) shape).curveTo(332.456, 242.41599, 332.461, 242.34999,
                332.461, 242.31499);
        ((GeneralPath) shape).curveTo(332.461, 241.93199, 332.254, 241.74098,
                331.837, 241.74098);
        ((GeneralPath) shape).curveTo(331.229, 241.74098, 330.924, 242.11598,
                330.924, 242.86998);
        ((GeneralPath) shape).lineTo(330.924, 245.10799);
        ((GeneralPath) shape).lineTo(330.379, 245.10799);
        ((GeneralPath) shape).lineTo(330.379, 241.372);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(336.39, 242.348);
        ((GeneralPath) shape).lineTo(335.84402, 242.348);
        ((GeneralPath) shape).curveTo(335.84402, 242.08801, 335.79102,
                241.92601, 335.687, 241.86401);
        ((GeneralPath) shape).curveTo(335.582, 241.80101, 335.31, 241.77002,
                334.87402, 241.77002);
        ((GeneralPath) shape).curveTo(334.46902, 241.77002, 334.21204,
                241.80302, 334.10303, 241.87003);
        ((GeneralPath) shape).curveTo(333.99402, 241.93703, 333.93903,
                242.09402, 333.93903, 242.34903);
        ((GeneralPath) shape).curveTo(333.93903, 242.73203, 334.12302,
                242.93103, 334.489, 242.95103);
        ((GeneralPath) shape).lineTo(334.92902, 242.97403);
        ((GeneralPath) shape).lineTo(335.48703, 243.00102);
        ((GeneralPath) shape).curveTo(336.16202, 243.03502, 336.50104,
                243.38802, 336.50104, 244.06403);
        ((GeneralPath) shape).curveTo(336.50104, 244.48203, 336.39005,
                244.77103, 336.16605, 244.92703);
        ((GeneralPath) shape).curveTo(335.94305, 245.08304, 335.53406,
                245.16103, 334.93704, 245.16103);
        ((GeneralPath) shape).curveTo(334.32706, 245.16103, 333.90604,
                245.08702, 333.67606, 244.93802);
        ((GeneralPath) shape).curveTo(333.44604, 244.78902, 333.33005,
                244.51802, 333.33005, 244.12202);
        ((GeneralPath) shape).lineTo(333.33405, 243.91902);
        ((GeneralPath) shape).lineTo(333.89905, 243.91902);
        ((GeneralPath) shape).lineTo(333.90305, 244.09402);
        ((GeneralPath) shape).curveTo(333.90305, 244.33902, 333.96603,
                244.50302, 334.09103, 244.58502);
        ((GeneralPath) shape).curveTo(334.21603, 244.66702, 334.46103,
                244.70802, 334.82703, 244.70802);
        ((GeneralPath) shape).curveTo(335.27502, 244.70802, 335.57202,
                244.66502, 335.71503, 244.57903);
        ((GeneralPath) shape).curveTo(335.85504, 244.49402, 335.92703,
                244.31403, 335.92703, 244.04002);
        ((GeneralPath) shape).curveTo(335.92703, 243.64803, 335.75003,
                243.45102, 335.39304, 243.45102);
        ((GeneralPath) shape).curveTo(334.56503, 243.45102, 334.01904,
                243.38101, 333.75705, 243.24002);
        ((GeneralPath) shape).curveTo(333.49606, 243.09901, 333.36505,
                242.80801, 333.36505, 242.36502);
        ((GeneralPath) shape).curveTo(333.36505, 241.94702, 333.46906,
                241.66801, 333.67606, 241.52702);
        ((GeneralPath) shape).curveTo(333.88205, 241.38802, 334.29306,
                241.31802, 334.90906, 241.31802);
        ((GeneralPath) shape).curveTo(335.89505, 241.31802, 336.39005,
                241.61502, 336.39005, 242.21303);
        ((GeneralPath) shape).lineTo(336.39005, 242.348);
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
        paint = new Color(35, 31, 32, 255);
        stroke = new BasicStroke(1.0f, 0, 0, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(295.806, 255.891);
        ((GeneralPath) shape).curveTo(295.806, 256.641, 295.195, 257.251,
                294.443, 257.251);
        ((GeneralPath) shape).lineTo(287.52298, 257.251);
        ((GeneralPath) shape).curveTo(286.77097, 257.251, 286.16098, 256.64102,
                286.16098, 255.891);
        ((GeneralPath) shape).lineTo(286.16098, 248.89801);
        ((GeneralPath) shape).curveTo(286.16098, 248.147, 286.77, 247.53801,
                287.52298, 247.53801);
        ((GeneralPath) shape).lineTo(294.443, 247.53801);
        ((GeneralPath) shape).curveTo(295.195, 247.53801, 295.806, 248.14601,
                295.806, 248.89801);
        ((GeneralPath) shape).lineTo(295.806, 255.891);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(258.643, 249.903);
        ((GeneralPath) shape).lineTo(258.643, 251.766);
        ((GeneralPath) shape).lineTo(261.236, 251.766);
        ((GeneralPath) shape).lineTo(261.236, 252.274);
        ((GeneralPath) shape).lineTo(258.643, 252.274);
        ((GeneralPath) shape).lineTo(258.643, 254.727);
        ((GeneralPath) shape).lineTo(258.043, 254.727);
        ((GeneralPath) shape).lineTo(258.043, 249.395);
        ((GeneralPath) shape).lineTo(261.306, 249.395);
        ((GeneralPath) shape).lineTo(261.306, 249.903);
        ((GeneralPath) shape).lineTo(258.643, 249.903);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(261.976, 250.993);
        ((GeneralPath) shape).lineTo(262.522, 250.993);
        ((GeneralPath) shape).lineTo(262.467, 251.42299);
        ((GeneralPath) shape).lineTo(262.479, 251.43498);
        ((GeneralPath) shape).curveTo(262.693, 251.08298, 263.05002, 250.90698,
                263.547, 250.90698);
        ((GeneralPath) shape).curveTo(264.233, 250.90698, 264.576, 251.26099,
                264.576, 251.96999);
        ((GeneralPath) shape).lineTo(264.572, 252.22798);
        ((GeneralPath) shape).lineTo(264.034, 252.22798);
        ((GeneralPath) shape).lineTo(264.046, 252.13399);
        ((GeneralPath) shape).curveTo(264.054, 252.03598, 264.05798, 251.96999,
                264.05798, 251.93498);
        ((GeneralPath) shape).curveTo(264.05798, 251.55199, 263.85098,
                251.35999, 263.434, 251.35999);
        ((GeneralPath) shape).curveTo(262.826, 251.35999, 262.521, 251.73499,
                262.521, 252.48898);
        ((GeneralPath) shape).lineTo(262.521, 254.72699);
        ((GeneralPath) shape).lineTo(261.975, 254.72699);
        ((GeneralPath) shape).lineTo(261.975, 250.993);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(266.64, 251.391);
        ((GeneralPath) shape).curveTo(266.157, 251.391, 265.858, 251.46901,
                265.742, 251.627);
        ((GeneralPath) shape).curveTo(265.627, 251.783, 265.568, 252.19499,
                265.568, 252.859);
        ((GeneralPath) shape).curveTo(265.568, 253.523, 265.625, 253.93399,
                265.742, 254.092);
        ((GeneralPath) shape).curveTo(265.857, 254.248, 266.157, 254.32799,
                266.64, 254.32799);
        ((GeneralPath) shape).curveTo(267.125, 254.32799, 267.42603, 254.24998,
                267.54303, 254.092);
        ((GeneralPath) shape).curveTo(267.65802, 253.93599, 267.71603, 253.523,
                267.71603, 252.859);
        ((GeneralPath) shape).curveTo(267.71603, 252.19499, 267.65903,
                251.78499, 267.54303, 251.627);
        ((GeneralPath) shape).curveTo(267.427, 251.471, 267.127, 251.391,
                266.64, 251.391);
        ((GeneralPath) shape).moveTo(266.64, 250.938);
        ((GeneralPath) shape).curveTo(267.328, 250.938, 267.77502, 251.057,
                267.98102, 251.297);
        ((GeneralPath) shape).curveTo(268.186, 251.535, 268.28903, 252.05699,
                268.28903, 252.86);
        ((GeneralPath) shape).curveTo(268.28903, 253.661, 268.18704, 254.182,
                267.98102, 254.423);
        ((GeneralPath) shape).curveTo(267.77603, 254.66101, 267.33002, 254.782,
                266.64, 254.782);
        ((GeneralPath) shape).curveTo(265.954, 254.782, 265.509, 254.663,
                265.303, 254.423);
        ((GeneralPath) shape).curveTo(265.09802, 254.185, 264.995, 253.664,
                264.995, 252.86);
        ((GeneralPath) shape).curveTo(264.995, 252.06, 265.097, 251.538,
                265.303, 251.297);
        ((GeneralPath) shape).curveTo(265.507, 251.06, 265.954, 250.938,
                266.64, 250.938);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(269.141, 250.993);
        ((GeneralPath) shape).lineTo(269.667, 250.993);
        ((GeneralPath) shape).lineTo(269.651, 251.50099);
        ((GeneralPath) shape).lineTo(269.667, 251.51299);
        ((GeneralPath) shape).curveTo(269.83298, 251.12999, 270.246, 250.93799,
                270.90698, 250.93799);
        ((GeneralPath) shape).curveTo(271.439, 250.93799, 271.8, 251.03198,
                271.98898, 251.219);
        ((GeneralPath) shape).curveTo(272.176, 251.407, 272.27197, 251.76599,
                272.27197, 252.297);
        ((GeneralPath) shape).lineTo(272.27197, 254.72699);
        ((GeneralPath) shape).lineTo(271.72598, 254.72699);
        ((GeneralPath) shape).lineTo(271.72598, 252.204);
        ((GeneralPath) shape).curveTo(271.72598, 251.88399, 271.66498,
                251.66899, 271.54297, 251.558);
        ((GeneralPath) shape).curveTo(271.42197, 251.449, 271.18597, 251.392,
                270.83698, 251.392);
        ((GeneralPath) shape).curveTo(270.07098, 251.392, 269.68698, 251.756,
                269.68698, 252.482);
        ((GeneralPath) shape).lineTo(269.68698, 254.728);
        ((GeneralPath) shape).lineTo(269.141, 254.728);
        ((GeneralPath) shape).lineTo(269.141, 250.993);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(275.238, 250.993);
        ((GeneralPath) shape).lineTo(275.238, 251.446);
        ((GeneralPath) shape).lineTo(273.803, 251.446);
        ((GeneralPath) shape).lineTo(273.803, 253.731);
        ((GeneralPath) shape).curveTo(273.803, 254.13, 273.978, 254.32901,
                274.333, 254.32901);
        ((GeneralPath) shape).curveTo(274.68402, 254.32901, 274.86002,
                254.15102, 274.86002, 253.794);
        ((GeneralPath) shape).lineTo(274.863, 253.61);
        ((GeneralPath) shape).lineTo(274.871, 253.403);
        ((GeneralPath) shape).lineTo(275.378, 253.403);
        ((GeneralPath) shape).lineTo(275.382, 253.68);
        ((GeneralPath) shape).curveTo(275.382, 254.41399, 275.035, 254.782,
                274.33698, 254.782);
        ((GeneralPath) shape).curveTo(273.61798, 254.782, 273.257, 254.477,
                273.257, 253.86499);
        ((GeneralPath) shape).lineTo(273.257, 251.44699);
        ((GeneralPath) shape).lineTo(272.74197, 251.44699);
        ((GeneralPath) shape).lineTo(272.74197, 250.99399);
        ((GeneralPath) shape).lineTo(273.257, 250.99399);
        ((GeneralPath) shape).lineTo(273.257, 250.096);
        ((GeneralPath) shape).lineTo(273.802, 250.096);
        ((GeneralPath) shape).lineTo(273.802, 250.99399);
        ((GeneralPath) shape).lineTo(275.238, 250.99399);
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
        paint = new Color(35, 31, 32, 255);
        stroke = new BasicStroke(1.0f, 0, 0, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(344.797, 255.891);
        ((GeneralPath) shape).curveTo(344.797, 256.642, 344.189, 257.252,
                343.435, 257.252);
        ((GeneralPath) shape).lineTo(336.51498, 257.252);
        ((GeneralPath) shape).curveTo(335.762, 257.252, 335.15298, 256.643,
                335.15298, 255.89102);
        ((GeneralPath) shape).lineTo(335.15298, 248.89603);
        ((GeneralPath) shape).curveTo(335.15298, 248.14603, 335.762, 247.53703,
                336.51498, 247.53703);
        ((GeneralPath) shape).lineTo(343.435, 247.53703);
        ((GeneralPath) shape).curveTo(344.189, 247.53703, 344.797, 248.14603,
                344.797, 248.89603);
        ((GeneralPath) shape).lineTo(344.797, 255.891);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(311.085, 249.395);
        ((GeneralPath) shape).lineTo(311.085, 254.169);
        ((GeneralPath) shape).lineTo(313.822, 254.169);
        ((GeneralPath) shape).lineTo(313.822, 254.727);
        ((GeneralPath) shape).lineTo(310.484, 254.727);
        ((GeneralPath) shape).lineTo(310.484, 249.395);
        ((GeneralPath) shape).lineTo(311.085, 249.395);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(316.922, 252.536);
        ((GeneralPath) shape).lineTo(316.918, 252.36);
        ((GeneralPath) shape).curveTo(316.918, 251.95801, 316.852, 251.694,
                316.72, 251.573);
        ((GeneralPath) shape).curveTo(316.587, 251.452, 316.303, 251.39099,
                315.862, 251.39099);
        ((GeneralPath) shape).curveTo(315.422, 251.39099, 315.135, 251.461,
                315.003, 251.60399);
        ((GeneralPath) shape).curveTo(314.87198, 251.745, 314.806, 252.055,
                314.806, 252.536);
        ((GeneralPath) shape).lineTo(316.922, 252.536);
        ((GeneralPath) shape).moveTo(316.922, 253.599);
        ((GeneralPath) shape).lineTo(317.48, 253.599);
        ((GeneralPath) shape).lineTo(317.484, 253.735);
        ((GeneralPath) shape).curveTo(317.484, 254.122, 317.367, 254.393,
                317.131, 254.549);
        ((GeneralPath) shape).curveTo(316.897, 254.703, 316.484, 254.78099,
                315.893, 254.78099);
        ((GeneralPath) shape).curveTo(315.206, 254.78099, 314.756, 254.65599,
                314.542, 254.40498);
        ((GeneralPath) shape).curveTo(314.327, 254.15498, 314.22098, 253.62498,
                314.22098, 252.82098);
        ((GeneralPath) shape).curveTo(314.22098, 252.07698, 314.32697,
                251.57698, 314.54398, 251.32098);
        ((GeneralPath) shape).curveTo(314.75797, 251.06699, 315.18198,
                250.93799, 315.81097, 250.93799);
        ((GeneralPath) shape).curveTo(316.498, 250.93799, 316.94598, 251.04698,
                317.16098, 251.26999);
        ((GeneralPath) shape).curveTo(317.373, 251.49098, 317.47998, 251.958,
                317.47998, 252.66899);
        ((GeneralPath) shape).lineTo(317.47998, 252.961);
        ((GeneralPath) shape).lineTo(314.79697, 252.961);
        ((GeneralPath) shape).curveTo(314.79697, 253.549, 314.85995, 253.924,
                314.98596, 254.086);
        ((GeneralPath) shape).curveTo(315.11096, 254.246, 315.40497, 254.328,
                315.86896, 254.328);
        ((GeneralPath) shape).curveTo(316.30695, 254.328, 316.59296, 254.291,
                316.72495, 254.213);
        ((GeneralPath) shape).curveTo(316.85495, 254.137, 316.92096, 253.971,
                316.92096, 253.715);
        ((GeneralPath) shape).lineTo(316.92096, 253.599);
        g.setPaint(paint);
        g.fill(shape);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(318.48, 254.727);
        ((GeneralPath) shape).lineTo(318.48, 251.446);
        ((GeneralPath) shape).lineTo(317.91202, 251.446);
        ((GeneralPath) shape).lineTo(317.91202, 250.993);
        ((GeneralPath) shape).lineTo(318.48, 250.993);
        ((GeneralPath) shape).lineTo(318.48, 250.438);
        ((GeneralPath) shape).curveTo(318.48, 249.70801, 318.855, 249.34,
                319.60703, 249.34);
        ((GeneralPath) shape).curveTo(319.71802, 249.34, 319.84903, 249.34799,
                320.00104, 249.36699);
        ((GeneralPath) shape).lineTo(320.00104, 249.81999);
        ((GeneralPath) shape).curveTo(319.82605, 249.803, 319.69705, 249.793,
                319.61505, 249.793);
        ((GeneralPath) shape).curveTo(319.22305, 249.793, 319.02606, 249.99,
                319.02606, 250.386);
        ((GeneralPath) shape).lineTo(319.02606, 250.991);
        ((GeneralPath) shape).lineTo(320.00107, 250.991);
        ((GeneralPath) shape).lineTo(320.00107, 251.444);
        ((GeneralPath) shape).lineTo(319.02606, 251.444);
        ((GeneralPath) shape).lineTo(319.02606, 254.725);
        ((GeneralPath) shape).lineTo(318.48, 254.725);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(322.429, 250.993);
        ((GeneralPath) shape).lineTo(322.429, 251.446);
        ((GeneralPath) shape).lineTo(320.994, 251.446);
        ((GeneralPath) shape).lineTo(320.994, 253.731);
        ((GeneralPath) shape).curveTo(320.994, 254.13, 321.16898, 254.32901,
                321.524, 254.32901);
        ((GeneralPath) shape).curveTo(321.875, 254.32901, 322.05, 254.15102,
                322.05, 253.794);
        ((GeneralPath) shape).lineTo(322.054, 253.61);
        ((GeneralPath) shape).lineTo(322.06198, 253.403);
        ((GeneralPath) shape).lineTo(322.56897, 253.403);
        ((GeneralPath) shape).lineTo(322.57297, 253.68);
        ((GeneralPath) shape).curveTo(322.57297, 254.41399, 322.22498, 254.782,
                321.52795, 254.782);
        ((GeneralPath) shape).curveTo(320.80795, 254.782, 320.44797, 254.477,
                320.44797, 253.86499);
        ((GeneralPath) shape).lineTo(320.44797, 251.44699);
        ((GeneralPath) shape).lineTo(319.93295, 251.44699);
        ((GeneralPath) shape).lineTo(319.93295, 250.99399);
        ((GeneralPath) shape).lineTo(320.44797, 250.99399);
        ((GeneralPath) shape).lineTo(320.44797, 250.096);
        ((GeneralPath) shape).lineTo(320.99396, 250.096);
        ((GeneralPath) shape).lineTo(320.99396, 250.99399);
        ((GeneralPath) shape).lineTo(322.429, 250.99399);
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
        paint = new Color(35, 31, 32, 255);
        stroke = new BasicStroke(1.0f, 0, 0, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(386.851, 255.891);
        ((GeneralPath) shape).curveTo(386.851, 256.641, 386.242, 257.251,
                385.488, 257.251);
        ((GeneralPath) shape).lineTo(378.568, 257.251);
        ((GeneralPath) shape).curveTo(377.815, 257.251, 377.206, 256.64102,
                377.206, 255.891);
        ((GeneralPath) shape).lineTo(377.206, 248.89801);
        ((GeneralPath) shape).curveTo(377.206, 248.147, 377.815, 247.53801,
                378.568, 247.53801);
        ((GeneralPath) shape).lineTo(385.488, 247.53801);
        ((GeneralPath) shape).curveTo(386.242, 247.53801, 386.851, 248.14601,
                386.851, 248.89801);
        ((GeneralPath) shape).lineTo(386.851, 255.891);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(353.605, 252.06);
        ((GeneralPath) shape).lineTo(355.33902, 252.06);
        ((GeneralPath) shape).curveTo(355.795, 252.06, 356.10803, 251.992,
                356.27603, 251.851);
        ((GeneralPath) shape).curveTo(356.44104, 251.71199, 356.52502, 251.45,
                356.52502, 251.063);
        ((GeneralPath) shape).curveTo(356.52502, 250.58301, 356.46402, 250.268,
                356.33902, 250.12201);
        ((GeneralPath) shape).curveTo(356.217, 249.977, 355.95102, 249.904,
                355.54602, 249.904);
        ((GeneralPath) shape).lineTo(353.605, 249.904);
        ((GeneralPath) shape).lineTo(353.605, 252.06);
        ((GeneralPath) shape).moveTo(353.005, 254.727);
        ((GeneralPath) shape).lineTo(353.005, 249.395);
        ((GeneralPath) shape).lineTo(355.539, 249.395);
        ((GeneralPath) shape).curveTo(356.11002, 249.395, 356.514, 249.50801,
                356.74802, 249.735);
        ((GeneralPath) shape).curveTo(356.98102, 249.962, 357.09903, 250.354,
                357.09903, 250.915);
        ((GeneralPath) shape).curveTo(357.09903, 251.407, 357.03604, 251.749,
                356.90802, 251.94499);
        ((GeneralPath) shape).curveTo(356.781, 252.13799, 356.53802, 252.26299,
                356.17902, 252.321);
        ((GeneralPath) shape).lineTo(356.17902, 252.333);
        ((GeneralPath) shape).curveTo(356.742, 252.374, 357.02502, 252.71799,
                357.02502, 253.364);
        ((GeneralPath) shape).lineTo(357.02502, 254.727);
        ((GeneralPath) shape).lineTo(356.424, 254.727);
        ((GeneralPath) shape).lineTo(356.424, 253.5);
        ((GeneralPath) shape).curveTo(356.424, 252.879, 356.155, 252.566,
                355.617, 252.566);
        ((GeneralPath) shape).lineTo(353.605, 252.566);
        ((GeneralPath) shape).lineTo(353.605, 254.726);
        ((GeneralPath) shape).lineTo(353.005, 254.726);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(358.497, 250.993);
        ((GeneralPath) shape).lineTo(358.497, 254.72699);
        ((GeneralPath) shape).lineTo(357.95102, 254.72699);
        ((GeneralPath) shape).lineTo(357.95102, 250.993);
        ((GeneralPath) shape).lineTo(358.497, 250.993);
        ((GeneralPath) shape).moveTo(358.497, 249.395);
        ((GeneralPath) shape).lineTo(358.497, 250.00801);
        ((GeneralPath) shape).lineTo(357.95102, 250.00801);
        ((GeneralPath) shape).lineTo(357.95102, 249.395);
        ((GeneralPath) shape).lineTo(358.497, 249.395);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(360.944, 251.391);
        ((GeneralPath) shape).curveTo(360.515, 251.391, 360.238, 251.481,
                360.11, 251.66301);
        ((GeneralPath) shape).curveTo(359.98297, 251.843, 359.91898, 252.23901,
                359.91898, 252.852);
        ((GeneralPath) shape).curveTo(359.91898, 253.46901, 359.98196,
                253.87001, 360.11, 254.054);
        ((GeneralPath) shape).curveTo(360.236, 254.237, 360.51498, 254.329,
                360.944, 254.329);
        ((GeneralPath) shape).curveTo(361.377, 254.329, 361.664, 254.22899,
                361.802, 254.026);
        ((GeneralPath) shape).curveTo(361.938, 253.825, 362.008, 253.405,
                362.008, 252.766);
        ((GeneralPath) shape).curveTo(362.008, 252.203, 361.94, 251.835,
                361.802, 251.65701);
        ((GeneralPath) shape).curveTo(361.666, 251.481, 361.379, 251.391,
                360.944, 251.391);
        ((GeneralPath) shape).moveTo(362.57, 250.993);
        ((GeneralPath) shape).lineTo(362.57, 254.97);
        ((GeneralPath) shape).curveTo(362.57, 255.511, 362.453, 255.884,
                362.219, 256.087);
        ((GeneralPath) shape).curveTo(361.985, 256.29, 361.556, 256.392,
                360.932, 256.392);
        ((GeneralPath) shape).curveTo(360.376, 256.392, 359.996, 256.306,
                359.793, 256.134);
        ((GeneralPath) shape).curveTo(359.591, 255.962, 359.489, 255.642,
                359.489, 255.17);
        ((GeneralPath) shape).lineTo(360.015, 255.17);
        ((GeneralPath) shape).curveTo(360.015, 255.49, 360.074, 255.69699,
                360.19302, 255.795);
        ((GeneralPath) shape).curveTo(360.30902, 255.89099, 360.56903, 255.939,
                360.97003, 255.939);
        ((GeneralPath) shape).curveTo(361.38504, 255.939, 361.66403, 255.87599,
                361.80902, 255.75099);
        ((GeneralPath) shape).curveTo(361.95102, 255.62599, 362.023, 255.38199,
                362.023, 255.017);
        ((GeneralPath) shape).lineTo(362.023, 254.267);
        ((GeneralPath) shape).lineTo(362.01102, 254.26399);
        ((GeneralPath) shape).curveTo(361.87003, 254.60999, 361.46503,
                254.78299, 360.794, 254.78299);
        ((GeneralPath) shape).curveTo(360.245, 254.78299, 359.864, 254.64899,
                359.656, 254.37498);
        ((GeneralPath) shape).curveTo(359.449, 254.10298, 359.34302, 253.60698,
                359.34302, 252.88399);
        ((GeneralPath) shape).curveTo(359.34302, 252.13799, 359.44803,
                251.62599, 359.661, 251.35098);
        ((GeneralPath) shape).curveTo(359.871, 251.07799, 360.265, 250.93799,
                360.84, 250.93799);
        ((GeneralPath) shape).curveTo(361.44998, 250.93799, 361.85098,
                251.12599, 362.037, 251.50099);
        ((GeneralPath) shape).lineTo(362.04898, 251.497);
        ((GeneralPath) shape).lineTo(362.02197, 250.993);
        ((GeneralPath) shape).lineTo(362.57, 250.993);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(364.068, 249.395);
        ((GeneralPath) shape).lineTo(364.068, 251.45401);
        ((GeneralPath) shape).lineTo(364.08, 251.462);
        ((GeneralPath) shape).curveTo(364.21698, 251.115, 364.602, 250.93901,
                365.23398, 250.93901);
        ((GeneralPath) shape).curveTo(366.16998, 250.93901, 366.637, 251.35501,
                366.637, 252.18901);
        ((GeneralPath) shape).lineTo(366.637, 254.72801);
        ((GeneralPath) shape).lineTo(366.091, 254.72801);
        ((GeneralPath) shape).lineTo(366.091, 252.23601);
        ((GeneralPath) shape).curveTo(366.091, 251.673, 365.805, 251.39201,
                365.23, 251.39201);
        ((GeneralPath) shape).curveTo(364.768, 251.39201, 364.458, 251.475,
                364.302, 251.641);
        ((GeneralPath) shape).curveTo(364.146, 251.80501, 364.068, 252.13501,
                364.068, 252.627);
        ((GeneralPath) shape).lineTo(364.068, 254.729);
        ((GeneralPath) shape).lineTo(363.52298, 254.729);
        ((GeneralPath) shape).lineTo(363.52298, 249.397);
        ((GeneralPath) shape).lineTo(364.068, 249.397);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(369.603, 250.993);
        ((GeneralPath) shape).lineTo(369.603, 251.446);
        ((GeneralPath) shape).lineTo(368.168, 251.446);
        ((GeneralPath) shape).lineTo(368.168, 253.731);
        ((GeneralPath) shape).curveTo(368.168, 254.13, 368.344, 254.32901,
                368.698, 254.32901);
        ((GeneralPath) shape).curveTo(369.049, 254.32901, 369.224, 254.15102,
                369.224, 253.794);
        ((GeneralPath) shape).lineTo(369.228, 253.61);
        ((GeneralPath) shape).lineTo(369.236, 253.403);
        ((GeneralPath) shape).lineTo(369.74298, 253.403);
        ((GeneralPath) shape).lineTo(369.74698, 253.68);
        ((GeneralPath) shape).curveTo(369.74698, 254.41399, 369.4, 254.782,
                368.70197, 254.782);
        ((GeneralPath) shape).curveTo(367.98196, 254.782, 367.62198, 254.477,
                367.62198, 253.86499);
        ((GeneralPath) shape).lineTo(367.62198, 251.44699);
        ((GeneralPath) shape).lineTo(367.10696, 251.44699);
        ((GeneralPath) shape).lineTo(367.10696, 250.99399);
        ((GeneralPath) shape).lineTo(367.62198, 250.99399);
        ((GeneralPath) shape).lineTo(367.62198, 250.096);
        ((GeneralPath) shape).lineTo(368.16797, 250.096);
        ((GeneralPath) shape).lineTo(368.16797, 250.99399);
        ((GeneralPath) shape).lineTo(369.603, 250.99399);
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
        paint = new Color(35, 31, 32, 255);
        stroke = new BasicStroke(1.0f, 0, 0, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(344.797, 267.229);
        ((GeneralPath) shape).curveTo(344.797, 267.98, 344.189, 268.59,
                343.436, 268.59);
        ((GeneralPath) shape).lineTo(336.516, 268.59);
        ((GeneralPath) shape).curveTo(335.762, 268.59, 335.15298, 267.981,
                335.15298, 267.229);
        ((GeneralPath) shape).lineTo(335.15298, 260.23502);
        ((GeneralPath) shape).curveTo(335.15298, 259.484, 335.762, 258.876,
                336.516, 258.876);
        ((GeneralPath) shape).lineTo(343.436, 258.876);
        ((GeneralPath) shape).curveTo(344.189, 258.876, 344.797, 259.484,
                344.797, 260.23502);
        ((GeneralPath) shape).lineTo(344.797, 267.229);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(312.808, 261.294);
        ((GeneralPath) shape).lineTo(312.808, 266.067);
        ((GeneralPath) shape).lineTo(312.207, 266.067);
        ((GeneralPath) shape).lineTo(312.207, 261.294);
        ((GeneralPath) shape).lineTo(310.473, 261.294);
        ((GeneralPath) shape).lineTo(310.473, 260.735);
        ((GeneralPath) shape).lineTo(314.526, 260.735);
        ((GeneralPath) shape).lineTo(314.526, 261.294);
        ((GeneralPath) shape).lineTo(312.808, 261.294);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(318.075, 262.333);
        ((GeneralPath) shape).lineTo(318.075, 266.06702);
        ((GeneralPath) shape).lineTo(317.53, 266.06702);
        ((GeneralPath) shape).lineTo(317.568, 265.579);
        ((GeneralPath) shape).lineTo(317.556, 265.56702);
        ((GeneralPath) shape).curveTo(317.368, 265.936, 316.962, 266.122,
                316.34, 266.122);
        ((GeneralPath) shape).curveTo(315.47, 266.122, 315.033, 265.68802,
                315.033, 264.81702);
        ((GeneralPath) shape).lineTo(315.033, 262.333);
        ((GeneralPath) shape).lineTo(315.57898, 262.333);
        ((GeneralPath) shape).lineTo(315.57898, 264.81702);
        ((GeneralPath) shape).curveTo(315.57898, 265.15503, 315.63397, 265.381,
                315.74698, 265.497);
        ((GeneralPath) shape).curveTo(315.85797, 265.61002, 316.07797, 265.669,
                316.40598, 265.669);
        ((GeneralPath) shape).curveTo(316.83496, 265.669, 317.12897, 265.585,
                317.28897, 265.413);
        ((GeneralPath) shape).curveTo(317.44797, 265.24298, 317.52896, 264.93,
                317.52896, 264.474);
        ((GeneralPath) shape).lineTo(317.52896, 262.333);
        ((GeneralPath) shape).lineTo(318.075, 262.333);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(319.135, 262.333);
        ((GeneralPath) shape).lineTo(319.681, 262.333);
        ((GeneralPath) shape).lineTo(319.626, 262.763);
        ((GeneralPath) shape).lineTo(319.638, 262.775);
        ((GeneralPath) shape).curveTo(319.852, 262.423, 320.209, 262.248,
                320.706, 262.248);
        ((GeneralPath) shape).curveTo(321.393, 262.248, 321.735, 262.602,
                321.735, 263.31097);
        ((GeneralPath) shape).lineTo(321.731, 263.56897);
        ((GeneralPath) shape).lineTo(321.193, 263.56897);
        ((GeneralPath) shape).lineTo(321.205, 263.47498);
        ((GeneralPath) shape).curveTo(321.21298, 263.37698, 321.21698,
                263.31097, 321.21698, 263.27597);
        ((GeneralPath) shape).curveTo(321.21698, 262.89297, 321.00998,
                262.70197, 320.593, 262.70197);
        ((GeneralPath) shape).curveTo(319.986, 262.70197, 319.681, 263.07697,
                319.681, 263.83096);
        ((GeneralPath) shape).lineTo(319.681, 266.06897);
        ((GeneralPath) shape).lineTo(319.135, 266.06897);
        ((GeneralPath) shape).lineTo(319.135, 262.333);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(322.311, 262.333);
        ((GeneralPath) shape).lineTo(322.857, 262.333);
        ((GeneralPath) shape).lineTo(322.802, 262.763);
        ((GeneralPath) shape).lineTo(322.814, 262.775);
        ((GeneralPath) shape).curveTo(323.02798, 262.423, 323.384, 262.248,
                323.88098, 262.248);
        ((GeneralPath) shape).curveTo(324.568, 262.248, 324.90997, 262.602,
                324.90997, 263.31097);
        ((GeneralPath) shape).lineTo(324.90698, 263.56897);
        ((GeneralPath) shape).lineTo(324.36798, 263.56897);
        ((GeneralPath) shape).lineTo(324.37997, 263.47498);
        ((GeneralPath) shape).curveTo(324.38797, 263.37698, 324.39297,
                263.31097, 324.39297, 263.27597);
        ((GeneralPath) shape).curveTo(324.39297, 262.89297, 324.18597,
                262.70197, 323.76898, 262.70197);
        ((GeneralPath) shape).curveTo(323.16098, 262.70197, 322.857, 263.07697,
                322.857, 263.83096);
        ((GeneralPath) shape).lineTo(322.857, 266.06897);
        ((GeneralPath) shape).lineTo(322.311, 266.06897);
        ((GeneralPath) shape).lineTo(322.311, 262.333);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(328.024, 263.876);
        ((GeneralPath) shape).lineTo(328.01898, 263.7);
        ((GeneralPath) shape).curveTo(328.01898, 263.298, 327.95398, 263.03403,
                327.82098, 262.91302);
        ((GeneralPath) shape).curveTo(327.688, 262.79202, 327.404, 262.73102,
                326.964, 262.73102);
        ((GeneralPath) shape).curveTo(326.52298, 262.73102, 326.236, 262.80103,
                326.104, 262.94403);
        ((GeneralPath) shape).curveTo(325.974, 263.08502, 325.908, 263.39502,
                325.908, 263.87604);
        ((GeneralPath) shape).lineTo(328.024, 263.87604);
        ((GeneralPath) shape).moveTo(328.024, 264.938);
        ((GeneralPath) shape).lineTo(328.582, 264.938);
        ((GeneralPath) shape).lineTo(328.585, 265.07498);
        ((GeneralPath) shape).curveTo(328.585, 265.46097, 328.469, 265.73297,
                328.232, 265.88898);
        ((GeneralPath) shape).curveTo(327.999, 266.04297, 327.586, 266.12097,
                326.995, 266.12097);
        ((GeneralPath) shape).curveTo(326.30798, 266.12097, 325.858, 265.99597,
                325.643, 265.74396);
        ((GeneralPath) shape).curveTo(325.42902, 265.49396, 325.322, 264.96497,
                325.322, 264.15994);
        ((GeneralPath) shape).curveTo(325.322, 263.41595, 325.429, 262.91595,
                325.645, 262.65994);
        ((GeneralPath) shape).curveTo(325.86, 262.40594, 326.283, 262.27695,
                326.913, 262.27695);
        ((GeneralPath) shape).curveTo(327.6, 262.27695, 328.048, 262.38596,
                328.262, 262.60895);
        ((GeneralPath) shape).curveTo(328.475, 262.82996, 328.582, 263.29694,
                328.582, 264.00696);
        ((GeneralPath) shape).lineTo(328.582, 264.29996);
        ((GeneralPath) shape).lineTo(325.899, 264.29996);
        ((GeneralPath) shape).curveTo(325.899, 264.88797, 325.96198, 265.26297,
                326.08698, 265.42496);
        ((GeneralPath) shape).curveTo(326.21198, 265.58496, 326.50598,
                265.66696, 326.97098, 265.66696);
        ((GeneralPath) shape).curveTo(327.40897, 265.66696, 327.69397,
                265.62997, 327.826, 265.55197);
        ((GeneralPath) shape).curveTo(327.957, 265.47598, 328.02298, 265.30997,
                328.02298, 265.05298);
        ((GeneralPath) shape).lineTo(328.02298, 264.938);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(331.414, 262.333);
        ((GeneralPath) shape).lineTo(331.414, 262.786);
        ((GeneralPath) shape).lineTo(329.979, 262.786);
        ((GeneralPath) shape).lineTo(329.979, 265.071);
        ((GeneralPath) shape).curveTo(329.979, 265.47, 330.154, 265.669,
                330.509, 265.669);
        ((GeneralPath) shape).curveTo(330.86002, 265.669, 331.035, 265.491,
                331.035, 265.134);
        ((GeneralPath) shape).lineTo(331.039, 264.95);
        ((GeneralPath) shape).lineTo(331.047, 264.743);
        ((GeneralPath) shape).lineTo(331.554, 264.743);
        ((GeneralPath) shape).lineTo(331.55798, 265.02002);
        ((GeneralPath) shape).curveTo(331.55798, 265.75403, 331.21, 266.122,
                330.512, 266.122);
        ((GeneralPath) shape).curveTo(329.793, 266.122, 329.43298, 265.81702,
                329.43298, 265.204);
        ((GeneralPath) shape).lineTo(329.43298, 262.786);
        ((GeneralPath) shape).lineTo(328.91797, 262.786);
        ((GeneralPath) shape).lineTo(328.91797, 262.333);
        ((GeneralPath) shape).lineTo(329.43298, 262.333);
        ((GeneralPath) shape).lineTo(329.43298, 261.435);
        ((GeneralPath) shape).lineTo(329.97897, 261.435);
        ((GeneralPath) shape).lineTo(329.97897, 262.333);
        ((GeneralPath) shape).lineTo(331.414, 262.333);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(263.884, 229.927);
        ((GeneralPath) shape).lineTo(263.884, 235.26001);
        ((GeneralPath) shape).lineTo(263.284, 235.26001);
        ((GeneralPath) shape).lineTo(263.284, 230.67401);
        ((GeneralPath) shape).lineTo(263.288, 230.51302);
        ((GeneralPath) shape).lineTo(263.291, 230.35301);
        ((GeneralPath) shape).lineTo(263.27597, 230.35301);
        ((GeneralPath) shape).lineTo(263.22897, 230.47801);
        ((GeneralPath) shape).curveTo(263.20596, 230.54102, 263.18997,
                230.58002, 263.18198, 230.59901);
        ((GeneralPath) shape).lineTo(263.08, 230.84901);
        ((GeneralPath) shape).lineTo(261.267, 235.26001);
        ((GeneralPath) shape).lineTo(260.667, 235.26001);
        ((GeneralPath) shape).lineTo(258.85, 230.90102);
        ((GeneralPath) shape).lineTo(258.745, 230.65402);
        ((GeneralPath) shape).lineTo(258.698, 230.52902);
        ((GeneralPath) shape).curveTo(258.687, 230.50203, 258.671, 230.46103,
                258.652, 230.40802);
        ((GeneralPath) shape).lineTo(258.63602, 230.40802);
        ((GeneralPath) shape).lineTo(258.64, 230.55202);
        ((GeneralPath) shape).lineTo(258.644, 230.70102);
        ((GeneralPath) shape).lineTo(258.644, 235.26003);
        ((GeneralPath) shape).lineTo(258.043, 235.26003);
        ((GeneralPath) shape).lineTo(258.043, 229.92703);
        ((GeneralPath) shape).lineTo(259.08398, 229.92703);
        ((GeneralPath) shape).lineTo(260.503, 233.37703);
        ((GeneralPath) shape).lineTo(260.729, 233.93604);
        ((GeneralPath) shape).lineTo(260.842, 234.21303);
        ((GeneralPath) shape).lineTo(260.95102, 234.49002);
        ((GeneralPath) shape).lineTo(260.967, 234.49002);
        ((GeneralPath) shape).lineTo(261.07602, 234.21303);
        ((GeneralPath) shape).curveTo(261.131, 234.08003, 261.16602, 233.98802,
                261.18503, 233.93604);
        ((GeneralPath) shape).lineTo(261.41504, 233.38104);
        ((GeneralPath) shape).lineTo(262.82703, 229.92705);
        ((GeneralPath) shape).lineTo(263.884, 229.92705);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(266.408, 231.924);
        ((GeneralPath) shape).curveTo(265.925, 231.924, 265.62698, 232.002,
                265.50998, 232.15999);
        ((GeneralPath) shape).curveTo(265.395, 232.316, 265.33597, 232.72798,
                265.33597, 233.39198);
        ((GeneralPath) shape).curveTo(265.33597, 234.05598, 265.39297,
                234.46599, 265.50998, 234.62398);
        ((GeneralPath) shape).curveTo(265.62497, 234.77998, 265.925, 234.85997,
                266.408, 234.85997);
        ((GeneralPath) shape).curveTo(266.89297, 234.85997, 267.194, 234.78197,
                267.311, 234.62398);
        ((GeneralPath) shape).curveTo(267.426, 234.46797, 267.484, 234.05598,
                267.484, 233.39198);
        ((GeneralPath) shape).curveTo(267.484, 232.72798, 267.427, 232.31699,
                267.311, 232.15999);
        ((GeneralPath) shape).curveTo(267.196, 232.004, 266.896, 231.924,
                266.408, 231.924);
        ((GeneralPath) shape).moveTo(266.408, 231.471);
        ((GeneralPath) shape).curveTo(267.09598, 231.471, 267.543, 231.59,
                267.749, 231.82999);
        ((GeneralPath) shape).curveTo(267.95398, 232.068, 268.057, 232.58998,
                268.057, 233.39299);
        ((GeneralPath) shape).curveTo(268.057, 234.193, 267.95502, 234.715,
                267.749, 234.956);
        ((GeneralPath) shape).curveTo(267.544, 235.194, 267.098, 235.31499,
                266.408, 235.31499);
        ((GeneralPath) shape).curveTo(265.722, 235.31499, 265.27798, 235.19598,
                265.07098, 234.956);
        ((GeneralPath) shape).curveTo(264.866, 234.71799, 264.76297, 234.196,
                264.76297, 233.39299);
        ((GeneralPath) shape).curveTo(264.76297, 232.592, 264.86398, 232.07098,
                265.07098, 231.82999);
        ((GeneralPath) shape).curveTo(265.276, 231.592, 265.722, 231.471,
                266.408, 231.471);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(270.886, 231.525);
        ((GeneralPath) shape).lineTo(270.886, 231.978);
        ((GeneralPath) shape).lineTo(269.451, 231.978);
        ((GeneralPath) shape).lineTo(269.451, 234.263);
        ((GeneralPath) shape).curveTo(269.451, 234.662, 269.62598, 234.86101,
                269.981, 234.86101);
        ((GeneralPath) shape).curveTo(270.332, 234.86101, 270.507, 234.68301,
                270.507, 234.326);
        ((GeneralPath) shape).lineTo(270.511, 234.142);
        ((GeneralPath) shape).lineTo(270.51898, 233.935);
        ((GeneralPath) shape).lineTo(271.02597, 233.935);
        ((GeneralPath) shape).lineTo(271.02997, 234.21199);
        ((GeneralPath) shape).curveTo(271.02997, 234.94598, 270.68298, 235.314,
                269.98495, 235.314);
        ((GeneralPath) shape).curveTo(269.26596, 235.314, 268.90497, 235.009,
                268.90497, 234.396);
        ((GeneralPath) shape).lineTo(268.90497, 231.978);
        ((GeneralPath) shape).lineTo(268.38995, 231.978);
        ((GeneralPath) shape).lineTo(268.38995, 231.525);
        ((GeneralPath) shape).lineTo(268.90497, 231.525);
        ((GeneralPath) shape).lineTo(268.90497, 230.627);
        ((GeneralPath) shape).lineTo(269.45096, 230.627);
        ((GeneralPath) shape).lineTo(269.45096, 231.525);
        ((GeneralPath) shape).lineTo(270.886, 231.525);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(272.059, 231.525);
        ((GeneralPath) shape).lineTo(272.059, 235.25899);
        ((GeneralPath) shape).lineTo(271.51398, 235.25899);
        ((GeneralPath) shape).lineTo(271.51398, 231.525);
        ((GeneralPath) shape).lineTo(272.059, 231.525);
        ((GeneralPath) shape).moveTo(272.059, 229.927);
        ((GeneralPath) shape).lineTo(272.059, 230.541);
        ((GeneralPath) shape).lineTo(271.51398, 230.541);
        ((GeneralPath) shape).lineTo(271.51398, 229.927);
        ((GeneralPath) shape).lineTo(272.059, 229.927);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(275.776, 231.525);
        ((GeneralPath) shape).lineTo(274.528, 235.26);
        ((GeneralPath) shape).lineTo(273.744, 235.26);
        ((GeneralPath) shape).lineTo(272.508, 231.525);
        ((GeneralPath) shape).lineTo(273.07, 231.525);
        ((GeneralPath) shape).lineTo(273.729, 233.557);
        ((GeneralPath) shape).lineTo(273.936, 234.193);
        ((GeneralPath) shape).lineTo(274.033, 234.513);
        ((GeneralPath) shape).lineTo(274.134, 234.833);
        ((GeneralPath) shape).lineTo(274.15, 234.833);
        ((GeneralPath) shape).lineTo(274.243, 234.518);
        ((GeneralPath) shape).lineTo(274.337, 234.197);
        ((GeneralPath) shape).lineTo(274.536, 233.564);
        ((GeneralPath) shape).lineTo(275.167, 231.525);
        ((GeneralPath) shape).lineTo(275.776, 231.525);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(278.818, 233.068);
        ((GeneralPath) shape).lineTo(278.814, 232.89299);
        ((GeneralPath) shape).curveTo(278.814, 232.491, 278.748, 232.22699,
                278.615, 232.10599);
        ((GeneralPath) shape).curveTo(278.482, 231.98499, 278.198, 231.92398,
                277.757, 231.92398);
        ((GeneralPath) shape).curveTo(277.317, 231.92398, 277.03, 231.99399,
                276.89798, 232.13698);
        ((GeneralPath) shape).curveTo(276.76697, 232.27798, 276.701, 232.58798,
                276.701, 233.06798);
        ((GeneralPath) shape).lineTo(278.818, 233.06798);
        ((GeneralPath) shape).moveTo(278.818, 234.131);
        ((GeneralPath) shape).lineTo(279.376, 234.131);
        ((GeneralPath) shape).lineTo(279.38, 234.26799);
        ((GeneralPath) shape).curveTo(279.38, 234.65399, 279.263, 234.926,
                279.027, 235.08199);
        ((GeneralPath) shape).curveTo(278.793, 235.236, 278.38, 235.31398,
                277.789, 235.31398);
        ((GeneralPath) shape).curveTo(277.103, 235.31398, 276.652, 235.18898,
                276.438, 234.93698);
        ((GeneralPath) shape).curveTo(276.224, 234.68698, 276.11697, 234.15797,
                276.11697, 233.35298);
        ((GeneralPath) shape).curveTo(276.11697, 232.60898, 276.22397,
                232.10898, 276.43997, 231.85298);
        ((GeneralPath) shape).curveTo(276.65497, 231.59898, 277.07797,
                231.46999, 277.70697, 231.46999);
        ((GeneralPath) shape).curveTo(278.39398, 231.46999, 278.84198,
                231.57898, 279.05597, 231.80199);
        ((GeneralPath) shape).curveTo(279.26797, 232.02298, 279.37598,
                232.48999, 279.37598, 233.20099);
        ((GeneralPath) shape).lineTo(279.37598, 233.49399);
        ((GeneralPath) shape).lineTo(276.69296, 233.49399);
        ((GeneralPath) shape).curveTo(276.69296, 234.08199, 276.75595,
                234.45699, 276.88196, 234.61899);
        ((GeneralPath) shape).curveTo(277.00696, 234.77899, 277.30096, 234.861,
                277.76495, 234.861);
        ((GeneralPath) shape).curveTo(278.20294, 234.861, 278.48795, 234.82399,
                278.62094, 234.74599);
        ((GeneralPath) shape).curveTo(278.75095, 234.66998, 278.81793,
                234.50398, 278.81793, 234.247);
        ((GeneralPath) shape).lineTo(278.81793, 234.131);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(285.908, 231.373);
        ((GeneralPath) shape).lineTo(285.31198, 231.373);
        ((GeneralPath) shape).curveTo(285.31198, 230.94301, 285.24197, 230.672,
                285.097, 230.55501);
        ((GeneralPath) shape).curveTo(284.955, 230.44, 284.61798, 230.38101,
                284.08698, 230.38101);
        ((GeneralPath) shape).curveTo(283.45898, 230.38101, 283.054, 230.436,
                282.87097, 230.54701);
        ((GeneralPath) shape).curveTo(282.68896, 230.656, 282.59796, 230.904,
                282.59796, 231.28702);
        ((GeneralPath) shape).curveTo(282.59796, 231.71701, 282.66797,
                231.97801, 282.81296, 232.07202);
        ((GeneralPath) shape).curveTo(282.95496, 232.16602, 283.37994,
                232.22803, 284.08795, 232.26302);
        ((GeneralPath) shape).curveTo(284.91696, 232.29901, 285.44196,
                232.40402, 285.66895, 232.58002);
        ((GeneralPath) shape).curveTo(285.89294, 232.75401, 286.00595,
                233.14401, 286.00595, 233.75201);
        ((GeneralPath) shape).curveTo(286.00595, 234.40802, 285.87695,
                234.83202, 285.61795, 235.02501);
        ((GeneralPath) shape).curveTo(285.36096, 235.216, 284.78894, 235.31401,
                283.90494, 235.31401);
        ((GeneralPath) shape).curveTo(283.13895, 235.31401, 282.62793,
                235.21802, 282.37494, 235.02301);
        ((GeneralPath) shape).curveTo(282.12094, 234.83002, 281.99493,
                234.43901, 281.99493, 233.84901);
        ((GeneralPath) shape).lineTo(281.99094, 233.61101);
        ((GeneralPath) shape).lineTo(282.58792, 233.61101);
        ((GeneralPath) shape).lineTo(282.58792, 233.744);
        ((GeneralPath) shape).curveTo(282.58792, 234.22101, 282.6599, 234.515,
                282.8059, 234.63301);
        ((GeneralPath) shape).curveTo(282.94992, 234.74802, 283.32492, 234.807,
                283.9249, 234.807);
        ((GeneralPath) shape).curveTo(284.61288, 234.807, 285.0359, 234.75,
                285.1959, 234.63301);
        ((GeneralPath) shape).curveTo(285.35388, 234.518, 285.4339, 234.20901,
                285.4339, 233.70502);
        ((GeneralPath) shape).curveTo(285.4339, 233.38101, 285.3809, 233.16402,
                285.2719, 233.05502);
        ((GeneralPath) shape).curveTo(285.16492, 232.94803, 284.9389,
                232.88303, 284.59592, 232.86203);
        ((GeneralPath) shape).lineTo(283.97192, 232.83102);
        ((GeneralPath) shape).lineTo(283.37894, 232.80002);
        ((GeneralPath) shape).curveTo(282.47894, 232.73701, 282.02594,
                232.26901, 282.02594, 231.39401);
        ((GeneralPath) shape).curveTo(282.02594, 230.78902, 282.15695,
                230.38202, 282.41995, 230.17902);
        ((GeneralPath) shape).curveTo(282.68094, 229.97601, 283.20596,
                229.87402, 283.99097, 229.87402);
        ((GeneralPath) shape).curveTo(284.78598, 229.87402, 285.30496,
                229.96802, 285.54697, 230.15802);
        ((GeneralPath) shape).curveTo(285.787, 230.344, 285.908, 230.75,
                285.908, 231.373);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(289.351, 231.525);
        ((GeneralPath) shape).lineTo(288.294, 235.474);
        ((GeneralPath) shape).curveTo(288.146, 236.031, 287.982, 236.414,
                287.801, 236.622);
        ((GeneralPath) shape).curveTo(287.622, 236.82999, 287.363, 236.935,
                287.027, 236.935);
        ((GeneralPath) shape).curveTo(286.961, 236.935, 286.87302, 236.927,
                286.76602, 236.908);
        ((GeneralPath) shape).lineTo(286.76602, 236.455);
        ((GeneralPath) shape).curveTo(286.84003, 236.47101, 286.90503, 236.478,
                286.95703, 236.482);
        ((GeneralPath) shape).curveTo(287.24902, 236.498, 287.46802, 236.26,
                287.61203, 235.76799);
        ((GeneralPath) shape).lineTo(287.71704, 235.40099);
        ((GeneralPath) shape).curveTo(287.71906, 235.38899, 287.73004,
                235.34198, 287.75204, 235.25998);
        ((GeneralPath) shape).lineTo(287.54504, 235.25998);
        ((GeneralPath) shape).lineTo(286.19604, 231.52599);
        ((GeneralPath) shape).lineTo(286.77704, 231.52599);
        ((GeneralPath) shape).lineTo(287.35004, 233.18999);
        ((GeneralPath) shape).lineTo(287.63904, 234.01799);
        ((GeneralPath) shape).lineTo(287.77905, 234.43599);
        ((GeneralPath) shape).lineTo(287.92404, 234.84999);
        ((GeneralPath) shape).lineTo(287.94003, 234.84999);
        ((GeneralPath) shape).lineTo(288.04105, 234.43599);
        ((GeneralPath) shape).lineTo(288.13904, 234.01799);
        ((GeneralPath) shape).lineTo(288.34903, 233.18999);
        ((GeneralPath) shape).lineTo(288.77002, 231.52599);
        ((GeneralPath) shape).lineTo(289.351, 231.52599);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(292.699, 232.502);
        ((GeneralPath) shape).lineTo(292.153, 232.502);
        ((GeneralPath) shape).curveTo(292.153, 232.242, 292.1, 232.08,
                291.99503, 232.018);
        ((GeneralPath) shape).curveTo(291.89, 231.955, 291.61902, 231.92401,
                291.18204, 231.92401);
        ((GeneralPath) shape).curveTo(290.77603, 231.92401, 290.51904,
                231.95702, 290.41003, 232.02402);
        ((GeneralPath) shape).curveTo(290.30103, 232.09102, 290.24603,
                232.24802, 290.24603, 232.50302);
        ((GeneralPath) shape).curveTo(290.24603, 232.88602, 290.43002,
                233.08502, 290.79602, 233.10503);
        ((GeneralPath) shape).lineTo(291.23602, 233.12802);
        ((GeneralPath) shape).lineTo(291.79404, 233.15501);
        ((GeneralPath) shape).curveTo(292.46805, 233.18901, 292.80804, 233.542,
                292.80804, 234.21802);
        ((GeneralPath) shape).curveTo(292.80804, 234.63602, 292.69705,
                234.92502, 292.47305, 235.08102);
        ((GeneralPath) shape).curveTo(292.25107, 235.23703, 291.84106,
                235.31502, 291.24506, 235.31502);
        ((GeneralPath) shape).curveTo(290.63507, 235.31502, 290.21405,
                235.24101, 289.98407, 235.09201);
        ((GeneralPath) shape).curveTo(289.75406, 234.94301, 289.63907,
                234.67201, 289.63907, 234.27602);
        ((GeneralPath) shape).lineTo(289.64307, 234.07301);
        ((GeneralPath) shape).lineTo(290.20807, 234.07301);
        ((GeneralPath) shape).lineTo(290.21207, 234.24802);
        ((GeneralPath) shape).curveTo(290.21207, 234.49301, 290.27505,
                234.65701, 290.39908, 234.73901);
        ((GeneralPath) shape).curveTo(290.52408, 234.82101, 290.77008,
                234.86201, 291.13608, 234.86201);
        ((GeneralPath) shape).curveTo(291.58408, 234.86201, 291.88107,
                234.81902, 292.02307, 234.73302);
        ((GeneralPath) shape).curveTo(292.1631, 234.64801, 292.23508,
                234.46802, 292.23508, 234.19402);
        ((GeneralPath) shape).curveTo(292.23508, 233.80202, 292.05807,
                233.60501, 291.70108, 233.60501);
        ((GeneralPath) shape).curveTo(290.87207, 233.60501, 290.3271, 233.535,
                290.0651, 233.39401);
        ((GeneralPath) shape).curveTo(289.8041, 233.253, 289.6731, 232.962,
                289.6731, 232.51901);
        ((GeneralPath) shape).curveTo(289.6731, 232.10101, 289.7761, 231.822,
                289.9831, 231.68102);
        ((GeneralPath) shape).curveTo(290.1901, 231.542, 290.6011, 231.47202,
                291.2171, 231.47202);
        ((GeneralPath) shape).curveTo(292.2031, 231.47202, 292.6981, 231.76901,
                292.6981, 232.36702);
        ((GeneralPath) shape).lineTo(292.6981, 232.502);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(295.573, 231.525);
        ((GeneralPath) shape).lineTo(295.573, 231.978);
        ((GeneralPath) shape).lineTo(294.138, 231.978);
        ((GeneralPath) shape).lineTo(294.138, 234.263);
        ((GeneralPath) shape).curveTo(294.138, 234.662, 294.313, 234.86101,
                294.668, 234.86101);
        ((GeneralPath) shape).curveTo(295.019, 234.86101, 295.194, 234.68301,
                295.194, 234.326);
        ((GeneralPath) shape).lineTo(295.198, 234.142);
        ((GeneralPath) shape).lineTo(295.206, 233.935);
        ((GeneralPath) shape).lineTo(295.71298, 233.935);
        ((GeneralPath) shape).lineTo(295.71698, 234.21199);
        ((GeneralPath) shape).curveTo(295.71698, 234.94598, 295.37, 235.314,
                294.67197, 235.314);
        ((GeneralPath) shape).curveTo(293.95297, 235.314, 293.59198, 235.009,
                293.59198, 234.396);
        ((GeneralPath) shape).lineTo(293.59198, 231.978);
        ((GeneralPath) shape).lineTo(293.07697, 231.978);
        ((GeneralPath) shape).lineTo(293.07697, 231.525);
        ((GeneralPath) shape).lineTo(293.59198, 231.525);
        ((GeneralPath) shape).lineTo(293.59198, 230.627);
        ((GeneralPath) shape).lineTo(294.13797, 230.627);
        ((GeneralPath) shape).lineTo(294.13797, 231.525);
        ((GeneralPath) shape).lineTo(295.573, 231.525);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(298.792, 233.068);
        ((GeneralPath) shape).lineTo(298.788, 232.89299);
        ((GeneralPath) shape).curveTo(298.788, 232.491, 298.722, 232.22699,
                298.589, 232.10599);
        ((GeneralPath) shape).curveTo(298.456, 231.98499, 298.172, 231.92398,
                297.731, 231.92398);
        ((GeneralPath) shape).curveTo(297.28998, 231.92398, 297.004, 231.99399,
                296.87198, 232.13698);
        ((GeneralPath) shape).curveTo(296.74097, 232.27798, 296.675, 232.58798,
                296.675, 233.06798);
        ((GeneralPath) shape).lineTo(298.792, 233.06798);
        ((GeneralPath) shape).moveTo(298.792, 234.131);
        ((GeneralPath) shape).lineTo(299.35, 234.131);
        ((GeneralPath) shape).lineTo(299.354, 234.26799);
        ((GeneralPath) shape).curveTo(299.354, 234.65399, 299.237, 234.926,
                299.001, 235.08199);
        ((GeneralPath) shape).curveTo(298.767, 235.236, 298.354, 235.31398,
                297.763, 235.31398);
        ((GeneralPath) shape).curveTo(297.077, 235.31398, 296.626, 235.18898,
                296.412, 234.93698);
        ((GeneralPath) shape).curveTo(296.198, 234.68698, 296.09097, 234.15797,
                296.09097, 233.35298);
        ((GeneralPath) shape).curveTo(296.09097, 232.60898, 296.19797,
                232.10898, 296.41397, 231.85298);
        ((GeneralPath) shape).curveTo(296.62897, 231.59898, 297.05197,
                231.46999, 297.68097, 231.46999);
        ((GeneralPath) shape).curveTo(298.36798, 231.46999, 298.81598,
                231.57898, 299.02997, 231.80199);
        ((GeneralPath) shape).curveTo(299.24197, 232.02298, 299.34998,
                232.48999, 299.34998, 233.20099);
        ((GeneralPath) shape).lineTo(299.34998, 233.49399);
        ((GeneralPath) shape).lineTo(296.66696, 233.49399);
        ((GeneralPath) shape).curveTo(296.66696, 234.08199, 296.72995,
                234.45699, 296.85596, 234.61899);
        ((GeneralPath) shape).curveTo(296.98096, 234.77899, 297.27496, 234.861,
                297.73895, 234.861);
        ((GeneralPath) shape).curveTo(298.17694, 234.861, 298.46194, 234.82399,
                298.59494, 234.74599);
        ((GeneralPath) shape).curveTo(298.72595, 234.66998, 298.79193,
                234.50398, 298.79193, 234.247);
        ((GeneralPath) shape).lineTo(298.79193, 234.131);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(300.208, 231.525);
        ((GeneralPath) shape).lineTo(300.754, 231.525);
        ((GeneralPath) shape).lineTo(300.742, 232.088);
        ((GeneralPath) shape).lineTo(300.754, 232.099);
        ((GeneralPath) shape).curveTo(300.964, 231.68199, 301.37, 231.471,
                301.97, 231.471);
        ((GeneralPath) shape).curveTo(302.601, 231.471, 302.984, 231.68,
                303.12, 232.099);
        ((GeneralPath) shape).lineTo(303.136, 232.099);
        ((GeneralPath) shape).curveTo(303.37, 231.68199, 303.797, 231.471,
                304.418, 231.471);
        ((GeneralPath) shape).curveTo(305.296, 231.471, 305.735, 231.92,
                305.735, 232.818);
        ((GeneralPath) shape).lineTo(305.735, 235.26);
        ((GeneralPath) shape).lineTo(305.18997, 235.26);
        ((GeneralPath) shape).lineTo(305.18997, 232.76799);
        ((GeneralPath) shape).curveTo(305.18997, 232.43999, 305.13098, 232.217,
                305.01398, 232.09999);
        ((GeneralPath) shape).curveTo(304.89697, 231.98299, 304.67697,
                231.92499, 304.35098, 231.92499);
        ((GeneralPath) shape).curveTo(303.91998, 231.92499, 303.628, 232.00499,
                303.47397, 232.16899);
        ((GeneralPath) shape).curveTo(303.32196, 232.331, 303.24396, 232.642,
                303.24396, 233.09999);
        ((GeneralPath) shape).lineTo(303.24396, 235.26099);
        ((GeneralPath) shape).lineTo(302.7, 235.26099);
        ((GeneralPath) shape).lineTo(302.7, 232.81898);
        ((GeneralPath) shape).lineTo(302.69202, 232.64798);
        ((GeneralPath) shape).curveTo(302.69202, 232.16798, 302.419, 231.92497,
                301.86902, 231.92497);
        ((GeneralPath) shape).curveTo(301.126, 231.92497, 300.75403, 232.32898,
                300.75403, 233.13898);
        ((GeneralPath) shape).lineTo(300.75403, 235.26097);
        ((GeneralPath) shape).lineTo(300.20804, 235.26097);
        ((GeneralPath) shape).lineTo(300.20804, 231.525);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(313.141, 229.927);
        ((GeneralPath) shape).lineTo(313.141, 235.26);
        ((GeneralPath) shape).lineTo(312.54, 235.26);
        ((GeneralPath) shape).lineTo(312.54, 232.787);
        ((GeneralPath) shape).lineTo(309.429, 232.787);
        ((GeneralPath) shape).lineTo(309.429, 235.26);
        ((GeneralPath) shape).lineTo(308.828, 235.26);
        ((GeneralPath) shape).lineTo(308.828, 229.927);
        ((GeneralPath) shape).lineTo(309.429, 229.927);
        ((GeneralPath) shape).lineTo(309.429, 232.279);
        ((GeneralPath) shape).lineTo(312.54, 232.279);
        ((GeneralPath) shape).lineTo(312.54, 229.927);
        ((GeneralPath) shape).lineTo(313.141, 229.927);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(314.674, 231.525);
        ((GeneralPath) shape).lineTo(314.674, 235.25899);
        ((GeneralPath) shape).lineTo(314.12802, 235.25899);
        ((GeneralPath) shape).lineTo(314.12802, 231.525);
        ((GeneralPath) shape).lineTo(314.674, 231.525);
        ((GeneralPath) shape).moveTo(314.674, 229.927);
        ((GeneralPath) shape).lineTo(314.674, 230.541);
        ((GeneralPath) shape).lineTo(314.12802, 230.541);
        ((GeneralPath) shape).lineTo(314.12802, 229.927);
        ((GeneralPath) shape).lineTo(314.674, 229.927);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(317.606, 231.525);
        ((GeneralPath) shape).lineTo(317.606, 231.978);
        ((GeneralPath) shape).lineTo(316.171, 231.978);
        ((GeneralPath) shape).lineTo(316.171, 234.263);
        ((GeneralPath) shape).curveTo(316.171, 234.662, 316.34598, 234.86101,
                316.701, 234.86101);
        ((GeneralPath) shape).curveTo(317.052, 234.86101, 317.227, 234.68301,
                317.227, 234.326);
        ((GeneralPath) shape).lineTo(317.231, 234.142);
        ((GeneralPath) shape).lineTo(317.23898, 233.935);
        ((GeneralPath) shape).lineTo(317.74597, 233.935);
        ((GeneralPath) shape).lineTo(317.74997, 234.21199);
        ((GeneralPath) shape).curveTo(317.74997, 234.94598, 317.40198, 235.314,
                316.70398, 235.314);
        ((GeneralPath) shape).curveTo(315.985, 235.314, 315.62497, 235.009,
                315.62497, 234.396);
        ((GeneralPath) shape).lineTo(315.62497, 231.978);
        ((GeneralPath) shape).lineTo(315.10995, 231.978);
        ((GeneralPath) shape).lineTo(315.10995, 231.525);
        ((GeneralPath) shape).lineTo(315.62497, 231.525);
        ((GeneralPath) shape).lineTo(315.62497, 230.627);
        ((GeneralPath) shape).lineTo(316.17096, 230.627);
        ((GeneralPath) shape).lineTo(316.17096, 231.525);
        ((GeneralPath) shape).lineTo(317.606, 231.525);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(321.123, 232.502);
        ((GeneralPath) shape).lineTo(320.577, 232.502);
        ((GeneralPath) shape).curveTo(320.577, 232.242, 320.524, 232.08,
                320.419, 232.018);
        ((GeneralPath) shape).curveTo(320.31403, 231.95601, 320.042, 231.92401,
                319.60602, 231.92401);
        ((GeneralPath) shape).curveTo(319.2, 231.92401, 318.94302, 231.95702,
                318.834, 232.02402);
        ((GeneralPath) shape).curveTo(318.725, 232.09102, 318.67, 232.24802,
                318.67, 232.50302);
        ((GeneralPath) shape).curveTo(318.67, 232.88602, 318.854, 233.08502,
                319.22, 233.10503);
        ((GeneralPath) shape).lineTo(319.661, 233.12802);
        ((GeneralPath) shape).lineTo(320.21802, 233.15501);
        ((GeneralPath) shape).curveTo(320.893, 233.18901, 321.23203, 233.542,
                321.23203, 234.21802);
        ((GeneralPath) shape).curveTo(321.23203, 234.63602, 321.12204,
                234.92502, 320.89703, 235.08102);
        ((GeneralPath) shape).curveTo(320.67505, 235.23703, 320.26505,
                235.31502, 319.66803, 235.31502);
        ((GeneralPath) shape).curveTo(319.05804, 235.31502, 318.63702,
                235.24101, 318.40704, 235.09201);
        ((GeneralPath) shape).curveTo(318.17703, 234.94301, 318.06104,
                234.67201, 318.06104, 234.27602);
        ((GeneralPath) shape).lineTo(318.06604, 234.07301);
        ((GeneralPath) shape).lineTo(318.63004, 234.07301);
        ((GeneralPath) shape).lineTo(318.63403, 234.24802);
        ((GeneralPath) shape).curveTo(318.63403, 234.49301, 318.69702,
                234.65701, 318.82202, 234.73901);
        ((GeneralPath) shape).curveTo(318.94702, 234.82101, 319.19202,
                234.86201, 319.558, 234.86201);
        ((GeneralPath) shape).curveTo(320.00702, 234.86201, 320.303, 234.81902,
                320.446, 234.73302);
        ((GeneralPath) shape).curveTo(320.58603, 234.64801, 320.65903,
                234.46802, 320.65903, 234.19402);
        ((GeneralPath) shape).curveTo(320.65903, 233.80202, 320.48102,
                233.60501, 320.12402, 233.60501);
        ((GeneralPath) shape).curveTo(319.29602, 233.60501, 318.75003, 233.535,
                318.489, 233.39401);
        ((GeneralPath) shape).curveTo(318.22702, 233.253, 318.096, 232.962,
                318.096, 232.51901);
        ((GeneralPath) shape).curveTo(318.096, 232.10101, 318.2, 231.822,
                318.407, 231.68102);
        ((GeneralPath) shape).curveTo(318.613, 231.542, 319.02502, 231.47202,
                319.64, 231.47202);
        ((GeneralPath) shape).curveTo(320.626, 231.47202, 321.122, 231.76901,
                321.122, 232.36702);
        ((GeneralPath) shape).lineTo(321.122, 232.502);
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
        paint = new Color(35, 31, 32, 255);
        stroke = new BasicStroke(1.0f, 0, 0, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(350.804, 235.842);
        ((GeneralPath) shape).curveTo(350.804, 236.593, 350.19498, 237.202,
                349.44098, 237.202);
        ((GeneralPath) shape).lineTo(342.52097, 237.202);
        ((GeneralPath) shape).curveTo(341.76996, 237.202, 341.15897, 236.594,
                341.15897, 235.842);
        ((GeneralPath) shape).lineTo(341.15897, 228.84999);
        ((GeneralPath) shape).curveTo(341.15897, 228.09799, 341.76996,
                227.48799, 342.52097, 227.48799);
        ((GeneralPath) shape).lineTo(349.44098, 227.48799);
        ((GeneralPath) shape).curveTo(350.19498, 227.48799, 350.804, 228.09698,
                350.804, 228.84999);
        ((GeneralPath) shape).lineTo(350.804, 235.842);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(343.478, 234.428);
        ((GeneralPath) shape).lineTo(343.478, 233.036);
        ((GeneralPath) shape).lineTo(342.103, 233.036);
        ((GeneralPath) shape).lineTo(342.103, 232.592);
        ((GeneralPath) shape).lineTo(343.478, 232.592);
        ((GeneralPath) shape).lineTo(343.478, 231.194);
        ((GeneralPath) shape).lineTo(343.955, 231.194);
        ((GeneralPath) shape).lineTo(343.955, 232.592);
        ((GeneralPath) shape).lineTo(345.33, 232.592);
        ((GeneralPath) shape).lineTo(345.33, 233.036);
        ((GeneralPath) shape).lineTo(343.955, 233.036);
        ((GeneralPath) shape).lineTo(343.955, 234.428);
        ((GeneralPath) shape).lineTo(343.478, 234.428);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(348.618, 230.479);
        ((GeneralPath) shape).lineTo(348.618, 235.146);
        ((GeneralPath) shape).lineTo(348.093, 235.146);
        ((GeneralPath) shape).lineTo(348.093, 230.873);
        ((GeneralPath) shape).lineTo(346.901, 232.195);
        ((GeneralPath) shape).lineTo(346.571, 231.881);
        ((GeneralPath) shape).lineTo(347.86, 230.479);
        ((GeneralPath) shape).lineTo(348.618, 230.479);
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
        paint = new Color(35, 31, 32, 255);
        stroke = new BasicStroke(1.0f, 0, 0, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(362.896, 235.842);
        ((GeneralPath) shape).curveTo(362.896, 236.593, 362.287, 237.202,
                361.534, 237.202);
        ((GeneralPath) shape).lineTo(354.612, 237.202);
        ((GeneralPath) shape).curveTo(353.86, 237.202, 353.252, 236.594,
                353.252, 235.842);
        ((GeneralPath) shape).lineTo(353.252, 228.847);
        ((GeneralPath) shape).curveTo(353.252, 228.097, 353.86002, 227.48601,
                354.612, 227.48601);
        ((GeneralPath) shape).lineTo(361.534, 227.48601);
        ((GeneralPath) shape).curveTo(362.287, 227.48601, 362.896, 228.097,
                362.896, 228.847);
        ((GeneralPath) shape).lineTo(362.896, 235.842);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(355.571, 234.428);
        ((GeneralPath) shape).lineTo(355.571, 233.036);
        ((GeneralPath) shape).lineTo(354.196, 233.036);
        ((GeneralPath) shape).lineTo(354.196, 232.592);
        ((GeneralPath) shape).lineTo(355.571, 232.592);
        ((GeneralPath) shape).lineTo(355.571, 231.194);
        ((GeneralPath) shape).lineTo(356.049, 231.194);
        ((GeneralPath) shape).lineTo(356.049, 232.592);
        ((GeneralPath) shape).lineTo(357.424, 232.592);
        ((GeneralPath) shape).lineTo(357.424, 233.036);
        ((GeneralPath) shape).lineTo(356.049, 233.036);
        ((GeneralPath) shape).lineTo(356.049, 234.428);
        ((GeneralPath) shape).lineTo(355.571, 234.428);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(361.681, 234.701);
        ((GeneralPath) shape).lineTo(361.681, 235.145);
        ((GeneralPath) shape).lineTo(358.327, 235.145);
        ((GeneralPath) shape).lineTo(358.327, 234.267);
        ((GeneralPath) shape).curveTo(358.327, 233.774, 358.423, 233.454,
                358.61398, 233.3);
        ((GeneralPath) shape).curveTo(358.804, 233.14801, 359.25897, 233.03,
                359.97397, 232.947);
        ((GeneralPath) shape).curveTo(360.54697, 232.884, 360.89398, 232.79701,
                361.01297, 232.688);
        ((GeneralPath) shape).curveTo(361.13196, 232.579, 361.19196, 232.295,
                361.19196, 231.837);
        ((GeneralPath) shape).curveTo(361.19196, 231.43701, 361.12595, 231.175,
                360.99295, 231.056);
        ((GeneralPath) shape).curveTo(360.85995, 230.937, 360.56995, 230.877,
                360.12094, 230.877);
        ((GeneralPath) shape).curveTo(359.56094, 230.877, 359.20895, 230.925,
                359.06195, 231.022);
        ((GeneralPath) shape).curveTo(358.91595, 231.118, 358.84094, 231.352,
                358.84094, 231.72101);
        ((GeneralPath) shape).lineTo(358.84894, 232.07);
        ((GeneralPath) shape).lineTo(358.33295, 232.07);
        ((GeneralPath) shape).lineTo(358.33694, 231.82701);
        ((GeneralPath) shape).curveTo(358.33694, 231.27, 358.45395, 230.89702,
                358.68994, 230.71101);
        ((GeneralPath) shape).curveTo(358.92493, 230.52602, 359.39594, 230.432,
                360.10394, 230.432);
        ((GeneralPath) shape).curveTo(360.73193, 230.432, 361.15295, 230.53201,
                361.36993, 230.733);
        ((GeneralPath) shape).curveTo(361.58493, 230.933, 361.69394, 231.324,
                361.69394, 231.905);
        ((GeneralPath) shape).curveTo(361.69394, 232.462, 361.59293, 232.836,
                361.39194, 233.024);
        ((GeneralPath) shape).curveTo(361.19095, 233.212, 360.75293, 233.338,
                360.07993, 233.405);
        ((GeneralPath) shape).curveTo(359.48892, 233.463, 359.13693, 233.545,
                359.0219, 233.651);
        ((GeneralPath) shape).curveTo(358.9089, 233.755, 358.85092, 234.053,
                358.85092, 234.544);
        ((GeneralPath) shape).lineTo(358.85092, 234.701);
        ((GeneralPath) shape).lineTo(361.681, 234.701);
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
        paint = new Color(35, 31, 32, 255);
        stroke = new BasicStroke(1.0f, 0, 0, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(374.987, 235.842);
        ((GeneralPath) shape).curveTo(374.987, 236.593, 374.379, 237.202,
                373.627, 237.202);
        ((GeneralPath) shape).lineTo(366.70502, 237.202);
        ((GeneralPath) shape).curveTo(365.953, 237.202, 365.34503, 236.594,
                365.34503, 235.842);
        ((GeneralPath) shape).lineTo(365.34503, 228.84999);
        ((GeneralPath) shape).curveTo(365.34503, 228.09799, 365.95303,
                227.48799, 366.70502, 227.48799);
        ((GeneralPath) shape).lineTo(373.627, 227.48799);
        ((GeneralPath) shape).curveTo(374.37903, 227.48799, 374.987, 228.09698,
                374.987, 228.84999);
        ((GeneralPath) shape).lineTo(374.987, 235.842);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_98;
        g.setTransform(defaultTransform__0_98);
        g.setClip(clip__0_98);
        float alpha__0_99 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_99 = g.getClip();
        AffineTransform defaultTransform__0_99 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_99 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(367.662, 234.428);
        ((GeneralPath) shape).lineTo(367.662, 233.036);
        ((GeneralPath) shape).lineTo(366.288, 233.036);
        ((GeneralPath) shape).lineTo(366.288, 232.592);
        ((GeneralPath) shape).lineTo(367.662, 232.592);
        ((GeneralPath) shape).lineTo(367.662, 231.194);
        ((GeneralPath) shape).lineTo(368.14, 231.194);
        ((GeneralPath) shape).lineTo(368.14, 232.592);
        ((GeneralPath) shape).lineTo(369.515, 232.592);
        ((GeneralPath) shape).lineTo(369.515, 233.036);
        ((GeneralPath) shape).lineTo(368.14, 233.036);
        ((GeneralPath) shape).lineTo(368.14, 234.428);
        ((GeneralPath) shape).lineTo(367.662, 234.428);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(371.746, 232.982);
        ((GeneralPath) shape).lineTo(371.746, 232.534);
        ((GeneralPath) shape).lineTo(371.923, 232.537);
        ((GeneralPath) shape).curveTo(372.551, 232.537, 372.935, 232.498,
                373.074, 232.418);
        ((GeneralPath) shape).curveTo(373.21402, 232.339, 373.284, 232.12,
                373.284, 231.765);
        ((GeneralPath) shape).curveTo(373.284, 231.354, 373.224, 231.105,
                373.10098, 231.013);
        ((GeneralPath) shape).curveTo(372.97998, 230.922, 372.64398, 230.876,
                372.093, 230.876);
        ((GeneralPath) shape).curveTo(371.586, 230.876, 371.27298, 230.92001,
                371.15298, 231.009);
        ((GeneralPath) shape).curveTo(371.034, 231.098, 370.974, 231.33,
                370.974, 231.709);
        ((GeneralPath) shape).lineTo(370.974, 231.911);
        ((GeneralPath) shape).lineTo(370.476, 231.911);
        ((GeneralPath) shape).lineTo(370.479, 231.726);
        ((GeneralPath) shape).curveTo(370.479, 231.193, 370.582, 230.844,
                370.79, 230.678);
        ((GeneralPath) shape).curveTo(370.997, 230.51399, 371.43402, 230.43,
                372.101, 230.43);
        ((GeneralPath) shape).curveTo(372.785, 230.43, 373.237, 230.50699,
                373.457, 230.661);
        ((GeneralPath) shape).curveTo(373.675, 230.815, 373.786, 231.131,
                373.786, 231.61299);
        ((GeneralPath) shape).curveTo(373.786, 232.01999, 373.734, 232.301,
                373.629, 232.45099);
        ((GeneralPath) shape).curveTo(373.525, 232.60098, 373.316, 232.69499,
                373.001, 232.73499);
        ((GeneralPath) shape).lineTo(373.001, 232.75899);
        ((GeneralPath) shape).curveTo(373.353, 232.80699, 373.586, 232.90298,
                373.702, 233.04599);
        ((GeneralPath) shape).curveTo(373.816, 233.18999, 373.874, 233.456,
                373.874, 233.846);
        ((GeneralPath) shape).curveTo(373.874, 234.388, 373.764, 234.74799,
                373.538, 234.926);
        ((GeneralPath) shape).curveTo(373.315, 235.10399, 372.863, 235.193,
                372.18298, 235.193);
        ((GeneralPath) shape).curveTo(371.44998, 235.193, 370.968, 235.111,
                370.74, 234.94699);
        ((GeneralPath) shape).curveTo(370.513, 234.78299, 370.399, 234.43599,
                370.399, 233.90498);
        ((GeneralPath) shape).lineTo(370.399, 233.62799);
        ((GeneralPath) shape).lineTo(370.91098, 233.62799);
        ((GeneralPath) shape).lineTo(370.91098, 233.898);
        ((GeneralPath) shape).curveTo(370.91098, 234.295, 370.977, 234.536,
                371.11197, 234.621);
        ((GeneralPath) shape).curveTo(371.24496, 234.70601, 371.62698,
                234.74901, 372.25497, 234.74901);
        ((GeneralPath) shape).curveTo(372.74097, 234.74901, 373.05, 234.69601,
                373.17996, 234.58601);
        ((GeneralPath) shape).curveTo(373.30997, 234.47801, 373.37396,
                234.21901, 373.37396, 233.80801);
        ((GeneralPath) shape).curveTo(373.37396, 233.46501, 373.31195,
                233.24101, 373.18896, 233.13701);
        ((GeneralPath) shape).curveTo(373.06598, 233.03401, 372.79996,
                232.98201, 372.38696, 232.98201);
        ((GeneralPath) shape).lineTo(371.746, 232.98201);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(261.692, 219.635);
        ((GeneralPath) shape).lineTo(261.09598, 219.635);
        ((GeneralPath) shape).curveTo(261.09598, 219.205, 261.02597, 218.93399,
                260.88098, 218.817);
        ((GeneralPath) shape).curveTo(260.73898, 218.702, 260.40198, 218.643,
                259.87097, 218.643);
        ((GeneralPath) shape).curveTo(259.24396, 218.643, 258.83798, 218.698,
                258.65497, 218.809);
        ((GeneralPath) shape).curveTo(258.47397, 218.918, 258.38196, 219.166,
                258.38196, 219.54901);
        ((GeneralPath) shape).curveTo(258.38196, 219.979, 258.45197, 220.24,
                258.59595, 220.33401);
        ((GeneralPath) shape).curveTo(258.73895, 220.42801, 259.16293,
                220.49002, 259.87094, 220.52501);
        ((GeneralPath) shape).curveTo(260.69995, 220.561, 261.22595, 220.66602,
                261.45193, 220.84201);
        ((GeneralPath) shape).curveTo(261.67593, 221.01501, 261.78894, 221.406,
                261.78894, 222.014);
        ((GeneralPath) shape).curveTo(261.78894, 222.67001, 261.65994,
                223.09401, 261.40094, 223.287);
        ((GeneralPath) shape).curveTo(261.14395, 223.478, 260.57193, 223.576,
                259.68695, 223.576);
        ((GeneralPath) shape).curveTo(258.92096, 223.576, 258.40994, 223.48001,
                258.15695, 223.285);
        ((GeneralPath) shape).curveTo(257.90396, 223.09201, 257.77695, 222.701,
                257.77695, 222.11101);
        ((GeneralPath) shape).lineTo(257.77295, 221.873);
        ((GeneralPath) shape).lineTo(258.36993, 221.873);
        ((GeneralPath) shape).lineTo(258.36993, 222.006);
        ((GeneralPath) shape).curveTo(258.36993, 222.483, 258.44193, 222.777,
                258.58792, 222.895);
        ((GeneralPath) shape).curveTo(258.73193, 223.01001, 259.10693, 223.069,
                259.7069, 223.069);
        ((GeneralPath) shape).curveTo(260.3949, 223.069, 260.8179, 223.012,
                260.9779, 222.895);
        ((GeneralPath) shape).curveTo(261.1359, 222.78, 261.2159, 222.47101,
                261.2159, 221.96701);
        ((GeneralPath) shape).curveTo(261.2159, 221.643, 261.1629, 221.42601,
                261.05392, 221.31702);
        ((GeneralPath) shape).curveTo(260.94693, 221.21002, 260.72092,
                221.14502, 260.37692, 221.12402);
        ((GeneralPath) shape).lineTo(259.75293, 221.09302);
        ((GeneralPath) shape).lineTo(259.15994, 221.06201);
        ((GeneralPath) shape).curveTo(258.25995, 220.99901, 257.80695, 220.531,
                257.80695, 219.656);
        ((GeneralPath) shape).curveTo(257.80695, 219.05101, 257.93796,
                218.64401, 258.20096, 218.44101);
        ((GeneralPath) shape).curveTo(258.46194, 218.238, 258.98697, 218.13602,
                259.77197, 218.13602);
        ((GeneralPath) shape).curveTo(260.567, 218.13602, 261.08496, 218.23001,
                261.32797, 218.42001);
        ((GeneralPath) shape).curveTo(261.571, 218.606, 261.692, 219.013,
                261.692, 219.635);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(265.081, 221.331);
        ((GeneralPath) shape).lineTo(265.077, 221.15599);
        ((GeneralPath) shape).curveTo(265.077, 220.754, 265.011, 220.48999,
                264.879, 220.368);
        ((GeneralPath) shape).curveTo(264.746, 220.247, 264.462, 220.187,
                264.021, 220.187);
        ((GeneralPath) shape).curveTo(263.58, 220.187, 263.294, 220.257,
                263.161, 220.399);
        ((GeneralPath) shape).curveTo(263.031, 220.54001, 262.96402, 220.851,
                262.96402, 221.33101);
        ((GeneralPath) shape).lineTo(265.081, 221.33101);
        ((GeneralPath) shape).moveTo(265.081, 222.394);
        ((GeneralPath) shape).lineTo(265.639, 222.394);
        ((GeneralPath) shape).lineTo(265.643, 222.53099);
        ((GeneralPath) shape).curveTo(265.643, 222.91699, 265.526, 223.189,
                265.29, 223.34499);
        ((GeneralPath) shape).curveTo(265.056, 223.49998, 264.643, 223.57799,
                264.052, 223.57799);
        ((GeneralPath) shape).curveTo(263.366, 223.57799, 262.915, 223.45299,
                262.701, 223.20099);
        ((GeneralPath) shape).curveTo(262.487, 222.95099, 262.37997, 222.42198,
                262.37997, 221.61699);
        ((GeneralPath) shape).curveTo(262.37997, 220.87299, 262.48697,
                220.37299, 262.70297, 220.11699);
        ((GeneralPath) shape).curveTo(262.91797, 219.86299, 263.34097, 219.734,
                263.96997, 219.734);
        ((GeneralPath) shape).curveTo(264.65698, 219.734, 265.10498, 219.84299,
                265.31897, 220.066);
        ((GeneralPath) shape).curveTo(265.53098, 220.28699, 265.63898, 220.754,
                265.63898, 221.465);
        ((GeneralPath) shape).lineTo(265.63898, 221.758);
        ((GeneralPath) shape).lineTo(262.95596, 221.758);
        ((GeneralPath) shape).curveTo(262.95596, 222.345, 263.01895, 222.72,
                263.14496, 222.883);
        ((GeneralPath) shape).curveTo(263.26996, 223.043, 263.56296, 223.125,
                264.02795, 223.125);
        ((GeneralPath) shape).curveTo(264.46594, 223.125, 264.75095, 223.087,
                264.88394, 223.009);
        ((GeneralPath) shape).curveTo(265.01395, 222.933, 265.08093, 222.767,
                265.08093, 222.511);
        ((GeneralPath) shape).lineTo(265.08093, 222.394);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(266.497, 219.788);
        ((GeneralPath) shape).lineTo(267.023, 219.788);
        ((GeneralPath) shape).lineTo(267.00702, 220.29599);
        ((GeneralPath) shape).lineTo(267.023, 220.30798);
        ((GeneralPath) shape).curveTo(267.189, 219.92499, 267.60202, 219.73398,
                268.263, 219.73398);
        ((GeneralPath) shape).curveTo(268.795, 219.73398, 269.156, 219.82797,
                269.345, 220.01498);
        ((GeneralPath) shape).curveTo(269.532, 220.20299, 269.628, 220.56198,
                269.628, 221.09299);
        ((GeneralPath) shape).lineTo(269.628, 223.52298);
        ((GeneralPath) shape).lineTo(269.082, 223.52298);
        ((GeneralPath) shape).lineTo(269.082, 220.99998);
        ((GeneralPath) shape).curveTo(269.082, 220.67998, 269.021, 220.46498,
                268.899, 220.35298);
        ((GeneralPath) shape).curveTo(268.77798, 220.24399, 268.542, 220.18698,
                268.193, 220.18698);
        ((GeneralPath) shape).curveTo(267.427, 220.18698, 267.043, 220.54999,
                267.043, 221.27599);
        ((GeneralPath) shape).lineTo(267.043, 223.52298);
        ((GeneralPath) shape).lineTo(266.497, 223.52298);
        ((GeneralPath) shape).lineTo(266.497, 219.788);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(273.507, 220.765);
        ((GeneralPath) shape).lineTo(272.961, 220.765);
        ((GeneralPath) shape).curveTo(272.961, 220.505, 272.908, 220.343,
                272.803, 220.281);
        ((GeneralPath) shape).curveTo(272.698, 220.218, 272.427, 220.18701,
                271.99002, 220.18701);
        ((GeneralPath) shape).curveTo(271.584, 220.18701, 271.32703, 220.22002,
                271.21802, 220.28702);
        ((GeneralPath) shape).curveTo(271.109, 220.35301, 271.05402, 220.51102,
                271.05402, 220.76602);
        ((GeneralPath) shape).curveTo(271.05402, 221.14902, 271.238, 221.34802,
                271.604, 221.36803);
        ((GeneralPath) shape).lineTo(272.045, 221.39102);
        ((GeneralPath) shape).lineTo(272.60202, 221.41801);
        ((GeneralPath) shape).curveTo(273.27603, 221.45201, 273.61603,
                221.80501, 273.61603, 222.48102);
        ((GeneralPath) shape).curveTo(273.61603, 222.89902, 273.50504,
                223.18802, 273.28104, 223.34402);
        ((GeneralPath) shape).curveTo(273.05905, 223.50003, 272.64905,
                223.57802, 272.05203, 223.57802);
        ((GeneralPath) shape).curveTo(271.44205, 223.57802, 271.02103,
                223.50401, 270.79105, 223.35501);
        ((GeneralPath) shape).curveTo(270.56104, 223.20601, 270.44604,
                222.93501, 270.44604, 222.53902);
        ((GeneralPath) shape).lineTo(270.45004, 222.33601);
        ((GeneralPath) shape).lineTo(271.01505, 222.33601);
        ((GeneralPath) shape).lineTo(271.01904, 222.51102);
        ((GeneralPath) shape).curveTo(271.01904, 222.75601, 271.08105,
                222.92001, 271.20605, 223.00102);
        ((GeneralPath) shape).curveTo(271.33105, 223.08401, 271.57706,
                223.12502, 271.94305, 223.12502);
        ((GeneralPath) shape).curveTo(272.39105, 223.12502, 272.68805,
                223.08202, 272.83005, 222.99602);
        ((GeneralPath) shape).curveTo(272.97104, 222.91101, 273.04306,
                222.73102, 273.04306, 222.45702);
        ((GeneralPath) shape).curveTo(273.04306, 222.06401, 272.86505,
                221.86801, 272.50906, 221.86801);
        ((GeneralPath) shape).curveTo(271.68005, 221.86801, 271.13406, 221.798,
                270.87308, 221.65701);
        ((GeneralPath) shape).curveTo(270.6121, 221.51602, 270.48108, 221.225,
                270.48108, 220.78201);
        ((GeneralPath) shape).curveTo(270.48108, 220.36401, 270.58408, 220.085,
                270.79108, 219.94402);
        ((GeneralPath) shape).curveTo(270.99808, 219.80501, 271.4091,
                219.73502, 272.0251, 219.73502);
        ((GeneralPath) shape).curveTo(273.01108, 219.73502, 273.50607,
                220.03201, 273.50607, 220.63002);
        ((GeneralPath) shape).lineTo(273.50607, 220.765);
        g.setPaint(paint);
        g.fill(shape);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(275.943, 220.187);
        ((GeneralPath) shape).curveTo(275.46, 220.187, 275.16098, 220.265,
                275.04498, 220.42299);
        ((GeneralPath) shape).curveTo(274.93, 220.579, 274.87097, 220.99199,
                274.87097, 221.65599);
        ((GeneralPath) shape).curveTo(274.87097, 222.31999, 274.92798, 222.73,
                275.04498, 222.88799);
        ((GeneralPath) shape).curveTo(275.15997, 223.04399, 275.46, 223.12398,
                275.943, 223.12398);
        ((GeneralPath) shape).curveTo(276.42798, 223.12398, 276.729, 223.04597,
                276.846, 222.88799);
        ((GeneralPath) shape).curveTo(276.961, 222.73198, 277.019, 222.31999,
                277.019, 221.65599);
        ((GeneralPath) shape).curveTo(277.019, 220.99199, 276.962, 220.581,
                276.846, 220.42299);
        ((GeneralPath) shape).curveTo(276.731, 220.266, 276.431, 220.187,
                275.943, 220.187);
        ((GeneralPath) shape).moveTo(275.943, 219.733);
        ((GeneralPath) shape).curveTo(276.63098, 219.733, 277.078, 219.852,
                277.284, 220.092);
        ((GeneralPath) shape).curveTo(277.48898, 220.33, 277.592, 220.851,
                277.592, 221.655);
        ((GeneralPath) shape).curveTo(277.592, 222.455, 277.49002, 222.977,
                277.284, 223.218);
        ((GeneralPath) shape).curveTo(277.079, 223.45601, 276.633, 223.577,
                275.943, 223.577);
        ((GeneralPath) shape).curveTo(275.257, 223.577, 274.81198, 223.458,
                274.606, 223.218);
        ((GeneralPath) shape).curveTo(274.401, 222.98, 274.29797, 222.45801,
                274.29797, 221.655);
        ((GeneralPath) shape).curveTo(274.29797, 220.854, 274.399, 220.333,
                274.606, 220.092);
        ((GeneralPath) shape).curveTo(274.811, 219.854, 275.257, 219.733,
                275.943, 219.733);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(278.499, 219.788);
        ((GeneralPath) shape).lineTo(279.04498, 219.788);
        ((GeneralPath) shape).lineTo(278.99, 220.21799);
        ((GeneralPath) shape).lineTo(279.00198, 220.22998);
        ((GeneralPath) shape).curveTo(279.21597, 219.87798, 279.573, 219.70299,
                280.06998, 219.70299);
        ((GeneralPath) shape).curveTo(280.75598, 219.70299, 281.09897,
                220.05598, 281.09897, 220.76599);
        ((GeneralPath) shape).lineTo(281.09497, 221.02399);
        ((GeneralPath) shape).lineTo(280.55698, 221.02399);
        ((GeneralPath) shape).lineTo(280.56897, 220.93);
        ((GeneralPath) shape).curveTo(280.57697, 220.83199, 280.58096,
                220.76599, 280.58096, 220.73099);
        ((GeneralPath) shape).curveTo(280.58096, 220.34799, 280.37396,
                220.15698, 279.95697, 220.15698);
        ((GeneralPath) shape).curveTo(279.34897, 220.15698, 279.04398,
                220.53198, 279.04398, 221.28598);
        ((GeneralPath) shape).lineTo(279.04398, 223.52399);
        ((GeneralPath) shape).lineTo(278.498, 223.52399);
        ((GeneralPath) shape).lineTo(278.498, 219.788);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(287.959, 218.19);
        ((GeneralPath) shape).lineTo(287.959, 223.522);
        ((GeneralPath) shape).lineTo(287.359, 223.522);
        ((GeneralPath) shape).lineTo(287.359, 221.05);
        ((GeneralPath) shape).lineTo(284.248, 221.05);
        ((GeneralPath) shape).lineTo(284.248, 223.522);
        ((GeneralPath) shape).lineTo(283.647, 223.522);
        ((GeneralPath) shape).lineTo(283.647, 218.19);
        ((GeneralPath) shape).lineTo(284.248, 218.19);
        ((GeneralPath) shape).lineTo(284.248, 220.542);
        ((GeneralPath) shape).lineTo(287.359, 220.542);
        ((GeneralPath) shape).lineTo(287.359, 218.19);
        ((GeneralPath) shape).lineTo(287.959, 218.19);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(289.493, 219.788);
        ((GeneralPath) shape).lineTo(289.493, 223.52199);
        ((GeneralPath) shape).lineTo(288.94702, 223.52199);
        ((GeneralPath) shape).lineTo(288.94702, 219.788);
        ((GeneralPath) shape).lineTo(289.493, 219.788);
        ((GeneralPath) shape).moveTo(289.493, 218.19);
        ((GeneralPath) shape).lineTo(289.493, 218.804);
        ((GeneralPath) shape).lineTo(288.94702, 218.804);
        ((GeneralPath) shape).lineTo(288.94702, 218.19);
        ((GeneralPath) shape).lineTo(289.493, 218.19);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(292.425, 219.788);
        ((GeneralPath) shape).lineTo(292.425, 220.241);
        ((GeneralPath) shape).lineTo(290.99, 220.241);
        ((GeneralPath) shape).lineTo(290.99, 222.526);
        ((GeneralPath) shape).curveTo(290.99, 222.925, 291.16498, 223.12401,
                291.52, 223.12401);
        ((GeneralPath) shape).curveTo(291.871, 223.12401, 292.046, 222.94601,
                292.046, 222.589);
        ((GeneralPath) shape).lineTo(292.05, 222.405);
        ((GeneralPath) shape).lineTo(292.05798, 222.198);
        ((GeneralPath) shape).lineTo(292.56497, 222.198);
        ((GeneralPath) shape).lineTo(292.56897, 222.47499);
        ((GeneralPath) shape).curveTo(292.56897, 223.20898, 292.222, 223.577,
                291.52396, 223.577);
        ((GeneralPath) shape).curveTo(290.80496, 223.577, 290.44397, 223.272,
                290.44397, 222.659);
        ((GeneralPath) shape).lineTo(290.44397, 220.241);
        ((GeneralPath) shape).lineTo(289.92896, 220.241);
        ((GeneralPath) shape).lineTo(289.92896, 219.788);
        ((GeneralPath) shape).lineTo(290.44397, 219.788);
        ((GeneralPath) shape).lineTo(290.44397, 218.89);
        ((GeneralPath) shape).lineTo(290.98996, 218.89);
        ((GeneralPath) shape).lineTo(290.98996, 219.788);
        ((GeneralPath) shape).lineTo(292.425, 219.788);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(295.941, 220.765);
        ((GeneralPath) shape).lineTo(295.39502, 220.765);
        ((GeneralPath) shape).curveTo(295.39502, 220.505, 295.342, 220.343,
                295.23703, 220.281);
        ((GeneralPath) shape).curveTo(295.13202, 220.218, 294.86102, 220.18701,
                294.42404, 220.18701);
        ((GeneralPath) shape).curveTo(294.01804, 220.18701, 293.76105,
                220.22002, 293.65204, 220.28702);
        ((GeneralPath) shape).curveTo(293.54303, 220.35301, 293.48804,
                220.51102, 293.48804, 220.76602);
        ((GeneralPath) shape).curveTo(293.48804, 221.14902, 293.67203,
                221.34802, 294.03802, 221.36803);
        ((GeneralPath) shape).lineTo(294.47903, 221.39102);
        ((GeneralPath) shape).lineTo(295.03604, 221.41801);
        ((GeneralPath) shape).curveTo(295.71005, 221.45201, 296.05005,
                221.80501, 296.05005, 222.48102);
        ((GeneralPath) shape).curveTo(296.05005, 222.89902, 295.93906,
                223.18802, 295.71506, 223.34402);
        ((GeneralPath) shape).curveTo(295.49307, 223.50003, 295.08307,
                223.57802, 294.48605, 223.57802);
        ((GeneralPath) shape).curveTo(293.87607, 223.57802, 293.45505,
                223.50401, 293.22507, 223.35501);
        ((GeneralPath) shape).curveTo(292.99506, 223.20601, 292.88007,
                222.93501, 292.88007, 222.53902);
        ((GeneralPath) shape).lineTo(292.88406, 222.33601);
        ((GeneralPath) shape).lineTo(293.44907, 222.33601);
        ((GeneralPath) shape).lineTo(293.45306, 222.51102);
        ((GeneralPath) shape).curveTo(293.45306, 222.75601, 293.51508,
                222.92001, 293.64008, 223.00102);
        ((GeneralPath) shape).curveTo(293.76508, 223.08401, 294.01108,
                223.12502, 294.37708, 223.12502);
        ((GeneralPath) shape).curveTo(294.82507, 223.12502, 295.12207,
                223.08202, 295.26407, 222.99602);
        ((GeneralPath) shape).curveTo(295.40506, 222.91101, 295.47708,
                222.73102, 295.47708, 222.45702);
        ((GeneralPath) shape).curveTo(295.47708, 222.06401, 295.30008,
                221.86801, 294.94308, 221.86801);
        ((GeneralPath) shape).curveTo(294.11407, 221.86801, 293.56808, 221.798,
                293.3071, 221.65701);
        ((GeneralPath) shape).curveTo(293.0461, 221.51602, 292.9151, 221.225,
                292.9151, 220.78201);
        ((GeneralPath) shape).curveTo(292.9151, 220.36401, 293.0181, 220.085,
                293.2251, 219.94402);
        ((GeneralPath) shape).curveTo(293.4321, 219.80501, 293.8431, 219.73502,
                294.4591, 219.73502);
        ((GeneralPath) shape).curveTo(295.4451, 219.73502, 295.9401, 220.03201,
                295.9401, 220.63002);
        ((GeneralPath) shape).lineTo(295.9401, 220.765);
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
        paint = new Color(35, 31, 32, 255);
        stroke = new BasicStroke(1.0f, 0, 0, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(350.804, 224.103);
        ((GeneralPath) shape).curveTo(350.804, 224.854, 350.19498, 225.46199,
                349.44098, 225.46199);
        ((GeneralPath) shape).lineTo(342.52097, 225.46199);
        ((GeneralPath) shape).curveTo(341.76996, 225.46199, 341.15897,
                224.85399, 341.15897, 224.103);
        ((GeneralPath) shape).lineTo(341.15897, 217.109);
        ((GeneralPath) shape).curveTo(341.15897, 216.35799, 341.76996, 215.749,
                342.52097, 215.749);
        ((GeneralPath) shape).lineTo(349.44098, 215.749);
        ((GeneralPath) shape).curveTo(350.19498, 215.749, 350.804, 216.357,
                350.804, 217.109);
        ((GeneralPath) shape).lineTo(350.804, 224.103);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(343.478, 222.688);
        ((GeneralPath) shape).lineTo(343.478, 221.296);
        ((GeneralPath) shape).lineTo(342.103, 221.296);
        ((GeneralPath) shape).lineTo(342.103, 220.852);
        ((GeneralPath) shape).lineTo(343.478, 220.852);
        ((GeneralPath) shape).lineTo(343.478, 219.454);
        ((GeneralPath) shape).lineTo(343.955, 219.454);
        ((GeneralPath) shape).lineTo(343.955, 220.852);
        ((GeneralPath) shape).lineTo(345.33, 220.852);
        ((GeneralPath) shape).lineTo(345.33, 221.296);
        ((GeneralPath) shape).lineTo(343.955, 221.296);
        ((GeneralPath) shape).lineTo(343.955, 222.688);
        ((GeneralPath) shape).lineTo(343.478, 222.688);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(348.618, 218.74);
        ((GeneralPath) shape).lineTo(348.618, 223.405);
        ((GeneralPath) shape).lineTo(348.093, 223.405);
        ((GeneralPath) shape).lineTo(348.093, 219.133);
        ((GeneralPath) shape).lineTo(346.901, 220.456);
        ((GeneralPath) shape).lineTo(346.571, 220.141);
        ((GeneralPath) shape).lineTo(347.86, 218.74);
        ((GeneralPath) shape).lineTo(348.618, 218.74);
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
        paint = new Color(35, 31, 32, 255);
        stroke = new BasicStroke(1.0f, 0, 0, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(362.896, 224.103);
        ((GeneralPath) shape).curveTo(362.896, 224.854, 362.287, 225.46199,
                361.534, 225.46199);
        ((GeneralPath) shape).lineTo(354.612, 225.46199);
        ((GeneralPath) shape).curveTo(353.86, 225.46199, 353.252, 224.85399,
                353.252, 224.103);
        ((GeneralPath) shape).lineTo(353.252, 217.108);
        ((GeneralPath) shape).curveTo(353.252, 216.357, 353.86002, 215.74901,
                354.612, 215.74901);
        ((GeneralPath) shape).lineTo(361.534, 215.74901);
        ((GeneralPath) shape).curveTo(362.287, 215.74901, 362.896, 216.35701,
                362.896, 217.108);
        ((GeneralPath) shape).lineTo(362.896, 224.103);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(355.571, 222.688);
        ((GeneralPath) shape).lineTo(355.571, 221.296);
        ((GeneralPath) shape).lineTo(354.196, 221.296);
        ((GeneralPath) shape).lineTo(354.196, 220.852);
        ((GeneralPath) shape).lineTo(355.571, 220.852);
        ((GeneralPath) shape).lineTo(355.571, 219.454);
        ((GeneralPath) shape).lineTo(356.049, 219.454);
        ((GeneralPath) shape).lineTo(356.049, 220.852);
        ((GeneralPath) shape).lineTo(357.424, 220.852);
        ((GeneralPath) shape).lineTo(357.424, 221.296);
        ((GeneralPath) shape).lineTo(356.049, 221.296);
        ((GeneralPath) shape).lineTo(356.049, 222.688);
        ((GeneralPath) shape).lineTo(355.571, 222.688);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(361.681, 222.961);
        ((GeneralPath) shape).lineTo(361.681, 223.405);
        ((GeneralPath) shape).lineTo(358.327, 223.405);
        ((GeneralPath) shape).lineTo(358.327, 222.527);
        ((GeneralPath) shape).curveTo(358.327, 222.03499, 358.423, 221.71399,
                358.61398, 221.56);
        ((GeneralPath) shape).curveTo(358.804, 221.408, 359.25897, 221.29,
                359.97397, 221.208);
        ((GeneralPath) shape).curveTo(360.54697, 221.14499, 360.89398, 221.058,
                361.01297, 220.948);
        ((GeneralPath) shape).curveTo(361.13196, 220.838, 361.19196, 220.555,
                361.19196, 220.097);
        ((GeneralPath) shape).curveTo(361.19196, 219.697, 361.12595, 219.435,
                360.99295, 219.316);
        ((GeneralPath) shape).curveTo(360.85995, 219.196, 360.56995, 219.136,
                360.12094, 219.136);
        ((GeneralPath) shape).curveTo(359.56094, 219.136, 359.20895, 219.184,
                359.06195, 219.282);
        ((GeneralPath) shape).curveTo(358.91595, 219.37799, 358.84094, 219.612,
                358.84094, 219.981);
        ((GeneralPath) shape).lineTo(358.84894, 220.33);
        ((GeneralPath) shape).lineTo(358.33295, 220.33);
        ((GeneralPath) shape).lineTo(358.33694, 220.087);
        ((GeneralPath) shape).curveTo(358.33694, 219.53, 358.45395, 219.15701,
                358.68994, 218.97101);
        ((GeneralPath) shape).curveTo(358.92493, 218.78601, 359.39594,
                218.69301, 360.10394, 218.69301);
        ((GeneralPath) shape).curveTo(360.73193, 218.69301, 361.15295, 218.792,
                361.36993, 218.994);
        ((GeneralPath) shape).curveTo(361.58493, 219.194, 361.69394, 219.585,
                361.69394, 220.166);
        ((GeneralPath) shape).curveTo(361.69394, 220.723, 361.59293, 221.097,
                361.39194, 221.286);
        ((GeneralPath) shape).curveTo(361.19095, 221.474, 360.75293, 221.59999,
                360.07993, 221.66699);
        ((GeneralPath) shape).curveTo(359.48892, 221.72499, 359.13693,
                221.80699, 359.0219, 221.913);
        ((GeneralPath) shape).curveTo(358.9089, 222.017, 358.85092, 222.314,
                358.85092, 222.805);
        ((GeneralPath) shape).lineTo(358.85092, 222.96199);
        ((GeneralPath) shape).lineTo(361.681, 222.96199);
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
        paint = new Color(35, 31, 32, 255);
        stroke = new BasicStroke(1.0f, 0, 0, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(374.987, 224.103);
        ((GeneralPath) shape).curveTo(374.987, 224.854, 374.379, 225.46199,
                373.627, 225.46199);
        ((GeneralPath) shape).lineTo(366.70502, 225.46199);
        ((GeneralPath) shape).curveTo(365.953, 225.46199, 365.34503, 224.85399,
                365.34503, 224.103);
        ((GeneralPath) shape).lineTo(365.34503, 217.109);
        ((GeneralPath) shape).curveTo(365.34503, 216.35799, 365.95303, 215.749,
                366.70502, 215.749);
        ((GeneralPath) shape).lineTo(373.627, 215.749);
        ((GeneralPath) shape).curveTo(374.37903, 215.749, 374.987, 216.357,
                374.987, 217.109);
        ((GeneralPath) shape).lineTo(374.987, 224.103);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(367.662, 222.688);
        ((GeneralPath) shape).lineTo(367.662, 221.296);
        ((GeneralPath) shape).lineTo(366.288, 221.296);
        ((GeneralPath) shape).lineTo(366.288, 220.852);
        ((GeneralPath) shape).lineTo(367.662, 220.852);
        ((GeneralPath) shape).lineTo(367.662, 219.454);
        ((GeneralPath) shape).lineTo(368.14, 219.454);
        ((GeneralPath) shape).lineTo(368.14, 220.852);
        ((GeneralPath) shape).lineTo(369.515, 220.852);
        ((GeneralPath) shape).lineTo(369.515, 221.296);
        ((GeneralPath) shape).lineTo(368.14, 221.296);
        ((GeneralPath) shape).lineTo(368.14, 222.688);
        ((GeneralPath) shape).lineTo(367.662, 222.688);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(371.746, 221.242);
        ((GeneralPath) shape).lineTo(371.746, 220.794);
        ((GeneralPath) shape).lineTo(371.923, 220.79701);
        ((GeneralPath) shape).curveTo(372.551, 220.79701, 372.935, 220.75801,
                373.074, 220.67702);
        ((GeneralPath) shape).curveTo(373.21402, 220.59802, 373.284, 220.38002,
                373.284, 220.02402);
        ((GeneralPath) shape).curveTo(373.284, 219.61401, 373.224, 219.36401,
                373.10098, 219.27202);
        ((GeneralPath) shape).curveTo(372.97998, 219.18102, 372.64398,
                219.13503, 372.093, 219.13503);
        ((GeneralPath) shape).curveTo(371.586, 219.13503, 371.27298, 219.17903,
                371.15298, 219.26802);
        ((GeneralPath) shape).curveTo(371.034, 219.35703, 370.974, 219.58902,
                370.974, 219.96902);
        ((GeneralPath) shape).lineTo(370.974, 220.17102);
        ((GeneralPath) shape).lineTo(370.476, 220.17102);
        ((GeneralPath) shape).lineTo(370.479, 219.98602);
        ((GeneralPath) shape).curveTo(370.479, 219.45302, 370.582, 219.10402,
                370.79, 218.93903);
        ((GeneralPath) shape).curveTo(370.997, 218.77502, 371.43402, 218.69102,
                372.101, 218.69102);
        ((GeneralPath) shape).curveTo(372.785, 218.69102, 373.237, 218.76802,
                373.457, 218.92102);
        ((GeneralPath) shape).curveTo(373.675, 219.07503, 373.786, 219.39102,
                373.786, 219.87302);
        ((GeneralPath) shape).curveTo(373.786, 220.28001, 373.734, 220.56001,
                373.629, 220.71002);
        ((GeneralPath) shape).curveTo(373.525, 220.86002, 373.316, 220.95502,
                373.001, 220.99402);
        ((GeneralPath) shape).lineTo(373.001, 221.01802);
        ((GeneralPath) shape).curveTo(373.353, 221.06602, 373.586, 221.16202,
                373.702, 221.30502);
        ((GeneralPath) shape).curveTo(373.816, 221.44902, 373.874, 221.71503,
                373.874, 222.10503);
        ((GeneralPath) shape).curveTo(373.874, 222.64703, 373.764, 223.00702,
                373.538, 223.18503);
        ((GeneralPath) shape).curveTo(373.315, 223.36302, 372.863, 223.45203,
                372.18298, 223.45203);
        ((GeneralPath) shape).curveTo(371.44998, 223.45203, 370.968, 223.37003,
                370.74, 223.20602);
        ((GeneralPath) shape).curveTo(370.513, 223.04202, 370.399, 222.69502,
                370.399, 222.16402);
        ((GeneralPath) shape).lineTo(370.399, 221.88702);
        ((GeneralPath) shape).lineTo(370.91098, 221.88702);
        ((GeneralPath) shape).lineTo(370.91098, 222.15703);
        ((GeneralPath) shape).curveTo(370.91098, 222.55302, 370.977, 222.79503,
                371.11197, 222.88004);
        ((GeneralPath) shape).curveTo(371.24496, 222.96603, 371.62698,
                223.00804, 372.25497, 223.00804);
        ((GeneralPath) shape).curveTo(372.74097, 223.00804, 373.05, 222.95505,
                373.17996, 222.84505);
        ((GeneralPath) shape).curveTo(373.30997, 222.73805, 373.37396,
                222.47804, 373.37396, 222.06805);
        ((GeneralPath) shape).curveTo(373.37396, 221.72406, 373.31195,
                221.50105, 373.18896, 221.39606);
        ((GeneralPath) shape).curveTo(373.06598, 221.29306, 372.79996,
                221.24106, 372.38696, 221.24106);
        ((GeneralPath) shape).lineTo(371.746, 221.24106);
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
        paint = new Color(35, 31, 32, 255);
        stroke = new BasicStroke(1.0f, 0, 0, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(387.08, 224.103);
        ((GeneralPath) shape).curveTo(387.08, 224.854, 386.47098, 225.46199,
                385.719, 225.46199);
        ((GeneralPath) shape).lineTo(378.797, 225.46199);
        ((GeneralPath) shape).curveTo(378.04498, 225.46199, 377.436, 224.85399,
                377.436, 224.103);
        ((GeneralPath) shape).lineTo(377.436, 217.109);
        ((GeneralPath) shape).curveTo(377.436, 216.35799, 378.045, 215.749,
                378.797, 215.749);
        ((GeneralPath) shape).lineTo(385.719, 215.749);
        ((GeneralPath) shape).curveTo(386.471, 215.749, 387.08, 216.357,
                387.08, 217.109);
        ((GeneralPath) shape).lineTo(387.08, 224.103);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(380.879, 222.961);
        ((GeneralPath) shape).lineTo(382.397, 222.961);
        ((GeneralPath) shape).curveTo(382.954, 222.961, 383.313, 222.85,
                383.475, 222.62599);
        ((GeneralPath) shape).curveTo(383.635, 222.40399, 383.717, 221.905,
                383.717, 221.13199);
        ((GeneralPath) shape).curveTo(383.717, 220.28398, 383.648, 219.74799,
                383.50702, 219.52199);
        ((GeneralPath) shape).curveTo(383.367, 219.29599, 383.03302, 219.18399,
                382.50302, 219.18399);
        ((GeneralPath) shape).lineTo(380.87903, 219.18399);
        ((GeneralPath) shape).lineTo(380.87903, 222.961);
        ((GeneralPath) shape).moveTo(380.354, 223.405);
        ((GeneralPath) shape).lineTo(380.354, 218.739);
        ((GeneralPath) shape).lineTo(382.51, 218.739);
        ((GeneralPath) shape).curveTo(383.17502, 218.739, 383.627, 218.886,
                383.864, 219.18);
        ((GeneralPath) shape).curveTo(384.099, 219.474, 384.21802, 220.036,
                384.21802, 220.868);
        ((GeneralPath) shape).curveTo(384.21802, 221.87999, 384.114, 222.555,
                383.903, 222.89499);
        ((GeneralPath) shape).curveTo(383.69302, 223.23299, 383.273, 223.40399,
                382.64203, 223.40399);
        ((GeneralPath) shape).lineTo(380.354, 223.40399);
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
        paint = new Color(35, 31, 32, 255);
        stroke = new BasicStroke(1.0f, 0, 0, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(295.806, 267.229);
        ((GeneralPath) shape).curveTo(295.806, 267.98, 295.195, 268.589,
                294.443, 268.589);
        ((GeneralPath) shape).lineTo(287.52298, 268.589);
        ((GeneralPath) shape).curveTo(286.77097, 268.589, 286.16098, 267.981,
                286.16098, 267.229);
        ((GeneralPath) shape).lineTo(286.16098, 260.23502);
        ((GeneralPath) shape).curveTo(286.16098, 259.484, 286.77, 258.87402,
                287.52298, 258.87402);
        ((GeneralPath) shape).lineTo(294.443, 258.87402);
        ((GeneralPath) shape).curveTo(295.195, 258.87402, 295.806, 259.484,
                295.806, 260.23502);
        ((GeneralPath) shape).lineTo(295.806, 267.229);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(258.643, 263.397);
        ((GeneralPath) shape).lineTo(260.378, 263.397);
        ((GeneralPath) shape).curveTo(260.835, 263.397, 261.146, 263.329,
                261.314, 263.188);
        ((GeneralPath) shape).curveTo(261.47998, 263.04898, 261.564, 262.787,
                261.564, 262.401);
        ((GeneralPath) shape).curveTo(261.564, 261.921, 261.503, 261.606,
                261.379, 261.459);
        ((GeneralPath) shape).curveTo(261.256, 261.315, 260.991, 261.24103,
                260.586, 261.24103);
        ((GeneralPath) shape).lineTo(258.644, 261.24103);
        ((GeneralPath) shape).lineTo(258.644, 263.397);
        ((GeneralPath) shape).moveTo(258.043, 266.065);
        ((GeneralPath) shape).lineTo(258.043, 260.732);
        ((GeneralPath) shape).lineTo(260.577, 260.732);
        ((GeneralPath) shape).curveTo(261.148, 260.732, 261.552, 260.846,
                261.785, 261.072);
        ((GeneralPath) shape).curveTo(262.018, 261.29797, 262.13602, 261.69098,
                262.13602, 262.25198);
        ((GeneralPath) shape).curveTo(262.13602, 262.744, 262.07303, 263.086,
                261.945, 263.28098);
        ((GeneralPath) shape).curveTo(261.819, 263.47397, 261.575, 263.59897,
                261.216, 263.658);
        ((GeneralPath) shape).lineTo(261.216, 263.66998);
        ((GeneralPath) shape).curveTo(261.779, 263.71097, 262.062, 264.055,
                262.062, 264.701);
        ((GeneralPath) shape).lineTo(262.062, 266.064);
        ((GeneralPath) shape).lineTo(261.461, 266.064);
        ((GeneralPath) shape).lineTo(261.461, 264.837);
        ((GeneralPath) shape).curveTo(261.461, 264.216, 261.192, 263.903,
                260.654, 263.903);
        ((GeneralPath) shape).lineTo(258.642, 263.903);
        ((GeneralPath) shape).lineTo(258.642, 266.06403);
        ((GeneralPath) shape).lineTo(258.043, 266.06403);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(265.583, 263.874);
        ((GeneralPath) shape).lineTo(265.579, 263.699);
        ((GeneralPath) shape).curveTo(265.579, 263.297, 265.513, 263.03302,
                265.38, 262.91202);
        ((GeneralPath) shape).curveTo(265.247, 262.79102, 264.963, 262.73,
                264.522, 262.73);
        ((GeneralPath) shape).curveTo(264.082, 262.73, 263.795, 262.80002,
                263.663, 262.94302);
        ((GeneralPath) shape).curveTo(263.53198, 263.084, 263.466, 263.394,
                263.466, 263.87402);
        ((GeneralPath) shape).lineTo(265.583, 263.87402);
        ((GeneralPath) shape).moveTo(265.583, 264.937);
        ((GeneralPath) shape).lineTo(266.14102, 264.937);
        ((GeneralPath) shape).lineTo(266.14502, 265.074);
        ((GeneralPath) shape).curveTo(266.14502, 265.46, 266.028, 265.732,
                265.79202, 265.888);
        ((GeneralPath) shape).curveTo(265.558, 266.042, 265.14502, 266.12,
                264.55402, 266.12);
        ((GeneralPath) shape).curveTo(263.868, 266.12, 263.41702, 265.995,
                263.203, 265.74298);
        ((GeneralPath) shape).curveTo(262.989, 265.49298, 262.882, 264.964,
                262.882, 264.15897);
        ((GeneralPath) shape).curveTo(262.882, 263.41498, 262.98898, 262.91498,
                263.205, 262.65897);
        ((GeneralPath) shape).curveTo(263.41998, 262.40497, 263.843, 262.27597,
                264.472, 262.27597);
        ((GeneralPath) shape).curveTo(265.159, 262.27597, 265.607, 262.38498,
                265.82098, 262.60797);
        ((GeneralPath) shape).curveTo(266.033, 262.82898, 266.141, 263.29596,
                266.141, 264.00696);
        ((GeneralPath) shape).lineTo(266.141, 264.29996);
        ((GeneralPath) shape).lineTo(263.45798, 264.29996);
        ((GeneralPath) shape).curveTo(263.45798, 264.88797, 263.52097,
                265.26297, 263.64697, 265.42496);
        ((GeneralPath) shape).curveTo(263.77197, 265.58496, 264.06598,
                265.66696, 264.52997, 265.66696);
        ((GeneralPath) shape).curveTo(264.96796, 265.66696, 265.25296,
                265.62997, 265.38596, 265.55197);
        ((GeneralPath) shape).curveTo(265.51596, 265.47598, 265.58295,
                265.30997, 265.58295, 265.05298);
        ((GeneralPath) shape).lineTo(265.58295, 264.937);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(268.445, 264.218);
        ((GeneralPath) shape).curveTo(268.028, 264.218, 267.755, 264.261,
                267.62802, 264.35098);
        ((GeneralPath) shape).curveTo(267.50302, 264.439, 267.43903, 264.628,
                267.43903, 264.921);
        ((GeneralPath) shape).curveTo(267.43903, 265.222, 267.502, 265.423,
                267.627, 265.521);
        ((GeneralPath) shape).curveTo(267.752, 265.619, 268.005, 265.667,
                268.38702, 265.667);
        ((GeneralPath) shape).curveTo(269.15503, 265.667, 269.54102, 265.43298,
                269.54102, 264.964);
        ((GeneralPath) shape).curveTo(269.54102, 264.671, 269.467, 264.472,
                269.31702, 264.37);
        ((GeneralPath) shape).curveTo(269.168, 264.269, 268.877, 264.218,
                268.445, 264.218);
        ((GeneralPath) shape).moveTo(267.568, 263.378);
        ((GeneralPath) shape).lineTo(267.026, 263.378);
        ((GeneralPath) shape).curveTo(267.026, 262.944, 267.124, 262.651,
                267.32, 262.50098);
        ((GeneralPath) shape).curveTo(267.515, 262.35297, 267.89902, 262.27597,
                268.47202, 262.27597);
        ((GeneralPath) shape).curveTo(269.092, 262.27597, 269.513, 262.36798,
                269.73102, 262.55096);
        ((GeneralPath) shape).curveTo(269.95, 262.73495, 270.05902, 263.08395,
                270.05902, 263.59998);
        ((GeneralPath) shape).lineTo(270.05902, 266.06497);
        ((GeneralPath) shape).lineTo(269.51303, 266.06497);
        ((GeneralPath) shape).lineTo(269.55603, 265.66196);
        ((GeneralPath) shape).lineTo(269.54404, 265.65897);
        ((GeneralPath) shape).curveTo(269.33704, 265.96597, 268.91403,
                266.11996, 268.27704, 266.11996);
        ((GeneralPath) shape).curveTo(267.33804, 266.11996, 266.86505,
                265.74097, 266.86505, 264.98297);
        ((GeneralPath) shape).curveTo(266.86505, 264.53397, 266.97006, 264.222,
                267.18106, 264.04996);
        ((GeneralPath) shape).curveTo(267.39206, 263.87793, 267.77206,
                263.79196, 268.32407, 263.79196);
        ((GeneralPath) shape).curveTo(268.97906, 263.79196, 269.37106,
                263.92096, 269.50107, 264.17795);
        ((GeneralPath) shape).lineTo(269.51306, 264.17496);
        ((GeneralPath) shape).lineTo(269.51306, 263.72195);
        ((GeneralPath) shape).curveTo(269.51306, 263.29596, 269.45505,
                263.01895, 269.33807, 262.89197);
        ((GeneralPath) shape).curveTo(269.22107, 262.76697, 268.96606,
                262.70297, 268.57007, 262.70297);
        ((GeneralPath) shape).curveTo(267.90005, 262.70297, 267.56406,
                262.88998, 267.56406, 263.26898);
        ((GeneralPath) shape).curveTo(267.564, 263.286, 267.564, 263.323,
                267.568, 263.378);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(271.125, 262.331);
        ((GeneralPath) shape).lineTo(271.67, 262.331);
        ((GeneralPath) shape).lineTo(271.61603, 262.761);
        ((GeneralPath) shape).lineTo(271.627, 262.77298);
        ((GeneralPath) shape).curveTo(271.841, 262.421, 272.19803, 262.24597,
                272.695, 262.24597);
        ((GeneralPath) shape).curveTo(273.38202, 262.24597, 273.724, 262.59998,
                273.724, 263.30896);
        ((GeneralPath) shape).lineTo(273.72, 263.56696);
        ((GeneralPath) shape).lineTo(273.182, 263.56696);
        ((GeneralPath) shape).lineTo(273.194, 263.47296);
        ((GeneralPath) shape).curveTo(273.202, 263.37497, 273.206, 263.30896,
                273.206, 263.27396);
        ((GeneralPath) shape).curveTo(273.206, 262.89096, 272.999, 262.69995,
                272.582, 262.69995);
        ((GeneralPath) shape).curveTo(271.974, 262.69995, 271.669, 263.07495,
                271.669, 263.82895);
        ((GeneralPath) shape).lineTo(271.669, 266.06696);
        ((GeneralPath) shape).lineTo(271.124, 266.06696);
        ((GeneralPath) shape).lineTo(271.124, 262.331);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(408.358, 360.571);
        ((GeneralPath) shape).lineTo(409.898, 360.571);
        ((GeneralPath) shape).lineTo(409.898, 359.708);
        ((GeneralPath) shape).lineTo(405.834, 359.708);
        ((GeneralPath) shape).lineTo(405.834, 360.571);
        ((GeneralPath) shape).lineTo(407.374, 360.571);
        ((GeneralPath) shape).lineTo(407.374, 364.968);
        ((GeneralPath) shape).lineTo(408.358, 364.968);
        ((GeneralPath) shape).lineTo(408.358, 360.571);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(413.671, 364.968);
        ((GeneralPath) shape).lineTo(413.671, 361.24197);
        ((GeneralPath) shape).lineTo(412.796, 361.24197);
        ((GeneralPath) shape).lineTo(412.796, 363.37897);
        ((GeneralPath) shape).curveTo(412.796, 364.02097, 412.57498, 364.30997,
                411.888, 364.30997);
        ((GeneralPath) shape).curveTo(411.314, 364.30997, 411.265, 364.10596,
                411.265, 363.57697);
        ((GeneralPath) shape).lineTo(411.265, 361.24197);
        ((GeneralPath) shape).lineTo(410.39, 361.24197);
        ((GeneralPath) shape).lineTo(410.39, 363.90796);
        ((GeneralPath) shape).curveTo(410.39, 364.71497, 410.88202, 364.96796,
                411.61902, 364.96796);
        ((GeneralPath) shape).curveTo(412.13, 364.96796, 412.592, 364.85095,
                412.79703, 364.37497);
        ((GeneralPath) shape).lineTo(412.79703, 364.96796);
        ((GeneralPath) shape).lineTo(413.671, 364.96796);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(414.431, 361.242);
        ((GeneralPath) shape).lineTo(414.431, 364.96802);
        ((GeneralPath) shape).lineTo(415.306, 364.96802);
        ((GeneralPath) shape).lineTo(415.306, 362.69403);
        ((GeneralPath) shape).curveTo(415.306, 362.21603, 415.468, 361.91504,
                416.026, 361.91504);
        ((GeneralPath) shape).curveTo(416.47202, 361.91504, 416.509, 362.13303,
                416.509, 362.50403);
        ((GeneralPath) shape).lineTo(416.509, 362.69403);
        ((GeneralPath) shape).lineTo(417.384, 362.69403);
        ((GeneralPath) shape).lineTo(417.384, 362.4);
        ((GeneralPath) shape).curveTo(417.384, 361.705, 417.182, 361.242,
                416.357, 361.242);
        ((GeneralPath) shape).curveTo(415.899, 361.242, 415.495, 361.359,
                415.306, 361.758);
        ((GeneralPath) shape).lineTo(415.306, 361.242);
        ((GeneralPath) shape).lineTo(414.431, 361.242);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(417.728, 361.242);
        ((GeneralPath) shape).lineTo(417.728, 364.96802);
        ((GeneralPath) shape).lineTo(418.603, 364.96802);
        ((GeneralPath) shape).lineTo(418.603, 362.69403);
        ((GeneralPath) shape).curveTo(418.603, 362.21603, 418.766, 361.91504,
                419.323, 361.91504);
        ((GeneralPath) shape).curveTo(419.769, 361.91504, 419.806, 362.13303,
                419.806, 362.50403);
        ((GeneralPath) shape).lineTo(419.806, 362.69403);
        ((GeneralPath) shape).lineTo(420.681, 362.69403);
        ((GeneralPath) shape).lineTo(420.681, 362.4);
        ((GeneralPath) shape).curveTo(420.681, 361.705, 420.48, 361.242,
                419.655, 361.242);
        ((GeneralPath) shape).curveTo(419.197, 361.242, 418.792, 361.359,
                418.603, 361.758);
        ((GeneralPath) shape).lineTo(418.603, 361.242);
        ((GeneralPath) shape).lineTo(417.728, 361.242);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(421.9, 362.776);
        ((GeneralPath) shape).curveTo(421.9, 362.086, 421.949, 361.915, 422.71,
                361.915);
        ((GeneralPath) shape).curveTo(423.43, 361.915, 423.541, 361.975,
                423.541, 362.776);
        ((GeneralPath) shape).lineTo(421.9, 362.776);
        ((GeneralPath) shape).moveTo(423.541, 363.8);
        ((GeneralPath) shape).curveTo(423.541, 364.31, 423.19897, 364.31,
                422.71, 364.31);
        ((GeneralPath) shape).curveTo(421.921, 364.31, 421.9, 364.072, 421.9,
                363.324);
        ((GeneralPath) shape).lineTo(424.416, 363.324);
        ((GeneralPath) shape).curveTo(424.416, 361.691, 424.214, 361.242,
                422.71, 361.242);
        ((GeneralPath) shape).curveTo(421.233, 361.242, 421.025, 361.829,
                421.025, 363.174);
        ((GeneralPath) shape).curveTo(421.025, 364.53802, 421.31, 364.96802,
                422.71, 364.96802);
        ((GeneralPath) shape).curveTo(423.754, 364.96802, 424.416, 364.91302,
                424.416, 363.80002);
        ((GeneralPath) shape).lineTo(423.541, 363.80002);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(424.663, 361.9);
        ((GeneralPath) shape).lineTo(425.125, 361.9);
        ((GeneralPath) shape).lineTo(425.125, 363.819);
        ((GeneralPath) shape).curveTo(425.125, 364.745, 425.422, 364.968,
                426.399, 364.968);
        ((GeneralPath) shape).curveTo(427.358, 364.968, 427.63998, 364.633,
                427.63998, 363.521);
        ((GeneralPath) shape).lineTo(426.874, 363.521);
        ((GeneralPath) shape).curveTo(426.874, 363.911, 426.929, 364.31,
                426.39798, 364.31);
        ((GeneralPath) shape).curveTo(425.999, 364.31, 425.999, 364.154,
                425.999, 363.812);
        ((GeneralPath) shape).lineTo(425.999, 361.9);
        ((GeneralPath) shape).lineTo(427.453, 361.9);
        ((GeneralPath) shape).lineTo(427.453, 361.242);
        ((GeneralPath) shape).lineTo(426.0, 361.242);
        ((GeneralPath) shape).lineTo(426.0, 360.391);
        ((GeneralPath) shape).lineTo(425.125, 360.391);
        ((GeneralPath) shape).lineTo(425.125, 361.242);
        ((GeneralPath) shape).lineTo(424.663, 361.242);
        ((GeneralPath) shape).lineTo(424.663, 361.9);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(433.56, 363.215);
        ((GeneralPath) shape).lineTo(431.738, 363.215);
        ((GeneralPath) shape).lineTo(432.639, 360.481);
        ((GeneralPath) shape).lineTo(432.653, 360.481);
        ((GeneralPath) shape).lineTo(433.56, 363.215);
        ((GeneralPath) shape).moveTo(433.785, 363.982);
        ((GeneralPath) shape).lineTo(434.13, 364.968);
        ((GeneralPath) shape).lineTo(435.157, 364.968);
        ((GeneralPath) shape).lineTo(433.37003, 359.70798);
        ((GeneralPath) shape).lineTo(431.87802, 359.70798);
        ((GeneralPath) shape).lineTo(430.126, 364.968);
        ((GeneralPath) shape).lineTo(431.17502, 364.968);
        ((GeneralPath) shape).lineTo(431.505, 363.982);
        ((GeneralPath) shape).lineTo(433.785, 363.982);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(435.417, 361.242);
        ((GeneralPath) shape).lineTo(435.417, 364.96802);
        ((GeneralPath) shape).lineTo(436.292, 364.96802);
        ((GeneralPath) shape).lineTo(436.292, 362.69403);
        ((GeneralPath) shape).curveTo(436.292, 362.21603, 436.455, 361.91504,
                437.012, 361.91504);
        ((GeneralPath) shape).curveTo(437.458, 361.91504, 437.495, 362.13303,
                437.495, 362.50403);
        ((GeneralPath) shape).lineTo(437.495, 362.69403);
        ((GeneralPath) shape).lineTo(438.37, 362.69403);
        ((GeneralPath) shape).lineTo(438.37, 362.4);
        ((GeneralPath) shape).curveTo(438.37, 361.705, 438.169, 361.242,
                437.344, 361.242);
        ((GeneralPath) shape).curveTo(436.886, 361.242, 436.481, 361.359,
                436.292, 361.758);
        ((GeneralPath) shape).lineTo(436.292, 361.242);
        ((GeneralPath) shape).lineTo(435.417, 361.242);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(438.823, 361.242);
        ((GeneralPath) shape).lineTo(438.823, 364.96802);
        ((GeneralPath) shape).lineTo(439.698, 364.96802);
        ((GeneralPath) shape).lineTo(439.698, 362.89102);
        ((GeneralPath) shape).curveTo(439.698, 362.239, 439.846, 361.915,
                440.6, 361.915);
        ((GeneralPath) shape).curveTo(441.111, 361.915, 441.229, 362.099,
                441.229, 362.561);
        ((GeneralPath) shape).lineTo(441.229, 364.969);
        ((GeneralPath) shape).lineTo(442.104, 364.969);
        ((GeneralPath) shape).lineTo(442.104, 362.892);
        ((GeneralPath) shape).curveTo(442.104, 362.24, 442.241, 361.916,
                442.94202, 361.916);
        ((GeneralPath) shape).curveTo(443.41702, 361.916, 443.52603, 362.09998,
                443.52603, 362.56198);
        ((GeneralPath) shape).lineTo(443.52603, 364.96997);
        ((GeneralPath) shape).lineTo(444.40103, 364.96997);
        ((GeneralPath) shape).lineTo(444.40103, 362.47797);
        ((GeneralPath) shape).curveTo(444.40103, 361.57397, 444.09503,
                361.24396, 443.16904, 361.24396);
        ((GeneralPath) shape).curveTo(442.68805, 361.24396, 442.19302,
                361.38495, 442.02704, 361.89496);
        ((GeneralPath) shape).lineTo(442.0, 361.89496);
        ((GeneralPath) shape).curveTo(441.902, 361.36197, 441.376, 361.24396,
                440.918, 361.24396);
        ((GeneralPath) shape).curveTo(440.43, 361.24396, 439.905, 361.34796,
                439.698, 361.81195);
        ((GeneralPath) shape).lineTo(439.698, 361.24396);
        ((GeneralPath) shape).lineTo(438.823, 361.24396);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(446.878, 361.915);
        ((GeneralPath) shape).curveTo(447.637, 361.915, 447.698, 362.138,
                447.698, 363.12402);
        ((GeneralPath) shape).curveTo(447.698, 364.09503, 447.636, 364.31104,
                446.878, 364.31104);
        ((GeneralPath) shape).curveTo(446.12, 364.31104, 446.05798, 364.09503,
                446.05798, 363.12402);
        ((GeneralPath) shape).curveTo(446.058, 362.138, 446.119, 361.915,
                446.878, 361.915);
        ((GeneralPath) shape).moveTo(446.878, 361.242);
        ((GeneralPath) shape).curveTo(445.374, 361.242, 445.18298, 361.696,
                445.18298, 363.117);
        ((GeneralPath) shape).curveTo(445.18298, 364.519, 445.374, 364.96802,
                446.878, 364.96802);
        ((GeneralPath) shape).curveTo(448.382, 364.96802, 448.573, 364.519,
                448.573, 363.117);
        ((GeneralPath) shape).curveTo(448.573, 361.696, 448.382, 361.242,
                446.878, 361.242);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(449.256, 361.242);
        ((GeneralPath) shape).lineTo(449.256, 364.96802);
        ((GeneralPath) shape).lineTo(450.131, 364.96802);
        ((GeneralPath) shape).lineTo(450.131, 362.69403);
        ((GeneralPath) shape).curveTo(450.131, 362.21603, 450.294, 361.91504,
                450.851, 361.91504);
        ((GeneralPath) shape).curveTo(451.29703, 361.91504, 451.334, 362.13303,
                451.334, 362.50403);
        ((GeneralPath) shape).lineTo(451.334, 362.69403);
        ((GeneralPath) shape).lineTo(452.209, 362.69403);
        ((GeneralPath) shape).lineTo(452.209, 362.4);
        ((GeneralPath) shape).curveTo(452.209, 361.705, 452.00803, 361.242,
                451.183, 361.242);
        ((GeneralPath) shape).curveTo(450.725, 361.242, 450.32, 361.359,
                450.131, 361.758);
        ((GeneralPath) shape).lineTo(450.131, 361.242);
        ((GeneralPath) shape).lineTo(449.256, 361.242);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(571.332, 350.792);
        ((GeneralPath) shape).curveTo(571.332, 349.022, 569.93396, 347.58798,
                568.21295, 347.58798);
        ((GeneralPath) shape).lineTo(526.808, 347.58798);
        ((GeneralPath) shape).curveTo(525.084, 347.58798, 523.688, 349.02197,
                523.688, 350.792);
        ((GeneralPath) shape).lineTo(523.688, 371.612);
        ((GeneralPath) shape).curveTo(523.688, 373.381, 525.084, 374.815,
                526.808, 374.815);
        ((GeneralPath) shape).lineTo(568.213, 374.815);
        ((GeneralPath) shape).curveTo(569.934, 374.815, 571.33203, 373.381,
                571.33203, 371.612);
        ((GeneralPath) shape).lineTo(571.33203, 350.792);
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
        paint = new Color(35, 31, 32, 255);
        stroke = new BasicStroke(1.945f, 0, 0, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(571.332, 350.792);
        ((GeneralPath) shape).curveTo(571.332, 349.02298, 569.933, 347.58798,
                568.21295, 347.58798);
        ((GeneralPath) shape).lineTo(526.808, 347.58798);
        ((GeneralPath) shape).curveTo(525.084, 347.58798, 523.688, 349.02298,
                523.688, 350.792);
        ((GeneralPath) shape).lineTo(523.688, 371.61298);
        ((GeneralPath) shape).curveTo(523.688, 373.38098, 525.084, 374.81497,
                526.808, 374.81497);
        ((GeneralPath) shape).lineTo(568.213, 374.81497);
        ((GeneralPath) shape).curveTo(569.933, 374.81497, 571.33203, 373.38098,
                571.33203, 371.61298);
        ((GeneralPath) shape).lineTo(571.33203, 350.792);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(530.113, 365.924);
        ((GeneralPath) shape).curveTo(529.953, 367.669, 529.141, 368.44202,
                527.69495, 368.44202);
        ((GeneralPath) shape).curveTo(526.16797, 368.44202, 525.33594,
                367.64902, 525.21497, 365.924);
        ((GeneralPath) shape).curveTo(525.13696, 364.892, 525.09796, 363.86102,
                525.09796, 362.811);
        ((GeneralPath) shape).curveTo(525.09796, 362.353, 525.09796, 361.897,
                525.11694, 361.44202);
        ((GeneralPath) shape).curveTo(525.11694, 361.44202, 525.13794,
                357.95203, 525.21594, 356.98102);
        ((GeneralPath) shape).curveTo(525.3369, 355.256, 526.16895, 354.44302,
                527.6959, 354.44302);
        ((GeneralPath) shape).curveTo(529.1419, 354.44302, 529.9539, 355.256,
                530.11395, 356.98102);
        ((GeneralPath) shape).curveTo(530.134, 357.299, 530.15295, 358.50803,
                530.15295, 359.00403);
        ((GeneralPath) shape).lineTo(528.60596, 359.00403);
        ((GeneralPath) shape).curveTo(528.60596, 358.48804, 528.56696,
                357.25903, 528.54694, 356.94104);
        ((GeneralPath) shape).curveTo(528.46893, 356.16705, 528.20996,
                355.83105, 527.69495, 355.83105);
        ((GeneralPath) shape).curveTo(527.238, 355.83105, 526.9799, 356.18704,
                526.8989, 356.94104);
        ((GeneralPath) shape).curveTo(526.80194, 357.91302, 526.80194,
                361.44205, 526.80194, 361.44205);
        ((GeneralPath) shape).curveTo(526.78094, 362.01706, 526.78094,
                362.61105, 526.78094, 363.20605);
        ((GeneralPath) shape).curveTo(526.78094, 364.13806, 526.80194,
                365.09006, 526.89795, 365.98306);
        ((GeneralPath) shape).curveTo(526.97894, 366.73706, 527.23694,
                367.09406, 527.694, 367.09406);
        ((GeneralPath) shape).curveTo(528.209, 367.09406, 528.467, 366.73807,
                528.54596, 365.98306);
        ((GeneralPath) shape).curveTo(528.566, 365.66507, 528.605, 364.35706,
                528.605, 363.84207);
        ((GeneralPath) shape).lineTo(530.152, 363.84207);
        ((GeneralPath) shape).curveTo(530.152, 364.16, 530.133, 365.625,
                530.113, 365.924);
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
        paint2(g, clip_, defaultTransform_, alpha__0, clip__0,
                defaultTransform__0, alpha__0_141, clip__0_141,
                defaultTransform__0_141);

    }

    private static void paint2(Graphics2D g, Shape clip_,
            AffineTransform defaultTransform_, float alpha__0, Shape clip__0,
            AffineTransform defaultTransform__0, float alpha__0_141,
            Shape clip__0_141, AffineTransform defaultTransform__0_141) {
        Shape shape;
        Paint paint;
        Stroke stroke;
        float origAlpha;
        // _0_141 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(534.551, 368.284);
        ((GeneralPath) shape).lineTo(534.551, 362.85098);
        ((GeneralPath) shape).lineTo(532.827, 362.85098);
        ((GeneralPath) shape).lineTo(532.827, 368.284);
        ((GeneralPath) shape).lineTo(531.14, 368.284);
        ((GeneralPath) shape).lineTo(531.14, 360.055);
        ((GeneralPath) shape).curveTo(531.14, 360.055, 531.14, 358.15198,
                531.219, 357.27798);
        ((GeneralPath) shape).curveTo(531.358, 355.714, 532.527, 354.60098,
                534.036, 354.60098);
        ((GeneralPath) shape).lineTo(536.275, 354.60098);
        ((GeneralPath) shape).lineTo(536.275, 368.284);
        ((GeneralPath) shape).lineTo(534.551, 368.284);
        ((GeneralPath) shape).moveTo(534.592, 356.029);
        ((GeneralPath) shape).lineTo(534.037, 356.029);
        ((GeneralPath) shape).curveTo(533.579, 356.029, 532.98596, 356.347,
                532.90497, 357.041);
        ((GeneralPath) shape).curveTo(532.82697, 357.65598, 532.82697,
                358.84598, 532.82697, 359.45798);
        ((GeneralPath) shape).lineTo(532.82697, 361.46298);
        ((GeneralPath) shape).lineTo(534.592, 361.46298);
        ((GeneralPath) shape).lineTo(534.592, 356.029);
        ((GeneralPath) shape).closePath();
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(545.501, 368.284);
        ((GeneralPath) shape).lineTo(545.501, 362.85098);
        ((GeneralPath) shape).lineTo(543.776, 362.85098);
        ((GeneralPath) shape).lineTo(543.776, 368.284);
        ((GeneralPath) shape).lineTo(542.09, 368.284);
        ((GeneralPath) shape).lineTo(542.09, 360.055);
        ((GeneralPath) shape).curveTo(542.09, 360.055, 542.09, 358.15198,
                542.17004, 357.27798);
        ((GeneralPath) shape).curveTo(542.30804, 355.714, 543.48004, 354.60098,
                544.98505, 354.60098);
        ((GeneralPath) shape).lineTo(547.22504, 354.60098);
        ((GeneralPath) shape).lineTo(547.22504, 368.284);
        ((GeneralPath) shape).lineTo(545.501, 368.284);
        ((GeneralPath) shape).moveTo(545.542, 356.029);
        ((GeneralPath) shape).lineTo(544.98596, 356.029);
        ((GeneralPath) shape).curveTo(544.52997, 356.029, 543.93396, 356.347,
                543.855, 357.041);
        ((GeneralPath) shape).curveTo(543.776, 357.65598, 543.776, 358.84598,
                543.776, 359.45798);
        ((GeneralPath) shape).lineTo(543.776, 361.46298);
        ((GeneralPath) shape).lineTo(545.542, 361.46298);
        ((GeneralPath) shape).lineTo(545.542, 356.029);
        ((GeneralPath) shape).closePath();
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(539.826, 356.029);
        ((GeneralPath) shape).lineTo(539.826, 366.063);
        ((GeneralPath) shape).curveTo(539.826, 366.757, 540.26196, 367.052,
                540.69696, 367.052);
        ((GeneralPath) shape).lineTo(541.13495, 367.052);
        ((GeneralPath) shape).lineTo(541.13495, 368.423);
        ((GeneralPath) shape).lineTo(540.69696, 368.423);
        ((GeneralPath) shape).curveTo(539.152, 368.423, 538.121, 367.749,
                538.121, 366.202);
        ((GeneralPath) shape).lineTo(538.121, 356.03);
        ((GeneralPath) shape).lineTo(536.19696, 356.03);
        ((GeneralPath) shape).lineTo(536.19696, 354.602);
        ((GeneralPath) shape).lineTo(541.769, 354.602);
        ((GeneralPath) shape).lineTo(541.769, 356.03);
        ((GeneralPath) shape).lineTo(539.826, 356.03);
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(561.201, 368.442);
        ((GeneralPath) shape).curveTo(559.735, 368.442, 558.685, 367.629,
                558.685, 365.88498);
        ((GeneralPath) shape).lineTo(558.685, 363.843);
        ((GeneralPath) shape).lineTo(560.249, 363.843);
        ((GeneralPath) shape).lineTo(560.249, 365.964);
        ((GeneralPath) shape).curveTo(560.249, 366.758, 560.705, 367.076,
                561.2, 367.076);
        ((GeneralPath) shape).curveTo(561.656, 367.076, 562.112, 366.758,
                562.112, 365.964);
        ((GeneralPath) shape).lineTo(562.112, 364.241);
        ((GeneralPath) shape).curveTo(562.112, 362.138, 558.74, 361.027,
                558.74, 358.449);
        ((GeneralPath) shape).lineTo(558.74, 356.982);
        ((GeneralPath) shape).curveTo(558.74, 355.237, 559.714, 354.444,
                561.24097, 354.444);
        ((GeneralPath) shape).curveTo(562.68695, 354.444, 563.63995, 355.237,
                563.63995, 356.982);
        ((GeneralPath) shape).lineTo(563.63995, 358.628);
        ((GeneralPath) shape).lineTo(562.09296, 358.628);
        ((GeneralPath) shape).lineTo(562.09296, 356.942);
        ((GeneralPath) shape).curveTo(562.09296, 356.168, 561.73694, 355.832,
                561.2399, 355.832);
        ((GeneralPath) shape).curveTo(560.78394, 355.832, 560.4459, 356.168,
                560.4459, 356.942);
        ((GeneralPath) shape).lineTo(560.4459, 358.43);
        ((GeneralPath) shape).curveTo(560.4459, 360.055, 563.79694, 361.603,
                563.79694, 364.179);
        ((GeneralPath) shape).lineTo(563.79694, 365.886);
        ((GeneralPath) shape).curveTo(563.799, 367.628, 562.748, 368.442,
                561.201, 368.442);
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(567.724, 356.029);
        ((GeneralPath) shape).lineTo(567.724, 366.063);
        ((GeneralPath) shape).curveTo(567.724, 366.757, 568.161, 367.052,
                568.595, 367.052);
        ((GeneralPath) shape).lineTo(569.03094, 367.052);
        ((GeneralPath) shape).lineTo(569.03094, 368.423);
        ((GeneralPath) shape).lineTo(568.595, 368.423);
        ((GeneralPath) shape).curveTo(567.04895, 368.423, 566.01794, 367.749,
                566.01794, 366.202);
        ((GeneralPath) shape).lineTo(566.01794, 356.03);
        ((GeneralPath) shape).lineTo(564.095, 356.03);
        ((GeneralPath) shape).lineTo(564.095, 354.602);
        ((GeneralPath) shape).lineTo(569.66595, 354.602);
        ((GeneralPath) shape).lineTo(569.66595, 356.03);
        ((GeneralPath) shape).lineTo(567.724, 356.03);
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(545.566, 371.316);
        ((GeneralPath) shape).curveTo(545.566, 371.742, 545.48, 372.047,
                545.266, 372.239);
        ((GeneralPath) shape).curveTo(545.054, 372.427, 544.761, 372.484,
                544.482, 372.484);
        ((GeneralPath) shape).curveTo(544.222, 372.484, 543.945, 372.433,
                543.772, 372.321);
        ((GeneralPath) shape).lineTo(543.884, 371.93503);
        ((GeneralPath) shape).curveTo(544.01196, 372.00903, 544.23, 372.09003,
                544.477, 372.09003);
        ((GeneralPath) shape).curveTo(544.812, 372.09003, 545.062, 371.91403,
                545.062, 371.47403);
        ((GeneralPath) shape).lineTo(545.062, 371.29904);
        ((GeneralPath) shape).lineTo(545.054, 371.29904);
        ((GeneralPath) shape).curveTo(544.935, 371.47803, 544.727, 371.59903,
                544.461, 371.59903);
        ((GeneralPath) shape).curveTo(543.954, 371.59903, 543.596, 371.18204,
                543.596, 370.60602);
        ((GeneralPath) shape).curveTo(543.596, 369.937, 544.031, 369.532,
                544.519, 369.532);
        ((GeneralPath) shape).curveTo(544.829, 369.532, 545.01196, 369.683,
                545.108, 369.849);
        ((GeneralPath) shape).lineTo(545.115, 369.849);
        ((GeneralPath) shape).lineTo(545.138, 369.578);
        ((GeneralPath) shape).lineTo(545.584, 369.578);
        ((GeneralPath) shape).curveTo(545.575, 369.715, 545.565, 369.883,
                545.565, 370.163);
        ((GeneralPath) shape).lineTo(545.565, 371.316);
        ((GeneralPath) shape).moveTo(545.055, 370.392);
        ((GeneralPath) shape).curveTo(545.055, 370.344, 545.05, 370.296,
                545.037, 370.254);
        ((GeneralPath) shape).curveTo(544.983, 370.066, 544.836, 369.924,
                544.61896, 369.924);
        ((GeneralPath) shape).curveTo(544.33093, 369.924, 544.11694, 370.174,
                544.11694, 370.588);
        ((GeneralPath) shape).curveTo(544.11694, 370.935, 544.29395, 371.216,
                544.6149, 371.216);
        ((GeneralPath) shape).curveTo(544.8079, 371.216, 544.9729, 371.09,
                545.03394, 370.906);
        ((GeneralPath) shape).curveTo(545.0449, 370.851, 545.05493, 370.78,
                545.05493, 370.723);
        ((GeneralPath) shape).lineTo(545.05493, 370.392);
        ((GeneralPath) shape).closePath();
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(547.532, 371.124);
        ((GeneralPath) shape).curveTo(547.532, 371.309, 547.54, 371.486,
                547.561, 371.611);
        ((GeneralPath) shape).lineTo(547.09595, 371.611);
        ((GeneralPath) shape).lineTo(547.0629, 371.38498);
        ((GeneralPath) shape).lineTo(547.0499, 371.38498);
        ((GeneralPath) shape).curveTo(546.9259, 371.54297, 546.71295,
                371.65698, 546.44794, 371.65698);
        ((GeneralPath) shape).curveTo(546.03894, 371.65698, 545.80896, 371.361,
                545.80896, 371.051);
        ((GeneralPath) shape).curveTo(545.80896, 370.537, 546.26495, 370.27798,
                547.0159, 370.283);
        ((GeneralPath) shape).lineTo(547.0159, 370.248);
        ((GeneralPath) shape).curveTo(547.0159, 370.11398, 546.9619, 369.89398,
                546.6019, 369.89398);
        ((GeneralPath) shape).curveTo(546.4019, 369.89398, 546.1939, 369.95697,
                546.05493, 370.04398);
        ((GeneralPath) shape).lineTo(545.95294, 369.70898);
        ((GeneralPath) shape).curveTo(546.10394, 369.61798, 546.36694,
                369.53098, 546.68896, 369.53098);
        ((GeneralPath) shape).curveTo(547.33997, 369.53098, 547.52997,
                369.94296, 547.52997, 370.38498);
        ((GeneralPath) shape).lineTo(547.52997, 371.124);
        ((GeneralPath) shape).moveTo(547.029, 370.616);
        ((GeneralPath) shape).curveTo(546.666, 370.609, 546.32, 370.688,
                546.32, 370.996);
        ((GeneralPath) shape).curveTo(546.32, 371.198, 546.44904, 371.288,
                546.612, 371.288);
        ((GeneralPath) shape).curveTo(546.817, 371.288, 546.968, 371.156,
                547.012, 371.009);
        ((GeneralPath) shape).curveTo(547.026, 370.971, 547.02905, 370.92902,
                547.02905, 370.897);
        ((GeneralPath) shape).lineTo(547.02905, 370.616);
        ((GeneralPath) shape).closePath();
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(547.907, 370.182);
        ((GeneralPath) shape).curveTo(547.907, 369.95, 547.90295, 369.752,
                547.889, 369.577);
        ((GeneralPath) shape).lineTo(548.332, 369.577);
        ((GeneralPath) shape).lineTo(548.35297, 369.87698);
        ((GeneralPath) shape).lineTo(548.36597, 369.87698);
        ((GeneralPath) shape).curveTo(548.46497, 369.71997, 548.649, 369.53098,
                548.99194, 369.53098);
        ((GeneralPath) shape).curveTo(549.26196, 369.53098, 549.46796,
                369.68097, 549.55597, 369.90598);
        ((GeneralPath) shape).lineTo(549.56396, 369.90598);
        ((GeneralPath) shape).curveTo(549.634, 369.79297, 549.71796, 369.70898,
                549.816, 369.64996);
        ((GeneralPath) shape).curveTo(549.928, 369.57196, 550.057, 369.52997,
                550.22595, 369.52997);
        ((GeneralPath) shape).curveTo(550.5629, 369.52997, 550.90594,
                369.75797, 550.90594, 370.41196);
        ((GeneralPath) shape).lineTo(550.90594, 371.61096);
        ((GeneralPath) shape).lineTo(550.40497, 371.61096);
        ((GeneralPath) shape).lineTo(550.40497, 370.48596);
        ((GeneralPath) shape).curveTo(550.40497, 370.14896, 550.28796,
                369.94897, 550.042, 369.94897);
        ((GeneralPath) shape).curveTo(549.86597, 369.94897, 549.735, 370.07297,
                549.682, 370.218);
        ((GeneralPath) shape).curveTo(549.67, 370.271, 549.659, 370.332,
                549.659, 370.392);
        ((GeneralPath) shape).lineTo(549.659, 371.611);
        ((GeneralPath) shape).lineTo(549.157, 371.611);
        ((GeneralPath) shape).lineTo(549.157, 370.434);
        ((GeneralPath) shape).curveTo(549.157, 370.14798, 549.044, 369.949,
                548.805, 369.949);
        ((GeneralPath) shape).curveTo(548.615, 369.949, 548.484, 370.098,
                548.438, 370.242);
        ((GeneralPath) shape).curveTo(548.417, 370.29, 548.409, 370.35,
                548.409, 370.407);
        ((GeneralPath) shape).lineTo(548.409, 371.612);
        ((GeneralPath) shape).lineTo(547.907, 371.612);
        ((GeneralPath) shape).lineTo(547.907, 370.182);
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(551.646, 370.739);
        ((GeneralPath) shape).curveTo(551.66, 371.108, 551.949, 371.265,
                552.275, 371.265);
        ((GeneralPath) shape).curveTo(552.51105, 371.265, 552.68304, 371.23203,
                552.83905, 371.174);
        ((GeneralPath) shape).lineTo(552.913, 371.53);
        ((GeneralPath) shape).curveTo(552.73804, 371.599, 552.494, 371.657,
                552.203, 371.657);
        ((GeneralPath) shape).curveTo(551.543, 371.657, 551.152, 371.24902,
                551.152, 370.626);
        ((GeneralPath) shape).curveTo(551.152, 370.062, 551.496, 369.53302,
                552.147, 369.53302);
        ((GeneralPath) shape).curveTo(552.811, 369.53302, 553.024, 370.075,
                553.024, 370.52203);
        ((GeneralPath) shape).curveTo(553.024, 370.61703, 553.01697, 370.69205,
                553.00696, 370.73904);
        ((GeneralPath) shape).lineTo(551.646, 370.73904);
        ((GeneralPath) shape).moveTo(552.54, 370.379);
        ((GeneralPath) shape).curveTo(552.545, 370.191, 552.462, 369.881,
                552.11896, 369.881);
        ((GeneralPath) shape).curveTo(551.80194, 369.881, 551.66895, 370.17,
                551.64496, 370.379);
        ((GeneralPath) shape).lineTo(552.54, 370.379);
        ((GeneralPath) shape).closePath();
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
        paint = new Color(255, 255, 255, 255);
        shape = new Rectangle2D.Double(556.760986328125, 368.6449890136719,
                0.5189999938011169, 2.9679999351501465);
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(559.248, 371.124);
        ((GeneralPath) shape).curveTo(559.248, 371.309, 559.255, 371.486,
                559.27496, 371.611);
        ((GeneralPath) shape).lineTo(558.811, 371.611);
        ((GeneralPath) shape).lineTo(558.777, 371.38498);
        ((GeneralPath) shape).lineTo(558.766, 371.38498);
        ((GeneralPath) shape).curveTo(558.641, 371.54297, 558.428, 371.65698,
                558.162, 371.65698);
        ((GeneralPath) shape).curveTo(557.753, 371.65698, 557.522, 371.361,
                557.522, 371.051);
        ((GeneralPath) shape).curveTo(557.522, 370.537, 557.97894, 370.27798,
                558.73096, 370.283);
        ((GeneralPath) shape).lineTo(558.73096, 370.248);
        ((GeneralPath) shape).curveTo(558.73096, 370.11398, 558.678, 369.89398,
                558.31793, 369.89398);
        ((GeneralPath) shape).curveTo(558.1179, 369.89398, 557.9099, 369.95697,
                557.7719, 370.04398);
        ((GeneralPath) shape).lineTo(557.6689, 369.70898);
        ((GeneralPath) shape).curveTo(557.8199, 369.61798, 558.0829, 369.53098,
                558.4059, 369.53098);
        ((GeneralPath) shape).curveTo(559.0559, 369.53098, 559.2459, 369.94296,
                559.2459, 370.38498);
        ((GeneralPath) shape).lineTo(559.2459, 371.124);
        ((GeneralPath) shape).moveTo(558.745, 370.616);
        ((GeneralPath) shape).curveTo(558.381, 370.609, 558.036, 370.688,
                558.036, 370.996);
        ((GeneralPath) shape).curveTo(558.036, 371.198, 558.163, 371.288,
                558.327, 371.288);
        ((GeneralPath) shape).curveTo(558.53, 371.288, 558.68304, 371.156,
                558.728, 371.009);
        ((GeneralPath) shape).curveTo(558.739, 370.971, 558.74506, 370.92902,
                558.74506, 370.897);
        ((GeneralPath) shape).lineTo(558.74506, 370.616);
        ((GeneralPath) shape).closePath();
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(559.621, 368.645);
        ((GeneralPath) shape).lineTo(560.136, 368.645);
        ((GeneralPath) shape).lineTo(560.136, 369.858);
        ((GeneralPath) shape).lineTo(560.14496, 369.858);
        ((GeneralPath) shape).curveTo(560.272, 369.66, 560.49194, 369.533,
                560.79694, 369.533);
        ((GeneralPath) shape).curveTo(561.29193, 369.533, 561.6489, 369.944,
                561.64496, 370.564);
        ((GeneralPath) shape).curveTo(561.64496, 371.29498, 561.18195, 371.659,
                560.722, 371.659);
        ((GeneralPath) shape).curveTo(560.459, 371.659, 560.224, 371.55798,
                560.07697, 371.309);
        ((GeneralPath) shape).lineTo(560.06995, 371.309);
        ((GeneralPath) shape).lineTo(560.04395, 371.612);
        ((GeneralPath) shape).lineTo(559.60596, 371.612);
        ((GeneralPath) shape).curveTo(559.6149, 371.474, 559.62195, 371.248,
                559.62195, 371.04);
        ((GeneralPath) shape).lineTo(559.62195, 368.645);
        ((GeneralPath) shape).moveTo(560.136, 370.772);
        ((GeneralPath) shape).curveTo(560.136, 370.814, 560.14, 370.85602,
                560.151, 370.892);
        ((GeneralPath) shape).curveTo(560.203, 371.097, 560.383, 371.25198,
                560.606, 371.25198);
        ((GeneralPath) shape).curveTo(560.927, 371.25198, 561.12305, 370.99298,
                561.12305, 370.58398);
        ((GeneralPath) shape).curveTo(561.12305, 370.224, 560.952, 369.934,
                560.6091, 369.934);
        ((GeneralPath) shape).curveTo(560.40106, 369.934, 560.21106, 370.08298,
                560.1531, 370.309);
        ((GeneralPath) shape).curveTo(560.1451, 370.34598, 560.13605, 370.393,
                560.13605, 370.442);
        ((GeneralPath) shape).lineTo(560.13605, 370.772);
        ((GeneralPath) shape).closePath();
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(561.875, 371.14);
        ((GeneralPath) shape).curveTo(561.992, 371.212, 562.213, 371.285,
                562.396, 371.285);
        ((GeneralPath) shape).curveTo(562.623, 371.285, 562.72, 371.194,
                562.72, 371.062);
        ((GeneralPath) shape).curveTo(562.72, 370.923, 562.63696, 370.851,
                562.386, 370.763);
        ((GeneralPath) shape).curveTo(561.99097, 370.626, 561.823, 370.408,
                561.82697, 370.17);
        ((GeneralPath) shape).curveTo(561.82697, 369.811, 562.12494, 369.532,
                562.59796, 369.532);
        ((GeneralPath) shape).curveTo(562.82196, 369.532, 563.01794, 369.59003,
                563.134, 369.652);
        ((GeneralPath) shape).lineTo(563.03296, 370.018);
        ((GeneralPath) shape).curveTo(562.94794, 369.965, 562.78296, 369.89902,
                562.60394, 369.89902);
        ((GeneralPath) shape).curveTo(562.4199, 369.89902, 562.319, 369.98502,
                562.319, 370.11102);
        ((GeneralPath) shape).curveTo(562.319, 370.24203, 562.417, 370.30402,
                562.675, 370.39502);
        ((GeneralPath) shape).curveTo(563.04297, 370.528, 563.213, 370.717,
                563.217, 371.019);
        ((GeneralPath) shape).curveTo(563.217, 371.385, 562.92896, 371.656,
                562.38995, 371.656);
        ((GeneralPath) shape).curveTo(562.14294, 371.656, 561.92194, 371.595,
                561.77094, 371.51102);
        ((GeneralPath) shape).lineTo(561.875, 371.14);
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(557.551, 357.645);
        ((GeneralPath) shape).lineTo(557.551, 354.61697);
        ((GeneralPath) shape).lineTo(556.479, 354.61697);
        ((GeneralPath) shape).lineTo(555.865, 355.75098);
        ((GeneralPath) shape).lineTo(555.865, 360.41296);
        ((GeneralPath) shape).curveTo(555.865, 360.41296, 555.865, 362.59497,
                555.765, 363.46695);
        ((GeneralPath) shape).curveTo(555.70703, 364.13895, 555.429, 364.49496,
                554.994, 364.49496);
        ((GeneralPath) shape).curveTo(554.53503, 364.49496, 554.28, 364.13898,
                554.198, 363.46695);
        ((GeneralPath) shape).curveTo(554.143, 362.97894, 554.119, 362.08594,
                554.107, 361.38995);
        ((GeneralPath) shape).curveTo(554.101, 360.89294, 554.101, 360.52097,
                554.101, 360.52097);
        ((GeneralPath) shape).lineTo(554.099, 358.84897);
        ((GeneralPath) shape).curveTo(554.099, 358.84897, 553.154, 360.67398,
                552.804, 360.90698);
        ((GeneralPath) shape).curveTo(552.45, 361.14697, 552.489, 361.413,
                552.489, 361.413);
        ((GeneralPath) shape).curveTo(552.489, 361.413, 552.451, 363.09198,
                552.495, 363.554);
        ((GeneralPath) shape).curveTo(552.613, 364.801, 553.12897, 365.637,
                554.141, 365.89398);
        ((GeneralPath) shape).lineTo(554.141, 366.503);
        ((GeneralPath) shape).curveTo(554.136, 366.509, 554.13, 366.513,
                554.125, 366.516);
        ((GeneralPath) shape).lineTo(554.125, 371.916);
        ((GeneralPath) shape).lineTo(555.813, 370.231);
        ((GeneralPath) shape).lineTo(555.813, 370.22598);
        ((GeneralPath) shape).lineTo(555.829, 370.21);
        ((GeneralPath) shape).lineTo(555.829, 365.893);
        ((GeneralPath) shape).curveTo(556.82, 365.63602, 557.355, 364.80002,
                557.53296, 363.553);
        ((GeneralPath) shape).curveTo(557.55396, 362.7, 557.55396, 360.52002,
                557.55396, 360.52002);
        ((GeneralPath) shape).lineTo(557.55396, 357.644);
        ((GeneralPath) shape).lineTo(557.551, 357.644);
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(550.315, 359.958);
        ((GeneralPath) shape).lineTo(548.631, 357.126);
        ((GeneralPath) shape).lineTo(548.631, 368.284);
        ((GeneralPath) shape).lineTo(552.892, 368.284);
        ((GeneralPath) shape).lineTo(552.892, 366.895);
        ((GeneralPath) shape).lineTo(550.315, 366.895);
        ((GeneralPath) shape).lineTo(550.315, 359.958);
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(563.89, 368.857);
        ((GeneralPath) shape).lineTo(563.66, 368.857);
        ((GeneralPath) shape).lineTo(563.66, 368.775);
        ((GeneralPath) shape).lineTo(564.219, 368.775);
        ((GeneralPath) shape).lineTo(564.219, 368.857);
        ((GeneralPath) shape).lineTo(563.989, 368.857);
        ((GeneralPath) shape).lineTo(563.989, 369.529);
        ((GeneralPath) shape).lineTo(563.89, 369.529);
        ((GeneralPath) shape).lineTo(563.89, 368.857);
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(564.935, 369.198);
        ((GeneralPath) shape).curveTo(564.929, 369.094, 564.923, 368.966,
                564.923, 368.873);
        ((GeneralPath) shape).lineTo(564.91895, 368.873);
        ((GeneralPath) shape).curveTo(564.89294, 368.95898, 564.8629, 369.055,
                564.824, 369.15698);
        ((GeneralPath) shape).lineTo(564.691, 369.524);
        ((GeneralPath) shape).lineTo(564.61896, 369.524);
        ((GeneralPath) shape).lineTo(564.49493, 369.16498);
        ((GeneralPath) shape).curveTo(564.45996, 369.05597, 564.4309,
                368.96198, 564.4099, 368.873);
        ((GeneralPath) shape).lineTo(564.40894, 368.873);
        ((GeneralPath) shape).curveTo(564.4069, 368.96597, 564.39996,
                369.09097, 564.3919, 369.205);
        ((GeneralPath) shape).lineTo(564.3719, 369.53);
        ((GeneralPath) shape).lineTo(564.2799, 369.53);
        ((GeneralPath) shape).lineTo(564.33093, 368.775);
        ((GeneralPath) shape).lineTo(564.45593, 368.775);
        ((GeneralPath) shape).lineTo(564.58496, 369.139);
        ((GeneralPath) shape).curveTo(564.61395, 369.23102, 564.639, 369.315,
                564.66, 369.392);
        ((GeneralPath) shape).lineTo(564.66296, 369.392);
        ((GeneralPath) shape).curveTo(564.683, 369.317, 564.709, 369.233,
                564.74194, 369.139);
        ((GeneralPath) shape).lineTo(564.8759, 368.775);
        ((GeneralPath) shape).lineTo(564.99994, 368.775);
        ((GeneralPath) shape).lineTo(565.0479, 369.53);
        ((GeneralPath) shape).lineTo(564.9499, 369.53);
        ((GeneralPath) shape).lineTo(564.935, 369.198);
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
        paint = new Color(199, 200, 202, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(557.246, 349.919);
        ((GeneralPath) shape).curveTo(557.246, 349.919, 556.899, 350.135,
                556.769, 350.315);
        ((GeneralPath) shape).curveTo(556.722, 350.548, 557.029, 350.811,
                556.80896, 350.99802);
        ((GeneralPath) shape).lineTo(554.65497, 352.33102);
        ((GeneralPath) shape).lineTo(548.467, 352.67303);
        ((GeneralPath) shape).curveTo(548.467, 352.67303, 546.958, 352.68704,
                548.05, 354.45105);
        ((GeneralPath) shape).lineTo(550.946, 359.12305);
        ((GeneralPath) shape).curveTo(552.03796, 360.88705, 552.721, 359.54004,
                552.721, 359.54004);
        ((GeneralPath) shape).lineTo(555.782, 354.14905);
        ((GeneralPath) shape).lineTo(557.75, 352.93106);
        ((GeneralPath) shape).lineTo(557.938, 352.81305);
        ((GeneralPath) shape).curveTo(558.203, 352.70004, 558.302, 353.09406,
                558.532, 353.15204);
        ((GeneralPath) shape).curveTo(558.75, 353.11905, 559.09796, 352.90204,
                559.09796, 352.90204);
        ((GeneralPath) shape).lineTo(557.246, 349.919);
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
        paint = new Color(35, 31, 32, 255);
        stroke = new BasicStroke(0.973f, 1, 1, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(557.246, 349.919);
        ((GeneralPath) shape).curveTo(557.246, 349.919, 556.899, 350.135,
                556.769, 350.315);
        ((GeneralPath) shape).curveTo(556.721, 350.546, 557.029, 350.809,
                556.80896, 350.997);
        ((GeneralPath) shape).lineTo(554.65497, 352.33102);
        ((GeneralPath) shape).lineTo(548.467, 352.67303);
        ((GeneralPath) shape).curveTo(548.467, 352.67303, 546.958, 352.68704,
                548.05, 354.45105);
        ((GeneralPath) shape).lineTo(550.945, 359.12305);
        ((GeneralPath) shape).curveTo(552.038, 360.88705, 552.72003, 359.53903,
                552.72003, 359.53903);
        ((GeneralPath) shape).lineTo(555.78204, 354.15002);
        ((GeneralPath) shape).lineTo(557.749, 352.93204);
        ((GeneralPath) shape).lineTo(557.937, 352.81305);
        ((GeneralPath) shape).curveTo(558.202, 352.70004, 558.3, 353.09506,
                558.531, 353.15305);
        ((GeneralPath) shape).curveTo(558.749, 353.11804, 559.097, 352.90305,
                559.097, 352.90305);
        ((GeneralPath) shape).lineTo(557.246, 349.919);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        paint = new Color(35, 31, 32, 255);
        stroke = new BasicStroke(0.486f, 1, 1, 4.0f, null, 0.0f);
        shape = new Line2D.Float(551.513977f, 353.864014f, 550.739990f,
                352.614990f);
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        paint = new Color(35, 31, 32, 255);
        stroke = new BasicStroke(0.486f, 1, 1, 4.0f, null, 0.0f);
        shape = new Line2D.Float(550.304993f, 353.881012f, 549.530029f,
                352.631012f);
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        paint = new Color(35, 31, 32, 255);
        stroke = new BasicStroke(0.486f, 1, 1, 4.0f, null, 0.0f);
        shape = new Line2D.Float(553.934998f, 353.713989f, 553.159973f,
                352.464996f);
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        paint = new Color(35, 31, 32, 255);
        stroke = new BasicStroke(0.486f, 1, 1, 4.0f, null, 0.0f);
        shape = new Line2D.Float(552.724976f, 353.713989f, 551.948975f,
                352.464996f);
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        paint = new Color(35, 31, 32, 255);
        stroke = new BasicStroke(0.486f, 1, 1, 4.0f, null, 0.0f);
        shape = new Line2D.Float(549.094971f, 354.023987f, 548.320007f,
                352.774994f);
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        paint = new Color(204, 206, 207, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(547.049, 73.618);
        ((GeneralPath) shape).lineTo(541.508, 67.778);
        ((GeneralPath) shape).lineTo(533.492, 74.802);
        ((GeneralPath) shape).lineTo(489.223, 74.23);
        ((GeneralPath) shape).lineTo(445.545, 74.23);
        ((GeneralPath) shape).lineTo(437.53, 67.165);
        ((GeneralPath) shape).lineTo(431.936, 73.005);
        ((GeneralPath) shape).lineTo(419.777, 212.411);
        ((GeneralPath) shape).lineTo(419.777, 312.32);
        ((GeneralPath) shape).lineTo(426.611, 336.583);
        ((GeneralPath) shape).lineTo(436.293, 336.583);
        ((GeneralPath) shape).lineTo(436.293, 334.664);
        ((GeneralPath) shape).lineTo(480.454, 334.664);
        ((GeneralPath) shape).lineTo(480.454, 336.583);
        ((GeneralPath) shape).lineTo(497.935, 336.583);
        ((GeneralPath) shape).lineTo(497.935, 334.664);
        ((GeneralPath) shape).lineTo(542.099, 334.664);
        ((GeneralPath) shape).lineTo(542.099, 336.583);
        ((GeneralPath) shape).lineTo(552.265, 336.583);
        ((GeneralPath) shape).lineTo(559.098, 312.32);
        ((GeneralPath) shape).lineTo(559.098, 212.411);
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(544.214, 70.784);
        ((GeneralPath) shape).lineTo(538.672, 64.942);
        ((GeneralPath) shape).lineTo(530.656, 71.968);
        ((GeneralPath) shape).lineTo(486.389, 71.396);
        ((GeneralPath) shape).lineTo(442.71, 71.396);
        ((GeneralPath) shape).lineTo(434.694, 64.33);
        ((GeneralPath) shape).lineTo(429.102, 70.17);
        ((GeneralPath) shape).lineTo(416.943, 209.576);
        ((GeneralPath) shape).lineTo(416.943, 309.485);
        ((GeneralPath) shape).lineTo(423.776, 333.749);
        ((GeneralPath) shape).lineTo(433.458, 333.749);
        ((GeneralPath) shape).lineTo(433.458, 331.831);
        ((GeneralPath) shape).lineTo(477.62, 331.831);
        ((GeneralPath) shape).lineTo(477.62, 333.749);
        ((GeneralPath) shape).lineTo(495.101, 333.749);
        ((GeneralPath) shape).lineTo(495.101, 331.831);
        ((GeneralPath) shape).lineTo(539.264, 331.831);
        ((GeneralPath) shape).lineTo(539.264, 333.749);
        ((GeneralPath) shape).lineTo(549.431, 333.749);
        ((GeneralPath) shape).lineTo(556.262, 309.485);
        ((GeneralPath) shape).lineTo(556.262, 209.576);
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
        paint = new Color(0, 0, 0, 255);
        stroke = new BasicStroke(1.5f, 0, 0, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(544.214, 70.784);
        ((GeneralPath) shape).lineTo(538.672, 64.942);
        ((GeneralPath) shape).lineTo(530.656, 71.968);
        ((GeneralPath) shape).lineTo(486.389, 71.397);
        ((GeneralPath) shape).lineTo(442.71, 71.397);
        ((GeneralPath) shape).lineTo(434.694, 64.33);
        ((GeneralPath) shape).lineTo(429.101, 70.17);
        ((GeneralPath) shape).lineTo(416.943, 209.577);
        ((GeneralPath) shape).lineTo(416.943, 309.485);
        ((GeneralPath) shape).lineTo(423.776, 333.749);
        ((GeneralPath) shape).lineTo(433.458, 333.749);
        ((GeneralPath) shape).lineTo(433.458, 331.831);
        ((GeneralPath) shape).lineTo(477.62, 331.831);
        ((GeneralPath) shape).lineTo(477.62, 333.749);
        ((GeneralPath) shape).lineTo(495.1, 333.749);
        ((GeneralPath) shape).lineTo(495.1, 331.831);
        ((GeneralPath) shape).lineTo(539.264, 331.831);
        ((GeneralPath) shape).lineTo(539.264, 333.749);
        ((GeneralPath) shape).lineTo(549.43, 333.749);
        ((GeneralPath) shape).lineTo(556.262, 309.485);
        ((GeneralPath) shape).lineTo(556.262, 209.577);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        paint = new Color(204, 206, 207, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(463.265, 115.354);
        ((GeneralPath) shape).lineTo(458.422, 115.354);
        ((GeneralPath) shape).lineTo(454.764, 148.572);
        ((GeneralPath) shape).lineTo(454.764, 276.92);
        ((GeneralPath) shape).lineTo(461.301, 289.627);
        ((GeneralPath) shape).lineTo(472.64, 296.771);
        ((GeneralPath) shape).lineTo(502.403, 296.771);
        ((GeneralPath) shape).lineTo(512.913, 289.627);
        ((GeneralPath) shape).lineTo(519.411, 276.92);
        ((GeneralPath) shape).lineTo(519.411, 148.572);
        ((GeneralPath) shape).lineTo(515.754, 115.354);
        ((GeneralPath) shape).lineTo(510.913, 115.354);
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
        paint = new Color(35, 31, 32, 255);
        stroke = new BasicStroke(1.0f, 0, 0, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(463.264, 115.354);
        ((GeneralPath) shape).lineTo(458.422, 115.354);
        ((GeneralPath) shape).lineTo(454.763, 148.572);
        ((GeneralPath) shape).lineTo(454.763, 276.921);
        ((GeneralPath) shape).lineTo(461.301, 289.627);
        ((GeneralPath) shape).lineTo(472.64, 296.771);
        ((GeneralPath) shape).lineTo(502.402, 296.771);
        ((GeneralPath) shape).lineTo(512.913, 289.627);
        ((GeneralPath) shape).lineTo(519.411, 276.921);
        ((GeneralPath) shape).lineTo(519.411, 148.572);
        ((GeneralPath) shape).lineTo(515.754, 115.354);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_169;
        g.setTransform(defaultTransform__0_169);
        g.setClip(clip__0_169);
        float alpha__0_170 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_170 = g.getClip();
        AffineTransform defaultTransform__0_170 = g.getTransform();
        g.transform(new AffineTransform(0.1500059962272644f, 0.0f, 0.0f,
                -0.12700198590755463f, -103.58602905273438f, 844.991455078125f));
        // _0_170 is CompositeGraphicsNode
        float alpha__0_170_0 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_170_0 = g.getClip();
        AffineTransform defaultTransform__0_170_0 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_170_0 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        stroke = new BasicStroke(7.88225f, 0, 0, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(3941.37, 4470.53);
        ((GeneralPath) shape).lineTo(3941.37, 5576.08);
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_170_0;
        g.setTransform(defaultTransform__0_170_0);
        g.setClip(clip__0_170_0);
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
        paint = new Color(0, 0, 0, 255);
        stroke = new BasicStroke(1.0f, 0, 0, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(556.262, 309.527);
        ((GeneralPath) shape).lineTo(518.869, 277.282);
        ((GeneralPath) shape).lineTo(454.75702, 277.282);
        ((GeneralPath) shape).lineTo(417.363, 309.527);
        ((GeneralPath) shape).moveTo(544.963, 72.833);
        ((GeneralPath) shape).lineTo(539.35004, 110.716995);
        ((GeneralPath) shape).lineTo(517.99506, 136.392);
        ((GeneralPath) shape).lineTo(455.67206, 136.392);
        ((GeneralPath) shape).lineTo(434.31705, 110.716995);
        ((GeneralPath) shape).lineTo(428.70404, 72.83299);
        ((GeneralPath) shape).moveTo(417.375, 208.896);
        ((GeneralPath) shape).lineTo(555.831, 208.896);
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(452.413, 247.166);
        ((GeneralPath) shape).lineTo(520.812, 247.166);
        ((GeneralPath) shape).lineTo(523.585, 244.314);
        ((GeneralPath) shape).lineTo(504.11, 174.49);
        ((GeneralPath) shape).lineTo(499.568, 174.49);
        ((GeneralPath) shape).lineTo(499.568, 178.766);
        ((GeneralPath) shape).lineTo(474.058, 178.766);
        ((GeneralPath) shape).lineTo(474.058, 174.964);
        ((GeneralPath) shape).lineTo(470.387, 174.964);
        ((GeneralPath) shape).lineTo(449.962, 243.839);
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
        paint = new Color(204, 206, 207, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(510.432, 203.362);
        ((GeneralPath) shape).lineTo(504.007, 180.324);
        ((GeneralPath) shape).lineTo(470.505, 180.324);
        ((GeneralPath) shape).lineTo(463.674, 203.362);
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
        paint = new Color(255, 255, 255, 255);
        shape = new Rectangle2D.Double(474.07000732421875, 168.88099670410156,
                25.49799919128418, 10.25100040435791);
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
        paint = new Color(67, 67, 68, 255);
        stroke = new BasicStroke(1.0f, 0, 1, 4.0f, null, 0.0f);
        shape = new Rectangle2D.Double(474.0690002441406, 168.8820037841797,
                25.49799919128418, 10.251999855041504);
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        paint = new Color(67, 67, 68, 255);
        stroke = new BasicStroke(1.0f, 0, 0, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(476.582, 177.046);
        ((GeneralPath) shape).lineTo(478.721, 177.046);
        ((GeneralPath) shape).lineTo(478.721, 171.11);
        ((GeneralPath) shape).lineTo(476.582, 171.11);
        ((GeneralPath) shape).lineTo(476.582, 177.046);
        ((GeneralPath) shape).closePath();
        ((GeneralPath) shape).moveTo(480.619, 177.046);
        ((GeneralPath) shape).lineTo(482.759, 177.046);
        ((GeneralPath) shape).lineTo(482.759, 171.11);
        ((GeneralPath) shape).lineTo(480.619, 171.11);
        ((GeneralPath) shape).lineTo(480.619, 177.046);
        ((GeneralPath) shape).closePath();
        ((GeneralPath) shape).moveTo(482.759, 177.046);
        ((GeneralPath) shape).lineTo(484.897, 177.046);
        ((GeneralPath) shape).lineTo(484.897, 171.11);
        ((GeneralPath) shape).lineTo(482.759, 171.11);
        ((GeneralPath) shape).lineTo(482.759, 177.046);
        ((GeneralPath) shape).closePath();
        ((GeneralPath) shape).moveTo(484.896, 177.046);
        ((GeneralPath) shape).lineTo(487.03, 177.046);
        ((GeneralPath) shape).lineTo(487.03, 171.11);
        ((GeneralPath) shape).lineTo(484.896, 171.11);
        ((GeneralPath) shape).lineTo(484.896, 177.046);
        ((GeneralPath) shape).closePath();
        ((GeneralPath) shape).moveTo(487.03, 177.046);
        ((GeneralPath) shape).lineTo(489.17, 177.046);
        ((GeneralPath) shape).lineTo(489.17, 171.11);
        ((GeneralPath) shape).lineTo(487.03, 171.11);
        ((GeneralPath) shape).lineTo(487.03, 177.046);
        ((GeneralPath) shape).closePath();
        ((GeneralPath) shape).moveTo(489.17, 177.046);
        ((GeneralPath) shape).lineTo(491.306, 177.046);
        ((GeneralPath) shape).lineTo(491.306, 171.11);
        ((GeneralPath) shape).lineTo(489.17, 171.11);
        ((GeneralPath) shape).lineTo(489.17, 177.046);
        ((GeneralPath) shape).closePath();
        ((GeneralPath) shape).moveTo(491.306, 177.046);
        ((GeneralPath) shape).lineTo(493.444, 177.046);
        ((GeneralPath) shape).lineTo(493.444, 171.11);
        ((GeneralPath) shape).lineTo(491.306, 171.11);
        ((GeneralPath) shape).lineTo(491.306, 177.046);
        ((GeneralPath) shape).closePath();
        ((GeneralPath) shape).moveTo(493.443, 177.046);
        ((GeneralPath) shape).lineTo(495.582, 177.046);
        ((GeneralPath) shape).lineTo(495.582, 171.11);
        ((GeneralPath) shape).lineTo(493.443, 171.11);
        ((GeneralPath) shape).lineTo(493.443, 177.046);
        ((GeneralPath) shape).closePath();
        ((GeneralPath) shape).moveTo(495.582, 177.046);
        ((GeneralPath) shape).lineTo(497.719, 177.046);
        ((GeneralPath) shape).lineTo(497.719, 171.11);
        ((GeneralPath) shape).lineTo(495.582, 171.11);
        ((GeneralPath) shape).lineTo(495.582, 177.046);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        paint = new Color(0, 0, 0, 255);
        stroke = new BasicStroke(1.0f, 0, 0, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(452.413, 247.166);
        ((GeneralPath) shape).lineTo(520.812, 247.166);
        ((GeneralPath) shape).lineTo(523.585, 244.314);
        ((GeneralPath) shape).lineTo(504.11, 174.49);
        ((GeneralPath) shape).lineTo(499.568, 174.49);
        ((GeneralPath) shape).lineTo(499.568, 178.767);
        ((GeneralPath) shape).lineTo(474.056, 178.767);
        ((GeneralPath) shape).lineTo(474.056, 174.964);
        ((GeneralPath) shape).lineTo(470.385, 174.964);
        ((GeneralPath) shape).lineTo(449.962, 243.839);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(487.692, 161.718);
        ((GeneralPath) shape).lineTo(489.594, 151.981);
        ((GeneralPath) shape).lineTo(495.77, 151.981);
        ((GeneralPath) shape).lineTo(497.906, 161.718);
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
        paint = new Color(67, 67, 68, 255);
        stroke = new BasicStroke(1.0f, 0, 0, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(487.692, 161.718);
        ((GeneralPath) shape).lineTo(489.594, 151.981);
        ((GeneralPath) shape).lineTo(495.77, 151.981);
        ((GeneralPath) shape).lineTo(497.906, 161.718);
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        paint = new Color(255, 255, 255, 255);
        shape = new Rectangle2D.Double(485.7959899902344, 161.718994140625,
                4.51200008392334, 6.888000011444092);
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
        paint = new Color(67, 67, 68, 255);
        stroke = new BasicStroke(1.0f, 0, 0, 4.0f, null, 0.0f);
        shape = new Rectangle2D.Double(485.7959899902344, 161.718994140625,
                4.51200008392334, 6.888000011444092);
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        paint = new Color(255, 255, 255, 255);
        shape = new Rectangle2D.Double(490.3070068359375, 161.718994140625,
                4.51200008392334, 6.888000011444092);
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
        paint = new Color(67, 67, 68, 255);
        stroke = new BasicStroke(1.0f, 0, 0, 4.0f, null, 0.0f);
        shape = new Rectangle2D.Double(490.3070068359375, 161.718994140625,
                4.511000156402588, 6.888000011444092);
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        paint = new Color(255, 255, 255, 255);
        shape = new Rectangle2D.Double(494.8179931640625, 161.718994140625,
                4.513000011444092, 6.888000011444092);
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
        paint = new Color(255, 255, 255, 255);
        shape = new Rectangle2D.Double(494.8179931640625, 161.718994140625,
                4.513000011444092, 6.888000011444092);
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
        paint = new Color(67, 67, 68, 255);
        stroke = new BasicStroke(1.0f, 0, 0, 4.0f, null, 0.0f);
        shape = new Rectangle2D.Double(494.8179931640625, 161.718994140625,
                4.513999938964844, 6.888000011444092);
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(485.396, 168.881);
        ((GeneralPath) shape).lineTo(474.058, 168.881);
        ((GeneralPath) shape).lineTo(485.396, 161.795);
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
        paint = new Color(67, 67, 68, 255);
        stroke = new BasicStroke(1.0f, 0, 2, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(485.396, 168.881);
        ((GeneralPath) shape).lineTo(474.056, 168.881);
        ((GeneralPath) shape).lineTo(485.396, 161.795);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        paint = new Color(35, 31, 32, 255);
        stroke = new BasicStroke(1.0f, 0, 0, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(510.432, 203.362);
        ((GeneralPath) shape).lineTo(504.007, 180.324);
        ((GeneralPath) shape).lineTo(470.505, 180.324);
        ((GeneralPath) shape).lineTo(463.672, 203.362);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(464.993, 340.54);
        ((GeneralPath) shape).lineTo(464.993, 338.52002);
        ((GeneralPath) shape).lineTo(466.54602, 338.52002);
        ((GeneralPath) shape).curveTo(467.34503, 338.52002, 467.493, 338.67,
                467.493, 339.506);
        ((GeneralPath) shape).curveTo(467.493, 340.37302, 467.298, 340.54,
                466.497, 340.54);
        ((GeneralPath) shape).lineTo(464.993, 340.54);
        ((GeneralPath) shape).moveTo(466.741, 341.542);
        ((GeneralPath) shape).curveTo(467.288, 341.542, 467.493, 341.89798,
                467.493, 342.41098);
        ((GeneralPath) shape).lineTo(467.493, 343.546);
        ((GeneralPath) shape).lineTo(468.618, 343.546);
        ((GeneralPath) shape).lineTo(468.618, 342.413);
        ((GeneralPath) shape).curveTo(468.618, 341.556, 468.415, 341.11,
                467.583, 341.038);
        ((GeneralPath) shape).lineTo(467.583, 341.004);
        ((GeneralPath) shape).curveTo(468.618, 340.852, 468.618, 340.202,
                468.618, 339.325);
        ((GeneralPath) shape).curveTo(468.618, 337.94702, 468.138, 337.53403,
                466.884, 337.53403);
        ((GeneralPath) shape).lineTo(463.868, 337.53403);
        ((GeneralPath) shape).lineTo(463.868, 343.545);
        ((GeneralPath) shape).lineTo(464.993, 343.545);
        ((GeneralPath) shape).lineTo(464.993, 341.54102);
        ((GeneralPath) shape).lineTo(466.741, 341.54102);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(470.52, 341.041);
        ((GeneralPath) shape).curveTo(470.52, 340.25198, 470.577, 340.05698,
                471.44598, 340.05698);
        ((GeneralPath) shape).curveTo(472.26797, 340.05698, 472.395, 340.12598,
                472.395, 341.041);
        ((GeneralPath) shape).lineTo(470.52, 341.041);
        ((GeneralPath) shape).moveTo(472.395, 342.211);
        ((GeneralPath) shape).curveTo(472.395, 342.794, 472.004, 342.794,
                471.44598, 342.794);
        ((GeneralPath) shape).curveTo(470.54398, 342.794, 470.52, 342.522,
                470.52, 341.667);
        ((GeneralPath) shape).lineTo(473.395, 341.667);
        ((GeneralPath) shape).curveTo(473.395, 339.8, 473.16498, 339.288,
                471.44598, 339.288);
        ((GeneralPath) shape).curveTo(469.758, 339.288, 469.52, 339.95898,
                469.52, 341.495);
        ((GeneralPath) shape).curveTo(469.52, 343.055, 469.84598, 343.546,
                471.44598, 343.546);
        ((GeneralPath) shape).curveTo(472.63898, 343.546, 473.395, 343.483,
                473.395, 342.21198);
        ((GeneralPath) shape).lineTo(472.395, 342.21198);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(476.955, 343.545);
        ((GeneralPath) shape).lineTo(477.955, 343.545);
        ((GeneralPath) shape).lineTo(477.955, 340.907);
        ((GeneralPath) shape).curveTo(477.955, 339.496, 477.387, 339.28702,
                476.025, 339.28702);
        ((GeneralPath) shape).curveTo(475.072, 339.28702, 474.205, 339.28702,
                474.205, 340.53302);
        ((GeneralPath) shape).lineTo(475.205, 340.53302);
        ((GeneralPath) shape).curveTo(475.205, 340.014, 475.56998, 339.975,
                476.025, 339.975);
        ((GeneralPath) shape).curveTo(476.896, 339.975, 476.955, 340.204,
                476.955, 340.854);
        ((GeneralPath) shape).lineTo(476.955, 341.437);
        ((GeneralPath) shape).lineTo(476.922, 341.437);
        ((GeneralPath) shape).curveTo(476.674, 340.915, 476.151, 340.915,
                475.619, 340.915);
        ((GeneralPath) shape).curveTo(474.598, 340.915, 474.08, 341.195,
                474.08, 342.20102);
        ((GeneralPath) shape).curveTo(474.08, 343.34402, 474.685, 343.545,
                475.619, 343.545);
        ((GeneralPath) shape).curveTo(476.125, 343.545, 476.75198, 343.545,
                476.955, 342.92502);
        ((GeneralPath) shape).lineTo(476.955, 343.545);
        ((GeneralPath) shape).moveTo(475.992, 341.667);
        ((GeneralPath) shape).curveTo(476.508, 341.667, 476.95502, 341.667,
                476.95502, 342.201);
        ((GeneralPath) shape).curveTo(476.95502, 342.75098, 476.549, 342.79398,
                475.992, 342.79398);
        ((GeneralPath) shape).curveTo(475.318, 342.79398, 475.08002, 342.74298,
                475.08002, 342.201);
        ((GeneralPath) shape).curveTo(475.08, 341.667, 475.463, 341.667,
                475.992, 341.667);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(478.792, 339.288);
        ((GeneralPath) shape).lineTo(478.792, 343.546);
        ((GeneralPath) shape).lineTo(479.792, 343.546);
        ((GeneralPath) shape).lineTo(479.792, 340.947);
        ((GeneralPath) shape).curveTo(479.792, 340.401, 479.978, 340.056,
                480.613, 340.056);
        ((GeneralPath) shape).curveTo(481.124, 340.056, 481.167, 340.305,
                481.167, 340.729);
        ((GeneralPath) shape).lineTo(481.167, 340.946);
        ((GeneralPath) shape).lineTo(482.167, 340.946);
        ((GeneralPath) shape).lineTo(482.167, 340.61002);
        ((GeneralPath) shape).curveTo(482.167, 339.816, 481.936, 339.28702,
                480.99298, 339.28702);
        ((GeneralPath) shape).curveTo(480.46997, 339.28702, 480.00598, 339.42,
                479.792, 339.876);
        ((GeneralPath) shape).lineTo(479.792, 339.28702);
        ((GeneralPath) shape).lineTo(478.792, 339.28702);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(488.925, 341.542);
        ((GeneralPath) shape).lineTo(486.843, 341.542);
        ((GeneralPath) shape).lineTo(487.87198, 338.417);
        ((GeneralPath) shape).lineTo(487.88797, 338.417);
        ((GeneralPath) shape).lineTo(488.925, 341.542);
        ((GeneralPath) shape).moveTo(489.183, 342.418);
        ((GeneralPath) shape).lineTo(489.578, 343.545);
        ((GeneralPath) shape).lineTo(490.752, 343.545);
        ((GeneralPath) shape).lineTo(488.709, 337.53403);
        ((GeneralPath) shape).lineTo(487.00403, 337.53403);
        ((GeneralPath) shape).lineTo(485.002, 343.545);
        ((GeneralPath) shape).lineTo(486.2, 343.545);
        ((GeneralPath) shape).lineTo(486.578, 342.418);
        ((GeneralPath) shape).lineTo(489.183, 342.418);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(491.048, 339.288);
        ((GeneralPath) shape).lineTo(491.048, 343.546);
        ((GeneralPath) shape).lineTo(492.048, 343.546);
        ((GeneralPath) shape).lineTo(492.048, 340.947);
        ((GeneralPath) shape).curveTo(492.048, 340.401, 492.234, 340.056,
                492.87, 340.056);
        ((GeneralPath) shape).curveTo(493.38, 340.056, 493.423, 340.305,
                493.423, 340.729);
        ((GeneralPath) shape).lineTo(493.423, 340.946);
        ((GeneralPath) shape).lineTo(494.423, 340.946);
        ((GeneralPath) shape).lineTo(494.423, 340.61002);
        ((GeneralPath) shape).curveTo(494.423, 339.816, 494.19202, 339.28702,
                493.249, 339.28702);
        ((GeneralPath) shape).curveTo(492.72598, 339.28702, 492.262, 339.42,
                492.048, 339.876);
        ((GeneralPath) shape).lineTo(492.048, 339.28702);
        ((GeneralPath) shape).lineTo(491.048, 339.28702);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(494.94, 339.288);
        ((GeneralPath) shape).lineTo(494.94, 343.546);
        ((GeneralPath) shape).lineTo(495.94, 343.546);
        ((GeneralPath) shape).lineTo(495.94, 341.172);
        ((GeneralPath) shape).curveTo(495.94, 340.426, 496.11002, 340.056,
                496.971, 340.056);
        ((GeneralPath) shape).curveTo(497.55502, 340.056, 497.69, 340.265,
                497.69, 340.794);
        ((GeneralPath) shape).lineTo(497.69, 343.545);
        ((GeneralPath) shape).lineTo(498.69, 343.545);
        ((GeneralPath) shape).lineTo(498.69, 341.17102);
        ((GeneralPath) shape).curveTo(498.69, 340.42502, 498.846, 340.05502,
                499.647, 340.05502);
        ((GeneralPath) shape).curveTo(500.19, 340.05502, 500.315, 340.26404,
                500.315, 340.79303);
        ((GeneralPath) shape).lineTo(500.315, 343.54404);
        ((GeneralPath) shape).lineTo(501.315, 343.54404);
        ((GeneralPath) shape).lineTo(501.315, 340.69705);
        ((GeneralPath) shape).curveTo(501.315, 339.66406, 500.965, 339.28604,
                499.907, 339.28604);
        ((GeneralPath) shape).curveTo(499.35602, 339.28604, 498.79202,
                339.44705, 498.60202, 340.03003);
        ((GeneralPath) shape).lineTo(498.571, 340.03003);
        ((GeneralPath) shape).curveTo(498.46002, 339.42102, 497.858, 339.28604,
                497.33502, 339.28604);
        ((GeneralPath) shape).curveTo(496.77603, 339.28604, 496.17703,
                339.40503, 495.94003, 339.93503);
        ((GeneralPath) shape).lineTo(495.94003, 339.28604);
        ((GeneralPath) shape).lineTo(494.94, 339.28604);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(504.146, 340.056);
        ((GeneralPath) shape).curveTo(505.013, 340.056, 505.08398, 340.311,
                505.08398, 341.438);
        ((GeneralPath) shape).curveTo(505.08398, 342.547, 505.01398, 342.79398,
                504.146, 342.79398);
        ((GeneralPath) shape).curveTo(503.278, 342.79398, 503.208, 342.54697,
                503.208, 341.438);
        ((GeneralPath) shape).curveTo(503.208, 340.311, 503.278, 340.056,
                504.146, 340.056);
        ((GeneralPath) shape).moveTo(504.146, 339.288);
        ((GeneralPath) shape).curveTo(502.427, 339.288, 502.208, 339.806,
                502.208, 341.431);
        ((GeneralPath) shape).curveTo(502.208, 343.034, 502.427, 343.546,
                504.146, 343.546);
        ((GeneralPath) shape).curveTo(505.865, 343.546, 506.08398, 343.033,
                506.08398, 341.431);
        ((GeneralPath) shape).curveTo(506.083, 339.806, 505.864, 339.288,
                504.146, 339.288);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(506.863, 339.288);
        ((GeneralPath) shape).lineTo(506.863, 343.546);
        ((GeneralPath) shape).lineTo(507.863, 343.546);
        ((GeneralPath) shape).lineTo(507.863, 340.947);
        ((GeneralPath) shape).curveTo(507.863, 340.401, 508.049, 340.056,
                508.685, 340.056);
        ((GeneralPath) shape).curveTo(509.19598, 340.056, 509.238, 340.305,
                509.238, 340.729);
        ((GeneralPath) shape).lineTo(509.238, 340.946);
        ((GeneralPath) shape).lineTo(510.238, 340.946);
        ((GeneralPath) shape).lineTo(510.238, 340.61002);
        ((GeneralPath) shape).curveTo(510.238, 339.816, 510.008, 339.28702,
                509.064, 339.28702);
        ((GeneralPath) shape).curveTo(508.541, 339.28702, 508.078, 339.42,
                507.863, 339.876);
        ((GeneralPath) shape).lineTo(507.863, 339.28702);
        ((GeneralPath) shape).lineTo(506.863, 339.28702);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(574.043, 90.05);
        ((GeneralPath) shape).lineTo(574.043, 92.867);
        ((GeneralPath) shape).lineTo(575.029, 92.867);
        ((GeneralPath) shape).lineTo(575.029, 88.925);
        ((GeneralPath) shape).lineTo(569.018, 88.925);
        ((GeneralPath) shape).lineTo(569.018, 90.05);
        ((GeneralPath) shape).lineTo(571.522, 90.05);
        ((GeneralPath) shape).lineTo(571.522, 92.722);
        ((GeneralPath) shape).lineTo(572.524, 92.722);
        ((GeneralPath) shape).lineTo(572.524, 90.05);
        ((GeneralPath) shape).lineTo(574.043, 90.05);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(573.276, 93.427);
        ((GeneralPath) shape).lineTo(569.017, 93.427);
        ((GeneralPath) shape).lineTo(569.017, 94.427);
        ((GeneralPath) shape).lineTo(571.616, 94.427);
        ((GeneralPath) shape).curveTo(572.16205, 94.427, 572.507, 94.613,
                572.507, 95.249);
        ((GeneralPath) shape).curveTo(572.507, 95.759, 572.25903, 95.802,
                571.83405, 95.802);
        ((GeneralPath) shape).lineTo(571.616, 95.802);
        ((GeneralPath) shape).lineTo(571.616, 96.802);
        ((GeneralPath) shape).lineTo(571.953, 96.802);
        ((GeneralPath) shape).curveTo(572.747, 96.802, 573.276, 96.572,
                573.276, 95.628);
        ((GeneralPath) shape).curveTo(573.276, 95.104996, 573.142, 94.642,
                572.687, 94.427);
        ((GeneralPath) shape).lineTo(573.276, 94.427);
        ((GeneralPath) shape).lineTo(573.276, 93.427);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(572.507, 99.104);
        ((GeneralPath) shape).curveTo(572.507, 99.97099, 572.252, 100.042,
                571.125, 100.042);
        ((GeneralPath) shape).curveTo(570.016, 100.042, 569.77, 99.972, 569.77,
                99.104);
        ((GeneralPath) shape).curveTo(569.77, 98.23599, 570.016, 98.16599,
                571.125, 98.16599);
        ((GeneralPath) shape).curveTo(572.252, 98.167, 572.507, 98.237,
                572.507, 99.104);
        ((GeneralPath) shape).moveTo(573.276, 99.104);
        ((GeneralPath) shape).curveTo(573.276, 97.384995, 572.757, 97.16599,
                571.132, 97.16599);
        ((GeneralPath) shape).curveTo(569.529, 97.16599, 569.017, 97.384995,
                569.017, 99.104);
        ((GeneralPath) shape).curveTo(569.017, 100.823, 569.53, 101.042,
                571.132, 101.042);
        ((GeneralPath) shape).curveTo(572.758, 101.042, 573.276, 100.823,
                573.276, 99.104);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(573.276, 101.918);
        ((GeneralPath) shape).lineTo(569.017, 101.918);
        ((GeneralPath) shape).lineTo(569.017, 102.918);
        ((GeneralPath) shape).lineTo(571.336, 102.918);
        ((GeneralPath) shape).curveTo(572.089, 102.918, 572.506, 103.034996,
                572.506, 103.899);
        ((GeneralPath) shape).curveTo(572.506, 104.535, 572.297, 104.668,
                571.688, 104.668);
        ((GeneralPath) shape).lineTo(569.01697, 104.668);
        ((GeneralPath) shape).lineTo(569.01697, 105.668);
        ((GeneralPath) shape).lineTo(571.792, 105.668);
        ((GeneralPath) shape).curveTo(572.817, 105.668, 573.27496, 105.336,
                573.27496, 104.245);
        ((GeneralPath) shape).curveTo(573.27496, 103.665, 573.212, 103.170006,
                572.59894, 102.950005);
        ((GeneralPath) shape).lineTo(572.59894, 102.91901);
        ((GeneralPath) shape).lineTo(573.27496, 102.91901);
        ((GeneralPath) shape).lineTo(573.27496, 101.918);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(572.524, 106.146);
        ((GeneralPath) shape).lineTo(572.524, 106.674);
        ((GeneralPath) shape).lineTo(570.331, 106.674);
        ((GeneralPath) shape).curveTo(569.271, 106.674, 569.018, 107.013,
                569.018, 108.131004);
        ((GeneralPath) shape).curveTo(569.018, 109.227005, 569.402, 109.549,
                570.671, 109.549);
        ((GeneralPath) shape).lineTo(570.671, 108.674);
        ((GeneralPath) shape).curveTo(570.22504, 108.674, 569.77, 108.73701,
                569.77, 108.131004);
        ((GeneralPath) shape).curveTo(569.77, 107.674, 569.948, 107.674,
                570.339, 107.674);
        ((GeneralPath) shape).lineTo(572.52496, 107.674);
        ((GeneralPath) shape).lineTo(572.52496, 109.336006);
        ((GeneralPath) shape).lineTo(573.277, 109.336006);
        ((GeneralPath) shape).lineTo(573.277, 107.674);
        ((GeneralPath) shape).lineTo(574.24896, 107.674);
        ((GeneralPath) shape).lineTo(574.24896, 106.674);
        ((GeneralPath) shape).lineTo(573.277, 106.674);
        ((GeneralPath) shape).lineTo(573.277, 106.146);
        ((GeneralPath) shape).lineTo(572.524, 106.146);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(572.023, 113.954);
        ((GeneralPath) shape).lineTo(574.043, 113.954);
        ((GeneralPath) shape).lineTo(574.043, 115.507);
        ((GeneralPath) shape).curveTo(574.043, 116.305, 573.893, 116.454,
                573.057, 116.454);
        ((GeneralPath) shape).curveTo(572.19, 116.454, 572.024, 116.259,
                572.024, 115.458);
        ((GeneralPath) shape).lineTo(572.024, 113.954);
        ((GeneralPath) shape).moveTo(571.021, 115.703);
        ((GeneralPath) shape).curveTo(571.021, 116.25, 570.665, 116.454,
                570.152, 116.454);
        ((GeneralPath) shape).lineTo(569.01697, 116.454);
        ((GeneralPath) shape).lineTo(569.01697, 117.579);
        ((GeneralPath) shape).lineTo(570.14996, 117.579);
        ((GeneralPath) shape).curveTo(571.00696, 117.579, 571.454, 117.376,
                571.52594, 116.544);
        ((GeneralPath) shape).lineTo(571.55896, 116.544);
        ((GeneralPath) shape).curveTo(571.71094, 117.579, 572.36194, 117.579,
                573.238, 117.579);
        ((GeneralPath) shape).curveTo(574.61597, 117.579, 575.029, 117.099,
                575.029, 115.845);
        ((GeneralPath) shape).lineTo(575.029, 112.829);
        ((GeneralPath) shape).lineTo(569.01697, 112.829);
        ((GeneralPath) shape).lineTo(569.01697, 113.954);
        ((GeneralPath) shape).lineTo(571.021, 113.954);
        ((GeneralPath) shape).lineTo(571.021, 115.703);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(575.029, 119.577);
        ((GeneralPath) shape).lineTo(575.029, 118.577);
        ((GeneralPath) shape).lineTo(574.172, 118.577);
        ((GeneralPath) shape).lineTo(574.172, 119.577);
        ((GeneralPath) shape).lineTo(575.029, 119.577);
        ((GeneralPath) shape).moveTo(573.276, 119.577);
        ((GeneralPath) shape).lineTo(573.276, 118.577);
        ((GeneralPath) shape).lineTo(569.017, 118.577);
        ((GeneralPath) shape).lineTo(569.017, 119.577);
        ((GeneralPath) shape).lineTo(573.276, 119.577);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(568.854, 123.186);
        ((GeneralPath) shape).curveTo(568.081, 123.186, 567.891, 123.013,
                567.891, 122.201996);
        ((GeneralPath) shape).curveTo(567.891, 121.646996, 567.985, 121.436,
                568.605, 121.436);
        ((GeneralPath) shape).lineTo(568.605, 120.436);
        ((GeneralPath) shape).curveTo(567.32, 120.371994, 567.139, 121.155,
                567.139, 122.201996);
        ((GeneralPath) shape).curveTo(567.139, 123.714, 567.511, 124.186,
                569.073, 124.186);
        ((GeneralPath) shape).lineTo(573.277, 124.186);
        ((GeneralPath) shape).lineTo(573.277, 123.186);
        ((GeneralPath) shape).lineTo(572.60095, 123.186);
        ((GeneralPath) shape).curveTo(573.1489, 122.937996, 573.277, 122.487,
                573.277, 121.912994);
        ((GeneralPath) shape).curveTo(573.277, 120.311, 572.294, 120.311,
                571.016, 120.311);
        ((GeneralPath) shape).curveTo(569.80096, 120.311, 569.018, 120.479,
                569.018, 121.912994);
        ((GeneralPath) shape).curveTo(569.018, 122.409, 569.112, 122.93099,
                569.652, 123.186);
        ((GeneralPath) shape).lineTo(568.854, 123.186);
        ((GeneralPath) shape).moveTo(572.507, 122.201);
        ((GeneralPath) shape).curveTo(572.507, 123.05299, 572.14905, 123.185,
                571.26, 123.185);
        ((GeneralPath) shape).curveTo(570.269, 123.185, 569.77, 123.185,
                569.77, 122.193);
        ((GeneralPath) shape).curveTo(569.77, 121.31, 570.318, 121.31, 571.26,
                121.31);
        ((GeneralPath) shape).curveTo(572.31, 121.311, 572.507, 121.51,
                572.507, 122.201);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(575.029, 126.159);
        ((GeneralPath) shape).lineTo(575.029, 125.159);
        ((GeneralPath) shape).lineTo(569.01697, 125.159);
        ((GeneralPath) shape).lineTo(569.01697, 126.159);
        ((GeneralPath) shape).lineTo(571.33594, 126.159);
        ((GeneralPath) shape).curveTo(572.0889, 126.159, 572.5059, 126.27599,
                572.5059, 127.139);
        ((GeneralPath) shape).curveTo(572.5059, 127.776, 572.29694, 127.909,
                571.6879, 127.909);
        ((GeneralPath) shape).lineTo(569.0169, 127.909);
        ((GeneralPath) shape).lineTo(569.0169, 128.909);
        ((GeneralPath) shape).lineTo(571.79193, 128.909);
        ((GeneralPath) shape).curveTo(572.81696, 128.909, 573.2749, 128.576,
                573.2749, 127.48499);
        ((GeneralPath) shape).curveTo(573.2749, 126.90499, 573.2099,
                126.409996, 572.5929, 126.189995);
        ((GeneralPath) shape).lineTo(572.5929, 126.159);
        ((GeneralPath) shape).lineTo(575.029, 126.159);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(572.524, 129.387);
        ((GeneralPath) shape).lineTo(572.524, 129.91399);
        ((GeneralPath) shape).lineTo(570.331, 129.91399);
        ((GeneralPath) shape).curveTo(569.271, 129.91399, 569.018, 130.25398,
                569.018, 131.37099);
        ((GeneralPath) shape).curveTo(569.018, 132.46698, 569.402, 132.78899,
                570.671, 132.78899);
        ((GeneralPath) shape).lineTo(570.671, 131.91399);
        ((GeneralPath) shape).curveTo(570.22504, 131.91399, 569.77, 131.97699,
                569.77, 131.37099);
        ((GeneralPath) shape).curveTo(569.77, 130.91399, 569.948, 130.91399,
                570.339, 130.91399);
        ((GeneralPath) shape).lineTo(572.52496, 130.91399);
        ((GeneralPath) shape).lineTo(572.52496, 132.57599);
        ((GeneralPath) shape).lineTo(573.277, 132.57599);
        ((GeneralPath) shape).lineTo(573.277, 130.91399);
        ((GeneralPath) shape).lineTo(574.24896, 130.91399);
        ((GeneralPath) shape).lineTo(574.24896, 129.91399);
        ((GeneralPath) shape).lineTo(573.277, 129.91399);
        ((GeneralPath) shape).lineTo(573.277, 129.387);
        ((GeneralPath) shape).lineTo(572.524, 129.387);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(571.027, 135.82);
        ((GeneralPath) shape).lineTo(570.75, 135.82);
        ((GeneralPath) shape).curveTo(569.018, 135.82, 569.018, 137.009,
                569.018, 138.341);
        ((GeneralPath) shape).curveTo(569.018, 139.891, 569.166, 140.695,
                570.901, 140.695);
        ((GeneralPath) shape).curveTo(572.418, 140.695, 572.513, 140.18501,
                572.607, 138.32);
        ((GeneralPath) shape).curveTo(572.67, 137.10301, 572.691, 136.945,
                573.33, 136.945);
        ((GeneralPath) shape).curveTo(573.974, 136.945, 574.085, 137.136,
                574.085, 138.328);
        ((GeneralPath) shape).curveTo(574.085, 139.17, 574.062, 139.445,
                573.41003, 139.445);
        ((GeneralPath) shape).lineTo(573.21906, 139.445);
        ((GeneralPath) shape).lineTo(573.21906, 140.57);
        ((GeneralPath) shape).lineTo(573.4161, 140.57);
        ((GeneralPath) shape).curveTo(575.0301, 140.57, 575.0301, 139.60101,
                575.0301, 138.328);
        ((GeneralPath) shape).curveTo(575.0301, 136.865, 575.0301, 135.82,
                573.3301, 135.82);
        ((GeneralPath) shape).curveTo(571.7201, 135.82, 571.76807, 136.84901,
                571.6871, 138.162);
        ((GeneralPath) shape).curveTo(571.6401, 139.095, 571.7521, 139.57,
                570.90106, 139.57);
        ((GeneralPath) shape).curveTo(570.2101, 139.57, 570.0201, 139.42001,
                570.0201, 138.328);
        ((GeneralPath) shape).curveTo(570.0201, 137.246, 570.0671, 136.945,
                570.75006, 136.945);
        ((GeneralPath) shape).lineTo(571.02704, 136.945);
        ((GeneralPath) shape).lineTo(571.02704, 135.82);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(575.029, 142.473);
        ((GeneralPath) shape).lineTo(575.029, 141.473);
        ((GeneralPath) shape).lineTo(574.172, 141.473);
        ((GeneralPath) shape).lineTo(574.172, 142.473);
        ((GeneralPath) shape).lineTo(575.029, 142.473);
        ((GeneralPath) shape).moveTo(573.276, 142.473);
        ((GeneralPath) shape).lineTo(573.276, 141.473);
        ((GeneralPath) shape).lineTo(569.017, 141.473);
        ((GeneralPath) shape).lineTo(569.017, 142.473);
        ((GeneralPath) shape).lineTo(573.276, 142.473);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(569.018, 146.122);
        ((GeneralPath) shape).lineTo(569.018, 147.122);
        ((GeneralPath) shape).lineTo(575.03, 147.122);
        ((GeneralPath) shape).lineTo(575.03, 146.122);
        ((GeneralPath) shape).lineTo(572.668, 146.122);
        ((GeneralPath) shape).lineTo(572.668, 146.099);
        ((GeneralPath) shape).curveTo(573.181, 145.884, 573.27704, 145.35,
                573.27704, 144.843);
        ((GeneralPath) shape).curveTo(573.27704, 143.41301, 572.48804, 143.248,
                571.26, 143.248);
        ((GeneralPath) shape).curveTo(569.98303, 143.248, 569.018, 143.248,
                569.018, 144.843);
        ((GeneralPath) shape).curveTo(569.018, 145.421, 569.141, 145.873,
                569.677, 146.123);
        ((GeneralPath) shape).lineTo(569.018, 146.123);
        ((GeneralPath) shape).moveTo(572.507, 145.138);
        ((GeneralPath) shape).curveTo(572.507, 146.003, 572.151, 146.122,
                571.26, 146.122);
        ((GeneralPath) shape).curveTo(570.269, 146.122, 569.77, 146.122,
                569.77, 145.138);
        ((GeneralPath) shape).curveTo(569.77, 144.247, 570.318, 144.247,
                571.26, 144.247);
        ((GeneralPath) shape).curveTo(572.31, 144.247, 572.507, 144.446,
                572.507, 145.138);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(571.522, 148.962);
        ((GeneralPath) shape).curveTo(572.311, 148.962, 572.506, 149.01901,
                572.506, 149.888);
        ((GeneralPath) shape).curveTo(572.506, 150.71, 572.438, 150.837,
                571.522, 150.837);
        ((GeneralPath) shape).lineTo(571.522, 148.962);
        ((GeneralPath) shape).moveTo(570.353, 150.837);
        ((GeneralPath) shape).curveTo(569.77, 150.837, 569.77, 150.446, 569.77,
                149.888);
        ((GeneralPath) shape).curveTo(569.77, 148.98601, 570.041, 148.962,
                570.89703, 148.962);
        ((GeneralPath) shape).lineTo(570.89703, 151.837);
        ((GeneralPath) shape).curveTo(572.76306, 151.837, 573.27704, 151.60701,
                573.27704, 149.888);
        ((GeneralPath) shape).curveTo(573.27704, 148.2, 572.60504, 147.962,
                571.06903, 147.962);
        ((GeneralPath) shape).curveTo(569.50903, 147.962, 569.018, 148.28801,
                569.018, 149.888);
        ((GeneralPath) shape).curveTo(569.018, 151.081, 569.081, 151.837,
                570.353, 151.837);
        ((GeneralPath) shape).lineTo(570.353, 150.837);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(571.021, 158.826);
        ((GeneralPath) shape).lineTo(571.021, 156.744);
        ((GeneralPath) shape).lineTo(574.146, 157.77301);
        ((GeneralPath) shape).lineTo(574.146, 157.78902);
        ((GeneralPath) shape).lineTo(571.021, 158.826);
        ((GeneralPath) shape).moveTo(570.145, 159.084);
        ((GeneralPath) shape).lineTo(569.018, 159.479);
        ((GeneralPath) shape).lineTo(569.018, 160.653);
        ((GeneralPath) shape).lineTo(575.03, 158.61);
        ((GeneralPath) shape).lineTo(575.03, 156.905);
        ((GeneralPath) shape).lineTo(569.018, 154.903);
        ((GeneralPath) shape).lineTo(569.018, 156.102);
        ((GeneralPath) shape).lineTo(570.145, 156.479);
        ((GeneralPath) shape).lineTo(570.145, 159.084);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(573.276, 160.977);
        ((GeneralPath) shape).lineTo(569.017, 160.977);
        ((GeneralPath) shape).lineTo(569.017, 161.977);
        ((GeneralPath) shape).lineTo(571.616, 161.977);
        ((GeneralPath) shape).curveTo(572.16205, 161.977, 572.507, 162.16301,
                572.507, 162.79901);
        ((GeneralPath) shape).curveTo(572.507, 163.309, 572.25903, 163.352,
                571.83405, 163.352);
        ((GeneralPath) shape).lineTo(571.616, 163.352);
        ((GeneralPath) shape).lineTo(571.616, 164.352);
        ((GeneralPath) shape).lineTo(571.953, 164.352);
        ((GeneralPath) shape).curveTo(572.747, 164.352, 573.276, 164.121,
                573.276, 163.17801);
        ((GeneralPath) shape).curveTo(573.276, 162.65501, 573.142, 162.19101,
                572.687, 161.977);
        ((GeneralPath) shape).lineTo(573.276, 161.977);
        ((GeneralPath) shape).lineTo(573.276, 160.977);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(573.276, 164.841);
        ((GeneralPath) shape).lineTo(569.017, 164.841);
        ((GeneralPath) shape).lineTo(569.017, 165.841);
        ((GeneralPath) shape).lineTo(571.39105, 165.841);
        ((GeneralPath) shape).curveTo(572.13605, 165.841, 572.50604, 166.011,
                572.50604, 166.87201);
        ((GeneralPath) shape).curveTo(572.50604, 167.45601, 572.29706, 167.591,
                571.76904, 167.591);
        ((GeneralPath) shape).lineTo(569.017, 167.591);
        ((GeneralPath) shape).lineTo(569.017, 168.591);
        ((GeneralPath) shape).lineTo(571.39105, 168.591);
        ((GeneralPath) shape).curveTo(572.13605, 168.591, 572.50604, 168.74701,
                572.50604, 169.548);
        ((GeneralPath) shape).curveTo(572.50604, 170.091, 572.29706, 170.216,
                571.76904, 170.216);
        ((GeneralPath) shape).lineTo(569.017, 170.216);
        ((GeneralPath) shape).lineTo(569.017, 171.216);
        ((GeneralPath) shape).lineTo(571.86505, 171.216);
        ((GeneralPath) shape).curveTo(572.8981, 171.216, 573.27606, 170.866,
                573.27606, 169.808);
        ((GeneralPath) shape).curveTo(573.27606, 169.257, 573.11505, 168.693,
                572.53204, 168.503);
        ((GeneralPath) shape).lineTo(572.53204, 168.472);
        ((GeneralPath) shape).curveTo(573.14, 168.36, 573.27606, 167.759,
                573.27606, 167.235);
        ((GeneralPath) shape).curveTo(573.27606, 166.677, 573.15607, 166.077,
                572.62604, 165.841);
        ((GeneralPath) shape).lineTo(573.27606, 165.841);
        ((GeneralPath) shape).lineTo(573.27606, 164.841);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(572.507, 174.017);
        ((GeneralPath) shape).curveTo(572.507, 174.884, 572.252, 174.955,
                571.125, 174.955);
        ((GeneralPath) shape).curveTo(570.016, 174.955, 569.77, 174.885,
                569.77, 174.017);
        ((GeneralPath) shape).curveTo(569.77, 173.149, 570.016, 173.079,
                571.125, 173.079);
        ((GeneralPath) shape).curveTo(572.252, 173.079, 572.507, 173.149,
                572.507, 174.017);
        ((GeneralPath) shape).moveTo(573.276, 174.017);
        ((GeneralPath) shape).curveTo(573.276, 172.298, 572.757, 172.079,
                571.132, 172.079);
        ((GeneralPath) shape).curveTo(569.529, 172.079, 569.017, 172.29799,
                569.017, 174.017);
        ((GeneralPath) shape).curveTo(569.017, 175.73601, 569.53, 175.955,
                571.132, 175.955);
        ((GeneralPath) shape).curveTo(572.758, 175.954, 573.276, 175.735,
                573.276, 174.017);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(573.276, 176.707);
        ((GeneralPath) shape).lineTo(569.017, 176.707);
        ((GeneralPath) shape).lineTo(569.017, 177.707);
        ((GeneralPath) shape).lineTo(571.616, 177.707);
        ((GeneralPath) shape).curveTo(572.16205, 177.707, 572.507, 177.893,
                572.507, 178.529);
        ((GeneralPath) shape).curveTo(572.507, 179.039, 572.25903, 179.082,
                571.83405, 179.082);
        ((GeneralPath) shape).lineTo(571.616, 179.082);
        ((GeneralPath) shape).lineTo(571.616, 180.082);
        ((GeneralPath) shape).lineTo(571.953, 180.082);
        ((GeneralPath) shape).curveTo(572.747, 180.082, 573.276, 179.851,
                573.276, 178.908);
        ((GeneralPath) shape).curveTo(573.276, 178.38501, 573.142, 177.921,
                572.687, 177.707);
        ((GeneralPath) shape).lineTo(573.276, 177.707);
        ((GeneralPath) shape).lineTo(573.276, 176.707);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(572.023, 214.774);
        ((GeneralPath) shape).lineTo(574.043, 214.774);
        ((GeneralPath) shape).lineTo(574.043, 216.327);
        ((GeneralPath) shape).curveTo(574.043, 217.12599, 573.893, 217.274,
                573.057, 217.274);
        ((GeneralPath) shape).curveTo(572.19, 217.274, 572.024, 217.079,
                572.024, 216.27701);
        ((GeneralPath) shape).lineTo(572.024, 214.774);
        ((GeneralPath) shape).moveTo(571.021, 216.522);
        ((GeneralPath) shape).curveTo(571.021, 217.069, 570.665, 217.274,
                570.152, 217.274);
        ((GeneralPath) shape).lineTo(569.01697, 217.274);
        ((GeneralPath) shape).lineTo(569.01697, 218.399);
        ((GeneralPath) shape).lineTo(570.14996, 218.399);
        ((GeneralPath) shape).curveTo(571.00696, 218.399, 571.454, 218.196,
                571.52594, 217.364);
        ((GeneralPath) shape).lineTo(571.55896, 217.364);
        ((GeneralPath) shape).curveTo(571.71094, 218.399, 572.36194, 218.399,
                573.238, 218.399);
        ((GeneralPath) shape).curveTo(574.61597, 218.399, 575.029, 217.918,
                575.029, 216.66501);
        ((GeneralPath) shape).lineTo(575.029, 213.649);
        ((GeneralPath) shape).lineTo(569.01697, 213.649);
        ((GeneralPath) shape).lineTo(569.01697, 214.774);
        ((GeneralPath) shape).lineTo(571.021, 214.774);
        ((GeneralPath) shape).lineTo(571.021, 216.522);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(571.522, 220.271);
        ((GeneralPath) shape).curveTo(572.311, 220.271, 572.506, 220.328,
                572.506, 221.19699);
        ((GeneralPath) shape).curveTo(572.506, 222.019, 572.438, 222.146,
                571.522, 222.146);
        ((GeneralPath) shape).lineTo(571.522, 220.271);
        ((GeneralPath) shape).moveTo(570.353, 222.146);
        ((GeneralPath) shape).curveTo(569.77, 222.146, 569.77, 221.75499,
                569.77, 221.19699);
        ((GeneralPath) shape).curveTo(569.77, 220.29399, 570.041, 220.271,
                570.89703, 220.271);
        ((GeneralPath) shape).lineTo(570.89703, 223.146);
        ((GeneralPath) shape).curveTo(572.76306, 223.146, 573.27704, 222.916,
                573.27704, 221.19699);
        ((GeneralPath) shape).curveTo(573.27704, 219.50899, 572.60504, 219.271,
                571.06903, 219.271);
        ((GeneralPath) shape).curveTo(569.50903, 219.271, 569.018, 219.597,
                569.018, 221.19699);
        ((GeneralPath) shape).curveTo(569.018, 222.38998, 569.081, 223.146,
                570.353, 223.146);
        ((GeneralPath) shape).lineTo(570.353, 222.146);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(569.018, 226.678);
        ((GeneralPath) shape).lineTo(569.018, 227.678);
        ((GeneralPath) shape).lineTo(571.656, 227.678);
        ((GeneralPath) shape).curveTo(573.067, 227.678, 573.277, 227.11,
                573.277, 225.748);
        ((GeneralPath) shape).curveTo(573.277, 224.795, 573.277, 223.928,
                572.02997, 223.928);
        ((GeneralPath) shape).lineTo(572.02997, 224.928);
        ((GeneralPath) shape).curveTo(572.54895, 224.928, 572.58795, 225.293,
                572.58795, 225.748);
        ((GeneralPath) shape).curveTo(572.58795, 226.619, 572.35895, 226.678,
                571.70996, 226.678);
        ((GeneralPath) shape).lineTo(571.126, 226.678);
        ((GeneralPath) shape).lineTo(571.126, 226.64499);
        ((GeneralPath) shape).curveTo(571.64795, 226.39699, 571.64795, 225.874,
                571.64795, 225.342);
        ((GeneralPath) shape).curveTo(571.64795, 224.321, 571.36896, 223.803,
                570.363, 223.803);
        ((GeneralPath) shape).curveTo(569.22, 223.803, 569.018, 224.40799,
                569.018, 225.342);
        ((GeneralPath) shape).curveTo(569.018, 225.84799, 569.018, 226.47499,
                569.638, 226.678);
        ((GeneralPath) shape).lineTo(569.018, 226.678);
        ((GeneralPath) shape).moveTo(570.896, 225.715);
        ((GeneralPath) shape).curveTo(570.896, 226.231, 570.896, 226.678,
                570.362, 226.678);
        ((GeneralPath) shape).curveTo(569.812, 226.678, 569.769, 226.27199,
                569.769, 225.715);
        ((GeneralPath) shape).curveTo(569.769, 225.041, 569.82, 224.803,
                570.362, 224.803);
        ((GeneralPath) shape).curveTo(570.896, 224.803, 570.896, 225.186,
                570.896, 225.715);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(573.276, 228.486);
        ((GeneralPath) shape).lineTo(569.017, 228.486);
        ((GeneralPath) shape).lineTo(569.017, 229.486);
        ((GeneralPath) shape).lineTo(571.616, 229.486);
        ((GeneralPath) shape).curveTo(572.16205, 229.486, 572.507, 229.672,
                572.507, 230.308);
        ((GeneralPath) shape).curveTo(572.507, 230.818, 572.25903, 230.861,
                571.83405, 230.861);
        ((GeneralPath) shape).lineTo(571.616, 230.861);
        ((GeneralPath) shape).lineTo(571.616, 231.861);
        ((GeneralPath) shape).lineTo(571.953, 231.861);
        ((GeneralPath) shape).curveTo(572.747, 231.861, 573.276, 231.631,
                573.276, 230.687);
        ((GeneralPath) shape).curveTo(573.276, 230.164, 573.142, 229.701,
                572.687, 229.486);
        ((GeneralPath) shape).lineTo(573.276, 229.486);
        ((GeneralPath) shape).lineTo(573.276, 228.486);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(572.023, 236.259);
        ((GeneralPath) shape).lineTo(574.043, 236.259);
        ((GeneralPath) shape).lineTo(574.043, 237.812);
        ((GeneralPath) shape).curveTo(574.043, 238.61, 573.893, 238.759,
                573.057, 238.759);
        ((GeneralPath) shape).curveTo(572.19, 238.759, 572.024, 238.564,
                572.024, 237.763);
        ((GeneralPath) shape).lineTo(572.024, 236.259);
        ((GeneralPath) shape).moveTo(571.021, 238.007);
        ((GeneralPath) shape).curveTo(571.021, 238.554, 570.665, 238.75801,
                570.152, 238.75801);
        ((GeneralPath) shape).lineTo(569.01697, 238.75801);
        ((GeneralPath) shape).lineTo(569.01697, 239.88301);
        ((GeneralPath) shape).lineTo(570.14996, 239.88301);
        ((GeneralPath) shape).curveTo(571.00696, 239.88301, 571.454, 239.68001,
                571.52594, 238.848);
        ((GeneralPath) shape).lineTo(571.55896, 238.848);
        ((GeneralPath) shape).curveTo(571.71094, 239.88301, 572.36194,
                239.88301, 573.238, 239.88301);
        ((GeneralPath) shape).curveTo(574.61597, 239.88301, 575.029, 239.40302,
                575.029, 238.14902);
        ((GeneralPath) shape).lineTo(575.029, 235.13301);
        ((GeneralPath) shape).lineTo(569.01697, 235.13301);
        ((GeneralPath) shape).lineTo(569.01697, 236.25801);
        ((GeneralPath) shape).lineTo(571.021, 236.25801);
        ((GeneralPath) shape).lineTo(571.021, 238.007);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(575.029, 241.881);
        ((GeneralPath) shape).lineTo(575.029, 240.881);
        ((GeneralPath) shape).lineTo(574.172, 240.881);
        ((GeneralPath) shape).lineTo(574.172, 241.881);
        ((GeneralPath) shape).lineTo(575.029, 241.881);
        ((GeneralPath) shape).moveTo(573.276, 241.881);
        ((GeneralPath) shape).lineTo(573.276, 240.881);
        ((GeneralPath) shape).lineTo(569.017, 240.881);
        ((GeneralPath) shape).lineTo(569.017, 241.881);
        ((GeneralPath) shape).lineTo(573.276, 241.881);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(568.854, 245.49);
        ((GeneralPath) shape).curveTo(568.081, 245.49, 567.891, 245.317,
                567.891, 244.50601);
        ((GeneralPath) shape).curveTo(567.891, 243.95102, 567.985, 243.74,
                568.605, 243.74);
        ((GeneralPath) shape).lineTo(568.605, 242.74);
        ((GeneralPath) shape).curveTo(567.32, 242.67601, 567.139, 243.459,
                567.139, 244.50601);
        ((GeneralPath) shape).curveTo(567.139, 246.018, 567.511, 246.49,
                569.073, 246.49);
        ((GeneralPath) shape).lineTo(573.277, 246.49);
        ((GeneralPath) shape).lineTo(573.277, 245.49);
        ((GeneralPath) shape).lineTo(572.60095, 245.49);
        ((GeneralPath) shape).curveTo(573.1489, 245.242, 573.277, 244.791,
                573.277, 244.21701);
        ((GeneralPath) shape).curveTo(573.277, 242.615, 572.294, 242.615,
                571.016, 242.615);
        ((GeneralPath) shape).curveTo(569.80096, 242.615, 569.018, 242.783,
                569.018, 244.21701);
        ((GeneralPath) shape).curveTo(569.018, 244.71301, 569.112, 245.23502,
                569.652, 245.49);
        ((GeneralPath) shape).lineTo(568.854, 245.49);
        ((GeneralPath) shape).moveTo(572.507, 244.506);
        ((GeneralPath) shape).curveTo(572.507, 245.358, 572.14905, 245.48999,
                571.26, 245.48999);
        ((GeneralPath) shape).curveTo(570.269, 245.48999, 569.77, 245.48999,
                569.77, 244.49799);
        ((GeneralPath) shape).curveTo(569.77, 243.61499, 570.318, 243.61499,
                571.26, 243.61499);
        ((GeneralPath) shape).curveTo(572.31, 243.615, 572.507, 243.814,
                572.507, 244.506);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(575.029, 248.464);
        ((GeneralPath) shape).lineTo(575.029, 247.464);
        ((GeneralPath) shape).lineTo(569.01697, 247.464);
        ((GeneralPath) shape).lineTo(569.01697, 248.464);
        ((GeneralPath) shape).lineTo(571.33594, 248.464);
        ((GeneralPath) shape).curveTo(572.0889, 248.464, 572.5059, 248.58101,
                572.5059, 249.445);
        ((GeneralPath) shape).curveTo(572.5059, 250.08101, 572.29694, 250.214,
                571.6879, 250.214);
        ((GeneralPath) shape).lineTo(569.0169, 250.214);
        ((GeneralPath) shape).lineTo(569.0169, 251.214);
        ((GeneralPath) shape).lineTo(571.79193, 251.214);
        ((GeneralPath) shape).curveTo(572.81696, 251.214, 573.2749, 250.882,
                573.2749, 249.791);
        ((GeneralPath) shape).curveTo(573.2749, 249.211, 573.2099, 248.716,
                572.5929, 248.496);
        ((GeneralPath) shape).lineTo(572.5929, 248.465);
        ((GeneralPath) shape).lineTo(575.029, 248.465);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(572.524, 251.691);
        ((GeneralPath) shape).lineTo(572.524, 252.219);
        ((GeneralPath) shape).lineTo(570.331, 252.219);
        ((GeneralPath) shape).curveTo(569.271, 252.219, 569.018, 252.558,
                569.018, 253.676);
        ((GeneralPath) shape).curveTo(569.018, 254.77199, 569.402, 255.094,
                570.671, 255.094);
        ((GeneralPath) shape).lineTo(570.671, 254.219);
        ((GeneralPath) shape).curveTo(570.22504, 254.219, 569.77, 254.282,
                569.77, 253.676);
        ((GeneralPath) shape).curveTo(569.77, 253.219, 569.948, 253.219,
                570.339, 253.219);
        ((GeneralPath) shape).lineTo(572.52496, 253.219);
        ((GeneralPath) shape).lineTo(572.52496, 254.881);
        ((GeneralPath) shape).lineTo(573.277, 254.881);
        ((GeneralPath) shape).lineTo(573.277, 253.219);
        ((GeneralPath) shape).lineTo(574.24896, 253.219);
        ((GeneralPath) shape).lineTo(574.24896, 252.219);
        ((GeneralPath) shape).lineTo(573.277, 252.219);
        ((GeneralPath) shape).lineTo(573.277, 251.691);
        ((GeneralPath) shape).lineTo(572.524, 251.691);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(571.027, 258.125);
        ((GeneralPath) shape).lineTo(570.75, 258.125);
        ((GeneralPath) shape).curveTo(569.018, 258.125, 569.018, 259.314,
                569.018, 260.646);
        ((GeneralPath) shape).curveTo(569.018, 262.19598, 569.166, 263.0,
                570.901, 263.0);
        ((GeneralPath) shape).curveTo(572.418, 263.0, 572.513, 262.49, 572.607,
                260.625);
        ((GeneralPath) shape).curveTo(572.67, 259.408, 572.691, 259.25, 573.33,
                259.25);
        ((GeneralPath) shape).curveTo(573.974, 259.25, 574.085, 259.441,
                574.085, 260.633);
        ((GeneralPath) shape).curveTo(574.085, 261.475, 574.062, 261.75,
                573.41003, 261.75);
        ((GeneralPath) shape).lineTo(573.21906, 261.75);
        ((GeneralPath) shape).lineTo(573.21906, 262.875);
        ((GeneralPath) shape).lineTo(573.4161, 262.875);
        ((GeneralPath) shape).curveTo(575.0301, 262.875, 575.0301, 261.906,
                575.0301, 260.633);
        ((GeneralPath) shape).curveTo(575.0301, 259.16998, 575.0301, 258.125,
                573.3301, 258.125);
        ((GeneralPath) shape).curveTo(571.7201, 258.125, 571.76807, 259.154,
                571.6871, 260.467);
        ((GeneralPath) shape).curveTo(571.6401, 261.40002, 571.7521, 261.875,
                570.90106, 261.875);
        ((GeneralPath) shape).curveTo(570.2101, 261.875, 570.0201, 261.725,
                570.0201, 260.633);
        ((GeneralPath) shape).curveTo(570.0201, 259.551, 570.0671, 259.25,
                570.75006, 259.25);
        ((GeneralPath) shape).lineTo(571.02704, 259.25);
        ((GeneralPath) shape).lineTo(571.02704, 258.125);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(575.029, 264.786);
        ((GeneralPath) shape).lineTo(575.029, 263.786);
        ((GeneralPath) shape).lineTo(574.172, 263.786);
        ((GeneralPath) shape).lineTo(574.172, 264.786);
        ((GeneralPath) shape).lineTo(575.029, 264.786);
        ((GeneralPath) shape).moveTo(573.276, 264.786);
        ((GeneralPath) shape).lineTo(573.276, 263.786);
        ((GeneralPath) shape).lineTo(569.017, 263.786);
        ((GeneralPath) shape).lineTo(569.017, 264.786);
        ((GeneralPath) shape).lineTo(573.276, 264.786);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(569.018, 268.427);
        ((GeneralPath) shape).lineTo(569.018, 269.427);
        ((GeneralPath) shape).lineTo(575.03, 269.427);
        ((GeneralPath) shape).lineTo(575.03, 268.427);
        ((GeneralPath) shape).lineTo(572.668, 268.427);
        ((GeneralPath) shape).lineTo(572.668, 268.404);
        ((GeneralPath) shape).curveTo(573.181, 268.189, 573.27704, 267.655,
                573.27704, 267.14798);
        ((GeneralPath) shape).curveTo(573.27704, 265.718, 572.48804, 265.55298,
                571.26, 265.55298);
        ((GeneralPath) shape).curveTo(569.98303, 265.55298, 569.018, 265.55298,
                569.018, 267.14798);
        ((GeneralPath) shape).curveTo(569.018, 267.72598, 569.141, 268.17798,
                569.677, 268.42798);
        ((GeneralPath) shape).lineTo(569.018, 268.42798);
        ((GeneralPath) shape).moveTo(572.507, 267.443);
        ((GeneralPath) shape).curveTo(572.507, 268.30798, 572.151, 268.427,
                571.26, 268.427);
        ((GeneralPath) shape).curveTo(570.269, 268.427, 569.77, 268.427,
                569.77, 267.443);
        ((GeneralPath) shape).curveTo(569.77, 266.552, 570.318, 266.552,
                571.26, 266.552);
        ((GeneralPath) shape).curveTo(572.31, 266.552, 572.507, 266.751,
                572.507, 267.443);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(571.522, 271.267);
        ((GeneralPath) shape).curveTo(572.311, 271.267, 572.506, 271.324,
                572.506, 272.193);
        ((GeneralPath) shape).curveTo(572.506, 273.01498, 572.438, 273.142,
                571.522, 273.142);
        ((GeneralPath) shape).lineTo(571.522, 271.267);
        ((GeneralPath) shape).moveTo(570.353, 273.142);
        ((GeneralPath) shape).curveTo(569.77, 273.142, 569.77, 272.751, 569.77,
                272.193);
        ((GeneralPath) shape).curveTo(569.77, 271.28998, 570.041, 271.267,
                570.89703, 271.267);
        ((GeneralPath) shape).lineTo(570.89703, 274.142);
        ((GeneralPath) shape).curveTo(572.76306, 274.142, 573.27704, 273.912,
                573.27704, 272.193);
        ((GeneralPath) shape).curveTo(573.27704, 270.505, 572.60504, 270.267,
                571.06903, 270.267);
        ((GeneralPath) shape).curveTo(569.50903, 270.267, 569.018, 270.593,
                569.018, 272.193);
        ((GeneralPath) shape).curveTo(569.018, 273.386, 569.081, 274.142,
                570.353, 274.142);
        ((GeneralPath) shape).lineTo(570.353, 273.142);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(571.021, 281.131);
        ((GeneralPath) shape).lineTo(571.021, 279.049);
        ((GeneralPath) shape).lineTo(574.146, 280.078);
        ((GeneralPath) shape).lineTo(574.146, 280.094);
        ((GeneralPath) shape).lineTo(571.021, 281.131);
        ((GeneralPath) shape).moveTo(570.145, 281.389);
        ((GeneralPath) shape).lineTo(569.018, 281.784);
        ((GeneralPath) shape).lineTo(569.018, 282.958);
        ((GeneralPath) shape).lineTo(575.03, 280.915);
        ((GeneralPath) shape).lineTo(575.03, 279.21002);
        ((GeneralPath) shape).lineTo(569.018, 277.208);
        ((GeneralPath) shape).lineTo(569.018, 278.407);
        ((GeneralPath) shape).lineTo(570.145, 278.78403);
        ((GeneralPath) shape).lineTo(570.145, 281.389);
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
        // _0_232 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(573.276, 283.282);
        ((GeneralPath) shape).lineTo(569.017, 283.282);
        ((GeneralPath) shape).lineTo(569.017, 284.282);
        ((GeneralPath) shape).lineTo(571.616, 284.282);
        ((GeneralPath) shape).curveTo(572.16205, 284.282, 572.507, 284.46802,
                572.507, 285.104);
        ((GeneralPath) shape).curveTo(572.507, 285.614, 572.25903, 285.657,
                571.83405, 285.657);
        ((GeneralPath) shape).lineTo(571.616, 285.657);
        ((GeneralPath) shape).lineTo(571.616, 286.657);
        ((GeneralPath) shape).lineTo(571.953, 286.657);
        ((GeneralPath) shape).curveTo(572.747, 286.657, 573.276, 286.427,
                573.276, 285.483);
        ((GeneralPath) shape).curveTo(573.276, 284.96, 573.142, 284.497,
                572.687, 284.282);
        ((GeneralPath) shape).lineTo(573.276, 284.282);
        ((GeneralPath) shape).lineTo(573.276, 283.282);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(573.276, 287.146);
        ((GeneralPath) shape).lineTo(569.017, 287.146);
        ((GeneralPath) shape).lineTo(569.017, 288.146);
        ((GeneralPath) shape).lineTo(571.39105, 288.146);
        ((GeneralPath) shape).curveTo(572.13605, 288.146, 572.50604, 288.316,
                572.50604, 289.177);
        ((GeneralPath) shape).curveTo(572.50604, 289.76102, 572.29706, 289.896,
                571.76904, 289.896);
        ((GeneralPath) shape).lineTo(569.017, 289.896);
        ((GeneralPath) shape).lineTo(569.017, 290.896);
        ((GeneralPath) shape).lineTo(571.39105, 290.896);
        ((GeneralPath) shape).curveTo(572.13605, 290.896, 572.50604, 291.052,
                572.50604, 291.853);
        ((GeneralPath) shape).curveTo(572.50604, 292.396, 572.29706, 292.521,
                571.76904, 292.521);
        ((GeneralPath) shape).lineTo(569.017, 292.521);
        ((GeneralPath) shape).lineTo(569.017, 293.521);
        ((GeneralPath) shape).lineTo(571.86505, 293.521);
        ((GeneralPath) shape).curveTo(572.8981, 293.521, 573.27606, 293.171,
                573.27606, 292.113);
        ((GeneralPath) shape).curveTo(573.27606, 291.562, 573.11505, 290.99802,
                572.53204, 290.808);
        ((GeneralPath) shape).lineTo(572.53204, 290.777);
        ((GeneralPath) shape).curveTo(573.14, 290.665, 573.27606, 290.064,
                573.27606, 289.54);
        ((GeneralPath) shape).curveTo(573.27606, 288.982, 573.15607, 288.38202,
                572.62604, 288.146);
        ((GeneralPath) shape).lineTo(573.27606, 288.146);
        ((GeneralPath) shape).lineTo(573.27606, 287.146);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(572.507, 296.321);
        ((GeneralPath) shape).curveTo(572.507, 297.18802, 572.252, 297.259,
                571.125, 297.259);
        ((GeneralPath) shape).curveTo(570.016, 297.259, 569.77, 297.189,
                569.77, 296.321);
        ((GeneralPath) shape).curveTo(569.77, 295.45303, 570.016, 295.38303,
                571.125, 295.38303);
        ((GeneralPath) shape).curveTo(572.252, 295.384, 572.507, 295.454,
                572.507, 296.321);
        ((GeneralPath) shape).moveTo(573.276, 296.321);
        ((GeneralPath) shape).curveTo(573.276, 294.60202, 572.757, 294.38303,
                571.132, 294.38303);
        ((GeneralPath) shape).curveTo(569.529, 294.38303, 569.017, 294.60202,
                569.017, 296.321);
        ((GeneralPath) shape).curveTo(569.017, 298.04, 569.53, 298.259,
                571.132, 298.259);
        ((GeneralPath) shape).curveTo(572.758, 298.259, 573.276, 298.04,
                573.276, 296.321);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(573.276, 299.011);
        ((GeneralPath) shape).lineTo(569.017, 299.011);
        ((GeneralPath) shape).lineTo(569.017, 300.011);
        ((GeneralPath) shape).lineTo(571.616, 300.011);
        ((GeneralPath) shape).curveTo(572.16205, 300.011, 572.507, 300.197,
                572.507, 300.83298);
        ((GeneralPath) shape).curveTo(572.507, 301.343, 572.25903, 301.386,
                571.83405, 301.386);
        ((GeneralPath) shape).lineTo(571.616, 301.386);
        ((GeneralPath) shape).lineTo(571.616, 302.386);
        ((GeneralPath) shape).lineTo(571.953, 302.386);
        ((GeneralPath) shape).curveTo(572.747, 302.386, 573.276, 302.155,
                573.276, 301.21198);
        ((GeneralPath) shape).curveTo(573.276, 300.68896, 573.142, 300.22498,
                572.687, 300.011);
        ((GeneralPath) shape).lineTo(573.276, 300.011);
        ((GeneralPath) shape).lineTo(573.276, 299.011);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(404.438, 322.073);
        ((GeneralPath) shape).lineTo(402.418, 322.073);
        ((GeneralPath) shape).lineTo(402.418, 320.52);
        ((GeneralPath) shape).curveTo(402.418, 319.72098, 402.568, 319.573,
                403.404, 319.573);
        ((GeneralPath) shape).curveTo(404.27, 319.573, 404.43698, 319.768,
                404.43698, 320.569);
        ((GeneralPath) shape).lineTo(404.43698, 322.073);
        ((GeneralPath) shape).moveTo(405.44, 320.325);
        ((GeneralPath) shape).curveTo(405.44, 319.778, 405.795, 319.573,
                406.308, 319.573);
        ((GeneralPath) shape).lineTo(407.44302, 319.573);
        ((GeneralPath) shape).lineTo(407.44302, 318.448);
        ((GeneralPath) shape).lineTo(406.31003, 318.448);
        ((GeneralPath) shape).curveTo(405.45404, 318.448, 405.00702, 318.651,
                404.93503, 319.483);
        ((GeneralPath) shape).lineTo(404.90103, 319.483);
        ((GeneralPath) shape).curveTo(404.74902, 318.448, 404.09903, 318.448,
                403.22205, 318.448);
        ((GeneralPath) shape).curveTo(401.84503, 318.448, 401.43204, 318.928,
                401.43204, 320.182);
        ((GeneralPath) shape).lineTo(401.43204, 323.198);
        ((GeneralPath) shape).lineTo(407.44302, 323.198);
        ((GeneralPath) shape).lineTo(407.44302, 322.073);
        ((GeneralPath) shape).lineTo(405.44003, 322.073);
        ((GeneralPath) shape).lineTo(405.44003, 320.325);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(404.939, 316.568);
        ((GeneralPath) shape).curveTo(404.15, 316.568, 403.955, 316.511,
                403.955, 315.642);
        ((GeneralPath) shape).curveTo(403.955, 314.82, 404.02298, 314.693,
                404.939, 314.693);
        ((GeneralPath) shape).lineTo(404.939, 316.568);
        ((GeneralPath) shape).moveTo(406.109, 314.693);
        ((GeneralPath) shape).curveTo(406.69202, 314.693, 406.69202, 315.08398,
                406.69202, 315.642);
        ((GeneralPath) shape).curveTo(406.69202, 316.544, 406.42, 316.568,
                405.565, 316.568);
        ((GeneralPath) shape).lineTo(405.565, 313.693);
        ((GeneralPath) shape).curveTo(403.698, 313.693, 403.185, 313.923,
                403.185, 315.642);
        ((GeneralPath) shape).curveTo(403.185, 317.33, 403.857, 317.568,
                405.392, 317.568);
        ((GeneralPath) shape).curveTo(406.952, 317.568, 407.443, 317.242,
                407.443, 315.642);
        ((GeneralPath) shape).curveTo(407.443, 314.449, 407.38, 313.693,
                406.10898, 313.693);
        ((GeneralPath) shape).lineTo(406.10898, 314.693);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(407.443, 310.154);
        ((GeneralPath) shape).lineTo(407.443, 309.154);
        ((GeneralPath) shape).lineTo(404.805, 309.154);
        ((GeneralPath) shape).curveTo(403.395, 309.154, 403.185, 309.723,
                403.185, 311.08398);
        ((GeneralPath) shape).curveTo(403.185, 312.037, 403.185, 312.904,
                404.431, 312.904);
        ((GeneralPath) shape).lineTo(404.431, 311.904);
        ((GeneralPath) shape).curveTo(403.913, 311.904, 403.873, 311.539,
                403.873, 311.08398);
        ((GeneralPath) shape).curveTo(403.873, 310.21298, 404.102, 310.154,
                404.75198, 310.154);
        ((GeneralPath) shape).lineTo(405.335, 310.154);
        ((GeneralPath) shape).lineTo(405.335, 310.18698);
        ((GeneralPath) shape).curveTo(404.813, 310.43497, 404.813, 310.95798,
                404.813, 311.49);
        ((GeneralPath) shape).curveTo(404.813, 312.512, 405.093, 313.029,
                406.099, 313.029);
        ((GeneralPath) shape).curveTo(407.242, 313.029, 407.443, 312.42398,
                407.443, 311.49);
        ((GeneralPath) shape).curveTo(407.443, 310.98398, 407.443, 310.357,
                406.823, 310.154);
        ((GeneralPath) shape).lineTo(407.443, 310.154);
        ((GeneralPath) shape).moveTo(405.565, 311.117);
        ((GeneralPath) shape).curveTo(405.565, 310.601, 405.565, 310.154,
                406.099, 310.154);
        ((GeneralPath) shape).curveTo(406.649, 310.154, 406.692, 310.56,
                406.692, 311.117);
        ((GeneralPath) shape).curveTo(406.692, 311.79102, 406.641, 312.029,
                406.099, 312.029);
        ((GeneralPath) shape).curveTo(405.565, 312.029, 405.565, 311.646,
                405.565, 311.117);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(403.186, 308.338);
        ((GeneralPath) shape).lineTo(407.444, 308.338);
        ((GeneralPath) shape).lineTo(407.444, 307.338);
        ((GeneralPath) shape).lineTo(404.845, 307.338);
        ((GeneralPath) shape).curveTo(404.299, 307.338, 403.955, 307.152,
                403.955, 306.51602);
        ((GeneralPath) shape).curveTo(403.955, 306.006, 404.20297, 305.963,
                404.628, 305.963);
        ((GeneralPath) shape).lineTo(404.845, 305.963);
        ((GeneralPath) shape).lineTo(404.845, 304.963);
        ((GeneralPath) shape).lineTo(404.509, 304.963);
        ((GeneralPath) shape).curveTo(403.714, 304.963, 403.186, 305.19302,
                403.186, 306.13702);
        ((GeneralPath) shape).curveTo(403.186, 306.66003, 403.319, 307.12302,
                403.775, 307.338);
        ((GeneralPath) shape).lineTo(403.186, 307.338);
        ((GeneralPath) shape).lineTo(403.186, 308.338);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(401.433, 300.557);
        ((GeneralPath) shape).lineTo(401.433, 301.682);
        ((GeneralPath) shape).lineTo(407.443, 301.682);
        ((GeneralPath) shape).lineTo(407.443, 297.756);
        ((GeneralPath) shape).lineTo(406.458, 297.756);
        ((GeneralPath) shape).lineTo(406.458, 300.557);
        ((GeneralPath) shape).lineTo(401.433, 300.557);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(404.939, 296.308);
        ((GeneralPath) shape).curveTo(404.15, 296.308, 403.955, 296.251,
                403.955, 295.38202);
        ((GeneralPath) shape).curveTo(403.955, 294.56003, 404.02298, 294.433,
                404.939, 294.433);
        ((GeneralPath) shape).lineTo(404.939, 296.308);
        ((GeneralPath) shape).moveTo(406.109, 294.433);
        ((GeneralPath) shape).curveTo(406.69202, 294.433, 406.69202, 294.824,
                406.69202, 295.38202);
        ((GeneralPath) shape).curveTo(406.69202, 296.28403, 406.42, 296.308,
                405.565, 296.308);
        ((GeneralPath) shape).lineTo(405.565, 293.433);
        ((GeneralPath) shape).curveTo(403.698, 293.433, 403.185, 293.66302,
                403.185, 295.38202);
        ((GeneralPath) shape).curveTo(403.185, 297.07, 403.857, 297.308,
                405.392, 297.308);
        ((GeneralPath) shape).curveTo(406.952, 297.308, 407.443, 296.98203,
                407.443, 295.38202);
        ((GeneralPath) shape).curveTo(407.443, 294.18903, 407.38, 293.433,
                406.10898, 293.433);
        ((GeneralPath) shape).lineTo(406.10898, 294.433);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(403.186, 290.37);
        ((GeneralPath) shape).lineTo(403.186, 291.393);
        ((GeneralPath) shape).lineTo(402.948, 291.393);
        ((GeneralPath) shape).curveTo(402.463, 291.393, 402.202, 291.393,
                402.202, 290.70502);
        ((GeneralPath) shape).lineTo(402.202, 290.40002);
        ((GeneralPath) shape).lineTo(401.43298, 290.40002);
        ((GeneralPath) shape).lineTo(401.43298, 291.05402);
        ((GeneralPath) shape).curveTo(401.43298, 292.15402, 401.84598,
                292.39203, 402.82797, 292.39203);
        ((GeneralPath) shape).lineTo(403.18597, 292.39203);
        ((GeneralPath) shape).lineTo(403.18597, 292.98404);
        ((GeneralPath) shape).lineTo(403.938, 292.98404);
        ((GeneralPath) shape).lineTo(403.938, 292.39203);
        ((GeneralPath) shape).lineTo(407.444, 292.39203);
        ((GeneralPath) shape).lineTo(407.444, 291.39203);
        ((GeneralPath) shape).lineTo(403.938, 291.39203);
        ((GeneralPath) shape).lineTo(403.938, 290.36902);
        ((GeneralPath) shape).lineTo(403.186, 290.36902);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(403.938, 290.407);
        ((GeneralPath) shape).lineTo(403.938, 289.88);
        ((GeneralPath) shape).lineTo(406.13098, 289.88);
        ((GeneralPath) shape).curveTo(407.18997, 289.88, 407.44397, 289.54,
                407.44397, 288.423);
        ((GeneralPath) shape).curveTo(407.44397, 287.327, 407.06097, 287.005,
                405.79095, 287.005);
        ((GeneralPath) shape).lineTo(405.79095, 287.88);
        ((GeneralPath) shape).curveTo(406.23697, 287.88, 406.69296, 287.81702,
                406.69296, 288.423);
        ((GeneralPath) shape).curveTo(406.69296, 288.88, 406.51495, 288.88,
                406.12396, 288.88);
        ((GeneralPath) shape).lineTo(403.93796, 288.88);
        ((GeneralPath) shape).lineTo(403.93796, 287.21802);
        ((GeneralPath) shape).lineTo(403.18594, 287.21802);
        ((GeneralPath) shape).lineTo(403.18594, 288.88);
        ((GeneralPath) shape).lineTo(402.21295, 288.88);
        ((GeneralPath) shape).lineTo(402.21295, 289.88);
        ((GeneralPath) shape).lineTo(403.18594, 289.88);
        ((GeneralPath) shape).lineTo(403.18594, 290.407);
        ((GeneralPath) shape).lineTo(403.938, 290.407);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(405.434, 283.935);
        ((GeneralPath) shape).lineTo(405.712, 283.935);
        ((GeneralPath) shape).curveTo(407.443, 283.935, 407.443, 282.746,
                407.443, 281.414);
        ((GeneralPath) shape).curveTo(407.443, 279.864, 407.29498, 279.06,
                405.561, 279.06);
        ((GeneralPath) shape).curveTo(404.044, 279.06, 403.949, 279.57,
                403.854, 281.435);
        ((GeneralPath) shape).curveTo(403.79102, 282.652, 403.77, 282.81,
                403.13202, 282.81);
        ((GeneralPath) shape).curveTo(402.488, 282.81, 402.377, 282.619,
                402.377, 281.427);
        ((GeneralPath) shape).curveTo(402.377, 280.585, 402.40002, 280.31,
                403.052, 280.31);
        ((GeneralPath) shape).lineTo(403.243, 280.31);
        ((GeneralPath) shape).lineTo(403.243, 279.185);
        ((GeneralPath) shape).lineTo(403.04602, 279.185);
        ((GeneralPath) shape).curveTo(401.432, 279.185, 401.432, 280.154,
                401.432, 281.427);
        ((GeneralPath) shape).curveTo(401.432, 282.89, 401.432, 283.935,
                403.13202, 283.935);
        ((GeneralPath) shape).curveTo(404.742, 283.935, 404.69403, 282.906,
                404.77402, 281.593);
        ((GeneralPath) shape).curveTo(404.821, 280.65997, 404.71002, 280.185,
                405.561, 280.185);
        ((GeneralPath) shape).curveTo(406.251, 280.185, 406.441, 280.335,
                406.441, 281.427);
        ((GeneralPath) shape).curveTo(406.441, 282.509, 406.394, 282.81,
                405.712, 282.81);
        ((GeneralPath) shape).lineTo(405.434, 282.81);
        ((GeneralPath) shape).lineTo(405.434, 283.935);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(401.433, 277.321);
        ((GeneralPath) shape).lineTo(401.433, 278.321);
        ((GeneralPath) shape).lineTo(402.289, 278.321);
        ((GeneralPath) shape).lineTo(402.289, 277.321);
        ((GeneralPath) shape).lineTo(401.433, 277.321);
        ((GeneralPath) shape).moveTo(403.186, 277.321);
        ((GeneralPath) shape).lineTo(403.186, 278.321);
        ((GeneralPath) shape).lineTo(407.444, 278.321);
        ((GeneralPath) shape).lineTo(407.444, 277.321);
        ((GeneralPath) shape).lineTo(403.186, 277.321);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(407.443, 273.704);
        ((GeneralPath) shape).lineTo(407.443, 272.704);
        ((GeneralPath) shape).lineTo(401.432, 272.704);
        ((GeneralPath) shape).lineTo(401.432, 273.704);
        ((GeneralPath) shape).lineTo(403.793, 273.704);
        ((GeneralPath) shape).lineTo(403.793, 273.72702);
        ((GeneralPath) shape).curveTo(403.28, 273.94202, 403.185, 274.476,
                403.185, 274.98303);
        ((GeneralPath) shape).curveTo(403.185, 276.41302, 403.974, 276.57803,
                405.201, 276.57803);
        ((GeneralPath) shape).curveTo(406.479, 276.57803, 407.443, 276.57803,
                407.443, 274.98303);
        ((GeneralPath) shape).curveTo(407.443, 274.40503, 407.32, 273.95303,
                406.784, 273.70303);
        ((GeneralPath) shape).lineTo(407.443, 273.70303);
        ((GeneralPath) shape).moveTo(403.955, 274.688);
        ((GeneralPath) shape).curveTo(403.955, 273.823, 404.31, 273.70398,
                405.201, 273.70398);
        ((GeneralPath) shape).curveTo(406.193, 273.70398, 406.692, 273.70398,
                406.692, 274.688);
        ((GeneralPath) shape).curveTo(406.692, 275.57898, 406.14398, 275.57898,
                405.201, 275.57898);
        ((GeneralPath) shape).curveTo(404.152, 275.579, 403.955, 275.38,
                403.955, 274.688);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(404.939, 270.856);
        ((GeneralPath) shape).curveTo(404.15, 270.856, 403.955, 270.79898,
                403.955, 269.93);
        ((GeneralPath) shape).curveTo(403.955, 269.108, 404.02298, 268.981,
                404.939, 268.981);
        ((GeneralPath) shape).lineTo(404.939, 270.856);
        ((GeneralPath) shape).moveTo(406.109, 268.981);
        ((GeneralPath) shape).curveTo(406.69202, 268.981, 406.69202, 269.37198,
                406.69202, 269.93);
        ((GeneralPath) shape).curveTo(406.69202, 270.833, 406.42, 270.856,
                405.565, 270.856);
        ((GeneralPath) shape).lineTo(405.565, 267.981);
        ((GeneralPath) shape).curveTo(403.698, 267.981, 403.185, 268.211,
                403.185, 269.93);
        ((GeneralPath) shape).curveTo(403.185, 271.61798, 403.857, 271.856,
                405.392, 271.856);
        ((GeneralPath) shape).curveTo(406.952, 271.856, 407.443, 271.529,
                407.443, 269.93);
        ((GeneralPath) shape).curveTo(407.443, 268.737, 407.38, 267.981,
                406.10898, 267.981);
        ((GeneralPath) shape).lineTo(406.10898, 268.981);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(405.44, 260.984);
        ((GeneralPath) shape).lineTo(405.44, 263.066);
        ((GeneralPath) shape).lineTo(402.314, 262.03702);
        ((GeneralPath) shape).lineTo(402.314, 262.02103);
        ((GeneralPath) shape).lineTo(405.44, 260.984);
        ((GeneralPath) shape).moveTo(406.316, 260.726);
        ((GeneralPath) shape).lineTo(407.44302, 260.33102);
        ((GeneralPath) shape).lineTo(407.44302, 259.157);
        ((GeneralPath) shape).lineTo(401.43204, 261.2);
        ((GeneralPath) shape).lineTo(401.43204, 262.905);
        ((GeneralPath) shape).lineTo(407.44302, 264.907);
        ((GeneralPath) shape).lineTo(407.44302, 263.708);
        ((GeneralPath) shape).lineTo(406.316, 263.331);
        ((GeneralPath) shape).lineTo(406.316, 260.726);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(403.186, 258.882);
        ((GeneralPath) shape).lineTo(407.444, 258.882);
        ((GeneralPath) shape).lineTo(407.444, 257.882);
        ((GeneralPath) shape).lineTo(404.845, 257.882);
        ((GeneralPath) shape).curveTo(404.299, 257.882, 403.955, 257.69598,
                403.955, 257.06);
        ((GeneralPath) shape).curveTo(403.955, 256.55, 404.20297, 256.507,
                404.628, 256.507);
        ((GeneralPath) shape).lineTo(404.845, 256.507);
        ((GeneralPath) shape).lineTo(404.845, 255.50699);
        ((GeneralPath) shape).lineTo(404.509, 255.50699);
        ((GeneralPath) shape).curveTo(403.714, 255.50699, 403.186, 255.73698,
                403.186, 256.68);
        ((GeneralPath) shape).curveTo(403.186, 257.203, 403.319, 257.667,
                403.775, 257.882);
        ((GeneralPath) shape).lineTo(403.186, 257.882);
        ((GeneralPath) shape).lineTo(403.186, 258.882);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(403.186, 255.009);
        ((GeneralPath) shape).lineTo(407.444, 255.009);
        ((GeneralPath) shape).lineTo(407.444, 254.009);
        ((GeneralPath) shape).lineTo(405.071, 254.009);
        ((GeneralPath) shape).curveTo(404.325, 254.009, 403.95602, 253.839,
                403.95602, 252.978);
        ((GeneralPath) shape).curveTo(403.95602, 252.394, 404.16504, 252.259,
                404.69302, 252.259);
        ((GeneralPath) shape).lineTo(407.44403, 252.259);
        ((GeneralPath) shape).lineTo(407.44403, 251.259);
        ((GeneralPath) shape).lineTo(405.07104, 251.259);
        ((GeneralPath) shape).curveTo(404.32504, 251.259, 403.95605, 251.103,
                403.95605, 250.302);
        ((GeneralPath) shape).curveTo(403.95605, 249.759, 404.16507, 249.634,
                404.69305, 249.634);
        ((GeneralPath) shape).lineTo(407.44406, 249.634);
        ((GeneralPath) shape).lineTo(407.44406, 248.634);
        ((GeneralPath) shape).lineTo(404.59708, 248.634);
        ((GeneralPath) shape).curveTo(403.5641, 248.634, 403.18607, 248.98401,
                403.18607, 250.042);
        ((GeneralPath) shape).curveTo(403.18607, 250.593, 403.34708, 251.15701,
                403.93005, 251.347);
        ((GeneralPath) shape).lineTo(403.93005, 251.378);
        ((GeneralPath) shape).curveTo(403.32104, 251.49, 403.18607, 252.091,
                403.18607, 252.615);
        ((GeneralPath) shape).curveTo(403.18607, 253.173, 403.30505, 253.77301,
                403.83505, 254.009);
        ((GeneralPath) shape).lineTo(403.18607, 254.009);
        ((GeneralPath) shape).lineTo(403.18607, 255.009);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(403.955, 245.826);
        ((GeneralPath) shape).curveTo(403.955, 244.959, 404.20898, 244.888,
                405.336, 244.888);
        ((GeneralPath) shape).curveTo(406.445, 244.888, 406.692, 244.95801,
                406.692, 245.826);
        ((GeneralPath) shape).curveTo(406.692, 246.694, 406.44498, 246.764,
                405.336, 246.764);
        ((GeneralPath) shape).curveTo(404.209, 246.763, 403.955, 246.693,
                403.955, 245.826);
        ((GeneralPath) shape).moveTo(403.186, 245.826);
        ((GeneralPath) shape).curveTo(403.186, 247.545, 403.70502, 247.764,
                405.329, 247.764);
        ((GeneralPath) shape).curveTo(406.932, 247.764, 407.444, 247.54501,
                407.444, 245.826);
        ((GeneralPath) shape).curveTo(407.444, 244.107, 406.931, 243.888,
                405.329, 243.888);
        ((GeneralPath) shape).curveTo(403.704, 243.888, 403.186, 244.107,
                403.186, 245.826);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(403.186, 243.128);
        ((GeneralPath) shape).lineTo(407.444, 243.128);
        ((GeneralPath) shape).lineTo(407.444, 242.128);
        ((GeneralPath) shape).lineTo(404.845, 242.128);
        ((GeneralPath) shape).curveTo(404.299, 242.128, 403.955, 241.942,
                403.955, 241.306);
        ((GeneralPath) shape).curveTo(403.955, 240.796, 404.20297, 240.753,
                404.628, 240.753);
        ((GeneralPath) shape).lineTo(404.845, 240.753);
        ((GeneralPath) shape).lineTo(404.845, 239.753);
        ((GeneralPath) shape).lineTo(404.509, 239.753);
        ((GeneralPath) shape).curveTo(403.714, 239.753, 403.186, 239.983,
                403.186, 240.927);
        ((GeneralPath) shape).curveTo(403.186, 241.45, 403.319, 241.913,
                403.775, 242.128);
        ((GeneralPath) shape).lineTo(403.186, 242.128);
        ((GeneralPath) shape).lineTo(403.186, 243.128);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(402.419, 204.436);
        ((GeneralPath) shape).lineTo(402.419, 201.62);
        ((GeneralPath) shape).lineTo(401.433, 201.62);
        ((GeneralPath) shape).lineTo(401.433, 205.561);
        ((GeneralPath) shape).lineTo(407.443, 205.561);
        ((GeneralPath) shape).lineTo(407.443, 204.436);
        ((GeneralPath) shape).lineTo(404.939, 204.436);
        ((GeneralPath) shape).lineTo(404.939, 201.764);
        ((GeneralPath) shape).lineTo(403.938, 201.764);
        ((GeneralPath) shape).lineTo(403.938, 204.436);
        ((GeneralPath) shape).lineTo(402.419, 204.436);
        g.setPaint(paint);
        g.fill(shape);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(403.186, 201.051);
        ((GeneralPath) shape).lineTo(407.444, 201.051);
        ((GeneralPath) shape).lineTo(407.444, 200.051);
        ((GeneralPath) shape).lineTo(404.845, 200.051);
        ((GeneralPath) shape).curveTo(404.299, 200.051, 403.955, 199.86499,
                403.955, 199.22899);
        ((GeneralPath) shape).curveTo(403.955, 198.719, 404.20297, 198.676,
                404.628, 198.676);
        ((GeneralPath) shape).lineTo(404.845, 198.676);
        ((GeneralPath) shape).lineTo(404.845, 197.676);
        ((GeneralPath) shape).lineTo(404.509, 197.676);
        ((GeneralPath) shape).curveTo(403.714, 197.676, 403.186, 197.90599,
                403.186, 198.84999);
        ((GeneralPath) shape).curveTo(403.186, 199.37299, 403.319, 199.83598,
                403.775, 200.051);
        ((GeneralPath) shape).lineTo(403.186, 200.051);
        ((GeneralPath) shape).lineTo(403.186, 201.051);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(403.955, 195.366);
        ((GeneralPath) shape).curveTo(403.955, 194.499, 404.20898, 194.428,
                405.336, 194.428);
        ((GeneralPath) shape).curveTo(406.445, 194.428, 406.692, 194.498,
                406.692, 195.366);
        ((GeneralPath) shape).curveTo(406.692, 196.234, 406.44498, 196.304,
                405.336, 196.304);
        ((GeneralPath) shape).curveTo(404.209, 196.304, 403.955, 196.233,
                403.955, 195.366);
        ((GeneralPath) shape).moveTo(403.186, 195.366);
        ((GeneralPath) shape).curveTo(403.186, 197.08499, 403.70502, 197.304,
                405.329, 197.304);
        ((GeneralPath) shape).curveTo(406.932, 197.304, 407.444, 197.085,
                407.444, 195.366);
        ((GeneralPath) shape).curveTo(407.444, 193.64699, 406.931, 193.428,
                405.329, 193.428);
        ((GeneralPath) shape).curveTo(403.704, 193.429, 403.186, 193.647,
                403.186, 195.366);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(403.186, 192.543);
        ((GeneralPath) shape).lineTo(407.444, 192.543);
        ((GeneralPath) shape).lineTo(407.444, 191.543);
        ((GeneralPath) shape).lineTo(405.126, 191.543);
        ((GeneralPath) shape).curveTo(404.372, 191.543, 403.956, 191.426,
                403.956, 190.563);
        ((GeneralPath) shape).curveTo(403.956, 189.92601, 404.165, 189.793,
                404.77298, 189.793);
        ((GeneralPath) shape).lineTo(407.44397, 189.793);
        ((GeneralPath) shape).lineTo(407.44397, 188.793);
        ((GeneralPath) shape).lineTo(404.66998, 188.793);
        ((GeneralPath) shape).curveTo(403.645, 188.793, 403.18698, 189.12599,
                403.18698, 190.217);
        ((GeneralPath) shape).curveTo(403.18698, 190.797, 403.24997, 191.291,
                403.86197, 191.512);
        ((GeneralPath) shape).lineTo(403.86197, 191.543);
        ((GeneralPath) shape).lineTo(403.18698, 191.543);
        ((GeneralPath) shape).lineTo(403.18698, 192.543);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(403.938, 188.308);
        ((GeneralPath) shape).lineTo(403.938, 187.78);
        ((GeneralPath) shape).lineTo(406.13098, 187.78);
        ((GeneralPath) shape).curveTo(407.18997, 187.78, 407.44397, 187.441,
                407.44397, 186.323);
        ((GeneralPath) shape).curveTo(407.44397, 185.227, 407.06097, 184.905,
                405.79095, 184.905);
        ((GeneralPath) shape).lineTo(405.79095, 185.78);
        ((GeneralPath) shape).curveTo(406.23697, 185.78, 406.69296, 185.717,
                406.69296, 186.323);
        ((GeneralPath) shape).curveTo(406.69296, 186.78, 406.51495, 186.78,
                406.12396, 186.78);
        ((GeneralPath) shape).lineTo(403.93796, 186.78);
        ((GeneralPath) shape).lineTo(403.93796, 185.118);
        ((GeneralPath) shape).lineTo(403.18594, 185.118);
        ((GeneralPath) shape).lineTo(403.18594, 186.78);
        ((GeneralPath) shape).lineTo(402.21295, 186.78);
        ((GeneralPath) shape).lineTo(402.21295, 187.78);
        ((GeneralPath) shape).lineTo(403.18594, 187.78);
        ((GeneralPath) shape).lineTo(403.18594, 188.308);
        ((GeneralPath) shape).lineTo(403.938, 188.308);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(401.433, 180.492);
        ((GeneralPath) shape).lineTo(401.433, 181.617);
        ((GeneralPath) shape).lineTo(407.443, 181.617);
        ((GeneralPath) shape).lineTo(407.443, 177.691);
        ((GeneralPath) shape).lineTo(406.458, 177.691);
        ((GeneralPath) shape).lineTo(406.458, 180.492);
        ((GeneralPath) shape).lineTo(401.433, 180.492);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(404.939, 176.243);
        ((GeneralPath) shape).curveTo(404.15, 176.243, 403.955, 176.18599,
                403.955, 175.317);
        ((GeneralPath) shape).curveTo(403.955, 174.495, 404.02298, 174.368,
                404.939, 174.368);
        ((GeneralPath) shape).lineTo(404.939, 176.243);
        ((GeneralPath) shape).moveTo(406.109, 174.368);
        ((GeneralPath) shape).curveTo(406.69202, 174.368, 406.69202, 174.759,
                406.69202, 175.317);
        ((GeneralPath) shape).curveTo(406.69202, 176.219, 406.42, 176.243,
                405.565, 176.243);
        ((GeneralPath) shape).lineTo(405.565, 173.368);
        ((GeneralPath) shape).curveTo(403.698, 173.368, 403.185, 173.599,
                403.185, 175.317);
        ((GeneralPath) shape).curveTo(403.185, 177.005, 403.857, 177.243,
                405.392, 177.243);
        ((GeneralPath) shape).curveTo(406.952, 177.243, 407.443, 176.91699,
                407.443, 175.317);
        ((GeneralPath) shape).curveTo(407.443, 174.12401, 407.38, 173.368,
                406.10898, 173.368);
        ((GeneralPath) shape).lineTo(406.10898, 174.368);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(403.186, 170.306);
        ((GeneralPath) shape).lineTo(403.186, 171.329);
        ((GeneralPath) shape).lineTo(402.948, 171.329);
        ((GeneralPath) shape).curveTo(402.463, 171.329, 402.202, 171.329,
                402.202, 170.64099);
        ((GeneralPath) shape).lineTo(402.202, 170.336);
        ((GeneralPath) shape).lineTo(401.43298, 170.336);
        ((GeneralPath) shape).lineTo(401.43298, 170.99);
        ((GeneralPath) shape).curveTo(401.43298, 172.09001, 401.84598, 172.328,
                402.82797, 172.328);
        ((GeneralPath) shape).lineTo(403.18597, 172.328);
        ((GeneralPath) shape).lineTo(403.18597, 172.92);
        ((GeneralPath) shape).lineTo(403.938, 172.92);
        ((GeneralPath) shape).lineTo(403.938, 172.328);
        ((GeneralPath) shape).lineTo(407.444, 172.328);
        ((GeneralPath) shape).lineTo(407.444, 171.328);
        ((GeneralPath) shape).lineTo(403.938, 171.328);
        ((GeneralPath) shape).lineTo(403.938, 170.30501);
        ((GeneralPath) shape).lineTo(403.186, 170.30501);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(403.938, 170.343);
        ((GeneralPath) shape).lineTo(403.938, 169.815);
        ((GeneralPath) shape).lineTo(406.13098, 169.815);
        ((GeneralPath) shape).curveTo(407.18997, 169.815, 407.44397, 169.476,
                407.44397, 168.358);
        ((GeneralPath) shape).curveTo(407.44397, 167.26201, 407.06097, 166.94,
                405.79095, 166.94);
        ((GeneralPath) shape).lineTo(405.79095, 167.815);
        ((GeneralPath) shape).curveTo(406.23697, 167.815, 406.69296, 167.752,
                406.69296, 168.358);
        ((GeneralPath) shape).curveTo(406.69296, 168.815, 406.51495, 168.815,
                406.12396, 168.815);
        ((GeneralPath) shape).lineTo(403.93796, 168.815);
        ((GeneralPath) shape).lineTo(403.93796, 167.153);
        ((GeneralPath) shape).lineTo(403.18594, 167.153);
        ((GeneralPath) shape).lineTo(403.18594, 168.815);
        ((GeneralPath) shape).lineTo(402.21295, 168.815);
        ((GeneralPath) shape).lineTo(402.21295, 169.815);
        ((GeneralPath) shape).lineTo(403.18594, 169.815);
        ((GeneralPath) shape).lineTo(403.18594, 170.343);
        ((GeneralPath) shape).lineTo(403.938, 170.343);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(405.434, 163.87);
        ((GeneralPath) shape).lineTo(405.712, 163.87);
        ((GeneralPath) shape).curveTo(407.443, 163.87, 407.443, 162.681,
                407.443, 161.349);
        ((GeneralPath) shape).curveTo(407.443, 159.798, 407.29498, 158.995,
                405.561, 158.995);
        ((GeneralPath) shape).curveTo(404.044, 158.995, 403.949, 159.50499,
                403.854, 161.37);
        ((GeneralPath) shape).curveTo(403.79102, 162.58699, 403.77, 162.745,
                403.13202, 162.745);
        ((GeneralPath) shape).curveTo(402.488, 162.745, 402.377, 162.554,
                402.377, 161.362);
        ((GeneralPath) shape).curveTo(402.377, 160.52, 402.40002, 160.245,
                403.052, 160.245);
        ((GeneralPath) shape).lineTo(403.243, 160.245);
        ((GeneralPath) shape).lineTo(403.243, 159.12);
        ((GeneralPath) shape).lineTo(403.04602, 159.12);
        ((GeneralPath) shape).curveTo(401.432, 159.12, 401.432, 160.08899,
                401.432, 161.362);
        ((GeneralPath) shape).curveTo(401.432, 162.825, 401.432, 163.87,
                403.13202, 163.87);
        ((GeneralPath) shape).curveTo(404.742, 163.87, 404.69403, 162.84099,
                404.77402, 161.528);
        ((GeneralPath) shape).curveTo(404.821, 160.594, 404.71002, 160.12,
                405.561, 160.12);
        ((GeneralPath) shape).curveTo(406.251, 160.12, 406.441, 160.26999,
                406.441, 161.362);
        ((GeneralPath) shape).curveTo(406.441, 162.444, 406.394, 162.745,
                405.712, 162.745);
        ((GeneralPath) shape).lineTo(405.434, 162.745);
        ((GeneralPath) shape).lineTo(405.434, 163.87);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(401.433, 157.257);
        ((GeneralPath) shape).lineTo(401.433, 158.257);
        ((GeneralPath) shape).lineTo(402.289, 158.257);
        ((GeneralPath) shape).lineTo(402.289, 157.257);
        ((GeneralPath) shape).lineTo(401.433, 157.257);
        ((GeneralPath) shape).moveTo(403.186, 157.257);
        ((GeneralPath) shape).lineTo(403.186, 158.257);
        ((GeneralPath) shape).lineTo(407.444, 158.257);
        ((GeneralPath) shape).lineTo(407.444, 157.257);
        ((GeneralPath) shape).lineTo(403.186, 157.257);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(407.443, 153.64);
        ((GeneralPath) shape).lineTo(407.443, 152.64);
        ((GeneralPath) shape).lineTo(401.432, 152.64);
        ((GeneralPath) shape).lineTo(401.432, 153.64);
        ((GeneralPath) shape).lineTo(403.793, 153.64);
        ((GeneralPath) shape).lineTo(403.793, 153.663);
        ((GeneralPath) shape).curveTo(403.28, 153.877, 403.185, 154.411,
                403.185, 154.91899);
        ((GeneralPath) shape).curveTo(403.185, 156.34898, 403.974, 156.51498,
                405.201, 156.51498);
        ((GeneralPath) shape).curveTo(406.479, 156.51498, 407.443, 156.51498,
                407.443, 154.91899);
        ((GeneralPath) shape).curveTo(407.443, 154.34099, 407.32, 153.88998,
                406.784, 153.63998);
        ((GeneralPath) shape).lineTo(407.443, 153.63998);
        ((GeneralPath) shape).moveTo(403.955, 154.625);
        ((GeneralPath) shape).curveTo(403.955, 153.76, 404.31, 153.641,
                405.201, 153.641);
        ((GeneralPath) shape).curveTo(406.193, 153.641, 406.692, 153.641,
                406.692, 154.625);
        ((GeneralPath) shape).curveTo(406.692, 155.516, 406.14398, 155.516,
                405.201, 155.516);
        ((GeneralPath) shape).curveTo(404.152, 155.515, 403.955, 155.316,
                403.955, 154.625);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(404.939, 150.792);
        ((GeneralPath) shape).curveTo(404.15, 150.792, 403.955, 150.735,
                403.955, 149.86601);
        ((GeneralPath) shape).curveTo(403.955, 149.044, 404.02298, 148.917,
                404.939, 148.917);
        ((GeneralPath) shape).lineTo(404.939, 150.792);
        ((GeneralPath) shape).moveTo(406.109, 148.917);
        ((GeneralPath) shape).curveTo(406.69202, 148.917, 406.69202, 149.30801,
                406.69202, 149.86601);
        ((GeneralPath) shape).curveTo(406.69202, 150.76901, 406.42, 150.792,
                405.565, 150.792);
        ((GeneralPath) shape).lineTo(405.565, 147.917);
        ((GeneralPath) shape).curveTo(403.698, 147.917, 403.185, 148.147,
                403.185, 149.86601);
        ((GeneralPath) shape).curveTo(403.185, 151.55402, 403.857, 151.792,
                405.392, 151.792);
        ((GeneralPath) shape).curveTo(406.952, 151.792, 407.443, 151.466,
                407.443, 149.86601);
        ((GeneralPath) shape).curveTo(407.443, 148.67302, 407.38, 147.917,
                406.10898, 147.917);
        ((GeneralPath) shape).lineTo(406.10898, 148.917);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(405.44, 140.92);
        ((GeneralPath) shape).lineTo(405.44, 143.002);
        ((GeneralPath) shape).lineTo(402.314, 141.97299);
        ((GeneralPath) shape).lineTo(402.314, 141.95699);
        ((GeneralPath) shape).lineTo(405.44, 140.92);
        ((GeneralPath) shape).moveTo(406.316, 140.662);
        ((GeneralPath) shape).lineTo(407.44302, 140.267);
        ((GeneralPath) shape).lineTo(407.44302, 139.094);
        ((GeneralPath) shape).lineTo(401.43204, 141.136);
        ((GeneralPath) shape).lineTo(401.43204, 142.842);
        ((GeneralPath) shape).lineTo(407.44302, 144.844);
        ((GeneralPath) shape).lineTo(407.44302, 143.64499);
        ((GeneralPath) shape).lineTo(406.316, 143.26799);
        ((GeneralPath) shape).lineTo(406.316, 140.662);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(403.186, 138.817);
        ((GeneralPath) shape).lineTo(407.444, 138.817);
        ((GeneralPath) shape).lineTo(407.444, 137.817);
        ((GeneralPath) shape).lineTo(404.845, 137.817);
        ((GeneralPath) shape).curveTo(404.299, 137.817, 403.955, 137.631,
                403.955, 136.995);
        ((GeneralPath) shape).curveTo(403.955, 136.485, 404.20297, 136.442,
                404.628, 136.442);
        ((GeneralPath) shape).lineTo(404.845, 136.442);
        ((GeneralPath) shape).lineTo(404.845, 135.442);
        ((GeneralPath) shape).lineTo(404.509, 135.442);
        ((GeneralPath) shape).curveTo(403.714, 135.442, 403.186, 135.672,
                403.186, 136.616);
        ((GeneralPath) shape).curveTo(403.186, 137.13899, 403.319, 137.60199,
                403.775, 137.817);
        ((GeneralPath) shape).lineTo(403.186, 137.817);
        ((GeneralPath) shape).lineTo(403.186, 138.817);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(403.186, 134.945);
        ((GeneralPath) shape).lineTo(407.444, 134.945);
        ((GeneralPath) shape).lineTo(407.444, 133.945);
        ((GeneralPath) shape).lineTo(405.071, 133.945);
        ((GeneralPath) shape).curveTo(404.325, 133.945, 403.95602, 133.77501,
                403.95602, 132.914);
        ((GeneralPath) shape).curveTo(403.95602, 132.33, 404.16504, 132.195,
                404.69302, 132.195);
        ((GeneralPath) shape).lineTo(407.44403, 132.195);
        ((GeneralPath) shape).lineTo(407.44403, 131.195);
        ((GeneralPath) shape).lineTo(405.07104, 131.195);
        ((GeneralPath) shape).curveTo(404.32504, 131.195, 403.95605, 131.039,
                403.95605, 130.23701);
        ((GeneralPath) shape).curveTo(403.95605, 129.695, 404.16507, 129.57,
                404.69305, 129.57);
        ((GeneralPath) shape).lineTo(407.44406, 129.57);
        ((GeneralPath) shape).lineTo(407.44406, 128.57);
        ((GeneralPath) shape).lineTo(404.59708, 128.57);
        ((GeneralPath) shape).curveTo(403.5641, 128.57, 403.18607, 128.92001,
                403.18607, 129.97801);
        ((GeneralPath) shape).curveTo(403.18607, 130.529, 403.34708, 131.09302,
                403.93005, 131.283);
        ((GeneralPath) shape).lineTo(403.93005, 131.31401);
        ((GeneralPath) shape).curveTo(403.32104, 131.425, 403.18607, 132.02701,
                403.18607, 132.55);
        ((GeneralPath) shape).curveTo(403.18607, 133.10901, 403.30505,
                133.70801, 403.83505, 133.945);
        ((GeneralPath) shape).lineTo(403.18607, 133.945);
        ((GeneralPath) shape).lineTo(403.18607, 134.945);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(403.955, 125.761);
        ((GeneralPath) shape).curveTo(403.955, 124.894005, 404.20898, 124.823,
                405.336, 124.823);
        ((GeneralPath) shape).curveTo(406.445, 124.823, 406.692, 124.893,
                406.692, 125.761);
        ((GeneralPath) shape).curveTo(406.692, 126.629005, 406.44498,
                126.699005, 405.336, 126.699005);
        ((GeneralPath) shape).curveTo(404.209, 126.699, 403.955, 126.628,
                403.955, 125.761);
        ((GeneralPath) shape).moveTo(403.186, 125.761);
        ((GeneralPath) shape).curveTo(403.186, 127.48, 403.70502, 127.699005,
                405.329, 127.699005);
        ((GeneralPath) shape).curveTo(406.932, 127.699005, 407.444, 127.48,
                407.444, 125.761);
        ((GeneralPath) shape).curveTo(407.444, 124.042, 406.931, 123.823,
                405.329, 123.823);
        ((GeneralPath) shape).curveTo(403.704, 123.824, 403.186, 124.042,
                403.186, 125.761);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(403.186, 123.063);
        ((GeneralPath) shape).lineTo(407.444, 123.063);
        ((GeneralPath) shape).lineTo(407.444, 122.063);
        ((GeneralPath) shape).lineTo(404.845, 122.063);
        ((GeneralPath) shape).curveTo(404.299, 122.063, 403.955, 121.87701,
                403.955, 121.241005);
        ((GeneralPath) shape).curveTo(403.955, 120.731, 404.20297, 120.688,
                404.628, 120.688);
        ((GeneralPath) shape).lineTo(404.845, 120.688);
        ((GeneralPath) shape).lineTo(404.845, 119.688);
        ((GeneralPath) shape).lineTo(404.509, 119.688);
        ((GeneralPath) shape).curveTo(403.714, 119.688, 403.186, 119.91801,
                403.186, 120.86201);
        ((GeneralPath) shape).curveTo(403.186, 121.38501, 403.319, 121.84801,
                403.775, 122.063);
        ((GeneralPath) shape).lineTo(403.186, 122.063);
        ((GeneralPath) shape).lineTo(403.186, 123.063);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(526.443, 45.698);
        ((GeneralPath) shape).lineTo(446.217, 45.698);
        ((GeneralPath) shape).lineTo(440.978, 37.841);
        ((GeneralPath) shape).lineTo(446.203, 29.983);
        ((GeneralPath) shape).lineTo(526.443, 29.983);
        ((GeneralPath) shape).lineTo(531.958, 37.841);
        ((GeneralPath) shape).lineTo(526.443, 45.698);
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
        paint = new Color(255, 255, 255, 255);
        stroke = new BasicStroke(0.973f, 0, 1, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(526.443, 45.698);
        ((GeneralPath) shape).lineTo(446.217, 45.698);
        ((GeneralPath) shape).lineTo(440.978, 37.841);
        ((GeneralPath) shape).lineTo(446.203, 29.983);
        ((GeneralPath) shape).lineTo(526.443, 29.983);
        ((GeneralPath) shape).lineTo(531.958, 37.841);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(450.937, 39.412);
        ((GeneralPath) shape).lineTo(449.81802, 36.036);
        ((GeneralPath) shape).lineTo(448.71802, 39.412);
        ((GeneralPath) shape).lineTo(450.937, 39.412);
        ((GeneralPath) shape).moveTo(451.221, 40.319);
        ((GeneralPath) shape).lineTo(448.42902, 40.319);
        ((GeneralPath) shape).lineTo(448.027, 41.564);
        ((GeneralPath) shape).lineTo(446.728, 41.564);
        ((GeneralPath) shape).lineTo(448.879, 35.081);
        ((GeneralPath) shape).lineTo(450.723, 35.081);
        ((GeneralPath) shape).lineTo(452.909, 41.564003);
        ((GeneralPath) shape).lineTo(451.634, 41.564003);
        ((GeneralPath) shape).lineTo(451.221, 40.319);
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(454.707, 38.343);
        ((GeneralPath) shape).lineTo(456.47, 38.343);
        ((GeneralPath) shape).curveTo(456.89, 38.343, 457.169, 38.268997,
                457.307, 38.116997);
        ((GeneralPath) shape).curveTo(457.444, 37.968, 457.512, 37.665997,
                457.512, 37.212997);
        ((GeneralPath) shape).curveTo(457.512, 36.752, 457.453, 36.453,
                457.335, 36.317997);
        ((GeneralPath) shape).curveTo(457.216, 36.184998, 456.95798, 36.115997,
                456.555, 36.115997);
        ((GeneralPath) shape).lineTo(454.707, 36.115997);
        ((GeneralPath) shape).lineTo(454.707, 38.343);
        ((GeneralPath) shape).moveTo(453.479, 41.563);
        ((GeneralPath) shape).lineTo(453.479, 35.08);
        ((GeneralPath) shape).lineTo(456.668, 35.08);
        ((GeneralPath) shape).curveTo(457.45898, 35.08, 458.007, 35.218002,
                458.308, 35.494003);
        ((GeneralPath) shape).curveTo(458.60703, 35.769005, 458.75803, 36.268,
                458.75803, 36.99);
        ((GeneralPath) shape).curveTo(458.75803, 37.646, 458.68503, 38.092003,
                458.532, 38.334003);
        ((GeneralPath) shape).curveTo(458.384, 38.574005, 458.075, 38.74,
                457.61102, 38.833004);
        ((GeneralPath) shape).lineTo(457.61102, 38.876003);
        ((GeneralPath) shape).curveTo(458.32703, 38.918003, 458.68604,
                39.339005, 458.68604, 40.134003);
        ((GeneralPath) shape).lineTo(458.68604, 41.564003);
        ((GeneralPath) shape).lineTo(457.45804, 41.564003);
        ((GeneralPath) shape).lineTo(457.45804, 40.381004);
        ((GeneralPath) shape).curveTo(457.45804, 39.714005, 457.13205,
                39.379005, 456.47305, 39.379005);
        ((GeneralPath) shape).lineTo(454.70505, 39.379005);
        ((GeneralPath) shape).lineTo(454.70505, 41.564007);
        ((GeneralPath) shape).lineTo(453.479, 41.564007);
        g.setPaint(paint);
        g.fill(shape);
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(467.304, 35.081);
        ((GeneralPath) shape).lineTo(467.304, 41.564003);
        ((GeneralPath) shape).lineTo(466.076, 41.564003);
        ((GeneralPath) shape).lineTo(466.076, 38.03);
        ((GeneralPath) shape).curveTo(466.076, 37.749, 466.08298, 37.428997,
                466.09998, 37.071);
        ((GeneralPath) shape).lineTo(466.123, 36.586998);
        ((GeneralPath) shape).lineTo(466.146, 36.107998);
        ((GeneralPath) shape).lineTo(466.108, 36.107998);
        ((GeneralPath) shape).lineTo(465.962, 36.559);
        ((GeneralPath) shape).lineTo(465.819, 37.01);
        ((GeneralPath) shape).curveTo(465.686, 37.413998, 465.585, 37.712997,
                465.511, 37.907997);
        ((GeneralPath) shape).lineTo(464.089, 41.565);
        ((GeneralPath) shape).lineTo(462.97, 41.565);
        ((GeneralPath) shape).lineTo(461.534, 37.937);
        ((GeneralPath) shape).curveTo(461.456, 37.737, 461.35098, 37.438,
                461.221, 37.04);
        ((GeneralPath) shape).lineTo(461.074, 36.589);
        ((GeneralPath) shape).lineTo(460.927, 36.142002);
        ((GeneralPath) shape).lineTo(460.89, 36.142002);
        ((GeneralPath) shape).lineTo(460.91302, 36.613003);
        ((GeneralPath) shape).lineTo(460.93604, 37.088);
        ((GeneralPath) shape).curveTo(460.95505, 37.454002, 460.96503, 37.77,
                460.96503, 38.033);
        ((GeneralPath) shape).lineTo(460.96503, 41.566);
        ((GeneralPath) shape).lineTo(459.73602, 41.566);
        ((GeneralPath) shape).lineTo(459.73602, 35.083);
        ((GeneralPath) shape).lineTo(461.73602, 35.083);
        ((GeneralPath) shape).lineTo(462.89304, 38.085);
        ((GeneralPath) shape).curveTo(462.97104, 38.293, 463.07504, 38.593,
                463.20602, 38.982);
        ((GeneralPath) shape).lineTo(463.34903, 39.433);
        ((GeneralPath) shape).lineTo(463.49503, 39.878998);
        ((GeneralPath) shape).lineTo(463.53802, 39.878998);
        ((GeneralPath) shape).lineTo(463.67502, 39.433);
        ((GeneralPath) shape).lineTo(463.81802, 38.986);
        ((GeneralPath) shape).curveTo(463.93402, 38.611, 464.03702, 38.312,
                464.122, 38.093);
        ((GeneralPath) shape).lineTo(465.259, 35.081997);
        ((GeneralPath) shape).lineTo(467.304, 35.081997);
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(471.039, 36.078);
        ((GeneralPath) shape).curveTo(470.215, 36.078, 469.733, 36.182,
                469.594, 36.396);
        ((GeneralPath) shape).curveTo(469.456, 36.607, 469.38498, 37.341,
                469.38498, 38.6);
        ((GeneralPath) shape).curveTo(469.38498, 39.531, 469.46597, 40.091,
                469.62897, 40.281);
        ((GeneralPath) shape).curveTo(469.78998, 40.470997, 470.27197,
                40.565998, 471.06696, 40.565998);
        ((GeneralPath) shape).curveTo(471.82794, 40.565998, 472.28595, 40.459,
                472.43695, 40.245);
        ((GeneralPath) shape).curveTo(472.58795, 40.031998, 472.66495, 39.384,
                472.66495, 38.301);
        ((GeneralPath) shape).curveTo(472.66495, 37.216, 472.59393, 36.574997,
                472.44894, 36.375);
        ((GeneralPath) shape).curveTo(472.307, 36.178, 471.836, 36.078,
                471.039, 36.078);
        ((GeneralPath) shape).moveTo(471.134, 35.024);
        ((GeneralPath) shape).curveTo(472.31, 35.024, 473.066, 35.218998,
                473.405, 35.609997);
        ((GeneralPath) shape).curveTo(473.742, 35.998997, 473.912, 36.875996,
                473.912, 38.233997);
        ((GeneralPath) shape).curveTo(473.912, 39.718, 473.744, 40.652996,
                473.40298, 41.040997);
        ((GeneralPath) shape).curveTo(473.064, 41.425995, 472.241, 41.619995,
                470.935, 41.619995);
        ((GeneralPath) shape).curveTo(469.76, 41.619995, 469.001, 41.429996,
                468.656, 41.047997);
        ((GeneralPath) shape).curveTo(468.312, 40.667995, 468.139, 39.827995,
                468.139, 38.527996);
        ((GeneralPath) shape).curveTo(468.139, 36.984997, 468.307, 36.013996,
                468.646, 35.616997);
        ((GeneralPath) shape).curveTo(468.982, 35.223, 469.812, 35.024,
                471.134, 35.024);
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(475.974, 38.343);
        ((GeneralPath) shape).lineTo(477.737, 38.343);
        ((GeneralPath) shape).curveTo(478.157, 38.343, 478.436, 38.268997,
                478.574, 38.116997);
        ((GeneralPath) shape).curveTo(478.712, 37.968, 478.78, 37.665997,
                478.78, 37.212997);
        ((GeneralPath) shape).curveTo(478.78, 36.752, 478.721, 36.453, 478.602,
                36.317997);
        ((GeneralPath) shape).curveTo(478.48398, 36.184998, 478.22498,
                36.115997, 477.823, 36.115997);
        ((GeneralPath) shape).lineTo(475.974, 36.115997);
        ((GeneralPath) shape).lineTo(475.974, 38.343);
        ((GeneralPath) shape).moveTo(474.746, 41.563);
        ((GeneralPath) shape).lineTo(474.746, 35.08);
        ((GeneralPath) shape).lineTo(477.935, 35.08);
        ((GeneralPath) shape).curveTo(478.727, 35.08, 479.275, 35.218002,
                479.575, 35.494003);
        ((GeneralPath) shape).curveTo(479.87402, 35.769005, 480.026, 36.268,
                480.026, 36.99);
        ((GeneralPath) shape).curveTo(480.026, 37.646, 479.952, 38.092003,
                479.8, 38.334003);
        ((GeneralPath) shape).curveTo(479.65198, 38.574005, 479.343, 38.74,
                478.878, 38.833004);
        ((GeneralPath) shape).lineTo(478.878, 38.876003);
        ((GeneralPath) shape).curveTo(479.594, 38.918003, 479.95398, 39.339005,
                479.95398, 40.134003);
        ((GeneralPath) shape).lineTo(479.95398, 41.564003);
        ((GeneralPath) shape).lineTo(478.72598, 41.564003);
        ((GeneralPath) shape).lineTo(478.72598, 40.381004);
        ((GeneralPath) shape).curveTo(478.72598, 39.714005, 478.399, 39.379005,
                477.74, 39.379005);
        ((GeneralPath) shape).lineTo(475.972, 39.379005);
        ((GeneralPath) shape).lineTo(475.972, 41.564007);
        ((GeneralPath) shape).lineTo(474.746, 41.564007);
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(484.671, 40.528);
        ((GeneralPath) shape).lineTo(486.41998, 40.528);
        ((GeneralPath) shape).curveTo(487.008, 40.528, 487.387, 40.393, 487.56,
                40.122);
        ((GeneralPath) shape).curveTo(487.731, 39.851, 487.818, 39.255,
                487.818, 38.329002);
        ((GeneralPath) shape).curveTo(487.818, 37.374, 487.74298, 36.766003,
                487.587, 36.505);
        ((GeneralPath) shape).curveTo(487.433, 36.246002, 487.07, 36.116,
                486.497, 36.116);
        ((GeneralPath) shape).lineTo(484.672, 36.116);
        ((GeneralPath) shape).lineTo(484.672, 40.528);
        ((GeneralPath) shape).moveTo(483.443, 41.563);
        ((GeneralPath) shape).lineTo(483.443, 35.08);
        ((GeneralPath) shape).lineTo(486.623, 35.08);
        ((GeneralPath) shape).curveTo(487.526, 35.08, 488.159, 35.277, 488.521,
                35.674004);
        ((GeneralPath) shape).curveTo(488.88098, 36.069004, 489.064, 36.762005,
                489.064, 37.754005);
        ((GeneralPath) shape).curveTo(489.064, 39.371006, 488.919, 40.411007,
                488.628, 40.872005);
        ((GeneralPath) shape).curveTo(488.33798, 41.333004, 487.68198,
                41.563004, 486.66098, 41.563004);
        ((GeneralPath) shape).lineTo(483.443, 41.563004);
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
        paint = new Color(255, 255, 255, 255);
        shape = new Rectangle2D.Double(489.96600341796875, 35.08100128173828,
                1.2280000448226929, 6.482999801635742);
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(495.984, 39.412);
        ((GeneralPath) shape).lineTo(494.866, 36.036);
        ((GeneralPath) shape).lineTo(493.766, 39.412);
        ((GeneralPath) shape).lineTo(495.984, 39.412);
        ((GeneralPath) shape).moveTo(496.269, 40.319);
        ((GeneralPath) shape).lineTo(493.47702, 40.319);
        ((GeneralPath) shape).lineTo(493.075, 41.564);
        ((GeneralPath) shape).lineTo(491.776, 41.564);
        ((GeneralPath) shape).lineTo(493.928, 35.081);
        ((GeneralPath) shape).lineTo(495.772, 35.081);
        ((GeneralPath) shape).lineTo(497.957, 41.564003);
        ((GeneralPath) shape).lineTo(496.683, 41.564003);
        ((GeneralPath) shape).lineTo(496.269, 40.319);
        g.setPaint(paint);
        g.fill(shape);
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(501.072, 38.115);
        ((GeneralPath) shape).lineTo(503.921, 38.115);
        ((GeneralPath) shape).lineTo(503.94, 39.402);
        ((GeneralPath) shape).curveTo(503.94, 40.338, 503.765, 40.944, 503.412,
                41.214);
        ((GeneralPath) shape).curveTo(503.06097, 41.485, 502.274, 41.62,
                501.055, 41.62);
        ((GeneralPath) shape).curveTo(499.936, 41.62, 499.197, 41.44, 498.832,
                41.079);
        ((GeneralPath) shape).curveTo(498.469, 40.718, 498.286, 39.981,
                498.286, 38.871);
        ((GeneralPath) shape).curveTo(498.286, 37.454, 498.35703, 36.558,
                498.505, 36.183);
        ((GeneralPath) shape).curveTo(498.685, 35.726997, 498.957, 35.420998,
                499.324, 35.261997);
        ((GeneralPath) shape).curveTo(499.689, 35.104996, 500.312, 35.024,
                501.19202, 35.024);
        ((GeneralPath) shape).curveTo(502.34003, 35.024, 503.083, 35.144997,
                503.42, 35.392);
        ((GeneralPath) shape).curveTo(503.75403, 35.636997, 503.92203, 36.181,
                503.92203, 37.024);
        ((GeneralPath) shape).lineTo(502.68103, 37.024);
        ((GeneralPath) shape).curveTo(502.66003, 36.600998, 502.56702,
                36.336998, 502.40402, 36.232998);
        ((GeneralPath) shape).curveTo(502.243, 36.130997, 501.833, 36.079,
                501.178, 36.079);
        ((GeneralPath) shape).curveTo(500.467, 36.079, 500.017, 36.167,
                499.825, 36.344997);
        ((GeneralPath) shape).curveTo(499.635, 36.520996, 499.53802, 36.937996,
                499.53802, 37.593998);
        ((GeneralPath) shape).lineTo(499.53302, 38.244);
        ((GeneralPath) shape).lineTo(499.54303, 39.075);
        ((GeneralPath) shape).curveTo(499.54303, 39.717, 499.63803, 40.127,
                499.82703, 40.303);
        ((GeneralPath) shape).curveTo(500.01602, 40.479, 500.45502, 40.567,
                501.144, 40.567);
        ((GeneralPath) shape).curveTo(501.81302, 40.567, 502.239, 40.493,
                502.42102, 40.344);
        ((GeneralPath) shape).curveTo(502.60202, 40.197002, 502.69302, 39.845,
                502.69302, 39.289);
        ((GeneralPath) shape).lineTo(502.69803, 39.024002);
        ((GeneralPath) shape).lineTo(501.07202, 39.024002);
        ((GeneralPath) shape).lineTo(501.07202, 38.115);
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(506.035, 38.343);
        ((GeneralPath) shape).lineTo(507.798, 38.343);
        ((GeneralPath) shape).curveTo(508.21802, 38.343, 508.497, 38.268997,
                508.635, 38.116997);
        ((GeneralPath) shape).curveTo(508.773, 37.968, 508.841, 37.665997,
                508.841, 37.212997);
        ((GeneralPath) shape).curveTo(508.841, 36.752, 508.781, 36.453,
                508.663, 36.317997);
        ((GeneralPath) shape).curveTo(508.54498, 36.184998, 508.28598,
                36.115997, 507.884, 36.115997);
        ((GeneralPath) shape).lineTo(506.035, 36.115997);
        ((GeneralPath) shape).lineTo(506.035, 38.343);
        ((GeneralPath) shape).moveTo(504.808, 41.563);
        ((GeneralPath) shape).lineTo(504.808, 35.08);
        ((GeneralPath) shape).lineTo(507.997, 35.08);
        ((GeneralPath) shape).curveTo(508.789, 35.08, 509.336, 35.218002,
                509.63702, 35.494003);
        ((GeneralPath) shape).curveTo(509.93604, 35.769005, 510.088, 36.268,
                510.088, 36.99);
        ((GeneralPath) shape).curveTo(510.088, 37.646, 510.014, 38.092003,
                509.862, 38.334003);
        ((GeneralPath) shape).curveTo(509.713, 38.574005, 509.405, 38.74,
                508.94, 38.833004);
        ((GeneralPath) shape).lineTo(508.94, 38.876003);
        ((GeneralPath) shape).curveTo(509.656, 38.918003, 510.016, 39.339005,
                510.016, 40.134003);
        ((GeneralPath) shape).lineTo(510.016, 41.564003);
        ((GeneralPath) shape).lineTo(508.788, 41.564003);
        ((GeneralPath) shape).lineTo(508.788, 40.381004);
        ((GeneralPath) shape).curveTo(508.788, 39.714005, 508.461, 39.379005,
                507.802, 39.379005);
        ((GeneralPath) shape).lineTo(506.034, 39.379005);
        ((GeneralPath) shape).lineTo(506.034, 41.564007);
        ((GeneralPath) shape).lineTo(504.808, 41.564007);
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(514.709, 39.412);
        ((GeneralPath) shape).lineTo(513.58997, 36.036);
        ((GeneralPath) shape).lineTo(512.49, 39.412);
        ((GeneralPath) shape).lineTo(514.709, 39.412);
        ((GeneralPath) shape).moveTo(514.993, 40.319);
        ((GeneralPath) shape).lineTo(512.201, 40.319);
        ((GeneralPath) shape).lineTo(511.79898, 41.564);
        ((GeneralPath) shape).lineTo(510.5, 41.564);
        ((GeneralPath) shape).lineTo(512.651, 35.081);
        ((GeneralPath) shape).lineTo(514.496, 35.081);
        ((GeneralPath) shape).lineTo(516.68097, 41.564003);
        ((GeneralPath) shape).lineTo(515.40594, 41.564003);
        ((GeneralPath) shape).lineTo(514.993, 40.319);
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(524.816, 35.081);
        ((GeneralPath) shape).lineTo(524.816, 41.564003);
        ((GeneralPath) shape).lineTo(523.58795, 41.564003);
        ((GeneralPath) shape).lineTo(523.58795, 38.03);
        ((GeneralPath) shape).curveTo(523.58795, 37.749, 523.595, 37.428997,
                523.61096, 37.071);
        ((GeneralPath) shape).lineTo(523.634, 36.586998);
        ((GeneralPath) shape).lineTo(523.657, 36.107998);
        ((GeneralPath) shape).lineTo(523.61896, 36.107998);
        ((GeneralPath) shape).lineTo(523.47296, 36.559);
        ((GeneralPath) shape).lineTo(523.33093, 37.01);
        ((GeneralPath) shape).curveTo(523.19794, 37.413998, 523.09595,
                37.712997, 523.0219, 37.907997);
        ((GeneralPath) shape).lineTo(521.5999, 41.565);
        ((GeneralPath) shape).lineTo(520.48193, 41.565);
        ((GeneralPath) shape).lineTo(519.0449, 37.937);
        ((GeneralPath) shape).curveTo(518.9669, 37.737, 518.86194, 37.438,
                518.73193, 37.04);
        ((GeneralPath) shape).lineTo(518.58496, 36.589);
        ((GeneralPath) shape).lineTo(518.43896, 36.142002);
        ((GeneralPath) shape).lineTo(518.40094, 36.142002);
        ((GeneralPath) shape).lineTo(518.42395, 36.613003);
        ((GeneralPath) shape).lineTo(518.44794, 37.088);
        ((GeneralPath) shape).curveTo(518.4669, 37.454002, 518.47595, 37.77,
                518.47595, 38.033);
        ((GeneralPath) shape).lineTo(518.47595, 41.566);
        ((GeneralPath) shape).lineTo(517.2479, 41.566);
        ((GeneralPath) shape).lineTo(517.2479, 35.083);
        ((GeneralPath) shape).lineTo(519.2479, 35.083);
        ((GeneralPath) shape).lineTo(520.40393, 38.085);
        ((GeneralPath) shape).curveTo(520.48193, 38.293, 520.5869, 38.593,
                520.7169, 38.982);
        ((GeneralPath) shape).lineTo(520.8599, 39.433);
        ((GeneralPath) shape).lineTo(521.0059, 39.878998);
        ((GeneralPath) shape).lineTo(521.04895, 39.878998);
        ((GeneralPath) shape).lineTo(521.18695, 39.433);
        ((GeneralPath) shape).lineTo(521.329, 38.986);
        ((GeneralPath) shape).curveTo(521.445, 38.611, 521.548, 38.312,
                521.633, 38.093);
        ((GeneralPath) shape).lineTo(522.771, 35.081997);
        ((GeneralPath) shape).lineTo(524.816, 35.081997);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(463.994, 57.353);
        ((GeneralPath) shape).lineTo(466.733, 57.353);
        ((GeneralPath) shape).lineTo(466.733, 56.394);
        ((GeneralPath) shape).lineTo(462.9, 56.394);
        ((GeneralPath) shape).lineTo(462.9, 62.24);
        ((GeneralPath) shape).lineTo(463.994, 62.24);
        ((GeneralPath) shape).lineTo(463.994, 59.804);
        ((GeneralPath) shape).lineTo(466.593, 59.804);
        ((GeneralPath) shape).lineTo(466.593, 58.83);
        ((GeneralPath) shape).lineTo(463.994, 58.83);
        ((GeneralPath) shape).lineTo(463.994, 57.353);
        g.setPaint(paint);
        g.fill(shape);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(467.307, 58.099);
        ((GeneralPath) shape).lineTo(467.307, 62.240997);
        ((GeneralPath) shape).lineTo(468.28, 62.240997);
        ((GeneralPath) shape).lineTo(468.28, 59.713997);
        ((GeneralPath) shape).curveTo(468.28, 59.183, 468.461, 58.847996,
                469.08, 58.847996);
        ((GeneralPath) shape).curveTo(469.576, 58.847996, 469.61798, 59.089996,
                469.61798, 59.502995);
        ((GeneralPath) shape).lineTo(469.61798, 59.713993);
        ((GeneralPath) shape).lineTo(470.59097, 59.713993);
        ((GeneralPath) shape).lineTo(470.59097, 59.385994);
        ((GeneralPath) shape).curveTo(470.59097, 58.613995, 470.36597,
                58.098995, 469.44897, 58.098995);
        ((GeneralPath) shape).curveTo(468.93997, 58.098995, 468.48898,
                58.227997, 468.27997, 58.671997);
        ((GeneralPath) shape).lineTo(468.27997, 58.098995);
        ((GeneralPath) shape).lineTo(467.307, 58.098995);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(472.919, 58.847);
        ((GeneralPath) shape).curveTo(473.762, 58.847, 473.83002, 59.095,
                473.83002, 60.191);
        ((GeneralPath) shape).curveTo(473.83002, 61.27, 473.76202, 61.510002,
                472.919, 61.510002);
        ((GeneralPath) shape).curveTo(472.075, 61.510002, 472.00702, 61.27,
                472.00702, 60.191);
        ((GeneralPath) shape).curveTo(472.007, 59.094, 472.075, 58.847,
                472.919, 58.847);
        ((GeneralPath) shape).moveTo(472.919, 58.099);
        ((GeneralPath) shape).curveTo(471.247, 58.099, 471.034, 58.603,
                471.034, 60.183);
        ((GeneralPath) shape).curveTo(471.034, 61.741997, 471.247, 62.239998,
                472.919, 62.239998);
        ((GeneralPath) shape).curveTo(474.59, 62.239998, 474.803, 61.740997,
                474.803, 60.183);
        ((GeneralPath) shape).curveTo(474.803, 58.603, 474.59, 58.099, 472.919,
                58.099);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(475.684, 58.099);
        ((GeneralPath) shape).lineTo(475.684, 62.240997);
        ((GeneralPath) shape).lineTo(476.65698, 62.240997);
        ((GeneralPath) shape).lineTo(476.65698, 59.985996);
        ((GeneralPath) shape).curveTo(476.65698, 59.252995, 476.771, 58.847996,
                477.611, 58.847996);
        ((GeneralPath) shape).curveTo(478.22998, 58.847996, 478.35898,
                59.051994, 478.35898, 59.643997);
        ((GeneralPath) shape).lineTo(478.35898, 62.241997);
        ((GeneralPath) shape).lineTo(479.33197, 62.241997);
        ((GeneralPath) shape).lineTo(479.33197, 59.542995);
        ((GeneralPath) shape).curveTo(479.33197, 58.545994, 479.00897,
                58.099995, 477.94696, 58.099995);
        ((GeneralPath) shape).curveTo(477.38297, 58.099995, 476.90195,
                58.160995, 476.68695, 58.756996);
        ((GeneralPath) shape).lineTo(476.65695, 58.756996);
        ((GeneralPath) shape).lineTo(476.65695, 58.099995);
        ((GeneralPath) shape).lineTo(475.684, 58.099995);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(479.824, 58.83);
        ((GeneralPath) shape).lineTo(480.337, 58.83);
        ((GeneralPath) shape).lineTo(480.337, 60.963);
        ((GeneralPath) shape).curveTo(480.337, 61.993, 480.667, 62.24, 481.754,
                62.24);
        ((GeneralPath) shape).curveTo(482.82, 62.24, 483.133, 61.867, 483.133,
                60.632);
        ((GeneralPath) shape).lineTo(482.28198, 60.632);
        ((GeneralPath) shape).curveTo(482.28198, 61.065998, 482.343, 61.509,
                481.75397, 61.509);
        ((GeneralPath) shape).curveTo(481.30997, 61.509, 481.30997, 61.336,
                481.30997, 60.954998);
        ((GeneralPath) shape).lineTo(481.30997, 58.83);
        ((GeneralPath) shape).lineTo(482.92697, 58.83);
        ((GeneralPath) shape).lineTo(482.92697, 58.099003);
        ((GeneralPath) shape).lineTo(481.30997, 58.099003);
        ((GeneralPath) shape).lineTo(481.30997, 57.153004);
        ((GeneralPath) shape).lineTo(480.33698, 57.153004);
        ((GeneralPath) shape).lineTo(480.33698, 58.099003);
        ((GeneralPath) shape).lineTo(479.82397, 58.099003);
        ((GeneralPath) shape).lineTo(479.82397, 58.83);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(489.713, 60.292);
        ((GeneralPath) shape).lineTo(487.68802, 60.292);
        ((GeneralPath) shape).lineTo(488.68903, 57.252);
        ((GeneralPath) shape).lineTo(488.70502, 57.252);
        ((GeneralPath) shape).lineTo(489.713, 60.292);
        ((GeneralPath) shape).moveTo(489.963, 61.144);
        ((GeneralPath) shape).lineTo(490.34702, 62.24);
        ((GeneralPath) shape).lineTo(491.489, 62.24);
        ((GeneralPath) shape).lineTo(489.50302, 56.393);
        ((GeneralPath) shape).lineTo(487.84402, 56.393);
        ((GeneralPath) shape).lineTo(485.898, 62.24);
        ((GeneralPath) shape).lineTo(487.064, 62.24);
        ((GeneralPath) shape).lineTo(487.431, 61.144);
        ((GeneralPath) shape).lineTo(489.963, 61.144);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(491.777, 58.099);
        ((GeneralPath) shape).lineTo(491.777, 62.240997);
        ((GeneralPath) shape).lineTo(492.75, 62.240997);
        ((GeneralPath) shape).lineTo(492.75, 59.713997);
        ((GeneralPath) shape).curveTo(492.75, 59.183, 492.931, 58.847996,
                493.55, 58.847996);
        ((GeneralPath) shape).curveTo(494.046, 58.847996, 494.08798, 59.089996,
                494.08798, 59.502995);
        ((GeneralPath) shape).lineTo(494.08798, 59.713993);
        ((GeneralPath) shape).lineTo(495.06097, 59.713993);
        ((GeneralPath) shape).lineTo(495.06097, 59.385994);
        ((GeneralPath) shape).curveTo(495.06097, 58.613995, 494.83597,
                58.098995, 493.91898, 58.098995);
        ((GeneralPath) shape).curveTo(493.40897, 58.098995, 492.95898,
                58.227997, 492.74997, 58.671997);
        ((GeneralPath) shape).lineTo(492.74997, 58.098995);
        ((GeneralPath) shape).lineTo(491.777, 58.098995);
        g.setPaint(paint);
        g.fill(shape);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(495.626, 58.099);
        ((GeneralPath) shape).lineTo(495.626, 62.240997);
        ((GeneralPath) shape).lineTo(496.599, 62.240997);
        ((GeneralPath) shape).lineTo(496.599, 59.932);
        ((GeneralPath) shape).curveTo(496.599, 59.207, 496.76498, 58.847,
                497.602, 58.847);
        ((GeneralPath) shape).curveTo(498.16998, 58.847, 498.301, 59.051,
                498.301, 59.564);
        ((GeneralPath) shape).lineTo(498.301, 62.239998);
        ((GeneralPath) shape).lineTo(499.274, 62.239998);
        ((GeneralPath) shape).lineTo(499.274, 59.931);
        ((GeneralPath) shape).curveTo(499.274, 59.206, 499.426, 58.846,
                500.205, 58.846);
        ((GeneralPath) shape).curveTo(500.733, 58.846, 500.85498, 59.05,
                500.85498, 59.563);
        ((GeneralPath) shape).lineTo(500.85498, 62.239);
        ((GeneralPath) shape).lineTo(501.82697, 62.239);
        ((GeneralPath) shape).lineTo(501.82697, 59.468998);
        ((GeneralPath) shape).curveTo(501.82697, 58.463997, 501.48697,
                58.096996, 500.45798, 58.096996);
        ((GeneralPath) shape).curveTo(499.92197, 58.096996, 499.373, 58.252995,
                499.18896, 58.820995);
        ((GeneralPath) shape).lineTo(499.15897, 58.820995);
        ((GeneralPath) shape).curveTo(499.04996, 58.228996, 498.46497,
                58.096996, 497.95596, 58.096996);
        ((GeneralPath) shape).curveTo(497.41296, 58.096996, 496.82996,
                58.212997, 496.59998, 58.728996);
        ((GeneralPath) shape).lineTo(496.59998, 58.096996);
        ((GeneralPath) shape).lineTo(495.626, 58.096996);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(504.578, 58.847);
        ((GeneralPath) shape).curveTo(505.422, 58.847, 505.49, 59.095, 505.49,
                60.191);
        ((GeneralPath) shape).curveTo(505.49, 61.27, 505.422, 61.510002,
                504.578, 61.510002);
        ((GeneralPath) shape).curveTo(503.73502, 61.510002, 503.667, 61.27,
                503.667, 60.191);
        ((GeneralPath) shape).curveTo(503.667, 59.094, 503.735, 58.847,
                504.578, 58.847);
        ((GeneralPath) shape).moveTo(504.578, 58.099);
        ((GeneralPath) shape).curveTo(502.906, 58.099, 502.694, 58.603,
                502.694, 60.183);
        ((GeneralPath) shape).curveTo(502.694, 61.741997, 502.906, 62.239998,
                504.578, 62.239998);
        ((GeneralPath) shape).curveTo(506.25, 62.239998, 506.463, 61.740997,
                506.463, 60.183);
        ((GeneralPath) shape).curveTo(506.463, 58.603, 506.25, 58.099, 504.578,
                58.099);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(507.223, 58.099);
        ((GeneralPath) shape).lineTo(507.223, 62.240997);
        ((GeneralPath) shape).lineTo(508.19598, 62.240997);
        ((GeneralPath) shape).lineTo(508.19598, 59.713997);
        ((GeneralPath) shape).curveTo(508.19598, 59.183, 508.37698, 58.847996,
                508.99597, 58.847996);
        ((GeneralPath) shape).curveTo(509.49197, 58.847996, 509.53296,
                59.089996, 509.53296, 59.502995);
        ((GeneralPath) shape).lineTo(509.53296, 59.713993);
        ((GeneralPath) shape).lineTo(510.50595, 59.713993);
        ((GeneralPath) shape).lineTo(510.50595, 59.385994);
        ((GeneralPath) shape).curveTo(510.50595, 58.613995, 510.28195,
                58.098995, 509.36395, 58.098995);
        ((GeneralPath) shape).curveTo(508.85495, 58.098995, 508.40494,
                58.227997, 508.19595, 58.671997);
        ((GeneralPath) shape).lineTo(508.19595, 58.098995);
        ((GeneralPath) shape).lineTo(507.223, 58.098995);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_294;
        g.setTransform(defaultTransform__0_294);
        g.setClip(clip__0_294);
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
        return 251;
    }

    /**
     * Returns the Y of the bounding box of the original SVG image.
     *
     * @return The Y of the bounding box of the original SVG image.
     */
    public static int getOrigY() {
        return 30;
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
