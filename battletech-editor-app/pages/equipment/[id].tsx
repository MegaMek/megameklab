import React, { useState, useEffect } from 'react';
import { useRouter } from 'next/router';
import Layout from '../../components/common/Layout';
import EquipmentDetail from '../../components/equipment/EquipmentDetail';
import { FullEquipment } from '../../types'; // Adjusted path

const EquipmentDetailPage: React.FC = () => {
  const router = useRouter();
  const { id } = router.query; // This will be a string or string[]

  const [equipment, setEquipment] = useState<FullEquipment | null>(null);
  const [isLoading, setIsLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const equipmentId = Array.isArray(id) ? id[0] : id;

    if (router.isReady && !equipmentId) {
        setIsLoading(false);
        setError("No Equipment ID provided in the URL.");
        return;
    }

    if (equipmentId) {
      setIsLoading(true);
      setError(null);
      setEquipment(null);

      fetch(`/api/equipment?id=${equipmentId}`)
        .then(async (res) => {
          if (!res.ok) {
            let errorMessage = `Error: ${res.status}`;
            try {
              const errorData = await res.json();
              errorMessage = errorData.message || errorMessage;
            } catch (e) { /* Ignore if response is not JSON */ }
            throw new Error(errorMessage);
          }
          return res.json();
        })
        .then((data: FullEquipment) => {
          if (data && typeof data === 'object' && !Array.isArray(data)) {
            setEquipment(data);
          } else {
            if(Array.isArray(data)) console.warn("API returned an array for single equipment ID fetch. Expected object.");
            throw new Error('Equipment not found or invalid API response format.');
          }
        })
        .catch((err) => {
          console.error("Failed to fetch equipment:", err);
          setError(err instanceof Error ? err.message : String(err));
          setEquipment(null);
        })
        .finally(() => {
          setIsLoading(false);
        });
    }
  }, [id, router.isReady]);

  let equipmentDisplayName = 'Equipment';
  if (isLoading) {
    equipmentDisplayName = 'Loading...';
  } else if (error) {
     equipmentDisplayName = 'Error';
  } else if (equipment) {
    equipmentDisplayName = equipment.name || `Equipment ${id}`;
  } else {
    equipmentDisplayName = 'Equipment Detail'; // Fallback if not loading, no error, no equipment
  }


  return (
    <Layout title={`${equipmentDisplayName} | BattleTech Editor`}>
      <EquipmentDetail equipment={equipment} isLoading={isLoading} error={error} />
    </Layout>
  );
};

export default EquipmentDetailPage;
