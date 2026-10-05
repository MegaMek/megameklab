/*
 * Copyright (C) 2026 The MegaMek Team. All Rights Reserved.
 * SPDX-License-Identifier: GPL-3.0-or-later
 */
package megameklab.ui.building;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;
import java.util.function.IntFunction;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import megamek.common.bays.Bay;
import megamek.common.bays.FirstClassQuartersCargoBay;
import megamek.common.bays.SecondClassQuartersCargoBay;
import megamek.common.bays.SteerageQuartersCargoBay;
import megamek.common.units.BuildingConstruction;
import megamek.common.units.BuildingDesign;
import megameklab.ui.generalUnit.TransportTab;

/** Native transport controls plus the optional passenger/residential quarters used by buildings (TO:AR p. 129). */
class BuildingTransportTab extends JPanel {
    private record Quarters(String name, Class<? extends Bay> type, IntFunction<Bay> create) {
    }

    private static final List<Quarters> QUARTERS = List.of(
          new Quarters("First class", FirstClassQuartersCargoBay.class, FirstClassQuartersCargoBay::new),
          new Quarters("Second class", SecondClassQuartersCargoBay.class, SecondClassQuartersCargoBay::new),
          new Quarters("Steerage", SteerageQuartersCargoBay.class, SteerageQuartersCargoBay::new));
    private final BuildingMainUI editor;
    private final TransportTab transport;
    private final JSpinner[] occupants = new JSpinner[QUARTERS.size()];
    private boolean refreshing;
    private final JComboBox<String> baySelector = new JComboBox<>();

    BuildingTransportTab(BuildingMainUI editor) {
        this.editor = editor;
        setLayout(new BorderLayout(10, 10));
        transport = new TransportTab(editor);
        transport.addRefreshedListener(editor);
        JPanel quarters = new JPanel(new GridLayout(1, QUARTERS.size() * 2, 8, 0));
        quarters.setBorder(BorderFactory.createTitledBorder("Quarters (people)"));
        for (int index = 0; index < QUARTERS.size(); index++) {
            Quarters type = QUARTERS.get(index);
            JSpinner count = new JSpinner(new SpinnerNumberModel(0, 0, 100000, 1));
            occupants[index] = count;
            count.setName(type.name() + " quarters");
            JLabel label = new JLabel(type.name());
            label.setLabelFor(count);
            quarters.add(label);
            quarters.add(count);
            count.addChangeListener(event -> {
                if (!refreshing) {
                    var oldBays = editor.getEntity().getTransportBays().stream().filter(type.type()::isInstance).toList();
                    double oldWeight = oldBays.stream().mapToDouble(Bay::getWeight).sum();
                    var spaces = oldBays.stream().flatMap(bay -> BuildingConstruction.baySpaces(editor.getEntity(), bay).stream()).toList();
                    boolean placed = oldBays.stream().anyMatch(editor.getEntity().getDesign().getBaySpace()::containsKey);
                    oldBays.forEach(editor.getEntity()::removeTransporter);
                    if ((int) count.getValue() > 0) {
                        var bay = type.create().apply((int) count.getValue());
                        editor.getEntity().addTransporter(bay);
                        if (placed && oldWeight > 0) {
                            editor.getEntity().getDesign().getBaySpace().put(bay, spaces.stream().map(space ->
                                  new BuildingDesign.Space(space.position(), space.tons() * bay.getWeight() / oldWeight)).toList());
                        }
                    }
                    editor.scheduleRefresh();
                }
            });
        }
        add(quarters, BorderLayout.NORTH);
        add(transport, BorderLayout.CENTER);
        JPanel placement = new JPanel();
        placement.setBorder(BorderFactory.createTitledBorder("Bay and quarters placement"));
        baySelector.setName("Building bay placement");
        placement.add(baySelector);
        JButton allocate = new JButton("Assign hexes and floors…");
        allocate.addActionListener(e -> {
            int index = baySelector.getSelectedIndex();
            if (index >= 0) {
                BuildingPlacementDialogs.bay(editor, editor.getEntity().getTransportBays().get(index));
            }
        });
        placement.add(allocate);
        JButton doors = new JButton("Assign doors…");
        doors.setName("Assign building bay doors");
        doors.addActionListener(event -> {
            int index = baySelector.getSelectedIndex();
            if (index >= 0) {
                BuildingPlacementDialogs.bayDoors(editor, editor.getEntity().getTransportBays().get(index));
            }
        });
        placement.add(doors);
        add(placement, BorderLayout.SOUTH);
    }

    void refresh() {
        refreshing = true;
        for (int index = 0; index < QUARTERS.size(); index++) {
            Quarters type = QUARTERS.get(index);
            int count = (int) editor.getEntity().getTransportBays().stream().filter(type.type()::isInstance)
                  .mapToDouble(Bay::getCapacity).sum();
            occupants[index].setValue(count);
        }
        transport.refresh();
        int selected = baySelector.getSelectedIndex();
        baySelector.removeAllItems();
        for (var bay : editor.getEntity().getTransportBays()) {
            baySelector.addItem(bay.getUnusedString() + " (" + bay.getWeight() + " t)");
        }
        if (selected >= 0 && selected < baySelector.getItemCount()) {
            baySelector.setSelectedIndex(selected);
        }
        refreshing = false;
    }
}
