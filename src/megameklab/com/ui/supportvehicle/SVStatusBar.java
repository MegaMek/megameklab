/*
 * MegaMekLab
 * Copyright (C) 2019 The MegaMek Team
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package megameklab.com.ui.supportvehicle;

import megamek.common.verifier.EntityVerifier;
import megamek.common.verifier.TestSupportVehicle;
import megameklab.com.ui.MegaMekLabMainUI;
import megameklab.com.util.CConfig;
import megameklab.com.util.ITab;
import megameklab.com.util.ImageHelper;
import megameklab.com.util.UnitUtil;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.text.DecimalFormat;

/**
 * Status bar for support vehicle construction option. Largely copied from combat vehicle status bar
 */
class SVStatusBar extends ITab {

    private final JPanel slotsPanel = new JPanel();
    private final JLabel move = new JLabel();
    private final JLabel bvLabel = new JLabel();
    private final JLabel tons = new JLabel();
    private final JLabel slots = new JLabel();
    private final JLabel cost = new JLabel();
    private final JLabel invalid = new JLabel();
    private final EntityVerifier entityVerifier = EntityVerifier.getInstance(new File(
            "data/mechfiles/UnitVerifierOptions.xml"));
    private TestSupportVehicle testEntity;
    private final DecimalFormat formatter;
    private final MegaMekLabMainUI mainUI;

    SVStatusBar(MegaMekLabMainUI parent) {
        super(parent);
        mainUI = parent;

        formatter = new DecimalFormat();
        testEntity = new TestSupportVehicle(parent.getEntity(), entityVerifier.tankOption,
                null);
        JButton btnValidate = new JButton("Validate Unit");
        btnValidate.addActionListener(evt -> UnitUtil.showValidation(parent.getEntity(), getParentFrame()));
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
        this.add(movementLabel(), gbc);
        gbc.gridx = 4;
        this.add(bvLabel(), gbc);
        gbc.gridx = 5;
        this.add(bvLabel, gbc);
        gbc.gridx = 6;
        this.add(tonnageLabel(), gbc);
        gbc.gridx = 7;
        this.add(slotsPanel(), gbc);
        gbc.gridx = 8;
        this.add(invalid, gbc);
        gbc.gridx = 9;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        this.add(cost, gbc);
        refresh();
    }

    private JLabel movementLabel() {
        int walk = eSource.getEntity().getOriginalWalkMP();
        int run = eSource.getEntity().getRunMP(false, true, false);
        int jump = eSource.getEntity().getOriginalJumpMP();

        move.setText("Movement: " + walk + "/" + run + "/" + jump);
        return move;
    }

    private JLabel bvLabel() {
        int bv = eSource.getEntity().calculateBattleValue();
        bvLabel.setText("BV: " + bv);

        return bvLabel;
    }

    private JLabel tonnageLabel() {
        double tonnage = eSource.getEntity().getWeight();
        double currentTonnage;

        currentTonnage = testEntity.calculateWeight();
        currentTonnage += UnitUtil.getUnallocatedAmmoTonnage(eSource.getEntity());

        tons.setText("Tonnage: " + currentTonnage + "/" + tonnage);
        return tons;
    }

    private JPanel slotsPanel() {
        final int totalSlots = testEntity.totalSlotCount();
        final int currentSlots = testEntity.occupiedSlotCount();
        slots.setText("Slots: " + currentSlots + "/" + totalSlots);
        slotsPanel.add(slots);
        return slotsPanel;
    }

    public void refresh() {
        int walk = eSource.getEntity().getOriginalWalkMP();
        int run = eSource.getEntity().getRunMP(true, true, false);
        int jump = eSource.getEntity().getOriginalJumpMP();
        double tonnage = eSource.getEntity().getWeight();
        double currentTonnage;
        int bv = eSource.getEntity().calculateBattleValue();

        testEntity = new TestSupportVehicle(eSource.getEntity(), entityVerifier.tankOption,
                null);

        currentTonnage = testEntity.calculateWeight();

        currentTonnage += UnitUtil.getUnallocatedAmmoTonnage(eSource.getEntity());
        long currentCost = Math.round(eSource.getEntity().getCost(false));

        tons.setText("Tonnage: " + currentTonnage + "/" + tonnage);
        tons.setToolTipText("Current Tonnage/Max Tonnage");
        if (currentTonnage > tonnage) {
            tons.setForeground(Color.red);
        } else {
            tons.setForeground(UIManager.getColor("Label.foreground"));
        }

        final int totalSlots = testEntity.totalSlotCount();
        final int currentSlots = testEntity.occupiedSlotCount();
        slots.setText("Slots: " + currentSlots + "/" + totalSlots);
        if (currentSlots > totalSlots) {
            slots.setForeground(Color.red);
        } else {
            slots.setForeground(UIManager.getColor("Label.foreground"));
        }

        bvLabel.setText("BV: " + bv);
        bvLabel.setToolTipText("BV 2.0");

        cost.setText("Cost: " + formatter.format(currentCost) + " C-bills");

        move.setText("Movement: " + walk + "/" + run + "/" + jump);
        move.setToolTipText("Walk/Run/Jump MP");
        StringBuffer sb = new StringBuffer();
        invalid.setVisible(!testEntity.correctEntity(sb));
        invalid.setToolTipText("<html>" + sb.toString().replaceAll("\n", "<br/>") + "</html>");
    }

    private void getFluffImage() {
        //copied from structureTab
        FileDialog fDialog = new FileDialog(getParentFrame(), "Image Path", FileDialog.LOAD);
        fDialog.setDirectory(new File(CConfig.getFluffImagesPath()).getAbsolutePath() + File.separatorChar + ImageHelper.imageMech + File.separatorChar);
        fDialog.setLocationRelativeTo(this);

        fDialog.setVisible(true);

        if (fDialog.getFile() != null) {
            String relativeFilePath = new File(fDialog.getDirectory() + fDialog.getFile()).getAbsolutePath();
            relativeFilePath = "." + File.separatorChar + relativeFilePath.substring(new File(System.getProperty("user.dir")).getAbsolutePath().length() + 1);
            eSource.getEntity().getFluff().setMMLImagePath(relativeFilePath);
        }
        mainUI.refreshPreview();
    }

    private JFrame getParentFrame() {
        return mainUI;
    }
}
