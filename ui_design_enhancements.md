# UI Design Enhancements for BattleTech Data Exploration

This document outlines concise design enhancements for UI components and page layouts for a BattleTech data exploration application.

## 1. Category Navigation Design (`CategoryNavigation.tsx`)

*   **Data Source:** Assumes an API endpoint like `/api/meta/categories` provides a list of `unit_type` strings (e.g., "BattleMech", "Vehicle").
*   **Display:**
    *   A sidebar component (potentially collapsible on smaller screens).
    *   Renders each `unit_type` as a clickable link or styled button.
    *   The currently active category will be visually highlighted (e.g., different background color or font weight).
*   **Interaction:**
    *   Clicking a `unit_type` link updates a shared global filter state (managed via Zustand, Redux, or React Context).
    *   This state change will signal the main unit/equipment list component (`UnitList.tsx` or `EquipmentList.tsx`) to re-fetch data, filtered by the newly selected `unit_type`.

## 2. Advanced Search/Filter Controls Design (within `UnitList.tsx` / `EquipmentList.tsx`)

*   A dedicated filter panel or section will be prominently displayed, typically above the list of units or equipment.
*   **Filter Inputs (Examples):**
    *   **Name/Chassis/Model Search:** A single text input field (e.g., `q` or `search_term`) for general keyword searching across these primary identification fields.
    *   **Tech Base:** A multi-select dropdown or a group of checkboxes (`tech_base_array` parameter) allowing users to select one or more options like "IS", "Clan", "Mixed", "Primitive".
    *   **Mass (for Units):** Two number input fields forming a range selector (`mass_gte` for minimum mass, `mass_lte` for maximum mass).
    *   **Quirks (for Units):** A simple text input (`has_quirk`) where users can type a single quirk name. The API will handle the specific JSONB query formatting based on this string.
    *   **Equipment Type (for Equipment):** A multi-select dropdown or checkbox group (`type_array` parameter) for categories like "weapon", "ammo", "equipment".
*   **State Management:**
    *   The `UnitList.tsx` (or `EquipmentList.tsx`) component will maintain a `filters` state object (e.g., `const [filters, setFilters] = useState<UnitFilters>({});`).
    *   This object will hold the current values of all active filters (e.g., `{ chassis: 'Archer', tech_base_array: ['IS'], mass_gte: 70 }`).
*   **API Interaction:**
    *   Changes to filter inputs (e.g., on input change with debounce for text fields, or on a dedicated "Apply Filters" button click) will update the `filters` state object.
    *   Upon `filters` state change, the component will trigger a re-fetch of data from its respective API endpoint (`/api/units` or `/api/equipment`). The `filters` object will be serialized into query parameters for the API request.

## 3. Refined Detail Page Design (`UnitDetail.tsx` - Key Sections)

*   **Structure:**
    *   The page will have a clear main heading displaying the unit's `chassis` and `model`.
    *   A sub-header section will prominently display key at-a-glance information like Mass, Tech Base, Role, and Era.
    *   Tabs will be used to organize detailed information into manageable sections:
        *   **Overview/Stats:** General performance characteristics (movement MPs, engine type/rating, structure type, heat sink count/type), and a summary of armor tonnage/type.
        *   **Armament & Equipment:** A detailed list of all weapons and significant pieces of equipment.
        *   **Criticals:** (Primarily for BattleMechs, ProtoMeks) A visual representation or list-based layout of critical slots for each hittable location.
        *   **Armor Distribution:** A clear breakdown of armor points per specific location (e.g., Head: 9, CT: 30, CT (Rear): 10).
        *   **History & Fluff:** Narrative sections like `overview`, `capabilities`, `deployment`, and `history`.
*   **Rendering `data` JSONB (Example - Weapons in "Armament" tab):**
    *   The `unit.data.weapons_and_equipment` array (from the JSONB `data` field) will be iterated.
    *   Each item will be displayed with its `item_name`, `item_type`, and `location`.
    *   Additional details like `rear_facing` or `turret_mounted` would be indicated.
    *   If weapon stats (damage, range) are directly embedded, they'll be shown. Otherwise, `item_name` might link to a dedicated equipment detail page/modal if such details are too extensive for the loadout list.
*   **Rendering `data` JSONB (Example - Criticals in "Criticals" tab):**
    *   The `unit.data.criticals` array (if present) will be iterated.
    *   For each `location` object (e.g., "Left Arm"), its `slots` array will be rendered, displaying each equipment name or "-Empty-" for unoccupied slots. This could be a simple list or a more graphical table-like layout.
*   **Fluff:**
    *   The `fluff_text` object fields (`overview`, `capabilities`, `deployment`, `history`, `notes`) will be rendered as well-formatted paragraphs under the "History & Fluff" tab.

