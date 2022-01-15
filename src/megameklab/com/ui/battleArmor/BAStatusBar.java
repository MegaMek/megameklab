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

package megameklab.com.ui.battleArmor;

import megamek.common.BattleArmor;
import megamek.common.verifier.EntityVerifier;
import megamek.common.verifier.TestBattleArmor;
import megameklab.com.ui.util.ITab;
import megameklab.com.ui.util.RefreshListener;
import megameklab.com.ui.util.WrapLayout;
import megameklab.com.util.ImageHelper;
import megameklab.com.util.UnitUtil;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.text.DecimalFormat;

public class BAStatusBar extends ITab {

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
    public BAStatusBar(final BAMainUI parent) {
        super(parent);
        parentFrame = parent;
        formatter = new DecimalFormat();
        JButton showEquipmentDatabase = new JButton("Show Equipment Database");
        showEquipmentDatabase.addActionListener(evt -> parent.getFloatingEquipmentDatabase().setVisible(true));
        JButton btnValidate = new JButton("Validate Unit");
        btnValidate.addActionListener(evt -> UnitUtil.showValidation(getBattleArmor(), getParentFrame()));
        JButton btnFluffImage = new JButton("Set Fluff Image");
        btnFluffImage.addActionListener(evt -> getFluffImage());
        invalid.setText("Invalid");
        invalid.setForeground(Color.RED);
        invalid.setVisible(false);

        setLayout(new WrapLayout(FlowLayout.LEFT, 22, 5));
        add(showEquipmentDatabase);
        add(btnValidate);
        add(btnFluffImage);
        add(movementPanel());
        add(bvPanel());
        add(tonnagePanel());
        add(invalid);
        add(cost);
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
        final double maxKilos = getBattleArmor().getTrooperWeight() * 1000;
        double currentKilos;
        final int bv = getBattleArmor().calculateBattleValue();
        final long currentCost = Math.round(getBattleArmor().getCost(false));

        TestBattleArmor testBA = new TestBattleArmor(getBattleArmor(), entityVerifier.baOption,
                null);
        currentKilos = testBA.calculateWeight(BattleArmor.LOC_SQUAD);
        currentKilos += UnitUtil.getUnallocatedAmmoTonnage(getBattleArmor());
        currentKilos *= 1000;

        tons.setText(String.format("Suit Weight: %,.0f/%,.0f (%,.0f Remaining)", currentKilos, maxKilos, maxKilos - currentKilos));
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