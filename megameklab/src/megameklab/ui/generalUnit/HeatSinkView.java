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

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.formdev.flatlaf.FlatClientProperties;
import megamek.client.ui.util.DisplayTextField;
import megamek.common.equipment.EquipmentType;
import megamek.common.equipment.EquipmentTypeLookup;
import megamek.common.equipment.MiscType;
import megamek.common.equipment.Mounted;
import megamek.common.interfaces.ITechManager;
import megamek.common.units.Aero;
import megamek.common.units.Entity;
import megamek.common.units.Mek;
import megamek.common.verifier.TestAero;
import megameklab.ui.listeners.BuildListener;
import megameklab.ui.util.CustomComboBox;
import megameklab.util.UnitUtil;

/**
 * Controls for selecting type and number of heat sinks for Meks and ASFs.
 *
 * @author Neoancient
 */
public class HeatSinkView extends BuildView implements ActionListener, ChangeListener {
    private final List<BuildListener> listeners = new CopyOnWriteArrayList<>();

    public void addListener(BuildListener l) {
        listeners.add(l);
    }

    public void removeListener(BuildListener l) {
        listeners.remove(l);
    }

    public static final ResourceBundle I18N = ResourceBundle.getBundle("megameklab.resources.Views");

    public static final int TYPE_SINGLE = 0;
    public static final int TYPE_DOUBLE_IS = 1;
    public static final int TYPE_DOUBLE_CLAN = 2;
    public static final int TYPE_COMPACT = 3;
    // UNUSED public static final int TYPE_LASER = 4;
    public static final int TYPE_PROTOTYPE = 5;
    public static final int TYPE_FREEZER = 6;
    // ASFs simply use an index and don't distinguish between IS and Clan
    public static final int TYPE_DOUBLE_AERO = 1;
    public static final int TYPE_PROTOTYPE_AERO = 2;

    private static final List<String> LOOKUP_NAMES = List.of(
          EquipmentTypeLookup.SINGLE_HS, EquipmentTypeLookup.IS_DOUBLE_HS,
          EquipmentTypeLookup.CLAN_DOUBLE_HS, EquipmentTypeLookup.COMPACT_HS_1,
          EquipmentTypeLookup.LASER_HS, EquipmentTypeLookup.IS_DOUBLE_HS_PROTOTYPE,
          EquipmentTypeLookup.IS_DOUBLE_HS_FREEZER);
    private final List<EquipmentType> heatSinks = LOOKUP_NAMES.stream().map(EquipmentType::get).toList();

    private String[] MekDisplayNames;
    private String[] aeroDisplayNames;

    private final CustomComboBox<Integer> cbHSType = new CustomComboBox<>(this::getDisplayName);
    private final JSpinner spnCount = new JSpinner();
    private final JLabel lblBaseCount =
          createLabel("omniBaseHS",
                I18N.getString("HeatSinkView.spnBaseCount.text"),
                I18N.getString("HeatSinkView.spnBaseCount.tooltip"));
    private final JSpinner spnBaseCount = new JSpinner();
    private final JLabel lblPrototypeCount =
          createLabel("P-DoubleCount",
                I18N.getString("HeatSinkView.spnPrototypeCount.text"),
                I18N.getString("HeatSinkView.spnPrototypeCount.tooltip"));
    private final JSpinner spnPrototypeCount = new JSpinner();
    private final JLabel protoInfo = new JLabel("4 single heat sinks");
    private final JLabel lblCritFreeText = createLabel("critFree",
          I18N.getString("HeatSinkView.lblCritFree.text"),
          I18N.getString("HeatSinkView.lblCritFree.tooltip"));
    private final DisplayTextField lblCritFreeCount = new DisplayTextField();

    private final DisplayTextField lblWeightFreeCount = new DisplayTextField();
    private final DisplayTextField lblTotalDissipationCount = new DisplayTextField();
    private final DisplayTextField lblMaxHeatCount = new DisplayTextField();
    private final JCheckBox chkRiscHeatSinkKit = new JCheckBox(I18N.getString("HeatSinkView.lblRiscHeatSinkKit.text")){
        @Override
        public Dimension getPreferredSize() {
            // make the height align with the other text fields
            return new Dimension(super.getPreferredSize().width, lblTotalDissipationCount.getPreferredSize().height);
        }
    };

