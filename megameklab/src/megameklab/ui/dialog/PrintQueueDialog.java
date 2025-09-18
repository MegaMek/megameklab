/*
 * Copyright (C) 2010-2025 The MegaMek Team. All Rights Reserved.
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

import static java.util.stream.Collectors.toList;
import static megamek.client.ui.clientGUI.ClientGUI.CG_FILEPATH_MUL;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import megamek.client.generator.RandomNameGenerator;
import megamek.client.ui.Messages;
import megamek.client.ui.buttons.MMButton;
import megamek.client.ui.dialogs.UnitLoadingDialog;
import megamek.common.Configuration;
import megamek.common.Player;
import megamek.common.battleArmor.BattleArmor;
import megamek.common.game.Game;
import megamek.common.loaders.MekFileParser;
import megamek.common.units.Aero;
import megamek.common.units.BTObject;
import megamek.common.units.Entity;
import megamek.common.units.EntityListFile;
import megamek.common.units.FixedWingSupport;
import megamek.common.units.Infantry;
import megamek.common.units.Mek;
import megamek.common.units.ProtoMek;
import megamek.common.units.Tank;
import megamek.common.util.C3Util;
import megamek.logging.MMLogger;
import megameklab.printing.PageBreak;
import megameklab.printing.RecordSheetOptions;
import megameklab.ui.generalUnit.RecordSheetPreviewPanel;
import megameklab.util.CConfig;
import megameklab.util.UnitPrintManager;
import megameklab.util.UnitUtil;
import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.util.Strings;

/**
 * Allows selecting multiple units and printing their record sheets.
 *
 * @author jtighe (torren@users.sourceforge.net)
 * @author Simon (Juliez)
 */
public class PrintQueueDialog extends AbstractMMLButtonDialog {
    private static final MMLogger logger = MMLogger.create(PrintQueueDialog.class);

    private final boolean printToPdf;
    private final JButton addFromFileButton = new JButton("Add From File");
    private final JButton addFromCacheButton = new JButton("Add From Cache");
    private final JButton addPageBreakButton = new JButton("Add Page Break");
    private final JButton removeButton = new JButton("Remove Selected");
    private final JButton sortButton = new JButton("Sort");
    private final JButton saveButton = new JButton("Save Unit List");

    private final JButton moveTopButton = new JButton(icon("moveTop.png"));
    private final JButton moveUpButton = new JButton(icon("moveUp.png"));
    private final JButton moveDownButton = new JButton(icon("moveDown.png"));
    private final JButton moveBottomButton = new JButton(icon("moveBottom.png"));

    private final JCheckBox oneUnitPerSheetCheck = new JCheckBox("Print each unit to a separate page");
    private final JCheckBox showPilotDataCheck = new JCheckBox("Print crew data if available");
    private final JCheckBox adjustedBvCheck = new JCheckBox("Print force-adjusted BV (C3 network)");
    private final JCheckBox showDamageCheck = new JCheckBox("Print damage");
    private final JFrame parent;
    private final List<BTObject> units = new ArrayList<>();
    private final JList<String> queuedUnitList = new JList<>();
    private final RecordSheetPreviewPanel recordSheetPanel = new RecordSheetPreviewPanel();

    private final boolean fromMul;

    private final String mulFileName;

    public PrintQueueDialog(JFrame parent, boolean printToPdf, List<? extends BTObject> units, boolean fromMul,
          String mulFileName) {
        super(parent,
              true,
              "PrintQueueDialog",
              printToPdf ? "PrintQueueDialog.windowNameExport.text" : "PrintQueueDialog.windowNamePrint.text");
        this.parent = parent;
        this.printToPdf = printToPdf;
        this.fromMul = fromMul;
        this.mulFileName = mulFileName;
        recordSheetPanel.setFullAsyncMode(true);
        initialize();
        if (units != null) {
            this.units.addAll(units);
            refresh();
        }
    }

