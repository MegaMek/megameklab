package megameklab.com.ui.battleArmor;

import megamek.common.*;
import megameklab.com.ui.EntitySource;
import megameklab.com.ui.util.AbstractEquipmentDatabaseView;
import megameklab.com.util.UnitUtil;

import java.util.List;

import static megameklab.com.ui.util.EquipmentTableModel.*;

public class BAEquipmentDatabaseView extends AbstractEquipmentDatabaseView {

    private final List<Integer> fluffColumns = List.of(COL_NAME, COL_TECH, COL_TLEVEL, COL_TRATING, COL_DPROTOTYPE,
            COL_DPRODUCTION, COL_DCOMMON, COL_DEXTINCT, COL_DREINTRO, COL_COST, COL_REF);

    private final List<Integer> statsColumns = List.of(COL_NAME, COL_DAMAGE, COL_MRANGE, COL_RANGE,
            COL_SHOTS, COL_TECH, COL_BV, COL_TON, COL_CRIT, COL_REF);

    public BAEquipmentDatabaseView(EntitySource eSource) {
        super(eSource);
    }

    @Override
    protected void addEquipment(EquipmentType equip, int count) {
        if ((equip instanceof MiscType) && equip.hasFlag(MiscType.F_TARGCOMP)) {
            if (!UnitUtil.hasTargComp(getBattleArmor())) {
                UnitUtil.updateTC(getBattleArmor(), equip);
            }
        } else {
            try {
                if (UnitUtil.isBAMultiMount(equip)) {
                    for (int t = 1; t <= getBattleArmor().getTroopers(); t++) {
                        Mounted mount = new Mounted(getBattleArmor(), equip);
                        mount.setBaMountLoc(BattleArmor.MOUNT_LOC_NONE);
                        getBattleArmor().addEquipment(mount, t, false);
                    }
                } else {
                    Mounted mount = new Mounted(getBattleArmor(), equip);
                    mount.setBaMountLoc(BattleArmor.MOUNT_LOC_NONE);
                    getBattleArmor().addEquipment(mount, BattleArmor.LOC_SQUAD,false);
                }
            } catch (LocationFullException lfe) {
                // this can't happen, we add to Entity.LOC_NONE
            }
        }
    }

    @Override
    protected boolean shouldShow(EquipmentType equipment) {
        return super.shouldShow(equipment);
//        if ((etype instanceof MiscType)
//                        && (etype.hasFlag(MiscType.F_TSM)
//                                || etype.hasFlag(MiscType.F_INDUSTRIAL_TSM)
//                                || (etype.hasFlag(MiscType.F_MASC)
//                                        && !etype.hasSubType(
//                                                MiscType.S_SUPERCHARGER)))) {
//                    return false;
//                }
//
//                // Don't show equipment that is added via the StructureTab
//                if ((etype instanceof MiscType )
//                        && (etype.hasFlag(MiscType.F_BA_MANIPULATOR)
//                            || etype.hasFlag(MiscType.F_PARTIAL_WING)
//                            || etype.hasFlag(MiscType.F_JUMP_BOOSTER)
//                            || etype.hasFlag(MiscType.F_MECHANICAL_JUMP_BOOSTER)
//                            || etype.hasFlag(MiscType.F_MASC)
//                            || UnitUtil.isArmorOrStructure(etype)
//                            || UnitUtil.isJumpJet(etype))) {
//                    return false;
//                }
//
//                if (etype.hasFlag(MiscType.F_DETACHABLE_WEAPON_PACK)
//                        && !getBattleArmor().canMountDWP()){
//                    return false;
//                }
    }

    @Override
    protected void updateVisibleColumns() {
        // TODO: just make this a single call with the desired columns
        setColumnsVisible(allColumns, false);
        setColumnsVisible(tableMode ? statsColumns : fluffColumns, true);
    }



}
