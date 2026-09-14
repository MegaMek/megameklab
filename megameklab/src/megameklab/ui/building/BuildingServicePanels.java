// Copyright (C) 2026 The MegaMek Team
// SPDX-License-Identifier: GPL-3.0-or-later

package megameklab.ui.building;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.Scrollable;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import megamek.client.ui.WrapLayout;
import megamek.common.board.CubeCoords;
import megamek.common.units.BuildingConstruction;
import megamek.common.units.BuildingDesign;
import megamek.common.units.BuildingDoors;
import megamek.common.units.IBuilding;
import megameklab.util.BuildingUtil;

/** Service controls always address the selected hex; structural doors also follow its starting floor. */
final class BuildingServicePanels {
    private final BuildingMainUI editor;
    final JPanel doors = new JPanel(new BorderLayout(6, 6));
    final JPanel elevators = new JPanel(new BorderLayout(6, 6));

    BuildingServicePanels(BuildingMainUI editor) {
        this.editor = editor;
        doors.setName("Building doors");
        elevators.setName("Building elevators");
        doors.setBorder(BorderFactory.createTitledBorder("Doors"));
        elevators.setBorder(BorderFactory.createTitledBorder("Elevators"));
        doors.setPreferredSize(new Dimension(360, 430));
        doors.setMinimumSize(new Dimension(360, 260));
        elevators.setMinimumSize(new Dimension(280, 260));
    }

    private int internalSides(CubeCoords hex, int floor) {
        int mask = 0;
        var building = editor.getEntity().getInternalBuilding();
        for (int side = 0; side < 6; side++) {
            var neighbor = hex.toOffset().translated(side).toCube();
            if (building.getOriginalCoordsList().contains(neighbor) && floor <= building.getHeight(neighbor)) {
                mask |= 1 << side;
            }
        }
        return mask;
    }

    void refresh() {
        refreshDoors();
        refreshElevators();
        doors.revalidate();
        doors.repaint();
        elevators.revalidate();
        elevators.repaint();
    }

