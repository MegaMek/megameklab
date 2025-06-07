import os
import json
import xml.etree.ElementTree as ET
import re
from pathlib import Path
import datetime
import multiprocessing

# --- Globals ---
DERIVED_EQUIPMENT = {}
SKIPPED_FILES_LOG = "skipped_files.log"

FUNDAMENTAL_IS_COMPONENTS = [ # Lowercase for matching
    "cockpit", "life support", "sensors", "gyro", "engine", "structure", "myomer",
    "heat sink", "jump jet", "shoulder", "upper arm actuator", "lower arm actuator", "hand actuator",
    "hip", "upper leg actuator", "lower leg actuator", "foot actuator"
]
# Specific location names for different unit types for BLK armor parsing
VEHICLE_ARMOR_LOCATIONS = ["Front", "Left Side", "Right Side", "Rear", "Turret"]
FIGHTER_ARMOR_LOCATIONS = ["Nose", "Left Wing", "Right Wing", "Aft"]
VTOL_ARMOR_LOCATIONS = ["Nose", "Left Side", "Right Side", "Rear", "Rotor"]

# Helper to derive a more specific type from an MTF item line
def get_mtf_item_type_from_line(item_name_line, unit_context):
    name_lower = item_name_line.lower().strip()
    # Standardize and clean name for type derivation, make it TitleCase
    # Handles "Medium Laser" -> "MediumLaser", "AC/5" -> "AC5"
    type_str = ''.join(word.title() for word in item_name_line.replace("(","").replace(")","").replace("-"," ").replace("/"," ").split())

    if not type_str: # Handle empty or only symbol names
        return "Unknown"

    if name_lower == "heat sink":
        hs_type = unit_context.get("heat_sink_type", "SingleHeatSink")
        return hs_type
    if name_lower == "fusion engine":
        engine_type = unit_context.get("engine_type", "FusionEngine")
        return engine_type
    if name_lower.startswith("is ammo") or name_lower.startswith("clan ammo"):
        return "Ammo"

    # Specific weapon types (prioritize keywords)
    if "ppc" in name_lower: return type_str
    if "laser" in name_lower: return type_str
    if "srm" in name_lower and "ammo" not in name_lower: return type_str
    if "lrm" in name_lower and "ammo" not in name_lower: return type_str
    if "machine gun" in name_lower: return "MachineGun"
    if "autocannon" in name_lower or name_lower.startswith("ac"):
        return type_str.replace("/", "")
    if "gauss rifle" in name_lower : return "GaussRifle"
    if "flamer" in name_lower and "ammo" not in name_lower: return type_str
    if "lb-x" in name_lower and "ammo" not in name_lower: return type_str.replace("LB-X", "LBX")
    if "ultra ac" in name_lower and "ammo" not in name_lower: return type_str.replace("UltraAC", "UAC")
    if "rotary ac" in name_lower and "ammo" not in name_lower: return type_str.replace("RotaryAC","RAC")
    if "mrm" in name_lower and "ammo" not in name_lower: return type_str
    if " Streak " in item_name_line and "ammo" not in name_lower: return type_str # Note: leading/trailing spaces for " Streak "

    # Fundamental components
    if name_lower == "gyro": return "Gyro"
    if name_lower == "sensors": return "Sensors"
    if name_lower == "cockpit": return "Cockpit"
    if name_lower == "life support": return "LifeSupport"
    if "actuator" in name_lower: return type_str
    if name_lower in ["shoulder", "hip"]: return type_str
    if name_lower == "structure": return "Structure"
    if name_lower == "myomer": return "Myomer"
    if "jump jet" in name_lower: return type_str

    return type_str if type_str else "Unknown"

# Helper to derive a more specific type from an BLK item name (from equipment tags)
def get_blk_item_type_from_name(item_name_str): # No unit_context for BLK for now
    name_lower = item_name_str.lower().strip()
    type_str = ''.join(word.title() for word in item_name_str.replace("(","").replace(")","").replace("-"," ").replace("/"," ").split())

    if not type_str:
        return "Unknown"

    # Specific weapon types (prioritize keywords) - similar to MTF helper
    if "ppc" in name_lower: return type_str
    if "laser" in name_lower: return type_str
    if "srm" in name_lower and "ammo" not in name_lower: return type_str
    if "lrm" in name_lower and "ammo" not in name_lower: return type_str
    if "machine gun" in name_lower: return "MachineGun"
    if "autocannon" in name_lower or name_lower.startswith("ac"):
        return type_str.replace("/", "")
    if "gauss rifle" in name_lower : return "GaussRifle"
    if "flamer" in name_lower and "ammo" not in name_lower: return type_str
    if "lb-x" in name_lower and "ammo" not in name_lower: return type_str.replace("LB-X", "LBX")
    if "ultra ac" in name_lower and "ammo" not in name_lower: return type_str.replace("UltraAC", "UAC")
    if "rotary ac" in name_lower and "ammo" not in name_lower: return type_str.replace("RotaryAC","RAC")
    if "mrm" in name_lower and "ammo" not in name_lower: return type_str
    if " Streak " in item_name_str and "ammo" not in name_lower: return type_str
    if name_lower.startswith("is ammo") or name_lower.startswith("clan ammo"): return "Ammo" # Ammo check

    # Fundamental components (less common in BLK equipment tags but possible)
    if name_lower == "heat sink": return "HeatSink" # Simpler than MTF, no context for single/double easily
    if name_lower == "fusion engine": return "FusionEngine"
    if name_lower == "gyro": return "Gyro"
    if name_lower == "sensors": return "Sensors"
    if name_lower == "cockpit": return "Cockpit"
    if name_lower == "life support": return "LifeSupport"
    if "actuator" in name_lower: return type_str
    if name_lower in ["shoulder", "hip"]: return type_str
    if name_lower == "structure": return "Structure"
    if name_lower == "myomer": return "Myomer"
    if "jump jet" in name_lower: return type_str
    if name_lower == "targeting computer": return "TargetingComputer"

    # Default for BLK items, often names are quite descriptive
    return type_str if type_str else "Unknown"


def log_skipped_file(filepath, reason, output_dir_for_log):
    log_path = os.path.join(output_dir_for_log, SKIPPED_FILES_LOG)
    os.makedirs(os.path.dirname(log_path), exist_ok=True)
    with open(log_path, "a", encoding='utf-8') as f:
        f.write(f"Skipped: {filepath} - Reason: {reason}\n")

def parse_mtf_engine(engine_str):
    match = re.match(r"(\d+)\s*(.*?)(?:\(([^)]+)\))?$", engine_str)
    if match:
        rating, type_name, manufacturer = match.groups()
        return {"rating": int(rating), "type": type_name.strip(), "manufacturer": manufacturer.strip() if manufacturer else None}
    return {"raw": engine_str, "rating":0, "type": "Unknown", "manufacturer": None}

