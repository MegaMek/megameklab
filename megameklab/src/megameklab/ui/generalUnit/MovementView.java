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

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.StringJoiner;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import megamek.client.ui.util.DisplayTextField;
import megamek.common.battleArmor.BattleArmor;
import megamek.common.equipment.Engine;
import megamek.common.equipment.EquipmentType;
import megamek.common.equipment.EquipmentTypeLookup;
import megamek.common.equipment.MiscMounted;
import megamek.common.equipment.MiscType;
import megamek.common.equipment.Mounted;
import megamek.common.equipment.enums.MiscTypeFlag;
import megamek.common.interfaces.ITechManager;
import megamek.common.ui.SmallFontHelpTextLabel;
import megamek.common.units.AeroSpaceFighter;
import megamek.common.units.ConvFighter;
import megamek.common.units.Entity;
import megamek.common.units.EntityMovementMode;
import megamek.common.units.Jumpship;
import megamek.common.units.Mek;
import megamek.common.units.ProtoMek;
import megamek.common.units.Tank;
import megamek.common.units.Warship;
import megamek.common.verifier.TestBattleArmor;
import megamek.common.verifier.TestEntity;
import megamek.common.verifier.TestMek;
import megamek.common.verifier.TestProtoMek;
import megameklab.ui.listeners.BuildListener;
import megameklab.ui.util.TechComboBox;

/**
 * Controls for setting a unit's speed
 *
 * @author Neoancient
 */
public class MovementView extends BuildView implements ActionListener, ChangeListener {

    private static final ResourceBundle I18N = ResourceBundle.getBundle("megameklab.resources.Views");

    private final List<BuildListener> listeners = new CopyOnWriteArrayList<>();

    public void addListener(BuildListener l) {
        listeners.add(l);
    }

    public void removeListener(BuildListener l) {
        listeners.remove(l);
    }

    private final static int LABEL_INDEX_MEK = 0;
    private final static int LABEL_INDEX_TANK = 1;
    private final static int LABEL_INDEX_AERO = 2;
    private final static int LABEL_INDEX_BA = 3;

    private final SpinnerNumberModel spnWalkModel = new SpinnerNumberModel(1, 1, null, 1);
    private final SpinnerNumberModel spnJumpModel = new SpinnerNumberModel(0, 0, null, 1);
    private final JSpinner spnWalk = new JSpinner(spnWalkModel);
    private final JSpinner spnJump = new JSpinner(spnJumpModel);
    private final TechComboBox<EquipmentType> cbJumpType =
          new TechComboBox<>(eq -> eq.getName().replaceAll("\\s+\\[.*?]", ""));

    private final JLabel lblWalk = createLabel("lblWalk", "");
    private final JLabel lblRun = createLabel("lblRun", "");
    private final JLabel lblJump = createLabel("lblJump", "");
    private final JLabel lblJumpType = createLabel("lblJumpType", "");

    private final JTextField txtRunBase = new JTextField();
    private final JTextField txtWalkFinal = new DisplayTextField();
    private final JTextField txtRunFinal = new DisplayTextField();
    private final JTextField txtJumpFinal = new DisplayTextField();
    private final JLabel lblMekMechanicalJumpMP = new JLabel(I18N.getString("MovementView.MMJB"));
    private final SpinnerNumberModel spnMekMechanicalJumpModel = new SpinnerNumberModel(0, 0, null, 1);
    private final JSpinner spnMekMechanicalJumpMP = new JSpinner(spnMekMechanicalJumpModel);

    private final JLabel wsStationKeepingInfo =
          new SmallFontHelpTextLabel(I18N.getString("MovementView.stationKeepingInfo"));

    private final ITechManager techManager;
    private long etype;
    private boolean industrial;
    private String[] walkNames;
    private String[] runNames;
    private String[] jumpNames;

    public MovementView(ITechManager techManager) {
        this.techManager = techManager;
        initUI();
    }

