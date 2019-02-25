/*
 * MegaMekLab - Copyright (C) 2017 - The MegaMek Team
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
package megameklab.com.util;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Rectangle2D;
import java.io.ByteArrayInputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;

import com.kitfox.svg.Path;
import com.kitfox.svg.SVGDiagram;
import com.kitfox.svg.SVGElement;
import com.kitfox.svg.SVGUniverse;

/**
 * Utility to generate svg code that defines rows for armor or structure pips that will fit in a region,
 * given the svg code for the path.
 * 
 * @author Neoancient
 *
 */
public class PipLayoutHelper {

    static class MainFrame extends JFrame {
        
        /**
         * 
         */
        private static final long serialVersionUID = -8470193698981242677L;
        
        private JTextArea txtRegionDef = new JTextArea();
        private JTextArea txtGeneratedCode = new JTextArea();
        private JTextField txtHeight = new JTextField(6);
        private JTextField txtAngle = new JTextField(6);
        private JCheckBox chkMirror = new JCheckBox("Mirror");
        private JButton btnGenerate = new JButton("Generate");
        
        public MainFrame() {
            super();
            setTitle("Pip Layout Helper");
            
            initUI();
            setSize(new Dimension(800,600));
            
            setVisible(true);
        }
        
        private void initUI() {
            Container pane = getContentPane();
            pane.setLayout(new GridBagLayout());
            
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.fill = GridBagConstraints.NONE;
            gbc.anchor = GridBagConstraints.WEST;
            
            pane.add(new JLabel("Height: "), gbc);
            gbc.gridx = 1;
            gbc.gridy = 0;
            txtHeight.setText("6.15152");
            pane.add(txtHeight, gbc);
            
            gbc.gridx = 0;
            gbc.gridy = 1;
            pane.add(new JLabel("Angle: "), gbc);
            
            gbc.gridx = 1;
            gbc.gridy = 1;
            pane.add(txtAngle, gbc);
            
            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.gridwidth = 2;
            pane.add(chkMirror, gbc);
            
            gbc.gridx = 0;
            gbc.gridy = 3;
            gbc.gridwidth = 2;
            gbc.weighty = 1.0;
            pane.add(btnGenerate, gbc);
            btnGenerate.addActionListener(e -> generate());

            gbc.gridx = 2;
            gbc.gridy = 0;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.gridheight = 4;
            txtRegionDef.setMinimumSize(new Dimension(300, 200));
            txtRegionDef.setPreferredSize(new Dimension(300, 200));
            JScrollPane scroll = new JScrollPane(txtRegionDef);
            scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            pane.add(scroll, gbc);
            scroll.setBorder(BorderFactory.createTitledBorder("Region"));
            
            gbc.gridx = 2;
            gbc.gridy = 4;
            gbc.fill = GridBagConstraints.BOTH;
            txtGeneratedCode.setMinimumSize(new Dimension(300, 200));
            txtGeneratedCode.setPreferredSize(new Dimension(300, 200));
            scroll = new JScrollPane(txtGeneratedCode);
            scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            pane.add(txtGeneratedCode, gbc);
            scroll.setBorder(BorderFactory.createTitledBorder("Generated Code"));
            
        }
        
        private Shape getRegionShape() {
            SVGUniverse universe = new SVGUniverse();
            String input = "<svg>" + txtRegionDef.getText() + "</svg>";
            URI uri;
            try {
                uri = universe.loadSVG(new ByteArrayInputStream(input.getBytes()), "diagram");
                SVGDiagram diagram = universe.getDiagram(uri);
                SVGElement element = diagram.getRoot();
                while (!(element instanceof Path) && (element.getNumChildren() > 0)) {
                    element = element.getChild(0);
                }
                return ((Path) element).getShape();
            } catch (Exception e) {
                return null;
            }
        }
        
