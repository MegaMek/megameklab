-- =================================================================
-- Utility Functions / Extensions
-- =================================================================
-- Enable UUID generation if not already enabled (optional, for future use if SERIAL is not preferred)
-- CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- Trigger function to automatically update updated_at timestamp
CREATE OR REPLACE FUNCTION trigger_set_timestamp()
RETURNS TRIGGER AS $$
BEGIN
  NEW.updated_at = NOW();
  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- =================================================================
-- units Table
-- Stores individual combat units (BattleMechs, Vehicles, Fighters, etc.)
-- =================================================================
CREATE TABLE IF NOT EXISTS units (
    id SERIAL PRIMARY KEY,
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
    data JSONB NOT NULL,

    created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP
);

-- Indexes for units table
CREATE INDEX IF NOT EXISTS idx_units_chassis ON units(chassis text_pattern_ops);
CREATE INDEX IF NOT EXISTS idx_units_model ON units(model text_pattern_ops);
CREATE INDEX IF NOT EXISTS idx_units_unit_type ON units(unit_type);
CREATE INDEX IF NOT EXISTS idx_units_tech_base ON units(tech_base);
CREATE INDEX IF NOT EXISTS idx_units_era ON units(era);
CREATE INDEX IF NOT EXISTS idx_units_mass_tons ON units(mass_tons);
CREATE INDEX IF NOT EXISTS idx_units_role ON units(role);
CREATE INDEX IF NOT EXISTS idx_units_data_gin ON units USING GIN (data jsonb_path_ops);

-- Drop trigger if it exists before creating, to ensure idempotency if function changes
DROP TRIGGER IF EXISTS set_timestamp_units ON units;
CREATE TRIGGER set_timestamp_units
BEFORE UPDATE ON units
FOR EACH ROW
EXECUTE FUNCTION trigger_set_timestamp();

-- =================================================================
-- equipment Table
-- Master list of unique equipment items (Weapons, Ammo, Gear)
-- =================================================================
CREATE TABLE IF NOT EXISTS equipment (
    id SERIAL PRIMARY KEY,
    internal_id TEXT UNIQUE NOT NULL, -- Derived, e.g., "ISERLargeLaser", "AmmoLRM15"
    name TEXT NOT NULL, -- Common name, e.g., "Large Laser", "LRM 15 Ammo (24)"
    type TEXT, -- e.g., 'Weapon', 'Ammo', 'Equipment', 'HeatSink' (from equipmentSchema.json)
    category TEXT, -- e.g., 'EnergyWeapon', 'BallisticWeapon', 'Ammunition' (from equipmentSchema.json)
    tech_base TEXT, -- e.g., 'Inner Sphere', 'Clan', 'Mixed'

    -- Full equipment data conforming to equipmentSchema.json
    data JSONB NOT NULL,

    created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP
);

-- Indexes for equipment table
CREATE INDEX IF NOT EXISTS idx_equipment_internal_id ON equipment(internal_id);
CREATE INDEX IF NOT EXISTS idx_equipment_name ON equipment(name text_pattern_ops);
CREATE INDEX IF NOT EXISTS idx_equipment_type ON equipment(type);
CREATE INDEX IF NOT EXISTS idx_equipment_category ON equipment(category);
CREATE INDEX IF NOT EXISTS idx_equipment_tech_base ON equipment(tech_base);
CREATE INDEX IF NOT EXISTS idx_equipment_data_gin ON equipment USING GIN (data jsonb_path_ops);

DROP TRIGGER IF EXISTS set_timestamp_equipment ON equipment;
CREATE TRIGGER set_timestamp_equipment
BEFORE UPDATE ON equipment
FOR EACH ROW
EXECUTE FUNCTION trigger_set_timestamp();

-- =================================================================
-- unit_validation_options Table
-- Stores the (typically single) set of unit verification options
-- from UnitVerifierOptions.xml
-- =================================================================
CREATE TABLE IF NOT EXISTS unit_validation_options (
    id SERIAL PRIMARY KEY, -- Only one row expected, effectively a singleton table
    name TEXT UNIQUE NOT NULL DEFAULT 'DefaultSettings', -- Changed default name slightly for clarity

    -- Full JSON data from UnitVerifierOptions.json
    data JSONB NOT NULL,

    created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP
);

DROP TRIGGER IF EXISTS set_timestamp_unit_validation_options ON unit_validation_options;
CREATE TRIGGER set_timestamp_unit_validation_options
BEFORE UPDATE ON unit_validation_options
FOR EACH ROW
EXECUTE FUNCTION trigger_set_timestamp();

-- End of schema.sql
DO $$
BEGIN
  RAISE NOTICE 'schema.sql executed successfully.';
END
$$;
