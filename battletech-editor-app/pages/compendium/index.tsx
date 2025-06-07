// battletech-editor-app/pages/compendium/index.tsx
import React, { useState } from 'react';
import Layout from '../../components/common/Layout';
import UnitCategoryNav from '../../components/compendium/UnitCategoryNav';
import UnitFilters, { UnitFilterState } from '../../components/compendium/UnitFilters';
import UnitCompendiumList from '../../components/compendium/UnitCompendiumList';
import EquipmentCategoryNav from '../../components/compendium/EquipmentCategoryNav';
import EquipmentFilters, { EquipmentFilterState } from '../../components/compendium/EquipmentFilters';
import EquipmentCompendiumList from '../../components/compendium/EquipmentCompendiumList';

const initialUnitFilters: UnitFilterState = { searchTerm: '', weightClass: '', techBase: '', era: '' };
const initialEquipmentFilters: EquipmentFilterState = { searchTerm: '', techBase: '', era: '' };

const CompendiumPage: React.FC = () => {
  const [activeView, setActiveView] = useState<'units' | 'equipment'>('units');

  const [selectedUnitCategory, setSelectedUnitCategory] = useState<string | null>(null);
  const [currentUnitFilters, setCurrentUnitFilters] = useState<UnitFilterState>(initialUnitFilters);

  const [selectedEquipmentCategory, setSelectedEquipmentCategory] = useState<string | null>(null);
  const [currentEquipmentFilters, setCurrentEquipmentFilters] = useState<EquipmentFilterState>(initialEquipmentFilters);

  const handleUnitFiltersApply = (filters: UnitFilterState) => setCurrentUnitFilters(filters);
  const handleEquipmentFiltersApply = (filters: EquipmentFilterState) => setCurrentEquipmentFilters(filters);

  return (
    <Layout pageTitle="Compendium">
      <div className="container mx-auto p-4">
        <div className="flex border-b mb-4">
          <button className={`py-2 px-4 ${activeView === 'units' ? 'border-b-2 border-blue-500' : ''}`} onClick={() => setActiveView('units')}>Units</button>
          <button className={`py-2 px-4 ${activeView === 'equipment' ? 'border-b-2 border-blue-500' : ''}`} onClick={() => setActiveView('equipment')}>Equipment</button>
        </div>
        {activeView === 'units' && (
          <section id="units-compendium">
            <h2 className="text-2xl font-bold mb-4">Units Compendium</h2>
            <div className="md:flex md:space-x-4">
              <div className="md:w-1/4">
                <UnitCategoryNav
                  onSelectCategory={setSelectedUnitCategory}
                  selectedCategory={selectedUnitCategory}
                />
              </div>
              <div className="md:w-3/4">
                <UnitFilters onFiltersApply={handleUnitFiltersApply} />
                <UnitCompendiumList
                  filters={currentUnitFilters}
                  selectedCategory={selectedUnitCategory}
                />
              </div>
            </div>
          </section>
        )}

        {activeView === 'equipment' && (
          <section id="equipment-compendium">
            <h2 className="text-2xl font-bold mb-4">Equipment Compendium</h2>
            <div className="md:flex md:space-x-4">
              <div className="md:w-1/4">
                <EquipmentCategoryNav
                  onSelectCategory={setSelectedEquipmentCategory}
                  selectedCategory={selectedEquipmentCategory}
                />
              </div>
              <div className="md:w-3/4">
                <EquipmentFilters onFiltersApply={handleEquipmentFiltersApply} />
                <EquipmentCompendiumList
                  filters={currentEquipmentFilters}
                  selectedEquipmentCategory={selectedEquipmentCategory}
                />
              </div>
            </div>
          </section>
        )}
      </div>
    </Layout>
  );
};

export default CompendiumPage;
