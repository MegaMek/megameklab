// Basic Unit structure based on schema fields for list view
export interface BasicUnit {
  id: string; // or number, depending on API
  chassis: string;
  model: string;
  mass: number; // Mass in tons
  era: string;
  tech_base: string;
  role?: string;
  // This comes from the `data` field in the DB, so API needs to extract it or pass `data`
  // For simplicity, assuming API might provide a summarized version or specific fields for lists
  // Alternatively, the component would access `unit.data.mass` etc.
}

// Full Unit structure, mirroring the schema more closely for detail view
// The `data` field from the DB is the core here.
export interface FullUnit {
  id: string; // or number
  chassis: string;
  model: string;
  mul_id?: string;
  data: UnitData; // This is the JSONB content
  // other top-level indexed fields if needed, like mass, era, tech_base, source
  mass: number;
  era: string;
  tech_base: string;
  rules_level?: number | string;
  source?: string;
  role?: string;
}

export interface UnitData {
  chassis: string; // Redundant with top-level, but often in MTF
  model: string;   // Redundant
  mul_id?: string;
  config?: string;
  tech_base?: string;
  era?: string;
  source?: string;
  rules_level?: number | string;
  role?: { name?: string }; // Role might be an object in some MTFs
  mass?: number;
  engine?: {
    type?: string;
    rating?: number;
    manufacturer?: string;
    gyro_type?: string; // Some schemas might place gyro here
  };
  structure?: {
    type?: string;
    manufacturer?: string;
  };
  myomer?: {
    type?: string;
    manufacturer?: string;
  };
  heat_sinks?: {
    type?: string;
    count?: number;
    dissipation_per_sink?: number; // Or just 'dissipation'
    location?: string; // sometimes heat sinks are listed in criticals by location
  };
  movement?: {
    walk_mp?: number;
    jump_mp?: number;
    cruise_mp?: number;
    flank_mp?: number;
    thrust_mp?: number;
    safe_thrust_mp?: number;
    dive_mp?: number;
  };
  armor?: {
    type?: string;
    manufacturer?: string;
    locations: ArmorLocation[];
  };
  weapons_and_equipment?: WeaponOrEquipmentItem[];
  criticals?: CriticalSlotLocation[];
  quirks?: string[] | { name: string, description?: string }[]; // Quirk format can vary
  manufacturers?: Manufacturer[];
  fluff_text?: FluffText;
  // LAM specific (examples)
  config_LAM?: string; // If 'Config: LAM'
  landing_gear?: any; // Structure depends on how it's stored
  avionics?: any;
}

export interface ArmorLocation {
  location: string;
  armor_points: number;
  rear_armor_points?: number;
}

export interface WeaponOrEquipmentItem {
  item_name: string;
  item_type: string; // 'weapon', 'ammo', 'equipment'
  location: string;
  rear_facing?: boolean;
  turret_mounted?: boolean;
  ammo_per_shot?: number;
  related_ammo?: string;
  // other fields like damage, range from .blk or derived equipment data
  damage?: string | number;
  range?: string; // e.g., S/M/L
  heat?: number;
}

export interface CriticalSlotLocation {
  location: string;
  slots: string[]; // Array of equipment names or '-Empty-'
}

export interface Manufacturer {
  type?: string; // 'primary_factory', 'system_manufacturer'
  name: string;
  location?: string;
}

export interface FluffText {
  overview?: string;
  capabilities?: string;
  deployment?: string;
  history?: string;
  // other fluff sections
  [key: string]: string | undefined;
}


// Basic Equipment structure
export interface BasicEquipment {
  id: string; // or number
  name: string;
  type: string;
  tech_base?: string;
  era?: string;
}

// Full Equipment structure (can be same as Basic if not much detail)
export interface FullEquipment extends BasicEquipment {
  data?: EquipmentData; // The JSONB content from the equipment table
  // other specific fields like damage, range, heat for weapons
  damage?: string | number;
  range?: string; // S/M/L or actual numbers
  heat?: number;
  weight?: number;
  space?: number; // e.g., critical slots
  cost?: number;
  introduction_year?: number | string;
  extinction_year?: number | string;
  rules?: string; // e.g. "Experimental", "Advanced"
  book_reference?: string;
  source?: string; // Source of the equipment data, e.g., a TRO name
}

export interface EquipmentData {
  tons?: number | string;
  slots?: number | string;
  cost?: number | string;
  battle_value?: number | string;
  battlevalue?: number | string; // alias for battle_value
  weapon_type?: string;
  damage?: string | number; // Could be '1d6', 5, or 'special'
  heatmap?: number;
  range?: {
    short?: number;
    medium?: number;
    long?: number;
    extreme?: number;
    minimum?: number;
  };
  ammo_per_shot?: number;
  shots?: number; // Total shots for ammo, or shots per ton
  manufacturer?: string;
  model?: string; // Sometimes distinct from name
  tech_rating?: string; // e.g., A, B, C, D, E, F, X or Clan/IS
  legality?: {
    all?: string; // e.g. "Tournament Legal", "Succession Wars Legal"
    [key: string]: any; // For specific era/faction legality
  };
  introduced?: number | string; // Year or approximate period
  specials?: string | string[]; // Special properties, e.g., "AP", "Heat", "Indirect Fire"
  // Fields that might also be on the top-level FullEquipment but could be in data blob
  name?: string;
  type?: string; // e.g. 'Energy Weapon', 'Ballistic Weapon', 'Equipment'
  tech_base?: string; // 'Clan', 'Inner Sphere', 'Mixed', 'Primitive'
  category?: string; // Broader than type, e.g., 'Lasers', 'Autocannons'
  rules_level?: string; // 'Introductory', 'Standard', 'Advanced', 'Experimental'
  extinction_year?: number | string;
  source_book?: string;
  critical_slots?: number; // alias for slots
  tonnage?: number | string; // alias for tons
  cost_cbills?: number | string; // alias for cost
}


// API response for paginated lists
export interface PaginatedResponse<T> {
  items: T[];
  totalItems: number;
  totalPages: number;
  currentPage: number;
}
