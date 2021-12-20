package megameklab.com.ui.util;

import megamek.common.*;
import megamek.common.weapons.tag.TAGWeapon;
import megameklab.com.util.UnitUtil;

import java.util.function.BiFunction;
import java.util.function.Function;
import static megamek.common.WeaponType.*;
import static megamek.common.MiscType.*;

public enum EquipmentDatabaseCategory {

    ENERGY ("Energy",
            (eq, en) -> (eq instanceof WeaponType) && !((WeaponType) eq).isCapital()
            && (eq.hasFlag(F_ENERGY)
            || ((eq.hasFlag(F_PLASMA) && (((WeaponType) eq).getAmmoType() == AmmoType.T_PLASMA))))),

    BALLISTIC ("Ballistic",
            (eq, en) -> (eq instanceof WeaponType) && !((WeaponType) eq).isCapital() && eq.hasFlag(F_BALLISTIC)),

    MISSILE ("Missile",
            (eq, en) -> (eq instanceof WeaponType) && !((WeaponType) eq).isCapital() && eq.hasFlag(F_MISSILE)),

    ARTILLERY ("Artillery",
            (eq, en) -> (eq instanceof WeaponType) && eq.hasFlag(F_ARTILLERY)),

    CAPITAL ("Capital",
            (eq, en) -> (eq instanceof WeaponType) && ((WeaponType) eq).isCapital(),
            Entity::isLargeCraft),

    PHYSICAL ("Physical",
            (eq, en) -> UnitUtil.isPhysicalWeapon(eq),
            e -> e.hasETypeFlag(Entity.ETYPE_MECH) || e.hasETypeFlag(Entity.ETYPE_PROTOMECH)),

    WEAPON ("All Weapons",
            (eq, en) -> ENERGY.filter(eq, en) || BALLISTIC.filter(eq, en)
            || MISSILE.filter(eq, en) || CAPITAL.filter(eq, en) || PHYSICAL.filter(eq, en)),

    AMMO ("Ammo",
            (eq, en) -> (eq instanceof AmmoType) && !(eq instanceof BombType)
            && UnitUtil.canUseAmmo(en, (AmmoType) eq, false),
            e -> e.getWeightClass() != EntityWeightClass.WEIGHT_SMALL_SUPPORT),

    OTHER ("Other",
            (eq, en) -> ((eq instanceof MiscType)
            && !UnitUtil.isPhysicalWeapon(eq)
            && !UnitUtil.isJumpJet(eq)
            && !UnitUtil.isHeatSink(eq)
            && !eq.hasFlag(F_TSM)
            && !eq.hasFlag(F_INDUSTRIAL_TSM)
            && !(eq.hasFlag(F_MASC)
            && !eq.hasSubType(S_SUPERCHARGER)
            && !eq.hasSubType(S_JETBOOSTER))
            && !(en.hasETypeFlag(Entity.ETYPE_QUADVEE) && eq.hasFlag(F_TRACKS))
            && !UnitUtil.isArmorOrStructure(eq)
            && !eq.hasFlag(F_CHASSIS_MODIFICATION)
            && !(en.isSupportVehicle() && (eq.hasFlag(F_BASIC_FIRECONTROL) || (eq.hasFlag(F_ADVANCED_FIRECONTROL))))
            && !eq.hasFlag(F_MAGNETIC_CLAMP)
            && !(eq.hasFlag(F_PARTIAL_WING) && en.hasETypeFlag(Entity.ETYPE_PROTOMECH)))
            && !eq.hasFlag(F_SPONSON_TURRET)
            && !eq.hasFlag(F_PINTLE_TURRET)
            || (eq instanceof TAGWeapon)),

    PROTOTYPE ("Prototype",
            (eq, en) -> (eq instanceof WeaponType) && eq.hasFlag(WeaponType.F_PROTOTYPE)),

    ONE_SHOT ("One-Shot",
            (eq, en) -> (eq instanceof WeaponType) && eq.hasFlag(WeaponType.F_ONESHOT)),

    TORPEDO ("Torpedoes",
            (eq, en) -> (eq instanceof WeaponType)
                    && (((WeaponType) eq).getAmmoType() == AmmoType.T_LRM_TORPEDO
                    || ((WeaponType) eq).getAmmoType() == AmmoType.T_SRM_TORPEDO)),

    UNAVAILABLE ("Unavailable")
            // TODO: Provide MM.ITechManager.isLegal in static form
    ;

    private final String displayName;
    private final BiFunction<EquipmentType, Entity, Boolean> filter;
    private final Function<Entity, Boolean> showForEntity;

    EquipmentDatabaseCategory(String displayName) {
        this(displayName, (eq, en) -> true, e -> true);
    }

    EquipmentDatabaseCategory(String displayName, BiFunction<EquipmentType, Entity, Boolean> filter) {
        this(displayName, filter, e -> true);
    }

    EquipmentDatabaseCategory(String displayName,
                      BiFunction<EquipmentType, Entity, Boolean> filter,
                      Function<Entity, Boolean> showForEntity) {
        this.displayName = displayName;
        this.filter = filter;
        this.showForEntity = showForEntity;
    }

    public String getDisplayName() {
        return displayName;
    }

    public boolean show(Entity en) {
        return showForEntity.apply(en);
    }

    public boolean filter(EquipmentType eq, Entity en) {
        return filter.apply(eq, en);
    }
}
