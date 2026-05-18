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
 * MechWarrior Copyright Microsoft Corporation. MegaMek was created under
 * Microsoft's "Game Content Usage Rules"
 * <https://www.xbox.com/en-US/developers/rules> and it is not endorsed by or
 * affiliated with Microsoft.
 */
package megameklab.ui.dialog;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;

import megamek.common.SourceBook;
import megamek.common.SourceBooks;
import megamek.common.loaders.MekSummary;
import megamek.common.loaders.MekSummaryCache;
import megamek.logging.MMLogger;

/**
 * Author: Drake
 * 
 * This dialog allows editing of sourcebook information stored in the data/sourcebooks folder.
 * Should be used with caution as changes will affect all units that reference the edited sourcebook.
 */
public class SourcebookEditorDialog extends AbstractMMLDialog {

    private static final MMLogger LOGGER = MMLogger.create(SourcebookEditorDialog.class);
    private static final Insets FIELD_INSETS = new Insets(2, 2, 2, 2);
    private static SourcebookEditorDialog sourcebookEditorDialog;

    private final SourceBooks sourceBooks = new SourceBooks();
    private final SourcebookTableModel sourcebookTableModel = new SourcebookTableModel();
    private final TableRowSorter<SourcebookTableModel> sourcebookSorter = new TableRowSorter<>(sourcebookTableModel);
    private final JTable sourcebookTable = new JTable(sourcebookTableModel);
    private final JTextField txtFilter = new JTextField(24);
    private final JTextField txtFileName = new JTextField();
    private final JTextField txtId = new JTextField();
    private final JTextField txtSku = new JTextField();
    private final JTextField txtTitle = new JTextField();
    private final JTextField txtAbbrev = new JTextField();
    private final JTextField txtImage = new JTextField();
    private final JTextField txtUrl = new JTextField();
    private final JCheckBox chkIsPublished = new JCheckBox("Published");
    private final JCheckBox chkCanon = new JCheckBox("Canon");
    private final JTextField txtMulUrl = new JTextField();
    private final JTextArea txtDescription = new JTextArea(10, 42);
    private final JLabel lblValidationSummary = new JLabel();
    private final JTextArea txtValidationLog = new JTextArea(6, 42);
    private final JButton btnCheckUnitSources = new JButton("Check Unit Sources");
    private final List<String> sourcebookValidationWarnings = new ArrayList<>();
    private final List<String> unitSourceValidationWarnings = new ArrayList<>();
    private SourcebookState savedSourcebookState = SourcebookState.empty();
    private boolean updatingSelection = false;
    private boolean unitSourceScanComplete = false;
    private boolean unitSourceScanRunning = false;

    public static void showDialog(JFrame frame) {
        if ((sourcebookEditorDialog == null) || !sourcebookEditorDialog.isDisplayable()) {
            sourcebookEditorDialog = new SourcebookEditorDialog(frame);
        }
        sourcebookEditorDialog.setVisible(true);
        sourcebookEditorDialog.toFront();
        sourcebookEditorDialog.requestFocus();
    }

    private SourcebookEditorDialog(JFrame frame) {
        super(frame, false, "SourcebookEditorDialog", "SourcebookEditorDialog.title");
        loadSourcebooks(null);
        initialize();
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        selectFirstSourcebook();
    }

    @Override
    protected Container createCenterPane() {
        JPanel panel = new JPanel(new BorderLayout(8, 8));
        panel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, createSourcebookListPane(),
              createEditorPane());
        splitPane.setResizeWeight(0.45);
        panel.add(splitPane, BorderLayout.CENTER);

