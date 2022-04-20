/*
 * MegaMekLab - Copyright (C) 2008
 *
 * Original author - jtighe (torren@users.sourceforge.net)
 *
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation; either version 2 of the License, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details.
 */
package megameklab.ui.infantry;

import java.awt.*;
import java.io.File;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import megameklab.ui.MegaMekLabMainUI;
import megameklab.ui.util.ITab;
import megameklab.util.ImageHelper;
import megameklab.ui.util.RefreshListener;
import megameklab.util.UnitUtil;

public class CIStatusBar extends ITab {
    private final JLabel move = new JLabel();
    private final JLabel damage = new JLabel();
    private final JLabel bvLabel = new JLabel();
    private final JLabel tons = new JLabel();
    private final JLabel cost = new JLabel();
    private final JLabel invalid = new JLabel();
    private final DecimalFormat formatter;
    private final JFrame parentFrame;

    private RefreshListener refresh;

    public CIStatusBar(MegaMekLabMainUI parent) {
        super(parent);
        this.parentFrame = parent;

        formatter = new DecimalFormat();
        JButton btnValidate = new JButton("Validate Unit");
        btnValidate.addActionListener(evt -> UnitUtil.showValidation(getInfantry(), getParentFrame()));
        JButton btnFluffImage = new JButton("Set Fluff Image");
        btnFluffImage.addActionListener(evt -> getFluffImage());
        invalid.setText("Invalid");
        invalid.setForeground(Color.RED);
        invalid.setVisible(false);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5,2,2,20);
        gbc.anchor = GridBagConstraints.WEST;
        this.add(btnValidate, gbc);
        gbc.gridx = 1;
        this.add(btnFluffImage, gbc);
        gbc.gridx = 2;
        this.add(move, gbc);
        gbc.gridx = 3;
        this.add(damage, gbc);
        gbc.gridx = 4;
        this.add(tons, gbc);
        gbc.gridx = 5;
        this.add(bvLabel, gbc);
        gbc.gridx = 6;
        this.add(invalid, gbc);
        gbc.gridx = 7;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        this.add(cost, gbc);
        refresh();
    }

    public void refresh() {
        DecimalFormat roundFormat = new DecimalFormat("#.##");
        int bv = getInfantry().calculateBattleValue();

        double currentTonnage = getInfantry().getWeight();

        move.setText("Movement: " + getInfantry().getWalkMP() + "/" + getInfantry().getJumpMP());

        damage.setText("Damage/Trooper: " + roundFormat.format(getInfantry().getDamagePerTrooper()));

        tons.setText("Tons: " + currentTonnage);

        bvLabel.setText("BV: " + bv);
        bvLabel.setToolTipText("BV 2.0");

        cost.setText("Dry Cost: " + formatter.format(Math.round(getEntity().getCost(true))) + " C-bills");
        cost.setToolTipText("The dry cost of the unit (without ammo). The unit's full cost is "
                + formatter.format(Math.round(getEntity().getCost(false))) + " C-bills.");

        String str = UnitUtil.validateUnit(getInfantry());
        invalid.setVisible(!str.isEmpty());
        invalid.setToolTipText("<html>" + str.replaceAll("\n", "<br/>") + "</html>");
    }

    private void getFluffImage() {
        // copied from structureTab
        FileDialog fDialog = new FileDialog(getParentFrame(), "Image Path", FileDialog.LOAD);
        fDialog.setDirectory(new File(ImageHelper.fluffPath).getAbsolutePath() + File.separatorChar + ImageHelper.imageMech + File.separatorChar);
        fDialog.setLocationRelativeTo(this);

        fDialog.setVisible(true);

        if (fDialog.getFile() != null) {
            String relativeFilePath = new File(fDialog.getDirectory() + fDialog.getFile()).getAbsolutePath();
            relativeFilePath = "." + File.separatorChar + relativeFilePath.substring(new File(System.getProperty("user.dir")).getAbsolutePath().length() + 1);
            getInfantry().getFluff().setMMLImagePath(relativeFilePath);
        }
        refresh.refreshPreview();
    }

    private JFrame getParentFrame() {
        return parentFrame;
    }

    public void addRefreshedListener(RefreshListener l) {
        refresh = l;
    }
}