def parse_mtf_heat_sinks(hs_str):
    match = re.match(r"(\d+)\s*(.*?)(?:\sHeat Sink)?$", hs_str)
    if match:
        count, type_name = match.groups()
        return {"count": int(count), "type": type_name.strip()}
    return {"raw": hs_str, "count": 0, "type": "Unknown"}

def parse_mtf_structure(structure_str):
    match = re.match(r"(.+?)(?:\(([^)]+)\))?$", structure_str)
    if match: type_name, manufacturer = match.groups(); return {"type": type_name.strip(), "manufacturer": manufacturer.strip() if manufacturer else None}
    return {"type": structure_str.strip(), "manufacturer": None}

def parse_mtf_armor_type_line(armor_str):
    match = re.match(r"(.+?)(?:\(([^)]+)\))?$", armor_str)
    if match: type_name, manufacturer = match.groups(); return {"type": type_name.strip(), "manufacturer": manufacturer.strip() if manufacturer else None}
    return {"type": armor_str.strip(), "manufacturer": None}

def parse_mtf_myomer(myomer_str):
    match = re.match(r"(.+?)(?:\(([^)]+)\))?$", myomer_str)
    if match: type_name, manufacturer = match.groups(); return {"type": type_name.strip(), "manufacturer": manufacturer.strip() if manufacturer else None}
    return {"type": myomer_str.strip(), "manufacturer": None}

def get_year_from_path(filepath_str):
    filepath = Path(filepath_str)
    year_pattern = re.compile(r"(\d{4})[Uu]?")
    path_str_lower = str(filepath).lower()

    known_tro_years = {
        "3025": 3025, "3039": 3039, "3050": 3050, "3055": 3055, "3057": 3057, "3058": 3058,
        "3060": 3060, "3067": 3067, "3075": 3075, "3085": 3085, "3145": 3145, "3150": 3150,
        "xtro primitivestest": 2400, "xtro primitives i": 2400, "xtro primitives ii": 2400,
        "xtro primitives iii": 2400, "xtro primitives iv": 2400, "xtro primitives v": 2400,
        "xtro clans": 2850, "xtro succession wars": 2900, "xtro boondoggles": 3060,
        "xtro corporations": 3060, "xtro davion": 3060, "xtro kurita": 3060,
        "xtro liaosuns": 3060, "xtro marik": 3060, "xtro mercs": 3060, "xtro steiner": 3060,
        "xtro pirates": 3060, "xtro royal society": 3060, "xtro ilclan": 3150,
        "prototypes": 3070, "project phoenix": 3070, "golden century": 2800,
        "recognition guide": 3140, "rg": 3140
    }
    for key, year_val in known_tro_years.items():
        if key in path_str_lower: return year_val

    for part in reversed(filepath.parts):
        match = year_pattern.search(part)
        if match: return int(match.group(1))
    return 2750


def add_to_derived_equipment(item_name, item_type="Unknown", unit_tech_base_for_context="Unknown",
                             introduction_year="Unknown", source_file="N/A"):
    if not item_name or item_name == "-Empty-": return
    clean_item_name = item_name.strip()
    internal_id_base = re.sub(r'[^a-zA-Z0-9]', '', clean_item_name.upper())
    # internal_id should be based SOLELY on the item name for consistent merging.
    # Type will be refined later.
    internal_id = internal_id_base
    # internal_id = item_type.upper() + "_" + internal_id_base if item_type != "Unknown" else internal_id_base # Old logic
    internal_id = internal_id.replace(" ","") # Should not be necessary if internal_id_base is already alphanumeric

    item_specific_tech_base = "Unknown"; name_upper = clean_item_name.upper()
    # Check for explicit Clan/IS markers, including suffixes like " C"
    if "(CL)" in name_upper or "CLAN" in name_upper or name_upper.endswith(" C") or name_upper.startswith("C "): item_specific_tech_base = "Clan"
    elif "(IS)" in name_upper or "INNER SPHERE" in name_upper: item_specific_tech_base = "Inner Sphere"

    final_tech_base = item_specific_tech_base

    is_fundamental = any(fundamental.lower() == clean_item_name.lower().split('(')[0].strip() for fundamental in FUNDAMENTAL_IS_COMPONENTS)

    if final_tech_base == "Unknown": # If item itself doesn't specify tech base
        if is_fundamental and unit_tech_base_for_context == "Inner Sphere":
            final_tech_base = "Inner Sphere" # Fundamental components on IS unit are IS
        else:
            final_tech_base = unit_tech_base_for_context # Otherwise, inherit from unit
    elif is_fundamental and final_tech_base == "Clan" and unit_tech_base_for_context == "Inner Sphere":
        # This is a Clan fundamental component on an IS unit. This is rare and implies mixed tech.
        # For simplicity, let's assume the component dictates its tech base here.
        pass # Keep final_tech_base as Clan

    current_intro_year = "Unknown"
    if isinstance(introduction_year, int): current_intro_year = introduction_year
    elif isinstance(introduction_year, str) and introduction_year.isdigit(): current_intro_year = int(introduction_year)
    elif isinstance(introduction_year, str): # Keep as string if not purely digits
        current_intro_year = introduction_year.strip() if introduction_year.strip() else "Unknown"


    if internal_id not in DERIVED_EQUIPMENT:
        DERIVED_EQUIPMENT[internal_id] = {
            "name": clean_item_name, "internal_id": internal_id, "type": item_type,
            "category": "Unknown", "tech_base": final_tech_base, "rules_level": "Standard",
            "introduction_year": current_intro_year, "extinction_year": "Unknown",
            "faction_availability": [], "technology_dependencies": [], "critical_slots": 0,
            "tonnage": 0, "cost_cbills": None, "battle_value": None,
            "source_book": None, "source_files": [source_file]
        }
    else:
        entry = DERIVED_EQUIPMENT[internal_id]
        if source_file not in entry["source_files"]: entry["source_files"].append(source_file)

        # Type refinement: Update if new type is more specific
        current_type_is_generic = entry["type"].lower() in ["unknown", "equipment", "weapon", "ammo", "component"]
        new_type_is_specific = item_type.lower() not in ["unknown", "equipment", "weapon", "ammo", "component"]

        if current_type_is_generic and new_type_is_specific:
            entry["type"] = item_type
        elif entry["type"].lower() == "unknown" and item_type.lower() != "unknown": # General fallback if old type was unknown
            entry["type"] = item_type


        # Update tech_base only if new info is more specific or clarifies "Mixed"
        if entry["tech_base"] == "Unknown" and final_tech_base != "Unknown":
            entry["tech_base"] = final_tech_base
        elif entry["tech_base"] != final_tech_base and final_tech_base != "Unknown" and entry["tech_base"] != "Mixed":
            # If conflicting specific tech bases (e.g. IS vs Clan), mark as Mixed
            # unless it's a fundamental IS component that should remain IS.
            is_entry_fundamental_is = any(f.lower() == entry["name"].lower().split('(')[0].strip() for f in FUNDAMENTAL_IS_COMPONENTS) and entry["tech_base"] == "Inner Sphere"
            if not is_entry_fundamental_is:
                entry["tech_base"] = "Mixed"

        # Prefer earliest known introduction year
        if current_intro_year != "Unknown":
            if entry["introduction_year"] == "Unknown" or \
               (isinstance(current_intro_year, int) and isinstance(entry["introduction_year"], int) and current_intro_year < entry["introduction_year"]) or \
               (isinstance(current_intro_year, int) and isinstance(entry["introduction_year"], str)): # If current is int and entry is string (like "Unknown")
                 entry["introduction_year"] = current_intro_year

