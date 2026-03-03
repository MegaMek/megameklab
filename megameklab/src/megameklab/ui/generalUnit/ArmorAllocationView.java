/*
 * Copyright (C) 2017-2026 The MegaMek Team. All Rights Reserved.
 *
 * This file is part of MegaMekLab.
 *
 * MegaMekLab is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License (GPL),
 * version 3 or (at your option) any later version,
 * as published by the Free Software Foundation.
 *
 * MegaMekLab is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * A copy of the GPL should have been included with this project;
 * if not, see <https://www.gnu.org/licenses/>.
 *
 * NOTICE: The MegaMek organization is a non-profit group of volunteers
 * creating free software for the BattleTech community.
 *
 * MechWarrior, BattleMech, `Mech and AeroTech are registered trademarks
 * of The Topps Company, Inc. All Rights Reserved.
 *
 * Catalyst Game Labs and the Catalyst Game Labs logo are trademarks of
 * InMediaRes Productions, LLC.
 *
 * MechWarrior Copyright Microsoft Corporation. MegaMek was created under
 * Microsoft's "Game Content Usage Rules"
 * <https://www.xbox.com/en-US/developers/rules> and it is not endorsed by or
 * affiliated with Microsoft.
 */
package megameklab.ui.generalUnit;

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

import megamek.common.equipment.ArmorType;
import megamek.common.interfaces.ITechManager;
import megamek.common.units.*;
import megamek.common.verifier.TestEntity;
import megamek.common.verifier.TestSupportVehicle;
import megameklab.ui.generalUnit.ArmorLocationView.ArmorLocationListener;
import megameklab.ui.listeners.ArmorAllocationListener;
import megameklab.util.UnitUtil;

/**
 * Panel for allocating armor to various locations on an Entity. The assignment of armor values for specific locations
 * is delegated to ArmorLocationView. This class handles positioning of the subviews to approximate the position on the
 * unit and tracking the total amount of armor allocated.
 *
 * @author Neoancient
 */
public class ArmorAllocationView extends BuildView implements ArmorLocationListener {
    private final List<ArmorAllocationListener> listeners = new CopyOnWriteArrayList<>();

    public void addListener(ArmorAllocationListener l) {
        listeners.add(l);
    }

    public void removeListener(ArmorAllocationListener l) {
        listeners.remove(l);
    }

    private static final int[][] MEK_LAYOUT = {
          { -1, -1, Mek.LOC_HEAD, -1, -1 },
          { Mek.LOC_LEFT_ARM, Mek.LOC_LEFT_TORSO, Mek.LOC_CENTER_TORSO, Mek.LOC_RIGHT_TORSO, Mek.LOC_RIGHT_ARM },
          { -1, Mek.LOC_LEFT_LEG, Mek.LOC_CENTER_LEG, Mek.LOC_RIGHT_LEG, -1 }
    };

    private static final int[][] PROTOMEK_LAYOUT = {
          { ProtoMek.LOC_MAIN_GUN, ProtoMek.LOC_HEAD, -1 },
          { ProtoMek.LOC_LEFT_ARM, ProtoMek.LOC_TORSO, ProtoMek.LOC_RIGHT_ARM },
          { -1, ProtoMek.LOC_LEG, -1 }
    };

    private static final int[][] TANK_LAYOUT = {
          { -1, Tank.LOC_FRONT, -1 },
          { Tank.LOC_LEFT, Tank.LOC_TURRET, Tank.LOC_RIGHT },
          { -1, Tank.LOC_TURRET_2, -1 },
          { -1, Tank.LOC_REAR, -1 }
    };

    private static final int[][] SH_TANK_LAYOUT = {
          { -1, SuperHeavyTank.LOC_FRONT, -1 },
          { SuperHeavyTank.LOC_FRONT_LEFT, SuperHeavyTank.LOC_TURRET, SuperHeavyTank.LOC_FRONT_RIGHT },
          { SuperHeavyTank.LOC_REAR_LEFT, SuperHeavyTank.LOC_TURRET_2, SuperHeavyTank.LOC_REAR_RIGHT },
          { -1, SuperHeavyTank.LOC_REAR, -1 }
    };

    private static final int[][] VTOL_LAYOUT = {
          { -1, VTOL.LOC_FRONT, -1 },
          { VTOL.LOC_LEFT, VTOL.LOC_ROTOR, VTOL.LOC_RIGHT },
          { -1, VTOL.LOC_TURRET, -1 },
          { -1, VTOL.LOC_REAR, -1 }
    };

