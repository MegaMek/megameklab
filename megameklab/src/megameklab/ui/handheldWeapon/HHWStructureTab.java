/*
 * Copyright (C) 2025 The MegaMek Team. All Rights Reserved.
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
 */

package megameklab.ui.handheldWeapon;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import megamek.common.EquipmentType;
import megamek.common.HandheldWeapon;
import megamek.common.ITechManager;
import megamek.common.SimpleTechLevel;
import megamek.common.UnitRole;
import megameklab.ui.EntitySource;
import megameklab.ui.generalUnit.BasicInfoView;
import megameklab.ui.generalUnit.IconView;
import megameklab.ui.generalUnit.summary.AmmoSummaryItem;
import megameklab.ui.generalUnit.summary.ArmorSummaryItem;
import megameklab.ui.generalUnit.summary.HeatSinkSummaryItem;
import megameklab.ui.generalUnit.summary.MiscEquipmentSummaryItem;
import megameklab.ui.generalUnit.summary.SummaryView;
import megameklab.ui.generalUnit.summary.UnitTypeSummaryItem;
import megameklab.ui.generalUnit.summary.WeaponsSummaryItem;
import megameklab.ui.listeners.BuildListener;
import megameklab.ui.listeners.HHWBuildListener;
import megameklab.ui.util.ITab;
import megameklab.ui.util.RefreshListener;
import megameklab.util.UnitUtil;

public class HHWStructureTab extends ITab implements HHWBuildListener, BuildListener {
    private BasicInfoView panBasicInfo;
    private HHWChassisView panChassisView;
    private HHWEquipmentView panEquipmentView;
    private SummaryView panSummary;
    private IconView panIcon;

    RefreshListener refresh;
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
              new MiscEquipmentSummaryItem());
        panIcon = new IconView();


        panBasicInfo.setFromEntity(getEntity());
        panChassisView.setFromEntity(getEntity());
        panIcon.setFromEntity(getEntity());

        JPanel leftPanel = new JPanel(), centerPanel = new JPanel(), rightPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        leftPanel.add(panBasicInfo);
        leftPanel.add(panChassisView);

        centerPanel.add(panIcon);

        rightPanel.add(panEquipmentView);
        rightPanel.add(panSummary);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.weightx = 0;
        gridBagConstraints.weighty = 1;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        masterPanel.add(leftPanel, gridBagConstraints);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        masterPanel.add(centerPanel, gridBagConstraints);
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        masterPanel.add(rightPanel, gridBagConstraints);

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
    }

    @Override
    public void armorChanged(int armor) {
        getEntity().initializeArmor(armor, HandheldWeapon.LOC_GUN);
        getEntity().setArmorTonnage(getEntity().getArmorWeight());
        refresh();
        refresh.refreshArmor();
        refresh.refreshStatus();
        refresh.refreshPreview();
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
    }

    @Override
    public void modelChanged(String model) {
        getEntity().setModel(model);
        refresh.refreshPreview();
        refresh.refreshHeader();
    }

    @Override
    public void yearChanged(int year) {
        getEntity().setYear(year);
        updateTechLevel();
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
        refresh.refreshSummary();
        refresh.refreshPreview();
    }

    @Override
    public void mulIdChanged(int mulId) {
        getEntity().setMulId(mulId);
        refresh.refreshSummary();
    }

    @Override
    public void techBaseChanged(boolean clan, boolean mixed) {
        if ((clan != getEntity().isClan()) || (mixed != getEntity().isMixedTech())) {
            getEntity().setMixedTech(mixed);
            updateTechLevel();
        }
    }

    @Override
    public void techLevelChanged(SimpleTechLevel techLevel) {
        updateTechLevel();
    }

    @Override
    public void roleChanged(UnitRole role) {
        getEntity().setUnitRole(role);
        refresh.refreshSummary();
        refresh.refreshPreview();
    }

    @Override
    public void manualBVChanged(int manualBV) {
        UnitUtil.setManualBV(manualBV, getEntity());
        refresh.refreshPreview();
        refresh.refreshStatus();
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
