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
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;

/**
 * This class has been automatically generated using <a
 * href="http://englishjavadrinker.blogspot.com/search/label/SVGRoundTrip"
 * >SVGRoundTrip</a>.
 */
public class TankSheetCopyrightInfo {
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(47.221, 768.367);
        ((GeneralPath) shape).curveTo(47.204002, 768.663, 46.952, 768.85803,
                46.681, 768.85803);
        ((GeneralPath) shape).curveTo(46.211, 768.85803, 45.94, 768.372, 45.94,
                767.929);
        ((GeneralPath) shape).curveTo(45.94, 767.473, 46.181, 767.033, 46.662,
                767.033);
        ((GeneralPath) shape).curveTo(46.944, 767.033, 47.174, 767.202, 47.221,
                767.48303);
        ((GeneralPath) shape).lineTo(47.721, 767.48303);
        ((GeneralPath) shape).curveTo(47.632, 766.97205, 47.209, 766.669,
                46.668, 766.669);
        ((GeneralPath) shape).curveTo(45.929, 766.669, 45.484, 767.209, 45.484,
                767.94);
        ((GeneralPath) shape).curveTo(45.484, 768.66, 45.951, 769.222, 46.681,
                769.222);
        ((GeneralPath) shape).curveTo(47.204, 769.222, 47.663, 768.90497,
                47.722, 768.367);
        ((GeneralPath) shape).lineTo(47.221, 768.367);
        ((GeneralPath) shape).moveTo(46.587, 769.679);
        ((GeneralPath) shape).curveTo(45.621002, 769.679, 44.846, 768.911,
                44.846, 767.95905);
        ((GeneralPath) shape).curveTo(44.846, 766.97406, 45.621002, 766.179,
                46.587, 766.179);
        ((GeneralPath) shape).curveTo(47.58, 766.179, 48.402, 766.974, 48.402,
                767.95905);
        ((GeneralPath) shape).curveTo(48.402, 768.911, 47.581, 769.679, 46.587,
                769.679);
        ((GeneralPath) shape).moveTo(46.587, 770.135);
        ((GeneralPath) shape).curveTo(47.839, 770.135, 48.858, 769.164, 48.858,
                767.959);
        ((GeneralPath) shape).curveTo(48.858, 766.739, 47.839, 765.758, 46.587,
                765.758);
        ((GeneralPath) shape).curveTo(45.370003, 765.758, 44.390003, 766.739,
                44.390003, 767.959);
        ((GeneralPath) shape).curveTo(44.39, 769.164, 45.371, 770.135, 46.587,
                770.135);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(71.561, 766.476);
        ((GeneralPath) shape).lineTo(72.842, 766.476);
        ((GeneralPath) shape).lineTo(72.842, 765.758);
        ((GeneralPath) shape).lineTo(69.461, 765.758);
        ((GeneralPath) shape).lineTo(69.461, 766.476);
        ((GeneralPath) shape).lineTo(70.742, 766.476);
        ((GeneralPath) shape).lineTo(70.742, 770.135);
        ((GeneralPath) shape).lineTo(71.561, 770.135);
        ((GeneralPath) shape).lineTo(71.561, 766.476);
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
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(73.984, 765.758);
        ((GeneralPath) shape).lineTo(73.256004, 765.758);
        ((GeneralPath) shape).lineTo(73.256004, 770.135);
        ((GeneralPath) shape).lineTo(73.984, 770.135);
        ((GeneralPath) shape).lineTo(73.984, 768.447);
        ((GeneralPath) shape).curveTo(73.984, 767.898, 74.069, 767.59503,
                74.698, 767.59503);
        ((GeneralPath) shape).curveTo(75.160995, 767.59503, 75.257996, 767.747,
                75.257996, 768.19104);
        ((GeneralPath) shape).lineTo(75.257996, 770.135);
        ((GeneralPath) shape).lineTo(75.98599, 770.135);
        ((GeneralPath) shape).lineTo(75.98599, 768.114);
        ((GeneralPath) shape).curveTo(75.98599, 767.36804, 75.743996, 767.034,
                74.94999, 767.034);
        ((GeneralPath) shape).curveTo(74.52799, 767.034, 74.16799, 767.081,
                74.00699, 767.52997);
        ((GeneralPath) shape).lineTo(73.983986, 767.52997);
        ((GeneralPath) shape).lineTo(73.983986, 765.758);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(77.382, 768.312);
        ((GeneralPath) shape).curveTo(77.382, 767.73804, 77.424, 767.59503,
                78.05601, 767.59503);
        ((GeneralPath) shape).curveTo(78.65501, 767.59503, 78.74701, 767.645,
                78.74701, 768.312);
        ((GeneralPath) shape).lineTo(77.382, 768.312);
        ((GeneralPath) shape).moveTo(78.747, 769.163);
        ((GeneralPath) shape).curveTo(78.747, 769.588, 78.462, 769.588, 78.056,
                769.588);
        ((GeneralPath) shape).curveTo(77.399, 769.588, 77.381996, 769.39,
                77.381996, 768.767);
        ((GeneralPath) shape).lineTo(79.475, 768.767);
        ((GeneralPath) shape).curveTo(79.475, 767.40906, 79.307, 767.03503,
                78.056, 767.03503);
        ((GeneralPath) shape).curveTo(76.827, 767.03503, 76.654, 767.52405,
                76.654, 768.642);
        ((GeneralPath) shape).curveTo(76.654, 769.778, 76.892, 770.135, 78.056,
                770.135);
        ((GeneralPath) shape).curveTo(78.925, 770.135, 79.475, 770.089, 79.475,
                769.163);
        ((GeneralPath) shape).lineTo(78.747, 769.163);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(83.855, 766.476);
        ((GeneralPath) shape).lineTo(85.136, 766.476);
        ((GeneralPath) shape).lineTo(85.136, 765.758);
        ((GeneralPath) shape).lineTo(81.755, 765.758);
        ((GeneralPath) shape).lineTo(81.755, 766.476);
        ((GeneralPath) shape).lineTo(83.036, 766.476);
        ((GeneralPath) shape).lineTo(83.036, 770.135);
        ((GeneralPath) shape).lineTo(83.855, 770.135);
        ((GeneralPath) shape).lineTo(83.855, 766.476);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(86.375, 767.595);
        ((GeneralPath) shape).curveTo(87.006, 767.595, 87.058, 767.77997,
                87.058, 768.60095);
        ((GeneralPath) shape).curveTo(87.058, 769.40894, 87.006996, 769.58795,
                86.375, 769.58795);
        ((GeneralPath) shape).curveTo(85.743004, 769.58795, 85.692, 769.40796,
                85.692, 768.60095);
        ((GeneralPath) shape).curveTo(85.692, 767.779, 85.744, 767.595, 86.375,
                767.595);
        ((GeneralPath) shape).moveTo(86.375, 767.034);
        ((GeneralPath) shape).curveTo(85.124, 767.034, 84.964, 767.412, 84.964,
                768.595);
        ((GeneralPath) shape).curveTo(84.964, 769.76196, 85.12299, 770.13495,
                86.375, 770.13495);
        ((GeneralPath) shape).curveTo(87.626, 770.13495, 87.785, 769.76196,
                87.785, 768.595);
        ((GeneralPath) shape).curveTo(87.785, 767.412, 87.626, 767.034, 86.375,
                767.034);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(89.179, 767.034);
        ((GeneralPath) shape).lineTo(88.451004, 767.034);
        ((GeneralPath) shape).lineTo(88.451004, 771.502);
        ((GeneralPath) shape).lineTo(89.179, 771.502);
        ((GeneralPath) shape).lineTo(89.179, 769.701);
        ((GeneralPath) shape).lineTo(89.207, 769.701);
        ((GeneralPath) shape).curveTo(89.362, 770.066, 89.746, 770.135, 90.113,
                770.135);
        ((GeneralPath) shape).curveTo(91.151, 770.135, 91.271996, 769.565,
                91.271996, 768.68);
        ((GeneralPath) shape).curveTo(91.271996, 767.75, 91.271996, 767.034,
                90.113, 767.034);
        ((GeneralPath) shape).curveTo(89.692, 767.034, 89.361, 767.127, 89.179,
                767.526);
        ((GeneralPath) shape).lineTo(89.179, 767.034);
        ((GeneralPath) shape).moveTo(89.901, 769.588);
        ((GeneralPath) shape).curveTo(89.276, 769.588, 89.179, 769.328, 89.179,
                768.68);
        ((GeneralPath) shape).curveTo(89.179, 767.958, 89.179, 767.595, 89.901,
                767.595);
        ((GeneralPath) shape).curveTo(90.544, 767.595, 90.544, 767.993, 90.544,
                768.68);
        ((GeneralPath) shape).curveTo(90.544, 769.443, 90.4, 769.588, 89.901,
                769.588);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(92.656, 767.034);
        ((GeneralPath) shape).lineTo(91.928, 767.034);
        ((GeneralPath) shape).lineTo(91.928, 771.502);
        ((GeneralPath) shape).lineTo(92.656, 771.502);
        ((GeneralPath) shape).lineTo(92.656, 769.701);
        ((GeneralPath) shape).lineTo(92.684, 769.701);
        ((GeneralPath) shape).curveTo(92.839, 770.066, 93.223, 770.135, 93.59,
                770.135);
        ((GeneralPath) shape).curveTo(94.628, 770.135, 94.74899, 769.565,
                94.74899, 768.68);
        ((GeneralPath) shape).curveTo(94.74899, 767.75, 94.74899, 767.034,
                93.59, 767.034);
        ((GeneralPath) shape).curveTo(93.169, 767.034, 92.838, 767.127, 92.656,
                767.526);
        ((GeneralPath) shape).lineTo(92.656, 767.034);
        ((GeneralPath) shape).moveTo(93.378, 769.588);
        ((GeneralPath) shape).curveTo(92.754, 769.588, 92.656, 769.328, 92.656,
                768.68);
        ((GeneralPath) shape).curveTo(92.656, 767.958, 92.656, 767.595, 93.378,
                767.595);
        ((GeneralPath) shape).curveTo(94.020996, 767.595, 94.020996, 767.993,
                94.020996, 768.68);
        ((GeneralPath) shape).curveTo(94.021, 769.443, 93.877, 769.588, 93.378,
                769.588);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(97.953, 767.891);
        ((GeneralPath) shape).curveTo(97.953, 767.035, 97.239006, 767.035,
                96.665, 767.035);
        ((GeneralPath) shape).curveTo(95.915, 767.035, 95.314, 767.035, 95.314,
                767.87994);
        ((GeneralPath) shape).curveTo(95.314, 768.634, 95.512, 768.77893,
                96.602005, 768.79694);
        ((GeneralPath) shape).curveTo(97.299, 768.8079, 97.316, 768.9379,
                97.316, 769.19495);
        ((GeneralPath) shape).curveTo(97.316, 769.5889, 97.073006, 769.5889,
                96.656, 769.5889);
        ((GeneralPath) shape).curveTo(96.141, 769.5889, 96.042, 769.53894,
                96.042, 769.1109);
        ((GeneralPath) shape).lineTo(95.314, 769.1109);
        ((GeneralPath) shape).curveTo(95.314, 770.1349, 95.856, 770.1349,
                96.662, 770.1349);
        ((GeneralPath) shape).curveTo(97.412, 770.1349, 98.04401, 770.0509,
                98.04401, 769.1949);
        ((GeneralPath) shape).curveTo(98.04401, 768.2379, 97.411, 768.2909,
                96.726006, 768.2569);
        ((GeneralPath) shape).curveTo(96.119, 768.2279, 96.04201, 768.2169,
                96.04201, 767.9249);
        ((GeneralPath) shape).curveTo(96.04201, 767.5369, 96.298004, 767.5369,
                96.66901, 767.5369);
        ((GeneralPath) shape).curveTo(97.04201, 767.5369, 97.225006, 767.5369,
                97.225006, 767.8909);
        ((GeneralPath) shape).lineTo(97.953, 767.8909);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(104.168, 767.242);
        ((GeneralPath) shape).lineTo(104.168, 767.008);
        ((GeneralPath) shape).curveTo(104.108, 765.758, 103.463, 765.758,
                102.376, 765.758);
        ((GeneralPath) shape).curveTo(101.076, 765.758, 100.529, 766.071,
                100.529, 767.455);
        ((GeneralPath) shape).lineTo(100.529, 768.437);
        ((GeneralPath) shape).curveTo(100.529, 769.693, 100.808, 770.193,
                102.376, 770.13403);
        ((GeneralPath) shape).curveTo(103.458, 770.12805, 104.168, 770.13403,
                104.168, 768.83905);
        ((GeneralPath) shape).lineTo(104.168, 768.55804);
        ((GeneralPath) shape).lineTo(103.349, 768.55804);
        ((GeneralPath) shape).lineTo(103.349, 768.79205);
        ((GeneralPath) shape).curveTo(103.349, 769.40405, 103.082, 769.40405,
                102.375, 769.40405);
        ((GeneralPath) shape).curveTo(101.456, 769.40405, 101.347, 769.24207,
                101.347, 768.40204);
        ((GeneralPath) shape).lineTo(101.347, 767.45404);
        ((GeneralPath) shape).curveTo(101.347, 766.61206, 101.498, 766.47504,
                102.375, 766.47504);
        ((GeneralPath) shape).curveTo(103.141, 766.47504, 103.349, 766.51,
                103.349, 767.007);
        ((GeneralPath) shape).lineTo(103.349, 767.241);
        ((GeneralPath) shape).lineTo(104.168, 767.241);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(106.089, 767.595);
        ((GeneralPath) shape).curveTo(106.71999, 767.595, 106.770996,
                767.77997, 106.770996, 768.60095);
        ((GeneralPath) shape).curveTo(106.770996, 769.40894, 106.71999,
                769.58795, 106.089, 769.58795);
        ((GeneralPath) shape).curveTo(105.458, 769.58795, 105.406, 769.40796,
                105.406, 768.60095);
        ((GeneralPath) shape).curveTo(105.407, 767.779, 105.458, 767.595,
                106.089, 767.595);
        ((GeneralPath) shape).moveTo(106.089, 767.034);
        ((GeneralPath) shape).curveTo(104.838, 767.034, 104.67799, 767.412,
                104.67799, 768.595);
        ((GeneralPath) shape).curveTo(104.67799, 769.76196, 104.83699,
                770.13495, 106.089, 770.13495);
        ((GeneralPath) shape).curveTo(107.34, 770.13495, 107.499, 769.76196,
                107.499, 768.595);
        ((GeneralPath) shape).curveTo(107.5, 767.412, 107.34, 767.034, 106.089,
                767.034);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(108.165, 767.034);
        ((GeneralPath) shape).lineTo(108.165, 770.135);
        ((GeneralPath) shape).lineTo(108.893, 770.135);
        ((GeneralPath) shape).lineTo(108.893, 768.406);
        ((GeneralPath) shape).curveTo(108.893, 767.864, 109.017, 767.594,
                109.644, 767.594);
        ((GeneralPath) shape).curveTo(110.069, 767.594, 110.167, 767.746,
                110.167, 768.131);
        ((GeneralPath) shape).lineTo(110.167, 770.134);
        ((GeneralPath) shape).lineTo(110.895, 770.134);
        ((GeneralPath) shape).lineTo(110.895, 768.40497);
        ((GeneralPath) shape).curveTo(110.895, 767.863, 111.008995, 767.59296,
                111.591995, 767.59296);
        ((GeneralPath) shape).curveTo(111.98699, 767.59296, 112.077995,
                767.74493, 112.077995, 768.12994);
        ((GeneralPath) shape).lineTo(112.077995, 770.13293);
        ((GeneralPath) shape).lineTo(112.80599, 770.13293);
        ((GeneralPath) shape).lineTo(112.80599, 768.05994);
        ((GeneralPath) shape).curveTo(112.80599, 767.3079, 112.551994,
                767.03296, 111.78099, 767.03296);
        ((GeneralPath) shape).curveTo(111.37999, 767.03296, 110.968994,
                767.14996, 110.83099, 767.57495);
        ((GeneralPath) shape).lineTo(110.80799, 767.57495);
        ((GeneralPath) shape).curveTo(110.72699, 767.13196, 110.288994,
                767.03296, 109.90799, 767.03296);
        ((GeneralPath) shape).curveTo(109.50199, 767.03296, 109.06499,
                767.11993, 108.89299, 767.50696);
        ((GeneralPath) shape).lineTo(108.89299, 767.03296);
        ((GeneralPath) shape).lineTo(108.165, 767.03296);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(114.286, 767.034);
        ((GeneralPath) shape).lineTo(113.55801, 767.034);
        ((GeneralPath) shape).lineTo(113.55801, 771.502);
        ((GeneralPath) shape).lineTo(114.286, 771.502);
        ((GeneralPath) shape).lineTo(114.286, 769.701);
        ((GeneralPath) shape).lineTo(114.315, 769.701);
        ((GeneralPath) shape).curveTo(114.47, 770.066, 114.854004, 770.135,
                115.22, 770.135);
        ((GeneralPath) shape).curveTo(116.258, 770.135, 116.379, 769.565,
                116.379, 768.68);
        ((GeneralPath) shape).curveTo(116.379, 767.75, 116.379, 767.034,
                115.22, 767.034);
        ((GeneralPath) shape).curveTo(114.799, 767.034, 114.468, 767.127,
                114.286, 767.526);
        ((GeneralPath) shape).lineTo(114.286, 767.034);
        ((GeneralPath) shape).moveTo(115.008, 769.588);
        ((GeneralPath) shape).curveTo(114.383, 769.588, 114.285, 769.328,
                114.285, 768.68);
        ((GeneralPath) shape).curveTo(114.285, 767.958, 114.285, 767.595,
                115.008, 767.595);
        ((GeneralPath) shape).curveTo(115.651, 767.595, 115.651, 767.993,
                115.651, 768.68);
        ((GeneralPath) shape).curveTo(115.651, 769.443, 115.507, 769.588,
                115.008, 769.588);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(119.037, 770.135);
        ((GeneralPath) shape).lineTo(119.765, 770.135);
        ((GeneralPath) shape).lineTo(119.765, 768.214);
        ((GeneralPath) shape).curveTo(119.765, 767.188, 119.351, 767.034,
                118.36, 767.034);
        ((GeneralPath) shape).curveTo(117.666, 767.034, 117.035, 767.034,
                117.035, 767.942);
        ((GeneralPath) shape).lineTo(117.763, 767.942);
        ((GeneralPath) shape).curveTo(117.763, 767.564, 118.029, 767.536,
                118.36, 767.536);
        ((GeneralPath) shape).curveTo(118.994, 767.536, 119.037, 767.703,
                119.037, 768.176);
        ((GeneralPath) shape).lineTo(119.037, 768.601);
        ((GeneralPath) shape).lineTo(119.013, 768.601);
        ((GeneralPath) shape).curveTo(118.832, 768.22003, 118.451004,
                768.22003, 118.065, 768.22003);
        ((GeneralPath) shape).curveTo(117.321, 768.22003, 116.944, 768.424,
                116.944, 769.15704);
        ((GeneralPath) shape).curveTo(116.944, 769.989, 117.385, 770.13605,
                118.065, 770.13605);
        ((GeneralPath) shape).curveTo(118.433, 770.13605, 118.89, 770.13605,
                119.038, 769.68506);
        ((GeneralPath) shape).lineTo(119.038, 770.135);
        ((GeneralPath) shape).moveTo(118.335, 768.767);
        ((GeneralPath) shape).curveTo(118.71, 768.767, 119.035995, 768.767,
                119.035995, 769.15704);
        ((GeneralPath) shape).curveTo(119.035995, 769.55707, 118.74, 769.58905,
                118.335, 769.58905);
        ((GeneralPath) shape).curveTo(117.844, 769.58905, 117.671, 769.55206,
                117.671, 769.15704);
        ((GeneralPath) shape).curveTo(117.671, 768.767, 117.95, 768.767,
                118.335, 768.767);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(120.471, 767.034);
        ((GeneralPath) shape).lineTo(120.471, 770.135);
        ((GeneralPath) shape).lineTo(121.199, 770.135);
        ((GeneralPath) shape).lineTo(121.199, 768.447);
        ((GeneralPath) shape).curveTo(121.199, 767.898, 121.284, 767.59503,
                121.912994, 767.59503);
        ((GeneralPath) shape).curveTo(122.37599, 767.59503, 122.47299, 767.747,
                122.47299, 768.19104);
        ((GeneralPath) shape).lineTo(122.47299, 770.135);
        ((GeneralPath) shape).lineTo(123.20099, 770.135);
        ((GeneralPath) shape).lineTo(123.20099, 768.114);
        ((GeneralPath) shape).curveTo(123.20099, 767.36804, 122.95899, 767.034,
                122.164986, 767.034);
        ((GeneralPath) shape).curveTo(121.74299, 767.034, 121.38299, 767.08,
                121.221985, 767.526);
        ((GeneralPath) shape).lineTo(121.19898, 767.526);
        ((GeneralPath) shape).lineTo(121.19898, 767.034);
        ((GeneralPath) shape).lineTo(120.471, 767.034);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(126.507, 767.034);
        ((GeneralPath) shape).lineTo(125.753006, 767.034);
        ((GeneralPath) shape).lineTo(125.15301, 769.675);
        ((GeneralPath) shape).lineTo(125.14101, 769.675);
        ((GeneralPath) shape).lineTo(124.35901, 767.034);
        ((GeneralPath) shape).lineTo(123.59401, 767.034);
        ((GeneralPath) shape).lineTo(124.61201, 770.135);
        ((GeneralPath) shape).lineTo(124.97001, 770.135);
        ((GeneralPath) shape).curveTo(124.90501, 770.499, 124.83501, 770.955,
                124.37001, 770.955);
        ((GeneralPath) shape).curveTo(124.31701, 770.955, 124.26401, 770.942,
                124.21101, 770.93604);
        ((GeneralPath) shape).lineTo(124.21101, 771.50104);
        ((GeneralPath) shape).curveTo(124.31702, 771.50104, 124.42301,
                771.50104, 124.530014, 771.50104);
        ((GeneralPath) shape).curveTo(125.39401, 771.50104, 125.53501,
                770.95306, 125.70502, 770.252);
        ((GeneralPath) shape).lineTo(126.507, 767.034);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(126.564, 770.864);
        ((GeneralPath) shape).lineTo(126.712006, 770.864);
        ((GeneralPath) shape).curveTo(127.351006, 770.864, 127.351006, 770.511,
                127.351006, 770.03503);
        ((GeneralPath) shape).lineTo(127.351006, 769.34705);
        ((GeneralPath) shape).lineTo(126.62301, 769.34705);
        ((GeneralPath) shape).lineTo(126.62301, 770.1351);
        ((GeneralPath) shape).lineTo(126.921005, 770.1351);
        ((GeneralPath) shape).curveTo(126.921005, 770.44006, 126.784004,
                770.4991, 126.564, 770.4991);
        ((GeneralPath) shape).lineTo(126.564, 770.864);
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
        paint = new Color(35, 31, 32, 255);
        shape = new Rectangle2D.Double(130.552001953125, 765.7579956054688,
                0.8190000057220459, 4.376999855041504);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(132.165, 767.034);
        ((GeneralPath) shape).lineTo(132.165, 770.135);
        ((GeneralPath) shape).lineTo(132.89299, 770.135);
        ((GeneralPath) shape).lineTo(132.89299, 768.447);
        ((GeneralPath) shape).curveTo(132.89299, 767.898, 132.978, 767.59503,
                133.607, 767.59503);
        ((GeneralPath) shape).curveTo(134.06999, 767.59503, 134.16699, 767.747,
                134.16699, 768.19104);
        ((GeneralPath) shape).lineTo(134.16699, 770.135);
        ((GeneralPath) shape).lineTo(134.89499, 770.135);
        ((GeneralPath) shape).lineTo(134.89499, 768.114);
        ((GeneralPath) shape).curveTo(134.89499, 767.36804, 134.65298, 767.034,
                133.859, 767.034);
        ((GeneralPath) shape).curveTo(133.437, 767.034, 133.077, 767.08,
                132.916, 767.526);
        ((GeneralPath) shape).lineTo(132.893, 767.526);
        ((GeneralPath) shape).lineTo(132.893, 767.034);
        ((GeneralPath) shape).lineTo(132.165, 767.034);
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
        ((GeneralPath) shape).moveTo(137.564, 768.952);
        ((GeneralPath) shape).curveTo(137.564, 769.48303, 137.506, 769.588,
                136.913, 769.588);
        ((GeneralPath) shape).curveTo(136.29, 769.588, 136.29, 769.352, 136.29,
                768.594);
        ((GeneralPath) shape).curveTo(136.29, 767.81396, 136.29, 767.595,
                136.913, 767.595);
        ((GeneralPath) shape).curveTo(137.42, 767.595, 137.564, 767.67596,
                137.564, 768.138);
        ((GeneralPath) shape).lineTo(138.29199, 768.138);
        ((GeneralPath) shape).curveTo(138.29199, 767.174, 137.81299, 767.034,
                136.913, 767.034);
        ((GeneralPath) shape).curveTo(135.728, 767.034, 135.562, 767.527,
                135.562, 768.595);
        ((GeneralPath) shape).curveTo(135.562, 769.813, 135.804, 770.13495,
                136.913, 770.13495);
        ((GeneralPath) shape).curveTo(137.93399, 770.13495, 138.29199,
                769.93994, 138.29199, 768.95197);
        ((GeneralPath) shape).lineTo(137.564, 768.95197);
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
        shape = new Rectangle2D.Double(139.0500030517578, 769.3359985351562,
                0.7279999852180481, 0.7990000247955322);
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
        ((GeneralPath) shape).moveTo(146.438, 767.242);
        ((GeneralPath) shape).lineTo(146.438, 767.008);
        ((GeneralPath) shape).curveTo(146.378, 765.758, 145.73201, 765.758,
                144.645, 765.758);
        ((GeneralPath) shape).curveTo(143.345, 765.758, 142.798, 766.071,
                142.798, 767.455);
        ((GeneralPath) shape).lineTo(142.798, 768.437);
        ((GeneralPath) shape).curveTo(142.798, 769.693, 143.07701, 770.193,
                144.645, 770.13403);
        ((GeneralPath) shape).curveTo(145.727, 770.12805, 146.438, 770.13403,
                146.438, 768.83905);
        ((GeneralPath) shape).lineTo(146.438, 768.55804);
        ((GeneralPath) shape).lineTo(145.619, 768.55804);
        ((GeneralPath) shape).lineTo(145.619, 768.79205);
        ((GeneralPath) shape).curveTo(145.619, 769.40405, 145.352, 769.40405,
                144.645, 769.40405);
        ((GeneralPath) shape).curveTo(143.726, 769.40405, 143.617, 769.24207,
                143.617, 768.40204);
        ((GeneralPath) shape).lineTo(143.617, 767.45404);
        ((GeneralPath) shape).curveTo(143.617, 766.61206, 143.768, 766.47504,
                144.645, 766.47504);
        ((GeneralPath) shape).curveTo(145.41101, 766.47504, 145.619, 766.51,
                145.619, 767.007);
        ((GeneralPath) shape).lineTo(145.619, 767.241);
        ((GeneralPath) shape).lineTo(146.438, 767.241);
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
        shape = new Rectangle2D.Double(147.0399932861328, 765.7579956054688,
                0.7279999852180481, 4.376999855041504);
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
        ((GeneralPath) shape).moveTo(150.509, 770.135);
        ((GeneralPath) shape).lineTo(151.237, 770.135);
        ((GeneralPath) shape).lineTo(151.237, 768.214);
        ((GeneralPath) shape).curveTo(151.237, 767.188, 150.823, 767.034,
                149.832, 767.034);
        ((GeneralPath) shape).curveTo(149.138, 767.034, 148.507, 767.034,
                148.507, 767.942);
        ((GeneralPath) shape).lineTo(149.235, 767.942);
        ((GeneralPath) shape).curveTo(149.235, 767.564, 149.501, 767.536,
                149.832, 767.536);
        ((GeneralPath) shape).curveTo(150.466, 767.536, 150.509, 767.703,
                150.509, 768.176);
        ((GeneralPath) shape).lineTo(150.509, 768.601);
        ((GeneralPath) shape).lineTo(150.485, 768.601);
        ((GeneralPath) shape).curveTo(150.304, 768.22003, 149.923, 768.22003,
                149.536, 768.22003);
        ((GeneralPath) shape).curveTo(148.79199, 768.22003, 148.416, 768.424,
                148.416, 769.15704);
        ((GeneralPath) shape).curveTo(148.416, 769.989, 148.857, 770.13605,
                149.536, 770.13605);
        ((GeneralPath) shape).curveTo(149.905, 770.13605, 150.361, 770.13605,
                150.509, 769.68506);
        ((GeneralPath) shape).lineTo(150.509, 770.135);
        ((GeneralPath) shape).moveTo(149.808, 768.767);
        ((GeneralPath) shape).curveTo(150.183, 768.767, 150.509, 768.767,
                150.509, 769.15704);
        ((GeneralPath) shape).curveTo(150.509, 769.55707, 150.213, 769.58905,
                149.808, 769.58905);
        ((GeneralPath) shape).curveTo(149.317, 769.58905, 149.144, 769.55206,
                149.144, 769.15704);
        ((GeneralPath) shape).curveTo(149.144, 768.767, 149.422, 768.767,
                149.808, 768.767);
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
        ((GeneralPath) shape).moveTo(154.491, 767.891);
        ((GeneralPath) shape).curveTo(154.491, 767.035, 153.777, 767.035,
                153.203, 767.035);
        ((GeneralPath) shape).curveTo(152.453, 767.035, 151.852, 767.035,
                151.852, 767.87994);
        ((GeneralPath) shape).curveTo(151.852, 768.634, 152.05, 768.77893,
                153.14, 768.79694);
        ((GeneralPath) shape).curveTo(153.837, 768.8079, 153.854, 768.9379,
                153.854, 769.19495);
        ((GeneralPath) shape).curveTo(153.854, 769.5889, 153.61, 769.5889,
                153.194, 769.5889);
        ((GeneralPath) shape).curveTo(152.679, 769.5889, 152.58, 769.53894,
                152.58, 769.1109);
        ((GeneralPath) shape).lineTo(151.852, 769.1109);
        ((GeneralPath) shape).curveTo(151.852, 770.1349, 152.39401, 770.1349,
                153.20001, 770.1349);
        ((GeneralPath) shape).curveTo(153.95102, 770.1349, 154.58202, 770.0509,
                154.58202, 769.1949);
        ((GeneralPath) shape).curveTo(154.58202, 768.2379, 153.95001, 768.2909,
                153.26402, 768.2569);
        ((GeneralPath) shape).curveTo(152.65703, 768.2279, 152.58002, 768.2169,
                152.58002, 767.9249);
        ((GeneralPath) shape).curveTo(152.58002, 767.5369, 152.83601, 767.5369,
                153.20702, 767.5369);
        ((GeneralPath) shape).curveTo(153.58002, 767.5369, 153.76302, 767.5369,
                153.76302, 767.8909);
        ((GeneralPath) shape).lineTo(154.491, 767.8909);
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
        ((GeneralPath) shape).moveTo(157.741, 767.891);
        ((GeneralPath) shape).curveTo(157.741, 767.035, 157.027, 767.035,
                156.453, 767.035);
        ((GeneralPath) shape).curveTo(155.703, 767.035, 155.102, 767.035,
                155.102, 767.87994);
        ((GeneralPath) shape).curveTo(155.102, 768.634, 155.3, 768.77893,
                156.39, 768.79694);
        ((GeneralPath) shape).curveTo(157.087, 768.8079, 157.104, 768.9379,
                157.104, 769.19495);
        ((GeneralPath) shape).curveTo(157.104, 769.5889, 156.86101, 769.5889,
                156.444, 769.5889);
        ((GeneralPath) shape).curveTo(155.929, 769.5889, 155.83, 769.53894,
                155.83, 769.1109);
        ((GeneralPath) shape).lineTo(155.102, 769.1109);
        ((GeneralPath) shape).curveTo(155.102, 770.1349, 155.64401, 770.1349,
                156.45001, 770.1349);
        ((GeneralPath) shape).curveTo(157.20001, 770.1349, 157.83202, 770.0509,
                157.83202, 769.1949);
        ((GeneralPath) shape).curveTo(157.83202, 768.2379, 157.19902, 768.2909,
                156.51402, 768.2569);
        ((GeneralPath) shape).curveTo(155.90703, 768.2279, 155.83002, 768.2169,
                155.83002, 767.9249);
        ((GeneralPath) shape).curveTo(155.83002, 767.5369, 156.08601, 767.5369,
                156.45702, 767.5369);
        ((GeneralPath) shape).curveTo(156.83002, 767.5369, 157.01302, 767.5369,
                157.01302, 767.8909);
        ((GeneralPath) shape).lineTo(157.741, 767.8909);
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
        ((GeneralPath) shape).moveTo(159.17, 765.758);
        ((GeneralPath) shape).lineTo(158.442, 765.758);
        ((GeneralPath) shape).lineTo(158.442, 766.382);
        ((GeneralPath) shape).lineTo(159.17, 766.382);
        ((GeneralPath) shape).lineTo(159.17, 765.758);
        ((GeneralPath) shape).moveTo(159.17, 767.034);
        ((GeneralPath) shape).lineTo(158.442, 767.034);
        ((GeneralPath) shape).lineTo(158.442, 770.135);
        ((GeneralPath) shape).lineTo(159.17, 770.135);
        ((GeneralPath) shape).lineTo(159.17, 767.034);
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
        ((GeneralPath) shape).moveTo(161.728, 768.952);
        ((GeneralPath) shape).curveTo(161.728, 769.48303, 161.66899, 769.588,
                161.077, 769.588);
        ((GeneralPath) shape).curveTo(160.454, 769.588, 160.454, 769.352,
                160.454, 768.594);
        ((GeneralPath) shape).curveTo(160.454, 767.81396, 160.454, 767.595,
                161.077, 767.595);
        ((GeneralPath) shape).curveTo(161.58499, 767.595, 161.728, 767.67596,
                161.728, 768.138);
        ((GeneralPath) shape).lineTo(162.456, 768.138);
        ((GeneralPath) shape).curveTo(162.456, 767.174, 161.97699, 767.034,
                161.077, 767.034);
        ((GeneralPath) shape).curveTo(159.892, 767.034, 159.726, 767.527,
                159.726, 768.595);
        ((GeneralPath) shape).curveTo(159.726, 769.813, 159.968, 770.13495,
                161.077, 770.13495);
        ((GeneralPath) shape).curveTo(162.09799, 770.13495, 162.456, 769.93994,
                162.456, 768.95197);
        ((GeneralPath) shape).lineTo(161.728, 768.95197);
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
        ((GeneralPath) shape).moveTo(165.907, 766.476);
        ((GeneralPath) shape).lineTo(167.114, 766.476);
        ((GeneralPath) shape).curveTo(167.536, 766.476, 167.727, 766.511,
                167.727, 766.974);
        ((GeneralPath) shape).curveTo(167.727, 767.437, 167.62001, 767.582,
                167.138, 767.582);
        ((GeneralPath) shape).lineTo(165.907, 767.582);
        ((GeneralPath) shape).lineTo(165.907, 766.476);
        ((GeneralPath) shape).moveTo(167.345, 770.135);
        ((GeneralPath) shape).curveTo(168.248, 770.135, 168.636, 769.883,
                168.636, 768.912);
        ((GeneralPath) shape).curveTo(168.636, 768.355, 168.522, 767.951,
                167.927, 767.881);
        ((GeneralPath) shape).lineTo(167.927, 767.857);
        ((GeneralPath) shape).curveTo(168.453, 767.729, 168.546, 767.393,
                168.546, 766.894);
        ((GeneralPath) shape).curveTo(168.546, 765.93396, 168.11801, 765.758,
                167.26901, 765.758);
        ((GeneralPath) shape).lineTo(165.08801, 765.758);
        ((GeneralPath) shape).lineTo(165.08801, 770.135);
        ((GeneralPath) shape).lineTo(167.345, 770.135);
        ((GeneralPath) shape).moveTo(165.907, 768.22);
        ((GeneralPath) shape).lineTo(167.22, 768.22);
        ((GeneralPath) shape).curveTo(167.714, 768.22, 167.817, 768.362,
                167.817, 768.84094);
        ((GeneralPath) shape).curveTo(167.817, 769.3809, 167.578, 769.41693,
                167.078, 769.41693);
        ((GeneralPath) shape).lineTo(165.907, 769.41693);
        ((GeneralPath) shape).lineTo(165.907, 768.22);
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
        ((GeneralPath) shape).moveTo(171.266, 770.135);
        ((GeneralPath) shape).lineTo(171.994, 770.135);
        ((GeneralPath) shape).lineTo(171.994, 768.214);
        ((GeneralPath) shape).curveTo(171.994, 767.188, 171.58, 767.034,
                170.589, 767.034);
        ((GeneralPath) shape).curveTo(169.895, 767.034, 169.264, 767.034,
                169.264, 767.942);
        ((GeneralPath) shape).lineTo(169.992, 767.942);
        ((GeneralPath) shape).curveTo(169.992, 767.564, 170.25801, 767.536,
                170.589, 767.536);
        ((GeneralPath) shape).curveTo(171.223, 767.536, 171.266, 767.703,
                171.266, 768.176);
        ((GeneralPath) shape).lineTo(171.266, 768.601);
        ((GeneralPath) shape).lineTo(171.242, 768.601);
        ((GeneralPath) shape).curveTo(171.06201, 768.22003, 170.68001,
                768.22003, 170.294, 768.22003);
        ((GeneralPath) shape).curveTo(169.55, 768.22003, 169.17401, 768.424,
                169.17401, 769.15704);
        ((GeneralPath) shape).curveTo(169.17401, 769.989, 169.61401, 770.13605,
                170.294, 770.13605);
        ((GeneralPath) shape).curveTo(170.662, 770.13605, 171.119, 770.13605,
                171.26701, 769.68506);
        ((GeneralPath) shape).lineTo(171.26701, 770.135);
        ((GeneralPath) shape).moveTo(170.564, 768.767);
        ((GeneralPath) shape).curveTo(170.939, 768.767, 171.265, 768.767,
                171.265, 769.15704);
        ((GeneralPath) shape).curveTo(171.265, 769.55707, 170.969, 769.58905,
                170.564, 769.58905);
        ((GeneralPath) shape).curveTo(170.073, 769.58905, 169.9, 769.55206,
                169.9, 769.15704);
        ((GeneralPath) shape).curveTo(169.9, 768.767, 170.179, 768.767,
                170.564, 768.767);
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
        ((GeneralPath) shape).moveTo(172.315, 767.582);
        ((GeneralPath) shape).lineTo(172.699, 767.582);
        ((GeneralPath) shape).lineTo(172.699, 769.17896);
        ((GeneralPath) shape).curveTo(172.699, 769.94995, 172.946, 770.13495,
                173.76001, 770.13495);
        ((GeneralPath) shape).curveTo(174.55801, 770.13495, 174.792, 769.85596,
                174.792, 768.93097);
        ((GeneralPath) shape).lineTo(174.15501, 768.93097);
        ((GeneralPath) shape).curveTo(174.15501, 769.256, 174.20102, 769.58795,
                173.76001, 769.58795);
        ((GeneralPath) shape).curveTo(173.42702, 769.58795, 173.42702,
                769.45795, 173.42702, 769.173);
        ((GeneralPath) shape).lineTo(173.42702, 767.582);
        ((GeneralPath) shape).lineTo(174.63702, 767.582);
        ((GeneralPath) shape).lineTo(174.63702, 767.034);
        ((GeneralPath) shape).lineTo(173.42702, 767.034);
        ((GeneralPath) shape).lineTo(173.42702, 766.326);
        ((GeneralPath) shape).lineTo(172.7, 766.326);
        ((GeneralPath) shape).lineTo(172.7, 767.034);
        ((GeneralPath) shape).lineTo(172.316, 767.034);
        ((GeneralPath) shape).lineTo(172.316, 767.582);
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
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(174.797, 767.582);
        ((GeneralPath) shape).lineTo(175.181, 767.582);
        ((GeneralPath) shape).lineTo(175.181, 769.17896);
        ((GeneralPath) shape).curveTo(175.181, 769.94995, 175.428, 770.13495,
                176.242, 770.13495);
        ((GeneralPath) shape).curveTo(177.039, 770.13495, 177.274, 769.85596,
                177.274, 768.93097);
        ((GeneralPath) shape).lineTo(176.63701, 768.93097);
        ((GeneralPath) shape).curveTo(176.63701, 769.256, 176.682, 769.58795,
                176.242, 769.58795);
        ((GeneralPath) shape).curveTo(175.90901, 769.58795, 175.90901,
                769.45795, 175.90901, 769.173);
        ((GeneralPath) shape).lineTo(175.90901, 767.582);
        ((GeneralPath) shape).lineTo(177.11902, 767.582);
        ((GeneralPath) shape).lineTo(177.11902, 767.034);
        ((GeneralPath) shape).lineTo(175.90901, 767.034);
        ((GeneralPath) shape).lineTo(175.90901, 766.326);
        ((GeneralPath) shape).lineTo(175.18102, 766.326);
        ((GeneralPath) shape).lineTo(175.18102, 767.034);
        ((GeneralPath) shape).lineTo(174.79701, 767.034);
        ((GeneralPath) shape).lineTo(174.79701, 767.582);
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
        paint = new Color(35, 31, 32, 255);
        shape = new Rectangle2D.Double(177.66200256347656, 765.7579956054688,
                0.7279999852180481, 4.376999855041504);
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
        ((GeneralPath) shape).moveTo(179.767, 768.312);
        ((GeneralPath) shape).curveTo(179.767, 767.73804, 179.808, 767.59503,
                180.441, 767.59503);
        ((GeneralPath) shape).curveTo(181.04, 767.59503, 181.13199, 767.645,
                181.13199, 768.312);
        ((GeneralPath) shape).lineTo(179.767, 768.312);
        ((GeneralPath) shape).moveTo(181.131, 769.163);
        ((GeneralPath) shape).curveTo(181.131, 769.588, 180.847, 769.588,
                180.44, 769.588);
        ((GeneralPath) shape).curveTo(179.783, 769.588, 179.766, 769.39,
                179.766, 768.767);
        ((GeneralPath) shape).lineTo(181.85901, 768.767);
        ((GeneralPath) shape).curveTo(181.85901, 767.40906, 181.69101,
                767.03503, 180.44, 767.03503);
        ((GeneralPath) shape).curveTo(179.211, 767.03503, 179.03801, 767.52405,
                179.03801, 768.642);
        ((GeneralPath) shape).curveTo(179.03801, 769.778, 179.27501, 770.135,
                180.44, 770.135);
        ((GeneralPath) shape).curveTo(181.309, 770.135, 181.85901, 770.089,
                181.85901, 769.163);
        ((GeneralPath) shape).lineTo(181.131, 769.163);
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
        ((GeneralPath) shape).moveTo(184.184, 766.476);
        ((GeneralPath) shape).lineTo(185.465, 766.476);
        ((GeneralPath) shape).lineTo(185.465, 765.758);
        ((GeneralPath) shape).lineTo(182.083, 765.758);
        ((GeneralPath) shape).lineTo(182.083, 766.476);
        ((GeneralPath) shape).lineTo(183.365, 766.476);
        ((GeneralPath) shape).lineTo(183.365, 770.135);
        ((GeneralPath) shape).lineTo(184.184, 770.135);
        ((GeneralPath) shape).lineTo(184.184, 766.476);
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
        ((GeneralPath) shape).moveTo(186.021, 768.312);
        ((GeneralPath) shape).curveTo(186.021, 767.73804, 186.062, 767.59503,
                186.69499, 767.59503);
        ((GeneralPath) shape).curveTo(187.29399, 767.59503, 187.38599, 767.645,
                187.38599, 768.312);
        ((GeneralPath) shape).lineTo(186.021, 768.312);
        ((GeneralPath) shape).moveTo(187.386, 769.163);
        ((GeneralPath) shape).curveTo(187.386, 769.588, 187.102, 769.588,
                186.695, 769.588);
        ((GeneralPath) shape).curveTo(186.03801, 769.588, 186.02101, 769.39,
                186.02101, 768.767);
        ((GeneralPath) shape).lineTo(188.11401, 768.767);
        ((GeneralPath) shape).curveTo(188.11401, 767.40906, 187.94601,
                767.03503, 186.695, 767.03503);
        ((GeneralPath) shape).curveTo(185.466, 767.03503, 185.29301, 767.52405,
                185.29301, 768.642);
        ((GeneralPath) shape).curveTo(185.29301, 769.778, 185.53001, 770.135,
                186.695, 770.135);
        ((GeneralPath) shape).curveTo(187.56401, 770.135, 188.11401, 770.089,
                188.11401, 769.163);
        ((GeneralPath) shape).lineTo(187.386, 769.163);
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
        ((GeneralPath) shape).moveTo(190.621, 768.952);
        ((GeneralPath) shape).curveTo(190.621, 769.48303, 190.563, 769.588,
                189.97, 769.588);
        ((GeneralPath) shape).curveTo(189.347, 769.588, 189.347, 769.352,
                189.347, 768.594);
        ((GeneralPath) shape).curveTo(189.347, 767.81396, 189.347, 767.595,
                189.97, 767.595);
        ((GeneralPath) shape).curveTo(190.477, 767.595, 190.621, 767.67596,
                190.621, 768.138);
        ((GeneralPath) shape).lineTo(191.349, 768.138);
        ((GeneralPath) shape).curveTo(191.349, 767.174, 190.87, 767.034,
                189.97, 767.034);
        ((GeneralPath) shape).curveTo(188.785, 767.034, 188.619, 767.527,
                188.619, 768.595);
        ((GeneralPath) shape).curveTo(188.619, 769.813, 188.86101, 770.13495,
                189.97, 770.13495);
        ((GeneralPath) shape).curveTo(190.991, 770.13495, 191.349, 769.93994,
                191.349, 768.95197);
        ((GeneralPath) shape).lineTo(190.621, 768.95197);
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
        ((GeneralPath) shape).moveTo(192.652, 765.758);
        ((GeneralPath) shape).lineTo(191.924, 765.758);
        ((GeneralPath) shape).lineTo(191.924, 770.135);
        ((GeneralPath) shape).lineTo(192.652, 770.135);
        ((GeneralPath) shape).lineTo(192.652, 768.447);
        ((GeneralPath) shape).curveTo(192.652, 767.898, 192.737, 767.59503,
                193.366, 767.59503);
        ((GeneralPath) shape).curveTo(193.829, 767.59503, 193.926, 767.747,
                193.926, 768.19104);
        ((GeneralPath) shape).lineTo(193.926, 770.135);
        ((GeneralPath) shape).lineTo(194.65399, 770.135);
        ((GeneralPath) shape).lineTo(194.65399, 768.114);
        ((GeneralPath) shape).curveTo(194.65399, 767.36804, 194.41199, 767.034,
                193.61699, 767.034);
        ((GeneralPath) shape).curveTo(193.19499, 767.034, 192.83499, 767.081,
                192.67499, 767.52997);
        ((GeneralPath) shape).lineTo(192.652, 767.52997);
        ((GeneralPath) shape).lineTo(192.652, 765.758);
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
        ((GeneralPath) shape).moveTo(195.537, 770.864);
        ((GeneralPath) shape).lineTo(195.685, 770.864);
        ((GeneralPath) shape).curveTo(196.323, 770.864, 196.323, 770.511,
                196.323, 770.03503);
        ((GeneralPath) shape).lineTo(196.323, 769.34705);
        ((GeneralPath) shape).lineTo(195.595, 769.34705);
        ((GeneralPath) shape).lineTo(195.595, 770.1351);
        ((GeneralPath) shape).lineTo(195.894, 770.1351);
        ((GeneralPath) shape).curveTo(195.894, 770.44006, 195.757, 770.4991,
                195.537, 770.4991);
        ((GeneralPath) shape).lineTo(195.537, 770.864);
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
        ((GeneralPath) shape).moveTo(200.343, 766.476);
        ((GeneralPath) shape).lineTo(201.55, 766.476);
        ((GeneralPath) shape).curveTo(201.972, 766.476, 202.16301, 766.511,
                202.16301, 766.974);
        ((GeneralPath) shape).curveTo(202.16301, 767.437, 202.057, 767.582,
                201.57501, 767.582);
        ((GeneralPath) shape).lineTo(200.34401, 767.582);
        ((GeneralPath) shape).lineTo(200.34401, 766.476);
        ((GeneralPath) shape).moveTo(201.782, 770.135);
        ((GeneralPath) shape).curveTo(202.685, 770.135, 203.073, 769.883,
                203.073, 768.912);
        ((GeneralPath) shape).curveTo(203.073, 768.355, 202.959, 767.951,
                202.364, 767.881);
        ((GeneralPath) shape).lineTo(202.364, 767.857);
        ((GeneralPath) shape).curveTo(202.89, 767.729, 202.983, 767.393,
                202.983, 766.894);
        ((GeneralPath) shape).curveTo(202.983, 765.93396, 202.55501, 765.758,
                201.70601, 765.758);
        ((GeneralPath) shape).lineTo(199.52501, 765.758);
        ((GeneralPath) shape).lineTo(199.52501, 770.135);
        ((GeneralPath) shape).lineTo(201.782, 770.135);
        ((GeneralPath) shape).moveTo(200.343, 768.22);
        ((GeneralPath) shape).lineTo(201.657, 768.22);
        ((GeneralPath) shape).curveTo(202.15, 768.22, 202.254, 768.362,
                202.254, 768.84094);
        ((GeneralPath) shape).curveTo(202.254, 769.3809, 202.015, 769.41693,
                201.51399, 769.41693);
        ((GeneralPath) shape).lineTo(200.34299, 769.41693);
        ((GeneralPath) shape).lineTo(200.34299, 768.22);
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
        ((GeneralPath) shape).moveTo(205.702, 770.135);
        ((GeneralPath) shape).lineTo(206.43, 770.135);
        ((GeneralPath) shape).lineTo(206.43, 768.214);
        ((GeneralPath) shape).curveTo(206.43, 767.188, 206.01599, 767.034,
                205.025, 767.034);
        ((GeneralPath) shape).curveTo(204.331, 767.034, 203.7, 767.034, 203.7,
                767.942);
        ((GeneralPath) shape).lineTo(204.428, 767.942);
        ((GeneralPath) shape).curveTo(204.428, 767.564, 204.694, 767.536,
                205.025, 767.536);
        ((GeneralPath) shape).curveTo(205.659, 767.536, 205.702, 767.703,
                205.702, 768.176);
        ((GeneralPath) shape).lineTo(205.702, 768.601);
        ((GeneralPath) shape).lineTo(205.678, 768.601);
        ((GeneralPath) shape).curveTo(205.497, 768.22003, 205.116, 768.22003,
                204.73, 768.22003);
        ((GeneralPath) shape).curveTo(203.986, 768.22003, 203.609, 768.424,
                203.609, 769.15704);
        ((GeneralPath) shape).curveTo(203.609, 769.989, 204.04999, 770.13605,
                204.73, 770.13605);
        ((GeneralPath) shape).curveTo(205.09799, 770.13605, 205.555, 770.13605,
                205.703, 769.68506);
        ((GeneralPath) shape).lineTo(205.703, 770.135);
        ((GeneralPath) shape).moveTo(205.001, 768.767);
        ((GeneralPath) shape).curveTo(205.376, 768.767, 205.70201, 768.767,
                205.70201, 769.15704);
        ((GeneralPath) shape).curveTo(205.70201, 769.55707, 205.406, 769.58905,
                205.001, 769.58905);
        ((GeneralPath) shape).curveTo(204.51001, 769.58905, 204.337, 769.55206,
                204.337, 769.15704);
        ((GeneralPath) shape).curveTo(204.337, 768.767, 204.616, 768.767,
                205.001, 768.767);
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
        ((GeneralPath) shape).moveTo(206.752, 767.582);
        ((GeneralPath) shape).lineTo(207.136, 767.582);
        ((GeneralPath) shape).lineTo(207.136, 769.17896);
        ((GeneralPath) shape).curveTo(207.136, 769.94995, 207.383, 770.13495,
                208.197, 770.13495);
        ((GeneralPath) shape).curveTo(208.99501, 770.13495, 209.229, 769.85596,
                209.229, 768.93097);
        ((GeneralPath) shape).lineTo(208.59201, 768.93097);
        ((GeneralPath) shape).curveTo(208.59201, 769.256, 208.63802, 769.58795,
                208.197, 769.58795);
        ((GeneralPath) shape).curveTo(207.86401, 769.58795, 207.86401,
                769.45795, 207.86401, 769.173);
        ((GeneralPath) shape).lineTo(207.86401, 767.582);
        ((GeneralPath) shape).lineTo(209.07402, 767.582);
        ((GeneralPath) shape).lineTo(209.07402, 767.034);
        ((GeneralPath) shape).lineTo(207.86401, 767.034);
        ((GeneralPath) shape).lineTo(207.86401, 766.326);
        ((GeneralPath) shape).lineTo(207.13602, 766.326);
        ((GeneralPath) shape).lineTo(207.13602, 767.034);
        ((GeneralPath) shape).lineTo(206.75201, 767.034);
        ((GeneralPath) shape).lineTo(206.75201, 767.582);
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
        ((GeneralPath) shape).moveTo(209.233, 767.582);
        ((GeneralPath) shape).lineTo(209.617, 767.582);
        ((GeneralPath) shape).lineTo(209.617, 769.17896);
        ((GeneralPath) shape).curveTo(209.617, 769.94995, 209.864, 770.13495,
                210.67801, 770.13495);
        ((GeneralPath) shape).curveTo(211.47601, 770.13495, 211.71, 769.85596,
                211.71, 768.93097);
        ((GeneralPath) shape).lineTo(211.07301, 768.93097);
        ((GeneralPath) shape).curveTo(211.07301, 769.256, 211.11801, 769.58795,
                210.67702, 769.58795);
        ((GeneralPath) shape).curveTo(210.34402, 769.58795, 210.34402,
                769.45795, 210.34402, 769.173);
        ((GeneralPath) shape).lineTo(210.34402, 767.582);
        ((GeneralPath) shape).lineTo(211.55403, 767.582);
        ((GeneralPath) shape).lineTo(211.55403, 767.034);
        ((GeneralPath) shape).lineTo(210.34402, 767.034);
        ((GeneralPath) shape).lineTo(210.34402, 766.326);
        ((GeneralPath) shape).lineTo(209.61603, 766.326);
        ((GeneralPath) shape).lineTo(209.61603, 767.034);
        ((GeneralPath) shape).lineTo(209.23203, 767.034);
        ((GeneralPath) shape).lineTo(209.23203, 767.582);
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
        shape = new Rectangle2D.Double(212.09800720214844, 765.7579956054688,
                0.7279999852180481, 4.376999855041504);
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
        ((GeneralPath) shape).moveTo(214.203, 768.312);
        ((GeneralPath) shape).curveTo(214.203, 767.73804, 214.24501, 767.59503,
                214.877, 767.59503);
        ((GeneralPath) shape).curveTo(215.476, 767.59503, 215.568, 767.645,
                215.568, 768.312);
        ((GeneralPath) shape).lineTo(214.203, 768.312);
        ((GeneralPath) shape).moveTo(215.568, 769.163);
        ((GeneralPath) shape).curveTo(215.568, 769.588, 215.28299, 769.588,
                214.877, 769.588);
        ((GeneralPath) shape).curveTo(214.22, 769.588, 214.203, 769.39,
                214.203, 768.767);
        ((GeneralPath) shape).lineTo(216.296, 768.767);
        ((GeneralPath) shape).curveTo(216.296, 767.40906, 216.128, 767.03503,
                214.877, 767.03503);
        ((GeneralPath) shape).curveTo(213.648, 767.03503, 213.475, 767.52405,
                213.475, 768.642);
        ((GeneralPath) shape).curveTo(213.475, 769.778, 213.71301, 770.135,
                214.877, 770.135);
        ((GeneralPath) shape).curveTo(215.746, 770.135, 216.296, 770.089,
                216.296, 769.163);
        ((GeneralPath) shape).lineTo(215.568, 769.163);
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
        ((GeneralPath) shape).moveTo(218.62, 766.476);
        ((GeneralPath) shape).lineTo(219.901, 766.476);
        ((GeneralPath) shape).lineTo(219.901, 765.758);
        ((GeneralPath) shape).lineTo(216.52, 765.758);
        ((GeneralPath) shape).lineTo(216.52, 766.476);
        ((GeneralPath) shape).lineTo(217.801, 766.476);
        ((GeneralPath) shape).lineTo(217.801, 770.135);
        ((GeneralPath) shape).lineTo(218.62, 770.135);
        ((GeneralPath) shape).lineTo(218.62, 766.476);
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
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(220.458, 768.312);
        ((GeneralPath) shape).curveTo(220.458, 767.73804, 220.499, 767.59503,
                221.13199, 767.59503);
        ((GeneralPath) shape).curveTo(221.73099, 767.59503, 221.82298, 767.645,
                221.82298, 768.312);
        ((GeneralPath) shape).lineTo(220.458, 768.312);
        ((GeneralPath) shape).moveTo(221.822, 769.163);
        ((GeneralPath) shape).curveTo(221.822, 769.588, 221.53801, 769.588,
                221.13101, 769.588);
        ((GeneralPath) shape).curveTo(220.47401, 769.588, 220.45702, 769.39,
                220.45702, 768.767);
        ((GeneralPath) shape).lineTo(222.55002, 768.767);
        ((GeneralPath) shape).curveTo(222.55002, 767.40906, 222.38202,
                767.03503, 221.13101, 767.03503);
        ((GeneralPath) shape).curveTo(219.90201, 767.03503, 219.72902,
                767.52405, 219.72902, 768.642);
        ((GeneralPath) shape).curveTo(219.72902, 769.778, 219.96602, 770.135,
                221.13101, 770.135);
        ((GeneralPath) shape).curveTo(222.00002, 770.135, 222.55002, 770.089,
                222.55002, 769.163);
        ((GeneralPath) shape).lineTo(221.822, 769.163);
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
        ((GeneralPath) shape).moveTo(225.057, 768.952);
        ((GeneralPath) shape).curveTo(225.057, 769.48303, 224.99901, 769.588,
                224.406, 769.588);
        ((GeneralPath) shape).curveTo(223.783, 769.588, 223.783, 769.352,
                223.783, 768.594);
        ((GeneralPath) shape).curveTo(223.783, 767.81396, 223.783, 767.595,
                224.406, 767.595);
        ((GeneralPath) shape).curveTo(224.91301, 767.595, 225.057, 767.67596,
                225.057, 768.138);
        ((GeneralPath) shape).lineTo(225.785, 768.138);
        ((GeneralPath) shape).curveTo(225.785, 767.174, 225.306, 767.034,
                224.406, 767.034);
        ((GeneralPath) shape).curveTo(223.22101, 767.034, 223.05501, 767.527,
                223.05501, 768.595);
        ((GeneralPath) shape).curveTo(223.05501, 769.813, 223.29701, 770.13495,
                224.406, 770.13495);
        ((GeneralPath) shape).curveTo(225.427, 770.13495, 225.785, 769.93994,
                225.785, 768.95197);
        ((GeneralPath) shape).lineTo(225.057, 768.95197);
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
        ((GeneralPath) shape).moveTo(227.089, 765.758);
        ((GeneralPath) shape).lineTo(226.36101, 765.758);
        ((GeneralPath) shape).lineTo(226.36101, 770.135);
        ((GeneralPath) shape).lineTo(227.089, 770.135);
        ((GeneralPath) shape).lineTo(227.089, 768.447);
        ((GeneralPath) shape).curveTo(227.089, 767.898, 227.17401, 767.59503,
                227.80301, 767.59503);
        ((GeneralPath) shape).curveTo(228.266, 767.59503, 228.363, 767.747,
                228.363, 768.19104);
        ((GeneralPath) shape).lineTo(228.363, 770.135);
        ((GeneralPath) shape).lineTo(229.091, 770.135);
        ((GeneralPath) shape).lineTo(229.091, 768.114);
        ((GeneralPath) shape).curveTo(229.091, 767.36804, 228.849, 767.034,
                228.054, 767.034);
        ((GeneralPath) shape).curveTo(227.632, 767.034, 227.272, 767.081,
                227.112, 767.52997);
        ((GeneralPath) shape).lineTo(227.089, 767.52997);
        ((GeneralPath) shape).lineTo(227.089, 765.758);
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
        ((GeneralPath) shape).moveTo(229.973, 770.864);
        ((GeneralPath) shape).lineTo(230.121, 770.864);
        ((GeneralPath) shape).curveTo(230.759, 770.864, 230.759, 770.511,
                230.759, 770.03503);
        ((GeneralPath) shape).lineTo(230.759, 769.34705);
        ((GeneralPath) shape).lineTo(230.031, 769.34705);
        ((GeneralPath) shape).lineTo(230.031, 770.1351);
        ((GeneralPath) shape).lineTo(230.33, 770.1351);
        ((GeneralPath) shape).curveTo(230.33, 770.44006, 230.19301, 770.4991,
                229.973, 770.4991);
        ((GeneralPath) shape).lineTo(229.973, 770.864);
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
        ((GeneralPath) shape).moveTo(233.902, 767.308);
        ((GeneralPath) shape).lineTo(234.04999, 767.308);
        ((GeneralPath) shape).curveTo(234.68799, 767.308, 234.68799, 766.956,
                234.68799, 766.44696);
        ((GeneralPath) shape).lineTo(234.68799, 765.759);
        ((GeneralPath) shape).lineTo(233.95999, 765.759);
        ((GeneralPath) shape).lineTo(233.95999, 766.547);
        ((GeneralPath) shape).lineTo(234.258, 766.547);
        ((GeneralPath) shape).curveTo(234.258, 766.87897, 234.121, 766.944,
                233.901, 766.944);
        ((GeneralPath) shape).lineTo(233.901, 767.308);
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
        ((GeneralPath) shape).moveTo(239.589, 766.446);
        ((GeneralPath) shape).lineTo(239.589, 770.135);
        ((GeneralPath) shape).lineTo(240.408, 770.135);
        ((GeneralPath) shape).lineTo(240.408, 765.758);
        ((GeneralPath) shape).lineTo(239.047, 765.758);
        ((GeneralPath) shape).lineTo(237.869, 769.01);
        ((GeneralPath) shape).lineTo(237.846, 769.01);
        ((GeneralPath) shape).lineTo(236.643, 765.758);
        ((GeneralPath) shape).lineTo(235.312, 765.758);
        ((GeneralPath) shape).lineTo(235.312, 770.135);
        ((GeneralPath) shape).lineTo(236.131, 770.135);
        ((GeneralPath) shape).lineTo(236.131, 766.465);
        ((GeneralPath) shape).lineTo(237.48, 770.135);
        ((GeneralPath) shape).lineTo(238.24, 770.135);
        ((GeneralPath) shape).lineTo(239.589, 766.446);
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
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(241.825, 768.312);
        ((GeneralPath) shape).curveTo(241.825, 767.73804, 241.867, 767.59503,
                242.499, 767.59503);
        ((GeneralPath) shape).curveTo(243.09799, 767.59503, 243.18999, 767.645,
                243.18999, 768.312);
        ((GeneralPath) shape).lineTo(241.825, 768.312);
        ((GeneralPath) shape).moveTo(243.19, 769.163);
        ((GeneralPath) shape).curveTo(243.19, 769.588, 242.905, 769.588,
                242.49901, 769.588);
        ((GeneralPath) shape).curveTo(241.84201, 769.588, 241.82501, 769.39,
                241.82501, 768.767);
        ((GeneralPath) shape).lineTo(243.91801, 768.767);
        ((GeneralPath) shape).curveTo(243.91801, 767.40906, 243.75002,
                767.03503, 242.49901, 767.03503);
        ((GeneralPath) shape).curveTo(241.27, 767.03503, 241.09702, 767.52405,
                241.09702, 768.642);
        ((GeneralPath) shape).curveTo(241.09702, 769.778, 241.33502, 770.135,
                242.49901, 770.135);
        ((GeneralPath) shape).curveTo(243.36801, 770.135, 243.91801, 770.089,
                243.91801, 769.163);
        ((GeneralPath) shape).lineTo(243.19, 769.163);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(246.425, 768.952);
        ((GeneralPath) shape).curveTo(246.425, 769.48303, 246.366, 769.588,
                245.774, 769.588);
        ((GeneralPath) shape).curveTo(245.151, 769.588, 245.151, 769.352,
                245.151, 768.594);
        ((GeneralPath) shape).curveTo(245.151, 767.81396, 245.151, 767.595,
                245.774, 767.595);
        ((GeneralPath) shape).curveTo(246.282, 767.595, 246.425, 767.67596,
                246.425, 768.138);
        ((GeneralPath) shape).lineTo(247.153, 768.138);
        ((GeneralPath) shape).curveTo(247.153, 767.174, 246.674, 767.034,
                245.774, 767.034);
        ((GeneralPath) shape).curveTo(244.589, 767.034, 244.423, 767.527,
                244.423, 768.595);
        ((GeneralPath) shape).curveTo(244.423, 769.813, 244.66501, 770.13495,
                245.774, 770.13495);
        ((GeneralPath) shape).curveTo(246.795, 770.13495, 247.153, 769.93994,
                247.153, 768.95197);
        ((GeneralPath) shape).lineTo(246.425, 768.95197);
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
        ((GeneralPath) shape).moveTo(248.457, 765.758);
        ((GeneralPath) shape).lineTo(247.729, 765.758);
        ((GeneralPath) shape).lineTo(247.729, 770.135);
        ((GeneralPath) shape).lineTo(248.457, 770.135);
        ((GeneralPath) shape).lineTo(248.457, 768.447);
        ((GeneralPath) shape).curveTo(248.457, 767.898, 248.542, 767.59503,
                249.171, 767.59503);
        ((GeneralPath) shape).curveTo(249.634, 767.59503, 249.731, 767.747,
                249.731, 768.19104);
        ((GeneralPath) shape).lineTo(249.731, 770.135);
        ((GeneralPath) shape).lineTo(250.459, 770.135);
        ((GeneralPath) shape).lineTo(250.459, 768.114);
        ((GeneralPath) shape).curveTo(250.459, 767.36804, 250.217, 767.034,
                249.422, 767.034);
        ((GeneralPath) shape).curveTo(249.0, 767.034, 248.64, 767.081, 248.479,
                767.52997);
        ((GeneralPath) shape).lineTo(248.457, 767.52997);
        ((GeneralPath) shape).lineTo(248.457, 765.758);
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
        ((GeneralPath) shape).moveTo(255.275, 770.135);
        ((GeneralPath) shape).lineTo(256.003, 770.135);
        ((GeneralPath) shape).lineTo(256.003, 768.214);
        ((GeneralPath) shape).curveTo(256.003, 767.188, 255.58899, 767.034,
                254.59898, 767.034);
        ((GeneralPath) shape).curveTo(253.90498, 767.034, 253.27399, 767.034,
                253.27399, 767.942);
        ((GeneralPath) shape).lineTo(254.00198, 767.942);
        ((GeneralPath) shape).curveTo(254.00198, 767.564, 254.26799, 767.536,
                254.59999, 767.536);
        ((GeneralPath) shape).curveTo(255.234, 767.536, 255.277, 767.703,
                255.277, 768.176);
        ((GeneralPath) shape).lineTo(255.277, 768.601);
        ((GeneralPath) shape).lineTo(255.25299, 768.601);
        ((GeneralPath) shape).curveTo(255.07199, 768.22003, 254.691, 768.22003,
                254.305, 768.22003);
        ((GeneralPath) shape).curveTo(253.56099, 768.22003, 253.18399, 768.424,
                253.18399, 769.15704);
        ((GeneralPath) shape).curveTo(253.18399, 769.989, 253.62498, 770.13605,
                254.305, 770.13605);
        ((GeneralPath) shape).curveTo(254.67299, 770.13605, 255.12999,
                770.13605, 255.278, 769.68506);
        ((GeneralPath) shape).lineTo(255.278, 770.135);
        ((GeneralPath) shape).moveTo(254.574, 768.767);
        ((GeneralPath) shape).curveTo(254.949, 768.767, 255.27501, 768.767,
                255.27501, 769.15704);
        ((GeneralPath) shape).curveTo(255.27501, 769.55707, 254.979, 769.58905,
                254.574, 769.58905);
        ((GeneralPath) shape).curveTo(254.08301, 769.58905, 253.91, 769.55206,
                253.91, 769.15704);
        ((GeneralPath) shape).curveTo(253.91, 768.767, 254.189, 768.767,
                254.574, 768.767);
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
        ((GeneralPath) shape).moveTo(256.709, 767.034);
        ((GeneralPath) shape).lineTo(256.709, 770.135);
        ((GeneralPath) shape).lineTo(257.437, 770.135);
        ((GeneralPath) shape).lineTo(257.437, 768.447);
        ((GeneralPath) shape).curveTo(257.437, 767.898, 257.522, 767.59503,
                258.151, 767.59503);
        ((GeneralPath) shape).curveTo(258.614, 767.59503, 258.711, 767.747,
                258.711, 768.19104);
        ((GeneralPath) shape).lineTo(258.711, 770.135);
        ((GeneralPath) shape).lineTo(259.439, 770.135);
        ((GeneralPath) shape).lineTo(259.439, 768.114);
        ((GeneralPath) shape).curveTo(259.439, 767.36804, 259.197, 767.034,
                258.40298, 767.034);
        ((GeneralPath) shape).curveTo(257.981, 767.034, 257.62097, 767.08,
                257.46, 767.526);
        ((GeneralPath) shape).lineTo(257.43698, 767.526);
        ((GeneralPath) shape).lineTo(257.43698, 767.034);
        ((GeneralPath) shape).lineTo(256.709, 767.034);
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
        ((GeneralPath) shape).moveTo(262.2, 770.135);
        ((GeneralPath) shape).lineTo(262.928, 770.135);
        ((GeneralPath) shape).lineTo(262.928, 765.758);
        ((GeneralPath) shape).lineTo(262.2, 765.758);
        ((GeneralPath) shape).lineTo(262.2, 767.47797);
        ((GeneralPath) shape).lineTo(262.183, 767.47797);
        ((GeneralPath) shape).curveTo(262.027, 767.105, 261.638, 767.035,
                261.268, 767.035);
        ((GeneralPath) shape).curveTo(260.22702, 767.035, 260.10602, 767.60895,
                260.10602, 768.503);
        ((GeneralPath) shape).curveTo(260.10602, 769.434, 260.10602, 770.136,
                261.268, 770.136);
        ((GeneralPath) shape).curveTo(261.689, 770.136, 262.018, 770.04596,
                262.2, 769.656);
        ((GeneralPath) shape).lineTo(262.2, 770.135);
        ((GeneralPath) shape).moveTo(261.483, 767.595);
        ((GeneralPath) shape).curveTo(262.113, 767.595, 262.2, 767.85394,
                262.2, 768.50195);
        ((GeneralPath) shape).curveTo(262.2, 769.225, 262.2, 769.58795,
                261.483, 769.58795);
        ((GeneralPath) shape).curveTo(260.835, 769.58795, 260.835, 769.18896,
                260.835, 768.50195);
        ((GeneralPath) shape).curveTo(260.834, 767.738, 260.979, 767.595,
                261.483, 767.595);
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
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(266.532, 766.476);
        ((GeneralPath) shape).lineTo(267.739, 766.476);
        ((GeneralPath) shape).curveTo(268.161, 766.476, 268.35202, 766.511,
                268.35202, 766.974);
        ((GeneralPath) shape).curveTo(268.35202, 767.437, 268.24503, 767.582,
                267.76303, 767.582);
        ((GeneralPath) shape).lineTo(266.53204, 767.582);
        ((GeneralPath) shape).lineTo(266.53204, 766.476);
        ((GeneralPath) shape).moveTo(267.971, 770.135);
        ((GeneralPath) shape).curveTo(268.87402, 770.135, 269.262, 769.883,
                269.262, 768.912);
        ((GeneralPath) shape).curveTo(269.262, 768.355, 269.14798, 767.951,
                268.55298, 767.881);
        ((GeneralPath) shape).lineTo(268.55298, 767.857);
        ((GeneralPath) shape).curveTo(269.07898, 767.729, 269.17197, 767.393,
                269.17197, 766.894);
        ((GeneralPath) shape).curveTo(269.17197, 765.93396, 268.74396, 765.758,
                267.89496, 765.758);
        ((GeneralPath) shape).lineTo(265.71396, 765.758);
        ((GeneralPath) shape).lineTo(265.71396, 770.135);
        ((GeneralPath) shape).lineTo(267.971, 770.135);
        ((GeneralPath) shape).moveTo(266.532, 768.22);
        ((GeneralPath) shape).lineTo(267.846, 768.22);
        ((GeneralPath) shape).curveTo(268.33902, 768.22, 268.443, 768.362,
                268.443, 768.84094);
        ((GeneralPath) shape).curveTo(268.443, 769.3809, 268.20398, 769.41693,
                267.70398, 769.41693);
        ((GeneralPath) shape).lineTo(266.53198, 769.41693);
        ((GeneralPath) shape).lineTo(266.53198, 768.22);
        g.setPaint(paint);
        g.fill(shape);
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
        ((GeneralPath) shape).moveTo(271.891, 770.135);
        ((GeneralPath) shape).lineTo(272.619, 770.135);
        ((GeneralPath) shape).lineTo(272.619, 768.214);
        ((GeneralPath) shape).curveTo(272.619, 767.188, 272.205, 767.034,
                271.214, 767.034);
        ((GeneralPath) shape).curveTo(270.52, 767.034, 269.88898, 767.034,
                269.88898, 767.942);
        ((GeneralPath) shape).lineTo(270.61697, 767.942);
        ((GeneralPath) shape).curveTo(270.61697, 767.564, 270.88297, 767.536,
                271.21396, 767.536);
        ((GeneralPath) shape).curveTo(271.84796, 767.536, 271.89096, 767.703,
                271.89096, 768.176);
        ((GeneralPath) shape).lineTo(271.89096, 768.601);
        ((GeneralPath) shape).lineTo(271.86697, 768.601);
        ((GeneralPath) shape).curveTo(271.68597, 768.22003, 271.30496,
                768.22003, 270.91898, 768.22003);
        ((GeneralPath) shape).curveTo(270.175, 768.22003, 269.79797, 768.424,
                269.79797, 769.15704);
        ((GeneralPath) shape).curveTo(269.79797, 769.989, 270.23898, 770.13605,
                270.91898, 770.13605);
        ((GeneralPath) shape).curveTo(271.287, 770.13605, 271.744, 770.13605,
                271.89197, 769.68506);
        ((GeneralPath) shape).lineTo(271.89197, 770.135);
        ((GeneralPath) shape).moveTo(271.189, 768.767);
        ((GeneralPath) shape).curveTo(271.564, 768.767, 271.88998, 768.767,
                271.88998, 769.15704);
        ((GeneralPath) shape).curveTo(271.88998, 769.55707, 271.594, 769.58905,
                271.189, 769.58905);
        ((GeneralPath) shape).curveTo(270.698, 769.58905, 270.525, 769.55206,
                270.525, 769.15704);
        ((GeneralPath) shape).curveTo(270.525, 768.767, 270.804, 768.767,
                271.189, 768.767);
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
        ((GeneralPath) shape).moveTo(272.94, 767.582);
        ((GeneralPath) shape).lineTo(273.324, 767.582);
        ((GeneralPath) shape).lineTo(273.324, 769.17896);
        ((GeneralPath) shape).curveTo(273.324, 769.94995, 273.572, 770.13495,
                274.385, 770.13495);
        ((GeneralPath) shape).curveTo(275.183, 770.13495, 275.41702, 769.85596,
                275.41702, 768.93097);
        ((GeneralPath) shape).lineTo(274.78003, 768.93097);
        ((GeneralPath) shape).curveTo(274.78003, 769.256, 274.82602, 769.58795,
                274.38504, 769.58795);
        ((GeneralPath) shape).curveTo(274.05203, 769.58795, 274.05203,
                769.45795, 274.05203, 769.173);
        ((GeneralPath) shape).lineTo(274.05203, 767.582);
        ((GeneralPath) shape).lineTo(275.26202, 767.582);
        ((GeneralPath) shape).lineTo(275.26202, 767.034);
        ((GeneralPath) shape).lineTo(274.05203, 767.034);
        ((GeneralPath) shape).lineTo(274.05203, 766.326);
        ((GeneralPath) shape).lineTo(273.32404, 766.326);
        ((GeneralPath) shape).lineTo(273.32404, 767.034);
        ((GeneralPath) shape).lineTo(272.94003, 767.034);
        ((GeneralPath) shape).lineTo(272.94003, 767.582);
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
        ((GeneralPath) shape).moveTo(275.421, 767.582);
        ((GeneralPath) shape).lineTo(275.805, 767.582);
        ((GeneralPath) shape).lineTo(275.805, 769.17896);
        ((GeneralPath) shape).curveTo(275.805, 769.94995, 276.052, 770.13495,
                276.866, 770.13495);
        ((GeneralPath) shape).curveTo(277.664, 770.13495, 277.898, 769.85596,
                277.898, 768.93097);
        ((GeneralPath) shape).lineTo(277.26102, 768.93097);
        ((GeneralPath) shape).curveTo(277.26102, 769.256, 277.30603, 769.58795,
                276.86502, 769.58795);
        ((GeneralPath) shape).curveTo(276.532, 769.58795, 276.532, 769.45795,
                276.532, 769.173);
        ((GeneralPath) shape).lineTo(276.532, 767.582);
        ((GeneralPath) shape).lineTo(277.742, 767.582);
        ((GeneralPath) shape).lineTo(277.742, 767.034);
        ((GeneralPath) shape).lineTo(276.532, 767.034);
        ((GeneralPath) shape).lineTo(276.532, 766.326);
        ((GeneralPath) shape).lineTo(275.80402, 766.326);
        ((GeneralPath) shape).lineTo(275.80402, 767.034);
        ((GeneralPath) shape).lineTo(275.42, 767.034);
        ((GeneralPath) shape).lineTo(275.42, 767.582);
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
        shape = new Rectangle2D.Double(278.2869873046875, 765.7579956054688,
                0.7279999852180481, 4.376999855041504);
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
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(280.391, 768.312);
        ((GeneralPath) shape).curveTo(280.391, 767.73804, 280.43298, 767.59503,
                281.065, 767.59503);
        ((GeneralPath) shape).curveTo(281.664, 767.59503, 281.756, 767.645,
                281.756, 768.312);
        ((GeneralPath) shape).lineTo(280.391, 768.312);
        ((GeneralPath) shape).moveTo(281.756, 769.163);
        ((GeneralPath) shape).curveTo(281.756, 769.588, 281.471, 769.588,
                281.065, 769.588);
        ((GeneralPath) shape).curveTo(280.408, 769.588, 280.391, 769.39,
                280.391, 768.767);
        ((GeneralPath) shape).lineTo(282.48398, 768.767);
        ((GeneralPath) shape).curveTo(282.48398, 767.40906, 282.31598,
                767.03503, 281.06497, 767.03503);
        ((GeneralPath) shape).curveTo(279.83597, 767.03503, 279.66296,
                767.52405, 279.66296, 768.642);
        ((GeneralPath) shape).curveTo(279.66296, 769.778, 279.90097, 770.135,
                281.06497, 770.135);
        ((GeneralPath) shape).curveTo(281.93396, 770.135, 282.48398, 770.089,
                282.48398, 769.163);
        ((GeneralPath) shape).lineTo(281.756, 769.163);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(287.356, 766.446);
        ((GeneralPath) shape).lineTo(287.356, 770.135);
        ((GeneralPath) shape).lineTo(288.176, 770.135);
        ((GeneralPath) shape).lineTo(288.176, 765.758);
        ((GeneralPath) shape).lineTo(286.815, 765.758);
        ((GeneralPath) shape).lineTo(285.636, 769.01);
        ((GeneralPath) shape).lineTo(285.613, 769.01);
        ((GeneralPath) shape).lineTo(284.411, 765.758);
        ((GeneralPath) shape).lineTo(283.08, 765.758);
        ((GeneralPath) shape).lineTo(283.08, 770.135);
        ((GeneralPath) shape).lineTo(283.898, 770.135);
        ((GeneralPath) shape).lineTo(283.898, 766.465);
        ((GeneralPath) shape).lineTo(285.248, 770.135);
        ((GeneralPath) shape).lineTo(286.007, 770.135);
        ((GeneralPath) shape).lineTo(287.356, 766.446);
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
        ((GeneralPath) shape).moveTo(289.593, 768.312);
        ((GeneralPath) shape).curveTo(289.593, 767.73804, 289.63498, 767.59503,
                290.267, 767.59503);
        ((GeneralPath) shape).curveTo(290.866, 767.59503, 290.958, 767.645,
                290.958, 768.312);
        ((GeneralPath) shape).lineTo(289.593, 768.312);
        ((GeneralPath) shape).moveTo(290.958, 769.163);
        ((GeneralPath) shape).curveTo(290.958, 769.588, 290.673, 769.588,
                290.267, 769.588);
        ((GeneralPath) shape).curveTo(289.61, 769.588, 289.593, 769.39,
                289.593, 768.767);
        ((GeneralPath) shape).lineTo(291.68597, 768.767);
        ((GeneralPath) shape).curveTo(291.68597, 767.40906, 291.51797,
                767.03503, 290.26697, 767.03503);
        ((GeneralPath) shape).curveTo(289.03796, 767.03503, 288.86496,
                767.52405, 288.86496, 768.642);
        ((GeneralPath) shape).curveTo(288.86496, 769.778, 289.10297, 770.135,
                290.26697, 770.135);
        ((GeneralPath) shape).curveTo(291.13596, 770.135, 291.68597, 770.089,
                291.68597, 769.163);
        ((GeneralPath) shape).lineTo(290.958, 769.163);
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
        ((GeneralPath) shape).moveTo(294.193, 768.952);
        ((GeneralPath) shape).curveTo(294.193, 769.48303, 294.134, 769.588,
                293.542, 769.588);
        ((GeneralPath) shape).curveTo(292.919, 769.588, 292.919, 769.352,
                292.919, 768.594);
        ((GeneralPath) shape).curveTo(292.919, 767.81396, 292.919, 767.595,
                293.542, 767.595);
        ((GeneralPath) shape).curveTo(294.05, 767.595, 294.193, 767.67596,
                294.193, 768.138);
        ((GeneralPath) shape).lineTo(294.921, 768.138);
        ((GeneralPath) shape).curveTo(294.921, 767.174, 294.442, 767.034,
                293.542, 767.034);
        ((GeneralPath) shape).curveTo(292.357, 767.034, 292.19098, 767.527,
                292.19098, 768.595);
        ((GeneralPath) shape).curveTo(292.19098, 769.813, 292.43298, 770.13495,
                293.542, 770.13495);
        ((GeneralPath) shape).curveTo(294.563, 770.13495, 294.921, 769.93994,
                294.921, 768.95197);
        ((GeneralPath) shape).lineTo(294.193, 768.95197);
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
        ((GeneralPath) shape).moveTo(296.225, 765.758);
        ((GeneralPath) shape).lineTo(295.497, 765.758);
        ((GeneralPath) shape).lineTo(295.497, 770.135);
        ((GeneralPath) shape).lineTo(296.225, 770.135);
        ((GeneralPath) shape).lineTo(296.225, 768.447);
        ((GeneralPath) shape).curveTo(296.225, 767.898, 296.31, 767.59503,
                296.939, 767.59503);
        ((GeneralPath) shape).curveTo(297.402, 767.59503, 297.499, 767.747,
                297.499, 768.19104);
        ((GeneralPath) shape).lineTo(297.499, 770.135);
        ((GeneralPath) shape).lineTo(298.227, 770.135);
        ((GeneralPath) shape).lineTo(298.227, 768.114);
        ((GeneralPath) shape).curveTo(298.227, 767.36804, 297.985, 767.034,
                297.19, 767.034);
        ((GeneralPath) shape).curveTo(296.768, 767.034, 296.408, 767.081,
                296.247, 767.52997);
        ((GeneralPath) shape).lineTo(296.225, 767.52997);
        ((GeneralPath) shape).lineTo(296.225, 765.758);
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
        ((GeneralPath) shape).moveTo(303.043, 770.135);
        ((GeneralPath) shape).lineTo(303.771, 770.135);
        ((GeneralPath) shape).lineTo(303.771, 768.214);
        ((GeneralPath) shape).curveTo(303.771, 767.188, 303.357, 767.034,
                302.366, 767.034);
        ((GeneralPath) shape).curveTo(301.672, 767.034, 301.041, 767.034,
                301.041, 767.942);
        ((GeneralPath) shape).lineTo(301.76898, 767.942);
        ((GeneralPath) shape).curveTo(301.76898, 767.564, 302.03497, 767.536,
                302.36597, 767.536);
        ((GeneralPath) shape).curveTo(302.99997, 767.536, 303.04297, 767.703,
                303.04297, 768.176);
        ((GeneralPath) shape).lineTo(303.04297, 768.601);
        ((GeneralPath) shape).lineTo(303.01898, 768.601);
        ((GeneralPath) shape).curveTo(302.83798, 768.22003, 302.45697,
                768.22003, 302.07098, 768.22003);
        ((GeneralPath) shape).curveTo(301.327, 768.22003, 300.94998, 768.424,
                300.94998, 769.15704);
        ((GeneralPath) shape).curveTo(300.94998, 769.989, 301.391, 770.13605,
                302.07098, 770.13605);
        ((GeneralPath) shape).curveTo(302.439, 770.13605, 302.896, 770.13605,
                303.04398, 769.68506);
        ((GeneralPath) shape).lineTo(303.04398, 770.135);
        ((GeneralPath) shape).moveTo(302.342, 768.767);
        ((GeneralPath) shape).curveTo(302.717, 768.767, 303.043, 768.767,
                303.043, 769.15704);
        ((GeneralPath) shape).curveTo(303.043, 769.55707, 302.747, 769.58905,
                302.342, 769.58905);
        ((GeneralPath) shape).curveTo(301.851, 769.58905, 301.678, 769.55206,
                301.678, 769.15704);
        ((GeneralPath) shape).curveTo(301.678, 768.767, 301.957, 768.767,
                302.342, 768.767);
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
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(304.386, 767.034);
        ((GeneralPath) shape).lineTo(304.386, 770.135);
        ((GeneralPath) shape).lineTo(305.11398, 770.135);
        ((GeneralPath) shape).lineTo(305.11398, 768.243);
        ((GeneralPath) shape).curveTo(305.11398, 767.845, 305.249, 767.595,
                305.71298, 767.595);
        ((GeneralPath) shape).curveTo(306.085, 767.595, 306.115, 767.776,
                306.115, 768.08496);
        ((GeneralPath) shape).lineTo(306.115, 768.243);
        ((GeneralPath) shape).lineTo(306.844, 768.243);
        ((GeneralPath) shape).lineTo(306.844, 767.998);
        ((GeneralPath) shape).curveTo(306.844, 767.419, 306.676, 767.034,
                305.99, 767.034);
        ((GeneralPath) shape).curveTo(305.60898, 767.034, 305.272, 767.132,
                305.115, 767.463);
        ((GeneralPath) shape).lineTo(305.115, 767.034);
        ((GeneralPath) shape).lineTo(304.386, 767.034);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(307.909, 768.312);
        ((GeneralPath) shape).curveTo(307.909, 767.73804, 307.94998, 767.59503,
                308.583, 767.59503);
        ((GeneralPath) shape).curveTo(309.182, 767.59503, 309.27402, 767.645,
                309.27402, 768.312);
        ((GeneralPath) shape).lineTo(307.909, 768.312);
        ((GeneralPath) shape).moveTo(309.274, 769.163);
        ((GeneralPath) shape).curveTo(309.274, 769.588, 308.99, 769.588,
                308.58298, 769.588);
        ((GeneralPath) shape).curveTo(307.92697, 769.588, 307.90897, 769.39,
                307.90897, 768.767);
        ((GeneralPath) shape).lineTo(310.00195, 768.767);
        ((GeneralPath) shape).curveTo(310.00195, 767.40906, 309.83395,
                767.03503, 308.58295, 767.03503);
        ((GeneralPath) shape).curveTo(307.35394, 767.03503, 307.18195,
                767.52405, 307.18195, 768.642);
        ((GeneralPath) shape).curveTo(307.18195, 769.778, 307.41895, 770.135,
                308.58295, 770.135);
        ((GeneralPath) shape).curveTo(309.45193, 770.135, 310.00195, 770.089,
                310.00195, 769.163);
        ((GeneralPath) shape).lineTo(309.274, 769.163);
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
        ((GeneralPath) shape).moveTo(312.27, 767.582);
        ((GeneralPath) shape).lineTo(312.654, 767.582);
        ((GeneralPath) shape).lineTo(312.654, 769.17896);
        ((GeneralPath) shape).curveTo(312.654, 769.94995, 312.901, 770.13495,
                313.716, 770.13495);
        ((GeneralPath) shape).curveTo(314.513, 770.13495, 314.747, 769.85596,
                314.747, 768.93097);
        ((GeneralPath) shape).lineTo(314.11002, 768.93097);
        ((GeneralPath) shape).curveTo(314.11002, 769.256, 314.156, 769.58795,
                313.71503, 769.58795);
        ((GeneralPath) shape).curveTo(313.38202, 769.58795, 313.38202,
                769.45795, 313.38202, 769.173);
        ((GeneralPath) shape).lineTo(313.38202, 767.582);
        ((GeneralPath) shape).lineTo(314.592, 767.582);
        ((GeneralPath) shape).lineTo(314.592, 767.034);
        ((GeneralPath) shape).lineTo(313.38202, 767.034);
        ((GeneralPath) shape).lineTo(313.38202, 766.326);
        ((GeneralPath) shape).lineTo(312.653, 766.326);
        ((GeneralPath) shape).lineTo(312.653, 767.034);
        ((GeneralPath) shape).lineTo(312.269, 767.034);
        ((GeneralPath) shape).lineTo(312.269, 767.582);
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
        ((GeneralPath) shape).moveTo(315.044, 767.034);
        ((GeneralPath) shape).lineTo(315.044, 770.135);
        ((GeneralPath) shape).lineTo(315.772, 770.135);
        ((GeneralPath) shape).lineTo(315.772, 768.243);
        ((GeneralPath) shape).curveTo(315.772, 767.845, 315.908, 767.595,
                316.371, 767.595);
        ((GeneralPath) shape).curveTo(316.742, 767.595, 316.773, 767.776,
                316.773, 768.08496);
        ((GeneralPath) shape).lineTo(316.773, 768.243);
        ((GeneralPath) shape).lineTo(317.502, 768.243);
        ((GeneralPath) shape).lineTo(317.502, 767.998);
        ((GeneralPath) shape).curveTo(317.502, 767.419, 317.334, 767.034,
                316.648, 767.034);
        ((GeneralPath) shape).curveTo(316.26602, 767.034, 315.92902, 767.132,
                315.773, 767.463);
        ((GeneralPath) shape).lineTo(315.773, 767.034);
        ((GeneralPath) shape).lineTo(315.044, 767.034);
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
        ((GeneralPath) shape).moveTo(319.886, 770.135);
        ((GeneralPath) shape).lineTo(320.61398, 770.135);
        ((GeneralPath) shape).lineTo(320.61398, 768.214);
        ((GeneralPath) shape).curveTo(320.61398, 767.188, 320.19998, 767.034,
                319.21, 767.034);
        ((GeneralPath) shape).curveTo(318.516, 767.034, 317.88498, 767.034,
                317.88498, 767.942);
        ((GeneralPath) shape).lineTo(318.61298, 767.942);
        ((GeneralPath) shape).curveTo(318.61298, 767.564, 318.87897, 767.536,
                319.21097, 767.536);
        ((GeneralPath) shape).curveTo(319.84497, 767.536, 319.88797, 767.703,
                319.88797, 768.176);
        ((GeneralPath) shape).lineTo(319.88797, 768.601);
        ((GeneralPath) shape).lineTo(319.86398, 768.601);
        ((GeneralPath) shape).curveTo(319.68298, 768.22003, 319.30197,
                768.22003, 318.916, 768.22003);
        ((GeneralPath) shape).curveTo(318.172, 768.22003, 317.79498, 768.424,
                317.79498, 769.15704);
        ((GeneralPath) shape).curveTo(317.79498, 769.989, 318.236, 770.13605,
                318.916, 770.13605);
        ((GeneralPath) shape).curveTo(319.284, 770.13605, 319.74, 770.13605,
                319.88898, 769.68506);
        ((GeneralPath) shape).lineTo(319.88898, 770.135);
        ((GeneralPath) shape).moveTo(319.185, 768.767);
        ((GeneralPath) shape).curveTo(319.56, 768.767, 319.886, 768.767,
                319.886, 769.15704);
        ((GeneralPath) shape).curveTo(319.886, 769.55707, 319.59, 769.58905,
                319.185, 769.58905);
        ((GeneralPath) shape).curveTo(318.694, 769.58905, 318.521, 769.55206,
                318.521, 769.15704);
        ((GeneralPath) shape).curveTo(318.521, 768.767, 318.799, 768.767,
                319.185, 768.767);
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
        ((GeneralPath) shape).moveTo(323.321, 770.135);
        ((GeneralPath) shape).lineTo(324.049, 770.135);
        ((GeneralPath) shape).lineTo(324.049, 765.758);
        ((GeneralPath) shape).lineTo(323.321, 765.758);
        ((GeneralPath) shape).lineTo(323.321, 767.47797);
        ((GeneralPath) shape).lineTo(323.30402, 767.47797);
        ((GeneralPath) shape).curveTo(323.148, 767.105, 322.759, 767.035,
                322.389, 767.035);
        ((GeneralPath) shape).curveTo(321.349, 767.035, 321.228, 767.60895,
                321.228, 768.503);
        ((GeneralPath) shape).curveTo(321.228, 769.434, 321.228, 770.136,
                322.389, 770.136);
        ((GeneralPath) shape).curveTo(322.81, 770.136, 323.139, 770.04596,
                323.321, 769.656);
        ((GeneralPath) shape).lineTo(323.321, 770.135);
        ((GeneralPath) shape).moveTo(322.604, 767.595);
        ((GeneralPath) shape).curveTo(323.234, 767.595, 323.321, 767.85394,
                323.321, 768.50195);
        ((GeneralPath) shape).curveTo(323.321, 769.225, 323.321, 769.58795,
                322.604, 769.58795);
        ((GeneralPath) shape).curveTo(321.956, 769.58795, 321.956, 769.18896,
                321.956, 768.50195);
        ((GeneralPath) shape).curveTo(321.956, 767.738, 322.102, 767.595,
                322.604, 767.595);
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
        ((GeneralPath) shape).moveTo(325.416, 768.312);
        ((GeneralPath) shape).curveTo(325.416, 767.73804, 325.45697, 767.59503,
                326.09, 767.59503);
        ((GeneralPath) shape).curveTo(326.689, 767.59503, 326.78, 767.645,
                326.78, 768.312);
        ((GeneralPath) shape).lineTo(325.416, 768.312);
        ((GeneralPath) shape).moveTo(326.78, 769.163);
        ((GeneralPath) shape).curveTo(326.78, 769.588, 326.496, 769.588,
                326.09, 769.588);
        ((GeneralPath) shape).curveTo(325.43298, 769.588, 325.416, 769.39,
                325.416, 768.767);
        ((GeneralPath) shape).lineTo(327.50897, 768.767);
        ((GeneralPath) shape).curveTo(327.50897, 767.40906, 327.34097,
                767.03503, 326.08997, 767.03503);
        ((GeneralPath) shape).curveTo(324.86096, 767.03503, 324.68796,
                767.52405, 324.68796, 768.642);
        ((GeneralPath) shape).curveTo(324.68796, 769.778, 324.92496, 770.135,
                326.08997, 770.135);
        ((GeneralPath) shape).curveTo(326.95798, 770.135, 327.50897, 770.089,
                327.50897, 769.163);
        ((GeneralPath) shape).lineTo(326.78, 769.163);
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
        ((GeneralPath) shape).moveTo(328.104, 767.034);
        ((GeneralPath) shape).lineTo(328.104, 770.135);
        ((GeneralPath) shape).lineTo(328.832, 770.135);
        ((GeneralPath) shape).lineTo(328.832, 768.406);
        ((GeneralPath) shape).curveTo(328.832, 767.864, 328.956, 767.594,
                329.583, 767.594);
        ((GeneralPath) shape).curveTo(330.009, 767.594, 330.10602, 767.746,
                330.10602, 768.131);
        ((GeneralPath) shape).lineTo(330.10602, 770.134);
        ((GeneralPath) shape).lineTo(330.834, 770.134);
        ((GeneralPath) shape).lineTo(330.834, 768.40497);
        ((GeneralPath) shape).curveTo(330.834, 767.863, 330.94803, 767.59296,
                331.531, 767.59296);
        ((GeneralPath) shape).curveTo(331.927, 767.59296, 332.017, 767.74493,
                332.017, 768.12994);
        ((GeneralPath) shape).lineTo(332.017, 770.13293);
        ((GeneralPath) shape).lineTo(332.745, 770.13293);
        ((GeneralPath) shape).lineTo(332.745, 768.05994);
        ((GeneralPath) shape).curveTo(332.745, 767.3079, 332.491, 767.03296,
                331.721, 767.03296);
        ((GeneralPath) shape).curveTo(331.32, 767.03296, 330.90802, 767.14996,
                330.771, 767.57495);
        ((GeneralPath) shape).lineTo(330.749, 767.57495);
        ((GeneralPath) shape).curveTo(330.668, 767.13196, 330.229, 767.03296,
                329.849, 767.03296);
        ((GeneralPath) shape).curveTo(329.443, 767.03296, 329.006, 767.11993,
                328.833, 767.50696);
        ((GeneralPath) shape).lineTo(328.833, 767.03296);
        ((GeneralPath) shape).lineTo(328.104, 767.03296);
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
        ((GeneralPath) shape).moveTo(335.499, 770.135);
        ((GeneralPath) shape).lineTo(336.228, 770.135);
        ((GeneralPath) shape).lineTo(336.228, 768.214);
        ((GeneralPath) shape).curveTo(336.228, 767.188, 335.814, 767.034,
                334.823, 767.034);
        ((GeneralPath) shape).curveTo(334.13, 767.034, 333.498, 767.034,
                333.498, 767.942);
        ((GeneralPath) shape).lineTo(334.227, 767.942);
        ((GeneralPath) shape).curveTo(334.227, 767.564, 334.49298, 767.536,
                334.82397, 767.536);
        ((GeneralPath) shape).curveTo(335.45898, 767.536, 335.50098, 767.703,
                335.50098, 768.176);
        ((GeneralPath) shape).lineTo(335.50098, 768.601);
        ((GeneralPath) shape).lineTo(335.47797, 768.601);
        ((GeneralPath) shape).curveTo(335.29697, 768.22003, 334.91595,
                768.22003, 334.52997, 768.22003);
        ((GeneralPath) shape).curveTo(333.78598, 768.22003, 333.40897, 768.424,
                333.40897, 769.15704);
        ((GeneralPath) shape).curveTo(333.40897, 769.989, 333.84998, 770.13605,
                334.52997, 770.13605);
        ((GeneralPath) shape).curveTo(334.89798, 770.13605, 335.35397,
                770.13605, 335.50195, 769.68506);
        ((GeneralPath) shape).lineTo(335.50195, 770.135);
        ((GeneralPath) shape).moveTo(334.799, 768.767);
        ((GeneralPath) shape).curveTo(335.174, 768.767, 335.49902, 768.767,
                335.49902, 769.15704);
        ((GeneralPath) shape).curveTo(335.49902, 769.55707, 335.204, 769.58905,
                334.799, 769.58905);
        ((GeneralPath) shape).curveTo(334.308, 769.58905, 334.135, 769.55206,
                334.135, 769.15704);
        ((GeneralPath) shape).curveTo(334.135, 768.767, 334.413, 768.767,
                334.799, 768.767);
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
        ((GeneralPath) shape).moveTo(336.843, 767.034);
        ((GeneralPath) shape).lineTo(336.843, 770.135);
        ((GeneralPath) shape).lineTo(337.57098, 770.135);
        ((GeneralPath) shape).lineTo(337.57098, 768.243);
        ((GeneralPath) shape).curveTo(337.57098, 767.845, 337.706, 767.595,
                338.16998, 767.595);
        ((GeneralPath) shape).curveTo(338.541, 767.595, 338.572, 767.776,
                338.572, 768.08496);
        ((GeneralPath) shape).lineTo(338.572, 768.243);
        ((GeneralPath) shape).lineTo(339.301, 768.243);
        ((GeneralPath) shape).lineTo(339.301, 767.998);
        ((GeneralPath) shape).curveTo(339.301, 767.419, 339.133, 767.034,
                338.447, 767.034);
        ((GeneralPath) shape).curveTo(338.065, 767.034, 337.728, 767.132,
                337.572, 767.463);
        ((GeneralPath) shape).lineTo(337.572, 767.034);
        ((GeneralPath) shape).lineTo(336.843, 767.034);
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
        ((GeneralPath) shape).moveTo(340.41, 765.758);
        ((GeneralPath) shape).lineTo(339.683, 765.758);
        ((GeneralPath) shape).lineTo(339.683, 770.135);
        ((GeneralPath) shape).lineTo(340.41, 770.135);
        ((GeneralPath) shape).lineTo(340.41, 768.767);
        ((GeneralPath) shape).lineTo(340.591, 768.767);
        ((GeneralPath) shape).lineTo(341.61, 770.135);
        ((GeneralPath) shape).lineTo(342.494, 770.135);
        ((GeneralPath) shape).lineTo(341.19, 768.493);
        ((GeneralPath) shape).lineTo(342.274, 767.034);
        ((GeneralPath) shape).lineTo(341.446, 767.034);
        ((GeneralPath) shape).lineTo(340.591, 768.22);
        ((GeneralPath) shape).lineTo(340.41, 768.22);
        ((GeneralPath) shape).lineTo(340.41, 765.758);
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
        ((GeneralPath) shape).moveTo(345.375, 767.891);
        ((GeneralPath) shape).curveTo(345.375, 767.035, 344.661, 767.035,
                344.087, 767.035);
        ((GeneralPath) shape).curveTo(343.338, 767.035, 342.736, 767.035,
                342.736, 767.87994);
        ((GeneralPath) shape).curveTo(342.736, 768.634, 342.93298, 768.77893,
                344.024, 768.79694);
        ((GeneralPath) shape).curveTo(344.72, 768.8079, 344.73798, 768.9379,
                344.73798, 769.19495);
        ((GeneralPath) shape).curveTo(344.73798, 769.5889, 344.49496, 769.5889,
                344.07797, 769.5889);
        ((GeneralPath) shape).curveTo(343.56296, 769.5889, 343.46396,
                769.53894, 343.46396, 769.1109);
        ((GeneralPath) shape).lineTo(342.73596, 769.1109);
        ((GeneralPath) shape).curveTo(342.73596, 770.1349, 343.27695, 770.1349,
                344.08395, 770.1349);
        ((GeneralPath) shape).curveTo(344.83496, 770.1349, 345.46594, 770.0509,
                345.46594, 769.1949);
        ((GeneralPath) shape).curveTo(345.46594, 768.2379, 344.83295, 768.2909,
                344.14795, 768.2569);
        ((GeneralPath) shape).curveTo(343.54196, 768.2279, 343.46396, 768.2169,
                343.46396, 767.9249);
        ((GeneralPath) shape).curveTo(343.46396, 767.5369, 343.71997, 767.5369,
                344.09097, 767.5369);
        ((GeneralPath) shape).curveTo(344.46396, 767.5369, 344.64697, 767.5369,
                344.64697, 767.8909);
        ((GeneralPath) shape).lineTo(345.375, 767.8909);
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
        ((GeneralPath) shape).moveTo(349.452, 767.595);
        ((GeneralPath) shape).curveTo(350.08398, 767.595, 350.135, 767.77997,
                350.135, 768.60095);
        ((GeneralPath) shape).curveTo(350.135, 769.40894, 350.084, 769.58795,
                349.452, 769.58795);
        ((GeneralPath) shape).curveTo(348.82098, 769.58795, 348.76898,
                769.40796, 348.76898, 768.60095);
        ((GeneralPath) shape).curveTo(348.77, 767.779, 348.821, 767.595,
                349.452, 767.595);
        ((GeneralPath) shape).moveTo(349.452, 767.034);
        ((GeneralPath) shape).curveTo(348.201, 767.034, 348.042, 767.412,
                348.042, 768.595);
        ((GeneralPath) shape).curveTo(348.042, 769.76196, 348.201, 770.13495,
                349.452, 770.13495);
        ((GeneralPath) shape).curveTo(350.703, 770.13495, 350.862, 769.76196,
                350.862, 768.595);
        ((GeneralPath) shape).curveTo(350.862, 767.412, 350.703, 767.034,
                349.452, 767.034);
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
        ((GeneralPath) shape).moveTo(353.183, 767.034);
        ((GeneralPath) shape).lineTo(352.43802, 767.034);
        ((GeneralPath) shape).lineTo(352.43802, 766.861);
        ((GeneralPath) shape).curveTo(352.43802, 766.507, 352.43802, 766.317,
                352.93903, 766.317);
        ((GeneralPath) shape).lineTo(353.161, 766.317);
        ((GeneralPath) shape).lineTo(353.161, 765.757);
        ((GeneralPath) shape).lineTo(352.68402, 765.757);
        ((GeneralPath) shape).curveTo(351.88303, 765.757, 351.71002, 766.05804,
                351.71002, 766.773);
        ((GeneralPath) shape).lineTo(351.71002, 767.034);
        ((GeneralPath) shape).lineTo(351.27902, 767.034);
        ((GeneralPath) shape).lineTo(351.27902, 767.582);
        ((GeneralPath) shape).lineTo(351.71002, 767.582);
        ((GeneralPath) shape).lineTo(351.71002, 770.13495);
        ((GeneralPath) shape).lineTo(352.43802, 770.13495);
        ((GeneralPath) shape).lineTo(352.43802, 767.582);
        ((GeneralPath) shape).lineTo(353.183, 767.582);
        ((GeneralPath) shape).lineTo(353.183, 767.034);
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
        ((GeneralPath) shape).moveTo(357.346, 766.476);
        ((GeneralPath) shape).lineTo(358.626, 766.476);
        ((GeneralPath) shape).lineTo(358.626, 765.758);
        ((GeneralPath) shape).lineTo(355.245, 765.758);
        ((GeneralPath) shape).lineTo(355.245, 766.476);
        ((GeneralPath) shape).lineTo(356.526, 766.476);
        ((GeneralPath) shape).lineTo(356.526, 770.135);
        ((GeneralPath) shape).lineTo(357.346, 770.135);
        ((GeneralPath) shape).lineTo(357.346, 766.476);
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
        ((GeneralPath) shape).moveTo(359.769, 765.758);
        ((GeneralPath) shape).lineTo(359.04102, 765.758);
        ((GeneralPath) shape).lineTo(359.04102, 770.135);
        ((GeneralPath) shape).lineTo(359.769, 770.135);
        ((GeneralPath) shape).lineTo(359.769, 768.447);
        ((GeneralPath) shape).curveTo(359.769, 767.898, 359.855, 767.59503,
                360.483, 767.59503);
        ((GeneralPath) shape).curveTo(360.947, 767.59503, 361.044, 767.747,
                361.044, 768.19104);
        ((GeneralPath) shape).lineTo(361.044, 770.135);
        ((GeneralPath) shape).lineTo(361.772, 770.135);
        ((GeneralPath) shape).lineTo(361.772, 768.114);
        ((GeneralPath) shape).curveTo(361.772, 767.36804, 361.531, 767.034,
                360.736, 767.034);
        ((GeneralPath) shape).curveTo(360.314, 767.034, 359.95398, 767.081,
                359.794, 767.52997);
        ((GeneralPath) shape).lineTo(359.771, 767.52997);
        ((GeneralPath) shape).lineTo(359.771, 765.758);
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
        ((GeneralPath) shape).moveTo(363.166, 768.312);
        ((GeneralPath) shape).curveTo(363.166, 767.73804, 363.20697, 767.59503,
                363.84097, 767.59503);
        ((GeneralPath) shape).curveTo(364.43896, 767.59503, 364.53098, 767.645,
                364.53098, 768.312);
        ((GeneralPath) shape).lineTo(363.166, 768.312);
        ((GeneralPath) shape).moveTo(364.531, 769.163);
        ((GeneralPath) shape).curveTo(364.531, 769.588, 364.247, 769.588,
                363.841, 769.588);
        ((GeneralPath) shape).curveTo(363.184, 769.588, 363.16602, 769.39,
                363.16602, 768.767);
        ((GeneralPath) shape).lineTo(365.26, 768.767);
        ((GeneralPath) shape).curveTo(365.26, 767.40906, 365.092, 767.03503,
                363.841, 767.03503);
        ((GeneralPath) shape).curveTo(362.612, 767.03503, 362.439, 767.52405,
                362.439, 768.642);
        ((GeneralPath) shape).curveTo(362.439, 769.778, 362.676, 770.135,
                363.841, 770.135);
        ((GeneralPath) shape).curveTo(364.709, 770.135, 365.26, 770.089,
                365.26, 769.163);
        ((GeneralPath) shape).lineTo(364.531, 769.163);
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
        ((GeneralPath) shape).moveTo(369.64, 766.476);
        ((GeneralPath) shape).lineTo(370.921, 766.476);
        ((GeneralPath) shape).lineTo(370.921, 765.758);
        ((GeneralPath) shape).lineTo(367.539, 765.758);
        ((GeneralPath) shape).lineTo(367.539, 766.476);
        ((GeneralPath) shape).lineTo(368.82, 766.476);
        ((GeneralPath) shape).lineTo(368.82, 770.135);
        ((GeneralPath) shape).lineTo(369.64, 770.135);
        ((GeneralPath) shape).lineTo(369.64, 766.476);
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
        ((GeneralPath) shape).moveTo(372.159, 767.595);
        ((GeneralPath) shape).curveTo(372.791, 767.595, 372.842, 767.77997,
                372.842, 768.60095);
        ((GeneralPath) shape).curveTo(372.842, 769.40894, 372.79102, 769.58795,
                372.159, 769.58795);
        ((GeneralPath) shape).curveTo(371.52798, 769.58795, 371.47598,
                769.40796, 371.47598, 768.60095);
        ((GeneralPath) shape).curveTo(371.477, 767.779, 371.528, 767.595,
                372.159, 767.595);
        ((GeneralPath) shape).moveTo(372.159, 767.034);
        ((GeneralPath) shape).curveTo(370.908, 767.034, 370.749, 767.412,
                370.749, 768.595);
        ((GeneralPath) shape).curveTo(370.749, 769.76196, 370.908, 770.13495,
                372.159, 770.13495);
        ((GeneralPath) shape).curveTo(373.41, 770.13495, 373.569, 769.76196,
                373.569, 768.595);
        ((GeneralPath) shape).curveTo(373.569, 767.412, 373.41, 767.034,
                372.159, 767.034);
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
        ((GeneralPath) shape).moveTo(374.964, 767.034);
        ((GeneralPath) shape).lineTo(374.235, 767.034);
        ((GeneralPath) shape).lineTo(374.235, 771.502);
        ((GeneralPath) shape).lineTo(374.964, 771.502);
        ((GeneralPath) shape).lineTo(374.964, 769.701);
        ((GeneralPath) shape).lineTo(374.992, 769.701);
        ((GeneralPath) shape).curveTo(375.146, 770.066, 375.531, 770.135,
                375.897, 770.135);
        ((GeneralPath) shape).curveTo(376.935, 770.135, 377.056, 769.565,
                377.056, 768.68);
        ((GeneralPath) shape).curveTo(377.056, 767.75, 377.056, 767.034,
                375.897, 767.034);
        ((GeneralPath) shape).curveTo(375.476, 767.034, 375.145, 767.127,
                374.963, 767.526);
        ((GeneralPath) shape).lineTo(374.963, 767.034);
        ((GeneralPath) shape).moveTo(375.686, 769.588);
        ((GeneralPath) shape).curveTo(375.062, 769.588, 374.96402, 769.328,
                374.96402, 768.68);
        ((GeneralPath) shape).curveTo(374.96402, 767.958, 374.96402, 767.595,
                375.686, 767.595);
        ((GeneralPath) shape).curveTo(376.329, 767.595, 376.329, 767.993,
                376.329, 768.68);
        ((GeneralPath) shape).curveTo(376.328, 769.443, 376.185, 769.588,
                375.686, 769.588);
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
        ((GeneralPath) shape).moveTo(378.44, 767.034);
        ((GeneralPath) shape).lineTo(377.711, 767.034);
        ((GeneralPath) shape).lineTo(377.711, 771.502);
        ((GeneralPath) shape).lineTo(378.44, 771.502);
        ((GeneralPath) shape).lineTo(378.44, 769.701);
        ((GeneralPath) shape).lineTo(378.46802, 769.701);
        ((GeneralPath) shape).curveTo(378.622, 770.066, 379.00702, 770.135,
                379.37302, 770.135);
        ((GeneralPath) shape).curveTo(380.411, 770.135, 380.532, 769.565,
                380.532, 768.68);
        ((GeneralPath) shape).curveTo(380.532, 767.75, 380.532, 767.034,
                379.37302, 767.034);
        ((GeneralPath) shape).curveTo(378.95203, 767.034, 378.621, 767.127,
                378.43903, 767.526);
        ((GeneralPath) shape).lineTo(378.43903, 767.034);
        ((GeneralPath) shape).moveTo(379.162, 769.588);
        ((GeneralPath) shape).curveTo(378.538, 769.588, 378.44, 769.328,
                378.44, 768.68);
        ((GeneralPath) shape).curveTo(378.44, 767.958, 378.44, 767.595,
                379.162, 767.595);
        ((GeneralPath) shape).curveTo(379.805, 767.595, 379.805, 767.993,
                379.805, 768.68);
        ((GeneralPath) shape).curveTo(379.805, 769.443, 379.661, 769.588,
                379.162, 769.588);
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
        ((GeneralPath) shape).moveTo(383.737, 767.891);
        ((GeneralPath) shape).curveTo(383.737, 767.035, 383.023, 767.035,
                382.449, 767.035);
        ((GeneralPath) shape).curveTo(381.699, 767.035, 381.09702, 767.035,
                381.09702, 767.87994);
        ((GeneralPath) shape).curveTo(381.09702, 768.634, 381.295, 768.77893,
                382.385, 768.79694);
        ((GeneralPath) shape).curveTo(383.082, 768.8079, 383.099, 768.9379,
                383.099, 769.19495);
        ((GeneralPath) shape).curveTo(383.099, 769.5889, 382.856, 769.5889,
                382.44, 769.5889);
        ((GeneralPath) shape).curveTo(381.925, 769.5889, 381.826, 769.53894,
                381.826, 769.1109);
        ((GeneralPath) shape).lineTo(381.097, 769.1109);
        ((GeneralPath) shape).curveTo(381.097, 770.1349, 381.63898, 770.1349,
                382.44598, 770.1349);
        ((GeneralPath) shape).curveTo(383.19598, 770.1349, 383.82797, 770.0509,
                383.82797, 769.1949);
        ((GeneralPath) shape).curveTo(383.82797, 768.2379, 383.19498, 768.2909,
                382.50998, 768.2569);
        ((GeneralPath) shape).curveTo(381.90298, 768.2279, 381.826, 768.2169,
                381.826, 767.9249);
        ((GeneralPath) shape).curveTo(381.826, 767.5369, 382.082, 767.5369,
                382.453, 767.5369);
        ((GeneralPath) shape).curveTo(382.825, 767.5369, 383.009, 767.5369,
                383.009, 767.8909);
        ((GeneralPath) shape).lineTo(383.737, 767.8909);
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
        ((GeneralPath) shape).moveTo(389.953, 767.242);
        ((GeneralPath) shape).lineTo(389.953, 767.008);
        ((GeneralPath) shape).curveTo(389.892, 765.758, 389.247, 765.758,
                388.16, 765.758);
        ((GeneralPath) shape).curveTo(386.86002, 765.758, 386.312, 766.071,
                386.312, 767.455);
        ((GeneralPath) shape).lineTo(386.312, 768.437);
        ((GeneralPath) shape).curveTo(386.312, 769.693, 386.591, 770.193,
                388.16, 770.13403);
        ((GeneralPath) shape).curveTo(389.242, 770.12805, 389.953, 770.13403,
                389.953, 768.83905);
        ((GeneralPath) shape).lineTo(389.953, 768.55804);
        ((GeneralPath) shape).lineTo(389.134, 768.55804);
        ((GeneralPath) shape).lineTo(389.134, 768.79205);
        ((GeneralPath) shape).curveTo(389.134, 769.40405, 388.866, 769.40405,
                388.16, 769.40405);
        ((GeneralPath) shape).curveTo(387.241, 769.40405, 387.132, 769.24207,
                387.132, 768.40204);
        ((GeneralPath) shape).lineTo(387.132, 767.45404);
        ((GeneralPath) shape).curveTo(387.132, 766.61206, 387.28198, 766.47504,
                388.16, 766.47504);
        ((GeneralPath) shape).curveTo(388.926, 766.47504, 389.134, 766.51,
                389.134, 767.007);
        ((GeneralPath) shape).lineTo(389.134, 767.241);
        ((GeneralPath) shape).lineTo(389.953, 767.241);
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
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(391.874, 767.595);
        ((GeneralPath) shape).curveTo(392.505, 767.595, 392.556, 767.77997,
                392.556, 768.60095);
        ((GeneralPath) shape).curveTo(392.556, 769.40894, 392.505, 769.58795,
                391.874, 769.58795);
        ((GeneralPath) shape).curveTo(391.242, 769.58795, 391.19098, 769.40796,
                391.19098, 768.60095);
        ((GeneralPath) shape).curveTo(391.191, 767.779, 391.242, 767.595,
                391.874, 767.595);
        ((GeneralPath) shape).moveTo(391.874, 767.034);
        ((GeneralPath) shape).curveTo(390.62198, 767.034, 390.46298, 767.412,
                390.46298, 768.595);
        ((GeneralPath) shape).curveTo(390.46298, 769.76196, 390.62198,
                770.13495, 391.874, 770.13495);
        ((GeneralPath) shape).curveTo(393.125, 770.13495, 393.284, 769.76196,
                393.284, 768.595);
        ((GeneralPath) shape).curveTo(393.284, 767.412, 393.125, 767.034,
                391.874, 767.034);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(393.949, 767.034);
        ((GeneralPath) shape).lineTo(393.949, 770.135);
        ((GeneralPath) shape).lineTo(394.677, 770.135);
        ((GeneralPath) shape).lineTo(394.677, 768.406);
        ((GeneralPath) shape).curveTo(394.677, 767.864, 394.801, 767.594,
                395.428, 767.594);
        ((GeneralPath) shape).curveTo(395.854, 767.594, 395.95102, 767.746,
                395.95102, 768.131);
        ((GeneralPath) shape).lineTo(395.95102, 770.134);
        ((GeneralPath) shape).lineTo(396.67902, 770.134);
        ((GeneralPath) shape).lineTo(396.67902, 768.40497);
        ((GeneralPath) shape).curveTo(396.67902, 767.863, 396.79303, 767.59296,
                397.376, 767.59296);
        ((GeneralPath) shape).curveTo(397.772, 767.59296, 397.862, 767.74493,
                397.862, 768.12994);
        ((GeneralPath) shape).lineTo(397.862, 770.13293);
        ((GeneralPath) shape).lineTo(398.59, 770.13293);
        ((GeneralPath) shape).lineTo(398.59, 768.05994);
        ((GeneralPath) shape).curveTo(398.59, 767.3079, 398.336, 767.03296,
                397.566, 767.03296);
        ((GeneralPath) shape).curveTo(397.165, 767.03296, 396.75302, 767.14996,
                396.616, 767.57495);
        ((GeneralPath) shape).lineTo(396.594, 767.57495);
        ((GeneralPath) shape).curveTo(396.513, 767.13196, 396.074, 767.03296,
                395.694, 767.03296);
        ((GeneralPath) shape).curveTo(395.288, 767.03296, 394.851, 767.11993,
                394.678, 767.50696);
        ((GeneralPath) shape).lineTo(394.678, 767.03296);
        ((GeneralPath) shape).lineTo(393.949, 767.03296);
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
        ((GeneralPath) shape).moveTo(400.07, 767.034);
        ((GeneralPath) shape).lineTo(399.341, 767.034);
        ((GeneralPath) shape).lineTo(399.341, 771.502);
        ((GeneralPath) shape).lineTo(400.07, 771.502);
        ((GeneralPath) shape).lineTo(400.07, 769.701);
        ((GeneralPath) shape).lineTo(400.09802, 769.701);
        ((GeneralPath) shape).curveTo(400.25302, 770.066, 400.63702, 770.135,
                401.00302, 770.135);
        ((GeneralPath) shape).curveTo(402.04102, 770.135, 402.16202, 769.565,
                402.16202, 768.68);
        ((GeneralPath) shape).curveTo(402.16202, 767.75, 402.16202, 767.034,
                401.00302, 767.034);
        ((GeneralPath) shape).curveTo(400.58203, 767.034, 400.251, 767.127,
                400.06903, 767.526);
        ((GeneralPath) shape).lineTo(400.06903, 767.034);
        ((GeneralPath) shape).moveTo(400.793, 769.588);
        ((GeneralPath) shape).curveTo(400.168, 769.588, 400.07, 769.328,
                400.07, 768.68);
        ((GeneralPath) shape).curveTo(400.07, 767.958, 400.07, 767.595,
                400.793, 767.595);
        ((GeneralPath) shape).curveTo(401.436, 767.595, 401.436, 767.993,
                401.436, 768.68);
        ((GeneralPath) shape).curveTo(401.436, 769.443, 401.292, 769.588,
                400.793, 769.588);
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
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(404.821, 770.135);
        ((GeneralPath) shape).lineTo(405.549, 770.135);
        ((GeneralPath) shape).lineTo(405.549, 768.214);
        ((GeneralPath) shape).curveTo(405.549, 767.188, 405.135, 767.034,
                404.14502, 767.034);
        ((GeneralPath) shape).curveTo(403.45102, 767.034, 402.82, 767.034,
                402.82, 767.942);
        ((GeneralPath) shape).lineTo(403.548, 767.942);
        ((GeneralPath) shape).curveTo(403.548, 767.564, 403.814, 767.536,
                404.146, 767.536);
        ((GeneralPath) shape).curveTo(404.78, 767.536, 404.823, 767.703,
                404.823, 768.176);
        ((GeneralPath) shape).lineTo(404.823, 768.601);
        ((GeneralPath) shape).lineTo(404.799, 768.601);
        ((GeneralPath) shape).curveTo(404.618, 768.22003, 404.237, 768.22003,
                403.851, 768.22003);
        ((GeneralPath) shape).curveTo(403.10703, 768.22003, 402.73, 768.424,
                402.73, 769.15704);
        ((GeneralPath) shape).curveTo(402.73, 769.989, 403.17102, 770.13605,
                403.851, 770.13605);
        ((GeneralPath) shape).curveTo(404.21902, 770.13605, 404.67502,
                770.13605, 404.824, 769.68506);
        ((GeneralPath) shape).lineTo(404.824, 770.135);
        ((GeneralPath) shape).moveTo(404.12, 768.767);
        ((GeneralPath) shape).curveTo(404.495, 768.767, 404.82098, 768.767,
                404.82098, 769.15704);
        ((GeneralPath) shape).curveTo(404.82098, 769.55707, 404.525, 769.58905,
                404.12, 769.58905);
        ((GeneralPath) shape).curveTo(403.629, 769.58905, 403.456, 769.55206,
                403.456, 769.15704);
        ((GeneralPath) shape).curveTo(403.456, 768.767, 403.734, 768.767,
                404.12, 768.767);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(406.255, 767.034);
        ((GeneralPath) shape).lineTo(406.255, 770.135);
        ((GeneralPath) shape).lineTo(406.984, 770.135);
        ((GeneralPath) shape).lineTo(406.984, 768.447);
        ((GeneralPath) shape).curveTo(406.984, 767.898, 407.069, 767.59503,
                407.698, 767.59503);
        ((GeneralPath) shape).curveTo(408.161, 767.59503, 408.258, 767.747,
                408.258, 768.19104);
        ((GeneralPath) shape).lineTo(408.258, 770.135);
        ((GeneralPath) shape).lineTo(408.987, 770.135);
        ((GeneralPath) shape).lineTo(408.987, 768.114);
        ((GeneralPath) shape).curveTo(408.987, 767.36804, 408.745, 767.034,
                407.951, 767.034);
        ((GeneralPath) shape).curveTo(407.52798, 767.034, 407.16898, 767.08,
                407.008, 767.526);
        ((GeneralPath) shape).lineTo(406.986, 767.526);
        ((GeneralPath) shape).lineTo(406.986, 767.034);
        ((GeneralPath) shape).lineTo(406.255, 767.034);
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
        ((GeneralPath) shape).moveTo(412.292, 767.034);
        ((GeneralPath) shape).lineTo(411.538, 767.034);
        ((GeneralPath) shape).lineTo(410.938, 769.675);
        ((GeneralPath) shape).lineTo(410.926, 769.675);
        ((GeneralPath) shape).lineTo(410.14398, 767.034);
        ((GeneralPath) shape).lineTo(409.37897, 767.034);
        ((GeneralPath) shape).lineTo(410.39697, 770.135);
        ((GeneralPath) shape).lineTo(410.75497, 770.135);
        ((GeneralPath) shape).curveTo(410.68997, 770.499, 410.61996, 770.955,
                410.15497, 770.955);
        ((GeneralPath) shape).curveTo(410.10196, 770.955, 410.04898, 770.942,
                409.99597, 770.93604);
        ((GeneralPath) shape).lineTo(409.99597, 771.50104);
        ((GeneralPath) shape).curveTo(410.10196, 771.50104, 410.20798,
                771.50104, 410.31396, 771.50104);
        ((GeneralPath) shape).curveTo(411.17798, 771.50104, 411.31897,
                770.95306, 411.48996, 770.252);
        ((GeneralPath) shape).lineTo(412.292, 767.034);
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
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(412.348, 770.864);
        ((GeneralPath) shape).lineTo(412.496, 770.864);
        ((GeneralPath) shape).curveTo(413.135, 770.864, 413.135, 770.511,
                413.135, 770.03503);
        ((GeneralPath) shape).lineTo(413.135, 769.34705);
        ((GeneralPath) shape).lineTo(412.406, 769.34705);
        ((GeneralPath) shape).lineTo(412.406, 770.1351);
        ((GeneralPath) shape).lineTo(412.70502, 770.1351);
        ((GeneralPath) shape).curveTo(412.70502, 770.44006, 412.56802,
                770.4991, 412.34802, 770.4991);
        ((GeneralPath) shape).lineTo(412.34802, 770.864);
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
        // _0_99 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new Rectangle2D.Double(416.33599853515625, 765.7579956054688,
                0.8190000057220459, 4.376999855041504);
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
        ((GeneralPath) shape).moveTo(417.949, 767.034);
        ((GeneralPath) shape).lineTo(417.949, 770.135);
        ((GeneralPath) shape).lineTo(418.678, 770.135);
        ((GeneralPath) shape).lineTo(418.678, 768.447);
        ((GeneralPath) shape).curveTo(418.678, 767.898, 418.763, 767.59503,
                419.392, 767.59503);
        ((GeneralPath) shape).curveTo(419.855, 767.59503, 419.952, 767.747,
                419.952, 768.19104);
        ((GeneralPath) shape).lineTo(419.952, 770.135);
        ((GeneralPath) shape).lineTo(420.681, 770.135);
        ((GeneralPath) shape).lineTo(420.681, 768.114);
        ((GeneralPath) shape).curveTo(420.681, 767.36804, 420.439, 767.034,
                419.645, 767.034);
        ((GeneralPath) shape).curveTo(419.222, 767.034, 418.86298, 767.08,
                418.702, 767.526);
        ((GeneralPath) shape).lineTo(418.68, 767.526);
        ((GeneralPath) shape).lineTo(418.68, 767.034);
        ((GeneralPath) shape).lineTo(417.949, 767.034);
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
        ((GeneralPath) shape).moveTo(423.349, 768.952);
        ((GeneralPath) shape).curveTo(423.349, 769.48303, 423.291, 769.588,
                422.699, 769.588);
        ((GeneralPath) shape).curveTo(422.07602, 769.588, 422.07602, 769.352,
                422.07602, 768.594);
        ((GeneralPath) shape).curveTo(422.07602, 767.81396, 422.07602, 767.595,
                422.699, 767.595);
        ((GeneralPath) shape).curveTo(423.206, 767.595, 423.349, 767.67596,
                423.349, 768.138);
        ((GeneralPath) shape).lineTo(424.078, 768.138);
        ((GeneralPath) shape).curveTo(424.078, 767.174, 423.599, 767.034,
                422.699, 767.034);
        ((GeneralPath) shape).curveTo(421.514, 767.034, 421.34702, 767.527,
                421.34702, 768.595);
        ((GeneralPath) shape).curveTo(421.34702, 769.813, 421.58902, 770.13495,
                422.699, 770.13495);
        ((GeneralPath) shape).curveTo(423.72, 770.13495, 424.078, 769.93994,
                424.078, 768.95197);
        ((GeneralPath) shape).lineTo(423.349, 768.95197);
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
        shape = new Rectangle2D.Double(424.8349914550781, 769.3359985351562,
                0.7279999852180481, 0.7990000247955322);
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
        ((GeneralPath) shape).moveTo(431.257, 768.676);
        ((GeneralPath) shape).lineTo(429.741, 768.676);
        ((GeneralPath) shape).lineTo(430.491, 766.401);
        ((GeneralPath) shape).lineTo(430.50198, 766.401);
        ((GeneralPath) shape).lineTo(431.257, 768.676);
        ((GeneralPath) shape).moveTo(431.445, 769.314);
        ((GeneralPath) shape).lineTo(431.732, 770.13403);
        ((GeneralPath) shape).lineTo(432.586, 770.13403);
        ((GeneralPath) shape).lineTo(431.099, 765.757);
        ((GeneralPath) shape).lineTo(429.858, 765.757);
        ((GeneralPath) shape).lineTo(428.4, 770.13403);
        ((GeneralPath) shape).lineTo(429.27298, 770.13403);
        ((GeneralPath) shape).lineTo(429.54697, 769.314);
        ((GeneralPath) shape).lineTo(431.445, 769.314);
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
        shape = new Rectangle2D.Double(432.89898681640625, 765.7579956054688,
                0.7289999723434448, 4.376999855041504);
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
        shape = new Rectangle2D.Double(434.36700439453125, 765.7579956054688,
                0.7279999852180481, 4.376999855041504);
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
        ((GeneralPath) shape).moveTo(437.8, 767.034);
        ((GeneralPath) shape).lineTo(437.8, 770.135);
        ((GeneralPath) shape).lineTo(438.52798, 770.135);
        ((GeneralPath) shape).lineTo(438.52798, 768.243);
        ((GeneralPath) shape).curveTo(438.52798, 767.845, 438.663, 767.595,
                439.12698, 767.595);
        ((GeneralPath) shape).curveTo(439.498, 767.595, 439.529, 767.776,
                439.529, 768.08496);
        ((GeneralPath) shape).lineTo(439.529, 768.243);
        ((GeneralPath) shape).lineTo(440.257, 768.243);
        ((GeneralPath) shape).lineTo(440.257, 767.998);
        ((GeneralPath) shape).curveTo(440.257, 767.419, 440.09, 767.034,
                439.40298, 767.034);
        ((GeneralPath) shape).curveTo(439.02197, 767.034, 438.685, 767.132,
                438.529, 767.463);
        ((GeneralPath) shape).lineTo(438.529, 767.034);
        ((GeneralPath) shape).lineTo(437.8, 767.034);
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
        ((GeneralPath) shape).moveTo(441.367, 765.758);
        ((GeneralPath) shape).lineTo(440.639, 765.758);
        ((GeneralPath) shape).lineTo(440.639, 766.382);
        ((GeneralPath) shape).lineTo(441.367, 766.382);
        ((GeneralPath) shape).lineTo(441.367, 765.758);
        ((GeneralPath) shape).moveTo(441.367, 767.034);
        ((GeneralPath) shape).lineTo(440.639, 767.034);
        ((GeneralPath) shape).lineTo(440.639, 770.135);
        ((GeneralPath) shape).lineTo(441.367, 770.135);
        ((GeneralPath) shape).lineTo(441.367, 767.034);
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
        ((GeneralPath) shape).moveTo(444.016, 770.254);
        ((GeneralPath) shape).curveTo(444.016, 770.817, 443.88998, 770.955,
                443.29898, 770.955);
        ((GeneralPath) shape).curveTo(442.89597, 770.955, 442.74197, 770.887,
                442.74197, 770.435);
        ((GeneralPath) shape).lineTo(442.01297, 770.435);
        ((GeneralPath) shape).curveTo(441.96597, 771.371, 442.53598, 771.501,
                443.29797, 771.501);
        ((GeneralPath) shape).curveTo(444.39996, 771.501, 444.74298, 771.23,
                444.74298, 770.094);
        ((GeneralPath) shape).lineTo(444.74298, 767.033);
        ((GeneralPath) shape).lineTo(444.01398, 767.033);
        ((GeneralPath) shape).lineTo(444.01398, 767.525);
        ((GeneralPath) shape).curveTo(443.83298, 767.12604, 443.50497, 767.033,
                443.08698, 767.033);
        ((GeneralPath) shape).curveTo(441.921, 767.033, 441.921, 767.749,
                441.921, 768.679);
        ((GeneralPath) shape).curveTo(441.921, 769.564, 442.043, 770.13403,
                443.08698, 770.13403);
        ((GeneralPath) shape).curveTo(443.44797, 770.13403, 443.82697,
                770.06604, 444.01398, 769.67206);
        ((GeneralPath) shape).lineTo(444.01398, 770.254);
        ((GeneralPath) shape).moveTo(443.299, 767.595);
        ((GeneralPath) shape).curveTo(443.919, 767.595, 444.01602, 767.85596,
                444.01602, 768.50195);
        ((GeneralPath) shape).curveTo(444.01602, 769.225, 444.01602, 769.58795,
                443.29404, 769.58795);
        ((GeneralPath) shape).curveTo(442.65103, 769.58795, 442.65103,
                769.18896, 442.65103, 768.50195);
        ((GeneralPath) shape).curveTo(442.651, 767.738, 442.796, 767.595,
                443.299, 767.595);
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
        ((GeneralPath) shape).moveTo(446.207, 765.758);
        ((GeneralPath) shape).lineTo(445.478, 765.758);
        ((GeneralPath) shape).lineTo(445.478, 770.135);
        ((GeneralPath) shape).lineTo(446.207, 770.135);
        ((GeneralPath) shape).lineTo(446.207, 768.447);
        ((GeneralPath) shape).curveTo(446.207, 767.898, 446.292, 767.59503,
                446.921, 767.59503);
        ((GeneralPath) shape).curveTo(447.384, 767.59503, 447.481, 767.747,
                447.481, 768.19104);
        ((GeneralPath) shape).lineTo(447.481, 770.135);
        ((GeneralPath) shape).lineTo(448.21, 770.135);
        ((GeneralPath) shape).lineTo(448.21, 768.114);
        ((GeneralPath) shape).curveTo(448.21, 767.36804, 447.968, 767.034,
                447.17398, 767.034);
        ((GeneralPath) shape).curveTo(446.75098, 767.034, 446.39197, 767.081,
                446.231, 767.52997);
        ((GeneralPath) shape).lineTo(446.20898, 767.52997);
        ((GeneralPath) shape).lineTo(446.20898, 765.758);
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
        ((GeneralPath) shape).moveTo(448.583, 767.582);
        ((GeneralPath) shape).lineTo(448.96802, 767.582);
        ((GeneralPath) shape).lineTo(448.96802, 769.17896);
        ((GeneralPath) shape).curveTo(448.96802, 769.94995, 449.21503,
                770.13495, 450.02902, 770.13495);
        ((GeneralPath) shape).curveTo(450.82703, 770.13495, 451.06104,
                769.85596, 451.06104, 768.93097);
        ((GeneralPath) shape).lineTo(450.42404, 768.93097);
        ((GeneralPath) shape).curveTo(450.42404, 769.256, 450.46906, 769.58795,
                450.02805, 769.58795);
        ((GeneralPath) shape).curveTo(449.69504, 769.58795, 449.69504,
                769.45795, 449.69504, 769.173);
        ((GeneralPath) shape).lineTo(449.69504, 767.582);
        ((GeneralPath) shape).lineTo(450.90503, 767.582);
        ((GeneralPath) shape).lineTo(450.90503, 767.034);
        ((GeneralPath) shape).lineTo(449.69504, 767.034);
        ((GeneralPath) shape).lineTo(449.69504, 766.326);
        ((GeneralPath) shape).lineTo(448.96704, 766.326);
        ((GeneralPath) shape).lineTo(448.96704, 767.034);
        ((GeneralPath) shape).lineTo(448.58203, 767.034);
        ((GeneralPath) shape).lineTo(448.58203, 767.582);
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
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(453.996, 767.891);
        ((GeneralPath) shape).curveTo(453.996, 767.035, 453.282, 767.035,
                452.708, 767.035);
        ((GeneralPath) shape).curveTo(451.959, 767.035, 451.357, 767.035,
                451.357, 767.87994);
        ((GeneralPath) shape).curveTo(451.357, 768.634, 451.554, 768.77893,
                452.645, 768.79694);
        ((GeneralPath) shape).curveTo(453.34198, 768.8079, 453.35898, 768.9379,
                453.35898, 769.19495);
        ((GeneralPath) shape).curveTo(453.35898, 769.5889, 453.11597, 769.5889,
                452.69897, 769.5889);
        ((GeneralPath) shape).curveTo(452.18497, 769.5889, 452.08597,
                769.53894, 452.08597, 769.1109);
        ((GeneralPath) shape).lineTo(451.35696, 769.1109);
        ((GeneralPath) shape).curveTo(451.35696, 770.1349, 451.89896, 770.1349,
                452.70496, 770.1349);
        ((GeneralPath) shape).curveTo(453.45596, 770.1349, 454.08795, 770.0509,
                454.08795, 769.1949);
        ((GeneralPath) shape).curveTo(454.08795, 768.2379, 453.45496, 768.2909,
                452.76996, 768.2569);
        ((GeneralPath) shape).curveTo(452.16296, 768.2279, 452.08597, 768.2169,
                452.08597, 767.9249);
        ((GeneralPath) shape).curveTo(452.08597, 767.5369, 452.34198, 767.5369,
                452.71298, 767.5369);
        ((GeneralPath) shape).curveTo(453.085, 767.5369, 453.26898, 767.5369,
                453.26898, 767.8909);
        ((GeneralPath) shape).lineTo(453.996, 767.8909);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(456.663, 767.034);
        ((GeneralPath) shape).lineTo(456.663, 770.135);
        ((GeneralPath) shape).lineTo(457.392, 770.135);
        ((GeneralPath) shape).lineTo(457.392, 768.243);
        ((GeneralPath) shape).curveTo(457.392, 767.845, 457.527, 767.595,
                457.991, 767.595);
        ((GeneralPath) shape).curveTo(458.362, 767.595, 458.393, 767.776,
                458.393, 768.08496);
        ((GeneralPath) shape).lineTo(458.393, 768.243);
        ((GeneralPath) shape).lineTo(459.121, 768.243);
        ((GeneralPath) shape).lineTo(459.121, 767.998);
        ((GeneralPath) shape).curveTo(459.121, 767.419, 458.953, 767.034,
                458.267, 767.034);
        ((GeneralPath) shape).curveTo(457.886, 767.034, 457.549, 767.132,
                457.393, 767.463);
        ((GeneralPath) shape).lineTo(457.393, 767.034);
        ((GeneralPath) shape).lineTo(456.663, 767.034);
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
        ((GeneralPath) shape).moveTo(460.187, 768.312);
        ((GeneralPath) shape).curveTo(460.187, 767.73804, 460.228, 767.59503,
                460.86102, 767.59503);
        ((GeneralPath) shape).curveTo(461.46002, 767.59503, 461.55203, 767.645,
                461.55203, 768.312);
        ((GeneralPath) shape).lineTo(460.187, 768.312);
        ((GeneralPath) shape).moveTo(461.552, 769.163);
        ((GeneralPath) shape).curveTo(461.552, 769.588, 461.267, 769.588,
                460.861, 769.588);
        ((GeneralPath) shape).curveTo(460.20398, 769.588, 460.18698, 769.39,
                460.18698, 768.767);
        ((GeneralPath) shape).lineTo(462.27997, 768.767);
        ((GeneralPath) shape).curveTo(462.27997, 767.40906, 462.11197,
                767.03503, 460.86096, 767.03503);
        ((GeneralPath) shape).curveTo(459.63196, 767.03503, 459.45895,
                767.52405, 459.45895, 768.642);
        ((GeneralPath) shape).curveTo(459.45895, 769.778, 459.69696, 770.135,
                460.86096, 770.135);
        ((GeneralPath) shape).curveTo(461.72995, 770.135, 462.27997, 770.089,
                462.27997, 769.163);
        ((GeneralPath) shape).lineTo(461.552, 769.163);
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
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(465.423, 767.891);
        ((GeneralPath) shape).curveTo(465.423, 767.035, 464.709, 767.035,
                464.135, 767.035);
        ((GeneralPath) shape).curveTo(463.38602, 767.035, 462.784, 767.035,
                462.784, 767.87994);
        ((GeneralPath) shape).curveTo(462.784, 768.634, 462.981, 768.77893,
                464.072, 768.79694);
        ((GeneralPath) shape).curveTo(464.76898, 768.8079, 464.78598, 768.9379,
                464.78598, 769.19495);
        ((GeneralPath) shape).curveTo(464.78598, 769.5889, 464.54297, 769.5889,
                464.12598, 769.5889);
        ((GeneralPath) shape).curveTo(463.61096, 769.5889, 463.51196,
                769.53894, 463.51196, 769.1109);
        ((GeneralPath) shape).lineTo(462.78397, 769.1109);
        ((GeneralPath) shape).curveTo(462.78397, 770.1349, 463.32596, 770.1349,
                464.13196, 770.1349);
        ((GeneralPath) shape).curveTo(464.88297, 770.1349, 465.51395, 770.0509,
                465.51395, 769.1949);
        ((GeneralPath) shape).curveTo(465.51395, 768.2379, 464.88196, 768.2909,
                464.19696, 768.2569);
        ((GeneralPath) shape).curveTo(463.58997, 768.2279, 463.51196, 768.2169,
                463.51196, 767.9249);
        ((GeneralPath) shape).curveTo(463.51196, 767.5369, 463.76895, 767.5369,
                464.13898, 767.5369);
        ((GeneralPath) shape).curveTo(464.51196, 767.5369, 464.69598, 767.5369,
                464.69598, 767.8909);
        ((GeneralPath) shape).lineTo(465.423, 767.8909);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(466.762, 768.312);
        ((GeneralPath) shape).curveTo(466.762, 767.73804, 466.80298, 767.59503,
                467.43698, 767.59503);
        ((GeneralPath) shape).curveTo(468.03497, 767.59503, 468.12698, 767.645,
                468.12698, 768.312);
        ((GeneralPath) shape).lineTo(466.762, 768.312);
        ((GeneralPath) shape).moveTo(468.127, 769.163);
        ((GeneralPath) shape).curveTo(468.127, 769.588, 467.84302, 769.588,
                467.437, 769.588);
        ((GeneralPath) shape).curveTo(466.78, 769.588, 466.76202, 769.39,
                466.76202, 768.767);
        ((GeneralPath) shape).lineTo(468.85602, 768.767);
        ((GeneralPath) shape).curveTo(468.85602, 767.40906, 468.68802,
                767.03503, 467.437, 767.03503);
        ((GeneralPath) shape).curveTo(466.208, 767.03503, 466.035, 767.52405,
                466.035, 768.642);
        ((GeneralPath) shape).curveTo(466.035, 769.778, 466.272, 770.135,
                467.437, 770.135);
        ((GeneralPath) shape).curveTo(468.30502, 770.135, 468.85602, 770.089,
                468.85602, 769.163);
        ((GeneralPath) shape).lineTo(468.127, 769.163);
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
        ((GeneralPath) shape).moveTo(469.359, 767.034);
        ((GeneralPath) shape).lineTo(469.359, 770.135);
        ((GeneralPath) shape).lineTo(470.087, 770.135);
        ((GeneralPath) shape).lineTo(470.087, 768.243);
        ((GeneralPath) shape).curveTo(470.087, 767.845, 470.223, 767.595,
                470.686, 767.595);
        ((GeneralPath) shape).curveTo(471.058, 767.595, 471.088, 767.776,
                471.088, 768.08496);
        ((GeneralPath) shape).lineTo(471.088, 768.243);
        ((GeneralPath) shape).lineTo(471.81702, 768.243);
        ((GeneralPath) shape).lineTo(471.81702, 767.998);
        ((GeneralPath) shape).curveTo(471.81702, 767.419, 471.64902, 767.034,
                470.963, 767.034);
        ((GeneralPath) shape).curveTo(470.582, 767.034, 470.24503, 767.132,
                470.088, 767.463);
        ((GeneralPath) shape).lineTo(470.088, 767.034);
        ((GeneralPath) shape).lineTo(469.359, 767.034);
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
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(474.794, 767.034);
        ((GeneralPath) shape).lineTo(474.027, 767.034);
        ((GeneralPath) shape).lineTo(473.36, 769.633);
        ((GeneralPath) shape).lineTo(473.343, 769.633);
        ((GeneralPath) shape).lineTo(472.63, 767.034);
        ((GeneralPath) shape).lineTo(471.882, 767.034);
        ((GeneralPath) shape).lineTo(472.805, 770.135);
        ((GeneralPath) shape).lineTo(473.906, 770.135);
        ((GeneralPath) shape).lineTo(474.794, 767.034);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(475.748, 768.312);
        ((GeneralPath) shape).curveTo(475.748, 767.73804, 475.78897, 767.59503,
                476.422, 767.59503);
        ((GeneralPath) shape).curveTo(477.021, 767.59503, 477.113, 767.645,
                477.113, 768.312);
        ((GeneralPath) shape).lineTo(475.748, 768.312);
        ((GeneralPath) shape).moveTo(477.113, 769.163);
        ((GeneralPath) shape).curveTo(477.113, 769.588, 476.828, 769.588,
                476.422, 769.588);
        ((GeneralPath) shape).curveTo(475.76498, 769.588, 475.748, 769.39,
                475.748, 768.767);
        ((GeneralPath) shape).lineTo(477.84097, 768.767);
        ((GeneralPath) shape).curveTo(477.84097, 767.40906, 477.67297,
                767.03503, 476.42197, 767.03503);
        ((GeneralPath) shape).curveTo(475.19296, 767.03503, 475.01996,
                767.52405, 475.01996, 768.642);
        ((GeneralPath) shape).curveTo(475.01996, 769.778, 475.25797, 770.135,
                476.42197, 770.135);
        ((GeneralPath) shape).curveTo(477.29095, 770.135, 477.84097, 770.089,
                477.84097, 769.163);
        ((GeneralPath) shape).lineTo(477.113, 769.163);
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
        ((GeneralPath) shape).moveTo(480.438, 770.135);
        ((GeneralPath) shape).lineTo(481.167, 770.135);
        ((GeneralPath) shape).lineTo(481.167, 765.758);
        ((GeneralPath) shape).lineTo(480.438, 765.758);
        ((GeneralPath) shape).lineTo(480.438, 767.47797);
        ((GeneralPath) shape).lineTo(480.421, 767.47797);
        ((GeneralPath) shape).curveTo(480.26498, 767.105, 479.87598, 767.035,
                479.507, 767.035);
        ((GeneralPath) shape).curveTo(478.466, 767.035, 478.345, 767.60895,
                478.345, 768.503);
        ((GeneralPath) shape).curveTo(478.345, 769.434, 478.345, 770.136,
                479.507, 770.136);
        ((GeneralPath) shape).curveTo(479.927, 770.136, 480.25598, 770.04596,
                480.438, 769.656);
        ((GeneralPath) shape).lineTo(480.438, 770.135);
        ((GeneralPath) shape).moveTo(479.722, 767.595);
        ((GeneralPath) shape).curveTo(480.352, 767.595, 480.439, 767.85394,
                480.439, 768.50195);
        ((GeneralPath) shape).curveTo(480.439, 769.225, 480.439, 769.58795,
                479.722, 769.58795);
        ((GeneralPath) shape).curveTo(479.07498, 769.58795, 479.07498,
                769.18896, 479.07498, 768.50195);
        ((GeneralPath) shape).curveTo(479.074, 767.738, 479.219, 767.595,
                479.722, 767.595);
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
        shape = new Rectangle2D.Double(482.0780029296875, 769.3359985351562,
                0.7279999852180481, 0.7990000247955322);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(489.465, 767.242);
        ((GeneralPath) shape).lineTo(489.465, 767.008);
        ((GeneralPath) shape).curveTo(489.405, 765.758, 488.76, 765.758,
                487.672, 765.758);
        ((GeneralPath) shape).curveTo(486.372, 765.758, 485.825, 766.071,
                485.825, 767.455);
        ((GeneralPath) shape).lineTo(485.825, 768.437);
        ((GeneralPath) shape).curveTo(485.825, 769.693, 486.10303, 770.193,
                487.672, 770.13403);
        ((GeneralPath) shape).curveTo(488.754, 770.12805, 489.465, 770.13403,
                489.465, 768.83905);
        ((GeneralPath) shape).lineTo(489.465, 768.55804);
        ((GeneralPath) shape).lineTo(488.647, 768.55804);
        ((GeneralPath) shape).lineTo(488.647, 768.79205);
        ((GeneralPath) shape).curveTo(488.647, 769.40405, 488.379, 769.40405,
                487.672, 769.40405);
        ((GeneralPath) shape).curveTo(486.754, 769.40405, 486.645, 769.24207,
                486.645, 768.40204);
        ((GeneralPath) shape).lineTo(486.645, 767.45404);
        ((GeneralPath) shape).curveTo(486.645, 766.61206, 486.79498, 766.47504,
                487.672, 766.47504);
        ((GeneralPath) shape).curveTo(488.439, 766.47504, 488.647, 766.51,
                488.647, 767.007);
        ((GeneralPath) shape).lineTo(488.647, 767.241);
        ((GeneralPath) shape).lineTo(489.465, 767.241);
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
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(492.068, 770.135);
        ((GeneralPath) shape).lineTo(492.797, 770.135);
        ((GeneralPath) shape).lineTo(492.797, 768.214);
        ((GeneralPath) shape).curveTo(492.797, 767.188, 492.383, 767.034,
                491.392, 767.034);
        ((GeneralPath) shape).curveTo(490.699, 767.034, 490.067, 767.034,
                490.067, 767.942);
        ((GeneralPath) shape).lineTo(490.796, 767.942);
        ((GeneralPath) shape).curveTo(490.796, 767.564, 491.06198, 767.536,
                491.39297, 767.536);
        ((GeneralPath) shape).curveTo(492.02798, 767.536, 492.06998, 767.703,
                492.06998, 768.176);
        ((GeneralPath) shape).lineTo(492.06998, 768.601);
        ((GeneralPath) shape).lineTo(492.046, 768.601);
        ((GeneralPath) shape).curveTo(491.865, 768.22003, 491.48398, 768.22003,
                491.098, 768.22003);
        ((GeneralPath) shape).curveTo(490.35498, 768.22003, 489.978, 768.424,
                489.978, 769.15704);
        ((GeneralPath) shape).curveTo(489.978, 769.989, 490.418, 770.13605,
                491.098, 770.13605);
        ((GeneralPath) shape).curveTo(491.466, 770.13605, 491.923, 770.13605,
                492.07098, 769.68506);
        ((GeneralPath) shape).lineTo(492.07098, 770.135);
        ((GeneralPath) shape).moveTo(491.367, 768.767);
        ((GeneralPath) shape).curveTo(491.743, 768.767, 492.068, 768.767,
                492.068, 769.15704);
        ((GeneralPath) shape).curveTo(492.068, 769.55707, 491.772, 769.58905,
                491.367, 769.58905);
        ((GeneralPath) shape).curveTo(490.877, 769.58905, 490.703, 769.55206,
                490.703, 769.15704);
        ((GeneralPath) shape).curveTo(490.703, 768.767, 490.982, 768.767,
                491.367, 768.767);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(493.118, 767.582);
        ((GeneralPath) shape).lineTo(493.502, 767.582);
        ((GeneralPath) shape).lineTo(493.502, 769.17896);
        ((GeneralPath) shape).curveTo(493.502, 769.94995, 493.75, 770.13495,
                494.56403, 770.13495);
        ((GeneralPath) shape).curveTo(495.36102, 770.13495, 495.59604,
                769.85596, 495.59604, 768.93097);
        ((GeneralPath) shape).lineTo(494.95804, 768.93097);
        ((GeneralPath) shape).curveTo(494.95804, 769.256, 495.00403, 769.58795,
                494.56305, 769.58795);
        ((GeneralPath) shape).curveTo(494.23004, 769.58795, 494.23004,
                769.45795, 494.23004, 769.173);
        ((GeneralPath) shape).lineTo(494.23004, 767.582);
        ((GeneralPath) shape).lineTo(495.44003, 767.582);
        ((GeneralPath) shape).lineTo(495.44003, 767.034);
        ((GeneralPath) shape).lineTo(494.23004, 767.034);
        ((GeneralPath) shape).lineTo(494.23004, 766.326);
        ((GeneralPath) shape).lineTo(493.50104, 766.326);
        ((GeneralPath) shape).lineTo(493.50104, 767.034);
        ((GeneralPath) shape).lineTo(493.11703, 767.034);
        ((GeneralPath) shape).lineTo(493.11703, 767.582);
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
        paint2(g, clip_, defaultTransform_, alpha__0, clip__0,
                defaultTransform__0, alpha__0_124, clip__0_124,
                defaultTransform__0_124);

    }

    private static void paint2(Graphics2D g, Shape clip_,
            AffineTransform defaultTransform_, float alpha__0, Shape clip__0,
            AffineTransform defaultTransform__0, float alpha__0_124,
            Shape clip__0_124, AffineTransform defaultTransform__0_124) {
        Shape shape;
        Paint paint;
        float origAlpha;
        // _0_124 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(497.985, 770.135);
        ((GeneralPath) shape).lineTo(498.71298, 770.135);
        ((GeneralPath) shape).lineTo(498.71298, 768.214);
        ((GeneralPath) shape).curveTo(498.71298, 767.188, 498.3, 767.034,
                497.309, 767.034);
        ((GeneralPath) shape).curveTo(496.616, 767.034, 495.98398, 767.034,
                495.98398, 767.942);
        ((GeneralPath) shape).lineTo(496.71198, 767.942);
        ((GeneralPath) shape).curveTo(496.71198, 767.564, 496.97897, 767.536,
                497.30997, 767.536);
        ((GeneralPath) shape).curveTo(497.94498, 767.536, 497.98697, 767.703,
                497.98697, 768.176);
        ((GeneralPath) shape).lineTo(497.98697, 768.601);
        ((GeneralPath) shape).lineTo(497.96298, 768.601);
        ((GeneralPath) shape).curveTo(497.78198, 768.22003, 497.40097,
                768.22003, 497.01498, 768.22003);
        ((GeneralPath) shape).curveTo(496.27197, 768.22003, 495.895, 768.424,
                495.895, 769.15704);
        ((GeneralPath) shape).curveTo(495.895, 769.989, 496.335, 770.13605,
                497.01498, 770.13605);
        ((GeneralPath) shape).curveTo(497.383, 770.13605, 497.84, 770.13605,
                497.98798, 769.68506);
        ((GeneralPath) shape).lineTo(497.98798, 770.135);
        ((GeneralPath) shape).moveTo(497.284, 768.767);
        ((GeneralPath) shape).curveTo(497.66, 768.767, 497.985, 768.767,
                497.985, 769.15704);
        ((GeneralPath) shape).curveTo(497.985, 769.55707, 497.689, 769.58905,
                497.284, 769.58905);
        ((GeneralPath) shape).curveTo(496.794, 769.58905, 496.62, 769.55206,
                496.62, 769.15704);
        ((GeneralPath) shape).curveTo(496.62, 768.767, 496.899, 768.767,
                497.284, 768.767);
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
        shape = new Rectangle2D.Double(499.41900634765625, 765.7579956054688,
                0.7289999723434448, 4.376999855041504);
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
        ((GeneralPath) shape).moveTo(503.435, 767.034);
        ((GeneralPath) shape).lineTo(502.682, 767.034);
        ((GeneralPath) shape).lineTo(502.081, 769.675);
        ((GeneralPath) shape).lineTo(502.07, 769.675);
        ((GeneralPath) shape).lineTo(501.288, 767.034);
        ((GeneralPath) shape).lineTo(500.52298, 767.034);
        ((GeneralPath) shape).lineTo(501.541, 770.135);
        ((GeneralPath) shape).lineTo(501.899, 770.135);
        ((GeneralPath) shape).curveTo(501.83398, 770.499, 501.76398, 770.955,
                501.29898, 770.955);
        ((GeneralPath) shape).curveTo(501.24597, 770.955, 501.192, 770.942,
                501.13898, 770.93604);
        ((GeneralPath) shape).lineTo(501.13898, 771.50104);
        ((GeneralPath) shape).curveTo(501.24597, 771.50104, 501.35098,
                771.50104, 501.45798, 771.50104);
        ((GeneralPath) shape).curveTo(502.322, 771.50104, 502.46298, 770.95306,
                502.63397, 770.252);
        ((GeneralPath) shape).lineTo(503.435, 767.034);
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
        ((GeneralPath) shape).moveTo(506.265, 767.891);
        ((GeneralPath) shape).curveTo(506.265, 767.035, 505.55103, 767.035,
                504.97702, 767.035);
        ((GeneralPath) shape).curveTo(504.22803, 767.035, 503.626, 767.035,
                503.626, 767.87994);
        ((GeneralPath) shape).curveTo(503.626, 768.634, 503.823, 768.77893,
                504.914, 768.79694);
        ((GeneralPath) shape).curveTo(505.61002, 768.8079, 505.628, 768.9379,
                505.628, 769.19495);
        ((GeneralPath) shape).curveTo(505.628, 769.5889, 505.38498, 769.5889,
                504.968, 769.5889);
        ((GeneralPath) shape).curveTo(504.45297, 769.5889, 504.35397,
                769.53894, 504.35397, 769.1109);
        ((GeneralPath) shape).lineTo(503.62598, 769.1109);
        ((GeneralPath) shape).curveTo(503.62598, 770.1349, 504.16797, 770.1349,
                504.97397, 770.1349);
        ((GeneralPath) shape).curveTo(505.72498, 770.1349, 506.35596, 770.0509,
                506.35596, 769.1949);
        ((GeneralPath) shape).curveTo(506.35596, 768.2379, 505.72397, 768.2909,
                505.03897, 768.2569);
        ((GeneralPath) shape).curveTo(504.43198, 768.2279, 504.35397, 768.2169,
                504.35397, 767.9249);
        ((GeneralPath) shape).curveTo(504.35397, 767.5369, 504.61, 767.5369,
                504.981, 767.5369);
        ((GeneralPath) shape).curveTo(505.35397, 767.5369, 505.538, 767.5369,
                505.538, 767.8909);
        ((GeneralPath) shape).lineTo(506.265, 767.8909);
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
        ((GeneralPath) shape).moveTo(506.583, 767.582);
        ((GeneralPath) shape).lineTo(506.967, 767.582);
        ((GeneralPath) shape).lineTo(506.967, 769.17896);
        ((GeneralPath) shape).curveTo(506.967, 769.94995, 507.21402, 770.13495,
                508.028, 770.13495);
        ((GeneralPath) shape).curveTo(508.82602, 770.13495, 509.06003,
                769.85596, 509.06003, 768.93097);
        ((GeneralPath) shape).lineTo(508.42303, 768.93097);
        ((GeneralPath) shape).curveTo(508.42303, 769.256, 508.46805, 769.58795,
                508.02704, 769.58795);
        ((GeneralPath) shape).curveTo(507.69403, 769.58795, 507.69403,
                769.45795, 507.69403, 769.173);
        ((GeneralPath) shape).lineTo(507.69403, 767.582);
        ((GeneralPath) shape).lineTo(508.90402, 767.582);
        ((GeneralPath) shape).lineTo(508.90402, 767.034);
        ((GeneralPath) shape).lineTo(507.69403, 767.034);
        ((GeneralPath) shape).lineTo(507.69403, 766.326);
        ((GeneralPath) shape).lineTo(506.96603, 766.326);
        ((GeneralPath) shape).lineTo(506.96603, 767.034);
        ((GeneralPath) shape).lineTo(506.58203, 767.034);
        ((GeneralPath) shape).lineTo(506.58203, 767.582);
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
        ((GeneralPath) shape).moveTo(513.188, 767.764);
        ((GeneralPath) shape).lineTo(513.188, 768.40295);
        ((GeneralPath) shape).lineTo(514.325, 768.40295);
        ((GeneralPath) shape).lineTo(514.325, 768.56195);
        ((GeneralPath) shape).curveTo(514.325, 769.40594, 514.072, 769.40594,
                513.249, 769.40594);
        ((GeneralPath) shape).curveTo(512.286, 769.40594, 512.142, 769.3129,
                512.142, 768.397);
        ((GeneralPath) shape).lineTo(512.142, 767.41595);
        ((GeneralPath) shape).curveTo(512.142, 766.61896, 512.239, 766.4769,
                513.249, 766.4769);
        ((GeneralPath) shape).curveTo(514.00104, 766.4769, 514.325, 766.4769,
                514.325, 767.0789);
        ((GeneralPath) shape).lineTo(515.144, 767.0789);
        ((GeneralPath) shape).curveTo(515.144, 765.7069, 514.307, 765.75995,
                513.242, 765.75995);
        ((GeneralPath) shape).curveTo(511.892, 765.75995, 511.322, 766.00793,
                511.322, 767.41693);
        ((GeneralPath) shape).lineTo(511.322, 768.39795);
        ((GeneralPath) shape).curveTo(511.322, 769.88995, 511.848, 770.1359,
                513.24896, 770.1359);
        ((GeneralPath) shape).curveTo(514.816, 770.1359, 515.14496, 769.8659,
                515.14496, 768.5629);
        ((GeneralPath) shape).lineTo(515.14496, 767.76495);
        ((GeneralPath) shape).lineTo(513.188, 767.76495);
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
        ((GeneralPath) shape).moveTo(517.745, 770.135);
        ((GeneralPath) shape).lineTo(518.474, 770.135);
        ((GeneralPath) shape).lineTo(518.474, 768.214);
        ((GeneralPath) shape).curveTo(518.474, 767.188, 518.06, 767.034,
                517.069, 767.034);
        ((GeneralPath) shape).curveTo(516.376, 767.034, 515.74396, 767.034,
                515.74396, 767.942);
        ((GeneralPath) shape).lineTo(516.47296, 767.942);
        ((GeneralPath) shape).curveTo(516.47296, 767.564, 516.73895, 767.536,
                517.06995, 767.536);
        ((GeneralPath) shape).curveTo(517.70496, 767.536, 517.74695, 767.703,
                517.74695, 768.176);
        ((GeneralPath) shape).lineTo(517.74695, 768.601);
        ((GeneralPath) shape).lineTo(517.72394, 768.601);
        ((GeneralPath) shape).curveTo(517.5429, 768.22003, 517.16095,
                768.22003, 516.77496, 768.22003);
        ((GeneralPath) shape).curveTo(516.032, 768.22003, 515.65497, 768.424,
                515.65497, 769.15704);
        ((GeneralPath) shape).curveTo(515.65497, 769.989, 516.09595, 770.13605,
                516.77496, 770.13605);
        ((GeneralPath) shape).curveTo(517.144, 770.13605, 517.6, 770.13605,
                517.748, 769.68506);
        ((GeneralPath) shape).lineTo(517.748, 770.135);
        ((GeneralPath) shape).moveTo(517.045, 768.767);
        ((GeneralPath) shape).curveTo(517.42, 768.767, 517.745, 768.767,
                517.745, 769.15704);
        ((GeneralPath) shape).curveTo(517.745, 769.55707, 517.449, 769.58905,
                517.045, 769.58905);
        ((GeneralPath) shape).curveTo(516.55396, 769.58905, 516.381, 769.55206,
                516.381, 769.15704);
        ((GeneralPath) shape).curveTo(516.381, 768.767, 516.659, 768.767,
                517.045, 768.767);
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
        ((GeneralPath) shape).moveTo(519.18, 767.034);
        ((GeneralPath) shape).lineTo(519.18, 770.135);
        ((GeneralPath) shape).lineTo(519.908, 770.135);
        ((GeneralPath) shape).lineTo(519.908, 768.406);
        ((GeneralPath) shape).curveTo(519.908, 767.864, 520.03204, 767.594,
                520.659, 767.594);
        ((GeneralPath) shape).curveTo(521.084, 767.594, 521.182, 767.746,
                521.182, 768.131);
        ((GeneralPath) shape).lineTo(521.182, 770.134);
        ((GeneralPath) shape).lineTo(521.91003, 770.134);
        ((GeneralPath) shape).lineTo(521.91003, 768.40497);
        ((GeneralPath) shape).curveTo(521.91003, 767.863, 522.02405, 767.59296,
                522.60706, 767.59296);
        ((GeneralPath) shape).curveTo(523.0021, 767.59296, 523.09204,
                767.74493, 523.09204, 768.12994);
        ((GeneralPath) shape).lineTo(523.09204, 770.13293);
        ((GeneralPath) shape).lineTo(523.82104, 770.13293);
        ((GeneralPath) shape).lineTo(523.82104, 768.05994);
        ((GeneralPath) shape).curveTo(523.82104, 767.3079, 523.56604,
                767.03296, 522.796, 767.03296);
        ((GeneralPath) shape).curveTo(522.396, 767.03296, 521.984, 767.14996,
                521.84705, 767.57495);
        ((GeneralPath) shape).lineTo(521.82404, 767.57495);
        ((GeneralPath) shape).curveTo(521.74304, 767.13196, 521.30505,
                767.03296, 520.924, 767.03296);
        ((GeneralPath) shape).curveTo(520.518, 767.03296, 520.081, 767.11993,
                519.909, 767.50696);
        ((GeneralPath) shape).lineTo(519.909, 767.03296);
        ((GeneralPath) shape).lineTo(519.18, 767.03296);
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
        ((GeneralPath) shape).moveTo(525.209, 768.312);
        ((GeneralPath) shape).curveTo(525.209, 767.73804, 525.25, 767.59503,
                525.883, 767.59503);
        ((GeneralPath) shape).curveTo(526.482, 767.59503, 526.574, 767.645,
                526.574, 768.312);
        ((GeneralPath) shape).lineTo(525.209, 768.312);
        ((GeneralPath) shape).moveTo(526.574, 769.163);
        ((GeneralPath) shape).curveTo(526.574, 769.588, 526.29, 769.588,
                525.883, 769.588);
        ((GeneralPath) shape).curveTo(525.227, 769.588, 525.209, 769.39,
                525.209, 768.767);
        ((GeneralPath) shape).lineTo(527.302, 768.767);
        ((GeneralPath) shape).curveTo(527.302, 767.40906, 527.135, 767.03503,
                525.883, 767.03503);
        ((GeneralPath) shape).curveTo(524.654, 767.03503, 524.482, 767.52405,
                524.482, 768.642);
        ((GeneralPath) shape).curveTo(524.482, 769.778, 524.719, 770.135,
                525.883, 770.135);
        ((GeneralPath) shape).curveTo(526.752, 770.135, 527.302, 770.089,
                527.302, 769.163);
        ((GeneralPath) shape).lineTo(526.574, 769.163);
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
        ((GeneralPath) shape).moveTo(530.772, 765.758);
        ((GeneralPath) shape).lineTo(529.953, 765.758);
        ((GeneralPath) shape).lineTo(529.953, 770.135);
        ((GeneralPath) shape).lineTo(532.812, 770.135);
        ((GeneralPath) shape).lineTo(532.812, 769.417);
        ((GeneralPath) shape).lineTo(530.772, 769.417);
        ((GeneralPath) shape).lineTo(530.772, 765.758);
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
        ((GeneralPath) shape).moveTo(535.252, 770.135);
        ((GeneralPath) shape).lineTo(535.98004, 770.135);
        ((GeneralPath) shape).lineTo(535.98004, 768.214);
        ((GeneralPath) shape).curveTo(535.98004, 767.188, 535.56604, 767.034,
                534.57605, 767.034);
        ((GeneralPath) shape).curveTo(533.8821, 767.034, 533.25104, 767.034,
                533.25104, 767.942);
        ((GeneralPath) shape).lineTo(533.97906, 767.942);
        ((GeneralPath) shape).curveTo(533.97906, 767.564, 534.24506, 767.536,
                534.5771, 767.536);
        ((GeneralPath) shape).curveTo(535.21106, 767.536, 535.2541, 767.703,
                535.2541, 768.176);
        ((GeneralPath) shape).lineTo(535.2541, 768.601);
        ((GeneralPath) shape).lineTo(535.2301, 768.601);
        ((GeneralPath) shape).curveTo(535.0491, 768.22003, 534.6681, 768.22003,
                534.2821, 768.22003);
        ((GeneralPath) shape).curveTo(533.5381, 768.22003, 533.16113, 768.424,
                533.16113, 769.15704);
        ((GeneralPath) shape).curveTo(533.16113, 769.989, 533.6021, 770.13605,
                534.2821, 770.13605);
        ((GeneralPath) shape).curveTo(534.6501, 770.13605, 535.1061, 770.13605,
                535.2551, 769.68506);
        ((GeneralPath) shape).lineTo(535.2551, 770.135);
        ((GeneralPath) shape).moveTo(534.551, 768.767);
        ((GeneralPath) shape).curveTo(534.926, 768.767, 535.252, 768.767,
                535.252, 769.15704);
        ((GeneralPath) shape).curveTo(535.252, 769.55707, 534.956, 769.58905,
                534.551, 769.58905);
        ((GeneralPath) shape).curveTo(534.06, 769.58905, 533.887, 769.55206,
                533.887, 769.15704);
        ((GeneralPath) shape).curveTo(533.887, 768.767, 534.165, 768.767,
                534.551, 768.767);
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
        ((GeneralPath) shape).moveTo(536.686, 770.135);
        ((GeneralPath) shape).lineTo(537.415, 770.135);
        ((GeneralPath) shape).lineTo(537.415, 769.655);
        ((GeneralPath) shape).curveTo(537.597, 770.046, 537.928, 770.135,
                538.349, 770.135);
        ((GeneralPath) shape).curveTo(539.508, 770.135, 539.508, 769.433,
                539.508, 768.502);
        ((GeneralPath) shape).curveTo(539.508, 767.60803, 539.387, 767.034,
                538.349, 767.034);
        ((GeneralPath) shape).curveTo(537.982, 767.034, 537.598, 767.104,
                537.444, 767.477);
        ((GeneralPath) shape).lineTo(537.41595, 767.477);
        ((GeneralPath) shape).lineTo(537.41595, 765.757);
        ((GeneralPath) shape).lineTo(536.68695, 765.757);
        ((GeneralPath) shape).lineTo(536.68695, 770.135);
        ((GeneralPath) shape).moveTo(538.136, 767.595);
        ((GeneralPath) shape).curveTo(538.635, 767.595, 538.779, 767.73895,
                538.779, 768.50195);
        ((GeneralPath) shape).curveTo(538.779, 769.18896, 538.779, 769.58795,
                538.136, 769.58795);
        ((GeneralPath) shape).curveTo(537.414, 769.58795, 537.414, 769.225,
                537.414, 768.50195);
        ((GeneralPath) shape).curveTo(537.414, 767.854, 537.512, 767.595,
                538.136, 767.595);
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
        ((GeneralPath) shape).moveTo(542.699, 767.891);
        ((GeneralPath) shape).curveTo(542.699, 767.035, 541.985, 767.035,
                541.41095, 767.035);
        ((GeneralPath) shape).curveTo(540.66095, 767.035, 540.05994, 767.035,
                540.05994, 767.87994);
        ((GeneralPath) shape).curveTo(540.05994, 768.634, 540.25696, 768.77893,
                541.3469, 768.79694);
        ((GeneralPath) shape).curveTo(542.04395, 768.8079, 542.06195, 768.9379,
                542.06195, 769.19495);
        ((GeneralPath) shape).curveTo(542.06195, 769.5889, 541.81793, 769.5889,
                541.402, 769.5889);
        ((GeneralPath) shape).curveTo(540.88696, 769.5889, 540.78796,
                769.53894, 540.78796, 769.1109);
        ((GeneralPath) shape).lineTo(540.05994, 769.1109);
        ((GeneralPath) shape).curveTo(540.05994, 770.1349, 540.60095, 770.1349,
                541.40796, 770.1349);
        ((GeneralPath) shape).curveTo(542.15796, 770.1349, 542.79, 770.0509,
                542.79, 769.1949);
        ((GeneralPath) shape).curveTo(542.79, 768.2379, 542.157, 768.2909,
                541.472, 768.2569);
        ((GeneralPath) shape).curveTo(540.86597, 768.2279, 540.78796, 768.2169,
                540.78796, 767.9249);
        ((GeneralPath) shape).curveTo(540.78796, 767.5369, 541.04395, 767.5369,
                541.415, 767.5369);
        ((GeneralPath) shape).curveTo(541.78796, 767.5369, 541.971, 767.5369,
                541.971, 767.8909);
        ((GeneralPath) shape).lineTo(542.699, 767.8909);
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
        ((GeneralPath) shape).moveTo(547.458, 770.135);
        ((GeneralPath) shape).lineTo(548.187, 770.135);
        ((GeneralPath) shape).lineTo(548.187, 768.214);
        ((GeneralPath) shape).curveTo(548.187, 767.188, 547.773, 767.034,
                546.782, 767.034);
        ((GeneralPath) shape).curveTo(546.089, 767.034, 545.457, 767.034,
                545.457, 767.942);
        ((GeneralPath) shape).lineTo(546.186, 767.942);
        ((GeneralPath) shape).curveTo(546.186, 767.564, 546.45197, 767.536,
                546.78296, 767.536);
        ((GeneralPath) shape).curveTo(547.41797, 767.536, 547.45996, 767.703,
                547.45996, 768.176);
        ((GeneralPath) shape).lineTo(547.45996, 768.601);
        ((GeneralPath) shape).lineTo(547.43695, 768.601);
        ((GeneralPath) shape).curveTo(547.2559, 768.22003, 546.87396,
                768.22003, 546.488, 768.22003);
        ((GeneralPath) shape).curveTo(545.745, 768.22003, 545.368, 768.424,
                545.368, 769.15704);
        ((GeneralPath) shape).curveTo(545.368, 769.989, 545.80896, 770.13605,
                546.488, 770.13605);
        ((GeneralPath) shape).curveTo(546.85596, 770.13605, 547.313, 770.13605,
                547.461, 769.68506);
        ((GeneralPath) shape).lineTo(547.461, 770.135);
        ((GeneralPath) shape).moveTo(546.758, 768.767);
        ((GeneralPath) shape).curveTo(547.133, 768.767, 547.458, 768.767,
                547.458, 769.15704);
        ((GeneralPath) shape).curveTo(547.458, 769.55707, 547.162, 769.58905,
                546.758, 769.58905);
        ((GeneralPath) shape).curveTo(546.26697, 769.58905, 546.094, 769.55206,
                546.094, 769.15704);
        ((GeneralPath) shape).curveTo(546.094, 768.767, 546.372, 768.767,
                546.758, 768.767);
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
        ((GeneralPath) shape).moveTo(548.893, 767.034);
        ((GeneralPath) shape).lineTo(548.893, 770.135);
        ((GeneralPath) shape).lineTo(549.622, 770.135);
        ((GeneralPath) shape).lineTo(549.622, 768.447);
        ((GeneralPath) shape).curveTo(549.622, 767.898, 549.70703, 767.59503,
                550.336, 767.59503);
        ((GeneralPath) shape).curveTo(550.799, 767.59503, 550.896, 767.747,
                550.896, 768.19104);
        ((GeneralPath) shape).lineTo(550.896, 770.135);
        ((GeneralPath) shape).lineTo(551.625, 770.135);
        ((GeneralPath) shape).lineTo(551.625, 768.114);
        ((GeneralPath) shape).curveTo(551.625, 767.36804, 551.383, 767.034,
                550.588, 767.034);
        ((GeneralPath) shape).curveTo(550.166, 767.034, 549.806, 767.08,
                549.646, 767.526);
        ((GeneralPath) shape).lineTo(549.624, 767.526);
        ((GeneralPath) shape).lineTo(549.624, 767.034);
        ((GeneralPath) shape).lineTo(548.893, 767.034);
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
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(554.383, 770.135);
        ((GeneralPath) shape).lineTo(555.112, 770.135);
        ((GeneralPath) shape).lineTo(555.112, 765.758);
        ((GeneralPath) shape).lineTo(554.383, 765.758);
        ((GeneralPath) shape).lineTo(554.383, 767.47797);
        ((GeneralPath) shape).lineTo(554.36597, 767.47797);
        ((GeneralPath) shape).curveTo(554.20996, 767.105, 553.821, 767.035,
                553.451, 767.035);
        ((GeneralPath) shape).curveTo(552.411, 767.035, 552.29, 767.60895,
                552.29, 768.503);
        ((GeneralPath) shape).curveTo(552.29, 769.434, 552.29, 770.136,
                553.451, 770.136);
        ((GeneralPath) shape).curveTo(553.873, 770.136, 554.201, 770.04596,
                554.383, 769.656);
        ((GeneralPath) shape).lineTo(554.383, 770.135);
        ((GeneralPath) shape).moveTo(553.666, 767.595);
        ((GeneralPath) shape).curveTo(554.297, 767.595, 554.383, 767.85394,
                554.383, 768.50195);
        ((GeneralPath) shape).curveTo(554.383, 769.225, 554.383, 769.58795,
                553.666, 769.58795);
        ((GeneralPath) shape).curveTo(553.018, 769.58795, 553.018, 769.18896,
                553.018, 768.50195);
        ((GeneralPath) shape).curveTo(553.018, 767.738, 553.163, 767.595,
                553.666, 767.595);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(557.513, 767.582);
        ((GeneralPath) shape).lineTo(557.897, 767.582);
        ((GeneralPath) shape).lineTo(557.897, 769.17896);
        ((GeneralPath) shape).curveTo(557.897, 769.94995, 558.144, 770.13495,
                558.95795, 770.13495);
        ((GeneralPath) shape).curveTo(559.7559, 770.13495, 559.9899, 769.85596,
                559.9899, 768.93097);
        ((GeneralPath) shape).lineTo(559.3529, 768.93097);
        ((GeneralPath) shape).curveTo(559.3529, 769.256, 559.3979, 769.58795,
                558.9569, 769.58795);
        ((GeneralPath) shape).curveTo(558.6239, 769.58795, 558.6239, 769.45795,
                558.6239, 769.173);
        ((GeneralPath) shape).lineTo(558.6239, 767.582);
        ((GeneralPath) shape).lineTo(559.8339, 767.582);
        ((GeneralPath) shape).lineTo(559.8339, 767.034);
        ((GeneralPath) shape).lineTo(558.6239, 767.034);
        ((GeneralPath) shape).lineTo(558.6239, 766.326);
        ((GeneralPath) shape).lineTo(557.8959, 766.326);
        ((GeneralPath) shape).lineTo(557.8959, 767.034);
        ((GeneralPath) shape).lineTo(557.5119, 767.034);
        ((GeneralPath) shape).lineTo(557.5119, 767.582);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(561.104, 765.758);
        ((GeneralPath) shape).lineTo(560.376, 765.758);
        ((GeneralPath) shape).lineTo(560.376, 770.135);
        ((GeneralPath) shape).lineTo(561.104, 770.135);
        ((GeneralPath) shape).lineTo(561.104, 768.447);
        ((GeneralPath) shape).curveTo(561.104, 767.898, 561.19, 767.59503,
                561.818, 767.59503);
        ((GeneralPath) shape).curveTo(562.282, 767.59503, 562.37897, 767.747,
                562.37897, 768.19104);
        ((GeneralPath) shape).lineTo(562.37897, 770.135);
        ((GeneralPath) shape).lineTo(563.107, 770.135);
        ((GeneralPath) shape).lineTo(563.107, 768.114);
        ((GeneralPath) shape).curveTo(563.107, 767.36804, 562.86597, 767.034,
                562.071, 767.034);
        ((GeneralPath) shape).curveTo(561.649, 767.034, 561.289, 767.081,
                561.12897, 767.52997);
        ((GeneralPath) shape).lineTo(561.10596, 767.52997);
        ((GeneralPath) shape).lineTo(561.10596, 765.758);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(564.503, 768.312);
        ((GeneralPath) shape).curveTo(564.503, 767.73804, 564.544, 767.59503,
                565.177, 767.59503);
        ((GeneralPath) shape).curveTo(565.776, 767.59503, 565.868, 767.645,
                565.868, 768.312);
        ((GeneralPath) shape).lineTo(564.503, 768.312);
        ((GeneralPath) shape).moveTo(565.868, 769.163);
        ((GeneralPath) shape).curveTo(565.868, 769.588, 565.583, 769.588,
                565.177, 769.588);
        ((GeneralPath) shape).curveTo(564.52, 769.588, 564.503, 769.39,
                564.503, 768.767);
        ((GeneralPath) shape).lineTo(566.596, 768.767);
        ((GeneralPath) shape).curveTo(566.596, 767.40906, 566.428, 767.03503,
                565.177, 767.03503);
        ((GeneralPath) shape).curveTo(563.948, 767.03503, 563.775, 767.52405,
                563.775, 768.642);
        ((GeneralPath) shape).curveTo(563.775, 769.778, 564.013, 770.135,
                565.177, 770.135);
        ((GeneralPath) shape).curveTo(566.046, 770.135, 566.596, 770.089,
                566.596, 769.163);
        ((GeneralPath) shape).lineTo(565.868, 769.163);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(141.907, 774.246);
        ((GeneralPath) shape).lineTo(141.907, 774.011);
        ((GeneralPath) shape).curveTo(141.847, 772.761, 141.201, 772.761,
                140.114, 772.761);
        ((GeneralPath) shape).curveTo(138.814, 772.761, 138.267, 773.074,
                138.267, 774.458);
        ((GeneralPath) shape).lineTo(138.267, 775.441);
        ((GeneralPath) shape).curveTo(138.267, 776.696, 138.546, 777.196,
                140.114, 777.13696);
        ((GeneralPath) shape).curveTo(141.196, 777.131, 141.907, 777.13696,
                141.907, 775.842);
        ((GeneralPath) shape).lineTo(141.907, 775.561);
        ((GeneralPath) shape).lineTo(141.088, 775.561);
        ((GeneralPath) shape).lineTo(141.088, 775.795);
        ((GeneralPath) shape).curveTo(141.088, 776.407, 140.821, 776.407,
                140.114, 776.407);
        ((GeneralPath) shape).curveTo(139.196, 776.407, 139.086, 776.245,
                139.086, 775.406);
        ((GeneralPath) shape).lineTo(139.086, 774.45703);
        ((GeneralPath) shape).curveTo(139.086, 773.61505, 139.237, 773.478,
                140.114, 773.478);
        ((GeneralPath) shape).curveTo(140.88, 773.478, 141.088, 773.512,
                141.088, 774.00903);
        ((GeneralPath) shape).lineTo(141.088, 774.244);
        ((GeneralPath) shape).lineTo(141.907, 774.244);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(144.504, 777.138);
        ((GeneralPath) shape).lineTo(145.232, 777.138);
        ((GeneralPath) shape).lineTo(145.232, 775.218);
        ((GeneralPath) shape).curveTo(145.232, 774.19104, 144.818, 774.038,
                143.827, 774.038);
        ((GeneralPath) shape).curveTo(143.133, 774.038, 142.502, 774.038,
                142.502, 774.945);
        ((GeneralPath) shape).lineTo(143.23, 774.945);
        ((GeneralPath) shape).curveTo(143.23, 774.567, 143.496, 774.539,
                143.827, 774.539);
        ((GeneralPath) shape).curveTo(144.461, 774.539, 144.504, 774.706,
                144.504, 775.179);
        ((GeneralPath) shape).lineTo(144.504, 775.604);
        ((GeneralPath) shape).lineTo(144.48, 775.604);
        ((GeneralPath) shape).curveTo(144.299, 775.223, 143.918, 775.223,
                143.53099, 775.223);
        ((GeneralPath) shape).curveTo(142.78699, 775.223, 142.411, 775.427,
                142.411, 776.16003);
        ((GeneralPath) shape).curveTo(142.411, 776.992, 142.85199, 777.13904,
                143.53099, 777.13904);
        ((GeneralPath) shape).curveTo(143.9, 777.13904, 144.35599, 777.13904,
                144.504, 776.68805);
        ((GeneralPath) shape).lineTo(144.504, 777.138);
        ((GeneralPath) shape).moveTo(143.804, 775.771);
        ((GeneralPath) shape).curveTo(144.179, 775.771, 144.505, 775.771,
                144.505, 776.16);
        ((GeneralPath) shape).curveTo(144.505, 776.56, 144.209, 776.592,
                143.804, 776.592);
        ((GeneralPath) shape).curveTo(143.313, 776.592, 143.14, 776.555,
                143.14, 776.16);
        ((GeneralPath) shape).curveTo(143.14, 775.771, 143.418, 775.771,
                143.804, 775.771);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(145.549, 774.585);
        ((GeneralPath) shape).lineTo(145.933, 774.585);
        ((GeneralPath) shape).lineTo(145.933, 776.182);
        ((GeneralPath) shape).curveTo(145.933, 776.953, 146.181, 777.138,
                146.994, 777.138);
        ((GeneralPath) shape).curveTo(147.791, 777.138, 148.026, 776.859,
                148.026, 775.934);
        ((GeneralPath) shape).lineTo(147.389, 775.934);
        ((GeneralPath) shape).curveTo(147.389, 776.25903, 147.434, 776.591,
                146.994, 776.591);
        ((GeneralPath) shape).curveTo(146.66101, 776.591, 146.66101, 776.461,
                146.66101, 776.176);
        ((GeneralPath) shape).lineTo(146.66101, 774.585);
        ((GeneralPath) shape).lineTo(147.87102, 774.585);
        ((GeneralPath) shape).lineTo(147.87102, 774.038);
        ((GeneralPath) shape).lineTo(146.66101, 774.038);
        ((GeneralPath) shape).lineTo(146.66101, 773.33);
        ((GeneralPath) shape).lineTo(145.93301, 773.33);
        ((GeneralPath) shape).lineTo(145.93301, 774.038);
        ((GeneralPath) shape).lineTo(145.54901, 774.038);
        ((GeneralPath) shape).lineTo(145.54901, 774.585);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(150.41, 777.138);
        ((GeneralPath) shape).lineTo(151.138, 777.138);
        ((GeneralPath) shape).lineTo(151.138, 775.218);
        ((GeneralPath) shape).curveTo(151.138, 774.19104, 150.724, 774.038,
                149.733, 774.038);
        ((GeneralPath) shape).curveTo(149.039, 774.038, 148.408, 774.038,
                148.408, 774.945);
        ((GeneralPath) shape).lineTo(149.136, 774.945);
        ((GeneralPath) shape).curveTo(149.136, 774.567, 149.40201, 774.539,
                149.733, 774.539);
        ((GeneralPath) shape).curveTo(150.367, 774.539, 150.41, 774.706,
                150.41, 775.179);
        ((GeneralPath) shape).lineTo(150.41, 775.604);
        ((GeneralPath) shape).lineTo(150.386, 775.604);
        ((GeneralPath) shape).curveTo(150.205, 775.223, 149.824, 775.223,
                149.437, 775.223);
        ((GeneralPath) shape).curveTo(148.693, 775.223, 148.317, 775.427,
                148.317, 776.16003);
        ((GeneralPath) shape).curveTo(148.317, 776.992, 148.758, 777.13904,
                149.437, 777.13904);
        ((GeneralPath) shape).curveTo(149.806, 777.13904, 150.262, 777.13904,
                150.41, 776.68805);
        ((GeneralPath) shape).lineTo(150.41, 777.138);
        ((GeneralPath) shape).moveTo(149.709, 775.771);
        ((GeneralPath) shape).curveTo(150.084, 775.771, 150.41, 775.771,
                150.41, 776.16);
        ((GeneralPath) shape).curveTo(150.41, 776.56, 150.114, 776.592,
                149.709, 776.592);
        ((GeneralPath) shape).curveTo(149.218, 776.592, 149.045, 776.555,
                149.045, 776.16);
        ((GeneralPath) shape).curveTo(149.045, 775.771, 149.323, 775.771,
                149.709, 775.771);
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
        paint = new Color(35, 31, 32, 255);
        shape = new Rectangle2D.Double(151.83799743652344, 772.760986328125,
                0.7279999852180481, 4.376999855041504);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(155.854, 774.038);
        ((GeneralPath) shape).lineTo(155.1, 774.038);
        ((GeneralPath) shape).lineTo(154.5, 776.67804);
        ((GeneralPath) shape).lineTo(154.489, 776.67804);
        ((GeneralPath) shape).lineTo(153.707, 774.038);
        ((GeneralPath) shape).lineTo(152.942, 774.038);
        ((GeneralPath) shape).lineTo(153.96, 777.138);
        ((GeneralPath) shape).lineTo(154.31801, 777.138);
        ((GeneralPath) shape).curveTo(154.253, 777.503, 154.18301, 777.958,
                153.718, 777.958);
        ((GeneralPath) shape).curveTo(153.66501, 777.958, 153.61101, 777.945,
                153.558, 777.94);
        ((GeneralPath) shape).lineTo(153.558, 778.505);
        ((GeneralPath) shape).curveTo(153.665, 778.505, 153.77, 778.505,
                153.877, 778.505);
        ((GeneralPath) shape).curveTo(154.741, 778.505, 154.882, 777.956,
                155.053, 777.255);
        ((GeneralPath) shape).lineTo(155.854, 774.038);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(158.678, 774.894);
        ((GeneralPath) shape).curveTo(158.678, 774.039, 157.96399, 774.039,
                157.39, 774.039);
        ((GeneralPath) shape).curveTo(156.641, 774.039, 156.039, 774.039,
                156.039, 774.884);
        ((GeneralPath) shape).curveTo(156.039, 775.638, 156.237, 775.782,
                157.327, 775.8);
        ((GeneralPath) shape).curveTo(158.024, 775.811, 158.041, 775.941,
                158.041, 776.198);
        ((GeneralPath) shape).curveTo(158.041, 776.592, 157.798, 776.592,
                157.381, 776.592);
        ((GeneralPath) shape).curveTo(156.866, 776.592, 156.767, 776.542,
                156.767, 776.11395);
        ((GeneralPath) shape).lineTo(156.039, 776.11395);
        ((GeneralPath) shape).curveTo(156.039, 777.13794, 156.58101, 777.13794,
                157.38701, 777.13794);
        ((GeneralPath) shape).curveTo(158.13802, 777.13794, 158.76901,
                777.05396, 158.76901, 776.19794);
        ((GeneralPath) shape).curveTo(158.76901, 775.24097, 158.13602,
                775.29395, 157.45102, 775.25995);
        ((GeneralPath) shape).curveTo(156.84402, 775.23193, 156.76701, 775.22,
                156.76701, 774.928);
        ((GeneralPath) shape).curveTo(156.76701, 774.54, 157.02301, 774.54,
                157.39401, 774.54);
        ((GeneralPath) shape).curveTo(157.76701, 774.54, 157.95001, 774.54,
                157.95001, 774.894);
        ((GeneralPath) shape).lineTo(158.678, 774.894);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(158.99, 774.585);
        ((GeneralPath) shape).lineTo(159.37401, 774.585);
        ((GeneralPath) shape).lineTo(159.37401, 776.182);
        ((GeneralPath) shape).curveTo(159.37401, 776.953, 159.62201, 777.138,
                160.43501, 777.138);
        ((GeneralPath) shape).curveTo(161.23201, 777.138, 161.46701, 776.859,
                161.46701, 775.934);
        ((GeneralPath) shape).lineTo(160.83002, 775.934);
        ((GeneralPath) shape).curveTo(160.83002, 776.25903, 160.87502, 776.591,
                160.43501, 776.591);
        ((GeneralPath) shape).curveTo(160.10202, 776.591, 160.10202, 776.461,
                160.10202, 776.176);
        ((GeneralPath) shape).lineTo(160.10202, 774.585);
        ((GeneralPath) shape).lineTo(161.31203, 774.585);
        ((GeneralPath) shape).lineTo(161.31203, 774.038);
        ((GeneralPath) shape).lineTo(160.10202, 774.038);
        ((GeneralPath) shape).lineTo(160.10202, 773.33);
        ((GeneralPath) shape).lineTo(159.37402, 773.33);
        ((GeneralPath) shape).lineTo(159.37402, 774.038);
        ((GeneralPath) shape).lineTo(158.99002, 774.038);
        ((GeneralPath) shape).lineTo(158.99002, 774.585);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(165.583, 774.768);
        ((GeneralPath) shape).lineTo(165.583, 775.406);
        ((GeneralPath) shape).lineTo(166.721, 775.406);
        ((GeneralPath) shape).lineTo(166.721, 775.566);
        ((GeneralPath) shape).curveTo(166.721, 776.409, 166.46799, 776.409,
                165.644, 776.409);
        ((GeneralPath) shape).curveTo(164.68199, 776.409, 164.537, 776.316,
                164.537, 775.4);
        ((GeneralPath) shape).lineTo(164.537, 774.419);
        ((GeneralPath) shape).curveTo(164.537, 773.623, 164.634, 773.481,
                165.644, 773.481);
        ((GeneralPath) shape).curveTo(166.396, 773.481, 166.721, 773.481,
                166.721, 774.08203);
        ((GeneralPath) shape).lineTo(167.54, 774.08203);
        ((GeneralPath) shape).curveTo(167.54, 772.71, 166.70299, 772.76306,
                165.638, 772.76306);
        ((GeneralPath) shape).curveTo(164.288, 772.76306, 163.718, 773.01105,
                163.718, 774.42004);
        ((GeneralPath) shape).lineTo(163.718, 775.40106);
        ((GeneralPath) shape).curveTo(163.718, 776.89307, 164.244, 777.13904,
                165.645, 777.13904);
        ((GeneralPath) shape).curveTo(167.212, 777.13904, 167.541, 776.869,
                167.541, 775.567);
        ((GeneralPath) shape).lineTo(167.541, 774.76904);
        ((GeneralPath) shape).lineTo(165.583, 774.76904);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(170.135, 777.138);
        ((GeneralPath) shape).lineTo(170.86299, 777.138);
        ((GeneralPath) shape).lineTo(170.86299, 775.218);
        ((GeneralPath) shape).curveTo(170.86299, 774.19104, 170.44899, 774.038,
                169.458, 774.038);
        ((GeneralPath) shape).curveTo(168.76399, 774.038, 168.133, 774.038,
                168.133, 774.945);
        ((GeneralPath) shape).lineTo(168.861, 774.945);
        ((GeneralPath) shape).curveTo(168.861, 774.567, 169.127, 774.539,
                169.458, 774.539);
        ((GeneralPath) shape).curveTo(170.092, 774.539, 170.135, 774.706,
                170.135, 775.179);
        ((GeneralPath) shape).lineTo(170.135, 775.604);
        ((GeneralPath) shape).lineTo(170.111, 775.604);
        ((GeneralPath) shape).curveTo(169.93, 775.223, 169.549, 775.223,
                169.16199, 775.223);
        ((GeneralPath) shape).curveTo(168.41798, 775.223, 168.04199, 775.427,
                168.04199, 776.16003);
        ((GeneralPath) shape).curveTo(168.04199, 776.992, 168.482, 777.13904,
                169.16199, 777.13904);
        ((GeneralPath) shape).curveTo(169.52998, 777.13904, 169.98698,
                777.13904, 170.135, 776.68805);
        ((GeneralPath) shape).lineTo(170.135, 777.138);
        ((GeneralPath) shape).moveTo(169.434, 775.771);
        ((GeneralPath) shape).curveTo(169.809, 775.771, 170.13501, 775.771,
                170.13501, 776.16);
        ((GeneralPath) shape).curveTo(170.13501, 776.56, 169.839, 776.592,
                169.434, 776.592);
        ((GeneralPath) shape).curveTo(168.94301, 776.592, 168.77, 776.555,
                168.77, 776.16);
        ((GeneralPath) shape).curveTo(168.77, 775.771, 169.049, 775.771,
                169.434, 775.771);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(171.563, 774.038);
        ((GeneralPath) shape).lineTo(171.563, 777.138);
        ((GeneralPath) shape).lineTo(172.291, 777.138);
        ((GeneralPath) shape).lineTo(172.291, 775.41);
        ((GeneralPath) shape).curveTo(172.291, 774.86694, 172.415, 774.597,
                173.042, 774.597);
        ((GeneralPath) shape).curveTo(173.46701, 774.597, 173.565, 774.74896,
                173.565, 775.134);
        ((GeneralPath) shape).lineTo(173.565, 777.13696);
        ((GeneralPath) shape).lineTo(174.293, 777.13696);
        ((GeneralPath) shape).lineTo(174.293, 775.40894);
        ((GeneralPath) shape).curveTo(174.293, 774.8659, 174.407, 774.59595,
                174.99, 774.59595);
        ((GeneralPath) shape).curveTo(175.386, 774.59595, 175.476, 774.7479,
                175.476, 775.13293);
        ((GeneralPath) shape).lineTo(175.476, 777.1359);
        ((GeneralPath) shape).lineTo(176.204, 777.1359);
        ((GeneralPath) shape).lineTo(176.204, 775.0629);
        ((GeneralPath) shape).curveTo(176.204, 774.3109, 175.94899, 774.0369,
                175.179, 774.0369);
        ((GeneralPath) shape).curveTo(174.778, 774.0369, 174.367, 774.15295,
                174.229, 774.57794);
        ((GeneralPath) shape).lineTo(174.207, 774.57794);
        ((GeneralPath) shape).curveTo(174.125, 774.13495, 173.687, 774.0369,
                173.307, 774.0369);
        ((GeneralPath) shape).curveTo(172.90001, 774.0369, 172.464, 774.1239,
                172.292, 774.50995);
        ((GeneralPath) shape).lineTo(172.292, 774.0369);
        ((GeneralPath) shape).lineTo(171.563, 774.0369);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(177.582, 775.314);
        ((GeneralPath) shape).curveTo(177.582, 774.74005, 177.623, 774.59705,
                178.256, 774.59705);
        ((GeneralPath) shape).curveTo(178.855, 774.59705, 178.94699, 774.64703,
                178.94699, 775.314);
        ((GeneralPath) shape).lineTo(177.582, 775.314);
        ((GeneralPath) shape).moveTo(178.947, 776.166);
        ((GeneralPath) shape).curveTo(178.947, 776.591, 178.66301, 776.591,
                178.25601, 776.591);
        ((GeneralPath) shape).curveTo(177.59901, 776.591, 177.58202, 776.393,
                177.58202, 775.771);
        ((GeneralPath) shape).lineTo(179.67502, 775.771);
        ((GeneralPath) shape).curveTo(179.67502, 774.412, 179.50702, 774.039,
                178.25601, 774.039);
        ((GeneralPath) shape).curveTo(177.02701, 774.039, 176.85402, 774.527,
                176.85402, 775.645);
        ((GeneralPath) shape).curveTo(176.85402, 776.781, 177.09102, 777.138,
                178.25601, 777.138);
        ((GeneralPath) shape).curveTo(179.12502, 777.138, 179.67502, 777.092,
                179.67502, 776.166);
        ((GeneralPath) shape).lineTo(178.947, 776.166);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(183.134, 772.761);
        ((GeneralPath) shape).lineTo(182.315, 772.761);
        ((GeneralPath) shape).lineTo(182.315, 777.138);
        ((GeneralPath) shape).lineTo(185.173, 777.138);
        ((GeneralPath) shape).lineTo(185.173, 776.42);
        ((GeneralPath) shape).lineTo(183.134, 776.42);
        ((GeneralPath) shape).lineTo(183.134, 772.761);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(187.607, 777.138);
        ((GeneralPath) shape).lineTo(188.33499, 777.138);
        ((GeneralPath) shape).lineTo(188.33499, 775.218);
        ((GeneralPath) shape).curveTo(188.33499, 774.19104, 187.92099, 774.038,
                186.93, 774.038);
        ((GeneralPath) shape).curveTo(186.236, 774.038, 185.605, 774.038,
                185.605, 774.945);
        ((GeneralPath) shape).lineTo(186.333, 774.945);
        ((GeneralPath) shape).curveTo(186.333, 774.567, 186.599, 774.539,
                186.93, 774.539);
        ((GeneralPath) shape).curveTo(187.564, 774.539, 187.607, 774.706,
                187.607, 775.179);
        ((GeneralPath) shape).lineTo(187.607, 775.604);
        ((GeneralPath) shape).lineTo(187.583, 775.604);
        ((GeneralPath) shape).curveTo(187.402, 775.223, 187.021, 775.223,
                186.63399, 775.223);
        ((GeneralPath) shape).curveTo(185.88998, 775.223, 185.51399, 775.427,
                185.51399, 776.16003);
        ((GeneralPath) shape).curveTo(185.51399, 776.992, 185.95499, 777.13904,
                186.63399, 777.13904);
        ((GeneralPath) shape).curveTo(187.00299, 777.13904, 187.45898,
                777.13904, 187.607, 776.68805);
        ((GeneralPath) shape).lineTo(187.607, 777.138);
        ((GeneralPath) shape).moveTo(186.906, 775.771);
        ((GeneralPath) shape).curveTo(187.281, 775.771, 187.60701, 775.771,
                187.60701, 776.16);
        ((GeneralPath) shape).curveTo(187.60701, 776.56, 187.311, 776.592,
                186.906, 776.592);
        ((GeneralPath) shape).curveTo(186.41501, 776.592, 186.242, 776.555,
                186.242, 776.16);
        ((GeneralPath) shape).curveTo(186.242, 775.771, 186.521, 775.771,
                186.906, 775.771);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(189.035, 777.138);
        ((GeneralPath) shape).lineTo(189.763, 777.138);
        ((GeneralPath) shape).lineTo(189.763, 776.659);
        ((GeneralPath) shape).curveTo(189.945, 777.049, 190.276, 777.138,
                190.697, 777.138);
        ((GeneralPath) shape).curveTo(191.856, 777.138, 191.856, 776.436,
                191.856, 775.505);
        ((GeneralPath) shape).curveTo(191.856, 774.612, 191.735, 774.038,
                190.697, 774.038);
        ((GeneralPath) shape).curveTo(190.33, 774.038, 189.947, 774.107,
                189.792, 774.48004);
        ((GeneralPath) shape).lineTo(189.763, 774.48004);
        ((GeneralPath) shape).lineTo(189.763, 772.7601);
        ((GeneralPath) shape).lineTo(189.035, 772.7601);
        ((GeneralPath) shape).lineTo(189.035, 777.138);
        ((GeneralPath) shape).moveTo(190.486, 774.598);
        ((GeneralPath) shape).curveTo(190.98499, 774.598, 191.129, 774.742,
                191.129, 775.505);
        ((GeneralPath) shape).curveTo(191.129, 776.192, 191.129, 776.591,
                190.486, 776.591);
        ((GeneralPath) shape).curveTo(189.76299, 776.591, 189.76299, 776.228,
                189.76299, 775.505);
        ((GeneralPath) shape).curveTo(189.763, 774.857, 189.861, 774.598,
                190.486, 774.598);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(195.042, 774.894);
        ((GeneralPath) shape).curveTo(195.042, 774.039, 194.328, 774.039,
                193.75401, 774.039);
        ((GeneralPath) shape).curveTo(193.00502, 774.039, 192.40302, 774.039,
                192.40302, 774.884);
        ((GeneralPath) shape).curveTo(192.40302, 775.638, 192.60101, 775.782,
                193.69101, 775.8);
        ((GeneralPath) shape).curveTo(194.38802, 775.811, 194.40501, 775.941,
                194.40501, 776.198);
        ((GeneralPath) shape).curveTo(194.40501, 776.592, 194.16202, 776.592,
                193.74501, 776.592);
        ((GeneralPath) shape).curveTo(193.23001, 776.592, 193.13101, 776.542,
                193.13101, 776.11395);
        ((GeneralPath) shape).lineTo(192.40302, 776.11395);
        ((GeneralPath) shape).curveTo(192.40302, 777.13794, 192.94502,
                777.13794, 193.75102, 777.13794);
        ((GeneralPath) shape).curveTo(194.50203, 777.13794, 195.13303,
                777.05396, 195.13303, 776.19794);
        ((GeneralPath) shape).curveTo(195.13303, 775.24097, 194.50003,
                775.29395, 193.81503, 775.25995);
        ((GeneralPath) shape).curveTo(193.20804, 775.23193, 193.13103, 775.22,
                193.13103, 774.928);
        ((GeneralPath) shape).curveTo(193.13103, 774.54, 193.38702, 774.54,
                193.75803, 774.54);
        ((GeneralPath) shape).curveTo(194.13103, 774.54, 194.31403, 774.54,
                194.31403, 774.894);
        ((GeneralPath) shape).lineTo(195.042, 774.894);
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
        shape = new Rectangle2D.Double(197.78900146484375, 772.760986328125,
                0.7279999852180481, 4.376999855041504);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(200.576, 774.598);
        ((GeneralPath) shape).curveTo(201.207, 774.598, 201.259, 774.784,
                201.259, 775.604);
        ((GeneralPath) shape).curveTo(201.259, 776.412, 201.20801, 776.591,
                200.576, 776.591);
        ((GeneralPath) shape).curveTo(199.945, 776.591, 199.894, 776.411,
                199.894, 775.604);
        ((GeneralPath) shape).curveTo(199.894, 774.783, 199.945, 774.598,
                200.576, 774.598);
        ((GeneralPath) shape).moveTo(200.576, 774.038);
        ((GeneralPath) shape).curveTo(199.325, 774.038, 199.166, 774.41504,
                199.166, 775.598);
        ((GeneralPath) shape).curveTo(199.166, 776.765, 199.325, 777.138,
                200.576, 777.138);
        ((GeneralPath) shape).curveTo(201.82701, 777.138, 201.987, 776.765,
                201.987, 775.598);
        ((GeneralPath) shape).curveTo(201.986, 774.415, 201.827, 774.038,
                200.576, 774.038);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(204.648, 777.258);
        ((GeneralPath) shape).curveTo(204.648, 777.821, 204.52199, 777.958,
                203.931, 777.958);
        ((GeneralPath) shape).curveTo(203.527, 777.958, 203.374, 777.89,
                203.374, 777.438);
        ((GeneralPath) shape).lineTo(202.646, 777.438);
        ((GeneralPath) shape).curveTo(202.599, 778.37396, 203.16899, 778.505,
                203.931, 778.505);
        ((GeneralPath) shape).curveTo(205.032, 778.505, 205.376, 778.234,
                205.376, 777.097);
        ((GeneralPath) shape).lineTo(205.376, 774.037);
        ((GeneralPath) shape).lineTo(204.64801, 774.037);
        ((GeneralPath) shape).lineTo(204.64801, 774.528);
        ((GeneralPath) shape).curveTo(204.46701, 774.129, 204.139, 774.037,
                203.72101, 774.037);
        ((GeneralPath) shape).curveTo(202.55501, 774.037, 202.55501, 774.752,
                202.55501, 775.683);
        ((GeneralPath) shape).curveTo(202.55501, 776.568, 202.67801, 777.13696,
                203.72101, 777.13696);
        ((GeneralPath) shape).curveTo(204.082, 777.13696, 204.462, 777.069,
                204.64801, 776.675);
        ((GeneralPath) shape).lineTo(204.64801, 777.258);
        ((GeneralPath) shape).moveTo(203.931, 774.598);
        ((GeneralPath) shape).curveTo(204.551, 774.598, 204.648, 774.859,
                204.648, 775.505);
        ((GeneralPath) shape).curveTo(204.648, 776.228, 204.648, 776.591,
                203.926, 776.591);
        ((GeneralPath) shape).curveTo(203.28299, 776.591, 203.28299, 776.192,
                203.28299, 775.505);
        ((GeneralPath) shape).curveTo(203.283, 774.741, 203.428, 774.598,
                203.931, 774.598);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(207.425, 774.598);
        ((GeneralPath) shape).curveTo(208.056, 774.598, 208.108, 774.784,
                208.108, 775.604);
        ((GeneralPath) shape).curveTo(208.108, 776.412, 208.057, 776.591,
                207.425, 776.591);
        ((GeneralPath) shape).curveTo(206.793, 776.591, 206.742, 776.411,
                206.742, 775.604);
        ((GeneralPath) shape).curveTo(206.742, 774.783, 206.793, 774.598,
                207.425, 774.598);
        ((GeneralPath) shape).moveTo(207.425, 774.038);
        ((GeneralPath) shape).curveTo(206.174, 774.038, 206.014, 774.41504,
                206.014, 775.598);
        ((GeneralPath) shape).curveTo(206.014, 776.765, 206.173, 777.138,
                207.425, 777.138);
        ((GeneralPath) shape).curveTo(208.67601, 777.138, 208.836, 776.765,
                208.836, 775.598);
        ((GeneralPath) shape).curveTo(208.835, 774.415, 208.676, 774.038,
                207.425, 774.038);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(213.547, 777.138);
        ((GeneralPath) shape).lineTo(214.275, 777.138);
        ((GeneralPath) shape).lineTo(214.275, 775.218);
        ((GeneralPath) shape).curveTo(214.275, 774.19104, 213.861, 774.038,
                212.87, 774.038);
        ((GeneralPath) shape).curveTo(212.176, 774.038, 211.545, 774.038,
                211.545, 774.945);
        ((GeneralPath) shape).lineTo(212.273, 774.945);
        ((GeneralPath) shape).curveTo(212.273, 774.567, 212.539, 774.539,
                212.87, 774.539);
        ((GeneralPath) shape).curveTo(213.504, 774.539, 213.547, 774.706,
                213.547, 775.179);
        ((GeneralPath) shape).lineTo(213.547, 775.604);
        ((GeneralPath) shape).lineTo(213.523, 775.604);
        ((GeneralPath) shape).curveTo(213.342, 775.223, 212.961, 775.223,
                212.575, 775.223);
        ((GeneralPath) shape).curveTo(211.831, 775.223, 211.454, 775.427,
                211.454, 776.16003);
        ((GeneralPath) shape).curveTo(211.454, 776.992, 211.89499, 777.13904,
                212.575, 777.13904);
        ((GeneralPath) shape).curveTo(212.943, 777.13904, 213.4, 777.13904,
                213.548, 776.68805);
        ((GeneralPath) shape).lineTo(213.548, 777.138);
        ((GeneralPath) shape).moveTo(212.846, 775.771);
        ((GeneralPath) shape).curveTo(213.221, 775.771, 213.547, 775.771,
                213.547, 776.16);
        ((GeneralPath) shape).curveTo(213.547, 776.56, 213.25099, 776.592,
                212.846, 776.592);
        ((GeneralPath) shape).curveTo(212.355, 776.592, 212.18199, 776.555,
                212.18199, 776.16);
        ((GeneralPath) shape).curveTo(212.182, 775.771, 212.46, 775.771,
                212.846, 775.771);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(214.884, 774.038);
        ((GeneralPath) shape).lineTo(214.884, 777.138);
        ((GeneralPath) shape).lineTo(215.612, 777.138);
        ((GeneralPath) shape).lineTo(215.612, 775.246);
        ((GeneralPath) shape).curveTo(215.612, 774.849, 215.747, 774.59796,
                216.211, 774.59796);
        ((GeneralPath) shape).curveTo(216.583, 774.59796, 216.61299, 774.779,
                216.61299, 775.08795);
        ((GeneralPath) shape).lineTo(216.61299, 775.246);
        ((GeneralPath) shape).lineTo(217.34099, 775.246);
        ((GeneralPath) shape).lineTo(217.34099, 775.001);
        ((GeneralPath) shape).curveTo(217.34099, 774.423, 217.17299, 774.03796,
                216.48698, 774.03796);
        ((GeneralPath) shape).curveTo(216.10599, 774.03796, 215.76898,
                774.13495, 215.61198, 774.467);
        ((GeneralPath) shape).lineTo(215.61198, 774.03796);
        ((GeneralPath) shape).lineTo(214.884, 774.03796);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(218.402, 775.314);
        ((GeneralPath) shape).curveTo(218.402, 774.74005, 218.444, 774.59705,
                219.07599, 774.59705);
        ((GeneralPath) shape).curveTo(219.67499, 774.59705, 219.76698,
                774.64703, 219.76698, 775.314);
        ((GeneralPath) shape).lineTo(218.402, 775.314);
        ((GeneralPath) shape).moveTo(219.767, 776.166);
        ((GeneralPath) shape).curveTo(219.767, 776.591, 219.482, 776.591,
                219.076, 776.591);
        ((GeneralPath) shape).curveTo(218.419, 776.591, 218.40201, 776.393,
                218.40201, 775.771);
        ((GeneralPath) shape).lineTo(220.49501, 775.771);
        ((GeneralPath) shape).curveTo(220.49501, 774.412, 220.32701, 774.039,
                219.076, 774.039);
        ((GeneralPath) shape).curveTo(217.847, 774.039, 217.67401, 774.527,
                217.67401, 775.645);
        ((GeneralPath) shape).curveTo(217.67401, 776.781, 217.91202, 777.138,
                219.076, 777.138);
        ((GeneralPath) shape).curveTo(219.945, 777.138, 220.49501, 777.092,
                220.49501, 776.166);
        ((GeneralPath) shape).lineTo(219.767, 776.166);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(222.75, 774.585);
        ((GeneralPath) shape).lineTo(223.134, 774.585);
        ((GeneralPath) shape).lineTo(223.134, 776.182);
        ((GeneralPath) shape).curveTo(223.134, 776.953, 223.381, 777.138,
                224.195, 777.138);
        ((GeneralPath) shape).curveTo(224.99301, 777.138, 225.227, 776.859,
                225.227, 775.934);
        ((GeneralPath) shape).lineTo(224.59001, 775.934);
        ((GeneralPath) shape).curveTo(224.59001, 776.25903, 224.63602, 776.591,
                224.195, 776.591);
        ((GeneralPath) shape).curveTo(223.86201, 776.591, 223.86201, 776.461,
                223.86201, 776.176);
        ((GeneralPath) shape).lineTo(223.86201, 774.585);
        ((GeneralPath) shape).lineTo(225.07202, 774.585);
        ((GeneralPath) shape).lineTo(225.07202, 774.038);
        ((GeneralPath) shape).lineTo(223.86201, 774.038);
        ((GeneralPath) shape).lineTo(223.86201, 773.33);
        ((GeneralPath) shape).lineTo(223.13402, 773.33);
        ((GeneralPath) shape).lineTo(223.13402, 774.038);
        ((GeneralPath) shape).lineTo(222.75002, 774.038);
        ((GeneralPath) shape).lineTo(222.75002, 774.585);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(225.519, 774.038);
        ((GeneralPath) shape).lineTo(225.519, 777.138);
        ((GeneralPath) shape).lineTo(226.247, 777.138);
        ((GeneralPath) shape).lineTo(226.247, 775.246);
        ((GeneralPath) shape).curveTo(226.247, 774.849, 226.38199, 774.59796,
                226.846, 774.59796);
        ((GeneralPath) shape).curveTo(227.217, 774.59796, 227.24799, 774.779,
                227.24799, 775.08795);
        ((GeneralPath) shape).lineTo(227.24799, 775.246);
        ((GeneralPath) shape).lineTo(227.97598, 775.246);
        ((GeneralPath) shape).lineTo(227.97598, 775.001);
        ((GeneralPath) shape).curveTo(227.97598, 774.423, 227.80798, 774.03796,
                227.12198, 774.03796);
        ((GeneralPath) shape).curveTo(226.74098, 774.03796, 226.40398,
                774.13495, 226.24698, 774.467);
        ((GeneralPath) shape).lineTo(226.24698, 774.03796);
        ((GeneralPath) shape).lineTo(225.519, 774.03796);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(230.354, 777.138);
        ((GeneralPath) shape).lineTo(231.082, 777.138);
        ((GeneralPath) shape).lineTo(231.082, 775.218);
        ((GeneralPath) shape).curveTo(231.082, 774.19104, 230.668, 774.038,
                229.677, 774.038);
        ((GeneralPath) shape).curveTo(228.983, 774.038, 228.352, 774.038,
                228.352, 774.945);
        ((GeneralPath) shape).lineTo(229.08, 774.945);
        ((GeneralPath) shape).curveTo(229.08, 774.567, 229.34601, 774.539,
                229.677, 774.539);
        ((GeneralPath) shape).curveTo(230.311, 774.539, 230.354, 774.706,
                230.354, 775.179);
        ((GeneralPath) shape).lineTo(230.354, 775.604);
        ((GeneralPath) shape).lineTo(230.33, 775.604);
        ((GeneralPath) shape).curveTo(230.149, 775.223, 229.768, 775.223,
                229.382, 775.223);
        ((GeneralPath) shape).curveTo(228.638, 775.223, 228.261, 775.427,
                228.261, 776.16003);
        ((GeneralPath) shape).curveTo(228.261, 776.992, 228.702, 777.13904,
                229.382, 777.13904);
        ((GeneralPath) shape).curveTo(229.75, 777.13904, 230.20601, 777.13904,
                230.354, 776.68805);
        ((GeneralPath) shape).lineTo(230.354, 777.138);
        ((GeneralPath) shape).moveTo(229.654, 775.771);
        ((GeneralPath) shape).curveTo(230.029, 775.771, 230.35501, 775.771,
                230.35501, 776.16);
        ((GeneralPath) shape).curveTo(230.35501, 776.56, 230.06001, 776.592,
                229.654, 776.592);
        ((GeneralPath) shape).curveTo(229.16301, 776.592, 228.99, 776.555,
                228.99, 776.16);
        ((GeneralPath) shape).curveTo(228.99, 775.771, 229.269, 775.771,
                229.654, 775.771);
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
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(233.785, 777.138);
        ((GeneralPath) shape).lineTo(234.513, 777.138);
        ((GeneralPath) shape).lineTo(234.513, 772.761);
        ((GeneralPath) shape).lineTo(233.785, 772.761);
        ((GeneralPath) shape).lineTo(233.785, 774.48096);
        ((GeneralPath) shape).lineTo(233.768, 774.48096);
        ((GeneralPath) shape).curveTo(233.612, 774.108, 233.223, 774.03894,
                232.85301, 774.03894);
        ((GeneralPath) shape).curveTo(231.81201, 774.03894, 231.69101,
                774.6129, 231.69101, 775.5059);
        ((GeneralPath) shape).curveTo(231.69101, 776.43695, 231.69101,
                777.1389, 232.85301, 777.1389);
        ((GeneralPath) shape).curveTo(233.27402, 777.1389, 233.60301, 777.0489,
                233.78502, 776.6599);
        ((GeneralPath) shape).lineTo(233.78502, 777.138);
        ((GeneralPath) shape).moveTo(233.068, 774.598);
        ((GeneralPath) shape).curveTo(233.698, 774.598, 233.78499, 774.85803,
                233.78499, 775.505);
        ((GeneralPath) shape).curveTo(233.78499, 776.228, 233.78499, 776.591,
                233.068, 776.591);
        ((GeneralPath) shape).curveTo(232.42, 776.591, 232.42, 776.192, 232.42,
                775.505);
        ((GeneralPath) shape).curveTo(232.42, 774.741, 232.565, 774.598,
                233.068, 774.598);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(235.873, 775.314);
        ((GeneralPath) shape).curveTo(235.873, 774.74005, 235.91501, 774.59705,
                236.547, 774.59705);
        ((GeneralPath) shape).curveTo(237.146, 774.59705, 237.23799, 774.64703,
                237.23799, 775.314);
        ((GeneralPath) shape).lineTo(235.873, 775.314);
        ((GeneralPath) shape).moveTo(237.238, 776.166);
        ((GeneralPath) shape).curveTo(237.238, 776.591, 236.95401, 776.591,
                236.54701, 776.591);
        ((GeneralPath) shape).curveTo(235.89001, 776.591, 235.87302, 776.393,
                235.87302, 775.771);
        ((GeneralPath) shape).lineTo(237.96602, 775.771);
        ((GeneralPath) shape).curveTo(237.96602, 774.412, 237.79802, 774.039,
                236.54701, 774.039);
        ((GeneralPath) shape).curveTo(235.31801, 774.039, 235.14502, 774.527,
                235.14502, 775.645);
        ((GeneralPath) shape).curveTo(235.14502, 776.781, 235.38202, 777.138,
                236.54701, 777.138);
        ((GeneralPath) shape).curveTo(237.41602, 777.138, 237.96602, 777.092,
                237.96602, 776.166);
        ((GeneralPath) shape).lineTo(237.238, 776.166);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(238.556, 774.038);
        ((GeneralPath) shape).lineTo(238.556, 777.138);
        ((GeneralPath) shape).lineTo(239.284, 777.138);
        ((GeneralPath) shape).lineTo(239.284, 775.41);
        ((GeneralPath) shape).curveTo(239.284, 774.86694, 239.40799, 774.597,
                240.034, 774.597);
        ((GeneralPath) shape).curveTo(240.459, 774.597, 240.55699, 774.74896,
                240.55699, 775.134);
        ((GeneralPath) shape).lineTo(240.55699, 777.13696);
        ((GeneralPath) shape).lineTo(241.28499, 777.13696);
        ((GeneralPath) shape).lineTo(241.28499, 775.40894);
        ((GeneralPath) shape).curveTo(241.28499, 774.8659, 241.398, 774.59595,
                241.982, 774.59595);
        ((GeneralPath) shape).curveTo(242.377, 774.59595, 242.46799, 774.7479,
                242.46799, 775.13293);
        ((GeneralPath) shape).lineTo(242.46799, 777.1359);
        ((GeneralPath) shape).lineTo(243.19598, 777.1359);
        ((GeneralPath) shape).lineTo(243.19598, 775.0629);
        ((GeneralPath) shape).curveTo(243.19598, 774.3109, 242.94199, 774.0369,
                242.17099, 774.0369);
        ((GeneralPath) shape).curveTo(241.76999, 774.0369, 241.359, 774.15295,
                241.221, 774.57794);
        ((GeneralPath) shape).lineTo(241.198, 774.57794);
        ((GeneralPath) shape).curveTo(241.117, 774.13495, 240.679, 774.0369,
                240.298, 774.0369);
        ((GeneralPath) shape).curveTo(239.892, 774.0369, 239.455, 774.1239,
                239.283, 774.50995);
        ((GeneralPath) shape).lineTo(239.283, 774.0369);
        ((GeneralPath) shape).lineTo(238.556, 774.0369);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(245.939, 777.138);
        ((GeneralPath) shape).lineTo(246.66699, 777.138);
        ((GeneralPath) shape).lineTo(246.66699, 775.218);
        ((GeneralPath) shape).curveTo(246.66699, 774.19104, 246.25299, 774.038,
                245.262, 774.038);
        ((GeneralPath) shape).curveTo(244.568, 774.038, 243.937, 774.038,
                243.937, 774.945);
        ((GeneralPath) shape).lineTo(244.665, 774.945);
        ((GeneralPath) shape).curveTo(244.665, 774.567, 244.931, 774.539,
                245.262, 774.539);
        ((GeneralPath) shape).curveTo(245.896, 774.539, 245.939, 774.706,
                245.939, 775.179);
        ((GeneralPath) shape).lineTo(245.939, 775.604);
        ((GeneralPath) shape).lineTo(245.915, 775.604);
        ((GeneralPath) shape).curveTo(245.734, 775.223, 245.353, 775.223,
                244.967, 775.223);
        ((GeneralPath) shape).curveTo(244.22299, 775.223, 243.846, 775.427,
                243.846, 776.16003);
        ((GeneralPath) shape).curveTo(243.846, 776.992, 244.28699, 777.13904,
                244.967, 777.13904);
        ((GeneralPath) shape).curveTo(245.33499, 777.13904, 245.79199,
                777.13904, 245.939, 776.68805);
        ((GeneralPath) shape).lineTo(245.939, 777.138);
        ((GeneralPath) shape).moveTo(245.238, 775.771);
        ((GeneralPath) shape).curveTo(245.613, 775.771, 245.93901, 775.771,
                245.93901, 776.16);
        ((GeneralPath) shape).curveTo(245.93901, 776.56, 245.64401, 776.592,
                245.238, 776.592);
        ((GeneralPath) shape).curveTo(244.74701, 776.592, 244.574, 776.555,
                244.574, 776.16);
        ((GeneralPath) shape).curveTo(244.574, 775.771, 244.853, 775.771,
                245.238, 775.771);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(247.276, 774.038);
        ((GeneralPath) shape).lineTo(247.276, 777.138);
        ((GeneralPath) shape).lineTo(248.004, 777.138);
        ((GeneralPath) shape).lineTo(248.004, 775.246);
        ((GeneralPath) shape).curveTo(248.004, 774.849, 248.13899, 774.59796,
                248.603, 774.59796);
        ((GeneralPath) shape).curveTo(248.974, 774.59796, 249.00499, 774.779,
                249.00499, 775.08795);
        ((GeneralPath) shape).lineTo(249.00499, 775.246);
        ((GeneralPath) shape).lineTo(249.73299, 775.246);
        ((GeneralPath) shape).lineTo(249.73299, 775.001);
        ((GeneralPath) shape).curveTo(249.73299, 774.423, 249.56499, 774.03796,
                248.87898, 774.03796);
        ((GeneralPath) shape).curveTo(248.49799, 774.03796, 248.16098,
                774.13495, 248.00398, 774.467);
        ((GeneralPath) shape).lineTo(248.00398, 774.03796);
        ((GeneralPath) shape).lineTo(247.276, 774.03796);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(250.839, 772.761);
        ((GeneralPath) shape).lineTo(250.111, 772.761);
        ((GeneralPath) shape).lineTo(250.111, 777.138);
        ((GeneralPath) shape).lineTo(250.839, 777.138);
        ((GeneralPath) shape).lineTo(250.839, 775.771);
        ((GeneralPath) shape).lineTo(251.019, 775.771);
        ((GeneralPath) shape).lineTo(252.039, 777.138);
        ((GeneralPath) shape).lineTo(252.923, 777.138);
        ((GeneralPath) shape).lineTo(251.619, 775.496);
        ((GeneralPath) shape).lineTo(252.703, 774.038);
        ((GeneralPath) shape).lineTo(251.875, 774.038);
        ((GeneralPath) shape).lineTo(251.019, 775.223);
        ((GeneralPath) shape).lineTo(250.839, 775.223);
        ((GeneralPath) shape).lineTo(250.839, 772.761);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(255.798, 774.894);
        ((GeneralPath) shape).curveTo(255.798, 774.039, 255.084, 774.039,
                254.51001, 774.039);
        ((GeneralPath) shape).curveTo(253.76001, 774.039, 253.15901, 774.039,
                253.15901, 774.884);
        ((GeneralPath) shape).curveTo(253.15901, 775.638, 253.35701, 775.782,
                254.447, 775.8);
        ((GeneralPath) shape).curveTo(255.14401, 775.811, 255.16101, 775.941,
                255.16101, 776.198);
        ((GeneralPath) shape).curveTo(255.16101, 776.592, 254.91801, 776.592,
                254.501, 776.592);
        ((GeneralPath) shape).curveTo(253.98601, 776.592, 253.88701, 776.542,
                253.88701, 776.11395);
        ((GeneralPath) shape).lineTo(253.15901, 776.11395);
        ((GeneralPath) shape).curveTo(253.15901, 777.13794, 253.70102,
                777.13794, 254.50702, 777.13794);
        ((GeneralPath) shape).curveTo(255.25702, 777.13794, 255.88902,
                777.05396, 255.88902, 776.19794);
        ((GeneralPath) shape).curveTo(255.88902, 775.24097, 255.25702,
                775.29395, 254.57103, 775.25995);
        ((GeneralPath) shape).curveTo(253.96404, 775.23193, 253.88702, 775.22,
                253.88702, 774.928);
        ((GeneralPath) shape).curveTo(253.88702, 774.54, 254.14302, 774.54,
                254.51402, 774.54);
        ((GeneralPath) shape).curveTo(254.88702, 774.54, 255.07002, 774.54,
                255.07002, 774.894);
        ((GeneralPath) shape).lineTo(255.798, 774.894);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(259.863, 774.598);
        ((GeneralPath) shape).curveTo(260.49402, 774.598, 260.54602, 774.784,
                260.54602, 775.604);
        ((GeneralPath) shape).curveTo(260.54602, 776.412, 260.49503, 776.591,
                259.863, 776.591);
        ((GeneralPath) shape).curveTo(259.231, 776.591, 259.18, 776.411,
                259.18, 775.604);
        ((GeneralPath) shape).curveTo(259.181, 774.783, 259.232, 774.598,
                259.863, 774.598);
        ((GeneralPath) shape).moveTo(259.863, 774.038);
        ((GeneralPath) shape).curveTo(258.612, 774.038, 258.453, 774.41504,
                258.453, 775.598);
        ((GeneralPath) shape).curveTo(258.453, 776.765, 258.612, 777.138,
                259.863, 777.138);
        ((GeneralPath) shape).curveTo(261.114, 777.138, 261.27402, 776.765,
                261.27402, 775.598);
        ((GeneralPath) shape).curveTo(261.274, 774.415, 261.115, 774.038,
                259.863, 774.038);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(263.588, 774.038);
        ((GeneralPath) shape).lineTo(262.84302, 774.038);
        ((GeneralPath) shape).lineTo(262.84302, 773.864);
        ((GeneralPath) shape).curveTo(262.84302, 773.51, 262.84302, 773.321,
                263.34302, 773.321);
        ((GeneralPath) shape).lineTo(263.565, 773.321);
        ((GeneralPath) shape).lineTo(263.565, 772.76);
        ((GeneralPath) shape).lineTo(263.088, 772.76);
        ((GeneralPath) shape).curveTo(262.28702, 772.76, 262.114, 773.06104,
                262.114, 773.77704);
        ((GeneralPath) shape).lineTo(262.114, 774.038);
        ((GeneralPath) shape).lineTo(261.683, 774.038);
        ((GeneralPath) shape).lineTo(261.683, 774.585);
        ((GeneralPath) shape).lineTo(262.114, 774.585);
        ((GeneralPath) shape).lineTo(262.114, 777.138);
        ((GeneralPath) shape).lineTo(262.842, 777.138);
        ((GeneralPath) shape).lineTo(262.842, 774.585);
        ((GeneralPath) shape).lineTo(263.587, 774.585);
        ((GeneralPath) shape).lineTo(263.587, 774.038);
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
        paint = new Color(35, 31, 32, 255);
        shape = new Rectangle2D.Double(266.010009765625, 772.760986328125,
                0.8190000057220459, 4.376999855041504);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(267.618, 774.038);
        ((GeneralPath) shape).lineTo(267.618, 777.138);
        ((GeneralPath) shape).lineTo(268.346, 777.138);
        ((GeneralPath) shape).lineTo(268.346, 775.45);
        ((GeneralPath) shape).curveTo(268.346, 774.90204, 268.431, 774.598,
                269.06, 774.598);
        ((GeneralPath) shape).curveTo(269.523, 774.598, 269.62, 774.75, 269.62,
                775.19403);
        ((GeneralPath) shape).lineTo(269.62, 777.138);
        ((GeneralPath) shape).lineTo(270.348, 777.138);
        ((GeneralPath) shape).lineTo(270.348, 775.118);
        ((GeneralPath) shape).curveTo(270.348, 774.371, 270.106, 774.03796,
                269.311, 774.03796);
        ((GeneralPath) shape).curveTo(268.889, 774.03796, 268.529, 774.08295,
                268.368, 774.529);
        ((GeneralPath) shape).lineTo(268.346, 774.529);
        ((GeneralPath) shape).lineTo(268.346, 774.03796);
        ((GeneralPath) shape).lineTo(267.618, 774.03796);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(275.377, 773.449);
        ((GeneralPath) shape).lineTo(275.377, 777.138);
        ((GeneralPath) shape).lineTo(276.196, 777.138);
        ((GeneralPath) shape).lineTo(276.196, 772.761);
        ((GeneralPath) shape).lineTo(274.835, 772.761);
        ((GeneralPath) shape).lineTo(273.656, 776.013);
        ((GeneralPath) shape).lineTo(273.634, 776.013);
        ((GeneralPath) shape).lineTo(272.431, 772.761);
        ((GeneralPath) shape).lineTo(271.1, 772.761);
        ((GeneralPath) shape).lineTo(271.1, 777.138);
        ((GeneralPath) shape).lineTo(271.919, 777.138);
        ((GeneralPath) shape).lineTo(271.919, 773.468);
        ((GeneralPath) shape).lineTo(273.268, 777.138);
        ((GeneralPath) shape).lineTo(274.027, 777.138);
        ((GeneralPath) shape).lineTo(275.377, 773.449);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(277.602, 775.314);
        ((GeneralPath) shape).curveTo(277.602, 774.74005, 277.64297, 774.59705,
                278.276, 774.59705);
        ((GeneralPath) shape).curveTo(278.875, 774.59705, 278.967, 774.64703,
                278.967, 775.314);
        ((GeneralPath) shape).lineTo(277.602, 775.314);
        ((GeneralPath) shape).moveTo(278.967, 776.166);
        ((GeneralPath) shape).curveTo(278.967, 776.591, 278.683, 776.591,
                278.276, 776.591);
        ((GeneralPath) shape).curveTo(277.619, 776.591, 277.602, 776.393,
                277.602, 775.771);
        ((GeneralPath) shape).lineTo(279.69498, 775.771);
        ((GeneralPath) shape).curveTo(279.69498, 774.412, 279.52698, 774.039,
                278.27597, 774.039);
        ((GeneralPath) shape).curveTo(277.04697, 774.039, 276.87396, 774.527,
                276.87396, 775.645);
        ((GeneralPath) shape).curveTo(276.87396, 776.781, 277.11096, 777.138,
                278.27597, 777.138);
        ((GeneralPath) shape).curveTo(279.14496, 777.138, 279.69498, 777.092,
                279.69498, 776.166);
        ((GeneralPath) shape).lineTo(278.967, 776.166);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(282.287, 777.138);
        ((GeneralPath) shape).lineTo(283.01498, 777.138);
        ((GeneralPath) shape).lineTo(283.01498, 772.761);
        ((GeneralPath) shape).lineTo(282.287, 772.761);
        ((GeneralPath) shape).lineTo(282.287, 774.48096);
        ((GeneralPath) shape).lineTo(282.27, 774.48096);
        ((GeneralPath) shape).curveTo(282.11398, 774.108, 281.72498, 774.03894,
                281.35498, 774.03894);
        ((GeneralPath) shape).curveTo(280.314, 774.03894, 280.193, 774.6129,
                280.193, 775.5059);
        ((GeneralPath) shape).curveTo(280.193, 776.43695, 280.193, 777.1389,
                281.35498, 777.1389);
        ((GeneralPath) shape).curveTo(281.77597, 777.1389, 282.10498, 777.0489,
                282.287, 776.6599);
        ((GeneralPath) shape).lineTo(282.287, 777.138);
        ((GeneralPath) shape).moveTo(281.57, 774.598);
        ((GeneralPath) shape).curveTo(282.2, 774.598, 282.28702, 774.85803,
                282.28702, 775.505);
        ((GeneralPath) shape).curveTo(282.28702, 776.228, 282.28702, 776.591,
                281.57, 776.591);
        ((GeneralPath) shape).curveTo(280.922, 776.591, 280.922, 776.192,
                280.922, 775.505);
        ((GeneralPath) shape).curveTo(280.921, 774.741, 281.066, 774.598,
                281.57, 774.598);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(284.466, 772.761);
        ((GeneralPath) shape).lineTo(283.738, 772.761);
        ((GeneralPath) shape).lineTo(283.738, 773.386);
        ((GeneralPath) shape).lineTo(284.466, 773.386);
        ((GeneralPath) shape).lineTo(284.466, 772.761);
        ((GeneralPath) shape).moveTo(284.466, 774.038);
        ((GeneralPath) shape).lineTo(283.738, 774.038);
        ((GeneralPath) shape).lineTo(283.738, 777.138);
        ((GeneralPath) shape).lineTo(284.466, 777.138);
        ((GeneralPath) shape).lineTo(284.466, 774.038);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(287.114, 777.138);
        ((GeneralPath) shape).lineTo(287.842, 777.138);
        ((GeneralPath) shape).lineTo(287.842, 775.218);
        ((GeneralPath) shape).curveTo(287.842, 774.19104, 287.428, 774.038,
                286.437, 774.038);
        ((GeneralPath) shape).curveTo(285.743, 774.038, 285.112, 774.038,
                285.112, 774.945);
        ((GeneralPath) shape).lineTo(285.84, 774.945);
        ((GeneralPath) shape).curveTo(285.84, 774.567, 286.106, 774.539,
                286.43698, 774.539);
        ((GeneralPath) shape).curveTo(287.07098, 774.539, 287.11398, 774.706,
                287.11398, 775.179);
        ((GeneralPath) shape).lineTo(287.11398, 775.604);
        ((GeneralPath) shape).lineTo(287.09, 775.604);
        ((GeneralPath) shape).curveTo(286.91, 775.223, 286.52798, 775.223,
                286.142, 775.223);
        ((GeneralPath) shape).curveTo(285.398, 775.223, 285.022, 775.427,
                285.022, 776.16003);
        ((GeneralPath) shape).curveTo(285.022, 776.992, 285.462, 777.13904,
                286.142, 777.13904);
        ((GeneralPath) shape).curveTo(286.51, 777.13904, 286.967, 777.13904,
                287.115, 776.68805);
        ((GeneralPath) shape).lineTo(287.115, 777.138);
        ((GeneralPath) shape).moveTo(286.413, 775.771);
        ((GeneralPath) shape).curveTo(286.788, 775.771, 287.11398, 775.771,
                287.11398, 776.16);
        ((GeneralPath) shape).curveTo(287.11398, 776.56, 286.818, 776.592,
                286.413, 776.592);
        ((GeneralPath) shape).curveTo(285.922, 776.592, 285.749, 776.555,
                285.749, 776.16);
        ((GeneralPath) shape).curveTo(285.749, 775.771, 286.028, 775.771,
                286.413, 775.771);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(289.361, 774.949);
        ((GeneralPath) shape).lineTo(289.361, 773.479);
        ((GeneralPath) shape).lineTo(290.492, 773.479);
        ((GeneralPath) shape).curveTo(291.073, 773.479, 291.181, 773.588,
                291.181, 774.197);
        ((GeneralPath) shape).curveTo(291.181, 774.828, 291.038, 774.94904,
                290.455, 774.94904);
        ((GeneralPath) shape).lineTo(289.361, 774.94904);
        ((GeneralPath) shape).moveTo(290.634, 775.679);
        ((GeneralPath) shape).curveTo(291.032, 775.679, 291.181, 775.939,
                291.181, 776.312);
        ((GeneralPath) shape).lineTo(291.181, 777.138);
        ((GeneralPath) shape).lineTo(292.0, 777.138);
        ((GeneralPath) shape).lineTo(292.0, 776.313);
        ((GeneralPath) shape).curveTo(292.0, 775.69, 291.852, 775.365, 291.247,
                775.312);
        ((GeneralPath) shape).lineTo(291.247, 775.288);
        ((GeneralPath) shape).curveTo(292.0, 775.177, 292.0, 774.70404, 292.0,
                774.065);
        ((GeneralPath) shape).curveTo(292.0, 773.062, 291.65, 772.761, 290.737,
                772.761);
        ((GeneralPath) shape).lineTo(288.542, 772.761);
        ((GeneralPath) shape).lineTo(288.542, 777.138);
        ((GeneralPath) shape).lineTo(289.361, 777.138);
        ((GeneralPath) shape).lineTo(289.361, 775.679);
        ((GeneralPath) shape).lineTo(290.634, 775.679);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(293.385, 775.314);
        ((GeneralPath) shape).curveTo(293.385, 774.74005, 293.426, 774.59705,
                294.05902, 774.59705);
        ((GeneralPath) shape).curveTo(294.65802, 774.59705, 294.75003,
                774.64703, 294.75003, 775.314);
        ((GeneralPath) shape).lineTo(293.385, 775.314);
        ((GeneralPath) shape).moveTo(294.75, 776.166);
        ((GeneralPath) shape).curveTo(294.75, 776.591, 294.466, 776.591,
                294.059, 776.591);
        ((GeneralPath) shape).curveTo(293.40198, 776.591, 293.38498, 776.393,
                293.38498, 775.771);
        ((GeneralPath) shape).lineTo(295.47797, 775.771);
        ((GeneralPath) shape).curveTo(295.47797, 774.412, 295.30997, 774.039,
                294.05896, 774.039);
        ((GeneralPath) shape).curveTo(292.82996, 774.039, 292.65695, 774.527,
                292.65695, 775.645);
        ((GeneralPath) shape).curveTo(292.65695, 776.781, 292.89395, 777.138,
                294.05896, 777.138);
        ((GeneralPath) shape).curveTo(294.92795, 777.138, 295.47797, 777.092,
                295.47797, 776.166);
        ((GeneralPath) shape).lineTo(294.75, 776.166);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(298.615, 774.894);
        ((GeneralPath) shape).curveTo(298.615, 774.039, 297.901, 774.039,
                297.327, 774.039);
        ((GeneralPath) shape).curveTo(296.578, 774.039, 295.97598, 774.039,
                295.97598, 774.884);
        ((GeneralPath) shape).curveTo(295.97598, 775.638, 296.17398, 775.782,
                297.26398, 775.8);
        ((GeneralPath) shape).curveTo(297.96097, 775.811, 297.97797, 775.941,
                297.97797, 776.198);
        ((GeneralPath) shape).curveTo(297.97797, 776.592, 297.73495, 776.592,
                297.31796, 776.592);
        ((GeneralPath) shape).curveTo(296.80295, 776.592, 296.70395, 776.542,
                296.70395, 776.11395);
        ((GeneralPath) shape).lineTo(295.97595, 776.11395);
        ((GeneralPath) shape).curveTo(295.97595, 777.13794, 296.51794,
                777.13794, 297.32394, 777.13794);
        ((GeneralPath) shape).curveTo(298.07495, 777.13794, 298.70593,
                777.05396, 298.70593, 776.19794);
        ((GeneralPath) shape).curveTo(298.70593, 775.24097, 298.07294,
                775.29395, 297.38794, 775.25995);
        ((GeneralPath) shape).curveTo(296.78094, 775.23193, 296.70395, 775.22,
                296.70395, 774.928);
        ((GeneralPath) shape).curveTo(296.70395, 774.54, 296.95996, 774.54,
                297.33096, 774.54);
        ((GeneralPath) shape).curveTo(297.70395, 774.54, 297.88696, 774.54,
                297.88696, 774.894);
        ((GeneralPath) shape).lineTo(298.615, 774.894);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(302.18, 774.949);
        ((GeneralPath) shape).lineTo(302.18, 773.479);
        ((GeneralPath) shape).lineTo(303.281, 773.479);
        ((GeneralPath) shape).curveTo(303.91202, 773.479, 304.0, 773.648,
                304.0, 774.24603);
        ((GeneralPath) shape).curveTo(304.0, 774.841, 303.883, 774.94904,
                303.281, 774.94904);
        ((GeneralPath) shape).lineTo(302.18, 774.94904);
        ((GeneralPath) shape).moveTo(301.361, 777.138);
        ((GeneralPath) shape).lineTo(302.18, 777.138);
        ((GeneralPath) shape).lineTo(302.18, 775.679);
        ((GeneralPath) shape).lineTo(303.27, 775.679);
        ((GeneralPath) shape).curveTo(304.40598, 775.679, 304.818, 775.46704,
                304.818, 774.24603);
        ((GeneralPath) shape).curveTo(304.818, 773.03705, 304.439, 772.76105,
                303.28, 772.76105);
        ((GeneralPath) shape).lineTo(301.36, 772.76105);
        ((GeneralPath) shape).lineTo(301.36, 777.138);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(305.265, 774.038);
        ((GeneralPath) shape).lineTo(305.265, 777.138);
        ((GeneralPath) shape).lineTo(305.993, 777.138);
        ((GeneralPath) shape).lineTo(305.993, 775.246);
        ((GeneralPath) shape).curveTo(305.993, 774.849, 306.12802, 774.59796,
                306.592, 774.59796);
        ((GeneralPath) shape).curveTo(306.963, 774.59796, 306.99402, 774.779,
                306.99402, 775.08795);
        ((GeneralPath) shape).lineTo(306.99402, 775.246);
        ((GeneralPath) shape).lineTo(307.72302, 775.246);
        ((GeneralPath) shape).lineTo(307.72302, 775.001);
        ((GeneralPath) shape).curveTo(307.72302, 774.423, 307.55502, 774.03796,
                306.86902, 774.03796);
        ((GeneralPath) shape).curveTo(306.48703, 774.03796, 306.15002,
                774.13495, 305.99402, 774.467);
        ((GeneralPath) shape).lineTo(305.99402, 774.03796);
        ((GeneralPath) shape).lineTo(305.265, 774.03796);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(309.466, 774.598);
        ((GeneralPath) shape).curveTo(310.09702, 774.598, 310.14902, 774.784,
                310.14902, 775.604);
        ((GeneralPath) shape).curveTo(310.14902, 776.412, 310.09702, 776.591,
                309.466, 776.591);
        ((GeneralPath) shape).curveTo(308.834, 776.591, 308.783, 776.411,
                308.783, 775.604);
        ((GeneralPath) shape).curveTo(308.783, 774.783, 308.834, 774.598,
                309.466, 774.598);
        ((GeneralPath) shape).moveTo(309.466, 774.038);
        ((GeneralPath) shape).curveTo(308.215, 774.038, 308.056, 774.41504,
                308.056, 775.598);
        ((GeneralPath) shape).curveTo(308.056, 776.765, 308.215, 777.138,
                309.466, 777.138);
        ((GeneralPath) shape).curveTo(310.717, 777.138, 310.876, 776.765,
                310.876, 775.598);
        ((GeneralPath) shape).curveTo(310.876, 774.415, 310.717, 774.038,
                309.466, 774.038);
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
        ((GeneralPath) shape).moveTo(313.537, 777.138);
        ((GeneralPath) shape).lineTo(314.266, 777.138);
        ((GeneralPath) shape).lineTo(314.266, 772.761);
        ((GeneralPath) shape).lineTo(313.537, 772.761);
        ((GeneralPath) shape).lineTo(313.537, 774.48096);
        ((GeneralPath) shape).lineTo(313.52, 774.48096);
        ((GeneralPath) shape).curveTo(313.36398, 774.108, 312.97498, 774.03894,
                312.60498, 774.03894);
        ((GeneralPath) shape).curveTo(311.56497, 774.03894, 311.44397,
                774.6129, 311.44397, 775.5059);
        ((GeneralPath) shape).curveTo(311.44397, 776.43695, 311.44397,
                777.1389, 312.60498, 777.1389);
        ((GeneralPath) shape).curveTo(313.02597, 777.1389, 313.35498, 777.0489,
                313.537, 776.6599);
        ((GeneralPath) shape).lineTo(313.537, 777.138);
        ((GeneralPath) shape).moveTo(312.82, 774.598);
        ((GeneralPath) shape).curveTo(313.45, 774.598, 313.53702, 774.85803,
                313.53702, 775.505);
        ((GeneralPath) shape).curveTo(313.53702, 776.228, 313.53702, 776.591,
                312.82, 776.591);
        ((GeneralPath) shape).curveTo(312.172, 776.591, 312.172, 776.192,
                312.172, 775.505);
        ((GeneralPath) shape).curveTo(312.172, 774.741, 312.317, 774.598,
                312.82, 774.598);
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
        ((GeneralPath) shape).moveTo(317.719, 777.138);
        ((GeneralPath) shape).lineTo(317.719, 774.038);
        ((GeneralPath) shape).lineTo(316.991, 774.038);
        ((GeneralPath) shape).lineTo(316.991, 775.815);
        ((GeneralPath) shape).curveTo(316.991, 776.349, 316.807, 776.59,
                316.236, 776.59);
        ((GeneralPath) shape).curveTo(315.757, 776.59, 315.716, 776.42004,
                315.716, 775.981);
        ((GeneralPath) shape).lineTo(315.716, 774.038);
        ((GeneralPath) shape).lineTo(314.988, 774.038);
        ((GeneralPath) shape).lineTo(314.988, 776.25604);
        ((GeneralPath) shape).curveTo(314.988, 776.92706, 315.397, 777.13806,
                316.01, 777.13806);
        ((GeneralPath) shape).curveTo(316.435, 777.13806, 316.819, 777.0411,
                316.989, 776.6451);
        ((GeneralPath) shape).lineTo(316.989, 777.13806);
        ((GeneralPath) shape).lineTo(317.719, 777.13806);
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
        ((GeneralPath) shape).moveTo(320.354, 775.955);
        ((GeneralPath) shape).curveTo(320.354, 776.486, 320.296, 776.591,
                319.704, 776.591);
        ((GeneralPath) shape).curveTo(319.08102, 776.591, 319.08102, 776.355,
                319.08102, 775.597);
        ((GeneralPath) shape).curveTo(319.08102, 774.818, 319.08102, 774.59796,
                319.704, 774.59796);
        ((GeneralPath) shape).curveTo(320.211, 774.59796, 320.354, 774.67896,
                320.354, 775.141);
        ((GeneralPath) shape).lineTo(321.083, 775.141);
        ((GeneralPath) shape).curveTo(321.083, 774.178, 320.604, 774.03796,
                319.704, 774.03796);
        ((GeneralPath) shape).curveTo(318.519, 774.03796, 318.35202, 774.52997,
                318.35202, 775.59796);
        ((GeneralPath) shape).curveTo(318.35202, 776.816, 318.59402, 777.13794,
                319.704, 777.13794);
        ((GeneralPath) shape).curveTo(320.725, 777.13794, 321.083, 776.94293,
                321.083, 775.95496);
        ((GeneralPath) shape).lineTo(320.354, 775.95496);
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
        ((GeneralPath) shape).moveTo(321.268, 774.585);
        ((GeneralPath) shape).lineTo(321.653, 774.585);
        ((GeneralPath) shape).lineTo(321.653, 776.182);
        ((GeneralPath) shape).curveTo(321.653, 776.953, 321.90002, 777.138,
                322.71402, 777.138);
        ((GeneralPath) shape).curveTo(323.51202, 777.138, 323.74603, 776.859,
                323.74603, 775.934);
        ((GeneralPath) shape).lineTo(323.10803, 775.934);
        ((GeneralPath) shape).curveTo(323.10803, 776.25903, 323.15402, 776.591,
                322.71304, 776.591);
        ((GeneralPath) shape).curveTo(322.38004, 776.591, 322.38004, 776.461,
                322.38004, 776.176);
        ((GeneralPath) shape).lineTo(322.38004, 774.585);
        ((GeneralPath) shape).lineTo(323.59003, 774.585);
        ((GeneralPath) shape).lineTo(323.59003, 774.038);
        ((GeneralPath) shape).lineTo(322.38004, 774.038);
        ((GeneralPath) shape).lineTo(322.38004, 773.33);
        ((GeneralPath) shape).lineTo(321.65204, 773.33);
        ((GeneralPath) shape).lineTo(321.65204, 774.038);
        ((GeneralPath) shape).lineTo(321.26703, 774.038);
        ((GeneralPath) shape).lineTo(321.26703, 774.585);
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
        ((GeneralPath) shape).moveTo(324.854, 772.761);
        ((GeneralPath) shape).lineTo(324.126, 772.761);
        ((GeneralPath) shape).lineTo(324.126, 773.386);
        ((GeneralPath) shape).lineTo(324.854, 773.386);
        ((GeneralPath) shape).lineTo(324.854, 772.761);
        ((GeneralPath) shape).moveTo(324.854, 774.038);
        ((GeneralPath) shape).lineTo(324.126, 774.038);
        ((GeneralPath) shape).lineTo(324.126, 777.138);
        ((GeneralPath) shape).lineTo(324.854, 777.138);
        ((GeneralPath) shape).lineTo(324.854, 774.038);
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
        ((GeneralPath) shape).moveTo(326.82, 774.598);
        ((GeneralPath) shape).curveTo(327.452, 774.598, 327.50302, 774.784,
                327.50302, 775.604);
        ((GeneralPath) shape).curveTo(327.50302, 776.412, 327.45203, 776.591,
                326.82, 776.591);
        ((GeneralPath) shape).curveTo(326.189, 776.591, 326.138, 776.411,
                326.138, 775.604);
        ((GeneralPath) shape).curveTo(326.139, 774.783, 326.189, 774.598,
                326.82, 774.598);
        ((GeneralPath) shape).moveTo(326.82, 774.038);
        ((GeneralPath) shape).curveTo(325.569, 774.038, 325.41, 774.41504,
                325.41, 775.598);
        ((GeneralPath) shape).curveTo(325.41, 776.765, 325.569, 777.138,
                326.82, 777.138);
        ((GeneralPath) shape).curveTo(328.07202, 777.138, 328.23102, 776.765,
                328.23102, 775.598);
        ((GeneralPath) shape).curveTo(328.231, 774.415, 328.072, 774.038,
                326.82, 774.038);
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
        ((GeneralPath) shape).moveTo(328.891, 774.038);
        ((GeneralPath) shape).lineTo(328.891, 777.138);
        ((GeneralPath) shape).lineTo(329.62, 777.138);
        ((GeneralPath) shape).lineTo(329.62, 775.45);
        ((GeneralPath) shape).curveTo(329.62, 774.90204, 329.705, 774.598,
                330.33398, 774.598);
        ((GeneralPath) shape).curveTo(330.797, 774.598, 330.89398, 774.75,
                330.89398, 775.19403);
        ((GeneralPath) shape).lineTo(330.89398, 777.138);
        ((GeneralPath) shape).lineTo(331.623, 777.138);
        ((GeneralPath) shape).lineTo(331.623, 775.118);
        ((GeneralPath) shape).curveTo(331.623, 774.371, 331.38098, 774.03796,
                330.586, 774.03796);
        ((GeneralPath) shape).curveTo(330.164, 774.03796, 329.804, 774.08295,
                329.644, 774.529);
        ((GeneralPath) shape).lineTo(329.622, 774.529);
        ((GeneralPath) shape).lineTo(329.622, 774.03796);
        ((GeneralPath) shape).lineTo(328.891, 774.03796);
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
        ((GeneralPath) shape).moveTo(332.497, 777.867);
        ((GeneralPath) shape).lineTo(332.64502, 777.867);
        ((GeneralPath) shape).curveTo(333.28302, 777.867, 333.28302, 777.515,
                333.28302, 777.038);
        ((GeneralPath) shape).lineTo(333.28302, 776.35004);
        ((GeneralPath) shape).lineTo(332.55502, 776.35004);
        ((GeneralPath) shape).lineTo(332.55502, 777.13806);
        ((GeneralPath) shape).lineTo(332.85403, 777.13806);
        ((GeneralPath) shape).curveTo(332.85403, 777.44305, 332.71704,
                777.50305, 332.49704, 777.50305);
        ((GeneralPath) shape).lineTo(332.49704, 777.867);
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
        ((GeneralPath) shape).moveTo(337.292, 772.761);
        ((GeneralPath) shape).lineTo(336.474, 772.761);
        ((GeneralPath) shape).lineTo(336.474, 777.138);
        ((GeneralPath) shape).lineTo(339.331, 777.138);
        ((GeneralPath) shape).lineTo(339.331, 776.42);
        ((GeneralPath) shape).lineTo(337.292, 776.42);
        ((GeneralPath) shape).lineTo(337.292, 772.761);
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
        ((GeneralPath) shape).moveTo(340.583, 772.761);
        ((GeneralPath) shape).lineTo(339.764, 772.761);
        ((GeneralPath) shape).lineTo(339.764, 777.138);
        ((GeneralPath) shape).lineTo(342.622, 777.138);
        ((GeneralPath) shape).lineTo(342.622, 776.42);
        ((GeneralPath) shape).lineTo(340.583, 776.42);
        ((GeneralPath) shape).lineTo(340.583, 772.761);
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
        ((GeneralPath) shape).moveTo(346.513, 774.246);
        ((GeneralPath) shape).lineTo(346.513, 774.011);
        ((GeneralPath) shape).curveTo(346.453, 772.761, 345.808, 772.761,
                344.72, 772.761);
        ((GeneralPath) shape).curveTo(343.42, 772.761, 342.872, 773.074,
                342.872, 774.458);
        ((GeneralPath) shape).lineTo(342.872, 775.441);
        ((GeneralPath) shape).curveTo(342.872, 776.696, 343.151, 777.196,
                344.72, 777.13696);
        ((GeneralPath) shape).curveTo(345.802, 777.131, 346.513, 777.13696,
                346.513, 775.842);
        ((GeneralPath) shape).lineTo(346.513, 775.561);
        ((GeneralPath) shape).lineTo(345.694, 775.561);
        ((GeneralPath) shape).lineTo(345.694, 775.795);
        ((GeneralPath) shape).curveTo(345.694, 776.407, 345.426, 776.407,
                344.72, 776.407);
        ((GeneralPath) shape).curveTo(343.801, 776.407, 343.692, 776.245,
                343.692, 775.406);
        ((GeneralPath) shape).lineTo(343.692, 774.45703);
        ((GeneralPath) shape).curveTo(343.692, 773.61505, 343.843, 773.478,
                344.72, 773.478);
        ((GeneralPath) shape).curveTo(345.486, 773.478, 345.694, 773.512,
                345.694, 774.00903);
        ((GeneralPath) shape).lineTo(345.694, 774.244);
        ((GeneralPath) shape).lineTo(346.513, 774.244);
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
        shape = new Rectangle2D.Double(347.2900085449219, 776.3389892578125,
                0.7279999852180481, 0.7990000247955322);
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
        ((GeneralPath) shape).moveTo(352.027, 774.949);
        ((GeneralPath) shape).lineTo(352.027, 773.479);
        ((GeneralPath) shape).lineTo(353.12802, 773.479);
        ((GeneralPath) shape).curveTo(353.75903, 773.479, 353.84702, 773.648,
                353.84702, 774.24603);
        ((GeneralPath) shape).curveTo(353.84702, 774.841, 353.73102, 774.94904,
                353.12802, 774.94904);
        ((GeneralPath) shape).lineTo(352.027, 774.94904);
        ((GeneralPath) shape).moveTo(351.208, 777.138);
        ((GeneralPath) shape).lineTo(352.027, 777.138);
        ((GeneralPath) shape).lineTo(352.027, 775.679);
        ((GeneralPath) shape).lineTo(353.117, 775.679);
        ((GeneralPath) shape).curveTo(354.254, 775.679, 354.66602, 775.46704,
                354.66602, 774.24603);
        ((GeneralPath) shape).curveTo(354.66602, 773.03705, 354.286, 772.76105,
                353.12802, 772.76105);
        ((GeneralPath) shape).lineTo(351.208, 772.76105);
        ((GeneralPath) shape).lineTo(351.208, 777.138);
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
        ((GeneralPath) shape).moveTo(355.84, 775.314);
        ((GeneralPath) shape).curveTo(355.84, 774.74005, 355.88098, 774.59705,
                356.51498, 774.59705);
        ((GeneralPath) shape).curveTo(357.11298, 774.59705, 357.205, 774.64703,
                357.205, 775.314);
        ((GeneralPath) shape).lineTo(355.84, 775.314);
        ((GeneralPath) shape).moveTo(357.205, 776.166);
        ((GeneralPath) shape).curveTo(357.205, 776.591, 356.921, 776.591,
                356.51498, 776.591);
        ((GeneralPath) shape).curveTo(355.85797, 776.591, 355.84, 776.393,
                355.84, 775.771);
        ((GeneralPath) shape).lineTo(357.93298, 775.771);
        ((GeneralPath) shape).curveTo(357.93298, 774.412, 357.766, 774.039,
                356.51498, 774.039);
        ((GeneralPath) shape).curveTo(355.28598, 774.039, 355.11298, 774.527,
                355.11298, 775.645);
        ((GeneralPath) shape).curveTo(355.11298, 776.781, 355.34998, 777.138,
                356.51498, 777.138);
        ((GeneralPath) shape).curveTo(357.383, 777.138, 357.93298, 777.092,
                357.93298, 776.166);
        ((GeneralPath) shape).lineTo(357.205, 776.166);
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
        ((GeneralPath) shape).moveTo(358.432, 774.038);
        ((GeneralPath) shape).lineTo(358.432, 777.138);
        ((GeneralPath) shape).lineTo(359.161, 777.138);
        ((GeneralPath) shape).lineTo(359.161, 775.246);
        ((GeneralPath) shape).curveTo(359.161, 774.849, 359.29602, 774.59796,
                359.76, 774.59796);
        ((GeneralPath) shape).curveTo(360.131, 774.59796, 360.16202, 774.779,
                360.16202, 775.08795);
        ((GeneralPath) shape).lineTo(360.16202, 775.246);
        ((GeneralPath) shape).lineTo(360.89, 775.246);
        ((GeneralPath) shape).lineTo(360.89, 775.001);
        ((GeneralPath) shape).curveTo(360.89, 774.423, 360.72202, 774.03796,
                360.036, 774.03796);
        ((GeneralPath) shape).curveTo(359.655, 774.03796, 359.31802, 774.13495,
                359.16202, 774.467);
        ((GeneralPath) shape).lineTo(359.16202, 774.03796);
        ((GeneralPath) shape).lineTo(358.432, 774.03796);
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
        ((GeneralPath) shape).moveTo(361.313, 774.038);
        ((GeneralPath) shape).lineTo(361.313, 777.138);
        ((GeneralPath) shape).lineTo(362.041, 777.138);
        ((GeneralPath) shape).lineTo(362.041, 775.41);
        ((GeneralPath) shape).curveTo(362.041, 774.86694, 362.16498, 774.597,
                362.792, 774.597);
        ((GeneralPath) shape).curveTo(363.21698, 774.597, 363.315, 774.74896,
                363.315, 775.134);
        ((GeneralPath) shape).lineTo(363.315, 777.13696);
        ((GeneralPath) shape).lineTo(364.043, 777.13696);
        ((GeneralPath) shape).lineTo(364.043, 775.40894);
        ((GeneralPath) shape).curveTo(364.043, 774.8659, 364.157, 774.59595,
                364.74, 774.59595);
        ((GeneralPath) shape).curveTo(365.136, 774.59595, 365.22598, 774.7479,
                365.22598, 775.13293);
        ((GeneralPath) shape).lineTo(365.22598, 777.1359);
        ((GeneralPath) shape).lineTo(365.95398, 777.1359);
        ((GeneralPath) shape).lineTo(365.95398, 775.0629);
        ((GeneralPath) shape).curveTo(365.95398, 774.3109, 365.69897, 774.0369,
                364.929, 774.0369);
        ((GeneralPath) shape).curveTo(364.529, 774.0369, 364.11697, 774.15295,
                363.97998, 774.57794);
        ((GeneralPath) shape).lineTo(363.95798, 774.57794);
        ((GeneralPath) shape).curveTo(363.87598, 774.13495, 363.438, 774.0369,
                363.05798, 774.0369);
        ((GeneralPath) shape).curveTo(362.65097, 774.0369, 362.214, 774.1239,
                362.042, 774.50995);
        ((GeneralPath) shape).lineTo(362.042, 774.0369);
        ((GeneralPath) shape).lineTo(361.313, 774.0369);
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
        ((GeneralPath) shape).moveTo(367.422, 772.761);
        ((GeneralPath) shape).lineTo(366.693, 772.761);
        ((GeneralPath) shape).lineTo(366.693, 773.386);
        ((GeneralPath) shape).lineTo(367.422, 773.386);
        ((GeneralPath) shape).lineTo(367.422, 772.761);
        ((GeneralPath) shape).moveTo(367.422, 774.038);
        ((GeneralPath) shape).lineTo(366.693, 774.038);
        ((GeneralPath) shape).lineTo(366.693, 777.138);
        ((GeneralPath) shape).lineTo(367.422, 777.138);
        ((GeneralPath) shape).lineTo(367.422, 774.038);
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
        ((GeneralPath) shape).moveTo(370.616, 774.894);
        ((GeneralPath) shape).curveTo(370.616, 774.039, 369.902, 774.039,
                369.328, 774.039);
        ((GeneralPath) shape).curveTo(368.578, 774.039, 367.976, 774.039,
                367.976, 774.884);
        ((GeneralPath) shape).curveTo(367.976, 775.638, 368.174, 775.782,
                369.264, 775.8);
        ((GeneralPath) shape).curveTo(369.961, 775.811, 369.978, 775.941,
                369.978, 776.198);
        ((GeneralPath) shape).curveTo(369.978, 776.592, 369.735, 776.592,
                369.319, 776.592);
        ((GeneralPath) shape).curveTo(368.804, 776.592, 368.705, 776.542,
                368.705, 776.11395);
        ((GeneralPath) shape).lineTo(367.97598, 776.11395);
        ((GeneralPath) shape).curveTo(367.97598, 777.13794, 368.51797,
                777.13794, 369.32498, 777.13794);
        ((GeneralPath) shape).curveTo(370.07498, 777.13794, 370.70697,
                777.05396, 370.70697, 776.19794);
        ((GeneralPath) shape).curveTo(370.70697, 775.24097, 370.07397,
                775.29395, 369.38898, 775.25995);
        ((GeneralPath) shape).curveTo(368.783, 775.23193, 368.705, 775.22,
                368.705, 774.928);
        ((GeneralPath) shape).curveTo(368.705, 774.54, 368.961, 774.54,
                369.332, 774.54);
        ((GeneralPath) shape).curveTo(369.705, 774.54, 369.888, 774.54,
                369.888, 774.894);
        ((GeneralPath) shape).lineTo(370.616, 774.894);
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
        ((GeneralPath) shape).moveTo(373.86, 774.894);
        ((GeneralPath) shape).curveTo(373.86, 774.039, 373.146, 774.039,
                372.572, 774.039);
        ((GeneralPath) shape).curveTo(371.822, 774.039, 371.22098, 774.039,
                371.22098, 774.884);
        ((GeneralPath) shape).curveTo(371.22098, 775.638, 371.41797, 775.782,
                372.50897, 775.8);
        ((GeneralPath) shape).curveTo(373.205, 775.811, 373.22296, 775.941,
                373.22296, 776.198);
        ((GeneralPath) shape).curveTo(373.22296, 776.592, 372.97995, 776.592,
                372.56296, 776.592);
        ((GeneralPath) shape).curveTo(372.04794, 776.592, 371.94894, 776.542,
                371.94894, 776.11395);
        ((GeneralPath) shape).lineTo(371.22095, 776.11395);
        ((GeneralPath) shape).curveTo(371.22095, 777.13794, 371.76193,
                777.13794, 372.56894, 777.13794);
        ((GeneralPath) shape).curveTo(373.31995, 777.13794, 373.95093,
                777.05396, 373.95093, 776.19794);
        ((GeneralPath) shape).curveTo(373.95093, 775.24097, 373.31793,
                775.29395, 372.63293, 775.25995);
        ((GeneralPath) shape).curveTo(372.02695, 775.23193, 371.94894, 775.22,
                371.94894, 774.928);
        ((GeneralPath) shape).curveTo(371.94894, 774.54, 372.20496, 774.54,
                372.57596, 774.54);
        ((GeneralPath) shape).curveTo(372.94894, 774.54, 373.13196, 774.54,
                373.13196, 774.894);
        ((GeneralPath) shape).lineTo(373.86, 774.894);
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
        ((GeneralPath) shape).moveTo(375.284, 772.761);
        ((GeneralPath) shape).lineTo(374.555, 772.761);
        ((GeneralPath) shape).lineTo(374.555, 773.386);
        ((GeneralPath) shape).lineTo(375.284, 773.386);
        ((GeneralPath) shape).lineTo(375.284, 772.761);
        ((GeneralPath) shape).moveTo(375.284, 774.038);
        ((GeneralPath) shape).lineTo(374.555, 774.038);
        ((GeneralPath) shape).lineTo(374.555, 777.138);
        ((GeneralPath) shape).lineTo(375.284, 777.138);
        ((GeneralPath) shape).lineTo(375.284, 774.038);
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
        ((GeneralPath) shape).moveTo(377.25, 774.598);
        ((GeneralPath) shape).curveTo(377.881, 774.598, 377.933, 774.784,
                377.933, 775.604);
        ((GeneralPath) shape).curveTo(377.933, 776.412, 377.881, 776.591,
                377.25, 776.591);
        ((GeneralPath) shape).curveTo(376.618, 776.591, 376.567, 776.411,
                376.567, 775.604);
        ((GeneralPath) shape).curveTo(376.567, 774.783, 376.618, 774.598,
                377.25, 774.598);
        ((GeneralPath) shape).moveTo(377.25, 774.038);
        ((GeneralPath) shape).curveTo(375.999, 774.038, 375.839, 774.41504,
                375.839, 775.598);
        ((GeneralPath) shape).curveTo(375.839, 776.765, 375.999, 777.138,
                377.25, 777.138);
        ((GeneralPath) shape).curveTo(378.501, 777.138, 378.66, 776.765,
                378.66, 775.598);
        ((GeneralPath) shape).curveTo(378.66, 774.415, 378.501, 774.038,
                377.25, 774.038);
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
        ((GeneralPath) shape).moveTo(379.32, 774.038);
        ((GeneralPath) shape).lineTo(379.32, 777.138);
        ((GeneralPath) shape).lineTo(380.048, 777.138);
        ((GeneralPath) shape).lineTo(380.048, 775.45);
        ((GeneralPath) shape).curveTo(380.048, 774.90204, 380.133, 774.598,
                380.762, 774.598);
        ((GeneralPath) shape).curveTo(381.22598, 774.598, 381.323, 774.75,
                381.323, 775.19403);
        ((GeneralPath) shape).lineTo(381.323, 777.138);
        ((GeneralPath) shape).lineTo(382.051, 777.138);
        ((GeneralPath) shape).lineTo(382.051, 775.118);
        ((GeneralPath) shape).curveTo(382.051, 774.371, 381.809, 774.03796,
                381.01498, 774.03796);
        ((GeneralPath) shape).curveTo(380.59198, 774.03796, 380.23297,
                774.08295, 380.072, 774.529);
        ((GeneralPath) shape).lineTo(380.05, 774.529);
        ((GeneralPath) shape).lineTo(380.05, 774.03796);
        ((GeneralPath) shape).lineTo(379.32, 774.03796);
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
        ((GeneralPath) shape).moveTo(384.469, 774.585);
        ((GeneralPath) shape).lineTo(384.853, 774.585);
        ((GeneralPath) shape).lineTo(384.853, 776.182);
        ((GeneralPath) shape).curveTo(384.853, 776.953, 385.1, 777.138,
                385.914, 777.138);
        ((GeneralPath) shape).curveTo(386.712, 777.138, 386.946, 776.859,
                386.946, 775.934);
        ((GeneralPath) shape).lineTo(386.30902, 775.934);
        ((GeneralPath) shape).curveTo(386.30902, 776.25903, 386.35403, 776.591,
                385.91302, 776.591);
        ((GeneralPath) shape).curveTo(385.58002, 776.591, 385.58002, 776.461,
                385.58002, 776.176);
        ((GeneralPath) shape).lineTo(385.58002, 774.585);
        ((GeneralPath) shape).lineTo(386.79102, 774.585);
        ((GeneralPath) shape).lineTo(386.79102, 774.038);
        ((GeneralPath) shape).lineTo(385.58002, 774.038);
        ((GeneralPath) shape).lineTo(385.58002, 773.33);
        ((GeneralPath) shape).lineTo(384.85202, 773.33);
        ((GeneralPath) shape).lineTo(384.85202, 774.038);
        ((GeneralPath) shape).lineTo(384.46802, 774.038);
        ((GeneralPath) shape).lineTo(384.46802, 774.585);
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
        ((GeneralPath) shape).moveTo(388.647, 774.598);
        ((GeneralPath) shape).curveTo(389.278, 774.598, 389.33002, 774.784,
                389.33002, 775.604);
        ((GeneralPath) shape).curveTo(389.33002, 776.412, 389.278, 776.591,
                388.647, 776.591);
        ((GeneralPath) shape).curveTo(388.016, 776.591, 387.964, 776.411,
                387.964, 775.604);
        ((GeneralPath) shape).curveTo(387.965, 774.783, 388.017, 774.598,
                388.647, 774.598);
        ((GeneralPath) shape).moveTo(388.647, 774.038);
        ((GeneralPath) shape).curveTo(387.396, 774.038, 387.237, 774.41504,
                387.237, 775.598);
        ((GeneralPath) shape).curveTo(387.237, 776.765, 387.396, 777.138,
                388.647, 777.138);
        ((GeneralPath) shape).curveTo(389.898, 777.138, 390.057, 776.765,
                390.057, 775.598);
        ((GeneralPath) shape).curveTo(390.058, 774.415, 389.898, 774.038,
                388.647, 774.038);
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
        ((GeneralPath) shape).moveTo(393.495, 774.038);
        ((GeneralPath) shape).lineTo(392.767, 774.038);
        ((GeneralPath) shape).lineTo(392.767, 778.50604);
        ((GeneralPath) shape).lineTo(393.495, 778.50604);
        ((GeneralPath) shape).lineTo(393.495, 776.705);
        ((GeneralPath) shape).lineTo(393.523, 776.705);
        ((GeneralPath) shape).curveTo(393.678, 777.06903, 394.062, 777.138,
                394.42902, 777.138);
        ((GeneralPath) shape).curveTo(395.467, 777.138, 395.587, 776.56903,
                395.587, 775.684);
        ((GeneralPath) shape).curveTo(395.587, 774.753, 395.587, 774.038,
                394.42902, 774.038);
        ((GeneralPath) shape).curveTo(394.00803, 774.038, 393.677, 774.13,
                393.49402, 774.52905);
        ((GeneralPath) shape).lineTo(393.49402, 774.038);
        ((GeneralPath) shape).moveTo(394.218, 776.591);
        ((GeneralPath) shape).curveTo(393.594, 776.591, 393.495, 776.331,
                393.495, 775.684);
        ((GeneralPath) shape).curveTo(393.495, 774.961, 393.495, 774.598,
                394.218, 774.598);
        ((GeneralPath) shape).curveTo(394.861, 774.598, 394.861, 774.99603,
                394.861, 775.684);
        ((GeneralPath) shape).curveTo(394.86, 776.447, 394.717, 776.591,
                394.218, 776.591);
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
        ((GeneralPath) shape).moveTo(396.967, 772.761);
        ((GeneralPath) shape).lineTo(396.238, 772.761);
        ((GeneralPath) shape).lineTo(396.238, 777.138);
        ((GeneralPath) shape).lineTo(396.967, 777.138);
        ((GeneralPath) shape).lineTo(396.967, 775.45);
        ((GeneralPath) shape).curveTo(396.967, 774.90204, 397.052, 774.598,
                397.681, 774.598);
        ((GeneralPath) shape).curveTo(398.144, 774.598, 398.241, 774.75,
                398.241, 775.19403);
        ((GeneralPath) shape).lineTo(398.241, 777.138);
        ((GeneralPath) shape).lineTo(398.97, 777.138);
        ((GeneralPath) shape).lineTo(398.97, 775.118);
        ((GeneralPath) shape).curveTo(398.97, 774.371, 398.728, 774.03796,
                397.933, 774.03796);
        ((GeneralPath) shape).curveTo(397.51102, 774.03796, 397.151, 774.08496,
                396.99103, 774.53296);
        ((GeneralPath) shape).lineTo(396.96902, 774.53296);
        ((GeneralPath) shape).lineTo(396.96902, 772.761);
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
        ((GeneralPath) shape).moveTo(401.041, 774.598);
        ((GeneralPath) shape).curveTo(401.672, 774.598, 401.724, 774.784,
                401.724, 775.604);
        ((GeneralPath) shape).curveTo(401.724, 776.412, 401.672, 776.591,
                401.041, 776.591);
        ((GeneralPath) shape).curveTo(400.409, 776.591, 400.35797, 776.411,
                400.35797, 775.604);
        ((GeneralPath) shape).curveTo(400.358, 774.783, 400.409, 774.598,
                401.041, 774.598);
        ((GeneralPath) shape).moveTo(401.041, 774.038);
        ((GeneralPath) shape).curveTo(399.78897, 774.038, 399.62997, 774.41504,
                399.62997, 775.598);
        ((GeneralPath) shape).curveTo(399.62997, 776.765, 399.78897, 777.138,
                401.041, 777.138);
        ((GeneralPath) shape).curveTo(402.292, 777.138, 402.451, 776.765,
                402.451, 775.598);
        ((GeneralPath) shape).curveTo(402.451, 774.415, 402.292, 774.038,
                401.041, 774.038);
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
        ((GeneralPath) shape).moveTo(402.727, 774.585);
        ((GeneralPath) shape).lineTo(403.111, 774.585);
        ((GeneralPath) shape).lineTo(403.111, 776.182);
        ((GeneralPath) shape).curveTo(403.111, 776.953, 403.358, 777.138,
                404.172, 777.138);
        ((GeneralPath) shape).curveTo(404.97, 777.138, 405.204, 776.859,
                405.204, 775.934);
        ((GeneralPath) shape).lineTo(404.56702, 775.934);
        ((GeneralPath) shape).curveTo(404.56702, 776.25903, 404.61203, 776.591,
                404.17102, 776.591);
        ((GeneralPath) shape).curveTo(403.838, 776.591, 403.838, 776.461,
                403.838, 776.176);
        ((GeneralPath) shape).lineTo(403.838, 774.585);
        ((GeneralPath) shape).lineTo(405.049, 774.585);
        ((GeneralPath) shape).lineTo(405.049, 774.038);
        ((GeneralPath) shape).lineTo(403.838, 774.038);
        ((GeneralPath) shape).lineTo(403.838, 773.33);
        ((GeneralPath) shape).lineTo(403.11002, 773.33);
        ((GeneralPath) shape).lineTo(403.11002, 774.038);
        ((GeneralPath) shape).lineTo(402.726, 774.038);
        ((GeneralPath) shape).lineTo(402.726, 774.585);
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
        ((GeneralPath) shape).moveTo(406.905, 774.598);
        ((GeneralPath) shape).curveTo(407.536, 774.598, 407.588, 774.784,
                407.588, 775.604);
        ((GeneralPath) shape).curveTo(407.588, 776.412, 407.536, 776.591,
                406.905, 776.591);
        ((GeneralPath) shape).curveTo(406.274, 776.591, 406.222, 776.411,
                406.222, 775.604);
        ((GeneralPath) shape).curveTo(406.223, 774.783, 406.274, 774.598,
                406.905, 774.598);
        ((GeneralPath) shape).moveTo(406.905, 774.038);
        ((GeneralPath) shape).curveTo(405.654, 774.038, 405.495, 774.41504,
                405.495, 775.598);
        ((GeneralPath) shape).curveTo(405.495, 776.765, 405.654, 777.138,
                406.905, 777.138);
        ((GeneralPath) shape).curveTo(408.156, 777.138, 408.315, 776.765,
                408.315, 775.598);
        ((GeneralPath) shape).curveTo(408.315, 774.415, 408.156, 774.038,
                406.905, 774.038);
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
        ((GeneralPath) shape).moveTo(410.887, 775.955);
        ((GeneralPath) shape).curveTo(410.887, 776.486, 410.828, 776.591,
                410.236, 776.591);
        ((GeneralPath) shape).curveTo(409.613, 776.591, 409.613, 776.355,
                409.613, 775.597);
        ((GeneralPath) shape).curveTo(409.613, 774.818, 409.613, 774.59796,
                410.236, 774.59796);
        ((GeneralPath) shape).curveTo(410.744, 774.59796, 410.887, 774.67896,
                410.887, 775.141);
        ((GeneralPath) shape).lineTo(411.615, 775.141);
        ((GeneralPath) shape).curveTo(411.615, 774.178, 411.136, 774.03796,
                410.236, 774.03796);
        ((GeneralPath) shape).curveTo(409.051, 774.03796, 408.88498, 774.52997,
                408.88498, 775.59796);
        ((GeneralPath) shape).curveTo(408.88498, 776.816, 409.12598, 777.13794,
                410.236, 777.13794);
        ((GeneralPath) shape).curveTo(411.257, 777.13794, 411.615, 776.94293,
                411.615, 775.95496);
        ((GeneralPath) shape).lineTo(410.887, 775.95496);
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
        ((GeneralPath) shape).moveTo(413.504, 774.598);
        ((GeneralPath) shape).curveTo(414.135, 774.598, 414.187, 774.784,
                414.187, 775.604);
        ((GeneralPath) shape).curveTo(414.187, 776.412, 414.135, 776.591,
                413.504, 776.591);
        ((GeneralPath) shape).curveTo(412.873, 776.591, 412.82098, 776.411,
                412.82098, 775.604);
        ((GeneralPath) shape).curveTo(412.821, 774.783, 412.873, 774.598,
                413.504, 774.598);
        ((GeneralPath) shape).moveTo(413.504, 774.038);
        ((GeneralPath) shape).curveTo(412.253, 774.038, 412.094, 774.41504,
                412.094, 775.598);
        ((GeneralPath) shape).curveTo(412.094, 776.765, 412.253, 777.138,
                413.504, 777.138);
        ((GeneralPath) shape).curveTo(414.755, 777.138, 414.914, 776.765,
                414.914, 775.598);
        ((GeneralPath) shape).curveTo(414.914, 774.415, 414.755, 774.038,
                413.504, 774.038);
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
        ((GeneralPath) shape).moveTo(416.302, 774.038);
        ((GeneralPath) shape).lineTo(415.574, 774.038);
        ((GeneralPath) shape).lineTo(415.574, 778.50604);
        ((GeneralPath) shape).lineTo(416.302, 778.50604);
        ((GeneralPath) shape).lineTo(416.302, 776.705);
        ((GeneralPath) shape).lineTo(416.33002, 776.705);
        ((GeneralPath) shape).curveTo(416.48502, 777.06903, 416.86902, 777.138,
                417.23602, 777.138);
        ((GeneralPath) shape).curveTo(418.27402, 777.138, 418.394, 776.56903,
                418.394, 775.684);
        ((GeneralPath) shape).curveTo(418.394, 774.753, 418.394, 774.038,
                417.23602, 774.038);
        ((GeneralPath) shape).curveTo(416.81503, 774.038, 416.484, 774.13,
                416.30103, 774.52905);
        ((GeneralPath) shape).lineTo(416.30103, 774.038);
        ((GeneralPath) shape).moveTo(417.024, 776.591);
        ((GeneralPath) shape).curveTo(416.4, 776.591, 416.301, 776.331,
                416.301, 775.684);
        ((GeneralPath) shape).curveTo(416.301, 774.961, 416.301, 774.598,
                417.024, 774.598);
        ((GeneralPath) shape).curveTo(417.667, 774.598, 417.667, 774.99603,
                417.667, 775.684);
        ((GeneralPath) shape).curveTo(417.667, 776.447, 417.523, 776.591,
                417.024, 776.591);
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
        ((GeneralPath) shape).moveTo(421.593, 774.038);
        ((GeneralPath) shape).lineTo(420.839, 774.038);
        ((GeneralPath) shape).lineTo(420.23898, 776.67804);
        ((GeneralPath) shape).lineTo(420.227, 776.67804);
        ((GeneralPath) shape).lineTo(419.44498, 774.038);
        ((GeneralPath) shape).lineTo(418.67996, 774.038);
        ((GeneralPath) shape).lineTo(419.69797, 777.138);
        ((GeneralPath) shape).lineTo(420.05597, 777.138);
        ((GeneralPath) shape).curveTo(419.99097, 777.503, 419.92096, 777.958,
                419.45596, 777.958);
        ((GeneralPath) shape).curveTo(419.40295, 777.958, 419.34998, 777.945,
                419.29697, 777.94);
        ((GeneralPath) shape).lineTo(419.29697, 778.505);
        ((GeneralPath) shape).curveTo(419.40295, 778.505, 419.50897, 778.505,
                419.61496, 778.505);
        ((GeneralPath) shape).curveTo(420.47897, 778.505, 420.61996, 777.956,
                420.79095, 777.255);
        ((GeneralPath) shape).lineTo(421.593, 774.038);
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
        ((GeneralPath) shape).moveTo(425.574, 774.038);
        ((GeneralPath) shape).lineTo(424.83002, 774.038);
        ((GeneralPath) shape).lineTo(424.83002, 773.864);
        ((GeneralPath) shape).curveTo(424.83002, 773.51, 424.83002, 773.321,
                425.33002, 773.321);
        ((GeneralPath) shape).lineTo(425.552, 773.321);
        ((GeneralPath) shape).lineTo(425.552, 772.76);
        ((GeneralPath) shape).lineTo(425.075, 772.76);
        ((GeneralPath) shape).curveTo(424.27502, 772.76, 424.101, 773.06104,
                424.101, 773.77704);
        ((GeneralPath) shape).lineTo(424.101, 774.038);
        ((GeneralPath) shape).lineTo(423.67, 774.038);
        ((GeneralPath) shape).lineTo(423.67, 774.585);
        ((GeneralPath) shape).lineTo(424.101, 774.585);
        ((GeneralPath) shape).lineTo(424.101, 777.138);
        ((GeneralPath) shape).lineTo(424.83002, 777.138);
        ((GeneralPath) shape).lineTo(424.83002, 774.585);
        ((GeneralPath) shape).lineTo(425.574, 774.585);
        ((GeneralPath) shape).lineTo(425.574, 774.038);
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
        ((GeneralPath) shape).moveTo(427.266, 774.598);
        ((GeneralPath) shape).curveTo(427.897, 774.598, 427.949, 774.784,
                427.949, 775.604);
        ((GeneralPath) shape).curveTo(427.949, 776.412, 427.897, 776.591,
                427.266, 776.591);
        ((GeneralPath) shape).curveTo(426.634, 776.591, 426.58298, 776.411,
                426.58298, 775.604);
        ((GeneralPath) shape).curveTo(426.583, 774.783, 426.634, 774.598,
                427.266, 774.598);
        ((GeneralPath) shape).moveTo(427.266, 774.038);
        ((GeneralPath) shape).curveTo(426.01498, 774.038, 425.856, 774.41504,
                425.856, 775.598);
        ((GeneralPath) shape).curveTo(425.856, 776.765, 426.01498, 777.138,
                427.266, 777.138);
        ((GeneralPath) shape).curveTo(428.517, 777.138, 428.676, 776.765,
                428.676, 775.598);
        ((GeneralPath) shape).curveTo(428.676, 774.415, 428.517, 774.038,
                427.266, 774.038);
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
        ((GeneralPath) shape).moveTo(429.244, 774.038);
        ((GeneralPath) shape).lineTo(429.244, 777.138);
        ((GeneralPath) shape).lineTo(429.973, 777.138);
        ((GeneralPath) shape).lineTo(429.973, 775.246);
        ((GeneralPath) shape).curveTo(429.973, 774.849, 430.108, 774.59796,
                430.572, 774.59796);
        ((GeneralPath) shape).curveTo(430.943, 774.59796, 430.974, 774.779,
                430.974, 775.08795);
        ((GeneralPath) shape).lineTo(430.974, 775.246);
        ((GeneralPath) shape).lineTo(431.702, 775.246);
        ((GeneralPath) shape).lineTo(431.702, 775.001);
        ((GeneralPath) shape).curveTo(431.702, 774.423, 431.534, 774.03796,
                430.848, 774.03796);
        ((GeneralPath) shape).curveTo(430.46698, 774.03796, 430.13, 774.13495,
                429.974, 774.467);
        ((GeneralPath) shape).lineTo(429.974, 774.03796);
        ((GeneralPath) shape).lineTo(429.244, 774.03796);
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
        ((GeneralPath) shape).moveTo(434.856, 774.038);
        ((GeneralPath) shape).lineTo(434.128, 774.038);
        ((GeneralPath) shape).lineTo(434.128, 778.50604);
        ((GeneralPath) shape).lineTo(434.856, 778.50604);
        ((GeneralPath) shape).lineTo(434.856, 776.705);
        ((GeneralPath) shape).lineTo(434.884, 776.705);
        ((GeneralPath) shape).curveTo(435.039, 777.06903, 435.423, 777.138,
                435.79, 777.138);
        ((GeneralPath) shape).curveTo(436.828, 777.138, 436.948, 776.56903,
                436.948, 775.684);
        ((GeneralPath) shape).curveTo(436.948, 774.753, 436.948, 774.038,
                435.79, 774.038);
        ((GeneralPath) shape).curveTo(435.36902, 774.038, 435.03702, 774.13,
                434.855, 774.52905);
        ((GeneralPath) shape).lineTo(434.855, 774.038);
        ((GeneralPath) shape).moveTo(435.579, 776.591);
        ((GeneralPath) shape).curveTo(434.954, 776.591, 434.85602, 776.331,
                434.85602, 775.684);
        ((GeneralPath) shape).curveTo(434.85602, 774.961, 434.85602, 774.598,
                435.579, 774.598);
        ((GeneralPath) shape).curveTo(436.22202, 774.598, 436.22202, 774.99603,
                436.22202, 775.684);
        ((GeneralPath) shape).curveTo(436.222, 776.447, 436.078, 776.591,
                435.579, 776.591);
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
        ((GeneralPath) shape).moveTo(438.236, 775.314);
        ((GeneralPath) shape).curveTo(438.236, 774.74005, 438.27698, 774.59705,
                438.91, 774.59705);
        ((GeneralPath) shape).curveTo(439.509, 774.59705, 439.601, 774.64703,
                439.601, 775.314);
        ((GeneralPath) shape).lineTo(438.236, 775.314);
        ((GeneralPath) shape).moveTo(439.602, 776.166);
        ((GeneralPath) shape).curveTo(439.602, 776.591, 439.318, 776.591,
                438.91098, 776.591);
        ((GeneralPath) shape).curveTo(438.25497, 776.591, 438.23697, 776.393,
                438.23697, 775.771);
        ((GeneralPath) shape).lineTo(440.32996, 775.771);
        ((GeneralPath) shape).curveTo(440.32996, 774.412, 440.16296, 774.039,
                438.91095, 774.039);
        ((GeneralPath) shape).curveTo(437.68195, 774.039, 437.50995, 774.527,
                437.50995, 775.645);
        ((GeneralPath) shape).curveTo(437.50995, 776.781, 437.74695, 777.138,
                438.91095, 777.138);
        ((GeneralPath) shape).curveTo(439.77994, 777.138, 440.32996, 777.092,
                440.32996, 776.166);
        ((GeneralPath) shape).lineTo(439.602, 776.166);
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
        ((GeneralPath) shape).moveTo(440.828, 774.038);
        ((GeneralPath) shape).lineTo(440.828, 777.138);
        ((GeneralPath) shape).lineTo(441.557, 777.138);
        ((GeneralPath) shape).lineTo(441.557, 775.246);
        ((GeneralPath) shape).curveTo(441.557, 774.849, 441.69202, 774.59796,
                442.156, 774.59796);
        ((GeneralPath) shape).curveTo(442.527, 774.59796, 442.558, 774.779,
                442.558, 775.08795);
        ((GeneralPath) shape).lineTo(442.558, 775.246);
        ((GeneralPath) shape).lineTo(443.286, 775.246);
        ((GeneralPath) shape).lineTo(443.286, 775.001);
        ((GeneralPath) shape).curveTo(443.286, 774.423, 443.118, 774.03796,
                442.432, 774.03796);
        ((GeneralPath) shape).curveTo(442.051, 774.03796, 441.71402, 774.13495,
                441.558, 774.467);
        ((GeneralPath) shape).lineTo(441.558, 774.03796);
        ((GeneralPath) shape).lineTo(440.828, 774.03796);
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
        ((GeneralPath) shape).moveTo(446.21, 774.894);
        ((GeneralPath) shape).curveTo(446.21, 774.039, 445.496, 774.039,
                444.922, 774.039);
        ((GeneralPath) shape).curveTo(444.173, 774.039, 443.57098, 774.039,
                443.57098, 774.884);
        ((GeneralPath) shape).curveTo(443.57098, 775.638, 443.76898, 775.782,
                444.85898, 775.8);
        ((GeneralPath) shape).curveTo(445.55597, 775.811, 445.57297, 775.941,
                445.57297, 776.198);
        ((GeneralPath) shape).curveTo(445.57297, 776.592, 445.32996, 776.592,
                444.91397, 776.592);
        ((GeneralPath) shape).curveTo(444.39896, 776.592, 444.29996, 776.542,
                444.29996, 776.11395);
        ((GeneralPath) shape).lineTo(443.57095, 776.11395);
        ((GeneralPath) shape).curveTo(443.57095, 777.13794, 444.11295,
                777.13794, 444.91895, 777.13794);
        ((GeneralPath) shape).curveTo(445.66995, 777.13794, 446.30194,
                777.05396, 446.30194, 776.19794);
        ((GeneralPath) shape).curveTo(446.30194, 775.24097, 445.66895,
                775.29395, 444.98395, 775.25995);
        ((GeneralPath) shape).curveTo(444.37695, 775.23193, 444.29996, 775.22,
                444.29996, 774.928);
        ((GeneralPath) shape).curveTo(444.29996, 774.54, 444.55597, 774.54,
                444.92697, 774.54);
        ((GeneralPath) shape).curveTo(445.29898, 774.54, 445.48297, 774.54,
                445.48297, 774.894);
        ((GeneralPath) shape).lineTo(446.21, 774.894);
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
        ((GeneralPath) shape).moveTo(448.226, 774.598);
        ((GeneralPath) shape).curveTo(448.858, 774.598, 448.90903, 774.784,
                448.90903, 775.604);
        ((GeneralPath) shape).curveTo(448.90903, 776.412, 448.85803, 776.591,
                448.226, 776.591);
        ((GeneralPath) shape).curveTo(447.595, 776.591, 447.543, 776.411,
                447.543, 775.604);
        ((GeneralPath) shape).curveTo(447.543, 774.783, 447.595, 774.598,
                448.226, 774.598);
        ((GeneralPath) shape).moveTo(448.226, 774.038);
        ((GeneralPath) shape).curveTo(446.975, 774.038, 446.816, 774.41504,
                446.816, 775.598);
        ((GeneralPath) shape).curveTo(446.816, 776.765, 446.975, 777.138,
                448.226, 777.138);
        ((GeneralPath) shape).curveTo(449.47702, 777.138, 449.63702, 776.765,
                449.63702, 775.598);
        ((GeneralPath) shape).curveTo(449.637, 774.415, 449.477, 774.038,
                448.226, 774.038);
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
        ((GeneralPath) shape).moveTo(450.296, 774.038);
        ((GeneralPath) shape).lineTo(450.296, 777.138);
        ((GeneralPath) shape).lineTo(451.024, 777.138);
        ((GeneralPath) shape).lineTo(451.024, 775.45);
        ((GeneralPath) shape).curveTo(451.024, 774.90204, 451.11, 774.598,
                451.73798, 774.598);
        ((GeneralPath) shape).curveTo(452.20197, 774.598, 452.29898, 774.75,
                452.29898, 775.19403);
        ((GeneralPath) shape).lineTo(452.29898, 777.138);
        ((GeneralPath) shape).lineTo(453.02698, 777.138);
        ((GeneralPath) shape).lineTo(453.02698, 775.118);
        ((GeneralPath) shape).curveTo(453.02698, 774.371, 452.78598, 774.03796,
                451.99097, 774.03796);
        ((GeneralPath) shape).curveTo(451.56897, 774.03796, 451.20895,
                774.08295, 451.04898, 774.529);
        ((GeneralPath) shape).lineTo(451.02597, 774.529);
        ((GeneralPath) shape).lineTo(451.02597, 774.03796);
        ((GeneralPath) shape).lineTo(450.296, 774.03796);
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
        ((GeneralPath) shape).moveTo(455.78, 777.138);
        ((GeneralPath) shape).lineTo(456.509, 777.138);
        ((GeneralPath) shape).lineTo(456.509, 775.218);
        ((GeneralPath) shape).curveTo(456.509, 774.19104, 456.095, 774.038,
                455.104, 774.038);
        ((GeneralPath) shape).curveTo(454.411, 774.038, 453.779, 774.038,
                453.779, 774.945);
        ((GeneralPath) shape).lineTo(454.508, 774.945);
        ((GeneralPath) shape).curveTo(454.508, 774.567, 454.774, 774.539,
                455.10498, 774.539);
        ((GeneralPath) shape).curveTo(455.74, 774.539, 455.78198, 774.706,
                455.78198, 775.179);
        ((GeneralPath) shape).lineTo(455.78198, 775.604);
        ((GeneralPath) shape).lineTo(455.75897, 775.604);
        ((GeneralPath) shape).curveTo(455.57797, 775.223, 455.19696, 775.223,
                454.81097, 775.223);
        ((GeneralPath) shape).curveTo(454.067, 775.223, 453.68997, 775.427,
                453.68997, 776.16003);
        ((GeneralPath) shape).curveTo(453.68997, 776.992, 454.13098, 777.13904,
                454.81097, 777.13904);
        ((GeneralPath) shape).curveTo(455.179, 777.13904, 455.63498, 777.13904,
                455.78296, 776.68805);
        ((GeneralPath) shape).lineTo(455.78296, 777.138);
        ((GeneralPath) shape).moveTo(455.08, 775.771);
        ((GeneralPath) shape).curveTo(455.455, 775.771, 455.78, 775.771,
                455.78, 776.16);
        ((GeneralPath) shape).curveTo(455.78, 776.56, 455.485, 776.592, 455.08,
                776.592);
        ((GeneralPath) shape).curveTo(454.589, 776.592, 454.416, 776.555,
                454.416, 776.16);
        ((GeneralPath) shape).curveTo(454.416, 775.771, 454.694, 775.771,
                455.08, 775.771);
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
        shape = new Rectangle2D.Double(457.2090148925781, 772.760986328125,
                0.7289999723434448, 4.376999855041504);
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
        ((GeneralPath) shape).moveTo(463.456, 777.138);
        ((GeneralPath) shape).lineTo(463.456, 774.038);
        ((GeneralPath) shape).lineTo(462.728, 774.038);
        ((GeneralPath) shape).lineTo(462.728, 775.815);
        ((GeneralPath) shape).curveTo(462.728, 776.349, 462.544, 776.59,
                461.973, 776.59);
        ((GeneralPath) shape).curveTo(461.495, 776.59, 461.453, 776.42004,
                461.453, 775.981);
        ((GeneralPath) shape).lineTo(461.453, 774.038);
        ((GeneralPath) shape).lineTo(460.725, 774.038);
        ((GeneralPath) shape).lineTo(460.725, 776.25604);
        ((GeneralPath) shape).curveTo(460.725, 776.92706, 461.134, 777.13806,
                461.747, 777.13806);
        ((GeneralPath) shape).curveTo(462.172, 777.13806, 462.556, 777.0411,
                462.726, 776.6451);
        ((GeneralPath) shape).lineTo(462.726, 777.13806);
        ((GeneralPath) shape).lineTo(463.456, 777.13806);
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
        ((GeneralPath) shape).moveTo(466.729, 774.894);
        ((GeneralPath) shape).curveTo(466.729, 774.039, 466.015, 774.039,
                465.441, 774.039);
        ((GeneralPath) shape).curveTo(464.691, 774.039, 464.08902, 774.039,
                464.08902, 774.884);
        ((GeneralPath) shape).curveTo(464.08902, 775.638, 464.28702, 775.782,
                465.377, 775.8);
        ((GeneralPath) shape).curveTo(466.074, 775.811, 466.091, 775.941,
                466.091, 776.198);
        ((GeneralPath) shape).curveTo(466.091, 776.592, 465.848, 776.592,
                465.432, 776.592);
        ((GeneralPath) shape).curveTo(464.917, 776.592, 464.818, 776.542,
                464.818, 776.11395);
        ((GeneralPath) shape).lineTo(464.089, 776.11395);
        ((GeneralPath) shape).curveTo(464.089, 777.13794, 464.63098, 777.13794,
                465.438, 777.13794);
        ((GeneralPath) shape).curveTo(466.188, 777.13794, 466.81998, 777.05396,
                466.81998, 776.19794);
        ((GeneralPath) shape).curveTo(466.81998, 775.24097, 466.18698,
                775.29395, 465.50198, 775.25995);
        ((GeneralPath) shape).curveTo(464.896, 775.23193, 464.818, 775.22,
                464.818, 774.928);
        ((GeneralPath) shape).curveTo(464.818, 774.54, 465.074, 774.54,
                465.445, 774.54);
        ((GeneralPath) shape).curveTo(465.81702, 774.54, 466.001, 774.54,
                466.001, 774.894);
        ((GeneralPath) shape).lineTo(466.729, 774.894);
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
        ((GeneralPath) shape).moveTo(468.061, 775.314);
        ((GeneralPath) shape).curveTo(468.061, 774.74005, 468.103, 774.59705,
                468.736, 774.59705);
        ((GeneralPath) shape).curveTo(469.335, 774.59705, 469.426, 774.64703,
                469.426, 775.314);
        ((GeneralPath) shape).lineTo(468.061, 775.314);
        ((GeneralPath) shape).moveTo(469.426, 776.166);
        ((GeneralPath) shape).curveTo(469.426, 776.591, 469.142, 776.591,
                468.736, 776.591);
        ((GeneralPath) shape).curveTo(468.07898, 776.591, 468.061, 776.393,
                468.061, 775.771);
        ((GeneralPath) shape).lineTo(470.155, 775.771);
        ((GeneralPath) shape).curveTo(470.155, 774.412, 469.987, 774.039,
                468.736, 774.039);
        ((GeneralPath) shape).curveTo(467.507, 774.039, 467.33398, 774.527,
                467.33398, 775.645);
        ((GeneralPath) shape).curveTo(467.33398, 776.781, 467.57098, 777.138,
                468.736, 777.138);
        ((GeneralPath) shape).curveTo(469.604, 777.138, 470.155, 777.092,
                470.155, 776.166);
        ((GeneralPath) shape).lineTo(469.426, 776.166);
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
        shape = new Rectangle2D.Double(470.9259948730469, 776.3389892578125,
                0.7279999852180481, 0.7990000247955322);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_238;
        g.setTransform(defaultTransform__0_238);
        g.setClip(clip__0_238);
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
        return 45;
    }

    /**
     * Returns the Y of the bounding box of the original SVG image.
     *
     * @return The Y of the bounding box of the original SVG image.
     */
    public static int getOrigY() {
        return 766;
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
