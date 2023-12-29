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
package megameklab.ui.mek;

import megamek.client.ui.swing.calculationReport.CalculationReport;
import megamek.common.*;
import megamek.common.annotations.Nullable;
import megamek.common.verifier.EntityVerifier;
import megamek.common.verifier.TestMech;
import megameklab.ui.EntitySource;
import megameklab.ui.generalUnit.summary.*;
import megameklab.ui.util.IView;
import megameklab.util.UnitUtil;

import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class BMSummaryView extends IView {
    private final JTextField txtStructTon = new JTextField("-");
    private final JTextField txtEngineTon = new JTextField("-");
    private final JTextField txtGyroTon = new JTextField("-");
    private final JTextField txtCockpitTon = new JTextField("-");
    private final JTextField txtHeatTon = new JTextField("-");
    private final JTextField txtArmorTon = new JTextField("-");
    private final JTextField txtEnhanceTon = new JTextField("-");
    private final JTextField txtJumpTon = new JTextField("-");
    private final JTextField txtEquipTon = new JTextField("-");
    private final JTextField txtOtherTon = new JTextField("-");

    private final JTextField txtStructCrit = new JTextField("-");
    private final JTextField txtEngineCrit = new JTextField("-");
    private final JTextField txtGyroCrit = new JTextField("-");
    private final JTextField txtCockpitCrit = new JTextField("-");
    private final JTextField txtHeatCrit = new JTextField("-");
    private final JTextField txtArmorCrit = new JTextField("-");
    private final JTextField txtEnhanceCrit = new JTextField("-");
    private final JTextField txtJumpCrit = new JTextField("-");
    private final JTextField txtEquipCrit = new JTextField("-");
    private final JTextField txtOtherCrit = new JTextField("-");

    private final JTextField txtStructAvail = new JTextField("-");
    private final JTextField txtEngineAvail = new JTextField("-");
    private final JTextField txtGyroAvail = new JTextField("-");
    private final JTextField txtCockpitAvail = new JTextField("-");
    private final JTextField txtHeatAvail = new JTextField("-");
    private final JTextField txtArmorAvail = new JTextField("-");
    private final JTextField txtEnhanceAvail = new JTextField("-");
    private final JTextField txtJumpAvail = new JTextField("-");
    private final JTextField txtEquipAvail = new JTextField("-");
    private final JTextField txtOtherAvail = new JTextField("-");

    private final SummaryItem structureSummary = new StructureSummaryItem();
    private final SummaryItem engineSummary = new EngineSummaryItem();
    private final SummaryItem gyroSummary = new GyroSummaryItem();
    private final SummaryItem cockpitSummary = new CockpitSummaryItem();
    private final SummaryItem heatsinkSummary = new HeatsinkSummaryItem();

    private final List<SummaryItem> summaryItemList = List.of(structureSummary, engineSummary, gyroSummary,
            cockpitSummary, heatsinkSummary);

    private final EntityVerifier entityVerifier =
            EntityVerifier.getInstance(new File("data/mechfiles/UnitVerifierOptions.xml"));

    private final Dimension weightCritSize = new Dimension(55, 25);
    private final Dimension availSize = new Dimension(110, 25);
    private final Dimension categorySize = new Dimension(110, 25);


    public BMSummaryView(EntitySource eSource) {
        super(eSource);

        for (SummaryItem summaryItem : summaryItemList) {
            summaryItem.getWeightComponent().setPreferredSize(weightCritSize);
            summaryItem.getCritsComponent().setPreferredSize(weightCritSize);
            summaryItem.getAvailabilityComponent().setPreferredSize(availSize);
        }

        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createTitledBorder("Summary"));
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 0, 5);
        add(createLabel("Category", categorySize, SwingConstants.CENTER), gbc);
        gbc.gridy++;
        for (SummaryItem summaryItem : summaryItemList) {
            this.add(createLabel(summaryItem.getName(), categorySize, SwingConstants.RIGHT), gbc);
            gbc.gridy++;
        }

        gbc.gridx = 1;
        gbc.gridy = 0;
        add(createLabel("Weight", weightCritSize, SwingConstants.CENTER), gbc);
        gbc.gridy++;
        for (SummaryItem summaryItem : summaryItemList) {
            this.add(summaryItem.getWeightComponent(), gbc);
            gbc.gridy++;
        }

        gbc.gridx = 2;
        gbc.gridy = 0;
        add(createLabel("Crits", weightCritSize, SwingConstants.CENTER), gbc);
        gbc.gridy++;
        for (SummaryItem summaryItem : summaryItemList) {
            this.add(summaryItem.getCritsComponent(), gbc);
            gbc.gridy++;
        }

        gbc.gridx = 3;
        gbc.gridy = 0;
        add(createLabel("Availability", weightCritSize, SwingConstants.CENTER), gbc);
        gbc.gridy++;
        for (SummaryItem summaryItem : summaryItemList) {
            this.add(summaryItem.getAvailabilityComponent(), gbc);
            gbc.gridy++;
        }
    }

    private JLabel createLabel(String text, Dimension size, int align) {
        JLabel label = new JLabel(text + ":", SwingConstants.TRAILING);
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
        TestMech testMech = new TestMech(getMech(), entityVerifier.mechOption, null);
        summaryItemList.forEach(summaryItem -> summaryItem.refresh(getEntity()));
        refreshEngine(testMech);
        refreshGyro(testMech);
        refreshCockpit(testMech);
        refreshStructure(testMech);
        refreshHeatSinks(testMech);
        refreshArmor(testMech);
        txtOtherTon.setText(formatWeight(testMech.getWeightPowerAmp() + testMech.getWeightCarryingSpace() + testMech.getWeightMisc()));
        runThroughEquipment();
    }

    private void refreshArmor(TestMech testMech) {
        if (getMech().hasPatchworkArmor()) {
            txtArmorTon.setText(formatWeight(testMech.getWeightAllocatedArmor()));
        } else {
            txtArmorTon.setText(formatWeight(testMech.getWeightArmor()));
        }
        // FIXME: This doesn't account for patchwork armor crits.
        int armorType = getMech().getArmorType(0);
        if ((armorType >= 0) && (armorType < EquipmentType.armorNames.length)) {
            String armorName = EquipmentType.getArmorTypeName(armorType,
                    TechConstants.isClan(getMech().getArmorTechLevel(0)));
            EquipmentType armor = EquipmentType.get(armorName);
            txtArmorCrit.setText(formatCrits(armor.getCriticals(getMech())));
            txtArmorAvail.setText(armor.getFullRatingName(getMech().isClan()));
        } else {
            txtArmorCrit.setText("-");
            txtArmorAvail.setText("-");
        }
    }

    private void refreshHeatSinks(TestMech testMech) {
        int numberSinks = UnitUtil.countActualHeatSinks(getMech());
        numberSinks = Math.max(0, numberSinks - UnitUtil.getCriticalFreeHeatSinks(getMech(), getMech().hasCompactHeatSinks()));
        int critSinks = numberSinks;
        if (UnitUtil.hasClanDoubleHeatSinks(getMech())) {
            critSinks = numberSinks * 2;
        } else if (getMech().hasDoubleHeatSinks()) {
            critSinks = numberSinks * 3;
        } else if (getMech().hasCompactHeatSinks()) {
            critSinks = (critSinks / 2) + (critSinks % 2);
        }
        EquipmentType hsType = EquipmentType.get(getHeatSinkType(getMech()));
        if (hsType != null) {
            txtHeatAvail.setText(hsType.getFullRatingName(getMech().isClan()));
        } else {
            txtHeatAvail.setText("-");
        }
        txtHeatTon.setText(formatWeight(testMech.getWeightHeatSinks()));
        txtHeatCrit.setText(formatCrits(critSinks));
    }

    private void refreshStructure(TestMech testMech) {
        int type = getMech().getStructureType();
        if ((type >= 0) && (type < EquipmentType.structureNames.length)) {
            String structName = EquipmentType.getStructureTypeName(type,
                    TechConstants.isClan(getMech().getStructureTechLevel()));
            EquipmentType structureType = EquipmentType.get(structName);
            txtStructAvail.setText(structureType.getFullRatingName(getMech().isClan()));
            txtStructTon.setText(formatWeight(testMech.getWeightStructure()));
            txtStructCrit.setText(formatCrits(structureType.getCriticals(getMech())));
        } else {
            txtStructAvail.setText("-");
            txtStructTon.setText("-");
            txtStructCrit.setText("-");
        }
    }

    private void refreshCockpit(TestMech testMech) {
        if (getMech().getCockpitType() != Mech.COCKPIT_UNKNOWN) {
            txtCockpitAvail.setText("tbd");
            txtCockpitTon.setText(formatWeight(testMech.getWeightCockpit()));
            txtCockpitCrit.setText(formatCrits(getCockpitCrits()));
        } else {
            txtCockpitAvail.setText("-");
            txtCockpitTon.setText("-");
            txtCockpitCrit.setText("-");
        }
    }

    private void refreshEngine(TestMech testMech) {
        if (getMech().hasEngine()) {
            txtEngineAvail.setText(getMech().getEngine().getFullRatingName(getMech().isClan()));
            txtEngineTon.setText(formatWeight(testMech.getWeightEngine()));
            txtEngineCrit.setText(formatCrits(getEngineCrits()));
        } else {
            txtEngineAvail.setText("-");
            txtEngineTon.setText("-");
            txtEngineCrit.setText("-");
        }
    }

    private void refreshGyro(TestMech testMech) {
        if (getMech().getGyroType() != Mech.GYRO_NONE) {
            txtGyroAvail.setText("tbd");
            txtGyroTon.setText(formatWeight(testMech.getWeightGyro()));
            txtGyroCrit.setText(formatCrits(getGyroCrits()));
        } else {
            txtGyroAvail.setText("-");
            txtGyroTon.setText("-");
            txtGyroCrit.setText("-");
        }
    }

    private void runThroughEquipment() {
        double weightJJ = 0.0f;
        double weightEnhance = 0.0f;
        double weightEquip = 0.0f;
        int critJJ = 0;
        int critEquip = 0;
        int critEnhance = 0;

        txtJumpAvail.setText("-");

        for (Mounted m : getMech().getMisc()) {
            MiscType mt = (MiscType) m.getType();
            if (UnitUtil.isArmorOrStructure(mt)) {
                continue;
            } else if (mt.hasFlag(MiscType.F_TSM)
                    || mt.hasFlag(MiscType.F_INDUSTRIAL_TSM)
                    || mt.hasFlag(MiscType.F_MASC)) {
                // There can only be one of these
                weightEnhance += m.getTonnage();
                critEnhance += UnitUtil.getCritsUsed(m);
                txtEnhanceAvail.setText(mt.getFullRatingName(getMech().isClan()));
            } else if (mt.hasFlag(MiscType.F_JUMP_JET)
                    || mt.hasFlag(MiscType.F_JUMP_BOOSTER)) {
                weightJJ += m.getTonnage();
                critJJ += m.getCriticals();
                txtJumpAvail.setText(mt.getFullRatingName(getMech().isClan()));
            } else if (mt.hasFlag(MiscType.F_HEAT_SINK)
                    || mt.hasFlag(MiscType.F_DOUBLE_HEAT_SINK)) {
                continue;
            } else {
                weightEquip += m.getTonnage();
                critEquip += m.getCriticals();
            }
        }
        for (Mounted m : getMech().getWeaponList()) {
            weightEquip += m.getTonnage();
            critEquip += m.getCriticals();
        }
        for (Mounted m : getMech().getAmmo()) {
            weightEquip += m.getTonnage();
            critEquip += m.getCriticals();
        }
        txtJumpTon.setText(formatWeight(weightJJ));
        txtEnhanceTon.setText(formatWeight(weightEnhance));
        txtEquipTon.setText(formatWeight(weightEquip));

        txtJumpCrit.setText(formatCrits(critJJ));
        txtEnhanceCrit.setText(formatCrits(critEnhance));
        txtEquipCrit.setText(formatCrits(critEquip));
    }

    private int getGyroCrits() {
        switch(getMech().getGyroType()) {
            case Mech.GYRO_COMPACT:
                return 2;
            case Mech.GYRO_XL:
                return 6;
            default:
                return 4;
        }
    }

    private int getEngineCrits() {
        if (getMech().getEngine().getEngineType() == Engine.COMPACT_ENGINE) {
            return 3;
        }
        return 6 + (2 * getMech().getEngine().getSideTorsoCriticalSlots().length);
    }

    private int getCockpitCrits() {
        return (getMech().getCockpitType() == Mech.COCKPIT_COMMAND_CONSOLE) ? 2 : 1;
    }

    private String formatCrits(int crits) {
        return (crits == 0) ? "-" : Integer.toString(crits);
    }

    private String formatWeight(Double weight) {
        return (weight == 0 ? "-" : CalculationReport.formatForReport(weight) + " t");
    }

    /** @return the type of heat sinks mounted on this mek (e.g. MiscType.F_HEAT_SINK) */
    public @Nullable String getHeatSinkType(Mech mek) {
        for (Mounted m : mek.getMisc()) {
            if (m.getType().hasFlag(MiscType.F_COMPACT_HEAT_SINK)
                    || m.getType().hasFlag(MiscType.F_HEAT_SINK)
                    || m.getType().hasFlag(MiscType.F_DOUBLE_HEAT_SINK)
                    || m.getType().hasFlag(MiscType.F_LASER_HEAT_SINK)) {
                return m.getType().getInternalName();
            }
        }
        return null;
    }
}
