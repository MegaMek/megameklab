/*
 * MegaMekLab - Copyright (C) 2018 - The MegaMek Team
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
package megameklab.com.ui.protomek;

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

import megamek.common.verifier.EntityVerifier;
import megamek.common.verifier.TestProtomech;
import megameklab.com.ui.MegaMekLabMainUI;
import megameklab.com.util.CConfig;
import megameklab.com.util.ITab;
import megameklab.com.util.ImageHelper;
import megameklab.com.util.RefreshListener;
import megameklab.com.util.UnitUtil;

/**
 * Status bar for protomech construction
 * 
 * @author Neoancient
 *
 */
public class ProtomekStatusBar extends ITab {
    
    private static final long serialVersionUID = 451172213105975797L;

    private final JLabel crits = new JLabel();
    private final JLabel bvLabel = new JLabel();
    private final JLabel tons = new JLabel();
    private final JLabel cost = new JLabel();
    private final JLabel invalid = new JLabel();
    private final TestProtomech testEntity;
    private final DecimalFormat formatter;
    private final JFrame parentFrame;

    private RefreshListener refresh;

    public ProtomekStatusBar(MegaMekLabMainUI parent) {
        super(parent);
        parentFrame = parent;

        formatter = new DecimalFormat();
        EntityVerifier entityVerifier = EntityVerifier.getInstance(new File("data/mechfiles/UnitVerifierOptions.xml"));
        testEntity = new TestProtomech(getProtomech(), entityVerifier.mechOption, null);
        JButton btnValidate = new JButton("Validate Unit");
        btnValidate.addActionListener(ev -> UnitUtil.showValidation(getProtomech(), getParentFrame()));
        JButton btnFluffImage = new JButton("Set Fluff Image");
        btnFluffImage.addActionListener(ev -> getFluffImage());
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
        JLabel heatSink = new JLabel();
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
        double tonnage = getProtomech().getWeight() * 1000;
        double currentTonnage;
        int bv = getProtomech().calculateBattleValue();
        int maxCrits = 0;
        for (int l = 0; l < getProtomech().locations(); l++) {
            maxCrits += TestProtomech.maxSlotsByLocation(l, getProtomech());
        }
        long currentCrits = getProtomech().getEquipment().stream()
                .filter(m -> TestProtomech.requiresSlot(m.getType())).count();
        long currentCost = Math.round(getProtomech().getCost(false));

        currentTonnage = testEntity.calculateWeight() * 1000;

        tons.setText("Mass: " + currentTonnage + "/" + tonnage);
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

    private void getFluffImage() {
        //copied from mech StatusBar
        FileDialog fDialog = new FileDialog(getParentFrame(), "Image Path", FileDialog.LOAD);
        fDialog.setDirectory(new File(CConfig.getFluffImagesPath()).getAbsolutePath() + File.separatorChar + ImageHelper.imageMech + File.separatorChar);
        fDialog.setLocationRelativeTo(this);

        fDialog.setVisible(true);

        if (fDialog.getFile() != null) {
            String relativeFilePath = new File(fDialog.getDirectory() + fDialog.getFile()).getAbsolutePath();
            relativeFilePath = "." + File.separatorChar + relativeFilePath.substring(new File(System.getProperty("user.dir")).getAbsolutePath().length() + 1);
            getProtomech().getFluff().setMMLImagePath(relativeFilePath);
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
