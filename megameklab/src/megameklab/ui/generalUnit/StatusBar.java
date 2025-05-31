/*
 * Copyright (c) 2023, 2024 - The MegaMek Team. All Rights Reserved.
 *
 * This file is part of MegaMekLab.
 *
 * MegaMek is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MegaMek is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MegaMek. If not, see <http://www.gnu.org/licenses/>.
 */
package megameklab.ui.generalUnit;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;

import megamek.client.ui.WrapLayout;
import megamek.client.ui.dialogs.abstractDialogs.BVDisplayDialog;
import megamek.client.ui.dialogs.abstractDialogs.CostDisplayDialog;
import megamek.client.ui.dialogs.abstractDialogs.WeightDisplayDialog;
import megamek.client.ui.clientGUI.GUIPreferences;
import megamek.client.ui.clientGUI.calculationReport.CalculationReport;
import megamek.client.ui.util.UIUtil;
import megamek.common.Aero;
import megamek.common.AmmoType;
import megamek.common.BattleArmor;
import megamek.common.Engine;
import megamek.common.Mek;
import megamek.common.MiscType;
import megamek.common.Mounted;
import megamek.common.WeaponType;
import megamek.common.verifier.TestEntity;
import megamek.utilities.DebugEntity;
import megameklab.ui.ForceBuildUI;
import megameklab.ui.MegaMekLabMainUI;
import megameklab.ui.util.ITab;
import megameklab.ui.util.RefreshListener;
import megameklab.util.CConfig;
import megameklab.util.UnitUtil;

public class StatusBar extends ITab {

    private static final String WEIGHT_LABEL = "Weight: %s %s / %s %s %s";

    private final MegaMekLabMainUI parent;
    private final JLabel bvLabel = new ClickableLabel(
            e -> new BVDisplayDialog(getParentFrame(), getEntity()).setVisible(true));
    protected final JLabel tons = new ClickableLabel(
            e -> new WeightDisplayDialog(getParentFrame(), getEntity()).setVisible(true));
    private final JLabel cost = new ClickableLabel(
            e -> new CostDisplayDialog(getParentFrame(), getEntity()).setVisible(true));
    private final JLabel invalid = new JLabel("Invalid");
    private final DecimalFormat formatter;
    private TestEntity testEntity;
    private RefreshListener refresh;

    public StatusBar(MegaMekLabMainUI parent) {
        super(parent);
        setBorder(new MatteBorder(1, 0, 0, 0, UIManager.getColor("Separator.foreground")));
        setLayout(new WrapLayout(FlowLayout.LEFT, 22, 8));
        this.parent = parent;
        formatter = new DecimalFormat();

        JButton btnValidate = new JButton("Validate Unit");
        btnValidate.addActionListener(validationListener);

        JButton btnRefresh = new JButton("Refresh UI");
        btnRefresh.setToolTipText("Refresh the UI, possibly repairing it if it is in a broken state.");
        btnRefresh.addActionListener(evt -> refresh.refreshAll());

        JButton btnAddToForce = new JButton("Add to Force");
        btnAddToForce.setToolTipText("Add this unit to the current force.");
        btnAddToForce.addActionListener(evt -> {
            ForceBuildUI.showAndAddEntity(getEntity());
        });

        invalid.setForeground(GUIPreferences.getInstance().getWarningColor());
        invalid.setVisible(false);

        if (!getEntity().isConventionalInfantry()) {
            JButton showEquipmentDatabase = new JButton("Equipment Database");
            showEquipmentDatabase.addActionListener(evt -> parent.getFloatingEquipmentDatabase().setVisible(true));
            add(showEquipmentDatabase);
        }

        add(btnValidate);
        add(btnAddToForce);
        add(btnRefresh);
        add(tons);
        add(bvLabel);
        add(invalid);
        add(cost);
    }

    public final void refresh() {
        testEntity = UnitUtil.getEntityVerifier(getEntity());
        refreshWeight();
        refreshBV();
        refreshCost();
        refreshInvalid();
        additionalRefresh();
    }

    /**
     * Subclasses must override this to provide the correct type of TestEntity subclass.
     */
    protected TestEntity getTestEntity() {
        return testEntity;
    }

