/*
 * Copyright (C) 2020-2025 The MegaMek Team. All Rights Reserved.
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
package megameklab.ui.dialog;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import javax.swing.*;

import megamek.client.ui.Messages;
import megamek.client.ui.WrapLayout;
import megamek.client.ui.dialogs.UnitLoadingDialog;
import megamek.client.ui.dialogs.unitSelectorDialogs.AbstractUnitSelectorDialog;
import megamek.client.ui.tileset.EntityImage;
import megamek.client.ui.tileset.MMStaticDirectoryManager;
import megamek.client.ui.util.PlayerColour;
import megamek.common.TechConstants;
import megamek.common.battlefieldSupport.BattlefieldSupportAsset;
import megamek.common.loaders.MekSummary;
import megamek.common.units.Entity;
import megamek.common.units.UnitType;
import megameklab.ui.generalUnit.BattlefieldSupportCardListPanel;
import megameklab.ui.generalUnit.RecordSheetPreviewPanel;
import megameklab.ui.util.PreviewCamouflage;
import megameklab.util.CConfig;
import megameklab.util.UnitPrintManager;
import megameklab.util.UnitUtil;

public class MegaMekLabUnitSelectorDialog extends AbstractUnitSelectorDialog {
    private static final int NO_UNIT_TYPE_RESTRICTION = -1;
    private static final Predicate<MekSummary> NO_UNIT_FILTER = Objects::nonNull;

    // region Variable Declarations
    private Entity chosenEntity;
    private ArrayList<Entity> chosenEntities;
    private final boolean allowPickWithoutClose;
    private Consumer<MegaMekLabUnitSelectorDialog> entityPickCallback;
    private RecordSheetPreviewPanel recordSheetPanel;
    private BattlefieldSupportCardListPanel bfsCardsPanel;
    private JButton printRecordSheetButton;
    private JButton exportToPDFRecordSheetButton;
    private JButton buttonSelectAsset;
    private final int restrictedUnitType;
    /**
     * When {@code true} (Force Builder / Print Queue), the callback dialog offers separate "Select as Unit" and "Select
     * as Asset" buttons. When {@code false} (opening a unit for editing), a single "Select" opens the combined editor,
     * so no asset/unit form choice is needed.
     */
    private boolean offerAssetForm = true;

    // endregion Variable Declarations

    /**
     * Constructs a Unit Selector Dialog that only allows choosing with closing the dialog.
     *
     * @param parent            The parent window of this dialog
     * @param unitLoadingDialog A {@link UnitLoadingDialog} likely {@code new UnitLoadingDialog(parent)}.
     * @param multiselect       Set this to {@code true} to allow multiple units to be selected at once.
     */
    public MegaMekLabUnitSelectorDialog(JFrame parent, UnitLoadingDialog unitLoadingDialog, boolean multiselect) {
        this(parent, unitLoadingDialog, multiselect, NO_UNIT_TYPE_RESTRICTION, NO_UNIT_FILTER);
    }

    public MegaMekLabUnitSelectorDialog(JFrame parent, UnitLoadingDialog unitLoadingDialog, boolean multiselect,
          int restrictedUnitType) {
        this(parent, unitLoadingDialog, multiselect, restrictedUnitType, NO_UNIT_FILTER);
    }

    public MegaMekLabUnitSelectorDialog(JFrame parent, UnitLoadingDialog unitLoadingDialog, boolean multiselect,
          int restrictedUnitType, Predicate<MekSummary> unitFilter) {
        super(parent, unitLoadingDialog, multiselect);
        gameTechLevel = TechConstants.T_SIMPLE_UNOFFICIAL;
        allowPickWithoutClose = false;
        this.restrictedUnitType = restrictedUnitType;
        setUnitSelectionScopeFilter(createUnitSelectionScopeFilter(restrictedUnitType, unitFilter));
        eraBasedTechLevel = CConfig.getBooleanParam(CConfig.TECH_PROGRESSION);
        if (CConfig.getBooleanParam(CConfig.TECH_USE_YEAR)) {
            allowedYear = CConfig.getIntParam(CConfig.TECH_YEAR);
        }
        initialize();
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                closeWithoutSelection();
            }
        });
        setupDoubleClickListener();
        setupRecordSheetTab();
        setupBfsCardsTab();
        run();
        setVisible(true);
    }

    /**
     * Constructs a Unit Selector Dialog that allows choosing a Unit while keeping the dialog open by pressing Enter or
     * the "Select" button. The entityPickCallback method will be called when units are selected in this way.
     * Multiselect is always enabled. This form offers the separate "Select as Unit" / "Select as Asset" buttons.
     *
     * @param parent             The parent window of this dialog
     * @param unitLoadingDialog  A {@link UnitLoadingDialog} likely {@code new UnitLoadingDialog(parent)}.
     * @param entityPickCallback This will be called when the user presses Select.
     */
    public MegaMekLabUnitSelectorDialog(JFrame parent, UnitLoadingDialog unitLoadingDialog,
          Consumer<MegaMekLabUnitSelectorDialog> entityPickCallback) {
        this(parent, unitLoadingDialog, entityPickCallback, true);
    }

    /**
     * Constructs a callback Unit Selector Dialog (see the three-argument constructor), choosing whether to offer the
     * "Select as Unit" / "Select as Asset" form buttons. When opening a unit for editing, pass {@code false}: a single
     * "Select" opens the combined base + asset editor, so no form choice is needed.
     *
     * @param parent             The parent window of this dialog
     * @param unitLoadingDialog  A {@link UnitLoadingDialog} likely {@code new UnitLoadingDialog(parent)}.
     * @param entityPickCallback This will be called when the user presses Select.
     * @param offerAssetForm     whether to offer separate unit/asset form buttons (Force Builder / Print Queue) or a
     *                           single "Select" (editing)
     */
    public MegaMekLabUnitSelectorDialog(JFrame parent, UnitLoadingDialog unitLoadingDialog,
          Consumer<MegaMekLabUnitSelectorDialog> entityPickCallback, boolean offerAssetForm) {
        super(parent, unitLoadingDialog, true);
        this.offerAssetForm = offerAssetForm;
        gameTechLevel = TechConstants.T_SIMPLE_UNOFFICIAL;
        allowPickWithoutClose = true;
        restrictedUnitType = NO_UNIT_TYPE_RESTRICTION;
        eraBasedTechLevel = CConfig.getBooleanParam(CConfig.TECH_PROGRESSION);
        if (CConfig.getBooleanParam(CConfig.TECH_USE_YEAR)) {
            allowedYear = CConfig.getIntParam(CConfig.TECH_YEAR);
        }
        this.entityPickCallback = entityPickCallback;
        initialize();
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                closeWithoutSelection();
            }
        });
        setupDoubleClickListener();
        // This overrides the default close behavior to avoid selecting another unit
        // when closing with ESC or the Close button. AbstractUnitSelectorDialog should
        // probably be changed to make the selectedEntity null in these cases
        JRootPane rootPane = getRootPane();
        KeyStroke escape = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
        rootPane.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(escape, CLOSE_ACTION);
        rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(escape, CLOSE_ACTION);
        rootPane.getInputMap(JComponent.WHEN_FOCUSED).put(escape, CLOSE_ACTION);
        rootPane.getActionMap().put(CLOSE_ACTION, closeAction);
        setupRecordSheetTab();
        setupBfsCardsTab();
        run();
        setVisible(true);

    }

    private boolean hasRestrictedUnitType() {
        return restrictedUnitType != NO_UNIT_TYPE_RESTRICTION;
    }

    private static Predicate<MekSummary> createUnitSelectionScopeFilter(int restrictedUnitType,
          Predicate<MekSummary> unitFilter) {
        Predicate<MekSummary> filter = NO_UNIT_FILTER;
        if (restrictedUnitType != NO_UNIT_TYPE_RESTRICTION) {
            String restrictedUnitTypeName = UnitType.getTypeName(restrictedUnitType);
            filter = filter.and(unit -> restrictedUnitTypeName.equals(unit.getUnitType()));
        }
        return (unitFilter == null) ? filter : filter.and(unitFilter);
    }

    @Override
    protected void configureUnitSelectionScope() {
        comboUnitType.setEnabled(!hasRestrictedUnitType());
        if (hasRestrictedUnitType()) {
            comboUnitType.setSelectedItem(UnitType.getTypeDisplayableName(restrictedUnitType));
        }
    }

    private void setupDoubleClickListener() {
        // The table showing units is in the parent class
        if (tableUnits != null) {
            tableUnits.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    if (evt.getClickCount() == 2) {
                        // Double click detected - select the available form and close.
                        if (!offerAssetForm) {
                            // Editing: open the combined editor for whatever the row is (base + linked asset, or a
                            // standalone asset).
                            selectForm(true, false);
                        } else if (hasSelectedRows()) {
                            // Select each row's standard entity form; a standalone Asset row selects the Asset itself.
                            selectForm(true, false);
                        }
                    }
                }
            });
        }
    }

    private void setupRecordSheetTab() {
        if (recordSheetPanel == null) {
            // Create the record sheet panel
            recordSheetPanel = new RecordSheetPreviewPanel();
            recordSheetPanel.setFullAsyncMode(true);

            // Create a toolbar panel with print button
            JPanel toolbarPanel = new JPanel(new WrapLayout(FlowLayout.LEFT, 15, 10));
            toolbarPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, toolbarPanel.getPreferredSize().height));

            // Create a print button
            printRecordSheetButton = new JButton("Print");
            printRecordSheetButton.setEnabled(false);
            printRecordSheetButton.addActionListener(e -> {
                if (multiSelect) {
                    ArrayList<Entity> entities = getSelectedEntities();
                    if ((entities != null) && !entities.isEmpty()) {
                        new PrintQueueDialog(frame, false, entities, false, "").setVisible(true);
                    }
                } else {
                    Entity entity = getSelectedEntity();
                    if (entity != null) {
                        UnitPrintManager.printEntity(entity);
                    }
                }
            });
            toolbarPanel.add(printRecordSheetButton);

            // Create an export to PDF button
            exportToPDFRecordSheetButton = new JButton("Export to PDF");
            exportToPDFRecordSheetButton.setEnabled(false);
            exportToPDFRecordSheetButton.addActionListener(e -> {
                if (multiSelect) {
                    ArrayList<Entity> entities = getSelectedEntities();
                    if ((entities != null) && !entities.isEmpty()) {
                        new PrintQueueDialog(frame, true, entities, false, "").setVisible(true);
                    }
                } else {
                    Entity entity = getSelectedEntity();
                    if (entity != null) {
                        UnitPrintManager.exportEntity(entity, frame);
                    }
                }
            });
            toolbarPanel.add(exportToPDFRecordSheetButton);

            // Create a container panel for the record sheet and toolbar
            JPanel recordSheetContainer = new JPanel(new BorderLayout());
            recordSheetContainer.add(toolbarPanel, BorderLayout.NORTH);
            recordSheetContainer.add(recordSheetPanel);

            // Add the container to the preview tabs
            panePreview.addTab("Record Sheet", recordSheetContainer);
        }
    }

    /**
     * Adds the multi-select "BFS Cards" preview tab, which shows every selected unit's Battlefield Support Asset card at
     * once (the BFS parallel of the Record Sheet tab). Only added in multi-select mode; in single-select the base
     * preview pane already shows the focused unit's BFS Card tab.
     */
    private void setupBfsCardsTab() {
        if (multiSelect && (bfsCardsPanel == null)) {
            bfsCardsPanel = new BattlefieldSupportCardListPanel();
            panePreview.addTab("BFS Cards", bfsCardsPanel);
        }
    }

    // Only necessary to override the default close behavior, see constructor
    Action closeAction = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            closeWithoutSelection();
        }
    };

    @Override
    public void updateOptionValues() {
        // Why is this empty?
    }

    // region Button Methods
    @Override
    protected JPanel createButtonsPanel() {
        JPanel panelButtons = new JPanel(new GridBagLayout());

        if (allowPickWithoutClose) {
            if (offerAssetForm) {
                // Callback (Force Builder / Print Queue) mode: two adjacent form buttons that add the selection without
                // closing. "Select as Unit" reuses buttonSelect (the base dialog wires it to select(false) = add as
                // unit); "Select as Asset" adds the assets. They are enabled/disabled per selection in refreshUnitView.
                buttonSelect = new JButton(Messages.getString("MekSelectorDialog.SelectAsUnit"));
                buttonSelect.setToolTipText(Messages.getString("MekSelectorDialog.SelectAsUnit.ToolTip"));
                buttonSelect.addActionListener(this);
                panelButtons.add(buttonSelect, new GridBagConstraints());

                buttonSelectAsset = new JButton(Messages.getString("MekSelectorDialog.SelectAsAsset"));
                buttonSelectAsset.setToolTipText(Messages.getString("MekSelectorDialog.SelectAsAsset.ToolTip"));
                buttonSelectAsset.addActionListener(e -> selectForm(false, true));
                panelButtons.add(buttonSelectAsset, new GridBagConstraints());
            } else {
                // Editing mode: a single "Select" opens the combined base + asset editor, so no form choice is offered.
                buttonSelect = new JButton(Messages.getString("MekSelectorDialog.m_bPick"));
                buttonSelect.addActionListener(this);
                panelButtons.add(buttonSelect, new GridBagConstraints());
            }
        }

        buttonSelectClose = new JButton(Messages.getString("MekSelectorDialog.m_bPickClose"));
        buttonSelectClose.addActionListener(this);
        panelButtons.add(buttonSelectClose, new GridBagConstraints());

        buttonClose = new JButton(Messages.getString("Close"));
        // Override the default close behavior, see constructor
        buttonClose.addActionListener(e -> closeWithoutSelection());
        panelButtons.add(buttonClose, new GridBagConstraints());

        buttonShowBV = new JButton(Messages.getString("MekSelectorDialog.BV"));
        buttonShowBV.addActionListener(this);
        panelButtons.add(buttonShowBV, new GridBagConstraints());

        return panelButtons;
    }

    void closeWithoutSelection() {
        chosenEntity = null;
        chosenEntities = new ArrayList<>();
        setVisible(false);
    }

    @Override
    protected void select(boolean close) {
        selectForm(close, false);
    }

    /**
     * Records the current selection as the chosen entities in the requested form and either closes the dialog or
     * notifies the pick callback (keeping the dialog open).
     *
     * @param close   {@code true} to close the dialog; {@code false} to keep it open and fire the pick callback
     * @param asAsset {@code true} to select each row's Battlefield Support Asset form; {@code false} for the standard
     *                (TW) unit form
     */
    private void selectForm(boolean close, boolean asAsset) {
        if (multiSelect) {
            chosenEntities = asAsset ? getSelectedAssetEntities() : getSelectedEntities();
        } else {
            chosenEntity = getSelectedEntity();
        }

        if (close) {
            setVisible(false);
        } else if (entityPickCallback != null) {
            entityPickCallback.accept(this);
        }
    }
    // endregion Button Methods

    /**
     * @return the chosenEntity
     */
    public Entity getChosenEntity() {
        if (multiSelect) {
            throw new IllegalStateException("multiselect must false to use getChosenEntity");
        }
        return chosenEntity;
    }

    public ArrayList<Entity> getChosenEntities() {
        if (!multiSelect) {
            throw new IllegalStateException("multiselect must true to use getChosenEntities");
        }
        if (chosenEntities == null) {
            chosenEntities = new ArrayList<>();
        }
        return chosenEntities;
    }

    @Override
    protected Entity refreshUnitView() {
        Entity selectedEntity = super.refreshUnitView();
        // Update the record sheet with the selected entity
        if (selectedEntity != null) {
            // Update unit image first (existing code)
            Image base = MMStaticDirectoryManager.getMekTileset().imageFor(selectedEntity);
            EntityImage entityImage = EntityImage.createIcon(base, PreviewCamouflage.of(PlayerColour.GOLD),
                  selectedEntity);
            entityImage.loadFacings();
            labelImage.setIcon(new ImageIcon(entityImage.getFacing(0)));
        }

        updateSelectFormButtons();

        // The Record Sheet preview shows the selected units in their standard (TW) form; asset-only rows have no
        // record sheet, so exclude them (their card appears on the BFS Card tab instead).
        ArrayList<Entity> recordSheetEntities = getSelectedEntities().stream()
              .filter(entity -> !(entity instanceof BattlefieldSupportAsset))
              .collect(Collectors.toCollection(ArrayList::new));
        recordSheetEntities.forEach(UnitUtil::updateLoadedUnit);
        if (!recordSheetEntities.isEmpty()) {
            recordSheetPanel.setEntities(recordSheetEntities);
            printRecordSheetButton.setEnabled(true);
            exportToPDFRecordSheetButton.setEnabled(true);
        } else {
            recordSheetPanel.setEntity(null);
            printRecordSheetButton.setEnabled(false);
            exportToPDFRecordSheetButton.setEnabled(false);
        }

        // The BFS Cards preview shows every selected unit that has an asset form (an asset row, or a base unit with a
        // linked asset), each as its own card.
        if (bfsCardsPanel != null) {
            List<BattlefieldSupportAsset> assets = getSelectedAssetEntities().stream()
                  .filter(entity -> entity instanceof BattlefieldSupportAsset)
                  .map(entity -> (BattlefieldSupportAsset) entity)
                  .collect(Collectors.toList());
            bfsCardsPanel.setAssets(assets);
        }

        return selectedEntity;
    }

    /**
     * Enables the form-selection buttons for the current selection: "Select as Unit" (and, in callback mode, "Select &
     * Close") accepts every selected row's standard entity form, including a standalone Asset; "Select as Asset"
     * requires every selected row to have an asset form.
     */
    private void updateSelectFormButtons() {
        boolean hasSelection = hasSelectedRows();
        if (!offerAssetForm) {
            // Editing mode: a single "Select" opens the combined editor for any non-empty selection (a base unit with
            // its linked asset, or a standalone asset).
            if (buttonSelect != null) {
                buttonSelect.setEnabled(hasSelection);
            }
            if (buttonSelectClose != null) {
                buttonSelectClose.setEnabled(hasSelection);
            }
            return;
        }
        if (buttonSelect != null) {
            buttonSelect.setEnabled(hasSelection);
        }
        if (buttonSelectAsset != null) {
            buttonSelectAsset.setEnabled(selectionCanSelectAsAsset());
        }
        if (buttonSelectClose != null) {
            buttonSelectClose.setEnabled(hasSelection);
        }
    }
}