    private final SpinnerNumberModel countModel = new SpinnerNumberModel(0, 0, 100000, 1);
    private final SpinnerNumberModel baseCountModel = new SpinnerNumberModel(0, 0, null, 1);
    private final SpinnerNumberModel prototypeCountModel = new SpinnerNumberModel(0, 0, null, 1);

    private final ITechManager techManager;
    private boolean isAero;
    private boolean isPrimitive;
    private boolean hasPrototypeDoubles;

    public HeatSinkView(ITechManager techManager) {
        this.techManager = techManager;
        initUI();
    }

    private void initUI() {
        MekDisplayNames = I18N.getString("HeatSinkView.mekNames.values").split(",");
        aeroDisplayNames = I18N.getString("HeatSinkView.aeroNames.values").split(",");

        cbHSType.setPrototypeDisplayValue(TYPE_PROTOTYPE);
        spnCount.setModel(countModel);
        lblCritFreeCount.setHorizontalAlignment(JTextField.RIGHT);
        spnBaseCount.setModel(baseCountModel);
        spnPrototypeCount.setModel(prototypeCountModel);
        lblWeightFreeCount.setHorizontalAlignment(JTextField.RIGHT);
        lblWeightFreeCount.setToolTipText(I18N.getString("HeatSinkView.lblWeightFree.tooltip"));
        lblTotalDissipationCount.setHorizontalAlignment(JTextField.RIGHT);
        lblMaxHeatCount.setHorizontalAlignment(JTextField.RIGHT);
        chkRiscHeatSinkKit.setVisible(false);
        chkRiscHeatSinkKit.addActionListener(this);
        JLabel weightFreeLabel = createLabel("weightFree",
              I18N.getString("HeatSinkView.lblWeightFree.text"),
              I18N.getString("HeatSinkView.lblWeightFree.tooltip"));
        spnPrototypeCount.setToolTipText(I18N.getString("HeatSinkView.spnPrototypeCount.tooltip"));
        chkRiscHeatSinkKit.setToolTipText(I18N.getString("HeatSinkView.lblRiscHeatSinkKit.tooltip"));
        lblCritFreeCount.setToolTipText(I18N.getString("HeatSinkView.lblCritFree.tooltip"));
        protoInfo.putClientProperty(FlatClientProperties.STYLE_CLASS, "mini");
        spnBaseCount.setToolTipText(I18N.getString("HeatSinkView.spnBaseCount.tooltip"));

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = STANDARD_INSETS;

        gbc.gridy = 0;
        add(new JLabel(I18N.getString("HeatSinkView.cbHSType.text"), SwingConstants.RIGHT), gbc);
        add(cbHSType, gbc);

        gbc.gridy++;
        add(new JLabel(I18N.getString("HeatSinkView.spnCount.text"), SwingConstants.RIGHT), gbc);
        add(spnCount, gbc);

        gbc.gridy++;
        add(lblCritFreeText, gbc);
        add(lblCritFreeCount, gbc);

        gbc.gridy++;
        add(lblBaseCount, gbc);
        add(spnBaseCount, gbc);

        gbc.gridy++;
        add(lblPrototypeCount, gbc);
        add(spnPrototypeCount, gbc);

        gbc.gridy++;
        add(new JLabel(), gbc);
        add(protoInfo, gbc);

        gbc.gridy++;
        add(weightFreeLabel, gbc);
        add(lblWeightFreeCount, gbc);

        gbc.gridy++;
        add(new JLabel(I18N.getString("HeatSinkView.lblTotalDissipation.text"), SwingConstants.RIGHT), gbc);
        add(lblTotalDissipationCount, gbc);

        gbc.gridy++;
        add(new JLabel(I18N.getString("HeatSinkView.lblMaxHeat.text"), SwingConstants.RIGHT), gbc);
        add(lblMaxHeatCount, gbc);

        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        add(chkRiscHeatSinkKit, gbc);

        spnCount.addChangeListener(this);
        cbHSType.addActionListener(this);
        spnBaseCount.addChangeListener(this);
        spnPrototypeCount.addChangeListener(this);
    }

    private String getDisplayName(int index) {
        return isAero ? aeroDisplayNames[index] : MekDisplayNames[index];
    }

    private void showRiscKit(boolean show) {
        chkRiscHeatSinkKit.setVisible(show);
        if (!show) {
            chkRiscHeatSinkKit.setSelected(false);
        }
    }

