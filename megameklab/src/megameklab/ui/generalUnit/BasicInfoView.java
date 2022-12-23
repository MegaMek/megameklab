/*
 * Copyright (c) 2017-2022 - The MegaMek Team. All Rights Reserved.
 *
 * This file is part of MegaMekLab.
 *
 * MegaMekLab is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MegaMekLab is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MegaMekLab. If not, see <http://www.gnu.org/licenses/>.
 */
package megameklab.ui.generalUnit;

import megamek.MMConstants;
import megamek.common.*;
import megameklab.ui.listeners.BuildListener;
import megameklab.ui.util.CustomComboBox;
import megameklab.ui.util.FactionComboBox;
import megameklab.ui.util.IntRangeTextField;
import megameklab.util.CConfig;
import org.apache.logging.log4j.LogManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.net.URI;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * A panel for basic information common to all unit types: name, year, tech level and others.
 * 
 * @author Neoancient
 */
public class BasicInfoView extends BuildView implements ITechManager, ActionListener, FocusListener {
    //region Variable Declarations
    private static final TechAdvancement TA_MIXED_TECH = Entity.getMixedTechAdvancement();
    private static final int CLAN_START = 2807;
    private static final int IS_MIXED_START = TA_MIXED_TECH.getIntroductionDate(false);
    private static final int CLAN_MIXED_START = TA_MIXED_TECH.getIntroductionDate(true);

    private static final int BASE_IS = 0;
    private static final int BASE_CLAN = 1;
    private static final int BASE_IS_MIXED = 2;
    private static final int BASE_CLAN_MIXED = 3;

    private String[] techBaseNames;
    private TechAdvancement baseTA;

    private final JTextField txtChassis = new JTextField(5);
    private final JTextField txtModel = new JTextField(5);
    private final IntRangeTextField txtYear = new IntRangeTextField(3);
    private final FactionComboBox cbFaction = new FactionComboBox();
    private final JLabel lblFaction = createLabel("lblFaction", "", labelSize);
    private final JTextField txtSource = new JTextField(3);
    private final CustomComboBox<Integer> cbTechBase = new CustomComboBox<>(i -> String.valueOf(techBaseNames[i]));
    private final JComboBox<SimpleTechLevel> cbTechLevel = new JComboBox<>();
    private final IntRangeTextField txtManualBV = new IntRangeTextField(3);
    private final JLabel lblMulId = createLabel("lblMulId", "", labelSize);
    private final IntRangeTextField txtMulId = new IntRangeTextField(8);
    private final JButton browseMul = new JButton("Open MUL in Browser");

    private int prevYear = 3145;
    private final List<BuildListener> listeners = new CopyOnWriteArrayList<>();
    //endregion Variable Declarations

    //region Constructors
    public BasicInfoView(TechAdvancement baseTA) {
        this.baseTA = baseTA;
        initUI();
    }
    //endregion Constructors

    private void initUI() {
        ResourceBundle resourceMap = ResourceBundle.getBundle("megameklab.resources.Views");
        techBaseNames = resourceMap.getString("BasicInfoView.cbTechBase.values").split(",");

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 0, 1, 2);
        add(createLabel(resourceMap, "lblChassis", "BasicInfoView.txtChassis.text",
                "BasicInfoView.txtChassis.tooltip", labelSize), gbc);
        gbc.gridx = 1;
        txtChassis.setToolTipText(resourceMap.getString("BasicInfoView.txtChassis.tooltip"));
        add(txtChassis, gbc);
        setFieldSize(txtChassis, controlSize);
        txtChassis.addFocusListener(this);

        gbc.gridx = 0;
        gbc.gridy++;
        add(createLabel(resourceMap, "lblModel", "BasicInfoView.txtModel.text",
                "BasicInfoView.txtModel.tooltip", labelSize), gbc);
        gbc.gridx = 1;
        txtModel.setToolTipText(resourceMap.getString("BasicInfoView.txtModel.tooltip"));
        add(txtModel, gbc);
        setFieldSize(txtModel, controlSize);
        txtModel.addFocusListener(this);

