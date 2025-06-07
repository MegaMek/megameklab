// battletech-editor-app/components/compendium/EquipmentCompendiumList.tsx
import React, { useState, useEffect } from 'react';
import Link from 'next/link';
import type { EquipmentFilterState } from './EquipmentFilters';
import { getEquipment, ApiListResponse } from '../../services/mockApiService';

interface MockEquipment {
  id: string;
  name: string;
  type: string;
  tech_base: string;
  era: string;
  [key: string]: any;
}

interface EquipmentCompendiumListProps {
  filters: EquipmentFilterState;
  selectedEquipmentCategory: string | null;
}

const EquipmentCompendiumList: React.FC<EquipmentCompendiumListProps> = ({ filters, selectedEquipmentCategory }) => {
  const [equipmentData, setEquipmentData] = useState<ApiListResponse<MockEquipment> | null>(null);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);
  const [currentPage, setCurrentPage] = useState(1);
  const itemsPerPage = 10;

  useEffect(() => {
    const fetchAndFilterEquipment = async () => {
      setLoading(true);
      try {
        const queryParams: any = {
          page: currentPage,
          limit: itemsPerPage,
          q: filters.searchTerm,
          tech_base: filters.techBase, // mockApiService getEquipment expects single tech_base
          era: filters.era,           // mockApiService getEquipment expects single era
        };
        if (selectedEquipmentCategory) {
          queryParams.type = selectedEquipmentCategory; // mockApiService getEquipment uses 'type' for category
        }

        const data = await getEquipment(queryParams);
        setEquipmentData(data);
        setError(null);
      } catch (e) {
        const errorMsg = e instanceof Error ? e.message : 'An unknown error occurred';
        setError(errorMsg);
        console.error("Failed to fetch equipment:", errorMsg);
      } finally {
        setLoading(false);
      }
    };
    fetchAndFilterEquipment();
  }, [filters, selectedEquipmentCategory, currentPage, itemsPerPage]);

  // Reset to page 1 when filters or category change
  useEffect(() => {
    setCurrentPage(1);
  }, [filters, selectedEquipmentCategory]);

  if (loading) return <p>Loading equipment...</p>;
  if (error) return <p className="text-red-500">Error loading equipment: {error}</p>;
  if (!equipmentData || !equipmentData.items || equipmentData.items.length === 0) {
    return <p>No equipment matches the current filters.</p>;
  }

  return (
    <div className="mt-4">
      <ul className="space-y-2">
        {equipmentData.items.map((item) => (
          <li key={item.id} className="p-2 border rounded hover:bg-gray-50">
            <Link href={`/equipment/${item.id}`} legacyBehavior>
              <a className="text-blue-600 hover:text-blue-800">
                {item.name} ({item.type}) - {item.tech_base} - Era: {item.era}
              </a>
            </Link>
          </li>
        ))}
      </ul>
      {equipmentData.totalPages > 1 && (
        <div className="mt-4 flex justify-between items-center">
          <button
            onClick={() => setCurrentPage(p => Math.max(1, p - 1))}
            disabled={equipmentData.currentPage === 1}
            className="bg-gray-300 hover:bg-gray-400 text-gray-800 font-bold py-2 px-4 rounded disabled:opacity-50"
          >
            Previous
          </button>
          <p>
            Page {equipmentData.currentPage} of {equipmentData.totalPages} (Total: {equipmentData.totalItems} items)
          </p>
          <button
            onClick={() => setCurrentPage(p => Math.min(equipmentData.totalPages, p + 1))}
            disabled={equipmentData.currentPage === equipmentData.totalPages}
            className="bg-gray-300 hover:bg-gray-400 text-gray-800 font-bold py-2 px-4 rounded disabled:opacity-50"
          >
            Next
          </button>
        </div>
      )}
    </div>
  );
};

export default EquipmentCompendiumList;