    private void refreshDoors() {
        doors.removeAll();
        var entity = editor.getEntity();
        var hex = editor.selectedHex();
        var position = new BuildingDesign.Position(hex, editor.selectedFloor());
        var all = entity.getDesign().getDoors();
        var local = all.stream().filter(door -> door.position().equals(position)).toList();
        int selected = local.stream().mapToInt(door -> 1 << door.facing()).reduce(0, (a, b) -> a | b);
        int blocked = internalSides(hex, 0) | all.stream().filter(door -> door.position().hex().equals(hex)
              && !door.position().equals(position)).mapToInt(door -> 1 << door.facing()).reduce(0, (a, b) -> a | b);
        int height = entity.getInternalBuilding().getHeight(hex) - position.level();
        boolean disabled = BuildingConstruction.hasNoInterior(entity) || entity.getBldgClass() == IBuilding.GUN_EMPLACEMENT || height < 1;
        doors.add(new JLabel("Hex " + editor.hexLabel(hex) + " / " + entity.getLevelLabel(position.level())), BorderLayout.NORTH);
        JPanel diagram = new JPanel(null);
        diagram.setPreferredSize(new Dimension(340, 340));
        var sides = new BuildingHexSides("Door side", editor.hexLabel(hex), "Doors · " + entity.getLevelLabel(position.level(), true),
              selected, blocked, disabled, side -> {
                  var existing = all.stream().filter(door -> door.position().equals(position) && door.facing() == side).findFirst();
                  if (existing.isPresent()) {
                      all.remove(existing.get());
                      BuildingDoors.normalize(all);
                  } else {
                      all.add(new BuildingDesign.Door(position, side, 1));
                  }
                  editor.scheduleRefresh();
              });
        sides.setBounds(60, 60, 220, 220);
        diagram.add(sides);
        for (var door : local) {
            JPanel field = new JPanel(new BorderLayout());
            field.add(new JLabel("Height", JLabel.CENTER), BorderLayout.NORTH);
            // Include invalid loaded values so the design remains editable for repair.
            JSpinner value = new JSpinner(new SpinnerNumberModel(door.height(), Math.min(1, door.height()),
                  Math.max(height, door.height()), 1));
            value.setName(BuildingUtil.facingLabel(door.facing()) + " door height");
            value.setEnabled(!disabled);
            value.addChangeListener(event -> {
                int next = (int) value.getValue();
                var related = all.stream().filter(other -> (other.position().equals(door.position()) && other.facing() == door.facing())
                      || (door.linkGroup() > 0 && other.linkGroup() == door.linkGroup())).toList();
                if (related.stream().anyMatch(other -> next > entity.getInternalBuilding().getHeight(other.position().hex()) - other.position().level())) {
                    value.setValue(door.height());
                    return;
                }
                all.replaceAll(other -> related.contains(other)
                      ? new BuildingDesign.Door(other.position(), other.facing(), next, other.linkGroup()) : other);
                editor.scheduleRefresh();
            });
            field.add(value, BorderLayout.CENTER);
            double angle = -Math.PI / 2 + door.facing() * Math.PI / 3;
            field.setBounds((int) (170 + Math.cos(angle) * 145 - 27), (int) (170 + Math.sin(angle) * 145 - 24), 54, 48);
            diagram.add(field);
        }
        for (int i = 0; i < all.size(); i++) {
            var a = all.get(i);
            for (var b : all.subList(i + 1, all.size())) {
                if ((!local.contains(a) && !local.contains(b)) || !BuildingDoors.touch(a, b)
                      || (internalSides(a.position().hex(), 0) & (1 << a.facing())) != 0
                      || (internalSides(b.position().hex(), 0) & (1 << b.facing())) != 0) {
                    continue;
                }
                var source = local.contains(a) ? a : b;
                var other = source == a ? b : a;
                boolean linked = a.linkGroup() > 0 && a.linkGroup() == b.linkGroup();
                boolean sameHex = source.position().hex().equals(other.position().hex());
                String target = (sameHex ? "" : editor.hexLabel(other.position().hex()) + "/") + BuildingUtil.facingLabel(other.facing());
                JButton link = new JButton(sameHex ? linked ? "Unlink" : "Link"
                      : "<html><center>" + (linked ? "Unlink" : "Link") + "<br>" + target + "</center></html>");
                link.setName((linked ? "Unlink " : "Link ") + BuildingUtil.facingLabel(source.facing()) + " door with " + target);
                link.setToolTipText(link.getName());
                link.setMargin(new java.awt.Insets(2, 2, 2, 2));
                link.setEnabled(!disabled);
                var vertex = BuildingDoors.vertices(a).stream().filter(BuildingDoors.vertices(b)::contains).findFirst().orElseThrow();
                double x = (vertex.x() - 3 * hex.q()) / 2, y = (vertex.y() - 2 * hex.r() - hex.q()) * Math.sqrt(3) / 2;
                double radius = Math.hypot(x, y);
                link.setBounds((int) (170 + 143 * x / radius - 34), (int) (170 + 143 * y / radius - 20), 68, 40);
                link.addActionListener(event -> {
                    if (linked) {
                        BuildingDoors.unlink(all, source);
                    } else {
                        BuildingDoors.link(all, source, other);
                    }
                    editor.scheduleRefresh();
                });
                diagram.add(link);
                diagram.setComponentZOrder(link, 0);
            }
        }
        JPanel centered = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        centered.add(diagram);
        doors.add(new JScrollPane(centered), BorderLayout.CENTER);
        doors.add(new JLabel("<html>Click edges to toggle doors. Link touching doors of matching height.<br>Linked heights change together; Unlink separates a segment.</html>"), BorderLayout.SOUTH);
    }