    private void initUI() {
        walkNames = I18N.getString("MovementView.lblWalk.values").split(",");
        runNames = I18N.getString("MovementView.lblRun.values").split(",");
        jumpNames = I18N.getString("MovementView.lblJump.values").split(",");

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = STANDARD_INSETS;

        gbc.gridy = 0;
        add(new JLabel());
        add(new JLabel(I18N.getString("MovementView.lblBase.text")), gbc);
        add(new JLabel(I18N.getString("MovementView.lblFinal.text")), gbc);

        gbc.gridy++;
        add(lblWalk, gbc);
        gbc.fill = GridBagConstraints.NONE;
        spnWalk.setToolTipText(I18N.getString("MovementView.spnWalk.tooltip"));
        add(spnWalk, gbc);
        txtWalkFinal.setToolTipText(I18N.getString("MovementView.txtWalkFinal.tooltip"));
        add(txtWalkFinal, gbc);
        spnWalk.addChangeListener(this);

        gbc.gridy++;
        gbc.gridwidth = 3;
        add(wsStationKeepingInfo, gbc);
        gbc.gridwidth = 1;

        gbc.gridy++;
        add(lblRun, gbc);
        txtRunBase.setToolTipText(I18N.getString("MovementView.txtRunBase.tooltip"));
        add(txtRunBase, gbc);
        txtRunFinal.setToolTipText(I18N.getString("MovementView.txtRunFinal.tooltip"));
        add(txtRunFinal, gbc);

        gbc.gridy++;
        add(lblJump, gbc);
        spnJump.setToolTipText(I18N.getString("MovementView.spnJump.tooltip"));
        add(spnJump, gbc);
        txtJumpFinal.setToolTipText(I18N.getString("MovementView.txtJumpFinal.tooltip"));
        add(txtJumpFinal, gbc);
        spnJump.addChangeListener(this);

        lblJumpType.setText(I18N.getString("MovementView.cbJumpType.text"));
        cbJumpType.setNullValue(I18N.getString("MovementView.cbJumpType.null"));
        gbc.gridy++;
        add(lblJumpType, gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        cbJumpType.setToolTipText(I18N.getString("MovementView.cbJumpType.tooltip"));
        gbc.gridwidth = 2;
        add(cbJumpType, gbc);
        gbc.gridwidth = 1;
        cbJumpType.addActionListener(this);
        cbJumpType.setPrototypeDisplayValue(CB_SIZE_EQUIPMENT);

        gbc.gridy++;
        add(lblMekMechanicalJumpMP, gbc);
        spnMekMechanicalJumpMP.setToolTipText(I18N.getString("MovementView.spnJump.tooltip"));
        add(spnMekMechanicalJumpMP, gbc);
        spnMekMechanicalJumpMP.addChangeListener(this);

        txtWalkFinal.setEditable(false);
        txtWalkFinal.setHorizontalAlignment(SwingConstants.RIGHT);
        txtRunBase.setEditable(false);
        txtRunBase.setHorizontalAlignment(SwingConstants.RIGHT);
        txtRunFinal.setEditable(false);
        txtRunFinal.setHorizontalAlignment(SwingConstants.RIGHT);
        txtJumpFinal.setEditable(false);
        txtJumpFinal.setHorizontalAlignment(SwingConstants.RIGHT);
    }

    public void setFromEntity(Entity en) {
        etype = en.getEntityType();
        industrial = (en instanceof Mek mek) && mek.isIndustrial();
        refresh();

        Optional<MiscType> jj = en.getMisc().stream().map(Mounted::getType)
              .filter(eq -> eq.hasFlag(MiscType.F_JUMP_JET) || eq.hasFlag(MiscType.F_UMU)).findAny();
        if (jj.isPresent()) {
            cbJumpType.removeActionListener(this);
            cbJumpType.setSelectedItem(jj.get());
            cbJumpType.addActionListener(this);
        }
        // LAMs have a minimum jump MP of 3, which implies a minimum walk
        int minWalk = en.isTrailer() ? 0 : 1;
        Integer maxWalk = null;
        int minJump = 0;
        Integer maxJump = en.getOriginalWalkMP();
        if (cbJumpType.getModel().getSize() == 0) { // No legal jump jet tech for this unit type
            maxJump = 0;
        } else if (en instanceof Mek mek) {
            maxJump = TestMek.maxJumpMP(mek);
        } else if (en instanceof ProtoMek protoMek) {
            maxJump = TestProtoMek.maxJumpMP(protoMek);
        }
        if (en.hasETypeFlag(Entity.ETYPE_TANK) && !en.isSupportVehicle() && !en.isTrailer()) {
            int minRating = 10 + Tank.getSuspensionFactor(en.getMovementMode(), en.getWeight());
            minWalk = Math.max(1, (int) (minRating / en.getWeight()));
        }
        if (en instanceof Tank && en.getMovementMode() == EntityMovementMode.WIGE && minWalk < 5) {
            minWalk = 5;
        }
        if (en.hasETypeFlag(Entity.ETYPE_LAND_AIR_MEK)) {
            minJump = minWalk = 3;
            // If the unit has improved JJs the walk can be 2 and still meet the minimum jump MP requirement of 3.
            int jumpType = en.getJumpType();
            if ((jumpType == Mek.JUMP_IMPROVED) || (jumpType == Mek.JUMP_PROTOTYPE_IMPROVED)) {
                minWalk = 2;
            }
        } else if (en instanceof BattleArmor battleArmor) {
            cbJumpType.removeActionListener(this);
            maxWalk = TestBattleArmor.maxWalkMP(battleArmor);
            if (battleArmor.getChassisType() == BattleArmor.CHASSIS_TYPE_QUAD) {
                minWalk = 2;
            }
            cbJumpType.setSelectedItem(TestBattleArmor.BAMotiveSystems.getEquipment(en.getMovementMode()));
            if (en.getMovementMode() == EntityMovementMode.VTOL) {
                maxJump = TestBattleArmor.maxVtolMP(battleArmor);
            } else if (en.getMovementMode() == EntityMovementMode.INF_UMU) {
                maxJump = TestBattleArmor.maxUmuMP(battleArmor);
            } else {
                maxJump = TestBattleArmor.maxJumpMP(battleArmor);
            }
            cbJumpType.addActionListener(this);
        } else if (en instanceof ProtoMek protoMek) {
            if (protoMek.isGlider()) {
                minWalk = TestProtoMek.GLIDER_MIN_MP;
            } else if (protoMek.isQuad()) {
                minWalk = TestProtoMek.QUAD_MIN_MP;
            } else {
                minWalk = 1;
            }
        } else if (en.hasETypeFlag(Entity.ETYPE_JUMPSHIP)) {
            minWalk = 0; // Station-keeping drive, legal for warships, SO:AA p.135
        } else if (en instanceof ConvFighter asf) {
            // ConvFighter is a subclass of AeroSpaceFighter so must be checked first
            minWalk = (asf.getWeight() <= 5) ? 2 : 1;
        } else if (en instanceof AeroSpaceFighter asf) {
            minWalk = (asf.getWeight() <= 5) ? 4 : 3;
        }
        // Trailers with no engine have a max speed of zero.
        if (en.isTrailer() && ((en.getEngine() == null)
              || (en.getEngine().getEngineType() == Engine.NONE))) {
            maxWalk = 0;
        }
        spnWalkModel.setMinimum(minWalk);
        spnWalkModel.setMaximum(maxWalk);
        spnWalk.setValue(Math.max(minWalk, en.getOriginalWalkMP()));
        if (null != maxWalk && getWalk() > maxWalk) {
            spnWalk.setValue(maxWalk);
        }
        txtWalkFinal.setText(String.valueOf(en.getWalkMP()));

        txtRunBase.setText(String.valueOf((int) Math.ceil(((Number) spnWalk.getValue()).intValue() * 1.5)));
        txtRunFinal.setText(en.getRunMPasString(false));

        int labelIndex = LABEL_INDEX_MEK;
        boolean showJump = true;
        boolean showJumpType = true;
        boolean showRun = true;
        if (en.hasETypeFlag(Entity.ETYPE_AERO)) {
            labelIndex = LABEL_INDEX_AERO;
            showJump = showJumpType = false;
        } else if (en.hasETypeFlag(Entity.ETYPE_VTOL)) {
            labelIndex = LABEL_INDEX_TANK;
            showJump = showJumpType = false;
        } else if (en.hasETypeFlag(Entity.ETYPE_TANK)) {
            labelIndex = LABEL_INDEX_TANK;
            showJump = showJumpType = (en.getMovementMode() == EntityMovementMode.TRACKED)
                  || (en.getMovementMode() == EntityMovementMode.WHEELED)
                  || (en.getMovementMode() == EntityMovementMode.HOVER)
                  || (en.getMovementMode() == EntityMovementMode.WIGE);
        } else if (en.hasETypeFlag(Entity.ETYPE_BATTLEARMOR)) {
            labelIndex = LABEL_INDEX_BA;
            showRun = false;
        }
        if (!showJump) {
            maxJump = 0;
        }
        spnJump.removeChangeListener(this);
        int jump0 = en.getOriginalJumpMP(true);
        int jump = en.getJumpMP();
        if (0 == jump0) {
            jump0 = en.getAllUMUCount();
            if (jump0 > 0) {
                jump = jump0;
            }
        }
        spnJumpModel.setMinimum(minJump);
        spnJumpModel.setMaximum(maxJump);
        spnJump.setValue(Math.max(minJump, jump0));
        txtJumpFinal.setText(String.valueOf(jump));
        spnJump.addChangeListener(this);

        setMovementModToolTips(en);

        lblWalk.setText(walkNames[labelIndex]);
        lblRun.setText(runNames[labelIndex]);
        lblRun.setVisible(showRun);
        txtRunBase.setVisible(showRun);
        txtRunFinal.setVisible(showRun);
        lblJump.setText(jumpNames[labelIndex]);
        lblJump.setVisible(showJump);
        spnJump.setVisible(showJump);
        txtJumpFinal.setVisible(showJump);
        lblJumpType.setVisible(showJumpType);
        cbJumpType.setVisible(showJumpType);
        spnJump.setEnabled((null == maxJump) || (maxJump > 0));
        cbJumpType.setEnabled((null == maxJump) || (maxJump > 0));
        spnMekMechanicalJumpMP.setVisible(en.isMek());
        lblMekMechanicalJumpMP.setVisible(en.isMek());
        // adapt the Mek Mechanical Jump Booster MP value to the equipment size
        spnMekMechanicalJumpMP.removeChangeListener(this);
        Optional<MiscMounted> mekMechanicalJumpBooster = en.getMisc().stream()
              .filter(m -> m.is(EquipmentTypeLookup.MECHANICAL_JUMP_BOOSTER))
              .findFirst();
        if (mekMechanicalJumpBooster.isEmpty()) {
            spnMekMechanicalJumpMP.setValue(0);
        } else if (getMekMechanicalJump() != mekMechanicalJumpBooster.get().getSize()) {
            spnMekMechanicalJumpMP.setValue(mekMechanicalJumpBooster.get().getSize());
        }
        spnMekMechanicalJumpMP.addChangeListener(this);

        if ((maxJump != null) && (jump0 > maxJump)) {
            spnJump.setValue(spnJumpModel.getMaximum());
        } else if (jump0 < minJump) {
            spnJump.setValue(spnJumpModel.getMinimum());
        }

        if (en instanceof Jumpship jumpship) {
            // no max thrust for station keeping drives, SO:AA p.135; a JumpShip instance can be a WarShip
            lblRun.setEnabled(!jumpship.hasStationKeepingDrive());
            txtRunBase.setEnabled(!jumpship.hasStationKeepingDrive());
            txtRunFinal.setEnabled(!jumpship.hasStationKeepingDrive());
        }
        wsStationKeepingInfo.setVisible(en instanceof Warship);
    }

    public void refresh() {
        if (cbJumpType.isVisible()) {
            EquipmentType prev = (EquipmentType) cbJumpType.getSelectedItem();
            cbJumpType.removeActionListener(this);
            cbJumpType.removeAllItems();
            for (EquipmentType eq : TestEntity.validJumpJets(etype, industrial)) {
                if (techManager.isLegal(eq)) {
                    cbJumpType.addItem(eq);
                }
            }
            cbJumpType.setSelectedItem(prev);
            cbJumpType.addActionListener(this);
            if (cbJumpType.getModel().getSize() > 0) {
                spnJump.setEnabled(true);
                cbJumpType.setEnabled(true);
                if ((cbJumpType.getSelectedIndex() < 0)
                      || !Objects.equals(cbJumpType.getSelectedItem(), prev)) {
                    cbJumpType.setSelectedIndex(0);
                }
            } else {
                spnJump.setEnabled(false);
                cbJumpType.setEnabled(false);
            }
        }
    }

    private void setMovementModToolTips(Entity en) {
        StringJoiner walkTooltip = new StringJoiner(", ");
        StringJoiner runTooltip = new StringJoiner(", ");
        StringJoiner jumpTooltip = new StringJoiner(", ");

        if (en.hasModularArmor()) {
            walkTooltip.add("-1 (Modular armor)");
            jumpTooltip.add("-1 (Modular armor)");
        }
        if (en instanceof Mek) {
            if (((Mek) en).hasMPReducingHardenedArmor()) {
                runTooltip.add("-1 (Hardened armor)");
            }

            switch (en.getMPBoosters()) {
                case MASC_ONLY:
                    runTooltip.add("MASC");
                    break;
                case SUPERCHARGER_ONLY:
                    runTooltip.add("Supercharger");
                    break;
                case MASC_AND_SUPERCHARGER:
                    runTooltip.add("MASC/Supercharger");
                    break;
                case NONE:
                default:
                    break;
            }

            int medShields = en.getNumberOfShields(MiscTypeFlag.S_SHIELD_MEDIUM);
            int lgShields = en.getNumberOfShields(MiscTypeFlag.S_SHIELD_LARGE);
            if (lgShields + medShields > 0) {
                walkTooltip.add(String.format("-%d (Shield)", lgShields + medShields));
            }

            if (lgShields > 0) {
                jumpTooltip.add("No Jump (Large Shield)");
            } else if (medShields > 0) {
                jumpTooltip.add(String.format("-%d (Shield)", medShields));
            }
        } else if (en.hasWorkingMisc(MiscType.F_MASC)) {
            runTooltip.add("Supercharger");
        } else if (en.hasWorkingMisc(MiscType.F_JET_BOOSTER)) {
            runTooltip.add("Jet Booster");
        }
        Optional<MiscMounted> partialWing = en.getMisc().stream()
              .filter(m -> m.getType().hasFlag(MiscType.F_PARTIAL_WING)).findAny();
        if (partialWing.isPresent()) {
            int bonus = 2;
            if (en instanceof Mek mek) {
                bonus = mek.getPartialWingJumpBonus(partialWing.get());
            } else if (en instanceof BattleArmor) {
                bonus = 1;
            }
            jumpTooltip.add(String.format("+%d (Partial wing)", bonus));
        }
        txtWalkFinal.setToolTipText((walkTooltip.length() > 0) ? walkTooltip.toString() : null);
        txtRunFinal.setToolTipText((runTooltip.length() > 0) ? runTooltip.toString() : null);
        txtJumpFinal.setToolTipText(((jumpTooltip.length() > 0) && (en.getOriginalJumpMP(false) > 0))
              ? jumpTooltip.toString() : null);
    }

    public int getWalk() {
        return spnWalkModel.getNumber().intValue();
    }

    public int getJump() {
        return spnJumpModel.getNumber().intValue();
    }

    public int getMekMechanicalJump() {
        return spnMekMechanicalJumpModel.getNumber().intValue();
    }

    public EquipmentType getJumpJet() {
        return (EquipmentType) cbJumpType.getSelectedItem();
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() == spnWalk) {
            listeners.forEach(l -> l.walkChanged(getWalk()));
        } else if (e.getSource() == spnJump) {
            listeners.forEach(l -> l.jumpChanged(getJump(), getJumpJet()));
        } else if (e.getSource() == spnMekMechanicalJumpMP) {
            listeners.forEach(l -> l.jumpChanged(getMekMechanicalJump(),
                  EquipmentType.get(EquipmentTypeLookup.MECHANICAL_JUMP_BOOSTER)));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cbJumpType) {
            listeners.forEach(l -> l.jumpTypeChanged(getJumpJet()));
        }
    }
}