    public PrintQueueDialog(JFrame parent, boolean printToPdf) {
        this(parent, printToPdf, null, false, "");
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
        sortButton.addActionListener(e -> sortAllUnits());
        sortButton.setToolTipText("Sort all units by type, tech-base, weight, name.");
        sortButton.setMnemonic(KeyEvent.VK_O);
        saveButton.addActionListener(e -> saveUnitList());
        saveButton.setMnemonic(KeyEvent.VK_S);

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
        oneUnitPerSheetCheck.setToolTipText(
              "When unchecked, the record sheets for some unit types may be printed on the same page. " +
                    "Note that the result may depend on whether reference tables are printed. This can be changed in the Settings.");
        oneUnitPerSheetCheck.addActionListener(e -> recordSheetPanel.setOneUnitPerSheet(oneUnitPerSheetCheck.isSelected()));

        showPilotDataCheck.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        showPilotDataCheck.setToolTipText(
              "When checked, pilot data will be printed if available. BV will be adjusted for pilot skills.");
        showPilotDataCheck.addActionListener(e -> recordSheetPanel.showPilotData(showPilotDataCheck.isSelected()));

        adjustedBvCheck.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        adjustedBvCheck.setToolTipText("When checked, printed BV is adjusted for force modifiers (C3, TAG, etc.).");
        adjustedBvCheck.addActionListener(e -> recordSheetPanel.includeC3inBV(adjustedBvCheck.isSelected()));

        showDamageCheck.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        showDamageCheck.setToolTipText("When checked, damage will be shown on the Record Sheet.");
        showDamageCheck.addActionListener(e -> recordSheetPanel.showDamage(showDamageCheck.isSelected()));

        queuedUnitList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        queuedUnitList.addListSelectionListener(new OnSelectionChanged());
        queuedUnitList.setVisibleRowCount(15);

        JPanel buttonPanel = new FixedXYPanel(new GridLayout(5, 2));
        if (!fromMul) {
            saveButton.setEnabled(false);
        }
        buttonPanel.add(addFromCacheButton);
        buttonPanel.add(addFromFileButton);
        buttonPanel.add(addPageBreakButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(sortButton);
        buttonPanel.add(saveButton);
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

        Box unitsPanel = Box.createHorizontalBox();
        unitsPanel.add(moveButtonPanel);
        unitsPanel.add(queuedUnitListScrollPane);

        JPanel checkboxPanel = new FixedXYPanel(new GridLayout(4, 1));
        checkboxPanel.add(oneUnitPerSheetCheck);
        oneUnitPerSheetCheck.setSelected(CConfig.getBooleanParam(CConfig.PQ_SINGLE_PRINT));
        checkboxPanel.add(showPilotDataCheck);
        showPilotDataCheck.setSelected(CConfig.getBooleanParam(CConfig.PQ_SHOW_PILOT_DATA));
        checkboxPanel.add(adjustedBvCheck);
        adjustedBvCheck.setSelected(CConfig.getBooleanParam(CConfig.PQ_ADJUSTED_BV));
        checkboxPanel.add(showDamageCheck);
        showDamageCheck.setSelected(CConfig.getBooleanParam(CConfig.PQ_DAMAGE));
        checkboxPanel.setAlignmentY(JComponent.TOP_ALIGNMENT);

        // Set RS settings from initial state of the checkboxes
        recordSheetPanel.setOneUnitPerSheet(oneUnitPerSheetCheck.isSelected());
        recordSheetPanel.showPilotData(showPilotDataCheck.isSelected());
        recordSheetPanel.includeC3inBV(adjustedBvCheck.isSelected());
        recordSheetPanel.showDamage(showDamageCheck.isSelected());

        Box buttonPanelWithCheckboxes = Box.createHorizontalBox();
        buttonPanelWithCheckboxes.add(buttonPanel);
        buttonPanelWithCheckboxes.add(Box.createHorizontalStrut(30));
        buttonPanelWithCheckboxes.add(checkboxPanel);
        buttonPanelWithCheckboxes.setAlignmentY(JComponent.TOP_ALIGNMENT);

        Box leftPanel = Box.createVerticalBox();
        leftPanel.add(unitsPanel);
        leftPanel.add(Box.createVerticalStrut(30));
        leftPanel.add(buttonPanelWithCheckboxes);

        JPanel recordSheetContainer = new JPanel(new BorderLayout());
        recordSheetContainer.add(recordSheetPanel, BorderLayout.CENTER);
        recordSheetContainer.setMinimumSize(new Dimension(400, 200));
        recordSheetContainer.setPreferredSize(new Dimension(600, 200));

        Box centerPanel = Box.createHorizontalBox();
        centerPanel.add(leftPanel);
        centerPanel.add(Box.createHorizontalStrut(15));
        centerPanel.add(recordSheetContainer);
        centerPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        return centerPanel;
    }

    @Override
    protected JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        var printButton = new MMButton("printButton",
              resources,
              printToPdf ? "PrintQueueDialog.Export.text" : "PrintQueueDialog.Print.text",
              printToPdf ? "PrintQueueDialog.Export.toolTipText" : "PrintQueueDialog.Print.toolTipText",
              this::okButtonActionPerformed);
        printButton.setMnemonic(KeyEvent.VK_P);
        panel.add(printButton);
        panel.add(new MMButton("cancelButton",
              resources,
              "PrintQueueDialog.Cancel.text",
              "PrintQueueDialog.Cancel.toolTipText",
              this::cancelActionPerformed));
        getRootPane().setDefaultButton(printButton);
        return panel;
    }

