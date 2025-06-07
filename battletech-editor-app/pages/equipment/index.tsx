import React from 'react';
import Layout from '../../components/common/Layout';
import EquipmentList from '../../components/equipment/EquipmentList';

const EquipmentPage: React.FC = () => {
  return (
    <Layout title="Equipment | BattleTech Editor">
      <div className="container mx-auto px-0 sm:px-4"> {/* Adjusted padding for better control */}
        <h1 className="text-2xl sm:text-3xl font-bold my-6 text-gray-800">Equipment Catalogue</h1>
        <EquipmentList />
      </div>
    </Layout>
  );
};

export default EquipmentPage;
