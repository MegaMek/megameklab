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

package megameklab.com.ui.Vehicle.views;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import megamek.common.Engine;
import megamek.common.EquipmentType;
import megamek.common.MiscType;
import megamek.common.Mounted;
import megamek.common.TechConstants;
import megamek.common.verifier.EntityVerifier;
import megamek.common.verifier.TestTank;
import megameklab.com.ui.EntitySource;
import megameklab.com.util.IView;
import megameklab.com.util.UnitUtil;

public class SummaryView extends IView{

    /**
     *
     */
    private static final long serialVersionUID = -8492419359401770037L;

    private JTextField txtStructTon = new JTextField("?");
    private JTextField txtEngineTon = new JTextField("?");
    private JTextField txtLiftTon = new JTextField("?");
    private JTextField txtControlsTon = new JTextField("?");
    private JTextField txtJumpTon = new JTextField("?");
    private JTextField txtHeatTon = new JTextField("?");
    private JTextField txtArmorTon = new JTextField("?");
    private JTextField txtTurretTon = new JTextField("?");
    private JTextField txtRearTurretTon = new JTextField("?");
    private JTextField txtSponsonTon = new JTextField("?");
    private JTextField txtPowerAmpTon = new JTextField("?");
    private JTextField txtEquipTon = new JTextField("?");

    private JTextField txtEngineCrit = new JTextField("?");
    private JTextField txtJumpCrit = new JTextField("?");
    private JTextField txtArmorCrit = new JTextField("?");   

    private JTextField txtStructAvail = new JTextField("?");
    private JTextField txtEngineAvail = new JTextField("?");
    private JTextField txtLiftAvail = new JTextField("?");
    private JTextField txtControlsAvail = new JTextField("?");
    private JTextField txtJumpAvail = new JTextField("?");
    private JTextField txtHeatAvail = new JTextField("?");
    private JTextField txtArmorAvail = new JTextField("?");
    private JTextField txtTurretAvail = new JTextField("?");
    private JTextField txtRearTurretAvail = new JTextField("?");
    private JTextField txtSponsonAvail = new JTextField("?");
    private JTextField txtPowerAmpAvail = new JTextField("?");
    private JTextField txtEquipAvail = new JTextField("?");
    
    
    

    private EntityVerifier entityVerifier = EntityVerifier.getInstance(new File("data/mechfiles/UnitVerifierOptions.xml"));

    public SummaryView(EntitySource eSource) {
        super(eSource);

        Vector<JTextField> valueFields = new Vector<JTextField>();

        valueFields.add(txtStructTon);
        valueFields.add(txtEngineTon);
        valueFields.add(txtLiftTon);
        valueFields.add(txtControlsTon);
        valueFields.add(txtHeatTon);
        valueFields.add(txtJumpTon);
        valueFields.add(txtArmorTon);
        valueFields.add(txtTurretTon);
        valueFields.add(txtRearTurretTon);
        valueFields.add(txtSponsonTon);
        valueFields.add(txtPowerAmpTon);
        valueFields.add(txtEquipTon);        


        valueFields.add(txtEngineCrit);
        valueFields.add(txtArmorCrit);
        valueFields.add(txtJumpCrit);

        Dimension size = new Dimension(45,25);
        for(JTextField field : valueFields) {
            field.setEditable(false);
            field.setSize(size);
            field.setPreferredSize(size);
            field.setMinimumSize(size);
            field.setMaximumSize(size);
            field.setHorizontalAlignment(SwingConstants.RIGHT);
        }

        valueFields.removeAllElements();

        valueFields.add(txtStructAvail);
        valueFields.add(txtEngineAvail);
        valueFields.add(txtLiftAvail);
        valueFields.add(txtControlsAvail);
        valueFields.add(txtJumpAvail);
        valueFields.add(txtHeatAvail);
        valueFields.add(txtArmorAvail);
        valueFields.add(txtTurretAvail);
        valueFields.add(txtRearTurretAvail);
        valueFields.add(txtSponsonAvail);
        valueFields.add(txtPowerAmpAvail);
        valueFields.add(txtEquipAvail);
        
        size = new Dimension(80,25);
        for(JTextField field : valueFields) {
            field.setEditable(false);
            field.setSize(size);
            field.setPreferredSize(size);
            field.setMinimumSize(size);
            field.setMaximumSize(size);
        }

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        size = new Dimension(120,25);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0,0,0,5);
        this.add(createLabel("Category", size, SwingConstants.CENTER), gbc);
        gbc.gridy++;
        this.add(createLabel("Internal Structure:", size, SwingConstants.RIGHT), gbc);
        gbc.gridy++;
        this.add(createLabel("Engine:", size, SwingConstants.RIGHT), gbc);
        gbc.gridy++;
        this.add(createLabel("Lift/Dive/Rotor:", size, SwingConstants.RIGHT), gbc);
        gbc.gridy++;
        this.add(createLabel("Controls:", size, SwingConstants.RIGHT), gbc);
        gbc.gridy++;
        this.add(createLabel("Jump Jets:", size, SwingConstants.RIGHT), gbc);
        gbc.gridy++;
        this.add(createLabel("Heat Sinks:", size, SwingConstants.RIGHT), gbc);
        gbc.gridy++;
        this.add(createLabel("Armor:", size, SwingConstants.RIGHT), gbc);
        gbc.gridy++;
        this.add(createLabel("Turret:", size, SwingConstants.RIGHT), gbc);
        gbc.gridy++;
        this.add(createLabel("Rear Turret:", size, SwingConstants.RIGHT), gbc);
        gbc.gridy++;
        this.add(createLabel("Sponsons", size, SwingConstants.RIGHT), gbc);
        gbc.gridy++;
        this.add(createLabel("Power Amplifiers:", size, SwingConstants.RIGHT), gbc);
        gbc.gridy++;
        this.add(createLabel("Equipment:", size, SwingConstants.RIGHT), gbc);

