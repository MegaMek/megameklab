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

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.Scrollable;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import megamek.client.ui.WrapLayout;
import megamek.common.SimpleTechLevel;
import megamek.common.board.CubeCoords;
import megamek.common.enums.BuildingType;
import megamek.common.enums.Faction;
import megamek.common.equipment.EquipmentType;
import megamek.common.equipment.enums.StructureEngine;
import megamek.common.interfaces.ITechManager;
import megamek.common.units.AbstractBuildingEntity;
import megamek.common.units.BuildingConstruction;
import megamek.common.units.BuildingDesign;
import megamek.common.units.EntityMovementMode;
import megamek.common.units.IBuilding;
import megamek.common.units.MobileStructure;
import megamek.common.units.UnitRole;
import megamek.common.verifier.TestBuilding;
import megameklab.ui.generalUnit.BasicInfoView;
import megameklab.ui.generalUnit.IconView;
import megameklab.ui.listeners.BuildListener;
import megameklab.ui.util.TabScrollPane;
import megameklab.ui.util.WidthControlComponent;
import megameklab.util.BuildingMap;
import megameklab.util.BuildingUtil;
import megameklab.util.UnitUtil;

class BuildingStructureTab extends JPanel implements BuildListener {
    private final BuildingMainUI editor;
    private final BasicInfoView basicInfo;
    private final JComboBox<String> structureType = new JComboBox<>(new String[] { "Structure", "Mobile Structure" });
    private final IconView icon = new IconView();
    private final JComboBox<BuildingType> type = new JComboBox<>(new BuildingType[] {
          BuildingType.LIGHT, BuildingType.MEDIUM, BuildingType.HEAVY, BuildingType.HARDENED, BuildingType.RAIL });
    private final JComboBox<String> buildingClass = new JComboBox<>(new String[] {
          "Standard", "Hangar", "Fortress", "Gun Emplacement", "Castles Brian", "Tent", "Wall", "Fence", "Bridge" });
    private final JSpinner levels = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
    private final JComboBox<EntityMovementMode> motive = new JComboBox<>(new EntityMovementMode[] {
          EntityMovementMode.TRACKED, EntityMovementMode.VTOL, EntityMovementMode.NAVAL, EntityMovementMode.SUBMARINE });
    private final JComboBox<StructureEngine> mobilePower = new JComboBox<>(StructureEngine.values());
    private final JSpinner maximumMP = new JSpinner(new SpinnerNumberModel(1.0, .25, 4.0, .25));
    private final JSpinner operatingRange = new JSpinner(new SpinnerNumberModel(0.0, 0.0, null, 100.0));
    private final JCheckBox uniformFuel = new JCheckBox("Distribute fuel evenly");
    private final JSpinner hexFuel = new JSpinner(new SpinnerNumberModel(0.0, 0.0, null, .5));
    private final JLabel fuelAllocation = new JLabel();
    private final JSpinner hexHeight = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
    private final JSpinner baseLevel = new JSpinner(new SpinnerNumberModel(0, null, null, 1));
    private final JCheckBox automaticBaseLevel = new JCheckBox("Automatic from site");
    private final JLabel baseLevelLabel;
    private final JSpinner cf = new JSpinner(new SpinnerNumberModel(40, 1, 1000, 1));
    private final JSpinner armor = new JSpinner(new SpinnerNumberModel(0, 0, 1000, 1));
    private final JLabel cfLabel;
    private final JLabel armorLabel;
    private final JLabel selection = new JLabel();
    private final JLabel limits = new JLabel();
    private final JButton remove = new JButton("Remove selected hex");
    private final Footprint footprint = new Footprint(false);
    private final Footprint pancake = new Footprint(true);
    private final JPanel legend = new JPanel(new WrapLayout(FlowLayout.LEFT, 12, 4));
    private final Map<BuildingMap.Feature, JLabel> legendEntries = new EnumMap<>(BuildingMap.Feature.class);
    private final JPanel pancakeLegend = new JPanel(new WrapLayout(FlowLayout.LEFT, 12, 4));
    private final Map<BuildingMap.Feature, JLabel> pancakeLegendEntries = new EnumMap<>(BuildingMap.Feature.class);
    private final JCheckBox absoluteCoordinates = new JCheckBox("Absolute coordinates");
    private final JPanel sideControls = new JPanel(new WrapLayout(FlowLayout.LEFT));
    private final JCheckBox[] sides = new JCheckBox[6];
    private final JPanel bridgeControls = new JPanel(new WrapLayout(FlowLayout.LEFT));
    private final JSpinner deckLevel = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
    private final JSpinner bridgeStart = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
    private final JSpinner bridgeEnd = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
    private final JLabel bridgeEndpoints = new JLabel();
    private final JLabel protectionScale = new JLabel();
    private final JTextArea geometryHint = new JTextArea(3, 0);
    private boolean refreshing;

