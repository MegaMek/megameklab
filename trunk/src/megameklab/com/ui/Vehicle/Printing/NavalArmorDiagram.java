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
public class NavalArmorDiagram {
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
        ((GeneralPath) shape).moveTo(573.917, 683.952);
        ((GeneralPath) shape).lineTo(581.003, 691.038);
        ((GeneralPath) shape).lineTo(581.003, 744.896);
        ((GeneralPath) shape).lineTo(573.917, 751.983);
        ((GeneralPath) shape).lineTo(412.341, 751.983);
        ((GeneralPath) shape).lineTo(405.254, 742.063);
        ((GeneralPath) shape).lineTo(405.254, 683.951);
        ((GeneralPath) shape).lineTo(410.925, 675.447);
        ((GeneralPath) shape).lineTo(494.547, 675.447);
        ((GeneralPath) shape).lineTo(500.215, 683.951);
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
        ((GeneralPath) shape).moveTo(530.355, 34.818);
        ((GeneralPath) shape).lineTo(447.869, 34.818);
        ((GeneralPath) shape).lineTo(442.483, 26.738);
        ((GeneralPath) shape).lineTo(447.855, 18.66);
        ((GeneralPath) shape).lineTo(530.355, 18.66);
        ((GeneralPath) shape).lineTo(536.025, 26.738);
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
        paint = new Color(255, 255, 255, 255);
        stroke = new BasicStroke(1.0f, 0, 1, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(530.355, 34.818);
        ((GeneralPath) shape).lineTo(447.869, 34.818);
        ((GeneralPath) shape).lineTo(442.483, 26.74);
        ((GeneralPath) shape).lineTo(447.855, 18.66);
        ((GeneralPath) shape).lineTo(530.355, 18.66);
        ((GeneralPath) shape).lineTo(536.025, 26.74);
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(453.133, 27.444);
        ((GeneralPath) shape).lineTo(451.983, 23.972);
        ((GeneralPath) shape).lineTo(450.852, 27.444);
        ((GeneralPath) shape).lineTo(453.133, 27.444);
        ((GeneralPath) shape).moveTo(453.425, 28.377);
        ((GeneralPath) shape).lineTo(450.555, 28.377);
        ((GeneralPath) shape).lineTo(450.141, 29.656);
        ((GeneralPath) shape).lineTo(448.805, 29.656);
        ((GeneralPath) shape).lineTo(451.018, 22.991001);
        ((GeneralPath) shape).lineTo(452.914, 22.991001);
        ((GeneralPath) shape).lineTo(455.16, 29.656002);
        ((GeneralPath) shape).lineTo(453.849, 29.656002);
        ((GeneralPath) shape).lineTo(453.425, 28.377);
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
        ((GeneralPath) shape).moveTo(457.008, 26.346);
        ((GeneralPath) shape).lineTo(458.82098, 26.346);
        ((GeneralPath) shape).curveTo(459.253, 26.346, 459.53998, 26.27,
                459.68097, 26.114);
        ((GeneralPath) shape).curveTo(459.82297, 25.960001, 459.89297, 25.65,
                459.89297, 25.184);
        ((GeneralPath) shape).curveTo(459.89297, 24.71, 459.83197, 24.403,
                459.70996, 24.264);
        ((GeneralPath) shape).curveTo(459.58795, 24.127, 459.32297, 24.056,
                458.90796, 24.056);
        ((GeneralPath) shape).lineTo(457.00797, 24.056);
        ((GeneralPath) shape).lineTo(457.00797, 26.346);
        ((GeneralPath) shape).moveTo(455.746, 29.656);
        ((GeneralPath) shape).lineTo(455.746, 22.991001);
        ((GeneralPath) shape).lineTo(459.025, 22.991001);
        ((GeneralPath) shape).curveTo(459.839, 22.991001, 460.402, 23.133001,
                460.711, 23.416);
        ((GeneralPath) shape).curveTo(461.019, 23.699001, 461.175, 24.212,
                461.175, 24.954);
        ((GeneralPath) shape).curveTo(461.175, 25.628, 461.099, 26.087,
                460.944, 26.336);
        ((GeneralPath) shape).curveTo(460.791, 26.583, 460.473, 26.753,
                459.996, 26.849);
        ((GeneralPath) shape).lineTo(459.996, 26.893002);
        ((GeneralPath) shape).curveTo(460.732, 26.937002, 461.102, 27.369001,
                461.102, 28.187002);
        ((GeneralPath) shape).lineTo(461.102, 29.657001);
        ((GeneralPath) shape).lineTo(459.84, 29.657001);
        ((GeneralPath) shape).lineTo(459.84, 28.44);
        ((GeneralPath) shape).curveTo(459.84, 27.754, 459.503, 27.41, 458.826,
                27.41);
        ((GeneralPath) shape).lineTo(457.008, 27.41);
        ((GeneralPath) shape).lineTo(457.008, 29.656);
        ((GeneralPath) shape).lineTo(455.746, 29.656);
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
        ((GeneralPath) shape).moveTo(469.97, 22.991);
        ((GeneralPath) shape).lineTo(469.97, 29.655998);
        ((GeneralPath) shape).lineTo(468.708, 29.655998);
        ((GeneralPath) shape).lineTo(468.708, 26.022999);
        ((GeneralPath) shape).curveTo(468.708, 25.734999, 468.715, 25.404999,
                468.73102, 25.036999);
        ((GeneralPath) shape).lineTo(468.756, 24.539);
        ((GeneralPath) shape).lineTo(468.77902, 24.046);
        ((GeneralPath) shape).lineTo(468.74002, 24.046);
        ((GeneralPath) shape).lineTo(468.59003, 24.51);
        ((GeneralPath) shape).lineTo(468.44403, 24.974);
        ((GeneralPath) shape).curveTo(468.30804, 25.389002, 468.20303, 25.697,
                468.12802, 25.897001);
        ((GeneralPath) shape).lineTo(466.66602, 29.657001);
        ((GeneralPath) shape).lineTo(465.51602, 29.657001);
        ((GeneralPath) shape).lineTo(464.03903, 25.927002);
        ((GeneralPath) shape).curveTo(463.95905, 25.722002, 463.85104,
                25.414001, 463.71802, 25.004002);
        ((GeneralPath) shape).lineTo(463.56702, 24.54);
        ((GeneralPath) shape).lineTo(463.41602, 24.081001);
        ((GeneralPath) shape).lineTo(463.377, 24.081001);
        ((GeneralPath) shape).lineTo(463.401, 24.564001);
        ((GeneralPath) shape).lineTo(463.426, 25.052002);
        ((GeneralPath) shape).curveTo(463.445, 25.428001, 463.454, 25.753002,
                463.454, 26.024002);
        ((GeneralPath) shape).lineTo(463.454, 29.657001);
        ((GeneralPath) shape).lineTo(462.19202, 29.657001);
        ((GeneralPath) shape).lineTo(462.19202, 22.992);
        ((GeneralPath) shape).lineTo(464.24902, 22.992);
        ((GeneralPath) shape).lineTo(465.437, 26.078001);
        ((GeneralPath) shape).curveTo(465.517, 26.293001, 465.625, 26.6,
                465.759, 27.001001);
        ((GeneralPath) shape).lineTo(465.905, 27.465002);
        ((GeneralPath) shape).lineTo(466.056, 27.924002);
        ((GeneralPath) shape).lineTo(466.1, 27.924002);
        ((GeneralPath) shape).lineTo(466.241, 27.465002);
        ((GeneralPath) shape).lineTo(466.387, 27.006002);
        ((GeneralPath) shape).curveTo(466.507, 26.620003, 466.612, 26.313002,
                466.69998, 26.088003);
        ((GeneralPath) shape).lineTo(467.869, 22.992002);
        ((GeneralPath) shape).lineTo(469.97, 22.992002);
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
        ((GeneralPath) shape).moveTo(473.809, 24.017);
        ((GeneralPath) shape).curveTo(472.961, 24.017, 472.466, 24.124,
                472.323, 24.344);
        ((GeneralPath) shape).curveTo(472.182, 24.560999, 472.108, 25.316,
                472.108, 26.61);
        ((GeneralPath) shape).curveTo(472.108, 27.567001, 472.191, 28.143002,
                472.36002, 28.339);
        ((GeneralPath) shape).curveTo(472.52502, 28.534, 473.019, 28.632,
                473.83902, 28.632);
        ((GeneralPath) shape).curveTo(474.62103, 28.632, 475.09103, 28.522,
                475.247, 28.302);
        ((GeneralPath) shape).curveTo(475.40298, 28.082, 475.48102, 27.416,
                475.48102, 26.302);
        ((GeneralPath) shape).curveTo(475.48102, 25.186, 475.40802, 24.527,
                475.25803, 24.322);
        ((GeneralPath) shape).curveTo(475.112, 24.119, 474.627, 24.017,
                473.809, 24.017);
        ((GeneralPath) shape).moveTo(473.906, 22.933);
        ((GeneralPath) shape).curveTo(475.11502, 22.933, 475.891, 23.133001,
                476.24002, 23.536001);
        ((GeneralPath) shape).curveTo(476.58603, 23.936, 476.76102, 24.837002,
                476.76102, 26.234001);
        ((GeneralPath) shape).curveTo(476.76102, 27.760002, 476.588, 28.722,
                476.238, 29.12);
        ((GeneralPath) shape).curveTo(475.889, 29.516, 475.044, 29.716002,
                473.70102, 29.716002);
        ((GeneralPath) shape).curveTo(472.493, 29.716002, 471.713, 29.521002,
                471.35703, 29.128002);
        ((GeneralPath) shape).curveTo(471.00302, 28.737001, 470.82602,
                27.873003, 470.82602, 26.538002);
        ((GeneralPath) shape).curveTo(470.82602, 24.951002, 470.99902,
                23.953003, 471.34702, 23.545002);
        ((GeneralPath) shape).curveTo(471.694, 23.138, 472.547, 22.933,
                473.906, 22.933);
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
        ((GeneralPath) shape).moveTo(478.882, 26.346);
        ((GeneralPath) shape).lineTo(480.69498, 26.346);
        ((GeneralPath) shape).curveTo(481.12698, 26.346, 481.41397, 26.27,
                481.55496, 26.114);
        ((GeneralPath) shape).curveTo(481.69595, 25.960001, 481.76596, 25.65,
                481.76596, 25.184);
        ((GeneralPath) shape).curveTo(481.76596, 24.71, 481.70496, 24.403,
                481.58395, 24.264);
        ((GeneralPath) shape).curveTo(481.46194, 24.127, 481.19595, 24.056,
                480.78094, 24.056);
        ((GeneralPath) shape).lineTo(478.88196, 24.056);
        ((GeneralPath) shape).lineTo(478.88196, 26.346);
        ((GeneralPath) shape).moveTo(477.619, 29.656);
        ((GeneralPath) shape).lineTo(477.619, 22.991001);
        ((GeneralPath) shape).lineTo(480.89798, 22.991001);
        ((GeneralPath) shape).curveTo(481.71198, 22.991001, 482.275, 23.133001,
                482.585, 23.416);
        ((GeneralPath) shape).curveTo(482.892, 23.699001, 483.048, 24.212,
                483.048, 24.954);
        ((GeneralPath) shape).curveTo(483.048, 25.628, 482.973, 26.087,
                482.81702, 26.336);
        ((GeneralPath) shape).curveTo(482.664, 26.583, 482.346, 26.753,
                481.86902, 26.849);
        ((GeneralPath) shape).lineTo(481.86902, 26.893002);
        ((GeneralPath) shape).curveTo(482.605, 26.937002, 482.975, 27.369001,
                482.975, 28.187002);
        ((GeneralPath) shape).lineTo(482.975, 29.657001);
        ((GeneralPath) shape).lineTo(481.713, 29.657001);
        ((GeneralPath) shape).lineTo(481.713, 28.44);
        ((GeneralPath) shape).curveTo(481.713, 27.754, 481.377, 27.41, 480.699,
                27.41);
        ((GeneralPath) shape).lineTo(478.88202, 27.41);
        ((GeneralPath) shape).lineTo(478.88202, 29.656);
        ((GeneralPath) shape).lineTo(477.619, 29.656);
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
        ((GeneralPath) shape).moveTo(487.832, 28.592);
        ((GeneralPath) shape).lineTo(489.63, 28.592);
        ((GeneralPath) shape).curveTo(490.234, 28.592, 490.624, 28.453,
                490.802, 28.175);
        ((GeneralPath) shape).curveTo(490.978, 27.897, 491.068, 27.283998,
                491.068, 26.331999);
        ((GeneralPath) shape).curveTo(491.068, 25.350998, 490.991, 24.725998,
                490.83, 24.456999);
        ((GeneralPath) shape).curveTo(490.672, 24.190998, 490.29898, 24.057,
                489.70898, 24.057);
        ((GeneralPath) shape).lineTo(487.83298, 24.057);
        ((GeneralPath) shape).lineTo(487.83298, 28.592);
        ((GeneralPath) shape).moveTo(486.57, 29.656);
        ((GeneralPath) shape).lineTo(486.57, 22.991001);
        ((GeneralPath) shape).lineTo(489.84, 22.991001);
        ((GeneralPath) shape).curveTo(490.76898, 22.991001, 491.419, 23.194,
                491.792, 23.601002);
        ((GeneralPath) shape).curveTo(492.162, 24.006002, 492.35, 24.719002,
                492.35, 25.740002);
        ((GeneralPath) shape).curveTo(492.35, 27.403002, 492.202, 28.472002,
                491.902, 28.946001);
        ((GeneralPath) shape).curveTo(491.604, 29.420002, 490.92902, 29.656,
                489.88, 29.656);
        ((GeneralPath) shape).lineTo(486.57, 29.656);
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
        shape = new Rectangle2D.Double(493.2760009765625, 22.990999221801758,
                1.2619999647140503, 6.664999961853027);
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
        ((GeneralPath) shape).moveTo(499.463, 27.444);
        ((GeneralPath) shape).lineTo(498.31302, 23.972);
        ((GeneralPath) shape).lineTo(497.182, 27.444);
        ((GeneralPath) shape).lineTo(499.463, 27.444);
        ((GeneralPath) shape).moveTo(499.755, 28.377);
        ((GeneralPath) shape).lineTo(496.885, 28.377);
        ((GeneralPath) shape).lineTo(496.471, 29.656);
        ((GeneralPath) shape).lineTo(495.13602, 29.656);
        ((GeneralPath) shape).lineTo(497.34802, 22.991001);
        ((GeneralPath) shape).lineTo(499.24402, 22.991001);
        ((GeneralPath) shape).lineTo(501.49103, 29.656002);
        ((GeneralPath) shape).lineTo(500.18002, 29.656002);
        ((GeneralPath) shape).lineTo(499.755, 28.377);
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
        ((GeneralPath) shape).moveTo(504.693, 26.111);
        ((GeneralPath) shape).lineTo(507.62198, 26.111);
        ((GeneralPath) shape).lineTo(507.64197, 27.434);
        ((GeneralPath) shape).curveTo(507.64197, 28.396, 507.46097, 29.018,
                507.09796, 29.297);
        ((GeneralPath) shape).curveTo(506.73798, 29.575, 505.92896, 29.714,
                504.67395, 29.714);
        ((GeneralPath) shape).curveTo(503.52396, 29.714, 502.76395, 29.528,
                502.38895, 29.157001);
        ((GeneralPath) shape).curveTo(502.01596, 28.786001, 501.82794,
                28.029001, 501.82794, 26.886002);
        ((GeneralPath) shape).curveTo(501.82794, 25.428001, 501.90094,
                24.508001, 502.05194, 24.122002);
        ((GeneralPath) shape).curveTo(502.23795, 23.653002, 502.51794,
                23.338001, 502.89594, 23.175001);
        ((GeneralPath) shape).curveTo(503.27094, 23.014002, 503.91193,
                22.931002, 504.81595, 22.931002);
        ((GeneralPath) shape).curveTo(505.99796, 22.931002, 506.75894,
                23.056002, 507.10596, 23.309002);
        ((GeneralPath) shape).curveTo(507.44995, 23.560001, 507.62296,
                24.120003, 507.62296, 24.986002);
        ((GeneralPath) shape).lineTo(506.34595, 24.986002);
        ((GeneralPath) shape).curveTo(506.32495, 24.551003, 506.22995,
                24.280003, 506.06195, 24.173002);
        ((GeneralPath) shape).curveTo(505.89597, 24.068003, 505.47394,
                24.014002, 504.80194, 24.014002);
        ((GeneralPath) shape).curveTo(504.07095, 24.014002, 503.60794,
                24.104002, 503.40994, 24.287003);
        ((GeneralPath) shape).curveTo(503.21494, 24.468002, 503.11493,
                24.897003, 503.11493, 25.571003);
        ((GeneralPath) shape).lineTo(503.10992, 26.240004);
        ((GeneralPath) shape).lineTo(503.11993, 27.094004);
        ((GeneralPath) shape).curveTo(503.11993, 27.753004, 503.21793,
                28.176004, 503.41193, 28.356003);
        ((GeneralPath) shape).curveTo(503.60693, 28.537003, 504.05792,
                28.627003, 504.76694, 28.627003);
        ((GeneralPath) shape).curveTo(505.45395, 28.627003, 505.89294,
                28.551003, 506.07993, 28.398003);
        ((GeneralPath) shape).curveTo(506.26492, 28.247004, 506.35992,
                27.885002, 506.35992, 27.314003);
        ((GeneralPath) shape).lineTo(506.36392, 27.041002);
        ((GeneralPath) shape).lineTo(504.69293, 27.041002);
        ((GeneralPath) shape).lineTo(504.69293, 26.111);
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
        ((GeneralPath) shape).moveTo(509.795, 26.346);
        ((GeneralPath) shape).lineTo(511.608, 26.346);
        ((GeneralPath) shape).curveTo(512.04, 26.346, 512.327, 26.27, 512.468,
                26.114);
        ((GeneralPath) shape).curveTo(512.609, 25.960001, 512.68, 25.65,
                512.68, 25.184);
        ((GeneralPath) shape).curveTo(512.68, 24.71, 512.618, 24.403, 512.497,
                24.264);
        ((GeneralPath) shape).curveTo(512.375, 24.127, 512.109, 24.056,
                511.695, 24.056);
        ((GeneralPath) shape).lineTo(509.795, 24.056);
        ((GeneralPath) shape).lineTo(509.795, 26.346);
        ((GeneralPath) shape).moveTo(508.532, 29.656);
        ((GeneralPath) shape).lineTo(508.532, 22.991001);
        ((GeneralPath) shape).lineTo(511.811, 22.991001);
        ((GeneralPath) shape).curveTo(512.625, 22.991001, 513.188, 23.133001,
                513.498, 23.416);
        ((GeneralPath) shape).curveTo(513.805, 23.699001, 513.961, 24.212,
                513.961, 24.954);
        ((GeneralPath) shape).curveTo(513.961, 25.628, 513.886, 26.087, 513.73,
                26.336);
        ((GeneralPath) shape).curveTo(513.57697, 26.583, 513.26, 26.753,
                512.78296, 26.849);
        ((GeneralPath) shape).lineTo(512.78296, 26.893002);
        ((GeneralPath) shape).curveTo(513.51794, 26.937002, 513.889, 27.369001,
                513.889, 28.187002);
        ((GeneralPath) shape).lineTo(513.889, 29.657001);
        ((GeneralPath) shape).lineTo(512.626, 29.657001);
        ((GeneralPath) shape).lineTo(512.626, 28.44);
        ((GeneralPath) shape).curveTo(512.626, 27.754, 512.29, 27.41,
                511.61197, 27.41);
        ((GeneralPath) shape).lineTo(509.79498, 27.41);
        ((GeneralPath) shape).lineTo(509.79498, 29.656);
        ((GeneralPath) shape).lineTo(508.532, 29.656);
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
        ((GeneralPath) shape).moveTo(518.711, 27.444);
        ((GeneralPath) shape).lineTo(517.561, 23.972);
        ((GeneralPath) shape).lineTo(516.43097, 27.444);
        ((GeneralPath) shape).lineTo(518.711, 27.444);
        ((GeneralPath) shape).moveTo(519.004, 28.377);
        ((GeneralPath) shape).lineTo(516.13306, 28.377);
        ((GeneralPath) shape).lineTo(515.71906, 29.656);
        ((GeneralPath) shape).lineTo(514.38403, 29.656);
        ((GeneralPath) shape).lineTo(516.596, 22.991001);
        ((GeneralPath) shape).lineTo(518.492, 22.991001);
        ((GeneralPath) shape).lineTo(520.738, 29.656002);
        ((GeneralPath) shape).lineTo(519.427, 29.656002);
        ((GeneralPath) shape).lineTo(519.004, 28.377);
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
        ((GeneralPath) shape).moveTo(529.103, 22.991);
        ((GeneralPath) shape).lineTo(529.103, 29.655998);
        ((GeneralPath) shape).lineTo(527.84, 29.655998);
        ((GeneralPath) shape).lineTo(527.84, 26.022999);
        ((GeneralPath) shape).curveTo(527.84, 25.734999, 527.848, 25.404999,
                527.864, 25.036999);
        ((GeneralPath) shape).lineTo(527.888, 24.539);
        ((GeneralPath) shape).lineTo(527.912, 24.046);
        ((GeneralPath) shape).lineTo(527.873, 24.046);
        ((GeneralPath) shape).lineTo(527.722, 24.51);
        ((GeneralPath) shape).lineTo(527.576, 24.974);
        ((GeneralPath) shape).curveTo(527.43896, 25.389002, 527.33496, 25.697,
                527.259, 25.897001);
        ((GeneralPath) shape).lineTo(525.797, 29.657001);
        ((GeneralPath) shape).lineTo(524.648, 29.657001);
        ((GeneralPath) shape).lineTo(523.171, 25.927002);
        ((GeneralPath) shape).curveTo(523.09, 25.722002, 522.98303, 25.414001,
                522.85004, 25.004002);
        ((GeneralPath) shape).lineTo(522.69904, 24.54);
        ((GeneralPath) shape).lineTo(522.54803, 24.081001);
        ((GeneralPath) shape).lineTo(522.50903, 24.081001);
        ((GeneralPath) shape).lineTo(522.533, 24.564001);
        ((GeneralPath) shape).lineTo(522.557, 25.052002);
        ((GeneralPath) shape).curveTo(522.577, 25.428001, 522.586, 25.753002,
                522.586, 26.024002);
        ((GeneralPath) shape).lineTo(522.586, 29.657001);
        ((GeneralPath) shape).lineTo(521.323, 29.657001);
        ((GeneralPath) shape).lineTo(521.323, 22.992);
        ((GeneralPath) shape).lineTo(523.38, 22.992);
        ((GeneralPath) shape).lineTo(524.56903, 26.078001);
        ((GeneralPath) shape).curveTo(524.64905, 26.293001, 524.757, 26.6,
                524.89, 27.001001);
        ((GeneralPath) shape).lineTo(525.036, 27.465002);
        ((GeneralPath) shape).lineTo(525.187, 27.924002);
        ((GeneralPath) shape).lineTo(525.23004, 27.924002);
        ((GeneralPath) shape).lineTo(525.3721, 27.465002);
        ((GeneralPath) shape).lineTo(525.51807, 27.006002);
        ((GeneralPath) shape).curveTo(525.6371, 26.620003, 525.74304,
                26.313002, 525.8301, 26.088003);
        ((GeneralPath) shape).lineTo(527.00006, 22.992002);
        ((GeneralPath) shape).lineTo(529.103, 22.992002);
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
        ((GeneralPath) shape).moveTo(569.665, 679.701);
        ((GeneralPath) shape).lineTo(576.751, 686.788);
        ((GeneralPath) shape).lineTo(576.751, 740.646);
        ((GeneralPath) shape).lineTo(569.665, 747.732);
        ((GeneralPath) shape).lineTo(408.089, 747.732);
        ((GeneralPath) shape).lineTo(401.003, 737.812);
        ((GeneralPath) shape).lineTo(401.003, 679.7);
        ((GeneralPath) shape).lineTo(406.672, 671.197);
        ((GeneralPath) shape).lineTo(490.294, 671.197);
        ((GeneralPath) shape).lineTo(495.964, 679.7);
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
        stroke = new BasicStroke(2.0f, 0, 0, 10.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(569.665, 679.701);
        ((GeneralPath) shape).lineTo(576.751, 686.787);
        ((GeneralPath) shape).lineTo(576.751, 740.646);
        ((GeneralPath) shape).lineTo(569.665, 747.732);
        ((GeneralPath) shape).lineTo(408.089, 747.732);
        ((GeneralPath) shape).lineTo(401.003, 737.812);
        ((GeneralPath) shape).lineTo(401.003, 679.7);
        ((GeneralPath) shape).lineTo(406.672, 671.197);
        ((GeneralPath) shape).lineTo(490.294, 671.197);
        ((GeneralPath) shape).lineTo(495.962, 679.7);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        ((GeneralPath) shape).moveTo(488.89, 689.622);
        ((GeneralPath) shape).lineTo(407.805, 689.622);
        ((GeneralPath) shape).lineTo(402.421, 681.542);
        ((GeneralPath) shape).lineTo(407.794, 673.464);
        ((GeneralPath) shape).lineTo(488.876, 673.464);
        ((GeneralPath) shape).lineTo(494.262, 681.542);
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
        stroke = new BasicStroke(1.0f, 0, 1, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(488.889, 689.622);
        ((GeneralPath) shape).lineTo(407.806, 689.622);
        ((GeneralPath) shape).lineTo(402.421, 681.542);
        ((GeneralPath) shape).lineTo(407.792, 673.464);
        ((GeneralPath) shape).lineTo(488.875, 673.464);
        ((GeneralPath) shape).lineTo(494.262, 681.542);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        ((GeneralPath) shape).moveTo(411.723, 684.094);
        ((GeneralPath) shape).lineTo(413.88, 684.094);
        ((GeneralPath) shape).curveTo(414.60602, 684.094, 415.073, 683.927,
                415.28702, 683.593);
        ((GeneralPath) shape).curveTo(415.497, 683.25903, 415.605, 682.52405,
                415.605, 681.38104);
        ((GeneralPath) shape).curveTo(415.605, 680.20306, 415.51102, 679.45306,
                415.319, 679.13104);
        ((GeneralPath) shape).curveTo(415.129, 678.8121, 414.681, 678.65106,
                413.973, 678.65106);
        ((GeneralPath) shape).lineTo(411.722, 678.65106);
        ((GeneralPath) shape).lineTo(411.722, 684.094);
        ((GeneralPath) shape).moveTo(410.208, 685.371);
        ((GeneralPath) shape).lineTo(410.208, 677.373);
        ((GeneralPath) shape).lineTo(414.13202, 677.373);
        ((GeneralPath) shape).curveTo(415.24603, 677.373, 416.028, 677.61597,
                416.475, 678.105);
        ((GeneralPath) shape).curveTo(416.919, 678.591, 417.144, 679.44696,
                417.144, 680.67096);
        ((GeneralPath) shape).curveTo(417.144, 682.66595, 416.966, 683.949,
                416.60602, 684.51794);
        ((GeneralPath) shape).curveTo(416.25003, 685.08594, 415.43903, 685.371,
                414.17902, 685.371);
        ((GeneralPath) shape).lineTo(410.208, 685.371);
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(419.603, 678.65);
        ((GeneralPath) shape).lineTo(419.603, 680.713);
        ((GeneralPath) shape).lineTo(423.345, 680.713);
        ((GeneralPath) shape).lineTo(423.345, 681.832);
        ((GeneralPath) shape).lineTo(419.603, 681.832);
        ((GeneralPath) shape).lineTo(419.603, 684.094);
        ((GeneralPath) shape).lineTo(423.585, 684.094);
        ((GeneralPath) shape).lineTo(423.585, 685.371);
        ((GeneralPath) shape).lineTo(418.087, 685.371);
        ((GeneralPath) shape).lineTo(418.087, 677.373);
        ((GeneralPath) shape).lineTo(423.55, 677.373);
        ((GeneralPath) shape).lineTo(423.55, 678.65);
        ((GeneralPath) shape).lineTo(419.603, 678.65);
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(426.229, 681.51);
        ((GeneralPath) shape).lineTo(427.954, 681.51);
        ((GeneralPath) shape).curveTo(428.639, 681.51, 429.074, 681.431,
                429.26202, 681.273);
        ((GeneralPath) shape).curveTo(429.446, 681.115, 429.53903, 680.743,
                429.53903, 680.151);
        ((GeneralPath) shape).curveTo(429.53903, 679.477, 429.46304, 679.058,
                429.30902, 678.894);
        ((GeneralPath) shape).curveTo(429.157, 678.733, 428.756, 678.651,
                428.10703, 678.651);
        ((GeneralPath) shape).lineTo(426.23, 678.651);
        ((GeneralPath) shape).lineTo(426.23, 681.51);
        ((GeneralPath) shape).moveTo(424.715, 685.371);
        ((GeneralPath) shape).lineTo(424.715, 677.373);
        ((GeneralPath) shape).lineTo(428.329, 677.373);
        ((GeneralPath) shape).curveTo(429.407, 677.373, 430.13602, 677.561,
                430.513, 677.938);
        ((GeneralPath) shape).curveTo(430.888, 678.313, 431.077, 679.042,
                431.077, 680.121);
        ((GeneralPath) shape).curveTo(431.077, 681.18994, 430.899, 681.902,
                430.539, 682.25397);
        ((GeneralPath) shape).curveTo(430.183, 682.60596, 429.458, 682.78094,
                428.363, 682.78094);
        ((GeneralPath) shape).lineTo(428.012, 682.7869);
        ((GeneralPath) shape).lineTo(426.229, 682.7869);
        ((GeneralPath) shape).lineTo(426.229, 685.3709);
        ((GeneralPath) shape).lineTo(424.715, 685.3709);
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(435.228, 678.732);
        ((GeneralPath) shape).lineTo(435.228, 685.371);
        ((GeneralPath) shape).lineTo(433.713, 685.371);
        ((GeneralPath) shape).lineTo(433.713, 678.732);
        ((GeneralPath) shape).lineTo(431.408, 678.732);
        ((GeneralPath) shape).lineTo(431.408, 677.373);
        ((GeneralPath) shape).lineTo(437.613, 677.373);
        ((GeneralPath) shape).lineTo(437.613, 678.732);
        ((GeneralPath) shape).lineTo(435.228, 678.732);
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(445.155, 677.373);
        ((GeneralPath) shape).lineTo(445.155, 685.371);
        ((GeneralPath) shape).lineTo(443.641, 685.371);
        ((GeneralPath) shape).lineTo(443.641, 681.914);
        ((GeneralPath) shape).lineTo(439.897, 681.914);
        ((GeneralPath) shape).lineTo(439.897, 685.371);
        ((GeneralPath) shape).lineTo(438.383, 685.371);
        ((GeneralPath) shape).lineTo(438.383, 677.373);
        ((GeneralPath) shape).lineTo(439.897, 677.373);
        ((GeneralPath) shape).lineTo(439.897, 680.637);
        ((GeneralPath) shape).lineTo(443.641, 680.637);
        ((GeneralPath) shape).lineTo(443.641, 677.373);
        ((GeneralPath) shape).lineTo(445.155, 677.373);
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(452.74, 678.732);
        ((GeneralPath) shape).lineTo(452.74, 685.371);
        ((GeneralPath) shape).lineTo(451.226, 685.371);
        ((GeneralPath) shape).lineTo(451.226, 678.732);
        ((GeneralPath) shape).lineTo(448.921, 678.732);
        ((GeneralPath) shape).lineTo(448.921, 677.373);
        ((GeneralPath) shape).lineTo(455.126, 677.373);
        ((GeneralPath) shape).lineTo(455.126, 678.732);
        ((GeneralPath) shape).lineTo(452.74, 678.732);
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
        ((GeneralPath) shape).moveTo(457.41, 681.398);
        ((GeneralPath) shape).lineTo(459.586, 681.398);
        ((GeneralPath) shape).curveTo(460.104, 681.398, 460.448, 681.307,
                460.618, 681.12);
        ((GeneralPath) shape).curveTo(460.78702, 680.935, 460.872, 680.563,
                460.872, 680.00397);
        ((GeneralPath) shape).curveTo(460.872, 679.436, 460.799, 679.066,
                460.653, 678.89996);
        ((GeneralPath) shape).curveTo(460.50702, 678.73596, 460.18802,
                678.65094, 459.691, 678.65094);
        ((GeneralPath) shape).lineTo(457.41, 678.65094);
        ((GeneralPath) shape).lineTo(457.41, 681.398);
        ((GeneralPath) shape).moveTo(455.896, 685.371);
        ((GeneralPath) shape).lineTo(455.896, 677.373);
        ((GeneralPath) shape).lineTo(459.832, 677.373);
        ((GeneralPath) shape).curveTo(460.81, 677.373, 461.484, 677.54297,
                461.855, 677.883);
        ((GeneralPath) shape).curveTo(462.224, 678.223, 462.411, 678.838,
                462.411, 679.729);
        ((GeneralPath) shape).curveTo(462.411, 680.538, 462.321, 681.088,
                462.134, 681.387);
        ((GeneralPath) shape).curveTo(461.95, 681.68304, 461.57, 681.888,
                460.996, 682.002);
        ((GeneralPath) shape).lineTo(460.996, 682.055);
        ((GeneralPath) shape).curveTo(461.88, 682.108, 462.324, 682.626,
                462.324, 683.608);
        ((GeneralPath) shape).lineTo(462.324, 685.37195);
        ((GeneralPath) shape).lineTo(460.809, 685.37195);
        ((GeneralPath) shape).lineTo(460.809, 683.91296);
        ((GeneralPath) shape).curveTo(460.809, 683.08997, 460.40598, 682.67694,
                459.59198, 682.67694);
        ((GeneralPath) shape).lineTo(457.40997, 682.67694);
        ((GeneralPath) shape).lineTo(457.40997, 685.37195);
        ((GeneralPath) shape).lineTo(455.896, 685.37195);
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(468.102, 682.717);
        ((GeneralPath) shape).lineTo(466.722, 678.55096);
        ((GeneralPath) shape).lineTo(465.366, 682.717);
        ((GeneralPath) shape).lineTo(468.102, 682.717);
        ((GeneralPath) shape).moveTo(468.453, 683.836);
        ((GeneralPath) shape).lineTo(465.008, 683.836);
        ((GeneralPath) shape).lineTo(464.511, 685.371);
        ((GeneralPath) shape).lineTo(462.908, 685.371);
        ((GeneralPath) shape).lineTo(465.563, 677.373);
        ((GeneralPath) shape).lineTo(467.83798, 677.373);
        ((GeneralPath) shape).lineTo(470.533, 685.371);
        ((GeneralPath) shape).lineTo(468.961, 685.371);
        ((GeneralPath) shape).lineTo(468.453, 683.836);
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(476.159, 682.518);
        ((GeneralPath) shape).lineTo(477.68, 682.518);
        ((GeneralPath) shape).lineTo(477.68, 682.793);
        ((GeneralPath) shape).curveTo(477.68, 683.906, 477.478, 684.624,
                477.072, 684.952);
        ((GeneralPath) shape).curveTo(476.66898, 685.27704, 475.77, 685.44104,
                474.382, 685.44104);
        ((GeneralPath) shape).curveTo(472.809, 685.44104, 471.83798, 685.18304,
                471.47598, 684.668);
        ((GeneralPath) shape).curveTo(471.11398, 684.153, 470.93198, 682.775,
                470.93198, 680.531);
        ((GeneralPath) shape).curveTo(470.93198, 679.213, 471.17697, 678.343,
                471.66898, 677.927);
        ((GeneralPath) shape).curveTo(472.15897, 677.511, 473.18597, 677.303,
                474.75098, 677.303);
        ((GeneralPath) shape).curveTo(475.88797, 677.303, 476.64798, 677.47296,
                477.03098, 677.816);
        ((GeneralPath) shape).curveTo(477.41098, 678.156, 477.60397, 678.836,
                477.60397, 679.852);
        ((GeneralPath) shape).lineTo(477.61, 680.034);
        ((GeneralPath) shape).lineTo(476.089, 680.034);
        ((GeneralPath) shape).lineTo(476.089, 679.829);
        ((GeneralPath) shape).curveTo(476.089, 679.308, 475.992, 678.971,
                475.793, 678.824);
        ((GeneralPath) shape).curveTo(475.598, 678.678, 475.147, 678.604,
                474.445, 678.604);
        ((GeneralPath) shape).curveTo(473.50702, 678.604, 472.94202, 678.718,
                472.752, 678.95);
        ((GeneralPath) shape).curveTo(472.565, 679.179, 472.46902, 679.864,
                472.46902, 681.00104);
        ((GeneralPath) shape).curveTo(472.46902, 682.53, 472.55402, 683.439,
                472.72403, 683.72003);
        ((GeneralPath) shape).curveTo(472.89304, 684.00104, 473.44003, 684.142,
                474.37003, 684.142);
        ((GeneralPath) shape).curveTo(475.12204, 684.142, 475.61002, 684.06604,
                475.83603, 683.908);
        ((GeneralPath) shape).curveTo(476.058, 683.753, 476.17102, 683.41003,
                476.17102, 682.877);
        ((GeneralPath) shape).lineTo(476.159, 682.518);
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
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(480.146, 677.373);
        ((GeneralPath) shape).lineTo(480.146, 680.631);
        ((GeneralPath) shape).lineTo(480.86, 680.631);
        ((GeneralPath) shape).lineTo(483.516, 677.373);
        ((GeneralPath) shape).lineTo(485.398, 677.373);
        ((GeneralPath) shape).lineTo(482.176, 681.258);
        ((GeneralPath) shape).lineTo(485.737, 685.371);
        ((GeneralPath) shape).lineTo(483.796, 685.371);
        ((GeneralPath) shape).lineTo(480.871, 681.908);
        ((GeneralPath) shape).lineTo(480.146, 681.908);
        ((GeneralPath) shape).lineTo(480.146, 685.371);
        ((GeneralPath) shape).lineTo(478.632, 685.371);
        ((GeneralPath) shape).lineTo(478.632, 677.373);
        ((GeneralPath) shape).lineTo(480.146, 677.373);
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
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(410.523, 694.528);
        ((GeneralPath) shape).lineTo(412.002, 694.528);
        ((GeneralPath) shape).lineTo(412.002, 693.544);
        ((GeneralPath) shape).lineTo(408.101, 693.544);
        ((GeneralPath) shape).lineTo(408.101, 694.528);
        ((GeneralPath) shape).lineTo(409.579, 694.528);
        ((GeneralPath) shape).lineTo(409.579, 699.544);
        ((GeneralPath) shape).lineTo(410.523, 699.544);
        ((GeneralPath) shape).lineTo(410.523, 694.528);
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
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(415.207, 699.544);
        ((GeneralPath) shape).lineTo(415.207, 695.294);
        ((GeneralPath) shape).lineTo(414.366, 695.294);
        ((GeneralPath) shape).lineTo(414.366, 697.732);
        ((GeneralPath) shape).curveTo(414.366, 698.464, 414.155, 698.795,
                413.495, 698.795);
        ((GeneralPath) shape).curveTo(412.944, 698.795, 412.896, 698.563,
                412.896, 697.959);
        ((GeneralPath) shape).lineTo(412.896, 695.295);
        ((GeneralPath) shape).lineTo(412.056, 695.295);
        ((GeneralPath) shape).lineTo(412.056, 698.336);
        ((GeneralPath) shape).curveTo(412.056, 699.256, 412.529, 699.545,
                413.236, 699.545);
        ((GeneralPath) shape).curveTo(413.72598, 699.545, 414.16998, 699.412,
                414.366, 698.86896);
        ((GeneralPath) shape).lineTo(414.366, 699.545);
        ((GeneralPath) shape).lineTo(415.207, 699.545);
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
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(415.937, 695.294);
        ((GeneralPath) shape).lineTo(415.937, 699.544);
        ((GeneralPath) shape).lineTo(416.778, 699.544);
        ((GeneralPath) shape).lineTo(416.778, 696.95);
        ((GeneralPath) shape).curveTo(416.778, 696.405, 416.933, 696.06104,
                417.46802, 696.06104);
        ((GeneralPath) shape).curveTo(417.897, 696.06104, 417.932, 696.309,
                417.932, 696.73303);
        ((GeneralPath) shape).lineTo(417.932, 696.95);
        ((GeneralPath) shape).lineTo(418.772, 696.95);
        ((GeneralPath) shape).lineTo(418.772, 696.614);
        ((GeneralPath) shape).curveTo(418.772, 695.821, 418.579, 695.294,
                417.78702, 695.294);
        ((GeneralPath) shape).curveTo(417.34802, 695.294, 416.958, 695.427,
                416.778, 695.882);
        ((GeneralPath) shape).lineTo(416.778, 695.294);
        ((GeneralPath) shape).lineTo(415.937, 695.294);
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
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(419.261, 695.294);
        ((GeneralPath) shape).lineTo(419.261, 699.544);
        ((GeneralPath) shape).lineTo(420.10098, 699.544);
        ((GeneralPath) shape).lineTo(420.10098, 697.23);
        ((GeneralPath) shape).curveTo(420.10098, 696.47797, 420.19998,
                696.06195, 420.92398, 696.06195);
        ((GeneralPath) shape).curveTo(421.45898, 696.06195, 421.56998,
                696.27094, 421.56998, 696.8779);
        ((GeneralPath) shape).lineTo(421.56998, 699.54395);
        ((GeneralPath) shape).lineTo(422.40997, 699.54395);
        ((GeneralPath) shape).lineTo(422.40997, 696.7739);
        ((GeneralPath) shape).curveTo(422.40997, 695.7509, 422.13098,
                695.29395, 421.21497, 695.29395);
        ((GeneralPath) shape).curveTo(420.72797, 695.29395, 420.31195,
                695.35693, 420.12695, 695.96796);
        ((GeneralPath) shape).lineTo(420.10095, 695.96796);
        ((GeneralPath) shape).lineTo(420.10095, 695.29395);
        ((GeneralPath) shape).lineTo(419.261, 695.29395);
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
        paint = new Color(34, 31, 31, 255);
        stroke = new BasicStroke(1.0f, 0, 0, 10.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(543.975, 691.039);
        ((GeneralPath) shape).lineTo(543.975, 716.552);
        ((GeneralPath) shape).moveTo(557.892, 691.039);
        ((GeneralPath) shape).lineTo(557.892, 716.552);
        ((GeneralPath) shape).moveTo(530.058, 691.039);
        ((GeneralPath) shape).lineTo(530.058, 716.552);
        ((GeneralPath) shape).moveTo(516.142, 691.039);
        ((GeneralPath) shape).lineTo(516.142, 716.552);
        ((GeneralPath) shape).moveTo(502.222, 691.039);
        ((GeneralPath) shape).lineTo(502.222, 716.552);
        ((GeneralPath) shape).moveTo(488.305, 691.039);
        ((GeneralPath) shape).lineTo(488.305, 716.552);
        ((GeneralPath) shape).moveTo(474.389, 691.039);
        ((GeneralPath) shape).lineTo(474.389, 716.552);
        ((GeneralPath) shape).moveTo(460.473, 691.039);
        ((GeneralPath) shape).lineTo(460.473, 716.552);
        ((GeneralPath) shape).moveTo(446.554, 691.039);
        ((GeneralPath) shape).lineTo(446.554, 716.552);
        ((GeneralPath) shape).moveTo(432.636, 691.039);
        ((GeneralPath) shape).lineTo(432.636, 716.552);
        ((GeneralPath) shape).moveTo(405.726, 703.796);
        ((GeneralPath) shape).lineTo(571.838, 703.796);
        ((GeneralPath) shape).moveTo(571.836, 710.38);
        ((GeneralPath) shape).curveTo(571.836, 713.788, 568.961, 716.552,
                565.412, 716.552);
        ((GeneralPath) shape).lineTo(411.676, 716.552);
        ((GeneralPath) shape).curveTo(408.13, 716.552, 405.253, 713.788,
                405.253, 710.38);
        ((GeneralPath) shape).lineTo(405.253, 697.212);
        ((GeneralPath) shape).curveTo(405.253, 693.80396, 408.13, 691.04,
                411.676, 691.04);
        ((GeneralPath) shape).lineTo(565.412, 691.04);
        ((GeneralPath) shape).curveTo(568.961, 691.04, 571.836, 693.80396,
                571.836, 697.212);
        ((GeneralPath) shape).lineTo(571.836, 710.38);
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
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(409.474, 711.315);
        ((GeneralPath) shape).lineTo(409.474, 707.284);
        ((GeneralPath) shape).lineTo(410.953, 707.284);
        ((GeneralPath) shape).curveTo(411.711, 707.284, 411.995, 707.532,
                411.995, 708.532);
        ((GeneralPath) shape).lineTo(411.995, 709.948);
        ((GeneralPath) shape).curveTo(411.995, 710.62, 411.788, 711.315,
                411.119, 711.315);
        ((GeneralPath) shape).lineTo(409.474, 711.315);
        ((GeneralPath) shape).moveTo(408.529, 712.3);
        ((GeneralPath) shape).lineTo(411.11798, 712.3);
        ((GeneralPath) shape).curveTo(412.64297, 712.3, 412.938, 711.196,
                412.938, 709.948);
        ((GeneralPath) shape).lineTo(412.938, 708.532);
        ((GeneralPath) shape).curveTo(412.938, 706.899, 412.34598, 706.3,
                410.952, 706.3);
        ((GeneralPath) shape).lineTo(408.529, 706.3);
        ((GeneralPath) shape).lineTo(408.529, 712.3);
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
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(414.33, 709.8);
        ((GeneralPath) shape).curveTo(414.33, 709.013, 414.378, 708.818,
                415.108, 708.818);
        ((GeneralPath) shape).curveTo(415.798, 708.818, 415.905, 708.886,
                415.905, 709.8);
        ((GeneralPath) shape).lineTo(414.33, 709.8);
        ((GeneralPath) shape).moveTo(415.905, 710.968);
        ((GeneralPath) shape).curveTo(415.905, 711.55, 415.577, 711.55,
                415.108, 711.55);
        ((GeneralPath) shape).curveTo(414.35, 711.55, 414.33, 711.279, 414.33,
                710.425);
        ((GeneralPath) shape).lineTo(416.745, 710.425);
        ((GeneralPath) shape).curveTo(416.745, 708.562, 416.552, 708.05,
                415.108, 708.05);
        ((GeneralPath) shape).curveTo(413.69, 708.05, 413.49, 708.72, 413.49,
                710.253);
        ((GeneralPath) shape).curveTo(413.49, 711.81, 413.76398, 712.3,
                415.108, 712.3);
        ((GeneralPath) shape).curveTo(416.11002, 712.3, 416.745, 712.237,
                416.745, 710.968);
        ((GeneralPath) shape).lineTo(415.905, 710.968);
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
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(418.267, 708.05);
        ((GeneralPath) shape).lineTo(417.426, 708.05);
        ((GeneralPath) shape).lineTo(417.426, 714.175);
        ((GeneralPath) shape).lineTo(418.267, 714.175);
        ((GeneralPath) shape).lineTo(418.267, 711.706);
        ((GeneralPath) shape).lineTo(418.299, 711.706);
        ((GeneralPath) shape).curveTo(418.478, 712.206, 418.92102, 712.3,
                419.34402, 712.3);
        ((GeneralPath) shape).curveTo(420.54202, 712.3, 420.68204, 711.519,
                420.68204, 710.30597);
        ((GeneralPath) shape).curveTo(420.68204, 709.03094, 420.68204, 708.05,
                419.34402, 708.05);
        ((GeneralPath) shape).curveTo(418.85904, 708.05, 418.47702, 708.177,
                418.26703, 708.724);
        ((GeneralPath) shape).lineTo(418.26703, 708.05);
        ((GeneralPath) shape).moveTo(419.1, 711.55);
        ((GeneralPath) shape).curveTo(418.379, 711.55, 418.267, 711.195,
                418.267, 710.30597);
        ((GeneralPath) shape).curveTo(418.267, 709.316, 418.267, 708.818,
                419.1, 708.818);
        ((GeneralPath) shape).curveTo(419.841, 708.818, 419.841, 709.365,
                419.841, 710.30597);
        ((GeneralPath) shape).curveTo(419.841, 711.353, 419.676, 711.55, 419.1,
                711.55);
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
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(420.988, 708.8);
        ((GeneralPath) shape).lineTo(421.431, 708.8);
        ((GeneralPath) shape).lineTo(421.431, 710.989);
        ((GeneralPath) shape).curveTo(421.431, 712.046, 421.716, 712.3,
                422.655, 712.3);
        ((GeneralPath) shape).curveTo(423.575, 712.3, 423.845, 711.917,
                423.845, 710.64996);
        ((GeneralPath) shape).lineTo(423.111, 710.64996);
        ((GeneralPath) shape).curveTo(423.111, 711.095, 423.164, 711.55,
                422.655, 711.55);
        ((GeneralPath) shape).curveTo(422.271, 711.55, 422.271, 711.372,
                422.271, 710.982);
        ((GeneralPath) shape).lineTo(422.271, 708.8);
        ((GeneralPath) shape).lineTo(423.667, 708.8);
        ((GeneralPath) shape).lineTo(423.667, 708.05);
        ((GeneralPath) shape).lineTo(422.271, 708.05);
        ((GeneralPath) shape).lineTo(422.271, 707.079);
        ((GeneralPath) shape).lineTo(421.431, 707.079);
        ((GeneralPath) shape).lineTo(421.431, 708.05);
        ((GeneralPath) shape).lineTo(420.988, 708.05);
        ((GeneralPath) shape).lineTo(420.988, 708.8);
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
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(425.127, 706.3);
        ((GeneralPath) shape).lineTo(424.28702, 706.3);
        ((GeneralPath) shape).lineTo(424.28702, 712.3);
        ((GeneralPath) shape).lineTo(425.127, 712.3);
        ((GeneralPath) shape).lineTo(425.127, 709.98596);
        ((GeneralPath) shape).curveTo(425.127, 709.23395, 425.226, 708.81793,
                425.95102, 708.81793);
        ((GeneralPath) shape).curveTo(426.48502, 708.81793, 426.59702,
                709.0269, 426.59702, 709.6339);
        ((GeneralPath) shape).lineTo(426.59702, 712.2999);
        ((GeneralPath) shape).lineTo(427.437, 712.2999);
        ((GeneralPath) shape).lineTo(427.437, 709.5299);
        ((GeneralPath) shape).curveTo(427.437, 708.5069, 427.15802, 708.0499,
                426.241, 708.0499);
        ((GeneralPath) shape).curveTo(425.754, 708.0499, 425.339, 708.11395,
                425.15298, 708.7299);
        ((GeneralPath) shape).lineTo(425.12698, 708.7299);
        ((GeneralPath) shape).lineTo(425.12698, 706.3);
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
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(439.682, 693.549);
        ((GeneralPath) shape).lineTo(438.901, 693.549);
        ((GeneralPath) shape).lineTo(437.51, 695.348);
        ((GeneralPath) shape).lineTo(437.867, 695.756);
        ((GeneralPath) shape).lineTo(439.157, 694.067);
        ((GeneralPath) shape).lineTo(439.157, 699.549);
        ((GeneralPath) shape).lineTo(439.682, 699.549);
        ((GeneralPath) shape).lineTo(439.682, 693.549);
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
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(454.738, 698.949);
        ((GeneralPath) shape).lineTo(451.693, 698.949);
        ((GeneralPath) shape).lineTo(451.693, 698.44196);
        ((GeneralPath) shape).curveTo(451.693, 697.57196, 451.957, 697.43695,
                453.035, 697.334);
        ((GeneralPath) shape).curveTo(454.277, 697.21497, 454.738, 697.01,
                454.738, 695.432);
        ((GeneralPath) shape).curveTo(454.738, 693.922, 454.33002, 693.549,
                453.02002, 693.549);
        ((GeneralPath) shape).curveTo(451.30502, 693.549, 451.16702, 694.026,
                451.16702, 695.328);
        ((GeneralPath) shape).lineTo(451.16702, 695.638);
        ((GeneralPath) shape).lineTo(451.69202, 695.638);
        ((GeneralPath) shape).lineTo(451.69202, 695.328);
        ((GeneralPath) shape).curveTo(451.69202, 694.236, 451.81802, 694.148,
                453.019, 694.148);
        ((GeneralPath) shape).curveTo(453.98203, 694.148, 454.211, 694.203,
                454.211, 695.431);
        ((GeneralPath) shape).curveTo(454.211, 696.66705, 453.979, 696.611,
                452.919, 696.73804);
        ((GeneralPath) shape).curveTo(451.719, 696.887, 451.16602, 696.99207,
                451.16602, 698.536);
        ((GeneralPath) shape).lineTo(451.16602, 699.549);
        ((GeneralPath) shape).lineTo(454.73602, 699.549);
        ((GeneralPath) shape).lineTo(454.73602, 698.949);
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
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(493.395, 694.148);
        ((GeneralPath) shape).lineTo(496.249, 694.148);
        ((GeneralPath) shape).lineTo(496.249, 693.54803);
        ((GeneralPath) shape).lineTo(492.871, 693.54803);
        ((GeneralPath) shape).lineTo(492.871, 696.54803);
        ((GeneralPath) shape).lineTo(493.395, 696.54803);
        ((GeneralPath) shape).curveTo(493.49, 695.94806, 494.20398, 696.04803,
                494.61798, 696.04803);
        ((GeneralPath) shape).curveTo(495.71198, 696.04803, 495.916, 696.179,
                495.916, 697.426);
        ((GeneralPath) shape).curveTo(495.916, 698.71204, 495.813, 698.92303,
                494.61798, 698.92303);
        ((GeneralPath) shape).curveTo(493.70697, 698.92303, 493.291, 698.92303,
                493.291, 697.98706);
        ((GeneralPath) shape).lineTo(493.291, 697.7881);
        ((GeneralPath) shape).lineTo(492.766, 697.7881);
        ((GeneralPath) shape).lineTo(492.766, 697.9311);
        ((GeneralPath) shape).curveTo(492.871, 699.38007, 493.296, 699.5481,
                494.619, 699.5481);
        ((GeneralPath) shape).curveTo(496.11798, 699.5481, 496.44098, 699.3001,
                496.44098, 697.4261);
        ((GeneralPath) shape).curveTo(496.44098, 696.2851, 496.32297, 695.4231,
                494.783, 695.4231);
        ((GeneralPath) shape).curveTo(494.348, 695.4231, 493.654, 695.4231,
                493.41, 695.9231);
        ((GeneralPath) shape).lineTo(493.396, 695.9231);
        ((GeneralPath) shape).lineTo(493.396, 694.148);
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
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(466.317, 696.799);
        ((GeneralPath) shape).lineTo(466.942, 696.799);
        ((GeneralPath) shape).curveTo(467.692, 696.799, 468.11298, 696.799,
                468.11298, 697.774);
        ((GeneralPath) shape).curveTo(468.11298, 698.87897, 467.94397, 698.924,
                466.71298, 698.924);
        ((GeneralPath) shape).curveTo(465.623, 698.924, 465.48798, 698.893,
                465.48798, 697.98804);
        ((GeneralPath) shape).lineTo(465.48798, 697.601);
        ((GeneralPath) shape).lineTo(464.964, 697.601);
        ((GeneralPath) shape).lineTo(464.964, 697.98804);
        ((GeneralPath) shape).curveTo(464.964, 699.361, 465.599, 699.549,
                466.72598, 699.549);
        ((GeneralPath) shape).curveTo(467.90598, 699.549, 468.63898, 699.61304,
                468.63898, 697.841);
        ((GeneralPath) shape).curveTo(468.63898, 696.981, 468.477, 696.565,
                467.735, 696.494);
        ((GeneralPath) shape).lineTo(467.735, 696.478);
        ((GeneralPath) shape).curveTo(468.444, 696.41504, 468.534, 695.853,
                468.534, 695.093);
        ((GeneralPath) shape).curveTo(468.534, 693.54803, 467.841, 693.54803,
                466.719, 693.54803);
        ((GeneralPath) shape).curveTo(465.56198, 693.54803, 464.965, 693.64404,
                464.965, 695.16504);
        ((GeneralPath) shape).lineTo(464.965, 695.41705);
        ((GeneralPath) shape).lineTo(465.48898, 695.41705);
        ((GeneralPath) shape).lineTo(465.48898, 695.16504);
        ((GeneralPath) shape).curveTo(465.48898, 694.241, 465.63098, 694.14703,
                466.641, 694.14703);
        ((GeneralPath) shape).curveTo(467.883, 694.14703, 468.009, 694.23303,
                468.009, 695.21106);
        ((GeneralPath) shape).curveTo(468.009, 696.1971, 467.693, 696.17206,
                466.943, 696.17206);
        ((GeneralPath) shape).lineTo(466.318, 696.17206);
        ((GeneralPath) shape).lineTo(466.318, 696.799);
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
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(481.49, 697.549);
        ((GeneralPath) shape).lineTo(479.179, 697.549);
        ((GeneralPath) shape).lineTo(481.47598, 693.98804);
        ((GeneralPath) shape).lineTo(481.49, 693.98804);
        ((GeneralPath) shape).lineTo(481.49, 697.549);
        ((GeneralPath) shape).moveTo(482.015, 693.549);
        ((GeneralPath) shape).lineTo(481.195, 693.549);
        ((GeneralPath) shape).lineTo(478.655, 697.341);
        ((GeneralPath) shape).lineTo(478.655, 698.174);
        ((GeneralPath) shape).lineTo(481.491, 698.174);
        ((GeneralPath) shape).lineTo(481.491, 699.549);
        ((GeneralPath) shape).lineTo(482.01498, 699.549);
        ((GeneralPath) shape).lineTo(482.01498, 698.174);
        ((GeneralPath) shape).lineTo(482.74698, 698.174);
        ((GeneralPath) shape).lineTo(482.74698, 697.549);
        ((GeneralPath) shape).lineTo(482.01498, 697.549);
        ((GeneralPath) shape).lineTo(482.01498, 693.549);
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
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(506.669, 697.439);
        ((GeneralPath) shape).curveTo(506.669, 699.294, 507.117, 699.54803,
                508.502, 699.54803);
        ((GeneralPath) shape).curveTo(509.91302, 699.54803, 510.34402,
                699.28406, 510.34402, 697.67303);
        ((GeneralPath) shape).curveTo(510.34402, 695.989, 509.88004, 695.79803,
                508.502, 695.79803);
        ((GeneralPath) shape).curveTo(507.721, 695.79803, 507.324, 695.981,
                507.194, 696.37103);
        ((GeneralPath) shape).lineTo(507.194, 695.12805);
        ((GeneralPath) shape).curveTo(507.194, 694.1481, 507.586, 694.1481,
                508.529, 694.1481);
        ((GeneralPath) shape).curveTo(509.3, 694.1481, 509.714, 694.1481,
                509.714, 694.9081);
        ((GeneralPath) shape).lineTo(509.714, 695.0671);
        ((GeneralPath) shape).lineTo(510.23898, 695.0671);
        ((GeneralPath) shape).lineTo(510.23898, 694.8821);
        ((GeneralPath) shape).curveTo(510.23898, 693.5481, 509.348, 693.5481,
                508.53098, 693.5481);
        ((GeneralPath) shape).curveTo(507.36597, 693.5481, 506.66898, 693.7181,
                506.66898, 695.0991);
        ((GeneralPath) shape).lineTo(506.66898, 697.439);
        ((GeneralPath) shape).moveTo(508.626, 696.424);
        ((GeneralPath) shape).curveTo(509.71802, 696.424, 509.819, 696.565,
                509.819, 697.674);
        ((GeneralPath) shape).curveTo(509.819, 698.729, 509.771, 698.924,
                508.502, 698.924);
        ((GeneralPath) shape).curveTo(507.29602, 698.924, 507.194, 698.713,
                507.194, 697.44);
        ((GeneralPath) shape).curveTo(507.194, 696.494, 507.506, 696.424,
                508.626, 696.424);
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
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(524.351, 693.549);
        ((GeneralPath) shape).lineTo(520.619, 693.549);
        ((GeneralPath) shape).lineTo(520.619, 694.109);
        ((GeneralPath) shape).lineTo(523.825, 694.109);
        ((GeneralPath) shape).lineTo(521.178, 699.549);
        ((GeneralPath) shape).lineTo(521.851, 699.549);
        ((GeneralPath) shape).lineTo(524.351, 694.406);
        ((GeneralPath) shape).lineTo(524.351, 693.549);
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
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(534.996, 697.888);
        ((GeneralPath) shape).curveTo(534.996, 696.875, 535.18396, 696.799,
                536.30597, 696.799);
        ((GeneralPath) shape).curveTo(537.474, 696.799, 537.621, 696.93,
                537.621, 697.888);
        ((GeneralPath) shape).curveTo(537.621, 698.732, 537.433, 698.924,
                536.30597, 698.924);
        ((GeneralPath) shape).curveTo(535.063, 698.924, 534.996, 698.816,
                534.996, 697.888);
        ((GeneralPath) shape).moveTo(534.996, 695.086);
        ((GeneralPath) shape).curveTo(534.996, 694.242, 535.08795, 694.148,
                536.30597, 694.148);
        ((GeneralPath) shape).curveTo(537.52997, 694.148, 537.621, 694.275,
                537.621, 695.086);
        ((GeneralPath) shape).curveTo(537.621, 696.016, 537.51495, 696.174,
                536.30597, 696.174);
        ((GeneralPath) shape).curveTo(535.082, 696.174, 534.996, 695.984,
                534.996, 695.086);
        ((GeneralPath) shape).moveTo(535.244, 696.465);
        ((GeneralPath) shape).curveTo(534.53, 696.61304, 534.471, 697.043,
                534.471, 697.888);
        ((GeneralPath) shape).curveTo(534.471, 699.549, 535.13403, 699.549,
                536.309, 699.549);
        ((GeneralPath) shape).curveTo(537.74005, 699.549, 538.14703, 699.377,
                538.14703, 697.888);
        ((GeneralPath) shape).curveTo(538.14703, 696.942, 537.991, 696.59,
                537.38, 696.465);
        ((GeneralPath) shape).lineTo(537.38, 696.45703);
        ((GeneralPath) shape).curveTo(538.08203, 696.30505, 538.14703,
                695.85205, 538.14703, 695.08606);
        ((GeneralPath) shape).curveTo(538.14703, 693.5491, 537.57306, 693.5491,
                536.306, 693.5491);
        ((GeneralPath) shape).curveTo(535.049, 693.5491, 534.471, 693.61206,
                534.471, 695.08606);
        ((GeneralPath) shape).curveTo(534.471, 695.97107, 534.588, 696.30707,
                535.244, 696.44904);
        ((GeneralPath) shape).lineTo(535.244, 696.465);
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
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(548.372, 698.161);
        ((GeneralPath) shape).curveTo(548.372, 699.549, 549.011, 699.549,
                550.26, 699.549);
        ((GeneralPath) shape).curveTo(551.894, 699.549, 552.153, 699.156,
                552.153, 697.611);
        ((GeneralPath) shape).lineTo(552.153, 695.379);
        ((GeneralPath) shape).curveTo(552.153, 693.802, 551.70404, 693.549,
                550.26, 693.549);
        ((GeneralPath) shape).curveTo(548.781, 693.549, 548.372, 693.795,
                548.372, 695.379);
        ((GeneralPath) shape).curveTo(548.372, 696.961, 548.84503, 697.174,
                550.268, 697.174);
        ((GeneralPath) shape).curveTo(550.473, 697.174, 551.38, 697.174,
                551.614, 696.615);
        ((GeneralPath) shape).lineTo(551.629, 696.633);
        ((GeneralPath) shape).lineTo(551.629, 697.612);
        ((GeneralPath) shape).curveTo(551.629, 698.876, 551.38104, 698.925,
                550.26105, 698.925);
        ((GeneralPath) shape).curveTo(549.137, 698.925, 548.89905, 698.86096,
                548.89905, 698.162);
        ((GeneralPath) shape).lineTo(548.89905, 697.936);
        ((GeneralPath) shape).lineTo(548.374, 697.936);
        ((GeneralPath) shape).lineTo(548.374, 698.161);
        ((GeneralPath) shape).moveTo(550.26, 696.549);
        ((GeneralPath) shape).curveTo(549.09, 696.549, 548.898, 696.473,
                548.898, 695.379);
        ((GeneralPath) shape).curveTo(548.898, 694.18604, 549.039, 694.14905,
                550.26, 694.14905);
        ((GeneralPath) shape).curveTo(551.323, 694.14905, 551.628, 694.14905,
                551.628, 695.379);
        ((GeneralPath) shape).curveTo(551.628, 696.473, 551.486, 696.549,
                550.26, 696.549);
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
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(562.475, 693.549);
        ((GeneralPath) shape).lineTo(561.694, 693.549);
        ((GeneralPath) shape).lineTo(560.303, 695.348);
        ((GeneralPath) shape).lineTo(560.66, 695.756);
        ((GeneralPath) shape).lineTo(561.95, 694.067);
        ((GeneralPath) shape).lineTo(561.95, 699.549);
        ((GeneralPath) shape).lineTo(562.475, 699.549);
        ((GeneralPath) shape).lineTo(562.475, 693.549);
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
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(566.485, 699.549);
        ((GeneralPath) shape).curveTo(568.037, 699.549, 568.579, 699.302,
                568.579, 697.396);
        ((GeneralPath) shape).lineTo(568.579, 695.676);
        ((GeneralPath) shape).curveTo(568.579, 693.793, 568.037, 693.549,
                566.47296, 693.549);
        ((GeneralPath) shape).curveTo(564.92, 693.549, 564.37897, 693.793,
                564.37897, 695.676);
        ((GeneralPath) shape).lineTo(564.37897, 697.396);
        ((GeneralPath) shape).curveTo(564.379, 699.302, 564.92, 699.549,
                566.485, 699.549);
        ((GeneralPath) shape).moveTo(566.473, 694.148);
        ((GeneralPath) shape).curveTo(567.72504, 694.148, 568.054, 694.148,
                568.054, 695.675);
        ((GeneralPath) shape).lineTo(568.054, 697.39496);
        ((GeneralPath) shape).curveTo(568.054, 698.923, 567.72504, 698.923,
                566.473, 698.923);
        ((GeneralPath) shape).curveTo(565.234, 698.923, 564.905, 698.923,
                564.905, 697.39496);
        ((GeneralPath) shape).lineTo(564.905, 695.675);
        ((GeneralPath) shape).curveTo(564.904, 694.148, 565.233, 694.148,
                566.473, 694.148);
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
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(410.523, 721.461);
        ((GeneralPath) shape).lineTo(412.002, 721.461);
        ((GeneralPath) shape).lineTo(412.002, 720.477);
        ((GeneralPath) shape).lineTo(408.101, 720.477);
        ((GeneralPath) shape).lineTo(408.101, 721.461);
        ((GeneralPath) shape).lineTo(409.579, 721.461);
        ((GeneralPath) shape).lineTo(409.579, 726.477);
        ((GeneralPath) shape).lineTo(410.523, 726.477);
        ((GeneralPath) shape).lineTo(410.523, 721.461);
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
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(415.207, 726.477);
        ((GeneralPath) shape).lineTo(415.207, 722.227);
        ((GeneralPath) shape).lineTo(414.366, 722.227);
        ((GeneralPath) shape).lineTo(414.366, 724.665);
        ((GeneralPath) shape).curveTo(414.366, 725.397, 414.155, 725.72797,
                413.495, 725.72797);
        ((GeneralPath) shape).curveTo(412.944, 725.72797, 412.896, 725.496,
                412.896, 724.89197);
        ((GeneralPath) shape).lineTo(412.896, 722.22797);
        ((GeneralPath) shape).lineTo(412.056, 722.22797);
        ((GeneralPath) shape).lineTo(412.056, 725.269);
        ((GeneralPath) shape).curveTo(412.056, 726.18896, 412.529, 726.47797,
                413.236, 726.47797);
        ((GeneralPath) shape).curveTo(413.72598, 726.47797, 414.16998, 726.345,
                414.366, 725.80194);
        ((GeneralPath) shape).lineTo(414.366, 726.47797);
        ((GeneralPath) shape).lineTo(415.207, 726.47797);
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
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(415.937, 722.227);
        ((GeneralPath) shape).lineTo(415.937, 726.477);
        ((GeneralPath) shape).lineTo(416.778, 726.477);
        ((GeneralPath) shape).lineTo(416.778, 723.883);
        ((GeneralPath) shape).curveTo(416.778, 723.338, 416.933, 722.994,
                417.46802, 722.994);
        ((GeneralPath) shape).curveTo(417.897, 722.994, 417.932, 723.242,
                417.932, 723.666);
        ((GeneralPath) shape).lineTo(417.932, 723.883);
        ((GeneralPath) shape).lineTo(418.772, 723.883);
        ((GeneralPath) shape).lineTo(418.772, 723.547);
        ((GeneralPath) shape).curveTo(418.772, 722.75397, 418.579, 722.227,
                417.78702, 722.227);
        ((GeneralPath) shape).curveTo(417.34802, 722.227, 416.958, 722.36,
                416.778, 722.815);
        ((GeneralPath) shape).lineTo(416.778, 722.227);
        ((GeneralPath) shape).lineTo(415.937, 722.227);
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
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(419.261, 722.227);
        ((GeneralPath) shape).lineTo(419.261, 726.477);
        ((GeneralPath) shape).lineTo(420.10098, 726.477);
        ((GeneralPath) shape).lineTo(420.10098, 724.16296);
        ((GeneralPath) shape).curveTo(420.10098, 723.41095, 420.19998,
                722.99493, 420.92398, 722.99493);
        ((GeneralPath) shape).curveTo(421.45898, 722.99493, 421.56998,
                723.2039, 421.56998, 723.8109);
        ((GeneralPath) shape).lineTo(421.56998, 726.4769);
        ((GeneralPath) shape).lineTo(422.40997, 726.4769);
        ((GeneralPath) shape).lineTo(422.40997, 723.7069);
        ((GeneralPath) shape).curveTo(422.40997, 722.6839, 422.13196, 722.2269,
                421.21497, 722.2269);
        ((GeneralPath) shape).curveTo(420.72797, 722.2269, 420.31195, 722.2899,
                420.12695, 722.90094);
        ((GeneralPath) shape).lineTo(420.10095, 722.90094);
        ((GeneralPath) shape).lineTo(420.10095, 722.2269);
        ((GeneralPath) shape).lineTo(419.261, 722.2269);
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
        paint = new Color(34, 31, 31, 255);
        stroke = new BasicStroke(1.0f, 0, 0, 10.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(543.975, 717.969);
        ((GeneralPath) shape).lineTo(543.975, 743.482);
        ((GeneralPath) shape).moveTo(557.892, 717.969);
        ((GeneralPath) shape).lineTo(557.892, 743.482);
        ((GeneralPath) shape).moveTo(530.058, 717.969);
        ((GeneralPath) shape).lineTo(530.058, 743.482);
        ((GeneralPath) shape).moveTo(516.142, 717.969);
        ((GeneralPath) shape).lineTo(516.142, 743.482);
        ((GeneralPath) shape).moveTo(502.222, 717.969);
        ((GeneralPath) shape).lineTo(502.222, 743.482);
        ((GeneralPath) shape).moveTo(488.305, 717.969);
        ((GeneralPath) shape).lineTo(488.305, 743.482);
        ((GeneralPath) shape).moveTo(474.389, 717.969);
        ((GeneralPath) shape).lineTo(474.389, 743.482);
        ((GeneralPath) shape).moveTo(460.473, 717.969);
        ((GeneralPath) shape).lineTo(460.473, 743.482);
        ((GeneralPath) shape).moveTo(446.554, 717.969);
        ((GeneralPath) shape).lineTo(446.554, 743.482);
        ((GeneralPath) shape).moveTo(432.636, 717.969);
        ((GeneralPath) shape).lineTo(432.636, 743.482);
        ((GeneralPath) shape).moveTo(405.726, 730.726);
        ((GeneralPath) shape).lineTo(571.838, 730.726);
        ((GeneralPath) shape).moveTo(571.836, 737.31);
        ((GeneralPath) shape).curveTo(571.836, 740.718, 568.961, 743.482,
                565.412, 743.482);
        ((GeneralPath) shape).lineTo(411.676, 743.482);
        ((GeneralPath) shape).curveTo(408.13, 743.482, 405.253, 740.718,
                405.253, 737.31);
        ((GeneralPath) shape).lineTo(405.253, 724.14197);
        ((GeneralPath) shape).curveTo(405.253, 720.73395, 408.13, 717.97,
                411.676, 717.97);
        ((GeneralPath) shape).lineTo(565.412, 717.97);
        ((GeneralPath) shape).curveTo(568.961, 717.97, 571.836, 720.73395,
                571.836, 724.14197);
        ((GeneralPath) shape).lineTo(571.836, 737.31);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(409.474, 738.245);
        ((GeneralPath) shape).lineTo(409.474, 734.214);
        ((GeneralPath) shape).lineTo(410.953, 734.214);
        ((GeneralPath) shape).curveTo(411.711, 734.214, 411.995, 734.462,
                411.995, 735.462);
        ((GeneralPath) shape).lineTo(411.995, 736.878);
        ((GeneralPath) shape).curveTo(411.995, 737.55, 411.788, 738.245,
                411.119, 738.245);
        ((GeneralPath) shape).lineTo(409.474, 738.245);
        ((GeneralPath) shape).moveTo(408.529, 739.229);
        ((GeneralPath) shape).lineTo(411.11798, 739.229);
        ((GeneralPath) shape).curveTo(412.64297, 739.229, 412.938, 738.125,
                412.938, 736.877);
        ((GeneralPath) shape).lineTo(412.938, 735.461);
        ((GeneralPath) shape).curveTo(412.938, 733.828, 412.34598, 733.229,
                410.952, 733.229);
        ((GeneralPath) shape).lineTo(408.529, 733.229);
        ((GeneralPath) shape).lineTo(408.529, 739.229);
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
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(414.33, 736.729);
        ((GeneralPath) shape).curveTo(414.33, 735.942, 414.378, 735.747,
                415.108, 735.747);
        ((GeneralPath) shape).curveTo(415.798, 735.747, 415.905, 735.815,
                415.905, 736.729);
        ((GeneralPath) shape).lineTo(414.33, 736.729);
        ((GeneralPath) shape).moveTo(415.905, 737.896);
        ((GeneralPath) shape).curveTo(415.905, 738.479, 415.577, 738.479,
                415.108, 738.479);
        ((GeneralPath) shape).curveTo(414.35, 738.479, 414.33, 738.208, 414.33,
                737.354);
        ((GeneralPath) shape).lineTo(416.745, 737.354);
        ((GeneralPath) shape).curveTo(416.745, 735.491, 416.552, 734.979,
                415.108, 734.979);
        ((GeneralPath) shape).curveTo(413.69, 734.979, 413.49, 735.649, 413.49,
                737.182);
        ((GeneralPath) shape).curveTo(413.49, 738.739, 413.76398, 739.229,
                415.108, 739.229);
        ((GeneralPath) shape).curveTo(416.11002, 739.229, 416.745, 739.166,
                416.745, 737.896);
        ((GeneralPath) shape).lineTo(415.905, 737.896);
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
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(418.267, 734.979);
        ((GeneralPath) shape).lineTo(417.426, 734.979);
        ((GeneralPath) shape).lineTo(417.426, 741.104);
        ((GeneralPath) shape).lineTo(418.267, 741.104);
        ((GeneralPath) shape).lineTo(418.267, 738.635);
        ((GeneralPath) shape).lineTo(418.299, 738.635);
        ((GeneralPath) shape).curveTo(418.478, 739.135, 418.92102, 739.229,
                419.34402, 739.229);
        ((GeneralPath) shape).curveTo(420.54202, 739.229, 420.68204, 738.448,
                420.68204, 737.235);
        ((GeneralPath) shape).curveTo(420.68204, 735.95996, 420.68204, 734.979,
                419.34402, 734.979);
        ((GeneralPath) shape).curveTo(418.85904, 734.979, 418.47702, 735.106,
                418.26703, 735.653);
        ((GeneralPath) shape).lineTo(418.26703, 734.979);
        ((GeneralPath) shape).moveTo(419.1, 738.479);
        ((GeneralPath) shape).curveTo(418.379, 738.479, 418.267, 738.123,
                418.267, 737.235);
        ((GeneralPath) shape).curveTo(418.267, 736.245, 418.267, 735.747,
                419.1, 735.747);
        ((GeneralPath) shape).curveTo(419.841, 735.747, 419.841, 736.294,
                419.841, 737.235);
        ((GeneralPath) shape).curveTo(419.841, 738.282, 419.676, 738.479,
                419.1, 738.479);
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
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(420.988, 735.729);
        ((GeneralPath) shape).lineTo(421.431, 735.729);
        ((GeneralPath) shape).lineTo(421.431, 737.918);
        ((GeneralPath) shape).curveTo(421.431, 738.97504, 421.716, 739.229,
                422.655, 739.229);
        ((GeneralPath) shape).curveTo(423.575, 739.229, 423.845, 738.846,
                423.845, 737.579);
        ((GeneralPath) shape).lineTo(423.111, 737.579);
        ((GeneralPath) shape).curveTo(423.111, 738.024, 423.164, 738.479,
                422.655, 738.479);
        ((GeneralPath) shape).curveTo(422.271, 738.479, 422.271, 738.301,
                422.271, 737.911);
        ((GeneralPath) shape).lineTo(422.271, 735.729);
        ((GeneralPath) shape).lineTo(423.667, 735.729);
        ((GeneralPath) shape).lineTo(423.667, 734.979);
        ((GeneralPath) shape).lineTo(422.271, 734.979);
        ((GeneralPath) shape).lineTo(422.271, 734.008);
        ((GeneralPath) shape).lineTo(421.431, 734.008);
        ((GeneralPath) shape).lineTo(421.431, 734.979);
        ((GeneralPath) shape).lineTo(420.988, 734.979);
        ((GeneralPath) shape).lineTo(420.988, 735.729);
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
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(425.127, 733.229);
        ((GeneralPath) shape).lineTo(424.28702, 733.229);
        ((GeneralPath) shape).lineTo(424.28702, 739.229);
        ((GeneralPath) shape).lineTo(425.127, 739.229);
        ((GeneralPath) shape).lineTo(425.127, 736.915);
        ((GeneralPath) shape).curveTo(425.127, 736.162, 425.226, 735.74695,
                425.95102, 735.74695);
        ((GeneralPath) shape).curveTo(426.48502, 735.74695, 426.59702,
                735.95593, 426.59702, 736.5629);
        ((GeneralPath) shape).lineTo(426.59702, 739.22894);
        ((GeneralPath) shape).lineTo(427.437, 739.22894);
        ((GeneralPath) shape).lineTo(427.437, 736.4589);
        ((GeneralPath) shape).curveTo(427.437, 735.4359, 427.15802, 734.97894,
                426.241, 734.97894);
        ((GeneralPath) shape).curveTo(425.754, 734.97894, 425.339, 735.04297,
                425.15298, 735.65894);
        ((GeneralPath) shape).lineTo(425.12698, 735.65894);
        ((GeneralPath) shape).lineTo(425.12698, 733.229);
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
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(437.363, 720.478);
        ((GeneralPath) shape).lineTo(436.583, 720.478);
        ((GeneralPath) shape).lineTo(435.191, 722.276);
        ((GeneralPath) shape).lineTo(435.549, 722.685);
        ((GeneralPath) shape).lineTo(436.839, 720.997);
        ((GeneralPath) shape).lineTo(436.839, 726.478);
        ((GeneralPath) shape).lineTo(437.363, 726.478);
        ((GeneralPath) shape).lineTo(437.363, 720.478);
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
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(442.0, 720.478);
        ((GeneralPath) shape).lineTo(441.22, 720.478);
        ((GeneralPath) shape).lineTo(439.828, 722.276);
        ((GeneralPath) shape).lineTo(440.186, 722.685);
        ((GeneralPath) shape).lineTo(441.476, 720.997);
        ((GeneralPath) shape).lineTo(441.476, 726.478);
        ((GeneralPath) shape).lineTo(442.0, 726.478);
        ((GeneralPath) shape).lineTo(442.0, 720.478);
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
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(451.268, 720.478);
        ((GeneralPath) shape).lineTo(450.486, 720.478);
        ((GeneralPath) shape).lineTo(449.095, 722.276);
        ((GeneralPath) shape).lineTo(449.452, 722.685);
        ((GeneralPath) shape).lineTo(450.742, 720.997);
        ((GeneralPath) shape).lineTo(450.742, 726.478);
        ((GeneralPath) shape).lineTo(451.268, 726.478);
        ((GeneralPath) shape).lineTo(451.268, 720.478);
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
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(457.059, 725.878);
        ((GeneralPath) shape).lineTo(454.01398, 725.878);
        ((GeneralPath) shape).lineTo(454.01398, 725.372);
        ((GeneralPath) shape).curveTo(454.01398, 724.50104, 454.279, 724.366,
                455.356, 724.263);
        ((GeneralPath) shape).curveTo(456.598, 724.144, 457.059, 723.939,
                457.059, 722.361);
        ((GeneralPath) shape).curveTo(457.059, 720.851, 456.651, 720.478,
                455.341, 720.478);
        ((GeneralPath) shape).curveTo(453.627, 720.478, 453.489, 720.955,
                453.489, 722.257);
        ((GeneralPath) shape).lineTo(453.489, 722.568);
        ((GeneralPath) shape).lineTo(454.013, 722.568);
        ((GeneralPath) shape).lineTo(454.013, 722.257);
        ((GeneralPath) shape).curveTo(454.013, 721.16504, 454.14, 721.077,
                455.34, 721.077);
        ((GeneralPath) shape).curveTo(456.304, 721.077, 456.533, 721.132,
                456.533, 722.36005);
        ((GeneralPath) shape).curveTo(456.533, 723.59607, 456.3, 723.54004,
                455.24, 723.66705);
        ((GeneralPath) shape).curveTo(454.041, 723.8171, 453.48798, 723.9211,
                453.48798, 725.46606);
        ((GeneralPath) shape).lineTo(453.48798, 726.4781);
        ((GeneralPath) shape).lineTo(457.05698, 726.4781);
        ((GeneralPath) shape).lineTo(457.05698, 725.878);
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
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(492.965, 720.478);
        ((GeneralPath) shape).lineTo(492.184, 720.478);
        ((GeneralPath) shape).lineTo(490.793, 722.276);
        ((GeneralPath) shape).lineTo(491.15, 722.685);
        ((GeneralPath) shape).lineTo(492.439, 720.997);
        ((GeneralPath) shape).lineTo(492.439, 726.478);
        ((GeneralPath) shape).lineTo(492.965, 726.478);
        ((GeneralPath) shape).lineTo(492.965, 720.478);
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
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(495.712, 721.077);
        ((GeneralPath) shape).lineTo(498.565, 721.077);
        ((GeneralPath) shape).lineTo(498.565, 720.47705);
        ((GeneralPath) shape).lineTo(495.187, 720.47705);
        ((GeneralPath) shape).lineTo(495.187, 723.47705);
        ((GeneralPath) shape).lineTo(495.712, 723.47705);
        ((GeneralPath) shape).curveTo(495.807, 722.8771, 496.521, 722.97705,
                496.934, 722.97705);
        ((GeneralPath) shape).curveTo(498.029, 722.97705, 498.232, 723.10803,
                498.232, 724.3561);
        ((GeneralPath) shape).curveTo(498.232, 725.64105, 498.13, 725.85205,
                496.934, 725.85205);
        ((GeneralPath) shape).curveTo(496.024, 725.85205, 495.607, 725.85205,
                495.607, 724.9161);
        ((GeneralPath) shape).lineTo(495.607, 724.7171);
        ((GeneralPath) shape).lineTo(495.083, 724.7171);
        ((GeneralPath) shape).lineTo(495.083, 724.8601);
        ((GeneralPath) shape).curveTo(495.187, 726.3091, 495.612, 726.4771,
                496.935, 726.4771);
        ((GeneralPath) shape).curveTo(498.435, 726.4771, 498.758, 726.2291,
                498.758, 724.35614);
        ((GeneralPath) shape).curveTo(498.758, 723.21515, 498.63998, 722.3521,
                497.099, 722.3521);
        ((GeneralPath) shape).curveTo(496.664, 722.3521, 495.97, 722.3521,
                495.726, 722.8521);
        ((GeneralPath) shape).lineTo(495.713, 722.8521);
        ((GeneralPath) shape).lineTo(495.713, 721.077);
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
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(465.165, 720.478);
        ((GeneralPath) shape).lineTo(464.384, 720.478);
        ((GeneralPath) shape).lineTo(462.993, 722.276);
        ((GeneralPath) shape).lineTo(463.351, 722.685);
        ((GeneralPath) shape).lineTo(464.64, 720.997);
        ((GeneralPath) shape).lineTo(464.64, 726.478);
        ((GeneralPath) shape).lineTo(465.165, 726.478);
        ((GeneralPath) shape).lineTo(465.165, 720.478);
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
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(468.636, 723.728);
        ((GeneralPath) shape).lineTo(469.261, 723.728);
        ((GeneralPath) shape).curveTo(470.011, 723.728, 470.43198, 723.728,
                470.43198, 724.703);
        ((GeneralPath) shape).curveTo(470.43198, 725.808, 470.26297, 725.853,
                469.03198, 725.853);
        ((GeneralPath) shape).curveTo(467.942, 725.853, 467.80698, 725.822,
                467.80698, 724.91705);
        ((GeneralPath) shape).lineTo(467.80698, 724.53);
        ((GeneralPath) shape).lineTo(467.283, 724.53);
        ((GeneralPath) shape).lineTo(467.283, 724.91705);
        ((GeneralPath) shape).curveTo(467.283, 726.29004, 467.918, 726.478,
                469.04498, 726.478);
        ((GeneralPath) shape).curveTo(470.22498, 726.478, 470.95798, 726.54205,
                470.95798, 724.77106);
        ((GeneralPath) shape).curveTo(470.95798, 723.91003, 470.796, 723.4941,
                470.054, 723.42303);
        ((GeneralPath) shape).lineTo(470.054, 723.40704);
        ((GeneralPath) shape).curveTo(470.763, 723.34406, 470.853, 722.78204,
                470.853, 722.02203);
        ((GeneralPath) shape).curveTo(470.853, 720.47705, 470.16, 720.47705,
                469.038, 720.47705);
        ((GeneralPath) shape).curveTo(467.88098, 720.47705, 467.284, 720.57306,
                467.284, 722.09406);
        ((GeneralPath) shape).lineTo(467.284, 722.34607);
        ((GeneralPath) shape).lineTo(467.80798, 722.34607);
        ((GeneralPath) shape).lineTo(467.80798, 722.09406);
        ((GeneralPath) shape).curveTo(467.80798, 721.17004, 467.94998,
                721.07605, 468.96, 721.07605);
        ((GeneralPath) shape).curveTo(470.202, 721.07605, 470.328, 721.16205,
                470.328, 722.1401);
        ((GeneralPath) shape).curveTo(470.328, 723.1261, 470.012, 723.1011,
                469.262, 723.1011);
        ((GeneralPath) shape).lineTo(468.637, 723.1011);
        ((GeneralPath) shape).lineTo(468.637, 723.728);
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
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(479.068, 720.478);
        ((GeneralPath) shape).lineTo(478.288, 720.478);
        ((GeneralPath) shape).lineTo(476.896, 722.276);
        ((GeneralPath) shape).lineTo(477.254, 722.685);
        ((GeneralPath) shape).lineTo(478.544, 720.997);
        ((GeneralPath) shape).lineTo(478.544, 726.478);
        ((GeneralPath) shape).lineTo(479.068, 726.478);
        ((GeneralPath) shape).lineTo(479.068, 720.478);
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
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(483.811, 724.478);
        ((GeneralPath) shape).lineTo(481.5, 724.478);
        ((GeneralPath) shape).lineTo(483.797, 720.91705);
        ((GeneralPath) shape).lineTo(483.811, 720.91705);
        ((GeneralPath) shape).lineTo(483.811, 724.478);
        ((GeneralPath) shape).moveTo(484.336, 720.478);
        ((GeneralPath) shape).lineTo(483.516, 720.478);
        ((GeneralPath) shape).lineTo(480.97598, 724.27106);
        ((GeneralPath) shape).lineTo(480.97598, 725.103);
        ((GeneralPath) shape).lineTo(483.81097, 725.103);
        ((GeneralPath) shape).lineTo(483.81097, 726.478);
        ((GeneralPath) shape).lineTo(484.33597, 726.478);
        ((GeneralPath) shape).lineTo(484.33597, 725.103);
        ((GeneralPath) shape).lineTo(485.06696, 725.103);
        ((GeneralPath) shape).lineTo(485.06696, 724.478);
        ((GeneralPath) shape).lineTo(484.33597, 724.478);
        ((GeneralPath) shape).lineTo(484.33597, 720.478);
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
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(506.869, 720.478);
        ((GeneralPath) shape).lineTo(506.088, 720.478);
        ((GeneralPath) shape).lineTo(504.697, 722.276);
        ((GeneralPath) shape).lineTo(505.055, 722.685);
        ((GeneralPath) shape).lineTo(506.345, 720.997);
        ((GeneralPath) shape).lineTo(506.345, 726.478);
        ((GeneralPath) shape).lineTo(506.869, 726.478);
        ((GeneralPath) shape).lineTo(506.869, 720.478);
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
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(508.986, 724.368);
        ((GeneralPath) shape).curveTo(508.986, 726.22296, 509.43298, 726.477,
                510.818, 726.477);
        ((GeneralPath) shape).curveTo(512.229, 726.477, 512.661, 726.213,
                512.661, 724.602);
        ((GeneralPath) shape).curveTo(512.661, 722.91797, 512.197, 722.727,
                510.81802, 722.727);
        ((GeneralPath) shape).curveTo(510.03802, 722.727, 509.64, 722.911,
                509.51, 723.30096);
        ((GeneralPath) shape).lineTo(509.51, 722.05695);
        ((GeneralPath) shape).curveTo(509.51, 721.07697, 509.903, 721.07697,
                510.846, 721.07697);
        ((GeneralPath) shape).curveTo(511.617, 721.07697, 512.031, 721.07697,
                512.031, 721.837);
        ((GeneralPath) shape).lineTo(512.031, 721.99695);
        ((GeneralPath) shape).lineTo(512.556, 721.99695);
        ((GeneralPath) shape).lineTo(512.556, 721.811);
        ((GeneralPath) shape).curveTo(512.556, 720.477, 511.66403, 720.477,
                510.84802, 720.477);
        ((GeneralPath) shape).curveTo(509.683, 720.477, 508.98602, 720.647,
                508.98602, 722.028);
        ((GeneralPath) shape).lineTo(508.98602, 724.368);
        ((GeneralPath) shape).moveTo(510.943, 723.353);
        ((GeneralPath) shape).curveTo(512.034, 723.353, 512.135, 723.494,
                512.135, 724.603);
        ((GeneralPath) shape).curveTo(512.135, 725.658, 512.088, 725.853,
                510.81802, 725.853);
        ((GeneralPath) shape).curveTo(509.61304, 725.853, 509.51, 725.642,
                509.51, 724.369);
        ((GeneralPath) shape).curveTo(509.511, 723.423, 509.823, 723.353,
                510.943, 723.353);
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
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(520.773, 720.478);
        ((GeneralPath) shape).lineTo(519.992, 720.478);
        ((GeneralPath) shape).lineTo(518.602, 722.276);
        ((GeneralPath) shape).lineTo(518.959, 722.685);
        ((GeneralPath) shape).lineTo(520.248, 720.997);
        ((GeneralPath) shape).lineTo(520.248, 726.478);
        ((GeneralPath) shape).lineTo(520.773, 726.478);
        ((GeneralPath) shape).lineTo(520.773, 720.478);
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
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(526.67, 720.478);
        ((GeneralPath) shape).lineTo(522.938, 720.478);
        ((GeneralPath) shape).lineTo(522.938, 721.038);
        ((GeneralPath) shape).lineTo(526.145, 721.038);
        ((GeneralPath) shape).lineTo(523.497, 726.478);
        ((GeneralPath) shape).lineTo(524.17, 726.478);
        ((GeneralPath) shape).lineTo(526.67, 721.335);
        ((GeneralPath) shape).lineTo(526.67, 720.478);
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
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(534.677, 720.478);
        ((GeneralPath) shape).lineTo(533.896, 720.478);
        ((GeneralPath) shape).lineTo(532.505, 722.276);
        ((GeneralPath) shape).lineTo(532.862, 722.685);
        ((GeneralPath) shape).lineTo(534.152, 720.997);
        ((GeneralPath) shape).lineTo(534.152, 726.478);
        ((GeneralPath) shape).lineTo(534.677, 726.478);
        ((GeneralPath) shape).lineTo(534.677, 720.478);
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
        ((GeneralPath) shape).moveTo(537.318, 724.817);
        ((GeneralPath) shape).curveTo(537.318, 723.80304, 537.507, 723.727,
                538.628, 723.727);
        ((GeneralPath) shape).curveTo(539.796, 723.727, 539.943, 723.858,
                539.943, 724.817);
        ((GeneralPath) shape).curveTo(539.943, 725.661, 539.755, 725.852,
                538.628, 725.852);
        ((GeneralPath) shape).curveTo(537.386, 725.853, 537.318, 725.745,
                537.318, 724.817);
        ((GeneralPath) shape).moveTo(537.318, 722.015);
        ((GeneralPath) shape).curveTo(537.318, 721.171, 537.41, 721.077,
                538.628, 721.077);
        ((GeneralPath) shape).curveTo(539.852, 721.077, 539.943, 721.20404,
                539.943, 722.015);
        ((GeneralPath) shape).curveTo(539.943, 722.945, 539.837, 723.103,
                538.628, 723.103);
        ((GeneralPath) shape).curveTo(537.404, 723.103, 537.318, 722.913,
                537.318, 722.015);
        ((GeneralPath) shape).moveTo(537.566, 723.394);
        ((GeneralPath) shape).curveTo(536.852, 723.542, 536.794, 723.972,
                536.794, 724.818);
        ((GeneralPath) shape).curveTo(536.794, 726.47797, 537.45703, 726.47797,
                538.631, 726.47797);
        ((GeneralPath) shape).curveTo(540.062, 726.47797, 540.469, 726.30597,
                540.469, 724.818);
        ((GeneralPath) shape).curveTo(540.469, 723.871, 540.313, 723.519,
                539.70197, 723.394);
        ((GeneralPath) shape).lineTo(539.70197, 723.386);
        ((GeneralPath) shape).curveTo(540.404, 723.234, 540.469, 722.781,
                540.469, 722.015);
        ((GeneralPath) shape).curveTo(540.469, 720.478, 539.895, 720.478,
                538.628, 720.478);
        ((GeneralPath) shape).curveTo(537.371, 720.478, 536.794, 720.541,
                536.794, 722.015);
        ((GeneralPath) shape).curveTo(536.794, 722.9, 536.91003, 723.236,
                537.566, 723.378);
        ((GeneralPath) shape).lineTo(537.566, 723.394);
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
        ((GeneralPath) shape).moveTo(548.574, 720.478);
        ((GeneralPath) shape).lineTo(547.793, 720.478);
        ((GeneralPath) shape).lineTo(546.401, 722.276);
        ((GeneralPath) shape).lineTo(546.76, 722.685);
        ((GeneralPath) shape).lineTo(548.049, 720.997);
        ((GeneralPath) shape).lineTo(548.049, 726.478);
        ((GeneralPath) shape).lineTo(548.574, 726.478);
        ((GeneralPath) shape).lineTo(548.574, 720.478);
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
        ((GeneralPath) shape).moveTo(550.69, 725.091);
        ((GeneralPath) shape).curveTo(550.69, 726.478, 551.328, 726.478,
                552.577, 726.478);
        ((GeneralPath) shape).curveTo(554.211, 726.478, 554.471, 726.085,
                554.471, 724.54004);
        ((GeneralPath) shape).lineTo(554.471, 722.30804);
        ((GeneralPath) shape).curveTo(554.471, 720.73206, 554.021, 720.478,
                552.577, 720.478);
        ((GeneralPath) shape).curveTo(551.098, 720.478, 550.69, 720.724,
                550.69, 722.30804);
        ((GeneralPath) shape).curveTo(550.69, 723.89, 551.163, 724.103,
                552.585, 724.103);
        ((GeneralPath) shape).curveTo(552.79004, 724.103, 553.697, 724.103,
                553.931, 723.544);
        ((GeneralPath) shape).lineTo(553.94604, 723.562);
        ((GeneralPath) shape).lineTo(553.94604, 724.541);
        ((GeneralPath) shape).curveTo(553.94604, 725.805, 553.69806, 725.854,
                552.57806, 725.854);
        ((GeneralPath) shape).curveTo(551.45404, 725.854, 551.21606, 725.79,
                551.21606, 725.092);
        ((GeneralPath) shape).lineTo(551.21606, 724.865);
        ((GeneralPath) shape).lineTo(550.6921, 724.865);
        ((GeneralPath) shape).lineTo(550.6921, 725.091);
        ((GeneralPath) shape).moveTo(552.577, 723.478);
        ((GeneralPath) shape).curveTo(551.40704, 723.478, 551.215, 723.40204,
                551.215, 722.30804);
        ((GeneralPath) shape).curveTo(551.215, 721.11505, 551.35706, 721.07806,
                552.577, 721.07806);
        ((GeneralPath) shape).curveTo(553.64, 721.07806, 553.945, 721.07806,
                553.945, 722.30804);
        ((GeneralPath) shape).curveTo(553.945, 723.401, 553.805, 723.478,
                552.577, 723.478);
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
        ((GeneralPath) shape).moveTo(563.633, 725.878);
        ((GeneralPath) shape).lineTo(560.588, 725.878);
        ((GeneralPath) shape).lineTo(560.588, 725.372);
        ((GeneralPath) shape).curveTo(560.588, 724.50104, 560.852, 724.366,
                561.93, 724.263);
        ((GeneralPath) shape).curveTo(563.172, 724.144, 563.633, 723.939,
                563.633, 722.361);
        ((GeneralPath) shape).curveTo(563.633, 720.851, 563.224, 720.478,
                561.915, 720.478);
        ((GeneralPath) shape).curveTo(560.19995, 720.478, 560.06195, 720.955,
                560.06195, 722.257);
        ((GeneralPath) shape).lineTo(560.06195, 722.568);
        ((GeneralPath) shape).lineTo(560.587, 722.568);
        ((GeneralPath) shape).lineTo(560.587, 722.257);
        ((GeneralPath) shape).curveTo(560.587, 721.16504, 560.71295, 721.077,
                561.914, 721.077);
        ((GeneralPath) shape).curveTo(562.877, 721.077, 563.106, 721.132,
                563.106, 722.36005);
        ((GeneralPath) shape).curveTo(563.106, 723.59607, 562.87305, 723.54004,
                561.813, 723.66705);
        ((GeneralPath) shape).curveTo(560.614, 723.8171, 560.061, 723.9211,
                560.061, 725.46606);
        ((GeneralPath) shape).lineTo(560.061, 726.4781);
        ((GeneralPath) shape).lineTo(563.631, 726.4781);
        ((GeneralPath) shape).lineTo(563.631, 725.878);
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
        ((GeneralPath) shape).moveTo(566.491, 726.478);
        ((GeneralPath) shape).curveTo(568.043, 726.478, 568.58405, 726.23206,
                568.58405, 724.32605);
        ((GeneralPath) shape).lineTo(568.58405, 722.60504);
        ((GeneralPath) shape).curveTo(568.58405, 720.72205, 568.043, 720.478,
                566.478, 720.478);
        ((GeneralPath) shape).curveTo(564.926, 720.478, 564.385, 720.72205,
                564.385, 722.60504);
        ((GeneralPath) shape).lineTo(564.385, 724.32605);
        ((GeneralPath) shape).curveTo(564.385, 726.231, 564.926, 726.478,
                566.491, 726.478);
        ((GeneralPath) shape).moveTo(566.478, 721.077);
        ((GeneralPath) shape).curveTo(567.73004, 721.077, 568.06, 721.077,
                568.06, 722.604);
        ((GeneralPath) shape).lineTo(568.06, 724.325);
        ((GeneralPath) shape).curveTo(568.06, 725.852, 567.73, 725.852,
                566.478, 725.852);
        ((GeneralPath) shape).curveTo(565.24005, 725.852, 564.91003, 725.852,
                564.91003, 724.325);
        ((GeneralPath) shape).lineTo(564.91003, 722.604);
        ((GeneralPath) shape).curveTo(564.909, 721.077, 565.239, 721.077,
                566.478, 721.077);
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
        paint = new Color(204, 206, 207, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(471.111, 647.098);
        ((GeneralPath) shape).curveTo(471.111, 647.098, 463.65, 647.638,
                464.71698, 638.724);
        ((GeneralPath) shape).lineTo(408.08698, 638.724);
        ((GeneralPath) shape).lineTo(408.08698, 634.91797);
        ((GeneralPath) shape).lineTo(435.48798, 590.009);
        ((GeneralPath) shape).lineTo(444.62396, 590.009);
        ((GeneralPath) shape).lineTo(439.14297, 575.547);
        ((GeneralPath) shape).lineTo(404.43198, 575.547);
        ((GeneralPath) shape).lineTo(404.43198, 566.411);
        ((GeneralPath) shape).lineTo(431.83597, 552.71);
        ((GeneralPath) shape).curveTo(431.83597, 552.71, 413.56497, 505.268,
                410.82697, 465.68802);
        ((GeneralPath) shape).lineTo(410.82697, 190.582);
        ((GeneralPath) shape).curveTo(410.82697, 190.582, 408.99997, 157.851,
                415.39297, 139.582);
        ((GeneralPath) shape).curveTo(415.39297, 139.582, 437.77197, 68.793,
                491.20697, 63.461998);
        ((GeneralPath) shape).lineTo(494.22696, 63.461998);
        ((GeneralPath) shape).curveTo(547.66296, 68.793, 570.042, 139.582,
                570.042, 139.582);
        ((GeneralPath) shape).curveTo(576.434, 157.851, 574.607, 190.582,
                574.607, 190.582);
        ((GeneralPath) shape).lineTo(574.607, 465.688);
        ((GeneralPath) shape).curveTo(571.867, 505.268, 553.597, 552.70996,
                553.597, 552.70996);
        ((GeneralPath) shape).lineTo(581.001, 566.41095);
        ((GeneralPath) shape).lineTo(581.001, 575.54694);
        ((GeneralPath) shape).lineTo(546.292, 575.54694);
        ((GeneralPath) shape).lineTo(540.81, 590.0089);
        ((GeneralPath) shape).lineTo(549.945, 590.0089);
        ((GeneralPath) shape).lineTo(577.346, 634.9179);
        ((GeneralPath) shape).lineTo(577.346, 638.72394);
        ((GeneralPath) shape).lineTo(520.714, 638.72394);
        ((GeneralPath) shape).curveTo(521.78296, 647.63794, 514.32196,
                647.09796, 514.32196, 647.09796);
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(468.277, 643.965);
        ((GeneralPath) shape).curveTo(468.277, 643.965, 460.814, 644.50604,
                461.883, 635.591);
        ((GeneralPath) shape).lineTo(405.25198, 635.591);
        ((GeneralPath) shape).lineTo(405.25198, 631.784);
        ((GeneralPath) shape).lineTo(432.65298, 586.876);
        ((GeneralPath) shape).lineTo(441.78897, 586.876);
        ((GeneralPath) shape).lineTo(436.30698, 572.41296);
        ((GeneralPath) shape).lineTo(401.59598, 572.41296);
        ((GeneralPath) shape).lineTo(401.59598, 563.277);
        ((GeneralPath) shape).lineTo(429.00098, 549.57697);
        ((GeneralPath) shape).curveTo(429.00098, 549.57697, 410.731, 502.13397,
                407.99197, 462.55496);
        ((GeneralPath) shape).lineTo(407.99197, 187.448);
        ((GeneralPath) shape).curveTo(407.99197, 187.448, 406.16498, 154.717,
                412.55698, 136.448);
        ((GeneralPath) shape).curveTo(412.55698, 136.448, 434.93597, 65.659,
                488.37198, 60.33);
        ((GeneralPath) shape).lineTo(491.391, 60.33);
        ((GeneralPath) shape).curveTo(544.829, 65.659004, 567.207, 136.448,
                567.207, 136.448);
        ((GeneralPath) shape).curveTo(573.6, 154.717, 571.771, 187.448,
                571.771, 187.448);
        ((GeneralPath) shape).lineTo(571.771, 462.554);
        ((GeneralPath) shape).curveTo(569.033, 502.133, 550.763, 549.576,
                550.763, 549.576);
        ((GeneralPath) shape).lineTo(578.165, 563.276);
        ((GeneralPath) shape).lineTo(578.165, 572.412);
        ((GeneralPath) shape).lineTo(543.46, 572.412);
        ((GeneralPath) shape).lineTo(537.97705, 586.875);
        ((GeneralPath) shape).lineTo(547.11304, 586.875);
        ((GeneralPath) shape).lineTo(574.515, 631.783);
        ((GeneralPath) shape).lineTo(574.515, 635.59);
        ((GeneralPath) shape).lineTo(517.882, 635.59);
        ((GeneralPath) shape).curveTo(518.952, 644.505, 511.49002, 643.96405,
                511.49002, 643.96405);
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
        paint = new Color(67, 67, 68, 255);
        stroke = new BasicStroke(1.522f, 0, 0, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(468.276, 643.965);
        ((GeneralPath) shape).curveTo(468.276, 643.965, 460.815, 644.505,
                461.882, 635.591);
        ((GeneralPath) shape).lineTo(405.25198, 635.591);
        ((GeneralPath) shape).lineTo(405.25198, 631.784);
        ((GeneralPath) shape).lineTo(432.65298, 586.876);
        ((GeneralPath) shape).lineTo(441.78897, 586.876);
        ((GeneralPath) shape).lineTo(436.30698, 572.41296);
        ((GeneralPath) shape).lineTo(401.59598, 572.41296);
        ((GeneralPath) shape).lineTo(401.59598, 563.277);
        ((GeneralPath) shape).lineTo(429.00098, 549.57697);
        ((GeneralPath) shape).curveTo(429.00098, 549.57697, 410.731, 502.13397,
                407.99197, 462.55496);
        ((GeneralPath) shape).lineTo(407.99197, 187.448);
        ((GeneralPath) shape).curveTo(407.99197, 187.448, 406.16397, 154.717,
                412.55698, 136.449);
        ((GeneralPath) shape).curveTo(412.55698, 136.449, 434.93597, 65.658005,
                488.37198, 60.329002);
        ((GeneralPath) shape).lineTo(491.391, 60.329002);
        ((GeneralPath) shape).curveTo(544.829, 65.658005, 567.207, 136.449,
                567.207, 136.449);
        ((GeneralPath) shape).curveTo(573.6, 154.71701, 571.771, 187.448,
                571.771, 187.448);
        ((GeneralPath) shape).lineTo(571.771, 462.554);
        ((GeneralPath) shape).curveTo(569.033, 502.133, 550.763, 549.576,
                550.763, 549.576);
        ((GeneralPath) shape).lineTo(578.165, 563.276);
        ((GeneralPath) shape).lineTo(578.165, 572.412);
        ((GeneralPath) shape).lineTo(543.46, 572.412);
        ((GeneralPath) shape).lineTo(537.97705, 586.875);
        ((GeneralPath) shape).lineTo(547.11304, 586.875);
        ((GeneralPath) shape).lineTo(574.51306, 631.783);
        ((GeneralPath) shape).lineTo(574.51306, 635.59);
        ((GeneralPath) shape).lineTo(517.8821, 635.59);
        ((GeneralPath) shape).curveTo(518.9521, 644.504, 511.48907, 643.96405,
                511.48907, 643.96405);
        ((GeneralPath) shape).lineTo(468.276, 643.96405);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        paint = new Color(204, 206, 207, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(519.402, 286.438);
        ((GeneralPath) shape).curveTo(524.21796, 286.438, 528.123, 289.948,
                528.123, 294.28497);
        ((GeneralPath) shape).lineTo(528.123, 394.32297);
        ((GeneralPath) shape).curveTo(528.123, 398.65897, 524.219, 402.17496,
                519.402, 402.17496);
        ((GeneralPath) shape).lineTo(489.352, 402.17496);
        ((GeneralPath) shape).lineTo(489.352, 515.29297);
        ((GeneralPath) shape).lineTo(524.319, 515.29297);
        ((GeneralPath) shape).lineTo(532.81396, 526.64795);
        ((GeneralPath) shape).lineTo(532.81396, 175.521);
        ((GeneralPath) shape).lineTo(489.35196, 175.521);
        ((GeneralPath) shape).lineTo(489.35196, 286.438);
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
        paint = new Color(204, 206, 207, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(489.314, 515.292);
        ((GeneralPath) shape).lineTo(489.314, 402.174);
        ((GeneralPath) shape).lineTo(459.266, 402.174);
        ((GeneralPath) shape).curveTo(454.449, 402.174, 450.543, 398.65802,
                450.543, 394.32202);
        ((GeneralPath) shape).lineTo(450.543, 294.285);
        ((GeneralPath) shape).curveTo(450.543, 289.948, 454.448, 286.438,
                459.266, 286.438);
        ((GeneralPath) shape).lineTo(489.314, 286.438);
        ((GeneralPath) shape).lineTo(489.314, 175.521);
        ((GeneralPath) shape).lineTo(445.852, 175.521);
        ((GeneralPath) shape).lineTo(445.852, 526.177);
        ((GeneralPath) shape).lineTo(453.99298, 515.292);
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
        paint = new Color(67, 67, 68, 255);
        stroke = new BasicStroke(0.761f, 0, 0, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(460.029, 632.165);
        ((GeneralPath) shape).lineTo(404.768, 632.165);
        ((GeneralPath) shape).moveTo(461.4, 635.591);
        ((GeneralPath) shape).lineTo(437.193, 568.987);
        ((GeneralPath) shape).lineTo(401.11398, 568.987);
        ((GeneralPath) shape).moveTo(518.769, 632.165);
        ((GeneralPath) shape).lineTo(574.032, 632.165);
        ((GeneralPath) shape).moveTo(517.399, 635.591);
        ((GeneralPath) shape).lineTo(541.604, 568.987);
        ((GeneralPath) shape).lineTo(577.684, 568.987);
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        paint = new Color(204, 206, 207, 255);
        shape = new Rectangle2D.Double(474.7019958496094, 286.4830017089844,
                29.7189998626709, 68.80500030517578);
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
        paint = new Color(67, 67, 68, 255);
        stroke = new BasicStroke(0.484f, 0, 0, 4.0f, null, 0.0f);
        shape = new Rectangle2D.Double(474.7019958496094, 286.48199462890625,
                29.7189998626709, 68.80500030517578);
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        paint = new Color(67, 67, 68, 255);
        stroke = new BasicStroke(1.454f, 0, 0, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(528.163, 294.285);
        ((GeneralPath) shape).curveTo(528.163, 289.948, 524.25903, 286.438,
                519.44, 286.438);
        ((GeneralPath) shape).lineTo(458.712, 286.438);
        ((GeneralPath) shape).curveTo(453.896, 286.438, 449.988, 289.948,
                449.988, 294.28497);
        ((GeneralPath) shape).lineTo(449.988, 394.32297);
        ((GeneralPath) shape).curveTo(449.988, 398.65897, 453.89502, 402.17496,
                458.712, 402.17496);
        ((GeneralPath) shape).lineTo(519.44, 402.17496);
        ((GeneralPath) shape).curveTo(524.258, 402.17496, 528.163, 398.65897,
                528.163, 394.32297);
        ((GeneralPath) shape).lineTo(528.163, 294.285);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        paint = new Color(204, 206, 207, 255);
        shape = new Rectangle2D.Double(489.3139953613281, 402.17401123046875,
                0.03799999877810478, 113.11799621582031);
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
        paint = new Color(204, 206, 207, 255);
        shape = new Rectangle2D.Double(489.3139953613281, 175.52099609375,
                0.03799999877810478, 110.91699981689453);
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
        paint = new Color(204, 206, 207, 255);
        shape = new Rectangle2D.Double(489.3139953613281, 515.2919921875,
                0.03799999877810478, 1.6950000524520874);
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
        paint = new Color(204, 206, 207, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(567.729, 141.118);
        ((GeneralPath) shape).lineTo(411.371, 141.118);
        ((GeneralPath) shape).lineTo(444.404, 175.521);
        ((GeneralPath) shape).lineTo(534.697, 175.521);
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
        paint = new Color(67, 67, 68, 255);
        stroke = new BasicStroke(1.0f, 0, 0, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(519.44, 286.438);
        ((GeneralPath) shape).curveTo(524.258, 286.438, 528.163, 289.948,
                528.163, 294.28497);
        ((GeneralPath) shape).lineTo(528.163, 394.32297);
        ((GeneralPath) shape).curveTo(528.163, 398.65897, 524.25903, 402.17496,
                519.44, 402.17496);
        ((GeneralPath) shape).lineTo(489.352, 402.17496);
        ((GeneralPath) shape).lineTo(489.352, 515.29297);
        ((GeneralPath) shape).lineTo(524.319, 515.29297);
        ((GeneralPath) shape).lineTo(532.852, 526.701);
        ((GeneralPath) shape).lineTo(532.852, 175.521);
        ((GeneralPath) shape).lineTo(489.352, 175.521);
        ((GeneralPath) shape).lineTo(489.352, 286.438);
        ((GeneralPath) shape).lineTo(519.44, 286.438);
        ((GeneralPath) shape).closePath();
        ((GeneralPath) shape).moveTo(489.353, 515.292);
        ((GeneralPath) shape).lineTo(489.353, 402.174);
        ((GeneralPath) shape).lineTo(459.266, 402.174);
        ((GeneralPath) shape).curveTo(454.449, 402.174, 450.544, 398.65802,
                450.544, 394.32202);
        ((GeneralPath) shape).lineTo(450.544, 294.285);
        ((GeneralPath) shape).curveTo(450.544, 289.948, 454.448, 286.438,
                459.266, 286.438);
        ((GeneralPath) shape).lineTo(489.353, 286.438);
        ((GeneralPath) shape).lineTo(489.353, 175.521);
        ((GeneralPath) shape).lineTo(445.853, 175.521);
        ((GeneralPath) shape).lineTo(445.853, 526.177);
        ((GeneralPath) shape).lineTo(453.994, 515.292);
        ((GeneralPath) shape).lineTo(489.353, 515.292);
        ((GeneralPath) shape).closePath();
        ((GeneralPath) shape).moveTo(567.729, 141.118);
        ((GeneralPath) shape).lineTo(411.371, 141.118);
        ((GeneralPath) shape).lineTo(444.403, 175.521);
        ((GeneralPath) shape).lineTo(534.69604, 175.521);
        ((GeneralPath) shape).lineTo(567.729, 141.118);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        paint = new Color(204, 206, 207, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(428.266, 549.692);
        ((GeneralPath) shape).lineTo(550.049, 549.692);
        ((GeneralPath) shape).lineTo(524.319, 515.292);
        ((GeneralPath) shape).lineTo(453.993, 515.292);
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
        paint = new Color(67, 67, 68, 255);
        stroke = new BasicStroke(1.0f, 0, 0, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(428.266, 549.692);
        ((GeneralPath) shape).lineTo(550.049, 549.692);
        ((GeneralPath) shape).lineTo(524.319, 515.292);
        ((GeneralPath) shape).lineTo(453.993, 515.292);
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
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
        paint = new Color(67, 67, 68, 255);
        stroke = new BasicStroke(0.484f, 0, 0, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
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
        paint = new Color(67, 67, 68, 255);
        stroke = new BasicStroke(0.484f, 0, 0, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
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
        paint = new Color(67, 67, 68, 255);
        stroke = new BasicStroke(0.484f, 0, 0, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
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
        paint = new Color(67, 67, 68, 255);
        stroke = new BasicStroke(0.484f, 0, 0, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(468.477, 652.602);
        ((GeneralPath) shape).lineTo(468.477, 650.582);
        ((GeneralPath) shape).lineTo(470.03, 650.582);
        ((GeneralPath) shape).curveTo(470.829, 650.582, 470.977, 650.732,
                470.977, 651.568);
        ((GeneralPath) shape).curveTo(470.977, 652.434, 470.78198, 652.601,
                469.981, 652.601);
        ((GeneralPath) shape).lineTo(468.477, 652.601);
        ((GeneralPath) shape).moveTo(470.225, 653.604);
        ((GeneralPath) shape).curveTo(470.772, 653.604, 470.97702, 653.96,
                470.97702, 654.472);
        ((GeneralPath) shape).lineTo(470.97702, 655.608);
        ((GeneralPath) shape).lineTo(472.10202, 655.608);
        ((GeneralPath) shape).lineTo(472.10202, 654.474);
        ((GeneralPath) shape).curveTo(472.10202, 653.618, 471.89902, 653.171,
                471.06702, 653.099);
        ((GeneralPath) shape).lineTo(471.06702, 653.066);
        ((GeneralPath) shape).curveTo(472.10202, 652.91296, 472.10202, 652.263,
                472.10202, 651.386);
        ((GeneralPath) shape).curveTo(472.10202, 650.009, 471.622, 649.596,
                470.368, 649.596);
        ((GeneralPath) shape).lineTo(467.35202, 649.596);
        ((GeneralPath) shape).lineTo(467.35202, 655.60803);
        ((GeneralPath) shape).lineTo(468.47702, 655.60803);
        ((GeneralPath) shape).lineTo(468.47702, 653.604);
        ((GeneralPath) shape).lineTo(470.225, 653.604);
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
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(474.003, 653.103);
        ((GeneralPath) shape).curveTo(474.003, 652.314, 474.06, 652.119,
                474.929, 652.119);
        ((GeneralPath) shape).curveTo(475.75098, 652.119, 475.878, 652.187,
                475.878, 653.103);
        ((GeneralPath) shape).lineTo(474.003, 653.103);
        ((GeneralPath) shape).moveTo(475.878, 654.272);
        ((GeneralPath) shape).curveTo(475.878, 654.855, 475.487, 654.855,
                474.929, 654.855);
        ((GeneralPath) shape).curveTo(474.02698, 654.855, 474.003, 654.584,
                474.003, 653.72797);
        ((GeneralPath) shape).lineTo(476.878, 653.72797);
        ((GeneralPath) shape).curveTo(476.878, 651.86096, 476.64798, 651.34796,
                474.929, 651.34796);
        ((GeneralPath) shape).curveTo(473.241, 651.34796, 473.003, 652.01996,
                473.003, 653.55597);
        ((GeneralPath) shape).curveTo(473.003, 655.11597, 473.32898, 655.607,
                474.929, 655.607);
        ((GeneralPath) shape).curveTo(476.12198, 655.607, 476.878, 655.544,
                476.878, 654.272);
        ((GeneralPath) shape).lineTo(475.878, 654.272);
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
        ((GeneralPath) shape).moveTo(480.438, 655.607);
        ((GeneralPath) shape).lineTo(481.438, 655.607);
        ((GeneralPath) shape).lineTo(481.438, 652.968);
        ((GeneralPath) shape).curveTo(481.438, 651.55804, 480.87, 651.348,
                479.508, 651.348);
        ((GeneralPath) shape).curveTo(478.555, 651.348, 477.688, 651.348,
                477.688, 652.59503);
        ((GeneralPath) shape).lineTo(478.688, 652.59503);
        ((GeneralPath) shape).curveTo(478.688, 652.07605, 479.05298, 652.03705,
                479.508, 652.03705);
        ((GeneralPath) shape).curveTo(480.379, 652.03705, 480.438, 652.26605,
                480.438, 652.91504);
        ((GeneralPath) shape).lineTo(480.438, 653.499);
        ((GeneralPath) shape).lineTo(480.405, 653.499);
        ((GeneralPath) shape).curveTo(480.157, 652.976, 479.634, 652.976,
                479.102, 652.976);
        ((GeneralPath) shape).curveTo(478.081, 652.976, 477.563, 653.25604,
                477.563, 654.262);
        ((GeneralPath) shape).curveTo(477.563, 655.405, 478.168, 655.607,
                479.102, 655.607);
        ((GeneralPath) shape).curveTo(479.608, 655.607, 480.235, 655.607,
                480.438, 654.986);
        ((GeneralPath) shape).lineTo(480.438, 655.607);
        ((GeneralPath) shape).moveTo(479.475, 653.729);
        ((GeneralPath) shape).curveTo(479.991, 653.729, 480.43802, 653.729,
                480.43802, 654.263);
        ((GeneralPath) shape).curveTo(480.43802, 654.813, 480.032, 654.856,
                479.475, 654.856);
        ((GeneralPath) shape).curveTo(478.801, 654.856, 478.56302, 654.805,
                478.56302, 654.263);
        ((GeneralPath) shape).curveTo(478.563, 653.729, 478.945, 653.729,
                479.475, 653.729);
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
        ((GeneralPath) shape).moveTo(482.274, 651.349);
        ((GeneralPath) shape).lineTo(482.274, 655.608);
        ((GeneralPath) shape).lineTo(483.274, 655.608);
        ((GeneralPath) shape).lineTo(483.274, 653.009);
        ((GeneralPath) shape).curveTo(483.274, 652.462, 483.46, 652.118,
                484.09598, 652.118);
        ((GeneralPath) shape).curveTo(484.606, 652.118, 484.649, 652.36597,
                484.649, 652.79095);
        ((GeneralPath) shape).lineTo(484.649, 653.009);
        ((GeneralPath) shape).lineTo(485.649, 653.009);
        ((GeneralPath) shape).lineTo(485.649, 652.672);
        ((GeneralPath) shape).curveTo(485.649, 651.877, 485.41898, 651.349,
                484.47498, 651.349);
        ((GeneralPath) shape).curveTo(483.95197, 651.349, 483.48898, 651.483,
                483.274, 651.938);
        ((GeneralPath) shape).lineTo(483.274, 651.349);
        ((GeneralPath) shape).lineTo(482.274, 651.349);
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
        ((GeneralPath) shape).moveTo(492.407, 653.604);
        ((GeneralPath) shape).lineTo(490.325, 653.604);
        ((GeneralPath) shape).lineTo(491.354, 650.479);
        ((GeneralPath) shape).lineTo(491.37, 650.479);
        ((GeneralPath) shape).lineTo(492.407, 653.604);
        ((GeneralPath) shape).moveTo(492.665, 654.479);
        ((GeneralPath) shape).lineTo(493.06, 655.607);
        ((GeneralPath) shape).lineTo(494.234, 655.607);
        ((GeneralPath) shape).lineTo(492.191, 649.595);
        ((GeneralPath) shape).lineTo(490.48602, 649.595);
        ((GeneralPath) shape).lineTo(488.484, 655.607);
        ((GeneralPath) shape).lineTo(489.683, 655.607);
        ((GeneralPath) shape).lineTo(490.06003, 654.479);
        ((GeneralPath) shape).lineTo(492.665, 654.479);
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
        ((GeneralPath) shape).moveTo(494.531, 651.349);
        ((GeneralPath) shape).lineTo(494.531, 655.608);
        ((GeneralPath) shape).lineTo(495.531, 655.608);
        ((GeneralPath) shape).lineTo(495.531, 653.009);
        ((GeneralPath) shape).curveTo(495.531, 652.462, 495.717, 652.118,
                496.353, 652.118);
        ((GeneralPath) shape).curveTo(496.863, 652.118, 496.906, 652.36597,
                496.906, 652.79095);
        ((GeneralPath) shape).lineTo(496.906, 653.009);
        ((GeneralPath) shape).lineTo(497.906, 653.009);
        ((GeneralPath) shape).lineTo(497.906, 652.672);
        ((GeneralPath) shape).curveTo(497.906, 651.877, 497.676, 651.349,
                496.732, 651.349);
        ((GeneralPath) shape).curveTo(496.20898, 651.349, 495.746, 651.483,
                495.531, 651.938);
        ((GeneralPath) shape).lineTo(495.531, 651.349);
        ((GeneralPath) shape).lineTo(494.531, 651.349);
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
        ((GeneralPath) shape).moveTo(498.424, 651.349);
        ((GeneralPath) shape).lineTo(498.424, 655.608);
        ((GeneralPath) shape).lineTo(499.424, 655.608);
        ((GeneralPath) shape).lineTo(499.424, 653.23395);
        ((GeneralPath) shape).curveTo(499.424, 652.48895, 499.59402, 652.11896,
                500.45502, 652.11896);
        ((GeneralPath) shape).curveTo(501.03903, 652.11896, 501.174, 652.32794,
                501.174, 652.85596);
        ((GeneralPath) shape).lineTo(501.174, 655.608);
        ((GeneralPath) shape).lineTo(502.174, 655.608);
        ((GeneralPath) shape).lineTo(502.174, 653.23395);
        ((GeneralPath) shape).curveTo(502.174, 652.48895, 502.33002, 652.11896,
                503.131, 652.11896);
        ((GeneralPath) shape).curveTo(503.674, 652.11896, 503.799, 652.32794,
                503.799, 652.85596);
        ((GeneralPath) shape).lineTo(503.799, 655.608);
        ((GeneralPath) shape).lineTo(504.799, 655.608);
        ((GeneralPath) shape).lineTo(504.799, 652.75995);
        ((GeneralPath) shape).curveTo(504.799, 651.7269, 504.449, 651.34894,
                503.39102, 651.34894);
        ((GeneralPath) shape).curveTo(502.84003, 651.34894, 502.27603,
                651.50995, 502.08603, 652.09296);
        ((GeneralPath) shape).lineTo(502.05502, 652.09296);
        ((GeneralPath) shape).curveTo(501.94403, 651.48395, 501.342, 651.34894,
                500.81903, 651.34894);
        ((GeneralPath) shape).curveTo(500.26004, 651.34894, 499.66104,
                651.46796, 499.42404, 651.99896);
        ((GeneralPath) shape).lineTo(499.42404, 651.34894);
        ((GeneralPath) shape).lineTo(498.424, 651.34894);
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
        ((GeneralPath) shape).moveTo(507.629, 652.118);
        ((GeneralPath) shape).curveTo(508.496, 652.118, 508.567, 652.372,
                508.567, 653.5);
        ((GeneralPath) shape).curveTo(508.567, 654.609, 508.49698, 654.855,
                507.629, 654.855);
        ((GeneralPath) shape).curveTo(506.76102, 654.855, 506.691, 654.609,
                506.691, 653.5);
        ((GeneralPath) shape).curveTo(506.691, 652.372, 506.762, 652.118,
                507.629, 652.118);
        ((GeneralPath) shape).moveTo(507.629, 651.349);
        ((GeneralPath) shape).curveTo(505.91, 651.349, 505.691, 651.868,
                505.691, 653.493);
        ((GeneralPath) shape).curveTo(505.691, 655.095, 505.91, 655.608,
                507.629, 655.608);
        ((GeneralPath) shape).curveTo(509.348, 655.608, 509.567, 655.094,
                509.567, 653.493);
        ((GeneralPath) shape).curveTo(509.566, 651.867, 509.348, 651.349,
                507.629, 651.349);
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
        ((GeneralPath) shape).moveTo(510.347, 651.349);
        ((GeneralPath) shape).lineTo(510.347, 655.608);
        ((GeneralPath) shape).lineTo(511.347, 655.608);
        ((GeneralPath) shape).lineTo(511.347, 653.009);
        ((GeneralPath) shape).curveTo(511.347, 652.462, 511.533, 652.118,
                512.169, 652.118);
        ((GeneralPath) shape).curveTo(512.679, 652.118, 512.722, 652.36597,
                512.722, 652.79095);
        ((GeneralPath) shape).lineTo(512.722, 653.009);
        ((GeneralPath) shape).lineTo(513.722, 653.009);
        ((GeneralPath) shape).lineTo(513.722, 652.672);
        ((GeneralPath) shape).curveTo(513.722, 651.877, 513.492, 651.349,
                512.548, 651.349);
        ((GeneralPath) shape).curveTo(512.02496, 651.349, 511.56198, 651.483,
                511.347, 651.938);
        ((GeneralPath) shape).lineTo(511.347, 651.349);
        ((GeneralPath) shape).lineTo(510.347, 651.349);
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
        ((GeneralPath) shape).moveTo(480.051, 665.46);
        ((GeneralPath) shape).curveTo(479.276, 665.46, 479.276, 664.961,
                479.276, 664.452);
        ((GeneralPath) shape).lineTo(479.276, 661.15);
        ((GeneralPath) shape).curveTo(479.276, 660.491, 479.323, 660.143,
                480.051, 660.143);
        ((GeneralPath) shape).lineTo(480.051, 659.198);
        ((GeneralPath) shape).curveTo(478.151, 659.198, 478.151, 660.086,
                478.151, 661.777);
        ((GeneralPath) shape).lineTo(478.151, 663.873);
        ((GeneralPath) shape).curveTo(478.151, 665.76196, 478.241, 666.462,
                480.051, 666.462);
        ((GeneralPath) shape).lineTo(480.051, 665.46);
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
        ((GeneralPath) shape).moveTo(497.805, 660.144);
        ((GeneralPath) shape).curveTo(498.58, 660.144, 498.58, 660.643, 498.58,
                661.151);
        ((GeneralPath) shape).lineTo(498.58, 664.453);
        ((GeneralPath) shape).curveTo(498.58, 665.112, 498.533, 665.461,
                497.805, 665.461);
        ((GeneralPath) shape).lineTo(497.805, 666.463);
        ((GeneralPath) shape).curveTo(499.705, 666.463, 499.705, 665.52,
                499.705, 663.827);
        ((GeneralPath) shape).lineTo(499.705, 661.731);
        ((GeneralPath) shape).curveTo(499.705, 659.883, 499.616, 659.19904,
                497.805, 659.19904);
        ((GeneralPath) shape).lineTo(497.805, 660.144);
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
        ((GeneralPath) shape).moveTo(468.203, 384.752);
        ((GeneralPath) shape).lineTo(469.743, 384.752);
        ((GeneralPath) shape).lineTo(469.743, 383.889);
        ((GeneralPath) shape).lineTo(465.68, 383.889);
        ((GeneralPath) shape).lineTo(465.68, 384.752);
        ((GeneralPath) shape).lineTo(467.219, 384.752);
        ((GeneralPath) shape).lineTo(467.219, 389.149);
        ((GeneralPath) shape).lineTo(468.203, 389.149);
        ((GeneralPath) shape).lineTo(468.203, 384.752);
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
        ((GeneralPath) shape).moveTo(473.516, 389.149);
        ((GeneralPath) shape).lineTo(473.516, 385.42297);
        ((GeneralPath) shape).lineTo(472.641, 385.42297);
        ((GeneralPath) shape).lineTo(472.641, 387.55997);
        ((GeneralPath) shape).curveTo(472.641, 388.20197, 472.41998, 388.49197,
                471.73398, 388.49197);
        ((GeneralPath) shape).curveTo(471.15997, 388.49197, 471.11, 388.28796,
                471.11, 387.75897);
        ((GeneralPath) shape).lineTo(471.11, 385.42398);
        ((GeneralPath) shape).lineTo(470.235, 385.42398);
        ((GeneralPath) shape).lineTo(470.235, 388.08997);
        ((GeneralPath) shape).curveTo(470.235, 388.89597, 470.727, 389.14996,
                471.464, 389.14996);
        ((GeneralPath) shape).curveTo(471.97498, 389.14996, 472.43698,
                389.03296, 472.642, 388.55698);
        ((GeneralPath) shape).lineTo(472.642, 389.14996);
        ((GeneralPath) shape).lineTo(473.516, 389.14996);
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
        ((GeneralPath) shape).moveTo(474.275, 385.423);
        ((GeneralPath) shape).lineTo(474.275, 389.14902);
        ((GeneralPath) shape).lineTo(475.15, 389.14902);
        ((GeneralPath) shape).lineTo(475.15, 386.87503);
        ((GeneralPath) shape).curveTo(475.15, 386.39703, 475.313, 386.09604,
                475.87, 386.09604);
        ((GeneralPath) shape).curveTo(476.316, 386.09604, 476.353, 386.31305,
                476.353, 386.68503);
        ((GeneralPath) shape).lineTo(476.353, 386.87503);
        ((GeneralPath) shape).lineTo(477.228, 386.87503);
        ((GeneralPath) shape).lineTo(477.228, 386.58002);
        ((GeneralPath) shape).curveTo(477.228, 385.885, 477.027, 385.423,
                476.202, 385.423);
        ((GeneralPath) shape).curveTo(475.744, 385.423, 475.339, 385.539,
                475.15, 385.93802);
        ((GeneralPath) shape).lineTo(475.15, 385.423);
        ((GeneralPath) shape).lineTo(474.275, 385.423);
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
        ((GeneralPath) shape).moveTo(477.573, 385.423);
        ((GeneralPath) shape).lineTo(477.573, 389.14902);
        ((GeneralPath) shape).lineTo(478.448, 389.14902);
        ((GeneralPath) shape).lineTo(478.448, 386.87503);
        ((GeneralPath) shape).curveTo(478.448, 386.39703, 478.61, 386.09604,
                479.167, 386.09604);
        ((GeneralPath) shape).curveTo(479.613, 386.09604, 479.651, 386.31305,
                479.651, 386.68503);
        ((GeneralPath) shape).lineTo(479.651, 386.87503);
        ((GeneralPath) shape).lineTo(480.526, 386.87503);
        ((GeneralPath) shape).lineTo(480.526, 386.58002);
        ((GeneralPath) shape).curveTo(480.526, 385.885, 480.324, 385.423,
                479.499, 385.423);
        ((GeneralPath) shape).curveTo(479.041, 385.423, 478.636, 385.539,
                478.448, 385.93802);
        ((GeneralPath) shape).lineTo(478.448, 385.423);
        ((GeneralPath) shape).lineTo(477.573, 385.423);
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
        ((GeneralPath) shape).moveTo(481.745, 386.958);
        ((GeneralPath) shape).curveTo(481.745, 386.268, 481.794, 386.09702,
                482.555, 386.09702);
        ((GeneralPath) shape).curveTo(483.275, 386.09702, 483.386, 386.157,
                483.386, 386.958);
        ((GeneralPath) shape).lineTo(481.745, 386.958);
        ((GeneralPath) shape).moveTo(483.386, 387.981);
        ((GeneralPath) shape).curveTo(483.386, 388.491, 483.04398, 388.491,
                482.555, 388.491);
        ((GeneralPath) shape).curveTo(481.766, 388.491, 481.745, 388.253,
                481.745, 387.505);
        ((GeneralPath) shape).lineTo(484.261, 387.505);
        ((GeneralPath) shape).curveTo(484.261, 385.872, 484.059, 385.423,
                482.555, 385.423);
        ((GeneralPath) shape).curveTo(481.078, 385.423, 480.87, 386.01, 480.87,
                387.354);
        ((GeneralPath) shape).curveTo(480.87, 388.719, 481.155, 389.14902,
                482.555, 389.14902);
        ((GeneralPath) shape).curveTo(483.599, 389.14902, 484.261, 389.09402,
                484.261, 387.98102);
        ((GeneralPath) shape).lineTo(483.386, 387.98102);
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
        ((GeneralPath) shape).moveTo(484.508, 386.081);
        ((GeneralPath) shape).lineTo(484.969, 386.081);
        ((GeneralPath) shape).lineTo(484.969, 388.0);
        ((GeneralPath) shape).curveTo(484.969, 388.927, 485.267, 389.149,
                486.244, 389.149);
        ((GeneralPath) shape).curveTo(487.203, 389.149, 487.48398, 388.813,
                487.48398, 387.702);
        ((GeneralPath) shape).lineTo(486.718, 387.702);
        ((GeneralPath) shape).curveTo(486.718, 388.093, 486.77298, 388.492,
                486.24298, 388.492);
        ((GeneralPath) shape).curveTo(485.843, 388.492, 485.843, 388.336,
                485.843, 387.993);
        ((GeneralPath) shape).lineTo(485.843, 386.08002);
        ((GeneralPath) shape).lineTo(487.29797, 386.08002);
        ((GeneralPath) shape).lineTo(487.29797, 385.423);
        ((GeneralPath) shape).lineTo(485.843, 385.423);
        ((GeneralPath) shape).lineTo(485.843, 384.572);
        ((GeneralPath) shape).lineTo(484.968, 384.572);
        ((GeneralPath) shape).lineTo(484.968, 385.423);
        ((GeneralPath) shape).lineTo(484.507, 385.423);
        ((GeneralPath) shape).lineTo(484.507, 386.081);
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
        ((GeneralPath) shape).moveTo(493.404, 387.396);
        ((GeneralPath) shape).lineTo(491.582, 387.396);
        ((GeneralPath) shape).lineTo(492.483, 384.661);
        ((GeneralPath) shape).lineTo(492.497, 384.661);
        ((GeneralPath) shape).lineTo(493.404, 387.396);
        ((GeneralPath) shape).moveTo(493.63, 388.163);
        ((GeneralPath) shape).lineTo(493.975, 389.149);
        ((GeneralPath) shape).lineTo(495.002, 389.149);
        ((GeneralPath) shape).lineTo(493.21503, 383.88898);
        ((GeneralPath) shape).lineTo(491.72302, 383.88898);
        ((GeneralPath) shape).lineTo(489.971, 389.149);
        ((GeneralPath) shape).lineTo(491.021, 389.149);
        ((GeneralPath) shape).lineTo(491.35, 388.163);
        ((GeneralPath) shape).lineTo(493.63, 388.163);
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
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(495.262, 385.423);
        ((GeneralPath) shape).lineTo(495.262, 389.14902);
        ((GeneralPath) shape).lineTo(496.137, 389.14902);
        ((GeneralPath) shape).lineTo(496.137, 386.87503);
        ((GeneralPath) shape).curveTo(496.137, 386.39703, 496.29898, 386.09604,
                496.857, 386.09604);
        ((GeneralPath) shape).curveTo(497.303, 386.09604, 497.34, 386.31305,
                497.34, 386.68503);
        ((GeneralPath) shape).lineTo(497.34, 386.87503);
        ((GeneralPath) shape).lineTo(498.215, 386.87503);
        ((GeneralPath) shape).lineTo(498.215, 386.58002);
        ((GeneralPath) shape).curveTo(498.215, 385.885, 498.013, 385.423,
                497.188, 385.423);
        ((GeneralPath) shape).curveTo(496.72998, 385.423, 496.32498, 385.539,
                496.137, 385.93802);
        ((GeneralPath) shape).lineTo(496.137, 385.423);
        ((GeneralPath) shape).lineTo(495.262, 385.423);
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
        ((GeneralPath) shape).moveTo(498.668, 385.423);
        ((GeneralPath) shape).lineTo(498.668, 389.14902);
        ((GeneralPath) shape).lineTo(499.543, 389.14902);
        ((GeneralPath) shape).lineTo(499.543, 387.07202);
        ((GeneralPath) shape).curveTo(499.543, 386.42, 499.692, 386.096,
                500.445, 386.096);
        ((GeneralPath) shape).curveTo(500.957, 386.096, 501.074, 386.27902,
                501.074, 386.742);
        ((GeneralPath) shape).lineTo(501.074, 389.15);
        ((GeneralPath) shape).lineTo(501.949, 389.15);
        ((GeneralPath) shape).lineTo(501.949, 387.073);
        ((GeneralPath) shape).curveTo(501.949, 386.421, 502.086, 386.097,
                502.78702, 386.097);
        ((GeneralPath) shape).curveTo(503.26202, 386.097, 503.37103, 386.28,
                503.37103, 386.74298);
        ((GeneralPath) shape).lineTo(503.37103, 389.15097);
        ((GeneralPath) shape).lineTo(504.24603, 389.15097);
        ((GeneralPath) shape).lineTo(504.24603, 386.65897);
        ((GeneralPath) shape).curveTo(504.24603, 385.75497, 503.94003,
                385.42496, 503.01505, 385.42496);
        ((GeneralPath) shape).curveTo(502.53305, 385.42496, 502.03806,
                385.56595, 501.87305, 386.07495);
        ((GeneralPath) shape).lineTo(501.84604, 386.07495);
        ((GeneralPath) shape).curveTo(501.74805, 385.54294, 501.22205,
                385.42496, 500.76404, 385.42496);
        ((GeneralPath) shape).curveTo(500.27502, 385.42496, 499.75003,
                385.52896, 499.54303, 385.99295);
        ((GeneralPath) shape).lineTo(499.54303, 385.42496);
        ((GeneralPath) shape).lineTo(498.668, 385.42496);
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
        ((GeneralPath) shape).moveTo(506.722, 386.096);
        ((GeneralPath) shape).curveTo(507.481, 386.096, 507.542, 386.319,
                507.542, 387.30502);
        ((GeneralPath) shape).curveTo(507.542, 388.27603, 507.47998, 388.49203,
                506.722, 388.49203);
        ((GeneralPath) shape).curveTo(505.964, 388.49203, 505.90198, 388.27603,
                505.90198, 387.30502);
        ((GeneralPath) shape).curveTo(505.901, 386.319, 505.963, 386.096,
                506.722, 386.096);
        ((GeneralPath) shape).moveTo(506.722, 385.423);
        ((GeneralPath) shape).curveTo(505.218, 385.423, 505.02698, 385.877,
                505.02698, 387.298);
        ((GeneralPath) shape).curveTo(505.02698, 388.7, 505.218, 389.14902,
                506.722, 389.14902);
        ((GeneralPath) shape).curveTo(508.22598, 389.14902, 508.417, 388.7,
                508.417, 387.298);
        ((GeneralPath) shape).curveTo(508.417, 385.877, 508.226, 385.423,
                506.722, 385.423);
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
        ((GeneralPath) shape).moveTo(509.101, 385.423);
        ((GeneralPath) shape).lineTo(509.101, 389.14902);
        ((GeneralPath) shape).lineTo(509.976, 389.14902);
        ((GeneralPath) shape).lineTo(509.976, 386.87503);
        ((GeneralPath) shape).curveTo(509.976, 386.39703, 510.138, 386.09604,
                510.696, 386.09604);
        ((GeneralPath) shape).curveTo(511.14203, 386.09604, 511.17902,
                386.31305, 511.17902, 386.68503);
        ((GeneralPath) shape).lineTo(511.17902, 386.87503);
        ((GeneralPath) shape).lineTo(512.054, 386.87503);
        ((GeneralPath) shape).lineTo(512.054, 386.58002);
        ((GeneralPath) shape).curveTo(512.054, 385.885, 511.85202, 385.423,
                511.027, 385.423);
        ((GeneralPath) shape).curveTo(510.569, 385.423, 510.164, 385.539,
                509.976, 385.93802);
        ((GeneralPath) shape).lineTo(509.976, 385.423);
        ((GeneralPath) shape).lineTo(509.101, 385.423);
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
        ((GeneralPath) shape).moveTo(479.919, 397.771);
        ((GeneralPath) shape).curveTo(479.24002, 397.771, 479.24002, 397.33398,
                479.24002, 396.889);
        ((GeneralPath) shape).lineTo(479.24002, 394.0);
        ((GeneralPath) shape).curveTo(479.24002, 393.423, 479.281, 393.119,
                479.919, 393.119);
        ((GeneralPath) shape).lineTo(479.919, 392.292);
        ((GeneralPath) shape).curveTo(478.256, 392.292, 478.256, 393.069,
                478.256, 394.548);
        ((GeneralPath) shape).lineTo(478.256, 396.38202);
        ((GeneralPath) shape).curveTo(478.256, 398.03403, 478.33502, 398.648,
                479.919, 398.648);
        ((GeneralPath) shape).lineTo(479.919, 397.771);
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
        ((GeneralPath) shape).moveTo(495.454, 393.119);
        ((GeneralPath) shape).curveTo(496.13202, 393.119, 496.13202, 393.556,
                496.13202, 394.0);
        ((GeneralPath) shape).lineTo(496.13202, 396.889);
        ((GeneralPath) shape).curveTo(496.13202, 397.466, 496.09103, 397.771,
                495.454, 397.771);
        ((GeneralPath) shape).lineTo(495.454, 398.648);
        ((GeneralPath) shape).curveTo(497.116, 398.648, 497.116, 397.823,
                497.116, 396.341);
        ((GeneralPath) shape).lineTo(497.116, 394.508);
        ((GeneralPath) shape).curveTo(497.116, 392.891, 497.038, 392.292,
                495.454, 392.292);
        ((GeneralPath) shape).lineTo(495.454, 393.119);
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
        paint = new Color(67, 67, 68, 255);
        stroke = new BasicStroke(1.0f, 0, 0, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        paint = new Color(67, 67, 68, 255);
        stroke = new BasicStroke(1.0f, 0, 0, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        ((GeneralPath) shape).moveTo(470.076, 39.715);
        ((GeneralPath) shape).lineTo(472.894, 39.715);
        ((GeneralPath) shape).lineTo(472.894, 38.729);
        ((GeneralPath) shape).lineTo(468.951, 38.729);
        ((GeneralPath) shape).lineTo(468.951, 44.74);
        ((GeneralPath) shape).lineTo(470.076, 44.74);
        ((GeneralPath) shape).lineTo(470.076, 42.235);
        ((GeneralPath) shape).lineTo(472.748, 42.235);
        ((GeneralPath) shape).lineTo(472.748, 41.233);
        ((GeneralPath) shape).lineTo(470.076, 41.233);
        ((GeneralPath) shape).lineTo(470.076, 39.715);
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
        ((GeneralPath) shape).moveTo(473.482, 40.482);
        ((GeneralPath) shape).lineTo(473.482, 44.739998);
        ((GeneralPath) shape).lineTo(474.482, 44.739998);
        ((GeneralPath) shape).lineTo(474.482, 42.141);
        ((GeneralPath) shape).curveTo(474.482, 41.594997, 474.668, 41.25,
                475.304, 41.25);
        ((GeneralPath) shape).curveTo(475.814, 41.25, 475.857, 41.499, 475.857,
                41.923);
        ((GeneralPath) shape).lineTo(475.857, 42.14);
        ((GeneralPath) shape).lineTo(476.857, 42.14);
        ((GeneralPath) shape).lineTo(476.857, 41.804);
        ((GeneralPath) shape).curveTo(476.857, 41.010002, 476.62698, 40.481003,
                475.68298, 40.481003);
        ((GeneralPath) shape).curveTo(475.15997, 40.481003, 474.697, 40.614002,
                474.482, 41.070004);
        ((GeneralPath) shape).lineTo(474.482, 40.481003);
        ((GeneralPath) shape).lineTo(473.482, 40.481003);
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
        ((GeneralPath) shape).moveTo(479.188, 41.251);
        ((GeneralPath) shape).curveTo(480.055, 41.251, 480.12598, 41.506,
                480.12598, 42.633);
        ((GeneralPath) shape).curveTo(480.12598, 43.742, 480.05597, 43.989,
                479.188, 43.989);
        ((GeneralPath) shape).curveTo(478.32, 43.989, 478.25, 43.741997,
                478.25, 42.633);
        ((GeneralPath) shape).curveTo(478.25, 41.506, 478.32, 41.251, 479.188,
                41.251);
        ((GeneralPath) shape).moveTo(479.188, 40.482);
        ((GeneralPath) shape).curveTo(477.469, 40.482, 477.25, 41.001, 477.25,
                42.625);
        ((GeneralPath) shape).curveTo(477.25, 44.228, 477.469, 44.74, 479.188,
                44.74);
        ((GeneralPath) shape).curveTo(480.90698, 44.74, 481.12598, 44.227,
                481.12598, 42.625);
        ((GeneralPath) shape).curveTo(481.125, 41.0, 480.906, 40.482, 479.188,
                40.482);
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
        ((GeneralPath) shape).moveTo(482.031, 40.482);
        ((GeneralPath) shape).lineTo(482.031, 44.739998);
        ((GeneralPath) shape).lineTo(483.031, 44.739998);
        ((GeneralPath) shape).lineTo(483.031, 42.420998);
        ((GeneralPath) shape).curveTo(483.031, 41.668, 483.148, 41.251,
                484.01102, 41.251);
        ((GeneralPath) shape).curveTo(484.648, 41.251, 484.781, 41.46, 484.781,
                42.069);
        ((GeneralPath) shape).lineTo(484.781, 44.74);
        ((GeneralPath) shape).lineTo(485.781, 44.74);
        ((GeneralPath) shape).lineTo(485.781, 41.966003);
        ((GeneralPath) shape).curveTo(485.781, 40.941, 485.449, 40.482002,
                484.357, 40.482002);
        ((GeneralPath) shape).curveTo(483.777, 40.482002, 483.283, 40.545002,
                483.06198, 41.157);
        ((GeneralPath) shape).lineTo(483.03098, 41.157);
        ((GeneralPath) shape).lineTo(483.03098, 40.482002);
        ((GeneralPath) shape).lineTo(482.031, 40.482002);
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
        ((GeneralPath) shape).moveTo(486.288, 41.233);
        ((GeneralPath) shape).lineTo(486.815, 41.233);
        ((GeneralPath) shape).lineTo(486.815, 43.427002);
        ((GeneralPath) shape).curveTo(486.815, 44.486, 487.155, 44.74, 488.272,
                44.74);
        ((GeneralPath) shape).curveTo(489.368, 44.74, 489.69, 44.357002,
                489.69, 43.087);
        ((GeneralPath) shape).lineTo(488.815, 43.087);
        ((GeneralPath) shape).curveTo(488.815, 43.533, 488.878, 43.989002,
                488.272, 43.989002);
        ((GeneralPath) shape).curveTo(487.815, 43.989002, 487.815, 43.811,
                487.815, 43.420002);
        ((GeneralPath) shape).lineTo(487.815, 41.234);
        ((GeneralPath) shape).lineTo(489.477, 41.234);
        ((GeneralPath) shape).lineTo(489.477, 40.483);
        ((GeneralPath) shape).lineTo(487.815, 40.483);
        ((GeneralPath) shape).lineTo(487.815, 39.51);
        ((GeneralPath) shape).lineTo(486.815, 39.51);
        ((GeneralPath) shape).lineTo(486.815, 40.482);
        ((GeneralPath) shape).lineTo(486.288, 40.482);
        ((GeneralPath) shape).lineTo(486.288, 41.233);
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
        ((GeneralPath) shape).moveTo(496.455, 42.736);
        ((GeneralPath) shape).lineTo(494.374, 42.736);
        ((GeneralPath) shape).lineTo(495.40298, 39.611);
        ((GeneralPath) shape).lineTo(495.41898, 39.611);
        ((GeneralPath) shape).lineTo(496.455, 42.736);
        ((GeneralPath) shape).moveTo(496.713, 43.613);
        ((GeneralPath) shape).lineTo(497.109, 44.739998);
        ((GeneralPath) shape).lineTo(498.282, 44.739998);
        ((GeneralPath) shape).lineTo(496.24002, 38.727997);
        ((GeneralPath) shape).lineTo(494.53403, 38.727997);
        ((GeneralPath) shape).lineTo(492.532, 44.739998);
        ((GeneralPath) shape).lineTo(493.73102, 44.739998);
        ((GeneralPath) shape).lineTo(494.109, 43.613);
        ((GeneralPath) shape).lineTo(496.713, 43.613);
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
        ((GeneralPath) shape).moveTo(498.579, 40.482);
        ((GeneralPath) shape).lineTo(498.579, 44.739998);
        ((GeneralPath) shape).lineTo(499.579, 44.739998);
        ((GeneralPath) shape).lineTo(499.579, 42.141);
        ((GeneralPath) shape).curveTo(499.579, 41.594997, 499.765, 41.25,
                500.40002, 41.25);
        ((GeneralPath) shape).curveTo(500.911, 41.25, 500.954, 41.499, 500.954,
                41.923);
        ((GeneralPath) shape).lineTo(500.954, 42.14);
        ((GeneralPath) shape).lineTo(501.954, 42.14);
        ((GeneralPath) shape).lineTo(501.954, 41.804);
        ((GeneralPath) shape).curveTo(501.954, 41.010002, 501.72302, 40.481003,
                500.78, 40.481003);
        ((GeneralPath) shape).curveTo(500.257, 40.481003, 499.793, 40.614002,
                499.579, 41.070004);
        ((GeneralPath) shape).lineTo(499.579, 40.481003);
        ((GeneralPath) shape).lineTo(498.579, 40.481003);
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
        ((GeneralPath) shape).moveTo(502.472, 40.482);
        ((GeneralPath) shape).lineTo(502.472, 44.739998);
        ((GeneralPath) shape).lineTo(503.472, 44.739998);
        ((GeneralPath) shape).lineTo(503.472, 42.365997);
        ((GeneralPath) shape).curveTo(503.472, 41.62, 503.642, 41.249996,
                504.503, 41.249996);
        ((GeneralPath) shape).curveTo(505.087, 41.249996, 505.222, 41.458996,
                505.222, 41.987995);
        ((GeneralPath) shape).lineTo(505.222, 44.738995);
        ((GeneralPath) shape).lineTo(506.222, 44.738995);
        ((GeneralPath) shape).lineTo(506.222, 42.364994);
        ((GeneralPath) shape).curveTo(506.222, 41.618996, 506.378, 41.248993,
                507.179, 41.248993);
        ((GeneralPath) shape).curveTo(507.722, 41.248993, 507.847, 41.457993,
                507.847, 41.98699);
        ((GeneralPath) shape).lineTo(507.847, 44.73799);
        ((GeneralPath) shape).lineTo(508.847, 44.73799);
        ((GeneralPath) shape).lineTo(508.847, 41.89099);
        ((GeneralPath) shape).curveTo(508.847, 40.85799, 508.49698, 40.479992,
                507.439, 40.479992);
        ((GeneralPath) shape).curveTo(506.888, 40.479992, 506.324, 40.64099,
                506.134, 41.22399);
        ((GeneralPath) shape).lineTo(506.103, 41.22399);
        ((GeneralPath) shape).curveTo(505.992, 40.61599, 505.38998, 40.479992,
                504.867, 40.479992);
        ((GeneralPath) shape).curveTo(504.308, 40.479992, 503.709, 40.59999,
                503.47202, 41.129993);
        ((GeneralPath) shape).lineTo(503.47202, 40.479992);
        ((GeneralPath) shape).lineTo(502.472, 40.479992);
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
        ((GeneralPath) shape).moveTo(511.676, 41.251);
        ((GeneralPath) shape).curveTo(512.54297, 41.251, 512.614, 41.506,
                512.614, 42.633);
        ((GeneralPath) shape).curveTo(512.614, 43.742, 512.544, 43.989,
                511.67603, 43.989);
        ((GeneralPath) shape).curveTo(510.80804, 43.989, 510.73804, 43.741997,
                510.73804, 42.633);
        ((GeneralPath) shape).curveTo(510.738, 41.506, 510.809, 41.251,
                511.676, 41.251);
        ((GeneralPath) shape).moveTo(511.676, 40.482);
        ((GeneralPath) shape).curveTo(509.957, 40.482, 509.738, 41.001,
                509.738, 42.625);
        ((GeneralPath) shape).curveTo(509.738, 44.228, 509.957, 44.74, 511.676,
                44.74);
        ((GeneralPath) shape).curveTo(513.395, 44.74, 513.614, 44.227, 513.614,
                42.625);
        ((GeneralPath) shape).curveTo(513.613, 41.0, 513.395, 40.482, 511.676,
                40.482);
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
        ((GeneralPath) shape).moveTo(514.395, 40.482);
        ((GeneralPath) shape).lineTo(514.395, 44.739998);
        ((GeneralPath) shape).lineTo(515.395, 44.739998);
        ((GeneralPath) shape).lineTo(515.395, 42.141);
        ((GeneralPath) shape).curveTo(515.395, 41.594997, 515.581, 41.25,
                516.21704, 41.25);
        ((GeneralPath) shape).curveTo(516.72705, 41.25, 516.77, 41.499, 516.77,
                41.923);
        ((GeneralPath) shape).lineTo(516.77, 42.14);
        ((GeneralPath) shape).lineTo(517.77, 42.14);
        ((GeneralPath) shape).lineTo(517.77, 41.804);
        ((GeneralPath) shape).curveTo(517.77, 41.010002, 517.54004, 40.481003,
                516.596, 40.481003);
        ((GeneralPath) shape).curveTo(516.073, 40.481003, 515.61, 40.614002,
                515.395, 41.070004);
        ((GeneralPath) shape).lineTo(515.395, 40.481003);
        ((GeneralPath) shape).lineTo(514.395, 40.481003);
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
        ((GeneralPath) shape).moveTo(481.468, 54.593);
        ((GeneralPath) shape).curveTo(480.693, 54.593, 480.693, 54.093998,
                480.693, 53.585);
        ((GeneralPath) shape).lineTo(480.693, 50.284);
        ((GeneralPath) shape).curveTo(480.693, 49.624, 480.74, 49.276, 481.468,
                49.276);
        ((GeneralPath) shape).lineTo(481.468, 48.331);
        ((GeneralPath) shape).curveTo(479.568, 48.331, 479.568, 49.22, 479.568,
                50.91);
        ((GeneralPath) shape).lineTo(479.568, 53.006);
        ((GeneralPath) shape).curveTo(479.568, 54.895, 479.658, 55.595,
                481.468, 55.595);
        ((GeneralPath) shape).lineTo(481.468, 54.593);
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
        ((GeneralPath) shape).moveTo(499.222, 49.276);
        ((GeneralPath) shape).curveTo(499.99698, 49.276, 499.99698, 49.775,
                499.99698, 50.284);
        ((GeneralPath) shape).lineTo(499.99698, 53.585);
        ((GeneralPath) shape).curveTo(499.99698, 54.245, 499.94998, 54.593,
                499.222, 54.593);
        ((GeneralPath) shape).lineTo(499.222, 55.594997);
        ((GeneralPath) shape).curveTo(501.12198, 55.594997, 501.12198,
                54.651997, 501.12198, 52.958996);
        ((GeneralPath) shape).lineTo(501.12198, 50.862995);
        ((GeneralPath) shape).curveTo(501.12198, 49.014996, 501.033, 48.330994,
                499.222, 48.330994);
        ((GeneralPath) shape).lineTo(499.222, 49.276);
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
        ((GeneralPath) shape).moveTo(397.825, 385.622);
        ((GeneralPath) shape).lineTo(397.825, 386.747);
        ((GeneralPath) shape).lineTo(403.837, 386.747);
        ((GeneralPath) shape).lineTo(403.837, 382.821);
        ((GeneralPath) shape).lineTo(402.851, 382.821);
        ((GeneralPath) shape).lineTo(402.851, 385.622);
        ((GeneralPath) shape).lineTo(397.825, 385.622);
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
        paint2(g, clip_, defaultTransform_, alpha__0, clip__0,
                defaultTransform__0, alpha__0_143, clip__0_143,
                defaultTransform__0_143);

    }

    private static void paint2(Graphics2D g, Shape clip_,
            AffineTransform defaultTransform_, float alpha__0, Shape clip__0,
            AffineTransform defaultTransform__0, float alpha__0_143,
            Shape clip__0_143, AffineTransform defaultTransform__0_143) {
        Shape shape;
        Paint paint;
        Stroke stroke;
        float origAlpha;
        // _0_143 is ShapeNode
        paint = new Color(34, 31, 31, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(401.332, 381.384);
        ((GeneralPath) shape).curveTo(400.543, 381.384, 400.348, 381.328,
                400.348, 380.458);
        ((GeneralPath) shape).curveTo(400.348, 379.63602, 400.416, 379.509,
                401.332, 379.509);
        ((GeneralPath) shape).lineTo(401.332, 381.384);
        ((GeneralPath) shape).moveTo(402.502, 379.509);
        ((GeneralPath) shape).curveTo(403.08502, 379.509, 403.08502, 379.9,
                403.08502, 380.458);
        ((GeneralPath) shape).curveTo(403.08502, 381.36002, 402.81403, 381.384,
                401.958, 381.384);
        ((GeneralPath) shape).lineTo(401.958, 378.509);
        ((GeneralPath) shape).curveTo(400.092, 378.509, 399.579, 378.74,
                399.579, 380.458);
        ((GeneralPath) shape).curveTo(399.579, 382.146, 400.25, 382.384,
                401.786, 382.384);
        ((GeneralPath) shape).curveTo(403.346, 382.384, 403.837, 382.058,
                403.837, 380.458);
        ((GeneralPath) shape).curveTo(403.837, 379.265, 403.77402, 378.509,
                402.502, 378.509);
        ((GeneralPath) shape).lineTo(402.502, 379.509);
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
        ((GeneralPath) shape).moveTo(399.579, 375.458);
        ((GeneralPath) shape).lineTo(399.579, 376.48102);
        ((GeneralPath) shape).lineTo(399.34, 376.48102);
        ((GeneralPath) shape).curveTo(398.855, 376.48102, 398.594, 376.48102,
                398.594, 375.79303);
        ((GeneralPath) shape).lineTo(398.594, 375.48804);
        ((GeneralPath) shape).lineTo(397.82498, 375.48804);
        ((GeneralPath) shape).lineTo(397.82498, 376.14203);
        ((GeneralPath) shape).curveTo(397.82498, 377.24203, 398.23798,
                377.48004, 399.22098, 377.48004);
        ((GeneralPath) shape).lineTo(399.57898, 377.48004);
        ((GeneralPath) shape).lineTo(399.57898, 378.07205);
        ((GeneralPath) shape).lineTo(400.33, 378.07205);
        ((GeneralPath) shape).lineTo(400.33, 377.48004);
        ((GeneralPath) shape).lineTo(403.83698, 377.48004);
        ((GeneralPath) shape).lineTo(403.83698, 376.48004);
        ((GeneralPath) shape).lineTo(400.33, 376.48004);
        ((GeneralPath) shape).lineTo(400.33, 375.45703);
        ((GeneralPath) shape).lineTo(399.579, 375.45703);
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
        ((GeneralPath) shape).moveTo(400.33, 375.506);
        ((GeneralPath) shape).lineTo(400.33, 374.979);
        ((GeneralPath) shape).lineTo(402.52298, 374.979);
        ((GeneralPath) shape).curveTo(403.58298, 374.979, 403.83597, 374.639,
                403.83597, 373.521);
        ((GeneralPath) shape).curveTo(403.83597, 372.425, 403.45197, 372.104,
                402.18295, 372.104);
        ((GeneralPath) shape).lineTo(402.18295, 372.979);
        ((GeneralPath) shape).curveTo(402.62897, 372.979, 403.08395, 372.91602,
                403.08395, 373.521);
        ((GeneralPath) shape).curveTo(403.08395, 373.979, 402.90594, 373.979,
                402.51495, 373.979);
        ((GeneralPath) shape).lineTo(400.32895, 373.979);
        ((GeneralPath) shape).lineTo(400.32895, 372.31702);
        ((GeneralPath) shape).lineTo(399.57794, 372.31702);
        ((GeneralPath) shape).lineTo(399.57794, 373.979);
        ((GeneralPath) shape).lineTo(398.60495, 373.979);
        ((GeneralPath) shape).lineTo(398.60495, 374.979);
        ((GeneralPath) shape).lineTo(399.57794, 374.979);
        ((GeneralPath) shape).lineTo(399.57794, 375.506);
        ((GeneralPath) shape).lineTo(400.33, 375.506);
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
        ((GeneralPath) shape).moveTo(401.827, 369.077);
        ((GeneralPath) shape).lineTo(402.105, 369.077);
        ((GeneralPath) shape).curveTo(403.836, 369.077, 403.836, 367.888,
                403.836, 366.556);
        ((GeneralPath) shape).curveTo(403.836, 365.005, 403.688, 364.202,
                401.953, 364.202);
        ((GeneralPath) shape).curveTo(400.436, 364.202, 400.341, 364.712,
                400.247, 366.577);
        ((GeneralPath) shape).curveTo(400.18402, 367.794, 400.163, 367.952,
                399.52402, 367.952);
        ((GeneralPath) shape).curveTo(398.88, 367.952, 398.769, 367.76,
                398.769, 366.569);
        ((GeneralPath) shape).curveTo(398.769, 365.727, 398.79202, 365.452,
                399.444, 365.452);
        ((GeneralPath) shape).lineTo(399.635, 365.452);
        ((GeneralPath) shape).lineTo(399.635, 364.327);
        ((GeneralPath) shape).lineTo(399.43802, 364.327);
        ((GeneralPath) shape).curveTo(397.824, 364.327, 397.824, 365.296,
                397.824, 366.569);
        ((GeneralPath) shape).curveTo(397.824, 368.032, 397.824, 369.077,
                399.52402, 369.077);
        ((GeneralPath) shape).curveTo(401.135, 369.077, 401.087, 368.048,
                401.16702, 366.735);
        ((GeneralPath) shape).curveTo(401.21402, 365.801, 401.10303, 365.327,
                401.95303, 365.327);
        ((GeneralPath) shape).curveTo(402.64404, 365.327, 402.83405, 365.477,
                402.83405, 366.569);
        ((GeneralPath) shape).curveTo(402.83405, 367.651, 402.78705, 367.952,
                402.10504, 367.952);
        ((GeneralPath) shape).lineTo(401.82703, 367.952);
        ((GeneralPath) shape).lineTo(401.82703, 369.077);
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
        ((GeneralPath) shape).moveTo(397.825, 362.419);
        ((GeneralPath) shape).lineTo(397.825, 363.419);
        ((GeneralPath) shape).lineTo(398.682, 363.419);
        ((GeneralPath) shape).lineTo(398.682, 362.419);
        ((GeneralPath) shape).lineTo(397.825, 362.419);
        ((GeneralPath) shape).moveTo(399.579, 362.419);
        ((GeneralPath) shape).lineTo(399.579, 363.419);
        ((GeneralPath) shape).lineTo(403.837, 363.419);
        ((GeneralPath) shape).lineTo(403.837, 362.419);
        ((GeneralPath) shape).lineTo(399.579, 362.419);
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
        ((GeneralPath) shape).moveTo(403.837, 358.773);
        ((GeneralPath) shape).lineTo(403.837, 357.773);
        ((GeneralPath) shape).lineTo(397.825, 357.773);
        ((GeneralPath) shape).lineTo(397.825, 358.773);
        ((GeneralPath) shape).lineTo(400.187, 358.773);
        ((GeneralPath) shape).lineTo(400.187, 358.79602);
        ((GeneralPath) shape).curveTo(399.674, 359.01102, 399.579, 359.544,
                399.579, 360.05203);
        ((GeneralPath) shape).curveTo(399.579, 361.48203, 400.367, 361.64804,
                401.595, 361.64804);
        ((GeneralPath) shape).curveTo(402.872, 361.64804, 403.837, 361.64804,
                403.837, 360.05203);
        ((GeneralPath) shape).curveTo(403.837, 359.47403, 403.71402, 359.02304,
                403.178, 358.77304);
        ((GeneralPath) shape).lineTo(403.837, 358.77304);
        ((GeneralPath) shape).moveTo(400.348, 359.757);
        ((GeneralPath) shape).curveTo(400.348, 358.892, 400.70398, 358.77298,
                401.595, 358.77298);
        ((GeneralPath) shape).curveTo(402.586, 358.77298, 403.085, 358.77298,
                403.085, 359.757);
        ((GeneralPath) shape).curveTo(403.085, 360.64798, 402.537, 360.64798,
                401.595, 360.64798);
        ((GeneralPath) shape).curveTo(400.545, 360.648, 400.348, 360.449,
                400.348, 359.757);
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
        ((GeneralPath) shape).moveTo(401.332, 355.936);
        ((GeneralPath) shape).curveTo(400.543, 355.936, 400.348, 355.879,
                400.348, 355.01);
        ((GeneralPath) shape).curveTo(400.348, 354.18802, 400.416, 354.061,
                401.332, 354.061);
        ((GeneralPath) shape).lineTo(401.332, 355.936);
        ((GeneralPath) shape).moveTo(402.502, 354.061);
        ((GeneralPath) shape).curveTo(403.08502, 354.061, 403.08502, 354.452,
                403.08502, 355.01);
        ((GeneralPath) shape).curveTo(403.08502, 355.91202, 402.81403, 355.936,
                401.958, 355.936);
        ((GeneralPath) shape).lineTo(401.958, 353.061);
        ((GeneralPath) shape).curveTo(400.092, 353.061, 399.579, 353.29102,
                399.579, 355.01);
        ((GeneralPath) shape).curveTo(399.579, 356.698, 400.25, 356.936,
                401.786, 356.936);
        ((GeneralPath) shape).curveTo(403.346, 356.936, 403.837, 356.61002,
                403.837, 355.01);
        ((GeneralPath) shape).curveTo(403.837, 353.81702, 403.77402, 353.061,
                402.502, 353.061);
        ((GeneralPath) shape).lineTo(402.502, 354.061);
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
        ((GeneralPath) shape).moveTo(401.833, 346.075);
        ((GeneralPath) shape).lineTo(401.833, 348.157);
        ((GeneralPath) shape).lineTo(398.708, 347.12802);
        ((GeneralPath) shape).lineTo(398.708, 347.11203);
        ((GeneralPath) shape).lineTo(401.833, 346.075);
        ((GeneralPath) shape).moveTo(402.71, 345.817);
        ((GeneralPath) shape).lineTo(403.837, 345.422);
        ((GeneralPath) shape).lineTo(403.837, 344.248);
        ((GeneralPath) shape).lineTo(397.825, 346.291);
        ((GeneralPath) shape).lineTo(397.825, 347.99597);
        ((GeneralPath) shape).lineTo(403.837, 349.998);
        ((GeneralPath) shape).lineTo(403.837, 348.8);
        ((GeneralPath) shape).lineTo(402.71, 348.42297);
        ((GeneralPath) shape).lineTo(402.71, 345.817);
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
        ((GeneralPath) shape).moveTo(399.579, 343.928);
        ((GeneralPath) shape).lineTo(403.837, 343.928);
        ((GeneralPath) shape).lineTo(403.837, 342.928);
        ((GeneralPath) shape).lineTo(401.238, 342.928);
        ((GeneralPath) shape).curveTo(400.69202, 342.928, 400.34702, 342.742,
                400.34702, 342.10602);
        ((GeneralPath) shape).curveTo(400.34702, 341.596, 400.596, 341.553,
                401.02002, 341.553);
        ((GeneralPath) shape).lineTo(401.238, 341.553);
        ((GeneralPath) shape).lineTo(401.238, 340.553);
        ((GeneralPath) shape).lineTo(400.901, 340.553);
        ((GeneralPath) shape).curveTo(400.107, 340.553, 399.579, 340.78302,
                399.579, 341.72702);
        ((GeneralPath) shape).curveTo(399.579, 342.25003, 399.712, 342.713,
                400.168, 342.928);
        ((GeneralPath) shape).lineTo(399.579, 342.928);
        ((GeneralPath) shape).lineTo(399.579, 343.928);
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
        ((GeneralPath) shape).moveTo(399.579, 340.067);
        ((GeneralPath) shape).lineTo(403.837, 340.067);
        ((GeneralPath) shape).lineTo(403.837, 339.067);
        ((GeneralPath) shape).lineTo(401.463, 339.067);
        ((GeneralPath) shape).curveTo(400.71802, 339.067, 400.34802, 338.89697,
                400.34802, 338.03598);
        ((GeneralPath) shape).curveTo(400.34802, 337.45197, 400.55704, 337.317,
                401.08502, 337.317);
        ((GeneralPath) shape).lineTo(403.83704, 337.317);
        ((GeneralPath) shape).lineTo(403.83704, 336.317);
        ((GeneralPath) shape).lineTo(401.46304, 336.317);
        ((GeneralPath) shape).curveTo(400.71805, 336.317, 400.34805, 336.16098,
                400.34805, 335.36);
        ((GeneralPath) shape).curveTo(400.34805, 334.817, 400.55707, 334.692,
                401.08505, 334.692);
        ((GeneralPath) shape).lineTo(403.83707, 334.692);
        ((GeneralPath) shape).lineTo(403.83707, 333.692);
        ((GeneralPath) shape).lineTo(400.98907, 333.692);
        ((GeneralPath) shape).curveTo(399.9561, 333.692, 399.57907, 334.042,
                399.57907, 335.09998);
        ((GeneralPath) shape).curveTo(399.57907, 335.65097, 399.73907,
                336.21497, 400.32208, 336.40497);
        ((GeneralPath) shape).lineTo(400.32208, 336.43597);
        ((GeneralPath) shape).curveTo(399.71408, 336.54697, 399.57907, 337.149,
                399.57907, 337.67197);
        ((GeneralPath) shape).curveTo(399.57907, 338.23096, 399.69806,
                338.82996, 400.22806, 339.06696);
        ((GeneralPath) shape).lineTo(399.57907, 339.06696);
        ((GeneralPath) shape).lineTo(399.57907, 340.067);
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
        ((GeneralPath) shape).moveTo(400.348, 330.895);
        ((GeneralPath) shape).curveTo(400.348, 330.02798, 400.603, 329.957,
                401.72998, 329.957);
        ((GeneralPath) shape).curveTo(402.839, 329.957, 403.085, 330.027,
                403.085, 330.895);
        ((GeneralPath) shape).curveTo(403.085, 331.76297, 402.839, 331.83298,
                401.72998, 331.83298);
        ((GeneralPath) shape).curveTo(400.603, 331.832, 400.348, 331.762,
                400.348, 330.895);
        ((GeneralPath) shape).moveTo(399.579, 330.895);
        ((GeneralPath) shape).curveTo(399.579, 332.61398, 400.09702, 332.83298,
                401.72202, 332.83298);
        ((GeneralPath) shape).curveTo(403.325, 332.83298, 403.837, 332.61398,
                403.837, 330.895);
        ((GeneralPath) shape).curveTo(403.837, 329.176, 403.324, 328.957,
                401.72202, 328.957);
        ((GeneralPath) shape).curveTo(400.097, 328.957, 399.579, 329.176,
                399.579, 330.895);
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
        ((GeneralPath) shape).moveTo(399.579, 328.208);
        ((GeneralPath) shape).lineTo(403.837, 328.208);
        ((GeneralPath) shape).lineTo(403.837, 327.208);
        ((GeneralPath) shape).lineTo(401.238, 327.208);
        ((GeneralPath) shape).curveTo(400.69202, 327.208, 400.34702, 327.022,
                400.34702, 326.38602);
        ((GeneralPath) shape).curveTo(400.34702, 325.876, 400.596, 325.833,
                401.02002, 325.833);
        ((GeneralPath) shape).lineTo(401.238, 325.833);
        ((GeneralPath) shape).lineTo(401.238, 324.833);
        ((GeneralPath) shape).lineTo(400.901, 324.833);
        ((GeneralPath) shape).curveTo(400.107, 324.833, 399.579, 325.06302,
                399.579, 326.00702);
        ((GeneralPath) shape).curveTo(399.579, 326.53003, 399.712, 326.993,
                400.168, 327.208);
        ((GeneralPath) shape).lineTo(399.579, 327.208);
        ((GeneralPath) shape).lineTo(399.579, 328.208);
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
        ((GeneralPath) shape).moveTo(404.087, 319.663);
        ((GeneralPath) shape).curveTo(404.087, 320.438, 403.588, 320.438,
                403.079, 320.438);
        ((GeneralPath) shape).lineTo(399.778, 320.438);
        ((GeneralPath) shape).curveTo(399.11902, 320.438, 398.77002, 320.391,
                398.77002, 319.663);
        ((GeneralPath) shape).lineTo(397.825, 319.663);
        ((GeneralPath) shape).curveTo(397.825, 321.563, 398.71402, 321.563,
                400.40402, 321.563);
        ((GeneralPath) shape).lineTo(402.50003, 321.563);
        ((GeneralPath) shape).curveTo(404.38904, 321.563, 405.08902, 321.473,
                405.08902, 319.663);
        ((GeneralPath) shape).lineTo(404.087, 319.663);
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
        ((GeneralPath) shape).moveTo(398.771, 302.132);
        ((GeneralPath) shape).curveTo(398.771, 301.357, 399.27, 301.357,
                399.779, 301.357);
        ((GeneralPath) shape).lineTo(403.08, 301.357);
        ((GeneralPath) shape).curveTo(403.74, 301.357, 404.08798, 301.404,
                404.08798, 302.132);
        ((GeneralPath) shape).lineTo(405.09, 302.132);
        ((GeneralPath) shape).curveTo(405.09, 300.232, 404.147, 300.232,
                402.454, 300.232);
        ((GeneralPath) shape).lineTo(400.358, 300.232);
        ((GeneralPath) shape).curveTo(398.51, 300.232, 397.826, 300.322,
                397.826, 302.132);
        ((GeneralPath) shape).lineTo(398.771, 302.132);
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
        ((GeneralPath) shape).moveTo(579.758, 299.248);
        ((GeneralPath) shape).lineTo(581.778, 299.248);
        ((GeneralPath) shape).lineTo(581.778, 300.801);
        ((GeneralPath) shape).curveTo(581.778, 301.6, 581.628, 301.748,
                580.792, 301.748);
        ((GeneralPath) shape).curveTo(579.925, 301.748, 579.759, 301.55298,
                579.759, 300.75198);
        ((GeneralPath) shape).lineTo(579.759, 299.248);
        ((GeneralPath) shape).moveTo(578.756, 300.996);
        ((GeneralPath) shape).curveTo(578.756, 301.543, 578.39996, 301.74802,
                577.88696, 301.74802);
        ((GeneralPath) shape).lineTo(576.75195, 301.74802);
        ((GeneralPath) shape).lineTo(576.75195, 302.87302);
        ((GeneralPath) shape).lineTo(577.88495, 302.87302);
        ((GeneralPath) shape).curveTo(578.74194, 302.87302, 579.1879, 302.67,
                579.2609, 301.838);
        ((GeneralPath) shape).lineTo(579.29395, 301.838);
        ((GeneralPath) shape).curveTo(579.4459, 302.87302, 580.09595,
                302.87302, 580.97296, 302.87302);
        ((GeneralPath) shape).curveTo(582.35095, 302.87302, 582.764, 302.393,
                582.764, 301.139);
        ((GeneralPath) shape).lineTo(582.764, 298.12302);
        ((GeneralPath) shape).lineTo(576.75195, 298.12302);
        ((GeneralPath) shape).lineTo(576.75195, 299.24802);
        ((GeneralPath) shape).lineTo(578.756, 299.24802);
        ((GeneralPath) shape).lineTo(578.756, 300.996);
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
        ((GeneralPath) shape).moveTo(582.764, 304.871);
        ((GeneralPath) shape).lineTo(582.764, 303.871);
        ((GeneralPath) shape).lineTo(581.907, 303.871);
        ((GeneralPath) shape).lineTo(581.907, 304.871);
        ((GeneralPath) shape).lineTo(582.764, 304.871);
        ((GeneralPath) shape).moveTo(581.01, 304.871);
        ((GeneralPath) shape).lineTo(581.01, 303.871);
        ((GeneralPath) shape).lineTo(576.752, 303.871);
        ((GeneralPath) shape).lineTo(576.752, 304.871);
        ((GeneralPath) shape).lineTo(581.01, 304.871);
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
        ((GeneralPath) shape).moveTo(576.588, 308.481);
        ((GeneralPath) shape).curveTo(575.815, 308.481, 575.625, 308.30698,
                575.625, 307.49698);
        ((GeneralPath) shape).curveTo(575.625, 306.942, 575.719, 306.731,
                576.339, 306.731);
        ((GeneralPath) shape).lineTo(576.339, 305.731);
        ((GeneralPath) shape).curveTo(575.053, 305.667, 574.873, 306.44998,
                574.873, 307.49698);
        ((GeneralPath) shape).curveTo(574.873, 309.00897, 575.245, 309.481,
                576.807, 309.481);
        ((GeneralPath) shape).lineTo(581.01, 309.481);
        ((GeneralPath) shape).lineTo(581.01, 308.481);
        ((GeneralPath) shape).lineTo(580.335, 308.481);
        ((GeneralPath) shape).curveTo(580.883, 308.233, 581.01, 307.78198,
                581.01, 307.20798);
        ((GeneralPath) shape).curveTo(581.01, 305.606, 580.028, 305.606,
                578.75, 305.606);
        ((GeneralPath) shape).curveTo(577.534, 305.606, 576.752, 305.774,
                576.752, 307.20798);
        ((GeneralPath) shape).curveTo(576.752, 307.70398, 576.846, 308.22598,
                577.386, 308.481);
        ((GeneralPath) shape).lineTo(576.588, 308.481);
        ((GeneralPath) shape).moveTo(580.241, 307.497);
        ((GeneralPath) shape).curveTo(580.241, 308.349, 579.88306, 308.48102,
                578.994, 308.48102);
        ((GeneralPath) shape).curveTo(578.002, 308.48102, 577.503, 308.48102,
                577.503, 307.489);
        ((GeneralPath) shape).curveTo(577.503, 306.60602, 578.05096, 306.60602,
                578.994, 306.60602);
        ((GeneralPath) shape).curveTo(580.043, 306.606, 580.241, 306.805,
                580.241, 307.497);
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
        ((GeneralPath) shape).moveTo(582.764, 311.455);
        ((GeneralPath) shape).lineTo(582.764, 310.455);
        ((GeneralPath) shape).lineTo(576.75195, 310.455);
        ((GeneralPath) shape).lineTo(576.75195, 311.455);
        ((GeneralPath) shape).lineTo(579.06995, 311.455);
        ((GeneralPath) shape).curveTo(579.824, 311.455, 580.24097, 311.572,
                580.24097, 312.435);
        ((GeneralPath) shape).curveTo(580.24097, 313.072, 580.03094, 313.205,
                579.423, 313.205);
        ((GeneralPath) shape).lineTo(576.75195, 313.205);
        ((GeneralPath) shape).lineTo(576.75195, 314.205);
        ((GeneralPath) shape).lineTo(579.52594, 314.205);
        ((GeneralPath) shape).curveTo(580.55096, 314.205, 581.0089, 313.873,
                581.0089, 312.78098);
        ((GeneralPath) shape).curveTo(581.0089, 312.201, 580.9449, 311.70697,
                580.3279, 311.48596);
        ((GeneralPath) shape).lineTo(580.3279, 311.45496);
        ((GeneralPath) shape).lineTo(582.764, 311.45496);
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
        ((GeneralPath) shape).moveTo(580.259, 314.684);
        ((GeneralPath) shape).lineTo(580.259, 315.211);
        ((GeneralPath) shape).lineTo(578.065, 315.211);
        ((GeneralPath) shape).curveTo(577.006, 315.211, 576.752, 315.551,
                576.752, 316.668);
        ((GeneralPath) shape).curveTo(576.752, 317.764, 577.136, 318.086,
                578.405, 318.086);
        ((GeneralPath) shape).lineTo(578.405, 317.211);
        ((GeneralPath) shape).curveTo(577.95905, 317.211, 577.50305, 317.274,
                577.50305, 316.668);
        ((GeneralPath) shape).curveTo(577.50305, 316.211, 577.68207, 316.211,
                578.072, 316.211);
        ((GeneralPath) shape).lineTo(580.25903, 316.211);
        ((GeneralPath) shape).lineTo(580.25903, 317.873);
        ((GeneralPath) shape).lineTo(581.01, 317.873);
        ((GeneralPath) shape).lineTo(581.01, 316.211);
        ((GeneralPath) shape).lineTo(581.98303, 316.211);
        ((GeneralPath) shape).lineTo(581.98303, 315.211);
        ((GeneralPath) shape).lineTo(581.01, 315.211);
        ((GeneralPath) shape).lineTo(581.01, 314.684);
        ((GeneralPath) shape).lineTo(580.259, 314.684);
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
        ((GeneralPath) shape).moveTo(578.762, 321.116);
        ((GeneralPath) shape).lineTo(578.484, 321.116);
        ((GeneralPath) shape).curveTo(576.753, 321.116, 576.753, 322.305,
                576.753, 323.637);
        ((GeneralPath) shape).curveTo(576.753, 325.188, 576.901, 325.991,
                578.635, 325.991);
        ((GeneralPath) shape).curveTo(580.15204, 325.991, 580.247, 325.481,
                580.342, 323.616);
        ((GeneralPath) shape).curveTo(580.40497, 322.399, 580.42596, 322.241,
                581.06396, 322.241);
        ((GeneralPath) shape).curveTo(581.709, 322.241, 581.81995, 322.432,
                581.81995, 323.624);
        ((GeneralPath) shape).curveTo(581.81995, 324.466, 581.79694, 324.741,
                581.1439, 324.741);
        ((GeneralPath) shape).lineTo(580.95294, 324.741);
        ((GeneralPath) shape).lineTo(580.95294, 325.866);
        ((GeneralPath) shape).lineTo(581.15094, 325.866);
        ((GeneralPath) shape).curveTo(582.76495, 325.866, 582.76495, 324.897,
                582.76495, 323.624);
        ((GeneralPath) shape).curveTo(582.76495, 322.16098, 582.76495, 321.116,
                581.06396, 321.116);
        ((GeneralPath) shape).curveTo(579.454, 321.116, 579.50195, 322.145,
                579.42194, 323.458);
        ((GeneralPath) shape).curveTo(579.37494, 324.392, 579.48596, 324.866,
                578.63495, 324.866);
        ((GeneralPath) shape).curveTo(577.94495, 324.866, 577.75494, 324.716,
                577.75494, 323.624);
        ((GeneralPath) shape).curveTo(577.75494, 322.542, 577.80194, 322.241,
                578.48395, 322.241);
        ((GeneralPath) shape).lineTo(578.76196, 322.241);
        ((GeneralPath) shape).lineTo(578.76196, 321.116);
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
        ((GeneralPath) shape).moveTo(582.764, 327.771);
        ((GeneralPath) shape).lineTo(582.764, 326.771);
        ((GeneralPath) shape).lineTo(581.907, 326.771);
        ((GeneralPath) shape).lineTo(581.907, 327.771);
        ((GeneralPath) shape).lineTo(582.764, 327.771);
        ((GeneralPath) shape).moveTo(581.01, 327.771);
        ((GeneralPath) shape).lineTo(581.01, 326.771);
        ((GeneralPath) shape).lineTo(576.752, 326.771);
        ((GeneralPath) shape).lineTo(576.752, 327.771);
        ((GeneralPath) shape).lineTo(581.01, 327.771);
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
        ((GeneralPath) shape).moveTo(576.752, 331.42);
        ((GeneralPath) shape).lineTo(576.752, 332.42);
        ((GeneralPath) shape).lineTo(582.76404, 332.42);
        ((GeneralPath) shape).lineTo(582.76404, 331.42);
        ((GeneralPath) shape).lineTo(580.40204, 331.42);
        ((GeneralPath) shape).lineTo(580.40204, 331.397);
        ((GeneralPath) shape).curveTo(580.91504, 331.183, 581.01, 330.64902,
                581.01, 330.141);
        ((GeneralPath) shape).curveTo(581.01, 328.711, 580.222, 328.54498,
                578.994, 328.54498);
        ((GeneralPath) shape).curveTo(577.71704, 328.54498, 576.752, 328.54498,
                576.752, 330.141);
        ((GeneralPath) shape).curveTo(576.752, 330.719, 576.875, 331.16998,
                577.411, 331.41998);
        ((GeneralPath) shape).lineTo(576.752, 331.41998);
        ((GeneralPath) shape).moveTo(580.241, 330.436);
        ((GeneralPath) shape).curveTo(580.241, 331.302, 579.885, 331.42,
                578.994, 331.42);
        ((GeneralPath) shape).curveTo(578.002, 331.42, 577.503, 331.42,
                577.503, 330.436);
        ((GeneralPath) shape).curveTo(577.503, 329.545, 578.05096, 329.545,
                578.994, 329.545);
        ((GeneralPath) shape).curveTo(580.043, 329.545, 580.241, 329.744,
                580.241, 330.436);
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
        ((GeneralPath) shape).moveTo(579.257, 334.261);
        ((GeneralPath) shape).curveTo(580.04504, 334.261, 580.241, 334.318,
                580.241, 335.18698);
        ((GeneralPath) shape).curveTo(580.241, 336.00897, 580.17303, 336.136,
                579.257, 336.136);
        ((GeneralPath) shape).lineTo(579.257, 334.261);
        ((GeneralPath) shape).moveTo(578.087, 336.136);
        ((GeneralPath) shape).curveTo(577.503, 336.136, 577.503, 335.745,
                577.503, 335.18698);
        ((GeneralPath) shape).curveTo(577.503, 334.28497, 577.77496, 334.261,
                578.631, 334.261);
        ((GeneralPath) shape).lineTo(578.631, 337.136);
        ((GeneralPath) shape).curveTo(580.497, 337.136, 581.01, 336.90598,
                581.01, 335.18698);
        ((GeneralPath) shape).curveTo(581.01, 333.499, 580.339, 333.261,
                578.80304, 333.261);
        ((GeneralPath) shape).curveTo(577.24304, 333.261, 576.752, 333.58698,
                576.752, 335.18698);
        ((GeneralPath) shape).curveTo(576.752, 336.37997, 576.815, 337.136,
                578.08704, 337.136);
        ((GeneralPath) shape).lineTo(578.08704, 336.136);
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
        ((GeneralPath) shape).moveTo(578.756, 344.125);
        ((GeneralPath) shape).lineTo(578.756, 342.043);
        ((GeneralPath) shape).lineTo(581.881, 343.072);
        ((GeneralPath) shape).lineTo(581.881, 343.08798);
        ((GeneralPath) shape).lineTo(578.756, 344.125);
        ((GeneralPath) shape).moveTo(577.879, 344.383);
        ((GeneralPath) shape).lineTo(576.752, 344.77798);
        ((GeneralPath) shape).lineTo(576.752, 345.952);
        ((GeneralPath) shape).lineTo(582.76404, 343.909);
        ((GeneralPath) shape).lineTo(582.76404, 342.204);
        ((GeneralPath) shape).lineTo(576.752, 340.202);
        ((GeneralPath) shape).lineTo(576.752, 341.401);
        ((GeneralPath) shape).lineTo(577.879, 341.778);
        ((GeneralPath) shape).lineTo(577.879, 344.383);
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
        ((GeneralPath) shape).moveTo(581.01, 346.269);
        ((GeneralPath) shape).lineTo(576.752, 346.269);
        ((GeneralPath) shape).lineTo(576.752, 347.269);
        ((GeneralPath) shape).lineTo(579.351, 347.269);
        ((GeneralPath) shape).curveTo(579.89703, 347.269, 580.242, 347.45502,
                580.242, 348.091);
        ((GeneralPath) shape).curveTo(580.242, 348.601, 579.993, 348.644,
                579.568, 348.644);
        ((GeneralPath) shape).lineTo(579.351, 348.644);
        ((GeneralPath) shape).lineTo(579.351, 349.644);
        ((GeneralPath) shape).lineTo(579.688, 349.644);
        ((GeneralPath) shape).curveTo(580.482, 349.644, 581.01, 349.414,
                581.01, 348.47);
        ((GeneralPath) shape).curveTo(581.01, 347.947, 580.877, 347.484,
                580.421, 347.269);
        ((GeneralPath) shape).lineTo(581.01, 347.269);
        ((GeneralPath) shape).lineTo(581.01, 346.269);
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
        ((GeneralPath) shape).moveTo(581.01, 350.133);
        ((GeneralPath) shape).lineTo(576.752, 350.133);
        ((GeneralPath) shape).lineTo(576.752, 351.133);
        ((GeneralPath) shape).lineTo(579.125, 351.133);
        ((GeneralPath) shape).curveTo(579.871, 351.133, 580.241, 351.303,
                580.241, 352.164);
        ((GeneralPath) shape).curveTo(580.241, 352.74802, 580.031, 352.883,
                579.50305, 352.883);
        ((GeneralPath) shape).lineTo(576.7521, 352.883);
        ((GeneralPath) shape).lineTo(576.7521, 353.883);
        ((GeneralPath) shape).lineTo(579.12506, 353.883);
        ((GeneralPath) shape).curveTo(579.87103, 353.883, 580.2411, 354.039,
                580.2411, 354.84);
        ((GeneralPath) shape).curveTo(580.2411, 355.383, 580.03107, 355.508,
                579.5031, 355.508);
        ((GeneralPath) shape).lineTo(576.75214, 355.508);
        ((GeneralPath) shape).lineTo(576.75214, 356.508);
        ((GeneralPath) shape).lineTo(579.60016, 356.508);
        ((GeneralPath) shape).curveTo(580.6332, 356.508, 581.01013, 356.158,
                581.01013, 355.1);
        ((GeneralPath) shape).curveTo(581.01013, 354.549, 580.85016, 353.98502,
                580.26715, 353.795);
        ((GeneralPath) shape).lineTo(580.26715, 353.764);
        ((GeneralPath) shape).curveTo(580.8751, 353.653, 581.01013, 353.051,
                581.01013, 352.528);
        ((GeneralPath) shape).curveTo(581.01013, 351.96902, 580.8911,
                351.37003, 580.36115, 351.13303);
        ((GeneralPath) shape).lineTo(581.01013, 351.13303);
        ((GeneralPath) shape).lineTo(581.01013, 350.133);
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
        ((GeneralPath) shape).moveTo(580.241, 359.31);
        ((GeneralPath) shape).curveTo(580.241, 360.177, 579.986, 360.248,
                578.859, 360.248);
        ((GeneralPath) shape).curveTo(577.75, 360.248, 577.503, 360.17798,
                577.503, 359.31);
        ((GeneralPath) shape).curveTo(577.503, 358.44202, 577.75, 358.372,
                578.859, 358.372);
        ((GeneralPath) shape).curveTo(579.986, 358.372, 580.241, 358.442,
                580.241, 359.31);
        ((GeneralPath) shape).moveTo(581.01, 359.31);
        ((GeneralPath) shape).curveTo(581.01, 357.591, 580.491, 357.372,
                578.867, 357.372);
        ((GeneralPath) shape).curveTo(577.264, 357.372, 576.752, 357.591,
                576.752, 359.31);
        ((GeneralPath) shape).curveTo(576.752, 361.029, 577.265, 361.248,
                578.867, 361.248);
        ((GeneralPath) shape).curveTo(580.491, 361.247, 581.01, 361.028,
                581.01, 359.31);
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
        ((GeneralPath) shape).moveTo(581.01, 362.0);
        ((GeneralPath) shape).lineTo(576.752, 362.0);
        ((GeneralPath) shape).lineTo(576.752, 363.0);
        ((GeneralPath) shape).lineTo(579.351, 363.0);
        ((GeneralPath) shape).curveTo(579.89703, 363.0, 580.242, 363.186,
                580.242, 363.822);
        ((GeneralPath) shape).curveTo(580.242, 364.332, 579.993, 364.375,
                579.568, 364.375);
        ((GeneralPath) shape).lineTo(579.351, 364.375);
        ((GeneralPath) shape).lineTo(579.351, 365.375);
        ((GeneralPath) shape).lineTo(579.688, 365.375);
        ((GeneralPath) shape).curveTo(580.482, 365.375, 581.01, 365.145,
                581.01, 364.202);
        ((GeneralPath) shape).curveTo(581.01, 363.679, 580.877, 363.215,
                580.421, 363.0);
        ((GeneralPath) shape).lineTo(581.01, 363.0);
        ((GeneralPath) shape).lineTo(581.01, 362.0);
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
        ((GeneralPath) shape).moveTo(576.501, 370.549);
        ((GeneralPath) shape).curveTo(576.501, 369.77402, 577.0, 369.77402,
                577.509, 369.77402);
        ((GeneralPath) shape).lineTo(580.811, 369.77402);
        ((GeneralPath) shape).curveTo(581.47, 369.77402, 581.819, 369.821,
                581.819, 370.549);
        ((GeneralPath) shape).lineTo(582.764, 370.549);
        ((GeneralPath) shape).curveTo(582.764, 368.64902, 581.875, 368.64902,
                580.185, 368.64902);
        ((GeneralPath) shape).lineTo(578.088, 368.64902);
        ((GeneralPath) shape).curveTo(576.2, 368.64902, 575.499, 368.739,
                575.499, 370.549);
        ((GeneralPath) shape).lineTo(576.501, 370.549);
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
        ((GeneralPath) shape).moveTo(581.818, 388.079);
        ((GeneralPath) shape).curveTo(581.818, 388.854, 581.319, 388.854,
                580.81, 388.854);
        ((GeneralPath) shape).lineTo(577.508, 388.854);
        ((GeneralPath) shape).curveTo(576.849, 388.854, 576.5, 388.807, 576.5,
                388.079);
        ((GeneralPath) shape).lineTo(575.498, 388.079);
        ((GeneralPath) shape).curveTo(575.498, 389.979, 576.441, 389.979,
                578.135, 389.979);
        ((GeneralPath) shape).lineTo(580.23, 389.979);
        ((GeneralPath) shape).curveTo(582.078, 389.979, 582.763, 389.889,
                582.763, 388.079);
        ((GeneralPath) shape).lineTo(581.818, 388.079);
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
        ((GeneralPath) shape).moveTo(577.711, 645.255);
        ((GeneralPath) shape).curveTo(577.711, 643.437, 576.273, 641.961,
                574.504, 641.961);
        ((GeneralPath) shape).lineTo(531.932, 641.961);
        ((GeneralPath) shape).curveTo(530.16003, 641.961, 528.724, 643.437,
                528.724, 645.255);
        ((GeneralPath) shape).lineTo(528.724, 666.662);
        ((GeneralPath) shape).curveTo(528.724, 668.48096, 530.16, 669.956,
                531.932, 669.956);
        ((GeneralPath) shape).lineTo(574.504, 669.956);
        ((GeneralPath) shape).curveTo(576.27405, 669.956, 577.711, 668.481,
                577.711, 666.662);
        ((GeneralPath) shape).lineTo(577.711, 645.255);
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
        stroke = new BasicStroke(2.0f, 0, 0, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(577.711, 645.255);
        ((GeneralPath) shape).curveTo(577.711, 643.437, 576.273, 641.961,
                574.504, 641.961);
        ((GeneralPath) shape).lineTo(531.932, 641.961);
        ((GeneralPath) shape).curveTo(530.16003, 641.961, 528.724, 643.437,
                528.724, 645.255);
        ((GeneralPath) shape).lineTo(528.724, 666.662);
        ((GeneralPath) shape).curveTo(528.724, 668.48096, 530.16, 669.954,
                531.932, 669.954);
        ((GeneralPath) shape).lineTo(574.504, 669.954);
        ((GeneralPath) shape).curveTo(576.27405, 669.954, 577.711, 668.48096,
                577.711, 666.662);
        ((GeneralPath) shape).lineTo(577.711, 645.255);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(535.331, 660.814);
        ((GeneralPath) shape).curveTo(535.166, 662.60803, 534.33, 663.403,
                532.844, 663.403);
        ((GeneralPath) shape).curveTo(531.274, 663.403, 530.417, 662.588,
                530.29297, 660.814);
        ((GeneralPath) shape).curveTo(530.21295, 659.752, 530.172, 658.69403,
                530.172, 657.614);
        ((GeneralPath) shape).curveTo(530.172, 657.143, 530.172, 656.675,
                530.193, 656.208);
        ((GeneralPath) shape).curveTo(530.193, 656.208, 530.214, 652.618,
                530.294, 651.618);
        ((GeneralPath) shape).curveTo(530.418, 649.845, 531.274, 649.009,
                532.84503, 649.009);
        ((GeneralPath) shape).curveTo(534.33105, 649.009, 535.16705, 649.845,
                535.33203, 651.618);
        ((GeneralPath) shape).curveTo(535.353, 651.946, 535.37103, 653.18896,
                535.37103, 653.699);
        ((GeneralPath) shape).lineTo(533.781, 653.699);
        ((GeneralPath) shape).curveTo(533.781, 653.16797, 533.74, 651.90497,
                533.72003, 651.579);
        ((GeneralPath) shape).curveTo(533.63904, 650.78296, 533.372, 650.435,
                532.84406, 650.435);
        ((GeneralPath) shape).curveTo(532.37305, 650.435, 532.11005, 650.802,
                532.02704, 651.579);
        ((GeneralPath) shape).curveTo(531.92706, 652.57697, 531.92706, 656.208,
                531.92706, 656.208);
        ((GeneralPath) shape).curveTo(531.90607, 656.797, 531.90607, 657.409,
                531.90607, 658.02);
        ((GeneralPath) shape).curveTo(531.90607, 658.98004, 531.92706, 659.958,
                532.02704, 660.87604);
        ((GeneralPath) shape).curveTo(532.11005, 661.64905, 532.374, 662.017,
                532.84406, 662.017);
        ((GeneralPath) shape).curveTo(533.3721, 662.017, 533.63904, 661.65,
                533.72003, 660.87604);
        ((GeneralPath) shape).curveTo(533.74005, 660.549, 533.781, 659.20404,
                533.781, 658.67303);
        ((GeneralPath) shape).lineTo(535.37103, 658.67303);
        ((GeneralPath) shape).curveTo(535.37, 659.0, 535.352, 660.507, 535.331,
                660.814);
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(539.894, 663.241);
        ((GeneralPath) shape).lineTo(539.894, 657.655);
        ((GeneralPath) shape).lineTo(538.121, 657.655);
        ((GeneralPath) shape).lineTo(538.121, 663.241);
        ((GeneralPath) shape).lineTo(536.38696, 663.241);
        ((GeneralPath) shape).lineTo(536.38696, 654.77905);
        ((GeneralPath) shape).curveTo(536.38696, 654.77905, 536.38696,
                652.82306, 536.46796, 651.92505);
        ((GeneralPath) shape).curveTo(536.613, 650.31604, 537.81396, 649.17206,
                539.3649, 649.17206);
        ((GeneralPath) shape).lineTo(541.6649, 649.17206);
        ((GeneralPath) shape).lineTo(541.6649, 663.2411);
        ((GeneralPath) shape).lineTo(539.894, 663.2411);
        ((GeneralPath) shape).moveTo(539.934, 650.641);
        ((GeneralPath) shape).lineTo(539.36505, 650.641);
        ((GeneralPath) shape).curveTo(538.89404, 650.641, 538.28503, 650.967,
                538.202, 651.68);
        ((GeneralPath) shape).curveTo(538.12103, 652.31396, 538.12103, 653.536,
                538.12103, 654.166);
        ((GeneralPath) shape).lineTo(538.12103, 656.227);
        ((GeneralPath) shape).lineTo(539.934, 656.227);
        ((GeneralPath) shape).lineTo(539.934, 650.641);
        ((GeneralPath) shape).closePath();
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(551.15, 663.241);
        ((GeneralPath) shape).lineTo(551.15, 657.655);
        ((GeneralPath) shape).lineTo(549.37805, 657.655);
        ((GeneralPath) shape).lineTo(549.37805, 663.241);
        ((GeneralPath) shape).lineTo(547.6451, 663.241);
        ((GeneralPath) shape).lineTo(547.6451, 654.77905);
        ((GeneralPath) shape).curveTo(547.6451, 654.77905, 547.6451, 652.82306,
                547.72705, 651.92505);
        ((GeneralPath) shape).curveTo(547.8691, 650.31604, 549.07306,
                649.17206, 550.6221, 649.17206);
        ((GeneralPath) shape).lineTo(552.92505, 649.17206);
        ((GeneralPath) shape).lineTo(552.92505, 663.2411);
        ((GeneralPath) shape).lineTo(551.15, 663.2411);
        ((GeneralPath) shape).moveTo(551.192, 650.641);
        ((GeneralPath) shape).lineTo(550.62103, 650.641);
        ((GeneralPath) shape).curveTo(550.15204, 650.641, 549.54004, 650.967,
                549.46, 651.68);
        ((GeneralPath) shape).curveTo(549.37805, 652.31396, 549.37805, 653.536,
                549.37805, 654.166);
        ((GeneralPath) shape).lineTo(549.37805, 656.227);
        ((GeneralPath) shape).lineTo(551.1921, 656.227);
        ((GeneralPath) shape).lineTo(551.1921, 650.641);
        ((GeneralPath) shape).closePath();
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(545.316, 650.641);
        ((GeneralPath) shape).lineTo(545.316, 660.956);
        ((GeneralPath) shape).curveTo(545.316, 661.669, 545.763, 661.975,
                546.211, 661.975);
        ((GeneralPath) shape).lineTo(546.661, 661.975);
        ((GeneralPath) shape).lineTo(546.661, 663.384);
        ((GeneralPath) shape).lineTo(546.211, 663.384);
        ((GeneralPath) shape).curveTo(544.624, 663.384, 543.563, 662.691,
                543.563, 661.099);
        ((GeneralPath) shape).lineTo(543.563, 650.641);
        ((GeneralPath) shape).lineTo(541.58496, 650.641);
        ((GeneralPath) shape).lineTo(541.58496, 649.172);
        ((GeneralPath) shape).lineTo(547.31494, 649.172);
        ((GeneralPath) shape).lineTo(547.31494, 650.641);
        ((GeneralPath) shape).lineTo(545.316, 650.641);
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(567.294, 663.403);
        ((GeneralPath) shape).curveTo(565.786, 663.403, 564.706, 662.56604,
                564.706, 660.77203);
        ((GeneralPath) shape).lineTo(564.706, 658.67206);
        ((GeneralPath) shape).lineTo(566.31396, 658.67206);
        ((GeneralPath) shape).lineTo(566.31396, 660.85406);
        ((GeneralPath) shape).curveTo(566.31396, 661.66907, 566.78394,
                661.9961, 567.29297, 661.9961);
        ((GeneralPath) shape).curveTo(567.76294, 661.9961, 568.23096, 661.6701,
                568.23096, 660.85406);
        ((GeneralPath) shape).lineTo(568.23096, 659.08105);
        ((GeneralPath) shape).curveTo(568.23096, 656.9211, 564.764, 655.7781,
                564.764, 653.12805);
        ((GeneralPath) shape).lineTo(564.764, 651.61804);
        ((GeneralPath) shape).curveTo(564.764, 649.825, 565.764, 649.00903,
                567.33295, 649.00903);
        ((GeneralPath) shape).curveTo(568.8209, 649.00903, 569.79895, 649.825,
                569.79895, 651.61804);
        ((GeneralPath) shape).lineTo(569.79895, 653.31006);
        ((GeneralPath) shape).lineTo(568.2089, 653.31006);
        ((GeneralPath) shape).lineTo(568.2089, 651.57904);
        ((GeneralPath) shape).curveTo(568.2089, 650.78204, 567.8429, 650.43506,
                567.33295, 650.43506);
        ((GeneralPath) shape).curveTo(566.863, 650.43506, 566.5159, 650.78204,
                566.5159, 651.57904);
        ((GeneralPath) shape).lineTo(566.5159, 653.10803);
        ((GeneralPath) shape).curveTo(566.5159, 654.778, 569.9619, 656.37006,
                569.9619, 659.02);
        ((GeneralPath) shape).lineTo(569.9619, 660.77203);
        ((GeneralPath) shape).curveTo(569.964, 662.566, 568.884, 663.403,
                567.294, 663.403);
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(574.0, 650.641);
        ((GeneralPath) shape).lineTo(574.0, 660.956);
        ((GeneralPath) shape).curveTo(574.0, 661.669, 574.448, 661.975,
                574.895, 661.975);
        ((GeneralPath) shape).lineTo(575.34503, 661.975);
        ((GeneralPath) shape).lineTo(575.34503, 663.384);
        ((GeneralPath) shape).lineTo(574.895, 663.384);
        ((GeneralPath) shape).curveTo(573.30804, 663.384, 572.247, 662.691,
                572.247, 661.099);
        ((GeneralPath) shape).lineTo(572.247, 650.641);
        ((GeneralPath) shape).lineTo(570.269, 650.641);
        ((GeneralPath) shape).lineTo(570.269, 649.172);
        ((GeneralPath) shape).lineTo(575.998, 649.172);
        ((GeneralPath) shape).lineTo(575.998, 650.641);
        ((GeneralPath) shape).lineTo(574.0, 650.641);
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(551.219, 666.356);
        ((GeneralPath) shape).curveTo(551.219, 666.79504, 551.13, 667.10803,
                550.91, 667.305);
        ((GeneralPath) shape).curveTo(550.69196, 667.498, 550.38995, 667.557,
                550.10394, 667.557);
        ((GeneralPath) shape).curveTo(549.8369, 667.557, 549.5529, 667.504,
                549.3729, 667.389);
        ((GeneralPath) shape).lineTo(549.4879, 666.993);
        ((GeneralPath) shape).curveTo(549.6219, 667.069, 549.84393, 667.151,
                550.09894, 667.151);
        ((GeneralPath) shape).curveTo(550.44196, 667.151, 550.69995, 666.969,
                550.69995, 666.52);
        ((GeneralPath) shape).lineTo(550.69995, 666.34);
        ((GeneralPath) shape).lineTo(550.69196, 666.34);
        ((GeneralPath) shape).curveTo(550.571, 666.52405, 550.35693, 666.648,
                550.08093, 666.648);
        ((GeneralPath) shape).curveTo(549.5609, 666.648, 549.19293, 666.219,
                549.19293, 665.62604);
        ((GeneralPath) shape).curveTo(549.19293, 664.94006, 549.63794,
                664.52203, 550.1409, 664.52203);
        ((GeneralPath) shape).curveTo(550.4599, 664.52203, 550.64795,
                664.67804, 550.7459, 664.848);
        ((GeneralPath) shape).lineTo(550.7549, 664.848);
        ((GeneralPath) shape).lineTo(550.7759, 664.57);
        ((GeneralPath) shape).lineTo(551.2369, 664.57);
        ((GeneralPath) shape).curveTo(551.22687, 664.71204, 551.21686, 664.883,
                551.21686, 665.171);
        ((GeneralPath) shape).lineTo(551.21686, 666.356);
        ((GeneralPath) shape).moveTo(550.692, 665.407);
        ((GeneralPath) shape).curveTo(550.692, 665.36, 550.687, 665.307,
                550.67303, 665.266);
        ((GeneralPath) shape).curveTo(550.61804, 665.073, 550.46704, 664.927,
                550.24304, 664.927);
        ((GeneralPath) shape).curveTo(549.94806, 664.927, 549.72705, 665.183,
                549.72705, 665.609);
        ((GeneralPath) shape).curveTo(549.72705, 665.965, 549.90906, 666.255,
                550.24005, 666.255);
        ((GeneralPath) shape).curveTo(550.4371, 666.255, 550.60803, 666.126,
                550.67004, 665.937);
        ((GeneralPath) shape).curveTo(550.68207, 665.88, 550.69104, 665.806,
                550.69104, 665.748);
        ((GeneralPath) shape).lineTo(550.69104, 665.407);
        ((GeneralPath) shape).closePath();
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(553.239, 666.16);
        ((GeneralPath) shape).curveTo(553.239, 666.34796, 553.247, 666.53296,
                553.268, 666.662);
        ((GeneralPath) shape).lineTo(552.791, 666.662);
        ((GeneralPath) shape).lineTo(552.75604, 666.43097);
        ((GeneralPath) shape).lineTo(552.744, 666.43097);
        ((GeneralPath) shape).curveTo(552.614, 666.59296, 552.39703, 666.70795,
                552.125, 666.70795);
        ((GeneralPath) shape).curveTo(551.704, 666.70795, 551.468, 666.40497,
                551.468, 666.08594);
        ((GeneralPath) shape).curveTo(551.468, 665.5579, 551.93604, 665.29193,
                552.71, 665.29596);
        ((GeneralPath) shape).lineTo(552.71, 665.26294);
        ((GeneralPath) shape).curveTo(552.71, 665.12396, 552.654, 664.8969,
                552.283, 664.8969);
        ((GeneralPath) shape).curveTo(552.07605, 664.8969, 551.86304, 664.9619,
                551.721, 665.05194);
        ((GeneralPath) shape).lineTo(551.617, 664.707);
        ((GeneralPath) shape).curveTo(551.773, 664.613, 552.044, 664.52295,
                552.373, 664.52295);
        ((GeneralPath) shape).curveTo(553.044, 664.52295, 553.238, 664.94794,
                553.238, 665.40295);
        ((GeneralPath) shape).lineTo(553.238, 666.16);
        ((GeneralPath) shape).moveTo(552.723, 665.64);
        ((GeneralPath) shape).curveTo(552.35004, 665.63104, 551.994, 665.713,
                551.994, 666.031);
        ((GeneralPath) shape).curveTo(551.994, 666.238, 552.12604, 666.33203,
                552.293, 666.33203);
        ((GeneralPath) shape).curveTo(552.504, 666.33203, 552.65906, 666.195,
                552.70605, 666.043);
        ((GeneralPath) shape).curveTo(552.71906, 666.00604, 552.7231,
                665.96204, 552.7231, 665.92804);
        ((GeneralPath) shape).lineTo(552.7231, 665.64);
        ((GeneralPath) shape).closePath();
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(553.625, 665.192);
        ((GeneralPath) shape).curveTo(553.625, 664.953, 553.621, 664.75104,
                553.608, 664.57);
        ((GeneralPath) shape).lineTo(554.06195, 664.57);
        ((GeneralPath) shape).lineTo(554.08496, 664.879);
        ((GeneralPath) shape).lineTo(554.09796, 664.879);
        ((GeneralPath) shape).curveTo(554.201, 664.716, 554.38995, 664.523,
                554.74194, 664.523);
        ((GeneralPath) shape).curveTo(555.0189, 664.523, 555.23096, 664.677,
                555.32294, 664.91003);
        ((GeneralPath) shape).lineTo(555.32996, 664.91003);
        ((GeneralPath) shape).curveTo(555.40295, 664.79504, 555.48895,
                664.70905, 555.5889, 664.648);
        ((GeneralPath) shape).curveTo(555.70593, 664.56604, 555.83795, 664.523,
                556.0109, 664.523);
        ((GeneralPath) shape).curveTo(556.3579, 664.523, 556.7099, 664.757,
                556.7099, 665.429);
        ((GeneralPath) shape).lineTo(556.7099, 666.663);
        ((GeneralPath) shape).lineTo(556.1939, 666.663);
        ((GeneralPath) shape).lineTo(556.1939, 665.505);
        ((GeneralPath) shape).curveTo(556.1939, 665.158, 556.0739, 664.953,
                555.8209, 664.953);
        ((GeneralPath) shape).curveTo(555.6399, 664.953, 555.50793, 665.08,
                555.44995, 665.23);
        ((GeneralPath) shape).curveTo(555.4379, 665.28296, 555.4249, 665.347,
                555.4249, 665.407);
        ((GeneralPath) shape).lineTo(555.4249, 666.66296);
        ((GeneralPath) shape).lineTo(554.9099, 666.66296);
        ((GeneralPath) shape).lineTo(554.9099, 665.451);
        ((GeneralPath) shape).curveTo(554.9099, 665.15796, 554.7949, 664.953,
                554.5489, 664.953);
        ((GeneralPath) shape).curveTo(554.3539, 664.953, 554.2189, 665.106,
                554.1709, 665.255);
        ((GeneralPath) shape).curveTo(554.1499, 665.304, 554.14087, 665.364,
                554.14087, 665.42303);
        ((GeneralPath) shape).lineTo(554.14087, 666.663);
        ((GeneralPath) shape).lineTo(553.6249, 666.663);
        ((GeneralPath) shape).lineTo(553.6249, 665.192);
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(557.47, 665.765);
        ((GeneralPath) shape).curveTo(557.483, 666.14404, 557.78094, 666.306,
                558.11597, 666.306);
        ((GeneralPath) shape).curveTo(558.36, 666.306, 558.53595, 666.27106,
                558.696, 666.21);
        ((GeneralPath) shape).lineTo(558.772, 666.575);
        ((GeneralPath) shape).curveTo(558.59094, 666.648, 558.342, 666.708,
                558.042, 666.708);
        ((GeneralPath) shape).curveTo(557.364, 666.708, 556.963, 666.288,
                556.963, 665.649);
        ((GeneralPath) shape).curveTo(556.963, 665.069, 557.317, 664.523,
                557.985, 664.523);
        ((GeneralPath) shape).curveTo(558.67, 664.523, 558.88696, 665.081,
                558.88696, 665.541);
        ((GeneralPath) shape).curveTo(558.88696, 665.64, 558.87897, 665.718,
                558.86993, 665.765);
        ((GeneralPath) shape).lineTo(557.47, 665.765);
        ((GeneralPath) shape).moveTo(558.389, 665.395);
        ((GeneralPath) shape).curveTo(558.393, 665.203, 558.30896, 664.88403,
                557.954, 664.88403);
        ((GeneralPath) shape).curveTo(557.62897, 664.88403, 557.492, 665.18005,
                557.47, 665.395);
        ((GeneralPath) shape).lineTo(558.389, 665.395);
        ((GeneralPath) shape).closePath();
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
        shape = new Rectangle2D.Double(562.72900390625, 663.6110229492188,
                0.5289999842643738, 3.052000045776367);
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(565.284, 666.16);
        ((GeneralPath) shape).curveTo(565.284, 666.34796, 565.291, 666.53296,
                565.314, 666.662);
        ((GeneralPath) shape).lineTo(564.835, 666.662);
        ((GeneralPath) shape).lineTo(564.802, 666.43097);
        ((GeneralPath) shape).lineTo(564.788, 666.43097);
        ((GeneralPath) shape).curveTo(564.661, 666.59296, 564.44104, 666.70795,
                564.171, 666.70795);
        ((GeneralPath) shape).curveTo(563.749, 666.70795, 563.513, 666.40497,
                563.513, 666.08594);
        ((GeneralPath) shape).curveTo(563.513, 665.5579, 563.983, 665.29193,
                564.754, 665.29596);
        ((GeneralPath) shape).lineTo(564.754, 665.26294);
        ((GeneralPath) shape).curveTo(564.754, 665.12396, 564.69904, 664.8969,
                564.33, 664.8969);
        ((GeneralPath) shape).curveTo(564.12103, 664.8969, 563.909, 664.9619,
                563.767, 665.05194);
        ((GeneralPath) shape).lineTo(563.663, 664.707);
        ((GeneralPath) shape).curveTo(563.81805, 664.613, 564.088, 664.52295,
                564.419, 664.52295);
        ((GeneralPath) shape).curveTo(565.089, 664.52295, 565.283, 664.94794,
                565.283, 665.40295);
        ((GeneralPath) shape).lineTo(565.283, 666.16);
        ((GeneralPath) shape).moveTo(564.769, 665.64);
        ((GeneralPath) shape).curveTo(564.394, 665.63104, 564.039, 665.713,
                564.039, 666.031);
        ((GeneralPath) shape).curveTo(564.039, 666.238, 564.172, 666.33203,
                564.338, 666.33203);
        ((GeneralPath) shape).curveTo(564.54803, 666.33203, 564.70404, 666.195,
                564.752, 666.043);
        ((GeneralPath) shape).curveTo(564.765, 666.00604, 564.77, 665.96204,
                564.77, 665.92804);
        ((GeneralPath) shape).lineTo(564.77, 665.64);
        ((GeneralPath) shape).closePath();
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(565.669, 663.611);
        ((GeneralPath) shape).lineTo(566.197, 663.611);
        ((GeneralPath) shape).lineTo(566.197, 664.85803);
        ((GeneralPath) shape).lineTo(566.20703, 664.85803);
        ((GeneralPath) shape).curveTo(566.33704, 664.65704, 566.56305, 664.523,
                566.87805, 664.523);
        ((GeneralPath) shape).curveTo(567.38806, 664.523, 567.7521, 664.947,
                567.749, 665.583);
        ((GeneralPath) shape).curveTo(567.749, 666.33704, 567.27203, 666.71,
                566.80005, 666.71);
        ((GeneralPath) shape).curveTo(566.52905, 666.71, 566.288, 666.606,
                566.13806, 666.351);
        ((GeneralPath) shape).lineTo(566.13007, 666.351);
        ((GeneralPath) shape).lineTo(566.10406, 666.663);
        ((GeneralPath) shape).lineTo(565.6531, 666.663);
        ((GeneralPath) shape).curveTo(565.6631, 666.521, 565.6701, 666.289,
                565.6701, 666.07404);
        ((GeneralPath) shape).lineTo(565.6701, 663.611);
        ((GeneralPath) shape).moveTo(566.197, 665.799);
        ((GeneralPath) shape).curveTo(566.197, 665.843, 566.202, 665.886,
                566.21204, 665.92303);
        ((GeneralPath) shape).curveTo(566.267, 666.13403, 566.45306, 666.294,
                566.68005, 666.294);
        ((GeneralPath) shape).curveTo(567.01105, 666.294, 567.2131, 666.026,
                567.2131, 665.606);
        ((GeneralPath) shape).curveTo(567.2131, 665.236, 567.0361, 664.93604,
                566.6841, 664.93604);
        ((GeneralPath) shape).curveTo(566.46906, 664.93604, 566.2751,
                665.08905, 566.2151, 665.32104);
        ((GeneralPath) shape).curveTo(566.2071, 665.361, 566.1971, 665.40704,
                566.1971, 665.46);
        ((GeneralPath) shape).lineTo(566.1971, 665.799);
        ((GeneralPath) shape).closePath();
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(567.984, 666.176);
        ((GeneralPath) shape).curveTo(568.105, 666.249, 568.333, 666.325,
                568.521, 666.325);
        ((GeneralPath) shape).curveTo(568.755, 666.325, 568.856, 666.23303,
                568.856, 666.096);
        ((GeneralPath) shape).curveTo(568.856, 665.952, 568.77, 665.88,
                568.51105, 665.79);
        ((GeneralPath) shape).curveTo(568.10504, 665.649, 567.93304, 665.425,
                567.9371, 665.18);
        ((GeneralPath) shape).curveTo(567.9371, 664.812, 568.24207, 664.524,
                568.7281, 664.524);
        ((GeneralPath) shape).curveTo(568.9601, 664.524, 569.1611, 664.583,
                569.2821, 664.648);
        ((GeneralPath) shape).lineTo(569.1781, 665.02203);
        ((GeneralPath) shape).curveTo(569.0881, 664.96906, 568.9211, 664.90204,
                568.7371, 664.90204);
        ((GeneralPath) shape).curveTo(568.54913, 664.90204, 568.4461, 664.991,
                568.4461, 665.119);
        ((GeneralPath) shape).curveTo(568.4461, 665.254, 568.5441, 665.317,
                568.8101, 665.414);
        ((GeneralPath) shape).curveTo(569.1881, 665.551, 569.3651, 665.744,
                569.3701, 666.053);
        ((GeneralPath) shape).curveTo(569.3701, 666.432, 569.07214, 666.709,
                568.5191, 666.709);
        ((GeneralPath) shape).curveTo(568.2641, 666.709, 568.0381, 666.646,
                567.8821, 666.56);
        ((GeneralPath) shape).lineTo(567.984, 666.176);
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(563.539, 652.302);
        ((GeneralPath) shape).lineTo(563.539, 649.189);
        ((GeneralPath) shape).lineTo(562.438, 649.189);
        ((GeneralPath) shape).lineTo(561.80597, 650.35504);
        ((GeneralPath) shape).lineTo(561.80597, 655.14703);
        ((GeneralPath) shape).curveTo(561.80597, 655.14703, 561.80597,
                657.39105, 561.70294, 658.28705);
        ((GeneralPath) shape).curveTo(561.64197, 658.98004, 561.35596, 659.348,
                560.90894, 659.348);
        ((GeneralPath) shape).curveTo(560.43994, 659.348, 560.17596, 658.981,
                560.09296, 658.28705);
        ((GeneralPath) shape).curveTo(560.0369, 657.78705, 560.01196, 656.869,
                559.99896, 656.1531);
        ((GeneralPath) shape).curveTo(559.993, 655.6401, 559.993, 655.2591,
                559.993, 655.2591);
        ((GeneralPath) shape).lineTo(559.99097, 653.5381);
        ((GeneralPath) shape).curveTo(559.99097, 653.5381, 559.019, 655.4171,
                558.66, 655.6571);
        ((GeneralPath) shape).curveTo(558.29395, 655.9031, 558.33496, 656.1771,
                558.33496, 656.1771);
        ((GeneralPath) shape).curveTo(558.33496, 656.1771, 558.29694, 657.9041,
                558.34296, 658.37714);
        ((GeneralPath) shape).curveTo(558.4639, 659.6591, 558.99396, 660.51917,
                560.03296, 660.78314);
        ((GeneralPath) shape).lineTo(560.03296, 661.41113);
        ((GeneralPath) shape).curveTo(560.02795, 661.41516, 560.022, 661.42114,
                560.01697, 661.42413);
        ((GeneralPath) shape).lineTo(560.01697, 666.97815);
        ((GeneralPath) shape).lineTo(561.74994, 665.24615);
        ((GeneralPath) shape).lineTo(561.74994, 665.2402);
        ((GeneralPath) shape).lineTo(561.76697, 665.2242);
        ((GeneralPath) shape).lineTo(561.76697, 660.7842);
        ((GeneralPath) shape).curveTo(562.78595, 660.51917, 563.337, 659.66016,
                563.519, 658.3782);
        ((GeneralPath) shape).curveTo(563.54, 657.50116, 563.54, 655.2602,
                563.54, 655.2602);
        ((GeneralPath) shape).lineTo(563.54, 652.3032);
        ((GeneralPath) shape).lineTo(563.539, 652.3032);
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(556.101, 654.679);
        ((GeneralPath) shape).lineTo(554.369, 651.769);
        ((GeneralPath) shape).lineTo(554.369, 663.241);
        ((GeneralPath) shape).lineTo(558.751, 663.241);
        ((GeneralPath) shape).lineTo(558.751, 661.813);
        ((GeneralPath) shape).lineTo(556.101, 661.813);
        ((GeneralPath) shape).lineTo(556.101, 654.679);
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(570.057, 663.831);
        ((GeneralPath) shape).lineTo(569.821, 663.831);
        ((GeneralPath) shape).lineTo(569.821, 663.746);
        ((GeneralPath) shape).lineTo(570.396, 663.746);
        ((GeneralPath) shape).lineTo(570.396, 663.831);
        ((GeneralPath) shape).lineTo(570.16, 663.831);
        ((GeneralPath) shape).lineTo(570.16, 664.521);
        ((GeneralPath) shape).lineTo(570.057, 664.521);
        ((GeneralPath) shape).lineTo(570.057, 663.831);
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(571.132, 664.181);
        ((GeneralPath) shape).curveTo(571.127, 664.07306, 571.12, 663.942,
                571.12, 663.846);
        ((GeneralPath) shape).lineTo(571.117, 663.846);
        ((GeneralPath) shape).curveTo(571.09, 663.935, 571.058, 664.033,
                571.02, 664.14);
        ((GeneralPath) shape).lineTo(570.882, 664.517);
        ((GeneralPath) shape).lineTo(570.807, 664.517);
        ((GeneralPath) shape).lineTo(570.68, 664.14703);
        ((GeneralPath) shape).curveTo(570.644, 664.03503, 570.614, 663.937,
                570.591, 663.846);
        ((GeneralPath) shape).lineTo(570.59, 663.846);
        ((GeneralPath) shape).curveTo(570.58704, 663.943, 570.58105, 664.072,
                570.57404, 664.189);
        ((GeneralPath) shape).lineTo(570.55304, 664.521);
        ((GeneralPath) shape).lineTo(570.45703, 664.521);
        ((GeneralPath) shape).lineTo(570.51105, 663.746);
        ((GeneralPath) shape).lineTo(570.63904, 663.746);
        ((GeneralPath) shape).lineTo(570.77106, 664.12);
        ((GeneralPath) shape).curveTo(570.8041, 664.216, 570.82904, 664.303,
                570.84906, 664.382);
        ((GeneralPath) shape).lineTo(570.8531, 664.382);
        ((GeneralPath) shape).curveTo(570.8731, 664.304, 570.90106, 664.219,
                570.93506, 664.12);
        ((GeneralPath) shape).lineTo(571.07306, 663.746);
        ((GeneralPath) shape).lineTo(571.2001, 663.746);
        ((GeneralPath) shape).lineTo(571.2491, 664.521);
        ((GeneralPath) shape).lineTo(571.1491, 664.521);
        ((GeneralPath) shape).lineTo(571.132, 664.181);
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
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(563.228, 644.357);
        ((GeneralPath) shape).curveTo(563.228, 644.357, 562.87103, 644.579,
                562.73804, 644.765);
        ((GeneralPath) shape).curveTo(562.689, 645.004, 563.00507, 645.274,
                562.77905, 645.465);
        ((GeneralPath) shape).lineTo(560.56506, 646.83704);
        ((GeneralPath) shape).lineTo(554.20306, 647.189);
        ((GeneralPath) shape).curveTo(554.20306, 647.189, 552.6501, 647.20404,
                553.7731, 649.018);
        ((GeneralPath) shape).lineTo(556.7491, 653.821);
        ((GeneralPath) shape).curveTo(557.8741, 655.635, 558.5761, 654.251,
                558.5761, 654.251);
        ((GeneralPath) shape).lineTo(561.7221, 648.70795);
        ((GeneralPath) shape).lineTo(563.7431, 647.45593);
        ((GeneralPath) shape).lineTo(563.9361, 647.33496);
        ((GeneralPath) shape).curveTo(564.20807, 647.21796, 564.3101,
                647.62195, 564.5461, 647.68494);
        ((GeneralPath) shape).curveTo(564.7701, 647.64996, 565.12805,
                647.42596, 565.12805, 647.42596);
        ((GeneralPath) shape).lineTo(563.228, 644.357);
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
        stroke = new BasicStroke(1.0f, 1, 1, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(563.228, 644.357);
        ((GeneralPath) shape).curveTo(563.228, 644.357, 562.87103, 644.579,
                562.74005, 644.764);
        ((GeneralPath) shape).curveTo(562.69006, 645.00195, 563.00507, 645.272,
                562.77905, 645.464);
        ((GeneralPath) shape).lineTo(560.56506, 646.837);
        ((GeneralPath) shape).lineTo(554.20306, 647.18896);
        ((GeneralPath) shape).curveTo(554.20306, 647.18896, 552.6501, 647.204,
                553.7731, 649.016);
        ((GeneralPath) shape).lineTo(556.7491, 653.821);
        ((GeneralPath) shape).curveTo(557.8741, 655.634, 558.5761, 654.24896,
                558.5761, 654.24896);
        ((GeneralPath) shape).lineTo(561.7221, 648.70795);
        ((GeneralPath) shape).lineTo(563.7431, 647.45593);
        ((GeneralPath) shape).lineTo(563.9361, 647.33496);
        ((GeneralPath) shape).curveTo(564.20807, 647.217, 564.3101, 647.62195,
                564.5461, 647.68494);
        ((GeneralPath) shape).curveTo(564.7701, 647.64795, 565.12805,
                647.42596, 565.12805, 647.42596);
        ((GeneralPath) shape).lineTo(563.228, 644.357);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        stroke = new BasicStroke(0.5f, 1, 1, 4.0f, null, 0.0f);
        shape = new Line2D.Float(557.333008f, 648.414978f, 556.536987f,
                647.130981f);
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
        paint = new Color(35, 31, 32, 255);
        stroke = new BasicStroke(0.5f, 1, 1, 4.0f, null, 0.0f);
        shape = new Line2D.Float(556.090027f, 648.432983f, 555.294006f,
                647.146973f);
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        stroke = new BasicStroke(0.5f, 1, 1, 4.0f, null, 0.0f);
        shape = new Line2D.Float(559.820984f, 648.260010f, 559.023987f,
                646.976013f);
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
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_198 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        stroke = new BasicStroke(0.5f, 1, 1, 4.0f, null, 0.0f);
        shape = new Line2D.Float(558.578003f, 648.260010f, 557.781006f,
                646.976013f);
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        stroke = new BasicStroke(0.5f, 1, 1, 4.0f, null, 0.0f);
        shape = new Line2D.Float(554.846985f, 648.578979f, 554.051025f,
                647.294006f);
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
        origAlpha = alpha__0_200;
        g.setTransform(defaultTransform__0_200);
        g.setClip(clip__0_200);
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