        size = new Dimension(45,25);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(0,0,0,1);
        this.add(createLabel("Ton", size, SwingConstants.CENTER), gbc);
        gbc.gridy++;
        this.add(txtStructTon, gbc);
        gbc.gridy++;
        this.add(txtEngineTon, gbc);
        gbc.gridy++;
        this.add(txtLiftTon, gbc);
        gbc.gridy++;
        this.add(txtControlsTon, gbc);
        gbc.gridy++;
        this.add(txtJumpTon, gbc);
        gbc.gridy++;
        this.add(txtHeatTon, gbc);
        gbc.gridy++;
        this.add(txtArmorTon, gbc);
        gbc.gridy++;
        this.add(txtTurretTon, gbc);
        gbc.gridy++;
        this.add(txtRearTurretTon, gbc);
        gbc.gridy++;
        this.add(txtSponsonTon, gbc);
        gbc.gridy++;
        this.add(txtPowerAmpTon, gbc);
        gbc.gridy++;
        this.add(txtEquipTon, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        this.add(createLabel("Crit", size, SwingConstants.CENTER), gbc);
        gbc.gridy++;
        // Structure
        gbc.gridy++;
        this.add(txtEngineCrit, gbc);
        gbc.gridy++;
        this.add(Box.createGlue(), gbc);
        // Lift
        gbc.gridy++;
        this.add(Box.createGlue(), gbc);
        // Controls
        gbc.gridy++;
        this.add(txtJumpCrit, gbc);
        gbc.gridy++;
        this.add(Box.createGlue(), gbc);
        // Heat sinks
        gbc.gridy++;
        this.add(txtArmorCrit, gbc);
        gbc.gridy++;
        this.add(Box.createGlue(), gbc);
        // Turret
        gbc.gridy++;
        this.add(Box.createGlue(), gbc);
        // Rear Turret
        gbc.gridy++;
        this.add(Box.createGlue(), gbc);
        // Sponsons
        gbc.gridy++;
        this.add(Box.createGlue(), gbc);
        // Power Amps
        gbc.gridy++;
        this.add(Box.createGlue(), gbc);
        // Equipment
        gbc.gridy++;
        this.add(Box.createGlue(), gbc);

        size = new Dimension(80,25);
        gbc.gridx = 3;
        gbc.gridy = 0;
        this.add(createLabel("Availability", size, SwingConstants.CENTER), gbc);
        gbc.gridy++;
        this.add(txtStructAvail, gbc);
        gbc.gridy++;
        this.add(txtEngineAvail, gbc);
        gbc.gridy++;
        this.add(txtLiftAvail, gbc);
        gbc.gridy++;
        this.add(txtControlsAvail, gbc);
        gbc.gridy++;
        this.add(txtJumpAvail, gbc);
        gbc.gridy++;
        this.add(txtHeatAvail, gbc);
        gbc.gridy++;
        this.add(txtArmorAvail, gbc);
        gbc.gridy++;
        this.add(txtTurretAvail, gbc);
        gbc.gridy++;
        this.add(txtRearTurretAvail, gbc);
        gbc.gridy++;
        this.add(txtSponsonAvail, gbc);
        gbc.gridy++;
        this.add(txtPowerAmpAvail, gbc);
        gbc.gridy++;
        this.add(txtEquipAvail, gbc);

        setBorder(BorderFactory.createTitledBorder("Summary"));

    }

    private JLabel createLabel(String text, Dimension size, int align) {

        JLabel label = new JLabel(text, SwingConstants.TRAILING);

        setFieldSize(label, size);
        label.setHorizontalAlignment(align);
        return label;
    }

    public void setFieldSize(JComponent box, Dimension maxSize) {
        box.setPreferredSize(maxSize);
        box.setMaximumSize(maxSize);
        box.setMinimumSize(maxSize);
    }

