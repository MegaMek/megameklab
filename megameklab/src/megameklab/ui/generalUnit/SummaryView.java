package megameklab.ui.generalUnit;

import megamek.client.ui.swing.calculationReport.CalculationReport;
import megamek.common.*;
import megamek.common.annotations.Nullable;
import megamek.common.verifier.EntityVerifier;
import megamek.common.verifier.TestMech;
import megameklab.ui.EntitySource;
import megameklab.ui.util.IView;
import megameklab.util.UnitUtil;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Vector;

public class SummaryView extends IView {

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
    private final JTextField txtLiftTon = new JTextField("?");
    private final JTextField txtControlsTon = new JTextField("?");
    private final JTextField txtTurretTon = new JTextField("?");
    private final JTextField txtRearTurretTon = new JTextField("?");
    private final JTextField txtSponsonTon = new JTextField("?");
    private final JTextField txtPowerAmpTon = new JTextField("?");

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
    private final JTextField txtLiftAvail = new JTextField("?");
    private final JTextField txtControlsAvail = new JTextField("?");
    private final JTextField txtTurretAvail = new JTextField("?");
    private final JTextField txtRearTurretAvail = new JTextField("?");
    private final JTextField txtSponsonAvail = new JTextField("?");
    private final JTextField txtPowerAmpAvail = new JTextField("?");

    private final EntityVerifier entityVerifier =
            EntityVerifier.getInstance(new File("data/mechfiles/UnitVerifierOptions.xml"));

