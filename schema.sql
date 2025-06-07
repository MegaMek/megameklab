-- =================================================================
-- Utility Functions / Extensions
-- =================================================================
-- Enable UUID generation if not already enabled (optional, for future use if INTEGER PRIMARY KEY AUTOINCREMENT is not preferred)
-- CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- Trigger function to automatically update updated_at timestamp
-- SQLite uses a different mechanism for this, see triggers below.

-- =================================================================
-- units Table
-- Stores individual combat units (BattleMechs, Vehicles, Fighters, etc.)
-- =================================================================
CREATE TABLE IF NOT EXISTS units (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    original_file_path TEXT UNIQUE NOT NULL, -- Path to the source .mtf/.blk file
    unit_type TEXT NOT NULL, -- e.g., 'BattleMech', 'Vehicle', 'Fighter', 'BattleArmor', 'ProtoMek', 'Infantry', 'ConventionalFighter', 'DropShip', 'SmallCraft', 'JumpShip', 'WarShip', 'SpaceStation', 'GunEmplacement'

    -- Extracted common fields for searching/indexing and quick overview
    chassis TEXT,
    model TEXT,
    mul_id TEXT, -- Master Unit List ID, can be string or number
    tech_base TEXT, -- e.g., 'Inner Sphere', 'Clan', 'Mixed'
    era TEXT, -- e.g., 'Succession Wars', 'Clan Invasion', 'Jihad'
    mass_tons INTEGER, -- Stored as integer for direct comparison, actual value might have decimals in source
    role TEXT, -- e.g., 'Striker', 'Missile Boat', 'Scout'
    source_book TEXT, -- Primary sourcebook for the unit variant

    -- Full unit data conforming to its specific JSON schema
    data TEXT NOT NULL,

    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- Indexes for units table
CREATE INDEX IF NOT EXISTS idx_units_chassis ON units(chassis);
CREATE INDEX IF NOT EXISTS idx_units_model ON units(model);
CREATE INDEX IF NOT EXISTS idx_units_unit_type ON units(unit_type);
CREATE INDEX IF NOT EXISTS idx_units_tech_base ON units(tech_base);
CREATE INDEX IF NOT EXISTS idx_units_era ON units(era);
CREATE INDEX IF NOT EXISTS idx_units_mass_tons ON units(mass_tons);
CREATE INDEX IF NOT EXISTS idx_units_role ON units(role);
CREATE INDEX IF NOT EXISTS idx_units_data ON units(data);

-- Drop trigger if it exists before creating, to ensure idempotency if function changes
DROP TRIGGER IF EXISTS set_timestamp_units ON units;
CREATE TRIGGER set_timestamp_units
AFTER UPDATE ON units
FOR EACH ROW
BEGIN
    UPDATE units SET updated_at = CURRENT_TIMESTAMP WHERE id = OLD.id;
END;

-- =================================================================
-- equipment Table
-- Master list of unique equipment items (Weapons, Ammo, Gear)
-- =================================================================
CREATE TABLE IF NOT EXISTS equipment (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    internal_id TEXT UNIQUE NOT NULL, -- Derived, e.g., "ISERLargeLaser", "AmmoLRM15"
    name TEXT NOT NULL, -- Common name, e.g., "Large Laser", "LRM 15 Ammo (24)"
    type TEXT, -- e.g., 'Weapon', 'Ammo', 'Equipment', 'HeatSink' (from equipmentSchema.json)
    category TEXT, -- e.g., 'EnergyWeapon', 'BallisticWeapon', 'Ammunition' (from equipmentSchema.json)
    tech_base TEXT, -- e.g., 'Inner Sphere', 'Clan', 'Mixed'

    -- Full equipment data conforming to equipmentSchema.json
    data TEXT NOT NULL,

    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- Indexes for equipment table
CREATE INDEX IF NOT EXISTS idx_equipment_internal_id ON equipment(internal_id);
CREATE INDEX IF NOT EXISTS idx_equipment_name ON equipment(name);
CREATE INDEX IF NOT EXISTS idx_equipment_type ON equipment(type);
CREATE INDEX IF NOT EXISTS idx_equipment_category ON equipment(category);
CREATE INDEX IF NOT EXISTS idx_equipment_tech_base ON equipment(tech_base);
CREATE INDEX IF NOT EXISTS idx_equipment_data ON equipment(data);

DROP TRIGGER IF EXISTS set_timestamp_equipment ON equipment;
CREATE TRIGGER set_timestamp_equipment
AFTER UPDATE ON equipment
FOR EACH ROW
BEGIN
    UPDATE equipment SET updated_at = CURRENT_TIMESTAMP WHERE id = OLD.id;
END;

-- =================================================================
-- unit_validation_options Table
-- Stores the (typically single) set of unit verification options
-- from UnitVerifierOptions.xml
-- =================================================================
CREATE TABLE IF NOT EXISTS unit_validation_options (
    id INTEGER PRIMARY KEY AUTOINCREMENT, -- Only one row expected, effectively a singleton table
    name TEXT UNIQUE NOT NULL DEFAULT 'DefaultSettings', -- Changed default name slightly for clarity

    -- Full JSON data from UnitVerifierOptions.json
    data TEXT NOT NULL,

    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

DROP TRIGGER IF EXISTS set_timestamp_unit_validation_options ON unit_validation_options;
CREATE TRIGGER set_timestamp_unit_validation_options
AFTER UPDATE ON unit_validation_options
FOR EACH ROW
BEGIN
    UPDATE unit_validation_options SET updated_at = CURRENT_TIMESTAMP WHERE id = OLD.id;
END;

-- End of schema.sql
