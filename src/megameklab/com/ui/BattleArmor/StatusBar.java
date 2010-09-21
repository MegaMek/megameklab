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

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import megamek.common.BattleArmor;
import megamek.common.EntityWeightClass;
import megamek.common.verifier.TestEntity;
import megameklab.com.util.ITab;
import megameklab.com.util.SpringLayoutHelper;
import megameklab.com.util.UnitUtil;

public class StatusBar extends ITab {

    /**
     *
     */
    private static final long serialVersionUID = -6754327753693500675L;

    private JPanel tonnagePanel = new JPanel();
    private JPanel movementPanel = new JPanel();
    private JPanel bvPanel = new JPanel();
    private JLabel move = new JLabel();
    private JLabel bvLabel = new JLabel();
    private JLabel tons = new JLabel();
    // private EntityVerifier entityVerifier = new EntityVerifier(new
    // File("data/BattleArmorfiles/UnitVerifierOptions.xml"));
    private TestEntity testEntity = null;

    public StatusBar(BattleArmor unit) {
        this.unit = unit;

        // testEntity = new TestBattleArmor(getBattleArmor(),
        // entityVerifier.BattleArmorOption, null);
        setLayout(new SpringLayout());
        this.add(movementPanel());
        this.add(bvPanel());
        this.add(tonnagePanel());

        SpringLayoutHelper.setupSpringGrid(this, 4);
        refresh();
    }

    public JPanel movementPanel() {
        int walk = getBattleArmor().getOriginalWalkMP();
        int jump = getBattleArmor().getOriginalJumpMP();

        move.setText("Movement: " + walk + "/" + jump);
        movementPanel.add(move);
        return movementPanel;
    }

    public JPanel bvPanel() {
        int bv = getBattleArmor().calculateBattleValue();
        bvLabel.setText("BV: " + bv);
        bvPanel.add(bvLabel);

        return bvPanel;
    }

    public JPanel tonnagePanel() {
        float tonnage = EntityWeightClass.getClassLimit(getBattleArmor().getWeightClass());
        float currentTonnage;

        currentTonnage = getBattleArmor().getWeight();
        currentTonnage += UnitUtil.getUnallocatedAmmoTonnage(getBattleArmor());

        tons.setText("Weight: " + currentTonnage + "/" + tonnage);
        tonnagePanel.add(tons);

        return tonnagePanel;
    }

    public void refresh() {

        int walk = getBattleArmor().getOriginalWalkMP();
        int jump = getBattleArmor().getOriginalJumpMP();
        float tonnage = getBattleArmor().getWeight();
        float currentTonnage;
        int bv = getBattleArmor().calculateBattleValue();

        // testEntity = new TestMech(getBattleArmor(),
        // entityVerifier.BattleArmorOption, null);

        currentTonnage = getBattleArmor().getWeight();
        currentTonnage += UnitUtil.getUnallocatedAmmoTonnage(getBattleArmor());

        tons.setText("Weight: " + currentTonnage + "/" + tonnage);
        tons.setToolTipText("Current Weight/Max Weight");
        if (currentTonnage > tonnage) {
            tons.setForeground(Color.red);
        } else {
            tons.setForeground(Color.black);
        }

        bvLabel.setText("BV: " + bv);
        bvLabel.setToolTipText("BV 2.0");

        move.setText("Movement: " + walk + "/" + jump);
        move.setToolTipText("Walk/Jump MP");

    }

}