/*
 * Copyright (C) 2026 The MegaMek Team. All Rights Reserved.
 *
 * This file is part of MegaMekLab.
 *
 * MegaMekLab is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License (GPL),
 * version 3 or (at your option) any later version,
 * as published by the Free Software Foundation.
 *
 * MegaMekLab is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * A copy of the GPL should have been included with this project;
 * if not, see <https://www.gnu.org/licenses/>.
 *
 * NOTICE: The MegaMek organization is a non-profit group of volunteers
 * creating free software for the BattleTech community.
 *
 * MechWarrior, BattleMech, `Mech and AeroTech are registered trademarks
 * of The Topps Company, Inc. All Rights Reserved.
 *
 * Catalyst Game Labs and the Catalyst Game Labs logo are trademarks of
 * InMediaRes Productions, LLC.
 *
 * MechWarrior Copyright Microsoft Corporation. MegaMek was created under
 * Microsoft's "Game Content Usage Rules"
 * <https://www.xbox.com/en-US/developers/rules> and it is not endorsed by or
 * affiliated with Microsoft.
 */

package megameklab.ui.building;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.function.IntConsumer;
import javax.swing.JButton;
import javax.swing.JPanel;

import megameklab.util.BuildingMap;
import megameklab.util.BuildingUtil;

/** Keyboard-accessible hex-edge selector shared by structural doors and elevator stops. */
final class BuildingHexSides extends JPanel {
    private final String hexLabel;
    private final String caption;
    private final int selected;
    private final int blocked;
    private final boolean disabled;
    private final JButton[] buttons = new JButton[6];

    BuildingHexSides(String name, String hexLabel, String caption, int selected, int blocked, boolean disabled, IntConsumer toggle) {
        this.hexLabel = hexLabel;
        this.caption = caption;
        this.selected = selected;
        this.blocked = blocked;
        this.disabled = disabled;
        setName(name);
        setLayout(null);
        setOpaque(false);
        setPreferredSize(new Dimension(220, 210));
        for (int side = 0; side < 6; side++) {
            final int facing = side;
            JButton button = new JButton(BuildingUtil.facingLabel(side));
            button.setName(name + " " + BuildingUtil.facingLabel(side));
            button.getAccessibleContext().setAccessibleName(button.getName());
            button.setToolTipText(((selected & (1 << side)) != 0 ? "Remove " : "Add ") + button.getName());
            button.setMargin(new java.awt.Insets(2, 2, 2, 2));
            button.setEnabled(available(side));
            button.addActionListener(event -> toggle.accept(facing));
            buttons[side] = button;
            add(button);
        }
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                if (!javax.swing.SwingUtilities.isLeftMouseButton(event)) {
                    return;
                }
                double x = event.getX() - getWidth() / 2.0, y = event.getY() - getHeight() / 2.0;
                if (Math.hypot(x, y) < radius() * .5 || Math.hypot(x, y) > radius() * 1.25) {
                    return;
                }
                int side = Math.floorMod((int) Math.round((Math.atan2(y, x) + Math.PI / 2) / (Math.PI / 3)), 6);
                if (available(side)) {
                    toggle.accept(side);
                }
            }
        });
    }

    private boolean available(int side) {
        return !disabled && ((blocked & (1 << side)) == 0 || (selected & (1 << side)) != 0);
    }

    private double radius() {
        return Math.min(getWidth(), getHeight()) * .31;
    }

    @Override
    public void doLayout() {
        for (int side = 0; side < 6; side++) {
            double angle = -Math.PI / 2 + side * Math.PI / 3;
            buttons[side].setBounds((int) (getWidth() / 2.0 + Math.cos(angle) * radius() * 1.4 - 19),
                  (int) (getHeight() / 2.0 + Math.sin(angle) * radius() * 1.4 - 12), 38, 24);
        }
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g = (Graphics2D) graphics.create();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        double radius = radius(), cx = getWidth() / 2.0, cy = getHeight() / 2.0;
        for (int side = 0; side < 6; side++) {
            double a = (side + 4) * Math.PI / 3, b = (side + 5) * Math.PI / 3;
            double[] p = { radius * Math.cos(a), radius * Math.sin(a) };
            double[] q = { radius * Math.cos(b), radius * Math.sin(b) };
            boolean chosen = (selected & (1 << side)) != 0;
            g.setColor(chosen ? new Color(210, 165, 60) : available(side) ? getForeground() : Color.GRAY);
            g.setStroke(new BasicStroke(chosen ? 4f : 2f));
            g.draw(new java.awt.geom.Line2D.Double(cx + p[0], cy + p[1], cx + q[0], cy + q[1]));
            if (chosen) {
                Polygon triangle = new Polygon();
                for (double[] point : BuildingMap.doorPoints(p, q)) {
                    triangle.addPoint((int) (cx + point[0]), (int) (cy + point[1]));
                }
                g.setColor(Color.WHITE);
                g.fill(triangle);
                g.setColor(Color.BLACK);
                g.setStroke(new BasicStroke(1.5f));
                g.draw(triangle);
            }
        }
        g.setColor(getForeground());
        g.drawString(hexLabel, (float) (cx - g.getFontMetrics().stringWidth(hexLabel) / 2.0), (float) cy - 2);
        g.drawString(caption, (float) (cx - g.getFontMetrics().stringWidth(caption) / 2.0), (float) cy + 15);
        g.dispose();
    }
}
