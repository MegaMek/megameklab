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

import java.awt.*;
import java.awt.geom.*;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * This class has been automatically generated using <a
 * href="http://englishjavadrinker.blogspot.com/search/label/SVGRoundTrip">SVGRoundTrip</a>.
 */
public class Naval_Right_Armor_43 {
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
paint = new Color(67, 67, 68, 255);
stroke = new BasicStroke(0.581f,0,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(566.035, 321.761);
((GeneralPath)shape).curveTo(564.399, 321.761, 563.073, 323.08698, 563.073, 324.723);
((GeneralPath)shape).curveTo(563.073, 326.358, 564.399, 327.684, 566.035, 327.684);
((GeneralPath)shape).curveTo(567.66797, 327.684, 568.99396, 326.358, 568.99396, 324.723);
((GeneralPath)shape).curveTo(568.994, 323.087, 567.668, 321.761, 566.035, 321.761);
((GeneralPath)shape).moveTo(566.035, 353.458);
((GeneralPath)shape).curveTo(564.399, 353.458, 563.073, 354.78302, 563.073, 356.42);
((GeneralPath)shape).curveTo(563.073, 358.05502, 564.399, 359.381, 566.035, 359.381);
((GeneralPath)shape).curveTo(567.66797, 359.381, 568.99396, 358.05502, 568.99396, 356.42);
((GeneralPath)shape).curveTo(568.994, 354.782, 567.668, 353.458, 566.035, 353.458);
((GeneralPath)shape).moveTo(566.035, 290.064);
((GeneralPath)shape).curveTo(564.399, 290.064, 563.073, 291.393, 563.073, 293.024);
((GeneralPath)shape).curveTo(563.073, 294.658, 564.399, 295.98398, 566.035, 295.98398);
((GeneralPath)shape).curveTo(567.66797, 295.98398, 568.99396, 294.658, 568.99396, 293.024);
((GeneralPath)shape).curveTo(568.994, 291.393, 567.668, 290.064, 566.035, 290.064);
((GeneralPath)shape).moveTo(566.035, 226.669);
((GeneralPath)shape).curveTo(564.399, 226.669, 563.073, 227.996, 563.073, 229.63);
((GeneralPath)shape).curveTo(563.073, 231.264, 564.399, 232.59001, 566.035, 232.59001);
((GeneralPath)shape).curveTo(567.66797, 232.59001, 568.99396, 231.264, 568.99396, 229.63);
((GeneralPath)shape).curveTo(568.99396, 227.996, 567.668, 226.669, 566.035, 226.669);
((GeneralPath)shape).moveTo(566.035, 171.139);
((GeneralPath)shape).curveTo(564.399, 171.139, 563.073, 172.46501, 563.073, 174.1);
((GeneralPath)shape).curveTo(563.073, 175.733, 564.399, 177.06001, 566.035, 177.06001);
((GeneralPath)shape).curveTo(567.66797, 177.06001, 568.99396, 175.73302, 568.99396, 174.1);
((GeneralPath)shape).curveTo(568.994, 172.465, 567.668, 171.139, 566.035, 171.139);
((GeneralPath)shape).moveTo(566.035, 155.291);
((GeneralPath)shape).curveTo(564.399, 155.291, 563.073, 156.617, 563.073, 158.25);
((GeneralPath)shape).curveTo(563.073, 159.887, 564.399, 161.212, 566.035, 161.212);
((GeneralPath)shape).curveTo(567.66797, 161.212, 568.99396, 159.88701, 568.99396, 158.25);
((GeneralPath)shape).curveTo(568.994, 156.617, 567.668, 155.291, 566.035, 155.291);
((GeneralPath)shape).moveTo(557.282, 171.137);
((GeneralPath)shape).curveTo(555.646, 171.137, 554.32, 172.46399, 554.32, 174.099);
((GeneralPath)shape).curveTo(554.32, 175.733, 555.646, 177.06, 557.282, 177.06);
((GeneralPath)shape).curveTo(558.914, 177.06, 560.24097, 175.733, 560.24097, 174.099);
((GeneralPath)shape).curveTo(560.241, 172.464, 558.914, 171.137, 557.282, 171.137);
((GeneralPath)shape).moveTo(548.528, 171.168);
((GeneralPath)shape).curveTo(546.893, 171.168, 545.568, 172.494, 545.568, 174.13);
((GeneralPath)shape).curveTo(545.568, 175.764, 546.893, 177.09001, 548.528, 177.09001);
((GeneralPath)shape).curveTo(550.161, 177.09001, 551.48804, 175.764, 551.48804, 174.13);
((GeneralPath)shape).curveTo(551.488, 172.494, 550.161, 171.168, 548.528, 171.168);
((GeneralPath)shape).moveTo(566.035, 258.367);
((GeneralPath)shape).curveTo(564.399, 258.367, 563.073, 259.696, 563.073, 261.327);
((GeneralPath)shape).curveTo(563.073, 262.964, 564.399, 264.288, 566.035, 264.288);
((GeneralPath)shape).curveTo(567.66797, 264.288, 568.99396, 262.96298, 568.99396, 261.327);
((GeneralPath)shape).curveTo(568.994, 259.696, 567.668, 258.367, 566.035, 258.367);
((GeneralPath)shape).moveTo(566.035, 194.973);
((GeneralPath)shape).curveTo(564.399, 194.973, 563.073, 196.29901, 563.073, 197.934);
((GeneralPath)shape).curveTo(563.073, 199.56801, 564.399, 200.89401, 566.035, 200.89401);
((GeneralPath)shape).curveTo(567.66797, 200.89401, 568.99396, 199.56801, 568.99396, 197.934);
((GeneralPath)shape).curveTo(568.994, 196.298, 567.668, 194.973, 566.035, 194.973);
((GeneralPath)shape).moveTo(557.282, 448.515);
((GeneralPath)shape).curveTo(555.646, 448.515, 554.32, 449.841, 554.32, 451.475);
((GeneralPath)shape).curveTo(554.32, 453.11002, 555.646, 454.437, 557.282, 454.437);
((GeneralPath)shape).curveTo(558.914, 454.437, 560.24097, 453.11002, 560.24097, 451.475);
((GeneralPath)shape).curveTo(560.241, 449.841, 558.914, 448.515, 557.282, 448.515);
((GeneralPath)shape).moveTo(557.282, 478.207);
((GeneralPath)shape).curveTo(555.646, 478.207, 554.32, 476.881, 554.32, 475.247);
((GeneralPath)shape).curveTo(554.32, 473.61102, 555.646, 472.285, 557.282, 472.285);
((GeneralPath)shape).curveTo(558.914, 472.285, 560.24097, 473.611, 560.24097, 475.247);
((GeneralPath)shape).curveTo(560.241, 476.881, 558.914, 478.207, 557.282, 478.207);
((GeneralPath)shape).moveTo(557.282, 416.822);
((GeneralPath)shape).curveTo(555.646, 416.822, 554.32, 418.14798, 554.32, 419.783);
((GeneralPath)shape).curveTo(554.32, 421.418, 555.646, 422.744, 557.282, 422.744);
((GeneralPath)shape).curveTo(558.914, 422.744, 560.24097, 421.418, 560.24097, 419.783);
((GeneralPath)shape).curveTo(560.24097, 418.14798, 558.914, 416.822, 557.282, 416.822);
((GeneralPath)shape).moveTo(557.282, 385.128);
((GeneralPath)shape).curveTo(555.646, 385.128, 554.32, 386.455, 554.32, 388.09);
((GeneralPath)shape).curveTo(554.32, 389.724, 555.646, 391.051, 557.282, 391.051);
((GeneralPath)shape).curveTo(558.914, 391.051, 560.24097, 389.724, 560.24097, 388.09);
((GeneralPath)shape).curveTo(560.241, 386.455, 558.914, 385.128, 557.282, 385.128);
((GeneralPath)shape).moveTo(557.282, 321.742);
((GeneralPath)shape).curveTo(555.646, 321.742, 554.32, 323.068, 554.32, 324.703);
((GeneralPath)shape).curveTo(554.32, 326.34, 555.646, 327.665, 557.282, 327.665);
((GeneralPath)shape).curveTo(558.914, 327.665, 560.24097, 326.34, 560.24097, 324.703);
((GeneralPath)shape).curveTo(560.241, 323.067, 558.914, 321.742, 557.282, 321.742);
((GeneralPath)shape).moveTo(557.282, 353.436);
((GeneralPath)shape).curveTo(555.646, 353.436, 554.32, 354.76102, 554.32, 356.396);
((GeneralPath)shape).curveTo(554.32, 358.03, 555.646, 359.356, 557.282, 359.356);
((GeneralPath)shape).curveTo(558.914, 359.356, 560.24097, 358.03, 560.24097, 356.396);
((GeneralPath)shape).curveTo(560.241, 354.761, 558.914, 353.436, 557.282, 353.436);
((GeneralPath)shape).moveTo(557.282, 290.05);
((GeneralPath)shape).curveTo(555.646, 290.05, 554.32, 291.37698, 554.32, 293.00998);
((GeneralPath)shape).curveTo(554.32, 294.645, 555.646, 295.97098, 557.282, 295.97098);
((GeneralPath)shape).curveTo(558.914, 295.97098, 560.24097, 294.645, 560.24097, 293.00998);
((GeneralPath)shape).curveTo(560.241, 291.377, 558.914, 290.05, 557.282, 290.05);
((GeneralPath)shape).moveTo(557.282, 226.663);
((GeneralPath)shape).curveTo(555.646, 226.663, 554.32, 227.989, 554.32, 229.625);
((GeneralPath)shape).curveTo(554.32, 231.259, 555.646, 232.585, 557.282, 232.585);
((GeneralPath)shape).curveTo(558.914, 232.585, 560.24097, 231.259, 560.24097, 229.625);
((GeneralPath)shape).curveTo(560.241, 227.989, 558.914, 226.663, 557.282, 226.663);
((GeneralPath)shape).moveTo(557.282, 258.356);
((GeneralPath)shape).curveTo(555.646, 258.356, 554.32, 259.685, 554.32, 261.31598);
((GeneralPath)shape).curveTo(554.32, 262.95398, 555.646, 264.27698, 557.282, 264.27698);
((GeneralPath)shape).curveTo(558.914, 264.27698, 560.24097, 262.95398, 560.24097, 261.31598);
((GeneralPath)shape).curveTo(560.241, 259.685, 558.914, 258.356, 557.282, 258.356);
((GeneralPath)shape).moveTo(557.282, 194.97);
((GeneralPath)shape).curveTo(555.646, 194.97, 554.32, 196.296, 554.32, 197.931);
((GeneralPath)shape).curveTo(554.32, 199.565, 555.646, 200.891, 557.282, 200.891);
((GeneralPath)shape).curveTo(558.914, 200.891, 560.24097, 199.565, 560.24097, 197.931);
((GeneralPath)shape).curveTo(560.241, 196.296, 558.914, 194.97, 557.282, 194.97);
((GeneralPath)shape).moveTo(539.775, 449.045);
((GeneralPath)shape).curveTo(538.14, 449.045, 536.815, 450.371, 536.815, 452.00702);
((GeneralPath)shape).curveTo(536.815, 453.64102, 538.14, 454.967, 539.775, 454.967);
((GeneralPath)shape).curveTo(541.409, 454.967, 542.736, 453.64102, 542.736, 452.00702);
((GeneralPath)shape).curveTo(542.736, 450.371, 541.409, 449.045, 539.775, 449.045);
((GeneralPath)shape).moveTo(539.775, 478.784);
((GeneralPath)shape).curveTo(538.14, 478.784, 536.815, 477.46, 536.815, 475.824);
((GeneralPath)shape).curveTo(536.815, 474.189, 538.14, 472.863, 539.775, 472.863);
((GeneralPath)shape).curveTo(541.409, 472.863, 542.736, 474.189, 542.736, 475.824);
((GeneralPath)shape).curveTo(542.736, 477.46, 541.409, 478.784, 539.775, 478.784);
((GeneralPath)shape).moveTo(539.775, 417.292);
((GeneralPath)shape).curveTo(538.14, 417.292, 536.815, 418.617, 536.815, 420.25198);
((GeneralPath)shape).curveTo(536.815, 421.88797, 538.14, 423.214, 539.775, 423.214);
((GeneralPath)shape).curveTo(541.409, 423.214, 542.736, 421.888, 542.736, 420.25198);
((GeneralPath)shape).curveTo(542.736, 418.617, 541.409, 417.292, 539.775, 417.292);
((GeneralPath)shape).moveTo(539.775, 385.535);
((GeneralPath)shape).curveTo(538.14, 385.535, 536.815, 386.861, 536.815, 388.497);
((GeneralPath)shape).curveTo(536.815, 390.131, 538.14, 391.457, 539.775, 391.457);
((GeneralPath)shape).curveTo(541.409, 391.457, 542.736, 390.131, 542.736, 388.497);
((GeneralPath)shape).curveTo(542.736, 386.861, 541.409, 385.535, 539.775, 385.535);
((GeneralPath)shape).moveTo(539.775, 322.023);
((GeneralPath)shape).curveTo(538.14, 322.023, 536.815, 323.35, 536.815, 324.983);
((GeneralPath)shape).curveTo(536.815, 326.62, 538.14, 327.945, 539.775, 327.945);
((GeneralPath)shape).curveTo(541.409, 327.945, 542.736, 326.62, 542.736, 324.983);
((GeneralPath)shape).curveTo(542.736, 323.351, 541.409, 322.023, 539.775, 322.023);
((GeneralPath)shape).moveTo(539.775, 353.779);
((GeneralPath)shape).curveTo(538.14, 353.779, 536.815, 355.104, 536.815, 356.74);
((GeneralPath)shape).curveTo(536.815, 358.374, 538.14, 359.69998, 539.775, 359.69998);
((GeneralPath)shape).curveTo(541.409, 359.69998, 542.736, 358.374, 542.736, 356.74);
((GeneralPath)shape).curveTo(542.736, 355.104, 541.409, 353.779, 539.775, 353.779);
((GeneralPath)shape).moveTo(539.775, 290.268);
((GeneralPath)shape).curveTo(538.14, 290.268, 536.815, 291.59702, 536.815, 293.229);
((GeneralPath)shape).curveTo(536.815, 294.864, 538.14, 296.189, 539.775, 296.189);
((GeneralPath)shape).curveTo(541.409, 296.189, 542.736, 294.863, 542.736, 293.229);
((GeneralPath)shape).curveTo(542.736, 291.597, 541.409, 290.268, 539.775, 290.268);
((GeneralPath)shape).moveTo(539.775, 226.757);
((GeneralPath)shape).curveTo(538.14, 226.757, 536.815, 228.084, 536.815, 229.718);
((GeneralPath)shape).curveTo(536.815, 231.354, 538.14, 232.68001, 539.775, 232.68001);
((GeneralPath)shape).curveTo(541.409, 232.68001, 542.736, 231.354, 542.736, 229.718);
((GeneralPath)shape).curveTo(542.736, 228.084, 541.409, 226.757, 539.775, 226.757);
((GeneralPath)shape).moveTo(539.775, 258.513);
((GeneralPath)shape).curveTo(538.14, 258.513, 536.815, 259.841, 536.815, 261.474);
((GeneralPath)shape).curveTo(536.815, 263.111, 538.14, 264.434, 539.775, 264.434);
((GeneralPath)shape).curveTo(541.409, 264.434, 542.736, 263.11, 542.736, 261.474);
((GeneralPath)shape).curveTo(542.736, 259.841, 541.409, 258.513, 539.775, 258.513);
((GeneralPath)shape).moveTo(539.775, 195.001);
((GeneralPath)shape).curveTo(538.14, 195.001, 536.815, 196.32901, 536.815, 197.96301);
((GeneralPath)shape).curveTo(536.815, 199.59702, 538.14, 200.92302, 539.775, 200.92302);
((GeneralPath)shape).curveTo(541.409, 200.92302, 542.736, 199.59702, 542.736, 197.96301);
((GeneralPath)shape).curveTo(542.736, 196.32901, 541.409, 195.001, 539.775, 195.001);
((GeneralPath)shape).moveTo(548.528, 449.045);
((GeneralPath)shape).curveTo(546.893, 449.045, 545.568, 450.371, 545.568, 452.00702);
((GeneralPath)shape).curveTo(545.568, 453.64102, 546.893, 454.967, 548.528, 454.967);
((GeneralPath)shape).curveTo(550.161, 454.967, 551.48804, 453.64102, 551.48804, 452.00702);
((GeneralPath)shape).curveTo(551.488, 450.371, 550.161, 449.045, 548.528, 449.045);
((GeneralPath)shape).moveTo(548.528, 478.784);
((GeneralPath)shape).curveTo(546.893, 478.784, 545.568, 477.46, 545.568, 475.824);
((GeneralPath)shape).curveTo(545.568, 474.189, 546.893, 472.863, 548.528, 472.863);
((GeneralPath)shape).curveTo(550.161, 472.863, 551.48804, 474.189, 551.48804, 475.824);
((GeneralPath)shape).curveTo(551.488, 477.46, 550.161, 478.784, 548.528, 478.784);
((GeneralPath)shape).moveTo(548.528, 417.292);
((GeneralPath)shape).curveTo(546.893, 417.292, 545.568, 418.617, 545.568, 420.25198);
((GeneralPath)shape).curveTo(545.568, 421.88797, 546.893, 423.214, 548.528, 423.214);
((GeneralPath)shape).curveTo(550.161, 423.214, 551.48804, 421.888, 551.48804, 420.25198);
((GeneralPath)shape).curveTo(551.488, 418.617, 550.161, 417.292, 548.528, 417.292);
((GeneralPath)shape).moveTo(548.528, 385.535);
((GeneralPath)shape).curveTo(546.893, 385.535, 545.568, 386.861, 545.568, 388.497);
((GeneralPath)shape).curveTo(545.568, 390.131, 546.893, 391.457, 548.528, 391.457);
((GeneralPath)shape).curveTo(550.161, 391.457, 551.48804, 390.131, 551.48804, 388.497);
((GeneralPath)shape).curveTo(551.488, 386.861, 550.161, 385.535, 548.528, 385.535);
((GeneralPath)shape).moveTo(548.528, 322.023);
((GeneralPath)shape).curveTo(546.893, 322.023, 545.568, 323.35, 545.568, 324.983);
((GeneralPath)shape).curveTo(545.568, 326.62, 546.893, 327.945, 548.528, 327.945);
((GeneralPath)shape).curveTo(550.161, 327.945, 551.48804, 326.62, 551.48804, 324.983);
((GeneralPath)shape).curveTo(551.488, 323.351, 550.161, 322.023, 548.528, 322.023);
((GeneralPath)shape).moveTo(548.528, 353.779);
((GeneralPath)shape).curveTo(546.893, 353.779, 545.568, 355.104, 545.568, 356.74);
((GeneralPath)shape).curveTo(545.568, 358.374, 546.893, 359.69998, 548.528, 359.69998);
((GeneralPath)shape).curveTo(550.161, 359.69998, 551.48804, 358.374, 551.48804, 356.74);
((GeneralPath)shape).curveTo(551.488, 355.104, 550.161, 353.779, 548.528, 353.779);
((GeneralPath)shape).moveTo(548.528, 290.268);
((GeneralPath)shape).curveTo(546.893, 290.268, 545.568, 291.59702, 545.568, 293.229);
((GeneralPath)shape).curveTo(545.568, 294.864, 546.893, 296.189, 548.528, 296.189);
((GeneralPath)shape).curveTo(550.161, 296.189, 551.48804, 294.863, 551.48804, 293.229);
((GeneralPath)shape).curveTo(551.488, 291.597, 550.161, 290.268, 548.528, 290.268);
((GeneralPath)shape).moveTo(548.528, 226.757);
((GeneralPath)shape).curveTo(546.893, 226.757, 545.568, 228.084, 545.568, 229.718);
((GeneralPath)shape).curveTo(545.568, 231.354, 546.893, 232.68001, 548.528, 232.68001);
((GeneralPath)shape).curveTo(550.161, 232.68001, 551.48804, 231.354, 551.48804, 229.718);
((GeneralPath)shape).curveTo(551.488, 228.084, 550.161, 226.757, 548.528, 226.757);
((GeneralPath)shape).moveTo(548.528, 258.513);
((GeneralPath)shape).curveTo(546.893, 258.513, 545.568, 259.841, 545.568, 261.474);
((GeneralPath)shape).curveTo(545.568, 263.111, 546.893, 264.434, 548.528, 264.434);
((GeneralPath)shape).curveTo(550.161, 264.434, 551.48804, 263.11, 551.48804, 261.474);
((GeneralPath)shape).curveTo(551.488, 259.841, 550.161, 258.513, 548.528, 258.513);
((GeneralPath)shape).moveTo(548.528, 195.001);
((GeneralPath)shape).curveTo(546.893, 195.001, 545.568, 196.32901, 545.568, 197.96301);
((GeneralPath)shape).curveTo(545.568, 199.59702, 546.893, 200.92302, 548.528, 200.92302);
((GeneralPath)shape).curveTo(550.161, 200.92302, 551.48804, 199.59702, 551.48804, 197.96301);
((GeneralPath)shape).curveTo(551.48804, 196.32901, 550.161, 195.001, 548.528, 195.001);
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
paint = new Color(67, 67, 68, 255);
stroke = new BasicStroke(0.581f,0,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(566.035, 448.549);
((GeneralPath)shape).curveTo(564.399, 448.549, 563.073, 449.875, 563.073, 451.51102);
((GeneralPath)shape).curveTo(563.073, 453.14603, 564.399, 454.47202, 566.035, 454.47202);
((GeneralPath)shape).curveTo(567.66797, 454.47202, 568.99396, 453.14603, 568.99396, 451.51102);
((GeneralPath)shape).curveTo(568.994, 449.875, 567.668, 448.549, 566.035, 448.549);
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
paint = new Color(67, 67, 68, 255);
stroke = new BasicStroke(0.581f,0,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(566.035, 416.853);
((GeneralPath)shape).curveTo(564.399, 416.853, 563.073, 418.179, 563.073, 419.812);
((GeneralPath)shape).curveTo(563.073, 421.448, 564.399, 422.77402, 566.035, 422.77402);
((GeneralPath)shape).curveTo(567.66797, 422.77402, 568.99396, 421.44803, 568.99396, 419.812);
((GeneralPath)shape).curveTo(568.994, 418.179, 567.668, 416.853, 566.035, 416.853);
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
paint = new Color(67, 67, 68, 255);
stroke = new BasicStroke(0.581f,0,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(566.035, 385.154);
((GeneralPath)shape).curveTo(564.399, 385.154, 563.073, 386.481, 563.073, 388.116);
((GeneralPath)shape).curveTo(563.073, 389.75, 564.399, 391.076, 566.035, 391.076);
((GeneralPath)shape).curveTo(567.66797, 391.076, 568.99396, 389.75, 568.99396, 388.116);
((GeneralPath)shape).curveTo(568.994, 386.481, 567.668, 385.154, 566.035, 385.154);
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
origAlpha = alpha__0_3;
g.setTransform(defaultTransform__0_3);
g.setClip(clip__0_3);
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
        return 537;
    }

    /**
     * Returns the Y of the bounding box of the original SVG image.
     * 
     * @return The Y of the bounding box of the original SVG image.
     */
    public static int getOrigY() {
        return 156;
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

