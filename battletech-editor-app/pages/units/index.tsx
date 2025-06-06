import React from 'react';
import Layout from '../../components/common/Layout';
import UnitList from '../../components/units/UnitList';

const UnitsPage: React.FC = () => {
  return (
    <Layout title="Units | BattleTech Editor">
      <h1 className="text-3xl font-bold mb-6 text-gray-800">BattleMech Units</h1>
      <UnitList />
    </Layout>
  );
};

export default UnitsPage;
