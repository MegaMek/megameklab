/*
 * Copyright (C) 2026 The MegaMek Team. All Rights Reserved.
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
 * MechWarrior Copyright Microsoft Corporation. MegaMekLab was created under
 * Microsoft's "Game Content Usage Rules"
 * <https://www.xbox.com/en-US/developers/rules> and it is not endorsed by or
 * affiliated with Microsoft.
 */
package megameklab.ui.battlefieldSupport;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Function;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import megamek.client.ui.dialogs.UnitLoadingDialog;
import megamek.client.ui.dialogs.iconChooser.EntityImagePanel;
import megamek.client.ui.panels.battlefieldSupport.ConfigurableBFSCardPanel;
import megamek.client.ui.tileset.MMStaticDirectoryManager;
import megamek.client.ui.util.FluffImageHelper;
import megamek.client.ui.util.PlayerColour;
import megamek.common.battlefieldSupport.BFSArtilleryType;
import megamek.common.battlefieldSupport.BFSAssetType;
import megamek.common.battlefieldSupport.BFSDamage;
import megamek.common.battlefieldSupport.BFSRange;
import megamek.common.battlefieldSupport.BFSSpecial;
import megamek.common.battlefieldSupport.BFSSpecialType;
import megamek.common.battlefieldSupport.BattlefieldSupportAsset;
import megamek.common.battlefieldSupport.BattlefieldSupportAssetData;
import megamek.common.icons.Camouflage;
import megamek.common.units.Entity;
import megamek.common.units.EntityMovementMode;
import megamek.common.units.UnitRole;
import megamek.common.util.ImageUtil;
import megamek.logging.MMLogger;
import megameklab.ui.EntitySource;
import megameklab.ui.PopupMessages;
import megameklab.ui.dialog.MMLFileChooser;
import megameklab.ui.dialog.MegaMekLabUnitSelectorDialog;
import megameklab.ui.generalUnit.SourceView;
import megameklab.ui.util.ITab;
import megameklab.ui.util.IntRangeTextField;
import megameklab.ui.util.PreviewCamouflage;
import megameklab.ui.util.RefreshListener;

/**
 * The single construction tab for a Battlefield Support Asset, laid out in three columns: the left column holds the
 * basic unit info and stats, the middle column is the Specials editor, and the right column shows a live card preview
 * with fluff-art and sprite-icon pickers. Assets have no construction rules, so the fields are bound directly to the
 * {@link BattlefieldSupportAsset} with no validation.
 */
public class BFSStructureTab extends ITab {

    private static final MMLogger logger = MMLogger.create(BFSStructureTab.class);
    private static final ResourceBundle I18N = ResourceBundle.getBundle("megameklab.resources.Views");

    private static final String[] TECH_BASES = {
          BattlefieldSupportAssetData.TECH_BASE_IS,
          BattlefieldSupportAssetData.TECH_BASE_CLAN,
          BattlefieldSupportAssetData.TECH_BASE_MIXED_IS,
          BattlefieldSupportAssetData.TECH_BASE_MIXED_CLAN
    };

    private static final BFSAssetType[] ASSET_TYPES = BFSAssetType.values();

    private static final Camouflage CAMO_MEKSET = PreviewCamouflage.of(PlayerColour.GRAY);
    private static final Camouflage CAMO_EMBEDDED = PreviewCamouflage.of(PlayerColour.GOLD);

    private RefreshListener refresh;
    private boolean loading;

    /** When true this tab edits a linked base unit's asset carrier (via {@link #assetSource}) instead of the sole
     * standalone entity, and hides the base-derived identity/type/mode controls. Its visibility (as a whole tab) is
     * managed by the base editor via the enable checkbox in the Structure tab, so no in-tab enable control is needed. */
    private final boolean linked;
    private final BFSAssetSource assetSource;

    // Left column - identity + basic info + stats
    private final JTextField txtChassis = new JTextField(14);
    private final JTextField txtModel = new JTextField(14);
    private final JTextField txtCardTitle = new JTextField(14);
    private final JTextField txtCardSubtitle = new JTextField(14);
    private final IntRangeTextField txtYear = new IntRangeTextField(6);
    private final JComboBox<String> cbTechBase = new JComboBox<>(TECH_BASES);
    private final SourceView sourceView = new SourceView(I18N.getString("BFSStructureTab.source.tooltip"));
    private final JComboBox<BFSAssetType> cbAssetType = new JComboBox<>(ASSET_TYPES);
    private final JComboBox<UnitRole> cbRole = new JComboBox<>(UnitRole.values());
    private final JComboBox<EntityMovementMode> cbMovementMode = new JComboBox<>(
          motiveOptionsFor(BFSAssetType.VEHICLE));
    private final JSpinner spnMp = intSpinner(0, 0, 30);
    private final JSpinner spnTmm = intSpinner(0, -10, 20);
    private final JSpinner spnShort = intSpinner(0, 0, 200);
    private final JSpinner spnMedium = intSpinner(0, 0, 200);
    private final JSpinner spnLong = intSpinner(0, 0, 200);
    private final JSpinner spnSkill = intSpinner(6, 0, 12);
    private final JCheckBox chkVeteran = new JCheckBox(I18N.getString("BFSStructureTab.veteran.text"));
    private final JSpinner spnVeteranSkill = intSpinner(5, 0, 12);
    private final JSpinner spnPerHit = intSpinner(0, 0, 200);
    private final JSpinner spnHits = intSpinner(0, 0, 50);
    private final JSpinner spnDestroyCheck = intSpinner(7, 0, 20);
    private final JSpinner spnThreshold = intSpinner(0, 0, 30);
    private final JSpinner spnCost = intSpinner(0, 0, 5000);
    // Max exceeds the regular-cost max so the enforced "veteran cost > regular cost" minimum is always attainable.
    private final JSpinner spnVeteranCost = intSpinner(0, 0, 5001);
    private final JLabel lblDerivedBv = new JLabel();

