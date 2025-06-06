import React, { useState, useEffect, useCallback } from 'react';
import UnitListItem from './UnitListItem';
import Pagination from '../common/Pagination';
import { BasicUnit, PaginatedResponse } from '../../types'; // Adjusted path

const UnitList: React.FC = () => {
  const [units, setUnits] = useState<BasicUnit[]>([]);
  const [isLoading, setIsLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);

  const [currentPage, setCurrentPage] = useState<number>(1);
  const [totalPages, setTotalPages] = useState<number>(0);
  const [totalItems, setTotalItems] = useState<number>(0);

  // Basic filters - can be expanded
  const [chassisFilter, setChassisFilter] = useState<string>('');
  const [techBaseFilter, setTechBaseFilter] = useState<string>(''); // e.g., "Inner Sphere", "Clan"
  const [eraFilter, setEraFilter] = useState<string>(''); // e.g., "Succession Wars", "Clan Invasion"
  const [massMinFilter, setMassMinFilter] = useState<string>('');
  const [massMaxFilter, setMassMaxFilter] = useState<string>('');


  const fetchUnits = useCallback(async (page: number, queryParams: URLSearchParams) => {
    setIsLoading(true);
    setError(null);
    try {
      // Assuming API uses query params like /api/units?page=1&limit=10&chassis=Warhammer...
      // The API needs to be designed to handle these.
      // For now, the API created in previous steps doesn't support pagination or filtering.
      // This frontend code anticipates such features.
      const limit = 10; // Or make this configurable
      queryParams.set('page', page.toString());
      queryParams.set('limit', limit.toString());

      const response = await fetch(`/api/units?${queryParams.toString()}`);
      if (!response.ok) {
        throw new Error(`API Error: ${response.status} ${response.statusText}`);
      }
      // Assuming API returns PaginatedResponse<BasicUnit> or similar
      // For now, our simple API returns BasicUnit[] directly and no pagination info.
      // We'll mock pagination for now.
      const data: BasicUnit[] | PaginatedResponse<BasicUnit> = await response.json();

      if (Array.isArray(data)) { // Simple API, no pagination info
        setUnits(data);
        setTotalPages(Math.ceil(data.length / limit)); // Mock total pages if API doesn't send
        setTotalItems(data.length); // Mock total items
        if (data.length === 0 && page > 1) setCurrentPage(1); // Reset page if current page becomes empty
      } else { // API sends pagination info
        setUnits(data.items);
        setTotalPages(data.totalPages);
        setCurrentPage(data.currentPage);
        setTotalItems(data.totalItems);
      }

    } catch (err) {
      setError(err instanceof Error ? err.message : 'An unknown error occurred');
      setUnits([]); // Clear units on error
    } finally {
      setIsLoading(false);
    }
  }, []); // No dependencies means this function is created once

  useEffect(() => {
    const queryParams = new URLSearchParams();
    if (chassisFilter) queryParams.set('chassis', chassisFilter);
    if (techBaseFilter) queryParams.set('tech_base', techBaseFilter);
    if (eraFilter) queryParams.set('era', eraFilter);
    if (massMinFilter) queryParams.set('mass_min', massMinFilter);
    if (massMaxFilter) queryParams.set('mass_max', massMaxFilter);

    fetchUnits(currentPage, queryParams);
  }, [currentPage, chassisFilter, techBaseFilter, eraFilter, massMinFilter, massMaxFilter, fetchUnits]);

  const handlePageChange = (page: number) => {
    setCurrentPage(page);
  };

  const handleFilterSubmit = (event: React.FormEvent) => {
    event.preventDefault();
    setCurrentPage(1); // Reset to first page on new filter application
    // The useEffect will trigger fetchUnits due to filter state changes
    // To be more explicit, one could call fetchUnits directly here too:
    // const queryParams = new URLSearchParams();
    // if (chassisFilter) queryParams.set('chassis', chassisFilter); ...
    // fetchUnits(1, queryParams);
  };

  if (isLoading) {
    return <div className="text-center py-10">Loading units...</div>;
  }

  if (error) {
    return <div className="text-center py-10 text-red-500">Error loading units: {error}</div>;
  }

  return (
    <div>
      <form onSubmit={handleFilterSubmit} className="p-4 mb-6 bg-gray-100 rounded-lg shadow">
        <div className="grid grid-cols-1 md:grid-cols-3 lg:grid-cols-5 gap-4">
          <input
            type="text"
            placeholder="Chassis (e.g., Warhammer)"
            value={chassisFilter}
            onChange={(e) => setChassisFilter(e.target.value)}
            className="p-2 border rounded"
          />
          <input
            type="text"
            placeholder="Tech Base (e.g., Clan)"
            value={techBaseFilter}
            onChange={(e) => setTechBaseFilter(e.target.value)}
            className="p-2 border rounded"
          />
          <input
            type="text"
            placeholder="Era (e.g., Succession Wars)"
            value={eraFilter}
            onChange={(e) => setEraFilter(e.target.value)}
            className="p-2 border rounded"
          />
          <input
            type="number"
            placeholder="Min Mass"
            value={massMinFilter}
            onChange={(e) => setMassMinFilter(e.target.value)}
            className="p-2 border rounded"
          />
          <input
            type="number"
            placeholder="Max Mass"
            value={massMaxFilter}
            onChange={(e) => setMassMaxFilter(e.target.value)}
            className="p-2 border rounded"
          />
        </div>
        <div className="mt-4 text-center">
          <button type="submit" className="px-6 py-2 bg-green-500 text-white rounded hover:bg-green-600">
            Apply Filters
          </button>
        </div>
      </form>

      {units.length === 0 ? (
        <div className="text-center py-10">No units found.</div>
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
