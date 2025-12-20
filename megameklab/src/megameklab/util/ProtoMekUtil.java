/*
 * Copyright (C) 2024-2025 The MegaMek Team. All Rights Reserved.
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
package megameklab.util;

import megamek.common.equipment.EquipmentType;
import megamek.common.equipment.enums.MiscTypeFlag;
import megamek.common.exceptions.LocationFullException;
import megamek.common.equipment.MiscType;
import megamek.common.equipment.Mounted;
import megamek.common.units.ProtoMek;
import megamek.common.equipment.WeaponType;
import megamek.common.verifier.TestProtoMek;

public final class ProtoMekUtil {

    public static boolean isProtoMekEquipment(EquipmentType eq, ProtoMek proto) {
        return isProtoMekEquipment(eq, proto, false);
    }

    public static boolean isProtoMekEquipment(EquipmentType eq, ProtoMek proto, boolean checkConfiguration) {
        if (checkConfiguration && (eq instanceof MiscType)) {
            if (eq.hasFlag(MiscType.F_MAGNETIC_CLAMP) && (proto.isQuad() || proto.isGlider())) {
                return false;
            }
            if (eq.hasFlag(MiscType.F_CLUB) && eq.hasFlag(MiscTypeFlag.S_PROTOMEK_WEAPON) && proto.isQuad()) {
                return false;
            }
            if (eq.hasFlag(MiscType.F_CLUB) && eq.hasFlag(MiscTypeFlag.S_PROTO_QMS) && !proto.isQuad()) {
                return false;
            }
        }
        if (eq instanceof MiscType) {
            return eq.hasFlag(MiscType.F_PROTOMEK_EQUIPMENT);
        } else if (eq instanceof WeaponType) {
            return eq.hasFlag(WeaponType.F_PROTO_WEAPON);
        }
        return true;
    }

    /**
     * Adds the given number of shots to the already present given ammo on the given ProtoMek.
     */
    public static void addProtoMekAmmo(ProtoMek entity, EquipmentType ammo, int shots) throws LocationFullException {
        Mounted<?> aMount = entity.getAmmo().stream()
              .filter(m -> ammo.equals(m.getType())).findFirst().orElse(null);
        if (null != aMount) {
            aMount.setShotsLeft(aMount.getUsableShotsLeft() + shots);
        } else {
            Mounted<?> mount = Mounted.createMounted(entity, ammo);
            entity.addEquipment(mount, ProtoMek.LOC_BODY, false);
            mount.setShotsLeft(shots);
        }
    }

    /**
     * Subtracts the given number of shots from the given ammo on the given ProtoMek. May remove the entire Mounted from
     * the ProtoMek.
     */
    public static void reduceProtoMekAmmo(ProtoMek entity, EquipmentType ammo, int shots) {
        Mounted<?> aMount = entity.getAmmo().stream()
              .filter(m -> ammo.equals(m.getType())).findFirst().orElse(null);
        if (aMount != null) {
            if (aMount.getUsableShotsLeft() <= shots) {
                UnitUtil.removeMounted(entity, aMount);
            } else {
                aMount.setShotsLeft(aMount.getUsableShotsLeft() - shots);
            }
        }
    }

    /**
     * Checks whether the space has room for the equipment within the slot and weight limits.
     *
     * @param location A ProtoMek location
     * @param mount    The equipment to be added to the location
     *
     * @return Whether the equipment can be added without exceeding the limits.
     */
    public static boolean protoMekHasRoom(ProtoMek proto, int location, Mounted<?> mount) {
        if (!TestProtoMek.requiresSlot(mount.getType())) {
            return true;
        }
        int slots = TestProtoMek.maxSlotsByLocation(location, proto) - 1;
        double weight = TestProtoMek.maxWeightByLocation(location, proto)
              - mount.getTonnage();
        if ((slots < 0) || (weight < 0)) {
            return false;
        }
        for (Mounted<?> m : proto.getEquipment()) {
            if (m.getLocation() == location) {
                slots--;
                weight -= m.getTonnage();
                if ((slots < 0) || (weight < 0)) {
                    return false;
                }
            }
        }
        return true;
    }

    private ProtoMekUtil() {
    }
}