def parse_mtf_file(filepath, output_dir_for_log):
    derived_equipment_accumulator = [] # To store (item_name, item_type, unit_tech_base, intro_year, source_file)
    data = {"quirks": [], "weapons_and_equipment": []}; fluff_text = {} # Initialize lists
    unit_context_for_typing = {} # To store info like heatsink type, engine type
    current_section_name = None; current_section_items = []
    fluff_fields = ["overview", "capabilities", "deployment", "history", "variants", "notable_pilots", "additional", "notes"]
    current_fluff_field = None; fluff_text_buffer = []
    base_filename = os.path.basename(filepath)

    file_era = get_year_from_path(filepath); data["era"] = file_era
    file_tech_base = "Inner Sphere"
    if "clan" in filepath.lower(): file_tech_base = "Clan"
    data["tech_base"] = file_tech_base # Set default, can be overridden

    non_mech_armor_keys = {
        "front armor": "Front", "left armor": "Left Side", "right armor": "Right Side", "rear armor": "Rear",
        "nose armor": "Nose", "left wing armor": "Left Wing", "right wing armor": "Right Wing", "aft armor": "Aft",
        "turret armor": "Turret", "rotor armor": "Rotor"
    }
    parsed_armor_locations = []
    line_number = 0

    try:
        with open(filepath, 'r', encoding='latin-1', errors='ignore') as f: lines = f.readlines()

        for line_content in lines:
            line_strip = line_content.strip()
            if ":" in line_strip:
                key, value = line_strip.split(":", 1); key_norm = key.strip().lower().replace(" ", "_"); value_clean = value.strip()
                if key_norm == "techbase": file_tech_base = value_clean; data["tech_base"] = file_tech_base
                elif key_norm == "era":
                    try: data["era"] = int(value_clean); file_era = data["era"]
                    except ValueError: data["era"] = value_clean; file_era = value_clean

        weapon_section_started = False
        for ln_idx, line_content in enumerate(lines):
            line_number = ln_idx + 1; line = line_content.strip()
            if not line:
                if current_fluff_field and fluff_text_buffer: fluff_text[current_fluff_field] = "\n".join(fluff_text_buffer).strip(); fluff_text_buffer = []; current_fluff_field = None
                weapon_section_started = False; continue

            is_fluff_header = False
            for field_name in fluff_fields:
                if line.lower().startswith(field_name + ":"):
                    if current_fluff_field and fluff_text_buffer: fluff_text[current_fluff_field] = "\n".join(fluff_text_buffer).strip()
                    current_fluff_field = field_name; fluff_text_buffer = [line[len(field_name)+1:].strip()]; is_fluff_header = True
                    if current_section_name: data.setdefault("criticals", []).append({"location": current_section_name, "slots": current_section_items}); current_section_name = None; current_section_items = []
                    weapon_section_started = False; break
            if is_fluff_header: continue
            if current_fluff_field: fluff_text_buffer.append(line); continue

            if line.lower().startswith("weapons:"): weapon_section_started = True; current_section_name = None; current_section_items = []; continue # Clear critical section context
            if weapon_section_started:
                item_name_on_weapon_line = line.split(',')[0].strip()
                location = line.split(',')[1].strip() if len(line.split(',')) > 1 else "Unknown"
                if item_name_on_weapon_line:
                    # Attempt to get a specific type from the name using the helper
                    derived_type_for_weapon = get_mtf_item_type_from_line(item_name_on_weapon_line, unit_context_for_typing)

                    # Determine the final type to use.
                    # If the derived type is "Unknown" or just a direct TitleCase of the name (which means no specific rule in helper matched well for a weapon context)
                    # AND it doesn't contain common weapon keywords, then classify it more generically as "Weapon".
                    # This handles cases where non-weapon items might appear in the weapons list.
                    is_generic_or_unclear_weapon_type = derived_type_for_weapon == "Unknown" or \
                                                       derived_type_for_weapon == ''.join(word.title() for word in item_name_on_weapon_line.replace("(","").replace(")","").replace("-"," ").replace("/"," ").split())

                    if is_generic_or_unclear_weapon_type and \
                       not any(kw in item_name_on_weapon_line.lower() for kw in ["laser","ppc","srm","lrm","ac","autocannon","gauss","flamer","lb-x","ultra","rotary"," streak ","gun","machine gun"]):
                        final_weapon_type = "Weapon"
                    else: # It's likely a weapon, or the helper found a good specific type.
                        final_weapon_type = derived_type_for_weapon

                    data["weapons_and_equipment"].append({"item_name": item_name_on_weapon_line, "location": location, "item_type": final_weapon_type})
                    derived_equipment_accumulator.append((item_name_on_weapon_line, final_weapon_type, file_tech_base, file_era, base_filename))
                continue

            if line.endswith(':') and not any(f_key in line.lower() for f_key in [" ammo:", "armor:", "manufacturer:", "primaryfactory:", "systemmanufacturer:"]) and not re.match(r"^\w+\s\w+:", line): # Potential critical section header
                if current_section_name and current_section_items:
                    data.setdefault("criticals", []).append({"location": current_section_name, "slots": current_section_items})

                temp_section_name = line[:-1]
                # Standardize critical hit locations
                if temp_section_name == "Rear Right Leg": temp_section_name = "Right Leg"
                elif temp_section_name == "Rear Left Leg": temp_section_name = "Left Leg"
                elif temp_section_name == "Rear Center Torso" or temp_section_name == "RTC": temp_section_name = "Center Torso (Rear)"
                # Add other critical location standardizations here if needed

                current_section_name = temp_section_name
                current_section_items = []
                continue

            if ":" in line:
                key, value = line.split(":", 1); key_norm = key.strip().lower().replace(" ", "_").replace("(", "").replace(")", ""); value_stripped = value.strip()

                if key_norm in non_mech_armor_keys:
                    try: parsed_armor_locations.append({"location": non_mech_armor_keys[key_norm], "armor_points": int(value_stripped)})
                    except ValueError: log_skipped_file(filepath, f"Non-integer armor value '{value_stripped}' for {key_norm}", output_dir_for_log)
                    continue

                if key_norm == "mass":
                    try: data["mass"] = int(value_stripped)
                    except ValueError: data["mass"] = value_stripped
                elif key_norm == "engine":
                    parsed_engine = parse_mtf_engine(value_stripped)
                    data["engine"] = parsed_engine
                    # Clean and store engine type, e.g. "FusionEngine", "XLEngine"
                    cleaned_engine_type = ''.join(word.title() for word in parsed_engine.get("type", "FusionEngine").replace("(IS)","").replace("(Clan)","").split())
                    if cleaned_engine_type and "engine" not in cleaned_engine_type.lower(): # e.g. XL -> XLEngine
                        cleaned_engine_type = f"{cleaned_engine_type}Engine"
                    elif not cleaned_engine_type:
                        cleaned_engine_type = "FusionEngine" # Default if type was empty
                    unit_context_for_typing["engine_type"] = cleaned_engine_type

                elif key_norm == "heat_sinks":
                    parsed_hs = parse_mtf_heat_sinks(value_stripped)
                    data["heat_sinks"] = parsed_hs
                    # Clean and store hs type, e.g. "SingleHeatSink", "DoubleHeatSink"
                    cleaned_hs_type = ''.join(word.title() for word in parsed_hs.get("type", "Single").split())
                    if cleaned_hs_type and "heatsink" not in cleaned_hs_type.lower(): # e.g. Single -> SingleHeatSink
                         cleaned_hs_type = f"{cleaned_hs_type}HeatSink"
                    elif not cleaned_hs_type:
                        cleaned_hs_type = "SingleHeatSink" # Default if type was empty
                    unit_context_for_typing["heat_sink_type"] = cleaned_hs_type
                elif key_norm == "armor" and not current_section_name: data["armor_details_temp"] = parse_mtf_armor_type_line(value_stripped)
                elif key_norm.endswith("_armor"):
                    loc_name = key_norm.upper().replace("_ARMOR",""); data.setdefault("armor_locations_map", {})[loc_name] = value_stripped
                elif key_norm == "quirk": data["quirks"].append(value_stripped)
                elif key_norm == "structure": data["structure"] = parse_mtf_structure(value_stripped)
                elif key_norm == "myomer": data["myomer"] = parse_mtf_myomer(value_stripped)
                # Manufacturer and PrimaryFactory processing
                elif key_norm == "manufacturer":
                    current_manufacturers = data.setdefault("manufacturers", [])
                    for man_name in value_stripped.split(','): # Handle comma-separated names
                        man_name_stripped = man_name.strip()
                        if man_name_stripped:
                            current_manufacturers.append({"name": man_name_stripped})
                elif key_norm == "primaryfactory":
                    current_manufacturers = data.setdefault("manufacturers", []) # Consolidate into manufacturers
                    for fac_name in value_stripped.split(','): # Handle comma-separated names
                        fac_name_stripped = fac_name.strip()
                        if fac_name_stripped:
                            current_manufacturers.append({"name": fac_name_stripped}) # Could add type: "primary" here if needed later
                elif key_norm not in ["engine", "heat_sinks", "structure", "myomer", "techbase", "era", "weapons", "armor", "conversion_notes", "mass", "systemmanufacturer", "mul_id"] : data[key_norm] = value_stripped
                # Removed "manufacturer", "primaryfactory" from the list above as they are now handled
                elif key_norm == "systemmanufacturer":
                    sys_type, sys_name = value_stripped.split(":", 1) if ":" in value_stripped else (key, value_stripped)
                    data.setdefault("system_manufacturers", []).append({"type": sys_type.strip(), "name": sys_name.strip()})
                elif key_norm == "mul_id": data["mul_id"] = value_stripped

            elif current_section_name:
                # Fix for model name appearing as critical slot item
                unit_model_name = data.get('model', "") # Get the model name, default to empty string if not found
                if line.strip() == unit_model_name:
                    pass # Skip adding model name as a critical item
                elif line.strip() == "-Empty-":
                    current_section_items.append("-Empty-")
                else: # This is a critical slot item
                    current_section_items.append(line)
                    item_type_for_crit = get_mtf_item_type_from_line(line, unit_context_for_typing)
                    derived_equipment_accumulator.append((line, item_type_for_crit, file_tech_base, file_era, base_filename))
            elif ":" not in line and current_section_name and line.strip() == "-Empty-": # Handle empty slots explicitly also here
                 current_section_items.append("-Empty-")


        if current_section_name and current_section_items: # Add the last section
            data.setdefault("criticals", []).append({"location": current_section_name, "slots": current_section_items})
        if current_fluff_field and fluff_text_buffer: fluff_text[current_fluff_field] = "\n".join(fluff_text_buffer).strip()
        if fluff_text: data["fluff_text"] = fluff_text

        armor_base_info = data.pop("armor_details_temp", parse_mtf_armor_type_line(data.get("armor", "Standard Armor")))
        final_armor_obj = {"type": armor_base_info["type"], "manufacturer": armor_base_info["manufacturer"], "locations": []}

        if "armor_locations_map" in data: # Mech armor
            total_armor = 0
            armor_map = data.pop("armor_locations_map")
            for loc_key, points_str in armor_map.items():
                try: points = int(points_str); total_armor += points
                except ValueError: points = 0

                # Standardize armor locations from map keys
                if loc_key == "RTC": display_loc = "Center Torso (Rear)"
                elif loc_key == "RTR": display_loc = "Right Torso (Rear)"
                elif loc_key == "RTL": display_loc = "Left Torso (Rear)"
                else: display_loc = loc_key # Keep original if no mapping needed

                final_armor_obj["locations"].append({"location": display_loc, "armor_points": points, "rear_armor_points": None})
            if total_armor > 0: final_armor_obj["total_armor_points"] = total_armor
        elif parsed_armor_locations: # Non-mech MTF armor
            final_armor_obj["locations"] = parsed_armor_locations
            final_armor_obj["total_armor_points"] = sum(loc.get("armor_points", 0) for loc in parsed_armor_locations if isinstance(loc.get("armor_points"), int))
        data["armor"] = final_armor_obj
        if not data.get("quirks"): data["quirks"] = [] # Ensure quirks list exists

    except Exception as e:
        log_skipped_file(filepath, f"MTF Error: {e} (line {line_number})", output_dir_for_log); return None, []
    return data, derived_equipment_accumulator

