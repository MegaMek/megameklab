/*
 * MegaMekLab - Copyright (C) 2009
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

package megameklab.com.ui.Vehicle;

import java.awt.Color;
import java.awt.FileDialog;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import megamek.common.Tank;
import megamek.common.verifier.EntityVerifier;
import megamek.common.verifier.TestSupportVehicle;
import megamek.common.verifier.TestTank;
import megameklab.com.ui.MegaMekLabMainUI;
import megameklab.com.util.ITab;
import megameklab.com.util.ImageHelper;
import megameklab.com.util.RefreshListener;
import megameklab.com.util.UnitUtil;

public class StatusBar extends ITab {

    /**
     *
     */
    private static final long serialVersionUID = -6754327753693500675L;

    private JButton btnValidate = new JButton("Validate Unit");
    private JButton btnFluffImage = new JButton("Set Fluff Image");
    private JPanel slotsPanel = new JPanel();
    private JLabel move = new JLabel();
    private JLabel bvLabel = new JLabel();
    private JLabel tons = new JLabel();
    private JLabel slots = new JLabel();
    private JLabel cost = new JLabel();
    private EntityVerifier entityVerifier = EntityVerifier.getInstance(new File(
            "data/mechfiles/UnitVerifierOptions.xml"));
    private TestTank testEntity = null;
    private DecimalFormat formatter;
    private JFrame parentFrame;

    private RefreshListener refresh;

    public StatusBar(MegaMekLabMainUI parent) {
        super(parent);
        parentFrame = parent;

        formatter = new DecimalFormat();
        if (parent.getEntity().isSupportVehicle()) {
            testEntity = new TestSupportVehicle((Tank) parent.getEntity(),
                    entityVerifier.tankOption, null);
        } else {
            testEntity = new TestTank((Tank) parent.getEntity(), entityVerifier.tankOption,
                    null);
        }
        btnValidate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UnitUtil.showValidation(getTank(), getParentFrame());
            }
        });
        btnFluffImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getFluffImage();
            }
        });
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
        this.add(tons, gbc);
        gbc.gridx = 3;
        this.add(movementLabel(), gbc);
        gbc.gridx = 4;
        this.add(bvLabel(), gbc);
        gbc.gridx = 5;
        this.add(bvLabel, gbc);
        gbc.gridx = 6;
        this.add(tonnageLabel());
        gbc.gridx = 7;
        this.add(slotsPanel());
        gbc.gridx = 8;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        this.add(cost, gbc);
        refresh();
    }

    public JLabel movementLabel() {
        int walk = getTank().getOriginalWalkMP();
        int run = getTank().getRunMP(false, true, false);
        int jump = getTank().getOriginalJumpMP();

        move.setText("Movement: " + walk + "/" + run + "/" + jump);
        return move;
    }

    public JLabel bvLabel() {
        int bv = getTank().calculateBattleValue();
        bvLabel.setText("BV: " + bv);

        return bvLabel;
    }

    public JLabel tonnageLabel() {
        double tonnage = getTank().getWeight();
        double currentTonnage;

        currentTonnage = testEntity.calculateWeight();
        currentTonnage += UnitUtil.getUnallocatedAmmoTonnage(getTank());

        tons.setText("Tonnage: " + currentTonnage + "/" + tonnage);
        return tons;
    }

    public JPanel slotsPanel() {
        Tank tank = getTank();
        int currentSlots = tank.getTotalSlots() - tank.getFreeSlots();
        slots.setText("Slots: "+currentSlots+"/"+tank.getTotalSlots());
        slotsPanel.add(slots);
        return slotsPanel;
    }

    public void refresh() {

        int walk = getTank().getOriginalWalkMP();
        int run = getTank().getRunMP(true, true, false);
        int jump = getTank().getOriginalJumpMP();
        double tonnage = getTank().getWeight();
        double currentTonnage;
        int bv = getTank().calculateBattleValue();

        if (getTank().isSupportVehicle()) {
            testEntity = new TestSupportVehicle(getTank(),
                    entityVerifier.tankOption, null);
        } else {
            testEntity = new TestTank((Tank) getTank(), entityVerifier.tankOption,
                    null);
        }

        currentTonnage = testEntity.calculateWeight();

        currentTonnage += UnitUtil.getUnallocatedAmmoTonnage(getTank());
        long currentCost = (long) Math.round(getTank().getCost(false));

        tons.setText("Tonnage: " + currentTonnage + "/" + tonnage);
        tons.setToolTipText("Current Tonnage/Max Tonnage");
        if (currentTonnage > tonnage) {
            tons.setForeground(Color.red);
        } else {
            tons.setForeground(Color.black);
        }
        Tank tank = getTank();
        int currentSlots = tank.getTotalSlots() - tank.getFreeSlots();
        slots.setText("Slots: "+currentSlots+"/"+tank.getTotalSlots());
        if (currentSlots > tank.getTotalSlots()) {
            slots.setForeground(Color.red);
        } else {
            slots.setForeground(Color.black);
        }

        bvLabel.setText("BV: " + bv);
        bvLabel.setToolTipText("BV 2.0");

        cost.setText("Cost: " + formatter.format(currentCost) + " C-bills");

        move.setText("Movement: " + walk + "/" + run + "/" + jump);
        move.setToolTipText("Walk/Run/Jump MP");

    }

    private void getFluffImage() {
        //copied from structureTab
        FileDialog fDialog = new FileDialog(getParentFrame(), "Image Path", FileDialog.LOAD);
        fDialog.setDirectory(new File(ImageHelper.fluffPath).getAbsolutePath() + File.separatorChar + ImageHelper.imageMech + File.separatorChar);
        /*
         //This does not seem to be working
        if (getMech().getFluff().getMMLImagePath().trim().length() > 0) {
            String fullPath = new File(getMech().getFluff().getMMLImagePath()).getAbsolutePath();
            String imageName = fullPath.substring(fullPath.lastIndexOf(File.separatorChar) + 1);
            fullPath = fullPath.substring(0, fullPath.lastIndexOf(File.separatorChar) + 1);
            fDialog.setDirectory(fullPath);
            fDialog.setFile(imageName);
        } else {
            fDialog.setDirectory(new File(ImageHelper.fluffPath).getAbsolutePath() + File.separatorChar + ImageHelper.imageMech + File.separatorChar);
            fDialog.setFile(getMech().getChassis() + " " + getMech().getModel() + ".png");
        }
        */
        fDialog.setLocationRelativeTo(this);

        fDialog.setVisible(true);

        if (fDialog.getFile() != null) {
            String relativeFilePath = new File(fDialog.getDirectory() + fDialog.getFile()).getAbsolutePath();
            relativeFilePath = "." + File.separatorChar + relativeFilePath.substring(new File(System.getProperty("user.dir").toString()).getAbsolutePath().length() + 1);
            getTank().getFluff().setMMLImagePath(relativeFilePath);
        }
        refresh.refreshPreview();
        return;
    }

    private JFrame getParentFrame() {
        return parentFrame;
    }
}