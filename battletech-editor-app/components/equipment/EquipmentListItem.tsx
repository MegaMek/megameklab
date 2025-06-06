import React from 'react';
import Link from 'next/link';
import { BasicEquipment } from '../../types'; // Adjusted path

interface EquipmentListItemProps {
  equipment: BasicEquipment;
}

const EquipmentListItem: React.FC<EquipmentListItemProps> = ({ equipment }) => {
  const id = equipment.id || 'unknown';
  return (
    <Link href={`/equipment/${id}`} legacyBehavior>
      <a className="block p-4 mb-4 bg-white shadow-md rounded-lg hover:bg-gray-50 transition-colors">
        <h3 className="text-lg font-semibold text-green-600">{equipment.name}</h3>
        <div className="grid grid-cols-2 gap-x-4 text-sm text-gray-700 mt-1">
          <p><strong>Type:</strong> {equipment.type || 'N/A'}</p>
          <p><strong>Tech:</strong> {equipment.tech_base || 'N/A'}</p>
          <p><strong>Era:</strong> {equipment.era || 'N/A'}</p>
        </div>
        {/* Can add more details here if BasicEquipment includes them, e.g., damage, range for weapons */}
      </a>
    </Link>
  );
};

export default EquipmentListItem;
