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





/**
 * This class has been automatically generated using <a
 * href="http://englishjavadrinker.blogspot.com/search/label/SVGRoundTrip">SVGRoundTrip</a>.
 */
public class BipedIS10 {
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
// _0_0_0 is CompositeGraphicsNode
float alpha__0_0_0_0 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_0 = g.getClip();
AffineTransform defaultTransform__0_0_0_0 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(482.48, 423.47);
((GeneralPath)shape).curveTo(481.48502, 423.47, 480.659, 422.642, 480.659, 421.648);
((GeneralPath)shape).curveTo(480.659, 420.656, 481.485, 419.828, 482.48, 419.828);
((GeneralPath)shape).curveTo(483.47202, 419.828, 484.30103, 420.656, 484.30103, 421.648);
((GeneralPath)shape).curveTo(484.302, 422.642, 483.473, 423.47, 482.48, 423.47);
((GeneralPath)shape).lineTo(482.48, 423.47);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
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
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(478.181, 415.8);
((GeneralPath)shape).curveTo(477.185, 415.8, 476.356, 414.972, 476.356, 413.97998);
((GeneralPath)shape).curveTo(476.356, 412.986, 477.185, 412.158, 478.181, 412.158);
((GeneralPath)shape).curveTo(479.173, 412.158, 480.001, 412.986, 480.001, 413.97998);
((GeneralPath)shape).curveTo(480.001, 414.972, 479.173, 415.8, 478.181, 415.8);
((GeneralPath)shape).lineTo(478.181, 415.8);
((GeneralPath)shape).closePath();
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
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(474.077, 423.47);
((GeneralPath)shape).curveTo(473.085, 423.47, 472.25598, 422.642, 472.25598, 421.648);
((GeneralPath)shape).curveTo(472.25598, 420.656, 473.085, 419.828, 474.077, 419.828);
((GeneralPath)shape).curveTo(475.072, 419.828, 475.897, 420.656, 475.897, 421.648);
((GeneralPath)shape).curveTo(475.897, 422.642, 475.072, 423.47, 474.077, 423.47);
((GeneralPath)shape).lineTo(474.077, 423.47);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
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
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(502.381, 517.283);
((GeneralPath)shape).curveTo(501.402, 517.471, 500.432, 516.817, 500.243, 515.843);
((GeneralPath)shape).curveTo(500.052, 514.867, 500.70602, 513.89703, 501.68402, 513.70703);
((GeneralPath)shape).curveTo(502.66003, 513.518, 503.627, 514.17206, 503.81802, 515.14606);
((GeneralPath)shape).curveTo(504.007, 516.123, 503.353, 517.094, 502.381, 517.283);
((GeneralPath)shape).lineTo(502.381, 517.283);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
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
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(507.696, 544.646);
((GeneralPath)shape).curveTo(506.72, 544.834, 505.75, 544.18097, 505.561, 543.205);
((GeneralPath)shape).curveTo(505.37, 542.229, 506.02402, 541.26, 507.003, 541.07);
((GeneralPath)shape).curveTo(507.978, 540.88, 508.94498, 541.534, 509.136, 542.51);
((GeneralPath)shape).curveTo(509.328, 543.485, 508.674, 544.456, 507.696, 544.646);
((GeneralPath)shape).lineTo(507.696, 544.646);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
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
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(453.143, 517.282);
((GeneralPath)shape).curveTo(454.11902, 517.47, 455.08902, 516.816, 455.281, 515.841);
((GeneralPath)shape).curveTo(455.469, 514.866, 454.814, 513.898, 453.83902, 513.708);
((GeneralPath)shape).curveTo(452.864, 513.517, 451.894, 514.171, 451.70203, 515.146);
((GeneralPath)shape).curveTo(451.514, 516.122, 452.168, 517.092, 453.143, 517.282);
((GeneralPath)shape).lineTo(453.143, 517.282);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
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
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(447.822, 544.645);
((GeneralPath)shape).curveTo(448.79898, 544.83405, 449.76898, 544.18, 449.961, 543.205);
((GeneralPath)shape).curveTo(450.149, 542.229, 449.494, 541.26, 448.518, 541.06903);
((GeneralPath)shape).curveTo(447.543, 540.88, 446.573, 541.533, 446.383, 542.50903);
((GeneralPath)shape).curveTo(446.193, 543.484, 446.848, 544.455, 447.822, 544.645);
((GeneralPath)shape).lineTo(447.822, 544.645);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
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
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(528.025, 462.552);
((GeneralPath)shape).curveTo(527.036, 462.639, 526.13605, 461.88602, 526.052, 460.896);
((GeneralPath)shape).curveTo(525.965, 459.906, 526.716, 459.01, 527.70703, 458.923);
((GeneralPath)shape).curveTo(528.69806, 458.835, 529.59406, 459.589, 529.681, 460.579);
((GeneralPath)shape).curveTo(529.767, 461.568, 529.016, 462.466, 528.025, 462.552);
((GeneralPath)shape).lineTo(528.025, 462.552);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
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
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(428.664, 462.552);
((GeneralPath)shape).curveTo(429.656, 462.639, 430.552, 461.88602, 430.64, 460.896);
((GeneralPath)shape).curveTo(430.72702, 459.906, 429.97302, 459.01, 428.98203, 458.923);
((GeneralPath)shape).curveTo(427.99402, 458.835, 427.09702, 459.589, 427.01102, 460.579);
((GeneralPath)shape).curveTo(426.923, 461.568, 427.676, 462.466, 428.664, 462.552);
((GeneralPath)shape).lineTo(428.664, 462.552);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_8;
g.setTransform(defaultTransform__0_0_0_8);
g.setClip(clip__0_0_0_8);
float alpha__0_0_0_9 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_9 = g.getClip();
AffineTransform defaultTransform__0_0_0_9 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_9 is CompositeGraphicsNode
float alpha__0_0_0_9_0 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_9_0 = g.getClip();
AffineTransform defaultTransform__0_0_0_9_0 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_9_0 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(478.252, 452.529);
((GeneralPath)shape).curveTo(477.256, 452.529, 476.427, 451.702, 476.427, 450.70798);
((GeneralPath)shape).curveTo(476.427, 449.71497, 477.256, 448.88696, 478.252, 448.88696);
((GeneralPath)shape).curveTo(479.24402, 448.88696, 480.07202, 449.71497, 480.07202, 450.70798);
((GeneralPath)shape).curveTo(480.072, 451.702, 479.244, 452.529, 478.252, 452.529);
((GeneralPath)shape).lineTo(478.252, 452.529);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_9_0;
g.setTransform(defaultTransform__0_0_0_9_0);
g.setClip(clip__0_0_0_9_0);
float alpha__0_0_0_9_1 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_9_1 = g.getClip();
AffineTransform defaultTransform__0_0_0_9_1 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_9_1 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(478.252, 438.479);
((GeneralPath)shape).curveTo(477.256, 438.479, 476.427, 437.652, 476.427, 436.658);
((GeneralPath)shape).curveTo(476.427, 435.66498, 477.256, 434.83698, 478.252, 434.83698);
((GeneralPath)shape).curveTo(479.24402, 434.83698, 480.07202, 435.66498, 480.07202, 436.658);
((GeneralPath)shape).curveTo(480.072, 437.652, 479.244, 438.479, 478.252, 438.479);
((GeneralPath)shape).lineTo(478.252, 438.479);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_9_1;
g.setTransform(defaultTransform__0_0_0_9_1);
g.setClip(clip__0_0_0_9_1);
float alpha__0_0_0_9_2 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_9_2 = g.getClip();
AffineTransform defaultTransform__0_0_0_9_2 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_9_2 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(478.252, 466.58);
((GeneralPath)shape).curveTo(477.256, 466.58, 476.427, 465.753, 476.427, 464.75897);
((GeneralPath)shape).curveTo(476.427, 463.76596, 477.256, 462.93796, 478.252, 462.93796);
((GeneralPath)shape).curveTo(479.24402, 462.93796, 480.07202, 463.76596, 480.07202, 464.75897);
((GeneralPath)shape).curveTo(480.072, 465.753, 479.244, 466.58, 478.252, 466.58);
((GeneralPath)shape).lineTo(478.252, 466.58);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_9_2;
g.setTransform(defaultTransform__0_0_0_9_2);
g.setClip(clip__0_0_0_9_2);
float alpha__0_0_0_9_3 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_9_3 = g.getClip();
AffineTransform defaultTransform__0_0_0_9_3 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_9_3 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(478.252, 480.63);
((GeneralPath)shape).curveTo(477.256, 480.63, 476.427, 479.803, 476.427, 478.809);
((GeneralPath)shape).curveTo(476.427, 477.81598, 477.256, 476.98798, 478.252, 476.98798);
((GeneralPath)shape).curveTo(479.24402, 476.98798, 480.07202, 477.81598, 480.07202, 478.809);
((GeneralPath)shape).curveTo(480.072, 479.803, 479.244, 480.63, 478.252, 480.63);
((GeneralPath)shape).lineTo(478.252, 480.63);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_9_3;
g.setTransform(defaultTransform__0_0_0_9_3);
g.setClip(clip__0_0_0_9_3);
origAlpha = alpha__0_0_0_9;
g.setTransform(defaultTransform__0_0_0_9);
g.setClip(clip__0_0_0_9);
float alpha__0_0_0_10 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_10 = g.getClip();
AffineTransform defaultTransform__0_0_0_10 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_10 is CompositeGraphicsNode
float alpha__0_0_0_10_0 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_10_0 = g.getClip();
AffineTransform defaultTransform__0_0_0_10_0 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_10_0 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(506.989, 438.477);
((GeneralPath)shape).curveTo(507.98502, 438.477, 508.81403, 437.649, 508.81403, 436.655);
((GeneralPath)shape).curveTo(508.81403, 435.663, 507.98502, 434.83398, 506.989, 434.83398);
((GeneralPath)shape).curveTo(505.997, 434.83398, 505.169, 435.663, 505.169, 436.655);
((GeneralPath)shape).curveTo(505.169, 437.648, 505.997, 438.477, 506.989, 438.477);
((GeneralPath)shape).lineTo(506.989, 438.477);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_10_0;
g.setTransform(defaultTransform__0_0_0_10_0);
g.setClip(clip__0_0_0_10_0);
float alpha__0_0_0_10_1 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_10_1 = g.getClip();
AffineTransform defaultTransform__0_0_0_10_1 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_10_1 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(495.485, 438.477);
((GeneralPath)shape).curveTo(496.47998, 438.477, 497.306, 437.649, 497.306, 436.655);
((GeneralPath)shape).curveTo(497.306, 435.663, 496.48, 434.83398, 495.485, 434.83398);
((GeneralPath)shape).curveTo(494.49298, 434.83398, 493.66397, 435.663, 493.66397, 436.655);
((GeneralPath)shape).curveTo(493.664, 437.648, 494.493, 438.477, 495.485, 438.477);
((GeneralPath)shape).lineTo(495.485, 438.477);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_10_1;
g.setTransform(defaultTransform__0_0_0_10_1);
g.setClip(clip__0_0_0_10_1);
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
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(493.905, 459.552);
((GeneralPath)shape).curveTo(494.898, 459.552, 495.726, 458.725, 495.726, 457.731);
((GeneralPath)shape).curveTo(495.726, 456.73798, 494.898, 455.90997, 493.905, 455.90997);
((GeneralPath)shape).curveTo(492.91, 455.90997, 492.08398, 456.73798, 492.08398, 457.731);
((GeneralPath)shape).curveTo(492.084, 458.725, 492.91, 459.552, 493.905, 459.552);
((GeneralPath)shape).lineTo(493.905, 459.552);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_11;
g.setTransform(defaultTransform__0_0_0_11);
g.setClip(clip__0_0_0_11);
float alpha__0_0_0_12 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_12 = g.getClip();
AffineTransform defaultTransform__0_0_0_12 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_12 is CompositeGraphicsNode
float alpha__0_0_0_12_0 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_12_0 = g.getClip();
AffineTransform defaultTransform__0_0_0_12_0 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_12_0 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(449.567, 438.477);
((GeneralPath)shape).curveTo(448.572, 438.477, 447.74298, 437.649, 447.74298, 436.655);
((GeneralPath)shape).curveTo(447.74298, 435.663, 448.572, 434.83398, 449.567, 434.83398);
((GeneralPath)shape).curveTo(450.559, 434.83398, 451.38498, 435.663, 451.38498, 436.655);
((GeneralPath)shape).curveTo(451.386, 437.648, 450.56, 438.477, 449.567, 438.477);
((GeneralPath)shape).lineTo(449.567, 438.477);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_12_0;
g.setTransform(defaultTransform__0_0_0_12_0);
g.setClip(clip__0_0_0_12_0);
float alpha__0_0_0_12_1 = origAlpha;
origAlpha = origAlpha * 1.0f;
g.setComposite(AlphaComposite.getInstance(3, origAlpha));
Shape clip__0_0_0_12_1 = g.getClip();
AffineTransform defaultTransform__0_0_0_12_1 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_12_1 is ShapeNode
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(461.072, 438.477);
((GeneralPath)shape).curveTo(460.076, 438.477, 459.248, 437.649, 459.248, 436.655);
((GeneralPath)shape).curveTo(459.248, 435.663, 460.076, 434.83398, 461.072, 434.83398);
((GeneralPath)shape).curveTo(462.064, 434.83398, 462.893, 435.663, 462.893, 436.655);
((GeneralPath)shape).curveTo(462.894, 437.648, 462.064, 438.477, 461.072, 438.477);
((GeneralPath)shape).lineTo(461.072, 438.477);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_12_1;
g.setTransform(defaultTransform__0_0_0_12_1);
g.setClip(clip__0_0_0_12_1);
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
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(0.504f,0,0,2.4142f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(462.652, 459.552);
((GeneralPath)shape).curveTo(461.659, 459.552, 460.831, 458.725, 460.831, 457.731);
((GeneralPath)shape).curveTo(460.831, 456.73798, 461.659, 455.90997, 462.652, 455.90997);
((GeneralPath)shape).curveTo(463.647, 455.90997, 464.47202, 456.73798, 464.47202, 457.731);
((GeneralPath)shape).curveTo(464.473, 458.725, 463.647, 459.552, 462.652, 459.552);
((GeneralPath)shape).lineTo(462.652, 459.552);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_0_0_13;
g.setTransform(defaultTransform__0_0_0_13);
g.setClip(clip__0_0_0_13);
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
        return 427;
    }

    /**
     * Returns the Y of the bounding box of the original SVG image.
     * 
     * @return The Y of the bounding box of the original SVG image.
     */
    public static int getOrigY() {
        return 412;
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

