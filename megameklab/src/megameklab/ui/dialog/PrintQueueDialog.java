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
import megamek.common.BTObject;
import megamek.common.Configuration;
import megamek.common.Entity;
import megamek.common.MechFileParser;
import megameklab.printing.PageBreak;
import megameklab.util.UnitPrintManager;
import org.apache.logging.log4j.LogManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

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
    private final JButton addPageBreakButton = new JButton("Add Page Break");
    private final JButton removeButton = new JButton("Remove Selected");

    private final JButton moveTopButton = new JButton(icon("moveTop.png"));
    private final JButton moveUpButton = new JButton(icon("moveUp.png"));
    private final JButton moveDownButton = new JButton(icon("moveDown.png"));
    private final JButton moveBottomButton = new JButton(icon("moveBottom.png"));

    private final JCheckBox oneUnitPerSheetCheck = new JCheckBox("Print each unit to a separate page");
    private final JFrame parent;
    private final List<BTObject> units = new ArrayList<>();
    private final JList<String> queuedUnitList = new JList<>();

    private final boolean fromMul;

    public PrintQueueDialog(JFrame parent, boolean printToPdf, List<? extends BTObject> units, boolean fromMul) {
        super(parent, true, "PrintQueueDialog", "PrintQueueDialog.windowName.text");
        this.parent = parent;
        this.printToPdf = printToPdf;
        this.fromMul = fromMul;
        initialize();
        if (units != null) {
            this.units.addAll(units);
            refresh();
        }
    }

    public PrintQueueDialog(JFrame parent, boolean printToPdf) {
        this(parent, printToPdf, null, false);
    }

    private static ImageIcon icon(String name) {
        var path = Configuration.widgetsDir().toPath().resolve(name);
        try {
            return new ImageIcon(path.toUri().toURL());
        } catch (MalformedURLException e) {
            return null;
        }
    }

    @Override
    protected Container createCenterPane() {
        addFromCacheButton.addActionListener(e -> selectFromCache());
        addFromCacheButton.setMnemonic(KeyEvent.VK_A);
        addFromFileButton.addActionListener(e -> selectFromFile());
        addFromFileButton.setMnemonic(KeyEvent.VK_F);
        addPageBreakButton.addActionListener(e -> pageBreak());
        addPageBreakButton.setMnemonic(KeyEvent.VK_P);
        removeButton.addActionListener(e -> removeSelectedUnits());
        removeButton.setEnabled(false);
        removeButton.setMnemonic(KeyEvent.VK_R);

        moveTopButton.addActionListener(e -> moveTop());
        moveTopButton.setMnemonic(KeyEvent.VK_PAGE_UP);
        moveTopButton.setEnabled(false);
        moveBottomButton.addActionListener(e -> moveBottom());
        moveBottomButton.setMnemonic(KeyEvent.VK_PAGE_DOWN);
        moveBottomButton.setEnabled(false);
        moveUpButton.addActionListener(e -> moveUp());
        moveUpButton.setMnemonic(KeyEvent.VK_UP);
        moveUpButton.setEnabled(false);
        moveDownButton.addActionListener(e -> moveDown());
        moveDownButton.setMnemonic(KeyEvent.VK_DOWN);
        moveDownButton.setEnabled(false);

        oneUnitPerSheetCheck.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        oneUnitPerSheetCheck.setToolTipText("When unchecked, the record sheets for some unit types may be printed on the same page. " +
                "Note that the result may depend on whether reference tables are printed. This can be changed in the Settings.");
        queuedUnitList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        queuedUnitList.addListSelectionListener(new OnSelectionChanged());
        queuedUnitList.setVisibleRowCount(15);

        JPanel buttonPanel = new FixedXYPanel(new GridLayout(4, 1));
        if (!fromMul) {
            buttonPanel.add(addFromCacheButton);
            buttonPanel.add(addFromFileButton);
        }
        buttonPanel.add(addPageBreakButton);
        buttonPanel.add(removeButton);
        buttonPanel.setAlignmentY(JComponent.TOP_ALIGNMENT);

        JPanel moveButtonPanel = new FixedXYPanel(new GridLayout(4, 1));
        moveButtonPanel.add(moveTopButton);
        moveButtonPanel.add(moveUpButton);
        moveButtonPanel.add(moveDownButton);
        moveButtonPanel.add(moveBottomButton);
        moveButtonPanel.setAlignmentY(JComponent.TOP_ALIGNMENT);

        JScrollPane queuedUnitListScrollPane = new JScrollPane(queuedUnitList);
        queuedUnitListScrollPane.setAlignmentY(JComponent.TOP_ALIGNMENT);
        queuedUnitListScrollPane.setBorder(new TitledBorder("Selected Units:"));

        Box centerPanel = Box.createHorizontalBox();
        centerPanel.add(buttonPanel);
        centerPanel.add(Box.createHorizontalStrut(30));
        centerPanel.add(moveButtonPanel);
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
                .map(unit -> {
                    String title = String.format(" %s %s", unit.generalName(), unit.specificName());
                    if (fromMul && unit instanceof Entity) {
                        var crew = ((Entity) unit).getCrew();
                        title += String.format("  {%s %d/%d}", crew.getName(), crew.getGunnery(), crew.getPiloting());
                    }
                    return title;
                })
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

    private void pageBreak() {
        units.add(new PageBreak());
        refresh();
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
        List<BTObject> newList = new ArrayList<>();
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

    private void moveTop() {
        List<BTObject> newListTop = new ArrayList<>();
        List<BTObject> newListBottom = new ArrayList<>();
        boolean state = false;
        for (int i = 0; i < units.size(); i++) {
            if (i == topSelectedIndex()) {
                state = true;
            } else if (i > bottomSelectedIndex()) {
                state = false;
            }
            (state ? newListTop : newListBottom).add(units.get(i));
        }
        units.clear();
        units.addAll(newListTop);
        units.addAll(newListBottom);
        refresh();
        queuedUnitList.setSelectedIndices(IntStream.range(0, newListTop.size()).toArray());
    }

    private void moveBottom() {
        List<BTObject> newListBottom = new ArrayList<>();
        List<BTObject> newListTop = new ArrayList<>();
        boolean state = false;
        for (int i = 0; i < units.size(); i++) {
            if (i == topSelectedIndex()) {
                state = true;
            } else if (i > bottomSelectedIndex()) {
                state = false;
            }
            (state ? newListBottom : newListTop).add(units.get(i));
        }
        units.clear();
        units.addAll(newListTop);
        units.addAll(newListBottom);
        refresh();
        queuedUnitList.setSelectedIndices(IntStream.range(newListTop.size(), newListTop.size() + newListBottom.size()).toArray());
    }

    private void moveUp() {
        var unit = units.remove(topSelectedIndex() - 1);
        units.add(bottomSelectedIndex(), unit);
        var indices = queuedUnitList.getSelectedIndices();
        refresh();
        queuedUnitList.setSelectedIndices(Arrays.stream(indices).map(i -> i - 1).toArray());
    }

    private void moveDown() {
        var unit = units.remove(bottomSelectedIndex() + 1);
        units.add(topSelectedIndex(), unit);
        var indices = queuedUnitList.getSelectedIndices();
        refresh();
        queuedUnitList.setSelectedIndices(Arrays.stream(indices).map(i -> i + 1).toArray());
    }

    private int topSelectedIndex() {
        return queuedUnitList.getSelectedIndex();
    }

    private int bottomSelectedIndex() {
        var indices = queuedUnitList.getSelectedIndices();
        return indices[indices.length - 1];
    }

    private class OnSelectionChanged implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            removeButton.setEnabled(!queuedUnitList.isSelectionEmpty());

            if (!isSelectionContiguous()) {
                moveTopButton.setEnabled(false);
                moveUpButton.setEnabled(false);
                moveDownButton.setEnabled(false);
                moveBottomButton.setEnabled(false);
            } else {
                if (topSelectedIndex() == 0) {
                    moveTopButton.setEnabled(false);
                    moveUpButton.setEnabled(false);
                } else {
                    moveTopButton.setEnabled(true);
                    moveUpButton.setEnabled(true);
                }
                if (bottomSelectedIndex() == units.size() - 1) {
                    moveBottomButton.setEnabled(false);
                    moveDownButton.setEnabled(false);
                } else {
                    moveBottomButton.setEnabled(true);
                    moveDownButton.setEnabled(true);
                }
            }
        }

        private boolean isSelectionContiguous() {
            // getSelectedIndices is guaranteed to return the indices in ascending order
            var indices = queuedUnitList.getSelectedIndices();
            if (indices.length == 0) {
                return false;
            }

            var start = indices[0];
            var end = indices[indices.length - 1];
            return end - start == indices.length - 1;
        }
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