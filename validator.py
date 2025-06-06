import os
import json
import psycopg2
import psycopg2.extras
import re
from jsonschema import validate, exceptions as jsonschema_exceptions, RefResolver
from pathlib import Path

# --- Database Connection Parameters ---
DB_HOST = os.environ.get("DB_HOST", "localhost")
DB_NAME = os.environ.get("DB_NAME", "battletech_editor")
DB_USER = os.environ.get("DB_USER", "battletech_user")
DB_PASSWORD = os.environ.get("DB_PASSWORD", "password")

# --- Helper Functions ---
def get_db_connection():
    """Establishes and returns a database connection."""
    conn = None
    try:
        conn = psycopg2.connect(
            host=DB_HOST,
            dbname=DB_NAME,
            user=DB_USER,
            password=DB_PASSWORD
        )
        return conn
    except psycopg2.Error as e:
        print(f"Error connecting to PostgreSQL database in validator: {e}")
        return None

def fetch_unit_by_id(conn, unit_id):
    """Fetches a unit's full data (row) by its ID."""
    with conn.cursor(cursor_factory=psycopg2.extras.DictCursor) as cur:
        cur.execute("SELECT * FROM units WHERE id = %s", (unit_id,))
        return cur.fetchone()

def fetch_validation_options(conn):
    """Fetches the (single row of) validation options."""
    with conn.cursor(cursor_factory=psycopg2.extras.DictCursor) as cur:
        cur.execute("SELECT data FROM unit_validation_options WHERE name = 'DefaultSettings'")
        row = cur.fetchone()
        return row['data'] if row else None

def load_json_schema(schema_filename, base_path="schemas"):
    """Loads a JSON schema from the specified base_path directory."""
    schema_path = Path(base_path) / schema_filename
    try:
        with open(schema_path, 'r', encoding='utf-8') as f:
            return json.load(f)
    except FileNotFoundError:
        print(f"Error: Schema file not found: {schema_path}")
        return None
    except json.JSONDecodeError as e:
        print(f"Error decoding JSON from schema {schema_path}: {e}")
        return None

# --- Validation Functions ---

def validate_schema_compliance(unit_data_jsonb, unit_type, base_schema_path_str="schemas"):
    errors = []
    # Ensure unit_type is a string and handle potential None values
    unit_type_lower = str(unit_type).lower().replace(" ", "") if unit_type else "unknown"

    schema_name_map = {
        "meks": "battleMechSchema.json", "battlemech": "battleMechSchema.json",
        "lam": "lamSchema.json", "lams": "lamSchema.json",
        "vehicles": "vehicleSchema.json", "vehicle": "vehicleSchema.json",
        "fighters": "fighterSchema.json", "aerospacefighter": "fighterSchema.json",
        "battlearmor": "battleArmorSchema.json",
        "protomek": "protoMekSchema.json", "protomeks": "protoMekSchema.json",
        "infantry": "infantrySchema.json",
        "convfighter": "conventionalFighterSchema.json", "conventionalfighter": "conventionalFighterSchema.json",
        "dropship": "dropshipSchema.json", "dropships": "dropshipSchema.json",
        "smallcraft": "smallCraftSchema.json",
        "jumpship": "jumpshipSchema.json", "jumpships": "jumpshipSchema.json",
        "warship": "warshipSchema.json", "warships": "warshipSchema.json",
        "spacestation": "spaceStationSchema.json", "spacestations": "spaceStationSchema.json",
        "gunemplacement": "gunEmplacementSchema.json", "ge": "gunEmplacementSchema.json",
        "handheld": "handheldSchema.json" # Added handheld
    }

    schema_filename = schema_name_map.get(unit_type_lower)
    if not schema_filename:
        errors.append(f"Schema Validation: No schema mapping for unit_type '{unit_type}'.")
        return errors

    # Create a Path object for the base schema path
    base_path = Path(base_schema_path_str).resolve()

    # Load the main schema
    main_schema = load_json_schema(schema_filename, base_path)
    if not main_schema:
        errors.append(f"Schema Validation: Could not load schema '{schema_filename}'.")
        return errors

    # Create a resolver for local file references
    # The base_uri should point to the directory containing the schemas
    # The referrer is the main schema document itself.
    resolver = RefResolver(base_uri=base_path.as_uri() + "/", referrer=main_schema, store={})

    try:
        validate(instance=unit_data_jsonb, schema=main_schema, resolver=resolver)
    except jsonschema_exceptions.ValidationError as e:
        errors.append(f"Schema Error: {e.message} (Path: {list(e.path)})")
    except jsonschema_exceptions.RefResolutionError as e:
        errors.append(f"Schema Resolution Error: {e}")
    except Exception as e:
        errors.append(f"Schema Validation: Unexpected error: {e}")

    return errors

