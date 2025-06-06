import os
import json
import xml.etree.ElementTree as ET
import re
from pathlib import Path
import datetime

# --- Globals ---
DERIVED_EQUIPMENT = {} # Store unique equipment items
SKIPPED_FILES_LOG = "skipped_files.log" # Will be placed in the root of the output dir

# List of fundamental components that should default to IS tech for IS units
# This list can be expanded. Using lowercase for case-insensitive matching.
FUNDAMENTAL_IS_COMPONENTS = [
    "cockpit", "life support", "sensors", "gyro",
    "engine", "structure", "myomer",
    "heat sink", "jump jet", # Generic, specific types (Clan Double Heat Sink) will have their own names
    "shoulder", "upper arm actuator", "lower arm actuator", "hand actuator",
    "hip", "upper leg actuator", "lower leg actuator", "foot actuator"
]

# --- Schema Conformance (Placeholder) ---
def log_skipped_file(filepath, reason, output_dir_for_log):
    log_path = os.path.join(output_dir_for_log, SKIPPED_FILES_LOG)
    # Ensure the directory for the log file exists
    os.makedirs(os.path.dirname(log_path), exist_ok=True)
    with open(log_path, "a", encoding='utf-8') as f:
        f.write(f"Skipped: {filepath} - Reason: {reason}\n")

# --- MTF Parsing Logic ---
def parse_mtf_engine(engine_str):
    match = re.match(r"(\d+)\s*(.*?)(?:\(([^)]+)\))?$", engine_str)
    if match:
        rating, type_name, manufacturer = match.groups()
        return {
            "rating": int(rating),
            "type": type_name.strip(),
            "manufacturer": manufacturer.strip() if manufacturer else None
        }
    return {"raw": engine_str, "rating":0, "type": "Unknown", "manufacturer": None}

def parse_mtf_heat_sinks(hs_str):
    match = re.match(r"(\d+)\s*(.*?)(?:\sHeat Sink)?$", hs_str)
    if match:
        count, type_name = match.groups()
        return {
            "count": int(count),
            "type": type_name.strip()
        }
    return {"raw": hs_str, "count": 0, "type": "Unknown"}

def parse_mtf_structure(structure_str):
    match = re.match(r"(.+?)(?:\(([^)]+)\))?$", structure_str)
    if match:
        type_name, manufacturer = match.groups()
        return {
            "type": type_name.strip(),
            "manufacturer": manufacturer.strip() if manufacturer else None
        }
    return {"type": structure_str.strip(), "manufacturer": None}

def parse_mtf_armor(armor_str):
    match = re.match(r"(.+?)(?:\(([^)]+)\))?$", armor_str)
    if match:
        type_name, manufacturer = match.groups()
        return {
            "type": type_name.strip(),
            "manufacturer": manufacturer.strip() if manufacturer else None
        }
    return {"raw": armor_str, "type": "Unknown", "manufacturer": None}

def parse_mtf_myomer(myomer_str): # Already added in last turn, ensure it's correctly placed if re-applying
    # Similar to structure and armor, try to find a manufacturer in parentheses
    match = re.match(r"(.+?)(?:\(([^)]+)\))?$", myomer_str)
    if match:
        type_name, manufacturer = match.groups()
        return {
            "type": type_name.strip(),
            "manufacturer": manufacturer.strip() if manufacturer else None
        }
    return {"type": myomer_str.strip(), "manufacturer": None}

def get_year_from_path(filepath):
    try:
        parts = Path(filepath).parts
        for part in parts:
            match = re.match(r"(\d{4})U*", part)
            if match:
                return int(match.group(1))
    except Exception:
        pass
    return "Unknown"

