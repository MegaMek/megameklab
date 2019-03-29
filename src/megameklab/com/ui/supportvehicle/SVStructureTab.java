/*
 * MekBuilder - unit design companion of MegaMek
 * Copyright (C) 2017 The MegaMek Team
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package megameklab.com.ui.supportvehicle;

import megamek.common.Entity;
import megamek.common.EquipmentType;
import megamek.common.ITechManager;
import megamek.common.SimpleTechLevel;
import megameklab.com.ui.EntitySource;
import megameklab.com.ui.view.BasicInfoView;
import megameklab.com.ui.view.MovementView;
import megameklab.com.ui.view.listeners.SVBuildListener;
import megameklab.com.util.ITab;
import megameklab.com.util.RefreshListener;

import javax.swing.*;
import java.awt.*;

/**
 * Structure tab for support vehicle construction
 */
public class SVStructureTab extends ITab implements SVBuildListener {

    private RefreshListener refresh = null;
    private JPanel masterPanel;
    private BasicInfoView panBasicInfo;
    private JPanel panChassis;
    private MovementView panMovement;
    private JPanel panSummary;
    private JPanel panChassisMod;

    public SVStructureTab(EntitySource eSource) {
        super(eSource);
        setLayout(new BorderLayout());
        setupPanels();
        add(masterPanel, BorderLayout.CENTER);
        refresh();
    }

    private Entity getSV() {
        return eSource.getEntity();
    }

    private void setupPanels() {
        masterPanel = new JPanel(new GridBagLayout());
        panBasicInfo = new BasicInfoView(getSV().getConstructionTechAdvancement());
        panChassis = new JPanel();
        panMovement = new MovementView(panBasicInfo);
        panSummary = new JPanel();
        panChassisMod = new JPanel();

        GridBagConstraints gbc;

        JPanel leftPanel = new JPanel();
        JPanel midPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        midPanel.setLayout(new BoxLayout(midPanel, BoxLayout.Y_AXIS));
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        leftPanel.add(panBasicInfo);
        leftPanel.add(Box.createVerticalStrut(6));
        leftPanel.add(panChassis);
        leftPanel.add(Box.createGlue());

        midPanel.add(panMovement);
        midPanel.add(panSummary);
        midPanel.add(Box.createVerticalGlue());

        rightPanel.add(panChassisMod);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = java.awt.GridBagConstraints.NONE;
        gbc.weightx = 0.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        masterPanel.add(leftPanel, gbc);
        gbc.gridx = 1;
        masterPanel.add(midPanel, gbc);
        gbc.gridx = 2;
        masterPanel.add(rightPanel, gbc);

        panBasicInfo.setBorder(BorderFactory.createTitledBorder("Basic Information"));
        panChassis.setBorder(BorderFactory.createTitledBorder("Chassis"));
        panMovement.setBorder(BorderFactory.createTitledBorder("Movement"));
        panSummary.setBorder(BorderFactory.createTitledBorder("Summary"));
        panChassisMod.setBorder(BorderFactory.createTitledBorder("Chassis Modifications"));
    }

    public void refresh() {
        removeAllListeners();

        panBasicInfo.setFromEntity(getSV());
        panMovement.setFromEntity(getSV());

        addAllListeners();
    }

    public ITechManager getTechManager() {
        return panBasicInfo;
    }

    private void removeAllListeners() {
        panBasicInfo.removeListener(this);
        panMovement.removeListener(this);
    }

    private void addAllListeners() {
        panBasicInfo.addListener(this);
        panMovement.addListener(this);
    }

    public void addRefreshedListener(RefreshListener l) {
        refresh = l;
    }

    @Override
    public void refreshSummary() {
//        panSummary.refresh();
    }

    @Override
    public void chassisChanged(String chassis) {
        getSV().setChassis(chassis);
        refresh.refreshHeader();
        refresh.refreshPreview();
    }

    @Override
    public void modelChanged(String model) {
        getSV().setModel(model);
        refresh.refreshHeader();
        refresh.refreshPreview();
    }

    @Override
    public void yearChanged(int year) {
        getSV().setYear(year);
        updateTechLevel();
    }

    @Override
    public void updateTechLevel() {

    }

    @Override
    public void sourceChanged(String source) {
        getSV().setSource(source);
        refresh.refreshPreview();
    }

    @Override
    public void techBaseChanged(boolean clan, boolean mixed) {
        if ((clan != getSV().isClan()) || (mixed != getSV().isMixedTech())) {
            getSV().setMixedTech(mixed);
            updateTechLevel();
        }
    }

    @Override
    public void techLevelChanged(SimpleTechLevel techLevel) {
        updateTechLevel();
    }

    @Override
    public void manualBVChanged(int manualBV) {
        getSV().setManualBV(manualBV);
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