    /**
     * This method is called whenever the status bar is refreshed. When additional type-specific information
     * is shown in a subclassed status bar, this method should be overridden to refresh that information.
     */
    protected void additionalRefresh() { }

    /**
     * Refreshes the weight display. This may be overridden; when doing so, the
     * {@link #tons} JLabel should be updated to reflect the weight.
     */
    protected void refreshWeight() {
        double tonnage = getEntity().getWeight();
        if (getEntity() instanceof BattleArmor) {
            tonnage = getBattleArmor().getTrooperWeight() * getBattleArmor().getSquadSize();
        }
        double currentTonnage = testEntity.calculateWeight();
        currentTonnage += UnitUtil.getUnallocatedAmmoTonnage(getEntity());
        String current = CalculationReport.formatForReport(currentTonnage);
        String full = CalculationReport.formatForReport(tonnage);
        String remaining = CalculationReport.formatForReport(tonnage - currentTonnage);
        String unit = "t";
        if (TestEntity.usesKgStandard(getEntity())) {
            unit = "kg";
            current = Math.round(currentTonnage * 1000) + "";
            full = Math.round(tonnage * 1000) + "";
            remaining = Math.round((tonnage - currentTonnage) * 1000) + "";
        }
        String remainingText = ((currentTonnage < tonnage) ? " (" + remaining + " " + unit + " Remaining)" : "");
        tons.setText(String.format(WEIGHT_LABEL, current, unit, full, unit, remainingText).trim());
        tons.setToolTipText("Current Weight / Max Weight (Remaining Weight, if any). Click to show the weight calculation.");
        tons.setForeground((currentTonnage > tonnage) ? GUIPreferences.getInstance().getWarningColor() : null);
    }

    private void refreshBV() {
        String bvValue;
        int baseBvValue = getEntity().calculateBattleValue(true, !CConfig.getBooleanParam(CConfig.RS_SHOW_PILOT_DATA));
        if (CConfig.getBooleanParam(CConfig.RS_SHOW_C3BV)) {
            int adjustedBvValue = getEntity().calculateBattleValue(false, !CConfig.getBooleanParam(CConfig.RS_SHOW_PILOT_DATA));
            if (adjustedBvValue == baseBvValue) {
                bvValue = NumberFormat.getInstance().format(baseBvValue);
            } else {
                bvValue = NumberFormat.getInstance().format(baseBvValue) + UIUtil.CONNECTED_SIGN
                        + NumberFormat.getInstance().format(adjustedBvValue);
            }
        } else {
            bvValue = NumberFormat.getInstance().format(baseBvValue);
        }
        bvLabel.setText("BV: " + bvValue);
        bvLabel.setToolTipText("Battle Value 2.0. Click to show the BV calculation.");
    }

    private void refreshCost() {
        cost.setText("Dry Cost: " + formatter.format(Math.round(getEntity().getCost(true))) + " C-bills");
        cost.setToolTipText("The dry cost of the unit (without ammo). The unit's full cost is "
                + formatter.format(Math.round(getEntity().getCost(false))) + " C-bills. "
                + "Click to show the cost calculation.");
    }

    private void refreshInvalid() {
        StringBuffer sb = new StringBuffer();
        invalid.setVisible(!testEntity.correctEntity(sb));
        invalid.setToolTipText("<html>" + sb.toString().replaceAll("\n", "<br/>") + "</html>");
    }

    private JFrame getParentFrame() {
        return parent != null ? parent.getParentFrame() : null;
    }

    public void addRefreshedListener(RefreshListener refreshListener) {
        refresh = refreshListener;
    }

    private final ActionListener validationListener = e -> {
        if ((e.getModifiers() & ActionEvent.CTRL_MASK) != 0) {
            DebugEntity.copyEquipmentState(getEntity());
        } else {
            UnitUtil.showValidation(getEntity(), getParentFrame());
        }
    };