def get_equipment_stat(item_name_on_unit, equipment_data_map, stat_key, default_value=0):
    clean_name = item_name_on_unit.strip()
    for _, eq_data in equipment_data_map.items():
        if eq_data.get('name', '').strip() == clean_name:
            if stat_key == "tonnage": return eq_data.get("tonnage", default_value)
            if stat_key == "critical_slots": return eq_data.get("critical_slots", default_value)
            return eq_data.get(stat_key, default_value)
    return default_value


def apply_ceil_weight(weight, rule):
    if rule == "HALFTON":
        return (int(weight * 2) + (1 if weight * 2 % 1 > 0.00001 else 0)) / 2.0 if weight > 0 else 0
    elif rule == "KILO":
        return round(weight, 3)
    elif rule == "TON":
        return float(int(weight + 0.999)) if weight > 0 else 0
    return weight


def validate_weight(unit_db_row, validation_options, equipment_data_map):
    errors = []
    unit_json = unit_db_row['data']
    unit_type_for_rules = unit_db_row['unit_type'].lower()
    if unit_type_for_rules == "meks": unit_type_for_rules = "mek"
    if unit_type_for_rules == "vehicles": unit_type_for_rules = "tank"

    entity_rules = validation_options.get(unit_type_for_rules)
    if not entity_rules: return errors

    # errors.append("Weight Validation: Full weight recalculation not implemented due to complexity.")
    # Placeholder - actual detailed weight validation is very complex
    return errors


def validate_critical_slots(unit_db_row, validation_options, equipment_data_map):
    errors = []
    # errors.append("Criticals: Full critical slot validation not implemented.")
    return errors

def validate_armor(unit_db_row, validation_options, equipment_data_map):
    errors = []
    # errors.append("Armor: Full armor validation not implemented.")
    return errors

