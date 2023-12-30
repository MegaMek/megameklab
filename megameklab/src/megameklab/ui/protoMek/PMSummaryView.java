/*
 * MegaMekLab - Copyright (C) 2018 - The MegaMek Team
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 */
package megameklab.ui.protoMek;

import megamek.common.MiscType;
import megamek.common.Mounted;
import megamek.common.verifier.EntityVerifier;
import megamek.common.verifier.TestProtomech;
import megameklab.ui.EntitySource;
import megameklab.ui.generalUnit.summary.*;
import megameklab.ui.util.IView;
import megameklab.util.UnitUtil;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Vector;

/**
 * Summary view that shows weight breakdown by category for ProtoMeks
 * 
 * @author Neoancient
 */
public class PMSummaryView extends IView {

    private final SummaryItem structureSummary = new StructureSummaryItem();
    private final SummaryItem engineSummary = new EngineSummaryItem();
    private final SummaryItem heatsinkSummary = new HeatsinkSummaryItem();
    private final SummaryItem controlsSummary = new ControlsSummaryItem();
    private final SummaryItem armorSummary = new ArmorSummaryItem();
    private final SummaryItem jumpSummary = new JumpSummaryItem();
    private final SummaryItem equipmentSummary = new EquipmentSummaryItem();
    private final SummaryItem myomerSummary = new MyomerEnhancementSummaryItem();

    private final java.util.List<SummaryItem> summaryItemList = List.of(structureSummary, engineSummary,
            heatsinkSummary, controlsSummary, armorSummary, jumpSummary, myomerSummary,
            equipmentSummary);

    private final SummaryView summaryView = new SummaryView(summaryItemList, false);

    public PMSummaryView(EntitySource eSource) {
        super(eSource);
        add(summaryView);
    }