    /**
     * Returns an estimated value of the total heat generation for Meks and Aeros (0 for other types).
     * This method is very specific to this use and cannot be generalized. It shouldn't be used elsewhere. It is
     * here in StatusBar to avoid duplication in BMStatusBar and ASStatusBar.
     *
     * @return An estimated value of the total heat generation.
     */
    protected long estimatedHeatGeneration() {
        if (!(getEntity() instanceof Mek) && !(getEntity() instanceof Aero)) {
            return 0;
        }

        double heat = calculateMovementHeat();
        heat += calculateWeaponHeat();
        heat += calculateEquipmentHeat();
        
        return Math.round(heat);
    }

    /**
     * Calculates heat generation from movement systems.
     */
    private double calculateMovementHeat() {
        if (!(getEntity() instanceof Mek)) {
            return 0;
        }
        
        double heat = 0;
        int engineType = getEntity().getEngineType();
        
        if (getEntity().getOriginalJumpMP() > 0) {
            // Calculate jump heat
            switch (getEntity().getJumpType()) {
                case Mek.JUMP_IMPROVED:
                    heat += Math.max(3, Math.ceil(getMek().getOriginalJumpMP() / 2.0f));
                    break;
                default:
                    heat += Math.max(3, getEntity().getOriginalJumpMP());
                    break;
            }
            
            // Apply XXL engine modifier for jumping
            if (engineType == Engine.XXL_ENGINE) {
                heat *= 2;
            }
        } else {
            // Calculate walking heat based on engine type
            switch (engineType) {
                case Engine.XXL_ENGINE:
                    heat += 6;
                    break;
                default:
                    heat += 2;
                    break;
            }
        }
        
        return heat;
    }

    /**
     * Calculates heat generation from weapons.
     */
    private double calculateWeaponHeat() {
        double totalHeat = 0;
        
        for (Mounted<?> mounted : getEntity().getTotalWeaponList()) {
            if (isWeaponInoperable(mounted)) {
                continue;
            }
            
            WeaponType weaponType = (WeaponType) mounted.getType();
            double weaponHeat = calculateIndividualWeaponHeat(weaponType);
            totalHeat += weaponHeat;
        }
        
        return totalHeat;
    }

    /**
     * Checks if a weapon is inoperable (missing, hit, destroyed, or breached).
     */
    private boolean isWeaponInoperable(Mounted<?> mounted) {
        return mounted.isMissing() || mounted.isHit() || 
            mounted.isDestroyed() || mounted.isBreached();
    }

    /**
     * Calculates heat generation for an individual weapon type.
     */
    private double calculateIndividualWeaponHeat(WeaponType weaponType) {
        double weaponHeat = weaponType.getHeat();
        AmmoType.AmmoTypeEnum ammoType = weaponType.getAmmoType();
        
        // Apply weapon-specific heat modifiers based on ammo type
        switch (ammoType) {
            case ROCKET_LAUNCHER:
                weaponHeat *= 0.25;
                break;
            case AC_ULTRA:
            case AC_ULTRA_THB:
                weaponHeat *= 2;
                break;
            case AC_ROTARY:
                weaponHeat *= 6;
                break;
            case SRM_STREAK:
            case LRM_STREAK:
                weaponHeat *= 0.5;
                break;
            default:
                // No modification for other ammo types
                break;
        }
        
        // Apply one-shot weapon modifier
        if (weaponType.hasFlag(WeaponType.F_ONESHOT)) {
            weaponHeat *= 0.25;
        }
        
        return weaponHeat;
    }

    /**
     * Calculates heat generation from miscellaneous equipment.
     */
    private double calculateEquipmentHeat() {
        double totalHeat = 0;
        
        for (Mounted<?> equipment : getEntity().getMisc()) {
            double equipmentHeat = equipment.getType().getHeat();
            
            // Apply equipment-specific heat modifiers
            MiscType miscType = (MiscType) equipment.getType();
            
            if (miscType.hasFlag(MiscType.F_LASER_INSULATOR)) {
                equipmentHeat += -1;
            } else if (miscType.hasFlag(MiscType.F_PPC_CAPACITOR)) {
                equipmentHeat += 5;
            } else if (miscType.hasFlag(MiscType.F_RISC_LASER_PULSE_MODULE)) {
                equipmentHeat += 2;
            }
            totalHeat += equipmentHeat;
        }
        
        return totalHeat;
    }
}
