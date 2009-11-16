/*
 * MegaMekLab - Copyright (C) 2009
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

package megameklab.com.ui.Vehicle;

import java.awt.Color;
import java.io.File;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import megamek.common.Tank;
import megamek.common.verifier.EntityVerifier;
import megamek.common.verifier.TestTank;
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
    private EntityVerifier entityVerifier = new EntityVerifier(new File("data/Tankfiles/UnitVerifierOptions.xml"));
    private TestTank testEntity = null;

    public StatusBar(Tank unit) {
        this.unit = unit;

        testEntity = new TestTank(unit, entityVerifier.mechOption, null);
        setLayout(new SpringLayout());
        this.add(movementPanel());
        this.add(bvPanel());
        this.add(tonnagePanel());

        SpringLayoutHelper.setupSpringGrid(this, 4);
        refresh();
    }

    public JPanel movementPanel() {
        int walk = unit.getOriginalWalkMP();
        int run = unit.getRunMP(false, true);
        int jump = unit.getOriginalJumpMP();

        move.setText("Movement: " + walk + "/" + run + "/" + jump);
        movementPanel.add(move);
        return movementPanel;
    }

    public JPanel bvPanel() {
        int bv = unit.calculateBattleValue();
        bvLabel.setText("BV: " + bv);
        bvPanel.add(bvLabel);

        return bvPanel;
    }

    public JPanel tonnagePanel() {
        float tonnage = unit.getWeight();
        float currentTonnage;

        currentTonnage = testEntity.calculateWeight();
        currentTonnage += UnitUtil.getUnallocatedAmmoTonnage(unit);

        tons.setText("Tonnage: " + currentTonnage + "/" + tonnage);
        tonnagePanel.add(tons);

        return tonnagePanel;
    }

    public void refresh() {

        int walk = unit.getOriginalWalkMP();
        int run = unit.getRunMP(true, true);
        int jump = unit.getOriginalJumpMP();
        float tonnage = unit.getWeight();
        float currentTonnage;
        int bv = unit.calculateBattleValue();

        testEntity = new TestTank((Tank) unit, entityVerifier.mechOption, null);

        currentTonnage = testEntity.calculateWeight();
        currentTonnage += UnitUtil.getUnallocatedAmmoTonnage(unit);

        tons.setText("Tonnage: " + currentTonnage + "/" + tonnage);
        tons.setToolTipText("Current Tonnage/Max Tonnage");
        if (currentTonnage > tonnage) {
            tons.setForeground(Color.red);
        } else {
            tons.setForeground(Color.black);
        }

        bvLabel.setText("BV: " + bv);
        bvLabel.setToolTipText("BV 2.0");

        move.setText("Movement: " + walk + "/" + run + "/" + jump);
        move.setToolTipText("Walk/Run/Jump MP");

    }
}