    private void refresh() {
        List<String> nameList = units.stream().map(unit -> {
            String title = String.format(" %s %s", unit.generalName(), unit.specificName());
            if (fromMul && unit instanceof Entity) {
                var crew = ((Entity) unit).getCrew();
                if (!crew.getName().startsWith(RandomNameGenerator.UNNAMED)) {
                    title += String.format("  {%s %d/%d}", crew.getName(), crew.getGunnery(), crew.getPiloting());
                }
            }
            return title;
        }).collect(toList());

        var replacementModel = new DefaultListModel<String>();
        replacementModel.addAll(nameList);
        queuedUnitList.setModel(replacementModel);

        saveButton.setEnabled(units.stream().anyMatch(unit -> unit instanceof Entity));
        recordSheetPanel.setEntities(units);
    }

    private void linkForce() {
        Game g = new Game();
        Player p = new Player(1, "Nobody");
        for (Entity e : getEntities()) {
            if (e.getId() == -1) {
                e.setId(g.getNextEntityId());
            }
            e.setOwner(p);
            g.addEntity(e);
            C3Util.wireC3(g, e);
        }
    }

    private void unlinkForce() {
        int id = 1;
        for (Entity e : getEntities()) {
            e.setOwner(new Player(id++, "Nobody"));
        }
    }

    @Override
    protected void okButtonActionPerformed(ActionEvent evt) {
        RecordSheetOptions options = recordSheetPanel.getRecordSheetOptions();
        if (fromMul) {
            if (adjustedBvCheck.isSelected()) {
                linkForce();
            } else {
                unlinkForce();
            }
        }
        options.setC3inBV(adjustedBvCheck.isSelected());
        options.setPilotData(showPilotDataCheck.isSelected());
        options.setDamage(showDamageCheck.isSelected());
        CConfig.setParam(CConfig.PQ_SINGLE_PRINT, String.valueOf(oneUnitPerSheetCheck.isSelected()));
        CConfig.setParam(CConfig.PQ_SHOW_PILOT_DATA, String.valueOf(showPilotDataCheck.isSelected()));
        CConfig.setParam(CConfig.PQ_ADJUSTED_BV, String.valueOf(adjustedBvCheck.isSelected()));
        CConfig.setParam(CConfig.PQ_DAMAGE, String.valueOf(showDamageCheck.isSelected()));
        CConfig.saveConfig();

        if (printToPdf) {
            File exportFile;
            if (mulFileName.isBlank()) {
                exportFile = UnitPrintManager.getExportFile(parent);
            } else {
                exportFile = UnitPrintManager.getExportFile(parent,
                      FilenameUtils.removeExtension(mulFileName) + ".pdf");
            }
            if (exportFile != null) {
                UnitPrintManager.exportUnits(units, exportFile, oneUnitPerSheetCheck.isSelected(), options);
            } else {
                return;
            }
        } else {
            if (!UnitPrintManager.printAllUnits(units, oneUnitPerSheetCheck.isSelected(), options)) {
                return;
            }
        }
        super.okButtonActionPerformed(evt);
    }

    private void saveUnitList() {
        var entities = getEntities();
        if (entities.isEmpty()) {
            return;
        }

        // If the first entity has no game, we link them all to a game. This is needed for C3 links.
        boolean forcedLink = false;
        if (entities.get(0).getGame() == null) {
            forcedLink = true;
            linkForce();
        }
        try {
            var fileChooser = new JFileChooser(".");
            fileChooser.setDialogTitle(Messages.getString("ClientGUI.saveUnitListFileDialog.title"));
            var filter = new FileNameExtensionFilter(Messages.getString("ClientGUI.descriptionMULFiles"),
                  CG_FILEPATH_MUL);
            fileChooser.setFileFilter(filter);
            fileChooser.setSelectedFile(new File(Strings.isNotBlank(mulFileName) ?
                  mulFileName :
                  entities.get(0).getShortName() + " etc." + CG_FILEPATH_MUL));

            if (!(fileChooser.showSaveDialog(parent) == JFileChooser.APPROVE_OPTION) ||
                  fileChooser.getSelectedFile() == null) {
                return;
            }

            File file = fileChooser.getSelectedFile();
            if (!FilenameUtils.getExtension(file.getName()).equalsIgnoreCase(CG_FILEPATH_MUL)) {
                file = new File(file + "." + CG_FILEPATH_MUL);
            }

            try {
                EntityListFile.saveTo(file, entities);
            } catch (IOException e) {
                logger.errorDialog(e, "Failed to save units to file: {}", "Error", e.getMessage());
            }
        } finally {
            if (forcedLink) {
                unlinkForce();
            }
        }
    }

