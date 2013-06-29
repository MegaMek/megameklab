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

import javax.swing.ImageIcon;

/**
 * This class has been automatically generated using <a
 * href="http://englishjavadrinker.blogspot.com/search/label/SVGRoundTrip"
 * >SVGRoundTrip</a>.
 */
public class SuperHeavyBaseTemplate {
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
        paint = new Color(199, 200, 202, 255);
        shape = new GeneralPath();
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
        // _0_1 is CompositeGraphicsNode
        float alpha__0_1_0 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_1_0 = g.getClip();
        AffineTransform defaultTransform__0_1_0 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_1_0 is ShapeNode
        paint = new Color(199, 200, 202, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(394.108, 77.891);
        ((GeneralPath) shape).lineTo(388.112, 83.886);
        ((GeneralPath) shape).lineTo(46.406, 83.886);
        ((GeneralPath) shape).lineTo(41.162, 77.146);
        ((GeneralPath) shape).lineTo(41.162, 40.424);
        ((GeneralPath) shape).lineTo(46.406, 35.927);
        ((GeneralPath) shape).lineTo(140.076, 35.927);
        ((GeneralPath) shape).lineTo(142.325, 38.928);
        ((GeneralPath) shape).lineTo(318.427, 38.928);
        ((GeneralPath) shape).lineTo(331.16, 32.181);
        ((GeneralPath) shape).lineTo(387.36, 32.181);
        ((GeneralPath) shape).lineTo(394.108, 37.423);
        ((GeneralPath) shape).lineTo(394.108, 77.891);
        g.setPaint(paint);
        g.fill(shape);
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(389.96, 74.211);
        ((GeneralPath) shape).lineTo(383.965, 80.208);
        ((GeneralPath) shape).lineTo(41.925, 80.208);
        ((GeneralPath) shape).lineTo(36.674, 73.459);
        ((GeneralPath) shape).lineTo(36.674, 36.705);
        ((GeneralPath) shape).lineTo(41.925, 32.205);
        ((GeneralPath) shape).lineTo(57.814, 32.205);
        ((GeneralPath) shape).lineTo(60.064, 35.207);
        ((GeneralPath) shape).lineTo(314.209, 35.207);
        ((GeneralPath) shape).lineTo(326.954, 28.458);
        ((GeneralPath) shape).lineTo(383.213, 28.458);
        ((GeneralPath) shape).lineTo(389.96, 33.701);
        ((GeneralPath) shape).lineTo(389.96, 74.211);
        g.setPaint(paint);
        g.fill(shape);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(389.96, 74.211);
        ((GeneralPath) shape).lineTo(390.65198, 74.894);
        ((GeneralPath) shape).lineTo(384.64597, 80.899994);
        ((GeneralPath) shape).curveTo(384.46198, 81.08099, 384.21796, 81.17999,
                383.96396, 81.17999);
        ((GeneralPath) shape).lineTo(41.925, 81.17999);
        ((GeneralPath) shape).curveTo(41.627, 81.17999, 41.34, 81.038994,
                41.155, 80.801994);
        ((GeneralPath) shape).lineTo(35.903, 74.05499);
        ((GeneralPath) shape).curveTo(35.771, 73.88799, 35.701, 73.67799,
                35.701, 73.45899);
        ((GeneralPath) shape).lineTo(35.701, 36.705);
        ((GeneralPath) shape).curveTo(35.701, 36.424004, 35.825, 36.153004,
                36.042, 35.968002);
        ((GeneralPath) shape).lineTo(41.295, 31.469002);
        ((GeneralPath) shape).curveTo(41.468998, 31.312002, 41.688, 31.234001,
                41.924, 31.234001);
        ((GeneralPath) shape).lineTo(57.813, 31.234001);
        ((GeneralPath) shape).curveTo(58.119, 31.234001, 58.408, 31.374,
                58.593, 31.618002);
        ((GeneralPath) shape).lineTo(60.552, 34.235);
        ((GeneralPath) shape).lineTo(313.964, 34.235);
        ((GeneralPath) shape).lineTo(326.49698, 27.599);
        ((GeneralPath) shape).curveTo(326.63797, 27.521002, 326.79498, 27.486,
                326.95297, 27.486);
        ((GeneralPath) shape).lineTo(383.21198, 27.486);
        ((GeneralPath) shape).curveTo(383.43097, 27.486, 383.63898, 27.555,
                383.80597, 27.688);
        ((GeneralPath) shape).lineTo(390.55597, 32.939);
        ((GeneralPath) shape).curveTo(390.79095, 33.124, 390.93298, 33.404,
                390.93298, 33.701);
        ((GeneralPath) shape).lineTo(390.93298, 74.212006);
        ((GeneralPath) shape).curveTo(390.93298, 74.466, 390.827, 74.72001,
                390.65198, 74.895004);
        ((GeneralPath) shape).lineTo(389.96, 74.211);
        ((GeneralPath) shape).lineTo(388.98898, 74.211);
        ((GeneralPath) shape).lineTo(388.98898, 34.183);
        ((GeneralPath) shape).lineTo(382.878, 29.429998);
        ((GeneralPath) shape).lineTo(327.19897, 29.429998);
        ((GeneralPath) shape).lineTo(314.66296, 36.065);
        ((GeneralPath) shape).curveTo(314.51596, 36.142, 314.36597, 36.177998,
                314.20895, 36.177998);
        ((GeneralPath) shape).lineTo(60.064, 36.177998);
        ((GeneralPath) shape).curveTo(59.758, 36.177998, 59.468998, 36.031,
                59.285, 35.783997);
        ((GeneralPath) shape).lineTo(57.332, 33.175995);
        ((GeneralPath) shape).lineTo(42.284, 33.175995);
        ((GeneralPath) shape).lineTo(37.645, 37.148994);
        ((GeneralPath) shape).lineTo(37.645, 73.127);
        ((GeneralPath) shape).lineTo(42.397, 79.236);
        ((GeneralPath) shape).lineTo(383.562, 79.236);
        ((GeneralPath) shape).lineTo(389.278, 73.522);
        ((GeneralPath) shape).lineTo(389.96, 74.211);
        ((GeneralPath) shape).lineTo(388.98898, 74.211);
        ((GeneralPath) shape).lineTo(389.96, 74.211);
        g.setPaint(paint);
        g.fill(shape);
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
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f,
                -2.2629525661468506f, 5.594630718231201f));
        // _0_2 is CompositeGraphicsNode
        float alpha__0_2_0 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_2_0 = g.getClip();
        AffineTransform defaultTransform__0_2_0 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        clip = new Area(g.getClip());
        clip.intersect(new Area(new Rectangle2D.Double(310.0649719238281,
                24.13599967956543, 79.46902465820312, 49.85000038146973)));
        g.setClip(clip);
        // _0_2_0 is CompositeGraphicsNode
        float alpha__0_2_0_0 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_2_0_0 = g.getClip();
        AffineTransform defaultTransform__0_2_0_0 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f,
                1.7752949133864604E-5f, 3.6172241379972547E-7f));
        clip = new Area(g.getClip());
        clip.intersect(new Area(new Rectangle2D.Double(309.9440002441406,
                24.013999938964844, 79.71099853515625, 50.097999572753906)));
        g.setClip(clip);
        // _0_2_0_0 is CompositeGraphicsNode
        float alpha__0_2_0_0_0 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_2_0_0_0 = g.getClip();
        AffineTransform defaultTransform__0_2_0_0_0 = g.getTransform();
        g.transform(new AffineTransform(0.2393999993801117f, 0.0f, 0.0f,
                0.23970000445842743f, 309.9443054199219f, 24.013700485229492f));
        // _0_2_0_0_0 is CompositeGraphicsNode
        Shape clip__0_2_0_0_0_0 = g.getClip();
        AffineTransform defaultTransform__0_2_0_0_0_0 = g.getTransform();
        g.transform(new AffineTransform(0.9812206625938416f, 0.0f, 0.0f,
                0.9812206625938416f, 0.18309551212860242f, -0.0f));
        // _0_2_0_0_0_0 is RasterImageNode

        g.drawImage(new ImageIcon(
                "data/images/recordsheets/SuperHeavyBaseTemplate.png")
                .getImage(), (int) 0.0, (int) 0.0, null);

        g.setTransform(defaultTransform__0_2_0_0_0_0);
        g.setClip(clip__0_2_0_0_0_0);
        origAlpha = alpha__0_2_0_0_0;
        g.setTransform(defaultTransform__0_2_0_0_0);
        g.setClip(clip__0_2_0_0_0);
        origAlpha = alpha__0_2_0_0;
        g.setTransform(defaultTransform__0_2_0_0);
        g.setClip(clip__0_2_0_0);
        origAlpha = alpha__0_2_0;
        g.setTransform(defaultTransform__0_2_0);
        g.setClip(clip__0_2_0);
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
        clip = new Area(g.getClip());
        clip.intersect(new Area(new Rectangle2D.Double(309.70098876953125,
                29.475000381469727, 77.29299926757812, 4.760000228881836)));
        g.setClip(clip);
        // _0_3_0 is CompositeGraphicsNode
        float alpha__0_3_0_0 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_3_0_0 = g.getClip();
        AffineTransform defaultTransform__0_3_0_0 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f,
                2.7854459403897636E-5f, 2.6375389552413253E-6f));
        clip = new Area(g.getClip());
        clip.intersect(new Area(new Rectangle2D.Double(309.5830078125,
                29.354999542236328, 77.52899169921875, 5.0410003662109375)));
        g.setClip(clip);
        // _0_3_0_0 is CompositeGraphicsNode
        float alpha__0_3_0_0_0 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_3_0_0_0 = g.getClip();
        AffineTransform defaultTransform__0_3_0_0_0 = g.getTransform();
        g.transform(new AffineTransform(0.7178999781608582f, 0.0f, 0.0f,
                0.6301000118255615f, 309.5830078125f, 29.354999542236328f));
        // _0_3_0_0_0 is CompositeGraphicsNode
        Shape clip__0_3_0_0_0_0 = g.getClip();
        AffineTransform defaultTransform__0_3_0_0_0_0 = g.getTransform();
        g.transform(new AffineTransform(0.9818181991577148f, 0.0f, 0.0f,
                0.9818181991577148f, -0.0f, 0.07272746474654923f));
        // _0_3_0_0_0_0 is RasterImageNode

        g.setTransform(defaultTransform__0_3_0_0_0_0);
        g.setClip(clip__0_3_0_0_0_0);
        origAlpha = alpha__0_3_0_0_0;
        g.setTransform(defaultTransform__0_3_0_0_0);
        g.setClip(clip__0_3_0_0_0);
        origAlpha = alpha__0_3_0_0;
        g.setTransform(defaultTransform__0_3_0_0);
        g.setClip(clip__0_3_0_0);
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
        clip = new Area(g.getClip());
        clip.intersect(new Area(new Rectangle2D.Double(35.70000076293945,
                27.486000061035156, 355.2330131530762, 53.694000244140625)));
        g.setClip(clip);
        // _0_4_0 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(37.646, 73.127);
        ((GeneralPath) shape).lineTo(37.646, 73.127);
        ((GeneralPath) shape).lineTo(42.398, 79.236);
        ((GeneralPath) shape).lineTo(383.56302, 79.236);
        ((GeneralPath) shape).lineTo(386.99402, 75.805);
        ((GeneralPath) shape).lineTo(388.99002, 73.809);
        ((GeneralPath) shape).lineTo(388.99002, 34.183);
        ((GeneralPath) shape).lineTo(382.88004, 29.429998);
        ((GeneralPath) shape).lineTo(327.20004, 29.429998);
        ((GeneralPath) shape).lineTo(314.66403, 36.063);
        ((GeneralPath) shape).curveTo(314.51703, 36.142, 314.36804, 36.177,
                314.21002, 36.177);
        ((GeneralPath) shape).lineTo(60.064, 36.177);
        ((GeneralPath) shape).curveTo(59.758, 36.177, 59.468998, 36.03, 59.285,
                35.782997);
        ((GeneralPath) shape).lineTo(57.332, 33.173996);
        ((GeneralPath) shape).lineTo(42.284, 33.173996);
        ((GeneralPath) shape).lineTo(37.645, 37.146996);
        ((GeneralPath) shape).lineTo(37.645, 73.127);
        ((GeneralPath) shape).moveTo(383.965, 81.18);
        ((GeneralPath) shape).lineTo(41.925, 81.18);
        ((GeneralPath) shape).curveTo(41.63, 81.18, 41.34, 81.038, 41.155,
                80.803);
        ((GeneralPath) shape).lineTo(35.903, 74.054);
        ((GeneralPath) shape).curveTo(35.773, 73.887, 35.701, 73.677, 35.701,
                73.458);
        ((GeneralPath) shape).lineTo(35.701, 36.705);
        ((GeneralPath) shape).curveTo(35.701, 36.423, 35.825, 36.153004,
                36.043, 35.969);
        ((GeneralPath) shape).lineTo(41.295998, 31.469002);
        ((GeneralPath) shape).curveTo(41.469997, 31.312002, 41.689, 31.233002,
                41.925, 31.233002);
        ((GeneralPath) shape).lineTo(57.815, 31.233002);
        ((GeneralPath) shape).curveTo(58.121, 31.233002, 58.409, 31.374002,
                58.593998, 31.618002);
        ((GeneralPath) shape).lineTo(60.553997, 34.234);
        ((GeneralPath) shape).lineTo(313.965, 34.234);
        ((GeneralPath) shape).lineTo(326.498, 27.599);
        ((GeneralPath) shape).curveTo(326.63898, 27.522001, 326.796, 27.486,
                326.95398, 27.486);
        ((GeneralPath) shape).lineTo(383.21298, 27.486);
        ((GeneralPath) shape).curveTo(383.43198, 27.486, 383.63998, 27.556,
                383.80698, 27.688);
        ((GeneralPath) shape).lineTo(390.55597, 32.939);
        ((GeneralPath) shape).curveTo(390.79297, 33.124, 390.93396, 33.404,
                390.93396, 33.701);
        ((GeneralPath) shape).lineTo(390.93396, 74.212006);
        ((GeneralPath) shape).curveTo(390.93396, 74.466, 390.82797, 74.72001,
                390.65195, 74.895004);
        ((GeneralPath) shape).lineTo(389.96094, 74.212006);
        ((GeneralPath) shape).lineTo(388.98993, 74.212006);
        ((GeneralPath) shape).lineTo(389.96094, 74.212006);
        ((GeneralPath) shape).lineTo(390.65195, 74.895004);
        ((GeneralPath) shape).lineTo(389.96094, 74.212006);
        ((GeneralPath) shape).lineTo(388.98993, 74.212006);
        ((GeneralPath) shape).lineTo(389.96094, 74.212006);
        ((GeneralPath) shape).lineTo(390.65195, 74.895004);
        ((GeneralPath) shape).lineTo(384.64694, 80.9);
        ((GeneralPath) shape).curveTo(384.463, 81.083, 384.219, 81.18, 383.965,
                81.18);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_4_0;
        g.setTransform(defaultTransform__0_4_0);
        g.setClip(clip__0_4_0);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(44.925, 46.73);
        ((GeneralPath) shape).lineTo(51.515, 46.73);
        ((GeneralPath) shape).lineTo(51.515, 68.707);
        ((GeneralPath) shape).lineTo(44.925, 68.707);
        ((GeneralPath) shape).lineTo(44.925, 46.73);
        ((GeneralPath) shape).moveTo(51.518, 55.521);
        ((GeneralPath) shape).lineTo(63.969, 55.521);
        ((GeneralPath) shape).lineTo(63.969, 59.916);
        ((GeneralPath) shape).lineTo(51.518, 59.916);
        ((GeneralPath) shape).lineTo(51.518, 55.521);
        ((GeneralPath) shape).closePath();
        ((GeneralPath) shape).moveTo(53.713, 46.73);
        ((GeneralPath) shape).lineTo(63.969, 46.73);
        ((GeneralPath) shape).lineTo(63.969, 51.126);
        ((GeneralPath) shape).lineTo(53.713, 51.126);
        ((GeneralPath) shape).lineTo(53.713, 46.73);
        ((GeneralPath) shape).closePath();
        ((GeneralPath) shape).moveTo(53.713, 64.311);
        ((GeneralPath) shape).lineTo(63.969, 64.311);
        ((GeneralPath) shape).lineTo(63.969, 68.707);
        ((GeneralPath) shape).lineTo(53.713, 68.707);
        ((GeneralPath) shape).lineTo(53.713, 64.311);
        ((GeneralPath) shape).closePath();
        ((GeneralPath) shape).moveTo(63.94, 46.759);
        ((GeneralPath) shape).curveTo(66.851, 46.740997, 68.812, 47.237,
                69.822, 48.246998);
        ((GeneralPath) shape).curveTo(70.833, 49.258, 71.331, 50.362, 71.316,
                51.558);
        ((GeneralPath) shape).curveTo(71.302, 52.753998, 71.295, 53.874,
                71.295, 54.913);
        ((GeneralPath) shape).curveTo(71.295, 55.951996, 70.809, 56.849,
                69.842995, 57.600998);
        ((GeneralPath) shape).curveTo(71.785995, 58.567997, 72.758995, 60.077,
                72.758995, 62.128998);
        ((GeneralPath) shape).curveTo(72.758995, 66.514, 70.80699, 68.706,
                66.898994, 68.706);
        ((GeneralPath) shape).lineTo(64.01299, 68.69);
        ((GeneralPath) shape).lineTo(63.93999, 64.339005);
        ((GeneralPath) shape).curveTo(64.93599, 64.32101, 65.72999, 63.944004,
                66.32099, 63.212006);
        ((GeneralPath) shape).curveTo(66.911995, 62.479004, 66.911995,
                61.748005, 66.32099, 61.014008);
        ((GeneralPath) shape).curveTo(65.72999, 60.28201, 64.94599, 59.90101,
                63.96799, 59.870007);
        ((GeneralPath) shape).lineTo(63.96799, 55.536007);
        ((GeneralPath) shape).curveTo(64.94599, 55.525005, 65.62299, 55.166008,
                66.00599, 54.458008);
        ((GeneralPath) shape).curveTo(66.38799, 53.750008, 66.38799, 53.01801,
                66.00599, 52.26101);
        ((GeneralPath) shape).curveTo(65.623985, 51.50401, 64.93599, 51.13101,
                63.939987, 51.14001);
        ((GeneralPath) shape).lineTo(63.939987, 46.759);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.fill(shape);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(74.863, 68.706);
        ((GeneralPath) shape).lineTo(86.524, 46.731);
        ((GeneralPath) shape).lineTo(95.313, 46.731);
        ((GeneralPath) shape).lineTo(107.034, 68.706);
        ((GeneralPath) shape).lineTo(99.708, 68.706);
        ((GeneralPath) shape).lineTo(90.919, 51.125);
        ((GeneralPath) shape).lineTo(82.13, 68.706);
        ((GeneralPath) shape).lineTo(74.863, 68.706);
        g.setPaint(paint);
        g.fill(shape);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(104.264, 46.731);
        ((GeneralPath) shape).lineTo(129.16699, 46.731);
        ((GeneralPath) shape).lineTo(129.16699, 51.126);
        ((GeneralPath) shape).lineTo(104.26399, 51.126);
        ((GeneralPath) shape).lineTo(104.26399, 46.731);
        ((GeneralPath) shape).moveTo(113.787, 68.706);
        ((GeneralPath) shape).lineTo(119.647, 68.706);
        ((GeneralPath) shape).lineTo(119.647, 53.323);
        ((GeneralPath) shape).lineTo(113.787, 53.323);
        ((GeneralPath) shape).lineTo(113.787, 68.706);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_5_2;
        g.setTransform(defaultTransform__0_5_2);
        g.setClip(clip__0_5_2);
        float alpha__0_5_3 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_5_3 = g.getClip();
        AffineTransform defaultTransform__0_5_3 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_5_3 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(131.935, 46.731);
        ((GeneralPath) shape).lineTo(156.837, 46.731);
        ((GeneralPath) shape).lineTo(156.837, 51.126);
        ((GeneralPath) shape).lineTo(131.935, 51.126);
        ((GeneralPath) shape).lineTo(131.935, 46.731);
        ((GeneralPath) shape).moveTo(141.457, 68.706);
        ((GeneralPath) shape).lineTo(147.317, 68.706);
        ((GeneralPath) shape).lineTo(147.317, 53.323);
        ((GeneralPath) shape).lineTo(141.457, 53.323);
        ((GeneralPath) shape).lineTo(141.457, 68.706);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_5_3;
        g.setTransform(defaultTransform__0_5_3);
        g.setClip(clip__0_5_3);
        float alpha__0_5_4 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_5_4 = g.getClip();
        AffineTransform defaultTransform__0_5_4 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_5_4 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(160.271, 46.731);
        ((GeneralPath) shape).lineTo(166.864, 46.731);
        ((GeneralPath) shape).lineTo(166.864, 68.706);
        ((GeneralPath) shape).lineTo(160.271, 68.706);
        ((GeneralPath) shape).lineTo(160.271, 46.731);
        ((GeneralPath) shape).moveTo(169.061, 62.845);
        ((GeneralPath) shape).lineTo(183.712, 62.845);
        ((GeneralPath) shape).lineTo(183.712, 68.705);
        ((GeneralPath) shape).lineTo(169.061, 68.705);
        ((GeneralPath) shape).lineTo(169.061, 62.845);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_5_4;
        g.setTransform(defaultTransform__0_5_4);
        g.setClip(clip__0_5_4);
        float alpha__0_5_5 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_5_5 = g.getClip();
        AffineTransform defaultTransform__0_5_5 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_5_5 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new Rectangle2D.Double(185.7729949951172, 46.73099899291992,
                6.591000080108643, 21.975000381469727);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_5_5;
        g.setTransform(defaultTransform__0_5_5);
        g.setClip(clip__0_5_5);
        float alpha__0_5_6 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_5_6 = g.getClip();
        AffineTransform defaultTransform__0_5_6 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_5_6 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new Rectangle2D.Double(192.36599731445312, 55.520999908447266,
                15.326000213623047, 4.394999980926514);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_5_6;
        g.setTransform(defaultTransform__0_5_6);
        g.setClip(clip__0_5_6);
        float alpha__0_5_7 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_5_7 = g.getClip();
        AffineTransform defaultTransform__0_5_7 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_5_7 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new Rectangle2D.Double(194.56500244140625, 46.73099899291992,
                14.649999618530273, 5.859000205993652);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_5_7;
        g.setTransform(defaultTransform__0_5_7);
        g.setClip(clip__0_5_7);
        float alpha__0_5_8 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_5_8 = g.getClip();
        AffineTransform defaultTransform__0_5_8 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_5_8 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new Rectangle2D.Double(194.56500244140625, 62.845001220703125,
                14.649999618530273, 5.860000133514404);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_5_8;
        g.setTransform(defaultTransform__0_5_8);
        g.setClip(clip__0_5_8);
        float alpha__0_5_9 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_5_9 = g.getClip();
        AffineTransform defaultTransform__0_5_9 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_5_9 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(212.777, 46.731);
        ((GeneralPath) shape).lineTo(237.68199, 46.731);
        ((GeneralPath) shape).lineTo(237.68199, 51.126);
        ((GeneralPath) shape).lineTo(212.777, 51.126);
        ((GeneralPath) shape).lineTo(212.777, 46.731);
        ((GeneralPath) shape).moveTo(222.299, 68.706);
        ((GeneralPath) shape).lineTo(228.16, 68.706);
        ((GeneralPath) shape).lineTo(228.16, 53.323);
        ((GeneralPath) shape).lineTo(222.29901, 53.323);
        ((GeneralPath) shape).lineTo(222.29901, 68.706);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_5_9;
        g.setTransform(defaultTransform__0_5_9);
        g.setClip(clip__0_5_9);
        float alpha__0_5_10 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_5_10 = g.getClip();
        AffineTransform defaultTransform__0_5_10 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_5_10 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new Rectangle2D.Double(242.2790069580078, 46.73099899291992,
                6.5929999351501465, 21.975000381469727);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_5_10;
        g.setTransform(defaultTransform__0_5_10);
        g.setClip(clip__0_5_10);
        float alpha__0_5_11 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_5_11 = g.getClip();
        AffineTransform defaultTransform__0_5_11 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_5_11 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new Rectangle2D.Double(248.8719940185547, 55.520999908447266,
                15.824999809265137, 4.394999980926514);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_5_11;
        g.setTransform(defaultTransform__0_5_11);
        g.setClip(clip__0_5_11);
        float alpha__0_5_12 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_5_12 = g.getClip();
        AffineTransform defaultTransform__0_5_12 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_5_12 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new Rectangle2D.Double(251.06900024414062, 46.73099899291992,
                14.651000022888184, 5.859000205993652);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_5_12;
        g.setTransform(defaultTransform__0_5_12);
        g.setClip(clip__0_5_12);
        float alpha__0_5_13 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_5_13 = g.getClip();
        AffineTransform defaultTransform__0_5_13 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_5_13 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new Rectangle2D.Double(251.06900024414062, 62.845001220703125,
                14.651000022888184, 5.860000133514404);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_5_13;
        g.setTransform(defaultTransform__0_5_13);
        g.setClip(clip__0_5_13);
        float alpha__0_5_14 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_5_14 = g.getClip();
        AffineTransform defaultTransform__0_5_14 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_5_14 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(270.784, 56.618);
        ((GeneralPath) shape).lineTo(270.784, 51.859);
        ((GeneralPath) shape).curveTo(270.784, 48.477, 273.225, 46.769,
                278.109, 46.729);
        ((GeneralPath) shape).lineTo(292.028, 46.729);
        ((GeneralPath) shape).curveTo(296.90802, 46.729, 299.351, 48.439,
                299.351, 51.859);
        ((GeneralPath) shape).lineTo(299.351, 55.519);
        ((GeneralPath) shape).lineTo(292.75803, 55.519);
        ((GeneralPath) shape).curveTo(292.75803, 52.59, 291.29303, 51.126,
                288.36502, 51.126);
        ((GeneralPath) shape).lineTo(281.77103, 51.126);
        ((GeneralPath) shape).curveTo(278.83902, 51.096, 277.37402, 52.315,
                277.37402, 54.787);
        ((GeneralPath) shape).lineTo(277.37402, 56.618);
        ((GeneralPath) shape).lineTo(270.784, 56.618);
        ((GeneralPath) shape).moveTo(270.784, 58.815);
        ((GeneralPath) shape).lineTo(270.784, 63.577);
        ((GeneralPath) shape).curveTo(270.784, 66.996, 273.225, 68.706,
                278.109, 68.706);
        ((GeneralPath) shape).lineTo(289.829, 68.706);
        ((GeneralPath) shape).curveTo(295.69, 68.706, 299.108, 66.996, 300.083,
                63.577);
        ((GeneralPath) shape).lineTo(300.083, 59.916);
        ((GeneralPath) shape).lineTo(292.758, 59.916);
        ((GeneralPath) shape).curveTo(292.758, 62.847, 291.293, 64.309,
                288.365, 64.309);
        ((GeneralPath) shape).lineTo(281.771, 64.309);
        ((GeneralPath) shape).curveTo(278.839, 64.309, 277.374, 63.089996,
                277.374, 60.647);
        ((GeneralPath) shape).lineTo(277.374, 58.815);
        ((GeneralPath) shape).lineTo(270.784, 58.815);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_5_14;
        g.setTransform(defaultTransform__0_5_14);
        g.setClip(clip__0_5_14);
        float alpha__0_5_15 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_5_15 = g.getClip();
        AffineTransform defaultTransform__0_5_15 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_5_15 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(303.786, 46.731);
        ((GeneralPath) shape).lineTo(310.37802, 46.731);
        ((GeneralPath) shape).lineTo(310.37802, 68.706);
        ((GeneralPath) shape).lineTo(303.786, 68.706);
        ((GeneralPath) shape).lineTo(303.786, 46.731);
        ((GeneralPath) shape).moveTo(312.576, 54.79);
        ((GeneralPath) shape).lineTo(322.832, 54.79);
        ((GeneralPath) shape).lineTo(322.832, 60.648003);
        ((GeneralPath) shape).lineTo(312.576, 60.648003);
        ((GeneralPath) shape).lineTo(312.576, 54.79);
        ((GeneralPath) shape).closePath();
        ((GeneralPath) shape).moveTo(325.028, 46.731);
        ((GeneralPath) shape).lineTo(331.617, 46.731);
        ((GeneralPath) shape).lineTo(331.617, 68.706);
        ((GeneralPath) shape).lineTo(325.028, 68.706);
        ((GeneralPath) shape).lineTo(325.028, 46.731);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_5_15;
        g.setTransform(defaultTransform__0_5_15);
        g.setClip(clip__0_5_15);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(387.509, 27.739);
        ((GeneralPath) shape).lineTo(387.509, 29.953);
        ((GeneralPath) shape).lineTo(387.002, 29.953);
        ((GeneralPath) shape).lineTo(387.002, 27.739);
        ((GeneralPath) shape).lineTo(386.233, 27.739);
        ((GeneralPath) shape).lineTo(386.233, 27.287);
        ((GeneralPath) shape).lineTo(388.306, 27.287);
        ((GeneralPath) shape).lineTo(388.306, 27.739);
        ((GeneralPath) shape).lineTo(387.509, 27.739);
        g.setPaint(paint);
        g.fill(shape);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(391.679, 27.287);
        ((GeneralPath) shape).lineTo(391.679, 29.954);
        ((GeneralPath) shape).lineTo(391.171, 29.954);
        ((GeneralPath) shape).lineTo(391.171, 28.5);
        ((GeneralPath) shape).curveTo(391.171, 28.384, 391.176, 28.251,
                391.18198, 28.105);
        ((GeneralPath) shape).lineTo(391.19296, 27.906);
        ((GeneralPath) shape).lineTo(391.20197, 27.709);
        ((GeneralPath) shape).lineTo(391.18695, 27.709);
        ((GeneralPath) shape).lineTo(391.12695, 27.894);
        ((GeneralPath) shape).lineTo(391.06595, 28.08);
        ((GeneralPath) shape).curveTo(391.01196, 28.246, 390.96896, 28.369,
                390.93994, 28.448);
        ((GeneralPath) shape).lineTo(390.35294, 29.953);
        ((GeneralPath) shape).lineTo(389.89093, 29.953);
        ((GeneralPath) shape).lineTo(389.30093, 28.46);
        ((GeneralPath) shape).curveTo(389.26694, 28.376999, 389.22495, 28.255,
                389.17093, 28.089998);
        ((GeneralPath) shape).lineTo(389.11093, 27.904999);
        ((GeneralPath) shape).lineTo(389.04993, 27.720999);
        ((GeneralPath) shape).lineTo(389.03293, 27.720999);
        ((GeneralPath) shape).lineTo(389.0439, 27.912998);
        ((GeneralPath) shape).lineTo(389.05392, 28.110998);
        ((GeneralPath) shape).curveTo(389.06293, 28.260998, 389.0649,
                28.390999, 389.0649, 28.498999);
        ((GeneralPath) shape).lineTo(389.0649, 29.951998);
        ((GeneralPath) shape).lineTo(388.5599, 29.951998);
        ((GeneralPath) shape).lineTo(388.5599, 27.284998);
        ((GeneralPath) shape).lineTo(389.38492, 27.284998);
        ((GeneralPath) shape).lineTo(389.86093, 28.519999);
        ((GeneralPath) shape).curveTo(389.89294, 28.604998, 389.93594,
                28.727999, 389.98892, 28.888998);
        ((GeneralPath) shape).lineTo(390.0479, 29.074999);
        ((GeneralPath) shape).lineTo(390.10992, 29.256998);
        ((GeneralPath) shape).lineTo(390.12692, 29.256998);
        ((GeneralPath) shape).lineTo(390.18494, 29.074999);
        ((GeneralPath) shape).lineTo(390.24194, 28.890999);
        ((GeneralPath) shape).curveTo(390.29095, 28.734999, 390.33295,
                28.613998, 390.36795, 28.523998);
        ((GeneralPath) shape).lineTo(390.83896, 27.284998);
        ((GeneralPath) shape).lineTo(391.679, 27.284998);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_6_1;
        g.setTransform(defaultTransform__0_6_1);
        g.setClip(clip__0_6_1);
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
        paint = new Color(199, 200, 202, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(247.99, 368.266);
        ((GeneralPath) shape).lineTo(241.097, 379.293);
        ((GeneralPath) shape).lineTo(48.107, 379.293);
        ((GeneralPath) shape).lineTo(41.215, 368.266);
        ((GeneralPath) shape).lineTo(41.215, 111.865);
        ((GeneralPath) shape).lineTo(46.728, 103.595);
        ((GeneralPath) shape).lineTo(129.439, 103.595);
        ((GeneralPath) shape).lineTo(134.952, 111.865);
        ((GeneralPath) shape).lineTo(247.99, 111.865);
        ((GeneralPath) shape).lineTo(247.99, 368.266);
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
        paint = new Color(199, 200, 202, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(256.26, 179.412);
        ((GeneralPath) shape).lineTo(256.26, 111.865);
        ((GeneralPath) shape).lineTo(261.774, 103.595);
        ((GeneralPath) shape).lineTo(332.078, 103.595);
        ((GeneralPath) shape).lineTo(337.594, 111.865);
        ((GeneralPath) shape).lineTo(387.219, 111.865);
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
        paint = new Color(199, 200, 202, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(387.219, 111.865);
        ((GeneralPath) shape).lineTo(394.11, 121.515);
        ((GeneralPath) shape).lineTo(394.11, 172.518);
        ((GeneralPath) shape).lineTo(387.219, 179.412);
        ((GeneralPath) shape).lineTo(256.26, 179.412);
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
        ((GeneralPath) shape).moveTo(383.082, 107.73);
        ((GeneralPath) shape).lineTo(389.974, 117.379);
        ((GeneralPath) shape).lineTo(389.974, 168.383);
        ((GeneralPath) shape).lineTo(383.082, 175.277);
        ((GeneralPath) shape).lineTo(252.125, 175.277);
        ((GeneralPath) shape).lineTo(252.125, 107.73);
        ((GeneralPath) shape).lineTo(257.639, 99.459);
        ((GeneralPath) shape).lineTo(327.942, 99.459);
        ((GeneralPath) shape).lineTo(333.457, 107.73);
        ((GeneralPath) shape).lineTo(383.082, 107.73);
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
        stroke = new BasicStroke(1.945f, 0, 0, 10.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(383.082, 107.73);
        ((GeneralPath) shape).lineTo(389.974, 117.379);
        ((GeneralPath) shape).lineTo(389.974, 168.384);
        ((GeneralPath) shape).lineTo(383.082, 175.277);
        ((GeneralPath) shape).lineTo(252.125, 175.277);
        ((GeneralPath) shape).lineTo(252.125, 107.73);
        ((GeneralPath) shape).lineTo(257.639, 99.459);
        ((GeneralPath) shape).lineTo(327.942, 99.459);
        ((GeneralPath) shape).lineTo(333.457, 107.73);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        ((GeneralPath) shape).moveTo(243.854, 364.13);
        ((GeneralPath) shape).lineTo(236.96, 375.158);
        ((GeneralPath) shape).lineTo(43.972, 375.158);
        ((GeneralPath) shape).lineTo(37.08, 364.13);
        ((GeneralPath) shape).lineTo(37.08, 107.73);
        ((GeneralPath) shape).lineTo(42.593, 99.459);
        ((GeneralPath) shape).lineTo(125.304, 99.459);
        ((GeneralPath) shape).lineTo(130.817, 107.73);
        ((GeneralPath) shape).lineTo(243.854, 107.73);
        ((GeneralPath) shape).lineTo(243.854, 364.13);
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
        stroke = new BasicStroke(1.945f, 0, 1, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(243.854, 364.13);
        ((GeneralPath) shape).lineTo(236.961, 375.157);
        ((GeneralPath) shape).lineTo(43.973, 375.157);
        ((GeneralPath) shape).lineTo(37.081, 364.13);
        ((GeneralPath) shape).lineTo(37.081, 107.73);
        ((GeneralPath) shape).lineTo(42.593, 99.459);
        ((GeneralPath) shape).lineTo(125.304, 99.459);
        ((GeneralPath) shape).lineTo(130.817, 107.73);
        ((GeneralPath) shape).lineTo(243.854, 107.73);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        ((GeneralPath) shape).moveTo(123.937, 117.379);
        ((GeneralPath) shape).lineTo(44.33, 117.379);
        ((GeneralPath) shape).lineTo(39.092, 109.521);
        ((GeneralPath) shape).lineTo(44.317, 101.665);
        ((GeneralPath) shape).lineTo(123.924, 101.665);
        ((GeneralPath) shape).lineTo(129.163, 109.521);
        ((GeneralPath) shape).lineTo(123.937, 117.379);
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
        stroke = new BasicStroke(0.973f, 0, 1, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(123.937, 117.379);
        ((GeneralPath) shape).lineTo(44.33, 117.379);
        ((GeneralPath) shape).lineTo(39.092, 109.521);
        ((GeneralPath) shape).lineTo(44.317, 101.665);
        ((GeneralPath) shape).lineTo(123.924, 101.665);
        ((GeneralPath) shape).lineTo(129.163, 109.521);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        ((GeneralPath) shape).moveTo(326.576, 117.379);
        ((GeneralPath) shape).lineTo(258.74, 117.379);
        ((GeneralPath) shape).lineTo(253.502, 109.521);
        ((GeneralPath) shape).lineTo(258.729, 101.665);
        ((GeneralPath) shape).lineTo(326.563, 101.665);
        ((GeneralPath) shape).lineTo(331.803, 109.521);
        ((GeneralPath) shape).lineTo(326.576, 117.379);
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
        stroke = new BasicStroke(0.973f, 0, 1, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(326.576, 117.379);
        ((GeneralPath) shape).lineTo(258.74, 117.379);
        ((GeneralPath) shape).lineTo(253.502, 109.521);
        ((GeneralPath) shape).lineTo(258.727, 101.665);
        ((GeneralPath) shape).lineTo(326.563, 101.665);
        ((GeneralPath) shape).lineTo(331.803, 109.521);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        ((GeneralPath) shape).moveTo(51.004, 105.465);
        ((GeneralPath) shape).lineTo(52.563004, 105.465);
        ((GeneralPath) shape).lineTo(50.060005, 113.243996);
        ((GeneralPath) shape).lineTo(47.796005, 113.243996);
        ((GeneralPath) shape).lineTo(45.316006, 105.465);
        ((GeneralPath) shape).lineTo(46.846004, 105.465);
        ((GeneralPath) shape).lineTo(48.302006, 109.869995);
        ((GeneralPath) shape).curveTo(48.439007, 110.29199, 48.640007,
                110.99799, 48.910007, 111.99);
        ((GeneralPath) shape).lineTo(48.95001, 111.99);
        ((GeneralPath) shape).lineTo(49.098007, 111.46);
        ((GeneralPath) shape).curveTo(49.280006, 110.798, 49.434006,
                110.271996, 49.564007, 109.876);
        ((GeneralPath) shape).lineTo(51.004, 105.465);
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
        ((GeneralPath) shape).moveTo(54.528, 106.708);
        ((GeneralPath) shape).lineTo(54.528, 108.713);
        ((GeneralPath) shape).lineTo(58.168, 108.713);
        ((GeneralPath) shape).lineTo(58.168, 109.802);
        ((GeneralPath) shape).lineTo(54.528, 109.802);
        ((GeneralPath) shape).lineTo(54.528, 112.002);
        ((GeneralPath) shape).lineTo(58.402, 112.002);
        ((GeneralPath) shape).lineTo(58.402, 113.244);
        ((GeneralPath) shape).lineTo(53.055, 113.244);
        ((GeneralPath) shape).lineTo(53.055, 105.465);
        ((GeneralPath) shape).lineTo(58.368, 105.465);
        ((GeneralPath) shape).lineTo(58.368, 106.708);
        ((GeneralPath) shape).lineTo(54.528, 106.708);
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
        ((GeneralPath) shape).moveTo(66.086, 105.465);
        ((GeneralPath) shape).lineTo(66.086, 113.244);
        ((GeneralPath) shape).lineTo(64.613, 113.244);
        ((GeneralPath) shape).lineTo(64.613, 109.882);
        ((GeneralPath) shape).lineTo(60.973, 109.882);
        ((GeneralPath) shape).lineTo(60.973, 113.244);
        ((GeneralPath) shape).lineTo(59.5, 113.244);
        ((GeneralPath) shape).lineTo(59.5, 105.465);
        ((GeneralPath) shape).lineTo(60.973, 105.465);
        ((GeneralPath) shape).lineTo(60.973, 108.64);
        ((GeneralPath) shape).lineTo(64.613, 108.64);
        ((GeneralPath) shape).lineTo(64.613, 105.465);
        ((GeneralPath) shape).lineTo(66.086, 105.465);
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
        shape = new Rectangle2D.Double(67.45600128173828, 105.46499633789062,
                1.4730000495910645, 7.7789998054504395);
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
        ((GeneralPath) shape).moveTo(75.105, 110.469);
        ((GeneralPath) shape).lineTo(76.584, 110.469);
        ((GeneralPath) shape).lineTo(76.584, 110.737);
        ((GeneralPath) shape).curveTo(76.584, 111.82, 76.387, 112.518, 75.992,
                112.837);
        ((GeneralPath) shape).curveTo(75.599, 113.153, 74.726, 113.312996,
                73.375, 113.312996);
        ((GeneralPath) shape).curveTo(71.845, 113.312996, 70.901, 113.062996,
                70.548, 112.561);
        ((GeneralPath) shape).curveTo(70.195, 112.06, 70.019, 110.71999,
                70.019, 108.537994);
        ((GeneralPath) shape).curveTo(70.019, 107.256, 70.257996, 106.409,
                70.736, 106.005);
        ((GeneralPath) shape).curveTo(71.214, 105.6, 72.212, 105.398994,
                73.734, 105.398994);
        ((GeneralPath) shape).curveTo(74.840004, 105.398994, 75.58, 105.563995,
                75.952, 105.896996);
        ((GeneralPath) shape).curveTo(76.32201, 106.228, 76.51, 106.88899,
                76.51, 107.877);
        ((GeneralPath) shape).lineTo(76.516, 108.054);
        ((GeneralPath) shape).lineTo(75.037, 108.054);
        ((GeneralPath) shape).lineTo(75.037, 107.854004);
        ((GeneralPath) shape).curveTo(75.037, 107.347, 74.943, 107.020004,
                74.75, 106.87701);
        ((GeneralPath) shape).curveTo(74.56, 106.73401, 74.122, 106.66301,
                73.439, 106.66301);
        ((GeneralPath) shape).curveTo(72.526, 106.66301, 71.977005, 106.77401,
                71.793, 106.99901);
        ((GeneralPath) shape).curveTo(71.611, 107.22101, 71.517, 107.88801,
                71.517, 108.99401);
        ((GeneralPath) shape).curveTo(71.517, 110.48101, 71.6, 111.36501,
                71.765, 111.63801);
        ((GeneralPath) shape).curveTo(71.93, 111.91101, 72.462, 112.04801,
                73.366, 112.04801);
        ((GeneralPath) shape).curveTo(74.097, 112.04801, 74.572, 111.974014,
                74.791, 111.820015);
        ((GeneralPath) shape).curveTo(75.007, 111.669014, 75.118004, 111.33601,
                75.118004, 110.81702);
        ((GeneralPath) shape).lineTo(75.105, 110.469);
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
        ((GeneralPath) shape).moveTo(78.982, 105.465);
        ((GeneralPath) shape).lineTo(78.982, 111.922);
        ((GeneralPath) shape).lineTo(82.623, 111.922);
        ((GeneralPath) shape).lineTo(82.623, 113.244);
        ((GeneralPath) shape).lineTo(77.509, 113.244);
        ((GeneralPath) shape).lineTo(77.509, 105.465);
        ((GeneralPath) shape).lineTo(78.982, 105.465);
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
        ((GeneralPath) shape).moveTo(84.688, 106.708);
        ((GeneralPath) shape).lineTo(84.688, 108.713);
        ((GeneralPath) shape).lineTo(88.328, 108.713);
        ((GeneralPath) shape).lineTo(88.328, 109.802);
        ((GeneralPath) shape).lineTo(84.688, 109.802);
        ((GeneralPath) shape).lineTo(84.688, 112.002);
        ((GeneralPath) shape).lineTo(88.562, 112.002);
        ((GeneralPath) shape).lineTo(88.562, 113.244);
        ((GeneralPath) shape).lineTo(83.214, 113.244);
        ((GeneralPath) shape).lineTo(83.214, 105.465);
        ((GeneralPath) shape).lineTo(88.527, 105.465);
        ((GeneralPath) shape).lineTo(88.527, 106.708);
        ((GeneralPath) shape).lineTo(84.688, 106.708);
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
        ((GeneralPath) shape).moveTo(94.044, 112.002);
        ((GeneralPath) shape).lineTo(96.143, 112.002);
        ((GeneralPath) shape).curveTo(96.849, 112.002, 97.304, 111.84, 97.511,
                111.515);
        ((GeneralPath) shape).curveTo(97.716, 111.19, 97.821, 110.475, 97.821,
                109.364);
        ((GeneralPath) shape).curveTo(97.821, 108.217995, 97.731, 107.489,
                97.543, 107.175995);
        ((GeneralPath) shape).curveTo(97.358, 106.865, 96.923, 106.70899,
                96.235, 106.70899);
        ((GeneralPath) shape).lineTo(94.045, 106.70899);
        ((GeneralPath) shape).lineTo(94.045, 112.002);
        ((GeneralPath) shape).moveTo(92.571, 113.244);
        ((GeneralPath) shape).lineTo(92.571, 105.465004);
        ((GeneralPath) shape).lineTo(96.387, 105.465004);
        ((GeneralPath) shape).curveTo(97.47, 105.465004, 98.23, 105.701004,
                98.665, 106.177);
        ((GeneralPath) shape).curveTo(99.097, 106.65, 99.316, 107.482, 99.316,
                108.673004);
        ((GeneralPath) shape).curveTo(99.316, 110.61301, 99.143005, 111.86101,
                98.793, 112.414);
        ((GeneralPath) shape).curveTo(98.446, 112.967, 97.658, 113.243004,
                96.433, 113.243004);
        ((GeneralPath) shape).lineTo(92.571, 113.243004);
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
        ((GeneralPath) shape).moveTo(104.744, 110.663);
        ((GeneralPath) shape).lineTo(103.402, 106.611);
        ((GeneralPath) shape).lineTo(102.082, 110.663);
        ((GeneralPath) shape).lineTo(104.744, 110.663);
        ((GeneralPath) shape).moveTo(105.085, 111.751);
        ((GeneralPath) shape).lineTo(101.734, 111.751);
        ((GeneralPath) shape).lineTo(101.251, 113.244);
        ((GeneralPath) shape).lineTo(99.692, 113.244);
        ((GeneralPath) shape).lineTo(102.275, 105.465004);
        ((GeneralPath) shape).lineTo(104.488, 105.465004);
        ((GeneralPath) shape).lineTo(107.11, 113.244);
        ((GeneralPath) shape).lineTo(105.58, 113.244);
        ((GeneralPath) shape).lineTo(105.085, 111.751);
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
        ((GeneralPath) shape).moveTo(110.878, 106.787);
        ((GeneralPath) shape).lineTo(110.878, 113.244);
        ((GeneralPath) shape).lineTo(109.406, 113.244);
        ((GeneralPath) shape).lineTo(109.406, 106.787);
        ((GeneralPath) shape).lineTo(107.164, 106.787);
        ((GeneralPath) shape).lineTo(107.164, 105.465);
        ((GeneralPath) shape).lineTo(113.199, 105.465);
        ((GeneralPath) shape).lineTo(113.199, 106.787);
        ((GeneralPath) shape).lineTo(110.878, 106.787);
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
        ((GeneralPath) shape).moveTo(118.304, 110.663);
        ((GeneralPath) shape).lineTo(116.962, 106.611);
        ((GeneralPath) shape).lineTo(115.642, 110.663);
        ((GeneralPath) shape).lineTo(118.304, 110.663);
        ((GeneralPath) shape).moveTo(118.645, 111.751);
        ((GeneralPath) shape).lineTo(115.294, 111.751);
        ((GeneralPath) shape).lineTo(114.811, 113.244);
        ((GeneralPath) shape).lineTo(113.252, 113.244);
        ((GeneralPath) shape).lineTo(115.835, 105.465004);
        ((GeneralPath) shape).lineTo(118.048, 105.465004);
        ((GeneralPath) shape).lineTo(120.67, 113.244);
        ((GeneralPath) shape).lineTo(119.14, 113.244);
        ((GeneralPath) shape).lineTo(118.645, 111.751);
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
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(265.873, 110.469);
        ((GeneralPath) shape).lineTo(267.352, 110.469);
        ((GeneralPath) shape).lineTo(267.352, 110.737);
        ((GeneralPath) shape).curveTo(267.352, 111.82, 267.155, 112.518,
                266.75998, 112.837);
        ((GeneralPath) shape).curveTo(266.36697, 113.153, 265.494, 113.312996,
                264.14297, 113.312996);
        ((GeneralPath) shape).curveTo(262.61298, 113.312996, 261.66898,
                113.062996, 261.31598, 112.561);
        ((GeneralPath) shape).curveTo(260.96298, 112.059, 260.787, 110.71999,
                260.787, 108.537994);
        ((GeneralPath) shape).curveTo(260.787, 107.256, 261.026, 106.409,
                261.504, 106.005);
        ((GeneralPath) shape).curveTo(261.981, 105.6, 262.98, 105.398994,
                264.501, 105.398994);
        ((GeneralPath) shape).curveTo(265.607, 105.398994, 266.34702,
                105.563995, 266.719, 105.896996);
        ((GeneralPath) shape).curveTo(267.089, 106.228, 267.277, 106.88899,
                267.277, 107.877);
        ((GeneralPath) shape).lineTo(267.28302, 108.054);
        ((GeneralPath) shape).lineTo(265.80402, 108.054);
        ((GeneralPath) shape).lineTo(265.80402, 107.854004);
        ((GeneralPath) shape).curveTo(265.80402, 107.347, 265.71002,
                107.020004, 265.51703, 106.87701);
        ((GeneralPath) shape).curveTo(265.32703, 106.73401, 264.88904,
                106.66301, 264.20602, 106.66301);
        ((GeneralPath) shape).curveTo(263.29303, 106.66301, 262.74402,
                106.77401, 262.55902, 106.99901);
        ((GeneralPath) shape).curveTo(262.377, 107.22101, 262.28302, 107.88801,
                262.28302, 108.99401);
        ((GeneralPath) shape).curveTo(262.28302, 110.48101, 262.36603,
                111.36501, 262.531, 111.63801);
        ((GeneralPath) shape).curveTo(262.696, 111.91101, 263.228, 112.04801,
                264.133, 112.04801);
        ((GeneralPath) shape).curveTo(264.863, 112.04801, 265.33798,
                111.974014, 265.557, 111.820015);
        ((GeneralPath) shape).curveTo(265.773, 111.669014, 265.884, 111.33601,
                265.884, 110.81702);
        ((GeneralPath) shape).lineTo(265.873, 110.469);
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
        ((GeneralPath) shape).moveTo(269.75, 109.38);
        ((GeneralPath) shape).lineTo(271.865, 109.38);
        ((GeneralPath) shape).curveTo(272.369, 109.38, 272.70398, 109.292,
                272.869, 109.10899);
        ((GeneralPath) shape).curveTo(273.034, 108.92899, 273.116, 108.56699,
                273.116, 108.023994);
        ((GeneralPath) shape).curveTo(273.116, 107.47099, 273.04498, 107.11199,
                272.90298, 106.95);
        ((GeneralPath) shape).curveTo(272.75998, 106.78999, 272.451, 106.707,
                271.96698, 106.707);
        ((GeneralPath) shape).lineTo(269.749, 106.707);
        ((GeneralPath) shape).lineTo(269.749, 109.38);
        ((GeneralPath) shape).moveTo(268.276, 113.244);
        ((GeneralPath) shape).lineTo(268.276, 105.465004);
        ((GeneralPath) shape).lineTo(272.104, 105.465004);
        ((GeneralPath) shape).curveTo(273.05402, 105.465004, 273.711,
                105.631004, 274.072, 105.961006);
        ((GeneralPath) shape).curveTo(274.43, 106.29201, 274.61298, 106.88901,
                274.61298, 107.755005);
        ((GeneralPath) shape).curveTo(274.61298, 108.54201, 274.52496, 109.077,
                274.343, 109.368004);
        ((GeneralPath) shape).curveTo(274.164, 109.656006, 273.79398, 109.855,
                273.237, 109.966);
        ((GeneralPath) shape).lineTo(273.237, 110.017006);
        ((GeneralPath) shape).curveTo(274.096, 110.06801, 274.529, 110.573006,
                274.529, 111.52701);
        ((GeneralPath) shape).lineTo(274.529, 113.242004);
        ((GeneralPath) shape).lineTo(273.055, 113.242004);
        ((GeneralPath) shape).lineTo(273.055, 111.824005);
        ((GeneralPath) shape).curveTo(273.055, 111.023, 272.663, 110.621,
                271.87198, 110.621);
        ((GeneralPath) shape).lineTo(269.75098, 110.621);
        ((GeneralPath) shape).lineTo(269.75098, 113.242004);
        ((GeneralPath) shape).lineTo(268.276, 113.242004);
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
        ((GeneralPath) shape).moveTo(277.098, 106.708);
        ((GeneralPath) shape).lineTo(277.098, 108.713);
        ((GeneralPath) shape).lineTo(280.738, 108.713);
        ((GeneralPath) shape).lineTo(280.738, 109.802);
        ((GeneralPath) shape).lineTo(277.098, 109.802);
        ((GeneralPath) shape).lineTo(277.098, 112.002);
        ((GeneralPath) shape).lineTo(280.972, 112.002);
        ((GeneralPath) shape).lineTo(280.972, 113.244);
        ((GeneralPath) shape).lineTo(275.625, 113.244);
        ((GeneralPath) shape).lineTo(275.625, 105.465);
        ((GeneralPath) shape).lineTo(280.938, 105.465);
        ((GeneralPath) shape).lineTo(280.938, 106.708);
        ((GeneralPath) shape).lineTo(277.098, 106.708);
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
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(292.984, 105.465);
        ((GeneralPath) shape).lineTo(290.90802, 113.243996);
        ((GeneralPath) shape).lineTo(288.72903, 113.243996);
        ((GeneralPath) shape).lineTo(287.64905, 109.255);
        ((GeneralPath) shape).curveTo(287.55206, 108.895996, 287.43506,
                108.411995, 287.30106, 107.808);
        ((GeneralPath) shape).lineTo(287.19305, 107.329);
        ((GeneralPath) shape).lineTo(287.14206, 107.329);
        ((GeneralPath) shape).lineTo(287.02805, 107.813);
        ((GeneralPath) shape).lineTo(286.92004, 108.292);
        ((GeneralPath) shape).curveTo(286.84006, 108.614, 286.75504, 108.936,
                286.66403, 109.26);
        ((GeneralPath) shape).lineTo(285.55502, 113.243004);
        ((GeneralPath) shape).lineTo(283.4, 113.243004);
        ((GeneralPath) shape).lineTo(281.387, 105.464005);
        ((GeneralPath) shape).lineTo(282.888, 105.464005);
        ((GeneralPath) shape).lineTo(284.009, 109.733);
        ((GeneralPath) shape).curveTo(284.077, 110.006004, 284.16202, 110.383,
                284.265, 110.867004);
        ((GeneralPath) shape).lineTo(284.384, 111.437004);
        ((GeneralPath) shape).lineTo(284.49802, 112.007);
        ((GeneralPath) shape).lineTo(284.549, 112.007);
        ((GeneralPath) shape).curveTo(284.609, 111.757, 284.65402, 111.568,
                284.686, 111.437004);
        ((GeneralPath) shape).lineTo(284.823, 110.873);
        ((GeneralPath) shape).curveTo(284.894, 110.577, 284.993, 110.201004,
                285.124, 109.739);
        ((GeneralPath) shape).lineTo(286.319, 105.465);
        ((GeneralPath) shape).lineTo(288.048, 105.465);
        ((GeneralPath) shape).lineTo(289.214, 109.739);
        ((GeneralPath) shape).curveTo(289.31097, 110.104, 289.408, 110.479996,
                289.504, 110.873);
        ((GeneralPath) shape).lineTo(289.635, 111.437004);
        ((GeneralPath) shape).lineTo(289.771, 112.007);
        ((GeneralPath) shape).lineTo(289.816, 112.007);
        ((GeneralPath) shape).lineTo(289.941, 111.437004);
        ((GeneralPath) shape).lineTo(290.061, 110.867004);
        ((GeneralPath) shape).curveTo(290.157, 110.408005, 290.249, 110.02901,
                290.328, 109.727005);
        ((GeneralPath) shape).lineTo(291.477, 105.464005);
        ((GeneralPath) shape).lineTo(292.984, 105.464005);
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
        ((GeneralPath) shape).moveTo(298.057, 112.002);
        ((GeneralPath) shape).lineTo(300.156, 112.002);
        ((GeneralPath) shape).curveTo(300.862, 112.002, 301.31702, 111.84,
                301.52402, 111.515);
        ((GeneralPath) shape).curveTo(301.729, 111.19, 301.834, 110.475,
                301.834, 109.364);
        ((GeneralPath) shape).curveTo(301.834, 108.217995, 301.74402, 107.489,
                301.556, 107.175995);
        ((GeneralPath) shape).curveTo(301.371, 106.865, 300.936, 106.70899,
                300.248, 106.70899);
        ((GeneralPath) shape).lineTo(298.05798, 106.70899);
        ((GeneralPath) shape).lineTo(298.05798, 112.002);
        ((GeneralPath) shape).moveTo(296.583, 113.244);
        ((GeneralPath) shape).lineTo(296.583, 105.465004);
        ((GeneralPath) shape).lineTo(300.39902, 105.465004);
        ((GeneralPath) shape).curveTo(301.48303, 105.465004, 302.242,
                105.701004, 302.67703, 106.177);
        ((GeneralPath) shape).curveTo(303.10904, 106.65, 303.32803, 107.482,
                303.32803, 108.673004);
        ((GeneralPath) shape).curveTo(303.32803, 110.61301, 303.15503,
                111.86101, 302.80502, 112.414);
        ((GeneralPath) shape).curveTo(302.45804, 112.967, 301.67, 113.243004,
                300.44504, 113.243004);
        ((GeneralPath) shape).lineTo(296.583, 113.243004);
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
        ((GeneralPath) shape).moveTo(308.757, 110.663);
        ((GeneralPath) shape).lineTo(307.414, 106.611);
        ((GeneralPath) shape).lineTo(306.095, 110.663);
        ((GeneralPath) shape).lineTo(308.757, 110.663);
        ((GeneralPath) shape).moveTo(309.098, 111.751);
        ((GeneralPath) shape).lineTo(305.748, 111.751);
        ((GeneralPath) shape).lineTo(305.26498, 113.244);
        ((GeneralPath) shape).lineTo(303.706, 113.244);
        ((GeneralPath) shape).lineTo(306.288, 105.465004);
        ((GeneralPath) shape).lineTo(308.501, 105.465004);
        ((GeneralPath) shape).lineTo(311.12302, 113.244);
        ((GeneralPath) shape).lineTo(309.59302, 113.244);
        ((GeneralPath) shape).lineTo(309.098, 111.751);
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
        ((GeneralPath) shape).moveTo(314.892, 106.787);
        ((GeneralPath) shape).lineTo(314.892, 113.244);
        ((GeneralPath) shape).lineTo(313.418, 113.244);
        ((GeneralPath) shape).lineTo(313.418, 106.787);
        ((GeneralPath) shape).lineTo(311.177, 106.787);
        ((GeneralPath) shape).lineTo(311.177, 105.465);
        ((GeneralPath) shape).lineTo(317.212, 105.465);
        ((GeneralPath) shape).lineTo(317.212, 106.787);
        ((GeneralPath) shape).lineTo(314.892, 106.787);
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
        ((GeneralPath) shape).moveTo(322.316, 110.663);
        ((GeneralPath) shape).lineTo(320.97302, 106.611);
        ((GeneralPath) shape).lineTo(319.65402, 110.663);
        ((GeneralPath) shape).lineTo(322.316, 110.663);
        ((GeneralPath) shape).moveTo(322.657, 111.751);
        ((GeneralPath) shape).lineTo(319.307, 111.751);
        ((GeneralPath) shape).lineTo(318.824, 113.244);
        ((GeneralPath) shape).lineTo(317.265, 113.244);
        ((GeneralPath) shape).lineTo(319.84702, 105.465004);
        ((GeneralPath) shape).lineTo(322.06003, 105.465004);
        ((GeneralPath) shape).lineTo(324.68204, 113.244);
        ((GeneralPath) shape).lineTo(323.15204, 113.244);
        ((GeneralPath) shape).lineTo(322.657, 111.751);
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
        ((GeneralPath) shape).moveTo(261.291, 125.18);
        ((GeneralPath) shape).lineTo(262.27698, 125.18);
        ((GeneralPath) shape).lineTo(262.27698, 125.359);
        ((GeneralPath) shape).curveTo(262.27698, 126.081, 262.14597, 126.546,
                261.882, 126.759);
        ((GeneralPath) shape).curveTo(261.62, 126.97, 261.038, 127.076004,
                260.137, 127.076004);
        ((GeneralPath) shape).curveTo(259.117, 127.076004, 258.488, 126.909004,
                258.253, 126.574005);
        ((GeneralPath) shape).curveTo(258.018, 126.240005, 257.9, 125.34701,
                257.9, 123.892006);
        ((GeneralPath) shape).curveTo(257.9, 123.037, 258.059, 122.47301,
                258.378, 122.204);
        ((GeneralPath) shape).curveTo(258.69598, 121.934006, 259.362, 121.799,
                260.37598, 121.799);
        ((GeneralPath) shape).curveTo(261.11298, 121.799, 261.606, 121.909004,
                261.85498, 122.132);
        ((GeneralPath) shape).curveTo(262.102, 122.352005, 262.227, 122.79301,
                262.227, 123.452);
        ((GeneralPath) shape).lineTo(262.231, 123.57);
        ((GeneralPath) shape).lineTo(261.245, 123.57);
        ((GeneralPath) shape).lineTo(261.245, 123.437);
        ((GeneralPath) shape).curveTo(261.245, 123.099, 261.182, 122.88,
                261.054, 122.784996);
        ((GeneralPath) shape).curveTo(260.92697, 122.689995, 260.63498,
                122.642, 260.18, 122.642);
        ((GeneralPath) shape).curveTo(259.572, 122.642, 259.205, 122.715996,
                259.082, 122.866);
        ((GeneralPath) shape).curveTo(258.96, 123.014, 258.898, 123.459,
                258.898, 124.196);
        ((GeneralPath) shape).curveTo(258.898, 125.187996, 258.953, 125.776,
                259.06302, 125.959);
        ((GeneralPath) shape).curveTo(259.173, 126.141, 259.528, 126.232,
                260.131, 126.232);
        ((GeneralPath) shape).curveTo(260.618, 126.232, 260.93402, 126.183,
                261.08102, 126.08);
        ((GeneralPath) shape).curveTo(261.22504, 125.979004, 261.299, 125.757,
                261.299, 125.412);
        ((GeneralPath) shape).lineTo(261.291, 125.18);
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
        ((GeneralPath) shape).moveTo(262.889, 123.398);
        ((GeneralPath) shape).lineTo(263.75702, 123.398);
        ((GeneralPath) shape).lineTo(263.704, 123.907005);
        ((GeneralPath) shape).lineTo(263.72302, 123.911);
        ((GeneralPath) shape).curveTo(263.93002, 123.531006, 264.256, 123.341,
                264.70203, 123.341);
        ((GeneralPath) shape).curveTo(265.33902, 123.341, 265.65802, 123.744,
                265.65802, 124.549);
        ((GeneralPath) shape).lineTo(265.65802, 124.804);
        ((GeneralPath) shape).lineTo(264.83902, 124.804);
        ((GeneralPath) shape).curveTo(264.84903, 124.705, 264.85403, 124.64,
                264.85403, 124.61);
        ((GeneralPath) shape).curveTo(264.85403, 124.222, 264.70505, 124.028,
                264.40305, 124.028);
        ((GeneralPath) shape).curveTo(263.97504, 124.028, 263.75806, 124.315,
                263.75806, 124.891);
        ((GeneralPath) shape).lineTo(263.75806, 127.03);
        ((GeneralPath) shape).lineTo(262.89005, 127.03);
        ((GeneralPath) shape).lineTo(262.89005, 123.398);
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
        ((GeneralPath) shape).moveTo(268.479, 124.838);
        ((GeneralPath) shape).lineTo(268.475, 124.693);
        ((GeneralPath) shape).curveTo(268.475, 124.404, 268.426, 124.218,
                268.325, 124.13);
        ((GeneralPath) shape).curveTo(268.226, 124.045, 268.009, 124.001,
                267.67502, 124.001);
        ((GeneralPath) shape).curveTo(267.35303, 124.001, 267.14203, 124.052,
                267.044, 124.157);
        ((GeneralPath) shape).curveTo(266.948, 124.259995, 266.898, 124.488,
                266.898, 124.837);
        ((GeneralPath) shape).lineTo(268.479, 124.837);
        ((GeneralPath) shape).moveTo(268.471, 125.868);
        ((GeneralPath) shape).lineTo(269.34302, 125.868);
        ((GeneralPath) shape).lineTo(269.34302, 126.008995);
        ((GeneralPath) shape).curveTo(269.34302, 126.716995, 268.812, 127.072,
                267.751, 127.072);
        ((GeneralPath) shape).curveTo(267.03, 127.072, 266.56, 126.95, 266.336,
                126.704);
        ((GeneralPath) shape).curveTo(266.114, 126.459, 266.003, 125.938,
                266.003, 125.142006);
        ((GeneralPath) shape).curveTo(266.003, 124.435005, 266.11798,
                123.96001, 266.352, 123.717);
        ((GeneralPath) shape).curveTo(266.58298, 123.47401, 267.039,
                123.352005, 267.71298, 123.352005);
        ((GeneralPath) shape).curveTo(268.35898, 123.352005, 268.79398, 123.47,
                269.01297, 123.70701);
        ((GeneralPath) shape).curveTo(269.23297, 123.94201, 269.34296,
                124.408005, 269.34296, 125.10301);
        ((GeneralPath) shape).lineTo(269.34296, 125.36901);
        ((GeneralPath) shape).lineTo(266.88995, 125.36901);
        ((GeneralPath) shape).curveTo(266.88596, 125.44901, 266.88196,
                125.502014, 266.88196, 125.529015);
        ((GeneralPath) shape).curveTo(266.88196, 125.88602, 266.93695,
                126.124016, 267.04697, 126.24301);
        ((GeneralPath) shape).curveTo(267.15695, 126.36101, 267.37497,
                126.42101, 267.70496, 126.42101);
        ((GeneralPath) shape).curveTo(268.02396, 126.42101, 268.22995,
                126.38702, 268.32697, 126.31702);
        ((GeneralPath) shape).curveTo(268.422, 126.25, 268.471, 126.1, 268.471,
                125.868);
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
        ((GeneralPath) shape).moveTo(275.142, 123.398);
        ((GeneralPath) shape).lineTo(274.33, 127.03);
        ((GeneralPath) shape).lineTo(273.04797, 127.03);
        ((GeneralPath) shape).lineTo(272.68396, 125.404);
        ((GeneralPath) shape).curveTo(272.62097, 125.123, 272.57397,
                124.909996, 272.54395, 124.762);
        ((GeneralPath) shape).lineTo(272.47995, 124.443);
        ((GeneralPath) shape).lineTo(272.41495, 124.124);
        ((GeneralPath) shape).lineTo(272.38895, 124.124);
        ((GeneralPath) shape).lineTo(272.32495, 124.443);
        ((GeneralPath) shape).lineTo(272.26096, 124.766);
        ((GeneralPath) shape).curveTo(272.23495, 124.907, 272.18896,
                125.119995, 272.12497, 125.404);
        ((GeneralPath) shape).lineTo(271.76096, 127.03);
        ((GeneralPath) shape).lineTo(270.45996, 127.03);
        ((GeneralPath) shape).lineTo(269.65295, 123.397995);
        ((GeneralPath) shape).lineTo(270.52795, 123.397995);
        ((GeneralPath) shape).lineTo(270.87695, 125.08099);
        ((GeneralPath) shape).curveTo(270.91895, 125.288994, 270.96194,
                125.51599, 271.00595, 125.756996);
        ((GeneralPath) shape).lineTo(271.06696, 126.09499);
        ((GeneralPath) shape).lineTo(271.13095, 126.43299);
        ((GeneralPath) shape).lineTo(271.14597, 126.43299);
        ((GeneralPath) shape).lineTo(271.22195, 126.09499);
        ((GeneralPath) shape).lineTo(271.29395, 125.756996);
        ((GeneralPath) shape).curveTo(271.33194, 125.588, 271.38293, 125.363,
                271.44894, 125.084);
        ((GeneralPath) shape).lineTo(271.84296, 123.397);
        ((GeneralPath) shape).lineTo(272.94995, 123.397);
        ((GeneralPath) shape).lineTo(273.34396, 125.084);
        ((GeneralPath) shape).curveTo(273.41595, 125.399, 273.46896, 125.624,
                273.49997, 125.756996);
        ((GeneralPath) shape).lineTo(273.57196, 126.09499);
        ((GeneralPath) shape).lineTo(273.64395, 126.43299);
        ((GeneralPath) shape).lineTo(273.66296, 126.43299);
        ((GeneralPath) shape).lineTo(273.72797, 126.09499);
        ((GeneralPath) shape).lineTo(273.78897, 125.756996);
        ((GeneralPath) shape).curveTo(273.83096, 125.524994, 273.87598,
                125.298996, 273.92197, 125.08099);
        ((GeneralPath) shape).lineTo(274.27496, 123.397995);
        ((GeneralPath) shape).lineTo(275.142, 123.397995);
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
        ((GeneralPath) shape).moveTo(276.409, 123.398);
        ((GeneralPath) shape).lineTo(276.409, 124.333);
        ((GeneralPath) shape).lineTo(275.541, 124.333);
        ((GeneralPath) shape).lineTo(275.541, 123.398);
        ((GeneralPath) shape).lineTo(276.409, 123.398);
        ((GeneralPath) shape).moveTo(276.409, 126.096);
        ((GeneralPath) shape).lineTo(276.409, 127.031);
        ((GeneralPath) shape).lineTo(275.541, 127.031);
        ((GeneralPath) shape).lineTo(275.541, 126.096);
        ((GeneralPath) shape).lineTo(276.409, 126.096);
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
        ((GeneralPath) shape).moveTo(260.13, 136.674);
        ((GeneralPath) shape).lineTo(262.40802, 136.674);
        ((GeneralPath) shape).lineTo(262.42303, 137.703);
        ((GeneralPath) shape).curveTo(262.42303, 138.452, 262.28302, 138.936,
                262.00003, 139.153);
        ((GeneralPath) shape).curveTo(261.72003, 139.369, 261.09003, 139.478,
                260.11404, 139.478);
        ((GeneralPath) shape).curveTo(259.21805, 139.478, 258.62704, 139.333,
                258.33505, 139.045);
        ((GeneralPath) shape).curveTo(258.04504, 138.756, 257.89905, 138.16699,
                257.89905, 137.278);
        ((GeneralPath) shape).curveTo(257.89905, 136.144, 257.95605, 135.428,
                258.07306, 135.128);
        ((GeneralPath) shape).curveTo(258.21707, 134.763, 258.43506, 134.518,
                258.72906, 134.391);
        ((GeneralPath) shape).curveTo(259.02106, 134.266, 259.52005, 134.201,
                260.22305, 134.201);
        ((GeneralPath) shape).curveTo(261.14206, 134.201, 261.73605, 134.298,
                262.00507, 134.49501);
        ((GeneralPath) shape).curveTo(262.27206, 134.69101, 262.40707, 135.126,
                262.40707, 135.8);
        ((GeneralPath) shape).lineTo(261.41406, 135.8);
        ((GeneralPath) shape).curveTo(261.39706, 135.462, 261.32306, 135.251,
                261.19208, 135.168);
        ((GeneralPath) shape).curveTo(261.06308, 135.086, 260.73508, 135.044,
                260.21207, 135.044);
        ((GeneralPath) shape).curveTo(259.64407, 135.044, 259.28308, 135.11401,
                259.12906, 135.257);
        ((GeneralPath) shape).curveTo(258.97806, 135.397, 258.90005, 135.73201,
                258.90005, 136.256);
        ((GeneralPath) shape).lineTo(258.89606, 136.777);
        ((GeneralPath) shape).lineTo(258.90405, 137.44199);
        ((GeneralPath) shape).curveTo(258.90405, 137.95499, 258.98004,
                138.28299, 259.13205, 138.42398);
        ((GeneralPath) shape).curveTo(259.28406, 138.56497, 259.63406,
                138.63498, 260.18604, 138.63498);
        ((GeneralPath) shape).curveTo(260.72104, 138.63498, 261.06204,
                138.57597, 261.20804, 138.45598);
        ((GeneralPath) shape).curveTo(261.35205, 138.33798, 261.42603,
                138.05698, 261.42603, 137.61198);
        ((GeneralPath) shape).lineTo(261.43002, 137.39998);
        ((GeneralPath) shape).lineTo(260.13004, 137.39998);
        ((GeneralPath) shape).lineTo(260.13004, 136.674);
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
        ((GeneralPath) shape).moveTo(266.303, 135.8);
        ((GeneralPath) shape).lineTo(266.303, 139.432);
        ((GeneralPath) shape).lineTo(265.43402, 139.432);
        ((GeneralPath) shape).lineTo(265.48303, 138.809);
        ((GeneralPath) shape).lineTo(265.46802, 138.80501);
        ((GeneralPath) shape).curveTo(265.30002, 139.24901, 264.91, 139.473,
                264.30002, 139.473);
        ((GeneralPath) shape).curveTo(263.48, 139.473, 263.06802, 139.063,
                263.06802, 138.23901);
        ((GeneralPath) shape).lineTo(263.06802, 135.8);
        ((GeneralPath) shape).lineTo(263.93604, 135.8);
        ((GeneralPath) shape).lineTo(263.93604, 138.03);
        ((GeneralPath) shape).curveTo(263.93604, 138.338, 263.97803, 138.541,
                264.06503, 138.64);
        ((GeneralPath) shape).curveTo(264.15002, 138.737, 264.32904, 138.786,
                264.60004, 138.786);
        ((GeneralPath) shape).curveTo(265.15604, 138.786, 265.43405, 138.452,
                265.43405, 137.78299);
        ((GeneralPath) shape).lineTo(265.43405, 135.8);
        ((GeneralPath) shape).lineTo(266.303, 135.8);
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
        ((GeneralPath) shape).moveTo(267.184, 135.8);
        ((GeneralPath) shape).lineTo(268.04498, 135.8);
        ((GeneralPath) shape).lineTo(268.011, 136.412);
        ((GeneralPath) shape).lineTo(268.03, 136.416);
        ((GeneralPath) shape).curveTo(268.198, 135.979, 268.578, 135.758,
                269.167, 135.758);
        ((GeneralPath) shape).curveTo(270.024, 135.758, 270.452, 136.157,
                270.452, 136.959);
        ((GeneralPath) shape).lineTo(270.452, 139.432);
        ((GeneralPath) shape).lineTo(269.58398, 139.432);
        ((GeneralPath) shape).lineTo(269.58398, 137.10701);
        ((GeneralPath) shape).lineTo(269.56497, 136.852);
        ((GeneralPath) shape).curveTo(269.52496, 136.582, 269.31296, 136.446,
                268.92798, 136.446);
        ((GeneralPath) shape).curveTo(268.34497, 136.446, 268.05197, 136.72299,
                268.05197, 137.278);
        ((GeneralPath) shape).lineTo(268.05197, 139.432);
        ((GeneralPath) shape).lineTo(267.18396, 139.432);
        ((GeneralPath) shape).lineTo(267.18396, 135.8);
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
        ((GeneralPath) shape).moveTo(271.3, 135.8);
        ((GeneralPath) shape).lineTo(272.16098, 135.8);
        ((GeneralPath) shape).lineTo(272.12598, 136.412);
        ((GeneralPath) shape).lineTo(272.14597, 136.416);
        ((GeneralPath) shape).curveTo(272.31396, 135.979, 272.69296, 135.758,
                273.28296, 135.758);
        ((GeneralPath) shape).curveTo(274.13995, 135.758, 274.56796, 136.157,
                274.56796, 136.959);
        ((GeneralPath) shape).lineTo(274.56796, 139.432);
        ((GeneralPath) shape).lineTo(273.7, 139.432);
        ((GeneralPath) shape).lineTo(273.7, 137.10701);
        ((GeneralPath) shape).lineTo(273.681, 136.852);
        ((GeneralPath) shape).curveTo(273.641, 136.582, 273.429, 136.446,
                273.044, 136.446);
        ((GeneralPath) shape).curveTo(272.46, 136.446, 272.168, 136.72299,
                272.168, 137.278);
        ((GeneralPath) shape).lineTo(272.168, 139.432);
        ((GeneralPath) shape).lineTo(271.3, 139.432);
        ((GeneralPath) shape).lineTo(271.3, 135.8);
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
        ((GeneralPath) shape).moveTo(277.759, 137.24);
        ((GeneralPath) shape).lineTo(277.755, 137.095);
        ((GeneralPath) shape).curveTo(277.755, 136.806, 277.706, 136.62,
                277.605, 136.533);
        ((GeneralPath) shape).curveTo(277.506, 136.448, 277.289, 136.404,
                276.95502, 136.404);
        ((GeneralPath) shape).curveTo(276.63202, 136.404, 276.42203, 136.455,
                276.32303, 136.559);
        ((GeneralPath) shape).curveTo(276.22702, 136.662, 276.17703, 136.89,
                276.17703, 137.239);
        ((GeneralPath) shape).lineTo(277.759, 137.239);
        ((GeneralPath) shape).moveTo(277.751, 138.27);
        ((GeneralPath) shape).lineTo(278.62302, 138.27);
        ((GeneralPath) shape).lineTo(278.62302, 138.41);
        ((GeneralPath) shape).curveTo(278.62302, 139.119, 278.092, 139.474,
                277.031, 139.474);
        ((GeneralPath) shape).curveTo(276.31, 139.474, 275.84, 139.352,
                275.616, 139.106);
        ((GeneralPath) shape).curveTo(275.394, 138.86101, 275.283, 138.34,
                275.283, 137.544);
        ((GeneralPath) shape).curveTo(275.283, 136.83801, 275.399, 136.363,
                275.632, 136.12001);
        ((GeneralPath) shape).curveTo(275.86298, 135.87701, 276.319, 135.755,
                276.99298, 135.755);
        ((GeneralPath) shape).curveTo(277.63898, 135.755, 278.07397, 135.873,
                278.29297, 136.11);
        ((GeneralPath) shape).curveTo(278.51297, 136.346, 278.62296, 136.811,
                278.62296, 137.506);
        ((GeneralPath) shape).lineTo(278.62296, 137.772);
        ((GeneralPath) shape).lineTo(276.16995, 137.772);
        ((GeneralPath) shape).curveTo(276.16595, 137.852, 276.16196, 137.905,
                276.16196, 137.932);
        ((GeneralPath) shape).curveTo(276.16196, 138.28801, 276.21695, 138.526,
                276.32697, 138.64601);
        ((GeneralPath) shape).curveTo(276.43695, 138.764, 276.65497, 138.82501,
                276.98495, 138.82501);
        ((GeneralPath) shape).curveTo(277.30396, 138.82501, 277.50995,
                138.79001, 277.60696, 138.72101);
        ((GeneralPath) shape).curveTo(277.702, 138.651, 277.751, 138.501,
                277.751, 138.27);
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
        ((GeneralPath) shape).moveTo(279.367, 135.8);
        ((GeneralPath) shape).lineTo(280.23502, 135.8);
        ((GeneralPath) shape).lineTo(280.182, 136.309);
        ((GeneralPath) shape).lineTo(280.20102, 136.31201);
        ((GeneralPath) shape).curveTo(280.40802, 135.93301, 280.734, 135.74301,
                281.18002, 135.74301);
        ((GeneralPath) shape).curveTo(281.81702, 135.74301, 282.13602, 136.145,
                282.13602, 136.951);
        ((GeneralPath) shape).lineTo(282.13602, 137.205);
        ((GeneralPath) shape).lineTo(281.31702, 137.205);
        ((GeneralPath) shape).curveTo(281.32703, 137.106, 281.33203, 137.041,
                281.33203, 137.01201);
        ((GeneralPath) shape).curveTo(281.33203, 136.62401, 281.18304,
                136.43001, 280.88104, 136.43001);
        ((GeneralPath) shape).curveTo(280.45303, 136.43001, 280.23605,
                136.71701, 280.23605, 137.292);
        ((GeneralPath) shape).lineTo(280.23605, 139.431);
        ((GeneralPath) shape).lineTo(279.36804, 139.431);
        ((GeneralPath) shape).lineTo(279.36804, 135.8);
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
        ((GeneralPath) shape).moveTo(285.598, 135.8);
        ((GeneralPath) shape).lineTo(284.646, 139.66);
        ((GeneralPath) shape).curveTo(284.513, 140.207, 284.346, 140.574,
                284.147, 140.764);
        ((GeneralPath) shape).curveTo(283.95, 140.95201, 283.631, 141.04701,
                283.193, 141.04701);
        ((GeneralPath) shape).curveTo(283.094, 141.04701, 282.992, 141.04301,
                282.88498, 141.031);
        ((GeneralPath) shape).lineTo(282.88498, 140.389);
        ((GeneralPath) shape).curveTo(282.96097, 140.393, 283.024, 140.397,
                283.07498, 140.397);
        ((GeneralPath) shape).curveTo(283.443, 140.397, 283.67798, 140.076,
                283.77597, 139.432);
        ((GeneralPath) shape).lineTo(283.33298, 139.432);
        ((GeneralPath) shape).lineTo(282.14597, 135.8);
        ((GeneralPath) shape).lineTo(283.07898, 135.8);
        ((GeneralPath) shape).lineTo(283.53397, 137.339);
        ((GeneralPath) shape).lineTo(283.76196, 138.11);
        ((GeneralPath) shape).curveTo(283.77396, 138.159, 283.80896, 138.288,
                283.86795, 138.497);
        ((GeneralPath) shape).lineTo(283.97794, 138.881);
        ((GeneralPath) shape).lineTo(283.99695, 138.881);
        ((GeneralPath) shape).lineTo(284.07693, 138.497);
        ((GeneralPath) shape).curveTo(284.11694, 138.29999, 284.14294, 138.17,
                284.15692, 138.11);
        ((GeneralPath) shape).lineTo(284.33493, 137.339);
        ((GeneralPath) shape).lineTo(284.68393, 135.8);
        ((GeneralPath) shape).lineTo(285.598, 135.8);
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
        ((GeneralPath) shape).moveTo(291.801, 135.758);
        ((GeneralPath) shape).lineTo(290.84198, 135.758);
        ((GeneralPath) shape).curveTo(290.83798, 135.711, 290.83398, 135.675,
                290.83398, 135.652);
        ((GeneralPath) shape).curveTo(290.81097, 135.361, 290.74698, 135.17899,
                290.641, 135.103);
        ((GeneralPath) shape).curveTo(290.535, 135.02899, 290.285, 134.991,
                289.891, 134.991);
        ((GeneralPath) shape).curveTo(289.426, 134.991, 289.123, 135.033,
                288.979, 135.12);
        ((GeneralPath) shape).curveTo(288.837, 135.205, 288.765, 135.386,
                288.765, 135.663);
        ((GeneralPath) shape).curveTo(288.765, 135.98999, 288.82202, 136.185,
                288.93903, 136.252);
        ((GeneralPath) shape).curveTo(289.05502, 136.316, 289.44003, 136.368,
                290.09204, 136.40399);
        ((GeneralPath) shape).curveTo(290.86203, 136.446, 291.36005, 136.555,
                291.58804, 136.734);
        ((GeneralPath) shape).curveTo(291.81406, 136.911, 291.92804, 137.27899,
                291.92804, 137.83899);
        ((GeneralPath) shape).curveTo(291.92804, 138.527, 291.79504, 138.97299,
                291.53003, 139.174);
        ((GeneralPath) shape).curveTo(291.265, 139.37599, 290.68002, 139.476,
                289.77502, 139.476);
        ((GeneralPath) shape).curveTo(288.96204, 139.476, 288.42102, 139.377,
                288.15402, 139.18);
        ((GeneralPath) shape).curveTo(287.88803, 138.982, 287.75403, 138.583,
                287.75403, 137.97899);
        ((GeneralPath) shape).lineTo(287.75003, 137.78899);
        ((GeneralPath) shape).lineTo(288.70602, 137.78899);
        ((GeneralPath) shape).lineTo(288.709, 137.89899);
        ((GeneralPath) shape).curveTo(288.709, 138.25998, 288.772, 138.48198,
                288.89902, 138.56398);
        ((GeneralPath) shape).curveTo(289.02402, 138.64398, 289.36902,
                138.68597, 289.93402, 138.68597);
        ((GeneralPath) shape).curveTo(290.37402, 138.68597, 290.65402,
                138.63997, 290.77603, 138.54497);
        ((GeneralPath) shape).curveTo(290.89703, 138.45197, 290.95804,
                138.23596, 290.95804, 137.89597);
        ((GeneralPath) shape).curveTo(290.95804, 137.64597, 290.91205,
                137.47997, 290.81903, 137.39597);
        ((GeneralPath) shape).curveTo(290.72803, 137.31396, 290.52902,
                137.26497, 290.22205, 137.24597);
        ((GeneralPath) shape).lineTo(289.68005, 137.21198);
        ((GeneralPath) shape).curveTo(288.86105, 137.16498, 288.33804,
                137.04997, 288.11105, 136.86998);
        ((GeneralPath) shape).curveTo(287.88306, 136.69098, 287.77005,
                136.30399, 287.77005, 135.71198);
        ((GeneralPath) shape).curveTo(287.77005, 135.10797, 287.90704,
                134.70297, 288.18204, 134.50197);
        ((GeneralPath) shape).curveTo(288.45505, 134.30096, 289.00305,
                134.19997, 289.82605, 134.19997);
        ((GeneralPath) shape).curveTo(290.60306, 134.19997, 291.12805,
                134.29097, 291.39905, 134.47696);
        ((GeneralPath) shape).curveTo(291.66904, 134.66196, 291.80505,
                135.02196, 291.80505, 135.55995);
        ((GeneralPath) shape).lineTo(291.80505, 135.758);
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
        ((GeneralPath) shape).moveTo(293.453, 134.246);
        ((GeneralPath) shape).lineTo(293.453, 137.213);
        ((GeneralPath) shape).lineTo(293.676, 137.213);
        ((GeneralPath) shape).lineTo(294.654, 135.8);
        ((GeneralPath) shape).lineTo(295.663, 135.8);
        ((GeneralPath) shape).lineTo(294.4, 137.51);
        ((GeneralPath) shape).lineTo(295.921, 139.432);
        ((GeneralPath) shape).lineTo(294.844, 139.432);
        ((GeneralPath) shape).lineTo(293.665, 137.821);
        ((GeneralPath) shape).lineTo(293.453, 137.821);
        ((GeneralPath) shape).lineTo(293.453, 139.432);
        ((GeneralPath) shape).lineTo(292.584, 139.432);
        ((GeneralPath) shape).lineTo(292.584, 134.246);
        ((GeneralPath) shape).lineTo(293.453, 134.246);
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
        ((GeneralPath) shape).moveTo(297.18, 135.8);
        ((GeneralPath) shape).lineTo(297.18, 139.432);
        ((GeneralPath) shape).lineTo(296.31198, 139.432);
        ((GeneralPath) shape).lineTo(296.31198, 135.8);
        ((GeneralPath) shape).lineTo(297.18, 135.8);
        ((GeneralPath) shape).moveTo(297.18, 134.246);
        ((GeneralPath) shape).lineTo(297.18, 134.972);
        ((GeneralPath) shape).lineTo(296.31198, 134.972);
        ((GeneralPath) shape).lineTo(296.31198, 134.246);
        ((GeneralPath) shape).lineTo(297.18, 134.246);
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
        shape = new Rectangle2D.Double(298.0360107421875, 134.24600219726562,
                0.8679999709129333, 5.185999870300293);
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
        shape = new Rectangle2D.Double(299.760009765625, 134.24600219726562,
                0.8679999709129333, 5.185999870300293);
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
        ((GeneralPath) shape).moveTo(302.314, 135.8);
        ((GeneralPath) shape).lineTo(302.314, 136.735);
        ((GeneralPath) shape).lineTo(301.44598, 136.735);
        ((GeneralPath) shape).lineTo(301.44598, 135.8);
        ((GeneralPath) shape).lineTo(302.314, 135.8);
        ((GeneralPath) shape).moveTo(302.314, 138.497);
        ((GeneralPath) shape).lineTo(302.314, 139.43199);
        ((GeneralPath) shape).lineTo(301.44598, 139.43199);
        ((GeneralPath) shape).lineTo(301.44598, 138.497);
        ((GeneralPath) shape).lineTo(302.314, 138.497);
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
        ((GeneralPath) shape).moveTo(329.384, 138.604);
        ((GeneralPath) shape).lineTo(330.782, 138.604);
        ((GeneralPath) shape).curveTo(331.25302, 138.604, 331.55502, 138.496,
                331.694, 138.279);
        ((GeneralPath) shape).curveTo(331.831, 138.063, 331.901, 137.58601,
                331.901, 136.845);
        ((GeneralPath) shape).curveTo(331.901, 136.081, 331.84, 135.595,
                331.715, 135.386);
        ((GeneralPath) shape).curveTo(331.591, 135.179, 331.301, 135.074,
                330.843, 135.074);
        ((GeneralPath) shape).lineTo(329.383, 135.074);
        ((GeneralPath) shape).lineTo(329.383, 138.604);
        ((GeneralPath) shape).moveTo(328.401, 139.432);
        ((GeneralPath) shape).lineTo(328.401, 134.246);
        ((GeneralPath) shape).lineTo(330.945, 134.246);
        ((GeneralPath) shape).curveTo(331.668, 134.246, 332.174, 134.404,
                332.46402, 134.72101);
        ((GeneralPath) shape).curveTo(332.75302, 135.03601, 332.89902, 135.591,
                332.89902, 136.38501);
        ((GeneralPath) shape).curveTo(332.89902, 137.67902, 332.78403,
                138.51102, 332.55002, 138.87901);
        ((GeneralPath) shape).curveTo(332.31903, 139.24802, 331.79303, 139.432,
                330.97702, 139.432);
        ((GeneralPath) shape).lineTo(328.401, 139.432);
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
        ((GeneralPath) shape).moveTo(333.607, 135.8);
        ((GeneralPath) shape).lineTo(334.47598, 135.8);
        ((GeneralPath) shape).lineTo(334.422, 136.309);
        ((GeneralPath) shape).lineTo(334.441, 136.31201);
        ((GeneralPath) shape).curveTo(334.648, 135.93301, 334.974, 135.74301,
                335.42, 135.74301);
        ((GeneralPath) shape).curveTo(336.057, 135.74301, 336.376, 136.145,
                336.376, 136.951);
        ((GeneralPath) shape).lineTo(336.376, 137.205);
        ((GeneralPath) shape).lineTo(335.557, 137.205);
        ((GeneralPath) shape).curveTo(335.56702, 137.106, 335.57202, 137.041,
                335.57202, 137.01201);
        ((GeneralPath) shape).curveTo(335.57202, 136.62401, 335.42303,
                136.43001, 335.122, 136.43001);
        ((GeneralPath) shape).curveTo(334.69302, 136.43001, 334.47702,
                136.71701, 334.47702, 137.292);
        ((GeneralPath) shape).lineTo(334.47702, 139.431);
        ((GeneralPath) shape).lineTo(333.60803, 139.431);
        ((GeneralPath) shape).lineTo(333.60803, 135.8);
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
        ((GeneralPath) shape).moveTo(337.723, 135.8);
        ((GeneralPath) shape).lineTo(337.723, 139.432);
        ((GeneralPath) shape).lineTo(336.85498, 139.432);
        ((GeneralPath) shape).lineTo(336.85498, 135.8);
        ((GeneralPath) shape).lineTo(337.723, 135.8);
        ((GeneralPath) shape).moveTo(337.723, 134.246);
        ((GeneralPath) shape).lineTo(337.723, 134.972);
        ((GeneralPath) shape).lineTo(336.85498, 134.972);
        ((GeneralPath) shape).lineTo(336.85498, 134.246);
        ((GeneralPath) shape).lineTo(337.723, 134.246);
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
        ((GeneralPath) shape).moveTo(341.601, 135.8);
        ((GeneralPath) shape).lineTo(340.562, 139.432);
        ((GeneralPath) shape).lineTo(339.20502, 139.432);
        ((GeneralPath) shape).lineTo(338.113, 135.8);
        ((GeneralPath) shape).lineTo(339.034, 135.8);
        ((GeneralPath) shape).lineTo(339.512, 137.475);
        ((GeneralPath) shape).curveTo(339.576, 137.707, 339.637, 137.933,
                339.694, 138.151);
        ((GeneralPath) shape).lineTo(339.782, 138.489);
        ((GeneralPath) shape).lineTo(339.86902, 138.827);
        ((GeneralPath) shape).lineTo(339.889, 138.827);
        ((GeneralPath) shape).lineTo(339.96802, 138.489);
        ((GeneralPath) shape).lineTo(340.048, 138.155);
        ((GeneralPath) shape).curveTo(340.109, 137.903, 340.165, 137.676,
                340.21802, 137.479);
        ((GeneralPath) shape).lineTo(340.665, 135.8);
        ((GeneralPath) shape).lineTo(341.601, 135.8);
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
        ((GeneralPath) shape).moveTo(342.855, 135.8);
        ((GeneralPath) shape).lineTo(342.855, 139.432);
        ((GeneralPath) shape).lineTo(341.987, 139.432);
        ((GeneralPath) shape).lineTo(341.987, 135.8);
        ((GeneralPath) shape).lineTo(342.855, 135.8);
        ((GeneralPath) shape).moveTo(342.855, 134.246);
        ((GeneralPath) shape).lineTo(342.855, 134.972);
        ((GeneralPath) shape).lineTo(341.987, 134.972);
        ((GeneralPath) shape).lineTo(341.987, 134.246);
        ((GeneralPath) shape).lineTo(342.855, 134.246);
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
        ((GeneralPath) shape).moveTo(343.711, 135.8);
        ((GeneralPath) shape).lineTo(344.572, 135.8);
        ((GeneralPath) shape).lineTo(344.537, 136.412);
        ((GeneralPath) shape).lineTo(344.55698, 136.416);
        ((GeneralPath) shape).curveTo(344.72598, 135.979, 345.10498, 135.758,
                345.69498, 135.758);
        ((GeneralPath) shape).curveTo(346.55096, 135.758, 346.97998, 136.157,
                346.97998, 136.959);
        ((GeneralPath) shape).lineTo(346.97998, 139.432);
        ((GeneralPath) shape).lineTo(346.11197, 139.432);
        ((GeneralPath) shape).lineTo(346.11197, 137.10701);
        ((GeneralPath) shape).lineTo(346.09198, 136.852);
        ((GeneralPath) shape).curveTo(346.05298, 136.582, 345.83997, 136.446,
                345.455, 136.446);
        ((GeneralPath) shape).curveTo(344.87097, 136.446, 344.57898, 136.72299,
                344.57898, 137.278);
        ((GeneralPath) shape).lineTo(344.57898, 139.432);
        ((GeneralPath) shape).lineTo(343.71097, 139.432);
        ((GeneralPath) shape).lineTo(343.71097, 135.8);
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
        ((GeneralPath) shape).moveTo(349.358, 136.446);
        ((GeneralPath) shape).curveTo(349.025, 136.446, 348.81, 136.512,
                348.715, 136.649);
        ((GeneralPath) shape).curveTo(348.62, 136.784, 348.572, 137.092,
                348.572, 137.574);
        ((GeneralPath) shape).curveTo(348.572, 138.085, 348.62, 138.412,
                348.715, 138.554);
        ((GeneralPath) shape).curveTo(348.81, 138.695, 349.02798, 138.767,
                349.369, 138.767);
        ((GeneralPath) shape).curveTo(349.71, 138.767, 349.93, 138.69499,
                350.03198, 138.549);
        ((GeneralPath) shape).curveTo(350.133, 138.40399, 350.184, 138.081,
                350.184, 137.582);
        ((GeneralPath) shape).curveTo(350.184, 137.103, 350.134, 136.796,
                350.03198, 136.655);
        ((GeneralPath) shape).curveTo(349.933, 136.516, 349.707, 136.446,
                349.358, 136.446);
        ((GeneralPath) shape).moveTo(351.068, 135.8);
        ((GeneralPath) shape).lineTo(351.068, 139.501);
        ((GeneralPath) shape).curveTo(351.068, 140.093, 350.949, 140.5, 350.71,
                140.72);
        ((GeneralPath) shape).curveTo(350.472, 140.941, 350.031, 141.051,
                349.38898, 141.051);
        ((GeneralPath) shape).curveTo(348.76797, 141.051, 348.352, 140.968,
                348.14197, 140.801);
        ((GeneralPath) shape).curveTo(347.93295, 140.63399, 347.82797, 140.303,
                347.82797, 139.80899);
        ((GeneralPath) shape).lineTo(348.66898, 139.80899);
        ((GeneralPath) shape).curveTo(348.66898, 140.04399, 348.71298,
                140.19398, 348.80197, 140.26299);
        ((GeneralPath) shape).curveTo(348.88998, 140.32898, 349.08597,
                140.36398, 349.39398, 140.36398);
        ((GeneralPath) shape).curveTo(349.93198, 140.36398, 350.202, 140.14198,
                350.202, 139.69498);
        ((GeneralPath) shape).lineTo(350.202, 138.87898);
        ((GeneralPath) shape).lineTo(350.18298, 138.87498);
        ((GeneralPath) shape).curveTo(350.029, 139.26198, 349.67398, 139.45598,
                349.12097, 139.45598);
        ((GeneralPath) shape).curveTo(348.57297, 139.45598, 348.19696,
                139.32298, 347.99597, 139.05698);
        ((GeneralPath) shape).curveTo(347.79498, 138.79097, 347.69498,
                138.29498, 347.69498, 137.56798);
        ((GeneralPath) shape).curveTo(347.69498, 136.88397, 347.79498,
                136.41298, 347.99597, 136.15097);
        ((GeneralPath) shape).curveTo(348.19696, 135.89098, 348.56097,
                135.75897, 349.08997, 135.75897);
        ((GeneralPath) shape).curveTo(349.66696, 135.75897, 350.04398,
                135.97197, 350.22498, 136.40198);
        ((GeneralPath) shape).lineTo(350.244, 136.40198);
        ((GeneralPath) shape).lineTo(350.2, 135.8);
        ((GeneralPath) shape).lineTo(351.068, 135.8);
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
        ((GeneralPath) shape).moveTo(357.729, 135.758);
        ((GeneralPath) shape).lineTo(356.769, 135.758);
        ((GeneralPath) shape).curveTo(356.765, 135.711, 356.76102, 135.675,
                356.76102, 135.652);
        ((GeneralPath) shape).curveTo(356.739, 135.361, 356.674, 135.17899,
                356.56903, 135.103);
        ((GeneralPath) shape).curveTo(356.46304, 135.02899, 356.21204, 134.991,
                355.81802, 134.991);
        ((GeneralPath) shape).curveTo(355.35303, 134.991, 355.049, 135.033,
                354.90604, 135.12);
        ((GeneralPath) shape).curveTo(354.76303, 135.205, 354.69104, 135.386,
                354.69104, 135.663);
        ((GeneralPath) shape).curveTo(354.69104, 135.98999, 354.74905, 136.185,
                354.86603, 136.252);
        ((GeneralPath) shape).curveTo(354.98102, 136.316, 355.36703, 136.368,
                356.01804, 136.40399);
        ((GeneralPath) shape).curveTo(356.78903, 136.446, 357.28705, 136.555,
                357.51404, 136.734);
        ((GeneralPath) shape).curveTo(357.74005, 136.911, 357.85403, 137.27899,
                357.85403, 137.83899);
        ((GeneralPath) shape).curveTo(357.85403, 138.527, 357.72104, 138.97299,
                357.45602, 139.174);
        ((GeneralPath) shape).curveTo(357.19003, 139.37599, 356.60602, 139.476,
                355.7, 139.476);
        ((GeneralPath) shape).curveTo(354.88702, 139.476, 354.346, 139.377,
                354.079, 139.18);
        ((GeneralPath) shape).curveTo(353.814, 138.982, 353.68002, 138.583,
                353.68002, 137.97899);
        ((GeneralPath) shape).lineTo(353.67603, 137.78899);
        ((GeneralPath) shape).lineTo(354.631, 137.78899);
        ((GeneralPath) shape).lineTo(354.635, 137.89899);
        ((GeneralPath) shape).curveTo(354.635, 138.25998, 354.698, 138.48198,
                354.824, 138.56398);
        ((GeneralPath) shape).curveTo(354.949, 138.64398, 355.295, 138.68597,
                355.859, 138.68597);
        ((GeneralPath) shape).curveTo(356.299, 138.68597, 356.58002, 138.63997,
                356.70102, 138.54497);
        ((GeneralPath) shape).curveTo(356.82303, 138.45197, 356.88403,
                138.23596, 356.88403, 137.89597);
        ((GeneralPath) shape).curveTo(356.88403, 137.64597, 356.83804,
                137.47997, 356.74503, 137.39597);
        ((GeneralPath) shape).curveTo(356.65402, 137.31396, 356.45502,
                137.26497, 356.14703, 137.24597);
        ((GeneralPath) shape).lineTo(355.60504, 137.21198);
        ((GeneralPath) shape).curveTo(354.78604, 137.16498, 354.26303,
                137.04997, 354.03604, 136.86998);
        ((GeneralPath) shape).curveTo(353.80804, 136.69098, 353.69403,
                136.30399, 353.69403, 135.71198);
        ((GeneralPath) shape).curveTo(353.69403, 135.10797, 353.83102,
                134.70297, 354.10602, 134.50197);
        ((GeneralPath) shape).curveTo(354.37802, 134.30096, 354.92603,
                134.19997, 355.75003, 134.19997);
        ((GeneralPath) shape).curveTo(356.52704, 134.19997, 357.05203,
                134.29097, 357.32303, 134.47696);
        ((GeneralPath) shape).curveTo(357.59302, 134.66196, 357.72903,
                135.02196, 357.72903, 135.55995);
        ((GeneralPath) shape).lineTo(357.72903, 135.758);
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
        ((GeneralPath) shape).moveTo(359.38, 134.246);
        ((GeneralPath) shape).lineTo(359.38, 137.213);
        ((GeneralPath) shape).lineTo(359.604, 137.213);
        ((GeneralPath) shape).lineTo(360.582, 135.8);
        ((GeneralPath) shape).lineTo(361.591, 135.8);
        ((GeneralPath) shape).lineTo(360.328, 137.51);
        ((GeneralPath) shape).lineTo(361.849, 139.432);
        ((GeneralPath) shape).lineTo(360.771, 139.432);
        ((GeneralPath) shape).lineTo(359.593, 137.821);
        ((GeneralPath) shape).lineTo(359.38, 137.821);
        ((GeneralPath) shape).lineTo(359.38, 139.432);
        ((GeneralPath) shape).lineTo(358.512, 139.432);
        ((GeneralPath) shape).lineTo(358.512, 134.246);
        ((GeneralPath) shape).lineTo(359.38, 134.246);
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
        ((GeneralPath) shape).moveTo(363.107, 135.8);
        ((GeneralPath) shape).lineTo(363.107, 139.432);
        ((GeneralPath) shape).lineTo(362.23898, 139.432);
        ((GeneralPath) shape).lineTo(362.23898, 135.8);
        ((GeneralPath) shape).lineTo(363.107, 135.8);
        ((GeneralPath) shape).moveTo(363.107, 134.246);
        ((GeneralPath) shape).lineTo(363.107, 134.972);
        ((GeneralPath) shape).lineTo(362.23898, 134.972);
        ((GeneralPath) shape).lineTo(362.23898, 134.246);
        ((GeneralPath) shape).lineTo(363.107, 134.246);
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
        shape = new Rectangle2D.Double(363.9630126953125, 134.24600219726562,
                0.8679999709129333, 5.185999870300293);
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
        shape = new Rectangle2D.Double(365.68798828125, 134.24600219726562,
                0.8679999709129333, 5.185999870300293);
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
        ((GeneralPath) shape).moveTo(368.241, 135.8);
        ((GeneralPath) shape).lineTo(368.241, 136.735);
        ((GeneralPath) shape).lineTo(367.373, 136.735);
        ((GeneralPath) shape).lineTo(367.373, 135.8);
        ((GeneralPath) shape).lineTo(368.241, 135.8);
        ((GeneralPath) shape).moveTo(368.241, 138.497);
        ((GeneralPath) shape).lineTo(368.241, 139.43199);
        ((GeneralPath) shape).lineTo(367.373, 139.43199);
        ((GeneralPath) shape).lineTo(367.373, 138.497);
        ((GeneralPath) shape).lineTo(368.241, 138.497);
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
        ((GeneralPath) shape).moveTo(44.362, 121.649);
        ((GeneralPath) shape).lineTo(44.362, 127.03);
        ((GeneralPath) shape).lineTo(43.135, 127.03);
        ((GeneralPath) shape).lineTo(43.135, 121.649);
        ((GeneralPath) shape).lineTo(41.267, 121.649);
        ((GeneralPath) shape).lineTo(41.267, 120.548);
        ((GeneralPath) shape).lineTo(46.296, 120.548);
        ((GeneralPath) shape).lineTo(46.296, 121.649);
        ((GeneralPath) shape).lineTo(44.362, 121.649);
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
        ((GeneralPath) shape).moveTo(50.616, 122.49);
        ((GeneralPath) shape).lineTo(49.427002, 127.314995);
        ((GeneralPath) shape).curveTo(49.261, 127.99899, 49.052002, 128.457,
                48.803, 128.694);
        ((GeneralPath) shape).curveTo(48.557003, 128.929, 48.158, 129.048,
                47.611, 129.048);
        ((GeneralPath) shape).curveTo(47.487, 129.048, 47.36, 129.043, 47.227,
                129.028);
        ((GeneralPath) shape).lineTo(47.227, 128.226);
        ((GeneralPath) shape).curveTo(47.322002, 128.23, 47.4, 128.235, 47.464,
                128.235);
        ((GeneralPath) shape).curveTo(47.924, 128.235, 48.217, 127.834, 48.341,
                127.029);
        ((GeneralPath) shape).lineTo(47.786, 127.029);
        ((GeneralPath) shape).lineTo(46.302998, 122.489);
        ((GeneralPath) shape).lineTo(47.468998, 122.489);
        ((GeneralPath) shape).lineTo(48.038, 124.411995);
        ((GeneralPath) shape).lineTo(48.322, 125.37599);
        ((GeneralPath) shape).curveTo(48.336, 125.43799, 48.380997, 125.59899,
                48.454998, 125.85999);
        ((GeneralPath) shape).lineTo(48.592, 126.33899);
        ((GeneralPath) shape).lineTo(48.615997, 126.33899);
        ((GeneralPath) shape).lineTo(48.714996, 125.85999);
        ((GeneralPath) shape).curveTo(48.764996, 125.61299, 48.797997, 125.451,
                48.814995, 125.37599);
        ((GeneralPath) shape).lineTo(49.036995, 124.411995);
        ((GeneralPath) shape).lineTo(49.473995, 122.489);
        ((GeneralPath) shape).lineTo(50.616, 122.489);
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
        ((GeneralPath) shape).moveTo(53.246, 123.293);
        ((GeneralPath) shape).curveTo(52.833, 123.293, 52.563, 123.385,
                52.435997, 123.573);
        ((GeneralPath) shape).curveTo(52.309998, 123.757996, 52.246, 124.155,
                52.246, 124.759995);
        ((GeneralPath) shape).curveTo(52.246, 125.34899, 52.312, 125.73899,
                52.449997, 125.93299);
        ((GeneralPath) shape).curveTo(52.584995, 126.12599, 52.862995,
                126.22299, 53.28, 126.22299);
        ((GeneralPath) shape).curveTo(53.701, 126.22299, 53.975998, 126.132996,
                54.105, 125.94799);
        ((GeneralPath) shape).curveTo(54.23, 125.76499, 54.294, 125.37099,
                54.294, 124.76499);
        ((GeneralPath) shape).curveTo(54.294, 124.14499, 54.23, 123.74399,
                54.101997, 123.56299);
        ((GeneralPath) shape).curveTo(53.974, 123.383, 53.689, 123.293, 53.246,
                123.293);
        ((GeneralPath) shape).moveTo(51.109, 122.49);
        ((GeneralPath) shape).lineTo(52.213, 122.49);
        ((GeneralPath) shape).lineTo(52.171, 123.164);
        ((GeneralPath) shape).lineTo(52.194, 123.168);
        ((GeneralPath) shape).curveTo(52.455, 122.679, 52.943, 122.432, 53.659,
                122.432);
        ((GeneralPath) shape).curveTo(54.318, 122.432, 54.773, 122.596, 55.022,
                122.923996);
        ((GeneralPath) shape).curveTo(55.267998, 123.252, 55.394, 123.848,
                55.394, 124.715996);
        ((GeneralPath) shape).curveTo(55.394, 125.620995, 55.271, 126.241,
                55.024002, 126.577995);
        ((GeneralPath) shape).curveTo(54.777, 126.912994, 54.323, 127.08099,
                53.654003, 127.08099);
        ((GeneralPath) shape).curveTo(52.943005, 127.08099, 52.479004,
                126.842995, 52.260002, 126.368996);
        ((GeneralPath) shape).lineTo(52.241, 126.368996);
        ((GeneralPath) shape).lineTo(52.241, 128.976);
        ((GeneralPath) shape).lineTo(51.109, 128.976);
        ((GeneralPath) shape).lineTo(51.109, 122.49);
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
        ((GeneralPath) shape).moveTo(59.232, 124.291);
        ((GeneralPath) shape).lineTo(59.226997, 124.11);
        ((GeneralPath) shape).curveTo(59.226997, 123.75, 59.164997, 123.517,
                59.038998, 123.407);
        ((GeneralPath) shape).curveTo(58.915997, 123.299995, 58.642998,
                123.245995, 58.225998, 123.245995);
        ((GeneralPath) shape).curveTo(57.822998, 123.245995, 57.559, 123.31,
                57.435997, 123.439995);
        ((GeneralPath) shape).curveTo(57.315, 123.56799, 57.253, 123.854,
                57.253, 124.29099);
        ((GeneralPath) shape).lineTo(59.232, 124.29099);
        ((GeneralPath) shape).moveTo(59.222, 125.577);
        ((GeneralPath) shape).lineTo(60.313, 125.577);
        ((GeneralPath) shape).lineTo(60.313, 125.753006);
        ((GeneralPath) shape).curveTo(60.313, 126.63901, 59.649, 127.08301,
                58.322, 127.08301);
        ((GeneralPath) shape).curveTo(57.420998, 127.08301, 56.833, 126.93101,
                56.553997, 126.62301);
        ((GeneralPath) shape).curveTo(56.276997, 126.31601, 56.136997,
                125.66601, 56.136997, 124.671005);
        ((GeneralPath) shape).curveTo(56.136997, 123.788, 56.281998, 123.19401,
                56.572998, 122.89001);
        ((GeneralPath) shape).curveTo(56.863, 122.586006, 57.431, 122.434006,
                58.274998, 122.434006);
        ((GeneralPath) shape).curveTo(59.082996, 122.434006, 59.626, 122.58101,
                59.899998, 122.878006);
        ((GeneralPath) shape).curveTo(60.175, 123.172005, 60.312996,
                123.754005, 60.312996, 124.62301);
        ((GeneralPath) shape).lineTo(60.312996, 124.95601);
        ((GeneralPath) shape).lineTo(57.245995, 124.95601);
        ((GeneralPath) shape).curveTo(57.240993, 125.05601, 57.235996,
                125.12201, 57.235996, 125.156006);
        ((GeneralPath) shape).curveTo(57.235996, 125.602005, 57.304996,
                125.899, 57.442997, 126.049);
        ((GeneralPath) shape).curveTo(57.579998, 126.19601, 57.852997, 126.272,
                58.264996, 126.272);
        ((GeneralPath) shape).curveTo(58.662994, 126.272, 58.921997,
                126.229004, 59.041996, 126.14101);
        ((GeneralPath) shape).curveTo(59.161, 126.054, 59.222, 125.867, 59.222,
                125.577);
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
        ((GeneralPath) shape).moveTo(62.252, 122.49);
        ((GeneralPath) shape).lineTo(62.252, 123.658);
        ((GeneralPath) shape).lineTo(61.167, 123.658);
        ((GeneralPath) shape).lineTo(61.167, 122.49);
        ((GeneralPath) shape).lineTo(62.252, 122.49);
        ((GeneralPath) shape).moveTo(62.252, 125.862);
        ((GeneralPath) shape).lineTo(62.252, 127.03);
        ((GeneralPath) shape).lineTo(61.167, 127.03);
        ((GeneralPath) shape).lineTo(61.167, 125.862);
        ((GeneralPath) shape).lineTo(62.252, 125.862);
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
        ((GeneralPath) shape).moveTo(53.458, 135.994);
        ((GeneralPath) shape).lineTo(53.458, 141.18001);
        ((GeneralPath) shape).lineTo(52.476, 141.18001);
        ((GeneralPath) shape).lineTo(52.476, 138.35301);
        ((GeneralPath) shape).curveTo(52.476, 138.12901, 52.481003, 137.87302,
                52.495003, 137.585);
        ((GeneralPath) shape).lineTo(52.514004, 137.19801);
        ((GeneralPath) shape).lineTo(52.533005, 136.81401);
        ((GeneralPath) shape).lineTo(52.503006, 136.81401);
        ((GeneralPath) shape).lineTo(52.385006, 137.175);
        ((GeneralPath) shape).lineTo(52.271008, 137.536);
        ((GeneralPath) shape).curveTo(52.16501, 137.859, 52.083008, 138.099,
                52.024006, 138.254);
        ((GeneralPath) shape).lineTo(50.887005, 141.179);
        ((GeneralPath) shape).lineTo(49.992004, 141.179);
        ((GeneralPath) shape).lineTo(48.843006, 138.27701);
        ((GeneralPath) shape).curveTo(48.780006, 138.117, 48.697006, 137.878,
                48.593006, 137.559);
        ((GeneralPath) shape).lineTo(48.475006, 137.19801);
        ((GeneralPath) shape).lineTo(48.357006, 136.84102);
        ((GeneralPath) shape).lineTo(48.327007, 136.84102);
        ((GeneralPath) shape).lineTo(48.34601, 137.21703);
        ((GeneralPath) shape).lineTo(48.36501, 137.59703);
        ((GeneralPath) shape).curveTo(48.38001, 137.88904, 48.38801, 138.14203,
                48.38801, 138.35303);
        ((GeneralPath) shape).lineTo(48.38801, 141.18002);
        ((GeneralPath) shape).lineTo(47.40601, 141.18002);
        ((GeneralPath) shape).lineTo(47.40601, 135.99402);
        ((GeneralPath) shape).lineTo(49.00601, 135.99402);
        ((GeneralPath) shape).lineTo(49.931007, 138.39502);
        ((GeneralPath) shape).curveTo(49.994007, 138.56203, 50.077007,
                138.80103, 50.181007, 139.11302);
        ((GeneralPath) shape).lineTo(50.295006, 139.47401);
        ((GeneralPath) shape).lineTo(50.413006, 139.83101);
        ((GeneralPath) shape).lineTo(50.447006, 139.83101);
        ((GeneralPath) shape).lineTo(50.557007, 139.47401);
        ((GeneralPath) shape).lineTo(50.671005, 139.11702);
        ((GeneralPath) shape).curveTo(50.764004, 138.81702, 50.845005,
                138.57703, 50.913006, 138.40302);
        ((GeneralPath) shape).lineTo(51.823006, 135.99402);
        ((GeneralPath) shape).lineTo(53.458, 135.99402);
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
        ((GeneralPath) shape).moveTo(55.923, 138.194);
        ((GeneralPath) shape).curveTo(55.563, 138.194, 55.331, 138.259, 55.233,
                138.39);
        ((GeneralPath) shape).curveTo(55.134003, 138.519, 55.085003, 138.825,
                55.085003, 139.304);
        ((GeneralPath) shape).curveTo(55.085003, 139.851, 55.13, 140.19301,
                55.226, 140.33);
        ((GeneralPath) shape).curveTo(55.319, 140.467, 55.552002, 140.535,
                55.927002, 140.535);
        ((GeneralPath) shape).curveTo(56.287003, 140.535, 56.517002, 140.463,
                56.611, 140.319);
        ((GeneralPath) shape).curveTo(56.706, 140.174, 56.754, 139.829, 56.754,
                139.278);
        ((GeneralPath) shape).curveTo(56.754, 138.815, 56.704002, 138.518,
                56.606003, 138.38899);
        ((GeneralPath) shape).curveTo(56.507, 138.259, 56.279, 138.194, 55.923,
                138.194);
        ((GeneralPath) shape).moveTo(55.931, 137.506);
        ((GeneralPath) shape).curveTo(56.631, 137.506, 57.089, 137.618, 57.307,
                137.842);
        ((GeneralPath) shape).curveTo(57.523, 138.066, 57.633, 138.539, 57.633,
                139.261);
        ((GeneralPath) shape).curveTo(57.633, 140.067, 57.527, 140.594, 57.315,
                140.845);
        ((GeneralPath) shape).curveTo(57.102997, 141.09601, 56.655, 141.22101,
                55.973, 141.22101);
        ((GeneralPath) shape).curveTo(55.234, 141.22101, 54.753998, 141.10501,
                54.534, 140.87201);
        ((GeneralPath) shape).curveTo(54.316, 140.64001, 54.206, 140.12701,
                54.206, 139.33301);
        ((GeneralPath) shape).curveTo(54.206, 138.57101, 54.312, 138.07501,
                54.529003, 137.84702);
        ((GeneralPath) shape).curveTo(54.742, 137.62, 55.21, 137.506, 55.931,
                137.506);
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
        ((GeneralPath) shape).moveTo(61.382, 137.548);
        ((GeneralPath) shape).lineTo(60.343, 141.18001);
        ((GeneralPath) shape).lineTo(58.986, 141.18001);
        ((GeneralPath) shape).lineTo(57.894, 137.548);
        ((GeneralPath) shape).lineTo(58.815002, 137.548);
        ((GeneralPath) shape).lineTo(59.293003, 139.223);
        ((GeneralPath) shape).curveTo(59.357002, 139.455, 59.418003, 139.681,
                59.475002, 139.899);
        ((GeneralPath) shape).lineTo(59.562004, 140.237);
        ((GeneralPath) shape).lineTo(59.649006, 140.575);
        ((GeneralPath) shape).lineTo(59.668007, 140.575);
        ((GeneralPath) shape).lineTo(59.74801, 140.237);
        ((GeneralPath) shape).lineTo(59.82801, 139.903);
        ((GeneralPath) shape).curveTo(59.88901, 139.651, 59.94601, 139.424,
                59.99801, 139.227);
        ((GeneralPath) shape).lineTo(60.44601, 137.548);
        ((GeneralPath) shape).lineTo(61.382, 137.548);
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
        ((GeneralPath) shape).moveTo(64.113, 138.988);
        ((GeneralPath) shape).lineTo(64.109, 138.843);
        ((GeneralPath) shape).curveTo(64.109, 138.554, 64.06, 138.368, 63.959,
                138.28);
        ((GeneralPath) shape).curveTo(63.86, 138.19499, 63.642998, 138.151,
                63.309, 138.151);
        ((GeneralPath) shape).curveTo(62.987, 138.151, 62.775997, 138.202,
                62.677998, 138.307);
        ((GeneralPath) shape).curveTo(62.580997, 138.41, 62.531998, 138.638,
                62.531998, 138.987);
        ((GeneralPath) shape).lineTo(64.113, 138.987);
        ((GeneralPath) shape).moveTo(64.105, 140.018);
        ((GeneralPath) shape).lineTo(64.978004, 140.018);
        ((GeneralPath) shape).lineTo(64.978004, 140.15901);
        ((GeneralPath) shape).curveTo(64.978004, 140.867, 64.44701, 141.22202,
                63.385006, 141.22202);
        ((GeneralPath) shape).curveTo(62.664005, 141.22202, 62.194008,
                141.10002, 61.971004, 140.85402);
        ((GeneralPath) shape).curveTo(61.749004, 140.60902, 61.637005,
                140.08801, 61.637005, 139.29202);
        ((GeneralPath) shape).curveTo(61.637005, 138.58502, 61.753006,
                138.11002, 61.986004, 137.86702);
        ((GeneralPath) shape).curveTo(62.217003, 137.62402, 62.672005,
                137.50201, 63.347004, 137.50201);
        ((GeneralPath) shape).curveTo(63.993004, 137.50201, 64.428, 137.62001,
                64.647, 137.85701);
        ((GeneralPath) shape).curveTo(64.867004, 138.09201, 64.977005,
                138.55801, 64.977005, 139.253);
        ((GeneralPath) shape).lineTo(64.977005, 139.51901);
        ((GeneralPath) shape).lineTo(62.523006, 139.51901);
        ((GeneralPath) shape).curveTo(62.519005, 139.59901, 62.516006,
                139.65201, 62.516006, 139.67902);
        ((GeneralPath) shape).curveTo(62.516006, 140.03601, 62.571007,
                140.27402, 62.681007, 140.39302);
        ((GeneralPath) shape).curveTo(62.791008, 140.51102, 63.009007,
                140.57101, 63.33901, 140.57101);
        ((GeneralPath) shape).curveTo(63.65701, 140.57101, 63.86401, 140.53702,
                63.96101, 140.46701);
        ((GeneralPath) shape).curveTo(64.056, 140.399, 64.105, 140.249, 64.105,
                140.018);
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
        ((GeneralPath) shape).moveTo(65.699, 137.548);
        ((GeneralPath) shape).lineTo(66.56699, 137.548);
        ((GeneralPath) shape).lineTo(66.54399, 138.106);
        ((GeneralPath) shape).lineTo(66.56399, 138.11);
        ((GeneralPath) shape).curveTo(66.73999, 137.70801, 67.108986, 137.506,
                67.66999, 137.506);
        ((GeneralPath) shape).curveTo(68.32399, 137.506, 68.68899, 137.73,
                68.76199, 138.178);
        ((GeneralPath) shape).lineTo(68.77699, 138.178);
        ((GeneralPath) shape).curveTo(68.94599, 137.73, 69.31899, 137.506,
                69.895996, 137.506);
        ((GeneralPath) shape).curveTo(70.73399, 137.506, 71.155, 137.928,
                71.155, 138.775);
        ((GeneralPath) shape).lineTo(71.155, 141.18);
        ((GeneralPath) shape).lineTo(70.285995, 141.18);
        ((GeneralPath) shape).lineTo(70.285995, 138.965);
        ((GeneralPath) shape).curveTo(70.285995, 138.452, 70.076, 138.194,
                69.65299, 138.194);
        ((GeneralPath) shape).curveTo(69.12599, 138.194, 68.86099, 138.481,
                68.86099, 139.057);
        ((GeneralPath) shape).lineTo(68.86099, 141.181);
        ((GeneralPath) shape).lineTo(67.993, 141.181);
        ((GeneralPath) shape).lineTo(67.993, 138.932);
        ((GeneralPath) shape).curveTo(67.993, 138.632, 67.952995, 138.432,
                67.87299, 138.337);
        ((GeneralPath) shape).curveTo(67.79299, 138.242, 67.62499, 138.194,
                67.367, 138.194);
        ((GeneralPath) shape).curveTo(66.834, 138.194, 66.56699, 138.487,
                66.56699, 139.075);
        ((GeneralPath) shape).lineTo(66.56699, 141.18);
        ((GeneralPath) shape).lineTo(65.699, 141.18);
        ((GeneralPath) shape).lineTo(65.699, 137.548);
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
        ((GeneralPath) shape).moveTo(74.387, 138.988);
        ((GeneralPath) shape).lineTo(74.383, 138.843);
        ((GeneralPath) shape).curveTo(74.383, 138.554, 74.334, 138.368, 74.233,
                138.28);
        ((GeneralPath) shape).curveTo(74.134, 138.19499, 73.917, 138.151,
                73.583, 138.151);
        ((GeneralPath) shape).curveTo(73.261, 138.151, 73.05, 138.202, 72.952,
                138.307);
        ((GeneralPath) shape).curveTo(72.855, 138.41, 72.806, 138.638, 72.806,
                138.987);
        ((GeneralPath) shape).lineTo(74.387, 138.987);
        ((GeneralPath) shape).moveTo(74.378, 140.018);
        ((GeneralPath) shape).lineTo(75.251, 140.018);
        ((GeneralPath) shape).lineTo(75.251, 140.15901);
        ((GeneralPath) shape).curveTo(75.251, 140.867, 74.72, 141.22202,
                73.659, 141.22202);
        ((GeneralPath) shape).curveTo(72.937996, 141.22202, 72.467995,
                141.10002, 72.243996, 140.85402);
        ((GeneralPath) shape).curveTo(72.021996, 140.60902, 71.910995,
                140.08801, 71.910995, 139.29202);
        ((GeneralPath) shape).curveTo(71.910995, 138.58502, 72.02599,
                138.11002, 72.259995, 137.86702);
        ((GeneralPath) shape).curveTo(72.491, 137.62402, 72.94699, 137.50201,
                73.620995, 137.50201);
        ((GeneralPath) shape).curveTo(74.267, 137.50201, 74.701996, 137.62001,
                74.921, 137.85701);
        ((GeneralPath) shape).curveTo(75.141, 138.09201, 75.251, 138.55801,
                75.251, 139.253);
        ((GeneralPath) shape).lineTo(75.251, 139.51901);
        ((GeneralPath) shape).lineTo(72.798, 139.51901);
        ((GeneralPath) shape).curveTo(72.794, 139.59901, 72.78999, 139.65201,
                72.78999, 139.67902);
        ((GeneralPath) shape).curveTo(72.78999, 140.03601, 72.84499, 140.27402,
                72.954994, 140.39302);
        ((GeneralPath) shape).curveTo(73.064995, 140.51102, 73.283, 140.57101,
                73.61299, 140.57101);
        ((GeneralPath) shape).curveTo(73.93199, 140.57101, 74.13799, 140.53702,
                74.23499, 140.46701);
        ((GeneralPath) shape).curveTo(74.33, 140.399, 74.378, 140.249, 74.378,
                140.018);
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
        ((GeneralPath) shape).moveTo(75.972, 137.548);
        ((GeneralPath) shape).lineTo(76.833, 137.548);
        ((GeneralPath) shape).lineTo(76.799, 138.159);
        ((GeneralPath) shape).lineTo(76.818, 138.163);
        ((GeneralPath) shape).curveTo(76.986, 137.726, 77.366, 137.506, 77.955,
                137.506);
        ((GeneralPath) shape).curveTo(78.812004, 137.506, 79.240005, 137.905,
                79.240005, 138.707);
        ((GeneralPath) shape).lineTo(79.240005, 141.18001);
        ((GeneralPath) shape).lineTo(78.37201, 141.18001);
        ((GeneralPath) shape).lineTo(78.37201, 138.85501);
        ((GeneralPath) shape).lineTo(78.35201, 138.60101);
        ((GeneralPath) shape).curveTo(78.31201, 138.33101, 78.10001, 138.19402,
                77.71501, 138.19402);
        ((GeneralPath) shape).curveTo(77.13201, 138.19402, 76.83901, 138.47101,
                76.83901, 139.02701);
        ((GeneralPath) shape).lineTo(76.83901, 141.18102);
        ((GeneralPath) shape).lineTo(75.971016, 141.18102);
        ((GeneralPath) shape).lineTo(75.971016, 137.548);
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
        ((GeneralPath) shape).moveTo(82.427, 137.548);
        ((GeneralPath) shape).lineTo(82.427, 138.209);
        ((GeneralPath) shape).lineTo(81.031, 138.209);
        ((GeneralPath) shape).lineTo(81.031, 140.033);
        ((GeneralPath) shape).curveTo(81.031, 140.369, 81.158, 140.53801,
                81.414, 140.53801);
        ((GeneralPath) shape).curveTo(81.695, 140.53801, 81.835, 140.335,
                81.835, 139.92702);
        ((GeneralPath) shape).lineTo(81.835, 139.78201);
        ((GeneralPath) shape).lineTo(82.574, 139.78201);
        ((GeneralPath) shape).lineTo(82.574, 139.96402);
        ((GeneralPath) shape).curveTo(82.574, 140.13103, 82.57, 140.27402,
                82.559, 140.39401);
        ((GeneralPath) shape).curveTo(82.512, 140.949, 82.1, 141.22601, 81.323,
                141.22601);
        ((GeneralPath) shape).curveTo(80.549995, 141.22601, 80.162994,
                140.87102, 80.162994, 140.15901);
        ((GeneralPath) shape).lineTo(80.162994, 138.21);
        ((GeneralPath) shape).lineTo(79.69299, 138.21);
        ((GeneralPath) shape).lineTo(79.69299, 137.54901);
        ((GeneralPath) shape).lineTo(80.162994, 137.54901);
        ((GeneralPath) shape).lineTo(80.162994, 136.73601);
        ((GeneralPath) shape).lineTo(81.03099, 136.73601);
        ((GeneralPath) shape).lineTo(81.03099, 137.54901);
        ((GeneralPath) shape).lineTo(82.427, 137.54901);
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
        ((GeneralPath) shape).moveTo(86.004, 138.677);
        ((GeneralPath) shape).lineTo(87.121994, 138.677);
        ((GeneralPath) shape).curveTo(87.565994, 138.677, 87.84899, 138.626,
                87.96999, 138.523);
        ((GeneralPath) shape).curveTo(88.089, 138.42, 88.149994, 138.179,
                88.149994, 137.795);
        ((GeneralPath) shape).curveTo(88.149994, 137.358, 88.10099, 137.087,
                87.99999, 136.98);
        ((GeneralPath) shape).curveTo(87.90099, 136.87599, 87.64199, 136.82199,
                87.22099, 136.82199);
        ((GeneralPath) shape).lineTo(86.00399, 136.82199);
        ((GeneralPath) shape).lineTo(86.00399, 138.677);
        ((GeneralPath) shape).moveTo(85.022, 141.18);
        ((GeneralPath) shape).lineTo(85.022, 135.99399);
        ((GeneralPath) shape).lineTo(87.365005, 135.99399);
        ((GeneralPath) shape).curveTo(88.065, 135.99399, 88.537, 136.11598,
                88.782005, 136.361);
        ((GeneralPath) shape).curveTo(89.025, 136.60399, 89.148, 137.077,
                89.148, 137.777);
        ((GeneralPath) shape).curveTo(89.148, 138.46999, 89.032005, 138.93199,
                88.799, 139.15999);
        ((GeneralPath) shape).curveTo(88.568, 139.38799, 88.097, 139.50198,
                87.388, 139.50198);
        ((GeneralPath) shape).lineTo(87.16, 139.50598);
        ((GeneralPath) shape).lineTo(86.004005, 139.50598);
        ((GeneralPath) shape).lineTo(86.004005, 141.18098);
        ((GeneralPath) shape).lineTo(85.022, 141.18098);
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
        ((GeneralPath) shape).moveTo(91.333, 138.194);
        ((GeneralPath) shape).curveTo(90.973, 138.194, 90.742, 138.259, 90.643,
                138.39);
        ((GeneralPath) shape).curveTo(90.545, 138.519, 90.494995, 138.825,
                90.494995, 139.304);
        ((GeneralPath) shape).curveTo(90.494995, 139.851, 90.53999, 140.19301,
                90.635994, 140.33);
        ((GeneralPath) shape).curveTo(90.729, 140.467, 90.96199, 140.535,
                91.33699, 140.535);
        ((GeneralPath) shape).curveTo(91.69699, 140.535, 91.92699, 140.463,
                92.02199, 140.319);
        ((GeneralPath) shape).curveTo(92.11699, 140.175, 92.16399, 139.829,
                92.16399, 139.278);
        ((GeneralPath) shape).curveTo(92.16399, 138.815, 92.11498, 138.518,
                92.01598, 138.38899);
        ((GeneralPath) shape).curveTo(91.917, 138.259, 91.689, 138.194, 91.333,
                138.194);
        ((GeneralPath) shape).moveTo(91.34, 137.506);
        ((GeneralPath) shape).curveTo(92.03999, 137.506, 92.49799, 137.618,
                92.715996, 137.842);
        ((GeneralPath) shape).curveTo(92.932, 138.066, 93.04199, 138.539,
                93.04199, 139.261);
        ((GeneralPath) shape).curveTo(93.04199, 140.067, 92.93599, 140.594,
                92.72299, 140.845);
        ((GeneralPath) shape).curveTo(92.510994, 141.09601, 92.06299,
                141.22101, 91.38099, 141.22101);
        ((GeneralPath) shape).curveTo(90.64199, 141.22101, 90.16199, 141.10501,
                89.941986, 140.87201);
        ((GeneralPath) shape).curveTo(89.72398, 140.64001, 89.61398, 140.12701,
                89.61398, 139.33301);
        ((GeneralPath) shape).curveTo(89.61398, 138.57101, 89.719986,
                138.07501, 89.93598, 137.84702);
        ((GeneralPath) shape).curveTo(90.152, 137.62, 90.62, 137.506, 91.34,
                137.506);
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
        ((GeneralPath) shape).moveTo(94.639, 137.548);
        ((GeneralPath) shape).lineTo(94.639, 141.18001);
        ((GeneralPath) shape).lineTo(93.771, 141.18001);
        ((GeneralPath) shape).lineTo(93.771, 137.548);
        ((GeneralPath) shape).lineTo(94.639, 137.548);
        ((GeneralPath) shape).moveTo(94.639, 135.994);
        ((GeneralPath) shape).lineTo(94.639, 136.72);
        ((GeneralPath) shape).lineTo(93.771, 136.72);
        ((GeneralPath) shape).lineTo(93.771, 135.994);
        ((GeneralPath) shape).lineTo(94.639, 135.994);
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
        ((GeneralPath) shape).moveTo(95.494, 137.548);
        ((GeneralPath) shape).lineTo(96.355, 137.548);
        ((GeneralPath) shape).lineTo(96.32101, 138.159);
        ((GeneralPath) shape).lineTo(96.340004, 138.163);
        ((GeneralPath) shape).curveTo(96.508, 137.726, 96.888, 137.506,
                97.477005, 137.506);
        ((GeneralPath) shape).curveTo(98.33401, 137.506, 98.76301, 137.905,
                98.76301, 138.707);
        ((GeneralPath) shape).lineTo(98.76301, 141.18001);
        ((GeneralPath) shape).lineTo(97.89501, 141.18001);
        ((GeneralPath) shape).lineTo(97.89501, 138.85501);
        ((GeneralPath) shape).lineTo(97.875015, 138.60101);
        ((GeneralPath) shape).curveTo(97.835014, 138.33101, 97.62302,
                138.19402, 97.238014, 138.19402);
        ((GeneralPath) shape).curveTo(96.655014, 138.19402, 96.362015,
                138.47101, 96.362015, 139.02701);
        ((GeneralPath) shape).lineTo(96.362015, 141.18102);
        ((GeneralPath) shape).lineTo(95.49402, 141.18102);
        ((GeneralPath) shape).lineTo(95.49402, 137.548);
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
        ((GeneralPath) shape).moveTo(101.949, 137.548);
        ((GeneralPath) shape).lineTo(101.949, 138.209);
        ((GeneralPath) shape).lineTo(100.554, 138.209);
        ((GeneralPath) shape).lineTo(100.554, 140.033);
        ((GeneralPath) shape).curveTo(100.554, 140.369, 100.681, 140.53801,
                100.937004, 140.53801);
        ((GeneralPath) shape).curveTo(101.217, 140.53801, 101.358, 140.335,
                101.358, 139.92702);
        ((GeneralPath) shape).lineTo(101.358, 139.78201);
        ((GeneralPath) shape).lineTo(102.097, 139.78201);
        ((GeneralPath) shape).lineTo(102.097, 139.96402);
        ((GeneralPath) shape).curveTo(102.097, 140.13103, 102.093, 140.27402,
                102.082, 140.39401);
        ((GeneralPath) shape).curveTo(102.035, 140.949, 101.623, 141.22601,
                100.846, 141.22601);
        ((GeneralPath) shape).curveTo(100.073, 141.22601, 99.686, 140.87102,
                99.686, 140.15901);
        ((GeneralPath) shape).lineTo(99.686, 138.21);
        ((GeneralPath) shape).lineTo(99.215996, 138.21);
        ((GeneralPath) shape).lineTo(99.215996, 137.54901);
        ((GeneralPath) shape).lineTo(99.686, 137.54901);
        ((GeneralPath) shape).lineTo(99.686, 136.73601);
        ((GeneralPath) shape).lineTo(100.555, 136.73601);
        ((GeneralPath) shape).lineTo(100.555, 137.54901);
        ((GeneralPath) shape).lineTo(101.949, 137.54901);
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
        ((GeneralPath) shape).moveTo(105.602, 138.547);
        ((GeneralPath) shape).lineTo(104.753, 138.547);
        ((GeneralPath) shape).curveTo(104.749, 138.517, 104.745995, 138.494,
                104.742, 138.479);
        ((GeneralPath) shape).curveTo(104.725, 138.304, 104.675995, 138.196,
                104.591995, 138.15201);
        ((GeneralPath) shape).curveTo(104.509995, 138.11, 104.311, 138.08801,
                103.994995, 138.08801);
        ((GeneralPath) shape).curveTo(103.544, 138.08801, 103.315994,
                138.23401, 103.315994, 138.52802);
        ((GeneralPath) shape).curveTo(103.315994, 138.72702, 103.355995,
                138.84702, 103.436, 138.88501);
        ((GeneralPath) shape).curveTo(103.516, 138.923, 103.784996, 138.953,
                104.245995, 138.97601);
        ((GeneralPath) shape).curveTo(104.86399, 139.00601, 105.267, 139.08801,
                105.454994, 139.223);
        ((GeneralPath) shape).curveTo(105.64099, 139.356, 105.73499, 139.63101,
                105.73499, 140.04701);
        ((GeneralPath) shape).curveTo(105.73499, 140.49, 105.61199, 140.79701,
                105.36199, 140.96802);
        ((GeneralPath) shape).curveTo(105.11299, 141.13902, 104.66599,
                141.22401, 104.02099, 141.22401);
        ((GeneralPath) shape).curveTo(103.40299, 141.22401, 102.97899,
                141.14801, 102.75099, 140.99301);
        ((GeneralPath) shape).curveTo(102.522995, 140.839, 102.40999,
                140.55002, 102.40999, 140.12701);
        ((GeneralPath) shape).lineTo(102.40999, 140.03601);
        ((GeneralPath) shape).lineTo(103.31199, 140.03601);
        ((GeneralPath) shape).curveTo(103.30099, 140.085, 103.29299, 140.12701,
                103.289986, 140.158);
        ((GeneralPath) shape).curveTo(103.25599, 140.479, 103.50198, 140.64,
                104.03298, 140.64);
        ((GeneralPath) shape).curveTo(104.58098, 140.64, 104.85598, 140.481,
                104.85598, 140.161);
        ((GeneralPath) shape).curveTo(104.85598, 139.855, 104.68498, 139.702,
                104.33998, 139.702);
        ((GeneralPath) shape).curveTo(103.56498, 139.702, 103.05198, 139.629,
                102.805984, 139.481);
        ((GeneralPath) shape).curveTo(102.55898, 139.335, 102.43598, 139.029,
                102.43598, 138.565);
        ((GeneralPath) shape).curveTo(102.43598, 138.151, 102.54798, 137.87,
                102.77298, 137.722);
        ((GeneralPath) shape).curveTo(102.99698, 137.576, 103.42898, 137.501,
                104.06998, 137.501);
        ((GeneralPath) shape).curveTo(104.67297, 137.501, 105.07998, 137.57101,
                105.28898, 137.714);
        ((GeneralPath) shape).curveTo(105.498, 137.856, 105.602, 138.133,
                105.602, 138.547);
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
        ((GeneralPath) shape).moveTo(107.25, 137.548);
        ((GeneralPath) shape).lineTo(107.25, 138.483);
        ((GeneralPath) shape).lineTo(106.382, 138.483);
        ((GeneralPath) shape).lineTo(106.382, 137.548);
        ((GeneralPath) shape).lineTo(107.25, 137.548);
        ((GeneralPath) shape).moveTo(107.25, 140.246);
        ((GeneralPath) shape).lineTo(107.25, 141.181);
        ((GeneralPath) shape).lineTo(106.382, 141.181);
        ((GeneralPath) shape).lineTo(106.382, 140.246);
        ((GeneralPath) shape).lineTo(107.25, 140.246);
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
        ((GeneralPath) shape).moveTo(155.341, 147.403);
        ((GeneralPath) shape).lineTo(155.341, 151.708);
        ((GeneralPath) shape).lineTo(154.36, 151.708);
        ((GeneralPath) shape).lineTo(154.36, 147.403);
        ((GeneralPath) shape).lineTo(152.865, 147.403);
        ((GeneralPath) shape).lineTo(152.865, 146.522);
        ((GeneralPath) shape).lineTo(156.889, 146.522);
        ((GeneralPath) shape).lineTo(156.889, 147.403);
        ((GeneralPath) shape).lineTo(155.341, 147.403);
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
        ((GeneralPath) shape).moveTo(159.703, 149.516);
        ((GeneralPath) shape).lineTo(159.699, 149.371);
        ((GeneralPath) shape).curveTo(159.699, 149.082, 159.65001, 148.896,
                159.55, 148.809);
        ((GeneralPath) shape).curveTo(159.451, 148.723, 159.23401, 148.68001,
                158.90001, 148.68001);
        ((GeneralPath) shape).curveTo(158.578, 148.68001, 158.367, 148.731,
                158.26901, 148.835);
        ((GeneralPath) shape).curveTo(158.17201, 148.938, 158.12302, 149.166,
                158.12302, 149.515);
        ((GeneralPath) shape).lineTo(159.703, 149.515);
        ((GeneralPath) shape).moveTo(159.695, 150.546);
        ((GeneralPath) shape).lineTo(160.56801, 150.546);
        ((GeneralPath) shape).lineTo(160.56801, 150.686);
        ((GeneralPath) shape).curveTo(160.56801, 151.395, 160.037, 151.75,
                158.97601, 151.75);
        ((GeneralPath) shape).curveTo(158.25502, 151.75, 157.78502, 151.628,
                157.56102, 151.381);
        ((GeneralPath) shape).curveTo(157.33902, 151.136, 157.22702, 150.61499,
                157.22702, 149.819);
        ((GeneralPath) shape).curveTo(157.22702, 149.112, 157.34302, 148.638,
                157.57602, 148.395);
        ((GeneralPath) shape).curveTo(157.80702, 148.15201, 158.26302, 148.03,
                158.93701, 148.03);
        ((GeneralPath) shape).curveTo(159.58301, 148.03, 160.018, 148.148,
                160.23701, 148.385);
        ((GeneralPath) shape).curveTo(160.45702, 148.62099, 160.56702, 149.086,
                160.56702, 149.78099);
        ((GeneralPath) shape).lineTo(160.56702, 150.047);
        ((GeneralPath) shape).lineTo(158.11401, 150.047);
        ((GeneralPath) shape).curveTo(158.11002, 150.127, 158.10602, 150.18,
                158.10602, 150.207);
        ((GeneralPath) shape).curveTo(158.10602, 150.564, 158.16101, 150.802,
                158.27101, 150.921);
        ((GeneralPath) shape).curveTo(158.38101, 151.039, 158.59901, 151.1,
                158.92902, 151.1);
        ((GeneralPath) shape).curveTo(159.24802, 151.1, 159.45401, 151.065,
                159.55101, 150.996);
        ((GeneralPath) shape).curveTo(159.646, 150.927, 159.695, 150.777,
                159.695, 150.546);
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
        ((GeneralPath) shape).moveTo(163.598, 150.321);
        ((GeneralPath) shape).lineTo(164.46301, 150.321);
        ((GeneralPath) shape).lineTo(164.46301, 150.446);
        ((GeneralPath) shape).lineTo(164.41002, 150.997);
        ((GeneralPath) shape).curveTo(164.30402, 151.499, 163.78603, 151.749,
                162.85503, 151.749);
        ((GeneralPath) shape).curveTo(162.17203, 151.749, 161.71902, 151.625,
                161.49403, 151.379);
        ((GeneralPath) shape).curveTo(161.27003, 151.132, 161.15604, 150.636,
                161.15604, 149.887);
        ((GeneralPath) shape).curveTo(161.15604, 149.15799, 161.26804, 148.668,
                161.49403, 148.413);
        ((GeneralPath) shape).curveTo(161.71704, 148.161, 162.15503, 148.03299,
                162.80603, 148.03299);
        ((GeneralPath) shape).curveTo(163.43103, 148.03299, 163.85603,
                148.12498, 164.08003, 148.30899);
        ((GeneralPath) shape).curveTo(164.30203, 148.491, 164.41302, 148.84099,
                164.41302, 149.355);
        ((GeneralPath) shape).lineTo(163.55203, 149.355);
        ((GeneralPath) shape).curveTo(163.55203, 148.933, 163.30904, 148.721,
                162.82004, 148.721);
        ((GeneralPath) shape).curveTo(162.47504, 148.721, 162.25703, 148.78699,
                162.16805, 148.92299);
        ((GeneralPath) shape).curveTo(162.08104, 149.05598, 162.03604,
                149.38599, 162.03604, 149.90999);
        ((GeneralPath) shape).curveTo(162.03604, 150.415, 162.08304, 150.73499,
                162.18204, 150.86598);
        ((GeneralPath) shape).curveTo(162.27904, 150.99498, 162.51704,
                151.06198, 162.89604, 151.06198);
        ((GeneralPath) shape).curveTo(163.19405, 151.06198, 163.38504,
                151.01797, 163.47104, 150.92699);
        ((GeneralPath) shape).curveTo(163.555, 150.838, 163.598, 150.637,
                163.598, 150.321);
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
        ((GeneralPath) shape).moveTo(165.97, 146.522);
        ((GeneralPath) shape).lineTo(165.97, 148.67601);
        ((GeneralPath) shape).lineTo(165.989, 148.68001);
        ((GeneralPath) shape).curveTo(166.15, 148.25002, 166.522, 148.03401,
                167.103, 148.03401);
        ((GeneralPath) shape).curveTo(167.94899, 148.03401, 168.374, 148.43501,
                168.374, 149.23901);
        ((GeneralPath) shape).lineTo(168.374, 151.70801);
        ((GeneralPath) shape).lineTo(167.50499, 151.70801);
        ((GeneralPath) shape).lineTo(167.50499, 149.36);
        ((GeneralPath) shape).curveTo(167.50499, 148.934, 167.28899, 148.722,
                166.857, 148.722);
        ((GeneralPath) shape).curveTo(166.26599, 148.722, 165.97, 149.03,
                165.97, 149.645);
        ((GeneralPath) shape).lineTo(165.97, 151.70801);
        ((GeneralPath) shape).lineTo(165.102, 151.70801);
        ((GeneralPath) shape).lineTo(165.102, 146.522);
        ((GeneralPath) shape).lineTo(165.97, 146.522);
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
        ((GeneralPath) shape).moveTo(172.175, 150.88);
        ((GeneralPath) shape).lineTo(173.252, 150.88);
        ((GeneralPath) shape).lineTo(173.597, 150.873);
        ((GeneralPath) shape).curveTo(173.927, 150.873, 174.145, 150.827,
                174.251, 150.732);
        ((GeneralPath) shape).curveTo(174.35501, 150.63899, 174.408, 150.441,
                174.408, 150.13899);
        ((GeneralPath) shape).curveTo(174.408, 149.82599, 174.35301, 149.62799,
                174.24301, 149.547);
        ((GeneralPath) shape).curveTo(174.13402, 149.467, 173.86201, 149.425,
                173.43001, 149.425);
        ((GeneralPath) shape).lineTo(172.175, 149.425);
        ((GeneralPath) shape).lineTo(172.175, 150.88);
        ((GeneralPath) shape).moveTo(172.175, 148.699);
        ((GeneralPath) shape).lineTo(173.377, 148.699);
        ((GeneralPath) shape).curveTo(173.766, 148.699, 174.01399, 148.657,
                174.122, 148.57);
        ((GeneralPath) shape).curveTo(174.228, 148.485, 174.28299, 148.285,
                174.28299, 147.973);
        ((GeneralPath) shape).curveTo(174.28299, 147.559, 174.04799, 147.35,
                173.57799, 147.35);
        ((GeneralPath) shape).lineTo(172.17499, 147.35);
        ((GeneralPath) shape).lineTo(172.17499, 148.699);
        ((GeneralPath) shape).moveTo(171.193, 151.708);
        ((GeneralPath) shape).lineTo(171.193, 146.52199);
        ((GeneralPath) shape).lineTo(173.737, 146.52199);
        ((GeneralPath) shape).curveTo(174.332, 146.52199, 174.74, 146.61699,
                174.956, 146.80899);
        ((GeneralPath) shape).curveTo(175.172, 146.999, 175.28, 147.35999,
                175.28, 147.88998);
        ((GeneralPath) shape).curveTo(175.28, 148.53099, 175.026, 148.90898,
                174.518, 149.02199);
        ((GeneralPath) shape).lineTo(174.518, 149.04099);
        ((GeneralPath) shape).curveTo(175.11, 149.13199, 175.405, 149.54298,
                175.405, 150.27599);
        ((GeneralPath) shape).curveTo(175.405, 150.80399, 175.29199, 151.17598,
                175.064, 151.38899);
        ((GeneralPath) shape).curveTo(174.836, 151.602, 174.44, 151.708,
                173.874, 151.708);
        ((GeneralPath) shape).lineTo(171.193, 151.708);
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
        ((GeneralPath) shape).moveTo(177.572, 150.082);
        ((GeneralPath) shape).curveTo(177.08301, 150.082, 176.837, 150.251,
                176.837, 150.591);
        ((GeneralPath) shape).curveTo(176.837, 150.826, 176.886, 150.98001,
                176.988, 151.054);
        ((GeneralPath) shape).curveTo(177.089, 151.126, 177.30101, 151.164,
                177.625, 151.164);
        ((GeneralPath) shape).curveTo(178.152, 151.164, 178.417, 150.985,
                178.417, 150.628);
        ((GeneralPath) shape).curveTo(178.418, 150.265, 178.137, 150.082,
                177.572, 150.082);
        ((GeneralPath) shape).moveTo(176.992, 149.128);
        ((GeneralPath) shape).lineTo(176.10501, 149.128);
        ((GeneralPath) shape).curveTo(176.10501, 148.69301, 176.20601,
                148.40201, 176.408, 148.25401);
        ((GeneralPath) shape).curveTo(176.60901, 148.10802, 177.011, 148.03302,
                177.61, 148.03302);
        ((GeneralPath) shape).curveTo(178.262, 148.03302, 178.702, 148.12202,
                178.933, 148.30302);
        ((GeneralPath) shape).curveTo(179.162, 148.48102, 179.278, 148.82503,
                179.278, 149.33203);
        ((GeneralPath) shape).lineTo(179.278, 151.70703);
        ((GeneralPath) shape).lineTo(178.41, 151.70703);
        ((GeneralPath) shape).lineTo(178.45201, 151.20903);
        ((GeneralPath) shape).lineTo(178.42902, 151.20503);
        ((GeneralPath) shape).curveTo(178.26201, 151.56602, 177.87701,
                151.74803, 177.27301, 151.74803);
        ((GeneralPath) shape).curveTo(176.397, 151.74803, 175.95702, 151.37604,
                175.95702, 150.62703);
        ((GeneralPath) shape).curveTo(175.95702, 149.87303, 176.40402,
                149.49503, 177.30301, 149.49503);
        ((GeneralPath) shape).curveTo(177.90201, 149.49503, 178.266, 149.63202,
                178.395, 149.90903);
        ((GeneralPath) shape).lineTo(178.41, 149.90903);
        ((GeneralPath) shape).lineTo(178.41, 149.32002);
        ((GeneralPath) shape).curveTo(178.41, 149.03702, 178.36, 148.84903,
                178.26201, 148.75603);
        ((GeneralPath) shape).curveTo(178.164, 148.66502, 177.96301, 148.61702,
                177.656, 148.61702);
        ((GeneralPath) shape).curveTo(177.214, 148.619, 176.992, 148.789,
                176.992, 149.128);
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
        ((GeneralPath) shape).moveTo(183.169, 149.075);
        ((GeneralPath) shape).lineTo(182.32, 149.075);
        ((GeneralPath) shape).curveTo(182.317, 149.045, 182.313, 149.022,
                182.309, 149.007);
        ((GeneralPath) shape).curveTo(182.292, 148.832, 182.24301, 148.724,
                182.15901, 148.681);
        ((GeneralPath) shape).curveTo(182.07701, 148.63899, 181.878, 148.616,
                181.56201, 148.616);
        ((GeneralPath) shape).curveTo(181.11002, 148.616, 180.88301, 148.762,
                180.88301, 149.05699);
        ((GeneralPath) shape).curveTo(180.88301, 149.256, 180.923, 149.37599,
                181.003, 149.41399);
        ((GeneralPath) shape).curveTo(181.08301, 149.45198, 181.352, 149.48198,
                181.813, 149.50499);
        ((GeneralPath) shape).curveTo(182.431, 149.53499, 182.834, 149.61699,
                183.022, 149.75198);
        ((GeneralPath) shape).curveTo(183.20801, 149.88498, 183.302, 150.16098,
                183.302, 150.57698);
        ((GeneralPath) shape).curveTo(183.302, 151.01898, 183.179, 151.32698,
                182.929, 151.49799);
        ((GeneralPath) shape).curveTo(182.68001, 151.66899, 182.233, 151.75499,
                181.589, 151.75499);
        ((GeneralPath) shape).curveTo(180.97101, 151.75499, 180.546, 151.67899,
                180.319, 151.523);
        ((GeneralPath) shape).curveTo(180.091, 151.36899, 179.977, 151.081,
                179.977, 150.657);
        ((GeneralPath) shape).lineTo(179.977, 150.566);
        ((GeneralPath) shape).lineTo(180.879, 150.566);
        ((GeneralPath) shape).curveTo(180.868, 150.616, 180.86, 150.657,
                180.857, 150.68799);
        ((GeneralPath) shape).curveTo(180.823, 151.00899, 181.069, 151.17099,
                181.59999, 151.17099);
        ((GeneralPath) shape).curveTo(182.148, 151.17099, 182.42299, 151.01099,
                182.42299, 150.69199);
        ((GeneralPath) shape).curveTo(182.42299, 150.38599, 182.25198,
                150.23299, 181.90698, 150.23299);
        ((GeneralPath) shape).curveTo(181.13199, 150.23299, 180.61899,
                150.16098, 180.37299, 150.012);
        ((GeneralPath) shape).curveTo(180.12599, 149.866, 180.00299, 149.56,
                180.00299, 149.096);
        ((GeneralPath) shape).curveTo(180.00299, 148.68199, 180.11499,
                148.40099, 180.34, 148.25299);
        ((GeneralPath) shape).curveTo(180.564, 148.107, 180.996, 148.03299,
                181.637, 148.03299);
        ((GeneralPath) shape).curveTo(182.23999, 148.03299, 182.64699, 148.103,
                182.85599, 148.245);
        ((GeneralPath) shape).curveTo(183.065, 148.384, 183.169, 148.661,
                183.169, 149.075);
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
        ((GeneralPath) shape).moveTo(186.331, 149.516);
        ((GeneralPath) shape).lineTo(186.327, 149.371);
        ((GeneralPath) shape).curveTo(186.327, 149.082, 186.278, 148.896,
                186.177, 148.809);
        ((GeneralPath) shape).curveTo(186.078, 148.723, 185.86101, 148.68001,
                185.52701, 148.68001);
        ((GeneralPath) shape).curveTo(185.205, 148.68001, 184.994, 148.731,
                184.89601, 148.835);
        ((GeneralPath) shape).curveTo(184.79901, 148.938, 184.75002, 149.166,
                184.75002, 149.515);
        ((GeneralPath) shape).lineTo(186.331, 149.515);
        ((GeneralPath) shape).moveTo(186.323, 150.546);
        ((GeneralPath) shape).lineTo(187.196, 150.546);
        ((GeneralPath) shape).lineTo(187.196, 150.686);
        ((GeneralPath) shape).curveTo(187.196, 151.395, 186.665, 151.75,
                185.603, 151.75);
        ((GeneralPath) shape).curveTo(184.882, 151.75, 184.412, 151.628,
                184.189, 151.381);
        ((GeneralPath) shape).curveTo(183.967, 151.136, 183.855, 150.61499,
                183.855, 149.819);
        ((GeneralPath) shape).curveTo(183.855, 149.112, 183.971, 148.638,
                184.204, 148.395);
        ((GeneralPath) shape).curveTo(184.435, 148.15201, 184.89, 148.03,
                185.56499, 148.03);
        ((GeneralPath) shape).curveTo(186.21098, 148.03, 186.64598, 148.148,
                186.86499, 148.385);
        ((GeneralPath) shape).curveTo(187.08499, 148.62099, 187.19499, 149.086,
                187.19499, 149.78099);
        ((GeneralPath) shape).lineTo(187.19499, 150.047);
        ((GeneralPath) shape).lineTo(184.741, 150.047);
        ((GeneralPath) shape).curveTo(184.737, 150.127, 184.734, 150.18,
                184.734, 150.207);
        ((GeneralPath) shape).curveTo(184.734, 150.564, 184.78899, 150.802,
                184.89899, 150.921);
        ((GeneralPath) shape).curveTo(185.00899, 151.039, 185.22699, 151.1,
                185.55699, 151.1);
        ((GeneralPath) shape).curveTo(185.87498, 151.1, 186.08199, 151.065,
                186.17899, 150.996);
        ((GeneralPath) shape).curveTo(186.273, 150.927, 186.323, 150.777,
                186.323, 150.546);
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
        ((GeneralPath) shape).moveTo(188.747, 148.076);
        ((GeneralPath) shape).lineTo(188.747, 149.011);
        ((GeneralPath) shape).lineTo(187.879, 149.011);
        ((GeneralPath) shape).lineTo(187.879, 148.076);
        ((GeneralPath) shape).lineTo(188.747, 148.076);
        ((GeneralPath) shape).moveTo(188.747, 150.773);
        ((GeneralPath) shape).lineTo(188.747, 151.708);
        ((GeneralPath) shape).lineTo(187.879, 151.708);
        ((GeneralPath) shape).lineTo(187.879, 150.773);
        ((GeneralPath) shape).lineTo(188.747, 150.773);
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
        paint3(g, clip_, defaultTransform_, alpha__0, clip__0,
                defaultTransform__0, alpha__0_97, clip__0_97,
                defaultTransform__0_97);
    }

    private static void paint3(Graphics2D g, Shape clip_,
            AffineTransform defaultTransform_, float alpha__0, Shape clip__0,
            AffineTransform defaultTransform__0, float alpha__0_97,
            Shape clip__0_97, AffineTransform defaultTransform__0_97) {
        Shape shape;
        Paint paint;
        Stroke stroke;
        float origAlpha;
        // _0_97 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        stroke = new BasicStroke(1.945f, 1, 1, 10.0f, null, 0.0f);
        shape = new Line2D.Float(39.837002f, 190.440002f, 239.761993f,
                190.440002f);
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
        paint = new Color(35, 31, 32, 255);
        stroke = new BasicStroke(1.945f, 1, 1, 10.0f, null, 0.0f);
        shape = new Line2D.Float(39.837002f, 353.102997f, 239.761993f,
                353.102997f);
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
        ((GeneralPath) shape).moveTo(50.88, 194.239);
        ((GeneralPath) shape).lineTo(49.15, 200.721);
        ((GeneralPath) shape).lineTo(47.335003, 200.721);
        ((GeneralPath) shape).lineTo(46.435, 197.39699);
        ((GeneralPath) shape).curveTo(46.354, 197.09799, 46.257, 196.69398,
                46.146, 196.18999);
        ((GeneralPath) shape).lineTo(46.056, 195.79199);
        ((GeneralPath) shape).lineTo(46.014, 195.79199);
        ((GeneralPath) shape).lineTo(45.919, 196.196);
        ((GeneralPath) shape).lineTo(45.829, 196.595);
        ((GeneralPath) shape).curveTo(45.762997, 196.863, 45.690998, 197.131,
                45.614998, 197.40201);
        ((GeneralPath) shape).lineTo(44.690998, 200.72202);
        ((GeneralPath) shape).lineTo(42.894997, 200.72202);
        ((GeneralPath) shape).lineTo(41.216995, 194.24002);
        ((GeneralPath) shape).lineTo(42.467995, 194.24002);
        ((GeneralPath) shape).lineTo(43.401993, 197.79703);
        ((GeneralPath) shape).curveTo(43.458992, 198.02502, 43.52999,
                198.33904, 43.614994, 198.74203);
        ((GeneralPath) shape).lineTo(43.713993, 199.21704);
        ((GeneralPath) shape).lineTo(43.808994, 199.69205);
        ((GeneralPath) shape).lineTo(43.850994, 199.69205);
        ((GeneralPath) shape).curveTo(43.900993, 199.48305, 43.938995,
                199.32605, 43.964993, 199.21704);
        ((GeneralPath) shape).lineTo(44.07899, 198.74704);
        ((GeneralPath) shape).curveTo(44.13799, 198.50005, 44.22099, 198.18704,
                44.32999, 197.80203);
        ((GeneralPath) shape).lineTo(45.32599, 194.24004);
        ((GeneralPath) shape).lineTo(46.765987, 194.24004);
        ((GeneralPath) shape).lineTo(47.737988, 197.80203);
        ((GeneralPath) shape).curveTo(47.81899, 198.10603, 47.898987,
                198.42003, 47.97999, 198.74704);
        ((GeneralPath) shape).lineTo(48.08899, 199.21704);
        ((GeneralPath) shape).lineTo(48.202988, 199.69205);
        ((GeneralPath) shape).lineTo(48.240986, 199.69205);
        ((GeneralPath) shape).lineTo(48.344986, 199.21704);
        ((GeneralPath) shape).lineTo(48.444984, 198.74203);
        ((GeneralPath) shape).curveTo(48.525986, 198.35904, 48.600983,
                198.04404, 48.667984, 197.79204);
        ((GeneralPath) shape).lineTo(49.625984, 194.24004);
        ((GeneralPath) shape).lineTo(50.88, 194.24004);
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
        ((GeneralPath) shape).moveTo(54.349, 197.981);
        ((GeneralPath) shape).lineTo(54.343998, 197.8);
        ((GeneralPath) shape).curveTo(54.343998, 197.44, 54.281998, 197.207,
                54.156998, 197.097);
        ((GeneralPath) shape).curveTo(54.032997, 196.99, 53.760998, 196.936,
                53.343998, 196.936);
        ((GeneralPath) shape).curveTo(52.940998, 196.936, 52.677998, 197.0,
                52.553997, 197.13);
        ((GeneralPath) shape).curveTo(52.434, 197.25801, 52.371998, 197.544,
                52.371998, 197.981);
        ((GeneralPath) shape).lineTo(54.349, 197.981);
        ((GeneralPath) shape).moveTo(54.339, 199.268);
        ((GeneralPath) shape).lineTo(55.43, 199.268);
        ((GeneralPath) shape).lineTo(55.43, 199.44301);
        ((GeneralPath) shape).curveTo(55.43, 200.32901, 54.766, 200.77301,
                53.439, 200.77301);
        ((GeneralPath) shape).curveTo(52.538, 200.77301, 51.951, 200.62102,
                51.670998, 200.313);
        ((GeneralPath) shape).curveTo(51.393997, 200.007, 51.253998, 199.356,
                51.253998, 198.36101);
        ((GeneralPath) shape).curveTo(51.253998, 197.47801, 51.399, 196.884,
                51.69, 196.58);
        ((GeneralPath) shape).curveTo(51.98, 196.276, 52.548, 196.12401,
                53.392, 196.12401);
        ((GeneralPath) shape).curveTo(54.199997, 196.12401, 54.743, 196.27101,
                55.017998, 196.56801);
        ((GeneralPath) shape).curveTo(55.293, 196.86201, 55.430996, 197.44402,
                55.430996, 198.313);
        ((GeneralPath) shape).lineTo(55.430996, 198.645);
        ((GeneralPath) shape).lineTo(52.363995, 198.645);
        ((GeneralPath) shape).curveTo(52.358994, 198.74501, 52.353996,
                198.81201, 52.353996, 198.845);
        ((GeneralPath) shape).curveTo(52.353996, 199.291, 52.422997, 199.588,
                52.560997, 199.738);
        ((GeneralPath) shape).curveTo(52.697998, 199.88501, 52.970997,
                199.96101, 53.382996, 199.96101);
        ((GeneralPath) shape).curveTo(53.780994, 199.96101, 54.039997,
                199.91801, 54.159996, 199.83002);
        ((GeneralPath) shape).curveTo(54.277, 199.745, 54.339, 199.558, 54.339,
                199.268);
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
        ((GeneralPath) shape).moveTo(58.185, 198.688);
        ((GeneralPath) shape).curveTo(57.574, 198.688, 57.266003, 198.899,
                57.266003, 199.325);
        ((GeneralPath) shape).curveTo(57.266003, 199.619, 57.328003, 199.81099,
                57.455, 199.90399);
        ((GeneralPath) shape).curveTo(57.58, 199.99399, 57.846, 200.04199,
                58.251003, 200.04199);
        ((GeneralPath) shape).curveTo(58.910004, 200.04199, 59.242004, 199.818,
                59.242004, 199.372);
        ((GeneralPath) shape).curveTo(59.242, 198.917, 58.891, 198.688, 58.185,
                198.688);
        ((GeneralPath) shape).moveTo(57.459, 197.497);
        ((GeneralPath) shape).lineTo(56.35, 197.497);
        ((GeneralPath) shape).curveTo(56.35, 196.95299, 56.475, 196.59, 56.729,
                196.405);
        ((GeneralPath) shape).curveTo(56.98, 196.222, 57.482, 196.13, 58.231,
                196.13);
        ((GeneralPath) shape).curveTo(59.045998, 196.13, 59.596, 196.241,
                59.885998, 196.46701);
        ((GeneralPath) shape).curveTo(60.172997, 196.69002, 60.316998,
                197.12001, 60.316998, 197.75401);
        ((GeneralPath) shape).lineTo(60.316998, 200.72202);
        ((GeneralPath) shape).lineTo(59.232, 200.72202);
        ((GeneralPath) shape).lineTo(59.283997, 200.10002);
        ((GeneralPath) shape).lineTo(59.255997, 200.09502);
        ((GeneralPath) shape).curveTo(59.047997, 200.54701, 58.565998,
                200.77402, 57.810997, 200.77402);
        ((GeneralPath) shape).curveTo(56.715996, 200.77402, 56.165997,
                200.30902, 56.165997, 199.37302);
        ((GeneralPath) shape).curveTo(56.165997, 198.43002, 56.725, 197.95802,
                57.848995, 197.95802);
        ((GeneralPath) shape).curveTo(58.597996, 197.95802, 59.052994,
                198.12903, 59.213997, 198.47603);
        ((GeneralPath) shape).lineTo(59.232998, 198.47603);
        ((GeneralPath) shape).lineTo(59.232998, 197.74004);
        ((GeneralPath) shape).curveTo(59.232998, 197.38603, 59.170998,
                197.15103, 59.047997, 197.03503);
        ((GeneralPath) shape).curveTo(58.924995, 196.92104, 58.672997,
                196.86203, 58.289997, 196.86203);
        ((GeneralPath) shape).curveTo(57.737, 196.86, 57.459, 197.072, 57.459,
                197.497);
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
        ((GeneralPath) shape).moveTo(63.536, 196.984);
        ((GeneralPath) shape).curveTo(63.123, 196.984, 62.853, 197.07599,
                62.725998, 197.26399);
        ((GeneralPath) shape).curveTo(62.6, 197.44899, 62.536, 197.846, 62.536,
                198.45099);
        ((GeneralPath) shape).curveTo(62.536, 199.04, 62.602, 199.43,
                62.739998, 199.624);
        ((GeneralPath) shape).curveTo(62.874996, 199.816, 63.152996, 199.91399,
                63.57, 199.91399);
        ((GeneralPath) shape).curveTo(63.991, 199.91399, 64.266, 199.82399,
                64.395, 199.63899);
        ((GeneralPath) shape).curveTo(64.52, 199.456, 64.584, 199.062, 64.584,
                198.456);
        ((GeneralPath) shape).curveTo(64.584, 197.836, 64.52, 197.435, 64.392,
                197.254);
        ((GeneralPath) shape).curveTo(64.264, 197.074, 63.979, 196.984, 63.536,
                196.984);
        ((GeneralPath) shape).moveTo(61.399, 196.181);
        ((GeneralPath) shape).lineTo(62.503, 196.181);
        ((GeneralPath) shape).lineTo(62.461, 196.855);
        ((GeneralPath) shape).lineTo(62.483997, 196.86);
        ((GeneralPath) shape).curveTo(62.745, 196.371, 63.232998, 196.12401,
                63.947998, 196.12401);
        ((GeneralPath) shape).curveTo(64.606995, 196.12401, 65.062, 196.28801,
                65.311, 196.61601);
        ((GeneralPath) shape).curveTo(65.558, 196.94402, 65.683, 197.54001,
                65.683, 198.40802);
        ((GeneralPath) shape).curveTo(65.683, 199.31302, 65.56, 199.93301,
                65.312996, 200.27002);
        ((GeneralPath) shape).curveTo(65.065994, 200.60503, 64.61099,
                200.77303, 63.942997, 200.77303);
        ((GeneralPath) shape).curveTo(63.232, 200.77303, 62.767998, 200.53502,
                62.548996, 200.06102);
        ((GeneralPath) shape).lineTo(62.529995, 200.06102);
        ((GeneralPath) shape).lineTo(62.529995, 202.66801);
        ((GeneralPath) shape).lineTo(61.397995, 202.66801);
        ((GeneralPath) shape).lineTo(61.397995, 196.181);
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
        ((GeneralPath) shape).moveTo(68.573, 196.988);
        ((GeneralPath) shape).curveTo(68.123, 196.988, 67.834, 197.069, 67.711,
                197.233);
        ((GeneralPath) shape).curveTo(67.588, 197.394, 67.526, 197.776, 67.526,
                198.375);
        ((GeneralPath) shape).curveTo(67.526, 199.059, 67.583, 199.486, 67.702,
                199.657);
        ((GeneralPath) shape).curveTo(67.818, 199.828, 68.11, 199.914, 68.578,
                199.914);
        ((GeneralPath) shape).curveTo(69.028, 199.914, 69.315, 199.824,
                69.43301, 199.643);
        ((GeneralPath) shape).curveTo(69.55201, 199.462, 69.61101, 199.031,
                69.61101, 198.34201);
        ((GeneralPath) shape).curveTo(69.61101, 197.76302, 69.54901, 197.393,
                69.42601, 197.23102);
        ((GeneralPath) shape).curveTo(69.303, 197.069, 69.019, 196.988, 68.573,
                196.988);
        ((GeneralPath) shape).moveTo(68.583, 196.129);
        ((GeneralPath) shape).curveTo(69.458, 196.129, 70.031, 196.269, 70.304,
                196.549);
        ((GeneralPath) shape).curveTo(70.574, 196.829, 70.712, 197.42099,
                70.712, 198.323);
        ((GeneralPath) shape).curveTo(70.712, 199.33, 70.578995, 199.99,
                70.313995, 200.303);
        ((GeneralPath) shape).curveTo(70.048996, 200.616, 69.489, 200.773,
                68.635994, 200.773);
        ((GeneralPath) shape).curveTo(67.71199, 200.773, 67.11199, 200.62799,
                66.83699, 200.336);
        ((GeneralPath) shape).curveTo(66.56399, 200.046, 66.42699, 199.405,
                66.42699, 198.413);
        ((GeneralPath) shape).curveTo(66.42699, 197.461, 66.55999, 196.84099,
                66.82999, 196.556);
        ((GeneralPath) shape).curveTo(67.097, 196.271, 67.682, 196.129, 68.583,
                196.129);
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
        ((GeneralPath) shape).moveTo(71.621, 196.181);
        ((GeneralPath) shape).lineTo(72.697, 196.181);
        ((GeneralPath) shape).lineTo(72.655, 196.946);
        ((GeneralPath) shape).lineTo(72.679, 196.951);
        ((GeneralPath) shape).curveTo(72.889, 196.405, 73.364, 196.129, 74.101,
                196.129);
        ((GeneralPath) shape).curveTo(75.172, 196.129, 75.707, 196.62799,
                75.707, 197.63);
        ((GeneralPath) shape).lineTo(75.707, 200.72101);
        ((GeneralPath) shape).lineTo(74.622, 200.72101);
        ((GeneralPath) shape).lineTo(74.622, 197.815);
        ((GeneralPath) shape).lineTo(74.598, 197.49701);
        ((GeneralPath) shape).curveTo(74.548, 197.16, 74.283, 196.98901,
                73.802, 196.98901);
        ((GeneralPath) shape).curveTo(73.073006, 196.98901, 72.707, 197.33601,
                72.707, 198.03001);
        ((GeneralPath) shape).lineTo(72.707, 200.72202);
        ((GeneralPath) shape).lineTo(71.622, 200.72202);
        ((GeneralPath) shape).lineTo(71.622, 196.181);
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
        ((GeneralPath) shape).moveTo(80.548, 197.43);
        ((GeneralPath) shape).lineTo(79.486, 197.43);
        ((GeneralPath) shape).curveTo(79.481, 197.392, 79.477, 197.36299,
                79.472, 197.34499);
        ((GeneralPath) shape).curveTo(79.451, 197.12599, 79.389, 196.99098,
                79.284, 196.93698);
        ((GeneralPath) shape).curveTo(79.182, 196.88399, 78.933, 196.85599,
                78.537994, 196.85599);
        ((GeneralPath) shape).curveTo(77.97399, 196.85599, 77.688995,
                197.03899, 77.688995, 197.40698);
        ((GeneralPath) shape).curveTo(77.688995, 197.65698, 77.738, 197.80598,
                77.838, 197.85399);
        ((GeneralPath) shape).curveTo(77.937, 197.90099, 78.273994, 197.939,
                78.85, 197.96799);
        ((GeneralPath) shape).curveTo(79.622, 198.00598, 80.127, 198.10799,
                80.362, 198.277);
        ((GeneralPath) shape).curveTo(80.594, 198.444, 80.713, 198.788, 80.713,
                199.308);
        ((GeneralPath) shape).curveTo(80.713, 199.861, 80.559, 200.246,
                80.245995, 200.459);
        ((GeneralPath) shape).curveTo(79.935, 200.673, 79.37599, 200.78,
                78.56999, 200.78);
        ((GeneralPath) shape).curveTo(77.79799, 200.78, 77.26699, 200.685,
                76.982994, 200.49);
        ((GeneralPath) shape).curveTo(76.69799, 200.298, 76.55599, 199.93701,
                76.55599, 199.40701);
        ((GeneralPath) shape).lineTo(76.55599, 199.29301);
        ((GeneralPath) shape).lineTo(77.68399, 199.29301);
        ((GeneralPath) shape).curveTo(77.66999, 199.35501, 77.65999, 199.40701,
                77.65599, 199.445);
        ((GeneralPath) shape).curveTo(77.61299, 199.847, 77.92099, 200.04901,
                78.58499, 200.04901);
        ((GeneralPath) shape).curveTo(79.26999, 200.04901, 79.61299, 199.85,
                79.61299, 199.451);
        ((GeneralPath) shape).curveTo(79.61299, 199.069, 79.399994, 198.876,
                78.967995, 198.876);
        ((GeneralPath) shape).curveTo(77.99899, 198.876, 77.35899, 198.78601,
                77.050995, 198.6);
        ((GeneralPath) shape).curveTo(76.743, 198.417, 76.589, 198.035, 76.589,
                197.45601);
        ((GeneralPath) shape).curveTo(76.589, 196.938, 76.729, 196.58601,
                77.009995, 196.40102);
        ((GeneralPath) shape).curveTo(77.28999, 196.21802, 77.829994,
                196.12602, 78.631996, 196.12602);
        ((GeneralPath) shape).curveTo(79.384995, 196.12602, 79.895, 196.21402,
                80.155, 196.39203);
        ((GeneralPath) shape).curveTo(80.418, 196.566, 80.548, 196.913, 80.548,
                197.43);
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
        ((GeneralPath) shape).moveTo(85.855, 197.62);
        ((GeneralPath) shape).curveTo(85.378006, 197.689, 85.139, 198.04799,
                85.139, 198.693);
        ((GeneralPath) shape).curveTo(85.139, 199.19199, 85.22, 199.51, 85.381,
                199.648);
        ((GeneralPath) shape).curveTo(85.542, 199.78299, 85.916, 199.853,
                86.508995, 199.853);
        ((GeneralPath) shape).curveTo(87.016, 199.853, 87.34499, 199.831,
                87.496994, 199.784);
        ((GeneralPath) shape).curveTo(87.645996, 199.739, 87.80799, 199.611,
                87.978, 199.397);
        ((GeneralPath) shape).lineTo(85.855, 197.62);
        ((GeneralPath) shape).moveTo(88.282, 197.596);
        ((GeneralPath) shape).lineTo(89.335, 197.596);
        ((GeneralPath) shape).lineTo(89.335, 197.805);
        ((GeneralPath) shape).lineTo(89.325, 198.37);
        ((GeneralPath) shape).curveTo(89.325, 198.64099, 89.286995, 198.931,
                89.20599, 199.239);
        ((GeneralPath) shape).lineTo(90.134995, 200.027);
        ((GeneralPath) shape).lineTo(89.551994, 200.73499);
        ((GeneralPath) shape).lineTo(88.774994, 200.07898);
        ((GeneralPath) shape).curveTo(88.48799, 200.54498, 87.825, 200.77698,
                86.784, 200.77698);
        ((GeneralPath) shape).curveTo(85.628, 200.77698, 84.871994, 200.64398,
                84.51199, 200.37798);
        ((GeneralPath) shape).curveTo(84.15399, 200.11197, 83.97399, 199.54898,
                83.97399, 198.68698);
        ((GeneralPath) shape).curveTo(83.97399, 197.71599, 84.383995,
                197.16998, 85.20599, 197.04898);
        ((GeneralPath) shape).curveTo(84.853, 196.73997, 84.674995, 196.35098,
                84.674995, 195.88098);
        ((GeneralPath) shape).curveTo(84.674995, 195.28297, 84.81999,
                194.86998, 85.113, 194.64198);
        ((GeneralPath) shape).curveTo(85.405, 194.41399, 85.933, 194.29999,
                86.698, 194.29999);
        ((GeneralPath) shape).curveTo(87.499, 194.29999, 88.047, 194.41599,
                88.340996, 194.64899);
        ((GeneralPath) shape).curveTo(88.632996, 194.88098, 88.779, 195.318,
                88.779, 195.96199);
        ((GeneralPath) shape).lineTo(88.774, 196.299);
        ((GeneralPath) shape).lineTo(87.684006, 196.299);
        ((GeneralPath) shape).lineTo(87.684006, 196.11);
        ((GeneralPath) shape).curveTo(87.684006, 195.72, 87.632, 195.476,
                87.52501, 195.376);
        ((GeneralPath) shape).curveTo(87.42101, 195.276, 87.16301, 195.227,
                86.75001, 195.227);
        ((GeneralPath) shape).curveTo(86.143005, 195.227, 85.840004, 195.453,
                85.840004, 195.906);
        ((GeneralPath) shape).curveTo(85.840004, 196.212, 85.996, 196.5,
                86.314, 196.77);
        ((GeneralPath) shape).lineTo(88.281006, 198.461);
        ((GeneralPath) shape).curveTo(88.312004, 198.297, 88.329, 198.143,
                88.329, 198.0);
        ((GeneralPath) shape).curveTo(88.33, 197.884, 88.315, 197.749, 88.282,
                197.596);
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
        ((GeneralPath) shape).moveTo(94.294, 195.274);
        ((GeneralPath) shape).lineTo(94.294, 196.946);
        ((GeneralPath) shape).lineTo(97.328, 196.946);
        ((GeneralPath) shape).lineTo(97.328, 197.853);
        ((GeneralPath) shape).lineTo(94.294, 197.853);
        ((GeneralPath) shape).lineTo(94.294, 199.686);
        ((GeneralPath) shape).lineTo(97.522, 199.686);
        ((GeneralPath) shape).lineTo(97.522, 200.721);
        ((GeneralPath) shape).lineTo(93.066, 200.721);
        ((GeneralPath) shape).lineTo(93.066, 194.239);
        ((GeneralPath) shape).lineTo(97.494, 194.239);
        ((GeneralPath) shape).lineTo(97.494, 195.274);
        ((GeneralPath) shape).lineTo(94.294, 195.274);
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
        ((GeneralPath) shape).moveTo(100.309, 196.988);
        ((GeneralPath) shape).curveTo(99.915, 196.988, 99.655, 197.076, 99.527,
                197.257);
        ((GeneralPath) shape).curveTo(99.402, 197.435, 99.338, 197.80501,
                99.338, 198.366);
        ((GeneralPath) shape).curveTo(99.338, 199.04, 99.396996, 199.468,
                99.516, 199.648);
        ((GeneralPath) shape).curveTo(99.633995, 199.829, 99.914, 199.91899,
                100.357, 199.91899);
        ((GeneralPath) shape).curveTo(100.757, 199.91899, 101.025, 199.827,
                101.16, 199.63899);
        ((GeneralPath) shape).curveTo(101.29301, 199.454, 101.361, 199.081,
                101.361, 198.523);
        ((GeneralPath) shape).curveTo(101.361, 197.877, 101.298, 197.461,
                101.169, 197.27199);
        ((GeneralPath) shape).curveTo(101.042, 197.083, 100.754, 196.988,
                100.309, 196.988);
        ((GeneralPath) shape).moveTo(102.461, 196.181);
        ((GeneralPath) shape).lineTo(102.461, 202.668);
        ((GeneralPath) shape).lineTo(101.376, 202.668);
        ((GeneralPath) shape).lineTo(101.376, 200.056);
        ((GeneralPath) shape).lineTo(101.357, 200.051);
        ((GeneralPath) shape).curveTo(101.158005, 200.53499, 100.693, 200.778,
                99.963005, 200.778);
        ((GeneralPath) shape).curveTo(99.297005, 200.778, 98.842, 200.609,
                98.600006, 200.272);
        ((GeneralPath) shape).curveTo(98.35801, 199.935, 98.23801, 199.30101,
                98.23801, 198.37001);
        ((GeneralPath) shape).curveTo(98.23801, 197.51302, 98.36101, 196.92401,
                98.61001, 196.606);
        ((GeneralPath) shape).curveTo(98.85701, 196.28801, 99.31501, 196.128,
                99.98301, 196.128);
        ((GeneralPath) shape).curveTo(100.66801, 196.128, 101.128006,
                196.37001, 101.36201, 196.85901);
        ((GeneralPath) shape).lineTo(101.39101, 196.85);
        ((GeneralPath) shape).lineTo(101.37701, 196.18001);
        ((GeneralPath) shape).lineTo(102.461, 196.18001);
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
        ((GeneralPath) shape).moveTo(107.553, 196.181);
        ((GeneralPath) shape).lineTo(107.553, 200.721);
        ((GeneralPath) shape).lineTo(106.467, 200.721);
        ((GeneralPath) shape).lineTo(106.529, 199.94199);
        ((GeneralPath) shape).lineTo(106.51, 199.93799);
        ((GeneralPath) shape).curveTo(106.299, 200.49298, 105.813, 200.773,
                105.05, 200.773);
        ((GeneralPath) shape).curveTo(104.024, 200.773, 103.509, 200.26,
                103.509, 199.23);
        ((GeneralPath) shape).lineTo(103.509, 196.181);
        ((GeneralPath) shape).lineTo(104.594, 196.181);
        ((GeneralPath) shape).lineTo(104.594, 198.969);
        ((GeneralPath) shape).curveTo(104.594, 199.35399, 104.646, 199.608,
                104.755005, 199.732);
        ((GeneralPath) shape).curveTo(104.86101, 199.853, 105.08501, 199.915,
                105.423004, 199.915);
        ((GeneralPath) shape).curveTo(106.117004, 199.915, 106.465004, 199.497,
                106.465004, 198.661);
        ((GeneralPath) shape).lineTo(106.465004, 196.18199);
        ((GeneralPath) shape).lineTo(107.553, 196.18199);
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
        ((GeneralPath) shape).moveTo(109.74, 196.181);
        ((GeneralPath) shape).lineTo(109.74, 200.721);
        ((GeneralPath) shape).lineTo(108.655, 200.721);
        ((GeneralPath) shape).lineTo(108.655, 196.181);
        ((GeneralPath) shape).lineTo(109.74, 196.181);
        ((GeneralPath) shape).moveTo(109.74, 194.239);
        ((GeneralPath) shape).lineTo(109.74, 195.146);
        ((GeneralPath) shape).lineTo(108.655, 195.146);
        ((GeneralPath) shape).lineTo(108.655, 194.239);
        ((GeneralPath) shape).lineTo(109.74, 194.239);
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
        ((GeneralPath) shape).moveTo(112.947, 196.984);
        ((GeneralPath) shape).curveTo(112.534, 196.984, 112.264, 197.07599,
                112.136, 197.26399);
        ((GeneralPath) shape).curveTo(112.01, 197.44899, 111.946, 197.846,
                111.946, 198.45099);
        ((GeneralPath) shape).curveTo(111.946, 199.04, 112.012, 199.43, 112.15,
                199.624);
        ((GeneralPath) shape).curveTo(112.285, 199.816, 112.563, 199.91399,
                112.98, 199.91399);
        ((GeneralPath) shape).curveTo(113.402, 199.91399, 113.677, 199.82399,
                113.805, 199.63899);
        ((GeneralPath) shape).curveTo(113.93, 199.456, 113.994, 199.062,
                113.994, 198.456);
        ((GeneralPath) shape).curveTo(113.994, 197.836, 113.93, 197.435,
                113.802, 197.254);
        ((GeneralPath) shape).curveTo(113.675, 197.074, 113.391, 196.984,
                112.947, 196.984);
        ((GeneralPath) shape).moveTo(110.81, 196.181);
        ((GeneralPath) shape).lineTo(111.913994, 196.181);
        ((GeneralPath) shape).lineTo(111.871994, 196.855);
        ((GeneralPath) shape).lineTo(111.895996, 196.86);
        ((GeneralPath) shape).curveTo(112.157, 196.371, 112.645, 196.12401,
                113.35999, 196.12401);
        ((GeneralPath) shape).curveTo(114.01899, 196.12401, 114.47399,
                196.28801, 114.72299, 196.61601);
        ((GeneralPath) shape).curveTo(114.96999, 196.94402, 115.09499,
                197.54001, 115.09499, 198.40802);
        ((GeneralPath) shape).curveTo(115.09499, 199.31302, 114.97199,
                199.93301, 114.72499, 200.27002);
        ((GeneralPath) shape).curveTo(114.47799, 200.60503, 114.02299,
                200.77303, 113.35499, 200.77303);
        ((GeneralPath) shape).curveTo(112.64399, 200.77303, 112.178986,
                200.53502, 111.96099, 200.06102);
        ((GeneralPath) shape).lineTo(111.94199, 200.06102);
        ((GeneralPath) shape).lineTo(111.94199, 202.66801);
        ((GeneralPath) shape).lineTo(110.80899, 202.66801);
        ((GeneralPath) shape).lineTo(110.80899, 196.181);
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
        ((GeneralPath) shape).moveTo(116.003, 196.181);
        ((GeneralPath) shape).lineTo(117.088, 196.181);
        ((GeneralPath) shape).lineTo(117.06, 196.879);
        ((GeneralPath) shape).lineTo(117.084, 196.884);
        ((GeneralPath) shape).curveTo(117.304, 196.381, 117.766, 196.129,
                118.468, 196.129);
        ((GeneralPath) shape).curveTo(119.286, 196.129, 119.741005, 196.409,
                119.833, 196.97);
        ((GeneralPath) shape).lineTo(119.852, 196.97);
        ((GeneralPath) shape).curveTo(120.062996, 196.409, 120.53, 196.129,
                121.25, 196.129);
        ((GeneralPath) shape).curveTo(122.297, 196.129, 122.824, 196.65599,
                122.824, 197.715);
        ((GeneralPath) shape).lineTo(122.824, 200.721);
        ((GeneralPath) shape).lineTo(121.738, 200.721);
        ((GeneralPath) shape).lineTo(121.738, 197.952);
        ((GeneralPath) shape).curveTo(121.738, 197.31099, 121.475, 196.98799,
                120.946, 196.98799);
        ((GeneralPath) shape).curveTo(120.287, 196.98799, 119.955, 197.34698,
                119.955, 198.06699);
        ((GeneralPath) shape).lineTo(119.955, 200.721);
        ((GeneralPath) shape).lineTo(118.87, 200.721);
        ((GeneralPath) shape).lineTo(118.87, 197.909);
        ((GeneralPath) shape).curveTo(118.87, 197.534, 118.821, 197.284,
                118.721, 197.166);
        ((GeneralPath) shape).curveTo(118.622, 197.047, 118.411, 196.988,
                118.089, 196.988);
        ((GeneralPath) shape).curveTo(117.423, 196.988, 117.089, 197.354,
                117.089, 198.09001);
        ((GeneralPath) shape).lineTo(117.089, 200.72101);
        ((GeneralPath) shape).lineTo(116.004, 200.72101);
        ((GeneralPath) shape).lineTo(116.004, 196.181);
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
        ((GeneralPath) shape).moveTo(126.863, 197.981);
        ((GeneralPath) shape).lineTo(126.858, 197.8);
        ((GeneralPath) shape).curveTo(126.858, 197.44, 126.796005, 197.207,
                126.671005, 197.097);
        ((GeneralPath) shape).curveTo(126.547005, 196.99, 126.275, 196.936,
                125.858, 196.936);
        ((GeneralPath) shape).curveTo(125.455, 196.936, 125.192, 197.0,
                125.068, 197.13);
        ((GeneralPath) shape).curveTo(124.947, 197.25801, 124.886, 197.544,
                124.886, 197.981);
        ((GeneralPath) shape).lineTo(126.863, 197.981);
        ((GeneralPath) shape).moveTo(126.854, 199.268);
        ((GeneralPath) shape).lineTo(127.945, 199.268);
        ((GeneralPath) shape).lineTo(127.945, 199.44301);
        ((GeneralPath) shape).curveTo(127.945, 200.32901, 127.281, 200.77301,
                125.954, 200.77301);
        ((GeneralPath) shape).curveTo(125.053, 200.77301, 124.466, 200.62102,
                124.186005, 200.313);
        ((GeneralPath) shape).curveTo(123.909004, 200.007, 123.769005, 199.356,
                123.769005, 198.36101);
        ((GeneralPath) shape).curveTo(123.769005, 197.47801, 123.914, 196.884,
                124.205, 196.58);
        ((GeneralPath) shape).curveTo(124.495, 196.276, 125.063, 196.12401,
                125.907005, 196.12401);
        ((GeneralPath) shape).curveTo(126.715004, 196.12401, 127.258,
                196.27101, 127.533005, 196.56801);
        ((GeneralPath) shape).curveTo(127.80801, 196.86201, 127.94601,
                197.44402, 127.94601, 198.313);
        ((GeneralPath) shape).lineTo(127.94601, 198.645);
        ((GeneralPath) shape).lineTo(124.879005, 198.645);
        ((GeneralPath) shape).curveTo(124.87401, 198.74501, 124.87, 198.81201,
                124.87, 198.845);
        ((GeneralPath) shape).curveTo(124.87, 199.291, 124.939, 199.588,
                125.076004, 199.738);
        ((GeneralPath) shape).curveTo(125.214005, 199.88501, 125.48601,
                199.96101, 125.898, 199.96101);
        ((GeneralPath) shape).curveTo(126.296005, 199.96101, 126.555,
                199.91801, 126.675, 199.83002);
        ((GeneralPath) shape).curveTo(126.792, 199.745, 126.854, 199.558,
                126.854, 199.268);
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
        ((GeneralPath) shape).moveTo(128.846, 196.181);
        ((GeneralPath) shape).lineTo(129.922, 196.181);
        ((GeneralPath) shape).lineTo(129.879, 196.946);
        ((GeneralPath) shape).lineTo(129.903, 196.951);
        ((GeneralPath) shape).curveTo(130.114, 196.405, 130.588, 196.129,
                131.325, 196.129);
        ((GeneralPath) shape).curveTo(132.396, 196.129, 132.931, 196.62799,
                132.931, 197.63);
        ((GeneralPath) shape).lineTo(132.931, 200.72101);
        ((GeneralPath) shape).lineTo(131.846, 200.72101);
        ((GeneralPath) shape).lineTo(131.846, 197.815);
        ((GeneralPath) shape).lineTo(131.82199, 197.49701);
        ((GeneralPath) shape).curveTo(131.773, 197.16, 131.50699, 196.98901,
                131.02599, 196.98901);
        ((GeneralPath) shape).curveTo(130.29599, 196.98901, 129.93098,
                197.33601, 129.93098, 198.03001);
        ((GeneralPath) shape).lineTo(129.93098, 200.72202);
        ((GeneralPath) shape).lineTo(128.84598, 200.72202);
        ((GeneralPath) shape).lineTo(128.84598, 196.181);
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
        ((GeneralPath) shape).moveTo(136.916, 196.181);
        ((GeneralPath) shape).lineTo(136.916, 197.007);
        ((GeneralPath) shape).lineTo(135.172, 197.007);
        ((GeneralPath) shape).lineTo(135.172, 199.287);
        ((GeneralPath) shape).curveTo(135.172, 199.707, 135.331, 199.918,
                135.651, 199.918);
        ((GeneralPath) shape).curveTo(136.002, 199.918, 136.177, 199.664,
                136.177, 199.15399);
        ((GeneralPath) shape).lineTo(136.177, 198.97299);
        ((GeneralPath) shape).lineTo(137.101, 198.97299);
        ((GeneralPath) shape).lineTo(137.101, 199.20099);
        ((GeneralPath) shape).curveTo(137.101, 199.40999, 137.097, 199.58798,
                137.082, 199.73799);
        ((GeneralPath) shape).curveTo(137.023, 200.43098, 136.509, 200.77798,
                135.537, 200.77798);
        ((GeneralPath) shape).curveTo(134.57, 200.77798, 134.086, 200.33398,
                134.086, 199.44298);
        ((GeneralPath) shape).lineTo(134.086, 197.00697);
        ((GeneralPath) shape).lineTo(133.499, 197.00697);
        ((GeneralPath) shape).lineTo(133.499, 196.18097);
        ((GeneralPath) shape).lineTo(134.086, 196.18097);
        ((GeneralPath) shape).lineTo(134.086, 195.16496);
        ((GeneralPath) shape).lineTo(135.172, 195.16496);
        ((GeneralPath) shape).lineTo(135.172, 196.18097);
        ((GeneralPath) shape).lineTo(136.916, 196.18097);
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
        shape = new Rectangle2D.Double(140.16000366210938, 194.23899841308594,
                1.2280000448226929, 6.48199987411499);
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
        ((GeneralPath) shape).moveTo(142.504, 196.181);
        ((GeneralPath) shape).lineTo(143.58, 196.181);
        ((GeneralPath) shape).lineTo(143.537, 196.946);
        ((GeneralPath) shape).lineTo(143.561, 196.951);
        ((GeneralPath) shape).curveTo(143.772, 196.405, 144.246, 196.129,
                144.983, 196.129);
        ((GeneralPath) shape).curveTo(146.054, 196.129, 146.589, 196.62799,
                146.589, 197.63);
        ((GeneralPath) shape).lineTo(146.589, 200.72101);
        ((GeneralPath) shape).lineTo(145.504, 200.72101);
        ((GeneralPath) shape).lineTo(145.504, 197.815);
        ((GeneralPath) shape).lineTo(145.48, 197.49701);
        ((GeneralPath) shape).curveTo(145.43, 197.16, 145.165, 196.98901,
                144.68399, 196.98901);
        ((GeneralPath) shape).curveTo(143.95499, 196.98901, 143.58899,
                197.33601, 143.58899, 198.03001);
        ((GeneralPath) shape).lineTo(143.58899, 200.72202);
        ((GeneralPath) shape).lineTo(142.50398, 200.72202);
        ((GeneralPath) shape).lineTo(142.50398, 196.181);
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
        ((GeneralPath) shape).moveTo(151.427, 196.181);
        ((GeneralPath) shape).lineTo(150.128, 200.721);
        ((GeneralPath) shape).lineTo(148.431, 200.721);
        ((GeneralPath) shape).lineTo(147.066, 196.181);
        ((GeneralPath) shape).lineTo(148.21799, 196.181);
        ((GeneralPath) shape).lineTo(148.81499, 198.275);
        ((GeneralPath) shape).curveTo(148.89598, 198.56499, 148.971, 198.847,
                149.04298, 199.12);
        ((GeneralPath) shape).lineTo(149.15198, 199.543);
        ((GeneralPath) shape).lineTo(149.26097, 199.965);
        ((GeneralPath) shape).lineTo(149.28497, 199.965);
        ((GeneralPath) shape).lineTo(149.38498, 199.543);
        ((GeneralPath) shape).lineTo(149.48499, 199.125);
        ((GeneralPath) shape).curveTo(149.56099, 198.81, 149.63098, 198.527,
                149.69798, 198.28);
        ((GeneralPath) shape).lineTo(150.25798, 196.181);
        ((GeneralPath) shape).lineTo(151.427, 196.181);
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
        ((GeneralPath) shape).moveTo(154.841, 197.981);
        ((GeneralPath) shape).lineTo(154.836, 197.8);
        ((GeneralPath) shape).curveTo(154.836, 197.44, 154.774, 197.207,
                154.648, 197.097);
        ((GeneralPath) shape).curveTo(154.525, 196.99, 154.252, 196.936,
                153.83499, 196.936);
        ((GeneralPath) shape).curveTo(153.43199, 196.936, 153.16798, 197.0,
                153.045, 197.13);
        ((GeneralPath) shape).curveTo(152.924, 197.25801, 152.862, 197.544,
                152.862, 197.981);
        ((GeneralPath) shape).lineTo(154.841, 197.981);
        ((GeneralPath) shape).moveTo(154.831, 199.268);
        ((GeneralPath) shape).lineTo(155.92099, 199.268);
        ((GeneralPath) shape).lineTo(155.92099, 199.44301);
        ((GeneralPath) shape).curveTo(155.92099, 200.32901, 155.25699,
                200.77301, 153.93, 200.77301);
        ((GeneralPath) shape).curveTo(153.02899, 200.77301, 152.441, 200.62102,
                152.16199, 200.313);
        ((GeneralPath) shape).curveTo(151.885, 200.007, 151.74498, 199.356,
                151.74498, 198.36101);
        ((GeneralPath) shape).curveTo(151.74498, 197.47801, 151.88998, 196.884,
                152.18198, 196.58);
        ((GeneralPath) shape).curveTo(152.47098, 196.276, 153.03998, 196.12401,
                153.88397, 196.12401);
        ((GeneralPath) shape).curveTo(154.69197, 196.12401, 155.23497,
                196.27101, 155.50897, 196.56801);
        ((GeneralPath) shape).curveTo(155.78397, 196.86201, 155.92197,
                197.44402, 155.92197, 198.313);
        ((GeneralPath) shape).lineTo(155.92197, 198.645);
        ((GeneralPath) shape).lineTo(152.85497, 198.645);
        ((GeneralPath) shape).curveTo(152.84996, 198.74501, 152.84596,
                198.81201, 152.84596, 198.845);
        ((GeneralPath) shape).curveTo(152.84596, 199.291, 152.91396, 199.588,
                153.05196, 199.738);
        ((GeneralPath) shape).curveTo(153.18895, 199.88501, 153.46196,
                199.96101, 153.87396, 199.96101);
        ((GeneralPath) shape).curveTo(154.27196, 199.96101, 154.53096,
                199.91801, 154.65096, 199.83002);
        ((GeneralPath) shape).curveTo(154.77, 199.745, 154.831, 199.558,
                154.831, 199.268);
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
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(156.823, 196.181);
        ((GeneralPath) shape).lineTo(157.899, 196.181);
        ((GeneralPath) shape).lineTo(157.856, 196.946);
        ((GeneralPath) shape).lineTo(157.88, 196.951);
        ((GeneralPath) shape).curveTo(158.091, 196.405, 158.565, 196.129,
                159.302, 196.129);
        ((GeneralPath) shape).curveTo(160.373, 196.129, 160.909, 196.62799,
                160.909, 197.63);
        ((GeneralPath) shape).lineTo(160.909, 200.72101);
        ((GeneralPath) shape).lineTo(159.82399, 200.72101);
        ((GeneralPath) shape).lineTo(159.82399, 197.815);
        ((GeneralPath) shape).lineTo(159.79999, 197.49701);
        ((GeneralPath) shape).curveTo(159.74998, 197.16, 159.48499, 196.98901,
                159.00398, 196.98901);
        ((GeneralPath) shape).curveTo(158.27498, 196.98901, 157.90898,
                197.33601, 157.90898, 198.03001);
        ((GeneralPath) shape).lineTo(157.90898, 200.72202);
        ((GeneralPath) shape).lineTo(156.82397, 200.72202);
        ((GeneralPath) shape).lineTo(156.82397, 196.181);
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
        ((GeneralPath) shape).moveTo(164.893, 196.181);
        ((GeneralPath) shape).lineTo(164.893, 197.007);
        ((GeneralPath) shape).lineTo(163.14801, 197.007);
        ((GeneralPath) shape).lineTo(163.14801, 199.287);
        ((GeneralPath) shape).curveTo(163.14801, 199.707, 163.307, 199.918,
                163.62701, 199.918);
        ((GeneralPath) shape).curveTo(163.97801, 199.918, 164.15302, 199.664,
                164.15302, 199.15399);
        ((GeneralPath) shape).lineTo(164.15302, 198.97299);
        ((GeneralPath) shape).lineTo(165.07701, 198.97299);
        ((GeneralPath) shape).lineTo(165.07701, 199.20099);
        ((GeneralPath) shape).curveTo(165.07701, 199.40999, 165.072, 199.58798,
                165.05801, 199.73799);
        ((GeneralPath) shape).curveTo(164.99901, 200.43098, 164.48502,
                200.77798, 163.51302, 200.77798);
        ((GeneralPath) shape).curveTo(162.54602, 200.77798, 162.06302,
                200.33398, 162.06302, 199.44298);
        ((GeneralPath) shape).lineTo(162.06302, 197.00697);
        ((GeneralPath) shape).lineTo(161.47601, 197.00697);
        ((GeneralPath) shape).lineTo(161.47601, 196.18097);
        ((GeneralPath) shape).lineTo(162.06302, 196.18097);
        ((GeneralPath) shape).lineTo(162.06302, 195.16496);
        ((GeneralPath) shape).lineTo(163.14803, 195.16496);
        ((GeneralPath) shape).lineTo(163.14803, 196.18097);
        ((GeneralPath) shape).lineTo(164.893, 196.18097);
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
        ((GeneralPath) shape).moveTo(167.657, 196.988);
        ((GeneralPath) shape).curveTo(167.207, 196.988, 166.918, 197.069,
                166.79399, 197.233);
        ((GeneralPath) shape).curveTo(166.67099, 197.394, 166.609, 197.776,
                166.609, 198.375);
        ((GeneralPath) shape).curveTo(166.609, 199.059, 166.666, 199.486,
                166.78499, 199.657);
        ((GeneralPath) shape).curveTo(166.90099, 199.828, 167.19199, 199.914,
                167.661, 199.914);
        ((GeneralPath) shape).curveTo(168.111, 199.914, 168.398, 199.824,
                168.51599, 199.643);
        ((GeneralPath) shape).curveTo(168.635, 199.462, 168.69398, 199.031,
                168.69398, 198.34201);
        ((GeneralPath) shape).curveTo(168.69398, 197.76302, 168.63199, 197.393,
                168.50899, 197.23102);
        ((GeneralPath) shape).curveTo(168.388, 197.069, 168.103, 196.988,
                167.657, 196.988);
        ((GeneralPath) shape).moveTo(167.667, 196.129);
        ((GeneralPath) shape).curveTo(168.542, 196.129, 169.115, 196.269,
                169.388, 196.549);
        ((GeneralPath) shape).curveTo(169.658, 196.829, 169.795, 197.42099,
                169.795, 198.323);
        ((GeneralPath) shape).curveTo(169.795, 199.33, 169.663, 199.99,
                169.397, 200.303);
        ((GeneralPath) shape).curveTo(169.13101, 200.61598, 168.572, 200.773,
                167.71901, 200.773);
        ((GeneralPath) shape).curveTo(166.794, 200.773, 166.195, 200.62799,
                165.92001, 200.336);
        ((GeneralPath) shape).curveTo(165.64801, 200.046, 165.51001, 199.405,
                165.51001, 198.413);
        ((GeneralPath) shape).curveTo(165.51001, 197.461, 165.643, 196.84099,
                165.91301, 196.556);
        ((GeneralPath) shape).curveTo(166.181, 196.271, 166.766, 196.129,
                167.667, 196.129);
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
        ((GeneralPath) shape).moveTo(170.733, 196.181);
        ((GeneralPath) shape).lineTo(171.819, 196.181);
        ((GeneralPath) shape).lineTo(171.753, 196.817);
        ((GeneralPath) shape).lineTo(171.776, 196.822);
        ((GeneralPath) shape).curveTo(172.035, 196.347, 172.44301, 196.11,
                172.99901, 196.11);
        ((GeneralPath) shape).curveTo(173.79501, 196.11, 174.19402, 196.613,
                174.19402, 197.62);
        ((GeneralPath) shape).lineTo(174.19402, 197.93799);
        ((GeneralPath) shape).lineTo(173.17001, 197.93799);
        ((GeneralPath) shape).curveTo(173.182, 197.814, 173.18901, 197.73299,
                173.18901, 197.69598);
        ((GeneralPath) shape).curveTo(173.18901, 197.21098, 173.00201,
                196.96898, 172.62502, 196.96898);
        ((GeneralPath) shape).curveTo(172.08902, 196.96898, 171.81902,
                197.32698, 171.81902, 198.04698);
        ((GeneralPath) shape).lineTo(171.81902, 200.71999);
        ((GeneralPath) shape).lineTo(170.73302, 200.71999);
        ((GeneralPath) shape).lineTo(170.73302, 196.181);
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
        ((GeneralPath) shape).moveTo(178.522, 196.181);
        ((GeneralPath) shape).lineTo(177.332, 201.006);
        ((GeneralPath) shape).curveTo(177.166, 201.69, 176.957, 202.148,
                176.709, 202.385);
        ((GeneralPath) shape).curveTo(176.462, 202.62, 176.064, 202.739,
                175.517, 202.739);
        ((GeneralPath) shape).curveTo(175.394, 202.739, 175.26599, 202.734,
                175.133, 202.719);
        ((GeneralPath) shape).lineTo(175.133, 201.91699);
        ((GeneralPath) shape).curveTo(175.228, 201.92099, 175.306, 201.92699,
                175.37, 201.92699);
        ((GeneralPath) shape).curveTo(175.83, 201.92699, 176.124, 201.52599,
                176.247, 200.71999);
        ((GeneralPath) shape).lineTo(175.692, 200.71999);
        ((GeneralPath) shape).lineTo(174.209, 196.18);
        ((GeneralPath) shape).lineTo(175.375, 196.18);
        ((GeneralPath) shape).lineTo(175.944, 198.103);
        ((GeneralPath) shape).lineTo(176.228, 199.067);
        ((GeneralPath) shape).curveTo(176.243, 199.129, 176.287, 199.29001,
                176.361, 199.551);
        ((GeneralPath) shape).lineTo(176.49799, 200.03);
        ((GeneralPath) shape).lineTo(176.52199, 200.03);
        ((GeneralPath) shape).lineTo(176.622, 199.551);
        ((GeneralPath) shape).curveTo(176.672, 199.304, 176.70499, 199.14299,
                176.722, 199.067);
        ((GeneralPath) shape).lineTo(176.945, 198.103);
        ((GeneralPath) shape).lineTo(177.38101, 196.18);
        ((GeneralPath) shape).lineTo(178.522, 196.18);
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
        ((GeneralPath) shape).moveTo(198.1, 201.939);
        ((GeneralPath) shape).lineTo(198.1, 202.814);
        ((GeneralPath) shape).curveTo(197.259, 202.814, 196.753, 202.64099,
                196.585, 202.293);
        ((GeneralPath) shape).curveTo(196.417, 201.946, 196.33301, 200.9,
                196.33301, 199.155);
        ((GeneralPath) shape).curveTo(196.33301, 197.411, 196.417, 196.366,
                196.585, 196.019);
        ((GeneralPath) shape).curveTo(196.75201, 195.674, 197.257, 195.5,
                198.1, 195.5);
        ((GeneralPath) shape).lineTo(198.1, 196.375);
        ((GeneralPath) shape).curveTo(197.76001, 196.375, 197.55501, 196.463,
                197.485, 196.64);
        ((GeneralPath) shape).curveTo(197.417, 196.816, 197.382, 197.34799,
                197.382, 198.235);
        ((GeneralPath) shape).lineTo(197.382, 200.079);
        ((GeneralPath) shape).curveTo(197.382, 200.96599, 197.41501, 201.498,
                197.485, 201.67499);
        ((GeneralPath) shape).curveTo(197.553, 201.851, 197.758, 201.939,
                198.1, 201.939);
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
        ((GeneralPath) shape).moveTo(199.919, 195.512);
        ((GeneralPath) shape).lineTo(199.919, 197.84099);
        ((GeneralPath) shape).lineTo(199.94, 197.84499);
        ((GeneralPath) shape).curveTo(200.114, 197.38098, 200.516, 197.14598,
                201.145, 197.14598);
        ((GeneralPath) shape).curveTo(202.059, 197.14598, 202.518, 197.57999,
                202.518, 198.44798);
        ((GeneralPath) shape).lineTo(202.518, 201.11699);
        ((GeneralPath) shape).lineTo(201.58, 201.11699);
        ((GeneralPath) shape).lineTo(201.58, 198.579);
        ((GeneralPath) shape).curveTo(201.58, 198.11899, 201.34601, 197.88899,
                200.879, 197.88899);
        ((GeneralPath) shape).curveTo(200.23999, 197.88899, 199.92, 198.22198,
                199.92, 198.887);
        ((GeneralPath) shape).lineTo(199.92, 201.11699);
        ((GeneralPath) shape).lineTo(198.982, 201.11699);
        ((GeneralPath) shape).lineTo(198.982, 195.51099);
        ((GeneralPath) shape).lineTo(199.919, 195.51099);
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
        ((GeneralPath) shape).moveTo(205.972, 198.749);
        ((GeneralPath) shape).lineTo(205.968, 198.59299);
        ((GeneralPath) shape).curveTo(205.968, 198.28099, 205.91501, 198.07999,
                205.806, 197.98499);
        ((GeneralPath) shape).curveTo(205.7, 197.89299, 205.464, 197.84598,
                205.103, 197.84598);
        ((GeneralPath) shape).curveTo(204.754, 197.84598, 204.527, 197.90097,
                204.42, 198.01398);
        ((GeneralPath) shape).curveTo(204.316, 198.12497, 204.262, 198.37097,
                204.262, 198.74898);
        ((GeneralPath) shape).lineTo(205.972, 198.74898);
        ((GeneralPath) shape).moveTo(205.964, 199.861);
        ((GeneralPath) shape).lineTo(206.907, 199.861);
        ((GeneralPath) shape).lineTo(206.907, 200.01299);
        ((GeneralPath) shape).curveTo(206.907, 200.77899, 206.333, 201.16298,
                205.185, 201.16298);
        ((GeneralPath) shape).curveTo(204.40599, 201.16298, 203.897, 201.03198,
                203.65599, 200.76498);
        ((GeneralPath) shape).curveTo(203.41599, 200.49998, 203.295, 199.93698,
                203.295, 199.07698);
        ((GeneralPath) shape).curveTo(203.295, 198.31398, 203.42, 197.79999,
                203.672, 197.53699);
        ((GeneralPath) shape).curveTo(203.922, 197.27399, 204.414, 197.14198,
                205.144, 197.14198);
        ((GeneralPath) shape).curveTo(205.843, 197.14198, 206.312, 197.26898,
                206.55, 197.52599);
        ((GeneralPath) shape).curveTo(206.78801, 197.77998, 206.907, 198.28299,
                206.907, 199.03499);
        ((GeneralPath) shape).lineTo(206.907, 199.32298);
        ((GeneralPath) shape).lineTo(204.255, 199.32298);
        ((GeneralPath) shape).curveTo(204.251, 199.40898, 204.24701, 199.46698,
                204.24701, 199.49599);
        ((GeneralPath) shape).curveTo(204.24701, 199.88199, 204.30602,
                200.13899, 204.425, 200.26799);
        ((GeneralPath) shape).curveTo(204.544, 200.39499, 204.779, 200.46098,
                205.136, 200.46098);
        ((GeneralPath) shape).curveTo(205.481, 200.46098, 205.704, 200.42398,
                205.808, 200.34798);
        ((GeneralPath) shape).curveTo(205.911, 200.274, 205.964, 200.112,
                205.964, 199.861);
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
        ((GeneralPath) shape).moveTo(210.893, 197.192);
        ((GeneralPath) shape).lineTo(209.753, 199.04);
        ((GeneralPath) shape).lineTo(211.02, 201.118);
        ((GeneralPath) shape).lineTo(209.896, 201.118);
        ((GeneralPath) shape).lineTo(209.109, 199.693);
        ((GeneralPath) shape).lineTo(208.343, 201.118);
        ((GeneralPath) shape).lineTo(207.191, 201.118);
        ((GeneralPath) shape).lineTo(208.474, 199.053);
        ((GeneralPath) shape).lineTo(207.342, 197.192);
        ((GeneralPath) shape).lineTo(208.461, 197.192);
        ((GeneralPath) shape).lineTo(209.109, 198.436);
        ((GeneralPath) shape).lineTo(209.773, 197.192);
        ((GeneralPath) shape).lineTo(210.893, 197.192);
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
        ((GeneralPath) shape).moveTo(213.99, 198.749);
        ((GeneralPath) shape).lineTo(213.98601, 198.59299);
        ((GeneralPath) shape).curveTo(213.98601, 198.28099, 213.93301,
                198.07999, 213.824, 197.98499);
        ((GeneralPath) shape).curveTo(213.71701, 197.89299, 213.48201,
                197.84598, 213.121, 197.84598);
        ((GeneralPath) shape).curveTo(212.772, 197.84598, 212.544, 197.90097,
                212.438, 198.01398);
        ((GeneralPath) shape).curveTo(212.334, 198.12497, 212.28, 198.37097,
                212.28, 198.74898);
        ((GeneralPath) shape).lineTo(213.99, 198.74898);
        ((GeneralPath) shape).moveTo(213.981, 199.861);
        ((GeneralPath) shape).lineTo(214.924, 199.861);
        ((GeneralPath) shape).lineTo(214.924, 200.01299);
        ((GeneralPath) shape).curveTo(214.924, 200.77899, 214.34999, 201.16298,
                213.202, 201.16298);
        ((GeneralPath) shape).curveTo(212.42299, 201.16298, 211.915, 201.03198,
                211.67299, 200.76498);
        ((GeneralPath) shape).curveTo(211.43298, 200.49998, 211.312, 199.93698,
                211.312, 199.07698);
        ((GeneralPath) shape).curveTo(211.312, 198.31398, 211.437, 197.79999,
                211.689, 197.53699);
        ((GeneralPath) shape).curveTo(211.939, 197.27399, 212.431, 197.14198,
                213.161, 197.14198);
        ((GeneralPath) shape).curveTo(213.86, 197.14198, 214.329, 197.26898,
                214.567, 197.52599);
        ((GeneralPath) shape).curveTo(214.80501, 197.77998, 214.923, 198.28299,
                214.923, 199.03499);
        ((GeneralPath) shape).lineTo(214.923, 199.32298);
        ((GeneralPath) shape).lineTo(212.27101, 199.32298);
        ((GeneralPath) shape).curveTo(212.26701, 199.40898, 212.26302,
                199.46698, 212.26302, 199.49599);
        ((GeneralPath) shape).curveTo(212.26302, 199.88199, 212.32202,
                200.13899, 212.44101, 200.26799);
        ((GeneralPath) shape).curveTo(212.56001, 200.39499, 212.79501,
                200.46098, 213.15201, 200.46098);
        ((GeneralPath) shape).curveTo(213.49701, 200.46098, 213.72, 200.42398,
                213.824, 200.34798);
        ((GeneralPath) shape).curveTo(213.928, 200.274, 213.981, 200.112,
                213.981, 199.861);
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
        ((GeneralPath) shape).moveTo(218.975, 198.272);
        ((GeneralPath) shape).lineTo(218.057, 198.272);
        ((GeneralPath) shape).curveTo(218.05301, 198.239, 218.04901, 198.214,
                218.04501, 198.198);
        ((GeneralPath) shape).curveTo(218.02602, 198.009, 217.973, 197.892,
                217.88301, 197.845);
        ((GeneralPath) shape).curveTo(217.79501, 197.8, 217.58002, 197.775,
                217.23701, 197.775);
        ((GeneralPath) shape).curveTo(216.74901, 197.775, 216.50302, 197.933,
                216.50302, 198.252);
        ((GeneralPath) shape).curveTo(216.50302, 198.467, 216.54602, 198.597,
                216.63202, 198.638);
        ((GeneralPath) shape).curveTo(216.71802, 198.679, 217.00902, 198.712,
                217.50702, 198.737);
        ((GeneralPath) shape).curveTo(218.17502, 198.77, 218.61102, 198.858,
                218.81502, 199.004);
        ((GeneralPath) shape).curveTo(219.01602, 199.148, 219.11801, 199.44499,
                219.11801, 199.896);
        ((GeneralPath) shape).curveTo(219.11801, 200.374, 218.98502, 200.707,
                218.71501, 200.892);
        ((GeneralPath) shape).curveTo(218.44601, 201.077, 217.96301, 201.16899,
                217.266, 201.16899);
        ((GeneralPath) shape).curveTo(216.598, 201.16899, 216.139, 201.08699,
                215.893, 200.91899);
        ((GeneralPath) shape).curveTo(215.647, 200.75198, 215.52301, 200.43999,
                215.52301, 199.982);
        ((GeneralPath) shape).lineTo(215.52301, 199.883);
        ((GeneralPath) shape).lineTo(216.49901, 199.883);
        ((GeneralPath) shape).curveTo(216.48701, 199.937, 216.47801, 199.982,
                216.475, 200.01399);
        ((GeneralPath) shape).curveTo(216.438, 200.361, 216.70401, 200.536,
                217.278, 200.536);
        ((GeneralPath) shape).curveTo(217.87, 200.536, 218.168, 200.36299,
                218.168, 200.01799);
        ((GeneralPath) shape).curveTo(218.168, 199.687, 217.983, 199.521,
                217.61, 199.521);
        ((GeneralPath) shape).curveTo(216.772, 199.521, 216.218, 199.443,
                215.952, 199.28299);
        ((GeneralPath) shape).curveTo(215.685, 199.12498, 215.553, 198.79399,
                215.553, 198.29298);
        ((GeneralPath) shape).curveTo(215.553, 197.84598, 215.674, 197.54198,
                215.91699, 197.38098);
        ((GeneralPath) shape).curveTo(216.159, 197.22298, 216.62599, 197.14297,
                217.31898, 197.14297);
        ((GeneralPath) shape).curveTo(217.97098, 197.14297, 218.41098,
                197.21898, 218.63698, 197.37297);
        ((GeneralPath) shape).curveTo(218.863, 197.525, 218.975, 197.824,
                218.975, 198.272);
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
        ((GeneralPath) shape).moveTo(219.811, 196.375);
        ((GeneralPath) shape).lineTo(219.811, 195.5);
        ((GeneralPath) shape).curveTo(220.651, 195.5, 221.158, 195.672,
                221.326, 196.019);
        ((GeneralPath) shape).curveTo(221.494, 196.364, 221.578, 197.409,
                221.578, 199.155);
        ((GeneralPath) shape).curveTo(221.578, 200.898, 221.494, 201.944,
                221.326, 202.293);
        ((GeneralPath) shape).curveTo(221.158, 202.64, 220.654, 202.814,
                219.811, 202.814);
        ((GeneralPath) shape).lineTo(219.811, 201.939);
        ((GeneralPath) shape).curveTo(220.278, 201.939, 220.51201, 201.687,
                220.51201, 201.179);
        ((GeneralPath) shape).lineTo(220.529, 200.078);
        ((GeneralPath) shape).lineTo(220.529, 198.23401);
        ((GeneralPath) shape).lineTo(220.51201, 197.13301);
        ((GeneralPath) shape).curveTo(220.511, 196.629, 220.278, 196.375,
                219.811, 196.375);
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
        ((GeneralPath) shape).moveTo(44.543, 210.299);
        ((GeneralPath) shape).lineTo(44.953, 209.706);
        ((GeneralPath) shape).lineTo(46.022, 210.43199);
        ((GeneralPath) shape).curveTo(46.041, 210.24599, 46.052, 210.12,
                46.052, 210.05598);
        ((GeneralPath) shape).lineTo(46.052, 208.88998);
        ((GeneralPath) shape).curveTo(46.052, 208.40698, 45.974, 208.09798,
                45.816998, 207.96098);
        ((GeneralPath) shape).curveTo(45.661, 207.82599, 45.304996, 207.75699,
                44.752, 207.75699);
        ((GeneralPath) shape).curveTo(44.092, 207.75699, 43.707, 207.84099,
                43.594997, 208.012);
        ((GeneralPath) shape).curveTo(43.484997, 208.181, 43.427998, 208.76799,
                43.427998, 209.775);
        ((GeneralPath) shape).curveTo(43.427998, 210.53499, 43.494, 210.987,
                43.630997, 211.131);
        ((GeneralPath) shape).curveTo(43.764996, 211.276, 44.187996, 211.34799,
                44.899, 211.34799);
        ((GeneralPath) shape).curveTo(45.259, 211.34799, 45.544, 211.27199,
                45.748997, 211.116);
        ((GeneralPath) shape).lineTo(44.543, 210.299);
        ((GeneralPath) shape).moveTo(47.49, 211.45);
        ((GeneralPath) shape).lineTo(47.084003, 212.04199);
        ((GeneralPath) shape).lineTo(46.591003, 211.7);
        ((GeneralPath) shape).curveTo(46.271004, 212.027, 45.658005, 212.19,
                44.756004, 212.19);
        ((GeneralPath) shape).curveTo(43.768005, 212.19, 43.133003, 212.042,
                42.853004, 211.743);
        ((GeneralPath) shape).curveTo(42.572006, 211.44699, 42.432003, 210.772,
                42.432003, 209.72);
        ((GeneralPath) shape).curveTo(42.432003, 208.497, 42.565002, 207.723,
                42.832005, 207.399);
        ((GeneralPath) shape).curveTo(43.098003, 207.076, 43.734005, 206.91301,
                44.741005, 206.91301);
        ((GeneralPath) shape).curveTo(45.731007, 206.91301, 46.364006, 207.065,
                46.639004, 207.371);
        ((GeneralPath) shape).curveTo(46.912003, 207.675, 47.050003, 208.379,
                47.050003, 209.482);
        ((GeneralPath) shape).curveTo(47.050003, 210.26399, 47.016003,
                210.79599, 46.948, 211.077);
        ((GeneralPath) shape).lineTo(47.49, 211.45);
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
        ((GeneralPath) shape).moveTo(50.186, 208.513);
        ((GeneralPath) shape).lineTo(50.186, 209.174);
        ((GeneralPath) shape).lineTo(48.791, 209.174);
        ((GeneralPath) shape).lineTo(48.791, 210.998);
        ((GeneralPath) shape).curveTo(48.791, 211.334, 48.918, 211.503, 49.174,
                211.503);
        ((GeneralPath) shape).curveTo(49.454, 211.503, 49.595, 211.3, 49.595,
                210.89201);
        ((GeneralPath) shape).lineTo(49.595, 210.74701);
        ((GeneralPath) shape).lineTo(50.334, 210.74701);
        ((GeneralPath) shape).lineTo(50.334, 210.93001);
        ((GeneralPath) shape).curveTo(50.334, 211.09702, 50.331, 211.24,
                50.319, 211.35901);
        ((GeneralPath) shape).curveTo(50.272, 211.914, 49.861, 212.19101,
                49.083, 212.19101);
        ((GeneralPath) shape).curveTo(48.31, 212.19101, 47.923, 211.83601,
                47.923, 211.12302);
        ((GeneralPath) shape).lineTo(47.923, 209.17401);
        ((GeneralPath) shape).lineTo(47.453, 209.17401);
        ((GeneralPath) shape).lineTo(47.453, 208.51302);
        ((GeneralPath) shape).lineTo(47.923, 208.51302);
        ((GeneralPath) shape).lineTo(47.923, 207.7);
        ((GeneralPath) shape).lineTo(48.792, 207.7);
        ((GeneralPath) shape).lineTo(48.792, 208.513);
        ((GeneralPath) shape).lineTo(50.186, 208.513);
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
        ((GeneralPath) shape).moveTo(53.797, 208.513);
        ((GeneralPath) shape).lineTo(52.846, 212.373);
        ((GeneralPath) shape).curveTo(52.713, 212.92, 52.546, 213.287, 52.347,
                213.477);
        ((GeneralPath) shape).curveTo(52.15, 213.66501, 51.831, 213.761,
                51.393, 213.761);
        ((GeneralPath) shape).curveTo(51.294003, 213.761, 51.192, 213.757,
                51.086002, 213.745);
        ((GeneralPath) shape).lineTo(51.086002, 213.103);
        ((GeneralPath) shape).curveTo(51.162003, 213.107, 51.225002, 213.111,
                51.275, 213.111);
        ((GeneralPath) shape).curveTo(51.643, 213.111, 51.878002, 212.79,
                51.977, 212.146);
        ((GeneralPath) shape).lineTo(51.533, 212.146);
        ((GeneralPath) shape).lineTo(50.346, 208.51399);
        ((GeneralPath) shape).lineTo(51.279, 208.51399);
        ((GeneralPath) shape).lineTo(51.734, 210.053);
        ((GeneralPath) shape).lineTo(51.962, 210.82399);
        ((GeneralPath) shape).curveTo(51.973003, 210.87299, 52.009003,
                211.00198, 52.068, 211.21098);
        ((GeneralPath) shape).lineTo(52.178, 211.59499);
        ((GeneralPath) shape).lineTo(52.197002, 211.59499);
        ((GeneralPath) shape).lineTo(52.277004, 211.21098);
        ((GeneralPath) shape).curveTo(52.317005, 211.01398, 52.343006,
                210.88399, 52.357006, 210.82399);
        ((GeneralPath) shape).lineTo(52.535007, 210.053);
        ((GeneralPath) shape).lineTo(52.884007, 208.51399);
        ((GeneralPath) shape).lineTo(53.797, 208.51399);
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
        ((GeneralPath) shape).moveTo(60.365, 207.841);
        ((GeneralPath) shape).lineTo(60.365, 212.146);
        ((GeneralPath) shape).lineTo(59.383, 212.146);
        ((GeneralPath) shape).lineTo(59.383, 207.841);
        ((GeneralPath) shape).lineTo(57.889, 207.841);
        ((GeneralPath) shape).lineTo(57.889, 206.959);
        ((GeneralPath) shape).lineTo(61.912, 206.959);
        ((GeneralPath) shape).lineTo(61.912, 207.841);
        ((GeneralPath) shape).lineTo(60.365, 207.841);
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
        ((GeneralPath) shape).moveTo(65.368, 208.513);
        ((GeneralPath) shape).lineTo(64.41599, 212.373);
        ((GeneralPath) shape).curveTo(64.28299, 212.92, 64.11599, 213.287,
                63.916992, 213.477);
        ((GeneralPath) shape).curveTo(63.719994, 213.66501, 63.400993, 213.761,
                62.962994, 213.761);
        ((GeneralPath) shape).curveTo(62.863995, 213.761, 62.761993, 213.757,
                62.654995, 213.745);
        ((GeneralPath) shape).lineTo(62.654995, 213.103);
        ((GeneralPath) shape).curveTo(62.730995, 213.107, 62.793995, 213.111,
                62.844994, 213.111);
        ((GeneralPath) shape).curveTo(63.212994, 213.111, 63.447994, 212.79,
                63.546993, 212.146);
        ((GeneralPath) shape).lineTo(63.102993, 212.146);
        ((GeneralPath) shape).lineTo(61.915993, 208.51399);
        ((GeneralPath) shape).lineTo(62.84899, 208.51399);
        ((GeneralPath) shape).lineTo(63.303993, 210.053);
        ((GeneralPath) shape).lineTo(63.531994, 210.82399);
        ((GeneralPath) shape).curveTo(63.542995, 210.87299, 63.578995,
                211.00198, 63.637993, 211.21098);
        ((GeneralPath) shape).lineTo(63.747993, 211.59499);
        ((GeneralPath) shape).lineTo(63.766994, 211.59499);
        ((GeneralPath) shape).lineTo(63.846996, 211.21098);
        ((GeneralPath) shape).curveTo(63.886997, 211.01398, 63.913, 210.88399,
                63.927, 210.82399);
        ((GeneralPath) shape).lineTo(64.104996, 210.053);
        ((GeneralPath) shape).lineTo(64.453995, 208.51399);
        ((GeneralPath) shape).lineTo(65.368, 208.51399);
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
        ((GeneralPath) shape).moveTo(67.472, 209.155);
        ((GeneralPath) shape).curveTo(67.142, 209.155, 66.926, 209.229, 66.824,
                209.379);
        ((GeneralPath) shape).curveTo(66.723, 209.527, 66.672, 209.844, 66.672,
                210.329);
        ((GeneralPath) shape).curveTo(66.672, 210.79999, 66.725, 211.112,
                66.835, 211.267);
        ((GeneralPath) shape).curveTo(66.943, 211.421, 67.165, 211.499, 67.499,
                211.499);
        ((GeneralPath) shape).curveTo(67.836, 211.499, 68.056, 211.42699,
                68.159004, 211.27899);
        ((GeneralPath) shape).curveTo(68.26, 211.133, 68.311005, 210.81699,
                68.311005, 210.333);
        ((GeneralPath) shape).curveTo(68.311005, 209.83699, 68.26, 209.51599,
                68.157005, 209.372);
        ((GeneralPath) shape).curveTo(68.054, 209.228, 67.827, 209.155, 67.472,
                209.155);
        ((GeneralPath) shape).moveTo(65.762, 208.513);
        ((GeneralPath) shape).lineTo(66.645004, 208.513);
        ((GeneralPath) shape).lineTo(66.61101, 209.053);
        ((GeneralPath) shape).lineTo(66.630005, 209.056);
        ((GeneralPath) shape).curveTo(66.838005, 208.665, 67.229004, 208.468,
                67.801, 208.468);
        ((GeneralPath) shape).curveTo(68.328, 208.468, 68.692, 208.599, 68.891,
                208.86101);
        ((GeneralPath) shape).curveTo(69.088, 209.123, 69.187996, 209.6,
                69.187996, 210.29501);
        ((GeneralPath) shape).curveTo(69.187996, 211.01901, 69.09, 211.51501,
                68.892, 211.78401);
        ((GeneralPath) shape).curveTo(68.695, 212.05202, 68.331, 212.18701,
                67.796, 212.18701);
        ((GeneralPath) shape).curveTo(67.228, 212.18701, 66.855995, 211.99701,
                66.681, 211.617);
        ((GeneralPath) shape).lineTo(66.666, 211.617);
        ((GeneralPath) shape).lineTo(66.666, 213.703);
        ((GeneralPath) shape).lineTo(65.76, 213.703);
        ((GeneralPath) shape).lineTo(65.76, 208.513);
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
        ((GeneralPath) shape).moveTo(72.26, 209.953);
        ((GeneralPath) shape).lineTo(72.256004, 209.808);
        ((GeneralPath) shape).curveTo(72.256004, 209.519, 72.207, 209.333,
                72.106, 209.245);
        ((GeneralPath) shape).curveTo(72.007, 209.15999, 71.79, 209.116,
                71.456, 209.116);
        ((GeneralPath) shape).curveTo(71.134, 209.116, 70.923004, 209.16699,
                70.824, 209.272);
        ((GeneralPath) shape).curveTo(70.728, 209.375, 70.67799, 209.603,
                70.67799, 209.952);
        ((GeneralPath) shape).lineTo(72.26, 209.952);
        ((GeneralPath) shape).moveTo(72.252, 210.983);
        ((GeneralPath) shape).lineTo(73.125, 210.983);
        ((GeneralPath) shape).lineTo(73.125, 211.123);
        ((GeneralPath) shape).curveTo(73.125, 211.832, 72.594, 212.187, 71.532,
                212.187);
        ((GeneralPath) shape).curveTo(70.811, 212.187, 70.340996, 212.065,
                70.117, 211.818);
        ((GeneralPath) shape).curveTo(69.895, 211.573, 69.784, 211.05199,
                69.784, 210.256);
        ((GeneralPath) shape).curveTo(69.784, 209.55, 69.899994, 209.075,
                70.132996, 208.832);
        ((GeneralPath) shape).curveTo(70.364, 208.589, 70.81999, 208.467,
                71.494995, 208.467);
        ((GeneralPath) shape).curveTo(72.141, 208.467, 72.575, 208.58499,
                72.795, 208.82199);
        ((GeneralPath) shape).curveTo(73.015, 209.05798, 73.125, 209.523,
                73.125, 210.21799);
        ((GeneralPath) shape).lineTo(73.125, 210.484);
        ((GeneralPath) shape).lineTo(70.671, 210.484);
        ((GeneralPath) shape).curveTo(70.667, 210.564, 70.663994, 210.61699,
                70.663994, 210.644);
        ((GeneralPath) shape).curveTo(70.663994, 211.00099, 70.718994, 211.239,
                70.828995, 211.358);
        ((GeneralPath) shape).curveTo(70.937996, 211.476, 71.157, 211.537,
                71.48599, 211.537);
        ((GeneralPath) shape).curveTo(71.80499, 211.537, 72.010994, 211.502,
                72.107994, 211.432);
        ((GeneralPath) shape).curveTo(72.203, 211.364, 72.252, 211.214, 72.252,
                210.983);
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
        ((GeneralPath) shape).moveTo(141.903, 206.959);
        ((GeneralPath) shape).lineTo(141.903, 211.264);
        ((GeneralPath) shape).lineTo(144.33, 211.264);
        ((GeneralPath) shape).lineTo(144.33, 212.146);
        ((GeneralPath) shape).lineTo(140.921, 212.146);
        ((GeneralPath) shape).lineTo(140.921, 206.959);
        ((GeneralPath) shape).lineTo(141.903, 206.959);
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
        ((GeneralPath) shape).moveTo(146.385, 209.159);
        ((GeneralPath) shape).curveTo(146.025, 209.159, 145.79399, 209.22299,
                145.69499, 209.355);
        ((GeneralPath) shape).curveTo(145.59698, 209.484, 145.547, 209.79,
                145.547, 210.269);
        ((GeneralPath) shape).curveTo(145.547, 210.816, 145.593, 211.158,
                145.688, 211.295);
        ((GeneralPath) shape).curveTo(145.781, 211.43199, 146.014, 211.5,
                146.389, 211.5);
        ((GeneralPath) shape).curveTo(146.74901, 211.5, 146.979, 211.428,
                147.074, 211.283);
        ((GeneralPath) shape).curveTo(147.169, 211.13802, 147.216, 210.793,
                147.216, 210.242);
        ((GeneralPath) shape).curveTo(147.216, 209.779, 147.167, 209.483,
                147.069, 209.353);
        ((GeneralPath) shape).curveTo(146.97, 209.224, 146.742, 209.159,
                146.385, 209.159);
        ((GeneralPath) shape).moveTo(146.393, 208.471);
        ((GeneralPath) shape).curveTo(147.093, 208.471, 147.55101, 208.583,
                147.76901, 208.80699);
        ((GeneralPath) shape).curveTo(147.98502, 209.03099, 148.09502, 209.504,
                148.09502, 210.226);
        ((GeneralPath) shape).curveTo(148.09502, 211.032, 147.98901, 211.55899,
                147.77702, 211.81);
        ((GeneralPath) shape).curveTo(147.56502, 212.06, 147.11702, 212.186,
                146.43402, 212.186);
        ((GeneralPath) shape).curveTo(145.69502, 212.186, 145.21503, 212.07,
                144.99503, 211.836);
        ((GeneralPath) shape).curveTo(144.77702, 211.605, 144.66702, 211.091,
                144.66702, 210.297);
        ((GeneralPath) shape).curveTo(144.66702, 209.535, 144.77303, 209.039,
                144.98903, 208.811);
        ((GeneralPath) shape).curveTo(145.205, 208.585, 145.672, 208.471,
                146.393, 208.471);
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
        ((GeneralPath) shape).moveTo(151.132, 210.758);
        ((GeneralPath) shape).lineTo(151.99701, 210.758);
        ((GeneralPath) shape).lineTo(151.99701, 210.883);
        ((GeneralPath) shape).lineTo(151.94402, 211.43399);
        ((GeneralPath) shape).curveTo(151.83801, 211.93599, 151.32002,
                212.18599, 150.38902, 212.18599);
        ((GeneralPath) shape).curveTo(149.70702, 212.18599, 149.25302, 212.062,
                149.02803, 211.816);
        ((GeneralPath) shape).curveTo(148.80403, 211.568, 148.69003, 211.073,
                148.69003, 210.32399);
        ((GeneralPath) shape).curveTo(148.69003, 209.59499, 148.80203, 209.105,
                149.02803, 208.84999);
        ((GeneralPath) shape).curveTo(149.25203, 208.59799, 149.69003,
                208.46999, 150.34003, 208.46999);
        ((GeneralPath) shape).curveTo(150.96603, 208.46999, 151.39102,
                208.56099, 151.61403, 208.74599);
        ((GeneralPath) shape).curveTo(151.83603, 208.928, 151.94702, 209.27798,
                151.94702, 209.79298);
        ((GeneralPath) shape).lineTo(151.08603, 209.79298);
        ((GeneralPath) shape).curveTo(151.08603, 209.37099, 150.84303,
                209.15799, 150.35403, 209.15799);
        ((GeneralPath) shape).curveTo(150.00903, 209.15799, 149.79103,
                209.22398, 149.70204, 209.359);
        ((GeneralPath) shape).curveTo(149.61504, 209.49199, 149.56905,
                209.82199, 149.56905, 210.347);
        ((GeneralPath) shape).curveTo(149.56905, 210.852, 149.61604, 211.171,
                149.71504, 211.302);
        ((GeneralPath) shape).curveTo(149.81204, 211.431, 150.05104, 211.498,
                150.43004, 211.498);
        ((GeneralPath) shape).curveTo(150.72804, 211.498, 150.91904, 211.454,
                151.00404, 211.363);
        ((GeneralPath) shape).curveTo(151.088, 211.275, 151.132, 211.074,
                151.132, 210.758);
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
        ((GeneralPath) shape).moveTo(159.283, 211.317);
        ((GeneralPath) shape).lineTo(160.682, 211.317);
        ((GeneralPath) shape).curveTo(161.15201, 211.317, 161.455, 211.209,
                161.59401, 210.992);
        ((GeneralPath) shape).curveTo(161.731, 210.776, 161.80101, 210.29901,
                161.80101, 209.557);
        ((GeneralPath) shape).curveTo(161.80101, 208.794, 161.74, 208.307,
                161.615, 208.09901);
        ((GeneralPath) shape).curveTo(161.492, 207.89201, 161.20201, 207.78702,
                160.74301, 207.78702);
        ((GeneralPath) shape).lineTo(159.28401, 207.78702);
        ((GeneralPath) shape).lineTo(159.28401, 211.317);
        ((GeneralPath) shape).moveTo(158.3, 212.146);
        ((GeneralPath) shape).lineTo(158.3, 206.959);
        ((GeneralPath) shape).lineTo(160.84401, 206.959);
        ((GeneralPath) shape).curveTo(161.56601, 206.959, 162.07301, 207.117,
                162.363, 207.434);
        ((GeneralPath) shape).curveTo(162.651, 207.74901, 162.79701, 208.304,
                162.79701, 209.098);
        ((GeneralPath) shape).curveTo(162.79701, 210.391, 162.68102, 211.22401,
                162.44801, 211.59201);
        ((GeneralPath) shape).curveTo(162.21701, 211.96101, 161.69101, 212.145,
                160.87401, 212.145);
        ((GeneralPath) shape).lineTo(158.3, 212.145);
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
        ((GeneralPath) shape).moveTo(163.484, 208.513);
        ((GeneralPath) shape).lineTo(164.35199, 208.513);
        ((GeneralPath) shape).lineTo(164.32999, 209.071);
        ((GeneralPath) shape).lineTo(164.34898, 209.075);
        ((GeneralPath) shape).curveTo(164.52498, 208.672, 164.89499, 208.471,
                165.45598, 208.471);
        ((GeneralPath) shape).curveTo(166.10999, 208.471, 166.47498, 208.69499,
                166.54797, 209.144);
        ((GeneralPath) shape).lineTo(166.56398, 209.144);
        ((GeneralPath) shape).curveTo(166.73299, 208.69499, 167.10599, 208.471,
                167.68298, 208.471);
        ((GeneralPath) shape).curveTo(168.52098, 208.471, 168.94199, 208.89299,
                168.94199, 209.73999);
        ((GeneralPath) shape).lineTo(168.94199, 212.14499);
        ((GeneralPath) shape).lineTo(168.07298, 212.14499);
        ((GeneralPath) shape).lineTo(168.07298, 209.93);
        ((GeneralPath) shape).curveTo(168.07298, 209.41699, 167.86298, 209.159,
                167.43999, 209.159);
        ((GeneralPath) shape).curveTo(166.913, 209.159, 166.64699, 209.446,
                166.64699, 210.021);
        ((GeneralPath) shape).lineTo(166.64699, 212.14499);
        ((GeneralPath) shape).lineTo(165.77899, 212.14499);
        ((GeneralPath) shape).lineTo(165.77899, 209.89499);
        ((GeneralPath) shape).curveTo(165.77899, 209.59499, 165.739, 209.39499,
                165.65999, 209.301);
        ((GeneralPath) shape).curveTo(165.57999, 209.206, 165.411, 209.15799,
                165.15399, 209.15799);
        ((GeneralPath) shape).curveTo(164.62099, 209.15799, 164.35399, 209.45,
                164.35399, 210.03899);
        ((GeneralPath) shape).lineTo(164.35399, 212.14398);
        ((GeneralPath) shape).lineTo(163.486, 212.14398);
        ((GeneralPath) shape).lineTo(163.486, 208.513);
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
        ((GeneralPath) shape).moveTo(171.361, 209.159);
        ((GeneralPath) shape).curveTo(171.028, 209.159, 170.81299, 209.22499,
                170.71799, 209.362);
        ((GeneralPath) shape).curveTo(170.62299, 209.497, 170.57599, 209.804,
                170.57599, 210.287);
        ((GeneralPath) shape).curveTo(170.57599, 210.798, 170.62299, 211.125,
                170.71799, 211.267);
        ((GeneralPath) shape).curveTo(170.81299, 211.408, 171.03099, 211.48,
                171.372, 211.48);
        ((GeneralPath) shape).curveTo(171.713, 211.48, 171.93399, 211.40799,
                172.036, 211.262);
        ((GeneralPath) shape).curveTo(172.137, 211.11699, 172.18799, 210.79399,
                172.18799, 210.295);
        ((GeneralPath) shape).curveTo(172.18799, 209.816, 172.13899, 209.509,
                172.036, 209.368);
        ((GeneralPath) shape).curveTo(171.935, 209.229, 171.709, 209.159,
                171.361, 209.159);
        ((GeneralPath) shape).moveTo(173.071, 208.513);
        ((GeneralPath) shape).lineTo(173.071, 212.214);
        ((GeneralPath) shape).curveTo(173.071, 212.806, 172.952, 213.213,
                172.713, 213.433);
        ((GeneralPath) shape).curveTo(172.474, 213.65399, 172.034, 213.76399,
                171.39099, 213.76399);
        ((GeneralPath) shape).curveTo(170.769, 213.76399, 170.35399, 213.681,
                170.14299, 213.51399);
        ((GeneralPath) shape).curveTo(169.935, 213.34698, 169.829, 213.01599,
                169.829, 212.52199);
        ((GeneralPath) shape).lineTo(170.67, 212.52199);
        ((GeneralPath) shape).curveTo(170.67, 212.75699, 170.714, 212.90698,
                170.803, 212.97598);
        ((GeneralPath) shape).curveTo(170.89, 213.04198, 171.08699, 213.07698,
                171.394, 213.07698);
        ((GeneralPath) shape).curveTo(171.933, 213.07698, 172.202, 212.85397,
                172.202, 212.40797);
        ((GeneralPath) shape).lineTo(172.202, 211.59198);
        ((GeneralPath) shape).lineTo(172.183, 211.58798);
        ((GeneralPath) shape).curveTo(172.03, 211.97498, 171.675, 212.16898,
                171.121, 212.16898);
        ((GeneralPath) shape).curveTo(170.573, 212.16898, 170.198, 212.03598,
                169.996, 211.76997);
        ((GeneralPath) shape).curveTo(169.795, 211.50397, 169.695, 211.00798,
                169.695, 210.28098);
        ((GeneralPath) shape).curveTo(169.695, 209.59697, 169.796, 209.12598,
                169.996, 208.86397);
        ((GeneralPath) shape).curveTo(170.197, 208.60397, 170.561, 208.47197,
                171.09, 208.47197);
        ((GeneralPath) shape).curveTo(171.666, 208.47197, 172.04399, 208.68497,
                172.224, 209.11397);
        ((GeneralPath) shape).lineTo(172.243, 209.11397);
        ((GeneralPath) shape).lineTo(172.20099, 208.51396);
        ((GeneralPath) shape).lineTo(173.071, 208.51396);
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
        ((GeneralPath) shape).moveTo(185.272, 206.959);
        ((GeneralPath) shape).lineTo(185.272, 212.146);
        ((GeneralPath) shape).lineTo(184.29001, 212.146);
        ((GeneralPath) shape).lineTo(184.29001, 209.319);
        ((GeneralPath) shape).curveTo(184.29001, 209.094, 184.29501, 208.838,
                184.309, 208.551);
        ((GeneralPath) shape).lineTo(184.328, 208.163);
        ((GeneralPath) shape).lineTo(184.347, 207.78);
        ((GeneralPath) shape).lineTo(184.317, 207.78);
        ((GeneralPath) shape).lineTo(184.199, 208.14);
        ((GeneralPath) shape).lineTo(184.086, 208.50099);
        ((GeneralPath) shape).curveTo(183.98, 208.82399, 183.898, 209.064,
                183.839, 209.219);
        ((GeneralPath) shape).lineTo(182.70201, 212.14499);
        ((GeneralPath) shape).lineTo(181.807, 212.14499);
        ((GeneralPath) shape).lineTo(180.658, 209.24199);
        ((GeneralPath) shape).curveTo(180.595, 209.08199, 180.51201, 208.84299,
                180.408, 208.52399);
        ((GeneralPath) shape).lineTo(180.29001, 208.163);
        ((GeneralPath) shape).lineTo(180.17201, 207.806);
        ((GeneralPath) shape).lineTo(180.14201, 207.806);
        ((GeneralPath) shape).lineTo(180.16101, 208.182);
        ((GeneralPath) shape).lineTo(180.18001, 208.56201);
        ((GeneralPath) shape).curveTo(180.195, 208.85402, 180.203, 209.10701,
                180.203, 209.31801);
        ((GeneralPath) shape).lineTo(180.203, 212.145);
        ((GeneralPath) shape).lineTo(179.22101, 212.145);
        ((GeneralPath) shape).lineTo(179.22101, 206.95801);
        ((GeneralPath) shape).lineTo(180.82101, 206.95801);
        ((GeneralPath) shape).lineTo(181.74602, 209.35901);
        ((GeneralPath) shape).curveTo(181.80902, 209.52602, 181.89201, 209.766,
                181.99602, 210.07701);
        ((GeneralPath) shape).lineTo(182.11002, 210.438);
        ((GeneralPath) shape).lineTo(182.22801, 210.795);
        ((GeneralPath) shape).lineTo(182.26201, 210.795);
        ((GeneralPath) shape).lineTo(182.37201, 210.438);
        ((GeneralPath) shape).lineTo(182.48601, 210.08101);
        ((GeneralPath) shape).curveTo(182.57901, 209.781, 182.66101, 209.54102,
                182.729, 209.367);
        ((GeneralPath) shape).lineTo(183.639, 206.95801);
        ((GeneralPath) shape).lineTo(185.272, 206.95801);
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
        ((GeneralPath) shape).moveTo(187.021, 208.513);
        ((GeneralPath) shape).lineTo(187.021, 212.145);
        ((GeneralPath) shape).lineTo(186.153, 212.145);
        ((GeneralPath) shape).lineTo(186.153, 208.513);
        ((GeneralPath) shape).lineTo(187.021, 208.513);
        ((GeneralPath) shape).moveTo(187.021, 206.959);
        ((GeneralPath) shape).lineTo(187.021, 207.685);
        ((GeneralPath) shape).lineTo(186.153, 207.685);
        ((GeneralPath) shape).lineTo(186.153, 206.959);
        ((GeneralPath) shape).lineTo(187.021, 206.959);
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
        ((GeneralPath) shape).moveTo(187.876, 208.513);
        ((GeneralPath) shape).lineTo(188.737, 208.513);
        ((GeneralPath) shape).lineTo(188.702, 209.125);
        ((GeneralPath) shape).lineTo(188.721, 209.129);
        ((GeneralPath) shape).curveTo(188.89, 208.691, 189.269, 208.471,
                189.859, 208.471);
        ((GeneralPath) shape).curveTo(190.71599, 208.471, 191.144, 208.87,
                191.144, 209.672);
        ((GeneralPath) shape).lineTo(191.144, 212.146);
        ((GeneralPath) shape).lineTo(190.276, 212.146);
        ((GeneralPath) shape).lineTo(190.276, 209.821);
        ((GeneralPath) shape).lineTo(190.257, 209.566);
        ((GeneralPath) shape).curveTo(190.21701, 209.29599, 190.005, 209.15999,
                189.62001, 209.15999);
        ((GeneralPath) shape).curveTo(189.03601, 209.15999, 188.744, 209.43698,
                188.744, 209.99199);
        ((GeneralPath) shape).lineTo(188.744, 212.146);
        ((GeneralPath) shape).lineTo(187.876, 212.146);
        ((GeneralPath) shape).lineTo(187.876, 208.513);
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
        ((GeneralPath) shape).moveTo(198.477, 208.471);
        ((GeneralPath) shape).lineTo(197.518, 208.471);
        ((GeneralPath) shape).curveTo(197.514, 208.424, 197.511, 208.388,
                197.511, 208.36499);
        ((GeneralPath) shape).curveTo(197.488, 208.07399, 197.424, 207.89198,
                197.31801, 207.816);
        ((GeneralPath) shape).curveTo(197.212, 207.74199, 196.962, 207.704,
                196.56801, 207.704);
        ((GeneralPath) shape).curveTo(196.10301, 207.704, 195.8, 207.746,
                195.65501, 207.833);
        ((GeneralPath) shape).curveTo(195.51302, 207.918, 195.44101, 208.099,
                195.44101, 208.37599);
        ((GeneralPath) shape).curveTo(195.44101, 208.70299, 195.49802, 208.898,
                195.615, 208.965);
        ((GeneralPath) shape).curveTo(195.731, 209.02899, 196.115, 209.081,
                196.768, 209.11699);
        ((GeneralPath) shape).curveTo(197.53801, 209.159, 198.03601, 209.26898,
                198.264, 209.44798);
        ((GeneralPath) shape).curveTo(198.49, 209.62498, 198.60301, 209.99298,
                198.60301, 210.55298);
        ((GeneralPath) shape).curveTo(198.60301, 211.24098, 198.47002,
                211.68698, 198.20502, 211.88799);
        ((GeneralPath) shape).curveTo(197.94002, 212.08998, 197.35501,
                212.18999, 196.45001, 212.18999);
        ((GeneralPath) shape).curveTo(195.63701, 212.18999, 195.09601,
                212.09099, 194.82901, 211.89398);
        ((GeneralPath) shape).curveTo(194.563, 211.69598, 194.42902, 211.29698,
                194.42902, 210.69298);
        ((GeneralPath) shape).lineTo(194.42601, 210.50298);
        ((GeneralPath) shape).lineTo(195.382, 210.50298);
        ((GeneralPath) shape).lineTo(195.38501, 210.61298);
        ((GeneralPath) shape).curveTo(195.38501, 210.97397, 195.44801,
                211.19597, 195.574, 211.27797);
        ((GeneralPath) shape).curveTo(195.699, 211.35797, 196.044, 211.39897,
                196.61, 211.39897);
        ((GeneralPath) shape).curveTo(197.049, 211.39897, 197.33, 211.35397,
                197.452, 211.25797);
        ((GeneralPath) shape).curveTo(197.573, 211.16496, 197.634, 210.94896,
                197.634, 210.60896);
        ((GeneralPath) shape).curveTo(197.634, 210.35796, 197.589, 210.19296,
                197.496, 210.10896);
        ((GeneralPath) shape).curveTo(197.405, 210.02696, 197.20601, 209.97797,
                196.899, 209.95897);
        ((GeneralPath) shape).lineTo(196.357, 209.92497);
        ((GeneralPath) shape).curveTo(195.538, 209.87697, 195.015, 209.76297,
                194.788, 209.58298);
        ((GeneralPath) shape).curveTo(194.56, 209.40398, 194.446, 209.01698,
                194.446, 208.42398);
        ((GeneralPath) shape).curveTo(194.446, 207.81998, 194.583, 207.41498,
                194.858, 207.21397);
        ((GeneralPath) shape).curveTo(195.131, 207.01297, 195.679, 206.91197,
                196.502, 206.91197);
        ((GeneralPath) shape).curveTo(197.27899, 206.91197, 197.804, 207.00298,
                198.075, 207.18896);
        ((GeneralPath) shape).curveTo(198.345, 207.37297, 198.481, 207.73396,
                198.481, 208.27196);
        ((GeneralPath) shape).lineTo(198.481, 208.471);
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
        ((GeneralPath) shape).moveTo(200.129, 206.959);
        ((GeneralPath) shape).lineTo(200.129, 209.113);
        ((GeneralPath) shape).lineTo(200.148, 209.117);
        ((GeneralPath) shape).curveTo(200.30899, 208.688, 200.681, 208.47101,
                201.262, 208.47101);
        ((GeneralPath) shape).curveTo(202.10799, 208.47101, 202.53299,
                208.87201, 202.53299, 209.67601);
        ((GeneralPath) shape).lineTo(202.53299, 212.14601);
        ((GeneralPath) shape).lineTo(201.66399, 212.14601);
        ((GeneralPath) shape).lineTo(201.66399, 209.798);
        ((GeneralPath) shape).curveTo(201.66399, 209.373, 201.44798, 209.16,
                201.01599, 209.16);
        ((GeneralPath) shape).curveTo(200.424, 209.16, 200.129, 209.468,
                200.129, 210.08301);
        ((GeneralPath) shape).lineTo(200.129, 212.14601);
        ((GeneralPath) shape).lineTo(199.261, 212.14601);
        ((GeneralPath) shape).lineTo(199.261, 206.95901);
        ((GeneralPath) shape).lineTo(200.129, 206.95901);
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
        ((GeneralPath) shape).moveTo(205.724, 208.513);
        ((GeneralPath) shape).lineTo(205.724, 209.174);
        ((GeneralPath) shape).lineTo(204.328, 209.174);
        ((GeneralPath) shape).lineTo(204.328, 210.998);
        ((GeneralPath) shape).curveTo(204.328, 211.334, 204.455, 211.503,
                204.711, 211.503);
        ((GeneralPath) shape).curveTo(204.992, 211.503, 205.132, 211.3,
                205.132, 210.89201);
        ((GeneralPath) shape).lineTo(205.132, 210.74701);
        ((GeneralPath) shape).lineTo(205.871, 210.74701);
        ((GeneralPath) shape).lineTo(205.871, 210.93001);
        ((GeneralPath) shape).curveTo(205.871, 211.09702, 205.868, 211.24,
                205.856, 211.35901);
        ((GeneralPath) shape).curveTo(205.809, 211.914, 205.39801, 212.19101,
                204.62001, 212.19101);
        ((GeneralPath) shape).curveTo(203.84702, 212.19101, 203.45901,
                211.83601, 203.45901, 211.12302);
        ((GeneralPath) shape).lineTo(203.45901, 209.17401);
        ((GeneralPath) shape).lineTo(202.98901, 209.17401);
        ((GeneralPath) shape).lineTo(202.98901, 208.51302);
        ((GeneralPath) shape).lineTo(203.45901, 208.51302);
        ((GeneralPath) shape).lineTo(203.45901, 207.7);
        ((GeneralPath) shape).lineTo(204.32802, 207.7);
        ((GeneralPath) shape).lineTo(204.32802, 208.513);
        ((GeneralPath) shape).lineTo(205.724, 208.513);
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
        ((GeneralPath) shape).moveTo(215.248, 206.959);
        ((GeneralPath) shape).lineTo(215.248, 212.146);
        ((GeneralPath) shape).lineTo(214.266, 212.146);
        ((GeneralPath) shape).lineTo(214.266, 209.319);
        ((GeneralPath) shape).curveTo(214.266, 209.094, 214.27101, 208.838,
                214.285, 208.551);
        ((GeneralPath) shape).lineTo(214.30501, 208.163);
        ((GeneralPath) shape).lineTo(214.324, 207.78);
        ((GeneralPath) shape).lineTo(214.294, 207.78);
        ((GeneralPath) shape).lineTo(214.17601, 208.14);
        ((GeneralPath) shape).lineTo(214.063, 208.50099);
        ((GeneralPath) shape).curveTo(213.957, 208.82399, 213.875, 209.064,
                213.81601, 209.219);
        ((GeneralPath) shape).lineTo(212.67801, 212.14499);
        ((GeneralPath) shape).lineTo(211.783, 212.14499);
        ((GeneralPath) shape).lineTo(210.634, 209.24199);
        ((GeneralPath) shape).curveTo(210.571, 209.08199, 210.488, 208.84299,
                210.384, 208.52399);
        ((GeneralPath) shape).lineTo(210.266, 208.163);
        ((GeneralPath) shape).lineTo(210.14801, 207.806);
        ((GeneralPath) shape).lineTo(210.11801, 207.806);
        ((GeneralPath) shape).lineTo(210.13701, 208.182);
        ((GeneralPath) shape).lineTo(210.156, 208.56201);
        ((GeneralPath) shape).curveTo(210.171, 208.85402, 210.179, 209.10701,
                210.179, 209.31801);
        ((GeneralPath) shape).lineTo(210.179, 212.145);
        ((GeneralPath) shape).lineTo(209.197, 212.145);
        ((GeneralPath) shape).lineTo(209.197, 206.95801);
        ((GeneralPath) shape).lineTo(210.79701, 206.95801);
        ((GeneralPath) shape).lineTo(211.72202, 209.35901);
        ((GeneralPath) shape).curveTo(211.78502, 209.52602, 211.86801, 209.766,
                211.97202, 210.07701);
        ((GeneralPath) shape).lineTo(212.08502, 210.438);
        ((GeneralPath) shape).lineTo(212.20302, 210.795);
        ((GeneralPath) shape).lineTo(212.23701, 210.795);
        ((GeneralPath) shape).lineTo(212.34702, 210.438);
        ((GeneralPath) shape).lineTo(212.46101, 210.08101);
        ((GeneralPath) shape).curveTo(212.55402, 209.781, 212.63501, 209.54102,
                212.70401, 209.367);
        ((GeneralPath) shape).lineTo(213.61401, 206.95801);
        ((GeneralPath) shape).lineTo(215.248, 206.95801);
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
        ((GeneralPath) shape).moveTo(218.471, 209.953);
        ((GeneralPath) shape).lineTo(218.467, 209.808);
        ((GeneralPath) shape).curveTo(218.467, 209.519, 218.418, 209.333,
                218.317, 209.245);
        ((GeneralPath) shape).curveTo(218.219, 209.15999, 218.001, 209.116,
                217.667, 209.116);
        ((GeneralPath) shape).curveTo(217.345, 209.116, 217.134, 209.16699,
                217.03601, 209.272);
        ((GeneralPath) shape).curveTo(216.93901, 209.375, 216.89001, 209.603,
                216.89001, 209.952);
        ((GeneralPath) shape).lineTo(218.471, 209.952);
        ((GeneralPath) shape).moveTo(218.463, 210.983);
        ((GeneralPath) shape).lineTo(219.336, 210.983);
        ((GeneralPath) shape).lineTo(219.336, 211.123);
        ((GeneralPath) shape).curveTo(219.336, 211.832, 218.805, 212.187,
                217.743, 212.187);
        ((GeneralPath) shape).curveTo(217.022, 212.187, 216.552, 212.065,
                216.329, 211.818);
        ((GeneralPath) shape).curveTo(216.107, 211.573, 215.995, 211.05199,
                215.995, 210.256);
        ((GeneralPath) shape).curveTo(215.995, 209.55, 216.111, 209.075,
                216.344, 208.832);
        ((GeneralPath) shape).curveTo(216.575, 208.589, 217.03099, 208.467,
                217.70499, 208.467);
        ((GeneralPath) shape).curveTo(218.35098, 208.467, 218.78598, 208.58499,
                219.00499, 208.82199);
        ((GeneralPath) shape).curveTo(219.22499, 209.05798, 219.33499, 209.523,
                219.33499, 210.21799);
        ((GeneralPath) shape).lineTo(219.33499, 210.484);
        ((GeneralPath) shape).lineTo(216.881, 210.484);
        ((GeneralPath) shape).curveTo(216.877, 210.564, 216.874, 210.61699,
                216.874, 210.644);
        ((GeneralPath) shape).curveTo(216.874, 211.00099, 216.92899, 211.239,
                217.03899, 211.358);
        ((GeneralPath) shape).curveTo(217.14899, 211.476, 217.36699, 211.537,
                217.69699, 211.537);
        ((GeneralPath) shape).curveTo(218.01599, 211.537, 218.22198, 211.502,
                218.31898, 211.432);
        ((GeneralPath) shape).curveTo(218.414, 211.364, 218.463, 211.214,
                218.463, 210.983);
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
        ((GeneralPath) shape).moveTo(221.582, 209.159);
        ((GeneralPath) shape).curveTo(221.259, 209.159, 221.049, 209.22699,
                220.952, 209.366);
        ((GeneralPath) shape).curveTo(220.857, 209.50299, 220.808, 209.801,
                220.808, 210.257);
        ((GeneralPath) shape).curveTo(220.808, 210.783, 220.856, 211.123,
                220.952, 211.276);
        ((GeneralPath) shape).curveTo(221.047, 211.428, 221.263, 211.504,
                221.597, 211.504);
        ((GeneralPath) shape).curveTo(221.946, 211.504, 222.167, 211.43,
                222.266, 211.28);
        ((GeneralPath) shape).curveTo(222.363, 211.132, 222.412, 210.786,
                222.412, 210.246);
        ((GeneralPath) shape).curveTo(222.412, 209.801, 222.35901, 209.509,
                222.251, 209.368);
        ((GeneralPath) shape).curveTo(222.145, 209.229, 221.92, 209.159,
                221.582, 209.159);
        ((GeneralPath) shape).moveTo(223.284, 206.959);
        ((GeneralPath) shape).lineTo(223.284, 212.146);
        ((GeneralPath) shape).lineTo(222.438, 212.146);
        ((GeneralPath) shape).lineTo(222.46901, 211.56099);
        ((GeneralPath) shape).lineTo(222.45401, 211.55699);
        ((GeneralPath) shape).curveTo(222.285, 211.97899, 221.91501, 212.19199,
                221.34702, 212.19199);
        ((GeneralPath) shape).curveTo(220.77402, 212.19199, 220.39302,
                212.06099, 220.20702, 211.79799);
        ((GeneralPath) shape).curveTo(220.02101, 211.536, 219.92801, 211.00499,
                219.92801, 210.20099);
        ((GeneralPath) shape).curveTo(219.92801, 209.553, 220.02701, 209.10298,
                220.22801, 208.84999);
        ((GeneralPath) shape).curveTo(220.42702, 208.59898, 220.78201,
                208.47198, 221.29301, 208.47198);
        ((GeneralPath) shape).curveTo(221.88402, 208.47198, 222.25201,
                208.65898, 222.40001, 209.03499);
        ((GeneralPath) shape).lineTo(222.41501, 209.03099);
        ((GeneralPath) shape).lineTo(222.41501, 206.95999);
        ((GeneralPath) shape).lineTo(223.284, 206.95999);
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
        ((GeneralPath) shape).moveTo(228.303, 206.959);
        ((GeneralPath) shape).lineTo(228.303, 211.264);
        ((GeneralPath) shape).lineTo(230.73, 211.264);
        ((GeneralPath) shape).lineTo(230.73, 212.146);
        ((GeneralPath) shape).lineTo(227.321, 212.146);
        ((GeneralPath) shape).lineTo(227.321, 206.959);
        ((GeneralPath) shape).lineTo(228.303, 206.959);
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
        ((GeneralPath) shape).moveTo(231.201, 208.513);
        ((GeneralPath) shape).lineTo(232.062, 208.513);
        ((GeneralPath) shape).lineTo(232.027, 209.125);
        ((GeneralPath) shape).lineTo(232.047, 209.129);
        ((GeneralPath) shape).curveTo(232.215, 208.691, 232.594, 208.471,
                233.18399, 208.471);
        ((GeneralPath) shape).curveTo(234.04099, 208.471, 234.469, 208.87,
                234.469, 209.672);
        ((GeneralPath) shape).lineTo(234.469, 212.146);
        ((GeneralPath) shape).lineTo(233.601, 212.146);
        ((GeneralPath) shape).lineTo(233.601, 209.821);
        ((GeneralPath) shape).lineTo(233.582, 209.566);
        ((GeneralPath) shape).curveTo(233.542, 209.29599, 233.33, 209.15999,
                232.945, 209.15999);
        ((GeneralPath) shape).curveTo(232.36101, 209.15999, 232.069, 209.43698,
                232.069, 209.99199);
        ((GeneralPath) shape).lineTo(232.069, 212.146);
        ((GeneralPath) shape).lineTo(231.201, 212.146);
        ((GeneralPath) shape).lineTo(231.201, 208.513);
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
        ((GeneralPath) shape).moveTo(236.848, 209.159);
        ((GeneralPath) shape).curveTo(236.51501, 209.159, 236.3, 209.22499,
                236.205, 209.362);
        ((GeneralPath) shape).curveTo(236.11, 209.497, 236.062, 209.804,
                236.062, 210.287);
        ((GeneralPath) shape).curveTo(236.062, 210.798, 236.109, 211.125,
                236.205, 211.267);
        ((GeneralPath) shape).curveTo(236.3, 211.408, 236.518, 211.48,
                236.85901, 211.48);
        ((GeneralPath) shape).curveTo(237.20001, 211.48, 237.421, 211.40799,
                237.52301, 211.262);
        ((GeneralPath) shape).curveTo(237.62401, 211.11699, 237.675, 210.79399,
                237.675, 210.295);
        ((GeneralPath) shape).curveTo(237.675, 209.816, 237.626, 209.509,
                237.52301, 209.368);
        ((GeneralPath) shape).curveTo(237.422, 209.229, 237.197, 209.159,
                236.848, 209.159);
        ((GeneralPath) shape).moveTo(238.558, 208.513);
        ((GeneralPath) shape).lineTo(238.558, 212.214);
        ((GeneralPath) shape).curveTo(238.558, 212.806, 238.438, 213.213,
                238.2, 213.433);
        ((GeneralPath) shape).curveTo(237.961, 213.65399, 237.521, 213.76399,
                236.87799, 213.76399);
        ((GeneralPath) shape).curveTo(236.256, 213.76399, 235.84099, 213.681,
                235.62999, 213.51399);
        ((GeneralPath) shape).curveTo(235.422, 213.34698, 235.316, 213.01599,
                235.316, 212.52199);
        ((GeneralPath) shape).lineTo(236.157, 212.52199);
        ((GeneralPath) shape).curveTo(236.157, 212.75699, 236.201, 212.90698,
                236.29, 212.97598);
        ((GeneralPath) shape).curveTo(236.377, 213.04198, 236.57399, 213.07698,
                236.88199, 213.07698);
        ((GeneralPath) shape).curveTo(237.42099, 213.07698, 237.68999,
                212.85397, 237.68999, 212.40797);
        ((GeneralPath) shape).lineTo(237.68999, 211.59198);
        ((GeneralPath) shape).lineTo(237.67099, 211.58798);
        ((GeneralPath) shape).curveTo(237.51799, 211.97498, 237.163, 212.16898,
                236.609, 212.16898);
        ((GeneralPath) shape).curveTo(236.06099, 212.16898, 235.68599,
                212.03598, 235.484, 211.76997);
        ((GeneralPath) shape).curveTo(235.282, 211.50397, 235.183, 211.00798,
                235.183, 210.28098);
        ((GeneralPath) shape).curveTo(235.183, 209.59697, 235.283, 209.12598,
                235.484, 208.86397);
        ((GeneralPath) shape).curveTo(235.685, 208.60397, 236.049, 208.47197,
                236.57799, 208.47197);
        ((GeneralPath) shape).curveTo(237.15399, 208.47197, 237.53198,
                208.68497, 237.71199, 209.11397);
        ((GeneralPath) shape).lineTo(237.73099, 209.11397);
        ((GeneralPath) shape).lineTo(237.68898, 208.51396);
        ((GeneralPath) shape).lineTo(238.558, 208.51396);
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
        ((GeneralPath) shape).moveTo(155.346, 136.323);
        ((GeneralPath) shape).lineTo(155.346, 140.627);
        ((GeneralPath) shape).lineTo(154.364, 140.627);
        ((GeneralPath) shape).lineTo(154.364, 136.323);
        ((GeneralPath) shape).lineTo(152.87, 136.323);
        ((GeneralPath) shape).lineTo(152.87, 135.441);
        ((GeneralPath) shape).lineTo(156.893, 135.441);
        ((GeneralPath) shape).lineTo(156.893, 136.323);
        ((GeneralPath) shape).lineTo(155.346, 136.323);
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
        ((GeneralPath) shape).moveTo(158.949, 137.641);
        ((GeneralPath) shape).curveTo(158.589, 137.641, 158.358, 137.70601,
                158.259, 137.837);
        ((GeneralPath) shape).curveTo(158.161, 137.966, 158.11101, 138.272,
                158.11101, 138.751);
        ((GeneralPath) shape).curveTo(158.11101, 139.298, 158.15701, 139.64001,
                158.25201, 139.77701);
        ((GeneralPath) shape).curveTo(158.34502, 139.914, 158.57802, 139.98201,
                158.95302, 139.98201);
        ((GeneralPath) shape).curveTo(159.31302, 139.98201, 159.54301, 139.91,
                159.63802, 139.76501);
        ((GeneralPath) shape).curveTo(159.73302, 139.62003, 159.78001,
                139.27501, 159.78001, 138.72401);
        ((GeneralPath) shape).curveTo(159.78001, 138.26102, 159.73102,
                137.96501, 159.63301, 137.835);
        ((GeneralPath) shape).curveTo(159.533, 137.706, 159.306, 137.641,
                158.949, 137.641);
        ((GeneralPath) shape).moveTo(158.957, 136.953);
        ((GeneralPath) shape).curveTo(159.657, 136.953, 160.115, 137.065,
                160.33301, 137.289);
        ((GeneralPath) shape).curveTo(160.54901, 137.513, 160.65901, 137.98601,
                160.65901, 138.70801);
        ((GeneralPath) shape).curveTo(160.65901, 139.513, 160.55301, 140.041,
                160.34001, 140.292);
        ((GeneralPath) shape).curveTo(160.12701, 140.54301, 159.68001,
                140.66801, 158.99802, 140.66801);
        ((GeneralPath) shape).curveTo(158.25902, 140.66801, 157.77902,
                140.55202, 157.55902, 140.31801);
        ((GeneralPath) shape).curveTo(157.34102, 140.087, 157.23102, 139.57301,
                157.23102, 138.779);
        ((GeneralPath) shape).curveTo(157.23102, 138.01701, 157.33702,
                137.52101, 157.55302, 137.29301);
        ((GeneralPath) shape).curveTo(157.768, 137.067, 158.236, 136.953,
                158.957, 136.953);
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
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(161.387, 136.995);
        ((GeneralPath) shape).lineTo(162.247, 136.995);
        ((GeneralPath) shape).lineTo(162.213, 137.607);
        ((GeneralPath) shape).lineTo(162.232, 137.611);
        ((GeneralPath) shape).curveTo(162.4, 137.174, 162.78, 136.95299,
                163.37, 136.95299);
        ((GeneralPath) shape).curveTo(164.22699, 136.95299, 164.655, 137.35199,
                164.655, 138.15399);
        ((GeneralPath) shape).lineTo(164.655, 140.627);
        ((GeneralPath) shape).lineTo(163.787, 140.627);
        ((GeneralPath) shape).lineTo(163.787, 138.302);
        ((GeneralPath) shape).lineTo(163.768, 138.047);
        ((GeneralPath) shape).curveTo(163.72801, 137.777, 163.516, 137.64099,
                163.13101, 137.64099);
        ((GeneralPath) shape).curveTo(162.54802, 137.64099, 162.25601,
                137.91798, 162.25601, 138.47299);
        ((GeneralPath) shape).lineTo(162.25601, 140.627);
        ((GeneralPath) shape).lineTo(161.38802, 140.627);
        ((GeneralPath) shape).lineTo(161.38802, 136.995);
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
        ((GeneralPath) shape).moveTo(165.502, 136.995);
        ((GeneralPath) shape).lineTo(166.36299, 136.995);
        ((GeneralPath) shape).lineTo(166.329, 137.607);
        ((GeneralPath) shape).lineTo(166.34799, 137.611);
        ((GeneralPath) shape).curveTo(166.51599, 137.174, 166.896, 136.95299,
                167.48499, 136.95299);
        ((GeneralPath) shape).curveTo(168.34198, 136.95299, 168.76999,
                137.35199, 168.76999, 138.15399);
        ((GeneralPath) shape).lineTo(168.76999, 140.627);
        ((GeneralPath) shape).lineTo(167.902, 140.627);
        ((GeneralPath) shape).lineTo(167.902, 138.302);
        ((GeneralPath) shape).lineTo(167.88199, 138.047);
        ((GeneralPath) shape).curveTo(167.842, 137.777, 167.62999, 137.64099,
                167.245, 137.64099);
        ((GeneralPath) shape).curveTo(166.662, 137.64099, 166.36899, 137.91798,
                166.36899, 138.47299);
        ((GeneralPath) shape).lineTo(166.36899, 140.627);
        ((GeneralPath) shape).lineTo(165.50099, 140.627);
        ((GeneralPath) shape).lineTo(165.50099, 136.995);
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
        ((GeneralPath) shape).moveTo(171.1, 139.001);
        ((GeneralPath) shape).curveTo(170.61101, 139.001, 170.365, 139.17001,
                170.365, 139.51001);
        ((GeneralPath) shape).curveTo(170.365, 139.746, 170.414, 139.89902,
                170.516, 139.973);
        ((GeneralPath) shape).curveTo(170.617, 140.04501, 170.82901, 140.08301,
                171.153, 140.08301);
        ((GeneralPath) shape).curveTo(171.68, 140.08301, 171.945, 139.904,
                171.945, 139.54701);
        ((GeneralPath) shape).curveTo(171.945, 139.184, 171.665, 139.001,
                171.1, 139.001);
        ((GeneralPath) shape).moveTo(170.52, 138.047);
        ((GeneralPath) shape).lineTo(169.63301, 138.047);
        ((GeneralPath) shape).curveTo(169.63301, 137.612, 169.73401, 137.321,
                169.936, 137.173);
        ((GeneralPath) shape).curveTo(170.13701, 137.02701, 170.539, 136.95201,
                171.138, 136.95201);
        ((GeneralPath) shape).curveTo(171.79, 136.95201, 172.23, 137.04102,
                172.461, 137.22202);
        ((GeneralPath) shape).curveTo(172.69, 137.40001, 172.806, 137.74402,
                172.806, 138.25102);
        ((GeneralPath) shape).lineTo(172.806, 140.62602);
        ((GeneralPath) shape).lineTo(171.938, 140.62602);
        ((GeneralPath) shape).lineTo(171.98001, 140.12802);
        ((GeneralPath) shape).lineTo(171.95702, 140.12502);
        ((GeneralPath) shape).curveTo(171.79001, 140.48601, 171.40501,
                140.66801, 170.80101, 140.66801);
        ((GeneralPath) shape).curveTo(169.925, 140.66801, 169.48502, 140.29602,
                169.48502, 139.54701);
        ((GeneralPath) shape).curveTo(169.48502, 138.79301, 169.93202,
                138.41501, 170.83101, 138.41501);
        ((GeneralPath) shape).curveTo(171.43001, 138.41501, 171.794, 138.552,
                171.923, 138.83);
        ((GeneralPath) shape).lineTo(171.938, 138.83);
        ((GeneralPath) shape).lineTo(171.938, 138.241);
        ((GeneralPath) shape).curveTo(171.938, 137.958, 171.888, 137.77,
                171.79001, 137.677);
        ((GeneralPath) shape).curveTo(171.692, 137.586, 171.49101, 137.538,
                171.184, 137.538);
        ((GeneralPath) shape).curveTo(170.742, 137.538, 170.52, 137.708,
                170.52, 138.047);
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
        ((GeneralPath) shape).moveTo(175.203, 137.641);
        ((GeneralPath) shape).curveTo(174.87001, 137.641, 174.655, 137.707,
                174.56, 137.84401);
        ((GeneralPath) shape).curveTo(174.465, 137.979, 174.418, 138.287,
                174.418, 138.76901);
        ((GeneralPath) shape).curveTo(174.418, 139.28001, 174.465, 139.60701,
                174.56, 139.74901);
        ((GeneralPath) shape).curveTo(174.655, 139.89001, 174.873, 139.962,
                175.214, 139.962);
        ((GeneralPath) shape).curveTo(175.55501, 139.962, 175.776, 139.89,
                175.878, 139.744);
        ((GeneralPath) shape).curveTo(175.979, 139.599, 176.03, 139.276,
                176.03, 138.77701);
        ((GeneralPath) shape).curveTo(176.03, 138.298, 175.981, 137.99101,
                175.878, 137.85);
        ((GeneralPath) shape).curveTo(175.777, 137.711, 175.552, 137.641,
                175.203, 137.641);
        ((GeneralPath) shape).moveTo(176.913, 136.995);
        ((GeneralPath) shape).lineTo(176.913, 140.696);
        ((GeneralPath) shape).curveTo(176.913, 141.288, 176.79399, 141.69499,
                176.555, 141.915);
        ((GeneralPath) shape).curveTo(176.316, 142.13599, 175.87599, 142.24599,
                175.234, 142.24599);
        ((GeneralPath) shape).curveTo(174.612, 142.24599, 174.19699, 142.163,
                173.986, 141.99599);
        ((GeneralPath) shape).curveTo(173.778, 141.82898, 173.67099, 141.49799,
                173.67099, 141.00398);
        ((GeneralPath) shape).lineTo(174.51299, 141.00398);
        ((GeneralPath) shape).curveTo(174.51299, 141.23898, 174.55598,
                141.38898, 174.64598, 141.45798);
        ((GeneralPath) shape).curveTo(174.73299, 141.52397, 174.92998,
                141.55898, 175.23698, 141.55898);
        ((GeneralPath) shape).curveTo(175.77599, 141.55898, 176.04498,
                141.33698, 176.04498, 140.88997);
        ((GeneralPath) shape).lineTo(176.04498, 140.07397);
        ((GeneralPath) shape).lineTo(176.02599, 140.06998);
        ((GeneralPath) shape).curveTo(175.87299, 140.45697, 175.51799,
                140.65097, 174.96399, 140.65097);
        ((GeneralPath) shape).curveTo(174.41599, 140.65097, 174.04099,
                140.51797, 173.84, 140.25197);
        ((GeneralPath) shape).curveTo(173.63899, 139.98596, 173.538, 139.48997,
                173.538, 138.76297);
        ((GeneralPath) shape).curveTo(173.538, 138.07896, 173.63899, 137.60797,
                173.84, 137.34596);
        ((GeneralPath) shape).curveTo(174.041, 137.08597, 174.40399, 136.95396,
                174.93399, 136.95396);
        ((GeneralPath) shape).curveTo(175.51, 136.95396, 175.88799, 137.16696,
                176.068, 137.59697);
        ((GeneralPath) shape).lineTo(176.08699, 137.59697);
        ((GeneralPath) shape).lineTo(176.04498, 136.99597);
        ((GeneralPath) shape).lineTo(176.913, 136.99597);
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
        ((GeneralPath) shape).moveTo(180.107, 138.435);
        ((GeneralPath) shape).lineTo(180.103, 138.29);
        ((GeneralPath) shape).curveTo(180.103, 138.00099, 180.054, 137.81499,
                179.953, 137.728);
        ((GeneralPath) shape).curveTo(179.854, 137.64299, 179.63701, 137.599,
                179.30301, 137.599);
        ((GeneralPath) shape).curveTo(178.98001, 137.599, 178.77, 137.65,
                178.671, 137.754);
        ((GeneralPath) shape).curveTo(178.57501, 137.857, 178.52501, 138.08499,
                178.52501, 138.43399);
        ((GeneralPath) shape).lineTo(180.107, 138.43399);
        ((GeneralPath) shape).moveTo(180.099, 139.465);
        ((GeneralPath) shape).lineTo(180.972, 139.465);
        ((GeneralPath) shape).lineTo(180.972, 139.605);
        ((GeneralPath) shape).curveTo(180.972, 140.314, 180.441, 140.66899,
                179.379, 140.66899);
        ((GeneralPath) shape).curveTo(178.658, 140.66899, 178.188, 140.547,
                177.964, 140.301);
        ((GeneralPath) shape).curveTo(177.742, 140.056, 177.63101, 139.53499,
                177.63101, 138.739);
        ((GeneralPath) shape).curveTo(177.63101, 138.033, 177.74701, 137.558,
                177.98001, 137.315);
        ((GeneralPath) shape).curveTo(178.21101, 137.072, 178.667, 136.95,
                179.341, 136.95);
        ((GeneralPath) shape).curveTo(179.987, 136.95, 180.422, 137.068,
                180.642, 137.305);
        ((GeneralPath) shape).curveTo(180.862, 137.54099, 180.972, 138.006,
                180.972, 138.70099);
        ((GeneralPath) shape).lineTo(180.972, 138.967);
        ((GeneralPath) shape).lineTo(178.518, 138.967);
        ((GeneralPath) shape).curveTo(178.514, 139.047, 178.511, 139.09999,
                178.511, 139.127);
        ((GeneralPath) shape).curveTo(178.511, 139.483, 178.566, 139.721,
                178.676, 139.841);
        ((GeneralPath) shape).curveTo(178.78499, 139.959, 179.004, 140.02,
                179.333, 140.02);
        ((GeneralPath) shape).curveTo(179.652, 140.02, 179.85799, 139.985,
                179.95499, 139.916);
        ((GeneralPath) shape).curveTo(180.05, 139.847, 180.099, 139.696,
                180.099, 139.465);
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
        ((GeneralPath) shape).moveTo(182.523, 136.995);
        ((GeneralPath) shape).lineTo(182.523, 137.93);
        ((GeneralPath) shape).lineTo(181.655, 137.93);
        ((GeneralPath) shape).lineTo(181.655, 136.995);
        ((GeneralPath) shape).lineTo(182.523, 136.995);
        ((GeneralPath) shape).moveTo(182.523, 139.692);
        ((GeneralPath) shape).lineTo(182.523, 140.627);
        ((GeneralPath) shape).lineTo(181.655, 140.627);
        ((GeneralPath) shape).lineTo(181.655, 139.692);
        ((GeneralPath) shape).lineTo(182.523, 139.692);
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
        ((GeneralPath) shape).moveTo(53.458, 167.198);
        ((GeneralPath) shape).lineTo(53.458, 172.384);
        ((GeneralPath) shape).lineTo(52.476, 172.384);
        ((GeneralPath) shape).lineTo(52.476, 169.557);
        ((GeneralPath) shape).curveTo(52.476, 169.33301, 52.481003, 169.07701,
                52.495003, 168.789);
        ((GeneralPath) shape).lineTo(52.514004, 168.40201);
        ((GeneralPath) shape).lineTo(52.533005, 168.018);
        ((GeneralPath) shape).lineTo(52.503006, 168.018);
        ((GeneralPath) shape).lineTo(52.385006, 168.379);
        ((GeneralPath) shape).lineTo(52.271008, 168.73999);
        ((GeneralPath) shape).curveTo(52.16501, 169.06299, 52.083008, 169.303,
                52.024006, 169.458);
        ((GeneralPath) shape).lineTo(50.887005, 172.383);
        ((GeneralPath) shape).lineTo(49.992004, 172.383);
        ((GeneralPath) shape).lineTo(48.843006, 169.48);
        ((GeneralPath) shape).curveTo(48.780006, 169.321, 48.697006, 169.082,
                48.593006, 168.762);
        ((GeneralPath) shape).lineTo(48.475006, 168.401);
        ((GeneralPath) shape).lineTo(48.357006, 168.044);
        ((GeneralPath) shape).lineTo(48.327007, 168.044);
        ((GeneralPath) shape).lineTo(48.34601, 168.42001);
        ((GeneralPath) shape).lineTo(48.36501, 168.80002);
        ((GeneralPath) shape).curveTo(48.38001, 169.09203, 48.38801, 169.34502,
                48.38801, 169.55602);
        ((GeneralPath) shape).lineTo(48.38801, 172.38301);
        ((GeneralPath) shape).lineTo(47.40601, 172.38301);
        ((GeneralPath) shape).lineTo(47.40601, 167.197);
        ((GeneralPath) shape).lineTo(49.00601, 167.197);
        ((GeneralPath) shape).lineTo(49.931007, 169.598);
        ((GeneralPath) shape).curveTo(49.994007, 169.76501, 50.077007,
                170.00401, 50.181007, 170.31601);
        ((GeneralPath) shape).lineTo(50.295006, 170.677);
        ((GeneralPath) shape).lineTo(50.413006, 171.033);
        ((GeneralPath) shape).lineTo(50.447006, 171.033);
        ((GeneralPath) shape).lineTo(50.557007, 170.677);
        ((GeneralPath) shape).lineTo(50.671005, 170.32);
        ((GeneralPath) shape).curveTo(50.764004, 170.02, 50.845005, 169.78001,
                50.913006, 169.606);
        ((GeneralPath) shape).lineTo(51.823006, 167.197);
        ((GeneralPath) shape).lineTo(53.458, 167.197);
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
        ((GeneralPath) shape).moveTo(55.923, 169.398);
        ((GeneralPath) shape).curveTo(55.563, 169.398, 55.331, 169.463, 55.233,
                169.594);
        ((GeneralPath) shape).curveTo(55.134003, 169.72299, 55.085003,
                170.02899, 55.085003, 170.508);
        ((GeneralPath) shape).curveTo(55.085003, 171.055, 55.13, 171.397,
                55.226, 171.534);
        ((GeneralPath) shape).curveTo(55.319, 171.67099, 55.552002, 171.739,
                55.927002, 171.739);
        ((GeneralPath) shape).curveTo(56.287003, 171.739, 56.517002, 171.66699,
                56.611, 171.522);
        ((GeneralPath) shape).curveTo(56.706, 171.378, 56.754, 171.032, 56.754,
                170.481);
        ((GeneralPath) shape).curveTo(56.754, 170.018, 56.704002, 169.722,
                56.606003, 169.592);
        ((GeneralPath) shape).curveTo(56.507, 169.463, 56.279, 169.398, 55.923,
                169.398);
        ((GeneralPath) shape).moveTo(55.931, 168.71);
        ((GeneralPath) shape).curveTo(56.631, 168.71, 57.089, 168.822, 57.307,
                169.04701);
        ((GeneralPath) shape).curveTo(57.523, 169.27101, 57.633, 169.74402,
                57.633, 170.46602);
        ((GeneralPath) shape).curveTo(57.633, 171.27101, 57.527, 171.79901,
                57.315, 172.05002);
        ((GeneralPath) shape).curveTo(57.102997, 172.30103, 56.655, 172.42603,
                55.973, 172.42603);
        ((GeneralPath) shape).curveTo(55.234, 172.42603, 54.753998, 172.31003,
                54.534, 172.07602);
        ((GeneralPath) shape).curveTo(54.316, 171.84502, 54.206, 171.33102,
                54.206, 170.53702);
        ((GeneralPath) shape).curveTo(54.206, 169.77602, 54.312, 169.28001,
                54.529003, 169.05202);
        ((GeneralPath) shape).curveTo(54.742, 168.824, 55.21, 168.71, 55.931,
                168.71);
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
        ((GeneralPath) shape).moveTo(61.382, 168.752);
        ((GeneralPath) shape).lineTo(60.343, 172.384);
        ((GeneralPath) shape).lineTo(58.986, 172.384);
        ((GeneralPath) shape).lineTo(57.894, 168.752);
        ((GeneralPath) shape).lineTo(58.815002, 168.752);
        ((GeneralPath) shape).lineTo(59.293003, 170.428);
        ((GeneralPath) shape).curveTo(59.357002, 170.659, 59.418003, 170.88599,
                59.475002, 171.10399);
        ((GeneralPath) shape).lineTo(59.562004, 171.44199);
        ((GeneralPath) shape).lineTo(59.649006, 171.77998);
        ((GeneralPath) shape).lineTo(59.668007, 171.77998);
        ((GeneralPath) shape).lineTo(59.74801, 171.44199);
        ((GeneralPath) shape).lineTo(59.82801, 171.10799);
        ((GeneralPath) shape).curveTo(59.88901, 170.85599, 59.94601, 170.62898,
                59.99801, 170.43199);
        ((GeneralPath) shape).lineTo(60.44601, 168.752);
        ((GeneralPath) shape).lineTo(61.382, 168.752);
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
        ((GeneralPath) shape).moveTo(64.113, 170.192);
        ((GeneralPath) shape).lineTo(64.109, 170.047);
        ((GeneralPath) shape).curveTo(64.109, 169.759, 64.06, 169.57199,
                63.959, 169.485);
        ((GeneralPath) shape).curveTo(63.86, 169.4, 63.642998, 169.356, 63.309,
                169.356);
        ((GeneralPath) shape).curveTo(62.987, 169.356, 62.775997, 169.407,
                62.677998, 169.51201);
        ((GeneralPath) shape).curveTo(62.580997, 169.615, 62.531998, 169.843,
                62.531998, 170.192);
        ((GeneralPath) shape).lineTo(64.113, 170.192);
        ((GeneralPath) shape).moveTo(64.105, 171.222);
        ((GeneralPath) shape).lineTo(64.978004, 171.222);
        ((GeneralPath) shape).lineTo(64.978004, 171.363);
        ((GeneralPath) shape).curveTo(64.978004, 172.071, 64.44701, 172.42601,
                63.385006, 172.42601);
        ((GeneralPath) shape).curveTo(62.664005, 172.42601, 62.194008,
                172.30402, 61.971004, 172.05801);
        ((GeneralPath) shape).curveTo(61.749004, 171.81302, 61.637005, 171.292,
                61.637005, 170.49602);
        ((GeneralPath) shape).curveTo(61.637005, 169.78902, 61.753006,
                169.31401, 61.986004, 169.07101);
        ((GeneralPath) shape).curveTo(62.217003, 168.82802, 62.672005,
                168.70601, 63.347004, 168.70601);
        ((GeneralPath) shape).curveTo(63.993004, 168.70601, 64.428, 168.824,
                64.647, 169.061);
        ((GeneralPath) shape).curveTo(64.867004, 169.296, 64.977005, 169.76201,
                64.977005, 170.457);
        ((GeneralPath) shape).lineTo(64.977005, 170.723);
        ((GeneralPath) shape).lineTo(62.523006, 170.723);
        ((GeneralPath) shape).curveTo(62.519005, 170.80301, 62.516006, 170.856,
                62.516006, 170.88301);
        ((GeneralPath) shape).curveTo(62.516006, 171.23901, 62.571007, 171.477,
                62.681007, 171.59702);
        ((GeneralPath) shape).curveTo(62.791008, 171.71501, 63.009007,
                171.77602, 63.33901, 171.77602);
        ((GeneralPath) shape).curveTo(63.65701, 171.77602, 63.86401, 171.74202,
                63.96101, 171.67201);
        ((GeneralPath) shape).curveTo(64.056, 171.604, 64.105, 171.453, 64.105,
                171.222);
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
        ((GeneralPath) shape).moveTo(65.699, 168.752);
        ((GeneralPath) shape).lineTo(66.56699, 168.752);
        ((GeneralPath) shape).lineTo(66.54399, 169.311);
        ((GeneralPath) shape).lineTo(66.56399, 169.315);
        ((GeneralPath) shape).curveTo(66.73999, 168.912, 67.108986, 168.711,
                67.66999, 168.711);
        ((GeneralPath) shape).curveTo(68.32399, 168.711, 68.68899, 168.936,
                68.76199, 169.384);
        ((GeneralPath) shape).lineTo(68.77699, 169.384);
        ((GeneralPath) shape).curveTo(68.94599, 168.936, 69.31899, 168.711,
                69.895996, 168.711);
        ((GeneralPath) shape).curveTo(70.73399, 168.711, 71.155, 169.133,
                71.155, 169.981);
        ((GeneralPath) shape).lineTo(71.155, 172.386);
        ((GeneralPath) shape).lineTo(70.285995, 172.386);
        ((GeneralPath) shape).lineTo(70.285995, 170.171);
        ((GeneralPath) shape).curveTo(70.285995, 169.658, 70.076, 169.40001,
                69.65299, 169.40001);
        ((GeneralPath) shape).curveTo(69.12599, 169.40001, 68.86099, 169.68701,
                68.86099, 170.26302);
        ((GeneralPath) shape).lineTo(68.86099, 172.38701);
        ((GeneralPath) shape).lineTo(67.993, 172.38701);
        ((GeneralPath) shape).lineTo(67.993, 170.13802);
        ((GeneralPath) shape).curveTo(67.993, 169.83801, 67.952995, 169.63802,
                67.87299, 169.54301);
        ((GeneralPath) shape).curveTo(67.79299, 169.44801, 67.62499, 169.40001,
                67.367, 169.40001);
        ((GeneralPath) shape).curveTo(66.834, 169.40001, 66.56699, 169.69301,
                66.56699, 170.281);
        ((GeneralPath) shape).lineTo(66.56699, 172.386);
        ((GeneralPath) shape).lineTo(65.699, 172.386);
        ((GeneralPath) shape).lineTo(65.699, 168.752);
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
        ((GeneralPath) shape).moveTo(74.387, 170.192);
        ((GeneralPath) shape).lineTo(74.383, 170.047);
        ((GeneralPath) shape).curveTo(74.383, 169.759, 74.334, 169.57199,
                74.233, 169.485);
        ((GeneralPath) shape).curveTo(74.134, 169.4, 73.917, 169.356, 73.583,
                169.356);
        ((GeneralPath) shape).curveTo(73.261, 169.356, 73.05, 169.407, 72.952,
                169.51201);
        ((GeneralPath) shape).curveTo(72.855, 169.615, 72.806, 169.843, 72.806,
                170.192);
        ((GeneralPath) shape).lineTo(74.387, 170.192);
        ((GeneralPath) shape).moveTo(74.378, 171.222);
        ((GeneralPath) shape).lineTo(75.251, 171.222);
        ((GeneralPath) shape).lineTo(75.251, 171.363);
        ((GeneralPath) shape).curveTo(75.251, 172.071, 74.72, 172.42601,
                73.659, 172.42601);
        ((GeneralPath) shape).curveTo(72.937996, 172.42601, 72.467995,
                172.30402, 72.243996, 172.05801);
        ((GeneralPath) shape).curveTo(72.021996, 171.81302, 71.910995, 171.292,
                71.910995, 170.49602);
        ((GeneralPath) shape).curveTo(71.910995, 169.78902, 72.02599,
                169.31401, 72.259995, 169.07101);
        ((GeneralPath) shape).curveTo(72.491, 168.82802, 72.94699, 168.70601,
                73.620995, 168.70601);
        ((GeneralPath) shape).curveTo(74.267, 168.70601, 74.701996, 168.824,
                74.921, 169.061);
        ((GeneralPath) shape).curveTo(75.141, 169.296, 75.251, 169.76201,
                75.251, 170.457);
        ((GeneralPath) shape).lineTo(75.251, 170.723);
        ((GeneralPath) shape).lineTo(72.798, 170.723);
        ((GeneralPath) shape).curveTo(72.794, 170.80301, 72.78999, 170.856,
                72.78999, 170.88301);
        ((GeneralPath) shape).curveTo(72.78999, 171.23901, 72.84499, 171.477,
                72.954994, 171.59702);
        ((GeneralPath) shape).curveTo(73.064995, 171.71501, 73.283, 171.77602,
                73.61299, 171.77602);
        ((GeneralPath) shape).curveTo(73.93199, 171.77602, 74.13799, 171.74202,
                74.23499, 171.67201);
        ((GeneralPath) shape).curveTo(74.33, 171.604, 74.378, 171.453, 74.378,
                171.222);
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
        ((GeneralPath) shape).moveTo(75.972, 168.752);
        ((GeneralPath) shape).lineTo(76.833, 168.752);
        ((GeneralPath) shape).lineTo(76.799, 169.364);
        ((GeneralPath) shape).lineTo(76.818, 169.368);
        ((GeneralPath) shape).curveTo(76.986, 168.931, 77.366, 168.70999,
                77.955, 168.70999);
        ((GeneralPath) shape).curveTo(78.812004, 168.70999, 79.240005, 169.109,
                79.240005, 169.911);
        ((GeneralPath) shape).lineTo(79.240005, 172.384);
        ((GeneralPath) shape).lineTo(78.37201, 172.384);
        ((GeneralPath) shape).lineTo(78.37201, 170.059);
        ((GeneralPath) shape).lineTo(78.35201, 169.804);
        ((GeneralPath) shape).curveTo(78.31201, 169.534, 78.10001, 169.398,
                77.71501, 169.398);
        ((GeneralPath) shape).curveTo(77.13201, 169.398, 76.83901, 169.67499,
                76.83901, 170.23099);
        ((GeneralPath) shape).lineTo(76.83901, 172.385);
        ((GeneralPath) shape).lineTo(75.971016, 172.385);
        ((GeneralPath) shape).lineTo(75.971016, 168.752);
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
        ((GeneralPath) shape).moveTo(82.427, 168.752);
        ((GeneralPath) shape).lineTo(82.427, 169.413);
        ((GeneralPath) shape).lineTo(81.031, 169.413);
        ((GeneralPath) shape).lineTo(81.031, 171.237);
        ((GeneralPath) shape).curveTo(81.031, 171.573, 81.158, 171.742, 81.414,
                171.742);
        ((GeneralPath) shape).curveTo(81.695, 171.742, 81.835, 171.539, 81.835,
                171.13);
        ((GeneralPath) shape).lineTo(81.835, 170.98601);
        ((GeneralPath) shape).lineTo(82.574, 170.98601);
        ((GeneralPath) shape).lineTo(82.574, 171.16801);
        ((GeneralPath) shape).curveTo(82.574, 171.33502, 82.57, 171.47801,
                82.559, 171.59702);
        ((GeneralPath) shape).curveTo(82.512, 172.15201, 82.1, 172.42902,
                81.323, 172.42902);
        ((GeneralPath) shape).curveTo(80.549995, 172.42902, 80.162994,
                172.07402, 80.162994, 171.36201);
        ((GeneralPath) shape).lineTo(80.162994, 169.41301);
        ((GeneralPath) shape).lineTo(79.69299, 169.41301);
        ((GeneralPath) shape).lineTo(79.69299, 168.75201);
        ((GeneralPath) shape).lineTo(80.162994, 168.75201);
        ((GeneralPath) shape).lineTo(80.162994, 167.93901);
        ((GeneralPath) shape).lineTo(81.03099, 167.93901);
        ((GeneralPath) shape).lineTo(81.03099, 168.75201);
        ((GeneralPath) shape).lineTo(82.427, 168.75201);
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
        ((GeneralPath) shape).moveTo(87.085, 168.08);
        ((GeneralPath) shape).lineTo(87.085, 172.384);
        ((GeneralPath) shape).lineTo(86.103, 172.384);
        ((GeneralPath) shape).lineTo(86.103, 168.08);
        ((GeneralPath) shape).lineTo(84.609, 168.08);
        ((GeneralPath) shape).lineTo(84.609, 167.198);
        ((GeneralPath) shape).lineTo(88.632, 167.198);
        ((GeneralPath) shape).lineTo(88.632, 168.08);
        ((GeneralPath) shape).lineTo(87.085, 168.08);
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
        ((GeneralPath) shape).moveTo(92.087, 168.752);
        ((GeneralPath) shape).lineTo(91.134995, 172.612);
        ((GeneralPath) shape).curveTo(91.00199, 173.159, 90.83499, 173.526,
                90.635994, 173.716);
        ((GeneralPath) shape).curveTo(90.438995, 173.904, 90.119995, 173.99901,
                89.68199, 173.99901);
        ((GeneralPath) shape).curveTo(89.58299, 173.99901, 89.480995,
                173.99501, 89.37399, 173.983);
        ((GeneralPath) shape).lineTo(89.37399, 173.341);
        ((GeneralPath) shape).curveTo(89.44999, 173.34401, 89.51299, 173.348,
                89.563995, 173.348);
        ((GeneralPath) shape).curveTo(89.93199, 173.348, 90.16699, 173.02701,
                90.26499, 172.38301);
        ((GeneralPath) shape).lineTo(89.82199, 172.38301);
        ((GeneralPath) shape).lineTo(88.634995, 168.751);
        ((GeneralPath) shape).lineTo(89.56799, 168.751);
        ((GeneralPath) shape).lineTo(90.022995, 170.29001);
        ((GeneralPath) shape).lineTo(90.25099, 171.061);
        ((GeneralPath) shape).curveTo(90.26299, 171.11101, 90.29799, 171.239,
                90.356995, 171.449);
        ((GeneralPath) shape).lineTo(90.466995, 171.832);
        ((GeneralPath) shape).lineTo(90.48599, 171.832);
        ((GeneralPath) shape).lineTo(90.565994, 171.449);
        ((GeneralPath) shape).curveTo(90.605995, 171.251, 90.631996, 171.12201,
                90.645996, 171.061);
        ((GeneralPath) shape).lineTo(90.824, 170.29001);
        ((GeneralPath) shape).lineTo(91.173, 168.751);
        ((GeneralPath) shape).lineTo(92.087, 168.751);
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
        ((GeneralPath) shape).moveTo(94.191, 169.395);
        ((GeneralPath) shape).curveTo(93.861, 169.395, 93.645004, 169.46901,
                93.543, 169.619);
        ((GeneralPath) shape).curveTo(93.442, 169.767, 93.391, 170.085, 93.391,
                170.569);
        ((GeneralPath) shape).curveTo(93.391, 171.04, 93.444, 171.351, 93.554,
                171.507);
        ((GeneralPath) shape).curveTo(93.662, 171.66101, 93.884, 171.739,
                94.218, 171.739);
        ((GeneralPath) shape).curveTo(94.555, 171.739, 94.775, 171.66699,
                94.878006, 171.519);
        ((GeneralPath) shape).curveTo(94.979004, 171.373, 95.03001, 171.05699,
                95.03001, 170.573);
        ((GeneralPath) shape).curveTo(95.03001, 170.077, 94.979004, 169.756,
                94.87601, 169.612);
        ((GeneralPath) shape).curveTo(94.773, 169.466, 94.546, 169.395, 94.191,
                169.395);
        ((GeneralPath) shape).moveTo(92.481, 168.752);
        ((GeneralPath) shape).lineTo(93.364006, 168.752);
        ((GeneralPath) shape).lineTo(93.33001, 169.29199);
        ((GeneralPath) shape).lineTo(93.34901, 169.29599);
        ((GeneralPath) shape).curveTo(93.55701, 168.90498, 93.948006,
                168.70699, 94.520004, 168.70699);
        ((GeneralPath) shape).curveTo(95.047005, 168.70699, 95.411, 168.83798,
                95.61, 169.09999);
        ((GeneralPath) shape).curveTo(95.807, 169.36198, 95.908, 169.83899,
                95.908, 170.534);
        ((GeneralPath) shape).curveTo(95.908, 171.258, 95.81, 171.754, 95.612,
                172.023);
        ((GeneralPath) shape).curveTo(95.415, 172.291, 95.051, 172.426, 94.516,
                172.426);
        ((GeneralPath) shape).curveTo(93.947, 172.426, 93.576, 172.236, 93.401,
                171.857);
        ((GeneralPath) shape).lineTo(93.386, 171.857);
        ((GeneralPath) shape).lineTo(93.386, 173.942);
        ((GeneralPath) shape).lineTo(92.48, 173.942);
        ((GeneralPath) shape).lineTo(92.48, 168.752);
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
        ((GeneralPath) shape).moveTo(98.979, 170.192);
        ((GeneralPath) shape).lineTo(98.975, 170.047);
        ((GeneralPath) shape).curveTo(98.975, 169.759, 98.925995, 169.57199,
                98.825, 169.485);
        ((GeneralPath) shape).curveTo(98.726, 169.4, 98.508995, 169.356,
                98.174995, 169.356);
        ((GeneralPath) shape).curveTo(97.852, 169.356, 97.642, 169.407,
                97.54299, 169.51201);
        ((GeneralPath) shape).curveTo(97.44699, 169.615, 97.39699, 169.843,
                97.39699, 170.192);
        ((GeneralPath) shape).lineTo(98.979, 170.192);
        ((GeneralPath) shape).moveTo(98.971, 171.222);
        ((GeneralPath) shape).lineTo(99.844, 171.222);
        ((GeneralPath) shape).lineTo(99.844, 171.363);
        ((GeneralPath) shape).curveTo(99.844, 172.071, 99.313, 172.42601,
                98.251, 172.42601);
        ((GeneralPath) shape).curveTo(97.53, 172.42601, 97.06, 172.30402,
                96.836, 172.05801);
        ((GeneralPath) shape).curveTo(96.614, 171.81302, 96.503, 171.292,
                96.503, 170.49602);
        ((GeneralPath) shape).curveTo(96.503, 169.78902, 96.618996, 169.31401,
                96.852, 169.07101);
        ((GeneralPath) shape).curveTo(97.083, 168.82802, 97.538994, 168.70601,
                98.213, 168.70601);
        ((GeneralPath) shape).curveTo(98.859, 168.70601, 99.294, 168.824,
                99.514, 169.061);
        ((GeneralPath) shape).curveTo(99.734, 169.296, 99.844, 169.76201,
                99.844, 170.457);
        ((GeneralPath) shape).lineTo(99.844, 170.723);
        ((GeneralPath) shape).lineTo(97.39, 170.723);
        ((GeneralPath) shape).curveTo(97.386, 170.80301, 97.382996, 170.856,
                97.382996, 170.88301);
        ((GeneralPath) shape).curveTo(97.382996, 171.23901, 97.437996, 171.477,
                97.548, 171.59702);
        ((GeneralPath) shape).curveTo(97.658, 171.71501, 97.876, 171.77602,
                98.20599, 171.77602);
        ((GeneralPath) shape).curveTo(98.524994, 171.77602, 98.730995,
                171.74202, 98.827995, 171.67201);
        ((GeneralPath) shape).curveTo(98.922, 171.604, 98.971, 171.453, 98.971,
                171.222);
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
        ((GeneralPath) shape).moveTo(101.395, 168.752);
        ((GeneralPath) shape).lineTo(101.395, 169.687);
        ((GeneralPath) shape).lineTo(100.527, 169.687);
        ((GeneralPath) shape).lineTo(100.527, 168.752);
        ((GeneralPath) shape).lineTo(101.395, 168.752);
        ((GeneralPath) shape).moveTo(101.395, 171.45);
        ((GeneralPath) shape).lineTo(101.395, 172.385);
        ((GeneralPath) shape).lineTo(100.527, 172.385);
        ((GeneralPath) shape).lineTo(100.527, 171.45);
        ((GeneralPath) shape).lineTo(101.395, 171.45);
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
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(48.287, 179.054);
        ((GeneralPath) shape).lineTo(48.287, 180.392);
        ((GeneralPath) shape).lineTo(50.713, 180.392);
        ((GeneralPath) shape).lineTo(50.713, 181.118);
        ((GeneralPath) shape).lineTo(48.287, 181.118);
        ((GeneralPath) shape).lineTo(48.287, 182.584);
        ((GeneralPath) shape).lineTo(50.869, 182.584);
        ((GeneralPath) shape).lineTo(50.869, 183.412);
        ((GeneralPath) shape).lineTo(47.304, 183.412);
        ((GeneralPath) shape).lineTo(47.304, 178.226);
        ((GeneralPath) shape).lineTo(50.846, 178.226);
        ((GeneralPath) shape).lineTo(50.846, 179.054);
        ((GeneralPath) shape).lineTo(48.287, 179.054);
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
        ((GeneralPath) shape).moveTo(51.574, 179.78);
        ((GeneralPath) shape).lineTo(52.435, 179.78);
        ((GeneralPath) shape).lineTo(52.401, 180.39099);
        ((GeneralPath) shape).lineTo(52.420002, 180.39499);
        ((GeneralPath) shape).curveTo(52.588, 179.958, 52.968002, 179.73799,
                53.557003, 179.73799);
        ((GeneralPath) shape).curveTo(54.414, 179.73799, 54.842003, 180.137,
                54.842003, 180.939);
        ((GeneralPath) shape).lineTo(54.842003, 183.412);
        ((GeneralPath) shape).lineTo(53.974003, 183.412);
        ((GeneralPath) shape).lineTo(53.974003, 181.087);
        ((GeneralPath) shape).lineTo(53.954002, 180.83301);
        ((GeneralPath) shape).curveTo(53.914, 180.563, 53.702003, 180.42601,
                53.317, 180.42601);
        ((GeneralPath) shape).curveTo(52.734, 180.42601, 52.441, 180.703,
                52.441, 181.259);
        ((GeneralPath) shape).lineTo(52.441, 183.41301);
        ((GeneralPath) shape).lineTo(51.573, 183.41301);
        ((GeneralPath) shape).lineTo(51.573, 179.78);
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
        ((GeneralPath) shape).moveTo(57.221, 180.426);
        ((GeneralPath) shape).curveTo(56.888, 180.426, 56.673, 180.493, 56.578,
                180.62999);
        ((GeneralPath) shape).curveTo(56.482998, 180.76498, 56.436, 181.07199,
                56.436, 181.555);
        ((GeneralPath) shape).curveTo(56.436, 182.066, 56.483, 182.39299,
                56.578, 182.53499);
        ((GeneralPath) shape).curveTo(56.673, 182.676, 56.891, 182.74799,
                57.232, 182.74799);
        ((GeneralPath) shape).curveTo(57.572998, 182.74799, 57.794, 182.67598,
                57.896, 182.52899);
        ((GeneralPath) shape).curveTo(57.997, 182.385, 58.048, 182.062, 58.048,
                181.562);
        ((GeneralPath) shape).curveTo(58.048, 181.083, 57.999, 180.775, 57.896,
                180.635);
        ((GeneralPath) shape).curveTo(57.795, 180.496, 57.57, 180.426, 57.221,
                180.426);
        ((GeneralPath) shape).moveTo(58.931, 179.78);
        ((GeneralPath) shape).lineTo(58.931, 183.48);
        ((GeneralPath) shape).curveTo(58.931, 184.073, 58.811, 184.47899,
                58.572998, 184.7);
        ((GeneralPath) shape).curveTo(58.334, 184.92099, 57.893997, 185.03099,
                57.252, 185.03099);
        ((GeneralPath) shape).curveTo(56.629997, 185.03099, 56.215, 184.948,
                56.003998, 184.77998);
        ((GeneralPath) shape).curveTo(55.795998, 184.61298, 55.69, 184.28198,
                55.69, 183.78899);
        ((GeneralPath) shape).lineTo(56.531998, 183.78899);
        ((GeneralPath) shape).curveTo(56.531998, 184.02399, 56.574997,
                184.17398, 56.664997, 184.24298);
        ((GeneralPath) shape).curveTo(56.752, 184.30898, 56.948997, 184.34398,
                57.255997, 184.34398);
        ((GeneralPath) shape).curveTo(57.795, 184.34398, 58.063995, 184.12097,
                58.063995, 183.67497);
        ((GeneralPath) shape).lineTo(58.063995, 182.85797);
        ((GeneralPath) shape).lineTo(58.044994, 182.85497);
        ((GeneralPath) shape).curveTo(57.891994, 183.24196, 57.536995,
                183.43596, 56.982994, 183.43596);
        ((GeneralPath) shape).curveTo(56.434994, 183.43596, 56.059994,
                183.30296, 55.858994, 183.03696);
        ((GeneralPath) shape).curveTo(55.657993, 182.77095, 55.556995,
                182.27496, 55.556995, 181.54796);
        ((GeneralPath) shape).curveTo(55.556995, 180.86395, 55.657997,
                180.39296, 55.858994, 180.13095);
        ((GeneralPath) shape).curveTo(56.059994, 179.87096, 56.422993,
                179.73895, 56.952995, 179.73895);
        ((GeneralPath) shape).curveTo(57.528996, 179.73895, 57.906994,
                179.95195, 58.086994, 180.38095);
        ((GeneralPath) shape).lineTo(58.105995, 180.38095);
        ((GeneralPath) shape).lineTo(58.063995, 179.78094);
        ((GeneralPath) shape).lineTo(58.931, 179.78094);
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
        ((GeneralPath) shape).moveTo(60.65, 179.78);
        ((GeneralPath) shape).lineTo(60.65, 183.412);
        ((GeneralPath) shape).lineTo(59.782, 183.412);
        ((GeneralPath) shape).lineTo(59.782, 179.78);
        ((GeneralPath) shape).lineTo(60.65, 179.78);
        ((GeneralPath) shape).moveTo(60.65, 178.226);
        ((GeneralPath) shape).lineTo(60.65, 178.952);
        ((GeneralPath) shape).lineTo(59.782, 178.952);
        ((GeneralPath) shape).lineTo(59.782, 178.226);
        ((GeneralPath) shape).lineTo(60.65, 178.226);
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
        ((GeneralPath) shape).moveTo(61.506, 179.78);
        ((GeneralPath) shape).lineTo(62.366, 179.78);
        ((GeneralPath) shape).lineTo(62.332, 180.39099);
        ((GeneralPath) shape).lineTo(62.351, 180.39499);
        ((GeneralPath) shape).curveTo(62.52, 179.958, 62.899002, 179.73799,
                63.489002, 179.73799);
        ((GeneralPath) shape).curveTo(64.346, 179.73799, 64.774, 180.137,
                64.774, 180.939);
        ((GeneralPath) shape).lineTo(64.774, 183.412);
        ((GeneralPath) shape).lineTo(63.906002, 183.412);
        ((GeneralPath) shape).lineTo(63.906002, 181.087);
        ((GeneralPath) shape).lineTo(63.887, 180.83301);
        ((GeneralPath) shape).curveTo(63.847, 180.563, 63.635002, 180.42601,
                63.25, 180.42601);
        ((GeneralPath) shape).curveTo(62.667, 180.42601, 62.375, 180.703,
                62.375, 181.259);
        ((GeneralPath) shape).lineTo(62.375, 183.41301);
        ((GeneralPath) shape).lineTo(61.507, 183.41301);
        ((GeneralPath) shape).lineTo(61.507, 179.78);
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
        paint2(g, clip_, defaultTransform_, alpha__0, clip__0,
                defaultTransform__0, alpha__0_183, clip__0_183,
                defaultTransform__0_183);
    }

    private static void paint2(Graphics2D g, Shape clip_,
            AffineTransform defaultTransform_, float alpha__0, Shape clip__0,
            AffineTransform defaultTransform__0, float alpha__0_183,
            Shape clip__0_183, AffineTransform defaultTransform__0_183) {
        Shape shape;
        Paint paint;
        Stroke stroke;
        float origAlpha;
        // _0_183 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(67.965, 181.22);
        ((GeneralPath) shape).lineTo(67.961, 181.075);
        ((GeneralPath) shape).curveTo(67.961, 180.786, 67.911995, 180.59999,
                67.811, 180.512);
        ((GeneralPath) shape).curveTo(67.712, 180.42699, 67.494995, 180.383,
                67.160995, 180.383);
        ((GeneralPath) shape).curveTo(66.839, 180.383, 66.628, 180.43399,
                66.53, 180.539);
        ((GeneralPath) shape).curveTo(66.433, 180.642, 66.383995, 180.87,
                66.383995, 181.219);
        ((GeneralPath) shape).lineTo(67.965, 181.219);
        ((GeneralPath) shape).moveTo(67.957, 182.25);
        ((GeneralPath) shape).lineTo(68.83, 182.25);
        ((GeneralPath) shape).lineTo(68.83, 182.391);
        ((GeneralPath) shape).curveTo(68.83, 183.099, 68.299, 183.455, 67.238,
                183.455);
        ((GeneralPath) shape).curveTo(66.517, 183.455, 66.047, 183.33301,
                65.823, 183.086);
        ((GeneralPath) shape).curveTo(65.601, 182.841, 65.49, 182.31999, 65.49,
                181.524);
        ((GeneralPath) shape).curveTo(65.49, 180.817, 65.604996, 180.342,
                65.839, 180.099);
        ((GeneralPath) shape).curveTo(66.07, 179.856, 66.52599, 179.734, 67.2,
                179.734);
        ((GeneralPath) shape).curveTo(67.846, 179.734, 68.281, 179.85199, 68.5,
                180.08899);
        ((GeneralPath) shape).curveTo(68.72, 180.32399, 68.83, 180.79, 68.83,
                181.48499);
        ((GeneralPath) shape).lineTo(68.83, 181.75099);
        ((GeneralPath) shape).lineTo(66.377, 181.75099);
        ((GeneralPath) shape).curveTo(66.373, 181.831, 66.368996, 181.88399,
                66.368996, 181.911);
        ((GeneralPath) shape).curveTo(66.368996, 182.26799, 66.423996, 182.506,
                66.534, 182.625);
        ((GeneralPath) shape).curveTo(66.644, 182.743, 66.862, 182.803,
                67.19199, 182.803);
        ((GeneralPath) shape).curveTo(67.510994, 182.803, 67.716995, 182.769,
                67.813995, 182.69899);
        ((GeneralPath) shape).curveTo(67.908, 182.631, 67.957, 182.481, 67.957,
                182.25);
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
        ((GeneralPath) shape).moveTo(73.581, 179.107);
        ((GeneralPath) shape).lineTo(73.581, 183.412);
        ((GeneralPath) shape).lineTo(72.599, 183.412);
        ((GeneralPath) shape).lineTo(72.599, 179.107);
        ((GeneralPath) shape).lineTo(71.104, 179.107);
        ((GeneralPath) shape).lineTo(71.104, 178.226);
        ((GeneralPath) shape).lineTo(75.127, 178.226);
        ((GeneralPath) shape).lineTo(75.127, 179.107);
        ((GeneralPath) shape).lineTo(73.581, 179.107);
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
        ((GeneralPath) shape).moveTo(78.583, 179.78);
        ((GeneralPath) shape).lineTo(77.631, 183.64);
        ((GeneralPath) shape).curveTo(77.49799, 184.187, 77.33199, 184.554,
                77.131996, 184.744);
        ((GeneralPath) shape).curveTo(76.935, 184.932, 76.616, 185.02701,
                76.17799, 185.02701);
        ((GeneralPath) shape).curveTo(76.078995, 185.02701, 75.977, 185.02301,
                75.870995, 185.01201);
        ((GeneralPath) shape).lineTo(75.870995, 184.37001);
        ((GeneralPath) shape).curveTo(75.94699, 184.37302, 76.009995, 184.378,
                76.06, 184.378);
        ((GeneralPath) shape).curveTo(76.42799, 184.378, 76.662994, 184.057,
                76.762, 183.41301);
        ((GeneralPath) shape).lineTo(76.32, 183.41301);
        ((GeneralPath) shape).lineTo(75.133, 179.781);
        ((GeneralPath) shape).lineTo(76.066, 179.781);
        ((GeneralPath) shape).lineTo(76.521, 181.32);
        ((GeneralPath) shape).lineTo(76.749, 182.091);
        ((GeneralPath) shape).curveTo(76.76, 182.141, 76.796, 182.27, 76.855,
                182.479);
        ((GeneralPath) shape).lineTo(76.965004, 182.863);
        ((GeneralPath) shape).lineTo(76.984, 182.863);
        ((GeneralPath) shape).lineTo(77.064, 182.479);
        ((GeneralPath) shape).curveTo(77.104004, 182.282, 77.130005, 182.15201,
                77.144005, 182.091);
        ((GeneralPath) shape).lineTo(77.32201, 181.32);
        ((GeneralPath) shape).lineTo(77.671005, 179.781);
        ((GeneralPath) shape).lineTo(78.583, 179.781);
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
        ((GeneralPath) shape).moveTo(80.679, 180.422);
        ((GeneralPath) shape).curveTo(80.349, 180.422, 80.133, 180.496, 80.031,
                180.646);
        ((GeneralPath) shape).curveTo(79.93, 180.79399, 79.879, 181.111,
                79.879, 181.596);
        ((GeneralPath) shape).curveTo(79.879, 182.06699, 79.932, 182.379,
                80.042, 182.534);
        ((GeneralPath) shape).curveTo(80.15, 182.688, 80.372, 182.76599,
                80.706, 182.76599);
        ((GeneralPath) shape).curveTo(81.043, 182.76599, 81.263, 182.69398,
                81.366005, 182.545);
        ((GeneralPath) shape).curveTo(81.467, 182.399, 81.518005, 182.084,
                81.518005, 181.599);
        ((GeneralPath) shape).curveTo(81.518005, 181.103, 81.467, 180.782,
                81.364006, 180.638);
        ((GeneralPath) shape).curveTo(81.262, 180.495, 81.034, 180.422, 80.679,
                180.422);
        ((GeneralPath) shape).moveTo(78.97, 179.78);
        ((GeneralPath) shape).lineTo(79.853004, 179.78);
        ((GeneralPath) shape).lineTo(79.81901, 180.31999);
        ((GeneralPath) shape).lineTo(79.838005, 180.323);
        ((GeneralPath) shape).curveTo(80.046005, 179.93199, 80.437004, 179.734,
                81.009, 179.734);
        ((GeneralPath) shape).curveTo(81.536, 179.734, 81.9, 179.86499, 82.099,
                180.12799);
        ((GeneralPath) shape).curveTo(82.296, 180.38998, 82.396996, 180.86699,
                82.396996, 181.562);
        ((GeneralPath) shape).curveTo(82.396996, 182.286, 82.298996, 182.782,
                82.101, 183.051);
        ((GeneralPath) shape).curveTo(81.904, 183.319, 81.54, 183.454, 81.005,
                183.454);
        ((GeneralPath) shape).curveTo(80.436, 183.454, 80.064995, 183.26399,
                79.89, 182.88399);
        ((GeneralPath) shape).lineTo(79.875, 182.88399);
        ((GeneralPath) shape).lineTo(79.875, 184.969);
        ((GeneralPath) shape).lineTo(78.97, 184.969);
        ((GeneralPath) shape).lineTo(78.97, 179.78);
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
        ((GeneralPath) shape).moveTo(85.467, 181.22);
        ((GeneralPath) shape).lineTo(85.463005, 181.075);
        ((GeneralPath) shape).curveTo(85.463005, 180.786, 85.414, 180.59999,
                85.313, 180.512);
        ((GeneralPath) shape).curveTo(85.214005, 180.42699, 84.997, 180.383,
                84.663, 180.383);
        ((GeneralPath) shape).curveTo(84.340004, 180.383, 84.130005, 180.43399,
                84.031, 180.539);
        ((GeneralPath) shape).curveTo(83.935, 180.642, 83.884995, 180.87,
                83.884995, 181.219);
        ((GeneralPath) shape).lineTo(85.467, 181.219);
        ((GeneralPath) shape).moveTo(85.459, 182.25);
        ((GeneralPath) shape).lineTo(86.332, 182.25);
        ((GeneralPath) shape).lineTo(86.332, 182.391);
        ((GeneralPath) shape).curveTo(86.332, 183.099, 85.801, 183.455, 84.739,
                183.455);
        ((GeneralPath) shape).curveTo(84.018, 183.455, 83.548, 183.33301,
                83.324, 183.086);
        ((GeneralPath) shape).curveTo(83.102, 182.841, 82.991, 182.31999,
                82.991, 181.524);
        ((GeneralPath) shape).curveTo(82.991, 180.817, 83.106995, 180.342,
                83.34, 180.099);
        ((GeneralPath) shape).curveTo(83.571, 179.856, 84.02699, 179.734,
                84.701, 179.734);
        ((GeneralPath) shape).curveTo(85.347, 179.734, 85.782, 179.85199,
                86.002, 180.08899);
        ((GeneralPath) shape).curveTo(86.222, 180.32399, 86.332, 180.79,
                86.332, 181.48499);
        ((GeneralPath) shape).lineTo(86.332, 181.75099);
        ((GeneralPath) shape).lineTo(83.878, 181.75099);
        ((GeneralPath) shape).curveTo(83.874, 181.831, 83.870995, 181.88399,
                83.870995, 181.911);
        ((GeneralPath) shape).curveTo(83.870995, 182.26799, 83.925995, 182.506,
                84.035995, 182.625);
        ((GeneralPath) shape).curveTo(84.145, 182.743, 84.364, 182.803,
                84.69299, 182.803);
        ((GeneralPath) shape).curveTo(85.01199, 182.803, 85.217995, 182.769,
                85.314995, 182.69899);
        ((GeneralPath) shape).curveTo(85.41, 182.631, 85.459, 182.481, 85.459,
                182.25);
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
        ((GeneralPath) shape).moveTo(87.884, 179.78);
        ((GeneralPath) shape).lineTo(87.884, 180.715);
        ((GeneralPath) shape).lineTo(87.01601, 180.715);
        ((GeneralPath) shape).lineTo(87.01601, 179.78);
        ((GeneralPath) shape).lineTo(87.884, 179.78);
        ((GeneralPath) shape).moveTo(87.884, 182.478);
        ((GeneralPath) shape).lineTo(87.884, 183.413);
        ((GeneralPath) shape).lineTo(87.01601, 183.413);
        ((GeneralPath) shape).lineTo(87.01601, 182.478);
        ((GeneralPath) shape).lineTo(87.884, 182.478);
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
        ((GeneralPath) shape).moveTo(55.578, 149.165);
        ((GeneralPath) shape).lineTo(56.564, 149.165);
        ((GeneralPath) shape).lineTo(56.564, 149.344);
        ((GeneralPath) shape).curveTo(56.564, 150.066, 56.433, 150.53099,
                56.17, 150.74399);
        ((GeneralPath) shape).curveTo(55.907997, 150.95499, 55.325996,
                151.06099, 54.425, 151.06099);
        ((GeneralPath) shape).curveTo(53.405, 151.06099, 52.776, 150.89398,
                52.541, 150.55998);
        ((GeneralPath) shape).curveTo(52.306, 150.22598, 52.188, 149.33199,
                52.188, 147.87698);
        ((GeneralPath) shape).curveTo(52.188, 147.02199, 52.347, 146.45798,
                52.666, 146.18898);
        ((GeneralPath) shape).curveTo(52.984, 145.91898, 53.65, 145.78398,
                54.664, 145.78398);
        ((GeneralPath) shape).curveTo(55.402, 145.78398, 55.894, 145.89398,
                56.143, 146.11697);
        ((GeneralPath) shape).curveTo(56.389, 146.33698, 56.515003, 146.77797,
                56.515003, 147.43698);
        ((GeneralPath) shape).lineTo(56.519005, 147.55498);
        ((GeneralPath) shape).lineTo(55.533005, 147.55498);
        ((GeneralPath) shape).lineTo(55.533005, 147.42198);
        ((GeneralPath) shape).curveTo(55.533005, 147.08398, 55.470005,
                146.86497, 55.342003, 146.76999);
        ((GeneralPath) shape).curveTo(55.216003, 146.67499, 54.923004,
                146.62698, 54.468002, 146.62698);
        ((GeneralPath) shape).curveTo(53.859, 146.62698, 53.493004, 146.70099,
                53.370003, 146.85199);
        ((GeneralPath) shape).curveTo(53.249004, 146.99998, 53.186005,
                147.44398, 53.186005, 148.18199);
        ((GeneralPath) shape).curveTo(53.186005, 149.174, 53.241005, 149.76299,
                53.351006, 149.94499);
        ((GeneralPath) shape).curveTo(53.461006, 150.127, 53.815006, 150.21799,
                54.418007, 150.21799);
        ((GeneralPath) shape).curveTo(54.905006, 150.21799, 55.222008,
                150.16899, 55.368008, 150.066);
        ((GeneralPath) shape).curveTo(55.51201, 149.965, 55.586006, 149.743,
                55.586006, 149.398);
        ((GeneralPath) shape).lineTo(55.578, 149.165);
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
        ((GeneralPath) shape).moveTo(57.177, 147.383);
        ((GeneralPath) shape).lineTo(58.045998, 147.383);
        ((GeneralPath) shape).lineTo(57.992996, 147.892);
        ((GeneralPath) shape).lineTo(58.011997, 147.895);
        ((GeneralPath) shape).curveTo(58.218998, 147.516, 58.545, 147.325,
                58.990997, 147.325);
        ((GeneralPath) shape).curveTo(59.627, 147.325, 59.946, 147.728, 59.946,
                148.53299);
        ((GeneralPath) shape).lineTo(59.946, 148.788);
        ((GeneralPath) shape).lineTo(59.127, 148.788);
        ((GeneralPath) shape).curveTo(59.136997, 148.689, 59.142998, 148.624,
                59.142998, 148.594);
        ((GeneralPath) shape).curveTo(59.142998, 148.206, 58.992996, 148.012,
                58.691998, 148.012);
        ((GeneralPath) shape).curveTo(58.263996, 148.012, 58.046997, 148.299,
                58.046997, 148.875);
        ((GeneralPath) shape).lineTo(58.046997, 151.014);
        ((GeneralPath) shape).lineTo(57.177998, 151.014);
        ((GeneralPath) shape).lineTo(57.177998, 147.383);
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
        ((GeneralPath) shape).moveTo(63.643, 147.383);
        ((GeneralPath) shape).lineTo(63.643, 151.015);
        ((GeneralPath) shape).lineTo(62.774002, 151.015);
        ((GeneralPath) shape).lineTo(62.823, 150.392);
        ((GeneralPath) shape).lineTo(62.808002, 150.38899);
        ((GeneralPath) shape).curveTo(62.640003, 150.833, 62.250004, 151.05699,
                61.640003, 151.05699);
        ((GeneralPath) shape).curveTo(60.819004, 151.05699, 60.408005,
                150.64699, 60.408005, 149.823);
        ((GeneralPath) shape).lineTo(60.408005, 147.384);
        ((GeneralPath) shape).lineTo(61.276005, 147.384);
        ((GeneralPath) shape).lineTo(61.276005, 149.614);
        ((GeneralPath) shape).curveTo(61.276005, 149.922, 61.318005, 150.125,
                61.405006, 150.224);
        ((GeneralPath) shape).curveTo(61.490005, 150.321, 61.669006, 150.37,
                61.940006, 150.37);
        ((GeneralPath) shape).curveTo(62.496006, 150.37, 62.774006, 150.036,
                62.774006, 149.36699);
        ((GeneralPath) shape).lineTo(62.774006, 147.38399);
        ((GeneralPath) shape).lineTo(63.643, 147.38399);
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
        ((GeneralPath) shape).moveTo(65.392, 147.383);
        ((GeneralPath) shape).lineTo(65.392, 151.015);
        ((GeneralPath) shape).lineTo(64.524, 151.015);
        ((GeneralPath) shape).lineTo(64.524, 147.383);
        ((GeneralPath) shape).lineTo(65.392, 147.383);
        ((GeneralPath) shape).moveTo(65.392, 145.829);
        ((GeneralPath) shape).lineTo(65.392, 146.555);
        ((GeneralPath) shape).lineTo(64.524, 146.555);
        ((GeneralPath) shape).lineTo(64.524, 145.829);
        ((GeneralPath) shape).lineTo(65.392, 145.829);
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
        ((GeneralPath) shape).moveTo(69.273, 148.382);
        ((GeneralPath) shape).lineTo(68.424, 148.382);
        ((GeneralPath) shape).curveTo(68.420006, 148.352, 68.417, 148.32901,
                68.413, 148.31401);
        ((GeneralPath) shape).curveTo(68.396, 148.139, 68.347, 148.031, 68.263,
                147.98701);
        ((GeneralPath) shape).curveTo(68.181, 147.945, 67.982, 147.92302,
                67.666, 147.92302);
        ((GeneralPath) shape).curveTo(67.215004, 147.92302, 66.987, 148.06902,
                66.987, 148.36302);
        ((GeneralPath) shape).curveTo(66.987, 148.56302, 67.027, 148.68202,
                67.107, 148.72002);
        ((GeneralPath) shape).curveTo(67.187004, 148.75801, 67.456, 148.78801,
                67.917, 148.81102);
        ((GeneralPath) shape).curveTo(68.534996, 148.84203, 68.938, 148.92302,
                69.126, 149.05801);
        ((GeneralPath) shape).curveTo(69.312, 149.19101, 69.406, 149.46602,
                69.406, 149.88301);
        ((GeneralPath) shape).curveTo(69.406, 150.32501, 69.283, 150.63301,
                69.033, 150.80402);
        ((GeneralPath) shape).curveTo(68.784, 150.97502, 68.337, 151.06102,
                67.69199, 151.06102);
        ((GeneralPath) shape).curveTo(67.074, 151.06102, 66.649994, 150.98502,
                66.422, 150.82903);
        ((GeneralPath) shape).curveTo(66.194, 150.67502, 66.08099, 150.38603,
                66.08099, 149.96303);
        ((GeneralPath) shape).lineTo(66.08099, 149.87202);
        ((GeneralPath) shape).lineTo(66.982994, 149.87202);
        ((GeneralPath) shape).curveTo(66.97199, 149.92102, 66.964, 149.96303,
                66.96099, 149.99402);
        ((GeneralPath) shape).curveTo(66.926994, 150.31502, 67.17299,
                150.47601, 67.70399, 150.47601);
        ((GeneralPath) shape).curveTo(68.25198, 150.47601, 68.526985,
                150.31702, 68.526985, 149.99701);
        ((GeneralPath) shape).curveTo(68.526985, 149.69101, 68.35599,
                149.53801, 68.01099, 149.53801);
        ((GeneralPath) shape).curveTo(67.235985, 149.53801, 66.722984, 149.466,
                66.47699, 149.31702);
        ((GeneralPath) shape).curveTo(66.22999, 149.17102, 66.10699, 148.86502,
                66.10699, 148.40102);
        ((GeneralPath) shape).curveTo(66.10699, 147.98701, 66.21899, 147.70601,
                66.443985, 147.55801);
        ((GeneralPath) shape).curveTo(66.667984, 147.41202, 67.09998,
                147.33801, 67.74098, 147.33801);
        ((GeneralPath) shape).curveTo(68.34398, 147.33801, 68.750984,
                147.40802, 68.959984, 147.55002);
        ((GeneralPath) shape).curveTo(69.169, 147.691, 69.273, 147.968, 69.273,
                148.382);
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
        ((GeneralPath) shape).moveTo(70.959, 147.383);
        ((GeneralPath) shape).lineTo(70.959, 151.015);
        ((GeneralPath) shape).lineTo(70.091, 151.015);
        ((GeneralPath) shape).lineTo(70.091, 147.383);
        ((GeneralPath) shape).lineTo(70.959, 147.383);
        ((GeneralPath) shape).moveTo(70.959, 145.829);
        ((GeneralPath) shape).lineTo(70.959, 146.555);
        ((GeneralPath) shape).lineTo(70.091, 146.555);
        ((GeneralPath) shape).lineTo(70.091, 145.829);
        ((GeneralPath) shape).lineTo(70.959, 145.829);
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
        ((GeneralPath) shape).moveTo(71.815, 147.383);
        ((GeneralPath) shape).lineTo(72.675, 147.383);
        ((GeneralPath) shape).lineTo(72.64101, 147.99399);
        ((GeneralPath) shape).lineTo(72.66, 147.99799);
        ((GeneralPath) shape).curveTo(72.829, 147.56099, 73.208, 147.34099,
                73.798004, 147.34099);
        ((GeneralPath) shape).curveTo(74.65501, 147.34099, 75.08301, 147.73999,
                75.08301, 148.54199);
        ((GeneralPath) shape).lineTo(75.08301, 151.015);
        ((GeneralPath) shape).lineTo(74.21501, 151.015);
        ((GeneralPath) shape).lineTo(74.21501, 148.69);
        ((GeneralPath) shape).lineTo(74.196014, 148.436);
        ((GeneralPath) shape).curveTo(74.15601, 148.166, 73.944016, 148.03,
                73.55901, 148.03);
        ((GeneralPath) shape).curveTo(72.97601, 148.03, 72.68401, 148.30699,
                72.68401, 148.862);
        ((GeneralPath) shape).lineTo(72.68401, 151.016);
        ((GeneralPath) shape).lineTo(71.81602, 151.016);
        ((GeneralPath) shape).lineTo(71.81602, 147.383);
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
        ((GeneralPath) shape).moveTo(77.462, 148.029);
        ((GeneralPath) shape).curveTo(77.129, 148.029, 76.914, 148.095, 76.82,
                148.23201);
        ((GeneralPath) shape).curveTo(76.725, 148.367, 76.677, 148.67401,
                76.677, 149.15701);
        ((GeneralPath) shape).curveTo(76.677, 149.66801, 76.724, 149.99501,
                76.82, 150.13701);
        ((GeneralPath) shape).curveTo(76.914, 150.27802, 77.133, 150.35,
                77.474, 150.35);
        ((GeneralPath) shape).curveTo(77.814995, 150.35, 78.034996, 150.278,
                78.138, 150.13101);
        ((GeneralPath) shape).curveTo(78.239, 149.98701, 78.29, 149.66402,
                78.29, 149.16402);
        ((GeneralPath) shape).curveTo(78.29, 148.68501, 78.241, 148.37701,
                78.138, 148.23701);
        ((GeneralPath) shape).curveTo(78.037, 148.099, 77.812, 148.029, 77.462,
                148.029);
        ((GeneralPath) shape).moveTo(79.173, 147.383);
        ((GeneralPath) shape).lineTo(79.173, 151.083);
        ((GeneralPath) shape).curveTo(79.173, 151.676, 79.05299, 152.08199,
                78.814995, 152.303);
        ((GeneralPath) shape).curveTo(78.576, 152.523, 78.135994, 152.63399,
                77.493996, 152.63399);
        ((GeneralPath) shape).curveTo(76.871994, 152.63399, 76.45699, 152.551,
                76.245995, 152.38298);
        ((GeneralPath) shape).curveTo(76.037994, 152.21597, 75.93199,
                151.88498, 75.93199, 151.39198);
        ((GeneralPath) shape).lineTo(76.772995, 151.39198);
        ((GeneralPath) shape).curveTo(76.772995, 151.62698, 76.81699,
                151.77698, 76.906, 151.84598);
        ((GeneralPath) shape).curveTo(76.993, 151.91298, 77.189995, 151.94698,
                77.498, 151.94698);
        ((GeneralPath) shape).curveTo(78.036, 151.94698, 78.306, 151.72397,
                78.306, 151.27797);
        ((GeneralPath) shape).lineTo(78.306, 150.46097);
        ((GeneralPath) shape).lineTo(78.287, 150.45796);
        ((GeneralPath) shape).curveTo(78.133, 150.84496, 77.779, 151.03896,
                77.225006, 151.03896);
        ((GeneralPath) shape).curveTo(76.67801, 151.03896, 76.30201, 150.90596,
                76.101006, 150.63995);
        ((GeneralPath) shape).curveTo(75.90001, 150.37395, 75.8, 149.87796,
                75.8, 149.15096);
        ((GeneralPath) shape).curveTo(75.8, 148.46695, 75.9, 147.99596,
                76.101006, 147.73395);
        ((GeneralPath) shape).curveTo(76.302, 147.47395, 76.66601, 147.34195,
                77.19501, 147.34195);
        ((GeneralPath) shape).curveTo(77.77201, 147.34195, 78.14901, 147.55495,
                78.32901, 147.98395);
        ((GeneralPath) shape).lineTo(78.34801, 147.98395);
        ((GeneralPath) shape).lineTo(78.30601, 147.38394);
        ((GeneralPath) shape).lineTo(79.173, 147.38394);
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
        ((GeneralPath) shape).moveTo(80.854, 147.383);
        ((GeneralPath) shape).lineTo(80.854, 148.318);
        ((GeneralPath) shape).lineTo(79.986, 148.318);
        ((GeneralPath) shape).lineTo(79.986, 147.383);
        ((GeneralPath) shape).lineTo(80.854, 147.383);
        ((GeneralPath) shape).moveTo(80.854, 150.081);
        ((GeneralPath) shape).lineTo(80.854, 151.01599);
        ((GeneralPath) shape).lineTo(79.986, 151.01599);
        ((GeneralPath) shape).lineTo(79.986, 150.081);
        ((GeneralPath) shape).lineTo(80.854, 150.081);
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
        ((GeneralPath) shape).moveTo(53.364, 156.871);
        ((GeneralPath) shape).lineTo(53.364, 158.269);
        ((GeneralPath) shape).lineTo(55.685, 158.269);
        ((GeneralPath) shape).lineTo(55.685, 159.097);
        ((GeneralPath) shape).lineTo(53.364, 159.097);
        ((GeneralPath) shape).lineTo(53.364, 161.229);
        ((GeneralPath) shape).lineTo(52.382, 161.229);
        ((GeneralPath) shape).lineTo(52.382, 156.042);
        ((GeneralPath) shape).lineTo(55.81, 156.042);
        ((GeneralPath) shape).lineTo(55.81, 156.871);
        ((GeneralPath) shape).lineTo(53.364, 156.871);
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
        shape = new Rectangle2D.Double(56.37799835205078, 156.04200744628906,
                0.8679999709129333, 5.186999797821045);
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
        ((GeneralPath) shape).moveTo(59.584, 159.602);
        ((GeneralPath) shape).curveTo(59.095, 159.602, 58.849, 159.77101,
                58.849, 160.11101);
        ((GeneralPath) shape).curveTo(58.849, 160.34601, 58.898, 160.50002,
                59.0, 160.574);
        ((GeneralPath) shape).curveTo(59.101, 160.64601, 59.313, 160.684,
                59.637, 160.684);
        ((GeneralPath) shape).curveTo(60.164, 160.684, 60.429, 160.505, 60.429,
                160.14801);
        ((GeneralPath) shape).curveTo(60.43, 159.785, 60.149, 159.602, 59.584,
                159.602);
        ((GeneralPath) shape).moveTo(59.004, 158.648);
        ((GeneralPath) shape).lineTo(58.117, 158.648);
        ((GeneralPath) shape).curveTo(58.117, 158.213, 58.218002, 157.922,
                58.420002, 157.774);
        ((GeneralPath) shape).curveTo(58.621002, 157.628, 59.023003, 157.554,
                59.622, 157.554);
        ((GeneralPath) shape).curveTo(60.274002, 157.554, 60.714, 157.643,
                60.945, 157.824);
        ((GeneralPath) shape).curveTo(61.174, 158.002, 61.29, 158.34601, 61.29,
                158.85301);
        ((GeneralPath) shape).lineTo(61.29, 161.22801);
        ((GeneralPath) shape).lineTo(60.422, 161.22801);
        ((GeneralPath) shape).lineTo(60.464, 160.73001);
        ((GeneralPath) shape).lineTo(60.441, 160.72601);
        ((GeneralPath) shape).curveTo(60.274002, 161.087, 59.889004, 161.26901,
                59.285004, 161.26901);
        ((GeneralPath) shape).curveTo(58.409004, 161.26901, 57.969, 160.89702,
                57.969, 160.14801);
        ((GeneralPath) shape).curveTo(57.969, 159.39401, 58.416, 159.016,
                59.315002, 159.016);
        ((GeneralPath) shape).curveTo(59.914, 159.016, 60.278004, 159.153,
                60.407, 159.431);
        ((GeneralPath) shape).lineTo(60.422, 159.431);
        ((GeneralPath) shape).lineTo(60.422, 158.842);
        ((GeneralPath) shape).curveTo(60.422, 158.55899, 60.372, 158.371,
                60.274002, 158.278);
        ((GeneralPath) shape).curveTo(60.176003, 158.187, 59.975002, 158.13899,
                59.668003, 158.13899);
        ((GeneralPath) shape).curveTo(59.226, 158.139, 59.004, 158.309, 59.004,
                158.648);
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
        ((GeneralPath) shape).moveTo(62.155, 157.596);
        ((GeneralPath) shape).lineTo(63.016, 157.596);
        ((GeneralPath) shape).lineTo(62.982, 158.208);
        ((GeneralPath) shape).lineTo(63.001, 158.21199);
        ((GeneralPath) shape).curveTo(63.169, 157.77399, 63.549, 157.55399,
                64.138, 157.55399);
        ((GeneralPath) shape).curveTo(64.995, 157.55399, 65.423004, 157.95299,
                65.423004, 158.75499);
        ((GeneralPath) shape).lineTo(65.423004, 161.22899);
        ((GeneralPath) shape).lineTo(64.55501, 161.22899);
        ((GeneralPath) shape).lineTo(64.55501, 158.90399);
        ((GeneralPath) shape).lineTo(64.53601, 158.64899);
        ((GeneralPath) shape).curveTo(64.49601, 158.37898, 64.28401, 158.24298,
                63.89901, 158.24298);
        ((GeneralPath) shape).curveTo(63.31601, 158.24298, 63.02301, 158.51997,
                63.02301, 159.07498);
        ((GeneralPath) shape).lineTo(63.02301, 161.22899);
        ((GeneralPath) shape).lineTo(62.15501, 161.22899);
        ((GeneralPath) shape).lineTo(62.15501, 157.596);
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
        ((GeneralPath) shape).moveTo(67.139, 156.042);
        ((GeneralPath) shape).lineTo(67.139, 159.01);
        ((GeneralPath) shape).lineTo(67.363, 159.01);
        ((GeneralPath) shape).lineTo(68.341, 157.596);
        ((GeneralPath) shape).lineTo(69.35, 157.596);
        ((GeneralPath) shape).lineTo(68.087, 159.306);
        ((GeneralPath) shape).lineTo(69.607, 161.229);
        ((GeneralPath) shape).lineTo(68.531, 161.229);
        ((GeneralPath) shape).lineTo(67.352, 159.617);
        ((GeneralPath) shape).lineTo(67.139, 159.617);
        ((GeneralPath) shape).lineTo(67.139, 161.229);
        ((GeneralPath) shape).lineTo(66.271, 161.229);
        ((GeneralPath) shape).lineTo(66.271, 156.042);
        ((GeneralPath) shape).lineTo(67.139, 156.042);
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
        ((GeneralPath) shape).moveTo(70.828, 157.596);
        ((GeneralPath) shape).lineTo(70.828, 158.53099);
        ((GeneralPath) shape).lineTo(69.96, 158.53099);
        ((GeneralPath) shape).lineTo(69.96, 157.596);
        ((GeneralPath) shape).lineTo(70.828, 157.596);
        ((GeneralPath) shape).moveTo(70.828, 160.293);
        ((GeneralPath) shape).lineTo(70.828, 161.228);
        ((GeneralPath) shape).lineTo(69.96, 161.228);
        ((GeneralPath) shape).lineTo(69.96, 160.293);
        ((GeneralPath) shape).lineTo(70.828, 160.293);
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
        shape = new Line2D.Float(281.970001f, 128.408005f, 307.263000f,
                128.408005f);
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
        shape = new Line2D.Float(305.885986f, 140.813995f, 319.670013f,
                140.813995f);
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
        paint = new Color(35, 31, 32, 255);
        stroke = new BasicStroke(0.973f, 0, 0, 4.0f, null, 0.0f);
        shape = new Line2D.Float(372.053986f, 140.813995f, 385.838989f,
                140.813995f);
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        ((GeneralPath) shape).moveTo(261.291, 125.18);
        ((GeneralPath) shape).lineTo(262.27698, 125.18);
        ((GeneralPath) shape).lineTo(262.27698, 125.359);
        ((GeneralPath) shape).curveTo(262.27698, 126.081, 262.14597, 126.546,
                261.882, 126.759);
        ((GeneralPath) shape).curveTo(261.62, 126.97, 261.038, 127.076004,
                260.137, 127.076004);
        ((GeneralPath) shape).curveTo(259.117, 127.076004, 258.488, 126.909004,
                258.253, 126.574005);
        ((GeneralPath) shape).curveTo(258.018, 126.240005, 257.9, 125.34701,
                257.9, 123.892006);
        ((GeneralPath) shape).curveTo(257.9, 123.037, 258.059, 122.47301,
                258.378, 122.204);
        ((GeneralPath) shape).curveTo(258.69598, 121.934006, 259.362, 121.799,
                260.37598, 121.799);
        ((GeneralPath) shape).curveTo(261.11298, 121.799, 261.606, 121.909004,
                261.85498, 122.132);
        ((GeneralPath) shape).curveTo(262.102, 122.352005, 262.227, 122.79301,
                262.227, 123.452);
        ((GeneralPath) shape).lineTo(262.231, 123.57);
        ((GeneralPath) shape).lineTo(261.245, 123.57);
        ((GeneralPath) shape).lineTo(261.245, 123.437);
        ((GeneralPath) shape).curveTo(261.245, 123.099, 261.182, 122.88,
                261.054, 122.784996);
        ((GeneralPath) shape).curveTo(260.92697, 122.689995, 260.63498,
                122.642, 260.18, 122.642);
        ((GeneralPath) shape).curveTo(259.572, 122.642, 259.205, 122.715996,
                259.082, 122.866);
        ((GeneralPath) shape).curveTo(258.96, 123.014, 258.898, 123.459,
                258.898, 124.196);
        ((GeneralPath) shape).curveTo(258.898, 125.187996, 258.953, 125.776,
                259.06302, 125.959);
        ((GeneralPath) shape).curveTo(259.173, 126.141, 259.528, 126.232,
                260.131, 126.232);
        ((GeneralPath) shape).curveTo(260.618, 126.232, 260.93402, 126.183,
                261.08102, 126.08);
        ((GeneralPath) shape).curveTo(261.22504, 125.979004, 261.299, 125.757,
                261.299, 125.412);
        ((GeneralPath) shape).lineTo(261.291, 125.18);
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
        ((GeneralPath) shape).moveTo(262.889, 123.398);
        ((GeneralPath) shape).lineTo(263.75702, 123.398);
        ((GeneralPath) shape).lineTo(263.704, 123.907005);
        ((GeneralPath) shape).lineTo(263.72302, 123.911);
        ((GeneralPath) shape).curveTo(263.93002, 123.531006, 264.256, 123.341,
                264.70203, 123.341);
        ((GeneralPath) shape).curveTo(265.33902, 123.341, 265.65802, 123.744,
                265.65802, 124.549);
        ((GeneralPath) shape).lineTo(265.65802, 124.804);
        ((GeneralPath) shape).lineTo(264.83902, 124.804);
        ((GeneralPath) shape).curveTo(264.84903, 124.705, 264.85403, 124.64,
                264.85403, 124.61);
        ((GeneralPath) shape).curveTo(264.85403, 124.222, 264.70505, 124.028,
                264.40305, 124.028);
        ((GeneralPath) shape).curveTo(263.97504, 124.028, 263.75806, 124.315,
                263.75806, 124.891);
        ((GeneralPath) shape).lineTo(263.75806, 127.03);
        ((GeneralPath) shape).lineTo(262.89005, 127.03);
        ((GeneralPath) shape).lineTo(262.89005, 123.398);
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
        ((GeneralPath) shape).moveTo(268.479, 124.838);
        ((GeneralPath) shape).lineTo(268.475, 124.693);
        ((GeneralPath) shape).curveTo(268.475, 124.404, 268.426, 124.218,
                268.325, 124.13);
        ((GeneralPath) shape).curveTo(268.226, 124.045, 268.009, 124.001,
                267.67502, 124.001);
        ((GeneralPath) shape).curveTo(267.35303, 124.001, 267.14203, 124.052,
                267.044, 124.157);
        ((GeneralPath) shape).curveTo(266.948, 124.259995, 266.898, 124.488,
                266.898, 124.837);
        ((GeneralPath) shape).lineTo(268.479, 124.837);
        ((GeneralPath) shape).moveTo(268.471, 125.868);
        ((GeneralPath) shape).lineTo(269.34302, 125.868);
        ((GeneralPath) shape).lineTo(269.34302, 126.008995);
        ((GeneralPath) shape).curveTo(269.34302, 126.716995, 268.812, 127.072,
                267.751, 127.072);
        ((GeneralPath) shape).curveTo(267.03, 127.072, 266.56, 126.95, 266.336,
                126.704);
        ((GeneralPath) shape).curveTo(266.114, 126.459, 266.003, 125.938,
                266.003, 125.142006);
        ((GeneralPath) shape).curveTo(266.003, 124.435005, 266.11798,
                123.96001, 266.352, 123.717);
        ((GeneralPath) shape).curveTo(266.58298, 123.47401, 267.039,
                123.352005, 267.71298, 123.352005);
        ((GeneralPath) shape).curveTo(268.35898, 123.352005, 268.79398, 123.47,
                269.01297, 123.70701);
        ((GeneralPath) shape).curveTo(269.23297, 123.94201, 269.34296,
                124.408005, 269.34296, 125.10301);
        ((GeneralPath) shape).lineTo(269.34296, 125.36901);
        ((GeneralPath) shape).lineTo(266.88995, 125.36901);
        ((GeneralPath) shape).curveTo(266.88596, 125.44901, 266.88196,
                125.502014, 266.88196, 125.529015);
        ((GeneralPath) shape).curveTo(266.88196, 125.88602, 266.93695,
                126.124016, 267.04697, 126.24301);
        ((GeneralPath) shape).curveTo(267.15695, 126.36101, 267.37497,
                126.42101, 267.70496, 126.42101);
        ((GeneralPath) shape).curveTo(268.02396, 126.42101, 268.22995,
                126.38702, 268.32697, 126.31702);
        ((GeneralPath) shape).curveTo(268.422, 126.25, 268.471, 126.1, 268.471,
                125.868);
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
        ((GeneralPath) shape).moveTo(275.142, 123.398);
        ((GeneralPath) shape).lineTo(274.33, 127.03);
        ((GeneralPath) shape).lineTo(273.04797, 127.03);
        ((GeneralPath) shape).lineTo(272.68396, 125.404);
        ((GeneralPath) shape).curveTo(272.62097, 125.123, 272.57397,
                124.909996, 272.54395, 124.762);
        ((GeneralPath) shape).lineTo(272.47995, 124.443);
        ((GeneralPath) shape).lineTo(272.41495, 124.124);
        ((GeneralPath) shape).lineTo(272.38895, 124.124);
        ((GeneralPath) shape).lineTo(272.32495, 124.443);
        ((GeneralPath) shape).lineTo(272.26096, 124.766);
        ((GeneralPath) shape).curveTo(272.23495, 124.907, 272.18896,
                125.119995, 272.12497, 125.404);
        ((GeneralPath) shape).lineTo(271.76096, 127.03);
        ((GeneralPath) shape).lineTo(270.45996, 127.03);
        ((GeneralPath) shape).lineTo(269.65295, 123.397995);
        ((GeneralPath) shape).lineTo(270.52795, 123.397995);
        ((GeneralPath) shape).lineTo(270.87695, 125.08099);
        ((GeneralPath) shape).curveTo(270.91895, 125.288994, 270.96194,
                125.51599, 271.00595, 125.756996);
        ((GeneralPath) shape).lineTo(271.06696, 126.09499);
        ((GeneralPath) shape).lineTo(271.13095, 126.43299);
        ((GeneralPath) shape).lineTo(271.14597, 126.43299);
        ((GeneralPath) shape).lineTo(271.22195, 126.09499);
        ((GeneralPath) shape).lineTo(271.29395, 125.756996);
        ((GeneralPath) shape).curveTo(271.33194, 125.588, 271.38293, 125.363,
                271.44894, 125.084);
        ((GeneralPath) shape).lineTo(271.84296, 123.397);
        ((GeneralPath) shape).lineTo(272.94995, 123.397);
        ((GeneralPath) shape).lineTo(273.34396, 125.084);
        ((GeneralPath) shape).curveTo(273.41595, 125.399, 273.46896, 125.624,
                273.49997, 125.756996);
        ((GeneralPath) shape).lineTo(273.57196, 126.09499);
        ((GeneralPath) shape).lineTo(273.64395, 126.43299);
        ((GeneralPath) shape).lineTo(273.66296, 126.43299);
        ((GeneralPath) shape).lineTo(273.72797, 126.09499);
        ((GeneralPath) shape).lineTo(273.78897, 125.756996);
        ((GeneralPath) shape).curveTo(273.83096, 125.524994, 273.87598,
                125.298996, 273.92197, 125.08099);
        ((GeneralPath) shape).lineTo(274.27496, 123.397995);
        ((GeneralPath) shape).lineTo(275.142, 123.397995);
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
        ((GeneralPath) shape).moveTo(276.409, 123.398);
        ((GeneralPath) shape).lineTo(276.409, 124.333);
        ((GeneralPath) shape).lineTo(275.541, 124.333);
        ((GeneralPath) shape).lineTo(275.541, 123.398);
        ((GeneralPath) shape).lineTo(276.409, 123.398);
        ((GeneralPath) shape).moveTo(276.409, 126.096);
        ((GeneralPath) shape).lineTo(276.409, 127.031);
        ((GeneralPath) shape).lineTo(275.541, 127.031);
        ((GeneralPath) shape).lineTo(275.541, 126.096);
        ((GeneralPath) shape).lineTo(276.409, 126.096);
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
        ((GeneralPath) shape).moveTo(260.13, 136.674);
        ((GeneralPath) shape).lineTo(262.40802, 136.674);
        ((GeneralPath) shape).lineTo(262.42303, 137.703);
        ((GeneralPath) shape).curveTo(262.42303, 138.452, 262.28302, 138.936,
                262.00003, 139.153);
        ((GeneralPath) shape).curveTo(261.72003, 139.369, 261.09003, 139.478,
                260.11404, 139.478);
        ((GeneralPath) shape).curveTo(259.21805, 139.478, 258.62704, 139.333,
                258.33505, 139.045);
        ((GeneralPath) shape).curveTo(258.04504, 138.756, 257.89905, 138.16699,
                257.89905, 137.278);
        ((GeneralPath) shape).curveTo(257.89905, 136.144, 257.95605, 135.428,
                258.07306, 135.128);
        ((GeneralPath) shape).curveTo(258.21707, 134.763, 258.43506, 134.518,
                258.72906, 134.391);
        ((GeneralPath) shape).curveTo(259.02106, 134.266, 259.52005, 134.201,
                260.22305, 134.201);
        ((GeneralPath) shape).curveTo(261.14206, 134.201, 261.73605, 134.298,
                262.00507, 134.49501);
        ((GeneralPath) shape).curveTo(262.27206, 134.69101, 262.40707, 135.126,
                262.40707, 135.8);
        ((GeneralPath) shape).lineTo(261.41406, 135.8);
        ((GeneralPath) shape).curveTo(261.39706, 135.462, 261.32306, 135.251,
                261.19208, 135.168);
        ((GeneralPath) shape).curveTo(261.06308, 135.086, 260.73508, 135.044,
                260.21207, 135.044);
        ((GeneralPath) shape).curveTo(259.64407, 135.044, 259.28308, 135.11401,
                259.12906, 135.257);
        ((GeneralPath) shape).curveTo(258.97806, 135.397, 258.90005, 135.73201,
                258.90005, 136.256);
        ((GeneralPath) shape).lineTo(258.89606, 136.777);
        ((GeneralPath) shape).lineTo(258.90405, 137.44199);
        ((GeneralPath) shape).curveTo(258.90405, 137.95499, 258.98004,
                138.28299, 259.13205, 138.42398);
        ((GeneralPath) shape).curveTo(259.28406, 138.56497, 259.63406,
                138.63498, 260.18604, 138.63498);
        ((GeneralPath) shape).curveTo(260.72104, 138.63498, 261.06204,
                138.57597, 261.20804, 138.45598);
        ((GeneralPath) shape).curveTo(261.35205, 138.33798, 261.42603,
                138.05698, 261.42603, 137.61198);
        ((GeneralPath) shape).lineTo(261.43002, 137.39998);
        ((GeneralPath) shape).lineTo(260.13004, 137.39998);
        ((GeneralPath) shape).lineTo(260.13004, 136.674);
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
        ((GeneralPath) shape).moveTo(266.303, 135.8);
        ((GeneralPath) shape).lineTo(266.303, 139.432);
        ((GeneralPath) shape).lineTo(265.43402, 139.432);
        ((GeneralPath) shape).lineTo(265.48303, 138.809);
        ((GeneralPath) shape).lineTo(265.46802, 138.80501);
        ((GeneralPath) shape).curveTo(265.30002, 139.24901, 264.91, 139.473,
                264.30002, 139.473);
        ((GeneralPath) shape).curveTo(263.48, 139.473, 263.06802, 139.063,
                263.06802, 138.23901);
        ((GeneralPath) shape).lineTo(263.06802, 135.8);
        ((GeneralPath) shape).lineTo(263.93604, 135.8);
        ((GeneralPath) shape).lineTo(263.93604, 138.03);
        ((GeneralPath) shape).curveTo(263.93604, 138.338, 263.97803, 138.541,
                264.06503, 138.64);
        ((GeneralPath) shape).curveTo(264.15002, 138.737, 264.32904, 138.786,
                264.60004, 138.786);
        ((GeneralPath) shape).curveTo(265.15604, 138.786, 265.43405, 138.452,
                265.43405, 137.78299);
        ((GeneralPath) shape).lineTo(265.43405, 135.8);
        ((GeneralPath) shape).lineTo(266.303, 135.8);
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
        ((GeneralPath) shape).moveTo(267.184, 135.8);
        ((GeneralPath) shape).lineTo(268.04498, 135.8);
        ((GeneralPath) shape).lineTo(268.011, 136.412);
        ((GeneralPath) shape).lineTo(268.03, 136.416);
        ((GeneralPath) shape).curveTo(268.198, 135.979, 268.578, 135.758,
                269.167, 135.758);
        ((GeneralPath) shape).curveTo(270.024, 135.758, 270.452, 136.157,
                270.452, 136.959);
        ((GeneralPath) shape).lineTo(270.452, 139.432);
        ((GeneralPath) shape).lineTo(269.58398, 139.432);
        ((GeneralPath) shape).lineTo(269.58398, 137.10701);
        ((GeneralPath) shape).lineTo(269.56497, 136.852);
        ((GeneralPath) shape).curveTo(269.52496, 136.582, 269.31296, 136.446,
                268.92798, 136.446);
        ((GeneralPath) shape).curveTo(268.34497, 136.446, 268.05197, 136.72299,
                268.05197, 137.278);
        ((GeneralPath) shape).lineTo(268.05197, 139.432);
        ((GeneralPath) shape).lineTo(267.18396, 139.432);
        ((GeneralPath) shape).lineTo(267.18396, 135.8);
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
        ((GeneralPath) shape).moveTo(271.3, 135.8);
        ((GeneralPath) shape).lineTo(272.16098, 135.8);
        ((GeneralPath) shape).lineTo(272.12598, 136.412);
        ((GeneralPath) shape).lineTo(272.14597, 136.416);
        ((GeneralPath) shape).curveTo(272.31396, 135.979, 272.69296, 135.758,
                273.28296, 135.758);
        ((GeneralPath) shape).curveTo(274.13995, 135.758, 274.56796, 136.157,
                274.56796, 136.959);
        ((GeneralPath) shape).lineTo(274.56796, 139.432);
        ((GeneralPath) shape).lineTo(273.7, 139.432);
        ((GeneralPath) shape).lineTo(273.7, 137.10701);
        ((GeneralPath) shape).lineTo(273.681, 136.852);
        ((GeneralPath) shape).curveTo(273.641, 136.582, 273.429, 136.446,
                273.044, 136.446);
        ((GeneralPath) shape).curveTo(272.46, 136.446, 272.168, 136.72299,
                272.168, 137.278);
        ((GeneralPath) shape).lineTo(272.168, 139.432);
        ((GeneralPath) shape).lineTo(271.3, 139.432);
        ((GeneralPath) shape).lineTo(271.3, 135.8);
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
        ((GeneralPath) shape).moveTo(277.759, 137.24);
        ((GeneralPath) shape).lineTo(277.755, 137.095);
        ((GeneralPath) shape).curveTo(277.755, 136.806, 277.706, 136.62,
                277.605, 136.533);
        ((GeneralPath) shape).curveTo(277.506, 136.448, 277.289, 136.404,
                276.95502, 136.404);
        ((GeneralPath) shape).curveTo(276.63202, 136.404, 276.42203, 136.455,
                276.32303, 136.559);
        ((GeneralPath) shape).curveTo(276.22702, 136.662, 276.17703, 136.89,
                276.17703, 137.239);
        ((GeneralPath) shape).lineTo(277.759, 137.239);
        ((GeneralPath) shape).moveTo(277.751, 138.27);
        ((GeneralPath) shape).lineTo(278.62302, 138.27);
        ((GeneralPath) shape).lineTo(278.62302, 138.41);
        ((GeneralPath) shape).curveTo(278.62302, 139.119, 278.092, 139.474,
                277.031, 139.474);
        ((GeneralPath) shape).curveTo(276.31, 139.474, 275.84, 139.352,
                275.616, 139.106);
        ((GeneralPath) shape).curveTo(275.394, 138.86101, 275.283, 138.34,
                275.283, 137.544);
        ((GeneralPath) shape).curveTo(275.283, 136.83801, 275.399, 136.363,
                275.632, 136.12001);
        ((GeneralPath) shape).curveTo(275.86298, 135.87701, 276.319, 135.755,
                276.99298, 135.755);
        ((GeneralPath) shape).curveTo(277.63898, 135.755, 278.07397, 135.873,
                278.29297, 136.11);
        ((GeneralPath) shape).curveTo(278.51297, 136.346, 278.62296, 136.811,
                278.62296, 137.506);
        ((GeneralPath) shape).lineTo(278.62296, 137.772);
        ((GeneralPath) shape).lineTo(276.16995, 137.772);
        ((GeneralPath) shape).curveTo(276.16595, 137.852, 276.16196, 137.905,
                276.16196, 137.932);
        ((GeneralPath) shape).curveTo(276.16196, 138.28801, 276.21695, 138.526,
                276.32697, 138.64601);
        ((GeneralPath) shape).curveTo(276.43695, 138.764, 276.65497, 138.82501,
                276.98495, 138.82501);
        ((GeneralPath) shape).curveTo(277.30396, 138.82501, 277.50995,
                138.79001, 277.60696, 138.72101);
        ((GeneralPath) shape).curveTo(277.702, 138.651, 277.751, 138.501,
                277.751, 138.27);
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
        ((GeneralPath) shape).moveTo(279.367, 135.8);
        ((GeneralPath) shape).lineTo(280.23502, 135.8);
        ((GeneralPath) shape).lineTo(280.182, 136.309);
        ((GeneralPath) shape).lineTo(280.20102, 136.31201);
        ((GeneralPath) shape).curveTo(280.40802, 135.93301, 280.734, 135.74301,
                281.18002, 135.74301);
        ((GeneralPath) shape).curveTo(281.81702, 135.74301, 282.13602, 136.145,
                282.13602, 136.951);
        ((GeneralPath) shape).lineTo(282.13602, 137.205);
        ((GeneralPath) shape).lineTo(281.31702, 137.205);
        ((GeneralPath) shape).curveTo(281.32703, 137.106, 281.33203, 137.041,
                281.33203, 137.01201);
        ((GeneralPath) shape).curveTo(281.33203, 136.62401, 281.18304,
                136.43001, 280.88104, 136.43001);
        ((GeneralPath) shape).curveTo(280.45303, 136.43001, 280.23605,
                136.71701, 280.23605, 137.292);
        ((GeneralPath) shape).lineTo(280.23605, 139.431);
        ((GeneralPath) shape).lineTo(279.36804, 139.431);
        ((GeneralPath) shape).lineTo(279.36804, 135.8);
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
        ((GeneralPath) shape).moveTo(285.598, 135.8);
        ((GeneralPath) shape).lineTo(284.646, 139.66);
        ((GeneralPath) shape).curveTo(284.513, 140.207, 284.346, 140.574,
                284.147, 140.764);
        ((GeneralPath) shape).curveTo(283.95, 140.95201, 283.631, 141.04701,
                283.193, 141.04701);
        ((GeneralPath) shape).curveTo(283.094, 141.04701, 282.992, 141.04301,
                282.88498, 141.031);
        ((GeneralPath) shape).lineTo(282.88498, 140.389);
        ((GeneralPath) shape).curveTo(282.96097, 140.393, 283.024, 140.397,
                283.07498, 140.397);
        ((GeneralPath) shape).curveTo(283.443, 140.397, 283.67798, 140.076,
                283.77597, 139.432);
        ((GeneralPath) shape).lineTo(283.33298, 139.432);
        ((GeneralPath) shape).lineTo(282.14597, 135.8);
        ((GeneralPath) shape).lineTo(283.07898, 135.8);
        ((GeneralPath) shape).lineTo(283.53397, 137.339);
        ((GeneralPath) shape).lineTo(283.76196, 138.11);
        ((GeneralPath) shape).curveTo(283.77396, 138.159, 283.80896, 138.288,
                283.86795, 138.497);
        ((GeneralPath) shape).lineTo(283.97794, 138.881);
        ((GeneralPath) shape).lineTo(283.99695, 138.881);
        ((GeneralPath) shape).lineTo(284.07693, 138.497);
        ((GeneralPath) shape).curveTo(284.11694, 138.29999, 284.14294, 138.17,
                284.15692, 138.11);
        ((GeneralPath) shape).lineTo(284.33493, 137.339);
        ((GeneralPath) shape).lineTo(284.68393, 135.8);
        ((GeneralPath) shape).lineTo(285.598, 135.8);
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
        ((GeneralPath) shape).moveTo(291.801, 135.758);
        ((GeneralPath) shape).lineTo(290.84198, 135.758);
        ((GeneralPath) shape).curveTo(290.83798, 135.711, 290.83398, 135.675,
                290.83398, 135.652);
        ((GeneralPath) shape).curveTo(290.81097, 135.361, 290.74698, 135.17899,
                290.641, 135.103);
        ((GeneralPath) shape).curveTo(290.535, 135.02899, 290.285, 134.991,
                289.891, 134.991);
        ((GeneralPath) shape).curveTo(289.426, 134.991, 289.123, 135.033,
                288.979, 135.12);
        ((GeneralPath) shape).curveTo(288.837, 135.205, 288.765, 135.386,
                288.765, 135.663);
        ((GeneralPath) shape).curveTo(288.765, 135.98999, 288.82202, 136.185,
                288.93903, 136.252);
        ((GeneralPath) shape).curveTo(289.05502, 136.316, 289.44003, 136.368,
                290.09204, 136.40399);
        ((GeneralPath) shape).curveTo(290.86203, 136.446, 291.36005, 136.555,
                291.58804, 136.734);
        ((GeneralPath) shape).curveTo(291.81406, 136.911, 291.92804, 137.27899,
                291.92804, 137.83899);
        ((GeneralPath) shape).curveTo(291.92804, 138.527, 291.79504, 138.97299,
                291.53003, 139.174);
        ((GeneralPath) shape).curveTo(291.265, 139.37599, 290.68002, 139.476,
                289.77502, 139.476);
        ((GeneralPath) shape).curveTo(288.96204, 139.476, 288.42102, 139.377,
                288.15402, 139.18);
        ((GeneralPath) shape).curveTo(287.88803, 138.982, 287.75403, 138.583,
                287.75403, 137.97899);
        ((GeneralPath) shape).lineTo(287.75003, 137.78899);
        ((GeneralPath) shape).lineTo(288.70602, 137.78899);
        ((GeneralPath) shape).lineTo(288.709, 137.89899);
        ((GeneralPath) shape).curveTo(288.709, 138.25998, 288.772, 138.48198,
                288.89902, 138.56398);
        ((GeneralPath) shape).curveTo(289.02402, 138.64398, 289.36902,
                138.68597, 289.93402, 138.68597);
        ((GeneralPath) shape).curveTo(290.37402, 138.68597, 290.65402,
                138.63997, 290.77603, 138.54497);
        ((GeneralPath) shape).curveTo(290.89703, 138.45197, 290.95804,
                138.23596, 290.95804, 137.89597);
        ((GeneralPath) shape).curveTo(290.95804, 137.64597, 290.91205,
                137.47997, 290.81903, 137.39597);
        ((GeneralPath) shape).curveTo(290.72803, 137.31396, 290.52902,
                137.26497, 290.22205, 137.24597);
        ((GeneralPath) shape).lineTo(289.68005, 137.21198);
        ((GeneralPath) shape).curveTo(288.86105, 137.16498, 288.33804,
                137.04997, 288.11105, 136.86998);
        ((GeneralPath) shape).curveTo(287.88306, 136.69098, 287.77005,
                136.30399, 287.77005, 135.71198);
        ((GeneralPath) shape).curveTo(287.77005, 135.10797, 287.90704,
                134.70297, 288.18204, 134.50197);
        ((GeneralPath) shape).curveTo(288.45505, 134.30096, 289.00305,
                134.19997, 289.82605, 134.19997);
        ((GeneralPath) shape).curveTo(290.60306, 134.19997, 291.12805,
                134.29097, 291.39905, 134.47696);
        ((GeneralPath) shape).curveTo(291.66904, 134.66196, 291.80505,
                135.02196, 291.80505, 135.55995);
        ((GeneralPath) shape).lineTo(291.80505, 135.758);
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
        ((GeneralPath) shape).moveTo(293.453, 134.246);
        ((GeneralPath) shape).lineTo(293.453, 137.213);
        ((GeneralPath) shape).lineTo(293.676, 137.213);
        ((GeneralPath) shape).lineTo(294.654, 135.8);
        ((GeneralPath) shape).lineTo(295.663, 135.8);
        ((GeneralPath) shape).lineTo(294.4, 137.51);
        ((GeneralPath) shape).lineTo(295.921, 139.432);
        ((GeneralPath) shape).lineTo(294.844, 139.432);
        ((GeneralPath) shape).lineTo(293.665, 137.821);
        ((GeneralPath) shape).lineTo(293.453, 137.821);
        ((GeneralPath) shape).lineTo(293.453, 139.432);
        ((GeneralPath) shape).lineTo(292.584, 139.432);
        ((GeneralPath) shape).lineTo(292.584, 134.246);
        ((GeneralPath) shape).lineTo(293.453, 134.246);
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
        ((GeneralPath) shape).moveTo(297.18, 135.8);
        ((GeneralPath) shape).lineTo(297.18, 139.432);
        ((GeneralPath) shape).lineTo(296.31198, 139.432);
        ((GeneralPath) shape).lineTo(296.31198, 135.8);
        ((GeneralPath) shape).lineTo(297.18, 135.8);
        ((GeneralPath) shape).moveTo(297.18, 134.246);
        ((GeneralPath) shape).lineTo(297.18, 134.972);
        ((GeneralPath) shape).lineTo(296.31198, 134.972);
        ((GeneralPath) shape).lineTo(296.31198, 134.246);
        ((GeneralPath) shape).lineTo(297.18, 134.246);
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
        shape = new Rectangle2D.Double(298.0360107421875, 134.24600219726562,
                0.8679999709129333, 5.185999870300293);
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
        shape = new Rectangle2D.Double(299.760009765625, 134.24600219726562,
                0.8679999709129333, 5.185999870300293);
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
        ((GeneralPath) shape).moveTo(302.314, 135.8);
        ((GeneralPath) shape).lineTo(302.314, 136.735);
        ((GeneralPath) shape).lineTo(301.44598, 136.735);
        ((GeneralPath) shape).lineTo(301.44598, 135.8);
        ((GeneralPath) shape).lineTo(302.314, 135.8);
        ((GeneralPath) shape).moveTo(302.314, 138.497);
        ((GeneralPath) shape).lineTo(302.314, 139.43199);
        ((GeneralPath) shape).lineTo(301.44598, 139.43199);
        ((GeneralPath) shape).lineTo(301.44598, 138.497);
        ((GeneralPath) shape).lineTo(302.314, 138.497);
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
        ((GeneralPath) shape).moveTo(329.384, 138.604);
        ((GeneralPath) shape).lineTo(330.782, 138.604);
        ((GeneralPath) shape).curveTo(331.25302, 138.604, 331.55502, 138.496,
                331.694, 138.279);
        ((GeneralPath) shape).curveTo(331.831, 138.063, 331.901, 137.58601,
                331.901, 136.845);
        ((GeneralPath) shape).curveTo(331.901, 136.081, 331.84, 135.595,
                331.715, 135.386);
        ((GeneralPath) shape).curveTo(331.591, 135.179, 331.301, 135.074,
                330.843, 135.074);
        ((GeneralPath) shape).lineTo(329.383, 135.074);
        ((GeneralPath) shape).lineTo(329.383, 138.604);
        ((GeneralPath) shape).moveTo(328.401, 139.432);
        ((GeneralPath) shape).lineTo(328.401, 134.246);
        ((GeneralPath) shape).lineTo(330.945, 134.246);
        ((GeneralPath) shape).curveTo(331.668, 134.246, 332.174, 134.404,
                332.46402, 134.72101);
        ((GeneralPath) shape).curveTo(332.75302, 135.03601, 332.89902, 135.591,
                332.89902, 136.38501);
        ((GeneralPath) shape).curveTo(332.89902, 137.67902, 332.78403,
                138.51102, 332.55002, 138.87901);
        ((GeneralPath) shape).curveTo(332.31903, 139.24802, 331.79303, 139.432,
                330.97702, 139.432);
        ((GeneralPath) shape).lineTo(328.401, 139.432);
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
        ((GeneralPath) shape).moveTo(333.607, 135.8);
        ((GeneralPath) shape).lineTo(334.47598, 135.8);
        ((GeneralPath) shape).lineTo(334.422, 136.309);
        ((GeneralPath) shape).lineTo(334.441, 136.31201);
        ((GeneralPath) shape).curveTo(334.648, 135.93301, 334.974, 135.74301,
                335.42, 135.74301);
        ((GeneralPath) shape).curveTo(336.057, 135.74301, 336.376, 136.145,
                336.376, 136.951);
        ((GeneralPath) shape).lineTo(336.376, 137.205);
        ((GeneralPath) shape).lineTo(335.557, 137.205);
        ((GeneralPath) shape).curveTo(335.56702, 137.106, 335.57202, 137.041,
                335.57202, 137.01201);
        ((GeneralPath) shape).curveTo(335.57202, 136.62401, 335.42303,
                136.43001, 335.122, 136.43001);
        ((GeneralPath) shape).curveTo(334.69302, 136.43001, 334.47702,
                136.71701, 334.47702, 137.292);
        ((GeneralPath) shape).lineTo(334.47702, 139.431);
        ((GeneralPath) shape).lineTo(333.60803, 139.431);
        ((GeneralPath) shape).lineTo(333.60803, 135.8);
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
        ((GeneralPath) shape).moveTo(337.723, 135.8);
        ((GeneralPath) shape).lineTo(337.723, 139.432);
        ((GeneralPath) shape).lineTo(336.85498, 139.432);
        ((GeneralPath) shape).lineTo(336.85498, 135.8);
        ((GeneralPath) shape).lineTo(337.723, 135.8);
        ((GeneralPath) shape).moveTo(337.723, 134.246);
        ((GeneralPath) shape).lineTo(337.723, 134.972);
        ((GeneralPath) shape).lineTo(336.85498, 134.972);
        ((GeneralPath) shape).lineTo(336.85498, 134.246);
        ((GeneralPath) shape).lineTo(337.723, 134.246);
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
        ((GeneralPath) shape).moveTo(341.601, 135.8);
        ((GeneralPath) shape).lineTo(340.562, 139.432);
        ((GeneralPath) shape).lineTo(339.20502, 139.432);
        ((GeneralPath) shape).lineTo(338.113, 135.8);
        ((GeneralPath) shape).lineTo(339.034, 135.8);
        ((GeneralPath) shape).lineTo(339.512, 137.475);
        ((GeneralPath) shape).curveTo(339.576, 137.707, 339.637, 137.933,
                339.694, 138.151);
        ((GeneralPath) shape).lineTo(339.782, 138.489);
        ((GeneralPath) shape).lineTo(339.86902, 138.827);
        ((GeneralPath) shape).lineTo(339.889, 138.827);
        ((GeneralPath) shape).lineTo(339.96802, 138.489);
        ((GeneralPath) shape).lineTo(340.048, 138.155);
        ((GeneralPath) shape).curveTo(340.109, 137.903, 340.165, 137.676,
                340.21802, 137.479);
        ((GeneralPath) shape).lineTo(340.665, 135.8);
        ((GeneralPath) shape).lineTo(341.601, 135.8);
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
        ((GeneralPath) shape).moveTo(342.855, 135.8);
        ((GeneralPath) shape).lineTo(342.855, 139.432);
        ((GeneralPath) shape).lineTo(341.987, 139.432);
        ((GeneralPath) shape).lineTo(341.987, 135.8);
        ((GeneralPath) shape).lineTo(342.855, 135.8);
        ((GeneralPath) shape).moveTo(342.855, 134.246);
        ((GeneralPath) shape).lineTo(342.855, 134.972);
        ((GeneralPath) shape).lineTo(341.987, 134.972);
        ((GeneralPath) shape).lineTo(341.987, 134.246);
        ((GeneralPath) shape).lineTo(342.855, 134.246);
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
        ((GeneralPath) shape).moveTo(343.711, 135.8);
        ((GeneralPath) shape).lineTo(344.572, 135.8);
        ((GeneralPath) shape).lineTo(344.537, 136.412);
        ((GeneralPath) shape).lineTo(344.55698, 136.416);
        ((GeneralPath) shape).curveTo(344.72598, 135.979, 345.10498, 135.758,
                345.69498, 135.758);
        ((GeneralPath) shape).curveTo(346.55096, 135.758, 346.97998, 136.157,
                346.97998, 136.959);
        ((GeneralPath) shape).lineTo(346.97998, 139.432);
        ((GeneralPath) shape).lineTo(346.11197, 139.432);
        ((GeneralPath) shape).lineTo(346.11197, 137.10701);
        ((GeneralPath) shape).lineTo(346.09198, 136.852);
        ((GeneralPath) shape).curveTo(346.05298, 136.582, 345.83997, 136.446,
                345.455, 136.446);
        ((GeneralPath) shape).curveTo(344.87097, 136.446, 344.57898, 136.72299,
                344.57898, 137.278);
        ((GeneralPath) shape).lineTo(344.57898, 139.432);
        ((GeneralPath) shape).lineTo(343.71097, 139.432);
        ((GeneralPath) shape).lineTo(343.71097, 135.8);
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
        ((GeneralPath) shape).moveTo(349.358, 136.446);
        ((GeneralPath) shape).curveTo(349.025, 136.446, 348.81, 136.512,
                348.715, 136.649);
        ((GeneralPath) shape).curveTo(348.62, 136.784, 348.572, 137.092,
                348.572, 137.574);
        ((GeneralPath) shape).curveTo(348.572, 138.085, 348.62, 138.412,
                348.715, 138.554);
        ((GeneralPath) shape).curveTo(348.81, 138.695, 349.02798, 138.767,
                349.369, 138.767);
        ((GeneralPath) shape).curveTo(349.71, 138.767, 349.93, 138.69499,
                350.03198, 138.549);
        ((GeneralPath) shape).curveTo(350.133, 138.40399, 350.184, 138.081,
                350.184, 137.582);
        ((GeneralPath) shape).curveTo(350.184, 137.103, 350.134, 136.796,
                350.03198, 136.655);
        ((GeneralPath) shape).curveTo(349.933, 136.516, 349.707, 136.446,
                349.358, 136.446);
        ((GeneralPath) shape).moveTo(351.068, 135.8);
        ((GeneralPath) shape).lineTo(351.068, 139.501);
        ((GeneralPath) shape).curveTo(351.068, 140.093, 350.949, 140.5, 350.71,
                140.72);
        ((GeneralPath) shape).curveTo(350.472, 140.941, 350.031, 141.051,
                349.38898, 141.051);
        ((GeneralPath) shape).curveTo(348.76797, 141.051, 348.352, 140.968,
                348.14197, 140.801);
        ((GeneralPath) shape).curveTo(347.93295, 140.63399, 347.82797, 140.303,
                347.82797, 139.80899);
        ((GeneralPath) shape).lineTo(348.66898, 139.80899);
        ((GeneralPath) shape).curveTo(348.66898, 140.04399, 348.71298,
                140.19398, 348.80197, 140.26299);
        ((GeneralPath) shape).curveTo(348.88998, 140.32898, 349.08597,
                140.36398, 349.39398, 140.36398);
        ((GeneralPath) shape).curveTo(349.93198, 140.36398, 350.202, 140.14198,
                350.202, 139.69498);
        ((GeneralPath) shape).lineTo(350.202, 138.87898);
        ((GeneralPath) shape).lineTo(350.18298, 138.87498);
        ((GeneralPath) shape).curveTo(350.029, 139.26198, 349.67398, 139.45598,
                349.12097, 139.45598);
        ((GeneralPath) shape).curveTo(348.57297, 139.45598, 348.19696,
                139.32298, 347.99597, 139.05698);
        ((GeneralPath) shape).curveTo(347.79498, 138.79097, 347.69498,
                138.29498, 347.69498, 137.56798);
        ((GeneralPath) shape).curveTo(347.69498, 136.88397, 347.79498,
                136.41298, 347.99597, 136.15097);
        ((GeneralPath) shape).curveTo(348.19696, 135.89098, 348.56097,
                135.75897, 349.08997, 135.75897);
        ((GeneralPath) shape).curveTo(349.66696, 135.75897, 350.04398,
                135.97197, 350.22498, 136.40198);
        ((GeneralPath) shape).lineTo(350.244, 136.40198);
        ((GeneralPath) shape).lineTo(350.2, 135.8);
        ((GeneralPath) shape).lineTo(351.068, 135.8);
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
        ((GeneralPath) shape).moveTo(357.729, 135.758);
        ((GeneralPath) shape).lineTo(356.769, 135.758);
        ((GeneralPath) shape).curveTo(356.765, 135.711, 356.76102, 135.675,
                356.76102, 135.652);
        ((GeneralPath) shape).curveTo(356.739, 135.361, 356.674, 135.17899,
                356.56903, 135.103);
        ((GeneralPath) shape).curveTo(356.46304, 135.02899, 356.21204, 134.991,
                355.81802, 134.991);
        ((GeneralPath) shape).curveTo(355.35303, 134.991, 355.049, 135.033,
                354.90604, 135.12);
        ((GeneralPath) shape).curveTo(354.76303, 135.205, 354.69104, 135.386,
                354.69104, 135.663);
        ((GeneralPath) shape).curveTo(354.69104, 135.98999, 354.74905, 136.185,
                354.86603, 136.252);
        ((GeneralPath) shape).curveTo(354.98102, 136.316, 355.36703, 136.368,
                356.01804, 136.40399);
        ((GeneralPath) shape).curveTo(356.78903, 136.446, 357.28705, 136.555,
                357.51404, 136.734);
        ((GeneralPath) shape).curveTo(357.74005, 136.911, 357.85403, 137.27899,
                357.85403, 137.83899);
        ((GeneralPath) shape).curveTo(357.85403, 138.527, 357.72104, 138.97299,
                357.45602, 139.174);
        ((GeneralPath) shape).curveTo(357.19003, 139.37599, 356.60602, 139.476,
                355.7, 139.476);
        ((GeneralPath) shape).curveTo(354.88702, 139.476, 354.346, 139.377,
                354.079, 139.18);
        ((GeneralPath) shape).curveTo(353.814, 138.982, 353.68002, 138.583,
                353.68002, 137.97899);
        ((GeneralPath) shape).lineTo(353.67603, 137.78899);
        ((GeneralPath) shape).lineTo(354.631, 137.78899);
        ((GeneralPath) shape).lineTo(354.635, 137.89899);
        ((GeneralPath) shape).curveTo(354.635, 138.25998, 354.698, 138.48198,
                354.824, 138.56398);
        ((GeneralPath) shape).curveTo(354.949, 138.64398, 355.295, 138.68597,
                355.859, 138.68597);
        ((GeneralPath) shape).curveTo(356.299, 138.68597, 356.58002, 138.63997,
                356.70102, 138.54497);
        ((GeneralPath) shape).curveTo(356.82303, 138.45197, 356.88403,
                138.23596, 356.88403, 137.89597);
        ((GeneralPath) shape).curveTo(356.88403, 137.64597, 356.83804,
                137.47997, 356.74503, 137.39597);
        ((GeneralPath) shape).curveTo(356.65402, 137.31396, 356.45502,
                137.26497, 356.14703, 137.24597);
        ((GeneralPath) shape).lineTo(355.60504, 137.21198);
        ((GeneralPath) shape).curveTo(354.78604, 137.16498, 354.26303,
                137.04997, 354.03604, 136.86998);
        ((GeneralPath) shape).curveTo(353.80804, 136.69098, 353.69403,
                136.30399, 353.69403, 135.71198);
        ((GeneralPath) shape).curveTo(353.69403, 135.10797, 353.83102,
                134.70297, 354.10602, 134.50197);
        ((GeneralPath) shape).curveTo(354.37802, 134.30096, 354.92603,
                134.19997, 355.75003, 134.19997);
        ((GeneralPath) shape).curveTo(356.52704, 134.19997, 357.05203,
                134.29097, 357.32303, 134.47696);
        ((GeneralPath) shape).curveTo(357.59302, 134.66196, 357.72903,
                135.02196, 357.72903, 135.55995);
        ((GeneralPath) shape).lineTo(357.72903, 135.758);
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
        ((GeneralPath) shape).moveTo(359.38, 134.246);
        ((GeneralPath) shape).lineTo(359.38, 137.213);
        ((GeneralPath) shape).lineTo(359.604, 137.213);
        ((GeneralPath) shape).lineTo(360.582, 135.8);
        ((GeneralPath) shape).lineTo(361.591, 135.8);
        ((GeneralPath) shape).lineTo(360.328, 137.51);
        ((GeneralPath) shape).lineTo(361.849, 139.432);
        ((GeneralPath) shape).lineTo(360.771, 139.432);
        ((GeneralPath) shape).lineTo(359.593, 137.821);
        ((GeneralPath) shape).lineTo(359.38, 137.821);
        ((GeneralPath) shape).lineTo(359.38, 139.432);
        ((GeneralPath) shape).lineTo(358.512, 139.432);
        ((GeneralPath) shape).lineTo(358.512, 134.246);
        ((GeneralPath) shape).lineTo(359.38, 134.246);
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
        ((GeneralPath) shape).moveTo(363.107, 135.8);
        ((GeneralPath) shape).lineTo(363.107, 139.432);
        ((GeneralPath) shape).lineTo(362.23898, 139.432);
        ((GeneralPath) shape).lineTo(362.23898, 135.8);
        ((GeneralPath) shape).lineTo(363.107, 135.8);
        ((GeneralPath) shape).moveTo(363.107, 134.246);
        ((GeneralPath) shape).lineTo(363.107, 134.972);
        ((GeneralPath) shape).lineTo(362.23898, 134.972);
        ((GeneralPath) shape).lineTo(362.23898, 134.246);
        ((GeneralPath) shape).lineTo(363.107, 134.246);
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
        shape = new Rectangle2D.Double(363.9630126953125, 134.24600219726562,
                0.8679999709129333, 5.185999870300293);
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
        shape = new Rectangle2D.Double(365.68798828125, 134.24600219726562,
                0.8679999709129333, 5.185999870300293);
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
        ((GeneralPath) shape).moveTo(368.241, 135.8);
        ((GeneralPath) shape).lineTo(368.241, 136.735);
        ((GeneralPath) shape).lineTo(367.373, 136.735);
        ((GeneralPath) shape).lineTo(367.373, 135.8);
        ((GeneralPath) shape).lineTo(368.241, 135.8);
        ((GeneralPath) shape).moveTo(368.241, 138.497);
        ((GeneralPath) shape).lineTo(368.241, 139.43199);
        ((GeneralPath) shape).lineTo(367.373, 139.43199);
        ((GeneralPath) shape).lineTo(367.373, 138.497);
        ((GeneralPath) shape).lineTo(368.241, 138.497);
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
        ((GeneralPath) shape).moveTo(330.396, 155.483);
        ((GeneralPath) shape).lineTo(332.08398, 155.483);
        ((GeneralPath) shape).curveTo(332.702, 155.483, 333.103, 155.35901,
                333.28198, 155.11);
        ((GeneralPath) shape).curveTo(333.46097, 154.863, 333.55197, 154.309,
                333.55197, 153.45);
        ((GeneralPath) shape).curveTo(333.55197, 152.507, 333.47397, 151.911,
                333.31897, 151.66);
        ((GeneralPath) shape).curveTo(333.16397, 151.41, 332.79196, 151.284,
                332.20297, 151.284);
        ((GeneralPath) shape).lineTo(330.39697, 151.284);
        ((GeneralPath) shape).lineTo(330.39697, 155.483);
        ((GeneralPath) shape).moveTo(329.812, 155.977);
        ((GeneralPath) shape).lineTo(329.812, 150.791);
        ((GeneralPath) shape).lineTo(332.208, 150.791);
        ((GeneralPath) shape).curveTo(332.94702, 150.791, 333.45, 150.955,
                333.71402, 151.281);
        ((GeneralPath) shape).curveTo(333.976, 151.608, 334.109, 152.233,
                334.109, 153.158);
        ((GeneralPath) shape).curveTo(334.109, 154.283, 333.993, 155.033,
                333.75702, 155.41101);
        ((GeneralPath) shape).curveTo(333.52502, 155.78702, 333.058, 155.977,
                332.35703, 155.977);
        ((GeneralPath) shape).lineTo(329.812, 155.977);
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
        ((GeneralPath) shape).moveTo(334.91, 152.345);
        ((GeneralPath) shape).lineTo(335.44, 152.345);
        ((GeneralPath) shape).lineTo(335.387, 152.763);
        ((GeneralPath) shape).lineTo(335.39798, 152.774);
        ((GeneralPath) shape).curveTo(335.607, 152.432, 335.95398, 152.261,
                336.43698, 152.261);
        ((GeneralPath) shape).curveTo(337.10498, 152.261, 337.439, 152.605,
                337.439, 153.294);
        ((GeneralPath) shape).lineTo(337.435, 153.54501);
        ((GeneralPath) shape).lineTo(336.912, 153.54501);
        ((GeneralPath) shape).lineTo(336.92297, 153.45401);
        ((GeneralPath) shape).curveTo(336.93097, 153.35901, 336.93497, 153.294,
                336.93497, 153.26001);
        ((GeneralPath) shape).curveTo(336.93497, 152.88701, 336.73398, 152.701,
                336.32898, 152.701);
        ((GeneralPath) shape).curveTo(335.73697, 152.701, 335.44098, 153.06601,
                335.44098, 153.79901);
        ((GeneralPath) shape).lineTo(335.44098, 155.97601);
        ((GeneralPath) shape).lineTo(334.91098, 155.97601);
        ((GeneralPath) shape).lineTo(334.91098, 152.345);
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
        ((GeneralPath) shape).moveTo(338.479, 152.345);
        ((GeneralPath) shape).lineTo(338.479, 155.977);
        ((GeneralPath) shape).lineTo(337.948, 155.977);
        ((GeneralPath) shape).lineTo(337.948, 152.345);
        ((GeneralPath) shape).lineTo(338.479, 152.345);
        ((GeneralPath) shape).moveTo(338.479, 150.791);
        ((GeneralPath) shape).lineTo(338.479, 151.388);
        ((GeneralPath) shape).lineTo(337.948, 151.388);
        ((GeneralPath) shape).lineTo(337.948, 150.791);
        ((GeneralPath) shape).lineTo(338.479, 150.791);
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
        ((GeneralPath) shape).moveTo(342.093, 152.345);
        ((GeneralPath) shape).lineTo(340.879, 155.977);
        ((GeneralPath) shape).lineTo(340.117, 155.977);
        ((GeneralPath) shape).lineTo(338.915, 152.345);
        ((GeneralPath) shape).lineTo(339.461, 152.345);
        ((GeneralPath) shape).lineTo(340.102, 154.321);
        ((GeneralPath) shape).lineTo(340.303, 154.94);
        ((GeneralPath) shape).lineTo(340.397, 155.251);
        ((GeneralPath) shape).lineTo(340.496, 155.563);
        ((GeneralPath) shape).lineTo(340.511, 155.563);
        ((GeneralPath) shape).lineTo(340.603, 155.255);
        ((GeneralPath) shape).lineTo(340.693, 154.944);
        ((GeneralPath) shape).lineTo(340.887, 154.328);
        ((GeneralPath) shape).lineTo(341.501, 152.345);
        ((GeneralPath) shape).lineTo(342.093, 152.345);
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
        ((GeneralPath) shape).moveTo(345.051, 153.846);
        ((GeneralPath) shape).lineTo(345.047, 153.67499);
        ((GeneralPath) shape).curveTo(345.047, 153.28398, 344.984, 153.02798,
                344.855, 152.90898);
        ((GeneralPath) shape).curveTo(344.726, 152.79099, 344.449, 152.73299,
                344.02002, 152.73299);
        ((GeneralPath) shape).curveTo(343.59103, 152.73299, 343.31302,
                152.80098, 343.18402, 152.93999);
        ((GeneralPath) shape).curveTo(343.057, 153.07698, 342.993, 153.37898,
                342.993, 153.846);
        ((GeneralPath) shape).lineTo(345.051, 153.846);
        ((GeneralPath) shape).moveTo(345.051, 154.879);
        ((GeneralPath) shape).lineTo(345.594, 154.879);
        ((GeneralPath) shape).lineTo(345.598, 155.012);
        ((GeneralPath) shape).curveTo(345.598, 155.388, 345.48398, 155.652,
                345.254, 155.804);
        ((GeneralPath) shape).curveTo(345.026, 155.954, 344.625, 156.03,
                344.05, 156.03);
        ((GeneralPath) shape).curveTo(343.383, 156.03, 342.94498, 155.908,
                342.737, 155.664);
        ((GeneralPath) shape).curveTo(342.52798, 155.421, 342.424, 154.906,
                342.424, 154.123);
        ((GeneralPath) shape).curveTo(342.424, 153.399, 342.528, 152.913,
                342.738, 152.664);
        ((GeneralPath) shape).curveTo(342.94702, 152.417, 343.358, 152.292,
                343.97, 152.292);
        ((GeneralPath) shape).curveTo(344.637, 152.292, 345.074, 152.39801,
                345.283, 152.615);
        ((GeneralPath) shape).curveTo(345.48898, 152.83, 345.594, 153.28401,
                345.594, 153.975);
        ((GeneralPath) shape).lineTo(345.594, 154.26001);
        ((GeneralPath) shape).lineTo(342.985, 154.26001);
        ((GeneralPath) shape).curveTo(342.985, 154.83202, 343.047, 155.197,
                343.16998, 155.354);
        ((GeneralPath) shape).curveTo(343.291, 155.51001, 343.577, 155.59,
                344.02798, 155.59);
        ((GeneralPath) shape).curveTo(344.455, 155.59, 344.731, 155.554,
                344.86, 155.478);
        ((GeneralPath) shape).curveTo(344.98798, 155.40399, 345.051, 155.243,
                345.051, 154.994);
        ((GeneralPath) shape).lineTo(345.051, 154.879);
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
        ((GeneralPath) shape).moveTo(346.481, 152.345);
        ((GeneralPath) shape).lineTo(347.012, 152.345);
        ((GeneralPath) shape).lineTo(346.958, 152.763);
        ((GeneralPath) shape).lineTo(346.97, 152.774);
        ((GeneralPath) shape).curveTo(347.178, 152.432, 347.525, 152.261,
                348.009, 152.261);
        ((GeneralPath) shape).curveTo(348.676, 152.261, 349.01, 152.605,
                349.01, 153.294);
        ((GeneralPath) shape).lineTo(349.006, 153.54501);
        ((GeneralPath) shape).lineTo(348.483, 153.54501);
        ((GeneralPath) shape).lineTo(348.495, 153.45401);
        ((GeneralPath) shape).curveTo(348.50198, 153.35901, 348.50598, 153.294,
                348.50598, 153.26001);
        ((GeneralPath) shape).curveTo(348.50598, 152.88701, 348.30597, 152.701,
                347.9, 152.701);
        ((GeneralPath) shape).curveTo(347.30798, 152.701, 347.013, 153.06601,
                347.013, 153.79901);
        ((GeneralPath) shape).lineTo(347.013, 155.97601);
        ((GeneralPath) shape).lineTo(346.482, 155.97601);
        ((GeneralPath) shape).lineTo(346.482, 152.345);
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
        ((GeneralPath) shape).moveTo(355.681, 150.791);
        ((GeneralPath) shape).lineTo(355.681, 155.977);
        ((GeneralPath) shape).lineTo(355.097, 155.977);
        ((GeneralPath) shape).lineTo(355.097, 153.572);
        ((GeneralPath) shape).lineTo(352.07, 153.572);
        ((GeneralPath) shape).lineTo(352.07, 155.977);
        ((GeneralPath) shape).lineTo(351.486, 155.977);
        ((GeneralPath) shape).lineTo(351.486, 150.791);
        ((GeneralPath) shape).lineTo(352.07, 150.791);
        ((GeneralPath) shape).lineTo(352.07, 153.078);
        ((GeneralPath) shape).lineTo(355.097, 153.078);
        ((GeneralPath) shape).lineTo(355.097, 150.791);
        ((GeneralPath) shape).lineTo(355.681, 150.791);
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
        ((GeneralPath) shape).moveTo(357.163, 152.345);
        ((GeneralPath) shape).lineTo(357.163, 155.977);
        ((GeneralPath) shape).lineTo(356.633, 155.977);
        ((GeneralPath) shape).lineTo(356.633, 152.345);
        ((GeneralPath) shape).lineTo(357.163, 152.345);
        ((GeneralPath) shape).moveTo(357.163, 150.791);
        ((GeneralPath) shape).lineTo(357.163, 151.388);
        ((GeneralPath) shape).lineTo(356.633, 151.388);
        ((GeneralPath) shape).lineTo(356.633, 150.791);
        ((GeneralPath) shape).lineTo(357.163, 150.791);
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
        ((GeneralPath) shape).moveTo(360.015, 152.345);
        ((GeneralPath) shape).lineTo(360.015, 152.786);
        ((GeneralPath) shape).lineTo(358.61902, 152.786);
        ((GeneralPath) shape).lineTo(358.61902, 155.008);
        ((GeneralPath) shape).curveTo(358.61902, 155.396, 358.79, 155.59,
                359.13602, 155.59);
        ((GeneralPath) shape).curveTo(359.47702, 155.59, 359.648, 155.41699,
                359.648, 155.069);
        ((GeneralPath) shape).lineTo(359.652, 154.89);
        ((GeneralPath) shape).lineTo(359.659, 154.689);
        ((GeneralPath) shape).lineTo(360.152, 154.689);
        ((GeneralPath) shape).lineTo(360.156, 154.959);
        ((GeneralPath) shape).curveTo(360.156, 155.673, 359.818, 156.03,
                359.139, 156.03);
        ((GeneralPath) shape).curveTo(358.44, 156.03, 358.08902, 155.734,
                358.08902, 155.137);
        ((GeneralPath) shape).lineTo(358.08902, 152.78499);
        ((GeneralPath) shape).lineTo(357.588, 152.78499);
        ((GeneralPath) shape).lineTo(357.588, 152.344);
        ((GeneralPath) shape).lineTo(358.08902, 152.344);
        ((GeneralPath) shape).lineTo(358.08902, 151.47);
        ((GeneralPath) shape).lineTo(358.61902, 151.47);
        ((GeneralPath) shape).lineTo(358.61902, 152.344);
        ((GeneralPath) shape).lineTo(360.015, 152.344);
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
        ((GeneralPath) shape).moveTo(261.526, 154.245);
        ((GeneralPath) shape).lineTo(262.106, 154.245);
        ((GeneralPath) shape).lineTo(262.106, 154.443);
        ((GeneralPath) shape).curveTo(262.106, 155.15, 261.986, 155.59,
                261.746, 155.767);
        ((GeneralPath) shape).curveTo(261.507, 155.942, 260.902, 156.031,
                259.933, 156.031);
        ((GeneralPath) shape).curveTo(259.06702, 156.031, 258.515, 155.877,
                258.28, 155.56601);
        ((GeneralPath) shape).curveTo(258.044, 155.25601, 257.927, 154.52701,
                257.927, 153.376);
        ((GeneralPath) shape).curveTo(257.927, 152.47801, 257.953, 151.912,
                258.007, 151.68001);
        ((GeneralPath) shape).curveTo(258.06, 151.449, 258.21198, 151.24101,
                258.46198, 151.059);
        ((GeneralPath) shape).curveTo(258.75397, 150.84601, 259.38696, 150.739,
                260.36197, 150.739);
        ((GeneralPath) shape).curveTo(261.02197, 150.739, 261.46597, 150.842,
                261.69498, 151.047);
        ((GeneralPath) shape).curveTo(261.92297, 151.252, 262.03796, 151.647,
                262.03796, 152.236);
        ((GeneralPath) shape).lineTo(262.04196, 152.37599);
        ((GeneralPath) shape).lineTo(261.46198, 152.37599);
        ((GeneralPath) shape).lineTo(261.45798, 152.21599);
        ((GeneralPath) shape).curveTo(261.45798, 151.79799, 261.39197,
                151.52998, 261.25497, 151.411);
        ((GeneralPath) shape).curveTo(261.11996, 151.293, 260.81497, 151.233,
                260.339, 151.233);
        ((GeneralPath) shape).curveTo(259.50497, 151.233, 258.99698, 151.301,
                258.81097, 151.441);
        ((GeneralPath) shape).curveTo(258.62698, 151.58, 258.53397, 151.965,
                258.53397, 152.59999);
        ((GeneralPath) shape).curveTo(258.53397, 154.055, 258.59497, 154.90999,
                258.71997, 155.161);
        ((GeneralPath) shape).curveTo(258.84396, 155.411, 259.26596, 155.537,
                259.99097, 155.537);
        ((GeneralPath) shape).curveTo(260.67697, 155.537, 261.10797, 155.48401,
                261.27795, 155.373);
        ((GeneralPath) shape).curveTo(261.44894, 155.265, 261.53397, 154.991,
                261.53397, 154.553);
        ((GeneralPath) shape).lineTo(261.526, 154.245);
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
        ((GeneralPath) shape).moveTo(264.24, 152.733);
        ((GeneralPath) shape).curveTo(263.77, 152.733, 263.47998, 152.809,
                263.366, 152.962);
        ((GeneralPath) shape).curveTo(263.254, 153.114, 263.197, 153.515,
                263.197, 154.16101);
        ((GeneralPath) shape).curveTo(263.197, 154.80702, 263.25198, 155.20601,
                263.366, 155.36002);
        ((GeneralPath) shape).curveTo(263.478, 155.51201, 263.77, 155.59001,
                264.24, 155.59001);
        ((GeneralPath) shape).curveTo(264.71198, 155.59001, 265.004, 155.514,
                265.11798, 155.36002);
        ((GeneralPath) shape).curveTo(265.22998, 155.20802, 265.28598,
                154.80702, 265.28598, 154.16101);
        ((GeneralPath) shape).curveTo(265.28598, 153.515, 265.231, 153.11601,
                265.11798, 152.962);
        ((GeneralPath) shape).curveTo(265.006, 152.811, 264.714, 152.733,
                264.24, 152.733);
        ((GeneralPath) shape).moveTo(264.24, 152.292);
        ((GeneralPath) shape).curveTo(264.909, 152.292, 265.344, 152.408,
                265.54398, 152.64201);
        ((GeneralPath) shape).curveTo(265.74298, 152.87302, 265.84396,
                153.38101, 265.84396, 154.16202);
        ((GeneralPath) shape).curveTo(265.84396, 154.94102, 265.74496,
                155.44801, 265.54398, 155.68202);
        ((GeneralPath) shape).curveTo(265.34497, 155.91402, 264.91098,
                156.03203, 264.24, 156.03203);
        ((GeneralPath) shape).curveTo(263.573, 156.03203, 263.13998, 155.91603,
                262.939, 155.68202);
        ((GeneralPath) shape).curveTo(262.74, 155.45102, 262.63998, 154.94302,
                262.63998, 154.16202);
        ((GeneralPath) shape).curveTo(262.63998, 153.38301, 262.73898,
                152.87602, 262.939, 152.64201);
        ((GeneralPath) shape).curveTo(263.139, 152.41, 263.573, 152.292,
                264.24, 152.292);
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
        ((GeneralPath) shape).moveTo(266.672, 152.345);
        ((GeneralPath) shape).lineTo(267.203, 152.345);
        ((GeneralPath) shape).lineTo(267.191, 152.892);
        ((GeneralPath) shape).lineTo(267.203, 152.903);
        ((GeneralPath) shape).curveTo(267.407, 152.497, 267.802, 152.292,
                268.38602, 152.292);
        ((GeneralPath) shape).curveTo(269.00003, 152.292, 269.372, 152.49501,
                269.50403, 152.903);
        ((GeneralPath) shape).lineTo(269.51904, 152.903);
        ((GeneralPath) shape).curveTo(269.74704, 152.497, 270.16205, 152.292,
                270.76703, 152.292);
        ((GeneralPath) shape).curveTo(271.62103, 152.292, 272.04904, 152.729,
                272.04904, 153.60301);
        ((GeneralPath) shape).lineTo(272.04904, 155.97801);
        ((GeneralPath) shape).lineTo(271.51804, 155.97801);
        ((GeneralPath) shape).lineTo(271.51804, 153.55402);
        ((GeneralPath) shape).curveTo(271.51804, 153.23502, 271.46103,
                153.01802, 271.34802, 152.90402);
        ((GeneralPath) shape).curveTo(271.234, 152.79002, 271.02002, 152.73402,
                270.70303, 152.73402);
        ((GeneralPath) shape).curveTo(270.28403, 152.73402, 270.00003,
                152.81203, 269.85004, 152.97102);
        ((GeneralPath) shape).curveTo(269.70203, 153.12903, 269.62604,
                153.43002, 269.62604, 153.87703);
        ((GeneralPath) shape).lineTo(269.62604, 155.97803);
        ((GeneralPath) shape).lineTo(269.09503, 155.97803);
        ((GeneralPath) shape).lineTo(269.09503, 153.60303);
        ((GeneralPath) shape).lineTo(269.08804, 153.43602);
        ((GeneralPath) shape).curveTo(269.08804, 152.96902, 268.82205,
                152.73302, 268.28806, 152.73302);
        ((GeneralPath) shape).curveTo(267.56607, 152.73302, 267.20404,
                153.12602, 267.20404, 153.91402);
        ((GeneralPath) shape).lineTo(267.20404, 155.97702);
        ((GeneralPath) shape).lineTo(266.67303, 155.97702);
        ((GeneralPath) shape).lineTo(266.67303, 152.345);
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
        ((GeneralPath) shape).moveTo(273.087, 152.345);
        ((GeneralPath) shape).lineTo(273.618, 152.345);
        ((GeneralPath) shape).lineTo(273.60703, 152.892);
        ((GeneralPath) shape).lineTo(273.618, 152.903);
        ((GeneralPath) shape).curveTo(273.823, 152.497, 274.217, 152.292,
                274.80103, 152.292);
        ((GeneralPath) shape).curveTo(275.41504, 152.292, 275.78702, 152.49501,
                275.92, 152.903);
        ((GeneralPath) shape).lineTo(275.93503, 152.903);
        ((GeneralPath) shape).curveTo(276.16302, 152.497, 276.57803, 152.292,
                277.183, 152.292);
        ((GeneralPath) shape).curveTo(278.036, 152.292, 278.46402, 152.729,
                278.46402, 153.60301);
        ((GeneralPath) shape).lineTo(278.46402, 155.97801);
        ((GeneralPath) shape).lineTo(277.933, 155.97801);
        ((GeneralPath) shape).lineTo(277.933, 153.55402);
        ((GeneralPath) shape).curveTo(277.933, 153.23502, 277.876, 153.01802,
                277.763, 152.90402);
        ((GeneralPath) shape).curveTo(277.649, 152.79002, 277.435, 152.73402,
                277.118, 152.73402);
        ((GeneralPath) shape).curveTo(276.699, 152.73402, 276.414, 152.81203,
                276.264, 152.97102);
        ((GeneralPath) shape).curveTo(276.117, 153.12903, 276.04, 153.43002,
                276.04, 153.87703);
        ((GeneralPath) shape).lineTo(276.04, 155.97803);
        ((GeneralPath) shape).lineTo(275.509, 155.97803);
        ((GeneralPath) shape).lineTo(275.509, 153.60303);
        ((GeneralPath) shape).lineTo(275.502, 153.43602);
        ((GeneralPath) shape).curveTo(275.502, 152.96902, 275.23602, 152.73302,
                274.70203, 152.73302);
        ((GeneralPath) shape).curveTo(273.97903, 152.73302, 273.618, 153.12602,
                273.618, 153.91402);
        ((GeneralPath) shape).lineTo(273.618, 155.97702);
        ((GeneralPath) shape).lineTo(273.087, 155.97702);
        ((GeneralPath) shape).lineTo(273.087, 152.345);
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
        ((GeneralPath) shape).moveTo(280.908, 154.18);
        ((GeneralPath) shape).curveTo(280.503, 154.18, 280.237, 154.222,
                280.11398, 154.30899);
        ((GeneralPath) shape).curveTo(279.99298, 154.394, 279.93, 154.579,
                279.93, 154.86398);
        ((GeneralPath) shape).curveTo(279.93, 155.15599, 279.991, 155.35199,
                280.112, 155.44698);
        ((GeneralPath) shape).curveTo(280.233, 155.54198, 280.48, 155.58998,
                280.851, 155.58998);
        ((GeneralPath) shape).curveTo(281.59802, 155.58998, 281.974, 155.36198,
                281.974, 154.90598);
        ((GeneralPath) shape).curveTo(281.974, 154.62097, 281.902, 154.42697,
                281.756, 154.32797);
        ((GeneralPath) shape).curveTo(281.611, 154.229, 281.329, 154.18,
                280.908, 154.18);
        ((GeneralPath) shape).moveTo(280.055, 153.363);
        ((GeneralPath) shape).lineTo(279.52798, 153.363);
        ((GeneralPath) shape).curveTo(279.52798, 152.94101, 279.623, 152.656,
                279.814, 152.51001);
        ((GeneralPath) shape).curveTo(280.003, 152.365, 280.37698, 152.29102,
                280.935, 152.29102);
        ((GeneralPath) shape).curveTo(281.538, 152.29102, 281.947, 152.38002,
                282.16, 152.55902);
        ((GeneralPath) shape).curveTo(282.372, 152.73802, 282.478, 153.07802,
                282.478, 153.58002);
        ((GeneralPath) shape).lineTo(282.478, 155.97702);
        ((GeneralPath) shape).lineTo(281.947, 155.97702);
        ((GeneralPath) shape).lineTo(281.98898, 155.58601);
        ((GeneralPath) shape).lineTo(281.978, 155.58202);
        ((GeneralPath) shape).curveTo(281.777, 155.88002, 281.366, 156.03001,
                280.746, 156.03001);
        ((GeneralPath) shape).curveTo(279.832, 156.03001, 279.37302, 155.66202,
                279.37302, 154.92502);
        ((GeneralPath) shape).curveTo(279.37302, 154.48802, 279.476, 154.18402,
                279.68002, 154.01701);
        ((GeneralPath) shape).curveTo(279.885, 153.85, 280.25504, 153.76701,
                280.79102, 153.76701);
        ((GeneralPath) shape).curveTo(281.428, 153.76701, 281.81003, 153.89201,
                281.937, 154.14302);
        ((GeneralPath) shape).lineTo(281.948, 154.14001);
        ((GeneralPath) shape).lineTo(281.948, 153.69902);
        ((GeneralPath) shape).curveTo(281.948, 153.28502, 281.891, 153.01501,
                281.77798, 152.89201);
        ((GeneralPath) shape).curveTo(281.66397, 152.77002, 281.416, 152.70702,
                281.03098, 152.70702);
        ((GeneralPath) shape).curveTo(280.37897, 152.70702, 280.05197,
                152.88902, 280.05197, 153.25801);
        ((GeneralPath) shape).curveTo(280.051, 153.274, 280.051, 153.31,
                280.055, 153.363);
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
        ((GeneralPath) shape).moveTo(283.462, 152.345);
        ((GeneralPath) shape).lineTo(283.974, 152.345);
        ((GeneralPath) shape).lineTo(283.95898, 152.839);
        ((GeneralPath) shape).lineTo(283.974, 152.851);
        ((GeneralPath) shape).curveTo(284.135, 152.479, 284.537, 152.29199,
                285.18, 152.29199);
        ((GeneralPath) shape).curveTo(285.698, 152.29199, 286.048, 152.383,
                286.232, 152.56499);
        ((GeneralPath) shape).curveTo(286.414, 152.74799, 286.507, 153.09698,
                286.507, 153.61398);
        ((GeneralPath) shape).lineTo(286.507, 155.97699);
        ((GeneralPath) shape).lineTo(285.97598, 155.97699);
        ((GeneralPath) shape).lineTo(285.97598, 153.523);
        ((GeneralPath) shape).curveTo(285.97598, 153.211, 285.917, 153.002,
                285.79797, 152.894);
        ((GeneralPath) shape).curveTo(285.67996, 152.788, 285.451, 152.733,
                285.11096, 152.733);
        ((GeneralPath) shape).curveTo(284.36597, 152.733, 283.99295, 153.086,
                283.99295, 153.793);
        ((GeneralPath) shape).lineTo(283.99295, 155.978);
        ((GeneralPath) shape).lineTo(283.46194, 155.978);
        ((GeneralPath) shape).lineTo(283.46194, 152.345);
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
        ((GeneralPath) shape).moveTo(288.903, 152.733);
        ((GeneralPath) shape).curveTo(288.51202, 152.733, 288.251, 152.822,
                288.122, 153.004);
        ((GeneralPath) shape).curveTo(287.993, 153.18399, 287.92902, 153.551,
                287.92902, 154.104);
        ((GeneralPath) shape).curveTo(287.92902, 154.712, 287.992, 155.11101,
                288.12003, 155.30301);
        ((GeneralPath) shape).curveTo(288.24704, 155.49301, 288.51303,
                155.59001, 288.91403, 155.59001);
        ((GeneralPath) shape).curveTo(289.35004, 155.59001, 289.63504,
                155.49501, 289.76703, 155.30301);
        ((GeneralPath) shape).curveTo(289.89804, 155.113, 289.96503, 154.697,
                289.96503, 154.05501);
        ((GeneralPath) shape).curveTo(289.96503, 153.53601, 289.89304,
                153.18701, 289.74704, 153.005);
        ((GeneralPath) shape).curveTo(289.603, 152.824, 289.32, 152.733,
                288.903, 152.733);
        ((GeneralPath) shape).moveTo(290.499, 150.791);
        ((GeneralPath) shape).lineTo(290.499, 155.977);
        ((GeneralPath) shape).lineTo(289.968, 155.977);
        ((GeneralPath) shape).lineTo(289.995, 155.50601);
        ((GeneralPath) shape).lineTo(289.97998, 155.503);
        ((GeneralPath) shape).curveTo(289.813, 155.85501, 289.426, 156.031,
                288.81497, 156.031);
        ((GeneralPath) shape).curveTo(288.25897, 156.031, 287.87997, 155.89601,
                287.67596, 155.625);
        ((GeneralPath) shape).curveTo(287.47296, 155.355, 287.36996, 154.85,
                287.36996, 154.109);
        ((GeneralPath) shape).curveTo(287.36996, 153.429, 287.47296, 152.956,
                287.67697, 152.68999);
        ((GeneralPath) shape).curveTo(287.88196, 152.42598, 288.248, 152.29298,
                288.77698, 152.29298);
        ((GeneralPath) shape).curveTo(289.43698, 152.29298, 289.83, 152.46399,
                289.95596, 152.80598);
        ((GeneralPath) shape).lineTo(289.96796, 152.79898);
        ((GeneralPath) shape).lineTo(289.96796, 150.79298);
        ((GeneralPath) shape).lineTo(290.499, 150.79298);
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
        ((GeneralPath) shape).moveTo(293.952, 153.846);
        ((GeneralPath) shape).lineTo(293.948, 153.67499);
        ((GeneralPath) shape).curveTo(293.948, 153.28398, 293.884, 153.02798,
                293.755, 152.90898);
        ((GeneralPath) shape).curveTo(293.626, 152.79099, 293.349, 152.73299,
                292.921, 152.73299);
        ((GeneralPath) shape).curveTo(292.49298, 152.73299, 292.214, 152.80098,
                292.085, 152.93999);
        ((GeneralPath) shape).curveTo(291.95798, 153.07698, 291.89398,
                153.37898, 291.89398, 153.846);
        ((GeneralPath) shape).lineTo(293.952, 153.846);
        ((GeneralPath) shape).moveTo(293.952, 154.879);
        ((GeneralPath) shape).lineTo(294.494, 154.879);
        ((GeneralPath) shape).lineTo(294.498, 155.012);
        ((GeneralPath) shape).curveTo(294.498, 155.388, 294.38397, 155.652,
                294.155, 155.804);
        ((GeneralPath) shape).curveTo(293.927, 155.954, 293.526, 156.03,
                292.951, 156.03);
        ((GeneralPath) shape).curveTo(292.284, 156.03, 291.84598, 155.908,
                291.638, 155.664);
        ((GeneralPath) shape).curveTo(291.429, 155.421, 291.325, 154.906,
                291.325, 154.123);
        ((GeneralPath) shape).curveTo(291.325, 153.399, 291.42902, 152.913,
                291.639, 152.664);
        ((GeneralPath) shape).curveTo(291.84802, 152.417, 292.259, 152.292,
                292.871, 152.292);
        ((GeneralPath) shape).curveTo(293.538, 152.292, 293.975, 152.39801,
                294.184, 152.615);
        ((GeneralPath) shape).curveTo(294.391, 152.83, 294.495, 153.28401,
                294.495, 153.975);
        ((GeneralPath) shape).lineTo(294.495, 154.26001);
        ((GeneralPath) shape).lineTo(291.886, 154.26001);
        ((GeneralPath) shape).curveTo(291.886, 154.83202, 291.947, 155.197,
                292.06998, 155.354);
        ((GeneralPath) shape).curveTo(292.19098, 155.51001, 292.477, 155.59,
                292.929, 155.59);
        ((GeneralPath) shape).curveTo(293.35498, 155.59, 293.633, 155.554,
                293.762, 155.478);
        ((GeneralPath) shape).curveTo(293.889, 155.40399, 293.953, 155.243,
                293.953, 154.994);
        ((GeneralPath) shape).lineTo(293.953, 154.879);
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
        ((GeneralPath) shape).moveTo(295.382, 152.345);
        ((GeneralPath) shape).lineTo(295.913, 152.345);
        ((GeneralPath) shape).lineTo(295.86, 152.763);
        ((GeneralPath) shape).lineTo(295.87097, 152.774);
        ((GeneralPath) shape).curveTo(296.07898, 152.432, 296.42697, 152.261,
                296.90997, 152.261);
        ((GeneralPath) shape).curveTo(297.57697, 152.261, 297.91098, 152.605,
                297.91098, 153.294);
        ((GeneralPath) shape).lineTo(297.908, 153.54501);
        ((GeneralPath) shape).lineTo(297.384, 153.54501);
        ((GeneralPath) shape).lineTo(297.396, 153.45401);
        ((GeneralPath) shape).curveTo(297.40298, 153.35901, 297.408, 153.294,
                297.408, 153.26001);
        ((GeneralPath) shape).curveTo(297.408, 152.88701, 297.207, 152.701,
                296.801, 152.701);
        ((GeneralPath) shape).curveTo(296.20898, 152.701, 295.914, 153.06601,
                295.914, 153.79901);
        ((GeneralPath) shape).lineTo(295.914, 155.97601);
        ((GeneralPath) shape).lineTo(295.383, 155.97601);
        ((GeneralPath) shape).lineTo(295.383, 152.345);
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
        ((GeneralPath) shape).moveTo(304.581, 150.791);
        ((GeneralPath) shape).lineTo(304.581, 155.977);
        ((GeneralPath) shape).lineTo(303.998, 155.977);
        ((GeneralPath) shape).lineTo(303.998, 153.572);
        ((GeneralPath) shape).lineTo(300.971, 153.572);
        ((GeneralPath) shape).lineTo(300.971, 155.977);
        ((GeneralPath) shape).lineTo(300.387, 155.977);
        ((GeneralPath) shape).lineTo(300.387, 150.791);
        ((GeneralPath) shape).lineTo(300.971, 150.791);
        ((GeneralPath) shape).lineTo(300.971, 153.078);
        ((GeneralPath) shape).lineTo(303.998, 153.078);
        ((GeneralPath) shape).lineTo(303.998, 150.791);
        ((GeneralPath) shape).lineTo(304.581, 150.791);
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
        ((GeneralPath) shape).moveTo(306.063, 152.345);
        ((GeneralPath) shape).lineTo(306.063, 155.977);
        ((GeneralPath) shape).lineTo(305.53198, 155.977);
        ((GeneralPath) shape).lineTo(305.53198, 152.345);
        ((GeneralPath) shape).lineTo(306.063, 152.345);
        ((GeneralPath) shape).moveTo(306.063, 150.791);
        ((GeneralPath) shape).lineTo(306.063, 151.388);
        ((GeneralPath) shape).lineTo(305.53198, 151.388);
        ((GeneralPath) shape).lineTo(305.53198, 150.791);
        ((GeneralPath) shape).lineTo(306.063, 150.791);
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
        ((GeneralPath) shape).moveTo(308.915, 152.345);
        ((GeneralPath) shape).lineTo(308.915, 152.786);
        ((GeneralPath) shape).lineTo(307.519, 152.786);
        ((GeneralPath) shape).lineTo(307.519, 155.008);
        ((GeneralPath) shape).curveTo(307.519, 155.396, 307.69, 155.59,
                308.035, 155.59);
        ((GeneralPath) shape).curveTo(308.377, 155.59, 308.548, 155.41699,
                308.548, 155.069);
        ((GeneralPath) shape).lineTo(308.551, 154.89);
        ((GeneralPath) shape).lineTo(308.559, 154.689);
        ((GeneralPath) shape).lineTo(309.052, 154.689);
        ((GeneralPath) shape).lineTo(309.056, 154.959);
        ((GeneralPath) shape).curveTo(309.056, 155.673, 308.718, 156.03,
                308.039, 156.03);
        ((GeneralPath) shape).curveTo(307.34, 156.03, 306.989, 155.734,
                306.989, 155.137);
        ((GeneralPath) shape).lineTo(306.989, 152.78499);
        ((GeneralPath) shape).lineTo(306.488, 152.78499);
        ((GeneralPath) shape).lineTo(306.488, 152.344);
        ((GeneralPath) shape).lineTo(306.989, 152.344);
        ((GeneralPath) shape).lineTo(306.989, 151.47);
        ((GeneralPath) shape).lineTo(307.519, 151.47);
        ((GeneralPath) shape).lineTo(307.519, 152.344);
        ((GeneralPath) shape).lineTo(308.915, 152.344);
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
        ((GeneralPath) shape).moveTo(333.949, 158.659);
        ((GeneralPath) shape).lineTo(333.949, 162.549);
        ((GeneralPath) shape).lineTo(333.51102, 162.549);
        ((GeneralPath) shape).lineTo(333.51102, 159.204);
        ((GeneralPath) shape).lineTo(333.514, 159.08699);
        ((GeneralPath) shape).lineTo(333.517, 158.96999);
        ((GeneralPath) shape).lineTo(333.505, 158.96999);
        ((GeneralPath) shape).lineTo(333.471, 159.06099);
        ((GeneralPath) shape).curveTo(333.454, 159.10599, 333.443, 159.135,
                333.437, 159.14899);
        ((GeneralPath) shape).lineTo(333.364, 159.331);
        ((GeneralPath) shape).lineTo(332.04102, 162.54799);
        ((GeneralPath) shape).lineTo(331.60303, 162.54799);
        ((GeneralPath) shape).lineTo(330.278, 159.368);
        ((GeneralPath) shape).lineTo(330.20102, 159.188);
        ((GeneralPath) shape).lineTo(330.16702, 159.097);
        ((GeneralPath) shape).curveTo(330.15903, 159.077, 330.14703, 159.047,
                330.13303, 159.009);
        ((GeneralPath) shape).lineTo(330.12204, 159.009);
        ((GeneralPath) shape).lineTo(330.12503, 159.114);
        ((GeneralPath) shape).lineTo(330.12802, 159.222);
        ((GeneralPath) shape).lineTo(330.12802, 162.547);
        ((GeneralPath) shape).lineTo(329.69003, 162.547);
        ((GeneralPath) shape).lineTo(329.69003, 158.657);
        ((GeneralPath) shape).lineTo(330.45004, 158.657);
        ((GeneralPath) shape).lineTo(331.48505, 161.173);
        ((GeneralPath) shape).lineTo(331.65005, 161.58101);
        ((GeneralPath) shape).lineTo(331.73206, 161.783);
        ((GeneralPath) shape).lineTo(331.81204, 161.985);
        ((GeneralPath) shape).lineTo(331.82303, 161.985);
        ((GeneralPath) shape).lineTo(331.903, 161.783);
        ((GeneralPath) shape).curveTo(331.94302, 161.686, 331.96802, 161.619,
                331.98203, 161.58101);
        ((GeneralPath) shape).lineTo(332.15002, 161.17601);
        ((GeneralPath) shape).lineTo(333.18002, 158.65701);
        ((GeneralPath) shape).lineTo(333.949, 158.65701);
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
        ((GeneralPath) shape).moveTo(335.79, 160.115);
        ((GeneralPath) shape).curveTo(335.437, 160.115, 335.22, 160.17201,
                335.135, 160.287);
        ((GeneralPath) shape).curveTo(335.051, 160.401, 335.008, 160.702,
                335.008, 161.186);
        ((GeneralPath) shape).curveTo(335.008, 161.67001, 335.04898, 161.97,
                335.135, 162.085);
        ((GeneralPath) shape).curveTo(335.21802, 162.199, 335.43802, 162.257,
                335.79, 162.257);
        ((GeneralPath) shape).curveTo(336.144, 162.257, 336.363, 162.2,
                336.448, 162.085);
        ((GeneralPath) shape).curveTo(336.532, 161.97101, 336.575, 161.67001,
                336.575, 161.186);
        ((GeneralPath) shape).curveTo(336.575, 160.702, 336.53302, 160.40201,
                336.448, 160.287);
        ((GeneralPath) shape).curveTo(336.364, 160.173, 336.146, 160.115,
                335.79, 160.115);
        ((GeneralPath) shape).moveTo(335.79, 159.784);
        ((GeneralPath) shape).curveTo(336.29202, 159.784, 336.617, 159.871,
                336.769, 160.04599);
        ((GeneralPath) shape).curveTo(336.91702, 160.21999, 336.99402,
                160.59999, 336.99402, 161.18599);
        ((GeneralPath) shape).curveTo(336.99402, 161.76999, 336.92, 162.15099,
                336.769, 162.32599);
        ((GeneralPath) shape).curveTo(336.62003, 162.499, 336.293, 162.58798,
                335.79, 162.58798);
        ((GeneralPath) shape).curveTo(335.289, 162.58798, 334.965, 162.50198,
                334.814, 162.32599);
        ((GeneralPath) shape).curveTo(334.665, 162.152, 334.589, 161.77199,
                334.589, 161.18599);
        ((GeneralPath) shape).curveTo(334.589, 160.60199, 334.663, 160.22198,
                334.814, 160.04599);
        ((GeneralPath) shape).curveTo(334.964, 159.873, 335.289, 159.784,
                335.79, 159.784);
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
        ((GeneralPath) shape).moveTo(338.688, 160.115);
        ((GeneralPath) shape).curveTo(338.395, 160.115, 338.19897, 160.182,
                338.102, 160.319);
        ((GeneralPath) shape).curveTo(338.005, 160.454, 337.957, 160.73,
                337.957, 161.144);
        ((GeneralPath) shape).curveTo(337.957, 161.59999, 338.004, 161.899,
                338.101, 162.043);
        ((GeneralPath) shape).curveTo(338.196, 162.186, 338.39502, 162.258,
                338.69702, 162.258);
        ((GeneralPath) shape).curveTo(339.02402, 162.258, 339.23703, 162.18599,
                339.33704, 162.043);
        ((GeneralPath) shape).curveTo(339.43604, 161.9, 339.48505, 161.588,
                339.48505, 161.107);
        ((GeneralPath) shape).curveTo(339.48505, 160.71799, 339.43005, 160.456,
                339.32104, 160.319);
        ((GeneralPath) shape).curveTo(339.213, 160.183, 339.002, 160.115,
                338.688, 160.115);
        ((GeneralPath) shape).moveTo(339.886, 158.659);
        ((GeneralPath) shape).lineTo(339.886, 162.549);
        ((GeneralPath) shape).lineTo(339.48798, 162.549);
        ((GeneralPath) shape).lineTo(339.50897, 162.19499);
        ((GeneralPath) shape).lineTo(339.49698, 162.193);
        ((GeneralPath) shape).curveTo(339.37198, 162.456, 339.08197, 162.58899,
                338.624, 162.58899);
        ((GeneralPath) shape).curveTo(338.207, 162.58899, 337.923, 162.48799,
                337.77, 162.284);
        ((GeneralPath) shape).curveTo(337.61798, 162.082, 337.541, 161.702,
                337.541, 161.147);
        ((GeneralPath) shape).curveTo(337.541, 160.63701, 337.61798, 160.282,
                337.771, 160.08301);
        ((GeneralPath) shape).curveTo(337.925, 159.88501, 338.19998, 159.785,
                338.596, 159.785);
        ((GeneralPath) shape).curveTo(339.091, 159.785, 339.385, 159.91301,
                339.48102, 160.17);
        ((GeneralPath) shape).lineTo(339.489, 160.165);
        ((GeneralPath) shape).lineTo(339.489, 158.65999);
        ((GeneralPath) shape).lineTo(339.886, 158.65999);
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
        ((GeneralPath) shape).moveTo(340.982, 159.824);
        ((GeneralPath) shape).lineTo(340.982, 162.548);
        ((GeneralPath) shape).lineTo(340.58398, 162.548);
        ((GeneralPath) shape).lineTo(340.58398, 159.824);
        ((GeneralPath) shape).lineTo(340.982, 159.824);
        ((GeneralPath) shape).moveTo(340.982, 158.659);
        ((GeneralPath) shape).lineTo(340.982, 159.106);
        ((GeneralPath) shape).lineTo(340.58398, 159.106);
        ((GeneralPath) shape).lineTo(340.58398, 158.659);
        ((GeneralPath) shape).lineTo(340.982, 158.659);
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
        ((GeneralPath) shape).moveTo(341.784, 162.548);
        ((GeneralPath) shape).lineTo(341.784, 160.154);
        ((GeneralPath) shape).lineTo(341.369, 160.154);
        ((GeneralPath) shape).lineTo(341.369, 159.82301);
        ((GeneralPath) shape).lineTo(341.784, 159.82301);
        ((GeneralPath) shape).lineTo(341.784, 159.41801);
        ((GeneralPath) shape).curveTo(341.784, 158.88501, 342.057, 158.61702,
                342.605, 158.61702);
        ((GeneralPath) shape).curveTo(342.687, 158.61702, 342.782, 158.62302,
                342.893, 158.63702);
        ((GeneralPath) shape).lineTo(342.893, 158.96802);
        ((GeneralPath) shape).curveTo(342.765, 158.95502, 342.67102, 158.94801,
                342.612, 158.94801);
        ((GeneralPath) shape).curveTo(342.326, 158.94801, 342.182, 159.09201,
                342.182, 159.38101);
        ((GeneralPath) shape).lineTo(342.182, 159.82301);
        ((GeneralPath) shape).lineTo(342.893, 159.82301);
        ((GeneralPath) shape).lineTo(342.893, 160.154);
        ((GeneralPath) shape).lineTo(342.182, 160.154);
        ((GeneralPath) shape).lineTo(342.182, 162.548);
        ((GeneralPath) shape).lineTo(341.784, 162.548);
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
        ((GeneralPath) shape).moveTo(343.621, 159.824);
        ((GeneralPath) shape).lineTo(343.621, 162.548);
        ((GeneralPath) shape).lineTo(343.223, 162.548);
        ((GeneralPath) shape).lineTo(343.223, 159.824);
        ((GeneralPath) shape).lineTo(343.621, 159.824);
        ((GeneralPath) shape).moveTo(343.621, 158.659);
        ((GeneralPath) shape).lineTo(343.621, 159.106);
        ((GeneralPath) shape).lineTo(343.223, 159.106);
        ((GeneralPath) shape).lineTo(343.223, 158.659);
        ((GeneralPath) shape).lineTo(343.621, 158.659);
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
        ((GeneralPath) shape).moveTo(346.209, 160.95);
        ((GeneralPath) shape).lineTo(346.20602, 160.82199);
        ((GeneralPath) shape).curveTo(346.20602, 160.52899, 346.157, 160.33699,
                346.06003, 160.24799);
        ((GeneralPath) shape).curveTo(345.96304, 160.15999, 345.75604,
                160.11598, 345.43503, 160.11598);
        ((GeneralPath) shape).curveTo(345.11304, 160.11598, 344.90503,
                160.16698, 344.808, 160.27098);
        ((GeneralPath) shape).curveTo(344.712, 160.37398, 344.664, 160.59998,
                344.664, 160.95097);
        ((GeneralPath) shape).lineTo(346.209, 160.95097);
        ((GeneralPath) shape).moveTo(346.209, 161.725);
        ((GeneralPath) shape).lineTo(346.61502, 161.725);
        ((GeneralPath) shape).lineTo(346.618, 161.82501);
        ((GeneralPath) shape).curveTo(346.618, 162.10701, 346.53302, 162.30501,
                346.36102, 162.419);
        ((GeneralPath) shape).curveTo(346.19003, 162.53201, 345.88803,
                162.58801, 345.458, 162.58801);
        ((GeneralPath) shape).curveTo(344.957, 162.58801, 344.629, 162.49701,
                344.47302, 162.31302);
        ((GeneralPath) shape).curveTo(344.31702, 162.13101, 344.239, 161.74503,
                344.239, 161.15802);
        ((GeneralPath) shape).curveTo(344.239, 160.61502, 344.31702, 160.25002,
                344.474, 160.06403);
        ((GeneralPath) shape).curveTo(344.631, 159.87903, 344.94, 159.78502,
                345.399, 159.78502);
        ((GeneralPath) shape).curveTo(345.899, 159.78502, 346.22598, 159.86502,
                346.383, 160.02702);
        ((GeneralPath) shape).curveTo(346.537, 160.18802, 346.615, 160.52902,
                346.615, 161.04802);
        ((GeneralPath) shape).lineTo(346.615, 161.26102);
        ((GeneralPath) shape).lineTo(344.658, 161.26102);
        ((GeneralPath) shape).curveTo(344.658, 161.69002, 344.70398, 161.96301,
                344.797, 162.08202);
        ((GeneralPath) shape).curveTo(344.888, 162.19902, 345.103, 162.25902,
                345.441, 162.25902);
        ((GeneralPath) shape).curveTo(345.76102, 162.25902, 345.96902,
                162.23203, 346.066, 162.17502);
        ((GeneralPath) shape).curveTo(346.161, 162.12003, 346.21002, 161.99902,
                346.21002, 161.81201);
        ((GeneralPath) shape).lineTo(346.21002, 161.725);
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
        ((GeneralPath) shape).moveTo(347.281, 159.824);
        ((GeneralPath) shape).lineTo(347.678, 159.824);
        ((GeneralPath) shape).lineTo(347.639, 160.13701);
        ((GeneralPath) shape).lineTo(347.647, 160.14601);
        ((GeneralPath) shape).curveTo(347.803, 159.889, 348.064, 159.76102,
                348.426, 159.76102);
        ((GeneralPath) shape).curveTo(348.927, 159.76102, 349.177, 160.01901,
                349.177, 160.53601);
        ((GeneralPath) shape).lineTo(349.174, 160.72401);
        ((GeneralPath) shape).lineTo(348.781, 160.72401);
        ((GeneralPath) shape).lineTo(348.79, 160.65602);
        ((GeneralPath) shape).curveTo(348.79602, 160.58502, 348.799, 160.53603,
                348.799, 160.51003);
        ((GeneralPath) shape).curveTo(348.799, 160.23102, 348.648, 160.09102,
                348.34402, 160.09102);
        ((GeneralPath) shape).curveTo(347.90103, 160.09102, 347.67804,
                160.36502, 347.67804, 160.91502);
        ((GeneralPath) shape).lineTo(347.67804, 162.54802);
        ((GeneralPath) shape).lineTo(347.28104, 162.54802);
        ((GeneralPath) shape).lineTo(347.28104, 159.824);
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
        ((GeneralPath) shape).moveTo(352.457, 159.824);
        ((GeneralPath) shape).lineTo(352.457, 160.155);
        ((GeneralPath) shape).lineTo(351.411, 160.155);
        ((GeneralPath) shape).lineTo(351.411, 161.822);
        ((GeneralPath) shape).curveTo(351.411, 162.113, 351.539, 162.259,
                351.798, 162.259);
        ((GeneralPath) shape).curveTo(352.05402, 162.259, 352.182, 162.129,
                352.182, 161.868);
        ((GeneralPath) shape).lineTo(352.185, 161.734);
        ((GeneralPath) shape).lineTo(352.191, 161.583);
        ((GeneralPath) shape).lineTo(352.56, 161.583);
        ((GeneralPath) shape).lineTo(352.563, 161.78499);
        ((GeneralPath) shape).curveTo(352.563, 162.32098, 352.31, 162.58798,
                351.8, 162.58798);
        ((GeneralPath) shape).curveTo(351.276, 162.58798, 351.013, 162.36598,
                351.013, 161.91898);
        ((GeneralPath) shape).lineTo(351.013, 160.15497);
        ((GeneralPath) shape).lineTo(350.637, 160.15497);
        ((GeneralPath) shape).lineTo(350.637, 159.82397);
        ((GeneralPath) shape).lineTo(351.013, 159.82397);
        ((GeneralPath) shape).lineTo(351.013, 159.16898);
        ((GeneralPath) shape).lineTo(351.411, 159.16898);
        ((GeneralPath) shape).lineTo(351.411, 159.82397);
        ((GeneralPath) shape).lineTo(352.457, 159.82397);
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
        ((GeneralPath) shape).moveTo(354.038, 160.115);
        ((GeneralPath) shape).curveTo(353.685, 160.115, 353.468, 160.17201,
                353.382, 160.287);
        ((GeneralPath) shape).curveTo(353.29797, 160.401, 353.25598, 160.702,
                353.25598, 161.186);
        ((GeneralPath) shape).curveTo(353.25598, 161.67001, 353.29697, 161.97,
                353.382, 162.085);
        ((GeneralPath) shape).curveTo(353.466, 162.199, 353.68597, 162.257,
                354.038, 162.257);
        ((GeneralPath) shape).curveTo(354.392, 162.257, 354.61, 162.2,
                354.69598, 162.085);
        ((GeneralPath) shape).curveTo(354.78, 161.97101, 354.822, 161.67001,
                354.822, 161.186);
        ((GeneralPath) shape).curveTo(354.822, 160.702, 354.781, 160.40201,
                354.69598, 160.287);
        ((GeneralPath) shape).curveTo(354.612, 160.173, 354.394, 160.115,
                354.038, 160.115);
        ((GeneralPath) shape).moveTo(354.038, 159.784);
        ((GeneralPath) shape).curveTo(354.54, 159.784, 354.865, 159.871,
                355.016, 160.04599);
        ((GeneralPath) shape).curveTo(355.16498, 160.21999, 355.242, 160.59999,
                355.242, 161.18599);
        ((GeneralPath) shape).curveTo(355.242, 161.76999, 355.168, 162.15099,
                355.016, 162.32599);
        ((GeneralPath) shape).curveTo(354.86798, 162.499, 354.541, 162.58798,
                354.038, 162.58798);
        ((GeneralPath) shape).curveTo(353.537, 162.58798, 353.21298, 162.50198,
                353.06198, 162.32599);
        ((GeneralPath) shape).curveTo(352.913, 162.152, 352.83698, 161.77199,
                352.83698, 161.18599);
        ((GeneralPath) shape).curveTo(352.83698, 160.60199, 352.90997,
                160.22198, 353.06198, 160.04599);
        ((GeneralPath) shape).curveTo(353.212, 159.873, 353.537, 159.784,
                354.038, 159.784);
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
        ((GeneralPath) shape).moveTo(357.775, 162.178);
        ((GeneralPath) shape).lineTo(359.041, 162.178);
        ((GeneralPath) shape).curveTo(359.50497, 162.178, 359.805, 162.086,
                359.93997, 161.89899);
        ((GeneralPath) shape).curveTo(360.07297, 161.71399, 360.14096,
                161.29799, 360.14096, 160.65399);
        ((GeneralPath) shape).curveTo(360.14096, 159.94699, 360.08295,
                159.49998, 359.96597, 159.312);
        ((GeneralPath) shape).curveTo(359.84998, 159.124, 359.56998, 159.03,
                359.12796, 159.03);
        ((GeneralPath) shape).lineTo(357.77396, 159.03);
        ((GeneralPath) shape).lineTo(357.77396, 162.178);
        ((GeneralPath) shape).moveTo(357.338, 162.548);
        ((GeneralPath) shape).lineTo(357.338, 158.658);
        ((GeneralPath) shape).lineTo(359.135, 158.658);
        ((GeneralPath) shape).curveTo(359.69, 158.658, 360.06702, 158.781,
                360.265, 159.026);
        ((GeneralPath) shape).curveTo(360.46002, 159.271, 360.56003, 159.74,
                360.56003, 160.434);
        ((GeneralPath) shape).curveTo(360.56003, 161.27701, 360.47302,
                161.84001, 360.29703, 162.123);
        ((GeneralPath) shape).curveTo(360.12204, 162.405, 359.77304, 162.548,
                359.24603, 162.548);
        ((GeneralPath) shape).lineTo(357.338, 162.548);
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
        ((GeneralPath) shape).moveTo(361.161, 159.824);
        ((GeneralPath) shape).lineTo(361.55902, 159.824);
        ((GeneralPath) shape).lineTo(361.519, 160.13701);
        ((GeneralPath) shape).lineTo(361.528, 160.14601);
        ((GeneralPath) shape).curveTo(361.68402, 159.889, 361.944, 159.76102,
                362.30603, 159.76102);
        ((GeneralPath) shape).curveTo(362.80704, 159.76102, 363.05704,
                160.01901, 363.05704, 160.53601);
        ((GeneralPath) shape).lineTo(363.05502, 160.72401);
        ((GeneralPath) shape).lineTo(362.661, 160.72401);
        ((GeneralPath) shape).lineTo(362.67, 160.65602);
        ((GeneralPath) shape).curveTo(362.67603, 160.58502, 362.67902,
                160.53603, 362.67902, 160.51003);
        ((GeneralPath) shape).curveTo(362.67902, 160.23102, 362.52902,
                160.09102, 362.22403, 160.09102);
        ((GeneralPath) shape).curveTo(361.78104, 160.09102, 361.55902,
                160.36502, 361.55902, 160.91502);
        ((GeneralPath) shape).lineTo(361.55902, 162.54802);
        ((GeneralPath) shape).lineTo(361.161, 162.54802);
        ((GeneralPath) shape).lineTo(361.161, 159.824);
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
        ((GeneralPath) shape).moveTo(363.838, 159.824);
        ((GeneralPath) shape).lineTo(363.838, 162.548);
        ((GeneralPath) shape).lineTo(363.44, 162.548);
        ((GeneralPath) shape).lineTo(363.44, 159.824);
        ((GeneralPath) shape).lineTo(363.838, 159.824);
        ((GeneralPath) shape).moveTo(363.838, 158.659);
        ((GeneralPath) shape).lineTo(363.838, 159.106);
        ((GeneralPath) shape).lineTo(363.44, 159.106);
        ((GeneralPath) shape).lineTo(363.44, 158.659);
        ((GeneralPath) shape).lineTo(363.838, 158.659);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(366.548, 159.824);
        ((GeneralPath) shape).lineTo(365.638, 162.548);
        ((GeneralPath) shape).lineTo(365.066, 162.548);
        ((GeneralPath) shape).lineTo(364.164, 159.824);
        ((GeneralPath) shape).lineTo(364.574, 159.824);
        ((GeneralPath) shape).lineTo(365.055, 161.306);
        ((GeneralPath) shape).lineTo(365.205, 161.77);
        ((GeneralPath) shape).lineTo(365.276, 162.004);
        ((GeneralPath) shape).lineTo(365.351, 162.237);
        ((GeneralPath) shape).lineTo(365.361, 162.237);
        ((GeneralPath) shape).lineTo(365.43, 162.007);
        ((GeneralPath) shape).lineTo(365.498, 161.773);
        ((GeneralPath) shape).lineTo(365.644, 161.312);
        ((GeneralPath) shape).lineTo(366.104, 159.824);
        ((GeneralPath) shape).lineTo(366.548, 159.824);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(367.273, 159.824);
        ((GeneralPath) shape).lineTo(367.273, 162.548);
        ((GeneralPath) shape).lineTo(366.876, 162.548);
        ((GeneralPath) shape).lineTo(366.876, 159.824);
        ((GeneralPath) shape).lineTo(367.273, 159.824);
        ((GeneralPath) shape).moveTo(367.273, 158.659);
        ((GeneralPath) shape).lineTo(367.273, 159.106);
        ((GeneralPath) shape).lineTo(366.876, 159.106);
        ((GeneralPath) shape).lineTo(366.876, 158.659);
        ((GeneralPath) shape).lineTo(367.273, 158.659);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(367.971, 159.824);
        ((GeneralPath) shape).lineTo(368.355, 159.824);
        ((GeneralPath) shape).lineTo(368.34302, 160.194);
        ((GeneralPath) shape).lineTo(368.355, 160.203);
        ((GeneralPath) shape).curveTo(368.476, 159.924, 368.777, 159.784,
                369.259, 159.784);
        ((GeneralPath) shape).curveTo(369.647, 159.784, 369.91, 159.85199,
                370.048, 159.989);
        ((GeneralPath) shape).curveTo(370.185, 160.12599, 370.254, 160.388,
                370.254, 160.776);
        ((GeneralPath) shape).lineTo(370.254, 162.548);
        ((GeneralPath) shape).lineTo(369.857, 162.548);
        ((GeneralPath) shape).lineTo(369.857, 160.707);
        ((GeneralPath) shape).curveTo(369.857, 160.473, 369.81198, 160.317,
                369.723, 160.235);
        ((GeneralPath) shape).curveTo(369.634, 160.155, 369.462, 160.114,
                369.20798, 160.114);
        ((GeneralPath) shape).curveTo(368.64798, 160.114, 368.369, 160.379,
                368.369, 160.909);
        ((GeneralPath) shape).lineTo(368.369, 162.548);
        ((GeneralPath) shape).lineTo(367.97098, 162.548);
        ((GeneralPath) shape).lineTo(367.97098, 159.824);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(372.065, 160.115);
        ((GeneralPath) shape).curveTo(371.752, 160.115, 371.55, 160.18001,
                371.457, 160.313);
        ((GeneralPath) shape).curveTo(371.364, 160.444, 371.317, 160.733,
                371.317, 161.181);
        ((GeneralPath) shape).curveTo(371.317, 161.631, 371.36298, 161.923,
                371.457, 162.057);
        ((GeneralPath) shape).curveTo(371.549, 162.19101, 371.753, 162.25801,
                372.065, 162.25801);
        ((GeneralPath) shape).curveTo(372.38, 162.25801, 372.589, 162.18501,
                372.691, 162.03702);
        ((GeneralPath) shape).curveTo(372.79, 161.89001, 372.841, 161.58401,
                372.841, 161.11801);
        ((GeneralPath) shape).curveTo(372.841, 160.70801, 372.79102, 160.43901,
                372.691, 160.309);
        ((GeneralPath) shape).curveTo(372.592, 160.18, 372.383, 160.115,
                372.065, 160.115);
        ((GeneralPath) shape).moveTo(373.251, 159.824);
        ((GeneralPath) shape).lineTo(373.251, 162.724);
        ((GeneralPath) shape).curveTo(373.251, 163.119, 373.16602, 163.391,
                372.995, 163.539);
        ((GeneralPath) shape).curveTo(372.824, 163.687, 372.512, 163.761,
                372.057, 163.761);
        ((GeneralPath) shape).curveTo(371.652, 163.761, 371.374, 163.698,
                371.22702, 163.573);
        ((GeneralPath) shape).curveTo(371.079, 163.448, 371.00504, 163.214,
                371.00504, 162.869);
        ((GeneralPath) shape).lineTo(371.38904, 162.869);
        ((GeneralPath) shape).curveTo(371.38904, 163.103, 371.43204, 163.254,
                371.51804, 163.325);
        ((GeneralPath) shape).curveTo(371.60403, 163.395, 371.79202, 163.43,
                372.08502, 163.43);
        ((GeneralPath) shape).curveTo(372.389, 163.43, 372.592, 163.38399,
                372.696, 163.293);
        ((GeneralPath) shape).curveTo(372.80002, 163.202, 372.85303, 163.023,
                372.85303, 162.757);
        ((GeneralPath) shape).lineTo(372.85303, 162.21);
        ((GeneralPath) shape).lineTo(372.84402, 162.207);
        ((GeneralPath) shape).curveTo(372.74103, 162.459, 372.446, 162.586,
                371.95602, 162.586);
        ((GeneralPath) shape).curveTo(371.55603, 162.586, 371.278, 162.48799,
                371.12604, 162.289);
        ((GeneralPath) shape).curveTo(370.97604, 162.091, 370.89804, 161.729,
                370.89804, 161.202);
        ((GeneralPath) shape).curveTo(370.89804, 160.65799, 370.97504,
                160.28499, 371.12903, 160.084);
        ((GeneralPath) shape).curveTo(371.28302, 159.885, 371.57004, 159.783,
                371.989, 159.783);
        ((GeneralPath) shape).curveTo(372.43402, 159.783, 372.726, 159.92,
                372.862, 160.19301);
        ((GeneralPath) shape).lineTo(372.871, 160.19);
        ((GeneralPath) shape).lineTo(372.851, 159.823);
        ((GeneralPath) shape).lineTo(373.251, 159.823);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(332.351, 166.715);
        ((GeneralPath) shape).lineTo(331.91602, 166.715);
        ((GeneralPath) shape).curveTo(331.91602, 166.402, 331.864, 166.204,
                331.759, 166.118);
        ((GeneralPath) shape).curveTo(331.655, 166.034, 331.409, 165.99199,
                331.023, 165.99199);
        ((GeneralPath) shape).curveTo(330.565, 165.99199, 330.269, 166.03198,
                330.135, 166.11299);
        ((GeneralPath) shape).curveTo(330.00302, 166.193, 329.936, 166.37299,
                329.936, 166.65298);
        ((GeneralPath) shape).curveTo(329.936, 166.96599, 329.988, 167.15698,
                330.09302, 167.22499);
        ((GeneralPath) shape).curveTo(330.19702, 167.29399, 330.506, 167.33899,
                331.023, 167.36499);
        ((GeneralPath) shape).curveTo(331.627, 167.39099, 332.01102, 167.46799,
                332.17603, 167.596);
        ((GeneralPath) shape).curveTo(332.33902, 167.72299, 332.42203, 168.008,
                332.42203, 168.45099);
        ((GeneralPath) shape).curveTo(332.42203, 168.93, 332.32803, 169.23898,
                332.13904, 169.37999);
        ((GeneralPath) shape).curveTo(331.95105, 169.51999, 331.53503,
                169.59099, 330.88904, 169.59099);
        ((GeneralPath) shape).curveTo(330.33005, 169.59099, 329.95703,
                169.52098, 329.77304, 169.37898);
        ((GeneralPath) shape).curveTo(329.58804, 169.23798, 329.49603,
                168.95299, 329.49603, 168.52298);
        ((GeneralPath) shape).lineTo(329.49304, 168.34998);
        ((GeneralPath) shape).lineTo(329.92905, 168.34998);
        ((GeneralPath) shape).lineTo(329.92905, 168.44698);
        ((GeneralPath) shape).curveTo(329.92905, 168.79498, 329.98206,
                169.00998, 330.08804, 169.09497);
        ((GeneralPath) shape).curveTo(330.19205, 169.17796, 330.46603,
                169.22098, 330.90405, 169.22098);
        ((GeneralPath) shape).curveTo(331.40607, 169.22098, 331.71506,
                169.17998, 331.83105, 169.09497);
        ((GeneralPath) shape).curveTo(331.94604, 169.01097, 332.00507,
                168.78497, 332.00507, 168.41797);
        ((GeneralPath) shape).curveTo(332.00507, 168.18097, 331.96606,
                168.02296, 331.88705, 167.94296);
        ((GeneralPath) shape).curveTo(331.80905, 167.86496, 331.64404,
                167.81796, 331.39307, 167.80196);
        ((GeneralPath) shape).lineTo(330.93808, 167.77995);
        ((GeneralPath) shape).lineTo(330.50607, 167.75696);
        ((GeneralPath) shape).curveTo(329.84906, 167.71196, 329.51907,
                167.36896, 329.51907, 166.73096);
        ((GeneralPath) shape).curveTo(329.51907, 166.28996, 329.61508,
                165.99295, 329.80606, 165.84496);
        ((GeneralPath) shape).curveTo(329.99707, 165.69696, 330.37906,
                165.62296, 330.95206, 165.62296);
        ((GeneralPath) shape).curveTo(331.53204, 165.62296, 331.91006,
                165.69095, 332.08707, 165.82996);
        ((GeneralPath) shape).curveTo(332.263, 165.964, 332.351, 166.261,
                332.351, 166.715);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(333.329, 165.661);
        ((GeneralPath) shape).lineTo(333.329, 167.92);
        ((GeneralPath) shape).lineTo(333.511, 167.92);
        ((GeneralPath) shape).lineTo(334.472, 166.826);
        ((GeneralPath) shape).lineTo(334.973, 166.826);
        ((GeneralPath) shape).lineTo(333.81, 168.077);
        ((GeneralPath) shape).lineTo(335.183, 169.55);
        ((GeneralPath) shape).lineTo(334.646, 169.55);
        ((GeneralPath) shape).lineTo(333.491, 168.231);
        ((GeneralPath) shape).lineTo(333.329, 168.231);
        ((GeneralPath) shape).lineTo(333.329, 169.55);
        ((GeneralPath) shape).lineTo(332.931, 169.55);
        ((GeneralPath) shape).lineTo(332.931, 165.661);
        ((GeneralPath) shape).lineTo(333.329, 165.661);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(335.833, 166.826);
        ((GeneralPath) shape).lineTo(335.833, 169.55);
        ((GeneralPath) shape).lineTo(335.436, 169.55);
        ((GeneralPath) shape).lineTo(335.436, 166.826);
        ((GeneralPath) shape).lineTo(335.833, 166.826);
        ((GeneralPath) shape).moveTo(335.833, 165.661);
        ((GeneralPath) shape).lineTo(335.833, 166.109);
        ((GeneralPath) shape).lineTo(335.436, 166.109);
        ((GeneralPath) shape).lineTo(335.436, 165.661);
        ((GeneralPath) shape).lineTo(335.833, 165.661);
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
        paint = new Color(35, 31, 32, 255);
        shape = new Rectangle2D.Double(336.5299987792969, 165.66099548339844,
                0.39800000190734863, 3.890000104904175);
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
        paint = new Color(35, 31, 32, 255);
        shape = new Rectangle2D.Double(337.625, 165.66099548339844,
                0.39800000190734863, 3.890000104904175);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(340.216, 166.826);
        ((GeneralPath) shape).lineTo(340.614, 166.826);
        ((GeneralPath) shape).lineTo(340.574, 167.139);
        ((GeneralPath) shape).lineTo(340.583, 167.14801);
        ((GeneralPath) shape).curveTo(340.739, 166.89201, 340.999, 166.76302,
                341.362, 166.76302);
        ((GeneralPath) shape).curveTo(341.862, 166.76302, 342.113, 167.02101,
                342.113, 167.53801);
        ((GeneralPath) shape).lineTo(342.11002, 167.72601);
        ((GeneralPath) shape).lineTo(341.717, 167.72601);
        ((GeneralPath) shape).lineTo(341.725, 167.65802);
        ((GeneralPath) shape).curveTo(341.73102, 167.58702, 341.734, 167.53802,
                341.734, 167.51302);
        ((GeneralPath) shape).curveTo(341.734, 167.23401, 341.584, 167.09401,
                341.27902, 167.09401);
        ((GeneralPath) shape).curveTo(340.83603, 167.09401, 340.614, 167.367,
                340.614, 167.91801);
        ((GeneralPath) shape).lineTo(340.614, 169.55002);
        ((GeneralPath) shape).lineTo(340.216, 169.55002);
        ((GeneralPath) shape).lineTo(340.216, 166.826);
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
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(343.618, 167.117);
        ((GeneralPath) shape).curveTo(343.265, 167.117, 343.048, 167.17401,
                342.963, 167.289);
        ((GeneralPath) shape).curveTo(342.879, 167.403, 342.836, 167.704,
                342.836, 168.188);
        ((GeneralPath) shape).curveTo(342.836, 168.67201, 342.87698, 168.972,
                342.963, 169.087);
        ((GeneralPath) shape).curveTo(343.04602, 169.201, 343.26602, 169.259,
                343.618, 169.259);
        ((GeneralPath) shape).curveTo(343.97202, 169.259, 344.191, 169.202,
                344.276, 169.087);
        ((GeneralPath) shape).curveTo(344.36002, 168.973, 344.402, 168.67201,
                344.402, 168.188);
        ((GeneralPath) shape).curveTo(344.402, 167.704, 344.36102, 167.404,
                344.276, 167.289);
        ((GeneralPath) shape).curveTo(344.192, 167.175, 343.974, 167.117,
                343.618, 167.117);
        ((GeneralPath) shape).moveTo(343.618, 166.787);
        ((GeneralPath) shape).curveTo(344.12003, 166.787, 344.445, 166.87401,
                344.59702, 167.049);
        ((GeneralPath) shape).curveTo(344.74503, 167.222, 344.82202, 167.603,
                344.82202, 168.189);
        ((GeneralPath) shape).curveTo(344.82202, 168.773, 344.74802, 169.153,
                344.59702, 169.329);
        ((GeneralPath) shape).curveTo(344.44803, 169.50299, 344.121, 169.59099,
                343.618, 169.59099);
        ((GeneralPath) shape).curveTo(343.117, 169.59099, 342.793, 169.50398,
                342.642, 169.329);
        ((GeneralPath) shape).curveTo(342.493, 169.155, 342.417, 168.775,
                342.417, 168.189);
        ((GeneralPath) shape).curveTo(342.417, 167.605, 342.49, 167.22499,
                342.642, 167.049);
        ((GeneralPath) shape).curveTo(342.792, 166.875, 343.117, 166.787,
                343.618, 166.787);
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
        paint = new Color(35, 31, 32, 255);
        shape = new Rectangle2D.Double(345.4410095214844, 165.66099548339844,
                0.39800000190734863, 3.890000104904175);
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
        shape = new Rectangle2D.Double(346.5369873046875, 165.66099548339844,
                0.3970000147819519, 3.890000104904175);
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
        ((GeneralPath) shape).moveTo(349.739, 167.539);
        ((GeneralPath) shape).lineTo(349.341, 167.539);
        ((GeneralPath) shape).curveTo(349.341, 167.35, 349.303, 167.231,
                349.226, 167.186);
        ((GeneralPath) shape).curveTo(349.14902, 167.14, 348.95203, 167.11801,
                348.63303, 167.11801);
        ((GeneralPath) shape).curveTo(348.33704, 167.11801, 348.15002,
                167.14201, 348.07004, 167.19101);
        ((GeneralPath) shape).curveTo(347.99103, 167.23901, 347.95105,
                167.35501, 347.95105, 167.54001);
        ((GeneralPath) shape).curveTo(347.95105, 167.81902, 348.08505,
                167.96501, 348.35104, 167.979);
        ((GeneralPath) shape).lineTo(348.67303, 167.996);
        ((GeneralPath) shape).lineTo(349.07904, 168.016);
        ((GeneralPath) shape).curveTo(349.57104, 168.04001, 349.81805, 168.298,
                349.81805, 168.791);
        ((GeneralPath) shape).curveTo(349.81805, 169.096, 349.73706, 169.307,
                349.57407, 169.421);
        ((GeneralPath) shape).curveTo(349.41208, 169.535, 349.11307, 169.59201,
                348.67807, 169.59201);
        ((GeneralPath) shape).curveTo(348.23407, 169.59201, 347.92606,
                169.53801, 347.75806, 169.42902);
        ((GeneralPath) shape).curveTo(347.59006, 169.32002, 347.50604,
                169.12302, 347.50604, 168.83401);
        ((GeneralPath) shape).lineTo(347.50903, 168.68602);
        ((GeneralPath) shape).lineTo(347.92203, 168.68602);
        ((GeneralPath) shape).lineTo(347.92502, 168.81403);
        ((GeneralPath) shape).curveTo(347.92502, 168.99202, 347.97003,
                169.11203, 348.061, 169.17102);
        ((GeneralPath) shape).curveTo(348.153, 169.23102, 348.332, 169.26102,
                348.599, 169.26102);
        ((GeneralPath) shape).curveTo(348.926, 169.26102, 349.142, 169.23001,
                349.245, 169.16702);
        ((GeneralPath) shape).curveTo(349.348, 169.10402, 349.4, 168.97403,
                349.4, 168.77402);
        ((GeneralPath) shape).curveTo(349.4, 168.48802, 349.271, 168.34402,
                349.00998, 168.34402);
        ((GeneralPath) shape).curveTo(348.40598, 168.34402, 348.00797,
                168.29303, 347.817, 168.19002);
        ((GeneralPath) shape).curveTo(347.62698, 168.08702, 347.53198,
                167.87502, 347.53198, 167.55202);
        ((GeneralPath) shape).curveTo(347.53198, 167.24702, 347.607, 167.04301,
                347.758, 166.94102);
        ((GeneralPath) shape).curveTo(347.909, 166.84003, 348.20898, 166.78903,
                348.658, 166.78903);
        ((GeneralPath) shape).curveTo(349.378, 166.78903, 349.73898, 167.00504,
                349.73898, 167.44102);
        ((GeneralPath) shape).lineTo(349.73898, 167.539);
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
        ((GeneralPath) shape).moveTo(262.267, 160.362);
        ((GeneralPath) shape).lineTo(262.267, 164.251);
        ((GeneralPath) shape).lineTo(261.829, 164.251);
        ((GeneralPath) shape).lineTo(261.829, 160.906);
        ((GeneralPath) shape).lineTo(261.832, 160.789);
        ((GeneralPath) shape).lineTo(261.835, 160.672);
        ((GeneralPath) shape).lineTo(261.824, 160.672);
        ((GeneralPath) shape).lineTo(261.79, 160.763);
        ((GeneralPath) shape).curveTo(261.773, 160.808, 261.76102, 160.837,
                261.756, 160.851);
        ((GeneralPath) shape).lineTo(261.682, 161.034);
        ((GeneralPath) shape).lineTo(260.36002, 164.25099);
        ((GeneralPath) shape).lineTo(259.92203, 164.25099);
        ((GeneralPath) shape).lineTo(258.59702, 161.071);
        ((GeneralPath) shape).lineTo(258.52002, 160.891);
        ((GeneralPath) shape).lineTo(258.48602, 160.8);
        ((GeneralPath) shape).curveTo(258.47702, 160.78, 258.46603, 160.751,
                258.45203, 160.712);
        ((GeneralPath) shape).lineTo(258.44003, 160.712);
        ((GeneralPath) shape).lineTo(258.44302, 160.817);
        ((GeneralPath) shape).lineTo(258.44504, 160.926);
        ((GeneralPath) shape).lineTo(258.44504, 164.25099);
        ((GeneralPath) shape).lineTo(258.00705, 164.25099);
        ((GeneralPath) shape).lineTo(258.00705, 160.362);
        ((GeneralPath) shape).lineTo(258.76706, 160.362);
        ((GeneralPath) shape).lineTo(259.80206, 162.878);
        ((GeneralPath) shape).lineTo(259.96707, 163.285);
        ((GeneralPath) shape).lineTo(260.05008, 163.488);
        ((GeneralPath) shape).lineTo(260.13007, 163.69);
        ((GeneralPath) shape).lineTo(260.14105, 163.69);
        ((GeneralPath) shape).lineTo(260.22104, 163.488);
        ((GeneralPath) shape).curveTo(260.26105, 163.391, 260.28604, 163.324,
                260.30103, 163.285);
        ((GeneralPath) shape).lineTo(260.46902, 162.881);
        ((GeneralPath) shape).lineTo(261.49802, 160.362);
        ((GeneralPath) shape).lineTo(262.267, 160.362);
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
        ((GeneralPath) shape).moveTo(264.108, 161.818);
        ((GeneralPath) shape).curveTo(263.755, 161.818, 263.538, 161.875,
                263.452, 161.98999);
        ((GeneralPath) shape).curveTo(263.36798, 162.10399, 263.326, 162.40498,
                263.326, 162.88899);
        ((GeneralPath) shape).curveTo(263.326, 163.373, 263.36697, 163.67299,
                263.452, 163.788);
        ((GeneralPath) shape).curveTo(263.536, 163.902, 263.755, 163.95999,
                264.108, 163.95999);
        ((GeneralPath) shape).curveTo(264.462, 163.95999, 264.681, 163.90298,
                264.766, 163.788);
        ((GeneralPath) shape).curveTo(264.85, 163.674, 264.892, 163.373,
                264.892, 162.88899);
        ((GeneralPath) shape).curveTo(264.892, 162.40498, 264.851, 162.105,
                264.766, 161.98999);
        ((GeneralPath) shape).curveTo(264.682, 161.876, 264.463, 161.818,
                264.108, 161.818);
        ((GeneralPath) shape).moveTo(264.108, 161.487);
        ((GeneralPath) shape).curveTo(264.609, 161.487, 264.936, 161.574,
                265.086, 161.749);
        ((GeneralPath) shape).curveTo(265.235, 161.92299, 265.311, 162.303,
                265.311, 162.88899);
        ((GeneralPath) shape).curveTo(265.311, 163.47299, 265.237, 163.853,
                265.086, 164.02899);
        ((GeneralPath) shape).curveTo(264.937, 164.20299, 264.611, 164.29099,
                264.108, 164.29099);
        ((GeneralPath) shape).curveTo(263.608, 164.29099, 263.283, 164.20398,
                263.132, 164.02899);
        ((GeneralPath) shape).curveTo(262.983, 163.85599, 262.90698, 163.47499,
                262.90698, 162.88899);
        ((GeneralPath) shape).curveTo(262.90698, 162.305, 262.981, 161.92499,
                263.132, 161.749);
        ((GeneralPath) shape).curveTo(263.282, 161.576, 263.607, 161.487,
                264.108, 161.487);
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
        ((GeneralPath) shape).moveTo(267.006, 161.818);
        ((GeneralPath) shape).curveTo(266.713, 161.818, 266.517, 161.885,
                266.42, 162.02199);
        ((GeneralPath) shape).curveTo(266.32303, 162.15698, 266.27502,
                162.43298, 266.27502, 162.84698);
        ((GeneralPath) shape).curveTo(266.27502, 163.30298, 266.32202,
                163.60199, 266.41904, 163.74599);
        ((GeneralPath) shape).curveTo(266.51404, 163.88799, 266.71304,
                163.96098, 267.01505, 163.96098);
        ((GeneralPath) shape).curveTo(267.34204, 163.96098, 267.55505,
                163.88998, 267.65506, 163.74599);
        ((GeneralPath) shape).curveTo(267.75305, 163.60298, 267.80307,
                163.29099, 267.80307, 162.80998);
        ((GeneralPath) shape).curveTo(267.80307, 162.42097, 267.74908,
                162.15898, 267.63907, 162.02199);
        ((GeneralPath) shape).curveTo(267.531, 161.886, 267.319, 161.818,
                267.006, 161.818);
        ((GeneralPath) shape).moveTo(268.204, 160.362);
        ((GeneralPath) shape).lineTo(268.204, 164.251);
        ((GeneralPath) shape).lineTo(267.806, 164.251);
        ((GeneralPath) shape).lineTo(267.826, 163.89801);
        ((GeneralPath) shape).lineTo(267.814, 163.895);
        ((GeneralPath) shape).curveTo(267.689, 164.15901, 267.399, 164.291,
                266.941, 164.291);
        ((GeneralPath) shape).curveTo(266.52402, 164.291, 266.24002, 164.19,
                266.087, 163.98601);
        ((GeneralPath) shape).curveTo(265.935, 163.78401, 265.858, 163.404,
                265.858, 162.84901);
        ((GeneralPath) shape).curveTo(265.858, 162.33902, 265.935, 161.98401,
                266.088, 161.78502);
        ((GeneralPath) shape).curveTo(266.24103, 161.58702, 266.51602,
                161.48701, 266.91302, 161.48701);
        ((GeneralPath) shape).curveTo(267.40802, 161.48701, 267.70203,
                161.61502, 267.79703, 161.87201);
        ((GeneralPath) shape).lineTo(267.80603, 161.86601);
        ((GeneralPath) shape).lineTo(267.80603, 160.36201);
        ((GeneralPath) shape).lineTo(268.204, 160.36201);
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
        ((GeneralPath) shape).moveTo(269.3, 161.527);
        ((GeneralPath) shape).lineTo(269.3, 164.25099);
        ((GeneralPath) shape).lineTo(268.90198, 164.25099);
        ((GeneralPath) shape).lineTo(268.90198, 161.527);
        ((GeneralPath) shape).lineTo(269.3, 161.527);
        ((GeneralPath) shape).moveTo(269.3, 160.362);
        ((GeneralPath) shape).lineTo(269.3, 160.809);
        ((GeneralPath) shape).lineTo(268.90198, 160.809);
        ((GeneralPath) shape).lineTo(268.90198, 160.362);
        ((GeneralPath) shape).lineTo(269.3, 160.362);
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
        ((GeneralPath) shape).moveTo(270.102, 164.251);
        ((GeneralPath) shape).lineTo(270.102, 161.858);
        ((GeneralPath) shape).lineTo(269.68698, 161.858);
        ((GeneralPath) shape).lineTo(269.68698, 161.52701);
        ((GeneralPath) shape).lineTo(270.102, 161.52701);
        ((GeneralPath) shape).lineTo(270.102, 161.12201);
        ((GeneralPath) shape).curveTo(270.102, 160.589, 270.375, 160.32101,
                270.92398, 160.32101);
        ((GeneralPath) shape).curveTo(271.00497, 160.32101, 271.10098,
                160.32701, 271.21097, 160.34102);
        ((GeneralPath) shape).lineTo(271.21097, 160.67201);
        ((GeneralPath) shape).curveTo(271.08298, 160.65901, 270.98898,
                160.65201, 270.92996, 160.65201);
        ((GeneralPath) shape).curveTo(270.64395, 160.65201, 270.49997, 160.796,
                270.49997, 161.085);
        ((GeneralPath) shape).lineTo(270.49997, 161.52701);
        ((GeneralPath) shape).lineTo(271.21097, 161.52701);
        ((GeneralPath) shape).lineTo(271.21097, 161.858);
        ((GeneralPath) shape).lineTo(270.5, 161.858);
        ((GeneralPath) shape).lineTo(270.5, 164.251);
        ((GeneralPath) shape).lineTo(270.102, 164.251);
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
        ((GeneralPath) shape).moveTo(271.938, 161.527);
        ((GeneralPath) shape).lineTo(271.938, 164.25099);
        ((GeneralPath) shape).lineTo(271.53998, 164.25099);
        ((GeneralPath) shape).lineTo(271.53998, 161.527);
        ((GeneralPath) shape).lineTo(271.938, 161.527);
        ((GeneralPath) shape).moveTo(271.938, 160.362);
        ((GeneralPath) shape).lineTo(271.938, 160.809);
        ((GeneralPath) shape).lineTo(271.53998, 160.809);
        ((GeneralPath) shape).lineTo(271.53998, 160.362);
        ((GeneralPath) shape).lineTo(271.938, 160.362);
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
        ((GeneralPath) shape).moveTo(274.526, 162.652);
        ((GeneralPath) shape).lineTo(274.523, 162.52399);
        ((GeneralPath) shape).curveTo(274.523, 162.23099, 274.475, 162.038,
                274.37802, 161.94998);
        ((GeneralPath) shape).curveTo(274.28104, 161.86198, 274.07404,
                161.81798, 273.752, 161.81798);
        ((GeneralPath) shape).curveTo(273.431, 161.81798, 273.22202, 161.86897,
                273.125, 161.97298);
        ((GeneralPath) shape).curveTo(273.03, 162.07597, 272.981, 162.30197,
                272.981, 162.65198);
        ((GeneralPath) shape).lineTo(274.526, 162.65198);
        ((GeneralPath) shape).moveTo(274.526, 163.428);
        ((GeneralPath) shape).lineTo(274.933, 163.428);
        ((GeneralPath) shape).lineTo(274.93503, 163.528);
        ((GeneralPath) shape).curveTo(274.93503, 163.81, 274.85004, 164.008,
                274.67804, 164.122);
        ((GeneralPath) shape).curveTo(274.50803, 164.235, 274.20605, 164.291,
                273.77502, 164.291);
        ((GeneralPath) shape).curveTo(273.27502, 164.291, 272.946, 164.2,
                272.79004, 164.016);
        ((GeneralPath) shape).curveTo(272.63303, 163.834, 272.55505, 163.44801,
                272.55505, 162.86101);
        ((GeneralPath) shape).curveTo(272.55505, 162.31801, 272.63306, 161.953,
                272.79105, 161.76701);
        ((GeneralPath) shape).curveTo(272.94705, 161.58101, 273.25604, 161.488,
                273.71506, 161.488);
        ((GeneralPath) shape).curveTo(274.21606, 161.488, 274.54306, 161.56801,
                274.69907, 161.73001);
        ((GeneralPath) shape).curveTo(274.85406, 161.891, 274.93207, 162.23102,
                274.93207, 162.75002);
        ((GeneralPath) shape).lineTo(274.93207, 162.96402);
        ((GeneralPath) shape).lineTo(272.97507, 162.96402);
        ((GeneralPath) shape).curveTo(272.97507, 163.39302, 273.02106,
                163.66602, 273.11307, 163.78502);
        ((GeneralPath) shape).curveTo(273.20407, 163.90102, 273.41907,
                163.96101, 273.75708, 163.96101);
        ((GeneralPath) shape).curveTo(274.0771, 163.96101, 274.2841, 163.93402,
                274.38107, 163.87701);
        ((GeneralPath) shape).curveTo(274.47708, 163.82202, 274.5251,
                163.70001, 274.5251, 163.514);
        ((GeneralPath) shape).lineTo(274.5251, 163.428);
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
        ((GeneralPath) shape).moveTo(275.599, 161.527);
        ((GeneralPath) shape).lineTo(275.997, 161.527);
        ((GeneralPath) shape).lineTo(275.957, 161.84);
        ((GeneralPath) shape).lineTo(275.966, 161.84799);
        ((GeneralPath) shape).curveTo(276.122, 161.592, 276.383, 161.463,
                276.745, 161.463);
        ((GeneralPath) shape).curveTo(277.246, 161.463, 277.496, 161.721,
                277.496, 162.23799);
        ((GeneralPath) shape).lineTo(277.494, 162.426);
        ((GeneralPath) shape).lineTo(277.10098, 162.426);
        ((GeneralPath) shape).lineTo(277.11, 162.358);
        ((GeneralPath) shape).curveTo(277.116, 162.287, 277.119, 162.238,
                277.119, 162.212);
        ((GeneralPath) shape).curveTo(277.119, 161.933, 276.968, 161.793,
                276.664, 161.793);
        ((GeneralPath) shape).curveTo(276.22, 161.793, 275.99802, 162.066,
                275.99802, 162.617);
        ((GeneralPath) shape).lineTo(275.99802, 164.24901);
        ((GeneralPath) shape).lineTo(275.6, 164.24901);
        ((GeneralPath) shape).lineTo(275.6, 161.527);
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
        ((GeneralPath) shape).moveTo(280.776, 161.527);
        ((GeneralPath) shape).lineTo(280.776, 161.85799);
        ((GeneralPath) shape).lineTo(279.729, 161.85799);
        ((GeneralPath) shape).lineTo(279.729, 163.525);
        ((GeneralPath) shape).curveTo(279.729, 163.816, 279.857, 163.961,
                280.116, 163.961);
        ((GeneralPath) shape).curveTo(280.372, 163.961, 280.5, 163.832, 280.5,
                163.571);
        ((GeneralPath) shape).lineTo(280.502, 163.437);
        ((GeneralPath) shape).lineTo(280.50803, 163.286);
        ((GeneralPath) shape).lineTo(280.87802, 163.286);
        ((GeneralPath) shape).lineTo(280.881, 163.489);
        ((GeneralPath) shape).curveTo(280.881, 164.025, 280.62802, 164.29199,
                280.11902, 164.29199);
        ((GeneralPath) shape).curveTo(279.59402, 164.29199, 279.33102,
                164.06999, 279.33102, 163.62299);
        ((GeneralPath) shape).lineTo(279.33102, 161.85898);
        ((GeneralPath) shape).lineTo(278.95602, 161.85898);
        ((GeneralPath) shape).lineTo(278.95602, 161.52798);
        ((GeneralPath) shape).lineTo(279.33102, 161.52798);
        ((GeneralPath) shape).lineTo(279.33102, 160.87299);
        ((GeneralPath) shape).lineTo(279.72903, 160.87299);
        ((GeneralPath) shape).lineTo(279.72903, 161.52798);
        ((GeneralPath) shape).lineTo(280.776, 161.52798);
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
        ((GeneralPath) shape).moveTo(282.355, 161.818);
        ((GeneralPath) shape).curveTo(282.002, 161.818, 281.785, 161.875,
                281.7, 161.98999);
        ((GeneralPath) shape).curveTo(281.616, 162.10399, 281.573, 162.40498,
                281.573, 162.88899);
        ((GeneralPath) shape).curveTo(281.573, 163.373, 281.615, 163.67299,
                281.7, 163.788);
        ((GeneralPath) shape).curveTo(281.78302, 163.902, 282.00302, 163.95999,
                282.355, 163.95999);
        ((GeneralPath) shape).curveTo(282.709, 163.95999, 282.928, 163.90298,
                283.014, 163.788);
        ((GeneralPath) shape).curveTo(283.09702, 163.674, 283.14, 163.373,
                283.14, 162.88899);
        ((GeneralPath) shape).curveTo(283.14, 162.40498, 283.09802, 162.105,
                283.014, 161.98999);
        ((GeneralPath) shape).curveTo(282.93, 161.876, 282.711, 161.818,
                282.355, 161.818);
        ((GeneralPath) shape).moveTo(282.355, 161.487);
        ((GeneralPath) shape).curveTo(282.85703, 161.487, 283.183, 161.574,
                283.334, 161.749);
        ((GeneralPath) shape).curveTo(283.483, 161.92299, 283.55902, 162.303,
                283.55902, 162.88899);
        ((GeneralPath) shape).curveTo(283.55902, 163.47299, 283.48502, 163.853,
                283.334, 164.02899);
        ((GeneralPath) shape).curveTo(283.18503, 164.20299, 282.859, 164.29099,
                282.355, 164.29099);
        ((GeneralPath) shape).curveTo(281.855, 164.29099, 281.53, 164.20398,
                281.379, 164.02899);
        ((GeneralPath) shape).curveTo(281.23, 163.85599, 281.154, 163.47499,
                281.154, 162.88899);
        ((GeneralPath) shape).curveTo(281.154, 162.305, 281.228, 161.92499,
                281.379, 161.749);
        ((GeneralPath) shape).curveTo(281.529, 161.576, 281.855, 161.487,
                282.355, 161.487);
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
        ((GeneralPath) shape).moveTo(286.691, 162.903);
        ((GeneralPath) shape).curveTo(286.38702, 162.903, 286.187, 162.934,
                286.095, 163.0);
        ((GeneralPath) shape).curveTo(286.004, 163.064, 285.957, 163.202,
                285.957, 163.416);
        ((GeneralPath) shape).curveTo(285.957, 163.635, 286.002, 163.782,
                286.094, 163.853);
        ((GeneralPath) shape).curveTo(286.185, 163.924, 286.369, 163.95999,
                286.649, 163.95999);
        ((GeneralPath) shape).curveTo(287.20898, 163.95999, 287.491, 163.78899,
                287.491, 163.44699);
        ((GeneralPath) shape).curveTo(287.491, 163.23299, 287.437, 163.088,
                287.327, 163.01399);
        ((GeneralPath) shape).curveTo(287.218, 162.94, 287.006, 162.903,
                286.691, 162.903);
        ((GeneralPath) shape).moveTo(286.051, 162.291);
        ((GeneralPath) shape).lineTo(285.655, 162.291);
        ((GeneralPath) shape).curveTo(285.655, 161.975, 285.726, 161.761,
                285.87, 161.651);
        ((GeneralPath) shape).curveTo(286.013, 161.543, 286.292, 161.487,
                286.711, 161.487);
        ((GeneralPath) shape).curveTo(287.163, 161.487, 287.47, 161.554,
                287.629, 161.688);
        ((GeneralPath) shape).curveTo(287.788, 161.822, 287.868, 162.07701,
                287.868, 162.453);
        ((GeneralPath) shape).lineTo(287.868, 164.251);
        ((GeneralPath) shape).lineTo(287.47, 164.251);
        ((GeneralPath) shape).lineTo(287.501, 163.95801);
        ((GeneralPath) shape).lineTo(287.493, 163.95601);
        ((GeneralPath) shape).curveTo(287.342, 164.18001, 287.034, 164.292,
                286.569, 164.292);
        ((GeneralPath) shape).curveTo(285.883, 164.292, 285.539, 164.016,
                285.539, 163.46301);
        ((GeneralPath) shape).curveTo(285.539, 163.13501, 285.616, 162.90701,
                285.77, 162.78201);
        ((GeneralPath) shape).curveTo(285.923, 162.65701, 286.201, 162.59401,
                286.603, 162.59401);
        ((GeneralPath) shape).curveTo(287.081, 162.59401, 287.367, 162.688,
                287.462, 162.876);
        ((GeneralPath) shape).lineTo(287.47, 162.873);
        ((GeneralPath) shape).lineTo(287.47, 162.542);
        ((GeneralPath) shape).curveTo(287.47, 162.231, 287.428, 162.029,
                287.342, 161.93701);
        ((GeneralPath) shape).curveTo(287.25702, 161.84601, 287.071, 161.79901,
                286.782, 161.79901);
        ((GeneralPath) shape).curveTo(286.293, 161.79901, 286.048, 161.936,
                286.048, 162.212);
        ((GeneralPath) shape).curveTo(286.048, 162.224, 286.048, 162.251,
                286.051, 162.291);
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
        shape = new Rectangle2D.Double(288.6059875488281, 160.36199951171875,
                0.39800000190734863, 3.8889999389648438);
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
        shape = new Rectangle2D.Double(289.70098876953125, 160.36199951171875,
                0.39800000190734863, 3.8889999389648438);
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
        ((GeneralPath) shape).moveTo(294.935, 161.416);
        ((GeneralPath) shape).lineTo(294.5, 161.416);
        ((GeneralPath) shape).curveTo(294.5, 161.103, 294.449, 160.905,
                294.343, 160.819);
        ((GeneralPath) shape).curveTo(294.23898, 160.735, 293.99298, 160.692,
                293.606, 160.692);
        ((GeneralPath) shape).curveTo(293.14798, 160.692, 292.853, 160.732,
                292.719, 160.813);
        ((GeneralPath) shape).curveTo(292.587, 160.893, 292.52, 161.074,
                292.52, 161.353);
        ((GeneralPath) shape).curveTo(292.52, 161.666, 292.57098, 161.857,
                292.677, 161.926);
        ((GeneralPath) shape).curveTo(292.781, 161.99399, 293.091, 162.04,
                293.607, 162.065);
        ((GeneralPath) shape).curveTo(294.211, 162.091, 294.595, 162.168,
                294.76, 162.296);
        ((GeneralPath) shape).curveTo(294.924, 162.423, 295.006, 162.70801,
                295.006, 163.151);
        ((GeneralPath) shape).curveTo(295.006, 163.63, 294.91202, 163.939,
                294.72302, 164.08);
        ((GeneralPath) shape).curveTo(294.53503, 164.22, 294.11902, 164.291,
                293.47302, 164.291);
        ((GeneralPath) shape).curveTo(292.91403, 164.291, 292.54102, 164.221,
                292.35703, 164.079);
        ((GeneralPath) shape).curveTo(292.17203, 163.93799, 292.08002, 163.653,
                292.08002, 163.22299);
        ((GeneralPath) shape).lineTo(292.07703, 163.049);
        ((GeneralPath) shape).lineTo(292.51202, 163.049);
        ((GeneralPath) shape).lineTo(292.51202, 163.146);
        ((GeneralPath) shape).curveTo(292.51202, 163.494, 292.56503, 163.709,
                292.67102, 163.79399);
        ((GeneralPath) shape).curveTo(292.77603, 163.87799, 293.049, 163.92099,
                293.48703, 163.92099);
        ((GeneralPath) shape).curveTo(293.98904, 163.92099, 294.29803,
                163.87898, 294.41403, 163.79399);
        ((GeneralPath) shape).curveTo(294.53003, 163.70999, 294.58804, 163.484,
                294.58804, 163.11699);
        ((GeneralPath) shape).curveTo(294.58804, 162.881, 294.54904, 162.72198,
                294.47003, 162.64198);
        ((GeneralPath) shape).curveTo(294.39203, 162.56398, 294.22702,
                162.51698, 293.97604, 162.50098);
        ((GeneralPath) shape).lineTo(293.52106, 162.47798);
        ((GeneralPath) shape).lineTo(293.08905, 162.45499);
        ((GeneralPath) shape).curveTo(292.43204, 162.40999, 292.10205,
                162.06699, 292.10205, 161.42899);
        ((GeneralPath) shape).curveTo(292.10205, 160.98799, 292.19806,
                160.69098, 292.38904, 160.54298);
        ((GeneralPath) shape).curveTo(292.58005, 160.39499, 292.96204,
                160.32098, 293.53503, 160.32098);
        ((GeneralPath) shape).curveTo(294.11502, 160.32098, 294.49304,
                160.38998, 294.67004, 160.52798);
        ((GeneralPath) shape).curveTo(294.846, 160.665, 294.935, 160.961,
                294.935, 161.416);
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
        ((GeneralPath) shape).moveTo(295.907, 160.362);
        ((GeneralPath) shape).lineTo(295.907, 162.621);
        ((GeneralPath) shape).lineTo(296.089, 162.621);
        ((GeneralPath) shape).lineTo(297.05, 161.527);
        ((GeneralPath) shape).lineTo(297.55, 161.527);
        ((GeneralPath) shape).lineTo(296.387, 162.778);
        ((GeneralPath) shape).lineTo(297.761, 164.251);
        ((GeneralPath) shape).lineTo(297.223, 164.251);
        ((GeneralPath) shape).lineTo(296.068, 162.932);
        ((GeneralPath) shape).lineTo(295.907, 162.932);
        ((GeneralPath) shape).lineTo(295.907, 164.251);
        ((GeneralPath) shape).lineTo(295.508, 164.251);
        ((GeneralPath) shape).lineTo(295.508, 160.362);
        ((GeneralPath) shape).lineTo(295.907, 160.362);
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
        ((GeneralPath) shape).moveTo(298.411, 161.527);
        ((GeneralPath) shape).lineTo(298.411, 164.25099);
        ((GeneralPath) shape).lineTo(298.013, 164.25099);
        ((GeneralPath) shape).lineTo(298.013, 161.527);
        ((GeneralPath) shape).lineTo(298.411, 161.527);
        ((GeneralPath) shape).moveTo(298.411, 160.362);
        ((GeneralPath) shape).lineTo(298.411, 160.809);
        ((GeneralPath) shape).lineTo(298.013, 160.809);
        ((GeneralPath) shape).lineTo(298.013, 160.362);
        ((GeneralPath) shape).lineTo(298.411, 160.362);
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
        shape = new Rectangle2D.Double(299.1080017089844, 160.36199951171875,
                0.39800000190734863, 3.8889999389648438);
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
        shape = new Rectangle2D.Double(300.2030029296875, 160.36199951171875,
                0.39800000190734863, 3.8889999389648438);
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
        ((GeneralPath) shape).moveTo(302.794, 161.527);
        ((GeneralPath) shape).lineTo(303.19202, 161.527);
        ((GeneralPath) shape).lineTo(303.152, 161.84);
        ((GeneralPath) shape).lineTo(303.161, 161.84799);
        ((GeneralPath) shape).curveTo(303.31702, 161.592, 303.578, 161.463,
                303.94, 161.463);
        ((GeneralPath) shape).curveTo(304.44, 161.463, 304.69, 161.721, 304.69,
                162.23799);
        ((GeneralPath) shape).lineTo(304.688, 162.426);
        ((GeneralPath) shape).lineTo(304.29498, 162.426);
        ((GeneralPath) shape).lineTo(304.304, 162.358);
        ((GeneralPath) shape).curveTo(304.31, 162.287, 304.313, 162.238,
                304.313, 162.212);
        ((GeneralPath) shape).curveTo(304.313, 161.933, 304.162, 161.793,
                303.858, 161.793);
        ((GeneralPath) shape).curveTo(303.414, 161.793, 303.19202, 162.066,
                303.19202, 162.617);
        ((GeneralPath) shape).lineTo(303.19202, 164.24901);
        ((GeneralPath) shape).lineTo(302.794, 164.24901);
        ((GeneralPath) shape).lineTo(302.794, 161.527);
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
        ((GeneralPath) shape).moveTo(306.195, 161.818);
        ((GeneralPath) shape).curveTo(305.842, 161.818, 305.625, 161.875,
                305.539, 161.98999);
        ((GeneralPath) shape).curveTo(305.455, 162.10399, 305.413, 162.40498,
                305.413, 162.88899);
        ((GeneralPath) shape).curveTo(305.413, 163.373, 305.45398, 163.67299,
                305.539, 163.788);
        ((GeneralPath) shape).curveTo(305.62302, 163.902, 305.842, 163.95999,
                306.195, 163.95999);
        ((GeneralPath) shape).curveTo(306.549, 163.95999, 306.768, 163.90298,
                306.853, 163.788);
        ((GeneralPath) shape).curveTo(306.937, 163.674, 306.98, 163.373,
                306.98, 162.88899);
        ((GeneralPath) shape).curveTo(306.98, 162.40498, 306.93802, 162.105,
                306.853, 161.98999);
        ((GeneralPath) shape).curveTo(306.77, 161.876, 306.551, 161.818,
                306.195, 161.818);
        ((GeneralPath) shape).moveTo(306.195, 161.487);
        ((GeneralPath) shape).curveTo(306.69702, 161.487, 307.022, 161.574,
                307.174, 161.749);
        ((GeneralPath) shape).curveTo(307.32202, 161.92299, 307.39902, 162.303,
                307.39902, 162.88899);
        ((GeneralPath) shape).curveTo(307.39902, 163.47299, 307.325, 163.853,
                307.174, 164.02899);
        ((GeneralPath) shape).curveTo(307.02502, 164.20299, 306.698, 164.29099,
                306.195, 164.29099);
        ((GeneralPath) shape).curveTo(305.694, 164.29099, 305.37, 164.20398,
                305.219, 164.02899);
        ((GeneralPath) shape).curveTo(305.07, 163.85599, 304.994, 163.47499,
                304.994, 162.88899);
        ((GeneralPath) shape).curveTo(304.994, 162.305, 305.068, 161.92499,
                305.219, 161.749);
        ((GeneralPath) shape).curveTo(305.369, 161.576, 305.694, 161.487,
                306.195, 161.487);
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
        shape = new Rectangle2D.Double(308.0199890136719, 160.36199951171875,
                0.39800000190734863, 3.8889999389648438);
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
        shape = new Rectangle2D.Double(309.114013671875, 160.36199951171875,
                0.39800000190734863, 3.8889999389648438);
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
        ((GeneralPath) shape).moveTo(312.316, 162.24);
        ((GeneralPath) shape).lineTo(311.918, 162.24);
        ((GeneralPath) shape).curveTo(311.918, 162.05101, 311.88, 161.932,
                311.804, 161.886);
        ((GeneralPath) shape).curveTo(311.727, 161.841, 311.529, 161.81801,
                311.21, 161.81801);
        ((GeneralPath) shape).curveTo(310.914, 161.81801, 310.727, 161.84201,
                310.647, 161.891);
        ((GeneralPath) shape).curveTo(310.56702, 161.93901, 310.527, 162.05501,
                310.527, 162.24);
        ((GeneralPath) shape).curveTo(310.527, 162.51901, 310.661, 162.664,
                310.928, 162.67801);
        ((GeneralPath) shape).lineTo(311.24902, 162.695);
        ((GeneralPath) shape).lineTo(311.65604, 162.71501);
        ((GeneralPath) shape).curveTo(312.14703, 162.73901, 312.39505,
                162.99701, 312.39505, 163.49);
        ((GeneralPath) shape).curveTo(312.39505, 163.795, 312.31406, 164.00601,
                312.15005, 164.119);
        ((GeneralPath) shape).curveTo(311.98807, 164.233, 311.69006, 164.29001,
                311.25406, 164.29001);
        ((GeneralPath) shape).curveTo(310.80905, 164.29001, 310.50204,
                164.23601, 310.33405, 164.128);
        ((GeneralPath) shape).curveTo(310.16605, 164.02, 310.08203, 163.821,
                310.08203, 163.53201);
        ((GeneralPath) shape).lineTo(310.08502, 163.38402);
        ((GeneralPath) shape).lineTo(310.497, 163.38402);
        ((GeneralPath) shape).lineTo(310.5, 163.51202);
        ((GeneralPath) shape).curveTo(310.5, 163.69002, 310.546, 163.81003,
                310.637, 163.86902);
        ((GeneralPath) shape).curveTo(310.728, 163.92902, 310.908, 163.95901,
                311.17398, 163.95901);
        ((GeneralPath) shape).curveTo(311.50098, 163.95901, 311.718, 163.92801,
                311.82098, 163.86502);
        ((GeneralPath) shape).curveTo(311.92398, 163.80202, 311.97598,
                163.67203, 311.97598, 163.47202);
        ((GeneralPath) shape).curveTo(311.97598, 163.18602, 311.84598,
                163.04202, 311.58597, 163.04202);
        ((GeneralPath) shape).curveTo(310.98196, 163.04202, 310.58298,
                162.99103, 310.39297, 162.88802);
        ((GeneralPath) shape).curveTo(310.20297, 162.785, 310.10696, 162.57301,
                310.10696, 162.25002);
        ((GeneralPath) shape).curveTo(310.10696, 161.94502, 310.18198,
                161.74101, 310.33395, 161.63902);
        ((GeneralPath) shape).curveTo(310.48395, 161.53802, 310.78397,
                161.48703, 311.23395, 161.48703);
        ((GeneralPath) shape).curveTo(311.95294, 161.48703, 312.31393,
                161.70403, 312.31393, 162.13902);
        ((GeneralPath) shape).lineTo(312.31393, 162.24);
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
        ((GeneralPath) shape).moveTo(313.385, 154.265);
        ((GeneralPath) shape).lineTo(313.385, 152.912);
        ((GeneralPath) shape).lineTo(312.047, 152.912);
        ((GeneralPath) shape).lineTo(312.047, 152.48);
        ((GeneralPath) shape).lineTo(313.385, 152.48);
        ((GeneralPath) shape).lineTo(313.385, 151.12);
        ((GeneralPath) shape).lineTo(313.849, 151.12);
        ((GeneralPath) shape).lineTo(313.849, 152.48);
        ((GeneralPath) shape).lineTo(315.187, 152.48);
        ((GeneralPath) shape).lineTo(315.187, 152.912);
        ((GeneralPath) shape).lineTo(313.849, 152.912);
        ((GeneralPath) shape).lineTo(313.849, 154.265);
        ((GeneralPath) shape).lineTo(313.385, 154.265);
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
        ((GeneralPath) shape).moveTo(318.387, 150.425);
        ((GeneralPath) shape).lineTo(318.387, 154.963);
        ((GeneralPath) shape).lineTo(317.875, 154.963);
        ((GeneralPath) shape).lineTo(317.875, 150.808);
        ((GeneralPath) shape).lineTo(316.717, 152.094);
        ((GeneralPath) shape).lineTo(316.396, 151.789);
        ((GeneralPath) shape).lineTo(317.649, 150.425);
        ((GeneralPath) shape).lineTo(318.387, 150.425);
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
        ((GeneralPath) shape).moveTo(378.564, 154.265);
        ((GeneralPath) shape).lineTo(378.564, 152.912);
        ((GeneralPath) shape).lineTo(377.228, 152.912);
        ((GeneralPath) shape).lineTo(377.228, 152.48);
        ((GeneralPath) shape).lineTo(378.564, 152.48);
        ((GeneralPath) shape).lineTo(378.564, 151.12);
        ((GeneralPath) shape).lineTo(379.029, 151.12);
        ((GeneralPath) shape).lineTo(379.029, 152.48);
        ((GeneralPath) shape).lineTo(380.367, 152.48);
        ((GeneralPath) shape).lineTo(380.367, 152.912);
        ((GeneralPath) shape).lineTo(379.029, 152.912);
        ((GeneralPath) shape).lineTo(379.029, 154.265);
        ((GeneralPath) shape).lineTo(378.564, 154.265);
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
        ((GeneralPath) shape).moveTo(384.509, 154.531);
        ((GeneralPath) shape).lineTo(384.509, 154.96301);
        ((GeneralPath) shape).lineTo(381.247, 154.96301);
        ((GeneralPath) shape).lineTo(381.247, 154.10901);
        ((GeneralPath) shape).curveTo(381.247, 153.63, 381.34, 153.31801,
                381.526, 153.16801);
        ((GeneralPath) shape).curveTo(381.712, 153.02101, 382.153, 152.90602,
                382.849, 152.82602);
        ((GeneralPath) shape).curveTo(383.407, 152.76402, 383.744, 152.68002,
                383.86, 152.57402);
        ((GeneralPath) shape).curveTo(383.97598, 152.46802, 384.034, 152.19202,
                384.034, 151.74602);
        ((GeneralPath) shape).curveTo(384.034, 151.35701, 383.97, 151.10301,
                383.84, 150.98701);
        ((GeneralPath) shape).curveTo(383.711, 150.87001, 383.429, 150.81302,
                382.992, 150.81302);
        ((GeneralPath) shape).curveTo(382.448, 150.81302, 382.104, 150.85902,
                381.962, 150.95403);
        ((GeneralPath) shape).curveTo(381.819, 151.04703, 381.74802, 151.27502,
                381.74802, 151.63402);
        ((GeneralPath) shape).lineTo(381.755, 151.97302);
        ((GeneralPath) shape).lineTo(381.254, 151.97302);
        ((GeneralPath) shape).lineTo(381.257, 151.73703);
        ((GeneralPath) shape).curveTo(381.257, 151.19502, 381.371, 150.83302,
                381.60098, 150.65202);
        ((GeneralPath) shape).curveTo(381.83, 150.47203, 382.288, 150.38103,
                382.97598, 150.38103);
        ((GeneralPath) shape).curveTo(383.58597, 150.38103, 383.99698,
                150.47803, 384.20697, 150.67403);
        ((GeneralPath) shape).curveTo(384.416, 150.86803, 384.52097, 151.24902,
                384.52097, 151.81403);
        ((GeneralPath) shape).curveTo(384.52097, 152.35603, 384.42297,
                152.72003, 384.22797, 152.90202);
        ((GeneralPath) shape).curveTo(384.03195, 153.08502, 383.60696,
                153.20802, 382.95297, 153.27303);
        ((GeneralPath) shape).curveTo(382.37897, 153.32903, 382.03497,
                153.40903, 381.92398, 153.51202);
        ((GeneralPath) shape).curveTo(381.81497, 153.61403, 381.758, 153.90303,
                381.758, 154.38002);
        ((GeneralPath) shape).lineTo(381.758, 154.53302);
        ((GeneralPath) shape).lineTo(384.509, 154.53302);
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
        stroke = new BasicStroke(0.973f, 0, 0, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(385.568, 156.181);
        ((GeneralPath) shape).curveTo(385.568, 156.912, 384.97598, 157.505,
                384.24298, 157.505);
        ((GeneralPath) shape).lineTo(377.51297, 157.505);
        ((GeneralPath) shape).curveTo(376.78198, 157.505, 376.18796, 156.912,
                376.18796, 156.181);
        ((GeneralPath) shape).lineTo(376.18796, 149.382);
        ((GeneralPath) shape).curveTo(376.18796, 148.65001, 376.78195, 148.057,
                377.51297, 148.057);
        ((GeneralPath) shape).lineTo(384.24298, 148.057);
        ((GeneralPath) shape).curveTo(384.97598, 148.057, 385.568, 148.65001,
                385.568, 149.382);
        ((GeneralPath) shape).lineTo(385.568, 156.181);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        stroke = new BasicStroke(0.973f, 0, 0, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(320.393, 156.181);
        ((GeneralPath) shape).curveTo(320.393, 156.912, 319.801, 157.505,
                319.07, 157.505);
        ((GeneralPath) shape).lineTo(312.337, 157.505);
        ((GeneralPath) shape).curveTo(311.607, 157.505, 311.014, 156.912,
                311.014, 156.181);
        ((GeneralPath) shape).lineTo(311.014, 149.382);
        ((GeneralPath) shape).curveTo(311.014, 148.65001, 311.607, 148.057,
                312.337, 148.057);
        ((GeneralPath) shape).lineTo(319.07, 148.057);
        ((GeneralPath) shape).curveTo(319.801, 148.057, 320.393, 148.65001,
                320.393, 149.382);
        ((GeneralPath) shape).lineTo(320.393, 156.181);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        stroke = new BasicStroke(0.973f, 0, 0, 4.0f, null, 0.0f);
        shape = new Line2D.Float(281.970001f, 128.406998f, 307.263000f,
                128.406998f);
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        stroke = new BasicStroke(0.973f, 0, 0, 4.0f, null, 0.0f);
        shape = new Line2D.Float(305.885986f, 140.813995f, 319.670013f,
                140.813995f);
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        stroke = new BasicStroke(0.973f, 0, 0, 4.0f, null, 0.0f);
        shape = new Line2D.Float(372.053986f, 140.813995f, 385.838989f,
                140.813995f);
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_317;
        g.setTransform(defaultTransform__0_317);
        g.setClip(clip__0_317);
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
