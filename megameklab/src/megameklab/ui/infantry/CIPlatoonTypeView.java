/*
 * Copyright (C) 2017-2025 The MegaMek Team. All Rights Reserved.
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
package megameklab.ui.infantry;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import megamek.client.ui.util.DisplayTextField;
import megamek.common.interfaces.ITechManager;
import megamek.common.units.EntityMovementMode;
import megamek.common.units.Infantry;
import megamek.common.units.InfantryMount;
import megamek.common.verifier.TestInfantry;
import megameklab.ui.generalUnit.BuildView;
import megameklab.ui.generalUnit.StandardBuildLabel;
import megameklab.ui.listeners.InfantryBuildListener;
import megameklab.ui.util.CustomComboBox;
import megameklab.ui.util.WidthControlComponent;

/**
 * Infantry structure tab panel for selecting platoon movement type and number/size of squads.
 *
 * @author Neoancient
 */
public class CIPlatoonTypeView extends BuildView implements ActionListener, ChangeListener {
    List<InfantryBuildListener> listeners = new CopyOnWriteArrayList<>();

    public void addListener(InfantryBuildListener l) {
        listeners.add(l);
    }

    public void removeListener(InfantryBuildListener l) {
        listeners.remove(l);
    }

    private final ResourceBundle resourceMap = ResourceBundle.getBundle("megameklab.resources.Views");

    private final SpinnerNumberModel spnNumSquadsModel = new SpinnerNumberModel(4, 1, null, 1);
    private final SpinnerNumberModel spnSquadSizeModel = new SpinnerNumberModel(7, 1, 10, 1);

    private final CustomComboBox<InfantryMotiveType> cbMotiveType = new CustomComboBox<>
          (i -> resourceMap.getString(i.resourceId));

    private final JLabel lblNumSquads = new StandardBuildLabel(
          resourceMap.getString("PlatoonTypeView.spnNumSquads.text"));
    private final JSpinner spnNumSquads = new JSpinner(spnNumSquadsModel);
    private final JSpinner spnSquadSize = new JSpinner(spnSquadSizeModel);
    private final JLabel lblMaxSize = new JLabel();
    private final JLabel lblMaxSquadSize = new JLabel();
    private final JLabel lblBeastMountLabel = new StandardBuildLabel();
    private final DisplayTextField lblBeastMountType = new DisplayTextField();

    private final ITechManager techManager;

    private int specialization = 0;
    private boolean isFieldGunner = false;
    private InfantryMount mount = null;

