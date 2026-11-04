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

package megameklab.ui.building;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;

import megamek.client.ui.WrapLayout;
import megamek.common.equipment.enums.StructureEngine;
import megamek.common.units.AbstractBuildingEntity;
import megamek.common.units.BuildingConstruction;
import megamek.common.units.BuildingDesign;
import megameklab.util.BuildingUtil;

/** Optional construction, service access and a complete, always available construction report. */
class BuildingSystemsTab extends JPanel {
    private final BuildingMainUI editor;
    private final JCheckBox sealing = new JCheckBox("Environmental sealing");
    private final JCheckBox heavyMetal = new JCheckBox("Heavy-metal superstructure");
    private final JCheckBox officers = new JCheckBox("Include officers for civilian operations");
    private final JCheckBox tunnel = new JCheckBox("Tunnel construction");
    private final JCheckBox openSpace = new JCheckBox("Open-space construction");
    private final JLabel openSpaceDescription = new JLabel();
    private final JButton portalTemplates = new JButton("Assign portal equipment templates…");
    private final JLabel portalTemplateDescription = new JLabel("Choose the installed equipment used by each large portal template.");
    private final JCheckBox roofClearance = new JCheckBox("Roof has clearance in a larger cave");
    private final JComboBox<String> ceiling = new JComboBox<>(new String[] { "Standard", "High", "Low" });
    private final JComboBox<String> site = new JComboBox<>(new String[] { "Surface", "Underground", "Underwater" });
    private final JSpinner depth = new JSpinner(new SpinnerNumberModel(1, 1, 1000, 1));
    private final JSpinner combatHours = new JSpinner(new SpinnerNumberModel(0, 0, 24, 1));
    private final JCheckBox minimumCrew = new JCheckBox("Use minimum operating crew", true);
    private final JSpinner crewCount = new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
    private final JPanel totals = new JPanel(new BorderLayout(8, 8));
    private final JTextArea report = new JTextArea();
    private final BuildingServicePanels servicePanels;
    private boolean refreshing;

    BuildingSystemsTab(BuildingMainUI editor) {
        this.editor = editor;
        servicePanels = new BuildingServicePanels(editor);
        setLayout(new BorderLayout(8, 8));
        setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        JPanel options = new JPanel(new GridLayout(0, 2, 8, 6));
        options.setBorder(BorderFactory.createCompoundBorder(
              BorderFactory.createTitledBorder("Construction options — Tactical Operations: Advanced Rules"),
              BorderFactory.createEmptyBorder(6, 6, 6, 6)));
        sealing.setName("Environmental sealing");
        heavyMetal.setName("Heavy-metal superstructure");
        officers.setName("Civilian officers");
        tunnel.setName("Tunnel construction");
        roofClearance.setName("Cave roof clearance");
        ceiling.setName("Building ceiling");
        site.setName("Building site");
        depth.setName("Building cover depth");
        options.add(sealing);
        options.add(new JLabel("No internal mass; structure cost ×1.5"));
        options.add(heavyMetal);
        options.add(new JLabel("Heavy/Hardened only; 75% capacity; structure cost ×1.25"));
        options.add(new JLabel("Ceiling height (Standard/Fortress only)"));
        options.add(ceiling);
        options.add(new JLabel("Construction site"));
        options.add(site);
        options.add(new JLabel("Levels above roof to ground/water surface"));
        options.add(depth);
        options.add(roofClearance);
        options.add(new JLabel("Underground only; at least one level above the roof permits rooftop equipment."));
        options.add(tunnel);
        options.add(new JLabel("Hangar only; doors at connections; no equipment; structure cost ×1.875"));
        openSpace.setName("Open-space construction");
        options.add(openSpace);
        options.add(openSpaceDescription);
        options.add(officers);
        options.add(new JLabel("Military structures include officers automatically."));
        options.add(portalTemplates);
        options.add(portalTemplateDescription);
        portalTemplates.setName("Portal equipment templates");
        portalTemplates.addActionListener(event -> BuildingPlacementDialogs.portalTemplates(editor));
        add(options, BorderLayout.NORTH);

        JPanel services = new JPanel(new BorderLayout(8, 8));
        services.setName("Building service sections");
        totals.setBorder(BorderFactory.createCompoundBorder(
              BorderFactory.createTitledBorder("Capacity, crew, power & validation"),
              BorderFactory.createEmptyBorder(8, 8, 8, 8)));
        JPanel planning = new JPanel(new WrapLayout(FlowLayout.LEFT));
        planning.add(new JLabel("Combat hours per day:"));
        planning.add(combatHours);
        minimumCrew.setName("Use minimum operating crew");
        crewCount.setName("Building crew count");
        planning.add(minimumCrew);
        planning.add(new JLabel("Crew:"));
        planning.add(crewCount);
        totals.add(planning, BorderLayout.NORTH);
        report.setEditable(false);
        report.setName("Building construction report");
        report.setFont(new java.awt.Font(java.awt.Font.MONOSPACED, java.awt.Font.PLAIN, 12));
        report.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        totals.add(new JScrollPane(report), BorderLayout.CENTER);
        services.add(servicePanels.doors, BorderLayout.WEST);
        services.add(servicePanels.elevators, BorderLayout.CENTER);
        setMinimumSize(new java.awt.Dimension(servicePanels.doors.getMinimumSize().width
              + servicePanels.elevators.getMinimumSize().width + 24, 160));
        add(services, BorderLayout.CENTER);
        sealing.addActionListener(e -> apply());
        heavyMetal.addActionListener(e -> apply());
        officers.addActionListener(e -> apply());
        tunnel.addActionListener(e -> apply());
        openSpace.addActionListener(e -> apply());
        roofClearance.addActionListener(e -> apply());
        ceiling.addActionListener(e -> apply());
        site.addActionListener(e -> apply());
        depth.addChangeListener(e -> apply());
        combatHours.addChangeListener(e -> refreshReport());
        minimumCrew.addActionListener(e -> applyCrew());
        crewCount.addChangeListener(e -> applyCrew());
    }

