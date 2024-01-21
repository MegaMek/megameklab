/*
 * Copyright (c) 2024 - The MegaMek Team. All Rights Reserved.
 *
 * This file is part of MegaMekLab.
 *
 * MegaMek is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MegaMek is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MegaMek. If not, see <http://www.gnu.org/licenses/>.
 */
package megameklab.ui.generalUnit;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * This is a specialized label for {@link StatusBar} that calls a callback method
 * when it is clicked and shows its text underlined while hovered.
 */
class ClickableLabel extends JLabel implements MouseListener {

    private static final String HOVERED_PREFIX = "<HTML><U>";
    private static final String NON_HOVERED_PREFIX = "<HTML>";
    private boolean isHovered = false;
    private String baseText = "";
    private final Consumer<MouseEvent> clickCallback;

    /**
     * Creates a new clickable label. The given callback method is called when the
     * label is mouse-clicked.
     *
     * @param clickCallback The method to call when the label is clicked
     */
    ClickableLabel(Consumer<MouseEvent> clickCallback) {
        this.clickCallback = Objects.requireNonNull(clickCallback);
        addMouseListener(this);
    }

    @Override
    public void setText(String text) {
        baseText = text;
        updateText();
    }

    private void updateText() {
        super.setText((isHovered ? HOVERED_PREFIX : NON_HOVERED_PREFIX) + baseText);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        clickCallback.accept(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        isHovered = true;
        updateText();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        isHovered = false;
        updateText();
    }
}