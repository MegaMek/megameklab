// battletech-editor-app/services/mockApiService.ts
export interface ApiListResponse<T> {
  items: T[];
  totalItems: number;
  totalPages: number;
  currentPage: number;
  // Add sortBy and sortOrder if you plan to mock sorting as well
}

// Basic Unit/Equipment types for mocking
interface MockUnit {
  id: string;
  chassis: string;
  model: string;
  mass: number;
  tech_base: string;
  era: string;
  type: string;
  [key: string]: any;
}

interface MockEquipment {
  id: string;
  name: string;
  type: string;
  tech_base: string;
  era: string;
  [key: string]: any;
}

const weightClassRanges: { [key: string]: { min: number; max: number } } = {
  'Light': { min: 20, max: 35 },
  'Medium': { min: 40, max: 55 },
  'Heavy': { min: 60, max: 75 },
  'Assault': { min: 80, max: 100 },
};

async function fetchMockDataFile(filePath: string): Promise<any> {
  const response = await fetch(filePath);
  if (!response.ok) {
    throw new Error(`Mock API Error: Could not fetch ${filePath} - ${response.statusText}`);
  }
  return response.json();
}

export const getUnits = async (queryParams: any): Promise<ApiListResponse<MockUnit>> => {
  console.log("Mock API: getUnits called with params:", queryParams);
  let units: MockUnit[] = await fetchMockDataFile('/mockdata/mockUnits.json'); // Default general list

  // More specific mock file if unit_type is provided (example for battlemechs)
  if (queryParams.unit_type && queryParams.unit_type.toLowerCase() === 'battlemech') {
    try {
      units = await fetchMockDataFile('/mockdata/mockBattlemechs.json');
    } catch (e) {
      console.warn("Mock API: Could not load mockBattlemechs.json, using mockUnits.json as fallback.");
    }
  } else if (queryParams.unit_type) {
    // Filter general list by type if not battlemech or specific file not found
     units = units.filter(unit => unit.type && unit.type.toLowerCase() === queryParams.unit_type.toLowerCase());
  }


  if (queryParams.q) {
    const term = queryParams.q.toLowerCase();
    units = units.filter(unit =>
      (unit.chassis && unit.chassis.toLowerCase().includes(term)) ||
      (unit.model && unit.model.toLowerCase().includes(term))
    );
  }
  if (queryParams.weight_class && weightClassRanges[queryParams.weight_class]) {
    const { min, max } = weightClassRanges[queryParams.weight_class];
    units = units.filter(unit => unit.mass >= min && unit.mass <= max);
  }
  if (queryParams.tech_base) { // Assuming tech_base is a single string from UnitFilters
    units = units.filter(unit => unit.tech_base && unit.tech_base.toLowerCase() === queryParams.tech_base.toLowerCase());
  }
   if (queryParams.tech_base_array) { // For API consistency if ever called from elsewhere with array
    const techBases = Array.isArray(queryParams.tech_base_array) ? queryParams.tech_base_array.map(t=>t.toLowerCase()) : queryParams.tech_base_array.toLowerCase().split(',');
    units = units.filter(unit => unit.tech_base && techBases.includes(unit.tech_base.toLowerCase()));
  }
  if (queryParams.era) { // Assuming era is a single string from UnitFilters
    units = units.filter(unit => unit.era && String(unit.era).toLowerCase() === queryParams.era.toLowerCase());
  }
   // Add mass_gte and mass_lte if present in queryParams
  if (queryParams.mass_gte) {
    units = units.filter(unit => unit.mass >= parseInt(queryParams.mass_gte, 10));
  }
  if (queryParams.mass_lte) {
    units = units.filter(unit => unit.mass <= parseInt(queryParams.mass_lte, 10));
  }


  const totalItems = units.length;
  const limit = parseInt(queryParams.limit, 10) || 10;
  const page = parseInt(queryParams.page, 10) || 1;
  const totalPages = Math.ceil(totalItems / limit);
  const startIndex = (page - 1) * limit;
  const endIndex = startIndex + limit;
  const items = units.slice(startIndex, endIndex);

  return { items, totalItems, totalPages, currentPage: page };
};

export const getEquipment = async (queryParams: any): Promise<ApiListResponse<MockEquipment>> => {
  console.log("Mock API: getEquipment called with params:", queryParams);
  let equipment: MockEquipment[] = await fetchMockDataFile('/mockdata/mockEquipment.json');

  if (queryParams.q) {
    const term = queryParams.q.toLowerCase();
    equipment = equipment.filter(item => item.name && item.name.toLowerCase().includes(term));
  }
  // 'type' filter for equipment (from selectedEquipmentCategory)
  if (queryParams.type) {
     equipment = equipment.filter(item => item.type && item.type.toLowerCase() === queryParams.type.toLowerCase());
  }
  // The API uses type_array, tech_base_array, era_array, so we'll check for those from filters
  if (queryParams.type_array) { // This would be used if selectedCategory was passed as type_array
    const types = Array.isArray(queryParams.type_array) ? queryParams.type_array.map(t => t.toLowerCase()) : queryParams.type_array.toLowerCase().split(',');
    equipment = equipment.filter(item => item.type && types.includes(item.type.toLowerCase()));
  }
  if (queryParams.tech_base_array) { // EquipmentFilters provides techBase as single string
     const techBases = Array.isArray(queryParams.tech_base_array) ? queryParams.tech_base_array.map(t=>t.toLowerCase()) : queryParams.tech_base_array.toLowerCase().split(',');
     equipment = equipment.filter(item => item.tech_base && techBases.includes(item.tech_base.toLowerCase()));
  } else if (queryParams.tech_base) { // Handling single tech_base from EquipmentFilters
     equipment = equipment.filter(item => item.tech_base && item.tech_base.toLowerCase() === queryParams.tech_base.toLowerCase());
  }

  if (queryParams.era_array) { // EquipmentFilters provides era as single string
    const eras = Array.isArray(queryParams.era_array) ? queryParams.era_array.map(e => String(e).toLowerCase()) : String(queryParams.era_array).toLowerCase().split(',');
    equipment = equipment.filter(item => item.era && eras.includes(String(item.era).toLowerCase()));
  } else if (queryParams.era) { // Handling single era from EquipmentFilters
    equipment = equipment.filter(item => item.era && String(item.era).toLowerCase() === queryParams.era.toLowerCase());
  }


  const totalItems = equipment.length;
  const limit = parseInt(queryParams.limit, 10) || 10;
  const page = parseInt(queryParams.page, 10) || 1;
  const totalPages = Math.ceil(totalItems / limit);
  const startIndex = (page - 1) * limit;
  const endIndex = startIndex + limit;
  const items = equipment.slice(startIndex, endIndex);

  return { items, totalItems, totalPages, currentPage: page };
};

export const getMetadata = async (filePath: string): Promise<string[]> => {
    console.log("Mock API: getMetadata called for:", filePath);
    return fetchMockDataFile(filePath);
};