    JPanel getTotalsPanel() {
        return totals;
    }

    private void applyCrew() {
        if (!refreshing) {
            editor.getEntity().setCrewCount(minimumCrew.isSelected()
                  ? AbstractBuildingEntity.CREW_FROM_MINIMUM_CREW_TABLE : (int) crewCount.getValue());
            editor.scheduleRefresh();
        }
    }

    private void apply() {
        if (refreshing) {
            return;
        }
        var design = editor.getEntity().getDesign();
        design.setEnvironmentalSealing(sealing.isEnabled() && sealing.isSelected());
        design.setHeavyMetal(heavyMetal.isSelected());
        design.setCivilianOfficers(officers.isSelected());
        design.setTunnel(tunnel.isSelected());
        design.setOpenSpace(openSpace.isSelected());
        design.setRoofClearance(roofClearance.isSelected());
        design.setCeiling(BuildingDesign.Ceiling.values()[ceiling.getSelectedIndex()]);
        design.setSite(BuildingDesign.Site.values()[site.getSelectedIndex()]);
        design.setDepth((int) depth.getValue());
        editor.scheduleRefresh();
    }

    void refresh() {
        refreshing = true;
        var entity = editor.getEntity();
        var design = entity.getDesign();
        sealing.setSelected(entity.hasEnvironmentalSealing());
        sealing.setEnabled(entity.getConstructionCFScale() == 1);
        heavyMetal.setSelected(design.hasHeavyMetal());
        officers.setSelected(design.hasCivilianOfficers());
        tunnel.setSelected(design.isTunnel());
        openSpace.setSelected(design.isOpenSpace());
        boolean mobile = entity instanceof megamek.common.units.MobileStructure;
        openSpace.setEnabled(entity.getConstructionCFScale() == 10 || design.isOpenSpace()
              || mobile && entity.getBldgClass() == megamek.common.units.IBuilding.HANGAR);
        openSpace.setText(mobile ? "Large Portal" : "Open-space construction");
        openSpaceDescription.setText(mobile ? "Hangar with open-space construction"
              : "600 t total; equipment on the lowest floor only");
        openSpace.setToolTipText(mobile ? "Deploy an underground open-space Castles Brian tunnel first. Place this portal flat against its hillside entrance, facing away from the hill. Move it completely aside; other structures may enter starting next turn." : null);
        portalTemplates.setVisible(mobile && design.isOpenSpace());
        portalTemplateDescription.setVisible(portalTemplates.isVisible());
        heavyMetal.setEnabled(!mobile || design.hasHeavyMetal());
        ceiling.setEnabled(!mobile || design.getCeiling() != BuildingDesign.Ceiling.STANDARD);
        tunnel.setEnabled(!mobile || design.isTunnel());
        site.setEnabled(!mobile || design.getSite() != BuildingDesign.Site.SURFACE);
        roofClearance.setEnabled(!mobile || design.hasRoofClearance());
        roofClearance.setSelected(design.hasRoofClearance());
        ceiling.setSelectedIndex(design.getCeiling().ordinal());
        site.setSelectedIndex(design.getSite().ordinal());
        depth.setValue(design.getDepth());
        depth.setEnabled(design.getSite() != BuildingDesign.Site.SURFACE);
        minimumCrew.setSelected(!entity.hasExplicitCrewCount());
        crewCount.setValue(entity.getNCrew());
        crewCount.setEnabled(entity.hasExplicitCrewCount());
        refreshPlacement();
        refreshReport();
        refreshing = false;
    }

