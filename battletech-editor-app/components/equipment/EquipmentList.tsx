import React, { useState, useEffect, useCallback } from 'react';
import EquipmentListItem from './EquipmentListItem';
import Pagination from '../common/Pagination';
import { BasicEquipment, PaginatedResponse } from '../../types'; // Ensure this path and types are correct

// Props could be extended if needed, e.g., for a shared category filter if applicable in future
// interface EquipmentListProps {
//   selectedGlobalCategory?: string | null;
// }

const EquipmentList: React.FC = () => {
  const [equipment, setEquipment] = useState<BasicEquipment[]>([]);
  const [isLoading, setIsLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);

  const [currentPage, setCurrentPage] = useState<number>(1);
  const [totalPages, setTotalPages] = useState<number>(0);

  // --- Filter States ---
  const [searchTerm, setSearchTerm] = useState<string>('');         // For 'q' API param (name)
  const [typeInput, setTypeInput] = useState<string>('');           // Comma-separated for 'type_array'
  const [techBaseInput, setTechBaseInput] = useState<string>('');   // Comma-separated for 'tech_base_array'
  // const [eraInput, setEraInput] = useState<string>(''); // API for equipment does not yet support era filter.

  // --- Sorting States ---
  const [sortBy, setSortBy] = useState<string>('name'); // Default sort for equipment
  const [sortOrder, setSortOrder] = useState<'asc' | 'desc'>('asc');


  const fetchEquipment = useCallback(async (page: number) => {
    setIsLoading(true);
    setError(null);

    const queryParams = new URLSearchParams();
    queryParams.set('page', page.toString());
    queryParams.set('limit', '15'); // Or make limit configurable

    // Add filters to queryParams
    if (searchTerm.trim()) {
      queryParams.set('q', searchTerm.trim());
    }
    if (typeInput.trim()) {
      const types = typeInput.split(',').map(t => t.trim()).filter(t => t);
      if (types.length > 0) {
        types.forEach(t => queryParams.append('type_array', t));
      }
    }
    if (techBaseInput.trim()) {
      const techBases = techBaseInput.split(',').map(tb => tb.trim()).filter(tb => tb);
      if (techBases.length > 0) {
        techBases.forEach(tb => queryParams.append('tech_base_array', tb));
      }
    }
    // API for equipment does not yet support era filter.
    // if (eraInput.trim()) {
    //   const eras = eraInput.split(',').map(e => e.trim()).filter(e => e);
    //   if (eras.length > 0) {
    //     eras.forEach(e => queryParams.append('era_array', e));
    //   }
    // }

    // Add sorting to queryParams
    if (sortBy) {
      queryParams.set('sortBy', sortBy);
      queryParams.set('sortOrder', sortOrder);
    }

    try {
      const response = await fetch(`/api/equipment?${queryParams.toString()}`);
      if (!response.ok) {
        const errorData = await response.json().catch(() => ({ message: `API Error: ${response.status} ${response.statusText}` }));
        throw new Error(errorData.message || `API Error: ${response.status} ${response.statusText}`);
      }

      const data: PaginatedResponse<BasicEquipment> = await response.json();

      setEquipment(data.items || []);
      setTotalPages(data.totalPages || 0);
      setCurrentPage(data.currentPage || 1);
      setSortBy(data.sortBy || 'name');
      setSortOrder(data.sortOrder || 'asc');


      if ((data.items || []).length === 0 && (data.currentPage || 1) > 1 && page > 1) {
        setCurrentPage(1);
      }

    } catch (err) {
      setError(err instanceof Error ? err.message : 'An unknown error occurred');
      setEquipment([]);
    } finally {
      setIsLoading(false);
    }
  }, [searchTerm, typeInput, techBaseInput, /*eraInput,*/ sortBy, sortOrder]);

  // Effect to reset to page 1 when filters change
  useEffect(() => {
    setCurrentPage(1);
  }, [searchTerm, typeInput, techBaseInput /*eraInput*/]);


  useEffect(() => {
    fetchEquipment(currentPage);
  }, [currentPage, fetchEquipment]);

  const handlePageChange = (page: number) => {
    setCurrentPage(page);
  };

  const handleFilterSubmit = (event?: React.FormEvent) => {
    event?.preventDefault();
    // setCurrentPage(1) is handled by the useEffect above if filter inputs changed.
    // If already on page 1 and inputs haven't changed, or to force a refresh:
    if (currentPage === 1) {
        fetchEquipment(1);
    } else {
        setCurrentPage(1); // This will trigger fetch via useEffect due to currentPage change.
    }
  };

  const handleInputKeyPress = (event: React.KeyboardEvent<HTMLInputElement>) => {
    if (event.key === 'Enter') {
      handleFilterSubmit();
    }
  };

  const handleSortChange = (newSortBy: string, newSortOrder?: 'asc' | 'desc') => {
    const finalSortOrder = newSortOrder || sortOrder;
    setSortBy(newSortBy);
    setSortOrder(finalSortOrder);
    setCurrentPage(1); // Reset to page 1 on sort change
  };


  const renderSortControls = () => (
    <div className="flex items-center space-x-2 mb-2">
      <label htmlFor="eqSortBy" className="text-sm font-medium">Sort By:</label>
      <select
        id="eqSortBy"
        value={sortBy}
        onChange={(e) => handleSortChange(e.target.value)}
        className="p-2 border rounded text-sm bg-white shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-500"
      >
        <option value="name">Name</option>
        <option value="type">Type</option>
        <option value="tech_base">Tech Base</option>
        <option value="era">Era</option> {/* API must support 'era' sort key */}
         <option value="id">ID</option>
      </select>
      <select
        value={sortOrder}
        onChange={(e) => handleSortChange(sortBy, e.target.value as 'asc' | 'desc')}
        className="p-2 border rounded text-sm bg-white shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-500"
      >
        <option value="asc">Asc</option>
        <option value="desc">Desc</option>
      </select>
    </div>
  );

  if (isLoading && equipment.length === 0 && currentPage === 1) {
    return <div className="text-center py-10">Loading equipment...</div>;
  }
  if (error) {
    return <div className="text-center py-10 text-red-500">Error: {error}</div>;
  }

  return (
    <div className="p-4">
      <form onSubmit={handleFilterSubmit} className="p-4 mb-6 bg-gray-50 border border-gray-200 rounded-lg shadow-sm">
        <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-4">
          <input
            type="text"
            placeholder="Search Name..."
            value={searchTerm}
            onChange={(e) => setSearchTerm(e.target.value)}
            onKeyPress={handleInputKeyPress}
            className="p-2 border border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500"
          />
          <input
            type="text"
            placeholder="Type (e.g., Weapon,Ammo) (comma-sep)"
            value={typeInput}
            onChange={(e) => setTypeInput(e.target.value)}
            onKeyPress={handleInputKeyPress}
            className="p-2 border border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500"
          />
          <input
            type="text"
            placeholder="Tech Base (e.g., IS,Clan) (comma-sep)"
            value={techBaseInput}
            onChange={(e) => setTechBaseInput(e.target.value)}
            onKeyPress={handleInputKeyPress}
            className="p-2 border border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500"
          />
          {/* API for equipment does not yet support era filter.
          <input
            type="text"
            placeholder="Era (e.g., Clan Invasion) (comma-sep)"
            value={eraInput}
            onChange={(e) => setEraInput(e.target.value)}
            onKeyPress={handleInputKeyPress}
            className="p-2 border rounded"
          />
          */}
        </div>
        <div className="mt-4 flex flex-col items-center md:flex-row md:justify-between">
          {renderSortControls()}
          <button type="submit" className="mt-4 md:mt-0 px-6 py-2 bg-green-600 text-white rounded-md hover:bg-green-700 focus:outline-none focus:ring-2 focus:ring-green-500 focus:ring-opacity-50 shadow-sm">
            Apply Filters
          </button>
        </div>
      </form>

      {isLoading && <div className="text-center py-5 text-gray-500">Updating list...</div>}

      {equipment.length === 0 && !isLoading ? (
        <p className="text-center py-10 text-gray-500">No equipment found matching your criteria.</p>
      ) : (
        <div className="space-y-3">
          {equipment.map((item) => (
            <EquipmentListItem key={item.id} equipment={item} />
          ))}
        </div>
      )}
      {totalPages > 1 && (
        <Pagination
          currentPage={currentPage}
          totalPages={totalPages}
          onPageChange={handlePageChange}
        />
      )}
    </div>
  );
};

export default EquipmentList;