        gbc.gridx = 0;
        gbc.gridy++;
        lblMulId.setText(resourceMap.getString("BasicInfoView.txtMulId.text"));
        add(lblMulId, gbc);
        gbc.gridx = 1;
        txtMulId.setToolTipText(resourceMap.getString("BasicInfoView.txtMulId.tooltip"));
        add(txtMulId, gbc);
        setFieldSize(txtMulId, controlSize);
        txtMulId.addFocusListener(this);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 30, 0, 30);
        browseMul.setToolTipText(resourceMap.getString("BasicInfoView.browseMul.tooltip"));
        browseMul.addActionListener(e -> openMUL());
        add(browseMul, gbc);
        gbc.insets = new Insets(0, 0, 1, 2);
        gbc.gridwidth = 1;

        gbc.gridx = 0;
        gbc.gridy++;
        add(createLabel(resourceMap, "lblYear", "BasicInfoView.txtYear.text",
                "BasicInfoView.txtYear.tooltip", labelSize), gbc);
        gbc.gridx = 1;
        add(txtYear, gbc);
        txtYear.setToolTipText(resourceMap.getString("BasicInfoView.txtYear.tooltip"));
        setFieldSize(txtYear, controlSize);
        txtYear.setMaximum(9999);
        txtYear.addFocusListener(this);

        gbc.gridx = 0;
        gbc.gridy++;
        lblFaction.setText(resourceMap.getString("BasicInfoView.cbFaction.text"));
        add(lblFaction, gbc);
        gbc.gridx = 1;
        cbFaction.setToolTipText(resourceMap.getString("BasicInfoView.cbFaction.tooltip"));
        add(cbFaction, gbc);
        setFieldSize(cbFaction, controlSize);
        cbFaction.addActionListener(this);

        gbc.gridx = 0;
        gbc.gridy++;
        add(createLabel(resourceMap, "lblSource", "BasicInfoView.txtSource.text",
                "BasicInfoView.txtSource.tooltip", labelSize), gbc);
        gbc.gridx = 1;
        setFieldSize(txtSource, controlSize);
        txtSource.setToolTipText(resourceMap.getString("BasicInfoView.txtSource.tooltip"));
        add(txtSource, gbc);
        txtSource.addFocusListener(this);

        gbc.gridx = 0;
        gbc.gridy++;
        add(createLabel(resourceMap, "lblTechBase", "BasicInfoView.cbTechBase.text",
                "BasicInfoView.cbTechBase.tooltip", labelSize), gbc);
        gbc.gridx = 1;
        setFieldSize(cbTechBase, controlSize);
        cbTechBase.setToolTipText(resourceMap.getString("BasicInfoView.cbTechBase.tooltip"));
        add(cbTechBase, gbc);
        cbTechBase.addActionListener(this);

        gbc.gridx = 0;
        gbc.gridy++;
        add(createLabel(resourceMap, "lblTechLevel", "BasicInfoView.cbTechLevel.text",
                "BasicInfoView.cbTechLevel.tooltip", labelSize), gbc);
        gbc.gridx = 1;
        setFieldSize(cbTechLevel, controlSize);
        cbTechLevel.setToolTipText(resourceMap.getString("BasicInfoView.cbTechLevel.tooltip"));
        add(cbTechLevel, gbc);
        cbTechLevel.addActionListener(this);
        refreshTechBase();

        gbc.gridx = 0;
        gbc.gridy++;
        add(createLabel(resourceMap, "lblManualBV", "BasicInfoView.txtManualBV.text",
                "BasicInfoView.txtManualBV.tooltip", labelSize), gbc);
        gbc.gridx = 1;
        setFieldSize(txtManualBV, controlSize);
        txtManualBV.setToolTipText(resourceMap.getString("BasicInfoView.txtManualBV.tooltip"));
        add(txtManualBV, gbc);
        txtManualBV.addFocusListener(this);

        txtMulId.setMinimum(-1);
        lblFaction.setVisible(CConfig.getBooleanParam(CConfig.TECH_SHOW_FACTION));
        cbFaction.setVisible(CConfig.getBooleanParam(CConfig.TECH_SHOW_FACTION));
    }

    public void setFromEntity(Entity en) {
        boolean useTP = CConfig.getBooleanParam(CConfig.TECH_PROGRESSION);
        baseTA = en.getConstructionTechAdvancement();
        txtYear.setMinimum(Math.max(baseTA.getIntroductionDate(), ITechnology.DATE_PS));
        refreshTechBase();
        setChassis(en.getChassis());
        setModel(en.getModel());
        txtMulId.setText(en.getMulId() + "");
        browseMul.setVisible(en.hasMulId());
        setYear(Math.max(en.getYear(), txtYear.getMinimum()));
        setSource(en.getSource());
        cbTechBase.removeActionListener(this);
        setTechBase(en.isClan(), en.isMixedTech());
        cbTechBase.addActionListener(this);
        cbTechLevel.removeActionListener(this);
        SimpleTechLevel lvl = useTP? en.getSimpleLevel(getGameYear()) : en.getStaticTechLevel();
        setTechLevel(SimpleTechLevel.max(lvl,
                SimpleTechLevel.convertCompoundToSimple(en.getTechLevel())));
        cbTechLevel.addActionListener(this);
        if (en.getManualBV() >= 0) {
            setManualBV(en.getManualBV());
        }
        
        refreshFaction();
    }

    public void setAsCustomization() {
        txtChassis.setEnabled(false);
        txtYear.setEnabled(false);
    }

    public void addListener(BuildListener l) {
        if (null != l) {
            listeners.add(l);
        }
    }

    public void removeListener(BuildListener l) {
        listeners.remove(l);
    }

    public String getChassis() {
        return txtChassis.getText();
    }

    public void setChassis(String chassis) {
        txtChassis.setText(chassis);
    }

    public String getModel() {
        return txtModel.getText();
    }

    public void setModel(String model) {
        txtModel.setText(model);
    }

    @Override
    public int getTechIntroYear() {
        return txtYear.getIntVal();
    }

    public void setYear(int year) {
        txtYear.setIntVal(year);
        refreshTechBase();
    }

    @Override
    public int getTechFaction() {
        if (!CConfig.getBooleanParam(CConfig.TECH_SHOW_FACTION)) {
            return ITechnology.F_NONE;
        }
        Integer retVal = (Integer) cbFaction.getSelectedItem();
        return (retVal == null) ? ITechnology.F_NONE : retVal;
    }

    public void setTechFaction(int techFaction) {
        cbFaction.setSelectedItem(techFaction);
    }

    @Override
    public int getGameYear() {
        if (CConfig.getBooleanParam(CConfig.TECH_USE_YEAR)) {
            return Math.max(getTechIntroYear(), CConfig.getIntParam(CConfig.TECH_YEAR));
        }
        return getTechIntroYear();
    }

    public String getSource() {
        return txtSource.getText();
    }

    public void setSource(String source) {
        txtSource.setText(source);
    }

    /**
     * @return the entered manual BV value or -1 if it can't be parsed.
     */
    public int getManualBV() {
        return txtManualBV.getIntVal(-1);
    }

    public void setManualBV(int bv) {
        if (bv >= 0) {
            txtManualBV.setIntVal(bv);
        } else {
            txtManualBV.setText("");
        }
    }

    @Override
    public boolean useClanTechBase() {
        if (getTechIntroYear() < CLAN_START) {
            return false;
        } else {
            Integer selected = (Integer) cbTechBase.getSelectedItem();
            return ((null != selected)
                    && ((selected == BASE_CLAN) || (selected == BASE_CLAN_MIXED)));
        }
    }
    
    @Override
    public boolean useMixedTech() {
        if (getTechIntroYear() < CLAN_START) {
            return false;
        }
        Integer selected = (Integer) cbTechBase.getSelectedItem();
        return ((null != selected)
                && ((selected == BASE_IS_MIXED) || (selected == BASE_CLAN_MIXED)));
    }

    public void setTechBase(boolean clan, boolean mixed) {
        int item = 0;
        if (clan && (getTechIntroYear() > CLAN_START)) {
            item++;
        }

        if (mixed) {
            item += 2;
        }
        cbTechBase.setSelectedItem(item);
        refreshFaction();
    }

    @Override
    public SimpleTechLevel getTechLevel() {
        return (cbTechLevel.getSelectedItem() == null) ? SimpleTechLevel.STANDARD
                : (SimpleTechLevel) cbTechLevel.getSelectedItem();
    }

    public void setTechLevel(SimpleTechLevel level) {
        cbTechLevel.setSelectedItem(level);
    }

    private void refreshTechBase() {
        Integer prev = (Integer) cbTechBase.getSelectedItem();
        cbTechBase.removeActionListener(this);
        cbTechBase.removeAllItems();
        // IS is available to anything that doesn't require a Clan tech base (e.g. QuadVee, ProtoMech).
        // Clan is available to anything that doesn't require an IS tech base, is built after the Clans
        // are formed, and not built by an IS faction before the Clan invasion.
        final boolean clanFaction = (getTechFaction() >= ITechnology.F_CLAN) || (getTechFaction() < 0);
        final boolean sphereAvailable = baseTA.getTechBase() != ITechnology.TECH_BASE_CLAN;
        final boolean clanAvailable = (getTechIntroYear() >= CLAN_START)
                && (baseTA.getTechBase() != ITechnology.TECH_BASE_IS)
                && (clanFaction || (getTechIntroYear() >= IS_MIXED_START));
        final boolean mixedTechAvailable = (getTechIntroYear() >= IS_MIXED_START)
                || ((getTechIntroYear() >= CLAN_MIXED_START) && clanFaction);
        if (sphereAvailable) {
            cbTechBase.addItem(BASE_IS);
        }

        if (clanAvailable) {
            cbTechBase.addItem(BASE_CLAN);
        }

        if (sphereAvailable && mixedTechAvailable) {
            cbTechBase.addItem(BASE_IS_MIXED);
        }

        if (clanAvailable && mixedTechAvailable) {
            cbTechBase.addItem(BASE_CLAN_MIXED);
        }

        if (prev != null) {
            cbTechBase.setSelectedItem(prev);
        }
        cbTechBase.addActionListener(this);
        if ((cbTechBase.getSelectedItem() == null) || !cbTechBase.getSelectedItem().equals(prev)) {
            cbTechBase.setSelectedItem(0);
        }
        refreshTechLevel();
        refreshFaction();
    }
    
    private void refreshTechLevel() {
        SimpleTechLevel prev = getTechLevel();
        cbTechLevel.removeActionListener(this);
        cbTechLevel.removeAllItems();
        SimpleTechLevel baseLevel;
        if (CConfig.getBooleanParam(CConfig.TECH_PROGRESSION)) {
            baseLevel = baseTA.getSimpleLevel(getGameYear());
            if (useMixedTech()
                    && (baseLevel.ordinal() < Entity.getMixedTechAdvancement().getSimpleLevel(getGameYear()).ordinal())) {
                baseLevel = Entity.getMixedTechAdvancement().getSimpleLevel(getGameYear());
            }
        } else {
            baseLevel = baseTA.getStaticTechLevel();
            if (useMixedTech()
                    && (baseLevel.ordinal() < Entity.getMixedTechAdvancement().getStaticTechLevel().ordinal())) {
                baseLevel = Entity.getMixedTechAdvancement().getStaticTechLevel();
            }
        }

        if (!useClanTechBase() && (SimpleTechLevel.INTRO.ordinal() >= baseLevel.ordinal())) {
            cbTechLevel.addItem(SimpleTechLevel.INTRO);
        }

        if (SimpleTechLevel.STANDARD.ordinal() >= baseLevel.ordinal()) {
            cbTechLevel.addItem(SimpleTechLevel.STANDARD);
        }

        if (SimpleTechLevel.ADVANCED.ordinal() >= baseLevel.ordinal()) {
            cbTechLevel.addItem(SimpleTechLevel.ADVANCED);
        }
        cbTechLevel.addItem(SimpleTechLevel.EXPERIMENTAL);
        cbTechLevel.addItem(SimpleTechLevel.UNOFFICIAL);
        cbTechLevel.setSelectedItem(prev);
        cbTechLevel.addActionListener(this);
        if ((cbTechLevel.getSelectedItem() == null) || (cbTechLevel.getSelectedItem() != prev)) {
            cbTechLevel.setSelectedIndex(0);
        }
    }

    private void refreshFaction() {
        if (CConfig.getBooleanParam(CConfig.TECH_SHOW_FACTION)) {
            cbFaction.removeActionListener(this);
            Integer prevFaction = (Integer) cbFaction.getSelectedItem();
            cbFaction.refresh(getTechIntroYear());
            cbFaction.setSelectedItem(prevFaction);
            cbFaction.addActionListener(this);
            if (cbFaction.getSelectedIndex() < 0) {
                cbFaction.setSelectedIndex(0);
            }
            lblFaction.setVisible(true);
            cbFaction.setVisible(true);
        } else {
            lblFaction.setVisible(false);
            cbFaction.setVisible(false);
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (e.getSource().equals(txtYear)) {
            prevYear = getTechIntroYear();
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (e.getSource() == txtChassis) {
            listeners.forEach(l -> l.chassisChanged(getChassis()));
        } else if (e.getSource() == txtModel) {
            listeners.forEach(l -> l.modelChanged(getModel()));
        } else if (e.getSource() == txtMulId) {
            if (txtMulId.getIntVal() < 1) {
                txtMulId.setText("-1");
            }
            browseMul.setVisible(shouldShowMULButton());
            listeners.forEach(l -> l.mulIdChanged(txtMulId.getIntVal(-1)));
        } else if (e.getSource() == txtYear) {
            try {
                int year = Math.max(getTechIntroYear(), baseTA.getIntroductionDate(useClanTechBase()));
                listeners.forEach(l -> l.yearChanged(year));
                prevYear = year;
            } catch (Exception ex) {
                // If text is not a legal integer value, reset to the previous value
                LogManager.getLogger().error("", ex);
            } finally {
                setYear(prevYear);
            }
        } else if (e.getSource() == txtSource) {
            listeners.forEach(l -> l.sourceChanged(getSource()));
        } else if (e.getSource() == txtManualBV) {
            int manualBv = getManualBV();
            txtManualBV.setText((manualBv > 0) ? String.valueOf(manualBv) : "");
            listeners.forEach(l -> l.manualBVChanged(manualBv));
        }
        listeners.forEach(BuildListener::refreshSummary);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cbFaction) {
            listeners.forEach(BuildListener::updateTechLevel);
            refreshTechBase();
        } else if (e.getSource() == cbTechBase) {
            listeners.forEach(l -> l.techBaseChanged(useClanTechBase(), useMixedTech()));
            refreshTechLevel();
        } else if (e.getSource() == cbTechLevel) {
            listeners.forEach(l -> l.techLevelChanged(getTechLevel()));
        }
        listeners.forEach(BuildListener::refreshSummary);
    }

    @Override
    public boolean unofficialNoYear() {
        return CConfig.getBooleanParam(CConfig.TECH_UNOFFICAL_NO_YEAR);
    }

    @Override
    public boolean useVariableTechLevel() {
        return CConfig.getBooleanParam(CConfig.TECH_PROGRESSION);
    }

    @Override
    public boolean showExtinct() {
        return CConfig.getBooleanParam(CConfig.TECH_EXTINCT);
    }

    /**
     * The MUL button should be shown when the current MUL ID field has a valid MUL (> 0) and the
     * system seems to support calling a standard browser.
     * @return true when the "Open MUL in Browser" Button can be used
     */
    private boolean shouldShowMULButton() {
        return (txtMulId.getIntVal(-1) > 0) && Desktop.isDesktopSupported()
                && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE);
    }

    /**
     * Opens the Master Unit List in the System Standard Explorer, if possible.
     */
    private void openMUL() {
        try {
            if (shouldShowMULButton()) {
                Desktop.getDesktop().browse(URI.create(MMConstants.MUL_URL_PREFIX + txtMulId.getIntVal()));
            }
        } catch (Exception ex) {
            LogManager.getLogger().error("", ex);
            JOptionPane.showMessageDialog(this, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
}
