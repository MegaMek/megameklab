/*
 * Copyright (C) 2017-2025 The MegaMek Team. All Rights Reserved.
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

import java.awt.Component;
import java.awt.Desktop;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.*;

import megamek.MMConstants;
import megamek.client.ui.baseComponents.BooksIcon;
import megamek.client.ui.baseComponents.DeleteIcon;
import megamek.client.ui.baseComponents.MulLinkIcon;
import megamek.client.ui.comboBoxes.MMComboBox;
import megamek.client.ui.dialogs.SourceChooserDialog;
import megamek.client.ui.util.DisplayTextField;
import megamek.common.SimpleTechLevel;
import megamek.common.SourceBook;
import megamek.common.SourceBooks;
import megamek.common.TechAdvancement;
import megamek.common.enums.Faction;
import megamek.common.enums.FactionAffiliation;
import megamek.common.enums.TechBase;
import megamek.common.interfaces.ITechManager;
import megamek.common.interfaces.ITechnology;
import megamek.common.units.Entity;
import megamek.common.units.Mek;
import megamek.common.units.UnitRole;
import megamek.logging.MMLogger;
import megameklab.ui.listeners.BuildListener;
import megameklab.ui.util.CustomComboBox;
import megameklab.ui.util.FactionComboBox;
import megameklab.ui.util.IntRangeTextField;
import megameklab.ui.util.WidthControlComponent;
import megameklab.util.CConfig;

/**
 * A panel for basic information common to all unit types: name, year, tech level and others.
 *
 * @author Neoancient
 */
public class BasicInfoView extends BuildView implements ITechManager, ActionListener, FocusListener {
    private static final MMLogger LOGGER = MMLogger.create(BasicInfoView.class);

    // region Variable Declarations
    private static final TechAdvancement TA_MIXED_TECH = Entity.getMixedTechAdvancement();
    private static final int CLAN_START = 2807;
    private static final int IS_MIXED_START = TA_MIXED_TECH.getIntroductionDate(false);
    private static final int CLAN_MIXED_START = TA_MIXED_TECH.getIntroductionDate(true);

    private static final int BASE_IS = 0;
    private static final int BASE_CLAN = 1;
    private static final int BASE_IS_MIXED = 2;
    private static final int BASE_CLAN_MIXED = 3;

    private static final String SOURCE_TOOLTIP_TEMPLATE =
          "<html>%s<hr>Product Code: %s<br>Saved to file as: %s</div></html>";

    private String[] techBaseNames;
    private TechAdvancement baseTA;

    private final ResourceBundle resourceMap = ResourceBundle.getBundle("megameklab.resources.Views");
    private final JTextField txtChassis = new JTextField(WidthControlComponent.TEXT_FIELD_COLUMNS);
    private final JTextField txtClanName = new JTextField();
    private final JLabel lblClanName = createLabel(resourceMap, "lblClanName", "BasicInfoView.txtClanName.text",
          "BasicInfoView.txtClanName.tooltip");
    private final JTextField txtModel = new JTextField();
    private final IntRangeTextField txtYear = new IntRangeTextField();
    private final FactionComboBox cbFaction = new FactionComboBox();
    private final JLabel lblFaction = createLabel("lblFaction", "");
    private final DisplayTextField txtSource = new DisplayTextField(15);
    private final JButton sourceMulLinkButton = new JButton(new MulLinkIcon());
    private final CustomComboBox<Integer> cbTechBase = new CustomComboBox<>(i -> String.valueOf(techBaseNames[i]));
    private final JComboBox<String> cbTechLevel = new JComboBox<>();
    private final IntRangeTextField txtManualBV = new IntRangeTextField(3);
    private final MMComboBox<UnitRole> cbRole = new MMComboBox<>("Role Combo");
    private final JLabel lblMulId = createLabel("lblMulId", "");
    private final IntRangeTextField txtMulId = new IntRangeTextField(8);
    private final JButton browseMul = new JButton(new MulLinkIcon());