def add_to_derived_equipment(item_name, item_type="Unknown", unit_tech_base_for_context="Unknown",
                             introduction_year="Unknown", source_file="N/A"):
    if not item_name or item_name == "-Empty-":
        return

    clean_item_name = item_name.strip()
    internal_id_base = re.sub(r'\s|\(|\)|\[|\]|/|\+|-', '', clean_item_name)
    internal_id = item_type.upper() + "_" + internal_id_base if item_type != "Unknown" else internal_id_base
    internal_id = internal_id.replace(" ","")

    # Determine item's specific tech_base
    item_specific_tech_base = "Unknown"
    if "(CL)" in clean_item_name.upper(): item_specific_tech_base = "Clan"
    elif "(IS)" in clean_item_name.upper(): item_specific_tech_base = "Inner Sphere"

    # If item_specific_tech_base is still Unknown, then use unit_tech_base_for_context
    # BUT, if it's a fundamental component and unit is IS, default to IS.
    final_tech_base = item_specific_tech_base
    if final_tech_base == "Unknown":
        is_fundamental = any(fundamental.lower() in clean_item_name.lower() for fundamental in FUNDAMENTAL_IS_COMPONENTS)
        if is_fundamental and unit_tech_base_for_context == "Inner Sphere":
            final_tech_base = "Inner Sphere"
        else:
            final_tech_base = unit_tech_base_for_context # Fallback to unit's tech base

    if internal_id not in DERIVED_EQUIPMENT:
        DERIVED_EQUIPMENT[internal_id] = {
            "name": clean_item_name,
            "internal_id": internal_id,
            "type": item_type,
            "category": "Unknown",
            "tech_base": final_tech_base, # Use the determined final_tech_base
            "rules_level": "Standard",
            "introduction_year": introduction_year,
            "extinction_year": "Unknown",
            "faction_availability": [],
            "technology_dependencies": [],
            "critical_slots": 0,
            "tonnage": 0,
            "cost_cbills": None,
            "battle_value": None,
            "source_book": None,
            "source_files": [source_file]
        }
    else:
        entry = DERIVED_EQUIPMENT[internal_id]
        if source_file not in entry["source_files"]:
            entry["source_files"].append(source_file)

        if entry["type"] == "Unknown" and item_type != "Unknown":
            entry["type"] = item_type

        # Update tech_base only if the new one is more specific or current one is Unknown
        if entry["tech_base"] == "Unknown" and final_tech_base != "Unknown":
             entry["tech_base"] = final_tech_base
        elif entry["tech_base"] == "Mixed" and final_tech_base not in ["Unknown", "Mixed"]: # Prefer specific over Mixed
            entry["tech_base"] = final_tech_base
        elif final_tech_base != "Unknown" and final_tech_base != "Mixed" and entry["tech_base"] != final_tech_base:
            # If there's a conflict (e.g. already IS, new is Clan, or vice-versa), mark as Mixed
            # unless one is clearly a more generic version of the other (this logic might need refinement)
            is_fundamental_entry = any(fundamental.lower() in entry["name"].lower() for fundamental in FUNDAMENTAL_IS_COMPONENTS)
            is_fundamental_new = any(fundamental.lower() in clean_item_name.lower() for fundamental in FUNDAMENTAL_IS_COMPONENTS)
            if not (is_fundamental_entry and is_fundamental_new): # Only set to mixed if not just variants of fundamental components
                 entry["tech_base"] = "Mixed"


        if introduction_year != "Unknown":
            if entry["introduction_year"] == "Unknown":
                entry["introduction_year"] = introduction_year
            elif isinstance(introduction_year, int) and isinstance(entry["introduction_year"], int):
                entry["introduction_year"] = min(entry["introduction_year"], introduction_year)
            elif isinstance(introduction_year, int) and isinstance(entry["introduction_year"], str): # Prefer int over string if current is string
                entry["introduction_year"] = introduction_year
            # If both are strings, no change unless one is "Unknown"

