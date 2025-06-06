import React from 'react';
import { FullEquipment } from '../../types'; // Adjusted path

interface EquipmentDetailProps {
  equipment: FullEquipment | null;
  isLoading: boolean;
  error?: string | null;
}

const DataPair: React.FC<{label: string, value?: string | number | null | boolean}> = ({ label, value }) => (
  <div className="flex justify-between py-1 border-b border-gray-200">
    <span className="font-medium text-gray-600">{label}:</span>
    <span className="text-gray-800">{value !== undefined && value !== null ? String(value) : 'N/A'}</span>
  </div>
);

const EquipmentDetail: React.FC<EquipmentDetailProps> = ({ equipment, isLoading, error }) => {
  if (isLoading) {
    return <div className="text-center py-10">Loading equipment details...</div>;
  }
  if (error) {
    return <div className="text-center py-10 text-red-500">Error: {error}</div>;
  }
  if (!equipment) {
    return <div className="text-center py-10">No equipment data available.</div>;
  }

  const { data, ...rest } = equipment; // Separate the JSONB data from top-level fields

  return (
    <div className="p-4 sm:p-6 bg-white shadow-lg rounded-lg">
      <header className="mb-6">
        <h1 className="text-3xl font-bold text-green-700">{equipment.name}</h1>
      </header>

      <h2 className="text-xl font-semibold text-gray-700 mt-4 mb-2">Basic Information</h2>
      <div className="grid grid-cols-1 md:grid-cols-2 gap-x-6">
        {Object.entries(rest).map(([key, value]) => {
          if (key === 'id') return null; // Don't show id field here
          return <DataPair key={key} label={key.replace(/_/g, ' ').replace(/\b\w/g, l => l.toUpperCase())} value={value as string | number} />;
        })}
      </div>

      {data && Object.keys(data).length > 0 && (
        <>
          <h2 className="text-xl font-semibold text-gray-700 mt-6 mb-2">Detailed Specifications (from JSON data)</h2>
          <div className="bg-gray-50 p-4 rounded shadow-inner">
            <pre className="whitespace-pre-wrap text-sm">
              {JSON.stringify(data, null, 2)}
            </pre>
          </div>
        </>
      )}
    </div>
  );
};

export default EquipmentDetail;