def parse_blk_tag_content(content_str, key_hint=""):
    items = [item.strip() for item in content_str.strip().split('\n') if item.strip()]
    if not items: return None

    if key_hint in ["model", "source", "name", "chassis", "variant"] or "manufacturer" in key_hint: # Ensure these are strings
        return " ".join(items)

    processed_items = []
    for item in items:
        if item.lower() == "true": processed_items.append(True)
        elif item.lower() == "false": processed_items.append(False)
        # Avoid converting MUL IDs or version strings like "2.0" to int/float unless contextually appropriate
        elif re.fullmatch(r"-?\d+", item) and key_hint not in ["id", "mulid", "version", "source", "model"]:
             processed_items.append(int(item))
        elif re.fullmatch(r"-?\d+\.\d+", item) and key_hint not in ["version", "source", "model"]:
            try: processed_items.append(float(item))
            except ValueError: processed_items.append(item)
        else: processed_items.append(item)
    return processed_items[0] if len(processed_items) == 1 else processed_items


def parse_blk_file(filepath, output_dir_for_log):
    derived_equipment_accumulator = [] # To store (item_name, item_type, unit_tech_base, intro_year, source_file)
    content = ""; data = {}; base_filename = os.path.basename(filepath)
    unit_type_from_file = "unknown"
    path_lower = filepath.lower()
    if "vehicle" in path_lower: unit_type_from_file = "vehicle"
    elif "fighter" in path_lower and "convfighter" not in path_lower : unit_type_from_file = "fighter"
    elif "convfighter" in path_lower: unit_type_from_file = "conventionalfighter"
    elif "dropship" in path_lower: unit_type_from_file = "dropship"
    elif "smallcraft" in path_lower: unit_type_from_file = "smallcraft"
    elif "warship" in path_lower: unit_type_from_file = "warship"
    elif "battlearmor" in path_lower: unit_type_from_file = "battlearmor"
    elif "protomek" in path_lower: unit_type_from_file = "protomek"
    elif "infantry" in path_lower: unit_type_from_file = "infantry"
    elif "gunemplacement" in path_lower or "/ge/" in path_lower : unit_type_from_file = "gunemplacement"
    elif "battlemech" in path_lower or "/mek/" in path_lower: unit_type_from_file = "battlemech" # Default if path contains /mek/


    file_era = get_year_from_path(filepath)
    file_tech_base = "Inner Sphere"
    if "clan" in path_lower: file_tech_base = "Clan"

    try:
        with open(filepath, 'r', encoding='latin-1', errors='ignore') as f: content = f.read()
        content = re.sub(r"<!--.*?-->", "", content, flags=re.DOTALL)
        content = re.sub(r"#ifs?.*?#endifs?", "", content, flags=re.DOTALL | re.IGNORECASE)
        content = re.sub(r"#.*?(\r?\n|$)", "", content) # More careful comment removal

        type_tag_match = re.search(r"<type>([^<]+)</type>", content, flags=re.IGNORECASE)
        if type_tag_match:
            type_val_str = type_tag_match.group(1).strip().lower()
            # More specific type inference from <type> tag
            if "vehicle" in type_val_str: unit_type_from_file = "vehicle"
            elif "battlemech" in type_val_str or " biped" in type_val_str : unit_type_from_file = "battlemech"
            elif "fighter" in type_val_str and "conventional" not in type_val_str: unit_type_from_file = "fighter"
            elif "conventionalfighter" in type_val_str: unit_type_from_file = "conventionalfighter"
            elif "battlearmor" in type_val_str: unit_type_from_file = "battlearmor"
            elif "protomek" in type_val_str: unit_type_from_file = "protomek"
            elif "infantry" in type_val_str: unit_type_from_file = "infantry"
            elif "dropship" in type_val_str: unit_type_from_file = "dropship"
            elif "smallcraft" in type_val_str: unit_type_from_file = "smallcraft"
            elif "warship" in type_val_str: unit_type_from_file = "warship"
            # Tech base from type tag
            if "clan" in type_val_str: file_tech_base = "Clan"
            elif "inner sphere" in type_val_str or "is " in type_val_str : file_tech_base = "Inner Sphere"
            elif "mixed" in type_val_str: file_tech_base = "Mixed"

        year_tag_match = re.search(r"<year>([^<]+)</year>", content, flags=re.IGNORECASE)
        if year_tag_match:
            year_val = year_tag_match.group(1).strip()
            try: file_era = int(year_val)
            except ValueError: file_era = year_val
        else:
            orig_year_match = re.search(r"<originalBuildYear>([^<]+)</originalBuildYear>", content, flags=re.IGNORECASE)
            if orig_year_match:
                year_val = orig_year_match.group(1).strip()
                try: file_era = int(year_val)
                except ValueError: file_era = year_val

        pattern = re.compile(r"<([a-zA-Z0-9_ :.-]+?)>([\s\S]*?)</\s*\s*>", flags=re.IGNORECASE) # Allow . and - in tag names
        for match in pattern.finditer(content):
            tag, value_str = match.groups()
            key = tag.strip().lower().replace(" ", "_").replace(":", "").replace(".","").replace("-","") # Normalize key
            parsed_value = parse_blk_tag_content(value_str, key)

            if key == "mass" and isinstance(parsed_value, str):
                try: parsed_value = int(parsed_value)
                except ValueError:
                    try: parsed_value = float(parsed_value)
                    except ValueError: pass

            if key == "grav_decks":
                if isinstance(parsed_value, list) and len(parsed_value) > 0:
                    try: data[key] = int(parsed_value[0])
                    except (ValueError, TypeError): data[key] = 0
                elif isinstance(parsed_value, (int, float)): data[key] = int(parsed_value)
                else: data[key] = 0
                continue

            if key == "armor":
                armor_type_str = data.get("armortype", "Standard Armor")
                armor_obj = {"type": armor_type_str, "manufacturer": None, "locations": [], "total_armor_points": 0}

                location_map = []
                if "vehicle" in unit_type_from_file: location_map = VEHICLE_ARMOR_LOCATIONS
                elif "fighter" in unit_type_from_file or "smallcraft" in unit_type_from_file or "dropship" in unit_type_from_file or "conventionalfighter" in unit_type_from_file: location_map = FIGHTER_ARMOR_LOCATIONS
                elif "vtol" in unit_type_from_file: location_map = VTOL_ARMOR_LOCATIONS

                raw_armor_values = []
                if isinstance(parsed_value, list): raw_armor_values = parsed_value
                elif parsed_value is not None: raw_armor_values = [parsed_value]

                total_armor = 0
                if "battlearmor" in unit_type_from_file and len(raw_armor_values) == 1:
                     try: armor_obj["armor_points_per_trooper"] = int(raw_armor_values[0]); total_armor = int(raw_armor_values[0])
                     except (ValueError, TypeError): pass
                elif location_map and raw_armor_values: # Ensure there's a map and values
                    for i, val_str in enumerate(raw_armor_values):
                        if i < len(location_map):
                            try: points = int(val_str); total_armor += points
                            except (ValueError, TypeError): points = 0
                            armor_obj["locations"].append({"location": location_map[i], "armor_points": points})
                        else:
                            try: points = int(val_str); total_armor += points
                            except (ValueError, TypeError): points = 0
                            armor_obj["locations"].append({"location": f"Extra_{i}", "armor_points": points})
                elif raw_armor_values: # No map, but values exist (e.g. single overall value)
                    try: points = int(raw_armor_values[0]); total_armor = points
                    except (ValueError,TypeError): points = 0
                    armor_obj["locations"].append({"location": "Overall", "armor_points": points})


                if total_armor > 0 : armor_obj["total_armor_points"] = total_armor
                data[key] = armor_obj
                if "armortype" in data and key == "armor": data.pop("armortype")
                continue

            if key in data:
                if not isinstance(data[key], list): data[key] = [data[key]]
                if isinstance(parsed_value, list): data[key].extend(parsed_value)
                else: data[key].append(parsed_value)
            else: data[key] = parsed_value

        # Process manufacturers ensuring it's a list of objects
        manufacturer_keys = ["manufacturer", "primaryfactory", "factory"] # Add other relevant keys if any
        final_manufacturers_list = []
        for m_key in manufacturer_keys:
            if m_key in data:
                m_values = data.pop(m_key) # Remove original string/list
                if not isinstance(m_values, list):
                    m_values = [m_values]
                for val in m_values:
                    if isinstance(val, str):
                         # Handle comma-separated values within a single manufacturer string
                        for s_val in val.split(','):
                            s_val_stripped = s_val.strip()
                            if s_val_stripped:
                                final_manufacturers_list.append({"name": s_val_stripped})
                    # If it's already an object (though current parsing makes it string), it could be handled here
        if final_manufacturers_list:
            # Ensure no duplicates if multiple keys contribute the same manufacturer
            unique_manufacturers = []
            seen_names = set()
            for man_obj in final_manufacturers_list:
                if man_obj["name"] not in seen_names:
                    unique_manufacturers.append(man_obj)
                    seen_names.add(man_obj["name"])
            data["manufacturers"] = unique_manufacturers

        # Process 'config' field (improved logic)
        if "config" in data:
            parsed_value = data["config"]
            if isinstance(parsed_value, str):
                config_val_lower = parsed_value.lower()
                if "biped" in config_val_lower: data["config"] = "Biped"
                elif "quad" in config_val_lower: data["config"] = "Quad"
                elif "tripod" in config_val_lower: data["config"] = "Tripod"
                else: data["config"] = None
            elif isinstance(parsed_value, list): # If it was parsed as a list of strings
                # Try to find a mappable config in the list, prioritizing Biped > Quad > Tripod
                found_config = None
                for item_str in parsed_value:
                    if isinstance(item_str, str):
                        item_lower = item_str.lower()
                        if "biped" in item_lower: found_config = "Biped"; break
                        if "quad" in item_lower and not found_config: found_config = "Quad"
                        if "tripod" in item_lower and not found_config: found_config = "Tripod"
                data["config"] = found_config # Will be None if no keywords found
            else: # If it's neither string nor list of strings that we can interpret
                data["config"] = None
        # If "config" was not in data at all, it remains absent, implying null in schema



        data['era'] = file_era
        data['tech_base'] = file_tech_base
        if 'type' not in data or data.get('type') == 'unknown' : data['type'] = unit_type_from_file # Ensure type is set

        equipment_tags = ["equipment", "body_equipment", "right_arm_equipment", "left_arm_equipment", "right_leg_equipment", "left_leg_equipment", "head_equipment", "center_torso_equipment", "right_torso_equipment", "left_torso_equipment", "nose_equipment", "left_wing_equipment", "right_wing_equipment", "aft_equipment"]
        all_items_for_derived = []
        unit_model_name_for_blk = data.get('model', "") # Get model name for checking

        for tag_name in equipment_tags:
            if tag_name in data:
                # It's important that data[tag_name] isn't modified if it's needed later for specific output structures
                # For derived_equipment, we just extract names.
                items_source = data[tag_name]
                if not isinstance(items_source, list): items_source = [items_source]

                for item_entry in items_source:
                    item_name_candidate = None
                    if isinstance(item_entry, str) and item_entry.lower() != "empty":
                        item_name_candidate = item_entry.split('(')[0].strip()
                    elif isinstance(item_entry, dict) and 'name' in item_entry and item_entry['name'].lower() != 'empty':
                        item_name_candidate = item_entry['name'].split('(')[0].strip()

                    if item_name_candidate:
                        # Fix for model name appearing as equipment
                        if unit_model_name_for_blk and item_name_candidate == unit_model_name_for_blk:
                            pass # Skip adding model name as equipment
                        else:
                            all_items_for_derived.append(item_name_candidate)

        for item_name_on_unit in set(all_items_for_derived): # Use set to get unique names
            item_type_for_blk = get_blk_item_type_from_name(item_name_on_unit)
            derived_equipment_accumulator.append((item_name_on_unit, item_type_for_blk, file_tech_base, file_era, base_filename))

        # Review point 5: weapons_and_equipment item_type in parse_blk_file
        # Current logic in parse_blk_file does not directly populate a structured 'weapons_and_equipment' list in 'data'.
        # It extracts equipment names into flat lists under keys like 'body_equipment', 'right_arm_equipment', etc.
        # These names are then added to 'derived_equipment_accumulator' with type "Unknown".
        # This is acceptable as per the subtask instructions. No change needed here for schema compliance of item_type
        # unless the script was also building a data["weapons_and_equipment"] list for the JSON output here.

        if "structure" in data and isinstance(data["structure"], str): data["structure"] = {"type": data["structure"], "manufacturer": None}
        if "myomer" in data and isinstance(data["myomer"], str): data["myomer"] = {"type": data["myomer"].strip(), "manufacturer": None}
        if "engine" in data and isinstance(data["engine"], str):
            engine_type_str = data["engine"]; rating_match = re.search(r'(\d+)', engine_type_str)
            rating = int(rating_match.group(1)) if rating_match else 0
            engine_type = engine_type_str.replace(str(rating), "").strip() if rating else engine_type_str
            data["engine"] = {"type": engine_type, "rating": rating, "manufacturer": None}
        if "heat_sinks" in data:
            hs_val = data["heat_sinks"]
            if isinstance(hs_val, str):
                hs_str = hs_val; count_match = re.search(r'(\d+)', hs_str)
                count = int(count_match.group(1)) if count_match else 0
                hs_type = hs_str.replace(str(count), "").strip() if count else hs_str
                data["heat_sinks"] = {"count": count, "type": hs_type}
            elif isinstance(hs_val, int): data["heat_sinks"] = {"count": hs_val, "type": "Unknown"}

        data.setdefault("quirks", [])
        if isinstance(data["quirks"], str): data["quirks"] = [data["quirks"]]

        if "model" in data and isinstance(data["model"], list) and len(data["model"]) == 1: data["model"] = data["model"][0]
        elif "model" in data and isinstance(data["model"], list) and not data["model"]: data["model"] = None


    except Exception as e:
        log_skipped_file(filepath, f"BLK Parsing Error: {e}", output_dir_for_log); return None, []
    return data, derived_equipment_accumulator