def parse_mtf_file(filepath, output_dir_for_log):
    data = {
        "weapons_and_equipment": [], "criticals": [], "quirks": [],
        "manufacturers": [], "system_manufacturers": [], "primary_factories": []
    }
    current_section_name = None
    current_section_items = []
    fluff_fields = ["overview", "capabilities", "deployment", "history"]
    current_fluff_field = None
    fluff_text_buffer = []

    base_filename = os.path.basename(filepath)
    unit_era_from_path_val = get_year_from_path(filepath) # Renamed to avoid conflict
    unit_tech_base_val = "Unknown" # Renamed to avoid conflict
    line_number = 0

    try:
        with open(filepath, 'r', encoding='utf-8', errors='ignore') as f:
            lines = f.readlines()

        # First pass to determine overall unit tech_base and era
        for line in lines:
            line_strip = line.strip()
            if ":" in line_strip:
                key, value = line_strip.split(":", 1)
                key_norm = key.strip().lower().replace(" ", "_")
                if key_norm == "techbase":
                    unit_tech_base_val = value.strip() # Use the renamed variable
                    data["tech_base"] = unit_tech_base_val # Keep original data key for output
                elif key_norm == "era":
                    era_val = value.strip()
                    try: data["era"] = int(era_val)
                    except ValueError: data["era"] = era_val
                    unit_era_from_path_val = data["era"] # Update with specific era if found

        weapon_section_started = False
        expected_weapon_count = 0
        actual_weapon_count = 0

        for ln_idx, line_content in enumerate(lines):
            line_number = ln_idx
            line = line_content.strip()

            if not line:
                if current_fluff_field and fluff_text_buffer:
                    data[current_fluff_field] = "\n".join(fluff_text_buffer).strip()
                    fluff_text_buffer = []
                    current_fluff_field = None
                weapon_section_started = False # Reset weapon section on empty line
                continue

            if line.lower().startswith("weapons:"):
                weapon_section_started = True
                try:
                    count_str = line.split(":")[1].strip()
                    if count_str.isdigit():
                        expected_weapon_count = int(count_str)
                    else: # Handle cases like "Weapons: SRM 6, Left Torso"
                        weapon_info_str = line.split(":", 1)[1].strip()
                        parts = weapon_info_str.split(',')
                        item_name = parts[0].strip()
                        location = parts[1].strip() if len(parts) > 1 else "Unknown"
                        data["weapons_and_equipment"].append({"item_name": item_name, "location": location})
                        add_to_derived_equipment(item_name, "Weapon", unit_tech_base_val, unit_era_from_path_val, base_filename)
                        actual_weapon_count = 1 # Set to 1 as we found one
                        expected_weapon_count = 1 # Assume only one if format is like this
                except (IndexError, ValueError):
                    expected_weapon_count = 0 # Couldn't parse count, assume 0 and let individual lines handle
                continue

            if weapon_section_started and (expected_weapon_count == 0 or actual_weapon_count < expected_weapon_count):
                # If expected_weapon_count is 0, it means we didn't parse a number, so treat each line as a weapon
                # Otherwise, only parse if actual_weapon_count < expected_weapon_count
                parts = line.split(',')
                if len(parts) >= 2 : # Basic check for item, location format
                    item_name = parts[0].strip()
                    location = parts[1].strip()
                    data["weapons_and_equipment"].append({"item_name": item_name, "location": location})
                    add_to_derived_equipment(item_name, "Weapon", unit_tech_base_val, unit_era_from_path_val, base_filename)
                    actual_weapon_count += 1
                elif expected_weapon_count == 0 and len(parts) == 1 and line.strip(): # Case for single item name on a line without location
                    item_name = line.strip()
                    data["weapons_and_equipment"].append({"item_name": item_name, "location": "Unknown"})
                    add_to_derived_equipment(item_name, "Weapon", unit_tech_base_val, unit_era_from_path_val, base_filename)
                    actual_weapon_count += 1

                if expected_weapon_count > 0 and actual_weapon_count >= expected_weapon_count:
                    weapon_section_started = False # Reached expected count
                continue


            if line.endswith(':') and not any(line.lower().startswith(fluff_field) for fluff_field in fluff_fields) and ' Ammo:' not in line and not re.match(r"^\w+\s\w+:", line) and "Armor:" not in line:
                if current_section_name and current_section_items:
                    data["criticals"].append({"location": current_section_name, "slots": current_section_items})
                current_section_name = line[:-1]
                current_section_items = []
                current_fluff_field = None
                if fluff_text_buffer:
                    if "last_major_key" in data and data["last_major_key"] in fluff_fields:
                         data[data["last_major_key"]] = "\n".join(fluff_text_buffer).strip()
                    fluff_text_buffer = []
                continue

            is_fluff_header = False
            for fluff_field in fluff_fields:
                if line.lower().startswith(fluff_field + ":"):
                    if current_fluff_field and fluff_text_buffer:
                        data[current_fluff_field] = "\n".join(fluff_text_buffer).strip()
                    current_fluff_field = fluff_field
                    fluff_text_buffer = [line[len(fluff_field)+1:].strip()]
                    is_fluff_header = True
                    if current_section_name:
                        data["criticals"].append({"location": current_section_name, "slots": current_section_items})
                        current_section_name = None; current_section_items = []
                    break
            if is_fluff_header: continue
            if current_fluff_field: fluff_text_buffer.append(line); continue

            if ":" in line:
                key, value = line.split(":", 1)
                key_norm = key.strip().lower().replace(" ", "_").replace("(", "").replace(")", "")
                value_stripped = value.strip() # Use stripped value for processing

                if key_norm == "engine": data["engine"] = parse_mtf_engine(value_stripped) # Schema name: engine
                elif key_norm == "heat_sinks": data["heat_sinks"] = parse_mtf_heat_sinks(value_stripped) # Schema name: heat_sinks
                elif key_norm == "armor" and not current_section_name: # Ensure this is not inside a criticals section
                    data["armor_details"] = parse_mtf_armor(value_stripped) # Keep temporary name for now
                    if "armor_locations_map" not in data: data["armor_locations_map"] = {} # Initialize if not present (use different name)
                elif key_norm.endswith("_armor") and "armor_locations_map" in data :
                    data["armor_locations_map"][key_norm.upper().replace("_ARMOR","")] = int(value_stripped) if value_stripped.isdigit() else value_stripped
                elif key_norm == "quirk": data["quirks"].append(value_stripped)
                elif key_norm == "structure": data["structure"] = parse_mtf_structure(value_stripped) # Use new parser
                elif key_norm == "manufacturer": data["manufacturers"].append(value_stripped)
                elif key_norm == "primaryfactory": data["primary_factories"].append(value_stripped)
                elif key_norm == "systemmanufacturer":
                    sys_type, sys_name = value_stripped.split(":", 1) if ":" in value_stripped else (key, value_stripped)
                    data["system_manufacturers"].append({"type": sys_type.strip(), "name": sys_name.strip()})
                elif key_norm == "mul_id": data["mul_id"] = value_stripped
                elif key_norm == "myomer": data["myomer"] = parse_mtf_myomer(value_stripped)
                # Avoid overwriting already processed special keys like engine, heat_sinks, structure, myomer, techbase, era, weapons, armor
                elif key_norm not in ["engine", "heat_sinks", "structure", "myomer", "techbase", "era", "weapons", "armor", "conversion_notes"]:
                     data[key_norm] = value_stripped
                     data["last_major_key"] = key_norm # Track for potential multi-line fluff
            elif current_section_name:
                current_section_items.append(line)
                item_type_in_crit = "Unknown"; l_line = line.lower()
                if "actuator" in l_line: item_type_in_crit = "Actuator"
                elif any(w in l_line for w in ["laser", "ppc", "flamer", "ac/", "autocannon", "gauss", "lb-x", "lrm", "srm", "mrm", "machine gun", " mg", " rifle"]): item_type_in_crit = "Weapon"
                elif "ammo" in l_line: item_type_in_crit = "Ammo"
                elif "heat sink" in l_line: item_type_in_crit = "HeatSink"
                elif "case" in l_line and "ii" not in l_line : item_type_in_crit = "CASE" # Avoid CASE II being just CASE
                elif "targeting computer" in l_line: item_type_in_crit = "TargetingComputer"
                # ... (other inferences)
                add_to_derived_equipment(line, item_type_in_crit, unit_tech_base_val, unit_era_from_path_val, base_filename)

            if current_section_name and current_section_items and line_number == len(lines) -1 :
                data["criticals"].append({"location": current_section_name, "slots": current_section_items})
            if current_fluff_field and fluff_text_buffer and line_number == len(lines) -1:
                data[current_fluff_field] = "\n".join(fluff_text_buffer).strip()

        # Consolidate armor data
        final_armor_data = data.pop("armor_details", {"type": "Unknown", "manufacturer": None})
        armor_locations_map = data.pop("armor_locations_map", {})
        final_armor_data["locations"] = []
        for loc, points in armor_locations_map.items():
            # MTF doesn't usually specify rear armor separately per location in this format
            final_armor_data["locations"].append({"location": loc, "armor_points": points, "rear_armor_points": None})
        data["armor"] = final_armor_data


    except Exception as e:
        log_skipped_file(filepath, f"MTF Parsing Error: {e} on line {line_number+1}", output_dir_for_log)
        return None
    return data

