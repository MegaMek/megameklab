/*
 * MegaMekLab
 * Copyright (c) 2021 - The MegaMek Team. All Rights Reserved.
 *
 * This program is  free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation; either version 2 of the License, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details.
 */
package megameklab.ui.util;

import megamek.common.*;
import megamek.common.weapons.tag.TAGWeapon;
import megameklab.util.BattleArmorUtil;
import megameklab.util.UnitUtil;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;

import static megamek.common.EquipmentTypeLookup.*;
import static megamek.common.WeaponType.*;
import static megamek.common.MiscType.*;

/**
 * Equipment categories used for filtering the equipment database and deciding which filters to show.
 *
 * @author Neoancient
 * @author Simon (Juliez) (Additions)
 */
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
            (eq, en) -> (eq instanceof WeaponType) && eq.hasFlag(F_ARTILLERY),
            e -> !(e instanceof ProtoMek)
                    && (!(e instanceof Infantry) || (e instanceof BattleArmor))),

    CAPITAL ("Capital",
            (eq, en) -> (eq instanceof WeaponType) && ((WeaponType) eq).isCapital(),
            Entity::isLargeCraft),

    PHYSICAL ("Physical",
            (eq, en) -> UnitUtil.isPhysicalWeapon(eq) || isIndustrialEquipment(eq),
            e -> e.hasETypeFlag(Entity.ETYPE_MEK) || e.hasETypeFlag(Entity.ETYPE_HANDHELD_WEAPON)),

    INDUSTRIAL ("Industrial",
            (eq, en) -> isIndustrialEquipment(eq),
            e -> (e instanceof Tank) || e.isSupportVehicle()),

    AMMO ("Ammo",
            (eq, en) -> (eq instanceof AmmoType) && !(eq instanceof BombType) && !eq.is(COOLANT_POD),
            e -> e.getWeightClass() != EntityWeightClass.WEIGHT_SMALL_SUPPORT),

    OTHER ("Other",
            (eq, en) -> ((eq instanceof MiscType)
                    && !UnitUtil.isPhysicalWeapon(eq)
                    && !UnitUtil.isJumpJet(eq)
                    && !UnitUtil.isHeatSink(eq)
                    && !(isIndustrialEquipment(eq) && ((en instanceof Tank) || en.isSupportVehicle() || en instanceof Mek))
                    && !eq.isAnyOf(LAM_FUEL_TANK)
                    && !eq.hasFlag(F_TSM)
                    && !eq.hasFlag(F_INDUSTRIAL_TSM)
                    && !(eq.hasFlag(F_MASC)
                    && !eq.hasSubType(S_SUPERCHARGER)
                    && !eq.hasSubType(S_JETBOOSTER))
                    && !(en.hasETypeFlag(Entity.ETYPE_QUADVEE) && eq.hasFlag(F_TRACKS))
                    && !UnitUtil.isArmorOrStructure(eq)
                    && !(eq.hasFlag(F_CHASSIS_MODIFICATION) && en.isSupportVehicle())
                    && !(en.isSupportVehicle() && (eq.hasFlag(F_BASIC_FIRECONTROL) || (eq.hasFlag(F_ADVANCED_FIRECONTROL))))
                    && !(eq.hasFlag(F_MAGNETIC_CLAMP) && en.hasETypeFlag(Entity.ETYPE_PROTOMEK))
                    && !(eq.hasFlag(F_PARTIAL_WING) && en.hasETypeFlag(Entity.ETYPE_PROTOMEK))
                    && !(eq.hasFlag(F_SPONSON_TURRET) && en.isSupportVehicle())
                    && !eq.hasFlag(F_PINTLE_TURRET))
                    || eq.is(COOLANT_POD)
                    || eq.is(BattleArmor.MINE_LAUNCHER)
                    || (eq instanceof TAGWeapon)
                    || eq.hasFlag(F_AMS)),

    AP ("Anti-Personnel",
            (eq, en) -> BattleArmorUtil.isBattleArmorAPWeapon(eq),
            e -> e instanceof BattleArmor),

    PROTOTYPE ("Prototype",
            (eq, en) -> ((eq instanceof WeaponType) && eq.hasFlag(WeaponType.F_PROTOTYPE))
                    || ((eq instanceof MiscType) && eq.hasFlag(MiscType.F_PROTOTYPE)),
            e -> !(e instanceof BattleArmor)),

    ONE_SHOT ("One-Shot",
            (eq, en) -> (eq instanceof WeaponType) && eq.hasFlag(WeaponType.F_ONESHOT)),

    TORPEDO ("Torpedoes",
            (eq, en) -> (eq instanceof WeaponType)
                    && (((WeaponType) eq).getAmmoType() == AmmoType.T_LRM_TORPEDO
                    || ((WeaponType) eq).getAmmoType() == AmmoType.T_SRM_TORPEDO),
            e -> !(e instanceof BattleArmor) && !(e instanceof Aero)),

    UNAVAILABLE ("Unavailable"),
    // TODO: Provide MM.ITechManager.isLegal in static form

    UNUSABLE_AMMO("Ammo w/o Weapon",
            (eq, en) -> (eq instanceof AmmoType) && !(eq instanceof BombType) && !eq.is(COOLANT_POD)
                    && !UnitUtil.canUseAmmo(en, (AmmoType) eq, false))
    ;

    private final static Set<EquipmentDatabaseCategory> showFilters = EnumSet.of(ENERGY, BALLISTIC, MISSILE,
            ARTILLERY, CAPITAL, PHYSICAL, AMMO, OTHER);

    private final static Set<EquipmentDatabaseCategory> hideFilters = EnumSet.of(PROTOTYPE, AP,
            ONE_SHOT, UNAVAILABLE);

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

    public boolean showFilterFor(Entity en) {
        return showForEntity.apply(en);
    }

    public boolean passesFilter(EquipmentType eq, Entity en) {
        return filter.apply(eq, en);
    }

    /**
     * @return a Set of the filters that should act as "Show..." filters.
     */
    public static Set<EquipmentDatabaseCategory> getShowFilters() {
        return Collections.unmodifiableSet(showFilters);
    }

    /**
     * @return a Set of the filters that should act as "Hide..." filters.
     */
    public static Set<EquipmentDatabaseCategory> getHideFilters() {
        return Collections.unmodifiableSet(hideFilters);
    }

    /**
     * Returns true if the given equipment is an Industrial Equipment such as a Backhoe (TM, pp. 241-249).
     * Note: This check has nothing to do with Industrial Meks.
     *
     * @param equipment The equipment to check
     * @return true if the equipment is "Industrial" equipment
     */
    public static boolean isIndustrialEquipment(EquipmentType equipment) {
        return equipment.isAnyOf(BACKHOE, LIGHT_BRIDGE_LAYER, MEDIUM_BRIDGE_LAYER, HEAVY_BRIDGE_LAYER,
                BULLDOZER, CHAINSAW, COMBINE, DUAL_SAW, DUMPER_FRONT, DUMPER_REAR, DUMPER_RIGHT, DUMPER_LEFT,
                EXTENDED_FUEL_TANK, PILE_DRIVER, LADDER, LIFT_HOIST, MANIPULATOR_INDUSTRIAL, MINING_DRILL,
                NAIL_RIVET_GUN, REFUELING_DROGUE, FLUID_SUCTION_LIGHT_MEK, FLUID_SUCTION_LIGHT_VEE,
                FLUID_SUCTION, ROCK_CUTTER, SALVAGE_ARM, SPOT_WELDER, SPRAYER_MEK, SPRAYER_VEE, WRECKING_BALL);
    }

}