    private static final int[][] AERODYNE_LAYOUT = {
          { -1, Aero.LOC_NOSE, -1 },
          { Aero.LOC_LEFT_WING, -1, Aero.LOC_RIGHT_WING },
          { -1, Aero.LOC_AFT, -1 }
    };

    private static final int[][] CAPITAL_LAYOUT = {
          { -1, Jumpship.LOC_NOSE, -1 },
          { Jumpship.LOC_FLS, -1, Jumpship.LOC_FRS },
          { Jumpship.LOC_ALS, -1, Jumpship.LOC_ARS },
          { -1, Jumpship.LOC_AFT, -1 }
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

    private final ResourceBundle resourceMap = ResourceBundle.getBundle("megameklab.resources.Views");
    private long entityType;
    private boolean showPatchwork = false;
    private String tooltipFormat;

    public ArmorAllocationView(ITechManager techManager, long entityType) {
        this.entityType = entityType;
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
        add(new JLabel(resourceMap.getString("ArmorAllocationView.txtUnallocated.text"), SwingConstants.RIGHT), gbc);
        gbc.gridx = 1;
        txtUnallocated.setEditable(false);
        add(txtUnallocated, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel(resourceMap.getString("ArmorAllocationView.txtAllocated.text"), SwingConstants.RIGHT), gbc);
        gbc.gridx = 1;
        txtAllocated.setEditable(false);
        add(txtAllocated, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel(resourceMap.getString("ArmorAllocationView.txtTotal.text"), SwingConstants.RIGHT), gbc);
        gbc.gridx = 1;
        txtTotal.setEditable(false);
        add(txtTotal, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel(resourceMap.getString("ArmorAllocationView.txtMaxPossible.text"), SwingConstants.RIGHT), gbc);
        gbc.gridx = 1;
        txtMaxPossible.setEditable(false);
        add(txtMaxPossible, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel(resourceMap.getString("ArmorAllocationView.txtWasted.text"), SwingConstants.RIGHT), gbc);
        gbc.gridx = 1;
        txtWasted.setEditable(false);
        add(txtWasted, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        lblPointsPerTon.setText(resourceMap.getString("ArmorAllocationView.txtPointsPerTon.text"));
        add(lblPointsPerTon, gbc);
        gbc.gridx = 1;
        txtPointsPerTon.setEditable(false);
        txtPointsPerTon.setToolTipText(resourceMap.getString("ArmorAllocationView.txtPointsPerTon.tooltip"));
        add(txtPointsPerTon, gbc);

        btnAutoAllocate.setText(resourceMap.getString("ArmorAllocationView.btnAutoAllocate.text"));
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
            final Integer maxArmor = UnitUtil.getMaxArmor(en, location);
            if (location < en.locations() && ((maxArmor == null) || (maxArmor > 0))) {
                locView.setVisible(true);
                locView.updateLocation(en.getLocationAbbr(location), en.hasRearArmor(location));
                locView.setMaxPoints(UnitUtil.getMaxArmor(en, location));
                locView.setPoints(en.getArmor(location));
                if (en.hasRearArmor(location)) {
                    locView.setPointsRear(en.getArmor(location, true));
                } else {
                    locView.setPointsRear(0);
                }
                if (en instanceof SmallCraft || en instanceof Jumpship) {
                    double primitiveFactor = en.isPrimitive() ? 0.66 : 1;
                    // As the SI bonus armor is multiplied by the primitive factor 0.66, primitive craft with little
                    // armor tonnage may not be able to assign the bonus evenly per location, therefore the minimum
                    // must be relaxed for those units, see IO:AE 3rd p.125 (Aquilla example calculation)
                    locView.setMinimum((int) (TestEntity.getSIBonusArmorPoints(en)
                          * primitiveFactor
                          / locationViews.size()));
                }
                if (showPatchwork) {
                    double pointsPerTon = UnitUtil.getArmorPointsPerTon(en);
                    double points = en.getArmor(location, false);
                    if (en.hasRearArmor(location)) {
                        points += en.getArmor(location, true);
                    }
                    locView.setToolTipText(String.format(tooltipFormat, pointsPerTon, points / pointsPerTon));
                }
            } else {
                locView.setVisible(false);
                locView.setPoints(0);
                locView.setPointsRear(0);
            }
        }
        int maxArmorPoints = UnitUtil.getMaximumArmorPoints(en);
        int raw = (int) (TestEntity.getRawArmorPoints(en, en.getLabArmorTonnage())
              + TestEntity.getSIBonusArmorPoints(en));

        // The primitive armor factor of 0.66 is multiplied as the last part of the calculation after adding the base
        // SI bonus and the base rounded armor, see IO:AE 3rd p.125 (Aquilla example calculation)
        if ((en instanceof SmallCraft || en instanceof Jumpship) && en.isPrimitive()) {
            raw = (int) (0.66 * raw);
        }
        int allocatedPoints = en.getTotalOArmor();
        int availableArmorPoints;
        if (showPatchwork) {
            availableArmorPoints = allocatedPoints;
            raw = allocatedPoints;
        } else {
            availableArmorPoints = Math.min(raw, maxArmorPoints);
        }
        btnAutoAllocate.setEnabled(!showPatchwork);
        int wastedPoints = Math.max(0, raw - availableArmorPoints);
        txtUnallocated.setText(Integer.toString(availableArmorPoints - allocatedPoints));
        txtAllocated.setText(Integer.toString(allocatedPoints));
        if (availableArmorPoints != allocatedPoints) {
            txtUnallocated.setForeground(Color.RED);
        } else {
            txtUnallocated.setForeground(UIManager.getColor("Label.foreground"));
        }
        txtTotal.setText(String.valueOf(availableArmorPoints));
        txtMaxPossible.setText(String.valueOf(maxArmorPoints));
        txtWasted.setText(String.valueOf(wastedPoints));
        if (en.hasPatchworkArmor()) {
            txtPointsPerTon.setText("-");
        } else if (en.getWeightClass() == EntityWeightClass.WEIGHT_SMALL_SUPPORT) {
            txtPointsPerTon.setText(String.format("%d", (int) (TestSupportVehicle.armorWeightPerPoint(en) * 1000)));
            lblPointsPerTon.setText(resourceMap.getString("ArmorAllocationView.txtKgPerPoint.text"));
            txtPointsPerTon.setToolTipText(resourceMap.getString("ArmorAllocationView.txtKgPerPoint.tooltip"));
        } else if (en instanceof ProtoMek) {
            txtPointsPerTon.setText(String.format("%d", (int) (ArmorType.forEntity(en).getWeightPerPoint() * 1000)));
            lblPointsPerTon.setText(resourceMap.getString("ArmorAllocationView.txtKgPerPoint.text"));
            txtPointsPerTon.setToolTipText(resourceMap.getString("ArmorAllocationView.txtKgPerPoint.tooltip"));
        } else {
            txtPointsPerTon.setText(String.format("%3.2f", UnitUtil.getArmorPointsPerTon(en)));
            lblPointsPerTon.setText(resourceMap.getString("ArmorAllocationView.txtPointsPerTon.text"));
            txtPointsPerTon.setToolTipText(resourceMap.getString("ArmorAllocationView.txtPointsPerTon.tooltip"));
        }
    }

    private void updateLayout() {
        int[][] layout = getLayoutFromEntity();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        locationViews.clear();
        for (int[] ints : layout) {
            JPanel panRow = new JPanel();
            panRow.setLayout(new BoxLayout(panRow, BoxLayout.X_AXIS));
            for (final int loc : ints) {
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

    private int[][] getLayoutFromEntity() {
        int[][] layout;
        if ((entityType & Entity.ETYPE_MEK) != 0) {
            layout = MEK_LAYOUT;
        } else if ((entityType & Entity.ETYPE_PROTOMEK) != 0) {
            layout = PROTOMEK_LAYOUT;
        } else if ((entityType & Entity.ETYPE_JUMPSHIP) != 0) {
            layout = CAPITAL_LAYOUT;
        } else if ((entityType & Entity.ETYPE_AERO) != 0) {
            // Spheroids use left wing/right wing rear for l/r aft positions
            layout = AERODYNE_LAYOUT;
        } else if ((entityType & Entity.ETYPE_VTOL) != 0) {
            layout = VTOL_LAYOUT;
        } else if ((entityType & (Entity.ETYPE_SUPER_HEAVY_TANK | Entity.ETYPE_LARGE_SUPPORT_TANK)) != 0) {
            layout = SH_TANK_LAYOUT;
        } else {
            layout = TANK_LAYOUT;
        }
        return layout;
    }

    public void setEntityType(long etype) {
        if (etype != entityType) {
            entityType = etype;
            panLocations.removeAll();
            updateLayout();
            panLocations.repaint();
        }
    }

    /**
     * Helper function for patchwork. If used for non-patchwork, it will likely give incorrect values due to rounding up
     * by location.
     *
     * @param en the current entity
     *
     * @return The total weight of all allocated armor.
     */
    public double getTotalArmorWeight(Entity en) {
        double weight = 0.0;
        for (ArmorLocationView locView : locationViews) {
            final int loc = locView.getLocationIndex();
            if (loc < en.locations()) {
                double pointsPerTon = UnitUtil.getArmorPointsPerTon(en);
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
