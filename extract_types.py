import json
from pathlib import Path
import sys

def extract_unique_types(file_path_str):
    file_path = Path(file_path_str)
    if not file_path.is_file():
        print(f"Error: File not found: {file_path}", file=sys.stderr)
        return []

    try:
        with open(file_path, 'r', encoding='utf-8') as f:
            data = json.load(f)
    except json.JSONDecodeError as e:
        print(f"Error decoding JSON from {file_path}: {e}", file=sys.stderr)
        return []
    except Exception as e:
        print(f"Error reading file {file_path}: {e}", file=sys.stderr)
        return []

    if not isinstance(data, list):
        print(f"Error: Expected a list of equipment objects, got {type(data)}", file=sys.stderr)
        return []

    unique_types = set()
    for item in data:
        if isinstance(item, dict) and 'type' in item:
            item_type = item.get('type')
            if isinstance(item_type, str): # Ensure type is a string before adding
                unique_types.add(item_type)
            # else:
                # Optionally log items with non-string types if that's unexpected
                # print(f"Warning: Item found with non-string type: {item_type} in item {item.get('name')}", file=sys.stderr)

    return sorted(list(unique_types))

if __name__ == "__main__":
    # The file path will be an argument to the script
    if len(sys.argv) > 1:
        target_file = sys.argv[1]
        types = extract_unique_types(target_file)
        if types: # Only print if types were successfully extracted
            print("Unique 'type' values found (sorted):")
            for equip_type in types:
                print(equip_type)
    else:
        print("Error: No file path provided for derivedEquipment.json", file=sys.stderr)
