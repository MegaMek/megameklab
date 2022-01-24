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
package megameklab.com.ui.largeAero;

import megamek.common.AmmoType;
import megamek.common.Entity;
import megamek.common.Mounted;
import megamek.common.WeaponType;
import megamek.common.verifier.EntityVerifier;
import megamek.common.verifier.TestSmallCraft;
import megameklab.com.ui.util.ITab;
import megameklab.com.ui.util.RefreshListener;
import megameklab.com.ui.util.WrapLayout;
import megameklab.com.util.ImageHelper;
import megameklab.com.util.UnitUtil;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.text.DecimalFormat;

/**
 * Status bar for SmallCraft and Dropships
 * 
 * @author Neoancient
 *
 */
public class DSStatusBar extends ITab {

    private final JLabel bvLabel = new JLabel();
    private final JLabel tons = new JLabel();
    private final JLabel heatSink = new JLabel();
    private final JLabel cost = new JLabel();
    private final JLabel invalid = new JLabel();
    private final EntityVerifier entityVerifier = EntityVerifier.getInstance(new File(
            "data/mechfiles/UnitVerifierOptions.xml"));
    private final DecimalFormat formatter;
    private final JFrame parentFrame;

    private RefreshListener refresh;

    public DSStatusBar(DSMainUI parent) {
        super(parent);
        parentFrame = parent;

        formatter = new DecimalFormat();
        JButton showEquipmentDatabase = new JButton("Show Equipment Database");
        showEquipmentDatabase.addActionListener(evt -> parent.getFloatingEquipmentDatabase().setVisible(true));
        JButton btnValidate = new JButton("Validate Unit");
        btnValidate.addActionListener(e -> UnitUtil.showValidation(getSmallCraft(), getParentFrame()));
        JButton btnFluffImage = new JButton("Set Fluff Image");
        btnFluffImage.addActionListener(e -> getFluffImage());
        invalid.setText("Invalid");
        invalid.setForeground(Color.RED);
        invalid.setVisible(false);

        setLayout(new WrapLayout(FlowLayout.LEFT, 22, 5));
        add(showEquipmentDatabase);
        add(btnValidate);
        add(btnFluffImage);
        add(tons);
        add(heatSink);
        add(bvLabel);
        add(invalid);
        add(cost);
        refresh();
    }

    public void refresh() {
        int heat = getSmallCraft().getHeatCapacity();
        double tonnage = getSmallCraft().getWeight();
        double currentTonnage;
        int bv = getSmallCraft().calculateBattleValue();
        long currentCost = Math.round(getSmallCraft().getCost(false));

        TestSmallCraft testSmallCraft = new TestSmallCraft(getSmallCraft(), entityVerifier.aeroOption, null);

        currentTonnage = testSmallCraft.calculateWeight();
        currentTonnage += UnitUtil.getUnallocatedAmmoTonnage(getSmallCraft());

        double totalHeat = calculateTotalHeat();

        heatSink.setText("Heat: " + totalHeat + "/" + heat);
        heatSink.setToolTipText("Total Heat Generated/Total Heat Dissipated");
        if (totalHeat > heat) {
            heatSink.setForeground(Color.red);
        } else {
            heatSink.setForeground(UIManager.getColor("Label.foreground"));
        }
        heatSink.setVisible(getSmallCraft().getEntityType() == Entity.ETYPE_AERO);

        tons.setText(String.format("Tonnage: %,.1f/%,.1f (%,.1f Remaining)", currentTonnage, tonnage, tonnage - currentTonnage));
        tons.setToolTipText("Current Tonnage/Max Tonnage");
        if (currentTonnage > tonnage) {
            tons.setForeground(Color.red);
        } else {
            tons.setForeground(UIManager.getColor("Label.foreground"));
        }

        bvLabel.setText("BV: " + bv);
        bvLabel.setToolTipText("BV 2.0");

        cost.setText("Cost: " + formatter.format(currentCost) + " C-bills");
        StringBuffer sb = new StringBuffer();
        invalid.setVisible(!testSmallCraft.correctEntity(sb));
        invalid.setToolTipText("<html>" + sb.toString().replaceAll("\n", "<br/>") + "</html>");
    }

    public double calculateTotalHeat() {
        double heat = 0;

        for (Mounted mounted : getSmallCraft().getWeaponList()) {
            WeaponType wtype = (WeaponType) mounted.getType();
            double weaponHeat = wtype.getHeat();

            // only count non-damaged equipment
            if (mounted.isMissing() || mounted.isHit() || mounted.isDestroyed()
                    || mounted.isBreached()) {
                continue;
            }

            // one shot weapons count 1/4
            if ((wtype.getAmmoType() == AmmoType.T_ROCKET_LAUNCHER)
                    || wtype.hasFlag(WeaponType.F_ONESHOT)) {
                weaponHeat *= 0.25;
            }

            // double heat for ultras
            if ((wtype.getAmmoType() == AmmoType.T_AC_ULTRA)
                    || (wtype.getAmmoType() == AmmoType.T_AC_ULTRA_THB)) {
                weaponHeat *= 2;
            }

            // Six times heat for RAC
            if (wtype.getAmmoType() == AmmoType.T_AC_ROTARY) {
                weaponHeat *= 6;
            }

            // half heat for streaks
            if ((wtype.getAmmoType() == AmmoType.T_SRM_STREAK)
                    || (wtype.getAmmoType() == AmmoType.T_LRM_STREAK)) {
                weaponHeat *= 0.5;
            }
            heat += weaponHeat;
        }
        for (Mounted m : getSmallCraft().getMisc()) {
            heat += m.getType().getHeat();
        }
        return heat;
    }

    private void getFluffImage() {
        //copied from structureTab
        FileDialog fDialog = new FileDialog(getParentFrame(), "Image Path",
                FileDialog.LOAD);
        fDialog.setDirectory(new File(ImageHelper.fluffPath).getAbsolutePath()
                + File.separatorChar + ImageHelper.imageMech
                + File.separatorChar);
        fDialog.setLocationRelativeTo(this);

        fDialog.setVisible(true);

        if (fDialog.getFile() != null) {
            String relativeFilePath = new File(fDialog.getDirectory()
                    + fDialog.getFile()).getAbsolutePath();
            relativeFilePath = "."
                    + File.separatorChar
                    + relativeFilePath
                            .substring(new File(System.getProperty("user.dir")).getAbsolutePath().length() + 1);
            getSmallCraft().getFluff().setMMLImagePath(relativeFilePath);
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
