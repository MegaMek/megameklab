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

import java.awt.*;
import java.awt.geom.*;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * This class has been automatically generated using <a
 * href="http://englishjavadrinker.blogspot.com/search/label/SVGRoundTrip">SVGRoundTrip</a>.
 */
public class Squad5_5_9 {
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
((GeneralPath)shape).moveTo(265.196, 713.355);
((GeneralPath)shape).curveTo(265.196, 714.80096, 264.02402, 715.97296, 262.57602, 715.97296);
((GeneralPath)shape).curveTo(261.12903, 715.97296, 259.958, 714.80096, 259.958, 713.355);
((GeneralPath)shape).curveTo(259.958, 711.90796, 261.129, 710.73596, 262.57602, 710.73596);
((GeneralPath)shape).curveTo(264.02304, 710.73596, 265.196, 711.907, 265.196, 713.355);
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
((GeneralPath)shape).moveTo(265.196, 695.149);
((GeneralPath)shape).curveTo(265.196, 696.595, 264.02402, 697.76697, 262.57602, 697.76697);
((GeneralPath)shape).curveTo(261.12903, 697.76697, 259.958, 696.595, 259.958, 695.149);
((GeneralPath)shape).curveTo(259.958, 693.70197, 261.129, 692.52997, 262.57602, 692.52997);
((GeneralPath)shape).curveTo(264.02304, 692.52997, 265.196, 693.701, 265.196, 695.149);
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
((GeneralPath)shape).moveTo(265.196, 676.942);
((GeneralPath)shape).curveTo(265.196, 678.387, 264.02402, 679.559, 262.57602, 679.559);
((GeneralPath)shape).curveTo(261.12903, 679.559, 259.958, 678.387, 259.958, 676.942);
((GeneralPath)shape).curveTo(259.958, 675.495, 261.129, 674.322, 262.57602, 674.322);
((GeneralPath)shape).curveTo(264.02304, 674.322, 265.196, 675.494, 265.196, 676.942);
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
((GeneralPath)shape).moveTo(265.196, 658.735);
((GeneralPath)shape).curveTo(265.196, 660.18097, 264.02402, 661.352, 262.57602, 661.352);
((GeneralPath)shape).curveTo(261.12903, 661.352, 259.958, 660.18097, 259.958, 658.735);
((GeneralPath)shape).curveTo(259.958, 657.28796, 261.129, 656.115, 262.57602, 656.115);
((GeneralPath)shape).curveTo(264.02304, 656.115, 265.196, 657.287, 265.196, 658.735);
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
((GeneralPath)shape).moveTo(265.196, 640.527);
((GeneralPath)shape).curveTo(265.196, 641.97296, 264.02402, 643.146, 262.57602, 643.146);
((GeneralPath)shape).curveTo(261.12903, 643.146, 259.958, 641.973, 259.958, 640.527);
((GeneralPath)shape).curveTo(259.958, 639.07996, 261.129, 637.90796, 262.57602, 637.90796);
((GeneralPath)shape).curveTo(264.02304, 637.90796, 265.196, 639.079, 265.196, 640.527);
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
((GeneralPath)shape).moveTo(272.196, 713.355);
((GeneralPath)shape).curveTo(272.196, 714.80096, 271.02402, 715.97296, 269.57602, 715.97296);
((GeneralPath)shape).curveTo(268.12903, 715.97296, 266.958, 714.80096, 266.958, 713.355);
((GeneralPath)shape).curveTo(266.958, 711.90796, 268.129, 710.73596, 269.57602, 710.73596);
((GeneralPath)shape).curveTo(271.02304, 710.73596, 272.196, 711.907, 272.196, 713.355);
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
((GeneralPath)shape).moveTo(272.196, 695.149);
((GeneralPath)shape).curveTo(272.196, 696.595, 271.02402, 697.76697, 269.57602, 697.76697);
((GeneralPath)shape).curveTo(268.12903, 697.76697, 266.958, 696.595, 266.958, 695.149);
((GeneralPath)shape).curveTo(266.958, 693.70197, 268.129, 692.52997, 269.57602, 692.52997);
((GeneralPath)shape).curveTo(271.02304, 692.52997, 272.196, 693.701, 272.196, 695.149);
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
((GeneralPath)shape).moveTo(272.196, 676.942);
((GeneralPath)shape).curveTo(272.196, 678.387, 271.02402, 679.559, 269.57602, 679.559);
((GeneralPath)shape).curveTo(268.12903, 679.559, 266.958, 678.387, 266.958, 676.942);
((GeneralPath)shape).curveTo(266.958, 675.495, 268.129, 674.322, 269.57602, 674.322);
((GeneralPath)shape).curveTo(271.02304, 674.322, 272.196, 675.494, 272.196, 676.942);
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
((GeneralPath)shape).moveTo(272.196, 658.735);
((GeneralPath)shape).curveTo(272.196, 660.18097, 271.02402, 661.352, 269.57602, 661.352);
((GeneralPath)shape).curveTo(268.12903, 661.352, 266.958, 660.18097, 266.958, 658.735);
((GeneralPath)shape).curveTo(266.958, 657.28796, 268.129, 656.115, 269.57602, 656.115);
((GeneralPath)shape).curveTo(271.02304, 656.115, 272.196, 657.287, 272.196, 658.735);
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
((GeneralPath)shape).moveTo(272.196, 640.527);
((GeneralPath)shape).curveTo(272.196, 641.97296, 271.02402, 643.146, 269.57602, 643.146);
((GeneralPath)shape).curveTo(268.12903, 643.146, 266.958, 641.973, 266.958, 640.527);
((GeneralPath)shape).curveTo(266.958, 639.07996, 268.129, 637.90796, 269.57602, 637.90796);
((GeneralPath)shape).curveTo(271.02304, 637.90796, 272.196, 639.079, 272.196, 640.527);
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
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
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.5057f,0,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(279.196, 713.355);
((GeneralPath)shape).curveTo(279.196, 714.80096, 278.02402, 715.97296, 276.57602, 715.97296);
((GeneralPath)shape).curveTo(275.12903, 715.97296, 273.958, 714.80096, 273.958, 713.355);
((GeneralPath)shape).curveTo(273.958, 711.90796, 275.129, 710.73596, 276.57602, 710.73596);
((GeneralPath)shape).curveTo(278.02304, 710.73596, 279.196, 711.907, 279.196, 713.355);
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
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
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.5057f,0,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(279.196, 695.149);
((GeneralPath)shape).curveTo(279.196, 696.595, 278.02402, 697.76697, 276.57602, 697.76697);
((GeneralPath)shape).curveTo(275.12903, 697.76697, 273.958, 696.595, 273.958, 695.149);
((GeneralPath)shape).curveTo(273.958, 693.70197, 275.129, 692.52997, 276.57602, 692.52997);
((GeneralPath)shape).curveTo(278.02304, 692.52997, 279.196, 693.701, 279.196, 695.149);
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
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.5057f,0,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(279.196, 676.942);
((GeneralPath)shape).curveTo(279.196, 678.387, 278.02402, 679.559, 276.57602, 679.559);
((GeneralPath)shape).curveTo(275.12903, 679.559, 273.958, 678.387, 273.958, 676.942);
((GeneralPath)shape).curveTo(273.958, 675.495, 275.129, 674.322, 276.57602, 674.322);
((GeneralPath)shape).curveTo(278.02304, 674.322, 279.196, 675.494, 279.196, 676.942);
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
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
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.5057f,0,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(279.196, 658.735);
((GeneralPath)shape).curveTo(279.196, 660.18097, 278.02402, 661.352, 276.57602, 661.352);
((GeneralPath)shape).curveTo(275.12903, 661.352, 273.958, 660.18097, 273.958, 658.735);
((GeneralPath)shape).curveTo(273.958, 657.28796, 275.129, 656.115, 276.57602, 656.115);
((GeneralPath)shape).curveTo(278.02304, 656.115, 279.196, 657.287, 279.196, 658.735);
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
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.5057f,0,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(279.196, 640.527);
((GeneralPath)shape).curveTo(279.196, 641.97296, 278.02402, 643.146, 276.57602, 643.146);
((GeneralPath)shape).curveTo(275.12903, 643.146, 273.958, 641.973, 273.958, 640.527);
((GeneralPath)shape).curveTo(273.958, 639.07996, 275.129, 637.90796, 276.57602, 637.90796);
((GeneralPath)shape).curveTo(278.02304, 637.90796, 279.196, 639.079, 279.196, 640.527);
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
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
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.5057f,0,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(286.196, 713.355);
((GeneralPath)shape).curveTo(286.196, 714.80096, 285.02402, 715.97296, 283.57602, 715.97296);
((GeneralPath)shape).curveTo(282.12903, 715.97296, 280.958, 714.80096, 280.958, 713.355);
((GeneralPath)shape).curveTo(280.958, 711.90796, 282.129, 710.73596, 283.57602, 710.73596);
((GeneralPath)shape).curveTo(285.02304, 710.73596, 286.196, 711.907, 286.196, 713.355);
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
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.5057f,0,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(286.196, 695.149);
((GeneralPath)shape).curveTo(286.196, 696.595, 285.02402, 697.76697, 283.57602, 697.76697);
((GeneralPath)shape).curveTo(282.12903, 697.76697, 280.958, 696.595, 280.958, 695.149);
((GeneralPath)shape).curveTo(280.958, 693.70197, 282.129, 692.52997, 283.57602, 692.52997);
((GeneralPath)shape).curveTo(285.02304, 692.52997, 286.196, 693.701, 286.196, 695.149);
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
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.5057f,0,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(286.196, 676.942);
((GeneralPath)shape).curveTo(286.196, 678.387, 285.02402, 679.559, 283.57602, 679.559);
((GeneralPath)shape).curveTo(282.12903, 679.559, 280.958, 678.387, 280.958, 676.942);
((GeneralPath)shape).curveTo(280.958, 675.495, 282.129, 674.322, 283.57602, 674.322);
((GeneralPath)shape).curveTo(285.02304, 674.322, 286.196, 675.494, 286.196, 676.942);
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
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.5057f,0,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(286.196, 658.735);
((GeneralPath)shape).curveTo(286.196, 660.18097, 285.02402, 661.352, 283.57602, 661.352);
((GeneralPath)shape).curveTo(282.12903, 661.352, 280.958, 660.18097, 280.958, 658.735);
((GeneralPath)shape).curveTo(280.958, 657.28796, 282.129, 656.115, 283.57602, 656.115);
((GeneralPath)shape).curveTo(285.02304, 656.115, 286.196, 657.287, 286.196, 658.735);
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
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.5057f,0,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(286.196, 640.527);
((GeneralPath)shape).curveTo(286.196, 641.97296, 285.02402, 643.146, 283.57602, 643.146);
((GeneralPath)shape).curveTo(282.12903, 643.146, 280.958, 641.973, 280.958, 640.527);
((GeneralPath)shape).curveTo(280.958, 639.07996, 282.129, 637.90796, 283.57602, 637.90796);
((GeneralPath)shape).curveTo(285.02304, 637.90796, 286.196, 639.079, 286.196, 640.527);
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
stroke = new BasicStroke(0.5057f,0,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(293.196, 713.355);
((GeneralPath)shape).curveTo(293.196, 714.80096, 292.02402, 715.97296, 290.57602, 715.97296);
((GeneralPath)shape).curveTo(289.12903, 715.97296, 287.958, 714.80096, 287.958, 713.355);
((GeneralPath)shape).curveTo(287.958, 711.90796, 289.129, 710.73596, 290.57602, 710.73596);
((GeneralPath)shape).curveTo(292.02304, 710.73596, 293.196, 711.907, 293.196, 713.355);
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
stroke = new BasicStroke(0.5057f,0,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(293.196, 695.149);
((GeneralPath)shape).curveTo(293.196, 696.595, 292.02402, 697.76697, 290.57602, 697.76697);
((GeneralPath)shape).curveTo(289.12903, 697.76697, 287.958, 696.595, 287.958, 695.149);
((GeneralPath)shape).curveTo(287.958, 693.70197, 289.129, 692.52997, 290.57602, 692.52997);
((GeneralPath)shape).curveTo(292.02304, 692.52997, 293.196, 693.701, 293.196, 695.149);
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
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.5057f,0,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(293.196, 676.942);
((GeneralPath)shape).curveTo(293.196, 678.387, 292.02402, 679.559, 290.57602, 679.559);
((GeneralPath)shape).curveTo(289.12903, 679.559, 287.958, 678.387, 287.958, 676.942);
((GeneralPath)shape).curveTo(287.958, 675.495, 289.129, 674.322, 290.57602, 674.322);
((GeneralPath)shape).curveTo(292.02304, 674.322, 293.196, 675.494, 293.196, 676.942);
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
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.5057f,0,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(293.196, 658.735);
((GeneralPath)shape).curveTo(293.196, 660.18097, 292.02402, 661.352, 290.57602, 661.352);
((GeneralPath)shape).curveTo(289.12903, 661.352, 287.958, 660.18097, 287.958, 658.735);
((GeneralPath)shape).curveTo(287.958, 657.28796, 289.129, 656.115, 290.57602, 656.115);
((GeneralPath)shape).curveTo(292.02304, 656.115, 293.196, 657.287, 293.196, 658.735);
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
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.5057f,0,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(293.196, 640.527);
((GeneralPath)shape).curveTo(293.196, 641.97296, 292.02402, 643.146, 290.57602, 643.146);
((GeneralPath)shape).curveTo(289.12903, 643.146, 287.958, 641.973, 287.958, 640.527);
((GeneralPath)shape).curveTo(287.958, 639.07996, 289.129, 637.90796, 290.57602, 637.90796);
((GeneralPath)shape).curveTo(292.02304, 637.90796, 293.196, 639.079, 293.196, 640.527);
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
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.5057f,0,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(300.196, 713.355);
((GeneralPath)shape).curveTo(300.196, 714.80096, 299.02402, 715.97296, 297.57602, 715.97296);
((GeneralPath)shape).curveTo(296.12903, 715.97296, 294.958, 714.80096, 294.958, 713.355);
((GeneralPath)shape).curveTo(294.958, 711.90796, 296.129, 710.73596, 297.57602, 710.73596);
((GeneralPath)shape).curveTo(299.02304, 710.73596, 300.196, 711.907, 300.196, 713.355);
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
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.5057f,0,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(300.196, 695.149);
((GeneralPath)shape).curveTo(300.196, 696.595, 299.02402, 697.76697, 297.57602, 697.76697);
((GeneralPath)shape).curveTo(296.12903, 697.76697, 294.958, 696.595, 294.958, 695.149);
((GeneralPath)shape).curveTo(294.958, 693.70197, 296.129, 692.52997, 297.57602, 692.52997);
((GeneralPath)shape).curveTo(299.02304, 692.52997, 300.196, 693.701, 300.196, 695.149);
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
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.5057f,0,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(300.196, 676.942);
((GeneralPath)shape).curveTo(300.196, 678.387, 299.02402, 679.559, 297.57602, 679.559);
((GeneralPath)shape).curveTo(296.12903, 679.559, 294.958, 678.387, 294.958, 676.942);
((GeneralPath)shape).curveTo(294.958, 675.495, 296.129, 674.322, 297.57602, 674.322);
((GeneralPath)shape).curveTo(299.02304, 674.322, 300.196, 675.494, 300.196, 676.942);
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
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
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.5057f,0,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(300.196, 658.735);
((GeneralPath)shape).curveTo(300.196, 660.18097, 299.02402, 661.352, 297.57602, 661.352);
((GeneralPath)shape).curveTo(296.12903, 661.352, 294.958, 660.18097, 294.958, 658.735);
((GeneralPath)shape).curveTo(294.958, 657.28796, 296.129, 656.115, 297.57602, 656.115);
((GeneralPath)shape).curveTo(299.02304, 656.115, 300.196, 657.287, 300.196, 658.735);
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
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.5057f,0,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(300.196, 640.527);
((GeneralPath)shape).curveTo(300.196, 641.97296, 299.02402, 643.146, 297.57602, 643.146);
((GeneralPath)shape).curveTo(296.12903, 643.146, 294.958, 641.973, 294.958, 640.527);
((GeneralPath)shape).curveTo(294.958, 639.07996, 296.129, 637.90796, 297.57602, 637.90796);
((GeneralPath)shape).curveTo(299.02304, 637.90796, 300.196, 639.079, 300.196, 640.527);
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
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.5057f,0,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(307.195, 713.355);
((GeneralPath)shape).curveTo(307.195, 714.80096, 306.023, 715.97296, 304.57602, 715.97296);
((GeneralPath)shape).curveTo(303.13, 715.97296, 301.959, 714.80096, 301.959, 713.355);
((GeneralPath)shape).curveTo(301.959, 711.90796, 303.13, 710.73596, 304.57602, 710.73596);
((GeneralPath)shape).curveTo(306.023, 710.736, 307.195, 711.907, 307.195, 713.355);
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
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
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.5057f,0,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(307.195, 695.149);
((GeneralPath)shape).curveTo(307.195, 696.595, 306.023, 697.76697, 304.57602, 697.76697);
((GeneralPath)shape).curveTo(303.13, 697.76697, 301.959, 696.595, 301.959, 695.149);
((GeneralPath)shape).curveTo(301.959, 693.70197, 303.13, 692.52997, 304.57602, 692.52997);
((GeneralPath)shape).curveTo(306.023, 692.53, 307.195, 693.701, 307.195, 695.149);
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
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.5057f,0,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(307.195, 676.942);
((GeneralPath)shape).curveTo(307.195, 678.387, 306.023, 679.559, 304.57602, 679.559);
((GeneralPath)shape).curveTo(303.13, 679.559, 301.959, 678.387, 301.959, 676.942);
((GeneralPath)shape).curveTo(301.959, 675.495, 303.13, 674.322, 304.57602, 674.322);
((GeneralPath)shape).curveTo(306.023, 674.322, 307.195, 675.494, 307.195, 676.942);
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
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
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.5057f,0,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(307.195, 658.735);
((GeneralPath)shape).curveTo(307.195, 660.18097, 306.023, 661.352, 304.57602, 661.352);
((GeneralPath)shape).curveTo(303.13, 661.352, 301.959, 660.18097, 301.959, 658.735);
((GeneralPath)shape).curveTo(301.959, 657.28796, 303.13, 656.115, 304.57602, 656.115);
((GeneralPath)shape).curveTo(306.023, 656.115, 307.195, 657.287, 307.195, 658.735);
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
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.5057f,0,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(307.195, 640.527);
((GeneralPath)shape).curveTo(307.195, 641.97296, 306.023, 643.146, 304.57602, 643.146);
((GeneralPath)shape).curveTo(303.13, 643.146, 301.959, 641.973, 301.959, 640.527);
((GeneralPath)shape).curveTo(301.959, 639.07996, 303.13, 637.90796, 304.57602, 637.90796);
((GeneralPath)shape).curveTo(306.023, 637.907, 307.195, 639.079, 307.195, 640.527);
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
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
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.5057f,0,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(314.195, 713.355);
((GeneralPath)shape).curveTo(314.195, 714.80096, 313.023, 715.97296, 311.57602, 715.97296);
((GeneralPath)shape).curveTo(310.13, 715.97296, 308.959, 714.80096, 308.959, 713.355);
((GeneralPath)shape).curveTo(308.959, 711.90796, 310.13, 710.73596, 311.57602, 710.73596);
((GeneralPath)shape).curveTo(313.023, 710.736, 314.195, 711.907, 314.195, 713.355);
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
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
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.5057f,0,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(314.195, 695.149);
((GeneralPath)shape).curveTo(314.195, 696.595, 313.023, 697.76697, 311.57602, 697.76697);
((GeneralPath)shape).curveTo(310.13, 697.76697, 308.959, 696.595, 308.959, 695.149);
((GeneralPath)shape).curveTo(308.959, 693.70197, 310.13, 692.52997, 311.57602, 692.52997);
((GeneralPath)shape).curveTo(313.023, 692.53, 314.195, 693.701, 314.195, 695.149);
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
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
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.5057f,0,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(314.195, 676.942);
((GeneralPath)shape).curveTo(314.195, 678.387, 313.023, 679.559, 311.57602, 679.559);
((GeneralPath)shape).curveTo(310.13, 679.559, 308.959, 678.387, 308.959, 676.942);
((GeneralPath)shape).curveTo(308.959, 675.495, 310.13, 674.322, 311.57602, 674.322);
((GeneralPath)shape).curveTo(313.023, 674.322, 314.195, 675.494, 314.195, 676.942);
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
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
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.5057f,0,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(314.195, 658.735);
((GeneralPath)shape).curveTo(314.195, 660.18097, 313.023, 661.352, 311.57602, 661.352);
((GeneralPath)shape).curveTo(310.13, 661.352, 308.959, 660.18097, 308.959, 658.735);
((GeneralPath)shape).curveTo(308.959, 657.28796, 310.13, 656.115, 311.57602, 656.115);
((GeneralPath)shape).curveTo(313.023, 656.115, 314.195, 657.287, 314.195, 658.735);
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
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
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.5057f,0,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(314.195, 640.527);
((GeneralPath)shape).curveTo(314.195, 641.97296, 313.023, 643.146, 311.57602, 643.146);
((GeneralPath)shape).curveTo(310.13, 643.146, 308.959, 641.973, 308.959, 640.527);
((GeneralPath)shape).curveTo(308.959, 639.07996, 310.13, 637.90796, 311.57602, 637.90796);
((GeneralPath)shape).curveTo(313.023, 637.907, 314.195, 639.079, 314.195, 640.527);
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
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
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.5057f,0,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(321.195, 713.355);
((GeneralPath)shape).curveTo(321.195, 714.80096, 320.023, 715.97296, 318.57602, 715.97296);
((GeneralPath)shape).curveTo(317.13, 715.97296, 315.959, 714.80096, 315.959, 713.355);
((GeneralPath)shape).curveTo(315.959, 711.90796, 317.13, 710.73596, 318.57602, 710.73596);
((GeneralPath)shape).curveTo(320.023, 710.736, 321.195, 711.907, 321.195, 713.355);
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
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
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.5057f,0,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(321.195, 695.149);
((GeneralPath)shape).curveTo(321.195, 696.595, 320.023, 697.76697, 318.57602, 697.76697);
((GeneralPath)shape).curveTo(317.13, 697.76697, 315.959, 696.595, 315.959, 695.149);
((GeneralPath)shape).curveTo(315.959, 693.70197, 317.13, 692.52997, 318.57602, 692.52997);
((GeneralPath)shape).curveTo(320.023, 692.53, 321.195, 693.701, 321.195, 695.149);
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
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
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.5057f,0,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(321.195, 676.942);
((GeneralPath)shape).curveTo(321.195, 678.387, 320.023, 679.559, 318.57602, 679.559);
((GeneralPath)shape).curveTo(317.13, 679.559, 315.959, 678.387, 315.959, 676.942);
((GeneralPath)shape).curveTo(315.959, 675.495, 317.13, 674.322, 318.57602, 674.322);
((GeneralPath)shape).curveTo(320.023, 674.322, 321.195, 675.494, 321.195, 676.942);
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
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
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.5057f,0,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(321.195, 658.735);
((GeneralPath)shape).curveTo(321.195, 660.18097, 320.023, 661.352, 318.57602, 661.352);
((GeneralPath)shape).curveTo(317.13, 661.352, 315.959, 660.18097, 315.959, 658.735);
((GeneralPath)shape).curveTo(315.959, 657.28796, 317.13, 656.115, 318.57602, 656.115);
((GeneralPath)shape).curveTo(320.023, 656.115, 321.195, 657.287, 321.195, 658.735);
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
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
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.5057f,0,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(321.195, 640.527);
((GeneralPath)shape).curveTo(321.195, 641.97296, 320.023, 643.146, 318.57602, 643.146);
((GeneralPath)shape).curveTo(317.13, 643.146, 315.959, 641.973, 315.959, 640.527);
((GeneralPath)shape).curveTo(315.959, 639.07996, 317.13, 637.90796, 318.57602, 637.90796);
((GeneralPath)shape).curveTo(320.023, 637.907, 321.195, 639.079, 321.195, 640.527);
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_44;
g.setTransform(defaultTransform__0_44);
g.setClip(clip__0_44);
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
        return 638;
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

