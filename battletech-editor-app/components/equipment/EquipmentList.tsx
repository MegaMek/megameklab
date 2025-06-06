import React, { useState, useEffect, useCallback } from 'react';
import EquipmentListItem from './EquipmentListItem';
import Pagination from '../common/Pagination';
import { BasicEquipment, PaginatedResponse } from '../../types'; // Adjusted path

const EquipmentList: React.FC = () => {
  const [equipment, setEquipment] = useState<BasicEquipment[]>([]);
  const [isLoading, setIsLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);

  const [currentPage, setCurrentPage] = useState<number>(1);
  const [totalPages, setTotalPages] = useState<number>(0);

  // Basic filters
  const [nameFilter, setNameFilter] = useState<string>('');
  const [typeFilter, setTypeFilter] = useState<string>(''); // e.g., "Weapon", "Ammo", "Component"
  const [techBaseFilter, setTechBaseFilter] = useState<string>('');


  const fetchEquipment = useCallback(async (page: number, queryParams: URLSearchParams) => {
    setIsLoading(true);
    setError(null);
    try {
      const limit = 15; // Or make this configurable
      queryParams.set('page', page.toString());
      queryParams.set('limit', limit.toString());

      // As with UnitList, assumes API supports these filters.
      // The current API from previous steps does not. This is for conceptual design.
      const response = await fetch(`/api/equipment?${queryParams.toString()}`);
      if (!response.ok) {
        throw new Error(`API Error: ${response.status} ${response.statusText}`);
      }
      // Assuming API returns PaginatedResponse<BasicEquipment> or similar
      // Our simple API returns BasicEquipment[] directly.
      const data: BasicEquipment[] | PaginatedResponse<BasicEquipment> = await response.json();

      if (Array.isArray(data)) { // Simple API
        setEquipment(data);
        setTotalPages(Math.ceil(data.length / limit));
         if (data.length === 0 && page > 1) setCurrentPage(1);
      } else { // Paginated API
        setEquipment(data.items);
        setTotalPages(data.totalPages);
        setCurrentPage(data.currentPage);
      }

    } catch (err) {
      setError(err instanceof Error ? err.message : 'An unknown error occurred');
      setEquipment([]);
    } finally {
      setIsLoading(false);
    }
  }, []);

  useEffect(() => {
    const queryParams = new URLSearchParams();
    if (nameFilter) queryParams.set('name', nameFilter);
    if (typeFilter) queryParams.set('type', typeFilter);
    if (techBaseFilter) queryParams.set('tech_base', techBaseFilter);

    fetchEquipment(currentPage, queryParams);
  }, [currentPage, nameFilter, typeFilter, techBaseFilter, fetchEquipment]);

  const handlePageChange = (page: number) => {
    setCurrentPage(page);
  };

  const handleFilterSubmit = (event: React.FormEvent) => {
    event.preventDefault();
    setCurrentPage(1); // Reset to first page
    // useEffect will trigger fetchEquipment
  };

  if (isLoading) return <div className="text-center py-10">Loading equipment...</div>;
  if (error) return <div className="text-center py-10 text-red-500">Error: {error}</div>;

  return (
    <div>
      <form onSubmit={handleFilterSubmit} className="p-4 mb-6 bg-gray-100 rounded-lg shadow">
        <div className="grid grid-cols-1 md:grid-cols-3 gap-4">
          <input
            type="text"
            placeholder="Name (e.g., Medium Laser)"
            value={nameFilter}
            onChange={(e) => setNameFilter(e.target.value)}
            className="p-2 border rounded"
          />
          <input
            type="text"
            placeholder="Type (e.g., Weapon)"
            value={typeFilter}
            onChange={(e) => setTypeFilter(e.target.value)}
            className="p-2 border rounded"
          />
          <input
            type="text"
            placeholder="Tech Base (e.g., Clan)"
            value={techBaseFilter}
            onChange={(e) => setTechBaseFilter(e.target.value)}
            className="p-2 border rounded"
          />
        </div>
        <div className="mt-4 text-center">
          <button type="submit" className="px-6 py-2 bg-green-500 text-white rounded hover:bg-green-600">
            Apply Filters
          </button>
        </div>
      </form>

      {equipment.length === 0 ? (
        <p className="text-center">No equipment found.</p>
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