    public SummaryView(EntitySource eSource) {
        super(eSource);

        Vector<JTextField> valueFields = new Vector<>();

        valueFields.add(txtStructTon);
        valueFields.add(txtEngineTon);
        valueFields.add(txtGyroTon);
        valueFields.add(txtCockpitTon);
        valueFields.add(txtHeatTon);
        valueFields.add(txtArmorTon);
        valueFields.add(txtEnhanceTon);
        valueFields.add(txtJumpTon);
        valueFields.add(txtEquipTon);
        valueFields.add(txtOtherTon);

        valueFields.add(txtStructCrit);
        valueFields.add(txtEngineCrit);
        valueFields.add(txtGyroCrit);
        valueFields.add(txtCockpitCrit);
        valueFields.add(txtHeatCrit);
        valueFields.add(txtArmorCrit);
        valueFields.add(txtEnhanceCrit);
        valueFields.add(txtJumpCrit);
        valueFields.add(txtEquipCrit);
        valueFields.add(txtOtherCrit);

        Dimension size = new Dimension(45, 25);
        for (JTextField field : valueFields) {
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
        valueFields.add(txtGyroAvail);
        valueFields.add(txtCockpitAvail);
        valueFields.add(txtHeatAvail);
        valueFields.add(txtArmorAvail);
        valueFields.add(txtEnhanceAvail);
        valueFields.add(txtJumpAvail);
        valueFields.add(txtEquipAvail);
        valueFields.add(txtOtherAvail);

        size = new Dimension(90, 25);
        for (JTextField field : valueFields) {
            field.setEditable(false);
            field.setSize(size);
            field.setPreferredSize(size);
            field.setMinimumSize(size);
            field.setMaximumSize(size);
        }

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        size = new Dimension(120, 25);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 0, 5);
        this.add(createLabel("Category", size, SwingConstants.CENTER), gbc);
        gbc.gridy = 1;
        this.add(createLabel("Internal Structure:", size, SwingConstants.RIGHT), gbc);
        gbc.gridy = 2;
        this.add(createLabel("Engine:", size, SwingConstants.RIGHT), gbc);
        if (showGyro()) {
            gbc.gridy++;
            this.add(createLabel("Gyro:", size, SwingConstants.RIGHT), gbc);
            gbc.gridx = 2;
            this.add(txtGyroCrit, gbc);
        }
        gbc.gridy = 4;
        this.add(createLabel("Cockpit:", size, SwingConstants.RIGHT), gbc);
        gbc.gridy = 5;
        this.add(createLabel("Heat Sinks:", size, SwingConstants.RIGHT), gbc);
        gbc.gridy = 6;
        this.add(createLabel("Armor:", size, SwingConstants.RIGHT), gbc);
        gbc.gridy = 7;
        this.add(createLabel("Enhancements:", size, SwingConstants.RIGHT), gbc);
        gbc.gridy = 8;
        this.add(createLabel("Jump Jets:", size, SwingConstants.RIGHT), gbc);
        gbc.gridy = 9;
        this.add(createLabel("Equipment", size, SwingConstants.RIGHT), gbc);
        gbc.gridy = 10;
        this.add(createLabel("Other:", size, SwingConstants.RIGHT), gbc);

        size = new Dimension(45, 25);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 0, 0);
        this.add(createLabel("Weight", size, SwingConstants.CENTER), gbc);
        gbc.gridy = 1;
        this.add(txtStructTon, gbc);
        gbc.gridy = 2;
        this.add(txtEngineTon, gbc);
        gbc.gridy = 3;
        this.add(txtGyroTon, gbc);
        gbc.gridy = 4;
        this.add(txtCockpitTon, gbc);
        gbc.gridy = 5;
        this.add(txtHeatTon, gbc);
        gbc.gridy = 6;
        this.add(txtArmorTon, gbc);
        gbc.gridy = 7;
        this.add(txtEnhanceTon, gbc);
        gbc.gridy = 8;
        this.add(txtJumpTon, gbc);
        gbc.gridy = 9;
        this.add(txtEquipTon, gbc);
        gbc.gridy = 10;
        this.add(txtOtherTon, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        this.add(createLabel("Crits", size, SwingConstants.CENTER), gbc);
        gbc.gridy = 1;
        this.add(txtStructCrit, gbc);
        gbc.gridy = 2;
        this.add(txtEngineCrit, gbc);
        gbc.gridy = 3;
        this.add(txtGyroCrit, gbc);
        gbc.gridy = 4;
        this.add(txtCockpitCrit, gbc);
        gbc.gridy = 5;
        this.add(txtHeatCrit, gbc);
        gbc.gridy = 6;
        this.add(txtArmorCrit, gbc);
        gbc.gridy = 7;
        this.add(txtEnhanceCrit, gbc);
        gbc.gridy = 8;
        this.add(txtJumpCrit, gbc);
        gbc.gridy = 9;
        this.add(txtEquipCrit, gbc);
        gbc.gridy = 10;
        this.add(txtOtherCrit, gbc);

        size = new Dimension(80,25);
        gbc.gridx = 3;
        gbc.gridy = 0;
        this.add(createLabel("Availability", size, SwingConstants.CENTER), gbc);
        gbc.gridy = 1;
        this.add(txtStructAvail, gbc);
        gbc.gridy = 2;
        this.add(txtEngineAvail, gbc);
        gbc.gridy = 3;
        this.add(txtGyroAvail, gbc);
        gbc.gridy = 4;
        this.add(txtCockpitAvail, gbc);
        gbc.gridy = 5;
        this.add(txtHeatAvail, gbc);
        gbc.gridy = 6;
        this.add(txtArmorAvail, gbc);
        gbc.gridy = 7;
        this.add(txtEnhanceAvail, gbc);
        gbc.gridy = 8;
        this.add(txtJumpAvail, gbc);
        gbc.gridy = 9;
        this.add(txtEquipAvail, gbc);
        gbc.gridy = 10;
        this.add(txtOtherAvail, gbc);

        setBorder(BorderFactory.createTitledBorder("Summary"));
    }

    protected boolean showGyro() {
        return false;
    }

    private JLabel createLabel(String text, Dimension size, int align) {
        JLabel label = new JLabel(text, SwingConstants.TRAILING);
        setFieldSize(label, size);
        label.setHorizontalAlignment(align);
        return label;
    }

    protected void setFieldSize(JComponent box, Dimension maxSize) {
        box.setPreferredSize(maxSize);
        box.setMaximumSize(maxSize);
        box.setMinimumSize(maxSize);
    }

    public void refresh() {
        TestMech testMech = new TestMech(getMech(), entityVerifier.mechOption, null);
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
