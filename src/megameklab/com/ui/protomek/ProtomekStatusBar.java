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

import megamek.common.verifier.EntityVerifier;
import megamek.common.verifier.TestProtomech;
import megameklab.com.ui.MegaMekLabMainUI;
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
    
    private JButton btnValidate = new JButton("Validate Unit");
    private JButton btnFluffImage = new JButton("Set Fluff Image");
    private JLabel crits = new JLabel();
    private JLabel bvLabel = new JLabel();
    private JLabel tons = new JLabel();
    private JLabel heatSink = new JLabel();
    private JLabel cost = new JLabel();
    private EntityVerifier entityVerifier = EntityVerifier.getInstance(new File("data/mechfiles/UnitVerifierOptions.xml"));
    private TestProtomech testEntity = null;
    private DecimalFormat formatter;
    private JFrame parentFrame;

    private RefreshListener refresh;

    public ProtomekStatusBar(MegaMekLabMainUI parent) {
        super(parent);
        parentFrame = parent;

        formatter = new DecimalFormat();
        testEntity = new TestProtomech(getProtomech(), entityVerifier.mechOption, null);
        btnValidate.addActionListener(ev -> UnitUtil.showValidation(getProtomech(), getParentFrame()));
        btnFluffImage.addActionListener(ev -> getFluffImage());

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
        long currentCost = (long) Math.round(getProtomech().getCost(false));

        testEntity = new TestProtomech(getProtomech(), entityVerifier.mechOption, null);

        currentTonnage = testEntity.calculateWeight() * 1000;

        tons.setText("Mass: " + currentTonnage + "/" + tonnage);
        tons.setToolTipText("Current Tonnage/Max Tonnage");
        if (currentTonnage > tonnage) {
            tons.setForeground(Color.red);
        } else {
            tons.setForeground(Color.black);
        }

        bvLabel.setText("BV: " + bv);
        bvLabel.setToolTipText("BV 2.0");

        cost.setText("Cost: " + formatter.format(currentCost) + " C-bills");

        crits.setText("Criticals: " +  currentCrits + "/" + maxCrits);
        if(currentCrits > maxCrits) {
            crits.setForeground(Color.red);
        } else {
            crits.setForeground(Color.BLACK);
        }

    }

    private void getFluffImage() {
        //copied from mech StatusBar
        FileDialog fDialog = new FileDialog(getParentFrame(), "Image Path", FileDialog.LOAD);
        fDialog.setDirectory(new File(ImageHelper.fluffPath).getAbsolutePath() + File.separatorChar + ImageHelper.imageMech + File.separatorChar);
        fDialog.setLocationRelativeTo(this);

        fDialog.setVisible(true);

        if (fDialog.getFile() != null) {
            String relativeFilePath = new File(fDialog.getDirectory() + fDialog.getFile()).getAbsolutePath();
            relativeFilePath = "." + File.separatorChar + relativeFilePath.substring(new File(System.getProperty("user.dir").toString()).getAbsolutePath().length() + 1);
            getProtomech().getFluff().setMMLImagePath(relativeFilePath);
        }
        refresh.refreshPreview();
        return;
    }

    private JFrame getParentFrame() {
        return parentFrame;
    }

    public void addRefreshedListener(RefreshListener l) {
        refresh = l;
    }

}