    public CIPlatoonTypeView(ITechManager techManager, CIStructureTab structureTab) {
        this.techManager = techManager;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = STANDARD_INSETS;
        gbc.weightx = 1;

        // left column, labels
        add(new StandardBuildLabel(resourceMap.getString("PlatoonTypeView.cbMotiveType.text")), gbc);
        add(lblBeastMountLabel, gbc);
        add(lblNumSquads, gbc);
        add(new StandardBuildLabel(resourceMap.getString("PlatoonTypeView.spnSquadSize.text")), gbc);

        // right columns, values
        gbc.gridx++;
        gbc.weightx = 0;
        add(cbMotiveType, gbc);
        add(lblBeastMountType, gbc);
        add(spnNumSquads, gbc);
        add(spnSquadSize, gbc);
        add(new WidthControlComponent(), gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        lblMaxSize.setToolTipText(resourceMap.getString("PlatoonTypeView.lblMaxSize.tooltip"));
        var maxSizePanel = new JPanel();
        maxSizePanel.add(new JLabel(resourceMap.getString("PlatoonTypeView.lblMaxSize.text")));
        maxSizePanel.add(lblMaxSize);
        add(maxSizePanel, gbc);

        gbc.gridy++;
        var maxSquadSizePanel = new JPanel();
        lblMaxSquadSize.setToolTipText(resourceMap.getString("PlatoonTypeView.lblMaxSquadSize.tooltip"));
        maxSquadSizePanel.add(new JLabel(resourceMap.getString("PlatoonTypeView.lblMaxSquadSize.text")));
        maxSquadSizePanel.add(lblMaxSquadSize);
        add(maxSquadSizePanel, gbc);

        cbMotiveType.setToolTipText(resourceMap.getString("PlatoonTypeView.cbMotiveType.tooltip"));
        cbMotiveType.addActionListener(this);

        lblBeastMountLabel.setText(resourceMap.getString("PlatoonTypeView.lblBeastMountLabel.text"));

        spnNumSquads.setToolTipText(resourceMap.getString("PlatoonTypeView.spnNumSquads.tooltip"));
        spnNumSquads.addChangeListener(this);

        spnSquadSize.setToolTipText(resourceMap.getString("PlatoonTypeView.spnSquadSize.tooltip"));
        spnSquadSize.addChangeListener(this);

        lblBeastMountType.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                structureTab.showMountChoiceTable();
            }
        });
    }

    void setFromEntity(Infantry inf) {
        specialization = inf.getSpecializations();
        isFieldGunner = inf.hasFieldWeapon();
        mount = inf.getMount();
        refresh();
        cbMotiveType.removeActionListener(this);
        if (inf.getMount() != null) {
            cbMotiveType.setSelectedItem(InfantryMotiveType.BEAST_MOUNTED);

        } else if (inf.getMovementMode() == EntityMovementMode.VTOL) {
            cbMotiveType.setSelectedItem(inf.hasMicrolite() ?
                  InfantryMotiveType.MICROLITE : InfantryMotiveType.VTOL);

        } else if (inf.getMovementMode() == EntityMovementMode.INF_UMU) {
            cbMotiveType.setSelectedItem((inf.getOriginalJumpMP() > 1) ?
                  InfantryMotiveType.UMU_MOTORIZED : InfantryMotiveType.UMU);

        } else {
            for (var type : InfantryMotiveType.values()) {
                if (type.mode == inf.getMovementMode()) {
                    cbMotiveType.setSelectedItem(type);
                }
            }
        }
        cbMotiveType.addActionListener(this);

        if (inf.getSquadCount() <= (Integer) spnNumSquadsModel.getMaximum()) {
            spnNumSquads.removeChangeListener(this);
            spnNumSquads.setValue(inf.getSquadCount());
            spnNumSquads.addChangeListener(this);
        } else {
            spnNumSquads.setValue(spnNumSquadsModel.getMaximum());
        }
        if (inf.getSquadSize() <= (Integer) spnSquadSizeModel.getMaximum()) {
            spnSquadSize.removeChangeListener(this);
            spnSquadSize.setValue(inf.getSquadSize());
            spnSquadSize.addChangeListener(this);
        } else {
            spnSquadSize.setValue(spnSquadSizeModel.getMaximum());
        }
    }

    private boolean legalMotiveType(InfantryMotiveType motiveType) {
        if ((specialization & (Infantry.MOUNTAIN_TROOPS | Infantry.PARATROOPS)) != 0) {
            return motiveType == InfantryMotiveType.FOOT;
        } else {
            return techManager.isLegal(Infantry.getMotiveTechAdvancement(motiveType.mode))
                  && (!isFieldGunner || motiveType.legalFieldGun);
        }
    }

    private void refresh() {
        InfantryMotiveType prevMotive = (InfantryMotiveType) cbMotiveType.getSelectedItem();
        cbMotiveType.removeActionListener(this);
        cbMotiveType.removeAllItems();
        for (var type : InfantryMotiveType.values()) {
            if (legalMotiveType(type)) {
                cbMotiveType.addItem(type);
            }
        }
        cbMotiveType.setSelectedItem(prevMotive);
        cbMotiveType.addActionListener(this);
        if (cbMotiveType.getSelectedIndex() < 0) {
            cbMotiveType.setSelectedIndex(0);
        }

        int maxSize = TestInfantry.maxUnitSize(getMovementMode(), isAltMode(),
              (specialization & (Infantry.COMBAT_ENGINEERS | Infantry.MOUNTAIN_TROOPS)) != 0, mount);
        int maxSquad = TestInfantry.maxSquadSize(getMovementMode(), isAltMode(), mount);
        spnNumSquads.removeChangeListener(this);
        spnSquadSize.removeChangeListener(this);
        spnNumSquadsModel.setMaximum(Math.min(TestInfantry.maxSquadCount(getMovementMode(), isAltMode(),
                    specialization, mount),
              (maxSize / spnSquadSizeModel.getNumber().intValue())));
        spnSquadSizeModel.setMaximum(maxSquad);
        spnNumSquads.addChangeListener(this);
        spnSquadSize.addChangeListener(this);

        lblMaxSize.setText(String.valueOf(maxSize));
        lblMaxSquadSize.setText(String.valueOf(maxSquad));

        lblBeastMountType.setEnabled(isBeastMounted());
        lblBeastMountLabel.setEnabled(isBeastMounted());

        lblNumSquads.setText(isBeastMounted() && !isLargeBeastMount() ?
              resourceMap.getString("PlatoonTypeView.spnNumSquads.creatures") :
              resourceMap.getString("PlatoonTypeView.spnNumSquads.text"));
        lblBeastMountType.setText(isBeastMounted() ? mount.name() : "");
    }

    private EntityMovementMode getMovementMode() {
        InfantryMotiveType type = (InfantryMotiveType) cbMotiveType.getSelectedItem();
        return (type != null) ? type.mode : EntityMovementMode.INF_LEG;
    }

    private boolean isBeastMounted() {
        return getMovementMode().isNone();
    }

    private boolean isLargeBeastMount() {
        return (mount != null) && (mount.size() == InfantryMount.BeastSize.LARGE);
    }

    private boolean isAltMode() {
        InfantryMotiveType type = (InfantryMotiveType) cbMotiveType.getSelectedItem();
        return (type == InfantryMotiveType.MICROLITE) || (type == InfantryMotiveType.UMU_MOTORIZED);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if ((e.getSource() == spnNumSquads) || (e.getSource() == spnSquadSize)) {
            listeners.forEach(l -> l.platoonSizeChanged(spnNumSquadsModel.getNumber().intValue(),
                  spnSquadSizeModel.getNumber().intValue()));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cbMotiveType) {
            listeners.forEach(l -> l.motiveTypeChanged(getMovementMode(), isAltMode()));
        }
    }
}
