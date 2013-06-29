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
public class SuperHeavyDualTurretTemplate {
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
        ((GeneralPath) shape).curveTo(530.152, 364.16006, 530.133, 365.62506,
                530.113, 365.92407);
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
        ((GeneralPath) shape).moveTo(534.59204, 356.029);
        ((GeneralPath) shape).lineTo(534.03705, 356.029);
        ((GeneralPath) shape).curveTo(533.57904, 356.029, 532.986, 356.347,
                532.905, 357.041);
        ((GeneralPath) shape).curveTo(532.827, 357.65598, 532.827, 358.84598,
                532.827, 359.45798);
        ((GeneralPath) shape).lineTo(532.827, 361.46298);
        ((GeneralPath) shape).lineTo(534.59204, 361.46298);
        ((GeneralPath) shape).lineTo(534.59204, 356.029);
        ((GeneralPath) shape).closePath();
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
        ((GeneralPath) shape).lineTo(545.50104, 368.284);
        ((GeneralPath) shape).moveTo(545.54205, 356.029);
        ((GeneralPath) shape).lineTo(544.986, 356.029);
        ((GeneralPath) shape).curveTo(544.53, 356.029, 543.934, 356.347,
                543.85504, 357.041);
        ((GeneralPath) shape).curveTo(543.77606, 357.65598, 543.77606,
                358.84598, 543.77606, 359.45798);
        ((GeneralPath) shape).lineTo(543.77606, 361.46298);
        ((GeneralPath) shape).lineTo(545.54205, 361.46298);
        ((GeneralPath) shape).lineTo(545.54205, 356.029);
        ((GeneralPath) shape).closePath();
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
        ((GeneralPath) shape).curveTo(563.79895, 367.628, 562.7479, 368.442,
                561.2009, 368.442);
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
        ((GeneralPath) shape).lineTo(567.72394, 356.03);
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
        ((GeneralPath) shape).lineTo(551.64594, 370.73904);
        ((GeneralPath) shape).moveTo(552.5399, 370.37906);
        ((GeneralPath) shape).curveTo(552.5449, 370.19107, 552.4619, 369.88107,
                552.1189, 369.88107);
        ((GeneralPath) shape).curveTo(551.8019, 369.88107, 551.6689, 370.17007,
                551.6449, 370.37906);
        ((GeneralPath) shape).lineTo(552.5399, 370.37906);
        ((GeneralPath) shape).closePath();
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
        shape = new Rectangle2D.Double(556.760986328125, 368.6449890136719,
                0.5189999938011169, 2.9679999351501465);
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
        ((GeneralPath) shape).moveTo(558.74493, 370.616);
        ((GeneralPath) shape).curveTo(558.3809, 370.609, 558.03595, 370.688,
                558.03595, 370.996);
        ((GeneralPath) shape).curveTo(558.03595, 371.198, 558.16296, 371.288,
                558.32697, 371.288);
        ((GeneralPath) shape).curveTo(558.52997, 371.288, 558.683, 371.156,
                558.72797, 371.009);
        ((GeneralPath) shape).curveTo(558.73895, 370.971, 558.745, 370.92902,
                558.745, 370.897);
        ((GeneralPath) shape).lineTo(558.745, 370.616);
        ((GeneralPath) shape).closePath();
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
        ((GeneralPath) shape).lineTo(559.62195, 368.64502);
        ((GeneralPath) shape).moveTo(560.1359, 370.77203);
        ((GeneralPath) shape).curveTo(560.1359, 370.81403, 560.13995,
                370.85605, 560.15094, 370.89203);
        ((GeneralPath) shape).curveTo(560.20294, 371.09702, 560.38293, 371.252,
                560.60596, 371.252);
        ((GeneralPath) shape).curveTo(560.92694, 371.252, 561.123, 370.993,
                561.123, 370.584);
        ((GeneralPath) shape).curveTo(561.123, 370.22403, 560.95197, 369.93402,
                560.609, 369.93402);
        ((GeneralPath) shape).curveTo(560.401, 369.93402, 560.211, 370.083,
                560.153, 370.30902);
        ((GeneralPath) shape).curveTo(560.145, 370.346, 560.136, 370.39304,
                560.136, 370.44202);
        ((GeneralPath) shape).lineTo(560.136, 370.772);
        ((GeneralPath) shape).closePath();
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
        ((GeneralPath) shape).lineTo(561.87494, 371.14);
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
        ((GeneralPath) shape).lineTo(557.55096, 357.644);
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
        ((GeneralPath) shape).moveTo(550.315, 359.958);
        ((GeneralPath) shape).lineTo(548.631, 357.126);
        ((GeneralPath) shape).lineTo(548.631, 368.284);
        ((GeneralPath) shape).lineTo(552.892, 368.284);
        ((GeneralPath) shape).lineTo(552.892, 366.895);
        ((GeneralPath) shape).lineTo(550.315, 366.895);
        ((GeneralPath) shape).lineTo(550.315, 359.958);
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
        ((GeneralPath) shape).lineTo(565.0, 368.775);
        ((GeneralPath) shape).lineTo(565.048, 369.53);
        ((GeneralPath) shape).lineTo(564.94995, 369.53);
        ((GeneralPath) shape).lineTo(564.93494, 369.198);
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
        ((GeneralPath) shape).lineTo(557.246, 349.91904);
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
        ((GeneralPath) shape).lineTo(557.246, 349.91904);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        stroke = new BasicStroke(0.486f, 1, 1, 4.0f, null, 0.0f);
        shape = new Line2D.Float(551.513977f, 353.864014f, 550.739990f,
                352.614990f);
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        stroke = new BasicStroke(0.486f, 1, 1, 4.0f, null, 0.0f);
        shape = new Line2D.Float(550.304993f, 353.881012f, 549.530029f,
                352.631012f);
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        stroke = new BasicStroke(0.486f, 1, 1, 4.0f, null, 0.0f);
        shape = new Line2D.Float(553.934998f, 353.713989f, 553.159973f,
                352.464996f);
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        stroke = new BasicStroke(0.486f, 1, 1, 4.0f, null, 0.0f);
        shape = new Line2D.Float(552.724976f, 353.713989f, 551.948975f,
                352.464996f);
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        stroke = new BasicStroke(0.486f, 1, 1, 4.0f, null, 0.0f);
        shape = new Line2D.Float(549.094971f, 354.023987f, 548.320007f,
                352.774994f);
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
        paint = new Color(199, 200, 202, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(387.219, 195.954);
        ((GeneralPath) shape).lineTo(394.11, 202.846);
        ((GeneralPath) shape).lineTo(394.11, 277.438);
        ((GeneralPath) shape).lineTo(387.219, 284.331);
        ((GeneralPath) shape).lineTo(256.26, 284.331);
        ((GeneralPath) shape).lineTo(256.26, 195.954);
        ((GeneralPath) shape).lineTo(261.774, 187.683);
        ((GeneralPath) shape).lineTo(365.161, 187.683);
        ((GeneralPath) shape).lineTo(370.677, 195.954);
        ((GeneralPath) shape).lineTo(387.219, 195.954);
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
        ((GeneralPath) shape).moveTo(526.443, 45.698);
        ((GeneralPath) shape).lineTo(446.217, 45.698);
        ((GeneralPath) shape).lineTo(440.978, 37.841);
        ((GeneralPath) shape).lineTo(446.203, 29.983);
        ((GeneralPath) shape).lineTo(526.443, 29.983);
        ((GeneralPath) shape).lineTo(531.958, 37.841);
        ((GeneralPath) shape).lineTo(526.443, 45.698);
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
        paint = new Color(255, 255, 255, 255);
        stroke = new BasicStroke(0.973f, 0, 1, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(440.978, 37.841);
        ((GeneralPath) shape).lineTo(446.203, 29.983);
        ((GeneralPath) shape).lineTo(526.443, 29.983);
        ((GeneralPath) shape).lineTo(531.958, 37.841);
        ((GeneralPath) shape).lineTo(526.443, 45.698);
        ((GeneralPath) shape).lineTo(446.217, 45.698);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        ((GeneralPath) shape).moveTo(383.082, 191.819);
        ((GeneralPath) shape).lineTo(389.974, 198.71);
        ((GeneralPath) shape).lineTo(389.974, 273.302);
        ((GeneralPath) shape).lineTo(383.082, 280.194);
        ((GeneralPath) shape).lineTo(252.125, 280.194);
        ((GeneralPath) shape).lineTo(252.125, 191.819);
        ((GeneralPath) shape).lineTo(257.639, 183.547);
        ((GeneralPath) shape).lineTo(361.024, 183.547);
        ((GeneralPath) shape).lineTo(366.54, 191.819);
        ((GeneralPath) shape).lineTo(383.082, 191.819);
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
        stroke = new BasicStroke(1.945f, 0, 0, 10.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(389.974, 273.302);
        ((GeneralPath) shape).lineTo(383.082, 280.195);
        ((GeneralPath) shape).lineTo(252.125, 280.195);
        ((GeneralPath) shape).lineTo(252.125, 191.819);
        ((GeneralPath) shape).lineTo(257.639, 183.548);
        ((GeneralPath) shape).lineTo(361.024, 183.548);
        ((GeneralPath) shape).lineTo(366.54, 191.819);
        ((GeneralPath) shape).lineTo(383.082, 191.819);
        ((GeneralPath) shape).lineTo(389.974, 198.71);
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
        ((GeneralPath) shape).moveTo(359.66, 201.468);
        ((GeneralPath) shape).lineTo(258.742, 201.468);
        ((GeneralPath) shape).lineTo(253.502, 193.609);
        ((GeneralPath) shape).lineTo(258.729, 185.753);
        ((GeneralPath) shape).lineTo(359.646, 185.753);
        ((GeneralPath) shape).lineTo(364.887, 193.609);
        ((GeneralPath) shape).lineTo(359.66, 201.468);
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
        stroke = new BasicStroke(0.973f, 0, 1, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(253.502, 193.609);
        ((GeneralPath) shape).lineTo(258.729, 185.753);
        ((GeneralPath) shape).lineTo(359.646, 185.753);
        ((GeneralPath) shape).lineTo(364.887, 193.609);
        ((GeneralPath) shape).lineTo(359.66, 201.468);
        ((GeneralPath) shape).lineTo(258.742, 201.468);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        ((GeneralPath) shape).moveTo(265.873, 194.558);
        ((GeneralPath) shape).lineTo(267.352, 194.558);
        ((GeneralPath) shape).lineTo(267.352, 194.826);
        ((GeneralPath) shape).curveTo(267.352, 195.909, 267.155, 196.60701,
                266.75998, 196.92601);
        ((GeneralPath) shape).curveTo(266.36697, 197.242, 265.494, 197.40201,
                264.14297, 197.40201);
        ((GeneralPath) shape).curveTo(262.61298, 197.40201, 261.66898,
                197.15201, 261.31598, 196.65001);
        ((GeneralPath) shape).curveTo(260.96298, 196.14801, 260.787, 194.809,
                260.787, 192.62701);
        ((GeneralPath) shape).curveTo(260.787, 191.34502, 261.026, 190.49901,
                261.504, 190.09401);
        ((GeneralPath) shape).curveTo(261.981, 189.68901, 262.98, 189.488,
                264.501, 189.488);
        ((GeneralPath) shape).curveTo(265.607, 189.488, 266.34702, 189.653,
                266.719, 189.98601);
        ((GeneralPath) shape).curveTo(267.089, 190.317, 267.277, 190.97801,
                267.277, 191.966);
        ((GeneralPath) shape).lineTo(267.28302, 192.143);
        ((GeneralPath) shape).lineTo(265.80402, 192.143);
        ((GeneralPath) shape).lineTo(265.80402, 191.944);
        ((GeneralPath) shape).curveTo(265.80402, 191.437, 265.71002, 191.109,
                265.51703, 190.966);
        ((GeneralPath) shape).curveTo(265.32703, 190.823, 264.88904, 190.752,
                264.20602, 190.752);
        ((GeneralPath) shape).curveTo(263.29303, 190.752, 262.74402, 190.86299,
                262.55902, 191.088);
        ((GeneralPath) shape).curveTo(262.377, 191.31, 262.28302, 191.977,
                262.28302, 193.083);
        ((GeneralPath) shape).curveTo(262.28302, 194.56999, 262.36603, 195.454,
                262.531, 195.72699);
        ((GeneralPath) shape).curveTo(262.696, 195.99998, 263.228, 196.13799,
                264.133, 196.13799);
        ((GeneralPath) shape).curveTo(264.863, 196.13799, 265.33798, 196.06299,
                265.557, 195.90999);
        ((GeneralPath) shape).curveTo(265.773, 195.75899, 265.884, 195.426,
                265.884, 194.90698);
        ((GeneralPath) shape).lineTo(265.87302, 194.55798);
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
        ((GeneralPath) shape).moveTo(269.75, 193.469);
        ((GeneralPath) shape).lineTo(271.865, 193.469);
        ((GeneralPath) shape).curveTo(272.369, 193.469, 272.70398, 193.381,
                272.869, 193.198);
        ((GeneralPath) shape).curveTo(273.034, 193.018, 273.116, 192.65599,
                273.116, 192.11299);
        ((GeneralPath) shape).curveTo(273.116, 191.56, 273.04498, 191.20099,
                272.90298, 191.03899);
        ((GeneralPath) shape).curveTo(272.75998, 190.87898, 272.451, 190.79599,
                271.96698, 190.79599);
        ((GeneralPath) shape).lineTo(269.749, 190.79599);
        ((GeneralPath) shape).lineTo(269.749, 193.469);
        ((GeneralPath) shape).moveTo(268.276, 197.333);
        ((GeneralPath) shape).lineTo(268.276, 189.55399);
        ((GeneralPath) shape).lineTo(272.104, 189.55399);
        ((GeneralPath) shape).curveTo(273.05402, 189.55399, 273.711, 189.71999,
                274.072, 190.04999);
        ((GeneralPath) shape).curveTo(274.43, 190.38098, 274.61298, 190.97899,
                274.61298, 191.84499);
        ((GeneralPath) shape).curveTo(274.61298, 192.63199, 274.52496,
                193.16699, 274.343, 193.458);
        ((GeneralPath) shape).curveTo(274.164, 193.74599, 273.79398, 193.94499,
                273.237, 194.056);
        ((GeneralPath) shape).lineTo(273.237, 194.108);
        ((GeneralPath) shape).curveTo(274.096, 194.159, 274.529, 194.664,
                274.529, 195.618);
        ((GeneralPath) shape).lineTo(274.529, 197.333);
        ((GeneralPath) shape).lineTo(273.055, 197.333);
        ((GeneralPath) shape).lineTo(273.055, 195.915);
        ((GeneralPath) shape).curveTo(273.055, 195.114, 272.663, 194.71199,
                271.87198, 194.71199);
        ((GeneralPath) shape).lineTo(269.75098, 194.71199);
        ((GeneralPath) shape).lineTo(269.75098, 197.333);
        ((GeneralPath) shape).lineTo(268.27597, 197.333);
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
        shape = new Rectangle2D.Double(275.77801513671875, 189.5540008544922,
                1.4739999771118164, 7.7789998054504395);
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
        ((GeneralPath) shape).moveTo(281.727, 190.876);
        ((GeneralPath) shape).lineTo(281.727, 197.333);
        ((GeneralPath) shape).lineTo(280.253, 197.333);
        ((GeneralPath) shape).lineTo(280.253, 190.876);
        ((GeneralPath) shape).lineTo(278.012, 190.876);
        ((GeneralPath) shape).lineTo(278.012, 189.554);
        ((GeneralPath) shape).lineTo(284.047, 189.554);
        ((GeneralPath) shape).lineTo(284.047, 190.876);
        ((GeneralPath) shape).lineTo(281.727, 190.876);
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
        shape = new Rectangle2D.Double(284.7950134277344, 189.5540008544922,
                1.4730000495910645, 7.7789998054504395);
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
        ((GeneralPath) shape).moveTo(292.444, 194.558);
        ((GeneralPath) shape).lineTo(293.923, 194.558);
        ((GeneralPath) shape).lineTo(293.923, 194.826);
        ((GeneralPath) shape).curveTo(293.923, 195.909, 293.726, 196.60701,
                293.331, 196.92601);
        ((GeneralPath) shape).curveTo(292.938, 197.242, 292.065, 197.40201,
                290.714, 197.40201);
        ((GeneralPath) shape).curveTo(289.184, 197.40201, 288.24, 197.15201,
                287.887, 196.65001);
        ((GeneralPath) shape).curveTo(287.534, 196.149, 287.358, 194.809,
                287.358, 192.62701);
        ((GeneralPath) shape).curveTo(287.358, 191.34502, 287.59702, 190.49901,
                288.075, 190.09401);
        ((GeneralPath) shape).curveTo(288.553, 189.68901, 289.55103, 189.488,
                291.073, 189.488);
        ((GeneralPath) shape).curveTo(292.179, 189.488, 292.919, 189.653,
                293.291, 189.98601);
        ((GeneralPath) shape).curveTo(293.66098, 190.317, 293.849, 190.97801,
                293.849, 191.966);
        ((GeneralPath) shape).lineTo(293.855, 192.143);
        ((GeneralPath) shape).lineTo(292.376, 192.143);
        ((GeneralPath) shape).lineTo(292.376, 191.944);
        ((GeneralPath) shape).curveTo(292.376, 191.437, 292.282, 191.109,
                292.08902, 190.966);
        ((GeneralPath) shape).curveTo(291.89902, 190.823, 291.46103, 190.752,
                290.778, 190.752);
        ((GeneralPath) shape).curveTo(289.86502, 190.752, 289.316, 190.86299,
                289.13202, 191.088);
        ((GeneralPath) shape).curveTo(288.95, 191.31, 288.85602, 191.977,
                288.85602, 193.083);
        ((GeneralPath) shape).curveTo(288.85602, 194.56999, 288.93802, 195.454,
                289.104, 195.72699);
        ((GeneralPath) shape).curveTo(289.269, 195.99998, 289.801, 196.13799,
                290.70502, 196.13799);
        ((GeneralPath) shape).curveTo(291.436, 196.13799, 291.911, 196.06299,
                292.13, 195.90999);
        ((GeneralPath) shape).curveTo(292.346, 195.75899, 292.457, 195.426,
                292.457, 194.90698);
        ((GeneralPath) shape).lineTo(292.444, 194.55798);
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
        ((GeneralPath) shape).moveTo(299.205, 194.752);
        ((GeneralPath) shape).lineTo(297.86298, 190.7);
        ((GeneralPath) shape).lineTo(296.54297, 194.752);
        ((GeneralPath) shape).lineTo(299.20496, 194.752);
        ((GeneralPath) shape).moveTo(299.54596, 195.84);
        ((GeneralPath) shape).lineTo(296.19495, 195.84);
        ((GeneralPath) shape).lineTo(295.71194, 197.333);
        ((GeneralPath) shape).lineTo(294.15295, 197.333);
        ((GeneralPath) shape).lineTo(296.73495, 189.55399);
        ((GeneralPath) shape).lineTo(298.94797, 189.55399);
        ((GeneralPath) shape).lineTo(301.56998, 197.333);
        ((GeneralPath) shape).lineTo(300.03998, 197.333);
        ((GeneralPath) shape).lineTo(299.546, 195.84);
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
        ((GeneralPath) shape).moveTo(303.718, 189.554);
        ((GeneralPath) shape).lineTo(303.718, 196.011);
        ((GeneralPath) shape).lineTo(307.358, 196.011);
        ((GeneralPath) shape).lineTo(307.358, 197.333);
        ((GeneralPath) shape).lineTo(302.245, 197.333);
        ((GeneralPath) shape).lineTo(302.245, 189.554);
        ((GeneralPath) shape).lineTo(303.718, 189.554);
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
        ((GeneralPath) shape).moveTo(312.49, 196.091);
        ((GeneralPath) shape).lineTo(314.589, 196.091);
        ((GeneralPath) shape).curveTo(315.29398, 196.091, 315.749, 195.929,
                315.957, 195.604);
        ((GeneralPath) shape).curveTo(316.161, 195.279, 316.267, 194.56401,
                316.267, 193.453);
        ((GeneralPath) shape).curveTo(316.267, 192.307, 316.176, 191.578,
                315.98898, 191.265);
        ((GeneralPath) shape).curveTo(315.804, 190.954, 315.369, 190.798,
                314.68, 190.798);
        ((GeneralPath) shape).lineTo(312.491, 190.798);
        ((GeneralPath) shape).lineTo(312.491, 196.091);
        ((GeneralPath) shape).moveTo(311.017, 197.33301);
        ((GeneralPath) shape).lineTo(311.017, 189.554);
        ((GeneralPath) shape).lineTo(314.833, 189.554);
        ((GeneralPath) shape).curveTo(315.91702, 189.554, 316.677, 189.791,
                317.11102, 190.266);
        ((GeneralPath) shape).curveTo(317.54404, 190.73901, 317.76202, 191.571,
                317.76202, 192.76201);
        ((GeneralPath) shape).curveTo(317.76202, 194.70201, 317.588, 195.95001,
                317.239, 196.503);
        ((GeneralPath) shape).curveTo(316.89203, 197.056, 316.104, 197.332,
                314.87903, 197.332);
        ((GeneralPath) shape).lineTo(311.01703, 197.332);
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
        ((GeneralPath) shape).moveTo(323.19, 194.752);
        ((GeneralPath) shape).lineTo(321.84702, 190.7);
        ((GeneralPath) shape).lineTo(320.528, 194.752);
        ((GeneralPath) shape).lineTo(323.19, 194.752);
        ((GeneralPath) shape).moveTo(323.531, 195.84);
        ((GeneralPath) shape).lineTo(320.18, 195.84);
        ((GeneralPath) shape).lineTo(319.697, 197.333);
        ((GeneralPath) shape).lineTo(318.138, 197.333);
        ((GeneralPath) shape).lineTo(320.721, 189.55399);
        ((GeneralPath) shape).lineTo(322.93402, 189.55399);
        ((GeneralPath) shape).lineTo(325.55603, 197.333);
        ((GeneralPath) shape).lineTo(324.02603, 197.333);
        ((GeneralPath) shape).lineTo(323.53104, 195.84);
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(335.308, 189.554);
        ((GeneralPath) shape).lineTo(335.308, 197.33301);
        ((GeneralPath) shape).lineTo(333.83502, 197.33301);
        ((GeneralPath) shape).lineTo(333.83502, 193.093);
        ((GeneralPath) shape).curveTo(333.83502, 192.757, 333.84302, 192.37201,
                333.86304, 191.942);
        ((GeneralPath) shape).lineTo(333.89105, 191.36);
        ((GeneralPath) shape).lineTo(333.91907, 190.785);
        ((GeneralPath) shape).lineTo(333.87405, 190.785);
        ((GeneralPath) shape).lineTo(333.69705, 191.326);
        ((GeneralPath) shape).lineTo(333.52704, 191.867);
        ((GeneralPath) shape).curveTo(333.36703, 192.352, 333.24503, 192.711,
                333.15704, 192.945);
        ((GeneralPath) shape).lineTo(331.45004, 197.33301);
        ((GeneralPath) shape).lineTo(330.10803, 197.33301);
        ((GeneralPath) shape).lineTo(328.38403, 192.979);
        ((GeneralPath) shape).curveTo(328.29004, 192.74, 328.16504, 192.381,
                328.00903, 191.90201);
        ((GeneralPath) shape).lineTo(327.83203, 191.36);
        ((GeneralPath) shape).lineTo(327.65503, 190.824);
        ((GeneralPath) shape).lineTo(327.61002, 190.824);
        ((GeneralPath) shape).lineTo(327.63803, 191.388);
        ((GeneralPath) shape).lineTo(327.66605, 191.95801);
        ((GeneralPath) shape).curveTo(327.68805, 192.39601, 327.70004,
                192.77501, 327.70004, 193.09201);
        ((GeneralPath) shape).lineTo(327.70004, 197.33202);
        ((GeneralPath) shape).lineTo(326.22705, 197.33202);
        ((GeneralPath) shape).lineTo(326.22705, 189.55301);
        ((GeneralPath) shape).lineTo(328.62604, 189.55301);
        ((GeneralPath) shape).lineTo(330.01505, 193.15501);
        ((GeneralPath) shape).curveTo(330.10904, 193.40501, 330.23404, 193.764,
                330.39005, 194.23201);
        ((GeneralPath) shape).lineTo(330.56104, 194.77402);
        ((GeneralPath) shape).lineTo(330.73804, 195.30902);
        ((GeneralPath) shape).lineTo(330.78903, 195.30902);
        ((GeneralPath) shape).lineTo(330.95303, 194.77402);
        ((GeneralPath) shape).lineTo(331.12503, 194.23802);
        ((GeneralPath) shape).curveTo(331.26404, 193.78802, 331.38702,
                193.42802, 331.48804, 193.16602);
        ((GeneralPath) shape).lineTo(332.85303, 189.55301);
        ((GeneralPath) shape).lineTo(335.308, 189.55301);
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
        ((GeneralPath) shape).moveTo(341.024, 194.752);
        ((GeneralPath) shape).lineTo(339.68198, 190.7);
        ((GeneralPath) shape).lineTo(338.36197, 194.752);
        ((GeneralPath) shape).lineTo(341.02396, 194.752);
        ((GeneralPath) shape).moveTo(341.36597, 195.84);
        ((GeneralPath) shape).lineTo(338.01495, 195.84);
        ((GeneralPath) shape).lineTo(337.53195, 197.333);
        ((GeneralPath) shape).lineTo(335.97296, 197.333);
        ((GeneralPath) shape).lineTo(338.55496, 189.55399);
        ((GeneralPath) shape).lineTo(340.76797, 189.55399);
        ((GeneralPath) shape).lineTo(343.38998, 197.333);
        ((GeneralPath) shape).lineTo(341.861, 197.333);
        ((GeneralPath) shape).lineTo(341.366, 195.84);
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(347.12, 193.196);
        ((GeneralPath) shape).lineTo(350.538, 193.196);
        ((GeneralPath) shape).lineTo(350.56, 194.74);
        ((GeneralPath) shape).curveTo(350.56, 195.863, 350.35, 196.59001,
                349.926, 196.914);
        ((GeneralPath) shape).curveTo(349.505, 197.239, 348.561, 197.401,
                347.096, 197.401);
        ((GeneralPath) shape).curveTo(345.75302, 197.401, 344.867, 197.185,
                344.428, 196.752);
        ((GeneralPath) shape).curveTo(343.993, 196.319, 343.77402, 195.436,
                343.77402, 194.102);
        ((GeneralPath) shape).curveTo(343.77402, 192.401, 343.86002, 191.32701,
                344.036, 190.876);
        ((GeneralPath) shape).curveTo(344.252, 190.32901, 344.58002, 189.96101,
                345.02002, 189.77101);
        ((GeneralPath) shape).curveTo(345.458, 189.58301, 346.20602, 189.48601,
                347.26102, 189.48601);
        ((GeneralPath) shape).curveTo(348.64, 189.48601, 349.531, 189.63101,
                349.93402, 189.927);
        ((GeneralPath) shape).curveTo(350.33502, 190.22, 350.53802, 190.873,
                350.53802, 191.885);
        ((GeneralPath) shape).lineTo(349.04803, 191.885);
        ((GeneralPath) shape).curveTo(349.02304, 191.37799, 348.91104, 191.062,
                348.71503, 190.93599);
        ((GeneralPath) shape).curveTo(348.52203, 190.81299, 348.02902,
                190.74998, 347.24503, 190.74998);
        ((GeneralPath) shape).curveTo(346.39102, 190.74998, 345.85004,
                190.85498, 345.62103, 191.06898);
        ((GeneralPath) shape).curveTo(345.39304, 191.27998, 345.27603,
                191.78099, 345.27603, 192.56798);
        ((GeneralPath) shape).lineTo(345.27002, 193.34898);
        ((GeneralPath) shape).lineTo(345.282, 194.34698);
        ((GeneralPath) shape).curveTo(345.282, 195.11699, 345.39502, 195.60898,
                345.62302, 195.81999);
        ((GeneralPath) shape).curveTo(345.851, 196.03099, 346.377, 196.13599,
                347.20502, 196.13599);
        ((GeneralPath) shape).curveTo(348.00702, 196.13599, 348.518, 196.04698,
                348.73703, 195.86798);
        ((GeneralPath) shape).curveTo(348.95404, 195.69098, 349.06403,
                195.26898, 349.06403, 194.60197);
        ((GeneralPath) shape).lineTo(349.07004, 194.28297);
        ((GeneralPath) shape).lineTo(347.12003, 194.28297);
        ((GeneralPath) shape).lineTo(347.12003, 193.19597);
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(352.909, 190.796);
        ((GeneralPath) shape).lineTo(352.909, 192.802);
        ((GeneralPath) shape).lineTo(356.55, 192.802);
        ((GeneralPath) shape).lineTo(356.55, 193.891);
        ((GeneralPath) shape).lineTo(352.909, 193.891);
        ((GeneralPath) shape).lineTo(352.909, 196.091);
        ((GeneralPath) shape).lineTo(356.783, 196.091);
        ((GeneralPath) shape).lineTo(356.783, 197.333);
        ((GeneralPath) shape).lineTo(351.436, 197.333);
        ((GeneralPath) shape).lineTo(351.436, 189.554);
        ((GeneralPath) shape).lineTo(356.749, 189.554);
        ((GeneralPath) shape).lineTo(356.749, 190.796);
        ((GeneralPath) shape).lineTo(352.909, 190.796);
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
        ((GeneralPath) shape).moveTo(257.742, 206.667);
        ((GeneralPath) shape).lineTo(257.742, 208.479);
        ((GeneralPath) shape).lineTo(260.264, 208.479);
        ((GeneralPath) shape).lineTo(260.264, 208.972);
        ((GeneralPath) shape).lineTo(257.742, 208.972);
        ((GeneralPath) shape).lineTo(257.742, 211.358);
        ((GeneralPath) shape).lineTo(257.158, 211.358);
        ((GeneralPath) shape).lineTo(257.158, 206.172);
        ((GeneralPath) shape).lineTo(260.332, 206.172);
        ((GeneralPath) shape).lineTo(260.332, 206.667);
        ((GeneralPath) shape).lineTo(257.742, 206.667);
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
        ((GeneralPath) shape).moveTo(260.982, 207.726);
        ((GeneralPath) shape).lineTo(261.513, 207.726);
        ((GeneralPath) shape).lineTo(261.46, 208.144);
        ((GeneralPath) shape).lineTo(261.47098, 208.15599);
        ((GeneralPath) shape).curveTo(261.679, 207.814, 262.02698, 207.64299,
                262.50998, 207.64299);
        ((GeneralPath) shape).curveTo(263.17697, 207.64299, 263.511, 207.98698,
                263.511, 208.676);
        ((GeneralPath) shape).lineTo(263.508, 208.927);
        ((GeneralPath) shape).lineTo(262.985, 208.927);
        ((GeneralPath) shape).lineTo(262.99597, 208.836);
        ((GeneralPath) shape).curveTo(263.00397, 208.741, 263.00797, 208.676,
                263.00797, 208.643);
        ((GeneralPath) shape).curveTo(263.00797, 208.27, 262.80698, 208.084,
                262.40097, 208.084);
        ((GeneralPath) shape).curveTo(261.80997, 208.084, 261.51398, 208.449,
                261.51398, 209.182);
        ((GeneralPath) shape).lineTo(261.51398, 211.35901);
        ((GeneralPath) shape).lineTo(260.98297, 211.35901);
        ((GeneralPath) shape).lineTo(260.98297, 207.72601);
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
        ((GeneralPath) shape).moveTo(265.518, 208.114);
        ((GeneralPath) shape).curveTo(265.048, 208.114, 264.758, 208.19,
                264.644, 208.343);
        ((GeneralPath) shape).curveTo(264.532, 208.495, 264.475, 208.896,
                264.475, 209.542);
        ((GeneralPath) shape).curveTo(264.475, 210.188, 264.53, 210.587,
                264.644, 210.74101);
        ((GeneralPath) shape).curveTo(264.755, 210.893, 265.048, 210.97101,
                265.518, 210.97101);
        ((GeneralPath) shape).curveTo(265.99, 210.97101, 266.282, 210.895,
                266.396, 210.74101);
        ((GeneralPath) shape).curveTo(266.507, 210.58902, 266.564, 210.18802,
                266.564, 209.542);
        ((GeneralPath) shape).curveTo(266.564, 208.89601, 266.509, 208.49701,
                266.396, 208.343);
        ((GeneralPath) shape).curveTo(266.284, 208.19101, 265.992, 208.114,
                265.518, 208.114);
        ((GeneralPath) shape).moveTo(265.518, 207.673);
        ((GeneralPath) shape).curveTo(266.187, 207.673, 266.622, 207.789,
                266.822, 208.02301);
        ((GeneralPath) shape).curveTo(267.021, 208.25401, 267.12198, 208.76201,
                267.12198, 209.54301);
        ((GeneralPath) shape).curveTo(267.12198, 210.32202, 267.02298,
                210.82901, 266.822, 211.06302);
        ((GeneralPath) shape).curveTo(266.623, 211.29402, 266.189, 211.41202,
                265.518, 211.41202);
        ((GeneralPath) shape).curveTo(264.851, 211.41202, 264.418, 211.29602,
                264.217, 211.06302);
        ((GeneralPath) shape).curveTo(264.018, 210.83102, 263.91702, 210.32402,
                263.91702, 209.54301);
        ((GeneralPath) shape).curveTo(263.91702, 208.76501, 264.01602,
                208.25702, 264.217, 208.02301);
        ((GeneralPath) shape).curveTo(264.41702, 207.79102, 264.851, 207.673,
                265.518, 207.673);
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
        ((GeneralPath) shape).moveTo(267.95, 207.726);
        ((GeneralPath) shape).lineTo(268.462, 207.726);
        ((GeneralPath) shape).lineTo(268.447, 208.22);
        ((GeneralPath) shape).lineTo(268.462, 208.231);
        ((GeneralPath) shape).curveTo(268.62302, 207.85901, 269.025, 207.672,
                269.668, 207.672);
        ((GeneralPath) shape).curveTo(270.186, 207.672, 270.536, 207.763,
                270.72, 207.94499);
        ((GeneralPath) shape).curveTo(270.902, 208.12799, 270.995, 208.47699,
                270.995, 208.99399);
        ((GeneralPath) shape).lineTo(270.995, 211.357);
        ((GeneralPath) shape).lineTo(270.464, 211.357);
        ((GeneralPath) shape).lineTo(270.464, 208.902);
        ((GeneralPath) shape).curveTo(270.464, 208.59, 270.405, 208.38199,
                270.28598, 208.273);
        ((GeneralPath) shape).curveTo(270.16797, 208.16699, 269.939, 208.112,
                269.59897, 208.112);
        ((GeneralPath) shape).curveTo(268.85397, 208.112, 268.47998, 208.466,
                268.47998, 209.172);
        ((GeneralPath) shape).lineTo(268.47998, 211.357);
        ((GeneralPath) shape).lineTo(267.94897, 211.357);
        ((GeneralPath) shape).lineTo(267.94897, 207.726);
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
        ((GeneralPath) shape).moveTo(273.879, 207.726);
        ((GeneralPath) shape).lineTo(273.879, 208.16699);
        ((GeneralPath) shape).lineTo(272.483, 208.16699);
        ((GeneralPath) shape).lineTo(272.483, 210.38899);
        ((GeneralPath) shape).curveTo(272.483, 210.777, 272.653, 210.971,
                272.999, 210.971);
        ((GeneralPath) shape).curveTo(273.34, 210.971, 273.511, 210.79799,
                273.511, 210.45);
        ((GeneralPath) shape).lineTo(273.51498, 210.271);
        ((GeneralPath) shape).lineTo(273.52197, 210.06999);
        ((GeneralPath) shape).lineTo(274.01498, 210.06999);
        ((GeneralPath) shape).lineTo(274.01898, 210.34);
        ((GeneralPath) shape).curveTo(274.01898, 211.054, 273.68097, 211.411,
                273.00198, 211.411);
        ((GeneralPath) shape).curveTo(272.30197, 211.411, 271.952, 211.11499,
                271.952, 210.51799);
        ((GeneralPath) shape).lineTo(271.952, 208.16599);
        ((GeneralPath) shape).lineTo(271.452, 208.16599);
        ((GeneralPath) shape).lineTo(271.452, 207.72499);
        ((GeneralPath) shape).lineTo(271.952, 207.72499);
        ((GeneralPath) shape).lineTo(271.952, 206.851);
        ((GeneralPath) shape).lineTo(272.483, 206.851);
        ((GeneralPath) shape).lineTo(272.483, 207.72499);
        ((GeneralPath) shape).lineTo(273.879, 207.72499);
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
        ((GeneralPath) shape).moveTo(278.261, 206.716);
        ((GeneralPath) shape).lineTo(278.261, 211.358);
        ((GeneralPath) shape).lineTo(277.677, 211.358);
        ((GeneralPath) shape).lineTo(277.677, 206.716);
        ((GeneralPath) shape).lineTo(275.99, 206.716);
        ((GeneralPath) shape).lineTo(275.99, 206.172);
        ((GeneralPath) shape).lineTo(279.933, 206.172);
        ((GeneralPath) shape).lineTo(279.933, 206.716);
        ((GeneralPath) shape).lineTo(278.261, 206.716);
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
        ((GeneralPath) shape).moveTo(283.387, 207.726);
        ((GeneralPath) shape).lineTo(283.387, 211.358);
        ((GeneralPath) shape).lineTo(282.856, 211.358);
        ((GeneralPath) shape).lineTo(282.89398, 210.883);
        ((GeneralPath) shape).lineTo(282.883, 210.872);
        ((GeneralPath) shape).curveTo(282.699, 211.23099, 282.305, 211.411,
                281.69998, 211.411);
        ((GeneralPath) shape).curveTo(280.85397, 211.411, 280.43, 210.98999,
                280.43, 210.142);
        ((GeneralPath) shape).lineTo(280.43, 207.72499);
        ((GeneralPath) shape).lineTo(280.961, 207.72499);
        ((GeneralPath) shape).lineTo(280.961, 210.142);
        ((GeneralPath) shape).curveTo(280.961, 210.471, 281.014, 210.691,
                281.124, 210.803);
        ((GeneralPath) shape).curveTo(281.232, 210.913, 281.44598, 210.97,
                281.76498, 210.97);
        ((GeneralPath) shape).curveTo(282.18198, 210.97, 282.468, 210.888,
                282.623, 210.72101);
        ((GeneralPath) shape).curveTo(282.77798, 210.55602, 282.856, 210.25201,
                282.856, 209.807);
        ((GeneralPath) shape).lineTo(282.856, 207.725);
        ((GeneralPath) shape).lineTo(283.387, 207.725);
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
        ((GeneralPath) shape).moveTo(284.42, 207.726);
        ((GeneralPath) shape).lineTo(284.95102, 207.726);
        ((GeneralPath) shape).lineTo(284.898, 208.144);
        ((GeneralPath) shape).lineTo(284.909, 208.15599);
        ((GeneralPath) shape).curveTo(285.117, 207.814, 285.465, 207.64299,
                285.948, 207.64299);
        ((GeneralPath) shape).curveTo(286.615, 207.64299, 286.949, 207.98698,
                286.949, 208.676);
        ((GeneralPath) shape).lineTo(286.946, 208.927);
        ((GeneralPath) shape).lineTo(286.42203, 208.927);
        ((GeneralPath) shape).lineTo(286.43402, 208.836);
        ((GeneralPath) shape).curveTo(286.44202, 208.741, 286.446, 208.676,
                286.446, 208.643);
        ((GeneralPath) shape).curveTo(286.446, 208.27, 286.24503, 208.084,
                285.83902, 208.084);
        ((GeneralPath) shape).curveTo(285.24802, 208.084, 284.95203, 208.449,
                284.95203, 209.182);
        ((GeneralPath) shape).lineTo(284.95203, 211.35901);
        ((GeneralPath) shape).lineTo(284.42102, 211.35901);
        ((GeneralPath) shape).lineTo(284.42102, 207.72601);
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
        ((GeneralPath) shape).moveTo(287.511, 207.726);
        ((GeneralPath) shape).lineTo(288.042, 207.726);
        ((GeneralPath) shape).lineTo(287.98898, 208.144);
        ((GeneralPath) shape).lineTo(287.99997, 208.15599);
        ((GeneralPath) shape).curveTo(288.20798, 207.814, 288.55597, 207.64299,
                289.03897, 207.64299);
        ((GeneralPath) shape).curveTo(289.70596, 207.64299, 290.03998,
                207.98698, 290.03998, 208.676);
        ((GeneralPath) shape).lineTo(290.03598, 208.927);
        ((GeneralPath) shape).lineTo(289.51297, 208.927);
        ((GeneralPath) shape).lineTo(289.52396, 208.836);
        ((GeneralPath) shape).curveTo(289.53195, 208.741, 289.53595, 208.676,
                289.53595, 208.643);
        ((GeneralPath) shape).curveTo(289.53595, 208.27, 289.33496, 208.084,
                288.92896, 208.084);
        ((GeneralPath) shape).curveTo(288.33795, 208.084, 288.04196, 208.449,
                288.04196, 209.182);
        ((GeneralPath) shape).lineTo(288.04196, 211.35901);
        ((GeneralPath) shape).lineTo(287.51096, 211.35901);
        ((GeneralPath) shape).lineTo(287.51096, 207.72601);
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
        ((GeneralPath) shape).moveTo(293.07, 209.227);
        ((GeneralPath) shape).lineTo(293.066, 209.056);
        ((GeneralPath) shape).curveTo(293.066, 208.665, 293.002, 208.409,
                292.87302, 208.29);
        ((GeneralPath) shape).curveTo(292.74402, 208.17299, 292.467, 208.114,
                292.039, 208.114);
        ((GeneralPath) shape).curveTo(291.611, 208.114, 291.332, 208.18199,
                291.203, 208.321);
        ((GeneralPath) shape).curveTo(291.076, 208.458, 291.012, 208.76,
                291.012, 209.227);
        ((GeneralPath) shape).lineTo(293.07, 209.227);
        ((GeneralPath) shape).moveTo(293.07, 210.26001);
        ((GeneralPath) shape).lineTo(293.612, 210.26001);
        ((GeneralPath) shape).lineTo(293.616, 210.393);
        ((GeneralPath) shape).curveTo(293.616, 210.76901, 293.50198, 211.033,
                293.273, 211.18501);
        ((GeneralPath) shape).curveTo(293.045, 211.335, 292.644, 211.41101,
                292.069, 211.41101);
        ((GeneralPath) shape).curveTo(291.402, 211.41101, 290.964, 211.29001,
                290.755, 211.04501);
        ((GeneralPath) shape).curveTo(290.547, 210.80202, 290.44202, 210.28702,
                290.44202, 209.50401);
        ((GeneralPath) shape).curveTo(290.44202, 208.78001, 290.54602, 208.294,
                290.756, 208.04501);
        ((GeneralPath) shape).curveTo(290.96503, 207.79802, 291.376, 207.67201,
                291.988, 207.67201);
        ((GeneralPath) shape).curveTo(292.655, 207.67201, 293.092, 207.77802,
                293.30002, 207.99501);
        ((GeneralPath) shape).curveTo(293.50702, 208.21, 293.61102, 208.66402,
                293.61102, 209.356);
        ((GeneralPath) shape).lineTo(293.61102, 209.641);
        ((GeneralPath) shape).lineTo(291.002, 209.641);
        ((GeneralPath) shape).curveTo(291.002, 210.21301, 291.06302, 210.57701,
                291.186, 210.735);
        ((GeneralPath) shape).curveTo(291.307, 210.89, 291.59302, 210.97,
                292.045, 210.97);
        ((GeneralPath) shape).curveTo(292.471, 210.97, 292.74802, 210.934,
                292.877, 210.858);
        ((GeneralPath) shape).curveTo(293.00403, 210.784, 293.06802, 210.623,
                293.06802, 210.37401);
        ((GeneralPath) shape).lineTo(293.06802, 210.26001);
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
        ((GeneralPath) shape).moveTo(296.37, 207.726);
        ((GeneralPath) shape).lineTo(296.37, 208.16699);
        ((GeneralPath) shape).lineTo(294.974, 208.16699);
        ((GeneralPath) shape).lineTo(294.974, 210.38899);
        ((GeneralPath) shape).curveTo(294.974, 210.777, 295.144, 210.971,
                295.49, 210.971);
        ((GeneralPath) shape).curveTo(295.831, 210.971, 296.00198, 210.79799,
                296.00198, 210.45);
        ((GeneralPath) shape).lineTo(296.00497, 210.271);
        ((GeneralPath) shape).lineTo(296.01297, 210.06999);
        ((GeneralPath) shape).lineTo(296.50598, 210.06999);
        ((GeneralPath) shape).lineTo(296.50998, 210.34);
        ((GeneralPath) shape).curveTo(296.50998, 211.054, 296.17297, 211.411,
                295.49298, 211.411);
        ((GeneralPath) shape).curveTo(294.79398, 211.411, 294.443, 211.11499,
                294.443, 210.51799);
        ((GeneralPath) shape).lineTo(294.443, 208.16599);
        ((GeneralPath) shape).lineTo(293.942, 208.16599);
        ((GeneralPath) shape).lineTo(293.942, 207.72499);
        ((GeneralPath) shape).lineTo(294.443, 207.72499);
        ((GeneralPath) shape).lineTo(294.443, 206.851);
        ((GeneralPath) shape).lineTo(294.974, 206.851);
        ((GeneralPath) shape).lineTo(294.974, 207.72499);
        ((GeneralPath) shape).lineTo(296.37, 207.72499);
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
        ((GeneralPath) shape).moveTo(299.531, 206.172);
        ((GeneralPath) shape).lineTo(299.531, 210.815);
        ((GeneralPath) shape).lineTo(302.193, 210.815);
        ((GeneralPath) shape).lineTo(302.193, 211.358);
        ((GeneralPath) shape).lineTo(298.947, 211.358);
        ((GeneralPath) shape).lineTo(298.947, 206.172);
        ((GeneralPath) shape).lineTo(299.531, 206.172);
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
        ((GeneralPath) shape).moveTo(304.185, 208.114);
        ((GeneralPath) shape).curveTo(303.715, 208.114, 303.425, 208.19,
                303.311, 208.343);
        ((GeneralPath) shape).curveTo(303.199, 208.495, 303.142, 208.896,
                303.142, 209.542);
        ((GeneralPath) shape).curveTo(303.142, 210.188, 303.197, 210.587,
                303.311, 210.74101);
        ((GeneralPath) shape).curveTo(303.422, 210.893, 303.715, 210.97101,
                304.185, 210.97101);
        ((GeneralPath) shape).curveTo(304.65698, 210.97101, 304.949, 210.895,
                305.063, 210.74101);
        ((GeneralPath) shape).curveTo(305.17398, 210.58902, 305.231, 210.18802,
                305.231, 209.542);
        ((GeneralPath) shape).curveTo(305.231, 208.89601, 305.176, 208.49701,
                305.063, 208.343);
        ((GeneralPath) shape).curveTo(304.94998, 208.19101, 304.659, 208.114,
                304.185, 208.114);
        ((GeneralPath) shape).moveTo(304.185, 207.673);
        ((GeneralPath) shape).curveTo(304.854, 207.673, 305.289, 207.789,
                305.48898, 208.02301);
        ((GeneralPath) shape).curveTo(305.688, 208.25401, 305.78897, 208.76201,
                305.78897, 209.54301);
        ((GeneralPath) shape).curveTo(305.78897, 210.32202, 305.68997,
                210.82901, 305.48898, 211.06302);
        ((GeneralPath) shape).curveTo(305.28998, 211.29402, 304.856, 211.41202,
                304.185, 211.41202);
        ((GeneralPath) shape).curveTo(303.518, 211.41202, 303.085, 211.29602,
                302.884, 211.06302);
        ((GeneralPath) shape).curveTo(302.685, 210.83102, 302.584, 210.32402,
                302.584, 209.54301);
        ((GeneralPath) shape).curveTo(302.584, 208.76501, 302.683, 208.25702,
                302.884, 208.02301);
        ((GeneralPath) shape).curveTo(303.083, 207.79102, 303.517, 207.673,
                304.185, 207.673);
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
        ((GeneralPath) shape).moveTo(309.107, 210.048);
        ((GeneralPath) shape).lineTo(309.638, 210.048);
        ((GeneralPath) shape).lineTo(309.641, 210.24501);
        ((GeneralPath) shape).curveTo(309.641, 211.022, 309.133, 211.41101,
                308.11798, 211.41101);
        ((GeneralPath) shape).curveTo(307.46597, 211.41101, 307.03497,
                211.29001, 306.82397, 211.04301);
        ((GeneralPath) shape).curveTo(306.61597, 210.79802, 306.50998,
                210.29102, 306.50998, 209.52301);
        ((GeneralPath) shape).curveTo(306.50998, 208.80501, 306.61597,
                208.31702, 306.83, 208.059);
        ((GeneralPath) shape).curveTo(307.043, 207.80301, 307.44998, 207.673,
                308.05, 207.673);
        ((GeneralPath) shape).curveTo(308.638, 207.673, 309.039, 207.75801,
                309.25497, 207.933);
        ((GeneralPath) shape).curveTo(309.46997, 208.106, 309.57797, 208.433,
                309.57797, 208.912);
        ((GeneralPath) shape).lineTo(309.04697, 208.912);
        ((GeneralPath) shape).lineTo(309.04697, 208.817);
        ((GeneralPath) shape).curveTo(309.04697, 208.535, 308.97897, 208.349,
                308.83795, 208.254);
        ((GeneralPath) shape).curveTo(308.69995, 208.161, 308.42294, 208.114,
                308.00797, 208.114);
        ((GeneralPath) shape).curveTo(307.60995, 208.114, 307.35196, 208.201,
                307.23798, 208.38);
        ((GeneralPath) shape).curveTo(307.12396, 208.556, 307.067, 208.95401,
                307.067, 209.573);
        ((GeneralPath) shape).curveTo(307.067, 210.175, 307.13098, 210.55899,
                307.262, 210.724);
        ((GeneralPath) shape).curveTo(307.391, 210.888, 307.697, 210.971,
                308.17398, 210.971);
        ((GeneralPath) shape).curveTo(308.577, 210.971, 308.83298, 210.918,
                308.94397, 210.81);
        ((GeneralPath) shape).curveTo(309.05298, 210.703, 309.10696, 210.448,
                309.10696, 210.048);
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
        ((GeneralPath) shape).moveTo(310.913, 206.172);
        ((GeneralPath) shape).lineTo(310.913, 209.185);
        ((GeneralPath) shape).lineTo(311.156, 209.185);
        ((GeneralPath) shape).lineTo(312.438, 207.726);
        ((GeneralPath) shape).lineTo(313.105, 207.726);
        ((GeneralPath) shape).lineTo(311.555, 209.394);
        ((GeneralPath) shape).lineTo(313.386, 211.358);
        ((GeneralPath) shape).lineTo(312.669, 211.358);
        ((GeneralPath) shape).lineTo(311.13, 209.599);
        ((GeneralPath) shape).lineTo(310.913, 209.599);
        ((GeneralPath) shape).lineTo(310.913, 211.358);
        ((GeneralPath) shape).lineTo(310.383, 211.358);
        ((GeneralPath) shape).lineTo(310.383, 206.172);
        ((GeneralPath) shape).lineTo(310.913, 206.172);
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
        ((GeneralPath) shape).moveTo(316.243, 209.227);
        ((GeneralPath) shape).lineTo(316.239, 209.056);
        ((GeneralPath) shape).curveTo(316.239, 208.665, 316.17502, 208.409,
                316.04602, 208.29);
        ((GeneralPath) shape).curveTo(315.91702, 208.17299, 315.64102, 208.114,
                315.212, 208.114);
        ((GeneralPath) shape).curveTo(314.78302, 208.114, 314.505, 208.18199,
                314.376, 208.321);
        ((GeneralPath) shape).curveTo(314.249, 208.458, 314.185, 208.76,
                314.185, 209.227);
        ((GeneralPath) shape).lineTo(316.243, 209.227);
        ((GeneralPath) shape).moveTo(316.243, 210.26001);
        ((GeneralPath) shape).lineTo(316.786, 210.26001);
        ((GeneralPath) shape).lineTo(316.79, 210.393);
        ((GeneralPath) shape).curveTo(316.79, 210.76901, 316.676, 211.033,
                316.446, 211.18501);
        ((GeneralPath) shape).curveTo(316.21802, 211.335, 315.81702, 211.41101,
                315.242, 211.41101);
        ((GeneralPath) shape).curveTo(314.575, 211.41101, 314.137, 211.29001,
                313.92902, 211.04501);
        ((GeneralPath) shape).curveTo(313.72, 210.80202, 313.61603, 210.28702,
                313.61603, 209.50401);
        ((GeneralPath) shape).curveTo(313.61603, 208.78001, 313.72003, 208.294,
                313.93002, 208.04501);
        ((GeneralPath) shape).curveTo(314.13803, 207.79802, 314.55002,
                207.67201, 315.16202, 207.67201);
        ((GeneralPath) shape).curveTo(315.829, 207.67201, 316.26602, 207.77802,
                316.47403, 207.99501);
        ((GeneralPath) shape).curveTo(316.68103, 208.21, 316.78604, 208.66402,
                316.78604, 209.356);
        ((GeneralPath) shape).lineTo(316.78604, 209.641);
        ((GeneralPath) shape).lineTo(314.17703, 209.641);
        ((GeneralPath) shape).curveTo(314.17703, 210.21301, 314.23804,
                210.57701, 314.36203, 210.735);
        ((GeneralPath) shape).curveTo(314.48303, 210.89, 314.76904, 210.97,
                315.22003, 210.97);
        ((GeneralPath) shape).curveTo(315.64703, 210.97, 315.92303, 210.934,
                316.05203, 210.858);
        ((GeneralPath) shape).curveTo(316.18002, 210.784, 316.24304, 210.623,
                316.24304, 210.37401);
        ((GeneralPath) shape).lineTo(316.24304, 210.26001);
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
        ((GeneralPath) shape).moveTo(319.054, 208.114);
        ((GeneralPath) shape).curveTo(318.663, 208.114, 318.40198, 208.203,
                318.27298, 208.385);
        ((GeneralPath) shape).curveTo(318.14398, 208.56499, 318.08, 208.93199,
                318.08, 209.485);
        ((GeneralPath) shape).curveTo(318.08, 210.093, 318.14297, 210.492,
                318.271, 210.684);
        ((GeneralPath) shape).curveTo(318.398, 210.87401, 318.664, 210.97101,
                319.066, 210.97101);
        ((GeneralPath) shape).curveTo(319.502, 210.97101, 319.78702, 210.876,
                319.919, 210.684);
        ((GeneralPath) shape).curveTo(320.05002, 210.494, 320.116, 210.078,
                320.116, 209.436);
        ((GeneralPath) shape).curveTo(320.116, 208.918, 320.04498, 208.56801,
                319.898, 208.386);
        ((GeneralPath) shape).curveTo(319.754, 208.205, 319.471, 208.114,
                319.05402, 208.114);
        ((GeneralPath) shape).moveTo(320.65002, 206.172);
        ((GeneralPath) shape).lineTo(320.65002, 211.359);
        ((GeneralPath) shape).lineTo(320.11902, 211.359);
        ((GeneralPath) shape).lineTo(320.14603, 210.888);
        ((GeneralPath) shape).lineTo(320.13004, 210.884);
        ((GeneralPath) shape).curveTo(319.96304, 211.23601, 319.57605, 211.412,
                318.96603, 211.412);
        ((GeneralPath) shape).curveTo(318.41003, 211.412, 318.03104, 211.27701,
                317.82602, 211.006);
        ((GeneralPath) shape).curveTo(317.62302, 210.736, 317.52002, 210.231,
                317.52002, 209.48999);
        ((GeneralPath) shape).curveTo(317.52002, 208.80899, 317.62302,
                208.33699, 317.82803, 208.07098);
        ((GeneralPath) shape).curveTo(318.03302, 207.80698, 318.39804,
                207.67398, 318.92804, 207.67398);
        ((GeneralPath) shape).curveTo(319.58804, 207.67398, 319.98105,
                207.84499, 320.10703, 208.18698);
        ((GeneralPath) shape).lineTo(320.11902, 208.17998);
        ((GeneralPath) shape).lineTo(320.11902, 206.17398);
        ((GeneralPath) shape).lineTo(320.65002, 206.17398);
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
        ((GeneralPath) shape).moveTo(339.511, 206.667);
        ((GeneralPath) shape).lineTo(339.511, 208.433);
        ((GeneralPath) shape).lineTo(342.139, 208.433);
        ((GeneralPath) shape).lineTo(342.139, 208.927);
        ((GeneralPath) shape).lineTo(339.511, 208.927);
        ((GeneralPath) shape).lineTo(339.511, 210.864);
        ((GeneralPath) shape).lineTo(342.252, 210.864);
        ((GeneralPath) shape).lineTo(342.252, 211.358);
        ((GeneralPath) shape).lineTo(338.927, 211.358);
        ((GeneralPath) shape).lineTo(338.927, 206.172);
        ((GeneralPath) shape).lineTo(342.252, 206.172);
        ((GeneralPath) shape).lineTo(342.252, 206.667);
        ((GeneralPath) shape).lineTo(339.511, 206.667);
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
        ((GeneralPath) shape).moveTo(342.977, 207.726);
        ((GeneralPath) shape).lineTo(343.48898, 207.726);
        ((GeneralPath) shape).lineTo(343.47397, 208.22);
        ((GeneralPath) shape).lineTo(343.48898, 208.231);
        ((GeneralPath) shape).curveTo(343.65, 207.85901, 344.05197, 207.672,
                344.69498, 207.672);
        ((GeneralPath) shape).curveTo(345.21298, 207.672, 345.563, 207.763,
                345.748, 207.94499);
        ((GeneralPath) shape).curveTo(345.93, 208.12799, 346.02197, 208.47699,
                346.02197, 208.99399);
        ((GeneralPath) shape).lineTo(346.02197, 211.357);
        ((GeneralPath) shape).lineTo(345.49097, 211.357);
        ((GeneralPath) shape).lineTo(345.49097, 208.902);
        ((GeneralPath) shape).curveTo(345.49097, 208.59, 345.43198, 208.38199,
                345.31296, 208.273);
        ((GeneralPath) shape).curveTo(345.19495, 208.16699, 344.96597, 208.112,
                344.62595, 208.112);
        ((GeneralPath) shape).curveTo(343.88095, 208.112, 343.50793, 208.466,
                343.50793, 209.172);
        ((GeneralPath) shape).lineTo(343.50793, 211.357);
        ((GeneralPath) shape).lineTo(342.97693, 211.357);
        ((GeneralPath) shape).lineTo(342.97693, 207.726);
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
        ((GeneralPath) shape).moveTo(348.437, 208.114);
        ((GeneralPath) shape).curveTo(348.019, 208.114, 347.75, 208.201,
                347.625, 208.378);
        ((GeneralPath) shape).curveTo(347.502, 208.55301, 347.438, 208.93901,
                347.438, 209.535);
        ((GeneralPath) shape).curveTo(347.438, 210.13501, 347.5, 210.52501,
                347.625, 210.703);
        ((GeneralPath) shape).curveTo(347.748, 210.881, 348.019, 210.97101,
                348.437, 210.97101);
        ((GeneralPath) shape).curveTo(348.858, 210.97101, 349.13602, 210.87401,
                349.27103, 210.677);
        ((GeneralPath) shape).curveTo(349.40402, 210.481, 349.47202, 210.073,
                349.47202, 209.452);
        ((GeneralPath) shape).curveTo(349.47202, 208.905, 349.406, 208.54599,
                349.27103, 208.373);
        ((GeneralPath) shape).curveTo(349.13803, 208.201, 348.85904, 208.114,
                348.437, 208.114);
        ((GeneralPath) shape).moveTo(350.018, 207.726);
        ((GeneralPath) shape).lineTo(350.018, 211.594);
        ((GeneralPath) shape).curveTo(350.018, 212.12, 349.904, 212.483,
                349.676, 212.68);
        ((GeneralPath) shape).curveTo(349.448, 212.87799, 349.031, 212.976,
                348.425, 212.976);
        ((GeneralPath) shape).curveTo(347.88498, 212.976, 347.51498, 212.893,
                347.318, 212.726);
        ((GeneralPath) shape).curveTo(347.121, 212.55899, 347.022, 212.247,
                347.022, 211.788);
        ((GeneralPath) shape).lineTo(347.534, 211.788);
        ((GeneralPath) shape).curveTo(347.534, 212.09999, 347.592, 212.301,
                347.707, 212.396);
        ((GeneralPath) shape).curveTo(347.821, 212.489, 348.073, 212.537,
                348.464, 212.537);
        ((GeneralPath) shape).curveTo(348.867, 212.537, 349.13898, 212.476,
                349.279, 212.354);
        ((GeneralPath) shape).curveTo(349.417, 212.23201, 349.487, 211.99501,
                349.487, 211.64);
        ((GeneralPath) shape).lineTo(349.487, 210.911);
        ((GeneralPath) shape).lineTo(349.476, 210.907);
        ((GeneralPath) shape).curveTo(349.33902, 211.243, 348.945, 211.412,
                348.29202, 211.412);
        ((GeneralPath) shape).curveTo(347.75803, 211.412, 347.38803, 211.281,
                347.18503, 211.016);
        ((GeneralPath) shape).curveTo(346.98502, 210.752, 346.88202, 210.26901,
                346.88202, 209.56601);
        ((GeneralPath) shape).curveTo(346.88202, 208.84001, 346.98502, 208.343,
                347.191, 208.07501);
        ((GeneralPath) shape).curveTo(347.396, 207.809, 347.77902, 207.67401,
                348.338, 207.67401);
        ((GeneralPath) shape).curveTo(348.931, 207.67401, 349.32, 207.85701,
                349.502, 208.22101);
        ((GeneralPath) shape).lineTo(349.513, 208.21701);
        ((GeneralPath) shape).lineTo(349.487, 207.727);
        ((GeneralPath) shape).lineTo(350.018, 207.727);
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
        ((GeneralPath) shape).moveTo(351.476, 207.726);
        ((GeneralPath) shape).lineTo(351.476, 211.358);
        ((GeneralPath) shape).lineTo(350.945, 211.358);
        ((GeneralPath) shape).lineTo(350.945, 207.726);
        ((GeneralPath) shape).lineTo(351.476, 207.726);
        ((GeneralPath) shape).moveTo(351.476, 206.172);
        ((GeneralPath) shape).lineTo(351.476, 206.769);
        ((GeneralPath) shape).lineTo(350.945, 206.769);
        ((GeneralPath) shape).lineTo(350.945, 206.172);
        ((GeneralPath) shape).lineTo(351.476, 206.172);
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
        ((GeneralPath) shape).moveTo(352.404, 207.726);
        ((GeneralPath) shape).lineTo(352.916, 207.726);
        ((GeneralPath) shape).lineTo(352.90097, 208.22);
        ((GeneralPath) shape).lineTo(352.916, 208.231);
        ((GeneralPath) shape).curveTo(353.077, 207.85901, 353.47897, 207.672,
                354.12198, 207.672);
        ((GeneralPath) shape).curveTo(354.63998, 207.672, 354.99, 207.763,
                355.175, 207.94499);
        ((GeneralPath) shape).curveTo(355.357, 208.12799, 355.44897, 208.47699,
                355.44897, 208.99399);
        ((GeneralPath) shape).lineTo(355.44897, 211.357);
        ((GeneralPath) shape).lineTo(354.91898, 211.357);
        ((GeneralPath) shape).lineTo(354.91898, 208.902);
        ((GeneralPath) shape).curveTo(354.91898, 208.59, 354.85898, 208.38199,
                354.74, 208.273);
        ((GeneralPath) shape).curveTo(354.623, 208.16699, 354.393, 208.112,
                354.05298, 208.112);
        ((GeneralPath) shape).curveTo(353.30798, 208.112, 352.93497, 208.466,
                352.93497, 209.172);
        ((GeneralPath) shape).lineTo(352.93497, 211.357);
        ((GeneralPath) shape).lineTo(352.40396, 211.357);
        ((GeneralPath) shape).lineTo(352.40396, 207.726);
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
        ((GeneralPath) shape).moveTo(358.934, 209.227);
        ((GeneralPath) shape).lineTo(358.93, 209.056);
        ((GeneralPath) shape).curveTo(358.93, 208.665, 358.866, 208.409,
                358.737, 208.29);
        ((GeneralPath) shape).curveTo(358.608, 208.17299, 358.332, 208.114,
                357.90298, 208.114);
        ((GeneralPath) shape).curveTo(357.474, 208.114, 357.19598, 208.18199,
                357.067, 208.321);
        ((GeneralPath) shape).curveTo(356.939, 208.458, 356.87598, 208.76,
                356.87598, 209.227);
        ((GeneralPath) shape).lineTo(358.934, 209.227);
        ((GeneralPath) shape).moveTo(358.934, 210.26001);
        ((GeneralPath) shape).lineTo(359.47598, 210.26001);
        ((GeneralPath) shape).lineTo(359.47998, 210.393);
        ((GeneralPath) shape).curveTo(359.47998, 210.76901, 359.36597, 211.033,
                359.137, 211.18501);
        ((GeneralPath) shape).curveTo(358.909, 211.335, 358.507, 211.41101,
                357.93298, 211.41101);
        ((GeneralPath) shape).curveTo(357.26498, 211.41101, 356.82797,
                211.29001, 356.619, 211.04501);
        ((GeneralPath) shape).curveTo(356.41098, 210.80202, 356.306, 210.28702,
                356.306, 209.50401);
        ((GeneralPath) shape).curveTo(356.306, 208.78001, 356.41, 208.294,
                356.62, 208.04501);
        ((GeneralPath) shape).curveTo(356.829, 207.79802, 357.24, 207.67201,
                357.852, 207.67201);
        ((GeneralPath) shape).curveTo(358.52, 207.67201, 358.956, 207.77802,
                359.16498, 207.99501);
        ((GeneralPath) shape).curveTo(359.37097, 208.21, 359.47598, 208.66402,
                359.47598, 209.356);
        ((GeneralPath) shape).lineTo(359.47598, 209.641);
        ((GeneralPath) shape).lineTo(356.86697, 209.641);
        ((GeneralPath) shape).curveTo(356.86697, 210.21301, 356.929, 210.57701,
                357.05197, 210.735);
        ((GeneralPath) shape).curveTo(357.17297, 210.89, 357.45898, 210.97,
                357.90997, 210.97);
        ((GeneralPath) shape).curveTo(358.33698, 210.97, 358.61398, 210.934,
                358.74298, 210.858);
        ((GeneralPath) shape).curveTo(358.87, 210.784, 358.934, 210.623,
                358.934, 210.37401);
        ((GeneralPath) shape).lineTo(358.934, 210.26001);
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
        ((GeneralPath) shape).moveTo(366.473, 206.172);
        ((GeneralPath) shape).lineTo(366.473, 211.358);
        ((GeneralPath) shape).lineTo(365.889, 211.358);
        ((GeneralPath) shape).lineTo(365.889, 208.954);
        ((GeneralPath) shape).lineTo(362.862, 208.954);
        ((GeneralPath) shape).lineTo(362.862, 211.358);
        ((GeneralPath) shape).lineTo(362.278, 211.358);
        ((GeneralPath) shape).lineTo(362.278, 206.172);
        ((GeneralPath) shape).lineTo(362.862, 206.172);
        ((GeneralPath) shape).lineTo(362.862, 208.459);
        ((GeneralPath) shape).lineTo(365.889, 208.459);
        ((GeneralPath) shape).lineTo(365.889, 206.172);
        ((GeneralPath) shape).lineTo(366.473, 206.172);
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
        ((GeneralPath) shape).moveTo(367.955, 207.726);
        ((GeneralPath) shape).lineTo(367.955, 211.358);
        ((GeneralPath) shape).lineTo(367.42398, 211.358);
        ((GeneralPath) shape).lineTo(367.42398, 207.726);
        ((GeneralPath) shape).lineTo(367.955, 207.726);
        ((GeneralPath) shape).moveTo(367.955, 206.172);
        ((GeneralPath) shape).lineTo(367.955, 206.769);
        ((GeneralPath) shape).lineTo(367.42398, 206.769);
        ((GeneralPath) shape).lineTo(367.42398, 206.172);
        ((GeneralPath) shape).lineTo(367.955, 206.172);
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
        ((GeneralPath) shape).moveTo(370.807, 207.726);
        ((GeneralPath) shape).lineTo(370.807, 208.16699);
        ((GeneralPath) shape).lineTo(369.411, 208.16699);
        ((GeneralPath) shape).lineTo(369.411, 210.38899);
        ((GeneralPath) shape).curveTo(369.411, 210.777, 369.58102, 210.971,
                369.927, 210.971);
        ((GeneralPath) shape).curveTo(370.268, 210.971, 370.439, 210.79799,
                370.439, 210.45);
        ((GeneralPath) shape).lineTo(370.443, 210.271);
        ((GeneralPath) shape).lineTo(370.451, 210.06999);
        ((GeneralPath) shape).lineTo(370.943, 210.06999);
        ((GeneralPath) shape).lineTo(370.947, 210.34);
        ((GeneralPath) shape).curveTo(370.947, 211.054, 370.61, 211.411,
                369.93, 211.411);
        ((GeneralPath) shape).curveTo(369.231, 211.411, 368.88, 211.11499,
                368.88, 210.51799);
        ((GeneralPath) shape).lineTo(368.88, 208.16599);
        ((GeneralPath) shape).lineTo(368.379, 208.16599);
        ((GeneralPath) shape).lineTo(368.379, 207.72499);
        ((GeneralPath) shape).lineTo(368.88, 207.72499);
        ((GeneralPath) shape).lineTo(368.88, 206.851);
        ((GeneralPath) shape).lineTo(369.411, 206.851);
        ((GeneralPath) shape).lineTo(369.411, 207.72499);
        ((GeneralPath) shape).lineTo(370.807, 207.72499);
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
        stroke = new BasicStroke(0.973f, 0, 0, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(333.774, 211.563);
        ((GeneralPath) shape).curveTo(333.774, 212.293, 333.18198, 212.886,
                332.44897, 212.886);
        ((GeneralPath) shape).lineTo(325.71896, 212.886);
        ((GeneralPath) shape).curveTo(324.98798, 212.886, 324.39395, 212.293,
                324.39395, 211.563);
        ((GeneralPath) shape).lineTo(324.39395, 204.764);
        ((GeneralPath) shape).curveTo(324.39395, 204.031, 324.98795, 203.438,
                325.71896, 203.438);
        ((GeneralPath) shape).lineTo(332.44897, 203.438);
        ((GeneralPath) shape).curveTo(333.18198, 203.438, 333.774, 204.031,
                333.774, 204.764);
        ((GeneralPath) shape).lineTo(333.774, 211.563);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        ((GeneralPath) shape).moveTo(257.742, 220.436);
        ((GeneralPath) shape).lineTo(259.43, 220.436);
        ((GeneralPath) shape).curveTo(259.874, 220.436, 260.177, 220.37001,
                260.34, 220.233);
        ((GeneralPath) shape).curveTo(260.501, 220.098, 260.583, 219.843,
                260.583, 219.467);
        ((GeneralPath) shape).curveTo(260.583, 219.0, 260.52402, 218.694,
                260.403, 218.551);
        ((GeneralPath) shape).curveTo(260.28302, 218.40999, 260.026, 218.33899,
                259.63202, 218.33899);
        ((GeneralPath) shape).lineTo(257.743, 218.33899);
        ((GeneralPath) shape).lineTo(257.743, 220.43599);
        ((GeneralPath) shape).moveTo(257.15802, 223.02998);
        ((GeneralPath) shape).lineTo(257.15802, 217.84398);
        ((GeneralPath) shape).lineTo(259.62302, 217.84398);
        ((GeneralPath) shape).curveTo(260.17902, 217.84398, 260.571, 217.95398,
                260.799, 218.17497);
        ((GeneralPath) shape).curveTo(261.027, 218.39597, 261.14, 218.77698,
                261.14, 219.32198);
        ((GeneralPath) shape).curveTo(261.14, 219.80098, 261.079, 220.13298,
                260.954, 220.32298);
        ((GeneralPath) shape).curveTo(260.83002, 220.51099, 260.59402,
                220.63298, 260.245, 220.68999);
        ((GeneralPath) shape).lineTo(260.245, 220.70198);
        ((GeneralPath) shape).curveTo(260.793, 220.74197, 261.068, 221.07597,
                261.068, 221.70499);
        ((GeneralPath) shape).lineTo(261.068, 223.03099);
        ((GeneralPath) shape).lineTo(260.48398, 223.03099);
        ((GeneralPath) shape).lineTo(260.48398, 221.838);
        ((GeneralPath) shape).curveTo(260.48398, 221.234, 260.222, 220.93,
                259.69897, 220.93);
        ((GeneralPath) shape).lineTo(257.74197, 220.93);
        ((GeneralPath) shape).lineTo(257.74197, 223.03099);
        ((GeneralPath) shape).lineTo(257.15796, 223.03099);
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
        ((GeneralPath) shape).moveTo(264.491, 220.899);
        ((GeneralPath) shape).lineTo(264.487, 220.728);
        ((GeneralPath) shape).curveTo(264.487, 220.33699, 264.423, 220.08,
                264.294, 219.96199);
        ((GeneralPath) shape).curveTo(264.165, 219.844, 263.889, 219.78499,
                263.46, 219.78499);
        ((GeneralPath) shape).curveTo(263.031, 219.78499, 262.753, 219.85298,
                262.624, 219.99199);
        ((GeneralPath) shape).curveTo(262.49698, 220.12898, 262.43298,
                220.43098, 262.43298, 220.898);
        ((GeneralPath) shape).lineTo(264.491, 220.898);
        ((GeneralPath) shape).moveTo(264.491, 221.933);
        ((GeneralPath) shape).lineTo(265.033, 221.933);
        ((GeneralPath) shape).lineTo(265.037, 222.066);
        ((GeneralPath) shape).curveTo(265.037, 222.442, 264.92297, 222.706,
                264.694, 222.858);
        ((GeneralPath) shape).curveTo(264.466, 223.008, 264.065, 223.084,
                263.49, 223.084);
        ((GeneralPath) shape).curveTo(262.823, 223.084, 262.38498, 222.962,
                262.176, 222.717);
        ((GeneralPath) shape).curveTo(261.968, 222.474, 261.863, 221.959,
                261.863, 221.176);
        ((GeneralPath) shape).curveTo(261.863, 220.452, 261.967, 219.96599,
                262.177, 219.717);
        ((GeneralPath) shape).curveTo(262.38602, 219.47, 262.797, 219.345,
                263.409, 219.345);
        ((GeneralPath) shape).curveTo(264.076, 219.345, 264.513, 219.451,
                264.721, 219.668);
        ((GeneralPath) shape).curveTo(264.928, 219.883, 265.032, 220.337,
                265.032, 221.028);
        ((GeneralPath) shape).lineTo(265.032, 221.313);
        ((GeneralPath) shape).lineTo(262.423, 221.313);
        ((GeneralPath) shape).curveTo(262.423, 221.88501, 262.484, 222.25,
                262.607, 222.407);
        ((GeneralPath) shape).curveTo(262.728, 222.563, 263.014, 222.642,
                263.466, 222.642);
        ((GeneralPath) shape).curveTo(263.892, 222.642, 264.169, 222.606,
                264.298, 222.53);
        ((GeneralPath) shape).curveTo(264.42502, 222.456, 264.489, 222.294,
                264.489, 222.045);
        ((GeneralPath) shape).lineTo(264.489, 221.933);
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
        ((GeneralPath) shape).moveTo(267.275, 221.233);
        ((GeneralPath) shape).curveTo(266.87, 221.233, 266.604, 221.27501,
                266.481, 221.362);
        ((GeneralPath) shape).curveTo(266.36, 221.448, 266.297, 221.632,
                266.297, 221.91699);
        ((GeneralPath) shape).curveTo(266.297, 222.209, 266.358, 222.405,
                266.479, 222.49998);
        ((GeneralPath) shape).curveTo(266.6, 222.59499, 266.84702, 222.64299,
                267.21802, 222.64299);
        ((GeneralPath) shape).curveTo(267.96503, 222.64299, 268.341, 222.415,
                268.341, 221.95898);
        ((GeneralPath) shape).curveTo(268.341, 221.67398, 268.269, 221.47998,
                268.12302, 221.38098);
        ((GeneralPath) shape).curveTo(267.979, 221.28297, 267.696, 221.23299,
                267.27502, 221.23299);
        ((GeneralPath) shape).moveTo(266.42203, 220.41699);
        ((GeneralPath) shape).lineTo(265.89502, 220.41699);
        ((GeneralPath) shape).curveTo(265.89502, 219.995, 265.99002, 219.70999,
                266.18103, 219.564);
        ((GeneralPath) shape).curveTo(266.37003, 219.41899, 266.74402, 219.346,
                267.30203, 219.346);
        ((GeneralPath) shape).curveTo(267.90503, 219.346, 268.31403, 219.435,
                268.52704, 219.614);
        ((GeneralPath) shape).curveTo(268.73904, 219.793, 268.84604, 220.133,
                268.84604, 220.635);
        ((GeneralPath) shape).lineTo(268.84604, 223.032);
        ((GeneralPath) shape).lineTo(268.31503, 223.032);
        ((GeneralPath) shape).lineTo(268.35703, 222.64);
        ((GeneralPath) shape).lineTo(268.34604, 222.637);
        ((GeneralPath) shape).curveTo(268.14505, 222.935, 267.73303, 223.08499,
                267.11404, 223.08499);
        ((GeneralPath) shape).curveTo(266.20004, 223.08499, 265.74106,
                222.71599, 265.74106, 221.97899);
        ((GeneralPath) shape).curveTo(265.74106, 221.54199, 265.84406,
                221.23898, 266.04807, 221.07098);
        ((GeneralPath) shape).curveTo(266.25305, 220.90398, 266.62308,
                220.82098, 267.15906, 220.82098);
        ((GeneralPath) shape).curveTo(267.79605, 220.82098, 268.17807,
                220.94598, 268.30505, 221.19699);
        ((GeneralPath) shape).lineTo(268.31604, 221.193);
        ((GeneralPath) shape).lineTo(268.31604, 220.75299);
        ((GeneralPath) shape).curveTo(268.31604, 220.33899, 268.25903,
                220.06898, 268.14603, 219.94499);
        ((GeneralPath) shape).curveTo(268.032, 219.823, 267.78403, 219.76099,
                267.39902, 219.76099);
        ((GeneralPath) shape).curveTo(266.747, 219.76099, 266.42, 219.943,
                266.42, 220.31198);
        ((GeneralPath) shape).curveTo(266.418, 220.32698, 266.418, 220.36298,
                266.42203, 220.41698);
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
        ((GeneralPath) shape).moveTo(269.882, 219.398);
        ((GeneralPath) shape).lineTo(270.413, 219.398);
        ((GeneralPath) shape).lineTo(270.36, 219.816);
        ((GeneralPath) shape).lineTo(270.37097, 219.827);
        ((GeneralPath) shape).curveTo(270.57898, 219.485, 270.92596, 219.314,
                271.40997, 219.314);
        ((GeneralPath) shape).curveTo(272.07697, 219.314, 272.41098, 219.65799,
                272.41098, 220.34799);
        ((GeneralPath) shape).lineTo(272.40698, 220.59799);
        ((GeneralPath) shape).lineTo(271.88397, 220.59799);
        ((GeneralPath) shape).lineTo(271.89597, 220.50699);
        ((GeneralPath) shape).curveTo(271.90295, 220.41199, 271.90695,
                220.34698, 271.90695, 220.31299);
        ((GeneralPath) shape).curveTo(271.90695, 219.93999, 271.70596,
                219.75398, 271.30096, 219.75398);
        ((GeneralPath) shape).curveTo(270.70895, 219.75398, 270.41397,
                220.11899, 270.41397, 220.85199);
        ((GeneralPath) shape).lineTo(270.41397, 223.02899);
        ((GeneralPath) shape).lineTo(269.88297, 223.02899);
        ((GeneralPath) shape).lineTo(269.88297, 219.398);
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
        ((GeneralPath) shape).moveTo(276.693, 218.388);
        ((GeneralPath) shape).lineTo(276.693, 223.03);
        ((GeneralPath) shape).lineTo(276.109, 223.03);
        ((GeneralPath) shape).lineTo(276.109, 218.388);
        ((GeneralPath) shape).lineTo(274.421, 218.388);
        ((GeneralPath) shape).lineTo(274.421, 217.844);
        ((GeneralPath) shape).lineTo(278.365, 217.844);
        ((GeneralPath) shape).lineTo(278.365, 218.388);
        ((GeneralPath) shape).lineTo(276.693, 218.388);
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
        ((GeneralPath) shape).moveTo(281.818, 219.398);
        ((GeneralPath) shape).lineTo(281.818, 223.03);
        ((GeneralPath) shape).lineTo(281.287, 223.03);
        ((GeneralPath) shape).lineTo(281.32498, 222.555);
        ((GeneralPath) shape).lineTo(281.314, 222.543);
        ((GeneralPath) shape).curveTo(281.13, 222.902, 280.736, 223.083,
                280.13098, 223.083);
        ((GeneralPath) shape).curveTo(279.28497, 223.083, 278.861, 222.66199,
                278.861, 221.814);
        ((GeneralPath) shape).lineTo(278.861, 219.398);
        ((GeneralPath) shape).lineTo(279.392, 219.398);
        ((GeneralPath) shape).lineTo(279.392, 221.814);
        ((GeneralPath) shape).curveTo(279.392, 222.14299, 279.445, 222.36299,
                279.555, 222.47499);
        ((GeneralPath) shape).curveTo(279.663, 222.58499, 279.87698, 222.642,
                280.19598, 222.642);
        ((GeneralPath) shape).curveTo(280.61298, 222.642, 280.9, 222.56,
                281.055, 222.393);
        ((GeneralPath) shape).curveTo(281.21, 222.22801, 281.288, 221.92401,
                281.288, 221.479);
        ((GeneralPath) shape).lineTo(281.288, 219.397);
        ((GeneralPath) shape).lineTo(281.818, 219.397);
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
        ((GeneralPath) shape).moveTo(282.852, 219.398);
        ((GeneralPath) shape).lineTo(283.383, 219.398);
        ((GeneralPath) shape).lineTo(283.33, 219.816);
        ((GeneralPath) shape).lineTo(283.34097, 219.827);
        ((GeneralPath) shape).curveTo(283.54898, 219.485, 283.89697, 219.314,
                284.37997, 219.314);
        ((GeneralPath) shape).curveTo(285.04697, 219.314, 285.38098, 219.65799,
                285.38098, 220.34799);
        ((GeneralPath) shape).lineTo(285.378, 220.59799);
        ((GeneralPath) shape).lineTo(284.854, 220.59799);
        ((GeneralPath) shape).lineTo(284.866, 220.50699);
        ((GeneralPath) shape).curveTo(284.873, 220.41199, 284.878, 220.34698,
                284.878, 220.31299);
        ((GeneralPath) shape).curveTo(284.878, 219.93999, 284.677, 219.75398,
                284.271, 219.75398);
        ((GeneralPath) shape).curveTo(283.679, 219.75398, 283.384, 220.11899,
                283.384, 220.85199);
        ((GeneralPath) shape).lineTo(283.384, 223.02899);
        ((GeneralPath) shape).lineTo(282.853, 223.02899);
        ((GeneralPath) shape).lineTo(282.853, 219.398);
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
        ((GeneralPath) shape).moveTo(285.942, 219.398);
        ((GeneralPath) shape).lineTo(286.473, 219.398);
        ((GeneralPath) shape).lineTo(286.41998, 219.816);
        ((GeneralPath) shape).lineTo(286.43097, 219.827);
        ((GeneralPath) shape).curveTo(286.63898, 219.485, 286.98697, 219.314,
                287.46997, 219.314);
        ((GeneralPath) shape).curveTo(288.13696, 219.314, 288.47098, 219.65799,
                288.47098, 220.34799);
        ((GeneralPath) shape).lineTo(288.468, 220.59799);
        ((GeneralPath) shape).lineTo(287.94498, 220.59799);
        ((GeneralPath) shape).lineTo(287.95596, 220.50699);
        ((GeneralPath) shape).curveTo(287.96396, 220.41199, 287.96796,
                220.34698, 287.96796, 220.31299);
        ((GeneralPath) shape).curveTo(287.96796, 219.93999, 287.76697,
                219.75398, 287.36096, 219.75398);
        ((GeneralPath) shape).curveTo(286.76996, 219.75398, 286.47397,
                220.11899, 286.47397, 220.85199);
        ((GeneralPath) shape).lineTo(286.47397, 223.02899);
        ((GeneralPath) shape).lineTo(285.94296, 223.02899);
        ((GeneralPath) shape).lineTo(285.94296, 219.398);
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
        ((GeneralPath) shape).moveTo(291.501, 220.899);
        ((GeneralPath) shape).lineTo(291.497, 220.728);
        ((GeneralPath) shape).curveTo(291.497, 220.33699, 291.433, 220.08,
                291.30402, 219.96199);
        ((GeneralPath) shape).curveTo(291.17502, 219.844, 290.89902, 219.78499,
                290.47, 219.78499);
        ((GeneralPath) shape).curveTo(290.04102, 219.78499, 289.763, 219.85298,
                289.634, 219.99199);
        ((GeneralPath) shape).curveTo(289.507, 220.12898, 289.443, 220.43098,
                289.443, 220.898);
        ((GeneralPath) shape).lineTo(291.501, 220.898);
        ((GeneralPath) shape).moveTo(291.501, 221.933);
        ((GeneralPath) shape).lineTo(292.043, 221.933);
        ((GeneralPath) shape).lineTo(292.047, 222.066);
        ((GeneralPath) shape).curveTo(292.047, 222.442, 291.93298, 222.706,
                291.704, 222.858);
        ((GeneralPath) shape).curveTo(291.476, 223.008, 291.075, 223.084,
                290.5, 223.084);
        ((GeneralPath) shape).curveTo(289.833, 223.084, 289.395, 222.962,
                289.186, 222.717);
        ((GeneralPath) shape).curveTo(288.978, 222.474, 288.87302, 221.959,
                288.87302, 221.176);
        ((GeneralPath) shape).curveTo(288.87302, 220.452, 288.97702, 219.96599,
                289.187, 219.717);
        ((GeneralPath) shape).curveTo(289.39502, 219.47, 289.807, 219.345,
                290.419, 219.345);
        ((GeneralPath) shape).curveTo(291.086, 219.345, 291.523, 219.451,
                291.73102, 219.668);
        ((GeneralPath) shape).curveTo(291.93802, 219.883, 292.04202, 220.337,
                292.04202, 221.028);
        ((GeneralPath) shape).lineTo(292.04202, 221.313);
        ((GeneralPath) shape).lineTo(289.433, 221.313);
        ((GeneralPath) shape).curveTo(289.433, 221.88501, 289.49402, 222.25,
                289.617, 222.407);
        ((GeneralPath) shape).curveTo(289.738, 222.563, 290.02402, 222.642,
                290.476, 222.642);
        ((GeneralPath) shape).curveTo(290.902, 222.642, 291.17902, 222.606,
                291.308, 222.53);
        ((GeneralPath) shape).curveTo(291.43503, 222.456, 291.49902, 222.294,
                291.49902, 222.045);
        ((GeneralPath) shape).lineTo(291.49902, 221.933);
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
        ((GeneralPath) shape).moveTo(294.801, 219.398);
        ((GeneralPath) shape).lineTo(294.801, 219.838);
        ((GeneralPath) shape).lineTo(293.405, 219.838);
        ((GeneralPath) shape).lineTo(293.405, 222.061);
        ((GeneralPath) shape).curveTo(293.405, 222.449, 293.575, 222.642,
                293.921, 222.642);
        ((GeneralPath) shape).curveTo(294.262, 222.642, 294.43298, 222.469,
                294.43298, 222.122);
        ((GeneralPath) shape).lineTo(294.43597, 221.943);
        ((GeneralPath) shape).lineTo(294.44397, 221.74199);
        ((GeneralPath) shape).lineTo(294.93698, 221.74199);
        ((GeneralPath) shape).lineTo(294.94098, 222.012);
        ((GeneralPath) shape).curveTo(294.94098, 222.726, 294.60397, 223.083,
                293.92398, 223.083);
        ((GeneralPath) shape).curveTo(293.22498, 223.083, 292.874, 222.78699,
                292.874, 222.18999);
        ((GeneralPath) shape).lineTo(292.874, 219.83798);
        ((GeneralPath) shape).lineTo(292.374, 219.83798);
        ((GeneralPath) shape).lineTo(292.374, 219.39798);
        ((GeneralPath) shape).lineTo(292.874, 219.39798);
        ((GeneralPath) shape).lineTo(292.874, 218.52399);
        ((GeneralPath) shape).lineTo(293.405, 218.52399);
        ((GeneralPath) shape).lineTo(293.405, 219.39798);
        ((GeneralPath) shape).lineTo(294.801, 219.39798);
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
        ((GeneralPath) shape).moveTo(297.962, 217.844);
        ((GeneralPath) shape).lineTo(297.962, 222.487);
        ((GeneralPath) shape).lineTo(300.624, 222.487);
        ((GeneralPath) shape).lineTo(300.624, 223.03);
        ((GeneralPath) shape).lineTo(297.378, 223.03);
        ((GeneralPath) shape).lineTo(297.378, 217.844);
        ((GeneralPath) shape).lineTo(297.962, 217.844);
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
        ((GeneralPath) shape).moveTo(302.616, 219.786);
        ((GeneralPath) shape).curveTo(302.146, 219.786, 301.856, 219.862,
                301.742, 220.01599);
        ((GeneralPath) shape).curveTo(301.63, 220.16798, 301.573, 220.56898,
                301.573, 221.215);
        ((GeneralPath) shape).curveTo(301.573, 221.861, 301.628, 222.26,
                301.742, 222.414);
        ((GeneralPath) shape).curveTo(301.853, 222.566, 302.146, 222.643,
                302.616, 222.643);
        ((GeneralPath) shape).curveTo(303.08798, 222.643, 303.38, 222.567,
                303.494, 222.414);
        ((GeneralPath) shape).curveTo(303.60498, 222.26201, 303.662, 221.86101,
                303.662, 221.215);
        ((GeneralPath) shape).curveTo(303.662, 220.569, 303.607, 220.17,
                303.494, 220.01599);
        ((GeneralPath) shape).curveTo(303.38098, 219.86299, 303.09, 219.786,
                302.616, 219.786);
        ((GeneralPath) shape).moveTo(302.616, 219.345);
        ((GeneralPath) shape).curveTo(303.285, 219.345, 303.72, 219.461,
                303.91998, 219.694);
        ((GeneralPath) shape).curveTo(304.119, 219.926, 304.21997, 220.433,
                304.21997, 221.214);
        ((GeneralPath) shape).curveTo(304.21997, 221.99301, 304.12097, 222.5,
                303.91998, 222.73401);
        ((GeneralPath) shape).curveTo(303.72098, 222.96501, 303.287, 223.08401,
                302.616, 223.08401);
        ((GeneralPath) shape).curveTo(301.949, 223.08401, 301.516, 222.96802,
                301.315, 222.73401);
        ((GeneralPath) shape).curveTo(301.116, 222.503, 301.015, 221.99501,
                301.015, 221.214);
        ((GeneralPath) shape).curveTo(301.015, 220.435, 301.114, 219.92801,
                301.315, 219.694);
        ((GeneralPath) shape).curveTo(301.514, 219.463, 301.948, 219.345,
                302.616, 219.345);
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
        ((GeneralPath) shape).moveTo(307.538, 221.72);
        ((GeneralPath) shape).lineTo(308.069, 221.72);
        ((GeneralPath) shape).lineTo(308.073, 221.917);
        ((GeneralPath) shape).curveTo(308.073, 222.694, 307.564, 223.08401,
                306.549, 223.08401);
        ((GeneralPath) shape).curveTo(305.897, 223.08401, 305.466, 222.96202,
                305.256, 222.71501);
        ((GeneralPath) shape).curveTo(305.048, 222.47002, 304.94202, 221.96301,
                304.94202, 221.195);
        ((GeneralPath) shape).curveTo(304.94202, 220.477, 305.048, 219.98901,
                305.26202, 219.73001);
        ((GeneralPath) shape).curveTo(305.47403, 219.47401, 305.88202,
                219.34502, 306.48102, 219.34502);
        ((GeneralPath) shape).curveTo(307.06903, 219.34502, 307.471, 219.43002,
                307.687, 219.60501);
        ((GeneralPath) shape).curveTo(307.901, 219.77802, 308.009, 220.10501,
                308.009, 220.58301);
        ((GeneralPath) shape).lineTo(307.478, 220.58301);
        ((GeneralPath) shape).lineTo(307.478, 220.488);
        ((GeneralPath) shape).curveTo(307.478, 220.20601, 307.41, 220.02101,
                307.27, 219.925);
        ((GeneralPath) shape).curveTo(307.13098, 219.832, 306.85498, 219.784,
                306.439, 219.784);
        ((GeneralPath) shape).curveTo(306.042, 219.784, 305.783, 219.871,
                305.66998, 220.05);
        ((GeneralPath) shape).curveTo(305.55597, 220.227, 305.499, 220.62401,
                305.499, 221.243);
        ((GeneralPath) shape).curveTo(305.499, 221.845, 305.563, 222.22899,
                305.694, 222.394);
        ((GeneralPath) shape).curveTo(305.823, 222.558, 306.128, 222.64099,
                306.606, 222.64099);
        ((GeneralPath) shape).curveTo(307.00998, 222.64099, 307.266, 222.588,
                307.37598, 222.48);
        ((GeneralPath) shape).curveTo(307.48297, 222.375, 307.53796, 222.12,
                307.53796, 221.72);
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
        ((GeneralPath) shape).moveTo(309.345, 217.844);
        ((GeneralPath) shape).lineTo(309.345, 220.857);
        ((GeneralPath) shape).lineTo(309.588, 220.857);
        ((GeneralPath) shape).lineTo(310.869, 219.398);
        ((GeneralPath) shape).lineTo(311.537, 219.398);
        ((GeneralPath) shape).lineTo(309.985, 221.066);
        ((GeneralPath) shape).lineTo(311.817, 223.03);
        ((GeneralPath) shape).lineTo(311.101, 223.03);
        ((GeneralPath) shape).lineTo(309.562, 221.271);
        ((GeneralPath) shape).lineTo(309.345, 221.271);
        ((GeneralPath) shape).lineTo(309.345, 223.03);
        ((GeneralPath) shape).lineTo(308.814, 223.03);
        ((GeneralPath) shape).lineTo(308.814, 217.844);
        ((GeneralPath) shape).lineTo(309.345, 217.844);
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
        ((GeneralPath) shape).moveTo(314.675, 220.899);
        ((GeneralPath) shape).lineTo(314.671, 220.728);
        ((GeneralPath) shape).curveTo(314.671, 220.33699, 314.607, 220.08,
                314.478, 219.96199);
        ((GeneralPath) shape).curveTo(314.349, 219.844, 314.073, 219.78499,
                313.64398, 219.78499);
        ((GeneralPath) shape).curveTo(313.215, 219.78499, 312.93698, 219.85298,
                312.80798, 219.99199);
        ((GeneralPath) shape).curveTo(312.68097, 220.12898, 312.61697,
                220.43098, 312.61697, 220.898);
        ((GeneralPath) shape).lineTo(314.675, 220.898);
        ((GeneralPath) shape).moveTo(314.675, 221.933);
        ((GeneralPath) shape).lineTo(315.218, 221.933);
        ((GeneralPath) shape).lineTo(315.22098, 222.066);
        ((GeneralPath) shape).curveTo(315.22098, 222.442, 315.10797, 222.706,
                314.878, 222.858);
        ((GeneralPath) shape).curveTo(314.65, 223.008, 314.249, 223.084,
                313.67398, 223.084);
        ((GeneralPath) shape).curveTo(313.007, 223.084, 312.56897, 222.962,
                312.361, 222.717);
        ((GeneralPath) shape).curveTo(312.15198, 222.474, 312.048, 221.959,
                312.048, 221.176);
        ((GeneralPath) shape).curveTo(312.048, 220.452, 312.152, 219.96599,
                312.362, 219.717);
        ((GeneralPath) shape).curveTo(312.57, 219.47, 312.982, 219.345,
                313.593, 219.345);
        ((GeneralPath) shape).curveTo(314.261, 219.345, 314.697, 219.451,
                314.90598, 219.668);
        ((GeneralPath) shape).curveTo(315.11298, 219.883, 315.218, 220.337,
                315.218, 221.028);
        ((GeneralPath) shape).lineTo(315.218, 221.313);
        ((GeneralPath) shape).lineTo(312.60898, 221.313);
        ((GeneralPath) shape).curveTo(312.60898, 221.88501, 312.66998, 222.25,
                312.79297, 222.407);
        ((GeneralPath) shape).curveTo(312.91498, 222.563, 313.20096, 222.642,
                313.65198, 222.642);
        ((GeneralPath) shape).curveTo(314.07898, 222.642, 314.35498, 222.606,
                314.48398, 222.53);
        ((GeneralPath) shape).curveTo(314.611, 222.456, 314.675, 222.294,
                314.675, 222.045);
        ((GeneralPath) shape).lineTo(314.675, 221.933);
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
        ((GeneralPath) shape).moveTo(317.485, 219.786);
        ((GeneralPath) shape).curveTo(317.094, 219.786, 316.83298, 219.875,
                316.70398, 220.058);
        ((GeneralPath) shape).curveTo(316.57498, 220.23799, 316.511, 220.605,
                316.511, 221.158);
        ((GeneralPath) shape).curveTo(316.511, 221.766, 316.57397, 222.16501,
                316.702, 222.35701);
        ((GeneralPath) shape).curveTo(316.829, 222.54701, 317.095, 222.64401,
                317.497, 222.64401);
        ((GeneralPath) shape).curveTo(317.933, 222.64401, 318.217, 222.54901,
                318.35, 222.35701);
        ((GeneralPath) shape).curveTo(318.48102, 222.167, 318.547, 221.751,
                318.547, 221.10901);
        ((GeneralPath) shape).curveTo(318.547, 220.59001, 318.475, 220.24101,
                318.329, 220.059);
        ((GeneralPath) shape).curveTo(318.186, 219.877, 317.902, 219.78601,
                317.48502, 219.78601);
        ((GeneralPath) shape).moveTo(319.082, 217.84401);
        ((GeneralPath) shape).lineTo(319.082, 223.03001);
        ((GeneralPath) shape).lineTo(318.551, 223.03001);
        ((GeneralPath) shape).lineTo(318.577, 222.55902);
        ((GeneralPath) shape).lineTo(318.56198, 222.55602);
        ((GeneralPath) shape).curveTo(318.395, 222.90701, 318.008, 223.08401,
                317.39798, 223.08401);
        ((GeneralPath) shape).curveTo(316.84198, 223.08401, 316.46298,
                222.94902, 316.25797, 222.67801);
        ((GeneralPath) shape).curveTo(316.05496, 222.408, 315.95197, 221.90302,
                315.95197, 221.162);
        ((GeneralPath) shape).curveTo(315.95197, 220.48201, 316.05496, 220.009,
                316.25998, 219.743);
        ((GeneralPath) shape).curveTo(316.464, 219.47899, 316.83, 219.346,
                317.36, 219.346);
        ((GeneralPath) shape).curveTo(318.01898, 219.346, 318.412, 219.517,
                318.53897, 219.859);
        ((GeneralPath) shape).lineTo(318.55096, 219.85199);
        ((GeneralPath) shape).lineTo(318.55096, 217.846);
        ((GeneralPath) shape).lineTo(319.08197, 217.846);
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
        stroke = new BasicStroke(0.973f, 0, 0, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(333.774, 223.233);
        ((GeneralPath) shape).curveTo(333.774, 223.963, 333.18198, 224.557,
                332.44897, 224.557);
        ((GeneralPath) shape).lineTo(325.71896, 224.557);
        ((GeneralPath) shape).curveTo(324.98798, 224.557, 324.39395, 223.96301,
                324.39395, 223.233);
        ((GeneralPath) shape).lineTo(324.39395, 216.433);
        ((GeneralPath) shape).curveTo(324.39395, 215.701, 324.98795, 215.108,
                325.71896, 215.108);
        ((GeneralPath) shape).lineTo(332.44897, 215.108);
        ((GeneralPath) shape).curveTo(333.18198, 215.108, 333.774, 215.701,
                333.774, 216.433);
        ((GeneralPath) shape).lineTo(333.774, 223.233);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        stroke = new BasicStroke(0.973f, 0, 0, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(383.862, 211.563);
        ((GeneralPath) shape).curveTo(383.862, 212.292, 383.27, 212.88501,
                382.538, 212.88501);
        ((GeneralPath) shape).lineTo(375.80798, 212.88501);
        ((GeneralPath) shape).curveTo(375.076, 212.88501, 374.482, 212.292,
                374.482, 211.563);
        ((GeneralPath) shape).lineTo(374.482, 204.763);
        ((GeneralPath) shape).curveTo(374.482, 204.03, 375.076, 203.438,
                375.80798, 203.438);
        ((GeneralPath) shape).lineTo(382.538, 203.438);
        ((GeneralPath) shape).curveTo(383.27, 203.438, 383.862, 204.03,
                383.862, 204.763);
        ((GeneralPath) shape).lineTo(383.862, 211.563);
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
        ((GeneralPath) shape).moveTo(305.793, 249.36);
        ((GeneralPath) shape).lineTo(305.213, 249.36);
        ((GeneralPath) shape).curveTo(305.213, 248.942, 305.14502, 248.678,
                305.005, 248.565);
        ((GeneralPath) shape).curveTo(304.867, 248.453, 304.539, 248.396,
                304.023, 248.396);
        ((GeneralPath) shape).curveTo(303.41302, 248.396, 303.019, 248.44899,
                302.84, 248.558);
        ((GeneralPath) shape).curveTo(302.663, 248.664, 302.574, 248.905,
                302.574, 249.278);
        ((GeneralPath) shape).curveTo(302.574, 249.696, 302.642, 249.95,
                302.782, 250.042);
        ((GeneralPath) shape).curveTo(302.92, 250.13301, 303.334, 250.194,
                304.022, 250.22801);
        ((GeneralPath) shape).curveTo(304.828, 250.26201, 305.34, 250.365,
                305.56, 250.53601);
        ((GeneralPath) shape).curveTo(305.77798, 250.70502, 305.888, 251.085,
                305.888, 251.67601);
        ((GeneralPath) shape).curveTo(305.888, 252.31401, 305.763, 252.72601,
                305.511, 252.91402);
        ((GeneralPath) shape).curveTo(305.261, 253.10101, 304.705, 253.19502,
                303.844, 253.19502);
        ((GeneralPath) shape).curveTo(303.099, 253.19502, 302.602, 253.10202,
                302.356, 252.91202);
        ((GeneralPath) shape).curveTo(302.10898, 252.72401, 301.986, 252.34402,
                301.986, 251.77002);
        ((GeneralPath) shape).lineTo(301.982, 251.53902);
        ((GeneralPath) shape).lineTo(302.563, 251.53902);
        ((GeneralPath) shape).lineTo(302.563, 251.66801);
        ((GeneralPath) shape).curveTo(302.563, 252.13202, 302.633, 252.41801,
                302.775, 252.53302);
        ((GeneralPath) shape).curveTo(302.915, 252.64502, 303.279, 252.70203,
                303.863, 252.70203);
        ((GeneralPath) shape).curveTo(304.532, 252.70203, 304.944, 252.64703,
                305.099, 252.53302);
        ((GeneralPath) shape).curveTo(305.253, 252.42102, 305.33, 252.12003,
                305.33, 251.63002);
        ((GeneralPath) shape).curveTo(305.33, 251.31502, 305.279, 251.10402,
                305.172, 250.99802);
        ((GeneralPath) shape).curveTo(305.068, 250.89401, 304.848, 250.83101,
                304.514, 250.81001);
        ((GeneralPath) shape).lineTo(303.907, 250.78001);
        ((GeneralPath) shape).lineTo(303.33102, 250.74901);
        ((GeneralPath) shape).curveTo(302.45502, 250.688, 302.015, 250.23201,
                302.015, 249.38101);
        ((GeneralPath) shape).curveTo(302.015, 248.792, 302.14203, 248.39702,
                302.398, 248.199);
        ((GeneralPath) shape).curveTo(302.652, 248.002, 303.16202, 247.903,
                303.92603, 247.903);
        ((GeneralPath) shape).curveTo(304.69904, 247.903, 305.20303, 247.995,
                305.43903, 248.179);
        ((GeneralPath) shape).curveTo(305.67502, 248.359, 305.79303, 248.754,
                305.79303, 249.36);
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
        ((GeneralPath) shape).moveTo(308.489, 249.508);
        ((GeneralPath) shape).lineTo(308.489, 249.948);
        ((GeneralPath) shape).lineTo(307.09302, 249.948);
        ((GeneralPath) shape).lineTo(307.09302, 252.171);
        ((GeneralPath) shape).curveTo(307.09302, 252.559, 307.26303, 252.753,
                307.609, 252.753);
        ((GeneralPath) shape).curveTo(307.95, 252.753, 308.121, 252.58,
                308.121, 252.23201);
        ((GeneralPath) shape).lineTo(308.125, 252.05301);
        ((GeneralPath) shape).lineTo(308.133, 251.852);
        ((GeneralPath) shape).lineTo(308.625, 251.852);
        ((GeneralPath) shape).lineTo(308.629, 252.12201);
        ((GeneralPath) shape).curveTo(308.629, 252.83601, 308.292, 253.19301,
                307.613, 253.19301);
        ((GeneralPath) shape).curveTo(306.913, 253.19301, 306.562, 252.897,
                306.562, 252.3);
        ((GeneralPath) shape).lineTo(306.562, 249.948);
        ((GeneralPath) shape).lineTo(306.062, 249.948);
        ((GeneralPath) shape).lineTo(306.062, 249.508);
        ((GeneralPath) shape).lineTo(306.562, 249.508);
        ((GeneralPath) shape).lineTo(306.562, 248.634);
        ((GeneralPath) shape).lineTo(307.09302, 248.634);
        ((GeneralPath) shape).lineTo(307.09302, 249.508);
        ((GeneralPath) shape).lineTo(308.489, 249.508);
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
        ((GeneralPath) shape).moveTo(310.505, 251.343);
        ((GeneralPath) shape).curveTo(310.1, 251.343, 309.834, 251.38501,
                309.71, 251.472);
        ((GeneralPath) shape).curveTo(309.589, 251.557, 309.526, 251.742,
                309.526, 252.027);
        ((GeneralPath) shape).curveTo(309.526, 252.319, 309.587, 252.515,
                309.709, 252.60999);
        ((GeneralPath) shape).curveTo(309.83002, 252.70499, 310.07602,
                252.75299, 310.44803, 252.75299);
        ((GeneralPath) shape).curveTo(311.19504, 252.75299, 311.57004, 252.525,
                311.57004, 252.06898);
        ((GeneralPath) shape).curveTo(311.57004, 251.78398, 311.49805,
                251.58998, 311.35205, 251.49098);
        ((GeneralPath) shape).curveTo(311.20804, 251.39297, 310.92606,
                251.34299, 310.50507, 251.34299);
        ((GeneralPath) shape).moveTo(309.65207, 250.52599);
        ((GeneralPath) shape).lineTo(309.12506, 250.52599);
        ((GeneralPath) shape).curveTo(309.12506, 250.10399, 309.22006,
                249.81898, 309.41107, 249.67299);
        ((GeneralPath) shape).curveTo(309.60007, 249.52899, 309.97406,
                249.45499, 310.53107, 249.45499);
        ((GeneralPath) shape).curveTo(311.13507, 249.45499, 311.54407,
                249.54399, 311.75607, 249.72299);
        ((GeneralPath) shape).curveTo(311.9691, 249.902, 312.07507, 250.24199,
                312.07507, 250.74399);
        ((GeneralPath) shape).lineTo(312.07507, 253.14099);
        ((GeneralPath) shape).lineTo(311.54407, 253.14099);
        ((GeneralPath) shape).lineTo(311.58606, 252.74998);
        ((GeneralPath) shape).lineTo(311.57407, 252.74599);
        ((GeneralPath) shape).curveTo(311.37308, 253.04399, 310.96207,
                253.19398, 310.34207, 253.19398);
        ((GeneralPath) shape).curveTo(309.42807, 253.19398, 308.9691,
                252.82599, 308.9691, 252.08899);
        ((GeneralPath) shape).curveTo(308.9691, 251.652, 309.07208, 251.34799,
                309.2771, 251.18098);
        ((GeneralPath) shape).curveTo(309.4821, 251.01398, 309.8511, 250.93098,
                310.3871, 250.93098);
        ((GeneralPath) shape).curveTo(311.0251, 250.93098, 311.4061, 251.05598,
                311.53308, 251.30699);
        ((GeneralPath) shape).lineTo(311.54507, 251.303);
        ((GeneralPath) shape).lineTo(311.54507, 250.86299);
        ((GeneralPath) shape).curveTo(311.54507, 250.44899, 311.48807,
                250.17899, 311.37408, 250.05598);
        ((GeneralPath) shape).curveTo(311.26108, 249.93399, 311.0121,
                249.87099, 310.62708, 249.87099);
        ((GeneralPath) shape).curveTo(309.97507, 249.87099, 309.64807, 250.053,
                309.64807, 250.42198);
        ((GeneralPath) shape).curveTo(309.64706, 250.43698, 309.64706,
                250.47298, 309.65207, 250.52599);
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
        ((GeneralPath) shape).moveTo(314.655, 249.896);
        ((GeneralPath) shape).curveTo(314.238, 249.896, 313.95898, 249.986,
                313.817, 250.164);
        ((GeneralPath) shape).curveTo(313.676, 250.343, 313.60397, 250.698,
                313.60397, 251.23);
        ((GeneralPath) shape).curveTo(313.60397, 251.885, 313.66696, 252.303,
                313.79498, 252.483);
        ((GeneralPath) shape).curveTo(313.922, 252.662, 314.21597, 252.753,
                314.67697, 252.753);
        ((GeneralPath) shape).curveTo(315.06598, 252.753, 315.32297, 252.662,
                315.44797, 252.47801);
        ((GeneralPath) shape).curveTo(315.57297, 252.29501, 315.63596, 251.917,
                315.63596, 251.348);
        ((GeneralPath) shape).curveTo(315.63596, 250.74701, 315.57495, 250.356,
                315.44995, 250.17201);
        ((GeneralPath) shape).curveTo(315.32794, 249.98901, 315.06296,
                249.89601, 314.65494, 249.89601);
        ((GeneralPath) shape).moveTo(313.05893, 253.14001);
        ((GeneralPath) shape).lineTo(313.05893, 247.95401);
        ((GeneralPath) shape).lineTo(313.58994, 247.95401);
        ((GeneralPath) shape).lineTo(313.58994, 249.97202);
        ((GeneralPath) shape).lineTo(313.60092, 249.98302);
        ((GeneralPath) shape).curveTo(313.72192, 249.63202, 314.11893,
                249.45502, 314.79092, 249.45502);
        ((GeneralPath) shape).curveTo(315.32193, 249.45502, 315.68793,
                249.59201, 315.89194, 249.86902);
        ((GeneralPath) shape).curveTo(316.09195, 250.14401, 316.19495,
                250.64401, 316.19495, 251.36902);
        ((GeneralPath) shape).curveTo(316.19495, 252.05502, 316.08896,
                252.53001, 315.87494, 252.79602);
        ((GeneralPath) shape).curveTo(315.66293, 253.06003, 315.27594,
                253.19302, 314.71695, 253.19302);
        ((GeneralPath) shape).curveTo(314.14795, 253.19302, 313.76895,
                253.02602, 313.57895, 252.68802);
        ((GeneralPath) shape).lineTo(313.56296, 252.69102);
        ((GeneralPath) shape).lineTo(313.58997, 253.13902);
        ((GeneralPath) shape).lineTo(313.05896, 253.13902);
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
        ((GeneralPath) shape).moveTo(317.558, 249.508);
        ((GeneralPath) shape).lineTo(317.558, 253.14);
        ((GeneralPath) shape).lineTo(317.028, 253.14);
        ((GeneralPath) shape).lineTo(317.028, 249.508);
        ((GeneralPath) shape).lineTo(317.558, 249.508);
        ((GeneralPath) shape).moveTo(317.558, 247.954);
        ((GeneralPath) shape).lineTo(317.558, 248.551);
        ((GeneralPath) shape).lineTo(317.028, 248.551);
        ((GeneralPath) shape).lineTo(317.028, 247.954);
        ((GeneralPath) shape).lineTo(317.558, 247.954);
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
        shape = new Rectangle2D.Double(318.48699951171875, 247.95399475097656,
                0.531000018119812, 5.185999870300293);
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
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(320.479, 249.508);
        ((GeneralPath) shape).lineTo(320.479, 253.14);
        ((GeneralPath) shape).lineTo(319.948, 253.14);
        ((GeneralPath) shape).lineTo(319.948, 249.508);
        ((GeneralPath) shape).lineTo(320.479, 249.508);
        ((GeneralPath) shape).moveTo(320.479, 247.954);
        ((GeneralPath) shape).lineTo(320.479, 248.551);
        ((GeneralPath) shape).lineTo(319.948, 248.551);
        ((GeneralPath) shape).lineTo(319.948, 247.954);
        ((GeneralPath) shape).lineTo(320.479, 247.954);
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
        ((GeneralPath) shape).moveTo(321.289, 249.508);
        ((GeneralPath) shape).lineTo(323.902, 249.508);
        ((GeneralPath) shape).lineTo(323.902, 250.025);
        ((GeneralPath) shape).lineTo(321.74, 252.7);
        ((GeneralPath) shape).lineTo(323.902, 252.7);
        ((GeneralPath) shape).lineTo(323.902, 253.14);
        ((GeneralPath) shape).lineTo(321.107, 253.14);
        ((GeneralPath) shape).lineTo(321.107, 252.635);
        ((GeneralPath) shape).lineTo(323.276, 249.949);
        ((GeneralPath) shape).lineTo(321.289, 249.949);
        ((GeneralPath) shape).lineTo(321.289, 249.508);
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
        ((GeneralPath) shape).moveTo(327.059, 251.009);
        ((GeneralPath) shape).lineTo(327.055, 250.838);
        ((GeneralPath) shape).curveTo(327.055, 250.44699, 326.991, 250.191,
                326.862, 250.07199);
        ((GeneralPath) shape).curveTo(326.733, 249.954, 326.457, 249.89499,
                326.02798, 249.89499);
        ((GeneralPath) shape).curveTo(325.599, 249.89499, 325.32098, 249.96298,
                325.192, 250.10199);
        ((GeneralPath) shape).curveTo(325.064, 250.23898, 325.00098, 250.54099,
                325.00098, 251.008);
        ((GeneralPath) shape).lineTo(327.059, 251.008);
        ((GeneralPath) shape).moveTo(327.059, 252.04199);
        ((GeneralPath) shape).lineTo(327.60098, 252.04199);
        ((GeneralPath) shape).lineTo(327.60498, 252.17499);
        ((GeneralPath) shape).curveTo(327.60498, 252.551, 327.49097, 252.81499,
                327.262, 252.967);
        ((GeneralPath) shape).curveTo(327.034, 253.11699, 326.632, 253.193,
                326.05798, 253.193);
        ((GeneralPath) shape).curveTo(325.38998, 253.193, 324.95297, 253.071,
                324.744, 252.827);
        ((GeneralPath) shape).curveTo(324.53598, 252.584, 324.431, 252.069,
                324.431, 251.286);
        ((GeneralPath) shape).curveTo(324.431, 250.562, 324.535, 250.07599,
                324.745, 249.827);
        ((GeneralPath) shape).curveTo(324.954, 249.58, 325.365, 249.455,
                325.977, 249.455);
        ((GeneralPath) shape).curveTo(326.645, 249.455, 327.081, 249.561,
                327.28998, 249.778);
        ((GeneralPath) shape).curveTo(327.49597, 249.993, 327.60098, 250.447,
                327.60098, 251.138);
        ((GeneralPath) shape).lineTo(327.60098, 251.423);
        ((GeneralPath) shape).lineTo(324.99197, 251.423);
        ((GeneralPath) shape).curveTo(324.99197, 251.99501, 325.054, 252.36,
                325.17697, 252.517);
        ((GeneralPath) shape).curveTo(325.29797, 252.673, 325.58398, 252.75299,
                326.03497, 252.75299);
        ((GeneralPath) shape).curveTo(326.46198, 252.75299, 326.73898, 252.717,
                326.86798, 252.64099);
        ((GeneralPath) shape).curveTo(326.995, 252.56699, 327.059, 252.40599,
                327.059, 252.157);
        ((GeneralPath) shape).lineTo(327.059, 252.04199);
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
        ((GeneralPath) shape).moveTo(328.488, 249.508);
        ((GeneralPath) shape).lineTo(329.019, 249.508);
        ((GeneralPath) shape).lineTo(328.966, 249.926);
        ((GeneralPath) shape).lineTo(328.977, 249.937);
        ((GeneralPath) shape).curveTo(329.185, 249.595, 329.533, 249.424,
                330.016, 249.424);
        ((GeneralPath) shape).curveTo(330.68298, 249.424, 331.017, 249.76799,
                331.017, 250.457);
        ((GeneralPath) shape).lineTo(331.013, 250.70801);
        ((GeneralPath) shape).lineTo(330.49, 250.70801);
        ((GeneralPath) shape).lineTo(330.50198, 250.617);
        ((GeneralPath) shape).curveTo(330.50998, 250.522, 330.51398, 250.457,
                330.51398, 250.423);
        ((GeneralPath) shape).curveTo(330.51398, 250.05, 330.313, 249.864,
                329.908, 249.864);
        ((GeneralPath) shape).curveTo(329.31598, 249.864, 329.02, 250.229,
                329.02, 250.962);
        ((GeneralPath) shape).lineTo(329.02, 253.139);
        ((GeneralPath) shape).lineTo(328.48898, 253.139);
        ((GeneralPath) shape).lineTo(328.48898, 249.50801);
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
        ((GeneralPath) shape).moveTo(334.336, 250.458);
        ((GeneralPath) shape).lineTo(333.805, 250.458);
        ((GeneralPath) shape).curveTo(333.805, 250.206, 333.754, 250.04799,
                333.65198, 249.987);
        ((GeneralPath) shape).curveTo(333.54898, 249.926, 333.28598, 249.896,
                332.86197, 249.896);
        ((GeneralPath) shape).curveTo(332.46698, 249.896, 332.21698, 249.928,
                332.11096, 249.993);
        ((GeneralPath) shape).curveTo(332.00497, 250.05699, 331.95197, 250.211,
                331.95197, 250.458);
        ((GeneralPath) shape).curveTo(331.95197, 250.82999, 332.12997,
                251.02399, 332.48596, 251.043);
        ((GeneralPath) shape).lineTo(332.91495, 251.066);
        ((GeneralPath) shape).lineTo(333.45694, 251.092);
        ((GeneralPath) shape).curveTo(334.11295, 251.125, 334.44293, 251.468,
                334.44293, 252.12599);
        ((GeneralPath) shape).curveTo(334.44293, 252.532, 334.33493, 252.814,
                334.11694, 252.965);
        ((GeneralPath) shape).curveTo(333.90094, 253.11699, 333.50293, 253.193,
                332.92294, 253.193);
        ((GeneralPath) shape).curveTo(332.32895, 253.193, 331.91995, 253.12099,
                331.69595, 252.97699);
        ((GeneralPath) shape).curveTo(331.47095, 252.83199, 331.35995, 252.568,
                331.35995, 252.18298);
        ((GeneralPath) shape).lineTo(331.36395, 251.98598);
        ((GeneralPath) shape).lineTo(331.91394, 251.98598);
        ((GeneralPath) shape).lineTo(331.91794, 252.15698);
        ((GeneralPath) shape).curveTo(331.91794, 252.39398, 331.97894,
                252.55399, 332.09995, 252.63399);
        ((GeneralPath) shape).curveTo(332.22095, 252.71399, 332.45993,
                252.75398, 332.81696, 252.75398);
        ((GeneralPath) shape).curveTo(333.25397, 252.75398, 333.54196,
                252.71198, 333.67896, 252.62798);
        ((GeneralPath) shape).curveTo(333.81595, 252.54498, 333.88596,
                252.36998, 333.88596, 252.10397);
        ((GeneralPath) shape).curveTo(333.88596, 251.72197, 333.71295,
                251.52997, 333.36597, 251.52997);
        ((GeneralPath) shape).curveTo(332.55997, 251.52997, 332.02896,
                251.46198, 331.77496, 251.32497);
        ((GeneralPath) shape).curveTo(331.52097, 251.18797, 331.39395,
                250.90497, 331.39395, 250.47397);
        ((GeneralPath) shape).curveTo(331.39395, 250.06697, 331.49496,
                249.79597, 331.69595, 249.65897);
        ((GeneralPath) shape).curveTo(331.89597, 249.52397, 332.29697,
                249.45596, 332.89597, 249.45596);
        ((GeneralPath) shape).curveTo(333.85498, 249.45596, 334.33597,
                249.74496, 334.33597, 250.32596);
        ((GeneralPath) shape).lineTo(334.33597, 250.45796);
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
        ((GeneralPath) shape).moveTo(257.745, 257.809);
        ((GeneralPath) shape).lineTo(257.745, 259.621);
        ((GeneralPath) shape).lineTo(260.267, 259.621);
        ((GeneralPath) shape).lineTo(260.267, 260.114);
        ((GeneralPath) shape).lineTo(257.745, 260.114);
        ((GeneralPath) shape).lineTo(257.745, 262.5);
        ((GeneralPath) shape).lineTo(257.161, 262.5);
        ((GeneralPath) shape).lineTo(257.161, 257.314);
        ((GeneralPath) shape).lineTo(260.335, 257.314);
        ((GeneralPath) shape).lineTo(260.335, 257.809);
        ((GeneralPath) shape).lineTo(257.745, 257.809);
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
        ((GeneralPath) shape).moveTo(260.985, 258.868);
        ((GeneralPath) shape).lineTo(261.516, 258.868);
        ((GeneralPath) shape).lineTo(261.46298, 259.286);
        ((GeneralPath) shape).lineTo(261.47498, 259.298);
        ((GeneralPath) shape).curveTo(261.68298, 258.956, 262.02997, 258.785,
                262.51398, 258.785);
        ((GeneralPath) shape).curveTo(263.18097, 258.785, 263.51498, 259.129,
                263.51498, 259.818);
        ((GeneralPath) shape).lineTo(263.511, 260.069);
        ((GeneralPath) shape).lineTo(262.98798, 260.069);
        ((GeneralPath) shape).lineTo(262.99896, 259.978);
        ((GeneralPath) shape).curveTo(263.00696, 259.883, 263.01096, 259.818,
                263.01096, 259.785);
        ((GeneralPath) shape).curveTo(263.01096, 259.41202, 262.80997, 259.226,
                262.40396, 259.226);
        ((GeneralPath) shape).curveTo(261.81296, 259.226, 261.51697, 259.59003,
                261.51697, 260.324);
        ((GeneralPath) shape).lineTo(261.51697, 262.501);
        ((GeneralPath) shape).lineTo(260.98596, 262.501);
        ((GeneralPath) shape).lineTo(260.98596, 258.868);
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
        ((GeneralPath) shape).moveTo(265.521, 259.256);
        ((GeneralPath) shape).curveTo(265.051, 259.256, 264.761, 259.332,
                264.647, 259.48502);
        ((GeneralPath) shape).curveTo(264.535, 259.63702, 264.478, 260.03802,
                264.478, 260.68402);
        ((GeneralPath) shape).curveTo(264.478, 261.33002, 264.533, 261.72903,
                264.647, 261.88303);
        ((GeneralPath) shape).curveTo(264.759, 262.03503, 265.051, 262.11304,
                265.521, 262.11304);
        ((GeneralPath) shape).curveTo(265.99298, 262.11304, 266.285, 262.03705,
                266.399, 261.88303);
        ((GeneralPath) shape).curveTo(266.50998, 261.73102, 266.567, 261.33002,
                266.567, 260.68402);
        ((GeneralPath) shape).curveTo(266.567, 260.03802, 266.512, 259.639,
                266.399, 259.48502);
        ((GeneralPath) shape).curveTo(266.287, 259.333, 265.995, 259.256,
                265.521, 259.256);
        ((GeneralPath) shape).moveTo(265.521, 258.815);
        ((GeneralPath) shape).curveTo(266.19, 258.815, 266.625, 258.931,
                266.82498, 259.165);
        ((GeneralPath) shape).curveTo(267.024, 259.396, 267.12497, 259.90402,
                267.12497, 260.685);
        ((GeneralPath) shape).curveTo(267.12497, 261.464, 267.02597, 261.971,
                266.82498, 262.205);
        ((GeneralPath) shape).curveTo(266.62598, 262.43597, 266.192, 262.554,
                265.521, 262.554);
        ((GeneralPath) shape).curveTo(264.854, 262.554, 264.421, 262.438,
                264.22, 262.205);
        ((GeneralPath) shape).curveTo(264.021, 261.973, 263.921, 261.46597,
                263.921, 260.685);
        ((GeneralPath) shape).curveTo(263.921, 259.906, 264.01898, 259.399,
                264.22, 259.165);
        ((GeneralPath) shape).curveTo(264.419, 258.933, 264.854, 258.815,
                265.521, 258.815);
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
        ((GeneralPath) shape).moveTo(267.953, 258.868);
        ((GeneralPath) shape).lineTo(268.465, 258.868);
        ((GeneralPath) shape).lineTo(268.44998, 259.362);
        ((GeneralPath) shape).lineTo(268.465, 259.373);
        ((GeneralPath) shape).curveTo(268.626, 259.00098, 269.02798, 258.814,
                269.671, 258.814);
        ((GeneralPath) shape).curveTo(270.189, 258.814, 270.539, 258.905,
                270.723, 259.087);
        ((GeneralPath) shape).curveTo(270.905, 259.27002, 270.998, 259.61902,
                270.998, 260.13602);
        ((GeneralPath) shape).lineTo(270.998, 262.49902);
        ((GeneralPath) shape).lineTo(270.46698, 262.49902);
        ((GeneralPath) shape).lineTo(270.46698, 260.045);
        ((GeneralPath) shape).curveTo(270.46698, 259.733, 270.408, 259.52402,
                270.28897, 259.41602);
        ((GeneralPath) shape).curveTo(270.17096, 259.31003, 269.942, 259.255,
                269.60196, 259.255);
        ((GeneralPath) shape).curveTo(268.85696, 259.255, 268.48297, 259.609,
                268.48297, 260.315);
        ((GeneralPath) shape).lineTo(268.48297, 262.5);
        ((GeneralPath) shape).lineTo(267.95197, 262.5);
        ((GeneralPath) shape).lineTo(267.95197, 258.868);
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
        ((GeneralPath) shape).moveTo(273.882, 258.868);
        ((GeneralPath) shape).lineTo(273.882, 259.30902);
        ((GeneralPath) shape).lineTo(272.486, 259.30902);
        ((GeneralPath) shape).lineTo(272.486, 261.531);
        ((GeneralPath) shape).curveTo(272.486, 261.919, 272.656, 262.113,
                273.00198, 262.113);
        ((GeneralPath) shape).curveTo(273.343, 262.113, 273.51398, 261.94,
                273.51398, 261.592);
        ((GeneralPath) shape).lineTo(273.51797, 261.41302);
        ((GeneralPath) shape).lineTo(273.52496, 261.21204);
        ((GeneralPath) shape).lineTo(274.01797, 261.21204);
        ((GeneralPath) shape).lineTo(274.02197, 261.48203);
        ((GeneralPath) shape).curveTo(274.02197, 262.196, 273.68396, 262.55304,
                273.00497, 262.55304);
        ((GeneralPath) shape).curveTo(272.30496, 262.55304, 271.955, 262.25705,
                271.955, 261.66003);
        ((GeneralPath) shape).lineTo(271.955, 259.30804);
        ((GeneralPath) shape).lineTo(271.455, 259.30804);
        ((GeneralPath) shape).lineTo(271.455, 258.86703);
        ((GeneralPath) shape).lineTo(271.955, 258.86703);
        ((GeneralPath) shape).lineTo(271.955, 257.99304);
        ((GeneralPath) shape).lineTo(272.486, 257.99304);
        ((GeneralPath) shape).lineTo(272.486, 258.86703);
        ((GeneralPath) shape).lineTo(273.882, 258.86703);
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
        stroke = new BasicStroke(0.973f, 0, 0, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(290.968, 263.632);
        ((GeneralPath) shape).curveTo(290.968, 264.361, 290.37598, 264.955,
                289.64297, 264.955);
        ((GeneralPath) shape).lineTo(282.91296, 264.955);
        ((GeneralPath) shape).curveTo(282.18097, 264.955, 281.58698, 264.361,
                281.58698, 263.632);
        ((GeneralPath) shape).lineTo(281.58698, 256.83);
        ((GeneralPath) shape).curveTo(281.58698, 256.099, 282.18097, 255.50598,
                282.91296, 255.50598);
        ((GeneralPath) shape).lineTo(289.64297, 255.50598);
        ((GeneralPath) shape).curveTo(290.37598, 255.50598, 290.968, 256.09897,
                290.968, 256.83);
        ((GeneralPath) shape).lineTo(290.968, 263.632);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        ((GeneralPath) shape).moveTo(296.103, 257.314);
        ((GeneralPath) shape).lineTo(296.103, 261.957);
        ((GeneralPath) shape).lineTo(298.765, 261.957);
        ((GeneralPath) shape).lineTo(298.765, 262.5);
        ((GeneralPath) shape).lineTo(295.519, 262.5);
        ((GeneralPath) shape).lineTo(295.519, 257.314);
        ((GeneralPath) shape).lineTo(296.103, 257.314);
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
        ((GeneralPath) shape).moveTo(301.78, 260.369);
        ((GeneralPath) shape).lineTo(301.776, 260.198);
        ((GeneralPath) shape).curveTo(301.776, 259.807, 301.712, 259.551,
                301.583, 259.432);
        ((GeneralPath) shape).curveTo(301.454, 259.314, 301.178, 259.256,
                300.749, 259.256);
        ((GeneralPath) shape).curveTo(300.32, 259.256, 300.042, 259.324,
                299.913, 259.463);
        ((GeneralPath) shape).curveTo(299.78598, 259.6, 299.722, 259.901,
                299.722, 260.36902);
        ((GeneralPath) shape).lineTo(301.78, 260.36902);
        ((GeneralPath) shape).moveTo(301.78, 261.402);
        ((GeneralPath) shape).lineTo(302.322, 261.402);
        ((GeneralPath) shape).lineTo(302.32498, 261.535);
        ((GeneralPath) shape).curveTo(302.32498, 261.911, 302.21198, 262.17502,
                301.982, 262.327);
        ((GeneralPath) shape).curveTo(301.754, 262.477, 301.353, 262.553,
                300.77798, 262.553);
        ((GeneralPath) shape).curveTo(300.111, 262.553, 299.67297, 262.431,
                299.464, 262.187);
        ((GeneralPath) shape).curveTo(299.25598, 261.944, 299.151, 261.42902,
                299.151, 260.64603);
        ((GeneralPath) shape).curveTo(299.151, 259.92203, 299.255, 259.43604,
                299.465, 259.187);
        ((GeneralPath) shape).curveTo(299.673, 258.94, 300.085, 258.81403,
                300.697, 258.81403);
        ((GeneralPath) shape).curveTo(301.36398, 258.81403, 301.801, 258.92,
                302.009, 259.13702);
        ((GeneralPath) shape).curveTo(302.216, 259.35202, 302.32, 259.80603,
                302.32, 260.497);
        ((GeneralPath) shape).lineTo(302.32, 260.782);
        ((GeneralPath) shape).lineTo(299.711, 260.782);
        ((GeneralPath) shape).curveTo(299.711, 261.35303, 299.772, 261.71802,
                299.895, 261.876);
        ((GeneralPath) shape).curveTo(300.017, 262.032, 300.30298, 262.112,
                300.754, 262.112);
        ((GeneralPath) shape).curveTo(301.181, 262.112, 301.458, 262.076,
                301.587, 262.0);
        ((GeneralPath) shape).curveTo(301.71402, 261.926, 301.778, 261.765,
                301.778, 261.516);
        ((GeneralPath) shape).lineTo(301.778, 261.40198);
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
        ((GeneralPath) shape).moveTo(303.297, 262.5);
        ((GeneralPath) shape).lineTo(303.297, 259.309);
        ((GeneralPath) shape).lineTo(302.744, 259.309);
        ((GeneralPath) shape).lineTo(302.744, 258.86798);
        ((GeneralPath) shape).lineTo(303.297, 258.86798);
        ((GeneralPath) shape).lineTo(303.297, 258.32797);
        ((GeneralPath) shape).curveTo(303.297, 257.61798, 303.661, 257.25998,
                304.393, 257.25998);
        ((GeneralPath) shape).curveTo(304.501, 257.25998, 304.628, 257.26797,
                304.776, 257.287);
        ((GeneralPath) shape).lineTo(304.776, 257.728);
        ((GeneralPath) shape).curveTo(304.605, 257.711, 304.48, 257.702,
                304.401, 257.702);
        ((GeneralPath) shape).curveTo(304.02, 257.702, 303.828, 257.893,
                303.828, 258.279);
        ((GeneralPath) shape).lineTo(303.828, 258.86798);
        ((GeneralPath) shape).lineTo(304.776, 258.86798);
        ((GeneralPath) shape).lineTo(304.776, 259.309);
        ((GeneralPath) shape).lineTo(303.828, 259.309);
        ((GeneralPath) shape).lineTo(303.828, 262.5);
        ((GeneralPath) shape).lineTo(303.297, 262.5);
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
        ((GeneralPath) shape).moveTo(307.138, 258.868);
        ((GeneralPath) shape).lineTo(307.138, 259.30902);
        ((GeneralPath) shape).lineTo(305.742, 259.30902);
        ((GeneralPath) shape).lineTo(305.742, 261.531);
        ((GeneralPath) shape).curveTo(305.742, 261.919, 305.91202, 262.113,
                306.258, 262.113);
        ((GeneralPath) shape).curveTo(306.6, 262.113, 306.77, 261.94, 306.77,
                261.592);
        ((GeneralPath) shape).lineTo(306.774, 261.41302);
        ((GeneralPath) shape).lineTo(306.78198, 261.21204);
        ((GeneralPath) shape).lineTo(307.275, 261.21204);
        ((GeneralPath) shape).lineTo(307.279, 261.48203);
        ((GeneralPath) shape).curveTo(307.279, 262.196, 306.94098, 262.55304,
                306.262, 262.55304);
        ((GeneralPath) shape).curveTo(305.56198, 262.55304, 305.212, 262.25705,
                305.212, 261.66003);
        ((GeneralPath) shape).lineTo(305.212, 259.30804);
        ((GeneralPath) shape).lineTo(304.712, 259.30804);
        ((GeneralPath) shape).lineTo(304.712, 258.86703);
        ((GeneralPath) shape).lineTo(305.212, 258.86703);
        ((GeneralPath) shape).lineTo(305.212, 257.99304);
        ((GeneralPath) shape).lineTo(305.743, 257.99304);
        ((GeneralPath) shape).lineTo(305.743, 258.86703);
        ((GeneralPath) shape).lineTo(307.138, 258.86703);
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
        stroke = new BasicStroke(0.973f, 0, 0, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(336.672, 263.632);
        ((GeneralPath) shape).curveTo(336.672, 264.361, 336.08, 264.955,
                335.348, 264.955);
        ((GeneralPath) shape).lineTo(328.616, 264.955);
        ((GeneralPath) shape).curveTo(327.887, 264.955, 327.293, 264.361,
                327.293, 263.632);
        ((GeneralPath) shape).lineTo(327.293, 256.83);
        ((GeneralPath) shape).curveTo(327.293, 256.099, 327.887, 255.50598,
                328.616, 255.50598);
        ((GeneralPath) shape).lineTo(335.348, 255.50598);
        ((GeneralPath) shape).curveTo(336.08, 255.50598, 336.672, 256.09897,
                336.672, 256.83);
        ((GeneralPath) shape).lineTo(336.672, 263.632);
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
        ((GeneralPath) shape).moveTo(341.349, 259.905);
        ((GeneralPath) shape).lineTo(343.037, 259.905);
        ((GeneralPath) shape).curveTo(343.47998, 259.905, 343.784, 259.839,
                343.947, 259.702);
        ((GeneralPath) shape).curveTo(344.108, 259.567, 344.19, 259.31198,
                344.19, 258.936);
        ((GeneralPath) shape).curveTo(344.19, 258.469, 344.13, 258.163,
                344.009, 258.02002);
        ((GeneralPath) shape).curveTo(343.89, 257.87903, 343.632, 257.808,
                343.238, 257.808);
        ((GeneralPath) shape).lineTo(341.349, 257.808);
        ((GeneralPath) shape).lineTo(341.349, 259.905);
        ((GeneralPath) shape).moveTo(340.76498, 262.5);
        ((GeneralPath) shape).lineTo(340.76498, 257.314);
        ((GeneralPath) shape).lineTo(343.22998, 257.314);
        ((GeneralPath) shape).curveTo(343.78598, 257.314, 344.17798, 257.42398,
                344.40598, 257.645);
        ((GeneralPath) shape).curveTo(344.63397, 257.865, 344.74698, 258.24698,
                344.74698, 258.792);
        ((GeneralPath) shape).curveTo(344.74698, 259.271, 344.68597, 259.604,
                344.56097, 259.793);
        ((GeneralPath) shape).curveTo(344.438, 259.981, 344.201, 260.103,
                343.85196, 260.16);
        ((GeneralPath) shape).lineTo(343.85196, 260.171);
        ((GeneralPath) shape).curveTo(344.39996, 260.211, 344.67496, 260.54498,
                344.67496, 261.17398);
        ((GeneralPath) shape).lineTo(344.67496, 262.49997);
        ((GeneralPath) shape).lineTo(344.08997, 262.49997);
        ((GeneralPath) shape).lineTo(344.08997, 261.30698);
        ((GeneralPath) shape).curveTo(344.08997, 260.70297, 343.82898, 260.399,
                343.30597, 260.399);
        ((GeneralPath) shape).lineTo(341.34897, 260.399);
        ((GeneralPath) shape).lineTo(341.34897, 262.5);
        ((GeneralPath) shape).lineTo(340.76495, 262.5);
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
        ((GeneralPath) shape).moveTo(346.107, 258.868);
        ((GeneralPath) shape).lineTo(346.107, 262.5);
        ((GeneralPath) shape).lineTo(345.576, 262.5);
        ((GeneralPath) shape).lineTo(345.576, 258.868);
        ((GeneralPath) shape).lineTo(346.107, 258.868);
        ((GeneralPath) shape).moveTo(346.107, 257.31403);
        ((GeneralPath) shape).lineTo(346.107, 257.911);
        ((GeneralPath) shape).lineTo(345.576, 257.911);
        ((GeneralPath) shape).lineTo(345.576, 257.31403);
        ((GeneralPath) shape).lineTo(346.107, 257.31403);
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
        ((GeneralPath) shape).moveTo(348.488, 259.256);
        ((GeneralPath) shape).curveTo(348.071, 259.256, 347.801, 259.34302,
                347.676, 259.52002);
        ((GeneralPath) shape).curveTo(347.553, 259.69403, 347.49, 260.08002,
                347.49, 260.67703);
        ((GeneralPath) shape).curveTo(347.49, 261.27704, 347.551, 261.66605,
                347.676, 261.84503);
        ((GeneralPath) shape).curveTo(347.8, 262.02402, 348.07098, 262.11304,
                348.488, 262.11304);
        ((GeneralPath) shape).curveTo(348.91, 262.11304, 349.18802, 262.01605,
                349.323, 261.81903);
        ((GeneralPath) shape).curveTo(349.455, 261.62302, 349.523, 261.21503,
                349.523, 260.59302);
        ((GeneralPath) shape).curveTo(349.523, 260.04602, 349.458, 259.687,
                349.323, 259.514);
        ((GeneralPath) shape).curveTo(349.19, 259.34302, 348.911, 259.256,
                348.488, 259.256);
        ((GeneralPath) shape).moveTo(350.07, 258.868);
        ((GeneralPath) shape).lineTo(350.07, 262.73602);
        ((GeneralPath) shape).curveTo(350.07, 263.26202, 349.956, 263.62503,
                349.728, 263.82202);
        ((GeneralPath) shape).curveTo(349.5, 264.02002, 349.083, 264.118,
                348.477, 264.118);
        ((GeneralPath) shape).curveTo(347.936, 264.118, 347.567, 264.035,
                347.37, 263.868);
        ((GeneralPath) shape).curveTo(347.173, 263.70102, 347.074, 263.389,
                347.074, 262.93002);
        ((GeneralPath) shape).lineTo(347.586, 262.93002);
        ((GeneralPath) shape).curveTo(347.586, 263.24203, 347.644, 263.44302,
                347.759, 263.53802);
        ((GeneralPath) shape).curveTo(347.87302, 263.631, 348.125, 263.67804,
                348.515, 263.67804);
        ((GeneralPath) shape).curveTo(348.919, 263.67804, 349.191, 263.61703,
                349.33002, 263.49603);
        ((GeneralPath) shape).curveTo(349.46902, 263.37402, 349.53903,
                263.13702, 349.53903, 262.78204);
        ((GeneralPath) shape).lineTo(349.53903, 262.05304);
        ((GeneralPath) shape).lineTo(349.52704, 262.05005);
        ((GeneralPath) shape).curveTo(349.39105, 262.38605, 348.99704,
                262.55505, 348.34402, 262.55505);
        ((GeneralPath) shape).curveTo(347.81003, 262.55505, 347.44003,
                262.42404, 347.23703, 262.15805);
        ((GeneralPath) shape).curveTo(347.03604, 261.89404, 346.93304,
                261.41104, 346.93304, 260.70905);
        ((GeneralPath) shape).curveTo(346.93304, 259.98303, 347.03604,
                259.48505, 347.24304, 259.21805);
        ((GeneralPath) shape).curveTo(347.44803, 258.95206, 347.83105,
                258.81705, 348.38904, 258.81705);
        ((GeneralPath) shape).curveTo(348.98303, 258.81705, 349.37103,
                259.00006, 349.55304, 259.36404);
        ((GeneralPath) shape).lineTo(349.56503, 259.36005);
        ((GeneralPath) shape).lineTo(349.53903, 258.87006);
        ((GeneralPath) shape).lineTo(350.07004, 258.87006);
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
        ((GeneralPath) shape).moveTo(351.527, 257.314);
        ((GeneralPath) shape).lineTo(351.527, 259.316);
        ((GeneralPath) shape).lineTo(351.539, 259.324);
        ((GeneralPath) shape).curveTo(351.672, 258.986, 352.047, 258.815,
                352.661, 258.815);
        ((GeneralPath) shape).curveTo(353.571, 258.815, 354.026, 259.22,
                354.026, 260.031);
        ((GeneralPath) shape).lineTo(354.026, 262.5);
        ((GeneralPath) shape).lineTo(353.496, 262.5);
        ((GeneralPath) shape).lineTo(353.496, 260.076);
        ((GeneralPath) shape).curveTo(353.496, 259.529, 353.217, 259.25598,
                352.658, 259.25598);
        ((GeneralPath) shape).curveTo(352.20798, 259.25598, 351.90598,
                259.33597, 351.75598, 259.49698);
        ((GeneralPath) shape).curveTo(351.60397, 259.65698, 351.52698, 259.977,
                351.52698, 260.456);
        ((GeneralPath) shape).lineTo(351.52698, 262.5);
        ((GeneralPath) shape).lineTo(350.99698, 262.5);
        ((GeneralPath) shape).lineTo(350.99698, 257.314);
        ((GeneralPath) shape).lineTo(351.52698, 257.314);
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
        ((GeneralPath) shape).moveTo(356.911, 258.868);
        ((GeneralPath) shape).lineTo(356.911, 259.30902);
        ((GeneralPath) shape).lineTo(355.515, 259.30902);
        ((GeneralPath) shape).lineTo(355.515, 261.531);
        ((GeneralPath) shape).curveTo(355.515, 261.919, 355.686, 262.113,
                356.031, 262.113);
        ((GeneralPath) shape).curveTo(356.37302, 262.113, 356.544, 261.94,
                356.544, 261.592);
        ((GeneralPath) shape).lineTo(356.547, 261.41302);
        ((GeneralPath) shape).lineTo(356.555, 261.21204);
        ((GeneralPath) shape).lineTo(357.048, 261.21204);
        ((GeneralPath) shape).lineTo(357.052, 261.48203);
        ((GeneralPath) shape).curveTo(357.052, 262.196, 356.714, 262.55304,
                356.035, 262.55304);
        ((GeneralPath) shape).curveTo(355.336, 262.55304, 354.98502, 262.25705,
                354.98502, 261.66003);
        ((GeneralPath) shape).lineTo(354.98502, 259.30804);
        ((GeneralPath) shape).lineTo(354.484, 259.30804);
        ((GeneralPath) shape).lineTo(354.484, 258.86703);
        ((GeneralPath) shape).lineTo(354.98502, 258.86703);
        ((GeneralPath) shape).lineTo(354.98502, 257.99304);
        ((GeneralPath) shape).lineTo(355.515, 257.99304);
        ((GeneralPath) shape).lineTo(355.515, 258.86703);
        ((GeneralPath) shape).lineTo(356.911, 258.86703);
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
        stroke = new BasicStroke(0.973f, 0, 0, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(383.633, 263.632);
        ((GeneralPath) shape).curveTo(383.633, 264.361, 383.041, 264.955,
                382.30798, 264.955);
        ((GeneralPath) shape).lineTo(375.577, 264.955);
        ((GeneralPath) shape).curveTo(374.846, 264.955, 374.253, 264.361,
                374.253, 263.632);
        ((GeneralPath) shape).lineTo(374.253, 256.83);
        ((GeneralPath) shape).curveTo(374.253, 256.099, 374.84598, 255.50598,
                375.577, 255.50598);
        ((GeneralPath) shape).lineTo(382.30798, 255.50598);
        ((GeneralPath) shape).curveTo(383.041, 255.50598, 383.633, 256.09897,
                383.633, 256.83);
        ((GeneralPath) shape).lineTo(383.633, 263.632);
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
        stroke = new BasicStroke(0.973f, 0, 0, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(383.633, 274.66);
        ((GeneralPath) shape).curveTo(383.633, 275.389, 383.041, 275.984,
                382.30798, 275.984);
        ((GeneralPath) shape).lineTo(375.577, 275.984);
        ((GeneralPath) shape).curveTo(374.846, 275.984, 374.253, 275.39,
                374.253, 274.66);
        ((GeneralPath) shape).lineTo(374.253, 267.86002);
        ((GeneralPath) shape).curveTo(374.253, 267.12802, 374.84598, 266.535,
                375.577, 266.535);
        ((GeneralPath) shape).lineTo(382.30798, 266.535);
        ((GeneralPath) shape).curveTo(383.041, 266.535, 383.633, 267.128,
                383.633, 267.86002);
        ((GeneralPath) shape).lineTo(383.633, 274.66);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        ((GeneralPath) shape).moveTo(295.585, 268.837);
        ((GeneralPath) shape).lineTo(295.585, 270.649);
        ((GeneralPath) shape).lineTo(298.106, 270.649);
        ((GeneralPath) shape).lineTo(298.106, 271.144);
        ((GeneralPath) shape).lineTo(295.585, 271.144);
        ((GeneralPath) shape).lineTo(295.585, 273.529);
        ((GeneralPath) shape).lineTo(295.001, 273.529);
        ((GeneralPath) shape).lineTo(295.001, 268.343);
        ((GeneralPath) shape).lineTo(298.175, 268.343);
        ((GeneralPath) shape).lineTo(298.175, 268.837);
        ((GeneralPath) shape).lineTo(295.585, 268.837);
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
        ((GeneralPath) shape).moveTo(300.694, 269.897);
        ((GeneralPath) shape).lineTo(300.694, 270.338);
        ((GeneralPath) shape).lineTo(299.298, 270.338);
        ((GeneralPath) shape).lineTo(299.298, 272.561);
        ((GeneralPath) shape).curveTo(299.298, 272.949, 299.46802, 273.142,
                299.814, 273.142);
        ((GeneralPath) shape).curveTo(300.155, 273.142, 300.326, 272.969,
                300.326, 272.622);
        ((GeneralPath) shape).lineTo(300.32898, 272.44302);
        ((GeneralPath) shape).lineTo(300.33698, 272.24103);
        ((GeneralPath) shape).lineTo(300.83, 272.24103);
        ((GeneralPath) shape).lineTo(300.83398, 272.51102);
        ((GeneralPath) shape).curveTo(300.83398, 273.225, 300.49698, 273.58203,
                299.817, 273.58203);
        ((GeneralPath) shape).curveTo(299.11798, 273.58203, 298.767, 273.28604,
                298.767, 272.68903);
        ((GeneralPath) shape).lineTo(298.767, 270.33704);
        ((GeneralPath) shape).lineTo(298.267, 270.33704);
        ((GeneralPath) shape).lineTo(298.267, 269.89603);
        ((GeneralPath) shape).lineTo(298.767, 269.89603);
        ((GeneralPath) shape).lineTo(298.767, 269.02203);
        ((GeneralPath) shape).lineTo(299.298, 269.02203);
        ((GeneralPath) shape).lineTo(299.298, 269.89603);
        ((GeneralPath) shape).lineTo(300.694, 269.89603);
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
        shape = new Rectangle2D.Double(301.3039855957031, 272.8299865722656,
                0.5839999914169312, 0.6990000009536743);
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
        ((GeneralPath) shape).moveTo(306.591, 268.887);
        ((GeneralPath) shape).lineTo(306.591, 273.529);
        ((GeneralPath) shape).lineTo(306.007, 273.529);
        ((GeneralPath) shape).lineTo(306.007, 268.887);
        ((GeneralPath) shape).lineTo(304.32, 268.887);
        ((GeneralPath) shape).lineTo(304.32, 268.343);
        ((GeneralPath) shape).lineTo(308.263, 268.343);
        ((GeneralPath) shape).lineTo(308.263, 268.887);
        ((GeneralPath) shape).lineTo(306.591, 268.887);
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
        ((GeneralPath) shape).moveTo(311.717, 269.897);
        ((GeneralPath) shape).lineTo(311.717, 273.529);
        ((GeneralPath) shape).lineTo(311.186, 273.529);
        ((GeneralPath) shape).lineTo(311.224, 273.054);
        ((GeneralPath) shape).lineTo(311.212, 273.042);
        ((GeneralPath) shape).curveTo(311.028, 273.401, 310.634, 273.582,
                310.029, 273.582);
        ((GeneralPath) shape).curveTo(309.18298, 273.582, 308.758, 273.161,
                308.758, 272.313);
        ((GeneralPath) shape).lineTo(308.758, 269.896);
        ((GeneralPath) shape).lineTo(309.289, 269.896);
        ((GeneralPath) shape).lineTo(309.289, 272.313);
        ((GeneralPath) shape).curveTo(309.289, 272.642, 309.342, 272.862,
                309.452, 272.974);
        ((GeneralPath) shape).curveTo(309.559, 273.08398, 309.774, 273.141,
                310.093, 273.141);
        ((GeneralPath) shape).curveTo(310.50998, 273.141, 310.796, 273.059,
                310.951, 272.892);
        ((GeneralPath) shape).curveTo(311.106, 272.727, 311.184, 272.423,
                311.184, 271.978);
        ((GeneralPath) shape).lineTo(311.184, 269.896);
        ((GeneralPath) shape).lineTo(311.71698, 269.896);
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
        ((GeneralPath) shape).moveTo(312.749, 269.897);
        ((GeneralPath) shape).lineTo(313.28, 269.897);
        ((GeneralPath) shape).lineTo(313.227, 270.315);
        ((GeneralPath) shape).lineTo(313.23798, 270.327);
        ((GeneralPath) shape).curveTo(313.447, 269.985, 313.79398, 269.814,
                314.27698, 269.814);
        ((GeneralPath) shape).curveTo(314.94498, 269.814, 315.27798, 270.158,
                315.27798, 270.848);
        ((GeneralPath) shape).lineTo(315.275, 271.098);
        ((GeneralPath) shape).lineTo(314.751, 271.098);
        ((GeneralPath) shape).lineTo(314.763, 271.007);
        ((GeneralPath) shape).curveTo(314.771, 270.912, 314.775, 270.848,
                314.775, 270.814);
        ((GeneralPath) shape).curveTo(314.775, 270.441, 314.574, 270.255,
                314.169, 270.255);
        ((GeneralPath) shape).curveTo(313.577, 270.255, 313.281, 270.62,
                313.281, 271.353);
        ((GeneralPath) shape).lineTo(313.281, 273.53);
        ((GeneralPath) shape).lineTo(312.75, 273.53);
        ((GeneralPath) shape).lineTo(312.75, 269.897);
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
        ((GeneralPath) shape).moveTo(315.841, 269.897);
        ((GeneralPath) shape).lineTo(316.371, 269.897);
        ((GeneralPath) shape).lineTo(316.318, 270.315);
        ((GeneralPath) shape).lineTo(316.32898, 270.327);
        ((GeneralPath) shape).curveTo(316.538, 269.985, 316.88498, 269.814,
                317.36798, 269.814);
        ((GeneralPath) shape).curveTo(318.03598, 269.814, 318.369, 270.158,
                318.369, 270.848);
        ((GeneralPath) shape).lineTo(318.366, 271.098);
        ((GeneralPath) shape).lineTo(317.842, 271.098);
        ((GeneralPath) shape).lineTo(317.854, 271.007);
        ((GeneralPath) shape).curveTo(317.862, 270.912, 317.866, 270.848,
                317.866, 270.814);
        ((GeneralPath) shape).curveTo(317.866, 270.441, 317.665, 270.255,
                317.26, 270.255);
        ((GeneralPath) shape).curveTo(316.668, 270.255, 316.372, 270.62,
                316.372, 271.353);
        ((GeneralPath) shape).lineTo(316.372, 273.53);
        ((GeneralPath) shape).lineTo(315.842, 273.53);
        ((GeneralPath) shape).lineTo(315.842, 269.897);
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
        ((GeneralPath) shape).moveTo(321.399, 271.397);
        ((GeneralPath) shape).lineTo(321.395, 271.227);
        ((GeneralPath) shape).curveTo(321.395, 270.836, 321.331, 270.57898,
                321.202, 270.461);
        ((GeneralPath) shape).curveTo(321.073, 270.343, 320.797, 270.284,
                320.36798, 270.284);
        ((GeneralPath) shape).curveTo(319.939, 270.284, 319.66098, 270.352,
                319.53198, 270.491);
        ((GeneralPath) shape).curveTo(319.404, 270.628, 319.34097, 270.93,
                319.34097, 271.397);
        ((GeneralPath) shape).lineTo(321.399, 271.397);
        ((GeneralPath) shape).moveTo(321.399, 272.432);
        ((GeneralPath) shape).lineTo(321.94098, 272.432);
        ((GeneralPath) shape).lineTo(321.94498, 272.565);
        ((GeneralPath) shape).curveTo(321.94498, 272.941, 321.83197, 273.20502,
                321.602, 273.357);
        ((GeneralPath) shape).curveTo(321.374, 273.507, 320.972, 273.583,
                320.39798, 273.583);
        ((GeneralPath) shape).curveTo(319.72998, 273.583, 319.29297, 273.461,
                319.08398, 273.216);
        ((GeneralPath) shape).curveTo(318.87598, 272.973, 318.771, 272.458,
                318.771, 271.67502);
        ((GeneralPath) shape).curveTo(318.771, 270.95102, 318.875, 270.46503,
                319.085, 270.216);
        ((GeneralPath) shape).curveTo(319.294, 269.969, 319.705, 269.84302,
                320.317, 269.84302);
        ((GeneralPath) shape).curveTo(320.985, 269.84302, 321.421, 269.949,
                321.62997, 270.16602);
        ((GeneralPath) shape).curveTo(321.83597, 270.381, 321.94098, 270.83502,
                321.94098, 271.526);
        ((GeneralPath) shape).lineTo(321.94098, 271.811);
        ((GeneralPath) shape).lineTo(319.33197, 271.811);
        ((GeneralPath) shape).curveTo(319.33197, 272.383, 319.39398, 272.74802,
                319.51697, 272.905);
        ((GeneralPath) shape).curveTo(319.63797, 273.06, 319.92398, 273.13998,
                320.37497, 273.13998);
        ((GeneralPath) shape).curveTo(320.80197, 273.13998, 321.07898,
                273.10397, 321.20798, 273.02798);
        ((GeneralPath) shape).curveTo(321.335, 272.95398, 321.399, 272.793,
                321.399, 272.54398);
        ((GeneralPath) shape).lineTo(321.399, 272.43198);
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
        ((GeneralPath) shape).moveTo(324.699, 269.897);
        ((GeneralPath) shape).lineTo(324.699, 270.338);
        ((GeneralPath) shape).lineTo(323.303, 270.338);
        ((GeneralPath) shape).lineTo(323.303, 272.561);
        ((GeneralPath) shape).curveTo(323.303, 272.949, 323.47302, 273.142,
                323.819, 273.142);
        ((GeneralPath) shape).curveTo(324.16, 273.142, 324.331, 272.969,
                324.331, 272.622);
        ((GeneralPath) shape).lineTo(324.335, 272.44302);
        ((GeneralPath) shape).lineTo(324.34198, 272.24103);
        ((GeneralPath) shape).lineTo(324.835, 272.24103);
        ((GeneralPath) shape).lineTo(324.839, 272.51102);
        ((GeneralPath) shape).curveTo(324.839, 273.225, 324.50198, 273.58203,
                323.822, 273.58203);
        ((GeneralPath) shape).curveTo(323.123, 273.58203, 322.772, 273.28604,
                322.772, 272.68903);
        ((GeneralPath) shape).lineTo(322.772, 270.33704);
        ((GeneralPath) shape).lineTo(322.271, 270.33704);
        ((GeneralPath) shape).lineTo(322.271, 269.89603);
        ((GeneralPath) shape).lineTo(322.772, 269.89603);
        ((GeneralPath) shape).lineTo(322.772, 269.02203);
        ((GeneralPath) shape).lineTo(323.303, 269.02203);
        ((GeneralPath) shape).lineTo(323.303, 269.89603);
        ((GeneralPath) shape).lineTo(324.699, 269.89603);
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
        ((GeneralPath) shape).moveTo(341.295, 270.935);
        ((GeneralPath) shape).lineTo(342.983, 270.935);
        ((GeneralPath) shape).curveTo(343.427, 270.935, 343.73, 270.869,
                343.893, 270.732);
        ((GeneralPath) shape).curveTo(344.05402, 270.597, 344.13602, 270.34198,
                344.13602, 269.966);
        ((GeneralPath) shape).curveTo(344.13602, 269.499, 344.07703, 269.193,
                343.95502, 269.05002);
        ((GeneralPath) shape).curveTo(343.83603, 268.90903, 343.578, 268.838,
                343.18402, 268.838);
        ((GeneralPath) shape).lineTo(341.295, 268.838);
        ((GeneralPath) shape).lineTo(341.295, 270.935);
        ((GeneralPath) shape).moveTo(340.711, 273.529);
        ((GeneralPath) shape).lineTo(340.711, 268.343);
        ((GeneralPath) shape).lineTo(343.176, 268.343);
        ((GeneralPath) shape).curveTo(343.732, 268.343, 344.124, 268.45297,
                344.352, 268.67398);
        ((GeneralPath) shape).curveTo(344.58, 268.895, 344.693, 269.27597,
                344.693, 269.82098);
        ((GeneralPath) shape).curveTo(344.693, 270.3, 344.632, 270.632,
                344.507, 270.822);
        ((GeneralPath) shape).curveTo(344.384, 271.00998, 344.147, 271.132,
                343.79797, 271.189);
        ((GeneralPath) shape).lineTo(343.79797, 271.19998);
        ((GeneralPath) shape).curveTo(344.34598, 271.24, 344.62097, 271.57498,
                344.62097, 272.20297);
        ((GeneralPath) shape).lineTo(344.62097, 273.52896);
        ((GeneralPath) shape).lineTo(344.03696, 273.52896);
        ((GeneralPath) shape).lineTo(344.03696, 272.33597);
        ((GeneralPath) shape).curveTo(344.03696, 271.73196, 343.77496,
                271.42798, 343.25195, 271.42798);
        ((GeneralPath) shape).lineTo(341.29495, 271.42798);
        ((GeneralPath) shape).lineTo(341.29495, 273.529);
        ((GeneralPath) shape).lineTo(340.71094, 273.529);
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
        paint3(g, clip_, defaultTransform_, alpha__0, clip__0,
                defaultTransform__0, alpha__0_132, clip__0_132,
                defaultTransform__0_132);

    }

    private static void paint3(Graphics2D g, Shape clip_,
            AffineTransform defaultTransform_, float alpha__0, Shape clip__0,
            AffineTransform defaultTransform__0, float alpha__0_132,
            Shape clip__0_132, AffineTransform defaultTransform__0_132) {
        Shape shape;
        Paint paint;
        Stroke stroke;
        float origAlpha;
        // _0_132 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(345.576, 269.897);
        ((GeneralPath) shape).lineTo(346.106, 269.897);
        ((GeneralPath) shape).lineTo(346.05298, 270.315);
        ((GeneralPath) shape).lineTo(346.06396, 270.327);
        ((GeneralPath) shape).curveTo(346.27298, 269.985, 346.61996, 269.814,
                347.10297, 269.814);
        ((GeneralPath) shape).curveTo(347.77097, 269.814, 348.10498, 270.158,
                348.10498, 270.848);
        ((GeneralPath) shape).lineTo(348.10098, 271.098);
        ((GeneralPath) shape).lineTo(347.57797, 271.098);
        ((GeneralPath) shape).lineTo(347.58896, 271.007);
        ((GeneralPath) shape).curveTo(347.59695, 270.912, 347.60095, 270.848,
                347.60095, 270.814);
        ((GeneralPath) shape).curveTo(347.60095, 270.441, 347.39996, 270.255,
                346.99496, 270.255);
        ((GeneralPath) shape).curveTo(346.40295, 270.255, 346.10696, 270.62,
                346.10696, 271.353);
        ((GeneralPath) shape).lineTo(346.10696, 273.53);
        ((GeneralPath) shape).lineTo(345.57697, 273.53);
        ((GeneralPath) shape).lineTo(345.57697, 269.897);
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
        shape = new Rectangle2D.Double(348.6130065917969, 272.8299865722656,
                0.5839999914169312, 0.6990000009536743);
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
        ((GeneralPath) shape).moveTo(353.901, 268.887);
        ((GeneralPath) shape).lineTo(353.901, 273.529);
        ((GeneralPath) shape).lineTo(353.317, 273.529);
        ((GeneralPath) shape).lineTo(353.317, 268.887);
        ((GeneralPath) shape).lineTo(351.63, 268.887);
        ((GeneralPath) shape).lineTo(351.63, 268.343);
        ((GeneralPath) shape).lineTo(355.573, 268.343);
        ((GeneralPath) shape).lineTo(355.573, 268.887);
        ((GeneralPath) shape).lineTo(353.901, 268.887);
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
        ((GeneralPath) shape).moveTo(359.027, 269.897);
        ((GeneralPath) shape).lineTo(359.027, 273.529);
        ((GeneralPath) shape).lineTo(358.496, 273.529);
        ((GeneralPath) shape).lineTo(358.533, 273.054);
        ((GeneralPath) shape).lineTo(358.522, 273.042);
        ((GeneralPath) shape).curveTo(358.338, 273.401, 357.944, 273.582,
                357.338, 273.582);
        ((GeneralPath) shape).curveTo(356.492, 273.582, 356.06802, 273.161,
                356.06802, 272.313);
        ((GeneralPath) shape).lineTo(356.06802, 269.896);
        ((GeneralPath) shape).lineTo(356.59802, 269.896);
        ((GeneralPath) shape).lineTo(356.59802, 272.313);
        ((GeneralPath) shape).curveTo(356.59802, 272.642, 356.652, 272.862,
                356.76102, 272.974);
        ((GeneralPath) shape).curveTo(356.86902, 273.08398, 357.083, 273.141,
                357.403, 273.141);
        ((GeneralPath) shape).curveTo(357.82, 273.141, 358.10602, 273.059,
                358.26102, 272.892);
        ((GeneralPath) shape).curveTo(358.41602, 272.727, 358.49402, 272.423,
                358.49402, 271.978);
        ((GeneralPath) shape).lineTo(358.49402, 269.896);
        ((GeneralPath) shape).lineTo(359.027, 269.896);
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
        ((GeneralPath) shape).moveTo(360.06, 269.897);
        ((GeneralPath) shape).lineTo(360.591, 269.897);
        ((GeneralPath) shape).lineTo(360.53702, 270.315);
        ((GeneralPath) shape).lineTo(360.549, 270.327);
        ((GeneralPath) shape).curveTo(360.75702, 269.985, 361.105, 269.814,
                361.588, 269.814);
        ((GeneralPath) shape).curveTo(362.255, 269.814, 362.58902, 270.158,
                362.58902, 270.848);
        ((GeneralPath) shape).lineTo(362.58502, 271.098);
        ((GeneralPath) shape).lineTo(362.062, 271.098);
        ((GeneralPath) shape).lineTo(362.074, 271.007);
        ((GeneralPath) shape).curveTo(362.082, 270.912, 362.086, 270.848,
                362.086, 270.814);
        ((GeneralPath) shape).curveTo(362.086, 270.441, 361.885, 270.255,
                361.479, 270.255);
        ((GeneralPath) shape).curveTo(360.888, 270.255, 360.592, 270.62,
                360.592, 271.353);
        ((GeneralPath) shape).lineTo(360.592, 273.53);
        ((GeneralPath) shape).lineTo(360.061, 273.53);
        ((GeneralPath) shape).lineTo(360.061, 269.897);
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
        ((GeneralPath) shape).moveTo(363.15, 269.897);
        ((GeneralPath) shape).lineTo(363.681, 269.897);
        ((GeneralPath) shape).lineTo(363.628, 270.315);
        ((GeneralPath) shape).lineTo(363.63898, 270.327);
        ((GeneralPath) shape).curveTo(363.847, 269.985, 364.19498, 269.814,
                364.67798, 269.814);
        ((GeneralPath) shape).curveTo(365.34497, 269.814, 365.679, 270.158,
                365.679, 270.848);
        ((GeneralPath) shape).lineTo(365.675, 271.098);
        ((GeneralPath) shape).lineTo(365.15198, 271.098);
        ((GeneralPath) shape).lineTo(365.16397, 271.007);
        ((GeneralPath) shape).curveTo(365.17197, 270.912, 365.17596, 270.848,
                365.17596, 270.814);
        ((GeneralPath) shape).curveTo(365.17596, 270.441, 364.97498, 270.255,
                364.56998, 270.255);
        ((GeneralPath) shape).curveTo(363.97797, 270.255, 363.68198, 270.62,
                363.68198, 271.353);
        ((GeneralPath) shape).lineTo(363.68198, 273.53);
        ((GeneralPath) shape).lineTo(363.15097, 273.53);
        ((GeneralPath) shape).lineTo(363.15097, 269.897);
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
        ((GeneralPath) shape).moveTo(368.71, 271.397);
        ((GeneralPath) shape).lineTo(368.706, 271.227);
        ((GeneralPath) shape).curveTo(368.706, 270.836, 368.642, 270.57898,
                368.513, 270.461);
        ((GeneralPath) shape).curveTo(368.384, 270.343, 368.108, 270.284,
                367.679, 270.284);
        ((GeneralPath) shape).curveTo(367.25, 270.284, 366.972, 270.352,
                366.843, 270.491);
        ((GeneralPath) shape).curveTo(366.71597, 270.628, 366.65198, 270.93,
                366.65198, 271.397);
        ((GeneralPath) shape).lineTo(368.71, 271.397);
        ((GeneralPath) shape).moveTo(368.71, 272.432);
        ((GeneralPath) shape).lineTo(369.25198, 272.432);
        ((GeneralPath) shape).lineTo(369.25598, 272.565);
        ((GeneralPath) shape).curveTo(369.25598, 272.941, 369.14297, 273.20502,
                368.913, 273.357);
        ((GeneralPath) shape).curveTo(368.685, 273.507, 368.283, 273.583,
                367.70898, 273.583);
        ((GeneralPath) shape).curveTo(367.042, 273.583, 366.60397, 273.461,
                366.396, 273.216);
        ((GeneralPath) shape).curveTo(366.18698, 272.973, 366.083, 272.458,
                366.083, 271.67502);
        ((GeneralPath) shape).curveTo(366.083, 270.95102, 366.187, 270.46503,
                366.397, 270.216);
        ((GeneralPath) shape).curveTo(366.60602, 269.969, 367.017, 269.84302,
                367.629, 269.84302);
        ((GeneralPath) shape).curveTo(368.297, 269.84302, 368.733, 269.949,
                368.942, 270.16602);
        ((GeneralPath) shape).curveTo(369.149, 270.381, 369.253, 270.83502,
                369.253, 271.526);
        ((GeneralPath) shape).lineTo(369.253, 271.811);
        ((GeneralPath) shape).lineTo(366.645, 271.811);
        ((GeneralPath) shape).curveTo(366.645, 272.383, 366.706, 272.74802,
                366.82898, 272.905);
        ((GeneralPath) shape).curveTo(366.951, 273.06, 367.236, 273.13998,
                367.688, 273.13998);
        ((GeneralPath) shape).curveTo(368.11398, 273.13998, 368.391, 273.10397,
                368.52, 273.02798);
        ((GeneralPath) shape).curveTo(368.647, 272.95398, 368.711, 272.793,
                368.711, 272.54398);
        ((GeneralPath) shape).lineTo(368.711, 272.43198);
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
        ((GeneralPath) shape).moveTo(372.009, 269.897);
        ((GeneralPath) shape).lineTo(372.009, 270.338);
        ((GeneralPath) shape).lineTo(370.613, 270.338);
        ((GeneralPath) shape).lineTo(370.613, 272.561);
        ((GeneralPath) shape).curveTo(370.613, 272.949, 370.784, 273.142,
                371.129, 273.142);
        ((GeneralPath) shape).curveTo(371.471, 273.142, 371.642, 272.969,
                371.642, 272.622);
        ((GeneralPath) shape).lineTo(371.645, 272.44302);
        ((GeneralPath) shape).lineTo(371.65298, 272.24103);
        ((GeneralPath) shape).lineTo(372.146, 272.24103);
        ((GeneralPath) shape).lineTo(372.15, 272.51102);
        ((GeneralPath) shape).curveTo(372.15, 273.225, 371.81198, 273.58203,
                371.133, 273.58203);
        ((GeneralPath) shape).curveTo(370.434, 273.58203, 370.083, 273.28604,
                370.083, 272.68903);
        ((GeneralPath) shape).lineTo(370.083, 270.33704);
        ((GeneralPath) shape).lineTo(369.582, 270.33704);
        ((GeneralPath) shape).lineTo(369.582, 269.89603);
        ((GeneralPath) shape).lineTo(370.083, 269.89603);
        ((GeneralPath) shape).lineTo(370.083, 269.02203);
        ((GeneralPath) shape).lineTo(370.613, 269.02203);
        ((GeneralPath) shape).lineTo(370.613, 269.89603);
        ((GeneralPath) shape).lineTo(372.009, 269.89603);
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
        stroke = new BasicStroke(0.973f, 0, 0, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(336.672, 274.66);
        ((GeneralPath) shape).curveTo(336.672, 275.391, 336.08, 275.984,
                335.349, 275.984);
        ((GeneralPath) shape).lineTo(328.618, 275.984);
        ((GeneralPath) shape).curveTo(327.88702, 275.984, 327.293, 275.39102,
                327.293, 274.66);
        ((GeneralPath) shape).lineTo(327.293, 267.86002);
        ((GeneralPath) shape).curveTo(327.293, 267.12903, 327.887, 266.536,
                328.618, 266.536);
        ((GeneralPath) shape).lineTo(335.349, 266.536);
        ((GeneralPath) shape).curveTo(336.08, 266.536, 336.672, 267.129,
                336.672, 267.86002);
        ((GeneralPath) shape).lineTo(336.672, 274.66);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        ((GeneralPath) shape).moveTo(262.838, 239.355);
        ((GeneralPath) shape).lineTo(262.838, 244.541);
        ((GeneralPath) shape).lineTo(262.254, 244.541);
        ((GeneralPath) shape).lineTo(262.254, 240.081);
        ((GeneralPath) shape).lineTo(262.258, 239.92499);
        ((GeneralPath) shape).lineTo(262.261, 239.76999);
        ((GeneralPath) shape).lineTo(262.24597, 239.76999);
        ((GeneralPath) shape).lineTo(262.20096, 239.89198);
        ((GeneralPath) shape).curveTo(262.17795, 239.95299, 262.16296,
                239.99098, 262.15594, 240.00998);
        ((GeneralPath) shape).lineTo(262.05695, 240.25298);
        ((GeneralPath) shape).lineTo(260.29294, 244.54198);
        ((GeneralPath) shape).lineTo(259.70993, 244.54198);
        ((GeneralPath) shape).lineTo(257.94293, 240.30197);
        ((GeneralPath) shape).lineTo(257.83994, 240.06297);
        ((GeneralPath) shape).lineTo(257.79492, 239.94098);
        ((GeneralPath) shape).curveTo(257.78394, 239.91498, 257.76892,
                239.87498, 257.7499, 239.82298);
        ((GeneralPath) shape).lineTo(257.7349, 239.82298);
        ((GeneralPath) shape).lineTo(257.7389, 239.96298);
        ((GeneralPath) shape).lineTo(257.74188, 240.10799);
        ((GeneralPath) shape).lineTo(257.74188, 244.54099);
        ((GeneralPath) shape).lineTo(257.15787, 244.54099);
        ((GeneralPath) shape).lineTo(257.15787, 239.35498);
        ((GeneralPath) shape).lineTo(258.17087, 239.35498);
        ((GeneralPath) shape).lineTo(259.55087, 242.70998);
        ((GeneralPath) shape).lineTo(259.77087, 243.25298);
        ((GeneralPath) shape).lineTo(259.88086, 243.52298);
        ((GeneralPath) shape).lineTo(259.98685, 243.79298);
        ((GeneralPath) shape).lineTo(260.00186, 243.79298);
        ((GeneralPath) shape).lineTo(260.10785, 243.52298);
        ((GeneralPath) shape).curveTo(260.16086, 243.39398, 260.19485,
                243.30498, 260.21384, 243.25298);
        ((GeneralPath) shape).lineTo(260.43784, 242.71298);
        ((GeneralPath) shape).lineTo(261.81082, 239.35399);
        ((GeneralPath) shape).lineTo(262.83783, 239.35399);
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
        ((GeneralPath) shape).moveTo(265.292, 241.297);
        ((GeneralPath) shape).curveTo(264.822, 241.297, 264.53198, 241.373,
                264.418, 241.526);
        ((GeneralPath) shape).curveTo(264.306, 241.678, 264.249, 242.079,
                264.249, 242.725);
        ((GeneralPath) shape).curveTo(264.249, 243.371, 264.304, 243.77,
                264.418, 243.92401);
        ((GeneralPath) shape).curveTo(264.53, 244.076, 264.822, 244.154,
                265.292, 244.154);
        ((GeneralPath) shape).curveTo(265.76398, 244.154, 266.056, 244.078,
                266.16998, 243.92401);
        ((GeneralPath) shape).curveTo(266.28198, 243.77202, 266.33798,
                243.37102, 266.33798, 242.725);
        ((GeneralPath) shape).curveTo(266.33798, 242.07901, 266.283, 241.68001,
                266.16998, 241.526);
        ((GeneralPath) shape).curveTo(266.059, 241.375, 265.76697, 241.297,
                265.292, 241.297);
        ((GeneralPath) shape).moveTo(265.292, 240.856);
        ((GeneralPath) shape).curveTo(265.961, 240.856, 266.396, 240.972,
                266.59598, 241.20601);
        ((GeneralPath) shape).curveTo(266.79498, 241.43701, 266.89597, 241.945,
                266.89597, 242.72601);
        ((GeneralPath) shape).curveTo(266.89597, 243.50502, 266.79697,
                244.01201, 266.59598, 244.24602);
        ((GeneralPath) shape).curveTo(266.39697, 244.47702, 265.96298,
                244.59502, 265.292, 244.59502);
        ((GeneralPath) shape).curveTo(264.625, 244.59502, 264.192, 244.47902,
                263.991, 244.24602);
        ((GeneralPath) shape).curveTo(263.792, 244.01402, 263.692, 243.50702,
                263.692, 242.72601);
        ((GeneralPath) shape).curveTo(263.692, 241.947, 263.791, 241.44002,
                263.991, 241.20601);
        ((GeneralPath) shape).curveTo(264.191, 240.97401, 264.625, 240.856,
                265.292, 240.856);
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
        ((GeneralPath) shape).moveTo(269.646, 240.909);
        ((GeneralPath) shape).lineTo(269.646, 241.34999);
        ((GeneralPath) shape).lineTo(268.25, 241.34999);
        ((GeneralPath) shape).lineTo(268.25, 243.57199);
        ((GeneralPath) shape).curveTo(268.25, 243.95999, 268.421, 244.15399,
                268.766, 244.15399);
        ((GeneralPath) shape).curveTo(269.107, 244.15399, 269.27798, 243.98099,
                269.27798, 243.633);
        ((GeneralPath) shape).lineTo(269.28198, 243.454);
        ((GeneralPath) shape).lineTo(269.28897, 243.25299);
        ((GeneralPath) shape).lineTo(269.78198, 243.25299);
        ((GeneralPath) shape).lineTo(269.78598, 243.523);
        ((GeneralPath) shape).curveTo(269.78598, 244.237, 269.44797, 244.594,
                268.76898, 244.594);
        ((GeneralPath) shape).curveTo(268.06897, 244.594, 267.719, 244.29799,
                267.719, 243.70099);
        ((GeneralPath) shape).lineTo(267.719, 241.34898);
        ((GeneralPath) shape).lineTo(267.219, 241.34898);
        ((GeneralPath) shape).lineTo(267.219, 240.90799);
        ((GeneralPath) shape).lineTo(267.719, 240.90799);
        ((GeneralPath) shape).lineTo(267.719, 240.034);
        ((GeneralPath) shape).lineTo(268.25, 240.034);
        ((GeneralPath) shape).lineTo(268.25, 240.90799);
        ((GeneralPath) shape).lineTo(269.646, 240.90799);
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
        ((GeneralPath) shape).moveTo(270.787, 240.909);
        ((GeneralPath) shape).lineTo(270.787, 244.541);
        ((GeneralPath) shape).lineTo(270.25598, 244.541);
        ((GeneralPath) shape).lineTo(270.25598, 240.909);
        ((GeneralPath) shape).lineTo(270.787, 240.909);
        ((GeneralPath) shape).moveTo(270.787, 239.355);
        ((GeneralPath) shape).lineTo(270.787, 239.952);
        ((GeneralPath) shape).lineTo(270.25598, 239.952);
        ((GeneralPath) shape).lineTo(270.25598, 239.355);
        ((GeneralPath) shape).lineTo(270.787, 239.355);
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
        ((GeneralPath) shape).moveTo(274.401, 240.909);
        ((GeneralPath) shape).lineTo(273.188, 244.541);
        ((GeneralPath) shape).lineTo(272.425, 244.541);
        ((GeneralPath) shape).lineTo(271.223, 240.909);
        ((GeneralPath) shape).lineTo(271.769, 240.909);
        ((GeneralPath) shape).lineTo(272.41, 242.885);
        ((GeneralPath) shape).lineTo(272.611, 243.504);
        ((GeneralPath) shape).lineTo(272.706, 243.815);
        ((GeneralPath) shape).lineTo(272.804, 244.127);
        ((GeneralPath) shape).lineTo(272.819, 244.127);
        ((GeneralPath) shape).lineTo(272.91, 243.819);
        ((GeneralPath) shape).lineTo(273.001, 243.508);
        ((GeneralPath) shape).lineTo(273.195, 242.893);
        ((GeneralPath) shape).lineTo(273.809, 240.909);
        ((GeneralPath) shape).lineTo(274.401, 240.909);
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
        ((GeneralPath) shape).moveTo(277.359, 242.41);
        ((GeneralPath) shape).lineTo(277.355, 242.239);
        ((GeneralPath) shape).curveTo(277.355, 241.84799, 277.29102, 241.592,
                277.16202, 241.47299);
        ((GeneralPath) shape).curveTo(277.03302, 241.355, 276.75702, 241.297,
                276.328, 241.297);
        ((GeneralPath) shape).curveTo(275.89902, 241.297, 275.621, 241.36499,
                275.492, 241.504);
        ((GeneralPath) shape).curveTo(275.365, 241.64099, 275.301, 241.942,
                275.301, 242.41);
        ((GeneralPath) shape).lineTo(277.359, 242.41);
        ((GeneralPath) shape).moveTo(277.359, 243.44301);
        ((GeneralPath) shape).lineTo(277.901, 243.44301);
        ((GeneralPath) shape).lineTo(277.905, 243.576);
        ((GeneralPath) shape).curveTo(277.905, 243.95201, 277.791, 244.216,
                277.562, 244.36801);
        ((GeneralPath) shape).curveTo(277.334, 244.518, 276.933, 244.59401,
                276.358, 244.59401);
        ((GeneralPath) shape).curveTo(275.691, 244.59401, 275.253, 244.47202,
                275.044, 244.22801);
        ((GeneralPath) shape).curveTo(274.836, 243.98502, 274.73102, 243.47002,
                274.73102, 242.68701);
        ((GeneralPath) shape).curveTo(274.73102, 241.96301, 274.83502, 241.477,
                275.045, 241.22801);
        ((GeneralPath) shape).curveTo(275.25302, 240.98102, 275.665, 240.85501,
                276.277, 240.85501);
        ((GeneralPath) shape).curveTo(276.944, 240.85501, 277.381, 240.96101,
                277.58902, 241.17801);
        ((GeneralPath) shape).curveTo(277.79602, 241.393, 277.90002, 241.84702,
                277.90002, 242.53801);
        ((GeneralPath) shape).lineTo(277.90002, 242.82301);
        ((GeneralPath) shape).lineTo(275.29102, 242.82301);
        ((GeneralPath) shape).curveTo(275.29102, 243.39401, 275.35202,
                243.75902, 275.476, 243.917);
        ((GeneralPath) shape).curveTo(275.59702, 244.07301, 275.88303, 244.153,
                276.334, 244.153);
        ((GeneralPath) shape).curveTo(276.76102, 244.153, 277.03802, 244.117,
                277.16702, 244.041);
        ((GeneralPath) shape).curveTo(277.29404, 243.967, 277.35803, 243.806,
                277.35803, 243.557);
        ((GeneralPath) shape).lineTo(277.35803, 243.44301);
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
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(284.254, 240.761);
        ((GeneralPath) shape).lineTo(283.674, 240.761);
        ((GeneralPath) shape).curveTo(283.674, 240.343, 283.60602, 240.079,
                283.465, 239.966);
        ((GeneralPath) shape).curveTo(283.327, 239.854, 282.999, 239.797,
                282.483, 239.797);
        ((GeneralPath) shape).curveTo(281.87302, 239.797, 281.478, 239.84999,
                281.3, 239.958);
        ((GeneralPath) shape).curveTo(281.124, 240.064, 281.034, 240.306,
                281.034, 240.678);
        ((GeneralPath) shape).curveTo(281.034, 241.096, 281.102, 241.34999,
                281.243, 241.441);
        ((GeneralPath) shape).curveTo(281.381, 241.532, 281.795, 241.59299,
                282.483, 241.627);
        ((GeneralPath) shape).curveTo(283.289, 241.662, 283.801, 241.76399,
                284.021, 241.935);
        ((GeneralPath) shape).curveTo(284.23898, 242.104, 284.349, 242.484,
                284.349, 243.075);
        ((GeneralPath) shape).curveTo(284.349, 243.713, 284.224, 244.125,
                283.972, 244.314);
        ((GeneralPath) shape).curveTo(283.722, 244.5, 283.166, 244.595,
                282.305, 244.595);
        ((GeneralPath) shape).curveTo(281.56, 244.595, 281.063, 244.502,
                280.817, 244.312);
        ((GeneralPath) shape).curveTo(280.56998, 244.124, 280.447, 243.744,
                280.447, 243.17);
        ((GeneralPath) shape).lineTo(280.443, 242.939);
        ((GeneralPath) shape).lineTo(281.02298, 242.939);
        ((GeneralPath) shape).lineTo(281.02298, 243.068);
        ((GeneralPath) shape).curveTo(281.02298, 243.53099, 281.093, 243.818,
                281.235, 243.93199);
        ((GeneralPath) shape).curveTo(281.375, 244.04399, 281.73898, 244.101,
                282.323, 244.101);
        ((GeneralPath) shape).curveTo(282.992, 244.101, 283.404, 244.046,
                283.559, 243.93199);
        ((GeneralPath) shape).curveTo(283.71298, 243.81999, 283.78998, 243.519,
                283.78998, 243.03);
        ((GeneralPath) shape).curveTo(283.78998, 242.715, 283.73898, 242.504,
                283.63297, 242.397);
        ((GeneralPath) shape).curveTo(283.52896, 242.293, 283.30896, 242.23,
                282.97498, 242.209);
        ((GeneralPath) shape).lineTo(282.369, 242.179);
        ((GeneralPath) shape).lineTo(281.792, 242.149);
        ((GeneralPath) shape).curveTo(280.917, 242.088, 280.477, 241.632,
                280.477, 240.781);
        ((GeneralPath) shape).curveTo(280.477, 240.192, 280.604, 239.79701,
                280.86, 239.599);
        ((GeneralPath) shape).curveTo(281.11398, 239.402, 281.624, 239.303,
                282.388, 239.303);
        ((GeneralPath) shape).curveTo(283.161, 239.303, 283.66602, 239.39499,
                283.901, 239.579);
        ((GeneralPath) shape).curveTo(284.136, 239.76, 284.254, 240.155,
                284.254, 240.761);
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
        ((GeneralPath) shape).moveTo(287.602, 240.909);
        ((GeneralPath) shape).lineTo(286.57397, 244.75);
        ((GeneralPath) shape).curveTo(286.42996, 245.292, 286.27097, 245.664,
                286.09497, 245.867);
        ((GeneralPath) shape).curveTo(285.92096, 246.06801, 285.66898, 246.171,
                285.34296, 246.171);
        ((GeneralPath) shape).curveTo(285.27795, 246.171, 285.19296, 246.16301,
                285.08896, 246.14401);
        ((GeneralPath) shape).lineTo(285.08896, 245.70302);
        ((GeneralPath) shape).curveTo(285.16095, 245.71802, 285.22397,
                245.72601, 285.27496, 245.73001);
        ((GeneralPath) shape).curveTo(285.55997, 245.74501, 285.77197,
                245.51302, 285.91196, 245.035);
        ((GeneralPath) shape).lineTo(286.01495, 244.67801);
        ((GeneralPath) shape).curveTo(286.01596, 244.66602, 286.02795, 244.621,
                286.04895, 244.54102);
        ((GeneralPath) shape).lineTo(285.84796, 244.54102);
        ((GeneralPath) shape).lineTo(284.53595, 240.90901);
        ((GeneralPath) shape).lineTo(285.10095, 240.90901);
        ((GeneralPath) shape).lineTo(285.65796, 242.52802);
        ((GeneralPath) shape).lineTo(285.93896, 243.33301);
        ((GeneralPath) shape).lineTo(286.07495, 243.74);
        ((GeneralPath) shape).lineTo(286.21594, 244.142);
        ((GeneralPath) shape).lineTo(286.23096, 244.142);
        ((GeneralPath) shape).lineTo(286.32996, 243.74);
        ((GeneralPath) shape).lineTo(286.42496, 243.33301);
        ((GeneralPath) shape).lineTo(286.62994, 242.52802);
        ((GeneralPath) shape).lineTo(287.03995, 240.90901);
        ((GeneralPath) shape).lineTo(287.60196, 240.90901);
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
        ((GeneralPath) shape).moveTo(290.857, 241.859);
        ((GeneralPath) shape).lineTo(290.326, 241.859);
        ((GeneralPath) shape).curveTo(290.326, 241.607, 290.275, 241.44899,
                290.172, 241.388);
        ((GeneralPath) shape).curveTo(290.069, 241.327, 289.806, 241.297,
                289.381, 241.297);
        ((GeneralPath) shape).curveTo(288.98602, 241.297, 288.73602, 241.329,
                288.631, 241.394);
        ((GeneralPath) shape).curveTo(288.52502, 241.458, 288.471, 241.612,
                288.471, 241.859);
        ((GeneralPath) shape).curveTo(288.471, 242.232, 288.64902, 242.42499,
                289.006, 242.444);
        ((GeneralPath) shape).lineTo(289.435, 242.466);
        ((GeneralPath) shape).lineTo(289.977, 242.492);
        ((GeneralPath) shape).curveTo(290.633, 242.52501, 290.96298, 242.86801,
                290.96298, 243.526);
        ((GeneralPath) shape).curveTo(290.96298, 243.933, 290.85498, 244.214,
                290.637, 244.366);
        ((GeneralPath) shape).curveTo(290.421, 244.51799, 290.02298, 244.594,
                289.442, 244.594);
        ((GeneralPath) shape).curveTo(288.849, 244.594, 288.439, 244.52199,
                288.215, 244.37799);
        ((GeneralPath) shape).curveTo(287.991, 244.234, 287.879, 243.96999,
                287.879, 243.58398);
        ((GeneralPath) shape).lineTo(287.883, 243.38599);
        ((GeneralPath) shape).lineTo(288.432, 243.38599);
        ((GeneralPath) shape).lineTo(288.436, 243.55699);
        ((GeneralPath) shape).curveTo(288.436, 243.795, 288.497, 243.954,
                288.618, 244.034);
        ((GeneralPath) shape).curveTo(288.74002, 244.114, 288.978, 244.15399,
                289.33502, 244.15399);
        ((GeneralPath) shape).curveTo(289.77103, 244.15399, 290.05902,
                244.11198, 290.19702, 244.02899);
        ((GeneralPath) shape).curveTo(290.334, 243.946, 290.40402, 243.771,
                290.40402, 243.50499);
        ((GeneralPath) shape).curveTo(290.40402, 243.12299, 290.23203,
                242.93199, 289.88403, 242.93199);
        ((GeneralPath) shape).curveTo(289.07803, 242.93199, 288.54803,
                242.86299, 288.29303, 242.726);
        ((GeneralPath) shape).curveTo(288.03903, 242.589, 287.91202, 242.30699,
                287.91202, 241.875);
        ((GeneralPath) shape).curveTo(287.91202, 241.469, 288.01202, 241.197,
                288.213, 241.061);
        ((GeneralPath) shape).curveTo(288.414, 240.92601, 288.81403, 240.85701,
                289.41302, 240.85701);
        ((GeneralPath) shape).curveTo(290.37204, 240.85701, 290.85403,
                241.14601, 290.85403, 241.727);
        ((GeneralPath) shape).lineTo(290.85403, 241.85901);
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
        ((GeneralPath) shape).moveTo(293.651, 240.909);
        ((GeneralPath) shape).lineTo(293.651, 241.34999);
        ((GeneralPath) shape).lineTo(292.255, 241.34999);
        ((GeneralPath) shape).lineTo(292.255, 243.57199);
        ((GeneralPath) shape).curveTo(292.255, 243.95999, 292.42502, 244.15399,
                292.771, 244.15399);
        ((GeneralPath) shape).curveTo(293.112, 244.15399, 293.283, 243.98099,
                293.283, 243.633);
        ((GeneralPath) shape).lineTo(293.28598, 243.454);
        ((GeneralPath) shape).lineTo(293.29398, 243.25299);
        ((GeneralPath) shape).lineTo(293.787, 243.25299);
        ((GeneralPath) shape).lineTo(293.791, 243.523);
        ((GeneralPath) shape).curveTo(293.791, 244.237, 293.45398, 244.594,
                292.774, 244.594);
        ((GeneralPath) shape).curveTo(292.07498, 244.594, 291.724, 244.29799,
                291.724, 243.70099);
        ((GeneralPath) shape).lineTo(291.724, 241.34898);
        ((GeneralPath) shape).lineTo(291.224, 241.34898);
        ((GeneralPath) shape).lineTo(291.224, 240.90799);
        ((GeneralPath) shape).lineTo(291.724, 240.90799);
        ((GeneralPath) shape).lineTo(291.724, 240.034);
        ((GeneralPath) shape).lineTo(292.255, 240.034);
        ((GeneralPath) shape).lineTo(292.255, 240.90799);
        ((GeneralPath) shape).lineTo(293.651, 240.90799);
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
        ((GeneralPath) shape).moveTo(296.782, 242.41);
        ((GeneralPath) shape).lineTo(296.778, 242.239);
        ((GeneralPath) shape).curveTo(296.778, 241.84799, 296.71402, 241.592,
                296.58502, 241.47299);
        ((GeneralPath) shape).curveTo(296.45602, 241.355, 296.17902, 241.297,
                295.751, 241.297);
        ((GeneralPath) shape).curveTo(295.323, 241.297, 295.044, 241.36499,
                294.915, 241.504);
        ((GeneralPath) shape).curveTo(294.788, 241.64099, 294.724, 241.942,
                294.724, 242.41);
        ((GeneralPath) shape).lineTo(296.782, 242.41);
        ((GeneralPath) shape).moveTo(296.782, 243.44301);
        ((GeneralPath) shape).lineTo(297.324, 243.44301);
        ((GeneralPath) shape).lineTo(297.328, 243.576);
        ((GeneralPath) shape).curveTo(297.328, 243.95201, 297.214, 244.216,
                296.98502, 244.36801);
        ((GeneralPath) shape).curveTo(296.75702, 244.518, 296.35602, 244.59401,
                295.781, 244.59401);
        ((GeneralPath) shape).curveTo(295.114, 244.59401, 294.676, 244.47202,
                294.46802, 244.22801);
        ((GeneralPath) shape).curveTo(294.259, 243.98502, 294.15503, 243.47002,
                294.15503, 242.68701);
        ((GeneralPath) shape).curveTo(294.15503, 241.96301, 294.25903, 241.477,
                294.46902, 241.22801);
        ((GeneralPath) shape).curveTo(294.67804, 240.98102, 295.08902,
                240.85501, 295.70102, 240.85501);
        ((GeneralPath) shape).curveTo(296.368, 240.85501, 296.80502, 240.96101,
                297.014, 241.17801);
        ((GeneralPath) shape).curveTo(297.221, 241.393, 297.325, 241.84702,
                297.325, 242.53801);
        ((GeneralPath) shape).lineTo(297.325, 242.82301);
        ((GeneralPath) shape).lineTo(294.716, 242.82301);
        ((GeneralPath) shape).curveTo(294.716, 243.39401, 294.777, 243.75902,
                294.9, 243.917);
        ((GeneralPath) shape).curveTo(295.021, 244.07301, 295.307, 244.153,
                295.759, 244.153);
        ((GeneralPath) shape).curveTo(296.185, 244.153, 296.463, 244.117,
                296.592, 244.041);
        ((GeneralPath) shape).curveTo(296.71902, 243.967, 296.78302, 243.806,
                296.78302, 243.557);
        ((GeneralPath) shape).lineTo(296.78302, 243.44301);
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
        ((GeneralPath) shape).moveTo(298.159, 240.909);
        ((GeneralPath) shape).lineTo(298.69, 240.909);
        ((GeneralPath) shape).lineTo(298.67902, 241.456);
        ((GeneralPath) shape).lineTo(298.69, 241.46799);
        ((GeneralPath) shape).curveTo(298.895, 241.06099, 299.289, 240.85599,
                299.87302, 240.85599);
        ((GeneralPath) shape).curveTo(300.48703, 240.85599, 300.859, 241.05998,
                300.992, 241.46799);
        ((GeneralPath) shape).lineTo(301.00702, 241.46799);
        ((GeneralPath) shape).curveTo(301.23502, 241.06099, 301.65002,
                240.85599, 302.25403, 240.85599);
        ((GeneralPath) shape).curveTo(303.10803, 240.85599, 303.53604,
                241.29298, 303.53604, 242.16699);
        ((GeneralPath) shape).lineTo(303.53604, 244.54199);
        ((GeneralPath) shape).lineTo(303.00504, 244.54199);
        ((GeneralPath) shape).lineTo(303.00504, 242.118);
        ((GeneralPath) shape).curveTo(303.00504, 241.799, 302.94803, 241.583,
                302.83502, 241.469);
        ((GeneralPath) shape).curveTo(302.72202, 241.355, 302.50702, 241.29799,
                302.19003, 241.29799);
        ((GeneralPath) shape).curveTo(301.77103, 241.29799, 301.48602,
                241.37599, 301.33603, 241.53499);
        ((GeneralPath) shape).curveTo(301.18903, 241.693, 301.11203, 241.995,
                301.11203, 242.441);
        ((GeneralPath) shape).lineTo(301.11203, 244.54199);
        ((GeneralPath) shape).lineTo(300.58102, 244.54199);
        ((GeneralPath) shape).lineTo(300.58102, 242.16699);
        ((GeneralPath) shape).lineTo(300.574, 242.0);
        ((GeneralPath) shape).curveTo(300.574, 241.532, 300.309, 241.297,
                299.77402, 241.297);
        ((GeneralPath) shape).curveTo(299.05103, 241.297, 298.69, 241.69,
                298.69, 242.478);
        ((GeneralPath) shape).lineTo(298.69, 244.541);
        ((GeneralPath) shape).lineTo(298.159, 244.541);
        ((GeneralPath) shape).lineTo(298.159, 240.909);
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
        ((GeneralPath) shape).moveTo(310.735, 239.355);
        ((GeneralPath) shape).lineTo(310.735, 244.541);
        ((GeneralPath) shape).lineTo(310.152, 244.541);
        ((GeneralPath) shape).lineTo(310.152, 242.137);
        ((GeneralPath) shape).lineTo(307.126, 242.137);
        ((GeneralPath) shape).lineTo(307.126, 244.541);
        ((GeneralPath) shape).lineTo(306.542, 244.541);
        ((GeneralPath) shape).lineTo(306.542, 239.355);
        ((GeneralPath) shape).lineTo(307.126, 239.355);
        ((GeneralPath) shape).lineTo(307.126, 241.643);
        ((GeneralPath) shape).lineTo(310.152, 241.643);
        ((GeneralPath) shape).lineTo(310.152, 239.355);
        ((GeneralPath) shape).lineTo(310.735, 239.355);
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
        ((GeneralPath) shape).moveTo(312.219, 240.909);
        ((GeneralPath) shape).lineTo(312.219, 244.541);
        ((GeneralPath) shape).lineTo(311.688, 244.541);
        ((GeneralPath) shape).lineTo(311.688, 240.909);
        ((GeneralPath) shape).lineTo(312.219, 240.909);
        ((GeneralPath) shape).moveTo(312.219, 239.355);
        ((GeneralPath) shape).lineTo(312.219, 239.952);
        ((GeneralPath) shape).lineTo(311.688, 239.952);
        ((GeneralPath) shape).lineTo(311.688, 239.355);
        ((GeneralPath) shape).lineTo(312.219, 239.355);
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
        ((GeneralPath) shape).moveTo(315.069, 240.909);
        ((GeneralPath) shape).lineTo(315.069, 241.34999);
        ((GeneralPath) shape).lineTo(313.673, 241.34999);
        ((GeneralPath) shape).lineTo(313.673, 243.57199);
        ((GeneralPath) shape).curveTo(313.673, 243.95999, 313.844, 244.15399,
                314.19, 244.15399);
        ((GeneralPath) shape).curveTo(314.531, 244.15399, 314.702, 243.98099,
                314.702, 243.633);
        ((GeneralPath) shape).lineTo(314.706, 243.454);
        ((GeneralPath) shape).lineTo(314.71298, 243.25299);
        ((GeneralPath) shape).lineTo(315.206, 243.25299);
        ((GeneralPath) shape).lineTo(315.21, 243.523);
        ((GeneralPath) shape).curveTo(315.21, 244.237, 314.87198, 244.594,
                314.193, 244.594);
        ((GeneralPath) shape).curveTo(313.494, 244.594, 313.143, 244.29799,
                313.143, 243.70099);
        ((GeneralPath) shape).lineTo(313.143, 241.34898);
        ((GeneralPath) shape).lineTo(312.642, 241.34898);
        ((GeneralPath) shape).lineTo(312.642, 240.90799);
        ((GeneralPath) shape).lineTo(313.143, 240.90799);
        ((GeneralPath) shape).lineTo(313.143, 240.034);
        ((GeneralPath) shape).lineTo(313.673, 240.034);
        ((GeneralPath) shape).lineTo(313.673, 240.90799);
        ((GeneralPath) shape).lineTo(315.069, 240.90799);
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
        ((GeneralPath) shape).moveTo(318.488, 241.859);
        ((GeneralPath) shape).lineTo(317.958, 241.859);
        ((GeneralPath) shape).curveTo(317.958, 241.607, 317.906, 241.44899,
                317.805, 241.388);
        ((GeneralPath) shape).curveTo(317.702, 241.327, 317.439, 241.297,
                317.014, 241.297);
        ((GeneralPath) shape).curveTo(316.61902, 241.297, 316.36902, 241.329,
                316.263, 241.394);
        ((GeneralPath) shape).curveTo(316.157, 241.459, 316.104, 241.612,
                316.104, 241.859);
        ((GeneralPath) shape).curveTo(316.104, 242.232, 316.283, 242.42499,
                316.639, 242.444);
        ((GeneralPath) shape).lineTo(317.06702, 242.466);
        ((GeneralPath) shape).lineTo(317.61002, 242.492);
        ((GeneralPath) shape).curveTo(318.265, 242.52501, 318.595, 242.86801,
                318.595, 243.526);
        ((GeneralPath) shape).curveTo(318.595, 243.933, 318.488, 244.214,
                318.269, 244.366);
        ((GeneralPath) shape).curveTo(318.053, 244.51799, 317.655, 244.594,
                317.075, 244.594);
        ((GeneralPath) shape).curveTo(316.48102, 244.594, 316.07202, 244.52199,
                315.84802, 244.37799);
        ((GeneralPath) shape).curveTo(315.62402, 244.234, 315.51202, 243.96999,
                315.51202, 243.58398);
        ((GeneralPath) shape).lineTo(315.51602, 243.38599);
        ((GeneralPath) shape).lineTo(316.066, 243.38599);
        ((GeneralPath) shape).lineTo(316.07, 243.55699);
        ((GeneralPath) shape).curveTo(316.07, 243.795, 316.131, 243.954,
                316.25302, 244.034);
        ((GeneralPath) shape).curveTo(316.37402, 244.114, 316.613, 244.15399,
                316.96902, 244.15399);
        ((GeneralPath) shape).curveTo(317.40604, 244.15399, 317.69403,
                244.11198, 317.83203, 244.02899);
        ((GeneralPath) shape).curveTo(317.96802, 243.946, 318.03903, 243.771,
                318.03903, 243.50499);
        ((GeneralPath) shape).curveTo(318.03903, 243.12299, 317.86603,
                242.93199, 317.51804, 242.93199);
        ((GeneralPath) shape).curveTo(316.71204, 242.93199, 316.18204,
                242.86299, 315.92703, 242.726);
        ((GeneralPath) shape).curveTo(315.67303, 242.589, 315.54602, 242.30699,
                315.54602, 241.875);
        ((GeneralPath) shape).curveTo(315.54602, 241.469, 315.64703, 241.197,
                315.84802, 241.061);
        ((GeneralPath) shape).curveTo(316.049, 240.92601, 316.44904, 240.85701,
                317.04803, 240.85701);
        ((GeneralPath) shape).curveTo(318.00705, 240.85701, 318.48804,
                241.14601, 318.48804, 241.727);
        ((GeneralPath) shape).lineTo(318.48804, 241.85901);
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
        ((GeneralPath) shape).moveTo(341.225, 243.729);
        ((GeneralPath) shape).lineTo(341.225, 242.376);
        ((GeneralPath) shape).lineTo(339.888, 242.376);
        ((GeneralPath) shape).lineTo(339.888, 241.944);
        ((GeneralPath) shape).lineTo(341.225, 241.944);
        ((GeneralPath) shape).lineTo(341.225, 240.584);
        ((GeneralPath) shape).lineTo(341.688, 240.584);
        ((GeneralPath) shape).lineTo(341.688, 241.944);
        ((GeneralPath) shape).lineTo(343.026, 241.944);
        ((GeneralPath) shape).lineTo(343.026, 242.376);
        ((GeneralPath) shape).lineTo(341.688, 242.376);
        ((GeneralPath) shape).lineTo(341.688, 243.729);
        ((GeneralPath) shape).lineTo(341.225, 243.729);
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
        ((GeneralPath) shape).moveTo(346.227, 239.89);
        ((GeneralPath) shape).lineTo(346.227, 244.427);
        ((GeneralPath) shape).lineTo(345.715, 244.427);
        ((GeneralPath) shape).lineTo(345.715, 240.272);
        ((GeneralPath) shape).lineTo(344.557, 241.558);
        ((GeneralPath) shape).lineTo(344.235, 241.252);
        ((GeneralPath) shape).lineTo(345.489, 239.89);
        ((GeneralPath) shape).lineTo(346.227, 239.89);
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
        stroke = new BasicStroke(0.973f, 0, 0, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(348.351, 245.105);
        ((GeneralPath) shape).curveTo(348.351, 245.834, 347.759, 246.428,
                347.026, 246.428);
        ((GeneralPath) shape).lineTo(340.296, 246.428);
        ((GeneralPath) shape).curveTo(339.56598, 246.428, 338.97098, 245.834,
                338.97098, 245.105);
        ((GeneralPath) shape).lineTo(338.97098, 238.305);
        ((GeneralPath) shape).curveTo(338.97098, 237.573, 339.56598, 236.98,
                340.296, 236.98);
        ((GeneralPath) shape).lineTo(347.026, 236.98);
        ((GeneralPath) shape).curveTo(347.759, 236.98, 348.351, 237.573,
                348.351, 238.305);
        ((GeneralPath) shape).lineTo(348.351, 245.105);
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
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(352.986, 243.729);
        ((GeneralPath) shape).lineTo(352.986, 242.376);
        ((GeneralPath) shape).lineTo(351.649, 242.376);
        ((GeneralPath) shape).lineTo(351.649, 241.944);
        ((GeneralPath) shape).lineTo(352.986, 241.944);
        ((GeneralPath) shape).lineTo(352.986, 240.584);
        ((GeneralPath) shape).lineTo(353.451, 240.584);
        ((GeneralPath) shape).lineTo(353.451, 241.944);
        ((GeneralPath) shape).lineTo(354.788, 241.944);
        ((GeneralPath) shape).lineTo(354.788, 242.376);
        ((GeneralPath) shape).lineTo(353.451, 242.376);
        ((GeneralPath) shape).lineTo(353.451, 243.729);
        ((GeneralPath) shape).lineTo(352.986, 243.729);
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
        ((GeneralPath) shape).moveTo(358.931, 243.995);
        ((GeneralPath) shape).lineTo(358.931, 244.427);
        ((GeneralPath) shape).lineTo(355.669, 244.427);
        ((GeneralPath) shape).lineTo(355.669, 243.573);
        ((GeneralPath) shape).curveTo(355.669, 243.094, 355.762, 242.78099,
                355.948, 242.632);
        ((GeneralPath) shape).curveTo(356.134, 242.48401, 356.575, 242.369,
                357.271, 242.289);
        ((GeneralPath) shape).curveTo(357.829, 242.227, 358.166, 242.143,
                358.28198, 242.037);
        ((GeneralPath) shape).curveTo(358.39798, 241.931, 358.456, 241.655,
                358.456, 241.209);
        ((GeneralPath) shape).curveTo(358.456, 240.81999, 358.392, 240.565,
                358.262, 240.449);
        ((GeneralPath) shape).curveTo(358.133, 240.33301, 357.85098, 240.27501,
                357.414, 240.27501);
        ((GeneralPath) shape).curveTo(356.87, 240.27501, 356.526, 240.32101,
                356.384, 240.41602);
        ((GeneralPath) shape).curveTo(356.241, 240.50902, 356.17, 240.73701,
                356.17, 241.09601);
        ((GeneralPath) shape).lineTo(356.177, 241.43501);
        ((GeneralPath) shape).lineTo(355.676, 241.43501);
        ((GeneralPath) shape).lineTo(355.679, 241.19902);
        ((GeneralPath) shape).curveTo(355.679, 240.65701, 355.793, 240.29501,
                356.02298, 240.11401);
        ((GeneralPath) shape).curveTo(356.25198, 239.93501, 356.71, 239.84302,
                357.39798, 239.84302);
        ((GeneralPath) shape).curveTo(358.00797, 239.84302, 358.41898,
                239.94002, 358.62897, 240.13602);
        ((GeneralPath) shape).curveTo(358.83798, 240.33002, 358.94296,
                240.71101, 358.94296, 241.27602);
        ((GeneralPath) shape).curveTo(358.94296, 241.81802, 358.84497,
                242.18202, 358.64996, 242.36502);
        ((GeneralPath) shape).curveTo(358.45395, 242.54802, 358.02896,
                242.67102, 357.37497, 242.73602);
        ((GeneralPath) shape).curveTo(356.80096, 242.79202, 356.45697,
                242.87202, 356.34598, 242.97502);
        ((GeneralPath) shape).curveTo(356.23697, 243.07703, 356.18, 243.36603,
                356.18, 243.84302);
        ((GeneralPath) shape).lineTo(356.18, 243.99602);
        ((GeneralPath) shape).lineTo(358.931, 243.99602);
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
        stroke = new BasicStroke(0.973f, 0, 0, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(360.111, 245.105);
        ((GeneralPath) shape).curveTo(360.111, 245.834, 359.51898, 246.428,
                358.787, 246.428);
        ((GeneralPath) shape).lineTo(352.056, 246.428);
        ((GeneralPath) shape).curveTo(351.325, 246.428, 350.731, 245.834,
                350.731, 245.105);
        ((GeneralPath) shape).lineTo(350.731, 238.303);
        ((GeneralPath) shape).curveTo(350.731, 237.57199, 351.32498, 236.97899,
                352.056, 236.97899);
        ((GeneralPath) shape).lineTo(358.787, 236.97899);
        ((GeneralPath) shape).curveTo(359.51898, 236.97899, 360.111, 237.57199,
                360.111, 238.303);
        ((GeneralPath) shape).lineTo(360.111, 245.105);
        ((GeneralPath) shape).closePath();
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
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(364.746, 243.729);
        ((GeneralPath) shape).lineTo(364.746, 242.376);
        ((GeneralPath) shape).lineTo(363.409, 242.376);
        ((GeneralPath) shape).lineTo(363.409, 241.944);
        ((GeneralPath) shape).lineTo(364.746, 241.944);
        ((GeneralPath) shape).lineTo(364.746, 240.584);
        ((GeneralPath) shape).lineTo(365.211, 240.584);
        ((GeneralPath) shape).lineTo(365.211, 241.944);
        ((GeneralPath) shape).lineTo(366.548, 241.944);
        ((GeneralPath) shape).lineTo(366.548, 242.376);
        ((GeneralPath) shape).lineTo(365.211, 242.376);
        ((GeneralPath) shape).lineTo(365.211, 243.729);
        ((GeneralPath) shape).lineTo(364.746, 243.729);
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
        ((GeneralPath) shape).moveTo(368.72, 242.323);
        ((GeneralPath) shape).lineTo(368.72, 241.887);
        ((GeneralPath) shape).lineTo(368.893, 241.89);
        ((GeneralPath) shape).curveTo(369.503, 241.89, 369.876, 241.852,
                370.012, 241.774);
        ((GeneralPath) shape).curveTo(370.149, 241.697, 370.216, 241.485,
                370.216, 241.139);
        ((GeneralPath) shape).curveTo(370.216, 240.74, 370.158, 240.49701,
                370.039, 240.408);
        ((GeneralPath) shape).curveTo(369.921, 240.32, 369.594, 240.27501,
                369.058, 240.27501);
        ((GeneralPath) shape).curveTo(368.566, 240.27501, 368.26, 240.31801,
                368.144, 240.404);
        ((GeneralPath) shape).curveTo(368.02902, 240.49, 367.97, 240.71701,
                367.97, 241.085);
        ((GeneralPath) shape).lineTo(367.97, 241.281);
        ((GeneralPath) shape).lineTo(367.486, 241.281);
        ((GeneralPath) shape).lineTo(367.48898, 241.102);
        ((GeneralPath) shape).curveTo(367.48898, 240.58301, 367.589, 240.244,
                367.792, 240.08301);
        ((GeneralPath) shape).curveTo(367.992, 239.923, 368.417, 239.84201,
                369.065, 239.84201);
        ((GeneralPath) shape).curveTo(369.73, 239.84201, 370.17, 239.917,
                370.384, 240.06702);
        ((GeneralPath) shape).curveTo(370.596, 240.21602, 370.704, 240.52402,
                370.704, 240.99301);
        ((GeneralPath) shape).curveTo(370.704, 241.389, 370.65402, 241.66101,
                370.551, 241.807);
        ((GeneralPath) shape).curveTo(370.44998, 241.953, 370.246, 242.04501,
                369.941, 242.08301);
        ((GeneralPath) shape).lineTo(369.941, 242.106);
        ((GeneralPath) shape).curveTo(370.28302, 242.15201, 370.51, 242.246,
                370.62402, 242.38501);
        ((GeneralPath) shape).curveTo(370.734, 242.52501, 370.79102, 242.78401,
                370.79102, 243.16301);
        ((GeneralPath) shape).curveTo(370.79102, 243.69, 370.68402, 244.04001,
                370.46402, 244.21301);
        ((GeneralPath) shape).curveTo(370.247, 244.38602, 369.808, 244.47202,
                369.14603, 244.47202);
        ((GeneralPath) shape).curveTo(368.43204, 244.47202, 367.96402,
                244.39201, 367.743, 244.23302);
        ((GeneralPath) shape).curveTo(367.522, 244.07301, 367.411, 243.73602,
                367.411, 243.21901);
        ((GeneralPath) shape).lineTo(367.411, 242.949);
        ((GeneralPath) shape).lineTo(367.909, 242.949);
        ((GeneralPath) shape).lineTo(367.909, 243.212);
        ((GeneralPath) shape).curveTo(367.909, 243.598, 367.973, 243.832,
                368.104, 243.91501);
        ((GeneralPath) shape).curveTo(368.233, 243.998, 368.605, 244.04001,
                369.215, 244.04001);
        ((GeneralPath) shape).curveTo(369.688, 244.04001, 369.988, 243.98901,
                370.11398, 243.882);
        ((GeneralPath) shape).curveTo(370.24, 243.77701, 370.30298, 243.52501,
                370.30298, 243.126);
        ((GeneralPath) shape).curveTo(370.30298, 242.792, 370.24298, 242.574,
                370.123, 242.473);
        ((GeneralPath) shape).curveTo(370.004, 242.373, 369.745, 242.322,
                369.344, 242.322);
        ((GeneralPath) shape).lineTo(368.72, 242.322);
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
        stroke = new BasicStroke(0.973f, 0, 0, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(371.871, 245.105);
        ((GeneralPath) shape).curveTo(371.871, 245.834, 371.28, 246.428,
                370.547, 246.428);
        ((GeneralPath) shape).lineTo(363.817, 246.428);
        ((GeneralPath) shape).curveTo(363.085, 246.428, 362.491, 245.834,
                362.491, 245.105);
        ((GeneralPath) shape).lineTo(362.491, 238.305);
        ((GeneralPath) shape).curveTo(362.491, 237.573, 363.085, 236.98,
                363.817, 236.98);
        ((GeneralPath) shape).lineTo(370.547, 236.98);
        ((GeneralPath) shape).curveTo(371.28, 236.98, 371.871, 237.573,
                371.871, 238.305);
        ((GeneralPath) shape).lineTo(371.871, 245.105);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        ((GeneralPath) shape).moveTo(260.707, 229.343);
        ((GeneralPath) shape).lineTo(260.127, 229.343);
        ((GeneralPath) shape).curveTo(260.127, 228.925, 260.05902, 228.661,
                259.919, 228.547);
        ((GeneralPath) shape).curveTo(259.781, 228.435, 259.453, 228.37799,
                258.937, 228.37799);
        ((GeneralPath) shape).curveTo(258.32703, 228.37799, 257.933, 228.43098,
                257.754, 228.54);
        ((GeneralPath) shape).curveTo(257.577, 228.646, 257.488, 228.887,
                257.488, 229.26);
        ((GeneralPath) shape).curveTo(257.488, 229.678, 257.556, 229.93199,
                257.696, 230.023);
        ((GeneralPath) shape).curveTo(257.834, 230.114, 258.24802, 230.17499,
                258.936, 230.209);
        ((GeneralPath) shape).curveTo(259.742, 230.243, 260.254, 230.346,
                260.474, 230.517);
        ((GeneralPath) shape).curveTo(260.692, 230.686, 260.803, 231.066,
                260.803, 231.657);
        ((GeneralPath) shape).curveTo(260.803, 232.295, 260.678, 232.707,
                260.426, 232.896);
        ((GeneralPath) shape).curveTo(260.176, 233.082, 259.62, 233.177,
                258.759, 233.177);
        ((GeneralPath) shape).curveTo(258.014, 233.177, 257.517, 233.084,
                257.271, 232.894);
        ((GeneralPath) shape).curveTo(257.024, 232.706, 256.901, 232.326,
                256.901, 231.752);
        ((GeneralPath) shape).lineTo(256.897, 231.521);
        ((GeneralPath) shape).lineTo(257.478, 231.521);
        ((GeneralPath) shape).lineTo(257.478, 231.65);
        ((GeneralPath) shape).curveTo(257.478, 232.114, 257.548, 232.4, 257.69,
                232.515);
        ((GeneralPath) shape).curveTo(257.831, 232.627, 258.194, 232.684,
                258.779, 232.684);
        ((GeneralPath) shape).curveTo(259.448, 232.684, 259.86, 232.62901,
                260.01498, 232.515);
        ((GeneralPath) shape).curveTo(260.16898, 232.403, 260.24597, 232.102,
                260.24597, 231.613);
        ((GeneralPath) shape).curveTo(260.24597, 231.298, 260.19498, 231.087,
                260.08798, 230.98001);
        ((GeneralPath) shape).curveTo(259.98398, 230.876, 259.76398, 230.813,
                259.43, 230.792);
        ((GeneralPath) shape).lineTo(258.823, 230.76201);
        ((GeneralPath) shape).lineTo(258.247, 230.731);
        ((GeneralPath) shape).curveTo(257.371, 230.67, 256.931, 230.214,
                256.931, 229.363);
        ((GeneralPath) shape).curveTo(256.931, 228.77501, 257.058, 228.37901,
                257.314, 228.181);
        ((GeneralPath) shape).curveTo(257.568, 227.984, 258.078, 227.885,
                258.842, 227.885);
        ((GeneralPath) shape).curveTo(259.616, 227.885, 260.12003, 227.97699,
                260.355, 228.161);
        ((GeneralPath) shape).curveTo(260.58902, 228.342, 260.707, 228.737,
                260.707, 229.343);
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
        ((GeneralPath) shape).moveTo(264.002, 230.992);
        ((GeneralPath) shape).lineTo(263.99802, 230.821);
        ((GeneralPath) shape).curveTo(263.99802, 230.43, 263.93402, 230.174,
                263.80502, 230.055);
        ((GeneralPath) shape).curveTo(263.67603, 229.937, 263.40002, 229.879,
                262.971, 229.879);
        ((GeneralPath) shape).curveTo(262.54202, 229.879, 262.264, 229.94699,
                262.135, 230.086);
        ((GeneralPath) shape).curveTo(262.008, 230.22299, 261.944, 230.524,
                261.944, 230.992);
        ((GeneralPath) shape).lineTo(264.002, 230.992);
        ((GeneralPath) shape).moveTo(264.002, 232.02501);
        ((GeneralPath) shape).lineTo(264.544, 232.02501);
        ((GeneralPath) shape).lineTo(264.547, 232.158);
        ((GeneralPath) shape).curveTo(264.547, 232.53401, 264.434, 232.798,
                264.204, 232.95001);
        ((GeneralPath) shape).curveTo(263.976, 233.1, 263.575, 233.17601,
                263.0, 233.17601);
        ((GeneralPath) shape).curveTo(262.333, 233.17601, 261.895, 233.05402,
                261.686, 232.81001);
        ((GeneralPath) shape).curveTo(261.478, 232.56702, 261.37302, 232.05202,
                261.37302, 231.26901);
        ((GeneralPath) shape).curveTo(261.37302, 230.54501, 261.47702, 230.059,
                261.687, 229.81102);
        ((GeneralPath) shape).curveTo(261.89502, 229.56403, 262.307, 229.43802,
                262.919, 229.43802);
        ((GeneralPath) shape).curveTo(263.586, 229.43802, 264.023, 229.54402,
                264.23102, 229.76102);
        ((GeneralPath) shape).curveTo(264.43802, 229.97601, 264.54202,
                230.43002, 264.54202, 231.12102);
        ((GeneralPath) shape).lineTo(264.54202, 231.40602);
        ((GeneralPath) shape).lineTo(261.933, 231.40602);
        ((GeneralPath) shape).curveTo(261.933, 231.97702, 261.99402, 232.34203,
                262.117, 232.50002);
        ((GeneralPath) shape).curveTo(262.239, 232.65602, 262.525, 232.73601,
                262.976, 232.73601);
        ((GeneralPath) shape).curveTo(263.403, 232.73601, 263.68002, 232.70001,
                263.80902, 232.62401);
        ((GeneralPath) shape).curveTo(263.93604, 232.55, 264.00003, 232.389,
                264.00003, 232.14001);
        ((GeneralPath) shape).lineTo(264.00003, 232.02501);
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
        ((GeneralPath) shape).moveTo(265.379, 229.491);
        ((GeneralPath) shape).lineTo(265.891, 229.491);
        ((GeneralPath) shape).lineTo(265.87598, 229.985);
        ((GeneralPath) shape).lineTo(265.891, 229.996);
        ((GeneralPath) shape).curveTo(266.052, 229.62401, 266.45398, 229.437,
                267.097, 229.437);
        ((GeneralPath) shape).curveTo(267.615, 229.437, 267.965, 229.528,
                268.149, 229.70999);
        ((GeneralPath) shape).curveTo(268.331, 229.89299, 268.42398, 230.24199,
                268.42398, 230.75899);
        ((GeneralPath) shape).lineTo(268.42398, 233.122);
        ((GeneralPath) shape).lineTo(267.89297, 233.122);
        ((GeneralPath) shape).lineTo(267.89297, 230.668);
        ((GeneralPath) shape).curveTo(267.89297, 230.356, 267.83398, 230.147,
                267.71497, 230.039);
        ((GeneralPath) shape).curveTo(267.59695, 229.933, 267.36798, 229.878,
                267.02795, 229.878);
        ((GeneralPath) shape).curveTo(266.28296, 229.878, 265.90994, 230.23201,
                265.90994, 230.938);
        ((GeneralPath) shape).lineTo(265.90994, 233.123);
        ((GeneralPath) shape).lineTo(265.37894, 233.123);
        ((GeneralPath) shape).lineTo(265.37894, 229.491);
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
        ((GeneralPath) shape).moveTo(272.196, 230.441);
        ((GeneralPath) shape).lineTo(271.665, 230.441);
        ((GeneralPath) shape).curveTo(271.665, 230.189, 271.614, 230.03099,
                271.512, 229.97);
        ((GeneralPath) shape).curveTo(271.409, 229.909, 271.146, 229.879,
                270.721, 229.879);
        ((GeneralPath) shape).curveTo(270.32602, 229.879, 270.07602, 229.911,
                269.97, 229.976);
        ((GeneralPath) shape).curveTo(269.864, 230.041, 269.81, 230.194,
                269.81, 230.441);
        ((GeneralPath) shape).curveTo(269.81, 230.814, 269.98898, 231.00699,
                270.345, 231.026);
        ((GeneralPath) shape).lineTo(270.774, 231.049);
        ((GeneralPath) shape).lineTo(271.31598, 231.075);
        ((GeneralPath) shape).curveTo(271.972, 231.108, 272.30197, 231.451,
                272.30197, 232.109);
        ((GeneralPath) shape).curveTo(272.30197, 232.51599, 272.19397, 232.797,
                271.97598, 232.948);
        ((GeneralPath) shape).curveTo(271.75998, 233.09999, 271.36197, 233.176,
                270.78198, 233.176);
        ((GeneralPath) shape).curveTo(270.188, 233.176, 269.779, 233.10399,
                269.555, 232.95999);
        ((GeneralPath) shape).curveTo(269.331, 232.81499, 269.219, 232.55199,
                269.219, 232.16599);
        ((GeneralPath) shape).lineTo(269.223, 231.96799);
        ((GeneralPath) shape).lineTo(269.77298, 231.96799);
        ((GeneralPath) shape).lineTo(269.77698, 232.13899);
        ((GeneralPath) shape).curveTo(269.77698, 232.377, 269.83798, 232.536,
                269.95898, 232.616);
        ((GeneralPath) shape).curveTo(270.08, 232.696, 270.31897, 232.736,
                270.675, 232.736);
        ((GeneralPath) shape).curveTo(271.112, 232.736, 271.4, 232.69398,
                271.538, 232.611);
        ((GeneralPath) shape).curveTo(271.675, 232.528, 271.745, 232.353,
                271.745, 232.08699);
        ((GeneralPath) shape).curveTo(271.745, 231.70499, 271.572, 231.51299,
                271.225, 231.51299);
        ((GeneralPath) shape).curveTo(270.419, 231.51299, 269.888, 231.44499,
                269.634, 231.30798);
        ((GeneralPath) shape).curveTo(269.38, 231.17099, 269.253, 230.88898,
                269.253, 230.45699);
        ((GeneralPath) shape).curveTo(269.253, 230.04999, 269.354, 229.77899,
                269.555, 229.64198);
        ((GeneralPath) shape).curveTo(269.75598, 229.50699, 270.156, 229.43898,
                270.755, 229.43898);
        ((GeneralPath) shape).curveTo(271.71402, 229.43898, 272.196, 229.72798,
                272.196, 230.30898);
        ((GeneralPath) shape).lineTo(272.196, 230.44098);
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
        ((GeneralPath) shape).moveTo(274.565, 229.879);
        ((GeneralPath) shape).curveTo(274.094, 229.879, 273.805, 229.955,
                273.691, 230.108);
        ((GeneralPath) shape).curveTo(273.579, 230.26, 273.522, 230.661,
                273.522, 231.307);
        ((GeneralPath) shape).curveTo(273.522, 231.953, 273.577, 232.352,
                273.691, 232.50601);
        ((GeneralPath) shape).curveTo(273.802, 232.658, 274.09402, 232.73601,
                274.565, 232.73601);
        ((GeneralPath) shape).curveTo(275.037, 232.73601, 275.329, 232.66,
                275.44202, 232.50601);
        ((GeneralPath) shape).curveTo(275.55402, 232.35402, 275.61102,
                231.95302, 275.61102, 231.307);
        ((GeneralPath) shape).curveTo(275.61102, 230.66101, 275.55603,
                230.26201, 275.44202, 230.108);
        ((GeneralPath) shape).curveTo(275.33102, 229.957, 275.039, 229.879,
                274.565, 229.879);
        ((GeneralPath) shape).moveTo(274.565, 229.438);
        ((GeneralPath) shape).curveTo(275.234, 229.438, 275.669, 229.554,
                275.869, 229.78801);
        ((GeneralPath) shape).curveTo(276.068, 230.01901, 276.16898, 230.52701,
                276.16898, 231.30801);
        ((GeneralPath) shape).curveTo(276.16898, 232.08702, 276.06998,
                232.59401, 275.869, 232.82802);
        ((GeneralPath) shape).curveTo(275.66998, 233.05902, 275.236, 233.17702,
                274.565, 233.17702);
        ((GeneralPath) shape).curveTo(273.898, 233.17702, 273.465, 233.06102,
                273.264, 232.82802);
        ((GeneralPath) shape).curveTo(273.065, 232.59602, 272.965, 232.08902,
                272.965, 231.30801);
        ((GeneralPath) shape).curveTo(272.965, 230.529, 273.064, 230.02202,
                273.264, 229.78801);
        ((GeneralPath) shape).curveTo(273.46402, 229.55602, 273.898, 229.438,
                274.565, 229.438);
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
        ((GeneralPath) shape).moveTo(277.05, 229.491);
        ((GeneralPath) shape).lineTo(277.581, 229.491);
        ((GeneralPath) shape).lineTo(277.52798, 229.909);
        ((GeneralPath) shape).lineTo(277.53897, 229.92099);
        ((GeneralPath) shape).curveTo(277.74698, 229.579, 278.09497, 229.40799,
                278.57797, 229.40799);
        ((GeneralPath) shape).curveTo(279.24496, 229.40799, 279.57898,
                229.75198, 279.57898, 230.441);
        ((GeneralPath) shape).lineTo(279.576, 230.692);
        ((GeneralPath) shape).lineTo(279.052, 230.692);
        ((GeneralPath) shape).lineTo(279.064, 230.601);
        ((GeneralPath) shape).curveTo(279.072, 230.506, 279.076, 230.441,
                279.076, 230.407);
        ((GeneralPath) shape).curveTo(279.076, 230.034, 278.875, 229.84799,
                278.469, 229.84799);
        ((GeneralPath) shape).curveTo(277.878, 229.84799, 277.582, 230.213,
                277.582, 230.946);
        ((GeneralPath) shape).lineTo(277.582, 233.123);
        ((GeneralPath) shape).lineTo(277.051, 233.123);
        ((GeneralPath) shape).lineTo(277.051, 229.491);
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
        ((GeneralPath) shape).moveTo(286.25, 227.937);
        ((GeneralPath) shape).lineTo(286.25, 233.123);
        ((GeneralPath) shape).lineTo(285.666, 233.123);
        ((GeneralPath) shape).lineTo(285.666, 230.718);
        ((GeneralPath) shape).lineTo(282.64, 230.718);
        ((GeneralPath) shape).lineTo(282.64, 233.123);
        ((GeneralPath) shape).lineTo(282.056, 233.123);
        ((GeneralPath) shape).lineTo(282.056, 227.937);
        ((GeneralPath) shape).lineTo(282.64, 227.937);
        ((GeneralPath) shape).lineTo(282.64, 230.224);
        ((GeneralPath) shape).lineTo(285.666, 230.224);
        ((GeneralPath) shape).lineTo(285.666, 227.937);
        ((GeneralPath) shape).lineTo(286.25, 227.937);
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
        ((GeneralPath) shape).moveTo(287.732, 229.491);
        ((GeneralPath) shape).lineTo(287.732, 233.123);
        ((GeneralPath) shape).lineTo(287.201, 233.123);
        ((GeneralPath) shape).lineTo(287.201, 229.491);
        ((GeneralPath) shape).lineTo(287.732, 229.491);
        ((GeneralPath) shape).moveTo(287.732, 227.937);
        ((GeneralPath) shape).lineTo(287.732, 228.534);
        ((GeneralPath) shape).lineTo(287.201, 228.534);
        ((GeneralPath) shape).lineTo(287.201, 227.937);
        ((GeneralPath) shape).lineTo(287.732, 227.937);
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
        ((GeneralPath) shape).moveTo(290.583, 229.491);
        ((GeneralPath) shape).lineTo(290.583, 229.931);
        ((GeneralPath) shape).lineTo(289.18802, 229.931);
        ((GeneralPath) shape).lineTo(289.18802, 232.154);
        ((GeneralPath) shape).curveTo(289.18802, 232.542, 289.35803, 232.73601,
                289.704, 232.73601);
        ((GeneralPath) shape).curveTo(290.045, 232.73601, 290.216, 232.563,
                290.216, 232.21501);
        ((GeneralPath) shape).lineTo(290.22, 232.03601);
        ((GeneralPath) shape).lineTo(290.227, 231.835);
        ((GeneralPath) shape).lineTo(290.72, 231.835);
        ((GeneralPath) shape).lineTo(290.724, 232.10501);
        ((GeneralPath) shape).curveTo(290.724, 232.81902, 290.387, 233.17601,
                289.707, 233.17601);
        ((GeneralPath) shape).curveTo(289.008, 233.17601, 288.657, 232.88,
                288.657, 232.283);
        ((GeneralPath) shape).lineTo(288.657, 229.931);
        ((GeneralPath) shape).lineTo(288.157, 229.931);
        ((GeneralPath) shape).lineTo(288.157, 229.491);
        ((GeneralPath) shape).lineTo(288.657, 229.491);
        ((GeneralPath) shape).lineTo(288.657, 228.617);
        ((GeneralPath) shape).lineTo(289.18802, 228.617);
        ((GeneralPath) shape).lineTo(289.18802, 229.491);
        ((GeneralPath) shape).lineTo(290.583, 229.491);
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
        ((GeneralPath) shape).moveTo(294.003, 230.441);
        ((GeneralPath) shape).lineTo(293.472, 230.441);
        ((GeneralPath) shape).curveTo(293.472, 230.189, 293.421, 230.03099,
                293.31897, 229.97);
        ((GeneralPath) shape).curveTo(293.21597, 229.909, 292.95297, 229.879,
                292.52798, 229.879);
        ((GeneralPath) shape).curveTo(292.133, 229.879, 291.883, 229.911,
                291.77698, 229.976);
        ((GeneralPath) shape).curveTo(291.671, 230.041, 291.61697, 230.194,
                291.61697, 230.441);
        ((GeneralPath) shape).curveTo(291.61697, 230.814, 291.79596, 231.00699,
                292.15198, 231.026);
        ((GeneralPath) shape).lineTo(292.58096, 231.049);
        ((GeneralPath) shape).lineTo(293.12296, 231.075);
        ((GeneralPath) shape).curveTo(293.77896, 231.108, 294.10895, 231.451,
                294.10895, 232.109);
        ((GeneralPath) shape).curveTo(294.10895, 232.51599, 294.00095, 232.797,
                293.78296, 232.948);
        ((GeneralPath) shape).curveTo(293.56696, 233.09999, 293.16895, 233.176,
                292.58896, 233.176);
        ((GeneralPath) shape).curveTo(291.99496, 233.176, 291.58597, 233.10399,
                291.36197, 232.95999);
        ((GeneralPath) shape).curveTo(291.13797, 232.81499, 291.02597,
                232.55199, 291.02597, 232.16599);
        ((GeneralPath) shape).lineTo(291.02997, 231.96799);
        ((GeneralPath) shape).lineTo(291.57996, 231.96799);
        ((GeneralPath) shape).lineTo(291.58395, 232.13899);
        ((GeneralPath) shape).curveTo(291.58395, 232.377, 291.64496, 232.536,
                291.76596, 232.616);
        ((GeneralPath) shape).curveTo(291.88696, 232.696, 292.12595, 232.736,
                292.48196, 232.736);
        ((GeneralPath) shape).curveTo(292.91898, 232.736, 293.20697, 232.69398,
                293.34497, 232.611);
        ((GeneralPath) shape).curveTo(293.48196, 232.528, 293.55197, 232.353,
                293.55197, 232.08699);
        ((GeneralPath) shape).curveTo(293.55197, 231.70499, 293.37897,
                231.51299, 293.03198, 231.51299);
        ((GeneralPath) shape).curveTo(292.22598, 231.51299, 291.69498,
                231.44499, 291.44098, 231.30798);
        ((GeneralPath) shape).curveTo(291.18698, 231.17099, 291.05997,
                230.88898, 291.05997, 230.45699);
        ((GeneralPath) shape).curveTo(291.05997, 230.04999, 291.16098,
                229.77899, 291.36197, 229.64198);
        ((GeneralPath) shape).curveTo(291.56296, 229.50699, 291.96298,
                229.43898, 292.56198, 229.43898);
        ((GeneralPath) shape).curveTo(293.521, 229.43898, 294.003, 229.72798,
                294.003, 230.30898);
        ((GeneralPath) shape).lineTo(294.003, 230.44098);
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
        ((GeneralPath) shape).moveTo(341.225, 232.311);
        ((GeneralPath) shape).lineTo(341.225, 230.958);
        ((GeneralPath) shape).lineTo(339.888, 230.958);
        ((GeneralPath) shape).lineTo(339.888, 230.526);
        ((GeneralPath) shape).lineTo(341.225, 230.526);
        ((GeneralPath) shape).lineTo(341.225, 229.167);
        ((GeneralPath) shape).lineTo(341.688, 229.167);
        ((GeneralPath) shape).lineTo(341.688, 230.526);
        ((GeneralPath) shape).lineTo(343.026, 230.526);
        ((GeneralPath) shape).lineTo(343.026, 230.958);
        ((GeneralPath) shape).lineTo(341.688, 230.958);
        ((GeneralPath) shape).lineTo(341.688, 232.311);
        ((GeneralPath) shape).lineTo(341.225, 232.311);
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
        ((GeneralPath) shape).moveTo(346.227, 228.471);
        ((GeneralPath) shape).lineTo(346.227, 233.009);
        ((GeneralPath) shape).lineTo(345.715, 233.009);
        ((GeneralPath) shape).lineTo(345.715, 228.854);
        ((GeneralPath) shape).lineTo(344.557, 230.14);
        ((GeneralPath) shape).lineTo(344.235, 229.834);
        ((GeneralPath) shape).lineTo(345.489, 228.471);
        ((GeneralPath) shape).lineTo(346.227, 228.471);
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
        stroke = new BasicStroke(0.973f, 0, 0, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(348.351, 233.687);
        ((GeneralPath) shape).curveTo(348.351, 234.416, 347.759, 235.009,
                347.026, 235.009);
        ((GeneralPath) shape).lineTo(340.296, 235.009);
        ((GeneralPath) shape).curveTo(339.56598, 235.009, 338.97098, 234.416,
                338.97098, 233.687);
        ((GeneralPath) shape).lineTo(338.97098, 226.885);
        ((GeneralPath) shape).curveTo(338.97098, 226.15399, 339.56598,
                225.56099, 340.296, 225.56099);
        ((GeneralPath) shape).lineTo(347.026, 225.56099);
        ((GeneralPath) shape).curveTo(347.759, 225.56099, 348.351, 226.15399,
                348.351, 226.885);
        ((GeneralPath) shape).lineTo(348.351, 233.687);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        ((GeneralPath) shape).moveTo(352.986, 232.311);
        ((GeneralPath) shape).lineTo(352.986, 230.958);
        ((GeneralPath) shape).lineTo(351.649, 230.958);
        ((GeneralPath) shape).lineTo(351.649, 230.526);
        ((GeneralPath) shape).lineTo(352.986, 230.526);
        ((GeneralPath) shape).lineTo(352.986, 229.167);
        ((GeneralPath) shape).lineTo(353.451, 229.167);
        ((GeneralPath) shape).lineTo(353.451, 230.526);
        ((GeneralPath) shape).lineTo(354.788, 230.526);
        ((GeneralPath) shape).lineTo(354.788, 230.958);
        ((GeneralPath) shape).lineTo(353.451, 230.958);
        ((GeneralPath) shape).lineTo(353.451, 232.311);
        ((GeneralPath) shape).lineTo(352.986, 232.311);
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
        ((GeneralPath) shape).moveTo(358.931, 232.577);
        ((GeneralPath) shape).lineTo(358.931, 233.009);
        ((GeneralPath) shape).lineTo(355.669, 233.009);
        ((GeneralPath) shape).lineTo(355.669, 232.155);
        ((GeneralPath) shape).curveTo(355.669, 231.676, 355.762, 231.364,
                355.948, 231.214);
        ((GeneralPath) shape).curveTo(356.134, 231.067, 356.575, 230.95201,
                357.271, 230.87201);
        ((GeneralPath) shape).curveTo(357.829, 230.81001, 358.166, 230.72601,
                358.28198, 230.62001);
        ((GeneralPath) shape).curveTo(358.39798, 230.514, 358.456, 230.238,
                358.456, 229.792);
        ((GeneralPath) shape).curveTo(358.456, 229.403, 358.392, 229.149,
                358.262, 229.033);
        ((GeneralPath) shape).curveTo(358.133, 228.916, 357.85098, 228.85901,
                357.414, 228.85901);
        ((GeneralPath) shape).curveTo(356.87, 228.85901, 356.526, 228.90501,
                356.384, 229.00002);
        ((GeneralPath) shape).curveTo(356.241, 229.09302, 356.17, 229.32101,
                356.17, 229.68001);
        ((GeneralPath) shape).lineTo(356.177, 230.01901);
        ((GeneralPath) shape).lineTo(355.676, 230.01901);
        ((GeneralPath) shape).lineTo(355.679, 229.78302);
        ((GeneralPath) shape).curveTo(355.679, 229.24101, 355.793, 228.87901,
                356.02298, 228.69801);
        ((GeneralPath) shape).curveTo(356.25198, 228.51901, 356.71, 228.42702,
                357.39798, 228.42702);
        ((GeneralPath) shape).curveTo(358.00797, 228.42702, 358.41898,
                228.52402, 358.62897, 228.72002);
        ((GeneralPath) shape).curveTo(358.83798, 228.91402, 358.94296,
                229.29501, 358.94296, 229.86002);
        ((GeneralPath) shape).curveTo(358.94296, 230.40202, 358.84497,
                230.76602, 358.64996, 230.94801);
        ((GeneralPath) shape).curveTo(358.45395, 231.13101, 358.02896,
                231.25401, 357.37497, 231.31902);
        ((GeneralPath) shape).curveTo(356.80096, 231.37502, 356.45697,
                231.45502, 356.34598, 231.55801);
        ((GeneralPath) shape).curveTo(356.23697, 231.66002, 356.18, 231.94902,
                356.18, 232.42601);
        ((GeneralPath) shape).lineTo(356.18, 232.57901);
        ((GeneralPath) shape).lineTo(358.931, 232.57901);
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
        stroke = new BasicStroke(0.973f, 0, 0, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(360.111, 233.687);
        ((GeneralPath) shape).curveTo(360.111, 234.416, 359.51898, 235.009,
                358.787, 235.009);
        ((GeneralPath) shape).lineTo(352.056, 235.009);
        ((GeneralPath) shape).curveTo(351.325, 235.009, 350.731, 234.416,
                350.731, 233.687);
        ((GeneralPath) shape).lineTo(350.731, 226.885);
        ((GeneralPath) shape).curveTo(350.731, 226.15399, 351.32498, 225.56099,
                352.056, 225.56099);
        ((GeneralPath) shape).lineTo(358.787, 225.56099);
        ((GeneralPath) shape).curveTo(359.51898, 225.56099, 360.111, 226.15399,
                360.111, 226.885);
        ((GeneralPath) shape).lineTo(360.111, 233.687);
        ((GeneralPath) shape).closePath();
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(364.746, 232.311);
        ((GeneralPath) shape).lineTo(364.746, 230.958);
        ((GeneralPath) shape).lineTo(363.409, 230.958);
        ((GeneralPath) shape).lineTo(363.409, 230.526);
        ((GeneralPath) shape).lineTo(364.746, 230.526);
        ((GeneralPath) shape).lineTo(364.746, 229.167);
        ((GeneralPath) shape).lineTo(365.211, 229.167);
        ((GeneralPath) shape).lineTo(365.211, 230.526);
        ((GeneralPath) shape).lineTo(366.548, 230.526);
        ((GeneralPath) shape).lineTo(366.548, 230.958);
        ((GeneralPath) shape).lineTo(365.211, 230.958);
        ((GeneralPath) shape).lineTo(365.211, 232.311);
        ((GeneralPath) shape).lineTo(364.746, 232.311);
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
        ((GeneralPath) shape).moveTo(368.72, 230.905);
        ((GeneralPath) shape).lineTo(368.72, 230.469);
        ((GeneralPath) shape).lineTo(368.893, 230.472);
        ((GeneralPath) shape).curveTo(369.503, 230.472, 369.876, 230.434,
                370.012, 230.356);
        ((GeneralPath) shape).curveTo(370.149, 230.279, 370.216, 230.06601,
                370.216, 229.72101);
        ((GeneralPath) shape).curveTo(370.216, 229.322, 370.158, 229.07901,
                370.039, 228.99);
        ((GeneralPath) shape).curveTo(369.921, 228.90201, 369.594, 228.85701,
                369.058, 228.85701);
        ((GeneralPath) shape).curveTo(368.566, 228.85701, 368.26, 228.90001,
                368.144, 228.98601);
        ((GeneralPath) shape).curveTo(368.02902, 229.072, 367.97, 229.29901,
                367.97, 229.667);
        ((GeneralPath) shape).lineTo(367.97, 229.863);
        ((GeneralPath) shape).lineTo(367.486, 229.863);
        ((GeneralPath) shape).lineTo(367.48898, 229.684);
        ((GeneralPath) shape).curveTo(367.48898, 229.16501, 367.589, 228.826,
                367.792, 228.66501);
        ((GeneralPath) shape).curveTo(367.992, 228.505, 368.417, 228.42401,
                369.065, 228.42401);
        ((GeneralPath) shape).curveTo(369.73, 228.42401, 370.17, 228.49901,
                370.384, 228.64902);
        ((GeneralPath) shape).curveTo(370.596, 228.79802, 370.704, 229.10602,
                370.704, 229.57501);
        ((GeneralPath) shape).curveTo(370.704, 229.97101, 370.65402, 230.24301,
                370.551, 230.389);
        ((GeneralPath) shape).curveTo(370.44998, 230.535, 370.246, 230.62701,
                369.941, 230.66501);
        ((GeneralPath) shape).lineTo(369.941, 230.688);
        ((GeneralPath) shape).curveTo(370.28302, 230.73401, 370.51, 230.828,
                370.62402, 230.96701);
        ((GeneralPath) shape).curveTo(370.734, 231.10701, 370.79102, 231.36601,
                370.79102, 231.74501);
        ((GeneralPath) shape).curveTo(370.79102, 232.272, 370.68402, 232.62201,
                370.46402, 232.79501);
        ((GeneralPath) shape).curveTo(370.247, 232.96802, 369.808, 233.05402,
                369.14603, 233.05402);
        ((GeneralPath) shape).curveTo(368.43204, 233.05402, 367.96402,
                232.97401, 367.743, 232.81502);
        ((GeneralPath) shape).curveTo(367.522, 232.65501, 367.411, 232.31802,
                367.411, 231.80101);
        ((GeneralPath) shape).lineTo(367.411, 231.531);
        ((GeneralPath) shape).lineTo(367.909, 231.531);
        ((GeneralPath) shape).lineTo(367.909, 231.794);
        ((GeneralPath) shape).curveTo(367.909, 232.18001, 367.973, 232.414,
                368.104, 232.49701);
        ((GeneralPath) shape).curveTo(368.233, 232.58, 368.605, 232.62201,
                369.215, 232.62201);
        ((GeneralPath) shape).curveTo(369.688, 232.62201, 369.988, 232.57101,
                370.11398, 232.464);
        ((GeneralPath) shape).curveTo(370.24, 232.35901, 370.30298, 232.10701,
                370.30298, 231.70801);
        ((GeneralPath) shape).curveTo(370.30298, 231.37401, 370.24298, 231.156,
                370.123, 231.05501);
        ((GeneralPath) shape).curveTo(370.004, 230.955, 369.745, 230.904,
                369.344, 230.904);
        ((GeneralPath) shape).lineTo(368.72, 230.904);
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
        stroke = new BasicStroke(0.973f, 0, 0, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(371.871, 233.687);
        ((GeneralPath) shape).curveTo(371.871, 234.416, 371.28, 235.009,
                370.547, 235.009);
        ((GeneralPath) shape).lineTo(363.817, 235.009);
        ((GeneralPath) shape).curveTo(363.085, 235.009, 362.491, 234.416,
                362.491, 233.687);
        ((GeneralPath) shape).lineTo(362.491, 226.885);
        ((GeneralPath) shape).curveTo(362.491, 226.15399, 363.085, 225.56099,
                363.817, 225.56099);
        ((GeneralPath) shape).lineTo(370.547, 225.56099);
        ((GeneralPath) shape).curveTo(371.28, 225.56099, 371.871, 226.15399,
                371.871, 226.885);
        ((GeneralPath) shape).lineTo(371.871, 233.687);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        ((GeneralPath) shape).moveTo(377.602, 232.577);
        ((GeneralPath) shape).lineTo(379.078, 232.577);
        ((GeneralPath) shape).curveTo(379.619, 232.577, 379.97, 232.469,
                380.127, 232.25099);
        ((GeneralPath) shape).curveTo(380.28302, 232.03499, 380.362, 231.549,
                380.362, 230.79799);
        ((GeneralPath) shape).curveTo(380.362, 229.97299, 380.295, 229.45099,
                380.158, 229.232);
        ((GeneralPath) shape).curveTo(380.022, 229.012, 379.697, 228.903,
                379.181, 228.903);
        ((GeneralPath) shape).lineTo(377.602, 228.903);
        ((GeneralPath) shape).lineTo(377.602, 232.577);
        ((GeneralPath) shape).moveTo(377.09, 233.009);
        ((GeneralPath) shape).lineTo(377.09, 228.47101);
        ((GeneralPath) shape).lineTo(379.188, 228.47101);
        ((GeneralPath) shape).curveTo(379.83398, 228.47101, 380.274, 228.61401,
                380.50497, 228.90001);
        ((GeneralPath) shape).curveTo(380.73398, 229.186, 380.84998, 229.733,
                380.84998, 230.542);
        ((GeneralPath) shape).curveTo(380.84998, 231.526, 380.748, 232.18301,
                380.54297, 232.513);
        ((GeneralPath) shape).curveTo(380.33896, 232.842, 379.93097, 233.009,
                379.31598, 233.009);
        ((GeneralPath) shape).lineTo(377.08997, 233.009);
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
        stroke = new BasicStroke(0.973f, 0, 0, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(383.633, 233.687);
        ((GeneralPath) shape).curveTo(383.633, 234.416, 383.041, 235.009,
                382.31, 235.009);
        ((GeneralPath) shape).lineTo(375.577, 235.009);
        ((GeneralPath) shape).curveTo(374.846, 235.009, 374.253, 234.416,
                374.253, 233.687);
        ((GeneralPath) shape).lineTo(374.253, 226.885);
        ((GeneralPath) shape).curveTo(374.253, 226.15399, 374.84598, 225.56099,
                375.577, 225.56099);
        ((GeneralPath) shape).lineTo(382.31, 225.56099);
        ((GeneralPath) shape).curveTo(383.041, 225.56099, 383.633, 226.15399,
                383.633, 226.885);
        ((GeneralPath) shape).lineTo(383.633, 233.687);
        ((GeneralPath) shape).closePath();
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(257.742, 270.935);
        ((GeneralPath) shape).lineTo(259.43, 270.935);
        ((GeneralPath) shape).curveTo(259.874, 270.935, 260.177, 270.869,
                260.34, 270.732);
        ((GeneralPath) shape).curveTo(260.501, 270.597, 260.583, 270.34198,
                260.583, 269.966);
        ((GeneralPath) shape).curveTo(260.583, 269.499, 260.52402, 269.193,
                260.403, 269.05002);
        ((GeneralPath) shape).curveTo(260.28302, 268.90903, 260.026, 268.838,
                259.63202, 268.838);
        ((GeneralPath) shape).lineTo(257.743, 268.838);
        ((GeneralPath) shape).lineTo(257.743, 270.935);
        ((GeneralPath) shape).moveTo(257.15802, 273.529);
        ((GeneralPath) shape).lineTo(257.15802, 268.343);
        ((GeneralPath) shape).lineTo(259.62302, 268.343);
        ((GeneralPath) shape).curveTo(260.17902, 268.343, 260.571, 268.45297,
                260.799, 268.67398);
        ((GeneralPath) shape).curveTo(261.027, 268.895, 261.14, 269.27597,
                261.14, 269.82098);
        ((GeneralPath) shape).curveTo(261.14, 270.3, 261.079, 270.632, 260.954,
                270.822);
        ((GeneralPath) shape).curveTo(260.83002, 271.00998, 260.59402, 271.132,
                260.245, 271.189);
        ((GeneralPath) shape).lineTo(260.245, 271.19998);
        ((GeneralPath) shape).curveTo(260.793, 271.24, 261.068, 271.57498,
                261.068, 272.20297);
        ((GeneralPath) shape).lineTo(261.068, 273.52896);
        ((GeneralPath) shape).lineTo(260.48398, 273.52896);
        ((GeneralPath) shape).lineTo(260.48398, 272.33597);
        ((GeneralPath) shape).curveTo(260.48398, 271.73196, 260.222, 271.42798,
                259.69897, 271.42798);
        ((GeneralPath) shape).lineTo(257.74197, 271.42798);
        ((GeneralPath) shape).lineTo(257.74197, 273.529);
        ((GeneralPath) shape).lineTo(257.15796, 273.529);
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
        ((GeneralPath) shape).moveTo(264.491, 271.397);
        ((GeneralPath) shape).lineTo(264.487, 271.227);
        ((GeneralPath) shape).curveTo(264.487, 270.836, 264.423, 270.57898,
                264.294, 270.461);
        ((GeneralPath) shape).curveTo(264.165, 270.343, 263.889, 270.284,
                263.46, 270.284);
        ((GeneralPath) shape).curveTo(263.031, 270.284, 262.753, 270.352,
                262.624, 270.491);
        ((GeneralPath) shape).curveTo(262.49698, 270.628, 262.43298, 270.93,
                262.43298, 271.397);
        ((GeneralPath) shape).lineTo(264.491, 271.397);
        ((GeneralPath) shape).moveTo(264.491, 272.432);
        ((GeneralPath) shape).lineTo(265.033, 272.432);
        ((GeneralPath) shape).lineTo(265.037, 272.565);
        ((GeneralPath) shape).curveTo(265.037, 272.941, 264.92297, 273.20502,
                264.694, 273.357);
        ((GeneralPath) shape).curveTo(264.466, 273.507, 264.065, 273.583,
                263.49, 273.583);
        ((GeneralPath) shape).curveTo(262.823, 273.583, 262.38498, 273.461,
                262.176, 273.216);
        ((GeneralPath) shape).curveTo(261.968, 272.973, 261.863, 272.458,
                261.863, 271.67502);
        ((GeneralPath) shape).curveTo(261.863, 270.95102, 261.967, 270.46503,
                262.177, 270.216);
        ((GeneralPath) shape).curveTo(262.38602, 269.969, 262.797, 269.84302,
                263.409, 269.84302);
        ((GeneralPath) shape).curveTo(264.076, 269.84302, 264.513, 269.949,
                264.721, 270.16602);
        ((GeneralPath) shape).curveTo(264.928, 270.381, 265.032, 270.83502,
                265.032, 271.526);
        ((GeneralPath) shape).lineTo(265.032, 271.811);
        ((GeneralPath) shape).lineTo(262.423, 271.811);
        ((GeneralPath) shape).curveTo(262.423, 272.383, 262.484, 272.74802,
                262.607, 272.905);
        ((GeneralPath) shape).curveTo(262.728, 273.06, 263.014, 273.13998,
                263.466, 273.13998);
        ((GeneralPath) shape).curveTo(263.892, 273.13998, 264.169, 273.10397,
                264.298, 273.02798);
        ((GeneralPath) shape).curveTo(264.42502, 272.95398, 264.489, 272.793,
                264.489, 272.54398);
        ((GeneralPath) shape).lineTo(264.489, 272.43198);
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
        ((GeneralPath) shape).moveTo(267.275, 271.732);
        ((GeneralPath) shape).curveTo(266.87, 271.732, 266.604, 271.774,
                266.481, 271.861);
        ((GeneralPath) shape).curveTo(266.36, 271.94598, 266.297, 272.13098,
                266.297, 272.416);
        ((GeneralPath) shape).curveTo(266.297, 272.70798, 266.358, 272.904,
                266.479, 272.999);
        ((GeneralPath) shape).curveTo(266.6, 273.094, 266.84702, 273.142,
                267.21802, 273.142);
        ((GeneralPath) shape).curveTo(267.96503, 273.142, 268.341, 272.914,
                268.341, 272.458);
        ((GeneralPath) shape).curveTo(268.341, 272.173, 268.269, 271.979,
                268.12302, 271.88);
        ((GeneralPath) shape).curveTo(267.979, 271.782, 267.696, 271.732,
                267.27502, 271.732);
        ((GeneralPath) shape).moveTo(266.42203, 270.916);
        ((GeneralPath) shape).lineTo(265.89502, 270.916);
        ((GeneralPath) shape).curveTo(265.89502, 270.494, 265.99002, 270.20898,
                266.18103, 270.063);
        ((GeneralPath) shape).curveTo(266.37003, 269.918, 266.74402, 269.844,
                267.30203, 269.844);
        ((GeneralPath) shape).curveTo(267.90503, 269.844, 268.31403, 269.934,
                268.52704, 270.112);
        ((GeneralPath) shape).curveTo(268.73904, 270.291, 268.84604, 270.631,
                268.84604, 271.132);
        ((GeneralPath) shape).lineTo(268.84604, 273.529);
        ((GeneralPath) shape).lineTo(268.31503, 273.529);
        ((GeneralPath) shape).lineTo(268.35703, 273.137);
        ((GeneralPath) shape).lineTo(268.34604, 273.134);
        ((GeneralPath) shape).curveTo(268.14505, 273.432, 267.73303, 273.582,
                267.11404, 273.582);
        ((GeneralPath) shape).curveTo(266.20004, 273.582, 265.74106, 273.213,
                265.74106, 272.476);
        ((GeneralPath) shape).curveTo(265.74106, 272.039, 265.84406, 271.73502,
                266.04807, 271.56802);
        ((GeneralPath) shape).curveTo(266.25305, 271.40103, 266.62308,
                271.31802, 267.15906, 271.31802);
        ((GeneralPath) shape).curveTo(267.79605, 271.31802, 268.17807,
                271.44302, 268.30505, 271.69403);
        ((GeneralPath) shape).lineTo(268.31604, 271.69003);
        ((GeneralPath) shape).lineTo(268.31604, 271.24902);
        ((GeneralPath) shape).curveTo(268.31604, 270.83502, 268.25903,
                270.56503, 268.14603, 270.44202);
        ((GeneralPath) shape).curveTo(268.032, 270.32, 267.78403, 270.25803,
                267.39902, 270.25803);
        ((GeneralPath) shape).curveTo(266.747, 270.25803, 266.42, 270.44003,
                266.42, 270.80902);
        ((GeneralPath) shape).curveTo(266.418, 270.82602, 266.418, 270.86203,
                266.42203, 270.91602);
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
        ((GeneralPath) shape).moveTo(269.882, 269.897);
        ((GeneralPath) shape).lineTo(270.413, 269.897);
        ((GeneralPath) shape).lineTo(270.36, 270.315);
        ((GeneralPath) shape).lineTo(270.37097, 270.327);
        ((GeneralPath) shape).curveTo(270.57898, 269.985, 270.92596, 269.814,
                271.40997, 269.814);
        ((GeneralPath) shape).curveTo(272.07697, 269.814, 272.41098, 270.158,
                272.41098, 270.848);
        ((GeneralPath) shape).lineTo(272.40698, 271.098);
        ((GeneralPath) shape).lineTo(271.88397, 271.098);
        ((GeneralPath) shape).lineTo(271.89597, 271.007);
        ((GeneralPath) shape).curveTo(271.90295, 270.912, 271.90695, 270.848,
                271.90695, 270.814);
        ((GeneralPath) shape).curveTo(271.90695, 270.441, 271.70596, 270.255,
                271.30096, 270.255);
        ((GeneralPath) shape).curveTo(270.70895, 270.255, 270.41397, 270.62,
                270.41397, 271.353);
        ((GeneralPath) shape).lineTo(270.41397, 273.53);
        ((GeneralPath) shape).lineTo(269.88297, 273.53);
        ((GeneralPath) shape).lineTo(269.88297, 269.897);
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
        stroke = new BasicStroke(0.973f, 0, 0, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(290.968, 274.66);
        ((GeneralPath) shape).curveTo(290.968, 275.389, 290.37598, 275.984,
                289.64297, 275.984);
        ((GeneralPath) shape).lineTo(282.91296, 275.984);
        ((GeneralPath) shape).curveTo(282.18097, 275.984, 281.58698, 275.39,
                281.58698, 274.66);
        ((GeneralPath) shape).lineTo(281.58698, 267.86002);
        ((GeneralPath) shape).curveTo(281.58698, 267.12802, 282.18097, 266.535,
                282.91296, 266.535);
        ((GeneralPath) shape).lineTo(289.64297, 266.535);
        ((GeneralPath) shape).curveTo(290.37598, 266.535, 290.968, 267.128,
                290.968, 267.86002);
        ((GeneralPath) shape).lineTo(290.968, 274.66);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        paint = new Color(199, 200, 202, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(550.61, 80.237);
        ((GeneralPath) shape).lineTo(540.885, 73.429);
        ((GeneralPath) shape).lineTo(531.158, 81.275);
        ((GeneralPath) shape).lineTo(447.205, 81.275);
        ((GeneralPath) shape).lineTo(437.479, 73.429);
        ((GeneralPath) shape).lineTo(427.753, 80.237);
        ((GeneralPath) shape).lineTo(418.027, 214.696);
        ((GeneralPath) shape).lineTo(418.027, 311.868);
        ((GeneralPath) shape).lineTo(427.753, 335.466);
        ((GeneralPath) shape).lineTo(438.951, 335.466);
        ((GeneralPath) shape).lineTo(438.951, 333.6);
        ((GeneralPath) shape).lineTo(478.986, 333.6);
        ((GeneralPath) shape).lineTo(478.986, 335.466);
        ((GeneralPath) shape).lineTo(499.878, 335.466);
        ((GeneralPath) shape).lineTo(499.878, 333.6);
        ((GeneralPath) shape).lineTo(539.914, 333.6);
        ((GeneralPath) shape).lineTo(539.914, 335.466);
        ((GeneralPath) shape).lineTo(550.61, 335.466);
        ((GeneralPath) shape).lineTo(560.336, 311.868);
        ((GeneralPath) shape).lineTo(560.336, 214.696);
        ((GeneralPath) shape).lineTo(550.61, 80.237);
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
        paint = new Color(199, 200, 202, 255);
        shape = new Rectangle2D.Double(485.50299072265625, 164.34500122070312,
                0.03099999949336052, 91.48100280761719);
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(547.693, 77.32);
        ((GeneralPath) shape).lineTo(537.967, 70.511);
        ((GeneralPath) shape).lineTo(528.241, 78.356);
        ((GeneralPath) shape).lineTo(444.288, 78.356);
        ((GeneralPath) shape).lineTo(434.563, 70.511);
        ((GeneralPath) shape).lineTo(424.836, 77.32);
        ((GeneralPath) shape).lineTo(415.11, 211.778);
        ((GeneralPath) shape).lineTo(415.11, 308.951);
        ((GeneralPath) shape).lineTo(424.836, 332.549);
        ((GeneralPath) shape).lineTo(436.033, 332.549);
        ((GeneralPath) shape).lineTo(436.033, 330.683);
        ((GeneralPath) shape).lineTo(476.068, 330.683);
        ((GeneralPath) shape).lineTo(476.068, 332.549);
        ((GeneralPath) shape).lineTo(496.96, 332.549);
        ((GeneralPath) shape).lineTo(496.96, 330.683);
        ((GeneralPath) shape).lineTo(536.995, 330.683);
        ((GeneralPath) shape).lineTo(536.995, 332.549);
        ((GeneralPath) shape).lineTo(547.693, 332.549);
        ((GeneralPath) shape).lineTo(557.419, 308.951);
        ((GeneralPath) shape).lineTo(557.419, 211.778);
        ((GeneralPath) shape).lineTo(547.693, 77.32);
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
        stroke = new BasicStroke(1.459f, 0, 0, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(528.241, 78.356);
        ((GeneralPath) shape).lineTo(444.288, 78.356);
        ((GeneralPath) shape).lineTo(434.563, 70.511);
        ((GeneralPath) shape).lineTo(424.836, 77.32);
        ((GeneralPath) shape).lineTo(415.11, 211.778);
        ((GeneralPath) shape).lineTo(415.11, 308.951);
        ((GeneralPath) shape).lineTo(424.836, 332.548);
        ((GeneralPath) shape).lineTo(436.034, 332.548);
        ((GeneralPath) shape).lineTo(436.034, 330.682);
        ((GeneralPath) shape).lineTo(476.068, 330.682);
        ((GeneralPath) shape).lineTo(476.068, 332.548);
        ((GeneralPath) shape).lineTo(496.961, 332.548);
        ((GeneralPath) shape).lineTo(496.961, 330.682);
        ((GeneralPath) shape).lineTo(536.996, 330.682);
        ((GeneralPath) shape).lineTo(536.996, 332.548);
        ((GeneralPath) shape).lineTo(547.693, 332.548);
        ((GeneralPath) shape).lineTo(557.419, 308.951);
        ((GeneralPath) shape).lineTo(557.419, 211.778);
        ((GeneralPath) shape).lineTo(547.693, 77.32);
        ((GeneralPath) shape).lineTo(537.967, 70.511);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        paint = new Color(199, 200, 202, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(454.201, 110.795);
        ((GeneralPath) shape).lineTo(448.164, 136.728);
        ((GeneralPath) shape).lineTo(435.521, 211.218);
        ((GeneralPath) shape).lineTo(447.192, 277.889);
        ((GeneralPath) shape).lineTo(457.422, 310.791);
        ((GeneralPath) shape).lineTo(515.15, 310.791);
        ((GeneralPath) shape).lineTo(525.336, 277.889);
        ((GeneralPath) shape).lineTo(537.007, 211.218);
        ((GeneralPath) shape).lineTo(525.336, 138.674);
        ((GeneralPath) shape).lineTo(518.328, 110.795);
        ((GeneralPath) shape).lineTo(454.201, 110.795);
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
        stroke = new BasicStroke(0.973f, 0, 0, 4.0f, null, 0.0f);
        shape = new Line2D.Float(415.652008f, 211.056000f, 557.328003f,
                211.056000f);
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_197;
        g.setTransform(defaultTransform__0_197);
        g.setClip(clip__0_197);
        float alpha__0_198 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_198 = g.getClip();
        AffineTransform defaultTransform__0_198 = g.getTransform();
        g.transform(new AffineTransform(0.1500059962272644f, 0.0f, 0.0f,
                -0.12777680158615112f, -103.7873306274414f, 853.2520751953125f));
        // _0_198 is CompositeGraphicsNode
        float alpha__0_198_0 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_198_0 = g.getClip();
        AffineTransform defaultTransform__0_198_0 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_198_0 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        stroke = new BasicStroke(7.88225f, 0, 0, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(3941.37, 4470.53);
        ((GeneralPath) shape).lineTo(3941.37, 5576.08);
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_198_0;
        g.setTransform(defaultTransform__0_198_0);
        g.setClip(clip__0_198_0);
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
        stroke = new BasicStroke(0.973f, 0, 0, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(557.328, 309.774);
        ((GeneralPath) shape).lineTo(523.448, 284.507);
        ((GeneralPath) shape).lineTo(449.25, 284.507);
        ((GeneralPath) shape).lineTo(415.49, 310.098);
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        stroke = new BasicStroke(0.973f, 0, 0, 4.0f, null, 0.0f);
        shape = new Line2D.Float(448.255005f, 136.604004f, 524.862976f,
                136.679993f);
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        stroke = new BasicStroke(0.973f, 1, 0, 4.0f, null, 0.0f);
        shape = new Line2D.Float(424.566986f, 77.485001f, 453.911011f,
                110.487999f);
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        stroke = new BasicStroke(0.973f, 1, 0, 4.0f, null, 0.0f);
        shape = new Line2D.Float(518.375000f, 110.487999f, 547.763977f,
                77.646004f);
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(522.104, 204.5);
        ((GeneralPath) shape).lineTo(520.727, 207.257);
        ((GeneralPath) shape).lineTo(451.801, 207.257);
        ((GeneralPath) shape).lineTo(450.424, 204.5);
        ((GeneralPath) shape).lineTo(461.451, 139.711);
        ((GeneralPath) shape).lineTo(462.832, 138.333);
        ((GeneralPath) shape).lineTo(472.479, 138.333);
        ((GeneralPath) shape).lineTo(472.479, 131.441);
        ((GeneralPath) shape).lineTo(484.884, 125.926);
        ((GeneralPath) shape).lineTo(484.884, 124.548);
        ((GeneralPath) shape).lineTo(486.264, 124.548);
        ((GeneralPath) shape).lineTo(487.643, 120.413);
        ((GeneralPath) shape).lineTo(489.021, 119.034);
        ((GeneralPath) shape).lineTo(487.643, 117.655);
        ((GeneralPath) shape).lineTo(489.021, 113.521);
        ((GeneralPath) shape).lineTo(494.535, 113.521);
        ((GeneralPath) shape).lineTo(495.914, 117.655);
        ((GeneralPath) shape).lineTo(494.535, 119.034);
        ((GeneralPath) shape).lineTo(495.914, 120.413);
        ((GeneralPath) shape).lineTo(497.293, 124.548);
        ((GeneralPath) shape).lineTo(498.67, 124.548);
        ((GeneralPath) shape).lineTo(498.672, 131.441);
        ((GeneralPath) shape).lineTo(500.05, 131.441);
        ((GeneralPath) shape).lineTo(500.05, 138.333);
        ((GeneralPath) shape).lineTo(509.697, 138.333);
        ((GeneralPath) shape).lineTo(511.077, 139.711);
        ((GeneralPath) shape).lineTo(522.104, 204.5);
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
        stroke = new BasicStroke(0.973f, 0, 0, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(435.521, 211.218);
        ((GeneralPath) shape).lineTo(447.19, 277.889);
        ((GeneralPath) shape).lineTo(457.421, 310.792);
        ((GeneralPath) shape).lineTo(515.15, 310.792);
        ((GeneralPath) shape).lineTo(525.336, 277.889);
        ((GeneralPath) shape).lineTo(537.007, 211.218);
        ((GeneralPath) shape).lineTo(525.336, 138.674);
        ((GeneralPath) shape).lineTo(518.327, 110.795);
        ((GeneralPath) shape).lineTo(454.201, 110.795);
        ((GeneralPath) shape).lineTo(448.164, 136.728);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        stroke = new BasicStroke(0.973f, 0, 0, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(451.801, 207.257);
        ((GeneralPath) shape).lineTo(450.425, 204.5);
        ((GeneralPath) shape).lineTo(461.453, 139.711);
        ((GeneralPath) shape).lineTo(462.832, 138.333);
        ((GeneralPath) shape).lineTo(472.479, 138.333);
        ((GeneralPath) shape).lineTo(472.479, 131.441);
        ((GeneralPath) shape).lineTo(484.884, 125.926);
        ((GeneralPath) shape).lineTo(484.884, 124.547);
        ((GeneralPath) shape).lineTo(486.264, 124.547);
        ((GeneralPath) shape).lineTo(487.644, 120.413);
        ((GeneralPath) shape).lineTo(489.021, 119.034);
        ((GeneralPath) shape).lineTo(487.644, 117.654);
        ((GeneralPath) shape).lineTo(489.021, 113.52);
        ((GeneralPath) shape).lineTo(494.535, 113.52);
        ((GeneralPath) shape).lineTo(495.914, 117.654);
        ((GeneralPath) shape).lineTo(494.535, 119.034);
        ((GeneralPath) shape).lineTo(495.914, 120.413);
        ((GeneralPath) shape).lineTo(497.293, 124.547);
        ((GeneralPath) shape).lineTo(498.671, 124.547);
        ((GeneralPath) shape).lineTo(498.672, 131.441);
        ((GeneralPath) shape).lineTo(500.05, 131.441);
        ((GeneralPath) shape).lineTo(500.05, 138.333);
        ((GeneralPath) shape).lineTo(509.697, 138.333);
        ((GeneralPath) shape).lineTo(511.077, 139.711);
        ((GeneralPath) shape).lineTo(522.104, 204.5);
        ((GeneralPath) shape).lineTo(520.727, 207.257);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        paint = new Color(255, 255, 255, 255);
        shape = new Rectangle2D.Double(484.885009765625, 124.54900360107422,
                4.135000228881836, 6.892000198364258);
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
        stroke = new BasicStroke(0.973f, 0, 0, 4.0f, null, 0.0f);
        shape = new Rectangle2D.Double(484.8840026855469, 124.54900360107422,
                4.136000156402588, 6.892000198364258);
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        paint = new Color(255, 255, 255, 255);
        shape = new Rectangle2D.Double(494.53399658203125, 124.54900360107422,
                4.135000228881836, 6.892000198364258);
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
        stroke = new BasicStroke(0.973f, 0, 0, 4.0f, null, 0.0f);
        shape = new Rectangle2D.Double(494.5329895019531, 124.54900360107422,
                4.136000156402588, 6.892000198364258);
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        paint = new Color(255, 255, 255, 255);
        shape = new Rectangle2D.Double(489.7090148925781, 124.54900360107422,
                4.13700008392334, 6.892000198364258);
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
        stroke = new BasicStroke(0.973f, 0, 0, 4.0f, null, 0.0f);
        shape = new Rectangle2D.Double(489.7080078125, 124.54900360107422,
                4.136000156402588, 6.892000198364258);
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(489.021, 119.034);
        ((GeneralPath) shape).lineTo(494.534, 119.034);
        ((GeneralPath) shape).lineTo(489.021, 119.034);
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
        stroke = new BasicStroke(0.973f, 0, 0, 4.0f, null, 0.0f);
        shape = new Line2D.Float(489.020996f, 119.033997f, 494.535004f,
                119.033997f);
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(472.479, 131.441);
        ((GeneralPath) shape).lineTo(484.885, 131.441);
        ((GeneralPath) shape).lineTo(472.479, 131.441);
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
        stroke = new BasicStroke(0.973f, 0, 0, 4.0f, null, 0.0f);
        shape = new Line2D.Float(472.480011f, 131.440994f, 484.885010f,
                131.440994f);
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        paint = new Color(255, 255, 255, 255);
        shape = new Rectangle2D.Double(496.4309997558594, 132.82000732421875,
                2.242000102996826, 5.513999938964844);
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
        stroke = new BasicStroke(0.973f, 0, 0, 4.0f, null, 0.0f);
        shape = new Rectangle2D.Double(496.4309997558594, 132.8179931640625,
                2.242000102996826, 5.513999938964844);
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        paint = new Color(255, 255, 255, 255);
        shape = new Rectangle2D.Double(480.7489929199219, 132.82000732421875,
                2.240999937057495, 5.513999938964844);
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
        stroke = new BasicStroke(0.973f, 0, 0, 4.0f, null, 0.0f);
        shape = new Rectangle2D.Double(480.7489929199219, 132.8179931640625,
                2.240999937057495, 5.513999938964844);
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        paint = new Color(255, 255, 255, 255);
        shape = new Rectangle2D.Double(494.18798828125, 132.82000732421875,
                2.242000102996826, 5.513999938964844);
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
        stroke = new BasicStroke(0.973f, 0, 0, 4.0f, null, 0.0f);
        shape = new Rectangle2D.Double(494.18798828125, 132.8179931640625,
                2.242000102996826, 5.513999938964844);
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        paint = new Color(255, 255, 255, 255);
        shape = new Rectangle2D.Double(491.95098876953125, 132.82000732421875,
                2.240000009536743, 5.513999938964844);
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
        stroke = new BasicStroke(0.973f, 0, 0, 4.0f, null, 0.0f);
        shape = new Rectangle2D.Double(491.95098876953125, 132.8179931640625,
                2.239000082015991, 5.513999938964844);
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        paint = new Color(255, 255, 255, 255);
        shape = new Rectangle2D.Double(489.7090148925781, 132.82000732421875,
                2.242000102996826, 5.513999938964844);
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
        stroke = new BasicStroke(0.973f, 0, 0, 4.0f, null, 0.0f);
        shape = new Rectangle2D.Double(489.7090148925781, 132.8179931640625,
                2.242000102996826, 5.513999938964844);
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        paint = new Color(255, 255, 255, 255);
        shape = new Rectangle2D.Double(487.468994140625, 132.82000732421875,
                2.243000030517578, 5.513999938964844);
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
        stroke = new BasicStroke(0.973f, 0, 0, 4.0f, null, 0.0f);
        shape = new Rectangle2D.Double(487.4700012207031, 132.8179931640625,
                2.242000102996826, 5.513999938964844);
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        paint = new Color(255, 255, 255, 255);
        shape = new Rectangle2D.Double(485.22900390625, 132.82000732421875,
                2.240999937057495, 5.513999938964844);
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
        stroke = new BasicStroke(0.973f, 0, 0, 4.0f, null, 0.0f);
        shape = new Rectangle2D.Double(485.22900390625, 132.8179931640625,
                2.242000102996826, 5.513999938964844);
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        paint = new Color(255, 255, 255, 255);
        shape = new Rectangle2D.Double(482.9909973144531, 132.82000732421875,
                2.239000082015991, 5.513999938964844);
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
        stroke = new BasicStroke(0.973f, 0, 0, 4.0f, null, 0.0f);
        shape = new Rectangle2D.Double(482.989990234375, 132.8179931640625,
                2.240000009536743, 5.513999938964844);
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        paint = new Color(255, 255, 255, 255);
        shape = new Rectangle2D.Double(475.75299072265625, 132.82000732421875,
                2.239000082015991, 5.513999938964844);
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
        stroke = new BasicStroke(0.973f, 0, 0, 4.0f, null, 0.0f);
        shape = new Rectangle2D.Double(475.75299072265625, 132.8179931640625,
                2.240000009536743, 5.513999938964844);
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        paint = new Color(199, 200, 202, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(513.497, 162.356);
        ((GeneralPath) shape).lineTo(459.031, 162.356);
        ((GeneralPath) shape).lineTo(462.922, 139.895);
        ((GeneralPath) shape).lineTo(509.606, 139.895);
        ((GeneralPath) shape).lineTo(513.497, 162.356);
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
        stroke = new BasicStroke(0.973f, 0, 0, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(462.922, 139.896);
        ((GeneralPath) shape).lineTo(509.606, 139.896);
        ((GeneralPath) shape).lineTo(513.497, 162.356);
        ((GeneralPath) shape).lineTo(459.031, 162.356);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(450.424, 218.4);
        ((GeneralPath) shape).lineTo(451.801, 215.645);
        ((GeneralPath) shape).lineTo(520.726, 215.645);
        ((GeneralPath) shape).lineTo(522.104, 218.4);
        ((GeneralPath) shape).lineTo(511.076, 283.189);
        ((GeneralPath) shape).lineTo(509.697, 284.567);
        ((GeneralPath) shape).lineTo(500.05, 284.567);
        ((GeneralPath) shape).lineTo(500.05, 291.461);
        ((GeneralPath) shape).lineTo(487.644, 296.975);
        ((GeneralPath) shape).lineTo(487.644, 298.354);
        ((GeneralPath) shape).lineTo(486.264, 298.354);
        ((GeneralPath) shape).lineTo(484.885, 302.489);
        ((GeneralPath) shape).lineTo(483.508, 303.868);
        ((GeneralPath) shape).lineTo(484.885, 305.247);
        ((GeneralPath) shape).lineTo(483.508, 309.38);
        ((GeneralPath) shape).lineTo(477.993, 309.38);
        ((GeneralPath) shape).lineTo(476.613, 305.247);
        ((GeneralPath) shape).lineTo(477.993, 303.868);
        ((GeneralPath) shape).lineTo(476.613, 302.489);
        ((GeneralPath) shape).lineTo(475.234, 298.354);
        ((GeneralPath) shape).lineTo(473.858, 298.354);
        ((GeneralPath) shape).lineTo(473.856, 291.461);
        ((GeneralPath) shape).lineTo(472.479, 291.461);
        ((GeneralPath) shape).lineTo(472.479, 284.567);
        ((GeneralPath) shape).lineTo(462.83, 284.567);
        ((GeneralPath) shape).lineTo(461.451, 283.189);
        ((GeneralPath) shape).lineTo(450.424, 218.4);
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
        stroke = new BasicStroke(0.973f, 0, 0, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(520.726, 215.645);
        ((GeneralPath) shape).lineTo(522.104, 218.4);
        ((GeneralPath) shape).lineTo(511.075, 283.189);
        ((GeneralPath) shape).lineTo(509.696, 284.567);
        ((GeneralPath) shape).lineTo(500.049, 284.567);
        ((GeneralPath) shape).lineTo(500.049, 291.459);
        ((GeneralPath) shape).lineTo(487.644, 296.975);
        ((GeneralPath) shape).lineTo(487.644, 298.353);
        ((GeneralPath) shape).lineTo(486.264, 298.353);
        ((GeneralPath) shape).lineTo(484.884, 302.488);
        ((GeneralPath) shape).lineTo(483.508, 303.868);
        ((GeneralPath) shape).lineTo(484.884, 305.245);
        ((GeneralPath) shape).lineTo(483.508, 309.38);
        ((GeneralPath) shape).lineTo(477.992, 309.38);
        ((GeneralPath) shape).lineTo(476.613, 305.245);
        ((GeneralPath) shape).lineTo(477.992, 303.868);
        ((GeneralPath) shape).lineTo(476.613, 302.488);
        ((GeneralPath) shape).lineTo(475.234, 298.353);
        ((GeneralPath) shape).lineTo(473.857, 298.353);
        ((GeneralPath) shape).lineTo(473.856, 291.459);
        ((GeneralPath) shape).lineTo(472.479, 291.459);
        ((GeneralPath) shape).lineTo(472.479, 284.567);
        ((GeneralPath) shape).lineTo(462.829, 284.567);
        ((GeneralPath) shape).lineTo(461.45, 283.189);
        ((GeneralPath) shape).lineTo(450.424, 218.4);
        ((GeneralPath) shape).lineTo(451.8, 215.645);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        paint = new Color(255, 255, 255, 255);
        shape = new Rectangle2D.Double(483.50799560546875, 291.4599914550781,
                4.136000156402588, 6.89300012588501);
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
        stroke = new BasicStroke(0.973f, 0, 0, 4.0f, null, 0.0f);
        shape = new Rectangle2D.Double(483.50799560546875, 291.4599914550781,
                4.136000156402588, 6.892000198364258);
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        paint = new Color(255, 255, 255, 255);
        shape = new Rectangle2D.Double(473.8580017089844, 291.4599914550781,
                4.133999824523926, 6.89300012588501);
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
        stroke = new BasicStroke(0.973f, 0, 0, 4.0f, null, 0.0f);
        shape = new Rectangle2D.Double(473.8580017089844, 291.4599914550781,
                4.135000228881836, 6.892000198364258);
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        paint = new Color(255, 255, 255, 255);
        shape = new Rectangle2D.Double(478.6830139160156, 291.4599914550781,
                4.136000156402588, 6.89300012588501);
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
        stroke = new BasicStroke(0.973f, 0, 0, 4.0f, null, 0.0f);
        shape = new Rectangle2D.Double(478.6830139160156, 291.4599914550781,
                4.136000156402588, 6.892000198364258);
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(477.992, 303.868);
        ((GeneralPath) shape).lineTo(483.508, 303.868);
        ((GeneralPath) shape).lineTo(477.992, 303.868);
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
        stroke = new BasicStroke(0.973f, 0, 0, 4.0f, null, 0.0f);
        shape = new Line2D.Float(483.507996f, 303.868011f, 477.992004f,
                303.868011f);
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(487.643, 291.459);
        ((GeneralPath) shape).lineTo(500.047, 291.459);
        ((GeneralPath) shape).lineTo(487.643, 291.459);
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
        stroke = new BasicStroke(0.973f, 0, 0, 4.0f, null, 0.0f);
        shape = new Line2D.Float(500.046997f, 291.460999f, 487.641998f,
                291.460999f);
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        paint = new Color(255, 255, 255, 255);
        shape = new Rectangle2D.Double(473.8559875488281, 284.5679931640625,
                2.240999937057495, 5.513999938964844);
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
        stroke = new BasicStroke(0.973f, 0, 0, 4.0f, null, 0.0f);
        shape = new Rectangle2D.Double(473.8559875488281, 284.5679931640625,
                2.240999937057495, 5.513999938964844);
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
        paint = new Color(255, 255, 255, 255);
        shape = new Rectangle2D.Double(489.5369873046875, 284.5679931640625,
                2.242000102996826, 5.513999938964844);
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
        stroke = new BasicStroke(0.973f, 0, 0, 4.0f, null, 0.0f);
        shape = new Rectangle2D.Double(489.5369873046875, 284.5679931640625,
                2.242000102996826, 5.513999938964844);
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        paint = new Color(255, 255, 255, 255);
        shape = new Rectangle2D.Double(476.09698486328125, 284.5679931640625,
                2.243000030517578, 5.513999938964844);
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
        stroke = new BasicStroke(0.973f, 0, 0, 4.0f, null, 0.0f);
        shape = new Rectangle2D.Double(476.09698486328125, 284.5679931640625,
                2.242000102996826, 5.513999938964844);
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        paint = new Color(255, 255, 255, 255);
        shape = new Rectangle2D.Double(478.3370056152344, 284.5679931640625,
                2.240000009536743, 5.513999938964844);
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
        stroke = new BasicStroke(0.973f, 0, 0, 4.0f, null, 0.0f);
        shape = new Rectangle2D.Double(478.3370056152344, 284.5679931640625,
                2.239000082015991, 5.513999938964844);
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        paint = new Color(255, 255, 255, 255);
        shape = new Rectangle2D.Double(480.5769958496094, 284.5679931640625,
                2.240999937057495, 5.513999938964844);
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
        stroke = new BasicStroke(0.973f, 0, 0, 4.0f, null, 0.0f);
        shape = new Rectangle2D.Double(480.5769958496094, 284.5679931640625,
                2.242000102996826, 5.513999938964844);
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        paint = new Color(255, 255, 255, 255);
        shape = new Rectangle2D.Double(482.8160095214844, 284.5679931640625,
                2.243000030517578, 5.513999938964844);
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
        stroke = new BasicStroke(0.973f, 0, 0, 4.0f, null, 0.0f);
        shape = new Rectangle2D.Double(482.8160095214844, 284.5679931640625,
                2.242000102996826, 5.513999938964844);
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        paint = new Color(255, 255, 255, 255);
        shape = new Rectangle2D.Double(485.0570068359375, 284.5679931640625,
                2.242000102996826, 5.513999938964844);
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
        stroke = new BasicStroke(0.973f, 0, 0, 4.0f, null, 0.0f);
        shape = new Rectangle2D.Double(485.0570068359375, 284.5679931640625,
                2.242000102996826, 5.513999938964844);
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        paint = new Color(255, 255, 255, 255);
        shape = new Rectangle2D.Double(487.2969970703125, 284.5679931640625,
                2.240999937057495, 5.513999938964844);
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
        stroke = new BasicStroke(0.973f, 0, 0, 4.0f, null, 0.0f);
        shape = new Rectangle2D.Double(487.2969970703125, 284.5679931640625,
                2.240000009536743, 5.513999938964844);
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        paint = new Color(255, 255, 255, 255);
        shape = new Rectangle2D.Double(494.53399658203125, 284.5679931640625,
                2.239000082015991, 5.513999938964844);
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
        stroke = new BasicStroke(0.973f, 0, 0, 4.0f, null, 0.0f);
        shape = new Rectangle2D.Double(494.53399658203125, 284.5679931640625,
                2.240000009536743, 5.513999938964844);
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        paint = new Color(199, 200, 202, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(459.031, 260.545);
        ((GeneralPath) shape).lineTo(513.497, 260.545);
        ((GeneralPath) shape).lineTo(509.606, 283.005);
        ((GeneralPath) shape).lineTo(462.922, 283.005);
        ((GeneralPath) shape).lineTo(459.031, 260.545);
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
        stroke = new BasicStroke(0.973f, 0, 0, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(509.606, 283.007);
        ((GeneralPath) shape).lineTo(462.922, 283.007);
        ((GeneralPath) shape).lineTo(459.031, 260.545);
        ((GeneralPath) shape).lineTo(513.497, 260.545);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        ((GeneralPath) shape).lineTo(451.221, 40.319004);
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
        ((GeneralPath) shape).lineTo(453.47903, 41.564007);
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
        ((GeneralPath) shape).lineTo(467.30402, 35.081997);
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
        ((GeneralPath) shape).curveTo(472.30695, 36.178, 471.83594, 36.078,
                471.03894, 36.078);
        ((GeneralPath) shape).moveTo(471.13394, 35.024);
        ((GeneralPath) shape).curveTo(472.30994, 35.024, 473.06595, 35.218998,
                473.40494, 35.609997);
        ((GeneralPath) shape).curveTo(473.74194, 35.998997, 473.91193,
                36.875996, 473.91193, 38.233997);
        ((GeneralPath) shape).curveTo(473.91193, 39.718, 473.74393, 40.652996,
                473.40292, 41.040997);
        ((GeneralPath) shape).curveTo(473.06393, 41.425995, 472.24094,
                41.619995, 470.93494, 41.619995);
        ((GeneralPath) shape).curveTo(469.75995, 41.619995, 469.00095,
                41.429996, 468.65594, 41.047997);
        ((GeneralPath) shape).curveTo(468.31195, 40.667995, 468.13895,
                39.827995, 468.13895, 38.527996);
        ((GeneralPath) shape).curveTo(468.13895, 36.984997, 468.30695,
                36.013996, 468.64594, 35.616997);
        ((GeneralPath) shape).curveTo(468.98193, 35.222996, 469.81192, 35.024,
                471.13394, 35.024);
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
        ((GeneralPath) shape).lineTo(474.74597, 41.564007);
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
        shape = new Rectangle2D.Double(489.96600341796875, 35.08100128173828,
                1.2280000448226929, 6.482999801635742);
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
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(495.984, 39.412);
        ((GeneralPath) shape).lineTo(494.866, 36.036);
        ((GeneralPath) shape).lineTo(493.766, 39.412);
        ((GeneralPath) shape).lineTo(495.98398, 39.412);
        ((GeneralPath) shape).moveTo(496.26898, 40.319);
        ((GeneralPath) shape).lineTo(493.477, 40.319);
        ((GeneralPath) shape).lineTo(493.07498, 41.564);
        ((GeneralPath) shape).lineTo(491.77597, 41.564);
        ((GeneralPath) shape).lineTo(493.92798, 35.081);
        ((GeneralPath) shape).lineTo(495.77197, 35.081);
        ((GeneralPath) shape).lineTo(497.95697, 41.564003);
        ((GeneralPath) shape).lineTo(496.68298, 41.564003);
        ((GeneralPath) shape).lineTo(496.26898, 40.319004);
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
        ((GeneralPath) shape).lineTo(504.80798, 41.564007);
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
        ((GeneralPath) shape).lineTo(514.9929, 40.319004);
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
        origAlpha = alpha__0_284;
        g.setTransform(defaultTransform__0_284);
        g.setClip(clip__0_284);
        float alpha__0_285 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_285 = g.getClip();
        AffineTransform defaultTransform__0_285 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        paint2(g, clip_, defaultTransform_, alpha__0, clip__0,
                defaultTransform__0, alpha__0_285, clip__0_285,
                defaultTransform__0_285);
    }

    private static void paint2(Graphics2D g, Shape clip_,
            AffineTransform defaultTransform_, float alpha__0, Shape clip__0,
            AffineTransform defaultTransform__0, float alpha__0_285,
            Shape clip__0_285, AffineTransform defaultTransform__0_285) {
        Shape shape;
        Paint paint;
        float origAlpha;
        // _0_285 is ShapeNode
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
        ((GeneralPath) shape).lineTo(467.30698, 58.098995);
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
        ((GeneralPath) shape).moveTo(472.919, 58.847);
        ((GeneralPath) shape).curveTo(473.762, 58.847, 473.83002, 59.095,
                473.83002, 60.191);
        ((GeneralPath) shape).curveTo(473.83002, 61.27, 473.76202, 61.510002,
                472.919, 61.510002);
        ((GeneralPath) shape).curveTo(472.075, 61.510002, 472.00702, 61.27,
                472.00702, 60.191);
        ((GeneralPath) shape).curveTo(472.00702, 59.094, 472.075, 58.847,
                472.919, 58.847);
        ((GeneralPath) shape).moveTo(472.919, 58.099);
        ((GeneralPath) shape).curveTo(471.247, 58.099, 471.034, 58.603,
                471.034, 60.183);
        ((GeneralPath) shape).curveTo(471.034, 61.741997, 471.247, 62.239998,
                472.919, 62.239998);
        ((GeneralPath) shape).curveTo(474.59, 62.239998, 474.803, 61.740997,
                474.803, 60.183);
        ((GeneralPath) shape).curveTo(474.803, 58.602997, 474.59, 58.099,
                472.919, 58.099);
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
        ((GeneralPath) shape).lineTo(476.65695, 58.1);
        ((GeneralPath) shape).lineTo(475.68396, 58.1);
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
        ((GeneralPath) shape).lineTo(491.77698, 58.098995);
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
        ((GeneralPath) shape).lineTo(495.62598, 58.096996);
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
        ((GeneralPath) shape).curveTo(506.463, 58.602997, 506.25, 58.099,
                504.578, 58.099);
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
        ((GeneralPath) shape).lineTo(507.22296, 58.098995);
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
        ((GeneralPath) shape).moveTo(465.216, 339.154);
        ((GeneralPath) shape).lineTo(465.216, 337.19);
        ((GeneralPath) shape).lineTo(466.727, 337.19);
        ((GeneralPath) shape).curveTo(467.503, 337.19, 467.64798, 337.336,
                467.64798, 338.14902);
        ((GeneralPath) shape).curveTo(467.64798, 338.992, 467.45798, 339.15402,
                466.679, 339.15402);
        ((GeneralPath) shape).lineTo(465.21597, 339.15402);
        ((GeneralPath) shape).moveTo(466.916, 340.12802);
        ((GeneralPath) shape).curveTo(467.448, 340.12802, 467.64697, 340.475,
                467.64697, 340.97302);
        ((GeneralPath) shape).lineTo(467.64697, 342.07703);
        ((GeneralPath) shape).lineTo(468.74097, 342.07703);
        ((GeneralPath) shape).lineTo(468.74097, 340.97504);
        ((GeneralPath) shape).curveTo(468.74097, 340.14203, 468.54398,
                339.70703, 467.73398, 339.63702);
        ((GeneralPath) shape).lineTo(467.73398, 339.60403);
        ((GeneralPath) shape).curveTo(468.74097, 339.45602, 468.74097,
                338.82404, 468.74097, 337.97104);
        ((GeneralPath) shape).curveTo(468.74097, 336.63104, 468.27396,
                336.22903, 467.05396, 336.22903);
        ((GeneralPath) shape).lineTo(464.12094, 336.22903);
        ((GeneralPath) shape).lineTo(464.12094, 342.07605);
        ((GeneralPath) shape).lineTo(465.21494, 342.07605);
        ((GeneralPath) shape).lineTo(465.21494, 340.12704);
        ((GeneralPath) shape).lineTo(466.91592, 340.12704);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(470.591, 339.641);
        ((GeneralPath) shape).curveTo(470.591, 338.874, 470.646, 338.684,
                471.491, 338.684);
        ((GeneralPath) shape).curveTo(472.291, 338.684, 472.414, 338.75,
                472.414, 339.641);
        ((GeneralPath) shape).lineTo(470.591, 339.641);
        ((GeneralPath) shape).moveTo(472.414, 340.779);
        ((GeneralPath) shape).curveTo(472.414, 341.34598, 472.034, 341.34598,
                471.491, 341.34598);
        ((GeneralPath) shape).curveTo(470.613, 341.34598, 470.591, 341.08096,
                470.591, 340.24997);
        ((GeneralPath) shape).lineTo(473.387, 340.24997);
        ((GeneralPath) shape).curveTo(473.387, 338.43396, 473.162, 337.93597,
                471.491, 337.93597);
        ((GeneralPath) shape).curveTo(469.849, 337.93597, 469.618, 338.589,
                469.618, 340.08298);
        ((GeneralPath) shape).curveTo(469.618, 341.59998, 469.935, 342.07797,
                471.491, 342.07797);
        ((GeneralPath) shape).curveTo(472.651, 342.07797, 473.387, 342.01697,
                473.387, 340.77997);
        ((GeneralPath) shape).lineTo(472.414, 340.77997);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(476.849, 342.078);
        ((GeneralPath) shape).lineTo(477.822, 342.078);
        ((GeneralPath) shape).lineTo(477.822, 339.512);
        ((GeneralPath) shape).curveTo(477.822, 338.13998, 477.26898, 337.936,
                475.94598, 337.936);
        ((GeneralPath) shape).curveTo(475.01797, 337.936, 474.175, 337.936,
                474.175, 339.148);
        ((GeneralPath) shape).lineTo(475.14798, 339.148);
        ((GeneralPath) shape).curveTo(475.14798, 338.644, 475.50198, 338.60602,
                475.94598, 338.60602);
        ((GeneralPath) shape).curveTo(476.79297, 338.60602, 476.849, 338.829,
                476.849, 339.46002);
        ((GeneralPath) shape).lineTo(476.849, 340.027);
        ((GeneralPath) shape).lineTo(476.817, 340.027);
        ((GeneralPath) shape).curveTo(476.576, 339.519, 476.067, 339.519,
                475.55, 339.519);
        ((GeneralPath) shape).curveTo(474.55698, 339.519, 474.05298, 339.79102,
                474.05298, 340.769);
        ((GeneralPath) shape).curveTo(474.05298, 341.88, 474.64197, 342.07703,
                475.55, 342.07703);
        ((GeneralPath) shape).curveTo(476.042, 342.07703, 476.65198, 342.07703,
                476.849, 341.47302);
        ((GeneralPath) shape).lineTo(476.849, 342.07803);
        ((GeneralPath) shape).moveTo(475.913, 340.25003);
        ((GeneralPath) shape).curveTo(476.414, 340.25003, 476.849, 340.25003,
                476.849, 340.77002);
        ((GeneralPath) shape).curveTo(476.849, 341.30502, 476.454, 341.34702,
                475.913, 341.34702);
        ((GeneralPath) shape).curveTo(475.257, 341.34702, 475.025, 341.29703,
                475.025, 340.77002);
        ((GeneralPath) shape).curveTo(475.025, 340.25003, 475.397, 340.25003,
                475.913, 340.25003);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(478.636, 337.936);
        ((GeneralPath) shape).lineTo(478.636, 342.078);
        ((GeneralPath) shape).lineTo(479.60898, 342.078);
        ((GeneralPath) shape).lineTo(479.60898, 339.55);
        ((GeneralPath) shape).curveTo(479.60898, 339.01898, 479.78998, 338.684,
                480.40897, 338.684);
        ((GeneralPath) shape).curveTo(480.90497, 338.684, 480.94595, 338.926,
                480.94595, 339.339);
        ((GeneralPath) shape).lineTo(480.94595, 339.55);
        ((GeneralPath) shape).lineTo(481.91895, 339.55);
        ((GeneralPath) shape).lineTo(481.91895, 339.223);
        ((GeneralPath) shape).curveTo(481.91895, 338.451, 481.69495, 337.936,
                480.77695, 337.936);
        ((GeneralPath) shape).curveTo(480.26794, 337.936, 479.81793, 338.065,
                479.60895, 338.509);
        ((GeneralPath) shape).lineTo(479.60895, 337.936);
        ((GeneralPath) shape).lineTo(478.63596, 337.936);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(488.491, 340.128);
        ((GeneralPath) shape).lineTo(486.466, 340.128);
        ((GeneralPath) shape).lineTo(487.467, 337.08798);
        ((GeneralPath) shape).lineTo(487.483, 337.08798);
        ((GeneralPath) shape).lineTo(488.491, 340.128);
        ((GeneralPath) shape).moveTo(488.741, 340.981);
        ((GeneralPath) shape).lineTo(489.126, 342.077);
        ((GeneralPath) shape).lineTo(490.268, 342.077);
        ((GeneralPath) shape).lineTo(488.281, 336.22998);
        ((GeneralPath) shape).lineTo(486.62302, 336.22998);
        ((GeneralPath) shape).lineTo(484.67603, 342.07697);
        ((GeneralPath) shape).lineTo(485.842, 342.07697);
        ((GeneralPath) shape).lineTo(486.209, 340.98096);
        ((GeneralPath) shape).lineTo(488.74103, 340.98096);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(490.556, 337.936);
        ((GeneralPath) shape).lineTo(490.556, 342.078);
        ((GeneralPath) shape).lineTo(491.529, 342.078);
        ((GeneralPath) shape).lineTo(491.529, 339.55);
        ((GeneralPath) shape).curveTo(491.529, 339.01898, 491.71, 338.684,
                492.32898, 338.684);
        ((GeneralPath) shape).curveTo(492.82498, 338.684, 492.86697, 338.926,
                492.86697, 339.339);
        ((GeneralPath) shape).lineTo(492.86697, 339.55);
        ((GeneralPath) shape).lineTo(493.83997, 339.55);
        ((GeneralPath) shape).lineTo(493.83997, 339.223);
        ((GeneralPath) shape).curveTo(493.83997, 338.451, 493.61496, 337.936,
                492.69797, 337.936);
        ((GeneralPath) shape).curveTo(492.18796, 337.936, 491.73798, 338.065,
                491.52896, 338.509);
        ((GeneralPath) shape).lineTo(491.52896, 337.936);
        ((GeneralPath) shape).lineTo(490.55597, 337.936);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(494.404, 337.936);
        ((GeneralPath) shape).lineTo(494.404, 342.078);
        ((GeneralPath) shape).lineTo(495.37698, 342.078);
        ((GeneralPath) shape).lineTo(495.37698, 339.769);
        ((GeneralPath) shape).curveTo(495.37698, 339.044, 495.54297, 338.68402,
                496.37997, 338.68402);
        ((GeneralPath) shape).curveTo(496.94797, 338.68402, 497.07898,
                338.88803, 497.07898, 339.40103);
        ((GeneralPath) shape).lineTo(497.07898, 342.07703);
        ((GeneralPath) shape).lineTo(498.05197, 342.07703);
        ((GeneralPath) shape).lineTo(498.05197, 339.76804);
        ((GeneralPath) shape).curveTo(498.05197, 339.04303, 498.20398,
                338.68304, 498.98398, 338.68304);
        ((GeneralPath) shape).curveTo(499.511, 338.68304, 499.63297, 338.88705,
                499.63297, 339.40005);
        ((GeneralPath) shape).lineTo(499.63297, 342.07605);
        ((GeneralPath) shape).lineTo(500.60596, 342.07605);
        ((GeneralPath) shape).lineTo(500.60596, 339.30606);
        ((GeneralPath) shape).curveTo(500.60596, 338.30106, 500.26495,
                337.93405, 499.23596, 337.93405);
        ((GeneralPath) shape).curveTo(498.69995, 337.93405, 498.15097,
                338.09006, 497.96695, 338.65704);
        ((GeneralPath) shape).lineTo(497.93695, 338.65704);
        ((GeneralPath) shape).curveTo(497.82895, 338.06503, 497.24295,
                337.93405, 496.73395, 337.93405);
        ((GeneralPath) shape).curveTo(496.19095, 337.93405, 495.60794,
                338.05005, 495.37796, 338.56604);
        ((GeneralPath) shape).lineTo(495.37796, 337.93405);
        ((GeneralPath) shape).lineTo(494.40396, 337.93405);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(503.356, 338.684);
        ((GeneralPath) shape).curveTo(504.19998, 338.684, 504.26797, 338.93198,
                504.26797, 340.02798);
        ((GeneralPath) shape).curveTo(504.26797, 341.107, 504.19998, 341.347,
                503.356, 341.347);
        ((GeneralPath) shape).curveTo(502.513, 341.347, 502.44498, 341.107,
                502.44498, 340.02798);
        ((GeneralPath) shape).curveTo(502.44498, 338.93198, 502.51398, 338.684,
                503.356, 338.684);
        ((GeneralPath) shape).moveTo(503.356, 337.936);
        ((GeneralPath) shape).curveTo(501.685, 337.936, 501.472, 338.44,
                501.472, 340.02002);
        ((GeneralPath) shape).curveTo(501.472, 341.579, 501.685, 342.07803,
                503.356, 342.07803);
        ((GeneralPath) shape).curveTo(505.02798, 342.07803, 505.241, 341.57904,
                505.241, 340.02002);
        ((GeneralPath) shape).curveTo(505.241, 338.44003, 505.02798, 337.936,
                503.356, 337.936);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(506.001, 337.936);
        ((GeneralPath) shape).lineTo(506.001, 342.078);
        ((GeneralPath) shape).lineTo(506.974, 342.078);
        ((GeneralPath) shape).lineTo(506.974, 339.55);
        ((GeneralPath) shape).curveTo(506.974, 339.01898, 507.155, 338.684,
                507.774, 338.684);
        ((GeneralPath) shape).curveTo(508.27, 338.684, 508.31097, 338.926,
                508.31097, 339.339);
        ((GeneralPath) shape).lineTo(508.31097, 339.55);
        ((GeneralPath) shape).lineTo(509.28397, 339.55);
        ((GeneralPath) shape).lineTo(509.28397, 339.223);
        ((GeneralPath) shape).curveTo(509.28397, 338.451, 509.05997, 337.936,
                508.14197, 337.936);
        ((GeneralPath) shape).curveTo(507.63297, 337.936, 507.18295, 338.065,
                506.97397, 338.509);
        ((GeneralPath) shape).lineTo(506.97397, 337.936);
        ((GeneralPath) shape).lineTo(506.00098, 337.936);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(417.813, 349.657);
        ((GeneralPath) shape).lineTo(417.813, 347.93802);
        ((GeneralPath) shape).lineTo(419.134, 347.93802);
        ((GeneralPath) shape).curveTo(419.815, 347.93802, 419.941, 348.066,
                419.941, 348.777);
        ((GeneralPath) shape).curveTo(419.941, 349.515, 419.77502, 349.656,
                419.09302, 349.656);
        ((GeneralPath) shape).lineTo(417.81302, 349.656);
        ((GeneralPath) shape).moveTo(419.30103, 350.51);
        ((GeneralPath) shape).curveTo(419.76602, 350.51, 419.94104, 350.81302,
                419.94104, 351.24902);
        ((GeneralPath) shape).lineTo(419.94104, 352.21503);
        ((GeneralPath) shape).lineTo(420.89804, 352.21503);
        ((GeneralPath) shape).lineTo(420.89804, 351.25104);
        ((GeneralPath) shape).curveTo(420.89804, 350.52203, 420.72504,
                350.14203, 420.01703, 350.08005);
        ((GeneralPath) shape).lineTo(420.01703, 350.05203);
        ((GeneralPath) shape).curveTo(420.89804, 349.92203, 420.89804,
                349.36902, 420.89804, 348.62305);
        ((GeneralPath) shape).curveTo(420.89804, 347.45105, 420.48904,
                347.09906, 419.42203, 347.09906);
        ((GeneralPath) shape).lineTo(416.85602, 347.09906);
        ((GeneralPath) shape).lineTo(416.85602, 352.21506);
        ((GeneralPath) shape).lineTo(417.81302, 352.21506);
        ((GeneralPath) shape).lineTo(417.81302, 350.50906);
        ((GeneralPath) shape).lineTo(419.30103, 350.50906);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(422.516, 350.083);
        ((GeneralPath) shape).curveTo(422.516, 349.41202, 422.564, 349.245,
                423.304, 349.245);
        ((GeneralPath) shape).curveTo(424.003, 349.245, 424.112, 349.304,
                424.112, 350.083);
        ((GeneralPath) shape).lineTo(422.516, 350.083);
        ((GeneralPath) shape).moveTo(424.111, 351.079);
        ((GeneralPath) shape).curveTo(424.111, 351.57602, 423.77798, 351.57602,
                423.30298, 351.57602);
        ((GeneralPath) shape).curveTo(422.53397, 351.57602, 422.51498,
                351.34503, 422.51498, 350.617);
        ((GeneralPath) shape).lineTo(424.961, 350.617);
        ((GeneralPath) shape).curveTo(424.961, 349.029, 424.76498, 348.592,
                423.303, 348.592);
        ((GeneralPath) shape).curveTo(421.866, 348.592, 421.663, 349.16302,
                421.663, 350.47);
        ((GeneralPath) shape).curveTo(421.663, 351.797, 421.94, 352.216,
                423.303, 352.216);
        ((GeneralPath) shape).curveTo(424.31802, 352.216, 424.961, 352.163,
                424.961, 351.08002);
        ((GeneralPath) shape).lineTo(424.111, 351.08002);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(427.992, 352.215);
        ((GeneralPath) shape).lineTo(428.84302, 352.215);
        ((GeneralPath) shape).lineTo(428.84302, 349.97);
        ((GeneralPath) shape).curveTo(428.84302, 348.769, 428.36002, 348.591,
                427.2, 348.591);
        ((GeneralPath) shape).curveTo(426.389, 348.591, 425.651, 348.591,
                425.651, 349.652);
        ((GeneralPath) shape).lineTo(426.502, 349.652);
        ((GeneralPath) shape).curveTo(426.502, 349.211, 426.81403, 349.177,
                427.2, 349.177);
        ((GeneralPath) shape).curveTo(427.94202, 349.177, 427.992, 349.371,
                427.992, 349.925);
        ((GeneralPath) shape).lineTo(427.992, 350.422);
        ((GeneralPath) shape).lineTo(427.964, 350.422);
        ((GeneralPath) shape).curveTo(427.75198, 349.977, 427.30698, 349.977,
                426.85498, 349.977);
        ((GeneralPath) shape).curveTo(425.986, 349.977, 425.54498, 350.215,
                425.54498, 351.07098);
        ((GeneralPath) shape).curveTo(425.54498, 352.04398, 426.06097,
                352.21597, 426.85498, 352.21597);
        ((GeneralPath) shape).curveTo(427.28598, 352.21597, 427.81897,
                352.21597, 427.99298, 351.68796);
        ((GeneralPath) shape).lineTo(427.99298, 352.21497);
        ((GeneralPath) shape).moveTo(427.17197, 350.61597);
        ((GeneralPath) shape).curveTo(427.61096, 350.61597, 427.99197,
                350.61597, 427.99197, 351.07095);
        ((GeneralPath) shape).curveTo(427.99197, 351.53894, 427.64597,
                351.57596, 427.17197, 351.57596);
        ((GeneralPath) shape).curveTo(426.59897, 351.57596, 426.39697,
                351.53296, 426.39697, 351.07095);
        ((GeneralPath) shape).curveTo(426.39597, 350.61597, 426.722, 350.61597,
                427.17197, 350.61597);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(429.555, 348.591);
        ((GeneralPath) shape).lineTo(429.555, 352.215);
        ((GeneralPath) shape).lineTo(430.40698, 352.215);
        ((GeneralPath) shape).lineTo(430.40698, 350.003);
        ((GeneralPath) shape).curveTo(430.40698, 349.539, 430.56497, 349.245,
                431.106, 349.245);
        ((GeneralPath) shape).curveTo(431.541, 349.245, 431.577, 349.457,
                431.577, 349.818);
        ((GeneralPath) shape).lineTo(431.577, 350.003);
        ((GeneralPath) shape).lineTo(432.429, 350.003);
        ((GeneralPath) shape).lineTo(432.429, 349.71698);
        ((GeneralPath) shape).curveTo(432.429, 349.041, 432.23297, 348.59097,
                431.43, 348.59097);
        ((GeneralPath) shape).curveTo(430.985, 348.59097, 430.59, 348.70398,
                430.408, 349.09198);
        ((GeneralPath) shape).lineTo(430.408, 348.59097);
        ((GeneralPath) shape).lineTo(429.555, 348.59097);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(404.457, 356.11);
        ((GeneralPath) shape).lineTo(405.955, 356.11);
        ((GeneralPath) shape).lineTo(405.955, 355.271);
        ((GeneralPath) shape).lineTo(402.002, 355.271);
        ((GeneralPath) shape).lineTo(402.002, 356.11);
        ((GeneralPath) shape).lineTo(403.5, 356.11);
        ((GeneralPath) shape).lineTo(403.5, 360.387);
        ((GeneralPath) shape).lineTo(404.457, 360.387);
        ((GeneralPath) shape).lineTo(404.457, 356.11);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(409.201, 360.387);
        ((GeneralPath) shape).lineTo(409.201, 356.763);
        ((GeneralPath) shape).lineTo(408.34998, 356.763);
        ((GeneralPath) shape).lineTo(408.34998, 358.841);
        ((GeneralPath) shape).curveTo(408.34998, 359.466, 408.136, 359.747,
                407.46698, 359.747);
        ((GeneralPath) shape).curveTo(406.908, 359.747, 406.861, 359.549,
                406.861, 359.034);
        ((GeneralPath) shape).lineTo(406.861, 356.763);
        ((GeneralPath) shape).lineTo(406.009, 356.763);
        ((GeneralPath) shape).lineTo(406.009, 359.356);
        ((GeneralPath) shape).curveTo(406.009, 360.141, 406.488, 360.387,
                407.204, 360.387);
        ((GeneralPath) shape).curveTo(407.70102, 360.387, 408.15002, 360.274,
                408.35, 359.811);
        ((GeneralPath) shape).lineTo(408.35, 360.387);
        ((GeneralPath) shape).lineTo(409.20102, 360.387);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(409.941, 356.763);
        ((GeneralPath) shape).lineTo(409.941, 360.387);
        ((GeneralPath) shape).lineTo(410.79202, 360.387);
        ((GeneralPath) shape).lineTo(410.79202, 358.176);
        ((GeneralPath) shape).curveTo(410.79202, 357.711, 410.95, 357.418,
                411.49203, 357.418);
        ((GeneralPath) shape).curveTo(411.92603, 357.418, 411.96304, 357.629,
                411.96304, 357.991);
        ((GeneralPath) shape).lineTo(411.96304, 358.176);
        ((GeneralPath) shape).lineTo(412.81406, 358.176);
        ((GeneralPath) shape).lineTo(412.81406, 357.889);
        ((GeneralPath) shape).curveTo(412.81406, 357.213, 412.61804, 356.764,
                411.81506, 356.764);
        ((GeneralPath) shape).curveTo(411.37006, 356.764, 410.97507, 356.877,
                410.79306, 357.265);
        ((GeneralPath) shape).lineTo(410.79306, 356.764);
        ((GeneralPath) shape).lineTo(409.94107, 356.764);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(413.202, 356.763);
        ((GeneralPath) shape).lineTo(413.202, 360.387);
        ((GeneralPath) shape).lineTo(414.054, 360.387);
        ((GeneralPath) shape).lineTo(414.054, 358.176);
        ((GeneralPath) shape).curveTo(414.054, 357.711, 414.211, 357.418,
                414.753, 357.418);
        ((GeneralPath) shape).curveTo(415.188, 357.418, 415.224, 357.629,
                415.224, 357.991);
        ((GeneralPath) shape).lineTo(415.224, 358.176);
        ((GeneralPath) shape).lineTo(416.075, 358.176);
        ((GeneralPath) shape).lineTo(416.075, 357.889);
        ((GeneralPath) shape).curveTo(416.075, 357.213, 415.879, 356.764,
                415.07602, 356.764);
        ((GeneralPath) shape).curveTo(414.631, 356.764, 414.23703, 356.877,
                414.05502, 357.265);
        ((GeneralPath) shape).lineTo(414.05502, 356.764);
        ((GeneralPath) shape).lineTo(413.20203, 356.764);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(417.314, 358.255);
        ((GeneralPath) shape).curveTo(417.314, 357.584, 417.362, 357.417,
                418.102, 357.417);
        ((GeneralPath) shape).curveTo(418.801, 357.417, 418.91, 357.475,
                418.91, 358.255);
        ((GeneralPath) shape).lineTo(417.314, 358.255);
        ((GeneralPath) shape).moveTo(418.91, 359.251);
        ((GeneralPath) shape).curveTo(418.91, 359.74802, 418.578, 359.74802,
                418.102, 359.74802);
        ((GeneralPath) shape).curveTo(417.33298, 359.74802, 417.314, 359.51602,
                417.314, 358.789);
        ((GeneralPath) shape).lineTo(419.76, 358.789);
        ((GeneralPath) shape).curveTo(419.76, 357.201, 419.564, 356.764,
                418.10202, 356.764);
        ((GeneralPath) shape).curveTo(416.665, 356.764, 416.462, 357.33502,
                416.462, 358.642);
        ((GeneralPath) shape).curveTo(416.462, 359.969, 416.74002, 360.387,
                418.10202, 360.387);
        ((GeneralPath) shape).curveTo(419.118, 360.387, 419.76, 360.33398,
                419.76, 359.251);
        ((GeneralPath) shape).lineTo(418.91, 359.251);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(420.002, 357.402);
        ((GeneralPath) shape).lineTo(420.45, 357.402);
        ((GeneralPath) shape).lineTo(420.45, 359.269);
        ((GeneralPath) shape).curveTo(420.45, 360.17, 420.739, 360.38602,
                421.69, 360.38602);
        ((GeneralPath) shape).curveTo(422.62302, 360.38602, 422.897, 360.06003,
                422.897, 358.979);
        ((GeneralPath) shape).lineTo(422.152, 358.979);
        ((GeneralPath) shape).curveTo(422.152, 359.359, 422.206, 359.747,
                421.69, 359.747);
        ((GeneralPath) shape).curveTo(421.301, 359.747, 421.301, 359.595,
                421.301, 359.26202);
        ((GeneralPath) shape).lineTo(421.301, 357.40204);
        ((GeneralPath) shape).lineTo(422.715, 357.40204);
        ((GeneralPath) shape).lineTo(422.715, 356.76303);
        ((GeneralPath) shape).lineTo(421.301, 356.76303);
        ((GeneralPath) shape).lineTo(421.301, 355.93503);
        ((GeneralPath) shape).lineTo(420.449, 355.93503);
        ((GeneralPath) shape).lineTo(420.449, 356.76303);
        ((GeneralPath) shape).lineTo(420.001, 356.76303);
        ((GeneralPath) shape).lineTo(420.001, 357.40204);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(428.654, 358.682);
        ((GeneralPath) shape).lineTo(426.883, 358.682);
        ((GeneralPath) shape).lineTo(427.759, 356.022);
        ((GeneralPath) shape).lineTo(427.772, 356.022);
        ((GeneralPath) shape).lineTo(428.654, 358.682);
        ((GeneralPath) shape).moveTo(428.873, 359.428);
        ((GeneralPath) shape).lineTo(429.20898, 360.38702);
        ((GeneralPath) shape).lineTo(430.20798, 360.38702);
        ((GeneralPath) shape).lineTo(428.46997, 355.27103);
        ((GeneralPath) shape).lineTo(427.01898, 355.27103);
        ((GeneralPath) shape).lineTo(425.31497, 360.38702);
        ((GeneralPath) shape).lineTo(426.33597, 360.38702);
        ((GeneralPath) shape).lineTo(426.65698, 359.428);
        ((GeneralPath) shape).lineTo(428.873, 359.428);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(430.461, 356.763);
        ((GeneralPath) shape).lineTo(430.461, 360.387);
        ((GeneralPath) shape).lineTo(431.313, 360.387);
        ((GeneralPath) shape).lineTo(431.313, 358.176);
        ((GeneralPath) shape).curveTo(431.313, 357.711, 431.47, 357.418,
                432.012, 357.418);
        ((GeneralPath) shape).curveTo(432.447, 357.418, 432.483, 357.629,
                432.483, 357.991);
        ((GeneralPath) shape).lineTo(432.483, 358.176);
        ((GeneralPath) shape).lineTo(433.334, 358.176);
        ((GeneralPath) shape).lineTo(433.334, 357.889);
        ((GeneralPath) shape).curveTo(433.334, 357.213, 433.138, 356.764,
                432.33502, 356.764);
        ((GeneralPath) shape).curveTo(431.89, 356.764, 431.49603, 356.877,
                431.31403, 357.265);
        ((GeneralPath) shape).lineTo(431.31403, 356.764);
        ((GeneralPath) shape).lineTo(430.46103, 356.764);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(433.828, 356.763);
        ((GeneralPath) shape).lineTo(433.828, 360.387);
        ((GeneralPath) shape).lineTo(434.68, 360.387);
        ((GeneralPath) shape).lineTo(434.68, 358.367);
        ((GeneralPath) shape).curveTo(434.68, 357.733, 434.82498, 357.418,
                435.55798, 357.418);
        ((GeneralPath) shape).curveTo(436.055, 357.418, 436.16898, 357.596,
                436.16898, 358.046);
        ((GeneralPath) shape).lineTo(436.16898, 360.387);
        ((GeneralPath) shape).lineTo(437.02, 360.387);
        ((GeneralPath) shape).lineTo(437.02, 358.367);
        ((GeneralPath) shape).curveTo(437.02, 357.733, 437.154, 357.418,
                437.835, 357.418);
        ((GeneralPath) shape).curveTo(438.297, 357.418, 438.40298, 357.596,
                438.40298, 358.046);
        ((GeneralPath) shape).lineTo(438.40298, 360.387);
        ((GeneralPath) shape).lineTo(439.254, 360.387);
        ((GeneralPath) shape).lineTo(439.254, 357.964);
        ((GeneralPath) shape).curveTo(439.254, 357.085, 438.956, 356.763,
                438.056, 356.763);
        ((GeneralPath) shape).curveTo(437.587, 356.763, 437.107, 356.9,
                436.946, 357.396);
        ((GeneralPath) shape).lineTo(436.92, 357.396);
        ((GeneralPath) shape).curveTo(436.824, 356.878, 436.31302, 356.763,
                435.867, 356.763);
        ((GeneralPath) shape).curveTo(435.391, 356.763, 434.88202, 356.865,
                434.68, 357.316);
        ((GeneralPath) shape).lineTo(434.68, 356.763);
        ((GeneralPath) shape).lineTo(433.828, 356.763);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(441.662, 357.417);
        ((GeneralPath) shape).curveTo(442.399, 357.417, 442.46, 357.634,
                442.46, 358.593);
        ((GeneralPath) shape).curveTo(442.46, 359.537, 442.399, 359.74698,
                441.662, 359.74698);
        ((GeneralPath) shape).curveTo(440.92398, 359.74698, 440.86398, 359.537,
                440.86398, 358.593);
        ((GeneralPath) shape).curveTo(440.86398, 357.63397, 440.92398, 357.417,
                441.662, 357.417);
        ((GeneralPath) shape).moveTo(441.662, 356.763);
        ((GeneralPath) shape).curveTo(440.19897, 356.763, 440.013, 357.204,
                440.013, 358.587);
        ((GeneralPath) shape).curveTo(440.013, 359.95, 440.2, 360.387, 441.662,
                360.387);
        ((GeneralPath) shape).curveTo(443.124, 360.387, 443.31, 359.94998,
                443.31, 358.587);
        ((GeneralPath) shape).curveTo(443.311, 357.204, 443.124, 356.763,
                441.662, 356.763);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(443.976, 356.763);
        ((GeneralPath) shape).lineTo(443.976, 360.387);
        ((GeneralPath) shape).lineTo(444.82703, 360.387);
        ((GeneralPath) shape).lineTo(444.82703, 358.176);
        ((GeneralPath) shape).curveTo(444.82703, 357.711, 444.98502, 357.418,
                445.52603, 357.418);
        ((GeneralPath) shape).curveTo(445.96103, 357.418, 445.99704, 357.629,
                445.99704, 357.991);
        ((GeneralPath) shape).lineTo(445.99704, 358.176);
        ((GeneralPath) shape).lineTo(446.84903, 358.176);
        ((GeneralPath) shape).lineTo(446.84903, 357.889);
        ((GeneralPath) shape).curveTo(446.84903, 357.213, 446.653, 356.764,
                445.85004, 356.764);
        ((GeneralPath) shape).curveTo(445.40503, 356.764, 445.01004, 356.877,
                444.82803, 357.265);
        ((GeneralPath) shape).lineTo(444.82803, 356.764);
        ((GeneralPath) shape).lineTo(443.97604, 356.764);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(540.265, 47.404);
        ((GeneralPath) shape).lineTo(542.662, 47.404);
        ((GeneralPath) shape).lineTo(542.662, 46.564);
        ((GeneralPath) shape).lineTo(539.308, 46.564);
        ((GeneralPath) shape).lineTo(539.308, 51.68);
        ((GeneralPath) shape).lineTo(540.265, 51.68);
        ((GeneralPath) shape).lineTo(540.265, 49.549);
        ((GeneralPath) shape).lineTo(542.538, 49.549);
        ((GeneralPath) shape).lineTo(542.538, 48.696);
        ((GeneralPath) shape).lineTo(540.265, 48.696);
        ((GeneralPath) shape).lineTo(540.265, 47.404);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(543.163, 48.057);
        ((GeneralPath) shape).lineTo(543.163, 51.681);
        ((GeneralPath) shape).lineTo(544.015, 51.681);
        ((GeneralPath) shape).lineTo(544.015, 49.47);
        ((GeneralPath) shape).curveTo(544.015, 49.005, 544.172, 48.712,
                544.714, 48.712);
        ((GeneralPath) shape).curveTo(545.149, 48.712, 545.185, 48.923,
                545.185, 49.285004);
        ((GeneralPath) shape).lineTo(545.185, 49.470005);
        ((GeneralPath) shape).lineTo(546.036, 49.470005);
        ((GeneralPath) shape).lineTo(546.036, 49.183006);
        ((GeneralPath) shape).curveTo(546.036, 48.507008, 545.84, 48.058006,
                545.037, 48.058006);
        ((GeneralPath) shape).curveTo(544.592, 48.058006, 544.198, 48.171005,
                544.016, 48.559006);
        ((GeneralPath) shape).lineTo(544.016, 48.058006);
        ((GeneralPath) shape).lineTo(543.16296, 48.058006);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(548.073, 48.711);
        ((GeneralPath) shape).curveTo(548.811, 48.711, 548.871, 48.927998,
                548.871, 49.886997);
        ((GeneralPath) shape).curveTo(548.871, 50.830997, 548.811, 51.040997,
                548.073, 51.040997);
        ((GeneralPath) shape).curveTo(547.335, 51.040997, 547.275, 50.830997,
                547.275, 49.886997);
        ((GeneralPath) shape).curveTo(547.275, 48.927998, 547.335, 48.711,
                548.073, 48.711);
        ((GeneralPath) shape).moveTo(548.073, 48.057);
        ((GeneralPath) shape).curveTo(546.61, 48.057, 546.425, 48.498, 546.425,
                49.879997);
        ((GeneralPath) shape).curveTo(546.425, 51.243996, 546.61096, 51.679996,
                548.073, 51.679996);
        ((GeneralPath) shape).curveTo(549.535, 51.679996, 549.721, 51.242996,
                549.721, 49.879997);
        ((GeneralPath) shape).curveTo(549.722, 48.497997, 549.536, 48.057,
                548.073, 48.057);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(550.493, 48.057);
        ((GeneralPath) shape).lineTo(550.493, 51.681);
        ((GeneralPath) shape).lineTo(551.344, 51.681);
        ((GeneralPath) shape).lineTo(551.344, 49.708);
        ((GeneralPath) shape).curveTo(551.344, 49.066, 551.445, 48.712,
                552.179, 48.712);
        ((GeneralPath) shape).curveTo(552.721, 48.712, 552.833, 48.890003,
                552.833, 49.408);
        ((GeneralPath) shape).lineTo(552.833, 51.681);
        ((GeneralPath) shape).lineTo(553.685, 51.681);
        ((GeneralPath) shape).lineTo(553.685, 49.32);
        ((GeneralPath) shape).curveTo(553.685, 48.447, 553.402, 48.058,
                552.473, 48.058);
        ((GeneralPath) shape).curveTo(551.98004, 48.058, 551.559, 48.111,
                551.37103, 48.632);
        ((GeneralPath) shape).lineTo(551.34406, 48.632);
        ((GeneralPath) shape).lineTo(551.34406, 48.058);
        ((GeneralPath) shape).lineTo(550.49304, 48.058);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(554.116, 48.696);
        ((GeneralPath) shape).lineTo(554.564, 48.696);
        ((GeneralPath) shape).lineTo(554.564, 50.563);
        ((GeneralPath) shape).curveTo(554.564, 51.464, 554.853, 51.681,
                555.804, 51.681);
        ((GeneralPath) shape).curveTo(556.737, 51.681, 557.011, 51.355,
                557.011, 50.274);
        ((GeneralPath) shape).lineTo(556.266, 50.274);
        ((GeneralPath) shape).curveTo(556.266, 50.654, 556.32, 51.042, 555.804,
                51.042);
        ((GeneralPath) shape).curveTo(555.41504, 51.042, 555.41504, 50.89,
                555.41504, 50.557);
        ((GeneralPath) shape).lineTo(555.41504, 48.697);
        ((GeneralPath) shape).lineTo(556.82904, 48.697);
        ((GeneralPath) shape).lineTo(556.82904, 48.058);
        ((GeneralPath) shape).lineTo(555.41504, 48.058);
        ((GeneralPath) shape).lineTo(555.41504, 47.23);
        ((GeneralPath) shape).lineTo(554.56305, 47.23);
        ((GeneralPath) shape).lineTo(554.56305, 48.058);
        ((GeneralPath) shape).lineTo(554.11505, 48.058);
        ((GeneralPath) shape).lineTo(554.11505, 48.696);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(527.977, 55.576);
        ((GeneralPath) shape).lineTo(529.475, 55.576);
        ((GeneralPath) shape).lineTo(529.475, 54.736);
        ((GeneralPath) shape).lineTo(525.522, 54.736);
        ((GeneralPath) shape).lineTo(525.522, 55.576);
        ((GeneralPath) shape).lineTo(527.02, 55.576);
        ((GeneralPath) shape).lineTo(527.02, 59.853);
        ((GeneralPath) shape).lineTo(527.977, 59.853);
        ((GeneralPath) shape).lineTo(527.977, 55.576);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(532.722, 59.853);
        ((GeneralPath) shape).lineTo(532.722, 56.229);
        ((GeneralPath) shape).lineTo(531.871, 56.229);
        ((GeneralPath) shape).lineTo(531.871, 58.308);
        ((GeneralPath) shape).curveTo(531.871, 58.933, 531.65594, 59.213997,
                530.988, 59.213997);
        ((GeneralPath) shape).curveTo(530.42896, 59.213997, 530.38196,
                59.015995, 530.38196, 58.500996);
        ((GeneralPath) shape).lineTo(530.38196, 56.23);
        ((GeneralPath) shape).lineTo(529.52997, 56.23);
        ((GeneralPath) shape).lineTo(529.52997, 58.822998);
        ((GeneralPath) shape).curveTo(529.52997, 59.607998, 530.009, 59.853996,
                530.725, 59.853996);
        ((GeneralPath) shape).curveTo(531.222, 59.853996, 531.67096, 59.740997,
                531.871, 59.277996);
        ((GeneralPath) shape).lineTo(531.871, 59.853996);
        ((GeneralPath) shape).lineTo(532.722, 59.853996);
        g.setPaint(paint);
        g.fill(shape);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(533.461, 56.229);
        ((GeneralPath) shape).lineTo(533.461, 59.853);
        ((GeneralPath) shape).lineTo(534.313, 59.853);
        ((GeneralPath) shape).lineTo(534.313, 57.641);
        ((GeneralPath) shape).curveTo(534.313, 57.177, 534.47, 56.883,
                535.01196, 56.883);
        ((GeneralPath) shape).curveTo(535.446, 56.883, 535.483, 57.095,
                535.483, 57.456);
        ((GeneralPath) shape).lineTo(535.483, 57.641003);
        ((GeneralPath) shape).lineTo(536.334, 57.641003);
        ((GeneralPath) shape).lineTo(536.334, 57.355003);
        ((GeneralPath) shape).curveTo(536.334, 56.679005, 536.138, 56.229004,
                535.33496, 56.229004);
        ((GeneralPath) shape).curveTo(534.88995, 56.229004, 534.496, 56.342003,
                534.31396, 56.730003);
        ((GeneralPath) shape).lineTo(534.31396, 56.229004);
        ((GeneralPath) shape).lineTo(533.46094, 56.229004);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(536.723, 56.229);
        ((GeneralPath) shape).lineTo(536.723, 59.853);
        ((GeneralPath) shape).lineTo(537.57404, 59.853);
        ((GeneralPath) shape).lineTo(537.57404, 57.641);
        ((GeneralPath) shape).curveTo(537.57404, 57.177, 537.73206, 56.883,
                538.273, 56.883);
        ((GeneralPath) shape).curveTo(538.708, 56.883, 538.744, 57.095,
                538.744, 57.456);
        ((GeneralPath) shape).lineTo(538.744, 57.641003);
        ((GeneralPath) shape).lineTo(539.596, 57.641003);
        ((GeneralPath) shape).lineTo(539.596, 57.355003);
        ((GeneralPath) shape).curveTo(539.596, 56.679005, 539.4, 56.229004,
                538.597, 56.229004);
        ((GeneralPath) shape).curveTo(538.152, 56.229004, 537.75696, 56.342003,
                537.575, 56.730003);
        ((GeneralPath) shape).lineTo(537.575, 56.229004);
        ((GeneralPath) shape).lineTo(536.723, 56.229004);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(540.835, 57.721);
        ((GeneralPath) shape).curveTo(540.835, 57.05, 540.883, 56.883,
                541.62305, 56.883);
        ((GeneralPath) shape).curveTo(542.322, 56.883, 542.431, 56.941998,
                542.431, 57.721);
        ((GeneralPath) shape).lineTo(540.835, 57.721);
        ((GeneralPath) shape).moveTo(542.431, 58.717);
        ((GeneralPath) shape).curveTo(542.431, 59.212997, 542.098, 59.212997,
                541.62305, 59.212997);
        ((GeneralPath) shape).curveTo(540.85406, 59.212997, 540.835, 58.982,
                540.835, 58.253998);
        ((GeneralPath) shape).lineTo(543.281, 58.253998);
        ((GeneralPath) shape).curveTo(543.281, 56.664997, 543.085, 56.228996,
                541.623, 56.228996);
        ((GeneralPath) shape).curveTo(540.186, 56.228996, 539.983, 56.799995,
                539.983, 58.107998);
        ((GeneralPath) shape).curveTo(539.983, 59.434998, 540.261, 59.852997,
                541.623, 59.852997);
        ((GeneralPath) shape).curveTo(542.638, 59.852997, 543.281, 59.799995,
                543.281, 58.716995);
        ((GeneralPath) shape).lineTo(542.431, 58.716995);
        g.setPaint(paint);
        g.fill(shape);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(543.521, 56.868);
        ((GeneralPath) shape).lineTo(543.97, 56.868);
        ((GeneralPath) shape).lineTo(543.97, 58.735);
        ((GeneralPath) shape).curveTo(543.97, 59.636, 544.259, 59.853,
                545.20996, 59.853);
        ((GeneralPath) shape).curveTo(546.14197, 59.853, 546.41595, 59.526,
                546.41595, 58.446);
        ((GeneralPath) shape).lineTo(545.67194, 58.446);
        ((GeneralPath) shape).curveTo(545.67194, 58.826, 545.7249, 59.214,
                545.20996, 59.214);
        ((GeneralPath) shape).curveTo(544.81995, 59.214, 544.81995, 59.063,
                544.81995, 58.73);
        ((GeneralPath) shape).lineTo(544.81995, 56.87);
        ((GeneralPath) shape).lineTo(546.2349, 56.87);
        ((GeneralPath) shape).lineTo(546.2349, 56.23);
        ((GeneralPath) shape).lineTo(544.81995, 56.23);
        ((GeneralPath) shape).lineTo(544.81995, 55.402);
        ((GeneralPath) shape).lineTo(543.96893, 55.402);
        ((GeneralPath) shape).lineTo(543.96893, 56.23);
        ((GeneralPath) shape).lineTo(543.51996, 56.23);
        ((GeneralPath) shape).lineTo(543.51996, 56.868);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(552.175, 58.147);
        ((GeneralPath) shape).lineTo(550.403, 58.147);
        ((GeneralPath) shape).lineTo(551.279, 55.488);
        ((GeneralPath) shape).lineTo(551.29297, 55.488);
        ((GeneralPath) shape).lineTo(552.175, 58.147);
        ((GeneralPath) shape).moveTo(552.394, 58.892998);
        ((GeneralPath) shape).lineTo(552.73, 59.851997);
        ((GeneralPath) shape).lineTo(553.729, 59.851997);
        ((GeneralPath) shape).lineTo(551.991, 54.735996);
        ((GeneralPath) shape).lineTo(550.54004, 54.735996);
        ((GeneralPath) shape).lineTo(548.83606, 59.851997);
        ((GeneralPath) shape).lineTo(549.85706, 59.851997);
        ((GeneralPath) shape).lineTo(550.17804, 58.892998);
        ((GeneralPath) shape).lineTo(552.39404, 58.892998);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(553.981, 56.229);
        ((GeneralPath) shape).lineTo(553.981, 59.853);
        ((GeneralPath) shape).lineTo(554.83203, 59.853);
        ((GeneralPath) shape).lineTo(554.83203, 57.641);
        ((GeneralPath) shape).curveTo(554.83203, 57.177, 554.99005, 56.883,
                555.53204, 56.883);
        ((GeneralPath) shape).curveTo(555.96606, 56.883, 556.002, 57.095,
                556.002, 57.456);
        ((GeneralPath) shape).lineTo(556.002, 57.641003);
        ((GeneralPath) shape).lineTo(556.854, 57.641003);
        ((GeneralPath) shape).lineTo(556.854, 57.355003);
        ((GeneralPath) shape).curveTo(556.854, 56.679005, 556.658, 56.229004,
                555.855, 56.229004);
        ((GeneralPath) shape).curveTo(555.41, 56.229004, 555.01495, 56.342003,
                554.833, 56.730003);
        ((GeneralPath) shape).lineTo(554.833, 56.229004);
        ((GeneralPath) shape).lineTo(553.981, 56.229004);
        g.setPaint(paint);
        g.fill(shape);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(557.349, 56.229);
        ((GeneralPath) shape).lineTo(557.349, 59.853);
        ((GeneralPath) shape).lineTo(558.2, 59.853);
        ((GeneralPath) shape).lineTo(558.2, 57.833);
        ((GeneralPath) shape).curveTo(558.2, 57.199, 558.346, 56.883, 559.078,
                56.883);
        ((GeneralPath) shape).curveTo(559.575, 56.883, 559.689, 57.062,
                559.689, 57.510998);
        ((GeneralPath) shape).lineTo(559.689, 59.852997);
        ((GeneralPath) shape).lineTo(560.541, 59.852997);
        ((GeneralPath) shape).lineTo(560.541, 57.832996);
        ((GeneralPath) shape).curveTo(560.541, 57.198997, 560.674, 56.882996,
                561.35504, 56.882996);
        ((GeneralPath) shape).curveTo(561.817, 56.882996, 561.92303, 57.061996,
                561.92303, 57.510994);
        ((GeneralPath) shape).lineTo(561.92303, 59.852993);
        ((GeneralPath) shape).lineTo(562.77405, 59.852993);
        ((GeneralPath) shape).lineTo(562.77405, 57.43);
        ((GeneralPath) shape).curveTo(562.77405, 56.551, 562.47705, 56.229,
                561.57605, 56.229);
        ((GeneralPath) shape).curveTo(561.10706, 56.229, 560.6271, 56.366,
                560.46606, 56.862);
        ((GeneralPath) shape).lineTo(560.44006, 56.862);
        ((GeneralPath) shape).curveTo(560.3451, 56.343998, 559.83405, 56.229,
                559.38806, 56.229);
        ((GeneralPath) shape).curveTo(558.91205, 56.229, 558.40204, 56.331,
                558.2001, 56.782);
        ((GeneralPath) shape).lineTo(558.2001, 56.229);
        ((GeneralPath) shape).lineTo(557.34906, 56.229);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(565.183, 56.883);
        ((GeneralPath) shape).curveTo(565.92, 56.883, 565.98096, 57.1,
                565.98096, 58.059);
        ((GeneralPath) shape).curveTo(565.98096, 59.003, 565.92, 59.212997,
                565.183, 59.212997);
        ((GeneralPath) shape).curveTo(564.445, 59.212997, 564.385, 59.003,
                564.385, 58.059);
        ((GeneralPath) shape).curveTo(564.385, 57.1, 564.44403, 56.883,
                565.183, 56.883);
        ((GeneralPath) shape).moveTo(565.183, 56.229);
        ((GeneralPath) shape).curveTo(563.72, 56.229, 563.534, 56.670002,
                563.534, 58.053);
        ((GeneralPath) shape).curveTo(563.534, 59.417, 563.721, 59.853,
                565.183, 59.853);
        ((GeneralPath) shape).curveTo(566.64496, 59.853, 566.831, 59.416,
                566.831, 58.053);
        ((GeneralPath) shape).curveTo(566.831, 56.670002, 566.645, 56.229,
                565.183, 56.229);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(567.496, 56.229);
        ((GeneralPath) shape).lineTo(567.496, 59.853);
        ((GeneralPath) shape).lineTo(568.347, 59.853);
        ((GeneralPath) shape).lineTo(568.347, 57.641);
        ((GeneralPath) shape).curveTo(568.347, 57.177, 568.505, 56.883,
                569.047, 56.883);
        ((GeneralPath) shape).curveTo(569.481, 56.883, 569.51697, 57.095,
                569.51697, 57.456);
        ((GeneralPath) shape).lineTo(569.51697, 57.641003);
        ((GeneralPath) shape).lineTo(570.36896, 57.641003);
        ((GeneralPath) shape).lineTo(570.36896, 57.355003);
        ((GeneralPath) shape).curveTo(570.36896, 56.679005, 570.173, 56.229004,
                569.36993, 56.229004);
        ((GeneralPath) shape).curveTo(568.9249, 56.229004, 568.5299, 56.342003,
                568.34796, 56.730003);
        ((GeneralPath) shape).lineTo(568.34796, 56.229004);
        ((GeneralPath) shape).lineTo(567.496, 56.229004);
        g.setPaint(paint);
        g.fill(shape);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(571.31, 95.527);
        ((GeneralPath) shape).lineTo(571.31, 98.266);
        ((GeneralPath) shape).lineTo(572.269, 98.266);
        ((GeneralPath) shape).lineTo(572.269, 94.433);
        ((GeneralPath) shape).lineTo(566.422, 94.433);
        ((GeneralPath) shape).lineTo(566.422, 95.527);
        ((GeneralPath) shape).lineTo(568.857, 95.527);
        ((GeneralPath) shape).lineTo(568.857, 98.125);
        ((GeneralPath) shape).lineTo(569.832, 98.125);
        ((GeneralPath) shape).lineTo(569.832, 95.527);
        ((GeneralPath) shape).lineTo(571.31, 95.527);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(570.563, 98.808);
        ((GeneralPath) shape).lineTo(566.42096, 98.808);
        ((GeneralPath) shape).lineTo(566.42096, 99.781);
        ((GeneralPath) shape).lineTo(568.94794, 99.781);
        ((GeneralPath) shape).curveTo(569.47894, 99.781, 569.81396, 99.962,
                569.81396, 100.581);
        ((GeneralPath) shape).curveTo(569.81396, 101.077, 569.57196, 101.119,
                569.15894, 101.119);
        ((GeneralPath) shape).lineTo(568.94794, 101.119);
        ((GeneralPath) shape).lineTo(568.94794, 102.092);
        ((GeneralPath) shape).lineTo(569.27496, 102.092);
        ((GeneralPath) shape).curveTo(570.04694, 102.092, 570.56195,
                101.867004, 570.56195, 100.950005);
        ((GeneralPath) shape).curveTo(570.56195, 100.441, 570.43195, 99.991005,
                569.98895, 99.782005);
        ((GeneralPath) shape).lineTo(570.56195, 99.782005);
        ((GeneralPath) shape).lineTo(570.56195, 98.80801);
        g.setPaint(paint);
        g.fill(shape);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(569.815, 104.389);
        ((GeneralPath) shape).curveTo(569.815, 105.233, 569.567, 105.301,
                568.471, 105.301);
        ((GeneralPath) shape).curveTo(567.392, 105.301, 567.15204, 105.233,
                567.15204, 104.389);
        ((GeneralPath) shape).curveTo(567.15204, 103.546, 567.392, 103.477,
                568.471, 103.477);
        ((GeneralPath) shape).curveTo(569.567, 103.477, 569.815, 103.545,
                569.815, 104.389);
        ((GeneralPath) shape).moveTo(570.563, 104.389);
        ((GeneralPath) shape).curveTo(570.563, 102.717, 570.058, 102.505,
                568.479, 102.505);
        ((GeneralPath) shape).curveTo(566.92, 102.505, 566.421, 102.716995,
                566.421, 104.389);
        ((GeneralPath) shape).curveTo(566.421, 106.061, 566.92004, 106.273,
                568.479, 106.273);
        ((GeneralPath) shape).curveTo(570.059, 106.273, 570.563, 106.061005,
                570.563, 104.389);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(570.563, 107.123);
        ((GeneralPath) shape).lineTo(566.42096, 107.123);
        ((GeneralPath) shape).lineTo(566.42096, 108.096);
        ((GeneralPath) shape).lineTo(568.67596, 108.096);
        ((GeneralPath) shape).curveTo(569.40894, 108.096, 569.81494, 108.21,
                569.81494, 109.05);
        ((GeneralPath) shape).curveTo(569.81494, 109.66901, 569.61096, 109.799,
                569.0189, 109.799);
        ((GeneralPath) shape).lineTo(566.4209, 109.799);
        ((GeneralPath) shape).lineTo(566.4209, 110.772);
        ((GeneralPath) shape).lineTo(569.1189, 110.772);
        ((GeneralPath) shape).curveTo(570.1169, 110.772, 570.5619, 110.449005,
                570.5619, 109.387);
        ((GeneralPath) shape).curveTo(570.5619, 108.823, 570.4999, 108.342,
                569.9049, 108.127);
        ((GeneralPath) shape).lineTo(569.9049, 108.097);
        ((GeneralPath) shape).lineTo(570.5619, 108.097);
        ((GeneralPath) shape).lineTo(570.5619, 107.123);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(569.832, 111.232);
        ((GeneralPath) shape).lineTo(569.832, 111.745);
        ((GeneralPath) shape).lineTo(567.699, 111.745);
        ((GeneralPath) shape).curveTo(566.66895, 111.745, 566.422, 112.075005,
                566.422, 113.162);
        ((GeneralPath) shape).curveTo(566.422, 114.228004, 566.795, 114.541,
                568.02997, 114.541);
        ((GeneralPath) shape).lineTo(568.02997, 113.69);
        ((GeneralPath) shape).curveTo(567.595, 113.69, 567.152, 113.751,
                567.152, 113.162);
        ((GeneralPath) shape).curveTo(567.152, 112.718, 567.326, 112.718,
                567.707, 112.718);
        ((GeneralPath) shape).lineTo(569.832, 112.718);
        ((GeneralPath) shape).lineTo(569.832, 114.335);
        ((GeneralPath) shape).lineTo(570.563, 114.335);
        ((GeneralPath) shape).lineTo(570.563, 112.718);
        ((GeneralPath) shape).lineTo(571.508, 112.718);
        ((GeneralPath) shape).lineTo(571.508, 111.745);
        ((GeneralPath) shape).lineTo(570.563, 111.745);
        ((GeneralPath) shape).lineTo(570.563, 111.232);
        ((GeneralPath) shape).lineTo(569.832, 111.232);
        g.setPaint(paint);
        g.fill(shape);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(569.345, 118.823);
        ((GeneralPath) shape).lineTo(571.31, 118.823);
        ((GeneralPath) shape).lineTo(571.31, 120.333);
        ((GeneralPath) shape).curveTo(571.31, 121.11, 571.164, 121.254, 570.35,
                121.254);
        ((GeneralPath) shape).curveTo(569.50696, 121.254, 569.345, 121.063995,
                569.345, 120.284996);
        ((GeneralPath) shape).lineTo(569.345, 118.823);
        ((GeneralPath) shape).moveTo(568.371, 120.522995);
        ((GeneralPath) shape).curveTo(568.371, 121.05499, 568.024, 121.254,
                567.52496, 121.254);
        ((GeneralPath) shape).lineTo(566.42096, 121.254);
        ((GeneralPath) shape).lineTo(566.42096, 122.348);
        ((GeneralPath) shape).lineTo(567.52295, 122.348);
        ((GeneralPath) shape).curveTo(568.35693, 122.348, 568.79095, 122.15,
                568.86096, 121.341);
        ((GeneralPath) shape).lineTo(568.894, 121.341);
        ((GeneralPath) shape).curveTo(569.042, 122.34801, 569.674, 122.34801,
                570.527, 122.34801);
        ((GeneralPath) shape).curveTo(571.867, 122.34801, 572.268, 121.880005,
                572.268, 120.66101);
        ((GeneralPath) shape).lineTo(572.268, 117.72801);
        ((GeneralPath) shape).lineTo(566.421, 117.72801);
        ((GeneralPath) shape).lineTo(566.421, 118.822014);
        ((GeneralPath) shape).lineTo(568.37, 118.822014);
        ((GeneralPath) shape).lineTo(568.37, 120.52301);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(572.269, 124.327);
        ((GeneralPath) shape).lineTo(572.269, 123.354004);
        ((GeneralPath) shape).lineTo(571.435, 123.354004);
        ((GeneralPath) shape).lineTo(571.435, 124.327);
        ((GeneralPath) shape).lineTo(572.269, 124.327);
        ((GeneralPath) shape).moveTo(570.563, 124.327);
        ((GeneralPath) shape).lineTo(570.563, 123.354004);
        ((GeneralPath) shape).lineTo(566.42096, 123.354004);
        ((GeneralPath) shape).lineTo(566.42096, 124.327);
        ((GeneralPath) shape).lineTo(570.563, 124.327);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(566.262, 127.873);
        ((GeneralPath) shape).curveTo(565.51, 127.873, 565.325, 127.704,
                565.325, 126.915);
        ((GeneralPath) shape).curveTo(565.325, 126.375, 565.417, 126.17,
                566.02, 126.17);
        ((GeneralPath) shape).lineTo(566.02, 125.197);
        ((GeneralPath) shape).curveTo(564.76904, 125.133995, 564.594,
                125.895996, 564.594, 126.914);
        ((GeneralPath) shape).curveTo(564.594, 128.384, 564.955, 128.844,
                566.474, 128.844);
        ((GeneralPath) shape).lineTo(570.563, 128.844);
        ((GeneralPath) shape).lineTo(570.563, 127.870995);
        ((GeneralPath) shape).lineTo(569.906, 127.870995);
        ((GeneralPath) shape).curveTo(570.439, 127.629, 570.563, 127.190994,
                570.563, 126.631996);
        ((GeneralPath) shape).curveTo(570.563, 125.074, 569.607, 125.074,
                568.365, 125.074);
        ((GeneralPath) shape).curveTo(567.182, 125.074, 566.422, 125.237,
                566.422, 126.631996);
        ((GeneralPath) shape).curveTo(566.422, 127.114, 566.513, 127.621994,
                567.038, 127.870995);
        ((GeneralPath) shape).lineTo(566.262, 127.870995);
        ((GeneralPath) shape).moveTo(569.815, 126.91599);
        ((GeneralPath) shape).curveTo(569.815, 127.743996, 569.466, 127.87399,
                568.602, 127.87399);
        ((GeneralPath) shape).curveTo(567.63696, 127.87399, 567.152, 127.87399,
                567.152, 126.909);
        ((GeneralPath) shape).curveTo(567.152, 126.050995, 567.685, 126.050995,
                568.602, 126.050995);
        ((GeneralPath) shape).curveTo(569.623, 126.048996, 569.815, 126.243,
                569.815, 126.91599);
        g.setPaint(paint);
        g.fill(shape);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(572.269, 130.761);
        ((GeneralPath) shape).lineTo(572.269, 129.788);
        ((GeneralPath) shape).lineTo(566.422, 129.788);
        ((GeneralPath) shape).lineTo(566.422, 130.761);
        ((GeneralPath) shape).lineTo(568.677, 130.761);
        ((GeneralPath) shape).curveTo(569.41, 130.761, 569.816, 130.875,
                569.816, 131.715);
        ((GeneralPath) shape).curveTo(569.816, 132.334, 569.612, 132.46399,
                569.01996, 132.46399);
        ((GeneralPath) shape).lineTo(566.42194, 132.46399);
        ((GeneralPath) shape).lineTo(566.42194, 133.437);
        ((GeneralPath) shape).lineTo(569.11993, 133.437);
        ((GeneralPath) shape).curveTo(570.1179, 133.437, 570.5629, 133.114,
                570.5629, 132.052);
        ((GeneralPath) shape).curveTo(570.5629, 131.488, 570.49994, 131.007,
                569.90094, 130.792);
        ((GeneralPath) shape).lineTo(569.90094, 130.76201);
        ((GeneralPath) shape).lineTo(572.2689, 130.76201);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(569.832, 133.897);
        ((GeneralPath) shape).lineTo(569.832, 134.41);
        ((GeneralPath) shape).lineTo(567.699, 134.41);
        ((GeneralPath) shape).curveTo(566.66895, 134.41, 566.422, 134.74,
                566.422, 135.82701);
        ((GeneralPath) shape).curveTo(566.422, 136.893, 566.795, 137.20601,
                568.02997, 137.20601);
        ((GeneralPath) shape).lineTo(568.02997, 136.35501);
        ((GeneralPath) shape).curveTo(567.595, 136.35501, 567.152, 136.41602,
                567.152, 135.82701);
        ((GeneralPath) shape).curveTo(567.152, 135.38301, 567.326, 135.38301,
                567.707, 135.38301);
        ((GeneralPath) shape).lineTo(569.832, 135.38301);
        ((GeneralPath) shape).lineTo(569.832, 137.0);
        ((GeneralPath) shape).lineTo(570.563, 137.0);
        ((GeneralPath) shape).lineTo(570.563, 135.383);
        ((GeneralPath) shape).lineTo(571.508, 135.383);
        ((GeneralPath) shape).lineTo(571.508, 134.40999);
        ((GeneralPath) shape).lineTo(570.563, 134.40999);
        ((GeneralPath) shape).lineTo(570.563, 133.89699);
        ((GeneralPath) shape).lineTo(569.832, 133.89699);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(568.377, 140.15);
        ((GeneralPath) shape).lineTo(568.106, 140.15);
        ((GeneralPath) shape).curveTo(566.421, 140.15, 566.421, 141.30699,
                566.421, 142.603);
        ((GeneralPath) shape).curveTo(566.421, 144.111, 566.56604, 144.892,
                568.252, 144.892);
        ((GeneralPath) shape).curveTo(569.727, 144.892, 569.82, 144.396,
                569.911, 142.582);
        ((GeneralPath) shape).curveTo(569.972, 141.398, 569.993, 141.245,
                570.614, 141.245);
        ((GeneralPath) shape).curveTo(571.24, 141.245, 571.348, 141.43199,
                571.348, 142.59);
        ((GeneralPath) shape).curveTo(571.348, 143.40799, 571.325, 143.676,
                570.69104, 143.676);
        ((GeneralPath) shape).lineTo(570.504, 143.676);
        ((GeneralPath) shape).lineTo(570.504, 144.76999);
        ((GeneralPath) shape).lineTo(570.69604, 144.76999);
        ((GeneralPath) shape).curveTo(572.26605, 144.76999, 572.26605,
                143.82799, 572.26605, 142.58899);
        ((GeneralPath) shape).curveTo(572.26605, 141.16599, 572.26605, 140.15,
                570.61304, 140.15);
        ((GeneralPath) shape).curveTo(569.046, 140.15, 569.093, 141.151,
                569.015, 142.428);
        ((GeneralPath) shape).curveTo(568.969, 143.336, 569.078, 143.79799,
                568.25, 143.79799);
        ((GeneralPath) shape).curveTo(567.578, 143.79799, 567.394, 143.652,
                567.394, 142.59);
        ((GeneralPath) shape).curveTo(567.394, 141.538, 567.43896, 141.245,
                568.104, 141.245);
        ((GeneralPath) shape).lineTo(568.375, 141.245);
        ((GeneralPath) shape).lineTo(568.375, 140.15);
        g.setPaint(paint);
        g.fill(shape);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(572.269, 146.626);
        ((GeneralPath) shape).lineTo(572.269, 145.653);
        ((GeneralPath) shape).lineTo(571.435, 145.653);
        ((GeneralPath) shape).lineTo(571.435, 146.626);
        ((GeneralPath) shape).lineTo(572.269, 146.626);
        ((GeneralPath) shape).moveTo(570.563, 146.626);
        ((GeneralPath) shape).lineTo(570.563, 145.653);
        ((GeneralPath) shape).lineTo(566.42096, 145.653);
        ((GeneralPath) shape).lineTo(566.42096, 146.626);
        ((GeneralPath) shape).lineTo(570.563, 146.626);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(566.422, 150.172);
        ((GeneralPath) shape).lineTo(566.422, 151.145);
        ((GeneralPath) shape).lineTo(572.269, 151.145);
        ((GeneralPath) shape).lineTo(572.269, 150.172);
        ((GeneralPath) shape).lineTo(569.972, 150.172);
        ((GeneralPath) shape).lineTo(569.972, 150.149);
        ((GeneralPath) shape).curveTo(570.47, 149.94, 570.56396, 149.421,
                570.56396, 148.927);
        ((GeneralPath) shape).curveTo(570.56396, 147.537, 569.79596, 147.375,
                568.60297, 147.375);
        ((GeneralPath) shape).curveTo(567.36096, 147.375, 566.42194, 147.375,
                566.42194, 148.927);
        ((GeneralPath) shape).curveTo(566.42194, 149.49, 566.54193, 149.92801,
                567.06396, 150.172);
        ((GeneralPath) shape).lineTo(566.42194, 150.172);
        ((GeneralPath) shape).moveTo(569.81494, 149.215);
        ((GeneralPath) shape).curveTo(569.81494, 150.05699, 569.46796,
                150.17299, 568.6019, 150.17299);
        ((GeneralPath) shape).curveTo(567.6369, 150.17299, 567.1519, 150.17299,
                567.1519, 149.215);
        ((GeneralPath) shape).curveTo(567.1519, 148.349, 567.68494, 148.349,
                568.6019, 148.349);
        ((GeneralPath) shape).curveTo(569.6229, 148.349, 569.81494, 148.54199,
                569.81494, 149.215);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(568.857, 152.932);
        ((GeneralPath) shape).curveTo(569.625, 152.932, 569.815, 152.987,
                569.815, 153.832);
        ((GeneralPath) shape).curveTo(569.815, 154.632, 569.749, 154.755,
                568.857, 154.755);
        ((GeneralPath) shape).lineTo(568.857, 152.932);
        ((GeneralPath) shape).moveTo(567.72, 154.755);
        ((GeneralPath) shape).curveTo(567.15295, 154.755, 567.15295, 154.375,
                567.15295, 153.832);
        ((GeneralPath) shape).curveTo(567.15295, 152.955, 567.41797, 152.932,
                568.24994, 152.932);
        ((GeneralPath) shape).lineTo(568.24994, 155.72801);
        ((GeneralPath) shape).curveTo(570.06494, 155.72801, 570.56396, 155.503,
                570.56396, 153.83202);
        ((GeneralPath) shape).curveTo(570.56396, 152.19002, 569.91095,
                151.95901, 568.417, 151.95901);
        ((GeneralPath) shape).curveTo(566.89996, 151.95901, 566.423, 152.27602,
                566.423, 153.83202);
        ((GeneralPath) shape).curveTo(566.423, 154.99301, 566.48395, 155.72801,
                567.72095, 155.72801);
        ((GeneralPath) shape).lineTo(567.72095, 154.755);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(568.371, 162.523);
        ((GeneralPath) shape).lineTo(568.371, 160.498);
        ((GeneralPath) shape).lineTo(571.41, 161.49901);
        ((GeneralPath) shape).lineTo(571.41, 161.51501);
        ((GeneralPath) shape).lineTo(568.371, 162.52301);
        ((GeneralPath) shape).moveTo(567.51794, 162.77301);
        ((GeneralPath) shape).lineTo(566.42194, 163.15701);
        ((GeneralPath) shape).lineTo(566.42194, 164.29901);
        ((GeneralPath) shape).lineTo(572.2689, 162.31201);
        ((GeneralPath) shape).lineTo(572.2689, 160.65302);
        ((GeneralPath) shape).lineTo(566.42194, 158.70601);
        ((GeneralPath) shape).lineTo(566.42194, 159.87302);
        ((GeneralPath) shape).lineTo(567.51794, 160.24002);
        ((GeneralPath) shape).lineTo(567.51794, 162.77303);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(570.563, 164.611);
        ((GeneralPath) shape).lineTo(566.42096, 164.611);
        ((GeneralPath) shape).lineTo(566.42096, 165.583);
        ((GeneralPath) shape).lineTo(568.94794, 165.583);
        ((GeneralPath) shape).curveTo(569.47894, 165.583, 569.81396, 165.76399,
                569.81396, 166.383);
        ((GeneralPath) shape).curveTo(569.81396, 166.879, 569.57196, 166.92099,
                569.15894, 166.92099);
        ((GeneralPath) shape).lineTo(568.94794, 166.92099);
        ((GeneralPath) shape).lineTo(568.94794, 167.894);
        ((GeneralPath) shape).lineTo(569.27496, 167.894);
        ((GeneralPath) shape).curveTo(570.04694, 167.894, 570.56195, 167.67,
                570.56195, 166.752);
        ((GeneralPath) shape).curveTo(570.56195, 166.243, 570.43195, 165.793,
                569.98895, 165.584);
        ((GeneralPath) shape).lineTo(570.56195, 165.584);
        ((GeneralPath) shape).lineTo(570.56195, 164.611);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(570.563, 168.428);
        ((GeneralPath) shape).lineTo(566.42096, 168.428);
        ((GeneralPath) shape).lineTo(566.42096, 169.401);
        ((GeneralPath) shape).lineTo(568.73, 169.401);
        ((GeneralPath) shape).curveTo(569.45496, 169.401, 569.815, 169.567,
                569.815, 170.404);
        ((GeneralPath) shape).curveTo(569.815, 170.972, 569.611, 171.10301,
                569.097, 171.10301);
        ((GeneralPath) shape).lineTo(566.42096, 171.10301);
        ((GeneralPath) shape).lineTo(566.42096, 172.07602);
        ((GeneralPath) shape).lineTo(568.73, 172.07602);
        ((GeneralPath) shape).curveTo(569.45496, 172.07602, 569.815, 172.22801,
                569.815, 173.00702);
        ((GeneralPath) shape).curveTo(569.815, 173.53502, 569.611, 173.65701,
                569.097, 173.65701);
        ((GeneralPath) shape).lineTo(566.42096, 173.65701);
        ((GeneralPath) shape).lineTo(566.42096, 174.63002);
        ((GeneralPath) shape).lineTo(569.191, 174.63002);
        ((GeneralPath) shape).curveTo(570.196, 174.63002, 570.563, 174.29002,
                570.563, 173.26003);
        ((GeneralPath) shape).curveTo(570.563, 172.72403, 570.407, 172.17502,
                569.839, 171.99103);
        ((GeneralPath) shape).lineTo(569.839, 171.96103);
        ((GeneralPath) shape).curveTo(570.43097, 171.85204, 570.563, 171.26703,
                570.563, 170.75803);
        ((GeneralPath) shape).curveTo(570.563, 170.21503, 570.44696, 169.63202,
                569.93097, 169.40202);
        ((GeneralPath) shape).lineTo(570.563, 169.40202);
        ((GeneralPath) shape).lineTo(570.563, 168.42802);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(569.815, 177.35);
        ((GeneralPath) shape).curveTo(569.815, 178.19301, 569.567, 178.26201,
                568.471, 178.26201);
        ((GeneralPath) shape).curveTo(567.392, 178.26201, 567.15204, 178.19402,
                567.15204, 177.35);
        ((GeneralPath) shape).curveTo(567.15204, 176.50601, 567.392, 176.438,
                568.471, 176.438);
        ((GeneralPath) shape).curveTo(569.567, 176.438, 569.815, 176.506,
                569.815, 177.35);
        ((GeneralPath) shape).moveTo(570.563, 177.35);
        ((GeneralPath) shape).curveTo(570.563, 175.67801, 570.058, 175.46501,
                568.479, 175.46501);
        ((GeneralPath) shape).curveTo(566.92, 175.46501, 566.421, 175.67801,
                566.421, 177.35);
        ((GeneralPath) shape).curveTo(566.421, 179.02101, 566.92004, 179.23401,
                568.479, 179.23401);
        ((GeneralPath) shape).curveTo(570.059, 179.23401, 570.563, 179.02101,
                570.563, 177.35);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(570.563, 179.962);
        ((GeneralPath) shape).lineTo(566.42096, 179.962);
        ((GeneralPath) shape).lineTo(566.42096, 180.93501);
        ((GeneralPath) shape).lineTo(568.94794, 180.93501);
        ((GeneralPath) shape).curveTo(569.47894, 180.93501, 569.81396, 181.115,
                569.81396, 181.73401);
        ((GeneralPath) shape).curveTo(569.81396, 182.23001, 569.57196, 182.272,
                569.15894, 182.272);
        ((GeneralPath) shape).lineTo(568.94794, 182.272);
        ((GeneralPath) shape).lineTo(568.94794, 183.24501);
        ((GeneralPath) shape).lineTo(569.27496, 183.24501);
        ((GeneralPath) shape).curveTo(570.04694, 183.24501, 570.56195,
                183.02101, 570.56195, 182.10301);
        ((GeneralPath) shape).curveTo(570.56195, 181.59401, 570.43195,
                181.14401, 569.98895, 180.93501);
        ((GeneralPath) shape).lineTo(570.56195, 180.93501);
        ((GeneralPath) shape).lineTo(570.56195, 179.962);
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
        // _0_353 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(569.345, 216.835);
        ((GeneralPath) shape).lineTo(571.31, 216.835);
        ((GeneralPath) shape).lineTo(571.31, 218.345);
        ((GeneralPath) shape).curveTo(571.31, 219.122, 571.164, 219.266,
                570.35, 219.266);
        ((GeneralPath) shape).curveTo(569.50696, 219.266, 569.345, 219.076,
                569.345, 218.29701);
        ((GeneralPath) shape).lineTo(569.345, 216.835);
        ((GeneralPath) shape).moveTo(568.371, 218.535);
        ((GeneralPath) shape).curveTo(568.371, 219.067, 568.024, 219.266,
                567.52496, 219.266);
        ((GeneralPath) shape).lineTo(566.42096, 219.266);
        ((GeneralPath) shape).lineTo(566.42096, 220.36);
        ((GeneralPath) shape).lineTo(567.52295, 220.36);
        ((GeneralPath) shape).curveTo(568.35693, 220.36, 568.79095, 220.163,
                568.86096, 219.353);
        ((GeneralPath) shape).lineTo(568.894, 219.353);
        ((GeneralPath) shape).curveTo(569.042, 220.36, 569.674, 220.36,
                570.527, 220.36);
        ((GeneralPath) shape).curveTo(571.867, 220.36, 572.268, 219.893,
                572.268, 218.673);
        ((GeneralPath) shape).lineTo(572.268, 215.74);
        ((GeneralPath) shape).lineTo(566.421, 215.74);
        ((GeneralPath) shape).lineTo(566.421, 216.834);
        ((GeneralPath) shape).lineTo(568.37, 216.834);
        ((GeneralPath) shape).lineTo(568.37, 218.535);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(568.857, 222.178);
        ((GeneralPath) shape).curveTo(569.625, 222.178, 569.815, 222.23299,
                569.815, 223.07799);
        ((GeneralPath) shape).curveTo(569.815, 223.87799, 569.749, 224.00099,
                568.857, 224.00099);
        ((GeneralPath) shape).lineTo(568.857, 222.178);
        ((GeneralPath) shape).moveTo(567.72, 224.002);
        ((GeneralPath) shape).curveTo(567.15295, 224.002, 567.15295, 223.622,
                567.15295, 223.079);
        ((GeneralPath) shape).curveTo(567.15295, 222.202, 567.41797, 222.179,
                568.24994, 222.179);
        ((GeneralPath) shape).lineTo(568.24994, 224.975);
        ((GeneralPath) shape).curveTo(570.06494, 224.975, 570.56396, 224.75,
                570.56396, 223.07901);
        ((GeneralPath) shape).curveTo(570.56396, 221.43701, 569.91095,
                221.20601, 568.417, 221.20601);
        ((GeneralPath) shape).curveTo(566.89996, 221.20601, 566.423, 221.52301,
                566.423, 223.07901);
        ((GeneralPath) shape).curveTo(566.423, 224.24, 566.48395, 224.975,
                567.72095, 224.975);
        ((GeneralPath) shape).lineTo(567.72095, 224.002);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(566.422, 228.406);
        ((GeneralPath) shape).lineTo(566.422, 229.37901);
        ((GeneralPath) shape).lineTo(568.987, 229.37901);
        ((GeneralPath) shape).curveTo(570.359, 229.37901, 570.563, 228.82602,
                570.563, 227.50201);
        ((GeneralPath) shape).curveTo(570.563, 226.57501, 570.563, 225.73102,
                569.35, 225.73102);
        ((GeneralPath) shape).lineTo(569.35, 226.70403);
        ((GeneralPath) shape).curveTo(569.855, 226.70403, 569.893, 227.05902,
                569.893, 227.50203);
        ((GeneralPath) shape).curveTo(569.893, 228.35004, 569.67, 228.40604,
                569.039, 228.40604);
        ((GeneralPath) shape).lineTo(568.472, 228.40604);
        ((GeneralPath) shape).lineTo(568.472, 228.37404);
        ((GeneralPath) shape).curveTo(568.98, 228.13304, 568.98, 227.62404,
                568.98, 227.10704);
        ((GeneralPath) shape).curveTo(568.98, 226.11304, 568.708, 225.61005,
                567.73, 225.61005);
        ((GeneralPath) shape).curveTo(566.61896, 225.61005, 566.422, 226.19905,
                566.422, 227.10704);
        ((GeneralPath) shape).curveTo(566.422, 227.59904, 566.422, 228.20905,
                567.026, 228.40604);
        ((GeneralPath) shape).lineTo(566.422, 228.40604);
        ((GeneralPath) shape).moveTo(568.249, 227.46904);
        ((GeneralPath) shape).curveTo(568.249, 227.97005, 568.249, 228.40604,
                567.729, 228.40604);
        ((GeneralPath) shape).curveTo(567.19403, 228.40604, 567.152, 228.01103,
                567.152, 227.46904);
        ((GeneralPath) shape).curveTo(567.152, 226.81303, 567.20197, 226.58205,
                567.729, 226.58205);
        ((GeneralPath) shape).curveTo(568.249, 226.58205, 568.249, 226.95505,
                568.249, 227.46904);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(570.563, 230.161);
        ((GeneralPath) shape).lineTo(566.42096, 230.161);
        ((GeneralPath) shape).lineTo(566.42096, 231.134);
        ((GeneralPath) shape).lineTo(568.94794, 231.134);
        ((GeneralPath) shape).curveTo(569.47894, 231.134, 569.81396, 231.315,
                569.81396, 231.934);
        ((GeneralPath) shape).curveTo(569.81396, 232.43001, 569.57196, 232.472,
                569.15894, 232.472);
        ((GeneralPath) shape).lineTo(568.94794, 232.472);
        ((GeneralPath) shape).lineTo(568.94794, 233.445);
        ((GeneralPath) shape).lineTo(569.27496, 233.445);
        ((GeneralPath) shape).curveTo(570.04694, 233.445, 570.56195, 233.22,
                570.56195, 232.30301);
        ((GeneralPath) shape).curveTo(570.56195, 231.794, 570.43195, 231.34401,
                569.98895, 231.13501);
        ((GeneralPath) shape).lineTo(570.56195, 231.13501);
        ((GeneralPath) shape).lineTo(570.56195, 230.16101);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(569.345, 237.718);
        ((GeneralPath) shape).lineTo(571.31, 237.718);
        ((GeneralPath) shape).lineTo(571.31, 239.228);
        ((GeneralPath) shape).curveTo(571.31, 240.00499, 571.164, 240.149,
                570.35, 240.149);
        ((GeneralPath) shape).curveTo(569.50696, 240.149, 569.345, 239.959,
                569.345, 239.18001);
        ((GeneralPath) shape).lineTo(569.345, 237.718);
        ((GeneralPath) shape).moveTo(568.371, 239.418);
        ((GeneralPath) shape).curveTo(568.371, 239.95, 568.024, 240.149,
                567.52496, 240.149);
        ((GeneralPath) shape).lineTo(566.42096, 240.149);
        ((GeneralPath) shape).lineTo(566.42096, 241.243);
        ((GeneralPath) shape).lineTo(567.52295, 241.243);
        ((GeneralPath) shape).curveTo(568.35693, 241.243, 568.79095, 241.04599,
                568.86096, 240.237);
        ((GeneralPath) shape).lineTo(568.894, 240.237);
        ((GeneralPath) shape).curveTo(569.042, 241.243, 569.674, 241.243,
                570.527, 241.243);
        ((GeneralPath) shape).curveTo(571.867, 241.243, 572.268, 240.776,
                572.268, 239.556);
        ((GeneralPath) shape).lineTo(572.268, 236.623);
        ((GeneralPath) shape).lineTo(566.421, 236.623);
        ((GeneralPath) shape).lineTo(566.421, 237.717);
        ((GeneralPath) shape).lineTo(568.37, 237.717);
        ((GeneralPath) shape).lineTo(568.37, 239.418);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(572.269, 243.223);
        ((GeneralPath) shape).lineTo(572.269, 242.25);
        ((GeneralPath) shape).lineTo(571.435, 242.25);
        ((GeneralPath) shape).lineTo(571.435, 243.223);
        ((GeneralPath) shape).lineTo(572.269, 243.223);
        ((GeneralPath) shape).moveTo(570.563, 243.223);
        ((GeneralPath) shape).lineTo(570.563, 242.25);
        ((GeneralPath) shape).lineTo(566.42096, 242.25);
        ((GeneralPath) shape).lineTo(566.42096, 243.223);
        ((GeneralPath) shape).lineTo(570.563, 243.223);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(566.262, 246.769);
        ((GeneralPath) shape).curveTo(565.51, 246.769, 565.325, 246.59999,
                565.325, 245.811);
        ((GeneralPath) shape).curveTo(565.325, 245.27101, 565.417, 245.06601,
                566.02, 245.06601);
        ((GeneralPath) shape).lineTo(566.02, 244.093);
        ((GeneralPath) shape).curveTo(564.76904, 244.03, 564.594, 244.792,
                564.594, 245.81);
        ((GeneralPath) shape).curveTo(564.594, 247.28099, 564.955, 247.73999,
                566.474, 247.73999);
        ((GeneralPath) shape).lineTo(570.563, 247.73999);
        ((GeneralPath) shape).lineTo(570.563, 246.76698);
        ((GeneralPath) shape).lineTo(569.906, 246.76698);
        ((GeneralPath) shape).curveTo(570.439, 246.52599, 570.563, 246.08699,
                570.563, 245.52898);
        ((GeneralPath) shape).curveTo(570.563, 243.97098, 569.607, 243.97098,
                568.365, 243.97098);
        ((GeneralPath) shape).curveTo(567.182, 243.97098, 566.422, 244.13397,
                566.422, 245.52898);
        ((GeneralPath) shape).curveTo(566.422, 246.01097, 566.513, 246.51797,
                567.038, 246.76698);
        ((GeneralPath) shape).lineTo(566.262, 246.76698);
        ((GeneralPath) shape).moveTo(569.815, 245.81099);
        ((GeneralPath) shape).curveTo(569.815, 246.63998, 569.466, 246.76898,
                568.602, 246.76898);
        ((GeneralPath) shape).curveTo(567.63696, 246.76898, 567.152, 246.76898,
                567.152, 245.80399);
        ((GeneralPath) shape).curveTo(567.152, 244.94499, 567.685, 244.94499,
                568.602, 244.94499);
        ((GeneralPath) shape).curveTo(569.623, 244.94499, 569.815, 245.13899,
                569.815, 245.81099);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(572.269, 249.658);
        ((GeneralPath) shape).lineTo(572.269, 248.685);
        ((GeneralPath) shape).lineTo(566.422, 248.685);
        ((GeneralPath) shape).lineTo(566.422, 249.658);
        ((GeneralPath) shape).lineTo(568.677, 249.658);
        ((GeneralPath) shape).curveTo(569.41, 249.658, 569.816, 249.772,
                569.816, 250.612);
        ((GeneralPath) shape).curveTo(569.816, 251.231, 569.612, 251.36,
                569.01996, 251.36);
        ((GeneralPath) shape).lineTo(566.42194, 251.36);
        ((GeneralPath) shape).lineTo(566.42194, 252.33301);
        ((GeneralPath) shape).lineTo(569.11993, 252.33301);
        ((GeneralPath) shape).curveTo(570.1179, 252.33301, 570.5629, 252.01001,
                570.5629, 250.94801);
        ((GeneralPath) shape).curveTo(570.5629, 250.38402, 570.49994,
                249.90302, 569.90094, 249.68901);
        ((GeneralPath) shape).lineTo(569.90094, 249.65901);
        ((GeneralPath) shape).lineTo(572.2689, 249.65901);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(569.832, 252.794);
        ((GeneralPath) shape).lineTo(569.832, 253.307);
        ((GeneralPath) shape).lineTo(567.699, 253.307);
        ((GeneralPath) shape).curveTo(566.66895, 253.307, 566.422, 253.638,
                566.422, 254.72401);
        ((GeneralPath) shape).curveTo(566.422, 255.78902, 566.795, 256.10303,
                568.02997, 256.10303);
        ((GeneralPath) shape).lineTo(568.02997, 255.25203);
        ((GeneralPath) shape).curveTo(567.595, 255.25203, 567.152, 255.31303,
                567.152, 254.72403);
        ((GeneralPath) shape).curveTo(567.152, 254.27902, 567.326, 254.27902,
                567.707, 254.27902);
        ((GeneralPath) shape).lineTo(569.832, 254.27902);
        ((GeneralPath) shape).lineTo(569.832, 255.89603);
        ((GeneralPath) shape).lineTo(570.563, 255.89603);
        ((GeneralPath) shape).lineTo(570.563, 254.27902);
        ((GeneralPath) shape).lineTo(571.508, 254.27902);
        ((GeneralPath) shape).lineTo(571.508, 253.30602);
        ((GeneralPath) shape).lineTo(570.563, 253.30602);
        ((GeneralPath) shape).lineTo(570.563, 252.79301);
        ((GeneralPath) shape).lineTo(569.832, 252.79301);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(568.377, 259.047);
        ((GeneralPath) shape).lineTo(568.106, 259.047);
        ((GeneralPath) shape).curveTo(566.421, 259.047, 566.421, 260.204,
                566.421, 261.5);
        ((GeneralPath) shape).curveTo(566.421, 263.008, 566.56604, 263.789,
                568.252, 263.789);
        ((GeneralPath) shape).curveTo(569.727, 263.789, 569.82, 263.293,
                569.911, 261.479);
        ((GeneralPath) shape).curveTo(569.972, 260.295, 569.993, 260.142,
                570.614, 260.142);
        ((GeneralPath) shape).curveTo(571.24, 260.142, 571.348, 260.328,
                571.348, 261.487);
        ((GeneralPath) shape).curveTo(571.348, 262.306, 571.325, 262.573,
                570.69104, 262.573);
        ((GeneralPath) shape).lineTo(570.504, 262.573);
        ((GeneralPath) shape).lineTo(570.504, 263.667);
        ((GeneralPath) shape).lineTo(570.69604, 263.667);
        ((GeneralPath) shape).curveTo(572.26605, 263.667, 572.26605, 262.725,
                572.26605, 261.486);
        ((GeneralPath) shape).curveTo(572.26605, 260.064, 572.26605, 259.047,
                570.61304, 259.047);
        ((GeneralPath) shape).curveTo(569.046, 259.047, 569.093, 260.048,
                569.015, 261.325);
        ((GeneralPath) shape).curveTo(568.969, 262.233, 569.078, 262.695,
                568.25, 262.695);
        ((GeneralPath) shape).curveTo(567.578, 262.695, 567.394, 262.549,
                567.394, 261.487);
        ((GeneralPath) shape).curveTo(567.394, 260.435, 567.43896, 260.142,
                568.104, 260.142);
        ((GeneralPath) shape).lineTo(568.375, 260.142);
        ((GeneralPath) shape).lineTo(568.375, 259.047);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(572.269, 265.522);
        ((GeneralPath) shape).lineTo(572.269, 264.549);
        ((GeneralPath) shape).lineTo(571.435, 264.549);
        ((GeneralPath) shape).lineTo(571.435, 265.522);
        ((GeneralPath) shape).lineTo(572.269, 265.522);
        ((GeneralPath) shape).moveTo(570.563, 265.522);
        ((GeneralPath) shape).lineTo(570.563, 264.549);
        ((GeneralPath) shape).lineTo(566.42096, 264.549);
        ((GeneralPath) shape).lineTo(566.42096, 265.522);
        ((GeneralPath) shape).lineTo(570.563, 265.522);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(566.422, 269.068);
        ((GeneralPath) shape).lineTo(566.422, 270.041);
        ((GeneralPath) shape).lineTo(572.269, 270.041);
        ((GeneralPath) shape).lineTo(572.269, 269.068);
        ((GeneralPath) shape).lineTo(569.972, 269.068);
        ((GeneralPath) shape).lineTo(569.972, 269.046);
        ((GeneralPath) shape).curveTo(570.47, 268.83698, 570.56396, 268.318,
                570.56396, 267.824);
        ((GeneralPath) shape).curveTo(570.56396, 266.433, 569.79596, 266.272,
                568.60297, 266.272);
        ((GeneralPath) shape).curveTo(567.36096, 266.272, 566.42194, 266.272,
                566.42194, 267.824);
        ((GeneralPath) shape).curveTo(566.42194, 268.387, 566.54193, 268.825,
                567.06396, 269.068);
        ((GeneralPath) shape).lineTo(566.42194, 269.068);
        ((GeneralPath) shape).moveTo(569.81494, 268.111);
        ((GeneralPath) shape).curveTo(569.81494, 268.952, 569.46796, 269.068,
                568.6019, 269.068);
        ((GeneralPath) shape).curveTo(567.6369, 269.068, 567.1519, 269.068,
                567.1519, 268.111);
        ((GeneralPath) shape).curveTo(567.1519, 267.245, 567.68494, 267.245,
                568.6019, 267.245);
        ((GeneralPath) shape).curveTo(569.6229, 267.245, 569.81494, 267.438,
                569.81494, 268.111);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(568.857, 271.828);
        ((GeneralPath) shape).curveTo(569.625, 271.828, 569.815, 271.883,
                569.815, 272.728);
        ((GeneralPath) shape).curveTo(569.815, 273.52798, 569.749, 273.651,
                568.857, 273.651);
        ((GeneralPath) shape).lineTo(568.857, 271.828);
        ((GeneralPath) shape).moveTo(567.72, 273.651);
        ((GeneralPath) shape).curveTo(567.15295, 273.651, 567.15295, 273.271,
                567.15295, 272.728);
        ((GeneralPath) shape).curveTo(567.15295, 271.85, 567.41797, 271.828,
                568.24994, 271.828);
        ((GeneralPath) shape).lineTo(568.24994, 274.624);
        ((GeneralPath) shape).curveTo(570.06494, 274.624, 570.56396, 274.4,
                570.56396, 272.728);
        ((GeneralPath) shape).curveTo(570.56396, 271.086, 569.91095, 270.855,
                568.417, 270.855);
        ((GeneralPath) shape).curveTo(566.89996, 270.855, 566.423, 271.172,
                566.423, 272.728);
        ((GeneralPath) shape).curveTo(566.423, 273.888, 566.48395, 274.624,
                567.72095, 274.624);
        ((GeneralPath) shape).lineTo(567.72095, 273.651);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(568.371, 281.418);
        ((GeneralPath) shape).lineTo(568.371, 279.393);
        ((GeneralPath) shape).lineTo(571.41, 280.394);
        ((GeneralPath) shape).lineTo(571.41, 280.40903);
        ((GeneralPath) shape).lineTo(568.371, 281.41803);
        ((GeneralPath) shape).moveTo(567.51794, 281.66803);
        ((GeneralPath) shape).lineTo(566.42194, 282.05203);
        ((GeneralPath) shape).lineTo(566.42194, 283.19403);
        ((GeneralPath) shape).lineTo(572.2689, 281.20703);
        ((GeneralPath) shape).lineTo(572.2689, 279.54904);
        ((GeneralPath) shape).lineTo(566.42194, 277.60205);
        ((GeneralPath) shape).lineTo(566.42194, 278.76804);
        ((GeneralPath) shape).lineTo(567.51794, 279.13504);
        ((GeneralPath) shape).lineTo(567.51794, 281.66803);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(570.563, 283.507);
        ((GeneralPath) shape).lineTo(566.42096, 283.507);
        ((GeneralPath) shape).lineTo(566.42096, 284.47998);
        ((GeneralPath) shape).lineTo(568.94794, 284.47998);
        ((GeneralPath) shape).curveTo(569.47894, 284.47998, 569.81396,
                284.66098, 569.81396, 285.27997);
        ((GeneralPath) shape).curveTo(569.81396, 285.77597, 569.57196,
                285.81796, 569.15894, 285.81796);
        ((GeneralPath) shape).lineTo(568.94794, 285.81796);
        ((GeneralPath) shape).lineTo(568.94794, 286.79095);
        ((GeneralPath) shape).lineTo(569.27496, 286.79095);
        ((GeneralPath) shape).curveTo(570.04694, 286.79095, 570.56195,
                286.56595, 570.56195, 285.64896);
        ((GeneralPath) shape).curveTo(570.56195, 285.13995, 570.43195,
                284.68994, 569.98895, 284.48096);
        ((GeneralPath) shape).lineTo(570.56195, 284.48096);
        ((GeneralPath) shape).lineTo(570.56195, 283.50696);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(570.563, 287.324);
        ((GeneralPath) shape).lineTo(566.42096, 287.324);
        ((GeneralPath) shape).lineTo(566.42096, 288.297);
        ((GeneralPath) shape).lineTo(568.73, 288.297);
        ((GeneralPath) shape).curveTo(569.45496, 288.297, 569.815, 288.462,
                569.815, 289.3);
        ((GeneralPath) shape).curveTo(569.815, 289.86798, 569.611, 289.999,
                569.097, 289.999);
        ((GeneralPath) shape).lineTo(566.42096, 289.999);
        ((GeneralPath) shape).lineTo(566.42096, 290.97098);
        ((GeneralPath) shape).lineTo(568.73, 290.97098);
        ((GeneralPath) shape).curveTo(569.45496, 290.97098, 569.815, 291.123,
                569.815, 291.90198);
        ((GeneralPath) shape).curveTo(569.815, 292.43, 569.611, 292.55096,
                569.097, 292.55096);
        ((GeneralPath) shape).lineTo(566.42096, 292.55096);
        ((GeneralPath) shape).lineTo(566.42096, 293.52396);
        ((GeneralPath) shape).lineTo(569.191, 293.52396);
        ((GeneralPath) shape).curveTo(570.196, 293.52396, 570.563, 293.18396,
                570.563, 292.15396);
        ((GeneralPath) shape).curveTo(570.563, 291.61795, 570.407, 291.06995,
                569.839, 290.88495);
        ((GeneralPath) shape).lineTo(569.839, 290.85495);
        ((GeneralPath) shape).curveTo(570.43097, 290.74695, 570.563, 290.16196,
                570.563, 289.65195);
        ((GeneralPath) shape).curveTo(570.563, 289.10895, 570.44696, 288.52594,
                569.93097, 288.29596);
        ((GeneralPath) shape).lineTo(570.563, 288.29596);
        ((GeneralPath) shape).lineTo(570.563, 287.32397);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(569.815, 296.245);
        ((GeneralPath) shape).curveTo(569.815, 297.089, 569.567, 297.15698,
                568.471, 297.15698);
        ((GeneralPath) shape).curveTo(567.392, 297.15698, 567.15204, 297.089,
                567.15204, 296.245);
        ((GeneralPath) shape).curveTo(567.15204, 295.402, 567.392, 295.333,
                568.471, 295.333);
        ((GeneralPath) shape).curveTo(569.567, 295.333, 569.815, 295.402,
                569.815, 296.245);
        ((GeneralPath) shape).moveTo(570.563, 296.245);
        ((GeneralPath) shape).curveTo(570.563, 294.574, 570.058, 294.361,
                568.479, 294.361);
        ((GeneralPath) shape).curveTo(566.92, 294.361, 566.421, 294.574,
                566.421, 296.245);
        ((GeneralPath) shape).curveTo(566.421, 297.917, 566.92004, 298.13,
                568.479, 298.13);
        ((GeneralPath) shape).curveTo(570.059, 298.13, 570.563, 297.917,
                570.563, 296.245);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(570.563, 298.858);
        ((GeneralPath) shape).lineTo(566.42096, 298.858);
        ((GeneralPath) shape).lineTo(566.42096, 299.831);
        ((GeneralPath) shape).lineTo(568.94794, 299.831);
        ((GeneralPath) shape).curveTo(569.47894, 299.831, 569.81396, 300.012,
                569.81396, 300.63098);
        ((GeneralPath) shape).curveTo(569.81396, 301.12698, 569.57196,
                301.16898, 569.15894, 301.16898);
        ((GeneralPath) shape).lineTo(568.94794, 301.16898);
        ((GeneralPath) shape).lineTo(568.94794, 302.14197);
        ((GeneralPath) shape).lineTo(569.27496, 302.14197);
        ((GeneralPath) shape).curveTo(570.04694, 302.14197, 570.56195,
                301.91797, 570.56195, 300.99997);
        ((GeneralPath) shape).curveTo(570.56195, 300.49097, 570.43195,
                300.04095, 569.98895, 299.83197);
        ((GeneralPath) shape).lineTo(570.56195, 299.83197);
        ((GeneralPath) shape).lineTo(570.56195, 298.85797);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(406.35, 321.194);
        ((GeneralPath) shape).lineTo(404.385, 321.194);
        ((GeneralPath) shape).lineTo(404.385, 319.684);
        ((GeneralPath) shape).curveTo(404.385, 318.90698, 404.531, 318.763,
                405.345, 318.763);
        ((GeneralPath) shape).curveTo(406.188, 318.763, 406.35, 318.953,
                406.35, 319.732);
        ((GeneralPath) shape).lineTo(406.35, 321.194);
        ((GeneralPath) shape).moveTo(407.324, 319.494);
        ((GeneralPath) shape).curveTo(407.324, 318.96198, 407.67, 318.763,
                408.169, 318.763);
        ((GeneralPath) shape).lineTo(409.273, 318.763);
        ((GeneralPath) shape).lineTo(409.273, 317.669);
        ((GeneralPath) shape).lineTo(408.17102, 317.669);
        ((GeneralPath) shape).curveTo(407.337, 317.669, 406.903, 317.867,
                406.833, 318.676);
        ((GeneralPath) shape).lineTo(406.801, 318.676);
        ((GeneralPath) shape).curveTo(406.65298, 317.669, 406.021, 317.669,
                405.168, 317.669);
        ((GeneralPath) shape).curveTo(403.828, 317.669, 403.426, 318.13602,
                403.426, 319.35602);
        ((GeneralPath) shape).lineTo(403.426, 322.28903);
        ((GeneralPath) shape).lineTo(409.273, 322.28903);
        ((GeneralPath) shape).lineTo(409.273, 321.19504);
        ((GeneralPath) shape).lineTo(407.325, 321.19504);
        ((GeneralPath) shape).lineTo(407.325, 319.49405);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(406.837, 315.85);
        ((GeneralPath) shape).curveTo(406.069, 315.85, 405.879, 315.795,
                405.879, 314.95);
        ((GeneralPath) shape).curveTo(405.879, 314.15002, 405.94598, 314.027,
                406.837, 314.027);
        ((GeneralPath) shape).lineTo(406.837, 315.85);
        ((GeneralPath) shape).moveTo(407.975, 314.027);
        ((GeneralPath) shape).curveTo(408.542, 314.027, 408.542, 314.407,
                408.542, 314.95);
        ((GeneralPath) shape).curveTo(408.542, 315.828, 408.27698, 315.85,
                407.445, 315.85);
        ((GeneralPath) shape).lineTo(407.445, 313.05402);
        ((GeneralPath) shape).curveTo(405.63, 313.05402, 405.131, 313.278,
                405.131, 314.95);
        ((GeneralPath) shape).curveTo(405.131, 316.592, 405.78403, 316.823,
                407.278, 316.823);
        ((GeneralPath) shape).curveTo(408.795, 316.823, 409.272, 316.506,
                409.272, 314.95);
        ((GeneralPath) shape).curveTo(409.272, 313.789, 409.211, 313.05402,
                407.974, 313.05402);
        ((GeneralPath) shape).lineTo(407.974, 314.027);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(409.272, 309.623);
        ((GeneralPath) shape).lineTo(409.272, 308.65);
        ((GeneralPath) shape).lineTo(406.707, 308.65);
        ((GeneralPath) shape).curveTo(405.335, 308.65, 405.131, 309.203,
                405.131, 310.526);
        ((GeneralPath) shape).curveTo(405.131, 311.453, 405.131, 312.297,
                406.34402, 312.297);
        ((GeneralPath) shape).lineTo(406.34402, 311.324);
        ((GeneralPath) shape).curveTo(405.83902, 311.324, 405.80103, 310.969,
                405.80103, 310.526);
        ((GeneralPath) shape).curveTo(405.80103, 309.67902, 406.02402, 309.622,
                406.65604, 309.622);
        ((GeneralPath) shape).lineTo(407.22205, 309.622);
        ((GeneralPath) shape).lineTo(407.22205, 309.65402);
        ((GeneralPath) shape).curveTo(406.71405, 309.89502, 406.71405,
                310.40402, 406.71405, 310.92102);
        ((GeneralPath) shape).curveTo(406.71405, 311.915, 406.98605, 312.41803,
                407.96405, 312.41803);
        ((GeneralPath) shape).curveTo(409.07605, 312.41803, 409.27206,
                311.82904, 409.27206, 310.92102);
        ((GeneralPath) shape).curveTo(409.27206, 310.42902, 409.27206,
                309.81903, 408.66907, 309.622);
        ((GeneralPath) shape).lineTo(409.27206, 309.622);
        ((GeneralPath) shape).moveTo(407.44507, 310.55902);
        ((GeneralPath) shape).curveTo(407.44507, 310.058, 407.44507, 309.62302,
                407.96506, 309.62302);
        ((GeneralPath) shape).curveTo(408.50006, 309.62302, 408.54205, 310.018,
                408.54205, 310.55902);
        ((GeneralPath) shape).curveTo(408.54205, 311.21503, 408.49207, 311.446,
                407.96506, 311.446);
        ((GeneralPath) shape).curveTo(407.44507, 311.446, 407.44507, 311.074,
                407.44507, 310.55902);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(405.131, 307.867);
        ((GeneralPath) shape).lineTo(409.273, 307.867);
        ((GeneralPath) shape).lineTo(409.273, 306.894);
        ((GeneralPath) shape).lineTo(406.746, 306.894);
        ((GeneralPath) shape).curveTo(406.215, 306.894, 405.88, 306.713,
                405.88, 306.09402);
        ((GeneralPath) shape).curveTo(405.88, 305.59802, 406.122, 305.55603,
                406.535, 305.55603);
        ((GeneralPath) shape).lineTo(406.746, 305.55603);
        ((GeneralPath) shape).lineTo(406.746, 304.58304);
        ((GeneralPath) shape).lineTo(406.419, 304.58304);
        ((GeneralPath) shape).curveTo(405.647, 304.58304, 405.13202, 304.80704,
                405.13202, 305.72504);
        ((GeneralPath) shape).curveTo(405.13202, 306.23404, 405.26202,
                306.68405, 405.70502, 306.89304);
        ((GeneralPath) shape).lineTo(405.13202, 306.89304);
        ((GeneralPath) shape).lineTo(405.13202, 307.86703);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(403.426, 300.31);
        ((GeneralPath) shape).lineTo(403.426, 301.404);
        ((GeneralPath) shape).lineTo(409.272, 301.404);
        ((GeneralPath) shape).lineTo(409.272, 297.586);
        ((GeneralPath) shape).lineTo(408.313, 297.586);
        ((GeneralPath) shape).lineTo(408.313, 300.31);
        ((GeneralPath) shape).lineTo(403.426, 300.31);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(406.837, 296.149);
        ((GeneralPath) shape).curveTo(406.069, 296.149, 405.879, 296.094,
                405.879, 295.248);
        ((GeneralPath) shape).curveTo(405.879, 294.448, 405.94598, 294.32498,
                406.837, 294.32498);
        ((GeneralPath) shape).lineTo(406.837, 296.149);
        ((GeneralPath) shape).moveTo(407.975, 294.326);
        ((GeneralPath) shape).curveTo(408.542, 294.326, 408.542, 294.706,
                408.542, 295.249);
        ((GeneralPath) shape).curveTo(408.542, 296.12698, 408.27698, 296.15,
                407.445, 296.15);
        ((GeneralPath) shape).lineTo(407.445, 293.354);
        ((GeneralPath) shape).curveTo(405.63, 293.354, 405.131, 293.578,
                405.131, 295.25);
        ((GeneralPath) shape).curveTo(405.131, 296.892, 405.78403, 297.123,
                407.278, 297.123);
        ((GeneralPath) shape).curveTo(408.795, 297.123, 409.272, 296.806,
                409.272, 295.25);
        ((GeneralPath) shape).curveTo(409.272, 294.09, 409.211, 293.354,
                407.974, 293.354);
        ((GeneralPath) shape).lineTo(407.974, 294.326);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(405.131, 290.346);
        ((GeneralPath) shape).lineTo(405.131, 291.342);
        ((GeneralPath) shape).lineTo(404.9, 291.342);
        ((GeneralPath) shape).curveTo(404.428, 291.342, 404.17398, 291.342,
                404.17398, 290.673);
        ((GeneralPath) shape).lineTo(404.17398, 290.377);
        ((GeneralPath) shape).lineTo(403.426, 290.377);
        ((GeneralPath) shape).lineTo(403.426, 291.014);
        ((GeneralPath) shape).curveTo(403.426, 292.083, 403.828, 292.315,
                404.783, 292.315);
        ((GeneralPath) shape).lineTo(405.13098, 292.315);
        ((GeneralPath) shape).lineTo(405.13098, 292.891);
        ((GeneralPath) shape).lineTo(405.86197, 292.891);
        ((GeneralPath) shape).lineTo(405.86197, 292.315);
        ((GeneralPath) shape).lineTo(409.27197, 292.315);
        ((GeneralPath) shape).lineTo(409.27197, 291.342);
        ((GeneralPath) shape).lineTo(405.86197, 291.342);
        ((GeneralPath) shape).lineTo(405.86197, 290.346);
        ((GeneralPath) shape).lineTo(405.13098, 290.346);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(405.862, 290.355);
        ((GeneralPath) shape).lineTo(405.862, 289.842);
        ((GeneralPath) shape).lineTo(407.996, 289.842);
        ((GeneralPath) shape).curveTo(409.025, 289.842, 409.272, 289.51202,
                409.272, 288.42502);
        ((GeneralPath) shape).curveTo(409.272, 287.359, 408.89902, 287.04602,
                407.665, 287.04602);
        ((GeneralPath) shape).lineTo(407.665, 287.89703);
        ((GeneralPath) shape).curveTo(408.099, 287.89703, 408.54202, 287.83603,
                408.54202, 288.42505);
        ((GeneralPath) shape).curveTo(408.54202, 288.86905, 408.36902,
                288.86905, 407.98804, 288.86905);
        ((GeneralPath) shape).lineTo(405.86203, 288.86905);
        ((GeneralPath) shape).lineTo(405.86203, 287.25305);
        ((GeneralPath) shape).lineTo(405.13104, 287.25305);
        ((GeneralPath) shape).lineTo(405.13104, 288.86905);
        ((GeneralPath) shape).lineTo(404.18604, 288.86905);
        ((GeneralPath) shape).lineTo(404.18604, 289.84204);
        ((GeneralPath) shape).lineTo(405.13104, 289.84204);
        ((GeneralPath) shape).lineTo(405.13104, 290.35504);
        ((GeneralPath) shape).lineTo(405.86203, 290.35504);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(407.318, 284.062);
        ((GeneralPath) shape).lineTo(407.589, 284.062);
        ((GeneralPath) shape).curveTo(409.27298, 284.062, 409.27298, 282.905,
                409.27298, 281.609);
        ((GeneralPath) shape).curveTo(409.27298, 280.101, 409.128, 279.32,
                407.443, 279.32);
        ((GeneralPath) shape).curveTo(405.96698, 279.32, 405.875, 279.816,
                405.783, 281.63);
        ((GeneralPath) shape).curveTo(405.722, 282.814, 405.701, 282.967,
                405.081, 282.967);
        ((GeneralPath) shape).curveTo(404.455, 282.967, 404.347, 282.781,
                404.347, 281.622);
        ((GeneralPath) shape).curveTo(404.347, 280.803, 404.369, 280.535,
                405.003, 280.535);
        ((GeneralPath) shape).lineTo(405.19, 280.535);
        ((GeneralPath) shape).lineTo(405.19, 279.441);
        ((GeneralPath) shape).lineTo(404.99802, 279.441);
        ((GeneralPath) shape).curveTo(403.428, 279.441, 403.428, 280.383,
                403.428, 281.622);
        ((GeneralPath) shape).curveTo(403.428, 283.045, 403.428, 284.061,
                405.082, 284.061);
        ((GeneralPath) shape).curveTo(406.648, 284.061, 406.601, 283.06,
                406.679, 281.783);
        ((GeneralPath) shape).curveTo(406.72498, 280.875, 406.616, 280.414,
                407.44498, 280.414);
        ((GeneralPath) shape).curveTo(408.11697, 280.414, 408.30096, 280.56,
                408.30096, 281.622);
        ((GeneralPath) shape).curveTo(408.30096, 282.674, 408.25497, 282.967,
                407.59097, 282.967);
        ((GeneralPath) shape).lineTo(407.31998, 282.967);
        ((GeneralPath) shape).lineTo(407.31998, 284.062);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(403.426, 277.586);
        ((GeneralPath) shape).lineTo(403.426, 278.559);
        ((GeneralPath) shape).lineTo(404.26, 278.559);
        ((GeneralPath) shape).lineTo(404.26, 277.586);
        ((GeneralPath) shape).lineTo(403.426, 277.586);
        ((GeneralPath) shape).moveTo(405.13098, 277.586);
        ((GeneralPath) shape).lineTo(405.13098, 278.559);
        ((GeneralPath) shape).lineTo(409.27298, 278.559);
        ((GeneralPath) shape).lineTo(409.27298, 277.586);
        ((GeneralPath) shape).lineTo(405.13098, 277.586);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(409.272, 274.041);
        ((GeneralPath) shape).lineTo(409.272, 273.068);
        ((GeneralPath) shape).lineTo(403.425, 273.068);
        ((GeneralPath) shape).lineTo(403.425, 274.041);
        ((GeneralPath) shape).lineTo(405.723, 274.041);
        ((GeneralPath) shape).lineTo(405.723, 274.063);
        ((GeneralPath) shape).curveTo(405.224, 274.272, 405.13, 274.791,
                405.13, 275.28497);
        ((GeneralPath) shape).curveTo(405.13, 276.67596, 405.898, 276.83698,
                407.091, 276.83698);
        ((GeneralPath) shape).curveTo(408.334, 276.83698, 409.272, 276.83698,
                409.272, 275.28497);
        ((GeneralPath) shape).curveTo(409.272, 274.722, 409.152, 274.28397,
                408.631, 274.041);
        ((GeneralPath) shape).lineTo(409.272, 274.041);
        ((GeneralPath) shape).moveTo(405.879, 274.998);
        ((GeneralPath) shape).curveTo(405.879, 274.15698, 406.22598, 274.041,
                407.092, 274.041);
        ((GeneralPath) shape).curveTo(408.057, 274.041, 408.54202, 274.041,
                408.54202, 274.998);
        ((GeneralPath) shape).curveTo(408.54202, 275.86398, 408.00903,
                275.86398, 407.092, 275.86398);
        ((GeneralPath) shape).curveTo(406.071, 275.86398, 405.879, 275.671,
                405.879, 274.998);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(406.837, 271.281);
        ((GeneralPath) shape).curveTo(406.069, 271.281, 405.879, 271.226,
                405.879, 270.381);
        ((GeneralPath) shape).curveTo(405.879, 269.58102, 405.94598, 269.458,
                406.837, 269.458);
        ((GeneralPath) shape).lineTo(406.837, 271.281);
        ((GeneralPath) shape).moveTo(407.975, 269.458);
        ((GeneralPath) shape).curveTo(408.542, 269.458, 408.542, 269.838,
                408.542, 270.381);
        ((GeneralPath) shape).curveTo(408.542, 271.259, 408.27698, 271.281,
                407.445, 271.281);
        ((GeneralPath) shape).lineTo(407.445, 268.48502);
        ((GeneralPath) shape).curveTo(405.63, 268.48502, 405.131, 268.709,
                405.131, 270.381);
        ((GeneralPath) shape).curveTo(405.131, 272.023, 405.78403, 272.254,
                407.278, 272.254);
        ((GeneralPath) shape).curveTo(408.795, 272.254, 409.272, 271.937,
                409.272, 270.381);
        ((GeneralPath) shape).curveTo(409.272, 269.221, 409.211, 268.48502,
                407.974, 268.48502);
        ((GeneralPath) shape).lineTo(407.974, 269.458);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(407.324, 261.691);
        ((GeneralPath) shape).lineTo(407.324, 263.716);
        ((GeneralPath) shape).lineTo(404.284, 262.715);
        ((GeneralPath) shape).lineTo(404.284, 262.7);
        ((GeneralPath) shape).lineTo(407.324, 261.691);
        ((GeneralPath) shape).moveTo(408.177, 261.44);
        ((GeneralPath) shape).lineTo(409.273, 261.056);
        ((GeneralPath) shape).lineTo(409.273, 259.914);
        ((GeneralPath) shape).lineTo(403.42603, 261.901);
        ((GeneralPath) shape).lineTo(403.42603, 263.56);
        ((GeneralPath) shape).lineTo(409.273, 265.507);
        ((GeneralPath) shape).lineTo(409.273, 264.341);
        ((GeneralPath) shape).lineTo(408.177, 263.974);
        ((GeneralPath) shape).lineTo(408.177, 261.44);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(405.131, 259.603);
        ((GeneralPath) shape).lineTo(409.273, 259.603);
        ((GeneralPath) shape).lineTo(409.273, 258.63);
        ((GeneralPath) shape).lineTo(406.746, 258.63);
        ((GeneralPath) shape).curveTo(406.215, 258.63, 405.88, 258.449, 405.88,
                257.83002);
        ((GeneralPath) shape).curveTo(405.88, 257.334, 406.122, 257.29202,
                406.535, 257.29202);
        ((GeneralPath) shape).lineTo(406.746, 257.29202);
        ((GeneralPath) shape).lineTo(406.746, 256.31903);
        ((GeneralPath) shape).lineTo(406.419, 256.31903);
        ((GeneralPath) shape).curveTo(405.647, 256.31903, 405.13202, 256.54303,
                405.13202, 257.46103);
        ((GeneralPath) shape).curveTo(405.13202, 257.97003, 405.26202,
                258.42004, 405.70502, 258.62903);
        ((GeneralPath) shape).lineTo(405.13202, 258.62903);
        ((GeneralPath) shape).lineTo(405.13202, 259.60303);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(405.131, 255.785);
        ((GeneralPath) shape).lineTo(409.273, 255.785);
        ((GeneralPath) shape).lineTo(409.273, 254.812);
        ((GeneralPath) shape).lineTo(406.96402, 254.812);
        ((GeneralPath) shape).curveTo(406.239, 254.812, 405.87903, 254.647,
                405.87903, 253.80899);
        ((GeneralPath) shape).curveTo(405.87903, 253.241, 406.08304, 253.10999,
                406.59702, 253.10999);
        ((GeneralPath) shape).lineTo(409.273, 253.10999);
        ((GeneralPath) shape).lineTo(409.273, 252.13698);
        ((GeneralPath) shape).lineTo(406.96402, 252.13698);
        ((GeneralPath) shape).curveTo(406.239, 252.13698, 405.87903, 251.98499,
                405.87903, 251.20598);
        ((GeneralPath) shape).curveTo(405.87903, 250.67798, 406.08304,
                250.55698, 406.59702, 250.55698);
        ((GeneralPath) shape).lineTo(409.273, 250.55698);
        ((GeneralPath) shape).lineTo(409.273, 249.58397);
        ((GeneralPath) shape).lineTo(406.504, 249.58397);
        ((GeneralPath) shape).curveTo(405.499, 249.58397, 405.131, 249.92397,
                405.131, 250.95396);
        ((GeneralPath) shape).curveTo(405.131, 251.48996, 405.28702, 252.03796,
                405.855, 252.22296);
        ((GeneralPath) shape).lineTo(405.855, 252.25397);
        ((GeneralPath) shape).curveTo(405.263, 252.36197, 405.131, 252.94696,
                405.131, 253.45697);
        ((GeneralPath) shape).curveTo(405.131, 253.99997, 405.247, 254.58298,
                405.764, 254.81297);
        ((GeneralPath) shape).lineTo(405.131, 254.81297);
        ((GeneralPath) shape).lineTo(405.131, 255.78497);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(405.879, 246.864);
        ((GeneralPath) shape).curveTo(405.879, 246.02, 406.12698, 245.952,
                407.223, 245.952);
        ((GeneralPath) shape).curveTo(408.302, 245.952, 408.542, 246.01999,
                408.542, 246.864);
        ((GeneralPath) shape).curveTo(408.542, 247.707, 408.302, 247.776,
                407.223, 247.776);
        ((GeneralPath) shape).curveTo(406.12698, 247.775, 405.879, 247.707,
                405.879, 246.864);
        ((GeneralPath) shape).moveTo(405.131, 246.864);
        ((GeneralPath) shape).curveTo(405.131, 248.535, 405.63602, 248.748,
                407.216, 248.748);
        ((GeneralPath) shape).curveTo(408.775, 248.748, 409.273, 248.535,
                409.273, 246.864);
        ((GeneralPath) shape).curveTo(409.273, 245.192, 408.77502, 244.979,
                407.216, 244.979);
        ((GeneralPath) shape).curveTo(405.63602, 244.979, 405.131, 245.192,
                405.131, 246.864);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(405.131, 244.251);
        ((GeneralPath) shape).lineTo(409.273, 244.251);
        ((GeneralPath) shape).lineTo(409.273, 243.278);
        ((GeneralPath) shape).lineTo(406.746, 243.278);
        ((GeneralPath) shape).curveTo(406.215, 243.278, 405.88, 243.097,
                405.88, 242.478);
        ((GeneralPath) shape).curveTo(405.88, 241.982, 406.122, 241.94,
                406.535, 241.94);
        ((GeneralPath) shape).lineTo(406.746, 241.94);
        ((GeneralPath) shape).lineTo(406.746, 240.967);
        ((GeneralPath) shape).lineTo(406.419, 240.967);
        ((GeneralPath) shape).curveTo(405.647, 240.967, 405.13202, 241.191,
                405.13202, 242.109);
        ((GeneralPath) shape).curveTo(405.13202, 242.618, 405.26202, 243.068,
                405.70502, 243.277);
        ((GeneralPath) shape).lineTo(405.13202, 243.277);
        ((GeneralPath) shape).lineTo(405.13202, 244.25099);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(404.385, 206.78);
        ((GeneralPath) shape).lineTo(404.385, 204.041);
        ((GeneralPath) shape).lineTo(403.426, 204.041);
        ((GeneralPath) shape).lineTo(403.426, 207.874);
        ((GeneralPath) shape).lineTo(409.272, 207.874);
        ((GeneralPath) shape).lineTo(409.272, 206.78);
        ((GeneralPath) shape).lineTo(406.837, 206.78);
        ((GeneralPath) shape).lineTo(406.837, 204.181);
        ((GeneralPath) shape).lineTo(405.862, 204.181);
        ((GeneralPath) shape).lineTo(405.862, 206.78);
        ((GeneralPath) shape).lineTo(404.385, 206.78);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(405.131, 203.498);
        ((GeneralPath) shape).lineTo(409.273, 203.498);
        ((GeneralPath) shape).lineTo(409.273, 202.525);
        ((GeneralPath) shape).lineTo(406.746, 202.525);
        ((GeneralPath) shape).curveTo(406.215, 202.525, 405.88, 202.344,
                405.88, 201.72499);
        ((GeneralPath) shape).curveTo(405.88, 201.22899, 406.122, 201.187,
                406.535, 201.187);
        ((GeneralPath) shape).lineTo(406.746, 201.187);
        ((GeneralPath) shape).lineTo(406.746, 200.21399);
        ((GeneralPath) shape).lineTo(406.419, 200.21399);
        ((GeneralPath) shape).curveTo(405.647, 200.21399, 405.13202, 200.43799,
                405.13202, 201.35599);
        ((GeneralPath) shape).curveTo(405.13202, 201.86499, 405.26202,
                202.31499, 405.70502, 202.52399);
        ((GeneralPath) shape).lineTo(405.13202, 202.52399);
        ((GeneralPath) shape).lineTo(405.13202, 203.49799);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(405.879, 197.918);
        ((GeneralPath) shape).curveTo(405.879, 197.074, 406.12698, 197.006,
                407.223, 197.006);
        ((GeneralPath) shape).curveTo(408.302, 197.006, 408.542, 197.07399,
                408.542, 197.918);
        ((GeneralPath) shape).curveTo(408.542, 198.761, 408.302, 198.83,
                407.223, 198.83);
        ((GeneralPath) shape).curveTo(406.12698, 198.83, 405.879, 198.761,
                405.879, 197.918);
        ((GeneralPath) shape).moveTo(405.131, 197.918);
        ((GeneralPath) shape).curveTo(405.131, 199.589, 405.63602, 199.802,
                407.216, 199.802);
        ((GeneralPath) shape).curveTo(408.775, 199.802, 409.273, 199.589,
                409.273, 197.918);
        ((GeneralPath) shape).curveTo(409.273, 196.246, 408.77502, 196.033,
                407.216, 196.033);
        ((GeneralPath) shape).curveTo(405.63602, 196.033, 405.131, 196.246,
                405.131, 197.918);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(405.131, 195.184);
        ((GeneralPath) shape).lineTo(409.273, 195.184);
        ((GeneralPath) shape).lineTo(409.273, 194.211);
        ((GeneralPath) shape).lineTo(407.018, 194.211);
        ((GeneralPath) shape).curveTo(406.285, 194.211, 405.879, 194.097,
                405.879, 193.257);
        ((GeneralPath) shape).curveTo(405.879, 192.638, 406.083, 192.509,
                406.675, 192.509);
        ((GeneralPath) shape).lineTo(409.27298, 192.509);
        ((GeneralPath) shape).lineTo(409.27298, 191.536);
        ((GeneralPath) shape).lineTo(406.57498, 191.536);
        ((GeneralPath) shape).curveTo(405.57797, 191.536, 405.132, 191.859,
                405.132, 192.92099);
        ((GeneralPath) shape).curveTo(405.132, 193.48499, 405.194, 193.96599,
                405.789, 194.18);
        ((GeneralPath) shape).lineTo(405.789, 194.211);
        ((GeneralPath) shape).lineTo(405.132, 194.211);
        ((GeneralPath) shape).lineTo(405.132, 195.184);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(405.862, 191.075);
        ((GeneralPath) shape).lineTo(405.862, 190.562);
        ((GeneralPath) shape).lineTo(407.996, 190.562);
        ((GeneralPath) shape).curveTo(409.025, 190.562, 409.272, 190.231,
                409.272, 189.14499);
        ((GeneralPath) shape).curveTo(409.272, 188.07999, 408.89902, 187.76599,
                407.665, 187.76599);
        ((GeneralPath) shape).lineTo(407.665, 188.61699);
        ((GeneralPath) shape).curveTo(408.099, 188.61699, 408.54202, 188.55598,
                408.54202, 189.14499);
        ((GeneralPath) shape).curveTo(408.54202, 189.59, 408.36902, 189.59,
                407.98804, 189.59);
        ((GeneralPath) shape).lineTo(405.86203, 189.59);
        ((GeneralPath) shape).lineTo(405.86203, 187.97299);
        ((GeneralPath) shape).lineTo(405.13104, 187.97299);
        ((GeneralPath) shape).lineTo(405.13104, 189.59);
        ((GeneralPath) shape).lineTo(404.18604, 189.59);
        ((GeneralPath) shape).lineTo(404.18604, 190.563);
        ((GeneralPath) shape).lineTo(405.13104, 190.563);
        ((GeneralPath) shape).lineTo(405.13104, 191.076);
        ((GeneralPath) shape).lineTo(405.86203, 191.076);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(403.426, 183.484);
        ((GeneralPath) shape).lineTo(403.426, 184.578);
        ((GeneralPath) shape).lineTo(409.272, 184.578);
        ((GeneralPath) shape).lineTo(409.272, 180.759);
        ((GeneralPath) shape).lineTo(408.313, 180.759);
        ((GeneralPath) shape).lineTo(408.313, 183.484);
        ((GeneralPath) shape).lineTo(403.426, 183.484);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(406.837, 179.323);
        ((GeneralPath) shape).curveTo(406.069, 179.323, 405.879, 179.268,
                405.879, 178.423);
        ((GeneralPath) shape).curveTo(405.879, 177.623, 405.94598, 177.5,
                406.837, 177.5);
        ((GeneralPath) shape).lineTo(406.837, 179.323);
        ((GeneralPath) shape).moveTo(407.975, 177.5);
        ((GeneralPath) shape).curveTo(408.542, 177.5, 408.542, 177.88, 408.542,
                178.423);
        ((GeneralPath) shape).curveTo(408.542, 179.30101, 408.27698, 179.323,
                407.445, 179.323);
        ((GeneralPath) shape).lineTo(407.445, 176.527);
        ((GeneralPath) shape).curveTo(405.63, 176.527, 405.131, 176.75099,
                405.131, 178.42299);
        ((GeneralPath) shape).curveTo(405.131, 180.06499, 405.78403, 180.29599,
                407.278, 180.29599);
        ((GeneralPath) shape).curveTo(408.795, 180.29599, 409.272, 179.97899,
                409.272, 178.42299);
        ((GeneralPath) shape).curveTo(409.272, 177.262, 409.211, 176.527,
                407.974, 176.527);
        ((GeneralPath) shape).lineTo(407.974, 177.5);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(405.131, 173.52);
        ((GeneralPath) shape).lineTo(405.131, 174.516);
        ((GeneralPath) shape).lineTo(404.9, 174.516);
        ((GeneralPath) shape).curveTo(404.428, 174.516, 404.17398, 174.516,
                404.17398, 173.847);
        ((GeneralPath) shape).lineTo(404.17398, 173.551);
        ((GeneralPath) shape).lineTo(403.426, 173.551);
        ((GeneralPath) shape).lineTo(403.426, 174.187);
        ((GeneralPath) shape).curveTo(403.426, 175.257, 403.828, 175.48799,
                404.783, 175.48799);
        ((GeneralPath) shape).lineTo(405.13098, 175.48799);
        ((GeneralPath) shape).lineTo(405.13098, 176.064);
        ((GeneralPath) shape).lineTo(405.86197, 176.064);
        ((GeneralPath) shape).lineTo(405.86197, 175.48799);
        ((GeneralPath) shape).lineTo(409.27197, 175.48799);
        ((GeneralPath) shape).lineTo(409.27197, 174.51599);
        ((GeneralPath) shape).lineTo(405.86197, 174.51599);
        ((GeneralPath) shape).lineTo(405.86197, 173.51999);
        ((GeneralPath) shape).lineTo(405.13098, 173.51999);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(405.862, 173.529);
        ((GeneralPath) shape).lineTo(405.862, 173.016);
        ((GeneralPath) shape).lineTo(407.996, 173.016);
        ((GeneralPath) shape).curveTo(409.025, 173.016, 409.272, 172.686,
                409.272, 171.599);
        ((GeneralPath) shape).curveTo(409.272, 170.533, 408.89902, 170.22,
                407.665, 170.22);
        ((GeneralPath) shape).lineTo(407.665, 171.071);
        ((GeneralPath) shape).curveTo(408.099, 171.071, 408.54202, 171.01,
                408.54202, 171.599);
        ((GeneralPath) shape).curveTo(408.54202, 172.043, 408.36902, 172.043,
                407.98804, 172.043);
        ((GeneralPath) shape).lineTo(405.86203, 172.043);
        ((GeneralPath) shape).lineTo(405.86203, 170.426);
        ((GeneralPath) shape).lineTo(405.13104, 170.426);
        ((GeneralPath) shape).lineTo(405.13104, 172.043);
        ((GeneralPath) shape).lineTo(404.18604, 172.043);
        ((GeneralPath) shape).lineTo(404.18604, 173.016);
        ((GeneralPath) shape).lineTo(405.13104, 173.016);
        ((GeneralPath) shape).lineTo(405.13104, 173.529);
        ((GeneralPath) shape).lineTo(405.86203, 173.529);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(407.318, 167.237);
        ((GeneralPath) shape).lineTo(407.589, 167.237);
        ((GeneralPath) shape).curveTo(409.27298, 167.237, 409.27298, 166.08,
                409.27298, 164.784);
        ((GeneralPath) shape).curveTo(409.27298, 163.276, 409.128, 162.495,
                407.443, 162.495);
        ((GeneralPath) shape).curveTo(405.96698, 162.495, 405.875, 162.991,
                405.783, 164.805);
        ((GeneralPath) shape).curveTo(405.722, 165.989, 405.701, 166.142,
                405.081, 166.142);
        ((GeneralPath) shape).curveTo(404.455, 166.142, 404.347, 165.955,
                404.347, 164.797);
        ((GeneralPath) shape).curveTo(404.347, 163.979, 404.369, 163.711,
                405.003, 163.711);
        ((GeneralPath) shape).lineTo(405.19, 163.711);
        ((GeneralPath) shape).lineTo(405.19, 162.617);
        ((GeneralPath) shape).lineTo(404.99802, 162.617);
        ((GeneralPath) shape).curveTo(403.428, 162.617, 403.428, 163.559,
                403.428, 164.798);
        ((GeneralPath) shape).curveTo(403.428, 166.22101, 403.428, 167.237,
                405.082, 167.237);
        ((GeneralPath) shape).curveTo(406.648, 167.237, 406.601, 166.236,
                406.679, 164.959);
        ((GeneralPath) shape).curveTo(406.72498, 164.051, 406.616, 163.589,
                407.44498, 163.589);
        ((GeneralPath) shape).curveTo(408.11697, 163.589, 408.30096, 163.735,
                408.30096, 164.797);
        ((GeneralPath) shape).curveTo(408.30096, 165.84999, 408.25497, 166.142,
                407.59097, 166.142);
        ((GeneralPath) shape).lineTo(407.31998, 166.142);
        ((GeneralPath) shape).lineTo(407.31998, 167.237);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(403.426, 160.762);
        ((GeneralPath) shape).lineTo(403.426, 161.735);
        ((GeneralPath) shape).lineTo(404.26, 161.735);
        ((GeneralPath) shape).lineTo(404.26, 160.762);
        ((GeneralPath) shape).lineTo(403.426, 160.762);
        ((GeneralPath) shape).moveTo(405.13098, 160.762);
        ((GeneralPath) shape).lineTo(405.13098, 161.735);
        ((GeneralPath) shape).lineTo(409.27298, 161.735);
        ((GeneralPath) shape).lineTo(409.27298, 160.762);
        ((GeneralPath) shape).lineTo(405.13098, 160.762);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(409.272, 157.214);
        ((GeneralPath) shape).lineTo(409.272, 156.241);
        ((GeneralPath) shape).lineTo(403.425, 156.241);
        ((GeneralPath) shape).lineTo(403.425, 157.214);
        ((GeneralPath) shape).lineTo(405.723, 157.214);
        ((GeneralPath) shape).lineTo(405.723, 157.237);
        ((GeneralPath) shape).curveTo(405.224, 157.446, 405.13, 157.965,
                405.13, 158.459);
        ((GeneralPath) shape).curveTo(405.13, 159.849, 405.898, 160.011,
                407.091, 160.011);
        ((GeneralPath) shape).curveTo(408.334, 160.011, 409.272, 160.011,
                409.272, 158.459);
        ((GeneralPath) shape).curveTo(409.272, 157.896, 409.152, 157.458,
                408.631, 157.214);
        ((GeneralPath) shape).lineTo(409.272, 157.214);
        ((GeneralPath) shape).moveTo(405.879, 158.172);
        ((GeneralPath) shape).curveTo(405.879, 157.33, 406.22598, 157.214,
                407.092, 157.214);
        ((GeneralPath) shape).curveTo(408.057, 157.214, 408.54202, 157.214,
                408.54202, 158.172);
        ((GeneralPath) shape).curveTo(408.54202, 159.038, 408.00903, 159.038,
                407.092, 159.038);
        ((GeneralPath) shape).curveTo(406.071, 159.038, 405.879, 158.845,
                405.879, 158.172);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(406.837, 154.455);
        ((GeneralPath) shape).curveTo(406.069, 154.455, 405.879, 154.40001,
                405.879, 153.55501);
        ((GeneralPath) shape).curveTo(405.879, 152.755, 405.94598, 152.632,
                406.837, 152.632);
        ((GeneralPath) shape).lineTo(406.837, 154.455);
        ((GeneralPath) shape).moveTo(407.975, 152.631);
        ((GeneralPath) shape).curveTo(408.542, 152.631, 408.542, 153.011,
                408.542, 153.554);
        ((GeneralPath) shape).curveTo(408.542, 154.432, 408.27698, 154.454,
                407.445, 154.454);
        ((GeneralPath) shape).lineTo(407.445, 151.65799);
        ((GeneralPath) shape).curveTo(405.63, 151.65799, 405.131, 151.88199,
                405.131, 153.55399);
        ((GeneralPath) shape).curveTo(405.131, 155.19598, 405.78403, 155.42699,
                407.278, 155.42699);
        ((GeneralPath) shape).curveTo(408.795, 155.42699, 409.272, 155.10999,
                409.272, 153.55399);
        ((GeneralPath) shape).curveTo(409.272, 152.39299, 409.211, 151.65799,
                407.974, 151.65799);
        ((GeneralPath) shape).lineTo(407.974, 152.631);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(407.324, 144.865);
        ((GeneralPath) shape).lineTo(407.324, 146.89);
        ((GeneralPath) shape).lineTo(404.284, 145.88899);
        ((GeneralPath) shape).lineTo(404.284, 145.874);
        ((GeneralPath) shape).lineTo(407.324, 144.86499);
        ((GeneralPath) shape).moveTo(408.177, 144.61499);
        ((GeneralPath) shape).lineTo(409.273, 144.23099);
        ((GeneralPath) shape).lineTo(409.273, 143.08899);
        ((GeneralPath) shape).lineTo(403.42603, 145.07599);
        ((GeneralPath) shape).lineTo(403.42603, 146.734);
        ((GeneralPath) shape).lineTo(409.273, 148.681);
        ((GeneralPath) shape).lineTo(409.273, 147.51399);
        ((GeneralPath) shape).lineTo(408.177, 147.14699);
        ((GeneralPath) shape).lineTo(408.177, 144.61499);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(405.131, 142.776);
        ((GeneralPath) shape).lineTo(409.273, 142.776);
        ((GeneralPath) shape).lineTo(409.273, 141.803);
        ((GeneralPath) shape).lineTo(406.746, 141.803);
        ((GeneralPath) shape).curveTo(406.215, 141.803, 405.88, 141.622,
                405.88, 141.00299);
        ((GeneralPath) shape).curveTo(405.88, 140.50699, 406.122, 140.465,
                406.535, 140.465);
        ((GeneralPath) shape).lineTo(406.746, 140.465);
        ((GeneralPath) shape).lineTo(406.746, 139.49199);
        ((GeneralPath) shape).lineTo(406.419, 139.49199);
        ((GeneralPath) shape).curveTo(405.647, 139.49199, 405.13202, 139.71599,
                405.13202, 140.63399);
        ((GeneralPath) shape).curveTo(405.13202, 141.14299, 405.26202,
                141.59299, 405.70502, 141.80199);
        ((GeneralPath) shape).lineTo(405.13202, 141.80199);
        ((GeneralPath) shape).lineTo(405.13202, 142.77599);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(405.131, 138.959);
        ((GeneralPath) shape).lineTo(409.273, 138.959);
        ((GeneralPath) shape).lineTo(409.273, 137.986);
        ((GeneralPath) shape).lineTo(406.96402, 137.986);
        ((GeneralPath) shape).curveTo(406.239, 137.986, 405.87903, 137.81999,
                405.87903, 136.98299);
        ((GeneralPath) shape).curveTo(405.87903, 136.415, 406.08304, 136.28398,
                406.59702, 136.28398);
        ((GeneralPath) shape).lineTo(409.273, 136.28398);
        ((GeneralPath) shape).lineTo(409.273, 135.31097);
        ((GeneralPath) shape).lineTo(406.96402, 135.31097);
        ((GeneralPath) shape).curveTo(406.239, 135.31097, 405.87903, 135.15898,
                405.87903, 134.37997);
        ((GeneralPath) shape).curveTo(405.87903, 133.85197, 406.08304,
                133.72998, 406.59702, 133.72998);
        ((GeneralPath) shape).lineTo(409.273, 133.72998);
        ((GeneralPath) shape).lineTo(409.273, 132.75798);
        ((GeneralPath) shape).lineTo(406.504, 132.75798);
        ((GeneralPath) shape).curveTo(405.499, 132.75798, 405.131, 133.09798,
                405.131, 134.12698);
        ((GeneralPath) shape).curveTo(405.131, 134.66298, 405.28702, 135.21199,
                405.855, 135.39598);
        ((GeneralPath) shape).lineTo(405.855, 135.42598);
        ((GeneralPath) shape).curveTo(405.263, 135.53497, 405.131, 136.11998,
                405.131, 136.62898);
        ((GeneralPath) shape).curveTo(405.131, 137.17198, 405.247, 137.75499,
                405.764, 137.98499);
        ((GeneralPath) shape).lineTo(405.131, 137.98499);
        ((GeneralPath) shape).lineTo(405.131, 138.95898);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(405.879, 130.037);
        ((GeneralPath) shape).curveTo(405.879, 129.194, 406.12698, 129.125,
                407.223, 129.125);
        ((GeneralPath) shape).curveTo(408.302, 129.125, 408.542, 129.193,
                408.542, 130.037);
        ((GeneralPath) shape).curveTo(408.542, 130.881, 408.302, 130.949,
                407.223, 130.949);
        ((GeneralPath) shape).curveTo(406.12698, 130.949, 405.879, 130.88101,
                405.879, 130.037);
        ((GeneralPath) shape).moveTo(405.131, 130.037);
        ((GeneralPath) shape).curveTo(405.131, 131.709, 405.63602, 131.922,
                407.216, 131.922);
        ((GeneralPath) shape).curveTo(408.775, 131.922, 409.273, 131.709,
                409.273, 130.037);
        ((GeneralPath) shape).curveTo(409.273, 128.366, 408.77502, 128.153,
                407.216, 128.153);
        ((GeneralPath) shape).curveTo(405.63602, 128.153, 405.131, 128.366,
                405.131, 130.037);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(405.131, 127.425);
        ((GeneralPath) shape).lineTo(409.273, 127.425);
        ((GeneralPath) shape).lineTo(409.273, 126.452);
        ((GeneralPath) shape).lineTo(406.746, 126.452);
        ((GeneralPath) shape).curveTo(406.215, 126.452, 405.88, 126.271,
                405.88, 125.652);
        ((GeneralPath) shape).curveTo(405.88, 125.156, 406.122, 125.114,
                406.535, 125.114);
        ((GeneralPath) shape).lineTo(406.746, 125.114);
        ((GeneralPath) shape).lineTo(406.746, 124.141);
        ((GeneralPath) shape).lineTo(406.419, 124.141);
        ((GeneralPath) shape).curveTo(405.647, 124.141, 405.13202, 124.365,
                405.13202, 125.283);
        ((GeneralPath) shape).curveTo(405.13202, 125.792, 405.26202, 126.242,
                405.70502, 126.451);
        ((GeneralPath) shape).lineTo(405.13202, 126.451);
        ((GeneralPath) shape).lineTo(405.13202, 127.424995);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_405;
        g.setTransform(defaultTransform__0_405);
        g.setClip(clip__0_405);
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
        return 252;
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
