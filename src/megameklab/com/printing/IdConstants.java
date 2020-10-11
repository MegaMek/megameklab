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
package megameklab.com.printing;

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

    String TYPE = "type";
    String TYPE2 = "type2";
    String FLUFF_NAME = "fluffName";
    String MP_WALK = "mpWalk";
    String MP_RUN = "mpRun";
    String MP_JUMP = "mpJump";
    String LBL_JUMP = "lblJump";
    String MP_CRUISE = "mpCruise";
    String MP_FLANK = "mpFlank";
    String MP_SAFE_THRUST = "mpSafeThrust";
    String MP_MAX_THRUST = "mpMaxThrust";
    String MP_AIRMECH_WALK = "mpAirMechWalk";
    String MP_AIRMECH_RUN = "mpAirMechRun";
    String MP_AIRMECH_CRUISE = "mpAirMechCruise";
    String MP_AIRMECH_FLANK = "mpAirMechFlank";
    String LBL_VEE_MODE = "lblVeeMode";
    String MOVEMENT_TYPE = "movementType";
    String ENGINE_TYPE = "engineType";
    String TONNAGE = "tonnage";
    String TECH_BASE = "techBase";
    String RULES_LEVEL = "rulesLevel";
    String ERA = "era";
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
    String ARMOR_TYPE_2 = "armorType2";
    String STRUCTURE_TYPE = "structureType";
    String PATCHWORK = "patchwork";
    String TEXT_ARMOR = "textArmor_";
    String TEXT_IS = "textIS_";
    String TEXT_SI = "textSI";
    String TEXT_SAIL = "textSail";
    String TEXT_KFDRIVE = "textKFDrive";
    String TEXT_DOCKING_COLLARS = "textDockingCollars";
    String ARMOR_PIPS = "armorPips";
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
    String HEAVY_DUTY_GYRO_PIP = "heavyDutyGyroPip";
    String SI_PIPS = "siPips";
    String KF_PIPS = "kfPips";
    String SAIL_PIPS = "sailPips";
    String DC_PIPS = "dcPips";

    String HEAT_SINK_PIPS = "heatSinkPips";
    String HS_TYPE = "hsType";
    String HS_COUNT = "hsCount";
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
    String CHECK_AP = "checkAp";
    String SUIT = "suit";
    String PIPS = "pips_";
    String RANGE_IN_HEXES = "rangeInHexes";
    String RANGE = "range_";

    String PROTOMECH_INDEX = "protomechIndex";
    String MAIN_GUN_ARMOR = "armor_MG";
    String MAIN_GUN_SHADOW = "shadow_MG";
    String MAIN_GUN_TEXT = "text_MG";
    String TORSO_WEAPON = "torsoWeapon_";
    String MAG_CLAMP_NOTE = "magClampNote";
    String MP_GROUND = "mpGround";
}