def parse_xml_file(filepath, output_dir_for_log):
    derived_equipment_accumulator = []
    data_for_json = {} # This will store the direct conversion for UnitVerifierOptions.json
    source_file_basename = os.path.basename(filepath)

    # Define categories that might contain equipment-like items
    # These are typically plural forms of the item tags.
    equipment_categories_tags = [
        "ammos", "battlearmorequips", "battlearmorweapons", "bombs",
        "engines", "equipments", "gyros", "heatsinks", "jumpjets",
        "physicalweapons", "structures", "targetingcomputers", "weapons"
    ]

    try:
        tree = ET.parse(filepath)
        root = tree.getroot()
        data_for_json[root.tag] = {} # Initialize for the main XML structure conversion

        for category_element in root: # Iterates over <ammos>, <weapons>, etc.
            category_tag_name = category_element.tag
            data_for_json[root.tag][category_tag_name] = {} # For direct JSON conversion

            # This part handles the direct conversion for UnitVerifierOptions.json
            # Needs to be adapted if category_element contains non-item children or just text
            if not list(category_element): # If it's a simple tag like <foo>text</foo>
                 data_for_json[root.tag][category_tag_name] = category_element.text.strip() if category_element.text else ""

            for item_element in category_element: # Iterates over <ammo>, <weapon> in <ammos>, <weapons>
                item_tag_name = item_element.tag # e.g. "ammo", "weapon"
                entity_data = {} # For direct JSON conversion of this item

                item_name_text = "Unknown Name"
                tech_base = "Unknown"
                intro_year_val = "Unknown"
                item_type_val = item_tag_name # Default type is the tag name itself

                for option_elem in item_element: # Iterates over <name>, <type>, <techlevel>, etc.
                    option_tag = option_elem.tag
                    text_content = option_elem.text.strip() if option_elem.text else ""

                    # Store for direct JSON conversion
                    if option_tag == "ceilWeight": # Example of complex tag handling
                        entity_data[option_tag] = {cw_elem.tag: cw_elem.text.strip() for cw_elem in option_elem}
                    elif option_tag == "ignoreFailedEquipment":
                         entity_data[option_tag] = [e.strip() for e in text_content.split(',')] if text_content else []
                    else: # Simple text content
                        if text_content.lower() == "true": entity_data[option_tag] = True
                        elif text_content.lower() == "false": entity_data[option_tag] = False
                        elif re.fullmatch(r"-?\d+", text_content): entity_data[option_tag] = int(text_content)
                        elif re.fullmatch(r"-?\d+\.\d+", text_content):
                            try: entity_data[option_tag] = float(text_content)
                            except ValueError: entity_data[option_tag] = text_content
                        else: entity_data[option_tag] = text_content

                    # Extract data for derived_equipment
                    if option_tag == "name":
                        item_name_text = text_content
                    elif option_tag == "techlevel":
                        if text_content == "0": tech_base = "Inner Sphere"
                        elif text_content == "1": tech_base = "Clan"
                        elif text_content == "2": tech_base = "Mixed"
                        else: tech_base = text_content # Keep as is if not 0,1,2
                    elif option_tag == "introyear":
                        intro_year_val = int(text_content) if text_content.isdigit() else text_content
                    elif option_tag == "type" and item_tag_name.lower() in ["weapon", "equipment", "ammo"]:
                        # Use inner <type> for more specific typing
                        if text_content: # Ensure it's not empty
                           item_type_val = text_content.replace(" ", "") # e.g. "ERLargeLaser"

                # Add to data_for_json (direct conversion)
                # This part might need adjustment based on how item_elements are structured
                # Assuming item_name_text is unique enough to be a key
                if item_name_text != "Unknown Name" and item_name_text :
                    data_for_json[root.tag][category_tag_name][item_name_text] = entity_data
                else: # Fallback if name is not suitable as key
                    data_for_json[root.tag][category_tag_name].setdefault(item_tag_name, []).append(entity_data)

                # Add to derived_equipment_accumulator if it's an equipment category
                if category_tag_name in equipment_categories_tags:
                    if item_name_text and item_name_text != "Unknown Name" and item_name_text != "-":
                        # Sanitize item_type_val for use in add_to_derived_equipment
                        # e.g. "Flamer (Vehicle)" -> "FlamerVehicle"
                        item_type_val_clean = re.sub(r'[^a-zA-Z0-9]', '', item_type_val.title())

                        derived_equipment_accumulator.append((
                            item_name_text,
                            item_type_val_clean, # Use the determined specific type
                            tech_base,
                            intro_year_val,
                            source_file_basename
                        ))
        return data_for_json, derived_equipment_accumulator
    except Exception as e:
        log_skipped_file(filepath, f"XML Processing Error: {e}", output_dir_for_log)
        return None, []