    public void refresh() {

        TestTank testTank = new TestTank(getTank(), entityVerifier.mechOption, null);

        txtStructTon.setText(Double.toString(testTank.getWeightStructure()));
        txtEngineTon.setText(Double.toString(testTank.getWeightEngine()));        
        txtLiftTon.setText(Double.toString(testTank.getTankWeightLifting()));
        txtControlsTon.setText(Double.toString(testTank.getWeightControls()));
        txtHeatTon.setText(Double.toString(testTank.getWeightHeatSinks()));
        txtArmorTon.setText(Double.toString(testTank.getWeightArmor()));
        txtTurretTon.setText(Double.toString(testTank.getTankWeightTurret()));
        txtRearTurretTon.setText(Double.toString(testTank.getTankWeightDualTurret()));
        // Sponsons?
        txtPowerAmpTon.setText(Double.toString(testTank.getWeightPowerAmp()));


        txtEngineCrit.setText(Integer.toString(getEngineCrits()));
        txtArmorCrit.setText(Integer.toString(getArmorCrits()));
        txtJumpCrit.setText(Integer.toString(0));

        runThroughEquipment(testTank);

    }

    private void runThroughEquipment(TestTank testTank) {
    	double weightJJ = 0.0f;
    	double weightEquip = 0.0f;
    	double weightSponson = 0.0f;

        for (Mounted m : getTank().getMisc()) {
            MiscType mt = (MiscType) m.getType();
            if(UnitUtil.isArmorOrStructure(mt)) {
                continue;
            }
            else if (mt.hasFlag(MiscType.F_SPONSON_TURRET)) {
                weightSponson = mt.getTonnage(getTank());
            } else if (mt.hasFlag(MiscType.F_JUMP_JET)) {
                weightJJ += mt.getTonnage(getTank(), m.getLocation());
            } else if (mt.hasFlag(MiscType.F_HEAT_SINK)
                    || mt.hasFlag(MiscType.F_DOUBLE_HEAT_SINK)) {
                continue;
            }
            else {
                weightEquip += mt.getTonnage(getTank(), m.getLocation());
            }
        }
        for (Mounted m : getTank().getWeaponList()) {
            EquipmentType et = m.getType();
            weightEquip += et.getTonnage(getTank());
        }
        for (Mounted m : getTank().getAmmo()) {
            EquipmentType et = m.getType();
            weightEquip += et.getTonnage(getTank());
        }
        txtJumpTon.setText(Double.toString(weightJJ));
        txtEquipTon.setText(Double.toString(weightEquip));
        txtSponsonTon.setText(Double.toString(weightSponson));
        
        if (weightJJ > 0) {
            txtJumpCrit.setText(Integer.toString(1)); 
        } else {
            txtJumpCrit.setText(Integer.toString(0));
        }
    }

    private int getEngineCrits() {
        Engine engine = getTank().getEngine();
        int usedSlots = 0;
        if (engine.isFusion()) {
            if (engine.getEngineType() == Engine.LIGHT_ENGINE) {
                usedSlots++;
            }
            if (engine.getEngineType() == Engine.XL_ENGINE) {
                if (engine.hasFlag(Engine.CLAN_ENGINE)) {
                    usedSlots++;
                } else {
                    usedSlots += 2;
                }
            }
            if (engine.getEngineType() == Engine.XXL_ENGINE) {
                if (engine.hasFlag(Engine.CLAN_ENGINE)) {
                    usedSlots += 2;
                } else {
                    usedSlots += 4;
                }
            }
        }
        if (engine.hasFlag(Engine.LARGE_ENGINE)) {
            usedSlots++;
        }
        if (engine.getEngineType() == Engine.COMPACT_ENGINE) {
            usedSlots--;
        }
        return usedSlots;
    }
    
    private int getArmorCrits() { 
        // different armor types take different amount of slots
        int usedSlots = 0;
        if (!getTank().hasPatchworkArmor()) {
            int type = getTank().getArmorType(1);
            switch (type) {
                case EquipmentType.T_ARMOR_FERRO_FIBROUS:
                    if (TechConstants.isClan(getTank().getArmorTechLevel(1))) {
                        usedSlots++;
                    } else {
                        usedSlots += 2;
                    }
                    break;
                case EquipmentType.T_ARMOR_HEAVY_FERRO:
                    usedSlots += 3;
                    break;
                case EquipmentType.T_ARMOR_LIGHT_FERRO:
                case EquipmentType.T_ARMOR_FERRO_LAMELLOR:
                case EquipmentType.T_ARMOR_REFLECTIVE:
                case EquipmentType.T_ARMOR_HARDENED:
                    usedSlots++;
                    break;
                case EquipmentType.T_ARMOR_STEALTH:
                    usedSlots += 2;
                    break;
                case EquipmentType.T_ARMOR_REACTIVE:
                    if (TechConstants.isClan(getTank().getArmorTechLevel(1))) {
                        usedSlots++;
                    } else {
                        usedSlots += 2;
                    }
                    break;
                default:
                    break;
            }

        }
        return usedSlots;
    }

}
