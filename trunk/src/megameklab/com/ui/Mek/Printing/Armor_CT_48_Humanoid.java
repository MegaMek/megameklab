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

/**
 * This class has been automatically generated using <a
 * href="http://englishjavadrinker.blogspot.com/search/label/SVGRoundTrip"
 * >SVGRoundTrip</a>.
 */
public class Armor_CT_48_Humanoid {
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
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        g.setPaint(paint);
        g.fill(shape);
        paint = new Color(0, 0, 0, 255);
        stroke = new BasicStroke(2.0f, 0, 0, 4.0f, null, 0.0f);
        shape = new GeneralPath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        paint = new Color(0, 0, 0, 255);
        stroke = new BasicStroke(0.504f, 0, 0, 2.4142f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(480.648, 169.327);
        ((GeneralPath) shape).curveTo(482.039, 169.327, 483.06, 168.228,
                483.06, 166.912);
        ((GeneralPath) shape).curveTo(483.06, 165.597, 482.039, 164.498,
                480.648, 164.498);
        ((GeneralPath) shape).curveTo(479.33102, 164.498, 478.234, 165.597,
                478.234, 166.912);
        ((GeneralPath) shape).curveTo(478.234, 168.229, 479.331, 169.327,
                480.648, 169.327);
        ((GeneralPath) shape).lineTo(480.648, 169.327);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        paint = new Color(0, 0, 0, 255);
        stroke = new BasicStroke(0.504f, 0, 0, 2.4142f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(502.223, 169.327);
        ((GeneralPath) shape).curveTo(503.611, 169.327, 504.63498, 168.228,
                504.63498, 166.912);
        ((GeneralPath) shape).curveTo(504.63498, 165.597, 503.611, 164.498,
                502.223, 164.498);
        ((GeneralPath) shape).curveTo(500.906, 164.498, 499.806, 165.597,
                499.806, 166.912);
        ((GeneralPath) shape).curveTo(499.806, 168.229, 500.905, 169.327,
                502.223, 169.327);
        ((GeneralPath) shape).lineTo(502.223, 169.327);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        paint = new Color(0, 0, 0, 255);
        stroke = new BasicStroke(0.504f, 0, 0, 2.4142f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(491.436, 169.327);
        ((GeneralPath) shape).curveTo(492.824, 169.327, 493.848, 168.228,
                493.848, 166.912);
        ((GeneralPath) shape).curveTo(493.848, 165.597, 492.824, 164.498,
                491.436, 164.498);
        ((GeneralPath) shape).curveTo(490.11902, 164.498, 489.019, 165.597,
                489.019, 166.912);
        ((GeneralPath) shape).curveTo(489.019, 168.229, 490.118, 169.327,
                491.436, 169.327);
        ((GeneralPath) shape).lineTo(491.436, 169.327);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_2 is CompositeGraphicsNode
        float alpha__0_2_0 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_2_0 = g.getClip();
        AffineTransform defaultTransform__0_2_0 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_2_0 is ShapeNode
        paint = new Color(0, 0, 0, 255);
        stroke = new BasicStroke(0.504f, 0, 0, 2.4142f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(480.648, 186.037);
        ((GeneralPath) shape).curveTo(482.039, 186.037, 483.06, 184.938,
                483.06, 183.62201);
        ((GeneralPath) shape).curveTo(483.06, 182.307, 482.039, 181.20801,
                480.648, 181.20801);
        ((GeneralPath) shape).curveTo(479.33102, 181.20801, 478.234, 182.307,
                478.234, 183.62201);
        ((GeneralPath) shape).curveTo(478.234, 184.938, 479.331, 186.037,
                480.648, 186.037);
        ((GeneralPath) shape).lineTo(480.648, 186.037);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_2_0;
        g.setTransform(defaultTransform__0_2_0);
        g.setClip(clip__0_2_0);
        float alpha__0_2_1 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_2_1 = g.getClip();
        AffineTransform defaultTransform__0_2_1 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_2_1 is ShapeNode
        paint = new Color(0, 0, 0, 255);
        stroke = new BasicStroke(0.504f, 0, 0, 2.4142f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(502.223, 186.037);
        ((GeneralPath) shape).curveTo(503.611, 186.037, 504.63498, 184.938,
                504.63498, 183.62201);
        ((GeneralPath) shape).curveTo(504.63498, 182.307, 503.611, 181.20801,
                502.223, 181.20801);
        ((GeneralPath) shape).curveTo(500.906, 181.20801, 499.806, 182.307,
                499.806, 183.62201);
        ((GeneralPath) shape).curveTo(499.806, 184.938, 500.905, 186.037,
                502.223, 186.037);
        ((GeneralPath) shape).lineTo(502.223, 186.037);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_2_1;
        g.setTransform(defaultTransform__0_2_1);
        g.setClip(clip__0_2_1);
        float alpha__0_2_2 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_2_2 = g.getClip();
        AffineTransform defaultTransform__0_2_2 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_2_2 is ShapeNode
        paint = new Color(0, 0, 0, 255);
        stroke = new BasicStroke(0.504f, 0, 0, 2.4142f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(491.436, 186.037);
        ((GeneralPath) shape).curveTo(492.824, 186.037, 493.848, 184.938,
                493.848, 183.62201);
        ((GeneralPath) shape).curveTo(493.848, 182.307, 492.824, 181.20801,
                491.436, 181.20801);
        ((GeneralPath) shape).curveTo(490.11902, 181.20801, 489.019, 182.307,
                489.019, 183.62201);
        ((GeneralPath) shape).curveTo(489.019, 184.938, 490.118, 186.037,
                491.436, 186.037);
        ((GeneralPath) shape).lineTo(491.436, 186.037);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_2_2;
        g.setTransform(defaultTransform__0_2_2);
        g.setClip(clip__0_2_2);
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
        // _0_3_0 is ShapeNode
        paint = new Color(0, 0, 0, 255);
        stroke = new BasicStroke(0.504f, 0, 0, 2.4142f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(480.648, 177.682);
        ((GeneralPath) shape).curveTo(482.039, 177.682, 483.06, 176.58301,
                483.06, 175.26701);
        ((GeneralPath) shape).curveTo(483.06, 173.95102, 482.039, 172.85301,
                480.648, 172.85301);
        ((GeneralPath) shape).curveTo(479.33102, 172.85301, 478.234, 173.95102,
                478.234, 175.26701);
        ((GeneralPath) shape).curveTo(478.234, 176.58301, 479.331, 177.682,
                480.648, 177.682);
        ((GeneralPath) shape).lineTo(480.648, 177.682);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_3_0;
        g.setTransform(defaultTransform__0_3_0);
        g.setClip(clip__0_3_0);
        float alpha__0_3_1 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_3_1 = g.getClip();
        AffineTransform defaultTransform__0_3_1 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_3_1 is ShapeNode
        paint = new Color(0, 0, 0, 255);
        stroke = new BasicStroke(0.504f, 0, 0, 2.4142f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(502.223, 177.682);
        ((GeneralPath) shape).curveTo(503.611, 177.682, 504.63498, 176.58301,
                504.63498, 175.26701);
        ((GeneralPath) shape).curveTo(504.63498, 173.95102, 503.611, 172.85301,
                502.223, 172.85301);
        ((GeneralPath) shape).curveTo(500.906, 172.85301, 499.806, 173.95102,
                499.806, 175.26701);
        ((GeneralPath) shape).curveTo(499.806, 176.58301, 500.905, 177.682,
                502.223, 177.682);
        ((GeneralPath) shape).lineTo(502.223, 177.682);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_3_1;
        g.setTransform(defaultTransform__0_3_1);
        g.setClip(clip__0_3_1);
        float alpha__0_3_2 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_3_2 = g.getClip();
        AffineTransform defaultTransform__0_3_2 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_3_2 is ShapeNode
        paint = new Color(0, 0, 0, 255);
        stroke = new BasicStroke(0.504f, 0, 0, 2.4142f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(491.436, 177.682);
        ((GeneralPath) shape).curveTo(492.824, 177.682, 493.848, 176.58301,
                493.848, 175.26701);
        ((GeneralPath) shape).curveTo(493.848, 173.95102, 492.824, 172.85301,
                491.436, 172.85301);
        ((GeneralPath) shape).curveTo(490.11902, 172.85301, 489.019, 173.95102,
                489.019, 175.26701);
        ((GeneralPath) shape).curveTo(489.019, 176.58301, 490.118, 177.682,
                491.436, 177.682);
        ((GeneralPath) shape).lineTo(491.436, 177.682);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_3_2;
        g.setTransform(defaultTransform__0_3_2);
        g.setClip(clip__0_3_2);
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
        // _0_4_0 is ShapeNode
        paint = new Color(0, 0, 0, 255);
        stroke = new BasicStroke(0.504f, 0, 0, 2.4142f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(480.647, 144.263);
        ((GeneralPath) shape).curveTo(482.039, 144.263, 483.134, 143.165,
                483.134, 141.848);
        ((GeneralPath) shape).curveTo(483.134, 140.457, 482.038, 139.434,
                480.647, 139.434);
        ((GeneralPath) shape).curveTo(479.331, 139.434, 478.234, 140.457,
                478.234, 141.848);
        ((GeneralPath) shape).curveTo(478.234, 143.165, 479.331, 144.263,
                480.647, 144.263);
        ((GeneralPath) shape).lineTo(480.647, 144.263);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_4_0;
        g.setTransform(defaultTransform__0_4_0);
        g.setClip(clip__0_4_0);
        float alpha__0_4_1 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_4_1 = g.getClip();
        AffineTransform defaultTransform__0_4_1 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_4_1 is ShapeNode
        paint = new Color(0, 0, 0, 255);
        stroke = new BasicStroke(0.504f, 0, 0, 2.4142f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(502.219, 144.263);
        ((GeneralPath) shape).curveTo(503.611, 144.263, 504.71, 143.165,
                504.71, 141.848);
        ((GeneralPath) shape).curveTo(504.71, 140.457, 503.61, 139.434,
                502.219, 139.434);
        ((GeneralPath) shape).curveTo(500.90298, 139.434, 499.806, 140.457,
                499.806, 141.848);
        ((GeneralPath) shape).curveTo(499.806, 143.165, 500.902, 144.263,
                502.219, 144.263);
        ((GeneralPath) shape).lineTo(502.219, 144.263);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_4_1;
        g.setTransform(defaultTransform__0_4_1);
        g.setClip(clip__0_4_1);
        float alpha__0_4_2 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_4_2 = g.getClip();
        AffineTransform defaultTransform__0_4_2 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_4_2 is ShapeNode
        paint = new Color(0, 0, 0, 255);
        stroke = new BasicStroke(0.504f, 0, 0, 2.4142f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(491.435, 144.263);
        ((GeneralPath) shape).curveTo(492.824, 144.263, 493.923, 143.165,
                493.923, 141.848);
        ((GeneralPath) shape).curveTo(493.923, 140.457, 492.823, 139.434,
                491.435, 139.434);
        ((GeneralPath) shape).curveTo(490.119, 139.434, 489.019, 140.457,
                489.019, 141.848);
        ((GeneralPath) shape).curveTo(489.019, 143.165, 490.118, 144.263,
                491.435, 144.263);
        ((GeneralPath) shape).lineTo(491.435, 144.263);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_4_2;
        g.setTransform(defaultTransform__0_4_2);
        g.setClip(clip__0_4_2);
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
        paint = new Color(0, 0, 0, 255);
        stroke = new BasicStroke(0.504f, 0, 0, 2.4142f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(480.647, 135.908);
        ((GeneralPath) shape).curveTo(482.039, 135.908, 483.134, 134.81,
                483.134, 133.49301);
        ((GeneralPath) shape).curveTo(483.134, 132.102, 482.038, 131.07901,
                480.647, 131.07901);
        ((GeneralPath) shape).curveTo(479.331, 131.07901, 478.234, 132.102,
                478.234, 133.49301);
        ((GeneralPath) shape).curveTo(478.234, 134.811, 479.331, 135.908,
                480.647, 135.908);
        ((GeneralPath) shape).lineTo(480.647, 135.908);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        paint = new Color(0, 0, 0, 255);
        stroke = new BasicStroke(0.504f, 0, 0, 2.4142f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(502.219, 135.908);
        ((GeneralPath) shape).curveTo(503.611, 135.908, 504.71, 134.81, 504.71,
                133.49301);
        ((GeneralPath) shape).curveTo(504.71, 132.102, 503.61, 131.07901,
                502.219, 131.07901);
        ((GeneralPath) shape).curveTo(500.90298, 131.07901, 499.806, 132.102,
                499.806, 133.49301);
        ((GeneralPath) shape).curveTo(499.806, 134.811, 500.902, 135.908,
                502.219, 135.908);
        ((GeneralPath) shape).lineTo(502.219, 135.908);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        paint = new Color(0, 0, 0, 255);
        stroke = new BasicStroke(0.504f, 0, 0, 2.4142f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(491.435, 135.908);
        ((GeneralPath) shape).curveTo(492.824, 135.908, 493.923, 134.81,
                493.923, 133.49301);
        ((GeneralPath) shape).curveTo(493.923, 132.102, 492.823, 131.07901,
                491.435, 131.07901);
        ((GeneralPath) shape).curveTo(490.119, 131.07901, 489.019, 132.102,
                489.019, 133.49301);
        ((GeneralPath) shape).curveTo(489.019, 134.811, 490.118, 135.908,
                491.435, 135.908);
        ((GeneralPath) shape).lineTo(491.435, 135.908);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_5_2;
        g.setTransform(defaultTransform__0_5_2);
        g.setClip(clip__0_5_2);
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
        paint = new Color(0, 0, 0, 255);
        stroke = new BasicStroke(0.504f, 0, 0, 2.4142f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(480.647, 127.553);
        ((GeneralPath) shape).curveTo(482.039, 127.553, 483.134, 126.455,
                483.134, 125.138);
        ((GeneralPath) shape).curveTo(483.134, 123.747, 482.038, 122.724,
                480.647, 122.724);
        ((GeneralPath) shape).curveTo(479.331, 122.724, 478.234, 123.747,
                478.234, 125.138);
        ((GeneralPath) shape).curveTo(478.234, 126.456, 479.331, 127.553,
                480.647, 127.553);
        ((GeneralPath) shape).lineTo(480.647, 127.553);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        paint = new Color(0, 0, 0, 255);
        stroke = new BasicStroke(0.504f, 0, 0, 2.4142f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(502.219, 127.553);
        ((GeneralPath) shape).curveTo(503.611, 127.553, 504.71, 126.455,
                504.71, 125.138);
        ((GeneralPath) shape).curveTo(504.71, 123.747, 503.61, 122.724,
                502.219, 122.724);
        ((GeneralPath) shape).curveTo(500.90298, 122.724, 499.806, 123.747,
                499.806, 125.138);
        ((GeneralPath) shape).curveTo(499.806, 126.456, 500.902, 127.553,
                502.219, 127.553);
        ((GeneralPath) shape).lineTo(502.219, 127.553);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_6_1;
        g.setTransform(defaultTransform__0_6_1);
        g.setClip(clip__0_6_1);
        float alpha__0_6_2 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_6_2 = g.getClip();
        AffineTransform defaultTransform__0_6_2 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_6_2 is ShapeNode
        paint = new Color(0, 0, 0, 255);
        stroke = new BasicStroke(0.504f, 0, 0, 2.4142f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(491.435, 127.553);
        ((GeneralPath) shape).curveTo(492.824, 127.553, 493.923, 126.455,
                493.923, 125.138);
        ((GeneralPath) shape).curveTo(493.923, 123.747, 492.823, 122.724,
                491.435, 122.724);
        ((GeneralPath) shape).curveTo(490.119, 122.724, 489.019, 123.747,
                489.019, 125.138);
        ((GeneralPath) shape).curveTo(489.019, 126.456, 490.118, 127.553,
                491.435, 127.553);
        ((GeneralPath) shape).lineTo(491.435, 127.553);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_6_2;
        g.setTransform(defaultTransform__0_6_2);
        g.setClip(clip__0_6_2);
        origAlpha = alpha__0_6;
        g.setTransform(defaultTransform__0_6);
        g.setClip(clip__0_6);
        float alpha__0_7 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_7 = g.getClip();
        AffineTransform defaultTransform__0_7 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_7 is CompositeGraphicsNode
        float alpha__0_7_0 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_7_0 = g.getClip();
        AffineTransform defaultTransform__0_7_0 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_7_0 is ShapeNode
        paint = new Color(0, 0, 0, 255);
        stroke = new BasicStroke(0.504f, 0, 0, 2.4142f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(480.647, 152.618);
        ((GeneralPath) shape).curveTo(482.039, 152.618, 483.134, 151.51999,
                483.134, 150.203);
        ((GeneralPath) shape).curveTo(483.134, 148.812, 482.038, 147.789,
                480.647, 147.789);
        ((GeneralPath) shape).curveTo(479.331, 147.789, 478.234, 148.812,
                478.234, 150.203);
        ((GeneralPath) shape).curveTo(478.234, 151.52, 479.331, 152.618,
                480.647, 152.618);
        ((GeneralPath) shape).lineTo(480.647, 152.618);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_7_0;
        g.setTransform(defaultTransform__0_7_0);
        g.setClip(clip__0_7_0);
        float alpha__0_7_1 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_7_1 = g.getClip();
        AffineTransform defaultTransform__0_7_1 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_7_1 is ShapeNode
        paint = new Color(0, 0, 0, 255);
        stroke = new BasicStroke(0.504f, 0, 0, 2.4142f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(502.219, 152.618);
        ((GeneralPath) shape).curveTo(503.611, 152.618, 504.71, 151.51999,
                504.71, 150.203);
        ((GeneralPath) shape).curveTo(504.71, 148.812, 503.61, 147.789,
                502.219, 147.789);
        ((GeneralPath) shape).curveTo(500.90298, 147.789, 499.806, 148.812,
                499.806, 150.203);
        ((GeneralPath) shape).curveTo(499.806, 151.52, 500.902, 152.618,
                502.219, 152.618);
        ((GeneralPath) shape).lineTo(502.219, 152.618);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_7_1;
        g.setTransform(defaultTransform__0_7_1);
        g.setClip(clip__0_7_1);
        float alpha__0_7_2 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_7_2 = g.getClip();
        AffineTransform defaultTransform__0_7_2 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_7_2 is ShapeNode
        paint = new Color(0, 0, 0, 255);
        stroke = new BasicStroke(0.504f, 0, 0, 2.4142f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(491.435, 152.618);
        ((GeneralPath) shape).curveTo(492.824, 152.618, 493.923, 151.51999,
                493.923, 150.203);
        ((GeneralPath) shape).curveTo(493.923, 148.812, 492.823, 147.789,
                491.435, 147.789);
        ((GeneralPath) shape).curveTo(490.119, 147.789, 489.019, 148.812,
                489.019, 150.203);
        ((GeneralPath) shape).curveTo(489.019, 151.52, 490.118, 152.618,
                491.435, 152.618);
        ((GeneralPath) shape).lineTo(491.435, 152.618);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_7_2;
        g.setTransform(defaultTransform__0_7_2);
        g.setClip(clip__0_7_2);
        origAlpha = alpha__0_7;
        g.setTransform(defaultTransform__0_7);
        g.setClip(clip__0_7);
        float alpha__0_8 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_8 = g.getClip();
        AffineTransform defaultTransform__0_8 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_8 is CompositeGraphicsNode
        float alpha__0_8_0 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_8_0 = g.getClip();
        AffineTransform defaultTransform__0_8_0 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_8_0 is ShapeNode
        paint = new Color(0, 0, 0, 255);
        stroke = new BasicStroke(0.504f, 0, 0, 2.4142f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(480.647, 160.972);
        ((GeneralPath) shape).curveTo(482.039, 160.972, 483.134, 159.874,
                483.134, 158.557);
        ((GeneralPath) shape).curveTo(483.134, 157.166, 482.038, 156.143,
                480.647, 156.143);
        ((GeneralPath) shape).curveTo(479.331, 156.143, 478.234, 157.166,
                478.234, 158.557);
        ((GeneralPath) shape).curveTo(478.234, 159.875, 479.331, 160.972,
                480.647, 160.972);
        ((GeneralPath) shape).lineTo(480.647, 160.972);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_8_0;
        g.setTransform(defaultTransform__0_8_0);
        g.setClip(clip__0_8_0);
        float alpha__0_8_1 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_8_1 = g.getClip();
        AffineTransform defaultTransform__0_8_1 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_8_1 is ShapeNode
        paint = new Color(0, 0, 0, 255);
        stroke = new BasicStroke(0.504f, 0, 0, 2.4142f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(502.219, 160.972);
        ((GeneralPath) shape).curveTo(503.611, 160.972, 504.71, 159.874,
                504.71, 158.557);
        ((GeneralPath) shape).curveTo(504.71, 157.166, 503.61, 156.143,
                502.219, 156.143);
        ((GeneralPath) shape).curveTo(500.90298, 156.143, 499.806, 157.166,
                499.806, 158.557);
        ((GeneralPath) shape).curveTo(499.806, 159.875, 500.902, 160.972,
                502.219, 160.972);
        ((GeneralPath) shape).lineTo(502.219, 160.972);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_8_1;
        g.setTransform(defaultTransform__0_8_1);
        g.setClip(clip__0_8_1);
        float alpha__0_8_2 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_8_2 = g.getClip();
        AffineTransform defaultTransform__0_8_2 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_8_2 is ShapeNode
        paint = new Color(0, 0, 0, 255);
        stroke = new BasicStroke(0.504f, 0, 0, 2.4142f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(491.435, 160.972);
        ((GeneralPath) shape).curveTo(492.824, 160.972, 493.923, 159.874,
                493.923, 158.557);
        ((GeneralPath) shape).curveTo(493.923, 157.166, 492.823, 156.143,
                491.435, 156.143);
        ((GeneralPath) shape).curveTo(490.119, 156.143, 489.019, 157.166,
                489.019, 158.557);
        ((GeneralPath) shape).curveTo(489.019, 159.875, 490.118, 160.972,
                491.435, 160.972);
        ((GeneralPath) shape).lineTo(491.435, 160.972);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_8_2;
        g.setTransform(defaultTransform__0_8_2);
        g.setClip(clip__0_8_2);
        origAlpha = alpha__0_8;
        g.setTransform(defaultTransform__0_8);
        g.setClip(clip__0_8);
        float alpha__0_9 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_9 = g.getClip();
        AffineTransform defaultTransform__0_9 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_9 is CompositeGraphicsNode
        float alpha__0_9_0 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_9_0 = g.getClip();
        AffineTransform defaultTransform__0_9_0 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_9_0 is ShapeNode
        paint = new Color(0, 0, 0, 255);
        stroke = new BasicStroke(0.504f, 0, 0, 2.4142f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(496.826, 123.376);
        ((GeneralPath) shape).curveTo(498.14297, 123.376, 499.24, 122.278,
                499.24, 120.962);
        ((GeneralPath) shape).curveTo(499.24, 119.645, 498.143, 118.547,
                496.826, 118.547);
        ((GeneralPath) shape).curveTo(495.50998, 118.547, 494.414, 119.645,
                494.414, 120.962);
        ((GeneralPath) shape).curveTo(494.414, 122.278, 495.51, 123.376,
                496.826, 123.376);
        ((GeneralPath) shape).lineTo(496.826, 123.376);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_9_0;
        g.setTransform(defaultTransform__0_9_0);
        g.setClip(clip__0_9_0);
        float alpha__0_9_1 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_9_1 = g.getClip();
        AffineTransform defaultTransform__0_9_1 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_9_1 is ShapeNode
        paint = new Color(0, 0, 0, 255);
        stroke = new BasicStroke(0.504f, 0, 0, 2.4142f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(486.039, 123.376);
        ((GeneralPath) shape).curveTo(487.359, 123.376, 488.456, 122.278,
                488.456, 120.962);
        ((GeneralPath) shape).curveTo(488.456, 119.645, 487.359, 118.547,
                486.039, 118.547);
        ((GeneralPath) shape).curveTo(484.723, 118.547, 483.627, 119.645,
                483.627, 120.962);
        ((GeneralPath) shape).curveTo(483.627, 122.278, 484.723, 123.376,
                486.039, 123.376);
        ((GeneralPath) shape).lineTo(486.039, 123.376);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_9_1;
        g.setTransform(defaultTransform__0_9_1);
        g.setClip(clip__0_9_1);
        origAlpha = alpha__0_9;
        g.setTransform(defaultTransform__0_9);
        g.setClip(clip__0_9);
        float alpha__0_10 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_10 = g.getClip();
        AffineTransform defaultTransform__0_10 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_10 is CompositeGraphicsNode
        float alpha__0_10_0 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_10_0 = g.getClip();
        AffineTransform defaultTransform__0_10_0 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_10_0 is ShapeNode
        paint = new Color(0, 0, 0, 255);
        stroke = new BasicStroke(0.504f, 0, 0, 2.4142f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(496.826, 115.021);
        ((GeneralPath) shape).curveTo(498.14297, 115.021, 499.24, 113.923004,
                499.24, 112.607);
        ((GeneralPath) shape).curveTo(499.24, 111.29, 498.143, 110.192,
                496.826, 110.192);
        ((GeneralPath) shape).curveTo(495.50998, 110.192, 494.414, 111.29,
                494.414, 112.607);
        ((GeneralPath) shape).curveTo(494.414, 113.923, 495.51, 115.021,
                496.826, 115.021);
        ((GeneralPath) shape).lineTo(496.826, 115.021);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_10_0;
        g.setTransform(defaultTransform__0_10_0);
        g.setClip(clip__0_10_0);
        float alpha__0_10_1 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_10_1 = g.getClip();
        AffineTransform defaultTransform__0_10_1 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_10_1 is ShapeNode
        paint = new Color(0, 0, 0, 255);
        stroke = new BasicStroke(0.504f, 0, 0, 2.4142f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(486.039, 115.021);
        ((GeneralPath) shape).curveTo(487.359, 115.021, 488.456, 113.923004,
                488.456, 112.607);
        ((GeneralPath) shape).curveTo(488.456, 111.29, 487.359, 110.192,
                486.039, 110.192);
        ((GeneralPath) shape).curveTo(484.723, 110.192, 483.627, 111.29,
                483.627, 112.607);
        ((GeneralPath) shape).curveTo(483.627, 113.923, 484.723, 115.021,
                486.039, 115.021);
        ((GeneralPath) shape).lineTo(486.039, 115.021);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_10_1;
        g.setTransform(defaultTransform__0_10_1);
        g.setClip(clip__0_10_1);
        origAlpha = alpha__0_10;
        g.setTransform(defaultTransform__0_10);
        g.setClip(clip__0_10);
        float alpha__0_11 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_11 = g.getClip();
        AffineTransform defaultTransform__0_11 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_11 is CompositeGraphicsNode
        float alpha__0_11_0 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_11_0 = g.getClip();
        AffineTransform defaultTransform__0_11_0 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_11_0 is ShapeNode
        paint = new Color(0, 0, 0, 255);
        stroke = new BasicStroke(0.504f, 0, 0, 2.4142f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(496.826, 140.085);
        ((GeneralPath) shape).curveTo(498.14297, 140.085, 499.24, 138.987,
                499.24, 137.67001);
        ((GeneralPath) shape).curveTo(499.24, 136.35402, 498.143, 135.25601,
                496.826, 135.25601);
        ((GeneralPath) shape).curveTo(495.50998, 135.25601, 494.414, 136.35402,
                494.414, 137.67001);
        ((GeneralPath) shape).curveTo(494.414, 138.988, 495.51, 140.085,
                496.826, 140.085);
        ((GeneralPath) shape).lineTo(496.826, 140.085);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_11_0;
        g.setTransform(defaultTransform__0_11_0);
        g.setClip(clip__0_11_0);
        float alpha__0_11_1 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_11_1 = g.getClip();
        AffineTransform defaultTransform__0_11_1 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_11_1 is ShapeNode
        paint = new Color(0, 0, 0, 255);
        stroke = new BasicStroke(0.504f, 0, 0, 2.4142f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(486.039, 140.085);
        ((GeneralPath) shape).curveTo(487.359, 140.085, 488.456, 138.987,
                488.456, 137.67001);
        ((GeneralPath) shape).curveTo(488.456, 136.35402, 487.359, 135.25601,
                486.039, 135.25601);
        ((GeneralPath) shape).curveTo(484.723, 135.25601, 483.627, 136.35402,
                483.627, 137.67001);
        ((GeneralPath) shape).curveTo(483.627, 138.988, 484.723, 140.085,
                486.039, 140.085);
        ((GeneralPath) shape).lineTo(486.039, 140.085);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_11_1;
        g.setTransform(defaultTransform__0_11_1);
        g.setClip(clip__0_11_1);
        origAlpha = alpha__0_11;
        g.setTransform(defaultTransform__0_11);
        g.setClip(clip__0_11);
        float alpha__0_12 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_12 = g.getClip();
        AffineTransform defaultTransform__0_12 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_12 is CompositeGraphicsNode
        float alpha__0_12_0 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_12_0 = g.getClip();
        AffineTransform defaultTransform__0_12_0 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_12_0 is ShapeNode
        paint = new Color(0, 0, 0, 255);
        stroke = new BasicStroke(0.504f, 0, 0, 2.4142f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(496.826, 131.73);
        ((GeneralPath) shape).curveTo(498.14297, 131.73, 499.24, 130.63199,
                499.24, 129.315);
        ((GeneralPath) shape).curveTo(499.24, 127.999, 498.143, 126.901,
                496.826, 126.901);
        ((GeneralPath) shape).curveTo(495.50998, 126.901, 494.414, 127.999,
                494.414, 129.315);
        ((GeneralPath) shape).curveTo(494.414, 130.633, 495.51, 131.73,
                496.826, 131.73);
        ((GeneralPath) shape).lineTo(496.826, 131.73);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_12_0;
        g.setTransform(defaultTransform__0_12_0);
        g.setClip(clip__0_12_0);
        float alpha__0_12_1 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_12_1 = g.getClip();
        AffineTransform defaultTransform__0_12_1 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_12_1 is ShapeNode
        paint = new Color(0, 0, 0, 255);
        stroke = new BasicStroke(0.504f, 0, 0, 2.4142f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(486.039, 131.73);
        ((GeneralPath) shape).curveTo(487.359, 131.73, 488.456, 130.63199,
                488.456, 129.315);
        ((GeneralPath) shape).curveTo(488.456, 127.999, 487.359, 126.901,
                486.039, 126.901);
        ((GeneralPath) shape).curveTo(484.723, 126.901, 483.627, 127.999,
                483.627, 129.315);
        ((GeneralPath) shape).curveTo(483.627, 130.633, 484.723, 131.73,
                486.039, 131.73);
        ((GeneralPath) shape).lineTo(486.039, 131.73);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_12_1;
        g.setTransform(defaultTransform__0_12_1);
        g.setClip(clip__0_12_1);
        origAlpha = alpha__0_12;
        g.setTransform(defaultTransform__0_12);
        g.setClip(clip__0_12);
        float alpha__0_13 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_13 = g.getClip();
        AffineTransform defaultTransform__0_13 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_13 is CompositeGraphicsNode
        float alpha__0_13_0 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_13_0 = g.getClip();
        AffineTransform defaultTransform__0_13_0 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_13_0 is ShapeNode
        paint = new Color(0, 0, 0, 255);
        stroke = new BasicStroke(0.504f, 0, 0, 2.4142f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(496.826, 181.859);
        ((GeneralPath) shape).curveTo(498.14297, 181.859, 499.24, 180.76099,
                499.24, 179.44499);
        ((GeneralPath) shape).curveTo(499.24, 178.12799, 498.143, 177.03,
                496.826, 177.03);
        ((GeneralPath) shape).curveTo(495.50998, 177.03, 494.414, 178.128,
                494.414, 179.44499);
        ((GeneralPath) shape).curveTo(494.414, 180.762, 495.51, 181.859,
                496.826, 181.859);
        ((GeneralPath) shape).lineTo(496.826, 181.859);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_13_0;
        g.setTransform(defaultTransform__0_13_0);
        g.setClip(clip__0_13_0);
        float alpha__0_13_1 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_13_1 = g.getClip();
        AffineTransform defaultTransform__0_13_1 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_13_1 is ShapeNode
        paint = new Color(0, 0, 0, 255);
        stroke = new BasicStroke(0.504f, 0, 0, 2.4142f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(486.039, 181.859);
        ((GeneralPath) shape).curveTo(487.359, 181.859, 488.456, 180.76099,
                488.456, 179.44499);
        ((GeneralPath) shape).curveTo(488.456, 178.12799, 487.359, 177.03,
                486.039, 177.03);
        ((GeneralPath) shape).curveTo(484.723, 177.03, 483.627, 178.128,
                483.627, 179.44499);
        ((GeneralPath) shape).curveTo(483.627, 180.762, 484.723, 181.859,
                486.039, 181.859);
        ((GeneralPath) shape).lineTo(486.039, 181.859);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_13_1;
        g.setTransform(defaultTransform__0_13_1);
        g.setClip(clip__0_13_1);
        origAlpha = alpha__0_13;
        g.setTransform(defaultTransform__0_13);
        g.setClip(clip__0_13);
        float alpha__0_14 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_14 = g.getClip();
        AffineTransform defaultTransform__0_14 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_14 is CompositeGraphicsNode
        float alpha__0_14_0 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_14_0 = g.getClip();
        AffineTransform defaultTransform__0_14_0 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_14_0 is ShapeNode
        paint = new Color(0, 0, 0, 255);
        stroke = new BasicStroke(0.504f, 0, 0, 2.4142f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(496.826, 173.504);
        ((GeneralPath) shape).curveTo(498.14297, 173.504, 499.24, 172.40599,
                499.24, 171.09);
        ((GeneralPath) shape).curveTo(499.24, 169.773, 498.143, 168.675,
                496.826, 168.675);
        ((GeneralPath) shape).curveTo(495.50998, 168.675, 494.414, 169.77301,
                494.414, 171.09);
        ((GeneralPath) shape).curveTo(494.414, 172.407, 495.51, 173.504,
                496.826, 173.504);
        ((GeneralPath) shape).lineTo(496.826, 173.504);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_14_0;
        g.setTransform(defaultTransform__0_14_0);
        g.setClip(clip__0_14_0);
        float alpha__0_14_1 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_14_1 = g.getClip();
        AffineTransform defaultTransform__0_14_1 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_14_1 is ShapeNode
        paint = new Color(0, 0, 0, 255);
        stroke = new BasicStroke(0.504f, 0, 0, 2.4142f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(486.039, 173.504);
        ((GeneralPath) shape).curveTo(487.359, 173.504, 488.456, 172.40599,
                488.456, 171.09);
        ((GeneralPath) shape).curveTo(488.456, 169.773, 487.359, 168.675,
                486.039, 168.675);
        ((GeneralPath) shape).curveTo(484.723, 168.675, 483.627, 169.77301,
                483.627, 171.09);
        ((GeneralPath) shape).curveTo(483.627, 172.407, 484.723, 173.504,
                486.039, 173.504);
        ((GeneralPath) shape).lineTo(486.039, 173.504);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_14_1;
        g.setTransform(defaultTransform__0_14_1);
        g.setClip(clip__0_14_1);
        origAlpha = alpha__0_14;
        g.setTransform(defaultTransform__0_14);
        g.setClip(clip__0_14);
        float alpha__0_15 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_15 = g.getClip();
        AffineTransform defaultTransform__0_15 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_15 is CompositeGraphicsNode
        float alpha__0_15_0 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_15_0 = g.getClip();
        AffineTransform defaultTransform__0_15_0 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_15_0 is ShapeNode
        paint = new Color(0, 0, 0, 255);
        stroke = new BasicStroke(0.504f, 0, 0, 2.4142f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(496.826, 165.149);
        ((GeneralPath) shape).curveTo(498.14297, 165.149, 499.24, 164.051,
                499.24, 162.73401);
        ((GeneralPath) shape).curveTo(499.24, 161.41801, 498.143, 160.32,
                496.826, 160.32);
        ((GeneralPath) shape).curveTo(495.50998, 160.32, 494.414, 161.41801,
                494.414, 162.73401);
        ((GeneralPath) shape).curveTo(494.414, 164.052, 495.51, 165.149,
                496.826, 165.149);
        ((GeneralPath) shape).lineTo(496.826, 165.149);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_15_0;
        g.setTransform(defaultTransform__0_15_0);
        g.setClip(clip__0_15_0);
        float alpha__0_15_1 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_15_1 = g.getClip();
        AffineTransform defaultTransform__0_15_1 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_15_1 is ShapeNode
        paint = new Color(0, 0, 0, 255);
        stroke = new BasicStroke(0.504f, 0, 0, 2.4142f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(486.039, 165.149);
        ((GeneralPath) shape).curveTo(487.359, 165.149, 488.456, 164.051,
                488.456, 162.73401);
        ((GeneralPath) shape).curveTo(488.456, 161.41801, 487.359, 160.32,
                486.039, 160.32);
        ((GeneralPath) shape).curveTo(484.723, 160.32, 483.627, 161.41801,
                483.627, 162.73401);
        ((GeneralPath) shape).curveTo(483.627, 164.052, 484.723, 165.149,
                486.039, 165.149);
        ((GeneralPath) shape).lineTo(486.039, 165.149);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_15_1;
        g.setTransform(defaultTransform__0_15_1);
        g.setClip(clip__0_15_1);
        origAlpha = alpha__0_15;
        g.setTransform(defaultTransform__0_15);
        g.setClip(clip__0_15);
        float alpha__0_16 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_16 = g.getClip();
        AffineTransform defaultTransform__0_16 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_16 is CompositeGraphicsNode
        float alpha__0_16_0 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_16_0 = g.getClip();
        AffineTransform defaultTransform__0_16_0 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_16_0 is ShapeNode
        paint = new Color(0, 0, 0, 255);
        stroke = new BasicStroke(0.504f, 0, 0, 2.4142f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(496.826, 156.795);
        ((GeneralPath) shape).curveTo(498.14297, 156.795, 499.24, 155.69699,
                499.24, 154.38);
        ((GeneralPath) shape).curveTo(499.24, 153.06401, 498.143, 151.966,
                496.826, 151.966);
        ((GeneralPath) shape).curveTo(495.50998, 151.966, 494.414, 153.06401,
                494.414, 154.38);
        ((GeneralPath) shape).curveTo(494.414, 155.697, 495.51, 156.795,
                496.826, 156.795);
        ((GeneralPath) shape).lineTo(496.826, 156.795);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_16_0;
        g.setTransform(defaultTransform__0_16_0);
        g.setClip(clip__0_16_0);
        float alpha__0_16_1 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_16_1 = g.getClip();
        AffineTransform defaultTransform__0_16_1 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_16_1 is ShapeNode
        paint = new Color(0, 0, 0, 255);
        stroke = new BasicStroke(0.504f, 0, 0, 2.4142f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(486.039, 156.795);
        ((GeneralPath) shape).curveTo(487.359, 156.795, 488.456, 155.69699,
                488.456, 154.38);
        ((GeneralPath) shape).curveTo(488.456, 153.06401, 487.359, 151.966,
                486.039, 151.966);
        ((GeneralPath) shape).curveTo(484.723, 151.966, 483.627, 153.06401,
                483.627, 154.38);
        ((GeneralPath) shape).curveTo(483.627, 155.697, 484.723, 156.795,
                486.039, 156.795);
        ((GeneralPath) shape).lineTo(486.039, 156.795);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_16_1;
        g.setTransform(defaultTransform__0_16_1);
        g.setClip(clip__0_16_1);
        origAlpha = alpha__0_16;
        g.setTransform(defaultTransform__0_16);
        g.setClip(clip__0_16);
        float alpha__0_17 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_17 = g.getClip();
        AffineTransform defaultTransform__0_17 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_17 is CompositeGraphicsNode
        float alpha__0_17_0 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_17_0 = g.getClip();
        AffineTransform defaultTransform__0_17_0 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_17_0 is ShapeNode
        paint = new Color(0, 0, 0, 255);
        stroke = new BasicStroke(0.504f, 0, 0, 2.4142f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(496.826, 148.44);
        ((GeneralPath) shape).curveTo(498.14297, 148.44, 499.24, 147.342,
                499.24, 146.02501);
        ((GeneralPath) shape).curveTo(499.24, 144.70901, 498.143, 143.61101,
                496.826, 143.61101);
        ((GeneralPath) shape).curveTo(495.50998, 143.61101, 494.414, 144.70901,
                494.414, 146.02501);
        ((GeneralPath) shape).curveTo(494.414, 147.342, 495.51, 148.44,
                496.826, 148.44);
        ((GeneralPath) shape).lineTo(496.826, 148.44);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_17_0;
        g.setTransform(defaultTransform__0_17_0);
        g.setClip(clip__0_17_0);
        float alpha__0_17_1 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_17_1 = g.getClip();
        AffineTransform defaultTransform__0_17_1 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_17_1 is ShapeNode
        paint = new Color(0, 0, 0, 255);
        stroke = new BasicStroke(0.504f, 0, 0, 2.4142f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(486.039, 148.44);
        ((GeneralPath) shape).curveTo(487.359, 148.44, 488.456, 147.342,
                488.456, 146.02501);
        ((GeneralPath) shape).curveTo(488.456, 144.70901, 487.359, 143.61101,
                486.039, 143.61101);
        ((GeneralPath) shape).curveTo(484.723, 143.61101, 483.627, 144.70901,
                483.627, 146.02501);
        ((GeneralPath) shape).curveTo(483.627, 147.342, 484.723, 148.44,
                486.039, 148.44);
        ((GeneralPath) shape).lineTo(486.039, 148.44);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_17_1;
        g.setTransform(defaultTransform__0_17_1);
        g.setClip(clip__0_17_1);
        origAlpha = alpha__0_17;
        g.setTransform(defaultTransform__0_17);
        g.setClip(clip__0_17);
        float alpha__0_18 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_18 = g.getClip();
        AffineTransform defaultTransform__0_18 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_18 is CompositeGraphicsNode
        float alpha__0_18_0 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_18_0 = g.getClip();
        AffineTransform defaultTransform__0_18_0 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_18_0 is ShapeNode
        paint = new Color(0, 0, 0, 255);
        stroke = new BasicStroke(0.504f, 0, 0, 2.4142f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(480.647, 119.198);
        ((GeneralPath) shape).curveTo(482.039, 119.198, 483.134, 118.1,
                483.134, 116.783);
        ((GeneralPath) shape).curveTo(483.134, 115.392, 482.038, 114.368996,
                480.647, 114.368996);
        ((GeneralPath) shape).curveTo(479.331, 114.368996, 478.234, 115.392,
                478.234, 116.783);
        ((GeneralPath) shape).curveTo(478.234, 118.101, 479.331, 119.198,
                480.647, 119.198);
        ((GeneralPath) shape).lineTo(480.647, 119.198);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_18_0;
        g.setTransform(defaultTransform__0_18_0);
        g.setClip(clip__0_18_0);
        float alpha__0_18_1 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_18_1 = g.getClip();
        AffineTransform defaultTransform__0_18_1 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_18_1 is ShapeNode
        paint = new Color(0, 0, 0, 255);
        stroke = new BasicStroke(0.504f, 0, 0, 2.4142f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(502.219, 119.198);
        ((GeneralPath) shape).curveTo(503.611, 119.198, 504.71, 118.1, 504.71,
                116.783);
        ((GeneralPath) shape).curveTo(504.71, 115.392, 503.61, 114.368996,
                502.219, 114.368996);
        ((GeneralPath) shape).curveTo(500.90298, 114.368996, 499.806, 115.392,
                499.806, 116.783);
        ((GeneralPath) shape).curveTo(499.806, 118.101, 500.902, 119.198,
                502.219, 119.198);
        ((GeneralPath) shape).lineTo(502.219, 119.198);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_18_1;
        g.setTransform(defaultTransform__0_18_1);
        g.setClip(clip__0_18_1);
        float alpha__0_18_2 = origAlpha;
        origAlpha = origAlpha * 1.0f;
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_18_2 = g.getClip();
        AffineTransform defaultTransform__0_18_2 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
        // _0_18_2 is ShapeNode
        paint = new Color(0, 0, 0, 255);
        stroke = new BasicStroke(0.504f, 0, 0, 2.4142f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(491.435, 119.198);
        ((GeneralPath) shape).curveTo(492.824, 119.198, 493.923, 118.1,
                493.923, 116.783);
        ((GeneralPath) shape).curveTo(493.923, 115.392, 492.823, 114.368996,
                491.435, 114.368996);
        ((GeneralPath) shape).curveTo(490.119, 114.368996, 489.019, 115.392,
                489.019, 116.783);
        ((GeneralPath) shape).curveTo(489.019, 118.101, 490.118, 119.198,
                491.435, 119.198);
        ((GeneralPath) shape).lineTo(491.435, 119.198);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_18_2;
        g.setTransform(defaultTransform__0_18_2);
        g.setClip(clip__0_18_2);
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
        paint = new Color(0, 0, 0, 255);
        stroke = new BasicStroke(0.504f, 0, 0, 2.4142f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(480.647, 110.844);
        ((GeneralPath) shape).curveTo(482.039, 110.844, 483.134, 109.746,
                483.134, 108.429);
        ((GeneralPath) shape).curveTo(483.134, 107.038, 482.038, 106.015,
                480.647, 106.015);
        ((GeneralPath) shape).curveTo(479.331, 106.015, 478.234, 107.038,
                478.234, 108.429);
        ((GeneralPath) shape).curveTo(478.234, 109.746, 479.331, 110.844,
                480.647, 110.844);
        ((GeneralPath) shape).lineTo(480.647, 110.844);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        paint = new Color(0, 0, 0, 255);
        stroke = new BasicStroke(0.504f, 0, 0, 2.4142f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(502.219, 110.844);
        ((GeneralPath) shape).curveTo(503.611, 110.844, 504.71, 109.746,
                504.71, 108.429);
        ((GeneralPath) shape).curveTo(504.71, 107.038, 503.61, 106.015,
                502.219, 106.015);
        ((GeneralPath) shape).curveTo(500.90298, 106.015, 499.806, 107.038,
                499.806, 108.429);
        ((GeneralPath) shape).curveTo(499.806, 109.746, 500.902, 110.844,
                502.219, 110.844);
        ((GeneralPath) shape).lineTo(502.219, 110.844);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
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
        paint = new Color(0, 0, 0, 255);
        stroke = new BasicStroke(0.504f, 0, 0, 2.4142f, null, 0.0f);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(491.435, 110.844);
        ((GeneralPath) shape).curveTo(492.824, 110.844, 493.923, 109.746,
                493.923, 108.429);
        ((GeneralPath) shape).curveTo(493.923, 107.038, 492.823, 106.015,
                491.435, 106.015);
        ((GeneralPath) shape).curveTo(490.119, 106.015, 489.019, 107.038,
                489.019, 108.429);
        ((GeneralPath) shape).curveTo(489.019, 109.746, 490.118, 110.844,
                491.435, 110.844);
        ((GeneralPath) shape).lineTo(491.435, 110.844);
        ((GeneralPath) shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_21;
        g.setTransform(defaultTransform__0_21);
        g.setClip(clip__0_21);
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