## 4. Layout/UX Notes (Brief)

*   **Styling:** Tailwind CSS will be leveraged for its utility-first approach, enabling a clean, responsive, and modern application layout. Standard responsive design practices (mobile-first or desktop-first as appropriate) will ensure usability across various screen sizes.
*   **User Feedback:**
    *   Loading indicators (e.g., spinners, skeleton screens) will be displayed during any API call (fetching categories, lists of units/equipment, individual unit details) to provide clear feedback to the user that data is being retrieved.
    *   Error messages will be displayed gracefully and informatively if API calls fail or if no data matches the filter criteria.
    *   Pagination controls (e.g., page numbers, "next"/"previous" buttons) will be implemented for all list views to manage large datasets.

---

## Pseudo-code / React/TSX Snippets (Illustrative)

**`UnitList.tsx` - Filters State and API Call Trigger (Conceptual):**

```tsx
// Simplified pseudo-code for UnitList.tsx
import React, { useState, useEffect, useCallback } from 'react';

interface UnitFilters {
  chassis?: string;
  tech_base_array?: string[];
  mass_gte?: number;
  // ... other filters
  unit_type?: string; // From category navigation via global state/context
  sortBy?: string;
  sortOrder?: 'asc' | 'desc';
}

function UnitList() {
  const [units, setUnits] = useState<any[]>([]);
  const [filters, setFilters] = useState<UnitFilters>({});
  const [isLoading, setIsLoading] = useState(false);
  // const globalUnitTypeFilter = useGlobalFilterStore(state => state.unitType); // Example

  // Effect to update local unit_type filter from global state
  // useEffect(() => {
  //   setFilters(prev => ({ ...prev, unit_type: globalUnitTypeFilter, page: 1 }));
  // }, [globalUnitTypeFilter]);

  const fetchUnits = useCallback(async () => {
    setIsLoading(true);
    const queryParams = new URLSearchParams();
    // Populate queryParams from filters state
    Object.entries(filters).forEach(([key, value]) => {
      if (value !== undefined && value !== '') {
        if (Array.isArray(value)) {
          value.forEach(v => queryParams.append(key, v)); // API might expect tech_base_array[]=IS&tech_base_array[]=Clan
        } else {
          queryParams.append(key, String(value));
        }
      }
    });

    try {
      // const response = await fetch(`/api/units?${queryParams.toString()}`);
      // const data = await response.json();
      // setUnits(data.items || []);
      // setTotalPages(data.totalPages || 1);
    } catch (error) {
      console.error("Failed to fetch units:", error);
    } finally {
      setIsLoading(false);
    }
  }, [filters]);

  useEffect(() => {
    fetchUnits();
  }, [fetchUnits]);

  const handleFilterChange = (filterName: keyof UnitFilters, value: any) => {
    setFilters(prev => ({ ...prev, [filterName]: value, page: 1 })); // Reset to page 1 on filter change
  };

  // ... JSX for filter inputs and unit display ...
  return (
    <div>
      {/* Filter Panel UI with inputs calling handleFilterChange */}
      {/* Unit List Rendering & Pagination UI */}
    </div>
  );
}
```

**`UnitDetail.tsx` - Rendering Weapons from `data` JSONB (Illustrative Snippet for an "Armament" Tab):**

```tsx
// Simplified snippet for UnitDetail.tsx (Armament Tab content)
import React from 'react';

// Assuming types are defined elsewhere
// interface WeaponOrEquipmentItem { item_name: string; item_type: string; location: string; ... }
// interface UnitData { weapons_and_equipment?: WeaponOrEquipmentItem[]; ... }
// interface Unit { id: string; chassis: string; model: string; data: UnitData; ... }

interface UnitArmamentSectionProps {
  // unit: Unit; // Passed from the parent UnitDetail component
  weapons_and_equipment?: any[]; // Directly pass the relevant array
}

function UnitArmamentSection({ weapons_and_equipment }: UnitArmamentSectionProps) {
  if (!weapons_and_equipment || weapons_and_equipment.length === 0) {
    return <p>No armament information available for this unit.</p>;
  }

  return (
    <div className="mt-4">
      <h4 className="text-lg font-semibold mb-2">Weapons & Equipment</h4>
      <ul className="divide-y divide-gray-200">
        {weapons_and_equipment.map((item, index) => (
          <li key={index} className="py-2">
            <div className="font-medium">{item.item_name} <span className="text-sm text-gray-500">({item.item_type})</span></div>
            <div className="text-sm text-gray-600">Location: {item.location}</div>
            {/* Render other details like quantity, mode, ammo if available */}
          </li>
        ))}
      </ul>
    </div>
  );
}
```

This markdown file encapsulates the UI design as requested.
