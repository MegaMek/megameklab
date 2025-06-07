import os
import json
import sqlite3
from pathlib import Path

# --- Database Connection Parameters ---
SQLITE_DB_PATH = os.environ.get("SQLITE_DB_PATH", "battletech_editor.sqlite")

BASE_INPUT_DIR = "megameklab_converted_output"
MEKFILES_INPUT_DIR = os.path.join(BASE_INPUT_DIR, "mekfiles")

def get_db_connection():
    conn = None
    try:
        conn = sqlite3.connect(SQLITE_DB_PATH, check_same_thread=False)
        return conn
    except sqlite3.Error as e: # Catch specific connection errors
        print(f"FATAL: Could not connect to SQLite database: {e}")
        print(f"Ensure the path to the SQLite database file is correct and writable:")
        print(f"  SQLITE_DB_PATH: {SQLITE_DB_PATH}")
        return None # Explicitly return None if connection fails
    except Exception as ex:
        print(f"An unexpected error occurred during DB connection: {ex}")
        raise # Re-raise other exceptions


def populate_equipment(conn):
    filepath = os.path.join(MEKFILES_INPUT_DIR, "derivedEquipment.json")
    if not os.path.exists(filepath):
        print(f"Error: {filepath} not found.")
        return 0

    with open(filepath, 'r', encoding='utf-8') as f:
        equipment_list = json.load(f)

    inserted_updated_count = 0
    sql = """
    INSERT INTO equipment (internal_id, name, type, category, tech_base, data, created_at, updated_at)
    VALUES (?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
    ON CONFLICT (internal_id) DO UPDATE SET
        name = EXCLUDED.name,
        type = EXCLUDED.type,
        category = EXCLUDED.category,
        tech_base = EXCLUDED.tech_base,
        data = EXCLUDED.data,
        updated_at = CURRENT_TIMESTAMP;
    """
    with conn.cursor() as cur:
        for item in equipment_list:
            try:
                internal_id = item.get('internal_id')
                name = item.get('name')
                item_type = item.get('type', 'Unknown')
                category = item.get('category', 'Unknown')
                tech_base = item.get('tech_base', 'Unknown')

                if not internal_id or not name:
                    print(f"Skipping equipment item due to missing internal_id or name: {item.get('name', 'Unnamed')}")
                    continue

                cur.execute(sql, (
                    internal_id, name, item_type, category,
                    tech_base, json.dumps(item)
                ))
                inserted_updated_count += 1
            except sqlite3.Error as e:
                print(f"Error inserting/updating equipment {item.get('internal_id', 'N/A')}: {e}")
                conn.rollback()
            except Exception as ex:
                print(f"Generic error processing equipment {item.get('internal_id', 'N/A')}: {ex}")
                conn.rollback()
        conn.commit()
    return inserted_updated_count

def populate_unit_validation_options(conn):
    filepath = os.path.join(MEKFILES_INPUT_DIR, "UnitVerifierOptions.json")
    if not os.path.exists(filepath):
        print(f"Error: {filepath} not found.")
        return 0

    options_data = None
    try:
        with open(filepath, 'r', encoding='utf-8') as f:
            options_data_root = json.load(f)
            options_data = options_data_root.get('entityverifier') # Data is nested under this key
            if not options_data:
                print(f"Error: 'entityverifier' key not found in {filepath}")
                return 0
    except json.JSONDecodeError as e:
        print(f"Error decoding JSON from {filepath}: {e}")
        return 0
    except Exception as ex:
        print(f"Error reading {filepath}: {ex}")
        return 0

    sql = """
    INSERT INTO unit_validation_options (name, data, created_at, updated_at)
    VALUES (?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
    ON CONFLICT (name) DO UPDATE SET
        data = EXCLUDED.data,
        updated_at = CURRENT_TIMESTAMP;
    """
    with conn.cursor() as cur:
        try:
            cur.execute(sql, ('DefaultSettings', json.dumps(options_data)))
            conn.commit()
            return 1
        except sqlite3.Error as e:
            print(f"Error inserting/updating unit_validation_options: {e}")
            conn.rollback()
            return 0
        except Exception as ex:
            print(f"Generic error processing unit_validation_options: {ex}")
            conn.rollback()
            return 0

