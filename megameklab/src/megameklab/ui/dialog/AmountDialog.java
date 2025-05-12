/*
 * Copyright (C) 2025 The MegaMek Team. All Rights Reserved.
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
package megameklab.ui.dialog;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ResourceBundle;

import javax.swing.Action;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.KeyStroke;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import megamek.client.ui.enums.DialogResult;

/**
 * This is a dialog for entering an amount. It is used in various places in MML, such as when
 * transferring items between units.
 *
 * @author drake
 */
public class AmountDialog extends AbstractMMLButtonDialog {
    private static final ResourceBundle resources = ResourceBundle.getBundle("megameklab.resources.Dialogs");

    private int amount;
    private JSpinner amountSpinner;
    private final String itemname;
    private final int maxAmount;

    private AmountDialog(JFrame frame, String itemname, int maxAmount) {
        super(frame, "AmountDialog", "AmountDialog.title.text");
        this.itemname = itemname;
        this.maxAmount = maxAmount;
        this.amount = maxAmount;
        initialize();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                if (amountSpinner != null) {
                    SwingUtilities.invokeLater(() -> amountSpinner.requestFocusInWindow());
                }
            }
        });
    }

    public static int showDialog(JFrame frame, String itemname, int maxAmount) {
        try {
            AmountDialog dialog = new AmountDialog(frame, itemname, maxAmount);
            try {
                dialog.setModal(true);
                dialog.setLocationRelativeTo(frame);
                dialog.setVisible(true);
                if (dialog.getResult() != DialogResult.CONFIRMED) {
                    return 0;
                }
                return dialog.getAmount();
            } finally {
                dialog.dispose();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getAmount() {
        return amount;
    }

    @Override
    protected Container createCenterPane() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add some padding

        final String bodyText = resources.getString("AmountDialog.body.text").replace("{0}", this.itemname);
        JLabel bodyLabel = new JLabel(bodyText);
        bodyLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(bodyLabel, BorderLayout.NORTH);

        int spinnerInitialValue = (this.maxAmount < 0) ? 0 : this.maxAmount;
        if (this.amount < 0) this.amount = 0;
        if (this.amount > spinnerInitialValue) this.amount = spinnerInitialValue;

        SpinnerNumberModel spinnerModel = new SpinnerNumberModel(this.amount, 0, spinnerInitialValue, 1);
        amountSpinner = new JSpinner(spinnerModel);
        amountSpinner.setName("amountSpinner");
        
        Dimension spinnerSize = amountSpinner.getPreferredSize();
        spinnerSize.width = Math.max(spinnerSize.width, 60);
        amountSpinner.setPreferredSize(spinnerSize);

        Action enterAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                okButtonActionPerformed(e);
            }
        };

        JComponent editor = amountSpinner.getEditor();
        if (editor instanceof JSpinner.DefaultEditor) {
            JFormattedTextField textField = ((JSpinner.DefaultEditor) editor).getTextField();
            textField.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "confirmOnEnter");
            textField.getActionMap().put("confirmOnEnter", enterAction);
        }
        
        JPanel spinnerWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        spinnerWrapper.add(amountSpinner);
        JLabel maxAmountLabel = new JLabel("/ " + spinnerInitialValue);
        spinnerWrapper.add(maxAmountLabel);

        panel.add(spinnerWrapper, BorderLayout.CENTER);
        
        return panel;
    }

    @Override
    protected void okAction() {
        if (amountSpinner != null) {
            this.amount = (Integer) amountSpinner.getValue();
        }
        super.okAction();
    }

    @Override
    protected void cancelAction() {
        super.cancelAction();
    }
    
}
