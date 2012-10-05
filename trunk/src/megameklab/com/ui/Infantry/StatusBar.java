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

package megameklab.com.ui.Infantry;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import megamek.common.Infantry;
import megamek.common.verifier.EntityVerifier;
import megamek.common.verifier.TestMech;
import megameklab.com.util.ITab;
import megameklab.com.util.SpringLayoutHelper;
import megameklab.com.util.UnitUtil;

public class StatusBar extends ITab {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6757027053670782126L;
	
	private JPanel tonnagePanel = new JPanel();
	private JPanel movementPanel = new JPanel();
	private JPanel bvPanel = new JPanel();
	private JPanel damagePanel = new JPanel();
	private JLabel move = new JLabel();
	private JLabel bvLabel = new JLabel();
	private JLabel tons = new JLabel();
	private JLabel damageLabel = new JLabel();
	
	public StatusBar(Infantry unit) {
		this.unit = unit;
		
		//testEntity = new TestMech(getMech(), entityVerifier.mechOption, null);
		setLayout(new SpringLayout());
		this.add(movementPanel());
		this.add(bvPanel());
		this.add(damagePanel());
		this.add(tonnagePanel());

		SpringLayoutHelper.setupSpringGrid(this, 4);
		refresh();
	}
	 
	public JPanel bvPanel() {
		int bv = getInfantry().calculateBattleValue();
		bvLabel.setText("BV: " + bv);
		bvPanel.add(bvLabel);
		 
		return bvPanel;
	}
	
	public JPanel damagePanel() {
		double damage = getInfantry().getDamagePerTrooper();
		damageLabel.setText("Damage/Trooper: " + damage);
		damagePanel.add(damageLabel);
		 
		return damagePanel;
	}
	
	public JPanel movementPanel() {
        int walk = getInfantry().getOriginalWalkMP();
        int jump = getInfantry().getOriginalJumpMP();

        move.setText("Movement: " + walk + "/" + jump);
        movementPanel.add(move);
        return movementPanel;
    }
	
	public JPanel tonnagePanel() {
        float tonnage = getInfantry().getWeight();
        
        tons.setText("Tonnage: " + tonnage);
        tonnagePanel.add(tons);

        return tonnagePanel;
    }
	
	public void refresh() {

        int walk = getInfantry().getOriginalWalkMP();
        int jump = getInfantry().getOriginalJumpMP();
        float tonnage = getInfantry().getWeight();
        int bv = getInfantry().calculateBattleValue();
        double damage = getInfantry().getDamagePerTrooper();

        tons.setText("Tonnage: " + tonnage);

        bvLabel.setText("BV: " + bv);
        bvLabel.setToolTipText("BV 2.0");

        move.setText("Movement: " + walk + "/" + jump);
        move.setToolTipText("Walk/Jump MP");
        
        damageLabel.setText("Damage/Trooper: " + damage);

    }
	
}
