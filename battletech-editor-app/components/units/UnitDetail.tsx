import React, { useState } from 'react';
import { FullUnit, ArmorLocation, WeaponOrEquipmentItem, CriticalSlotLocation, FluffText, Quirks } from '../../types';

interface UnitDetailProps {
  unit: FullUnit | null;
  isLoading: boolean;
  error?: string | null;
}

const SectionTitle: React.FC<{children: React.ReactNode; className?: string}> = ({ children, className }) => (
  <h3 className={`text-xl font-semibold text-gray-700 mt-4 mb-2 pb-1 border-b border-gray-200 ${className}`}>{children}</h3>
);

const DataPair: React.FC<{label: string, value?: string | number | null}> = ({ label, value }) => (
  <div className="flex justify-between py-1 text-sm">
    <span className="font-medium text-gray-600">{label}:</span>
    <span className="text-gray-800 break-words">{value !== undefined && value !== null ? String(value) : 'N/A'}</span>
  </div>
);

type TabName = "Overview" | "Armament" | "Criticals" | "Armor" | "Fluff";

const UnitDetail: React.FC<UnitDetailProps> = ({ unit, isLoading, error }) => {
  const [activeTab, setActiveTab] = useState<TabName>("Overview");

  if (isLoading) {
    return <div className="text-center py-10">Loading unit details...</div>;
  }
  if (error) {
    return <div className="text-center py-10 text-red-500">Error loading unit details: {error}</div>;
  }
  if (!unit) {
    return <div className="text-center py-10">No unit data available.</div>;
  }

  // Prefer data from the 'data' JSONB field, but fallback to root properties if necessary
  const uData = unit.data || {};
  const chassis = uData.chassis || unit.chassis;
  const model = uData.model || unit.model;
  const mass = uData.mass || unit.mass;
  const tech_base = uData.tech_base || unit.tech_base;
  const era = uData.era || unit.era;
  const rules_level = uData.rules_level || unit.rules_level;
  const role = uData.role?.name || (typeof uData.role === 'string' ? uData.role : unit.role); // Handle object or string role
  const source = uData.source || unit.source;
  const mul_id = uData.mul_id || unit.mul_id;


  const renderOverviewTab = () => (
    <>
      <SectionTitle>General</SectionTitle>
      <div className="grid grid-cols-1 md:grid-cols-2 gap-x-6 gap-y-1">
        <DataPair label="Mass" value={`${mass || 0} tons`} />
        <DataPair label="Tech Base" value={tech_base} />
        <DataPair label="Era" value={era} />
        <DataPair label="Rules Level" value={rules_level} />
        <DataPair label="Role" value={role} />
        <DataPair label="Source" value={source} />
        <DataPair label="Configuration" value={uData.config} />
        <DataPair label="MUL ID" value={mul_id || 'N/A'} />
      </div>

      {uData.engine && (
        <>
          <SectionTitle>Propulsion & Structure</SectionTitle>
          <div className="grid grid-cols-1 md:grid-cols-2 gap-x-6 gap-y-1">
            <DataPair label="Engine" value={`${uData.engine.rating} ${uData.engine.type} (${uData.engine.manufacturer || 'N/A'})`} />
            {uData.movement && <DataPair label="Walking MP" value={uData.movement.walk_mp} />}
            {uData.movement && <DataPair label="Running MP" value={uData.movement.run_mp} /> }
            {uData.movement && <DataPair label="Jumping MP" value={uData.movement.jump_mp} />}
            {uData.structure && <DataPair label="Internal Structure" value={`${uData.structure.type} (${uData.structure.manufacturer || 'N/A'})`} />}
            {uData.myomer && <DataPair label="Myomer" value={`${uData.myomer.type} (${uData.myomer.manufacturer || 'N/A'})`} />}
          </div>
        </>
      )}
      {uData.heat_sinks && (
        <>
          <SectionTitle>Heat Management</SectionTitle>
          <DataPair
            label="Heat Sinks"
            value={`${uData.heat_sinks.count} ${uData.heat_sinks.type || ''} (Dissipating: ${uData.heat_sinks.dissipation_per_sink || uData.heat_sinks.count * (uData.heat_sinks.type?.includes("Double") ? 2 : 1) })`}
          />
        </>
      )}
      {uData.cockpit && (
         <>
          <SectionTitle>Cockpit</SectionTitle>
          <DataPair label="Cockpit Type" value={uData.cockpit.type || 'Standard'} />
          {uData.cockpit.manufacturer && <DataPair label="Manufacturer" value={uData.cockpit.manufacturer} />}
         </>
      )}
       {uData.gyro && (
         <>
          <SectionTitle>Gyro</SectionTitle>
          <DataPair label="Gyro Type" value={uData.gyro.type || 'Standard'} />
          {uData.gyro.manufacturer && <DataPair label="Manufacturer" value={uData.gyro.manufacturer} />}
         </>
      )}
      {uData.quirks && uData.quirks.length > 0 && (
        <>
          <SectionTitle>Quirks</SectionTitle>
          <ul className="list-disc list-inside pl-4 space-y-1 text-sm text-gray-700">
            {uData.quirks.map((quirk: Quirks, index: number) => (
              <li key={index}>{typeof quirk === 'string' ? quirk : quirk.Name}</li>
            ))}
          </ul>
        </>
      )}
    </>
  );

  const renderArmamentTab = () => (
    <>
      <SectionTitle>Weapons and Equipment</SectionTitle>
      {(!uData.weapons_and_equipment || uData.weapons_and_equipment.length === 0) && <p className="text-sm text-gray-500">No armament or significant equipment listed.</p>}
      <div className="space-y-3 mt-2">
        {uData.weapons_and_equipment?.map((item: WeaponOrEquipmentItem, index: number) => (
          <div key={index} className="p-3 bg-gray-50 rounded-md shadow-sm border border-gray-200">
            <p className="font-semibold text-gray-800">{item.item_name} ({item.item_type})</p>
            <p className="text-sm text-gray-600">Location: {item.location} {item.rear_facing ? '(Rear)' : ''} {item.turret_mounted ? '(Turret)' : ''}</p>
            {item.damage && <DataPair label="Damage" value={typeof item.damage === 'object' ? JSON.stringify(item.damage) : String(item.damage)} />}
            {item.range && item.range.short !== undefined && <DataPair label="Range (S/M/L/E)" value={`${item.range.short}/${item.range.medium}/${item.range.long}${item.range.extreme !== undefined ? '/'+item.range.extreme : ''}`} />}
            {item.ammo_per_ton && <DataPair label="Ammo/Ton" value={item.ammo_per_ton} />}
            {item.tons && <DataPair label="Tons" value={item.tons} />}
            {item.crits && <DataPair label="Crits" value={item.crits} />}
            {/* TODO: Display more item details like modes if available in item object */}
          </div>
        ))}
      </div>
    </>
  );

  const renderCriticalsTab = () => (
    <>
      <SectionTitle>Critical Locations</SectionTitle>
      {(!uData.criticals || uData.criticals.length === 0) && <p className="text-sm text-gray-500">Critical slot information not available.</p>}
      <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
        {uData.criticals?.map((critLoc: CriticalSlotLocation) => (
          <div key={critLoc.location}>
            <h4 className="font-semibold text-md text-gray-700 bg-gray-100 px-2 py-1 rounded-t-md border-t border-x border-gray-200">{critLoc.location}:</h4>
            <ul className="list-none list-inside text-sm text-gray-600 bg-white p-2 rounded-b-md border border-gray-200 shadow-sm">
              {critLoc.slots.map((slot, i) => (
                <li key={i} className={`py-0.5 px-1 ${slot && slot !== '-Empty-' ? '' : 'text-gray-400'}`}>
                  {`${i + 1}: ${slot || '-Empty-'}`}
                </li>
              ))}
            </ul>
          </div>
        ))}
      </div>
    </>
  );

  const renderArmorTab = () => (
    <>
      <SectionTitle>Armor Distribution ({uData.armor?.type || 'N/A'})</SectionTitle>
      {(!uData.armor || !uData.armor.locations || uData.armor.locations.length === 0) && <p className="text-sm text-gray-500">Armor information not available.</p>}
      {uData.armor?.locations && uData.armor.locations.length > 0 && (
        <div className="overflow-x-auto mt-2">
          <table className="min-w-full divide-y divide-gray-200 border border-gray-200">
            <thead className="bg-gray-100">
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
                    <td className="px-4 py-2 whitespace-nowrap text-sm text-gray-500">{loc.rear_armor_points !== null && loc.rear_armor_points !== undefined ? loc.rear_armor_points : '-'}</td>
                  )}
                </tr>
              ))}
            </tbody>
            {uData.armor.total_armor_points && (
                <tfoot>
                    <tr className="bg-gray-50 font-semibold">
                        <td className="px-4 py-2 text-sm text-gray-700">Total Armor</td>
                        <td className="px-4 py-2 text-sm text-gray-700">{uData.armor.total_armor_points}</td>
                        {uData.armor.locations.some(l => l.rear_armor_points !== undefined && l.rear_armor_points > 0) && <td className="px-4 py-2"></td>}
                    </tr>
                </tfoot>
            )}
          </table>
        </div>
      )}
    </>
  );

  const renderFluffTab = () => (
    <>
      <SectionTitle>History & Background</SectionTitle>
      {(!uData.fluff_text || Object.keys(uData.fluff_text).length === 0) && <p className="text-sm text-gray-500">No fluff or historical information available.</p>}
      {uData.fluff_text && Object.entries(uData.fluff_text as FluffText).map(([key, value]) => value && (
        <div key={key} className="mt-3">
          <h4 className="font-semibold text-md capitalize text-gray-700">{key.replace(/_/g, ' ')}</h4>
          <p className="text-sm text-gray-600 whitespace-pre-wrap leading-relaxed">{String(value)}</p>
        </div>
      ))}
    </>
  );

  const tabs: { name: TabName; label: string }[] = [
    { name: "Overview", label: "Overview & Stats" },
    { name: "Armament", label: "Armament & Equipment" },
    { name: "Criticals", label: "Criticals" },
    { name: "Armor", label: "Armor Distribution" },
    { name: "Fluff", label: "History & Fluff" },
  ];

  return (
    <div className="p-4 sm:p-6 bg-white shadow-lg rounded-lg">
      <header className="mb-4">
        <h1 className="text-3xl font-bold text-blue-700">{chassis} {model}</h1>
      </header>

      <div className="border-b border-gray-200 mb-4">
        <nav className="-mb-px flex space-x-2 sm:space-x-4 overflow-x-auto" aria-label="Tabs">
          {tabs.map((tab) => (
            <button
              key={tab.name}
              onClick={() => setActiveTab(tab.name)}
              className={`${
                activeTab === tab.name
                  ? 'border-blue-500 text-blue-600'
                  : 'border-transparent text-gray-500 hover:text-gray-700 hover:border-gray-300'
              } whitespace-nowrap py-3 px-2 sm:px-4 border-b-2 font-medium text-sm focus:outline-none focus:ring-2 focus:ring-blue-300 focus:ring-opacity-50 rounded-t-md`}
            >
              {tab.label}
            </button>
          ))}
        </nav>
      </div>

      <div className="tab-content min-h-[200px]"> {/* Added min-height to prevent layout shifts */}
        {activeTab === "Overview" && renderOverviewTab()}
        {activeTab === "Armament" && renderArmamentTab()}
        {activeTab === "Criticals" && renderCriticalsTab()}
        {activeTab === "Armor" && renderArmorTab()}
        {activeTab === "Fluff" && renderFluffTab()}
      </div>
    </div>
  );
};

export default UnitDetail;
