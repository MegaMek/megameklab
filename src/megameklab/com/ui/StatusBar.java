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

package megameklab.com.ui;

import java.awt.Color;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import megamek.common.Mech;
import megamek.common.verifier.EntityVerifier;
import megamek.common.verifier.TestMech;

public class StatusBar extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = -6754327753693500675L;
    private Mech unit;
    private JPanel heatPanel = new JPanel();
    private JPanel tonnagePanel = new JPanel();
    private JPanel movementPanel = new JPanel();
    private JPanel bvPanel = new JPanel();
    private JLabel move = new JLabel();
    private JLabel bvLabel = new JLabel();
    private JLabel tons = new JLabel();
    private JLabel heatSink = new JLabel();
    private EntityVerifier entityVerifier = new EntityVerifier(new File("data/mechfiles/UnitVerifierOptions.xml"));
    private TestMech testEntity = null;
    
    public StatusBar(Mech unit) {
        this.unit = unit;

        testEntity = new TestMech(unit, entityVerifier.mechOption, null);
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.add(movementPanel());
        this.add(bvPanel());
        this.add(tonnagePanel());
        this.add(heatPanel());

        refresh();
    }

    public JPanel movementPanel() {
        int walk = unit.getOriginalWalkMP();
        int run = unit.getOriginalRunMPwithoutMASC();
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

        tons.setText("Tonnage: " + currentTonnage + "/" + tonnage);
        tonnagePanel.add(tons);
        
        return tonnagePanel;
    }
    
    public JPanel heatPanel() {
        int heat = unit.getHeatCapacity();

        heatSink.setText("Heat: 0/" + heat);
        heatPanel.add(heatSink);
     
        return heatPanel;
    }
    
    public void refresh() {
        int walk = unit.getOriginalWalkMP();
        int run = unit.getOriginalRunMPwithoutMASC();
        int jump = unit.getOriginalJumpMP();
        int heat = unit.getHeatCapacity();
        float tonnage = unit.getWeight();
        float currentTonnage;
        int bv = unit.calculateBattleValue();
         

        testEntity = new TestMech(unit, entityVerifier.mechOption, null);

        currentTonnage = testEntity.calculateWeight();
        
        heatSink.setText("Heat: 0/" + heat);
        tons.setText("Tonnage: " +currentTonnage+"/"+ tonnage);
        
        if ( currentTonnage > tonnage )
            tons.setForeground(Color.red);
        else
            tons.setForeground(Color.black);
        
        bvLabel.setText("BV: " + bv);
        move.setText("Movement: " + walk + "/" + run + "/" + jump);

        
    }

}