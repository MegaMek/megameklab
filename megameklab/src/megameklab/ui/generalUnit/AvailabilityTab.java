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
package megameklab.ui.generalUnit;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.Serial;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Set;
import java.util.StringJoiner;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableRowSorter;

import megamek.client.ratgenerator.AvailabilityRating;
import megamek.client.ratgenerator.FactionRecord;
import megamek.client.ratgenerator.MissionRole;
import megamek.client.ratgenerator.RATGenerator;
import megamek.client.ui.dialogs.UnitLoadingDialog;
import megamek.client.ui.util.UIUtil;
import megamek.common.loaders.MekFileParser;
import megamek.common.loaders.MekSummary;
import megamek.common.loaders.MekSummaryCache;
import megamek.common.units.Entity;
import megamek.common.units.ForceGeneratorAvailability;
import megamek.common.units.UnitType;
import megameklab.ui.EntitySource;
import megameklab.ui.dialog.AddFactionsDialog;
import megameklab.ui.dialog.MegaMekLabUnitSelectorDialog;
import megameklab.ui.generalUnit.AvailabilityTableModel.AvailabilityRow;
import megameklab.ui.util.ITab;
import megameklab.ui.util.RefreshListener;
import megameklab.util.AvailabilityCalibration;

/**
 * Lets a player say which factions field a custom unit, and how often, so it turns up in generated forces.
 * <p>
 * The tab is built around the order a player actually works in: the unit is already built and its introduction year is
 * already set on Basic Info, so this tab comes last. List the factions, set the number, and only then worry about year
 * ranges.
 * </p>
 * <p>
 * The number runs 0 to 10 on a base-2 log scale and means nothing on its own, so the slider says it in words and the
 * tab names canon designs of about the same commonness. That is the difference between a considered number and a
 * guess.
 * </p>
 */
public class AvailabilityTab extends ITab {
    @Serial
    private static final long serialVersionUID = 1L;

    /** What a new faction starts at. 6 is what the canon data uses when the source books give no hint either way. */
    private static final int DEFAULT_AVAILABILITY = 6;
    private static final int MIN_AVAILABILITY = 0;
    private static final int MAX_AVAILABILITY = 10;
    private static final int MIN_YEAR = 1950;
    private static final int MAX_YEAR = 3200;
    private static final int TABLE_WIDTH = 640;
    private static final int TABLE_HEIGHT = 200;
    private static final int ROLE_COLUMNS = 4;

    /** Cached canon chassis names, lowercased. Built once; canon data does not change within a session. */
    private static Set<String> cachedCanonChassisNames;

    private RefreshListener refresh;

    private final AvailabilityTableModel tableModel = new AvailabilityTableModel();
    private final JTable factionTable = new JTable(tableModel);

    private final JLabel headerLabel = new JLabel();
    private final JTextArea warningArea = new JTextArea();

    private final JSlider availabilitySlider = new JSlider(MIN_AVAILABILITY, MAX_AVAILABILITY);
    private final JLabel availabilityWordLabel = new JLabel();
    private final JLabel comparableLabel = new JLabel();
    private final JSpinner fromYearSpinner = new JSpinner(new SpinnerNumberModel(MIN_YEAR, MIN_YEAR, MAX_YEAR, 1));
    private final JSpinner toYearSpinner = new JSpinner(new SpinnerNumberModel(MIN_YEAR, MIN_YEAR, MAX_YEAR, 1));
    private final JCheckBox neverStopsCheckBox = new JCheckBox("never stops");
    private final JPanel editorPanel = new JPanel();

    private final Map<MissionRole, JCheckBox> roleCheckBoxes = new LinkedHashMap<>();
    private final JPanel rolesPanel = new JPanel(new GridLayout(0, ROLE_COLUMNS));
    /** Roles currently shown, being those that fit the unit type plus any the file declares. */
    private final Set<MissionRole> offeredRoles = EnumSet.noneOf(MissionRole.class);
    /** Roles the unit file declares that do not apply to this unit type. Shown, not dropped. */
    private final Set<MissionRole> mismatchedRoles = EnumSet.noneOf(MissionRole.class);

