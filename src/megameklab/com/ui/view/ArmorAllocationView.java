/*
 * MegaMekLab - Copyright (C) 2017 - The MegaMek Team
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
package megameklab.com.ui.view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import megamek.common.*;
import megamek.common.util.EncodeControl;
import megamek.common.verifier.TestSupportVehicle;
import megameklab.com.ui.view.listeners.ArmorAllocationListener;
import megameklab.com.ui.view.listeners.BuildListener;
import megameklab.com.util.UnitUtil;

/**
 * Panel for allocating armor to various locations on an Entity. The assignment of armor values for specific
 * locations is delegated to ArmorLocationView. This class handles positioning of the subviews to approximate
 * the position on the unit and tracking the total amount of armor allocated.
 * 
 * @author Neoancient
 *
 */
public class ArmorAllocationView extends BuildView implements
        ArmorLocationView.ArmorLocationListener {
    
    private static final long serialVersionUID = 1707528067499186372L;
    
    private final List<ArmorAllocationListener> listeners = new CopyOnWriteArrayList<>();
    public void addListener(ArmorAllocationListener l) {
        listeners.add(l);
    }
    public void removeListener(ArmorAllocationListener l) {
        listeners.remove(l);
    }
    
    private static final int[][] MEK_LAYOUT = {
            {-1, -1, Mech.LOC_HEAD, -1, -1},
            {Mech.LOC_LARM, Mech.LOC_LT, Mech.LOC_CT, Mech.LOC_RT, Mech.LOC_RARM},
            {-1, Mech.LOC_LLEG, Mech.LOC_CLEG, Mech.LOC_RLEG, -1}
    };
    
    private static final int[][] PROTOMEK_LAYOUT = {
            {Protomech.LOC_MAINGUN, Protomech.LOC_HEAD, -1 },
            {Protomech.LOC_LARM, Protomech.LOC_TORSO, Protomech.LOC_RARM },
            {-1, Protomech.LOC_LEG, -1}
    };
    
    private static final int[][] TANK_LAYOUT = {
            {-1, Tank.LOC_FRONT, -1},
            {Tank.LOC_LEFT, Tank.LOC_TURRET, Tank.LOC_RIGHT},
            {-1, Tank.LOC_TURRET_2, -1},
            {-1, Tank.LOC_REAR, -1}
    };

    private static final int[][] SH_TANK_LAYOUT = {
            {-1, SuperHeavyTank.LOC_FRONT, -1},
            {SuperHeavyTank.LOC_FRONTLEFT, SuperHeavyTank.LOC_TURRET, SuperHeavyTank.LOC_FRONTRIGHT},
            {SuperHeavyTank.LOC_REARLEFT, SuperHeavyTank.LOC_TURRET_2, SuperHeavyTank.LOC_REARRIGHT},
            {-1, SuperHeavyTank.LOC_REAR, -1}
    };

    private static final int[][] VTOL_LAYOUT = {
            {-1, VTOL.LOC_FRONT, -1},
            {VTOL.LOC_LEFT, VTOL.LOC_ROTOR, VTOL.LOC_RIGHT},
            {-1, VTOL.LOC_TURRET, -1},
            {-1, VTOL.LOC_REAR, -1}
    };
    
    private static final int[][] AERODYNE_LAYOUT = {
            {-1, Aero.LOC_NOSE, -1},
            {Aero.LOC_LWING, -1, Aero.LOC_RWING},
            {-1, Aero.LOC_AFT, -1}
    };
    
    private static final int[][] CAPITAL_LAYOUT = {
            {-1, Jumpship.LOC_NOSE, -1},
            {Jumpship.LOC_FLS, -1, Jumpship.LOC_FRS},
            {Jumpship.LOC_ALS, -1, Jumpship.LOC_ARS},
            {-1, Jumpship.LOC_AFT, -1}
    };
    
    private final List<ArmorLocationView> locationViews = new ArrayList<>();
    private final JPanel panLocations = new JPanel();
    private final JTextField txtUnallocated = new JTextField();
    private final JTextField txtAllocated = new JTextField();
    private final JTextField txtTotal = new JTextField();
    private final JTextField txtMaxPossible = new JTextField();
    private final JTextField txtWasted = new JTextField();
    private final JTextField txtPointsPerTon = new JTextField();
    private final JButton btnAutoAllocate = new JButton();
    private final JLabel lblPointsPerTon = new JLabel("", SwingConstants.RIGHT);

    private final ResourceBundle resourceMap = ResourceBundle.getBundle("megameklab.resources.Views", new EncodeControl()); //$NON-NLS-1$
    private long entitytype;
    private boolean showPatchwork = false;
    private String tooltipFormat;
    
    public ArmorAllocationView(ITechManager techManager, long entitytype) {
        this.entitytype = entitytype;
        initUI();
    }
    
    private void initUI() {
        tooltipFormat = resourceMap.getString("ArmorAllocationView.locationTooltip.format");
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        panLocations.setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        add(panLocations, gbc);
        
        updateLayout();
        gbc.gridy++;
        
        gbc.gridwidth = 1;
        add(new JLabel(resourceMap.getString("ArmorAllocationView.txtUnallocated.text"), SwingConstants.RIGHT), gbc); //$NON-NLS-1$
        gbc.gridx = 1;
        txtUnallocated.setEditable(false);
        setFieldSize(txtUnallocated, editorSizeLg);
        add(txtUnallocated, gbc);
        
        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel(resourceMap.getString("ArmorAllocationView.txtAllocated.text"), SwingConstants.RIGHT), gbc); //$NON-NLS-1$
        gbc.gridx = 1;
        txtAllocated.setEditable(false);
        setFieldSize(txtAllocated, editorSizeLg);
        add(txtAllocated, gbc);
        
        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel(resourceMap.getString("ArmorAllocationView.txtTotal.text"), SwingConstants.RIGHT), gbc); //$NON-NLS-1$
        gbc.gridx = 1;
        txtTotal.setEditable(false);
        setFieldSize(txtTotal, editorSizeLg);
        add(txtTotal, gbc);
        
        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel(resourceMap.getString("ArmorAllocationView.txtMaxPossible.text"), SwingConstants.RIGHT), gbc); //$NON-NLS-1$
        gbc.gridx = 1;
        txtMaxPossible.setEditable(false);
        setFieldSize(txtMaxPossible, editorSizeLg);
        add(txtMaxPossible, gbc);
        
        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel(resourceMap.getString("ArmorAllocationView.txtWasted.text"), SwingConstants.RIGHT), gbc); //$NON-NLS-1$
        gbc.gridx = 1;
        txtWasted.setEditable(false);
        setFieldSize(txtWasted, editorSizeLg);
        add(txtWasted, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        lblPointsPerTon.setText(resourceMap.getString("ArmorAllocationView.txtPointsPerTon.text"));
        add(lblPointsPerTon, gbc); //$NON-NLS-1$
        gbc.gridx = 1;
        txtPointsPerTon.setEditable(false);
        setFieldSize(txtPointsPerTon, editorSizeLg);
        txtPointsPerTon.setToolTipText(resourceMap.getString("ArmorAllocationView.txtPointsPerTon.tooltip"));
        add(txtPointsPerTon, gbc);

        btnAutoAllocate.setText(resourceMap.getString("ArmorAllocationView.btnAutoAllocate.text")); //$NON-NLS-1$
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(Box.createVerticalStrut(18), gbc);
        gbc.gridy++;
        add(btnAutoAllocate, gbc);
        btnAutoAllocate.addActionListener(e -> listeners.forEach(ArmorAllocationListener::autoAllocateArmor));
    }

    public void setFromEntity(Entity en) {
        setEntityType(en.getEntityType());
        for (ArmorLocationView locView : locationViews) {
            final int location = locView.getLocationIndex();
            final Integer maxArmor = UnitUtil.getMaxArmor(en,  location);
            if (location < en.locations() && ((maxArmor == null) || (maxArmor > 0))) {
                locView.setVisible(true);
                locView.updateLocation(en.getLocationAbbr(location),
                        en.hasRearArmor(location));
                locView.setMaxPoints(UnitUtil.getMaxArmor(en, location));
                locView.setPoints(en.getArmor(location));
                if (en.hasRearArmor(location)) {
                    locView.setPointsRear(en.getArmor(location, true));
                } else {
                    locView.setPointsRear(0);
                }
                if (en.hasETypeFlag(Entity.ETYPE_SMALL_CRAFT)
                        || en.hasETypeFlag(Entity.ETYPE_JUMPSHIP)) {
                    locView.setMinimum((int) (UnitUtil.getSIBonusArmorPoints(en) / locationViews.size()));
                }
                if (showPatchwork) {
                    double pointsPerTon = UnitUtil.getArmorPointsPerTon(en, en.getArmorType(location),  en.getArmorTechLevel(location));
                    double points = en.getArmor(location, false);
                    if (en.hasRearArmor(location)) {
                        points += en.getArmor(location, true);
                    }
                    locView.setToolTipText(String.format(tooltipFormat, pointsPerTon,
                            points / pointsPerTon));
                }
            } else {
                locView.setVisible(false);
                locView.setPoints(0);
                locView.setPointsRear(0);
            }
        }
        int maxArmorPoints = UnitUtil.getMaximumArmorPoints(en);
        int raw = (int) (UnitUtil.getRawArmorPoints(en, en.getLabArmorTonnage())
                + UnitUtil.getSIBonusArmorPoints(en));
        int currentPoints = en.getTotalOArmor();
        int armorPoints;
        if (showPatchwork) {
            armorPoints = currentPoints;
            raw = currentPoints;
            btnAutoAllocate.setEnabled(false);
        } else {
            armorPoints = Math.min(raw, maxArmorPoints);
            btnAutoAllocate.setEnabled(true);
        }
        int wastedPoints = Math.max(0, raw - armorPoints);
        txtUnallocated.setText(Integer.toString(armorPoints - currentPoints));
        txtAllocated.setText(String.valueOf(currentPoints));
        if (armorPoints != currentPoints) {
            txtUnallocated.setForeground(Color.RED);
        } else {
            txtUnallocated.setForeground(UIManager.getColor("Label.foreground"));
        }
        txtTotal.setText(String.valueOf(armorPoints));
        txtMaxPossible.setText(String.valueOf(maxArmorPoints));
        txtWasted.setText(String.valueOf(wastedPoints));
        if (en.hasPatchworkArmor()) {
            txtPointsPerTon.setText("-"); //$NON-NLS-1$
        } else if (en.getWeightClass() == EntityWeightClass.WEIGHT_SMALL_SUPPORT){
            txtPointsPerTon.setText(String.format("%d", //$NON-NLS-1$
                    (int) (TestSupportVehicle.armorWeightPerPoint(en) * 1000)));
            lblPointsPerTon.setText(resourceMap.getString("ArmorAllocationView.txtKgPerPoint.text"));
            txtPointsPerTon.setToolTipText(resourceMap.getString("ArmorAllocationView.txtKgPerPoint.tooltip"));
        } else if (en instanceof Protomech) {
            txtPointsPerTon.setText(String.format("%d", //$NON-NLS-1$
                    (int)(EquipmentType.getProtomechArmorWeightPerPoint(en.getArmorType(Protomech.LOC_BODY))
                     * 1000)));
            lblPointsPerTon.setText(resourceMap.getString("ArmorAllocationView.txtKgPerPoint.text"));
            txtPointsPerTon.setToolTipText(resourceMap.getString("ArmorAllocationView.txtKgPerPoint.tooltip"));
        } else {
            txtPointsPerTon.setText(String.format("%3.2f", //$NON-NLS-1$
                    UnitUtil.getArmorPointsPerTon(en, en.getArmorType(1), en.getArmorTechLevel(1))));
            lblPointsPerTon.setText(resourceMap.getString("ArmorAllocationView.txtPointsPerTon.text"));
            txtPointsPerTon.setToolTipText(resourceMap.getString("ArmorAllocationView.txtPointsPerTon.tooltip"));
        }
    }
    
    private void updateLayout() {
        int[][] layout;
        if ((entitytype & Entity.ETYPE_MECH) != 0) {
            layout = MEK_LAYOUT;
        } else if ((entitytype & Entity.ETYPE_PROTOMECH) != 0) {
            layout = PROTOMEK_LAYOUT;
        } else if ((entitytype & Entity.ETYPE_JUMPSHIP) != 0) {
            layout = CAPITAL_LAYOUT;
        } else if ((entitytype & Entity.ETYPE_AERO) != 0) {
            // Spheroids use lwing/rwing rear for l/r aft positions
            layout = AERODYNE_LAYOUT;
        } else if ((entitytype & Entity.ETYPE_VTOL) != 0) {
            layout = VTOL_LAYOUT;
        } else if ((entitytype & (Entity.ETYPE_SUPER_HEAVY_TANK | Entity.ETYPE_LARGE_SUPPORT_TANK)) != 0) {
            layout = SH_TANK_LAYOUT;
        } else {
            layout = TANK_LAYOUT;
        }
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        locationViews.clear();
        for (int row = 0; row < layout.length; row++) {
            JPanel panRow = new JPanel();
            panRow.setLayout(new BoxLayout(panRow, BoxLayout.X_AXIS));
            for (int col = 0; col < layout[row].length; col++) {
                final int loc = layout[row][col];
                if (loc >= 0) {
                    ArmorLocationView locView = new ArmorLocationView(loc);
                    locationViews.add(locView);
                    panRow.add(locView);
                    locView.addListener(this);
                } else {
                    panRow.add(Box.createHorizontalGlue());
                }
            }
            panLocations.add(panRow, gbc);
            gbc.gridy++;
        }
    }
    
    public void setEntityType(long etype) {
        if (etype != entitytype) {
            entitytype = etype;
            panLocations.removeAll();
            updateLayout();
            panLocations.repaint();
        }
    }
    
    /**
     * Helper function for patchwork. If used for non-patchwork, it will likely give incorrect values
     * due to rounding up by location.
     * 
     * @param en
     * @return   The total weight of all allocated armor.
     */
    public double getTotalArmorWeight(Entity en) {
        double weight = 0.0;
        for (ArmorLocationView locView : locationViews) {
            final int loc = locView.getLocationIndex();
            if (loc < en.locations()) {
                double pointsPerTon = UnitUtil.getArmorPointsPerTon(en, en.getArmorType(loc),  en.getArmorTechLevel(loc));
                weight += (locView.getPoints() + locView.getPointsRear()) / pointsPerTon;
            }
        }
        return Math.ceil(weight * 2.0) * 0.5;
    }
    
    public void showPatchwork(boolean show) {
        showPatchwork = show;
    }
    
    @Override
    public void armorPointsChanged(int location, int front, int rear) {
        listeners.forEach(l -> l.armorPointsChanged(location, front, rear));
    }
}