# Worker function for multiprocessing
def _process_file_worker(args_tuple):
    filepath, base_output_dir, mekfiles_output_dir, root_dir_for_relative_path = args_tuple

    processed_count = 0
    derived_equipment_for_worker = []
    skipped_file_info = None # Tuple: (filepath, reason, output_dir_for_log)

    filename = os.path.basename(filepath)

    # Calculate relative_path for structuring output correctly
    if root_dir_for_relative_path:
        relative_path = os.path.relpath(filepath, root_dir_for_relative_path)
    else:
        # Fallback: use filename directly, implies output files are flat in mekfiles_output_dir
        # This might happen if root_dir_for_relative_path is not passed, though it should be.
        relative_path = Path(filename)

    # Ensure the output directory for the JSON file exists
    # output_filepath_json needs to include any subdirectories from relative_path
    output_json_dir = os.path.dirname(os.path.join(mekfiles_output_dir, relative_path))
    # os.makedirs(output_json_dir, exist_ok=True) # Worker should not create dirs, main process should pre-create.
                                                # Let's assume mekfiles_output_dir and its subdirs are prepped by main.
                                                # For now, save_to_json handles os.makedirs for the direct parent.

    output_filepath_json = os.path.join(mekfiles_output_dir, Path(relative_path).with_suffix('.json'))

    parsed_data = None
    # parse_error_occurred is used to avoid double-logging skips if the parser itself logs it.
    parse_error_occurred = False

    if filename.lower().endswith(".mtf"):
        parsed_data, derived_eq = parse_mtf_file(filepath, base_output_dir)
        if parsed_data:
            derived_equipment_for_worker.extend(derived_eq)
        else: # Error in parsing, assumed to be logged by parse_mtf_file
            parse_error_occurred = True
    elif filename.lower().endswith(".blk"):
        parsed_data, derived_eq = parse_blk_file(filepath, base_output_dir)
        if parsed_data:
            derived_equipment_for_worker.extend(derived_eq)
        else: # Error in parsing, assumed to be logged by parse_blk_file
            parse_error_occurred = True
    elif filename.lower() == "unitverifieroptions.xml":
        # For this specific file, the output path is fixed relative to mekfiles_output_dir
        output_filepath_json = os.path.join(mekfiles_output_dir, "UnitVerifierOptions.json")
        parsed_data, derived_eq = parse_xml_file(filepath, base_output_dir) # Expects two values now
        if parsed_data:
            derived_equipment_for_worker.extend(derived_eq)
        else: # Error in parsing, assumed to be logged by parse_xml_file
            parse_error_occurred = True

    # This part for non-MTF/BLK/XML files might need adjustment if other XMLs are to be processed for derived_equipment
    # For now, only unitverifieroptions.xml is handled by parse_xml_file explicitly for derived_equipment.


    if parsed_data:
        # Ensure the specific directory for this JSON exists before saving
        # This is important if relative_path contains subdirectories
        os.makedirs(os.path.dirname(output_filepath_json), exist_ok=True)
        save_to_json(parsed_data, output_filepath_json, base_output_dir)
        processed_count = 1
    elif not parse_error_occurred and not filename.lower().endswith(
        ('.png', '.gif', '.jpg', '.jpeg', '.svg', '.txt', '.html', '.xml~',
         '.psd', '.md', '.pdf', '.doc', '.docx', '.zip', '.log', '.jar',
         '.xsl', '.css', '.js', '.tif', '.tiff', '.bmp', '.datasheet',
         '.bk2', '.लक', '.dat', '.mmf')
        ):
        # This file wasn't processed by any specific parser, no error was logged by them,
        # and it's not in the general skip list.
        # We need to record this skip to be logged by the main process.
        # Exception: unitverifieroptions.xml, if it failed parsing, parse_error_occurred would be true.
        # If it's some other XML, it would fall here.
        if not (filename.lower() == "unitverifieroptions.xml" and parse_error_occurred) :
             skipped_file_info = (filepath, "Unsupported file type or error during processing.", base_output_dir)

    return {
        "processed_count": processed_count,
        "derived_equipment": derived_equipment_for_worker,
        "skipped_file_info": skipped_file_info
    }

