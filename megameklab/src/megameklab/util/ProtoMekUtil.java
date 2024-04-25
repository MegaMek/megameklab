/*
 * Copyright (c) 2024 - The MegaMek Team. All Rights Reserved.
 *
 * This file is part of MegaMek.
 *
 * MegaMek is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MegaMek is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MegaMek. If not, see <http://www.gnu.org/licenses/>.
 */
package megameklab.util;

import megamek.common.*;
import megamek.common.verifier.TestProtomech;

public final class ProtoMekUtil {

    public static boolean isProtomechEquipment(EquipmentType eq, Protomech proto) {
        return isProtomechEquipment(eq, proto, false);
    }

    public static boolean isProtomechEquipment(EquipmentType eq, Protomech proto, boolean checkConfiguration) {
        if (checkConfiguration && (eq instanceof MiscType)) {
            if (eq.hasFlag(MiscType.F_MAGNETIC_CLAMP) && (proto.isQuad() || proto.isGlider())) {
                return false;
            }
            if (eq.hasFlag(MiscType.F_CLUB) && eq.hasSubType(MiscType.S_PROTOMECH_WEAPON) && proto.isQuad()) {
                return false;
            }
            if (eq.hasFlag(MiscType.F_CLUB) && eq.hasSubType(MiscType.S_PROTO_QMS) && !proto.isQuad()) {
                return false;
            }
        }
        if (eq instanceof MiscType) {
            return eq.hasFlag(MiscType.F_PROTOMECH_EQUIPMENT);
        } else if (eq instanceof WeaponType) {
            return eq.hasFlag(WeaponType.F_PROTO_WEAPON);
        }
        return true;
    }

    /** Adds the given number of shots to the already present given ammo on the given ProtoMek. */
    public static void addProtoMechAmmo(Protomech entity, EquipmentType ammo, int shots) throws LocationFullException {
        Mounted<?> aMount = entity.getAmmo().stream()
                .filter(m -> ammo.equals(m.getType())).findFirst().orElse(null);
        if (null != aMount) {
            aMount.setShotsLeft(aMount.getUsableShotsLeft() + shots);
        } else {
            Mounted<?> mount = Mounted.createMounted(entity, ammo);
            entity.addEquipment(mount, Protomech.LOC_BODY, false);
            mount.setShotsLeft(shots);
        }
    }

    /**
     * Subtracts the given number of shots from the given ammo on the given ProtoMek.
     * May remove the entire Mounted from the ProtoMek.
     */
    public static void reduceProtoMechAmmo(Protomech entity, EquipmentType ammo, int shots) {
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
     * @return Whether the equipment can be added without exceeding the limits.
     */
    public static boolean protomechHasRoom(Protomech proto, int location, Mounted<?> mount) {
        if (!TestProtomech.requiresSlot(mount.getType())) {
            return true;
        }
        int slots = TestProtomech.maxSlotsByLocation(location, proto) - 1;
        double weight = TestProtomech.maxWeightByLocation(location, proto)
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

    private ProtoMekUtil() { }
}
