/*
 * Copyright (c) 2024 - The MegaMek Team. All rights reserved.
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
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MegaMekLab.  If not, see <http://www.gnu.org/licenses/>.
 */
package megameklab.ui.dialog;

import megamek.client.ui.Messages;
import megamek.client.ui.swing.UnitLoadingDialog;
import megamek.client.ui.swing.dialog.AbstractUnitSelectorDialog;
import megamek.client.ui.swing.tileset.EntityImage;
import megamek.client.ui.swing.tileset.MMStaticDirectoryManager;
import megamek.client.ui.swing.util.PlayerColour;
import megamek.common.Entity;
import megamek.common.TechConstants;
import megamek.common.icons.Camouflage;
import megameklab.ui.generalUnit.RecordSheetPreviewPanel;
import megameklab.util.CConfig;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.function.Consumer;

public class MegaMekLabUnitSelectorDialog extends AbstractUnitSelectorDialog {
    // region Variable Declarations
    private Entity chosenEntity;
    private ArrayList<Entity> chosenEntities;
    private final boolean allowPickWithoutClose;
    private Consumer<MegaMekLabUnitSelectorDialog> entityPickCallback;
    private RecordSheetPreviewPanel recordSheetPanel;

    // endregion Variable Declarations

    /**
     * Constructs a Unit Selector Dialog that only allows choosing with closing the
     * dialog.
     *
     * @param parent The parent window of this dialog
     * @param unitLoadingDialog A {@link UnitLoadingDialog} likely {@code new UnitLoadingDialog(parent)}.
     * @param multiselect Set this to {@code true} to allow multiple units to be selected at once.
     */
    public MegaMekLabUnitSelectorDialog(JFrame parent, UnitLoadingDialog unitLoadingDialog, boolean multiselect) {
        super(parent, unitLoadingDialog, multiselect);
        gameTechLevel = TechConstants.T_SIMPLE_UNOFFICIAL;
        allowPickWithoutClose = false;
        eraBasedTechLevel = CConfig.getBooleanParam(CConfig.TECH_PROGRESSION);
        if (CConfig.getBooleanParam(CConfig.TECH_USE_YEAR)) {
            allowedYear = CConfig.getIntParam(CConfig.TECH_YEAR);
        }
        initialize();
        run();
        setVisible(true);
    }

    /**
     * Constructs a Unit Selector Dialog that allows choosing a Unit while keeping
     * the dialog open by pressing Enter or the "Select" button. The
     * entityPickCallback method will be called when units are selected in this way.
     * Multiselect is always enabled.
     *
     * @param parent The parent window of this dialog
     * @param unitLoadingDialog A {@link UnitLoadingDialog} likely {@code new UnitLoadingDialog(parent)}.
     * @param entityPickCallback This will be called when the user presses Select.
     */
    public MegaMekLabUnitSelectorDialog(JFrame parent, UnitLoadingDialog unitLoadingDialog,
            Consumer<MegaMekLabUnitSelectorDialog> entityPickCallback) {
        super(parent, unitLoadingDialog, true);
        gameTechLevel = TechConstants.T_SIMPLE_UNOFFICIAL;
        allowPickWithoutClose = true;
        eraBasedTechLevel = CConfig.getBooleanParam(CConfig.TECH_PROGRESSION);
        if (CConfig.getBooleanParam(CConfig.TECH_USE_YEAR)) {
            allowedYear = CConfig.getIntParam(CConfig.TECH_YEAR);
        }
        this.entityPickCallback = entityPickCallback;
        initialize();
        // This overrides the default close behavior to avoid selecting another unit
        // when closing with ESC or the Close button. AbstractUnitSelectorDialog should
        // probably be changed to make the selectedEntity null in these cases
        JRootPane rootPane = getRootPane();
        KeyStroke escape = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
        rootPane.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(escape, CLOSE_ACTION);
        rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(escape, CLOSE_ACTION);
        rootPane.getInputMap(JComponent.WHEN_FOCUSED).put(escape, CLOSE_ACTION);
        rootPane.getActionMap().put(CLOSE_ACTION, closeAction);
        run();
        setVisible(true);
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
            buttonSelect = new JButton(Messages.getString("MekSelectorDialog.m_bPick"));
            buttonSelect.addActionListener(this);
            panelButtons.add(buttonSelect, new GridBagConstraints());
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
        setVisible(false);
    }

    @Override
    protected void select(boolean close) {
        if (multiSelect) {
            chosenEntities = getSelectedEntities();
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
        return chosenEntities;
    }

    @Override
    protected Entity refreshUnitView() {
        Entity selectedEntity = super.refreshUnitView();
        
        // Get the EntityViewPane's tabbed pane
        if (recordSheetPanel == null) {
            // First time - create the record sheet panel and add it as a tab
            recordSheetPanel = new RecordSheetPreviewPanel();
            panePreview.addTab("Record Sheet", recordSheetPanel);
        }
        
        // Update the record sheet with the selected entity
        if (selectedEntity != null) {
            // Update unit image first (existing code)
            Image base = MMStaticDirectoryManager.getMekTileset().imageFor(selectedEntity);
            EntityImage entityImage = EntityImage.createIcon(base, Camouflage.of(PlayerColour.GOLD), selectedEntity);
            entityImage.loadFacings();
            labelImage.setIcon(new ImageIcon(entityImage.getFacing(0)));
            
            // Update the record sheet
            recordSheetPanel.setEntity(selectedEntity);
        } else {
            recordSheetPanel.setEntity(null);
        }
        
        return selectedEntity;
    }
}