def save_to_json(data, output_filepath, output_dir_for_log):
    try:
        os.makedirs(os.path.dirname(output_filepath), exist_ok=True)
        with open(output_filepath, 'w', encoding='utf-8') as f: json.dump(data, f, indent=2, ensure_ascii=False)
    except Exception as e:
        print(f"Error saving JSON to {output_filepath}: {e}")
        log_skipped_file(output_filepath, f"JSON Save Error: {e}", output_dir_for_log)

def process_files(root_dir, base_output_dir):
    total_processed_count = 0
    all_derived_equipment_tuples = []
    files_to_process_args_list = []

    mekfiles_output_dir = os.path.join(base_output_dir, "mekfiles")
    # No need to os.makedirs for mekfiles_output_dir here,
    # _process_file_worker -> save_to_json will create subdirectories as needed for each file.
    # However, the main mekfiles_output_dir itself should exist.
    os.makedirs(mekfiles_output_dir, exist_ok=True)

    # Collect all filepaths and arguments for the worker
    for dirpath, _, filenames in os.walk(root_dir):
        for filename in filenames:
            filepath = os.path.join(dirpath, filename)
            # The worker needs: filepath, base_output_dir, mekfiles_output_dir, root_dir (for relpath calc)
            files_to_process_args_list.append((filepath, base_output_dir, mekfiles_output_dir, root_dir))

    # Use multiprocessing Pool
    # num_processes = multiprocessing.cpu_count() # Use all available CPUs
    # Using a fixed number for now for stability, can be tuned. e.g. max(1, num_processes -1 )
    num_processes = multiprocessing.cpu_count()

    print(f"Starting processing with {num_processes} workers...")

    results = []
    # Consider using imap_unordered for large number of files for better memory usage and progress feedback
    # For simplicity, using map for now.
    with multiprocessing.Pool(processes=num_processes) as pool:
        results = pool.map(_process_file_worker, files_to_process_args_list)

    # Process results from workers
    for result in results:
        if result: # Ensure result is not None, though workers should always return a dict
            total_processed_count += result["processed_count"]
            all_derived_equipment_tuples.extend(result["derived_equipment"])
            if result["skipped_file_info"]:
                # skipped_file_info is (filepath, reason, output_dir_for_log)
                log_skipped_file(result["skipped_file_info"][0], result["skipped_file_info"][1], result["skipped_file_info"][2])

    # Populate DERIVED_EQUIPMENT from all collected tuples
    # This must be done in the main process after all workers are done.
    for equip_tuple in all_derived_equipment_tuples:
        # Ensure the tuple has the correct number of arguments for add_to_derived_equipment
        if len(equip_tuple) == 5:
            add_to_derived_equipment(equip_tuple[0], equip_tuple[1], equip_tuple[2], equip_tuple[3], equip_tuple[4])
        else:
            # Log an error or handle malformed tuples if necessary
            print(f"Warning: Malformed equipment tuple: {equip_tuple}")


    # Save the aggregated DERIVED_EQUIPMENT to JSON
    derived_equipment_path = os.path.join(mekfiles_output_dir, "derivedEquipment.json")
    # Ensure DERIVED_EQUIPMENT is not empty before trying to get values, though list() on empty dict is fine.
    save_to_json(list(DERIVED_EQUIPMENT.values()), derived_equipment_path, base_output_dir)
    print(f"Derived equipment data saved to {derived_equipment_path}")

    return total_processed_count

