/*
 * Copyright (C) 2008-2025 The MegaMek Team. All Rights Reserved.
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

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Optional;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import megamek.common.SimpleTechLevel;
import megamek.common.enums.Faction;
import megamek.common.equipment.EquipmentType;
import megamek.common.equipment.EquipmentTypeLookup;
import megamek.common.equipment.Mounted;
import megamek.common.equipment.WeaponType;
import megamek.common.exceptions.LocationFullException;
import megamek.common.interfaces.ITechManager;
import megamek.common.units.EntityMovementMode;
import megamek.common.units.Infantry;
import megamek.common.units.InfantryMount;
import megamek.common.units.UnitRole;
import megamek.common.verifier.TestInfantry;
import megamek.common.weapons.infantry.InfantryWeapon;
import megameklab.ui.EntitySource;
import megameklab.ui.generalUnit.BasicInfoView;
import megameklab.ui.generalUnit.IconView;
import megameklab.ui.listeners.InfantryBuildListener;
import megameklab.ui.util.ITab;
import megameklab.ui.util.RefreshListener;
import megameklab.ui.util.TabScrollPane;
import megameklab.util.InfantryUtil;
import megameklab.util.UnitUtil;

/*
 * Original author - jtighe (torren@users.sourceforge.net)
 */
public class CIStructureTab extends ITab implements InfantryBuildListener {
    private RefreshListener refresh;

    public static final int T_INFANTRY_WEAPONS = 0;
    public static final int T_FIELD_GUNS = 1;
    public static final int T_ARMOR_KIT = 2;
    public static final int T_SPECIALIZATION = 3;
    public static final int T_MOUNT = 4;
    public static final int T_AUGMENTATION = 5;
    private static final EquipmentType ANTI_MEK_GEAR = EquipmentType.get(EquipmentTypeLookup.ANTI_MEK_GEAR);

    private final BasicInfoView basicInfoView;
    private final CIPlatoonTypeView platoonTypeView;
    private final CIWeaponView weaponView;
    private final IconView iconView;
    private final CIAdvancedView advancedView;

    private final String[] tabNames = { "Weapons", "Field Guns", "Armor Kit", "Specializations", "Mount",
                                        "Augmentation" };

    private JTabbedPane equipmentPane;

    private final CIEquipmentView weaponChoiceTable;
    private final CIFieldGunTableView fieldGunChoiceTable;
    private final CIArmorView armorChoiceTable;
    private final CISpecializationView specializationChoiceTable;
    private final CIMountView mountChoiceTable;
    private final CIAugmentationView augmentationChoiceTable;

    public CIStructureTab(EntitySource eSource) {
        super(eSource);
        basicInfoView = new BasicInfoView(getInfantry().getConstructionTechAdvancement());
        platoonTypeView = new CIPlatoonTypeView(basicInfoView, this);
        weaponView = new CIWeaponView(basicInfoView, this);
        weaponChoiceTable = new CIEquipmentView(eSource, basicInfoView);
        fieldGunChoiceTable = new CIFieldGunTableView(eSource, basicInfoView);
        armorChoiceTable = new CIArmorView(eSource, basicInfoView);
        specializationChoiceTable = new CISpecializationView(eSource);
        mountChoiceTable = new CIMountView(eSource);
        augmentationChoiceTable = new CIAugmentationView(eSource);
        iconView = new IconView();
        advancedView = new CIAdvancedView(eSource, this);
        setUpPanels();
        refresh();
    }

