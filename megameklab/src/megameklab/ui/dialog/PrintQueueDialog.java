/*
 * Copyright (c) 2010, 2022 - The MegaMek Team. All Rights Reserved.
 *
 * This file is part of MegaMekLab.
 *
 * MegaMek is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MegaMek is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MegaMek. If not, see <http://www.gnu.org/licenses/>.
 */
package megameklab.ui.dialog;

import megamek.client.ui.baseComponents.MMButton;
import megamek.client.ui.swing.UnitLoadingDialog;
import megamek.common.Entity;
import megamek.common.MechFileParser;
import megameklab.util.UnitPrintManager;
import org.apache.logging.log4j.LogManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Allows selecting multiple units and printing their record sheets.
 *
 * @author jtighe (torren@users.sourceforge.net)
 * @author Simon (Juliez)
 */
public class PrintQueueDialog extends AbstractMMLButtonDialog {

    private final boolean printToPdf;
    private final JButton addFromFileButton = new JButton("Add From File");
    private final JButton addFromCacheButton = new JButton("Add From Cache");
    private final JButton removeButton = new JButton("Remove Selected");
    private final JCheckBox oneUnitPerSheetCheck = new JCheckBox("Print each unit to a separate page");
    private final JFrame parent;
    private final List<Entity> units = new ArrayList<>();
    private final JList<String> queuedUnitList = new JList<>();

    public PrintQueueDialog(JFrame parent, boolean printToPdf) {
        super(parent, true, "PrintQueueDialog", "PrintQueueDialog.windowName.text");
        this.parent = parent;
        this.printToPdf = printToPdf;
        initialize();
    }

    @Override
    protected Container createCenterPane() {
        addFromCacheButton.addActionListener(e -> selectFromCache());
        addFromCacheButton.setMnemonic(KeyEvent.VK_A);
        addFromFileButton.addActionListener(e -> selectFromFile());
        addFromFileButton.setMnemonic(KeyEvent.VK_F);
        removeButton.addActionListener(e -> removeSelectedUnits());
        removeButton.setEnabled(false);
        removeButton.setMnemonic(KeyEvent.VK_R);
        oneUnitPerSheetCheck.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        oneUnitPerSheetCheck.setToolTipText("When unchecked, the record sheets for some unit types may be printed on the same page. " +
                "Note that the result may depend on whether reference tables are printed. This can be changed in the Settings.");
        queuedUnitList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        queuedUnitList.addListSelectionListener(e -> removeButton.setEnabled(!queuedUnitList.isSelectionEmpty()));
        queuedUnitList.setVisibleRowCount(15);

        JPanel buttonPanel = new FixedXYPanel(new GridLayout(4, 1));
        buttonPanel.add(new JLabel());
        buttonPanel.add(addFromCacheButton);
        buttonPanel.add(addFromFileButton);
        buttonPanel.add(removeButton);
        buttonPanel.setAlignmentY(JComponent.TOP_ALIGNMENT);
        JScrollPane queuedUnitListScrollPane = new JScrollPane(queuedUnitList);
        queuedUnitListScrollPane.setAlignmentY(JComponent.TOP_ALIGNMENT);
        queuedUnitListScrollPane.setBorder(new TitledBorder("Selected Units:"));

        Box centerPanel = Box.createHorizontalBox();
        centerPanel.add(buttonPanel);
        centerPanel.add(Box.createHorizontalStrut(30));
        centerPanel.add(queuedUnitListScrollPane);
        centerPanel.setBorder(new EmptyBorder(20, 30, 20, 30));

        Box panel = Box.createVerticalBox();
        panel.add(centerPanel);
        panel.add(oneUnitPerSheetCheck);
        panel.add(Box.createVerticalStrut(20));
        return panel;
    }

    @Override
    protected JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        var printButton = new MMButton("printButton", resources, "PrintQueueDialog.Print.text",
                "PrintQueueDialog.Print.toolTipText", this::okButtonActionPerformed);
        printButton.setMnemonic(KeyEvent.VK_P);
        panel.add(printButton);
        panel.add(new MMButton("cancelButton", resources, "PrintQueueDialog.Cancel.text",
                "PrintQueueDialog.Cancel.toolTipText", this::cancelActionPerformed));
        getRootPane().setDefaultButton(printButton);
        return panel;
    }

    private void refresh() {
        List<String> nameList = units.stream()
                .map(unit -> " " + unit.getChassis() + " " + unit.getModel())
                .collect(toList());

        var replacementModel = new DefaultListModel<String>();
        replacementModel.addAll(nameList);
        queuedUnitList.setModel(replacementModel);
    }

    @Override
    protected void okButtonActionPerformed(ActionEvent evt) {
        if (printToPdf) {
            File exportFile = UnitPrintManager.getExportFile(parent);
            if (exportFile != null) {
                UnitPrintManager.exportUnits(units, exportFile, oneUnitPerSheetCheck.isSelected());
            } else {
                return;
            }
        } else {
            UnitPrintManager.printAllUnits(units, oneUnitPerSheetCheck.isSelected());
        }
        super.okButtonActionPerformed(evt);
    }

    private void selectFromCache() {
        UnitLoadingDialog unitLoadingDialog = new UnitLoadingDialog(parent);
        unitLoadingDialog.setVisible(true);
        MegaMekLabUnitSelectorDialog viewer = new MegaMekLabUnitSelectorDialog(parent, unitLoadingDialog, this::entitySelected);
        Entity entity = viewer.getChosenEntity();
        viewer.dispose();

        if (entity != null) {
            units.add(entity);
            refresh();
        }
    }

    /**
     * This is a callback function given to the Unit Selector Dialog to pass on selected units
     * without closing the Unit Selector.
     *
     * @param entity the chosen Unit
     */
    public void entitySelected(Entity entity) {
        if (entity != null) {
            units.add(entity);
            refresh();
        }
    }

    private void selectFromFile() {
        String filePathName = System.getProperty("user.dir") + "/data/mechfiles/";

        JFileChooser f = new JFileChooser(filePathName);
        f.setLocation(parent.getLocation().x + 150, parent.getLocation().y + 100);
        f.setDialogTitle("Print Unit File");
        f.setMultiSelectionEnabled(true);

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Unit Files", "blk", "mtf");
        f.setFileFilter(filter);
        int returnVal = f.showOpenDialog(parent);
        if ((returnVal != JFileChooser.APPROVE_OPTION) || (f.getSelectedFile() == null)) {
            return;
        }

        for (File entityFile : f.getSelectedFiles()) {
            try {
                Entity tempEntity = new MechFileParser(entityFile).getEntity();
                units.add(tempEntity);
            } catch (Exception ex) {
                LogManager.getLogger().error("", ex);
            }
        }
        refresh();
    }

    private void removeSelectedUnits() {
        List<Entity> newList = new ArrayList<>();
        for (int i = 0; i < units.size(); i++) {
            final int index = i;
            if (Arrays.stream(queuedUnitList.getSelectedIndices()).noneMatch(idx -> idx == index)) {
                newList.add(units.get(i));
            }
        }
        units.clear();
        units.addAll(newList);
        refresh();
    }

    // TODO: Move to UIUtil
    public static class FixedXYPanel extends JPanel {

        public FixedXYPanel(LayoutManager layout) {
            super(layout);
        }

        public FixedXYPanel() {
            super();
        }

        @Override
        public Dimension getMaximumSize() {
            return new Dimension(getPreferredSize().width, getPreferredSize().height);
        }
    }
}