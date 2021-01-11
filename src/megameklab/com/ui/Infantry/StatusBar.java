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

package megameklab.com.ui.Infantry;

import java.awt.*;
import java.io.File;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import megameklab.com.ui.MegaMekLabMainUI;
import megameklab.com.util.CConfig;
import megameklab.com.util.ITab;
import megameklab.com.util.ImageHelper;
import megameklab.com.util.RefreshListener;
import megameklab.com.util.UnitUtil;

public class StatusBar extends ITab {

    private static final long serialVersionUID = -6754327753693500675L;

    private final JLabel move = new JLabel();
    private final JLabel damage = new JLabel();
    private final JLabel bvLabel = new JLabel();
    private final JLabel tons = new JLabel();
    private final JLabel cost = new JLabel();
    private final JLabel invalid = new JLabel();
    private final DecimalFormat formatter;
    private final JFrame parentFrame;

    private RefreshListener refresh;

    public StatusBar(MegaMekLabMainUI parent) {
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
        double currentTonnage;
        int bv = getInfantry().calculateBattleValue();
        long currentCost = Math.round(getInfantry().getCost(false));

        currentTonnage = getInfantry().getWeight();

        move.setText("Movement: " + getInfantry().getWalkMP() + "/" + getInfantry().getJumpMP());

        damage.setText("Damage/Trooper: " + roundFormat.format(getInfantry().getDamagePerTrooper()));

        tons.setText("Tons: " + currentTonnage);

        bvLabel.setText("BV: " + bv);
        bvLabel.setToolTipText("BV 2.0");

        cost.setText("Cost: " + formatter.format(currentCost) + " C-bills");
        String str = UnitUtil.validateUnit(getInfantry());
        invalid.setVisible(!str.isEmpty());
        invalid.setToolTipText("<html>" + str.replaceAll("\n", "<br/>") + "</html>");
    }

    private void getFluffImage() {
        //copied from structureTab
        FileDialog fDialog = new FileDialog(getParentFrame(), "Image Path", FileDialog.LOAD);
        fDialog.setDirectory(new File(CConfig.getFluffImagesPath()).getAbsolutePath() + File.separatorChar + ImageHelper.imageMech + File.separatorChar);
        /*
         //This does not seem to be working
        if (getMech().getFluff().getMMLImagePath().trim().length() > 0) {
            String fullPath = new File(getMech().getFluff().getMMLImagePath()).getAbsolutePath();
            String imageName = fullPath.substring(fullPath.lastIndexOf(File.separatorChar) + 1);
            fullPath = fullPath.substring(0, fullPath.lastIndexOf(File.separatorChar) + 1);
            fDialog.setDirectory(fullPath);
            fDialog.setFile(imageName);
        } else {
            fDialog.setDirectory(new File(CConfig.getFluffImagesPath()).getAbsolutePath() + File.separatorChar + ImageHelper.imageMech + File.separatorChar);
            fDialog.setFile(getMech().getChassis() + " " + getMech().getModel() + ".png");
        }
        */
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