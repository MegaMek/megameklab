import React, { useState } from 'react';
import Layout from '../../components/common/Layout';
import UnitList from '../../components/units/UnitList';
import CategoryNavigation from '../../components/common/CategoryNavigation';

const UnitsPage: React.FC = () => {
  const [selectedCategory, setSelectedCategory] = useState<string | null>(null);

  const handleSelectCategory = (category: string | null) => {
    setSelectedCategory(category);
  };

  const unitPageSidebar = (
    <CategoryNavigation
      selectedCategory={selectedCategory}
      onSelectCategory={handleSelectCategory}
    />
  );

  return (
    <Layout title="Units | BattleTech Editor" sidebar={unitPageSidebar}>
      <div className="pt-16 md:pt-0"> {/* Add padding top for mobile if sidebar is fixed and overlapping */}
        <h1 className="text-2xl sm:text-3xl font-bold mb-6 text-gray-800">
          {selectedCategory ? `${selectedCategory} List` : 'All Units List'}
        </h1>
        <UnitList selectedCategory={selectedCategory} />
      </div>
    </Layout>
  );
};

export default UnitsPage;