    public void setFromMek(Mek mek) {
        isAero = false;
        isPrimitive = mek.isPrimitive();
        hasPrototypeDoubles = mek.hasWorkingMisc(MiscType.F_IS_DOUBLE_HEAT_SINK_PROTOTYPE);
        refresh();
        // If there are prototype doubles, we want to skip any singles and select that as the base type.
        Optional<MiscType> hs = mek.getMisc().stream().map(Mounted::getType)
              .filter(et -> et.hasFlag(MiscType.F_IS_DOUBLE_HEAT_SINK_PROTOTYPE)).findAny();
        if (hs.isEmpty()) {
            hs = mek.getMisc().stream().map(Mounted::getType).filter(UnitUtil::isHeatSink).findAny();
        }
        if (hs.isPresent()) {
            if (hs.get().is(EquipmentTypeLookup.COMPACT_HS_2)) {
                // 2 CHS packed in 1 slot is its own MiscType but doesnt find the right combobox entry; must translate it manually
                hs = Optional.of((MiscType) EquipmentType.get(EquipmentTypeLookup.COMPACT_HS_1));
            }
            cbHSType.removeActionListener(this);
            setHeatSinkType(hs.get());
            cbHSType.addActionListener(this);
        }
        int totalSinks = mek.heatSinks(true);
        spnCount.removeChangeListener(this);
        countModel.setValue(totalSinks);
        countModel.setMinimum(mek.getEngine().getWeightFreeEngineHeatSinks());
        spnCount.addChangeListener(this);
        boolean isCompact = cbHSType.getSelectedItem() != null
              && ((Integer) cbHSType.getSelectedItem()) == TYPE_COMPACT;
        int capacity = mek.getEngine().integralHeatSinkCapacity(isCompact);
        lblBaseCount.setVisible(mek.isOmni());
        spnBaseCount.setVisible(mek.isOmni());
        spnBaseCount.removeChangeListener(this);
        baseCountModel.setMaximum(capacity);
        baseCountModel.setValue(Math.max(0, mek.getEngine().getBaseChassisHeatSinks(isCompact)));
        spnBaseCount.addChangeListener(this);
        lblPrototypeCount.setVisible(hasPrototypeDoubles);
        spnPrototypeCount.setVisible(hasPrototypeDoubles);
        spnPrototypeCount.removeChangeListener(this);
        spnPrototypeCount.setValue(totalSinks - mek.heatSinks(false));
        prototypeCountModel.setMaximum(totalSinks);
        prototypeCountModel.setMinimum(hasPrototypeDoubles ? 1 : 0);
        protoInfo.setVisible(hasPrototypeDoubles);
        protoInfo.setText(MessageFormat.format(I18N.getString("HeatSinkView.spnPrototypeCount.info"),
              mek.heatSinks(false)));

        spnPrototypeCount.addChangeListener(this);
        lblCritFreeCount.setText(String.valueOf(UnitUtil.getCriticalFreeHeatSinks(mek, isCompact)));
        lblWeightFreeCount.setText(String.valueOf(mek.getEngine().getWeightFreeEngineHeatSinks()));
        lblTotalDissipationCount.setText(String.valueOf(mek.getHeatCapacity(true, false)));
        lblMaxHeatCount.setText(String.valueOf(UnitUtil.getTotalHeatGeneration(mek)));

        showRiscKit(techManager.isLegal(Mek.getRiscHeatSinkOverrideKitAdvancement()));
        if (mek.hasRiscHeatSinkOverrideKit()) {
            chkRiscHeatSinkKit.setSelected(true);
        }
    }

