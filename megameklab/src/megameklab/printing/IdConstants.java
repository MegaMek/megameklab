/*
 * Copyright (C) 2019-2025 The MegaMek Team. All Rights Reserved.
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
package megameklab.printing;

/**
 * ID attribute values in SVG templates
 */
public interface IdConstants {
    String RS_TEMPLATE = "rs_template";

    String MML_COLOR_ELEMENTS = "mml-color-elements";
    String MML_FIELD_WIDTH = "mml-field-width";
    String MML_GAP = "mml-gap";
    String MML_MULTISECTION = "mml-multisection";

    String FOOTER = "footer";
    String COPYRIGHT = "tspanCopyright";
    String TITLE = "title";
    String UNIT_SCALE = "unitScale";
    String ROW_SHADING = "tableshading";
    String FRAME = "frame";

    String TYPE = "type";
    String TYPE2 = "type2";
    String UNIT_TYPE = "unitType";
    String FLUFF_NAME = "fluffName";
    String MP_WALK = "mpWalk";
    String MP_RUN = "mpRun";
    String MP_JUMP = "mpJump";
    String LBL_JUMP = "lblJump";
    String MP_CRUISE = "mpCruise";
    String MP_FLANK = "mpFlank";
    String MP_SAFE_THRUST = "mpSafeThrust";
    String MP_MAX_THRUST = "mpMaxThrust";
    String MP_AIR_MEK_WALK = "mpAirMekWalk";
    String MP_AIR_MEK_RUN = "mpAirMekRun";
    String MP_AIR_MEK_CRUISE = "mpAirMekCruise";
    String MP_AIR_MEK_FLANK = "mpAirMekFlank";
    String LBL_VEE_MODE = "lblVeeMode";
    String MOVEMENT_TYPE = "movementType";
    String ENGINE_TYPE = "engineType";
    String TONNAGE = "tonnage";
    String TECH_BASE = "techBase";
    String RULES_LEVEL = "rulesLevel";
    String COST = "cost";
    String BV = "bv";
    String LBL_ROLE = "labelRole";
    String ROLE = "role";
    String ERA_ICON = "eraIcon";
    String INVENTORY = "inventory";

    String CREW_NAME = "crewName";
    String BLANKS_CREW = "blanksCrew";
    String BLANK_CREW_NAME = "blankCrewName";
    String PILOT_NAME = "pilotName";
    String GUNNERY_SKILL = "gunnerySkill";
    String PILOTING_SKILL = "pilotingSkill";
    String BLANK_GUNNERY_SKILL = "blankGunnerySkill";
    String BLANK_PILOTING_SKILL = "blankPilotingSkill";
    String GUNNERY_SKILL_TEXT = "gunnerySkillText";
    String PILOTING_SKILL_TEXT = "pilotingSkillText";
    String ASF_GUNNERY_SKILL = "asfGunnerySkill";
    String ASF_PILOTING_SKILL = "asfPilotingSkill";
    String ASF_BLANK_GUNNERY_SKILL = "asfBlankGunnerySkill";
    String ASF_BLANK_PILOTING_SKILL = "asfBlankPilotingSkill";
    String SPAS = "spas";
    String WARRIOR_DATA_SINGLE = "warriorDataSingle";
    String WARRIOR_DATA_DUAL = "warriorDataDual";
    String WARRIOR_DATA_TRIPLE = "warriorDataTriple";
    String CREW_DAMAGE = "crewDamage";
    String N_CREW = "nCrew";
    String N_PASSENGERS = "nPassengers";
    String N_OTHER = "nOther";
    String N_MARINES = "nMarines";
    String N_BATTLEARMOR = "nBattleArmor";
    String LIFE_BOATS = "lifeBoatsEscapePods";
    String LBL_BATTLEARMOR = "lblBattleArmor";

    String FLUFF_IMAGE = "fluffImage";
    String FLUFF_SINGLE_PILOT = "fluffSinglePilot";
    String FLUFF_DUAL_PILOT = "fluffDualPilot";
    String FLUFF_TRIPLE_PILOT = "fluffTriplePilot";
    String NOTES = "notes";
    String DEFAULT_FLUFF_IMAGE = "defaultFluffImage";

    String ARMOR_TYPE = "armorType";
    String STRUCTURE_TYPE = "structureType";
    String PATCHWORK = "patchwork";
    String TEXT_ARMOR = "textArmor_";
    String TEXT_IS = "textIS_";
    String TEXT_SI = "textSI";
    String TEXT_SAIL = "textSail";
    String TEXT_KF_DRIVE = "textKFDrive";
    String TEXT_DOCKING_COLLARS = "textDockingCollars";
    String ARMOR_PIPS = "armorPips";
    String AMMO_PIPS = "ammoPips";
    String IS_PIPS = "isPips";
    String IS_PIPS_HD = "isPipsHD";
    String IS_PIPS_HD_SH = "isPipsHD_SH";
    String STRUCTURE_PIPS = "structurePips";
    String CANON_ARMOR_PIPS = "canonArmorPips";
    String CANON_STRUCTURE_PIPS = "canonStructurePips";
    String ARMOR_DIAGRAM = "armorDiagram";
    String SHIELD = "shield";
    String SHIELD_DC = "shieldDC";
    String SHIELD_DA = "shieldDA";
    String TRACKS = "tracks";
    String WHEELS = "wheels";

