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

package megameklab.com.ui.Mek;

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
import javax.swing.UIManager;

import megamek.common.AmmoType;
import megamek.common.Engine;
import megamek.common.Mech;
import megamek.common.Mounted;
import megamek.common.QuadMech;
import megamek.common.TripodMech;
import megamek.common.WeaponType;
import megamek.common.verifier.EntityVerifier;
import megamek.common.verifier.TestMech;
import megameklab.com.ui.MegaMekLabMainUI;
import megameklab.com.util.CConfig;
import megameklab.com.util.ITab;
import megameklab.com.util.ImageHelper;
import megameklab.com.util.RefreshListener;
import megameklab.com.util.UnitUtil;

public class StatusBar extends ITab {

    private static final long serialVersionUID = -6754327753693500675L;

    private final JLabel crits = new JLabel();
    private final JLabel bvLabel = new JLabel();
    private final JLabel tons = new JLabel();
    private final JLabel heatSink = new JLabel();
    private final JLabel cost = new JLabel();
    private final JLabel invalid = new JLabel();
    private final EntityVerifier entityVerifier = EntityVerifier.getInstance(new File("data/mechfiles/UnitVerifierOptions.xml"));
    private TestMech testEntity;
    private final DecimalFormat formatter;
    private final JFrame parentFrame;

    private RefreshListener refresh;

    public StatusBar(MegaMekLabMainUI parent) {
        super(parent);
        parentFrame = parent;

        formatter = new DecimalFormat();
        testEntity = new TestMech(getMech(), entityVerifier.mechOption, null);
        JButton btnValidate = new JButton("Validate Unit");
        btnValidate.addActionListener(evt -> UnitUtil.showValidation(getMech(), getParentFrame()));
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
        this.add(tons, gbc);
        gbc.gridx = 3;
        this.add(crits, gbc);
        gbc.gridx = 4;
        this.add(heatSink, gbc);
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
        int heat = getMech().getHeatCapacity();
        double tonnage = getMech().getWeight();
        double currentTonnage;
        int bv = getMech().calculateBattleValue();
        int maxCrits;
        if (getMech() instanceof TripodMech) {
            maxCrits = 84;
        } else if (getMech() instanceof QuadMech) {
            maxCrits = 66;
        } else {
            maxCrits = 78;
        }
        int currentCrits = UnitUtil.countUsedCriticals(getMech());
        long currentCost = Math.round(getMech().getCost(false));

        testEntity = new TestMech(getMech(), entityVerifier.mechOption, null);

        currentTonnage = testEntity.calculateWeight();
        currentTonnage += UnitUtil.getUnallocatedAmmoTonnage(getMech());

        double totalHeat = calculateTotalHeat();

        heatSink.setText("Heat: " + totalHeat + "/" + heat);
        heatSink.setToolTipText("Total Heat Generated/Total Heat Dissipated");
        if (totalHeat > heat) {
            heatSink.setForeground(Color.red);
        } else {
            heatSink.setForeground(UIManager.getColor("Label.foreground"));
        }

        tons.setText("Tonnage: " + currentTonnage + "/" + tonnage);
        tons.setToolTipText("Current Tonnage/Max Tonnage");
        if (currentTonnage > tonnage) {
            tons.setForeground(Color.red);
        } else {
            tons.setForeground(UIManager.getColor("Label.foreground"));
        }

        bvLabel.setText("BV: " + bv);
        bvLabel.setToolTipText("BV 2.0");

        cost.setText("Cost: " + formatter.format(currentCost) + " C-bills");

        crits.setText("Criticals: " +  currentCrits + "/" + maxCrits);
        if(currentCrits > maxCrits) {
            crits.setForeground(Color.red);
        } else {
            crits.setForeground(UIManager.getColor("Label.foreground"));
        }
        StringBuffer sb = new StringBuffer();
        invalid.setVisible(!testEntity.correctEntity(sb));
        invalid.setToolTipText("<html>" + sb.toString().replaceAll("\n", "<br/>") + "</html>");
    }

    public double calculateTotalHeat() {
        double heat = 0;

        if (getMech().getOriginalJumpMP() > 0) {
            if (getMech().getJumpType() == Mech.JUMP_IMPROVED) {
                heat += Math.max(3, Math.ceil(getMech().getOriginalJumpMP() / 2.0f));
            } else if (getMech().getJumpType() != Mech.JUMP_BOOSTER) {
                heat += Math.max(3, getMech().getOriginalJumpMP());
            }
            if (getMech().getEngine().getEngineType() == Engine.XXL_ENGINE) {
                heat *= 2;
            }
        } else if (getMech().getEngine().getEngineType() == Engine.XXL_ENGINE) {
            heat += 6;
        } else {
            heat += 2;
        }

        for (Mounted mounted : getMech().getWeaponList()) {
            WeaponType wtype = (WeaponType) mounted.getType();
            double weaponHeat = wtype.getHeat();

            // only count non-damaged equipment
            if (mounted.isMissing() || mounted.isHit() || mounted.isDestroyed() || mounted.isBreached()) {
                continue;
            }

            // one shot weapons count 1/4
            if ((wtype.getAmmoType() == AmmoType.T_ROCKET_LAUNCHER) || wtype.hasFlag(WeaponType.F_ONESHOT)) {
                weaponHeat *= 0.25;
            }

            // double heat for ultras
            if ((wtype.getAmmoType() == AmmoType.T_AC_ULTRA) || (wtype.getAmmoType() == AmmoType.T_AC_ULTRA_THB)) {
                weaponHeat *= 2;
            }

            // Six times heat for RAC
            if (wtype.getAmmoType() == AmmoType.T_AC_ROTARY) {
                weaponHeat *= 6;
            }

            // half heat for streaks
            if ((wtype.getAmmoType() == AmmoType.T_SRM_STREAK) || (wtype.getAmmoType() == AmmoType.T_MRM_STREAK) || (wtype.getAmmoType() == AmmoType.T_LRM_STREAK)) {
                weaponHeat *= 0.5;
            }
            heat += weaponHeat;
        }
        if (getMech().hasStealth()) {
            heat += 10;
        }
        for (Mounted m : getMech().getMisc()) {
            heat += m.getType().getHeat();
        }
        return heat;
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
            getMech().getFluff().setMMLImagePath(relativeFilePath);
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