    private final List<BuildListener> listeners = new CopyOnWriteArrayList<>();
    private final SourceBooks sourceBooks = new SourceBooks();

    private int prevYear = 3145;
    private String sourceAbbreviation;
    // endregion Variable Declarations

    // region Constructors
    public BasicInfoView(TechAdvancement baseTA) {
        this.baseTA = baseTA;
        initUI();
    }
    // endregion Constructors

    private void initUI() {
        techBaseNames = resourceMap.getString("BasicInfoView.cbTechBase.values").split(",");

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = STANDARD_INSETS;
        gbc.weightx = 1;
        add(createLabel(resourceMap, "lblChassis", "BasicInfoView.txtChassis.text",
              "BasicInfoView.txtChassis.tooltip"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 0;
        txtChassis.setToolTipText(resourceMap.getString("BasicInfoView.txtChassis.tooltip"));
        add(txtChassis, gbc);
        txtChassis.addFocusListener(this);

        gbc.gridx = 0;
        gbc.gridy++;
        add(lblClanName, gbc);
        gbc.gridx = 1;
        txtClanName.setToolTipText(resourceMap.getString("BasicInfoView.txtModel.tooltip"));
        add(txtClanName, gbc);
        txtClanName.addFocusListener(this);

        gbc.gridx = 0;
        gbc.gridy++;
        add(createLabel(resourceMap, "lblModel", "BasicInfoView.txtModel.text",
              "BasicInfoView.txtModel.tooltip"), gbc);
        gbc.gridx = 1;
        txtModel.setToolTipText(resourceMap.getString("BasicInfoView.txtModel.tooltip"));
        add(txtModel, gbc);
        txtModel.addFocusListener(this);

        gbc.gridx = 0;
        gbc.gridy++;
        lblMulId.setText(resourceMap.getString("BasicInfoView.txtMulId.text"));
        add(lblMulId, gbc);
        gbc.gridx = 1;
        txtMulId.setToolTipText(resourceMap.getString("BasicInfoView.txtMulId.tooltip"));
        txtMulId.addFocusListener(this);
        browseMul.setToolTipText(resourceMap.getString("BasicInfoView.browseMul.tooltip"));
        browseMul.addActionListener(e -> openMUL());
        var mulPanel = Box.createHorizontalBox();
        mulPanel.add(txtMulId);
        mulPanel.add(browseMul);
        add(mulPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(createLabel(resourceMap, "lblYear", "BasicInfoView.txtYear.text",
              "BasicInfoView.txtYear.tooltip"), gbc);
        gbc.gridx = 1;
        add(txtYear, gbc);
        txtYear.setToolTipText(resourceMap.getString("BasicInfoView.txtYear.tooltip"));
        txtYear.setMaximum(9999);
        txtYear.addFocusListener(this);

        gbc.gridx = 0;
        gbc.gridy++;
        lblFaction.setText(resourceMap.getString("BasicInfoView.cbFaction.text"));
        add(lblFaction, gbc);
        gbc.gridx = 1;
        cbFaction.setToolTipText(resourceMap.getString("BasicInfoView.cbFaction.tooltip"));
        add(cbFaction, gbc);
        cbFaction.addActionListener(this);
        cbFaction.setPrototypeDisplayValue(Faction.NONE);

        gbc.gridx = 0;
        gbc.gridy++;
        add(createLabel(resourceMap, "lblSource", "BasicInfoView.txtSource.text",
              "BasicInfoView.txtSource.tooltip"), gbc);
        gbc.gridx = 1;
        var sourcePanel = Box.createHorizontalBox();
        sourcePanel.add(txtSource);
        var clearSourceButton = new JButton(new DeleteIcon());
        clearSourceButton.setToolTipText(resourceMap.getString("BasicInfoView.deleteSource.tooltip"));
        clearSourceButton.addActionListener(e -> setSource(""));
        sourcePanel.add(clearSourceButton);
        var editSourceButton = new JButton(new BooksIcon());
        sourcePanel.add(editSourceButton);
        editSourceButton.setToolTipText(resourceMap.getString("BasicInfoView.configSource.tooltip"));
        sourceMulLinkButton.setToolTipText(resourceMap.getString("BasicInfoView.browseSourcebook.tooltip"));
        sourceMulLinkButton.addActionListener(e -> openSourcebookMUL());
        sourcePanel.add(sourceMulLinkButton);
        add(sourcePanel, gbc);
        txtSource.setEditable(false);
        txtSource.setToolTipText(resourceMap.getString("BasicInfoView.txtSource.tooltip"));
        editSourceButton.addActionListener(e -> {
            String result = SourceChooserDialog.showChoiceDialog(getRootPane(), true);
            if (result != null) {
                setSource(result);
            }
        });

        gbc.gridx = 0;
        gbc.gridy++;
        add(createLabel(resourceMap, "lblTechBase", "BasicInfoView.cbTechBase.text",
              "BasicInfoView.cbTechBase.tooltip"), gbc);
        gbc.gridx = 1;
        cbTechBase.setToolTipText(resourceMap.getString("BasicInfoView.cbTechBase.tooltip"));
        add(cbTechBase, gbc);
        cbTechBase.addActionListener(this);
        cbTechBase.setPrototypeDisplayValue(0);

        gbc.gridx = 0;
        gbc.gridy++;
        add(createLabel(resourceMap, "lblTechLevel", "BasicInfoView.cbTechLevel.text",
              "BasicInfoView.cbTechLevel.tooltip"), gbc);
        gbc.gridx = 1;
        cbTechLevel.setToolTipText(resourceMap.getString("BasicInfoView.cbTechLevel.tooltip"));
        add(cbTechLevel, gbc);
        cbTechLevel.addActionListener(this);
        cbTechLevel.setPrototypeDisplayValue(CB_SIZE_VALUE);
        refreshTechBase();

        gbc.gridx = 0;
        gbc.gridy++;
        add(createLabel(resourceMap, "lblManualBV", "BasicInfoView.txtManualBV.text",
              "BasicInfoView.txtManualBV.tooltip"), gbc);
        gbc.gridx = 1;
        txtManualBV.setToolTipText(resourceMap.getString("BasicInfoView.txtManualBV.tooltip"));
        add(txtManualBV, gbc);
        txtManualBV.addFocusListener(this);

        gbc.gridx = 0;
        gbc.gridy++;
        add(createLabel(resourceMap, "lblRole", "BasicInfoView.txtRole.text",
              "BasicInfoView.txtRole.tooltip"), gbc);
        gbc.gridx = 1;
        cbRole.setToolTipText(resourceMap.getString("BasicInfoView.txtRole.tooltip"));
        add(cbRole, gbc);
        cbRole.addActionListener(this);
        cbRole.setPrototypeDisplayValue(UnitRole.ATTACK_FIGHTER);
        // Show the role UNDETERMINED as an empty selection to differentiate it from NONE
        // UNDETERMINED means that no role at all will be saved to the unit
        cbRole.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
                  boolean cellHasFocus) {
                if (value == UnitRole.UNDETERMINED) {
                    value = " ";
                }
                return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            }
        });

        txtMulId.setMinimum(-1);
        lblFaction.setVisible(CConfig.getBooleanParam(CConfig.TECH_SHOW_FACTION));
        cbFaction.setVisible(CConfig.getBooleanParam(CConfig.TECH_SHOW_FACTION));
    }

