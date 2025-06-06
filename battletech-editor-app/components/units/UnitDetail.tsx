import React from 'react';
import { FullUnit, ArmorLocation, WeaponOrEquipmentItem, CriticalSlotLocation } from '../../types'; // Adjusted path

interface UnitDetailProps {
  unit: FullUnit | null; // Allow null for loading/error states handled by the page
  isLoading: boolean;
  error?: string | null;
}

const SectionTitle: React.FC<{children: React.ReactNode}> = ({ children }) => (
  <h2 className="text-2xl font-semibold text-gray-700 mt-6 mb-3 border-b-2 pb-1 border-gray-300">{children}</h2>
);

const DataPair: React.FC<{label: string, value?: string | number | null}> = ({ label, value }) => (
  <div className="flex justify-between py-1">
    <span className="font-medium text-gray-600">{label}:</span>
    <span className="text-gray-800">{value !== undefined && value !== null ? value : 'N/A'}</span>
  </div>
);

const UnitDetail: React.FC<UnitDetailProps> = ({ unit, isLoading, error }) => {
  if (isLoading) {
    return <div className="text-center py-10">Loading unit details...</div>;
  }

  if (error) {
    return <div className="text-center py-10 text-red-500">Error loading unit details: {error}</div>;
  }

  if (!unit) {
    return <div className="text-center py-10">No unit data available.</div>;
  }

  const uData = unit.data; // The JSONB content

  return (
    <div className="p-4 sm:p-6 bg-white shadow-lg rounded-lg">
      <header className="mb-6">
        <h1 className="text-4xl font-bold text-blue-700">{uData.chassis || unit.chassis} {uData.model || unit.model}</h1>
        <p className="text-md text-gray-500">MUL ID: {uData.mul_id || unit.mul_id || 'N/A'}</p>
      </header>

      <SectionTitle>Overview</SectionTitle>
      <div className="grid grid-cols-1 md:grid-cols-2 gap-x-6">
        <DataPair label="Mass" value={`${uData.mass || unit.mass || 0} tons`} />
        <DataPair label="Tech Base" value={uData.tech_base || unit.tech_base} />
        <DataPair label="Era" value={uData.era || unit.era} />
        <DataPair label="Rules Level" value={uData.rules_level || unit.rules_level} />
        <DataPair label="Role" value={uData.role?.name || (typeof uData.role === 'string' ? uData.role : unit.role)} />
        <DataPair label="Source" value={uData.source || unit.source} />
        <DataPair label="Configuration" value={uData.config} />
      </div>

      {uData.engine && (
        <>
          <SectionTitle>Propulsion & Structure</SectionTitle>
          <div className="grid grid-cols-1 md:grid-cols-2 gap-x-6">
            <DataPair label="Engine Type" value={`${uData.engine.rating} ${uData.engine.type} (${uData.engine.manufacturer || 'N/A'})`} />
            {uData.movement && <DataPair label="Walking MP" value={uData.movement.walk_mp} />}
            {uData.movement && <DataPair label="Jumping MP" value={uData.movement.jump_mp} />}
            {uData.structure && <DataPair label="Internal Structure" value={`${uData.structure.type} (${uData.structure.manufacturer || 'N/A'})`} />}
            {uData.myomer && <DataPair label="Myomer" value={`${uData.myomer.type} (${uData.myomer.manufacturer || 'N/A'})`} />}
          </div>
        </>
      )}

      {uData.heat_sinks && (
        <>
          <SectionTitle>Heat Management</SectionTitle>
          <DataPair label="Heat Sinks" value={`${uData.heat_sinks.count} ${uData.heat_sinks.type} (Dissipation: ${uData.heat_sinks.dissipation_per_sink || uData.heat_sinks.count})`} />
        </>
      )}

      {uData.armor && uData.armor.locations && uData.armor.locations.length > 0 && (
        <>
          <SectionTitle>Armor ({uData.armor.type})</SectionTitle>
          <div className="overflow-x-auto">
            <table className="min-w-full divide-y divide-gray-200">
              <thead className="bg-gray-50">
                <tr>
                  <th className="px-4 py-2 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Location</th>
                  <th className="px-4 py-2 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Armor</th>
                  {uData.armor.locations.some(loc => loc.rear_armor_points !== undefined && loc.rear_armor_points > 0) && (
                    <th className="px-4 py-2 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Rear Armor</th>
                  )}
                </tr>
              </thead>
              <tbody className="bg-white divide-y divide-gray-200">
                {uData.armor.locations.map((loc: ArmorLocation) => (
                  <tr key={loc.location}>
                    <td className="px-4 py-2 whitespace-nowrap text-sm font-medium text-gray-900">{loc.location}</td>
                    <td className="px-4 py-2 whitespace-nowrap text-sm text-gray-500">{loc.armor_points}</td>
                    {uData.armor.locations.some(l => l.rear_armor_points !== undefined && l.rear_armor_points > 0) && (
                      <td className="px-4 py-2 whitespace-nowrap text-sm text-gray-500">{loc.rear_armor_points || '-'}</td>
                    )}
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        </>
      )}

      {uData.weapons_and_equipment && uData.weapons_and_equipment.length > 0 && (
        <>
          <SectionTitle>Weapons and Equipment</SectionTitle>
          <div className="space-y-3">
            {uData.weapons_and_equipment.map((item: WeaponOrEquipmentItem, index: number) => (
              <div key={index} className="p-3 bg-gray-50 rounded-md shadow-sm">
                <p className="font-semibold text-gray-800">{item.item_name} ({item.item_type})</p>
                <p className="text-sm text-gray-600">Location: {item.location} {item.rear_facing ? '(Rear)' : ''} {item.turret_mounted ? '(Turret)' : ''}</p>
                {/* Add more details like damage, range if available in item */}
              </div>
            ))}
          </div>
        </>
      )}

      {/* Simplified Criticals Display - a full criticals table is more complex */}
      {uData.criticals && uData.criticals.length > 0 && (
        <>
          <SectionTitle>Critical Locations</SectionTitle>
          <div className="space-y-2">
            {uData.criticals.map((critLoc: CriticalSlotLocation) => (
              <div key={critLoc.location}>
                <h4 className="font-semibold text-md text-gray-700">{critLoc.location}:</h4>
                <ul className="list-disc list-inside pl-4 text-sm text-gray-600">
                  {critLoc.slots.map((slot, i) => slot !== '-Empty-' && <li key={i}>{slot}</li>)}
                </ul>
              </div>
            ))}
          </div>
        </>
      )}

      {uData.quirks && uData.quirks.length > 0 && (
        <>
          <SectionTitle>Quirks</SectionTitle>
          <ul className="list-disc list-inside pl-4 space-y-1 text-sm text-gray-700">
            {uData.quirks.map((quirk: string | { name: string }, index: number) => (
              <li key={index}>{typeof quirk === 'string' ? quirk : quirk.name}</li>
            ))}
          </ul>
        </>
      )}

      {uData.fluff_text && Object.keys(uData.fluff_text).length > 0 && (
        <>
          <SectionTitle>Fluff</SectionTitle>
          {Object.entries(uData.fluff_text).map(([key, value]) => value && (
            <div key={key} className="mt-3">
              <h4 className="font-semibold text-md capitalize text-gray-700">{key.replace(/_/g, ' ')}</h4>
              <p className="text-sm text-gray-600 whitespace-pre-wrap">{value}</p>
            </div>
          ))}
        </>
      )}
    </div>
  );
};

export default UnitDetail;