# --- BLK Parsing Logic ---
def parse_blk_tag_content(content_str):
    items = [item.strip() for item in content_str.strip().split('\n') if item.strip()]
    if len(items) == 1:
        val = items[0]
        if val.lower() == "true": return True
        if val.lower() == "false": return False
        if re.fullmatch(r"-?\d+", val): return int(val)
        if re.fullmatch(r"-?\d+\.\d+", val):
            try: return float(val)
            except ValueError: pass # Not a float, return as string
        return val # Return as string if no other type matches

    # If items is empty after stripping, or contains only empty strings, it should be null.
    # If it's a list of actual values, return the list.
    # This helps with tags that might be present but empty.
    items = [item for item in items if item] # Filter out empty strings from list
    if not items:
        return None # Will become null in JSON, preferred over empty list for single optional string fields
    return items if len(items) > 1 else items[0] # Return single item as string, else list

def parse_blk_file(filepath, output_dir_for_log):
    content = ""; data = {}; base_filename = os.path.basename(filepath)
    unit_era_from_path_val = get_year_from_path(filepath); unit_tech_base_val = "Unknown" # Renamed

    try:
        with open(filepath, 'r', encoding='utf-8', errors='ignore') as f:
            content = f.read()

        # Remove comments and #ifs
        content = re.sub(r"<!--.*?-->", "", content, flags=re.DOTALL)
        content = re.sub(r"#.*?\n", "", content)

        # Pre-scan for tech_base and era from <type> and <year>/<originalBuildYear>
        pre_scan_pattern = re.compile(r"<type>([^<]+)</type>|<year>([^<]+)</year>|<originalBuildYear>([^<]+)</originalBuildYear>", flags=re.IGNORECASE)
        for pre_match in pre_scan_pattern.finditer(content):
            if pre_match.group(1): # <type>
                type_val = pre_match.group(1).strip()
                if "clan" in type_val.lower(): unit_tech_base_val = "Clan"
                elif "inner sphere" in type_val.lower() or "is " in type_val.lower(): unit_tech_base_val = "Inner Sphere"
                elif "mixed" in type_val.lower(): unit_tech_base_val = "Mixed"
            elif pre_match.group(2): # <year>
                year_val = pre_match.group(2).strip()
                try: unit_era_from_path_val = int(year_val)
                except ValueError: unit_era_from_path_val = year_val
            elif pre_match.group(3) and unit_era_from_path_val == "Unknown": # <originalBuildYear>
                year_val = pre_match.group(3).strip()
                try: unit_era_from_path_val = int(year_val)
                except ValueError: unit_era_from_path_val = year_val

        pattern = re.compile(r"<([a-zA-Z0-9_ :]+?)>([\s\S]*?)</\1>", flags=re.IGNORECASE)
        for match in pattern.finditer(content):
            tag, value_str = match.groups()
            key = tag.strip().lower().replace(" ", "_").replace(":", "")
            parsed_value = parse_blk_tag_content(value_str)

            if key == "systemmanufacturers":
                key = "system_manufacturers"; current_list = data.get(key, []); processed_values = []
                if isinstance(parsed_value, list): processed_values = parsed_value
                elif isinstance(parsed_value, str): processed_values = [parsed_value]
                for item_str_val in processed_values:
                    if isinstance(item_str_val, str) and ":" in item_str_val:
                        sm_type, sm_name = item_str_val.split(":", 1)
                        current_list.append({"type": sm_type.strip(), "name": sm_name.strip()})
                data[key] = current_list
            elif key in data: # Handle multiple identical tags by creating/appending to a list
                if not isinstance(data[key], list): data[key] = [data[key]]
                data[key].append(parsed_value)
            else: data[key] = parsed_value

        data['derived_era'] = unit_era_from_path_val # Use renamed variable
        data['derived_tech_base'] = unit_tech_base_val # Use renamed variable

        # Equipment extraction logic (simplified, adjust as needed)
        equipment_section_keys = [
            "body_equipment", "front_equipment", "right_equipment", "left_equipment",
            "rear_equipment", "turret_equipment", "nose_equipment", "left_wing_equipment",
            "right_wing_equipment", "aft_equipment", "wings_equipment", "fuselage_equipment",
            "squad_equipment", "gun_equipment", "head_equipment", "torso_equipment",
            "legs_equipment", "main_gun_equipment", "field_guns_equipment", "equipment" # Generic equipment tag
        ]
        criticals_like_tags = ["criticals", "criticalslot"] # Common criticals container tags

        for section_key in equipment_section_keys:
            if section_key in data:
                items = data[section_key];
                if not isinstance(items, list): items = [items]
                for item_entry in items: # item_entry could be a string or a dict from nested tags
                    item_name = ""
                    if isinstance(item_entry, str):
                        item_name_match = re.match(r"([^:(]+)", item_entry)
                        item_name = item_name_match.group(1).strip() if item_name_match else item_entry
                    elif isinstance(item_entry, dict) and 'name' in item_entry: # If equipment is structured
                        item_name = item_entry['name']

                    if item_name:
                        item_type = "Unknown"; l_item_name = item_name.lower()
                        # Basic type inference (can be expanded)
                        if any(w in l_item_name for w in ["laser", "ppc", "flamer", "ac/", "autocannon", "gauss", "lb-x", "lrm", "srm", "mrm", "machine gun", " mg", " rifle"]): item_type = "Weapon"
                        elif "ammo" in l_item_name: item_type = "Ammo"
                        elif any(w in l_item_name for w in ["mount", "manipulator", "stealth", "ecm", "probe", "tag", "sensors", "case"]): item_type = "Equipment"
                        add_to_derived_equipment(item_name, item_type, unit_tech_base_val, unit_era_from_path_val, base_filename)

        # Extract from criticals sections
        for crit_tag_key in criticals_like_tags:
            if crit_tag_key in data:
                crit_sections = data[crit_tag_key]
                if not isinstance(crit_sections, list): crit_sections = [crit_sections]
                for section in crit_sections:
                    if isinstance(section, dict): # e.g. <criticals><location>RA</location><item>...</item></criticals>
                        # This part depends heavily on BLK structure for criticals, needs refinement
                        # Assuming items might be directly listed or under an <item> tag
                        items_in_section = section.get('item', [])
                        if not isinstance(items_in_section, list): items_in_section = [items_in_section]
                        for item_name in items_in_section:
                            if item_name and isinstance(item_name, str) and item_name.lower() != "empty":
                                add_to_derived_equipment(item_name, "Unknown", unit_tech_base_val, unit_era_from_path_val, base_filename)
                    # Could also have simpler structures like <criticals>item1\nitem2</criticals>
                    elif isinstance(section, str) and section.lower() != "empty":
                         add_to_derived_equipment(section, "Unknown", unit_tech_base_val, unit_era_from_path_val, base_filename)

        # --- Post-processing for BLK data structure ---
        if "structure" in data and isinstance(data["structure"], str):
            data["structure"] = {"type": data["structure"], "manufacturer": None}

        if "myomer" in data and isinstance(data["myomer"], str):
            data["myomer"] = {"type": data["myomer"].strip(), "manufacturer": None} # Ensure stripping for consistency

        if "engine" in data: # BLK engines can be complex. This is a simplification.
            if isinstance(data["engine"], str): # e.g. <engine>Standard 250</engine>
                engine_type_str = data["engine"]
                rating_match = re.search(r'(\d+)', engine_type_str)
                rating = int(rating_match.group(1)) if rating_match else 0
                engine_type = engine_type_str.replace(str(rating), "").strip() if rating else engine_type_str
                data["engine"] = {"type": engine_type, "rating": rating, "manufacturer": None}
            # If engine is already an object (e.g. from <engine><type>Standard</type><rating>250</rating></engine>), leave as is for now.

        if "heat_sinks" in data:
            if isinstance(data["heat_sinks"], str): # e.g. <heatsinks>10 Double</heatsinks>
                hs_str = data["heat_sinks"]
                count_match = re.search(r'(\d+)', hs_str)
                count = int(count_match.group(1)) if count_match else 0
                hs_type = hs_str.replace(str(count), "").strip() if count else hs_str
                data["heat_sinks"] = {"count": count, "type": hs_type}
            elif isinstance(data["heat_sinks"], int): # e.g. <heatsinks>10</heatsinks>
                data["heat_sinks"] = {"count": data["heat_sinks"], "type": "Unknown"}

        # Armor: Special handling for BattleArmor, could be expanded for other types
        # This assumes 'unit_type' might be available in data, or infer from path if needed
        unit_type_indicator = data.get("type", "").lower() # BLK often uses <type> for overall unit type
        is_ba = "battlearmor" in unit_type_indicator or "battle armor" in unit_type_indicator or \
                "megameklab/data/mekfiles/battlearmor" in filepath.lower()

        if "armor" in data:
            if is_ba and isinstance(data["armor"], (int, float)):
                armor_type = data.pop("armortype", "Unknown") if "armortype" in data else "Standard"
                data["armor"] = {
                    "type": armor_type,
                    "armor_points_per_trooper": int(data["armor"])
                    # BA schema does not have 'manufacturer' or 'locations' under top-level armor
                }
            elif isinstance(data["armor"], str): # For other unit types, if armor is just a string
                data["armor"] = {"type": data["armor"], "manufacturer": None, "locations": []}


        if "crew" in data and isinstance(data["crew"], int):
            data["crew"] = {"total_crew": data["crew"]} # Basic object

        if "quirks" in data and isinstance(data["quirks"], str):
            data["quirks"] = [data["quirks"]]
        elif "quirks" in data and data["quirks"] is None: # Ensure it's array or null
            pass # Keep as null (None)
        elif "quirks" not in data:
            data["quirks"] = None # Explicitly set to null if not present

        if "model" in data and isinstance(data["model"], list) and len(data["model"]) == 1:
            data["model"] = data["model"][0]
        elif "model" in data and isinstance(data["model"], list) and not data["model"]: # Empty list
             data["model"] = None


    except Exception as e:
        log_skipped_file(filepath, f"BLK Parsing Error: {e}", output_dir_for_log)
        return None
    return data