    BuildingStructureTab(BuildingMainUI editor) {
        this.editor = editor;
        basicInfo = new BasicInfoView(entity().getConstructionTechAdvancement());
        setLayout(new BorderLayout(15, 10));
        JPanel identity = new JPanel(new GridBagLayout());
        mobilePower.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                  boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof StructureEngine engine) {
                    setText(engine.getEngineName());
                }
                return this;
            }
        });
        basicInfo.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Basic Information"),
              BorderFactory.createEmptyBorder(4, 4, 4, 4)));
        addStructureTypeField();
        structureType.setToolTipText("Choose between a stationary structure and a mobile structure.");
        structureType.addActionListener(event -> {
            if (!refreshing) {
                editor.changeStructureType(structureType.getSelectedIndex() == 1);
            }
        });
        icon.setFromEntity(entity());
        icon.setRefreshedListener(editor);
        icon.setBorder(BorderFactory.createCompoundBorder(icon.getBorder(), BorderFactory.createEmptyBorder(4, 4, 4, 4)));
        JPanel settings = new JPanel(new BorderLayout(0, 10));
        settings.setName("Building superstructure");
        settings.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Superstructure"),
              BorderFactory.createEmptyBorder(6, 6, 6, 6)));
        JPanel fields = new JPanel(new GridBagLayout());
        addField(fields, "Building type", type);
        addField(fields, "Classification", buildingClass);
        addField(fields, "Building levels", levels).setText("Levels:");
        levels.setToolTipText("Number of floors. Set their numbering with Lowest floor level.");
        JPanel floorNumbering = new JPanel(new GridLayout(0, 1, 0, 4));
        baseLevel.setName("Lowest floor level");
        baseLevel.setToolTipText("Actual lowest-floor elevation relative to the map surface. 0 = Ground. For a connected basement, choose Underground, turn off Automatic, and set this to minus the number of levels. Deploy the surface building first, then place this part on the same hexes.");
        automaticBaseLevel.setName("Automatic floor numbering");
        automaticBaseLevel.setToolTipText("Surface floors start at Ground. Underground/underwater floors use roof cover depth.");
        floorNumbering.add(baseLevel);
        floorNumbering.add(automaticBaseLevel);
        baseLevelLabel = addField(fields, "Lowest floor level (0 = Ground)", floorNumbering);
        cfLabel = addField(fields, "CF per hex", cf);
        armorLabel = addField(fields, "Armor points per hex", armor);
        if (entity() instanceof MobileStructure) {
            buildingClass.setModel(new DefaultComboBoxModel<>(new String[] { "Standard", "Hangar", "Fortress" }));
            addField(fields, "Motive system", motive);
            addField(fields, "Power system", mobilePower);
            addField(fields, "Maximum MP", maximumMP);
            addField(fields, "Operating range (km)", operatingRange);
            JPanel fuel = new JPanel(new GridLayout(0, 1, 0, 4));
            uniformFuel.setName("Distribute mobile fuel evenly");
            hexFuel.setName("Selected hex fuel tons");
            hexFuel.setToolTipText("Fuel stored in the selected hex. Total allocated fuel must match the operating range.");
            fuel.add(uniformFuel);
            fuel.add(hexFuel);
            fuel.add(fuelAllocation);
            addField(fields, "Selected hex fuel (tons)", fuel);
            addField(fields, "Selected hex levels", hexHeight);
            motive.addActionListener(event -> applyPropulsion());
            mobilePower.addActionListener(event -> applyPropulsion());
            maximumMP.addChangeListener(event -> applyPropulsion());
            operatingRange.addChangeListener(event -> applyPropulsion());
            uniformFuel.addActionListener(event -> applyFuelDistribution());
            hexFuel.addChangeListener(event -> applyFuelDistribution());
            hexHeight.addChangeListener(event -> {
                if (!refreshing) {
                    BuildingUtil.setHexHeight(entity(), editor.selectedHex(), (Integer) hexHeight.getValue());
                    editor.scheduleRefresh();
                }
            });
        }
        GridBagConstraints fieldWidth = new GridBagConstraints();
        fieldWidth.gridx = 1;
        fieldWidth.gridy = fields.getComponentCount() / 2;
        fields.add(new WidthControlComponent(), fieldWidth);
        settings.add(fields, BorderLayout.CENTER);
        JPanel notes = new JPanel(new GridLayout(0, 1, 0, 6));
        notes.add(limits);
        notes.add(protectionScale);
        settings.add(notes, BorderLayout.SOUTH);
        GridBagConstraints row = new GridBagConstraints();
        row.gridx = 0;
        row.gridy = 0;
        row.weightx = 1;
        row.fill = GridBagConstraints.HORIZONTAL;
        row.insets = new Insets(0, 0, 8, 0);
        for (Component panel : List.of(basicInfo, icon, settings)) {
            identity.add(panel, row);
            row.gridy++;
        }
        row.weighty = 1;
        identity.add(Box.createVerticalGlue(), row);
        TabScrollPane properties = new TabScrollPane(identity);
        properties.setName("Building properties");
        properties.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(properties, BorderLayout.WEST);

        JPanel geometry = new JPanel(new BorderLayout(5, 10));
        JPanel map = new JPanel(new BorderLayout(0, 6));
        map.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Footprint"),
              BorderFactory.createEmptyBorder(6, 6, 6, 6)));
        legend.setName("Building footprint legend");
        int markerHeight = legend.getFontMetrics(legend.getFont()).getHeight();
        for (BuildingMap.Feature feature : BuildingMap.Feature.values()) {
            JLabel entry = new JLabel(feature.label, new FeatureIcon(feature, markerHeight), SwingConstants.LEADING);
            entry.setIconTextGap(6);
            legend.add(entry);
            legendEntries.put(feature, entry);
        }
        absoluteCoordinates.setName("Absolute coordinates");
        absoluteCoordinates.setToolTipText("Show authored coordinates (q,r). Sheets always use sheet coordinates.");
        absoluteCoordinates.addActionListener(event -> editor.setAbsoluteCoordinates(absoluteCoordinates.isSelected()));
        JPanel mapHeader = new JPanel(new BorderLayout(8, 4));
        mapHeader.add(legend, BorderLayout.CENTER);
        map.add(mapHeader, BorderLayout.NORTH);
        map.add(new TabScrollPane(footprint), BorderLayout.CENTER);
        geometry.add(map, BorderLayout.CENTER);
        JPanel actions = new JPanel();
        actions.setLayout(new BoxLayout(actions, BoxLayout.Y_AXIS));
        geometryHint.setEditable(false);
        geometryHint.setFocusable(false);
        geometryHint.setOpaque(false);
        geometryHint.setFont(selection.getFont());
        geometryHint.setLineWrap(true);
        geometryHint.setWrapStyleWord(true);
        actions.add(geometryHint);
        JPanel buttons = new JPanel(new WrapLayout(FlowLayout.LEFT));
        remove.addActionListener(e -> {
            if (entity().getInternalBuilding().getCoordsList().size() > 1) {
                List<CubeCoords> hexes = new ArrayList<>(entity().getInternalBuilding().getCoordsList());
                hexes.remove(editor.selectedHex());
                configure(hexes);
            }
        });
        JButton rotate = new JButton("Rotate clockwise");
        rotate.addActionListener(e -> {
            transform(c -> new CubeCoords(-(int) c.r(), -(int) c.s(), -(int) c.q()), facing -> (facing + 1) % 6);
        });
        buttons.add(selection);
        buttons.add(remove);
        buttons.add(rotate);
        JButton mirror = new JButton("Mirror");
        mirror.addActionListener(e -> {
            transform(c -> new CubeCoords(-(int) c.q(), -(int) c.s(), -(int) c.r()),
                  facing -> (6 - facing) % 6);
        });
        buttons.add(mirror);
        actions.add(buttons);
        JPanel moveControls = new JPanel(new WrapLayout(FlowLayout.LEFT));
        JComboBox<String> direction = new JComboBox<>(BuildingEquipmentTab.FACINGS);
        JButton move = new JButton("Move selected hex");
        move.addActionListener(e -> {
            CubeCoords old = editor.selectedHex();
            CubeCoords target = old.toOffset().translated(direction.getSelectedIndex()).toCube();
            if (!entity().getInternalBuilding().getCoordsList().contains(target)) {
                transform(hex -> hex.equals(old) ? target : hex, facing -> facing);
            }
        });
        moveControls.add(direction);
        moveControls.add(move);
        actions.add(moveControls);
        sideControls.add(new JLabel("Wall/fence sides at selected hex:"));
        for (int side = 0; side < 6; side++) {
            int facing = side;
            sides[side] = new JCheckBox(BuildingEquipmentTab.FACINGS[side]);
            sides[side].setName("Structure side " + side);
            sides[side].addActionListener(e -> {
                if (!refreshing) {
                    BuildingDesign design = entity().getDesign();
                    CubeCoords hex = editor.selectedHex();
                    int mask = design.wallSides(hex) ^ (1 << facing);
                    design.getWallSides().put(hex, mask);
                    // The same physical side is shared by two hexes; keep one owner.
                    CubeCoords neighbor = hex.toOffset().translated(facing).toCube();
                    if ((mask & (1 << facing)) != 0 && entity().getInternalBuilding().getCoordsList().contains(neighbor)) {
                        design.getWallSides().put(neighbor, design.wallSides(neighbor) & ~(1 << ((facing + 3) % 6)));
                    }
                    editor.scheduleRefresh();
                }
            });
            sideControls.add(sides[side]);
        }
        actions.add(sideControls);
        JLabel deckLabel = new JLabel("Deck elevation at selected hex (0 = Ground)");
        deckLabel.setLabelFor(deckLevel);
        deckLevel.setName("Bridge deck elevation");
        deckLevel.setEnabled(false);
        bridgeControls.add(deckLabel);
        bridgeControls.add(deckLevel);
        bridgeStart.setName("Bridge start elevation");
        bridgeEnd.setName("Bridge end elevation");
        bridgeControls.add(bridgeEndpoints);
        bridgeControls.add(bridgeStart);
        bridgeControls.add(bridgeEnd);
        javax.swing.event.ChangeListener slopeChanged = e -> {
            if (!refreshing) {
                BuildingConstruction.BridgeSpan span = BuildingConstruction.bridgeSpan(entity().getInternalBuilding().getOriginalCoordsList());
                if (span == null) {
                    return;
                }
                // Keep endpoints editable for an invalid requested rise; the verifier reports it.
                span.distances().keySet().forEach(hex -> entity().getDesign().getBridgeDecks().put(hex,
                      span.level(hex, (int) bridgeStart.getValue(), (int) bridgeEnd.getValue())));
                editor.scheduleRefresh();
            }
        };
        bridgeStart.addChangeListener(slopeChanged);
        bridgeEnd.addChangeListener(slopeChanged);
        actions.add(bridgeControls);
        geometry.add(actions, BorderLayout.SOUTH);
        add(geometry, BorderLayout.CENTER);
        setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        basicInfo.addListener(this);
        type.addActionListener(e -> applySettings());
        buildingClass.addActionListener(e -> applySettings());
        levels.addChangeListener(e -> applySettings());
        baseLevel.addChangeListener(e -> applyFloorNumbering());
        automaticBaseLevel.addActionListener(e -> applyFloorNumbering());
        cf.addChangeListener(e -> applySettings());
        armor.addChangeListener(e -> applySettings());
        refresh();
        properties.setPreferredSize(new Dimension(identity.getPreferredSize().width
              + properties.getVerticalScrollBar().getPreferredSize().width, 0));
    }

    private static JLabel addField(JPanel panel, String label, javax.swing.JComponent field) {
        JLabel name = new JLabel(label + ":", SwingConstants.RIGHT);
        name.setLabelFor(field);
        field.setName(label);
        GridBagConstraints cell = new GridBagConstraints();
        cell.gridx = 0;
        cell.gridy = panel.getComponentCount() / 2;
        cell.anchor = GridBagConstraints.EAST;
        cell.insets = new Insets(2, 0, 2, 8);
        panel.add(name, cell);
        cell.gridx = 1;
        cell.weightx = 1;
        cell.fill = GridBagConstraints.HORIZONTAL;
        cell.insets = new Insets(2, 0, 2, 0);
        panel.add(field, cell);
        return name;
    }

    private void addStructureTypeField() {
        JLabel name = new JLabel("Structure type: ", SwingConstants.RIGHT);
        name.setLabelFor(structureType);
        structureType.setName("Structure type");
        GridBagConstraints cell = new GridBagConstraints();
        cell.gridx = 0;
        cell.gridy = 13;
        cell.weightx = 1;
        cell.fill = GridBagConstraints.HORIZONTAL;
        cell.anchor = GridBagConstraints.EAST;
        cell.insets = new Insets(2, 2, 2, 2);
        basicInfo.add(name, cell);
        cell.gridx = 1;
        cell.weightx = 0;
        cell.anchor = GridBagConstraints.WEST;
        basicInfo.add(structureType, cell);
    }

    private AbstractBuildingEntity entity() {
        return editor.getEntity();
    }

    ITechManager getTechManager() {
        return basicInfo;
    }

    JPanel createPancakePane() {
        JPanel panel = new JPanel(new BorderLayout(0, 6));
        JPanel header = new JPanel(new BorderLayout());
        header.add(absoluteCoordinates, BorderLayout.NORTH);
        pancakeLegend.setName("Building pancake legend");
        int markerHeight = pancakeLegend.getFontMetrics(pancakeLegend.getFont()).getHeight();
        for (BuildingMap.Feature feature : BuildingMap.Feature.values()) {
            JLabel entry = new JLabel(feature.label, new FeatureIcon(feature, markerHeight), SwingConstants.LEADING);
            entry.setIconTextGap(6);
            pancakeLegend.add(entry);
            pancakeLegendEntries.put(feature, entry);
        }
        header.add(pancakeLegend, BorderLayout.CENTER);
        panel.add(header, BorderLayout.NORTH);
        TabScrollPane scroll = new TabScrollPane(pancake);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        panel.add(scroll, BorderLayout.CENTER);
        return panel;
    }

    void refresh() {
        refreshing = true;
        basicInfo.removeListener(this);
        basicInfo.setFromEntity(entity());
        basicInfo.addListener(this);
        structureType.setSelectedIndex(entity() instanceof MobileStructure ? 1 : 0);
        icon.refresh();
        type.setModel(new DefaultComboBoxModel<>(java.util.Arrays.stream(BuildingType.values())
              .filter(value -> TestBuilding.limits(entity(), value, entity().getBldgClass()) != null || value == entity().getBuildingType())
              .toArray(BuildingType[]::new)));
        type.setSelectedItem(entity().getBuildingType());
        buildingClass.setSelectedIndex(entity().getBldgClass() >= 0 && entity().getBldgClass() < buildingClass.getItemCount()
              ? entity().getBldgClass() : -1);
        levels.setValue(entity().getInternalBuilding().getBuildingHeight());
        if (entity() instanceof MobileStructure mobile) {
            motive.setSelectedItem(mobile.getMovementMode());
            mobilePower.setModel(new DefaultComboBoxModel<>(java.util.Arrays.stream(StructureEngine.values())
                  .filter(power -> power.mobilePowerMultiplier(mobile.getMovementMode(), mobile.isClan()) > 0)
                  .toArray(StructureEngine[]::new)));
            mobilePower.setSelectedItem(mobile.getPowerSystem());
            maximumMP.setValue(mobile.getMaximumMP());
            operatingRange.setValue(mobile.getOperatingRange());
            uniformFuel.setSelected(mobile.getFuelLocations().isEmpty());
            hexFuel.setEnabled(!uniformFuel.isSelected());
            hexFuel.setValue(mobile.fuelWeightInHex(editor.selectedHex()));
            double allocatedFuel = mobile.getInternalBuilding().getOriginalCoordsList().stream()
                  .mapToDouble(mobile::fuelWeightInHex).sum();
            fuelAllocation.setText("Allocated: %.2f / %.2f tons".formatted(allocatedFuel, mobile.getFuelWeight()));
            ((SpinnerNumberModel) hexHeight.getModel()).setMaximum(entity().getInternalBuilding().getBuildingHeight());
            hexHeight.setValue(entity().getInternalBuilding().getHeight(editor.selectedHex()));
        }
        baseLevel.setValue(BuildingConstruction.baseLevel(entity()));
        automaticBaseLevel.setSelected(entity().getDesign().getBaseLevel() == null);
        baseLevel.setEnabled(!automaticBaseLevel.isSelected());
        baseLevelLabel.setVisible(entity().getBldgClass() != IBuilding.BRIDGE);
        baseLevel.getParent().setVisible(entity().getBldgClass() != IBuilding.BRIDGE);
        cf.setValue(entity().getOInternal(0));
        armor.setValue(entity().getOArmor(0));
        cfLabel.setText(BuildingConstruction.usesHexsides(entity()) ? "CF per hexside:" : "CF per hex:");
        armorLabel.setText(BuildingConstruction.usesHexsides(entity()) ? "Armor per hexside:" : "Armor per hex:");
        TestBuilding.Limits rule = TestBuilding.limits(entity());
        limits.setText("<html><b>Construction limits</b><br>" + (rule == null ? "Invalid type/class combination" : "CF %d–%d; %s; %d %s"
              .formatted(rule.minimumCF(), rule.maximumCF(), rule.hexes() == Integer.MAX_VALUE ? "no length limit"
                    : "up to " + rule.hexes() + " hexes", rule.levels(), entity().getBldgClass() == IBuilding.BRIDGE ? "deck" : "levels")) + "</html>");
        protectionScale.setText(entity().getConstructionCFScale() == 10 ? "<html>Capital CF and armor<br>1 point = 10 standard points</html>"
              : BuildingConstruction.usesHexsides(entity()) ? "Standard CF and armor per occupied hexside" : "Standard CF and armor per hex");
        geometryHint.setText("Click + to add a hex; click a hex to select it; double-click to edit its equipment.\n"
              + (entity().getBldgClass() == IBuilding.BRIDGE
              ? "Bridge decks follow a steady slope. Their ends must meet the underlying map terrain."
              : BuildingConstruction.usesHexsides(entity()) ? "Select occupied hexsides below. All segments share CF, armor and height."
                    : entity() instanceof MobileStructure ? "Select a hex to set its individual height. Capacity uses the structure's maximum height."
                          : "All hexes share the same height. Stepped buildings are separate buildings in a complex."));
        sideControls.setVisible(BuildingConstruction.usesHexsides(entity()));
        for (int side = 0; side < 6; side++) {
            sides[side].setSelected((entity().getDesign().wallSides(editor.selectedHex()) & (1 << side)) != 0);
        }
        bridgeControls.setVisible(entity().getBldgClass() == IBuilding.BRIDGE);
        deckLevel.setValue(entity().getDesign().bridgeDeck(editor.selectedHex()));
        BuildingConstruction.BridgeSpan span = entity().getBldgClass() == IBuilding.BRIDGE
              ? BuildingConstruction.bridgeSpan(entity().getInternalBuilding().getOriginalCoordsList()) : null;
        bridgeStart.setEnabled(span != null);
        bridgeEnd.setEnabled(span != null && span.length() > 0);
        if (span != null) {
            bridgeEndpoints.setText("Steady slope: " + editor.hexLabel(span.start()) + " → " + editor.hexLabel(span.end()));
            bridgeStart.setValue(entity().getDesign().bridgeDeck(span.start()));
            bridgeEnd.setValue(entity().getDesign().bridgeDeck(span.end()));
        } else {
            bridgeEndpoints.setText("Connect the bridge hexes to set a slope");
        }
        levels.setEnabled(entity().getBldgClass() != IBuilding.BRIDGE && entity().getBldgClass() != IBuilding.TENT
              && entity().getBldgClass() != IBuilding.GUN_EMPLACEMENT);
        type.setEnabled(entity().getBldgClass() != IBuilding.TENT && entity().getBldgClass() != IBuilding.FENCE);
        absoluteCoordinates.setSelected(editor.absoluteCoordinates());
        selection.setText("Editing: " + editor.hexLabel(editor.selectedHex()) + "/"
              + entity().getLevelLabel(displayedLevel(editor.selectedHex())));
        remove.setEnabled(entity().getInternalBuilding().getCoordsList().size() > 1);
        EnumSet<BuildingMap.Feature> features = EnumSet.noneOf(BuildingMap.Feature.class);
        EnumSet<BuildingMap.Feature> allFeatures = EnumSet.noneOf(BuildingMap.Feature.class);
        List<BuildingDesign.Door> mapDoors = entity().getDesign().getMapDoors();
        BuildingMap.FeatureIndex featureIndex = BuildingMap.featureIndex(entity(), mapDoors);
        for (CubeCoords hex : entity().getInternalBuilding().getCoordsList()) {
            for (int level : BuildingConstruction.mapLevels(entity())) {
                if (BuildingConstruction.occupiesMapLevel(entity(), hex, level)) {
                    allFeatures.addAll(featureIndex.features(hex, level));
                    if (level == displayedLevel(hex)) {
                        features.addAll(featureIndex.features(hex, level));
                    }
                }
            }
        }
        legendEntries.forEach((feature, entry) -> entry.setVisible(features.contains(feature)));
        legend.setVisible(!features.isEmpty());
        legend.revalidate();
        pancakeLegendEntries.forEach((feature, entry) -> entry.setVisible(allFeatures.contains(feature)));
        pancakeLegend.setVisible(!allFeatures.isEmpty());
        pancakeLegend.revalidate();
        footprint.revalidate();
        footprint.repaint();
        pancake.revalidate();
        pancake.repaint();
        refreshing = false;
    }

    private void applySettings() {
        if (!refreshing && type.getSelectedItem() != null && buildingClass.getSelectedIndex() >= 0) {
            if (buildingClass.getSelectedIndex() != entity().getBldgClass()) {
                refreshing = true;
                BuildingType chosen = (BuildingType) type.getSelectedItem();
                if (TestBuilding.limits(entity(), chosen, buildingClass.getSelectedIndex()) == null) {
                    chosen = java.util.Arrays.stream(BuildingType.values())
                          .filter(value -> TestBuilding.limits(entity(), value, buildingClass.getSelectedIndex()) != null).findFirst().orElseThrow();
                    type.setSelectedItem(chosen);
                }
                TestBuilding.Limits rule = TestBuilding.limits(entity(), chosen, buildingClass.getSelectedIndex());
                cf.setValue(Math.clamp((int) cf.getValue(), rule.minimumCF(), rule.maximumCF()));
                levels.setValue(Math.min((int) levels.getValue(), rule.levels()));
                refreshing = false;
            }
            configure(List.copyOf(entity().getInternalBuilding().getCoordsList()));
        }
    }

    private void applyFloorNumbering() {
        if (!refreshing) {
            entity().getDesign().setBaseLevel(automaticBaseLevel.isSelected() ? null : (Integer) baseLevel.getValue());
            editor.scheduleRefresh();
        }
    }

    private void applyPropulsion() {
        if (!refreshing && entity() instanceof MobileStructure mobile) {
            mobile.setMovementMode((EntityMovementMode) motive.getSelectedItem());
            mobile.setMaximumMP(((Number) maximumMP.getValue()).doubleValue());
            mobile.setPowerSystem((StructureEngine) mobilePower.getSelectedItem());
            mobile.setOperatingRange(((Number) operatingRange.getValue()).doubleValue());
            editor.scheduleRefresh();
        }
    }

    private void applyFuelDistribution() {
        if (!refreshing && entity() instanceof MobileStructure mobile) {
            Map<CubeCoords, Double> allocations = new LinkedHashMap<>();
            if (!uniformFuel.isSelected()) {
                mobile.getInternalBuilding().getOriginalCoordsList().forEach(hex ->
                      allocations.put(hex, mobile.fuelWeightInHex(hex)));
                allocations.put(editor.selectedHex(), ((Number) hexFuel.getValue()).doubleValue());
            }
            mobile.setFuelLocations(allocations);
            editor.scheduleRefresh();
        }
    }

    private void configure(List<CubeCoords> hexes) {
        var affected = BuildingUtil.topologyDoorChanges(entity(), hexes);
        if (affected.count() > 0 && javax.swing.JOptionPane.showOptionDialog(editor,
              affected.description() + "\nRemove them with this edit? Keeping them will leave validation errors.",
              "Doors affected by footprint edit", javax.swing.JOptionPane.DEFAULT_OPTION, javax.swing.JOptionPane.WARNING_MESSAGE,
              null, new String[] { "Remove affected doors", "Keep invalid doors" }, "Remove affected doors") == 0) {
            affected.remove().run();
        }
        // Materialize implicit north sides before transformations so their orientation follows the building.
        if (BuildingConstruction.usesHexsides(entity())) {
            entity().getInternalBuilding().getOriginalCoordsList().forEach(hex ->
                  entity().getDesign().getWallSides().putIfAbsent(hex, 1));
        }
        BuildingUtil.configure(entity(), (BuildingType) type.getSelectedItem(), buildingClass.getSelectedIndex(),
              (int) levels.getValue(), (int) cf.getValue(), (int) armor.getValue(), hexes);
        editor.scheduleRefresh();
    }

    private void transform(java.util.function.UnaryOperator<CubeCoords> transform, java.util.function.IntUnaryOperator facing) {
        CubeCoords selected = transform.apply(editor.selectedHex());
        List<CubeCoords> hexes = entity().getInternalBuilding().getOriginalCoordsList().stream().map(transform).toList();
        CubeCoords origin = hexes.contains(CubeCoords.ZERO) ? CubeCoords.ZERO : hexes.getFirst();
        BuildingUtil.transform(entity(), transform, facing);
        editor.selectLocation(selected.subtract(origin), editor.selectedFloor());
        editor.scheduleRefresh();
    }

    private int displayedLevel(CubeCoords hex) {
        return entity().getBldgClass() == IBuilding.BRIDGE ? entity().getDesign().bridgeDeck(hex) : editor.selectedFloor();
    }

    private record FeatureIcon(BuildingMap.Feature feature, int getIconHeight) implements Icon {
        @Override
        public int getIconWidth() {
            if (feature == BuildingMap.Feature.LARGE_DOOR) {
                return getIconHeight * 8 / 3;
            }
            return feature.glyph.isBlank() ? getIconHeight * 4 / 3
                  : (int) Math.ceil(2 * (getIconHeight - 2) / Math.sqrt(3)) + 2;
        }

        @Override
        public void paintIcon(Component component, Graphics graphics, int x, int y) {
            Graphics2D g = (Graphics2D) graphics.create();
            g.translate(x, y);
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int w = getIconWidth() - 2, h = getIconHeight - 2;
            Path2D marker = new Path2D.Double();
            if (feature == BuildingMap.Feature.LARGE_DOOR) {
                int half = w / 2;
                g.setColor(Color.BLACK);
                g.setStroke(new BasicStroke(2));
                g.drawLine(half / 2, h * 2 / 3, half + half / 2, h * 2 / 3);
                g.setStroke(new BasicStroke(1));
                for (int offset : new int[] { 0, half }) {
                    marker.append(new Polygon(new int[] { offset + half / 2, offset + half - 1, offset + 1 },
                          new int[] { 1, h, h }, 3), false);
                }
            } else if (feature.glyph.isBlank()) {
                marker.append(new Polygon(new int[] { w / 2, w - 1, 1 }, new int[] { 1, h, h }, 3), false);
            } else {
                // Legend hexes use a regular top view, independent of the pancake projection.
                double radius = h / Math.sqrt(3);
                for (int corner = 0; corner < 6; corner++) {
                    double px = getIconWidth() / 2.0 + radius * Math.cos(corner * Math.PI / 3);
                    double py = getIconHeight / 2.0 + radius * Math.sin(corner * Math.PI / 3);
                    if (corner == 0) {
                        marker.moveTo(px, py);
                    } else {
                        marker.lineTo(px, py);
                    }
                }
                marker.closePath();
            }
            g.setColor(Color.decode(feature.color));
            g.fill(marker);
            g.setColor(Color.BLACK);
            g.draw(marker);
            g.setFont(component.getFont().deriveFont((float) h - 2));
            FontMetrics metrics = g.getFontMetrics();
            g.drawString(feature.glyph, (getIconWidth() - metrics.stringWidth(feature.glyph)) / 2f,
                  (getIconHeight - metrics.getHeight()) / 2f + metrics.getAscent());
            g.dispose();
        }
    }

    private class Footprint extends JPanel implements Scrollable {
        private final boolean pancakeView;
        private final Map<CubeCoords, Polygon> cells = new LinkedHashMap<>();
        private final List<Layer> layers = new ArrayList<>();
        private BuildingDesign.Position revealedSelection;

        private record Layer(int level, Rectangle2D bounds, Map<CubeCoords, Polygon> cells) { }

        Footprint(boolean pancakeView) {
            this.pancakeView = pancakeView;
            setName(pancakeView ? "Building pancake" : "Building footprint");
            setToolTipText("");
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent event) {
                    if (!javax.swing.SwingUtilities.isLeftMouseButton(event)) {
                        return;
                    }
                    if (pancakeView) {
                        for (Layer layer : layers) {
                            if (layer.bounds().contains(event.getPoint())) {
                                CubeCoords hex = layer.cells().entrySet().stream()
                                      .filter(cell -> cell.getValue().contains(event.getPoint()))
                                      .map(Map.Entry::getKey).findFirst().orElse(null);
                                if (hex != null) {
                                    editor.selectLocation(hex, entity().getBldgClass() == IBuilding.BRIDGE ? 0 : layer.level());
                                }
                                return;
                            }
                        }
                        return;
                    }
                    for (Map.Entry<CubeCoords, Polygon> cell : cells.entrySet()) {
                        if (cell.getValue().contains(event.getPoint())) {
                            CubeCoords selected = cell.getKey();
                            List<CubeCoords> hexes = new ArrayList<>(entity().getInternalBuilding().getCoordsList());
                            if (!hexes.contains(selected)) {
                                hexes.add(selected);
                                configure(hexes);
                                editor.selectLocation(selected, editor.selectedFloor());
                            } else {
                                editor.selectLocation(selected, editor.selectedFloor());
                                if (event.getClickCount() == 2) {
                                    editor.showEquipment();
                                }
                            }
                            return;
                        }
                    }
                }
            });
        }

        @Override
        public String getToolTipText(MouseEvent event) {
            if (pancakeView) {
                return layers.stream().flatMap(layer -> layer.cells().entrySet().stream()
                            .filter(cell -> cell.getValue().contains(event.getPoint()))
                            .map(cell -> editor.hexLabel(cell.getKey()) + "/" + entity().getLevelLabel(layer.level())
                                  + " — click to select hex and level"))
                      .findFirst().orElse(null);
            }
            return cells.entrySet().stream().filter(cell -> cell.getValue().contains(event.getPoint()))
                  .map(cell -> editor.hexLabel(cell.getKey()) + "/" + entity().getLevelLabel(displayedLevel(cell.getKey())))
                  .findFirst().orElse(null);
        }

        @Override
        public Dimension getPreferredSize() {
            if (editor == null || !pancakeView) {
                return new Dimension(560, 370);
            }
            Rectangle2D bounds = pancakeBounds();
            int height = (int) Math.ceil(BuildingConstruction.mapLevels(entity()).size()
                  * (bounds.getHeight() * pancakeSize(bounds) + 44) + 20);
            return new Dimension(320, height);
        }

        @Override
        public Dimension getPreferredScrollableViewportSize() {
            return new Dimension(pancakeView ? 320 : 560, 370);
        }

        @Override
        public int getScrollableUnitIncrement(Rectangle visible, int orientation, int direction) {
            return 24;
        }

        @Override
        public int getScrollableBlockIncrement(Rectangle visible, int orientation, int direction) {
            return Math.max(24, (orientation == SwingConstants.VERTICAL ? visible.height : visible.width) - 24);
        }

        @Override
        public boolean getScrollableTracksViewportWidth() {
            return true;
        }

        @Override
        public boolean getScrollableTracksViewportHeight() {
            return !pancakeView || (getParent() != null && getParent().getHeight() >= getPreferredSize().height);
        }

        @Override
        protected void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);
            Graphics2D g = (Graphics2D) graphics.create();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            cells.clear();
            layers.clear();
            if (pancakeView) {
                paintPancake(g);
            } else {
                paintTop(g);
            }
            g.dispose();
        }

        private void paintTop(Graphics2D g) {
            List<CubeCoords> hexes = entity().getInternalBuilding().getCoordsList();
            BuildingMap.FeatureIndex featureIndex = BuildingMap.featureIndex(entity(), entity().getDesign().getMapDoors());
            LinkedHashSet<CubeCoords> visible = new LinkedHashSet<>(hexes);
            hexes.forEach(hex -> visible.addAll(hex.neighbors()));
            // Keep the construction origin and the first two added rings fixed while editing.
            double width = 11, height = 7;
            for (CubeCoords hex : visible) {
                width = Math.max(width, 3 * Math.abs(hex.q()) + 2);
                height = Math.max(height, 2 * Math.abs(hex.r() + hex.q() / 2) + 1);
            }
            double size = Math.max(1, Math.min((getWidth() - 30.0) / width,
                  (getHeight() - 30.0) / (Math.sqrt(3) * height)));
            AffineTransform transform = new AffineTransform(size, 0, 0, size, getWidth() / 2.0, getHeight() / 2.0);
            cells.putAll(paintHexes(g, List.copyOf(visible), editor.selectedFloor(), transform, size, false, featureIndex));
        }

        private Rectangle2D pancakeBounds() {
            List<CubeCoords> hexes = entity().getInternalBuilding().getCoordsList();
            double minX = Double.POSITIVE_INFINITY, minY = Double.POSITIVE_INFINITY;
            double maxX = Double.NEGATIVE_INFINITY, maxY = Double.NEGATIVE_INFINITY;
            for (CubeCoords hex : hexes) {
                double y = Math.sqrt(3) * (hex.r() + hex.q() / 2);
                double x = 1.5 * hex.q() - .35 * y;
                minX = Math.min(minX, x - 1.35);
                maxX = Math.max(maxX, x + 1.35);
                minY = Math.min(minY, .38 * y - .38);
                maxY = Math.max(maxY, .38 * y + .38);
            }
            return new Rectangle2D.Double(minX, minY, maxX - minX, maxY - minY);
        }

        private double pancakeSize(Rectangle2D bounds) {
            int width = getParent() == null ? getWidth() : getParent().getWidth();
            return Math.max(1, Math.min(56, ((width > 0 ? width : 320) - 48) / bounds.getWidth()));
        }

        private void paintPancake(Graphics2D g) {
            Rectangle2D bounds = pancakeBounds();
            double size = pancakeSize(bounds), step = bounds.getHeight() * size + 44;
            List<Integer> levels = BuildingConstruction.mapLevels(entity());
            BuildingMap.FeatureIndex featureIndex = BuildingMap.featureIndex(entity(), entity().getDesign().getMapDoors());
            double top = Math.max(10, (getHeight() - levels.size() * step) / 2);
            double x = (getWidth() - bounds.getWidth() * size) / 2 - bounds.getX() * size;
            for (int index = levels.size() - 1; index >= 0; index--) {
                int level = levels.get(index);
                double y = top + index * step;
                Rectangle2D hit = new Rectangle2D.Double(12, y, Math.max(1, getWidth() - 24), step - 6);
                if (level == displayedLevel(editor.selectedHex())) {
                    g.setColor(new Color(65, 125, 190));
                    g.setStroke(new BasicStroke(1.5f));
                    g.drawRoundRect(12, (int) y, Math.max(1, getWidth() - 24), (int) step - 6, 8, 8);
                }
                List<CubeCoords> hexes = entity().getInternalBuilding().getCoordsList().stream()
                      .filter(hex -> BuildingConstruction.occupiesMapLevel(entity(), hex, level)).toList();
                g.setColor(getForeground());
                g.drawString("Level: " + entity().getLevelLabel(level), 24, (float) y + 18);
                String count = hexes.stream().mapToLong(hex -> equipmentCount(hex, level)).sum() + " equipped";
                g.drawString(count, getWidth() - 24 - g.getFontMetrics().stringWidth(count), (float) y + 18);
                AffineTransform transform = new AffineTransform(size, 0, -.35 * size, .38 * size,
                      x, y + 32 - bounds.getY() * size);
                Map<CubeCoords, Polygon> polygons = paintHexes(g, hexes, level, transform, size, true, featureIndex);
                layers.add(new Layer(level, hit, polygons));
                // Outgoing shafts cover this floor; the next higher floor then covers the incoming shafts.
                if (index > 0) {
                    int upper = levels.get(index - 1);
                    g.setColor(Color.decode(BuildingMap.Feature.ELEVATOR.color));
                    g.setStroke(new BasicStroke(2f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10f,
                          new float[] { 4f, 4f }, 0f));
                    for (BuildingDesign.Elevator lift : entity().getDesign().getElevators()) {
                        if (lift.reaches(level) && lift.reaches(upper)
                              && BuildingConstruction.occupiesMapLevel(entity(), lift.hex(), level)
                              && BuildingConstruction.occupiesMapLevel(entity(), lift.hex(), upper)) {
                            Point2D center = center(transform, lift.hex());
                            for (int side : new int[] { -1, 1 }) {
                                double edge = center.getX() + side * size;
                                g.draw(new java.awt.geom.Line2D.Double(edge, center.getY(), edge, center.getY() - step));
                            }
                        }
                    }
                }
            }
            revealSelection();
        }

        private void revealSelection() {
            BuildingDesign.Position selection = new BuildingDesign.Position(editor.selectedHex(),
                  displayedLevel(editor.selectedHex()));
            if (selection.equals(revealedSelection)) {
                return;
            }
            for (Layer layer : layers) {
                Polygon selected = layer.cells().get(selection.hex());
                if (layer.level() == selection.level() && selected != null) {
                    revealedSelection = selection;
                    Rectangle bounds = selected.getBounds();
                    bounds.grow(8, 8);
                    // Wait until painting finishes before moving the viewport. Manual scrolling stays untouched
                    // until the editing location changes again.
                    SwingUtilities.invokeLater(() -> {
                        if (selection.equals(revealedSelection)) {
                            scrollRectToVisible(bounds);
                        }
                    });
                    return;
                }
            }
        }

        private Point2D center(AffineTransform transform, CubeCoords hex) {
            return transform.transform(new Point2D.Double(1.5 * hex.q(), Math.sqrt(3) * (hex.r() + hex.q() / 2)), null);
        }

        private long equipmentCount(CubeCoords hex, int level) {
            int floor = entity().getBldgClass() == IBuilding.BRIDGE ? 0 : level;
            return entity().getEquipmentInHex(hex).stream().filter(mount -> BuildingConstruction.equipmentPositions(entity(), mount)
                  .stream().anyMatch(position -> position.hex().equals(hex) && position.level() == floor)).count();
        }

        private Map<CubeCoords, Polygon> paintHexes(Graphics2D g, List<CubeCoords> visible, int floor,
              AffineTransform transform, double size, boolean pancake, BuildingMap.FeatureIndex featureIndex) {
            Map<CubeCoords, Polygon> polygons = new LinkedHashMap<>();
            List<CubeCoords> hexes = entity().getInternalBuilding().getCoordsList();
            BuildingUtil.SheetGrid labels = BuildingUtil.sheetGrid(hexes);
            for (CubeCoords hex : visible) {
                Point2D center = center(transform, hex);
                double x = center.getX(), y = center.getY();
                Polygon polygon = new Polygon();
                for (int i = 0; i < 6; i++) {
                    Point2D point = transform.deltaTransform(new Point2D.Double(Math.cos(i * Math.PI / 3), Math.sin(i * Math.PI / 3)), null);
                    polygon.addPoint((int) (x + point.getX()), (int) (y + point.getY()));
                }
                polygons.put(hex, polygon);
                boolean occupied = hexes.contains(hex);
                int level = pancake ? floor : displayedLevel(hex);
                boolean selected = occupied && hex.equals(editor.selectedHex()) && level == displayedLevel(editor.selectedHex());
                List<BuildingMap.Feature> features = occupied ? featureIndex.features(hex, level) : List.of();
                BuildingMap.Feature fill = BuildingMap.fill(features);
                g.setColor(fill != null ? Color.decode(fill.color) : selected ? new Color(180, 210, 240)
                      : occupied ? new Color(230, 230, 230) : Color.WHITE);
                g.fill(polygon);
                boolean wall = occupied && BuildingConstruction.usesHexsides(entity());
                g.setStroke(new BasicStroke(occupied && !wall ? 2f : 1f));
                g.setColor(occupied && !wall ? Color.BLACK : Color.GRAY);
                g.draw(polygon);
            }
            // Paint selection after adjacent fills, but underneath labels, doors and wall edges. Keep the halo
            // inside the hex so it cannot cover the incoming elevator shafts outside a pancake layer.
            Polygon selected = polygons.get(editor.selectedHex());
            if (selected != null && (!pancake || floor == displayedLevel(editor.selectedHex()))) {
                Graphics2D highlight = (Graphics2D) g.create();
                highlight.clip(selected);
                highlight.setColor(Color.WHITE);
                highlight.setStroke(new BasicStroke(7f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                highlight.draw(selected);
                highlight.setColor(new Color(30, 105, 210));
                highlight.setStroke(new BasicStroke(3f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                highlight.draw(selected);
                highlight.dispose();
            }
            for (CubeCoords hex : polygons.keySet()) {
                Point2D center = center(transform, hex);
                double x = center.getX(), y = center.getY();
                boolean occupied = hexes.contains(hex);
                int level = pancake ? floor : displayedLevel(hex);
                List<BuildingMap.Feature> features = occupied ? featureIndex.features(hex, level) : List.of();
                g.setColor(occupied ? Color.BLACK : Color.GRAY);
                String symbols = features.stream().filter(feature -> !feature.glyph.isBlank()).map(feature -> feature.glyph)
                      .collect(java.util.stream.Collectors.joining(" "));
                String coordinate = editor.absoluteCoordinates() ? BuildingUtil.absoluteHexLabel(hex) : labels.label(hex);
                String text = occupied ? (symbols.isEmpty() ? "" : symbols + " ") + coordinate + "/" + entity().getLevelLabel(level, true) : "+";
                long count = occupied ? equipmentCount(hex, level) : 0;
                if (pancake && count > 0) {
                    text += " · " + count;
                }
                Font font = g.getFont();
                int textWidth = g.getFontMetrics().stringWidth(text);
                if (occupied && textWidth > size * 1.6) {
                    g.setFont(font.deriveFont((float) (font.getSize2D() * size * 1.6 / textWidth)));
                }
                g.drawString(text, (float) (x - g.getFontMetrics().stringWidth(text) / 2.0), (float) (y + (pancake ? 3 : 0)));
                g.setFont(font);
                if (occupied && !pancake) {
                    String equipment = count + " items";
                    g.drawString(equipment, (float) (x - g.getFontMetrics().stringWidth(equipment) / 2.0), (float) (y + 12));
                }
            }
            // Decorations follow every fill so adjacent hexes cannot erase edge symbols.
            for (Map.Entry<CubeCoords, Polygon> cell : polygons.entrySet()) {
                int level = pancake ? floor : displayedLevel(cell.getKey());
                Polygon polygon = cell.getValue();
                Point2D center = center(transform, cell.getKey());
                double x = center.getX(), y = center.getY();
                for (BuildingMap.DoorMarker door : featureIndex.doors(cell.getKey(), level)) {
                    if (door.facing() < 0 || door.facing() > 5) {
                        continue;
                    }
                    int a = (door.facing() + 4) % 6, b = (a + 1) % 6;
                    Polygon triangle = new Polygon();
                    if (door.geometry() != null) {
                        Path2D opening = new Path2D.Double();
                        boolean first = true;
                        for (var point : door.geometry().line()) {
                            var projected = transform.deltaTransform(new Point2D.Double(point.x(), point.y()), null);
                            if (first) {
                                opening.moveTo(x + projected.getX(), y + projected.getY());
                                first = false;
                            } else {
                                opening.lineTo(x + projected.getX(), y + projected.getY());
                            }
                        }
                        g.setColor(Color.BLACK);
                        g.setStroke(new BasicStroke(pancake ? 3f : 5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                        g.draw(opening);
                        for (var point : door.geometry().arrow()) {
                            var projected = transform.deltaTransform(new Point2D.Double(point.x(), point.y()), null);
                            triangle.addPoint((int) Math.round(x + projected.getX()), (int) Math.round(y + projected.getY()));
                        }
                    } else {
                        for (double[] point : BuildingMap.doorPoints(new double[] { polygon.xpoints[a] - x, polygon.ypoints[a] - y },
                              new double[] { polygon.xpoints[b] - x, polygon.ypoints[b] - y })) {
                            triangle.addPoint((int) Math.round(x + point[0]), (int) Math.round(y + point[1]));
                        }
                    }
                    g.setStroke(new BasicStroke(1.5f));
                    g.setColor(Color.decode(door.feature().color));
                    g.fill(triangle);
                    g.setColor(Color.BLACK);
                    g.draw(triangle);
                }
            }
            if (BuildingConstruction.usesHexsides(entity())) {
                g.setColor(Color.BLACK);
                g.setStroke(new BasicStroke(pancake ? 3f : 4f));
                for (CubeCoords hex : hexes) {
                    Polygon polygon = polygons.get(hex);
                    if (polygon == null) {
                        continue;
                    }
                    for (int side = 0; side < 6; side++) {
                        if ((entity().getDesign().wallSides(hex) & (1 << side)) != 0) {
                            int a = (side + 4) % 6, b = (a + 1) % 6;
                            g.drawLine(polygon.xpoints[a], polygon.ypoints[a], polygon.xpoints[b], polygon.ypoints[b]);
                        }
                    }
                }
            }
            return polygons;
        }
    }

    @Override
    public void chassisChanged(String chassis) {
        entity().setChassis(chassis);
        editor.scheduleRefresh();
    }

    @Override
    public void modelChanged(String model) {
        entity().setModel(model);
        editor.scheduleRefresh();
    }

    @Override
    public void yearChanged(int year) {
        entity().setYear(year);
        updateTechLevel();
    }

    @Override
    public void buildYearChanged(int year) {
        entity().setOriginalBuildYear(year);
        editor.scheduleRefresh();
    }

    @Override
    public void updateTechLevel() {
        entity().setTechLevel(basicInfo.getTechLevel().getCompoundTechLevel(basicInfo.useClanTechBase()));
        editor.scheduleRefresh();
    }

    @Override
    public void sourceChanged(String source) {
        entity().setSource(source);
        editor.scheduleRefresh();
    }

    @Override
    public void publishedChanged(String published) {
        entity().setPublished(published);
        editor.scheduleRefresh();
    }

    @Override
    public void factionChanged(Faction faction) {
        entity().setTechFaction(faction);
        editor.scheduleRefresh();
    }

    @Override
    public void mulIdChanged(int mulId) {
        entity().setMulId(mulId);
        editor.scheduleRefresh();
    }

    @Override
    public void techBaseChanged(boolean clan, boolean mixed) {
        entity().setMixedTech(mixed);
        updateTechLevel();
    }

    @Override
    public void techLevelChanged(SimpleTechLevel techLevel) {
        updateTechLevel();
    }

    @Override
    public void roleChanged(UnitRole role) {
        entity().setUnitRole(role);
        editor.scheduleRefresh();
    }

    @Override
    public void manualBVChanged(int manualBV) {
        UnitUtil.setManualBV(manualBV, entity());
        editor.scheduleRefresh();
    }

    @Override
    public void refreshSummary() {
        editor.scheduleRefresh();
    }

    @Override
    public void walkChanged(int walkMP) {
    }

    @Override
    public void jumpChanged(int jumpMP, EquipmentType jumpJet) {
    }

    @Override
    public void jumpTypeChanged(EquipmentType jumpJet) {
    }
}