    // Middle column - specials editor
    private final DefaultListModel<BFSSpecial> specialsModel = new DefaultListModel<>();
    private final JList<BFSSpecial> specialsList = new JList<>(specialsModel);
    private final JComboBox<BFSSpecialType> cbKnownSpecial = new JComboBox<>(BFSSpecialType.values());
    private final JComboBox<BFSArtilleryType> cbArtilleryType = new JComboBox<>(BFSArtilleryType.values());
    private final JPanel specialValueCards = new JPanel(new CardLayout());
    private final JSpinner spnSpecialValue = intSpinner(1, 1, 999);
    private final JTextField txtFreeSpecial = new JTextField(10);

    // Right column - preview + art
    private final ConfigurableBFSCardPanel previewPanel = new ConfigurableBFSCardPanel(true, null);
    private final EntityImagePanel spritePreview = new EntityImagePanel(null, null);

    /** Constructs a standalone asset tab that edits the editor's sole {@code .bfs} entity. */
    public BFSStructureTab(EntitySource eSource) {
        this(eSource, null);
    }

    /**
     * Constructs an asset tab. When {@code assetSource} is non-null the tab operates in <em>linked</em> mode: it edits
     * that source's asset carrier (belonging to a base unit) and hides the base-derived identity/type/mode controls.
     * The base unit's Structure tab controls whether the linked Asset tab is enabled. When {@code assetSource} is
     * {@code null} this tab operates in standalone mode, editing the editor's sole entity as the asset.
     *
     * @param eSource     the entity source (the editor); in linked mode {@code getEntity()} is the base unit
     * @param assetSource the linked-asset source, or {@code null} for a standalone asset editor
     */
    public BFSStructureTab(EntitySource eSource, BFSAssetSource assetSource) {
        super(eSource);
        this.assetSource = assetSource;
        this.linked = (assetSource != null);
        configureRenderers();
        configureYearField();
        setUpLayout();
        addListeners();
        refresh();
    }

    /** Bounds the intro-year field: minimum is the asset's construction-tech introduction date, up to 9999. */
    private void configureYearField() {
        int minYear = 1950;
        BattlefieldSupportAsset asset = getAsset();
        if (asset != null) {
            minYear = asset.getConstructionTechAdvancement().getIntroductionDate(asset.isClan());
        }
        txtYear.setMinimum(minYear);
        txtYear.setMaximum(9999);
    }

    /** Uses the friendly display names of the enum-backed pickers instead of their raw {@code toString()}. */
    private void configureRenderers() {
        cbAssetType.setRenderer(friendlyRenderer(v -> assetTypeLabel((BFSAssetType) v)));
        cbKnownSpecial.setRenderer(friendlyRenderer(v -> specialTypeLabel((BFSSpecialType) v)));
        cbArtilleryType.setRenderer(friendlyRenderer(v -> artilleryTypeLabel((BFSArtilleryType) v)));
        specialsList.setCellRenderer(friendlyRenderer(v -> ((BFSSpecial) v).displayString()));
        cbMovementMode.setRenderer(friendlyRenderer(v ->
              motiveLabel((BFSAssetType) cbAssetType.getSelectedItem(), (EntityMovementMode) v)));
    }

