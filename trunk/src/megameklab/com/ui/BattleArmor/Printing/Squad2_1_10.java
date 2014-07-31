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

package megameklab.com.ui.BattleArmor.Printing;

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
 * href="http://englishjavadrinker.blogspot.com/search/label/SVGRoundTrip">SVGRoundTrip</a>.
 */
public class Squad2_1_10 {
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
// _0_0 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.5057f,0,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(265.196, 235.428);
((GeneralPath)shape).curveTo(265.196, 236.872, 264.02402, 238.04599, 262.57602, 238.04599);
((GeneralPath)shape).curveTo(261.12903, 238.04599, 259.958, 236.872, 259.958, 235.428);
((GeneralPath)shape).curveTo(259.958, 233.98, 261.129, 232.80899, 262.57602, 232.80899);
((GeneralPath)shape).curveTo(264.023, 232.809, 265.196, 233.98, 265.196, 235.428);
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
// _0_1 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.5057f,0,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(272.196, 235.428);
((GeneralPath)shape).curveTo(272.196, 236.872, 271.02402, 238.04599, 269.57602, 238.04599);
((GeneralPath)shape).curveTo(268.12903, 238.04599, 266.958, 236.872, 266.958, 235.428);
((GeneralPath)shape).curveTo(266.958, 233.98, 268.129, 232.80899, 269.57602, 232.80899);
((GeneralPath)shape).curveTo(271.023, 232.809, 272.196, 233.98, 272.196, 235.428);
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
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.5057f,0,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(279.196, 235.428);
((GeneralPath)shape).curveTo(279.196, 236.872, 278.02402, 238.04599, 276.57602, 238.04599);
((GeneralPath)shape).curveTo(275.12903, 238.04599, 273.958, 236.872, 273.958, 235.428);
((GeneralPath)shape).curveTo(273.958, 233.98, 275.129, 232.80899, 276.57602, 232.80899);
((GeneralPath)shape).curveTo(278.023, 232.809, 279.196, 233.98, 279.196, 235.428);
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
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.5057f,0,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(286.196, 235.428);
((GeneralPath)shape).curveTo(286.196, 236.872, 285.02402, 238.04599, 283.57602, 238.04599);
((GeneralPath)shape).curveTo(282.12903, 238.04599, 280.958, 236.872, 280.958, 235.428);
((GeneralPath)shape).curveTo(280.958, 233.98, 282.129, 232.80899, 283.57602, 232.80899);
((GeneralPath)shape).curveTo(285.023, 232.809, 286.196, 233.98, 286.196, 235.428);
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
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
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.5057f,0,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(293.196, 235.428);
((GeneralPath)shape).curveTo(293.196, 236.872, 292.02402, 238.04599, 290.57602, 238.04599);
((GeneralPath)shape).curveTo(289.12903, 238.04599, 287.958, 236.872, 287.958, 235.428);
((GeneralPath)shape).curveTo(287.958, 233.98, 289.129, 232.80899, 290.57602, 232.80899);
((GeneralPath)shape).curveTo(292.023, 232.809, 293.196, 233.98, 293.196, 235.428);
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
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.5057f,0,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(300.196, 235.428);
((GeneralPath)shape).curveTo(300.196, 236.872, 299.02402, 238.04599, 297.57602, 238.04599);
((GeneralPath)shape).curveTo(296.12903, 238.04599, 294.958, 236.872, 294.958, 235.428);
((GeneralPath)shape).curveTo(294.958, 233.98, 296.129, 232.80899, 297.57602, 232.80899);
((GeneralPath)shape).curveTo(299.023, 232.809, 300.196, 233.98, 300.196, 235.428);
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
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
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.5057f,0,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(307.195, 235.428);
((GeneralPath)shape).curveTo(307.195, 236.872, 306.023, 238.04599, 304.57602, 238.04599);
((GeneralPath)shape).curveTo(303.12903, 238.04599, 301.958, 236.872, 301.958, 235.428);
((GeneralPath)shape).curveTo(301.958, 233.98, 303.129, 232.80899, 304.57602, 232.80899);
((GeneralPath)shape).curveTo(306.023, 232.809, 307.195, 233.98, 307.195, 235.428);
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
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
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.5057f,0,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(314.195, 235.428);
((GeneralPath)shape).curveTo(314.195, 236.872, 313.023, 238.04599, 311.57602, 238.04599);
((GeneralPath)shape).curveTo(310.12903, 238.04599, 308.958, 236.872, 308.958, 235.428);
((GeneralPath)shape).curveTo(308.958, 233.98, 310.129, 232.80899, 311.57602, 232.80899);
((GeneralPath)shape).curveTo(313.02304, 232.80899, 314.195, 233.98, 314.195, 235.428);
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
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
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.5057f,0,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(321.195, 235.428);
((GeneralPath)shape).curveTo(321.195, 236.872, 320.023, 238.04599, 318.57602, 238.04599);
((GeneralPath)shape).curveTo(317.12903, 238.04599, 315.958, 236.872, 315.958, 235.428);
((GeneralPath)shape).curveTo(315.958, 233.98, 317.129, 232.80899, 318.57602, 232.80899);
((GeneralPath)shape).curveTo(320.02304, 232.80899, 321.195, 233.98, 321.195, 235.428);
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
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
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.5057f,0,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(328.195, 235.428);
((GeneralPath)shape).curveTo(328.195, 236.872, 327.023, 238.04599, 325.57602, 238.04599);
((GeneralPath)shape).curveTo(324.12903, 238.04599, 322.958, 236.872, 322.958, 235.428);
((GeneralPath)shape).curveTo(322.958, 233.98, 324.129, 232.80899, 325.57602, 232.80899);
((GeneralPath)shape).curveTo(327.02304, 232.80899, 328.195, 233.98, 328.195, 235.428);
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_9;
g.setTransform(defaultTransform__0_9);
g.setClip(clip__0_9);
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
        return 260;
    }

    /**
     * Returns the Y of the bounding box of the original SVG image.
     * 
     * @return The Y of the bounding box of the original SVG image.
     */
    public static int getOrigY() {
        return 233;
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

