/*
 * Copyright (c) 2020 - The MegaMek Team. All rights reserved.
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
package megameklab.com.ui.dialog;

import megamek.client.ui.Messages;
import megamek.client.ui.swing.UnitLoadingDialog;
import megamek.client.ui.swing.dialog.AbstractUnitSelectorDialog;
import megamek.common.Entity;
import megamek.common.MechSummary;
import megamek.common.TechConstants;

import javax.swing.*;
import java.awt.*;

public class MegaMekLabUnitSelectorDialog extends AbstractUnitSelectorDialog {
    //region Variable Declarations
    private Entity chosenEntity;
    //endregion Variable Declarations

    public MegaMekLabUnitSelectorDialog(JFrame frame, UnitLoadingDialog unitLoadingDialog) {
        super(frame, unitLoadingDialog);
        gameTechLevel = TechConstants.T_SIMPLE_UNOFFICIAL;

        initialize();

        run();
        setVisible(true);
    }

    @Override
    public void updateOptionValues() {

    }

    //region Button Methods
    @Override
    protected JPanel createButtonsPanel() {
        JPanel panelButtons = new JPanel(new GridBagLayout());

        buttonSelectClose = new JButton(Messages.getString("MechSelectorDialog.m_bPickClose"));
        buttonSelectClose.addActionListener(this);
        panelButtons.add(buttonSelectClose, new GridBagConstraints());

        buttonClose = new JButton(Messages.getString("Close"));
        buttonClose.addActionListener(this);
        panelButtons.add(buttonClose, new GridBagConstraints());

        buttonShowBV = new JButton(Messages.getString("MechSelectorDialog.BV"));
        buttonShowBV.addActionListener(this);
        panelButtons.add(buttonShowBV, new GridBagConstraints());

        return panelButtons;
    }

    @Override
    protected void select(boolean close) {
        chosenEntity = getSelectedEntity();
        
        if (close) {
            setVisible(false);
        }
    }
    //endregion Button Methods

    /**
     * @return the MechSummary for the chosen mech
     */
    public MechSummary getChosenMechSummary() {
        int view = tableUnits.getSelectedRow();
        if (view < 0) {
            // selection got filtered away
            return null;
        }
        return mechs[tableUnits.convertRowIndexToModel(view)];
    }

    /**
     * @return the chosenEntity
     */
    public Entity getChosenEntity() {
        return chosenEntity;
    }
}