    public void setUpPanels() {
        GridBagConstraints gbc;

        basicInfoView.setBorder(BorderFactory.createTitledBorder("Basic Information"));
        platoonTypeView.setBorder(BorderFactory.createTitledBorder("Movement and Size"));
        weaponView.setBorder(BorderFactory.createTitledBorder("Weapons"));
        advancedView.setBorder(BorderFactory.createTitledBorder("Advanced"));

        equipmentPane = new JTabbedPane();
        equipmentPane.addTab(tabNames[T_INFANTRY_WEAPONS], weaponChoiceTable);
        equipmentPane.addTab(tabNames[T_FIELD_GUNS], fieldGunChoiceTable);
        equipmentPane.addTab(tabNames[T_ARMOR_KIT], armorChoiceTable);
        equipmentPane.addTab(tabNames[T_SPECIALIZATION], specializationChoiceTable);
        equipmentPane.addTab(tabNames[T_MOUNT], mountChoiceTable);
        equipmentPane.addTab(tabNames[T_AUGMENTATION], augmentationChoiceTable);

        JPanel leftPanel = new JPanel(new GridBagLayout());
        leftPanel.setBorder(new EmptyBorder(0, 15, 0, 15));
        GridBagConstraints leftGbc = new GridBagConstraints();
        leftGbc.gridx = 0;
        leftGbc.fill = GridBagConstraints.BOTH;
        leftGbc.anchor = GridBagConstraints.NORTH;
        leftGbc.weighty = 0;
        leftPanel.add(basicInfoView, leftGbc);
        leftPanel.add(iconView, leftGbc);
        leftPanel.add(platoonTypeView, leftGbc);
        leftPanel.add(weaponView, leftGbc);
        leftPanel.add(advancedView, leftGbc);
        leftGbc.weighty = 1;
        leftPanel.add(Box.createVerticalGlue(), leftGbc);
        var leftPanelScrollPane = new TabScrollPane(leftPanel) {
            @Override
            public Dimension getMinimumSize() {
                // The structure panel should never use horizontal scrolling, so force it to be as wide as needed
                int preferredWidth = leftPanel.getPreferredSize().width +
                      getVerticalScrollBar().getPreferredSize().width;
                return new Dimension(preferredWidth, super.getMinimumSize().height);
            }
        };

        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(5, 5, 5, 5);
        add(leftPanelScrollPane, gbc);

        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.insets = new Insets(5, 0, 5, 0);
        add(equipmentPane, gbc);
    }

    public ITechManager getTechManager() {
        return basicInfoView;
    }

    public void setTechFaction(Faction techFaction) {
        basicInfoView.setTechFaction(techFaction);
    }

    public void refresh() {

        basicInfoView.setFromEntity(getInfantry());
        platoonTypeView.setFromEntity(getInfantry());
        weaponView.setFromEntity(getInfantry());
        iconView.setFromEntity(getEntity());
        advancedView.setFromEntity();

        removeAllListeners();

        weaponChoiceTable.refresh();
        fieldGunChoiceTable.refresh();
        armorChoiceTable.refresh();
        specializationChoiceTable.refresh();
        mountChoiceTable.refresh();
        augmentationChoiceTable.refresh();

        enableTabs();

        addAllListeners();
    }

    public void addAllListeners() {
        basicInfoView.addListener(this);
        platoonTypeView.addListener(this);
        weaponView.addListener(this);
        specializationChoiceTable.addListener(this);
    }

    public void removeAllListeners() {
        weaponView.removeListener(this);
        basicInfoView.removeListener(this);
        platoonTypeView.removeListener(this);
        specializationChoiceTable.removeListener(this);
    }

    public void addRefreshedListener(RefreshListener l) {
        refresh = l;
        weaponChoiceTable.addRefreshedListener(refresh);
        fieldGunChoiceTable.addRefreshedListener(refresh);
        armorChoiceTable.addRefreshedListener(refresh);
        mountChoiceTable.addRefreshedListener(refresh);
        augmentationChoiceTable.addRefreshedListener(refresh);
    }

    public void setAsCustomization() {
        basicInfoView.setAsCustomization();
    }

    private void updateSpecializations() {
        advancedView.updateSpecializations();
        if (getInfantry().hasSpecialization(Infantry.TAG_TROOPS)
              && ((getInfantry().getSecondaryWeaponsPerSquad() < 2)
              || (getInfantry().getSecondaryWeapon() == null)
              || !getInfantry().getSecondaryWeapon().hasFlag(WeaponType.F_TAG))) {
            InfantryUtil.replaceMainWeapon(getInfantry(),
                  (InfantryWeapon) EquipmentType.get(EquipmentTypeLookup.INFANTRY_TAG), true);
            getInfantry().setSecondaryWeaponsPerSquad(2);
        }
    }

