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
public class Armor_Head_5_Humanoid {
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
// _0_0_0 is ShapeNode
paint = new Color(255, 255, 255, 255);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(485.558, 87.631);
((GeneralPath)shape).curveTo(486.846, 87.631, 487.89102, 88.676994, 487.89102, 89.965996);
((GeneralPath)shape).curveTo(487.89102, 91.173, 486.846, 92.218994, 485.558, 92.218994);
((GeneralPath)shape).curveTo(484.35, 92.218994, 483.30402, 91.173, 483.30402, 89.965996);
((GeneralPath)shape).curveTo(483.304, 88.677, 484.35, 87.631, 485.558, 87.631);
((GeneralPath)shape).lineTo(485.558, 87.631);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(485.558, 87.631);
((GeneralPath)shape).curveTo(486.846, 87.631, 487.89102, 88.676994, 487.89102, 89.965996);
((GeneralPath)shape).curveTo(487.89102, 91.173, 486.846, 92.218994, 485.558, 92.218994);
((GeneralPath)shape).curveTo(484.35, 92.218994, 483.30402, 91.173, 483.30402, 89.965996);
((GeneralPath)shape).curveTo(483.304, 88.677, 484.35, 87.631, 485.558, 87.631);
((GeneralPath)shape).lineTo(485.558, 87.631);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0;
g.setTransform(defaultTransform__0_0_0);
g.setClip(clip__0_0_0);
float alpha__0_0_1 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_1 = g.getClip();
AffineTransform defaultTransform__0_0_1 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_1 is ShapeNode
paint = new Color(255, 255, 255, 255);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(497.306, 87.631);
((GeneralPath)shape).curveTo(498.596, 87.631, 499.641, 88.676994, 499.641, 89.965996);
((GeneralPath)shape).curveTo(499.641, 91.173, 498.59598, 92.218994, 497.306, 92.218994);
((GeneralPath)shape).curveTo(496.098, 92.218994, 495.054, 91.173, 495.054, 89.965996);
((GeneralPath)shape).curveTo(495.054, 88.677, 496.098, 87.631, 497.306, 87.631);
((GeneralPath)shape).lineTo(497.306, 87.631);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(497.306, 87.631);
((GeneralPath)shape).curveTo(498.596, 87.631, 499.641, 88.676994, 499.641, 89.965996);
((GeneralPath)shape).curveTo(499.641, 91.173, 498.59598, 92.218994, 497.306, 92.218994);
((GeneralPath)shape).curveTo(496.098, 92.218994, 495.054, 91.173, 495.054, 89.965996);
((GeneralPath)shape).curveTo(495.054, 88.677, 496.098, 87.631, 497.306, 87.631);
((GeneralPath)shape).lineTo(497.306, 87.631);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_1;
g.setTransform(defaultTransform__0_0_1);
g.setClip(clip__0_0_1);
float alpha__0_0_2 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_2 = g.getClip();
AffineTransform defaultTransform__0_0_2 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_2 is ShapeNode
paint = new Color(255, 255, 255, 255);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(485.558, 80.248);
((GeneralPath)shape).curveTo(486.846, 80.248, 487.89102, 81.294, 487.89102, 82.501);
((GeneralPath)shape).curveTo(487.89102, 83.789, 486.846, 84.755, 485.558, 84.755);
((GeneralPath)shape).curveTo(484.35, 84.755, 483.30402, 83.788994, 483.30402, 82.501);
((GeneralPath)shape).curveTo(483.304, 81.294, 484.35, 80.248, 485.558, 80.248);
((GeneralPath)shape).lineTo(485.558, 80.248);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(485.558, 80.248);
((GeneralPath)shape).curveTo(486.846, 80.248, 487.89102, 81.294, 487.89102, 82.501);
((GeneralPath)shape).curveTo(487.89102, 83.789, 486.846, 84.755, 485.558, 84.755);
((GeneralPath)shape).curveTo(484.35, 84.755, 483.30402, 83.788994, 483.30402, 82.501);
((GeneralPath)shape).curveTo(483.304, 81.294, 484.35, 80.248, 485.558, 80.248);
((GeneralPath)shape).lineTo(485.558, 80.248);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_2;
g.setTransform(defaultTransform__0_0_2);
g.setClip(clip__0_0_2);
float alpha__0_0_3 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_3 = g.getClip();
AffineTransform defaultTransform__0_0_3 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_3 is ShapeNode
paint = new Color(255, 255, 255, 255);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(497.306, 80.248);
((GeneralPath)shape).curveTo(498.596, 80.248, 499.641, 81.294, 499.641, 82.501);
((GeneralPath)shape).curveTo(499.641, 83.789, 498.59598, 84.755, 497.306, 84.755);
((GeneralPath)shape).curveTo(496.098, 84.755, 495.054, 83.788994, 495.054, 82.501);
((GeneralPath)shape).curveTo(495.054, 81.294, 496.098, 80.248, 497.306, 80.248);
((GeneralPath)shape).lineTo(497.306, 80.248);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(497.306, 80.248);
((GeneralPath)shape).curveTo(498.596, 80.248, 499.641, 81.294, 499.641, 82.501);
((GeneralPath)shape).curveTo(499.641, 83.789, 498.59598, 84.755, 497.306, 84.755);
((GeneralPath)shape).curveTo(496.098, 84.755, 495.054, 83.788994, 495.054, 82.501);
((GeneralPath)shape).curveTo(495.054, 81.294, 496.098, 80.248, 497.306, 80.248);
((GeneralPath)shape).lineTo(497.306, 80.248);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_3;
g.setTransform(defaultTransform__0_0_3);
g.setClip(clip__0_0_3);
float alpha__0_0_4 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_4 = g.getClip();
AffineTransform defaultTransform__0_0_4 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_4 is ShapeNode
paint = new Color(255, 255, 255, 255);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(491.432, 73.144);
((GeneralPath)shape).curveTo(492.72, 73.144, 493.76602, 74.189995, 493.76602, 75.397995);
((GeneralPath)shape).curveTo(493.76602, 76.686, 492.72003, 77.65099, 491.432, 77.65099);
((GeneralPath)shape).curveTo(490.225, 77.65099, 489.17902, 76.686, 489.17902, 75.397995);
((GeneralPath)shape).curveTo(489.179, 74.19, 490.225, 73.144, 491.432, 73.144);
((GeneralPath)shape).lineTo(491.432, 73.144);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(491.432, 73.144);
((GeneralPath)shape).curveTo(492.72, 73.144, 493.76602, 74.189995, 493.76602, 75.397995);
((GeneralPath)shape).curveTo(493.76602, 76.686, 492.72003, 77.65099, 491.432, 77.65099);
((GeneralPath)shape).curveTo(490.225, 77.65099, 489.17902, 76.686, 489.17902, 75.397995);
((GeneralPath)shape).curveTo(489.179, 74.19, 490.225, 73.144, 491.432, 73.144);
((GeneralPath)shape).lineTo(491.432, 73.144);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_4;
g.setTransform(defaultTransform__0_0_4);
g.setClip(clip__0_0_4);
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
        return 484;
    }

    /**
     * Returns the Y of the bounding box of the original SVG image.
     * 
     * @return The Y of the bounding box of the original SVG image.
     */
    public static int getOrigY() {
        return 73;
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