    public void setFromEntity(Entity en) {
        boolean useTP = CConfig.getBooleanParam(CConfig.TECH_PROGRESSION);
        baseTA = en.getConstructionTechAdvancement();
        txtYear.setMinimum(Math.max(baseTA.getIntroductionDate(useClanTechBase()), ITechnology.DATE_PS));
        refreshTechBase();
        setChassis(en.getChassis());
        txtClanName.setText(en.getClanChassisName());
        txtClanName.setVisible(en instanceof Mek);
        lblClanName.setVisible(en instanceof Mek);
        setModel(en.getModel());
        txtMulId.setText(en.getMulId() + "");
        browseMul.setEnabled(en.hasMulId());
        setYear(Math.max(en.getYear(), txtYear.getMinimum()));
        setSource(en.getSource());
        cbTechBase.removeActionListener(this);
        setTechBase(en.isClan(), en.isMixedTech());
        cbTechBase.addActionListener(this);
        cbTechLevel.removeActionListener(this);
        SimpleTechLevel lvl = useTP ? en.getSimpleLevel(getGameYear()) : en.getStaticTechLevel();
        setTechLevel(SimpleTechLevel.max(lvl,
              SimpleTechLevel.convertCompoundToSimple(en.getTechLevel())));
        cbTechLevel.addActionListener(this);
        setManualBV(en.getManualBV());
        cbRole.removeActionListener(this);
        cbRole.removeAllItems();
        for (UnitRole role : UnitRole.values()) {
            if (role.isAvailableTo(en)) {
                cbRole.addItem(role);
            }
        }
        cbRole.setSelectedItem(en.getRole());
        cbRole.addActionListener(this);

        refreshFaction();
    }