    private void pageBreak() {
        units.add(new PageBreak());
        refresh();
    }

    private void selectFromCache() {
        UnitLoadingDialog unitLoadingDialog = new UnitLoadingDialog(parent);
        unitLoadingDialog.setVisible(true);
        MegaMekLabUnitSelectorDialog viewer = new MegaMekLabUnitSelectorDialog(parent,
              unitLoadingDialog,
              dialog -> entitiesSelected(dialog.getChosenEntities()));
        var entities = viewer.getChosenEntities();
        unitLoadingDialog.dispose();
        viewer.dispose();
        entitiesSelected(entities);
    }

    /**
     * This is a callback function given to the Unit Selector Dialog to pass on selected units without closing the Unit
     * Selector.
     *
     * @param entities the chosen Units
     */
    private void entitiesSelected(List<Entity> entities) {
        for (var entity : entities) {
            if (entity != null) {
                units.add(entity);
            }
        }
        refresh();
    }

    private void selectFromFile() {
        String filePathName = System.getProperty("user.dir") + "/data/mekfiles/";

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
                Entity tempEntity = new MekFileParser(entityFile).getEntity();
                units.add(tempEntity);
            } catch (Exception ex) {
                logger.error("", ex);
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


    private void sortAllUnits() {
        units.removeIf(o -> !(o instanceof Entity));
        var ba = new ArrayList<Entity>();
        var ci = new ArrayList<Entity>();
        var cv = new ArrayList<Entity>();
        var mek = new ArrayList<Entity>();
        var proto = new ArrayList<Entity>();
        var aero = new ArrayList<Entity>();
        var others = new ArrayList<Entity>();
        for (BTObject u : units) {
            var e = (Entity) u;
            if (e instanceof BattleArmor) {
                ba.add(e);
            } else if (e instanceof Infantry) {
                ci.add(e);
            } else if (e instanceof Tank || e instanceof FixedWingSupport) {
                cv.add(e);
            } else if (e instanceof Mek) {
                mek.add(e);
            } else if (e instanceof ProtoMek) {
                proto.add(e);
            } else if (e instanceof Aero) {
                aero.add(e);
            } else {
                others.add(e);
            }
        }

        for (var list : List.of(ba, ci, cv, mek, proto, aero, others)) {
            list.sort(Comparator.comparing(Entity::isClan).reversed()
                        .thenComparingDouble(Entity::getWeight)
                        .thenComparing(UnitUtil::getPrintName));
        }

        units.clear();

        // Putting a page break after each unit type makes it so "incomplete" small unit sheets don't get pushed to
        // the end, since those normally wait until they're full to be processed.
        if (!ba.isEmpty()) {
            units.addAll(ba);
            units.add(new PageBreak());
        }
        if (!ci.isEmpty()) {
            units.addAll(ci);
            units.add(new PageBreak());
        }
        // Add a page break after every tank since we want one per page in official RS books
        for (var vehicle : cv) {
            units.add(vehicle);
            units.add(new PageBreak());
        }
        if (!mek.isEmpty()) {
            units.addAll(mek);
            units.add(new PageBreak());
        }
        if (!proto.isEmpty()) {
            units.addAll(proto);
            units.add(new PageBreak());
        }
        if (!aero.isEmpty()) {
            units.addAll(aero);
            units.add(new PageBreak());
        }
        units.addAll(others);

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
        queuedUnitList.setSelectedIndices(IntStream.range(newListTop.size(), newListTop.size() + newListBottom.size())
              .toArray());
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

    /**
     * Gets all the Entities currently queued (i.e., with page breaks removed).
     */
    private ArrayList<Entity> getEntities() {
        return new ArrayList<>(units.stream().filter(i -> i instanceof Entity).map(i -> (Entity) i).toList());
    }

    private class OnSelectionChanged implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            removeButton.setEnabled(!queuedUnitList.isSelectionEmpty());
            sortButton.setEnabled(!units.isEmpty());

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

    @Override
    protected void okAction() {
        dispose();
    }

    @Override
    protected void cancelAction() {
        dispose();
    }

    // TODO: Move to UIUtil
    public static class FixedXYPanel extends JPanel {
        public FixedXYPanel(LayoutManager layout) {
            super(layout);
        }

        @Override
        public Dimension getMaximumSize() {
            return new Dimension(getPreferredSize().width, getPreferredSize().height);
        }
    }
}