    public void setFromAero(Aero aero) {
        isAero = true;
        isPrimitive = aero.isPrimitive();
        refresh();
        cbHSType.removeActionListener(this);
        // Roundabout way to make it show "Double (Prototype)"
        if (aero.getHeatType() == Aero.HEAT_DOUBLE
              && !techManager.isLegal(heatSinks.get(TYPE_DOUBLE_AERO))
              && (techManager.isLegal(heatSinks.get(TYPE_PROTOTYPE))
              || techManager.isLegal(heatSinks.get(TYPE_FREEZER)))) {
            setHeatSinkIndex(TYPE_PROTOTYPE_AERO);
        } else {
            setHeatSinkIndex(aero.getHeatType());
        }
        cbHSType.addActionListener(this);
        spnCount.removeChangeListener(this);
        countModel.setValue(aero.getHeatSinks());
        if (!aero.hasETypeFlag(Entity.ETYPE_SMALL_CRAFT) && !aero.hasETypeFlag(Entity.ETYPE_JUMPSHIP)) {
            countModel.setMinimum(TestAero.weightFreeHeatSinks(aero));
        }
        spnCount.addChangeListener(this);
        spnBaseCount.removeChangeListener(this);
        baseCountModel.setMaximum(aero.getHeatSinks());
        baseCountModel.setValue(Math.max(0, aero.getHeatSinks() - aero.getPodHeatSinks()));
        spnBaseCount.addChangeListener(this);
        lblWeightFreeCount.setText(String.valueOf(TestAero.weightFreeHeatSinks(aero)));
        lblTotalDissipationCount.setText(String.valueOf(aero.getHeatCapacity(false)));
        lblMaxHeatCount.setText(String.valueOf(UnitUtil.getTotalHeatGeneration(aero)));
        lblBaseCount.setVisible(aero.isOmni());
        spnBaseCount.setVisible(aero.isOmni());
        lblPrototypeCount.setVisible(false);
        spnPrototypeCount.setVisible(false);
        protoInfo.setVisible(false);
        lblCritFreeText.setVisible(false);
        lblCritFreeCount.setVisible(false);

        showRiscKit(false);
    }

    public void refresh() {
        Integer prev = (Integer) cbHSType.getSelectedItem();
        cbHSType.removeActionListener(this);
        cbHSType.removeAllItems();
        if (isAero) {
            cbHSType.addItem(TYPE_SINGLE);
            if (techManager.isLegal(heatSinks.get(TYPE_DOUBLE_IS))
                  || techManager.isLegal(heatSinks.get(TYPE_DOUBLE_CLAN))) {
                cbHSType.addItem(TYPE_DOUBLE_AERO);
            } else if (techManager.isLegal(heatSinks.get(TYPE_PROTOTYPE))
                  || techManager.isLegal(heatSinks.get(TYPE_FREEZER))) {
                cbHSType.addItem(TYPE_PROTOTYPE_AERO);
            }
        } else if (isPrimitive) {
            cbHSType.addItem(TYPE_SINGLE);
        } else {
            for (int i = 0; i < heatSinks.size(); i++) {
                if (techManager.isLegal(heatSinks.get(i))) {
                    cbHSType.addItem(i);
                }
            }
        }
        cbHSType.setSelectedItem(prev);
        cbHSType.addActionListener(this);
        if (cbHSType.getSelectedIndex() < 0) {
            cbHSType.setSelectedIndex(0);
        }

        showRiscKit(techManager.isLegal(Mek.getRiscHeatSinkOverrideKitAdvancement()) && !isAero);
    }

    public int getHeatSinkIndex() {
        return (cbHSType.getSelectedItem() instanceof Integer index) ? index : 0;
    }

    public void setHeatSinkIndex(int index) {
        cbHSType.setSelectedItem(index);
    }

    public EquipmentType getHeatSinkType() {
        return heatSinks.get(getHeatSinkIndex());
    }

    public void setHeatSinkType(EquipmentType hs) {
        int index = heatSinks.indexOf(hs);
        cbHSType.setSelectedItem(index);
    }

    public int getCount() {
        return countModel.getNumber().intValue();
    }

    public int getBaseCount() {
        return baseCountModel.getNumber().intValue();
    }

    /**
     * @return The number of heat sinks out of the total that are prototype double heat sinks.
     */
    public int getPrototypeCount() {
        return hasPrototypeDoubles ? prototypeCountModel.getNumber().intValue() : 0;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cbHSType) {
            reportChange();
        } else if (e.getSource() == chkRiscHeatSinkKit) {
            listeners.forEach(l -> l.riscHeatSinkOverrideKitChanged(chkRiscHeatSinkKit.isSelected()));
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() == spnCount) {
            reportChange();
        } else if (e.getSource() == spnBaseCount) {
            listeners.forEach(l -> l.heatSinkBaseCountChanged(getBaseCount()));
            lblCritFreeCount.setText(String.valueOf(getBaseCount()));
        } else if (e.getSource() == spnPrototypeCount) {
            listeners.forEach(l -> l.redistributePrototypeHS(getPrototypeCount()));
        }
    }

    private void reportChange() {
        if (isAero) {
            listeners.forEach(l -> l.heatSinksChanged(Math.min(TYPE_DOUBLE_AERO, getHeatSinkIndex()), getCount()));
        } else {
            listeners.forEach(l -> l.heatSinksChanged(getHeatSinkType(), getCount()));
        }
    }
}