    public void refresh() {
        summaryView.refresh(getEntity());
    }
//    private JTextField txtStructKg = new JTextField("?");
//    private JTextField txtEngineKg = new JTextField("?");
//    private JTextField txtControlKg = new JTextField("?");
//    private JTextField txtHeatKg = new JTextField("?");
//    private JTextField txtArmorKg = new JTextField("?");
//    private JTextField txtEnhanceKg = new JTextField("?");
//    private JTextField txtJumpKg = new JTextField("?");
//    private JTextField txtWeaponsKg = new JTextField("?");
//    private JTextField txtAmmoKg = new JTextField("?");
//    private JTextField txtMiscKg = new JTextField("?");
//
//    private EntityVerifier entityVerifier = EntityVerifier.getInstance(new File("data/mechfiles/UnitVerifierOptions.xml"));
//
//    public PMSummaryView(EntitySource eSource) {
//        super(eSource);
//
//        Vector<JTextField> valueFields = new Vector<>();
//
//        valueFields.add(txtStructKg);
//        valueFields.add(txtEngineKg);
//        valueFields.add(txtControlKg);
//        valueFields.add(txtHeatKg);
//        valueFields.add(txtArmorKg);
//        valueFields.add(txtEnhanceKg);
//        valueFields.add(txtJumpKg);
//        valueFields.add(txtWeaponsKg);
//        valueFields.add(txtAmmoKg);
//        valueFields.add(txtMiscKg);
//
//        Dimension size = new Dimension(60,25);
//        for (JTextField field : valueFields) {
//            field.setEditable(false);
//            field.setSize(size);
//            field.setPreferredSize(size);
//            field.setMinimumSize(size);
//            field.setMaximumSize(size);
//            field.setHorizontalAlignment(SwingConstants.RIGHT);
//        }
//
//        valueFields.removeAllElements();
//
//        setLayout(new GridBagLayout());
//        GridBagConstraints gbc = new GridBagConstraints();
//
//        size = new Dimension(180,25);
//
//        gbc.gridx = 0;
//        gbc.gridy = 0;
//        gbc.insets = new Insets(0,0,0,5);
//        this.add(createLabel("Category", size, SwingConstants.CENTER), gbc);
//        gbc.gridy++;
//        this.add(createLabel("Internal Structure:", size, SwingConstants.RIGHT), gbc);
//        gbc.gridy++;
//        this.add(createLabel("Engine:", size, SwingConstants.RIGHT), gbc);
//        gbc.gridy++;
//        this.add(createLabel("Cockpit:", size, SwingConstants.RIGHT), gbc);
//        gbc.gridy++;
//        this.add(createLabel("Heat Sinks:", size, SwingConstants.RIGHT), gbc);
//        gbc.gridy++;
//        this.add(createLabel("Armor:", size, SwingConstants.RIGHT), gbc);
//        gbc.gridy++;
//        this.add(createLabel("Enhancements:", size, SwingConstants.RIGHT), gbc);
//        gbc.gridy++;
//        this.add(createLabel("Jump Jets:", size, SwingConstants.RIGHT), gbc);
//        gbc.gridy++;
//        this.add(createLabel("Weapons", size, SwingConstants.RIGHT), gbc);
//        gbc.gridy++;
//        this.add(createLabel("Ammo:", size, SwingConstants.RIGHT), gbc);
//        gbc.gridy++;
//        this.add(createLabel("Miscellaneous:", size, SwingConstants.RIGHT), gbc);
//
//        size = new Dimension(45,25);
//        gbc.gridx = 1;
//        gbc.gridy = 0;
//        gbc.insets = new Insets(0,0,0,0);
//        this.add(createLabel("Kg", size, SwingConstants.CENTER), gbc);
//        gbc.gridy++;
//        this.add(txtStructKg, gbc);
//        gbc.gridy++;
//        this.add(txtEngineKg, gbc);
//        gbc.gridy++;
//        this.add(txtControlKg, gbc);
//        gbc.gridy++;
//        this.add(txtHeatKg, gbc);
//        gbc.gridy++;
//        this.add(txtArmorKg, gbc);
//        gbc.gridy++;
//        this.add(txtEnhanceKg, gbc);
//        gbc.gridy++;
//        this.add(txtJumpKg, gbc);
//        gbc.gridy++;
//        this.add(txtWeaponsKg, gbc);
//        gbc.gridy++;
//        this.add(txtAmmoKg, gbc);
//        gbc.gridy++;
//        this.add(txtMiscKg, gbc);
//
//        setBorder(BorderFactory.createTitledBorder("Summary"));
//    }
//
//    private JLabel createLabel(String text, Dimension size, int align) {
//        JLabel label = new JLabel(text, SwingConstants.TRAILING);
//
//        setFieldSize(label, size);
//        label.setHorizontalAlignment(align);
//        return label;
//    }
//
//    public void setFieldSize(JComponent box, Dimension maxSize) {
//        box.setPreferredSize(maxSize);
//        box.setMaximumSize(maxSize);
//        box.setMinimumSize(maxSize);
//    }
//
//    public void refresh() {
//        final NumberFormat format = DecimalFormat.getInstance();
//        final TestProtomech testProto = new TestProtomech(getProtoMek(), entityVerifier.mechOption, null);
//
//        txtEngineKg.setText(format.format(testProto.getWeightEngine() * 1000));
//        txtControlKg.setText(format.format(testProto.getWeightControls() * 1000));
//        txtHeatKg.setText(format.format(testProto.getWeightHeatSinks() * 1000));
//        txtStructKg.setText(format.format(testProto.getWeightStructure() * 1000));
//        txtArmorKg.setText(format.format(testProto.getWeightArmor() * 1000));
//        txtWeaponsKg.setText(format.format(testProto.getWeightWeapon() * 1000));
//        txtAmmoKg.setText(format.format(testProto.getWeightAmmo() * 1000));
//        txtMiscKg.setText(format.format(testProto.getWeightMisc() * 1000));
//
//        runThroughEquipment();
//    }
//
//    private void runThroughEquipment() {
//        final NumberFormat format = DecimalFormat.getInstance();
//        double weightJJ = 0.0f;
//        double weightEnhance = 0.0f;
//
//        for (Mounted m : getProtoMek().getMisc()) {
//            MiscType mt = (MiscType) m.getType();
//            if (UnitUtil.isArmorOrStructure(mt)) {
//                continue;
//            } else if (mt.hasFlag(MiscType.F_MASC)) {
//                weightEnhance += m.getTonnage();
//            } else if (mt.hasFlag(MiscType.F_JUMP_JET) || mt.hasFlag(MiscType.F_UMU)) {
//                weightJJ += m.getTonnage();
//            }
//        }
//        txtJumpKg.setText(format.format(weightJJ * 1000));
//        txtEnhanceKg.setText(format.format(weightEnhance * 1000));
//    }
}
