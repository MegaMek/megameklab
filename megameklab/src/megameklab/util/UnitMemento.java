package megameklab.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import megamek.common.Entity;
import megamek.common.EquipmentType;
import megamek.common.Mounted;
import megamek.common.loaders.MtfFile;
import megameklab.ui.MegaMekLabMainUI;

public class UnitMemento {
    private final String entityState;
    private final String unallocatedEquipment;
    private final double armorTonnage;

    public UnitMemento(Entity entity, MegaMekLabMainUI mainUI) {
        this.entityState = UnitUtil.saveUnitToString(entity, false);
        double armorTonnage = -1;
        String unallocatedEquipment = null;
        if (entity != null) {
            armorTonnage = entity.getLabArmorTonnage();
        }
        if (mainUI != null) {
            List<Mounted<?>> unallocatedMounted = mainUI.getUnallocatedMounted();
            if (unallocatedMounted != null && !unallocatedMounted.isEmpty()) {
                StringWriter stringWriter = new StringWriter();
                try (PrintWriter pw = new PrintWriter(stringWriter)) {
                    pw.println(unallocatedMounted.size());
                    for (Mounted<?> mounted : unallocatedMounted) {
                        EquipmentType type = mounted.getType();
                        if (type.isVariableSize()) {
                            pw.printf("%s%s%f\n",
                                    type.getInternalName(),
                                    MtfFile.SIZE,
                                    mounted.getSize());
                        } else {
                            pw.println(type.getInternalName());
                        }
                    }
                    unallocatedEquipment = stringWriter.toString();
                }
            }
        }
        this.armorTonnage = armorTonnage;
        this.unallocatedEquipment = unallocatedEquipment;
    }

    public String getEntityState() {
        return entityState;
    }

    public String getUnallocatedEquipment() {
        return unallocatedEquipment;
    }

    public double getArmorTonnage() {
        return armorTonnage;
    }

    public boolean isEmpty() {
        return entityState == null || entityState.isEmpty();
    }

    public boolean equals(UnitMemento other) {
        if (other == null) {
            return false;
        }
        if (this == other) {
            return true;
        }
        // Compare entityState
        if (entityState == null) {
            if (other.entityState != null) {
                return false;
            }
        } else if (!entityState.equals(other.entityState)) {
            return false;
        }
        // Compare armorTonnage
        if (armorTonnage != other.armorTonnage) {
            return false;
        }
        // Compare unallocatedEquipment
        if (unallocatedEquipment == null) {
            if (other.unallocatedEquipment != null) {
                return false;
            }
        } else if (!unallocatedEquipment.equals(other.unallocatedEquipment)) {
            return false;
        }
        // If we get here, both fields are equal
        return true;
    }
}