# --- XML Parsing Logic ---
def parse_xml_file(filepath, output_dir_for_log):
    try:
        tree = ET.parse(filepath)
        root = tree.getroot()
        data = {root.tag: {}}

        for entity_elem in root:
            entity_name = entity_elem.tag
            entity_data = {}
            for option_elem in entity_elem:
                option_tag = option_elem.tag
                if option_tag == "ceilWeight":
                    entity_data[option_tag] = {cw_elem.tag: cw_elem.text.strip() for cw_elem in option_elem}
                elif option_tag == "ignoreFailedEquipment":
                    if option_elem.text and option_elem.text.strip():
                        entity_data[option_tag] = [e.strip() for e in option_elem.text.strip().split(',')]
                    else: entity_data[option_tag] = []
                else:
                    text_content = option_elem.text.strip() if option_elem.text else ""
                    if text_content.lower() == "true": entity_data[option_tag] = True
                    elif text_content.lower() == "false": entity_data[option_tag] = False
                    elif re.fullmatch(r"-?\d+", text_content): entity_data[option_tag] = int(text_content)
                    elif re.fullmatch(r"-?\d+\.\d+", text_content):
                         try: entity_data[option_tag] = float(text_content)
                         except ValueError: entity_data[option_tag] = text_content
                    else: entity_data[option_tag] = text_content
            data[root.tag][entity_name] = entity_data
        return data
    except ET.ParseError as e:
        log_skipped_file(filepath, f"XML Parsing Error: {e}", output_dir_for_log)
        return None
    except Exception as e:
        log_skipped_file(filepath, f"General XML Processing Error: {e}", output_dir_for_log)
        return None