    private final JButton addButton = new JButton("+ Add factions...");
    private final JButton removeButton = new JButton("- Remove");
    private final JButton copyFromUnitButton = new JButton("Copy numbers from a unit...");

    /** Guards the listeners while the editor is being filled in from the selected row. */
    private boolean updatingEditor = false;

    public AvailabilityTab(EntitySource eSource) {
        super(eSource);
        buildLayout();
        refresh();
    }

    public void addRefreshedListener(RefreshListener listener) {
        refresh = listener;
    }

    /**
     * The rows the tab is showing.
     *
     * @return the table model
     */
    public AvailabilityTableModel getTableModel() {
        return tableModel;
    }

    /**
     * Whether a role is on offer for this unit. A role that does not apply to the unit type is hidden, unless the unit
     * file already declares it.
     *
     * @param role the role to check
     *
     * @return {@code true} if the player can see and tick it
     */
    public boolean isRoleOffered(MissionRole role) {
        return offeredRoles.contains(role);
    }

    /**
     * How many role checkboxes are actually in the grid. Equals the number of offered roles when the grid packs with
     * no hidden placeholder cells; used to guard against the holes that setVisible-based hiding left behind.
     *
     * @return the checkbox count
     */
    int missionRoleGridSize() {
        return rolesPanel.getComponentCount();
    }

    /**
     * The roles the unit file declares that do not apply to this unit type. The Force Generator ignores these.
     *
     * @return the mismatched roles
     */
    public Set<MissionRole> getMismatchedRoles() {
        return Set.copyOf(mismatchedRoles);
    }

    /**
     * Reloads the tab from the unit. The introduction year lives on Basic Info, so it can change under this tab; the
     * faction list and the warnings are rebuilt every time.
     */
    public void refresh() {
        Entity entity = getEntity();

        headerLabel.setText(entity.getShortNameRaw()
              + "  -  introduced " + entity.getYear()
              + "  -  " + (isCanonUnit() ? "canon unit" : "custom unit"));

        tableModel.setIntroYear(entity.getYear());
        tableModel.loadFrom(entity.getForceGeneratorAvailability(), this::factionNameOf);
        tableModel.markStaleRows(this::isRowStale);
        loadMissionRoles(entity.getMissionRoles());

        updateWarnings();
        updateEditorFromSelection();
    }