    private void refreshReport() {
        var entity = editor.getEntity();
        var crew = entity.calculateMinimumCrewRequirements();
        var hexes = entity.getInternalBuilding().getOriginalCoordsList();
        var grid = BuildingUtil.sheetGrid(hexes);
        StringBuilder text = new StringBuilder("PER-HEX CAPACITY (all floors combined)\n");
        for (var hex : hexes) {
            double installed = BuildingConstruction.installedWeightInHex(entity, hex);
            double capacity = BuildingConstruction.capacityInHex(entity, hex);
            text.append("%s   Installed %9.2f t   Capacity %9.2f t   Free %9.2f t%n".formatted(
                  grid.label(hex), installed, capacity, capacity - installed));
        }
        text.append("%nMINIMUM OPERATING CREW%nCrew: %d   Gunners: %d   Officers: %d   Total: %d%n".formatted(
              crew.crew(), crew.gunners(), crew.officers(), crew.total()));
        if (entity.hasExplicitCrewCount()) {
            text.append("Crew specified in unit file: %d%n".formatted(entity.getNCrew()));
        }
        text.append("Quarters are optional; staff may commute. Automated weapons use Gunnery 5.\n");
        text.append("%nHEAT & POWER%nEnergy weapon heat: %d   Heat dissipation: %d%n".formatted(
              BuildingConstruction.energyHeat(entity), BuildingConstruction.heatDissipation(entity)));
        text.append(entity.hasFusionOrFissionPower() ? "Fusion/fission power: no minimum heat-sink requirement.\n"
              : "Provide enough heat sinks for all energy weapons firing together.\n");
        text.append("Current supply: ").append(BuildingUtil.powerDescription(entity)).append('\n');
        text.append("\nGENERATOR SIZING FOR THIS BUILDING\n");
        for (var engine : StructureEngine.values()) {
            double fuel = BuildingConstruction.dailyFuel(entity, engine, (int) combatHours.getValue());
            text.append("%-22s %7.0f t    Fuel/day %8.2f t    Fuel/30 days %9.2f t%n".formatted(
                  engine.name().replace('_', ' '), BuildingConstruction.generatorTons(entity, engine), fuel, fuel * 30));
        }
        text.append("Use Liquid Cargo bays for liquid fuel; Cargo bays for solid fuel. Storage may be off site.\n");
        text.append("Liquid fuel needs storage mass of fuel / 0.91; liquid-storage-only buildings need no power.\n");
        text.append("External receivers store one hour of power per five tons, rounded up.\n");
        if (entity.getDesign().isTunnel()) {
            text.append("Tunnel doors must connect to separate buildings when assembling the complex.\n");
        }
        text.append("\nCONSTRUCTION CHECKS\n");
        List<String> issues = BuildingUtil.constructionIssues(entity);
        text.append(issues.isEmpty() ? "Construction checks pass.\n" : String.join("\n", issues));
        report.setText(text.toString());
        report.setCaretPosition(0);
    }

    void refreshPlacement() {
        servicePanels.refresh();
    }
}
