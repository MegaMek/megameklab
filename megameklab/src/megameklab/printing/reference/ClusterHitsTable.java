/*
 * MegaMekLab - Copyright (C) 2020 - The MegaMek Team
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 */
package megameklab.printing.reference;

import megamek.common.*;
import megamek.common.weapons.missiles.MissileWeapon;
import megameklab.printing.PrintEntity;
import megameklab.printing.PrintRecordSheet;

import java.util.*;

/**
 * Table showing the relevant columns of the cluster hits table
 */
public class ClusterHitsTable extends ReferenceTable {
    protected final Set<Integer> clusterSizes = new TreeSet<>();
    protected boolean hasATM;
    protected boolean hasHAG;

    public ClusterHitsTable(PrintEntity sheet) {
        this(sheet, sheet.getEntity());
    }

    public ClusterHitsTable(PrintRecordSheet sheet, Entity entity) {
        super(sheet);
        calculateClusterSizes(entity);
        addTable(entity);
    }

    public ClusterHitsTable(PrintRecordSheet sheet, List<Entity> entities) {
        super(sheet);
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
            headers.add(bundle.getString("dieRoll2d6"));
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
                for (Mounted mounted : entity.getIndividualWeaponList()) {
                    if (mounted.getType() instanceof MissileWeapon) {
                        for (int troopers = 1; troopers <= size; troopers++) {
                            clusterSizes.add(Math.min(40, troopers * ((MissileWeapon) mounted.getType()).getRackSize()));
                        }
                    }
                }
            }
            return;
        }
        for (Mounted mounted : entity.getIndividualWeaponList()) {
            if (mounted.getType() instanceof WeaponType) {
                final WeaponType weapon = (WeaponType) mounted.getType();
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
     * @return Whether the unit has any weapons that use the cluster hits table
     */
    public boolean required() {
        return !clusterSizes.isEmpty();
    }

    public int columnCount() {
        return clusterSizes.size();
    }
}
