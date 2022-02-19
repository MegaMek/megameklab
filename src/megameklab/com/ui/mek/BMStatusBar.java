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
package megameklab.com.ui.mek;

import megamek.client.ui.swing.GUIPreferences;
import megamek.common.*;
import megamek.common.verifier.EntityVerifier;
import megamek.common.verifier.TestMech;
import megameklab.com.ui.util.ITab;
import megameklab.com.ui.util.RefreshListener;
import megamek.client.ui.WrapLayout;
import megameklab.com.util.ImageHelper;
import megameklab.com.util.UnitUtil;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.text.DecimalFormat;

public class BMStatusBar extends ITab {

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

    public BMStatusBar(BMMainUI parent) {
        super(parent);
        parentFrame = parent;

        formatter = new DecimalFormat();
        testEntity = new TestMech(getMech(), entityVerifier.mechOption, null);
        JButton showEquipmentDatabase = new JButton("Show Equipment Database");
        showEquipmentDatabase.addActionListener(evt -> parent.getFloatingEquipmentDatabase().setVisible(true));
        JButton btnValidate = new JButton("Validate Unit");
        btnValidate.addActionListener(evt -> UnitUtil.showValidation(getMech(), getParentFrame()));
        JButton btnFluffImage = new JButton("Set Fluff Image");
        btnFluffImage.addActionListener(evt -> getFluffImage());
        invalid.setText("Invalid");
        invalid.setForeground(Color.RED);
        invalid.setVisible(false);

        setLayout(new WrapLayout(FlowLayout.LEFT, 22, 5));
        add(showEquipmentDatabase);
        add(btnValidate);
        add(btnFluffImage);
        add(tons);
        add(crits);
        add(heatSink);
        add(bvLabel);
        add(invalid);
        add(cost);
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
        heatSink.setText("Heat: " + totalHeat + " / " + heat);
        heatSink.setToolTipText("Total Heat Generated / Total Heat Dissipated");

        tons.setText(String.format("Tonnage: %.1f / %.0f (%.1f Remaining)", currentTonnage, tonnage, tonnage - currentTonnage));
        tons.setToolTipText("Current Tonnage/Max Tonnage");
        tons.setForeground(currentTonnage > tonnage ? GUIPreferences.getInstance().getWarningColor() : null);

        bvLabel.setText("BV: " + bv);
        bvLabel.setToolTipText("BV 2.0");

        cost.setText("Cost: " + formatter.format(currentCost) + " C-bills");

        crits.setText("Criticals: " +  currentCrits + " / " + maxCrits);
        crits.setForeground(currentCrits > maxCrits ? GUIPreferences.getInstance().getWarningColor() : null);

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
            if ((wtype.getAmmoType() == AmmoType.T_SRM_STREAK) || (wtype.getAmmoType() == AmmoType.T_LRM_STREAK)) {
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
        FileDialog fDialog = new FileDialog(getParentFrame(), "Image Path", FileDialog.LOAD);
        fDialog.setDirectory(new File(ImageHelper.fluffPath).getAbsolutePath() + File.separatorChar + ImageHelper.imageMech + File.separatorChar);
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

    public void addRefreshedListener(RefreshListener refreshListener) {
        refresh = refreshListener;
    }

}