    private void enableTabs() {
        SimpleTechLevel level = basicInfoView.getTechLevel();
        advancedView.enableTabs(level);
        if (level.ordinal() >= SimpleTechLevel.ADVANCED.ordinal()) {
            equipmentPane.setEnabledAt(T_FIELD_GUNS,
                  getInfantry().getMovementMode() == EntityMovementMode.INF_MOTORIZED
                        || getInfantry().getMovementMode() == EntityMovementMode.TRACKED
                        || getInfantry().getMovementMode() == EntityMovementMode.WHEELED);
            equipmentPane.setEnabledAt(T_ARMOR_KIT, true);
            equipmentPane.setEnabledAt(T_SPECIALIZATION, true);
            equipmentPane.setEnabledAt(T_MOUNT, getInfantry().getMount() != null);
            // Experimental level
            equipmentPane.setEnabledAt(T_AUGMENTATION, level.ordinal() >= SimpleTechLevel.EXPERIMENTAL.ordinal());
        } else {
            equipmentPane.setEnabledAt(T_FIELD_GUNS, false);
            equipmentPane.setEnabledAt(T_ARMOR_KIT, false);
            equipmentPane.setEnabledAt(T_SPECIALIZATION, false);
            equipmentPane.setEnabledAt(T_MOUNT, false);
            equipmentPane.setEnabledAt(T_AUGMENTATION, false);
        }
        if (!equipmentPane.isEnabledAt(equipmentPane.getSelectedIndex())) {
            equipmentPane.setSelectedIndex(T_INFANTRY_WEAPONS);
        }
    }

    private void showEquipmentChoiceTable(int choice) {
        if ((choice >= 0) && (choice < equipmentPane.getTabCount()) && equipmentPane.isEnabledAt(choice)) {
            equipmentPane.setSelectedIndex(choice);
        }
    }

    void showWeaponChoiceTable() {
        showEquipmentChoiceTable(T_INFANTRY_WEAPONS);
    }

    void showFieldGunChoiceTable() {
        showEquipmentChoiceTable(T_FIELD_GUNS);
    }

    void showArmorChoiceTable() {
        showEquipmentChoiceTable(T_ARMOR_KIT);
    }

    void showSpecializationChoiceTable() {
        showEquipmentChoiceTable(T_SPECIALIZATION);
    }

    void showAugmentationChoiceTable() {
        showEquipmentChoiceTable(T_AUGMENTATION);
    }

    void showMountChoiceTable() {
        showEquipmentChoiceTable(T_MOUNT);
    }

    public void refreshEquipmentTable() {
        weaponChoiceTable.refresh();
    }

    @Override
    public void refreshSummary() {}

    @Override
    public void chassisChanged(String chassis) {
        getInfantry().setChassis(chassis);
        refresh.refreshHeader();
        refresh.refreshPreview();
        iconView.refresh();
    }

    @Override
    public void modelChanged(String model) {
        getInfantry().setModel(model);
        refresh.refreshHeader();
        refresh.refreshPreview();
        iconView.refresh();
    }

    @Override
    public void yearChanged(int year) {
        getInfantry().setYear(year);
        updateTechLevel();
    }

    @Override
    public void updateTechLevel() {
        if (!basicInfoView.isLegal(getInfantry().getMotiveTechAdvancement())) {
            motiveTypeChanged(EntityMovementMode.INF_LEG, false);
        }
        getInfantry().setTechLevel(basicInfoView.getTechLevel().getCompoundTechLevel(basicInfoView.useClanTechBase()));
        UnitUtil.checkEquipmentByTechLevel(getInfantry(), basicInfoView);
        InfantryUtil.resetInfantryArmor(getInfantry());
        platoonTypeView.setFromEntity(getInfantry());
        weaponView.setFromEntity(getInfantry());
        updateSpecializations();
        enableTabs();
        weaponChoiceTable.refresh();
        fieldGunChoiceTable.refresh();
        armorChoiceTable.refresh();
        specializationChoiceTable.refresh();
        augmentationChoiceTable.refresh();
        refresh.refreshPreview();
    }

    @Override
    public void sourceChanged(String source) {
        getInfantry().setSource(source);
        refresh.refreshPreview();
    }

    @Override
    public void techBaseChanged(boolean clan, boolean mixed) {
        if ((clan != getInfantry().isClan()) || (mixed != getInfantry().isMixedTech())) {
            getInfantry().setMixedTech(mixed);
            updateTechLevel();
        }
    }

