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
public class VTOLCatalystLogo {
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(579.095, 351.475);
        ((GeneralPath) shape).curveTo(579.095, 349.651, 577.654, 348.173,
                575.87897, 348.173);
        ((GeneralPath) shape).lineTo(533.19196, 348.173);
        ((GeneralPath) shape).curveTo(531.415, 348.173, 529.975, 349.652,
                529.975, 351.475);
        ((GeneralPath) shape).lineTo(529.975, 372.941);
        ((GeneralPath) shape).curveTo(529.975, 374.764, 531.414, 376.242,
                533.19196, 376.242);
        ((GeneralPath) shape).lineTo(575.87897, 376.242);
        ((GeneralPath) shape).curveTo(577.65295, 376.242, 579.095, 374.763,
                579.095, 372.941);
        ((GeneralPath) shape).lineTo(579.095, 351.475);
        g.setPaint(paint);
        g.fill(shape);
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
        paint = new Color(35, 31, 32, 255);
        stroke = new BasicStroke(2.005f, 0, 0, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(579.095, 351.475);
        ((GeneralPath) shape).curveTo(579.095, 349.651, 577.65295, 348.172,
                575.87897, 348.172);
        ((GeneralPath) shape).lineTo(533.19196, 348.172);
        ((GeneralPath) shape).curveTo(531.415, 348.172, 529.975, 349.651,
                529.975, 351.475);
        ((GeneralPath) shape).lineTo(529.975, 372.941);
        ((GeneralPath) shape).curveTo(529.975, 374.764, 531.414, 376.242,
                533.19196, 376.242);
        ((GeneralPath) shape).lineTo(575.87897, 376.242);
        ((GeneralPath) shape).curveTo(577.652, 376.242, 579.095, 374.763,
                579.095, 372.941);
        ((GeneralPath) shape).lineTo(579.095, 351.475);
        ((GeneralPath) shape).closePath();
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(536.601, 367.076);
        ((GeneralPath) shape).curveTo(536.438, 368.874, 535.599, 369.672,
                534.107, 369.672);
        ((GeneralPath) shape).curveTo(532.533, 369.672, 531.675, 368.855,
                531.55, 367.076);
        ((GeneralPath) shape).curveTo(531.471, 366.012, 531.429, 364.94998,
                531.429, 363.866);
        ((GeneralPath) shape).curveTo(531.429, 363.395, 531.429, 362.924,
                531.45, 362.456);
        ((GeneralPath) shape).curveTo(531.45, 362.456, 531.471, 358.857,
                531.551, 357.85498);
        ((GeneralPath) shape).curveTo(531.676, 356.07697, 532.533, 355.23898,
                534.10803, 355.23898);
        ((GeneralPath) shape).curveTo(535.60004, 355.23898, 536.439, 356.076,
                536.60205, 357.85498);
        ((GeneralPath) shape).curveTo(536.62305, 358.18298, 536.642, 359.43,
                536.642, 359.94098);
        ((GeneralPath) shape).lineTo(535.04803, 359.94098);
        ((GeneralPath) shape).curveTo(535.04803, 359.40897, 535.007, 358.14197,
                534.98706, 357.81598);
        ((GeneralPath) shape).curveTo(534.9051, 357.01797, 534.63904,
                356.66998, 534.10706, 356.66998);
        ((GeneralPath) shape).curveTo(533.6371, 356.66998, 533.37103,
                357.03897, 533.28906, 357.81598);
        ((GeneralPath) shape).curveTo(533.18805, 358.818, 533.18805, 362.45697,
                533.18805, 362.45697);
        ((GeneralPath) shape).curveTo(533.16705, 363.04797, 533.16705,
                363.66196, 533.16705, 364.27396);
        ((GeneralPath) shape).curveTo(533.16705, 365.23596, 533.18805,
                366.21796, 533.28906, 367.13797);
        ((GeneralPath) shape).curveTo(533.37103, 367.91397, 533.63806,
                368.28296, 534.10706, 368.28296);
        ((GeneralPath) shape).curveTo(534.63904, 368.28296, 534.905, 367.91495,
                534.98706, 367.13797);
        ((GeneralPath) shape).curveTo(535.0071, 366.80997, 535.04803,
                365.46198, 535.04803, 364.92996);
        ((GeneralPath) shape).lineTo(536.642, 364.92996);
        ((GeneralPath) shape).curveTo(536.641, 365.257, 536.621, 366.768,
                536.601, 367.076);
        g.setPaint(paint);
        g.fill(shape);
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(541.176, 369.509);
        ((GeneralPath) shape).lineTo(541.176, 363.907);
        ((GeneralPath) shape).lineTo(539.398, 363.907);
        ((GeneralPath) shape).lineTo(539.398, 369.509);
        ((GeneralPath) shape).lineTo(537.66003, 369.509);
        ((GeneralPath) shape).lineTo(537.66003, 361.025);
        ((GeneralPath) shape).curveTo(537.66003, 361.025, 537.66003, 359.064,
                537.742, 358.163);
        ((GeneralPath) shape).curveTo(537.886, 356.54898, 539.09, 355.40298,
                540.645, 355.40298);
        ((GeneralPath) shape).lineTo(542.95404, 355.40298);
        ((GeneralPath) shape).lineTo(542.95404, 369.50897);
        ((GeneralPath) shape).lineTo(541.176, 369.50897);
        ((GeneralPath) shape).moveTo(541.217, 356.875);
        ((GeneralPath) shape).lineTo(540.64496, 356.875);
        ((GeneralPath) shape).curveTo(540.17395, 356.875, 539.56396, 357.203,
                539.48, 357.918);
        ((GeneralPath) shape).curveTo(539.398, 358.553, 539.398, 359.779,
                539.398, 360.41);
        ((GeneralPath) shape).lineTo(539.398, 362.476);
        ((GeneralPath) shape).lineTo(541.217, 362.476);
        ((GeneralPath) shape).lineTo(541.217, 356.875);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.fill(shape);
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(552.465, 369.509);
        ((GeneralPath) shape).lineTo(552.465, 363.907);
        ((GeneralPath) shape).lineTo(550.68604, 363.907);
        ((GeneralPath) shape).lineTo(550.68604, 369.509);
        ((GeneralPath) shape).lineTo(548.94904, 369.509);
        ((GeneralPath) shape).lineTo(548.94904, 361.025);
        ((GeneralPath) shape).curveTo(548.94904, 361.025, 548.94904, 359.064,
                549.03204, 358.163);
        ((GeneralPath) shape).curveTo(549.1741, 356.54898, 550.38104,
                355.40298, 551.93304, 355.40298);
        ((GeneralPath) shape).lineTo(554.24506, 355.40298);
        ((GeneralPath) shape).lineTo(554.24506, 369.50897);
        ((GeneralPath) shape).lineTo(552.465, 369.50897);
        ((GeneralPath) shape).moveTo(552.507, 356.875);
        ((GeneralPath) shape).lineTo(551.93304, 356.875);
        ((GeneralPath) shape).curveTo(551.46204, 356.875, 550.8511, 357.203,
                550.76807, 357.918);
        ((GeneralPath) shape).curveTo(550.6861, 358.553, 550.6861, 359.779,
                550.6861, 360.41);
        ((GeneralPath) shape).lineTo(550.6861, 362.476);
        ((GeneralPath) shape).lineTo(552.5071, 362.476);
        ((GeneralPath) shape).lineTo(552.5071, 356.875);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.fill(shape);
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(546.613, 356.875);
        ((GeneralPath) shape).lineTo(546.613, 367.219);
        ((GeneralPath) shape).curveTo(546.613, 367.934, 547.061, 368.23898,
                547.511, 368.23898);
        ((GeneralPath) shape).lineTo(547.962, 368.23898);
        ((GeneralPath) shape).lineTo(547.962, 369.65097);
        ((GeneralPath) shape).lineTo(547.511, 369.65097);
        ((GeneralPath) shape).curveTo(545.919, 369.65097, 544.855, 368.95697,
                544.855, 367.36096);
        ((GeneralPath) shape).lineTo(544.855, 356.87396);
        ((GeneralPath) shape).lineTo(542.873, 356.87396);
        ((GeneralPath) shape).lineTo(542.873, 355.40198);
        ((GeneralPath) shape).lineTo(548.617, 355.40198);
        ((GeneralPath) shape).lineTo(548.617, 356.87396);
        ((GeneralPath) shape).lineTo(546.613, 356.87396);
        g.setPaint(paint);
        g.fill(shape);
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(568.651, 369.671);
        ((GeneralPath) shape).curveTo(567.138, 369.671, 566.055, 368.83298,
                566.055, 367.03598);
        ((GeneralPath) shape).lineTo(566.055, 364.93);
        ((GeneralPath) shape).lineTo(567.669, 364.93);
        ((GeneralPath) shape).lineTo(567.669, 367.115);
        ((GeneralPath) shape).curveTo(567.669, 367.935, 568.139, 368.261,
                568.65, 368.261);
        ((GeneralPath) shape).curveTo(569.12, 368.261, 569.59, 367.935, 569.59,
                367.115);
        ((GeneralPath) shape).lineTo(569.59, 365.339);
        ((GeneralPath) shape).curveTo(569.59, 363.172, 566.11304, 362.026,
                566.11304, 359.369);
        ((GeneralPath) shape).lineTo(566.11304, 357.856);
        ((GeneralPath) shape).curveTo(566.11304, 356.05698, 567.11505, 355.24,
                568.69006, 355.24);
        ((GeneralPath) shape).curveTo(570.1811, 355.24, 571.16406, 356.05698,
                571.16406, 357.856);
        ((GeneralPath) shape).lineTo(571.16406, 359.554);
        ((GeneralPath) shape).lineTo(569.5691, 359.554);
        ((GeneralPath) shape).lineTo(569.5691, 357.81497);
        ((GeneralPath) shape).curveTo(569.5691, 357.01697, 569.2011, 356.66998,
                568.69006, 356.66998);
        ((GeneralPath) shape).curveTo(568.21906, 356.66998, 567.8711,
                357.01697, 567.8711, 357.81497);
        ((GeneralPath) shape).lineTo(567.8711, 359.34998);
        ((GeneralPath) shape).curveTo(567.8711, 361.02496, 571.3261, 362.61996,
                571.3261, 365.27698);
        ((GeneralPath) shape).lineTo(571.3261, 367.03598);
        ((GeneralPath) shape).curveTo(571.328, 368.833, 570.245, 369.671,
                568.651, 369.671);
        g.setPaint(paint);
        g.fill(shape);
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(575.375, 356.875);
        ((GeneralPath) shape).lineTo(575.375, 367.219);
        ((GeneralPath) shape).curveTo(575.375, 367.934, 575.825, 368.23898,
                576.272, 368.23898);
        ((GeneralPath) shape).lineTo(576.722, 368.23898);
        ((GeneralPath) shape).lineTo(576.722, 369.65097);
        ((GeneralPath) shape).lineTo(576.272, 369.65097);
        ((GeneralPath) shape).curveTo(574.67896, 369.65097, 573.615, 368.95697,
                573.615, 367.36096);
        ((GeneralPath) shape).lineTo(573.615, 356.87396);
        ((GeneralPath) shape).lineTo(571.635, 356.87396);
        ((GeneralPath) shape).lineTo(571.635, 355.40198);
        ((GeneralPath) shape).lineTo(577.378, 355.40198);
        ((GeneralPath) shape).lineTo(577.378, 356.87396);
        ((GeneralPath) shape).lineTo(575.375, 356.87396);
        g.setPaint(paint);
        g.fill(shape);
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(552.531, 372.635);
        ((GeneralPath) shape).curveTo(552.531, 373.074, 552.442, 373.389,
                552.222, 373.58502);
        ((GeneralPath) shape).curveTo(552.003, 373.78104, 551.701, 373.838,
                551.412, 373.838);
        ((GeneralPath) shape).curveTo(551.147, 373.838, 550.86, 373.78403,
                550.68097, 373.669);
        ((GeneralPath) shape).lineTo(550.797, 373.271);
        ((GeneralPath) shape).curveTo(550.93, 373.35, 551.154, 373.432,
                551.407, 373.432);
        ((GeneralPath) shape).curveTo(551.753, 373.432, 552.011, 373.249,
                552.011, 372.797);
        ((GeneralPath) shape).lineTo(552.011, 372.618);
        ((GeneralPath) shape).lineTo(552.002, 372.618);
        ((GeneralPath) shape).curveTo(551.88104, 372.80002, 551.666, 372.92502,
                551.39, 372.92502);
        ((GeneralPath) shape).curveTo(550.869, 372.92502, 550.499, 372.49503,
                550.499, 371.903);
        ((GeneralPath) shape).curveTo(550.499, 371.213, 550.945, 370.79602,
                551.44904, 370.79602);
        ((GeneralPath) shape).curveTo(551.768, 370.79602, 551.958, 370.95203,
                552.057, 371.122);
        ((GeneralPath) shape).lineTo(552.065, 371.122);
        ((GeneralPath) shape).lineTo(552.087, 370.844);
        ((GeneralPath) shape).lineTo(552.548, 370.844);
        ((GeneralPath) shape).curveTo(552.53796, 370.984, 552.52795, 371.15698,
                552.52795, 371.447);
        ((GeneralPath) shape).lineTo(552.52795, 372.635);
        ((GeneralPath) shape).moveTo(552.004, 371.683);
        ((GeneralPath) shape).curveTo(552.004, 371.634, 551.999, 371.582,
                551.98505, 371.539);
        ((GeneralPath) shape).curveTo(551.93005, 371.346, 551.77905, 371.2,
                551.55505, 371.2);
        ((GeneralPath) shape).curveTo(551.25806, 371.2, 551.0361, 371.457,
                551.0361, 371.885);
        ((GeneralPath) shape).curveTo(551.0361, 372.241, 551.21906, 372.531,
                551.55005, 372.531);
        ((GeneralPath) shape).curveTo(551.74805, 372.531, 551.921, 372.403,
                551.98303, 372.212);
        ((GeneralPath) shape).curveTo(551.99506, 372.155, 552.004, 372.082,
                552.004, 372.02402);
        ((GeneralPath) shape).lineTo(552.004, 371.683);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.fill(shape);
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(554.559, 372.436);
        ((GeneralPath) shape).curveTo(554.559, 372.627, 554.56604, 372.811,
                554.58704, 372.94);
        ((GeneralPath) shape).lineTo(554.109, 372.94);
        ((GeneralPath) shape).lineTo(554.075, 372.708);
        ((GeneralPath) shape).lineTo(554.062, 372.708);
        ((GeneralPath) shape).curveTo(553.933, 372.871, 553.714, 372.987,
                553.44104, 372.987);
        ((GeneralPath) shape).curveTo(553.01904, 372.987, 552.783, 372.682,
                552.783, 372.362);
        ((GeneralPath) shape).curveTo(552.783, 371.83398, 553.253, 371.565,
                554.028, 371.571);
        ((GeneralPath) shape).lineTo(554.028, 371.536);
        ((GeneralPath) shape).curveTo(554.028, 371.397, 553.972, 371.17,
                553.601, 371.17);
        ((GeneralPath) shape).curveTo(553.393, 371.17, 553.18, 371.234,
                553.038, 371.324);
        ((GeneralPath) shape).lineTo(552.934, 370.98102);
        ((GeneralPath) shape).curveTo(553.088, 370.88602, 553.36005, 370.79602,
                553.693, 370.79602);
        ((GeneralPath) shape).curveTo(554.365, 370.79602, 554.559, 371.22202,
                554.559, 371.678);
        ((GeneralPath) shape).lineTo(554.559, 372.436);
        ((GeneralPath) shape).moveTo(554.04, 371.914);
        ((GeneralPath) shape).curveTo(553.66595, 371.906, 553.31, 371.988,
                553.31, 372.307);
        ((GeneralPath) shape).curveTo(553.31, 372.515, 553.442, 372.608,
                553.61, 372.608);
        ((GeneralPath) shape).curveTo(553.821, 372.608, 553.977, 372.471,
                554.024, 372.32);
        ((GeneralPath) shape).curveTo(554.037, 372.28, 554.041, 372.237,
                554.041, 372.20502);
        ((GeneralPath) shape).lineTo(554.041, 371.914);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.fill(shape);
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(554.944, 371.465);
        ((GeneralPath) shape).curveTo(554.944, 371.22598, 554.941, 371.024,
                554.92694, 370.842);
        ((GeneralPath) shape).lineTo(555.38293, 370.842);
        ((GeneralPath) shape).lineTo(555.40393, 371.152);
        ((GeneralPath) shape).lineTo(555.4179, 371.152);
        ((GeneralPath) shape).curveTo(555.5199, 370.99002, 555.71094,
                370.79602, 556.0639, 370.79602);
        ((GeneralPath) shape).curveTo(556.3399, 370.79602, 556.5529, 370.95102,
                556.6459, 371.182);
        ((GeneralPath) shape).lineTo(556.65186, 371.182);
        ((GeneralPath) shape).curveTo(556.7258, 371.06702, 556.81287, 370.98,
                556.91284, 370.919);
        ((GeneralPath) shape).curveTo(557.02985, 370.838, 557.16187, 370.795,
                557.33484, 370.795);
        ((GeneralPath) shape).curveTo(557.68384, 370.795, 558.03687, 371.03,
                558.03687, 371.70502);
        ((GeneralPath) shape).lineTo(558.03687, 372.941);
        ((GeneralPath) shape).lineTo(557.51984, 372.941);
        ((GeneralPath) shape).lineTo(557.51984, 371.78);
        ((GeneralPath) shape).curveTo(557.51984, 371.432, 557.39984, 371.226,
                557.14386, 371.226);
        ((GeneralPath) shape).curveTo(556.96387, 371.226, 556.8309, 371.354,
                556.7729, 371.50403);
        ((GeneralPath) shape).curveTo(556.76086, 371.55704, 556.7489,
                371.62103, 556.7489, 371.683);
        ((GeneralPath) shape).lineTo(556.7489, 372.941);
        ((GeneralPath) shape).lineTo(556.2319, 372.941);
        ((GeneralPath) shape).lineTo(556.2319, 371.72702);
        ((GeneralPath) shape).curveTo(556.2319, 371.433, 556.11584, 371.22702,
                555.8689, 371.22702);
        ((GeneralPath) shape).curveTo(555.6739, 371.22702, 555.5379, 371.38202,
                555.4909, 371.52902);
        ((GeneralPath) shape).curveTo(555.4699, 371.579, 555.4609, 371.639,
                555.4609, 371.69904);
        ((GeneralPath) shape).lineTo(555.4609, 372.94104);
        ((GeneralPath) shape).lineTo(554.9429, 372.94104);
        ((GeneralPath) shape).lineTo(554.9429, 371.465);
        g.setPaint(paint);
        g.fill(shape);
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(558.801, 372.039);
        ((GeneralPath) shape).curveTo(558.814, 372.419, 559.112, 372.581,
                559.447, 372.581);
        ((GeneralPath) shape).curveTo(559.692, 372.581, 559.86804, 372.548,
                560.029, 372.487);
        ((GeneralPath) shape).lineTo(560.105, 372.854);
        ((GeneralPath) shape).curveTo(559.92395, 372.926, 559.67395, 372.986,
                559.373, 372.986);
        ((GeneralPath) shape).curveTo(558.69196, 372.986, 558.29, 372.564,
                558.29, 371.92398);
        ((GeneralPath) shape).curveTo(558.29, 371.34198, 558.64496, 370.796,
                559.316, 370.796);
        ((GeneralPath) shape).curveTo(560.00195, 370.796, 560.22, 371.354,
                560.22, 371.815);
        ((GeneralPath) shape).curveTo(560.22, 371.913, 560.212, 371.991,
                560.20197, 372.038);
        ((GeneralPath) shape).lineTo(558.801, 372.038);
        ((GeneralPath) shape).moveTo(559.722, 371.669);
        ((GeneralPath) shape).curveTo(559.726, 371.475, 559.641, 371.156,
                559.286, 371.156);
        ((GeneralPath) shape).curveTo(558.96, 371.156, 558.822, 371.452,
                558.801, 371.669);
        ((GeneralPath) shape).lineTo(559.722, 371.669);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.fill(shape);
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
        paint = new Color(255, 255, 255, 255);
        shape = new Rectangle2D.Double(564.072021484375, 369.8800048828125,
                0.5329999923706055, 3.059000015258789);
        g.setPaint(paint);
        g.fill(shape);
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(566.637, 372.436);
        ((GeneralPath) shape).curveTo(566.637, 372.627, 566.643, 372.811,
                566.666, 372.94);
        ((GeneralPath) shape).lineTo(566.187, 372.94);
        ((GeneralPath) shape).lineTo(566.15204, 372.708);
        ((GeneralPath) shape).lineTo(566.14, 372.708);
        ((GeneralPath) shape).curveTo(566.013, 372.871, 565.792, 372.987,
                565.52, 372.987);
        ((GeneralPath) shape).curveTo(565.096, 372.987, 564.861, 372.682,
                564.861, 372.362);
        ((GeneralPath) shape).curveTo(564.861, 371.83398, 565.331, 371.565,
                566.10504, 371.571);
        ((GeneralPath) shape).lineTo(566.10504, 371.536);
        ((GeneralPath) shape).curveTo(566.10504, 371.397, 566.051, 371.17,
                565.68005, 371.17);
        ((GeneralPath) shape).curveTo(565.4731, 371.17, 565.25806, 371.234,
                565.11707, 371.324);
        ((GeneralPath) shape).lineTo(565.0121, 370.98102);
        ((GeneralPath) shape).curveTo(565.1671, 370.88602, 565.4381, 370.79602,
                565.77106, 370.79602);
        ((GeneralPath) shape).curveTo(566.44403, 370.79602, 566.63806,
                371.22202, 566.63806, 371.678);
        ((GeneralPath) shape).lineTo(566.63806, 372.436);
        ((GeneralPath) shape).moveTo(566.119, 371.914);
        ((GeneralPath) shape).curveTo(565.742, 371.906, 565.387, 371.988,
                565.387, 372.307);
        ((GeneralPath) shape).curveTo(565.387, 372.515, 565.52, 372.608,
                565.689, 372.608);
        ((GeneralPath) shape).curveTo(565.898, 372.608, 566.05505, 372.471,
                566.10205, 372.32);
        ((GeneralPath) shape).curveTo(566.1141, 372.28, 566.12006, 372.237,
                566.12006, 372.20502);
        ((GeneralPath) shape).lineTo(566.12006, 371.914);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.fill(shape);
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(567.021, 369.881);
        ((GeneralPath) shape).lineTo(567.551, 369.881);
        ((GeneralPath) shape).lineTo(567.551, 371.13);
        ((GeneralPath) shape).lineTo(567.56, 371.13);
        ((GeneralPath) shape).curveTo(567.691, 370.928, 567.917, 370.797,
                568.234, 370.797);
        ((GeneralPath) shape).curveTo(568.745, 370.797, 569.112, 371.221,
                569.107, 371.859);
        ((GeneralPath) shape).curveTo(569.107, 372.612, 568.62897, 372.987,
                568.157, 372.987);
        ((GeneralPath) shape).curveTo(567.886, 372.987, 567.641, 372.884,
                567.492, 372.628);
        ((GeneralPath) shape).lineTo(567.484, 372.628);
        ((GeneralPath) shape).lineTo(567.458, 372.94);
        ((GeneralPath) shape).lineTo(567.006, 372.94);
        ((GeneralPath) shape).curveTo(567.013, 372.797, 567.022, 372.564,
                567.022, 372.349);
        ((GeneralPath) shape).lineTo(567.022, 369.881);
        ((GeneralPath) shape).moveTo(567.552, 372.074);
        ((GeneralPath) shape).curveTo(567.552, 372.117, 567.557, 372.16,
                567.567, 372.197);
        ((GeneralPath) shape).curveTo(567.62103, 372.409, 567.80804, 372.569,
                568.036, 372.569);
        ((GeneralPath) shape).curveTo(568.369, 372.569, 568.57, 372.302,
                568.57, 371.88);
        ((GeneralPath) shape).curveTo(568.57, 371.51, 568.392, 371.209, 568.04,
                371.209);
        ((GeneralPath) shape).curveTo(567.82495, 371.209, 567.63, 371.363,
                567.571, 371.59702);
        ((GeneralPath) shape).curveTo(567.561, 371.634, 567.552, 371.682,
                567.552, 371.734);
        ((GeneralPath) shape).lineTo(567.552, 372.074);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.fill(shape);
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(569.345, 372.453);
        ((GeneralPath) shape).curveTo(569.46594, 372.527, 569.693, 372.603,
                569.883, 372.603);
        ((GeneralPath) shape).curveTo(570.11597, 372.603, 570.217, 372.509,
                570.217, 372.373);
        ((GeneralPath) shape).curveTo(570.217, 372.22897, 570.131, 372.15598,
                569.873, 372.06598);
        ((GeneralPath) shape).curveTo(569.466, 371.925, 569.29297, 371.69998,
                569.298, 371.45398);
        ((GeneralPath) shape).curveTo(569.298, 371.08398, 569.60297, 370.79797,
                570.08997, 370.79797);
        ((GeneralPath) shape).curveTo(570.32196, 370.79797, 570.524, 370.856,
                570.646, 370.92096);
        ((GeneralPath) shape).lineTo(570.541, 371.29697);
        ((GeneralPath) shape).curveTo(570.452, 371.24396, 570.283, 371.17596,
                570.10004, 371.17596);
        ((GeneralPath) shape).curveTo(569.91205, 371.17596, 569.806, 371.26495,
                569.806, 371.39395);
        ((GeneralPath) shape).curveTo(569.806, 371.52994, 569.90704, 371.59195,
                570.171, 371.68796);
        ((GeneralPath) shape).curveTo(570.552, 371.82495, 570.729, 372.01794,
                570.732, 372.32996);
        ((GeneralPath) shape).curveTo(570.732, 372.70795, 570.435, 372.98795,
                569.88, 372.98795);
        ((GeneralPath) shape).curveTo(569.625, 372.98795, 569.39703, 372.92496,
                569.241, 372.83795);
        ((GeneralPath) shape).lineTo(569.345, 372.453);
        g.setPaint(paint);
        g.fill(shape);
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(564.887, 358.541);
        ((GeneralPath) shape).lineTo(564.887, 355.41898);
        ((GeneralPath) shape).lineTo(563.78204, 355.41898);
        ((GeneralPath) shape).lineTo(563.14905, 356.58798);
        ((GeneralPath) shape).lineTo(563.14905, 361.39398);
        ((GeneralPath) shape).curveTo(563.14905, 361.39398, 563.14905,
                363.64297, 563.046, 364.541);
        ((GeneralPath) shape).curveTo(562.986, 365.23398, 562.69904, 365.602,
                562.252, 365.602);
        ((GeneralPath) shape).curveTo(561.779, 365.602, 561.515, 365.23398,
                561.432, 364.541);
        ((GeneralPath) shape).curveTo(561.374, 364.038, 561.35004, 363.11798,
                561.33704, 362.4);
        ((GeneralPath) shape).curveTo(561.33105, 361.887, 561.33105, 361.504,
                561.33105, 361.504);
        ((GeneralPath) shape).lineTo(561.32904, 359.781);
        ((GeneralPath) shape).curveTo(561.32904, 359.781, 560.35504, 361.66202,
                559.99506, 361.904);
        ((GeneralPath) shape).curveTo(559.62805, 362.15298, 559.66907, 362.425,
                559.66907, 362.425);
        ((GeneralPath) shape).curveTo(559.66907, 362.425, 559.63007, 364.15598,
                559.67706, 364.632);
        ((GeneralPath) shape).curveTo(559.79803, 365.91998, 560.3301, 366.78,
                561.37103, 367.046);
        ((GeneralPath) shape).lineTo(561.37103, 367.67398);
        ((GeneralPath) shape).curveTo(561.366, 367.68, 561.36005, 367.68497,
                561.356, 367.689);
        ((GeneralPath) shape).lineTo(561.356, 373.25598);
        ((GeneralPath) shape).lineTo(563.094, 371.52);
        ((GeneralPath) shape).lineTo(563.094, 371.51398);
        ((GeneralPath) shape).lineTo(563.11, 371.49698);
        ((GeneralPath) shape).lineTo(563.11, 367.046);
        ((GeneralPath) shape).curveTo(564.13196, 366.78098, 564.685, 365.91998,
                564.867, 364.632);
        ((GeneralPath) shape).curveTo(564.888, 363.754, 564.888, 361.50497,
                564.888, 361.50497);
        ((GeneralPath) shape).lineTo(564.888, 358.541);
        ((GeneralPath) shape).lineTo(564.887, 358.541);
        g.setPaint(paint);
        g.fill(shape);
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(557.428, 360.925);
        ((GeneralPath) shape).lineTo(555.689, 358.006);
        ((GeneralPath) shape).lineTo(555.689, 369.509);
        ((GeneralPath) shape).lineTo(560.084, 369.509);
        ((GeneralPath) shape).lineTo(560.084, 368.077);
        ((GeneralPath) shape).lineTo(557.428, 368.077);
        ((GeneralPath) shape).lineTo(557.428, 360.925);
        g.setPaint(paint);
        g.fill(shape);
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(571.422, 370.101);
        ((GeneralPath) shape).lineTo(571.186, 370.101);
        ((GeneralPath) shape).lineTo(571.186, 370.017);
        ((GeneralPath) shape).lineTo(571.762, 370.017);
        ((GeneralPath) shape).lineTo(571.762, 370.101);
        ((GeneralPath) shape).lineTo(571.524, 370.101);
        ((GeneralPath) shape).lineTo(571.524, 370.793);
        ((GeneralPath) shape).lineTo(571.422, 370.793);
        ((GeneralPath) shape).lineTo(571.422, 370.101);
        g.setPaint(paint);
        g.fill(shape);
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(572.5, 370.451);
        ((GeneralPath) shape).curveTo(572.493, 370.345, 572.487, 370.21198,
                572.487, 370.11697);
        ((GeneralPath) shape).lineTo(572.482, 370.11697);
        ((GeneralPath) shape).curveTo(572.456, 370.20398, 572.424, 370.30496,
                572.386, 370.41098);
        ((GeneralPath) shape).lineTo(572.248, 370.78897);
        ((GeneralPath) shape).lineTo(572.174, 370.78897);
        ((GeneralPath) shape).lineTo(572.046, 370.41898);
        ((GeneralPath) shape).curveTo(572.00903, 370.30597, 571.98004,
                370.20898, 571.95703, 370.11697);
        ((GeneralPath) shape).lineTo(571.95605, 370.11697);
        ((GeneralPath) shape).curveTo(571.95404, 370.21298, 571.94806, 370.343,
                571.94006, 370.45996);
        ((GeneralPath) shape).lineTo(571.9181, 370.79495);
        ((GeneralPath) shape).lineTo(571.8241, 370.79495);
        ((GeneralPath) shape).lineTo(571.8771, 370.01794);
        ((GeneralPath) shape).lineTo(572.00806, 370.01794);
        ((GeneralPath) shape).lineTo(572.13904, 370.39093);
        ((GeneralPath) shape).curveTo(572.17004, 370.48694, 572.19604,
                370.57394, 572.21704, 370.65393);
        ((GeneralPath) shape).lineTo(572.218, 370.65393);
        ((GeneralPath) shape).curveTo(572.239, 370.57593, 572.267, 370.48892,
                572.301, 370.39093);
        ((GeneralPath) shape).lineTo(572.44, 370.01794);
        ((GeneralPath) shape).lineTo(572.568, 370.01794);
        ((GeneralPath) shape).lineTo(572.61597, 370.79495);
        ((GeneralPath) shape).lineTo(572.51697, 370.79495);
        ((GeneralPath) shape).lineTo(572.5, 370.451);
        g.setPaint(paint);
        g.fill(shape);
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
        paint = new Color(199, 200, 202, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(564.573, 350.575);
        ((GeneralPath) shape).curveTo(564.573, 350.575, 564.216, 350.798,
                564.083, 350.983);
        ((GeneralPath) shape).curveTo(564.032, 351.223, 564.35004, 351.493,
                564.123, 351.686);
        ((GeneralPath) shape).lineTo(561.902, 353.062);
        ((GeneralPath) shape).lineTo(555.52295, 353.415);
        ((GeneralPath) shape).curveTo(555.52295, 353.415, 553.96497, 353.43002,
                555.08997, 355.24802);
        ((GeneralPath) shape).lineTo(558.074, 360.065);
        ((GeneralPath) shape).curveTo(559.20197, 361.883, 559.907, 360.495,
                559.907, 360.495);
        ((GeneralPath) shape).lineTo(563.063, 354.93698);
        ((GeneralPath) shape).lineTo(565.089, 353.68198);
        ((GeneralPath) shape).lineTo(565.28296, 353.56097);
        ((GeneralPath) shape).curveTo(565.55597, 353.44296, 565.65894,
                353.84998, 565.89496, 353.90997);
        ((GeneralPath) shape).curveTo(566.118, 353.87497, 566.47797, 353.65198,
                566.47797, 353.65198);
        ((GeneralPath) shape).lineTo(564.573, 350.575);
        g.setPaint(paint);
        g.fill(shape);
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
        paint = new Color(35, 31, 32, 255);
        stroke = new BasicStroke(1.003f, 1, 1, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(564.573, 350.575);
        ((GeneralPath) shape).curveTo(564.573, 350.575, 564.216, 350.798,
                564.083, 350.983);
        ((GeneralPath) shape).curveTo(564.032, 351.223, 564.35004, 351.493,
                564.123, 351.686);
        ((GeneralPath) shape).lineTo(561.902, 353.061);
        ((GeneralPath) shape).lineTo(555.52295, 353.414);
        ((GeneralPath) shape).curveTo(555.52295, 353.414, 553.96497, 353.428,
                555.08997, 355.247);
        ((GeneralPath) shape).lineTo(558.074, 360.064);
        ((GeneralPath) shape).curveTo(559.20197, 361.88098, 559.90594, 360.494,
                559.90594, 360.494);
        ((GeneralPath) shape).lineTo(563.0629, 354.93597);
        ((GeneralPath) shape).lineTo(565.0889, 353.67996);
        ((GeneralPath) shape).lineTo(565.2819, 353.55997);
        ((GeneralPath) shape).curveTo(565.5559, 353.44196, 565.65894,
                353.84796, 565.89294, 353.90897);
        ((GeneralPath) shape).curveTo(566.1179, 353.87396, 566.47797,
                353.64996, 566.47797, 353.64996);
        ((GeneralPath) shape).lineTo(564.573, 350.575);
        ((GeneralPath) shape).closePath();
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
        paint = new Color(35, 31, 32, 255);
        stroke = new BasicStroke(0.501f, 1, 1, 4.0f, null, 0.0f);
        shape = new Line2D.Float(558.663025f, 354.643005f, 557.864990f,
                353.356995f);
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
        paint = new Color(35, 31, 32, 255);
        stroke = new BasicStroke(0.501f, 1, 1, 4.0f, null, 0.0f);
        shape = new Line2D.Float(557.416992f, 354.661011f, 556.619019f,
                353.373993f);
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
        paint = new Color(35, 31, 32, 255);
        stroke = new BasicStroke(0.501f, 1, 1, 4.0f, null, 0.0f);
        shape = new Line2D.Float(561.158020f, 354.488007f, 560.359985f,
                353.200989f);
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
        paint = new Color(35, 31, 32, 255);
        stroke = new BasicStroke(0.501f, 1, 1, 4.0f, null, 0.0f);
        shape = new Line2D.Float(559.911011f, 354.488007f, 559.112000f,
                353.200989f);
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
        paint = new Color(35, 31, 32, 255);
        stroke = new BasicStroke(0.501f, 1, 1, 4.0f, null, 0.0f);
        shape = new Line2D.Float(556.169006f, 354.808014f, 555.370972f,
                353.519989f);
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_0_0_26;
        g.setTransform(defaultTransform__0_0_0_26);
        g.setClip(clip__0_0_0_26);
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
        return 529;
    }

    /**
     * Returns the Y of the bounding box of the original SVG image.
     *
     * @return The Y of the bounding box of the original SVG image.
     */
    public static int getOrigY() {
        return 348;
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