    private static ListCellRenderer<Object> friendlyRenderer(Function<Object, String> labeler) {
        return new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                  boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value != null) {
                    setText(labeler.apply(value));
                }
                return this;
            }
        };
    }

    private static String assetTypeLabel(BFSAssetType type) {
        return I18N.getString("BFSStructureTab.assetType." + type.name());
    }

    private static String specialTypeLabel(BFSSpecialType type) {
        return I18N.getString("BFSStructureTab.special." + type.name());
    }

    private static String artilleryTypeLabel(BFSArtilleryType type) {
        return I18N.getString("BFSStructureTab.artillery." + type.name());
    }

    /** @return the asset being edited: the linked carrier in linked mode, else the sole standalone entity. */
    protected BattlefieldSupportAsset getAsset() {
        if (linked) {
            return assetSource.getBattlefieldSupportAssetCarrier();
        }
        return (BattlefieldSupportAsset) getEntity();
    }

    public void addRefreshedListener(RefreshListener l) {
        refresh = l;
    }

    private static JSpinner intSpinner(int value, int min, int max) {
        return new JSpinner(new SpinnerNumberModel(value, min, max, 1));
    }

    // region Layout

    private void setUpLayout() {
        // Three columns separated by draggable dividers so the user can rebalance them: left = basic info + stats,
        // middle = specials, right = preview + art. Nested split panes give two adjustable dividers.
        JScrollPane leftColumn = new JScrollPane(buildLeftColumn());
        JComponent middleColumn = buildMiddleColumn();
        JComponent rightColumn = buildRightColumn();

        leftColumn.setMinimumSize(new Dimension(240, 0));
        middleColumn.setMinimumSize(new Dimension(200, 0));
        rightColumn.setMinimumSize(new Dimension(260, 0));
        leftColumn.setPreferredSize(new Dimension(430, 700));
        middleColumn.setPreferredSize(new Dimension(330, 700));
        rightColumn.setPreferredSize(new Dimension(460, 700));

        JSplitPane rightSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, middleColumn, rightColumn);
        rightSplit.setResizeWeight(0.4);
        rightSplit.setContinuousLayout(true);

        JSplitPane mainSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftColumn, rightSplit);
        mainSplit.setResizeWeight(0.34);
        mainSplit.setContinuousLayout(true);

        setLayout(new BorderLayout());
        add(mainSplit, BorderLayout.CENTER);
    }

    private JPanel buildLeftColumn() {
        JPanel column = new JPanel(new GridBagLayout());

        JPanel movement = titledPanel(I18N.getString("BFSStructureTab.movement.title"));
        JPanel combat = titledPanel(I18N.getString("BFSStructureTab.combat.title"));
        int r = 0;
        addRow(combat, r++, I18N.getString("BFSStructureTab.damage.text"), twoSpinners(spnPerHit, spnHits));
        addRow(combat, r++, I18N.getString("BFSStructureTab.range.text"),
              threeSpinners(spnShort, spnMedium, spnLong));
        addRow(combat, r++, I18N.getString("BFSStructureTab.skill.text"), spnSkill);
        addRow(combat, r++, "", chkVeteran);
        addRow(combat, r++, I18N.getString("BFSStructureTab.veteranSkill.text"), spnVeteranSkill);
        addRow(combat, r++, I18N.getString("BFSStructureTab.destroyCheck.text"), spnDestroyCheck);
        addRow(combat, r, I18N.getString("BFSStructureTab.threshold.text"), spnThreshold);

        JPanel cost = titledPanel(I18N.getString("BFSStructureTab.cost.title"));
        addRow(cost, 0, I18N.getString("BFSStructureTab.cost.text"), spnCost);
        addRow(cost, 1, I18N.getString("BFSStructureTab.veteranCost.text"), spnVeteranCost);
        addRow(cost, 2, I18N.getString("BFSStructureTab.battleValue.text"), lblDerivedBv);

        if (linked) {
            // Linked mode: identity, tech base, asset type and movement mode all derive from (and are locked to) the
            // base unit, so only the asset-specific stats are shown. The whole tab is only visible when the asset is
            // enabled (via the Structure tab's checkbox), so there is no in-tab enable control.
            JPanel overrides = titledPanel(I18N.getString("BFSStructureTab.cardOverrides.title"));
            addRow(overrides, 0, I18N.getString("BFSStructureTab.cardTitle.text"), txtCardTitle);
            addRow(overrides, 1, I18N.getString("BFSStructureTab.cardSubtitle.text"), txtCardSubtitle);

            addRow(movement, 0, I18N.getString("BFSStructureTab.mp.text"), spnMp);
            addRow(movement, 1, I18N.getString("BFSStructureTab.tmm.text"), spnTmm);

            stack(column, overrides, movement, combat, cost);
            return column;
        }

        JPanel identity = titledPanel(I18N.getString("BFSStructureTab.identity.title"));
        r = 0;
        addRow(identity, r++, I18N.getString("BFSStructureTab.chassis.text"), txtChassis);
        addRow(identity, r++, I18N.getString("BFSStructureTab.model.text"), txtModel);
        addRow(identity, r++, I18N.getString("BFSStructureTab.cardTitle.text"), txtCardTitle);
        addRow(identity, r++, I18N.getString("BFSStructureTab.cardSubtitle.text"), txtCardSubtitle);
        addRow(identity, r++, I18N.getString("BFSStructureTab.introYear.text"), txtYear);
        addRow(identity, r++, I18N.getString("BFSStructureTab.techBase.text"), cbTechBase);
        addRow(identity, r++, I18N.getString("BFSStructureTab.source.text"), sourceView);
        addRow(identity, r++, I18N.getString("BFSStructureTab.assetType.text"), cbAssetType);
        addRow(identity, r, I18N.getString("BFSStructureTab.role.text"), cbRole);

        addRow(movement, 0, I18N.getString("BFSStructureTab.mode.text"), cbMovementMode);
        addRow(movement, 1, I18N.getString("BFSStructureTab.mp.text"), spnMp);
        addRow(movement, 2, I18N.getString("BFSStructureTab.tmm.text"), spnTmm);

        stack(column, identity, movement, combat, cost);
        return column;
    }

    private JPanel buildMiddleColumn() {
        JPanel column = titledPanel(I18N.getString("BFSStructureTab.specials.title"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(3, 3, 3, 3);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridwidth = 3;
        gbc.weightx = 1.0;

        specialsList.setVisibleRowCount(12);
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0;
        column.add(new JScrollPane(specialsList), gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weighty = 0;
        JButton btnRemove = new JButton(I18N.getString("BFSStructureTab.removeSelected.text"));
        btnRemove.addActionListener(e -> removeSelectedSpecial());
        gbc.gridy = 1;
        column.add(btnRemove, gbc);

        // Known-special picker: code combo + value control (an integer spinner for valued specials, the artillery-type
        // dropdown for Artillery, or an empty placeholder for valueless specials) + Add.
        specialValueCards.add(new JPanel(), "none");
        specialValueCards.add(spnSpecialValue, "spinner");
        specialValueCards.add(cbArtilleryType, "artillery");
        gbc.gridwidth = 1;
        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.weightx = 1.0;
        column.add(cbKnownSpecial, gbc);
        gbc.gridx = 1;
        gbc.weightx = 0;
        column.add(specialValueCards, gbc);
        gbc.gridx = 2;
        JButton btnAddKnown = new JButton(I18N.getString("BFSStructureTab.add.text"));
        btnAddKnown.addActionListener(e -> addKnownSpecial());
        column.add(btnAddKnown, gbc);

        // Free-text entry for arbitrary/unknown codes.
        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        column.add(txtFreeSpecial, gbc);
        gbc.gridx = 2;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        JButton btnAddFree = new JButton(I18N.getString("BFSStructureTab.addCustom.text"));
        btnAddFree.addActionListener(e -> addFreeSpecial());
        column.add(btnAddFree, gbc);

        return column;
    }

    private JPanel buildRightColumn() {
        JPanel column = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(3, 3, 3, 3);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        column.add(previewPanel, gbc);

        if (linked) {
            // Linked mode: the card art (fluff image + sprite) is taken from the base unit, like the name, so there is
            // no in-tab Art editor. Only the live card preview is shown.
            return column;
        }

        JPanel art = titledPanel(I18N.getString("BFSStructureTab.art.title"));
        JButton btnSetFluff = new JButton(I18N.getString("BFSStructureTab.fluffFile.text"));
        btnSetFluff.addActionListener(e -> chooseFluffImage());
        JButton btnFluffCache = new JButton(I18N.getString("BFSStructureTab.fluffCache.text"));
        btnFluffCache.addActionListener(e -> chooseFluffImageFromCache());
        JButton btnClearFluff = new JButton(I18N.getString("BFSStructureTab.clearFluff.text"));
        btnClearFluff.addActionListener(e -> clearFluffImage());
        JButton btnSetIcon = new JButton(I18N.getString("BFSStructureTab.spriteFile.text"));
        btnSetIcon.addActionListener(e -> chooseIconImage());
        JButton btnIconCache = new JButton(I18N.getString("BFSStructureTab.spriteCache.text"));
        btnIconCache.addActionListener(e -> chooseIconImageFromCache());
        JButton btnClearIcon = new JButton(I18N.getString("BFSStructureTab.clearSprite.text"));
        btnClearIcon.addActionListener(e -> clearIconImage());
        GridBagConstraints ag = new GridBagConstraints();
        ag.insets = new Insets(2, 2, 2, 2);
        ag.fill = GridBagConstraints.HORIZONTAL;
        ag.weightx = 1.0;
        ag.gridx = 0;
        ag.gridy = 0;
        art.add(btnSetFluff, ag);
        ag.gridx = 1;
        art.add(btnFluffCache, ag);
        ag.gridx = 2;
        art.add(btnClearFluff, ag);
        ag.gridx = 0;
        ag.gridy = 1;
        art.add(btnSetIcon, ag);
        ag.gridx = 1;
        art.add(btnIconCache, ag);
        ag.gridx = 2;
        art.add(btnClearIcon, ag);

        ag.gridx = 0;
        ag.gridy = 2;
        ag.gridwidth = 3;
        ag.fill = GridBagConstraints.NONE;
        ag.anchor = GridBagConstraints.CENTER;
        art.add(new JLabel(I18N.getString("BFSStructureTab.spritePreview.text"), SwingConstants.CENTER), ag);
        ag.gridy = 3;
        art.add(spritePreview, ag);

        gbc.gridy = 1;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        column.add(art, gbc);
        return column;
    }

    private static JPanel titledPanel(String title) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder(title));
        return panel;
    }

    private static void stack(JPanel column, JPanel... panels) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.gridx = 0;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(0, 0, 6, 0);
        int y = 0;
        for (JPanel panel : panels) {
            gbc.gridy = y++;
            column.add(panel, gbc);
        }
        // Filler so panels align to the top.
        gbc.gridy = y;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        column.add(new JPanel(), gbc);
    }

    private static void addRow(JPanel panel, int row, String label, JComponent field) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 4, 2, 4);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = row;
        panel.add(new JLabel(label, SwingConstants.RIGHT), gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        panel.add(field, gbc);
    }

    private static JPanel threeSpinners(JSpinner a, JSpinner b, JSpinner c) {
        JPanel panel = new JPanel();
        panel.add(a);
        panel.add(new JLabel("/"));
        panel.add(b);
        panel.add(new JLabel("/"));
        panel.add(c);
        return panel;
    }

    private static JPanel twoSpinners(JSpinner a, JSpinner b) {
        JPanel panel = new JPanel();
        panel.add(a);
        panel.add(new JLabel("x"));
        panel.add(b);
        return panel;
    }

    // endregion

    // region Listeners & refresh

    private void addListeners() {
        for (JSpinner spinner : List.of(spnMp, spnTmm, spnShort, spnMedium, spnLong, spnSkill, spnVeteranSkill,
              spnPerHit, spnHits, spnDestroyCheck, spnThreshold, spnCost, spnVeteranCost)) {
            spinner.addChangeListener(e -> commit());
        }
        for (JComboBox<?> combo : List.of(cbTechBase, cbRole, cbMovementMode)) {
            combo.addActionListener(e -> commit());
        }
        cbAssetType.addActionListener(e -> onAssetTypeChanged());
        chkVeteran.addActionListener(e -> commit());
        cbKnownSpecial.addActionListener(e -> updateSpecialValueEnabled());
        sourceView.setChangeListener(source -> commit());
        for (JTextField field : List.of(txtChassis, txtModel, txtCardTitle, txtCardSubtitle, txtYear)) {
            field.addFocusListener(new FocusAdapter() {
                @Override
                public void focusLost(FocusEvent e) {
                    commit();
                }
            });
        }
    }

    /** Reloads every field from the asset entity. */
    public void refresh() {
        BattlefieldSupportAsset asset = getAsset();
        if (asset == null) {
            return;
        }
        if (linked) {
            // Keep the carrier's base-derived identity/type/mode in sync with the base unit so a Structure-tab change
            // (name, motive, tech base, ...) is reflected in the card the next time this tab refreshes.
            BFSLinkedAssetSupport.applySharedIdentity(getEntity(), asset);
        }
        loading = true;

        txtChassis.setText(asset.getChassis());
        txtModel.setText(asset.getModel());
        txtCardTitle.setText(asset.getCardTitle() == null ? "" : asset.getCardTitle());
        txtCardSubtitle.setText(asset.getCardSubtitle() == null ? "" : asset.getCardSubtitle());
        txtYear.setIntVal(asset.getYear());
        cbTechBase.setSelectedItem(asset.getAssetTechBase());
        sourceView.setSource(asset.getSource());
        cbAssetType.setSelectedItem(asset.getAssetType());
        cbRole.setSelectedItem(asset.getRole());
        rebuildMotiveOptions(asset.getMovementMode());
        spnMp.setValue(asset.getMp());
        spnTmm.setValue(asset.getTmm());

        BFSRange range = asset.getRange();
        boolean keyword = range.isKeyword();
        spnShort.setValue(keyword ? 0 : range.shortRange());
        spnMedium.setValue(keyword ? 0 : range.mediumRange());
        spnLong.setValue(keyword ? 0 : range.longRange());

        spnSkill.setValue(asset.getSkill());
        boolean hasVeteran = asset.hasVeteranProfile();
        chkVeteran.setSelected(hasVeteran);
        spnVeteranSkill.setValue(asset.getVeteranSkill() == null ? asset.getSkill() : asset.getVeteranSkill());
        spnVeteranCost.setValue(asset.getVeteranCost() == null ? asset.getCost() : asset.getVeteranCost());

        BFSDamage damage = asset.getDamage();
        spnPerHit.setValue(damage.perHit());
        spnHits.setValue(damage.hits());

        spnDestroyCheck.setValue(asset.getODestroyCheck());
        spnThreshold.setValue(asset.getThreshold());
        spnCost.setValue(asset.getCost());

        specialsModel.clear();
        for (BFSSpecial special : asset.getSpecials()) {
            specialsModel.addElement(special);
        }

        loading = false;
        updateEnabledStates();
        updateDerivedBv();
        updateSpritePreview();
        previewPanel.setAsset(asset);
    }

    /** Writes every field back onto the asset entity, then updates the preview and notifies other views. */
    private void commit() {
        if (loading) {
            return;
        }
        BattlefieldSupportAsset asset = getAsset();
        if (asset == null) {
            return;
        }

        asset.setCardTitle(blankToNull(txtCardTitle.getText()));
        asset.setCardSubtitle(blankToNull(txtCardSubtitle.getText()));
        if (linked) {
            // Identity, tech base, asset type and movement mode are owned by the base unit; re-sync them so the saved
            // .bfs stays consistent, rather than reading the (hidden) standalone identity controls.
            BFSLinkedAssetSupport.applySharedIdentity(getEntity(), asset);
        } else {
            asset.setChassis(txtChassis.getText().trim());
            asset.setModel(txtModel.getText().trim());
            asset.setYear(txtYear.getIntVal());
            asset.setSource(sourceView.getSource());
            asset.setAssetTechBase((String) cbTechBase.getSelectedItem());
            asset.setAssetType((BFSAssetType) cbAssetType.getSelectedItem());
            asset.setUnitRole((UnitRole) cbRole.getSelectedItem());
            asset.setMovementMode((EntityMovementMode) cbMovementMode.getSelectedItem());
        }

        if (isImmobileMode()) {
            setSpinnerSilently(spnMp, 0);
            setSpinnerSilently(spnTmm, 0);
        }
        asset.setMp((int) spnMp.getValue());
        asset.setTmm((int) spnTmm.getValue());

        int perHit = (int) spnPerHit.getValue();
        int hits = (int) spnHits.getValue();
        asset.setDamage(new BFSDamage(perHit, hits));

        // An asset has keyword range exactly when it deals no damage; this is derived, not user-set.
        if (dealsDamage()) {
            asset.setRange(new BFSRange((int) spnShort.getValue(), (int) spnMedium.getValue(),
                  (int) spnLong.getValue()));
        } else {
            asset.setRange(BFSRange.KEYWORD);
        }

        asset.setSkill((int) spnSkill.getValue());
        asset.setCost((int) spnCost.getValue());
        if (chkVeteran.isSelected()) {
            enforceVeteranConstraints();
            asset.setVeteranSkill((int) spnVeteranSkill.getValue());
            asset.setVeteranCost((int) spnVeteranCost.getValue());
        } else {
            asset.setVeteranSkill(null);
            asset.setVeteranCost(null);
        }

        asset.setODestroyCheck((int) spnDestroyCheck.getValue());
        asset.setThreshold((int) spnThreshold.getValue());

        updateEnabledStates();
        updateDerivedBv();
        previewPanel.setAsset(asset);

        if (refresh != null) {
            refresh.refreshStructure();
            refresh.refreshHeader();
        }
    }

    /** Handles an Asset-type change: resets the motive options to the new type's default and enforces its rules. */
    private void onAssetTypeChanged() {
        if (loading) {
            return;
        }
        BFSAssetType type = (BFSAssetType) cbAssetType.getSelectedItem();
        if (type == null) {
            return;
        }
        rebuildMotiveOptions(defaultMotiveFor(type));
        boolean specialsChanged = enforceImmobileForType();
        commit();
        if (specialsChanged) {
            commitSpecials();
        }
    }

    /** The movement modes allowed for the given Asset type. */
    private static EntityMovementMode[] motiveOptionsFor(BFSAssetType type) {
        return BFSLinkedAssetSupport.allowedMotives(type);
    }

    /** The default motive type for a newly-typed Asset. */
    private static EntityMovementMode defaultMotiveFor(BFSAssetType type) {
        return BFSLinkedAssetSupport.defaultMotive(type);
    }

    /**
     * The friendly label for a motive mode, contextual to the Asset type: infantry types drop the "Infantry" suffix
     * ("Foot"/"Jump"/"Motorized"), and Conventional Infantry labels its vehicle motive types as "Mechanized (X)".
     */
    private static String motiveLabel(BFSAssetType type, EntityMovementMode mode) {
        if (type == BFSAssetType.CONV_INFANTRY) {
            return switch (mode) {
                case INF_LEG -> I18N.getString("BFSStructureTab.motive.foot");
                case INF_JUMP -> I18N.getString("BFSStructureTab.motive.jump");
                case INF_MOTORIZED -> I18N.getString("BFSStructureTab.motive.motorized");
                case NONE -> mode.toString();
                default -> I18N.getString("BFSStructureTab.motive.mechanized").formatted(mode);
            };
        }
        if (type == BFSAssetType.BATTLE_ARMOR) {
            return switch (mode) {
                case INF_LEG -> I18N.getString("BFSStructureTab.motive.foot");
                case INF_JUMP -> I18N.getString("BFSStructureTab.motive.jump");
                default -> mode.toString();
            };
        }
        return mode.toString();
    }

    /**
     * Rebuilds the motive-type combo for the currently-selected Asset type, selecting {@code desired} if it is a valid
     * option or the type's default otherwise. Does not fire a commit.
     */
    private void rebuildMotiveOptions(EntityMovementMode desired) {
        BFSAssetType type = (BFSAssetType) cbAssetType.getSelectedItem();
        if (type == null) {
            return;
        }
        EntityMovementMode[] allowed = motiveOptionsFor(type);
        EntityMovementMode target = Arrays.asList(allowed).contains(desired) ? desired : defaultMotiveFor(type);
        boolean previousLoading = loading;
        loading = true;
        cbMovementMode.setModel(new DefaultComboBoxModel<>(allowed));
        cbMovementMode.setSelectedItem(target);
        loading = previousLoading;
    }

    /**
     * Enforces the Immobile special for the current Asset type: Emplacements must carry it, and switching to any other
     * type removes the by-design Immobile (the user may re-add it manually). @return true if the specials changed.
     */
    private boolean enforceImmobileForType() {
        BFSAssetType type = (BFSAssetType) cbAssetType.getSelectedItem();
        boolean hasImmobile = false;
        int immobileIndex = -1;
        for (int i = 0; i < specialsModel.size(); i++) {
            if (isImmobile(specialsModel.get(i))) {
                hasImmobile = true;
                immobileIndex = i;
                break;
            }
        }
        if (type == BFSAssetType.EMPLACEMENT) {
            if (!hasImmobile) {
                specialsModel.addElement(BFSSpecial.of(BFSSpecialType.IMMOBILE.canonicalCode()));
                return true;
            }
            return false;
        }
        if (hasImmobile) {
            specialsModel.remove(immobileIndex);
            return true;
        }
        return false;
    }

    private static boolean isImmobile(BFSSpecial special) {
        return BFSSpecialType.forCode(special.code()).map(type -> type == BFSSpecialType.IMMOBILE).orElse(false);
    }

    private void updateEnabledStates() {
        // An asset has keyword range (no distance brackets) exactly when it deals no damage.
        boolean numericRange = dealsDamage();
        spnShort.setEnabled(numericRange);
        spnMedium.setEnabled(numericRange);
        spnLong.setEnabled(numericRange);

        boolean veteran = chkVeteran.isSelected();
        spnVeteranSkill.setEnabled(veteran);
        spnVeteranCost.setEnabled(veteran);
        if (veteran) {
            enforceVeteranConstraints();
        }

        // None (immobile) movement locks MP and TMM to 0.
        boolean mobile = !isImmobileMode();
        spnMp.setEnabled(mobile);
        spnTmm.setEnabled(mobile);

        updateSpecialValueEnabled();
    }

    /**
     * Constrains the Veteran spinners relative to the Regular values: a Veteran profile must be more skilled (a
     * strictly lower skill number - lower is better) and more expensive (a strictly higher cost). The spinner bounds
     * are tightened and any out-of-range value is clamped silently.
     */
    private void enforceVeteranConstraints() {
        int regularSkill = (int) spnSkill.getValue();
        int regularCost = (int) spnCost.getValue();

        int vetSkillMax = Math.max(0, regularSkill - 1);
        ((SpinnerNumberModel) spnVeteranSkill.getModel()).setMaximum(vetSkillMax);
        if ((int) spnVeteranSkill.getValue() > vetSkillMax) {
            setSpinnerSilently(spnVeteranSkill, vetSkillMax);
        }

        int vetCostMin = regularCost + 1;
        ((SpinnerNumberModel) spnVeteranCost.getModel()).setMinimum(vetCostMin);
        if ((int) spnVeteranCost.getValue() < vetCostMin) {
            setSpinnerSilently(spnVeteranCost, vetCostMin);
        }
    }

    /** @return true when the asset deals damage (both per-hit and hits are positive); otherwise it has keyword range. */
    private boolean dealsDamage() {
        return ((int) spnPerHit.getValue() > 0) && ((int) spnHits.getValue() > 0);
    }

    private boolean isImmobileMode() {
        return cbMovementMode.getSelectedItem() == EntityMovementMode.NONE;
    }

    /** Sets a spinner's value without triggering a re-entrant commit. */
    private void setSpinnerSilently(JSpinner spinner, int value) {
        boolean previousLoading = loading;
        loading = true;
        spinner.setValue(value);
        loading = previousLoading;
    }

    private void updateSpecialValueEnabled() {
        BFSSpecialType type = (BFSSpecialType) cbKnownSpecial.getSelectedItem();
        CardLayout layout = (CardLayout) specialValueCards.getLayout();
        if (type == BFSSpecialType.ARTILLERY) {
            layout.show(specialValueCards, "artillery");
        } else if ((type != null) && type.takesValue()) {
            layout.show(specialValueCards, "spinner");
        } else {
            layout.show(specialValueCards, "none");
        }
    }

    private void updateSpritePreview() {
        if (linked) {
            // No in-tab sprite editor in linked mode; art derives from the base unit.
            return;
        }
        BattlefieldSupportAsset asset = getAsset();
        if (asset != null) {
            spritePreview.updateDisplayedEntity(asset, asset.hasEmbeddedIcon() ? CAMO_EMBEDDED : CAMO_MEKSET);
        }
    }

    private void updateDerivedBv() {
        BattlefieldSupportAsset asset = getAsset();
        if (asset == null) {
            return;
        }
        String text = I18N.getString("BFSStructureTab.bv.text").formatted(asset.getBv());
        if (asset.getVeteranBv() != null) {
            text += I18N.getString("BFSStructureTab.veteranBv.text").formatted(asset.getVeteranBv());
        }
        lblDerivedBv.setText(text);
    }

    // endregion

    // region Specials editing

    private void addKnownSpecial() {
        BFSSpecialType type = (BFSSpecialType) cbKnownSpecial.getSelectedItem();
        if (type == null) {
            return;
        }
        BFSSpecial special;
        if (type == BFSSpecialType.ARTILLERY) {
            BFSArtilleryType artillery = (BFSArtilleryType) cbArtilleryType.getSelectedItem();
            special = BFSSpecial.of(type.canonicalCode(), artillery.code());
        } else if (type.takesValue()) {
            // The value control is an integer spinner for valued specials, so a valid number is always present.
            special = BFSSpecial.of(type.canonicalCode(), (int) spnSpecialValue.getValue());
        } else {
            special = BFSSpecial.of(type.canonicalCode());
        }
        addSpecial(special);
    }

    private void addFreeSpecial() {
        BFSSpecial special = BFSSpecial.parse(txtFreeSpecial.getText().trim());
        if (special == null) {
            return;
        }
        if (isInvalidArtillery(special)) {
            JOptionPane.showMessageDialog(getParent(),
                  I18N.getString("BFSStructureTab.invalidArtillery.message"),
                  I18N.getString("BFSStructureTab.invalidArtillery.title"), JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (requiresMissingNumber(special)) {
            BFSSpecialType type = BFSSpecialType.forCode(special.code()).orElseThrow();
            JOptionPane.showMessageDialog(getParent(),
                  I18N.getString("BFSStructureTab.missingValue.message")
                        .formatted(specialTypeLabel(type), type.canonicalCode()),
                  I18N.getString("BFSStructureTab.missingValue.title"), JOptionPane.WARNING_MESSAGE);
            return;
        }
        txtFreeSpecial.setText("");
        addSpecial(special);
    }

    /** @return true if the given Special is a known valued (non-artillery) special that lacks a numeric value. */
    private static boolean requiresMissingNumber(BFSSpecial special) {
        return BFSSpecialType.forCode(special.code())
              .filter(type -> type.takesValue() && (type != BFSSpecialType.ARTILLERY))
              .map(type -> special.intValue().isEmpty())
              .orElse(false);
    }

    /** @return true if the given Special is an Artillery Special whose value is not one of the three valid types. */
    private static boolean isInvalidArtillery(BFSSpecial special) {
        boolean isArtillery = BFSSpecialType.forCode(special.code())
              .map(type -> type == BFSSpecialType.ARTILLERY)
              .orElse(false);
        return isArtillery && (BFSArtilleryType.fromString(special.value()) == null);
    }

    private void addSpecial(BFSSpecial special) {
        specialsModel.addElement(special);
        commitSpecials();
    }

    private void removeSelectedSpecial() {
        int index = specialsList.getSelectedIndex();
        if (index < 0) {
            return;
        }
        BFSAssetType type = (BFSAssetType) cbAssetType.getSelectedItem();
        if ((type == BFSAssetType.EMPLACEMENT) && isImmobile(specialsModel.get(index))) {
            JOptionPane.showMessageDialog(getParent(),
                  I18N.getString("BFSStructureTab.immobileRequired.message"),
                  I18N.getString("BFSStructureTab.immobileRequired.title"), JOptionPane.WARNING_MESSAGE);
            return;
        }
        specialsModel.remove(index);
        commitSpecials();
    }

    private void commitSpecials() {
        BattlefieldSupportAsset asset = getAsset();
        if (asset == null) {
            return;
        }
        List<BFSSpecial> specials = new ArrayList<>();
        for (int i = 0; i < specialsModel.size(); i++) {
            specials.add(specialsModel.get(i));
        }
        asset.setSpecials(specials);
        previewPanel.setAsset(asset);
        if (refresh != null) {
            refresh.refreshStructure();
            refresh.refreshHeader();
        }
    }

    // endregion

    // region Art

    private void chooseFluffImage() {
        BufferedImage image = chooseImage();
        if (image != null) {
            getAsset().getFluff().setFluffImage(ImageUtil.base64TextEncodeImage(image));
            previewPanel.setAsset(getAsset());
        }
    }

    private void clearFluffImage() {
        if (getAsset().getFluff().hasEmbeddedFluffImage()) {
            getAsset().getFluff().setFluffImage("");
            previewPanel.setAsset(getAsset());
        }
    }

    private void chooseIconImage() {
        BufferedImage image = chooseImage();
        if (image != null) {
            getAsset().setIcon(ImageUtil.base64TextEncodeImage(image));
            updateSpritePreview();
            previewPanel.setAsset(getAsset());
        }
    }

    private void clearIconImage() {
        if (getAsset().hasEmbeddedIcon()) {
            getAsset().setIcon("");
            updateSpritePreview();
            previewPanel.setAsset(getAsset());
        }
    }

    private void chooseFluffImageFromCache() {
        Entity chosen = chooseEntityFromCache();
        if (chosen != null) {
            Image image = FluffImageHelper.getFluffImage(chosen);
            if (image == null) {
                PopupMessages.showNoFluffImage(getParent());
                return;
            }
            getAsset().getFluff().setFluffImage(ImageUtil.base64TextEncodeImage(image));
            previewPanel.setAsset(getAsset());
        }
    }

    private void chooseIconImageFromCache() {
        Entity chosen = chooseEntityFromCache();
        if ((chosen != null) && (MMStaticDirectoryManager.getMekTileset() != null)) {
            Image image = MMStaticDirectoryManager.getMekTileset().imageFor(chosen);
            getAsset().setIcon(ImageUtil.base64TextEncodeImage(image));
            updateSpritePreview();
            previewPanel.setAsset(getAsset());
        }
    }

    /** Opens the unit selector and returns the chosen unit from the cache, or {@code null} if cancelled. */
    private Entity chooseEntityFromCache() {
        UnitLoadingDialog unitLoadingDialog = new UnitLoadingDialog(null);
        unitLoadingDialog.setVisible(true);
        MegaMekLabUnitSelectorDialog viewer = new MegaMekLabUnitSelectorDialog(null, unitLoadingDialog, false);
        try {
            return viewer.getChosenEntity();
        } finally {
            unitLoadingDialog.dispose();
            viewer.dispose();
        }
    }

    private BufferedImage chooseImage() {
        MMLFileChooser imageChooser = new MMLFileChooser();
        int result = imageChooser.showOpenDialog(getParent());
        if ((result != JFileChooser.APPROVE_OPTION) || (imageChooser.getSelectedFile() == null)) {
            return null;
        }
        File file = imageChooser.getSelectedFile();
        try {
            BufferedImage image = ImageIO.read(file);
            if (image == null) {
                PopupMessages.showFileReadError(getParent(), file.toString());
            }
            return image;
        } catch (Exception ex) {
            PopupMessages.showFileReadError(getParent(), file.toString(), ex.getMessage());
            logger.error("", ex);
            return null;
        }
    }

    // endregion

    private static String blankToNull(String text) {
        return (text == null || text.isBlank()) ? null : text.trim();
    }
}
