// battletech-editor-app/components/compendium/UnitCompendiumList.tsx
import React, { useState, useEffect } from 'react';
import Link from 'next/link';
import type { UnitFilterState } from './UnitFilters';
import { getUnits, ApiListResponse } from '../../services/mockApiService';

// Define a basic Unit interface for mock data - can be enhanced if needed
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

interface UnitCompendiumListProps {
  filters: UnitFilterState;
  selectedCategory: string | null;
}

const UnitCompendiumList: React.FC<UnitCompendiumListProps> = ({ filters, selectedCategory }) => {
  const [unitData, setUnitData] = useState<ApiListResponse<MockUnit> | null>(null);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);
  const [currentPage, setCurrentPage] = useState(1);
  const itemsPerPage = 10; // This could be made a prop or part of a settings context later

  useEffect(() => {
    const fetchAndFilterUnits = async () => {
      setLoading(true);
      try {
        const queryParams: any = {
          page: currentPage,
          limit: itemsPerPage,
          q: filters.searchTerm,
          weight_class: filters.weightClass,
          tech_base: filters.techBase, // mockApiService getUnits expects single tech_base from UnitFilters
          era: filters.era, // mockApiService getUnits expects single era from UnitFilters
        };
        if (selectedCategory) {
          queryParams.unit_type = selectedCategory; // mockApiService getUnits uses unit_type for category
        }

        const data = await getUnits(queryParams);
        setUnitData(data);
        setError(null);
      } catch (e) {
        const errorMsg = e instanceof Error ? e.message : 'An unknown error occurred';
        setError(errorMsg);
        console.error("Failed to fetch units:", errorMsg);
      } finally {
        setLoading(false);
      }
    };
    fetchAndFilterUnits();
  }, [filters, selectedCategory, currentPage, itemsPerPage]);

  // Reset to page 1 when filters or category change
  useEffect(() => {
    setCurrentPage(1);
  }, [filters, selectedCategory]);


  if (loading) return <p>Loading units...</p>;
  if (error) return <p className="text-red-500">Error loading units: {error}</p>;
  if (!unitData || !unitData.items || unitData.items.length === 0) {
    return <p>No units match the current filters.</p>;
  }

  return (
    <div className="mt-4">
      <ul className="space-y-2">
        {unitData.items.map((unit) => (
          <li key={unit.id} className="p-2 border rounded hover:bg-gray-50">
            <Link href={`/units/${unit.id}`} legacyBehavior>
              <a className="text-blue-600 hover:text-blue-800">
                {unit.chassis} {unit.model} ({unit.mass} tons) - {unit.type}
              </a>
            </Link>
          </li>
        ))}
      </ul>
      {unitData.totalPages > 1 && (
        <div className="mt-4 flex justify-between items-center">
          <button
            onClick={() => setCurrentPage(p => Math.max(1, p - 1))}
            disabled={unitData.currentPage === 1}
            className="bg-gray-300 hover:bg-gray-400 text-gray-800 font-bold py-2 px-4 rounded disabled:opacity-50"
          >
            Previous
          </button>
          <p>
            Page {unitData.currentPage} of {unitData.totalPages} (Total: {unitData.totalItems} units)
          </p>
          <button
            onClick={() => setCurrentPage(p => Math.min(unitData.totalPages, p + 1))}
            disabled={unitData.currentPage === unitData.totalPages}
            className="bg-gray-300 hover:bg-gray-400 text-gray-800 font-bold py-2 px-4 rounded disabled:opacity-50"
          >
            Next
          </button>
        </div>
      )}
    </div>
  );
};

export default UnitCompendiumList;