def populate_units(conn):
    inserted_updated_count = 0

    for dirpath, _, filenames in os.walk(MEKFILES_INPUT_DIR):
        for filename in filenames:
            if filename.endswith(".json") and filename not in ["derivedEquipment.json", "UnitVerifierOptions.json"]:
                filepath = os.path.join(dirpath, filename)
                original_file_rel_path = str(Path(filepath).relative_to(MEKFILES_INPUT_DIR))

                try:
                    with open(filepath, 'r', encoding='utf-8') as f:
                        unit_json_data = json.load(f)

                    relative_to_mekfiles_root = Path(dirpath).relative_to(MEKFILES_INPUT_DIR)
                    unit_type = relative_to_mekfiles_root.parts[0] if relative_to_mekfiles_root.parts else "Unknown"

                    chassis = unit_json_data.get('chassis', unit_json_data.get('name'))
                    model = unit_json_data.get('model', '')
                    mul_id = str(unit_json_data.get('mul_id', '')) if unit_json_data.get('mul_id') is not None else None

                    tech_base = unit_json_data.get('techbase', unit_json_data.get('derived_tech_base', 'Unknown'))
                    if not isinstance(tech_base, str): tech_base = str(tech_base) # Ensure string

                    era_raw = unit_json_data.get('era', unit_json_data.get('derived_era', 'Unknown'))
                    era = str(era_raw)

                    mass_raw = unit_json_data.get('mass', unit_json_data.get('tonnage'))
                    mass_tons = None
                    if mass_raw is not None:
                        try: mass_tons = int(float(mass_raw))
                        except (ValueError, TypeError): pass

                    role = unit_json_data.get('role', 'Unknown')
                    if not isinstance(role, str): role = str(role)

                    source_book = unit_json_data.get('source', unit_json_data.get('source_book'))
                    if not isinstance(source_book, str) and source_book is not None: source_book = str(source_book)


                    sql = """
                    INSERT INTO units (original_file_path, unit_type, chassis, model, mul_id, tech_base, era, mass_tons, role, source_book, data, created_at, updated_at)
                    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
                    ON CONFLICT (original_file_path) DO UPDATE SET
                        unit_type = EXCLUDED.unit_type, chassis = EXCLUDED.chassis, model = EXCLUDED.model,
                        mul_id = EXCLUDED.mul_id, tech_base = EXCLUDED.tech_base, era = EXCLUDED.era,
                        mass_tons = EXCLUDED.mass_tons, role = EXCLUDED.role, source_book = EXCLUDED.source_book,
                        data = EXCLUDED.data, updated_at = CURRENT_TIMESTAMP;
                    """
                    with conn.cursor() as cur:
                        cur.execute(sql, (
                            original_file_rel_path, unit_type, chassis, model, mul_id,
                            tech_base, era, mass_tons, role, source_book, json.dumps(unit_json_data)
                        ))
                    inserted_updated_count += 1
                except json.JSONDecodeError as e:
                    print(f"Error decoding JSON from {filepath}: {e}")
                except sqlite3.Error as e:
                    print(f"Database error processing unit {original_file_rel_path}: {e}")
                    conn.rollback()
                except Exception as ex:
                    print(f"Generic error processing unit file {filepath}: {ex}")
                    conn.rollback()
        conn.commit()
    return inserted_updated_count

def main():
    conn = None
    try:
        conn = get_db_connection()
        if conn is None: # Exit if connection failed
            return

        print("Starting database population...")

        eq_count = populate_equipment(conn)
        print(f"Processed {eq_count} equipment items.")

        opt_count = populate_unit_validation_options(conn)
        print(f"Processed {opt_count} unit validation options rows.")

        unit_count = populate_units(conn)
        print(f"Processed {unit_count} unit files.")

        print("Database population complete.")

    except sqlite3.Error as e:
        print(f"A database error occurred during main execution: {e}")
    except Exception as e:
        print(f"An unexpected error occurred during main execution: {e}")
    finally:
        if conn:
            conn.close()
            print("Database connection closed.")

if __name__ == "__main__":
    if not os.path.isdir(MEKFILES_INPUT_DIR):
        print(f"Error: Input directory '{MEKFILES_INPUT_DIR}' not found.")
        print(f"Ensure the '{BASE_INPUT_DIR}' directory exists and contains 'mekfiles' with converted JSON data.")
        print(f"Current working directory: {os.getcwd()}")
        print(f"Expected structure: {os.path.abspath(BASE_INPUT_DIR)}/mekfiles/")
    else:
        main()
# Removed the offending line that was here: ```