# --- JSON Output ---
def save_to_json(data, output_filepath, output_dir_for_log):
    try:
        os.makedirs(os.path.dirname(output_filepath), exist_ok=True)
        with open(output_filepath, 'w', encoding='utf-8') as f:
            json.dump(data, f, indent=2, ensure_ascii=False)
    except Exception as e:
        print(f"Error saving JSON to {output_filepath}: {e}")
        log_skipped_file(output_filepath, f"JSON Save Error: {e}", output_dir_for_log)

# --- Main Traversal and Processing ---
def process_files(root_dir, base_output_dir): # base_output_dir is now the fixed name dir
    processed_count = 0
    # The log file will be in base_output_dir
    # All converted files will go into base_output_dir/mekfiles/
    mekfiles_output_dir = os.path.join(base_output_dir, "mekfiles")
    os.makedirs(mekfiles_output_dir, exist_ok=True)


    for dirpath, _, filenames in os.walk(root_dir):
        for filename in filenames:
            filepath = os.path.join(dirpath, filename)
            relative_path = os.path.relpath(filepath, root_dir)
            output_filepath_json = os.path.join(mekfiles_output_dir, Path(relative_path).with_suffix('.json'))


            parsed_data = None
            if filename.lower().endswith(".mtf"):
                parsed_data = parse_mtf_file(filepath, base_output_dir) # Pass base_output_dir for logging
            elif filename.lower().endswith(".blk"):
                parsed_data = parse_blk_file(filepath, base_output_dir) # Pass base_output_dir for logging
            elif filename.lower() == "unitverifieroptions.xml":
                output_filepath_json = os.path.join(mekfiles_output_dir, "UnitVerifierOptions.json")
                parsed_data = parse_xml_file(filepath, base_output_dir) # Pass base_output_dir for logging

            if parsed_data:
                save_to_json(parsed_data, output_filepath_json, base_output_dir) # Pass base_output_dir for logging
                processed_count +=1
            elif not filename.lower().endswith(('.png', '.gif', '.jpg', '.jpeg', '.svg', '.txt', '.xsl', '.html', '.xml~', '.psd', '.md', '.pdf', '.doc', '.docx', '.odt', '.rtf', '.bmp', 'mtf_names.txt', 'name_changes.txt', 'verify.txt', '.tif', '.tiff', '.wav', '.mp3', '.zip', '.rar', '.7z', '.tar', '.gz', '.jar', '.class', '.pyc', '.pyo', '.bak', '.old', '.tmp', '.DS_Store', 'Thumbs.db', '.exe', '.dll', '.so', '.o', '.a', '.lib', '.obj', '.dat_java_crash_pid', '.log', '.timestamp')):
                 if filename.lower() != "unitverifieroptions.xml" or (filename.lower() == "unitverifieroptions.xml" and parsed_data is None) :
                    log_skipped_file(filepath, "Unsupported file type or error during processing.", base_output_dir) # Pass base_output_dir

    derived_equipment_path = os.path.join(mekfiles_output_dir, "derivedEquipment.json")
    save_to_json(list(DERIVED_EQUIPMENT.values()), derived_equipment_path, base_output_dir) # Pass base_output_dir
    print(f"Derived equipment data saved to {derived_equipment_path}")

    return processed_count


