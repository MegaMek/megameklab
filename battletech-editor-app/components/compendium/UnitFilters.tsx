// battletech-editor-app/components/compendium/UnitFilters.tsx
import React, { useState, useEffect } from 'react';
import { getMetadata } from '../../services/mockApiService';

export interface UnitFilterState {
  searchTerm: string;
  weightClass: string;
  techBase: string;
  era: string;
}

interface UnitFiltersProps {
  onFiltersApply: (filters: UnitFilterState) => void;
}

const UnitFilters: React.FC<UnitFiltersProps> = ({ onFiltersApply }) => {
  const [searchTerm, setSearchTerm] = useState('');
  const [weightClass, setWeightClass] = useState('');
  const [techBase, setTechBase] = useState('');
  const [era, setEra] = useState('');

  const [weightClasses, setWeightClasses] = useState<string[]>([]);
  const [techBases, setTechBases] = useState<string[]>([]);
  const [eras, setEras] = useState<string[]>([]);

  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const fetchData = async () => {
      try {
        setLoading(true);
        const [wcData, tbData, eraData] = await Promise.all([
          getMetadata('/mockdata/mockUnitWeightClasses.json'),
          getMetadata('/mockdata/mockUnitTechBases.json'),
          getMetadata('/mockdata/mockUnitEras.json'),
        ]);

        setWeightClasses(wcData);
        setTechBases(tbData);
        setEras(eraData);
        setError(null);
      } catch (e) {
        const errorMsg = e instanceof Error ? e.message : 'An unknown error occurred';
        setError(errorMsg);
        console.error("Failed to fetch filter data:", errorMsg);
      } finally {
        setLoading(false);
      }
    };
    fetchData();
  }, []);

  const handleApply = () => {
    onFiltersApply({ searchTerm, weightClass, techBase, era });
    console.log('Applying Unit Filters:', { searchTerm, weightClass, techBase, era });
  };

  const handleClear = () => {
    setSearchTerm('');
    setWeightClass('');
    setTechBase('');
    setEra('');
    onFiltersApply({ searchTerm: '', weightClass: '', techBase: '', era: '' });
     console.log('Cleared Unit Filters');
  };

  if (loading) return <p>Loading filters...</p>;
  if (error) return <p className="text-red-500">Error loading filters: {error}</p>;

  return (
    <div className="p-4 bg-gray-200 rounded mb-4">
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-4">
        <div>
          <label htmlFor="searchTerm" className="block text-sm font-medium text-gray-700">Search Name/Model</label>
          <input
            type="text"
            id="searchTerm"
            value={searchTerm}
            onChange={(e) => setSearchTerm(e.target.value)}
            className="mt-1 block w-full p-2 border border-gray-300 rounded-md shadow-sm"
          />
        </div>
        <div>
          <label htmlFor="weightClass" className="block text-sm font-medium text-gray-700">Weight Class</label>
          <select
            id="weightClass"
            value={weightClass}
            onChange={(e) => setWeightClass(e.target.value)}
            className="mt-1 block w-full p-2 border border-gray-300 rounded-md shadow-sm"
          >
            <option value="">All</option>
            {weightClasses.map(wc => <option key={wc} value={wc}>{wc}</option>)}
          </select>
        </div>
        <div>
          <label htmlFor="techBase" className="block text-sm font-medium text-gray-700">Tech Base</label>
          <select
            id="techBase"
            value={techBase}
            onChange={(e) => setTechBase(e.target.value)}
            className="mt-1 block w-full p-2 border border-gray-300 rounded-md shadow-sm"
          >
            <option value="">All</option>
            {techBases.map(tb => <option key={tb} value={tb}>{tb}</option>)}
          </select>
        </div>
        <div>
          <label htmlFor="era" className="block text-sm font-medium text-gray-700">Era</label>
          <select
            id="era"
            value={era}
            onChange={(e) => setEra(e.target.value)}
            className="mt-1 block w-full p-2 border border-gray-300 rounded-md shadow-sm"
          >
            <option value="">All</option>
            {eras.map(er => <option key={er} value={er}>{er}</option>)}
          </select>
        </div>
      </div>
      <div className="mt-4 flex space-x-2">
        <button
          onClick={handleApply}
          className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded"
        >
          Apply Filters
        </button>
         <button
          onClick={handleClear}
          className="bg-gray-500 hover:bg-gray-700 text-white font-bold py-2 px-4 rounded"
        >
          Clear Filters
        </button>
      </div>
    </div>
  );
};

export default UnitFilters;