    public void setAsCustomization() {
        txtChassis.setEnabled(false);
        txtClanName.setEnabled(false);
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
    public Faction getTechFaction() {
        if (!CConfig.getBooleanParam(CConfig.TECH_SHOW_FACTION)) {
            return Faction.NONE;
        }
        Faction selectedFaction = (Faction) cbFaction.getSelectedItem();
        return (selectedFaction == null) ? Faction.NONE : selectedFaction;
    }

    public void setTechFaction(Faction techFaction) {
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
        sourceAbbreviation = source;
        // show the title of the book if available, otherwise show the given String
        Optional<SourceBook> book = sourceBooks.loadSourceBook(source);
        if (book.isPresent()) {
            String tooltip = SOURCE_TOOLTIP_TEMPLATE
                  .formatted(book.get().getTitle(), book.get().getSku(), book.get().getAbbrev());
            txtSource.setToolTipText(tooltip);
            txtSource.setText(book.get().getTitle());
        } else {
            txtSource.setText(source);
            txtSource.setToolTipText(resourceMap.getString("BasicInfoView.txtSource.tooltip"));
        }
        sourceMulLinkButton.setEnabled(sourceBooks.loadSourceBook(sourceAbbreviation).isPresent());
        listeners.forEach(l -> l.sourceChanged(sourceAbbreviation));
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
        try {
            return SimpleTechLevel.parse((String) cbTechLevel.getSelectedItem());
        } catch (Exception e) {
            return SimpleTechLevel.STANDARD;
        }
    }

    public void setTechLevel(SimpleTechLevel level) {
        cbTechLevel.setSelectedItem(level.toString());
    }

    private void refreshTechBase() {
        Integer prev = (Integer) cbTechBase.getSelectedItem();
        cbTechBase.removeActionListener(this);
        cbTechBase.removeAllItems();
        // IS is available to anything that doesn't require a Clan tech base (e.g.
        // QuadVee, ProtoMek).
        // Clan is available to anything that doesn't require an IS tech base, is built
        // after the Clans
        // are formed, and not built by an IS faction before the Clan invasion.
        final boolean clanFaction = getTechFaction().getAffiliation().equals(FactionAffiliation.CLAN)
              || (getTechFaction() == Faction.NONE);
        final boolean sphereAvailable = baseTA.getTechBase() != TechBase.CLAN;
        final boolean clanAvailable = (getTechIntroYear() >= CLAN_START)
              && (baseTA.getTechBase() != TechBase.IS)
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
                  && (baseLevel.ordinal() < Entity.getMixedTechAdvancement().getSimpleLevel(getGameYear())
                  .ordinal())) {
                baseLevel = Entity.getMixedTechAdvancement().getSimpleLevel(getGameYear());
            }
        } else {
            baseLevel = baseTA.getStaticTechLevel();
            if (useMixedTech()
                  && (baseLevel.ordinal() < Entity.getMixedTechAdvancement().getStaticTechLevel().ordinal())) {
                baseLevel = Entity.getMixedTechAdvancement().getStaticTechLevel();
            }
        }