        JPanel footerPanel = new JPanel(new BorderLayout(4, 4));
        footerPanel.add(createValidationPane(), BorderLayout.CENTER);
        footerPanel.add(createButtonPane(), BorderLayout.PAGE_END);
        panel.add(footerPanel, BorderLayout.PAGE_END);
        return panel;
    }

    private JPanel createValidationPane() {
        lblValidationSummary.setName("lblValidationSummary");

        txtValidationLog.setName("txtValidationLog");
        txtValidationLog.setEditable(false);
        txtValidationLog.setLineWrap(true);
        txtValidationLog.setWrapStyleWord(true);

        btnCheckUnitSources.setName("btnCheckUnitSources");
        btnCheckUnitSources.addActionListener(event -> checkUnitSourceReferences());

        JPanel headerPanel = new JPanel(new BorderLayout(4, 4));
        headerPanel.add(lblValidationSummary, BorderLayout.CENTER);
        headerPanel.add(btnCheckUnitSources, BorderLayout.LINE_END);

        JPanel panel = new JPanel(new BorderLayout(4, 4));
        panel.setBorder(BorderFactory.createTitledBorder("Validation"));
        panel.add(headerPanel, BorderLayout.PAGE_START);
        panel.add(new JScrollPane(txtValidationLog), BorderLayout.CENTER);
        updateValidationDisplay();
        return panel;
    }

    private JPanel createSourcebookListPane() {
        sourcebookTable.setName("sourcebookTable");
        sourcebookTable.setRowSorter(sourcebookSorter);
        sourcebookSorter.setSortKeys(List.of(new RowSorter.SortKey(SourcebookTableModel.COL_TITLE,
              SortOrder.ASCENDING)));
        sourcebookTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        configureSourcebookTableColumns();
        sourcebookTable.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting() && !updatingSelection) {
                selectedSourcebookChanged();
            }
        });

        txtFilter.setName("txtFilter");
        txtFilter.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent event) {
                updateFilter();
            }

            @Override
            public void removeUpdate(DocumentEvent event) {
                updateFilter();
            }

            @Override
            public void changedUpdate(DocumentEvent event) {
                updateFilter();
            }
        });

        JPanel filterPane = new JPanel(new BorderLayout(4, 4));
        filterPane.add(new JLabel("Filter:"), BorderLayout.LINE_START);
        filterPane.add(txtFilter, BorderLayout.CENTER);

        JPanel panel = new JPanel(new BorderLayout(4, 4));
        panel.add(filterPane, BorderLayout.PAGE_START);
        panel.add(new JScrollPane(sourcebookTable), BorderLayout.CENTER);
        return panel;
    }

    private void configureSourcebookTableColumns() {
        sourcebookTable.getColumnModel().getColumn(SourcebookTableModel.COL_SKU).setPreferredWidth(90);
        sourcebookTable.getColumnModel().getColumn(SourcebookTableModel.COL_TITLE).setPreferredWidth(360);
        sourcebookTable.getColumnModel().getColumn(SourcebookTableModel.COL_ABBREV).setPreferredWidth(90);
        sourcebookTable.getColumnModel().getColumn(SourcebookTableModel.COL_CANON).setPreferredWidth(65);
        sourcebookTable.getColumnModel().getColumn(SourcebookTableModel.COL_CANON).setMaxWidth(90);
    }

    private JPanel createEditorPane() {
        txtDescription.setLineWrap(true);
        txtDescription.setWrapStyleWord(true);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = FIELD_INSETS;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;

        int row = 0;
        addField(panel, constraints, row++, "File:", txtFileName);
        addField(panel, constraints, row++, "MUL ID:", txtId);
        addField(panel, constraints, row++, "SKU:", txtSku);
        addField(panel, constraints, row++, "Title:", txtTitle);
        addField(panel, constraints, row++, "Abbrev:", txtAbbrev);
        addBrowseField(panel, constraints, row++, "Image:", txtImage);
        addBrowseField(panel, constraints, row++, "URL:", txtUrl);
        JPanel checkboxPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        checkboxPanel.add(chkIsPublished);
        checkboxPanel.add(Box.createHorizontalStrut(12));
        checkboxPanel.add(chkCanon);
        constraints.gridx = 1;
        constraints.gridy = row++;
        constraints.weightx = 1;
        panel.add(checkboxPanel, constraints);

        addBrowseField(panel, constraints, row++, "MUL URL:", txtMulUrl);

        constraints.gridx = 0;
        constraints.gridy = row;
        constraints.weightx = 0;
        constraints.weighty = 1;
        constraints.anchor = GridBagConstraints.NORTHWEST;
        panel.add(new JLabel("Description:"), constraints);

        constraints.gridx = 1;
        constraints.weightx = 1;
        constraints.fill = GridBagConstraints.BOTH;
        panel.add(new JScrollPane(txtDescription), constraints);

        return panel;
    }

    private void addField(JPanel panel, GridBagConstraints constraints, int row, String label, JTextField textField) {
        addFieldComponent(panel, constraints, row, label, textField);
    }

    private void addBrowseField(JPanel panel, GridBagConstraints constraints, int row, String label,
          JTextField textField) {
        JButton openButton = new JButton("Open");
        openButton.addActionListener(event -> openUrl(textField));

        JPanel fieldPanel = new JPanel(new BorderLayout(4, 0));
        fieldPanel.add(textField, BorderLayout.CENTER);
        fieldPanel.add(openButton, BorderLayout.LINE_END);
        addFieldComponent(panel, constraints, row, label, fieldPanel);
    }

    private void addFieldComponent(JPanel panel, GridBagConstraints constraints, int row, String label,
          Component field) {
        constraints.gridx = 0;
        constraints.gridy = row;
        constraints.weightx = 0;
        constraints.weighty = 0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        panel.add(new JLabel(label), constraints);

        constraints.gridx = 1;
        constraints.weightx = 1;
        panel.add(field, constraints);
    }

    private void openUrl(JTextField textField) {
        String url = textField.getText().trim();
        if (url.isBlank()) {
            return;
        }

        try {
            if (!Desktop.isDesktopSupported() || !Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                JOptionPane.showMessageDialog(this, "Opening browser links is not supported on this system.",
                      "Sourcebooks", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Desktop.getDesktop().browse(URI.create(url));
        } catch (Exception exception) {
            LOGGER.error("", exception);
            JOptionPane.showMessageDialog(this, exception.getMessage(), "Sourcebooks", JOptionPane.ERROR_MESSAGE);
        }
    }

    private JPanel createButtonPane() {
        JButton newButton = new JButton("New");
        newButton.addActionListener(event -> beginNewSourcebook());

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(event -> saveSourcebook());

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(event -> closeDialog());

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel.add(newButton);
        panel.add(Box.createHorizontalStrut(12));
        panel.add(saveButton);
        panel.add(closeButton);
        return panel;
    }

    @Override
    protected void cancelActionPerformed(ActionEvent event) {
        closeDialog();
    }

    @Override
    public void windowClosing(WindowEvent event) {
        closeDialog();
    }

    private void closeDialog() {
        if (confirmSaveDiscardOrCancel()) {
            dispose();
        }
    }

    @Override
    public void windowClosed(WindowEvent event) {
        if (sourcebookEditorDialog == this) {
            sourcebookEditorDialog = null;
        }
    }

    private void updateFilter() {
        String filterText = txtFilter.getText().trim().toLowerCase(Locale.ROOT);
        if (filterText.isBlank()) {
            sourcebookSorter.setRowFilter(null);
            return;
        }

        sourcebookSorter.setRowFilter(new RowFilter<>() {
            @Override
            public boolean include(Entry<? extends SourcebookTableModel, ? extends Integer> entry) {
                return sourcebookTableModel.getRow(entry.getIdentifier()).matches(filterText);
            }
        });
    }

    private void loadSourcebooks(String selectedSourcebookKey) {
        List<SourcebookRow> rows = new ArrayList<>();
        for (String sourcebookKey : sourceBooks.availableSourcebooks()) {
            sourceBooks.loadSourceBook(sourcebookKey)
                  .map(sourceBook -> new SourcebookRow(sourcebookKey, sourceBook))
                  .ifPresent(rows::add);
        }
        rows.sort(Comparator.comparing(SourcebookRow::titleForSort, String.CASE_INSENSITIVE_ORDER));
        sourcebookTableModel.setRows(rows);
        refreshSourcebookValidationWarnings(rows);
        updateFilter();
        if (selectedSourcebookKey != null) {
            selectSourcebook(selectedSourcebookKey);
        }
    }

    private void refreshSourcebookValidationWarnings(List<SourcebookRow> rows) {
        sourcebookValidationWarnings.clear();
        addDuplicateIdWarnings(rows);
        addDuplicateTextWarnings(rows, "SKU", SourceBook::getSku);
        addDuplicateTextWarnings(rows, "abbrev", SourceBook::getAbbrev);
        addDuplicateTextWarnings(rows, "title", SourceBook::getTitle);
        updateValidationDisplay();
    }

    private void addDuplicateIdWarnings(List<SourcebookRow> rows) {
        Map<Integer, List<String>> sourcebooksById = new HashMap<>();
        for (SourcebookRow row : rows) {
            int id = row.sourceBook().getId();
            if (id == -1 || id == 0) {
                continue;
            }
            sourcebooksById.computeIfAbsent(id, ignored -> new ArrayList<>()).add(describeSourcebook(row));
        }

        sourcebooksById.entrySet().stream()
              .filter(entry -> entry.getValue().size() > 1)
              .sorted(Map.Entry.comparingByKey())
              .forEach(entry -> sourcebookValidationWarnings.add("Duplicate MUL ID " + entry.getKey() + ": "
                    + String.join("; ", entry.getValue())));
    }

    private void addDuplicateTextWarnings(List<SourcebookRow> rows, String fieldName,
          Function<SourceBook, String> valueExtractor) {
        Map<String, String> displayValues = new HashMap<>();
        Map<String, List<String>> sourcebooksByValue = new HashMap<>();
        for (SourcebookRow row : rows) {
            String value = normalizedText(valueExtractor.apply(row.sourceBook()));
            if (value.isBlank()) {
                continue;
            }

            String key = value.toLowerCase(Locale.ROOT);
            displayValues.putIfAbsent(key, value);
            sourcebooksByValue.computeIfAbsent(key, ignored -> new ArrayList<>()).add(describeSourcebook(row));
        }

        sourcebooksByValue.entrySet().stream()
              .filter(entry -> entry.getValue().size() > 1)
              .sorted(Map.Entry.comparingByKey())
              .forEach(entry -> sourcebookValidationWarnings.add("Duplicate " + fieldName + " \""
                    + displayValues.get(entry.getKey()) + "\": " + String.join("; ", entry.getValue())));
    }

    private String describeSourcebook(SourcebookRow row) {
        SourceBook sourceBook = row.sourceBook();
        String title = normalizedText(sourceBook.getTitle());
        String abbrev = normalizedText(sourceBook.getAbbrev());
        if (!title.isBlank() && !abbrev.isBlank()) {
            return row.fileName() + " [" + abbrev + " / " + title + "]";
        } else if (!title.isBlank()) {
            return row.fileName() + " [" + title + "]";
        } else if (!abbrev.isBlank()) {
            return row.fileName() + " [" + abbrev + "]";
        }
        return row.fileName();
    }

    private void checkUnitSourceReferences() {
        unitSourceScanRunning = true;
        unitSourceScanComplete = false;
        unitSourceValidationWarnings.clear();
        updateValidationDisplay();

        new SwingWorker<List<String>, Void>() {
            @Override
            protected List<String> doInBackground() {
                return findMissingUnitSourceWarnings();
            }

            @Override
            protected void done() {
                try {
                    unitSourceValidationWarnings.clear();
                    unitSourceValidationWarnings.addAll(get());
                } catch (InterruptedException exception) {
                    Thread.currentThread().interrupt();
                    unitSourceValidationWarnings.add("Unit source scan was interrupted.");
                } catch (ExecutionException exception) {
                    LOGGER.error("", exception);
                    unitSourceValidationWarnings.add("Unit source scan failed: " + executionExceptionMessage(
                          exception));
                } finally {
                    unitSourceScanRunning = false;
                    unitSourceScanComplete = true;
                    updateValidationDisplay();
                }
            }
        }.execute();
    }

    private List<String> findMissingUnitSourceWarnings() {
        Set<String> availableSourcebookReferences = buildAvailableSourcebookReferences();
        MekSummary[] units = MekSummaryCache.getInstance().getAllMeks();
        List<String> warnings = new ArrayList<>();
        if (units == null) {
            warnings.add("Unit cache did not return any units to check.");
            return warnings;
        }

        for (MekSummary unit : units) {
            addMissingUnitSourceWarnings(warnings, unit, "source", unit.getSource(), availableSourcebookReferences);
            addMissingUnitSourceWarnings(warnings, unit, "published", unit.getPublished(),
                  availableSourcebookReferences);
        }
        return warnings;
    }

    private Set<String> buildAvailableSourcebookReferences() {
        Set<String> availableSourcebookReferences = new HashSet<>();
        for (String fileName : sourceBooks.availableSourcebooks()) {
            addSourcebookReference(availableSourcebookReferences, fileName);
            sourceBooks.loadSourceBook(fileName).ifPresent(sourceBook -> {
                addSourcebookReference(availableSourcebookReferences, sourceBook.getSku());
                addSourcebookReference(availableSourcebookReferences, sourceBook.getTitle());
                addSourcebookReference(availableSourcebookReferences, sourceBook.getAbbrev());
            });
        }
        return availableSourcebookReferences;
    }

    private void addSourcebookReference(Set<String> availableSourcebookReferences, String sourcebookReference) {
        String reference = normalizedText(sourcebookReference);
        if (reference.isBlank()) {
            return;
        }
        availableSourcebookReferences.add(normalizedSourcebookReference(reference));
        availableSourcebookReferences.add(normalizedSourcebookReference(sourceBooks.sourceBookKey(reference)));
    }

    private void addMissingUnitSourceWarnings(List<String> warnings, MekSummary unit, String fieldName,
          String sourceList, Set<String> availableSourcebookReferences) {
        for (String sourcebookName : SourceBooks.splitSourceList(sourceList)) {
            if (!containsSourcebookReference(availableSourcebookReferences, sourcebookName)) {
                warnings.add("Missing sourcebook referenced by " + fieldName + " \"" + sourcebookName
                      + "\" for " + describeUnit(unit));
            }
        }
    }

    private boolean containsSourcebookReference(Set<String> availableSourcebookReferences, String sourcebookReference) {
        return availableSourcebookReferences.contains(normalizedSourcebookReference(sourcebookReference))
              || availableSourcebookReferences.contains(normalizedSourcebookReference(sourceBooks.sourceBookKey(
                    sourcebookReference)));
    }

    private static String executionExceptionMessage(ExecutionException exception) {
        if (exception.getCause() == null) {
            return exception.getMessage();
        }
        return exception.getCause().getMessage();
    }

    private String describeUnit(MekSummary unit) {
        String unitName = normalizedText(unit.getName());
        if (unitName.isBlank()) {
            unitName = "Unknown unit";
        }

        String sourceLocation = "";
        var sourceFile = unit.getSourceFile();
        if (sourceFile != null) {
            sourceLocation = sourceFile.getName();
        }
        if (unit.getEntryName() != null) {
            sourceLocation = sourceLocation.isBlank() ? unit.getEntryName() : sourceLocation + " >> "
                  + unit.getEntryName();
        }

        return sourceLocation.isBlank() ? unitName : unitName + " (" + sourceLocation + ")";
    }

    private void updateValidationDisplay() {
        int warningCount = sourcebookValidationWarnings.size() + unitSourceValidationWarnings.size();
        btnCheckUnitSources.setEnabled(!unitSourceScanRunning);
        btnCheckUnitSources.setText(unitSourceScanRunning ? "Checking..." : "Check Unit Sources");

        if (unitSourceScanRunning) {
            lblValidationSummary.setIcon(UIManager.getIcon("OptionPane.informationIcon"));
            lblValidationSummary.setText("Checking unit source and published references...");
        } else if (warningCount > 0) {
            lblValidationSummary.setIcon(UIManager.getIcon("OptionPane.warningIcon"));
            lblValidationSummary.setText(warningCount + " validation warning" + (warningCount == 1 ? "" : "s")
                  + " found.");
        } else if (unitSourceScanComplete) {
            lblValidationSummary.setIcon(UIManager.getIcon("OptionPane.informationIcon"));
            lblValidationSummary.setText("No validation warnings found.");
        } else {
            lblValidationSummary.setIcon(UIManager.getIcon("OptionPane.informationIcon"));
            lblValidationSummary.setText("No sourcebook metadata warnings. Unit source references not checked.");
        }

        txtValidationLog.setText(validationLogText());
        txtValidationLog.setCaretPosition(0);
    }

    private String validationLogText() {
        List<String> logLines = new ArrayList<>();
        if (sourcebookValidationWarnings.isEmpty()) {
            logLines.add("Sourcebook metadata: no duplicate id, SKU, abbrev, or title warnings.");
        } else {
            logLines.add("Sourcebook metadata warnings:");
            sourcebookValidationWarnings.forEach(warning -> logLines.add("- " + warning));
        }

        logLines.add("");
        if (unitSourceScanRunning) {
            logLines.add("Unit source scan: running...");
        } else if (!unitSourceScanComplete) {
            logLines.add("Unit source scan: not run. Click Check Unit Sources to scan unit source and published "
                  + "entries.");
        } else if (unitSourceValidationWarnings.isEmpty()) {
            logLines.add("Unit source scan: no missing source or published sourcebooks found.");
        } else {
            logLines.add("Unit source warnings:");
            unitSourceValidationWarnings.forEach(warning -> logLines.add("- " + warning));
        }
        return String.join(System.lineSeparator(), logLines);
    }

    private static String normalizedSourcebookReference(String sourcebookReference) {
        return normalizedText(sourcebookReference).toLowerCase(Locale.ROOT);
    }

    private void reloadSourcebooks(String selectedSourcebookKey) {
        runWithoutSelectionEvents(() -> loadSourcebooks(selectedSourcebookKey));
    }

    private void runWithoutSelectionEvents(Runnable task) {
        boolean wasUpdatingSelection = updatingSelection;
        updatingSelection = true;
        try {
            task.run();
        } finally {
            updatingSelection = wasUpdatingSelection;
        }
    }

    private void selectFirstSourcebook() {
        if (sourcebookTable.getRowCount() > 0) {
            sourcebookTable.setRowSelectionInterval(0, 0);
        } else {
            newSourcebook();
        }
    }

    private boolean selectSourcebook(String sourcebookKey) {
        for (int modelRow = 0; modelRow < sourcebookTableModel.getRowCount(); modelRow++) {
            if (sourcebookTableModel.getRow(modelRow).fileName().equals(sourcebookKey)) {
                int viewRow = sourcebookTable.convertRowIndexToView(modelRow);
                if (viewRow >= 0) {
                    sourcebookTable.setRowSelectionInterval(viewRow, viewRow);
                    return true;
                }
            }
        }
        return false;
    }

    private void selectedSourcebookChanged() {
        int selectedRow = sourcebookTable.getSelectedRow();
        if (selectedRow < 0) {
            return;
        }

        SourcebookRow selectedSourcebook = sourcebookTableModel.getRow(sourcebookTable.convertRowIndexToModel(
              selectedRow));
        if (selectedSourcebook.fileName().equals(savedSourcebookState.fileName())) {
            return;
        }
        if (!confirmSaveDiscardOrCancel()) {
            restoreSourcebookSelection();
            return;
        }

        reloadSourcebooks(selectedSourcebook.fileName());
        loadSelectedSourcebook();
    }

    private void restoreSourcebookSelection() {
        runWithoutSelectionEvents(() -> {
            if (savedSourcebookState.fileName().isBlank() || !selectSourcebook(savedSourcebookState.fileName())) {
                sourcebookTable.clearSelection();
            }
        });
    }

    private void loadSelectedSourcebook() {
        int selectedRow = sourcebookTable.getSelectedRow();
        if (selectedRow < 0) {
            return;
        }
        SourcebookRow sourcebookRow = sourcebookTableModel.getRow(sourcebookTable.convertRowIndexToModel(selectedRow));
        SourceBook sourceBook = sourcebookRow.sourceBook();
        txtFileName.setText(sourcebookRow.fileName());
        txtFileName.setEditable(false);
        txtId.setText(String.valueOf(sourceBook.getId()));
        txtSku.setText(text(sourceBook.getSku()));
        txtTitle.setText(text(sourceBook.getTitle()));
        txtAbbrev.setText(text(sourceBook.getAbbrev()));
        txtImage.setText(text(sourceBook.getImage()));
        txtUrl.setText(text(sourceBook.getUrl()));
        chkIsPublished.setSelected(sourceBook.getIspublished());
        chkCanon.setSelected(sourceBook.isCanon());
        txtMulUrl.setText(text(sourceBook.getMul_url()));
        txtDescription.setText(text(sourceBook.getDescription()));
        txtDescription.setCaretPosition(0);
        savedSourcebookState = currentSourcebookState();
    }

    private void beginNewSourcebook() {
        if (confirmSaveDiscardOrCancel()) {
            reloadSourcebooks(null);
            newSourcebook();
        }
    }

    private void newSourcebook() {
        runWithoutSelectionEvents(sourcebookTable::clearSelection);
        txtFileName.setText("");
        txtFileName.setEditable(true);
        txtId.setText("0");
        txtSku.setText("");
        txtTitle.setText("");
        txtAbbrev.setText("");
        txtImage.setText("");
        txtUrl.setText("");
        chkIsPublished.setSelected(true);
        chkCanon.setSelected(true);
        txtMulUrl.setText("");
        txtDescription.setText("");
        savedSourcebookState = currentSourcebookState();
    }

    private boolean saveSourcebook() {
        String savedSourcebookKey = saveSourcebookData();
        if (savedSourcebookKey == null) {
            return false;
        }

        reloadSourcebooks(savedSourcebookKey);
        if ((sourcebookTable.getSelectedRow() < 0) && !txtFilter.getText().isBlank()) {
            runWithoutSelectionEvents(() -> {
                txtFilter.setText("");
                loadSourcebooks(savedSourcebookKey);
            });
        }
        loadSelectedSourcebook();
        return true;
    }

    private String saveSourcebookData() {
        String sourcebookKey = txtFileName.getText().trim();
        if (sourcebookKey.isBlank()) {
            sourcebookKey = txtAbbrev.getText().trim();
        }
        if (sourcebookKey.isBlank()) {
            JOptionPane.showMessageDialog(this, "File or abbrev is required.", "Sourcebooks",
                  JOptionPane.ERROR_MESSAGE);
            return null;
        }

        String savedSourcebookKey = sourceBooks.sourceBookKey(sourcebookKey);
        if (sourcebookKeyExistsForAnotherSourcebook(savedSourcebookKey)) {
            JOptionPane.showMessageDialog(this,
                  "A sourcebook file named \"" + savedSourcebookKey + "\" already exists. Choose a different file name.",
                  "Sourcebooks",
                  JOptionPane.ERROR_MESSAGE);
            return null;
        }

        String abbrev = txtAbbrev.getText().trim();
        if (abbrevExistsForAnotherSourcebook(abbrev)) {
            JOptionPane.showMessageDialog(this,
                  "The abbrev \"" + abbrev + "\" is already used by another sourcebook. Choose a unique abbrev.",
                  "Sourcebooks",
                  JOptionPane.ERROR_MESSAGE);
            return null;
        }

        SourceBook sourceBook = new SourceBook();
        try {
            sourceBook.setId(txtId.getText().trim().isEmpty() ? 0 : Integer.parseInt(txtId.getText().trim()));
        } catch (NumberFormatException exception) {
            JOptionPane.showMessageDialog(this, "MUL ID must be a number.", "Sourcebooks", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        sourceBook.setSku(blankToNull(txtSku.getText()));
        sourceBook.setTitle(blankToNull(txtTitle.getText()));
        sourceBook.setAbbrev(blankToNull(txtAbbrev.getText()));
        sourceBook.setImage(blankToNull(txtImage.getText()));
        sourceBook.setUrl(blankToNull(txtUrl.getText()));
        sourceBook.setIspublished(chkIsPublished.isSelected());
        sourceBook.setCanon(chkCanon.isSelected());
        sourceBook.setDescription(blankToNull(txtDescription.getText()));
        sourceBook.setMul_url(blankToNull(txtMulUrl.getText()));

        try {
            sourceBooks.saveSourceBook(savedSourcebookKey, sourceBook);
            clearUnitSourceScanResults();
            return savedSourcebookKey;
        } catch (IOException exception) {
            LOGGER.error("", exception);
            JOptionPane.showMessageDialog(this, exception.getMessage(), "Sourcebooks", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    private void clearUnitSourceScanResults() {
        unitSourceScanComplete = false;
        unitSourceValidationWarnings.clear();
        updateValidationDisplay();
    }

    private boolean sourcebookKeyExistsForAnotherSourcebook(String candidateSourcebookKey) {
        return sourceBooks.availableSourcebooks().stream()
              .map(sourceBooks::sourceBookKey)
              .anyMatch(existingSourcebookKey -> sameSourcebookKey(existingSourcebookKey, candidateSourcebookKey)
                    && !sameSourcebookKey(existingSourcebookKey, savedSourcebookState.fileName()));
    }

    private boolean abbrevExistsForAnotherSourcebook(String candidateAbbrev) {
        if (candidateAbbrev.isBlank()) {
            return false;
        }

        return sourceBooks.availableSourcebooks().stream()
              .filter(sourcebookKey -> !sameSourcebookKey(sourcebookKey, savedSourcebookState.fileName()))
              .map(sourceBooks::loadSourceBook)
              .flatMap(Optional::stream)
              .map(SourceBook::getAbbrev)
              .filter(Objects::nonNull)
              .map(String::trim)
              .anyMatch(existingAbbrev -> existingAbbrev.equalsIgnoreCase(candidateAbbrev));
    }

    private boolean sameSourcebookKey(String firstSourcebookKey, String secondSourcebookKey) {
        if ((firstSourcebookKey == null) || firstSourcebookKey.isBlank()
              || (secondSourcebookKey == null) || secondSourcebookKey.isBlank()) {
            return false;
        }
        return sourceBooks.sourceBookKey(firstSourcebookKey)
              .equalsIgnoreCase(sourceBooks.sourceBookKey(secondSourcebookKey));
    }

    private boolean confirmSaveDiscardOrCancel() {
        if (!isDirty()) {
            return true;
        }

        Object[] options = { "Save", "Discard", "Cancel" };
        int choice = JOptionPane.showOptionDialog(this,
              "Changes to the current sourcebook will be lost.\nSave before proceeding?",
              "Unsaved Sourcebook Changes",
              JOptionPane.YES_NO_CANCEL_OPTION,
              JOptionPane.WARNING_MESSAGE,
              null,
              options,
              options[0]);
        return switch (choice) {
            case 0 -> saveSourcebookData() != null;
            case 1 -> true;
            default -> false;
        };
    }

    private boolean isDirty() {
        return !currentSourcebookState().equals(savedSourcebookState);
    }

    private SourcebookState currentSourcebookState() {
        return new SourcebookState(txtFileName.getText().trim(),
              txtId.getText().trim(),
              normalizedText(txtSku.getText()),
              normalizedText(txtTitle.getText()),
              normalizedText(txtAbbrev.getText()),
              normalizedText(txtImage.getText()),
              normalizedText(txtUrl.getText()),
              chkIsPublished.isSelected(),
              chkCanon.isSelected(),
              normalizedText(txtMulUrl.getText()),
              normalizedText(txtDescription.getText()));
    }

    private static String text(String value) {
        return Objects.requireNonNullElse(value, "");
    }

    private static String normalizedText(String value) {
        return text(value).trim();
    }

    private static String blankToNull(String value) {
        String trimmedValue = value.trim();
        return trimmedValue.isBlank() ? null : trimmedValue;
    }

    private record SourcebookState(String fileName, String id, String sku, String title, String abbrev, String image,
          String url, boolean isPublished, boolean canon, String mulUrl, String description) {
        private static SourcebookState empty() {
            return new SourcebookState("", "", "", "", "", "", "", false, false, "", "");
        }
    }

    private record SourcebookRow(String fileName, SourceBook sourceBook) {
        private String titleForSort() {
            return text(sourceBook.getTitle()).isBlank() ? fileName : sourceBook.getTitle();
        }

        private boolean matches(String filterText) {
            return text(sourceBook.getSku()).toLowerCase(Locale.ROOT).contains(filterText)
                  || text(sourceBook.getTitle()).toLowerCase(Locale.ROOT).contains(filterText)
                  || text(sourceBook.getAbbrev()).toLowerCase(Locale.ROOT).contains(filterText);
        }
    }

    private static class SourcebookTableModel extends AbstractTableModel {
        private static final int COL_SKU = 0;
        private static final int COL_TITLE = 1;
        private static final int COL_ABBREV = 2;
        private static final int COL_CANON = 3;
        private static final String[] COLUMNS = { "SKU", "Title", "Abbrev", "Canon" };

        private final List<SourcebookRow> rows = new ArrayList<>();

        private void setRows(List<SourcebookRow> sourcebookRows) {
            rows.clear();
            rows.addAll(sourcebookRows);
            fireTableDataChanged();
        }

        private SourcebookRow getRow(int rowIndex) {
            return rows.get(rowIndex);
        }

        @Override
        public int getRowCount() {
            return rows.size();
        }

        @Override
        public int getColumnCount() {
            return COLUMNS.length;
        }

        @Override
        public String getColumnName(int column) {
            return COLUMNS[column];
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            SourceBook sourceBook = rows.get(rowIndex).sourceBook();
            return switch (columnIndex) {
                case 0 -> text(sourceBook.getSku());
                case 1 -> text(sourceBook.getTitle());
                case 2 -> text(sourceBook.getAbbrev());
                case 3 -> sourceBook.isCanon();
                default -> "";
            };
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return columnIndex == COL_CANON ? Boolean.class : String.class;
        }
    }
}