if __name__ == "__main__":
    fixed_output_dir_name = os.environ.get("MEGAMEKLAB_OUTPUT_DIR", "megameklab_converted_output")
    os.makedirs(fixed_output_dir_name, exist_ok=True)
    current_run_log_path = os.path.join(fixed_output_dir_name, SKIPPED_FILES_LOG)
    if os.path.exists(current_run_log_path):
        try: os.remove(current_run_log_path)
        except OSError: pass
    megameklab_data_dir = os.environ.get("MEGAMEKLAB_DATA_DIR", "megameklab/data/mekfiles") # Default path
    if not os.path.isdir(megameklab_data_dir): print(f"Error: Data directory not found: {megameklab_data_dir}")
    else:
        print(f"Starting conversion from '{megameklab_data_dir}'... Output will be in: '{fixed_output_dir_name}'")
        processed_count = process_files(megameklab_data_dir, fixed_output_dir_name)
        print(f"Processing complete. {processed_count} files converted.")
        print(f"Total unique equipment items derived: {len(DERIVED_EQUIPMENT)}")
        if os.path.exists(current_run_log_path):
            with open(current_run_log_path, 'r', encoding='utf-8') as log_f:
                num_skipped = sum(1 for _ in log_f)
                if num_skipped > 0: print(f"{num_skipped} files/items were skipped or had errors. See {current_run_log_path}")
                else:
                    try: os.remove(current_run_log_path)
                    except OSError: pass
        else: print("No files were skipped or had errors.")
    print(f"Output directory name: {fixed_output_dir_name}")
