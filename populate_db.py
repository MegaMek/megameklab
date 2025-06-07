import os
import json
import sqlite3 # Changed from psycopg2
from pathlib import Path

# --- Database File ---
SQLITE_DB_FILE = "battletech_dev.sqlite" # New SQLite DB file

BASE_INPUT_DIR = "megameklab_converted_output"
MEKFILES_INPUT_DIR = os.path.join(BASE_INPUT_DIR, "mekfiles")

def get_db_connection():
    conn = None
    try:
        conn = sqlite3.connect(SQLITE_DB_FILE)
        print(f"Successfully connected to SQLite database: {SQLITE_DB_FILE}")
        return conn
    except sqlite3.Error as e:
        print(f"Error connecting to SQLite database {SQLITE_DB_FILE}: {e}")
        raise # Re-raise SQLite errors
    except Exception as ex:
        print(f"An unexpected error occurred during DB connection: {ex}")
        raise

def create_schema(conn):
    """Creates database schema from schema_sqlite.sql if it doesn't exist."""
    try:
        schema_path = "schema_sqlite.sql" # Assuming it's in the same directory
        if not os.path.exists(schema_path):
            print(f"FATAL: Schema file not found: {schema_path}")
            return False

        with open(schema_path, 'r', encoding='utf-8') as f:
            schema_sql = f.read()

        cur = conn.cursor()
        cur.executescript(schema_sql)
        conn.commit() # Commit schema changes
        print("SQLite schema created/verified successfully.")
        return True
    except sqlite3.Error as e:
        print(f"Error creating SQLite schema: {e}")
        conn.rollback() # Rollback in case of error during schema creation
        return False
    except Exception as ex:
        print(f"An unexpected error occurred during schema creation: {ex}")
        conn.rollback()
        return False

def populate_equipment(conn):
    filepath = os.path.join(MEKFILES_INPUT_DIR, "derivedEquipment.json")
    if not os.path.exists(filepath):
        print(f"Error: {filepath} not found.")
        return 0

    with open(filepath, 'r', encoding='utf-8') as f:
        equipment_list = json.load(f)

    inserted_updated_count = 0
    # SQLite uses ? for placeholders. ON CONFLICT requires an indexed column.
    # Assuming internal_id is unique and indexed for conflict resolution.
    sql = """
    INSERT OR REPLACE INTO equipment
        (internal_id, name, type, category, tech_base, data, created_at, updated_at)
    VALUES (?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
    """
    # Note: For created_at with INSERT OR REPLACE, it will always be the time of the last operation.
    # If strict "created_at only on first insert" is needed, a different approach is required.

    cur = conn.cursor()
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
                tech_base, json.dumps(item) # Store full JSON data as TEXT
            ))
            inserted_updated_count += 1
        except sqlite3.Error as e:
            print(f"Error inserting/replacing equipment {item.get('internal_id', 'N/A')}: {e}")
            # No conn.rollback() needed per statement in sqlite3 default mode, but good for consistency if batching
        except Exception as ex:
            print(f"Generic error processing equipment {item.get('internal_id', 'N/A')}: {ex}")
    conn.commit() # Commit all equipment inserts/replaces
    return inserted_updated_count

def populate_unit_validation_options(conn):
    filepath = os.path.join(MEKFILES_INPUT_DIR, "UnitVerifierOptions.json")
    if not os.path.exists(filepath):
        print(f"Error: {filepath} not found.")
        return 0

    options_data_root = None
    try:
        with open(filepath, 'r', encoding='utf-8') as f:
            options_data_root = json.load(f)

        options_data = options_data_root.get('entityverifier')
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
    INSERT OR REPLACE INTO unit_validation_options
        (name, data, created_at, updated_at)
    VALUES (?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
    """
    cur = conn.cursor()
    try:
        cur.execute(sql, ('DefaultSettings', json.dumps(options_data)))
        conn.commit()
        return 1
    except sqlite3.Error as e:
        print(f"Error inserting/replacing unit_validation_options: {e}")
        return 0
    except Exception as ex:
        print(f"Generic error processing unit_validation_options: {ex}")
        return 0

def populate_units(conn):
    inserted_updated_count = 0
    cur = conn.cursor()

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
                    if not isinstance(tech_base, str): tech_base = str(tech_base)

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
                    INSERT OR REPLACE INTO units
                        (original_file_path, unit_type, chassis, model, mul_id, tech_base, era, mass_tons, role, source_book, data, created_at, updated_at)
                    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
                    """
                    cur.execute(sql, (
                        original_file_rel_path, unit_type, chassis, model, mul_id,
                        tech_base, era, mass_tons, role, source_book, json.dumps(unit_json_data)
                    ))
                    inserted_updated_count += 1
                except json.JSONDecodeError as e:
                    print(f"Error decoding JSON from {filepath}: {e}")
                except sqlite3.Error as e:
                    print(f"Database error processing unit {original_file_rel_path}: {e}")
                except Exception as ex:
                    print(f"Generic error processing unit file {filepath}: {ex}")
    conn.commit() # Commit all unit inserts/replaces
    return inserted_updated_count

def main():
    conn = None
    try:
        # Remove old DB file if it exists to ensure fresh start
        if os.path.exists(SQLITE_DB_FILE):
            print(f"Removing existing SQLite DB file: {SQLITE_DB_FILE}")
            os.remove(SQLITE_DB_FILE)

        conn = get_db_connection()
        if conn is None:
            return

        if not create_schema(conn):
            print("Halting due to schema creation failure.")
            return

        print("Starting database population...")

        eq_count = populate_equipment(conn)
        print(f"Processed {eq_count} equipment items.")

        opt_count = populate_unit_validation_options(conn)
        print(f"Processed {opt_count} unit validation options rows.")

        unit_count = populate_units(conn)
        print(f"Processed {unit_count} unit files.")

        print("Database population complete.")

    except sqlite3.Error as e: # Catch SQLite specific errors in main
        print(f"A SQLite database error occurred during main execution: {e}")
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