    @Override
    public void techLevelChanged(SimpleTechLevel techLevel) {
        updateTechLevel();
    }

    @Override
    public void manualBVChanged(int manualBV) {
        UnitUtil.setManualBV(manualBV, getEntity());
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

    @Override
    public void motiveTypeChanged(EntityMovementMode movementMode, boolean alt) {
        if (alt && (movementMode == EntityMovementMode.INF_UMU)) {
            getInfantry().setMotorizedScuba();
        } else if (movementMode.isNone()) {
            // Pick a default
            getInfantry().setMount(InfantryMount.HORSE);
        } else {
            getInfantry().setMovementMode(movementMode);
            getInfantry().setMount(null);
        }
        getInfantry().setMicrolite(alt && (movementMode == EntityMovementMode.VTOL));

        if (getInfantry().getMovementMode() != EntityMovementMode.INF_MOTORIZED
              && getInfantry().getMovementMode() != EntityMovementMode.TRACKED
              && getInfantry().getMovementMode() != EntityMovementMode.WHEELED) {
            InfantryUtil.replaceFieldGun(getInfantry(), null, 0);
        }
        enableTabs();
        TestInfantry.adaptAntiMekAttacks(getInfantry());
        platoonTypeView.setFromEntity(getInfantry());
        weaponView.setFromEntity(getInfantry());
        specializationChoiceTable.refresh();
        mountChoiceTable.refresh();
        refresh.refreshPreview();
        refresh.refreshStatus();
    }

    @Override
    public void platoonSizeChanged(int numSquads, int squadSize) {
        getInfantry().setSquadCount(numSquads);
        getInfantry().setSquadSize(squadSize);
        getInfantry().autoSetInternal();
        platoonTypeView.setFromEntity(getInfantry());
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

    @Override
    public void specializationsChanged() {
        updateSpecializations();
        platoonTypeView.setFromEntity(getInfantry());
        weaponView.setFromEntity(getInfantry());
        weaponChoiceTable.refresh();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

    @Override
    public void numSecondaryChanged(final int count) {
        if (getInfantry().getSecondaryWeapon() == null) {
            getInfantry().setSecondaryWeaponsPerSquad(0);
        } else {
            if (count == 0) {
                InfantryUtil.replaceMainWeapon(getInfantry(), null, true);
                getInfantry().setSpecializations(getInfantry().getSpecializations() & ~Infantry.TAG_TROOPS);
            }
            getInfantry().setSecondaryWeaponsPerSquad(count);
        }
        refresh.refreshStatus();
        refresh.refreshPreview();
        weaponView.setFromEntity(getInfantry());
    }

    @Override
    public void numFieldGunsChanged(final int count) {
        Optional<WeaponType> fieldGun = getInfantry().getWeaponList().stream()
              .filter(m -> m.getLocation() == Infantry.LOC_FIELD_GUNS)
              .map(Mounted::getType)
              .findAny();
        InfantryUtil.replaceFieldGun(getInfantry(), fieldGun.orElse(null), count);
        refresh.refreshStatus();
        refresh.refreshPreview();
        weaponView.setFromEntity(getInfantry());
    }

    @Override
    public void antiMekChanged(final boolean antiMek) {
        if (antiMek) {
            try {
                getInfantry().addEquipment(ANTI_MEK_GEAR, Infantry.LOC_INFANTRY);
            } catch (LocationFullException ignored) {
                // Not on infantry
            }
        } else {
            UnitUtil.removeAllMounted(getInfantry(), ANTI_MEK_GEAR);
        }
        TestInfantry.adaptAntiMekAttacks(getInfantry());
        refresh.refreshStatus();
    }

    @Override
    public void walkChanged(int walkMP) {
        // not used by conventional infantry
    }

    @Override
    public void jumpChanged(int jumpMP, EquipmentType jumpJet) {
        // not used by conventional infantry
    }

    @Override
    public void jumpTypeChanged(EquipmentType jumpJet) {
        // not used by conventional infantry
    }

    @Override
    public void mulIdChanged(int mulId) {
        getInfantry().setMulId(mulId);
        refresh.refreshSummary();
    }

    @Override
    public void roleChanged(UnitRole role) {
        getEntity().setUnitRole(role);
        refresh.refreshSummary();
        refresh.refreshPreview();
    }
}