        private void generate() {
            Shape shape = getRegionShape();
            if (null == shape) {
                return;
            }
            final double HEIGHT = Double.parseDouble(txtHeight.getText());
            Rectangle2D bbox = shape.getBounds2D();
            List<String> regions = new ArrayList<>();
            double curY = bbox.getY() + 0.5; // add a bit of a margin
            AffineTransform at = null;
            if (txtAngle.getText().length() > 0) {
                double angle = Double.parseDouble(txtAngle.getText()) * Math.PI / 180;
                at = AffineTransform.getRotateInstance(- angle);
            }
            do {
                List<Double> top = findIntersections(curY, shape.getPathIterator(at));
                List<Double> bottom = findIntersections(curY + HEIGHT, shape.getPathIterator(at));
                if ((top.size() > 1) && (bottom.size() > 1)) {
                    Collections.sort(top);
                    Collections.sort(bottom);
                    double x1 = Math.max(top.get(0), bottom.get(0)) + 0.5;
                    double x2 = Math.min(top.get(top.size() - 1) - 1.0,
                            bottom.get(bottom.size() - 1));
                    regions.add(String.format("<rect x=\"%f\" y=\"%f\" width=\"%f\" height=\"%f\" />",
                            x1 - bbox.getX(), bbox.getHeight() - (curY - bbox.getY() - 0.5), x2 - x1, HEIGHT));
                }
                curY += HEIGHT * 0.866;
            } while (curY + HEIGHT + 0.5 < bbox.getY() + bbox.getHeight());
            Collections.reverse(regions);
            txtGeneratedCode.setText(regions.stream().collect(Collectors.joining("\n")));
        }
        
        private List<Double> findIntersections(double curY, PathIterator iter) {
            double[] coords = new double[6];
            double[] curPos = new double[2];
            double[] start = new double[6];
            double dy = 0;
            List<Double> intersections = new ArrayList<>();
            iter.currentSegment(start);
            while (!iter.isDone()) {
                int type = iter.currentSegment(coords);
                double x = 0;
                double y = 0;
                switch (type) {
                    case PathIterator.SEG_MOVETO:
                        dy = curY - coords[1];
                        curPos[0] = coords[0];
                        curPos[1] = coords[1];
                        iter.next();
                        continue;
                    case PathIterator.SEG_LINETO:
                        x = coords[0];
                        y = coords[1];
                        break;
                    case PathIterator.SEG_CUBICTO:
                        x = coords[2];
                        y = coords[3];
                        break;
                    case PathIterator.SEG_QUADTO:
                        x = coords[4];
                        y = coords[5];
                        break;
                    case PathIterator.SEG_CLOSE:
                        x = start[0];
                        y = start[1];
                        break;
                }
                double dy1 = curY - y;
                if ((curPos[1] != y) && (dy * dy1 <= 0)) {
                    intersections.add(getLineX(curY, curPos[0], curPos[1], x, y));
                }
                curPos[0] = x;
                curPos[1] = y;
                dy = dy1;
                iter.next();
            }
            return intersections;
        }

        @SuppressWarnings("unused")
        private void showShape(Shape shape) {
            double[] coords = new double[6];
            PathIterator iter = shape.getPathIterator(null);
            while (!iter.isDone()) {
                int type = iter.currentSegment(coords);
                switch (type) {
                    case PathIterator.SEG_MOVETO:
                        System.out.print("M " + coords[0] + "," + coords[1]);
                        break;
                    case PathIterator.SEG_LINETO:
                        System.out.print("L " + coords[0] + "," + coords[1]);
                        break;
                    case PathIterator.SEG_CUBICTO:
                        System.out.print("C " + coords[2] + "," + coords[3]);
                        break;
                    case PathIterator.SEG_QUADTO:
                        System.out.print("Q " + coords[4] + "," + coords[5]);
                        break;
                    case PathIterator.SEG_CLOSE:
                        System.out.print("Z");
                        break;
                }
                System.out.print(" ");
                iter.next();
            }
            System.out.println();
        }

        /**
         * Given a y coordinate, find the corresponding x coordinate  on the given line. This does not
         * check whether the point is actually within the bounds of the segment or whether the segment
         * has a slope of zero.
         *
         * @return    The x coordinate of the point.
         */
        private double getLineX(double y, double x1, double y1, double x2, double y2) {
            return x1 + (y - y1)/(y2 - y1) * (x2 - x1);
        }
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame());
    }

}
