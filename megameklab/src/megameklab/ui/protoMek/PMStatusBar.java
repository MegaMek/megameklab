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
package megameklab.ui.protoMek;

import megamek.common.verifier.EntityVerifier;
import megamek.common.verifier.TestProtomech;
import megameklab.ui.util.ITab;
import megameklab.ui.util.RefreshListener;
import megamek.client.ui.WrapLayout;
import megameklab.util.ImageHelper;
import megameklab.util.UnitUtil;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.text.DecimalFormat;

/**
 * Status bar for protomech construction
 * 
 * @author Neoancient
 *
 */
public class PMStatusBar extends ITab {
    
    private final JLabel crits = new JLabel();
    private final JLabel bvLabel = new JLabel();
    private final JLabel tons = new JLabel();
    private final JLabel cost = new JLabel();
    private final JLabel invalid = new JLabel();
    private final TestProtomech testEntity;
    private final DecimalFormat formatter;
    private final JFrame parentFrame;

    private RefreshListener refresh;

    public PMStatusBar(PMMainUI parent) {
        super(parent);
        parentFrame = parent;

        formatter = new DecimalFormat();
        EntityVerifier entityVerifier = EntityVerifier.getInstance(new File("data/mechfiles/UnitVerifierOptions.xml"));
        testEntity = new TestProtomech(getProtomech(), entityVerifier.mechOption, null);
        JButton showEquipmentDatabase = new JButton("Show Equipment Database");
        showEquipmentDatabase.addActionListener(evt -> parent.getFloatingEquipmentDatabase().setVisible(true));
        JButton btnValidate = new JButton("Validate Unit");
        btnValidate.addActionListener(ev -> UnitUtil.showValidation(getProtomech(), getParentFrame()));
        JButton btnFluffImage = new JButton("Set Fluff Image");
        btnFluffImage.addActionListener(ev -> getFluffImage());
        invalid.setText("Invalid");
        invalid.setForeground(Color.RED);
        invalid.setVisible(false);

        setLayout(new WrapLayout(FlowLayout.LEFT, 22, 5));
        add(showEquipmentDatabase);
        add(btnValidate);
        add(btnFluffImage);
        add(tons);
        add(crits);
        add(bvLabel);
        add(invalid);
        add(cost);
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

        tons.setText(String.format("Tonnage: %,.0f/%,.0f (%,.0f Remaining)", currentTonnage, tonnage, tonnage - currentTonnage));
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
        fDialog.setDirectory(new File(ImageHelper.fluffPath).getAbsolutePath() + File.separatorChar + ImageHelper.imageMech + File.separatorChar);
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
