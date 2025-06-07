import React, { useState, useEffect } from 'react';
import { useRouter } from 'next/router';
import Layout from '../../components/common/Layout';
import UnitDetail from '../../components/units/UnitDetail';
import { FullUnit } from '../../types'; // Adjusted path

const UnitDetailPage: React.FC = () => {
  const router = useRouter();
  const { id } = router.query; // This will be a string or string[]

  const [unit, setUnit] = useState<FullUnit | null>(null);
  const [isLoading, setIsLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    // Ensure 'id' is a string and not an array or undefined
    const unitId = Array.isArray(id) ? id[0] : id;

    if (router.isReady && !unitId) {
        setIsLoading(false);
        setError("No Unit ID provided in the URL.");
        return; // Stop further execution if no ID and router is ready
    }

    if (unitId) {
      setIsLoading(true);
      setError(null);
      setUnit(null); // Clear previous unit data

      fetch(`/api/units?id=${unitId}`)
        .then(async (res) => {
          if (!res.ok) {
            let errorMessage = `Error: ${res.status}`;
            try {
              const errorData = await res.json();
              errorMessage = errorData.message || errorMessage;
            } catch (e) {
              // Ignore if response is not JSON
            }
            throw new Error(errorMessage);
          }
          return res.json();
        })
        .then((data: FullUnit) => {
          if (data && typeof data === 'object' && !Array.isArray(data)) {
            setUnit(data);
          } else {
            // This case handles if data is null, undefined, an array, or not an object.
            // For a single ID fetch, we expect a single object or a 404 error handled above.
             console.error("Invalid API response format for single unit fetch. Expected an object, got:", data);
            throw new Error('Unit not found or invalid API response format.');
          }
        })
        .catch((err) => {
          console.error("Failed to fetch unit:", err);
          setError(err instanceof Error ? err.message : String(err));
          setUnit(null);
        })
        .finally(() => {
          setIsLoading(false);
        });
    }
  }, [id, router.isReady]);

  let unitDisplayName = 'Unit';
  if (isLoading) {
    unitDisplayName = 'Loading...';
  } else if (error) {
    unitDisplayName = 'Error';
  } else if (unit) {
    const chassis = unit.data?.chassis || unit.chassis || '';
    const model = unit.data?.model || unit.model || '';
    unitDisplayName = `${chassis} ${model}`.trim();
    if (!unitDisplayName) unitDisplayName = `Unit ${id}`;
  } else {
    unitDisplayName = 'Unit Detail'; // Default if no unit, no error, not loading
  }


  return (
    <Layout title={`${unitDisplayName} | BattleTech Editor`}>
      <UnitDetail unit={unit} isLoading={isLoading} error={error} />
    </Layout>
  );
};

export default UnitDetailPage;
