import React, { useState, useEffect } from 'react';
import { useRouter } from 'next/router';
import Layout from '../../components/common/Layout';
import EquipmentDetail from '../../components/equipment/EquipmentDetail'; // Using the new component
import { FullEquipment } from '../../types'; // Adjusted path

const EquipmentDetailPage: React.FC = () => {
  const router = useRouter();
  const { id } = router.query;

  const [equipment, setEquipment] = useState<FullEquipment | null>(null);
  const [isLoading, setIsLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    if (id) {
      setIsLoading(true);
      setError(null);
      fetch(`/api/equipment/${id}`) // This API endpoint needs to exist
        .then(async (res) => {
          if (!res.ok) {
            const errorData = await res.json().catch(() => ({ message: 'Unknown error structure' }));
            throw new Error(errorData.message || `Error: ${res.status}`);
          }
          return res.json();
        })
        .then((data: FullEquipment) => {
          // Similar to UnitDetailPage, assuming API for /api/equipment/[id] returns FullEquipment
          if (Array.isArray(data) && data.length > 0) {
            setEquipment(data[0]);
          } else if (!Array.isArray(data)) {
            setEquipment(data);
          } else {
            throw new Error('Equipment not found or invalid API response');
          }
        })
        .catch((err) => {
          setError(err.message);
          setEquipment(null);
        })
        .finally(() => {
          setIsLoading(false);
        });
    }
  }, [id]);

  const equipmentName = equipment ? equipment.name : 'Equipment';

  return (
    <Layout title={`${isLoading ? 'Loading...' : equipmentName} | BattleTech Editor`}>
      <EquipmentDetail equipment={equipment} isLoading={isLoading} error={error} />
    </Layout>
  );
};

export default EquipmentDetailPage;
