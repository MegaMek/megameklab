import React, { useState, useEffect } from 'react';
import { useRouter } from 'next/router';
import Layout from '../../components/common/Layout';
import UnitDetail from '../../components/units/UnitDetail';
import { FullUnit } from '../../types'; // Adjusted path

const UnitDetailPage: React.FC = () => {
  const router = useRouter();
  const { id } = router.query;

  const [unit, setUnit] = useState<FullUnit | null>(null);
  const [isLoading, setIsLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    if (id) {
      setIsLoading(true);
      setError(null);
      fetch(`/api/units/${id}`)
        .then(async (res) => {
          if (!res.ok) {
            const errorData = await res.json().catch(() => ({ message: 'Unknown error structure' }));
            throw new Error(errorData.message || `Error: ${res.status}`);
          }
          return res.json();
        })
        .then((data: FullUnit) => {
          // The API endpoint /api/units/[id] would need to be created.
          // It was not part of the previous subtask's API creation.
          // For now, this frontend code assumes it exists and returns a FullUnit.
          // If the API returns an array (e.g. if using a generic handler that always returns array for /api/units), take first element.
          if (Array.isArray(data) && data.length > 0) {
            setUnit(data[0]);
          } else if (!Array.isArray(data)) {
            setUnit(data);
          } else {
            throw new Error('Unit not found or invalid API response');
          }
        })
        .catch((err) => {
          setError(err.message);
          setUnit(null);
        })
        .finally(() => {
          setIsLoading(false);
        });
    }
  }, [id]);

  // Determine the display name for the title, try to get it from unit data once loaded
  const unitDisplayName = unit ? `${unit.data?.chassis || unit.chassis} ${unit.data?.model || unit.model}` : 'Unit';

  return (
    <Layout title={`${isLoading ? 'Loading...' : unitDisplayName} | BattleTech Editor`}>
      <UnitDetail unit={unit} isLoading={isLoading} error={error} />
    </Layout>
  );
};

export default UnitDetailPage;
