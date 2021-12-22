package megameklab.com.ui.fighterAero;

import megamek.common.EquipmentType;
import megamek.common.MiscType;
import megamek.common.Mounted;
import megamek.common.weapons.bayweapons.BayWeapon;
import megameklab.com.ui.EntitySource;
import megameklab.com.ui.generalUnit.AbstractEquipmentTab;
import megameklab.com.ui.util.AbstractEquipmentDatabaseView;
import megameklab.com.util.UnitUtil;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

/*
 * The Equipment Tab for BattleArmor units showing the equipment database and the current loadout list.
 *
 * Original author - jtighe (torren@users.sourceforge.net)
 * @author arlith
 * @author neoancient
 * @author Simon (Juliez)
 */
public class ASEquipmentTab extends AbstractEquipmentTab {

    public ASEquipmentTab(EntitySource eSource) {
        super(eSource);
    }

    @Override
    protected AbstractEquipmentDatabaseView getEquipmentDatabaseView() {
        return new ASEquipmentDatabaseView(eSource);
    }

    @Override
    protected List<Mounted> getLoadout() {
        List<Mounted> result = new ArrayList<>();
        result.addAll(getEntity().getWeaponList().stream().filter(this::showWeaponInLoadout).collect(toList()));
        result.addAll(getEntity().getAmmo());
        result.addAll(getEntity().getMisc().stream().filter(this::showMiscInLoadout).collect(toList()));
        return result;
    }

    private boolean showWeaponInLoadout(Mounted mount) {
        //TODO: Needed for Fighters?
        return !(mount.getType() instanceof BayWeapon) && !mount.isWeaponGroup();
    }

    private boolean showMiscInLoadout(Mounted mount) {
        //TODO: All necessary for fighters?
        EquipmentType etype = mount.getType();
        return !(UnitUtil.isHeatSink(mount)
                || etype.hasFlag(MiscType.F_JUMP_JET)
                || etype.hasFlag(MiscType.F_JUMP_BOOSTER)
                || etype.hasFlag(MiscType.F_TSM)
                || etype.hasFlag(MiscType.F_INDUSTRIAL_TSM)
                || etype.hasFlag(MiscType.F_MASC)
                || (etype.hasFlag(MiscType.F_MASC) && !etype.hasSubType(MiscType.S_SUPERCHARGER))
                || UnitUtil.isArmorOrStructure(etype));
    }

}
