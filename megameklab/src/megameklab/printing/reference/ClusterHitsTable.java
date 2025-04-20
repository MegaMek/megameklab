/*
 * Copyright (C) 2020-2025 The MegaMek Team. All Rights Reserved.
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
package megameklab.printing.reference;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import megamek.common.AmmoType;
import megamek.common.BattleArmor;
import megamek.common.Compute;
import megamek.common.Entity;
import megamek.common.Infantry;
import megamek.common.MiscType;
import megamek.common.Mounted;
import megamek.common.WeaponType;
import megamek.common.weapons.missiles.MissileWeapon;
import megameklab.printing.PrintEntity;
import megameklab.printing.PrintRecordSheet;

/**
 * Table showing the relevant columns of the cluster hits table
 */
public class ClusterHitsTable extends ReferenceTable {
    protected final Set<Integer> clusterSizes = new TreeSet<>();
    protected boolean hasATM;
    protected boolean hasHAG;
    protected boolean condensed;

    public ClusterHitsTable(PrintEntity sheet, boolean condensed) {
        this(sheet, sheet.getEntity(), condensed);
    }

    public ClusterHitsTable(PrintRecordSheet sheet, Entity entity, boolean condensed) {
        super(sheet);
        this.condensed = condensed;
        calculateClusterSizes(entity);
        addTable(entity);
    }

    public ClusterHitsTable(PrintRecordSheet sheet, List<Entity> entities, boolean condensed) {
        super(sheet);
        this.condensed = condensed;
        for (Entity en : entities) {
            calculateClusterSizes(en);
        }
        addTable(entities.get(0));
    }

    public Set<Integer> getSizes() {
        return clusterSizes;
    }

    protected void addTable(Entity entity) {
        if (!clusterSizes.isEmpty()) {
            List<Double> offsets = new ArrayList<>();
            double spacing = 0.9 / (clusterSizes.size() + 1);
            for (double o = 0.05 + spacing / 2.0; o <= 0.95; o += spacing) {
                offsets.add(o);
            }
            setColOffsets(offsets);
            List<String> headers = new ArrayList<>();
            if (!condensed) {
                headers.add(bundle.getString("dieRoll2d6"));
            } else {
                headers.add(bundle.getString("2d6"));
            }
            for (int size : clusterSizes) {
                headers.add(String.valueOf(size));
            }
            setHeaders(headers);
            addRows();
            addNotes(entity);
        }
    }

    private void calculateClusterSizes(Entity entity) {
        if (entity instanceof Infantry) {
            int size = ((Infantry) entity).getShootingStrength();
            for (int i = 2; i <= size; i++) {
                clusterSizes.add(i);
            }
            if (entity instanceof BattleArmor) {
                for (Mounted<?> mounted : entity.getIndividualWeaponList()) {
                    if (mounted.getType() instanceof MissileWeapon) {
                        for (int troopers = 1; troopers <= size; troopers++) {
                            clusterSizes.add(Math.min(40,
                                  troopers * ((MissileWeapon) mounted.getType()).getRackSize()));
                        }
                    }
                }
            }
            return;
        }
        for (Mounted<?> mounted : entity.getIndividualWeaponList()) {
            if (mounted.getType() instanceof WeaponType weapon) {
                switch (weapon.getAmmoType()) {
                    case AmmoType.T_AC_LBX:
                    case AmmoType.T_EXLRM:
                    case AmmoType.T_IATM:
                    case AmmoType.T_LRM:
                    case AmmoType.T_LRM_IMP:
                    case AmmoType.T_LRM_PRIMITIVE:
                    case AmmoType.T_LRM_TORPEDO:
                    case AmmoType.T_LRM_STREAK:
                    case AmmoType.T_MML:
                    case AmmoType.T_MRM:
                    case AmmoType.T_NLRM:
                    case AmmoType.T_ROCKET_LAUNCHER:
                    case AmmoType.T_SBGAUSS:
                    case AmmoType.T_SRM:
                    case AmmoType.T_SRM_ADVANCED:
                    case AmmoType.T_SRM_IMP:
                    case AmmoType.T_SRM_PRIMITIVE:
                    case AmmoType.T_SRM_TORPEDO:
                    case AmmoType.T_SRM_STREAK:
                        clusterSizes.add(weapon.getRackSize());
                        break;
                    case AmmoType.T_ATM:
                        hasATM = true;
                        clusterSizes.add(weapon.getRackSize());
                        break;
                    case AmmoType.T_HAG:
                        hasHAG = true;
                        clusterSizes.add(weapon.getRackSize());
                        break;
                    case AmmoType.T_AC_ROTARY:
                        for (int i = 2; i <= 6; i++) {
                            clusterSizes.add(i);
                        }
                        break;
                    case AmmoType.T_AC_ULTRA:
                    case AmmoType.T_AC_ULTRA_THB:
                        clusterSizes.add(2);
                        break;
                    case AmmoType.T_MG:
                    case AmmoType.T_MG_HEAVY:
                    case AmmoType.T_MG_LIGHT:
                        if (weapon.hasFlag(WeaponType.F_MGA)) {
                            for (int i = 2; i <= 4; i++) {
                                clusterSizes.add(i);
                            }
                        }
                        break;
                }
            }
        }
    }

    protected void addRows() {
        for (int roll = 2; roll <= 12; roll++) {
            List<String> row = new ArrayList<>();
            row.add(String.valueOf(roll));
            for (int size : clusterSizes) {
                row.add(String.valueOf(Compute.calculateClusterHitTableAmount(roll, size)));
            }
            addRow(row);
        }
    }

    protected void addNotes(Entity entity) {
        if (hasATM || entity.hasWorkingMisc(MiscType.F_ARTEMIS)) {
            addNote(bundle.getString("artemisIV.note"));
        } else if (entity.hasWorkingMisc(MiscType.F_ARTEMIS_V)) {
            addNote(bundle.getString("artemisV.note"));
        } else if (entity.hasWorkingMisc(MiscType.F_ARTEMIS_PROTO)) {
            addNote(bundle.getString("artemisProto.note"));
        }
        if (entity.hasWorkingMisc(MiscType.F_APOLLO)) {
            addNote(bundle.getString("apollo.note"));
        }
        if (hasHAG) {
            addNote(bundle.getString("hag.note"));
        }
    }

    /**
     * @return Whether the unit has any weapons that use the cluster hits the table
     */
    public boolean required() {
        return !clusterSizes.isEmpty();
    }

    public int columnCount() {
        return clusterSizes.size();
    }
}
