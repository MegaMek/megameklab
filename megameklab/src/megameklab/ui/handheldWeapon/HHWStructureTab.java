/*
 * MegaMekLab - Copyright (C) 2025 The MegaMek Team
 *
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation; either version 2 of the License, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details.
 */

package megameklab.ui.handheldWeapon;

import megamek.common.*;
import megameklab.ui.EntitySource;
import megameklab.ui.generalUnit.BasicInfoView;
import megameklab.ui.generalUnit.IconView;
import megameklab.ui.generalUnit.summary.*;
import megameklab.ui.listeners.BuildListener;
import megameklab.ui.listeners.HHWBuildListener;
import megameklab.ui.util.ITab;
import megameklab.ui.util.RefreshListener;
import megameklab.util.UnitUtil;

import javax.swing.*;
import java.awt.*;

public class HHWStructureTab extends ITab implements HHWBuildListener, BuildListener {
    private BasicInfoView panBasicInfo;
    private HHWChassisView panChassisView;
    private HHWEquipmentView panEquipmentView;
    private SummaryView panSummary;
    private IconView panIcon;

    RefreshListener refresh = null;
    JPanel masterPanel;

    public HHWStructureTab(EntitySource eSource, RefreshListener refresh) {
        super(eSource);
        this.refresh = refresh;
        setLayout(new BorderLayout());
        setUpPanels();
        this.add(masterPanel, BorderLayout.CENTER);
        refresh();
    }

    private void setUpPanels() {
        masterPanel = new JPanel(new GridBagLayout());
        panBasicInfo = new BasicInfoView(getEntity().getConstructionTechAdvancement());
        panChassisView = new HHWChassisView();
        panEquipmentView = new HHWEquipmentView(eSource, refresh);
        panSummary = new SummaryView(eSource,
            new UnitTypeSummaryItem(),
            new ArmorSummaryItem(),
            new WeaponsSummaryItem(),
            new HeatSinkSummaryItem(),
            new AmmoSummaryItem(),
            new MiscEquipmentSummaryItem()
        );
        panIcon = new IconView();


        panBasicInfo.setFromEntity(getEntity());
        panChassisView.setFromEntity(getEntity());
        panIcon.setFromEntity(getEntity());

        JPanel leftPanel = new JPanel(), centerPanel = new JPanel(),  rightPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        leftPanel.add(panBasicInfo);
        leftPanel.add(panChassisView);

        centerPanel.add(panIcon);

        rightPanel.add(panEquipmentView);
        rightPanel.add(panSummary);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(5, 5, 5, 5);
        masterPanel.add(leftPanel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        masterPanel.add(centerPanel, gbc);
        gbc.gridx = 2;
        gbc.gridy = 0;
        masterPanel.add(rightPanel, gbc);

        panBasicInfo.setBorder(BorderFactory.createTitledBorder("Basic Information"));
        panChassisView.setBorder(BorderFactory.createTitledBorder("Structure"));
        panEquipmentView.setBorder(BorderFactory.createTitledBorder("Equipment"));
        panSummary.setBorder(BorderFactory.createTitledBorder("Summary"));
        panIcon.setBorder(BorderFactory.createTitledBorder("Icon"));
        panSummary.refresh();
        panEquipmentView.refresh();
        panIcon.refresh();
    }

    public void refresh() {
        removeAllListeners();
        panBasicInfo.setFromEntity(getEntity());
        panChassisView.setFromEntity(getEntity());
        panEquipmentView.refresh();
        panSummary.refresh();
        panIcon.refresh();
        addAllListeners();
    }

    public void removeAllListeners() {
        panBasicInfo.removeListener(this);
        panChassisView.removeListener(this);
    }

    public void addAllListeners() {
        panBasicInfo.addListener(this);
        panChassisView.addListener(this);
    }

    public void addRefreshedListener(RefreshListener l) {
        refresh = l;
    }

    public ITechManager getTechManager() {
        return panBasicInfo;
    }

    @Override
    public void weightChanged(double weight) {
        getEntity().setWeight(weight);
        refresh.refreshStatus();
        refresh.refreshPreview();
        refresh.refreshStructure();
        refresh.markDirty();
    }

    @Override
    public void armorChanged(int armor) {
        getEntity().initializeArmor(armor, HandheldWeapon.LOC_GUN);
        getEntity().setArmorTonnage(getEntity().getArmorWeight());
        refresh();
        refresh.refreshArmor();
        refresh.refreshStatus();
        refresh.refreshPreview();
        refresh.markDirty();
    }

    @Override
    public void refreshSummary() {
        panSummary.refresh();
    }

    @Override
    public void chassisChanged(String chassis) {
        getEntity().setChassis(chassis);
        refresh.refreshPreview();
        refresh.refreshHeader();
        refresh.markDirty();
    }

    @Override
    public void modelChanged(String model) {
        getEntity().setModel(model);
        refresh.refreshPreview();
        refresh.refreshHeader();
        refresh.markDirty();
    }

    @Override
    public void yearChanged(int year) {
        getEntity().setYear(year);
        updateTechLevel();
        refresh.markDirty();
    }

    @Override
    public void updateTechLevel() {
        removeAllListeners();
        getEntity().setTechLevel(panBasicInfo.getTechLevel().getCompoundTechLevel(panBasicInfo.useClanTechBase()));
        if (UnitUtil.checkEquipmentByTechLevel(getEntity(), panBasicInfo)) {
            refresh.refreshEquipment();
        } else {
            refresh.refreshEquipmentTable();
        }
        refresh.refreshPreview();
        refresh.refreshStatus();
    }

    @Override
    public void sourceChanged(String source) {
        getEntity().setSource(source);
        refresh.markDirty();
    }

    @Override
    public void mulIdChanged(int mulId) {
        getEntity().setMulId(mulId);
        refresh.markDirty();
    }

    @Override
    public void techBaseChanged(boolean clan, boolean mixed) {
        if ((clan != getEntity().isClan()) || (mixed != getEntity().isMixedTech())) {
            getEntity().setMixedTech(mixed);
            updateTechLevel();
            refresh.markDirty();
        }
    }

    @Override
    public void techLevelChanged(SimpleTechLevel techLevel) {
        updateTechLevel();
        refresh.markDirty();
    }

    @Override
    public void roleChanged(UnitRole role) {
        getEntity().setUnitRole(role);
        refresh.refreshPreview();
        refresh.markDirty();
    }

    @Override
    public void manualBVChanged(int manualBV) {
        UnitUtil.setManualBV(manualBV, getEntity());
        refresh.refreshPreview();
        refresh.refreshStatus();
        refresh.markDirty();
    }

    @Override
    public void walkChanged(int walkMP) { }

    @Override
    public void jumpChanged(int jumpMP, EquipmentType jumpJet) { }

    @Override
    public void jumpTypeChanged(EquipmentType jumpJet) { }
}
