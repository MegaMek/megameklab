import os
import json
from pathlib import Path
import sys

# Ensure validator.py can be imported.
# Assuming validator.py is in the same directory or accessible via PYTHONPATH.
# If run_validation.py and validator.py are in the root, this should work.
sys.path.append(os.getcwd())
try:
    from validator import validate_schema_compliance, load_json_schema # Corrected import
except ImportError as e:
    print(f"Failed to import from validator.py: {e}")
    print("Ensure validator.py is in the current working directory or accessible via PYTHONPATH.")
    sys.exit(1)

CONVERTED_MEKFILES_DIR = "megameklab_converted_output/mekfiles"
SCHEMAS_DIR = "schemas"
OUTPUT_REPORT_FILE = "schema_validation_report.json"

def determine_unit_type_from_path(file_path: Path, base_mekfiles_dir: Path) -> str:
    """
    Determines unit type based on the first subdirectory within the base_mekfiles_dir.
    Example: megameklab_converted_output/mekfiles/meks/somefile.json -> meks
    """
    try:
        relative_path = file_path.relative_to(base_mekfiles_dir)
        # The first part of the relative path should be the unit type directory
        if relative_path.parts:
            return relative_path.parts[0]
    except ValueError:
        # This can happen if file_path is not under base_mekfiles_dir, though it shouldn't in this script's logic.
        pass
    return "unknown" # Default or fallback

def main():
    print(f"Starting schema-only validation of files in: {CONVERTED_MEKFILES_DIR}")
    print(f"Using schemas from: {SCHEMAS_DIR}")

    base_mekfiles_path = Path(CONVERTED_MEKFILES_DIR)
    if not base_mekfiles_path.is_dir():
        print(f"Error: Converted mekfiles directory not found: {CONVERTED_MEKFILES_DIR}")
        return

    if not Path(SCHEMAS_DIR).is_dir():
        print(f"Error: Schemas directory not found: {SCHEMAS_DIR}")
        return

    validation_results = {}
    files_processed = 0
    files_with_errors = 0

    for dirpath, _, filenames in os.walk(base_mekfiles_path):
        for filename in filenames:
            if filename.endswith(".json") and filename not in ["derivedEquipment.json", "UnitVerifierOptions.json"]:
                filepath = Path(dirpath) / filename
                unit_id_for_report = str(filepath.relative_to(base_mekfiles_path))
                files_processed += 1

                if files_processed % 500 == 0:
                    print(f"Processed {files_processed} files...")

                unit_type = determine_unit_type_from_path(filepath, base_mekfiles_path)
                if unit_type == "unknown":
                    validation_results[unit_id_for_report] = [f"Validator Error: Could not determine unit_type for {filepath}"]
                    files_with_errors += 1
                    continue

                try:
                    with open(filepath, 'r', encoding='utf-8') as f:
                        unit_json_data = json.load(f)
                except json.JSONDecodeError as e:
                    validation_results[unit_id_for_report] = [f"JSON Decode Error: {e}"]
                    files_with_errors += 1
                    continue
                except Exception as e:
                    validation_results[unit_id_for_report] = [f"File Read Error: {e}"]
                    files_with_errors += 1
                    continue

                # Ensure unit_json_data is a dict, as expected by validate_schema_compliance
                if not isinstance(unit_json_data, dict):
                    validation_results[unit_id_for_report] = [f"Validator Error: Unit data is not a JSON object (dict). Found type: {type(unit_json_data)}"]
                    files_with_errors +=1
                    continue

                errors = validate_schema_compliance(unit_json_data, unit_type, SCHEMAS_DIR)
                if errors:
                    validation_results[unit_id_for_report] = errors
                    files_with_errors += 1

    print(f"Saving schema validation report to {OUTPUT_REPORT_FILE}...")
    try:
        with open(OUTPUT_REPORT_FILE, 'w', encoding='utf-8') as f:
            json.dump(validation_results, f, indent=2, ensure_ascii=False)
        print("Schema validation report saved.")
    except Exception as e:
        print(f"Error saving schema validation report: {e}")

    print("\n--- Schema Validation Summary ---")
    print(f"Total JSON files processed (excluding derivedEquipment & UnitVerifierOptions): {files_processed}")
    print(f"Files with schema errors or processing issues: {files_with_errors}")
    if files_processed > 0:
        error_rate = (files_with_errors / files_processed) * 100
        print(f"Error rate: {error_rate:.2f}%")

if __name__ == "__main__":
    main()
