import React from 'react';
import { FullEquipment, EquipmentData } from '../../types'; // Adjusted path

interface EquipmentDetailProps {
  equipment: FullEquipment | null;
  isLoading: boolean;
  error?: string | null;
}

const SectionTitle: React.FC<{children: React.ReactNode; className?: string}> = ({ children, className }) => (
  <h2 className={`text-xl font-semibold text-gray-700 mt-6 mb-3 border-b pb-1 border-gray-300 ${className}`}>{children}</h2>
);

const DataPair: React.FC<{label: string, value?: string | number | boolean | null}> = ({ label, value }) => (
  <div className="flex justify-between py-1.5 text-sm border-b border-gray-100 last:border-b-0">
    <span className="font-medium text-gray-600">{label}:</span>
    <span className="text-gray-800 break-words text-right">{value !== undefined && value !== null && value !== '' ? String(value) : 'N/A'}</span>
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

  const eqData = equipment.data as EquipmentData || {};
  const { id, name, type, tech_base, era, source, ...otherRootFields } = equipment;

  // Fields explicitly handled in "Specifications" or part of "Basic Information"
  const handledSpecKeys = [
    'tons', 'slots', 'cost', 'battle_value', 'battlevalue',
    'weapon_type', 'damage', 'heatmap', 'range', 'ammo_per_shot', 'shots',
    'manufacturer', 'model', 'tech_rating', 'legality', 'introduced', 'specials',
    'name', 'type', 'tech_base', 'era', 'source', 'data', 'id', 'mul_id', 'rules_level', 'role', 'config' // also exclude root-like fields if they appear in data
  ];


  return (
    <div className="p-4 sm:p-6 bg-white shadow-lg rounded-lg max-w-2xl mx-auto">
      <header className="mb-6 text-center">
        <h1 className="text-3xl font-bold text-green-700">{name}</h1>
        {type && <p className="text-md text-gray-500">Type: {type}</p>}
      </header>

      <SectionTitle>Basic Information</SectionTitle>
      <div className="grid grid-cols-1 md:grid-cols-2 gap-x-6">
        <DataPair label="Tech Base" value={tech_base} />
        <DataPair label="Era" value={era} />
        <DataPair label="Source" value={source} />
        {Object.entries(otherRootFields).map(([key, value]) => {
          if (['id', 'data'].includes(key) || value === null || value === undefined) return null;
           return <DataPair key={key} label={key.replace(/_/g, ' ').replace(/\b\w/g, l => l.toUpperCase())} value={value as string | number} />;
        })}
      </div>

      {(Object.keys(eqData).length > 0 || equipment.rules_level || equipment.mul_id) && ( // Check if there's any data to show in specs
        <SectionTitle>Specifications</SectionTitle>
      )}
      <div className="grid grid-cols-1 md:grid-cols-2 gap-x-6">
        {/* Display root-level fields not shown in Basic Info, if they exist */}
        {equipment.rules_level && <DataPair label="Rules Level" value={equipment.rules_level} />}
        {equipment.mul_id && <DataPair label="MUL ID" value={equipment.mul_id} />}

        {/* Common stats from eqData */}
        {eqData.tons !== undefined && <DataPair label="Tonnage" value={eqData.tons} />}
        {eqData.slots !== undefined && <DataPair label="Critical Slots" value={eqData.slots} />}
        {eqData.cost !== undefined && <DataPair label="Cost (C-Bills)" value={eqData.cost?.toLocaleString()} />}
        {(eqData.battle_value !== undefined || eqData.battlevalue !== undefined) && (
            <DataPair label="Battle Value (BV)" value={(eqData.battle_value || eqData.battlevalue)?.toLocaleString()} />
        )}

        {/* Weapon-specific stats from eqData */}
        {eqData.weapon_type && <DataPair label="Weapon Type" value={eqData.weapon_type} />}
        {eqData.damage !== undefined && <DataPair label="Damage" value={String(eqData.damage)} />}
        {eqData.heatmap !== undefined && <DataPair label="Heat" value={eqData.heatmap} />}
        {eqData.range?.short !== undefined && (
          <DataPair
            label="Range (S/M/L/E)"
            value={`${eqData.range.short}/${eqData.range.medium}/${eqData.range.long}${eqData.range.extreme ? `/${eqData.range.extreme}` : ''}${eqData.range.minimum ? ` (Min: ${eqData.range.minimum})` : ''}`}
          />
        )}
        {eqData.ammo_per_shot !== undefined && <DataPair label="Ammo Per Shot" value={eqData.ammo_per_shot} />}
        {eqData.shots !== undefined && <DataPair label="Shots (Total Ammo)" value={eqData.shots} />}

        {/* Other specific fields from eqData */}
        {eqData.manufacturer && <DataPair label="Manufacturer" value={eqData.manufacturer} />}
        {eqData.model && <DataPair label="Model (Data)" value={eqData.model} />}
        {eqData.tech_rating && <DataPair label="Tech Rating" value={eqData.tech_rating} />}
        {eqData.legality?.all && <DataPair label="Legality (All)" value={eqData.legality.all} />}
        {eqData.introduced && <DataPair label="Introduced (Year)" value={eqData.introduced} />}
        {eqData.specials && (
            <DataPair
                label="Specials"
                value={Array.isArray(eqData.specials) ? eqData.specials.join(', ') : String(eqData.specials)}
            />
        )}
      </div>

      {/* Fallback for other data fields not explicitly handled */}
      {Object.entries(eqData).filter(([key]) => !handledSpecKeys.includes(key.toLowerCase()) && eqData[key as keyof EquipmentData] !== null && eqData[key as keyof EquipmentData] !== undefined).length > 0 && (
        <>
          <SectionTitle className="mt-8">Additional Data</SectionTitle>
          <div className="bg-gray-50 p-3 rounded shadow-inner text-sm space-y-1">
            {Object.entries(eqData).map(([key, value]) => {
              if (handledSpecKeys.includes(key.toLowerCase()) || value === null || value === undefined) return null;

              let displayValue = '';
              if (typeof value === 'object') {
                displayValue = JSON.stringify(value, null, 2); // Pretty print objects
                return (
                  <div key={key} className="py-1.5 text-sm">
                    <span className="font-medium text-gray-600">{key.replace(/_/g, ' ').replace(/\b\w/g, l => l.toUpperCase())}:</span>
                    <pre className="text-gray-800 break-words whitespace-pre-wrap bg-white p-2 rounded mt-1 shadow-sm text-xs">{displayValue}</pre>
                  </div>
                );
              } else {
                displayValue = String(value);
                return <DataPair key={key} label={key.replace(/_/g, ' ').replace(/\b\w/g, l => l.toUpperCase())} value={displayValue} />;
              }
            })}
          </div>
        </>
      )}
       {/* Raw JSON for debugging, can be removed or made collapsible
       {process.env.NODE_ENV === 'development' && equipment.data && Object.keys(equipment.data).length > 0 && (
        <>
          <SectionTitle className="mt-8">Raw JSON Data (Debug)</SectionTitle>
          <div className="bg-gray-900 text-white p-4 rounded text-xs overflow-x-auto">
              <pre>{JSON.stringify(equipment.data, null, 2)}</pre>
          </div>
        </>
       )}
      */}
    </div>
  );
};

export default EquipmentDetail;