# --- Main Execution ---
if __name__ == "__main__":
    # Fixed output directory name
    fixed_output_dir_name = "megameklab_converted_output"
    os.makedirs(fixed_output_dir_name, exist_ok=True)

    # The log file will now be created inside this fixed_output_dir_name
    # SKIPPED_FILES_LOG global will be used by log_skipped_file, which now takes output_dir_for_log

    # Clear log file at the start of a run, if it exists within the fixed output directory
    current_run_log_path = os.path.join(fixed_output_dir_name, SKIPPED_FILES_LOG)
    if os.path.exists(current_run_log_path):
        try: os.remove(current_run_log_path)
        except OSError: pass


    megameklab_data_dir = "megameklab/data/mekfiles"

    if not os.path.isdir(megameklab_data_dir):
        print(f"Error: Data directory not found: {megameklab_data_dir}")
    else:
        print(f"Starting conversion... Output will be in: {fixed_output_dir_name}")
        # Pass fixed_output_dir_name as the base for all outputs, including the log file
        processed_count = process_files(megameklab_data_dir, fixed_output_dir_name)
        print(f"Processing complete. {processed_count} files converted.")
        print(f"Total unique equipment items derived: {len(DERIVED_EQUIPMENT)}")

        skipped_log_path = os.path.join(fixed_output_dir_name, SKIPPED_FILES_LOG)
        if os.path.exists(skipped_log_path):
            with open(skipped_log_path, 'r', encoding='utf-8') as log_f:
                num_skipped = sum(1 for _ in log_f)
                if num_skipped > 0:
                    print(f"{num_skipped} files/items were skipped or had errors. See {skipped_log_path} for details.")
                else:
                    try: os.remove(skipped_log_path)
                    except OSError: pass
        else:
            print("No files were skipped.")

    # For reporting purposes, we need the name of the created directory
    print(f"Output directory name: {fixed_output_dir_name}")