        if (!useClanTechBase() && (SimpleTechLevel.INTRO.ordinal() == baseLevel.ordinal())) {
            cbTechLevel.addItem(SimpleTechLevel.INTRO.toString());
        }

        if (SimpleTechLevel.STANDARD.ordinal() >= baseLevel.ordinal()) {
            cbTechLevel.addItem(SimpleTechLevel.STANDARD.toString());
        }

        if (SimpleTechLevel.ADVANCED.ordinal() >= baseLevel.ordinal()) {
            cbTechLevel.addItem(SimpleTechLevel.ADVANCED.toString());
        }
        cbTechLevel.addItem(SimpleTechLevel.EXPERIMENTAL.toString());
        cbTechLevel.addItem(SimpleTechLevel.UNOFFICIAL.toString());
        cbTechLevel.setSelectedItem(prev.toString());
        cbTechLevel.addActionListener(this);
        if ((cbTechLevel.getSelectedItem() == null) || (!cbTechLevel.getSelectedItem().equals(prev.toString()))) {
            cbTechLevel.setSelectedIndex(0);
        }
    }

    private void refreshFaction() {
        if (CConfig.getBooleanParam(CConfig.TECH_SHOW_FACTION)) {
            cbFaction.removeActionListener(this);
            Faction prevFaction = (Faction) cbFaction.getSelectedItem();
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
        } else if (e.getSource() == txtClanName) {
            listeners.forEach(l -> l.clanNameChanged(txtClanName.getText()));
        } else if (e.getSource() == txtModel) {
            listeners.forEach(l -> l.modelChanged(getModel()));
        } else if (e.getSource() == txtMulId) {
            if (txtMulId.getIntVal() < 1) {
                txtMulId.setText("-1");
            }
            browseMul.setEnabled(shouldShowMULButton());
            listeners.forEach(l -> l.mulIdChanged(txtMulId.getIntVal(-1)));
        } else if (e.getSource() == txtYear) {
            try {
                int year = Math.max(getTechIntroYear(), baseTA.getIntroductionDate(useClanTechBase()));
                listeners.forEach(l -> l.yearChanged(year));
                prevYear = year;
            } catch (Exception ex) {
                // If text is not a legal integer value, reset to the previous value
                LOGGER.error("", ex);
            } finally {
                setYear(prevYear);
            }
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
        } else if (e.getSource() == cbRole) {
            UnitRole newRole = (cbRole.getSelectedItem() == null) ? UnitRole.UNDETERMINED : cbRole.getSelectedItem();
            listeners.forEach(l -> l.roleChanged(newRole));
        }
        listeners.forEach(BuildListener::refreshSummary);
    }

    @Override
    public boolean unofficialNoYear() {
        return CConfig.getBooleanParam(CConfig.TECH_UNOFFICIAL_NO_YEAR);
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
     * The MUL button should be shown when the current MUL ID field has a valid MUL (> 0) and the system seems to
     * support calling a standard browser.
     *
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
            LOGGER.error("", ex);
            JOptionPane.showMessageDialog(this, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Opens the Master Unit List sourcebook page in the System Standard Explorer, if possible.
     */
    private void openSourcebookMUL() {
        sourceBooks.loadSourceBook(sourceAbbreviation).ifPresent(sourceBook -> {
            try {
                Desktop.getDesktop().browse(URI.create(sourceBook.getMul_url()));
            } catch (IOException ex) {
                LOGGER.error("", ex);
                JOptionPane.showMessageDialog(this, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
