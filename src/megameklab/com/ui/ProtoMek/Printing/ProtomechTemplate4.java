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

package megameklab.com.ui.ProtoMek.Printing;

import java.awt.*;
import java.awt.geom.*;


/**
 * This class has been automatically generated using <a
 * href="http://englishjavadrinker.blogspot.com/search/label/SVGRoundTrip">SVGRoundTrip</a>.
 */
public class ProtomechTemplate4 {
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
        Area clip = null;        float origAlpha = 1.0f;
        Composite origComposite = ((Graphics2D)g).getComposite();
        if (origComposite instanceof AlphaComposite) {
        	AlphaComposite origAlphaComposite = 
        		(AlphaComposite)origComposite;
        	if (origAlphaComposite.getRule() == AlphaComposite.SRC_OVER) {
        		origAlpha = origAlphaComposite.getAlpha();
        	}
        }        Shape clip_ = g.getClip();
        AffineTransform defaultTransform_ = g.getTransform();
//      is CompositeGraphicsNode
        float alpha__0 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0 = g.getClip();
        AffineTransform defaultTransform__0 = g.getTransform();
        g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, -0.0f, -0.0f));
        clip = new Area(g.getClip());
        clip.intersect(new Area(new Rectangle2D.Double(0.0,0.0,612.0,792.0)));
        g.setClip(clip);
//      _0 is CompositeGraphicsNode
        float alpha__0_0 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_0 = g.getClip();
        AffineTransform defaultTransform__0_0 = g.getTransform();
        
//      _0_0 is ShapeNode
        paint = new Color(199, 200, 201, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(584.796, 592.836);
        ((GeneralPath)shape).lineTo(579.127, 601.339);
        ((GeneralPath)shape).lineTo(136.001, 601.339);
        ((GeneralPath)shape).lineTo(130.332, 592.836);
        ((GeneralPath)shape).lineTo(39.542, 592.836);
        ((GeneralPath)shape).lineTo(33.873, 584.332);
        ((GeneralPath)shape).lineTo(33.873, 480.812);
        ((GeneralPath)shape).lineTo(39.542, 472.308);
        ((GeneralPath)shape).lineTo(126.078, 472.308);
        ((GeneralPath)shape).lineTo(131.748, 480.812);
        ((GeneralPath)shape).lineTo(577.71, 480.812);
        ((GeneralPath)shape).lineTo(584.796, 489.314);
        ((GeneralPath)shape).lineTo(584.796, 592.836);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_0;
        g.setTransform(defaultTransform__0_0);
        g.setClip(clip__0_0);
        float alpha__0_1 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_1 = g.getClip();
        AffineTransform defaultTransform__0_1 = g.getTransform();
        
//      _0_1 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(580.543, 588.583);
        ((GeneralPath)shape).lineTo(574.876, 597.088);
        ((GeneralPath)shape).lineTo(131.748, 597.088);
        ((GeneralPath)shape).lineTo(126.079, 588.583);
        ((GeneralPath)shape).lineTo(35.292, 588.583);
        ((GeneralPath)shape).lineTo(29.622, 580.08);
        ((GeneralPath)shape).lineTo(29.622, 476.56);
        ((GeneralPath)shape).lineTo(35.292, 468.056);
        ((GeneralPath)shape).lineTo(121.828, 468.056);
        ((GeneralPath)shape).lineTo(127.495, 476.56);
        ((GeneralPath)shape).lineTo(573.458, 476.56);
        ((GeneralPath)shape).lineTo(580.543, 485.063);
        ((GeneralPath)shape).lineTo(580.543, 588.583);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_1;
        g.setTransform(defaultTransform__0_1);
        g.setClip(clip__0_1);
        float alpha__0_2 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_2 = g.getClip();
        AffineTransform defaultTransform__0_2 = g.getTransform();
        
//      _0_2 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        stroke = new BasicStroke(2.0f,1,0,10.0f,null,0.0f);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(580.543, 588.583);
        ((GeneralPath)shape).lineTo(574.876, 597.088);
        ((GeneralPath)shape).lineTo(131.748, 597.088);
        ((GeneralPath)shape).lineTo(126.079, 588.583);
        ((GeneralPath)shape).lineTo(35.292, 588.583);
        ((GeneralPath)shape).lineTo(29.621, 580.079);
        ((GeneralPath)shape).lineTo(29.621, 476.56);
        ((GeneralPath)shape).lineTo(35.292, 468.056);
        ((GeneralPath)shape).lineTo(121.828, 468.056);
        ((GeneralPath)shape).lineTo(127.495, 476.56);
        ((GeneralPath)shape).lineTo(573.458, 476.56);
        ((GeneralPath)shape).lineTo(580.543, 485.062);
        ((GeneralPath)shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_2;
        g.setTransform(defaultTransform__0_2);
        g.setClip(clip__0_2);
        float alpha__0_3 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_3 = g.getClip();
        AffineTransform defaultTransform__0_3 = g.getTransform();
        
//      _0_3 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(120.422, 486.48);
        ((GeneralPath)shape).lineTo(36.425, 486.48);
        ((GeneralPath)shape).lineTo(31.039, 478.401);
        ((GeneralPath)shape).lineTo(36.412, 470.322);
        ((GeneralPath)shape).lineTo(120.409, 470.322);
        ((GeneralPath)shape).lineTo(125.796, 478.401);
        ((GeneralPath)shape).lineTo(120.422, 486.48);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_3;
        g.setTransform(defaultTransform__0_3);
        g.setClip(clip__0_3);
        float alpha__0_4 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_4 = g.getClip();
        AffineTransform defaultTransform__0_4 = g.getTransform();
        
//      _0_4 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        stroke = new BasicStroke(1.0f,0,1,4.0f,null,0.0f);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(120.422, 486.48);
        ((GeneralPath)shape).lineTo(36.424, 486.48);
        ((GeneralPath)shape).lineTo(31.039, 478.401);
        ((GeneralPath)shape).lineTo(36.412, 470.322);
        ((GeneralPath)shape).lineTo(120.409, 470.322);
        ((GeneralPath)shape).lineTo(125.796, 478.401);
        ((GeneralPath)shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_4;
        g.setTransform(defaultTransform__0_4);
        g.setClip(clip__0_4);
        float alpha__0_5 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_5 = g.getClip();
        AffineTransform defaultTransform__0_5 = g.getTransform();
        
//      _0_5 is ShapeNode
        paint = new Color(199, 200, 201, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(524.039, 487.05);
        ((GeneralPath)shape).curveTo(521.289, 487.05, 519.06, 489.83798, 519.06, 493.27698);
        ((GeneralPath)shape).curveTo(519.06, 496.71597, 521.289, 499.50198, 524.039, 499.50198);
        ((GeneralPath)shape).curveTo(526.789, 499.50198, 529.019, 496.71597, 529.019, 493.27698);
        ((GeneralPath)shape).curveTo(529.019, 489.83798, 526.789, 487.05, 524.039, 487.05);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_5;
        g.setTransform(defaultTransform__0_5);
        g.setClip(clip__0_5);
        float alpha__0_6 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_6 = g.getClip();
        AffineTransform defaultTransform__0_6 = g.getTransform();
        
//      _0_6 is ShapeNode
        paint = new Color(199, 200, 201, 255);
        stroke = new BasicStroke(0.475f,0,0,4.0f,null,0.0f);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(524.039, 487.05);
        ((GeneralPath)shape).curveTo(521.289, 487.05, 519.06, 489.83798, 519.06, 493.276);
        ((GeneralPath)shape).curveTo(519.06, 496.715, 521.289, 499.502, 524.039, 499.502);
        ((GeneralPath)shape).curveTo(526.789, 499.502, 529.019, 496.716, 529.019, 493.276);
        ((GeneralPath)shape).curveTo(529.02, 489.838, 526.789, 487.05, 524.039, 487.05);
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_6;
        g.setTransform(defaultTransform__0_6);
        g.setClip(clip__0_6);
        float alpha__0_7 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_7 = g.getClip();
        AffineTransform defaultTransform__0_7 = g.getTransform();
        
//      _0_7 is ShapeNode
        paint = new Color(199, 200, 201, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(578.044, 525.598);
        ((GeneralPath)shape).lineTo(577.455, 523.83704);
        ((GeneralPath)shape).lineTo(576.28204, 522.077);
        ((GeneralPath)shape).lineTo(574.22705, 520.021);
        ((GeneralPath)shape).lineTo(572.75903, 518.847);
        ((GeneralPath)shape).lineTo(571.291, 517.37897);
        ((GeneralPath)shape).lineTo(569.82404, 515.326);
        ((GeneralPath)shape).lineTo(567.77106, 512.976);
        ((GeneralPath)shape).lineTo(566.00806, 511.802);
        ((GeneralPath)shape).lineTo(565.42004, 510.04);
        ((GeneralPath)shape).lineTo(564.54205, 507.98502);
        ((GeneralPath)shape).lineTo(562.77905, 506.812);
        ((GeneralPath)shape).lineTo(560.72504, 505.34402);
        ((GeneralPath)shape).lineTo(558.96405, 503.87604);
        ((GeneralPath)shape).lineTo(557.49506, 502.40805);
        ((GeneralPath)shape).lineTo(555.56104, 501.85605);
        ((GeneralPath)shape).lineTo(555.73505, 500.64703);
        ((GeneralPath)shape).lineTo(556.90704, 499.76602);
        ((GeneralPath)shape).lineTo(558.66907, 498.88702);
        ((GeneralPath)shape).lineTo(560.4311, 498.59302);
        ((GeneralPath)shape).lineTo(561.3121, 496.53702);
        ((GeneralPath)shape).lineTo(562.1921, 494.191);
        ((GeneralPath)shape).lineTo(561.6051, 492.135);
        ((GeneralPath)shape).lineTo(560.4311, 492.427);
        ((GeneralPath)shape).lineTo(560.13806, 493.896);
        ((GeneralPath)shape).lineTo(560.13806, 495.07);
        ((GeneralPath)shape).lineTo(558.96405, 495.95);
        ((GeneralPath)shape).lineTo(557.49506, 496.243);
        ((GeneralPath)shape).lineTo(556.6141, 495.657);
        ((GeneralPath)shape).lineTo(556.02606, 494.483);
        ((GeneralPath)shape).lineTo(554.2671, 494.191);
        ((GeneralPath)shape).lineTo(552.50507, 494.483);
        ((GeneralPath)shape).lineTo(550.15607, 494.775);
        ((GeneralPath)shape).lineTo(545.46106, 494.775);
        ((GeneralPath)shape).lineTo(543.405, 495.07);
        ((GeneralPath)shape).lineTo(541.351, 495.657);
        ((GeneralPath)shape).lineTo(540.17804, 496.83102);
        ((GeneralPath)shape).lineTo(537.955, 496.55402);
        ((GeneralPath)shape).lineTo(537.95703, 496.53702);
        ((GeneralPath)shape).lineTo(534.89404, 496.53702);
        ((GeneralPath)shape).lineTo(532.54706, 496.83102);
        ((GeneralPath)shape).lineTo(531.3721, 495.657);
        ((GeneralPath)shape).lineTo(529.3171, 495.07);
        ((GeneralPath)shape).lineTo(527.2621, 494.775);
        ((GeneralPath)shape).lineTo(522.5661, 494.775);
        ((GeneralPath)shape).lineTo(520.2181, 494.483);
        ((GeneralPath)shape).lineTo(518.45605, 494.191);
        ((GeneralPath)shape).lineTo(516.69604, 494.483);
        ((GeneralPath)shape).lineTo(516.1091, 495.657);
        ((GeneralPath)shape).lineTo(515.22705, 496.243);
        ((GeneralPath)shape).lineTo(513.76105, 495.95);
        ((GeneralPath)shape).lineTo(512.585, 495.07);
        ((GeneralPath)shape).lineTo(512.585, 493.9);
        ((GeneralPath)shape).lineTo(512.292, 492.431);
        ((GeneralPath)shape).lineTo(511.119, 492.139);
        ((GeneralPath)shape).lineTo(510.53198, 494.195);
        ((GeneralPath)shape).lineTo(511.41098, 496.54102);
        ((GeneralPath)shape).lineTo(512.292, 498.59702);
        ((GeneralPath)shape).lineTo(514.053, 498.89102);
        ((GeneralPath)shape).lineTo(515.81396, 499.77002);
        ((GeneralPath)shape).lineTo(516.98895, 500.65103);
        ((GeneralPath)shape).lineTo(517.28094, 502.70502);
        ((GeneralPath)shape).lineTo(517.42395, 503.97003);
        ((GeneralPath)shape).lineTo(516.98895, 501.82504);
        ((GeneralPath)shape).lineTo(514.053, 502.41205);
        ((GeneralPath)shape).lineTo(512.58496, 503.88004);
        ((GeneralPath)shape).lineTo(510.82495, 505.34802);
        ((GeneralPath)shape).lineTo(508.76996, 506.816);
        ((GeneralPath)shape).lineTo(507.00897, 507.989);
        ((GeneralPath)shape).lineTo(506.12796, 510.044);
        ((GeneralPath)shape).lineTo(505.54095, 511.806);
        ((GeneralPath)shape).lineTo(503.77795, 512.98);
        ((GeneralPath)shape).lineTo(501.72397, 515.32996);
        ((GeneralPath)shape).lineTo(500.25797, 517.38293);
        ((GeneralPath)shape).lineTo(498.78897, 518.85095);
        ((GeneralPath)shape).lineTo(497.32297, 520.02496);
        ((GeneralPath)shape).lineTo(495.26697, 522.081);
        ((GeneralPath)shape).lineTo(494.09198, 523.841);
        ((GeneralPath)shape).lineTo(493.50598, 525.602);
        ((GeneralPath)shape).lineTo(492.91797, 527.951);
        ((GeneralPath)shape).lineTo(493.21298, 530.00397);
        ((GeneralPath)shape).lineTo(493.79898, 532.35394);
        ((GeneralPath)shape).lineTo(495.26697, 534.1149);
        ((GeneralPath)shape).lineTo(496.73297, 535.87695);
        ((GeneralPath)shape).lineTo(498.49496, 537.34296);
        ((GeneralPath)shape).lineTo(500.25797, 538.51697);
        ((GeneralPath)shape).lineTo(501.43097, 539.98596);
        ((GeneralPath)shape).lineTo(503.77795, 540.57294);
        ((GeneralPath)shape).lineTo(505.24695, 541.74695);
        ((GeneralPath)shape).lineTo(506.71594, 541.74695);
        ((GeneralPath)shape).lineTo(509.06293, 541.16095);
        ((GeneralPath)shape).lineTo(510.2379, 540.57294);
        ((GeneralPath)shape).lineTo(510.2379, 539.3989);
        ((GeneralPath)shape).lineTo(508.76993, 538.8109);
        ((GeneralPath)shape).lineTo(507.30292, 538.22394);
        ((GeneralPath)shape).lineTo(507.30292, 536.1699);
        ((GeneralPath)shape).lineTo(507.88992, 534.9959);
        ((GeneralPath)shape).lineTo(507.30292, 534.1149);
        ((GeneralPath)shape).lineTo(507.0089, 532.64795);
        ((GeneralPath)shape).lineTo(506.7159, 531.178);
        ((GeneralPath)shape).lineTo(507.59592, 531.178);
        ((GeneralPath)shape).lineTo(508.4749, 532.941);
        ((GeneralPath)shape).lineTo(509.06293, 534.70197);
        ((GeneralPath)shape).lineTo(509.94492, 535.28894);
        ((GeneralPath)shape).lineTo(510.53192, 533.52795);
        ((GeneralPath)shape).lineTo(510.53192, 531.7659);
        ((GeneralPath)shape).lineTo(509.6509, 530.0039);
        ((GeneralPath)shape).lineTo(508.4749, 529.1229);
        ((GeneralPath)shape).lineTo(507.0089, 528.5379);
        ((GeneralPath)shape).lineTo(507.0089, 527.0699);
        ((GeneralPath)shape).lineTo(508.18192, 526.4839);
        ((GeneralPath)shape).lineTo(509.94492, 526.1899);
        ((GeneralPath)shape).lineTo(511.99792, 525.60187);
        ((GeneralPath)shape).lineTo(513.4669, 524.7209);
        ((GeneralPath)shape).lineTo(514.9339, 523.5469);
        ((GeneralPath)shape).lineTo(516.1089, 521.78687);
        ((GeneralPath)shape).lineTo(516.9889, 520.9059);
        ((GeneralPath)shape).lineTo(519.3749, 521.1629);
        ((GeneralPath)shape).lineTo(522.4369, 520.8869);
        ((GeneralPath)shape).lineTo(519.60187, 506.8729);
        ((GeneralPath)shape).lineTo(518.16284, 507.40588);
        ((GeneralPath)shape).lineTo(519.33984, 508.8719);
        ((GeneralPath)shape).curveTo(519.33984, 508.8719, 520.80585, 509.7529, 521.68585, 509.7529);
        ((GeneralPath)shape).curveTo(522.56683, 509.7529, 523.4479, 510.3389, 523.4479, 510.3389);
        ((GeneralPath)shape).lineTo(525.79785, 510.9269);
        ((GeneralPath)shape).lineTo(527.55786, 511.51392);
        ((GeneralPath)shape).lineTo(529.02484, 512.6879);
        ((GeneralPath)shape).lineTo(530.49286, 514.44995);
        ((GeneralPath)shape).lineTo(532.2549, 516.50397);
        ((GeneralPath)shape).lineTo(533.7229, 518.85297);
        ((GeneralPath)shape).lineTo(535.1899, 520.027);
        ((GeneralPath)shape).lineTo(537.5379, 520.027);
        ((GeneralPath)shape).lineTo(539.0049, 518.85297);
        ((GeneralPath)shape).lineTo(540.4739, 516.50397);
        ((GeneralPath)shape).lineTo(542.2339, 514.44995);
        ((GeneralPath)shape).lineTo(543.7019, 512.6879);
        ((GeneralPath)shape).lineTo(545.1709, 511.51392);
        ((GeneralPath)shape).lineTo(546.9319, 510.9269);
        ((GeneralPath)shape).lineTo(549.2809, 510.3389);
        ((GeneralPath)shape).curveTo(549.2809, 510.3389, 550.1599, 509.7529, 551.03986, 509.7529);
        ((GeneralPath)shape).curveTo(551.43787, 509.7529, 551.95184, 509.5729, 552.3978, 509.3759);
        ((GeneralPath)shape).lineTo(535.19183, 504.7639);
        ((GeneralPath)shape).lineTo(519.63586, 509.1649);
        ((GeneralPath)shape).lineTo(519.9289, 511.8079);
        ((GeneralPath)shape).lineTo(520.2229, 514.7439);
        ((GeneralPath)shape).lineTo(520.8099, 517.3849);
        ((GeneralPath)shape).lineTo(521.10486, 519.4409);
        ((GeneralPath)shape).lineTo(520.8099, 522.3739);
        ((GeneralPath)shape).lineTo(520.2229, 525.3109);
        ((GeneralPath)shape).lineTo(520.5169, 527.65894);
        ((GeneralPath)shape).lineTo(521.1049, 530.29895);
        ((GeneralPath)shape).lineTo(522.2769, 532.64996);
        ((GeneralPath)shape).lineTo(522.3079, 532.673);
        ((GeneralPath)shape).lineTo(522.16693, 532.59296);
        ((GeneralPath)shape).lineTo(521.57996, 534.94196);
        ((GeneralPath)shape).lineTo(521.10394, 538.51794);
        ((GeneralPath)shape).lineTo(521.39496, 541.45496);
        ((GeneralPath)shape).lineTo(521.39496, 544.389);
        ((GeneralPath)shape).lineTo(519.04694, 547.326);
        ((GeneralPath)shape).lineTo(517.2849, 550.84796);
        ((GeneralPath)shape).lineTo(515.2309, 553.78296);
        ((GeneralPath)shape).lineTo(512.2959, 558.47894);
        ((GeneralPath)shape).lineTo(512.0019, 560.23895);
        ((GeneralPath)shape).lineTo(510.5359, 561.41595);
        ((GeneralPath)shape).lineTo(509.65488, 562.881);
        ((GeneralPath)shape).lineTo(510.24188, 565.818);
        ((GeneralPath)shape).lineTo(510.24188, 570.80597);
        ((GeneralPath)shape).lineTo(510.5359, 572.568);
        ((GeneralPath)shape).lineTo(511.1229, 574.623);
        ((GeneralPath)shape).lineTo(512.0019, 576.383);
        ((GeneralPath)shape).lineTo(509.3609, 584.013);
        ((GeneralPath)shape).lineTo(507.5999, 585.189);
        ((GeneralPath)shape).lineTo(506.4259, 586.06903);
        ((GeneralPath)shape).lineTo(505.25092, 587.83105);
        ((GeneralPath)shape).lineTo(503.78192, 589.8851);
        ((GeneralPath)shape).lineTo(502.31592, 591.35205);
        ((GeneralPath)shape).lineTo(501.4349, 592.52704);
        ((GeneralPath)shape).lineTo(501.1419, 593.99603);
        ((GeneralPath)shape).lineTo(504.0759, 593.99603);
        ((GeneralPath)shape).lineTo(504.3719, 595.463);
        ((GeneralPath)shape).lineTo(509.3599, 595.463);
        ((GeneralPath)shape).lineTo(511.1219, 595.169);
        ((GeneralPath)shape).lineTo(513.4699, 594.876);
        ((GeneralPath)shape).lineTo(515.8169, 593.996);
        ((GeneralPath)shape).lineTo(517.8739, 593.40796);
        ((GeneralPath)shape).lineTo(519.3419, 592.527);
        ((GeneralPath)shape).lineTo(521.3939, 591.646);
        ((GeneralPath)shape).lineTo(523.44995, 590.765);
        ((GeneralPath)shape).lineTo(525.212, 590.17804);
        ((GeneralPath)shape).lineTo(527.266, 589.59106);
        ((GeneralPath)shape).lineTo(528.733, 589.29706);
        ((GeneralPath)shape).lineTo(530.201, 588.418);
        ((GeneralPath)shape).lineTo(530.789, 586.65704);
        ((GeneralPath)shape).lineTo(530.201, 584.895);
        ((GeneralPath)shape).lineTo(529.027, 582.253);
        ((GeneralPath)shape).lineTo(529.321, 580.786);
        ((GeneralPath)shape).curveTo(529.321, 580.786, 529.321, 579.025, 529.027, 578.144);
        ((GeneralPath)shape).curveTo(528.733, 577.26196, 529.027, 576.08795, 529.027, 576.08795);
        ((GeneralPath)shape).lineTo(528.44, 572.8599);
        ((GeneralPath)shape).lineTo(527.56, 571.6859);
        ((GeneralPath)shape).lineTo(526.68, 570.5109);
        ((GeneralPath)shape).lineTo(526.385, 568.4569);
        ((GeneralPath)shape).lineTo(526.68, 566.9879);
        ((GeneralPath)shape).lineTo(526.972, 564.0539);
        ((GeneralPath)shape).lineTo(527.56, 561.9989);
        ((GeneralPath)shape).lineTo(529.613, 560.2369);
        ((GeneralPath)shape).lineTo(531.67, 558.18085);
        ((GeneralPath)shape).lineTo(532.842, 556.71484);
        ((GeneralPath)shape).lineTo(533.43097, 555.24786);
        ((GeneralPath)shape).lineTo(534.89795, 552.8979);
        ((GeneralPath)shape).lineTo(536.07196, 552.3109);
        ((GeneralPath)shape).lineTo(537.246, 552.8979);
        ((GeneralPath)shape).lineTo(538.71497, 555.24786);
        ((GeneralPath)shape).lineTo(539.30194, 556.71484);
        ((GeneralPath)shape).lineTo(540.47595, 558.18085);
        ((GeneralPath)shape).lineTo(542.532, 560.2369);
        ((GeneralPath)shape).lineTo(544.58496, 561.9989);
        ((GeneralPath)shape).lineTo(545.173, 564.0539);
        ((GeneralPath)shape).lineTo(545.466, 566.9879);
        ((GeneralPath)shape).lineTo(545.761, 568.4569);
        ((GeneralPath)shape).lineTo(545.466, 570.5109);
        ((GeneralPath)shape).lineTo(544.585, 571.6859);
        ((GeneralPath)shape).lineTo(543.70404, 572.8599);
        ((GeneralPath)shape).lineTo(543.11707, 576.08795);
        ((GeneralPath)shape).curveTo(543.11707, 576.08795, 543.4101, 577.26196, 543.11707, 578.144);
        ((GeneralPath)shape).curveTo(542.82306, 579.02496, 542.82306, 580.786, 542.82306, 580.786);
        ((GeneralPath)shape).lineTo(543.11707, 582.253);
        ((GeneralPath)shape).lineTo(541.94305, 584.895);
        ((GeneralPath)shape).lineTo(541.3561, 586.65704);
        ((GeneralPath)shape).lineTo(541.94305, 588.418);
        ((GeneralPath)shape).lineTo(543.41003, 589.29706);
        ((GeneralPath)shape).lineTo(544.879, 589.59106);
        ((GeneralPath)shape).lineTo(546.934, 590.17804);
        ((GeneralPath)shape).lineTo(548.69403, 590.765);
        ((GeneralPath)shape).lineTo(550.75006, 591.646);
        ((GeneralPath)shape).lineTo(552.80505, 592.527);
        ((GeneralPath)shape).lineTo(554.2731, 593.40796);
        ((GeneralPath)shape).lineTo(556.32806, 593.996);
        ((GeneralPath)shape).lineTo(558.67505, 594.876);
        ((GeneralPath)shape).lineTo(561.0231, 595.169);
        ((GeneralPath)shape).lineTo(562.7851, 595.463);
        ((GeneralPath)shape).lineTo(567.7761, 595.463);
        ((GeneralPath)shape).lineTo(568.0681, 593.99603);
        ((GeneralPath)shape).lineTo(571.00214, 593.99603);
        ((GeneralPath)shape).lineTo(570.7091, 592.52704);
        ((GeneralPath)shape).lineTo(569.8291, 591.35205);
        ((GeneralPath)shape).lineTo(568.3611, 589.8851);
        ((GeneralPath)shape).lineTo(566.8941, 587.83105);
        ((GeneralPath)shape).lineTo(565.7191, 586.06903);
        ((GeneralPath)shape).lineTo(564.5471, 585.189);
        ((GeneralPath)shape).lineTo(562.7841, 584.013);
        ((GeneralPath)shape).lineTo(560.1431, 576.383);
        ((GeneralPath)shape).lineTo(561.02216, 574.623);
        ((GeneralPath)shape).lineTo(561.61017, 572.568);
        ((GeneralPath)shape).lineTo(561.9032, 570.80597);
        ((GeneralPath)shape).lineTo(561.9032, 565.818);
        ((GeneralPath)shape).lineTo(562.4892, 562.881);
        ((GeneralPath)shape).lineTo(561.61017, 561.41595);
        ((GeneralPath)shape).lineTo(560.1432, 560.23895);
        ((GeneralPath)shape).lineTo(559.8492, 558.47894);
        ((GeneralPath)shape).lineTo(556.9122, 553.78296);
        ((GeneralPath)shape).lineTo(554.8572, 550.84796);
        ((GeneralPath)shape).lineTo(553.0982, 547.326);
        ((GeneralPath)shape).lineTo(550.7492, 544.389);
        ((GeneralPath)shape).lineTo(550.7492, 541.45496);
        ((GeneralPath)shape).lineTo(551.0412, 538.51794);
        ((GeneralPath)shape).lineTo(550.4542, 534.99695);
        ((GeneralPath)shape).lineTo(549.8682, 532.6489);
        ((GeneralPath)shape).lineTo(551.0412, 530.2979);
        ((GeneralPath)shape).lineTo(551.6292, 527.6579);
        ((GeneralPath)shape).lineTo(551.9232, 525.3099);
        ((GeneralPath)shape).lineTo(551.33624, 522.37286);
        ((GeneralPath)shape).lineTo(551.04126, 519.4399);
        ((GeneralPath)shape).lineTo(551.33624, 517.38385);
        ((GeneralPath)shape).lineTo(551.9232, 514.74286);
        ((GeneralPath)shape).lineTo(552.21625, 511.80685);
        ((GeneralPath)shape).lineTo(552.49023, 509.33585);
        ((GeneralPath)shape).curveTo(552.9932, 509.10684, 553.39026, 508.87085, 553.39026, 508.87085);
        ((GeneralPath)shape).lineTo(552.95123, 507.65884);
        ((GeneralPath)shape).lineTo(550.16125, 520.6118);
        ((GeneralPath)shape).lineTo(552.80426, 521.1978);
        ((GeneralPath)shape).lineTo(554.5663, 520.9048);
        ((GeneralPath)shape).lineTo(555.4463, 521.78577);
        ((GeneralPath)shape).lineTo(556.61926, 523.5458);
        ((GeneralPath)shape).lineTo(558.0873, 524.7198);
        ((GeneralPath)shape).lineTo(559.5563, 525.60077);
        ((GeneralPath)shape).lineTo(561.6103, 526.1888);
        ((GeneralPath)shape).lineTo(563.3723, 526.4828);
        ((GeneralPath)shape).lineTo(564.5473, 527.0688);
        ((GeneralPath)shape).lineTo(564.5473, 528.5368);
        ((GeneralPath)shape).lineTo(563.0783, 529.1218);
        ((GeneralPath)shape).lineTo(561.9033, 530.0028);
        ((GeneralPath)shape).lineTo(561.02234, 531.76483);
        ((GeneralPath)shape).lineTo(561.02234, 533.52686);
        ((GeneralPath)shape).lineTo(561.61035, 535.28784);
        ((GeneralPath)shape).lineTo(562.4894, 534.70087);
        ((GeneralPath)shape).lineTo(563.07837, 532.9399);
        ((GeneralPath)shape).lineTo(563.9584, 531.1769);
        ((GeneralPath)shape).lineTo(564.8384, 531.1769);
        ((GeneralPath)shape).lineTo(564.54736, 532.64685);
        ((GeneralPath)shape).lineTo(564.25134, 534.11383);
        ((GeneralPath)shape).lineTo(563.66437, 534.9948);
        ((GeneralPath)shape).lineTo(564.25134, 536.1688);
        ((GeneralPath)shape).lineTo(564.25134, 538.22284);
        ((GeneralPath)shape).lineTo(562.78436, 538.8098);
        ((GeneralPath)shape).lineTo(561.3174, 539.3978);
        ((GeneralPath)shape).lineTo(561.3174, 540.57184);
        ((GeneralPath)shape).lineTo(562.4894, 541.15985);
        ((GeneralPath)shape).lineTo(564.8384, 541.74585);
        ((GeneralPath)shape).lineTo(566.3074, 541.74585);
        ((GeneralPath)shape).lineTo(567.77637, 540.57184);
        ((GeneralPath)shape).lineTo(570.12335, 539.98486);
        ((GeneralPath)shape).lineTo(571.2963, 538.51587);
        ((GeneralPath)shape).lineTo(573.05835, 537.34186);
        ((GeneralPath)shape).lineTo(574.8204, 535.87585);
        ((GeneralPath)shape).lineTo(576.28735, 534.11383);
        ((GeneralPath)shape).lineTo(577.75635, 532.35284);
        ((GeneralPath)shape).lineTo(578.3414, 530.00287);
        ((GeneralPath)shape).lineTo(578.6344, 527.9499);
        ((GeneralPath)shape).lineTo(578.044, 525.598);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_7;
        g.setTransform(defaultTransform__0_7);
        g.setClip(clip__0_7);
        float alpha__0_8 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_8 = g.getClip();
        AffineTransform defaultTransform__0_8 = g.getTransform();
        
//      _0_8 is ShapeNode
        paint = new Color(199, 200, 201, 255);
        stroke = new BasicStroke(0.475f,0,0,4.0f,null,0.0f);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(578.044, 525.598);
        ((GeneralPath)shape).lineTo(577.455, 523.83704);
        ((GeneralPath)shape).lineTo(576.283, 522.077);
        ((GeneralPath)shape).lineTo(574.227, 520.021);
        ((GeneralPath)shape).lineTo(572.759, 518.847);
        ((GeneralPath)shape).lineTo(571.29095, 517.37897);
        ((GeneralPath)shape).lineTo(569.824, 515.326);
        ((GeneralPath)shape).lineTo(567.771, 512.976);
        ((GeneralPath)shape).lineTo(566.008, 511.802);
        ((GeneralPath)shape).lineTo(565.421, 510.04102);
        ((GeneralPath)shape).lineTo(564.542, 507.98502);
        ((GeneralPath)shape).lineTo(562.779, 506.81302);
        ((GeneralPath)shape).lineTo(560.725, 505.34402);
        ((GeneralPath)shape).lineTo(558.964, 503.87604);
        ((GeneralPath)shape).lineTo(557.495, 502.40805);
        ((GeneralPath)shape).lineTo(555.561, 501.85605);
        ((GeneralPath)shape).lineTo(555.73596, 500.64703);
        ((GeneralPath)shape).lineTo(556.907, 499.76602);
        ((GeneralPath)shape).lineTo(558.67, 498.88702);
        ((GeneralPath)shape).lineTo(560.43097, 498.59302);
        ((GeneralPath)shape).lineTo(561.31195, 496.53802);
        ((GeneralPath)shape).lineTo(562.19196, 494.19104);
        ((GeneralPath)shape).lineTo(561.605, 492.13504);
        ((GeneralPath)shape).lineTo(560.43097, 492.42905);
        ((GeneralPath)shape).lineTo(560.13794, 493.89606);
        ((GeneralPath)shape).lineTo(560.13794, 495.07007);
        ((GeneralPath)shape).lineTo(558.9639, 495.95108);
        ((GeneralPath)shape).lineTo(557.49493, 496.24408);
        ((GeneralPath)shape).lineTo(556.61395, 495.65707);
        ((GeneralPath)shape).lineTo(556.02594, 494.48306);
        ((GeneralPath)shape).lineTo(554.26697, 494.19107);
        ((GeneralPath)shape).lineTo(552.50494, 494.48306);
        ((GeneralPath)shape).lineTo(550.15594, 494.77505);
        ((GeneralPath)shape).lineTo(545.46094, 494.77505);
        ((GeneralPath)shape).lineTo(543.4049, 495.07007);
        ((GeneralPath)shape).lineTo(541.3509, 495.65707);
        ((GeneralPath)shape).lineTo(540.1779, 496.8311);
        ((GeneralPath)shape).lineTo(537.9549, 496.55408);
        ((GeneralPath)shape).lineTo(537.9569, 496.5381);
        ((GeneralPath)shape).lineTo(534.8939, 496.5381);
        ((GeneralPath)shape).lineTo(532.54694, 496.8311);
        ((GeneralPath)shape).lineTo(531.37195, 495.65707);
        ((GeneralPath)shape).lineTo(529.31696, 495.07007);
        ((GeneralPath)shape).lineTo(527.2609, 494.77505);
        ((GeneralPath)shape).lineTo(522.5659, 494.77505);
        ((GeneralPath)shape).lineTo(520.2179, 494.48306);
        ((GeneralPath)shape).lineTo(518.4559, 494.19107);
        ((GeneralPath)shape).lineTo(516.69586, 494.48306);
        ((GeneralPath)shape).lineTo(516.1089, 495.65707);
        ((GeneralPath)shape).lineTo(515.22687, 496.24408);
        ((GeneralPath)shape).lineTo(513.7599, 495.95108);
        ((GeneralPath)shape).lineTo(512.5849, 495.07007);
        ((GeneralPath)shape).lineTo(512.5849, 493.9);
        ((GeneralPath)shape).lineTo(512.2919, 492.43298);
        ((GeneralPath)shape).lineTo(511.11887, 492.13898);
        ((GeneralPath)shape).lineTo(510.53186, 494.19498);
        ((GeneralPath)shape).lineTo(511.41086, 496.54196);
        ((GeneralPath)shape).lineTo(512.2919, 498.59695);
        ((GeneralPath)shape).lineTo(514.05286, 498.89096);
        ((GeneralPath)shape).lineTo(515.81384, 499.76996);
        ((GeneralPath)shape).lineTo(516.98883, 500.65097);
        ((GeneralPath)shape).lineTo(517.2808, 502.70596);
        ((GeneralPath)shape).lineTo(517.4238, 503.97098);
        ((GeneralPath)shape).lineTo(516.98883, 501.82498);
        ((GeneralPath)shape).lineTo(514.05286, 502.412);
        ((GeneralPath)shape).lineTo(512.58484, 503.87997);
        ((GeneralPath)shape).lineTo(510.82483, 505.34796);
        ((GeneralPath)shape).lineTo(508.76984, 506.81696);
        ((GeneralPath)shape).lineTo(507.00885, 507.98895);
        ((GeneralPath)shape).lineTo(506.12784, 510.04495);
        ((GeneralPath)shape).lineTo(505.54083, 511.80594);
        ((GeneralPath)shape).lineTo(503.77783, 512.9799);
        ((GeneralPath)shape).lineTo(501.72385, 515.3299);
        ((GeneralPath)shape).lineTo(500.25784, 517.3829);
        ((GeneralPath)shape).lineTo(498.78986, 518.8509);
        ((GeneralPath)shape).lineTo(497.32285, 520.0249);
        ((GeneralPath)shape).lineTo(495.26685, 522.08093);
        ((GeneralPath)shape).lineTo(494.09186, 523.84094);
        ((GeneralPath)shape).lineTo(493.50586, 525.6019);
        ((GeneralPath)shape).lineTo(492.91785, 527.9509);
        ((GeneralPath)shape).lineTo(493.21286, 530.0039);
        ((GeneralPath)shape).lineTo(493.79886, 532.3539);
        ((GeneralPath)shape).lineTo(495.26685, 534.11487);
        ((GeneralPath)shape).lineTo(496.73285, 535.8769);
        ((GeneralPath)shape).lineTo(498.49484, 537.3429);
        ((GeneralPath)shape).lineTo(500.25784, 538.5179);
        ((GeneralPath)shape).lineTo(501.43085, 539.9859);
        ((GeneralPath)shape).lineTo(503.77783, 540.5729);
        ((GeneralPath)shape).lineTo(505.24683, 541.7469);
        ((GeneralPath)shape).lineTo(506.71582, 541.7469);
        ((GeneralPath)shape).lineTo(509.0628, 541.1609);
        ((GeneralPath)shape).lineTo(510.2378, 540.5729);
        ((GeneralPath)shape).lineTo(510.2378, 539.39886);
        ((GeneralPath)shape).lineTo(508.7698, 538.81085);
        ((GeneralPath)shape).lineTo(507.3028, 538.22485);
        ((GeneralPath)shape).lineTo(507.3028, 536.16986);
        ((GeneralPath)shape).lineTo(507.8898, 534.99585);
        ((GeneralPath)shape).lineTo(507.3028, 534.11487);
        ((GeneralPath)shape).lineTo(507.0088, 532.6479);
        ((GeneralPath)shape).lineTo(506.7158, 531.1789);
        ((GeneralPath)shape).lineTo(507.5958, 531.1789);
        ((GeneralPath)shape).lineTo(508.4758, 532.9409);
        ((GeneralPath)shape).lineTo(509.0628, 534.7019);
        ((GeneralPath)shape).lineTo(509.9448, 535.2899);
        ((GeneralPath)shape).lineTo(510.5318, 533.5279);
        ((GeneralPath)shape).lineTo(510.5318, 531.7669);
        ((GeneralPath)shape).lineTo(509.6498, 530.0039);
        ((GeneralPath)shape).lineTo(508.4758, 529.1229);
        ((GeneralPath)shape).lineTo(507.0088, 528.53894);
        ((GeneralPath)shape).lineTo(507.0088, 527.06995);
        ((GeneralPath)shape).lineTo(508.1818, 526.48395);
        ((GeneralPath)shape).lineTo(509.9448, 526.18994);
        ((GeneralPath)shape).lineTo(511.9978, 525.6019);
        ((GeneralPath)shape).lineTo(513.4668, 524.72095);
        ((GeneralPath)shape).lineTo(514.9338, 523.54694);
        ((GeneralPath)shape).lineTo(516.10876, 521.78796);
        ((GeneralPath)shape).lineTo(516.9888, 520.907);
        ((GeneralPath)shape).lineTo(519.37476, 521.164);
        ((GeneralPath)shape).lineTo(522.43677, 520.888);
        ((GeneralPath)shape).lineTo(519.60175, 506.873);
        ((GeneralPath)shape).lineTo(518.1627, 507.40698);
        ((GeneralPath)shape).lineTo(519.3397, 508.87198);
        ((GeneralPath)shape).curveTo(519.3397, 508.87198, 520.8057, 509.753, 521.6857, 509.753);
        ((GeneralPath)shape).curveTo(522.5667, 509.753, 523.4487, 510.34, 523.4487, 510.34);
        ((GeneralPath)shape).lineTo(525.7977, 510.927);
        ((GeneralPath)shape).lineTo(527.55975, 511.514);
        ((GeneralPath)shape).lineTo(529.0248, 512.688);
        ((GeneralPath)shape).lineTo(530.4928, 514.451);
        ((GeneralPath)shape).lineTo(532.2548, 516.50397);
        ((GeneralPath)shape).lineTo(533.72284, 518.85297);
        ((GeneralPath)shape).lineTo(535.1898, 520.027);
        ((GeneralPath)shape).lineTo(537.53784, 520.027);
        ((GeneralPath)shape).lineTo(539.0048, 518.85297);
        ((GeneralPath)shape).lineTo(540.4738, 516.50397);
        ((GeneralPath)shape).lineTo(542.2338, 514.451);
        ((GeneralPath)shape).lineTo(543.70184, 512.688);
        ((GeneralPath)shape).lineTo(545.1718, 511.51398);
        ((GeneralPath)shape).lineTo(546.9318, 510.92697);
        ((GeneralPath)shape).lineTo(549.2808, 510.33997);
        ((GeneralPath)shape).curveTo(549.2808, 510.33997, 550.15985, 509.75296, 551.0398, 509.75296);
        ((GeneralPath)shape).curveTo(551.4378, 509.75296, 551.9528, 509.57297, 552.39777, 509.37595);
        ((GeneralPath)shape).lineTo(535.1918, 504.76395);
        ((GeneralPath)shape).lineTo(519.63574, 509.16495);
        ((GeneralPath)shape).lineTo(519.9288, 511.80795);
        ((GeneralPath)shape).lineTo(520.2228, 514.74396);
        ((GeneralPath)shape).lineTo(520.80975, 517.38495);
        ((GeneralPath)shape).lineTo(521.10474, 519.441);
        ((GeneralPath)shape).lineTo(520.80975, 522.37396);
        ((GeneralPath)shape).lineTo(520.2228, 525.311);
        ((GeneralPath)shape).lineTo(520.5188, 527.66);
        ((GeneralPath)shape).lineTo(521.1048, 530.3);
        ((GeneralPath)shape).lineTo(522.2768, 532.64996);
        ((GeneralPath)shape).lineTo(522.3078, 532.673);
        ((GeneralPath)shape).lineTo(522.1678, 532.59296);
        ((GeneralPath)shape).lineTo(521.5798, 534.94293);
        ((GeneralPath)shape).lineTo(521.10376, 538.5189);
        ((GeneralPath)shape).lineTo(521.3948, 541.4549);
        ((GeneralPath)shape).lineTo(521.3948, 544.3889);
        ((GeneralPath)shape).lineTo(519.04675, 547.3259);
        ((GeneralPath)shape).lineTo(517.2847, 550.8479);
        ((GeneralPath)shape).lineTo(515.2307, 553.7829);
        ((GeneralPath)shape).lineTo(512.2957, 558.4789);
        ((GeneralPath)shape).lineTo(512.0017, 560.2409);
        ((GeneralPath)shape).lineTo(510.5357, 561.4159);
        ((GeneralPath)shape).lineTo(509.65372, 562.8819);
        ((GeneralPath)shape).lineTo(510.24173, 565.8179);
        ((GeneralPath)shape).lineTo(510.24173, 570.80585);
        ((GeneralPath)shape).lineTo(510.53574, 572.5679);
        ((GeneralPath)shape).lineTo(511.12274, 574.6239);
        ((GeneralPath)shape).lineTo(512.0018, 576.3829);
        ((GeneralPath)shape).lineTo(509.36078, 584.01385);
        ((GeneralPath)shape).lineTo(507.5998, 585.18884);
        ((GeneralPath)shape).lineTo(506.42578, 586.06885);
        ((GeneralPath)shape).lineTo(505.2508, 587.8309);
        ((GeneralPath)shape).lineTo(503.7818, 589.8849);
        ((GeneralPath)shape).lineTo(502.3158, 591.3529);
        ((GeneralPath)shape).lineTo(501.43478, 592.5269);
        ((GeneralPath)shape).lineTo(501.14178, 593.9959);
        ((GeneralPath)shape).lineTo(504.07578, 593.9959);
        ((GeneralPath)shape).lineTo(504.37177, 595.4639);
        ((GeneralPath)shape).lineTo(509.35977, 595.4639);
        ((GeneralPath)shape).lineTo(511.12177, 595.1699);
        ((GeneralPath)shape).lineTo(513.4698, 594.8759);
        ((GeneralPath)shape).lineTo(515.8168, 593.9959);
        ((GeneralPath)shape).lineTo(517.8738, 593.4079);
        ((GeneralPath)shape).lineTo(519.3418, 592.5269);
        ((GeneralPath)shape).lineTo(521.3938, 591.64594);
        ((GeneralPath)shape).lineTo(523.4508, 590.76495);
        ((GeneralPath)shape).lineTo(525.2118, 590.178);
        ((GeneralPath)shape).lineTo(527.2648, 589.591);
        ((GeneralPath)shape).lineTo(528.7328, 589.297);
        ((GeneralPath)shape).lineTo(530.2008, 588.41797);
        ((GeneralPath)shape).lineTo(530.7888, 586.657);
        ((GeneralPath)shape).lineTo(530.2008, 584.89496);
        ((GeneralPath)shape).lineTo(529.0268, 582.2529);
        ((GeneralPath)shape).lineTo(529.3208, 580.78595);
        ((GeneralPath)shape).curveTo(529.3208, 580.78595, 529.3208, 579.02496, 529.0268, 578.1439);
        ((GeneralPath)shape).curveTo(528.7328, 577.2629, 529.0268, 576.0879, 529.0268, 576.0879);
        ((GeneralPath)shape).lineTo(528.4398, 572.85986);
        ((GeneralPath)shape).lineTo(527.5618, 571.68585);
        ((GeneralPath)shape).lineTo(526.6798, 570.51184);
        ((GeneralPath)shape).lineTo(526.3848, 568.45685);
        ((GeneralPath)shape).lineTo(526.6798, 566.98785);
        ((GeneralPath)shape).lineTo(526.9718, 564.05383);
        ((GeneralPath)shape).lineTo(527.5618, 561.99884);
        ((GeneralPath)shape).lineTo(529.61285, 560.23883);
        ((GeneralPath)shape).lineTo(531.66986, 558.18085);
        ((GeneralPath)shape).lineTo(532.84186, 556.71484);
        ((GeneralPath)shape).lineTo(533.43085, 555.24786);
        ((GeneralPath)shape).lineTo(534.8978, 552.89886);
        ((GeneralPath)shape).lineTo(536.07184, 552.31085);
        ((GeneralPath)shape).lineTo(537.24585, 552.89886);
        ((GeneralPath)shape).lineTo(538.71484, 555.24786);
        ((GeneralPath)shape).lineTo(539.3018, 556.71484);
        ((GeneralPath)shape).lineTo(540.4758, 558.18085);
        ((GeneralPath)shape).lineTo(542.53186, 560.23883);
        ((GeneralPath)shape).lineTo(544.58484, 561.99884);
        ((GeneralPath)shape).lineTo(545.1738, 564.05383);
        ((GeneralPath)shape).lineTo(545.4658, 566.98785);
        ((GeneralPath)shape).lineTo(545.7608, 568.45685);
        ((GeneralPath)shape).lineTo(545.4658, 570.51184);
        ((GeneralPath)shape).lineTo(544.58484, 571.68585);
        ((GeneralPath)shape).lineTo(543.70386, 572.85986);
        ((GeneralPath)shape).lineTo(543.1169, 576.0879);
        ((GeneralPath)shape).curveTo(543.1169, 576.0879, 543.4099, 577.2629, 543.1169, 578.1439);
        ((GeneralPath)shape).curveTo(542.8229, 579.0249, 542.8229, 580.78595, 542.8229, 580.78595);
        ((GeneralPath)shape).lineTo(543.1169, 582.2529);
        ((GeneralPath)shape).lineTo(541.9429, 584.89496);
        ((GeneralPath)shape).lineTo(541.3559, 586.657);
        ((GeneralPath)shape).lineTo(541.9429, 588.41797);
        ((GeneralPath)shape).lineTo(543.40985, 589.297);
        ((GeneralPath)shape).lineTo(544.87787, 589.591);
        ((GeneralPath)shape).lineTo(546.9339, 590.178);
        ((GeneralPath)shape).lineTo(548.6939, 590.76495);
        ((GeneralPath)shape).lineTo(550.74994, 591.64594);
        ((GeneralPath)shape).lineTo(552.80493, 592.5269);
        ((GeneralPath)shape).lineTo(554.27295, 593.4079);
        ((GeneralPath)shape).lineTo(556.32794, 593.9959);
        ((GeneralPath)shape).lineTo(558.67596, 594.8759);
        ((GeneralPath)shape).lineTo(561.02295, 595.1699);
        ((GeneralPath)shape).lineTo(562.785, 595.4639);
        ((GeneralPath)shape).lineTo(567.776, 595.4639);
        ((GeneralPath)shape).lineTo(568.068, 593.9959);
        ((GeneralPath)shape).lineTo(571.002, 593.9959);
        ((GeneralPath)shape).lineTo(570.709, 592.5269);
        ((GeneralPath)shape).lineTo(569.829, 591.3529);
        ((GeneralPath)shape).lineTo(568.36096, 589.8849);
        ((GeneralPath)shape).lineTo(566.894, 587.8309);
        ((GeneralPath)shape).lineTo(565.719, 586.06885);
        ((GeneralPath)shape).lineTo(564.547, 585.18884);
        ((GeneralPath)shape).lineTo(562.784, 584.01385);
        ((GeneralPath)shape).lineTo(560.143, 576.3829);
        ((GeneralPath)shape).lineTo(561.02203, 574.6239);
        ((GeneralPath)shape).lineTo(561.61005, 572.5679);
        ((GeneralPath)shape).lineTo(561.9031, 570.80585);
        ((GeneralPath)shape).lineTo(561.9031, 565.8179);
        ((GeneralPath)shape).lineTo(562.4891, 562.8819);
        ((GeneralPath)shape).lineTo(561.61005, 561.4159);
        ((GeneralPath)shape).lineTo(560.14307, 560.2409);
        ((GeneralPath)shape).lineTo(559.84906, 558.4789);
        ((GeneralPath)shape).lineTo(556.91205, 553.7829);
        ((GeneralPath)shape).lineTo(554.85706, 550.8479);
        ((GeneralPath)shape).lineTo(553.0981, 547.3259);
        ((GeneralPath)shape).lineTo(550.7491, 544.3889);
        ((GeneralPath)shape).lineTo(550.7491, 541.4549);
        ((GeneralPath)shape).lineTo(551.0411, 538.5189);
        ((GeneralPath)shape).lineTo(550.4541, 534.9979);
        ((GeneralPath)shape).lineTo(549.8681, 532.6499);
        ((GeneralPath)shape).lineTo(551.0411, 530.2999);
        ((GeneralPath)shape).lineTo(551.63007, 527.6599);
        ((GeneralPath)shape).lineTo(551.9231, 525.3109);
        ((GeneralPath)shape).lineTo(551.3361, 522.3739);
        ((GeneralPath)shape).lineTo(551.04114, 519.4409);
        ((GeneralPath)shape).lineTo(551.3361, 517.3849);
        ((GeneralPath)shape).lineTo(551.9231, 514.7439);
        ((GeneralPath)shape).lineTo(552.2161, 511.8079);
        ((GeneralPath)shape).lineTo(552.4901, 509.3379);
        ((GeneralPath)shape).curveTo(552.9931, 509.1099, 553.39014, 508.8719, 553.39014, 508.8719);
        ((GeneralPath)shape).lineTo(552.9511, 507.65988);
        ((GeneralPath)shape).lineTo(550.16113, 520.61285);
        ((GeneralPath)shape).lineTo(552.80414, 521.1998);
        ((GeneralPath)shape).lineTo(554.56616, 520.9068);
        ((GeneralPath)shape).lineTo(555.44617, 521.7878);
        ((GeneralPath)shape).lineTo(556.61914, 523.54675);
        ((GeneralPath)shape).lineTo(558.08716, 524.72076);
        ((GeneralPath)shape).lineTo(559.55615, 525.60175);
        ((GeneralPath)shape).lineTo(561.61017, 526.18976);
        ((GeneralPath)shape).lineTo(563.3722, 526.48376);
        ((GeneralPath)shape).lineTo(564.5472, 527.06976);
        ((GeneralPath)shape).lineTo(564.5472, 528.53876);
        ((GeneralPath)shape).lineTo(563.0782, 529.12274);
        ((GeneralPath)shape).lineTo(561.9032, 530.0037);
        ((GeneralPath)shape).lineTo(561.0222, 531.7667);
        ((GeneralPath)shape).lineTo(561.0222, 533.5277);
        ((GeneralPath)shape).lineTo(561.6102, 535.28973);
        ((GeneralPath)shape).lineTo(562.48926, 534.7017);
        ((GeneralPath)shape).lineTo(563.07825, 532.94073);
        ((GeneralPath)shape).lineTo(563.95825, 531.1787);
        ((GeneralPath)shape).lineTo(564.83826, 531.1787);
        ((GeneralPath)shape).lineTo(564.54724, 532.6477);
        ((GeneralPath)shape).lineTo(564.2512, 534.1147);
        ((GeneralPath)shape).lineTo(563.66425, 534.99567);
        ((GeneralPath)shape).lineTo(564.2512, 536.1697);
        ((GeneralPath)shape).lineTo(564.2512, 538.2247);
        ((GeneralPath)shape).lineTo(562.78424, 538.81067);
        ((GeneralPath)shape).lineTo(561.31726, 539.3987);
        ((GeneralPath)shape).lineTo(561.31726, 540.5727);
        ((GeneralPath)shape).lineTo(562.48926, 541.1607);
        ((GeneralPath)shape).lineTo(564.83826, 541.7467);
        ((GeneralPath)shape).lineTo(566.30725, 541.7467);
        ((GeneralPath)shape).lineTo(567.77625, 540.5727);
        ((GeneralPath)shape).lineTo(570.1232, 539.9857);
        ((GeneralPath)shape).lineTo(571.2962, 538.5177);
        ((GeneralPath)shape).lineTo(573.0582, 537.3427);
        ((GeneralPath)shape).lineTo(574.82025, 535.8767);
        ((GeneralPath)shape).lineTo(576.28827, 534.1147);
        ((GeneralPath)shape).lineTo(577.7563, 532.3537);
        ((GeneralPath)shape).lineTo(578.3413, 530.0037);
        ((GeneralPath)shape).lineTo(578.63434, 527.95074);
        ((GeneralPath)shape).lineTo(578.044, 525.598);
        ((GeneralPath)shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_8;
        g.setTransform(defaultTransform__0_8);
        g.setClip(clip__0_8);
        float alpha__0_9 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_9 = g.getClip();
        AffineTransform defaultTransform__0_9 = g.getTransform();
        
//      _0_9 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(569.825, 591.635);
        ((GeneralPath)shape).lineTo(568.946, 590.46);
        ((GeneralPath)shape).lineTo(567.476, 588.99304);
        ((GeneralPath)shape).lineTo(566.01, 586.939);
        ((GeneralPath)shape).lineTo(564.835, 585.17804);
        ((GeneralPath)shape).lineTo(563.66003, 584.29706);
        ((GeneralPath)shape).lineTo(561.9, 583.1221);
        ((GeneralPath)shape).lineTo(559.257, 575.49005);
        ((GeneralPath)shape).lineTo(560.138, 573.7311);
        ((GeneralPath)shape).lineTo(560.725, 571.6761);
        ((GeneralPath)shape).lineTo(561.018, 569.91406);
        ((GeneralPath)shape).lineTo(561.018, 564.9261);
        ((GeneralPath)shape).lineTo(561.607, 561.9891);
        ((GeneralPath)shape).lineTo(560.725, 560.52405);
        ((GeneralPath)shape).lineTo(559.25696, 559.34705);
        ((GeneralPath)shape).lineTo(558.967, 557.58704);
        ((GeneralPath)shape).lineTo(556.02795, 552.89);
        ((GeneralPath)shape).lineTo(553.975, 549.956);
        ((GeneralPath)shape).lineTo(552.21295, 546.434);
        ((GeneralPath)shape).lineTo(549.8649, 543.497);
        ((GeneralPath)shape).lineTo(549.8649, 540.563);
        ((GeneralPath)shape).lineTo(550.15796, 537.627);
        ((GeneralPath)shape).lineTo(549.571, 534.106);
        ((GeneralPath)shape).lineTo(548.984, 531.757);
        ((GeneralPath)shape).lineTo(535.19, 539.682);
        ((GeneralPath)shape).lineTo(521.286, 531.703);
        ((GeneralPath)shape).lineTo(520.698, 534.051);
        ((GeneralPath)shape).lineTo(520.221, 537.62805);
        ((GeneralPath)shape).lineTo(520.515, 540.564);
        ((GeneralPath)shape).lineTo(520.515, 543.49805);
        ((GeneralPath)shape).lineTo(518.16504, 546.43506);
        ((GeneralPath)shape).lineTo(516.405, 549.95703);
        ((GeneralPath)shape).lineTo(514.35004, 552.89105);
        ((GeneralPath)shape).lineTo(511.41403, 557.5881);
        ((GeneralPath)shape).lineTo(511.12204, 559.3481);
        ((GeneralPath)shape).lineTo(509.65405, 560.5251);
        ((GeneralPath)shape).lineTo(508.77405, 561.9901);
        ((GeneralPath)shape).lineTo(509.36005, 564.9271);
        ((GeneralPath)shape).lineTo(509.36005, 569.9151);
        ((GeneralPath)shape).lineTo(509.65405, 571.6771);
        ((GeneralPath)shape).lineTo(510.24106, 573.7321);
        ((GeneralPath)shape).lineTo(511.12207, 575.4911);
        ((GeneralPath)shape).lineTo(508.47906, 583.1231);
        ((GeneralPath)shape).lineTo(506.71808, 584.2981);
        ((GeneralPath)shape).lineTo(505.54608, 585.1791);
        ((GeneralPath)shape).lineTo(504.3711, 586.94006);
        ((GeneralPath)shape).lineTo(502.9021, 588.9941);
        ((GeneralPath)shape).lineTo(501.4351, 590.46106);
        ((GeneralPath)shape).lineTo(500.5531, 591.63605);
        ((GeneralPath)shape).lineTo(500.2601, 593.10406);
        ((GeneralPath)shape).lineTo(503.1941, 593.10406);
        ((GeneralPath)shape).lineTo(503.4881, 594.5721);
        ((GeneralPath)shape).lineTo(508.4781, 594.5721);
        ((GeneralPath)shape).lineTo(510.24008, 594.27905);
        ((GeneralPath)shape).lineTo(512.5871, 593.986);
        ((GeneralPath)shape).lineTo(514.9361, 593.104);
        ((GeneralPath)shape).lineTo(516.9911, 592.517);
        ((GeneralPath)shape).lineTo(518.45807, 591.63605);
        ((GeneralPath)shape).lineTo(520.5141, 590.75507);
        ((GeneralPath)shape).lineTo(522.5691, 589.87506);
        ((GeneralPath)shape).lineTo(524.3311, 589.28705);
        ((GeneralPath)shape).lineTo(526.3841, 588.7001);
        ((GeneralPath)shape).lineTo(527.8521, 588.40607);
        ((GeneralPath)shape).lineTo(529.3201, 587.52704);
        ((GeneralPath)shape).lineTo(529.90814, 585.76605);
        ((GeneralPath)shape).lineTo(529.3201, 584.00305);
        ((GeneralPath)shape).lineTo(528.1461, 581.36304);
        ((GeneralPath)shape).lineTo(528.43915, 579.89307);
        ((GeneralPath)shape).curveTo(528.43915, 579.89307, 528.43915, 578.1341, 528.1461, 577.2521);
        ((GeneralPath)shape).curveTo(527.8521, 576.3721, 528.1461, 575.1971, 528.1461, 575.1971);
        ((GeneralPath)shape).lineTo(527.55914, 571.96906);
        ((GeneralPath)shape).lineTo(526.67914, 570.79504);
        ((GeneralPath)shape).lineTo(525.79913, 569.62006);
        ((GeneralPath)shape).lineTo(525.50415, 567.56604);
        ((GeneralPath)shape).lineTo(525.79913, 566.09906);
        ((GeneralPath)shape).lineTo(526.09216, 563.1631);
        ((GeneralPath)shape).lineTo(526.67914, 561.1081);
        ((GeneralPath)shape).lineTo(528.7321, 559.34607);
        ((GeneralPath)shape).lineTo(530.7891, 557.29205);
        ((GeneralPath)shape).lineTo(531.9621, 555.82404);
        ((GeneralPath)shape).lineTo(532.5501, 554.35706);
        ((GeneralPath)shape).lineTo(534.0161, 552.0071);
        ((GeneralPath)shape).lineTo(535.1911, 551.4201);
        ((GeneralPath)shape).lineTo(536.3651, 552.0071);
        ((GeneralPath)shape).lineTo(537.8331, 554.35706);
        ((GeneralPath)shape).lineTo(538.4201, 555.82404);
        ((GeneralPath)shape).lineTo(539.5951, 557.29205);
        ((GeneralPath)shape).lineTo(541.6481, 559.34607);
        ((GeneralPath)shape).lineTo(543.7041, 561.1081);
        ((GeneralPath)shape).lineTo(544.2921, 563.1631);
        ((GeneralPath)shape).lineTo(544.58514, 566.09906);
        ((GeneralPath)shape).lineTo(544.8782, 567.56604);
        ((GeneralPath)shape).lineTo(544.58514, 569.62006);
        ((GeneralPath)shape).lineTo(543.70416, 570.79504);
        ((GeneralPath)shape).lineTo(542.82214, 571.96906);
        ((GeneralPath)shape).lineTo(542.23517, 575.1971);
        ((GeneralPath)shape).curveTo(542.23517, 575.1971, 542.5312, 576.3721, 542.23517, 577.2521);
        ((GeneralPath)shape).curveTo(541.94214, 578.1341, 541.94214, 579.89307, 541.94214, 579.89307);
        ((GeneralPath)shape).lineTo(542.23517, 581.36304);
        ((GeneralPath)shape).lineTo(541.06116, 584.00305);
        ((GeneralPath)shape).lineTo(540.47516, 585.76605);
        ((GeneralPath)shape).lineTo(541.06116, 587.52704);
        ((GeneralPath)shape).lineTo(542.5311, 588.40607);
        ((GeneralPath)shape).lineTo(543.9981, 588.7001);
        ((GeneralPath)shape).lineTo(546.0531, 589.28705);
        ((GeneralPath)shape).lineTo(547.8111, 589.87506);
        ((GeneralPath)shape).lineTo(549.8681, 590.75507);
        ((GeneralPath)shape).lineTo(551.9231, 591.63605);
        ((GeneralPath)shape).lineTo(553.3901, 592.517);
        ((GeneralPath)shape).lineTo(555.4461, 593.104);
        ((GeneralPath)shape).lineTo(557.7941, 593.986);
        ((GeneralPath)shape).lineTo(560.14215, 594.27905);
        ((GeneralPath)shape).lineTo(561.9042, 594.5721);
        ((GeneralPath)shape).lineTo(566.89215, 594.5721);
        ((GeneralPath)shape).lineTo(567.1852, 593.10406);
        ((GeneralPath)shape).lineTo(570.1222, 593.10406);
        ((GeneralPath)shape).lineTo(569.825, 591.635);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_9;
        g.setTransform(defaultTransform__0_9);
        g.setClip(clip__0_9);
        float alpha__0_10 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_10 = g.getClip();
        AffineTransform defaultTransform__0_10 = g.getTransform();
        
//      _0_10 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        stroke = new BasicStroke(1.0f,0,0,4.0f,null,0.0f);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(569.825, 591.635);
        ((GeneralPath)shape).lineTo(568.945, 590.461);
        ((GeneralPath)shape).lineTo(567.476, 588.993);
        ((GeneralPath)shape).lineTo(566.01, 586.93896);
        ((GeneralPath)shape).lineTo(564.834, 585.178);
        ((GeneralPath)shape).lineTo(563.66, 584.297);
        ((GeneralPath)shape).lineTo(561.89996, 583.123);
        ((GeneralPath)shape).lineTo(559.25696, 575.49097);
        ((GeneralPath)shape).lineTo(560.13794, 573.732);
        ((GeneralPath)shape).lineTo(560.7249, 571.67596);
        ((GeneralPath)shape).lineTo(561.01794, 569.91394);
        ((GeneralPath)shape).lineTo(561.01794, 564.92596);
        ((GeneralPath)shape).lineTo(561.60693, 561.99);
        ((GeneralPath)shape).lineTo(560.7249, 560.524);
        ((GeneralPath)shape).lineTo(559.2569, 559.349);
        ((GeneralPath)shape).lineTo(558.9669, 557.587);
        ((GeneralPath)shape).lineTo(556.0279, 552.891);
        ((GeneralPath)shape).lineTo(553.9749, 549.956);
        ((GeneralPath)shape).lineTo(552.2129, 546.434);
        ((GeneralPath)shape).lineTo(549.86487, 543.497);
        ((GeneralPath)shape).lineTo(549.86487, 540.563);
        ((GeneralPath)shape).lineTo(550.1579, 537.62897);
        ((GeneralPath)shape).lineTo(549.5709, 534.10596);
        ((GeneralPath)shape).lineTo(548.98395, 531.75793);
        ((GeneralPath)shape).lineTo(535.18994, 539.68195);
        ((GeneralPath)shape).lineTo(521.28595, 531.70294);
        ((GeneralPath)shape).lineTo(520.69696, 534.0529);
        ((GeneralPath)shape).lineTo(520.22095, 537.62994);
        ((GeneralPath)shape).lineTo(520.51495, 540.56396);
        ((GeneralPath)shape).lineTo(520.51495, 543.498);
        ((GeneralPath)shape).lineTo(518.165, 546.435);
        ((GeneralPath)shape).lineTo(516.40497, 549.957);
        ((GeneralPath)shape).lineTo(514.35, 552.89197);
        ((GeneralPath)shape).lineTo(511.41397, 557.58795);
        ((GeneralPath)shape).lineTo(511.12198, 559.35);
        ((GeneralPath)shape).lineTo(509.654, 560.52496);
        ((GeneralPath)shape).lineTo(508.774, 561.99097);
        ((GeneralPath)shape).lineTo(509.36, 564.92694);
        ((GeneralPath)shape).lineTo(509.36, 569.9149);
        ((GeneralPath)shape).lineTo(509.654, 571.67694);
        ((GeneralPath)shape).lineTo(510.241, 573.733);
        ((GeneralPath)shape).lineTo(511.122, 575.49194);
        ((GeneralPath)shape).lineTo(508.479, 583.12396);
        ((GeneralPath)shape).lineTo(506.71802, 584.298);
        ((GeneralPath)shape).lineTo(505.544, 585.17896);
        ((GeneralPath)shape).lineTo(504.371, 586.93994);
        ((GeneralPath)shape).lineTo(502.902, 588.99396);
        ((GeneralPath)shape).lineTo(501.43402, 590.462);
        ((GeneralPath)shape).lineTo(500.553, 591.636);
        ((GeneralPath)shape).lineTo(500.26, 593.104);
        ((GeneralPath)shape).lineTo(503.194, 593.104);
        ((GeneralPath)shape).lineTo(503.488, 594.573);
        ((GeneralPath)shape).lineTo(508.478, 594.573);
        ((GeneralPath)shape).lineTo(510.24, 594.27997);
        ((GeneralPath)shape).lineTo(512.587, 593.98596);
        ((GeneralPath)shape).lineTo(514.936, 593.10394);
        ((GeneralPath)shape).lineTo(516.99097, 592.51697);
        ((GeneralPath)shape).lineTo(518.45795, 591.636);
        ((GeneralPath)shape).lineTo(520.514, 590.756);
        ((GeneralPath)shape).lineTo(522.569, 589.875);
        ((GeneralPath)shape).lineTo(524.331, 589.287);
        ((GeneralPath)shape).lineTo(526.383, 588.7);
        ((GeneralPath)shape).lineTo(527.852, 588.406);
        ((GeneralPath)shape).lineTo(529.32, 587.528);
        ((GeneralPath)shape).lineTo(529.907, 585.766);
        ((GeneralPath)shape).lineTo(529.32, 584.00397);
        ((GeneralPath)shape).lineTo(528.146, 581.36395);
        ((GeneralPath)shape).lineTo(528.439, 579.89496);
        ((GeneralPath)shape).curveTo(528.439, 579.89496, 528.439, 578.134, 528.146, 577.2529);
        ((GeneralPath)shape).curveTo(527.852, 576.3729, 528.146, 575.1969, 528.146, 575.1969);
        ((GeneralPath)shape).lineTo(527.559, 571.9689);
        ((GeneralPath)shape).lineTo(526.679, 570.79486);
        ((GeneralPath)shape).lineTo(525.799, 569.62085);
        ((GeneralPath)shape).lineTo(525.504, 567.56586);
        ((GeneralPath)shape).lineTo(525.799, 566.09985);
        ((GeneralPath)shape).lineTo(526.093, 563.16284);
        ((GeneralPath)shape).lineTo(526.679, 561.10785);
        ((GeneralPath)shape).lineTo(528.73303, 559.34784);
        ((GeneralPath)shape).lineTo(530.78906, 557.2918);
        ((GeneralPath)shape).lineTo(531.96204, 555.8238);
        ((GeneralPath)shape).lineTo(532.55005, 554.3568);
        ((GeneralPath)shape).lineTo(534.01605, 552.0088);
        ((GeneralPath)shape).lineTo(535.19104, 551.4198);
        ((GeneralPath)shape).lineTo(536.36505, 552.0088);
        ((GeneralPath)shape).lineTo(537.83307, 554.3568);
        ((GeneralPath)shape).lineTo(538.42004, 555.8238);
        ((GeneralPath)shape).lineTo(539.59503, 557.2918);
        ((GeneralPath)shape).lineTo(541.648, 559.34784);
        ((GeneralPath)shape).lineTo(543.703, 561.10785);
        ((GeneralPath)shape).lineTo(544.292, 563.16284);
        ((GeneralPath)shape).lineTo(544.585, 566.09985);
        ((GeneralPath)shape).lineTo(544.87805, 567.56586);
        ((GeneralPath)shape).lineTo(544.585, 569.62085);
        ((GeneralPath)shape).lineTo(543.703, 570.79486);
        ((GeneralPath)shape).lineTo(542.822, 571.9689);
        ((GeneralPath)shape).lineTo(542.23505, 575.1969);
        ((GeneralPath)shape).curveTo(542.23505, 575.1969, 542.53107, 576.3729, 542.23505, 577.2529);
        ((GeneralPath)shape).curveTo(541.942, 578.1339, 541.942, 579.89496, 541.942, 579.89496);
        ((GeneralPath)shape).lineTo(542.23505, 581.36395);
        ((GeneralPath)shape).lineTo(541.06104, 584.00397);
        ((GeneralPath)shape).lineTo(540.47504, 585.766);
        ((GeneralPath)shape).lineTo(541.06104, 587.528);
        ((GeneralPath)shape).lineTo(542.531, 588.406);
        ((GeneralPath)shape).lineTo(543.998, 588.7);
        ((GeneralPath)shape).lineTo(546.053, 589.287);
        ((GeneralPath)shape).lineTo(547.811, 589.875);
        ((GeneralPath)shape).lineTo(549.868, 590.756);
        ((GeneralPath)shape).lineTo(551.923, 591.636);
        ((GeneralPath)shape).lineTo(553.38995, 592.51697);
        ((GeneralPath)shape).lineTo(555.446, 593.10394);
        ((GeneralPath)shape).lineTo(557.794, 593.98596);
        ((GeneralPath)shape).lineTo(560.142, 594.27997);
        ((GeneralPath)shape).lineTo(561.90405, 594.573);
        ((GeneralPath)shape).lineTo(566.892, 594.573);
        ((GeneralPath)shape).lineTo(567.18506, 593.104);
        ((GeneralPath)shape).lineTo(570.1221, 593.104);
        ((GeneralPath)shape).lineTo(569.825, 591.635);
        ((GeneralPath)shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_10;
        g.setTransform(defaultTransform__0_10);
        g.setClip(clip__0_10);
        float alpha__0_11 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_11 = g.getClip();
        AffineTransform defaultTransform__0_11 = g.getTransform();
        
//      _0_11 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(550.453, 520.021);
        ((GeneralPath)shape).lineTo(551.921, 520.314);
        ((GeneralPath)shape).lineTo(553.683, 520.021);
        ((GeneralPath)shape).lineTo(554.563, 520.902);
        ((GeneralPath)shape).lineTo(555.738, 522.664);
        ((GeneralPath)shape).lineTo(557.205, 523.837);
        ((GeneralPath)shape).lineTo(558.672, 524.717);
        ((GeneralPath)shape).lineTo(560.727, 525.305);
        ((GeneralPath)shape).lineTo(562.485, 525.598);
        ((GeneralPath)shape).lineTo(563.661, 526.185);
        ((GeneralPath)shape).lineTo(563.661, 527.652);
        ((GeneralPath)shape).lineTo(562.192, 528.24);
        ((GeneralPath)shape).lineTo(561.018, 529.119);
        ((GeneralPath)shape).lineTo(560.141, 530.88);
        ((GeneralPath)shape).lineTo(560.141, 532.642);
        ((GeneralPath)shape).lineTo(560.727, 534.402);
        ((GeneralPath)shape).lineTo(561.607, 533.816);
        ((GeneralPath)shape).lineTo(562.192, 532.055);
        ((GeneralPath)shape).lineTo(563.075, 530.292);
        ((GeneralPath)shape).lineTo(563.955, 530.292);
        ((GeneralPath)shape).lineTo(563.661, 531.761);
        ((GeneralPath)shape).lineTo(563.367, 533.229);
        ((GeneralPath)shape).lineTo(562.781, 534.109);
        ((GeneralPath)shape).lineTo(563.367, 535.283);
        ((GeneralPath)shape).lineTo(563.367, 537.337);
        ((GeneralPath)shape).lineTo(561.9, 537.924);
        ((GeneralPath)shape).lineTo(560.433, 538.511);
        ((GeneralPath)shape).lineTo(560.433, 539.685);
        ((GeneralPath)shape).lineTo(561.607, 540.273);
        ((GeneralPath)shape).lineTo(563.955, 540.86);
        ((GeneralPath)shape).lineTo(565.422, 540.86);
        ((GeneralPath)shape).lineTo(566.891, 539.685);
        ((GeneralPath)shape).lineTo(569.236, 539.098);
        ((GeneralPath)shape).lineTo(570.411, 537.631);
        ((GeneralPath)shape).lineTo(572.175, 536.458);
        ((GeneralPath)shape).lineTo(573.935, 534.99);
        ((GeneralPath)shape).lineTo(575.401, 533.229);
        ((GeneralPath)shape).lineTo(576.867, 531.468);
        ((GeneralPath)shape).lineTo(577.455, 529.119);
        ((GeneralPath)shape).lineTo(577.751, 527.065);
        ((GeneralPath)shape).lineTo(577.163, 524.717);
        ((GeneralPath)shape).lineTo(576.576, 522.958);
        ((GeneralPath)shape).lineTo(575.401, 521.195);
        ((GeneralPath)shape).lineTo(573.348, 519.141);
        ((GeneralPath)shape).lineTo(571.879, 517.967);
        ((GeneralPath)shape).lineTo(570.411, 516.5);
        ((GeneralPath)shape).lineTo(568.945, 514.445);
        ((GeneralPath)shape).lineTo(566.891, 512.098);
        ((GeneralPath)shape).lineTo(565.129, 510.922);
        ((GeneralPath)shape).lineTo(564.544, 509.162);
        ((GeneralPath)shape).lineTo(563.661, 507.106);
        ((GeneralPath)shape).lineTo(561.9, 505.934);
        ((GeneralPath)shape).lineTo(559.846, 504.467);
        ((GeneralPath)shape).lineTo(558.085, 502.999);
        ((GeneralPath)shape).lineTo(556.617, 501.531);
        ((GeneralPath)shape).lineTo(551.669, 501.237);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_11;
        g.setTransform(defaultTransform__0_11);
        g.setClip(clip__0_11);
        float alpha__0_12 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_12 = g.getClip();
        AffineTransform defaultTransform__0_12 = g.getTransform();
        
//      _0_12 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        stroke = new BasicStroke(1.0f,0,0,10.0f,null,0.0f);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(550.453, 520.021);
        ((GeneralPath)shape).lineTo(551.921, 520.314);
        ((GeneralPath)shape).lineTo(553.683, 520.021);
        ((GeneralPath)shape).lineTo(554.563, 520.902);
        ((GeneralPath)shape).lineTo(555.738, 522.664);
        ((GeneralPath)shape).lineTo(557.205, 523.837);
        ((GeneralPath)shape).lineTo(558.672, 524.717);
        ((GeneralPath)shape).lineTo(560.727, 525.305);
        ((GeneralPath)shape).lineTo(562.485, 525.598);
        ((GeneralPath)shape).lineTo(563.661, 526.185);
        ((GeneralPath)shape).lineTo(563.661, 527.652);
        ((GeneralPath)shape).lineTo(562.192, 528.24);
        ((GeneralPath)shape).lineTo(561.018, 529.119);
        ((GeneralPath)shape).lineTo(560.141, 530.88);
        ((GeneralPath)shape).lineTo(560.141, 532.642);
        ((GeneralPath)shape).lineTo(560.727, 534.402);
        ((GeneralPath)shape).lineTo(561.607, 533.816);
        ((GeneralPath)shape).lineTo(562.192, 532.055);
        ((GeneralPath)shape).lineTo(563.075, 530.292);
        ((GeneralPath)shape).lineTo(563.955, 530.292);
        ((GeneralPath)shape).lineTo(563.661, 531.761);
        ((GeneralPath)shape).lineTo(563.367, 533.229);
        ((GeneralPath)shape).lineTo(562.781, 534.109);
        ((GeneralPath)shape).lineTo(563.367, 535.283);
        ((GeneralPath)shape).lineTo(563.367, 537.337);
        ((GeneralPath)shape).lineTo(561.9, 537.924);
        ((GeneralPath)shape).lineTo(560.433, 538.511);
        ((GeneralPath)shape).lineTo(560.433, 539.685);
        ((GeneralPath)shape).lineTo(561.607, 540.273);
        ((GeneralPath)shape).lineTo(563.955, 540.86);
        ((GeneralPath)shape).lineTo(565.422, 540.86);
        ((GeneralPath)shape).lineTo(566.891, 539.685);
        ((GeneralPath)shape).lineTo(569.236, 539.098);
        ((GeneralPath)shape).lineTo(570.411, 537.631);
        ((GeneralPath)shape).lineTo(572.175, 536.458);
        ((GeneralPath)shape).lineTo(573.935, 534.99);
        ((GeneralPath)shape).lineTo(575.401, 533.229);
        ((GeneralPath)shape).lineTo(576.867, 531.468);
        ((GeneralPath)shape).lineTo(577.455, 529.119);
        ((GeneralPath)shape).lineTo(577.751, 527.065);
        ((GeneralPath)shape).lineTo(577.163, 524.717);
        ((GeneralPath)shape).lineTo(576.576, 522.958);
        ((GeneralPath)shape).lineTo(575.401, 521.195);
        ((GeneralPath)shape).lineTo(573.348, 519.141);
        ((GeneralPath)shape).lineTo(571.879, 517.967);
        ((GeneralPath)shape).lineTo(570.411, 516.5);
        ((GeneralPath)shape).lineTo(568.945, 514.445);
        ((GeneralPath)shape).lineTo(566.891, 512.098);
        ((GeneralPath)shape).lineTo(565.129, 510.922);
        ((GeneralPath)shape).lineTo(564.544, 509.162);
        ((GeneralPath)shape).lineTo(563.661, 507.106);
        ((GeneralPath)shape).lineTo(561.9, 505.934);
        ((GeneralPath)shape).lineTo(559.846, 504.467);
        ((GeneralPath)shape).lineTo(558.085, 502.999);
        ((GeneralPath)shape).lineTo(556.617, 501.531);
        ((GeneralPath)shape).lineTo(551.669, 501.237);
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_12;
        g.setTransform(defaultTransform__0_12);
        g.setClip(clip__0_12);
        float alpha__0_13 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_13 = g.getClip();
        AffineTransform defaultTransform__0_13 = g.getTransform();
        
//      _0_13 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        stroke = new BasicStroke(0.475f,0,0,4.0f,null,0.0f);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(550.453, 520.021);
        ((GeneralPath)shape).lineTo(551.921, 520.314);
        ((GeneralPath)shape).lineTo(553.683, 520.021);
        ((GeneralPath)shape).lineTo(554.563, 520.902);
        ((GeneralPath)shape).lineTo(555.738, 522.664);
        ((GeneralPath)shape).lineTo(557.205, 523.837);
        ((GeneralPath)shape).lineTo(558.672, 524.717);
        ((GeneralPath)shape).lineTo(560.727, 525.305);
        ((GeneralPath)shape).lineTo(562.485, 525.598);
        ((GeneralPath)shape).lineTo(563.661, 526.185);
        ((GeneralPath)shape).lineTo(563.661, 527.652);
        ((GeneralPath)shape).lineTo(562.192, 528.24);
        ((GeneralPath)shape).lineTo(561.018, 529.119);
        ((GeneralPath)shape).lineTo(560.141, 530.88);
        ((GeneralPath)shape).lineTo(560.141, 532.642);
        ((GeneralPath)shape).lineTo(560.727, 534.402);
        ((GeneralPath)shape).lineTo(561.607, 533.816);
        ((GeneralPath)shape).lineTo(562.192, 532.055);
        ((GeneralPath)shape).lineTo(563.075, 530.292);
        ((GeneralPath)shape).lineTo(563.955, 530.292);
        ((GeneralPath)shape).lineTo(563.661, 531.761);
        ((GeneralPath)shape).lineTo(563.367, 533.229);
        ((GeneralPath)shape).lineTo(562.781, 534.109);
        ((GeneralPath)shape).lineTo(563.367, 535.283);
        ((GeneralPath)shape).lineTo(563.367, 537.337);
        ((GeneralPath)shape).lineTo(561.9, 537.924);
        ((GeneralPath)shape).lineTo(560.433, 538.511);
        ((GeneralPath)shape).lineTo(560.433, 539.685);
        ((GeneralPath)shape).lineTo(561.607, 540.273);
        ((GeneralPath)shape).lineTo(563.955, 540.86);
        ((GeneralPath)shape).lineTo(565.422, 540.86);
        ((GeneralPath)shape).lineTo(566.891, 539.685);
        ((GeneralPath)shape).lineTo(569.236, 539.098);
        ((GeneralPath)shape).lineTo(570.411, 537.631);
        ((GeneralPath)shape).lineTo(572.175, 536.458);
        ((GeneralPath)shape).lineTo(573.935, 534.99);
        ((GeneralPath)shape).lineTo(575.401, 533.229);
        ((GeneralPath)shape).lineTo(576.867, 531.468);
        ((GeneralPath)shape).lineTo(577.455, 529.119);
        ((GeneralPath)shape).lineTo(577.751, 527.065);
        ((GeneralPath)shape).lineTo(577.163, 524.717);
        ((GeneralPath)shape).lineTo(576.576, 522.958);
        ((GeneralPath)shape).lineTo(575.401, 521.195);
        ((GeneralPath)shape).lineTo(573.348, 519.141);
        ((GeneralPath)shape).lineTo(571.879, 517.967);
        ((GeneralPath)shape).lineTo(570.411, 516.5);
        ((GeneralPath)shape).lineTo(568.945, 514.445);
        ((GeneralPath)shape).lineTo(566.891, 512.098);
        ((GeneralPath)shape).lineTo(565.129, 510.922);
        ((GeneralPath)shape).lineTo(564.544, 509.162);
        ((GeneralPath)shape).lineTo(563.661, 507.106);
        ((GeneralPath)shape).lineTo(561.9, 505.934);
        ((GeneralPath)shape).lineTo(559.846, 504.467);
        ((GeneralPath)shape).lineTo(558.085, 502.999);
        ((GeneralPath)shape).lineTo(556.617, 501.531);
        ((GeneralPath)shape).lineTo(551.669, 501.237);
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_13;
        g.setTransform(defaultTransform__0_13);
        g.setClip(clip__0_13);
        float alpha__0_14 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_14 = g.getClip();
        AffineTransform defaultTransform__0_14 = g.getTransform();
        
//      _0_14 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(519.966, 519.985);
        ((GeneralPath)shape).lineTo(518.5, 520.279);
        ((GeneralPath)shape).lineTo(516.115, 520.021);
        ((GeneralPath)shape).lineTo(515.234, 520.902);
        ((GeneralPath)shape).lineTo(514.059, 522.664);
        ((GeneralPath)shape).lineTo(512.591, 523.839);
        ((GeneralPath)shape).lineTo(511.126, 524.717);
        ((GeneralPath)shape).lineTo(509.07, 525.305);
        ((GeneralPath)shape).lineTo(507.31, 525.598);
        ((GeneralPath)shape).lineTo(506.136, 526.185);
        ((GeneralPath)shape).lineTo(506.136, 527.652);
        ((GeneralPath)shape).lineTo(507.603, 528.24);
        ((GeneralPath)shape).lineTo(508.778, 529.119);
        ((GeneralPath)shape).lineTo(509.657, 530.881);
        ((GeneralPath)shape).lineTo(509.657, 532.642);
        ((GeneralPath)shape).lineTo(509.07, 534.402);
        ((GeneralPath)shape).lineTo(508.189, 533.816);
        ((GeneralPath)shape).lineTo(507.603, 532.055);
        ((GeneralPath)shape).lineTo(506.723, 530.293);
        ((GeneralPath)shape).lineTo(505.842, 530.293);
        ((GeneralPath)shape).lineTo(506.136, 531.761);
        ((GeneralPath)shape).lineTo(506.429, 533.229);
        ((GeneralPath)shape).lineTo(507.016, 534.109);
        ((GeneralPath)shape).lineTo(506.429, 535.284);
        ((GeneralPath)shape).lineTo(506.429, 537.339);
        ((GeneralPath)shape).lineTo(507.896, 537.924);
        ((GeneralPath)shape).lineTo(509.362, 538.511);
        ((GeneralPath)shape).lineTo(509.362, 539.685);
        ((GeneralPath)shape).lineTo(508.189, 540.273);
        ((GeneralPath)shape).lineTo(505.842, 540.86);
        ((GeneralPath)shape).lineTo(504.376, 540.86);
        ((GeneralPath)shape).lineTo(502.905, 539.685);
        ((GeneralPath)shape).lineTo(500.559, 539.098);
        ((GeneralPath)shape).lineTo(499.384, 537.631);
        ((GeneralPath)shape).lineTo(497.623, 536.458);
        ((GeneralPath)shape).lineTo(495.861, 534.99);
        ((GeneralPath)shape).lineTo(494.396, 533.229);
        ((GeneralPath)shape).lineTo(492.927, 531.468);
        ((GeneralPath)shape).lineTo(492.341, 529.119);
        ((GeneralPath)shape).lineTo(492.047, 527.065);
        ((GeneralPath)shape).lineTo(492.634, 524.717);
        ((GeneralPath)shape).lineTo(493.222, 522.958);
        ((GeneralPath)shape).lineTo(494.396, 521.195);
        ((GeneralPath)shape).lineTo(496.448, 519.141);
        ((GeneralPath)shape).lineTo(497.918, 517.967);
        ((GeneralPath)shape).lineTo(499.384, 516.5);
        ((GeneralPath)shape).lineTo(500.853, 514.445);
        ((GeneralPath)shape).lineTo(502.905, 512.098);
        ((GeneralPath)shape).lineTo(504.668, 510.922);
        ((GeneralPath)shape).lineTo(505.255, 509.162);
        ((GeneralPath)shape).lineTo(506.136, 507.106);
        ((GeneralPath)shape).lineTo(507.896, 505.934);
        ((GeneralPath)shape).lineTo(509.951, 504.467);
        ((GeneralPath)shape).lineTo(511.712, 502.999);
        ((GeneralPath)shape).lineTo(513.18, 501.531);
        ((GeneralPath)shape).lineTo(518.757, 500.356);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_14;
        g.setTransform(defaultTransform__0_14);
        g.setClip(clip__0_14);
        float alpha__0_15 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_15 = g.getClip();
        AffineTransform defaultTransform__0_15 = g.getTransform();
        
//      _0_15 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        stroke = new BasicStroke(1.0f,0,0,10.0f,null,0.0f);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(519.966, 519.985);
        ((GeneralPath)shape).lineTo(518.5, 520.279);
        ((GeneralPath)shape).lineTo(516.115, 520.021);
        ((GeneralPath)shape).lineTo(515.234, 520.902);
        ((GeneralPath)shape).lineTo(514.059, 522.664);
        ((GeneralPath)shape).lineTo(512.591, 523.839);
        ((GeneralPath)shape).lineTo(511.126, 524.717);
        ((GeneralPath)shape).lineTo(509.07, 525.305);
        ((GeneralPath)shape).lineTo(507.31, 525.598);
        ((GeneralPath)shape).lineTo(506.136, 526.185);
        ((GeneralPath)shape).lineTo(506.136, 527.652);
        ((GeneralPath)shape).lineTo(507.603, 528.24);
        ((GeneralPath)shape).lineTo(508.778, 529.119);
        ((GeneralPath)shape).lineTo(509.657, 530.881);
        ((GeneralPath)shape).lineTo(509.657, 532.642);
        ((GeneralPath)shape).lineTo(509.07, 534.402);
        ((GeneralPath)shape).lineTo(508.189, 533.816);
        ((GeneralPath)shape).lineTo(507.603, 532.055);
        ((GeneralPath)shape).lineTo(506.723, 530.293);
        ((GeneralPath)shape).lineTo(505.842, 530.293);
        ((GeneralPath)shape).lineTo(506.136, 531.761);
        ((GeneralPath)shape).lineTo(506.429, 533.229);
        ((GeneralPath)shape).lineTo(507.016, 534.109);
        ((GeneralPath)shape).lineTo(506.429, 535.284);
        ((GeneralPath)shape).lineTo(506.429, 537.339);
        ((GeneralPath)shape).lineTo(507.896, 537.924);
        ((GeneralPath)shape).lineTo(509.362, 538.511);
        ((GeneralPath)shape).lineTo(509.362, 539.685);
        ((GeneralPath)shape).lineTo(508.189, 540.273);
        ((GeneralPath)shape).lineTo(505.842, 540.86);
        ((GeneralPath)shape).lineTo(504.376, 540.86);
        ((GeneralPath)shape).lineTo(502.905, 539.685);
        ((GeneralPath)shape).lineTo(500.559, 539.098);
        ((GeneralPath)shape).lineTo(499.384, 537.631);
        ((GeneralPath)shape).lineTo(497.623, 536.458);
        ((GeneralPath)shape).lineTo(495.861, 534.99);
        ((GeneralPath)shape).lineTo(494.396, 533.229);
        ((GeneralPath)shape).lineTo(492.927, 531.468);
        ((GeneralPath)shape).lineTo(492.341, 529.119);
        ((GeneralPath)shape).lineTo(492.047, 527.065);
        ((GeneralPath)shape).lineTo(492.634, 524.717);
        ((GeneralPath)shape).lineTo(493.222, 522.958);
        ((GeneralPath)shape).lineTo(494.396, 521.195);
        ((GeneralPath)shape).lineTo(496.448, 519.141);
        ((GeneralPath)shape).lineTo(497.918, 517.967);
        ((GeneralPath)shape).lineTo(499.384, 516.5);
        ((GeneralPath)shape).lineTo(500.853, 514.445);
        ((GeneralPath)shape).lineTo(502.905, 512.098);
        ((GeneralPath)shape).lineTo(504.668, 510.922);
        ((GeneralPath)shape).lineTo(505.255, 509.162);
        ((GeneralPath)shape).lineTo(506.136, 507.106);
        ((GeneralPath)shape).lineTo(507.896, 505.934);
        ((GeneralPath)shape).lineTo(509.951, 504.467);
        ((GeneralPath)shape).lineTo(511.712, 502.999);
        ((GeneralPath)shape).lineTo(513.18, 501.531);
        ((GeneralPath)shape).lineTo(518.757, 500.356);
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_15;
        g.setTransform(defaultTransform__0_15);
        g.setClip(clip__0_15);
        float alpha__0_16 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_16 = g.getClip();
        AffineTransform defaultTransform__0_16 = g.getTransform();
        
//      _0_16 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        stroke = new BasicStroke(0.475f,0,0,4.0f,null,0.0f);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(519.966, 519.985);
        ((GeneralPath)shape).lineTo(518.5, 520.279);
        ((GeneralPath)shape).lineTo(516.115, 520.021);
        ((GeneralPath)shape).lineTo(515.234, 520.902);
        ((GeneralPath)shape).lineTo(514.059, 522.664);
        ((GeneralPath)shape).lineTo(512.591, 523.839);
        ((GeneralPath)shape).lineTo(511.126, 524.717);
        ((GeneralPath)shape).lineTo(509.07, 525.305);
        ((GeneralPath)shape).lineTo(507.31, 525.598);
        ((GeneralPath)shape).lineTo(506.136, 526.185);
        ((GeneralPath)shape).lineTo(506.136, 527.652);
        ((GeneralPath)shape).lineTo(507.603, 528.24);
        ((GeneralPath)shape).lineTo(508.778, 529.119);
        ((GeneralPath)shape).lineTo(509.657, 530.881);
        ((GeneralPath)shape).lineTo(509.657, 532.642);
        ((GeneralPath)shape).lineTo(509.07, 534.402);
        ((GeneralPath)shape).lineTo(508.189, 533.816);
        ((GeneralPath)shape).lineTo(507.603, 532.055);
        ((GeneralPath)shape).lineTo(506.723, 530.293);
        ((GeneralPath)shape).lineTo(505.842, 530.293);
        ((GeneralPath)shape).lineTo(506.136, 531.761);
        ((GeneralPath)shape).lineTo(506.429, 533.229);
        ((GeneralPath)shape).lineTo(507.016, 534.109);
        ((GeneralPath)shape).lineTo(506.429, 535.284);
        ((GeneralPath)shape).lineTo(506.429, 537.339);
        ((GeneralPath)shape).lineTo(507.896, 537.924);
        ((GeneralPath)shape).lineTo(509.362, 538.511);
        ((GeneralPath)shape).lineTo(509.362, 539.685);
        ((GeneralPath)shape).lineTo(508.189, 540.273);
        ((GeneralPath)shape).lineTo(505.842, 540.86);
        ((GeneralPath)shape).lineTo(504.376, 540.86);
        ((GeneralPath)shape).lineTo(502.905, 539.685);
        ((GeneralPath)shape).lineTo(500.559, 539.098);
        ((GeneralPath)shape).lineTo(499.384, 537.631);
        ((GeneralPath)shape).lineTo(497.623, 536.458);
        ((GeneralPath)shape).lineTo(495.861, 534.99);
        ((GeneralPath)shape).lineTo(494.396, 533.229);
        ((GeneralPath)shape).lineTo(492.927, 531.468);
        ((GeneralPath)shape).lineTo(492.341, 529.119);
        ((GeneralPath)shape).lineTo(492.047, 527.065);
        ((GeneralPath)shape).lineTo(492.634, 524.717);
        ((GeneralPath)shape).lineTo(493.222, 522.958);
        ((GeneralPath)shape).lineTo(494.396, 521.195);
        ((GeneralPath)shape).lineTo(496.448, 519.141);
        ((GeneralPath)shape).lineTo(497.918, 517.967);
        ((GeneralPath)shape).lineTo(499.384, 516.5);
        ((GeneralPath)shape).lineTo(500.853, 514.445);
        ((GeneralPath)shape).lineTo(502.905, 512.098);
        ((GeneralPath)shape).lineTo(504.668, 510.922);
        ((GeneralPath)shape).lineTo(505.255, 509.162);
        ((GeneralPath)shape).lineTo(506.136, 507.106);
        ((GeneralPath)shape).lineTo(507.896, 505.934);
        ((GeneralPath)shape).lineTo(509.951, 504.467);
        ((GeneralPath)shape).lineTo(511.712, 502.999);
        ((GeneralPath)shape).lineTo(513.18, 501.531);
        ((GeneralPath)shape).lineTo(518.757, 500.356);
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_16;
        g.setTransform(defaultTransform__0_16);
        g.setClip(clip__0_16);
        float alpha__0_17 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_17 = g.getClip();
        AffineTransform defaultTransform__0_17 = g.getTransform();
        
//      _0_17 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(535.192, 539.686);
        ((GeneralPath)shape).lineTo(537.541, 539.979);
        ((GeneralPath)shape).lineTo(539.595, 540.273);
        ((GeneralPath)shape).lineTo(541.942, 540.86);
        ((GeneralPath)shape).lineTo(543.41, 541.154);
        ((GeneralPath)shape).lineTo(543.998, 539.686);
        ((GeneralPath)shape).lineTo(543.998, 537.631);
        ((GeneralPath)shape).lineTo(543.41, 535.283);
        ((GeneralPath)shape).lineTo(545.172, 534.402);
        ((GeneralPath)shape).lineTo(546.933, 533.522);
        ((GeneralPath)shape).lineTo(548.986, 531.761);
        ((GeneralPath)shape).lineTo(550.16, 529.414);
        ((GeneralPath)shape).lineTo(550.748, 526.771);
        ((GeneralPath)shape).lineTo(551.041, 524.423);
        ((GeneralPath)shape).lineTo(550.454, 521.489);
        ((GeneralPath)shape).lineTo(550.16, 518.555);
        ((GeneralPath)shape).lineTo(550.454, 516.499);
        ((GeneralPath)shape).lineTo(551.041, 513.856);
        ((GeneralPath)shape).lineTo(551.336, 510.923);
        ((GeneralPath)shape).lineTo(551.629, 508.281);
        ((GeneralPath)shape).lineTo(518.757, 508.281);
        ((GeneralPath)shape).lineTo(519.049, 510.923);
        ((GeneralPath)shape).lineTo(519.345, 513.856);
        ((GeneralPath)shape).lineTo(519.93, 516.499);
        ((GeneralPath)shape).lineTo(520.224, 518.555);
        ((GeneralPath)shape).lineTo(519.93, 521.489);
        ((GeneralPath)shape).lineTo(519.345, 524.423);
        ((GeneralPath)shape).lineTo(519.637, 526.771);
        ((GeneralPath)shape).lineTo(520.224, 529.414);
        ((GeneralPath)shape).lineTo(521.396, 531.761);
        ((GeneralPath)shape).lineTo(523.453, 533.522);
        ((GeneralPath)shape).lineTo(526.974, 535.283);
        ((GeneralPath)shape).lineTo(526.387, 537.631);
        ((GeneralPath)shape).lineTo(526.387, 539.686);
        ((GeneralPath)shape).lineTo(526.974, 541.154);
        ((GeneralPath)shape).lineTo(528.441, 540.86);
        ((GeneralPath)shape).lineTo(530.791, 540.273);
        ((GeneralPath)shape).lineTo(532.843, 539.979);
        ((GeneralPath)shape).lineTo(535.192, 539.686);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_17;
        g.setTransform(defaultTransform__0_17);
        g.setClip(clip__0_17);
        float alpha__0_18 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_18 = g.getClip();
        AffineTransform defaultTransform__0_18 = g.getTransform();
        
//      _0_18 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        stroke = new BasicStroke(1.0f,0,0,4.0f,null,0.0f);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(535.192, 539.686);
        ((GeneralPath)shape).lineTo(537.541, 539.979);
        ((GeneralPath)shape).lineTo(539.595, 540.273);
        ((GeneralPath)shape).lineTo(541.942, 540.86);
        ((GeneralPath)shape).lineTo(543.409, 541.154);
        ((GeneralPath)shape).lineTo(543.997, 539.686);
        ((GeneralPath)shape).lineTo(543.997, 537.631);
        ((GeneralPath)shape).lineTo(543.409, 535.284);
        ((GeneralPath)shape).lineTo(545.172, 534.403);
        ((GeneralPath)shape).lineTo(546.933, 533.522);
        ((GeneralPath)shape).lineTo(548.986, 531.762);
        ((GeneralPath)shape).lineTo(550.16, 529.414);
        ((GeneralPath)shape).lineTo(550.748, 526.771);
        ((GeneralPath)shape).lineTo(551.041, 524.424);
        ((GeneralPath)shape).lineTo(550.453, 521.489);
        ((GeneralPath)shape).lineTo(550.16, 518.555);
        ((GeneralPath)shape).lineTo(550.453, 516.499);
        ((GeneralPath)shape).lineTo(551.041, 513.858);
        ((GeneralPath)shape).lineTo(551.335, 510.923);
        ((GeneralPath)shape).lineTo(551.629, 508.281);
        ((GeneralPath)shape).lineTo(518.757, 508.281);
        ((GeneralPath)shape).lineTo(519.049, 510.923);
        ((GeneralPath)shape).lineTo(519.343, 513.858);
        ((GeneralPath)shape).lineTo(519.93, 516.499);
        ((GeneralPath)shape).lineTo(520.224, 518.555);
        ((GeneralPath)shape).lineTo(519.93, 521.489);
        ((GeneralPath)shape).lineTo(519.343, 524.424);
        ((GeneralPath)shape).lineTo(519.636, 526.771);
        ((GeneralPath)shape).lineTo(520.224, 529.414);
        ((GeneralPath)shape).lineTo(521.396, 531.762);
        ((GeneralPath)shape).lineTo(523.453, 533.522);
        ((GeneralPath)shape).lineTo(526.974, 535.284);
        ((GeneralPath)shape).lineTo(526.386, 537.631);
        ((GeneralPath)shape).lineTo(526.386, 539.686);
        ((GeneralPath)shape).lineTo(526.974, 541.154);
        ((GeneralPath)shape).lineTo(528.441, 540.86);
        ((GeneralPath)shape).lineTo(530.791, 540.273);
        ((GeneralPath)shape).lineTo(532.843, 539.979);
        ((GeneralPath)shape).lineTo(535.192, 539.686);
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_18;
        g.setTransform(defaultTransform__0_18);
        g.setClip(clip__0_18);
        float alpha__0_19 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_19 = g.getClip();
        AffineTransform defaultTransform__0_19 = g.getTransform();
        
//      _0_19 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        stroke = new BasicStroke(0.475f,0,0,4.0f,null,0.0f);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(535.192, 539.686);
        ((GeneralPath)shape).lineTo(537.541, 539.979);
        ((GeneralPath)shape).lineTo(539.595, 540.273);
        ((GeneralPath)shape).lineTo(541.942, 540.86);
        ((GeneralPath)shape).lineTo(543.409, 541.154);
        ((GeneralPath)shape).lineTo(543.997, 539.686);
        ((GeneralPath)shape).lineTo(543.997, 537.631);
        ((GeneralPath)shape).lineTo(543.409, 535.284);
        ((GeneralPath)shape).lineTo(545.172, 534.403);
        ((GeneralPath)shape).lineTo(546.933, 533.522);
        ((GeneralPath)shape).lineTo(548.986, 531.762);
        ((GeneralPath)shape).lineTo(550.16, 529.414);
        ((GeneralPath)shape).lineTo(550.748, 526.771);
        ((GeneralPath)shape).lineTo(551.041, 524.424);
        ((GeneralPath)shape).lineTo(550.453, 521.489);
        ((GeneralPath)shape).lineTo(550.16, 518.555);
        ((GeneralPath)shape).lineTo(550.453, 516.499);
        ((GeneralPath)shape).lineTo(551.041, 513.858);
        ((GeneralPath)shape).lineTo(551.335, 510.923);
        ((GeneralPath)shape).lineTo(551.629, 508.281);
        ((GeneralPath)shape).lineTo(518.757, 508.281);
        ((GeneralPath)shape).lineTo(519.049, 510.923);
        ((GeneralPath)shape).lineTo(519.343, 513.858);
        ((GeneralPath)shape).lineTo(519.93, 516.499);
        ((GeneralPath)shape).lineTo(520.224, 518.555);
        ((GeneralPath)shape).lineTo(519.93, 521.489);
        ((GeneralPath)shape).lineTo(519.343, 524.424);
        ((GeneralPath)shape).lineTo(519.636, 526.771);
        ((GeneralPath)shape).lineTo(520.224, 529.414);
        ((GeneralPath)shape).lineTo(521.396, 531.762);
        ((GeneralPath)shape).lineTo(523.453, 533.522);
        ((GeneralPath)shape).lineTo(526.974, 535.284);
        ((GeneralPath)shape).lineTo(526.386, 537.631);
        ((GeneralPath)shape).lineTo(526.386, 539.686);
        ((GeneralPath)shape).lineTo(526.974, 541.154);
        ((GeneralPath)shape).lineTo(528.441, 540.86);
        ((GeneralPath)shape).lineTo(530.791, 540.273);
        ((GeneralPath)shape).lineTo(532.843, 539.979);
        ((GeneralPath)shape).lineTo(535.192, 539.686);
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_19;
        g.setTransform(defaultTransform__0_19);
        g.setClip(clip__0_19);
        float alpha__0_20 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_20 = g.getClip();
        AffineTransform defaultTransform__0_20 = g.getTransform();
        
//      _0_20 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(560.728, 491.26);
        ((GeneralPath)shape).lineTo(559.554, 491.551);
        ((GeneralPath)shape).lineTo(559.25903, 493.02);
        ((GeneralPath)shape).lineTo(559.25903, 494.19598);
        ((GeneralPath)shape).lineTo(558.085, 495.07397);
        ((GeneralPath)shape).lineTo(556.619, 495.36798);
        ((GeneralPath)shape).lineTo(555.739, 494.77997);
        ((GeneralPath)shape).lineTo(555.15, 493.60696);
        ((GeneralPath)shape).lineTo(553.38904, 493.31296);
        ((GeneralPath)shape).lineTo(551.62805, 493.60696);
        ((GeneralPath)shape).lineTo(549.28107, 493.90097);
        ((GeneralPath)shape).lineTo(544.58307, 493.90097);
        ((GeneralPath)shape).lineTo(542.53107, 494.19598);
        ((GeneralPath)shape).lineTo(540.47406, 494.78);
        ((GeneralPath)shape).lineTo(539.30005, 495.956);
        ((GeneralPath)shape).lineTo(537.0801, 495.679);
        ((GeneralPath)shape).lineTo(537.0801, 495.662);
        ((GeneralPath)shape).lineTo(534.0171, 495.662);
        ((GeneralPath)shape).lineTo(531.6681, 495.956);
        ((GeneralPath)shape).lineTo(530.4931, 494.78);
        ((GeneralPath)shape).lineTo(528.4391, 494.19598);
        ((GeneralPath)shape).lineTo(526.38306, 493.90097);
        ((GeneralPath)shape).lineTo(521.68805, 493.90097);
        ((GeneralPath)shape).lineTo(519.34106, 493.60696);
        ((GeneralPath)shape).lineTo(517.57904, 493.31296);
        ((GeneralPath)shape).lineTo(515.817, 493.60696);
        ((GeneralPath)shape).lineTo(515.23004, 494.77997);
        ((GeneralPath)shape).lineTo(514.35004, 495.36798);
        ((GeneralPath)shape).lineTo(512.88104, 495.07397);
        ((GeneralPath)shape).lineTo(511.70703, 494.19598);
        ((GeneralPath)shape).lineTo(511.70703, 493.02);
        ((GeneralPath)shape).lineTo(511.41302, 491.551);
        ((GeneralPath)shape).lineTo(510.24103, 491.26);
        ((GeneralPath)shape).lineTo(509.65402, 493.31302);
        ((GeneralPath)shape).lineTo(510.53503, 495.66202);
        ((GeneralPath)shape).lineTo(511.41302, 497.716);
        ((GeneralPath)shape).lineTo(513.17505, 498.01102);
        ((GeneralPath)shape).lineTo(514.9371, 498.89203);
        ((GeneralPath)shape).lineTo(516.11206, 499.77103);
        ((GeneralPath)shape).lineTo(516.4051, 501.825);
        ((GeneralPath)shape).lineTo(516.6991, 504.46902);
        ((GeneralPath)shape).lineTo(517.2851, 506.52203);
        ((GeneralPath)shape).lineTo(518.45807, 507.99002);
        ((GeneralPath)shape).curveTo(518.45807, 507.99002, 519.9261, 508.87103, 520.80707, 508.87103);
        ((GeneralPath)shape).curveTo(521.68805, 508.87103, 522.5691, 509.45804, 522.5691, 509.45804);
        ((GeneralPath)shape).lineTo(524.9181, 510.04605);
        ((GeneralPath)shape).lineTo(526.6791, 510.63306);
        ((GeneralPath)shape).lineTo(528.1481, 511.80707);
        ((GeneralPath)shape).lineTo(529.6131, 513.5691);
        ((GeneralPath)shape).lineTo(531.3751, 515.6221);
        ((GeneralPath)shape).lineTo(532.8411, 517.97107);
        ((GeneralPath)shape).lineTo(534.31213, 519.1451);
        ((GeneralPath)shape).lineTo(536.6591, 519.1451);
        ((GeneralPath)shape).lineTo(538.1261, 517.97107);
        ((GeneralPath)shape).lineTo(539.5951, 515.6221);
        ((GeneralPath)shape).lineTo(541.3551, 513.5691);
        ((GeneralPath)shape).lineTo(542.8231, 511.8071);
        ((GeneralPath)shape).lineTo(544.29114, 510.6331);
        ((GeneralPath)shape).lineTo(546.05316, 510.04608);
        ((GeneralPath)shape).lineTo(548.40216, 509.45807);
        ((GeneralPath)shape).curveTo(548.40216, 509.45807, 549.28314, 508.87106, 550.1622, 508.87106);
        ((GeneralPath)shape).curveTo(551.0412, 508.87106, 552.5102, 507.99005, 552.5102, 507.99005);
        ((GeneralPath)shape).lineTo(553.6842, 506.52206);
        ((GeneralPath)shape).lineTo(554.2722, 504.46906);
        ((GeneralPath)shape).lineTo(554.5662, 501.82504);
        ((GeneralPath)shape).lineTo(554.85925, 499.77106);
        ((GeneralPath)shape).lineTo(556.03326, 498.89206);
        ((GeneralPath)shape).lineTo(557.7933, 498.01105);
        ((GeneralPath)shape).lineTo(559.5563, 497.71603);
        ((GeneralPath)shape).lineTo(560.4363, 495.66205);
        ((GeneralPath)shape).lineTo(561.31726, 493.31305);
        ((GeneralPath)shape).lineTo(560.728, 491.26);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_20;
        g.setTransform(defaultTransform__0_20);
        g.setClip(clip__0_20);
        float alpha__0_21 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_21 = g.getClip();
        AffineTransform defaultTransform__0_21 = g.getTransform();
        
//      _0_21 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        stroke = new BasicStroke(1.0f,0,0,10.0f,null,0.0f);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(560.728, 491.26);
        ((GeneralPath)shape).lineTo(559.554, 491.551);
        ((GeneralPath)shape).lineTo(559.25903, 493.02);
        ((GeneralPath)shape).lineTo(559.25903, 494.19598);
        ((GeneralPath)shape).lineTo(558.085, 495.07397);
        ((GeneralPath)shape).lineTo(556.619, 495.36798);
        ((GeneralPath)shape).lineTo(555.739, 494.77997);
        ((GeneralPath)shape).lineTo(555.15, 493.60696);
        ((GeneralPath)shape).lineTo(553.38904, 493.31296);
        ((GeneralPath)shape).lineTo(551.62805, 493.60696);
        ((GeneralPath)shape).lineTo(549.28107, 493.90097);
        ((GeneralPath)shape).lineTo(544.58307, 493.90097);
        ((GeneralPath)shape).lineTo(542.53107, 494.19598);
        ((GeneralPath)shape).lineTo(540.47406, 494.78);
        ((GeneralPath)shape).lineTo(539.30005, 495.956);
        ((GeneralPath)shape).lineTo(537.0801, 495.679);
        ((GeneralPath)shape).lineTo(537.0801, 495.662);
        ((GeneralPath)shape).lineTo(534.0171, 495.662);
        ((GeneralPath)shape).lineTo(531.6681, 495.956);
        ((GeneralPath)shape).lineTo(530.4931, 494.78);
        ((GeneralPath)shape).lineTo(528.4391, 494.19598);
        ((GeneralPath)shape).lineTo(526.38306, 493.90097);
        ((GeneralPath)shape).lineTo(521.68805, 493.90097);
        ((GeneralPath)shape).lineTo(519.34106, 493.60696);
        ((GeneralPath)shape).lineTo(517.57904, 493.31296);
        ((GeneralPath)shape).lineTo(515.817, 493.60696);
        ((GeneralPath)shape).lineTo(515.23004, 494.77997);
        ((GeneralPath)shape).lineTo(514.35004, 495.36798);
        ((GeneralPath)shape).lineTo(512.88104, 495.07397);
        ((GeneralPath)shape).lineTo(511.70703, 494.19598);
        ((GeneralPath)shape).lineTo(511.70703, 493.02);
        ((GeneralPath)shape).lineTo(511.41302, 491.551);
        ((GeneralPath)shape).lineTo(510.24103, 491.26);
        ((GeneralPath)shape).lineTo(509.65402, 493.31302);
        ((GeneralPath)shape).lineTo(510.53503, 495.66202);
        ((GeneralPath)shape).lineTo(511.41302, 497.716);
        ((GeneralPath)shape).lineTo(513.17505, 498.01102);
        ((GeneralPath)shape).lineTo(514.9371, 498.89203);
        ((GeneralPath)shape).lineTo(516.11206, 499.77103);
        ((GeneralPath)shape).lineTo(516.4051, 501.825);
        ((GeneralPath)shape).lineTo(516.6991, 504.46902);
        ((GeneralPath)shape).lineTo(517.2851, 506.52203);
        ((GeneralPath)shape).lineTo(518.45807, 507.99002);
        ((GeneralPath)shape).curveTo(518.45807, 507.99002, 519.9261, 508.87103, 520.80707, 508.87103);
        ((GeneralPath)shape).curveTo(521.68805, 508.87103, 522.5691, 509.45804, 522.5691, 509.45804);
        ((GeneralPath)shape).lineTo(524.9181, 510.04605);
        ((GeneralPath)shape).lineTo(526.6791, 510.63306);
        ((GeneralPath)shape).lineTo(528.1481, 511.80707);
        ((GeneralPath)shape).lineTo(529.6131, 513.5691);
        ((GeneralPath)shape).lineTo(531.3751, 515.6221);
        ((GeneralPath)shape).lineTo(532.8411, 517.97107);
        ((GeneralPath)shape).lineTo(534.31213, 519.1451);
        ((GeneralPath)shape).lineTo(536.6591, 519.1451);
        ((GeneralPath)shape).lineTo(538.1261, 517.97107);
        ((GeneralPath)shape).lineTo(539.5951, 515.6221);
        ((GeneralPath)shape).lineTo(541.3551, 513.5691);
        ((GeneralPath)shape).lineTo(542.8231, 511.8071);
        ((GeneralPath)shape).lineTo(544.29114, 510.6331);
        ((GeneralPath)shape).lineTo(546.05316, 510.04608);
        ((GeneralPath)shape).lineTo(548.40216, 509.45807);
        ((GeneralPath)shape).curveTo(548.40216, 509.45807, 549.28314, 508.87106, 550.1622, 508.87106);
        ((GeneralPath)shape).curveTo(551.0412, 508.87106, 552.5102, 507.99005, 552.5102, 507.99005);
        ((GeneralPath)shape).lineTo(553.6842, 506.52206);
        ((GeneralPath)shape).lineTo(554.2722, 504.46906);
        ((GeneralPath)shape).lineTo(554.5662, 501.82504);
        ((GeneralPath)shape).lineTo(554.85925, 499.77106);
        ((GeneralPath)shape).lineTo(556.03326, 498.89206);
        ((GeneralPath)shape).lineTo(557.7933, 498.01105);
        ((GeneralPath)shape).lineTo(559.5563, 497.71603);
        ((GeneralPath)shape).lineTo(560.4363, 495.66205);
        ((GeneralPath)shape).lineTo(561.31726, 493.31305);
        ((GeneralPath)shape).lineTo(560.728, 491.26);
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_21;
        g.setTransform(defaultTransform__0_21);
        g.setClip(clip__0_21);
        float alpha__0_22 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_22 = g.getClip();
        AffineTransform defaultTransform__0_22 = g.getTransform();
        
//      _0_22 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        stroke = new BasicStroke(0.475f,0,0,4.0f,null,0.0f);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(560.728, 491.26);
        ((GeneralPath)shape).lineTo(559.554, 491.551);
        ((GeneralPath)shape).lineTo(559.25903, 493.02);
        ((GeneralPath)shape).lineTo(559.25903, 494.19598);
        ((GeneralPath)shape).lineTo(558.085, 495.07397);
        ((GeneralPath)shape).lineTo(556.619, 495.36798);
        ((GeneralPath)shape).lineTo(555.739, 494.77997);
        ((GeneralPath)shape).lineTo(555.15, 493.60696);
        ((GeneralPath)shape).lineTo(553.38904, 493.31296);
        ((GeneralPath)shape).lineTo(551.62805, 493.60696);
        ((GeneralPath)shape).lineTo(549.28107, 493.90097);
        ((GeneralPath)shape).lineTo(544.58307, 493.90097);
        ((GeneralPath)shape).lineTo(542.53107, 494.19598);
        ((GeneralPath)shape).lineTo(540.47406, 494.78);
        ((GeneralPath)shape).lineTo(539.30005, 495.956);
        ((GeneralPath)shape).lineTo(537.0801, 495.679);
        ((GeneralPath)shape).lineTo(537.0801, 495.662);
        ((GeneralPath)shape).lineTo(534.0171, 495.662);
        ((GeneralPath)shape).lineTo(531.6681, 495.956);
        ((GeneralPath)shape).lineTo(530.4931, 494.78);
        ((GeneralPath)shape).lineTo(528.4391, 494.19598);
        ((GeneralPath)shape).lineTo(526.38306, 493.90097);
        ((GeneralPath)shape).lineTo(521.68805, 493.90097);
        ((GeneralPath)shape).lineTo(519.34106, 493.60696);
        ((GeneralPath)shape).lineTo(517.57904, 493.31296);
        ((GeneralPath)shape).lineTo(515.817, 493.60696);
        ((GeneralPath)shape).lineTo(515.23004, 494.77997);
        ((GeneralPath)shape).lineTo(514.35004, 495.36798);
        ((GeneralPath)shape).lineTo(512.88104, 495.07397);
        ((GeneralPath)shape).lineTo(511.70703, 494.19598);
        ((GeneralPath)shape).lineTo(511.70703, 493.02);
        ((GeneralPath)shape).lineTo(511.41302, 491.551);
        ((GeneralPath)shape).lineTo(510.24103, 491.26);
        ((GeneralPath)shape).lineTo(509.65402, 493.31302);
        ((GeneralPath)shape).lineTo(510.53503, 495.66202);
        ((GeneralPath)shape).lineTo(511.41302, 497.716);
        ((GeneralPath)shape).lineTo(513.17505, 498.01102);
        ((GeneralPath)shape).lineTo(514.9371, 498.89203);
        ((GeneralPath)shape).lineTo(516.11206, 499.77103);
        ((GeneralPath)shape).lineTo(516.4051, 501.825);
        ((GeneralPath)shape).lineTo(516.6991, 504.46902);
        ((GeneralPath)shape).lineTo(517.2851, 506.52203);
        ((GeneralPath)shape).lineTo(518.45807, 507.99002);
        ((GeneralPath)shape).curveTo(518.45807, 507.99002, 519.9261, 508.87103, 520.80707, 508.87103);
        ((GeneralPath)shape).curveTo(521.68805, 508.87103, 522.5691, 509.45804, 522.5691, 509.45804);
        ((GeneralPath)shape).lineTo(524.9181, 510.04605);
        ((GeneralPath)shape).lineTo(526.6791, 510.63306);
        ((GeneralPath)shape).lineTo(528.1481, 511.80707);
        ((GeneralPath)shape).lineTo(529.6131, 513.5691);
        ((GeneralPath)shape).lineTo(531.3751, 515.6221);
        ((GeneralPath)shape).lineTo(532.8411, 517.97107);
        ((GeneralPath)shape).lineTo(534.31213, 519.1451);
        ((GeneralPath)shape).lineTo(536.6591, 519.1451);
        ((GeneralPath)shape).lineTo(538.1261, 517.97107);
        ((GeneralPath)shape).lineTo(539.5951, 515.6221);
        ((GeneralPath)shape).lineTo(541.3551, 513.5691);
        ((GeneralPath)shape).lineTo(542.8231, 511.8071);
        ((GeneralPath)shape).lineTo(544.29114, 510.6331);
        ((GeneralPath)shape).lineTo(546.05316, 510.04608);
        ((GeneralPath)shape).lineTo(548.40216, 509.45807);
        ((GeneralPath)shape).curveTo(548.40216, 509.45807, 549.28314, 508.87106, 550.1622, 508.87106);
        ((GeneralPath)shape).curveTo(551.0412, 508.87106, 552.5102, 507.99005, 552.5102, 507.99005);
        ((GeneralPath)shape).lineTo(553.6842, 506.52206);
        ((GeneralPath)shape).lineTo(554.2722, 504.46906);
        ((GeneralPath)shape).lineTo(554.5662, 501.82504);
        ((GeneralPath)shape).lineTo(554.85925, 499.77106);
        ((GeneralPath)shape).lineTo(556.03326, 498.89206);
        ((GeneralPath)shape).lineTo(557.7933, 498.01105);
        ((GeneralPath)shape).lineTo(559.5563, 497.71603);
        ((GeneralPath)shape).lineTo(560.4363, 495.66205);
        ((GeneralPath)shape).lineTo(561.31726, 493.31305);
        ((GeneralPath)shape).lineTo(560.728, 491.26);
        ((GeneralPath)shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_22;
        g.setTransform(defaultTransform__0_22);
        g.setClip(clip__0_22);
        float alpha__0_23 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_23 = g.getClip();
        AffineTransform defaultTransform__0_23 = g.getTransform();
        
//      _0_23 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(535.339, 513.71);
        ((GeneralPath)shape).lineTo(533.871, 512.832);
        ((GeneralPath)shape).lineTo(532.402, 512.244);
        ((GeneralPath)shape).lineTo(531.524, 510.481);
        ((GeneralPath)shape).lineTo(530.937, 508.428);
        ((GeneralPath)shape).lineTo(530.351, 506.374);
        ((GeneralPath)shape).lineTo(529.763, 504.025);
        ((GeneralPath)shape).lineTo(529.763, 502.559);
        ((GeneralPath)shape).lineTo(530.644, 500.503);
        ((GeneralPath)shape).lineTo(531.816, 499.035);
        ((GeneralPath)shape).lineTo(533.871, 497.862);
        ((GeneralPath)shape).lineTo(536.806, 497.862);
        ((GeneralPath)shape).lineTo(538.861, 499.035);
        ((GeneralPath)shape).lineTo(540.035, 500.503);
        ((GeneralPath)shape).lineTo(540.916, 502.559);
        ((GeneralPath)shape).lineTo(540.916, 504.025);
        ((GeneralPath)shape).lineTo(540.33, 506.374);
        ((GeneralPath)shape).lineTo(539.742, 508.428);
        ((GeneralPath)shape).lineTo(539.154, 510.481);
        ((GeneralPath)shape).lineTo(538.273, 512.244);
        ((GeneralPath)shape).lineTo(536.806, 512.832);
        ((GeneralPath)shape).lineTo(535.339, 513.71);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_23;
        g.setTransform(defaultTransform__0_23);
        g.setClip(clip__0_23);
        float alpha__0_24 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_24 = g.getClip();
        AffineTransform defaultTransform__0_24 = g.getTransform();
        
//      _0_24 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        stroke = new BasicStroke(1.0f,0,0,4.0f,null,0.0f);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(535.339, 513.71);
        ((GeneralPath)shape).lineTo(533.871, 512.831);
        ((GeneralPath)shape).lineTo(532.402, 512.244);
        ((GeneralPath)shape).lineTo(531.524, 510.481);
        ((GeneralPath)shape).lineTo(530.937, 508.428);
        ((GeneralPath)shape).lineTo(530.351, 506.374);
        ((GeneralPath)shape).lineTo(529.763, 504.025);
        ((GeneralPath)shape).lineTo(529.763, 502.559);
        ((GeneralPath)shape).lineTo(530.644, 500.503);
        ((GeneralPath)shape).lineTo(531.816, 499.035);
        ((GeneralPath)shape).lineTo(533.871, 497.863);
        ((GeneralPath)shape).lineTo(536.806, 497.863);
        ((GeneralPath)shape).lineTo(538.861, 499.035);
        ((GeneralPath)shape).lineTo(540.035, 500.503);
        ((GeneralPath)shape).lineTo(540.916, 502.559);
        ((GeneralPath)shape).lineTo(540.916, 504.025);
        ((GeneralPath)shape).lineTo(540.329, 506.374);
        ((GeneralPath)shape).lineTo(539.742, 508.428);
        ((GeneralPath)shape).lineTo(539.154, 510.481);
        ((GeneralPath)shape).lineTo(538.273, 512.244);
        ((GeneralPath)shape).lineTo(536.806, 512.831);
        ((GeneralPath)shape).lineTo(535.339, 513.71);
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_24;
        g.setTransform(defaultTransform__0_24);
        g.setClip(clip__0_24);
        float alpha__0_25 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_25 = g.getClip();
        AffineTransform defaultTransform__0_25 = g.getTransform();
        
//      _0_25 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(523.159, 486.169);
        ((GeneralPath)shape).curveTo(520.409, 486.169, 518.18, 488.957, 518.18, 492.397);
        ((GeneralPath)shape).curveTo(518.18, 495.835, 520.409, 498.621, 523.159, 498.621);
        ((GeneralPath)shape).curveTo(525.911, 498.621, 528.14, 495.835, 528.14, 492.397);
        ((GeneralPath)shape).curveTo(528.141, 488.957, 525.911, 486.169, 523.159, 486.169);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_25;
        g.setTransform(defaultTransform__0_25);
        g.setClip(clip__0_25);
        float alpha__0_26 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_26 = g.getClip();
        AffineTransform defaultTransform__0_26 = g.getTransform();
        
//      _0_26 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        stroke = new BasicStroke(1.0f,0,0,4.0f,null,0.0f);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(523.159, 486.169);
        ((GeneralPath)shape).curveTo(520.409, 486.169, 518.18, 488.957, 518.18, 492.397);
        ((GeneralPath)shape).curveTo(518.18, 495.835, 520.409, 498.621, 523.159, 498.621);
        ((GeneralPath)shape).curveTo(525.911, 498.621, 528.14, 495.835, 528.14, 492.397);
        ((GeneralPath)shape).curveTo(528.141, 488.957, 525.911, 486.169, 523.159, 486.169);
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_26;
        g.setTransform(defaultTransform__0_26);
        g.setClip(clip__0_26);
        float alpha__0_27 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_27 = g.getClip();
        AffineTransform defaultTransform__0_27 = g.getTransform();
        
//      _0_27 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        stroke = new BasicStroke(0.475f,0,0,4.0f,null,0.0f);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(523.159, 486.169);
        ((GeneralPath)shape).curveTo(520.409, 486.169, 518.18, 488.957, 518.18, 492.397);
        ((GeneralPath)shape).curveTo(518.18, 495.835, 520.409, 498.621, 523.159, 498.621);
        ((GeneralPath)shape).curveTo(525.911, 498.621, 528.14, 495.835, 528.14, 492.397);
        ((GeneralPath)shape).curveTo(528.141, 488.957, 525.911, 486.169, 523.159, 486.169);
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_27;
        g.setTransform(defaultTransform__0_27);
        g.setClip(clip__0_27);
        float alpha__0_28 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_28 = g.getClip();
        AffineTransform defaultTransform__0_28 = g.getTransform();
        
//      _0_28 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(501.849, 482.836);
        ((GeneralPath)shape).lineTo(499.772, 482.836);
        ((GeneralPath)shape).lineTo(500.799, 479.602);
        ((GeneralPath)shape).lineTo(500.815, 479.602);
        ((GeneralPath)shape).lineTo(501.849, 482.836);
        ((GeneralPath)shape).moveTo(502.106, 483.711);
        ((GeneralPath)shape).lineTo(502.5, 484.836);
        ((GeneralPath)shape).lineTo(503.672, 484.836);
        ((GeneralPath)shape).lineTo(501.633, 478.711);
        ((GeneralPath)shape).lineTo(499.931, 478.711);
        ((GeneralPath)shape).lineTo(497.933, 484.836);
        ((GeneralPath)shape).lineTo(499.13, 484.836);
        ((GeneralPath)shape).lineTo(499.506, 483.711);
        ((GeneralPath)shape).lineTo(502.106, 483.711);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_28;
        g.setTransform(defaultTransform__0_28);
        g.setClip(clip__0_28);
        float alpha__0_29 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_29 = g.getClip();
        AffineTransform defaultTransform__0_29 = g.getTransform();
        
//      _0_29 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(505.215, 481.836);
        ((GeneralPath)shape).lineTo(505.215, 479.707);
        ((GeneralPath)shape).lineTo(506.76498, 479.707);
        ((GeneralPath)shape).curveTo(507.563, 479.707, 507.71, 479.865, 507.71, 480.746);
        ((GeneralPath)shape).curveTo(507.71, 481.662, 507.50998, 481.836, 506.688, 481.836);
        ((GeneralPath)shape).lineTo(505.215, 481.836);
        ((GeneralPath)shape).moveTo(506.96, 482.836);
        ((GeneralPath)shape).curveTo(507.50598, 482.836, 507.71, 483.193, 507.71, 483.703);
        ((GeneralPath)shape).lineTo(507.71, 484.836);
        ((GeneralPath)shape).lineTo(508.83298, 484.836);
        ((GeneralPath)shape).lineTo(508.83298, 483.705);
        ((GeneralPath)shape).curveTo(508.83298, 482.84998, 508.63098, 482.404, 507.8, 482.332);
        ((GeneralPath)shape).lineTo(507.8, 482.299);
        ((GeneralPath)shape).curveTo(508.83298, 482.14102, 508.83298, 481.46902, 508.83298, 480.547);
        ((GeneralPath)shape).curveTo(508.83298, 479.135, 508.34396, 478.711, 507.06897, 478.711);
        ((GeneralPath)shape).lineTo(504.09198, 478.711);
        ((GeneralPath)shape).lineTo(504.09198, 484.836);
        ((GeneralPath)shape).lineTo(505.214, 484.836);
        ((GeneralPath)shape).lineTo(505.214, 482.836);
        ((GeneralPath)shape).lineTo(506.96, 482.836);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_29;
        g.setTransform(defaultTransform__0_29);
        g.setClip(clip__0_29);
        float alpha__0_30 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_30 = g.getClip();
        AffineTransform defaultTransform__0_30 = g.getTransform();
        
//      _0_30 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(515.721, 479.666);
        ((GeneralPath)shape).lineTo(515.721, 484.836);
        ((GeneralPath)shape).lineTo(516.844, 484.836);
        ((GeneralPath)shape).lineTo(516.844, 478.711);
        ((GeneralPath)shape).lineTo(514.979, 478.711);
        ((GeneralPath)shape).lineTo(513.362, 483.258);
        ((GeneralPath)shape).lineTo(513.331, 483.258);
        ((GeneralPath)shape).lineTo(511.682, 478.711);
        ((GeneralPath)shape).lineTo(509.857, 478.711);
        ((GeneralPath)shape).lineTo(509.857, 484.836);
        ((GeneralPath)shape).lineTo(510.979, 484.836);
        ((GeneralPath)shape).lineTo(510.979, 479.691);
        ((GeneralPath)shape).lineTo(512.83, 484.836);
        ((GeneralPath)shape).lineTo(513.871, 484.836);
        ((GeneralPath)shape).lineTo(515.721, 479.666);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_30;
        g.setTransform(defaultTransform__0_30);
        g.setClip(clip__0_30);
        float alpha__0_31 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_31 = g.getClip();
        AffineTransform defaultTransform__0_31 = g.getTransform();
        
//      _0_31 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(520.232, 479.707);
        ((GeneralPath)shape).curveTo(521.238, 479.707, 521.64, 479.787, 521.64, 480.91602);
        ((GeneralPath)shape).lineTo(521.64, 482.531);
        ((GeneralPath)shape).curveTo(521.64, 483.691, 521.28503, 483.836, 520.255, 483.836);
        ((GeneralPath)shape).curveTo(519.123, 483.836, 518.895, 483.725, 518.895, 482.531);
        ((GeneralPath)shape).lineTo(518.895, 480.91602);
        ((GeneralPath)shape).curveTo(518.896, 479.963, 519.015, 479.707, 520.232, 479.707);
        ((GeneralPath)shape).moveTo(520.256, 478.711);
        ((GeneralPath)shape).curveTo(518.391, 478.711, 517.773, 479.041, 517.773, 480.914);
        ((GeneralPath)shape).lineTo(517.773, 482.537);
        ((GeneralPath)shape).curveTo(517.773, 484.51398, 518.44, 484.836, 520.256, 484.836);
        ((GeneralPath)shape).curveTo(522.034, 484.836, 522.763, 484.467, 522.763, 482.537);
        ((GeneralPath)shape).lineTo(522.763, 480.914);
        ((GeneralPath)shape).curveTo(522.763, 478.977, 521.948, 478.711, 520.256, 478.711);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_31;
        g.setTransform(defaultTransform__0_31);
        g.setClip(clip__0_31);
        float alpha__0_32 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_32 = g.getClip();
        AffineTransform defaultTransform__0_32 = g.getTransform();
        
//      _0_32 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(524.729, 481.836);
        ((GeneralPath)shape).lineTo(524.729, 479.707);
        ((GeneralPath)shape).lineTo(526.279, 479.707);
        ((GeneralPath)shape).curveTo(527.076, 479.707, 527.224, 479.865, 527.224, 480.746);
        ((GeneralPath)shape).curveTo(527.224, 481.662, 527.023, 481.836, 526.201, 481.836);
        ((GeneralPath)shape).lineTo(524.729, 481.836);
        ((GeneralPath)shape).moveTo(526.474, 482.836);
        ((GeneralPath)shape).curveTo(527.02, 482.836, 527.225, 483.193, 527.225, 483.703);
        ((GeneralPath)shape).lineTo(527.225, 484.836);
        ((GeneralPath)shape).lineTo(528.34796, 484.836);
        ((GeneralPath)shape).lineTo(528.34796, 483.705);
        ((GeneralPath)shape).curveTo(528.34796, 482.84998, 528.14496, 482.404, 527.31494, 482.332);
        ((GeneralPath)shape).lineTo(527.31494, 482.299);
        ((GeneralPath)shape).curveTo(528.34796, 482.14102, 528.34796, 481.46902, 528.34796, 480.547);
        ((GeneralPath)shape).curveTo(528.34796, 479.135, 527.858, 478.711, 526.58295, 478.711);
        ((GeneralPath)shape).lineTo(523.60596, 478.711);
        ((GeneralPath)shape).lineTo(523.60596, 484.836);
        ((GeneralPath)shape).lineTo(524.72894, 484.836);
        ((GeneralPath)shape).lineTo(524.72894, 482.836);
        ((GeneralPath)shape).lineTo(526.474, 482.836);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_32;
        g.setTransform(defaultTransform__0_32);
        g.setClip(clip__0_32);
        float alpha__0_33 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_33 = g.getClip();
        AffineTransform defaultTransform__0_33 = g.getTransform();
        
//      _0_33 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(533.305, 483.84);
        ((GeneralPath)shape).lineTo(533.305, 479.707);
        ((GeneralPath)shape).lineTo(535.061, 479.707);
        ((GeneralPath)shape).curveTo(535.962, 479.707, 536.29895, 479.961, 536.29895, 480.986);
        ((GeneralPath)shape).lineTo(536.29895, 482.43698);
        ((GeneralPath)shape).curveTo(536.29895, 483.12598, 536.053, 483.839, 535.25793, 483.839);
        ((GeneralPath)shape).lineTo(533.305, 483.839);
        ((GeneralPath)shape).moveTo(532.182, 484.836);
        ((GeneralPath)shape).lineTo(535.24603, 484.836);
        ((GeneralPath)shape).curveTo(537.06805, 484.836, 537.42206, 483.70898, 537.42206, 482.436);
        ((GeneralPath)shape).lineTo(537.42206, 480.991);
        ((GeneralPath)shape).curveTo(537.42206, 479.325, 536.71606, 478.712, 535.05505, 478.712);
        ((GeneralPath)shape).lineTo(532.18207, 478.712);
        ((GeneralPath)shape).lineTo(532.18207, 484.836);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_33;
        g.setTransform(defaultTransform__0_33);
        g.setClip(clip__0_33);
        float alpha__0_34 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_34 = g.getClip();
        AffineTransform defaultTransform__0_34 = g.getTransform();
        
//      _0_34 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new Rectangle2D.Double(538.2020263671875, 478.71099853515625, 1.1230000257492065, 6.125);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_34;
        g.setTransform(defaultTransform__0_34);
        g.setClip(clip__0_34);
        float alpha__0_35 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_35 = g.getClip();
        AffineTransform defaultTransform__0_35 = g.getTransform();
        
//      _0_35 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(543.822, 482.836);
        ((GeneralPath)shape).lineTo(541.745, 482.836);
        ((GeneralPath)shape).lineTo(542.772, 479.602);
        ((GeneralPath)shape).lineTo(542.78796, 479.602);
        ((GeneralPath)shape).lineTo(543.822, 482.836);
        ((GeneralPath)shape).moveTo(544.08, 483.711);
        ((GeneralPath)shape).lineTo(544.474, 484.836);
        ((GeneralPath)shape).lineTo(545.646, 484.836);
        ((GeneralPath)shape).lineTo(543.607, 478.711);
        ((GeneralPath)shape).lineTo(541.90497, 478.711);
        ((GeneralPath)shape).lineTo(539.907, 484.836);
        ((GeneralPath)shape).lineTo(541.104, 484.836);
        ((GeneralPath)shape).lineTo(541.48, 483.711);
        ((GeneralPath)shape).lineTo(544.08, 483.711);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_35;
        g.setTransform(defaultTransform__0_35);
        g.setClip(clip__0_35);
        float alpha__0_36 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_36 = g.getClip();
        AffineTransform defaultTransform__0_36 = g.getTransform();
        
//      _0_36 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(548.375, 481.586);
        ((GeneralPath)shape).lineTo(548.375, 482.461);
        ((GeneralPath)shape).lineTo(549.935, 482.461);
        ((GeneralPath)shape).lineTo(549.935, 482.68);
        ((GeneralPath)shape).curveTo(549.935, 483.836, 549.587, 483.836, 548.459, 483.836);
        ((GeneralPath)shape).curveTo(547.139, 483.836, 546.94, 483.707, 546.94, 482.451);
        ((GeneralPath)shape).lineTo(546.94, 481.076);
        ((GeneralPath)shape).curveTo(546.94, 479.916, 547.072, 479.707, 548.459, 479.707);
        ((GeneralPath)shape).curveTo(549.489, 479.707, 549.935, 479.707, 549.935, 480.594);
        ((GeneralPath)shape).lineTo(551.057, 480.594);
        ((GeneralPath)shape).curveTo(551.057, 478.639, 549.965, 478.711, 548.459, 478.711);
        ((GeneralPath)shape).curveTo(546.601, 478.711, 545.81696, 479.065, 545.81696, 481.074);
        ((GeneralPath)shape).lineTo(545.81696, 482.457);
        ((GeneralPath)shape).curveTo(545.81696, 484.498, 546.53796, 484.836, 548.4639, 484.836);
        ((GeneralPath)shape).curveTo(550.60596, 484.836, 551.0559, 484.467, 551.0559, 482.68);
        ((GeneralPath)shape).lineTo(551.0559, 481.586);
        ((GeneralPath)shape).lineTo(548.375, 481.586);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_36;
        g.setTransform(defaultTransform__0_36);
        g.setClip(clip__0_36);
        float alpha__0_37 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_37 = g.getClip();
        AffineTransform defaultTransform__0_37 = g.getTransform();
        
//      _0_37 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(552.994, 481.836);
        ((GeneralPath)shape).lineTo(552.994, 479.707);
        ((GeneralPath)shape).lineTo(554.544, 479.707);
        ((GeneralPath)shape).curveTo(555.341, 479.707, 555.489, 479.865, 555.489, 480.746);
        ((GeneralPath)shape).curveTo(555.489, 481.662, 555.288, 481.836, 554.466, 481.836);
        ((GeneralPath)shape).lineTo(552.994, 481.836);
        ((GeneralPath)shape).moveTo(554.738, 482.836);
        ((GeneralPath)shape).curveTo(555.284, 482.836, 555.48895, 483.193, 555.48895, 483.703);
        ((GeneralPath)shape).lineTo(555.48895, 484.836);
        ((GeneralPath)shape).lineTo(556.61194, 484.836);
        ((GeneralPath)shape).lineTo(556.61194, 483.705);
        ((GeneralPath)shape).curveTo(556.61194, 482.84998, 556.40894, 482.404, 555.5789, 482.332);
        ((GeneralPath)shape).lineTo(555.5789, 482.299);
        ((GeneralPath)shape).curveTo(556.61194, 482.14102, 556.61194, 481.46902, 556.61194, 480.547);
        ((GeneralPath)shape).curveTo(556.61194, 479.135, 556.1229, 478.711, 554.8469, 478.711);
        ((GeneralPath)shape).lineTo(551.86993, 478.711);
        ((GeneralPath)shape).lineTo(551.86993, 484.836);
        ((GeneralPath)shape).lineTo(552.9929, 484.836);
        ((GeneralPath)shape).lineTo(552.9929, 482.836);
        ((GeneralPath)shape).lineTo(554.738, 482.836);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_37;
        g.setTransform(defaultTransform__0_37);
        g.setClip(clip__0_37);
        float alpha__0_38 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_38 = g.getClip();
        AffineTransform defaultTransform__0_38 = g.getTransform();
        
//      _0_38 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(561.053, 482.836);
        ((GeneralPath)shape).lineTo(558.97595, 482.836);
        ((GeneralPath)shape).lineTo(560.0029, 479.602);
        ((GeneralPath)shape).lineTo(560.0189, 479.602);
        ((GeneralPath)shape).lineTo(561.053, 482.836);
        ((GeneralPath)shape).moveTo(561.311, 483.711);
        ((GeneralPath)shape).lineTo(561.70496, 484.836);
        ((GeneralPath)shape).lineTo(562.87695, 484.836);
        ((GeneralPath)shape).lineTo(560.83795, 478.711);
        ((GeneralPath)shape).lineTo(559.1359, 478.711);
        ((GeneralPath)shape).lineTo(557.13794, 484.836);
        ((GeneralPath)shape).lineTo(558.33496, 484.836);
        ((GeneralPath)shape).lineTo(558.71094, 483.711);
        ((GeneralPath)shape).lineTo(561.311, 483.711);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_38;
        g.setTransform(defaultTransform__0_38);
        g.setClip(clip__0_38);
        float alpha__0_39 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_39 = g.getClip();
        AffineTransform defaultTransform__0_39 = g.getTransform();
        
//      _0_39 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(569.16, 479.666);
        ((GeneralPath)shape).lineTo(569.16, 484.836);
        ((GeneralPath)shape).lineTo(570.283, 484.836);
        ((GeneralPath)shape).lineTo(570.283, 478.711);
        ((GeneralPath)shape).lineTo(568.418, 478.711);
        ((GeneralPath)shape).lineTo(566.802, 483.258);
        ((GeneralPath)shape).lineTo(566.771, 483.258);
        ((GeneralPath)shape).lineTo(565.121, 478.711);
        ((GeneralPath)shape).lineTo(563.297, 478.711);
        ((GeneralPath)shape).lineTo(563.297, 484.836);
        ((GeneralPath)shape).lineTo(564.42, 484.836);
        ((GeneralPath)shape).lineTo(564.42, 479.691);
        ((GeneralPath)shape).lineTo(566.27, 484.836);
        ((GeneralPath)shape).lineTo(567.311, 484.836);
        ((GeneralPath)shape).lineTo(569.16, 479.666);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_39;
        g.setTransform(defaultTransform__0_39);
        g.setClip(clip__0_39);
        float alpha__0_40 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_40 = g.getClip();
        AffineTransform defaultTransform__0_40 = g.getTransform();
        
//      _0_40 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        stroke = new BasicStroke(1.0f,0,0,10.0f,null,0.0f);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(488.607, 588.801);
        ((GeneralPath)shape).lineTo(483.856, 593.801);
        ((GeneralPath)shape).lineTo(309.607, 593.801);
        ((GeneralPath)shape).lineTo(304.107, 588.801);
        ((GeneralPath)shape).lineTo(304.107, 485.801);
        ((GeneralPath)shape).lineTo(309.108, 480.801);
        ((GeneralPath)shape).lineTo(483.357, 480.801);
        ((GeneralPath)shape).lineTo(488.607, 485.801);
        ((GeneralPath)shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_40;
        g.setTransform(defaultTransform__0_40);
        g.setClip(clip__0_40);
        float alpha__0_41 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_41 = g.getClip();
        AffineTransform defaultTransform__0_41 = g.getTransform();
        
//      _0_41 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(487.334, 487.265);
        ((GeneralPath)shape).lineTo(482.585, 492.266);
        ((GeneralPath)shape).lineTo(311.334, 492.266);
        ((GeneralPath)shape).lineTo(305.834, 487.265);
        ((GeneralPath)shape).lineTo(310.834, 482.266);
        ((GeneralPath)shape).lineTo(482.084, 482.266);
        ((GeneralPath)shape).lineTo(487.334, 487.265);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_41;
        g.setTransform(defaultTransform__0_41);
        g.setClip(clip__0_41);
        float alpha__0_42 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_42 = g.getClip();
        AffineTransform defaultTransform__0_42 = g.getTransform();
        
//      _0_42 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        stroke = new BasicStroke(1.0f,0,0,10.0f,null,0.0f);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(487.334, 487.265);
        ((GeneralPath)shape).lineTo(482.585, 492.266);
        ((GeneralPath)shape).lineTo(311.334, 492.266);
        ((GeneralPath)shape).lineTo(305.834, 487.265);
        ((GeneralPath)shape).lineTo(310.834, 482.265);
        ((GeneralPath)shape).lineTo(482.084, 482.265);
        ((GeneralPath)shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_42;
        g.setTransform(defaultTransform__0_42);
        g.setClip(clip__0_42);
        float alpha__0_43 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_43 = g.getClip();
        AffineTransform defaultTransform__0_43 = g.getTransform();
        
//      _0_43 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(300.352, 487.265);
        ((GeneralPath)shape).lineTo(295.602, 492.266);
        ((GeneralPath)shape).lineTo(130.353, 492.266);
        ((GeneralPath)shape).lineTo(124.852, 487.265);
        ((GeneralPath)shape).lineTo(129.852, 482.266);
        ((GeneralPath)shape).lineTo(295.102, 482.266);
        ((GeneralPath)shape).lineTo(300.352, 487.265);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_43;
        g.setTransform(defaultTransform__0_43);
        g.setClip(clip__0_43);
        float alpha__0_44 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_44 = g.getClip();
        AffineTransform defaultTransform__0_44 = g.getTransform();
        
//      _0_44 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        stroke = new BasicStroke(1.0f,0,0,10.0f,null,0.0f);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(300.352, 487.265);
        ((GeneralPath)shape).lineTo(295.602, 492.266);
        ((GeneralPath)shape).lineTo(130.353, 492.266);
        ((GeneralPath)shape).lineTo(124.852, 487.265);
        ((GeneralPath)shape).lineTo(129.852, 482.265);
        ((GeneralPath)shape).lineTo(295.102, 482.265);
        ((GeneralPath)shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_44;
        g.setTransform(defaultTransform__0_44);
        g.setClip(clip__0_44);
        float alpha__0_45 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_45 = g.getClip();
        AffineTransform defaultTransform__0_45 = g.getTransform();
        
//      _0_45 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        stroke = new BasicStroke(1.0f,0,0,10.0f,null,0.0f);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(301.608, 549.801);
        ((GeneralPath)shape).lineTo(296.858, 554.801);
        ((GeneralPath)shape).lineTo(128.607, 554.801);
        ((GeneralPath)shape).lineTo(123.108, 549.801);
        ((GeneralPath)shape).lineTo(123.108, 485.801);
        ((GeneralPath)shape).lineTo(128.108, 480.801);
        ((GeneralPath)shape).lineTo(296.358, 480.801);
        ((GeneralPath)shape).lineTo(301.608, 485.801);
        ((GeneralPath)shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_45;
        g.setTransform(defaultTransform__0_45);
        g.setClip(clip__0_45);
        float alpha__0_46 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_46 = g.getClip();
        AffineTransform defaultTransform__0_46 = g.getTransform();
        
//      _0_46 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        stroke = new BasicStroke(1.0f,0,0,10.0f,null,0.0f);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(301.608, 578.801);
        ((GeneralPath)shape).lineTo(296.858, 583.801);
        ((GeneralPath)shape).lineTo(40.608, 583.801);
        ((GeneralPath)shape).lineTo(35.108, 578.801);
        ((GeneralPath)shape).lineTo(35.108, 556.801);
        ((GeneralPath)shape).lineTo(40.108, 551.801);
        ((GeneralPath)shape).lineTo(120.525, 551.801);
        ((GeneralPath)shape).lineTo(126.691, 557.801);
        ((GeneralPath)shape).lineTo(296.358, 557.801);
        ((GeneralPath)shape).lineTo(301.608, 562.801);
        ((GeneralPath)shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_46;
        g.setTransform(defaultTransform__0_46);
        g.setClip(clip__0_46);
        float alpha__0_47 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_47 = g.getClip();
        AffineTransform defaultTransform__0_47 = g.getTransform();
        
//      _0_47 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(127.119, 494.613);
        ((GeneralPath)shape).lineTo(125.9, 494.613);
        ((GeneralPath)shape).lineTo(125.9, 499.207);
        ((GeneralPath)shape).lineTo(128.997, 499.207);
        ((GeneralPath)shape).lineTo(128.997, 498.151);
        ((GeneralPath)shape).lineTo(127.119, 498.151);
        ((GeneralPath)shape).lineTo(127.119, 494.613);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_47;
        g.setTransform(defaultTransform__0_47);
        g.setClip(clip__0_47);
        float alpha__0_48 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_48 = g.getClip();
        AffineTransform defaultTransform__0_48 = g.getTransform();
        
//      _0_48 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(131.276, 498.176);
        ((GeneralPath)shape).curveTo(130.647, 498.176, 130.442, 498.111, 130.442, 497.432);
        ((GeneralPath)shape).lineTo(130.442, 496.409);
        ((GeneralPath)shape).curveTo(130.442, 495.736, 130.647, 495.671, 131.276, 495.671);
        ((GeneralPath)shape).curveTo(131.905, 495.671, 132.129, 495.736, 132.129, 496.409);
        ((GeneralPath)shape).lineTo(132.129, 497.432);
        ((GeneralPath)shape).curveTo(132.129, 498.11, 131.905, 498.176, 131.276, 498.176);
        ((GeneralPath)shape).moveTo(131.285, 499.207);
        ((GeneralPath)shape).curveTo(132.492, 499.207, 133.348, 498.942, 133.348, 497.549);
        ((GeneralPath)shape).lineTo(133.348, 496.272);
        ((GeneralPath)shape).curveTo(133.348, 494.879, 132.49301, 494.614, 131.285, 494.614);
        ((GeneralPath)shape).curveTo(130.077, 494.614, 129.222, 494.88, 129.222, 496.272);
        ((GeneralPath)shape).lineTo(129.222, 497.549);
        ((GeneralPath)shape).curveTo(129.223, 498.942, 130.078, 499.207, 131.285, 499.207);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_48;
        g.setTransform(defaultTransform__0_48);
        g.setClip(clip__0_48);
        float alpha__0_49 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_49 = g.getClip();
        AffineTransform defaultTransform__0_49 = g.getTransform();
        
//      _0_49 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(136.501, 497.475);
        ((GeneralPath)shape).lineTo(136.501, 497.608);
        ((GeneralPath)shape).curveTo(136.501, 498.14, 136.26001, 498.176, 135.757, 498.176);
        ((GeneralPath)shape).curveTo(135.032, 498.176, 135.001, 497.915, 135.001, 497.238);
        ((GeneralPath)shape).lineTo(135.001, 496.53);
        ((GeneralPath)shape).curveTo(135.001, 495.883, 135.06801, 495.671, 135.76001, 495.671);
        ((GeneralPath)shape).curveTo(136.173, 495.671, 136.45901, 495.701, 136.501, 496.108);
        ((GeneralPath)shape).lineTo(136.501, 496.282);
        ((GeneralPath)shape).lineTo(137.72, 496.282);
        ((GeneralPath)shape).lineTo(137.72, 496.10602);
        ((GeneralPath)shape).curveTo(137.72, 494.743, 137.143, 494.61502, 135.905, 494.61502);
        ((GeneralPath)shape).curveTo(134.583, 494.61502, 133.782, 494.83203, 133.782, 496.32803);
        ((GeneralPath)shape).lineTo(133.782, 497.49704);
        ((GeneralPath)shape).curveTo(133.782, 499.14905, 134.664, 499.20905, 135.893, 499.20905);
        ((GeneralPath)shape).curveTo(136.42201, 499.20905, 136.914, 499.20905, 137.317, 498.87503);
        ((GeneralPath)shape).curveTo(137.72, 498.53702, 137.72, 498.09604, 137.72, 497.60602);
        ((GeneralPath)shape).lineTo(137.72, 497.476);
        ((GeneralPath)shape).lineTo(136.501, 497.476);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_49;
        g.setTransform(defaultTransform__0_49);
        g.setClip(clip__0_49);
        float alpha__0_50 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_50 = g.getClip();
        AffineTransform defaultTransform__0_50 = g.getTransform();
        
//      _0_50 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new Rectangle2D.Double(138.5260009765625, 498.1510009765625, 0.9380000233650208, 1.055999994277954);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_50;
        g.setTransform(defaultTransform__0_50);
        g.setClip(clip__0_50);
        float alpha__0_51 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_51 = g.getClip();
        AffineTransform defaultTransform__0_51 = g.getTransform();
        
//      _0_51 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(158.121, 495.67);
        ((GeneralPath)shape).lineTo(159.333, 495.67);
        ((GeneralPath)shape).lineTo(159.333, 494.613);
        ((GeneralPath)shape).lineTo(155.75, 494.613);
        ((GeneralPath)shape).lineTo(155.75, 495.67);
        ((GeneralPath)shape).lineTo(156.902, 495.67);
        ((GeneralPath)shape).lineTo(156.902, 499.207);
        ((GeneralPath)shape).lineTo(158.121, 499.207);
        ((GeneralPath)shape).lineTo(158.121, 495.67);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_51;
        g.setTransform(defaultTransform__0_51);
        g.setClip(clip__0_51);
        float alpha__0_52 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_52 = g.getClip();
        AffineTransform defaultTransform__0_52 = g.getTransform();
        
//      _0_52 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(163.709, 494.613);
        ((GeneralPath)shape).lineTo(162.318, 494.613);
        ((GeneralPath)shape).lineTo(161.481, 496.383);
        ((GeneralPath)shape).lineTo(161.439, 496.383);
        ((GeneralPath)shape).lineTo(160.625, 494.613);
        ((GeneralPath)shape).lineTo(159.25, 494.613);
        ((GeneralPath)shape).lineTo(160.847, 497.596);
        ((GeneralPath)shape).lineTo(160.847, 499.207);
        ((GeneralPath)shape).lineTo(162.066, 499.207);
        ((GeneralPath)shape).lineTo(162.066, 497.596);
        ((GeneralPath)shape).lineTo(163.709, 494.613);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_52;
        g.setTransform(defaultTransform__0_52);
        g.setClip(clip__0_52);
        float alpha__0_53 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_53 = g.getClip();
        AffineTransform defaultTransform__0_53 = g.getTransform();
        
//      _0_53 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(165.188, 497.051);
        ((GeneralPath)shape).lineTo(165.188, 495.66998);
        ((GeneralPath)shape).lineTo(165.98001, 495.66998);
        ((GeneralPath)shape).curveTo(166.43102, 495.66998, 166.50002, 495.82898, 166.50002, 496.344);
        ((GeneralPath)shape).curveTo(166.50002, 496.991, 166.43701, 497.051, 165.98001, 497.051);
        ((GeneralPath)shape).lineTo(165.188, 497.051);
        ((GeneralPath)shape).moveTo(165.188, 499.207);
        ((GeneralPath)shape).lineTo(165.188, 498.082);
        ((GeneralPath)shape).lineTo(166.212, 498.082);
        ((GeneralPath)shape).curveTo(167.442, 498.082, 167.71901, 497.634, 167.71901, 496.323);
        ((GeneralPath)shape).curveTo(167.71901, 495.104, 167.30402, 494.613, 166.203, 494.613);
        ((GeneralPath)shape).lineTo(163.96901, 494.613);
        ((GeneralPath)shape).lineTo(163.96901, 499.207);
        ((GeneralPath)shape).lineTo(165.188, 499.207);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_53;
        g.setTransform(defaultTransform__0_53);
        g.setClip(clip__0_53);
        float alpha__0_54 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_54 = g.getClip();
        AffineTransform defaultTransform__0_54 = g.getTransform();
        
//      _0_54 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(169.455, 495.609);
        ((GeneralPath)shape).lineTo(171.501, 495.609);
        ((GeneralPath)shape).lineTo(171.501, 494.613);
        ((GeneralPath)shape).lineTo(168.236, 494.613);
        ((GeneralPath)shape).lineTo(168.236, 499.207);
        ((GeneralPath)shape).lineTo(171.542, 499.207);
        ((GeneralPath)shape).lineTo(171.542, 498.211);
        ((GeneralPath)shape).lineTo(169.455, 498.211);
        ((GeneralPath)shape).lineTo(169.455, 497.332);
        ((GeneralPath)shape).lineTo(171.375, 497.332);
        ((GeneralPath)shape).lineTo(171.375, 496.488);
        ((GeneralPath)shape).lineTo(169.455, 496.488);
        ((GeneralPath)shape).lineTo(169.455, 495.609);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_54;
        g.setTransform(defaultTransform__0_54);
        g.setClip(clip__0_54);
        float alpha__0_55 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_55 = g.getClip();
        AffineTransform defaultTransform__0_55 = g.getTransform();
        
//      _0_55 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(220.817, 495.67);
        ((GeneralPath)shape).lineTo(221.88701, 495.67);
        ((GeneralPath)shape).curveTo(222.31601, 495.67, 222.505, 495.793, 222.505, 496.392);
        ((GeneralPath)shape).lineTo(222.505, 497.362);
        ((GeneralPath)shape).curveTo(222.505, 497.85, 222.34001, 498.152, 221.88701, 498.152);
        ((GeneralPath)shape).lineTo(220.817, 498.152);
        ((GeneralPath)shape).lineTo(220.817, 495.67);
        ((GeneralPath)shape).moveTo(219.598, 499.207);
        ((GeneralPath)shape).lineTo(222.06001, 499.207);
        ((GeneralPath)shape).curveTo(223.32301, 499.207, 223.723, 498.612, 223.723, 497.357);
        ((GeneralPath)shape).lineTo(223.723, 496.396);
        ((GeneralPath)shape).curveTo(223.723, 495.09, 223.16, 494.613, 221.89601, 494.613);
        ((GeneralPath)shape).lineTo(219.598, 494.613);
        ((GeneralPath)shape).lineTo(219.598, 499.207);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_55;
        g.setTransform(defaultTransform__0_55);
        g.setClip(clip__0_55);
        float alpha__0_56 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_56 = g.getClip();
        AffineTransform defaultTransform__0_56 = g.getTransform();
        
//      _0_56 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(224.342, 496.02);
        ((GeneralPath)shape).lineTo(224.342, 499.20798);
        ((GeneralPath)shape).lineTo(225.373, 499.20798);
        ((GeneralPath)shape).lineTo(225.373, 497.42596);
        ((GeneralPath)shape).curveTo(225.373, 497.04895, 225.448, 496.82196, 225.855, 496.82196);
        ((GeneralPath)shape).curveTo(226.19899, 496.82196, 226.217, 497.01895, 226.217, 497.30597);
        ((GeneralPath)shape).lineTo(226.217, 499.20697);
        ((GeneralPath)shape).lineTo(227.248, 499.20697);
        ((GeneralPath)shape).lineTo(227.248, 497.42496);
        ((GeneralPath)shape).curveTo(227.248, 497.09094, 227.331, 496.82095, 227.758, 496.82095);
        ((GeneralPath)shape).curveTo(228.18599, 496.82095, 228.18599, 497.05896, 228.18599, 497.38895);
        ((GeneralPath)shape).lineTo(228.18599, 499.20593);
        ((GeneralPath)shape).lineTo(229.217, 499.20593);
        ((GeneralPath)shape).lineTo(229.217, 497.05194);
        ((GeneralPath)shape).curveTo(229.217, 496.31995, 228.838, 496.01895, 228.094, 496.01895);
        ((GeneralPath)shape).curveTo(227.68999, 496.01895, 227.25499, 496.16495, 227.19899, 496.55994);
        ((GeneralPath)shape).lineTo(227.152, 496.55994);
        ((GeneralPath)shape).curveTo(227.099, 496.08795, 226.655, 496.01895, 226.22499, 496.01895);
        ((GeneralPath)shape).curveTo(225.881, 496.01895, 225.50699, 496.11194, 225.38399, 496.43195);
        ((GeneralPath)shape).lineTo(225.372, 496.43195);
        ((GeneralPath)shape).lineTo(225.372, 496.01895);
        ((GeneralPath)shape).lineTo(224.342, 496.01895);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_56;
        g.setTransform(defaultTransform__0_56);
        g.setClip(clip__0_56);
        float alpha__0_57 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_57 = g.getClip();
        AffineTransform defaultTransform__0_57 = g.getTransform();
        
//      _0_57 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(229.822, 499.488);
        ((GeneralPath)shape).curveTo(229.822, 500.492, 230.15901, 500.613, 231.319, 500.613);
        ((GeneralPath)shape).curveTo(232.579, 500.613, 232.916, 500.25, 232.916, 499.062);
        ((GeneralPath)shape).lineTo(232.916, 496.019);
        ((GeneralPath)shape).lineTo(231.885, 496.019);
        ((GeneralPath)shape).lineTo(231.885, 496.582);
        ((GeneralPath)shape).curveTo(231.756, 496.122, 231.424, 496.019, 230.969, 496.019);
        ((GeneralPath)shape).curveTo(229.857, 496.019, 229.728, 496.614, 229.728, 497.63303);
        ((GeneralPath)shape).curveTo(229.728, 498.52902, 229.826, 499.20602, 230.969, 499.20602);
        ((GeneralPath)shape).curveTo(231.375, 499.20602, 231.713, 499.079, 231.84799, 498.68503);
        ((GeneralPath)shape).lineTo(231.885, 498.68503);
        ((GeneralPath)shape).lineTo(231.885, 499.34702);
        ((GeneralPath)shape).curveTo(231.885, 499.789, 231.78699, 499.863, 231.33, 499.863);
        ((GeneralPath)shape).curveTo(231.205, 499.863, 231.088, 499.863, 231.002, 499.819);
        ((GeneralPath)shape).curveTo(230.91, 499.769, 230.854, 499.67, 230.854, 499.488);
        ((GeneralPath)shape).lineTo(229.822, 499.488);
        ((GeneralPath)shape).moveTo(231.313, 498.406);
        ((GeneralPath)shape).curveTo(230.847, 498.406, 230.759, 498.275, 230.759, 497.634);
        ((GeneralPath)shape).curveTo(230.759, 496.91602, 230.809, 496.821, 231.313, 496.821);
        ((GeneralPath)shape).curveTo(231.764, 496.821, 231.884, 496.927, 231.884, 497.634);
        ((GeneralPath)shape).curveTo(231.885, 498.169, 231.841, 498.406, 231.313, 498.406);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_57;
        g.setTransform(defaultTransform__0_57);
        g.setClip(clip__0_57);
        float alpha__0_58 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_58 = g.getClip();
        AffineTransform defaultTransform__0_58 = g.getTransform();
        
//      _0_58 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(240.444, 495.676);
        ((GeneralPath)shape).lineTo(240.481, 495.676);
        ((GeneralPath)shape).lineTo(241.625, 499.207);
        ((GeneralPath)shape).lineTo(242.656, 499.207);
        ((GeneralPath)shape).lineTo(243.789, 495.676);
        ((GeneralPath)shape).lineTo(243.831, 495.676);
        ((GeneralPath)shape).lineTo(243.711, 499.207);
        ((GeneralPath)shape).lineTo(244.903, 499.207);
        ((GeneralPath)shape).lineTo(244.903, 494.613);
        ((GeneralPath)shape).lineTo(242.967, 494.613);
        ((GeneralPath)shape).lineTo(242.155, 497.493);
        ((GeneralPath)shape).lineTo(242.114, 497.493);
        ((GeneralPath)shape).lineTo(241.262, 494.613);
        ((GeneralPath)shape).lineTo(239.372, 494.613);
        ((GeneralPath)shape).lineTo(239.372, 499.207);
        ((GeneralPath)shape).lineTo(240.564, 499.207);
        ((GeneralPath)shape).lineTo(240.444, 495.676);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_58;
        g.setTransform(defaultTransform__0_58);
        g.setClip(clip__0_58);
        float alpha__0_59 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_59 = g.getClip();
        AffineTransform defaultTransform__0_59 = g.getTransform();
        
//      _0_59 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(246.604, 494.613);
        ((GeneralPath)shape).lineTo(245.573, 494.613);
        ((GeneralPath)shape).lineTo(245.573, 495.415);
        ((GeneralPath)shape).lineTo(246.604, 495.415);
        ((GeneralPath)shape).lineTo(246.604, 494.613);
        ((GeneralPath)shape).moveTo(246.604, 496.02);
        ((GeneralPath)shape).lineTo(245.573, 496.02);
        ((GeneralPath)shape).lineTo(245.573, 499.20798);
        ((GeneralPath)shape).lineTo(246.604, 499.20798);
        ((GeneralPath)shape).lineTo(246.604, 496.02);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_59;
        g.setTransform(defaultTransform__0_59);
        g.setClip(clip__0_59);
        float alpha__0_60 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_60 = g.getClip();
        AffineTransform defaultTransform__0_60 = g.getTransform();
        
//      _0_60 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(247.208, 496.02);
        ((GeneralPath)shape).lineTo(247.208, 499.20798);
        ((GeneralPath)shape).lineTo(248.239, 499.20798);
        ((GeneralPath)shape).lineTo(248.239, 497.45297);
        ((GeneralPath)shape).curveTo(248.239, 497.06497, 248.315, 496.78497, 248.76599, 496.78497);
        ((GeneralPath)shape).curveTo(249.094, 496.78497, 249.176, 496.93698, 249.176, 497.24698);
        ((GeneralPath)shape).lineTo(249.176, 499.20798);
        ((GeneralPath)shape).lineTo(250.207, 499.20798);
        ((GeneralPath)shape).lineTo(250.207, 497.02798);
        ((GeneralPath)shape).curveTo(250.207, 496.34998, 249.894, 496.02, 249.20801, 496.02);
        ((GeneralPath)shape).curveTo(248.97101, 496.02, 248.776, 496.048, 248.623, 496.137);
        ((GeneralPath)shape).curveTo(248.469, 496.219, 248.356, 496.365, 248.28, 496.597);
        ((GeneralPath)shape).lineTo(248.239, 496.597);
        ((GeneralPath)shape).lineTo(248.239, 496.02);
        ((GeneralPath)shape).lineTo(247.208, 496.02);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_60;
        g.setTransform(defaultTransform__0_60);
        g.setClip(clip__0_60);
        float alpha__0_61 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_61 = g.getClip();
        AffineTransform defaultTransform__0_61 = g.getTransform();
        
//      _0_61 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(255.973, 496.348);
        ((GeneralPath)shape).curveTo(255.64801, 496.348, 255.46901, 496.336, 255.46901, 495.985);
        ((GeneralPath)shape).curveTo(255.46901, 495.73898, 255.52501, 495.61, 256.187, 495.61);
        ((GeneralPath)shape).curveTo(256.67902, 495.61, 256.875, 495.61, 256.875, 496.06097);
        ((GeneralPath)shape).lineTo(258.0, 496.06097);
        ((GeneralPath)shape).lineTo(258.0, 495.873);
        ((GeneralPath)shape).curveTo(258.0, 494.61398, 257.155, 494.61398, 256.165, 494.61398);
        ((GeneralPath)shape).curveTo(254.981, 494.61398, 254.25002, 494.698, 254.25002, 495.97598);
        ((GeneralPath)shape).curveTo(254.25002, 497.25598, 254.88602, 497.24997, 256.04102, 497.33197);
        ((GeneralPath)shape).lineTo(256.31403, 497.33197);
        ((GeneralPath)shape).curveTo(256.90002, 497.33197, 256.96902, 497.32596, 256.96902, 497.73798);
        ((GeneralPath)shape).curveTo(256.96902, 498.13696, 256.773, 498.17596, 256.17603, 498.17596);
        ((GeneralPath)shape).curveTo(255.55302, 498.17596, 255.37602, 498.12497, 255.37602, 497.63095);
        ((GeneralPath)shape).lineTo(254.25102, 497.63095);
        ((GeneralPath)shape).curveTo(254.25102, 499.20694, 255.02303, 499.20694, 256.183, 499.20694);
        ((GeneralPath)shape).curveTo(258.18802, 499.20694, 258.18802, 498.44693, 258.18802, 497.65594);
        ((GeneralPath)shape).curveTo(258.18802, 496.61395, 257.85, 496.44394, 256.48303, 496.34695);
        ((GeneralPath)shape).lineTo(255.973, 496.34695);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_61;
        g.setTransform(defaultTransform__0_61);
        g.setClip(clip__0_61);
        float alpha__0_62 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_62 = g.getClip();
        AffineTransform defaultTransform__0_62 = g.getTransform();
        
//      _0_62 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(259.765, 494.613);
        ((GeneralPath)shape).lineTo(258.734, 494.613);
        ((GeneralPath)shape).lineTo(258.734, 499.207);
        ((GeneralPath)shape).lineTo(259.765, 499.207);
        ((GeneralPath)shape).lineTo(259.765, 497.478);
        ((GeneralPath)shape).curveTo(259.765, 497.096, 259.841, 496.82098, 260.29202, 496.82098);
        ((GeneralPath)shape).curveTo(260.62003, 496.82098, 260.70203, 496.96997, 260.70203, 497.275);
        ((GeneralPath)shape).lineTo(260.70203, 499.207);
        ((GeneralPath)shape).lineTo(261.73303, 499.207);
        ((GeneralPath)shape).lineTo(261.73303, 497.027);
        ((GeneralPath)shape).curveTo(261.73303, 496.349, 261.42004, 496.019, 260.73303, 496.019);
        ((GeneralPath)shape).curveTo(260.49603, 496.019, 260.30103, 496.04703, 260.14804, 496.13602);
        ((GeneralPath)shape).curveTo(259.99405, 496.21802, 259.88104, 496.364, 259.80505, 496.596);
        ((GeneralPath)shape).lineTo(259.76407, 496.596);
        ((GeneralPath)shape).lineTo(259.76407, 494.613);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_62;
        g.setTransform(defaultTransform__0_62);
        g.setClip(clip__0_62);
        float alpha__0_63 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_63 = g.getClip();
        AffineTransform defaultTransform__0_63 = g.getTransform();
        
//      _0_63 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(264.744, 496.02);
        ((GeneralPath)shape).lineTo(263.57397, 496.02);
        ((GeneralPath)shape).lineTo(263.57397, 495.34598);
        ((GeneralPath)shape).lineTo(262.54297, 495.34598);
        ((GeneralPath)shape).lineTo(262.54297, 496.02);
        ((GeneralPath)shape).lineTo(262.15897, 496.02);
        ((GeneralPath)shape).lineTo(262.15897, 496.785);
        ((GeneralPath)shape).lineTo(262.54297, 496.785);
        ((GeneralPath)shape).lineTo(262.54297, 498.338);
        ((GeneralPath)shape).curveTo(262.54297, 499.125, 263.08496, 499.208, 263.73798, 499.208);
        ((GeneralPath)shape).curveTo(264.57098, 499.208, 264.886, 499.018, 264.886, 498.164);
        ((GeneralPath)shape).lineTo(264.886, 497.883);
        ((GeneralPath)shape).lineTo(264.042, 497.883);
        ((GeneralPath)shape).lineTo(264.042, 498.051);
        ((GeneralPath)shape).curveTo(264.042, 498.267, 264.042, 498.458, 263.789, 498.458);
        ((GeneralPath)shape).curveTo(263.607, 498.458, 263.574, 498.381, 263.574, 498.194);
        ((GeneralPath)shape).lineTo(263.574, 496.785);
        ((GeneralPath)shape).lineTo(264.74402, 496.785);
        ((GeneralPath)shape).lineTo(264.74402, 496.02);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_63;
        g.setTransform(defaultTransform__0_63);
        g.setClip(clip__0_63);
        float alpha__0_64 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_64 = g.getClip();
        AffineTransform defaultTransform__0_64 = g.getTransform();
        
//      _0_64 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(270.768, 495.676);
        ((GeneralPath)shape).lineTo(270.805, 495.676);
        ((GeneralPath)shape).lineTo(271.949, 499.207);
        ((GeneralPath)shape).lineTo(272.98, 499.207);
        ((GeneralPath)shape).lineTo(274.112, 495.676);
        ((GeneralPath)shape).lineTo(274.155, 495.676);
        ((GeneralPath)shape).lineTo(274.035, 499.207);
        ((GeneralPath)shape).lineTo(275.227, 499.207);
        ((GeneralPath)shape).lineTo(275.227, 494.613);
        ((GeneralPath)shape).lineTo(273.291, 494.613);
        ((GeneralPath)shape).lineTo(272.479, 497.493);
        ((GeneralPath)shape).lineTo(272.438, 497.493);
        ((GeneralPath)shape).lineTo(271.585, 494.613);
        ((GeneralPath)shape).lineTo(269.696, 494.613);
        ((GeneralPath)shape).lineTo(269.696, 499.207);
        ((GeneralPath)shape).lineTo(270.888, 499.207);
        ((GeneralPath)shape).lineTo(270.768, 495.676);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_64;
        g.setTransform(defaultTransform__0_64);
        g.setClip(clip__0_64);
        float alpha__0_65 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_65 = g.getClip();
        AffineTransform defaultTransform__0_65 = g.getTransform();
        
//      _0_65 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(277.865, 498.082);
        ((GeneralPath)shape).curveTo(277.865, 498.421, 277.66098, 498.457, 277.344, 498.457);
        ((GeneralPath)shape).curveTo(276.918, 498.457, 276.83398, 498.308, 276.83398, 497.801);
        ((GeneralPath)shape).lineTo(278.89697, 497.801);
        ((GeneralPath)shape).lineTo(278.89697, 497.628);
        ((GeneralPath)shape).curveTo(278.89697, 496.348, 278.541, 496.02, 277.35397, 496.02);
        ((GeneralPath)shape).curveTo(276.08096, 496.02, 275.804, 496.417, 275.804, 497.61697);
        ((GeneralPath)shape).curveTo(275.804, 498.77997, 276.106, 499.20798, 277.35397, 499.20798);
        ((GeneralPath)shape).curveTo(277.81198, 499.20798, 278.19797, 499.17197, 278.46997, 499.01498);
        ((GeneralPath)shape).curveTo(278.74097, 498.85397, 278.89798, 498.57498, 278.89798, 498.08298);
        ((GeneralPath)shape).lineTo(277.865, 498.08298);
        ((GeneralPath)shape).moveTo(276.833, 497.238);
        ((GeneralPath)shape).curveTo(276.833, 496.831, 276.95, 496.74802, 277.327, 496.74802);
        ((GeneralPath)shape).curveTo(277.686, 496.74802, 277.865, 496.777, 277.865, 497.238);
        ((GeneralPath)shape).lineTo(276.833, 497.238);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_65;
        g.setTransform(defaultTransform__0_65);
        g.setClip(clip__0_65);
        float alpha__0_66 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_66 = g.getClip();
        AffineTransform defaultTransform__0_66 = g.getTransform();
        
//      _0_66 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(280.277, 497.643);
        ((GeneralPath)shape).curveTo(280.277, 497.019, 280.277, 496.822, 280.823, 496.822);
        ((GeneralPath)shape).curveTo(281.402, 496.822, 281.402, 497.031, 281.402, 497.643);
        ((GeneralPath)shape).curveTo(281.402, 498.355, 281.26602, 498.457, 280.823, 498.457);
        ((GeneralPath)shape).curveTo(280.377, 498.457, 280.277, 498.409, 280.277, 497.643);
        ((GeneralPath)shape).moveTo(282.434, 494.613);
        ((GeneralPath)shape).lineTo(281.40298, 494.613);
        ((GeneralPath)shape).lineTo(281.40298, 496.488);
        ((GeneralPath)shape).lineTo(281.365, 496.488);
        ((GeneralPath)shape).curveTo(281.194, 496.094, 280.85498, 496.019, 280.469, 496.019);
        ((GeneralPath)shape).curveTo(279.586, 496.019, 279.247, 496.39902, 279.247, 497.349);
        ((GeneralPath)shape).lineTo(279.247, 498.061);
        ((GeneralPath)shape).curveTo(279.247, 498.88602, 279.689, 499.207, 280.469, 499.207);
        ((GeneralPath)shape).curveTo(280.93, 499.207, 281.268, 499.106, 281.391, 498.665);
        ((GeneralPath)shape).lineTo(281.404, 498.665);
        ((GeneralPath)shape).lineTo(281.404, 499.207);
        ((GeneralPath)shape).lineTo(282.435, 499.207);
        ((GeneralPath)shape).lineTo(282.435, 494.613);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_66;
        g.setTransform(defaultTransform__0_66);
        g.setClip(clip__0_66);
        float alpha__0_67 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_67 = g.getClip();
        AffineTransform defaultTransform__0_67 = g.getTransform();
        
//      _0_67 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(288.663, 494.613);
        ((GeneralPath)shape).lineTo(287.444, 494.613);
        ((GeneralPath)shape).lineTo(287.444, 499.207);
        ((GeneralPath)shape).lineTo(290.541, 499.207);
        ((GeneralPath)shape).lineTo(290.541, 498.151);
        ((GeneralPath)shape).lineTo(288.663, 498.151);
        ((GeneralPath)shape).lineTo(288.663, 494.613);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_67;
        g.setTransform(defaultTransform__0_67);
        g.setClip(clip__0_67);
        float alpha__0_68 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_68 = g.getClip();
        AffineTransform defaultTransform__0_68 = g.getTransform();
        
//      _0_68 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(290.954, 496.02);
        ((GeneralPath)shape).lineTo(290.954, 499.20798);
        ((GeneralPath)shape).lineTo(291.98502, 499.20798);
        ((GeneralPath)shape).lineTo(291.98502, 497.45297);
        ((GeneralPath)shape).curveTo(291.98502, 497.06497, 292.061, 496.78497, 292.51202, 496.78497);
        ((GeneralPath)shape).curveTo(292.84003, 496.78497, 292.92203, 496.93698, 292.92203, 497.24698);
        ((GeneralPath)shape).lineTo(292.92203, 499.20798);
        ((GeneralPath)shape).lineTo(293.95303, 499.20798);
        ((GeneralPath)shape).lineTo(293.95303, 497.02798);
        ((GeneralPath)shape).curveTo(293.95303, 496.34998, 293.64005, 496.02, 292.95404, 496.02);
        ((GeneralPath)shape).curveTo(292.71704, 496.02, 292.52203, 496.048, 292.36905, 496.137);
        ((GeneralPath)shape).curveTo(292.21506, 496.219, 292.10205, 496.365, 292.02606, 496.597);
        ((GeneralPath)shape).lineTo(291.98508, 496.597);
        ((GeneralPath)shape).lineTo(291.98508, 496.02);
        ((GeneralPath)shape).lineTo(290.954, 496.02);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_68;
        g.setTransform(defaultTransform__0_68);
        g.setClip(clip__0_68);
        float alpha__0_69 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_69 = g.getClip();
        AffineTransform defaultTransform__0_69 = g.getTransform();
        
//      _0_69 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(294.574, 499.488);
        ((GeneralPath)shape).curveTo(294.574, 500.492, 294.911, 500.613, 296.072, 500.613);
        ((GeneralPath)shape).curveTo(297.332, 500.613, 297.668, 500.25, 297.668, 499.062);
        ((GeneralPath)shape).lineTo(297.668, 496.019);
        ((GeneralPath)shape).lineTo(296.637, 496.019);
        ((GeneralPath)shape).lineTo(296.637, 496.582);
        ((GeneralPath)shape).curveTo(296.508, 496.122, 296.176, 496.019, 295.722, 496.019);
        ((GeneralPath)shape).curveTo(294.61, 496.019, 294.481, 496.614, 294.481, 497.63303);
        ((GeneralPath)shape).curveTo(294.481, 498.52902, 294.58, 499.20602, 295.722, 499.20602);
        ((GeneralPath)shape).curveTo(296.12698, 499.20602, 296.46597, 499.079, 296.60098, 498.68503);
        ((GeneralPath)shape).lineTo(296.637, 498.68503);
        ((GeneralPath)shape).lineTo(296.637, 499.34702);
        ((GeneralPath)shape).curveTo(296.637, 499.789, 296.539, 499.863, 296.082, 499.863);
        ((GeneralPath)shape).curveTo(295.957, 499.863, 295.84, 499.863, 295.754, 499.819);
        ((GeneralPath)shape).curveTo(295.662, 499.769, 295.606, 499.67, 295.606, 499.488);
        ((GeneralPath)shape).lineTo(294.574, 499.488);
        ((GeneralPath)shape).moveTo(296.065, 498.406);
        ((GeneralPath)shape).curveTo(295.599, 498.406, 295.51102, 498.275, 295.51102, 497.634);
        ((GeneralPath)shape).curveTo(295.51102, 496.91602, 295.561, 496.821, 296.065, 496.821);
        ((GeneralPath)shape).curveTo(296.516, 496.821, 296.63602, 496.927, 296.63602, 497.634);
        ((GeneralPath)shape).curveTo(296.637, 498.169, 296.593, 498.406, 296.065, 498.406);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_69;
        g.setTransform(defaultTransform__0_69);
        g.setClip(clip__0_69);
        float alpha__0_70 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_70 = g.getClip();
        AffineTransform defaultTransform__0_70 = g.getTransform();
        
//      _0_70 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(37.405, 491.913);
        ((GeneralPath)shape).lineTo(37.405, 496.339);
        ((GeneralPath)shape).lineTo(36.396, 496.339);
        ((GeneralPath)shape).lineTo(36.396, 491.913);
        ((GeneralPath)shape).lineTo(34.859, 491.913);
        ((GeneralPath)shape).lineTo(34.859, 491.007);
        ((GeneralPath)shape).lineTo(38.996, 491.007);
        ((GeneralPath)shape).lineTo(38.996, 491.913);
        ((GeneralPath)shape).lineTo(37.405, 491.913);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_70;
        g.setTransform(defaultTransform__0_70);
        g.setClip(clip__0_70);
        float alpha__0_71 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_71 = g.getClip();
        AffineTransform defaultTransform__0_71 = g.getTransform();
        
//      _0_71 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(42.549, 492.604);
        ((GeneralPath)shape).lineTo(41.571, 496.573);
        ((GeneralPath)shape).curveTo(41.434, 497.136, 41.262, 497.512, 41.058, 497.708);
        ((GeneralPath)shape).curveTo(40.855, 497.901, 40.528, 497.999, 40.078, 497.999);
        ((GeneralPath)shape).curveTo(39.976997, 497.999, 39.871, 497.995, 39.761997, 497.983);
        ((GeneralPath)shape).lineTo(39.761997, 497.323);
        ((GeneralPath)shape).curveTo(39.840996, 497.327, 39.905, 497.331, 39.956997, 497.331);
        ((GeneralPath)shape).curveTo(40.334995, 497.331, 40.576996, 497.001, 40.678997, 496.339);
        ((GeneralPath)shape).lineTo(40.221996, 496.339);
        ((GeneralPath)shape).lineTo(39.001995, 492.60498);
        ((GeneralPath)shape).lineTo(39.960995, 492.60498);
        ((GeneralPath)shape).lineTo(40.428993, 494.18698);
        ((GeneralPath)shape).lineTo(40.662994, 494.97998);
        ((GeneralPath)shape).curveTo(40.674995, 495.03098, 40.711994, 495.16397, 40.771996, 495.378);
        ((GeneralPath)shape).lineTo(40.884995, 495.77298);
        ((GeneralPath)shape).lineTo(40.904995, 495.77298);
        ((GeneralPath)shape).lineTo(40.986996, 495.378);
        ((GeneralPath)shape).curveTo(41.028996, 495.175, 41.054996, 495.042, 41.068996, 494.97998);
        ((GeneralPath)shape).lineTo(41.251995, 494.18698);
        ((GeneralPath)shape).lineTo(41.610996, 492.60498);
        ((GeneralPath)shape).lineTo(42.549, 492.60498);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_71;
        g.setTransform(defaultTransform__0_71);
        g.setClip(clip__0_71);
        float alpha__0_72 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_72 = g.getClip();
        AffineTransform defaultTransform__0_72 = g.getTransform();
        
//      _0_72 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(44.713, 493.265);
        ((GeneralPath)shape).curveTo(44.374, 493.265, 44.151, 493.341, 44.046, 493.49503);
        ((GeneralPath)shape).curveTo(43.942, 493.64703, 43.890003, 493.97403, 43.890003, 494.47202);
        ((GeneralPath)shape).curveTo(43.890003, 494.95602, 43.945004, 495.277, 44.058002, 495.437);
        ((GeneralPath)shape).curveTo(44.169003, 495.595, 44.397003, 495.67502, 44.74, 495.67502);
        ((GeneralPath)shape).curveTo(45.087, 495.67502, 45.313004, 495.601, 45.419003, 495.44803);
        ((GeneralPath)shape).curveTo(45.522003, 495.29803, 45.575, 494.97302, 45.575, 494.47504);
        ((GeneralPath)shape).curveTo(45.575, 493.96503, 45.522, 493.63504, 45.417, 493.48703);
        ((GeneralPath)shape).curveTo(45.312, 493.339, 45.078, 493.265, 44.713, 493.265);
        ((GeneralPath)shape).moveTo(42.955, 492.604);
        ((GeneralPath)shape).lineTo(43.863003, 492.604);
        ((GeneralPath)shape).lineTo(43.828003, 493.159);
        ((GeneralPath)shape).lineTo(43.848003, 493.163);
        ((GeneralPath)shape).curveTo(44.062004, 492.761, 44.464005, 492.55798, 45.053005, 492.55798);
        ((GeneralPath)shape).curveTo(45.595005, 492.55798, 45.969006, 492.693, 46.174004, 492.96198);
        ((GeneralPath)shape).curveTo(46.377003, 493.23196, 46.480003, 493.722, 46.480003, 494.43698);
        ((GeneralPath)shape).curveTo(46.480003, 495.18097, 46.379, 495.69098, 46.176003, 495.968);
        ((GeneralPath)shape).curveTo(45.973003, 496.24298, 45.599003, 496.382, 45.050003, 496.382);
        ((GeneralPath)shape).curveTo(44.465004, 496.382, 44.083004, 496.18698, 43.904003, 495.796);
        ((GeneralPath)shape).lineTo(43.889004, 495.796);
        ((GeneralPath)shape).lineTo(43.889004, 497.94098);
        ((GeneralPath)shape).lineTo(42.957005, 497.94098);
        ((GeneralPath)shape).lineTo(42.957005, 492.604);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_72;
        g.setTransform(defaultTransform__0_72);
        g.setClip(clip__0_72);
        float alpha__0_73 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_73 = g.getClip();
        AffineTransform defaultTransform__0_73 = g.getTransform();
        
//      _0_73 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(49.635, 494.085);
        ((GeneralPath)shape).lineTo(49.630997, 493.93698);
        ((GeneralPath)shape).curveTo(49.630997, 493.63998, 49.580997, 493.44897, 49.476997, 493.35898);
        ((GeneralPath)shape).curveTo(49.374996, 493.27097, 49.150997, 493.22598, 48.809, 493.22598);
        ((GeneralPath)shape).curveTo(48.476997, 493.22598, 48.260998, 493.279, 48.16, 493.386);
        ((GeneralPath)shape).curveTo(48.061, 493.491, 48.01, 493.72598, 48.01, 494.085);
        ((GeneralPath)shape).lineTo(49.635, 494.085);
        ((GeneralPath)shape).moveTo(49.627, 495.144);
        ((GeneralPath)shape).lineTo(50.524, 495.144);
        ((GeneralPath)shape).lineTo(50.524, 495.289);
        ((GeneralPath)shape).curveTo(50.524, 496.018, 49.977997, 496.383, 48.885998, 496.383);
        ((GeneralPath)shape).curveTo(48.144997, 496.383, 47.662, 496.258, 47.432, 496.004);
        ((GeneralPath)shape).curveTo(47.204, 495.75198, 47.089, 495.217, 47.089, 494.399);
        ((GeneralPath)shape).curveTo(47.089, 493.672, 47.208, 493.184, 47.448, 492.934);
        ((GeneralPath)shape).curveTo(47.686, 492.684, 48.154003, 492.559, 48.848003, 492.559);
        ((GeneralPath)shape).curveTo(49.513004, 492.559, 49.959003, 492.68, 50.185005, 492.92398);
        ((GeneralPath)shape).curveTo(50.411007, 493.166, 50.524006, 493.645, 50.524006, 494.36);
        ((GeneralPath)shape).lineTo(50.524006, 494.633);
        ((GeneralPath)shape).lineTo(48.002007, 494.633);
        ((GeneralPath)shape).curveTo(47.998005, 494.715, 47.994007, 494.77, 47.994007, 494.797);
        ((GeneralPath)shape).curveTo(47.994007, 495.164, 48.050007, 495.408, 48.163006, 495.531);
        ((GeneralPath)shape).curveTo(48.276005, 495.652, 48.500008, 495.715, 48.839005, 495.715);
        ((GeneralPath)shape).curveTo(49.167004, 495.715, 49.379005, 495.68, 49.478004, 495.608);
        ((GeneralPath)shape).curveTo(49.577, 495.536, 49.627, 495.382, 49.627, 495.144);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_73;
        g.setTransform(defaultTransform__0_73);
        g.setClip(clip__0_73);
        float alpha__0_74 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_74 = g.getClip();
        AffineTransform defaultTransform__0_74 = g.getTransform();
        
//      _0_74 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(52.12, 492.604);
        ((GeneralPath)shape).lineTo(52.12, 493.565);
        ((GeneralPath)shape).lineTo(51.226997, 493.565);
        ((GeneralPath)shape).lineTo(51.226997, 492.604);
        ((GeneralPath)shape).lineTo(52.12, 492.604);
        ((GeneralPath)shape).moveTo(52.12, 495.378);
        ((GeneralPath)shape).lineTo(52.12, 496.339);
        ((GeneralPath)shape).lineTo(51.226997, 496.339);
        ((GeneralPath)shape).lineTo(51.226997, 495.378);
        ((GeneralPath)shape).lineTo(52.12, 495.378);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_74;
        g.setTransform(defaultTransform__0_74);
        g.setClip(clip__0_74);
        float alpha__0_75 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_75 = g.getClip();
        AffineTransform defaultTransform__0_75 = g.getTransform();
        
//      _0_75 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(37.829, 504.29);
        ((GeneralPath)shape).lineTo(37.829, 508.716);
        ((GeneralPath)shape).lineTo(36.819, 508.716);
        ((GeneralPath)shape).lineTo(36.819, 504.29);
        ((GeneralPath)shape).lineTo(35.283, 504.29);
        ((GeneralPath)shape).lineTo(35.283, 503.384);
        ((GeneralPath)shape).lineTo(39.419, 503.384);
        ((GeneralPath)shape).lineTo(39.419, 504.29);
        ((GeneralPath)shape).lineTo(37.829, 504.29);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_75;
        g.setTransform(defaultTransform__0_75);
        g.setClip(clip__0_75);
        float alpha__0_76 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_76 = g.getClip();
        AffineTransform defaultTransform__0_76 = g.getTransform();
        
//      _0_76 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(41.534, 505.646);
        ((GeneralPath)shape).curveTo(41.163002, 505.646, 40.926, 505.712, 40.825, 505.847);
        ((GeneralPath)shape).curveTo(40.724, 505.97998, 40.673, 506.29398, 40.673, 506.78598);
        ((GeneralPath)shape).curveTo(40.673, 507.34897, 40.72, 507.69998, 40.818, 507.84097);
        ((GeneralPath)shape).curveTo(40.914, 507.98196, 41.153, 508.05197, 41.539, 508.05197);
        ((GeneralPath)shape).curveTo(41.909, 508.05197, 42.145, 507.97797, 42.243, 507.82898);
        ((GeneralPath)shape).curveTo(42.341, 507.68097, 42.389, 507.32498, 42.389, 506.75897);
        ((GeneralPath)shape).curveTo(42.389, 506.28198, 42.338, 505.97797, 42.237, 505.84497);
        ((GeneralPath)shape).curveTo(42.135, 505.712, 41.901, 505.646, 41.534, 505.646);
        ((GeneralPath)shape).moveTo(41.542, 504.938);
        ((GeneralPath)shape).curveTo(42.261, 504.938, 42.733, 505.05298, 42.957, 505.284);
        ((GeneralPath)shape).curveTo(43.179, 505.514, 43.292, 506.001, 43.292, 506.743);
        ((GeneralPath)shape).curveTo(43.292, 507.571, 43.183, 508.114, 42.964, 508.372);
        ((GeneralPath)shape).curveTo(42.745003, 508.63, 42.286, 508.759, 41.584, 508.759);
        ((GeneralPath)shape).curveTo(40.824, 508.759, 40.331, 508.64, 40.105, 508.4);
        ((GeneralPath)shape).curveTo(39.88, 508.162, 39.767998, 507.634, 39.767998, 506.818);
        ((GeneralPath)shape).curveTo(39.767998, 506.035, 39.877, 505.525, 40.1, 505.291);
        ((GeneralPath)shape).curveTo(40.32, 505.056, 40.801, 504.938, 41.542, 504.938);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_76;
        g.setTransform(defaultTransform__0_76);
        g.setClip(clip__0_76);
        float alpha__0_77 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_77 = g.getClip();
        AffineTransform defaultTransform__0_77 = g.getTransform();
        
//      _0_77 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(44.041, 504.981);
        ((GeneralPath)shape).lineTo(44.926, 504.981);
        ((GeneralPath)shape).lineTo(44.891, 505.61);
        ((GeneralPath)shape).lineTo(44.911, 505.61398);
        ((GeneralPath)shape).curveTo(45.084, 505.16498, 45.474, 504.938, 46.079998, 504.938);
        ((GeneralPath)shape).curveTo(46.961, 504.938, 47.401997, 505.348, 47.401997, 506.172);
        ((GeneralPath)shape).lineTo(47.401997, 508.715);
        ((GeneralPath)shape).lineTo(46.508995, 508.715);
        ((GeneralPath)shape).lineTo(46.508995, 506.324);
        ((GeneralPath)shape).lineTo(46.488995, 506.062);
        ((GeneralPath)shape).curveTo(46.447994, 505.785, 46.229996, 505.644, 45.833996, 505.644);
        ((GeneralPath)shape).curveTo(45.233997, 505.644, 44.933994, 505.92902, 44.933994, 506.49902);
        ((GeneralPath)shape).lineTo(44.933994, 508.71402);
        ((GeneralPath)shape).lineTo(44.040993, 508.71402);
        ((GeneralPath)shape).lineTo(44.040993, 504.981);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_77;
        g.setTransform(defaultTransform__0_77);
        g.setClip(clip__0_77);
        float alpha__0_78 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_78 = g.getClip();
        AffineTransform defaultTransform__0_78 = g.getTransform();
        
//      _0_78 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(51.383, 506.009);
        ((GeneralPath)shape).lineTo(50.51, 506.009);
        ((GeneralPath)shape).curveTo(50.507, 505.978, 50.503, 505.954, 50.498997, 505.939);
        ((GeneralPath)shape).curveTo(50.480995, 505.759, 50.430996, 505.648, 50.344997, 505.603);
        ((GeneralPath)shape).curveTo(50.260998, 505.56, 50.055996, 505.537, 49.731, 505.537);
        ((GeneralPath)shape).curveTo(49.267, 505.537, 49.032997, 505.68698, 49.032997, 505.99);
        ((GeneralPath)shape).curveTo(49.032997, 506.19498, 49.073997, 506.318, 49.156, 506.357);
        ((GeneralPath)shape).curveTo(49.238, 506.396, 49.515, 506.427, 49.989, 506.451);
        ((GeneralPath)shape).curveTo(50.623997, 506.482, 51.038998, 506.56598, 51.232998, 506.705);
        ((GeneralPath)shape).curveTo(51.424, 506.84198, 51.520996, 507.125, 51.520996, 507.55298);
        ((GeneralPath)shape).curveTo(51.520996, 508.00797, 51.394997, 508.32397, 51.136997, 508.49997);
        ((GeneralPath)shape).curveTo(50.881996, 508.67596, 50.421997, 508.76398, 49.759, 508.76398);
        ((GeneralPath)shape).curveTo(49.122997, 508.76398, 48.687, 508.68597, 48.453, 508.52597);
        ((GeneralPath)shape).curveTo(48.218998, 508.36798, 48.101997, 508.07098, 48.101997, 507.63498);
        ((GeneralPath)shape).lineTo(48.101997, 507.541);
        ((GeneralPath)shape).lineTo(49.03, 507.541);
        ((GeneralPath)shape).curveTo(49.017998, 507.59198, 49.010998, 507.63498, 49.007, 507.666);
        ((GeneralPath)shape).curveTo(48.972, 507.99597, 49.225, 508.162, 49.771, 508.162);
        ((GeneralPath)shape).curveTo(50.334, 508.162, 50.617, 507.998, 50.617, 507.66998);
        ((GeneralPath)shape).curveTo(50.617, 507.356, 50.441, 507.197, 50.087, 507.197);
        ((GeneralPath)shape).curveTo(49.29, 507.197, 48.763, 507.123, 48.510002, 506.97);
        ((GeneralPath)shape).curveTo(48.256, 506.82, 48.13, 506.505, 48.13, 506.029);
        ((GeneralPath)shape).curveTo(48.13, 505.603, 48.245003, 505.314, 48.477, 505.162);
        ((GeneralPath)shape).curveTo(48.707, 505.012, 49.152, 504.935, 49.811, 504.935);
        ((GeneralPath)shape).curveTo(50.431, 504.935, 50.850002, 505.007, 51.064, 505.154);
        ((GeneralPath)shape).curveTo(51.276, 505.298, 51.383, 505.583, 51.383, 506.009);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_78;
        g.setTransform(defaultTransform__0_78);
        g.setClip(clip__0_78);
        float alpha__0_79 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_79 = g.getClip();
        AffineTransform defaultTransform__0_79 = g.getTransform();
        
//      _0_79 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(53.078, 504.981);
        ((GeneralPath)shape).lineTo(53.078, 505.942);
        ((GeneralPath)shape).lineTo(52.184998, 505.942);
        ((GeneralPath)shape).lineTo(52.184998, 504.981);
        ((GeneralPath)shape).lineTo(53.078, 504.981);
        ((GeneralPath)shape).moveTo(53.078, 507.755);
        ((GeneralPath)shape).lineTo(53.078, 508.716);
        ((GeneralPath)shape).lineTo(52.184998, 508.716);
        ((GeneralPath)shape).lineTo(52.184998, 507.755);
        ((GeneralPath)shape).lineTo(53.078, 507.755);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_79;
        g.setTransform(defaultTransform__0_79);
        g.setClip(clip__0_79);
        float alpha__0_80 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_80 = g.getClip();
        AffineTransform defaultTransform__0_80 = g.getTransform();
        
//      _0_80 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(41.93, 515.759);
        ((GeneralPath)shape).lineTo(41.93, 521.092);
        ((GeneralPath)shape).lineTo(40.920002, 521.092);
        ((GeneralPath)shape).lineTo(40.920002, 518.186);
        ((GeneralPath)shape).curveTo(40.920002, 517.95496, 40.926003, 517.69196, 40.940002, 517.397);
        ((GeneralPath)shape).lineTo(40.960003, 516.99896);
        ((GeneralPath)shape).lineTo(40.980003, 516.60394);
        ((GeneralPath)shape).lineTo(40.949005, 516.60394);
        ((GeneralPath)shape).lineTo(40.828007, 516.9749);
        ((GeneralPath)shape).lineTo(40.711006, 517.3449);
        ((GeneralPath)shape).curveTo(40.602005, 517.6779, 40.518005, 517.9239, 40.458008, 518.0839);
        ((GeneralPath)shape).lineTo(39.28901, 521.0919);
        ((GeneralPath)shape).lineTo(38.36901, 521.0919);
        ((GeneralPath)shape).lineTo(37.18801, 518.1079);
        ((GeneralPath)shape).curveTo(37.124012, 517.9439, 37.03801, 517.69794, 36.93101, 517.36993);
        ((GeneralPath)shape).lineTo(36.810013, 516.99896);
        ((GeneralPath)shape).lineTo(36.689014, 516.63196);
        ((GeneralPath)shape).lineTo(36.658016, 516.63196);
        ((GeneralPath)shape).lineTo(36.678017, 517.019);
        ((GeneralPath)shape).lineTo(36.698017, 517.41);
        ((GeneralPath)shape).curveTo(36.714016, 517.711, 36.721016, 517.97095, 36.721016, 518.18695);
        ((GeneralPath)shape).lineTo(36.721016, 521.09296);
        ((GeneralPath)shape).lineTo(35.711018, 521.09296);
        ((GeneralPath)shape).lineTo(35.711018, 515.75995);
        ((GeneralPath)shape).lineTo(37.356018, 515.75995);
        ((GeneralPath)shape).lineTo(38.30702, 518.2299);
        ((GeneralPath)shape).curveTo(38.371017, 518.40094, 38.45702, 518.64795, 38.56402, 518.9679);
        ((GeneralPath)shape).lineTo(38.68102, 519.33887);
        ((GeneralPath)shape).lineTo(38.802017, 519.7049);
        ((GeneralPath)shape).lineTo(38.837017, 519.7049);
        ((GeneralPath)shape).lineTo(38.950016, 519.33887);
        ((GeneralPath)shape).lineTo(39.067017, 518.9709);
        ((GeneralPath)shape).curveTo(39.162018, 518.6629, 39.246017, 518.4159, 39.317017, 518.2369);
        ((GeneralPath)shape).lineTo(40.252018, 515.7599);
        ((GeneralPath)shape).lineTo(41.93, 515.7599);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_80;
        g.setTransform(defaultTransform__0_80);
        g.setClip(clip__0_80);
        float alpha__0_81 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_81 = g.getClip();
        AffineTransform defaultTransform__0_81 = g.getTransform();
        
//      _0_81 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(44.464, 518.021);
        ((GeneralPath)shape).curveTo(44.093002, 518.021, 43.856, 518.087, 43.754, 518.222);
        ((GeneralPath)shape).curveTo(43.653, 518.355, 43.602, 518.669, 43.602, 519.161);
        ((GeneralPath)shape).curveTo(43.602, 519.724, 43.649002, 520.075, 43.747, 520.216);
        ((GeneralPath)shape).curveTo(43.843002, 520.357, 44.082, 520.427, 44.468002, 520.427);
        ((GeneralPath)shape).curveTo(44.838, 520.427, 45.074, 520.353, 45.172, 520.203);
        ((GeneralPath)shape).curveTo(45.27, 520.055, 45.318, 519.7, 45.318, 519.133);
        ((GeneralPath)shape).curveTo(45.318, 518.657, 45.267002, 518.35297, 45.166, 518.219);
        ((GeneralPath)shape).curveTo(45.065, 518.088, 44.831, 518.021, 44.464, 518.021);
        ((GeneralPath)shape).moveTo(44.472, 517.313);
        ((GeneralPath)shape).curveTo(45.191, 517.313, 45.663002, 517.429, 45.887, 517.66);
        ((GeneralPath)shape).curveTo(46.109, 517.88995, 46.222, 518.37695, 46.222, 519.11896);
        ((GeneralPath)shape).curveTo(46.222, 519.94696, 46.113, 520.4899, 45.894, 520.748);
        ((GeneralPath)shape).curveTo(45.675003, 521.00604, 45.216, 521.134, 44.514, 521.134);
        ((GeneralPath)shape).curveTo(43.753, 521.134, 43.261, 521.016, 43.034, 520.77496);
        ((GeneralPath)shape).curveTo(42.81, 520.53796, 42.697, 520.009, 42.697, 519.194);
        ((GeneralPath)shape).curveTo(42.697, 518.41095, 42.806, 517.89996, 43.029, 517.66595);
        ((GeneralPath)shape).curveTo(43.25, 517.431, 43.731, 517.313, 44.472, 517.313);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_81;
        g.setTransform(defaultTransform__0_81);
        g.setClip(clip__0_81);
        float alpha__0_82 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_82 = g.getClip();
        AffineTransform defaultTransform__0_82 = g.getTransform();
        
//      _0_82 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(50.078, 517.357);
        ((GeneralPath)shape).lineTo(49.01, 521.091);
        ((GeneralPath)shape).lineTo(47.614, 521.091);
        ((GeneralPath)shape).lineTo(46.490997, 517.357);
        ((GeneralPath)shape).lineTo(47.439, 517.357);
        ((GeneralPath)shape).lineTo(47.93, 519.079);
        ((GeneralPath)shape).curveTo(47.996002, 519.318, 48.058, 519.55096, 48.117, 519.774);
        ((GeneralPath)shape).lineTo(48.207, 520.123);
        ((GeneralPath)shape).lineTo(48.296, 520.47);
        ((GeneralPath)shape).lineTo(48.316, 520.47);
        ((GeneralPath)shape).lineTo(48.398003, 520.123);
        ((GeneralPath)shape).lineTo(48.480003, 519.779);
        ((GeneralPath)shape).curveTo(48.542004, 519.519, 48.601, 519.287, 48.655003, 519.084);
        ((GeneralPath)shape).lineTo(49.115, 517.357);
        ((GeneralPath)shape).lineTo(50.078, 517.357);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_82;
        g.setTransform(defaultTransform__0_82);
        g.setClip(clip__0_82);
        float alpha__0_83 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_83 = g.getClip();
        AffineTransform defaultTransform__0_83 = g.getTransform();
        
//      _0_83 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(52.885, 518.838);
        ((GeneralPath)shape).lineTo(52.880997, 518.689);
        ((GeneralPath)shape).curveTo(52.880997, 518.393, 52.829998, 518.202, 52.726997, 518.111);
        ((GeneralPath)shape).curveTo(52.625996, 518.02405, 52.401997, 517.979, 52.059, 517.979);
        ((GeneralPath)shape).curveTo(51.726997, 517.979, 51.510998, 518.032, 51.41, 518.139);
        ((GeneralPath)shape).curveTo(51.31, 518.24396, 51.26, 518.479, 51.26, 518.83795);
        ((GeneralPath)shape).lineTo(52.885, 518.83795);
        ((GeneralPath)shape).moveTo(52.877, 519.896);
        ((GeneralPath)shape).lineTo(53.774, 519.896);
        ((GeneralPath)shape).lineTo(53.774, 520.04);
        ((GeneralPath)shape).curveTo(53.774, 520.769, 53.227997, 521.134, 52.135998, 521.134);
        ((GeneralPath)shape).curveTo(51.394997, 521.134, 50.911, 521.009, 50.682, 520.756);
        ((GeneralPath)shape).curveTo(50.454, 520.50397, 50.339, 519.969, 50.339, 519.14996);
        ((GeneralPath)shape).curveTo(50.339, 518.42395, 50.458, 517.936, 50.698, 517.686);
        ((GeneralPath)shape).curveTo(50.936, 517.436, 51.404003, 517.311, 52.097, 517.311);
        ((GeneralPath)shape).curveTo(52.762, 517.311, 53.208, 517.43097, 53.434002, 517.67596);
        ((GeneralPath)shape).curveTo(53.660004, 517.91797, 53.773003, 518.397, 53.773003, 519.11096);
        ((GeneralPath)shape).lineTo(53.773003, 519.384);
        ((GeneralPath)shape).lineTo(51.250004, 519.384);
        ((GeneralPath)shape).curveTo(51.246002, 519.467, 51.242004, 519.522, 51.242004, 519.54895);
        ((GeneralPath)shape).curveTo(51.242004, 519.915, 51.299004, 520.16, 51.412003, 520.28296);
        ((GeneralPath)shape).curveTo(51.525, 520.40393, 51.749004, 520.467, 52.088, 520.467);
        ((GeneralPath)shape).curveTo(52.416, 520.467, 52.628002, 520.43097, 52.727, 520.36);
        ((GeneralPath)shape).curveTo(52.827, 520.289, 52.877, 520.134, 52.877, 519.896);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_83;
        g.setTransform(defaultTransform__0_83);
        g.setClip(clip__0_83);
        float alpha__0_84 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_84 = g.getClip();
        AffineTransform defaultTransform__0_84 = g.getTransform();
        
//      _0_84 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(54.516, 517.357);
        ((GeneralPath)shape).lineTo(55.409, 517.357);
        ((GeneralPath)shape).lineTo(55.386, 517.93);
        ((GeneralPath)shape).lineTo(55.406002, 517.935);
        ((GeneralPath)shape).curveTo(55.588, 517.521, 55.968002, 517.313, 56.544003, 517.313);
        ((GeneralPath)shape).curveTo(57.217003, 517.313, 57.591003, 517.544, 57.667004, 518.005);
        ((GeneralPath)shape).lineTo(57.683002, 518.005);
        ((GeneralPath)shape).curveTo(57.856003, 517.544, 58.241, 517.313, 58.833004, 517.313);
        ((GeneralPath)shape).curveTo(59.695004, 517.313, 60.127003, 517.748, 60.127003, 518.619);
        ((GeneralPath)shape).lineTo(60.127003, 521.09204);
        ((GeneralPath)shape).lineTo(59.234, 521.09204);
        ((GeneralPath)shape).lineTo(59.234, 518.814);
        ((GeneralPath)shape).curveTo(59.234, 518.288, 59.018, 518.02203, 58.583, 518.02203);
        ((GeneralPath)shape).curveTo(58.041, 518.02203, 57.768, 518.317, 57.768, 518.90906);
        ((GeneralPath)shape).lineTo(57.768, 521.0931);
        ((GeneralPath)shape).lineTo(56.875, 521.0931);
        ((GeneralPath)shape).lineTo(56.875, 518.7801);
        ((GeneralPath)shape).curveTo(56.875, 518.4701, 56.834, 518.2661, 56.752, 518.16907);
        ((GeneralPath)shape).curveTo(56.67, 518.072, 56.496998, 518.0231, 56.231, 518.0231);
        ((GeneralPath)shape).curveTo(55.683, 518.0231, 55.407997, 518.3241, 55.407997, 518.9291);
        ((GeneralPath)shape).lineTo(55.407997, 521.0931);
        ((GeneralPath)shape).lineTo(54.514996, 521.0931);
        ((GeneralPath)shape).lineTo(54.514996, 517.357);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_84;
        g.setTransform(defaultTransform__0_84);
        g.setClip(clip__0_84);
        float alpha__0_85 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_85 = g.getClip();
        AffineTransform defaultTransform__0_85 = g.getTransform();
        
//      _0_85 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(63.449, 518.838);
        ((GeneralPath)shape).lineTo(63.445, 518.689);
        ((GeneralPath)shape).curveTo(63.445, 518.393, 63.395, 518.202, 63.291, 518.111);
        ((GeneralPath)shape).curveTo(63.189, 518.02405, 62.966, 517.979, 62.623, 517.979);
        ((GeneralPath)shape).curveTo(62.291, 517.979, 62.075, 518.032, 61.974003, 518.139);
        ((GeneralPath)shape).curveTo(61.875004, 518.24396, 61.824, 518.479, 61.824, 518.83795);
        ((GeneralPath)shape).lineTo(63.449, 518.83795);
        ((GeneralPath)shape).moveTo(63.44, 519.896);
        ((GeneralPath)shape).lineTo(64.337, 519.896);
        ((GeneralPath)shape).lineTo(64.337, 520.04);
        ((GeneralPath)shape).curveTo(64.337, 520.769, 63.790997, 521.134, 62.699997, 521.134);
        ((GeneralPath)shape).curveTo(61.958996, 521.134, 61.475, 521.009, 61.244995, 520.756);
        ((GeneralPath)shape).curveTo(61.016994, 520.50397, 60.901997, 519.969, 60.901997, 519.14996);
        ((GeneralPath)shape).curveTo(60.901997, 518.42395, 61.020996, 517.936, 61.26, 517.686);
        ((GeneralPath)shape).curveTo(61.497997, 517.436, 61.966, 517.311, 62.66, 517.311);
        ((GeneralPath)shape).curveTo(63.325, 517.311, 63.771, 517.43097, 63.997, 517.67596);
        ((GeneralPath)shape).curveTo(64.223, 517.91797, 64.336, 518.397, 64.336, 519.11096);
        ((GeneralPath)shape).lineTo(64.336, 519.384);
        ((GeneralPath)shape).lineTo(61.814, 519.384);
        ((GeneralPath)shape).curveTo(61.809998, 519.467, 61.806, 519.522, 61.806, 519.54895);
        ((GeneralPath)shape).curveTo(61.806, 519.915, 61.863, 520.16, 61.975, 520.28296);
        ((GeneralPath)shape).curveTo(62.087997, 520.40393, 62.312, 520.467, 62.650997, 520.467);
        ((GeneralPath)shape).curveTo(62.978996, 520.467, 63.191998, 520.43097, 63.290997, 520.36);
        ((GeneralPath)shape).curveTo(63.39, 520.289, 63.44, 520.134, 63.44, 519.896);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_85;
        g.setTransform(defaultTransform__0_85);
        g.setClip(clip__0_85);
        float alpha__0_86 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_86 = g.getClip();
        AffineTransform defaultTransform__0_86 = g.getTransform();
        
//      _0_86 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(65.08, 517.357);
        ((GeneralPath)shape).lineTo(65.965004, 517.357);
        ((GeneralPath)shape).lineTo(65.93, 517.985);
        ((GeneralPath)shape).lineTo(65.95, 517.99);
        ((GeneralPath)shape).curveTo(66.12299, 517.54, 66.513, 517.313, 67.118996, 517.313);
        ((GeneralPath)shape).curveTo(67.99999, 517.313, 68.439995, 517.724, 68.439995, 518.548);
        ((GeneralPath)shape).lineTo(68.439995, 521.091);
        ((GeneralPath)shape).lineTo(67.547, 521.091);
        ((GeneralPath)shape).lineTo(67.547, 518.7);
        ((GeneralPath)shape).lineTo(67.527, 518.437);
        ((GeneralPath)shape).curveTo(67.486, 518.161, 67.268, 518.02, 66.872, 518.02);
        ((GeneralPath)shape).curveTo(66.272, 518.02, 65.972, 518.304, 65.972, 518.875);
        ((GeneralPath)shape).lineTo(65.972, 521.09);
        ((GeneralPath)shape).lineTo(65.08, 521.09);
        ((GeneralPath)shape).lineTo(65.08, 517.357);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_86;
        g.setTransform(defaultTransform__0_86);
        g.setClip(clip__0_86);
        float alpha__0_87 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_87 = g.getClip();
        AffineTransform defaultTransform__0_87 = g.getTransform();
        
//      _0_87 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(71.716, 517.357);
        ((GeneralPath)shape).lineTo(71.716, 518.037);
        ((GeneralPath)shape).lineTo(70.281006, 518.037);
        ((GeneralPath)shape).lineTo(70.281006, 519.912);
        ((GeneralPath)shape).curveTo(70.281006, 520.258, 70.412, 520.43097, 70.675, 520.43097);
        ((GeneralPath)shape).curveTo(70.964005, 520.43097, 71.108, 520.22296, 71.108, 519.803);
        ((GeneralPath)shape).lineTo(71.108, 519.65497);
        ((GeneralPath)shape).lineTo(71.868004, 519.65497);
        ((GeneralPath)shape).lineTo(71.868004, 519.84296);
        ((GeneralPath)shape).curveTo(71.868004, 520.01495, 71.864006, 520.16095, 71.852005, 520.28394);
        ((GeneralPath)shape).curveTo(71.803, 520.85394, 71.380005, 521.1389, 70.581, 521.1389);
        ((GeneralPath)shape).curveTo(69.786, 521.1389, 69.388, 520.7739, 69.388, 520.0399);
        ((GeneralPath)shape).lineTo(69.388, 518.0369);
        ((GeneralPath)shape).lineTo(68.905, 518.0369);
        ((GeneralPath)shape).lineTo(68.905, 517.35693);
        ((GeneralPath)shape).lineTo(69.388, 517.35693);
        ((GeneralPath)shape).lineTo(69.388, 516.52094);
        ((GeneralPath)shape).lineTo(70.281, 516.52094);
        ((GeneralPath)shape).lineTo(70.281, 517.35693);
        ((GeneralPath)shape).lineTo(71.716, 517.35693);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_87;
        g.setTransform(defaultTransform__0_87);
        g.setClip(clip__0_87);
        float alpha__0_88 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_88 = g.getClip();
        AffineTransform defaultTransform__0_88 = g.getTransform();
        
//      _0_88 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(75.394, 518.518);
        ((GeneralPath)shape).lineTo(76.544, 518.518);
        ((GeneralPath)shape).curveTo(77.0, 518.518, 77.291, 518.465, 77.416, 518.36);
        ((GeneralPath)shape).curveTo(77.539, 518.255, 77.601, 518.006, 77.601, 517.61096);
        ((GeneralPath)shape).curveTo(77.601, 517.16296, 77.549995, 516.88293, 77.447, 516.774);
        ((GeneralPath)shape).curveTo(77.345, 516.666, 77.077995, 516.61096, 76.645996, 516.61096);
        ((GeneralPath)shape).lineTo(75.395, 516.61096);
        ((GeneralPath)shape).lineTo(75.395, 518.518);
        ((GeneralPath)shape).moveTo(74.384, 521.092);
        ((GeneralPath)shape).lineTo(74.384, 515.759);
        ((GeneralPath)shape).lineTo(76.793, 515.759);
        ((GeneralPath)shape).curveTo(77.512, 515.759, 77.998, 515.884, 78.249, 516.13696);
        ((GeneralPath)shape).curveTo(78.499, 516.38696, 78.625, 516.873, 78.625, 517.592);
        ((GeneralPath)shape).curveTo(78.625, 518.305, 78.506, 518.77997, 78.266, 519.014);
        ((GeneralPath)shape).curveTo(78.029, 519.248, 77.545, 519.36597, 76.816, 519.36597);
        ((GeneralPath)shape).lineTo(76.582, 519.37);
        ((GeneralPath)shape).lineTo(75.393, 519.37);
        ((GeneralPath)shape).lineTo(75.393, 521.093);
        ((GeneralPath)shape).lineTo(74.384, 521.093);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_88;
        g.setTransform(defaultTransform__0_88);
        g.setClip(clip__0_88);
        float alpha__0_89 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_89 = g.getClip();
        AffineTransform defaultTransform__0_89 = g.getTransform();
        
//      _0_89 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(80.873, 518.021);
        ((GeneralPath)shape).curveTo(80.503, 518.021, 80.265, 518.087, 80.164, 518.222);
        ((GeneralPath)shape).curveTo(80.063, 518.355, 80.012, 518.669, 80.012, 519.161);
        ((GeneralPath)shape).curveTo(80.012, 519.724, 80.059, 520.075, 80.157, 520.216);
        ((GeneralPath)shape).curveTo(80.252, 520.357, 80.492, 520.427, 80.878, 520.427);
        ((GeneralPath)shape).curveTo(81.249, 520.427, 81.484, 520.353, 81.582, 520.203);
        ((GeneralPath)shape).curveTo(81.68, 520.055, 81.728004, 519.7, 81.728004, 519.133);
        ((GeneralPath)shape).curveTo(81.728004, 518.657, 81.677, 518.35297, 81.576004, 518.219);
        ((GeneralPath)shape).curveTo(81.473, 518.088, 81.239, 518.021, 80.873, 518.021);
        ((GeneralPath)shape).moveTo(80.881, 517.313);
        ((GeneralPath)shape).curveTo(81.6, 517.313, 82.071, 517.429, 82.296, 517.66);
        ((GeneralPath)shape).curveTo(82.518, 517.88995, 82.631, 518.37695, 82.631, 519.11896);
        ((GeneralPath)shape).curveTo(82.631, 519.94696, 82.521996, 520.4899, 82.30399, 520.748);
        ((GeneralPath)shape).curveTo(82.08599, 521.00604, 81.62499, 521.134, 80.923996, 521.134);
        ((GeneralPath)shape).curveTo(80.163994, 521.134, 79.671, 521.016, 79.445, 520.77496);
        ((GeneralPath)shape).curveTo(79.221, 520.53796, 79.108, 520.009, 79.108, 519.194);
        ((GeneralPath)shape).curveTo(79.108, 518.41095, 79.217, 517.89996, 79.44, 517.66595);
        ((GeneralPath)shape).curveTo(79.658, 517.431, 80.14, 517.313, 80.881, 517.313);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_89;
        g.setTransform(defaultTransform__0_89);
        g.setClip(clip__0_89);
        float alpha__0_90 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_90 = g.getClip();
        AffineTransform defaultTransform__0_90 = g.getTransform();
        
//      _0_90 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(84.271, 517.357);
        ((GeneralPath)shape).lineTo(84.271, 521.091);
        ((GeneralPath)shape).lineTo(83.378006, 521.091);
        ((GeneralPath)shape).lineTo(83.378006, 517.357);
        ((GeneralPath)shape).lineTo(84.271, 517.357);
        ((GeneralPath)shape).moveTo(84.271, 515.759);
        ((GeneralPath)shape).lineTo(84.271, 516.506);
        ((GeneralPath)shape).lineTo(83.378006, 516.506);
        ((GeneralPath)shape).lineTo(83.378006, 515.759);
        ((GeneralPath)shape).lineTo(84.271, 515.759);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_90;
        g.setTransform(defaultTransform__0_90);
        g.setClip(clip__0_90);
        float alpha__0_91 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_91 = g.getClip();
        AffineTransform defaultTransform__0_91 = g.getTransform();
        
//      _0_91 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(85.152, 517.357);
        ((GeneralPath)shape).lineTo(86.037, 517.357);
        ((GeneralPath)shape).lineTo(86.002, 517.985);
        ((GeneralPath)shape).lineTo(86.021996, 517.99);
        ((GeneralPath)shape).curveTo(86.19499, 517.54, 86.585, 517.313, 87.19199, 517.313);
        ((GeneralPath)shape).curveTo(88.07299, 517.313, 88.51299, 517.724, 88.51299, 518.548);
        ((GeneralPath)shape).lineTo(88.51299, 521.091);
        ((GeneralPath)shape).lineTo(87.62, 521.091);
        ((GeneralPath)shape).lineTo(87.62, 518.7);
        ((GeneralPath)shape).lineTo(87.600006, 518.437);
        ((GeneralPath)shape).curveTo(87.559006, 518.161, 87.341, 518.02, 86.94501, 518.02);
        ((GeneralPath)shape).curveTo(86.34501, 518.02, 86.045006, 518.304, 86.045006, 518.875);
        ((GeneralPath)shape).lineTo(86.045006, 521.09);
        ((GeneralPath)shape).lineTo(85.15201, 521.09);
        ((GeneralPath)shape).lineTo(85.15201, 517.357);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_91;
        g.setTransform(defaultTransform__0_91);
        g.setClip(clip__0_91);
        float alpha__0_92 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_92 = g.getClip();
        AffineTransform defaultTransform__0_92 = g.getTransform();
        
//      _0_92 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(91.789, 517.357);
        ((GeneralPath)shape).lineTo(91.789, 518.037);
        ((GeneralPath)shape).lineTo(90.354004, 518.037);
        ((GeneralPath)shape).lineTo(90.354004, 519.912);
        ((GeneralPath)shape).curveTo(90.354004, 520.258, 90.484, 520.43097, 90.748, 520.43097);
        ((GeneralPath)shape).curveTo(91.037, 520.43097, 91.181, 520.22296, 91.181, 519.803);
        ((GeneralPath)shape).lineTo(91.181, 519.65497);
        ((GeneralPath)shape).lineTo(91.941, 519.65497);
        ((GeneralPath)shape).lineTo(91.941, 519.84296);
        ((GeneralPath)shape).curveTo(91.941, 520.01495, 91.937004, 520.16095, 91.925, 520.28394);
        ((GeneralPath)shape).curveTo(91.876, 520.85394, 91.453, 521.1389, 90.654, 521.1389);
        ((GeneralPath)shape).curveTo(89.859, 521.1389, 89.461, 520.7739, 89.461, 520.0399);
        ((GeneralPath)shape).lineTo(89.461, 518.0369);
        ((GeneralPath)shape).lineTo(88.978, 518.0369);
        ((GeneralPath)shape).lineTo(88.978, 517.35693);
        ((GeneralPath)shape).lineTo(89.461, 517.35693);
        ((GeneralPath)shape).lineTo(89.461, 516.52094);
        ((GeneralPath)shape).lineTo(90.354, 516.52094);
        ((GeneralPath)shape).lineTo(90.354, 517.35693);
        ((GeneralPath)shape).lineTo(91.789, 517.35693);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_92;
        g.setTransform(defaultTransform__0_92);
        g.setClip(clip__0_92);
        float alpha__0_93 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_93 = g.getClip();
        AffineTransform defaultTransform__0_93 = g.getTransform();
        
//      _0_93 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(95.544, 518.384);
        ((GeneralPath)shape).lineTo(94.67, 518.384);
        ((GeneralPath)shape).curveTo(94.666, 518.35394, 94.662994, 518.329, 94.658, 518.31396);
        ((GeneralPath)shape).curveTo(94.641, 518.134, 94.59, 518.024, 94.504, 517.97894);
        ((GeneralPath)shape).curveTo(94.42, 517.9359, 94.215, 517.91296, 93.89, 517.91296);
        ((GeneralPath)shape).curveTo(93.426, 517.91296, 93.192, 518.063, 93.192, 518.36597);
        ((GeneralPath)shape).curveTo(93.192, 518.571, 93.233, 518.694, 93.315, 518.733);
        ((GeneralPath)shape).curveTo(93.397, 518.772, 93.674, 518.803, 94.148, 518.82697);
        ((GeneralPath)shape).curveTo(94.783005, 518.858, 95.198006, 518.94196, 95.392006, 519.07996);
        ((GeneralPath)shape).curveTo(95.58201, 519.21796, 95.68001, 519.501, 95.68001, 519.92896);
        ((GeneralPath)shape).curveTo(95.68001, 520.384, 95.55401, 520.69995, 95.296005, 520.876);
        ((GeneralPath)shape).curveTo(95.04101, 521.052, 94.58101, 521.13995, 93.91801, 521.13995);
        ((GeneralPath)shape).curveTo(93.283005, 521.13995, 92.84601, 521.06195, 92.61201, 520.90094);
        ((GeneralPath)shape).curveTo(92.378006, 520.74396, 92.26101, 520.44696, 92.26101, 520.00995);
        ((GeneralPath)shape).lineTo(92.26101, 519.91595);
        ((GeneralPath)shape).lineTo(93.18901, 519.91595);
        ((GeneralPath)shape).curveTo(93.17701, 519.96796, 93.169014, 520.00995, 93.16601, 520.04095);
        ((GeneralPath)shape).curveTo(93.131004, 520.37195, 93.38401, 520.53796, 93.93001, 520.53796);
        ((GeneralPath)shape).curveTo(94.49301, 520.53796, 94.77601, 520.37396, 94.77601, 520.04596);
        ((GeneralPath)shape).curveTo(94.77601, 519.73193, 94.600006, 519.57294, 94.24601, 519.57294);
        ((GeneralPath)shape).curveTo(93.44901, 519.57294, 92.92201, 519.49896, 92.66901, 519.3449);
        ((GeneralPath)shape).curveTo(92.41501, 519.1959, 92.28901, 518.8809, 92.28901, 518.4049);
        ((GeneralPath)shape).curveTo(92.28901, 517.9789, 92.40401, 517.6889, 92.63601, 517.5379);
        ((GeneralPath)shape).curveTo(92.86601, 517.3879, 93.31101, 517.3109, 93.97001, 517.3109);
        ((GeneralPath)shape).curveTo(94.59001, 517.3109, 95.00901, 517.38293, 95.22301, 517.5299);
        ((GeneralPath)shape).curveTo(95.438, 517.674, 95.544, 517.959, 95.544, 518.384);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_93;
        g.setTransform(defaultTransform__0_93);
        g.setClip(clip__0_93);
        float alpha__0_94 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_94 = g.getClip();
        AffineTransform defaultTransform__0_94 = g.getTransform();
        
//      _0_94 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(97.24, 517.357);
        ((GeneralPath)shape).lineTo(97.24, 518.318);
        ((GeneralPath)shape).lineTo(96.347, 518.318);
        ((GeneralPath)shape).lineTo(96.347, 517.357);
        ((GeneralPath)shape).lineTo(97.24, 517.357);
        ((GeneralPath)shape).moveTo(97.24, 520.131);
        ((GeneralPath)shape).lineTo(97.24, 521.092);
        ((GeneralPath)shape).lineTo(96.347, 521.092);
        ((GeneralPath)shape).lineTo(96.347, 520.131);
        ((GeneralPath)shape).lineTo(97.24, 520.131);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_94;
        g.setTransform(defaultTransform__0_94);
        g.setClip(clip__0_94);
        float alpha__0_95 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_95 = g.getClip();
        AffineTransform defaultTransform__0_95 = g.getTransform();
        
//      _0_95 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(53.378, 528.135);
        ((GeneralPath)shape).lineTo(51.954998, 533.467);
        ((GeneralPath)shape).lineTo(50.461998, 533.467);
        ((GeneralPath)shape).lineTo(49.720997, 530.733);
        ((GeneralPath)shape).curveTo(49.654995, 530.487, 49.574997, 530.15497, 49.482998, 529.74097);
        ((GeneralPath)shape).lineTo(49.408997, 529.41296);
        ((GeneralPath)shape).lineTo(49.373997, 529.41296);
        ((GeneralPath)shape).lineTo(49.295998, 529.74493);
        ((GeneralPath)shape).lineTo(49.221996, 530.07294);
        ((GeneralPath)shape).curveTo(49.167995, 530.29395, 49.108997, 530.5139, 49.046997, 530.73694);
        ((GeneralPath)shape).lineTo(48.287, 533.4669);
        ((GeneralPath)shape).lineTo(46.809, 533.4669);
        ((GeneralPath)shape).lineTo(45.428997, 528.13495);
        ((GeneralPath)shape).lineTo(46.457996, 528.13495);
        ((GeneralPath)shape).lineTo(47.225998, 531.061);
        ((GeneralPath)shape).curveTo(47.273, 531.24896, 47.330997, 531.506, 47.400997, 531.83795);
        ((GeneralPath)shape).lineTo(47.482998, 532.22894);
        ((GeneralPath)shape).lineTo(47.560997, 532.61993);
        ((GeneralPath)shape).lineTo(47.595997, 532.61993);
        ((GeneralPath)shape).curveTo(47.636997, 532.44794, 47.667995, 532.3189, 47.688995, 532.22894);
        ((GeneralPath)shape).lineTo(47.782997, 531.8419);
        ((GeneralPath)shape).curveTo(47.831997, 531.6389, 47.899998, 531.3809, 47.989998, 531.06494);
        ((GeneralPath)shape).lineTo(48.809, 528.13495);
        ((GeneralPath)shape).lineTo(49.994, 528.13495);
        ((GeneralPath)shape).lineTo(50.793, 531.06494);
        ((GeneralPath)shape).curveTo(50.859, 531.31494, 50.925, 531.57294, 50.992, 531.8419);
        ((GeneralPath)shape).lineTo(51.082, 532.22894);
        ((GeneralPath)shape).lineTo(51.175, 532.61993);
        ((GeneralPath)shape).lineTo(51.205997, 532.61993);
        ((GeneralPath)shape).lineTo(51.291996, 532.22894);
        ((GeneralPath)shape).lineTo(51.373997, 531.83795);
        ((GeneralPath)shape).curveTo(51.44, 531.5239, 51.501995, 531.264, 51.556995, 531.05695);
        ((GeneralPath)shape).lineTo(52.344994, 528.13495);
        ((GeneralPath)shape).lineTo(53.378, 528.13495);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_95;
        g.setTransform(defaultTransform__0_95);
        g.setClip(clip__0_95);
        float alpha__0_96 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_96 = g.getClip();
        AffineTransform defaultTransform__0_96 = g.getTransform();
        
//      _0_96 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(55.346, 531.795);
        ((GeneralPath)shape).curveTo(54.843002, 531.795, 54.59, 531.969, 54.59, 532.318);
        ((GeneralPath)shape).curveTo(54.59, 532.56, 54.641, 532.718, 54.746, 532.795);
        ((GeneralPath)shape).curveTo(54.85, 532.86896, 55.067997, 532.90796, 55.400997, 532.90796);
        ((GeneralPath)shape).curveTo(55.942997, 532.90796, 56.214996, 532.72394, 56.214996, 532.35693);
        ((GeneralPath)shape).curveTo(56.215, 531.982, 55.927, 531.795, 55.346, 531.795);
        ((GeneralPath)shape).moveTo(54.75, 530.814);
        ((GeneralPath)shape).lineTo(53.837, 530.814);
        ((GeneralPath)shape).curveTo(53.837, 530.367, 53.941, 530.06805, 54.149002, 529.916);
        ((GeneralPath)shape).curveTo(54.356003, 529.766, 54.769, 529.689, 55.385002, 529.689);
        ((GeneralPath)shape).curveTo(56.055, 529.689, 56.508003, 529.781, 56.746002, 529.966);
        ((GeneralPath)shape).curveTo(56.981003, 530.15, 57.100002, 530.503, 57.100002, 531.025);
        ((GeneralPath)shape).lineTo(57.100002, 533.466);
        ((GeneralPath)shape).lineTo(56.207, 533.466);
        ((GeneralPath)shape).lineTo(56.249, 532.954);
        ((GeneralPath)shape).lineTo(56.226, 532.94995);
        ((GeneralPath)shape).curveTo(56.055, 533.3209, 55.659, 533.509, 55.037003, 533.509);
        ((GeneralPath)shape).curveTo(54.137, 533.509, 53.684002, 533.126, 53.684002, 532.357);
        ((GeneralPath)shape).curveTo(53.684002, 531.582, 54.144, 531.193, 55.068, 531.193);
        ((GeneralPath)shape).curveTo(55.684002, 531.193, 56.058002, 531.334, 56.191, 531.619);
        ((GeneralPath)shape).lineTo(56.207, 531.619);
        ((GeneralPath)shape).lineTo(56.207, 531.01404);
        ((GeneralPath)shape).curveTo(56.207, 530.723, 56.156002, 530.53, 56.055, 530.434);
        ((GeneralPath)shape).curveTo(55.953, 530.34, 55.747, 530.291, 55.431, 530.291);
        ((GeneralPath)shape).curveTo(54.978, 530.291, 54.75, 530.465, 54.75, 530.814);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_96;
        g.setTransform(defaultTransform__0_96);
        g.setClip(clip__0_96);
        float alpha__0_97 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_97 = g.getClip();
        AffineTransform defaultTransform__0_97 = g.getTransform();
        
//      _0_97 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new Rectangle2D.Double(57.98899841308594, 528.135009765625, 0.8930000066757202, 5.331999778747559);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_97;
        g.setTransform(defaultTransform__0_97);
        g.setClip(clip__0_97);
        float alpha__0_98 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_98 = g.getClip();
        AffineTransform defaultTransform__0_98 = g.getTransform();
        
//      _0_98 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(60.654, 528.135);
        ((GeneralPath)shape).lineTo(60.654, 531.186);
        ((GeneralPath)shape).lineTo(60.885, 531.186);
        ((GeneralPath)shape).lineTo(61.89, 529.732);
        ((GeneralPath)shape).lineTo(62.927, 529.732);
        ((GeneralPath)shape).lineTo(61.629, 531.49);
        ((GeneralPath)shape).lineTo(63.192, 533.467);
        ((GeneralPath)shape).lineTo(62.085, 533.467);
        ((GeneralPath)shape).lineTo(60.873, 531.811);
        ((GeneralPath)shape).lineTo(60.654, 531.811);
        ((GeneralPath)shape).lineTo(60.654, 533.467);
        ((GeneralPath)shape).lineTo(59.762, 533.467);
        ((GeneralPath)shape).lineTo(59.762, 528.135);
        ((GeneralPath)shape).lineTo(60.654, 528.135);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_98;
        g.setTransform(defaultTransform__0_98);
        g.setClip(clip__0_98);
        float alpha__0_99 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_99 = g.getClip();
        AffineTransform defaultTransform__0_99 = g.getTransform();
        
//      _0_99 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(67.504, 528.135);
        ((GeneralPath)shape).lineTo(63.403, 535.068);
        ((GeneralPath)shape).lineTo(62.732, 535.068);
        ((GeneralPath)shape).lineTo(66.834, 528.135);
        ((GeneralPath)shape).lineTo(67.504, 528.135);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_99;
        g.setTransform(defaultTransform__0_99);
        g.setClip(clip__0_99);
        float alpha__0_100 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_100 = g.getClip();
        AffineTransform defaultTransform__0_100 = g.getTransform();
        
//      _0_100 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(69.478, 530.818);
        ((GeneralPath)shape).lineTo(70.92799, 530.818);
        ((GeneralPath)shape).curveTo(71.272995, 530.818, 71.50299, 530.757, 71.616, 530.632);
        ((GeneralPath)shape).curveTo(71.729, 530.50903, 71.784996, 530.26105, 71.784996, 529.888);
        ((GeneralPath)shape).curveTo(71.784996, 529.509, 71.73599, 529.263, 71.63899, 529.152);
        ((GeneralPath)shape).curveTo(71.54099, 529.04297, 71.328995, 528.98596, 70.996994, 528.98596);
        ((GeneralPath)shape).lineTo(69.477, 528.98596);
        ((GeneralPath)shape).lineTo(69.477, 530.818);
        ((GeneralPath)shape).moveTo(68.468, 533.467);
        ((GeneralPath)shape).lineTo(68.468, 528.135);
        ((GeneralPath)shape).lineTo(71.092, 528.135);
        ((GeneralPath)shape).curveTo(71.743004, 528.135, 72.194, 528.248, 72.441, 528.47504);
        ((GeneralPath)shape).curveTo(72.687004, 528.702, 72.812004, 529.11206, 72.812004, 529.705);
        ((GeneralPath)shape).curveTo(72.812004, 530.244, 72.75101, 530.611, 72.62701, 530.81);
        ((GeneralPath)shape).curveTo(72.504005, 531.007, 72.25101, 531.144, 71.869, 531.22);
        ((GeneralPath)shape).lineTo(71.869, 531.25494);
        ((GeneralPath)shape).curveTo(72.458, 531.2899, 72.754005, 531.6359, 72.754005, 532.2899);
        ((GeneralPath)shape).lineTo(72.754005, 533.46594);
        ((GeneralPath)shape).lineTo(71.744, 533.46594);
        ((GeneralPath)shape).lineTo(71.744, 532.4929);
        ((GeneralPath)shape).curveTo(71.744, 531.9439, 71.475006, 531.66895, 70.93301, 531.66895);
        ((GeneralPath)shape).lineTo(69.479004, 531.66895);
        ((GeneralPath)shape).lineTo(69.479004, 533.46594);
        ((GeneralPath)shape).lineTo(68.468, 533.46594);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_100;
        g.setTransform(defaultTransform__0_100);
        g.setClip(clip__0_100);
        float alpha__0_101 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_101 = g.getClip();
        AffineTransform defaultTransform__0_101 = g.getTransform();
        
//      _0_101 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(76.901, 529.732);
        ((GeneralPath)shape).lineTo(76.901, 533.466);
        ((GeneralPath)shape).lineTo(76.008, 533.466);
        ((GeneralPath)shape).lineTo(76.059006, 532.825);
        ((GeneralPath)shape).lineTo(76.04301, 532.821);
        ((GeneralPath)shape).curveTo(75.87001, 533.27795, 75.47001, 533.509, 74.84201, 533.509);
        ((GeneralPath)shape).curveTo(73.99801, 533.509, 73.57501, 533.087, 73.57501, 532.23895);
        ((GeneralPath)shape).lineTo(73.57501, 529.73096);
        ((GeneralPath)shape).lineTo(74.46801, 529.73096);
        ((GeneralPath)shape).lineTo(74.46801, 532.024);
        ((GeneralPath)shape).curveTo(74.46801, 532.33997, 74.51101, 532.549, 74.60101, 532.651);
        ((GeneralPath)shape).curveTo(74.68801, 532.751, 74.87202, 532.801, 75.15002, 532.801);
        ((GeneralPath)shape).curveTo(75.722015, 532.801, 76.00802, 532.45703, 76.00802, 531.77);
        ((GeneralPath)shape).lineTo(76.00802, 529.731);
        ((GeneralPath)shape).lineTo(76.901, 529.731);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_101;
        g.setTransform(defaultTransform__0_101);
        g.setClip(clip__0_101);
        float alpha__0_102 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_102 = g.getClip();
        AffineTransform defaultTransform__0_102 = g.getTransform();
        
//      _0_102 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(77.807, 529.732);
        ((GeneralPath)shape).lineTo(78.692, 529.732);
        ((GeneralPath)shape).lineTo(78.657, 530.361);
        ((GeneralPath)shape).lineTo(78.676994, 530.36505);
        ((GeneralPath)shape).curveTo(78.84999, 529.9161, 79.24, 529.689, 79.84599, 529.689);
        ((GeneralPath)shape).curveTo(80.72699, 529.689, 81.16699, 530.099, 81.16699, 530.92303);
        ((GeneralPath)shape).lineTo(81.16699, 533.46606);
        ((GeneralPath)shape).lineTo(80.273994, 533.46606);
        ((GeneralPath)shape).lineTo(80.273994, 531.0751);
        ((GeneralPath)shape).lineTo(80.254, 530.81305);
        ((GeneralPath)shape).curveTo(80.213, 530.5361, 79.994995, 530.395, 79.599, 530.395);
        ((GeneralPath)shape).curveTo(78.999, 530.395, 78.699, 530.68, 78.699, 531.25);
        ((GeneralPath)shape).lineTo(78.699, 533.465);
        ((GeneralPath)shape).lineTo(77.806, 533.465);
        ((GeneralPath)shape).lineTo(77.806, 529.732);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_102;
        g.setTransform(defaultTransform__0_102);
        g.setClip(clip__0_102);
        float alpha__0_103 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_103 = g.getClip();
        AffineTransform defaultTransform__0_103 = g.getTransform();
        
//      _0_103 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(85.948, 528.135);
        ((GeneralPath)shape).lineTo(81.847, 535.068);
        ((GeneralPath)shape).lineTo(81.177, 535.068);
        ((GeneralPath)shape).lineTo(85.278, 528.135);
        ((GeneralPath)shape).lineTo(85.948, 528.135);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_103;
        g.setTransform(defaultTransform__0_103);
        g.setClip(clip__0_103);
        float alpha__0_104 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_104 = g.getClip();
        AffineTransform defaultTransform__0_104 = g.getTransform();
        
//      _0_104 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(90.101, 528.135);
        ((GeneralPath)shape).lineTo(90.101, 531.815);
        ((GeneralPath)shape).curveTo(90.101, 532.499, 89.988, 532.954, 89.762, 533.178);
        ((GeneralPath)shape).curveTo(89.536, 533.401, 89.076004, 533.514, 88.382, 533.514);
        ((GeneralPath)shape).curveTo(87.645004, 533.514, 87.165, 533.407, 86.94501, 533.19);
        ((GeneralPath)shape).curveTo(86.727005, 532.975, 86.616005, 532.502, 86.616005, 531.772);
        ((GeneralPath)shape).lineTo(86.62701, 531.37695);
        ((GeneralPath)shape).lineTo(87.590004, 531.37695);
        ((GeneralPath)shape).curveTo(87.606, 531.574, 87.61301, 531.72296, 87.61301, 531.82196);
        ((GeneralPath)shape).curveTo(87.61301, 531.91595, 87.617004, 532.02893, 87.62401, 532.162);
        ((GeneralPath)shape).curveTo(87.64001, 532.484, 87.89501, 532.646, 88.392006, 532.646);
        ((GeneralPath)shape).curveTo(88.688, 532.646, 88.87701, 532.589, 88.963005, 532.472);
        ((GeneralPath)shape).curveTo(89.047005, 532.357, 89.090004, 532.095, 89.090004, 531.68896);
        ((GeneralPath)shape).lineTo(89.090004, 528.134);
        ((GeneralPath)shape).lineTo(90.101, 528.134);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_104;
        g.setTransform(defaultTransform__0_104);
        g.setClip(clip__0_104);
        float alpha__0_105 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_105 = g.getClip();
        AffineTransform defaultTransform__0_105 = g.getTransform();
        
//      _0_105 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(94.322, 529.732);
        ((GeneralPath)shape).lineTo(94.322, 533.466);
        ((GeneralPath)shape).lineTo(93.429, 533.466);
        ((GeneralPath)shape).lineTo(93.48, 532.825);
        ((GeneralPath)shape).lineTo(93.464005, 532.821);
        ((GeneralPath)shape).curveTo(93.29101, 533.27795, 92.89101, 533.509, 92.26301, 533.509);
        ((GeneralPath)shape).curveTo(91.41901, 533.509, 90.99601, 533.087, 90.99601, 532.23895);
        ((GeneralPath)shape).lineTo(90.99601, 529.73096);
        ((GeneralPath)shape).lineTo(91.88901, 529.73096);
        ((GeneralPath)shape).lineTo(91.88901, 532.024);
        ((GeneralPath)shape).curveTo(91.88901, 532.33997, 91.93101, 532.549, 92.02101, 532.651);
        ((GeneralPath)shape).curveTo(92.10901, 532.751, 92.292015, 532.801, 92.571014, 532.801);
        ((GeneralPath)shape).curveTo(93.14201, 532.801, 93.42802, 532.45703, 93.42802, 531.77);
        ((GeneralPath)shape).lineTo(93.42802, 529.731);
        ((GeneralPath)shape).lineTo(94.322, 529.731);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_105;
        g.setTransform(defaultTransform__0_105);
        g.setClip(clip__0_105);
        float alpha__0_106 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_106 = g.getClip();
        AffineTransform defaultTransform__0_106 = g.getTransform();
        
//      _0_106 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(95.229, 529.732);
        ((GeneralPath)shape).lineTo(96.121994, 529.732);
        ((GeneralPath)shape).lineTo(96.09899, 530.30597);
        ((GeneralPath)shape).lineTo(96.11899, 530.31);
        ((GeneralPath)shape).curveTo(96.29999, 529.896, 96.679985, 529.689, 97.25699, 529.689);
        ((GeneralPath)shape).curveTo(97.929985, 529.689, 98.303986, 529.919, 98.37999, 530.38);
        ((GeneralPath)shape).lineTo(98.39599, 530.38);
        ((GeneralPath)shape).curveTo(98.568985, 529.919, 98.95399, 529.689, 99.54599, 529.689);
        ((GeneralPath)shape).curveTo(100.40699, 529.689, 100.83999, 530.12305, 100.83999, 530.994);
        ((GeneralPath)shape).lineTo(100.83999, 533.46704);
        ((GeneralPath)shape).lineTo(99.94699, 533.46704);
        ((GeneralPath)shape).lineTo(99.94699, 531.19006);
        ((GeneralPath)shape).curveTo(99.94699, 530.6631, 99.73099, 530.39703, 99.29599, 530.39703);
        ((GeneralPath)shape).curveTo(98.75399, 530.39703, 98.48099, 530.692, 98.48099, 531.28406);
        ((GeneralPath)shape).lineTo(98.48099, 533.4681);
        ((GeneralPath)shape).lineTo(97.58799, 533.4681);
        ((GeneralPath)shape).lineTo(97.58799, 531.1551);
        ((GeneralPath)shape).curveTo(97.58799, 530.84607, 97.54699, 530.6411, 97.46499, 530.54407);
        ((GeneralPath)shape).curveTo(97.38299, 530.44604, 97.20999, 530.3981, 96.94499, 530.3981);
        ((GeneralPath)shape).curveTo(96.396996, 530.3981, 96.121994, 530.6991, 96.121994, 531.3041);
        ((GeneralPath)shape).lineTo(96.121994, 533.4681);
        ((GeneralPath)shape).lineTo(95.229, 533.4681);
        ((GeneralPath)shape).lineTo(95.229, 529.732);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_106;
        g.setTransform(defaultTransform__0_106);
        g.setClip(clip__0_106);
        float alpha__0_107 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_107 = g.getClip();
        AffineTransform defaultTransform__0_107 = g.getTransform();
        
//      _0_107 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(103.51, 530.393);
        ((GeneralPath)shape).curveTo(103.171005, 530.393, 102.948006, 530.469, 102.843, 530.623);
        ((GeneralPath)shape).curveTo(102.739006, 530.77496, 102.687004, 531.102, 102.687004, 531.6);
        ((GeneralPath)shape).curveTo(102.687004, 532.084, 102.742004, 532.40497, 102.854004, 532.565);
        ((GeneralPath)shape).curveTo(102.965004, 532.723, 103.193, 532.803, 103.537, 532.803);
        ((GeneralPath)shape).curveTo(103.884, 532.803, 104.11, 532.729, 104.215004, 532.576);
        ((GeneralPath)shape).curveTo(104.319, 532.42596, 104.371, 532.101, 104.371, 531.60297);
        ((GeneralPath)shape).curveTo(104.371, 531.09296, 104.318, 530.76294, 104.213005, 530.615);
        ((GeneralPath)shape).curveTo(104.108, 530.467, 103.875, 530.393, 103.51, 530.393);
        ((GeneralPath)shape).moveTo(101.751, 529.732);
        ((GeneralPath)shape).lineTo(102.659, 529.732);
        ((GeneralPath)shape).lineTo(102.62399, 530.287);
        ((GeneralPath)shape).lineTo(102.64299, 530.291);
        ((GeneralPath)shape).curveTo(102.85799, 529.88904, 103.25899, 529.68604, 103.84799, 529.68604);
        ((GeneralPath)shape).curveTo(104.38999, 529.68604, 104.76499, 529.82104, 104.968994, 530.09);
        ((GeneralPath)shape).curveTo(105.172, 530.36005, 105.274994, 530.85004, 105.274994, 531.565);
        ((GeneralPath)shape).curveTo(105.274994, 532.309, 105.173996, 532.81903, 104.97099, 533.096);
        ((GeneralPath)shape).curveTo(104.76799, 533.37103, 104.39399, 533.51, 103.843994, 533.51);
        ((GeneralPath)shape).curveTo(103.259995, 533.51, 102.87699, 533.315, 102.69799, 532.924);
        ((GeneralPath)shape).lineTo(102.68199, 532.924);
        ((GeneralPath)shape).lineTo(102.68199, 535.06903);
        ((GeneralPath)shape).lineTo(101.74999, 535.06903);
        ((GeneralPath)shape).lineTo(101.74999, 529.732);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_107;
        g.setTransform(defaultTransform__0_107);
        g.setClip(clip__0_107);
        float alpha__0_108 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_108 = g.getClip();
        AffineTransform defaultTransform__0_108 = g.getTransform();
        
//      _0_108 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(125.352, 558.266);
        ((GeneralPath)shape).lineTo(120.601, 563.266);
        ((GeneralPath)shape).lineTo(41.352, 563.266);
        ((GeneralPath)shape).lineTo(35.852, 558.266);
        ((GeneralPath)shape).lineTo(40.852, 553.265);
        ((GeneralPath)shape).lineTo(120.102, 553.265);
        ((GeneralPath)shape).lineTo(125.352, 558.266);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_108;
        g.setTransform(defaultTransform__0_108);
        g.setClip(clip__0_108);
        float alpha__0_109 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_109 = g.getClip();
        AffineTransform defaultTransform__0_109 = g.getTransform();
        
//      _0_109 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        stroke = new BasicStroke(1.0f,0,0,10.0f,null,0.0f);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(125.352, 558.266);
        ((GeneralPath)shape).lineTo(120.603, 563.265);
        ((GeneralPath)shape).lineTo(41.352, 563.265);
        ((GeneralPath)shape).lineTo(35.852, 558.266);
        ((GeneralPath)shape).lineTo(40.852, 553.265);
        ((GeneralPath)shape).lineTo(120.102, 553.265);
        ((GeneralPath)shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_109;
        g.setTransform(defaultTransform__0_109);
        g.setClip(clip__0_109);
        float alpha__0_110 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_110 = g.getClip();
        AffineTransform defaultTransform__0_110 = g.getTransform();
        
//      _0_110 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(58.739, 558.585);
        ((GeneralPath)shape).lineTo(58.739, 556.456);
        ((GeneralPath)shape).lineTo(60.251, 556.456);
        ((GeneralPath)shape).curveTo(61.118, 556.456, 61.239, 556.70197, 61.239, 557.569);
        ((GeneralPath)shape).curveTo(61.239, 558.428, 61.079, 558.58496, 60.251, 558.58496);
        ((GeneralPath)shape).lineTo(58.739, 558.58496);
        ((GeneralPath)shape).moveTo(57.614, 561.585);
        ((GeneralPath)shape).lineTo(58.739, 561.585);
        ((GeneralPath)shape).lineTo(58.739, 559.585);
        ((GeneralPath)shape).lineTo(60.251, 559.585);
        ((GeneralPath)shape).curveTo(61.801, 559.585, 62.364, 559.284, 62.364, 557.56);
        ((GeneralPath)shape).curveTo(62.364, 555.851, 61.841, 555.46, 60.246998, 555.46);
        ((GeneralPath)shape).lineTo(57.614, 555.46);
        ((GeneralPath)shape).lineTo(57.614, 561.585);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_110;
        g.setTransform(defaultTransform__0_110);
        g.setClip(clip__0_110);
        float alpha__0_111 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_111 = g.getClip();
        AffineTransform defaultTransform__0_111 = g.getTransform();
        
//      _0_111 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new Rectangle2D.Double(63.10200119018555, 555.4600219726562, 1.125, 6.125);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_111;
        g.setTransform(defaultTransform__0_111);
        g.setClip(clip__0_111);
        float alpha__0_112 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_112 = g.getClip();
        AffineTransform defaultTransform__0_112 = g.getTransform();
        
//      _0_112 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(66.436, 555.46);
        ((GeneralPath)shape).lineTo(65.311, 555.46);
        ((GeneralPath)shape).lineTo(65.311, 561.585);
        ((GeneralPath)shape).lineTo(69.236, 561.585);
        ((GeneralPath)shape).lineTo(69.236, 560.589);
        ((GeneralPath)shape).lineTo(66.436, 560.589);
        ((GeneralPath)shape).lineTo(66.436, 555.46);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_112;
        g.setTransform(defaultTransform__0_112);
        g.setClip(clip__0_112);
        float alpha__0_113 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_113 = g.getClip();
        AffineTransform defaultTransform__0_113 = g.getTransform();
        
//      _0_113 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(72.17, 556.456);
        ((GeneralPath)shape).curveTo(73.178, 556.456, 73.581, 556.536, 73.581, 557.665);
        ((GeneralPath)shape).lineTo(73.581, 559.27997);
        ((GeneralPath)shape).curveTo(73.581, 560.43994, 73.225, 560.58496, 72.194, 560.58496);
        ((GeneralPath)shape).curveTo(71.06, 560.58496, 70.831, 560.47394, 70.831, 559.27997);
        ((GeneralPath)shape).lineTo(70.831, 557.665);
        ((GeneralPath)shape).curveTo(70.831, 556.712, 70.95, 556.456, 72.17, 556.456);
        ((GeneralPath)shape).moveTo(72.193, 555.46);
        ((GeneralPath)shape).curveTo(70.324, 555.46, 69.705, 555.79004, 69.705, 557.663);
        ((GeneralPath)shape).lineTo(69.705, 559.286);
        ((GeneralPath)shape).curveTo(69.705, 561.263, 70.372, 561.585, 72.193, 561.585);
        ((GeneralPath)shape).curveTo(73.974, 561.585, 74.705, 561.216, 74.705, 559.286);
        ((GeneralPath)shape).lineTo(74.705, 557.663);
        ((GeneralPath)shape).curveTo(74.706, 555.726, 73.889, 555.46, 72.193, 555.46);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_113;
        g.setTransform(defaultTransform__0_113);
        g.setClip(clip__0_113);
        float alpha__0_114 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_114 = g.getClip();
        AffineTransform defaultTransform__0_114 = g.getTransform();
        
//      _0_114 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(77.925, 556.456);
        ((GeneralPath)shape).lineTo(79.685, 556.456);
        ((GeneralPath)shape).lineTo(79.685, 555.46);
        ((GeneralPath)shape).lineTo(75.041, 555.46);
        ((GeneralPath)shape).lineTo(75.041, 556.456);
        ((GeneralPath)shape).lineTo(76.8, 556.456);
        ((GeneralPath)shape).lineTo(76.8, 561.585);
        ((GeneralPath)shape).lineTo(77.925, 561.585);
        ((GeneralPath)shape).lineTo(77.925, 556.456);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_114;
        g.setTransform(defaultTransform__0_114);
        g.setClip(clip__0_114);
        float alpha__0_115 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_115 = g.getClip();
        AffineTransform defaultTransform__0_115 = g.getTransform();
        
//      _0_115 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(84.188, 560.589);
        ((GeneralPath)shape).lineTo(84.188, 556.456);
        ((GeneralPath)shape).lineTo(85.94701, 556.456);
        ((GeneralPath)shape).curveTo(86.850006, 556.456, 87.188, 556.71, 87.188, 557.735);
        ((GeneralPath)shape).lineTo(87.188, 559.186);
        ((GeneralPath)shape).curveTo(87.188, 559.875, 86.941, 560.58795, 86.145004, 560.58795);
        ((GeneralPath)shape).lineTo(84.188, 560.58795);
        ((GeneralPath)shape).moveTo(83.063, 561.585);
        ((GeneralPath)shape).lineTo(86.133, 561.585);
        ((GeneralPath)shape).curveTo(87.959, 561.585, 88.313, 560.458, 88.313, 559.185);
        ((GeneralPath)shape).lineTo(88.313, 557.74);
        ((GeneralPath)shape).curveTo(88.313, 556.074, 87.605, 555.461, 85.941, 555.461);
        ((GeneralPath)shape).lineTo(83.063, 555.461);
        ((GeneralPath)shape).lineTo(83.063, 561.585);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_115;
        g.setTransform(defaultTransform__0_115);
        g.setClip(clip__0_115);
        float alpha__0_116 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_116 = g.getClip();
        AffineTransform defaultTransform__0_116 = g.getTransform();
        
//      _0_116 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(92.518, 559.585);
        ((GeneralPath)shape).lineTo(90.436, 559.585);
        ((GeneralPath)shape).lineTo(91.465, 556.351);
        ((GeneralPath)shape).lineTo(91.480995, 556.351);
        ((GeneralPath)shape).lineTo(92.518, 559.585);
        ((GeneralPath)shape).moveTo(92.776, 560.46);
        ((GeneralPath)shape).lineTo(93.171, 561.585);
        ((GeneralPath)shape).lineTo(94.345, 561.585);
        ((GeneralPath)shape).lineTo(92.302, 555.46);
        ((GeneralPath)shape).lineTo(90.597, 555.46);
        ((GeneralPath)shape).lineTo(88.595, 561.585);
        ((GeneralPath)shape).lineTo(89.794, 561.585);
        ((GeneralPath)shape).lineTo(90.171, 560.46);
        ((GeneralPath)shape).lineTo(92.776, 560.46);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_116;
        g.setTransform(defaultTransform__0_116);
        g.setClip(clip__0_116);
        float alpha__0_117 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_117 = g.getClip();
        AffineTransform defaultTransform__0_117 = g.getTransform();
        
//      _0_117 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(96.645, 556.456);
        ((GeneralPath)shape).lineTo(98.405, 556.456);
        ((GeneralPath)shape).lineTo(98.405, 555.46);
        ((GeneralPath)shape).lineTo(93.76, 555.46);
        ((GeneralPath)shape).lineTo(93.76, 556.456);
        ((GeneralPath)shape).lineTo(95.52, 556.456);
        ((GeneralPath)shape).lineTo(95.52, 561.585);
        ((GeneralPath)shape).lineTo(96.645, 561.585);
        ((GeneralPath)shape).lineTo(96.645, 556.456);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_117;
        g.setTransform(defaultTransform__0_117);
        g.setClip(clip__0_117);
        float alpha__0_118 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_118 = g.getClip();
        AffineTransform defaultTransform__0_118 = g.getTransform();
        
//      _0_118 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(101.91, 559.585);
        ((GeneralPath)shape).lineTo(99.828, 559.585);
        ((GeneralPath)shape).lineTo(100.857, 556.351);
        ((GeneralPath)shape).lineTo(100.873, 556.351);
        ((GeneralPath)shape).lineTo(101.91, 559.585);
        ((GeneralPath)shape).moveTo(102.167, 560.46);
        ((GeneralPath)shape).lineTo(102.562, 561.585);
        ((GeneralPath)shape).lineTo(103.73499, 561.585);
        ((GeneralPath)shape).lineTo(101.69299, 555.46);
        ((GeneralPath)shape).lineTo(99.98699, 555.46);
        ((GeneralPath)shape).lineTo(97.98499, 561.585);
        ((GeneralPath)shape).lineTo(99.18399, 561.585);
        ((GeneralPath)shape).lineTo(99.56099, 560.46);
        ((GeneralPath)shape).lineTo(102.167, 560.46);
        g.setPaint(paint);
        g.fill(shape);
        paint4(g, clip_, defaultTransform_, alpha__0, clip__0,
				defaultTransform__0, alpha__0_118, clip__0_118,
				defaultTransform__0_118);    }
	private static void paint4(Graphics2D g, Shape clip_,
			AffineTransform defaultTransform_, float alpha__0, Shape clip__0,
			AffineTransform defaultTransform__0, float alpha__0_118,
			Shape clip__0_118, AffineTransform defaultTransform__0_118) {
		Shape shape;
		Paint paint;
		Stroke stroke;
		float origAlpha;
		origAlpha = alpha__0_118;
        g.setTransform(defaultTransform__0_118);
        g.setClip(clip__0_118);
        float alpha__0_119 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_119 = g.getClip();
        AffineTransform defaultTransform__0_119 = g.getTransform();
        
//      _0_119 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(42.955, 577.333);
        ((GeneralPath)shape).lineTo(45.298, 577.333);
        ((GeneralPath)shape).lineTo(45.314, 578.392);
        ((GeneralPath)shape).curveTo(45.314, 579.16205, 45.17, 579.66003, 44.878998, 579.882);
        ((GeneralPath)shape).curveTo(44.589996, 580.10504, 43.942997, 580.216, 42.94, 580.216);
        ((GeneralPath)shape).curveTo(42.02, 580.216, 41.412, 580.068, 41.111, 579.771);
        ((GeneralPath)shape).curveTo(40.813, 579.474, 40.663, 578.869, 40.663, 577.955);
        ((GeneralPath)shape).curveTo(40.663, 576.789, 40.721996, 576.05304, 40.842, 575.744);
        ((GeneralPath)shape).curveTo(40.989998, 575.369, 41.215, 575.117, 41.517, 574.986);
        ((GeneralPath)shape).curveTo(41.816998, 574.857, 42.329998, 574.791, 43.052998, 574.791);
        ((GeneralPath)shape).curveTo(43.997997, 574.791, 44.608997, 574.891, 44.885998, 575.094);
        ((GeneralPath)shape).curveTo(45.161, 575.295, 45.298996, 575.742, 45.298996, 576.436);
        ((GeneralPath)shape).lineTo(44.277996, 576.436);
        ((GeneralPath)shape).curveTo(44.259995, 576.08795, 44.183994, 575.87195, 44.049995, 575.78595);
        ((GeneralPath)shape).curveTo(43.916996, 575.70197, 43.579994, 575.65894, 43.041996, 575.65894);
        ((GeneralPath)shape).curveTo(42.456997, 575.65894, 42.085995, 575.73096, 41.928997, 575.8779);
        ((GeneralPath)shape).curveTo(41.773, 576.02295, 41.692997, 576.3659, 41.692997, 576.9049);
        ((GeneralPath)shape).lineTo(41.688995, 577.4399);
        ((GeneralPath)shape).lineTo(41.696995, 578.1239);
        ((GeneralPath)shape).curveTo(41.696995, 578.6509, 41.774994, 578.9889, 41.930996, 579.1339);
        ((GeneralPath)shape).curveTo(42.086998, 579.27893, 42.447994, 579.3509, 43.014996, 579.3509);
        ((GeneralPath)shape).curveTo(43.564995, 579.3509, 43.914997, 579.2899, 44.065994, 579.1669);
        ((GeneralPath)shape).curveTo(44.213993, 579.0459, 44.289993, 578.7569, 44.289993, 578.29987);
        ((GeneralPath)shape).lineTo(44.29299, 578.0809);
        ((GeneralPath)shape).lineTo(42.95599, 578.0809);
        ((GeneralPath)shape).lineTo(42.95599, 577.333);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_119;
        g.setTransform(defaultTransform__0_119);
        g.setClip(clip__0_119);
        float alpha__0_120 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_120 = g.getClip();
        AffineTransform defaultTransform__0_120 = g.getTransform();
        
//      _0_120 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(49.302, 576.435);
        ((GeneralPath)shape).lineTo(49.302, 580.169);
        ((GeneralPath)shape).lineTo(48.408997, 580.169);
        ((GeneralPath)shape).lineTo(48.459995, 579.528);
        ((GeneralPath)shape).lineTo(48.443996, 579.524);
        ((GeneralPath)shape).curveTo(48.270996, 579.98096, 47.870995, 580.212, 47.242996, 580.212);
        ((GeneralPath)shape).curveTo(46.398994, 580.212, 45.975998, 579.79, 45.975998, 578.94196);
        ((GeneralPath)shape).lineTo(45.975998, 576.43396);
        ((GeneralPath)shape).lineTo(46.869, 576.43396);
        ((GeneralPath)shape).lineTo(46.869, 578.727);
        ((GeneralPath)shape).curveTo(46.869, 579.04297, 46.911, 579.252, 47.001, 579.354);
        ((GeneralPath)shape).curveTo(47.089, 579.454, 47.272, 579.504, 47.551, 579.504);
        ((GeneralPath)shape).curveTo(48.121998, 579.504, 48.407997, 579.16003, 48.407997, 578.473);
        ((GeneralPath)shape).lineTo(48.407997, 576.434);
        ((GeneralPath)shape).lineTo(49.302, 576.434);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_120;
        g.setTransform(defaultTransform__0_120);
        g.setClip(clip__0_120);
        float alpha__0_121 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_121 = g.getClip();
        AffineTransform defaultTransform__0_121 = g.getTransform();
        
//      _0_121 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(50.208, 576.435);
        ((GeneralPath)shape).lineTo(51.093, 576.435);
        ((GeneralPath)shape).lineTo(51.058, 577.064);
        ((GeneralPath)shape).lineTo(51.078, 577.06805);
        ((GeneralPath)shape).curveTo(51.251, 576.6191, 51.641, 576.392, 52.246998, 576.392);
        ((GeneralPath)shape).curveTo(53.128, 576.392, 53.568996, 576.802, 53.568996, 577.62604);
        ((GeneralPath)shape).lineTo(53.568996, 580.16907);
        ((GeneralPath)shape).lineTo(52.675995, 580.16907);
        ((GeneralPath)shape).lineTo(52.675995, 577.7781);
        ((GeneralPath)shape).lineTo(52.655994, 577.51605);
        ((GeneralPath)shape).curveTo(52.614994, 577.2391, 52.396996, 577.098, 52.000996, 577.098);
        ((GeneralPath)shape).curveTo(51.400997, 577.098, 51.100994, 577.383, 51.100994, 577.953);
        ((GeneralPath)shape).lineTo(51.100994, 580.168);
        ((GeneralPath)shape).lineTo(50.207993, 580.168);
        ((GeneralPath)shape).lineTo(50.207993, 576.435);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_121;
        g.setTransform(defaultTransform__0_121);
        g.setClip(clip__0_121);
        float alpha__0_122 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_122 = g.getClip();
        AffineTransform defaultTransform__0_122 = g.getTransform();
        
//      _0_122 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(54.44, 576.435);
        ((GeneralPath)shape).lineTo(55.324997, 576.435);
        ((GeneralPath)shape).lineTo(55.289997, 577.064);
        ((GeneralPath)shape).lineTo(55.309998, 577.06805);
        ((GeneralPath)shape).curveTo(55.482998, 576.6191, 55.872997, 576.392, 56.478996, 576.392);
        ((GeneralPath)shape).curveTo(57.359997, 576.392, 57.800995, 576.802, 57.800995, 577.62604);
        ((GeneralPath)shape).lineTo(57.800995, 580.16907);
        ((GeneralPath)shape).lineTo(56.907993, 580.16907);
        ((GeneralPath)shape).lineTo(56.907993, 577.7781);
        ((GeneralPath)shape).lineTo(56.887993, 577.51605);
        ((GeneralPath)shape).curveTo(56.846992, 577.2391, 56.628994, 577.098, 56.232994, 577.098);
        ((GeneralPath)shape).curveTo(55.632996, 577.098, 55.332993, 577.383, 55.332993, 577.953);
        ((GeneralPath)shape).lineTo(55.332993, 580.168);
        ((GeneralPath)shape).lineTo(54.44, 580.168);
        ((GeneralPath)shape).lineTo(54.44, 576.435);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_122;
        g.setTransform(defaultTransform__0_122);
        g.setClip(clip__0_122);
        float alpha__0_123 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_123 = g.getClip();
        AffineTransform defaultTransform__0_123 = g.getTransform();
        
//      _0_123 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(61.081, 577.915);
        ((GeneralPath)shape).lineTo(61.077, 577.76697);
        ((GeneralPath)shape).curveTo(61.077, 577.47, 61.026, 577.279, 60.923, 577.18896);
        ((GeneralPath)shape).curveTo(60.821, 577.10095, 60.598, 577.05597, 60.255, 577.05597);
        ((GeneralPath)shape).curveTo(59.923, 577.05597, 59.707, 577.10895, 59.606003, 577.21594);
        ((GeneralPath)shape).curveTo(59.507004, 577.3209, 59.456, 577.55597, 59.456, 577.9149);
        ((GeneralPath)shape).lineTo(61.081, 577.9149);
        ((GeneralPath)shape).moveTo(61.073, 578.974);
        ((GeneralPath)shape).lineTo(61.97, 578.974);
        ((GeneralPath)shape).lineTo(61.97, 579.119);
        ((GeneralPath)shape).curveTo(61.97, 579.848, 61.424, 580.213, 60.333, 580.213);
        ((GeneralPath)shape).curveTo(59.592, 580.213, 59.108, 580.088, 58.878, 579.834);
        ((GeneralPath)shape).curveTo(58.649998, 579.582, 58.535, 579.047, 58.535, 578.229);
        ((GeneralPath)shape).curveTo(58.535, 577.502, 58.654, 577.014, 58.893, 576.764);
        ((GeneralPath)shape).curveTo(59.131, 576.514, 59.599003, 576.389, 60.293003, 576.389);
        ((GeneralPath)shape).curveTo(60.958004, 576.389, 61.404003, 576.50995, 61.630005, 576.75397);
        ((GeneralPath)shape).curveTo(61.856007, 576.996, 61.969006, 577.475, 61.969006, 578.18994);
        ((GeneralPath)shape).lineTo(61.969006, 578.46295);
        ((GeneralPath)shape).lineTo(59.447006, 578.46295);
        ((GeneralPath)shape).curveTo(59.443005, 578.5449, 59.439007, 578.6, 59.439007, 578.62695);
        ((GeneralPath)shape).curveTo(59.439007, 578.99396, 59.496006, 579.238, 59.609005, 579.36096);
        ((GeneralPath)shape).curveTo(59.722004, 579.48193, 59.946007, 579.545, 60.285004, 579.545);
        ((GeneralPath)shape).curveTo(60.613003, 579.545, 60.826004, 579.51, 60.925003, 579.438);
        ((GeneralPath)shape).curveTo(61.022, 579.366, 61.073, 579.212, 61.073, 578.974);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_123;
        g.setTransform(defaultTransform__0_123);
        g.setClip(clip__0_123);
        float alpha__0_124 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_124 = g.getClip();
        AffineTransform defaultTransform__0_124 = g.getTransform();
        
//      _0_124 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(62.734, 576.435);
        ((GeneralPath)shape).lineTo(63.627003, 576.435);
        ((GeneralPath)shape).lineTo(63.572002, 576.958);
        ((GeneralPath)shape).lineTo(63.592003, 576.96204);
        ((GeneralPath)shape).curveTo(63.804005, 576.57104, 64.14, 576.37604, 64.598, 576.37604);
        ((GeneralPath)shape).curveTo(65.253, 576.37604, 65.58, 576.79004, 65.58, 577.61804);
        ((GeneralPath)shape).lineTo(65.58, 577.88007);
        ((GeneralPath)shape).lineTo(64.738, 577.88007);
        ((GeneralPath)shape).curveTo(64.748, 577.7781, 64.754, 577.71204, 64.754, 577.6811);
        ((GeneralPath)shape).curveTo(64.754, 577.2831, 64.6, 577.08307, 64.29, 577.08307);
        ((GeneralPath)shape).curveTo(63.850002, 577.08307, 63.627003, 577.37805, 63.627003, 577.9701);
        ((GeneralPath)shape).lineTo(63.627003, 580.16907);
        ((GeneralPath)shape).lineTo(62.734, 580.16907);
        ((GeneralPath)shape).lineTo(62.734, 576.435);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_124;
        g.setTransform(defaultTransform__0_124);
        g.setClip(clip__0_124);
        float alpha__0_125 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_125 = g.getClip();
        AffineTransform defaultTransform__0_125 = g.getTransform();
        
//      _0_125 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(69.141, 576.435);
        ((GeneralPath)shape).lineTo(68.163, 580.404);
        ((GeneralPath)shape).curveTo(68.026, 580.967, 67.854004, 581.343, 67.65, 581.539);
        ((GeneralPath)shape).curveTo(67.447, 581.732, 67.12, 581.83, 66.67, 581.83);
        ((GeneralPath)shape).curveTo(66.569, 581.83, 66.463, 581.826, 66.354, 581.814);
        ((GeneralPath)shape).lineTo(66.354, 581.15405);
        ((GeneralPath)shape).curveTo(66.432, 581.1581, 66.496994, 581.16205, 66.548996, 581.16205);
        ((GeneralPath)shape).curveTo(66.926994, 581.16205, 67.169, 580.83203, 67.270996, 580.17004);
        ((GeneralPath)shape).lineTo(66.813995, 580.17004);
        ((GeneralPath)shape).lineTo(65.593994, 576.43604);
        ((GeneralPath)shape).lineTo(66.55299, 576.43604);
        ((GeneralPath)shape).lineTo(67.020996, 578.018);
        ((GeneralPath)shape).lineTo(67.255, 578.81104);
        ((GeneralPath)shape).curveTo(67.267, 578.86206, 67.304, 578.99506, 67.364, 579.20905);
        ((GeneralPath)shape).lineTo(67.477, 579.60406);
        ((GeneralPath)shape).lineTo(67.496994, 579.60406);
        ((GeneralPath)shape).lineTo(67.578995, 579.20905);
        ((GeneralPath)shape).curveTo(67.620995, 579.00604, 67.646996, 578.87305, 67.660995, 578.81104);
        ((GeneralPath)shape).lineTo(67.843994, 578.018);
        ((GeneralPath)shape).lineTo(68.202995, 576.43604);
        ((GeneralPath)shape).lineTo(69.141, 576.43604);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_125;
        g.setTransform(defaultTransform__0_125);
        g.setClip(clip__0_125);
        float alpha__0_126 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_126 = g.getClip();
        AffineTransform defaultTransform__0_126 = g.getTransform();
        
//      _0_126 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(75.519, 576.392);
        ((GeneralPath)shape).lineTo(74.532, 576.392);
        ((GeneralPath)shape).curveTo(74.528, 576.343, 74.523994, 576.306, 74.523994, 576.283);
        ((GeneralPath)shape).curveTo(74.50099, 575.984, 74.434, 575.797, 74.325, 575.719);
        ((GeneralPath)shape).curveTo(74.215996, 575.643, 73.958, 575.604, 73.55299, 575.604);
        ((GeneralPath)shape).curveTo(73.075, 575.604, 72.76299, 575.64703, 72.61499, 575.737);
        ((GeneralPath)shape).curveTo(72.46899, 575.825, 72.39499, 576.01, 72.39499, 576.296);
        ((GeneralPath)shape).curveTo(72.39499, 576.632, 72.45399, 576.833, 72.57399, 576.901);
        ((GeneralPath)shape).curveTo(72.69299, 576.967, 73.08899, 577.02, 73.75899, 577.057);
        ((GeneralPath)shape).curveTo(74.55099, 577.10004, 75.06299, 577.213, 75.29699, 577.39703);
        ((GeneralPath)shape).curveTo(75.52899, 577.57904, 75.64599, 577.958, 75.64599, 578.53406);
        ((GeneralPath)shape).curveTo(75.64599, 579.241, 75.50899, 579.7001, 75.235985, 579.90704);
        ((GeneralPath)shape).curveTo(74.96398, 580.114, 74.36298, 580.218, 73.430984, 580.218);
        ((GeneralPath)shape).curveTo(72.594986, 580.218, 72.03899, 580.116, 71.763985, 579.913);
        ((GeneralPath)shape).curveTo(71.49098, 579.71, 71.35298, 579.30005, 71.35298, 578.679);
        ((GeneralPath)shape).lineTo(71.34898, 578.484);
        ((GeneralPath)shape).lineTo(72.330986, 578.484);
        ((GeneralPath)shape).lineTo(72.334984, 578.597);
        ((GeneralPath)shape).curveTo(72.334984, 578.96796, 72.39899, 579.19696, 72.52998, 579.281);
        ((GeneralPath)shape).curveTo(72.65898, 579.363, 73.012985, 579.406, 73.59399, 579.406);
        ((GeneralPath)shape).curveTo(74.04599, 579.406, 74.334984, 579.359, 74.459984, 579.261);
        ((GeneralPath)shape).curveTo(74.584984, 579.165, 74.64698, 578.943, 74.64698, 578.59296);
        ((GeneralPath)shape).curveTo(74.64698, 578.33496, 74.59998, 578.165, 74.50398, 578.079);
        ((GeneralPath)shape).curveTo(74.41098, 577.995, 74.205986, 577.944, 73.889984, 577.925);
        ((GeneralPath)shape).lineTo(73.331985, 577.89);
        ((GeneralPath)shape).curveTo(72.48998, 577.841, 71.95199, 577.724, 71.71799, 577.538);
        ((GeneralPath)shape).curveTo(71.483986, 577.354, 71.36699, 576.95605, 71.36699, 576.34705);
        ((GeneralPath)shape).curveTo(71.36699, 575.7261, 71.50799, 575.31006, 71.789986, 575.103);
        ((GeneralPath)shape).curveTo(72.070984, 574.89606, 72.63399, 574.79205, 73.47999, 574.79205);
        ((GeneralPath)shape).curveTo(74.27899, 574.79205, 74.818985, 574.88605, 75.09799, 575.077);
        ((GeneralPath)shape).curveTo(75.37499, 575.26605, 75.51499, 575.638, 75.51499, 576.19);
        ((GeneralPath)shape).lineTo(75.51499, 576.392);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_126;
        g.setTransform(defaultTransform__0_126);
        g.setClip(clip__0_126);
        float alpha__0_127 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_127 = g.getClip();
        AffineTransform defaultTransform__0_127 = g.getTransform();
        
//      _0_127 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(77.218, 574.837);
        ((GeneralPath)shape).lineTo(77.218, 577.888);
        ((GeneralPath)shape).lineTo(77.448, 577.888);
        ((GeneralPath)shape).lineTo(78.453, 576.435);
        ((GeneralPath)shape).lineTo(79.49, 576.435);
        ((GeneralPath)shape).lineTo(78.192, 578.192);
        ((GeneralPath)shape).lineTo(79.755, 580.169);
        ((GeneralPath)shape).lineTo(78.648, 580.169);
        ((GeneralPath)shape).lineTo(77.436, 578.513);
        ((GeneralPath)shape).lineTo(77.218, 578.513);
        ((GeneralPath)shape).lineTo(77.218, 580.169);
        ((GeneralPath)shape).lineTo(76.325, 580.169);
        ((GeneralPath)shape).lineTo(76.325, 574.837);
        ((GeneralPath)shape).lineTo(77.218, 574.837);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_127;
        g.setTransform(defaultTransform__0_127);
        g.setClip(clip__0_127);
        float alpha__0_128 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_128 = g.getClip();
        AffineTransform defaultTransform__0_128 = g.getTransform();
        
//      _0_128 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(81.05, 576.435);
        ((GeneralPath)shape).lineTo(81.05, 580.169);
        ((GeneralPath)shape).lineTo(80.157005, 580.169);
        ((GeneralPath)shape).lineTo(80.157005, 576.435);
        ((GeneralPath)shape).lineTo(81.05, 576.435);
        ((GeneralPath)shape).moveTo(81.05, 574.837);
        ((GeneralPath)shape).lineTo(81.05, 575.58295);
        ((GeneralPath)shape).lineTo(80.157005, 575.58295);
        ((GeneralPath)shape).lineTo(80.157005, 574.837);
        ((GeneralPath)shape).lineTo(81.05, 574.837);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_128;
        g.setTransform(defaultTransform__0_128);
        g.setClip(clip__0_128);
        float alpha__0_129 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_129 = g.getClip();
        AffineTransform defaultTransform__0_129 = g.getTransform();
        
//      _0_129 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new Rectangle2D.Double(81.93000030517578, 574.8369750976562, 0.8930000066757202, 5.331999778747559);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_129;
        g.setTransform(defaultTransform__0_129);
        g.setClip(clip__0_129);
        float alpha__0_130 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_130 = g.getClip();
        AffineTransform defaultTransform__0_130 = g.getTransform();
        
//      _0_130 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new Rectangle2D.Double(83.7030029296875, 574.8369750976562, 0.8930000066757202, 5.331999778747559);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_130;
        g.setTransform(defaultTransform__0_130);
        g.setClip(clip__0_130);
        float alpha__0_131 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_131 = g.getClip();
        AffineTransform defaultTransform__0_131 = g.getTransform();
        
//      _0_131 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(86.329, 576.435);
        ((GeneralPath)shape).lineTo(86.329, 577.396);
        ((GeneralPath)shape).lineTo(85.436005, 577.396);
        ((GeneralPath)shape).lineTo(85.436005, 576.435);
        ((GeneralPath)shape).lineTo(86.329, 576.435);
        ((GeneralPath)shape).moveTo(86.329, 579.208);
        ((GeneralPath)shape).lineTo(86.329, 580.169);
        ((GeneralPath)shape).lineTo(85.436005, 580.169);
        ((GeneralPath)shape).lineTo(85.436005, 579.208);
        ((GeneralPath)shape).lineTo(86.329, 579.208);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_131;
        g.setTransform(defaultTransform__0_131);
        g.setClip(clip__0_131);
        float alpha__0_132 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_132 = g.getClip();
        AffineTransform defaultTransform__0_132 = g.getTransform();
        
//      _0_132 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        stroke = new BasicStroke(1.0f,0,0,10.0f,null,0.0f);
        shape = new Line2D.Float(88.930000f,581.585999f,107.353996f,581.585999f);
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_132;
        g.setTransform(defaultTransform__0_132);
        g.setClip(clip__0_132);
        float alpha__0_133 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_133 = g.getClip();
        AffineTransform defaultTransform__0_133 = g.getTransform();
        
//      _0_133 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        stroke = new BasicStroke(0.599f,1,1,10.0f,null,0.0f);
        shape = new Line2D.Float(217.897995f,561.544983f,217.897995f,579.859009f);
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_133;
        g.setTransform(defaultTransform__0_133);
        g.setClip(clip__0_133);
        float alpha__0_134 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_134 = g.getClip();
        AffineTransform defaultTransform__0_134 = g.getTransform();
        
//      _0_134 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(211.271, 564.043);
        ((GeneralPath)shape).lineTo(211.271, 569.375);
        ((GeneralPath)shape).lineTo(210.261, 569.375);
        ((GeneralPath)shape).lineTo(210.261, 564.926);
        ((GeneralPath)shape).lineTo(208.877, 566.301);
        ((GeneralPath)shape).lineTo(208.277, 565.648);
        ((GeneralPath)shape).lineTo(209.996, 564.043);
        ((GeneralPath)shape).lineTo(211.271, 564.043);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_134;
        g.setTransform(defaultTransform__0_134);
        g.setClip(clip__0_134);
        float alpha__0_135 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_135 = g.getClip();
        AffineTransform defaultTransform__0_135 = g.getTransform();
        
//      _0_135 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(209.895, 575.938);
        ((GeneralPath)shape).lineTo(209.895, 575.154);
        ((GeneralPath)shape).lineTo(210.06601, 575.158);
        ((GeneralPath)shape).curveTo(210.63101, 575.158, 210.977, 575.124, 211.10101, 575.056);
        ((GeneralPath)shape).curveTo(211.22401, 574.99005, 211.28702, 574.79803, 211.28702, 574.48206);
        ((GeneralPath)shape).curveTo(211.28702, 574.13007, 211.23203, 573.9131, 211.12102, 573.83203);
        ((GeneralPath)shape).curveTo(211.01202, 573.749, 210.71902, 573.70905, 210.24602, 573.70905);
        ((GeneralPath)shape).curveTo(209.81702, 573.70905, 209.54802, 573.754, 209.43501, 573.843);
        ((GeneralPath)shape).curveTo(209.32402, 573.934, 209.26701, 574.153, 209.26701, 574.502);
        ((GeneralPath)shape).lineTo(209.26701, 574.658);
        ((GeneralPath)shape).lineTo(208.292, 574.658);
        ((GeneralPath)shape).lineTo(208.292, 574.45905);
        ((GeneralPath)shape).curveTo(208.292, 573.8381, 208.423, 573.421, 208.686, 573.21);
        ((GeneralPath)shape).curveTo(208.947, 573.002, 209.46501, 572.89703, 210.241, 572.89703);
        ((GeneralPath)shape).curveTo(211.021, 572.89703, 211.555, 572.994, 211.847, 573.19403);
        ((GeneralPath)shape).curveTo(212.138, 573.39, 212.284, 573.757, 212.284, 574.291);
        ((GeneralPath)shape).curveTo(212.284, 575.028, 211.98999, 575.442, 211.399, 575.534);
        ((GeneralPath)shape).lineTo(211.399, 575.561);
        ((GeneralPath)shape).curveTo(212.041, 575.631, 212.362, 576.01794, 212.362, 576.725);
        ((GeneralPath)shape).curveTo(212.362, 577.35095, 212.222, 577.77295, 211.937, 577.99097);
        ((GeneralPath)shape).curveTo(211.65399, 578.20496, 211.103, 578.31396, 210.28, 578.31396);
        ((GeneralPath)shape).curveTo(209.475, 578.31396, 208.927, 578.20795, 208.636, 577.99097);
        ((GeneralPath)shape).curveTo(208.348, 577.77496, 208.202, 577.36597, 208.202, 576.764);
        ((GeneralPath)shape).lineTo(208.205, 576.467);
        ((GeneralPath)shape).lineTo(209.191, 576.467);
        ((GeneralPath)shape).curveTo(209.19899, 576.584, 209.20299, 576.65497, 209.20299, 576.678);
        ((GeneralPath)shape).curveTo(209.20299, 577.045, 209.26399, 577.27295, 209.38899, 577.36597);
        ((GeneralPath)shape).curveTo(209.512, 577.45496, 209.82199, 577.503, 210.31898, 577.503);
        ((GeneralPath)shape).curveTo(210.77298, 577.503, 211.06099, 577.453, 211.18199, 577.355);
        ((GeneralPath)shape).curveTo(211.303, 577.25696, 211.36299, 577.019, 211.36299, 576.644);
        ((GeneralPath)shape).curveTo(211.36299, 576.359, 211.30899, 576.169, 211.19699, 576.078);
        ((GeneralPath)shape).curveTo(211.088, 575.987, 210.85799, 575.94, 210.50899, 575.94);
        ((GeneralPath)shape).lineTo(209.895, 575.94);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_135;
        g.setTransform(defaultTransform__0_135);
        g.setClip(clip__0_135);
        float alpha__0_136 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_136 = g.getClip();
        AffineTransform defaultTransform__0_136 = g.getTransform();
        
//      _0_136 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        stroke = new BasicStroke(0.599f,1,1,10.0f,null,0.0f);
        shape = new Line2D.Float(232.559998f,561.544983f,232.559998f,579.859009f);
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_136;
        g.setTransform(defaultTransform__0_136);
        g.setClip(clip__0_136);
        float alpha__0_137 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_137 = g.getClip();
        AffineTransform defaultTransform__0_137 = g.getTransform();
        
//      _0_137 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(226.964, 568.574);
        ((GeneralPath)shape).lineTo(226.964, 569.375);
        ((GeneralPath)shape).lineTo(222.942, 569.375);
        ((GeneralPath)shape).lineTo(222.942, 568.289);
        ((GeneralPath)shape).curveTo(222.942, 567.726, 223.041, 567.353, 223.244, 567.168);
        ((GeneralPath)shape).curveTo(223.445, 566.984, 223.925, 566.82404, 224.681, 566.68805);
        ((GeneralPath)shape).curveTo(225.307, 566.577, 225.677, 566.47504, 225.794, 566.38104);
        ((GeneralPath)shape).curveTo(225.90901, 566.28906, 225.96701, 566.051, 225.96701, 565.66406);
        ((GeneralPath)shape).curveTo(225.96701, 565.29504, 225.91101, 565.06006, 225.794, 564.96106);
        ((GeneralPath)shape).curveTo(225.679, 564.86304, 225.404, 564.81305, 224.97, 564.81305);
        ((GeneralPath)shape).curveTo(224.524, 564.81305, 224.241, 564.86206, 224.12401, 564.9631);
        ((GeneralPath)shape).curveTo(224.007, 565.06305, 223.949, 565.30707, 223.949, 565.69507);
        ((GeneralPath)shape).lineTo(223.949, 565.92206);
        ((GeneralPath)shape).lineTo(222.959, 565.92206);
        ((GeneralPath)shape).lineTo(222.959, 565.69904);
        ((GeneralPath)shape).curveTo(222.959, 565.031, 223.096, 564.58, 223.373, 564.34705);
        ((GeneralPath)shape).curveTo(223.647, 564.11707, 224.182, 563.999, 224.975, 563.999);
        ((GeneralPath)shape).curveTo(225.74901, 563.999, 226.27501, 564.116, 226.552, 564.351);
        ((GeneralPath)shape).curveTo(226.829, 564.585, 226.967, 565.031, 226.967, 565.687);
        ((GeneralPath)shape).curveTo(226.967, 566.306, 226.862, 566.72003, 226.652, 566.931);
        ((GeneralPath)shape).curveTo(226.44199, 567.14, 225.958, 567.31604, 225.198, 567.45605);
        ((GeneralPath)shape).curveTo(224.57199, 567.56903, 224.208, 567.66705, 224.107, 567.7471);
        ((GeneralPath)shape).curveTo(224.00499, 567.8251, 223.955, 568.0541, 223.955, 568.4291);
        ((GeneralPath)shape).lineTo(223.955, 568.5741);
        ((GeneralPath)shape).lineTo(226.964, 568.5741);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_137;
        g.setTransform(defaultTransform__0_137);
        g.setClip(clip__0_137);
        float alpha__0_138 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_138 = g.getClip();
        AffineTransform defaultTransform__0_138 = g.getTransform();
        
//      _0_138 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(226.96, 572.938);
        ((GeneralPath)shape).lineTo(226.96, 573.74);
        ((GeneralPath)shape).lineTo(224.16501, 573.74);
        ((GeneralPath)shape).lineTo(224.15302, 575.024);
        ((GeneralPath)shape).lineTo(224.17302, 575.024);
        ((GeneralPath)shape).curveTo(224.37003, 574.74896, 224.77502, 574.61, 225.38902, 574.61);
        ((GeneralPath)shape).curveTo(226.08702, 574.61, 226.56203, 574.74, 226.81403, 574.998);
        ((GeneralPath)shape).curveTo(227.06403, 575.256, 227.19003, 575.74, 227.19003, 576.454);
        ((GeneralPath)shape).curveTo(227.19003, 577.185, 227.05003, 577.67896, 226.76903, 577.933);
        ((GeneralPath)shape).curveTo(226.48802, 578.186, 225.94702, 578.313, 225.14003, 578.313);
        ((GeneralPath)shape).curveTo(224.34503, 578.313, 223.81104, 578.207, 223.53403, 577.99);
        ((GeneralPath)shape).curveTo(223.25903, 577.774, 223.12003, 577.355, 223.12003, 576.732);
        ((GeneralPath)shape).lineTo(224.10602, 576.732);
        ((GeneralPath)shape).curveTo(224.12402, 577.076, 224.19002, 577.288, 224.30302, 577.374);
        ((GeneralPath)shape).curveTo(224.41603, 577.45905, 224.69302, 577.502, 225.13602, 577.502);
        ((GeneralPath)shape).curveTo(225.62701, 577.502, 225.92601, 577.44403, 226.03302, 577.33203);
        ((GeneralPath)shape).curveTo(226.13802, 577.218, 226.19302, 576.89905, 226.19302, 576.377);
        ((GeneralPath)shape).curveTo(226.19302, 575.968, 226.13402, 575.70703, 226.01303, 575.593);
        ((GeneralPath)shape).curveTo(225.89403, 575.481, 225.61703, 575.424, 225.18303, 575.424);
        ((GeneralPath)shape).curveTo(224.55103, 575.424, 224.20403, 575.546, 224.14203, 575.795);
        ((GeneralPath)shape).lineTo(223.23003, 575.795);
        ((GeneralPath)shape).lineTo(223.23003, 572.93896);
        ((GeneralPath)shape).lineTo(226.96, 572.93896);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_138;
        g.setTransform(defaultTransform__0_138);
        g.setClip(clip__0_138);
        float alpha__0_139 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_139 = g.getClip();
        AffineTransform defaultTransform__0_139 = g.getTransform();
        
//      _0_139 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        stroke = new BasicStroke(0.599f,1,1,10.0f,null,0.0f);
        shape = new Line2D.Float(247.220001f,561.544983f,247.220001f,579.859009f);
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_139;
        g.setTransform(defaultTransform__0_139);
        g.setClip(clip__0_139);
        float alpha__0_140 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_140 = g.getClip();
        AffineTransform defaultTransform__0_140 = g.getTransform();
        
//      _0_140 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(239.286, 567.043);
        ((GeneralPath)shape).lineTo(239.286, 566.25806);
        ((GeneralPath)shape).lineTo(239.457, 566.2621);
        ((GeneralPath)shape).curveTo(240.022, 566.2621, 240.368, 566.22906, 240.492, 566.1601);
        ((GeneralPath)shape).curveTo(240.615, 566.0941, 240.677, 565.9021, 240.677, 565.5861);
        ((GeneralPath)shape).curveTo(240.677, 565.23413, 240.623, 565.0181, 240.511, 564.9361);
        ((GeneralPath)shape).curveTo(240.40201, 564.8541, 240.11, 564.8131, 239.636, 564.8131);
        ((GeneralPath)shape).curveTo(239.207, 564.8131, 238.939, 564.8581, 238.825, 564.9481);
        ((GeneralPath)shape).curveTo(238.714, 565.03815, 238.65799, 565.25714, 238.65799, 565.60614);
        ((GeneralPath)shape).lineTo(238.65799, 565.76215);
        ((GeneralPath)shape).lineTo(237.68298, 565.76215);
        ((GeneralPath)shape).lineTo(237.68298, 565.5632);
        ((GeneralPath)shape).curveTo(237.68298, 564.9422, 237.81299, 564.5262, 238.07698, 564.3152);
        ((GeneralPath)shape).curveTo(238.33798, 564.1062, 238.85698, 564.00116, 239.63298, 564.00116);
        ((GeneralPath)shape).curveTo(240.41199, 564.00116, 240.94698, 564.0992, 241.23898, 564.29816);
        ((GeneralPath)shape).curveTo(241.52998, 564.4952, 241.67598, 564.86115, 241.67598, 565.3962);
        ((GeneralPath)shape).curveTo(241.67598, 566.1322, 241.38197, 566.5462, 240.79099, 566.6382);
        ((GeneralPath)shape).lineTo(240.79099, 566.66516);
        ((GeneralPath)shape).curveTo(241.43199, 566.73517, 241.75398, 567.12213, 241.75398, 567.82916);
        ((GeneralPath)shape).curveTo(241.75398, 568.4562, 241.61398, 568.8782, 241.32898, 569.09515);
        ((GeneralPath)shape).curveTo(241.04698, 569.3102, 240.49498, 569.4191, 239.67198, 569.4191);
        ((GeneralPath)shape).curveTo(238.86699, 569.4191, 238.31898, 569.31213, 238.02898, 569.09515);
        ((GeneralPath)shape).curveTo(237.73997, 568.8801, 237.59398, 568.47015, 237.59398, 567.86816);
        ((GeneralPath)shape).lineTo(237.59798, 567.57117);
        ((GeneralPath)shape).lineTo(238.58397, 567.57117);
        ((GeneralPath)shape).curveTo(238.59196, 567.6882, 238.59596, 567.75916, 238.59596, 567.78217);
        ((GeneralPath)shape).curveTo(238.59596, 568.1492, 238.65697, 568.3782, 238.78096, 568.47015);
        ((GeneralPath)shape).curveTo(238.90396, 568.5602, 239.21396, 568.6072, 239.71095, 568.6072);
        ((GeneralPath)shape).curveTo(240.16495, 568.6072, 240.45395, 568.55817, 240.57495, 568.45917);
        ((GeneralPath)shape).curveTo(240.69595, 568.36115, 240.75595, 568.12317, 240.75595, 567.74817);
        ((GeneralPath)shape).curveTo(240.75595, 567.4632, 240.70096, 567.2732, 240.58995, 567.1822);
        ((GeneralPath)shape).curveTo(240.48096, 567.09216, 240.25095, 567.04517, 239.90195, 567.04517);
        ((GeneralPath)shape).lineTo(239.286, 567.04517);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_140;
        g.setTransform(defaultTransform__0_140);
        g.setClip(clip__0_140);
        float alpha__0_141 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_141 = g.getClip();
        AffineTransform defaultTransform__0_141 = g.getTransform();
        
//      _0_141 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(241.767, 572.938);
        ((GeneralPath)shape).lineTo(241.767, 573.912);
        ((GeneralPath)shape).lineTo(239.623, 578.271);
        ((GeneralPath)shape).lineTo(238.496, 578.271);
        ((GeneralPath)shape).lineTo(240.757, 573.79);
        ((GeneralPath)shape).lineTo(237.814, 573.79);
        ((GeneralPath)shape).lineTo(237.814, 572.938);
        ((GeneralPath)shape).lineTo(241.767, 572.938);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_141;
        g.setTransform(defaultTransform__0_141);
        g.setClip(clip__0_141);
        float alpha__0_142 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_142 = g.getClip();
        AffineTransform defaultTransform__0_142 = g.getTransform();
        
//      _0_142 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        stroke = new BasicStroke(0.599f,1,1,10.0f,null,0.0f);
        shape = new Line2D.Float(261.776001f,561.544983f,261.776001f,579.859009f);
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_142;
        g.setTransform(defaultTransform__0_142);
        g.setClip(clip__0_142);
        float alpha__0_143 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_143 = g.getClip();
        AffineTransform defaultTransform__0_143 = g.getTransform();
        
//      _0_143 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(255.184, 567.449);
        ((GeneralPath)shape).lineTo(255.184, 564.808);
        ((GeneralPath)shape).lineTo(255.153, 564.808);
        ((GeneralPath)shape).lineTo(253.138, 567.449);
        ((GeneralPath)shape).lineTo(255.184, 567.449);
        ((GeneralPath)shape).moveTo(256.193, 564.043);
        ((GeneralPath)shape).lineTo(256.193, 567.44904);
        ((GeneralPath)shape).lineTo(256.805, 567.44904);
        ((GeneralPath)shape).lineTo(256.805, 568.23004);
        ((GeneralPath)shape).lineTo(256.193, 568.23004);
        ((GeneralPath)shape).lineTo(256.193, 569.37506);
        ((GeneralPath)shape).lineTo(255.183, 569.37506);
        ((GeneralPath)shape).lineTo(255.183, 568.23004);
        ((GeneralPath)shape).lineTo(252.31, 568.23004);
        ((GeneralPath)shape).lineTo(252.31, 567.093);
        ((GeneralPath)shape).lineTo(254.634, 564.042);
        ((GeneralPath)shape).lineTo(256.193, 564.042);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_143;
        g.setTransform(defaultTransform__0_143);
        g.setClip(clip__0_143);
        float alpha__0_144 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_144 = g.getClip();
        AffineTransform defaultTransform__0_144 = g.getTransform();
        
//      _0_144 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(253.125, 572.938);
        ((GeneralPath)shape).lineTo(253.125, 578.271);
        ((GeneralPath)shape).lineTo(252.115, 578.271);
        ((GeneralPath)shape).lineTo(252.115, 573.822);
        ((GeneralPath)shape).lineTo(250.731, 575.197);
        ((GeneralPath)shape).lineTo(250.131, 574.545);
        ((GeneralPath)shape).lineTo(251.85, 572.938);
        ((GeneralPath)shape).lineTo(253.125, 572.938);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_144;
        g.setTransform(defaultTransform__0_144);
        g.setClip(clip__0_144);
        float alpha__0_145 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_145 = g.getClip();
        AffineTransform defaultTransform__0_145 = g.getTransform();
        
//      _0_145 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(257.036, 573.764);
        ((GeneralPath)shape).curveTo(256.57202, 573.764, 256.278, 573.829, 256.153, 573.96295);
        ((GeneralPath)shape).curveTo(256.03003, 574.095, 255.96701, 574.41, 255.96701, 574.90796);
        ((GeneralPath)shape).lineTo(255.95901, 575.60693);
        ((GeneralPath)shape).lineTo(255.96701, 576.25494);
        ((GeneralPath)shape).curveTo(255.96701, 576.79395, 256.03, 577.1259, 256.155, 577.25494);
        ((GeneralPath)shape).curveTo(256.28, 577.3809, 256.603, 577.4459, 257.125, 577.4459);
        ((GeneralPath)shape).curveTo(257.667, 577.4459, 257.995, 577.3559, 258.104, 577.1759);
        ((GeneralPath)shape).curveTo(258.213, 576.9959, 258.268, 576.4609, 258.268, 575.5709);
        ((GeneralPath)shape).curveTo(258.268, 574.6729, 258.217, 574.14594, 258.112, 573.99194);
        ((GeneralPath)shape).curveTo(258.009, 573.842, 257.65, 573.764, 257.036, 573.764);
        ((GeneralPath)shape).moveTo(257.114, 572.896);
        ((GeneralPath)shape).curveTo(258.09003, 572.896, 258.69302, 573.038, 258.923, 573.327);
        ((GeneralPath)shape).curveTo(259.151, 573.61505, 259.266, 574.37305, 259.266, 575.603);
        ((GeneralPath)shape).curveTo(259.266, 576.833, 259.15298, 577.59204, 258.923, 577.88104);
        ((GeneralPath)shape).curveTo(258.695, 578.16907, 258.092, 578.31305, 257.114, 578.31305);
        ((GeneralPath)shape).curveTo(256.13702, 578.31305, 255.53502, 578.16907, 255.30501, 577.88007);
        ((GeneralPath)shape).curveTo(255.07701, 577.59106, 254.962, 576.83307, 254.962, 575.6031);
        ((GeneralPath)shape).curveTo(254.962, 574.3741, 255.07501, 573.6161, 255.30501, 573.3271);
        ((GeneralPath)shape).curveTo(255.533, 573.04, 256.136, 572.896, 257.114, 572.896);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_145;
        g.setTransform(defaultTransform__0_145);
        g.setClip(clip__0_145);
        float alpha__0_146 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_146 = g.getClip();
        AffineTransform defaultTransform__0_146 = g.getTransform();
        
//      _0_146 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        stroke = new BasicStroke(0.599f,1,1,10.0f,null,0.0f);
        shape = new Line2D.Float(276.437012f,561.544983f,276.437012f,579.859009f);
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_146;
        g.setTransform(defaultTransform__0_146);
        g.setClip(clip__0_146);
        float alpha__0_147 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_147 = g.getClip();
        AffineTransform defaultTransform__0_147 = g.getTransform();
        
//      _0_147 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(271.198, 564.043);
        ((GeneralPath)shape).lineTo(271.198, 564.84406);
        ((GeneralPath)shape).lineTo(268.40298, 564.84406);
        ((GeneralPath)shape).lineTo(268.391, 566.129);
        ((GeneralPath)shape).lineTo(268.41098, 566.129);
        ((GeneralPath)shape).curveTo(268.60797, 565.854, 269.01398, 565.715, 269.62698, 565.715);
        ((GeneralPath)shape).curveTo(270.32498, 565.715, 270.8, 565.84406, 271.05197, 566.10205);
        ((GeneralPath)shape).curveTo(271.30197, 566.36005, 271.42798, 566.84406, 271.42798, 567.559);
        ((GeneralPath)shape).curveTo(271.42798, 568.289, 271.28796, 568.784, 271.007, 569.038);
        ((GeneralPath)shape).curveTo(270.726, 569.29205, 270.184, 569.419, 269.378, 569.419);
        ((GeneralPath)shape).curveTo(268.58298, 569.419, 268.048, 569.312, 267.772, 569.09503);
        ((GeneralPath)shape).curveTo(267.497, 568.88, 267.359, 568.46, 267.359, 567.83704);
        ((GeneralPath)shape).lineTo(268.345, 567.83704);
        ((GeneralPath)shape).curveTo(268.363, 568.181, 268.42902, 568.39404, 268.542, 568.48004);
        ((GeneralPath)shape).curveTo(268.655, 568.564, 268.932, 568.60706, 269.374, 568.60706);
        ((GeneralPath)shape).curveTo(269.866, 568.60706, 270.164, 568.55005, 270.271, 568.4371);
        ((GeneralPath)shape).curveTo(270.376, 568.3241, 270.431, 568.00507, 270.431, 567.48206);
        ((GeneralPath)shape).curveTo(270.431, 567.07404, 270.372, 566.8121, 270.252, 566.69904);
        ((GeneralPath)shape).curveTo(270.13303, 566.58606, 269.85602, 566.52905, 269.42203, 566.52905);
        ((GeneralPath)shape).curveTo(268.79004, 566.52905, 268.44302, 566.65204, 268.38104, 566.9);
        ((GeneralPath)shape).lineTo(267.46805, 566.9);
        ((GeneralPath)shape).lineTo(267.46805, 564.04504);
        ((GeneralPath)shape).lineTo(271.198, 564.04504);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_147;
        g.setTransform(defaultTransform__0_147);
        g.setClip(clip__0_147);
        float alpha__0_148 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_148 = g.getClip();
        AffineTransform defaultTransform__0_148 = g.getTransform();
        
//      _0_148 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(267.896, 572.938);
        ((GeneralPath)shape).lineTo(267.896, 578.271);
        ((GeneralPath)shape).lineTo(266.885, 578.271);
        ((GeneralPath)shape).lineTo(266.885, 573.822);
        ((GeneralPath)shape).lineTo(265.501, 575.197);
        ((GeneralPath)shape).lineTo(264.901, 574.545);
        ((GeneralPath)shape).lineTo(266.62, 572.938);
        ((GeneralPath)shape).lineTo(267.896, 572.938);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_148;
        g.setTransform(defaultTransform__0_148);
        g.setClip(clip__0_148);
        float alpha__0_149 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_149 = g.getClip();
        AffineTransform defaultTransform__0_149 = g.getTransform();
        
//      _0_149 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(272.91, 572.938);
        ((GeneralPath)shape).lineTo(272.91, 578.271);
        ((GeneralPath)shape).lineTo(271.9, 578.271);
        ((GeneralPath)shape).lineTo(271.9, 573.822);
        ((GeneralPath)shape).lineTo(270.516, 575.197);
        ((GeneralPath)shape).lineTo(269.916, 574.545);
        ((GeneralPath)shape).lineTo(271.635, 572.938);
        ((GeneralPath)shape).lineTo(272.91, 572.938);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_149;
        g.setTransform(defaultTransform__0_149);
        g.setClip(clip__0_149);
        float alpha__0_150 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_150 = g.getClip();
        AffineTransform defaultTransform__0_150 = g.getTransform();
        
//      _0_150 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        stroke = new BasicStroke(0.604f,1,1,10.0f,null,0.0f);
        shape = new Line2D.Float(204.330994f,570.747986f,292.204987f,570.747986f);
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_150;
        g.setTransform(defaultTransform__0_150);
        g.setClip(clip__0_150);
        float alpha__0_151 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_151 = g.getClip();
        AffineTransform defaultTransform__0_151 = g.getTransform();
        
//      _0_151 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(284.531, 566.934);
        ((GeneralPath)shape).curveTo(284.055, 566.934, 283.751, 566.97504, 283.617, 567.06104);
        ((GeneralPath)shape).curveTo(283.484, 567.145, 283.41602, 567.33606, 283.41602, 567.637);
        ((GeneralPath)shape).curveTo(283.41602, 568.07104, 283.47403, 568.34204, 283.595, 568.45);
        ((GeneralPath)shape).curveTo(283.714, 568.555, 284.016, 568.61, 284.499, 568.61);
        ((GeneralPath)shape).curveTo(284.961, 568.61, 285.249, 568.563, 285.367, 568.467);
        ((GeneralPath)shape).curveTo(285.482, 568.373, 285.54, 568.133, 285.54, 567.75);
        ((GeneralPath)shape).curveTo(285.54, 567.389, 285.483, 567.162, 285.37, 567.07);
        ((GeneralPath)shape).curveTo(285.258, 566.98, 284.979, 566.934, 284.531, 566.934);
        ((GeneralPath)shape).moveTo(286.492, 565.539);
        ((GeneralPath)shape).lineTo(285.49, 565.539);
        ((GeneralPath)shape).lineTo(285.494, 565.418);
        ((GeneralPath)shape).curveTo(285.494, 565.168, 285.439, 565.00604, 285.326, 564.92804);
        ((GeneralPath)shape).curveTo(285.215, 564.85205, 284.979, 564.81305, 284.61697, 564.81305);
        ((GeneralPath)shape).curveTo(284.093, 564.81305, 283.76498, 564.86206, 283.63498, 564.9631);
        ((GeneralPath)shape).curveTo(283.507, 565.06305, 283.43997, 565.31506, 283.43997, 565.71906);
        ((GeneralPath)shape).lineTo(283.43997, 566.6371);
        ((GeneralPath)shape).lineTo(283.45996, 566.6371);
        ((GeneralPath)shape).curveTo(283.63397, 566.2931, 284.06796, 566.1211, 284.76596, 566.1211);
        ((GeneralPath)shape).curveTo(285.44797, 566.1211, 285.91595, 566.2281, 286.16495, 566.4471);
        ((GeneralPath)shape).curveTo(286.41495, 566.66406, 286.53995, 567.07007, 286.53995, 567.6681);
        ((GeneralPath)shape).curveTo(286.53995, 568.3571, 286.40695, 568.8221, 286.13895, 569.0631);
        ((GeneralPath)shape).curveTo(285.87094, 569.3011, 285.35095, 569.4221, 284.57895, 569.4221);
        ((GeneralPath)shape).curveTo(283.62796, 569.4221, 283.03195, 569.2811, 282.78595, 568.9981);
        ((GeneralPath)shape).curveTo(282.54196, 568.7171, 282.41995, 568.0271, 282.41995, 566.9301);
        ((GeneralPath)shape).curveTo(282.41995, 565.5761, 282.52896, 564.7501, 282.74896, 564.45013);
        ((GeneralPath)shape).curveTo(282.96796, 564.1511, 283.57797, 564.00116, 284.57596, 564.00116);
        ((GeneralPath)shape).curveTo(285.30295, 564.00116, 285.80396, 564.10114, 286.08096, 564.3022);
        ((GeneralPath)shape).curveTo(286.35495, 564.50116, 286.49396, 564.86816, 286.49396, 565.4002);
        ((GeneralPath)shape).lineTo(286.49396, 565.539);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_151;
        g.setTransform(defaultTransform__0_151);
        g.setClip(clip__0_151);
        float alpha__0_152 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_152 = g.getClip();
        AffineTransform defaultTransform__0_152 = g.getTransform();
        
//      _0_152 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(277.995, 577.171);
        ((GeneralPath)shape).lineTo(279.191, 577.171);
        ((GeneralPath)shape).curveTo(279.59302, 577.171, 279.85202, 577.07904, 279.97, 576.89404);
        ((GeneralPath)shape).curveTo(280.087, 576.70905, 280.146, 576.301, 280.146, 575.668);
        ((GeneralPath)shape).curveTo(280.146, 575.01605, 280.095, 574.60004, 279.987, 574.421);
        ((GeneralPath)shape).curveTo(279.882, 574.244, 279.634, 574.154, 279.242, 574.154);
        ((GeneralPath)shape).lineTo(277.99402, 574.154);
        ((GeneralPath)shape).lineTo(277.99402, 577.171);
        ((GeneralPath)shape).moveTo(277.156, 577.879);
        ((GeneralPath)shape).lineTo(277.156, 573.44604);
        ((GeneralPath)shape).lineTo(279.33002, 573.44604);
        ((GeneralPath)shape).curveTo(279.94803, 573.44604, 280.38, 573.58105, 280.62802, 573.85205);
        ((GeneralPath)shape).curveTo(280.87503, 574.1221, 280.99902, 574.59607, 280.99902, 575.275);
        ((GeneralPath)shape).curveTo(280.99902, 576.38, 280.90002, 577.091, 280.70102, 577.406);
        ((GeneralPath)shape).curveTo(280.50302, 577.721, 280.05402, 577.879, 279.35602, 577.879);
        ((GeneralPath)shape).lineTo(277.156, 577.879);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_152;
        g.setTransform(defaultTransform__0_152);
        g.setClip(clip__0_152);
        float alpha__0_153 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_153 = g.getClip();
        AffineTransform defaultTransform__0_153 = g.getTransform();
        
//      _0_153 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(283.589, 576.006);
        ((GeneralPath)shape).lineTo(283.586, 575.88196);
        ((GeneralPath)shape).curveTo(283.586, 575.636, 283.544, 575.4769, 283.458, 575.402);
        ((GeneralPath)shape).curveTo(283.374, 575.329, 283.187, 575.292, 282.902, 575.292);
        ((GeneralPath)shape).curveTo(282.627, 575.292, 282.446, 575.336, 282.362, 575.425);
        ((GeneralPath)shape).curveTo(282.279, 575.513, 282.237, 575.708, 282.237, 576.00696);
        ((GeneralPath)shape).lineTo(283.589, 576.00696);
        ((GeneralPath)shape).moveTo(283.582, 576.886);
        ((GeneralPath)shape).lineTo(284.328, 576.886);
        ((GeneralPath)shape).lineTo(284.328, 577.006);
        ((GeneralPath)shape).curveTo(284.328, 577.61096, 283.874, 577.915, 282.967, 577.915);
        ((GeneralPath)shape).curveTo(282.351, 577.915, 281.949, 577.811, 281.758, 577.6);
        ((GeneralPath)shape).curveTo(281.569, 577.391, 281.473, 576.946, 281.473, 576.266);
        ((GeneralPath)shape).curveTo(281.473, 575.662, 281.572, 575.256, 281.771, 575.048);
        ((GeneralPath)shape).curveTo(281.969, 574.83997, 282.357, 574.73596, 282.935, 574.73596);
        ((GeneralPath)shape).curveTo(283.487, 574.73596, 283.858, 574.837, 284.046, 575.04);
        ((GeneralPath)shape).curveTo(284.23398, 575.24097, 284.328, 575.639, 284.328, 576.232);
        ((GeneralPath)shape).lineTo(284.328, 576.46);
        ((GeneralPath)shape).lineTo(282.23102, 576.46);
        ((GeneralPath)shape).curveTo(282.22803, 576.528, 282.225, 576.57404, 282.225, 576.59705);
        ((GeneralPath)shape).curveTo(282.225, 576.90204, 282.272, 577.10504, 282.366, 577.20703);
        ((GeneralPath)shape).curveTo(282.46, 577.30804, 282.646, 577.359, 282.928, 577.359);
        ((GeneralPath)shape).curveTo(283.2, 577.359, 283.377, 577.33, 283.459, 577.27);
        ((GeneralPath)shape).curveTo(283.54, 577.212, 283.582, 577.083, 283.582, 576.886);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_153;
        g.setTransform(defaultTransform__0_153);
        g.setClip(clip__0_153);
        float alpha__0_154 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_154 = g.getClip();
        AffineTransform defaultTransform__0_154 = g.getTransform();
        
//      _0_154 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(286.211, 576.489);
        ((GeneralPath)shape).curveTo(285.793, 576.489, 285.582, 576.63403, 285.582, 576.925);
        ((GeneralPath)shape).curveTo(285.582, 577.126, 285.624, 577.25696, 285.712, 577.321);
        ((GeneralPath)shape).curveTo(285.797, 577.384, 285.979, 577.41595, 286.256, 577.41595);
        ((GeneralPath)shape).curveTo(286.70602, 577.41595, 286.933, 577.26294, 286.933, 576.95795);
        ((GeneralPath)shape).curveTo(286.934, 576.646, 286.694, 576.489, 286.211, 576.489);
        ((GeneralPath)shape).moveTo(285.716, 575.674);
        ((GeneralPath)shape).lineTo(284.958, 575.674);
        ((GeneralPath)shape).curveTo(284.958, 575.30304, 285.043, 575.054, 285.217, 574.92804);
        ((GeneralPath)shape).curveTo(285.389, 574.80304, 285.733, 574.74005, 286.24402, 574.74005);
        ((GeneralPath)shape).curveTo(286.80203, 574.74005, 287.178, 574.81604, 287.37503, 574.97003);
        ((GeneralPath)shape).curveTo(287.57104, 575.122, 287.66904, 575.416, 287.66904, 575.85004);
        ((GeneralPath)shape).lineTo(287.66904, 577.879);
        ((GeneralPath)shape).lineTo(286.92703, 577.879);
        ((GeneralPath)shape).lineTo(286.96304, 577.453);
        ((GeneralPath)shape).lineTo(286.94305, 577.45);
        ((GeneralPath)shape).curveTo(286.80005, 577.75903, 286.47107, 577.91504, 285.95505, 577.91504);
        ((GeneralPath)shape).curveTo(285.20605, 577.91504, 284.83005, 577.59705, 284.83005, 576.95703);
        ((GeneralPath)shape).curveTo(284.83005, 576.312, 285.21204, 575.989, 285.98004, 575.989);
        ((GeneralPath)shape).curveTo(286.49203, 575.989, 286.80304, 576.106, 286.91306, 576.343);
        ((GeneralPath)shape).lineTo(286.92606, 576.343);
        ((GeneralPath)shape).lineTo(286.92606, 575.839);
        ((GeneralPath)shape).curveTo(286.92606, 575.597, 286.88406, 575.437, 286.80005, 575.357);
        ((GeneralPath)shape).curveTo(286.71603, 575.27997, 286.54404, 575.239, 286.28104, 575.239);
        ((GeneralPath)shape).curveTo(285.905, 575.239, 285.716, 575.384, 285.716, 575.674);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_154;
        g.setTransform(defaultTransform__0_154);
        g.setClip(clip__0_154);
        float alpha__0_155 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_155 = g.getClip();
        AffineTransform defaultTransform__0_155 = g.getTransform();
        
//      _0_155 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(289.711, 575.327);
        ((GeneralPath)shape).curveTo(289.436, 575.327, 289.256, 575.38605, 289.173, 575.504);
        ((GeneralPath)shape).curveTo(289.092, 575.62103, 289.05002, 575.87604, 289.05002, 576.26605);
        ((GeneralPath)shape).curveTo(289.05002, 576.715, 289.091, 577.00604, 289.173, 577.13605);
        ((GeneralPath)shape).curveTo(289.254, 577.26605, 289.439, 577.33, 289.724, 577.33);
        ((GeneralPath)shape).curveTo(290.022, 577.33, 290.212, 577.267, 290.296, 577.13904);
        ((GeneralPath)shape).curveTo(290.379, 577.01306, 290.421, 576.71704, 290.421, 576.25604);
        ((GeneralPath)shape).curveTo(290.421, 575.87604, 290.37598, 575.62604, 290.283, 575.50507);
        ((GeneralPath)shape).curveTo(290.193, 575.387, 290.001, 575.327, 289.711, 575.327);
        ((GeneralPath)shape).moveTo(291.167, 573.446);
        ((GeneralPath)shape).lineTo(291.167, 577.87897);
        ((GeneralPath)shape).lineTo(290.444, 577.87897);
        ((GeneralPath)shape).lineTo(290.47, 577.37897);
        ((GeneralPath)shape).lineTo(290.457, 577.376);
        ((GeneralPath)shape).curveTo(290.312, 577.73596, 289.997, 577.91797, 289.511, 577.91797);
        ((GeneralPath)shape).curveTo(289.02197, 577.91797, 288.69598, 577.80597, 288.537, 577.582);
        ((GeneralPath)shape).curveTo(288.378, 577.358, 288.29898, 576.90295, 288.29898, 576.217);
        ((GeneralPath)shape).curveTo(288.29898, 575.66296, 288.383, 575.279, 288.555, 575.063);
        ((GeneralPath)shape).curveTo(288.725, 574.84796, 289.02798, 574.74, 289.466, 574.74);
        ((GeneralPath)shape).curveTo(289.971, 574.74, 290.286, 574.899, 290.41202, 575.22);
        ((GeneralPath)shape).lineTo(290.42502, 575.217);
        ((GeneralPath)shape).lineTo(290.42502, 573.446);
        ((GeneralPath)shape).lineTo(291.167, 573.446);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_155;
        g.setTransform(defaultTransform__0_155);
        g.setClip(clip__0_155);
        float alpha__0_156 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_156 = g.getClip();
        AffineTransform defaultTransform__0_156 = g.getTransform();
        
//      _0_156 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(151.47, 574.797);
        ((GeneralPath)shape).lineTo(152.357, 574.797);
        ((GeneralPath)shape).lineTo(152.357, 574.957);
        ((GeneralPath)shape).curveTo(152.357, 575.60596, 152.239, 576.02496, 152.002, 576.217);
        ((GeneralPath)shape).curveTo(151.767, 576.406, 151.243, 576.50195, 150.433, 576.50195);
        ((GeneralPath)shape).curveTo(149.51599, 576.50195, 148.95, 576.3519, 148.73799, 576.05096);
        ((GeneralPath)shape).curveTo(148.527, 575.74994, 148.42099, 574.94696, 148.42099, 573.63794);
        ((GeneralPath)shape).curveTo(148.42099, 572.86896, 148.56499, 572.36194, 148.85098, 572.1179);
        ((GeneralPath)shape).curveTo(149.13799, 571.8759, 149.73598, 571.75494, 150.64899, 571.75494);
        ((GeneralPath)shape).curveTo(151.31198, 571.75494, 151.75499, 571.85394, 151.97899, 572.05396);
        ((GeneralPath)shape).curveTo(152.20099, 572.25195, 152.31299, 572.6489, 152.31299, 573.24194);
        ((GeneralPath)shape).lineTo(152.316, 573.34796);
        ((GeneralPath)shape).lineTo(151.429, 573.34796);
        ((GeneralPath)shape).lineTo(151.429, 573.22797);
        ((GeneralPath)shape).curveTo(151.429, 572.92395, 151.373, 572.727, 151.257, 572.64197);
        ((GeneralPath)shape).curveTo(151.143, 572.55597, 150.88, 572.514, 150.47, 572.514);
        ((GeneralPath)shape).curveTo(149.923, 572.514, 149.594, 572.57996, 149.483, 572.71497);
        ((GeneralPath)shape).curveTo(149.37401, 572.84894, 149.317, 573.248, 149.317, 573.91095);
        ((GeneralPath)shape).curveTo(149.317, 574.80396, 149.366, 575.33295, 149.465, 575.49695);
        ((GeneralPath)shape).curveTo(149.564, 575.66095, 149.883, 575.7429, 150.425, 575.7429);
        ((GeneralPath)shape).curveTo(150.863, 575.7429, 151.14801, 575.6989, 151.279, 575.6059);
        ((GeneralPath)shape).curveTo(151.408, 575.5149, 151.475, 575.3159, 151.475, 575.0039);
        ((GeneralPath)shape).lineTo(151.47, 574.797);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_156;
        g.setTransform(defaultTransform__0_156);
        g.setClip(clip__0_156);
        float alpha__0_157 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_157 = g.getClip();
        AffineTransform defaultTransform__0_157 = g.getTransform();
        
//      _0_157 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(154.313, 573.774);
        ((GeneralPath)shape).curveTo(153.989, 573.774, 153.781, 573.833, 153.692, 573.95);
        ((GeneralPath)shape).curveTo(153.604, 574.06604, 153.559, 574.34204, 153.559, 574.77203);
        ((GeneralPath)shape).curveTo(153.559, 575.26404, 153.6, 575.572, 153.68501, 575.695);
        ((GeneralPath)shape).curveTo(153.768, 575.818, 153.97801, 575.88, 154.31601, 575.88);
        ((GeneralPath)shape).curveTo(154.64001, 575.88, 154.84601, 575.816, 154.932, 575.68604);
        ((GeneralPath)shape).curveTo(155.018, 575.5561, 155.06001, 575.24506, 155.06001, 574.749);
        ((GeneralPath)shape).curveTo(155.06001, 574.33203, 155.016, 574.065, 154.92702, 573.94904);
        ((GeneralPath)shape).curveTo(154.839, 573.833, 154.634, 573.774, 154.313, 573.774);
        ((GeneralPath)shape).moveTo(154.32, 573.156);
        ((GeneralPath)shape).curveTo(154.949, 573.156, 155.36201, 573.257, 155.55801, 573.458);
        ((GeneralPath)shape).curveTo(155.75201, 573.66003, 155.85101, 574.086, 155.85101, 574.735);
        ((GeneralPath)shape).curveTo(155.85101, 575.45996, 155.75502, 575.93396, 155.56401, 576.16);
        ((GeneralPath)shape).curveTo(155.373, 576.386, 154.97002, 576.49896, 154.35602, 576.49896);
        ((GeneralPath)shape).curveTo(153.69002, 576.49896, 153.25902, 576.39496, 153.06102, 576.18494);
        ((GeneralPath)shape).curveTo(152.86502, 575.97595, 152.76602, 575.51495, 152.76602, 574.7999);
        ((GeneralPath)shape).curveTo(152.76602, 574.11395, 152.86201, 573.66895, 153.05602, 573.4639);
        ((GeneralPath)shape).curveTo(153.251, 573.259, 153.672, 573.156, 154.32, 573.156);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_157;
        g.setTransform(defaultTransform__0_157);
        g.setClip(clip__0_157);
        float alpha__0_158 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_158 = g.getClip();
        AffineTransform defaultTransform__0_158 = g.getTransform();
        
//      _0_158 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(156.5, 573.193);
        ((GeneralPath)shape).lineTo(157.274, 573.193);
        ((GeneralPath)shape).lineTo(157.243, 573.744);
        ((GeneralPath)shape).lineTo(157.26, 573.747);
        ((GeneralPath)shape).curveTo(157.41199, 573.354, 157.75299, 573.156, 158.28299, 573.156);
        ((GeneralPath)shape).curveTo(159.05399, 573.156, 159.439, 573.514, 159.439, 574.236);
        ((GeneralPath)shape).lineTo(159.439, 576.461);
        ((GeneralPath)shape).lineTo(158.65799, 576.461);
        ((GeneralPath)shape).lineTo(158.65799, 574.369);
        ((GeneralPath)shape).lineTo(158.64099, 574.14);
        ((GeneralPath)shape).curveTo(158.605, 573.89703, 158.41399, 573.774, 158.068, 573.774);
        ((GeneralPath)shape).curveTo(157.543, 573.774, 157.28, 574.024, 157.28, 574.523);
        ((GeneralPath)shape).lineTo(157.28, 576.461);
        ((GeneralPath)shape).lineTo(156.5, 576.461);
        ((GeneralPath)shape).lineTo(156.5, 573.193);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_158;
        g.setTransform(defaultTransform__0_158);
        g.setClip(clip__0_158);
        float alpha__0_159 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_159 = g.getClip();
        AffineTransform defaultTransform__0_159 = g.getTransform();
        
//      _0_159 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(162.924, 574.093);
        ((GeneralPath)shape).lineTo(162.15999, 574.093);
        ((GeneralPath)shape).curveTo(162.15698, 574.06604, 162.15399, 574.04504, 162.15, 574.031);
        ((GeneralPath)shape).curveTo(162.13399, 573.874, 162.09, 573.776, 162.015, 573.737);
        ((GeneralPath)shape).curveTo(161.941, 573.699, 161.763, 573.678, 161.478, 573.678);
        ((GeneralPath)shape).curveTo(161.07199, 573.678, 160.867, 573.81, 160.867, 574.074);
        ((GeneralPath)shape).curveTo(160.867, 574.25397, 160.903, 574.36096, 160.974, 574.39496);
        ((GeneralPath)shape).curveTo(161.045, 574.42896, 161.288, 574.457, 161.703, 574.4769);
        ((GeneralPath)shape).curveTo(162.259, 574.5039, 162.62201, 574.57794, 162.791, 574.69995);
        ((GeneralPath)shape).curveTo(162.95801, 574.819, 163.043, 575.06696, 163.043, 575.441);
        ((GeneralPath)shape).curveTo(163.043, 575.839, 162.932, 576.11597, 162.707, 576.26996);
        ((GeneralPath)shape).curveTo(162.483, 576.423, 162.081, 576.49994, 161.501, 576.49994);
        ((GeneralPath)shape).curveTo(160.945, 576.49994, 160.563, 576.43195, 160.358, 576.29193);
        ((GeneralPath)shape).curveTo(160.153, 576.15295, 160.051, 575.8939, 160.051, 575.51294);
        ((GeneralPath)shape).lineTo(160.051, 575.43097);
        ((GeneralPath)shape).lineTo(160.86299, 575.43097);
        ((GeneralPath)shape).curveTo(160.853, 575.475, 160.846, 575.51294, 160.842, 575.54);
        ((GeneralPath)shape).curveTo(160.81099, 575.828, 161.03299, 575.974, 161.511, 575.974);
        ((GeneralPath)shape).curveTo(162.004, 575.974, 162.251, 575.83, 162.251, 575.54297);
        ((GeneralPath)shape).curveTo(162.251, 575.26794, 162.098, 575.12994, 161.787, 575.12994);
        ((GeneralPath)shape).curveTo(161.09, 575.12994, 160.629, 575.06494, 160.407, 574.93195);
        ((GeneralPath)shape).curveTo(160.185, 574.7999, 160.074, 574.52496, 160.074, 574.108);
        ((GeneralPath)shape).curveTo(160.074, 573.73596, 160.175, 573.483, 160.378, 573.349);
        ((GeneralPath)shape).curveTo(160.57901, 573.217, 160.968, 573.151, 161.54501, 573.151);
        ((GeneralPath)shape).curveTo(162.08702, 573.151, 162.45401, 573.214, 162.64201, 573.342);
        ((GeneralPath)shape).curveTo(162.83, 573.471, 162.924, 573.72, 162.924, 574.093);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_159;
        g.setTransform(defaultTransform__0_159);
        g.setClip(clip__0_159);
        float alpha__0_160 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_160 = g.getClip();
        AffineTransform defaultTransform__0_160 = g.getTransform();
        
//      _0_160 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(165.737, 575.214);
        ((GeneralPath)shape).lineTo(166.515, 575.214);
        ((GeneralPath)shape).lineTo(166.515, 575.326);
        ((GeneralPath)shape).lineTo(166.467, 575.82196);
        ((GeneralPath)shape).curveTo(166.371, 576.27295, 165.90599, 576.49896, 165.069, 576.49896);
        ((GeneralPath)shape).curveTo(164.455, 576.49896, 164.047, 576.38794, 163.844, 576.165);
        ((GeneralPath)shape).curveTo(163.64299, 575.943, 163.54, 575.49695, 163.54, 574.824);
        ((GeneralPath)shape).curveTo(163.54, 574.16797, 163.64099, 573.72595, 163.844, 573.498);
        ((GeneralPath)shape).curveTo(164.045, 573.26996, 164.439, 573.156, 165.02399, 573.156);
        ((GeneralPath)shape).curveTo(165.58699, 573.156, 165.969, 573.238, 166.16998, 573.403);
        ((GeneralPath)shape).curveTo(166.36899, 573.567, 166.46999, 573.882, 166.46999, 574.34503);
        ((GeneralPath)shape).lineTo(165.69598, 574.34503);
        ((GeneralPath)shape).curveTo(165.69598, 573.965, 165.47798, 573.77405, 165.03798, 573.77405);
        ((GeneralPath)shape).curveTo(164.72697, 573.77405, 164.53098, 573.83405, 164.45097, 573.95605);
        ((GeneralPath)shape).curveTo(164.37297, 574.0751, 164.33197, 574.37305, 164.33197, 574.84503);
        ((GeneralPath)shape).curveTo(164.33197, 575.299, 164.37398, 575.58606, 164.46297, 575.70404);
        ((GeneralPath)shape).curveTo(164.54997, 575.82007, 164.76497, 575.88007, 165.10597, 575.88007);
        ((GeneralPath)shape).curveTo(165.37398, 575.88007, 165.54597, 575.84106, 165.62297, 575.7591);
        ((GeneralPath)shape).curveTo(165.698, 575.679, 165.737, 575.497, 165.737, 575.214);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_160;
        g.setTransform(defaultTransform__0_160);
        g.setClip(clip__0_160);
        float alpha__0_161 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_161 = g.getClip();
        AffineTransform defaultTransform__0_161 = g.getTransform();
        
//      _0_161 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(167.872, 573.193);
        ((GeneralPath)shape).lineTo(167.872, 576.461);
        ((GeneralPath)shape).lineTo(167.09099, 576.461);
        ((GeneralPath)shape).lineTo(167.09099, 573.193);
        ((GeneralPath)shape).lineTo(167.872, 573.193);
        ((GeneralPath)shape).moveTo(167.872, 571.796);
        ((GeneralPath)shape).lineTo(167.872, 572.448);
        ((GeneralPath)shape).lineTo(167.09099, 572.448);
        ((GeneralPath)shape).lineTo(167.09099, 571.796);
        ((GeneralPath)shape).lineTo(167.872, 571.796);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_161;
        g.setTransform(defaultTransform__0_161);
        g.setClip(clip__0_161);
        float alpha__0_162 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_162 = g.getClip();
        AffineTransform defaultTransform__0_162 = g.getTransform();
        
//      _0_162 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(170.066, 573.774);
        ((GeneralPath)shape).curveTo(169.74199, 573.774, 169.534, 573.833, 169.44499, 573.95);
        ((GeneralPath)shape).curveTo(169.357, 574.06604, 169.312, 574.34204, 169.312, 574.77203);
        ((GeneralPath)shape).curveTo(169.312, 575.26404, 169.353, 575.572, 169.438, 575.695);
        ((GeneralPath)shape).curveTo(169.521, 575.818, 169.731, 575.88, 170.069, 575.88);
        ((GeneralPath)shape).curveTo(170.393, 575.88, 170.599, 575.816, 170.684, 575.68604);
        ((GeneralPath)shape).curveTo(170.76901, 575.556, 170.81201, 575.24506, 170.81201, 574.749);
        ((GeneralPath)shape).curveTo(170.81201, 574.33203, 170.768, 574.065, 170.67902, 573.94904);
        ((GeneralPath)shape).curveTo(170.592, 573.833, 170.388, 573.774, 170.066, 573.774);
        ((GeneralPath)shape).moveTo(170.074, 573.156);
        ((GeneralPath)shape).curveTo(170.703, 573.156, 171.11601, 573.257, 171.31201, 573.458);
        ((GeneralPath)shape).curveTo(171.50601, 573.66003, 171.60501, 574.086, 171.60501, 574.735);
        ((GeneralPath)shape).curveTo(171.60501, 575.45996, 171.51001, 575.93396, 171.31902, 576.16);
        ((GeneralPath)shape).curveTo(171.12802, 576.386, 170.72502, 576.49896, 170.11102, 576.49896);
        ((GeneralPath)shape).curveTo(169.44603, 576.49896, 169.01402, 576.39496, 168.81702, 576.18494);
        ((GeneralPath)shape).curveTo(168.62102, 575.97595, 168.52202, 575.51495, 168.52202, 574.7999);
        ((GeneralPath)shape).curveTo(168.52202, 574.11395, 168.61801, 573.66895, 168.81201, 573.4639);
        ((GeneralPath)shape).curveTo(169.004, 573.259, 169.425, 573.156, 170.074, 573.156);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_162;
        g.setTransform(defaultTransform__0_162);
        g.setClip(clip__0_162);
        float alpha__0_163 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_163 = g.getClip();
        AffineTransform defaultTransform__0_163 = g.getTransform();
        
//      _0_163 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(175.156, 573.193);
        ((GeneralPath)shape).lineTo(175.156, 576.461);
        ((GeneralPath)shape).lineTo(174.375, 576.461);
        ((GeneralPath)shape).lineTo(174.419, 575.9);
        ((GeneralPath)shape).lineTo(174.405, 575.89703);
        ((GeneralPath)shape).curveTo(174.253, 576.296, 173.90399, 576.499, 173.354, 576.499);
        ((GeneralPath)shape).curveTo(172.616, 576.499, 172.246, 576.13, 172.246, 575.388);
        ((GeneralPath)shape).lineTo(172.246, 573.19403);
        ((GeneralPath)shape).lineTo(173.02701, 573.19403);
        ((GeneralPath)shape).lineTo(173.02701, 575.20105);
        ((GeneralPath)shape).curveTo(173.02701, 575.47705, 173.06401, 575.66003, 173.143, 575.749);
        ((GeneralPath)shape).curveTo(173.22, 575.83704, 173.38, 575.88104, 173.62401, 575.88104);
        ((GeneralPath)shape).curveTo(174.12401, 575.88104, 174.37401, 575.58, 174.37401, 574.97906);
        ((GeneralPath)shape).lineTo(174.37401, 573.19507);
        ((GeneralPath)shape).lineTo(175.156, 573.19507);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_163;
        g.setTransform(defaultTransform__0_163);
        g.setClip(clip__0_163);
        float alpha__0_164 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_164 = g.getClip();
        AffineTransform defaultTransform__0_164 = g.getTransform();
        
//      _0_164 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(178.67, 574.093);
        ((GeneralPath)shape).lineTo(177.90599, 574.093);
        ((GeneralPath)shape).curveTo(177.90298, 574.06604, 177.89899, 574.04504, 177.896, 574.031);
        ((GeneralPath)shape).curveTo(177.881, 573.874, 177.836, 573.776, 177.761, 573.737);
        ((GeneralPath)shape).curveTo(177.688, 573.699, 177.509, 573.678, 177.224, 573.678);
        ((GeneralPath)shape).curveTo(176.818, 573.678, 176.613, 573.81, 176.613, 574.074);
        ((GeneralPath)shape).curveTo(176.613, 574.25397, 176.649, 574.36096, 176.72, 574.39496);
        ((GeneralPath)shape).curveTo(176.791, 574.42896, 177.034, 574.457, 177.449, 574.4769);
        ((GeneralPath)shape).curveTo(178.005, 574.5039, 178.36801, 574.57794, 178.537, 574.69995);
        ((GeneralPath)shape).curveTo(178.70401, 574.819, 178.789, 575.06696, 178.789, 575.441);
        ((GeneralPath)shape).curveTo(178.789, 575.839, 178.67801, 576.11597, 178.453, 576.26996);
        ((GeneralPath)shape).curveTo(178.23, 576.423, 177.827, 576.49994, 177.24701, 576.49994);
        ((GeneralPath)shape).curveTo(176.69101, 576.49994, 176.309, 576.43195, 176.104, 576.29193);
        ((GeneralPath)shape).curveTo(175.899, 576.15295, 175.796, 575.8939, 175.796, 575.51294);
        ((GeneralPath)shape).lineTo(175.796, 575.43097);
        ((GeneralPath)shape).lineTo(176.608, 575.43097);
        ((GeneralPath)shape).curveTo(176.598, 575.475, 176.591, 575.51294, 176.588, 575.54);
        ((GeneralPath)shape).curveTo(176.55699, 575.828, 176.778, 575.974, 177.256, 575.974);
        ((GeneralPath)shape).curveTo(177.749, 575.974, 177.996, 575.83, 177.996, 575.54297);
        ((GeneralPath)shape).curveTo(177.996, 575.26794, 177.842, 575.12994, 177.532, 575.12994);
        ((GeneralPath)shape).curveTo(176.83499, 575.12994, 176.374, 575.06494, 176.153, 574.93195);
        ((GeneralPath)shape).curveTo(175.931, 574.7999, 175.82, 574.52496, 175.82, 574.108);
        ((GeneralPath)shape).curveTo(175.82, 573.73596, 175.921, 573.483, 176.123, 573.349);
        ((GeneralPath)shape).curveTo(176.324, 573.217, 176.713, 573.151, 177.29001, 573.151);
        ((GeneralPath)shape).curveTo(177.83202, 573.151, 178.199, 573.214, 178.386, 573.342);
        ((GeneralPath)shape).curveTo(178.577, 573.471, 178.67, 573.72, 178.67, 574.093);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_164;
        g.setTransform(defaultTransform__0_164);
        g.setClip(clip__0_164);
        float alpha__0_165 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_165 = g.getClip();
        AffineTransform defaultTransform__0_165 = g.getTransform();
        
//      _0_165 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(179.407, 573.193);
        ((GeneralPath)shape).lineTo(180.181, 573.193);
        ((GeneralPath)shape).lineTo(180.15, 573.744);
        ((GeneralPath)shape).lineTo(180.16699, 573.747);
        ((GeneralPath)shape).curveTo(180.318, 573.354, 180.65999, 573.156, 181.18999, 573.156);
        ((GeneralPath)shape).curveTo(181.96098, 573.156, 182.346, 573.514, 182.346, 574.236);
        ((GeneralPath)shape).lineTo(182.346, 576.461);
        ((GeneralPath)shape).lineTo(181.56499, 576.461);
        ((GeneralPath)shape).lineTo(181.56499, 574.369);
        ((GeneralPath)shape).lineTo(181.54799, 574.14);
        ((GeneralPath)shape).curveTo(181.512, 573.89703, 181.32098, 573.774, 180.97499, 573.774);
        ((GeneralPath)shape).curveTo(180.45, 573.774, 180.187, 574.024, 180.187, 574.523);
        ((GeneralPath)shape).lineTo(180.187, 576.461);
        ((GeneralPath)shape).lineTo(179.40599, 576.461);
        ((GeneralPath)shape).lineTo(179.40599, 573.193);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_165;
        g.setTransform(defaultTransform__0_165);
        g.setClip(clip__0_165);
        float alpha__0_166 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_166 = g.getClip();
        AffineTransform defaultTransform__0_166 = g.getTransform();
        
//      _0_166 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(185.217, 574.489);
        ((GeneralPath)shape).lineTo(185.213, 574.359);
        ((GeneralPath)shape).curveTo(185.213, 574.099, 185.16899, 573.931, 185.078, 573.853);
        ((GeneralPath)shape).curveTo(184.99, 573.776, 184.793, 573.737, 184.494, 573.737);
        ((GeneralPath)shape).curveTo(184.203, 573.737, 184.015, 573.783, 183.92601, 573.877);
        ((GeneralPath)shape).curveTo(183.839, 573.97003, 183.79501, 574.175, 183.79501, 574.489);
        ((GeneralPath)shape).lineTo(185.217, 574.489);
        ((GeneralPath)shape).moveTo(185.21, 575.415);
        ((GeneralPath)shape).lineTo(185.99501, 575.415);
        ((GeneralPath)shape).lineTo(185.99501, 575.542);
        ((GeneralPath)shape).curveTo(185.99501, 576.179, 185.51701, 576.49896, 184.56201, 576.49896);
        ((GeneralPath)shape).curveTo(183.91402, 576.49896, 183.49101, 576.38995, 183.29001, 576.167);
        ((GeneralPath)shape).curveTo(183.091, 575.946, 182.99, 575.479, 182.99, 574.763);
        ((GeneralPath)shape).curveTo(182.99, 574.127, 183.09401, 573.7, 183.304, 573.481);
        ((GeneralPath)shape).curveTo(183.512, 573.262, 183.922, 573.153, 184.529, 573.153);
        ((GeneralPath)shape).curveTo(185.11101, 573.153, 185.501, 573.25903, 185.699, 573.472);
        ((GeneralPath)shape).curveTo(185.897, 573.68396, 185.996, 574.10297, 185.996, 574.729);
        ((GeneralPath)shape).lineTo(185.996, 574.968);
        ((GeneralPath)shape).lineTo(183.789, 574.968);
        ((GeneralPath)shape).curveTo(183.786, 575.039, 183.782, 575.08704, 183.782, 575.112);
        ((GeneralPath)shape).curveTo(183.782, 575.433, 183.831, 575.646, 183.93, 575.755);
        ((GeneralPath)shape).curveTo(184.02899, 575.86, 184.22499, 575.915, 184.52199, 575.915);
        ((GeneralPath)shape).curveTo(184.80899, 575.915, 184.995, 575.88495, 185.08199, 575.821);
        ((GeneralPath)shape).curveTo(185.166, 575.759, 185.21, 575.624, 185.21, 575.415);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_166;
        g.setTransform(defaultTransform__0_166);
        g.setClip(clip__0_166);
        float alpha__0_167 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_167 = g.getClip();
        AffineTransform defaultTransform__0_167 = g.getTransform();
        
//      _0_167 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(189.366, 574.093);
        ((GeneralPath)shape).lineTo(188.60199, 574.093);
        ((GeneralPath)shape).curveTo(188.59898, 574.06604, 188.596, 574.04504, 188.592, 574.031);
        ((GeneralPath)shape).curveTo(188.577, 573.874, 188.532, 573.776, 188.457, 573.737);
        ((GeneralPath)shape).curveTo(188.384, 573.699, 188.205, 573.678, 187.92, 573.678);
        ((GeneralPath)shape).curveTo(187.51399, 573.678, 187.31, 573.81, 187.31, 574.074);
        ((GeneralPath)shape).curveTo(187.31, 574.25397, 187.346, 574.36096, 187.41699, 574.39496);
        ((GeneralPath)shape).curveTo(187.48799, 574.42896, 187.73, 574.457, 188.14499, 574.4769);
        ((GeneralPath)shape).curveTo(188.70099, 574.5039, 189.064, 574.57794, 189.23299, 574.69995);
        ((GeneralPath)shape).curveTo(189.4, 574.819, 189.48499, 575.06696, 189.48499, 575.441);
        ((GeneralPath)shape).curveTo(189.48499, 575.839, 189.374, 576.11597, 189.14899, 576.26996);
        ((GeneralPath)shape).curveTo(188.92499, 576.423, 188.52298, 576.49994, 187.943, 576.49994);
        ((GeneralPath)shape).curveTo(187.387, 576.49994, 187.00499, 576.43195, 186.79999, 576.29193);
        ((GeneralPath)shape).curveTo(186.59499, 576.15295, 186.49298, 575.8939, 186.49298, 575.51294);
        ((GeneralPath)shape).lineTo(186.49298, 575.43097);
        ((GeneralPath)shape).lineTo(187.30498, 575.43097);
        ((GeneralPath)shape).curveTo(187.29498, 575.475, 187.28798, 575.51294, 187.28497, 575.54);
        ((GeneralPath)shape).curveTo(187.25397, 575.828, 187.47597, 575.974, 187.95297, 575.974);
        ((GeneralPath)shape).curveTo(188.44597, 575.974, 188.69298, 575.83, 188.69298, 575.54297);
        ((GeneralPath)shape).curveTo(188.69298, 575.26794, 188.53897, 575.12994, 188.22897, 575.12994);
        ((GeneralPath)shape).curveTo(187.53098, 575.12994, 187.07097, 575.06494, 186.84897, 574.93195);
        ((GeneralPath)shape).curveTo(186.62697, 574.7999, 186.51598, 574.52496, 186.51598, 574.108);
        ((GeneralPath)shape).curveTo(186.51598, 573.73596, 186.61697, 573.483, 186.81897, 573.349);
        ((GeneralPath)shape).curveTo(187.02097, 573.217, 187.40897, 573.151, 187.98598, 573.151);
        ((GeneralPath)shape).curveTo(188.52798, 573.151, 188.89497, 573.214, 189.08298, 573.342);
        ((GeneralPath)shape).curveTo(189.272, 573.471, 189.366, 573.72, 189.366, 574.093);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_167;
        g.setTransform(defaultTransform__0_167);
        g.setClip(clip__0_167);
        float alpha__0_168 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_168 = g.getClip();
        AffineTransform defaultTransform__0_168 = g.getTransform();
        
//      _0_168 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(192.824, 574.093);
        ((GeneralPath)shape).lineTo(192.06, 574.093);
        ((GeneralPath)shape).curveTo(192.05699, 574.06604, 192.053, 574.04504, 192.05, 574.031);
        ((GeneralPath)shape).curveTo(192.035, 573.874, 191.99, 573.776, 191.91501, 573.737);
        ((GeneralPath)shape).curveTo(191.84201, 573.699, 191.66301, 573.678, 191.378, 573.678);
        ((GeneralPath)shape).curveTo(190.972, 573.678, 190.76701, 573.81, 190.76701, 574.074);
        ((GeneralPath)shape).curveTo(190.76701, 574.25397, 190.80301, 574.36096, 190.87502, 574.39496);
        ((GeneralPath)shape).curveTo(190.94601, 574.42896, 191.18802, 574.457, 191.60301, 574.4769);
        ((GeneralPath)shape).curveTo(192.15901, 574.5039, 192.52202, 574.57794, 192.69101, 574.69995);
        ((GeneralPath)shape).curveTo(192.85802, 574.819, 192.94301, 575.06696, 192.94301, 575.441);
        ((GeneralPath)shape).curveTo(192.94301, 575.839, 192.83202, 576.11597, 192.60701, 576.26996);
        ((GeneralPath)shape).curveTo(192.38301, 576.423, 191.981, 576.49994, 191.40102, 576.49994);
        ((GeneralPath)shape).curveTo(190.84502, 576.49994, 190.46301, 576.43195, 190.25801, 576.29193);
        ((GeneralPath)shape).curveTo(190.05301, 576.15295, 189.951, 575.8939, 189.951, 575.51294);
        ((GeneralPath)shape).lineTo(189.951, 575.43097);
        ((GeneralPath)shape).lineTo(190.763, 575.43097);
        ((GeneralPath)shape).curveTo(190.753, 575.475, 190.746, 575.51294, 190.743, 575.54);
        ((GeneralPath)shape).curveTo(190.71199, 575.828, 190.93399, 575.974, 191.411, 575.974);
        ((GeneralPath)shape).curveTo(191.90399, 575.974, 192.151, 575.83, 192.151, 575.54297);
        ((GeneralPath)shape).curveTo(192.151, 575.26794, 191.997, 575.12994, 191.687, 575.12994);
        ((GeneralPath)shape).curveTo(190.989, 575.12994, 190.52899, 575.06494, 190.30699, 574.93195);
        ((GeneralPath)shape).curveTo(190.08499, 574.7999, 189.974, 574.52496, 189.974, 574.108);
        ((GeneralPath)shape).curveTo(189.974, 573.73596, 190.075, 573.483, 190.277, 573.349);
        ((GeneralPath)shape).curveTo(190.47899, 573.217, 190.86699, 573.151, 191.444, 573.151);
        ((GeneralPath)shape).curveTo(191.98601, 573.151, 192.353, 573.214, 192.541, 573.342);
        ((GeneralPath)shape).curveTo(192.73, 573.471, 192.824, 573.72, 192.824, 574.093);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_168;
        g.setTransform(defaultTransform__0_168);
        g.setClip(clip__0_168);
        float alpha__0_169 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_169 = g.getClip();
        AffineTransform defaultTransform__0_169 = g.getTransform();
        
//      _0_169 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(197.653, 573.672);
        ((GeneralPath)shape).lineTo(196.722, 573.672);
        ((GeneralPath)shape).lineTo(196.576, 574.574);
        ((GeneralPath)shape).lineTo(197.504, 574.574);
        ((GeneralPath)shape).lineTo(197.653, 573.672);
        ((GeneralPath)shape).moveTo(198.861, 574.574);
        ((GeneralPath)shape).lineTo(198.77599, 575.121);
        ((GeneralPath)shape).lineTo(198.01898, 575.121);
        ((GeneralPath)shape).lineTo(197.80798, 576.461);
        ((GeneralPath)shape).lineTo(197.21098, 576.461);
        ((GeneralPath)shape).lineTo(197.42198, 575.121);
        ((GeneralPath)shape).lineTo(196.49098, 575.121);
        ((GeneralPath)shape).lineTo(196.27998, 576.461);
        ((GeneralPath)shape).lineTo(195.68999, 576.461);
        ((GeneralPath)shape).lineTo(195.902, 575.121);
        ((GeneralPath)shape).lineTo(195.14499, 575.121);
        ((GeneralPath)shape).lineTo(195.22699, 574.574);
        ((GeneralPath)shape).lineTo(195.98799, 574.574);
        ((GeneralPath)shape).lineTo(196.135, 573.672);
        ((GeneralPath)shape).lineTo(195.37799, 573.672);
        ((GeneralPath)shape).lineTo(195.463, 573.125);
        ((GeneralPath)shape).lineTo(196.22, 573.125);
        ((GeneralPath)shape).lineTo(196.432, 571.796);
        ((GeneralPath)shape).lineTo(197.018, 571.796);
        ((GeneralPath)shape).lineTo(196.81001, 573.125);
        ((GeneralPath)shape).lineTo(197.74101, 573.125);
        ((GeneralPath)shape).lineTo(197.949, 571.796);
        ((GeneralPath)shape).lineTo(198.546, 571.796);
        ((GeneralPath)shape).lineTo(198.335, 573.125);
        ((GeneralPath)shape).lineTo(199.08801, 573.125);
        ((GeneralPath)shape).lineTo(199.00601, 573.672);
        ((GeneralPath)shape).lineTo(198.25201, 573.672);
        ((GeneralPath)shape).lineTo(198.10202, 574.574);
        ((GeneralPath)shape).lineTo(198.861, 574.574);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_169;
        g.setTransform(defaultTransform__0_169);
        g.setClip(clip__0_169);
        float alpha__0_170 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_170 = g.getClip();
        AffineTransform defaultTransform__0_170 = g.getTransform();
        
//      _0_170 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(171.532, 563.29);
        ((GeneralPath)shape).lineTo(171.532, 567.956);
        ((GeneralPath)shape).lineTo(170.648, 567.956);
        ((GeneralPath)shape).lineTo(170.648, 565.939);
        ((GeneralPath)shape).lineTo(168.465, 565.939);
        ((GeneralPath)shape).lineTo(168.465, 567.956);
        ((GeneralPath)shape).lineTo(167.582, 567.956);
        ((GeneralPath)shape).lineTo(167.582, 563.29);
        ((GeneralPath)shape).lineTo(168.465, 563.29);
        ((GeneralPath)shape).lineTo(168.465, 565.194);
        ((GeneralPath)shape).lineTo(170.648, 565.194);
        ((GeneralPath)shape).lineTo(170.648, 563.29);
        ((GeneralPath)shape).lineTo(171.532, 563.29);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_170;
        g.setTransform(defaultTransform__0_170);
        g.setClip(clip__0_170);
        float alpha__0_171 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_171 = g.getClip();
        AffineTransform defaultTransform__0_171 = g.getTransform();
        
//      _0_171 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(173.111, 564.688);
        ((GeneralPath)shape).lineTo(173.111, 567.956);
        ((GeneralPath)shape).lineTo(172.32999, 567.956);
        ((GeneralPath)shape).lineTo(172.32999, 564.688);
        ((GeneralPath)shape).lineTo(173.111, 564.688);
        ((GeneralPath)shape).moveTo(173.111, 563.29);
        ((GeneralPath)shape).lineTo(173.111, 563.943);
        ((GeneralPath)shape).lineTo(172.32999, 563.943);
        ((GeneralPath)shape).lineTo(172.32999, 563.29);
        ((GeneralPath)shape).lineTo(173.111, 563.29);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_171;
        g.setTransform(defaultTransform__0_171);
        g.setClip(clip__0_171);
        float alpha__0_172 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_172 = g.getClip();
        AffineTransform defaultTransform__0_172 = g.getTransform();
        
//      _0_172 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(175.985, 564.688);
        ((GeneralPath)shape).lineTo(175.985, 565.28296);
        ((GeneralPath)shape).lineTo(174.73, 565.28296);
        ((GeneralPath)shape).lineTo(174.73, 566.92395);
        ((GeneralPath)shape).curveTo(174.73, 567.22595, 174.844, 567.3779, 175.07399, 567.3779);
        ((GeneralPath)shape).curveTo(175.32599, 567.3779, 175.45299, 567.19495, 175.45299, 566.82794);
        ((GeneralPath)shape).lineTo(175.45299, 566.69794);
        ((GeneralPath)shape).lineTo(176.11798, 566.69794);
        ((GeneralPath)shape).lineTo(176.11798, 566.86194);
        ((GeneralPath)shape).curveTo(176.11798, 567.01196, 176.11497, 567.13995, 176.10397, 567.2479);
        ((GeneralPath)shape).curveTo(176.06197, 567.74695, 175.69098, 567.99695, 174.99197, 567.99695);
        ((GeneralPath)shape).curveTo(174.29597, 567.99695, 173.94797, 567.67694, 173.94797, 567.03595);
        ((GeneralPath)shape).lineTo(173.94797, 565.28296);
        ((GeneralPath)shape).lineTo(173.52496, 565.28296);
        ((GeneralPath)shape).lineTo(173.52496, 564.688);
        ((GeneralPath)shape).lineTo(173.94797, 564.688);
        ((GeneralPath)shape).lineTo(173.94797, 563.957);
        ((GeneralPath)shape).lineTo(174.72897, 563.957);
        ((GeneralPath)shape).lineTo(174.72897, 564.688);
        ((GeneralPath)shape).lineTo(175.985, 564.688);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_172;
        g.setTransform(defaultTransform__0_172);
        g.setClip(clip__0_172);
        float alpha__0_173 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_173 = g.getClip();
        AffineTransform defaultTransform__0_173 = g.getTransform();
        
//      _0_173 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(179.271, 565.587);
        ((GeneralPath)shape).lineTo(178.50699, 565.587);
        ((GeneralPath)shape).curveTo(178.50398, 565.56, 178.50099, 565.539, 178.497, 565.52496);
        ((GeneralPath)shape).curveTo(178.48099, 565.368, 178.437, 565.26996, 178.362, 565.23096);
        ((GeneralPath)shape).curveTo(178.288, 565.194, 178.11, 565.173, 177.825, 565.173);
        ((GeneralPath)shape).curveTo(177.41899, 565.173, 177.214, 565.30396, 177.214, 565.569);
        ((GeneralPath)shape).curveTo(177.214, 565.748, 177.25, 565.85596, 177.321, 565.88995);
        ((GeneralPath)shape).curveTo(177.392, 565.92395, 177.635, 565.95197, 178.05, 565.9719);
        ((GeneralPath)shape).curveTo(178.606, 565.9989, 178.96901, 566.07294, 179.138, 566.1939);
        ((GeneralPath)shape).curveTo(179.30501, 566.3139, 179.39, 566.5609, 179.39, 566.9359);
        ((GeneralPath)shape).curveTo(179.39, 567.3329, 179.279, 567.6109, 179.054, 567.7639);
        ((GeneralPath)shape).curveTo(178.83, 567.9179, 178.428, 567.99493, 177.848, 567.99493);
        ((GeneralPath)shape).curveTo(177.292, 567.99493, 176.91, 567.92694, 176.705, 567.78595);
        ((GeneralPath)shape).curveTo(176.5, 567.647, 176.398, 567.38794, 176.398, 567.00696);
        ((GeneralPath)shape).lineTo(176.398, 566.925);
        ((GeneralPath)shape).lineTo(177.20999, 566.925);
        ((GeneralPath)shape).curveTo(177.2, 566.97, 177.193, 567.00696, 177.189, 567.034);
        ((GeneralPath)shape).curveTo(177.15799, 567.323, 177.37999, 567.469, 177.858, 567.469);
        ((GeneralPath)shape).curveTo(178.351, 567.469, 178.598, 567.325, 178.598, 567.03796);
        ((GeneralPath)shape).curveTo(178.598, 566.76294, 178.445, 566.62396, 178.134, 566.62396);
        ((GeneralPath)shape).curveTo(177.437, 566.62396, 176.976, 566.55994, 176.754, 566.42596);
        ((GeneralPath)shape).curveTo(176.532, 566.29395, 176.421, 566.01996, 176.421, 565.60297);
        ((GeneralPath)shape).curveTo(176.421, 565.23, 176.522, 564.977, 176.725, 564.844);
        ((GeneralPath)shape).curveTo(176.92601, 564.712, 177.315, 564.646, 177.89201, 564.646);
        ((GeneralPath)shape).curveTo(178.43402, 564.646, 178.80101, 564.709, 178.98901, 564.837);
        ((GeneralPath)shape).curveTo(179.177, 564.965, 179.271, 565.215, 179.271, 565.587);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_173;
        g.setTransform(defaultTransform__0_173);
        g.setClip(clip__0_173);
        float alpha__0_174 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_174 = g.getClip();
        AffineTransform defaultTransform__0_174 = g.getTransform();
        
//      _0_174 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(183.633, 564.083);
        ((GeneralPath)shape).lineTo(183.633, 567.956);
        ((GeneralPath)shape).lineTo(182.75, 567.956);
        ((GeneralPath)shape).lineTo(182.75, 564.083);
        ((GeneralPath)shape).lineTo(181.405, 564.083);
        ((GeneralPath)shape).lineTo(181.405, 563.29);
        ((GeneralPath)shape).lineTo(185.024, 563.29);
        ((GeneralPath)shape).lineTo(185.024, 564.083);
        ((GeneralPath)shape).lineTo(183.633, 564.083);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_174;
        g.setTransform(defaultTransform__0_174);
        g.setClip(clip__0_174);
        float alpha__0_175 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_175 = g.getClip();
        AffineTransform defaultTransform__0_175 = g.getTransform();
        
//      _0_175 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(186.783, 566.493);
        ((GeneralPath)shape).curveTo(186.343, 566.493, 186.121, 566.644, 186.121, 566.951);
        ((GeneralPath)shape).curveTo(186.121, 567.16296, 186.16501, 567.30096, 186.257, 567.368);
        ((GeneralPath)shape).curveTo(186.347, 567.432, 186.53801, 567.467, 186.83, 567.467);
        ((GeneralPath)shape).curveTo(187.304, 567.467, 187.542, 567.307, 187.542, 566.98596);
        ((GeneralPath)shape).curveTo(187.543, 566.657, 187.291, 566.493, 186.783, 566.493);
        ((GeneralPath)shape).moveTo(186.261, 565.635);
        ((GeneralPath)shape).lineTo(185.463, 565.635);
        ((GeneralPath)shape).curveTo(185.463, 565.243, 185.553, 564.98303, 185.735, 564.849);
        ((GeneralPath)shape).curveTo(185.916, 564.717, 186.27701, 564.651, 186.817, 564.651);
        ((GeneralPath)shape).curveTo(187.403, 564.651, 187.799, 564.731, 188.007, 564.894);
        ((GeneralPath)shape).curveTo(188.214, 565.05396, 188.317, 565.36395, 188.317, 565.82);
        ((GeneralPath)shape).lineTo(188.317, 567.95703);
        ((GeneralPath)shape).lineTo(187.536, 567.95703);
        ((GeneralPath)shape).lineTo(187.57399, 567.50903);
        ((GeneralPath)shape).lineTo(187.553, 567.50604);
        ((GeneralPath)shape).curveTo(187.403, 567.83, 187.056, 567.994, 186.512, 567.994);
        ((GeneralPath)shape).curveTo(185.724, 567.994, 185.32799, 567.659, 185.32799, 566.986);
        ((GeneralPath)shape).curveTo(185.32799, 566.307, 185.72998, 565.96704, 186.53899, 565.96704);
        ((GeneralPath)shape).curveTo(187.07799, 565.96704, 187.40498, 566.09, 187.52098, 566.33905);
        ((GeneralPath)shape).lineTo(187.53499, 566.33905);
        ((GeneralPath)shape).lineTo(187.53499, 565.81006);
        ((GeneralPath)shape).curveTo(187.53499, 565.55505, 187.49098, 565.38605, 187.402, 565.30206);
        ((GeneralPath)shape).curveTo(187.314, 565.2201, 187.133, 565.17706, 186.85599, 565.17706);
        ((GeneralPath)shape).curveTo(186.46, 565.177, 186.261, 565.329, 186.261, 565.635);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_175;
        g.setTransform(defaultTransform__0_175);
        g.setClip(clip__0_175);
        float alpha__0_176 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_176 = g.getClip();
        AffineTransform defaultTransform__0_176 = g.getTransform();
        
//      _0_176 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(189.877, 563.29);
        ((GeneralPath)shape).lineTo(189.877, 565.96);
        ((GeneralPath)shape).lineTo(190.078, 565.96);
        ((GeneralPath)shape).lineTo(190.958, 564.688);
        ((GeneralPath)shape).lineTo(191.865, 564.688);
        ((GeneralPath)shape).lineTo(190.729, 566.227);
        ((GeneralPath)shape).lineTo(192.097, 567.956);
        ((GeneralPath)shape).lineTo(191.128, 567.956);
        ((GeneralPath)shape).lineTo(190.068, 566.507);
        ((GeneralPath)shape).lineTo(189.877, 566.507);
        ((GeneralPath)shape).lineTo(189.877, 567.956);
        ((GeneralPath)shape).lineTo(189.096, 567.956);
        ((GeneralPath)shape).lineTo(189.096, 563.29);
        ((GeneralPath)shape).lineTo(189.877, 563.29);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_176;
        g.setTransform(defaultTransform__0_176);
        g.setClip(clip__0_176);
        float alpha__0_177 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_177 = g.getClip();
        AffineTransform defaultTransform__0_177 = g.getTransform();
        
//      _0_177 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(194.558, 565.983);
        ((GeneralPath)shape).lineTo(194.554, 565.85297);
        ((GeneralPath)shape).curveTo(194.554, 565.59296, 194.51, 565.42596, 194.419, 565.347);
        ((GeneralPath)shape).curveTo(194.33101, 565.26996, 194.134, 565.23096, 193.835, 565.23096);
        ((GeneralPath)shape).curveTo(193.544, 565.23096, 193.356, 565.277, 193.26701, 565.37195);
        ((GeneralPath)shape).curveTo(193.18001, 565.4639, 193.13602, 565.66895, 193.13602, 565.983);
        ((GeneralPath)shape).lineTo(194.558, 565.983);
        ((GeneralPath)shape).moveTo(194.55, 566.91);
        ((GeneralPath)shape).lineTo(195.335, 566.91);
        ((GeneralPath)shape).lineTo(195.335, 567.03595);
        ((GeneralPath)shape).curveTo(195.335, 567.67395, 194.85701, 567.9929, 193.90201, 567.9929);
        ((GeneralPath)shape).curveTo(193.25401, 567.9929, 192.83101, 567.8839, 192.63, 567.6619);
        ((GeneralPath)shape).curveTo(192.431, 567.4409, 192.33, 566.9729, 192.33, 566.2569);
        ((GeneralPath)shape).curveTo(192.33, 565.6209, 192.434, 565.1939, 192.644, 564.9759);
        ((GeneralPath)shape).curveTo(192.85199, 564.7569, 193.262, 564.6479, 193.869, 564.6479);
        ((GeneralPath)shape).curveTo(194.451, 564.6479, 194.841, 564.75287, 195.039, 564.96686);
        ((GeneralPath)shape).curveTo(195.237, 565.17883, 195.336, 565.59784, 195.336, 566.22284);
        ((GeneralPath)shape).lineTo(195.336, 566.46185);
        ((GeneralPath)shape).lineTo(193.129, 566.46185);
        ((GeneralPath)shape).curveTo(193.12599, 566.5339, 193.122, 566.58185, 193.122, 566.60583);
        ((GeneralPath)shape).curveTo(193.122, 566.9268, 193.17099, 567.1408, 193.26999, 567.24884);
        ((GeneralPath)shape).curveTo(193.36899, 567.35486, 193.56499, 567.40985, 193.86198, 567.40985);
        ((GeneralPath)shape).curveTo(194.14899, 567.40985, 194.33499, 567.37885, 194.42198, 567.3149);
        ((GeneralPath)shape).curveTo(194.506, 567.253, 194.55, 567.118, 194.55, 566.91);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_177;
        g.setTransform(defaultTransform__0_177);
        g.setClip(clip__0_177);
        float alpha__0_178 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_178 = g.getClip();
        AffineTransform defaultTransform__0_178 = g.getTransform();
        
//      _0_178 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(195.984, 564.688);
        ((GeneralPath)shape).lineTo(196.758, 564.688);
        ((GeneralPath)shape).lineTo(196.72699, 565.238);
        ((GeneralPath)shape).lineTo(196.74399, 565.242);
        ((GeneralPath)shape).curveTo(196.89598, 564.848, 197.23698, 564.65, 197.76698, 564.65);
        ((GeneralPath)shape).curveTo(198.53798, 564.65, 198.92299, 565.00903, 198.92299, 565.73004);
        ((GeneralPath)shape).lineTo(198.92299, 567.95605);
        ((GeneralPath)shape).lineTo(198.14198, 567.95605);
        ((GeneralPath)shape).lineTo(198.14198, 565.8641);
        ((GeneralPath)shape).lineTo(198.12498, 565.6351);
        ((GeneralPath)shape).curveTo(198.08899, 565.39307, 197.89798, 565.2701, 197.55199, 565.2701);
        ((GeneralPath)shape).curveTo(197.027, 565.2701, 196.76399, 565.5191, 196.76399, 566.01807);
        ((GeneralPath)shape).lineTo(196.76399, 567.95605);
        ((GeneralPath)shape).lineTo(195.98299, 567.95605);
        ((GeneralPath)shape).lineTo(195.98299, 564.688);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_178;
        g.setTransform(defaultTransform__0_178);
        g.setClip(clip__0_178);
        float alpha__0_179 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_179 = g.getClip();
        AffineTransform defaultTransform__0_179 = g.getTransform();
        
//      _0_179 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        stroke = new BasicStroke(1.068f,1,0,10.0f,null,0.0f);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(203.725, 579.374);
        ((GeneralPath)shape).curveTo(203.725, 579.95404, 204.196, 580.426, 204.776, 580.426);
        ((GeneralPath)shape).lineTo(291.066, 580.426);
        ((GeneralPath)shape).curveTo(291.646, 580.426, 292.117, 579.95404, 292.117, 579.374);
        ((GeneralPath)shape).lineTo(292.117, 562.42303);
        ((GeneralPath)shape).curveTo(292.117, 561.843, 291.64502, 561.37103, 291.066, 561.37103);
        ((GeneralPath)shape).lineTo(204.776, 561.37103);
        ((GeneralPath)shape).curveTo(204.195, 561.37103, 203.725, 561.843, 203.725, 562.42303);
        ((GeneralPath)shape).lineTo(203.725, 579.374);
        ((GeneralPath)shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_179;
        g.setTransform(defaultTransform__0_179);
        g.setClip(clip__0_179);
        float alpha__0_180 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_180 = g.getClip();
        AffineTransform defaultTransform__0_180 = g.getTransform();
        
//      _0_180 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(45.801, 564.408);
        ((GeneralPath)shape).lineTo(45.801, 569.74);
        ((GeneralPath)shape).lineTo(44.09, 569.74);
        ((GeneralPath)shape).lineTo(42.569, 566.81396);
        ((GeneralPath)shape).curveTo(42.495, 566.66895, 42.376, 566.41394, 42.214, 566.048);
        ((GeneralPath)shape).lineTo(42.043, 565.665);
        ((GeneralPath)shape).lineTo(41.875, 565.282);
        ((GeneralPath)shape).lineTo(41.836, 565.282);
        ((GeneralPath)shape).lineTo(41.851997, 565.634);
        ((GeneralPath)shape).lineTo(41.864, 565.982);
        ((GeneralPath)shape).lineTo(41.871998, 566.68097);
        ((GeneralPath)shape).lineTo(41.871998, 569.74);
        ((GeneralPath)shape).lineTo(40.862, 569.74);
        ((GeneralPath)shape).lineTo(40.862, 564.408);
        ((GeneralPath)shape).lineTo(42.572998, 564.408);
        ((GeneralPath)shape).lineTo(43.956997, 567.111);
        ((GeneralPath)shape).curveTo(44.077995, 567.349, 44.220997, 567.64, 44.381996, 567.982);
        ((GeneralPath)shape).lineTo(44.584995, 568.416);
        ((GeneralPath)shape).lineTo(44.787994, 568.854);
        ((GeneralPath)shape).lineTo(44.822994, 568.854);
        ((GeneralPath)shape).lineTo(44.810993, 568.51);
        ((GeneralPath)shape).lineTo(44.798992, 568.166);
        ((GeneralPath)shape).lineTo(44.790993, 567.478);
        ((GeneralPath)shape).lineTo(44.790993, 564.408);
        ((GeneralPath)shape).lineTo(45.801, 564.408);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_180;
        g.setTransform(defaultTransform__0_180);
        g.setClip(clip__0_180);
        float alpha__0_181 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_181 = g.getClip();
        AffineTransform defaultTransform__0_181 = g.getTransform();
        
//      _0_181 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(48.235, 568.068);
        ((GeneralPath)shape).curveTo(47.732002, 568.068, 47.479, 568.242, 47.479, 568.591);
        ((GeneralPath)shape).curveTo(47.479, 568.833, 47.53, 568.991, 47.635, 569.068);
        ((GeneralPath)shape).curveTo(47.738, 569.14197, 47.956997, 569.18097, 48.289997, 569.18097);
        ((GeneralPath)shape).curveTo(48.831997, 569.18097, 49.104996, 568.99695, 49.104996, 568.62994);
        ((GeneralPath)shape).curveTo(49.104, 568.256, 48.815, 568.068, 48.235, 568.068);
        ((GeneralPath)shape).moveTo(47.638, 567.088);
        ((GeneralPath)shape).lineTo(46.726, 567.088);
        ((GeneralPath)shape).curveTo(46.726, 566.641, 46.829002, 566.34204, 47.038002, 566.19);
        ((GeneralPath)shape).curveTo(47.245003, 566.04, 47.658, 565.963, 48.274002, 565.963);
        ((GeneralPath)shape).curveTo(48.945004, 565.963, 49.397003, 566.055, 49.635002, 566.24);
        ((GeneralPath)shape).curveTo(49.871002, 566.424, 49.989002, 566.777, 49.989002, 567.299);
        ((GeneralPath)shape).lineTo(49.989002, 569.74);
        ((GeneralPath)shape).lineTo(49.096, 569.74);
        ((GeneralPath)shape).lineTo(49.139, 569.22797);
        ((GeneralPath)shape).lineTo(49.116, 569.22394);
        ((GeneralPath)shape).curveTo(48.945, 569.5949, 48.549, 569.78296, 47.927002, 569.78296);
        ((GeneralPath)shape).curveTo(47.027, 569.78296, 46.574, 569.39996, 46.574, 568.631);
        ((GeneralPath)shape).curveTo(46.574, 567.85596, 47.034, 567.467, 47.958, 567.467);
        ((GeneralPath)shape).curveTo(48.574, 567.467, 48.948, 567.608, 49.081, 567.893);
        ((GeneralPath)shape).lineTo(49.097, 567.893);
        ((GeneralPath)shape).lineTo(49.097, 567.288);
        ((GeneralPath)shape).curveTo(49.097, 566.997, 49.046, 566.804, 48.945, 566.708);
        ((GeneralPath)shape).curveTo(48.843, 566.614, 48.637, 566.565, 48.321, 566.565);
        ((GeneralPath)shape).curveTo(47.866, 566.564, 47.638, 566.738, 47.638, 567.088);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_181;
        g.setTransform(defaultTransform__0_181);
        g.setClip(clip__0_181);
        float alpha__0_182 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_182 = g.getClip();
        AffineTransform defaultTransform__0_182 = g.getTransform();
        
//      _0_182 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(50.878, 566.006);
        ((GeneralPath)shape).lineTo(51.771, 566.006);
        ((GeneralPath)shape).lineTo(51.748, 566.57996);
        ((GeneralPath)shape).lineTo(51.768, 566.584);
        ((GeneralPath)shape).curveTo(51.949, 566.17, 52.329002, 565.963, 52.906002, 565.963);
        ((GeneralPath)shape).curveTo(53.578003, 565.963, 53.953003, 566.193, 54.029003, 566.654);
        ((GeneralPath)shape).lineTo(54.045002, 566.654);
        ((GeneralPath)shape).curveTo(54.219, 566.193, 54.603, 565.963, 55.195004, 565.963);
        ((GeneralPath)shape).curveTo(56.057003, 565.963, 56.489002, 566.39703, 56.489002, 567.268);
        ((GeneralPath)shape).lineTo(56.489002, 569.741);
        ((GeneralPath)shape).lineTo(55.596, 569.741);
        ((GeneralPath)shape).lineTo(55.596, 567.46405);
        ((GeneralPath)shape).curveTo(55.596, 566.9371, 55.38, 566.671, 54.945, 566.671);
        ((GeneralPath)shape).curveTo(54.403, 566.671, 54.13, 566.966, 54.13, 567.55804);
        ((GeneralPath)shape).lineTo(54.13, 569.74207);
        ((GeneralPath)shape).lineTo(53.237, 569.74207);
        ((GeneralPath)shape).lineTo(53.237, 567.4291);
        ((GeneralPath)shape).curveTo(53.237, 567.12006, 53.196, 566.9151, 53.114, 566.81805);
        ((GeneralPath)shape).curveTo(53.031998, 566.721, 52.858997, 566.67206, 52.593, 566.67206);
        ((GeneralPath)shape).curveTo(52.045, 566.67206, 51.769997, 566.9731, 51.769997, 567.57806);
        ((GeneralPath)shape).lineTo(51.769997, 569.74207);
        ((GeneralPath)shape).lineTo(50.876995, 569.74207);
        ((GeneralPath)shape).lineTo(50.876995, 566.006);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_182;
        g.setTransform(defaultTransform__0_182);
        g.setClip(clip__0_182);
        float alpha__0_183 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_183 = g.getClip();
        AffineTransform defaultTransform__0_183 = g.getTransform();
        
//      _0_183 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(59.81, 567.486);
        ((GeneralPath)shape).lineTo(59.806, 567.338);
        ((GeneralPath)shape).curveTo(59.806, 567.041, 59.755, 566.85004, 59.652, 566.76);
        ((GeneralPath)shape).curveTo(59.551, 566.672, 59.327, 566.627, 58.984, 566.627);
        ((GeneralPath)shape).curveTo(58.652, 566.627, 58.436, 566.68, 58.335003, 566.787);
        ((GeneralPath)shape).curveTo(58.235004, 566.89197, 58.185, 567.127, 58.185, 567.48596);
        ((GeneralPath)shape).lineTo(59.81, 567.48596);
        ((GeneralPath)shape).moveTo(59.802, 568.545);
        ((GeneralPath)shape).lineTo(60.698997, 568.545);
        ((GeneralPath)shape).lineTo(60.698997, 568.69);
        ((GeneralPath)shape).curveTo(60.698997, 569.419, 60.152996, 569.784, 59.061996, 569.784);
        ((GeneralPath)shape).curveTo(58.320995, 569.784, 57.836998, 569.659, 57.607998, 569.40497);
        ((GeneralPath)shape).curveTo(57.379997, 569.15295, 57.265, 568.618, 57.265, 567.8);
        ((GeneralPath)shape).curveTo(57.265, 567.073, 57.384, 566.58496, 57.624, 566.33496);
        ((GeneralPath)shape).curveTo(57.862, 566.08496, 58.33, 565.95996, 59.023, 565.95996);
        ((GeneralPath)shape).curveTo(59.688, 565.95996, 60.134, 566.08093, 60.36, 566.32495);
        ((GeneralPath)shape).curveTo(60.586002, 566.56696, 60.699, 567.04596, 60.699, 567.7609);
        ((GeneralPath)shape).lineTo(60.699, 568.03394);
        ((GeneralPath)shape).lineTo(58.177002, 568.03394);
        ((GeneralPath)shape).curveTo(58.173, 568.1159, 58.169003, 568.17096, 58.169003, 568.19794);
        ((GeneralPath)shape).curveTo(58.169003, 568.56494, 58.226, 568.80896, 58.339, 568.93195);
        ((GeneralPath)shape).curveTo(58.452, 569.0529, 58.676003, 569.11597, 59.015, 569.11597);
        ((GeneralPath)shape).curveTo(59.343, 569.11597, 59.555, 569.081, 59.654, 569.009);
        ((GeneralPath)shape).curveTo(59.751, 568.938, 59.802, 568.783, 59.802, 568.545);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_183;
        g.setTransform(defaultTransform__0_183);
        g.setClip(clip__0_183);
        float alpha__0_184 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_184 = g.getClip();
        AffineTransform defaultTransform__0_184 = g.getTransform();
        
//      _0_184 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(62.294, 566.006);
        ((GeneralPath)shape).lineTo(62.294, 566.967);
        ((GeneralPath)shape).lineTo(61.400997, 566.967);
        ((GeneralPath)shape).lineTo(61.400997, 566.006);
        ((GeneralPath)shape).lineTo(62.294, 566.006);
        ((GeneralPath)shape).moveTo(62.294, 568.779);
        ((GeneralPath)shape).lineTo(62.294, 569.74);
        ((GeneralPath)shape).lineTo(61.400997, 569.74);
        ((GeneralPath)shape).lineTo(61.400997, 568.779);
        ((GeneralPath)shape).lineTo(62.294, 568.779);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_184;
        g.setTransform(defaultTransform__0_184);
        g.setClip(clip__0_184);
        float alpha__0_185 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_185 = g.getClip();
        AffineTransform defaultTransform__0_185 = g.getTransform();
        
//      _0_185 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        stroke = new BasicStroke(1.0f,0,0,4.0f,null,0.0f);
        shape = new Line2D.Float(65.410004f,571.158020f,145.608002f,571.158020f);
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_185;
        g.setTransform(defaultTransform__0_185);
        g.setClip(clip__0_185);
        float alpha__0_186 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_186 = g.getClip();
        AffineTransform defaultTransform__0_186 = g.getTransform();
        
//      _0_186 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(312.641, 581.326);
        ((GeneralPath)shape).lineTo(312.495, 580.84);
        ((GeneralPath)shape).lineTo(311.846, 581.068);
        ((GeneralPath)shape).lineTo(311.846, 580.384);
        ((GeneralPath)shape).lineTo(311.377, 580.384);
        ((GeneralPath)shape).lineTo(311.377, 581.068);
        ((GeneralPath)shape).lineTo(310.747, 580.84);
        ((GeneralPath)shape).lineTo(310.588, 581.286);
        ((GeneralPath)shape).lineTo(311.223, 581.52);
        ((GeneralPath)shape).lineTo(310.833, 582.066);
        ((GeneralPath)shape).lineTo(311.213, 582.346);
        ((GeneralPath)shape).lineTo(311.635, 581.782);
        ((GeneralPath)shape).lineTo(312.025, 582.351);
        ((GeneralPath)shape).lineTo(312.391, 582.066);
        ((GeneralPath)shape).lineTo(312.016, 581.524);
        ((GeneralPath)shape).lineTo(312.641, 581.326);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_186;
        g.setTransform(defaultTransform__0_186);
        g.setClip(clip__0_186);
        float alpha__0_187 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_187 = g.getClip();
        AffineTransform defaultTransform__0_187 = g.getTransform();
        
//      _0_187 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(313.728, 584.219);
        ((GeneralPath)shape).lineTo(314.744, 584.219);
        ((GeneralPath)shape).lineTo(314.744, 583.201);
        ((GeneralPath)shape).lineTo(315.50497, 583.201);
        ((GeneralPath)shape).curveTo(315.79898, 583.201, 315.916, 583.396, 315.916, 583.68097);
        ((GeneralPath)shape).lineTo(315.916, 584.21796);
        ((GeneralPath)shape).lineTo(316.93198, 584.21796);
        ((GeneralPath)shape).lineTo(316.93198, 583.446);
        ((GeneralPath)shape).curveTo(316.93198, 583.009, 316.66098, 582.779, 316.24298, 582.779);
        ((GeneralPath)shape).lineTo(316.24298, 582.74);
        ((GeneralPath)shape).curveTo(316.93198, 582.569, 316.93198, 582.191, 316.93198, 581.544);
        ((GeneralPath)shape).curveTo(316.93198, 580.575, 316.50897, 580.38403, 315.649, 580.38403);
        ((GeneralPath)shape).lineTo(313.72897, 580.38403);
        ((GeneralPath)shape).lineTo(313.72897, 584.219);
        ((GeneralPath)shape).moveTo(314.743, 582.341);
        ((GeneralPath)shape).lineTo(314.743, 581.266);
        ((GeneralPath)shape).lineTo(315.506, 581.266);
        ((GeneralPath)shape).curveTo(315.83102, 581.266, 315.915, 581.36597, 315.915, 581.721);
        ((GeneralPath)shape).curveTo(315.915, 582.17, 315.93002, 582.341, 315.506, 582.341);
        ((GeneralPath)shape).lineTo(314.743, 582.341);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_187;
        g.setTransform(defaultTransform__0_187);
        g.setClip(clip__0_187);
        float alpha__0_188 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_188 = g.getClip();
        AffineTransform defaultTransform__0_188 = g.getTransform();
        
//      _0_188 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(318.623, 582.227);
        ((GeneralPath)shape).curveTo(319.03598, 582.227, 319.07797, 582.342, 319.07797, 582.914);
        ((GeneralPath)shape).curveTo(319.07797, 583.483, 319.03598, 583.594, 318.623, 583.594);
        ((GeneralPath)shape).curveTo(318.192, 583.594, 318.141, 583.484, 318.141, 582.914);
        ((GeneralPath)shape).curveTo(318.141, 582.342, 318.192, 582.227, 318.623, 582.227);
        ((GeneralPath)shape).moveTo(318.607, 581.558);
        ((GeneralPath)shape).curveTo(317.498, 581.558, 317.281, 581.826, 317.281, 582.891);
        ((GeneralPath)shape).curveTo(317.281, 583.932, 317.519, 584.219, 318.607, 584.219);
        ((GeneralPath)shape).curveTo(319.70898, 584.219, 319.93698, 583.961, 319.93698, 582.891);
        ((GeneralPath)shape).curveTo(319.938, 581.79, 319.721, 581.558, 318.607, 581.558);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_188;
        g.setTransform(defaultTransform__0_188);
        g.setClip(clip__0_188);
        float alpha__0_189 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_189 = g.getClip();
        AffineTransform defaultTransform__0_189 = g.getTransform();
        
//      _0_189 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new Rectangle2D.Double(320.4020080566406, 580.3839721679688, 0.859000027179718, 3.8350000381469727);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_189;
        g.setTransform(defaultTransform__0_189);
        g.setClip(clip__0_189);
        float alpha__0_190 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_190 = g.getClip();
        AffineTransform defaultTransform__0_190 = g.getTransform();
        
//      _0_190 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new Rectangle2D.Double(321.8479919433594, 580.3839721679688, 0.859000027179718, 3.8350000381469727);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_190;
        g.setTransform(defaultTransform__0_190);
        g.setClip(clip__0_190);
        float alpha__0_191 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_191 = g.getClip();
        AffineTransform defaultTransform__0_191 = g.getTransform();
        
//      _0_191 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(327.593, 580.384);
        ((GeneralPath)shape).lineTo(326.407, 580.384);
        ((GeneralPath)shape).lineTo(325.043, 581.532);
        ((GeneralPath)shape).lineTo(325.606, 582.188);
        ((GeneralPath)shape).lineTo(326.577, 581.281);
        ((GeneralPath)shape).lineTo(326.577, 584.219);
        ((GeneralPath)shape).lineTo(327.593, 584.219);
        ((GeneralPath)shape).lineTo(327.593, 580.384);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_191;
        g.setTransform(defaultTransform__0_191);
        g.setClip(clip__0_191);
        float alpha__0_192 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_192 = g.getClip();
        AffineTransform defaultTransform__0_192 = g.getTransform();
        
//      _0_192 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(329.63, 581.266);
        ((GeneralPath)shape).lineTo(330.522, 581.266);
        ((GeneralPath)shape).curveTo(330.879, 581.266, 331.03702, 581.369, 331.03702, 581.869);
        ((GeneralPath)shape).lineTo(331.03702, 582.679);
        ((GeneralPath)shape).curveTo(331.03702, 583.086, 330.89902, 583.338, 330.522, 583.338);
        ((GeneralPath)shape).lineTo(329.63, 583.338);
        ((GeneralPath)shape).lineTo(329.63, 581.266);
        ((GeneralPath)shape).moveTo(328.614, 584.219);
        ((GeneralPath)shape).lineTo(330.66602, 584.219);
        ((GeneralPath)shape).curveTo(331.71902, 584.219, 332.052, 583.723, 332.052, 582.674);
        ((GeneralPath)shape).lineTo(332.052, 581.872);
        ((GeneralPath)shape).curveTo(332.052, 580.782, 331.583, 580.38403, 330.53, 580.38403);
        ((GeneralPath)shape).lineTo(328.615, 580.38403);
        ((GeneralPath)shape).lineTo(328.615, 584.219);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_192;
        g.setTransform(defaultTransform__0_192);
        g.setClip(clip__0_192);
        float alpha__0_193 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_193 = g.getClip();
        AffineTransform defaultTransform__0_193 = g.getTransform();
        
//      _0_193 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(335.536, 581.549);
        ((GeneralPath)shape).lineTo(335.536, 581.39);
        ((GeneralPath)shape).curveTo(335.536, 580.422, 334.85, 580.38403, 334.086, 580.38403);
        ((GeneralPath)shape).curveTo(333.017, 580.38403, 332.567, 580.58, 332.567, 581.747);
        ((GeneralPath)shape).lineTo(332.567, 582.693);
        ((GeneralPath)shape).curveTo(332.567, 583.966, 332.87598, 584.218, 334.10098, 584.218);
        ((GeneralPath)shape).curveTo(335.05698, 584.218, 335.53598, 584.029, 335.53598, 582.961);
        ((GeneralPath)shape).curveTo(335.53598, 581.964, 335.076, 581.861, 334.19397, 581.861);
        ((GeneralPath)shape).curveTo(333.86996, 581.861, 333.64496, 581.861, 333.51398, 582.25604);
        ((GeneralPath)shape).lineTo(333.50397, 582.25604);
        ((GeneralPath)shape).lineTo(333.50397, 581.679);
        ((GeneralPath)shape).curveTo(333.50397, 581.19403, 333.63696, 581.174, 334.08496, 581.174);
        ((GeneralPath)shape).curveTo(334.44995, 581.174, 334.59796, 581.193, 334.59796, 581.54803);
        ((GeneralPath)shape).lineTo(335.536, 581.54803);
        ((GeneralPath)shape).moveTo(333.985, 582.653);
        ((GeneralPath)shape).curveTo(334.431, 582.653, 334.598, 582.653, 334.598, 582.982);
        ((GeneralPath)shape).curveTo(334.598, 583.411, 334.52798, 583.436, 333.988, 583.436);
        ((GeneralPath)shape).curveTo(333.565, 583.436, 333.505, 583.363, 333.505, 582.99896);
        ((GeneralPath)shape).curveTo(333.505, 582.69, 333.615, 582.653, 333.985, 582.653);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_193;
        g.setTransform(defaultTransform__0_193);
        g.setClip(clip__0_193);
        float alpha__0_194 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_194 = g.getClip();
        AffineTransform defaultTransform__0_194 = g.getTransform();
        
//      _0_194 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(337.104, 581.558);
        ((GeneralPath)shape).lineTo(336.323, 581.558);
        ((GeneralPath)shape).lineTo(336.323, 582.44);
        ((GeneralPath)shape).lineTo(337.104, 582.44);
        ((GeneralPath)shape).lineTo(337.104, 581.558);
        ((GeneralPath)shape).moveTo(337.104, 583.337);
        ((GeneralPath)shape).lineTo(336.323, 583.337);
        ((GeneralPath)shape).lineTo(336.323, 584.219);
        ((GeneralPath)shape).lineTo(337.104, 584.219);
        ((GeneralPath)shape).lineTo(337.104, 583.337);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_194;
        g.setTransform(defaultTransform__0_194);
        g.setClip(clip__0_194);
        float alpha__0_195 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_195 = g.getClip();
        AffineTransform defaultTransform__0_195 = g.getTransform();
        
//      _0_195 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(315.227, 586.755);
        ((GeneralPath)shape).lineTo(314.041, 586.755);
        ((GeneralPath)shape).lineTo(312.677, 587.903);
        ((GeneralPath)shape).lineTo(313.24, 588.561);
        ((GeneralPath)shape).lineTo(314.211, 587.652);
        ((GeneralPath)shape).lineTo(314.211, 590.591);
        ((GeneralPath)shape).lineTo(315.227, 590.591);
        ((GeneralPath)shape).lineTo(315.227, 586.755);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_195;
        g.setTransform(defaultTransform__0_195);
        g.setClip(clip__0_195);
        float alpha__0_196 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_196 = g.getClip();
        AffineTransform defaultTransform__0_196 = g.getTransform();
        
//      _0_196 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new Rectangle2D.Double(316.0140075683594, 588.7119750976562, 2.5, 0.8610000014305115);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_196;
        g.setTransform(defaultTransform__0_196);
        g.setClip(clip__0_196);
        float alpha__0_197 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_197 = g.getClip();
        AffineTransform defaultTransform__0_197 = g.getTransform();
        
//      _0_197 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(321.795, 589.8);
        ((GeneralPath)shape).lineTo(319.764, 589.8);
        ((GeneralPath)shape).curveTo(319.764, 589.287, 319.982, 589.394, 320.751, 589.186);
        ((GeneralPath)shape).curveTo(321.547, 588.96796, 321.795, 588.805, 321.795, 587.954);
        ((GeneralPath)shape).curveTo(321.795, 586.884, 321.19702, 586.756, 320.31403, 586.756);
        ((GeneralPath)shape).curveTo(319.35403, 586.756, 318.82703, 586.954, 318.82703, 587.98895);
        ((GeneralPath)shape).lineTo(318.82703, 588.17596);
        ((GeneralPath)shape).lineTo(319.765, 588.17596);
        ((GeneralPath)shape).lineTo(319.765, 587.98895);
        ((GeneralPath)shape).curveTo(319.765, 587.58295, 319.838, 587.548, 320.312, 587.548);
        ((GeneralPath)shape).curveTo(320.728, 587.548, 320.859, 587.59296, 320.859, 587.974);
        ((GeneralPath)shape).curveTo(320.859, 588.364, 320.814, 588.353, 320.16602, 588.505);
        ((GeneralPath)shape).curveTo(319.01703, 588.774, 318.828, 588.92, 318.828, 589.802);
        ((GeneralPath)shape).lineTo(318.828, 590.592);
        ((GeneralPath)shape).lineTo(321.797, 590.592);
        ((GeneralPath)shape).lineTo(321.797, 589.8);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_197;
        g.setTransform(defaultTransform__0_197);
        g.setClip(clip__0_197);
        float alpha__0_198 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_198 = g.getClip();
        AffineTransform defaultTransform__0_198 = g.getTransform();
        
//      _0_198 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(322.583, 589.739);
        ((GeneralPath)shape).lineTo(322.583, 590.591);
        ((GeneralPath)shape).lineTo(322.88202, 590.591);
        ((GeneralPath)shape).curveTo(322.88202, 590.828, 322.80902, 590.904, 322.532, 590.904);
        ((GeneralPath)shape).lineTo(322.532, 591.295);
        ((GeneralPath)shape).curveTo(323.183, 591.295, 323.364, 591.099, 323.364, 590.591);
        ((GeneralPath)shape).lineTo(323.364, 589.739);
        ((GeneralPath)shape).lineTo(322.583, 589.739);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_198;
        g.setTransform(defaultTransform__0_198);
        g.setClip(clip__0_198);
        float alpha__0_199 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_199 = g.getClip();
        AffineTransform defaultTransform__0_199 = g.getTransform();
        
//      _0_199 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(327.667, 587.637);
        ((GeneralPath)shape).lineTo(328.677, 587.637);
        ((GeneralPath)shape).lineTo(328.677, 586.755);
        ((GeneralPath)shape).lineTo(325.691, 586.755);
        ((GeneralPath)shape).lineTo(325.691, 587.637);
        ((GeneralPath)shape).lineTo(326.651, 587.637);
        ((GeneralPath)shape).lineTo(326.651, 590.591);
        ((GeneralPath)shape).lineTo(327.667, 590.591);
        ((GeneralPath)shape).lineTo(327.667, 587.637);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_199;
        g.setTransform(defaultTransform__0_199);
        g.setClip(clip__0_199);
        float alpha__0_200 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_200 = g.getClip();
        AffineTransform defaultTransform__0_200 = g.getTransform();
        
//      _0_200 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(329.912, 588.599);
        ((GeneralPath)shape).curveTo(330.32498, 588.599, 330.36697, 588.713, 330.36697, 589.285);
        ((GeneralPath)shape).curveTo(330.36697, 589.855, 330.32498, 589.966, 329.912, 589.966);
        ((GeneralPath)shape).curveTo(329.47998, 589.966, 329.43, 589.856, 329.43, 589.285);
        ((GeneralPath)shape).curveTo(329.43, 588.713, 329.48, 588.599, 329.912, 588.599);
        ((GeneralPath)shape).moveTo(329.896, 587.93);
        ((GeneralPath)shape).curveTo(328.788, 587.93, 328.57098, 588.198, 328.57098, 589.263);
        ((GeneralPath)shape).curveTo(328.57098, 590.304, 328.809, 590.591, 329.896, 590.591);
        ((GeneralPath)shape).curveTo(330.999, 590.591, 331.227, 590.333, 331.227, 589.263);
        ((GeneralPath)shape).curveTo(331.227, 588.162, 331.009, 587.93, 329.896, 587.93);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_200;
        g.setTransform(defaultTransform__0_200);
        g.setClip(clip__0_200);
        float alpha__0_201 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_201 = g.getClip();
        AffineTransform defaultTransform__0_201 = g.getTransform();
        
//      _0_201 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(333.02, 589.121);
        ((GeneralPath)shape).lineTo(333.801, 589.121);
        ((GeneralPath)shape).lineTo(333.801, 588.91296);
        ((GeneralPath)shape).curveTo(333.801, 588.42596, 333.751, 587.93, 333.147, 587.93);
        ((GeneralPath)shape).curveTo(332.986, 587.93, 332.85, 587.964, 332.74, 588.037);
        ((GeneralPath)shape).curveTo(332.62997, 588.11, 332.543, 588.22797, 332.483, 588.399);
        ((GeneralPath)shape).lineTo(332.473, 588.399);
        ((GeneralPath)shape).lineTo(332.473, 587.929);
        ((GeneralPath)shape).lineTo(331.61398, 587.929);
        ((GeneralPath)shape).lineTo(331.61398, 590.59);
        ((GeneralPath)shape).lineTo(332.473, 590.59);
        ((GeneralPath)shape).lineTo(332.473, 589.01904);
        ((GeneralPath)shape).curveTo(332.473, 588.788, 332.53198, 588.567, 332.788, 588.567);
        ((GeneralPath)shape).curveTo(333.01898, 588.567, 333.01898, 588.788, 333.01898, 588.986);
        ((GeneralPath)shape).lineTo(333.01898, 589.121);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_201;
        g.setTransform(defaultTransform__0_201);
        g.setClip(clip__0_201);
        float alpha__0_202 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_202 = g.getClip();
        AffineTransform defaultTransform__0_202 = g.getTransform();
        
//      _0_202 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(336.49, 588.716);
        ((GeneralPath)shape).lineTo(336.49, 588.589);
        ((GeneralPath)shape).curveTo(336.49, 587.93, 335.809, 587.93, 335.328, 587.93);
        ((GeneralPath)shape).curveTo(334.59702, 587.93, 334.04, 587.93, 334.04, 588.77997);
        ((GeneralPath)shape).curveTo(334.04, 589.318, 334.234, 589.56396, 335.31702, 589.56396);
        ((GeneralPath)shape).curveTo(335.61102, 589.56396, 335.75803, 589.56396, 335.75803, 589.818);
        ((GeneralPath)shape).curveTo(335.75803, 590.019, 335.69904, 590.044, 335.31702, 590.044);
        ((GeneralPath)shape).curveTo(335.11102, 590.044, 334.89902, 590.044, 334.89902, 589.755);
        ((GeneralPath)shape).lineTo(334.04, 589.755);
        ((GeneralPath)shape).curveTo(334.04, 590.592, 334.663, 590.592, 335.31702, 590.592);
        ((GeneralPath)shape).curveTo(336.015, 590.592, 336.618, 590.48, 336.618, 589.722);
        ((GeneralPath)shape).curveTo(336.618, 589.001, 336.21802, 588.95197, 335.31702, 588.932);
        ((GeneralPath)shape).curveTo(334.90903, 588.922, 334.89902, 588.83203, 334.89902, 588.703);
        ((GeneralPath)shape).curveTo(334.89902, 588.549, 334.89902, 588.494, 335.328, 588.494);
        ((GeneralPath)shape).curveTo(335.395, 588.494, 335.471, 588.494, 335.533, 588.51904);
        ((GeneralPath)shape).curveTo(335.595, 588.5491, 335.646, 588.606, 335.66098, 588.71704);
        ((GeneralPath)shape).lineTo(336.49, 588.71704);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_202;
        g.setTransform(defaultTransform__0_202);
        g.setClip(clip__0_202);
        float alpha__0_203 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_203 = g.getClip();
        AffineTransform defaultTransform__0_203 = g.getTransform();
        
//      _0_203 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(338.271, 588.599);
        ((GeneralPath)shape).curveTo(338.685, 588.599, 338.727, 588.713, 338.727, 589.285);
        ((GeneralPath)shape).curveTo(338.727, 589.855, 338.685, 589.966, 338.271, 589.966);
        ((GeneralPath)shape).curveTo(337.84, 589.966, 337.79, 589.856, 337.79, 589.285);
        ((GeneralPath)shape).curveTo(337.79, 588.713, 337.841, 588.599, 338.271, 588.599);
        ((GeneralPath)shape).moveTo(338.256, 587.93);
        ((GeneralPath)shape).curveTo(337.148, 587.93, 336.931, 588.198, 336.931, 589.263);
        ((GeneralPath)shape).curveTo(336.931, 590.304, 337.168, 590.591, 338.256, 590.591);
        ((GeneralPath)shape).curveTo(339.359, 590.591, 339.587, 590.333, 339.587, 589.263);
        ((GeneralPath)shape).curveTo(339.587, 588.162, 339.369, 587.93, 338.256, 587.93);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_203;
        g.setTransform(defaultTransform__0_203);
        g.setClip(clip__0_203);
        float alpha__0_204 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_204 = g.getClip();
        AffineTransform defaultTransform__0_204 = g.getTransform();
        
//      _0_204 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(345.512, 589.719);
        ((GeneralPath)shape).lineTo(345.478, 589.719);
        ((GeneralPath)shape).lineTo(344.805, 586.755);
        ((GeneralPath)shape).lineTo(343.762, 586.755);
        ((GeneralPath)shape).lineTo(343.056, 589.719);
        ((GeneralPath)shape).lineTo(343.02, 589.719);
        ((GeneralPath)shape).lineTo(342.479, 586.755);
        ((GeneralPath)shape).lineTo(341.461, 586.755);
        ((GeneralPath)shape).lineTo(342.272, 590.591);
        ((GeneralPath)shape).lineTo(343.707, 590.591);
        ((GeneralPath)shape).lineTo(344.254, 588.191);
        ((GeneralPath)shape).lineTo(344.293, 588.191);
        ((GeneralPath)shape).lineTo(344.805, 590.591);
        ((GeneralPath)shape).lineTo(346.254, 590.591);
        ((GeneralPath)shape).lineTo(347.086, 586.755);
        ((GeneralPath)shape).lineTo(346.079, 586.755);
        ((GeneralPath)shape).lineTo(345.512, 589.719);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_204;
        g.setTransform(defaultTransform__0_204);
        g.setClip(clip__0_204);
        float alpha__0_205 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_205 = g.getClip();
        AffineTransform defaultTransform__0_205 = g.getTransform();
        
//      _0_205 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(348.839, 589.651);
        ((GeneralPath)shape).curveTo(348.839, 589.933, 348.66898, 589.964, 348.404, 589.964);
        ((GeneralPath)shape).curveTo(348.04898, 589.964, 347.979, 589.839, 347.979, 589.416);
        ((GeneralPath)shape).lineTo(349.698, 589.416);
        ((GeneralPath)shape).lineTo(349.698, 589.271);
        ((GeneralPath)shape).curveTo(349.698, 588.202, 349.401, 587.928, 348.412, 587.928);
        ((GeneralPath)shape).curveTo(347.34998, 587.928, 347.12, 588.259, 347.12, 589.261);
        ((GeneralPath)shape).curveTo(347.12, 590.232, 347.372, 590.589, 348.412, 590.589);
        ((GeneralPath)shape).curveTo(348.79398, 590.589, 349.115, 590.55896, 349.34198, 590.428);
        ((GeneralPath)shape).curveTo(349.568, 590.29297, 349.69797, 590.06, 349.69797, 589.64996);
        ((GeneralPath)shape).lineTo(348.839, 589.64996);
        ((GeneralPath)shape).moveTo(347.979, 588.947);
        ((GeneralPath)shape).curveTo(347.979, 588.607, 348.077, 588.53705, 348.39, 588.53705);
        ((GeneralPath)shape).curveTo(348.69, 588.53705, 348.838, 588.56104, 348.838, 588.947);
        ((GeneralPath)shape).lineTo(347.979, 588.947);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_205;
        g.setTransform(defaultTransform__0_205);
        g.setClip(clip__0_205);
        float alpha__0_206 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_206 = g.getClip();
        AffineTransform defaultTransform__0_206 = g.getTransform();
        
//      _0_206 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(351.709, 590.591);
        ((GeneralPath)shape).lineTo(352.56802, 590.591);
        ((GeneralPath)shape).lineTo(352.56802, 588.947);
        ((GeneralPath)shape).curveTo(352.56802, 588.039, 352.20502, 587.929, 351.338, 587.929);
        ((GeneralPath)shape).curveTo(350.76202, 587.929, 350.147, 587.929, 350.147, 588.75);
        ((GeneralPath)shape).lineTo(351.006, 588.75);
        ((GeneralPath)shape).curveTo(351.006, 588.512, 351.135, 588.461, 351.338, 588.461);
        ((GeneralPath)shape).curveTo(351.694, 588.461, 351.709, 588.61, 351.709, 588.851);
        ((GeneralPath)shape).lineTo(351.709, 589.254);
        ((GeneralPath)shape).lineTo(351.67203, 589.254);
        ((GeneralPath)shape).curveTo(351.58102, 588.94604, 351.22504, 588.94604, 350.99503, 588.94604);
        ((GeneralPath)shape).curveTo(350.39102, 588.94604, 350.06802, 589.14105, 350.06802, 589.77203);
        ((GeneralPath)shape).curveTo(350.06802, 590.44403, 350.40503, 590.58905, 350.98203, 590.58905);
        ((GeneralPath)shape).curveTo(351.27103, 590.58905, 351.64603, 590.53, 351.709, 590.18207);
        ((GeneralPath)shape).lineTo(351.709, 590.591);
        ((GeneralPath)shape).moveTo(351.274, 589.495);
        ((GeneralPath)shape).curveTo(351.533, 589.495, 351.70898, 589.515, 351.70898, 589.729);
        ((GeneralPath)shape).curveTo(351.70898, 589.999, 351.60797, 590.042, 351.274, 590.042);
        ((GeneralPath)shape).curveTo(351.155, 590.042, 350.927, 590.042, 350.927, 589.763);
        ((GeneralPath)shape).curveTo(350.928, 589.529, 351.08, 589.495, 351.274, 589.495);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_206;
        g.setTransform(defaultTransform__0_206);
        g.setClip(clip__0_206);
        float alpha__0_207 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_207 = g.getClip();
        AffineTransform defaultTransform__0_207 = g.getTransform();
        
//      _0_207 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(354.459, 588.567);
        ((GeneralPath)shape).curveTo(354.89102, 588.567, 354.91, 588.74603, 354.91, 589.268);
        ((GeneralPath)shape).curveTo(354.91, 589.877, 354.797, 589.964, 354.459, 589.964);
        ((GeneralPath)shape).curveTo(354.079, 589.964, 354.05103, 589.718, 354.05103, 589.268);
        ((GeneralPath)shape).curveTo(354.051, 588.701, 354.113, 588.567, 354.459, 588.567);
        ((GeneralPath)shape).moveTo(353.191, 591.765);
        ((GeneralPath)shape).lineTo(354.05002, 591.765);
        ((GeneralPath)shape).lineTo(354.05002, 590.2);
        ((GeneralPath)shape).lineTo(354.088, 590.2);
        ((GeneralPath)shape).curveTo(354.225, 590.532, 354.492, 590.59204, 354.80002, 590.59204);
        ((GeneralPath)shape).curveTo(355.50003, 590.59204, 355.769, 590.27606, 355.769, 589.48206);
        ((GeneralPath)shape).lineTo(355.769, 588.8871);
        ((GeneralPath)shape).curveTo(355.769, 588.1991, 355.419, 587.9311, 354.80002, 587.9311);
        ((GeneralPath)shape).curveTo(354.43503, 587.9311, 354.16702, 588.0161, 354.07, 588.3511);
        ((GeneralPath)shape).lineTo(354.05002, 588.3511);
        ((GeneralPath)shape).lineTo(354.05002, 587.9311);
        ((GeneralPath)shape).lineTo(353.191, 587.9311);
        ((GeneralPath)shape).lineTo(353.191, 591.765);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_207;
        g.setTransform(defaultTransform__0_207);
        g.setClip(clip__0_207);
        float alpha__0_208 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_208 = g.getClip();
        AffineTransform defaultTransform__0_208 = g.getTransform();
        
//      _0_208 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(357.467, 588.599);
        ((GeneralPath)shape).curveTo(357.881, 588.599, 357.923, 588.713, 357.923, 589.285);
        ((GeneralPath)shape).curveTo(357.923, 589.855, 357.881, 589.966, 357.467, 589.966);
        ((GeneralPath)shape).curveTo(357.036, 589.966, 356.98602, 589.856, 356.98602, 589.285);
        ((GeneralPath)shape).curveTo(356.985, 588.713, 357.036, 588.599, 357.467, 588.599);
        ((GeneralPath)shape).moveTo(357.451, 587.93);
        ((GeneralPath)shape).curveTo(356.343, 587.93, 356.12598, 588.198, 356.12598, 589.263);
        ((GeneralPath)shape).curveTo(356.12598, 590.304, 356.36298, 590.591, 357.451, 590.591);
        ((GeneralPath)shape).curveTo(358.554, 590.591, 358.78198, 590.333, 358.78198, 589.263);
        ((GeneralPath)shape).curveTo(358.782, 588.162, 358.564, 587.93, 357.451, 587.93);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_208;
        g.setTransform(defaultTransform__0_208);
        g.setClip(clip__0_208);
        float alpha__0_209 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_209 = g.getClip();
        AffineTransform defaultTransform__0_209 = g.getTransform();
        
//      _0_209 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(359.247, 587.93);
        ((GeneralPath)shape).lineTo(359.247, 590.591);
        ((GeneralPath)shape).lineTo(360.10602, 590.591);
        ((GeneralPath)shape).lineTo(360.10602, 589.126);
        ((GeneralPath)shape).curveTo(360.10602, 588.802, 360.169, 588.56696, 360.545, 588.56696);
        ((GeneralPath)shape).curveTo(360.81802, 588.56696, 360.88702, 588.69495, 360.88702, 588.95294);
        ((GeneralPath)shape).lineTo(360.88702, 590.59094);
        ((GeneralPath)shape).lineTo(361.74603, 590.59094);
        ((GeneralPath)shape).lineTo(361.74603, 588.77094);
        ((GeneralPath)shape).curveTo(361.74603, 588.20496, 361.48404, 587.92993, 360.91302, 587.92993);
        ((GeneralPath)shape).curveTo(360.71603, 587.92993, 360.55304, 587.95294, 360.42502, 588.02795);
        ((GeneralPath)shape).curveTo(360.29703, 588.09595, 360.20303, 588.217, 360.14, 588.4119);
        ((GeneralPath)shape).lineTo(360.10602, 588.4119);
        ((GeneralPath)shape).lineTo(360.10602, 587.9309);
        ((GeneralPath)shape).lineTo(359.247, 587.9309);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_209;
        g.setTransform(defaultTransform__0_209);
        g.setClip(clip__0_209);
        float alpha__0_210 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_210 = g.getClip();
        AffineTransform defaultTransform__0_210 = g.getTransform();
        
//      _0_210 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(366.1, 589.26);
        ((GeneralPath)shape).lineTo(365.178, 589.26);
        ((GeneralPath)shape).lineTo(365.629, 587.516);
        ((GeneralPath)shape).lineTo(365.63998, 587.516);
        ((GeneralPath)shape).lineTo(366.1, 589.26);
        ((GeneralPath)shape).moveTo(366.298, 589.965);
        ((GeneralPath)shape).lineTo(366.49, 590.591);
        ((GeneralPath)shape).lineTo(367.50098, 590.591);
        ((GeneralPath)shape).lineTo(366.37198, 586.755);
        ((GeneralPath)shape).lineTo(364.865, 586.755);
        ((GeneralPath)shape).lineTo(363.75098, 590.591);
        ((GeneralPath)shape).lineTo(364.78098, 590.591);
        ((GeneralPath)shape).lineTo(364.96497, 589.965);
        ((GeneralPath)shape).lineTo(366.298, 589.965);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_210;
        g.setTransform(defaultTransform__0_210);
        g.setClip(clip__0_210);
        float alpha__0_211 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_211 = g.getClip();
        AffineTransform defaultTransform__0_211 = g.getTransform();
        
//      _0_211 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(370.544, 587.637);
        ((GeneralPath)shape).lineTo(371.436, 587.637);
        ((GeneralPath)shape).curveTo(371.793, 587.637, 371.95102, 587.74005, 371.95102, 588.241);
        ((GeneralPath)shape).lineTo(371.95102, 589.051);
        ((GeneralPath)shape).curveTo(371.95102, 589.458, 371.81302, 589.71, 371.436, 589.71);
        ((GeneralPath)shape).lineTo(370.544, 589.71);
        ((GeneralPath)shape).lineTo(370.544, 587.637);
        ((GeneralPath)shape).moveTo(369.528, 590.591);
        ((GeneralPath)shape).lineTo(371.58002, 590.591);
        ((GeneralPath)shape).curveTo(372.63303, 590.591, 372.966, 590.09503, 372.966, 589.046);
        ((GeneralPath)shape).lineTo(372.966, 588.24304);
        ((GeneralPath)shape).curveTo(372.966, 587.15405, 372.497, 586.75507, 371.444, 586.75507);
        ((GeneralPath)shape).lineTo(369.529, 586.75507);
        ((GeneralPath)shape).lineTo(369.529, 590.591);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_211;
        g.setTransform(defaultTransform__0_211);
        g.setClip(clip__0_211);
        float alpha__0_212 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_212 = g.getClip();
        AffineTransform defaultTransform__0_212 = g.getTransform();
        
//      _0_212 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(375.044, 589.651);
        ((GeneralPath)shape).curveTo(375.044, 589.933, 374.874, 589.964, 374.609, 589.964);
        ((GeneralPath)shape).curveTo(374.254, 589.964, 374.18402, 589.839, 374.18402, 589.416);
        ((GeneralPath)shape).lineTo(375.903, 589.416);
        ((GeneralPath)shape).lineTo(375.903, 589.271);
        ((GeneralPath)shape).curveTo(375.903, 588.202, 375.60602, 587.928, 374.617, 587.928);
        ((GeneralPath)shape).curveTo(373.555, 587.928, 373.325, 588.259, 373.325, 589.261);
        ((GeneralPath)shape).curveTo(373.325, 590.232, 373.57703, 590.589, 374.617, 590.589);
        ((GeneralPath)shape).curveTo(374.999, 590.589, 375.32, 590.55896, 375.547, 590.428);
        ((GeneralPath)shape).curveTo(375.773, 590.29297, 375.90298, 590.06, 375.90298, 589.64996);
        ((GeneralPath)shape).lineTo(375.044, 589.64996);
        ((GeneralPath)shape).moveTo(374.185, 588.947);
        ((GeneralPath)shape).curveTo(374.185, 588.607, 374.283, 588.53705, 374.596, 588.53705);
        ((GeneralPath)shape).curveTo(374.896, 588.53705, 375.044, 588.56104, 375.044, 588.947);
        ((GeneralPath)shape).lineTo(374.185, 588.947);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_212;
        g.setTransform(defaultTransform__0_212);
        g.setClip(clip__0_212);
        float alpha__0_213 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_213 = g.getClip();
        AffineTransform defaultTransform__0_213 = g.getTransform();
        
//      _0_213 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(378.646, 588.716);
        ((GeneralPath)shape).lineTo(378.646, 588.589);
        ((GeneralPath)shape).curveTo(378.646, 587.93, 377.964, 587.93, 377.484, 587.93);
        ((GeneralPath)shape).curveTo(376.75302, 587.93, 376.196, 587.93, 376.196, 588.77997);
        ((GeneralPath)shape).curveTo(376.196, 589.318, 376.39, 589.56396, 377.47302, 589.56396);
        ((GeneralPath)shape).curveTo(377.76703, 589.56396, 377.91403, 589.56396, 377.91403, 589.818);
        ((GeneralPath)shape).curveTo(377.91403, 590.019, 377.85504, 590.044, 377.47302, 590.044);
        ((GeneralPath)shape).curveTo(377.26602, 590.044, 377.05502, 590.044, 377.05502, 589.755);
        ((GeneralPath)shape).lineTo(376.196, 589.755);
        ((GeneralPath)shape).curveTo(376.196, 590.592, 376.819, 590.592, 377.47302, 590.592);
        ((GeneralPath)shape).curveTo(378.17102, 590.592, 378.77402, 590.48, 378.77402, 589.722);
        ((GeneralPath)shape).curveTo(378.77402, 589.001, 378.37402, 588.95197, 377.47302, 588.932);
        ((GeneralPath)shape).curveTo(377.06503, 588.922, 377.05502, 588.83203, 377.05502, 588.703);
        ((GeneralPath)shape).curveTo(377.05502, 588.549, 377.05502, 588.494, 377.484, 588.494);
        ((GeneralPath)shape).curveTo(377.551, 588.494, 377.627, 588.494, 377.689, 588.51904);
        ((GeneralPath)shape).curveTo(377.75, 588.5491, 377.802, 588.606, 377.817, 588.71704);
        ((GeneralPath)shape).lineTo(378.646, 588.71704);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_213;
        g.setTransform(defaultTransform__0_213);
        g.setClip(clip__0_213);
        float alpha__0_214 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_214 = g.getClip();
        AffineTransform defaultTransform__0_214 = g.getTransform();
        
//      _0_214 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(381.154, 587.93);
        ((GeneralPath)shape).lineTo(380.17798, 587.93);
        ((GeneralPath)shape).lineTo(380.17798, 587.367);
        ((GeneralPath)shape).lineTo(379.31897, 587.367);
        ((GeneralPath)shape).lineTo(379.31897, 587.93);
        ((GeneralPath)shape).lineTo(379.0, 587.93);
        ((GeneralPath)shape).lineTo(379.0, 588.568);
        ((GeneralPath)shape).lineTo(379.319, 588.568);
        ((GeneralPath)shape).lineTo(379.319, 589.865);
        ((GeneralPath)shape).curveTo(379.319, 590.522, 379.771, 590.592, 380.315, 590.592);
        ((GeneralPath)shape).curveTo(381.01, 590.592, 381.272, 590.433, 381.272, 589.72);
        ((GeneralPath)shape).lineTo(381.272, 589.48596);
        ((GeneralPath)shape).lineTo(380.569, 589.48596);
        ((GeneralPath)shape).lineTo(380.569, 589.62695);
        ((GeneralPath)shape).curveTo(380.569, 589.80695, 380.569, 589.967, 380.358, 589.967);
        ((GeneralPath)shape).curveTo(380.207, 589.967, 380.178, 589.902, 380.178, 589.746);
        ((GeneralPath)shape).lineTo(380.178, 588.569);
        ((GeneralPath)shape).lineTo(381.15402, 588.569);
        ((GeneralPath)shape).lineTo(381.15402, 587.93);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_214;
        g.setTransform(defaultTransform__0_214);
        g.setClip(clip__0_214);
        float alpha__0_215 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_215 = g.getClip();
        AffineTransform defaultTransform__0_215 = g.getTransform();
        
//      _0_215 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(382.994, 589.121);
        ((GeneralPath)shape).lineTo(383.775, 589.121);
        ((GeneralPath)shape).lineTo(383.775, 588.91296);
        ((GeneralPath)shape).curveTo(383.775, 588.42596, 383.725, 587.93, 383.121, 587.93);
        ((GeneralPath)shape).curveTo(382.96, 587.93, 382.825, 587.964, 382.714, 588.037);
        ((GeneralPath)shape).curveTo(382.604, 588.11, 382.51797, 588.22797, 382.457, 588.399);
        ((GeneralPath)shape).lineTo(382.447, 588.399);
        ((GeneralPath)shape).lineTo(382.447, 587.929);
        ((GeneralPath)shape).lineTo(381.58798, 587.929);
        ((GeneralPath)shape).lineTo(381.58798, 590.59);
        ((GeneralPath)shape).lineTo(382.447, 590.59);
        ((GeneralPath)shape).lineTo(382.447, 589.01904);
        ((GeneralPath)shape).curveTo(382.447, 588.788, 382.50598, 588.567, 382.762, 588.567);
        ((GeneralPath)shape).curveTo(382.99298, 588.567, 382.99298, 588.788, 382.99298, 588.986);
        ((GeneralPath)shape).lineTo(382.99298, 589.121);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_215;
        g.setTransform(defaultTransform__0_215);
        g.setClip(clip__0_215);
        float alpha__0_216 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_216 = g.getClip();
        AffineTransform defaultTransform__0_216 = g.getTransform();
        
//      _0_216 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(385.457, 588.599);
        ((GeneralPath)shape).curveTo(385.871, 588.599, 385.912, 588.713, 385.912, 589.285);
        ((GeneralPath)shape).curveTo(385.912, 589.855, 385.871, 589.966, 385.457, 589.966);
        ((GeneralPath)shape).curveTo(385.026, 589.966, 384.975, 589.856, 384.975, 589.285);
        ((GeneralPath)shape).curveTo(384.975, 588.713, 385.026, 588.599, 385.457, 588.599);
        ((GeneralPath)shape).moveTo(385.441, 587.93);
        ((GeneralPath)shape).curveTo(384.333, 587.93, 384.11502, 588.198, 384.11502, 589.263);
        ((GeneralPath)shape).curveTo(384.11502, 590.304, 384.35303, 590.591, 385.441, 590.591);
        ((GeneralPath)shape).curveTo(386.543, 590.591, 386.771, 590.333, 386.771, 589.263);
        ((GeneralPath)shape).curveTo(386.771, 588.162, 386.555, 587.93, 385.441, 587.93);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_216;
        g.setTransform(defaultTransform__0_216);
        g.setClip(clip__0_216);
        float alpha__0_217 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_217 = g.getClip();
        AffineTransform defaultTransform__0_217 = g.getTransform();
        
//      _0_217 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(389.658, 587.93);
        ((GeneralPath)shape).lineTo(388.77798, 587.93);
        ((GeneralPath)shape).lineTo(388.353, 590.095);
        ((GeneralPath)shape).lineTo(388.343, 590.095);
        ((GeneralPath)shape).lineTo(387.822, 587.93);
        ((GeneralPath)shape).lineTo(386.923, 587.93);
        ((GeneralPath)shape).lineTo(387.703, 590.591);
        ((GeneralPath)shape).lineTo(388.173, 590.591);
        ((GeneralPath)shape).curveTo(388.158, 590.882, 388.083, 591.139, 387.723, 591.139);
        ((GeneralPath)shape).curveTo(387.673, 591.139, 387.62198, 591.134, 387.573, 591.12897);
        ((GeneralPath)shape).lineTo(387.573, 591.76495);
        ((GeneralPath)shape).curveTo(387.674, 591.76495, 387.768, 591.76495, 387.869, 591.76495);
        ((GeneralPath)shape).curveTo(388.598, 591.76495, 388.853, 591.51697, 388.998, 590.819);
        ((GeneralPath)shape).lineTo(389.658, 587.93);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_217;
        g.setTransform(defaultTransform__0_217);
        g.setClip(clip__0_217);
        float alpha__0_218 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_218 = g.getClip();
        AffineTransform defaultTransform__0_218 = g.getTransform();
        
//      _0_218 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(391.464, 589.651);
        ((GeneralPath)shape).curveTo(391.464, 589.933, 391.29398, 589.964, 391.029, 589.964);
        ((GeneralPath)shape).curveTo(390.67398, 589.964, 390.604, 589.839, 390.604, 589.416);
        ((GeneralPath)shape).lineTo(392.323, 589.416);
        ((GeneralPath)shape).lineTo(392.323, 589.271);
        ((GeneralPath)shape).curveTo(392.323, 588.202, 392.026, 587.928, 391.037, 587.928);
        ((GeneralPath)shape).curveTo(389.97498, 587.928, 389.745, 588.259, 389.745, 589.261);
        ((GeneralPath)shape).curveTo(389.745, 590.232, 389.997, 590.589, 391.037, 590.589);
        ((GeneralPath)shape).curveTo(391.41898, 590.589, 391.74, 590.55896, 391.96698, 590.428);
        ((GeneralPath)shape).curveTo(392.193, 590.29297, 392.32297, 590.06, 392.32297, 589.64996);
        ((GeneralPath)shape).lineTo(391.464, 589.64996);
        ((GeneralPath)shape).moveTo(390.604, 588.947);
        ((GeneralPath)shape).curveTo(390.604, 588.607, 390.702, 588.53705, 391.015, 588.53705);
        ((GeneralPath)shape).curveTo(391.315, 588.53705, 391.463, 588.56104, 391.463, 588.947);
        ((GeneralPath)shape).lineTo(390.604, 588.947);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_218;
        g.setTransform(defaultTransform__0_218);
        g.setClip(clip__0_218);
        float alpha__0_219 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_219 = g.getClip();
        AffineTransform defaultTransform__0_219 = g.getTransform();
        
//      _0_219 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(393.475, 589.284);
        ((GeneralPath)shape).curveTo(393.475, 588.763, 393.475, 588.598, 393.93, 588.598);
        ((GeneralPath)shape).curveTo(394.412, 588.598, 394.412, 588.773, 394.412, 589.284);
        ((GeneralPath)shape).curveTo(394.412, 589.87897, 394.29898, 589.965, 393.93, 589.965);
        ((GeneralPath)shape).curveTo(393.558, 589.965, 393.475, 589.924, 393.475, 589.284);
        ((GeneralPath)shape).moveTo(395.271, 586.755);
        ((GeneralPath)shape).lineTo(394.412, 586.755);
        ((GeneralPath)shape).lineTo(394.412, 588.32);
        ((GeneralPath)shape).lineTo(394.37997, 588.32);
        ((GeneralPath)shape).curveTo(394.23697, 587.992, 393.955, 587.929, 393.63297, 587.929);
        ((GeneralPath)shape).curveTo(392.89798, 587.929, 392.61496, 588.245, 392.61496, 589.039);
        ((GeneralPath)shape).lineTo(392.61496, 589.633);
        ((GeneralPath)shape).curveTo(392.61496, 590.322, 392.98297, 590.58997, 393.63297, 590.58997);
        ((GeneralPath)shape).curveTo(394.01797, 590.58997, 394.29895, 590.506, 394.40198, 590.13794);
        ((GeneralPath)shape).lineTo(394.41296, 590.13794);
        ((GeneralPath)shape).lineTo(394.41296, 590.58997);
        ((GeneralPath)shape).lineTo(395.27197, 590.58997);
        ((GeneralPath)shape).lineTo(395.27197, 586.755);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_219;
        g.setTransform(defaultTransform__0_219);
        g.setClip(clip__0_219);
        float alpha__0_220 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_220 = g.getClip();
        AffineTransform defaultTransform__0_220 = g.getTransform();
        
//      _0_220 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(396.769, 587.93);
        ((GeneralPath)shape).lineTo(395.988, 587.93);
        ((GeneralPath)shape).lineTo(395.988, 588.812);
        ((GeneralPath)shape).lineTo(396.769, 588.812);
        ((GeneralPath)shape).lineTo(396.769, 587.93);
        ((GeneralPath)shape).moveTo(395.938, 591.295);
        ((GeneralPath)shape).curveTo(396.58798, 591.295, 396.76898, 591.099, 396.76898, 590.591);
        ((GeneralPath)shape).lineTo(396.76898, 589.739);
        ((GeneralPath)shape).lineTo(395.98798, 589.739);
        ((GeneralPath)shape).lineTo(395.98798, 590.591);
        ((GeneralPath)shape).lineTo(396.28796, 590.591);
        ((GeneralPath)shape).curveTo(396.28796, 590.828, 396.21497, 590.904, 395.93796, 590.904);
        ((GeneralPath)shape).lineTo(395.93796, 591.295);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_220;
        g.setTransform(defaultTransform__0_220);
        g.setClip(clip__0_220);
        float alpha__0_221 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_221 = g.getClip();
        AffineTransform defaultTransform__0_221 = g.getTransform();
        
//      _0_221 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(400.682, 589.025);
        ((GeneralPath)shape).lineTo(400.927, 589.025);
        ((GeneralPath)shape).curveTo(401.492, 589.025, 401.54102, 589.11804, 401.54102, 589.36505);
        ((GeneralPath)shape).curveTo(401.54102, 589.788, 401.376, 589.80707, 400.95502, 589.80707);
        ((GeneralPath)shape).curveTo(400.59503, 589.80707, 400.36902, 589.78705, 400.36902, 589.3791);
        ((GeneralPath)shape).lineTo(400.36902, 589.2581);
        ((GeneralPath)shape).lineTo(399.43103, 589.2581);
        ((GeneralPath)shape).lineTo(399.43103, 589.4561);
        ((GeneralPath)shape).curveTo(399.43103, 590.5161, 400.12802, 590.5901, 400.99902, 590.5901);
        ((GeneralPath)shape).curveTo(401.84702, 590.5901, 402.47803, 590.4521, 402.47803, 589.4961);
        ((GeneralPath)shape).curveTo(402.47803, 589.0681, 402.33603, 588.7241, 401.85303, 588.7041);
        ((GeneralPath)shape).lineTo(401.85303, 588.6651);
        ((GeneralPath)shape).curveTo(402.29004, 588.6021, 402.40002, 588.2271, 402.40002, 587.7941);
        ((GeneralPath)shape).curveTo(402.40002, 586.80615, 401.571, 586.75415, 400.963, 586.75415);
        ((GeneralPath)shape).curveTo(399.99802, 586.75415, 399.431, 586.84717, 399.431, 587.9581);
        ((GeneralPath)shape).lineTo(399.431, 588.09314);
        ((GeneralPath)shape).lineTo(400.369, 588.09314);
        ((GeneralPath)shape).lineTo(400.369, 588.01215);
        ((GeneralPath)shape).curveTo(400.369, 587.54614, 400.53998, 587.54614, 400.918, 587.54614);
        ((GeneralPath)shape).curveTo(401.447, 587.54614, 401.463, 587.65814, 401.463, 587.9921);
        ((GeneralPath)shape).curveTo(401.463, 588.26514, 401.423, 588.31915, 400.927, 588.31915);
        ((GeneralPath)shape).lineTo(400.682, 588.31915);
        ((GeneralPath)shape).lineTo(400.682, 589.025);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_221;
        g.setTransform(defaultTransform__0_221);
        g.setClip(clip__0_221);
        float alpha__0_222 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_222 = g.getClip();
        AffineTransform defaultTransform__0_222 = g.getTransform();
        
//      _0_222 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new Rectangle2D.Double(402.718994140625, 588.7119750976562, 2.5, 0.8610000014305115);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_222;
        g.setTransform(defaultTransform__0_222);
        g.setClip(clip__0_222);
        float alpha__0_223 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_223 = g.getClip();
        AffineTransform defaultTransform__0_223 = g.getTransform();
        
//      _0_223 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(407.328, 589.104);
        ((GeneralPath)shape).lineTo(406.152, 589.104);
        ((GeneralPath)shape).lineTo(407.293, 587.536);
        ((GeneralPath)shape).lineTo(407.328, 587.536);
        ((GeneralPath)shape).lineTo(407.328, 589.104);
        ((GeneralPath)shape).moveTo(408.266, 586.755);
        ((GeneralPath)shape).lineTo(406.804, 586.755);
        ((GeneralPath)shape).lineTo(405.36697, 588.74603);
        ((GeneralPath)shape).lineTo(405.36697, 589.80804);
        ((GeneralPath)shape).lineTo(407.32797, 589.80804);
        ((GeneralPath)shape).lineTo(407.32797, 590.59106);
        ((GeneralPath)shape).lineTo(408.26596, 590.59106);
        ((GeneralPath)shape).lineTo(408.26596, 589.80804);
        ((GeneralPath)shape).lineTo(408.58597, 589.80804);
        ((GeneralPath)shape).lineTo(408.58597, 589.10406);
        ((GeneralPath)shape).lineTo(408.26596, 589.10406);
        ((GeneralPath)shape).lineTo(408.26596, 586.755);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_223;
        g.setTransform(defaultTransform__0_223);
        g.setClip(clip__0_223);
        float alpha__0_224 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_224 = g.getClip();
        AffineTransform defaultTransform__0_224 = g.getTransform();
        
//      _0_224 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(409.287, 589.739);
        ((GeneralPath)shape).lineTo(409.287, 590.591);
        ((GeneralPath)shape).lineTo(409.58698, 590.591);
        ((GeneralPath)shape).curveTo(409.58698, 590.828, 409.51398, 590.904, 409.23697, 590.904);
        ((GeneralPath)shape).lineTo(409.23697, 591.295);
        ((GeneralPath)shape).curveTo(409.88696, 591.295, 410.06796, 591.099, 410.06796, 590.591);
        ((GeneralPath)shape).lineTo(410.06796, 589.739);
        ((GeneralPath)shape).lineTo(409.287, 589.739);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_224;
        g.setTransform(defaultTransform__0_224);
        g.setClip(clip__0_224);
        float alpha__0_225 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_225 = g.getClip();
        AffineTransform defaultTransform__0_225 = g.getTransform();
        
//      _0_225 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(414.372, 587.637);
        ((GeneralPath)shape).lineTo(415.382, 587.637);
        ((GeneralPath)shape).lineTo(415.382, 586.755);
        ((GeneralPath)shape).lineTo(412.396, 586.755);
        ((GeneralPath)shape).lineTo(412.396, 587.637);
        ((GeneralPath)shape).lineTo(413.356, 587.637);
        ((GeneralPath)shape).lineTo(413.356, 590.591);
        ((GeneralPath)shape).lineTo(414.372, 590.591);
        ((GeneralPath)shape).lineTo(414.372, 587.637);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_225;
        g.setTransform(defaultTransform__0_225);
        g.setClip(clip__0_225);
        float alpha__0_226 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_226 = g.getClip();
        AffineTransform defaultTransform__0_226 = g.getTransform();
        
//      _0_226 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(416.616, 588.599);
        ((GeneralPath)shape).curveTo(417.03, 588.599, 417.072, 588.713, 417.072, 589.285);
        ((GeneralPath)shape).curveTo(417.072, 589.855, 417.03, 589.966, 416.616, 589.966);
        ((GeneralPath)shape).curveTo(416.185, 589.966, 416.135, 589.856, 416.135, 589.285);
        ((GeneralPath)shape).curveTo(416.135, 588.713, 416.186, 588.599, 416.616, 588.599);
        ((GeneralPath)shape).moveTo(416.601, 587.93);
        ((GeneralPath)shape).curveTo(415.493, 587.93, 415.276, 588.198, 415.276, 589.263);
        ((GeneralPath)shape).curveTo(415.276, 590.304, 415.513, 590.591, 416.601, 590.591);
        ((GeneralPath)shape).curveTo(417.704, 590.591, 417.932, 590.333, 417.932, 589.263);
        ((GeneralPath)shape).curveTo(417.932, 588.162, 417.714, 587.93, 416.601, 587.93);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_226;
        g.setTransform(defaultTransform__0_226);
        g.setClip(clip__0_226);
        float alpha__0_227 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_227 = g.getClip();
        AffineTransform defaultTransform__0_227 = g.getTransform();
        
//      _0_227 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(419.725, 589.121);
        ((GeneralPath)shape).lineTo(420.506, 589.121);
        ((GeneralPath)shape).lineTo(420.506, 588.91296);
        ((GeneralPath)shape).curveTo(420.506, 588.42596, 420.45602, 587.93, 419.85202, 587.93);
        ((GeneralPath)shape).curveTo(419.691, 587.93, 419.55502, 587.964, 419.445, 588.037);
        ((GeneralPath)shape).curveTo(419.335, 588.11, 419.24802, 588.22797, 419.18802, 588.399);
        ((GeneralPath)shape).lineTo(419.178, 588.399);
        ((GeneralPath)shape).lineTo(419.178, 587.929);
        ((GeneralPath)shape).lineTo(418.319, 587.929);
        ((GeneralPath)shape).lineTo(418.319, 590.59);
        ((GeneralPath)shape).lineTo(419.178, 590.59);
        ((GeneralPath)shape).lineTo(419.178, 589.01904);
        ((GeneralPath)shape).curveTo(419.178, 588.788, 419.237, 588.567, 419.493, 588.567);
        ((GeneralPath)shape).curveTo(419.724, 588.567, 419.724, 588.788, 419.724, 588.986);
        ((GeneralPath)shape).lineTo(419.724, 589.121);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_227;
        g.setTransform(defaultTransform__0_227);
        g.setClip(clip__0_227);
        float alpha__0_228 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_228 = g.getClip();
        AffineTransform defaultTransform__0_228 = g.getTransform();
        
//      _0_228 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(423.195, 588.716);
        ((GeneralPath)shape).lineTo(423.195, 588.589);
        ((GeneralPath)shape).curveTo(423.195, 587.93, 422.513, 587.93, 422.03302, 587.93);
        ((GeneralPath)shape).curveTo(421.30203, 587.93, 420.74503, 587.93, 420.74503, 588.77997);
        ((GeneralPath)shape).curveTo(420.74503, 589.318, 420.93903, 589.56396, 422.02203, 589.56396);
        ((GeneralPath)shape).curveTo(422.31604, 589.56396, 422.46304, 589.56396, 422.46304, 589.818);
        ((GeneralPath)shape).curveTo(422.46304, 590.019, 422.40405, 590.044, 422.02203, 590.044);
        ((GeneralPath)shape).curveTo(421.81503, 590.044, 421.60403, 590.044, 421.60403, 589.755);
        ((GeneralPath)shape).lineTo(420.74503, 589.755);
        ((GeneralPath)shape).curveTo(420.74503, 590.592, 421.368, 590.592, 422.02203, 590.592);
        ((GeneralPath)shape).curveTo(422.72003, 590.592, 423.32303, 590.48, 423.32303, 589.722);
        ((GeneralPath)shape).curveTo(423.32303, 589.001, 422.92303, 588.95197, 422.02203, 588.932);
        ((GeneralPath)shape).curveTo(421.61404, 588.922, 421.60403, 588.83203, 421.60403, 588.703);
        ((GeneralPath)shape).curveTo(421.60403, 588.549, 421.60403, 588.494, 422.03302, 588.494);
        ((GeneralPath)shape).curveTo(422.1, 588.494, 422.17603, 588.494, 422.238, 588.51904);
        ((GeneralPath)shape).curveTo(422.299, 588.5491, 422.351, 588.606, 422.366, 588.71704);
        ((GeneralPath)shape).lineTo(423.195, 588.71704);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_228;
        g.setTransform(defaultTransform__0_228);
        g.setClip(clip__0_228);
        float alpha__0_229 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_229 = g.getClip();
        AffineTransform defaultTransform__0_229 = g.getTransform();
        
//      _0_229 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(424.977, 588.599);
        ((GeneralPath)shape).curveTo(425.391, 588.599, 425.43198, 588.713, 425.43198, 589.285);
        ((GeneralPath)shape).curveTo(425.43198, 589.855, 425.391, 589.966, 424.977, 589.966);
        ((GeneralPath)shape).curveTo(424.546, 589.966, 424.495, 589.856, 424.495, 589.285);
        ((GeneralPath)shape).curveTo(424.494, 588.713, 424.546, 588.599, 424.977, 588.599);
        ((GeneralPath)shape).moveTo(424.961, 587.93);
        ((GeneralPath)shape).curveTo(423.853, 587.93, 423.635, 588.198, 423.635, 589.263);
        ((GeneralPath)shape).curveTo(423.635, 590.304, 423.87302, 590.591, 424.961, 590.591);
        ((GeneralPath)shape).curveTo(426.064, 590.591, 426.291, 590.333, 426.291, 589.263);
        ((GeneralPath)shape).curveTo(426.291, 588.162, 426.074, 587.93, 424.961, 587.93);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_229;
        g.setTransform(defaultTransform__0_229);
        g.setClip(clip__0_229);
        float alpha__0_230 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_230 = g.getClip();
        AffineTransform defaultTransform__0_230 = g.getTransform();
        
//      _0_230 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(432.216, 589.719);
        ((GeneralPath)shape).lineTo(432.182, 589.719);
        ((GeneralPath)shape).lineTo(431.509, 586.755);
        ((GeneralPath)shape).lineTo(430.467, 586.755);
        ((GeneralPath)shape).lineTo(429.76, 589.719);
        ((GeneralPath)shape).lineTo(429.725, 589.719);
        ((GeneralPath)shape).lineTo(429.184, 586.755);
        ((GeneralPath)shape).lineTo(428.166, 586.755);
        ((GeneralPath)shape).lineTo(428.978, 590.591);
        ((GeneralPath)shape).lineTo(430.412, 590.591);
        ((GeneralPath)shape).lineTo(430.959, 588.191);
        ((GeneralPath)shape).lineTo(430.998, 588.191);
        ((GeneralPath)shape).lineTo(431.509, 590.591);
        ((GeneralPath)shape).lineTo(432.958, 590.591);
        ((GeneralPath)shape).lineTo(433.791, 586.755);
        ((GeneralPath)shape).lineTo(432.783, 586.755);
        ((GeneralPath)shape).lineTo(432.216, 589.719);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_230;
        g.setTransform(defaultTransform__0_230);
        g.setClip(clip__0_230);
        float alpha__0_231 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_231 = g.getClip();
        AffineTransform defaultTransform__0_231 = g.getTransform();
        
//      _0_231 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(435.544, 589.651);
        ((GeneralPath)shape).curveTo(435.544, 589.933, 435.374, 589.964, 435.109, 589.964);
        ((GeneralPath)shape).curveTo(434.754, 589.964, 434.68402, 589.839, 434.68402, 589.416);
        ((GeneralPath)shape).lineTo(436.403, 589.416);
        ((GeneralPath)shape).lineTo(436.403, 589.271);
        ((GeneralPath)shape).curveTo(436.403, 588.202, 436.10602, 587.928, 435.117, 587.928);
        ((GeneralPath)shape).curveTo(434.055, 587.928, 433.825, 588.259, 433.825, 589.261);
        ((GeneralPath)shape).curveTo(433.825, 590.232, 434.07703, 590.589, 435.117, 590.589);
        ((GeneralPath)shape).curveTo(435.499, 590.589, 435.82, 590.55896, 436.047, 590.428);
        ((GeneralPath)shape).curveTo(436.273, 590.29297, 436.40298, 590.06, 436.40298, 589.64996);
        ((GeneralPath)shape).lineTo(435.544, 589.64996);
        ((GeneralPath)shape).moveTo(434.685, 588.947);
        ((GeneralPath)shape).curveTo(434.685, 588.607, 434.783, 588.53705, 435.096, 588.53705);
        ((GeneralPath)shape).curveTo(435.396, 588.53705, 435.544, 588.56104, 435.544, 588.947);
        ((GeneralPath)shape).lineTo(434.685, 588.947);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_231;
        g.setTransform(defaultTransform__0_231);
        g.setClip(clip__0_231);
        float alpha__0_232 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_232 = g.getClip();
        AffineTransform defaultTransform__0_232 = g.getTransform();
        
//      _0_232 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(438.414, 590.591);
        ((GeneralPath)shape).lineTo(439.273, 590.591);
        ((GeneralPath)shape).lineTo(439.273, 588.947);
        ((GeneralPath)shape).curveTo(439.273, 588.039, 438.909, 587.929, 438.043, 587.929);
        ((GeneralPath)shape).curveTo(437.467, 587.929, 436.852, 587.929, 436.852, 588.75);
        ((GeneralPath)shape).lineTo(437.711, 588.75);
        ((GeneralPath)shape).curveTo(437.711, 588.512, 437.839, 588.461, 438.043, 588.461);
        ((GeneralPath)shape).curveTo(438.399, 588.461, 438.414, 588.61, 438.414, 588.851);
        ((GeneralPath)shape).lineTo(438.414, 589.254);
        ((GeneralPath)shape).lineTo(438.376, 589.254);
        ((GeneralPath)shape).curveTo(438.285, 588.94604, 437.93, 588.94604, 437.699, 588.94604);
        ((GeneralPath)shape).curveTo(437.095, 588.94604, 436.773, 589.14105, 436.773, 589.77203);
        ((GeneralPath)shape).curveTo(436.773, 590.44403, 437.11002, 590.58905, 437.687, 590.58905);
        ((GeneralPath)shape).curveTo(437.975, 590.58905, 438.351, 590.53, 438.414, 590.18207);
        ((GeneralPath)shape).lineTo(438.414, 590.591);
        ((GeneralPath)shape).moveTo(437.979, 589.495);
        ((GeneralPath)shape).curveTo(438.238, 589.495, 438.414, 589.515, 438.414, 589.729);
        ((GeneralPath)shape).curveTo(438.414, 589.999, 438.312, 590.042, 437.979, 590.042);
        ((GeneralPath)shape).curveTo(437.859, 590.042, 437.63202, 590.042, 437.63202, 589.763);
        ((GeneralPath)shape).curveTo(437.633, 589.529, 437.785, 589.495, 437.979, 589.495);
        g.setPaint(paint);
        g.fill(shape);
        paint3(g, clip_, defaultTransform_, alpha__0, clip__0,
				defaultTransform__0, alpha__0_232, clip__0_232,
				defaultTransform__0_232);
	}
	private static void paint3(Graphics2D g, Shape clip_,
			AffineTransform defaultTransform_, float alpha__0, Shape clip__0,
			AffineTransform defaultTransform__0, float alpha__0_232,
			Shape clip__0_232, AffineTransform defaultTransform__0_232) {
		Shape shape;
		Paint paint;
		Stroke stroke;
		float origAlpha;
		origAlpha = alpha__0_232;
        g.setTransform(defaultTransform__0_232);
        g.setClip(clip__0_232);
        float alpha__0_233 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_233 = g.getClip();
        AffineTransform defaultTransform__0_233 = g.getTransform();
        
//      _0_233 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(441.163, 588.567);
        ((GeneralPath)shape).curveTo(441.596, 588.567, 441.615, 588.74603, 441.615, 589.268);
        ((GeneralPath)shape).curveTo(441.615, 589.877, 441.50198, 589.964, 441.163, 589.964);
        ((GeneralPath)shape).curveTo(440.784, 589.964, 440.75598, 589.718, 440.75598, 589.268);
        ((GeneralPath)shape).curveTo(440.756, 588.701, 440.818, 588.567, 441.163, 588.567);
        ((GeneralPath)shape).moveTo(439.896, 591.765);
        ((GeneralPath)shape).lineTo(440.755, 591.765);
        ((GeneralPath)shape).lineTo(440.755, 590.2);
        ((GeneralPath)shape).lineTo(440.793, 590.2);
        ((GeneralPath)shape).curveTo(440.93, 590.532, 441.196, 590.59204, 441.504, 590.59204);
        ((GeneralPath)shape).curveTo(442.205, 590.59204, 442.474, 590.27606, 442.474, 589.48206);
        ((GeneralPath)shape).lineTo(442.474, 588.8871);
        ((GeneralPath)shape).curveTo(442.474, 588.1991, 442.123, 587.9311, 441.504, 587.9311);
        ((GeneralPath)shape).curveTo(441.13998, 587.9311, 440.872, 588.0161, 440.775, 588.3511);
        ((GeneralPath)shape).lineTo(440.755, 588.3511);
        ((GeneralPath)shape).lineTo(440.755, 587.9311);
        ((GeneralPath)shape).lineTo(439.896, 587.9311);
        ((GeneralPath)shape).lineTo(439.896, 591.765);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_233;
        g.setTransform(defaultTransform__0_233);
        g.setClip(clip__0_233);
        float alpha__0_234 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_234 = g.getClip();
        AffineTransform defaultTransform__0_234 = g.getTransform();
        
//      _0_234 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(444.172, 588.599);
        ((GeneralPath)shape).curveTo(444.585, 588.599, 444.62698, 588.713, 444.62698, 589.285);
        ((GeneralPath)shape).curveTo(444.62698, 589.855, 444.585, 589.966, 444.172, 589.966);
        ((GeneralPath)shape).curveTo(443.74, 589.966, 443.69, 589.856, 443.69, 589.285);
        ((GeneralPath)shape).curveTo(443.689, 588.713, 443.74, 588.599, 444.172, 588.599);
        ((GeneralPath)shape).moveTo(444.156, 587.93);
        ((GeneralPath)shape).curveTo(443.047, 587.93, 442.83002, 588.198, 442.83002, 589.263);
        ((GeneralPath)shape).curveTo(442.83002, 590.304, 443.06802, 590.591, 444.156, 590.591);
        ((GeneralPath)shape).curveTo(445.258, 590.591, 445.486, 590.333, 445.486, 589.263);
        ((GeneralPath)shape).curveTo(445.486, 588.162, 445.27, 587.93, 444.156, 587.93);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_234;
        g.setTransform(defaultTransform__0_234);
        g.setClip(clip__0_234);
        float alpha__0_235 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_235 = g.getClip();
        AffineTransform defaultTransform__0_235 = g.getTransform();
        
//      _0_235 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(445.951, 587.93);
        ((GeneralPath)shape).lineTo(445.951, 590.591);
        ((GeneralPath)shape).lineTo(446.81, 590.591);
        ((GeneralPath)shape).lineTo(446.81, 589.126);
        ((GeneralPath)shape).curveTo(446.81, 588.802, 446.873, 588.56696, 447.249, 588.56696);
        ((GeneralPath)shape).curveTo(447.522, 588.56696, 447.591, 588.69495, 447.591, 588.95294);
        ((GeneralPath)shape).lineTo(447.591, 590.59094);
        ((GeneralPath)shape).lineTo(448.45, 590.59094);
        ((GeneralPath)shape).lineTo(448.45, 588.77094);
        ((GeneralPath)shape).curveTo(448.45, 588.20496, 448.18903, 587.92993, 447.618, 587.92993);
        ((GeneralPath)shape).curveTo(447.42, 587.92993, 447.25803, 587.95294, 447.13, 588.02795);
        ((GeneralPath)shape).curveTo(447.002, 588.09595, 446.907, 588.217, 446.844, 588.4119);
        ((GeneralPath)shape).lineTo(446.81, 588.4119);
        ((GeneralPath)shape).lineTo(446.81, 587.9309);
        ((GeneralPath)shape).lineTo(445.951, 587.9309);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_235;
        g.setTransform(defaultTransform__0_235);
        g.setClip(clip__0_235);
        float alpha__0_236 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_236 = g.getClip();
        AffineTransform defaultTransform__0_236 = g.getTransform();
        
//      _0_236 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(451.862, 589.025);
        ((GeneralPath)shape).lineTo(452.609, 589.025);
        ((GeneralPath)shape).curveTo(452.922, 589.025, 453.034, 589.07, 453.034, 589.379);
        ((GeneralPath)shape).curveTo(453.034, 589.684, 452.90298, 589.75806, 452.591, 589.75806);
        ((GeneralPath)shape).lineTo(451.862, 589.75806);
        ((GeneralPath)shape).lineTo(451.862, 589.025);
        ((GeneralPath)shape).moveTo(451.862, 587.587);
        ((GeneralPath)shape).lineTo(452.658, 587.587);
        ((GeneralPath)shape).curveTo(452.923, 587.587, 452.956, 587.68, 452.956, 587.927);
        ((GeneralPath)shape).curveTo(452.956, 588.255, 452.876, 588.321, 452.54498, 588.321);
        ((GeneralPath)shape).lineTo(451.86197, 588.321);
        ((GeneralPath)shape).lineTo(451.86197, 587.587);
        ((GeneralPath)shape).moveTo(450.847, 590.591);
        ((GeneralPath)shape).lineTo(452.82898, 590.591);
        ((GeneralPath)shape).curveTo(453.62097, 590.591, 454.05, 590.465, 454.05, 589.538);
        ((GeneralPath)shape).curveTo(454.05, 589.064, 453.952, 588.726, 453.384, 588.69104);
        ((GeneralPath)shape).lineTo(453.384, 588.64905);
        ((GeneralPath)shape).curveTo(453.877, 588.56604, 453.97202, 588.24506, 453.97202, 587.7521);
        ((GeneralPath)shape).curveTo(453.97202, 586.9001, 453.536, 586.75507, 452.798, 586.75507);
        ((GeneralPath)shape).lineTo(450.84702, 586.75507);
        ((GeneralPath)shape).lineTo(450.84702, 590.591);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_236;
        g.setTransform(defaultTransform__0_236);
        g.setClip(clip__0_236);
        float alpha__0_237 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_237 = g.getClip();
        AffineTransform defaultTransform__0_237 = g.getTransform();
        
//      _0_237 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(457.274, 587.637);
        ((GeneralPath)shape).lineTo(458.166, 587.637);
        ((GeneralPath)shape).curveTo(458.52298, 587.637, 458.681, 587.74005, 458.681, 588.241);
        ((GeneralPath)shape).lineTo(458.681, 589.051);
        ((GeneralPath)shape).curveTo(458.681, 589.458, 458.543, 589.71, 458.166, 589.71);
        ((GeneralPath)shape).lineTo(457.274, 589.71);
        ((GeneralPath)shape).lineTo(457.274, 587.637);
        ((GeneralPath)shape).moveTo(456.259, 590.591);
        ((GeneralPath)shape).lineTo(458.311, 590.591);
        ((GeneralPath)shape).curveTo(459.364, 590.591, 459.697, 590.09503, 459.697, 589.046);
        ((GeneralPath)shape).lineTo(459.697, 588.24304);
        ((GeneralPath)shape).curveTo(459.697, 587.15405, 459.228, 586.75507, 458.175, 586.75507);
        ((GeneralPath)shape).lineTo(456.25998, 586.75507);
        ((GeneralPath)shape).lineTo(456.25998, 590.591);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_237;
        g.setTransform(defaultTransform__0_237);
        g.setClip(clip__0_237);
        float alpha__0_238 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_238 = g.getClip();
        AffineTransform defaultTransform__0_238 = g.getTransform();
        
//      _0_238 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(461.774, 589.651);
        ((GeneralPath)shape).curveTo(461.774, 589.933, 461.60397, 589.964, 461.339, 589.964);
        ((GeneralPath)shape).curveTo(460.98398, 589.964, 460.914, 589.839, 460.914, 589.416);
        ((GeneralPath)shape).lineTo(462.633, 589.416);
        ((GeneralPath)shape).lineTo(462.633, 589.271);
        ((GeneralPath)shape).curveTo(462.633, 588.202, 462.336, 587.928, 461.347, 587.928);
        ((GeneralPath)shape).curveTo(460.28497, 587.928, 460.055, 588.259, 460.055, 589.261);
        ((GeneralPath)shape).curveTo(460.055, 590.232, 460.307, 590.589, 461.347, 590.589);
        ((GeneralPath)shape).curveTo(461.72897, 590.589, 462.05, 590.55896, 462.27698, 590.428);
        ((GeneralPath)shape).curveTo(462.503, 590.29297, 462.63297, 590.06, 462.63297, 589.64996);
        ((GeneralPath)shape).lineTo(461.774, 589.64996);
        ((GeneralPath)shape).moveTo(460.915, 588.947);
        ((GeneralPath)shape).curveTo(460.915, 588.607, 461.013, 588.53705, 461.32602, 588.53705);
        ((GeneralPath)shape).curveTo(461.626, 588.53705, 461.77402, 588.56104, 461.77402, 588.947);
        ((GeneralPath)shape).lineTo(460.915, 588.947);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_238;
        g.setTransform(defaultTransform__0_238);
        g.setClip(clip__0_238);
        float alpha__0_239 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_239 = g.getClip();
        AffineTransform defaultTransform__0_239 = g.getTransform();
        
//      _0_239 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(465.376, 588.716);
        ((GeneralPath)shape).lineTo(465.376, 588.589);
        ((GeneralPath)shape).curveTo(465.376, 587.93, 464.694, 587.93, 464.21402, 587.93);
        ((GeneralPath)shape).curveTo(463.48303, 587.93, 462.92603, 587.93, 462.92603, 588.77997);
        ((GeneralPath)shape).curveTo(462.92603, 589.318, 463.12003, 589.56396, 464.20303, 589.56396);
        ((GeneralPath)shape).curveTo(464.49704, 589.56396, 464.64404, 589.56396, 464.64404, 589.818);
        ((GeneralPath)shape).curveTo(464.64404, 590.019, 464.58505, 590.044, 464.20303, 590.044);
        ((GeneralPath)shape).curveTo(463.99603, 590.044, 463.78503, 590.044, 463.78503, 589.755);
        ((GeneralPath)shape).lineTo(462.92603, 589.755);
        ((GeneralPath)shape).curveTo(462.92603, 590.592, 463.549, 590.592, 464.20303, 590.592);
        ((GeneralPath)shape).curveTo(464.90103, 590.592, 465.50403, 590.48, 465.50403, 589.722);
        ((GeneralPath)shape).curveTo(465.50403, 589.001, 465.10403, 588.95197, 464.20303, 588.932);
        ((GeneralPath)shape).curveTo(463.79504, 588.922, 463.78503, 588.83203, 463.78503, 588.703);
        ((GeneralPath)shape).curveTo(463.78503, 588.549, 463.78503, 588.494, 464.21402, 588.494);
        ((GeneralPath)shape).curveTo(464.281, 588.494, 464.35703, 588.494, 464.419, 588.51904);
        ((GeneralPath)shape).curveTo(464.48, 588.5491, 464.532, 588.606, 464.547, 588.71704);
        ((GeneralPath)shape).lineTo(465.376, 588.71704);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_239;
        g.setTransform(defaultTransform__0_239);
        g.setClip(clip__0_239);
        float alpha__0_240 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_240 = g.getClip();
        AffineTransform defaultTransform__0_240 = g.getTransform();
        
//      _0_240 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(467.885, 587.93);
        ((GeneralPath)shape).lineTo(466.909, 587.93);
        ((GeneralPath)shape).lineTo(466.909, 587.367);
        ((GeneralPath)shape).lineTo(466.05, 587.367);
        ((GeneralPath)shape).lineTo(466.05, 587.93);
        ((GeneralPath)shape).lineTo(465.731, 587.93);
        ((GeneralPath)shape).lineTo(465.731, 588.568);
        ((GeneralPath)shape).lineTo(466.05, 588.568);
        ((GeneralPath)shape).lineTo(466.05, 589.865);
        ((GeneralPath)shape).curveTo(466.05, 590.522, 466.50198, 590.592, 467.046, 590.592);
        ((GeneralPath)shape).curveTo(467.741, 590.592, 468.003, 590.433, 468.003, 589.72);
        ((GeneralPath)shape).lineTo(468.003, 589.48596);
        ((GeneralPath)shape).lineTo(467.3, 589.48596);
        ((GeneralPath)shape).lineTo(467.3, 589.62695);
        ((GeneralPath)shape).curveTo(467.3, 589.80695, 467.3, 589.967, 467.089, 589.967);
        ((GeneralPath)shape).curveTo(466.938, 589.967, 466.909, 589.902, 466.909, 589.746);
        ((GeneralPath)shape).lineTo(466.909, 588.569);
        ((GeneralPath)shape).lineTo(467.885, 588.569);
        ((GeneralPath)shape).lineTo(467.885, 587.93);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_240;
        g.setTransform(defaultTransform__0_240);
        g.setClip(clip__0_240);
        float alpha__0_241 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_241 = g.getClip();
        AffineTransform defaultTransform__0_241 = g.getTransform();
        
//      _0_241 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(469.725, 589.121);
        ((GeneralPath)shape).lineTo(470.506, 589.121);
        ((GeneralPath)shape).lineTo(470.506, 588.91296);
        ((GeneralPath)shape).curveTo(470.506, 588.42596, 470.45602, 587.93, 469.85202, 587.93);
        ((GeneralPath)shape).curveTo(469.691, 587.93, 469.55603, 587.964, 469.445, 588.037);
        ((GeneralPath)shape).curveTo(469.33502, 588.11, 469.249, 588.22797, 469.18802, 588.399);
        ((GeneralPath)shape).lineTo(469.178, 588.399);
        ((GeneralPath)shape).lineTo(469.178, 587.929);
        ((GeneralPath)shape).lineTo(468.319, 587.929);
        ((GeneralPath)shape).lineTo(468.319, 590.59);
        ((GeneralPath)shape).lineTo(469.178, 590.59);
        ((GeneralPath)shape).lineTo(469.178, 589.01904);
        ((GeneralPath)shape).curveTo(469.178, 588.788, 469.237, 588.567, 469.493, 588.567);
        ((GeneralPath)shape).curveTo(469.724, 588.567, 469.724, 588.788, 469.724, 588.986);
        ((GeneralPath)shape).lineTo(469.724, 589.121);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_241;
        g.setTransform(defaultTransform__0_241);
        g.setClip(clip__0_241);
        float alpha__0_242 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_242 = g.getClip();
        AffineTransform defaultTransform__0_242 = g.getTransform();
        
//      _0_242 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(472.188, 588.599);
        ((GeneralPath)shape).curveTo(472.602, 588.599, 472.64297, 588.713, 472.64297, 589.285);
        ((GeneralPath)shape).curveTo(472.64297, 589.855, 472.602, 589.966, 472.188, 589.966);
        ((GeneralPath)shape).curveTo(471.757, 589.966, 471.706, 589.856, 471.706, 589.285);
        ((GeneralPath)shape).curveTo(471.705, 588.713, 471.757, 588.599, 472.188, 588.599);
        ((GeneralPath)shape).moveTo(472.172, 587.93);
        ((GeneralPath)shape).curveTo(471.064, 587.93, 470.846, 588.198, 470.846, 589.263);
        ((GeneralPath)shape).curveTo(470.846, 590.304, 471.084, 590.591, 472.172, 590.591);
        ((GeneralPath)shape).curveTo(473.274, 590.591, 473.50198, 590.333, 473.50198, 589.263);
        ((GeneralPath)shape).curveTo(473.502, 588.162, 473.285, 587.93, 472.172, 587.93);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_242;
        g.setTransform(defaultTransform__0_242);
        g.setClip(clip__0_242);
        float alpha__0_243 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_243 = g.getClip();
        AffineTransform defaultTransform__0_243 = g.getTransform();
        
//      _0_243 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(476.389, 587.93);
        ((GeneralPath)shape).lineTo(475.509, 587.93);
        ((GeneralPath)shape).lineTo(475.084, 590.095);
        ((GeneralPath)shape).lineTo(475.074, 590.095);
        ((GeneralPath)shape).lineTo(474.553, 587.93);
        ((GeneralPath)shape).lineTo(473.65402, 587.93);
        ((GeneralPath)shape).lineTo(474.43402, 590.591);
        ((GeneralPath)shape).lineTo(474.90402, 590.591);
        ((GeneralPath)shape).curveTo(474.889, 590.882, 474.81403, 591.139, 474.454, 591.139);
        ((GeneralPath)shape).curveTo(474.40402, 591.139, 474.353, 591.134, 474.30402, 591.12897);
        ((GeneralPath)shape).lineTo(474.30402, 591.76495);
        ((GeneralPath)shape).curveTo(474.40503, 591.76495, 474.49902, 591.76495, 474.6, 591.76495);
        ((GeneralPath)shape).curveTo(475.329, 591.76495, 475.584, 591.51697, 475.729, 590.819);
        ((GeneralPath)shape).lineTo(476.389, 587.93);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_243;
        g.setTransform(defaultTransform__0_243);
        g.setClip(clip__0_243);
        float alpha__0_244 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_244 = g.getClip();
        AffineTransform defaultTransform__0_244 = g.getTransform();
        
//      _0_244 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(478.194, 589.651);
        ((GeneralPath)shape).curveTo(478.194, 589.933, 478.024, 589.964, 477.759, 589.964);
        ((GeneralPath)shape).curveTo(477.404, 589.964, 477.334, 589.839, 477.334, 589.416);
        ((GeneralPath)shape).lineTo(479.053, 589.416);
        ((GeneralPath)shape).lineTo(479.053, 589.271);
        ((GeneralPath)shape).curveTo(479.053, 588.202, 478.756, 587.928, 477.767, 587.928);
        ((GeneralPath)shape).curveTo(476.705, 587.928, 476.475, 588.259, 476.475, 589.261);
        ((GeneralPath)shape).curveTo(476.475, 590.232, 476.72702, 590.589, 477.767, 590.589);
        ((GeneralPath)shape).curveTo(478.149, 590.589, 478.47, 590.55896, 478.697, 590.428);
        ((GeneralPath)shape).curveTo(478.923, 590.29297, 479.05298, 590.06, 479.05298, 589.64996);
        ((GeneralPath)shape).lineTo(478.194, 589.64996);
        ((GeneralPath)shape).moveTo(477.335, 588.947);
        ((GeneralPath)shape).curveTo(477.335, 588.607, 477.43298, 588.53705, 477.746, 588.53705);
        ((GeneralPath)shape).curveTo(478.046, 588.53705, 478.194, 588.56104, 478.194, 588.947);
        ((GeneralPath)shape).lineTo(477.335, 588.947);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_244;
        g.setTransform(defaultTransform__0_244);
        g.setClip(clip__0_244);
        float alpha__0_245 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_245 = g.getClip();
        AffineTransform defaultTransform__0_245 = g.getTransform();
        
//      _0_245 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(480.205, 589.284);
        ((GeneralPath)shape).curveTo(480.205, 588.763, 480.205, 588.598, 480.65997, 588.598);
        ((GeneralPath)shape).curveTo(481.14197, 588.598, 481.14197, 588.773, 481.14197, 589.284);
        ((GeneralPath)shape).curveTo(481.14197, 589.87897, 481.02896, 589.965, 480.65997, 589.965);
        ((GeneralPath)shape).curveTo(480.288, 589.965, 480.205, 589.924, 480.205, 589.284);
        ((GeneralPath)shape).moveTo(482.002, 586.755);
        ((GeneralPath)shape).lineTo(481.143, 586.755);
        ((GeneralPath)shape).lineTo(481.143, 588.32);
        ((GeneralPath)shape).lineTo(481.111, 588.32);
        ((GeneralPath)shape).curveTo(480.968, 587.992, 480.686, 587.929, 480.36398, 587.929);
        ((GeneralPath)shape).curveTo(479.629, 587.929, 479.34598, 588.245, 479.34598, 589.039);
        ((GeneralPath)shape).lineTo(479.34598, 589.633);
        ((GeneralPath)shape).curveTo(479.34598, 590.322, 479.714, 590.58997, 480.36398, 590.58997);
        ((GeneralPath)shape).curveTo(480.749, 590.58997, 481.02997, 590.506, 481.133, 590.13794);
        ((GeneralPath)shape).lineTo(481.14398, 590.13794);
        ((GeneralPath)shape).lineTo(481.14398, 590.58997);
        ((GeneralPath)shape).lineTo(482.003, 590.58997);
        ((GeneralPath)shape).lineTo(482.003, 586.755);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_245;
        g.setTransform(defaultTransform__0_245);
        g.setClip(clip__0_245);
        float alpha__0_246 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_246 = g.getClip();
        AffineTransform defaultTransform__0_246 = g.getTransform();
        
//      _0_246 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(330.757, 484.46);
        ((GeneralPath)shape).lineTo(329.632, 484.46);
        ((GeneralPath)shape).lineTo(329.632, 486.96);
        ((GeneralPath)shape).lineTo(326.757, 486.96);
        ((GeneralPath)shape).lineTo(326.757, 484.46);
        ((GeneralPath)shape).lineTo(325.632, 484.46);
        ((GeneralPath)shape).lineTo(325.632, 490.585);
        ((GeneralPath)shape).lineTo(326.757, 490.585);
        ((GeneralPath)shape).lineTo(326.757, 487.96);
        ((GeneralPath)shape).lineTo(329.632, 487.96);
        ((GeneralPath)shape).lineTo(329.632, 490.585);
        ((GeneralPath)shape).lineTo(330.757, 490.585);
        ((GeneralPath)shape).lineTo(330.757, 484.46);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_246;
        g.setTransform(defaultTransform__0_246);
        g.setClip(clip__0_246);
        float alpha__0_247 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_247 = g.getClip();
        AffineTransform defaultTransform__0_247 = g.getTransform();
        
//      _0_247 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new Rectangle2D.Double(331.7829895019531, 484.4599914550781, 1.125, 6.125);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_247;
        g.setTransform(defaultTransform__0_247);
        g.setClip(clip__0_247);
        float alpha__0_248 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_248 = g.getClip();
        AffineTransform defaultTransform__0_248 = g.getTransform();
        
//      _0_248 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(336.367, 485.456);
        ((GeneralPath)shape).lineTo(338.127, 485.456);
        ((GeneralPath)shape).lineTo(338.127, 484.46);
        ((GeneralPath)shape).lineTo(333.482, 484.46);
        ((GeneralPath)shape).lineTo(333.482, 485.456);
        ((GeneralPath)shape).lineTo(335.242, 485.456);
        ((GeneralPath)shape).lineTo(335.242, 490.585);
        ((GeneralPath)shape).lineTo(336.367, 490.585);
        ((GeneralPath)shape).lineTo(336.367, 485.456);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_248;
        g.setTransform(defaultTransform__0_248);
        g.setClip(clip__0_248);
        float alpha__0_249 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_249 = g.getClip();
        AffineTransform defaultTransform__0_249 = g.getTransform();
        
//      _0_249 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(342.629, 484.46);
        ((GeneralPath)shape).lineTo(341.504, 484.46);
        ((GeneralPath)shape).lineTo(341.504, 490.585);
        ((GeneralPath)shape).lineTo(345.43, 490.585);
        ((GeneralPath)shape).lineTo(345.43, 489.589);
        ((GeneralPath)shape).lineTo(342.629, 489.589);
        ((GeneralPath)shape).lineTo(342.629, 484.46);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_249;
        g.setTransform(defaultTransform__0_249);
        g.setClip(clip__0_249);
        float alpha__0_250 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_250 = g.getClip();
        AffineTransform defaultTransform__0_250 = g.getTransform();
        
//      _0_250 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(348.363, 485.456);
        ((GeneralPath)shape).curveTo(349.371, 485.456, 349.773, 485.53598, 349.773, 486.665);
        ((GeneralPath)shape).lineTo(349.773, 488.28);
        ((GeneralPath)shape).curveTo(349.773, 489.44, 349.418, 489.585, 348.38602, 489.585);
        ((GeneralPath)shape).curveTo(347.251, 489.585, 347.023, 489.474, 347.023, 488.28);
        ((GeneralPath)shape).lineTo(347.023, 486.665);
        ((GeneralPath)shape).curveTo(347.023, 485.712, 347.143, 485.456, 348.363, 485.456);
        ((GeneralPath)shape).moveTo(348.387, 484.46);
        ((GeneralPath)shape).curveTo(346.518, 484.46, 345.899, 484.78998, 345.899, 486.663);
        ((GeneralPath)shape).lineTo(345.899, 488.28598);
        ((GeneralPath)shape).curveTo(345.899, 490.26297, 346.567, 490.585, 348.387, 490.585);
        ((GeneralPath)shape).curveTo(350.168, 490.585, 350.899, 490.216, 350.899, 488.28598);
        ((GeneralPath)shape).lineTo(350.899, 486.663);
        ((GeneralPath)shape).curveTo(350.898, 484.726, 350.082, 484.46, 348.387, 484.46);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_250;
        g.setTransform(defaultTransform__0_250);
        g.setClip(clip__0_250);
        float alpha__0_251 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_251 = g.getClip();
        AffineTransform defaultTransform__0_251 = g.getTransform();
        
//      _0_251 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(356.494, 486.536);
        ((GeneralPath)shape).lineTo(356.494, 486.204);
        ((GeneralPath)shape).curveTo(356.414, 484.46002, 355.547, 484.46002, 354.03497, 484.46002);
        ((GeneralPath)shape).curveTo(352.24597, 484.46002, 351.494, 484.894, 351.494, 486.82303);
        ((GeneralPath)shape).lineTo(351.494, 488.20602);
        ((GeneralPath)shape).curveTo(351.494, 489.96603, 351.87698, 490.66504, 354.03098, 490.58502);
        ((GeneralPath)shape).curveTo(355.51697, 490.57703, 356.494, 490.58502, 356.494, 488.786);
        ((GeneralPath)shape).lineTo(356.494, 488.39102);
        ((GeneralPath)shape).lineTo(355.369, 488.39102);
        ((GeneralPath)shape).lineTo(355.369, 488.721);
        ((GeneralPath)shape).curveTo(355.369, 489.584, 354.94498, 489.584, 353.998, 489.584);
        ((GeneralPath)shape).curveTo(352.766, 489.584, 352.619, 489.35202, 352.619, 488.152);
        ((GeneralPath)shape).lineTo(352.619, 486.824);
        ((GeneralPath)shape).curveTo(352.619, 485.646, 352.828, 485.45502, 354.03497, 485.45502);
        ((GeneralPath)shape).curveTo(355.08597, 485.45502, 355.369, 485.50403, 355.369, 486.20502);
        ((GeneralPath)shape).lineTo(355.369, 486.535);
        ((GeneralPath)shape).lineTo(356.494, 486.535);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_251;
        g.setTransform(defaultTransform__0_251);
        g.setClip(clip__0_251);
        float alpha__0_252 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_252 = g.getClip();
        AffineTransform defaultTransform__0_252 = g.getTransform();
        
//      _0_252 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(360.735, 488.585);
        ((GeneralPath)shape).lineTo(358.65298, 488.585);
        ((GeneralPath)shape).lineTo(359.68198, 485.35098);
        ((GeneralPath)shape).lineTo(359.69797, 485.35098);
        ((GeneralPath)shape).lineTo(360.735, 488.585);
        ((GeneralPath)shape).moveTo(360.993, 489.46);
        ((GeneralPath)shape).lineTo(361.388, 490.585);
        ((GeneralPath)shape).lineTo(362.562, 490.585);
        ((GeneralPath)shape).lineTo(360.519, 484.46);
        ((GeneralPath)shape).lineTo(358.81403, 484.46);
        ((GeneralPath)shape).lineTo(356.812, 490.585);
        ((GeneralPath)shape).lineTo(358.01102, 490.585);
        ((GeneralPath)shape).lineTo(358.38803, 489.46);
        ((GeneralPath)shape).lineTo(360.993, 489.46);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_252;
        g.setTransform(defaultTransform__0_252);
        g.setClip(clip__0_252);
        float alpha__0_253 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_253 = g.getClip();
        AffineTransform defaultTransform__0_253 = g.getTransform();
        
//      _0_253 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(364.862, 485.456);
        ((GeneralPath)shape).lineTo(366.622, 485.456);
        ((GeneralPath)shape).lineTo(366.622, 484.46);
        ((GeneralPath)shape).lineTo(361.979, 484.46);
        ((GeneralPath)shape).lineTo(361.979, 485.456);
        ((GeneralPath)shape).lineTo(363.737, 485.456);
        ((GeneralPath)shape).lineTo(363.737, 490.585);
        ((GeneralPath)shape).lineTo(364.862, 490.585);
        ((GeneralPath)shape).lineTo(364.862, 485.456);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_253;
        g.setTransform(defaultTransform__0_253);
        g.setClip(clip__0_253);
        float alpha__0_254 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_254 = g.getClip();
        AffineTransform defaultTransform__0_254 = g.getTransform();
        
//      _0_254 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new Rectangle2D.Double(367.1839904785156, 484.4599914550781, 1.125, 6.125);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_254;
        g.setTransform(defaultTransform__0_254);
        g.setClip(clip__0_254);
        float alpha__0_255 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_255 = g.getClip();
        AffineTransform defaultTransform__0_255 = g.getTransform();
        
//      _0_255 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(371.731, 485.456);
        ((GeneralPath)shape).curveTo(372.73898, 485.456, 373.141, 485.53598, 373.141, 486.665);
        ((GeneralPath)shape).lineTo(373.141, 488.28);
        ((GeneralPath)shape).curveTo(373.141, 489.44, 372.78598, 489.585, 371.754, 489.585);
        ((GeneralPath)shape).curveTo(370.619, 489.585, 370.391, 489.474, 370.391, 488.28);
        ((GeneralPath)shape).lineTo(370.391, 486.665);
        ((GeneralPath)shape).curveTo(370.392, 485.712, 370.511, 485.456, 371.731, 485.456);
        ((GeneralPath)shape).moveTo(371.755, 484.46);
        ((GeneralPath)shape).curveTo(369.88602, 484.46, 369.267, 484.78998, 369.267, 486.663);
        ((GeneralPath)shape).lineTo(369.267, 488.28598);
        ((GeneralPath)shape).curveTo(369.267, 490.26297, 369.935, 490.585, 371.755, 490.585);
        ((GeneralPath)shape).curveTo(373.536, 490.585, 374.267, 490.216, 374.267, 488.28598);
        ((GeneralPath)shape).lineTo(374.267, 486.663);
        ((GeneralPath)shape).curveTo(374.267, 484.726, 373.45, 484.46, 371.755, 484.46);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_255;
        g.setTransform(defaultTransform__0_255);
        g.setClip(clip__0_255);
        float alpha__0_256 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_256 = g.getClip();
        AffineTransform defaultTransform__0_256 = g.getTransform();
        
//      _0_256 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(379.611, 489.589);
        ((GeneralPath)shape).lineTo(379.611, 489.589);
        ((GeneralPath)shape).lineTo(377.016, 484.46);
        ((GeneralPath)shape).lineTo(375.111, 484.46);
        ((GeneralPath)shape).lineTo(375.111, 490.585);
        ((GeneralPath)shape).lineTo(376.236, 490.585);
        ((GeneralPath)shape).lineTo(376.236, 485.456);
        ((GeneralPath)shape).lineTo(378.824, 490.585);
        ((GeneralPath)shape).lineTo(380.736, 490.585);
        ((GeneralPath)shape).lineTo(380.736, 484.46);
        ((GeneralPath)shape).lineTo(379.611, 484.46);
        ((GeneralPath)shape).lineTo(379.611, 489.589);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_256;
        g.setTransform(defaultTransform__0_256);
        g.setClip(clip__0_256);
        float alpha__0_257 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_257 = g.getClip();
        AffineTransform defaultTransform__0_257 = g.getTransform();
        
//      _0_257 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(381.478, 488.562);
        ((GeneralPath)shape).lineTo(381.478, 488.845);
        ((GeneralPath)shape).curveTo(381.478, 490.585, 382.603, 490.585, 383.986, 490.585);
        ((GeneralPath)shape).curveTo(385.546, 490.585, 386.353, 490.439, 386.353, 488.714);
        ((GeneralPath)shape).curveTo(386.353, 487.15298, 385.844, 487.056, 383.978, 486.96);
        ((GeneralPath)shape).curveTo(382.761, 486.88998, 382.603, 486.866, 382.603, 486.149);
        ((GeneralPath)shape).curveTo(382.603, 485.524, 382.79498, 485.41498, 383.986, 485.41498);
        ((GeneralPath)shape).curveTo(384.82898, 485.41498, 385.103, 485.43997, 385.103, 486.136);
        ((GeneralPath)shape).lineTo(385.103, 486.343);
        ((GeneralPath)shape).lineTo(386.228, 486.343);
        ((GeneralPath)shape).lineTo(386.228, 486.138);
        ((GeneralPath)shape).curveTo(386.228, 484.46, 385.219, 484.46, 383.982, 484.46);
        ((GeneralPath)shape).curveTo(382.53, 484.46, 381.478, 484.46, 381.478, 486.112);
        ((GeneralPath)shape).curveTo(381.478, 487.862, 382.508, 487.813, 383.82098, 487.893);
        ((GeneralPath)shape).curveTo(384.754, 487.94202, 385.228, 487.829, 385.228, 488.69);
        ((GeneralPath)shape).curveTo(385.228, 489.391, 385.08, 489.585, 384.001, 489.585);
        ((GeneralPath)shape).curveTo(382.907, 489.585, 382.603, 489.53598, 382.603, 488.845);
        ((GeneralPath)shape).lineTo(382.603, 488.562);
        ((GeneralPath)shape).lineTo(381.478, 488.562);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_257;
        g.setTransform(defaultTransform__0_257);
        g.setClip(clip__0_257);
        float alpha__0_258 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_258 = g.getClip();
        AffineTransform defaultTransform__0_258 = g.getTransform();
        
//      _0_258 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(393.352, 488.585);
        ((GeneralPath)shape).lineTo(391.27, 488.585);
        ((GeneralPath)shape).lineTo(392.29898, 485.35098);
        ((GeneralPath)shape).lineTo(392.31497, 485.35098);
        ((GeneralPath)shape).lineTo(393.352, 488.585);
        ((GeneralPath)shape).moveTo(393.609, 489.46);
        ((GeneralPath)shape).lineTo(394.004, 490.585);
        ((GeneralPath)shape).lineTo(395.178, 490.585);
        ((GeneralPath)shape).lineTo(393.135, 484.46);
        ((GeneralPath)shape).lineTo(391.43002, 484.46);
        ((GeneralPath)shape).lineTo(389.428, 490.585);
        ((GeneralPath)shape).lineTo(390.627, 490.585);
        ((GeneralPath)shape).lineTo(391.00403, 489.46);
        ((GeneralPath)shape).lineTo(393.609, 489.46);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_258;
        g.setTransform(defaultTransform__0_258);
        g.setClip(clip__0_258);
        float alpha__0_259 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_259 = g.getClip();
        AffineTransform defaultTransform__0_259 = g.getTransform();
        
//      _0_259 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(400.1, 489.589);
        ((GeneralPath)shape).lineTo(400.1, 489.589);
        ((GeneralPath)shape).lineTo(397.504, 484.46);
        ((GeneralPath)shape).lineTo(395.6, 484.46);
        ((GeneralPath)shape).lineTo(395.6, 490.585);
        ((GeneralPath)shape).lineTo(396.725, 490.585);
        ((GeneralPath)shape).lineTo(396.725, 485.456);
        ((GeneralPath)shape).lineTo(399.313, 490.585);
        ((GeneralPath)shape).lineTo(401.225, 490.585);
        ((GeneralPath)shape).lineTo(401.225, 484.46);
        ((GeneralPath)shape).lineTo(400.1, 484.46);
        ((GeneralPath)shape).lineTo(400.1, 489.589);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_259;
        g.setTransform(defaultTransform__0_259);
        g.setClip(clip__0_259);
        float alpha__0_260 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_260 = g.getClip();
        AffineTransform defaultTransform__0_260 = g.getTransform();
        
//      _0_260 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(403.341, 489.589);
        ((GeneralPath)shape).lineTo(403.341, 485.456);
        ((GeneralPath)shape).lineTo(405.101, 485.456);
        ((GeneralPath)shape).curveTo(406.00302, 485.456, 406.341, 485.71, 406.341, 486.735);
        ((GeneralPath)shape).lineTo(406.341, 488.18597);
        ((GeneralPath)shape).curveTo(406.341, 488.87497, 406.095, 489.58798, 405.298, 489.58798);
        ((GeneralPath)shape).lineTo(403.341, 489.58798);
        ((GeneralPath)shape).moveTo(402.216, 490.585);
        ((GeneralPath)shape).lineTo(405.286, 490.585);
        ((GeneralPath)shape).curveTo(407.112, 490.585, 407.466, 489.45798, 407.466, 488.185);
        ((GeneralPath)shape).lineTo(407.466, 486.74);
        ((GeneralPath)shape).curveTo(407.466, 485.074, 406.759, 484.461, 405.095, 484.461);
        ((GeneralPath)shape).lineTo(402.216, 484.461);
        ((GeneralPath)shape).lineTo(402.216, 490.585);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_260;
        g.setTransform(defaultTransform__0_260);
        g.setClip(clip__0_260);
        float alpha__0_261 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_261 = g.getClip();
        AffineTransform defaultTransform__0_261 = g.getTransform();
        
//      _0_261 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(415.813, 486.536);
        ((GeneralPath)shape).lineTo(415.813, 486.204);
        ((GeneralPath)shape).curveTo(415.733, 484.46002, 414.866, 484.46002, 413.35397, 484.46002);
        ((GeneralPath)shape).curveTo(411.56497, 484.46002, 410.813, 484.894, 410.813, 486.82303);
        ((GeneralPath)shape).lineTo(410.813, 488.20602);
        ((GeneralPath)shape).curveTo(410.813, 489.96603, 411.19598, 490.66504, 413.34998, 490.58502);
        ((GeneralPath)shape).curveTo(414.83597, 490.57703, 415.813, 490.58502, 415.813, 488.786);
        ((GeneralPath)shape).lineTo(415.813, 488.39102);
        ((GeneralPath)shape).lineTo(414.688, 488.39102);
        ((GeneralPath)shape).lineTo(414.688, 488.721);
        ((GeneralPath)shape).curveTo(414.688, 489.584, 414.26398, 489.584, 413.317, 489.584);
        ((GeneralPath)shape).curveTo(412.085, 489.584, 411.938, 489.35202, 411.938, 488.152);
        ((GeneralPath)shape).lineTo(411.938, 486.824);
        ((GeneralPath)shape).curveTo(411.938, 485.646, 412.147, 485.45502, 413.35397, 485.45502);
        ((GeneralPath)shape).curveTo(414.40497, 485.45502, 414.688, 485.50403, 414.688, 486.20502);
        ((GeneralPath)shape).lineTo(414.688, 486.535);
        ((GeneralPath)shape).lineTo(415.813, 486.535);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_261;
        g.setTransform(defaultTransform__0_261);
        g.setClip(clip__0_261);
        float alpha__0_262 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_262 = g.getClip();
        AffineTransform defaultTransform__0_262 = g.getTransform();
        
//      _0_262 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(417.757, 487.585);
        ((GeneralPath)shape).lineTo(417.757, 485.456);
        ((GeneralPath)shape).lineTo(419.31, 485.456);
        ((GeneralPath)shape).curveTo(420.109, 485.456, 420.257, 485.61398, 420.257, 486.495);
        ((GeneralPath)shape).curveTo(420.257, 487.41098, 420.056, 487.585, 419.232, 487.585);
        ((GeneralPath)shape).lineTo(417.757, 487.585);
        ((GeneralPath)shape).moveTo(419.505, 488.585);
        ((GeneralPath)shape).curveTo(420.052, 488.585, 420.25702, 488.942, 420.25702, 489.452);
        ((GeneralPath)shape).lineTo(420.25702, 490.585);
        ((GeneralPath)shape).lineTo(421.38202, 490.585);
        ((GeneralPath)shape).lineTo(421.38202, 489.45398);
        ((GeneralPath)shape).curveTo(421.38202, 488.59897, 421.17902, 488.15298, 420.34702, 488.081);
        ((GeneralPath)shape).lineTo(420.34702, 488.048);
        ((GeneralPath)shape).curveTo(421.38202, 487.89, 421.38202, 487.21802, 421.38202, 486.296);
        ((GeneralPath)shape).curveTo(421.38202, 484.884, 420.89203, 484.46, 419.614, 484.46);
        ((GeneralPath)shape).lineTo(416.63202, 484.46);
        ((GeneralPath)shape).lineTo(416.63202, 490.585);
        ((GeneralPath)shape).lineTo(417.75702, 490.585);
        ((GeneralPath)shape).lineTo(417.75702, 488.585);
        ((GeneralPath)shape).lineTo(419.505, 488.585);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_262;
        g.setTransform(defaultTransform__0_262);
        g.setClip(clip__0_262);
        float alpha__0_263 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_263 = g.getClip();
        AffineTransform defaultTransform__0_263 = g.getTransform();
        
//      _0_263 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new Rectangle2D.Double(422.4079895019531, 484.4599914550781, 1.125, 6.125);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_263;
        g.setTransform(defaultTransform__0_263);
        g.setClip(clip__0_263);
        float alpha__0_264 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_264 = g.getClip();
        AffineTransform defaultTransform__0_264 = g.getTransform();
        
//      _0_264 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(426.99, 485.456);
        ((GeneralPath)shape).lineTo(428.75, 485.456);
        ((GeneralPath)shape).lineTo(428.75, 484.46);
        ((GeneralPath)shape).lineTo(424.106, 484.46);
        ((GeneralPath)shape).lineTo(424.106, 485.456);
        ((GeneralPath)shape).lineTo(425.865, 485.456);
        ((GeneralPath)shape).lineTo(425.865, 490.585);
        ((GeneralPath)shape).lineTo(426.99, 490.585);
        ((GeneralPath)shape).lineTo(426.99, 485.456);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_264;
        g.setTransform(defaultTransform__0_264);
        g.setClip(clip__0_264);
        float alpha__0_265 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_265 = g.getClip();
        AffineTransform defaultTransform__0_265 = g.getTransform();
        
//      _0_265 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new Rectangle2D.Double(429.31201171875, 484.4599914550781, 1.125, 6.125);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_265;
        g.setTransform(defaultTransform__0_265);
        g.setClip(clip__0_265);
        float alpha__0_266 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_266 = g.getClip();
        AffineTransform defaultTransform__0_266 = g.getTransform();
        
//      _0_266 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(436.27, 486.536);
        ((GeneralPath)shape).lineTo(436.27, 486.204);
        ((GeneralPath)shape).curveTo(436.19, 484.46002, 435.323, 484.46002, 433.81097, 484.46002);
        ((GeneralPath)shape).curveTo(432.02197, 484.46002, 431.27, 484.894, 431.27, 486.82303);
        ((GeneralPath)shape).lineTo(431.27, 488.20602);
        ((GeneralPath)shape).curveTo(431.27, 489.96603, 431.65298, 490.66504, 433.80698, 490.58502);
        ((GeneralPath)shape).curveTo(435.29297, 490.57703, 436.27, 490.58502, 436.27, 488.786);
        ((GeneralPath)shape).lineTo(436.27, 488.39102);
        ((GeneralPath)shape).lineTo(435.145, 488.39102);
        ((GeneralPath)shape).lineTo(435.145, 488.721);
        ((GeneralPath)shape).curveTo(435.145, 489.584, 434.72098, 489.584, 433.774, 489.584);
        ((GeneralPath)shape).curveTo(432.542, 489.584, 432.395, 489.35202, 432.395, 488.152);
        ((GeneralPath)shape).lineTo(432.395, 486.824);
        ((GeneralPath)shape).curveTo(432.395, 485.646, 432.604, 485.45502, 433.81097, 485.45502);
        ((GeneralPath)shape).curveTo(434.86197, 485.45502, 435.145, 485.50403, 435.145, 486.20502);
        ((GeneralPath)shape).lineTo(435.145, 486.535);
        ((GeneralPath)shape).lineTo(436.27, 486.535);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_266;
        g.setTransform(defaultTransform__0_266);
        g.setClip(clip__0_266);
        float alpha__0_267 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_267 = g.getClip();
        AffineTransform defaultTransform__0_267 = g.getTransform();
        
//      _0_267 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(440.512, 488.585);
        ((GeneralPath)shape).lineTo(438.43, 488.585);
        ((GeneralPath)shape).lineTo(439.45898, 485.35098);
        ((GeneralPath)shape).lineTo(439.47498, 485.35098);
        ((GeneralPath)shape).lineTo(440.512, 488.585);
        ((GeneralPath)shape).moveTo(440.77, 489.46);
        ((GeneralPath)shape).lineTo(441.16498, 490.585);
        ((GeneralPath)shape).lineTo(442.339, 490.585);
        ((GeneralPath)shape).lineTo(440.296, 484.46);
        ((GeneralPath)shape).lineTo(438.591, 484.46);
        ((GeneralPath)shape).lineTo(436.589, 490.585);
        ((GeneralPath)shape).lineTo(437.788, 490.585);
        ((GeneralPath)shape).lineTo(438.165, 489.46);
        ((GeneralPath)shape).lineTo(440.77, 489.46);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_267;
        g.setTransform(defaultTransform__0_267);
        g.setClip(clip__0_267);
        float alpha__0_268 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_268 = g.getClip();
        AffineTransform defaultTransform__0_268 = g.getTransform();
        
//      _0_268 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(443.885, 484.46);
        ((GeneralPath)shape).lineTo(442.76, 484.46);
        ((GeneralPath)shape).lineTo(442.76, 490.585);
        ((GeneralPath)shape).lineTo(446.686, 490.585);
        ((GeneralPath)shape).lineTo(446.686, 489.589);
        ((GeneralPath)shape).lineTo(443.885, 489.589);
        ((GeneralPath)shape).lineTo(443.885, 484.46);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_268;
        g.setTransform(defaultTransform__0_268);
        g.setClip(clip__0_268);
        float alpha__0_269 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_269 = g.getClip();
        AffineTransform defaultTransform__0_269 = g.getTransform();
        
//      _0_269 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(455.221, 484.46);
        ((GeneralPath)shape).lineTo(454.096, 484.46);
        ((GeneralPath)shape).lineTo(454.096, 486.96);
        ((GeneralPath)shape).lineTo(451.221, 486.96);
        ((GeneralPath)shape).lineTo(451.221, 484.46);
        ((GeneralPath)shape).lineTo(450.096, 484.46);
        ((GeneralPath)shape).lineTo(450.096, 490.585);
        ((GeneralPath)shape).lineTo(451.221, 490.585);
        ((GeneralPath)shape).lineTo(451.221, 487.96);
        ((GeneralPath)shape).lineTo(454.096, 487.96);
        ((GeneralPath)shape).lineTo(454.096, 490.585);
        ((GeneralPath)shape).lineTo(455.221, 490.585);
        ((GeneralPath)shape).lineTo(455.221, 484.46);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_269;
        g.setTransform(defaultTransform__0_269);
        g.setClip(clip__0_269);
        float alpha__0_270 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_270 = g.getClip();
        AffineTransform defaultTransform__0_270 = g.getTransform();
        
//      _0_270 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new Rectangle2D.Double(456.24798583984375, 484.4599914550781, 1.125, 6.125);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_270;
        g.setTransform(defaultTransform__0_270);
        g.setClip(clip__0_270);
        float alpha__0_271 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_271 = g.getClip();
        AffineTransform defaultTransform__0_271 = g.getTransform();
        
//      _0_271 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(460.831, 485.456);
        ((GeneralPath)shape).lineTo(462.59, 485.456);
        ((GeneralPath)shape).lineTo(462.59, 484.46);
        ((GeneralPath)shape).lineTo(457.946, 484.46);
        ((GeneralPath)shape).lineTo(457.946, 485.456);
        ((GeneralPath)shape).lineTo(459.706, 485.456);
        ((GeneralPath)shape).lineTo(459.706, 490.585);
        ((GeneralPath)shape).lineTo(460.831, 490.585);
        ((GeneralPath)shape).lineTo(460.831, 485.456);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_271;
        g.setTransform(defaultTransform__0_271);
        g.setClip(clip__0_271);
        float alpha__0_272 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_272 = g.getClip();
        AffineTransform defaultTransform__0_272 = g.getTransform();
        
//      _0_272 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(462.901, 488.562);
        ((GeneralPath)shape).lineTo(462.901, 488.845);
        ((GeneralPath)shape).curveTo(462.901, 490.585, 464.026, 490.585, 465.409, 490.585);
        ((GeneralPath)shape).curveTo(466.968, 490.585, 467.776, 490.439, 467.776, 488.714);
        ((GeneralPath)shape).curveTo(467.776, 487.15298, 467.266, 487.056, 465.401, 486.96);
        ((GeneralPath)shape).curveTo(464.184, 486.88998, 464.026, 486.866, 464.026, 486.149);
        ((GeneralPath)shape).curveTo(464.026, 485.524, 464.217, 485.41498, 465.409, 485.41498);
        ((GeneralPath)shape).curveTo(466.251, 485.41498, 466.526, 485.43997, 466.526, 486.136);
        ((GeneralPath)shape).lineTo(466.526, 486.343);
        ((GeneralPath)shape).lineTo(467.651, 486.343);
        ((GeneralPath)shape).lineTo(467.651, 486.138);
        ((GeneralPath)shape).curveTo(467.651, 484.46, 466.641, 484.46, 465.405, 484.46);
        ((GeneralPath)shape).curveTo(463.952, 484.46, 462.901, 484.46, 462.901, 486.112);
        ((GeneralPath)shape).curveTo(462.901, 487.862, 463.93, 487.813, 465.243, 487.893);
        ((GeneralPath)shape).curveTo(466.177, 487.94202, 466.651, 487.829, 466.651, 488.69);
        ((GeneralPath)shape).curveTo(466.651, 489.391, 466.503, 489.585, 465.424, 489.585);
        ((GeneralPath)shape).curveTo(464.33002, 489.585, 464.026, 489.53598, 464.026, 488.845);
        ((GeneralPath)shape).lineTo(464.026, 488.562);
        ((GeneralPath)shape).lineTo(462.901, 488.562);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_272;
        g.setTransform(defaultTransform__0_272);
        g.setClip(clip__0_272);
        float alpha__0_273 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_273 = g.getClip();
        AffineTransform defaultTransform__0_273 = g.getTransform();
        
//      _0_273 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(314.15, 498.02);
        ((GeneralPath)shape).lineTo(311.712, 498.02);
        ((GeneralPath)shape).curveTo(311.712, 497.40598, 311.973, 497.534, 312.897, 497.285);
        ((GeneralPath)shape).curveTo(313.852, 497.02402, 314.15, 496.829, 314.15, 495.808);
        ((GeneralPath)shape).curveTo(314.15, 494.526, 313.432, 494.37302, 312.37198, 494.37302);
        ((GeneralPath)shape).curveTo(311.22098, 494.37302, 310.58798, 494.61002, 310.58798, 495.85);
        ((GeneralPath)shape).lineTo(310.58798, 496.075);
        ((GeneralPath)shape).lineTo(311.71298, 496.075);
        ((GeneralPath)shape).lineTo(311.71298, 495.85);
        ((GeneralPath)shape).curveTo(311.71298, 495.364, 311.801, 495.322, 312.369, 495.322);
        ((GeneralPath)shape).curveTo(312.86798, 495.322, 313.025, 495.37598, 313.025, 495.832);
        ((GeneralPath)shape).curveTo(313.025, 496.299, 312.97, 496.286, 312.193, 496.469);
        ((GeneralPath)shape).curveTo(310.813, 496.79, 310.58798, 496.966, 310.58798, 498.022);
        ((GeneralPath)shape).lineTo(310.58798, 498.96802);
        ((GeneralPath)shape).lineTo(314.15097, 498.96802);
        ((GeneralPath)shape).lineTo(314.15097, 498.02);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_273;
        g.setTransform(defaultTransform__0_273);
        g.setClip(clip__0_273);
        float alpha__0_274 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_274 = g.getClip();
        AffineTransform defaultTransform__0_274 = g.getTransform();
        
//      _0_274 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(316.032, 495.43);
        ((GeneralPath)shape).lineTo(317.10202, 495.43);
        ((GeneralPath)shape).curveTo(317.531, 495.43, 317.71902, 495.55298, 317.71902, 496.15198);
        ((GeneralPath)shape).lineTo(317.71902, 497.12198);
        ((GeneralPath)shape).curveTo(317.71902, 497.61, 317.55402, 497.912, 317.10202, 497.912);
        ((GeneralPath)shape).lineTo(316.032, 497.912);
        ((GeneralPath)shape).lineTo(316.032, 495.43);
        ((GeneralPath)shape).moveTo(314.813, 498.967);
        ((GeneralPath)shape).lineTo(317.276, 498.967);
        ((GeneralPath)shape).curveTo(318.539, 498.967, 318.938, 498.372, 318.938, 497.117);
        ((GeneralPath)shape).lineTo(318.938, 496.156);
        ((GeneralPath)shape).curveTo(318.938, 494.85, 318.375, 494.37302, 317.112, 494.37302);
        ((GeneralPath)shape).lineTo(314.813, 494.37302);
        ((GeneralPath)shape).lineTo(314.813, 498.967);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_274;
        g.setTransform(defaultTransform__0_274);
        g.setClip(clip__0_274);
        float alpha__0_275 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_275 = g.getClip();
        AffineTransform defaultTransform__0_275 = g.getTransform();
        
//      _0_275 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(323.12, 495.77);
        ((GeneralPath)shape).lineTo(323.12, 495.58);
        ((GeneralPath)shape).curveTo(323.12, 494.41998, 322.297, 494.374, 321.38, 494.374);
        ((GeneralPath)shape).curveTo(320.09702, 494.374, 319.558, 494.61, 319.558, 496.008);
        ((GeneralPath)shape).lineTo(319.558, 497.142);
        ((GeneralPath)shape).curveTo(319.558, 498.666, 319.928, 498.968, 321.39902, 498.968);
        ((GeneralPath)shape).curveTo(322.54602, 498.968, 323.121, 498.741, 323.121, 497.46198);
        ((GeneralPath)shape).curveTo(323.121, 496.26898, 322.569, 496.14597, 321.51102, 496.14597);
        ((GeneralPath)shape).curveTo(321.122, 496.14597, 320.85303, 496.14597, 320.696, 496.61896);
        ((GeneralPath)shape).lineTo(320.68402, 496.61896);
        ((GeneralPath)shape).lineTo(320.68402, 495.92795);
        ((GeneralPath)shape).curveTo(320.68402, 495.34695, 320.84302, 495.32394, 321.381, 495.32394);
        ((GeneralPath)shape).curveTo(321.819, 495.32394, 321.996, 495.34494, 321.996, 495.77194);
        ((GeneralPath)shape).lineTo(323.12, 495.77194);
        ((GeneralPath)shape).moveTo(321.26, 497.092);
        ((GeneralPath)shape).curveTo(321.795, 497.092, 321.995, 497.092, 321.995, 497.487);
        ((GeneralPath)shape).curveTo(321.995, 498.0, 321.91, 498.03, 321.263, 498.03);
        ((GeneralPath)shape).curveTo(320.756, 498.03, 320.683, 497.942, 320.683, 497.508);
        ((GeneralPath)shape).curveTo(320.683, 497.136, 320.814, 497.092, 321.26, 497.092);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_275;
        g.setTransform(defaultTransform__0_275);
        g.setClip(clip__0_275);
        float alpha__0_276 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_276 = g.getClip();
        AffineTransform defaultTransform__0_276 = g.getTransform();
        
//      _0_276 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(328.45, 494.373);
        ((GeneralPath)shape).lineTo(327.231, 494.373);
        ((GeneralPath)shape).lineTo(327.231, 498.967);
        ((GeneralPath)shape).lineTo(330.328, 498.967);
        ((GeneralPath)shape).lineTo(330.328, 497.911);
        ((GeneralPath)shape).lineTo(328.45, 497.911);
        ((GeneralPath)shape).lineTo(328.45, 494.373);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_276;
        g.setTransform(defaultTransform__0_276);
        g.setClip(clip__0_276);
        float alpha__0_277 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_277 = g.getClip();
        AffineTransform defaultTransform__0_277 = g.getTransform();
        
//      _0_277 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(332.607, 497.936);
        ((GeneralPath)shape).curveTo(331.977, 497.936, 331.772, 497.871, 331.772, 497.19202);
        ((GeneralPath)shape).lineTo(331.772, 496.169);
        ((GeneralPath)shape).curveTo(331.772, 495.496, 331.977, 495.431, 332.607, 495.431);
        ((GeneralPath)shape).curveTo(333.236, 495.431, 333.46, 495.496, 333.46, 496.169);
        ((GeneralPath)shape).lineTo(333.46, 497.19202);
        ((GeneralPath)shape).curveTo(333.46, 497.87, 333.236, 497.936, 332.607, 497.936);
        ((GeneralPath)shape).moveTo(332.616, 498.967);
        ((GeneralPath)shape).curveTo(333.823, 498.967, 334.679, 498.702, 334.679, 497.30902);
        ((GeneralPath)shape).lineTo(334.679, 496.032);
        ((GeneralPath)shape).curveTo(334.679, 494.639, 333.82397, 494.37402, 332.616, 494.37402);
        ((GeneralPath)shape).curveTo(331.40802, 494.37402, 330.553, 494.64, 330.553, 496.032);
        ((GeneralPath)shape).lineTo(330.553, 497.30902);
        ((GeneralPath)shape).curveTo(330.554, 498.702, 331.409, 498.967, 332.616, 498.967);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_277;
        g.setTransform(defaultTransform__0_277);
        g.setClip(clip__0_277);
        float alpha__0_278 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_278 = g.getClip();
        AffineTransform defaultTransform__0_278 = g.getTransform();
        
//      _0_278 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(337.833, 497.234);
        ((GeneralPath)shape).lineTo(337.833, 497.367);
        ((GeneralPath)shape).curveTo(337.833, 497.89902, 337.591, 497.935, 337.08902, 497.935);
        ((GeneralPath)shape).curveTo(336.363, 497.935, 336.333, 497.674, 336.333, 496.997);
        ((GeneralPath)shape).lineTo(336.333, 496.289);
        ((GeneralPath)shape).curveTo(336.333, 495.642, 336.4, 495.43, 337.092, 495.43);
        ((GeneralPath)shape).curveTo(337.505, 495.43, 337.79, 495.46, 337.833, 495.867);
        ((GeneralPath)shape).lineTo(337.833, 496.04102);
        ((GeneralPath)shape).lineTo(339.052, 496.04102);
        ((GeneralPath)shape).lineTo(339.052, 495.86502);
        ((GeneralPath)shape).curveTo(339.052, 494.502, 338.475, 494.37402, 337.237, 494.37402);
        ((GeneralPath)shape).curveTo(335.915, 494.37402, 335.115, 494.59103, 335.115, 496.08704);
        ((GeneralPath)shape).lineTo(335.115, 497.25604);
        ((GeneralPath)shape).curveTo(335.115, 498.90805, 335.99698, 498.96805, 337.22498, 498.96805);
        ((GeneralPath)shape).curveTo(337.75397, 498.96805, 338.24597, 498.96805, 338.649, 498.63403);
        ((GeneralPath)shape).curveTo(339.052, 498.29602, 339.052, 497.85504, 339.052, 497.36502);
        ((GeneralPath)shape).lineTo(339.052, 497.23502);
        ((GeneralPath)shape).lineTo(337.833, 497.23502);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_278;
        g.setTransform(defaultTransform__0_278);
        g.setClip(clip__0_278);
        float alpha__0_279 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_279 = g.getClip();
        AffineTransform defaultTransform__0_279 = g.getTransform();
        
//      _0_279 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(342.02, 497.373);
        ((GeneralPath)shape).lineTo(340.914, 497.373);
        ((GeneralPath)shape).lineTo(341.455, 495.284);
        ((GeneralPath)shape).lineTo(341.468, 495.284);
        ((GeneralPath)shape).lineTo(342.02, 497.373);
        ((GeneralPath)shape).moveTo(342.257, 498.217);
        ((GeneralPath)shape).lineTo(342.48798, 498.967);
        ((GeneralPath)shape).lineTo(343.701, 498.967);
        ((GeneralPath)shape).lineTo(342.34598, 494.37302);
        ((GeneralPath)shape).lineTo(340.53796, 494.37302);
        ((GeneralPath)shape).lineTo(339.20096, 498.967);
        ((GeneralPath)shape).lineTo(340.43695, 498.967);
        ((GeneralPath)shape).lineTo(340.65695, 498.217);
        ((GeneralPath)shape).lineTo(342.257, 498.217);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_279;
        g.setTransform(defaultTransform__0_279);
        g.setClip(clip__0_279);
        float alpha__0_280 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_280 = g.getClip();
        AffineTransform defaultTransform__0_280 = g.getTransform();
        
//      _0_280 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(345.862, 495.43);
        ((GeneralPath)shape).lineTo(347.074, 495.43);
        ((GeneralPath)shape).lineTo(347.074, 494.373);
        ((GeneralPath)shape).lineTo(343.492, 494.373);
        ((GeneralPath)shape).lineTo(343.492, 495.43);
        ((GeneralPath)shape).lineTo(344.644, 495.43);
        ((GeneralPath)shape).lineTo(344.644, 498.967);
        ((GeneralPath)shape).lineTo(345.862, 498.967);
        ((GeneralPath)shape).lineTo(345.862, 495.43);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_280;
        g.setTransform(defaultTransform__0_280);
        g.setClip(clip__0_280);
        float alpha__0_281 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_281 = g.getClip();
        AffineTransform defaultTransform__0_281 = g.getTransform();
        
//      _0_281 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new Rectangle2D.Double(347.4639892578125, 494.37298583984375, 1.218999981880188, 4.593999862670898);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_281;
        g.setTransform(defaultTransform__0_281);
        g.setClip(clip__0_281);
        float alpha__0_282 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_282 = g.getClip();
        AffineTransform defaultTransform__0_282 = g.getTransform();
        
//      _0_282 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(351.273, 497.936);
        ((GeneralPath)shape).curveTo(350.643, 497.936, 350.43802, 497.871, 350.43802, 497.19202);
        ((GeneralPath)shape).lineTo(350.43802, 496.169);
        ((GeneralPath)shape).curveTo(350.43802, 495.496, 350.643, 495.431, 351.273, 495.431);
        ((GeneralPath)shape).curveTo(351.902, 495.431, 352.126, 495.496, 352.126, 496.169);
        ((GeneralPath)shape).lineTo(352.126, 497.19202);
        ((GeneralPath)shape).curveTo(352.126, 497.87, 351.902, 497.936, 351.273, 497.936);
        ((GeneralPath)shape).moveTo(351.282, 498.967);
        ((GeneralPath)shape).curveTo(352.489, 498.967, 353.345, 498.702, 353.345, 497.30902);
        ((GeneralPath)shape).lineTo(353.345, 496.032);
        ((GeneralPath)shape).curveTo(353.345, 494.639, 352.49, 494.37402, 351.282, 494.37402);
        ((GeneralPath)shape).curveTo(350.07404, 494.37402, 349.21902, 494.64, 349.21902, 496.032);
        ((GeneralPath)shape).lineTo(349.21902, 497.30902);
        ((GeneralPath)shape).curveTo(349.22, 498.702, 350.075, 498.967, 351.282, 498.967);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_282;
        g.setTransform(defaultTransform__0_282);
        g.setClip(clip__0_282);
        float alpha__0_283 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_283 = g.getClip();
        AffineTransform defaultTransform__0_283 = g.getTransform();
        
//      _0_283 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(355.093, 495.43);
        ((GeneralPath)shape).lineTo(355.093, 495.43);
        ((GeneralPath)shape).lineTo(356.377, 498.967);
        ((GeneralPath)shape).lineTo(358.374, 498.967);
        ((GeneralPath)shape).lineTo(358.374, 494.373);
        ((GeneralPath)shape).lineTo(357.249, 494.373);
        ((GeneralPath)shape).lineTo(357.249, 497.911);
        ((GeneralPath)shape).lineTo(355.945, 494.373);
        ((GeneralPath)shape).lineTo(353.968, 494.373);
        ((GeneralPath)shape).lineTo(353.968, 498.967);
        ((GeneralPath)shape).lineTo(355.093, 498.967);
        ((GeneralPath)shape).lineTo(355.093, 495.43);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_283;
        g.setTransform(defaultTransform__0_283);
        g.setClip(clip__0_283);
        float alpha__0_284 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_284 = g.getClip();
        AffineTransform defaultTransform__0_284 = g.getTransform();
        
//      _0_284 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(369.231, 494.373);
        ((GeneralPath)shape).lineTo(367.81, 494.373);
        ((GeneralPath)shape).lineTo(366.172, 495.749);
        ((GeneralPath)shape).lineTo(366.849, 496.535);
        ((GeneralPath)shape).lineTo(368.013, 495.448);
        ((GeneralPath)shape).lineTo(368.013, 498.967);
        ((GeneralPath)shape).lineTo(369.231, 498.967);
        ((GeneralPath)shape).lineTo(369.231, 494.373);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_284;
        g.setTransform(defaultTransform__0_284);
        g.setClip(clip__0_284);
        float alpha__0_285 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_285 = g.getClip();
        AffineTransform defaultTransform__0_285 = g.getTransform();
        
//      _0_285 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(373.304, 496.722);
        ((GeneralPath)shape).lineTo(373.304, 496.56998);
        ((GeneralPath)shape).curveTo(373.304, 495.77997, 372.487, 495.77997, 371.909, 495.77997);
        ((GeneralPath)shape).curveTo(371.03198, 495.77997, 370.36398, 495.77997, 370.36398, 496.79898);
        ((GeneralPath)shape).curveTo(370.36398, 497.44397, 370.59598, 497.73697, 371.896, 497.73697);
        ((GeneralPath)shape).curveTo(372.249, 497.73697, 372.426, 497.73697, 372.426, 498.04196);
        ((GeneralPath)shape).curveTo(372.426, 498.28195, 372.356, 498.31195, 371.896, 498.31195);
        ((GeneralPath)shape).curveTo(371.648, 498.31195, 371.395, 498.31195, 371.395, 497.96594);
        ((GeneralPath)shape).lineTo(370.36398, 497.96594);
        ((GeneralPath)shape).curveTo(370.36398, 498.96796, 371.111, 498.96796, 371.896, 498.96796);
        ((GeneralPath)shape).curveTo(372.734, 498.96796, 373.458, 498.83295, 373.458, 497.92697);
        ((GeneralPath)shape).curveTo(373.458, 497.06296, 372.978, 497.00397, 371.896, 496.98096);
        ((GeneralPath)shape).curveTo(371.40698, 496.96896, 371.395, 496.86096, 371.395, 496.70596);
        ((GeneralPath)shape).curveTo(371.395, 496.52097, 371.395, 496.45496, 371.909, 496.45496);
        ((GeneralPath)shape).curveTo(371.99, 496.45496, 372.081, 496.45496, 372.155, 496.48596);
        ((GeneralPath)shape).curveTo(372.228, 496.52197, 372.292, 496.58997, 372.309, 496.72296);
        ((GeneralPath)shape).lineTo(373.304, 496.72296);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_285;
        g.setTransform(defaultTransform__0_285);
        g.setClip(clip__0_285);
        float alpha__0_286 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_286 = g.getClip();
        AffineTransform defaultTransform__0_286 = g.getTransform();
        
//      _0_286 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(376.315, 495.779);
        ((GeneralPath)shape).lineTo(375.144, 495.779);
        ((GeneralPath)shape).lineTo(375.144, 495.10498);
        ((GeneralPath)shape).lineTo(374.113, 495.10498);
        ((GeneralPath)shape).lineTo(374.113, 495.779);
        ((GeneralPath)shape).lineTo(373.729, 495.779);
        ((GeneralPath)shape).lineTo(373.729, 496.544);
        ((GeneralPath)shape).lineTo(374.113, 496.544);
        ((GeneralPath)shape).lineTo(374.113, 498.09702);
        ((GeneralPath)shape).curveTo(374.113, 498.884, 374.655, 498.967, 375.308, 498.967);
        ((GeneralPath)shape).curveTo(376.14203, 498.967, 376.45602, 498.777, 376.45602, 497.923);
        ((GeneralPath)shape).lineTo(376.45602, 497.642);
        ((GeneralPath)shape).lineTo(375.61203, 497.642);
        ((GeneralPath)shape).lineTo(375.61203, 497.81);
        ((GeneralPath)shape).curveTo(375.61203, 498.026, 375.61203, 498.217, 375.35904, 498.217);
        ((GeneralPath)shape).curveTo(375.17703, 498.217, 375.14304, 498.14, 375.14304, 497.953);
        ((GeneralPath)shape).lineTo(375.14304, 496.544);
        ((GeneralPath)shape).lineTo(376.31403, 496.544);
        ((GeneralPath)shape).lineTo(376.31403, 495.779);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_286;
        g.setTransform(defaultTransform__0_286);
        g.setClip(clip__0_286);
        float alpha__0_287 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_287 = g.getClip();
        AffineTransform defaultTransform__0_287 = g.getTransform();
        
//      _0_287 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(383.121, 494.373);
        ((GeneralPath)shape).lineTo(381.902, 494.373);
        ((GeneralPath)shape).lineTo(381.902, 496.154);
        ((GeneralPath)shape).lineTo(380.309, 496.154);
        ((GeneralPath)shape).lineTo(380.309, 494.373);
        ((GeneralPath)shape).lineTo(379.09, 494.373);
        ((GeneralPath)shape).lineTo(379.09, 498.967);
        ((GeneralPath)shape).lineTo(380.309, 498.967);
        ((GeneralPath)shape).lineTo(380.309, 497.186);
        ((GeneralPath)shape).lineTo(381.902, 497.186);
        ((GeneralPath)shape).lineTo(381.902, 498.967);
        ((GeneralPath)shape).lineTo(383.121, 498.967);
        ((GeneralPath)shape).lineTo(383.121, 494.373);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_287;
        g.setTransform(defaultTransform__0_287);
        g.setClip(clip__0_287);
        float alpha__0_288 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_288 = g.getClip();
        AffineTransform defaultTransform__0_288 = g.getTransform();
        
//      _0_288 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new Rectangle2D.Double(383.8169860839844, 494.37298583984375, 1.218999981880188, 4.593999862670898);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_288;
        g.setTransform(defaultTransform__0_288);
        g.setClip(clip__0_288);
        float alpha__0_289 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_289 = g.getClip();
        AffineTransform defaultTransform__0_289 = g.getTransform();
        
//      _0_289 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(387.73, 495.43);
        ((GeneralPath)shape).lineTo(388.941, 495.43);
        ((GeneralPath)shape).lineTo(388.941, 494.373);
        ((GeneralPath)shape).lineTo(385.36, 494.373);
        ((GeneralPath)shape).lineTo(385.36, 495.43);
        ((GeneralPath)shape).lineTo(386.512, 495.43);
        ((GeneralPath)shape).lineTo(386.512, 498.967);
        ((GeneralPath)shape).lineTo(387.73, 498.967);
        ((GeneralPath)shape).lineTo(387.73, 495.43);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_289;
        g.setTransform(defaultTransform__0_289);
        g.setClip(clip__0_289);
        float alpha__0_290 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_290 = g.getClip();
        AffineTransform defaultTransform__0_290 = g.getTransform();
        
//      _0_290 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(414.212, 498.02);
        ((GeneralPath)shape).lineTo(411.77402, 498.02);
        ((GeneralPath)shape).curveTo(411.77402, 497.40598, 412.035, 497.534, 412.96002, 497.285);
        ((GeneralPath)shape).curveTo(413.915, 497.02402, 414.21204, 496.829, 414.21204, 495.808);
        ((GeneralPath)shape).curveTo(414.21204, 494.526, 413.49405, 494.37302, 412.43402, 494.37302);
        ((GeneralPath)shape).curveTo(411.28302, 494.37302, 410.65002, 494.61002, 410.65002, 495.85);
        ((GeneralPath)shape).lineTo(410.65002, 496.075);
        ((GeneralPath)shape).lineTo(411.77502, 496.075);
        ((GeneralPath)shape).lineTo(411.77502, 495.85);
        ((GeneralPath)shape).curveTo(411.77502, 495.364, 411.86304, 495.322, 412.43103, 495.322);
        ((GeneralPath)shape).curveTo(412.93103, 495.322, 413.08704, 495.37598, 413.08704, 495.832);
        ((GeneralPath)shape).curveTo(413.08704, 496.299, 413.03305, 496.286, 412.25504, 496.469);
        ((GeneralPath)shape).curveTo(410.87604, 496.79, 410.65002, 496.966, 410.65002, 498.022);
        ((GeneralPath)shape).lineTo(410.65002, 498.96802);
        ((GeneralPath)shape).lineTo(414.213, 498.96802);
        ((GeneralPath)shape).lineTo(414.213, 498.02);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_290;
        g.setTransform(defaultTransform__0_290);
        g.setClip(clip__0_290);
        float alpha__0_291 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_291 = g.getClip();
        AffineTransform defaultTransform__0_291 = g.getTransform();
        
//      _0_291 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(414.97, 495.779);
        ((GeneralPath)shape).lineTo(414.97, 498.96698);
        ((GeneralPath)shape).lineTo(416.001, 498.96698);
        ((GeneralPath)shape).lineTo(416.001, 497.21198);
        ((GeneralPath)shape).curveTo(416.001, 496.82397, 416.077, 496.54398, 416.528, 496.54398);
        ((GeneralPath)shape).curveTo(416.85602, 496.54398, 416.93802, 496.69598, 416.93802, 497.00598);
        ((GeneralPath)shape).lineTo(416.93802, 498.96698);
        ((GeneralPath)shape).lineTo(417.96902, 498.96698);
        ((GeneralPath)shape).lineTo(417.96902, 496.787);
        ((GeneralPath)shape).curveTo(417.96902, 496.10898, 417.65604, 495.779, 416.97003, 495.779);
        ((GeneralPath)shape).curveTo(416.73303, 495.779, 416.53802, 495.807, 416.38403, 495.896);
        ((GeneralPath)shape).curveTo(416.23102, 495.978, 416.11703, 496.124, 416.04105, 496.356);
        ((GeneralPath)shape).lineTo(416.00006, 496.356);
        ((GeneralPath)shape).lineTo(416.00006, 495.779);
        ((GeneralPath)shape).lineTo(414.97, 495.779);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_291;
        g.setTransform(defaultTransform__0_291);
        g.setClip(clip__0_291);
        float alpha__0_292 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_292 = g.getClip();
        AffineTransform defaultTransform__0_292 = g.getTransform();
        
//      _0_292 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(419.527, 497.402);
        ((GeneralPath)shape).curveTo(419.527, 496.778, 419.527, 496.581, 420.074, 496.581);
        ((GeneralPath)shape).curveTo(420.652, 496.581, 420.652, 496.79, 420.652, 497.402);
        ((GeneralPath)shape).curveTo(420.652, 498.114, 420.51602, 498.216, 420.074, 498.216);
        ((GeneralPath)shape).curveTo(419.627, 498.217, 419.527, 498.169, 419.527, 497.402);
        ((GeneralPath)shape).moveTo(421.684, 494.373);
        ((GeneralPath)shape).lineTo(420.65298, 494.373);
        ((GeneralPath)shape).lineTo(420.65298, 496.248);
        ((GeneralPath)shape).lineTo(420.615, 496.248);
        ((GeneralPath)shape).curveTo(420.444, 495.85397, 420.10498, 495.779, 419.719, 495.779);
        ((GeneralPath)shape).curveTo(418.836, 495.779, 418.497, 496.159, 418.497, 497.10898);
        ((GeneralPath)shape).lineTo(418.497, 497.82098);
        ((GeneralPath)shape).curveTo(418.497, 498.646, 418.939, 498.96698, 419.719, 498.96698);
        ((GeneralPath)shape).curveTo(420.181, 498.96698, 420.518, 498.86597, 420.641, 498.425);
        ((GeneralPath)shape).lineTo(420.654, 498.425);
        ((GeneralPath)shape).lineTo(420.654, 498.96698);
        ((GeneralPath)shape).lineTo(421.685, 498.96698);
        ((GeneralPath)shape).lineTo(421.685, 494.373);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_292;
        g.setTransform(defaultTransform__0_292);
        g.setClip(clip__0_292);
        float alpha__0_293 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_293 = g.getClip();
        AffineTransform defaultTransform__0_293 = g.getTransform();
        
//      _0_293 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(428.547, 494.373);
        ((GeneralPath)shape).lineTo(427.328, 494.373);
        ((GeneralPath)shape).lineTo(427.328, 496.154);
        ((GeneralPath)shape).lineTo(425.734, 496.154);
        ((GeneralPath)shape).lineTo(425.734, 494.373);
        ((GeneralPath)shape).lineTo(424.516, 494.373);
        ((GeneralPath)shape).lineTo(424.516, 498.967);
        ((GeneralPath)shape).lineTo(425.734, 498.967);
        ((GeneralPath)shape).lineTo(425.734, 497.186);
        ((GeneralPath)shape).lineTo(427.328, 497.186);
        ((GeneralPath)shape).lineTo(427.328, 498.967);
        ((GeneralPath)shape).lineTo(428.547, 498.967);
        ((GeneralPath)shape).lineTo(428.547, 494.373);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_293;
        g.setTransform(defaultTransform__0_293);
        g.setClip(clip__0_293);
        float alpha__0_294 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_294 = g.getClip();
        AffineTransform defaultTransform__0_294 = g.getTransform();
        
//      _0_294 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new Rectangle2D.Double(429.2439880371094, 494.37298583984375, 1.218999981880188, 4.593999862670898);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_294;
        g.setTransform(defaultTransform__0_294);
        g.setClip(clip__0_294);
        float alpha__0_295 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_295 = g.getClip();
        AffineTransform defaultTransform__0_295 = g.getTransform();
        
//      _0_295 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(433.156, 495.43);
        ((GeneralPath)shape).lineTo(434.368, 495.43);
        ((GeneralPath)shape).lineTo(434.368, 494.373);
        ((GeneralPath)shape).lineTo(430.786, 494.373);
        ((GeneralPath)shape).lineTo(430.786, 495.43);
        ((GeneralPath)shape).lineTo(431.938, 495.43);
        ((GeneralPath)shape).lineTo(431.938, 498.967);
        ((GeneralPath)shape).lineTo(433.156, 498.967);
        ((GeneralPath)shape).lineTo(433.156, 495.43);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_295;
        g.setTransform(defaultTransform__0_295);
        g.setClip(clip__0_295);
        float alpha__0_296 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_296 = g.getClip();
        AffineTransform defaultTransform__0_296 = g.getTransform();
        
//      _0_296 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(453.651, 497.092);
        ((GeneralPath)shape).lineTo(453.946, 497.092);
        ((GeneralPath)shape).curveTo(454.62402, 497.092, 454.682, 497.203, 454.682, 497.49902);
        ((GeneralPath)shape).curveTo(454.682, 498.006, 454.48502, 498.02902, 453.979, 498.02902);
        ((GeneralPath)shape).curveTo(453.547, 498.02902, 453.276, 498.006, 453.276, 497.51602);
        ((GeneralPath)shape).lineTo(453.276, 497.37103);
        ((GeneralPath)shape).lineTo(452.151, 497.37103);
        ((GeneralPath)shape).lineTo(452.151, 497.60803);
        ((GeneralPath)shape).curveTo(452.151, 498.87704, 452.988, 498.96503, 454.034, 498.96503);
        ((GeneralPath)shape).curveTo(455.051, 498.96503, 455.807, 498.80002, 455.807, 497.65503);
        ((GeneralPath)shape).curveTo(455.807, 497.14203, 455.637, 496.72903, 455.057, 496.70602);
        ((GeneralPath)shape).lineTo(455.057, 496.65903);
        ((GeneralPath)shape).curveTo(455.581, 496.58502, 455.713, 496.13504, 455.713, 495.61603);
        ((GeneralPath)shape).curveTo(455.713, 494.43204, 454.71902, 494.37103, 453.989, 494.37103);
        ((GeneralPath)shape).curveTo(452.832, 494.37103, 452.15002, 494.48203, 452.15002, 495.81204);
        ((GeneralPath)shape).lineTo(452.15002, 495.97305);
        ((GeneralPath)shape).lineTo(453.27502, 495.97305);
        ((GeneralPath)shape).lineTo(453.27502, 495.87607);
        ((GeneralPath)shape).curveTo(453.27502, 495.31805, 453.48, 495.31805, 453.93402, 495.31805);
        ((GeneralPath)shape).curveTo(454.56903, 495.31805, 454.58704, 495.45306, 454.58704, 495.85205);
        ((GeneralPath)shape).curveTo(454.58704, 496.18005, 454.53903, 496.24506, 453.94403, 496.24506);
        ((GeneralPath)shape).lineTo(453.64902, 496.24506);
        ((GeneralPath)shape).lineTo(453.64902, 497.092);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_296;
        g.setTransform(defaultTransform__0_296);
        g.setClip(clip__0_296);
        float alpha__0_297 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_297 = g.getClip();
        AffineTransform defaultTransform__0_297 = g.getTransform();
        
//      _0_297 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(458.065, 497.206);
        ((GeneralPath)shape).lineTo(459.003, 497.206);
        ((GeneralPath)shape).lineTo(459.003, 496.957);
        ((GeneralPath)shape).curveTo(459.003, 496.374, 458.942, 495.779, 458.218, 495.779);
        ((GeneralPath)shape).curveTo(458.025, 495.779, 457.862, 495.81998, 457.72897, 495.908);
        ((GeneralPath)shape).curveTo(457.597, 495.996, 457.49298, 496.138, 457.42096, 496.34198);
        ((GeneralPath)shape).lineTo(457.40897, 496.34198);
        ((GeneralPath)shape).lineTo(457.40897, 495.779);
        ((GeneralPath)shape).lineTo(456.37796, 495.779);
        ((GeneralPath)shape).lineTo(456.37796, 498.96698);
        ((GeneralPath)shape).lineTo(457.40897, 498.96698);
        ((GeneralPath)shape).lineTo(457.40897, 497.085);
        ((GeneralPath)shape).curveTo(457.40897, 496.80798, 457.47897, 496.544, 457.78696, 496.544);
        ((GeneralPath)shape).curveTo(458.06497, 496.544, 458.06497, 496.808, 458.06497, 497.045);
        ((GeneralPath)shape).lineTo(458.06497, 497.206);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_297;
        g.setTransform(defaultTransform__0_297);
        g.setClip(clip__0_297);
        float alpha__0_298 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_298 = g.getClip();
        AffineTransform defaultTransform__0_298 = g.getTransform();
        
//      _0_298 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(460.441, 497.402);
        ((GeneralPath)shape).curveTo(460.441, 496.778, 460.441, 496.581, 460.987, 496.581);
        ((GeneralPath)shape).curveTo(461.566, 496.581, 461.566, 496.79, 461.566, 497.402);
        ((GeneralPath)shape).curveTo(461.566, 498.114, 461.43002, 498.216, 460.987, 498.216);
        ((GeneralPath)shape).curveTo(460.541, 498.217, 460.441, 498.169, 460.441, 497.402);
        ((GeneralPath)shape).moveTo(462.598, 494.373);
        ((GeneralPath)shape).lineTo(461.567, 494.373);
        ((GeneralPath)shape).lineTo(461.567, 496.248);
        ((GeneralPath)shape).lineTo(461.529, 496.248);
        ((GeneralPath)shape).curveTo(461.357, 495.85397, 461.01898, 495.779, 460.633, 495.779);
        ((GeneralPath)shape).curveTo(459.75, 495.779, 459.411, 496.159, 459.411, 497.10898);
        ((GeneralPath)shape).lineTo(459.411, 497.82098);
        ((GeneralPath)shape).curveTo(459.411, 498.646, 459.853, 498.96698, 460.633, 498.96698);
        ((GeneralPath)shape).curveTo(461.094, 498.96698, 461.432, 498.86597, 461.555, 498.425);
        ((GeneralPath)shape).lineTo(461.568, 498.425);
        ((GeneralPath)shape).lineTo(461.568, 498.96698);
        ((GeneralPath)shape).lineTo(462.599, 498.96698);
        ((GeneralPath)shape).lineTo(462.599, 494.373);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_298;
        g.setTransform(defaultTransform__0_298);
        g.setClip(clip__0_298);
        float alpha__0_299 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_299 = g.getClip();
        AffineTransform defaultTransform__0_299 = g.getTransform();
        
//      _0_299 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(469.461, 494.373);
        ((GeneralPath)shape).lineTo(468.242, 494.373);
        ((GeneralPath)shape).lineTo(468.242, 496.154);
        ((GeneralPath)shape).lineTo(466.648, 496.154);
        ((GeneralPath)shape).lineTo(466.648, 494.373);
        ((GeneralPath)shape).lineTo(465.43, 494.373);
        ((GeneralPath)shape).lineTo(465.43, 498.967);
        ((GeneralPath)shape).lineTo(466.648, 498.967);
        ((GeneralPath)shape).lineTo(466.648, 497.186);
        ((GeneralPath)shape).lineTo(468.242, 497.186);
        ((GeneralPath)shape).lineTo(468.242, 498.967);
        ((GeneralPath)shape).lineTo(469.461, 498.967);
        ((GeneralPath)shape).lineTo(469.461, 494.373);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_299;
        g.setTransform(defaultTransform__0_299);
        g.setClip(clip__0_299);
        float alpha__0_300 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_300 = g.getClip();
        AffineTransform defaultTransform__0_300 = g.getTransform();
        
//      _0_300 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new Rectangle2D.Double(470.1570129394531, 494.37298583984375, 1.218999981880188, 4.593999862670898);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_300;
        g.setTransform(defaultTransform__0_300);
        g.setClip(clip__0_300);
        float alpha__0_301 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_301 = g.getClip();
        AffineTransform defaultTransform__0_301 = g.getTransform();
        
//      _0_301 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(474.07, 495.43);
        ((GeneralPath)shape).lineTo(475.282, 495.43);
        ((GeneralPath)shape).lineTo(475.282, 494.373);
        ((GeneralPath)shape).lineTo(471.7, 494.373);
        ((GeneralPath)shape).lineTo(471.7, 495.43);
        ((GeneralPath)shape).lineTo(472.852, 495.43);
        ((GeneralPath)shape).lineTo(472.852, 498.967);
        ((GeneralPath)shape).lineTo(474.07, 498.967);
        ((GeneralPath)shape).lineTo(474.07, 495.43);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_301;
        g.setTransform(defaultTransform__0_301);
        g.setClip(clip__0_301);
        float alpha__0_302 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_302 = g.getClip();
        AffineTransform defaultTransform__0_302 = g.getTransform();
        
//      _0_302 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(318.037, 508.37);
        ((GeneralPath)shape).lineTo(315.92798, 508.37);
        ((GeneralPath)shape).lineTo(315.92798, 508.24298);
        ((GeneralPath)shape).curveTo(315.92798, 507.67697, 316.13397, 507.69598, 316.80298, 507.58997);
        ((GeneralPath)shape).curveTo(317.722, 507.43295, 318.037, 507.31195, 318.037, 506.30896);
        ((GeneralPath)shape).curveTo(318.037, 505.30695, 317.54898, 505.13895, 316.66898, 505.13895);
        ((GeneralPath)shape).curveTo(315.304, 505.13895, 315.22498, 505.59296, 315.22498, 506.51794);
        ((GeneralPath)shape).lineTo(315.92798, 506.51794);
        ((GeneralPath)shape).curveTo(315.92798, 505.73694, 316.042, 505.73694, 316.666, 505.73694);
        ((GeneralPath)shape).curveTo(317.175, 505.73694, 317.33398, 505.80695, 317.33398, 506.30893);
        ((GeneralPath)shape).curveTo(317.33398, 506.99692, 317.162, 506.92593, 316.43, 507.06293);
        ((GeneralPath)shape).curveTo(315.603, 507.19992, 315.225, 507.27094, 315.225, 508.24393);
        ((GeneralPath)shape).lineTo(315.225, 508.96793);
        ((GeneralPath)shape).lineTo(318.038, 508.96793);
        ((GeneralPath)shape).lineTo(318.038, 508.37);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_302;
        g.setTransform(defaultTransform__0_302);
        g.setClip(clip__0_302);
        float alpha__0_303 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_303 = g.getClip();
        AffineTransform defaultTransform__0_303 = g.getTransform();
        
//      _0_303 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(331.842, 505.736);
        ((GeneralPath)shape).lineTo(331.842, 508.967);
        ((GeneralPath)shape).lineTo(332.545, 508.967);
        ((GeneralPath)shape).lineTo(332.545, 505.139);
        ((GeneralPath)shape).lineTo(331.377, 505.139);
        ((GeneralPath)shape).lineTo(330.365, 507.98);
        ((GeneralPath)shape).lineTo(330.346, 507.98);
        ((GeneralPath)shape).lineTo(329.313, 505.139);
        ((GeneralPath)shape).lineTo(328.17, 505.139);
        ((GeneralPath)shape).lineTo(328.17, 508.967);
        ((GeneralPath)shape).lineTo(328.873, 508.967);
        ((GeneralPath)shape).lineTo(328.873, 505.752);
        ((GeneralPath)shape).lineTo(330.031, 508.967);
        ((GeneralPath)shape).lineTo(330.684, 508.967);
        ((GeneralPath)shape).lineTo(331.842, 505.736);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_303;
        g.setTransform(defaultTransform__0_303);
        g.setClip(clip__0_303);
        float alpha__0_304 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_304 = g.getClip();
        AffineTransform defaultTransform__0_304 = g.getTransform();
        
//      _0_304 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(334.924, 508.967);
        ((GeneralPath)shape).lineTo(335.549, 508.967);
        ((GeneralPath)shape).lineTo(335.549, 507.321);
        ((GeneralPath)shape).curveTo(335.549, 506.441, 335.195, 506.31003, 334.34402, 506.31003);
        ((GeneralPath)shape).curveTo(333.73703, 506.31003, 333.20502, 506.31003, 333.20502, 507.09003);
        ((GeneralPath)shape).lineTo(333.83002, 507.09003);
        ((GeneralPath)shape).curveTo(333.83002, 506.769, 334.05902, 506.74603, 334.34302, 506.74603);
        ((GeneralPath)shape).curveTo(334.88803, 506.74603, 334.924, 506.89404, 334.924, 507.31403);
        ((GeneralPath)shape).lineTo(334.924, 507.65103);
        ((GeneralPath)shape).lineTo(334.903, 507.65103);
        ((GeneralPath)shape).curveTo(334.752, 507.32504, 334.43503, 507.32504, 334.113, 507.32504);
        ((GeneralPath)shape).curveTo(333.459, 507.32504, 333.127, 507.50003, 333.127, 508.13104);
        ((GeneralPath)shape).curveTo(333.127, 508.84204, 333.513, 508.96603, 334.108, 508.96603);
        ((GeneralPath)shape).curveTo(334.413, 508.96603, 334.797, 508.96603, 334.923, 508.57904);
        ((GeneralPath)shape).lineTo(334.923, 508.967);
        ((GeneralPath)shape).moveTo(334.335, 507.795);
        ((GeneralPath)shape).curveTo(334.65, 507.795, 334.92398, 507.795, 334.92398, 508.12903);
        ((GeneralPath)shape).curveTo(334.92398, 508.47202, 334.675, 508.49802, 334.335, 508.49802);
        ((GeneralPath)shape).curveTo(333.904, 508.49802, 333.75198, 508.467, 333.75198, 508.134);
        ((GeneralPath)shape).curveTo(333.752, 507.795, 334.011, 507.795, 334.335, 507.795);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_304;
        g.setTransform(defaultTransform__0_304);
        g.setClip(clip__0_304);
        float alpha__0_305 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_305 = g.getClip();
        AffineTransform defaultTransform__0_305 = g.getTransform();
        
//      _0_305 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(336.774, 505.139);
        ((GeneralPath)shape).lineTo(336.149, 505.139);
        ((GeneralPath)shape).lineTo(336.149, 505.681);
        ((GeneralPath)shape).lineTo(336.774, 505.681);
        ((GeneralPath)shape).lineTo(336.774, 505.139);
        ((GeneralPath)shape).moveTo(336.774, 506.311);
        ((GeneralPath)shape).lineTo(336.149, 506.311);
        ((GeneralPath)shape).lineTo(336.149, 508.967);
        ((GeneralPath)shape).lineTo(336.774, 508.967);
        ((GeneralPath)shape).lineTo(336.774, 506.311);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_305;
        g.setTransform(defaultTransform__0_305);
        g.setClip(clip__0_305);
        float alpha__0_306 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_306 = g.getClip();
        AffineTransform defaultTransform__0_306 = g.getTransform();
        
//      _0_306 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(337.33, 506.311);
        ((GeneralPath)shape).lineTo(337.33, 508.967);
        ((GeneralPath)shape).lineTo(337.955, 508.967);
        ((GeneralPath)shape).lineTo(337.955, 507.526);
        ((GeneralPath)shape).curveTo(337.955, 507.057, 338.02798, 506.797, 338.568, 506.797);
        ((GeneralPath)shape).curveTo(338.965, 506.797, 339.048, 506.926, 339.048, 507.306);
        ((GeneralPath)shape).lineTo(339.048, 508.967);
        ((GeneralPath)shape).lineTo(339.673, 508.967);
        ((GeneralPath)shape).lineTo(339.673, 507.237);
        ((GeneralPath)shape).curveTo(339.673, 506.597, 339.467, 506.311, 338.78702, 506.311);
        ((GeneralPath)shape).curveTo(338.42203, 506.311, 338.11102, 506.352, 337.97403, 506.738);
        ((GeneralPath)shape).lineTo(337.95404, 506.738);
        ((GeneralPath)shape).lineTo(337.95404, 506.311);
        ((GeneralPath)shape).lineTo(337.33, 506.311);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_306;
        g.setTransform(defaultTransform__0_306);
        g.setClip(clip__0_306);
        float alpha__0_307 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_307 = g.getClip();
        AffineTransform defaultTransform__0_307 = g.getTransform();
        
//      _0_307 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(343.525, 506.936);
        ((GeneralPath)shape).lineTo(343.525, 507.483);
        ((GeneralPath)shape).lineTo(344.50198, 507.483);
        ((GeneralPath)shape).lineTo(344.50198, 507.62);
        ((GeneralPath)shape).curveTo(344.50198, 508.343, 344.284, 508.343, 343.57797, 508.343);
        ((GeneralPath)shape).curveTo(342.75098, 508.343, 342.62698, 508.263, 342.62698, 507.478);
        ((GeneralPath)shape).lineTo(342.62698, 506.619);
        ((GeneralPath)shape).curveTo(342.62698, 505.89398, 342.71, 505.76398, 343.57797, 505.76398);
        ((GeneralPath)shape).curveTo(344.22397, 505.76398, 344.50198, 505.76398, 344.50198, 506.31796);
        ((GeneralPath)shape).lineTo(345.205, 506.31796);
        ((GeneralPath)shape).curveTo(345.205, 505.09598, 344.521, 505.14096, 343.57797, 505.14096);
        ((GeneralPath)shape).curveTo(342.41397, 505.14096, 341.92398, 505.36295, 341.92398, 506.61896);
        ((GeneralPath)shape).lineTo(341.92398, 507.48297);
        ((GeneralPath)shape).curveTo(341.92398, 508.75797, 342.37497, 508.96896, 343.581, 508.96896);
        ((GeneralPath)shape).curveTo(344.923, 508.96896, 345.205, 508.73895, 345.205, 507.62097);
        ((GeneralPath)shape).lineTo(345.205, 506.93698);
        ((GeneralPath)shape).lineTo(343.525, 506.93698);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_307;
        g.setTransform(defaultTransform__0_307);
        g.setClip(clip__0_307);
        float alpha__0_308 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_308 = g.getClip();
        AffineTransform defaultTransform__0_308 = g.getTransform();
        
//      _0_308 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(348.059, 508.967);
        ((GeneralPath)shape).lineTo(348.059, 506.311);
        ((GeneralPath)shape).lineTo(347.434, 506.311);
        ((GeneralPath)shape).lineTo(347.434, 507.834);
        ((GeneralPath)shape).curveTo(347.434, 508.29202, 347.27698, 508.49802, 346.78598, 508.49802);
        ((GeneralPath)shape).curveTo(346.37598, 508.49802, 346.34097, 508.35303, 346.34097, 507.976);
        ((GeneralPath)shape).lineTo(346.34097, 506.311);
        ((GeneralPath)shape).lineTo(345.71597, 506.311);
        ((GeneralPath)shape).lineTo(345.71597, 508.212);
        ((GeneralPath)shape).curveTo(345.71597, 508.786, 346.06897, 508.967, 346.59897, 508.967);
        ((GeneralPath)shape).curveTo(346.96097, 508.967, 347.28796, 508.884, 347.43497, 508.545);
        ((GeneralPath)shape).lineTo(347.43497, 508.967);
        ((GeneralPath)shape).lineTo(348.059, 508.967);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_308;
        g.setTransform(defaultTransform__0_308);
        g.setClip(clip__0_308);
        float alpha__0_309 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_309 = g.getClip();
        AffineTransform defaultTransform__0_309 = g.getTransform();
        
//      _0_309 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(348.68, 506.311);
        ((GeneralPath)shape).lineTo(348.68, 508.967);
        ((GeneralPath)shape).lineTo(349.305, 508.967);
        ((GeneralPath)shape).lineTo(349.305, 507.526);
        ((GeneralPath)shape).curveTo(349.305, 507.057, 349.378, 506.797, 349.918, 506.797);
        ((GeneralPath)shape).curveTo(350.315, 506.797, 350.398, 506.926, 350.398, 507.306);
        ((GeneralPath)shape).lineTo(350.398, 508.967);
        ((GeneralPath)shape).lineTo(351.023, 508.967);
        ((GeneralPath)shape).lineTo(351.023, 507.237);
        ((GeneralPath)shape).curveTo(351.023, 506.597, 350.81702, 506.311, 350.13702, 506.311);
        ((GeneralPath)shape).curveTo(349.77203, 506.311, 349.46103, 506.352, 349.32404, 506.738);
        ((GeneralPath)shape).lineTo(349.30405, 506.738);
        ((GeneralPath)shape).lineTo(349.30405, 506.311);
        ((GeneralPath)shape).lineTo(348.68, 506.311);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_309;
        g.setTransform(defaultTransform__0_309);
        g.setClip(clip__0_309);
        float alpha__0_310 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_310 = g.getClip();
        AffineTransform defaultTransform__0_310 = g.getTransform();
        
//      _0_310 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(377.842, 505.736);
        ((GeneralPath)shape).lineTo(377.842, 508.967);
        ((GeneralPath)shape).lineTo(378.545, 508.967);
        ((GeneralPath)shape).lineTo(378.545, 505.139);
        ((GeneralPath)shape).lineTo(377.376, 505.139);
        ((GeneralPath)shape).lineTo(376.364, 507.98);
        ((GeneralPath)shape).lineTo(376.345, 507.98);
        ((GeneralPath)shape).lineTo(375.313, 505.139);
        ((GeneralPath)shape).lineTo(374.17, 505.139);
        ((GeneralPath)shape).lineTo(374.17, 508.967);
        ((GeneralPath)shape).lineTo(374.873, 508.967);
        ((GeneralPath)shape).lineTo(374.873, 505.752);
        ((GeneralPath)shape).lineTo(376.031, 508.967);
        ((GeneralPath)shape).lineTo(376.683, 508.967);
        ((GeneralPath)shape).lineTo(377.842, 505.736);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_310;
        g.setTransform(defaultTransform__0_310);
        g.setClip(clip__0_310);
        float alpha__0_311 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_311 = g.getClip();
        AffineTransform defaultTransform__0_311 = g.getTransform();
        
//      _0_311 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(380.923, 508.967);
        ((GeneralPath)shape).lineTo(381.548, 508.967);
        ((GeneralPath)shape).lineTo(381.548, 507.321);
        ((GeneralPath)shape).curveTo(381.548, 506.441, 381.194, 506.31003, 380.344, 506.31003);
        ((GeneralPath)shape).curveTo(379.736, 506.31003, 379.20398, 506.31003, 379.20398, 507.09003);
        ((GeneralPath)shape).lineTo(379.82898, 507.09003);
        ((GeneralPath)shape).curveTo(379.82898, 506.769, 380.05798, 506.74603, 380.34198, 506.74603);
        ((GeneralPath)shape).curveTo(380.887, 506.74603, 380.92297, 506.89404, 380.92297, 507.31403);
        ((GeneralPath)shape).lineTo(380.92297, 507.65103);
        ((GeneralPath)shape).lineTo(380.90198, 507.65103);
        ((GeneralPath)shape).curveTo(380.75098, 507.32504, 380.434, 507.32504, 380.11197, 507.32504);
        ((GeneralPath)shape).curveTo(379.45798, 507.32504, 379.12598, 507.50003, 379.12598, 508.13104);
        ((GeneralPath)shape).curveTo(379.12598, 508.84204, 379.51196, 508.96603, 380.10696, 508.96603);
        ((GeneralPath)shape).curveTo(380.41296, 508.96603, 380.79596, 508.96603, 380.92197, 508.57904);
        ((GeneralPath)shape).lineTo(380.92197, 508.967);
        ((GeneralPath)shape).moveTo(380.335, 507.795);
        ((GeneralPath)shape).curveTo(380.649, 507.795, 380.923, 507.795, 380.923, 508.12903);
        ((GeneralPath)shape).curveTo(380.923, 508.47202, 380.674, 508.49802, 380.335, 508.49802);
        ((GeneralPath)shape).curveTo(379.904, 508.49802, 379.75098, 508.467, 379.75098, 508.134);
        ((GeneralPath)shape).curveTo(379.751, 507.795, 380.01, 507.795, 380.335, 507.795);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_311;
        g.setTransform(defaultTransform__0_311);
        g.setClip(clip__0_311);
        float alpha__0_312 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_312 = g.getClip();
        AffineTransform defaultTransform__0_312 = g.getTransform();
        
//      _0_312 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(382.774, 505.139);
        ((GeneralPath)shape).lineTo(382.149, 505.139);
        ((GeneralPath)shape).lineTo(382.149, 505.681);
        ((GeneralPath)shape).lineTo(382.774, 505.681);
        ((GeneralPath)shape).lineTo(382.774, 505.139);
        ((GeneralPath)shape).moveTo(382.774, 506.311);
        ((GeneralPath)shape).lineTo(382.149, 506.311);
        ((GeneralPath)shape).lineTo(382.149, 508.967);
        ((GeneralPath)shape).lineTo(382.774, 508.967);
        ((GeneralPath)shape).lineTo(382.774, 506.311);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_312;
        g.setTransform(defaultTransform__0_312);
        g.setClip(clip__0_312);
        float alpha__0_313 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_313 = g.getClip();
        AffineTransform defaultTransform__0_313 = g.getTransform();
        
//      _0_313 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(383.329, 506.311);
        ((GeneralPath)shape).lineTo(383.329, 508.967);
        ((GeneralPath)shape).lineTo(383.954, 508.967);
        ((GeneralPath)shape).lineTo(383.954, 507.526);
        ((GeneralPath)shape).curveTo(383.954, 507.057, 384.027, 506.797, 384.56702, 506.797);
        ((GeneralPath)shape).curveTo(384.96402, 506.797, 385.04703, 506.926, 385.04703, 507.306);
        ((GeneralPath)shape).lineTo(385.04703, 508.967);
        ((GeneralPath)shape).lineTo(385.67203, 508.967);
        ((GeneralPath)shape).lineTo(385.67203, 507.237);
        ((GeneralPath)shape).curveTo(385.67203, 506.597, 385.46603, 506.311, 384.78604, 506.311);
        ((GeneralPath)shape).curveTo(384.42105, 506.311, 384.11005, 506.352, 383.97305, 506.738);
        ((GeneralPath)shape).lineTo(383.95306, 506.738);
        ((GeneralPath)shape).lineTo(383.95306, 506.311);
        ((GeneralPath)shape).lineTo(383.329, 506.311);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_313;
        g.setTransform(defaultTransform__0_313);
        g.setClip(clip__0_313);
        float alpha__0_314 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_314 = g.getClip();
        AffineTransform defaultTransform__0_314 = g.getTransform();
        
//      _0_314 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(389.525, 506.936);
        ((GeneralPath)shape).lineTo(389.525, 507.483);
        ((GeneralPath)shape).lineTo(390.50198, 507.483);
        ((GeneralPath)shape).lineTo(390.50198, 507.62);
        ((GeneralPath)shape).curveTo(390.50198, 508.343, 390.284, 508.343, 389.577, 508.343);
        ((GeneralPath)shape).curveTo(388.751, 508.343, 388.62698, 508.263, 388.62698, 507.478);
        ((GeneralPath)shape).lineTo(388.62698, 506.619);
        ((GeneralPath)shape).curveTo(388.62698, 505.89398, 388.71, 505.76398, 389.577, 505.76398);
        ((GeneralPath)shape).curveTo(390.223, 505.76398, 390.50198, 505.76398, 390.50198, 506.31796);
        ((GeneralPath)shape).lineTo(391.205, 506.31796);
        ((GeneralPath)shape).curveTo(391.205, 505.09598, 390.521, 505.14096, 389.577, 505.14096);
        ((GeneralPath)shape).curveTo(388.414, 505.14096, 387.92398, 505.36295, 387.92398, 506.61896);
        ((GeneralPath)shape).lineTo(387.92398, 507.48297);
        ((GeneralPath)shape).curveTo(387.92398, 508.75797, 388.37497, 508.96896, 389.581, 508.96896);
        ((GeneralPath)shape).curveTo(390.923, 508.96896, 391.205, 508.73895, 391.205, 507.62097);
        ((GeneralPath)shape).lineTo(391.205, 506.93698);
        ((GeneralPath)shape).lineTo(389.525, 506.93698);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_314;
        g.setTransform(defaultTransform__0_314);
        g.setClip(clip__0_314);
        float alpha__0_315 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_315 = g.getClip();
        AffineTransform defaultTransform__0_315 = g.getTransform();
        
//      _0_315 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(394.059, 508.967);
        ((GeneralPath)shape).lineTo(394.059, 506.311);
        ((GeneralPath)shape).lineTo(393.434, 506.311);
        ((GeneralPath)shape).lineTo(393.434, 507.834);
        ((GeneralPath)shape).curveTo(393.434, 508.29202, 393.276, 508.49802, 392.78598, 508.49802);
        ((GeneralPath)shape).curveTo(392.37598, 508.49802, 392.34097, 508.35303, 392.34097, 507.976);
        ((GeneralPath)shape).lineTo(392.34097, 506.311);
        ((GeneralPath)shape).lineTo(391.71597, 506.311);
        ((GeneralPath)shape).lineTo(391.71597, 508.212);
        ((GeneralPath)shape).curveTo(391.71597, 508.786, 392.06897, 508.967, 392.59796, 508.967);
        ((GeneralPath)shape).curveTo(392.95996, 508.967, 393.28796, 508.884, 393.43497, 508.545);
        ((GeneralPath)shape).lineTo(393.43497, 508.967);
        ((GeneralPath)shape).lineTo(394.059, 508.967);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_315;
        g.setTransform(defaultTransform__0_315);
        g.setClip(clip__0_315);
        float alpha__0_316 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_316 = g.getClip();
        AffineTransform defaultTransform__0_316 = g.getTransform();
        
//      _0_316 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(394.68, 506.311);
        ((GeneralPath)shape).lineTo(394.68, 508.967);
        ((GeneralPath)shape).lineTo(395.305, 508.967);
        ((GeneralPath)shape).lineTo(395.305, 507.526);
        ((GeneralPath)shape).curveTo(395.305, 507.057, 395.378, 506.797, 395.918, 506.797);
        ((GeneralPath)shape).curveTo(396.315, 506.797, 396.398, 506.926, 396.398, 507.306);
        ((GeneralPath)shape).lineTo(396.398, 508.967);
        ((GeneralPath)shape).lineTo(397.023, 508.967);
        ((GeneralPath)shape).lineTo(397.023, 507.237);
        ((GeneralPath)shape).curveTo(397.023, 506.597, 396.81702, 506.311, 396.13702, 506.311);
        ((GeneralPath)shape).curveTo(395.77203, 506.311, 395.46103, 506.352, 395.32404, 506.738);
        ((GeneralPath)shape).lineTo(395.30405, 506.738);
        ((GeneralPath)shape).lineTo(395.30405, 506.311);
        ((GeneralPath)shape).lineTo(394.68, 506.311);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_316;
        g.setTransform(defaultTransform__0_316);
        g.setClip(clip__0_316);
        float alpha__0_317 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_317 = g.getClip();
        AffineTransform defaultTransform__0_317 = g.getTransform();
        
//      _0_317 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(400.133, 508.345);
        ((GeneralPath)shape).lineTo(400.133, 505.762);
        ((GeneralPath)shape).lineTo(401.233, 505.762);
        ((GeneralPath)shape).curveTo(401.797, 505.762, 402.008, 505.91998, 402.008, 506.56198);
        ((GeneralPath)shape).lineTo(402.008, 507.468);
        ((GeneralPath)shape).curveTo(402.008, 507.9, 401.854, 508.345, 401.356, 508.345);
        ((GeneralPath)shape).lineTo(400.133, 508.345);
        ((GeneralPath)shape).moveTo(399.43, 508.967);
        ((GeneralPath)shape).lineTo(401.349, 508.967);
        ((GeneralPath)shape).curveTo(402.491, 508.967, 402.711, 508.263, 402.711, 507.467);
        ((GeneralPath)shape).lineTo(402.711, 506.564);
        ((GeneralPath)shape).curveTo(402.711, 505.523, 402.269, 505.139, 401.229, 505.139);
        ((GeneralPath)shape).lineTo(399.43, 505.139);
        ((GeneralPath)shape).lineTo(399.43, 508.967);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_317;
        g.setTransform(defaultTransform__0_317);
        g.setClip(clip__0_317);
        float alpha__0_318 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_318 = g.getClip();
        AffineTransform defaultTransform__0_318 = g.getTransform();
        
//      _0_318 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(403.746, 507.404);
        ((GeneralPath)shape).curveTo(403.746, 506.918, 403.782, 506.797, 404.325, 506.797);
        ((GeneralPath)shape).curveTo(404.83902, 506.797, 404.918, 506.83798, 404.918, 507.404);
        ((GeneralPath)shape).lineTo(403.746, 507.404);
        ((GeneralPath)shape).moveTo(404.918, 508.135);
        ((GeneralPath)shape).curveTo(404.918, 508.49802, 404.674, 508.49802, 404.325, 508.49802);
        ((GeneralPath)shape).curveTo(403.76102, 508.49802, 403.746, 508.329, 403.746, 507.795);
        ((GeneralPath)shape).lineTo(405.543, 507.795);
        ((GeneralPath)shape).curveTo(405.543, 506.631, 405.399, 506.311, 404.325, 506.311);
        ((GeneralPath)shape).curveTo(403.27002, 506.311, 403.121, 506.716, 403.121, 507.647);
        ((GeneralPath)shape).curveTo(403.121, 508.652, 403.325, 508.967, 404.325, 508.967);
        ((GeneralPath)shape).curveTo(405.071, 508.967, 405.543, 508.928, 405.543, 508.135);
        ((GeneralPath)shape).lineTo(404.918, 508.135);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_318;
        g.setTransform(defaultTransform__0_318);
        g.setClip(clip__0_318);
        float alpha__0_319 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_319 = g.getClip();
        AffineTransform defaultTransform__0_319 = g.getTransform();
        
//      _0_319 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(408.237, 507.05);
        ((GeneralPath)shape).curveTo(408.237, 506.31097, 407.637, 506.31097, 407.135, 506.31097);
        ((GeneralPath)shape).curveTo(406.478, 506.31097, 405.971, 506.31097, 405.971, 507.02097);
        ((GeneralPath)shape).curveTo(405.971, 507.67996, 406.14102, 507.80795, 407.07602, 507.82297);
        ((GeneralPath)shape).curveTo(407.67502, 507.83298, 407.68903, 507.94098, 407.68903, 508.15897);
        ((GeneralPath)shape).curveTo(407.68903, 508.49896, 407.49002, 508.49896, 407.12903, 508.49896);
        ((GeneralPath)shape).curveTo(406.68204, 508.49896, 406.59503, 508.45697, 406.59503, 508.09195);
        ((GeneralPath)shape).lineTo(405.97003, 508.09195);
        ((GeneralPath)shape).curveTo(405.97003, 508.96796, 406.43503, 508.96796, 407.12704, 508.96796);
        ((GeneralPath)shape).curveTo(407.77203, 508.96796, 408.31406, 508.89896, 408.31406, 508.19196);
        ((GeneralPath)shape).curveTo(408.31406, 507.34595, 407.77106, 507.39096, 407.18207, 507.36197);
        ((GeneralPath)shape).curveTo(406.66107, 507.33798, 406.59506, 507.32797, 406.59506, 507.07898);
        ((GeneralPath)shape).curveTo(406.59506, 506.749, 406.81506, 506.749, 407.13306, 506.749);
        ((GeneralPath)shape).curveTo(407.45306, 506.749, 407.61105, 506.749, 407.61105, 507.052);
        ((GeneralPath)shape).lineTo(408.237, 507.052);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_319;
        g.setTransform(defaultTransform__0_319);
        g.setClip(clip__0_319);
        float alpha__0_320 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_320 = g.getClip();
        AffineTransform defaultTransform__0_320 = g.getTransform();
        
//      _0_320 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(408.505, 506.779);
        ((GeneralPath)shape).lineTo(408.834, 506.779);
        ((GeneralPath)shape).lineTo(408.834, 508.14798);
        ((GeneralPath)shape).curveTo(408.834, 508.80798, 409.04703, 508.96597, 409.742, 508.96597);
        ((GeneralPath)shape).curveTo(410.43, 508.96597, 410.631, 508.72696, 410.631, 507.93497);
        ((GeneralPath)shape).lineTo(410.084, 507.93497);
        ((GeneralPath)shape).curveTo(410.084, 508.21298, 410.12302, 508.49796, 409.74402, 508.49796);
        ((GeneralPath)shape).curveTo(409.459, 508.49796, 409.459, 508.38797, 409.459, 508.14395);
        ((GeneralPath)shape).lineTo(409.459, 506.77994);
        ((GeneralPath)shape).lineTo(410.49802, 506.77994);
        ((GeneralPath)shape).lineTo(410.49802, 506.31094);
        ((GeneralPath)shape).lineTo(409.459, 506.31094);
        ((GeneralPath)shape).lineTo(409.459, 505.69293);
        ((GeneralPath)shape).lineTo(408.834, 505.69293);
        ((GeneralPath)shape).lineTo(408.834, 506.31094);
        ((GeneralPath)shape).lineTo(408.505, 506.31094);
        ((GeneralPath)shape).lineTo(408.505, 506.779);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_320;
        g.setTransform(defaultTransform__0_320);
        g.setClip(clip__0_320);
        float alpha__0_321 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_321 = g.getClip();
        AffineTransform defaultTransform__0_321 = g.getTransform();
        
//      _0_321 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(410.881, 506.311);
        ((GeneralPath)shape).lineTo(410.881, 508.967);
        ((GeneralPath)shape).lineTo(411.506, 508.967);
        ((GeneralPath)shape).lineTo(411.506, 507.351);
        ((GeneralPath)shape).curveTo(411.506, 507.01202, 411.62302, 506.79703, 412.02402, 506.79703);
        ((GeneralPath)shape).curveTo(412.33902, 506.79703, 412.36603, 506.95203, 412.36603, 507.21603);
        ((GeneralPath)shape).lineTo(412.36603, 507.35104);
        ((GeneralPath)shape).lineTo(412.99103, 507.35104);
        ((GeneralPath)shape).lineTo(412.99103, 507.14005);
        ((GeneralPath)shape).curveTo(412.99103, 506.64304, 412.84702, 506.31104, 412.25403, 506.31104);
        ((GeneralPath)shape).curveTo(411.93002, 506.31104, 411.64, 506.39603, 411.50702, 506.68402);
        ((GeneralPath)shape).lineTo(411.50702, 506.31104);
        ((GeneralPath)shape).lineTo(410.881, 506.31104);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_321;
        g.setTransform(defaultTransform__0_321);
        g.setClip(clip__0_321);
        float alpha__0_322 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_322 = g.getClip();
        AffineTransform defaultTransform__0_322 = g.getTransform();
        
//      _0_322 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(414.487, 506.797);
        ((GeneralPath)shape).curveTo(415.029, 506.797, 415.073, 506.954, 415.073, 507.655);
        ((GeneralPath)shape).curveTo(415.073, 508.345, 415.029, 508.498, 414.487, 508.498);
        ((GeneralPath)shape).curveTo(413.945, 508.498, 413.901, 508.34598, 413.901, 507.655);
        ((GeneralPath)shape).curveTo(413.901, 506.954, 413.945, 506.797, 414.487, 506.797);
        ((GeneralPath)shape).moveTo(414.487, 506.311);
        ((GeneralPath)shape).curveTo(413.413, 506.311, 413.276, 506.63202, 413.276, 507.642);
        ((GeneralPath)shape).curveTo(413.276, 508.647, 413.413, 508.967, 414.487, 508.967);
        ((GeneralPath)shape).curveTo(415.561, 508.967, 415.698, 508.647, 415.698, 507.642);
        ((GeneralPath)shape).curveTo(415.698, 506.632, 415.562, 506.311, 414.487, 506.311);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_322;
        g.setTransform(defaultTransform__0_322);
        g.setClip(clip__0_322);
        float alpha__0_323 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_323 = g.getClip();
        AffineTransform defaultTransform__0_323 = g.getTransform();
        
//      _0_323 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(418.452, 506.311);
        ((GeneralPath)shape).lineTo(417.805, 506.311);
        ((GeneralPath)shape).lineTo(417.28998, 508.574);
        ((GeneralPath)shape).lineTo(417.27997, 508.574);
        ((GeneralPath)shape).lineTo(416.60898, 506.311);
        ((GeneralPath)shape).lineTo(415.95197, 506.311);
        ((GeneralPath)shape).lineTo(416.82596, 508.967);
        ((GeneralPath)shape).lineTo(417.13397, 508.967);
        ((GeneralPath)shape).curveTo(417.07797, 509.28, 417.01797, 509.67, 416.61896, 509.67);
        ((GeneralPath)shape).curveTo(416.57297, 509.67, 416.52695, 509.65903, 416.48096, 509.65402);
        ((GeneralPath)shape).lineTo(416.48096, 510.13803);
        ((GeneralPath)shape).curveTo(416.57196, 510.13803, 416.66397, 510.13803, 416.75296, 510.13803);
        ((GeneralPath)shape).curveTo(417.49597, 510.13803, 417.61697, 509.66803, 417.76395, 509.06802);
        ((GeneralPath)shape).lineTo(418.452, 506.311);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_323;
        g.setTransform(defaultTransform__0_323);
        g.setClip(clip__0_323);
        float alpha__0_324 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_324 = g.getClip();
        AffineTransform defaultTransform__0_324 = g.getTransform();
        
//      _0_324 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(419.236, 507.404);
        ((GeneralPath)shape).curveTo(419.236, 506.918, 419.271, 506.797, 419.815, 506.797);
        ((GeneralPath)shape).curveTo(420.329, 506.797, 420.408, 506.83798, 420.408, 507.404);
        ((GeneralPath)shape).lineTo(419.236, 507.404);
        ((GeneralPath)shape).moveTo(420.408, 508.135);
        ((GeneralPath)shape).curveTo(420.408, 508.49802, 420.164, 508.49802, 419.815, 508.49802);
        ((GeneralPath)shape).curveTo(419.251, 508.49802, 419.236, 508.329, 419.236, 507.795);
        ((GeneralPath)shape).lineTo(421.033, 507.795);
        ((GeneralPath)shape).curveTo(421.033, 506.631, 420.888, 506.311, 419.815, 506.311);
        ((GeneralPath)shape).curveTo(418.759, 506.311, 418.611, 506.716, 418.611, 507.647);
        ((GeneralPath)shape).curveTo(418.611, 508.652, 418.815, 508.967, 419.815, 508.967);
        ((GeneralPath)shape).curveTo(420.56, 508.967, 421.033, 508.928, 421.033, 508.135);
        ((GeneralPath)shape).lineTo(420.408, 508.135);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_324;
        g.setTransform(defaultTransform__0_324);
        g.setClip(clip__0_324);
        float alpha__0_325 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_325 = g.getClip();
        AffineTransform defaultTransform__0_325 = g.getTransform();
        
//      _0_325 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(423.258, 508.967);
        ((GeneralPath)shape).lineTo(423.883, 508.967);
        ((GeneralPath)shape).lineTo(423.883, 505.139);
        ((GeneralPath)shape).lineTo(423.258, 505.139);
        ((GeneralPath)shape).lineTo(423.258, 506.696);
        ((GeneralPath)shape).lineTo(423.24298, 506.696);
        ((GeneralPath)shape).curveTo(423.10898, 506.37302, 422.77698, 506.311, 422.45898, 506.311);
        ((GeneralPath)shape).curveTo(421.564, 506.311, 421.461, 506.801, 421.461, 507.56302);
        ((GeneralPath)shape).curveTo(421.461, 508.36002, 421.461, 508.967, 422.438, 508.967);
        ((GeneralPath)shape).curveTo(422.80798, 508.967, 423.098, 508.89102, 423.258, 508.556);
        ((GeneralPath)shape).lineTo(423.258, 508.967);
        ((GeneralPath)shape).moveTo(422.643, 506.797);
        ((GeneralPath)shape).curveTo(423.184, 506.797, 423.258, 507.01898, 423.258, 507.57);
        ((GeneralPath)shape).curveTo(423.258, 508.187, 423.258, 508.49802, 422.638, 508.49802);
        ((GeneralPath)shape).curveTo(422.086, 508.49802, 422.086, 508.15802, 422.086, 507.57);
        ((GeneralPath)shape).curveTo(422.086, 506.92, 422.211, 506.797, 422.643, 506.797);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_325;
        g.setTransform(defaultTransform__0_325);
        g.setClip(clip__0_325);
        float alpha__0_326 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_326 = g.getClip();
        AffineTransform defaultTransform__0_326 = g.getTransform();
        
//      _0_326 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        stroke = new BasicStroke(1.0f,0,0,10.0f,null,0.0f);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(372.65, 508.398);
        ((GeneralPath)shape).curveTo(372.65, 509.19202, 372.005, 509.836, 371.212, 509.836);
        ((GeneralPath)shape).lineTo(367.336, 509.836);
        ((GeneralPath)shape).curveTo(366.543, 509.836, 365.898, 509.192, 365.898, 508.398);
        ((GeneralPath)shape).lineTo(365.898, 504.48203);
        ((GeneralPath)shape).curveTo(365.898, 503.68903, 366.543, 503.04404, 367.336, 503.04404);
        ((GeneralPath)shape).lineTo(371.212, 503.04404);
        ((GeneralPath)shape).curveTo(372.005, 503.04404, 372.65, 503.68903, 372.65, 504.48203);
        ((GeneralPath)shape).lineTo(372.65, 508.398);
        ((GeneralPath)shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_326;
        g.setTransform(defaultTransform__0_326);
        g.setClip(clip__0_326);
        float alpha__0_327 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_327 = g.getClip();
        AffineTransform defaultTransform__0_327 = g.getTransform();
        
//      _0_327 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(317.1, 520.188);
        ((GeneralPath)shape).lineTo(315.63, 520.188);
        ((GeneralPath)shape).lineTo(317.08002, 518.213);
        ((GeneralPath)shape).lineTo(317.1, 518.213);
        ((GeneralPath)shape).lineTo(317.1, 520.188);
        ((GeneralPath)shape).moveTo(317.803, 517.688);
        ((GeneralPath)shape).lineTo(316.72, 517.688);
        ((GeneralPath)shape).lineTo(315.06, 519.936);
        ((GeneralPath)shape).lineTo(315.06, 520.735);
        ((GeneralPath)shape).lineTo(317.1, 520.735);
        ((GeneralPath)shape).lineTo(317.1, 521.516);
        ((GeneralPath)shape).lineTo(317.803, 521.516);
        ((GeneralPath)shape).lineTo(317.803, 520.735);
        ((GeneralPath)shape).lineTo(318.242, 520.735);
        ((GeneralPath)shape).lineTo(318.242, 520.188);
        ((GeneralPath)shape).lineTo(317.803, 520.188);
        ((GeneralPath)shape).lineTo(317.803, 517.688);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_327;
        g.setTransform(defaultTransform__0_327);
        g.setClip(clip__0_327);
        float alpha__0_328 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_328 = g.getClip();
        AffineTransform defaultTransform__0_328 = g.getTransform();
        
//      _0_328 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(328.873, 519.641);
        ((GeneralPath)shape).lineTo(328.873, 518.31);
        ((GeneralPath)shape).lineTo(329.844, 518.31);
        ((GeneralPath)shape).curveTo(330.343, 518.31, 330.436, 518.409, 330.436, 518.959);
        ((GeneralPath)shape).curveTo(330.436, 519.531, 330.31, 519.641, 329.795, 519.641);
        ((GeneralPath)shape).lineTo(328.873, 519.641);
        ((GeneralPath)shape).moveTo(329.966, 520.266);
        ((GeneralPath)shape).curveTo(330.308, 520.266, 330.436, 520.489, 330.436, 520.807);
        ((GeneralPath)shape).lineTo(330.436, 521.516);
        ((GeneralPath)shape).lineTo(331.139, 521.516);
        ((GeneralPath)shape).lineTo(331.139, 520.809);
        ((GeneralPath)shape).curveTo(331.139, 520.27405, 331.012, 519.99603, 330.492, 519.95105);
        ((GeneralPath)shape).lineTo(330.492, 519.93005);
        ((GeneralPath)shape).curveTo(331.139, 519.8301, 331.139, 519.41003, 331.139, 518.83405);
        ((GeneralPath)shape).curveTo(331.139, 517.952, 330.832, 517.68805, 330.035, 517.68805);
        ((GeneralPath)shape).lineTo(328.171, 517.68805);
        ((GeneralPath)shape).lineTo(328.171, 521.51605);
        ((GeneralPath)shape).lineTo(328.874, 521.51605);
        ((GeneralPath)shape).lineTo(328.874, 520.26605);
        ((GeneralPath)shape).lineTo(329.966, 520.26605);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_328;
        g.setTransform(defaultTransform__0_328);
        g.setClip(clip__0_328);
        float alpha__0_329 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_329 = g.getClip();
        AffineTransform defaultTransform__0_329 = g.getTransform();
        
//      _0_329 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(332.405, 517.688);
        ((GeneralPath)shape).lineTo(331.78, 517.688);
        ((GeneralPath)shape).lineTo(331.78, 518.229);
        ((GeneralPath)shape).lineTo(332.405, 518.229);
        ((GeneralPath)shape).lineTo(332.405, 517.688);
        ((GeneralPath)shape).moveTo(332.405, 518.859);
        ((GeneralPath)shape).lineTo(331.78, 518.859);
        ((GeneralPath)shape).lineTo(331.78, 521.515);
        ((GeneralPath)shape).lineTo(332.405, 521.515);
        ((GeneralPath)shape).lineTo(332.405, 518.859);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_329;
        g.setTransform(defaultTransform__0_329);
        g.setClip(clip__0_329);
        float alpha__0_330 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_330 = g.getClip();
        AffineTransform defaultTransform__0_330 = g.getTransform();
        
//      _0_330 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(334.679, 521.639);
        ((GeneralPath)shape).curveTo(334.679, 522.105, 334.569, 522.219, 334.055, 522.219);
        ((GeneralPath)shape).curveTo(333.715, 522.219, 333.585, 522.16, 333.585, 521.773);
        ((GeneralPath)shape).lineTo(332.96, 521.773);
        ((GeneralPath)shape).curveTo(332.91998, 522.575, 333.4, 522.688, 334.042, 522.688);
        ((GeneralPath)shape).curveTo(335.003, 522.688, 335.304, 522.45996, 335.304, 521.506);
        ((GeneralPath)shape).lineTo(335.304, 518.86);
        ((GeneralPath)shape).lineTo(334.679, 518.86);
        ((GeneralPath)shape).lineTo(334.679, 519.286);
        ((GeneralPath)shape).curveTo(334.51898, 518.94, 334.22897, 518.86, 333.85898, 518.86);
        ((GeneralPath)shape).curveTo(332.882, 518.86, 332.882, 519.467, 332.882, 520.264);
        ((GeneralPath)shape).curveTo(332.882, 521.026, 332.986, 521.516, 333.87198, 521.516);
        ((GeneralPath)shape).curveTo(334.18597, 521.516, 334.51697, 521.457, 334.679, 521.12);
        ((GeneralPath)shape).lineTo(334.679, 521.639);
        ((GeneralPath)shape).moveTo(334.059, 519.345);
        ((GeneralPath)shape).curveTo(334.595, 519.345, 334.679, 519.56696, 334.679, 520.11896);
        ((GeneralPath)shape).curveTo(334.679, 520.735, 334.679, 521.04694, 334.059, 521.04694);
        ((GeneralPath)shape).curveTo(333.507, 521.04694, 333.507, 520.70593, 333.507, 520.11896);
        ((GeneralPath)shape).curveTo(333.507, 519.468, 333.63, 519.345, 334.059, 519.345);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_330;
        g.setTransform(defaultTransform__0_330);
        g.setClip(clip__0_330);
        float alpha__0_331 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_331 = g.getClip();
        AffineTransform defaultTransform__0_331 = g.getTransform();
        
//      _0_331 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(336.555, 517.688);
        ((GeneralPath)shape).lineTo(335.93, 517.688);
        ((GeneralPath)shape).lineTo(335.93, 521.516);
        ((GeneralPath)shape).lineTo(336.555, 521.516);
        ((GeneralPath)shape).lineTo(336.555, 520.074);
        ((GeneralPath)shape).curveTo(336.555, 519.605, 336.628, 519.345, 337.168, 519.345);
        ((GeneralPath)shape).curveTo(337.565, 519.345, 337.648, 519.475, 337.648, 519.85394);
        ((GeneralPath)shape).lineTo(337.648, 521.5159);
        ((GeneralPath)shape).lineTo(338.273, 521.5159);
        ((GeneralPath)shape).lineTo(338.273, 519.7849);
        ((GeneralPath)shape).curveTo(338.273, 519.1449, 338.06702, 518.8599, 337.38702, 518.8599);
        ((GeneralPath)shape).curveTo(337.02203, 518.8599, 336.71103, 518.8999, 336.57404, 519.2899);
        ((GeneralPath)shape).lineTo(336.55405, 519.2899);
        ((GeneralPath)shape).lineTo(336.55405, 517.688);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_331;
        g.setTransform(defaultTransform__0_331);
        g.setClip(clip__0_331);
        float alpha__0_332 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_332 = g.getClip();
        AffineTransform defaultTransform__0_332 = g.getTransform();
        
//      _0_332 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(338.591, 519.328);
        ((GeneralPath)shape).lineTo(338.92, 519.328);
        ((GeneralPath)shape).lineTo(338.92, 520.696);
        ((GeneralPath)shape).curveTo(338.92, 521.35596, 339.13303, 521.51495, 339.828, 521.51495);
        ((GeneralPath)shape).curveTo(340.516, 521.51495, 340.717, 521.27496, 340.717, 520.483);
        ((GeneralPath)shape).lineTo(340.17, 520.483);
        ((GeneralPath)shape).curveTo(340.17, 520.761, 340.209, 521.04596, 339.829, 521.04596);
        ((GeneralPath)shape).curveTo(339.545, 521.04596, 339.545, 520.93494, 339.545, 520.691);
        ((GeneralPath)shape).lineTo(339.545, 519.328);
        ((GeneralPath)shape).lineTo(340.584, 519.328);
        ((GeneralPath)shape).lineTo(340.584, 518.859);
        ((GeneralPath)shape).lineTo(339.545, 518.859);
        ((GeneralPath)shape).lineTo(339.545, 518.24);
        ((GeneralPath)shape).lineTo(338.92, 518.24);
        ((GeneralPath)shape).lineTo(338.92, 518.859);
        ((GeneralPath)shape).lineTo(338.591, 518.859);
        ((GeneralPath)shape).lineTo(338.591, 519.328);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_332;
        g.setTransform(defaultTransform__0_332);
        g.setClip(clip__0_332);
        float alpha__0_333 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_333 = g.getClip();
        AffineTransform defaultTransform__0_333 = g.getTransform();
        
//      _0_333 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(344.945, 520.266);
        ((GeneralPath)shape).lineTo(343.643, 520.266);
        ((GeneralPath)shape).lineTo(344.28702, 518.245);
        ((GeneralPath)shape).lineTo(344.29703, 518.245);
        ((GeneralPath)shape).lineTo(344.945, 520.266);
        ((GeneralPath)shape).moveTo(345.106, 520.813);
        ((GeneralPath)shape).lineTo(345.352, 521.516);
        ((GeneralPath)shape).lineTo(346.085, 521.516);
        ((GeneralPath)shape).lineTo(344.809, 517.688);
        ((GeneralPath)shape).lineTo(343.744, 517.688);
        ((GeneralPath)shape).lineTo(342.49197, 521.516);
        ((GeneralPath)shape).lineTo(343.24197, 521.516);
        ((GeneralPath)shape).lineTo(343.47696, 520.813);
        ((GeneralPath)shape).lineTo(345.106, 520.813);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_333;
        g.setTransform(defaultTransform__0_333);
        g.setClip(clip__0_333);
        float alpha__0_334 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_334 = g.getClip();
        AffineTransform defaultTransform__0_334 = g.getTransform();
        
//      _0_334 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(346.271, 518.859);
        ((GeneralPath)shape).lineTo(346.271, 521.515);
        ((GeneralPath)shape).lineTo(346.896, 521.515);
        ((GeneralPath)shape).lineTo(346.896, 519.898);
        ((GeneralPath)shape).curveTo(346.896, 519.559, 347.013, 519.344, 347.414, 519.344);
        ((GeneralPath)shape).curveTo(347.729, 519.344, 347.756, 519.499, 347.756, 519.763);
        ((GeneralPath)shape).lineTo(347.756, 519.898);
        ((GeneralPath)shape).lineTo(348.381, 519.898);
        ((GeneralPath)shape).lineTo(348.381, 519.687);
        ((GeneralPath)shape).curveTo(348.381, 519.19104, 348.237, 518.859, 347.644, 518.859);
        ((GeneralPath)shape).curveTo(347.32, 518.859, 347.03, 518.943, 346.897, 519.231);
        ((GeneralPath)shape).lineTo(346.897, 518.859);
        ((GeneralPath)shape).lineTo(346.271, 518.859);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_334;
        g.setTransform(defaultTransform__0_334);
        g.setClip(clip__0_334);
        float alpha__0_335 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_335 = g.getClip();
        AffineTransform defaultTransform__0_335 = g.getTransform();
        
//      _0_335 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(348.745, 518.859);
        ((GeneralPath)shape).lineTo(348.745, 521.515);
        ((GeneralPath)shape).lineTo(349.37, 521.515);
        ((GeneralPath)shape).lineTo(349.37, 520.037);
        ((GeneralPath)shape).curveTo(349.37, 519.573, 349.47598, 519.344, 350.01498, 519.344);
        ((GeneralPath)shape).curveTo(350.37997, 519.344, 350.464, 519.474, 350.464, 519.803);
        ((GeneralPath)shape).lineTo(350.464, 521.51495);
        ((GeneralPath)shape).lineTo(351.089, 521.51495);
        ((GeneralPath)shape).lineTo(351.089, 520.0369);
        ((GeneralPath)shape).curveTo(351.089, 519.57294, 351.18698, 519.34393, 351.688, 519.34393);
        ((GeneralPath)shape).curveTo(352.02698, 519.34393, 352.10498, 519.47394, 352.10498, 519.8029);
        ((GeneralPath)shape).lineTo(352.10498, 521.5149);
        ((GeneralPath)shape).lineTo(352.72998, 521.5149);
        ((GeneralPath)shape).lineTo(352.72998, 519.7389);
        ((GeneralPath)shape).curveTo(352.72998, 519.0939, 352.50998, 518.8589, 351.84497, 518.8589);
        ((GeneralPath)shape).curveTo(351.50296, 518.8589, 351.15198, 518.9599, 351.03397, 519.32886);
        ((GeneralPath)shape).lineTo(351.01398, 519.32886);
        ((GeneralPath)shape).curveTo(350.942, 518.94385, 350.55197, 518.8589, 350.214, 518.8589);
        ((GeneralPath)shape).curveTo(349.876, 518.8589, 349.512, 518.93286, 349.369, 519.26886);
        ((GeneralPath)shape).lineTo(349.369, 518.8589);
        ((GeneralPath)shape).lineTo(348.745, 518.8589);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_335;
        g.setTransform(defaultTransform__0_335);
        g.setClip(clip__0_335);
        float alpha__0_336 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_336 = g.getClip();
        AffineTransform defaultTransform__0_336 = g.getTransform();
        
//      _0_336 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(376.106, 518.859);
        ((GeneralPath)shape).lineTo(376.106, 519.875);
        ((GeneralPath)shape).lineTo(375.136, 519.875);
        ((GeneralPath)shape).lineTo(375.136, 520.344);
        ((GeneralPath)shape).lineTo(376.106, 520.344);
        ((GeneralPath)shape).lineTo(376.106, 521.324);
        ((GeneralPath)shape).lineTo(376.575, 521.324);
        ((GeneralPath)shape).lineTo(376.575, 520.344);
        ((GeneralPath)shape).lineTo(377.546, 520.344);
        ((GeneralPath)shape).lineTo(377.546, 519.875);
        ((GeneralPath)shape).lineTo(376.575, 519.875);
        ((GeneralPath)shape).lineTo(376.575, 518.859);
        ((GeneralPath)shape).lineTo(376.106, 518.859);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_336;
        g.setTransform(defaultTransform__0_336);
        g.setClip(clip__0_336);
        float alpha__0_337 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_337 = g.getClip();
        AffineTransform defaultTransform__0_337 = g.getTransform();
        
//      _0_337 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(380.276, 517.688);
        ((GeneralPath)shape).lineTo(379.388, 517.688);
        ((GeneralPath)shape).lineTo(378.188, 518.836);
        ((GeneralPath)shape).lineTo(378.599, 519.291);
        ((GeneralPath)shape).lineTo(379.573, 518.294);
        ((GeneralPath)shape).lineTo(379.573, 521.516);
        ((GeneralPath)shape).lineTo(380.276, 521.516);
        ((GeneralPath)shape).lineTo(380.276, 517.688);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_337;
        g.setTransform(defaultTransform__0_337);
        g.setClip(clip__0_337);
        float alpha__0_338 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_338 = g.getClip();
        AffineTransform defaultTransform__0_338 = g.getTransform();
        
//      _0_338 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(383.044, 519.328);
        ((GeneralPath)shape).lineTo(383.374, 519.328);
        ((GeneralPath)shape).lineTo(383.374, 520.696);
        ((GeneralPath)shape).curveTo(383.374, 521.35596, 383.586, 521.51495, 384.28198, 521.51495);
        ((GeneralPath)shape).curveTo(384.96997, 521.51495, 385.171, 521.27496, 385.171, 520.483);
        ((GeneralPath)shape).lineTo(384.624, 520.483);
        ((GeneralPath)shape).curveTo(384.624, 520.761, 384.663, 521.04596, 384.283, 521.04596);
        ((GeneralPath)shape).curveTo(383.999, 521.04596, 383.999, 520.93494, 383.999, 520.691);
        ((GeneralPath)shape).lineTo(383.999, 519.328);
        ((GeneralPath)shape).lineTo(385.038, 519.328);
        ((GeneralPath)shape).lineTo(385.038, 518.859);
        ((GeneralPath)shape).lineTo(383.999, 518.859);
        ((GeneralPath)shape).lineTo(383.999, 518.24);
        ((GeneralPath)shape).lineTo(383.374, 518.24);
        ((GeneralPath)shape).lineTo(383.374, 518.859);
        ((GeneralPath)shape).lineTo(383.044, 518.859);
        ((GeneralPath)shape).lineTo(383.044, 519.328);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_338;
        g.setTransform(defaultTransform__0_338);
        g.setClip(clip__0_338);
        float alpha__0_339 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_339 = g.getClip();
        AffineTransform defaultTransform__0_339 = g.getTransform();
        
//      _0_339 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(386.632, 519.345);
        ((GeneralPath)shape).curveTo(387.17398, 519.345, 387.218, 519.50195, 387.218, 520.20294);
        ((GeneralPath)shape).curveTo(387.218, 520.89294, 387.17398, 521.04694, 386.632, 521.04694);
        ((GeneralPath)shape).curveTo(386.09, 521.04694, 386.046, 520.8939, 386.046, 520.20294);
        ((GeneralPath)shape).curveTo(386.046, 519.502, 386.09, 519.345, 386.632, 519.345);
        ((GeneralPath)shape).moveTo(386.632, 518.859);
        ((GeneralPath)shape).curveTo(385.55798, 518.859, 385.421, 519.179, 385.421, 520.189);
        ((GeneralPath)shape).curveTo(385.421, 521.19403, 385.55798, 521.515, 386.632, 521.515);
        ((GeneralPath)shape).curveTo(387.706, 521.515, 387.843, 521.19403, 387.843, 520.189);
        ((GeneralPath)shape).curveTo(387.843, 519.18, 387.706, 518.859, 386.632, 518.859);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_339;
        g.setTransform(defaultTransform__0_339);
        g.setClip(clip__0_339);
        float alpha__0_340 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_340 = g.getClip();
        AffineTransform defaultTransform__0_340 = g.getTransform();
        
//      _0_340 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(393.372, 517.688);
        ((GeneralPath)shape).lineTo(392.669, 517.688);
        ((GeneralPath)shape).lineTo(392.669, 519.25);
        ((GeneralPath)shape).lineTo(390.872, 519.25);
        ((GeneralPath)shape).lineTo(390.872, 517.688);
        ((GeneralPath)shape).lineTo(390.169, 517.688);
        ((GeneralPath)shape).lineTo(390.169, 521.516);
        ((GeneralPath)shape).lineTo(390.872, 521.516);
        ((GeneralPath)shape).lineTo(390.872, 519.875);
        ((GeneralPath)shape).lineTo(392.669, 519.875);
        ((GeneralPath)shape).lineTo(392.669, 521.516);
        ((GeneralPath)shape).lineTo(393.372, 521.516);
        ((GeneralPath)shape).lineTo(393.372, 517.688);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_340;
        g.setTransform(defaultTransform__0_340);
        g.setClip(clip__0_340);
        float alpha__0_341 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_341 = g.getClip();
        AffineTransform defaultTransform__0_341 = g.getTransform();
        
//      _0_341 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(394.639, 517.688);
        ((GeneralPath)shape).lineTo(394.014, 517.688);
        ((GeneralPath)shape).lineTo(394.014, 518.229);
        ((GeneralPath)shape).lineTo(394.639, 518.229);
        ((GeneralPath)shape).lineTo(394.639, 517.688);
        ((GeneralPath)shape).moveTo(394.639, 518.859);
        ((GeneralPath)shape).lineTo(394.014, 518.859);
        ((GeneralPath)shape).lineTo(394.014, 521.515);
        ((GeneralPath)shape).lineTo(394.639, 521.515);
        ((GeneralPath)shape).lineTo(394.639, 518.859);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_341;
        g.setTransform(defaultTransform__0_341);
        g.setClip(clip__0_341);
        float alpha__0_342 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_342 = g.getClip();
        AffineTransform defaultTransform__0_342 = g.getTransform();
        
//      _0_342 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(394.864, 519.328);
        ((GeneralPath)shape).lineTo(395.194, 519.328);
        ((GeneralPath)shape).lineTo(395.194, 520.696);
        ((GeneralPath)shape).curveTo(395.194, 521.35596, 395.406, 521.51495, 396.102, 521.51495);
        ((GeneralPath)shape).curveTo(396.789, 521.51495, 396.991, 521.27496, 396.991, 520.483);
        ((GeneralPath)shape).lineTo(396.444, 520.483);
        ((GeneralPath)shape).curveTo(396.444, 520.761, 396.483, 521.04596, 396.103, 521.04596);
        ((GeneralPath)shape).curveTo(395.819, 521.04596, 395.819, 520.93494, 395.819, 520.691);
        ((GeneralPath)shape).lineTo(395.819, 519.328);
        ((GeneralPath)shape).lineTo(396.857, 519.328);
        ((GeneralPath)shape).lineTo(396.857, 518.859);
        ((GeneralPath)shape).lineTo(395.819, 518.859);
        ((GeneralPath)shape).lineTo(395.819, 518.24);
        ((GeneralPath)shape).lineTo(395.194, 518.24);
        ((GeneralPath)shape).lineTo(395.194, 518.859);
        ((GeneralPath)shape).lineTo(394.864, 518.859);
        ((GeneralPath)shape).lineTo(394.864, 519.328);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_342;
        g.setTransform(defaultTransform__0_342);
        g.setClip(clip__0_342);
        float alpha__0_343 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_343 = g.getClip();
        AffineTransform defaultTransform__0_343 = g.getTransform();
        
//      _0_343 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        stroke = new BasicStroke(1.0f,0,0,10.0f,null,0.0f);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(372.65, 521.732);
        ((GeneralPath)shape).curveTo(372.65, 522.526, 372.005, 523.17, 371.212, 523.17);
        ((GeneralPath)shape).lineTo(367.336, 523.17);
        ((GeneralPath)shape).curveTo(366.543, 523.17, 365.898, 522.526, 365.898, 521.732);
        ((GeneralPath)shape).lineTo(365.898, 517.818);
        ((GeneralPath)shape).curveTo(365.898, 517.023, 366.543, 516.37897, 367.336, 516.37897);
        ((GeneralPath)shape).lineTo(371.212, 516.37897);
        ((GeneralPath)shape).curveTo(372.005, 516.37897, 372.65, 517.024, 372.65, 517.818);
        ((GeneralPath)shape).lineTo(372.65, 521.732);
        ((GeneralPath)shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_343;
        g.setTransform(defaultTransform__0_343);
        g.setClip(clip__0_343);
        float alpha__0_344 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_344 = g.getClip();
        AffineTransform defaultTransform__0_344 = g.getTransform();
        
//      _0_344 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(419.874, 519.641);
        ((GeneralPath)shape).lineTo(419.874, 518.31);
        ((GeneralPath)shape).lineTo(420.845, 518.31);
        ((GeneralPath)shape).curveTo(421.344, 518.31, 421.437, 518.409, 421.437, 518.959);
        ((GeneralPath)shape).curveTo(421.437, 519.531, 421.312, 519.641, 420.79602, 519.641);
        ((GeneralPath)shape).lineTo(419.874, 519.641);
        ((GeneralPath)shape).moveTo(420.967, 520.266);
        ((GeneralPath)shape).curveTo(421.30902, 520.266, 421.437, 520.489, 421.437, 520.807);
        ((GeneralPath)shape).lineTo(421.437, 521.516);
        ((GeneralPath)shape).lineTo(422.14, 521.516);
        ((GeneralPath)shape).lineTo(422.14, 520.809);
        ((GeneralPath)shape).curveTo(422.14, 520.27405, 422.013, 519.99603, 421.49402, 519.95105);
        ((GeneralPath)shape).lineTo(421.49402, 519.93005);
        ((GeneralPath)shape).curveTo(422.14, 519.8301, 422.14, 519.41003, 422.14, 518.83405);
        ((GeneralPath)shape).curveTo(422.14, 517.952, 421.834, 517.68805, 421.036, 517.68805);
        ((GeneralPath)shape).lineTo(419.172, 517.68805);
        ((GeneralPath)shape).lineTo(419.172, 521.51605);
        ((GeneralPath)shape).lineTo(419.875, 521.51605);
        ((GeneralPath)shape).lineTo(419.875, 520.26605);
        ((GeneralPath)shape).lineTo(420.967, 520.26605);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_344;
        g.setTransform(defaultTransform__0_344);
        g.setClip(clip__0_344);
        float alpha__0_345 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_345 = g.getClip();
        AffineTransform defaultTransform__0_345 = g.getTransform();
        
//      _0_345 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(423.406, 517.688);
        ((GeneralPath)shape).lineTo(422.781, 517.688);
        ((GeneralPath)shape).lineTo(422.781, 518.229);
        ((GeneralPath)shape).lineTo(423.406, 518.229);
        ((GeneralPath)shape).lineTo(423.406, 517.688);
        ((GeneralPath)shape).moveTo(423.406, 518.859);
        ((GeneralPath)shape).lineTo(422.781, 518.859);
        ((GeneralPath)shape).lineTo(422.781, 521.515);
        ((GeneralPath)shape).lineTo(423.406, 521.515);
        ((GeneralPath)shape).lineTo(423.406, 518.859);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_345;
        g.setTransform(defaultTransform__0_345);
        g.setClip(clip__0_345);
        float alpha__0_346 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_346 = g.getClip();
        AffineTransform defaultTransform__0_346 = g.getTransform();
        
//      _0_346 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(425.681, 521.639);
        ((GeneralPath)shape).curveTo(425.681, 522.105, 425.571, 522.219, 425.057, 522.219);
        ((GeneralPath)shape).curveTo(424.716, 522.219, 424.587, 522.16, 424.587, 521.773);
        ((GeneralPath)shape).lineTo(423.962, 521.773);
        ((GeneralPath)shape).curveTo(423.92102, 522.575, 424.402, 522.688, 425.043, 522.688);
        ((GeneralPath)shape).curveTo(426.005, 522.688, 426.306, 522.45996, 426.306, 521.506);
        ((GeneralPath)shape).lineTo(426.306, 518.86);
        ((GeneralPath)shape).lineTo(425.681, 518.86);
        ((GeneralPath)shape).lineTo(425.681, 519.286);
        ((GeneralPath)shape).curveTo(425.521, 518.94, 425.23, 518.86, 424.861, 518.86);
        ((GeneralPath)shape).curveTo(423.884, 518.86, 423.884, 519.467, 423.884, 520.264);
        ((GeneralPath)shape).curveTo(423.884, 521.026, 423.988, 521.516, 424.87302, 521.516);
        ((GeneralPath)shape).curveTo(425.18802, 521.516, 425.518, 521.457, 425.68103, 521.12);
        ((GeneralPath)shape).lineTo(425.68103, 521.639);
        ((GeneralPath)shape).moveTo(425.061, 519.345);
        ((GeneralPath)shape).curveTo(425.596, 519.345, 425.681, 519.56696, 425.681, 520.11896);
        ((GeneralPath)shape).curveTo(425.681, 520.735, 425.681, 521.04694, 425.061, 521.04694);
        ((GeneralPath)shape).curveTo(424.509, 521.04694, 424.509, 520.70593, 424.509, 520.11896);
        ((GeneralPath)shape).curveTo(424.509, 519.468, 424.632, 519.345, 425.061, 519.345);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_346;
        g.setTransform(defaultTransform__0_346);
        g.setClip(clip__0_346);
        float alpha__0_347 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_347 = g.getClip();
        AffineTransform defaultTransform__0_347 = g.getTransform();
        
//      _0_347 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(427.557, 517.688);
        ((GeneralPath)shape).lineTo(426.932, 517.688);
        ((GeneralPath)shape).lineTo(426.932, 521.516);
        ((GeneralPath)shape).lineTo(427.557, 521.516);
        ((GeneralPath)shape).lineTo(427.557, 520.074);
        ((GeneralPath)shape).curveTo(427.557, 519.605, 427.63, 519.345, 428.169, 519.345);
        ((GeneralPath)shape).curveTo(428.56702, 519.345, 428.65, 519.475, 428.65, 519.85394);
        ((GeneralPath)shape).lineTo(428.65, 521.5159);
        ((GeneralPath)shape).lineTo(429.275, 521.5159);
        ((GeneralPath)shape).lineTo(429.275, 519.7849);
        ((GeneralPath)shape).curveTo(429.275, 519.1449, 429.069, 518.8599, 428.388, 518.8599);
        ((GeneralPath)shape).curveTo(428.024, 518.8599, 427.713, 518.8999, 427.575, 519.2899);
        ((GeneralPath)shape).lineTo(427.55502, 519.2899);
        ((GeneralPath)shape).lineTo(427.55502, 517.688);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_347;
        g.setTransform(defaultTransform__0_347);
        g.setClip(clip__0_347);
        float alpha__0_348 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_348 = g.getClip();
        AffineTransform defaultTransform__0_348 = g.getTransform();
        
//      _0_348 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(429.592, 519.328);
        ((GeneralPath)shape).lineTo(429.922, 519.328);
        ((GeneralPath)shape).lineTo(429.922, 520.696);
        ((GeneralPath)shape).curveTo(429.922, 521.35596, 430.134, 521.51495, 430.83, 521.51495);
        ((GeneralPath)shape).curveTo(431.51797, 521.51495, 431.719, 521.27496, 431.719, 520.483);
        ((GeneralPath)shape).lineTo(431.172, 520.483);
        ((GeneralPath)shape).curveTo(431.172, 520.761, 431.211, 521.04596, 430.831, 521.04596);
        ((GeneralPath)shape).curveTo(430.547, 521.04596, 430.547, 520.93494, 430.547, 520.691);
        ((GeneralPath)shape).lineTo(430.547, 519.328);
        ((GeneralPath)shape).lineTo(431.586, 519.328);
        ((GeneralPath)shape).lineTo(431.586, 518.859);
        ((GeneralPath)shape).lineTo(430.547, 518.859);
        ((GeneralPath)shape).lineTo(430.547, 518.24);
        ((GeneralPath)shape).lineTo(429.922, 518.24);
        ((GeneralPath)shape).lineTo(429.922, 518.859);
        ((GeneralPath)shape).lineTo(429.592, 518.859);
        ((GeneralPath)shape).lineTo(429.592, 519.328);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_348;
        g.setTransform(defaultTransform__0_348);
        g.setClip(clip__0_348);
        float alpha__0_349 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_349 = g.getClip();
        AffineTransform defaultTransform__0_349 = g.getTransform();
        
//      _0_349 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(435.946, 520.266);
        ((GeneralPath)shape).lineTo(434.64502, 520.266);
        ((GeneralPath)shape).lineTo(435.28903, 518.245);
        ((GeneralPath)shape).lineTo(435.29904, 518.245);
        ((GeneralPath)shape).lineTo(435.946, 520.266);
        ((GeneralPath)shape).moveTo(436.107, 520.813);
        ((GeneralPath)shape).lineTo(436.354, 521.516);
        ((GeneralPath)shape).lineTo(437.087, 521.516);
        ((GeneralPath)shape).lineTo(435.811, 517.688);
        ((GeneralPath)shape).lineTo(434.745, 517.688);
        ((GeneralPath)shape).lineTo(433.494, 521.516);
        ((GeneralPath)shape).lineTo(434.24298, 521.516);
        ((GeneralPath)shape).lineTo(434.47897, 520.813);
        ((GeneralPath)shape).lineTo(436.107, 520.813);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_349;
        g.setTransform(defaultTransform__0_349);
        g.setClip(clip__0_349);
        float alpha__0_350 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_350 = g.getClip();
        AffineTransform defaultTransform__0_350 = g.getTransform();
        
//      _0_350 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(437.273, 518.859);
        ((GeneralPath)shape).lineTo(437.273, 521.515);
        ((GeneralPath)shape).lineTo(437.898, 521.515);
        ((GeneralPath)shape).lineTo(437.898, 519.898);
        ((GeneralPath)shape).curveTo(437.898, 519.559, 438.015, 519.344, 438.41602, 519.344);
        ((GeneralPath)shape).curveTo(438.73, 519.344, 438.75803, 519.499, 438.75803, 519.763);
        ((GeneralPath)shape).lineTo(438.75803, 519.898);
        ((GeneralPath)shape).lineTo(439.38303, 519.898);
        ((GeneralPath)shape).lineTo(439.38303, 519.687);
        ((GeneralPath)shape).curveTo(439.38303, 519.19104, 439.23804, 518.859, 438.64603, 518.859);
        ((GeneralPath)shape).curveTo(438.321, 518.859, 438.032, 518.943, 437.89902, 519.231);
        ((GeneralPath)shape).lineTo(437.89902, 518.859);
        ((GeneralPath)shape).lineTo(437.273, 518.859);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_350;
        g.setTransform(defaultTransform__0_350);
        g.setClip(clip__0_350);
        float alpha__0_351 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_351 = g.getClip();
        AffineTransform defaultTransform__0_351 = g.getTransform();
        
//      _0_351 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(439.746, 518.859);
        ((GeneralPath)shape).lineTo(439.746, 521.515);
        ((GeneralPath)shape).lineTo(440.371, 521.515);
        ((GeneralPath)shape).lineTo(440.371, 520.037);
        ((GeneralPath)shape).curveTo(440.371, 519.573, 440.477, 519.344, 441.016, 519.344);
        ((GeneralPath)shape).curveTo(441.38098, 519.344, 441.465, 519.474, 441.465, 519.803);
        ((GeneralPath)shape).lineTo(441.465, 521.51495);
        ((GeneralPath)shape).lineTo(442.09, 521.51495);
        ((GeneralPath)shape).lineTo(442.09, 520.0369);
        ((GeneralPath)shape).curveTo(442.09, 519.57294, 442.188, 519.34393, 442.689, 519.34393);
        ((GeneralPath)shape).curveTo(443.02798, 519.34393, 443.106, 519.47394, 443.106, 519.8029);
        ((GeneralPath)shape).lineTo(443.106, 521.5149);
        ((GeneralPath)shape).lineTo(443.731, 521.5149);
        ((GeneralPath)shape).lineTo(443.731, 519.7389);
        ((GeneralPath)shape).curveTo(443.731, 519.0939, 443.512, 518.8589, 442.84598, 518.8589);
        ((GeneralPath)shape).curveTo(442.50397, 518.8589, 442.15298, 518.9599, 442.03497, 519.32886);
        ((GeneralPath)shape).lineTo(442.01498, 519.32886);
        ((GeneralPath)shape).curveTo(441.943, 518.94385, 441.554, 518.8589, 441.21597, 518.8589);
        ((GeneralPath)shape).curveTo(440.87698, 518.8589, 440.51398, 518.93286, 440.36996, 519.26886);
        ((GeneralPath)shape).lineTo(440.36996, 518.8589);
        ((GeneralPath)shape).lineTo(439.746, 518.8589);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_351;
        g.setTransform(defaultTransform__0_351);
        g.setClip(clip__0_351);
        float alpha__0_352 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_352 = g.getClip();
        AffineTransform defaultTransform__0_352 = g.getTransform();
        
//      _0_352 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(446.829, 520.893);
        ((GeneralPath)shape).lineTo(446.829, 518.31);
        ((GeneralPath)shape).lineTo(447.92902, 518.31);
        ((GeneralPath)shape).curveTo(448.493, 518.31, 448.704, 518.469, 448.704, 519.11);
        ((GeneralPath)shape).lineTo(448.704, 520.016);
        ((GeneralPath)shape).curveTo(448.704, 520.448, 448.551, 520.893, 448.053, 520.893);
        ((GeneralPath)shape).lineTo(446.829, 520.893);
        ((GeneralPath)shape).moveTo(446.126, 521.516);
        ((GeneralPath)shape).lineTo(448.045, 521.516);
        ((GeneralPath)shape).curveTo(449.187, 521.516, 449.407, 520.811, 449.407, 520.015);
        ((GeneralPath)shape).lineTo(449.407, 519.112);
        ((GeneralPath)shape).curveTo(449.407, 518.071, 448.966, 517.688, 447.92603, 517.688);
        ((GeneralPath)shape).lineTo(446.12604, 517.688);
        ((GeneralPath)shape).lineTo(446.12604, 521.516);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_352;
        g.setTransform(defaultTransform__0_352);
        g.setClip(clip__0_352);
        float alpha__0_353 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_353 = g.getClip();
        AffineTransform defaultTransform__0_353 = g.getTransform();
        
//      _0_353 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(450.443, 519.953);
        ((GeneralPath)shape).curveTo(450.443, 519.466, 450.478, 519.34503, 451.021, 519.34503);
        ((GeneralPath)shape).curveTo(451.536, 519.34503, 451.615, 519.387, 451.615, 519.953);
        ((GeneralPath)shape).lineTo(450.443, 519.953);
        ((GeneralPath)shape).moveTo(451.615, 520.683);
        ((GeneralPath)shape).curveTo(451.615, 521.047, 451.371, 521.047, 451.021, 521.047);
        ((GeneralPath)shape).curveTo(450.458, 521.047, 450.443, 520.877, 450.443, 520.344);
        ((GeneralPath)shape).lineTo(452.24, 520.344);
        ((GeneralPath)shape).curveTo(452.24, 519.179, 452.095, 518.86, 451.021, 518.86);
        ((GeneralPath)shape).curveTo(449.966, 518.86, 449.818, 519.264, 449.818, 520.195);
        ((GeneralPath)shape).curveTo(449.818, 521.2, 450.022, 521.516, 451.021, 521.516);
        ((GeneralPath)shape).curveTo(451.767, 521.516, 452.24, 521.477, 452.24, 520.683);
        ((GeneralPath)shape).lineTo(451.615, 520.683);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_353;
        g.setTransform(defaultTransform__0_353);
        g.setClip(clip__0_353);
        float alpha__0_354 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_354 = g.getClip();
        AffineTransform defaultTransform__0_354 = g.getTransform();
        
//      _0_354 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(454.934, 519.598);
        ((GeneralPath)shape).curveTo(454.934, 518.86005, 454.33398, 518.86005, 453.83298, 518.86005);
        ((GeneralPath)shape).curveTo(453.175, 518.86005, 452.66797, 518.86005, 452.66797, 519.56903);
        ((GeneralPath)shape).curveTo(452.66797, 520.228, 452.83798, 520.356, 453.77396, 520.37103);
        ((GeneralPath)shape).curveTo(454.37195, 520.38104, 454.38596, 520.489, 454.38596, 520.70703);
        ((GeneralPath)shape).curveTo(454.38596, 521.04803, 454.18695, 521.04803, 453.82697, 521.04803);
        ((GeneralPath)shape).curveTo(453.37897, 521.04803, 453.29196, 521.005, 453.29196, 520.64);
        ((GeneralPath)shape).lineTo(452.66696, 520.64);
        ((GeneralPath)shape).curveTo(452.66696, 521.517, 453.13196, 521.517, 453.82397, 521.517);
        ((GeneralPath)shape).curveTo(454.46896, 521.517, 455.011, 521.447, 455.011, 520.74005);
        ((GeneralPath)shape).curveTo(455.011, 519.89404, 454.468, 519.939, 453.879, 519.91003);
        ((GeneralPath)shape).curveTo(453.358, 519.887, 453.292, 519.877, 453.292, 519.627);
        ((GeneralPath)shape).curveTo(453.292, 519.297, 453.512, 519.297, 453.83, 519.297);
        ((GeneralPath)shape).curveTo(454.15, 519.297, 454.30798, 519.297, 454.30798, 519.6);
        ((GeneralPath)shape).lineTo(454.934, 519.6);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_354;
        g.setTransform(defaultTransform__0_354);
        g.setClip(clip__0_354);
        float alpha__0_355 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_355 = g.getClip();
        AffineTransform defaultTransform__0_355 = g.getTransform();
        
//      _0_355 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(455.201, 519.328);
        ((GeneralPath)shape).lineTo(455.53098, 519.328);
        ((GeneralPath)shape).lineTo(455.53098, 520.696);
        ((GeneralPath)shape).curveTo(455.53098, 521.35596, 455.74298, 521.51495, 456.43896, 521.51495);
        ((GeneralPath)shape).curveTo(457.12695, 521.51495, 457.32797, 521.27496, 457.32797, 520.483);
        ((GeneralPath)shape).lineTo(456.78098, 520.483);
        ((GeneralPath)shape).curveTo(456.78098, 520.761, 456.81998, 521.04596, 456.43997, 521.04596);
        ((GeneralPath)shape).curveTo(456.15598, 521.04596, 456.15598, 520.93494, 456.15598, 520.691);
        ((GeneralPath)shape).lineTo(456.15598, 519.328);
        ((GeneralPath)shape).lineTo(457.19498, 519.328);
        ((GeneralPath)shape).lineTo(457.19498, 518.859);
        ((GeneralPath)shape).lineTo(456.15598, 518.859);
        ((GeneralPath)shape).lineTo(456.15598, 518.24);
        ((GeneralPath)shape).lineTo(455.53098, 518.24);
        ((GeneralPath)shape).lineTo(455.53098, 518.859);
        ((GeneralPath)shape).lineTo(455.201, 518.859);
        ((GeneralPath)shape).lineTo(455.201, 519.328);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_355;
        g.setTransform(defaultTransform__0_355);
        g.setClip(clip__0_355);
        float alpha__0_356 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_356 = g.getClip();
        AffineTransform defaultTransform__0_356 = g.getTransform();
        
//      _0_356 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(457.578, 518.859);
        ((GeneralPath)shape).lineTo(457.578, 521.515);
        ((GeneralPath)shape).lineTo(458.203, 521.515);
        ((GeneralPath)shape).lineTo(458.203, 519.898);
        ((GeneralPath)shape).curveTo(458.203, 519.559, 458.32, 519.344, 458.721, 519.344);
        ((GeneralPath)shape).curveTo(459.036, 519.344, 459.06302, 519.499, 459.06302, 519.763);
        ((GeneralPath)shape).lineTo(459.06302, 519.898);
        ((GeneralPath)shape).lineTo(459.68802, 519.898);
        ((GeneralPath)shape).lineTo(459.68802, 519.687);
        ((GeneralPath)shape).curveTo(459.68802, 519.19104, 459.544, 518.859, 458.95102, 518.859);
        ((GeneralPath)shape).curveTo(458.627, 518.859, 458.337, 518.943, 458.204, 519.231);
        ((GeneralPath)shape).lineTo(458.204, 518.859);
        ((GeneralPath)shape).lineTo(457.578, 518.859);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_356;
        g.setTransform(defaultTransform__0_356);
        g.setClip(clip__0_356);
        float alpha__0_357 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_357 = g.getClip();
        AffineTransform defaultTransform__0_357 = g.getTransform();
        
//      _0_357 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(461.185, 519.345);
        ((GeneralPath)shape).curveTo(461.727, 519.345, 461.771, 519.50195, 461.771, 520.20294);
        ((GeneralPath)shape).curveTo(461.771, 520.89294, 461.727, 521.04694, 461.185, 521.04694);
        ((GeneralPath)shape).curveTo(460.643, 521.04694, 460.599, 520.8939, 460.599, 520.20294);
        ((GeneralPath)shape).curveTo(460.599, 519.502, 460.643, 519.345, 461.185, 519.345);
        ((GeneralPath)shape).moveTo(461.185, 518.859);
        ((GeneralPath)shape).curveTo(460.111, 518.859, 459.974, 519.179, 459.974, 520.189);
        ((GeneralPath)shape).curveTo(459.974, 521.19403, 460.111, 521.515, 461.185, 521.515);
        ((GeneralPath)shape).curveTo(462.259, 521.515, 462.396, 521.19403, 462.396, 520.189);
        ((GeneralPath)shape).curveTo(462.396, 519.18, 462.259, 518.859, 461.185, 518.859);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_357;
        g.setTransform(defaultTransform__0_357);
        g.setClip(clip__0_357);
        float alpha__0_358 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_358 = g.getClip();
        AffineTransform defaultTransform__0_358 = g.getTransform();
        
//      _0_358 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(465.148, 518.859);
        ((GeneralPath)shape).lineTo(464.502, 518.859);
        ((GeneralPath)shape).lineTo(463.98602, 521.12103);
        ((GeneralPath)shape).lineTo(463.976, 521.12103);
        ((GeneralPath)shape).lineTo(463.30502, 518.859);
        ((GeneralPath)shape).lineTo(462.648, 518.859);
        ((GeneralPath)shape).lineTo(463.522, 521.515);
        ((GeneralPath)shape).lineTo(463.83002, 521.515);
        ((GeneralPath)shape).curveTo(463.77402, 521.828, 463.71503, 522.218, 463.315, 522.218);
        ((GeneralPath)shape).curveTo(463.27, 522.218, 463.223, 522.206, 463.178, 522.201);
        ((GeneralPath)shape).lineTo(463.178, 522.686);
        ((GeneralPath)shape).curveTo(463.268, 522.686, 463.36002, 522.686, 463.45, 522.686);
        ((GeneralPath)shape).curveTo(464.19302, 522.686, 464.31403, 522.21497, 464.461, 521.615);
        ((GeneralPath)shape).lineTo(465.148, 518.859);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_358;
        g.setTransform(defaultTransform__0_358);
        g.setClip(clip__0_358);
        float alpha__0_359 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_359 = g.getClip();
        AffineTransform defaultTransform__0_359 = g.getTransform();
        
//      _0_359 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(465.933, 519.953);
        ((GeneralPath)shape).curveTo(465.933, 519.466, 465.96902, 519.34503, 466.51202, 519.34503);
        ((GeneralPath)shape).curveTo(467.02603, 519.34503, 467.105, 519.387, 467.105, 519.953);
        ((GeneralPath)shape).lineTo(465.933, 519.953);
        ((GeneralPath)shape).moveTo(467.104, 520.683);
        ((GeneralPath)shape).curveTo(467.104, 521.047, 466.86002, 521.047, 466.51102, 521.047);
        ((GeneralPath)shape).curveTo(465.94702, 521.047, 465.932, 520.877, 465.932, 520.344);
        ((GeneralPath)shape).lineTo(467.729, 520.344);
        ((GeneralPath)shape).curveTo(467.729, 519.179, 467.585, 518.86, 466.51102, 518.86);
        ((GeneralPath)shape).curveTo(465.45602, 518.86, 465.307, 519.264, 465.307, 520.195);
        ((GeneralPath)shape).curveTo(465.307, 521.2, 465.51102, 521.516, 466.51102, 521.516);
        ((GeneralPath)shape).curveTo(467.25702, 521.516, 467.729, 521.477, 467.729, 520.683);
        ((GeneralPath)shape).lineTo(467.104, 520.683);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_359;
        g.setTransform(defaultTransform__0_359);
        g.setClip(clip__0_359);
        float alpha__0_360 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_360 = g.getClip();
        AffineTransform defaultTransform__0_360 = g.getTransform();
        
//      _0_360 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(469.955, 521.516);
        ((GeneralPath)shape).lineTo(470.58, 521.516);
        ((GeneralPath)shape).lineTo(470.58, 517.688);
        ((GeneralPath)shape).lineTo(469.955, 517.688);
        ((GeneralPath)shape).lineTo(469.955, 519.244);
        ((GeneralPath)shape).lineTo(469.93997, 519.244);
        ((GeneralPath)shape).curveTo(469.80496, 518.921, 469.47296, 518.86005, 469.15497, 518.86005);
        ((GeneralPath)shape).curveTo(468.26096, 518.86005, 468.15796, 519.34906, 468.15796, 520.111);
        ((GeneralPath)shape).curveTo(468.15796, 520.908, 468.15796, 521.51605, 469.13495, 521.51605);
        ((GeneralPath)shape).curveTo(469.50494, 521.51605, 469.79495, 521.439, 469.95496, 521.10406);
        ((GeneralPath)shape).lineTo(469.95496, 521.516);
        ((GeneralPath)shape).moveTo(469.34, 519.345);
        ((GeneralPath)shape).curveTo(469.88098, 519.345, 469.955, 519.56696, 469.955, 520.11896);
        ((GeneralPath)shape).curveTo(469.955, 520.735, 469.955, 521.04694, 469.335, 521.04694);
        ((GeneralPath)shape).curveTo(468.783, 521.04694, 468.783, 520.70593, 468.783, 520.11896);
        ((GeneralPath)shape).curveTo(468.783, 519.468, 468.907, 519.345, 469.34, 519.345);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_360;
        g.setTransform(defaultTransform__0_360);
        g.setClip(clip__0_360);
        float alpha__0_361 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_361 = g.getClip();
        AffineTransform defaultTransform__0_361 = g.getTransform();
        
//      _0_361 is ShapeNode
        paint = new Color(199, 200, 201, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(416.65, 521.732);
        ((GeneralPath)shape).curveTo(416.65, 522.526, 416.007, 523.17, 415.212, 523.17);
        ((GeneralPath)shape).lineTo(411.337, 523.17);
        ((GeneralPath)shape).curveTo(410.543, 523.17, 409.89902, 522.526, 409.89902, 521.732);
        ((GeneralPath)shape).lineTo(409.89902, 517.818);
        ((GeneralPath)shape).curveTo(409.89902, 517.023, 410.544, 516.37897, 411.337, 516.37897);
        ((GeneralPath)shape).lineTo(415.212, 516.37897);
        ((GeneralPath)shape).curveTo(416.00702, 516.37897, 416.65, 517.024, 416.65, 517.818);
        ((GeneralPath)shape).lineTo(416.65, 521.732);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_361;
        g.setTransform(defaultTransform__0_361);
        g.setClip(clip__0_361);
        float alpha__0_362 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_362 = g.getClip();
        AffineTransform defaultTransform__0_362 = g.getTransform();
        
//      _0_362 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        stroke = new BasicStroke(1.0f,0,0,10.0f,null,0.0f);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(416.65, 521.732);
        ((GeneralPath)shape).curveTo(416.65, 522.526, 416.00598, 523.17, 415.212, 523.17);
        ((GeneralPath)shape).lineTo(411.337, 523.17);
        ((GeneralPath)shape).curveTo(410.543, 523.17, 409.89902, 522.526, 409.89902, 521.732);
        ((GeneralPath)shape).lineTo(409.89902, 517.818);
        ((GeneralPath)shape).curveTo(409.89902, 517.023, 410.544, 516.37897, 411.337, 516.37897);
        ((GeneralPath)shape).lineTo(415.212, 516.37897);
        ((GeneralPath)shape).curveTo(416.006, 516.37897, 416.65, 517.024, 416.65, 517.818);
        ((GeneralPath)shape).lineTo(416.65, 521.732);
        ((GeneralPath)shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_362;
        g.setTransform(defaultTransform__0_362);
        g.setClip(clip__0_362);
        float alpha__0_363 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_363 = g.getClip();
        AffineTransform defaultTransform__0_363 = g.getTransform();
        
//      _0_363 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(314.592, 557.329);
        ((GeneralPath)shape).lineTo(313.703, 557.329);
        ((GeneralPath)shape).lineTo(312.503, 558.478);
        ((GeneralPath)shape).lineTo(312.913, 558.933);
        ((GeneralPath)shape).lineTo(313.889, 557.936);
        ((GeneralPath)shape).lineTo(313.889, 561.157);
        ((GeneralPath)shape).lineTo(314.592, 561.157);
        ((GeneralPath)shape).lineTo(314.592, 557.329);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_363;
        g.setTransform(defaultTransform__0_363);
        g.setClip(clip__0_363);
        float alpha__0_364 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_364 = g.getClip();
        AffineTransform defaultTransform__0_364 = g.getTransform();
        
//      _0_364 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(317.414, 560.532);
        ((GeneralPath)shape).curveTo(316.718, 560.532, 316.555, 560.503, 316.555, 559.74);
        ((GeneralPath)shape).lineTo(316.555, 558.722);
        ((GeneralPath)shape).curveTo(316.555, 557.964, 316.718, 557.925, 317.414, 557.925);
        ((GeneralPath)shape).curveTo(318.104, 557.925, 318.273, 557.964, 318.273, 558.722);
        ((GeneralPath)shape).lineTo(318.273, 559.74);
        ((GeneralPath)shape).curveTo(318.273, 560.503, 318.104, 560.532, 317.414, 560.532);
        ((GeneralPath)shape).moveTo(317.414, 557.329);
        ((GeneralPath)shape).curveTo(316.12302, 557.329, 315.851, 557.636, 315.851, 558.896);
        ((GeneralPath)shape).lineTo(315.851, 559.589);
        ((GeneralPath)shape).curveTo(315.851, 560.936, 316.27902, 561.156, 317.414, 561.156);
        ((GeneralPath)shape).curveTo(318.548, 561.156, 318.977, 560.935, 318.977, 559.589);
        ((GeneralPath)shape).lineTo(318.977, 558.90497);
        ((GeneralPath)shape).curveTo(318.977, 557.56, 318.548, 557.329, 317.414, 557.329);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_364;
        g.setTransform(defaultTransform__0_364);
        g.setClip(clip__0_364);
        float alpha__0_365 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_365 = g.getClip();
        AffineTransform defaultTransform__0_365 = g.getTransform();
        
//      _0_365 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(376.106, 558.501);
        ((GeneralPath)shape).lineTo(376.106, 559.517);
        ((GeneralPath)shape).lineTo(375.136, 559.517);
        ((GeneralPath)shape).lineTo(375.136, 559.985);
        ((GeneralPath)shape).lineTo(376.106, 559.985);
        ((GeneralPath)shape).lineTo(376.106, 560.967);
        ((GeneralPath)shape).lineTo(376.575, 560.967);
        ((GeneralPath)shape).lineTo(376.575, 559.985);
        ((GeneralPath)shape).lineTo(377.546, 559.985);
        ((GeneralPath)shape).lineTo(377.546, 559.517);
        ((GeneralPath)shape).lineTo(376.575, 559.517);
        ((GeneralPath)shape).lineTo(376.575, 558.501);
        ((GeneralPath)shape).lineTo(376.106, 558.501);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_365;
        g.setTransform(defaultTransform__0_365);
        g.setClip(clip__0_365);
        float alpha__0_366 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_366 = g.getClip();
        AffineTransform defaultTransform__0_366 = g.getTransform();
        
//      _0_366 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(380.276, 557.329);
        ((GeneralPath)shape).lineTo(379.388, 557.329);
        ((GeneralPath)shape).lineTo(378.188, 558.478);
        ((GeneralPath)shape).lineTo(378.598, 558.933);
        ((GeneralPath)shape).lineTo(379.573, 557.936);
        ((GeneralPath)shape).lineTo(379.573, 561.157);
        ((GeneralPath)shape).lineTo(380.276, 561.157);
        ((GeneralPath)shape).lineTo(380.276, 557.329);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_366;
        g.setTransform(defaultTransform__0_366);
        g.setClip(clip__0_366);
        float alpha__0_367 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_367 = g.getClip();
        AffineTransform defaultTransform__0_367 = g.getTransform();
        
//      _0_367 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(383.044, 558.97);
        ((GeneralPath)shape).lineTo(383.374, 558.97);
        ((GeneralPath)shape).lineTo(383.374, 560.33795);
        ((GeneralPath)shape).curveTo(383.374, 560.9979, 383.586, 561.1569, 384.28198, 561.1569);
        ((GeneralPath)shape).curveTo(384.96997, 561.1569, 385.171, 560.9179, 385.171, 560.1259);
        ((GeneralPath)shape).lineTo(384.624, 560.1259);
        ((GeneralPath)shape).curveTo(384.624, 560.40393, 384.663, 560.6889, 384.283, 560.6889);
        ((GeneralPath)shape).curveTo(383.999, 560.6889, 383.999, 560.5779, 383.999, 560.3339);
        ((GeneralPath)shape).lineTo(383.999, 558.97095);
        ((GeneralPath)shape).lineTo(385.038, 558.97095);
        ((GeneralPath)shape).lineTo(385.038, 558.50195);
        ((GeneralPath)shape).lineTo(383.999, 558.50195);
        ((GeneralPath)shape).lineTo(383.999, 557.88293);
        ((GeneralPath)shape).lineTo(383.374, 557.88293);
        ((GeneralPath)shape).lineTo(383.374, 558.50195);
        ((GeneralPath)shape).lineTo(383.044, 558.50195);
        ((GeneralPath)shape).lineTo(383.044, 558.97);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_367;
        g.setTransform(defaultTransform__0_367);
        g.setClip(clip__0_367);
        float alpha__0_368 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_368 = g.getClip();
        AffineTransform defaultTransform__0_368 = g.getTransform();
        
//      _0_368 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(386.632, 558.986);
        ((GeneralPath)shape).curveTo(387.17398, 558.986, 387.218, 559.14404, 387.218, 559.844);
        ((GeneralPath)shape).curveTo(387.218, 560.535, 387.17398, 560.688, 386.632, 560.688);
        ((GeneralPath)shape).curveTo(386.09, 560.688, 386.046, 560.536, 386.046, 559.844);
        ((GeneralPath)shape).curveTo(386.046, 559.145, 386.09, 558.986, 386.632, 558.986);
        ((GeneralPath)shape).moveTo(386.632, 558.501);
        ((GeneralPath)shape).curveTo(385.55798, 558.501, 385.421, 558.82196, 385.421, 559.831);
        ((GeneralPath)shape).curveTo(385.421, 560.836, 385.55798, 561.157, 386.632, 561.157);
        ((GeneralPath)shape).curveTo(387.706, 561.157, 387.843, 560.836, 387.843, 559.831);
        ((GeneralPath)shape).curveTo(387.843, 558.822, 387.706, 558.501, 386.632, 558.501);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_368;
        g.setTransform(defaultTransform__0_368);
        g.setClip(clip__0_368);
        float alpha__0_369 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_369 = g.getClip();
        AffineTransform defaultTransform__0_369 = g.getTransform();
        
//      _0_369 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(393.372, 557.329);
        ((GeneralPath)shape).lineTo(392.669, 557.329);
        ((GeneralPath)shape).lineTo(392.669, 558.892);
        ((GeneralPath)shape).lineTo(390.872, 558.892);
        ((GeneralPath)shape).lineTo(390.872, 557.329);
        ((GeneralPath)shape).lineTo(390.169, 557.329);
        ((GeneralPath)shape).lineTo(390.169, 561.157);
        ((GeneralPath)shape).lineTo(390.872, 561.157);
        ((GeneralPath)shape).lineTo(390.872, 559.517);
        ((GeneralPath)shape).lineTo(392.669, 559.517);
        ((GeneralPath)shape).lineTo(392.669, 561.157);
        ((GeneralPath)shape).lineTo(393.372, 561.157);
        ((GeneralPath)shape).lineTo(393.372, 557.329);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_369;
        g.setTransform(defaultTransform__0_369);
        g.setClip(clip__0_369);
        float alpha__0_370 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_370 = g.getClip();
        AffineTransform defaultTransform__0_370 = g.getTransform();
        
//      _0_370 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(394.639, 557.329);
        ((GeneralPath)shape).lineTo(394.014, 557.329);
        ((GeneralPath)shape).lineTo(394.014, 557.871);
        ((GeneralPath)shape).lineTo(394.639, 557.871);
        ((GeneralPath)shape).lineTo(394.639, 557.329);
        ((GeneralPath)shape).moveTo(394.639, 558.501);
        ((GeneralPath)shape).lineTo(394.014, 558.501);
        ((GeneralPath)shape).lineTo(394.014, 561.157);
        ((GeneralPath)shape).lineTo(394.639, 561.157);
        ((GeneralPath)shape).lineTo(394.639, 558.501);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_370;
        g.setTransform(defaultTransform__0_370);
        g.setClip(clip__0_370);
        float alpha__0_371 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_371 = g.getClip();
        AffineTransform defaultTransform__0_371 = g.getTransform();
        
//      _0_371 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(394.864, 558.97);
        ((GeneralPath)shape).lineTo(395.19302, 558.97);
        ((GeneralPath)shape).lineTo(395.19302, 560.33795);
        ((GeneralPath)shape).curveTo(395.19302, 560.9979, 395.40604, 561.1569, 396.101, 561.1569);
        ((GeneralPath)shape).curveTo(396.789, 561.1569, 396.99002, 560.9179, 396.99002, 560.1259);
        ((GeneralPath)shape).lineTo(396.44302, 560.1259);
        ((GeneralPath)shape).curveTo(396.44302, 560.40393, 396.48203, 560.6889, 396.10303, 560.6889);
        ((GeneralPath)shape).curveTo(395.81802, 560.6889, 395.81802, 560.5779, 395.81802, 560.3339);
        ((GeneralPath)shape).lineTo(395.81802, 558.97095);
        ((GeneralPath)shape).lineTo(396.85703, 558.97095);
        ((GeneralPath)shape).lineTo(396.85703, 558.50195);
        ((GeneralPath)shape).lineTo(395.81802, 558.50195);
        ((GeneralPath)shape).lineTo(395.81802, 557.88293);
        ((GeneralPath)shape).lineTo(395.19302, 557.88293);
        ((GeneralPath)shape).lineTo(395.19302, 558.50195);
        ((GeneralPath)shape).lineTo(394.864, 558.50195);
        ((GeneralPath)shape).lineTo(394.864, 558.97);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_371;
        g.setTransform(defaultTransform__0_371);
        g.setClip(clip__0_371);
        float alpha__0_372 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_372 = g.getClip();
        AffineTransform defaultTransform__0_372 = g.getTransform();
        
//      _0_372 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(328.873, 557.329);
        ((GeneralPath)shape).lineTo(328.17, 557.329);
        ((GeneralPath)shape).lineTo(328.17, 561.157);
        ((GeneralPath)shape).lineTo(330.624, 561.157);
        ((GeneralPath)shape).lineTo(330.624, 560.534);
        ((GeneralPath)shape).lineTo(328.873, 560.534);
        ((GeneralPath)shape).lineTo(328.873, 557.329);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_372;
        g.setTransform(defaultTransform__0_372);
        g.setClip(clip__0_372);
        float alpha__0_373 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_373 = g.getClip();
        AffineTransform defaultTransform__0_373 = g.getTransform();
        
//      _0_373 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(331.542, 559.595);
        ((GeneralPath)shape).curveTo(331.542, 559.108, 331.577, 558.987, 332.121, 558.987);
        ((GeneralPath)shape).curveTo(332.635, 558.987, 332.714, 559.029, 332.714, 559.595);
        ((GeneralPath)shape).lineTo(331.542, 559.595);
        ((GeneralPath)shape).moveTo(332.714, 560.324);
        ((GeneralPath)shape).curveTo(332.714, 560.688, 332.47, 560.688, 332.121, 560.688);
        ((GeneralPath)shape).curveTo(331.557, 560.688, 331.542, 560.518, 331.542, 559.985);
        ((GeneralPath)shape).lineTo(333.339, 559.985);
        ((GeneralPath)shape).curveTo(333.339, 558.82, 333.194, 558.501, 332.121, 558.501);
        ((GeneralPath)shape).curveTo(331.065, 558.501, 330.917, 558.906, 330.917, 559.836);
        ((GeneralPath)shape).curveTo(330.917, 560.841, 331.121, 561.157, 332.121, 561.157);
        ((GeneralPath)shape).curveTo(332.866, 561.157, 333.339, 561.118, 333.339, 560.324);
        ((GeneralPath)shape).lineTo(332.714, 560.324);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_373;
        g.setTransform(defaultTransform__0_373);
        g.setClip(clip__0_373);
        float alpha__0_374 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_374 = g.getClip();
        AffineTransform defaultTransform__0_374 = g.getTransform();
        
//      _0_374 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(335.266, 558.501);
        ((GeneralPath)shape).lineTo(334.62598, 558.501);
        ((GeneralPath)shape).lineTo(334.62598, 558.33496);
        ((GeneralPath)shape).curveTo(334.62598, 557.99695, 334.62598, 557.81396, 335.05597, 557.81396);
        ((GeneralPath)shape).lineTo(335.24597, 557.81396);
        ((GeneralPath)shape).lineTo(335.24597, 557.329);
        ((GeneralPath)shape).lineTo(334.83698, 557.329);
        ((GeneralPath)shape).curveTo(334.14996, 557.329, 334.00098, 557.60297, 334.00098, 558.25696);
        ((GeneralPath)shape).lineTo(334.00098, 558.501);
        ((GeneralPath)shape).lineTo(333.63098, 558.501);
        ((GeneralPath)shape).lineTo(333.63098, 558.97);
        ((GeneralPath)shape).lineTo(334.00098, 558.97);
        ((GeneralPath)shape).lineTo(334.00098, 561.15796);
        ((GeneralPath)shape).lineTo(334.62598, 561.15796);
        ((GeneralPath)shape).lineTo(334.62598, 558.97);
        ((GeneralPath)shape).lineTo(335.266, 558.97);
        ((GeneralPath)shape).lineTo(335.266, 558.501);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_374;
        g.setTransform(defaultTransform__0_374);
        g.setClip(clip__0_374);
        float alpha__0_375 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_375 = g.getClip();
        AffineTransform defaultTransform__0_375 = g.getTransform();
        
//      _0_375 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(335.255, 558.97);
        ((GeneralPath)shape).lineTo(335.585, 558.97);
        ((GeneralPath)shape).lineTo(335.585, 560.33795);
        ((GeneralPath)shape).curveTo(335.585, 560.9979, 335.797, 561.1569, 336.49298, 561.1569);
        ((GeneralPath)shape).curveTo(337.18097, 561.1569, 337.382, 560.9179, 337.382, 560.1259);
        ((GeneralPath)shape).lineTo(336.835, 560.1259);
        ((GeneralPath)shape).curveTo(336.835, 560.40393, 336.874, 560.6889, 336.494, 560.6889);
        ((GeneralPath)shape).curveTo(336.21, 560.6889, 336.21, 560.5779, 336.21, 560.3339);
        ((GeneralPath)shape).lineTo(336.21, 558.97095);
        ((GeneralPath)shape).lineTo(337.249, 558.97095);
        ((GeneralPath)shape).lineTo(337.249, 558.50195);
        ((GeneralPath)shape).lineTo(336.21, 558.50195);
        ((GeneralPath)shape).lineTo(336.21, 557.88293);
        ((GeneralPath)shape).lineTo(335.585, 557.88293);
        ((GeneralPath)shape).lineTo(335.585, 558.50195);
        ((GeneralPath)shape).lineTo(335.255, 558.50195);
        ((GeneralPath)shape).lineTo(335.255, 558.97);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_375;
        g.setTransform(defaultTransform__0_375);
        g.setClip(clip__0_375);
        float alpha__0_376 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_376 = g.getClip();
        AffineTransform defaultTransform__0_376 = g.getTransform();
        
//      _0_376 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(341.609, 559.907);
        ((GeneralPath)shape).lineTo(340.308, 559.907);
        ((GeneralPath)shape).lineTo(340.95203, 557.886);
        ((GeneralPath)shape).lineTo(340.96204, 557.886);
        ((GeneralPath)shape).lineTo(341.609, 559.907);
        ((GeneralPath)shape).moveTo(341.771, 560.454);
        ((GeneralPath)shape).lineTo(342.018, 561.157);
        ((GeneralPath)shape).lineTo(342.751, 561.157);
        ((GeneralPath)shape).lineTo(341.475, 557.329);
        ((GeneralPath)shape).lineTo(340.409, 557.329);
        ((GeneralPath)shape).lineTo(339.158, 561.157);
        ((GeneralPath)shape).lineTo(339.908, 561.157);
        ((GeneralPath)shape).lineTo(340.14297, 560.454);
        ((GeneralPath)shape).lineTo(341.771, 560.454);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_376;
        g.setTransform(defaultTransform__0_376);
        g.setClip(clip__0_376);
        float alpha__0_377 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_377 = g.getClip();
        AffineTransform defaultTransform__0_377 = g.getTransform();
        
//      _0_377 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(342.937, 558.501);
        ((GeneralPath)shape).lineTo(342.937, 561.157);
        ((GeneralPath)shape).lineTo(343.562, 561.157);
        ((GeneralPath)shape).lineTo(343.562, 559.54095);
        ((GeneralPath)shape).curveTo(343.562, 559.2009, 343.67902, 558.98596, 344.08002, 558.98596);
        ((GeneralPath)shape).curveTo(344.39502, 558.98596, 344.42203, 559.141, 344.42203, 559.40497);
        ((GeneralPath)shape).lineTo(344.42203, 559.54095);
        ((GeneralPath)shape).lineTo(345.04703, 559.54095);
        ((GeneralPath)shape).lineTo(345.04703, 559.32996);
        ((GeneralPath)shape).curveTo(345.04703, 558.83295, 344.903, 558.501, 344.31003, 558.501);
        ((GeneralPath)shape).curveTo(343.98602, 558.501, 343.69702, 558.58496, 343.56302, 558.873);
        ((GeneralPath)shape).lineTo(343.56302, 558.501);
        ((GeneralPath)shape).lineTo(342.937, 558.501);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_377;
        g.setTransform(defaultTransform__0_377);
        g.setClip(clip__0_377);
        float alpha__0_378 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_378 = g.getClip();
        AffineTransform defaultTransform__0_378 = g.getTransform();
        
//      _0_378 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(345.41, 558.501);
        ((GeneralPath)shape).lineTo(345.41, 561.157);
        ((GeneralPath)shape).lineTo(346.035, 561.157);
        ((GeneralPath)shape).lineTo(346.035, 559.67896);
        ((GeneralPath)shape).curveTo(346.035, 559.21497, 346.141, 558.98596, 346.68, 558.98596);
        ((GeneralPath)shape).curveTo(347.04498, 558.98596, 347.129, 559.11597, 347.129, 559.44495);
        ((GeneralPath)shape).lineTo(347.129, 561.1569);
        ((GeneralPath)shape).lineTo(347.754, 561.1569);
        ((GeneralPath)shape).lineTo(347.754, 559.6789);
        ((GeneralPath)shape).curveTo(347.754, 559.2149, 347.852, 558.9859, 348.352, 558.9859);
        ((GeneralPath)shape).curveTo(348.692, 558.9859, 348.77, 559.1159, 348.77, 559.4449);
        ((GeneralPath)shape).lineTo(348.77, 561.15686);
        ((GeneralPath)shape).lineTo(349.395, 561.15686);
        ((GeneralPath)shape).lineTo(349.395, 559.38086);
        ((GeneralPath)shape).curveTo(349.395, 558.73584, 349.175, 558.50085, 348.50998, 558.50085);
        ((GeneralPath)shape).curveTo(348.16797, 558.50085, 347.817, 558.60284, 347.69897, 558.9708);
        ((GeneralPath)shape).lineTo(347.679, 558.9708);
        ((GeneralPath)shape).curveTo(347.607, 558.5858, 347.21698, 558.50085, 346.879, 558.50085);
        ((GeneralPath)shape).curveTo(346.54102, 558.50085, 346.177, 558.5748, 346.034, 558.9108);
        ((GeneralPath)shape).lineTo(346.034, 558.50085);
        ((GeneralPath)shape).lineTo(345.41, 558.50085);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_378;
        g.setTransform(defaultTransform__0_378);
        g.setClip(clip__0_378);
        float alpha__0_379 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_379 = g.getClip();
        AffineTransform defaultTransform__0_379 = g.getTransform();
        
//      _0_379 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        stroke = new BasicStroke(1.0f,0,0,10.0f,null,0.0f);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(372.65, 561.374);
        ((GeneralPath)shape).curveTo(372.65, 562.17004, 372.005, 562.81305, 371.212, 562.81305);
        ((GeneralPath)shape).lineTo(367.336, 562.81305);
        ((GeneralPath)shape).curveTo(366.543, 562.81305, 365.898, 562.16907, 365.898, 561.374);
        ((GeneralPath)shape).lineTo(365.898, 557.46);
        ((GeneralPath)shape).curveTo(365.898, 556.666, 366.543, 556.02203, 367.336, 556.02203);
        ((GeneralPath)shape).lineTo(371.212, 556.02203);
        ((GeneralPath)shape).curveTo(372.005, 556.02203, 372.65, 556.66705, 372.65, 557.46);
        ((GeneralPath)shape).lineTo(372.65, 561.374);
        ((GeneralPath)shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_379;
        g.setTransform(defaultTransform__0_379);
        g.setClip(clip__0_379);
        float alpha__0_380 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_380 = g.getClip();
        AffineTransform defaultTransform__0_380 = g.getTransform();
        
//      _0_380 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(419.874, 557.329);
        ((GeneralPath)shape).lineTo(419.171, 557.329);
        ((GeneralPath)shape).lineTo(419.171, 561.157);
        ((GeneralPath)shape).lineTo(421.625, 561.157);
        ((GeneralPath)shape).lineTo(421.625, 560.534);
        ((GeneralPath)shape).lineTo(419.874, 560.534);
        ((GeneralPath)shape).lineTo(419.874, 557.329);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_380;
        g.setTransform(defaultTransform__0_380);
        g.setClip(clip__0_380);
        float alpha__0_381 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_381 = g.getClip();
        AffineTransform defaultTransform__0_381 = g.getTransform();
        
//      _0_381 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(422.543, 559.595);
        ((GeneralPath)shape).curveTo(422.543, 559.108, 422.579, 558.987, 423.122, 558.987);
        ((GeneralPath)shape).curveTo(423.63602, 558.987, 423.715, 559.029, 423.715, 559.595);
        ((GeneralPath)shape).lineTo(422.543, 559.595);
        ((GeneralPath)shape).moveTo(423.715, 560.324);
        ((GeneralPath)shape).curveTo(423.715, 560.688, 423.471, 560.688, 423.122, 560.688);
        ((GeneralPath)shape).curveTo(422.558, 560.688, 422.543, 560.518, 422.543, 559.985);
        ((GeneralPath)shape).lineTo(424.34, 559.985);
        ((GeneralPath)shape).curveTo(424.34, 558.82, 424.19598, 558.501, 423.122, 558.501);
        ((GeneralPath)shape).curveTo(422.06702, 558.501, 421.918, 558.906, 421.918, 559.836);
        ((GeneralPath)shape).curveTo(421.918, 560.841, 422.122, 561.157, 423.122, 561.157);
        ((GeneralPath)shape).curveTo(423.868, 561.157, 424.34, 561.118, 424.34, 560.324);
        ((GeneralPath)shape).lineTo(423.715, 560.324);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_381;
        g.setTransform(defaultTransform__0_381);
        g.setClip(clip__0_381);
        float alpha__0_382 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_382 = g.getClip();
        AffineTransform defaultTransform__0_382 = g.getTransform();
        
//      _0_382 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(426.268, 558.501);
        ((GeneralPath)shape).lineTo(425.628, 558.501);
        ((GeneralPath)shape).lineTo(425.628, 558.33496);
        ((GeneralPath)shape).curveTo(425.628, 557.99695, 425.628, 557.81396, 426.05798, 557.81396);
        ((GeneralPath)shape).lineTo(426.248, 557.81396);
        ((GeneralPath)shape).lineTo(426.248, 557.329);
        ((GeneralPath)shape).lineTo(425.839, 557.329);
        ((GeneralPath)shape).curveTo(425.151, 557.329, 425.003, 557.60297, 425.003, 558.25696);
        ((GeneralPath)shape).lineTo(425.003, 558.501);
        ((GeneralPath)shape).lineTo(424.633, 558.501);
        ((GeneralPath)shape).lineTo(424.633, 558.97);
        ((GeneralPath)shape).lineTo(425.003, 558.97);
        ((GeneralPath)shape).lineTo(425.003, 561.15796);
        ((GeneralPath)shape).lineTo(425.628, 561.15796);
        ((GeneralPath)shape).lineTo(425.628, 558.97);
        ((GeneralPath)shape).lineTo(426.268, 558.97);
        ((GeneralPath)shape).lineTo(426.268, 558.501);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_382;
        g.setTransform(defaultTransform__0_382);
        g.setClip(clip__0_382);
        float alpha__0_383 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_383 = g.getClip();
        AffineTransform defaultTransform__0_383 = g.getTransform();
        
//      _0_383 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(426.257, 558.97);
        ((GeneralPath)shape).lineTo(426.58698, 558.97);
        ((GeneralPath)shape).lineTo(426.58698, 560.33795);
        ((GeneralPath)shape).curveTo(426.58698, 560.9979, 426.79898, 561.1569, 427.49496, 561.1569);
        ((GeneralPath)shape).curveTo(428.18198, 561.1569, 428.38397, 560.9179, 428.38397, 560.1259);
        ((GeneralPath)shape).lineTo(427.83698, 560.1259);
        ((GeneralPath)shape).curveTo(427.83698, 560.40393, 427.87598, 560.6889, 427.49597, 560.6889);
        ((GeneralPath)shape).curveTo(427.21198, 560.6889, 427.21198, 560.5779, 427.21198, 560.3339);
        ((GeneralPath)shape).lineTo(427.21198, 558.97095);
        ((GeneralPath)shape).lineTo(428.24997, 558.97095);
        ((GeneralPath)shape).lineTo(428.24997, 558.50195);
        ((GeneralPath)shape).lineTo(427.21198, 558.50195);
        ((GeneralPath)shape).lineTo(427.21198, 557.88293);
        ((GeneralPath)shape).lineTo(426.58698, 557.88293);
        ((GeneralPath)shape).lineTo(426.58698, 558.50195);
        ((GeneralPath)shape).lineTo(426.257, 558.50195);
        ((GeneralPath)shape).lineTo(426.257, 558.97);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_383;
        g.setTransform(defaultTransform__0_383);
        g.setClip(clip__0_383);
        float alpha__0_384 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_384 = g.getClip();
        AffineTransform defaultTransform__0_384 = g.getTransform();
        
//      _0_384 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(432.611, 559.907);
        ((GeneralPath)shape).lineTo(431.31, 559.907);
        ((GeneralPath)shape).lineTo(431.953, 557.886);
        ((GeneralPath)shape).lineTo(431.963, 557.886);
        ((GeneralPath)shape).lineTo(432.611, 559.907);
        ((GeneralPath)shape).moveTo(432.772, 560.454);
        ((GeneralPath)shape).lineTo(433.019, 561.157);
        ((GeneralPath)shape).lineTo(433.752, 561.157);
        ((GeneralPath)shape).lineTo(432.475, 557.329);
        ((GeneralPath)shape).lineTo(431.41, 557.329);
        ((GeneralPath)shape).lineTo(430.159, 561.157);
        ((GeneralPath)shape).lineTo(430.908, 561.157);
        ((GeneralPath)shape).lineTo(431.14398, 560.454);
        ((GeneralPath)shape).lineTo(432.772, 560.454);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_384;
        g.setTransform(defaultTransform__0_384);
        g.setClip(clip__0_384);
        float alpha__0_385 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_385 = g.getClip();
        AffineTransform defaultTransform__0_385 = g.getTransform();
        
//      _0_385 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(433.938, 558.501);
        ((GeneralPath)shape).lineTo(433.938, 561.157);
        ((GeneralPath)shape).lineTo(434.563, 561.157);
        ((GeneralPath)shape).lineTo(434.563, 559.54095);
        ((GeneralPath)shape).curveTo(434.563, 559.2009, 434.68, 558.98596, 435.081, 558.98596);
        ((GeneralPath)shape).curveTo(435.396, 558.98596, 435.423, 559.141, 435.423, 559.40497);
        ((GeneralPath)shape).lineTo(435.423, 559.54095);
        ((GeneralPath)shape).lineTo(436.048, 559.54095);
        ((GeneralPath)shape).lineTo(436.048, 559.32996);
        ((GeneralPath)shape).curveTo(436.048, 558.83295, 435.903, 558.501, 435.311, 558.501);
        ((GeneralPath)shape).curveTo(434.987, 558.501, 434.697, 558.58496, 434.564, 558.873);
        ((GeneralPath)shape).lineTo(434.564, 558.501);
        ((GeneralPath)shape).lineTo(433.938, 558.501);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_385;
        g.setTransform(defaultTransform__0_385);
        g.setClip(clip__0_385);
        float alpha__0_386 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_386 = g.getClip();
        AffineTransform defaultTransform__0_386 = g.getTransform();
        
//      _0_386 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(436.411, 558.501);
        ((GeneralPath)shape).lineTo(436.411, 561.157);
        ((GeneralPath)shape).lineTo(437.036, 561.157);
        ((GeneralPath)shape).lineTo(437.036, 559.67896);
        ((GeneralPath)shape).curveTo(437.036, 559.21497, 437.142, 558.98596, 437.681, 558.98596);
        ((GeneralPath)shape).curveTo(438.046, 558.98596, 438.13, 559.11597, 438.13, 559.44495);
        ((GeneralPath)shape).lineTo(438.13, 561.1569);
        ((GeneralPath)shape).lineTo(438.755, 561.1569);
        ((GeneralPath)shape).lineTo(438.755, 559.6789);
        ((GeneralPath)shape).curveTo(438.755, 559.2149, 438.853, 558.9859, 439.354, 558.9859);
        ((GeneralPath)shape).curveTo(439.693, 558.9859, 439.771, 559.1159, 439.771, 559.4449);
        ((GeneralPath)shape).lineTo(439.771, 561.15686);
        ((GeneralPath)shape).lineTo(440.396, 561.15686);
        ((GeneralPath)shape).lineTo(440.396, 559.38086);
        ((GeneralPath)shape).curveTo(440.396, 558.73584, 440.176, 558.50085, 439.511, 558.50085);
        ((GeneralPath)shape).curveTo(439.16898, 558.50085, 438.818, 558.60284, 438.69998, 558.9708);
        ((GeneralPath)shape).lineTo(438.68, 558.9708);
        ((GeneralPath)shape).curveTo(438.608, 558.5858, 438.218, 558.50085, 437.88, 558.50085);
        ((GeneralPath)shape).curveTo(437.54202, 558.50085, 437.178, 558.5748, 437.035, 558.9108);
        ((GeneralPath)shape).lineTo(437.035, 558.50085);
        ((GeneralPath)shape).lineTo(436.411, 558.50085);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_386;
        g.setTransform(defaultTransform__0_386);
        g.setClip(clip__0_386);
        float alpha__0_387 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_387 = g.getClip();
        AffineTransform defaultTransform__0_387 = g.getTransform();
        
//      _0_387 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(443.495, 560.534);
        ((GeneralPath)shape).lineTo(443.495, 557.951);
        ((GeneralPath)shape).lineTo(444.595, 557.951);
        ((GeneralPath)shape).curveTo(445.158, 557.951, 445.37, 558.11, 445.37, 558.751);
        ((GeneralPath)shape).lineTo(445.37, 559.65796);
        ((GeneralPath)shape).curveTo(445.37, 560.089, 445.216, 560.53394, 444.718, 560.53394);
        ((GeneralPath)shape).lineTo(443.495, 560.53394);
        ((GeneralPath)shape).moveTo(442.792, 561.157);
        ((GeneralPath)shape).lineTo(444.711, 561.157);
        ((GeneralPath)shape).curveTo(445.852, 561.157, 446.073, 560.453, 446.073, 559.657);
        ((GeneralPath)shape).lineTo(446.073, 558.75397);
        ((GeneralPath)shape).curveTo(446.073, 557.712, 445.631, 557.329, 444.591, 557.329);
        ((GeneralPath)shape).lineTo(442.792, 557.329);
        ((GeneralPath)shape).lineTo(442.792, 561.157);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_387;
        g.setTransform(defaultTransform__0_387);
        g.setClip(clip__0_387);
        float alpha__0_388 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_388 = g.getClip();
        AffineTransform defaultTransform__0_388 = g.getTransform();
        
//      _0_388 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(447.108, 559.595);
        ((GeneralPath)shape).curveTo(447.108, 559.108, 447.143, 558.987, 447.686, 558.987);
        ((GeneralPath)shape).curveTo(448.20102, 558.987, 448.28, 559.029, 448.28, 559.595);
        ((GeneralPath)shape).lineTo(447.108, 559.595);
        ((GeneralPath)shape).moveTo(448.28, 560.324);
        ((GeneralPath)shape).curveTo(448.28, 560.688, 448.036, 560.688, 447.686, 560.688);
        ((GeneralPath)shape).curveTo(447.12302, 560.688, 447.108, 560.518, 447.108, 559.985);
        ((GeneralPath)shape).lineTo(448.905, 559.985);
        ((GeneralPath)shape).curveTo(448.905, 558.82, 448.76, 558.501, 447.686, 558.501);
        ((GeneralPath)shape).curveTo(446.631, 558.501, 446.483, 558.906, 446.483, 559.836);
        ((GeneralPath)shape).curveTo(446.483, 560.841, 446.687, 561.157, 447.686, 561.157);
        ((GeneralPath)shape).curveTo(448.432, 561.157, 448.905, 561.118, 448.905, 560.324);
        ((GeneralPath)shape).lineTo(448.28, 560.324);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_388;
        g.setTransform(defaultTransform__0_388);
        g.setClip(clip__0_388);
        float alpha__0_389 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_389 = g.getClip();
        AffineTransform defaultTransform__0_389 = g.getTransform();
        
//      _0_389 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(451.599, 559.239);
        ((GeneralPath)shape).curveTo(451.599, 558.50104, 450.999, 558.50104, 450.498, 558.50104);
        ((GeneralPath)shape).curveTo(449.84, 558.50104, 449.33298, 558.50104, 449.33298, 559.21);
        ((GeneralPath)shape).curveTo(449.33298, 559.869, 449.503, 559.997, 450.43896, 560.012);
        ((GeneralPath)shape).curveTo(451.03696, 560.02203, 451.05096, 560.13104, 451.05096, 560.348);
        ((GeneralPath)shape).curveTo(451.05096, 560.689, 450.85196, 560.689, 450.49197, 560.689);
        ((GeneralPath)shape).curveTo(450.04398, 560.689, 449.95697, 560.646, 449.95697, 560.281);
        ((GeneralPath)shape).lineTo(449.33197, 560.281);
        ((GeneralPath)shape).curveTo(449.33197, 561.158, 449.79697, 561.158, 450.48898, 561.158);
        ((GeneralPath)shape).curveTo(451.13397, 561.158, 451.676, 561.08905, 451.676, 560.382);
        ((GeneralPath)shape).curveTo(451.676, 559.536, 451.133, 559.581, 450.544, 559.552);
        ((GeneralPath)shape).curveTo(450.023, 559.528, 449.957, 559.518, 449.957, 559.269);
        ((GeneralPath)shape).curveTo(449.957, 558.938, 450.177, 558.938, 450.495, 558.938);
        ((GeneralPath)shape).curveTo(450.815, 558.938, 450.973, 558.938, 450.973, 559.24097);
        ((GeneralPath)shape).lineTo(451.599, 559.24097);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_389;
        g.setTransform(defaultTransform__0_389);
        g.setClip(clip__0_389);
        float alpha__0_390 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_390 = g.getClip();
        AffineTransform defaultTransform__0_390 = g.getTransform();
        
//      _0_390 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(451.867, 558.97);
        ((GeneralPath)shape).lineTo(452.196, 558.97);
        ((GeneralPath)shape).lineTo(452.196, 560.33795);
        ((GeneralPath)shape).curveTo(452.196, 560.9979, 452.40903, 561.1569, 453.104, 561.1569);
        ((GeneralPath)shape).curveTo(453.792, 561.1569, 453.993, 560.9179, 453.993, 560.1259);
        ((GeneralPath)shape).lineTo(453.446, 560.1259);
        ((GeneralPath)shape).curveTo(453.446, 560.40393, 453.48502, 560.6889, 453.105, 560.6889);
        ((GeneralPath)shape).curveTo(452.821, 560.6889, 452.821, 560.5779, 452.821, 560.3339);
        ((GeneralPath)shape).lineTo(452.821, 558.97095);
        ((GeneralPath)shape).lineTo(453.86002, 558.97095);
        ((GeneralPath)shape).lineTo(453.86002, 558.50195);
        ((GeneralPath)shape).lineTo(452.821, 558.50195);
        ((GeneralPath)shape).lineTo(452.821, 557.88293);
        ((GeneralPath)shape).lineTo(452.196, 557.88293);
        ((GeneralPath)shape).lineTo(452.196, 558.50195);
        ((GeneralPath)shape).lineTo(451.867, 558.50195);
        ((GeneralPath)shape).lineTo(451.867, 558.97);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_390;
        g.setTransform(defaultTransform__0_390);
        g.setClip(clip__0_390);
        float alpha__0_391 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_391 = g.getClip();
        AffineTransform defaultTransform__0_391 = g.getTransform();
        
//      _0_391 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(454.243, 558.501);
        ((GeneralPath)shape).lineTo(454.243, 561.157);
        ((GeneralPath)shape).lineTo(454.868, 561.157);
        ((GeneralPath)shape).lineTo(454.868, 559.54095);
        ((GeneralPath)shape).curveTo(454.868, 559.2009, 454.98502, 558.98596, 455.38602, 558.98596);
        ((GeneralPath)shape).curveTo(455.70102, 558.98596, 455.72803, 559.141, 455.72803, 559.40497);
        ((GeneralPath)shape).lineTo(455.72803, 559.54095);
        ((GeneralPath)shape).lineTo(456.35303, 559.54095);
        ((GeneralPath)shape).lineTo(456.35303, 559.32996);
        ((GeneralPath)shape).curveTo(456.35303, 558.83295, 456.20804, 558.501, 455.61603, 558.501);
        ((GeneralPath)shape).curveTo(455.29202, 558.501, 455.002, 558.58496, 454.86902, 558.873);
        ((GeneralPath)shape).lineTo(454.86902, 558.501);
        ((GeneralPath)shape).lineTo(454.243, 558.501);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_391;
        g.setTransform(defaultTransform__0_391);
        g.setClip(clip__0_391);
        float alpha__0_392 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_392 = g.getClip();
        AffineTransform defaultTransform__0_392 = g.getTransform();
        
//      _0_392 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(457.849, 558.986);
        ((GeneralPath)shape).curveTo(458.391, 558.986, 458.435, 559.14404, 458.435, 559.844);
        ((GeneralPath)shape).curveTo(458.435, 560.535, 458.391, 560.688, 457.849, 560.688);
        ((GeneralPath)shape).curveTo(457.307, 560.688, 457.263, 560.536, 457.263, 559.844);
        ((GeneralPath)shape).curveTo(457.263, 559.145, 457.307, 558.986, 457.849, 558.986);
        ((GeneralPath)shape).moveTo(457.849, 558.501);
        ((GeneralPath)shape).curveTo(456.775, 558.501, 456.638, 558.82196, 456.638, 559.831);
        ((GeneralPath)shape).curveTo(456.638, 560.836, 456.775, 561.157, 457.849, 561.157);
        ((GeneralPath)shape).curveTo(458.923, 561.157, 459.06, 560.836, 459.06, 559.831);
        ((GeneralPath)shape).curveTo(459.06, 558.822, 458.923, 558.501, 457.849, 558.501);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_392;
        g.setTransform(defaultTransform__0_392);
        g.setClip(clip__0_392);
        float alpha__0_393 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_393 = g.getClip();
        AffineTransform defaultTransform__0_393 = g.getTransform();
        
//      _0_393 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(461.813, 558.501);
        ((GeneralPath)shape).lineTo(461.167, 558.501);
        ((GeneralPath)shape).lineTo(460.651, 560.763);
        ((GeneralPath)shape).lineTo(460.641, 560.763);
        ((GeneralPath)shape).lineTo(459.97, 558.501);
        ((GeneralPath)shape).lineTo(459.313, 558.501);
        ((GeneralPath)shape).lineTo(460.18698, 561.157);
        ((GeneralPath)shape).lineTo(460.495, 561.157);
        ((GeneralPath)shape).curveTo(460.439, 561.47, 460.379, 561.86, 459.97998, 561.86);
        ((GeneralPath)shape).curveTo(459.934, 561.86, 459.88797, 561.849, 459.843, 561.844);
        ((GeneralPath)shape).lineTo(459.843, 562.328);
        ((GeneralPath)shape).curveTo(459.93298, 562.328, 460.025, 562.328, 460.11398, 562.328);
        ((GeneralPath)shape).curveTo(460.85797, 562.328, 460.978, 561.85803, 461.12497, 561.258);
        ((GeneralPath)shape).lineTo(461.813, 558.501);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_393;
        g.setTransform(defaultTransform__0_393);
        g.setClip(clip__0_393);
        float alpha__0_394 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_394 = g.getClip();
        AffineTransform defaultTransform__0_394 = g.getTransform();
        
//      _0_394 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(462.599, 559.595);
        ((GeneralPath)shape).curveTo(462.599, 559.108, 462.634, 558.987, 463.177, 558.987);
        ((GeneralPath)shape).curveTo(463.69202, 558.987, 463.771, 559.029, 463.771, 559.595);
        ((GeneralPath)shape).lineTo(462.599, 559.595);
        ((GeneralPath)shape).moveTo(463.771, 560.324);
        ((GeneralPath)shape).curveTo(463.771, 560.688, 463.527, 560.688, 463.177, 560.688);
        ((GeneralPath)shape).curveTo(462.614, 560.688, 462.599, 560.518, 462.599, 559.985);
        ((GeneralPath)shape).lineTo(464.396, 559.985);
        ((GeneralPath)shape).curveTo(464.396, 558.82, 464.251, 558.501, 463.177, 558.501);
        ((GeneralPath)shape).curveTo(462.122, 558.501, 461.974, 558.906, 461.974, 559.836);
        ((GeneralPath)shape).curveTo(461.974, 560.841, 462.178, 561.157, 463.177, 561.157);
        ((GeneralPath)shape).curveTo(463.923, 561.157, 464.396, 561.118, 464.396, 560.324);
        ((GeneralPath)shape).lineTo(463.771, 560.324);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_394;
        g.setTransform(defaultTransform__0_394);
        g.setClip(clip__0_394);
        float alpha__0_395 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_395 = g.getClip();
        AffineTransform defaultTransform__0_395 = g.getTransform();
        
//      _0_395 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(466.62, 561.157);
        ((GeneralPath)shape).lineTo(467.245, 561.157);
        ((GeneralPath)shape).lineTo(467.245, 557.329);
        ((GeneralPath)shape).lineTo(466.62, 557.329);
        ((GeneralPath)shape).lineTo(466.62, 558.886);
        ((GeneralPath)shape).lineTo(466.60498, 558.886);
        ((GeneralPath)shape).curveTo(466.47098, 558.562, 466.13898, 558.501, 465.81998, 558.501);
        ((GeneralPath)shape).curveTo(464.92596, 558.501, 464.82297, 558.99, 464.82297, 559.75195);
        ((GeneralPath)shape).curveTo(464.82297, 560.54895, 464.82297, 561.157, 465.79996, 561.157);
        ((GeneralPath)shape).curveTo(466.16995, 561.157, 466.45996, 561.07996, 466.61996, 560.746);
        ((GeneralPath)shape).lineTo(466.61996, 561.157);
        ((GeneralPath)shape).moveTo(466.005, 558.986);
        ((GeneralPath)shape).curveTo(466.546, 558.986, 466.62, 559.20905, 466.62, 559.76);
        ((GeneralPath)shape).curveTo(466.62, 560.37604, 466.62, 560.688, 466.0, 560.688);
        ((GeneralPath)shape).curveTo(465.448, 560.688, 465.448, 560.347, 465.448, 559.76);
        ((GeneralPath)shape).curveTo(465.448, 559.11, 465.573, 558.986, 466.005, 558.986);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_395;
        g.setTransform(defaultTransform__0_395);
        g.setClip(clip__0_395);
        float alpha__0_396 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_396 = g.getClip();
        AffineTransform defaultTransform__0_396 = g.getTransform();
        
//      _0_396 is ShapeNode
        paint = new Color(199, 200, 201, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(416.65, 561.374);
        ((GeneralPath)shape).curveTo(416.65, 562.17004, 416.007, 562.81305, 415.212, 562.81305);
        ((GeneralPath)shape).lineTo(411.337, 562.81305);
        ((GeneralPath)shape).curveTo(410.543, 562.81305, 409.89902, 562.16907, 409.89902, 561.374);
        ((GeneralPath)shape).lineTo(409.89902, 557.461);
        ((GeneralPath)shape).curveTo(409.89902, 556.666, 410.544, 556.022, 411.337, 556.022);
        ((GeneralPath)shape).lineTo(415.212, 556.022);
        ((GeneralPath)shape).curveTo(416.00702, 556.022, 416.65, 556.667, 416.65, 557.461);
        ((GeneralPath)shape).lineTo(416.65, 561.374);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_396;
        g.setTransform(defaultTransform__0_396);
        g.setClip(clip__0_396);
        float alpha__0_397 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_397 = g.getClip();
        AffineTransform defaultTransform__0_397 = g.getTransform();
        
//      _0_397 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        stroke = new BasicStroke(1.0f,0,0,10.0f,null,0.0f);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(416.65, 561.374);
        ((GeneralPath)shape).curveTo(416.65, 562.17004, 416.00598, 562.81305, 415.212, 562.81305);
        ((GeneralPath)shape).lineTo(411.337, 562.81305);
        ((GeneralPath)shape).curveTo(410.543, 562.81305, 409.89902, 562.16907, 409.89902, 561.374);
        ((GeneralPath)shape).lineTo(409.89902, 557.46);
        ((GeneralPath)shape).curveTo(409.89902, 556.666, 410.544, 556.02203, 411.337, 556.02203);
        ((GeneralPath)shape).lineTo(415.212, 556.02203);
        ((GeneralPath)shape).curveTo(416.006, 556.02203, 416.65, 556.66705, 416.65, 557.46);
        ((GeneralPath)shape).lineTo(416.65, 561.374);
        ((GeneralPath)shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_397;
        g.setTransform(defaultTransform__0_397);
        g.setClip(clip__0_397);
        float alpha__0_398 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_398 = g.getClip();
        AffineTransform defaultTransform__0_398 = g.getTransform();
        
//      _0_398 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(314.592, 569.877);
        ((GeneralPath)shape).lineTo(313.703, 569.877);
        ((GeneralPath)shape).lineTo(312.503, 571.025);
        ((GeneralPath)shape).lineTo(312.913, 571.481);
        ((GeneralPath)shape).lineTo(313.889, 570.483);
        ((GeneralPath)shape).lineTo(313.889, 573.705);
        ((GeneralPath)shape).lineTo(314.592, 573.705);
        ((GeneralPath)shape).lineTo(314.592, 569.877);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_398;
        g.setTransform(defaultTransform__0_398);
        g.setClip(clip__0_398);
        float alpha__0_399 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_399 = g.getClip();
        AffineTransform defaultTransform__0_399 = g.getTransform();
        
//      _0_399 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(318.742, 573.108);
        ((GeneralPath)shape).lineTo(316.633, 573.108);
        ((GeneralPath)shape).lineTo(316.633, 572.98096);
        ((GeneralPath)shape).curveTo(316.633, 572.415, 316.839, 572.43396, 317.508, 572.32794);
        ((GeneralPath)shape).curveTo(318.427, 572.17096, 318.742, 572.0499, 318.742, 571.04694);
        ((GeneralPath)shape).curveTo(318.742, 570.04395, 318.254, 569.87695, 317.37302, 569.87695);
        ((GeneralPath)shape).curveTo(316.009, 569.87695, 315.93002, 570.33093, 315.93002, 571.25494);
        ((GeneralPath)shape).lineTo(316.63303, 571.25494);
        ((GeneralPath)shape).curveTo(316.63303, 570.47394, 316.74704, 570.47394, 317.37103, 570.47394);
        ((GeneralPath)shape).curveTo(317.88004, 570.47394, 318.03903, 570.5449, 318.03903, 571.04694);
        ((GeneralPath)shape).curveTo(318.03903, 571.7349, 317.86703, 571.66394, 317.13504, 571.80096);
        ((GeneralPath)shape).curveTo(316.30804, 571.938, 315.93005, 572.009, 315.93005, 572.982);
        ((GeneralPath)shape).lineTo(315.93005, 573.706);
        ((GeneralPath)shape).lineTo(318.74304, 573.706);
        ((GeneralPath)shape).lineTo(318.74304, 573.108);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_399;
        g.setTransform(defaultTransform__0_399);
        g.setClip(clip__0_399);
        float alpha__0_400 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_400 = g.getClip();
        AffineTransform defaultTransform__0_400 = g.getTransform();
        
//      _0_400 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(376.106, 571.049);
        ((GeneralPath)shape).lineTo(376.106, 572.064);
        ((GeneralPath)shape).lineTo(375.136, 572.064);
        ((GeneralPath)shape).lineTo(375.136, 572.533);
        ((GeneralPath)shape).lineTo(376.106, 572.533);
        ((GeneralPath)shape).lineTo(376.106, 573.515);
        ((GeneralPath)shape).lineTo(376.575, 573.515);
        ((GeneralPath)shape).lineTo(376.575, 572.533);
        ((GeneralPath)shape).lineTo(377.546, 572.533);
        ((GeneralPath)shape).lineTo(377.546, 572.064);
        ((GeneralPath)shape).lineTo(376.575, 572.064);
        ((GeneralPath)shape).lineTo(376.575, 571.049);
        ((GeneralPath)shape).lineTo(376.106, 571.049);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_400;
        g.setTransform(defaultTransform__0_400);
        g.setClip(clip__0_400);
        float alpha__0_401 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_401 = g.getClip();
        AffineTransform defaultTransform__0_401 = g.getTransform();
        
//      _0_401 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(380.276, 569.877);
        ((GeneralPath)shape).lineTo(379.388, 569.877);
        ((GeneralPath)shape).lineTo(378.188, 571.025);
        ((GeneralPath)shape).lineTo(378.598, 571.481);
        ((GeneralPath)shape).lineTo(379.573, 570.483);
        ((GeneralPath)shape).lineTo(379.573, 573.705);
        ((GeneralPath)shape).lineTo(380.276, 573.705);
        ((GeneralPath)shape).lineTo(380.276, 569.877);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_401;
        g.setTransform(defaultTransform__0_401);
        g.setClip(clip__0_401);
        float alpha__0_402 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_402 = g.getClip();
        AffineTransform defaultTransform__0_402 = g.getTransform();
        
//      _0_402 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(383.044, 571.518);
        ((GeneralPath)shape).lineTo(383.374, 571.518);
        ((GeneralPath)shape).lineTo(383.374, 572.886);
        ((GeneralPath)shape).curveTo(383.374, 573.547, 383.586, 573.70496, 384.28198, 573.70496);
        ((GeneralPath)shape).curveTo(384.96997, 573.70496, 385.171, 573.46594, 385.171, 572.67395);
        ((GeneralPath)shape).lineTo(384.624, 572.67395);
        ((GeneralPath)shape).curveTo(384.624, 572.95197, 384.663, 573.23694, 384.283, 573.23694);
        ((GeneralPath)shape).curveTo(383.999, 573.23694, 383.999, 573.1259, 383.999, 572.88196);
        ((GeneralPath)shape).lineTo(383.999, 571.519);
        ((GeneralPath)shape).lineTo(385.038, 571.519);
        ((GeneralPath)shape).lineTo(385.038, 571.05);
        ((GeneralPath)shape).lineTo(383.999, 571.05);
        ((GeneralPath)shape).lineTo(383.999, 570.43097);
        ((GeneralPath)shape).lineTo(383.374, 570.43097);
        ((GeneralPath)shape).lineTo(383.374, 571.05);
        ((GeneralPath)shape).lineTo(383.044, 571.05);
        ((GeneralPath)shape).lineTo(383.044, 571.518);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_402;
        g.setTransform(defaultTransform__0_402);
        g.setClip(clip__0_402);
        float alpha__0_403 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_403 = g.getClip();
        AffineTransform defaultTransform__0_403 = g.getTransform();
        
//      _0_403 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(386.632, 571.535);
        ((GeneralPath)shape).curveTo(387.17398, 571.535, 387.218, 571.69196, 387.218, 572.39197);
        ((GeneralPath)shape).curveTo(387.218, 573.08295, 387.17398, 573.23596, 386.632, 573.23596);
        ((GeneralPath)shape).curveTo(386.09, 573.23596, 386.046, 573.084, 386.046, 572.39197);
        ((GeneralPath)shape).curveTo(386.046, 571.692, 386.09, 571.535, 386.632, 571.535);
        ((GeneralPath)shape).moveTo(386.632, 571.049);
        ((GeneralPath)shape).curveTo(385.55798, 571.049, 385.421, 571.37, 385.421, 572.379);
        ((GeneralPath)shape).curveTo(385.421, 573.38403, 385.55798, 573.705, 386.632, 573.705);
        ((GeneralPath)shape).curveTo(387.706, 573.705, 387.843, 573.38403, 387.843, 572.379);
        ((GeneralPath)shape).curveTo(387.843, 571.37, 387.706, 571.049, 386.632, 571.049);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_403;
        g.setTransform(defaultTransform__0_403);
        g.setClip(clip__0_403);
        float alpha__0_404 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_404 = g.getClip();
        AffineTransform defaultTransform__0_404 = g.getTransform();
        
//      _0_404 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(393.372, 569.877);
        ((GeneralPath)shape).lineTo(392.669, 569.877);
        ((GeneralPath)shape).lineTo(392.669, 571.439);
        ((GeneralPath)shape).lineTo(390.872, 571.439);
        ((GeneralPath)shape).lineTo(390.872, 569.877);
        ((GeneralPath)shape).lineTo(390.169, 569.877);
        ((GeneralPath)shape).lineTo(390.169, 573.705);
        ((GeneralPath)shape).lineTo(390.872, 573.705);
        ((GeneralPath)shape).lineTo(390.872, 572.064);
        ((GeneralPath)shape).lineTo(392.669, 572.064);
        ((GeneralPath)shape).lineTo(392.669, 573.705);
        ((GeneralPath)shape).lineTo(393.372, 573.705);
        ((GeneralPath)shape).lineTo(393.372, 569.877);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_404;
        g.setTransform(defaultTransform__0_404);
        g.setClip(clip__0_404);
        float alpha__0_405 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_405 = g.getClip();
        AffineTransform defaultTransform__0_405 = g.getTransform();
        
//      _0_405 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(394.639, 569.877);
        ((GeneralPath)shape).lineTo(394.014, 569.877);
        ((GeneralPath)shape).lineTo(394.014, 570.419);
        ((GeneralPath)shape).lineTo(394.639, 570.419);
        ((GeneralPath)shape).lineTo(394.639, 569.877);
        ((GeneralPath)shape).moveTo(394.639, 571.049);
        ((GeneralPath)shape).lineTo(394.014, 571.049);
        ((GeneralPath)shape).lineTo(394.014, 573.705);
        ((GeneralPath)shape).lineTo(394.639, 573.705);
        ((GeneralPath)shape).lineTo(394.639, 571.049);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_405;
        g.setTransform(defaultTransform__0_405);
        g.setClip(clip__0_405);
        float alpha__0_406 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_406 = g.getClip();
        AffineTransform defaultTransform__0_406 = g.getTransform();
        
//      _0_406 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(394.864, 571.518);
        ((GeneralPath)shape).lineTo(395.19302, 571.518);
        ((GeneralPath)shape).lineTo(395.19302, 572.886);
        ((GeneralPath)shape).curveTo(395.19302, 573.547, 395.40604, 573.70496, 396.101, 573.70496);
        ((GeneralPath)shape).curveTo(396.789, 573.70496, 396.99002, 573.46594, 396.99002, 572.67395);
        ((GeneralPath)shape).lineTo(396.44302, 572.67395);
        ((GeneralPath)shape).curveTo(396.44302, 572.95197, 396.48203, 573.23694, 396.10303, 573.23694);
        ((GeneralPath)shape).curveTo(395.81802, 573.23694, 395.81802, 573.1259, 395.81802, 572.88196);
        ((GeneralPath)shape).lineTo(395.81802, 571.519);
        ((GeneralPath)shape).lineTo(396.85703, 571.519);
        ((GeneralPath)shape).lineTo(396.85703, 571.05);
        ((GeneralPath)shape).lineTo(395.81802, 571.05);
        ((GeneralPath)shape).lineTo(395.81802, 570.43097);
        ((GeneralPath)shape).lineTo(395.19302, 570.43097);
        ((GeneralPath)shape).lineTo(395.19302, 571.05);
        ((GeneralPath)shape).lineTo(394.864, 571.05);
        ((GeneralPath)shape).lineTo(394.864, 571.518);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_406;
        g.setTransform(defaultTransform__0_406);
        g.setClip(clip__0_406);
        float alpha__0_407 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_407 = g.getClip();
        AffineTransform defaultTransform__0_407 = g.getTransform();
        
//      _0_407 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(331.373, 569.877);
        ((GeneralPath)shape).lineTo(330.67, 569.877);
        ((GeneralPath)shape).lineTo(330.67, 571.439);
        ((GeneralPath)shape).lineTo(328.873, 571.439);
        ((GeneralPath)shape).lineTo(328.873, 569.877);
        ((GeneralPath)shape).lineTo(328.17, 569.877);
        ((GeneralPath)shape).lineTo(328.17, 573.705);
        ((GeneralPath)shape).lineTo(328.873, 573.705);
        ((GeneralPath)shape).lineTo(328.873, 572.064);
        ((GeneralPath)shape).lineTo(330.67, 572.064);
        ((GeneralPath)shape).lineTo(330.67, 573.705);
        ((GeneralPath)shape).lineTo(331.373, 573.705);
        ((GeneralPath)shape).lineTo(331.373, 569.877);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_407;
        g.setTransform(defaultTransform__0_407);
        g.setClip(clip__0_407);
        float alpha__0_408 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_408 = g.getClip();
        AffineTransform defaultTransform__0_408 = g.getTransform();
        
//      _0_408 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(332.562, 572.143);
        ((GeneralPath)shape).curveTo(332.562, 571.656, 332.59802, 571.536, 333.14102, 571.536);
        ((GeneralPath)shape).curveTo(333.65503, 571.536, 333.734, 571.577, 333.734, 572.143);
        ((GeneralPath)shape).lineTo(332.562, 572.143);
        ((GeneralPath)shape).moveTo(333.733, 572.872);
        ((GeneralPath)shape).curveTo(333.733, 573.236, 333.489, 573.236, 333.14, 573.236);
        ((GeneralPath)shape).curveTo(332.57602, 573.236, 332.561, 573.06604, 332.561, 572.533);
        ((GeneralPath)shape).lineTo(334.358, 572.533);
        ((GeneralPath)shape).curveTo(334.358, 571.369, 334.214, 571.049, 333.14, 571.049);
        ((GeneralPath)shape).curveTo(332.08502, 571.049, 331.936, 571.45404, 331.936, 572.385);
        ((GeneralPath)shape).curveTo(331.936, 573.38904, 332.14, 573.705, 333.14, 573.705);
        ((GeneralPath)shape).curveTo(333.88602, 573.705, 334.358, 573.666, 334.358, 572.872);
        ((GeneralPath)shape).lineTo(333.733, 572.872);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_408;
        g.setTransform(defaultTransform__0_408);
        g.setClip(clip__0_408);
        float alpha__0_409 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_409 = g.getClip();
        AffineTransform defaultTransform__0_409 = g.getTransform();
        
//      _0_409 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(336.584, 573.705);
        ((GeneralPath)shape).lineTo(337.209, 573.705);
        ((GeneralPath)shape).lineTo(337.209, 572.059);
        ((GeneralPath)shape).curveTo(337.209, 571.179, 336.855, 571.04803, 336.00403, 571.04803);
        ((GeneralPath)shape).curveTo(335.39703, 571.04803, 334.86502, 571.04803, 334.86502, 571.82806);
        ((GeneralPath)shape).lineTo(335.49002, 571.82806);
        ((GeneralPath)shape).curveTo(335.49002, 571.5071, 335.71902, 571.4831, 336.00302, 571.4831);
        ((GeneralPath)shape).curveTo(336.54703, 571.4831, 336.584, 571.6321, 336.584, 572.05206);
        ((GeneralPath)shape).lineTo(336.584, 572.38904);
        ((GeneralPath)shape).lineTo(336.56302, 572.38904);
        ((GeneralPath)shape).curveTo(336.41202, 572.06305, 336.09503, 572.06305, 335.773, 572.06305);
        ((GeneralPath)shape).curveTo(335.11902, 572.06305, 334.78702, 572.23804, 334.78702, 572.8691);
        ((GeneralPath)shape).curveTo(334.78702, 573.5801, 335.173, 573.7041, 335.768, 573.7041);
        ((GeneralPath)shape).curveTo(336.073, 573.7041, 336.457, 573.7041, 336.583, 573.3171);
        ((GeneralPath)shape).lineTo(336.583, 573.705);
        ((GeneralPath)shape).moveTo(335.995, 572.533);
        ((GeneralPath)shape).curveTo(336.31, 572.533, 336.58398, 572.533, 336.58398, 572.866);
        ((GeneralPath)shape).curveTo(336.58398, 573.21, 336.335, 573.236, 335.995, 573.236);
        ((GeneralPath)shape).curveTo(335.564, 573.236, 335.412, 573.20404, 335.412, 572.87103);
        ((GeneralPath)shape).curveTo(335.412, 572.533, 335.671, 572.533, 335.995, 572.533);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_409;
        g.setTransform(defaultTransform__0_409);
        g.setClip(clip__0_409);
        float alpha__0_410 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_410 = g.getClip();
        AffineTransform defaultTransform__0_410 = g.getTransform();
        
//      _0_410 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(339.529, 573.705);
        ((GeneralPath)shape).lineTo(340.154, 573.705);
        ((GeneralPath)shape).lineTo(340.154, 569.877);
        ((GeneralPath)shape).lineTo(339.529, 569.877);
        ((GeneralPath)shape).lineTo(339.529, 571.434);
        ((GeneralPath)shape).lineTo(339.51398, 571.434);
        ((GeneralPath)shape).curveTo(339.37897, 571.11005, 339.04697, 571.049, 338.72897, 571.049);
        ((GeneralPath)shape).curveTo(337.83496, 571.049, 337.73196, 571.538, 337.73196, 572.3);
        ((GeneralPath)shape).curveTo(337.73196, 573.09796, 337.73196, 573.705, 338.70895, 573.705);
        ((GeneralPath)shape).curveTo(339.07794, 573.705, 339.36896, 573.628, 339.52896, 573.294);
        ((GeneralPath)shape).lineTo(339.52896, 573.705);
        ((GeneralPath)shape).moveTo(338.914, 571.535);
        ((GeneralPath)shape).curveTo(339.454, 571.535, 339.529, 571.75696, 339.529, 572.308);
        ((GeneralPath)shape).curveTo(339.529, 572.924, 339.529, 573.23596, 338.909, 573.23596);
        ((GeneralPath)shape).curveTo(338.357, 573.23596, 338.357, 572.89496, 338.357, 572.308);
        ((GeneralPath)shape).curveTo(338.357, 571.658, 338.481, 571.535, 338.914, 571.535);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_410;
        g.setTransform(defaultTransform__0_410);
        g.setClip(clip__0_410);
        float alpha__0_411 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_411 = g.getClip();
        AffineTransform defaultTransform__0_411 = g.getTransform();
        
//      _0_411 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        stroke = new BasicStroke(1.0f,0,0,10.0f,null,0.0f);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(372.65, 573.922);
        ((GeneralPath)shape).curveTo(372.65, 574.716, 372.005, 575.36, 371.212, 575.36);
        ((GeneralPath)shape).lineTo(367.336, 575.36);
        ((GeneralPath)shape).curveTo(366.543, 575.36, 365.898, 574.716, 365.898, 573.922);
        ((GeneralPath)shape).lineTo(365.898, 570.007);
        ((GeneralPath)shape).curveTo(365.898, 569.213, 366.543, 568.56903, 367.336, 568.56903);
        ((GeneralPath)shape).lineTo(371.212, 568.56903);
        ((GeneralPath)shape).curveTo(372.005, 568.56903, 372.65, 569.213, 372.65, 570.007);
        ((GeneralPath)shape).lineTo(372.65, 573.922);
        ((GeneralPath)shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_411;
        g.setTransform(defaultTransform__0_411);
        g.setClip(clip__0_411);
        float alpha__0_412 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_412 = g.getClip();
        AffineTransform defaultTransform__0_412 = g.getTransform();
        
//      _0_412 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(420.108, 571.049);
        ((GeneralPath)shape).lineTo(420.108, 572.064);
        ((GeneralPath)shape).lineTo(419.139, 572.064);
        ((GeneralPath)shape).lineTo(419.139, 572.533);
        ((GeneralPath)shape).lineTo(420.108, 572.533);
        ((GeneralPath)shape).lineTo(420.108, 573.515);
        ((GeneralPath)shape).lineTo(420.577, 573.515);
        ((GeneralPath)shape).lineTo(420.577, 572.533);
        ((GeneralPath)shape).lineTo(421.548, 572.533);
        ((GeneralPath)shape).lineTo(421.548, 572.064);
        ((GeneralPath)shape).lineTo(420.577, 572.064);
        ((GeneralPath)shape).lineTo(420.577, 571.049);
        ((GeneralPath)shape).lineTo(420.108, 571.049);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_412;
        g.setTransform(defaultTransform__0_412);
        g.setClip(clip__0_412);
        float alpha__0_413 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_413 = g.getClip();
        AffineTransform defaultTransform__0_413 = g.getTransform();
        
//      _0_413 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(424.904, 573.108);
        ((GeneralPath)shape).lineTo(422.79498, 573.108);
        ((GeneralPath)shape).lineTo(422.79498, 572.98096);
        ((GeneralPath)shape).curveTo(422.79498, 572.415, 423.00098, 572.43396, 423.66998, 572.32794);
        ((GeneralPath)shape).curveTo(424.589, 572.17096, 424.904, 572.0499, 424.904, 571.04694);
        ((GeneralPath)shape).curveTo(424.904, 570.04395, 424.416, 569.87695, 423.53598, 569.87695);
        ((GeneralPath)shape).curveTo(422.171, 569.87695, 422.09198, 570.33093, 422.09198, 571.25494);
        ((GeneralPath)shape).lineTo(422.79498, 571.25494);
        ((GeneralPath)shape).curveTo(422.79498, 570.47394, 422.909, 570.47394, 423.533, 570.47394);
        ((GeneralPath)shape).curveTo(424.042, 570.47394, 424.201, 570.5449, 424.201, 571.04694);
        ((GeneralPath)shape).curveTo(424.201, 571.7349, 424.029, 571.66394, 423.297, 571.80096);
        ((GeneralPath)shape).curveTo(422.47, 571.938, 422.092, 572.009, 422.092, 572.982);
        ((GeneralPath)shape).lineTo(422.092, 573.706);
        ((GeneralPath)shape).lineTo(424.905, 573.706);
        ((GeneralPath)shape).lineTo(424.905, 573.108);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_413;
        g.setTransform(defaultTransform__0_413);
        g.setClip(clip__0_413);
        float alpha__0_414 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_414 = g.getClip();
        AffineTransform defaultTransform__0_414 = g.getTransform();
        
//      _0_414 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(427.047, 571.518);
        ((GeneralPath)shape).lineTo(427.376, 571.518);
        ((GeneralPath)shape).lineTo(427.376, 572.886);
        ((GeneralPath)shape).curveTo(427.376, 573.547, 427.58902, 573.70496, 428.284, 573.70496);
        ((GeneralPath)shape).curveTo(428.972, 573.70496, 429.173, 573.46594, 429.173, 572.67395);
        ((GeneralPath)shape).lineTo(428.626, 572.67395);
        ((GeneralPath)shape).curveTo(428.626, 572.95197, 428.665, 573.23694, 428.286, 573.23694);
        ((GeneralPath)shape).curveTo(428.001, 573.23694, 428.001, 573.1259, 428.001, 572.88196);
        ((GeneralPath)shape).lineTo(428.001, 571.519);
        ((GeneralPath)shape).lineTo(429.04, 571.519);
        ((GeneralPath)shape).lineTo(429.04, 571.05);
        ((GeneralPath)shape).lineTo(428.001, 571.05);
        ((GeneralPath)shape).lineTo(428.001, 570.43097);
        ((GeneralPath)shape).lineTo(427.376, 570.43097);
        ((GeneralPath)shape).lineTo(427.376, 571.05);
        ((GeneralPath)shape).lineTo(427.047, 571.05);
        ((GeneralPath)shape).lineTo(427.047, 571.518);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_414;
        g.setTransform(defaultTransform__0_414);
        g.setClip(clip__0_414);
        float alpha__0_415 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_415 = g.getClip();
        AffineTransform defaultTransform__0_415 = g.getTransform();
        
//      _0_415 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(430.635, 571.535);
        ((GeneralPath)shape).curveTo(431.177, 571.535, 431.221, 571.69196, 431.221, 572.39197);
        ((GeneralPath)shape).curveTo(431.221, 573.08295, 431.177, 573.23596, 430.635, 573.23596);
        ((GeneralPath)shape).curveTo(430.09302, 573.23596, 430.049, 573.084, 430.049, 572.39197);
        ((GeneralPath)shape).curveTo(430.049, 571.692, 430.093, 571.535, 430.635, 571.535);
        ((GeneralPath)shape).moveTo(430.635, 571.049);
        ((GeneralPath)shape).curveTo(429.561, 571.049, 429.424, 571.37, 429.424, 572.379);
        ((GeneralPath)shape).curveTo(429.424, 573.38403, 429.561, 573.705, 430.635, 573.705);
        ((GeneralPath)shape).curveTo(431.709, 573.705, 431.846, 573.38403, 431.846, 572.379);
        ((GeneralPath)shape).curveTo(431.846, 571.37, 431.709, 571.049, 430.635, 571.049);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_415;
        g.setTransform(defaultTransform__0_415);
        g.setClip(clip__0_415);
        float alpha__0_416 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_416 = g.getClip();
        AffineTransform defaultTransform__0_416 = g.getTransform();
        
//      _0_416 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(437.374, 569.877);
        ((GeneralPath)shape).lineTo(436.671, 569.877);
        ((GeneralPath)shape).lineTo(436.671, 571.439);
        ((GeneralPath)shape).lineTo(434.874, 571.439);
        ((GeneralPath)shape).lineTo(434.874, 569.877);
        ((GeneralPath)shape).lineTo(434.171, 569.877);
        ((GeneralPath)shape).lineTo(434.171, 573.705);
        ((GeneralPath)shape).lineTo(434.874, 573.705);
        ((GeneralPath)shape).lineTo(434.874, 572.064);
        ((GeneralPath)shape).lineTo(436.671, 572.064);
        ((GeneralPath)shape).lineTo(436.671, 573.705);
        ((GeneralPath)shape).lineTo(437.374, 573.705);
        ((GeneralPath)shape).lineTo(437.374, 569.877);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_416;
        g.setTransform(defaultTransform__0_416);
        g.setClip(clip__0_416);
        float alpha__0_417 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_417 = g.getClip();
        AffineTransform defaultTransform__0_417 = g.getTransform();
        
//      _0_417 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(438.642, 569.877);
        ((GeneralPath)shape).lineTo(438.017, 569.877);
        ((GeneralPath)shape).lineTo(438.017, 570.419);
        ((GeneralPath)shape).lineTo(438.642, 570.419);
        ((GeneralPath)shape).lineTo(438.642, 569.877);
        ((GeneralPath)shape).moveTo(438.642, 571.049);
        ((GeneralPath)shape).lineTo(438.017, 571.049);
        ((GeneralPath)shape).lineTo(438.017, 573.705);
        ((GeneralPath)shape).lineTo(438.642, 573.705);
        ((GeneralPath)shape).lineTo(438.642, 571.049);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_417;
        g.setTransform(defaultTransform__0_417);
        g.setClip(clip__0_417);
        float alpha__0_418 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_418 = g.getClip();
        AffineTransform defaultTransform__0_418 = g.getTransform();
        
//      _0_418 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(438.866, 571.518);
        ((GeneralPath)shape).lineTo(439.19598, 571.518);
        ((GeneralPath)shape).lineTo(439.19598, 572.886);
        ((GeneralPath)shape).curveTo(439.19598, 573.547, 439.408, 573.70496, 440.10397, 573.70496);
        ((GeneralPath)shape).curveTo(440.79196, 573.70496, 440.99298, 573.46594, 440.99298, 572.67395);
        ((GeneralPath)shape).lineTo(440.44598, 572.67395);
        ((GeneralPath)shape).curveTo(440.44598, 572.95197, 440.485, 573.23694, 440.10498, 573.23694);
        ((GeneralPath)shape).curveTo(439.82098, 573.23694, 439.82098, 573.1259, 439.82098, 572.88196);
        ((GeneralPath)shape).lineTo(439.82098, 571.519);
        ((GeneralPath)shape).lineTo(440.86, 571.519);
        ((GeneralPath)shape).lineTo(440.86, 571.05);
        ((GeneralPath)shape).lineTo(439.82098, 571.05);
        ((GeneralPath)shape).lineTo(439.82098, 570.43097);
        ((GeneralPath)shape).lineTo(439.19598, 570.43097);
        ((GeneralPath)shape).lineTo(439.19598, 571.05);
        ((GeneralPath)shape).lineTo(438.866, 571.05);
        ((GeneralPath)shape).lineTo(438.866, 571.518);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_418;
        g.setTransform(defaultTransform__0_418);
        g.setClip(clip__0_418);
        float alpha__0_419 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_419 = g.getClip();
        AffineTransform defaultTransform__0_419 = g.getTransform();
        
//      _0_419 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(419.171, 577.049);
        ((GeneralPath)shape).lineTo(419.171, 579.705);
        ((GeneralPath)shape).lineTo(419.796, 579.705);
        ((GeneralPath)shape).lineTo(419.796, 578.26404);
        ((GeneralPath)shape).curveTo(419.796, 577.79504, 419.869, 577.53503, 420.409, 577.53503);
        ((GeneralPath)shape).curveTo(420.806, 577.53503, 420.889, 577.66406, 420.889, 578.044);
        ((GeneralPath)shape).lineTo(420.889, 579.705);
        ((GeneralPath)shape).lineTo(421.514, 579.705);
        ((GeneralPath)shape).lineTo(421.514, 577.97504);
        ((GeneralPath)shape).curveTo(421.514, 577.335, 421.308, 577.049, 420.62802, 577.049);
        ((GeneralPath)shape).curveTo(420.26303, 577.049, 419.95303, 577.089, 419.81503, 577.47504);
        ((GeneralPath)shape).lineTo(419.79504, 577.47504);
        ((GeneralPath)shape).lineTo(419.79504, 577.049);
        ((GeneralPath)shape).lineTo(419.171, 577.049);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_419;
        g.setTransform(defaultTransform__0_419);
        g.setClip(clip__0_419);
        float alpha__0_420 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_420 = g.getClip();
        AffineTransform defaultTransform__0_420 = g.getTransform();
        
//      _0_420 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(423.294, 577.535);
        ((GeneralPath)shape).curveTo(423.836, 577.535, 423.88, 577.69196, 423.88, 578.39197);
        ((GeneralPath)shape).curveTo(423.88, 579.08295, 423.836, 579.23596, 423.294, 579.23596);
        ((GeneralPath)shape).curveTo(422.752, 579.23596, 422.708, 579.084, 422.708, 578.39197);
        ((GeneralPath)shape).curveTo(422.708, 577.692, 422.752, 577.535, 423.294, 577.535);
        ((GeneralPath)shape).moveTo(423.294, 577.049);
        ((GeneralPath)shape).curveTo(422.22, 577.049, 422.083, 577.37, 422.083, 578.379);
        ((GeneralPath)shape).curveTo(422.083, 579.38403, 422.22, 579.705, 423.294, 579.705);
        ((GeneralPath)shape).curveTo(424.368, 579.705, 424.505, 579.38403, 424.505, 578.379);
        ((GeneralPath)shape).curveTo(424.505, 577.37, 424.368, 577.049, 423.294, 577.049);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_420;
        g.setTransform(defaultTransform__0_420);
        g.setClip(clip__0_420);
        float alpha__0_421 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_421 = g.getClip();
        AffineTransform defaultTransform__0_421 = g.getTransform();
        
//      _0_421 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(427.535, 575.877);
        ((GeneralPath)shape).lineTo(426.832, 575.877);
        ((GeneralPath)shape).lineTo(426.832, 579.705);
        ((GeneralPath)shape).lineTo(429.285, 579.705);
        ((GeneralPath)shape).lineTo(429.285, 579.082);
        ((GeneralPath)shape).lineTo(427.535, 579.082);
        ((GeneralPath)shape).lineTo(427.535, 575.877);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_421;
        g.setTransform(defaultTransform__0_421);
        g.setClip(clip__0_421);
        float alpha__0_422 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_422 = g.getClip();
        AffineTransform defaultTransform__0_422 = g.getTransform();
        
//      _0_422 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(430.789, 577.535);
        ((GeneralPath)shape).curveTo(431.331, 577.535, 431.375, 577.69196, 431.375, 578.39197);
        ((GeneralPath)shape).curveTo(431.375, 579.08295, 431.331, 579.23596, 430.789, 579.23596);
        ((GeneralPath)shape).curveTo(430.247, 579.23596, 430.203, 579.084, 430.203, 578.39197);
        ((GeneralPath)shape).curveTo(430.203, 577.692, 430.247, 577.535, 430.789, 577.535);
        ((GeneralPath)shape).moveTo(430.789, 577.049);
        ((GeneralPath)shape).curveTo(429.715, 577.049, 429.578, 577.37, 429.578, 578.379);
        ((GeneralPath)shape).curveTo(429.578, 579.38403, 429.715, 579.705, 430.789, 579.705);
        ((GeneralPath)shape).curveTo(431.863, 579.705, 432.0, 579.38403, 432.0, 578.379);
        ((GeneralPath)shape).curveTo(432.0, 577.37, 431.863, 577.049, 430.789, 577.049);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_422;
        g.setTransform(defaultTransform__0_422);
        g.setClip(clip__0_422);
        float alpha__0_423 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_423 = g.getClip();
        AffineTransform defaultTransform__0_423 = g.getTransform();
        
//      _0_423 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(432.566, 577.049);
        ((GeneralPath)shape).lineTo(432.566, 579.705);
        ((GeneralPath)shape).lineTo(433.191, 579.705);
        ((GeneralPath)shape).lineTo(433.191, 578.26404);
        ((GeneralPath)shape).curveTo(433.191, 577.79504, 433.264, 577.53503, 433.803, 577.53503);
        ((GeneralPath)shape).curveTo(434.20102, 577.53503, 434.284, 577.66406, 434.284, 578.044);
        ((GeneralPath)shape).lineTo(434.284, 579.705);
        ((GeneralPath)shape).lineTo(434.909, 579.705);
        ((GeneralPath)shape).lineTo(434.909, 577.97504);
        ((GeneralPath)shape).curveTo(434.909, 577.335, 434.703, 577.049, 434.022, 577.049);
        ((GeneralPath)shape).curveTo(433.658, 577.049, 433.34702, 577.089, 433.209, 577.47504);
        ((GeneralPath)shape).lineTo(433.18903, 577.47504);
        ((GeneralPath)shape).lineTo(433.18903, 577.049);
        ((GeneralPath)shape).lineTo(432.566, 577.049);
        g.setPaint(paint);
        g.fill(shape);
        paint2(g, clip_, defaultTransform_, alpha__0, clip__0,
				defaultTransform__0, alpha__0_423, clip__0_423,
				defaultTransform__0_423);
	}
	private static void paint2(Graphics2D g, Shape clip_,
			AffineTransform defaultTransform_, float alpha__0, Shape clip__0,
			AffineTransform defaultTransform__0, float alpha__0_423,
			Shape clip__0_423, AffineTransform defaultTransform__0_423) {
		Shape shape;
		Paint paint;
		Stroke stroke;
		float origAlpha;
		origAlpha = alpha__0_423;
        g.setTransform(defaultTransform__0_423);
        g.setClip(clip__0_423);
        float alpha__0_424 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_424 = g.getClip();
        AffineTransform defaultTransform__0_424 = g.getTransform();
        
//      _0_424 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(437.275, 579.828);
        ((GeneralPath)shape).curveTo(437.275, 580.295, 437.165, 580.408, 436.651, 580.408);
        ((GeneralPath)shape).curveTo(436.31, 580.408, 436.181, 580.349, 436.181, 579.963);
        ((GeneralPath)shape).lineTo(435.556, 579.963);
        ((GeneralPath)shape).curveTo(435.516, 580.765, 435.996, 580.877, 436.637, 580.877);
        ((GeneralPath)shape).curveTo(437.599, 580.877, 437.9, 580.65, 437.9, 579.695);
        ((GeneralPath)shape).lineTo(437.9, 577.049);
        ((GeneralPath)shape).lineTo(437.275, 577.049);
        ((GeneralPath)shape).lineTo(437.275, 577.47504);
        ((GeneralPath)shape).curveTo(437.115, 577.129, 436.82498, 577.049, 436.455, 577.049);
        ((GeneralPath)shape).curveTo(435.478, 577.049, 435.478, 577.656, 435.478, 578.45404);
        ((GeneralPath)shape).curveTo(435.478, 579.21606, 435.582, 579.705, 436.468, 579.705);
        ((GeneralPath)shape).curveTo(436.78198, 579.705, 437.11298, 579.646, 437.275, 579.309);
        ((GeneralPath)shape).lineTo(437.275, 579.828);
        ((GeneralPath)shape).moveTo(436.655, 577.535);
        ((GeneralPath)shape).curveTo(437.191, 577.535, 437.275, 577.75696, 437.275, 578.308);
        ((GeneralPath)shape).curveTo(437.275, 578.924, 437.275, 579.23596, 436.655, 579.23596);
        ((GeneralPath)shape).curveTo(436.103, 579.23596, 436.103, 578.89496, 436.103, 578.308);
        ((GeneralPath)shape).curveTo(436.104, 577.658, 436.227, 577.535, 436.655, 577.535);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_424;
        g.setTransform(defaultTransform__0_424);
        g.setClip(clip__0_424);
        float alpha__0_425 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_425 = g.getClip();
        AffineTransform defaultTransform__0_425 = g.getTransform();
        
//      _0_425 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(440.208, 577.049);
        ((GeneralPath)shape).lineTo(440.208, 579.705);
        ((GeneralPath)shape).lineTo(440.833, 579.705);
        ((GeneralPath)shape).lineTo(440.833, 578.089);
        ((GeneralPath)shape).curveTo(440.833, 577.75, 440.95, 577.535, 441.351, 577.535);
        ((GeneralPath)shape).curveTo(441.66602, 577.535, 441.69302, 577.68896, 441.69302, 577.953);
        ((GeneralPath)shape).lineTo(441.69302, 578.089);
        ((GeneralPath)shape).lineTo(442.31802, 578.089);
        ((GeneralPath)shape).lineTo(442.31802, 577.878);
        ((GeneralPath)shape).curveTo(442.31802, 577.381, 442.174, 577.049, 441.58102, 577.049);
        ((GeneralPath)shape).curveTo(441.25702, 577.049, 440.96802, 577.133, 440.834, 577.421);
        ((GeneralPath)shape).lineTo(440.834, 577.049);
        ((GeneralPath)shape).lineTo(440.208, 577.049);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_425;
        g.setTransform(defaultTransform__0_425);
        g.setClip(clip__0_425);
        float alpha__0_426 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_426 = g.getClip();
        AffineTransform defaultTransform__0_426 = g.getTransform();
        
//      _0_426 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(444.36, 579.705);
        ((GeneralPath)shape).lineTo(444.985, 579.705);
        ((GeneralPath)shape).lineTo(444.985, 578.059);
        ((GeneralPath)shape).curveTo(444.985, 577.179, 444.63098, 577.04803, 443.78, 577.04803);
        ((GeneralPath)shape).curveTo(443.173, 577.04803, 442.641, 577.04803, 442.641, 577.82806);
        ((GeneralPath)shape).lineTo(443.266, 577.82806);
        ((GeneralPath)shape).curveTo(443.266, 577.5071, 443.495, 577.4831, 443.779, 577.4831);
        ((GeneralPath)shape).curveTo(444.324, 577.4831, 444.36, 577.6321, 444.36, 578.05206);
        ((GeneralPath)shape).lineTo(444.36, 578.38904);
        ((GeneralPath)shape).lineTo(444.339, 578.38904);
        ((GeneralPath)shape).curveTo(444.188, 578.06305, 443.871, 578.06305, 443.54898, 578.06305);
        ((GeneralPath)shape).curveTo(442.895, 578.06305, 442.563, 578.23804, 442.563, 578.8691);
        ((GeneralPath)shape).curveTo(442.563, 579.5801, 442.94897, 579.7041, 443.54398, 579.7041);
        ((GeneralPath)shape).curveTo(443.84897, 579.7041, 444.23297, 579.7041, 444.35898, 579.3171);
        ((GeneralPath)shape).lineTo(444.35898, 579.705);
        ((GeneralPath)shape).moveTo(443.771, 578.533);
        ((GeneralPath)shape).curveTo(444.086, 578.533, 444.36, 578.533, 444.36, 578.866);
        ((GeneralPath)shape).curveTo(444.36, 579.21, 444.111, 579.236, 443.771, 579.236);
        ((GeneralPath)shape).curveTo(443.34, 579.236, 443.188, 579.20404, 443.188, 578.87103);
        ((GeneralPath)shape).curveTo(443.188, 578.533, 443.447, 578.533, 443.771, 578.533);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_426;
        g.setTransform(defaultTransform__0_426);
        g.setClip(clip__0_426);
        float alpha__0_427 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_427 = g.getClip();
        AffineTransform defaultTransform__0_427 = g.getTransform();
        
//      _0_427 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(445.586, 577.049);
        ((GeneralPath)shape).lineTo(445.586, 579.705);
        ((GeneralPath)shape).lineTo(446.211, 579.705);
        ((GeneralPath)shape).lineTo(446.211, 578.26404);
        ((GeneralPath)shape).curveTo(446.211, 577.79504, 446.284, 577.53503, 446.824, 577.53503);
        ((GeneralPath)shape).curveTo(447.221, 577.53503, 447.30402, 577.66406, 447.30402, 578.044);
        ((GeneralPath)shape).lineTo(447.30402, 579.705);
        ((GeneralPath)shape).lineTo(447.92902, 579.705);
        ((GeneralPath)shape).lineTo(447.92902, 577.97504);
        ((GeneralPath)shape).curveTo(447.92902, 577.335, 447.72302, 577.049, 447.04303, 577.049);
        ((GeneralPath)shape).curveTo(446.67804, 577.049, 446.36804, 577.089, 446.23004, 577.47504);
        ((GeneralPath)shape).lineTo(446.21005, 577.47504);
        ((GeneralPath)shape).lineTo(446.21005, 577.049);
        ((GeneralPath)shape).lineTo(445.586, 577.049);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_427;
        g.setTransform(defaultTransform__0_427);
        g.setClip(clip__0_427);
        float alpha__0_428 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_428 = g.getClip();
        AffineTransform defaultTransform__0_428 = g.getTransform();
        
//      _0_428 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(450.295, 579.828);
        ((GeneralPath)shape).curveTo(450.295, 580.295, 450.186, 580.408, 449.67102, 580.408);
        ((GeneralPath)shape).curveTo(449.33102, 580.408, 449.20102, 580.349, 449.20102, 579.963);
        ((GeneralPath)shape).lineTo(448.57602, 579.963);
        ((GeneralPath)shape).curveTo(448.536, 580.765, 449.01602, 580.877, 449.65802, 580.877);
        ((GeneralPath)shape).curveTo(450.62003, 580.877, 450.92, 580.65, 450.92, 579.695);
        ((GeneralPath)shape).lineTo(450.92, 577.049);
        ((GeneralPath)shape).lineTo(450.295, 577.049);
        ((GeneralPath)shape).lineTo(450.295, 577.47504);
        ((GeneralPath)shape).curveTo(450.135, 577.129, 449.845, 577.049, 449.475, 577.049);
        ((GeneralPath)shape).curveTo(448.49802, 577.049, 448.49802, 577.656, 448.49802, 578.45404);
        ((GeneralPath)shape).curveTo(448.49802, 579.21606, 448.60202, 579.705, 449.488, 579.705);
        ((GeneralPath)shape).curveTo(449.802, 579.705, 450.133, 579.646, 450.295, 579.309);
        ((GeneralPath)shape).lineTo(450.295, 579.828);
        ((GeneralPath)shape).moveTo(449.675, 577.535);
        ((GeneralPath)shape).curveTo(450.211, 577.535, 450.29498, 577.75696, 450.29498, 578.308);
        ((GeneralPath)shape).curveTo(450.29498, 578.924, 450.29498, 579.23596, 449.675, 579.23596);
        ((GeneralPath)shape).curveTo(449.123, 579.23596, 449.123, 578.89496, 449.123, 578.308);
        ((GeneralPath)shape).curveTo(449.123, 577.658, 449.246, 577.535, 449.675, 577.535);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_428;
        g.setTransform(defaultTransform__0_428);
        g.setClip(clip__0_428);
        float alpha__0_429 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_429 = g.getClip();
        AffineTransform defaultTransform__0_429 = g.getTransform();
        
//      _0_429 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(452.094, 578.143);
        ((GeneralPath)shape).curveTo(452.094, 577.656, 452.129, 577.536, 452.672, 577.536);
        ((GeneralPath)shape).curveTo(453.186, 577.536, 453.266, 577.577, 453.266, 578.143);
        ((GeneralPath)shape).lineTo(452.094, 578.143);
        ((GeneralPath)shape).moveTo(453.266, 578.872);
        ((GeneralPath)shape).curveTo(453.266, 579.236, 453.022, 579.236, 452.672, 579.236);
        ((GeneralPath)shape).curveTo(452.108, 579.236, 452.094, 579.06604, 452.094, 578.533);
        ((GeneralPath)shape).lineTo(453.891, 578.533);
        ((GeneralPath)shape).curveTo(453.891, 577.369, 453.746, 577.049, 452.672, 577.049);
        ((GeneralPath)shape).curveTo(451.617, 577.049, 451.469, 577.45404, 451.469, 578.385);
        ((GeneralPath)shape).curveTo(451.469, 579.38904, 451.672, 579.705, 452.672, 579.705);
        ((GeneralPath)shape).curveTo(453.418, 579.705, 453.891, 579.666, 453.891, 578.872);
        ((GeneralPath)shape).lineTo(453.266, 578.872);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_429;
        g.setTransform(defaultTransform__0_429);
        g.setClip(clip__0_429);
        float alpha__0_430 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_430 = g.getClip();
        AffineTransform defaultTransform__0_430 = g.getTransform();
        
//      _0_430 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(458.344, 577.787);
        ((GeneralPath)shape).curveTo(458.344, 577.049, 457.744, 577.049, 457.24298, 577.049);
        ((GeneralPath)shape).curveTo(456.585, 577.049, 456.07797, 577.049, 456.07797, 577.758);
        ((GeneralPath)shape).curveTo(456.07797, 578.417, 456.248, 578.545, 457.18396, 578.56);
        ((GeneralPath)shape).curveTo(457.78195, 578.57, 457.79596, 578.679, 457.79596, 578.896);
        ((GeneralPath)shape).curveTo(457.79596, 579.237, 457.59695, 579.237, 457.23697, 579.237);
        ((GeneralPath)shape).curveTo(456.78897, 579.237, 456.70197, 579.194, 456.70197, 578.829);
        ((GeneralPath)shape).lineTo(456.07697, 578.829);
        ((GeneralPath)shape).curveTo(456.07697, 579.706, 456.54196, 579.706, 457.23398, 579.706);
        ((GeneralPath)shape).curveTo(457.87897, 579.706, 458.421, 579.637, 458.421, 578.93);
        ((GeneralPath)shape).curveTo(458.421, 578.084, 457.878, 578.12897, 457.289, 578.1);
        ((GeneralPath)shape).curveTo(456.768, 578.076, 456.702, 578.066, 456.702, 577.81696);
        ((GeneralPath)shape).curveTo(456.702, 577.48596, 456.922, 577.48596, 457.24, 577.48596);
        ((GeneralPath)shape).curveTo(457.56, 577.48596, 457.718, 577.48596, 457.718, 577.78894);
        ((GeneralPath)shape).lineTo(458.344, 577.78894);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_430;
        g.setTransform(defaultTransform__0_430);
        g.setClip(clip__0_430);
        float alpha__0_431 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_431 = g.getClip();
        AffineTransform defaultTransform__0_431 = g.getTransform();
        
//      _0_431 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(459.566, 575.877);
        ((GeneralPath)shape).lineTo(458.941, 575.877);
        ((GeneralPath)shape).lineTo(458.941, 579.705);
        ((GeneralPath)shape).lineTo(459.566, 579.705);
        ((GeneralPath)shape).lineTo(459.566, 578.26404);
        ((GeneralPath)shape).curveTo(459.566, 577.79504, 459.639, 577.53503, 460.17902, 577.53503);
        ((GeneralPath)shape).curveTo(460.57602, 577.53503, 460.65903, 577.66406, 460.65903, 578.044);
        ((GeneralPath)shape).lineTo(460.65903, 579.705);
        ((GeneralPath)shape).lineTo(461.28403, 579.705);
        ((GeneralPath)shape).lineTo(461.28403, 577.97504);
        ((GeneralPath)shape).curveTo(461.28403, 577.335, 461.07803, 577.049, 460.39804, 577.049);
        ((GeneralPath)shape).curveTo(460.03305, 577.049, 459.72205, 577.089, 459.58505, 577.479);
        ((GeneralPath)shape).lineTo(459.56506, 577.479);
        ((GeneralPath)shape).lineTo(459.56506, 575.877);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_431;
        g.setTransform(defaultTransform__0_431);
        g.setClip(clip__0_431);
        float alpha__0_432 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_432 = g.getClip();
        AffineTransform defaultTransform__0_432 = g.getTransform();
        
//      _0_432 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(463.064, 577.535);
        ((GeneralPath)shape).curveTo(463.606, 577.535, 463.65, 577.69196, 463.65, 578.39197);
        ((GeneralPath)shape).curveTo(463.65, 579.08295, 463.606, 579.23596, 463.064, 579.23596);
        ((GeneralPath)shape).curveTo(462.522, 579.23596, 462.478, 579.084, 462.478, 578.39197);
        ((GeneralPath)shape).curveTo(462.479, 577.692, 462.522, 577.535, 463.064, 577.535);
        ((GeneralPath)shape).moveTo(463.064, 577.049);
        ((GeneralPath)shape).curveTo(461.99, 577.049, 461.853, 577.37, 461.853, 578.379);
        ((GeneralPath)shape).curveTo(461.853, 579.38403, 461.99, 579.705, 463.064, 579.705);
        ((GeneralPath)shape).curveTo(464.138, 579.705, 464.275, 579.38403, 464.275, 578.379);
        ((GeneralPath)shape).curveTo(464.275, 577.37, 464.139, 577.049, 463.064, 577.049);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_432;
        g.setTransform(defaultTransform__0_432);
        g.setClip(clip__0_432);
        float alpha__0_433 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_433 = g.getClip();
        AffineTransform defaultTransform__0_433 = g.getTransform();
        
//      _0_433 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(464.512, 577.518);
        ((GeneralPath)shape).lineTo(464.84198, 577.518);
        ((GeneralPath)shape).lineTo(464.84198, 578.886);
        ((GeneralPath)shape).curveTo(464.84198, 579.547, 465.054, 579.70496, 465.74997, 579.70496);
        ((GeneralPath)shape).curveTo(466.43796, 579.70496, 466.63898, 579.46594, 466.63898, 578.67395);
        ((GeneralPath)shape).lineTo(466.09198, 578.67395);
        ((GeneralPath)shape).curveTo(466.09198, 578.95197, 466.13098, 579.23694, 465.75098, 579.23694);
        ((GeneralPath)shape).curveTo(465.46698, 579.23694, 465.46698, 579.1259, 465.46698, 578.88196);
        ((GeneralPath)shape).lineTo(465.46698, 577.519);
        ((GeneralPath)shape).lineTo(466.50598, 577.519);
        ((GeneralPath)shape).lineTo(466.50598, 577.05);
        ((GeneralPath)shape).lineTo(465.46698, 577.05);
        ((GeneralPath)shape).lineTo(465.46698, 576.43097);
        ((GeneralPath)shape).lineTo(464.84198, 576.43097);
        ((GeneralPath)shape).lineTo(464.84198, 577.05);
        ((GeneralPath)shape).lineTo(464.512, 577.05);
        ((GeneralPath)shape).lineTo(464.512, 577.518);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_433;
        g.setTransform(defaultTransform__0_433);
        g.setClip(clip__0_433);
        float alpha__0_434 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_434 = g.getClip();
        AffineTransform defaultTransform__0_434 = g.getTransform();
        
//      _0_434 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(469.154, 577.787);
        ((GeneralPath)shape).curveTo(469.154, 577.049, 468.554, 577.049, 468.052, 577.049);
        ((GeneralPath)shape).curveTo(467.394, 577.049, 466.888, 577.049, 466.888, 577.758);
        ((GeneralPath)shape).curveTo(466.888, 578.417, 467.057, 578.545, 467.993, 578.56);
        ((GeneralPath)shape).curveTo(468.592, 578.57, 468.60602, 578.679, 468.60602, 578.896);
        ((GeneralPath)shape).curveTo(468.60602, 579.237, 468.407, 579.237, 468.04602, 579.237);
        ((GeneralPath)shape).curveTo(467.59802, 579.237, 467.51202, 579.194, 467.51202, 578.829);
        ((GeneralPath)shape).lineTo(466.88702, 578.829);
        ((GeneralPath)shape).curveTo(466.88702, 579.706, 467.35202, 579.706, 468.04404, 579.706);
        ((GeneralPath)shape).curveTo(468.68903, 579.706, 469.23105, 579.637, 469.23105, 578.93);
        ((GeneralPath)shape).curveTo(469.23105, 578.084, 468.68704, 578.12897, 468.09906, 578.1);
        ((GeneralPath)shape).curveTo(467.57806, 578.076, 467.51205, 578.066, 467.51205, 577.81696);
        ((GeneralPath)shape).curveTo(467.51205, 577.48596, 467.73206, 577.48596, 468.05005, 577.48596);
        ((GeneralPath)shape).curveTo(468.36905, 577.48596, 468.52805, 577.48596, 468.52805, 577.78894);
        ((GeneralPath)shape).lineTo(469.154, 577.78894);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_434;
        g.setTransform(defaultTransform__0_434);
        g.setClip(clip__0_434);
        float alpha__0_435 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_435 = g.getClip();
        AffineTransform defaultTransform__0_435 = g.getTransform();
        
//      _0_435 is ShapeNode
        paint = new Color(199, 200, 201, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(416.65, 573.922);
        ((GeneralPath)shape).curveTo(416.65, 574.716, 416.007, 575.36, 415.212, 575.36);
        ((GeneralPath)shape).lineTo(411.337, 575.36);
        ((GeneralPath)shape).curveTo(410.543, 575.36, 409.89902, 574.716, 409.89902, 573.922);
        ((GeneralPath)shape).lineTo(409.89902, 570.007);
        ((GeneralPath)shape).curveTo(409.89902, 569.213, 410.544, 568.56903, 411.337, 568.56903);
        ((GeneralPath)shape).lineTo(415.212, 568.56903);
        ((GeneralPath)shape).curveTo(416.00702, 568.56903, 416.65, 569.213, 416.65, 570.007);
        ((GeneralPath)shape).lineTo(416.65, 573.922);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_435;
        g.setTransform(defaultTransform__0_435);
        g.setClip(clip__0_435);
        float alpha__0_436 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_436 = g.getClip();
        AffineTransform defaultTransform__0_436 = g.getTransform();
        
//      _0_436 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        stroke = new BasicStroke(1.0f,0,0,10.0f,null,0.0f);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(416.65, 573.922);
        ((GeneralPath)shape).curveTo(416.65, 574.716, 416.00598, 575.36, 415.212, 575.36);
        ((GeneralPath)shape).lineTo(411.337, 575.36);
        ((GeneralPath)shape).curveTo(410.543, 575.36, 409.89902, 574.716, 409.89902, 573.922);
        ((GeneralPath)shape).lineTo(409.89902, 570.007);
        ((GeneralPath)shape).curveTo(409.89902, 569.213, 410.544, 568.56903, 411.337, 568.56903);
        ((GeneralPath)shape).lineTo(415.212, 568.56903);
        ((GeneralPath)shape).curveTo(416.006, 568.56903, 416.65, 569.213, 416.65, 570.007);
        ((GeneralPath)shape).lineTo(416.65, 573.922);
        ((GeneralPath)shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_436;
        g.setTransform(defaultTransform__0_436);
        g.setClip(clip__0_436);
        float alpha__0_437 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_437 = g.getClip();
        AffineTransform defaultTransform__0_437 = g.getTransform();
        
//      _0_437 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(311.699, 531.786);
        ((GeneralPath)shape).lineTo(313.741, 531.786);
        ((GeneralPath)shape).lineTo(313.741, 531.234);
        ((GeneralPath)shape).lineTo(311.074, 531.234);
        ((GeneralPath)shape).lineTo(311.074, 533.265);
        ((GeneralPath)shape).lineTo(311.699, 533.265);
        ((GeneralPath)shape).curveTo(311.76102, 533.031, 312.039, 533.031, 312.413, 533.031);
        ((GeneralPath)shape).curveTo(313.081, 533.031, 313.184, 533.109, 313.184, 533.739);
        ((GeneralPath)shape).curveTo(313.184, 534.374, 313.08398, 534.437, 312.427, 534.437);
        ((GeneralPath)shape).curveTo(311.971, 534.437, 311.699, 534.437, 311.699, 533.925);
        ((GeneralPath)shape).lineTo(310.996, 533.925);
        ((GeneralPath)shape).curveTo(310.996, 534.923, 311.417, 535.062, 312.357, 535.062);
        ((GeneralPath)shape).curveTo(313.496, 535.062, 313.886, 534.909, 313.886, 533.724);
        ((GeneralPath)shape).curveTo(313.886, 532.77, 313.623, 532.406, 312.625, 532.406);
        ((GeneralPath)shape).curveTo(312.321, 532.406, 311.878, 532.406, 311.708, 532.719);
        ((GeneralPath)shape).lineTo(311.698, 532.719);
        ((GeneralPath)shape).lineTo(311.698, 531.786);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_437;
        g.setTransform(defaultTransform__0_437);
        g.setClip(clip__0_437);
        float alpha__0_438 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_438 = g.getClip();
        AffineTransform defaultTransform__0_438 = g.getTransform();
        
//      _0_438 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(314.628, 535.688);
        ((GeneralPath)shape).lineTo(314.75598, 535.688);
        ((GeneralPath)shape).curveTo(315.30298, 535.688, 315.30298, 535.402, 315.30298, 534.971);
        ((GeneralPath)shape).lineTo(315.30298, 534.379);
        ((GeneralPath)shape).lineTo(314.67798, 534.379);
        ((GeneralPath)shape).lineTo(314.67798, 535.06305);
        ((GeneralPath)shape).lineTo(314.934, 535.06305);
        ((GeneralPath)shape).curveTo(314.934, 535.32404, 314.817, 535.37604, 314.628, 535.37604);
        ((GeneralPath)shape).lineTo(314.628, 535.688);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_438;
        g.setTransform(defaultTransform__0_438);
        g.setClip(clip__0_438);
        float alpha__0_439 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_439 = g.getClip();
        AffineTransform defaultTransform__0_439 = g.getTransform();
        
//      _0_439 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(316.359, 533.976);
        ((GeneralPath)shape).curveTo(316.281, 535.063, 316.911, 535.063, 317.72302, 535.063);
        ((GeneralPath)shape).curveTo(318.62003, 535.063, 319.17102, 534.89197, 319.17102, 533.937);
        ((GeneralPath)shape).lineTo(319.17102, 532.847);
        ((GeneralPath)shape).curveTo(319.17102, 531.545, 318.95102, 531.235, 317.747, 531.235);
        ((GeneralPath)shape).curveTo(316.63702, 531.235, 316.358, 531.487, 316.358, 532.42896);
        ((GeneralPath)shape).curveTo(316.358, 533.45593, 316.797, 533.57794, 317.563, 533.57794);
        ((GeneralPath)shape).curveTo(318.022, 533.57794, 318.3, 533.50995, 318.45798, 533.18994);
        ((GeneralPath)shape).lineTo(318.468, 533.18994);
        ((GeneralPath)shape).lineTo(318.468, 533.8029);
        ((GeneralPath)shape).curveTo(318.468, 534.4369, 318.28598, 534.4369, 317.723, 534.4369);
        ((GeneralPath)shape).curveTo(317.156, 534.4369, 316.98398, 534.4369, 316.98398, 533.9749);
        ((GeneralPath)shape).lineTo(316.359, 533.9749);
        ((GeneralPath)shape).moveTo(317.75, 531.831);
        ((GeneralPath)shape).curveTo(318.271, 531.831, 318.469, 531.86, 318.469, 532.414);
        ((GeneralPath)shape).curveTo(318.469, 532.914, 318.354, 532.953, 317.75, 532.953);
        ((GeneralPath)shape).curveTo(317.146, 532.953, 317.062, 532.897, 317.062, 532.414);
        ((GeneralPath)shape).curveTo(317.063, 531.88, 317.146, 531.831, 317.75, 531.831);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_439;
        g.setTransform(defaultTransform__0_439);
        g.setClip(clip__0_439);
        float alpha__0_440 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_440 = g.getClip();
        AffineTransform defaultTransform__0_440 = g.getTransform();
        
//      _0_440 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new Rectangle2D.Double(374.8590087890625, 533.6950073242188, 2.5, 0.39100000262260437);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_440;
        g.setTransform(defaultTransform__0_440);
        g.setClip(clip__0_440);
        float alpha__0_441 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_441 = g.getClip();
        AffineTransform defaultTransform__0_441 = g.getTransform();
        
//      _0_441 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(379.859, 531.43);
        ((GeneralPath)shape).lineTo(378.971, 531.43);
        ((GeneralPath)shape).lineTo(377.771, 532.578);
        ((GeneralPath)shape).lineTo(378.181, 533.033);
        ((GeneralPath)shape).lineTo(379.156, 532.036);
        ((GeneralPath)shape).lineTo(379.156, 535.258);
        ((GeneralPath)shape).lineTo(379.859, 535.258);
        ((GeneralPath)shape).lineTo(379.859, 531.43);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_441;
        g.setTransform(defaultTransform__0_441);
        g.setClip(clip__0_441);
        float alpha__0_442 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_442 = g.getClip();
        AffineTransform defaultTransform__0_442 = g.getTransform();
        
//      _0_442 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(386.728, 534.661);
        ((GeneralPath)shape).lineTo(386.713, 534.661);
        ((GeneralPath)shape).lineTo(385.868, 531.43);
        ((GeneralPath)shape).lineTo(385.034, 531.43);
        ((GeneralPath)shape).lineTo(384.172, 534.661);
        ((GeneralPath)shape).lineTo(384.15, 534.661);
        ((GeneralPath)shape).lineTo(383.367, 531.43);
        ((GeneralPath)shape).lineTo(382.645, 531.43);
        ((GeneralPath)shape).lineTo(383.623, 535.258);
        ((GeneralPath)shape).lineTo(384.658, 535.258);
        ((GeneralPath)shape).lineTo(385.438, 532.33);
        ((GeneralPath)shape).lineTo(385.457, 532.33);
        ((GeneralPath)shape).lineTo(386.21, 535.258);
        ((GeneralPath)shape).lineTo(387.255, 535.258);
        ((GeneralPath)shape).lineTo(388.27, 531.43);
        ((GeneralPath)shape).lineTo(387.541, 531.43);
        ((GeneralPath)shape).lineTo(386.728, 534.661);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_442;
        g.setTransform(defaultTransform__0_442);
        g.setClip(clip__0_442);
        float alpha__0_443 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_443 = g.getClip();
        AffineTransform defaultTransform__0_443 = g.getTransform();
        
//      _0_443 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(390.125, 535.258);
        ((GeneralPath)shape).lineTo(390.75, 535.258);
        ((GeneralPath)shape).lineTo(390.75, 533.612);
        ((GeneralPath)shape).curveTo(390.75, 532.732, 390.396, 532.601, 389.546, 532.601);
        ((GeneralPath)shape).curveTo(388.938, 532.601, 388.40598, 532.601, 388.40598, 533.38104);
        ((GeneralPath)shape).lineTo(389.03098, 533.38104);
        ((GeneralPath)shape).curveTo(389.03098, 533.06006, 389.25998, 533.0361, 389.54398, 533.0361);
        ((GeneralPath)shape).curveTo(390.089, 533.0361, 390.12497, 533.18506, 390.12497, 533.60504);
        ((GeneralPath)shape).lineTo(390.12497, 533.942);
        ((GeneralPath)shape).lineTo(390.10397, 533.942);
        ((GeneralPath)shape).curveTo(389.95297, 533.616, 389.636, 533.616, 389.31396, 533.616);
        ((GeneralPath)shape).curveTo(388.65997, 533.616, 388.32797, 533.791, 388.32797, 534.42206);
        ((GeneralPath)shape).curveTo(388.32797, 535.1321, 388.71396, 535.2571, 389.30896, 535.2571);
        ((GeneralPath)shape).curveTo(389.61496, 535.2571, 389.99896, 535.2571, 390.12396, 534.87006);
        ((GeneralPath)shape).lineTo(390.12396, 535.258);
        ((GeneralPath)shape).moveTo(389.537, 534.086);
        ((GeneralPath)shape).curveTo(389.85098, 534.086, 390.125, 534.086, 390.125, 534.419);
        ((GeneralPath)shape).curveTo(390.125, 534.762, 389.876, 534.789, 389.537, 534.789);
        ((GeneralPath)shape).curveTo(389.106, 534.789, 388.95297, 534.757, 388.95297, 534.424);
        ((GeneralPath)shape).curveTo(388.953, 534.086, 389.212, 534.086, 389.537, 534.086);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_443;
        g.setTransform(defaultTransform__0_443);
        g.setClip(clip__0_443);
        float alpha__0_444 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_444 = g.getClip();
        AffineTransform defaultTransform__0_444 = g.getTransform();
        
//      _0_444 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new Rectangle2D.Double(391.35198974609375, 531.4299926757812, 0.625, 3.828000068664551);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_444;
        g.setTransform(defaultTransform__0_444);
        g.setClip(clip__0_444);
        float alpha__0_445 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_445 = g.getClip();
        AffineTransform defaultTransform__0_445 = g.getTransform();
        
//      _0_445 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(393.236, 531.43);
        ((GeneralPath)shape).lineTo(392.611, 531.43);
        ((GeneralPath)shape).lineTo(392.611, 535.258);
        ((GeneralPath)shape).lineTo(393.236, 535.258);
        ((GeneralPath)shape).lineTo(393.236, 534.086);
        ((GeneralPath)shape).lineTo(393.392, 534.086);
        ((GeneralPath)shape).lineTo(394.267, 535.258);
        ((GeneralPath)shape).lineTo(395.026, 535.258);
        ((GeneralPath)shape).lineTo(393.907, 533.852);
        ((GeneralPath)shape).lineTo(394.837, 532.602);
        ((GeneralPath)shape).lineTo(394.127, 532.602);
        ((GeneralPath)shape).lineTo(393.392, 533.617);
        ((GeneralPath)shape).lineTo(393.236, 533.617);
        ((GeneralPath)shape).lineTo(393.236, 531.43);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_445;
        g.setTransform(defaultTransform__0_445);
        g.setClip(clip__0_445);
        float alpha__0_446 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_446 = g.getClip();
        AffineTransform defaultTransform__0_446 = g.getTransform();
        
//      _0_446 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(400.738, 532.026);
        ((GeneralPath)shape).lineTo(400.738, 535.258);
        ((GeneralPath)shape).lineTo(401.441, 535.258);
        ((GeneralPath)shape).lineTo(401.441, 531.43);
        ((GeneralPath)shape).lineTo(400.273, 531.43);
        ((GeneralPath)shape).lineTo(399.262, 534.271);
        ((GeneralPath)shape).lineTo(399.242, 534.271);
        ((GeneralPath)shape).lineTo(398.209, 531.43);
        ((GeneralPath)shape).lineTo(397.066, 531.43);
        ((GeneralPath)shape).lineTo(397.066, 535.258);
        ((GeneralPath)shape).lineTo(397.77, 535.258);
        ((GeneralPath)shape).lineTo(397.77, 532.042);
        ((GeneralPath)shape).lineTo(398.928, 535.258);
        ((GeneralPath)shape).lineTo(399.58, 535.258);
        ((GeneralPath)shape).lineTo(400.738, 532.026);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_446;
        g.setTransform(defaultTransform__0_446);
        g.setClip(clip__0_446);
        float alpha__0_447 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_447 = g.getClip();
        AffineTransform defaultTransform__0_447 = g.getTransform();
        
//      _0_447 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(402.805, 533.383);
        ((GeneralPath)shape).lineTo(402.805, 532.052);
        ((GeneralPath)shape).lineTo(403.75, 532.052);
        ((GeneralPath)shape).curveTo(404.291, 532.052, 404.367, 532.206, 404.367, 532.748);
        ((GeneralPath)shape).curveTo(404.367, 533.285, 404.266, 533.383, 403.75, 533.383);
        ((GeneralPath)shape).lineTo(402.805, 533.383);
        ((GeneralPath)shape).moveTo(402.102, 535.258);
        ((GeneralPath)shape).lineTo(402.805, 535.258);
        ((GeneralPath)shape).lineTo(402.805, 534.008);
        ((GeneralPath)shape).lineTo(403.75, 534.008);
        ((GeneralPath)shape).curveTo(404.719, 534.008, 405.07, 533.82, 405.07, 532.742);
        ((GeneralPath)shape).curveTo(405.07, 531.674, 404.743, 531.429, 403.747, 531.429);
        ((GeneralPath)shape).lineTo(402.101, 531.429);
        ((GeneralPath)shape).lineTo(402.101, 535.258);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_447;
        g.setTransform(defaultTransform__0_447);
        g.setClip(clip__0_447);
        float alpha__0_448 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_448 = g.getClip();
        AffineTransform defaultTransform__0_448 = g.getTransform();
        
//      _0_448 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(328.875, 531.234);
        ((GeneralPath)shape).lineTo(328.172, 531.234);
        ((GeneralPath)shape).lineTo(328.172, 535.063);
        ((GeneralPath)shape).lineTo(330.626, 535.063);
        ((GeneralPath)shape).lineTo(330.626, 534.439);
        ((GeneralPath)shape).lineTo(328.875, 534.439);
        ((GeneralPath)shape).lineTo(328.875, 531.234);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_448;
        g.setTransform(defaultTransform__0_448);
        g.setClip(clip__0_448);
        float alpha__0_449 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_449 = g.getClip();
        AffineTransform defaultTransform__0_449 = g.getTransform();
        
//      _0_449 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(331.544, 533.5);
        ((GeneralPath)shape).curveTo(331.544, 533.013, 331.579, 532.892, 332.12302, 532.892);
        ((GeneralPath)shape).curveTo(332.63702, 532.892, 332.716, 532.934, 332.716, 533.5);
        ((GeneralPath)shape).lineTo(331.544, 533.5);
        ((GeneralPath)shape).moveTo(332.716, 534.229);
        ((GeneralPath)shape).curveTo(332.716, 534.593, 332.47202, 534.593, 332.12302, 534.593);
        ((GeneralPath)shape).curveTo(331.55902, 534.593, 331.544, 534.42303, 331.544, 533.89);
        ((GeneralPath)shape).lineTo(333.341, 533.89);
        ((GeneralPath)shape).curveTo(333.341, 532.72504, 333.196, 532.406, 332.12302, 532.406);
        ((GeneralPath)shape).curveTo(331.06702, 532.406, 330.919, 532.81104, 330.919, 533.741);
        ((GeneralPath)shape).curveTo(330.919, 534.74603, 331.12302, 535.062, 332.12302, 535.062);
        ((GeneralPath)shape).curveTo(332.868, 535.062, 333.341, 535.023, 333.341, 534.229);
        ((GeneralPath)shape).lineTo(332.716, 534.229);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_449;
        g.setTransform(defaultTransform__0_449);
        g.setClip(clip__0_449);
        float alpha__0_450 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_450 = g.getClip();
        AffineTransform defaultTransform__0_450 = g.getTransform();
        
//      _0_450 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(335.565, 535.186);
        ((GeneralPath)shape).curveTo(335.565, 535.652, 335.456, 535.766, 334.94202, 535.766);
        ((GeneralPath)shape).curveTo(334.601, 535.766, 334.471, 535.707, 334.471, 535.32);
        ((GeneralPath)shape).lineTo(333.846, 535.32);
        ((GeneralPath)shape).curveTo(333.806, 536.123, 334.28702, 536.235, 334.928, 536.235);
        ((GeneralPath)shape).curveTo(335.89, 536.235, 336.19, 536.00696, 336.19, 535.053);
        ((GeneralPath)shape).lineTo(336.19, 532.407);
        ((GeneralPath)shape).lineTo(335.565, 532.407);
        ((GeneralPath)shape).lineTo(335.565, 532.833);
        ((GeneralPath)shape).curveTo(335.406, 532.487, 335.115, 532.407, 334.745, 532.407);
        ((GeneralPath)shape).curveTo(333.768, 532.407, 333.768, 533.014, 333.768, 533.811);
        ((GeneralPath)shape).curveTo(333.768, 534.573, 333.872, 535.063, 334.758, 535.063);
        ((GeneralPath)shape).curveTo(335.073, 535.063, 335.40298, 535.00397, 335.565, 534.667);
        ((GeneralPath)shape).lineTo(335.565, 535.186);
        ((GeneralPath)shape).moveTo(334.945, 532.892);
        ((GeneralPath)shape).curveTo(335.48102, 532.892, 335.565, 533.11505, 335.565, 533.666);
        ((GeneralPath)shape).curveTo(335.565, 534.28204, 335.565, 534.594, 334.945, 534.594);
        ((GeneralPath)shape).curveTo(334.393, 534.594, 334.393, 534.253, 334.393, 533.666);
        ((GeneralPath)shape).curveTo(334.394, 533.015, 334.518, 532.892, 334.945, 532.892);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_450;
        g.setTransform(defaultTransform__0_450);
        g.setClip(clip__0_450);
        float alpha__0_451 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_451 = g.getClip();
        AffineTransform defaultTransform__0_451 = g.getTransform();
        
//      _0_451 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(339.005, 533.145);
        ((GeneralPath)shape).curveTo(339.005, 532.40704, 338.405, 532.40704, 337.903, 532.40704);
        ((GeneralPath)shape).curveTo(337.246, 532.40704, 336.739, 532.40704, 336.739, 533.116);
        ((GeneralPath)shape).curveTo(336.739, 533.775, 336.90802, 533.903, 337.84402, 533.918);
        ((GeneralPath)shape).curveTo(338.44302, 533.92804, 338.45703, 534.036, 338.45703, 534.254);
        ((GeneralPath)shape).curveTo(338.45703, 534.59503, 338.25803, 534.59503, 337.89703, 534.59503);
        ((GeneralPath)shape).curveTo(337.45004, 534.59503, 337.36304, 534.552, 337.36304, 534.187);
        ((GeneralPath)shape).lineTo(336.73804, 534.187);
        ((GeneralPath)shape).curveTo(336.73804, 535.064, 337.20303, 535.064, 337.89505, 535.064);
        ((GeneralPath)shape).curveTo(338.54004, 535.064, 339.08206, 534.994, 339.08206, 534.288);
        ((GeneralPath)shape).curveTo(339.08206, 533.44104, 338.53806, 533.487, 337.95007, 533.458);
        ((GeneralPath)shape).curveTo(337.42908, 533.434, 337.36307, 533.424, 337.36307, 533.175);
        ((GeneralPath)shape).curveTo(337.36307, 532.844, 337.58307, 532.844, 337.90106, 532.844);
        ((GeneralPath)shape).curveTo(338.22107, 532.844, 338.37906, 532.844, 338.37906, 533.147);
        ((GeneralPath)shape).lineTo(339.005, 533.147);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_451;
        g.setTransform(defaultTransform__0_451);
        g.setClip(clip__0_451);
        float alpha__0_452 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_452 = g.getClip();
        AffineTransform defaultTransform__0_452 = g.getTransform();
        
//      _0_452 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        stroke = new BasicStroke(1.0f,0,0,10.0f,null,0.0f);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(372.65, 535.279);
        ((GeneralPath)shape).curveTo(372.65, 536.072, 372.005, 536.717, 371.212, 536.717);
        ((GeneralPath)shape).lineTo(367.336, 536.717);
        ((GeneralPath)shape).curveTo(366.543, 536.717, 365.898, 536.07196, 365.898, 535.279);
        ((GeneralPath)shape).lineTo(365.898, 531.363);
        ((GeneralPath)shape).curveTo(365.898, 530.56995, 366.543, 529.925, 367.336, 529.925);
        ((GeneralPath)shape).lineTo(371.212, 529.925);
        ((GeneralPath)shape).curveTo(372.005, 529.925, 372.65, 530.571, 372.65, 531.363);
        ((GeneralPath)shape).lineTo(372.65, 535.279);
        ((GeneralPath)shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_452;
        g.setTransform(defaultTransform__0_452);
        g.setClip(clip__0_452);
        float alpha__0_453 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_453 = g.getClip();
        AffineTransform defaultTransform__0_453 = g.getTransform();
        
//      _0_453 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(420.827, 531.431);
        ((GeneralPath)shape).lineTo(420.305, 531.431);
        ((GeneralPath)shape).lineTo(419.373, 532.577);
        ((GeneralPath)shape).lineTo(419.612, 532.837);
        ((GeneralPath)shape).lineTo(420.476, 531.76);
        ((GeneralPath)shape).lineTo(420.476, 535.259);
        ((GeneralPath)shape).lineTo(420.827, 535.259);
        ((GeneralPath)shape).lineTo(420.827, 531.431);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_453;
        g.setTransform(defaultTransform__0_453);
        g.setClip(clip__0_453);
        float alpha__0_454 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_454 = g.getClip();
        AffineTransform defaultTransform__0_454 = g.getTransform();
        
//      _0_454 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(425.128, 531.431);
        ((GeneralPath)shape).lineTo(424.709, 531.431);
        ((GeneralPath)shape).lineTo(422.104, 535.799);
        ((GeneralPath)shape).lineTo(422.515, 535.799);
        ((GeneralPath)shape).lineTo(425.128, 531.431);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_454;
        g.setTransform(defaultTransform__0_454);
        g.setClip(clip__0_454);
        float alpha__0_455 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_455 = g.getClip();
        AffineTransform defaultTransform__0_455 = g.getTransform();
        
//      _0_455 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(427.978, 534.88);
        ((GeneralPath)shape).lineTo(425.939, 534.88);
        ((GeneralPath)shape).lineTo(425.939, 534.556);
        ((GeneralPath)shape).curveTo(425.939, 533.99805, 426.116, 533.913, 426.83798, 533.84705);
        ((GeneralPath)shape).curveTo(427.66898, 533.77203, 427.978, 533.6401, 427.978, 532.63104);
        ((GeneralPath)shape).curveTo(427.978, 531.66907, 427.705, 531.431, 426.827, 531.431);
        ((GeneralPath)shape).curveTo(425.681, 531.431, 425.58798, 531.736, 425.58798, 532.56604);
        ((GeneralPath)shape).lineTo(425.58798, 532.76306);
        ((GeneralPath)shape).lineTo(425.93997, 532.76306);
        ((GeneralPath)shape).lineTo(425.93997, 532.56604);
        ((GeneralPath)shape).curveTo(425.93997, 531.86804, 426.02597, 531.81104, 426.83597, 531.81104);
        ((GeneralPath)shape).curveTo(427.47598, 531.81104, 427.62796, 531.846, 427.62796, 532.63104);
        ((GeneralPath)shape).curveTo(427.62796, 533.421, 427.47296, 533.38605, 426.76297, 533.46606);
        ((GeneralPath)shape).curveTo(425.95898, 533.56305, 425.58896, 533.62805, 425.58896, 534.6161);
        ((GeneralPath)shape).lineTo(425.58896, 535.2591);
        ((GeneralPath)shape).lineTo(427.97995, 535.2591);
        ((GeneralPath)shape).lineTo(427.97995, 534.88);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_455;
        g.setTransform(defaultTransform__0_455);
        g.setClip(clip__0_455);
        float alpha__0_456 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_456 = g.getClip();
        AffineTransform defaultTransform__0_456 = g.getTransform();
        
//      _0_456 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(433.599, 534.662);
        ((GeneralPath)shape).lineTo(433.586, 534.662);
        ((GeneralPath)shape).lineTo(432.825, 531.431);
        ((GeneralPath)shape).lineTo(432.075, 531.431);
        ((GeneralPath)shape).lineTo(431.299, 534.662);
        ((GeneralPath)shape).lineTo(431.28, 534.662);
        ((GeneralPath)shape).lineTo(430.574, 531.431);
        ((GeneralPath)shape).lineTo(429.924, 531.431);
        ((GeneralPath)shape).lineTo(430.806, 535.259);
        ((GeneralPath)shape).lineTo(431.736, 535.259);
        ((GeneralPath)shape).lineTo(432.438, 532.332);
        ((GeneralPath)shape).lineTo(432.455, 532.332);
        ((GeneralPath)shape).lineTo(433.133, 535.259);
        ((GeneralPath)shape).lineTo(434.073, 535.259);
        ((GeneralPath)shape).lineTo(434.986, 531.431);
        ((GeneralPath)shape).lineTo(434.331, 531.431);
        ((GeneralPath)shape).lineTo(433.599, 534.662);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_456;
        g.setTransform(defaultTransform__0_456);
        g.setClip(clip__0_456);
        float alpha__0_457 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_457 = g.getClip();
        AffineTransform defaultTransform__0_457 = g.getTransform();
        
//      _0_457 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(436.657, 535.259);
        ((GeneralPath)shape).lineTo(437.22, 535.259);
        ((GeneralPath)shape).lineTo(437.22, 533.613);
        ((GeneralPath)shape).curveTo(437.22, 532.733, 436.902, 532.602, 436.136, 532.602);
        ((GeneralPath)shape).curveTo(435.58798, 532.602, 435.111, 532.602, 435.111, 533.382);
        ((GeneralPath)shape).lineTo(435.67398, 533.382);
        ((GeneralPath)shape).curveTo(435.67398, 533.06104, 435.87897, 533.038, 436.136, 533.038);
        ((GeneralPath)shape).curveTo(436.625, 533.038, 436.658, 533.18604, 436.658, 533.606);
        ((GeneralPath)shape).lineTo(436.658, 533.943);
        ((GeneralPath)shape).lineTo(436.63898, 533.943);
        ((GeneralPath)shape).curveTo(436.50198, 533.617, 436.218, 533.617, 435.92798, 533.617);
        ((GeneralPath)shape).curveTo(435.339, 533.617, 435.03998, 533.792, 435.03998, 534.42303);
        ((GeneralPath)shape).curveTo(435.03998, 535.13403, 435.38797, 535.25806, 435.92398, 535.25806);
        ((GeneralPath)shape).curveTo(436.19797, 535.25806, 436.54398, 535.25806, 436.65698, 534.87103);
        ((GeneralPath)shape).lineTo(436.65698, 535.259);
        ((GeneralPath)shape).moveTo(436.128, 534.087);
        ((GeneralPath)shape).curveTo(436.41098, 534.087, 436.65698, 534.087, 436.65698, 534.42096);
        ((GeneralPath)shape).curveTo(436.65698, 534.764, 436.43298, 534.79, 436.128, 534.79);
        ((GeneralPath)shape).curveTo(435.74, 534.79, 435.603, 534.759, 435.603, 534.42596);
        ((GeneralPath)shape).curveTo(435.603, 534.087, 435.836, 534.087, 436.128, 534.087);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_457;
        g.setTransform(defaultTransform__0_457);
        g.setClip(clip__0_457);
        float alpha__0_458 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_458 = g.getClip();
        AffineTransform defaultTransform__0_458 = g.getTransform();
        
//      _0_458 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new Rectangle2D.Double(437.760986328125, 531.4310302734375, 0.5630000233650208, 3.828000068664551);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_458;
        g.setTransform(defaultTransform__0_458);
        g.setClip(clip__0_458);
        float alpha__0_459 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_459 = g.getClip();
        AffineTransform defaultTransform__0_459 = g.getTransform();
        
//      _0_459 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(439.458, 531.431);
        ((GeneralPath)shape).lineTo(438.896, 531.431);
        ((GeneralPath)shape).lineTo(438.896, 535.259);
        ((GeneralPath)shape).lineTo(439.458, 535.259);
        ((GeneralPath)shape).lineTo(439.458, 534.087);
        ((GeneralPath)shape).lineTo(439.597, 534.087);
        ((GeneralPath)shape).lineTo(440.385, 535.259);
        ((GeneralPath)shape).lineTo(441.068, 535.259);
        ((GeneralPath)shape).lineTo(440.061, 533.853);
        ((GeneralPath)shape).lineTo(440.897, 532.603);
        ((GeneralPath)shape).lineTo(440.259, 532.603);
        ((GeneralPath)shape).lineTo(439.597, 533.618);
        ((GeneralPath)shape).lineTo(439.458, 533.618);
        ((GeneralPath)shape).lineTo(439.458, 531.431);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_459;
        g.setTransform(defaultTransform__0_459);
        g.setClip(clip__0_459);
        float alpha__0_460 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_460 = g.getClip();
        AffineTransform defaultTransform__0_460 = g.getTransform();
        
//      _0_460 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(446.209, 532.028);
        ((GeneralPath)shape).lineTo(446.209, 535.259);
        ((GeneralPath)shape).lineTo(446.842, 535.259);
        ((GeneralPath)shape).lineTo(446.842, 531.431);
        ((GeneralPath)shape).lineTo(445.79, 531.431);
        ((GeneralPath)shape).lineTo(444.88, 534.272);
        ((GeneralPath)shape).lineTo(444.862, 534.272);
        ((GeneralPath)shape).lineTo(443.933, 531.431);
        ((GeneralPath)shape).lineTo(442.904, 531.431);
        ((GeneralPath)shape).lineTo(442.904, 535.259);
        ((GeneralPath)shape).lineTo(443.537, 535.259);
        ((GeneralPath)shape).lineTo(443.537, 532.044);
        ((GeneralPath)shape).lineTo(444.58, 535.259);
        ((GeneralPath)shape).lineTo(445.166, 535.259);
        ((GeneralPath)shape).lineTo(446.209, 532.028);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_460;
        g.setTransform(defaultTransform__0_460);
        g.setClip(clip__0_460);
        float alpha__0_461 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_461 = g.getClip();
        AffineTransform defaultTransform__0_461 = g.getTransform();
        
//      _0_461 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(448.069, 533.384);
        ((GeneralPath)shape).lineTo(448.069, 532.05396);
        ((GeneralPath)shape).lineTo(448.919, 532.05396);
        ((GeneralPath)shape).curveTo(449.407, 532.05396, 449.476, 532.207, 449.476, 532.74896);
        ((GeneralPath)shape).curveTo(449.476, 533.28595, 449.385, 533.384, 448.919, 533.384);
        ((GeneralPath)shape).lineTo(448.069, 533.384);
        ((GeneralPath)shape).moveTo(447.437, 535.259);
        ((GeneralPath)shape).lineTo(448.07, 535.259);
        ((GeneralPath)shape).lineTo(448.07, 534.009);
        ((GeneralPath)shape).lineTo(448.92, 534.009);
        ((GeneralPath)shape).curveTo(449.793, 534.009, 450.109, 533.821, 450.109, 532.743);
        ((GeneralPath)shape).curveTo(450.109, 531.675, 449.814, 531.43, 448.918, 531.43);
        ((GeneralPath)shape).lineTo(447.438, 531.43);
        ((GeneralPath)shape).lineTo(447.438, 535.259);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_461;
        g.setTransform(defaultTransform__0_461);
        g.setClip(clip__0_461);
        float alpha__0_462 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_462 = g.getClip();
        AffineTransform defaultTransform__0_462 = g.getTransform();
        
//      _0_462 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(465.373, 531.523);
        ((GeneralPath)shape).lineTo(464.982, 531.523);
        ((GeneralPath)shape).lineTo(464.982, 535.022);
        ((GeneralPath)shape).lineTo(464.973, 535.022);
        ((GeneralPath)shape).lineTo(462.841, 531.523);
        ((GeneralPath)shape).lineTo(462.17, 531.523);
        ((GeneralPath)shape).lineTo(462.17, 535.352);
        ((GeneralPath)shape).lineTo(462.561, 535.352);
        ((GeneralPath)shape).lineTo(462.561, 531.877);
        ((GeneralPath)shape).lineTo(462.57, 531.877);
        ((GeneralPath)shape).lineTo(464.708, 535.352);
        ((GeneralPath)shape).lineTo(465.373, 535.352);
        ((GeneralPath)shape).lineTo(465.373, 531.523);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_462;
        g.setTransform(defaultTransform__0_462);
        g.setClip(clip__0_462);
        float alpha__0_463 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_463 = g.getClip();
        AffineTransform defaultTransform__0_463 = g.getTransform();
        
//      _0_463 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(466.397, 534.031);
        ((GeneralPath)shape).curveTo(466.397, 533.127, 466.449, 533.018, 467.178, 533.018);
        ((GeneralPath)shape).curveTo(467.90802, 533.018, 467.959, 533.126, 467.959, 534.031);
        ((GeneralPath)shape).curveTo(467.959, 534.929, 467.90802, 535.039, 467.178, 535.039);
        ((GeneralPath)shape).curveTo(466.449, 535.039, 466.397, 534.93, 466.397, 534.031);
        ((GeneralPath)shape).moveTo(466.007, 534.025);
        ((GeneralPath)shape).curveTo(466.007, 535.119, 466.19897, 535.351, 467.179, 535.351);
        ((GeneralPath)shape).curveTo(468.159, 535.351, 468.35098, 535.119, 468.35098, 534.025);
        ((GeneralPath)shape).curveTo(468.35098, 532.926, 468.15997, 532.695, 467.179, 532.695);
        ((GeneralPath)shape).curveTo(466.199, 532.695, 466.007, 532.927, 466.007, 534.025);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_463;
        g.setTransform(defaultTransform__0_463);
        g.setClip(clip__0_463);
        float alpha__0_464 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_464 = g.getClip();
        AffineTransform defaultTransform__0_464 = g.getTransform();
        
//      _0_464 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(474.4, 531.847);
        ((GeneralPath)shape).lineTo(474.41, 531.847);
        ((GeneralPath)shape).lineTo(474.41, 535.352);
        ((GeneralPath)shape).lineTo(474.801, 535.352);
        ((GeneralPath)shape).lineTo(474.801, 531.523);
        ((GeneralPath)shape).lineTo(474.093, 531.523);
        ((GeneralPath)shape).lineTo(472.729, 534.805);
        ((GeneralPath)shape).lineTo(471.368, 531.523);
        ((GeneralPath)shape).lineTo(470.66, 531.523);
        ((GeneralPath)shape).lineTo(470.66, 535.352);
        ((GeneralPath)shape).lineTo(471.051, 535.352);
        ((GeneralPath)shape).lineTo(471.051, 531.847);
        ((GeneralPath)shape).lineTo(471.061, 531.847);
        ((GeneralPath)shape).lineTo(472.519, 535.352);
        ((GeneralPath)shape).lineTo(472.949, 535.352);
        ((GeneralPath)shape).lineTo(474.4, 531.847);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_464;
        g.setTransform(defaultTransform__0_464);
        g.setClip(clip__0_464);
        float alpha__0_465 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_465 = g.getClip();
        AffineTransform defaultTransform__0_465 = g.getTransform();
        
//      _0_465 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(475.798, 534.031);
        ((GeneralPath)shape).curveTo(475.798, 533.127, 475.849, 533.018, 476.579, 533.018);
        ((GeneralPath)shape).curveTo(477.308, 533.018, 477.36002, 533.126, 477.36002, 534.031);
        ((GeneralPath)shape).curveTo(477.36002, 534.929, 477.308, 535.039, 476.579, 535.039);
        ((GeneralPath)shape).curveTo(475.849, 535.039, 475.798, 534.93, 475.798, 534.031);
        ((GeneralPath)shape).moveTo(475.407, 534.025);
        ((GeneralPath)shape).curveTo(475.407, 535.119, 475.59802, 535.351, 476.579, 535.351);
        ((GeneralPath)shape).curveTo(477.56, 535.351, 477.751, 535.119, 477.751, 534.025);
        ((GeneralPath)shape).curveTo(477.751, 532.926, 477.56, 532.695, 476.579, 532.695);
        ((GeneralPath)shape).curveTo(475.59802, 532.695, 475.407, 532.927, 475.407, 534.025);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_465;
        g.setTransform(defaultTransform__0_465);
        g.setClip(clip__0_465);
        float alpha__0_466 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_466 = g.getClip();
        AffineTransform defaultTransform__0_466 = g.getTransform();
        
//      _0_466 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(480.366, 532.695);
        ((GeneralPath)shape).lineTo(479.93, 532.695);
        ((GeneralPath)shape).lineTo(479.197, 535.047);
        ((GeneralPath)shape).lineTo(479.188, 535.047);
        ((GeneralPath)shape).lineTo(478.429, 532.695);
        ((GeneralPath)shape).lineTo(478.022, 532.695);
        ((GeneralPath)shape).lineTo(478.907, 535.352);
        ((GeneralPath)shape).lineTo(479.472, 535.352);
        ((GeneralPath)shape).lineTo(480.366, 532.695);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_466;
        g.setTransform(defaultTransform__0_466);
        g.setClip(clip__0_466);
        float alpha__0_467 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_467 = g.getClip();
        AffineTransform defaultTransform__0_467 = g.getTransform();
        
//      _0_467 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(480.912, 533.789);
        ((GeneralPath)shape).curveTo(480.912, 533.199, 480.939, 533.018, 481.72098, 533.018);
        ((GeneralPath)shape).curveTo(482.37598, 533.018, 482.47498, 533.062, 482.47498, 533.789);
        ((GeneralPath)shape).lineTo(480.912, 533.789);
        ((GeneralPath)shape).moveTo(482.475, 534.532);
        ((GeneralPath)shape).lineTo(482.475, 534.63196);
        ((GeneralPath)shape).curveTo(482.475, 535.0029, 482.29102, 535.03894, 481.723, 535.03894);
        ((GeneralPath)shape).curveTo(480.97598, 535.03894, 480.912, 534.93396, 480.912, 534.10095);
        ((GeneralPath)shape).lineTo(482.865, 534.10095);
        ((GeneralPath)shape).lineTo(482.865, 533.83997);
        ((GeneralPath)shape).curveTo(482.865, 532.886, 482.591, 532.694, 481.724, 532.694);
        ((GeneralPath)shape).curveTo(480.76498, 532.694, 480.521, 532.946, 480.521, 534.024);
        ((GeneralPath)shape).curveTo(480.521, 535.013, 480.679, 535.35, 481.724, 535.35);
        ((GeneralPath)shape).curveTo(482.32, 535.35, 482.865, 535.35, 482.865, 534.62897);
        ((GeneralPath)shape).lineTo(482.865, 534.52997);
        ((GeneralPath)shape).lineTo(482.475, 534.52997);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_467;
        g.setTransform(defaultTransform__0_467);
        g.setClip(clip__0_467);
        float alpha__0_468 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_468 = g.getClip();
        AffineTransform defaultTransform__0_468 = g.getTransform();
        
//      _0_468 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        stroke = new BasicStroke(1.0f,0,0,10.0f,null,0.0f);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(416.65, 535.279);
        ((GeneralPath)shape).curveTo(416.65, 536.072, 416.00598, 536.717, 415.212, 536.717);
        ((GeneralPath)shape).lineTo(411.337, 536.717);
        ((GeneralPath)shape).curveTo(410.543, 536.717, 409.89902, 536.07196, 409.89902, 535.279);
        ((GeneralPath)shape).lineTo(409.89902, 531.363);
        ((GeneralPath)shape).curveTo(409.89902, 530.56995, 410.544, 529.925, 411.337, 529.925);
        ((GeneralPath)shape).lineTo(415.212, 529.925);
        ((GeneralPath)shape).curveTo(416.006, 529.925, 416.65, 530.571, 416.65, 531.363);
        ((GeneralPath)shape).lineTo(416.65, 535.279);
        ((GeneralPath)shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_468;
        g.setTransform(defaultTransform__0_468);
        g.setClip(clip__0_468);
        float alpha__0_469 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_469 = g.getClip();
        AffineTransform defaultTransform__0_469 = g.getTransform();
        
//      _0_469 is ShapeNode
        paint = new Color(199, 200, 201, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(459.65, 535.279);
        ((GeneralPath)shape).curveTo(459.65, 536.072, 459.00598, 536.717, 458.212, 536.717);
        ((GeneralPath)shape).lineTo(454.337, 536.717);
        ((GeneralPath)shape).curveTo(453.543, 536.717, 452.89902, 536.07196, 452.89902, 535.279);
        ((GeneralPath)shape).lineTo(452.89902, 531.362);
        ((GeneralPath)shape).curveTo(452.89902, 530.57, 453.54303, 529.924, 454.337, 529.924);
        ((GeneralPath)shape).lineTo(458.212, 529.924);
        ((GeneralPath)shape).curveTo(459.006, 529.924, 459.65, 530.57, 459.65, 531.362);
        ((GeneralPath)shape).lineTo(459.65, 535.279);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_469;
        g.setTransform(defaultTransform__0_469);
        g.setClip(clip__0_469);
        float alpha__0_470 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_470 = g.getClip();
        AffineTransform defaultTransform__0_470 = g.getTransform();
        
//      _0_470 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        stroke = new BasicStroke(1.0f,0,0,10.0f,null,0.0f);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(459.65, 535.279);
        ((GeneralPath)shape).curveTo(459.65, 536.072, 459.005, 536.717, 458.212, 536.717);
        ((GeneralPath)shape).lineTo(454.336, 536.717);
        ((GeneralPath)shape).curveTo(453.543, 536.717, 452.898, 536.07196, 452.898, 535.279);
        ((GeneralPath)shape).lineTo(452.898, 531.363);
        ((GeneralPath)shape).curveTo(452.898, 530.56995, 453.543, 529.925, 454.336, 529.925);
        ((GeneralPath)shape).lineTo(458.212, 529.925);
        ((GeneralPath)shape).curveTo(459.005, 529.925, 459.65, 530.571, 459.65, 531.363);
        ((GeneralPath)shape).lineTo(459.65, 535.279);
        ((GeneralPath)shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_470;
        g.setTransform(defaultTransform__0_470);
        g.setClip(clip__0_470);
        float alpha__0_471 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_471 = g.getClip();
        AffineTransform defaultTransform__0_471 = g.getTransform();
        
//      _0_471 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(306.77, 546.137);
        ((GeneralPath)shape).curveTo(306.77, 547.424, 307.08798, 547.61005, 308.215, 547.61005);
        ((GeneralPath)shape).curveTo(309.301, 547.61005, 309.66, 547.39703, 309.66, 546.36707);
        ((GeneralPath)shape).curveTo(309.66, 545.3981, 309.324, 545.26605, 308.319, 545.26605);
        ((GeneralPath)shape).curveTo(307.96, 545.26605, 307.598, 545.3091, 307.482, 545.64703);
        ((GeneralPath)shape).lineTo(307.472, 545.64703);
        ((GeneralPath)shape).lineTo(307.472, 545.05206);
        ((GeneralPath)shape).curveTo(307.472, 544.4181, 307.576, 544.37805, 308.18997, 544.37805);
        ((GeneralPath)shape).curveTo(308.692, 544.37805, 308.87796, 544.37805, 308.87796, 544.85205);
        ((GeneralPath)shape).lineTo(309.58096, 544.85205);
        ((GeneralPath)shape).curveTo(309.58096, 543.82007, 309.02896, 543.78204, 308.17996, 543.78204);
        ((GeneralPath)shape).curveTo(307.19095, 543.78204, 306.76895, 543.98303, 306.76895, 545.043);
        ((GeneralPath)shape).lineTo(306.76895, 546.137);
        ((GeneralPath)shape).moveTo(308.215, 545.891);
        ((GeneralPath)shape).curveTo(308.813, 545.891, 308.957, 545.906, 308.957, 546.42);
        ((GeneralPath)shape).curveTo(308.957, 546.984, 308.834, 546.984, 308.215, 546.984);
        ((GeneralPath)shape).curveTo(307.552, 546.984, 307.473, 546.907, 307.473, 546.336);
        ((GeneralPath)shape).curveTo(307.473, 545.891, 307.68, 545.891, 308.215, 545.891);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_471;
        g.setTransform(defaultTransform__0_471);
        g.setClip(clip__0_471);
        float alpha__0_472 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_472 = g.getClip();
        AffineTransform defaultTransform__0_472 = g.getTransform();
        
//      _0_472 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(310.4, 548.234);
        ((GeneralPath)shape).lineTo(310.52798, 548.234);
        ((GeneralPath)shape).curveTo(311.07498, 548.234, 311.07498, 547.948, 311.07498, 547.517);
        ((GeneralPath)shape).lineTo(311.07498, 546.92505);
        ((GeneralPath)shape).lineTo(310.44998, 546.92505);
        ((GeneralPath)shape).lineTo(310.44998, 547.6091);
        ((GeneralPath)shape).lineTo(310.70697, 547.6091);
        ((GeneralPath)shape).curveTo(310.70697, 547.87006, 310.58997, 547.92206, 310.39996, 547.92206);
        ((GeneralPath)shape).lineTo(310.39996, 548.234);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_472;
        g.setTransform(defaultTransform__0_472);
        g.setClip(clip__0_472);
        float alpha__0_473 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_473 = g.getClip();
        AffineTransform defaultTransform__0_473 = g.getTransform();
        
//      _0_473 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(314.867, 543.781);
        ((GeneralPath)shape).lineTo(312.055, 543.781);
        ((GeneralPath)shape).lineTo(312.055, 544.403);
        ((GeneralPath)shape).lineTo(314.174, 544.403);
        ((GeneralPath)shape).lineTo(312.535, 547.609);
        ((GeneralPath)shape).lineTo(313.319, 547.609);
        ((GeneralPath)shape).lineTo(314.867, 544.48);
        ((GeneralPath)shape).lineTo(314.867, 543.781);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_473;
        g.setTransform(defaultTransform__0_473);
        g.setClip(clip__0_473);
        float alpha__0_474 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_474 = g.getClip();
        AffineTransform defaultTransform__0_474 = g.getTransform();
        
//      _0_474 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(315.686, 548.234);
        ((GeneralPath)shape).lineTo(315.814, 548.234);
        ((GeneralPath)shape).curveTo(316.361, 548.234, 316.361, 547.948, 316.361, 547.517);
        ((GeneralPath)shape).lineTo(316.361, 546.92505);
        ((GeneralPath)shape).lineTo(315.736, 546.92505);
        ((GeneralPath)shape).lineTo(315.736, 547.6091);
        ((GeneralPath)shape).lineTo(315.99298, 547.6091);
        ((GeneralPath)shape).curveTo(315.99298, 547.87006, 315.87598, 547.92206, 315.68597, 547.92206);
        ((GeneralPath)shape).lineTo(315.68597, 548.234);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_474;
        g.setTransform(defaultTransform__0_474);
        g.setClip(clip__0_474);
        float alpha__0_475 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_475 = g.getClip();
        AffineTransform defaultTransform__0_475 = g.getTransform();
        
//      _0_475 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(318.824, 545.422);
        ((GeneralPath)shape).curveTo(318.315, 545.422, 318.121, 545.422, 318.121, 544.807);
        ((GeneralPath)shape).curveTo(318.121, 544.411, 318.237, 544.378, 318.824, 544.378);
        ((GeneralPath)shape).curveTo(319.338, 544.378, 319.527, 544.378, 319.527, 544.809);
        ((GeneralPath)shape).curveTo(319.527, 545.447, 319.333, 545.422, 318.824, 545.422);
        ((GeneralPath)shape).moveTo(318.824, 547.609);
        ((GeneralPath)shape).curveTo(319.797, 547.609, 320.308, 547.526, 320.308, 546.493);
        ((GeneralPath)shape).curveTo(320.308, 546.037, 320.147, 545.756, 319.64502, 545.68695);
        ((GeneralPath)shape).lineTo(319.64502, 545.66595);
        ((GeneralPath)shape).curveTo(320.14902, 545.56793, 320.23, 545.285, 320.23, 544.808);
        ((GeneralPath)shape).curveTo(320.23, 543.73096, 319.50702, 543.781, 318.824, 543.781);
        ((GeneralPath)shape).curveTo(317.94202, 543.781, 317.418, 543.899, 317.418, 544.808);
        ((GeneralPath)shape).curveTo(317.418, 545.30096, 317.52, 545.55896, 318.02798, 545.66595);
        ((GeneralPath)shape).lineTo(318.02798, 545.68695);
        ((GeneralPath)shape).curveTo(317.45898, 545.75195, 317.34, 546.092, 317.34, 546.50696);
        ((GeneralPath)shape).curveTo(317.34, 547.609, 318.004, 547.609, 318.824, 547.609);
        ((GeneralPath)shape).moveTo(318.824, 545.969);
        ((GeneralPath)shape).curveTo(319.516, 545.969, 319.605, 546.02997, 319.605, 546.474);
        ((GeneralPath)shape).curveTo(319.605, 546.891, 319.51602, 546.985, 318.824, 546.985);
        ((GeneralPath)shape).curveTo(318.132, 546.985, 318.043, 546.914, 318.043, 546.474);
        ((GeneralPath)shape).curveTo(318.043, 546.058, 318.122, 545.969, 318.824, 545.969);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_475;
        g.setTransform(defaultTransform__0_475);
        g.setClip(clip__0_475);
        float alpha__0_476 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_476 = g.getClip();
        AffineTransform defaultTransform__0_476 = g.getTransform();
        
//      _0_476 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new Rectangle2D.Double(374.8559875488281, 546.2410278320312, 2.5, 0.39100000262260437);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_476;
        g.setTransform(defaultTransform__0_476);
        g.setClip(clip__0_476);
        float alpha__0_477 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_477 = g.getClip();
        AffineTransform defaultTransform__0_477 = g.getTransform();
        
//      _0_477 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(379.856, 543.976);
        ((GeneralPath)shape).lineTo(378.968, 543.976);
        ((GeneralPath)shape).lineTo(377.768, 545.124);
        ((GeneralPath)shape).lineTo(378.178, 545.58);
        ((GeneralPath)shape).lineTo(379.153, 544.582);
        ((GeneralPath)shape).lineTo(379.153, 547.804);
        ((GeneralPath)shape).lineTo(379.856, 547.804);
        ((GeneralPath)shape).lineTo(379.856, 543.976);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_477;
        g.setTransform(defaultTransform__0_477);
        g.setClip(clip__0_477);
        float alpha__0_478 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_478 = g.getClip();
        AffineTransform defaultTransform__0_478 = g.getTransform();
        
//      _0_478 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(384.517, 543.976);
        ((GeneralPath)shape).lineTo(384.517, 546.602);
        ((GeneralPath)shape).curveTo(384.517, 547.068, 384.454, 547.179, 384.042, 547.179);
        ((GeneralPath)shape).curveTo(383.501, 547.179, 383.501, 547.063, 383.501, 546.56604);
        ((GeneralPath)shape).lineTo(383.501, 546.28406);
        ((GeneralPath)shape).lineTo(382.798, 546.28406);
        ((GeneralPath)shape).lineTo(382.798, 546.83203);
        ((GeneralPath)shape).curveTo(382.798, 547.708, 383.278, 547.80505, 384.028, 547.80505);
        ((GeneralPath)shape).curveTo(384.872, 547.80505, 385.21902, 547.603, 385.21902, 546.60504);
        ((GeneralPath)shape).lineTo(385.21902, 543.97705);
        ((GeneralPath)shape).lineTo(384.517, 543.97705);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_478;
        g.setTransform(defaultTransform__0_478);
        g.setClip(clip__0_478);
        float alpha__0_479 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_479 = g.getClip();
        AffineTransform defaultTransform__0_479 = g.getTransform();
        
//      _0_479 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(388.213, 547.804);
        ((GeneralPath)shape).lineTo(388.213, 545.148);
        ((GeneralPath)shape).lineTo(387.588, 545.148);
        ((GeneralPath)shape).lineTo(387.588, 546.671);
        ((GeneralPath)shape).curveTo(387.588, 547.129, 387.431, 547.335, 386.94, 547.335);
        ((GeneralPath)shape).curveTo(386.53, 547.335, 386.495, 547.189, 386.495, 546.81305);
        ((GeneralPath)shape).lineTo(386.495, 545.1481);
        ((GeneralPath)shape).lineTo(385.87, 545.1481);
        ((GeneralPath)shape).lineTo(385.87, 547.0481);
        ((GeneralPath)shape).curveTo(385.87, 547.6231, 386.223, 547.8041, 386.753, 547.8041);
        ((GeneralPath)shape).curveTo(387.115, 547.8041, 387.442, 547.72107, 387.589, 547.3811);
        ((GeneralPath)shape).lineTo(387.589, 547.8041);
        ((GeneralPath)shape).lineTo(388.213, 547.8041);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_479;
        g.setTransform(defaultTransform__0_479);
        g.setClip(clip__0_479);
        float alpha__0_480 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_480 = g.getClip();
        AffineTransform defaultTransform__0_480 = g.getTransform();
        
//      _0_480 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(388.834, 545.147);
        ((GeneralPath)shape).lineTo(388.834, 547.803);
        ((GeneralPath)shape).lineTo(389.459, 547.803);
        ((GeneralPath)shape).lineTo(389.459, 546.326);
        ((GeneralPath)shape).curveTo(389.459, 545.86096, 389.565, 545.633, 390.104, 545.633);
        ((GeneralPath)shape).curveTo(390.469, 545.633, 390.553, 545.762, 390.553, 546.092);
        ((GeneralPath)shape).lineTo(390.553, 547.803);
        ((GeneralPath)shape).lineTo(391.178, 547.803);
        ((GeneralPath)shape).lineTo(391.178, 546.326);
        ((GeneralPath)shape).curveTo(391.178, 545.86096, 391.276, 545.633, 391.776, 545.633);
        ((GeneralPath)shape).curveTo(392.116, 545.633, 392.194, 545.762, 392.194, 546.092);
        ((GeneralPath)shape).lineTo(392.194, 547.803);
        ((GeneralPath)shape).lineTo(392.819, 547.803);
        ((GeneralPath)shape).lineTo(392.819, 546.027);
        ((GeneralPath)shape).curveTo(392.819, 545.38196, 392.599, 545.147, 391.934, 545.147);
        ((GeneralPath)shape).curveTo(391.59198, 545.147, 391.241, 545.24896, 391.123, 545.61694);
        ((GeneralPath)shape).lineTo(391.103, 545.61694);
        ((GeneralPath)shape).curveTo(391.031, 545.233, 390.641, 545.147, 390.303, 545.147);
        ((GeneralPath)shape).curveTo(389.96503, 545.147, 389.601, 545.22095, 389.458, 545.55695);
        ((GeneralPath)shape).lineTo(389.458, 545.147);
        ((GeneralPath)shape).lineTo(388.834, 545.147);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_480;
        g.setTransform(defaultTransform__0_480);
        g.setClip(clip__0_480);
        float alpha__0_481 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_481 = g.getClip();
        AffineTransform defaultTransform__0_481 = g.getTransform();
        
//      _0_481 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(394.079, 545.147);
        ((GeneralPath)shape).lineTo(393.454, 545.147);
        ((GeneralPath)shape).lineTo(393.454, 548.975);
        ((GeneralPath)shape).lineTo(394.079, 548.975);
        ((GeneralPath)shape).lineTo(394.079, 547.43195);
        ((GeneralPath)shape).lineTo(394.103, 547.43195);
        ((GeneralPath)shape).curveTo(394.236, 547.74493, 394.567, 547.8029, 394.883, 547.8029);
        ((GeneralPath)shape).curveTo(395.772, 547.8029, 395.875, 547.3139, 395.875, 546.55194);
        ((GeneralPath)shape).curveTo(395.875, 545.75397, 395.875, 545.1469, 394.898, 545.1469);
        ((GeneralPath)shape).curveTo(394.528, 545.1469, 394.238, 545.2269, 394.078, 545.57294);
        ((GeneralPath)shape).lineTo(394.078, 545.147);
        ((GeneralPath)shape).moveTo(394.699, 547.335);
        ((GeneralPath)shape).curveTo(394.163, 547.335, 394.079, 547.112, 394.079, 546.562);
        ((GeneralPath)shape).curveTo(394.079, 545.945, 394.079, 545.63403, 394.699, 545.63403);
        ((GeneralPath)shape).curveTo(395.251, 545.63403, 395.251, 545.97406, 395.251, 546.562);
        ((GeneralPath)shape).curveTo(395.251, 547.212, 395.128, 547.335, 394.699, 547.335);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_481;
        g.setTransform(defaultTransform__0_481);
        g.setClip(clip__0_481);
        float alpha__0_482 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_482 = g.getClip();
        AffineTransform defaultTransform__0_482 = g.getTransform();
        
//      _0_482 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(398.886, 544.841);
        ((GeneralPath)shape).lineTo(398.76, 544.456);
        ((GeneralPath)shape).lineTo(398.075, 544.704);
        ((GeneralPath)shape).lineTo(398.075, 543.976);
        ((GeneralPath)shape).lineTo(397.685, 543.976);
        ((GeneralPath)shape).lineTo(397.685, 544.704);
        ((GeneralPath)shape).lineTo(397.004, 544.456);
        ((GeneralPath)shape).lineTo(396.874, 544.815);
        ((GeneralPath)shape).lineTo(397.555, 545.074);
        ((GeneralPath)shape).lineTo(397.134, 545.66);
        ((GeneralPath)shape).lineTo(397.434, 545.889);
        ((GeneralPath)shape).lineTo(397.893, 545.281);
        ((GeneralPath)shape).lineTo(398.315, 545.889);
        ((GeneralPath)shape).lineTo(398.609, 545.66);
        ((GeneralPath)shape).lineTo(398.204, 545.074);
        ((GeneralPath)shape).lineTo(398.886, 544.841);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_482;
        g.setTransform(defaultTransform__0_482);
        g.setClip(clip__0_482);
        float alpha__0_483 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_483 = g.getClip();
        AffineTransform defaultTransform__0_483 = g.getTransform();
        
//      _0_483 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(329.653, 544.403);
        ((GeneralPath)shape).lineTo(330.754, 544.403);
        ((GeneralPath)shape).lineTo(330.754, 543.781);
        ((GeneralPath)shape).lineTo(327.851, 543.781);
        ((GeneralPath)shape).lineTo(327.851, 544.403);
        ((GeneralPath)shape).lineTo(328.95, 544.403);
        ((GeneralPath)shape).lineTo(328.95, 547.609);
        ((GeneralPath)shape).lineTo(329.653, 547.609);
        ((GeneralPath)shape).lineTo(329.653, 544.403);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_483;
        g.setTransform(defaultTransform__0_483);
        g.setClip(clip__0_483);
        float alpha__0_484 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_484 = g.getClip();
        AffineTransform defaultTransform__0_484 = g.getTransform();
        
//      _0_484 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(331.812, 545.438);
        ((GeneralPath)shape).curveTo(332.354, 545.438, 332.398, 545.596, 332.398, 546.29596);
        ((GeneralPath)shape).curveTo(332.398, 546.98694, 332.354, 547.13995, 331.812, 547.13995);
        ((GeneralPath)shape).curveTo(331.27002, 547.13995, 331.226, 546.988, 331.226, 546.29596);
        ((GeneralPath)shape).curveTo(331.226, 545.597, 331.27, 545.438, 331.812, 545.438);
        ((GeneralPath)shape).moveTo(331.812, 544.953);
        ((GeneralPath)shape).curveTo(330.738, 544.953, 330.601, 545.274, 330.601, 546.283);
        ((GeneralPath)shape).curveTo(330.601, 547.288, 330.738, 547.609, 331.812, 547.609);
        ((GeneralPath)shape).curveTo(332.88602, 547.609, 333.023, 547.288, 333.023, 546.283);
        ((GeneralPath)shape).curveTo(333.022, 545.274, 332.886, 544.953, 331.812, 544.953);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_484;
        g.setTransform(defaultTransform__0_484);
        g.setClip(clip__0_484);
        float alpha__0_485 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_485 = g.getClip();
        AffineTransform defaultTransform__0_485 = g.getTransform();
        
//      _0_485 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(333.511, 544.953);
        ((GeneralPath)shape).lineTo(333.511, 547.609);
        ((GeneralPath)shape).lineTo(334.136, 547.609);
        ((GeneralPath)shape).lineTo(334.136, 545.993);
        ((GeneralPath)shape).curveTo(334.136, 545.65295, 334.253, 545.438, 334.654, 545.438);
        ((GeneralPath)shape).curveTo(334.969, 545.438, 334.996, 545.593, 334.996, 545.857);
        ((GeneralPath)shape).lineTo(334.996, 545.993);
        ((GeneralPath)shape).lineTo(335.621, 545.993);
        ((GeneralPath)shape).lineTo(335.621, 545.782);
        ((GeneralPath)shape).curveTo(335.621, 545.285, 335.477, 544.953, 334.884, 544.953);
        ((GeneralPath)shape).curveTo(334.56, 544.953, 334.27, 545.037, 334.137, 545.325);
        ((GeneralPath)shape).lineTo(334.137, 544.953);
        ((GeneralPath)shape).lineTo(333.511, 544.953);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_485;
        g.setTransform(defaultTransform__0_485);
        g.setClip(clip__0_485);
        float alpha__0_486 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_486 = g.getClip();
        AffineTransform defaultTransform__0_486 = g.getTransform();
        
//      _0_486 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(338.132, 545.691);
        ((GeneralPath)shape).curveTo(338.132, 544.953, 337.53198, 544.953, 337.03, 544.953);
        ((GeneralPath)shape).curveTo(336.372, 544.953, 335.866, 544.953, 335.866, 545.662);
        ((GeneralPath)shape).curveTo(335.866, 546.321, 336.035, 546.449, 336.971, 546.464);
        ((GeneralPath)shape).curveTo(337.57, 546.474, 337.584, 546.583, 337.584, 546.8);
        ((GeneralPath)shape).curveTo(337.584, 547.141, 337.385, 547.141, 337.02402, 547.141);
        ((GeneralPath)shape).curveTo(336.57602, 547.141, 336.49002, 547.09796, 336.49002, 546.733);
        ((GeneralPath)shape).lineTo(335.86502, 546.733);
        ((GeneralPath)shape).curveTo(335.86502, 547.61, 336.33002, 547.61, 337.02203, 547.61);
        ((GeneralPath)shape).curveTo(337.66702, 547.61, 338.20905, 547.541, 338.20905, 546.834);
        ((GeneralPath)shape).curveTo(338.20905, 545.988, 337.66504, 546.03296, 337.07706, 546.00397);
        ((GeneralPath)shape).curveTo(336.55606, 545.98, 336.49005, 545.97, 336.49005, 545.72095);
        ((GeneralPath)shape).curveTo(336.49005, 545.38995, 336.71005, 545.38995, 337.02805, 545.38995);
        ((GeneralPath)shape).curveTo(337.34705, 545.38995, 337.50604, 545.38995, 337.50604, 545.69293);
        ((GeneralPath)shape).lineTo(338.132, 545.69293);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_486;
        g.setTransform(defaultTransform__0_486);
        g.setClip(clip__0_486);
        float alpha__0_487 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_487 = g.getClip();
        AffineTransform defaultTransform__0_487 = g.getTransform();
        
//      _0_487 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(339.862, 545.438);
        ((GeneralPath)shape).curveTo(340.404, 545.438, 340.448, 545.596, 340.448, 546.29596);
        ((GeneralPath)shape).curveTo(340.448, 546.98694, 340.404, 547.13995, 339.862, 547.13995);
        ((GeneralPath)shape).curveTo(339.32, 547.13995, 339.276, 546.988, 339.276, 546.29596);
        ((GeneralPath)shape).curveTo(339.276, 545.597, 339.32, 545.438, 339.862, 545.438);
        ((GeneralPath)shape).moveTo(339.862, 544.953);
        ((GeneralPath)shape).curveTo(338.788, 544.953, 338.651, 545.274, 338.651, 546.283);
        ((GeneralPath)shape).curveTo(338.651, 547.288, 338.788, 547.609, 339.862, 547.609);
        ((GeneralPath)shape).curveTo(340.936, 547.609, 341.073, 547.288, 341.073, 546.283);
        ((GeneralPath)shape).curveTo(341.073, 545.274, 340.937, 544.953, 339.862, 544.953);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_487;
        g.setTransform(defaultTransform__0_487);
        g.setClip(clip__0_487);
        float alpha__0_488 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_488 = g.getClip();
        AffineTransform defaultTransform__0_488 = g.getTransform();
        
//      _0_488 is ShapeNode
        paint = new Color(199, 200, 201, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(372.65, 547.827);
        ((GeneralPath)shape).curveTo(372.65, 548.62305, 372.00598, 549.265, 371.212, 549.265);
        ((GeneralPath)shape).lineTo(367.337, 549.265);
        ((GeneralPath)shape).curveTo(366.543, 549.265, 365.89902, 548.622, 365.89902, 547.827);
        ((GeneralPath)shape).lineTo(365.89902, 543.913);
        ((GeneralPath)shape).curveTo(365.89902, 543.119, 366.54303, 542.47504, 367.337, 542.47504);
        ((GeneralPath)shape).lineTo(371.212, 542.47504);
        ((GeneralPath)shape).curveTo(372.006, 542.47504, 372.65, 543.119, 372.65, 543.913);
        ((GeneralPath)shape).lineTo(372.65, 547.827);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_488;
        g.setTransform(defaultTransform__0_488);
        g.setClip(clip__0_488);
        float alpha__0_489 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_489 = g.getClip();
        AffineTransform defaultTransform__0_489 = g.getTransform();
        
//      _0_489 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        stroke = new BasicStroke(1.0f,0,0,10.0f,null,0.0f);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(372.65, 547.827);
        ((GeneralPath)shape).curveTo(372.65, 548.62305, 372.005, 549.265, 371.212, 549.265);
        ((GeneralPath)shape).lineTo(367.336, 549.265);
        ((GeneralPath)shape).curveTo(366.543, 549.265, 365.898, 548.622, 365.898, 547.827);
        ((GeneralPath)shape).lineTo(365.898, 543.913);
        ((GeneralPath)shape).curveTo(365.898, 543.119, 366.543, 542.47504, 367.336, 542.47504);
        ((GeneralPath)shape).lineTo(371.212, 542.47504);
        ((GeneralPath)shape).curveTo(372.005, 542.47504, 372.65, 543.119, 372.65, 543.913);
        ((GeneralPath)shape).lineTo(372.65, 547.827);
        ((GeneralPath)shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_489;
        g.setTransform(defaultTransform__0_489);
        g.setClip(clip__0_489);
        float alpha__0_490 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_490 = g.getClip();
        AffineTransform defaultTransform__0_490 = g.getTransform();
        
//      _0_490 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(421.046, 543.978);
        ((GeneralPath)shape).lineTo(420.465, 543.978);
        ((GeneralPath)shape).lineTo(419.43, 545.124);
        ((GeneralPath)shape).lineTo(419.696, 545.384);
        ((GeneralPath)shape).lineTo(420.655, 544.307);
        ((GeneralPath)shape).lineTo(420.655, 547.806);
        ((GeneralPath)shape).lineTo(421.046, 547.806);
        ((GeneralPath)shape).lineTo(421.046, 543.978);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_490;
        g.setTransform(defaultTransform__0_490);
        g.setClip(clip__0_490);
        float alpha__0_491 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_491 = g.getClip();
        AffineTransform defaultTransform__0_491 = g.getTransform();
        
//      _0_491 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(425.824, 543.978);
        ((GeneralPath)shape).lineTo(425.359, 543.978);
        ((GeneralPath)shape).lineTo(422.465, 548.346);
        ((GeneralPath)shape).lineTo(422.92, 548.346);
        ((GeneralPath)shape).lineTo(425.824, 543.978);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_491;
        g.setTransform(defaultTransform__0_491);
        g.setClip(clip__0_491);
        float alpha__0_492 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_492 = g.getClip();
        AffineTransform defaultTransform__0_492 = g.getTransform();
        
//      _0_492 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(428.991, 547.427);
        ((GeneralPath)shape).lineTo(426.725, 547.427);
        ((GeneralPath)shape).lineTo(426.725, 547.103);
        ((GeneralPath)shape).curveTo(426.725, 546.54504, 426.92102, 546.46, 427.723, 546.39404);
        ((GeneralPath)shape).curveTo(428.647, 546.31903, 428.991, 546.1871, 428.991, 545.17804);
        ((GeneralPath)shape).curveTo(428.991, 544.21606, 428.686, 543.978, 427.712, 543.978);
        ((GeneralPath)shape).curveTo(426.43802, 543.978, 426.335, 544.283, 426.335, 545.11304);
        ((GeneralPath)shape).lineTo(426.335, 545.31006);
        ((GeneralPath)shape).lineTo(426.72598, 545.31006);
        ((GeneralPath)shape).lineTo(426.72598, 545.11304);
        ((GeneralPath)shape).curveTo(426.72598, 544.41504, 426.82098, 544.35803, 427.71997, 544.35803);
        ((GeneralPath)shape).curveTo(428.43097, 544.35803, 428.60098, 544.393, 428.60098, 545.17804);
        ((GeneralPath)shape).curveTo(428.60098, 545.968, 428.42697, 545.93304, 427.63898, 546.01306);
        ((GeneralPath)shape).curveTo(426.74597, 546.11005, 426.335, 546.17505, 426.335, 547.1631);
        ((GeneralPath)shape).lineTo(426.335, 547.8061);
        ((GeneralPath)shape).lineTo(428.991, 547.8061);
        ((GeneralPath)shape).lineTo(428.991, 547.427);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_492;
        g.setTransform(defaultTransform__0_492);
        g.setClip(clip__0_492);
        float alpha__0_493 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_493 = g.getClip();
        AffineTransform defaultTransform__0_493 = g.getTransform();
        
//      _0_493 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(433.028, 543.978);
        ((GeneralPath)shape).lineTo(433.028, 546.604);
        ((GeneralPath)shape).curveTo(433.028, 547.071, 432.966, 547.181, 432.553, 547.181);
        ((GeneralPath)shape).curveTo(432.01202, 547.181, 432.01202, 547.06604, 432.01202, 546.56903);
        ((GeneralPath)shape).lineTo(432.01202, 546.286);
        ((GeneralPath)shape).lineTo(431.30902, 546.286);
        ((GeneralPath)shape).lineTo(431.30902, 546.834);
        ((GeneralPath)shape).curveTo(431.30902, 547.711, 431.79, 547.807, 432.53903, 547.807);
        ((GeneralPath)shape).curveTo(433.38303, 547.807, 433.73004, 547.605, 433.73004, 546.60803);
        ((GeneralPath)shape).lineTo(433.73004, 543.979);
        ((GeneralPath)shape).lineTo(433.028, 543.979);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_493;
        g.setTransform(defaultTransform__0_493);
        g.setClip(clip__0_493);
        float alpha__0_494 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_494 = g.getClip();
        AffineTransform defaultTransform__0_494 = g.getTransform();
        
//      _0_494 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(436.725, 547.806);
        ((GeneralPath)shape).lineTo(436.725, 545.15);
        ((GeneralPath)shape).lineTo(436.1, 545.15);
        ((GeneralPath)shape).lineTo(436.1, 546.67303);
        ((GeneralPath)shape).curveTo(436.1, 547.13104, 435.943, 547.33704, 435.453, 547.33704);
        ((GeneralPath)shape).curveTo(435.043, 547.33704, 435.007, 547.192, 435.007, 546.81506);
        ((GeneralPath)shape).lineTo(435.007, 545.1501);
        ((GeneralPath)shape).lineTo(434.382, 545.1501);
        ((GeneralPath)shape).lineTo(434.382, 547.0511);
        ((GeneralPath)shape).curveTo(434.382, 547.62506, 434.736, 547.8061, 435.26498, 547.8061);
        ((GeneralPath)shape).curveTo(435.62698, 547.8061, 435.95398, 547.7231, 436.10098, 547.3841);
        ((GeneralPath)shape).lineTo(436.10098, 547.8061);
        ((GeneralPath)shape).lineTo(436.725, 547.8061);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_494;
        g.setTransform(defaultTransform__0_494);
        g.setClip(clip__0_494);
        float alpha__0_495 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_495 = g.getClip();
        AffineTransform defaultTransform__0_495 = g.getTransform();
        
//      _0_495 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(437.346, 545.149);
        ((GeneralPath)shape).lineTo(437.346, 547.805);
        ((GeneralPath)shape).lineTo(437.971, 547.805);
        ((GeneralPath)shape).lineTo(437.971, 546.328);
        ((GeneralPath)shape).curveTo(437.971, 545.864, 438.077, 545.635, 438.616, 545.635);
        ((GeneralPath)shape).curveTo(438.981, 545.635, 439.065, 545.76404, 439.065, 546.094);
        ((GeneralPath)shape).lineTo(439.065, 547.805);
        ((GeneralPath)shape).lineTo(439.69, 547.805);
        ((GeneralPath)shape).lineTo(439.69, 546.328);
        ((GeneralPath)shape).curveTo(439.69, 545.864, 439.788, 545.635, 440.289, 545.635);
        ((GeneralPath)shape).curveTo(440.628, 545.635, 440.706, 545.76404, 440.706, 546.094);
        ((GeneralPath)shape).lineTo(440.706, 547.805);
        ((GeneralPath)shape).lineTo(441.331, 547.805);
        ((GeneralPath)shape).lineTo(441.331, 546.02997);
        ((GeneralPath)shape).curveTo(441.331, 545.38495, 441.111, 545.149, 440.44598, 545.149);
        ((GeneralPath)shape).curveTo(440.10397, 545.149, 439.753, 545.251, 439.63498, 545.62);
        ((GeneralPath)shape).lineTo(439.615, 545.62);
        ((GeneralPath)shape).curveTo(439.543, 545.235, 439.154, 545.149, 438.815, 545.149);
        ((GeneralPath)shape).curveTo(438.477, 545.149, 438.114, 545.224, 437.97, 545.55896);
        ((GeneralPath)shape).lineTo(437.97, 545.149);
        ((GeneralPath)shape).lineTo(437.346, 545.149);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_495;
        g.setTransform(defaultTransform__0_495);
        g.setClip(clip__0_495);
        float alpha__0_496 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_496 = g.getClip();
        AffineTransform defaultTransform__0_496 = g.getTransform();
        
//      _0_496 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(442.591, 545.149);
        ((GeneralPath)shape).lineTo(441.966, 545.149);
        ((GeneralPath)shape).lineTo(441.966, 548.977);
        ((GeneralPath)shape).lineTo(442.591, 548.977);
        ((GeneralPath)shape).lineTo(442.591, 547.43396);
        ((GeneralPath)shape).lineTo(442.615, 547.43396);
        ((GeneralPath)shape).curveTo(442.748, 547.74695, 443.07898, 547.80493, 443.395, 547.80493);
        ((GeneralPath)shape).curveTo(444.284, 547.80493, 444.387, 547.3159, 444.387, 546.55396);
        ((GeneralPath)shape).curveTo(444.387, 545.75696, 444.387, 545.1489, 443.41, 545.1489);
        ((GeneralPath)shape).curveTo(443.04, 545.1489, 442.75, 545.2299, 442.59, 545.5759);
        ((GeneralPath)shape).lineTo(442.59, 545.149);
        ((GeneralPath)shape).moveTo(443.211, 547.337);
        ((GeneralPath)shape).curveTo(442.675, 547.337, 442.591, 547.115, 442.591, 546.56396);
        ((GeneralPath)shape).curveTo(442.591, 545.94794, 442.591, 545.636, 443.211, 545.636);
        ((GeneralPath)shape).curveTo(443.763, 545.636, 443.763, 545.977, 443.763, 546.56396);
        ((GeneralPath)shape).curveTo(443.763, 547.214, 443.64, 547.337, 443.211, 547.337);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_496;
        g.setTransform(defaultTransform__0_496);
        g.setClip(clip__0_496);
        float alpha__0_497 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_497 = g.getClip();
        AffineTransform defaultTransform__0_497 = g.getTransform();
        
//      _0_497 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(447.397, 544.844);
        ((GeneralPath)shape).lineTo(447.271, 544.459);
        ((GeneralPath)shape).lineTo(446.587, 544.707);
        ((GeneralPath)shape).lineTo(446.587, 543.978);
        ((GeneralPath)shape).lineTo(446.196, 543.978);
        ((GeneralPath)shape).lineTo(446.196, 544.707);
        ((GeneralPath)shape).lineTo(445.517, 544.459);
        ((GeneralPath)shape).lineTo(445.386, 544.817);
        ((GeneralPath)shape).lineTo(446.067, 545.076);
        ((GeneralPath)shape).lineTo(445.646, 545.662);
        ((GeneralPath)shape).lineTo(445.946, 545.891);
        ((GeneralPath)shape).lineTo(446.405, 545.284);
        ((GeneralPath)shape).lineTo(446.827, 545.891);
        ((GeneralPath)shape).lineTo(447.122, 545.662);
        ((GeneralPath)shape).lineTo(446.717, 545.076);
        ((GeneralPath)shape).lineTo(447.397, 544.844);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_497;
        g.setTransform(defaultTransform__0_497);
        g.setClip(clip__0_497);
        float alpha__0_498 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_498 = g.getClip();
        AffineTransform defaultTransform__0_498 = g.getTransform();
        
//      _0_498 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(463.86, 544.447);
        ((GeneralPath)shape).curveTo(464.38498, 544.447, 464.51398, 544.544, 464.51398, 545.27704);
        ((GeneralPath)shape).curveTo(464.51398, 546.02106, 464.22498, 546.02106, 463.65997, 546.02106);
        ((GeneralPath)shape).lineTo(462.56097, 546.02106);
        ((GeneralPath)shape).lineTo(462.56097, 544.4471);
        ((GeneralPath)shape).lineTo(463.86, 544.4471);
        ((GeneralPath)shape).moveTo(462.171, 547.896);
        ((GeneralPath)shape).lineTo(462.56198, 547.896);
        ((GeneralPath)shape).lineTo(462.56198, 546.412);
        ((GeneralPath)shape).lineTo(464.02597, 546.412);
        ((GeneralPath)shape).curveTo(464.90598, 546.412, 464.90598, 545.57196, 464.90598, 545.23);
        ((GeneralPath)shape).curveTo(464.90598, 544.569, 464.74, 544.068, 464.03397, 544.068);
        ((GeneralPath)shape).lineTo(462.17197, 544.068);
        ((GeneralPath)shape).lineTo(462.17197, 547.896);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_498;
        g.setTransform(defaultTransform__0_498);
        g.setClip(clip__0_498);
        float alpha__0_499 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_499 = g.getClip();
        AffineTransform defaultTransform__0_499 = g.getTransform();
        
//      _0_499 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(465.477, 545.24);
        ((GeneralPath)shape).lineTo(465.477, 547.896);
        ((GeneralPath)shape).lineTo(465.86798, 547.896);
        ((GeneralPath)shape).lineTo(465.86798, 546.262);
        ((GeneralPath)shape).curveTo(465.86798, 545.797, 466.029, 545.56305, 466.51898, 545.56305);
        ((GeneralPath)shape).curveTo(466.85098, 545.56305, 466.96097, 545.67206, 466.96097, 545.99805);
        ((GeneralPath)shape).lineTo(466.96097, 546.17303);
        ((GeneralPath)shape).lineTo(467.35196, 546.17303);
        ((GeneralPath)shape).lineTo(467.35196, 545.98303);
        ((GeneralPath)shape).curveTo(467.35196, 545.491, 467.12595, 545.241, 466.61697, 545.241);
        ((GeneralPath)shape).curveTo(466.32098, 545.241, 466.00397, 545.31604, 465.86798, 545.609);
        ((GeneralPath)shape).lineTo(465.86798, 545.241);
        ((GeneralPath)shape).lineTo(465.477, 545.241);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_499;
        g.setTransform(defaultTransform__0_499);
        g.setClip(clip__0_499);
        float alpha__0_500 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_500 = g.getClip();
        AffineTransform defaultTransform__0_500 = g.getTransform();
        
//      _0_500 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(467.999, 546.576);
        ((GeneralPath)shape).curveTo(467.999, 545.672, 468.05, 545.563, 468.78, 545.563);
        ((GeneralPath)shape).curveTo(469.509, 545.563, 469.561, 545.67096, 469.561, 546.576);
        ((GeneralPath)shape).curveTo(469.561, 547.474, 469.509, 547.584, 468.78, 547.584);
        ((GeneralPath)shape).curveTo(468.05, 547.584, 467.999, 547.475, 467.999, 546.576);
        ((GeneralPath)shape).moveTo(467.608, 546.57);
        ((GeneralPath)shape).curveTo(467.608, 547.664, 467.799, 547.896, 468.78, 547.896);
        ((GeneralPath)shape).curveTo(469.759, 547.896, 469.952, 547.664, 469.952, 546.57);
        ((GeneralPath)shape).curveTo(469.952, 545.471, 469.76, 545.24, 468.78, 545.24);
        ((GeneralPath)shape).curveTo(467.8, 545.24, 467.608, 545.472, 467.608, 546.57);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_500;
        g.setTransform(defaultTransform__0_500);
        g.setClip(clip__0_500);
        float alpha__0_501 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_501 = g.getClip();
        AffineTransform defaultTransform__0_501 = g.getTransform();
        
//      _0_501 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(471.995, 545.24);
        ((GeneralPath)shape).lineTo(470.927, 545.24);
        ((GeneralPath)shape).lineTo(470.927, 544.597);
        ((GeneralPath)shape).lineTo(470.536, 544.597);
        ((GeneralPath)shape).lineTo(470.536, 545.24);
        ((GeneralPath)shape).lineTo(470.185, 545.24);
        ((GeneralPath)shape).lineTo(470.185, 545.563);
        ((GeneralPath)shape).lineTo(470.536, 545.563);
        ((GeneralPath)shape).lineTo(470.536, 547.112);
        ((GeneralPath)shape).curveTo(470.536, 547.722, 470.682, 547.896, 471.31702, 547.896);
        ((GeneralPath)shape).curveTo(471.876, 547.896, 472.09802, 547.652, 472.09802, 547.135);
        ((GeneralPath)shape).lineTo(472.09802, 546.94104);
        ((GeneralPath)shape).lineTo(471.70703, 546.94104);
        ((GeneralPath)shape).lineTo(471.70703, 547.137);
        ((GeneralPath)shape).curveTo(471.70703, 547.388, 471.70703, 547.583, 471.31403, 547.583);
        ((GeneralPath)shape).curveTo(471.00403, 547.583, 470.92502, 547.518, 470.92502, 547.232);
        ((GeneralPath)shape).lineTo(470.92502, 545.562);
        ((GeneralPath)shape).lineTo(471.993, 545.562);
        ((GeneralPath)shape).lineTo(471.993, 545.24);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_501;
        g.setTransform(defaultTransform__0_501);
        g.setClip(clip__0_501);
        float alpha__0_502 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_502 = g.getClip();
        AffineTransform defaultTransform__0_502 = g.getTransform();
        
//      _0_502 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(472.679, 546.576);
        ((GeneralPath)shape).curveTo(472.679, 545.672, 472.731, 545.563, 473.46, 545.563);
        ((GeneralPath)shape).curveTo(474.189, 545.563, 474.241, 545.67096, 474.241, 546.576);
        ((GeneralPath)shape).curveTo(474.241, 547.474, 474.189, 547.584, 473.46, 547.584);
        ((GeneralPath)shape).curveTo(472.731, 547.584, 472.679, 547.475, 472.679, 546.576);
        ((GeneralPath)shape).moveTo(472.288, 546.57);
        ((GeneralPath)shape).curveTo(472.288, 547.664, 472.479, 547.896, 473.46, 547.896);
        ((GeneralPath)shape).curveTo(474.44098, 547.896, 474.632, 547.664, 474.632, 546.57);
        ((GeneralPath)shape).curveTo(474.632, 545.471, 474.44098, 545.24, 473.46, 545.24);
        ((GeneralPath)shape).curveTo(472.479, 545.24, 472.288, 545.472, 472.288, 546.57);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_502;
        g.setTransform(defaultTransform__0_502);
        g.setClip(clip__0_502);
        float alpha__0_503 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_503 = g.getClip();
        AffineTransform defaultTransform__0_503 = g.getTransform();
        
//      _0_503 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(462.562, 553.517);
        ((GeneralPath)shape).lineTo(462.562, 550.44806);
        ((GeneralPath)shape).lineTo(464.036, 550.44806);
        ((GeneralPath)shape).curveTo(464.75302, 550.44806, 464.828, 551.15906, 464.828, 551.3741);
        ((GeneralPath)shape).lineTo(464.828, 552.3821);
        ((GeneralPath)shape).curveTo(464.828, 553.06805, 464.728, 553.51807, 463.973, 553.51807);
        ((GeneralPath)shape).lineTo(462.562, 553.51807);
        ((GeneralPath)shape).moveTo(462.171, 553.896);
        ((GeneralPath)shape).lineTo(464.05798, 553.896);
        ((GeneralPath)shape).curveTo(465.15997, 553.896, 465.218, 552.833, 465.218, 552.457);
        ((GeneralPath)shape).lineTo(465.218, 551.54297);
        ((GeneralPath)shape).curveTo(465.218, 550.12897, 464.405, 550.068, 463.96, 550.068);
        ((GeneralPath)shape).lineTo(462.171, 550.068);
        ((GeneralPath)shape).lineTo(462.171, 553.896);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_503;
        g.setTransform(defaultTransform__0_503);
        g.setClip(clip__0_503);
        float alpha__0_504 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_504 = g.getClip();
        AffineTransform defaultTransform__0_504 = g.getTransform();
        
//      _0_504 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(466.153, 552.334);
        ((GeneralPath)shape).curveTo(466.153, 551.74396, 466.18002, 551.563, 466.962, 551.563);
        ((GeneralPath)shape).curveTo(467.617, 551.563, 467.716, 551.607, 467.716, 552.334);
        ((GeneralPath)shape).lineTo(466.153, 552.334);
        ((GeneralPath)shape).moveTo(467.716, 553.077);
        ((GeneralPath)shape).lineTo(467.716, 553.177);
        ((GeneralPath)shape).curveTo(467.716, 553.548, 467.532, 553.584, 466.964, 553.584);
        ((GeneralPath)shape).curveTo(466.21698, 553.584, 466.15298, 553.479, 466.15298, 552.646);
        ((GeneralPath)shape).lineTo(468.106, 552.646);
        ((GeneralPath)shape).lineTo(468.106, 552.385);
        ((GeneralPath)shape).curveTo(468.106, 551.431, 467.832, 551.239, 466.965, 551.239);
        ((GeneralPath)shape).curveTo(466.00598, 551.239, 465.762, 551.491, 465.762, 552.56903);
        ((GeneralPath)shape).curveTo(465.762, 553.55804, 465.91998, 553.895, 466.965, 553.895);
        ((GeneralPath)shape).curveTo(467.561, 553.895, 468.106, 553.895, 468.106, 553.174);
        ((GeneralPath)shape).lineTo(468.106, 553.075);
        ((GeneralPath)shape).lineTo(467.716, 553.075);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_504;
        g.setTransform(defaultTransform__0_504);
        g.setClip(clip__0_504);
        float alpha__0_505 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_505 = g.getClip();
        AffineTransform defaultTransform__0_505 = g.getTransform();
        
//      _0_505 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(470.775, 551.838);
        ((GeneralPath)shape).curveTo(470.775, 551.24, 470.205, 551.24, 469.74298, 551.24);
        ((GeneralPath)shape).curveTo(469.02597, 551.24, 468.58798, 551.24, 468.58798, 551.993);
        ((GeneralPath)shape).curveTo(468.58798, 552.732, 469.16898, 552.673, 469.744, 552.712);
        ((GeneralPath)shape).curveTo(470.248, 552.742, 470.46298, 552.651, 470.46298, 553.15594);
        ((GeneralPath)shape).curveTo(470.46298, 553.63696, 470.02698, 553.5839, 469.744, 553.5839);
        ((GeneralPath)shape).curveTo(469.137, 553.5839, 468.978, 553.52594, 468.978, 553.26495);
        ((GeneralPath)shape).lineTo(468.978, 553.05194);
        ((GeneralPath)shape).lineTo(468.587, 553.05194);
        ((GeneralPath)shape).lineTo(468.587, 553.30096);
        ((GeneralPath)shape).curveTo(468.587, 553.897, 469.185, 553.897, 469.755, 553.897);
        ((GeneralPath)shape).curveTo(470.341, 553.897, 470.853, 553.897, 470.853, 553.13495);
        ((GeneralPath)shape).curveTo(470.853, 552.25696, 470.228, 552.36395, 469.54, 552.32495);
        ((GeneralPath)shape).curveTo(469.113, 552.30597, 468.97702, 552.33997, 468.97702, 551.8989);
        ((GeneralPath)shape).curveTo(468.97702, 551.5929, 469.22702, 551.5929, 469.743, 551.5929);
        ((GeneralPath)shape).curveTo(470.17502, 551.5929, 470.384, 551.5929, 470.384, 551.8429);
        ((GeneralPath)shape).lineTo(470.384, 551.93787);
        ((GeneralPath)shape).lineTo(470.775, 551.93787);
        ((GeneralPath)shape).lineTo(470.775, 551.838);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_505;
        g.setTransform(defaultTransform__0_505);
        g.setClip(clip__0_505);
        float alpha__0_506 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_506 = g.getClip();
        AffineTransform defaultTransform__0_506 = g.getTransform();
        
//      _0_506 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(472.811, 551.24);
        ((GeneralPath)shape).lineTo(471.743, 551.24);
        ((GeneralPath)shape).lineTo(471.743, 550.597);
        ((GeneralPath)shape).lineTo(471.35202, 550.597);
        ((GeneralPath)shape).lineTo(471.35202, 551.24);
        ((GeneralPath)shape).lineTo(471.001, 551.24);
        ((GeneralPath)shape).lineTo(471.001, 551.563);
        ((GeneralPath)shape).lineTo(471.35202, 551.563);
        ((GeneralPath)shape).lineTo(471.35202, 553.112);
        ((GeneralPath)shape).curveTo(471.35202, 553.722, 471.49802, 553.896, 472.13303, 553.896);
        ((GeneralPath)shape).curveTo(472.69202, 553.896, 472.91403, 553.652, 472.91403, 553.135);
        ((GeneralPath)shape).lineTo(472.91403, 552.94104);
        ((GeneralPath)shape).lineTo(472.52304, 552.94104);
        ((GeneralPath)shape).lineTo(472.52304, 553.137);
        ((GeneralPath)shape).curveTo(472.52304, 553.388, 472.52304, 553.583, 472.12903, 553.583);
        ((GeneralPath)shape).curveTo(471.82004, 553.583, 471.74103, 553.518, 471.74103, 553.232);
        ((GeneralPath)shape).lineTo(471.74103, 551.562);
        ((GeneralPath)shape).lineTo(472.80902, 551.562);
        ((GeneralPath)shape).lineTo(472.80902, 551.24);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_506;
        g.setTransform(defaultTransform__0_506);
        g.setClip(clip__0_506);
        float alpha__0_507 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_507 = g.getClip();
        AffineTransform defaultTransform__0_507 = g.getTransform();
        
//      _0_507 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(473.182, 551.24);
        ((GeneralPath)shape).lineTo(473.182, 553.896);
        ((GeneralPath)shape).lineTo(473.573, 553.896);
        ((GeneralPath)shape).lineTo(473.573, 552.262);
        ((GeneralPath)shape).curveTo(473.573, 551.797, 473.734, 551.56305, 474.224, 551.56305);
        ((GeneralPath)shape).curveTo(474.556, 551.56305, 474.666, 551.67206, 474.666, 551.99805);
        ((GeneralPath)shape).lineTo(474.666, 552.17303);
        ((GeneralPath)shape).lineTo(475.05698, 552.17303);
        ((GeneralPath)shape).lineTo(475.05698, 551.98303);
        ((GeneralPath)shape).curveTo(475.05698, 551.491, 474.83, 551.241, 474.322, 551.241);
        ((GeneralPath)shape).curveTo(474.025, 551.241, 473.70798, 551.31604, 473.573, 551.609);
        ((GeneralPath)shape).lineTo(473.573, 551.241);
        ((GeneralPath)shape).lineTo(473.182, 551.241);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_507;
        g.setTransform(defaultTransform__0_507);
        g.setClip(clip__0_507);
        float alpha__0_508 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_508 = g.getClip();
        AffineTransform defaultTransform__0_508 = g.getTransform();
        
//      _0_508 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(475.704, 552.576);
        ((GeneralPath)shape).curveTo(475.704, 551.672, 475.755, 551.563, 476.48502, 551.563);
        ((GeneralPath)shape).curveTo(477.21402, 551.563, 477.26602, 551.67096, 477.26602, 552.576);
        ((GeneralPath)shape).curveTo(477.26602, 553.474, 477.21402, 553.584, 476.48502, 553.584);
        ((GeneralPath)shape).curveTo(475.755, 553.584, 475.704, 553.475, 475.704, 552.576);
        ((GeneralPath)shape).moveTo(475.313, 552.57);
        ((GeneralPath)shape).curveTo(475.313, 553.664, 475.504, 553.896, 476.485, 553.896);
        ((GeneralPath)shape).curveTo(477.46597, 553.896, 477.65698, 553.664, 477.65698, 552.57);
        ((GeneralPath)shape).curveTo(477.65698, 551.471, 477.46597, 551.24, 476.485, 551.24);
        ((GeneralPath)shape).curveTo(475.504, 551.24, 475.313, 551.472, 475.313, 552.57);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_508;
        g.setTransform(defaultTransform__0_508);
        g.setClip(clip__0_508);
        float alpha__0_509 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_509 = g.getClip();
        AffineTransform defaultTransform__0_509 = g.getTransform();
        
//      _0_509 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(480.116, 551.24);
        ((GeneralPath)shape).lineTo(479.714, 551.24);
        ((GeneralPath)shape).lineTo(479.134, 553.60297);
        ((GeneralPath)shape).lineTo(479.124, 553.60297);
        ((GeneralPath)shape).lineTo(478.327, 551.24);
        ((GeneralPath)shape).lineTo(477.929, 551.24);
        ((GeneralPath)shape).lineTo(478.86298, 553.896);
        ((GeneralPath)shape).lineTo(479.00198, 553.896);
        ((GeneralPath)shape).curveTo(478.912, 554.199, 478.86798, 554.755, 478.46597, 554.755);
        ((GeneralPath)shape).curveTo(478.41696, 554.755, 478.37198, 554.75, 478.32797, 554.74);
        ((GeneralPath)shape).lineTo(478.32797, 555.04297);
        ((GeneralPath)shape).curveTo(478.38196, 555.053, 478.44098, 555.06696, 478.50497, 555.06696);
        ((GeneralPath)shape).curveTo(479.12396, 555.06696, 479.23697, 554.57697, 479.37497, 554.07697);
        ((GeneralPath)shape).lineTo(480.116, 551.24);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_509;
        g.setTransform(defaultTransform__0_509);
        g.setClip(clip__0_509);
        float alpha__0_510 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_510 = g.getClip();
        AffineTransform defaultTransform__0_510 = g.getTransform();
        
//      _0_510 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(480.738, 552.334);
        ((GeneralPath)shape).curveTo(480.738, 551.74396, 480.765, 551.563, 481.547, 551.563);
        ((GeneralPath)shape).curveTo(482.202, 551.563, 482.301, 551.607, 482.301, 552.334);
        ((GeneralPath)shape).lineTo(480.738, 552.334);
        ((GeneralPath)shape).moveTo(482.301, 553.077);
        ((GeneralPath)shape).lineTo(482.301, 553.177);
        ((GeneralPath)shape).curveTo(482.301, 553.548, 482.117, 553.584, 481.54898, 553.584);
        ((GeneralPath)shape).curveTo(480.80197, 553.584, 480.73798, 553.479, 480.73798, 552.646);
        ((GeneralPath)shape).lineTo(482.69098, 552.646);
        ((GeneralPath)shape).lineTo(482.69098, 552.385);
        ((GeneralPath)shape).curveTo(482.69098, 551.431, 482.417, 551.239, 481.55, 551.239);
        ((GeneralPath)shape).curveTo(480.59097, 551.239, 480.347, 551.491, 480.347, 552.56903);
        ((GeneralPath)shape).curveTo(480.347, 553.55804, 480.50497, 553.895, 481.55, 553.895);
        ((GeneralPath)shape).curveTo(482.146, 553.895, 482.69098, 553.895, 482.69098, 553.174);
        ((GeneralPath)shape).lineTo(482.69098, 553.075);
        ((GeneralPath)shape).lineTo(482.301, 553.075);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_510;
        g.setTransform(defaultTransform__0_510);
        g.setClip(clip__0_510);
        float alpha__0_511 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_511 = g.getClip();
        AffineTransform defaultTransform__0_511 = g.getTransform();
        
//      _0_511 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(485.048, 553.896);
        ((GeneralPath)shape).lineTo(485.439, 553.896);
        ((GeneralPath)shape).lineTo(485.439, 550.068);
        ((GeneralPath)shape).lineTo(485.048, 550.068);
        ((GeneralPath)shape).lineTo(485.048, 551.597);
        ((GeneralPath)shape).lineTo(485.038, 551.597);
        ((GeneralPath)shape).curveTo(484.92398, 551.24, 484.486, 551.24, 484.197, 551.24);
        ((GeneralPath)shape).curveTo(483.262, 551.24, 483.173, 551.789, 483.173, 552.57);
        ((GeneralPath)shape).curveTo(483.173, 553.361, 483.268, 553.896, 484.197, 553.896);
        ((GeneralPath)shape).curveTo(484.551, 553.896, 484.88498, 553.842, 485.038, 553.535);
        ((GeneralPath)shape).lineTo(485.048, 553.545);
        ((GeneralPath)shape).lineTo(485.048, 553.896);
        ((GeneralPath)shape).moveTo(484.313, 553.584);
        ((GeneralPath)shape).curveTo(483.744, 553.584, 483.563, 553.475, 483.563, 552.576);
        ((GeneralPath)shape).curveTo(483.563, 551.667, 483.70398, 551.563, 484.313, 551.563);
        ((GeneralPath)shape).curveTo(484.96198, 551.563, 485.047, 551.865, 485.047, 552.576);
        ((GeneralPath)shape).curveTo(485.048, 553.376, 484.933, 553.584, 484.313, 553.584);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_511;
        g.setTransform(defaultTransform__0_511);
        g.setClip(clip__0_511);
        float alpha__0_512 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_512 = g.getClip();
        AffineTransform defaultTransform__0_512 = g.getTransform();
        
//      _0_512 is ShapeNode
        paint = new Color(199, 200, 201, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(416.65, 547.827);
        ((GeneralPath)shape).curveTo(416.65, 548.62305, 416.007, 549.265, 415.212, 549.265);
        ((GeneralPath)shape).lineTo(411.337, 549.265);
        ((GeneralPath)shape).curveTo(410.543, 549.265, 409.89902, 548.622, 409.89902, 547.827);
        ((GeneralPath)shape).lineTo(409.89902, 543.913);
        ((GeneralPath)shape).curveTo(409.89902, 543.119, 410.544, 542.47504, 411.337, 542.47504);
        ((GeneralPath)shape).lineTo(415.212, 542.47504);
        ((GeneralPath)shape).curveTo(416.00702, 542.47504, 416.65, 543.119, 416.65, 543.913);
        ((GeneralPath)shape).lineTo(416.65, 547.827);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_512;
        g.setTransform(defaultTransform__0_512);
        g.setClip(clip__0_512);
        float alpha__0_513 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_513 = g.getClip();
        AffineTransform defaultTransform__0_513 = g.getTransform();
        
//      _0_513 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        stroke = new BasicStroke(1.0f,0,0,10.0f,null,0.0f);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(416.65, 547.827);
        ((GeneralPath)shape).curveTo(416.65, 548.62305, 416.00598, 549.265, 415.212, 549.265);
        ((GeneralPath)shape).lineTo(411.337, 549.265);
        ((GeneralPath)shape).curveTo(410.543, 549.265, 409.89902, 548.622, 409.89902, 547.827);
        ((GeneralPath)shape).lineTo(409.89902, 543.913);
        ((GeneralPath)shape).curveTo(409.89902, 543.119, 410.544, 542.47504, 411.337, 542.47504);
        ((GeneralPath)shape).lineTo(415.212, 542.47504);
        ((GeneralPath)shape).curveTo(416.006, 542.47504, 416.65, 543.119, 416.65, 543.913);
        ((GeneralPath)shape).lineTo(416.65, 547.827);
        ((GeneralPath)shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_513;
        g.setTransform(defaultTransform__0_513);
        g.setClip(clip__0_513);
        float alpha__0_514 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_514 = g.getClip();
        AffineTransform defaultTransform__0_514 = g.getTransform();
        
//      _0_514 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(459.65, 547.827);
        ((GeneralPath)shape).curveTo(459.65, 548.62305, 459.00598, 549.265, 458.212, 549.265);
        ((GeneralPath)shape).lineTo(454.337, 549.265);
        ((GeneralPath)shape).curveTo(453.543, 549.265, 452.89902, 548.622, 452.89902, 547.827);
        ((GeneralPath)shape).lineTo(452.89902, 543.913);
        ((GeneralPath)shape).curveTo(452.89902, 543.119, 453.54303, 542.47504, 454.337, 542.47504);
        ((GeneralPath)shape).lineTo(458.212, 542.47504);
        ((GeneralPath)shape).curveTo(459.006, 542.47504, 459.65, 543.119, 459.65, 543.913);
        ((GeneralPath)shape).lineTo(459.65, 547.827);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_514;
        g.setTransform(defaultTransform__0_514);
        g.setClip(clip__0_514);
        float alpha__0_515 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_515 = g.getClip();
        AffineTransform defaultTransform__0_515 = g.getTransform();
        
//      _0_515 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        stroke = new BasicStroke(1.0f,0,0,10.0f,null,0.0f);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(459.65, 547.827);
        ((GeneralPath)shape).curveTo(459.65, 548.62305, 459.005, 549.265, 458.212, 549.265);
        ((GeneralPath)shape).lineTo(454.336, 549.265);
        ((GeneralPath)shape).curveTo(453.543, 549.265, 452.898, 548.622, 452.898, 547.827);
        ((GeneralPath)shape).lineTo(452.898, 543.913);
        ((GeneralPath)shape).curveTo(452.898, 543.119, 453.543, 542.47504, 454.336, 542.47504);
        ((GeneralPath)shape).lineTo(458.212, 542.47504);
        ((GeneralPath)shape).curveTo(459.005, 542.47504, 459.65, 543.119, 459.65, 543.913);
        ((GeneralPath)shape).lineTo(459.65, 547.827);
        ((GeneralPath)shape).closePath();
        g.setPaint(paint);
        g.setStroke(stroke);
        g.draw(shape);
        origAlpha = alpha__0_515;
        g.setTransform(defaultTransform__0_515);
        g.setClip(clip__0_515);
        float alpha__0_516 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_516 = g.getClip();
        AffineTransform defaultTransform__0_516 = g.getTransform();
        
//      _0_516 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(172.647, 489.63);
        ((GeneralPath)shape).lineTo(172.624, 489.63);
        ((GeneralPath)shape).lineTo(171.272, 484.46);
        ((GeneralPath)shape).lineTo(169.938, 484.46);
        ((GeneralPath)shape).lineTo(168.557, 489.63);
        ((GeneralPath)shape).lineTo(168.524, 489.63);
        ((GeneralPath)shape).lineTo(167.27, 484.46);
        ((GeneralPath)shape).lineTo(166.114, 484.46);
        ((GeneralPath)shape).lineTo(167.681, 490.585);
        ((GeneralPath)shape).lineTo(169.336, 490.585);
        ((GeneralPath)shape).lineTo(170.583, 485.901);
        ((GeneralPath)shape).lineTo(170.614, 485.901);
        ((GeneralPath)shape).lineTo(171.819, 490.585);
        ((GeneralPath)shape).lineTo(173.491, 490.585);
        ((GeneralPath)shape).lineTo(175.114, 484.46);
        ((GeneralPath)shape).lineTo(173.948, 484.46);
        ((GeneralPath)shape).lineTo(172.647, 489.63);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_516;
        g.setTransform(defaultTransform__0_516);
        g.setClip(clip__0_516);
        float alpha__0_517 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_517 = g.getClip();
        AffineTransform defaultTransform__0_517 = g.getTransform();
        
//      _0_517 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(176.643, 485.456);
        ((GeneralPath)shape).lineTo(179.604, 485.456);
        ((GeneralPath)shape).lineTo(179.604, 484.46);
        ((GeneralPath)shape).lineTo(175.518, 484.46);
        ((GeneralPath)shape).lineTo(175.518, 490.585);
        ((GeneralPath)shape).lineTo(179.627, 490.585);
        ((GeneralPath)shape).lineTo(179.627, 489.589);
        ((GeneralPath)shape).lineTo(176.643, 489.589);
        ((GeneralPath)shape).lineTo(176.643, 487.96);
        ((GeneralPath)shape).lineTo(179.451, 487.96);
        ((GeneralPath)shape).lineTo(179.451, 486.96);
        ((GeneralPath)shape).lineTo(176.643, 486.96);
        ((GeneralPath)shape).lineTo(176.643, 485.456);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_517;
        g.setTransform(defaultTransform__0_517);
        g.setClip(clip__0_517);
        float alpha__0_518 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_518 = g.getClip();
        AffineTransform defaultTransform__0_518 = g.getTransform();
        
//      _0_518 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(183.894, 488.585);
        ((GeneralPath)shape).lineTo(181.812, 488.585);
        ((GeneralPath)shape).lineTo(182.841, 485.35098);
        ((GeneralPath)shape).lineTo(182.85701, 485.35098);
        ((GeneralPath)shape).lineTo(183.894, 488.585);
        ((GeneralPath)shape).moveTo(184.151, 489.46);
        ((GeneralPath)shape).lineTo(184.546, 490.585);
        ((GeneralPath)shape).lineTo(185.72, 490.585);
        ((GeneralPath)shape).lineTo(183.677, 484.46);
        ((GeneralPath)shape).lineTo(181.972, 484.46);
        ((GeneralPath)shape).lineTo(179.97, 490.585);
        ((GeneralPath)shape).lineTo(181.169, 490.585);
        ((GeneralPath)shape).lineTo(181.546, 489.46);
        ((GeneralPath)shape).lineTo(184.151, 489.46);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_518;
        g.setTransform(defaultTransform__0_518);
        g.setClip(clip__0_518);
        float alpha__0_519 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_519 = g.getClip();
        AffineTransform defaultTransform__0_519 = g.getTransform();
        
//      _0_519 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(187.267, 487.585);
        ((GeneralPath)shape).lineTo(187.267, 485.456);
        ((GeneralPath)shape).lineTo(188.77899, 485.456);
        ((GeneralPath)shape).curveTo(189.646, 485.456, 189.767, 485.702, 189.767, 486.569);
        ((GeneralPath)shape).curveTo(189.767, 487.428, 189.607, 487.585, 188.77899, 487.585);
        ((GeneralPath)shape).lineTo(187.267, 487.585);
        ((GeneralPath)shape).moveTo(186.142, 490.585);
        ((GeneralPath)shape).lineTo(187.267, 490.585);
        ((GeneralPath)shape).lineTo(187.267, 488.585);
        ((GeneralPath)shape).lineTo(188.77899, 488.585);
        ((GeneralPath)shape).curveTo(190.32999, 488.585, 190.892, 488.284, 190.892, 486.56);
        ((GeneralPath)shape).curveTo(190.892, 484.85098, 190.369, 484.46, 188.775, 484.46);
        ((GeneralPath)shape).lineTo(186.142, 484.46);
        ((GeneralPath)shape).lineTo(186.142, 490.585);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_519;
        g.setTransform(defaultTransform__0_519);
        g.setClip(clip__0_519);
        float alpha__0_520 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_520 = g.getClip();
        AffineTransform defaultTransform__0_520 = g.getTransform();
        
//      _0_520 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(193.969, 485.456);
        ((GeneralPath)shape).curveTo(194.97699, 485.456, 195.37999, 485.53598, 195.37999, 486.665);
        ((GeneralPath)shape).lineTo(195.37999, 488.28);
        ((GeneralPath)shape).curveTo(195.37999, 489.44, 195.02399, 489.585, 193.993, 489.585);
        ((GeneralPath)shape).curveTo(192.858, 489.585, 192.62999, 489.474, 192.62999, 488.28);
        ((GeneralPath)shape).lineTo(192.62999, 486.665);
        ((GeneralPath)shape).curveTo(192.63, 485.712, 192.749, 485.456, 193.969, 485.456);
        ((GeneralPath)shape).moveTo(193.993, 484.46);
        ((GeneralPath)shape).curveTo(192.124, 484.46, 191.50499, 484.78998, 191.50499, 486.663);
        ((GeneralPath)shape).lineTo(191.50499, 488.28598);
        ((GeneralPath)shape).curveTo(191.50499, 490.26297, 192.172, 490.585, 193.993, 490.585);
        ((GeneralPath)shape).curveTo(195.774, 490.585, 196.50499, 490.216, 196.50499, 488.28598);
        ((GeneralPath)shape).lineTo(196.50499, 486.663);
        ((GeneralPath)shape).curveTo(196.505, 484.726, 195.688, 484.46, 193.993, 484.46);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_520;
        g.setTransform(defaultTransform__0_520);
        g.setClip(clip__0_520);
        float alpha__0_521 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_521 = g.getClip();
        AffineTransform defaultTransform__0_521 = g.getTransform();
        
//      _0_521 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(201.85, 489.589);
        ((GeneralPath)shape).lineTo(201.85, 489.589);
        ((GeneralPath)shape).lineTo(199.254, 484.46);
        ((GeneralPath)shape).lineTo(197.35, 484.46);
        ((GeneralPath)shape).lineTo(197.35, 490.585);
        ((GeneralPath)shape).lineTo(198.475, 490.585);
        ((GeneralPath)shape).lineTo(198.475, 485.456);
        ((GeneralPath)shape).lineTo(201.063, 490.585);
        ((GeneralPath)shape).lineTo(202.975, 490.585);
        ((GeneralPath)shape).lineTo(202.975, 484.46);
        ((GeneralPath)shape).lineTo(201.85, 484.46);
        ((GeneralPath)shape).lineTo(201.85, 489.589);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_521;
        g.setTransform(defaultTransform__0_521);
        g.setClip(clip__0_521);
        float alpha__0_522 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_522 = g.getClip();
        AffineTransform defaultTransform__0_522 = g.getTransform();
        
//      _0_522 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(203.716, 488.562);
        ((GeneralPath)shape).lineTo(203.716, 488.845);
        ((GeneralPath)shape).curveTo(203.716, 490.585, 204.841, 490.585, 206.224, 490.585);
        ((GeneralPath)shape).curveTo(207.783, 490.585, 208.591, 490.439, 208.591, 488.714);
        ((GeneralPath)shape).curveTo(208.591, 487.15298, 208.08101, 487.056, 206.216, 486.96);
        ((GeneralPath)shape).curveTo(204.99901, 486.88998, 204.841, 486.866, 204.841, 486.149);
        ((GeneralPath)shape).curveTo(204.841, 485.524, 205.032, 485.41498, 206.224, 485.41498);
        ((GeneralPath)shape).curveTo(207.066, 485.41498, 207.341, 485.43997, 207.341, 486.136);
        ((GeneralPath)shape).lineTo(207.341, 486.343);
        ((GeneralPath)shape).lineTo(208.466, 486.343);
        ((GeneralPath)shape).lineTo(208.466, 486.138);
        ((GeneralPath)shape).curveTo(208.466, 484.46, 207.45601, 484.46, 206.22, 484.46);
        ((GeneralPath)shape).curveTo(204.767, 484.46, 203.716, 484.46, 203.716, 486.112);
        ((GeneralPath)shape).curveTo(203.716, 487.862, 204.74501, 487.813, 206.058, 487.893);
        ((GeneralPath)shape).curveTo(206.992, 487.94202, 207.466, 487.829, 207.466, 488.69);
        ((GeneralPath)shape).curveTo(207.466, 489.391, 207.31801, 489.585, 206.239, 489.585);
        ((GeneralPath)shape).curveTo(205.145, 489.585, 204.841, 489.53598, 204.841, 488.845);
        ((GeneralPath)shape).lineTo(204.841, 488.562);
        ((GeneralPath)shape).lineTo(203.716, 488.562);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_522;
        g.setTransform(defaultTransform__0_522);
        g.setClip(clip__0_522);
        float alpha__0_523 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_523 = g.getClip();
        AffineTransform defaultTransform__0_523 = g.getTransform();
        
//      _0_523 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new Rectangle2D.Double(212.16600036621094, 484.4599914550781, 1.125, 6.125);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_523;
        g.setTransform(defaultTransform__0_523);
        g.setClip(clip__0_523);
        float alpha__0_524 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_524 = g.getClip();
        AffineTransform defaultTransform__0_524 = g.getTransform();
        
//      _0_524 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(218.874, 489.589);
        ((GeneralPath)shape).lineTo(218.874, 489.589);
        ((GeneralPath)shape).lineTo(216.278, 484.46);
        ((GeneralPath)shape).lineTo(214.374, 484.46);
        ((GeneralPath)shape).lineTo(214.374, 490.585);
        ((GeneralPath)shape).lineTo(215.499, 490.585);
        ((GeneralPath)shape).lineTo(215.499, 485.456);
        ((GeneralPath)shape).lineTo(218.086, 490.585);
        ((GeneralPath)shape).lineTo(219.999, 490.585);
        ((GeneralPath)shape).lineTo(219.999, 484.46);
        ((GeneralPath)shape).lineTo(218.874, 484.46);
        ((GeneralPath)shape).lineTo(218.874, 489.589);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_524;
        g.setTransform(defaultTransform__0_524);
        g.setClip(clip__0_524);
        float alpha__0_525 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_525 = g.getClip();
        AffineTransform defaultTransform__0_525 = g.getTransform();
        
//      _0_525 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(224.765, 484.46);
        ((GeneralPath)shape).lineTo(223.166, 489.614);
        ((GeneralPath)shape).lineTo(223.14, 489.614);
        ((GeneralPath)shape).lineTo(221.541, 484.46);
        ((GeneralPath)shape).lineTo(220.365, 484.46);
        ((GeneralPath)shape).lineTo(222.285, 490.585);
        ((GeneralPath)shape).lineTo(224.021, 490.585);
        ((GeneralPath)shape).lineTo(225.957, 484.46);
        ((GeneralPath)shape).lineTo(224.765, 484.46);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_525;
        g.setTransform(defaultTransform__0_525);
        g.setClip(clip__0_525);
        float alpha__0_526 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_526 = g.getClip();
        AffineTransform defaultTransform__0_526 = g.getTransform();
        
//      _0_526 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(227.531, 485.456);
        ((GeneralPath)shape).lineTo(230.492, 485.456);
        ((GeneralPath)shape).lineTo(230.492, 484.46);
        ((GeneralPath)shape).lineTo(226.406, 484.46);
        ((GeneralPath)shape).lineTo(226.406, 490.585);
        ((GeneralPath)shape).lineTo(230.515, 490.585);
        ((GeneralPath)shape).lineTo(230.515, 489.589);
        ((GeneralPath)shape).lineTo(227.531, 489.589);
        ((GeneralPath)shape).lineTo(227.531, 487.96);
        ((GeneralPath)shape).lineTo(230.339, 487.96);
        ((GeneralPath)shape).lineTo(230.339, 486.96);
        ((GeneralPath)shape).lineTo(227.531, 486.96);
        ((GeneralPath)shape).lineTo(227.531, 485.456);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_526;
        g.setTransform(defaultTransform__0_526);
        g.setClip(clip__0_526);
        float alpha__0_527 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_527 = g.getClip();
        AffineTransform defaultTransform__0_527 = g.getTransform();
        
//      _0_527 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(235.857, 489.589);
        ((GeneralPath)shape).lineTo(235.857, 489.589);
        ((GeneralPath)shape).lineTo(233.262, 484.46);
        ((GeneralPath)shape).lineTo(231.357, 484.46);
        ((GeneralPath)shape).lineTo(231.357, 490.585);
        ((GeneralPath)shape).lineTo(232.482, 490.585);
        ((GeneralPath)shape).lineTo(232.482, 485.456);
        ((GeneralPath)shape).lineTo(235.07, 490.585);
        ((GeneralPath)shape).lineTo(236.982, 490.585);
        ((GeneralPath)shape).lineTo(236.982, 484.46);
        ((GeneralPath)shape).lineTo(235.857, 484.46);
        ((GeneralPath)shape).lineTo(235.857, 489.589);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_527;
        g.setTransform(defaultTransform__0_527);
        g.setClip(clip__0_527);
        float alpha__0_528 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_528 = g.getClip();
        AffineTransform defaultTransform__0_528 = g.getTransform();
        
//      _0_528 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(240.349, 485.456);
        ((GeneralPath)shape).lineTo(242.108, 485.456);
        ((GeneralPath)shape).lineTo(242.108, 484.46);
        ((GeneralPath)shape).lineTo(237.464, 484.46);
        ((GeneralPath)shape).lineTo(237.464, 485.456);
        ((GeneralPath)shape).lineTo(239.224, 485.456);
        ((GeneralPath)shape).lineTo(239.224, 490.585);
        ((GeneralPath)shape).lineTo(240.349, 490.585);
        ((GeneralPath)shape).lineTo(240.349, 485.456);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_528;
        g.setTransform(defaultTransform__0_528);
        g.setClip(clip__0_528);
        float alpha__0_529 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_529 = g.getClip();
        AffineTransform defaultTransform__0_529 = g.getTransform();
        
//      _0_529 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(245.01, 485.456);
        ((GeneralPath)shape).curveTo(246.01799, 485.456, 246.42, 485.53598, 246.42, 486.665);
        ((GeneralPath)shape).lineTo(246.42, 488.28);
        ((GeneralPath)shape).curveTo(246.42, 489.44, 246.065, 489.585, 245.033, 489.585);
        ((GeneralPath)shape).curveTo(243.89801, 489.585, 243.67, 489.474, 243.67, 488.28);
        ((GeneralPath)shape).lineTo(243.67, 486.665);
        ((GeneralPath)shape).curveTo(243.67, 485.712, 243.789, 485.456, 245.01, 485.456);
        ((GeneralPath)shape).moveTo(245.033, 484.46);
        ((GeneralPath)shape).curveTo(243.164, 484.46, 242.545, 484.78998, 242.545, 486.663);
        ((GeneralPath)shape).lineTo(242.545, 488.28598);
        ((GeneralPath)shape).curveTo(242.545, 490.26297, 243.213, 490.585, 245.033, 490.585);
        ((GeneralPath)shape).curveTo(246.81401, 490.585, 247.545, 490.216, 247.545, 488.28598);
        ((GeneralPath)shape).lineTo(247.545, 486.663);
        ((GeneralPath)shape).curveTo(247.545, 484.726, 246.729, 484.46, 245.033, 484.46);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_529;
        g.setTransform(defaultTransform__0_529);
        g.setClip(clip__0_529);
        float alpha__0_530 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_530 = g.getClip();
        AffineTransform defaultTransform__0_530 = g.getTransform();
        
//      _0_530 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(249.515, 487.585);
        ((GeneralPath)shape).lineTo(249.515, 485.456);
        ((GeneralPath)shape).lineTo(251.068, 485.456);
        ((GeneralPath)shape).curveTo(251.86699, 485.456, 252.015, 485.61398, 252.015, 486.495);
        ((GeneralPath)shape).curveTo(252.015, 487.41098, 251.814, 487.585, 250.99, 487.585);
        ((GeneralPath)shape).lineTo(249.515, 487.585);
        ((GeneralPath)shape).moveTo(251.263, 488.585);
        ((GeneralPath)shape).curveTo(251.81, 488.585, 252.015, 488.942, 252.015, 489.452);
        ((GeneralPath)shape).lineTo(252.015, 490.585);
        ((GeneralPath)shape).lineTo(253.14, 490.585);
        ((GeneralPath)shape).lineTo(253.14, 489.45398);
        ((GeneralPath)shape).curveTo(253.14, 488.59897, 252.937, 488.15298, 252.105, 488.081);
        ((GeneralPath)shape).lineTo(252.105, 488.048);
        ((GeneralPath)shape).curveTo(253.14, 487.89, 253.14, 487.21802, 253.14, 486.296);
        ((GeneralPath)shape).curveTo(253.14, 484.884, 252.65, 484.46, 251.372, 484.46);
        ((GeneralPath)shape).lineTo(248.39, 484.46);
        ((GeneralPath)shape).lineTo(248.39, 490.585);
        ((GeneralPath)shape).lineTo(249.515, 490.585);
        ((GeneralPath)shape).lineTo(249.515, 488.585);
        ((GeneralPath)shape).lineTo(251.263, 488.585);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_530;
        g.setTransform(defaultTransform__0_530);
        g.setClip(clip__0_530);
        float alpha__0_531 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_531 = g.getClip();
        AffineTransform defaultTransform__0_531 = g.getTransform();
        
//      _0_531 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(259.132, 484.46);
        ((GeneralPath)shape).lineTo(257.804, 484.46);
        ((GeneralPath)shape).lineTo(256.369, 487.056);
        ((GeneralPath)shape).lineTo(256.345, 487.056);
        ((GeneralPath)shape).lineTo(254.935, 484.46);
        ((GeneralPath)shape).lineTo(253.623, 484.46);
        ((GeneralPath)shape).lineTo(255.791, 488.222);
        ((GeneralPath)shape).lineTo(255.791, 490.585);
        ((GeneralPath)shape).lineTo(256.916, 490.585);
        ((GeneralPath)shape).lineTo(256.916, 488.222);
        ((GeneralPath)shape).lineTo(259.132, 484.46);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_531;
        g.setTransform(defaultTransform__0_531);
        g.setClip(clip__0_531);
        float alpha__0_532 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_532 = g.getClip();
        AffineTransform defaultTransform__0_532 = g.getTransform();
        
//      _0_532 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(496.807, 525.476);
        ((GeneralPath)shape).lineTo(496.188, 525.476);
        ((GeneralPath)shape).lineTo(495.532, 528.846);
        ((GeneralPath)shape).lineTo(497.692, 528.846);
        ((GeneralPath)shape).lineTo(497.799, 528.298);
        ((GeneralPath)shape).lineTo(496.258, 528.298);
        ((GeneralPath)shape).lineTo(496.807, 525.476);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_532;
        g.setTransform(defaultTransform__0_532);
        g.setClip(clip__0_532);
        float alpha__0_533 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_533 = g.getClip();
        AffineTransform defaultTransform__0_533 = g.getTransform();
        
//      _0_533 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(498.768, 527.471);
        ((GeneralPath)shape).curveTo(498.85202, 527.041, 498.904, 526.93604, 499.38202, 526.93604);
        ((GeneralPath)shape).curveTo(499.834, 526.93604, 499.89703, 526.97205, 499.80002, 527.471);
        ((GeneralPath)shape).lineTo(498.768, 527.471);
        ((GeneralPath)shape).moveTo(499.675, 528.113);
        ((GeneralPath)shape).curveTo(499.612, 528.433, 499.39798, 528.433, 499.09097, 528.433);
        ((GeneralPath)shape).curveTo(498.59396, 528.433, 498.61096, 528.284, 498.70096, 527.81396);
        ((GeneralPath)shape).lineTo(500.28296, 527.81396);
        ((GeneralPath)shape).curveTo(500.48196, 526.78894, 500.40997, 526.506, 499.46497, 526.506);
        ((GeneralPath)shape).curveTo(498.53598, 526.506, 498.33597, 526.863, 498.17697, 527.683);
        ((GeneralPath)shape).curveTo(498.00497, 528.56696, 498.12997, 528.845, 499.00998, 528.845);
        ((GeneralPath)shape).curveTo(499.667, 528.845, 500.08997, 528.811, 500.22598, 528.113);
        ((GeneralPath)shape).lineTo(499.675, 528.113);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_533;
        g.setTransform(defaultTransform__0_533);
        g.setClip(clip__0_533);
        float alpha__0_534 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_534 = g.getClip();
        AffineTransform defaultTransform__0_534 = g.getTransform();
        
//      _0_534 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(502.234, 526.507);
        ((GeneralPath)shape).lineTo(501.67102, 526.507);
        ((GeneralPath)shape).lineTo(501.7, 526.361);
        ((GeneralPath)shape).curveTo(501.75803, 526.06305, 501.789, 525.903, 502.16702, 525.903);
        ((GeneralPath)shape).lineTo(502.33502, 525.903);
        ((GeneralPath)shape).lineTo(502.41803, 525.47504);
        ((GeneralPath)shape).lineTo(502.05804, 525.47504);
        ((GeneralPath)shape).curveTo(501.45404, 525.47504, 501.27505, 525.71606, 501.16306, 526.291);
        ((GeneralPath)shape).lineTo(501.12106, 526.50604);
        ((GeneralPath)shape).lineTo(500.79507, 526.50604);
        ((GeneralPath)shape).lineTo(500.7151, 526.91907);
        ((GeneralPath)shape).lineTo(501.04108, 526.91907);
        ((GeneralPath)shape).lineTo(500.66608, 528.8451);
        ((GeneralPath)shape).lineTo(501.21707, 528.8451);
        ((GeneralPath)shape).lineTo(501.59106, 526.91907);
        ((GeneralPath)shape).lineTo(502.15405, 526.91907);
        ((GeneralPath)shape).lineTo(502.234, 526.507);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_534;
        g.setTransform(defaultTransform__0_534);
        g.setClip(clip__0_534);
        float alpha__0_535 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_535 = g.getClip();
        AffineTransform defaultTransform__0_535 = g.getTransform();
        
//      _0_535 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(502.145, 526.92);
        ((GeneralPath)shape).lineTo(502.43597, 526.92);
        ((GeneralPath)shape).lineTo(502.20197, 528.125);
        ((GeneralPath)shape).curveTo(502.08896, 528.706, 502.24896, 528.846, 502.86096, 528.846);
        ((GeneralPath)shape).curveTo(503.46597, 528.846, 503.68396, 528.636, 503.81998, 527.938);
        ((GeneralPath)shape).lineTo(503.339, 527.938);
        ((GeneralPath)shape).curveTo(503.291, 528.183, 503.27698, 528.43396, 502.943, 528.43396);
        ((GeneralPath)shape).curveTo(502.693, 528.43396, 502.712, 528.33594, 502.754, 528.121);
        ((GeneralPath)shape).lineTo(502.987, 526.92096);
        ((GeneralPath)shape).lineTo(503.902, 526.92096);
        ((GeneralPath)shape).lineTo(503.982, 526.50793);
        ((GeneralPath)shape).lineTo(503.067, 526.50793);
        ((GeneralPath)shape).lineTo(503.17297, 525.96295);
        ((GeneralPath)shape).lineTo(502.62198, 525.96295);
        ((GeneralPath)shape).lineTo(502.51697, 526.50793);
        ((GeneralPath)shape).lineTo(502.22598, 526.50793);
        ((GeneralPath)shape).lineTo(502.145, 526.92);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_535;
        g.setTransform(defaultTransform__0_535);
        g.setClip(clip__0_535);
        float alpha__0_536 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_536 = g.getClip();
        AffineTransform defaultTransform__0_536 = g.getTransform();
        
//      _0_536 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(497.633, 532.148);
        ((GeneralPath)shape).lineTo(496.487, 532.148);
        ((GeneralPath)shape).lineTo(497.4, 530.368);
        ((GeneralPath)shape).lineTo(497.409, 530.368);
        ((GeneralPath)shape).lineTo(497.633, 532.148);
        ((GeneralPath)shape).moveTo(497.681, 532.63);
        ((GeneralPath)shape).lineTo(497.77798, 533.249);
        ((GeneralPath)shape).lineTo(498.42398, 533.249);
        ((GeneralPath)shape).lineTo(497.95398, 529.879);
        ((GeneralPath)shape).lineTo(497.016, 529.879);
        ((GeneralPath)shape).lineTo(495.259, 533.249);
        ((GeneralPath)shape).lineTo(495.919, 533.249);
        ((GeneralPath)shape).lineTo(496.247, 532.63);
        ((GeneralPath)shape).lineTo(497.681, 532.63);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_536;
        g.setTransform(defaultTransform__0_536);
        g.setClip(clip__0_536);
        float alpha__0_537 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_537 = g.getClip();
        AffineTransform defaultTransform__0_537 = g.getTransform();
        
//      _0_537 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(499.041, 530.91);
        ((GeneralPath)shape).lineTo(498.586, 533.24896);
        ((GeneralPath)shape).lineTo(499.137, 533.24896);
        ((GeneralPath)shape).lineTo(499.413, 531.826);
        ((GeneralPath)shape).curveTo(499.472, 531.527, 499.611, 531.338, 499.964, 531.338);
        ((GeneralPath)shape).curveTo(500.241, 531.338, 500.23798, 531.47504, 500.193, 531.70703);
        ((GeneralPath)shape).lineTo(500.16998, 531.82605);
        ((GeneralPath)shape).lineTo(500.72098, 531.82605);
        ((GeneralPath)shape).lineTo(500.757, 531.6401);
        ((GeneralPath)shape).curveTo(500.84198, 531.2021, 500.772, 530.9101, 500.249, 530.9101);
        ((GeneralPath)shape).curveTo(499.96298, 530.9101, 499.694, 530.9841, 499.527, 531.2381);
        ((GeneralPath)shape).lineTo(499.591, 530.9101);
        ((GeneralPath)shape).lineTo(499.041, 530.9101);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_537;
        g.setTransform(defaultTransform__0_537);
        g.setClip(clip__0_537);
        float alpha__0_538 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_538 = g.getClip();
        AffineTransform defaultTransform__0_538 = g.getTransform();
        
//      _0_538 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(501.258, 530.91);
        ((GeneralPath)shape).lineTo(500.804, 533.24896);
        ((GeneralPath)shape).lineTo(501.35397, 533.24896);
        ((GeneralPath)shape).lineTo(501.60696, 531.94794);
        ((GeneralPath)shape).curveTo(501.68597, 531.5399, 501.81897, 531.33795, 502.29398, 531.33795);
        ((GeneralPath)shape).curveTo(502.615, 531.33795, 502.66696, 531.45197, 502.61, 531.74194);
        ((GeneralPath)shape).lineTo(502.317, 533.24896);
        ((GeneralPath)shape).lineTo(502.86798, 533.24896);
        ((GeneralPath)shape).lineTo(503.12097, 531.94794);
        ((GeneralPath)shape).curveTo(503.19998, 531.5399, 503.32498, 531.33795, 503.76697, 531.33795);
        ((GeneralPath)shape).curveTo(504.06598, 531.33795, 504.11197, 531.45197, 504.05597, 531.74194);
        ((GeneralPath)shape).lineTo(503.76297, 533.24896);
        ((GeneralPath)shape).lineTo(504.31296, 533.24896);
        ((GeneralPath)shape).lineTo(504.61795, 531.686);
        ((GeneralPath)shape).curveTo(504.72794, 531.118, 504.57495, 530.91095, 503.98895, 530.91095);
        ((GeneralPath)shape).curveTo(503.68796, 530.91095, 503.36096, 531.001, 503.19495, 531.32495);
        ((GeneralPath)shape).lineTo(503.17694, 531.32495);
        ((GeneralPath)shape).curveTo(503.17896, 530.98596, 502.85095, 530.91095, 502.55396, 530.91095);
        ((GeneralPath)shape).curveTo(502.25595, 530.91095, 501.92294, 530.97595, 501.73996, 531.272);
        ((GeneralPath)shape).lineTo(501.80997, 530.91095);
        ((GeneralPath)shape).lineTo(501.258, 530.91095);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_538;
        g.setTransform(defaultTransform__0_538);
        g.setClip(clip__0_538);
        float alpha__0_539 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_539 = g.getClip();
        AffineTransform defaultTransform__0_539 = g.getTransform();
        
//      _0_539 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(566.031, 527.49);
        ((GeneralPath)shape).lineTo(566.25903, 526.319);
        ((GeneralPath)shape).lineTo(567.11304, 526.319);
        ((GeneralPath)shape).curveTo(567.55206, 526.319, 567.61707, 526.40594, 567.523, 526.88995);
        ((GeneralPath)shape).curveTo(567.425, 527.394, 567.295, 527.4899, 566.842, 527.4899);
        ((GeneralPath)shape).lineTo(566.031, 527.4899);
        ((GeneralPath)shape).moveTo(566.886, 528.041);
        ((GeneralPath)shape).curveTo(567.188, 528.041, 567.26196, 528.237, 567.207, 528.518);
        ((GeneralPath)shape).lineTo(567.086, 529.142);
        ((GeneralPath)shape).lineTo(567.705, 529.142);
        ((GeneralPath)shape).lineTo(567.826, 528.52);
        ((GeneralPath)shape).curveTo(567.91797, 528.049, 567.85297, 527.804, 567.403, 527.76404);
        ((GeneralPath)shape).lineTo(567.40704, 527.74506);
        ((GeneralPath)shape).curveTo(567.99304, 527.6581, 568.06506, 527.2881, 568.16406, 526.78107);
        ((GeneralPath)shape).curveTo(568.3141, 526.0041, 568.09106, 525.7701, 567.38806, 525.7701);
        ((GeneralPath)shape).lineTo(565.74603, 525.7701);
        ((GeneralPath)shape).lineTo(565.091, 529.14105);
        ((GeneralPath)shape).lineTo(565.71, 529.14105);
        ((GeneralPath)shape).lineTo(565.924, 528.04004);
        ((GeneralPath)shape).lineTo(566.886, 528.04004);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_539;
        g.setTransform(defaultTransform__0_539);
        g.setClip(clip__0_539);
        float alpha__0_540 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_540 = g.getClip();
        AffineTransform defaultTransform__0_540 = g.getTransform();
        
//      _0_540 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(569.476, 525.771);
        ((GeneralPath)shape).lineTo(568.925, 525.771);
        ((GeneralPath)shape).lineTo(568.832, 526.249);
        ((GeneralPath)shape).lineTo(569.383, 526.249);
        ((GeneralPath)shape).lineTo(569.476, 525.771);
        ((GeneralPath)shape).moveTo(569.274, 526.803);
        ((GeneralPath)shape).lineTo(568.724, 526.803);
        ((GeneralPath)shape).lineTo(568.269, 529.14197);
        ((GeneralPath)shape).lineTo(568.82, 529.14197);
        ((GeneralPath)shape).lineTo(569.274, 526.803);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_540;
        g.setTransform(defaultTransform__0_540);
        g.setClip(clip__0_540);
        float alpha__0_541 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_541 = g.getClip();
        AffineTransform defaultTransform__0_541 = g.getTransform();
        
//      _0_541 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(570.801, 529.25);
        ((GeneralPath)shape).curveTo(570.721, 529.66, 570.60504, 529.761, 570.153, 529.761);
        ((GeneralPath)shape).curveTo(569.853, 529.761, 569.749, 529.709, 569.815, 529.368);
        ((GeneralPath)shape).lineTo(569.265, 529.368);
        ((GeneralPath)shape).curveTo(569.09204, 530.074, 569.49603, 530.173, 570.06104, 530.173);
        ((GeneralPath)shape).curveTo(570.908, 530.173, 571.21, 529.974, 571.374, 529.133);
        ((GeneralPath)shape).lineTo(571.827, 526.803);
        ((GeneralPath)shape).lineTo(571.276, 526.803);
        ((GeneralPath)shape).lineTo(571.203, 527.178);
        ((GeneralPath)shape).curveTo(571.122, 526.873, 570.88, 526.803, 570.554, 526.803);
        ((GeneralPath)shape).curveTo(569.695, 526.803, 569.59, 527.33795, 569.45404, 528.04);
        ((GeneralPath)shape).curveTo(569.32306, 528.711, 569.33105, 529.14197, 570.111, 529.14197);
        ((GeneralPath)shape).curveTo(570.388, 529.14197, 570.68805, 529.08997, 570.88904, 528.79297);
        ((GeneralPath)shape).lineTo(570.801, 529.25);
        ((GeneralPath)shape).moveTo(570.647, 527.23);
        ((GeneralPath)shape).curveTo(571.11896, 527.23, 571.15497, 527.425, 571.06, 527.912);
        ((GeneralPath)shape).curveTo(570.955, 528.455, 570.902, 528.72797, 570.356, 528.72797);
        ((GeneralPath)shape).curveTo(569.87, 528.72797, 569.92804, 528.428, 570.029, 527.912);
        ((GeneralPath)shape).curveTo(570.141, 527.339, 570.27, 527.23, 570.647, 527.23);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_541;
        g.setTransform(defaultTransform__0_541);
        g.setClip(clip__0_541);
        float alpha__0_542 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_542 = g.getClip();
        AffineTransform defaultTransform__0_542 = g.getTransform();
        
//      _0_542 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(573.129, 525.771);
        ((GeneralPath)shape).lineTo(572.57904, 525.771);
        ((GeneralPath)shape).lineTo(571.924, 529.14197);
        ((GeneralPath)shape).lineTo(572.474, 529.14197);
        ((GeneralPath)shape).lineTo(572.721, 527.87195);
        ((GeneralPath)shape).curveTo(572.801, 527.4589, 572.91003, 527.2299, 573.385, 527.2299);
        ((GeneralPath)shape).curveTo(573.736, 527.2299, 573.786, 527.34393, 573.722, 527.6779);
        ((GeneralPath)shape).lineTo(573.437, 529.1409);
        ((GeneralPath)shape).lineTo(573.98804, 529.1409);
        ((GeneralPath)shape).lineTo(574.28406, 527.61694);
        ((GeneralPath)shape).curveTo(574.39307, 527.05396, 574.2621, 526.8029, 573.6631, 526.8029);
        ((GeneralPath)shape).curveTo(573.34106, 526.8029, 573.0611, 526.8379, 572.8731, 527.1809);
        ((GeneralPath)shape).lineTo(572.8561, 527.1809);
        ((GeneralPath)shape).lineTo(573.129, 525.771);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_542;
        g.setTransform(defaultTransform__0_542);
        g.setClip(clip__0_542);
        float alpha__0_543 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_543 = g.getClip();
        AffineTransform defaultTransform__0_543 = g.getTransform();
        
//      _0_543 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(574.641, 527.216);
        ((GeneralPath)shape).lineTo(574.93097, 527.216);
        ((GeneralPath)shape).lineTo(574.69696, 528.42);
        ((GeneralPath)shape).curveTo(574.584, 529.00195, 574.74396, 529.14197, 575.35596, 529.14197);
        ((GeneralPath)shape).curveTo(575.96094, 529.14197, 576.17896, 528.93097, 576.31494, 528.23395);
        ((GeneralPath)shape).lineTo(575.8339, 528.23395);
        ((GeneralPath)shape).curveTo(575.78595, 528.47894, 575.7719, 528.72894, 575.4379, 528.72894);
        ((GeneralPath)shape).curveTo(575.1879, 528.72894, 575.20795, 528.6309, 575.2489, 528.41595);
        ((GeneralPath)shape).lineTo(575.4819, 527.21594);
        ((GeneralPath)shape).lineTo(576.39685, 527.21594);
        ((GeneralPath)shape).lineTo(576.47687, 526.8029);
        ((GeneralPath)shape).lineTo(575.5619, 526.8029);
        ((GeneralPath)shape).lineTo(575.6679, 526.25793);
        ((GeneralPath)shape).lineTo(575.1169, 526.25793);
        ((GeneralPath)shape).lineTo(575.0119, 526.8029);
        ((GeneralPath)shape).lineTo(574.7219, 526.8029);
        ((GeneralPath)shape).lineTo(574.641, 527.216);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_543;
        g.setTransform(defaultTransform__0_543);
        g.setClip(clip__0_543);
        float alpha__0_544 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_544 = g.getClip();
        AffineTransform defaultTransform__0_544 = g.getTransform();
        
//      _0_544 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(567.191, 532.442);
        ((GeneralPath)shape).lineTo(566.045, 532.442);
        ((GeneralPath)shape).lineTo(566.957, 530.662);
        ((GeneralPath)shape).lineTo(566.96594, 530.662);
        ((GeneralPath)shape).lineTo(567.191, 532.442);
        ((GeneralPath)shape).moveTo(567.24, 532.924);
        ((GeneralPath)shape).lineTo(567.337, 533.543);
        ((GeneralPath)shape).lineTo(567.983, 533.543);
        ((GeneralPath)shape).lineTo(567.514, 530.17206);
        ((GeneralPath)shape).lineTo(566.576, 530.17206);
        ((GeneralPath)shape).lineTo(564.819, 533.543);
        ((GeneralPath)shape).lineTo(565.47894, 533.543);
        ((GeneralPath)shape).lineTo(565.80597, 532.924);
        ((GeneralPath)shape).lineTo(567.24, 532.924);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_544;
        g.setTransform(defaultTransform__0_544);
        g.setClip(clip__0_544);
        float alpha__0_545 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_545 = g.getClip();
        AffineTransform defaultTransform__0_545 = g.getTransform();
        
//      _0_545 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(568.601, 531.204);
        ((GeneralPath)shape).lineTo(568.146, 533.54297);
        ((GeneralPath)shape).lineTo(568.696, 533.54297);
        ((GeneralPath)shape).lineTo(568.97296, 532.12);
        ((GeneralPath)shape).curveTo(569.03094, 531.821, 569.17096, 531.632, 569.524, 531.632);
        ((GeneralPath)shape).curveTo(569.80096, 531.632, 569.798, 531.76904, 569.753, 532.00104);
        ((GeneralPath)shape).lineTo(569.731, 532.12006);
        ((GeneralPath)shape).lineTo(570.281, 532.12006);
        ((GeneralPath)shape).lineTo(570.317, 531.93304);
        ((GeneralPath)shape).curveTo(570.40204, 531.49506, 570.33203, 531.20404, 569.81, 531.20404);
        ((GeneralPath)shape).curveTo(569.524, 531.20404, 569.25397, 531.278, 569.088, 531.53204);
        ((GeneralPath)shape).lineTo(569.151, 531.20404);
        ((GeneralPath)shape).lineTo(568.601, 531.20404);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_545;
        g.setTransform(defaultTransform__0_545);
        g.setClip(clip__0_545);
        float alpha__0_546 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_546 = g.getClip();
        AffineTransform defaultTransform__0_546 = g.getTransform();
        
//      _0_546 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(570.818, 531.204);
        ((GeneralPath)shape).lineTo(570.363, 533.54297);
        ((GeneralPath)shape).lineTo(570.914, 533.54297);
        ((GeneralPath)shape).lineTo(571.167, 532.24194);
        ((GeneralPath)shape).curveTo(571.246, 531.8339, 571.37897, 531.63196, 571.85297, 531.63196);
        ((GeneralPath)shape).curveTo(572.17395, 531.63196, 572.227, 531.746, 572.17, 532.03595);
        ((GeneralPath)shape).lineTo(571.87695, 533.54297);
        ((GeneralPath)shape).lineTo(572.42694, 533.54297);
        ((GeneralPath)shape).lineTo(572.67993, 532.24194);
        ((GeneralPath)shape).curveTo(572.75995, 531.8339, 572.88495, 531.63196, 573.3259, 531.63196);
        ((GeneralPath)shape).curveTo(573.62494, 531.63196, 573.67194, 531.746, 573.6149, 532.03595);
        ((GeneralPath)shape).lineTo(573.3219, 533.54297);
        ((GeneralPath)shape).lineTo(573.8729, 533.54297);
        ((GeneralPath)shape).lineTo(574.17694, 531.98);
        ((GeneralPath)shape).curveTo(574.2869, 531.412, 574.1339, 531.20496, 573.5479, 531.20496);
        ((GeneralPath)shape).curveTo(573.2469, 531.20496, 572.9199, 531.29395, 572.7539, 531.61896);
        ((GeneralPath)shape).lineTo(572.7369, 531.61896);
        ((GeneralPath)shape).curveTo(572.7389, 531.27997, 572.4109, 531.20496, 572.11285, 531.20496);
        ((GeneralPath)shape).curveTo(571.8148, 531.20496, 571.4819, 531.26996, 571.2988, 531.566);
        ((GeneralPath)shape).lineTo(571.36884, 531.20496);
        ((GeneralPath)shape).lineTo(570.818, 531.20496);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_546;
        g.setTransform(defaultTransform__0_546);
        g.setClip(clip__0_546);
        float alpha__0_547 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_547 = g.getClip();
        AffineTransform defaultTransform__0_547 = g.getTransform();
        
//      _0_547 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(532.023, 536.004);
        ((GeneralPath)shape).lineTo(532.991, 536.004);
        ((GeneralPath)shape).lineTo(533.098, 535.456);
        ((GeneralPath)shape).lineTo(530.542, 535.456);
        ((GeneralPath)shape).lineTo(530.436, 536.004);
        ((GeneralPath)shape).lineTo(531.404, 536.004);
        ((GeneralPath)shape).lineTo(530.855, 538.826);
        ((GeneralPath)shape).lineTo(531.475, 538.826);
        ((GeneralPath)shape).lineTo(532.023, 536.004);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_547;
        g.setTransform(defaultTransform__0_547);
        g.setClip(clip__0_547);
        float alpha__0_548 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_548 = g.getClip();
        AffineTransform defaultTransform__0_548 = g.getTransform();
        
//      _0_548 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(533.412, 536.915);
        ((GeneralPath)shape).curveTo(533.889, 536.915, 533.89996, 537.05396, 533.781, 537.67096);
        ((GeneralPath)shape).curveTo(533.662, 538.27893, 533.597, 538.41296, 533.12, 538.41296);
        ((GeneralPath)shape).curveTo(532.643, 538.41296, 532.631, 538.279, 532.749, 537.67096);
        ((GeneralPath)shape).curveTo(532.869, 537.054, 532.935, 536.915, 533.412, 536.915);
        ((GeneralPath)shape).moveTo(533.495, 536.487);
        ((GeneralPath)shape).curveTo(532.549, 536.487, 532.374, 536.77, 532.201, 537.659);
        ((GeneralPath)shape).curveTo(532.029, 538.544, 532.095, 538.826, 533.04, 538.826);
        ((GeneralPath)shape).curveTo(533.98596, 538.826, 534.16095, 538.544, 534.333, 537.659);
        ((GeneralPath)shape).curveTo(534.506, 536.771, 534.44, 536.487, 533.495, 536.487);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_548;
        g.setTransform(defaultTransform__0_548);
        g.setClip(clip__0_548);
        float alpha__0_549 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_549 = g.getClip();
        AffineTransform defaultTransform__0_549 = g.getTransform();
        
//      _0_549 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(534.991, 536.487);
        ((GeneralPath)shape).lineTo(534.536, 538.826);
        ((GeneralPath)shape).lineTo(535.08704, 538.826);
        ((GeneralPath)shape).lineTo(535.36304, 537.403);
        ((GeneralPath)shape).curveTo(535.421, 537.104, 535.56104, 536.91504, 535.91406, 536.91504);
        ((GeneralPath)shape).curveTo(536.19104, 536.91504, 536.18805, 537.05206, 536.14307, 537.28406);
        ((GeneralPath)shape).lineTo(536.1211, 537.4031);
        ((GeneralPath)shape).lineTo(536.6711, 537.4031);
        ((GeneralPath)shape).lineTo(536.7071, 537.21606);
        ((GeneralPath)shape).curveTo(536.7921, 536.7781, 536.7231, 536.48706, 536.2001, 536.48706);
        ((GeneralPath)shape).curveTo(535.91406, 536.48706, 535.64404, 536.56104, 535.4781, 536.81506);
        ((GeneralPath)shape).lineTo(535.5411, 536.48706);
        ((GeneralPath)shape).lineTo(534.991, 536.48706);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_549;
        g.setTransform(defaultTransform__0_549);
        g.setClip(clip__0_549);
        float alpha__0_550 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_550 = g.getClip();
        AffineTransform defaultTransform__0_550 = g.getTransform();
        
//      _0_550 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(538.933, 537.138);
        ((GeneralPath)shape).curveTo(539.06, 536.488, 538.532, 536.488, 538.08997, 536.488);
        ((GeneralPath)shape).curveTo(537.511, 536.488, 537.06494, 536.488, 536.944, 537.113);
        ((GeneralPath)shape).curveTo(536.831, 537.693, 536.959, 537.805, 537.77997, 537.819);
        ((GeneralPath)shape).curveTo(538.305, 537.82697, 538.3, 537.923, 538.26196, 538.11395);
        ((GeneralPath)shape).curveTo(538.20294, 538.41394, 538.029, 538.41394, 537.71094, 538.41394);
        ((GeneralPath)shape).curveTo(537.3159, 538.41394, 537.2479, 538.37695, 537.3109, 538.05597);
        ((GeneralPath)shape).lineTo(536.7599, 538.05597);
        ((GeneralPath)shape).curveTo(536.6109, 538.82697, 537.0199, 538.82697, 537.6289, 538.82697);
        ((GeneralPath)shape).curveTo(538.1959, 538.82697, 538.6859, 538.76495, 538.8069, 538.14294);
        ((GeneralPath)shape).curveTo(538.9519, 537.39795, 538.4649, 537.4379, 537.9529, 537.41296);
        ((GeneralPath)shape).curveTo(537.49786, 537.39197, 537.4419, 537.38293, 537.4839, 537.16296);
        ((GeneralPath)shape).curveTo(537.5409, 536.87195, 537.7339, 536.87195, 538.0149, 536.87195);
        ((GeneralPath)shape).curveTo(538.2959, 536.87195, 538.4349, 536.87195, 538.3829, 537.139);
        ((GeneralPath)shape).lineTo(538.933, 537.139);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_550;
        g.setTransform(defaultTransform__0_550);
        g.setClip(clip__0_550);
        float alpha__0_551 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_551 = g.getClip();
        AffineTransform defaultTransform__0_551 = g.getTransform();
        
//      _0_551 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(540.5, 536.915);
        ((GeneralPath)shape).curveTo(540.977, 536.915, 540.988, 537.05396, 540.869, 537.67096);
        ((GeneralPath)shape).curveTo(540.75, 538.27893, 540.685, 538.41296, 540.208, 538.41296);
        ((GeneralPath)shape).curveTo(539.731, 538.41296, 539.719, 538.279, 539.83704, 537.67096);
        ((GeneralPath)shape).curveTo(539.957, 537.054, 540.022, 536.915, 540.5, 536.915);
        ((GeneralPath)shape).moveTo(540.583, 536.487);
        ((GeneralPath)shape).curveTo(539.637, 536.487, 539.46204, 536.77, 539.289, 537.659);
        ((GeneralPath)shape).curveTo(539.117, 538.544, 539.183, 538.826, 540.128, 538.826);
        ((GeneralPath)shape).curveTo(541.074, 538.826, 541.24896, 538.544, 541.421, 537.659);
        ((GeneralPath)shape).curveTo(541.594, 536.771, 541.528, 536.487, 540.583, 536.487);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_551;
        g.setTransform(defaultTransform__0_551);
        g.setClip(clip__0_551);
        float alpha__0_552 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_552 = g.getClip();
        AffineTransform defaultTransform__0_552 = g.getTransform();
        
//      _0_552 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(532.321, 541.619);
        ((GeneralPath)shape).lineTo(531.702, 541.619);
        ((GeneralPath)shape).lineTo(531.047, 544.99);
        ((GeneralPath)shape).lineTo(533.208, 544.99);
        ((GeneralPath)shape).lineTo(533.314, 544.441);
        ((GeneralPath)shape).lineTo(531.772, 544.441);
        ((GeneralPath)shape).lineTo(532.321, 541.619);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_552;
        g.setTransform(defaultTransform__0_552);
        g.setClip(clip__0_552);
        float alpha__0_553 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_553 = g.getClip();
        AffineTransform defaultTransform__0_553 = g.getTransform();
        
//      _0_553 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(534.283, 543.614);
        ((GeneralPath)shape).curveTo(534.366, 543.185, 534.418, 543.07904, 534.896, 543.07904);
        ((GeneralPath)shape).curveTo(535.349, 543.07904, 535.412, 543.11505, 535.315, 543.614);
        ((GeneralPath)shape).lineTo(534.283, 543.614);
        ((GeneralPath)shape).moveTo(535.19, 544.257);
        ((GeneralPath)shape).curveTo(535.127, 544.577, 534.913, 544.577, 534.605, 544.577);
        ((GeneralPath)shape).curveTo(534.109, 544.577, 534.125, 544.42804, 534.216, 543.958);
        ((GeneralPath)shape).lineTo(535.798, 543.958);
        ((GeneralPath)shape).curveTo(535.99695, 542.933, 535.925, 542.651, 534.98, 542.651);
        ((GeneralPath)shape).curveTo(534.05, 542.651, 533.85, 543.007, 533.691, 543.827);
        ((GeneralPath)shape).curveTo(533.519, 544.711, 533.64496, 544.99005, 534.52496, 544.99005);
        ((GeneralPath)shape).curveTo(535.18097, 544.99005, 535.60394, 544.9551, 535.74, 544.2571);
        ((GeneralPath)shape).lineTo(535.19, 544.2571);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_553;
        g.setTransform(defaultTransform__0_553);
        g.setClip(clip__0_553);
        float alpha__0_554 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_554 = g.getClip();
        AffineTransform defaultTransform__0_554 = g.getTransform();
        
//      _0_554 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(537.536, 545.099);
        ((GeneralPath)shape).curveTo(537.456, 545.509, 537.34, 545.61, 536.888, 545.61);
        ((GeneralPath)shape).curveTo(536.588, 545.61, 536.484, 545.558, 536.55, 545.217);
        ((GeneralPath)shape).lineTo(535.99896, 545.217);
        ((GeneralPath)shape).curveTo(535.82697, 545.923, 536.23, 546.022, 536.795, 546.022);
        ((GeneralPath)shape).curveTo(537.643, 546.022, 537.945, 545.823, 538.109, 544.982);
        ((GeneralPath)shape).lineTo(538.56104, 542.652);
        ((GeneralPath)shape).lineTo(538.01105, 542.652);
        ((GeneralPath)shape).lineTo(537.93805, 543.027);
        ((GeneralPath)shape).curveTo(537.85706, 542.722, 537.61505, 542.652, 537.28906, 542.652);
        ((GeneralPath)shape).curveTo(536.43005, 542.652, 536.3251, 543.18695, 536.18805, 543.889);
        ((GeneralPath)shape).curveTo(536.05804, 544.55896, 536.06604, 544.99097, 536.84607, 544.99097);
        ((GeneralPath)shape).curveTo(537.12305, 544.99097, 537.4231, 544.93896, 537.6241, 544.64197);
        ((GeneralPath)shape).lineTo(537.536, 545.099);
        ((GeneralPath)shape).moveTo(537.383, 543.079);
        ((GeneralPath)shape).curveTo(537.855, 543.079, 537.891, 543.274, 537.796, 543.76);
        ((GeneralPath)shape).curveTo(537.69104, 544.30304, 537.637, 544.577, 537.091, 544.577);
        ((GeneralPath)shape).curveTo(536.606, 544.577, 536.664, 544.27704, 536.764, 543.76);
        ((GeneralPath)shape).curveTo(536.875, 543.188, 537.005, 543.079, 537.383, 543.079);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_554;
        g.setTransform(defaultTransform__0_554);
        g.setClip(clip__0_554);
        float alpha__0_555 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_555 = g.getClip();
        AffineTransform defaultTransform__0_555 = g.getTransform();
        
//      _0_555 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(540.913, 543.301);
        ((GeneralPath)shape).curveTo(541.039, 542.65204, 540.512, 542.65204, 540.07, 542.65204);
        ((GeneralPath)shape).curveTo(539.49, 542.65204, 539.045, 542.65204, 538.92303, 543.27606);
        ((GeneralPath)shape).curveTo(538.81104, 543.8561, 538.93805, 543.96906, 539.76, 543.98206);
        ((GeneralPath)shape).curveTo(540.28503, 543.991, 540.28, 544.08606, 540.242, 544.2781);
        ((GeneralPath)shape).curveTo(540.183, 544.57806, 540.008, 544.57806, 539.691, 544.57806);
        ((GeneralPath)shape).curveTo(539.29596, 544.57806, 539.227, 544.54004, 539.29, 544.21906);
        ((GeneralPath)shape).lineTo(538.74, 544.21906);
        ((GeneralPath)shape).curveTo(538.58997, 544.991, 538.99896, 544.991, 539.608, 544.991);
        ((GeneralPath)shape).curveTo(540.17596, 544.991, 540.665, 544.929, 540.78595, 544.307);
        ((GeneralPath)shape).curveTo(540.93195, 543.562, 540.44495, 543.602, 539.93195, 543.576);
        ((GeneralPath)shape).curveTo(539.47797, 543.555, 539.42096, 543.54596, 539.4639, 543.32697);
        ((GeneralPath)shape).curveTo(539.52094, 543.03595, 539.7139, 543.03595, 539.99396, 543.03595);
        ((GeneralPath)shape).curveTo(540.27594, 543.03595, 540.41394, 543.03595, 540.36194, 543.30194);
        ((GeneralPath)shape).lineTo(540.913, 543.30194);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_555;
        g.setTransform(defaultTransform__0_555);
        g.setClip(clip__0_555);
        float alpha__0_556 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_556 = g.getClip();
        AffineTransform defaultTransform__0_556 = g.getTransform();
        
//      _0_556 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(534.421, 488.924);
        ((GeneralPath)shape).lineTo(533.964, 491.276);
        ((GeneralPath)shape).lineTo(534.476, 491.276);
        ((GeneralPath)shape).lineTo(535.018, 488.49);
        ((GeneralPath)shape).lineTo(534.167, 488.49);
        ((GeneralPath)shape).lineTo(533.028, 490.559);
        ((GeneralPath)shape).lineTo(533.014, 490.559);
        ((GeneralPath)shape).lineTo(532.664, 488.49);
        ((GeneralPath)shape).lineTo(531.833, 488.49);
        ((GeneralPath)shape).lineTo(531.291, 491.276);
        ((GeneralPath)shape).lineTo(531.803, 491.276);
        ((GeneralPath)shape).lineTo(532.258, 488.936);
        ((GeneralPath)shape).lineTo(532.646, 491.276);
        ((GeneralPath)shape).lineTo(533.121, 491.276);
        ((GeneralPath)shape).lineTo(534.421, 488.924);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_556;
        g.setTransform(defaultTransform__0_556);
        g.setClip(clip__0_556);
        float alpha__0_557 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_557 = g.getClip();
        AffineTransform defaultTransform__0_557 = g.getTransform();
        
//      _0_557 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(536.207, 491.276);
        ((GeneralPath)shape).lineTo(536.662, 491.276);
        ((GeneralPath)shape).lineTo(536.894, 490.078);
        ((GeneralPath)shape).curveTo(537.019, 489.437, 536.77997, 489.34302, 536.16, 489.34302);
        ((GeneralPath)shape).curveTo(535.719, 489.34302, 535.332, 489.34302, 535.222, 489.91);
        ((GeneralPath)shape).lineTo(535.67596, 489.91);
        ((GeneralPath)shape).curveTo(535.722, 489.677, 535.89197, 489.66, 536.09796, 489.66);
        ((GeneralPath)shape).curveTo(536.49396, 489.66, 536.49994, 489.768, 536.441, 490.074);
        ((GeneralPath)shape).lineTo(536.393, 490.319);
        ((GeneralPath)shape).lineTo(536.378, 490.319);
        ((GeneralPath)shape).curveTo(536.31396, 490.082, 536.084, 490.082, 535.849, 490.082);
        ((GeneralPath)shape).curveTo(535.373, 490.082, 535.107, 490.209, 535.018, 490.668);
        ((GeneralPath)shape).curveTo(534.917, 491.186, 535.18, 491.276, 535.614, 491.276);
        ((GeneralPath)shape).curveTo(535.83704, 491.276, 536.116, 491.276, 536.262, 490.994);
        ((GeneralPath)shape).lineTo(536.207, 491.276);
        ((GeneralPath)shape).moveTo(535.944, 490.423);
        ((GeneralPath)shape).curveTo(536.173, 490.423, 536.373, 490.423, 536.32495, 490.66602);
        ((GeneralPath)shape).curveTo(536.277, 490.915, 536.092, 490.93503, 535.845, 490.93503);
        ((GeneralPath)shape).curveTo(535.532, 490.93503, 535.425, 490.91302, 535.472, 490.66904);
        ((GeneralPath)shape).curveTo(535.52, 490.423, 535.708, 490.423, 535.944, 490.423);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_557;
        g.setTransform(defaultTransform__0_557);
        g.setClip(clip__0_557);
        float alpha__0_558 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_558 = g.getClip();
        AffineTransform defaultTransform__0_558 = g.getTransform();
        
//      _0_558 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(538.096, 488.49);
        ((GeneralPath)shape).lineTo(537.641, 488.49);
        ((GeneralPath)shape).lineTo(537.565, 488.88498);
        ((GeneralPath)shape).lineTo(538.02, 488.88498);
        ((GeneralPath)shape).lineTo(538.096, 488.49);
        ((GeneralPath)shape).moveTo(537.93, 489.343);
        ((GeneralPath)shape).lineTo(537.476, 489.343);
        ((GeneralPath)shape).lineTo(537.10004, 491.27698);
        ((GeneralPath)shape).lineTo(537.55505, 491.27698);
        ((GeneralPath)shape).lineTo(537.93, 489.343);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_558;
        g.setTransform(defaultTransform__0_558);
        g.setClip(clip__0_558);
        float alpha__0_559 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_559 = g.getClip();
        AffineTransform defaultTransform__0_559 = g.getTransform();
        
//      _0_559 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(538.334, 489.343);
        ((GeneralPath)shape).lineTo(537.958, 491.27698);
        ((GeneralPath)shape).lineTo(538.413, 491.27698);
        ((GeneralPath)shape).lineTo(538.617, 490.227);
        ((GeneralPath)shape).curveTo(538.683, 489.886, 538.773, 489.697, 539.166, 489.697);
        ((GeneralPath)shape).curveTo(539.456, 489.697, 539.498, 489.792, 539.44403, 490.067);
        ((GeneralPath)shape).lineTo(539.20905, 491.27698);
        ((GeneralPath)shape).lineTo(539.66406, 491.27698);
        ((GeneralPath)shape).lineTo(539.90906, 490.01697);
        ((GeneralPath)shape).curveTo(540.00006, 489.55096, 539.88904, 489.34296, 539.39404, 489.34296);
        ((GeneralPath)shape).curveTo(539.12805, 489.34296, 538.89703, 489.37195, 538.74304, 489.65295);
        ((GeneralPath)shape).lineTo(538.728, 489.65295);
        ((GeneralPath)shape).lineTo(538.789, 489.34296);
        ((GeneralPath)shape).lineTo(538.334, 489.34296);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_559;
        g.setTransform(defaultTransform__0_559);
        g.setClip(clip__0_559);
        float alpha__0_560 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_560 = g.getClip();
        AffineTransform defaultTransform__0_560 = g.getTransform();
        
//      _0_560 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(543.847, 489.798);
        ((GeneralPath)shape).lineTo(543.76996, 490.195);
        ((GeneralPath)shape).lineTo(544.48096, 490.195);
        ((GeneralPath)shape).lineTo(544.46094, 490.295);
        ((GeneralPath)shape).curveTo(544.3579, 490.821, 544.19995, 490.821, 543.68695, 490.821);
        ((GeneralPath)shape).curveTo(543.0839, 490.821, 543.00494, 490.76202, 543.11694, 490.191);
        ((GeneralPath)shape).lineTo(543.23895, 489.565);
        ((GeneralPath)shape).curveTo(543.342, 489.038, 543.42, 488.943, 544.05194, 488.943);
        ((GeneralPath)shape).curveTo(544.52295, 488.943, 544.7249, 488.943, 544.6469, 489.346);
        ((GeneralPath)shape).lineTo(545.15894, 489.346);
        ((GeneralPath)shape).curveTo(545.3319, 488.456, 544.82794, 488.49002, 544.1409, 488.49002);
        ((GeneralPath)shape).curveTo(543.29395, 488.49002, 542.90594, 488.65002, 542.7279, 489.56403);
        ((GeneralPath)shape).lineTo(542.6059, 490.19403);
        ((GeneralPath)shape).curveTo(542.42487, 491.12204, 542.7239, 491.27603, 543.60187, 491.27603);
        ((GeneralPath)shape).curveTo(544.57886, 491.27603, 544.81586, 491.10803, 544.9739, 490.29504);
        ((GeneralPath)shape).lineTo(545.07086, 489.79803);
        ((GeneralPath)shape).lineTo(543.847, 489.79803);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_560;
        g.setTransform(defaultTransform__0_560);
        g.setClip(clip__0_560);
        float alpha__0_561 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_561 = g.getClip();
        AffineTransform defaultTransform__0_561 = g.getTransform();
        
//      _0_561 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(546.859, 491.276);
        ((GeneralPath)shape).lineTo(547.234, 489.342);
        ((GeneralPath)shape).lineTo(546.779, 489.342);
        ((GeneralPath)shape).lineTo(546.56396, 490.45);
        ((GeneralPath)shape).curveTo(546.49994, 490.78403, 546.355, 490.933, 545.998, 490.933);
        ((GeneralPath)shape).curveTo(545.7, 490.933, 545.694, 490.828, 545.748, 490.553);
        ((GeneralPath)shape).lineTo(545.983, 489.341);
        ((GeneralPath)shape).lineTo(545.529, 489.341);
        ((GeneralPath)shape).lineTo(545.259, 490.725);
        ((GeneralPath)shape).curveTo(545.178, 491.143, 545.409, 491.275, 545.79395, 491.275);
        ((GeneralPath)shape).curveTo(546.05896, 491.275, 546.30896, 491.214, 546.46295, 490.96698);
        ((GeneralPath)shape).lineTo(546.40295, 491.275);
        ((GeneralPath)shape).lineTo(546.859, 491.275);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_561;
        g.setTransform(defaultTransform__0_561);
        g.setClip(clip__0_561);
        float alpha__0_562 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_562 = g.getClip();
        AffineTransform defaultTransform__0_562 = g.getTransform();
        
//      _0_562 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(547.687, 489.343);
        ((GeneralPath)shape).lineTo(547.312, 491.27698);
        ((GeneralPath)shape).lineTo(547.766, 491.27698);
        ((GeneralPath)shape).lineTo(547.97, 490.227);
        ((GeneralPath)shape).curveTo(548.03595, 489.886, 548.126, 489.697, 548.51996, 489.697);
        ((GeneralPath)shape).curveTo(548.80896, 489.697, 548.85095, 489.792, 548.79694, 490.067);
        ((GeneralPath)shape).lineTo(548.5629, 491.27698);
        ((GeneralPath)shape).lineTo(549.0169, 491.27698);
        ((GeneralPath)shape).lineTo(549.2619, 490.01697);
        ((GeneralPath)shape).curveTo(549.3529, 489.55096, 549.2429, 489.34296, 548.7479, 489.34296);
        ((GeneralPath)shape).curveTo(548.48193, 489.34296, 548.2509, 489.37195, 548.09595, 489.65295);
        ((GeneralPath)shape).lineTo(548.082, 489.65295);
        ((GeneralPath)shape).lineTo(548.14197, 489.34296);
        ((GeneralPath)shape).lineTo(547.687, 489.34296);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_562;
        g.setTransform(defaultTransform__0_562);
        g.setClip(clip__0_562);
        float alpha__0_563 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_563 = g.getClip();
        AffineTransform defaultTransform__0_563 = g.getTransform();
        
//      _0_563 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(545.22, 497.396);
        ((GeneralPath)shape).lineTo(544.601, 497.396);
        ((GeneralPath)shape).lineTo(544.333, 498.772);
        ((GeneralPath)shape).lineTo(542.751, 498.772);
        ((GeneralPath)shape).lineTo(543.019, 497.396);
        ((GeneralPath)shape).lineTo(542.399, 497.396);
        ((GeneralPath)shape).lineTo(541.744, 500.768);
        ((GeneralPath)shape).lineTo(542.363, 500.768);
        ((GeneralPath)shape).lineTo(542.644, 499.322);
        ((GeneralPath)shape).lineTo(544.226, 499.322);
        ((GeneralPath)shape).lineTo(543.945, 500.768);
        ((GeneralPath)shape).lineTo(544.564, 500.768);
        ((GeneralPath)shape).lineTo(545.22, 497.396);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_563;
        g.setTransform(defaultTransform__0_563);
        g.setClip(clip__0_563);
        float alpha__0_564 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_564 = g.getClip();
        AffineTransform defaultTransform__0_564 = g.getTransform();
        
//      _0_564 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(545.879, 499.392);
        ((GeneralPath)shape).curveTo(545.96204, 498.963, 546.01404, 498.857, 546.492, 498.857);
        ((GeneralPath)shape).curveTo(546.94403, 498.857, 547.008, 498.893, 546.91003, 499.392);
        ((GeneralPath)shape).lineTo(545.879, 499.392);
        ((GeneralPath)shape).moveTo(546.785, 500.034);
        ((GeneralPath)shape).curveTo(546.72296, 500.354, 546.509, 500.354, 546.201, 500.354);
        ((GeneralPath)shape).curveTo(545.704, 500.354, 545.721, 500.20502, 545.811, 499.73502);
        ((GeneralPath)shape).lineTo(547.39294, 499.73502);
        ((GeneralPath)shape).curveTo(547.59296, 498.71002, 547.52094, 498.428, 546.57495, 498.428);
        ((GeneralPath)shape).curveTo(545.64594, 498.428, 545.4459, 498.784, 545.2869, 499.604);
        ((GeneralPath)shape).curveTo(545.1149, 500.488, 545.2399, 500.767, 546.1209, 500.767);
        ((GeneralPath)shape).curveTo(546.7769, 500.767, 547.1999, 500.732, 547.33594, 500.034);
        ((GeneralPath)shape).lineTo(546.785, 500.034);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_564;
        g.setTransform(defaultTransform__0_564);
        g.setClip(clip__0_564);
        float alpha__0_565 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_565 = g.getClip();
        AffineTransform defaultTransform__0_565 = g.getTransform();
        
//      _0_565 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(549.152, 500.768);
        ((GeneralPath)shape).lineTo(549.70197, 500.768);
        ((GeneralPath)shape).lineTo(549.98395, 499.319);
        ((GeneralPath)shape).curveTo(550.134, 498.544, 549.845, 498.429, 549.09595, 498.429);
        ((GeneralPath)shape).curveTo(548.561, 498.429, 548.09296, 498.429, 547.9589, 499.116);
        ((GeneralPath)shape).lineTo(548.50995, 499.116);
        ((GeneralPath)shape).curveTo(548.56494, 498.833, 548.76996, 498.812, 549.01996, 498.812);
        ((GeneralPath)shape).curveTo(549.49896, 498.812, 549.506, 498.944, 549.43396, 499.31302);
        ((GeneralPath)shape).lineTo(549.376, 499.61002);
        ((GeneralPath)shape).lineTo(549.358, 499.61002);
        ((GeneralPath)shape).curveTo(549.27997, 499.32303, 549.00195, 499.32303, 548.71796, 499.32303);
        ((GeneralPath)shape).curveTo(548.14197, 499.32303, 547.81995, 499.47702, 547.71094, 500.03302);
        ((GeneralPath)shape).curveTo(547.58997, 500.65802, 547.90796, 500.768, 548.4329, 500.768);
        ((GeneralPath)shape).curveTo(548.7019, 500.768, 549.0399, 500.768, 549.2169, 500.427);
        ((GeneralPath)shape).lineTo(549.152, 500.768);
        ((GeneralPath)shape).moveTo(548.835, 499.735);
        ((GeneralPath)shape).curveTo(549.112, 499.735, 549.353, 499.735, 549.296, 500.02798);
        ((GeneralPath)shape).curveTo(549.237, 500.331, 549.01404, 500.35397, 548.715, 500.35397);
        ((GeneralPath)shape).curveTo(548.335, 500.35397, 548.20605, 500.32596, 548.26404, 500.03296);
        ((GeneralPath)shape).curveTo(548.321, 499.735, 548.549, 499.735, 548.835, 499.735);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_565;
        g.setTransform(defaultTransform__0_565);
        g.setClip(clip__0_565);
        float alpha__0_566 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_566 = g.getClip();
        AffineTransform defaultTransform__0_566 = g.getTransform();
        
//      _0_566 is ShapeNode
        paint = new Color(35, 31, 32, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(551.745, 500.768);
        ((GeneralPath)shape).lineTo(552.296, 500.768);
        ((GeneralPath)shape).lineTo(552.95105, 497.397);
        ((GeneralPath)shape).lineTo(552.4, 497.397);
        ((GeneralPath)shape).lineTo(552.133, 498.767);
        ((GeneralPath)shape).lineTo(552.12, 498.767);
        ((GeneralPath)shape).curveTo(552.057, 498.483, 551.775, 498.429, 551.495, 498.429);
        ((GeneralPath)shape).curveTo(550.708, 498.429, 550.533, 498.86, 550.402, 499.53098);
        ((GeneralPath)shape).curveTo(550.266, 500.23297, 550.162, 500.76797, 551.022, 500.76797);
        ((GeneralPath)shape).curveTo(551.347, 500.76797, 551.61597, 500.69998, 551.81396, 500.40598);
        ((GeneralPath)shape).lineTo(551.745, 500.768);
        ((GeneralPath)shape).moveTo(551.575, 498.856);
        ((GeneralPath)shape).curveTo(552.052, 498.856, 552.07904, 499.051, 551.984, 499.537);
        ((GeneralPath)shape).curveTo(551.879, 500.08, 551.825, 500.35397, 551.279, 500.35397);
        ((GeneralPath)shape).curveTo(550.794, 500.35397, 550.851, 500.054, 550.95197, 499.537);
        ((GeneralPath)shape).curveTo(551.063, 498.965, 551.194, 498.856, 551.575, 498.856);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_566;
        g.setTransform(defaultTransform__0_566);
        g.setClip(clip__0_566);
        float alpha__0_567 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_567 = g.getClip();
        AffineTransform defaultTransform__0_567 = g.getTransform();
        
//      _0_567 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(40.925, 478.36);
        ((GeneralPath)shape).lineTo(42.649998, 478.36);
        ((GeneralPath)shape).curveTo(43.334, 478.36, 43.769997, 478.28098, 43.956997, 478.12198);
        ((GeneralPath)shape).curveTo(44.140995, 477.96497, 44.234997, 477.59198, 44.234997, 476.99997);
        ((GeneralPath)shape).curveTo(44.234997, 476.32596, 44.157997, 475.90698, 44.003998, 475.74298);
        ((GeneralPath)shape).curveTo(43.851997, 475.58197, 43.450996, 475.49997, 42.802, 475.49997);
        ((GeneralPath)shape).lineTo(40.925, 475.49997);
        ((GeneralPath)shape).lineTo(40.925, 478.36);
        ((GeneralPath)shape).moveTo(39.41, 482.221);
        ((GeneralPath)shape).lineTo(39.41, 474.22302);
        ((GeneralPath)shape).lineTo(43.024, 474.22302);
        ((GeneralPath)shape).curveTo(44.102997, 474.22302, 44.830997, 474.411, 45.209, 474.78903);
        ((GeneralPath)shape).curveTo(45.583, 475.16403, 45.773, 475.89304, 45.773, 476.97104);
        ((GeneralPath)shape).curveTo(45.773, 478.04004, 45.594997, 478.75204, 45.235, 479.10403);
        ((GeneralPath)shape).curveTo(44.879, 479.45602, 44.153, 479.63104, 43.06, 479.63104);
        ((GeneralPath)shape).lineTo(42.709, 479.63803);
        ((GeneralPath)shape).lineTo(40.926, 479.63803);
        ((GeneralPath)shape).lineTo(40.926, 482.22104);
        ((GeneralPath)shape).lineTo(39.41, 482.22104);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_567;
        g.setTransform(defaultTransform__0_567);
        g.setClip(clip__0_567);
        float alpha__0_568 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_568 = g.getClip();
        AffineTransform defaultTransform__0_568 = g.getTransform();
        
//      _0_568 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(48.255, 478.248);
        ((GeneralPath)shape).lineTo(50.43, 478.248);
        ((GeneralPath)shape).curveTo(50.948, 478.248, 51.292, 478.15698, 51.462, 477.96997);
        ((GeneralPath)shape).curveTo(51.631, 477.78497, 51.716003, 477.41397, 51.716003, 476.85397);
        ((GeneralPath)shape).curveTo(51.716003, 476.28598, 51.643, 475.916, 51.497, 475.74997);
        ((GeneralPath)shape).curveTo(51.351, 475.58597, 51.032, 475.49997, 50.535, 475.49997);
        ((GeneralPath)shape).lineTo(48.255, 475.49997);
        ((GeneralPath)shape).lineTo(48.255, 478.248);
        ((GeneralPath)shape).moveTo(46.74, 482.221);
        ((GeneralPath)shape).lineTo(46.74, 474.22302);
        ((GeneralPath)shape).lineTo(50.676003, 474.22302);
        ((GeneralPath)shape).curveTo(51.653004, 474.22302, 52.328003, 474.39304, 52.699, 474.73303);
        ((GeneralPath)shape).curveTo(53.068, 475.07303, 53.255, 475.68802, 53.255, 476.57904);
        ((GeneralPath)shape).curveTo(53.255, 477.38904, 53.164, 477.93805, 52.977, 478.23703);
        ((GeneralPath)shape).curveTo(52.793003, 478.53302, 52.413002, 478.73904, 51.84, 478.85202);
        ((GeneralPath)shape).lineTo(51.84, 478.90503);
        ((GeneralPath)shape).curveTo(52.723, 478.95804, 53.168, 479.47702, 53.168, 480.45804);
        ((GeneralPath)shape).lineTo(53.168, 482.22205);
        ((GeneralPath)shape).lineTo(51.653, 482.22205);
        ((GeneralPath)shape).lineTo(51.653, 480.76404);
        ((GeneralPath)shape).curveTo(51.653, 479.94003, 51.25, 479.52704, 50.437, 479.52704);
        ((GeneralPath)shape).lineTo(48.256, 479.52704);
        ((GeneralPath)shape).lineTo(48.256, 482.22205);
        ((GeneralPath)shape).lineTo(46.74, 482.22205);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_568;
        g.setTransform(defaultTransform__0_568);
        g.setClip(clip__0_568);
        float alpha__0_569 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_569 = g.getClip();
        AffineTransform defaultTransform__0_569 = g.getTransform();
        
//      _0_569 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(57.653, 475.453);
        ((GeneralPath)shape).curveTo(56.635, 475.453, 56.042, 475.582, 55.869, 475.846);
        ((GeneralPath)shape).curveTo(55.7, 476.107, 55.612, 477.013, 55.612, 478.565);
        ((GeneralPath)shape).curveTo(55.612, 479.713, 55.711, 480.405, 55.913, 480.639);
        ((GeneralPath)shape).curveTo(56.112, 480.87302, 56.704998, 480.991, 57.686996, 480.991);
        ((GeneralPath)shape).curveTo(58.625996, 480.991, 59.189995, 480.86, 59.376995, 480.595);
        ((GeneralPath)shape).curveTo(59.563995, 480.332, 59.657993, 479.532, 59.657993, 478.197);
        ((GeneralPath)shape).curveTo(59.657993, 476.857, 59.57099, 476.067, 59.391994, 475.82098);
        ((GeneralPath)shape).curveTo(59.217, 475.576, 58.635, 475.453, 57.653, 475.453);
        ((GeneralPath)shape).moveTo(57.77, 474.153);
        ((GeneralPath)shape).curveTo(59.221, 474.153, 60.153, 474.39203, 60.571, 474.876);
        ((GeneralPath)shape).curveTo(60.987, 475.35602, 61.196, 476.43802, 61.196, 478.114);
        ((GeneralPath)shape).curveTo(61.196, 479.945, 60.989, 481.09802, 60.568, 481.57602);
        ((GeneralPath)shape).curveTo(60.15, 482.05203, 59.135002, 482.29102, 57.524002, 482.29102);
        ((GeneralPath)shape).curveTo(56.074, 482.29102, 55.138, 482.057, 54.711002, 481.58603);
        ((GeneralPath)shape).curveTo(54.287003, 481.11703, 54.073, 480.07904, 54.073, 478.47702);
        ((GeneralPath)shape).curveTo(54.073, 476.57303, 54.281002, 475.37503, 54.699, 474.885);
        ((GeneralPath)shape).curveTo(55.115, 474.398, 56.138, 474.153, 57.77, 474.153);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_569;
        g.setTransform(defaultTransform__0_569);
        g.setClip(clip__0_569);
        float alpha__0_570 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_570 = g.getClip();
        AffineTransform defaultTransform__0_570 = g.getTransform();
        
//      _0_570 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(65.396, 475.582);
        ((GeneralPath)shape).lineTo(65.396, 482.221);
        ((GeneralPath)shape).lineTo(63.882, 482.221);
        ((GeneralPath)shape).lineTo(63.882, 475.582);
        ((GeneralPath)shape).lineTo(61.577, 475.582);
        ((GeneralPath)shape).lineTo(61.577, 474.223);
        ((GeneralPath)shape).lineTo(67.782, 474.223);
        ((GeneralPath)shape).lineTo(67.782, 475.582);
        ((GeneralPath)shape).lineTo(65.396, 475.582);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_570;
        g.setTransform(defaultTransform__0_570);
        g.setClip(clip__0_570);
        float alpha__0_571 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_571 = g.getClip();
        AffineTransform defaultTransform__0_571 = g.getTransform();
        
//      _0_571 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(71.75, 475.453);
        ((GeneralPath)shape).curveTo(70.732, 475.453, 70.139, 475.582, 69.966, 475.846);
        ((GeneralPath)shape).curveTo(69.797005, 476.107, 69.709, 477.013, 69.709, 478.565);
        ((GeneralPath)shape).curveTo(69.709, 479.713, 69.808, 480.405, 70.01, 480.639);
        ((GeneralPath)shape).curveTo(70.209, 480.87302, 70.802, 480.991, 71.784004, 480.991);
        ((GeneralPath)shape).curveTo(72.72301, 480.991, 73.287, 480.86, 73.47401, 480.595);
        ((GeneralPath)shape).curveTo(73.661, 480.332, 73.755005, 479.532, 73.755005, 478.197);
        ((GeneralPath)shape).curveTo(73.755005, 476.857, 73.66801, 476.067, 73.489006, 475.82098);
        ((GeneralPath)shape).curveTo(73.313, 475.576, 72.732, 475.453, 71.75, 475.453);
        ((GeneralPath)shape).moveTo(71.866, 474.153);
        ((GeneralPath)shape).curveTo(73.31699, 474.153, 74.249, 474.39203, 74.667, 474.876);
        ((GeneralPath)shape).curveTo(75.083, 475.35602, 75.292, 476.43802, 75.292, 478.114);
        ((GeneralPath)shape).curveTo(75.292, 479.945, 75.085, 481.09802, 74.664, 481.57602);
        ((GeneralPath)shape).curveTo(74.246, 482.05203, 73.231, 482.29102, 71.62, 482.29102);
        ((GeneralPath)shape).curveTo(70.170006, 482.29102, 69.234, 482.057, 68.807, 481.58603);
        ((GeneralPath)shape).curveTo(68.382996, 481.11703, 68.169, 480.07904, 68.169, 478.47702);
        ((GeneralPath)shape).curveTo(68.169, 476.57303, 68.377, 475.37503, 68.795, 474.885);
        ((GeneralPath)shape).curveTo(69.211, 474.398, 70.235, 474.153, 71.866, 474.153);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_571;
        g.setTransform(defaultTransform__0_571);
        g.setClip(clip__0_571);
        float alpha__0_572 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_572 = g.getClip();
        AffineTransform defaultTransform__0_572 = g.getTransform();
        
//      _0_572 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(85.646, 474.223);
        ((GeneralPath)shape).lineTo(85.646, 482.22098);
        ((GeneralPath)shape).lineTo(84.132, 482.22098);
        ((GeneralPath)shape).lineTo(84.132, 477.86197);
        ((GeneralPath)shape).curveTo(84.132, 477.51596, 84.14001, 477.12198, 84.161, 476.67798);
        ((GeneralPath)shape).lineTo(84.191, 476.08);
        ((GeneralPath)shape).lineTo(84.22, 475.48898);
        ((GeneralPath)shape).lineTo(84.173004, 475.48898);
        ((GeneralPath)shape).lineTo(83.991005, 476.04498);
        ((GeneralPath)shape).lineTo(83.816, 476.602);
        ((GeneralPath)shape).curveTo(83.652, 477.09998, 83.526, 477.47, 83.436005, 477.70898);
        ((GeneralPath)shape).lineTo(81.68201, 482.22098);
        ((GeneralPath)shape).lineTo(80.3, 482.22098);
        ((GeneralPath)shape).lineTo(78.528, 477.744);
        ((GeneralPath)shape).curveTo(78.431, 477.498, 78.303, 477.12997, 78.142, 476.638);
        ((GeneralPath)shape).lineTo(77.961, 476.08);
        ((GeneralPath)shape).lineTo(77.779, 475.529);
        ((GeneralPath)shape).lineTo(77.732, 475.529);
        ((GeneralPath)shape).lineTo(77.761, 476.11);
        ((GeneralPath)shape).lineTo(77.791, 476.69598);
        ((GeneralPath)shape).curveTo(77.814, 477.146, 77.826004, 477.53598, 77.826004, 477.861);
        ((GeneralPath)shape).lineTo(77.826004, 482.22);
        ((GeneralPath)shape).lineTo(76.311005, 482.22);
        ((GeneralPath)shape).lineTo(76.311005, 474.22202);
        ((GeneralPath)shape).lineTo(78.77901, 474.22202);
        ((GeneralPath)shape).lineTo(80.20601, 477.92603);
        ((GeneralPath)shape).curveTo(80.30201, 478.183, 80.43101, 478.55203, 80.59201, 479.032);
        ((GeneralPath)shape).lineTo(80.76801, 479.59003);
        ((GeneralPath)shape).lineTo(80.94901, 480.14);
        ((GeneralPath)shape).lineTo(81.002014, 480.14);
        ((GeneralPath)shape).lineTo(81.17101, 479.59003);
        ((GeneralPath)shape).lineTo(81.347015, 479.03802);
        ((GeneralPath)shape).curveTo(81.49001, 478.575, 81.61601, 478.20602, 81.721016, 477.93604);
        ((GeneralPath)shape).lineTo(83.124016, 474.22104);
        ((GeneralPath)shape).lineTo(85.646, 474.22104);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_572;
        g.setTransform(defaultTransform__0_572);
        g.setClip(clip__0_572);
        float alpha__0_573 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_573 = g.getClip();
        AffineTransform defaultTransform__0_573 = g.getTransform();
        
//      _0_573 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(88.412, 475.5);
        ((GeneralPath)shape).lineTo(88.412, 477.563);
        ((GeneralPath)shape).lineTo(92.155, 477.563);
        ((GeneralPath)shape).lineTo(92.155, 478.682);
        ((GeneralPath)shape).lineTo(88.412, 478.682);
        ((GeneralPath)shape).lineTo(88.412, 480.943);
        ((GeneralPath)shape).lineTo(92.395, 480.943);
        ((GeneralPath)shape).lineTo(92.395, 482.221);
        ((GeneralPath)shape).lineTo(86.897, 482.221);
        ((GeneralPath)shape).lineTo(86.897, 474.223);
        ((GeneralPath)shape).lineTo(92.36, 474.223);
        ((GeneralPath)shape).lineTo(92.36, 475.5);
        ((GeneralPath)shape).lineTo(88.412, 475.5);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_573;
        g.setTransform(defaultTransform__0_573);
        g.setClip(clip__0_573);
        float alpha__0_574 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_574 = g.getClip();
        AffineTransform defaultTransform__0_574 = g.getTransform();
        
//      _0_574 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(98.453, 479.367);
        ((GeneralPath)shape).lineTo(99.97401, 479.367);
        ((GeneralPath)shape).lineTo(99.97401, 479.642);
        ((GeneralPath)shape).curveTo(99.97401, 480.755, 99.772, 481.473, 99.366005, 481.801);
        ((GeneralPath)shape).curveTo(98.962006, 482.126, 98.064, 482.29, 96.676, 482.29);
        ((GeneralPath)shape).curveTo(95.103004, 482.29, 94.132, 482.032, 93.769005, 481.517);
        ((GeneralPath)shape).curveTo(93.407005, 481.001, 93.225006, 479.624, 93.225006, 477.38);
        ((GeneralPath)shape).curveTo(93.225006, 476.062, 93.47101, 475.19202, 93.962006, 474.776);
        ((GeneralPath)shape).curveTo(94.453, 474.359, 95.479004, 474.152, 97.04401, 474.152);
        ((GeneralPath)shape).curveTo(98.18101, 474.152, 98.94101, 474.321, 99.325005, 474.664);
        ((GeneralPath)shape).curveTo(99.705, 475.004, 99.898, 475.684, 99.898, 476.7);
        ((GeneralPath)shape).lineTo(99.904, 476.88303);
        ((GeneralPath)shape).lineTo(98.382996, 476.88303);
        ((GeneralPath)shape).lineTo(98.382996, 476.67703);
        ((GeneralPath)shape).curveTo(98.382996, 476.15604, 98.285995, 475.82004, 98.088, 475.67203);
        ((GeneralPath)shape).curveTo(97.893, 475.52603, 97.44199, 475.45203, 96.74, 475.45203);
        ((GeneralPath)shape).curveTo(95.801994, 475.45203, 95.237, 475.56604, 95.047, 475.79803);
        ((GeneralPath)shape).curveTo(94.86, 476.02704, 94.763, 476.71204, 94.763, 477.84903);
        ((GeneralPath)shape).curveTo(94.763, 479.37903, 94.848, 480.28702, 95.018, 480.56802);
        ((GeneralPath)shape).curveTo(95.187, 480.84903, 95.734, 480.99002, 96.664, 480.99002);
        ((GeneralPath)shape).curveTo(97.415, 480.99002, 97.903, 480.91403, 98.129, 480.756);
        ((GeneralPath)shape).curveTo(98.351, 480.60202, 98.465, 480.259, 98.465, 479.725);
        ((GeneralPath)shape).lineTo(98.453, 479.367);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_574;
        g.setTransform(defaultTransform__0_574);
        g.setClip(clip__0_574);
        float alpha__0_575 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_575 = g.getClip();
        AffineTransform defaultTransform__0_575 = g.getTransform();
        
//      _0_575 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(107.697, 474.223);
        ((GeneralPath)shape).lineTo(107.697, 482.221);
        ((GeneralPath)shape).lineTo(106.183, 482.221);
        ((GeneralPath)shape).lineTo(106.183, 478.764);
        ((GeneralPath)shape).lineTo(102.439, 478.764);
        ((GeneralPath)shape).lineTo(102.439, 482.221);
        ((GeneralPath)shape).lineTo(100.925, 482.221);
        ((GeneralPath)shape).lineTo(100.925, 474.223);
        ((GeneralPath)shape).lineTo(102.439, 474.223);
        ((GeneralPath)shape).lineTo(102.439, 477.486);
        ((GeneralPath)shape).lineTo(106.183, 477.486);
        ((GeneralPath)shape).lineTo(106.183, 474.223);
        ((GeneralPath)shape).lineTo(107.697, 474.223);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_575;
        g.setTransform(defaultTransform__0_575);
        g.setClip(clip__0_575);
        float alpha__0_576 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_576 = g.getClip();
        AffineTransform defaultTransform__0_576 = g.getTransform();
        
//      _0_576 is ShapeNode
        paint = new Color(255, 255, 255, 255);
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(116.018, 479.332);
        ((GeneralPath)shape).lineTo(116.018, 475.371);
        ((GeneralPath)shape).lineTo(115.971, 475.371);
        ((GeneralPath)shape).lineTo(112.948, 479.332);
        ((GeneralPath)shape).lineTo(116.018, 479.332);
        ((GeneralPath)shape).moveTo(117.532, 474.223);
        ((GeneralPath)shape).lineTo(117.532, 479.332);
        ((GeneralPath)shape).lineTo(118.45, 479.332);
        ((GeneralPath)shape).lineTo(118.45, 480.505);
        ((GeneralPath)shape).lineTo(117.532, 480.505);
        ((GeneralPath)shape).lineTo(117.532, 482.221);
        ((GeneralPath)shape).lineTo(116.018, 482.221);
        ((GeneralPath)shape).lineTo(116.018, 480.505);
        ((GeneralPath)shape).lineTo(111.708, 480.505);
        ((GeneralPath)shape).lineTo(111.708, 478.799);
        ((GeneralPath)shape).lineTo(115.193, 474.22302);
        ((GeneralPath)shape).lineTo(117.532, 474.22302);
        g.setPaint(paint);
        g.fill(shape);
        origAlpha = alpha__0_576;
        g.setTransform(defaultTransform__0_576);
        g.setClip(clip__0_576);
        float alpha__0_577 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_577 = g.getClip();
        AffineTransform defaultTransform__0_577 = g.getTransform();
        
//      _0_577 is CompositeGraphicsNode
        origAlpha = alpha__0_577;
        g.setTransform(defaultTransform__0_577);
        g.setClip(clip__0_577);
        float alpha__0_578 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_578 = g.getClip();
        AffineTransform defaultTransform__0_578 = g.getTransform();
        
//      _0_578 is CompositeGraphicsNode
        origAlpha = alpha__0_578;
        g.setTransform(defaultTransform__0_578);
        g.setClip(clip__0_578);
        float alpha__0_579 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_579 = g.getClip();
        AffineTransform defaultTransform__0_579 = g.getTransform();
        
//      _0_579 is CompositeGraphicsNode
        origAlpha = alpha__0_579;
        g.setTransform(defaultTransform__0_579);
        g.setClip(clip__0_579);
        float alpha__0_580 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_580 = g.getClip();
        AffineTransform defaultTransform__0_580 = g.getTransform();
        
//      _0_580 is CompositeGraphicsNode
        origAlpha = alpha__0_580;
        g.setTransform(defaultTransform__0_580);
        g.setClip(clip__0_580);
        float alpha__0_581 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_581 = g.getClip();
        AffineTransform defaultTransform__0_581 = g.getTransform();
        
//      _0_581 is CompositeGraphicsNode
        origAlpha = alpha__0_581;
        g.setTransform(defaultTransform__0_581);
        g.setClip(clip__0_581);
        float alpha__0_582 = origAlpha;
        
        g.setComposite(AlphaComposite.getInstance(3, origAlpha));
        Shape clip__0_582 = g.getClip();
        AffineTransform defaultTransform__0_582 = g.getTransform();
        
//      _0_582 is CompositeGraphicsNode
        origAlpha = alpha__0_582;
        g.setTransform(defaultTransform__0_582);
        g.setClip(clip__0_582);
        origAlpha = alpha__0;
        g.setTransform(defaultTransform__0);
        g.setClip(clip__0);
        g.setTransform(defaultTransform_);
        g.setClip(clip_);
	}
    /**
     /* Returns the X of the bounding box of the original SVG image.
     * 
     * @return The X of the bounding box of the original SVG image.
     */
    public static int getOrigX() {
        return 29;
    }    /**
     * Returns the Y of the bounding box of the original SVG image.
     * 
     * @return The Y of the bounding box of the original SVG image.
     */
    public static int getOrigY() {
        return 468;
    }    /**
     * Returns the width of the bounding box of the original SVG image.
     * 
     * @return The width of the bounding box of the original SVG image.
     */
    public static int getOrigWidth() {
        return 612;
    }    /**
     * Returns the height of the bounding box of the original SVG image.
     * 
     * @return The height of the bounding box of the original SVG image.
     */
    public static int getOrigHeight() {
        return 792;
    }


}