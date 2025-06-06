import os
import json
import psycopg2
import psycopg2.extras # For DictCursor
from pathlib import Path
import validator # Import the validator module created previously

# Base directory for converted JSONs and where schemas are expected to be (relative to run_validation.py)
CONVERTED_MEKFILES_DIR = "megameklab_converted_output/mekfiles"
SCHEMAS_DIR = "schemas" # Assumes schemas are in a 'schemas' subdirectory

def fetch_all_units(conn):
    """Fetches all units from the database."""
    units = []
    with conn.cursor(cursor_factory=psycopg2.extras.DictCursor) as cur:
        cur.execute("SELECT * FROM units") # Add "LIMIT 10" for testing on a small sample
        units = cur.fetchall()
    return units

def load_all_equipment(conn):
    """Loads all equipment into a dictionary keyed by internal_id."""
    equipment_map = {}
    with conn.cursor(cursor_factory=psycopg2.extras.DictCursor) as cur:
        cur.execute("SELECT internal_id, data FROM equipment")
        for row in cur:
            # The 'data' column is already a dict due to JSONB and DictCursor
            equipment_map[row['internal_id']] = row['data']
    return equipment_map


def main():
    print("Starting validation process...")
    conn = validator.get_db_connection()
    if not conn:
        print("Failed to connect to database. Exiting.")
        return

    # 1. Load Validation Options
    print("Fetching validation options...")
    validation_options_data = validator.fetch_validation_options(conn)
    if not validation_options_data:
        print("Could not fetch validation options. Some validation rules may not apply.")
        # Decide if to proceed or exit. For now, let's proceed but some validations might be skipped by validator functions.

    # 2. Load All Equipment Data
    print("Loading all equipment data...")
    equipment_data_map = load_all_equipment(conn)
    if not equipment_data_map:
        print("Warning: No equipment data loaded. Legality and component-specific validations will be limited.")

    # 3. Fetch Units to Validate
    print("Fetching units from database...")
    units_to_validate = fetch_all_units(conn)
    if not units_to_validate:
        print("No units found in the database to validate.")
        conn.close()
        return

    print(f"Found {len(units_to_validate)} units to validate.")

    validation_report = {}
    units_with_issues = 0
    total_issues = 0

    # 4. Iterate and Validate
    for i, unit_row in enumerate(units_to_validate):
        unit_id_for_report = unit_row["original_file_path"] # Use path as a unique ID for report
        if (i + 1) % 100 == 0: # Print progress every 100 units
            print(f"Validating unit {i+1}/{len(units_to_validate)}: {unit_id_for_report}")

        # Ensure 'data' and 'unit_type' are present, 'data' should be dict
        if 'data' not in unit_row or not isinstance(unit_row['data'], dict) or 'unit_type' not in unit_row :
            validation_report[unit_id_for_report] = ["Validator Error: Unit row is missing 'data' or 'unit_type', or 'data' is not JSON."]
            units_with_issues +=1
            total_issues +=1
            continue

        errors_warnings = validator.validate_unit_data(
            unit_row,
            validation_options_data,
            equipment_data_map,
            base_schema_path_str=SCHEMAS_DIR
        )

        if errors_warnings:
            validation_report[unit_id_for_report] = errors_warnings
            units_with_issues += 1
            total_issues += len(errors_warnings)

    # 5. Reporting
    report_filepath = "validation_report.json"
    print(f"\nSaving validation report to {report_filepath}...")
    try:
        with open(report_filepath, 'w', encoding='utf-8') as f:
            json.dump(validation_report, f, indent=2, ensure_ascii=False)
        print("Validation report saved.")
    except Exception as e:
        print(f"Error saving validation report: {e}")

    print("\n--- Validation Summary ---")
    print(f"Total units validated: {len(units_to_validate)}")
    print(f"Units with issues: {units_with_issues}")
    print(f"Total issues found: {total_issues}")

    if conn:
        conn.close()
        print("Database connection closed.")

if __name__ == "__main__":
    # Ensure the schemas directory is accessible
    if not os.path.isdir(SCHEMAS_DIR):
        print(f"Error: Schemas directory '{SCHEMAS_DIR}' not found.")
        print(f"Please ensure all JSON schema files are in a '{SCHEMAS_DIR}' subdirectory relative to this script.")
    else:
        main()
