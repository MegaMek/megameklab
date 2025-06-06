import React from 'react';
import Link from 'next/link';
import { BasicUnit } from '../../types'; // Adjusted path if types are in battletech-editor-app/types

interface UnitListItemProps {
  unit: BasicUnit;
}

const UnitListItem: React.FC<UnitListItemProps> = ({ unit }) => {
  // Defensive checks for potentially missing data, though BasicUnit defines them
  const id = unit.id || 'unknown';
  const chassis = unit.chassis || 'N/A';
  const model = unit.model || 'N/A';
  const mass = unit.mass || 0;
  const era = unit.era || 'N/A';
  const tech_base = unit.tech_base || 'N/A';
  const role = unit.role || 'N/A';

  return (
    <Link href={`/units/${id}`} legacyBehavior>
      <a className="block p-4 mb-4 bg-white shadow-md rounded-lg hover:bg-gray-50 transition-colors">
        <h3 className="text-xl font-semibold text-blue-600">{chassis} {model}</h3>
        <div className="grid grid-cols-2 gap-x-4 text-sm text-gray-700 mt-2">
          <p><strong>Mass:</strong> {mass} tons</p>
          <p><strong>Era:</strong> {era}</p>
          <p><strong>Tech:</strong> {tech_base}</p>
          <p><strong>Role:</strong> {role}</p>
        </div>
      </a>
    </Link>
  );
};

export default UnitListItem;