    private void refreshElevators() {
        elevators.removeAll();
        var entity = editor.getEntity();
        var hex = editor.selectedHex();
        int height = entity.getInternalBuilding().getHeight(hex);
        var all = entity.getDesign().getElevators();
        var content = new ElevatorContent();
        JPanel lifts = new JPanel(new GridBagLayout());
        content.add(lifts, BorderLayout.NORTH);
        elevators.add(new JLabel("Hex " + editor.hexLabel(hex)), BorderLayout.NORTH);
        for (int index = 0; index < all.size(); index++) {
            var lift = all.get(index);
            if (!lift.hex().equals(hex)) {
                continue;
            }
            final int slot = index;
            JPanel panel = new JPanel(new BorderLayout(4, 4));
            panel.setBorder(BorderFactory.createTitledBorder("Elevator " + (index + 1)));
            GridBagConstraints row = new GridBagConstraints();
            row.gridx = 0;
            row.gridy = lifts.getComponentCount();
            row.weightx = 1;
            row.fill = GridBagConstraints.HORIZONTAL;
            lifts.add(panel, row);
            JPanel fields = new JPanel(new WrapLayout(FlowLayout.LEFT, 6, 4));
            var capacity = new JSpinner(new SpinnerNumberModel(lift.capacity(), Math.min(.1, lift.capacity()),
                  Double.MAX_VALUE, 1.0));
            capacity.setName("Elevator capacity");
            // The default editor measures Double.MAX_VALUE, creating a thousands-of-pixels-wide field.
            ((JSpinner.DefaultEditor) capacity.getEditor()).getTextField().setColumns(6);
            var from = new JSpinner(new SpinnerNumberModel(lift.lowerLevel(), Math.min(0, lift.lowerLevel()),
                  Math.max(height, lift.upperLevel()), 1));
            var to = new JSpinner(new SpinnerNumberModel(lift.upperLevel(), Math.min(0, lift.upperLevel()),
                  Math.max(height, lift.upperLevel()), 1));
            from.setName("Elevator lowest level");
            to.setName("Elevator highest level");
            ((JSpinner.DefaultEditor) from.getEditor()).getTextField().setColumns(3);
            ((JSpinner.DefaultEditor) to.getEditor()).getTextField().setColumns(3);
            for (var field : java.util.List.of(java.util.Map.entry("Capacity (t)", capacity),
                  java.util.Map.entry("From", from), java.util.Map.entry("To", to))) {
                JPanel pair = new JPanel(new FlowLayout(FlowLayout.LEFT, 4, 0));
                pair.add(new JLabel(field.getKey()));
                pair.add(field.getValue());
                fields.add(pair);
            }
            panel.add(fields, BorderLayout.NORTH);
            capacity.addChangeListener(event -> {
                var current = all.get(slot);
                all.set(slot, new BuildingDesign.Elevator(hex, ((Number) capacity.getValue()).doubleValue(), current.exits()));
                editor.scheduleRefresh();
            });
            javax.swing.event.ChangeListener range = event -> {
                int start = (int) from.getValue(), end = (int) to.getValue();
                if (start < 0 || start >= end || end > height) {
                    return;
                }
                var current = all.get(slot);
                var exits = new HashMap<Integer, Integer>();
                for (int level = start; level <= end; level++) {
                    exits.put(level, current.exits().getOrDefault(level, internalSides(hex, level)));
                }
                all.set(slot, new BuildingDesign.Elevator(hex, current.capacity(), exits));
                editor.scheduleRefresh();
            };
            from.addChangeListener(range);
            to.addChangeListener(range);
            JPanel floors = new JPanel(new WrapLayout(FlowLayout.LEFT, 8, 8));
            floors.setName("Elevator " + (index + 1) + " floors");
            panel.add(floors, BorderLayout.CENTER);
            for (int level : lift.exits().keySet().stream().sorted().toList()) {
                String label = BuildingUtil.roofLevelLabel(entity, level);
                var sides = new BuildingHexSides("Elevator " + (index + 1) + " level " + label + " access", editor.hexLabel(hex),
                      "Level " + label, lift.exits().get(level), 63 & ~internalSides(hex, level), false, side -> {
                          var current = all.get(slot);
                          var exits = new HashMap<>(current.exits());
                          exits.put(level, exits.getOrDefault(level, 0) ^ (1 << side));
                          all.set(slot, new BuildingDesign.Elevator(hex, current.capacity(), exits));
                          editor.scheduleRefresh();
                      });
                floors.add(sides);
            }
            JButton remove = new JButton("Remove elevator");
            remove.addActionListener(event -> {
                all.remove(slot);
                editor.scheduleRefresh();
            });
            JPanel actions = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 4));
            actions.add(remove);
            panel.add(actions, BorderLayout.SOUTH);
        }
        var scroll = new JScrollPane(content, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setName("Building elevator scroll");
        elevators.add(scroll, BorderLayout.CENTER);
        JButton add = new JButton("Add elevator in this hex");
        add.setEnabled(!BuildingConstruction.hasNoInterior(entity) && !entity.getDesign().isOpenSpace());
        add.addActionListener(event -> {
            int floor = Math.min(editor.selectedFloor(), height - 1);
            all.add(new BuildingDesign.Elevator(hex, Math.min(20, entity.getInternalBuilding().getCurrentCF(hex)),
                  java.util.Map.of(floor, internalSides(hex, floor), floor + 1, internalSides(hex, floor + 1))));
            editor.scheduleRefresh();
        });
        elevators.add(add, BorderLayout.SOUTH);
    }

    /** Keep floor rows inside the available width; scroll vertically only when they cannot all fit. */
    private static class ElevatorContent extends JPanel implements Scrollable {
        ElevatorContent() {
            super(new BorderLayout());
        }

        @Override
        public Dimension getPreferredScrollableViewportSize() {
            return new Dimension(480, 430);
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
            return getParent() != null && getParent().getHeight() >= getPreferredSize().height;
        }
    }
}
