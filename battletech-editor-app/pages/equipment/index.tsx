import React from 'react';
import Layout from '../../components/common/Layout';
import EquipmentList from '../../components/equipment/EquipmentList';

const EquipmentPage: React.FC = () => {
  return (
    <Layout title="Equipment | BattleTech Editor">
      <h1 className="text-3xl font-bold mb-6 text-gray-800">Equipment Catalogue</h1>
      <EquipmentList />
    </Layout>
  );
};

export default EquipmentPage;