    private void buildLayout() {
        setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 4, 8));
        headerPanel.add(headerLabel, BorderLayout.NORTH);
        // A text area, not a label, so a long warning wraps to the width instead of being clipped
        warningArea.setEditable(false);
        warningArea.setLineWrap(true);
        warningArea.setWrapStyleWord(true);
        warningArea.setOpaque(false);
        warningArea.setForeground(Color.RED);
        warningArea.setFont(headerLabel.getFont());
        warningArea.setBorder(null);
        headerPanel.add(warningArea, BorderLayout.CENTER);
        add(headerPanel, BorderLayout.NORTH);

        JPanel centrePanel = new JPanel();
        centrePanel.setLayout(new BoxLayout(centrePanel, BoxLayout.Y_AXIS));
        centrePanel.add(buildFactionPanel());
        centrePanel.add(buildEditorPanel());
        centrePanel.add(buildRolesPanel());
        add(new JScrollPane(centrePanel), BorderLayout.CENTER);
    }

    private JPanel buildFactionPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Who fields this unit?"));

        factionTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        factionTable.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting()) {
                updateEditorFromSelection();
            }
        });

        // Clicking a column header sorts by it. The cells are display text ("4  rare", "3085"), so the numeric columns
        // need comparators that read the number rather than sorting the text.
        TableRowSorter<AvailabilityTableModel> sorter = new TableRowSorter<>(tableModel);
        sorter.setComparator(AvailabilityTableModel.COL_AVAILABILITY, Comparator.comparingInt(AvailabilityTab::leadingInt));
        sorter.setComparator(AvailabilityTableModel.COL_FROM, Comparator.comparingInt(AvailabilityTab::yearValue));
        sorter.setComparator(AvailabilityTableModel.COL_TO, Comparator.comparingInt(AvailabilityTab::yearValue));
        factionTable.setRowSorter(sorter);

        factionTable.setDefaultRenderer(Object.class, new StaleRowRenderer());
        JScrollPane tableScroll = new JScrollPane(factionTable);
        tableScroll.setPreferredSize(UIUtil.scaleForGUI(TABLE_WIDTH, TABLE_HEIGHT));
        panel.add(tableScroll, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        addButton.addActionListener(event -> addFactions());
        removeButton.addActionListener(event -> removeSelectedFaction());
        copyFromUnitButton.addActionListener(event -> copyNumbersFromUnit());
        copyFromUnitButton.setToolTipText("Start from a design you already know, rather than from a blank table.");
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(copyFromUnitButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    /**
     * Fills the table from a canon design's own numbers.
     * <p>
     * Nobody has intuition for a base-2 log scale, so the surest way to a sane table is to start from a design whose
     * commonness the player already understands and adjust from there.
     * </p>
     */
    private void copyNumbersFromUnit() {
        UnitLoadingDialog unitLoadingDialog = new UnitLoadingDialog(null);
        unitLoadingDialog.setVisible(true);

        Entity chosenEntity;
        try {
            MegaMekLabUnitSelectorDialog selector =
                  new MegaMekLabUnitSelectorDialog(null, unitLoadingDialog, false);
            chosenEntity = selector.getChosenEntity();
        } finally {
            unitLoadingDialog.setVisible(false);
        }

        if (chosenEntity == null) {
            return;
        }

        List<AvailabilityRating> ratings = AvailabilityCalibration.ratingsOf(chassisKeyOf(chosenEntity),
              getEntity().getYear());

        if (ratings.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                  "The Force Generator has no availability for " + chosenEntity.getShortNameRaw() + " in "
                        + getEntity().getYear() + ", so there is nothing to copy.",
                  "Nothing to copy",
                  JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        for (AvailabilityRating rating : ratings) {
            tableModel.addRow(new AvailabilityRow(rating.getFactionCode(),
                  factionNameOf(rating.getFactionCode()),
                  rating.getAvailability(),
                  ForceGeneratorAvailability.UNSPECIFIED_YEAR,
                  ForceGeneratorAvailability.UNSPECIFIED_YEAR,
                  false));
        }

        tableModel.markStaleRows(this::isRowStale);
        writeBack();
    }

    /**
     * Builds the key the Force Generator files a design's chassis under.
     *
     * @param entity the design
     *
     * @return the chassis key, e.g. "Archer[Mek]"
     */
    private static String chassisKeyOf(Entity entity) {
        String key = entity.getChassis() + '[' + UnitType.getTypeName(entity.getUnitType()) + ']';
        if (!entity.isOmni()) {
            return key;
        }

        return key + (entity.isClan() ? "ClanOmni" : "ISOmni");
    }

    private JPanel buildEditorPanel() {
        editorPanel.setLayout(new BoxLayout(editorPanel, BoxLayout.Y_AXIS));
        editorPanel.setBorder(BorderFactory.createTitledBorder("Selected faction"));

        availabilitySlider.setMajorTickSpacing(2);
        availabilitySlider.setPaintTicks(true);
        availabilitySlider.setPaintLabels(true);
        availabilitySlider.setSnapToTicks(false);
        availabilitySlider.addChangeListener(event -> onAvailabilityChanged());

        JPanel sliderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        sliderPanel.add(new JLabel("How common?"));
        sliderPanel.add(availabilitySlider);
        sliderPanel.add(availabilityWordLabel);
        editorPanel.add(sliderPanel);

        JPanel comparablePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        comparablePanel.add(comparableLabel);
        editorPanel.add(comparablePanel);

        // A year is not a quantity: show "3056", not the locale-grouped "3,056"
        fromYearSpinner.setEditor(new JSpinner.NumberEditor(fromYearSpinner, "0"));
        toYearSpinner.setEditor(new JSpinner.NumberEditor(toYearSpinner, "0"));

        JPanel yearPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        yearPanel.add(new JLabel("Years: from"));
        yearPanel.add(fromYearSpinner);
        yearPanel.add(new JLabel("to"));
        yearPanel.add(toYearSpinner);
        yearPanel.add(neverStopsCheckBox);
        fromYearSpinner.addChangeListener(event -> onYearsChanged());
        toYearSpinner.addChangeListener(event -> onYearsChanged());
        neverStopsCheckBox.addActionListener(event -> onYearsChanged());
        editorPanel.add(yearPanel);

        return editorPanel;
    }

    private JPanel buildRolesPanel() {
        rolesPanel.setBorder(BorderFactory.createTitledBorder("Mission roles (optional)"));

        // Build every checkbox once, but leave the panel empty: loadMissionRoles adds only the ones to show, so the
        // grid packs with no holes where a role does not apply to this unit type.
        for (MissionRole role : MissionRole.values()) {
            JCheckBox checkBox = new JCheckBox(role.toString().replace('_', ' '));
            checkBox.addActionListener(event -> writeBack());
            roleCheckBoxes.put(role, checkBox);
        }

        return rolesPanel;
    }

    // --- Faction list -------------------------------------------------------------------------------------------

    private void addFactions() {
        List<String> alreadyChosen = new ArrayList<>();
        for (AvailabilityRow row : tableModel.getRows()) {
            alreadyChosen.add(row.factionCode());
        }

        AddFactionsDialog dialog = new AddFactionsDialog(this, getEntity().getYear(), alreadyChosen);
        dialog.setVisible(true);

        for (String factionCode : dialog.getChosenFactionCodes()) {
            tableModel.addRow(new AvailabilityRow(factionCode,
                  factionNameOf(factionCode),
                  DEFAULT_AVAILABILITY,
                  ForceGeneratorAvailability.UNSPECIFIED_YEAR,
                  ForceGeneratorAvailability.UNSPECIFIED_YEAR,
                  false));
        }

        tableModel.markStaleRows(this::isRowStale);
        writeBack();
    }

    private void removeSelectedFaction() {
        int selected = selectedModelRow();
        if (selected < 0) {
            return;
        }

        tableModel.removeRow(selected);
        writeBack();
    }

    /**
     * The model index of the selected row, or -1 if none. The table can be sorted, so the selected view row is mapped
     * back to the model before it is used to read or change a row.
     *
     * @return the selected model row, or -1
     */
    private int selectedModelRow() {
        int viewRow = factionTable.getSelectedRow();

        return (viewRow < 0) ? -1 : factionTable.convertRowIndexToModel(viewRow);
    }

    /** Reads the leading number out of a cell like "4  rare", for sorting the How common column by value. */
    private static int leadingInt(String text) {
        int end = 0;
        while ((end < text.length()) && Character.isDigit(text.charAt(end))) {
            end++;
        }

        return (end == 0) ? 0 : Integer.parseInt(text.substring(0, end));
    }

    /** Reads a year out of a cell, treating a blank (never stops) as the largest value so it sorts last. */
    private static int yearValue(String text) {
        String trimmed = text.trim();

        return trimmed.isEmpty() ? Integer.MAX_VALUE : Integer.parseInt(trimmed);
    }

    // --- Editor strip -------------------------------------------------------------------------------------------

    private void updateEditorFromSelection() {
        int selected = selectedModelRow();
        boolean hasSelection = (selected >= 0) && (selected < tableModel.getRowCount());

        availabilitySlider.setEnabled(hasSelection);
        fromYearSpinner.setEnabled(hasSelection);
        toYearSpinner.setEnabled(hasSelection);
        neverStopsCheckBox.setEnabled(hasSelection);

        if (!hasSelection) {
            availabilityWordLabel.setText("");
            comparableLabel.setText("Select a faction to set how common this unit is.");
            return;
        }

        AvailabilityRow row = tableModel.getRow(selected);

        updatingEditor = true;
        availabilitySlider.setValue(row.availability());
        int fromYear = (row.fromYear() == ForceGeneratorAvailability.UNSPECIFIED_YEAR)
              ? getEntity().getYear()
              : row.fromYear();
        fromYearSpinner.setValue(Math.clamp(fromYear, MIN_YEAR, MAX_YEAR));
        boolean neverStops = (row.toYear() == ForceGeneratorAvailability.UNSPECIFIED_YEAR);
        neverStopsCheckBox.setSelected(neverStops);
        toYearSpinner.setEnabled(!neverStops);
        toYearSpinner.setValue(Math.clamp(neverStops ? MAX_YEAR : row.toYear(), MIN_YEAR, MAX_YEAR));
        updatingEditor = false;

        updateAvailabilityText(row);
    }

    private void onAvailabilityChanged() {
        if (updatingEditor) {
            return;
        }

        int selected = selectedModelRow();
        if (selected < 0) {
            return;
        }

        AvailabilityRow row = tableModel.getRow(selected).withAvailability(availabilitySlider.getValue());
        tableModel.setRow(selected, row);
        updateAvailabilityText(row);
        writeBack();
    }

    private void onYearsChanged() {
        if (updatingEditor) {
            return;
        }

        int selected = selectedModelRow();
        if (selected < 0) {
            return;
        }

        toYearSpinner.setEnabled(!neverStopsCheckBox.isSelected());

        AvailabilityRow currentRow = tableModel.getRow(selected);
        int fromYear = resolveFromYear((int) fromYearSpinner.getValue(), currentRow.fromYear(), getEntity().getYear());
        int toYear = neverStopsCheckBox.isSelected()
              ? ForceGeneratorAvailability.UNSPECIFIED_YEAR
              : (int) toYearSpinner.getValue();

        tableModel.setRow(selected, currentRow.withYears(fromYear, toYear));
        updateWarnings();
        writeBack();
    }

    /**
     * Keeps "start at the unit's introduction year" stored as the sentinel rather than a concrete year, so it still
     * tracks the intro year if that later changes on Basic Info. Editing the year controls must not freeze the
     * sentinel just because the spinner happens to show the intro year.
     *
     * @param spinnerFromYear the value in the from-year spinner
     * @param rowFromYear     the row's current start year, which may be the sentinel
     * @param introYear       the unit's introduction year
     *
     * @return the start year to store
     */
    static int resolveFromYear(int spinnerFromYear, int rowFromYear, int introYear) {
        boolean stillAtIntro = (rowFromYear == ForceGeneratorAvailability.UNSPECIFIED_YEAR)
              && (spinnerFromYear == introYear);

        return stillAtIntro ? ForceGeneratorAvailability.UNSPECIFIED_YEAR : spinnerFromYear;
    }

    /**
     * The mission roles the tab would write to the unit, for tests to check a hidden checkbox does not leak in.
     *
     * @return the comma-separated role text
     */
    String currentMissionRolesText() {
        return missionRolesText();
    }

    /**
     * Says the number in words and names canon designs of about the same commonness. Without this the number is a
     * guess: nobody has intuition for a base-2 log scale.
     *
     * @param row the row being edited
     */
    private void updateAvailabilityText(AvailabilityRow row) {
        availabilityWordLabel.setText(row.availability() + "  " + AvailabilityCalibration.describe(row.availability()));

        List<String> comparable = AvailabilityCalibration.comparableUnits(getEntity().getUnitType(),
              row.factionCode(),
              getEntity().getYear(),
              row.availability());

        if (comparable.isEmpty()) {
            comparableLabel.setText(" ");
            return;
        }

        StringJoiner names = new StringJoiner(", ");
        comparable.forEach(names::add);
        comparableLabel.setText("At " + row.factionCode() + ":" + row.availability()
              + " this is about as common as: " + names);
    }

    // --- Mission roles ------------------------------------------------------------------------------------------

    /**
     * Shows only the roles that mean anything for this unit type. A Mek has no business being offered "mek carrier" or
     * "paratrooper", and the Force Generator would ignore them anyway.
     * <p>
     * Only the roles to show are added to the grid, in role order, so the checkboxes pack together with no gaps where a
     * role does not apply. A role the unit file declares that does not fit is kept, selected, so the player can see it
     * and decide. Quietly dropping something out of somebody's file is not this tab's job.
     * </p>
     */
    private void loadMissionRoles(String missionRoles) {
        Set<MissionRole> chosen = EnumSet.noneOf(MissionRole.class);
        for (String roleName : missionRoles.split(",")) {
            String trimmed = roleName.trim();
            if (trimmed.isEmpty()) {
                continue;
            }
            MissionRole role = MissionRole.parseRole(trimmed);
            if (role != null) {
                chosen.add(role);
            }
        }

        int unitType = getEntity().getUnitType();
        mismatchedRoles.clear();
        offeredRoles.clear();
        rolesPanel.removeAll();

        for (MissionRole role : MissionRole.values()) {
            boolean isSelected = chosen.contains(role);
            boolean fits = role.fitsUnitType(unitType);

            // Set every checkbox's state, even ones not shown. missionRolesText() reads them all, so a checkbox left
            // selected from a previous refresh would otherwise be written back into the unit.
            JCheckBox checkBox = roleCheckBoxes.get(role);
            checkBox.setSelected(isSelected);

            if (!fits && !isSelected) {
                continue;
            }

            rolesPanel.add(checkBox);
            offeredRoles.add(role);

            if (isSelected && !fits) {
                mismatchedRoles.add(role);
            }
        }

        rolesPanel.revalidate();
        rolesPanel.repaint();
    }

    private String missionRolesText() {
        StringJoiner roles = new StringJoiner(",");
        for (Map.Entry<MissionRole, JCheckBox> entry : roleCheckBoxes.entrySet()) {
            if (entry.getValue().isSelected()) {
                roles.add(entry.getKey().toString());
            }
        }

        return roles.toString();
    }

    // --- Warnings -----------------------------------------------------------------------------------------------

    /**
     * Tells the player when what they are doing will silently not work. Everything here is a rule a hand-editing
     * player would only discover by reading megamek.log, which is not a reasonable ask.
     */
    private void updateWarnings() {
        List<String> warnings = new ArrayList<>();

        if (isCanonUnit()) {
            warnings.add("This is a canon unit. What you set here REPLACES its canon availability for the factions you "
                  + "list, so it changes how canon forces generate for anyone who installs this file. To leave canon "
                  + "alone, save it under a new model name as a custom variant instead.");
        } else if (isCanonChassis()) {
            warnings.add("This is a custom variant of a canon chassis. Factions that already field the chassis keep "
                  + "their canon rating, so your number only decides which variant they get. Factions that do not "
                  + "field it will now get this variant.");
        }

        for (AvailabilityRow row : tableModel.getRows()) {
            if ((row.fromYear() != ForceGeneratorAvailability.UNSPECIFIED_YEAR)
                  && (row.fromYear() < getEntity().getYear())) {
                warnings.add(row.factionCode() + " starts in " + row.fromYear()
                      + ", but the unit does not exist until " + getEntity().getYear() + ".");
            }
        }

        if (!mismatchedRoles.isEmpty()) {
            StringJoiner roleNames = new StringJoiner(", ");
            mismatchedRoles.forEach(role -> roleNames.add(role.toString().replace('_', ' ')));
            warnings.add("These mission roles do not apply to this unit type and will be ignored: " + roleNames + ".");
        }

        if (tableModel.hasStaleRows()) {
            warnings.add("Some factions never exist during the years their row covers, so those entries will never be "
                  + "used. Give them a year range that reaches the years they exist, or remove them.");
        }

        String eraAlignmentWarning = eraAlignmentWarning();
        if (eraAlignmentWarning != null) {
            warnings.add(eraAlignmentWarning);
        }

        if (warnings.isEmpty()) {
            warningArea.setText("");
            return;
        }

        warningArea.setText(String.join("\n", warnings));
    }

    /**
     * Warns when a year range does not line up with the game's eras. The Force Generator stores availability in fixed
     * era buckets, so a change written inside a bucket is spread across it rather than taking effect exactly then. This
     * is what let the QA report's Periphery ranges land in the wrong era; telling the player up front is how they avoid
     * it.
     *
     * @return the warning, or {@code null} if every range lines up with an era
     */
    private String eraAlignmentWarning() {
        RATGenerator ratGenerator = RATGenerator.getInstance();
        if (!ratGenerator.isInitialized() || ratGenerator.getEraSet().isEmpty()) {
            return null;
        }

        List<String> problems = eraAlignmentProblems(tableModel.getRows(), getEntity().getYear(),
              ratGenerator.getEraSet());
        if (problems.isEmpty()) {
            return null;
        }

        return "The Force Generator works in eras, so a year inside an era is approximated to its edge rather than "
              + "taking effect exactly: " + String.join("; ", problems) + ".";
    }

    /**
     * Finds the year-range boundaries that fall inside an era rather than on its edge. A range should start on an era's
     * first year and end on its last, or the change it describes is smeared across the era.
     * <p>
     * Pure so it can be tested without a loaded Force Generator.
     * </p>
     *
     * @param rows      the table rows
     * @param introYear the unit's introduction year, which is a natural start and never a problem
     * @param eras      the era boundary years, each being an era's first year
     *
     * @return one message per misaligned boundary, in row order; empty if all line up
     */
    static List<String> eraAlignmentProblems(List<AvailabilityRow> rows, int introYear, NavigableSet<Integer> eras) {
        List<String> problems = new ArrayList<>();

        for (AvailabilityRow row : rows) {
            int fromYear = row.fromYear();
            if ((fromYear != ForceGeneratorAvailability.UNSPECIFIED_YEAR)
                  && (fromYear != introYear)
                  && !eras.contains(fromYear)) {
                Integer eraStart = eras.floor(fromYear);
                Integer nextEra = eras.higher(fromYear);
                if ((eraStart != null) && (nextEra != null)) {
                    problems.add(row.factionCode() + " starts at " + fromYear + ", inside the " + eraStart + "-"
                          + (nextEra - 1) + " era (use " + eraStart + " or " + nextEra + ")");
                }
            }

            int toYear = row.toYear();
            if (toYear != ForceGeneratorAvailability.UNSPECIFIED_YEAR) {
                Integer nextEra = eras.higher(toYear);
                Integer eraStart = eras.floor(toYear);
                if ((nextEra != null) && (eraStart != null) && ((toYear + 1) != nextEra)) {
                    problems.add(row.factionCode() + " ends at " + toYear + ", inside the " + eraStart + "-"
                          + (nextEra - 1) + " era (use " + (nextEra - 1) + ")");
                }
            }
        }

        return problems;
    }

    /**
     * Entity.isCanon() is stamped when the unit is loaded, so it goes stale the moment the player renames the unit,
     * which is exactly how a canon design becomes a custom variant. Ask by the current name instead.
     *
     * @return true if the unit's current name is a canon unit
     */
    private boolean isCanonUnit() {
        return MekFileParser.isCanonUnitName(getEntity().getShortNameRaw());
    }

    private boolean isCanonChassis() {
        return canonChassisNames().contains(getEntity().getChassis().toLowerCase());
    }

    /**
     * The chassis names that belong to canon units, lowercased. Built once from the unit cache. The Force Generator's
     * own chassis list cannot answer this: it holds custom chassis this feature injected into it, so a brand new custom
     * chassis would wrongly read as canon.
     *
     * @return the canon chassis names, or an empty set until the unit cache is ready
     */
    private static Set<String> canonChassisNames() {
        if (cachedCanonChassisNames != null) {
            return cachedCanonChassisNames;
        }

        MekSummaryCache mekSummaryCache = MekSummaryCache.getInstance();
        if (!mekSummaryCache.isInitialized()) {
            return Set.of();
        }

        Set<String> names = new HashSet<>();
        for (MekSummary mekSummary : mekSummaryCache.getAllMeks()) {
            if (mekSummary.isCanon()) {
                names.add(mekSummary.getChassis().toLowerCase());
            }
        }
        cachedCanonChassisNames = names;

        return names;
    }

    // --- Force Generator lookups --------------------------------------------------------------------------------

    private String factionNameOf(String factionCode) {
        RATGenerator ratGenerator = RATGenerator.getInstance();
        if (!ratGenerator.isInitialized()) {
            return factionCode;
        }

        FactionRecord factionRecord = ratGenerator.getFaction(factionCode);

        return (factionRecord == null) ? factionCode : factionRecord.getName(getEntity().getYear());
    }

    /**
     * Whether a row's faction can never be used during the years that row applies. A future faction added on purpose,
     * whose range reaches the years it exists, is fine; a faction that died out before the row's years is not. This is
     * checked against the row's own start year, not the unit's intro year, so deliberately adding a later faction does
     * not trip the warning.
     *
     * @param row the row to check
     *
     * @return {@code true} if the faction is not active at or after the row's first year
     */
    private boolean isRowStale(AvailabilityRow row) {
        if (AddFactionsDialog.UMBRELLA_CODES.contains(row.factionCode())) {
            return false;
        }

        RATGenerator ratGenerator = RATGenerator.getInstance();
        if (!ratGenerator.isInitialized()) {
            return false;
        }

        FactionRecord factionRecord = ratGenerator.getFaction(row.factionCode());
        if (factionRecord == null) {
            return false;
        }

        int firstYear = (row.fromYear() == ForceGeneratorAvailability.UNSPECIFIED_YEAR)
              ? getEntity().getYear()
              : row.fromYear();

        return !factionRecord.isActiveInOrAfterYear(firstYear);
    }

    // --- Persistence --------------------------------------------------------------------------------------------

    /**
     * Stores the tab's state on the unit. MegaMekLab's savers write the entity out, so nothing else is needed to get
     * these onto disk.
     */
    private void writeBack() {
        List<ForceGeneratorAvailability> entries = tableModel.toAvailabilityEntries();
        getEntity().setForceGeneratorAvailability(entries);
        getEntity().setMissionRoles(missionRolesText());

        updateWarnings();

        if (refresh != null) {
            refresh.refreshStatus();
        }
    }

    /** Highlights rows whose faction does not exist in the unit's year. */
    private static class StaleRowRenderer extends DefaultTableCellRenderer {
        @Serial
        private static final long serialVersionUID = 1L;

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
              boolean hasFocus, int row, int column) {
            Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            AvailabilityTableModel model = (AvailabilityTableModel) table.getModel();
            // row is a view index; the sorter can reorder rows, so map it back to the model before reading the row
            if (model.getRow(table.convertRowIndexToModel(row)).stale() && !isSelected) {
                component.setForeground(Color.RED);
            } else if (!isSelected) {
                component.setForeground(table.getForeground());
            }

            return component;
        }
    }

    public ComponentListener refreshOnShow = new ComponentAdapter() {
        @Override
        public void componentShown(ComponentEvent event) {
            refresh();
        }
    };
}
