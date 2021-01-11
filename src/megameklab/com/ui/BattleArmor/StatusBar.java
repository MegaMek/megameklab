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

package megameklab.com.ui.BattleArmor;

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
import javax.swing.UIManager;

import megamek.common.BattleArmor;
import megamek.common.verifier.EntityVerifier;
import megamek.common.verifier.TestBattleArmor;
import megameklab.com.ui.MegaMekLabMainUI;
import megameklab.com.util.CConfig;
import megameklab.com.util.ITab;
import megameklab.com.util.ImageHelper;
import megameklab.com.util.RefreshListener;
import megameklab.com.util.UnitUtil;

public class StatusBar extends ITab {

    private static final long serialVersionUID = -6754327753693500675L;

    private final JPanel tonnagePanel = new JPanel();
    private final JPanel movementPanel = new JPanel();
    private final JPanel bvPanel = new JPanel();
    
    private final JLabel move = new JLabel();
    private final JLabel bvLabel = new JLabel();
    private final JLabel tons = new JLabel();
    private final JLabel cost = new JLabel();
    private final JLabel invalid = new JLabel();
    private final EntityVerifier entityVerifier = EntityVerifier.getInstance(new File(
            "data/mechfiles/UnitVerifierOptions.xml"));
    private final DecimalFormat formatter;
    private final JFrame parentFrame;

    private RefreshListener refresh;
    public StatusBar(final MegaMekLabMainUI parent) {
        super(parent);
        parentFrame = parent;
        formatter = new DecimalFormat();
        JButton btnValidate = new JButton("Validate Unit");
        btnValidate.addActionListener(evt -> UnitUtil.showValidation(getBattleArmor(), getParentFrame()));
        JButton btnFluffImage = new JButton("Set Fluff Image");
        btnFluffImage.addActionListener(evt -> getFluffImage());
        invalid.setText("Invalid");
        invalid.setForeground(Color.RED);
        invalid.setVisible(false);
        setLayout(new GridBagLayout());
        this.add(movementPanel());
        this.add(bvPanel());
        this.add(tonnagePanel());

        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5,2,2,30);
        gbc.anchor = GridBagConstraints.WEST;
        this.add(btnValidate, gbc);
        gbc.gridx = 1;
        this.add(btnFluffImage, gbc);
        gbc.gridx = 2;
        this.add(movementPanel, gbc);
        gbc.gridx = 3;
        this.add(bvPanel, gbc);
        gbc.gridx = 4;
        this.add(tonnagePanel, gbc);
        gbc.gridx = 5;
        this.add(invalid, gbc);
        gbc.gridx = 6;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        this.add(cost, gbc);
        
        refresh();
    }

    public JPanel movementPanel() {
        final int walk = getBattleArmor().getOriginalWalkMP();
        final int jump = getBattleArmor().getOriginalJumpMP();

        move.setText("Movement: " + walk + "/" + jump);
        movementPanel.add(move);
        return movementPanel;
    }

    public JPanel bvPanel() {
        final int bv = getBattleArmor().calculateBattleValue();
        bvLabel.setText("BV: " + bv);
        bvPanel.add(bvLabel);

        return bvPanel;
    }

    public JPanel tonnagePanel() {
        tonnagePanel.add(tons);

        return tonnagePanel;
    }

    public void refresh() {

        final int walk = getBattleArmor().getOriginalWalkMP();
        final int jump = getBattleArmor().getOriginalJumpMP();
        final double maxKilos = getBattleArmor().getTrooperWeight();
        double currentKilos;
        final int bv = getBattleArmor().calculateBattleValue();
        final long currentCost = Math.round(getBattleArmor().getCost(false));

        TestBattleArmor testBA = new TestBattleArmor(getBattleArmor(), entityVerifier.baOption,
                null);
        currentKilos = testBA.calculateWeight(BattleArmor.LOC_SQUAD);
        currentKilos += UnitUtil.getUnallocatedAmmoTonnage(getBattleArmor());

        tons.setText("Suit Weight: " + String.format("%1$.3f",currentKilos) + 
                "/" + maxKilos);
        tons.setToolTipText("This represents the weight of all squad-level " +
                "equipment, it does not count individual equipment");
        if (currentKilos > maxKilos) {
            tons.setForeground(Color.red);
        } else {
            tons.setForeground(UIManager.getColor("Label.foreground"));
        }

        bvLabel.setText("BV: " + bv);
        bvLabel.setToolTipText("BV 2.0");

        move.setText("Movement: " + walk + "/" + jump);
        move.setToolTipText("Walk/Jump MP");

        cost.setText("Squad Cost: " + formatter.format(currentCost) + " C-bills");
        StringBuffer sb = new StringBuffer();
        invalid.setVisible(!testBA.correctEntity(sb));
        invalid.setToolTipText("<html>" + sb.toString().replaceAll("\n", "<br/>") + "</html>");
    }
    
    private void getFluffImage() {
        // copied from structureTab
        final FileDialog fDialog = new FileDialog(getParentFrame(), "Image Path",
                FileDialog.LOAD);
        fDialog.setDirectory(new File(CConfig.getFluffImagesPath()).getAbsolutePath()
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
            getBattleArmor().getFluff().setMMLImagePath(relativeFilePath);
        }
        refresh.refreshPreview();
    }

    private JFrame getParentFrame() {
        return parentFrame;
    }
    
    public void addRefreshedListener(final RefreshListener l) {
        refresh = l;
    }

}