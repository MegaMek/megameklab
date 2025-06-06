import React, { useState, useEffect, useCallback } from 'react';
import UnitListItem from './UnitListItem';
import Pagination from '../common/Pagination';
import { BasicUnit, PaginatedResponse } from '../../types'; // Ensure this path and types are correct

interface UnitListProps {
  selectedCategory: string | null; // From CategoryNavigation
}

const UnitList: React.FC<UnitListProps> = ({ selectedCategory }) => {
  const [units, setUnits] = useState<BasicUnit[]>([]);
  const [isLoading, setIsLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);

  const [currentPage, setCurrentPage] = useState<number>(1);
  const [totalPages, setTotalPages] = useState<number>(0);
  // totalItems is available from API but not directly used in UI here yet, could be for "X results found"
  // const [totalItems, setTotalItems] = useState<number>(0);

  // --- Filter States ---
  const [searchTerm, setSearchTerm] = useState<string>(''); // For 'q' API param (chassis, model)
  const [techBaseInput, setTechBaseInput] = useState<string>(''); // Comma-separated string for 'tech_base_array'
  const [massMin, setMassMin] = useState<string>('');       // For 'mass_gte'
  const [massMax, setMassMax] = useState<string>('');       // For 'mass_lte'
  const [quirk, setQuirk] = useState<string>('');           // For 'has_quirk'

  // --- Sorting States ---
  const [sortBy, setSortBy] = useState<string>('chassis'); // Default sort
  const [sortOrder, setSortOrder] = useState<'asc' | 'desc'>('asc');


  const fetchUnits = useCallback(async (page: number) => {
    setIsLoading(true);
    setError(null);

    const queryParams = new URLSearchParams();
    queryParams.set('page', page.toString());
    queryParams.set('limit', '10'); // Or make limit configurable

    // Add filters to queryParams
    if (selectedCategory) {
      queryParams.set('unit_type', selectedCategory);
    }
    if (searchTerm.trim()) {
      queryParams.set('q', searchTerm.trim());
    }
    if (techBaseInput.trim()) {
      const techBases = techBaseInput.split(',').map(tb => tb.trim()).filter(tb => tb);
      if (techBases.length > 0) {
        // The API expects 'tech_base_array' for each value if it's an array
        techBases.forEach(tb => queryParams.append('tech_base_array', tb));
      }
    }
    if (massMin.trim()) {
      queryParams.set('mass_gte', massMin.trim());
    }
    if (massMax.trim()) {
      queryParams.set('mass_lte', massMax.trim());
    }
    if (quirk.trim()) {
      queryParams.set('has_quirk', quirk.trim());
    }

    // Add sorting to queryParams
    if (sortBy) {
      queryParams.set('sortBy', sortBy);
      queryParams.set('sortOrder', sortOrder);
    }

    try {
      const response = await fetch(`/api/units?${queryParams.toString()}`);
      if (!response.ok) {
        const errorData = await response.json().catch(() => ({ message: `API Error: ${response.status} ${response.statusText}` }));
        throw new Error(errorData.message || `API Error: ${response.status} ${response.statusText}`);
      }

      const data: PaginatedResponse<BasicUnit> = await response.json();

      setUnits(data.items || []);
      setTotalPages(data.totalPages || 0);
      setCurrentPage(data.currentPage || 1);
      // setTotalItems(data.totalItems || 0);
      setSortBy(data.sortBy || 'chassis'); // Update sort state from API response
      setSortOrder(data.sortOrder || 'asc');


      if ((data.items || []).length === 0 && (data.currentPage || 1) > 1 && page > 1) {
        setCurrentPage(1);
      }

    } catch (err) {
      setError(err instanceof Error ? err.message : 'An unknown error occurred');
      setUnits([]);
    } finally {
      setIsLoading(false);
    }
  }, [selectedCategory, searchTerm, techBaseInput, massMin, massMax, quirk, sortBy, sortOrder]);

  useEffect(() => {
    // This effect triggers fetching when currentPage changes or when fetchUnits itself changes due to its dependencies.
    fetchUnits(currentPage);
  }, [currentPage, fetchUnits]);

  // This effect resets to page 1 when filters (excluding currentPage, sortBy, sortOrder which are handled in fetchUnits dependencies) change.
  useEffect(() => {
    setCurrentPage(1);
  }, [selectedCategory, searchTerm, techBaseInput, massMin, massMax, quirk]);


  const handlePageChange = (page: number) => {
    setCurrentPage(page);
  };

  const handleFilterSubmit = (event?: React.FormEvent) => {
    event?.preventDefault();
    // setCurrentPage(1) is handled by the second useEffect based on filter changes.
    // If already on page 1, and filters changed, fetchUnits dependency change will trigger the fetch.
    // If filters didn't change (e.g. just pressing enter on an unchanged field), no extra fetch needed.
    // Explicitly call fetch for page 1 if already on page 1 and want to force refresh on submit button.
    if (currentPage === 1) {
        fetchUnits(1);
    } else {
        setCurrentPage(1); // This will trigger the fetch via useEffect.
    }
  };

  const handleInputKeyPress = (event: React.KeyboardEvent<HTMLInputElement>) => {
    if (event.key === 'Enter') {
      handleFilterSubmit();
    }
  };

  const handleSortChange = (newSortBy: string, newSortOrder?: 'asc' | 'desc') => {
    const finalSortOrder = newSortOrder || sortOrder; // Keep current order if only column changes
    setSortBy(newSortBy);
    setSortOrder(finalSortOrder);
    setCurrentPage(1); // Reset to page 1 on sort change
    // fetchUnits will be called by useEffect due to sortBy/sortOrder change in fetchUnits deps.
  };

  const renderSortControls = () => (
    <div className="flex items-center space-x-2 mb-2">
      <label htmlFor="sortBy" className="text-sm font-medium">Sort By:</label>
      <select
        id="sortBy"
        value={sortBy}
        onChange={(e) => handleSortChange(e.target.value)}
        className="p-2 border rounded text-sm bg-white shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-500"
      >
        <option value="chassis">Chassis</option>
        <option value="model">Model</option>
        <option value="mass">Mass</option>
        <option value="tech_base">Tech Base</option>
        <option value="era">Era</option>
        <option value="rules_level">Rules Level</option>
        <option value="type">Type</option>
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

  if (isLoading && units.length === 0 && currentPage === 1) {
    return <div className="text-center py-10">Loading units...</div>;
  }

  if (error) {
    return <div className="text-center py-10 text-red-500">Error loading units: {error}</div>;
  }

  return (
    <div className="p-4">
      <form onSubmit={handleFilterSubmit} className="p-4 mb-6 bg-gray-50 border border-gray-200 rounded-lg shadow-sm">
        <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-4">
          <input
            type="text"
            placeholder="Search Name/Chassis/Model..."
            value={searchTerm}
            onChange={(e) => setSearchTerm(e.target.value)}
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
          <input
            type="number"
            placeholder="Min Mass"
            value={massMin}
            onChange={(e) => setMassMin(e.target.value)}
            onKeyPress={handleInputKeyPress}
            className="p-2 border border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500"
          />
          <input
            type="number"
            placeholder="Max Mass"
            value={massMax}
            onChange={(e) => setMassMax(e.target.value)}
            onKeyPress={handleInputKeyPress}
            className="p-2 border border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500"
          />
          <input
            type="text"
            placeholder="Quirk (e.g., Accurate Weapon)"
            value={quirk}
            onChange={(e) => setQuirk(e.target.value)}
            onKeyPress={handleInputKeyPress}
            className="p-2 border border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500"
          />
        </div>
        <div className="mt-4 flex flex-col items-center md:flex-row md:justify-between">
          {renderSortControls()}
          <button type="submit" className="mt-4 md:mt-0 px-6 py-2 bg-green-600 text-white rounded-md hover:bg-green-700 focus:outline-none focus:ring-2 focus:ring-green-500 focus:ring-opacity-50 shadow-sm">
            Apply Filters
          </button>
        </div>
      </form>

      {isLoading && <div className="text-center py-5 text-gray-500">Updating list...</div>}

      {units.length === 0 && !isLoading ? (
        <div className="text-center py-10 text-gray-500">No units found matching your criteria.</div>
      ) : (
        <div className="space-y-4">
          {units.map((unit) => (
            <UnitListItem key={unit.id} unit={unit} />
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

export default UnitList;