def validate_equipment_legality(unit_db_row, validation_options, equipment_data_map, current_year_for_rules=3067):
    errors = []
    unit_json = unit_db_row['data']
    unit_era_str = str(unit_json.get('era', unit_db_row.get('era', "Unknown")))
    # Ensure tech_base is fetched correctly, preferring the unit_json's specific field if available
    unit_tech_base = unit_json.get('tech_base', unit_db_row.get('tech_base', "Unknown"))


    try: unit_year = int(unit_era_str)
    except ValueError:
        era_year_map = {"succession wars": 2801, "clan invasion": 3050, "civil war": 3062, "jihad": 3068, "dark age": 3130, "ilclan": 3151}
        unit_year = era_year_map.get(unit_era_str.lower().replace("_", " "), current_year_for_rules)

    unit_type_for_rules = unit_db_row['unit_type'].lower()
    if unit_type_for_rules == "meks": unit_type_for_rules = "mek"
    if unit_type_for_rules == "vehicles": unit_type_for_rules = "tank"

    entity_rules = validation_options.get(unit_type_for_rules, {})
    ignore_list = entity_rules.get('ignoreFailedEquipment', [])
    intro_year_margin = entity_rules.get('introYearMargin', 0)
    show_incorrect_intro = entity_rules.get('showIncorrectIntroYear', False)
    show_failed_equip = entity_rules.get('showFailedEquip', True)

    all_equipment_references = []
    if unit_json.get("weapons_and_equipment") and isinstance(unit_json["weapons_and_equipment"], list):
        for equip in unit_json["weapons_and_equipment"]:
            if isinstance(equip, dict) and equip.get("item_name"): all_equipment_references.append(equip["item_name"])
            elif isinstance(equip, str): all_equipment_references.append(equip.split(',')[0].strip())
    if unit_json.get("criticals") and isinstance(unit_json["criticals"], list):
        for section in unit_json["criticals"]:
            if isinstance(section, dict):
                for slot_item in section.get('slots', []):
                    if slot_item and slot_item != "-Empty-": all_equipment_references.append(slot_item)

    for item_name_on_unit in set(all_equipment_references):
        clean_item_name = item_name_on_unit.strip()
        # Remove (IS) or (CL) suffixes for matching if they exist
        clean_item_name_base = re.sub(r'\s*\((IS|CL)\)\s*$', '', clean_item_name, flags=re.IGNORECASE).strip()

        if clean_item_name_base in ignore_list or clean_item_name in ignore_list : continue

        found_item_stats = None
        for eq_id_key, eq_data_val in equipment_data_map.items():
             # Try matching cleaned name, original name, or if item_name_on_unit is an internal_id itself
            if eq_data_val['name'] == clean_item_name or eq_data_val['name'] == clean_item_name_base or eq_id_key == clean_item_name_base.replace(" ","") :
                found_item_stats = eq_data_val
                break

        if not found_item_stats: # Try a more generic ID match if specific name match fails
            temp_id_from_name = re.sub(r'\s|\(|\)|\[|\]|/|\+|-', '', clean_item_name_base)
            for eq_id_key, eq_data_val in equipment_data_map.items():
                if temp_id_from_name in eq_id_key: # Check if the generated ID is part of a stored internal_id
                    found_item_stats = eq_data_val
                    break

        if not found_item_stats:
            if show_failed_equip: errors.append(f"Equip '{clean_item_name}' not in derivedEquipment.")
            continue

        eq_intro_year_str = str(found_item_stats.get('introduction_year', "Unknown"))
        eq_tech = found_item_stats.get('tech_base', "Unknown")

        try:
            eq_intro_year_val = int(eq_intro_year_str)
            if show_incorrect_intro and eq_intro_year_val > (unit_year + intro_year_margin):
                errors.append(f"Equip '{clean_item_name}' (intro: {eq_intro_year_val}) vs unit era {unit_year} (margin: {intro_year_margin}).")
        except ValueError:
            if unit_era_str != "Unknown" and eq_intro_year_str != "Unknown" and eq_intro_year_str != unit_era_str and eq_intro_year_str.lower() != "mixed" and eq_tech.lower() != "mixed":
                pass

        if unit_tech_base not in ["Unknown", "Mixed", None, ''] and eq_tech not in ["Unknown", "Mixed", None, ''] and unit_tech_base != eq_tech:
            is_clan_equip_on_is_unit_pre_invasion = (unit_tech_base == "Inner Sphere" and eq_tech == "Clan" and unit_year < 3050)
            if is_clan_equip_on_is_unit_pre_invasion:
                 if show_failed_equip: errors.append(f"Error: Clan equip '{clean_item_name}' on IS unit in era {unit_year} (<3050).")
    return errors


def validate_unit_data(db_unit_row, validation_options, equipment_data_map, base_schema_path_str="schemas"):
    all_errors_warnings = []
    if not db_unit_row or 'data' not in db_unit_row or 'unit_type' not in db_unit_row:
        all_errors_warnings.append("Validator Error: Invalid unit data row provided.")
        return all_errors_warnings

    unit_jsonb_data = db_unit_row['data']
    unit_type = db_unit_row['unit_type']

    schema_errors = validate_schema_compliance(unit_jsonb_data, unit_type, base_schema_path_str)
    if schema_errors: all_errors_warnings.extend([f"[Schema] {e}" for e in schema_errors])

    if validation_options is None:
        all_errors_warnings.append("Validator Error: Validation options missing.")
        return all_errors_warnings # Cannot proceed without options for some rules

    # These are mostly conceptual as full implementation is complex
    # all_errors_warnings.extend(validate_weight(db_unit_row, validation_options, equipment_data_map if equipment_data_map else {}))
    # all_errors_warnings.extend(validate_critical_slots(db_unit_row, validation_options, equipment_data_map if equipment_data_map else {}))
    # all_errors_warnings.extend(validate_armor(db_unit_row, validation_options, equipment_data_map if equipment_data_map else {}))

    if equipment_data_map:
        legality_errors = validate_equipment_legality(db_unit_row, validation_options, equipment_data_map)
        if legality_errors: all_errors_warnings.extend([f"[Legality] {e}" for e in legality_errors])

    return all_errors_warnings