    String CRITS = "crits_";
    String HEAVY_DUTY_GYRO_PIP = "gyro_hit_3";
    String SI_PIPS = "siPips";
    String KF_PIPS = "kfPips";
    String SAIL_PIPS = "sailPips";
    String DC_PIPS = "dcPips";
    String CREW_HIT = "crew_damage_";
    String COMMANDER_HIT = "commander_hit";
    String DRIVER_HIT = "driver_hit";
    String PILOT_HIT = "pilot_hit";
    String COPILOT_HIT = "copilot_hit";
    String AVIONICS_HIT = "avionics_hit_";
    String FCS_HIT = "fcs_hit_";
    String CIC_HIT = "cic_hit_";
    String FUEL_TANK_HIT = "fuel_tank_hit_";
    String DOCKING_COLLAR_HIT = "docking_collar_hit_";
    String KF_BOOM_HIT = "kf_boom_hit_";
    String THRUSTER_LEFT_HIT = "thruster_left_hit_";
    String THRUSTER_RIGHT_HIT = "thruster_right_hit_";
    String ENGINE_HIT = "engine_hit_";
    String GYRO_HIT = "gyro_hit_";
    String SENSOR_HIT = "sensor_hit_";
    String LANDING_GEAR_HIT = "landing_gear_hit_";
    String LIFE_SUPPORT_HIT = "life_support_hit_";
    String MOTIVE_SYSTEM_HIT = "motive_system_hit_";
    String TURRET_LOCKED = "turret_locked";
    String TURRET_LOCKED_FRONT = "turret_locked_f";
    String TURRET_LOCKED_REAR = "turret_locked_r";
    String STABILIZER_HIT_FRONT = "stabilizer_hit_front";
    String STABILIZER_HIT_LEFT = "stabilizer_hit_left";
    String STABILIZER_HIT_RIGHT = "stabilizer_hit_right";
    String STABILIZER_HIT_REAR = "stabilizer_hit_rear";
    String STABILIZER_HIT_TURRET = "stabilizer_hit_turret";
    String STABILIZER_HIT_TURRET_FRONT = "stabilizer_hit_turret_f";
    String STABILIZER_HIT_TURRET_REAR = "stabilizer_hit_turret_r";
    String FLIGHT_STABILIZER_HIT = "flight_stabilizer_hit";

    String HEAT_SINK_PIPS = "heatSinkPips";
    String HS_TYPE = "hsType";
    String HS_COUNT = "hsCount";
    String MINUS_9_MP = "minus9MP";
    String MINUS_8_MP = "minus8MP";
    String MINUS_7_MP = "minus7MP";
    String MINUS_6_MP = "minus6MP";
    String MINUS_5_MP = "minus5MP";
    String MINUS_4_MP = "minus4MP";
    String MINUS_3_MP = "minus3MP";
    String MINUS_2_MP = "minus2MP";
    String MINUS_1_MP = "minus1MP";
    String PARTIAL_WING_BONUS = "partialWingBonus";
    String EXTERNAL_STORES = "external_stores";
    String BOMB_BOXES = "bomb_boxes";
    String EXTERNAL_STORES_KEY = "external_stores_key";
    String DOUBLE_HS_COUNT = "hsCountDouble";
    String NOSE_HEAT = "noseHeat";
    String FORE_SIDES_HEAT = "foreSidesHeat";
    String BROADSIDES_HEAT = "broadsidesHeat";
    String AFT_SIDES_HEAT = "aftSidesHeat";
    String AFT_HEAT = "aftHeat";

    String ARMOR_KIT = "armor_kit";
    String ARMOR_DIVISOR = "armor_divisor";
    String SOLDIER = "soldier_";
    String NO_SOLDIER = "no_soldier_";
    String DAMAGE = "damage_";
    String RANGE_MOD = "range_mod_";
    String UW_LABEL = "uw_range_modifier";
    String UW_RANGE_MOD = "uw_range_mod_";
    String FIELD_GUN_COLUMNS = "field_gun_columns";
    String FIELD_GUN_QTY = "field_gun_qty";
    String FIELD_GUN_TYPE = "field_gun_type";
    String FIELD_GUN_DMG = "field_gun_dmg";
    String FIELD_GUN_DMG_2 = "field_gun_dmg_2";
    String FIELD_GUN_MIN_RANGE = "field_gun_min_range";
    String FIELD_GUN_SHORT = "field_gun_short";
    String FIELD_GUN_MED = "field_gun_med";
    String FIELD_GUN_LONG = "field_gun_long";
    String FIELD_GUN_AMMO = "field_gun_ammo";
    String FIELD_GUN_CREW = "field_gun_crew";
    String DEST_MODS = "dest_mods";
    String SNEAK_CAMO_MODS = "sneak_camo_mods";
    String SNEAK_IR_MODS = "sneak_ir_mods";
    String TRANSPORT_WT = "transport_wt";
    String MP_1 = "mp_1";
    String MODE_1 = "movement_mode_1";
    String MP_2 = "mp_2";
    String MODE_2 = "movement_mode_2";
    String MP_2_LABEL = "mp_2_label";
    String MODE_2_LABEL = "movement_mode_2_label";

    String SQUAD = "squad";
    String CHECK_MECHANIZED = "checkMechanized";
    String CHECK_SWARM = "checkSwarm";
    String CHECK_LEG = "checkLeg";
    String CHECK_AP = "checkAP";
    String SUIT = "suit";
    String PIPS = "pips_";
    String RANGE_IN_HEXES = "rangeInHexes";
    String RANGE = "range_";

    String PROTOMEK_INDEX = "protomekIndex";
    String MAIN_GUN_ARMOR = "armor_MG";
    String MAIN_GUN_SHADOW = "shadow_MG";
    String MAIN_GUN_TEXT = "text_MG";
    String TORSO_WEAPON = "torsoWeapon_";
    String MAG_CLAMP_NOTE = "magClampNote";
    String MP_GROUND = "mpGround";
}
