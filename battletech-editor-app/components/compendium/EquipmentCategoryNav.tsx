// battletech-editor-app/components/compendium/EquipmentCategoryNav.tsx
import React, { useState, useEffect } from 'react';
import { getMetadata } from '../../services/mockApiService';

interface EquipmentCategoryNavProps {
  onSelectCategory: (category: string | null) => void;
  selectedCategory: string | null;
}

const EquipmentCategoryNav: React.FC<EquipmentCategoryNavProps> = ({ onSelectCategory, selectedCategory }) => {
  const [categories, setCategories] = useState<string[]>([]);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const fetchCategories = async () => {
      try {
        setLoading(true);
        const data = await getMetadata('/mockdata/mockEquipmentCategories.json');
        setCategories(data);
        setError(null); // Clear any previous error
      } catch (e) {
        if (e instanceof Error) {
          setError(e.message);
        } else {
          setError('An unknown error occurred');
        }
        console.error("Failed to fetch equipment categories:", e);
      } finally {
        setLoading(false);
      }
    };

    fetchCategories();
  }, []);

  if (loading) return <p>Loading equipment categories...</p>;
  if (error) return <p className="text-red-500">Error loading equipment categories: {error}</p>;
  if (!categories.length) return <p>No equipment categories found.</p>;

  // TODO: Consider a more sophisticated display if the number of categories is very large.
  // For now, a simple list.
  return (
    <nav className="bg-gray-100 p-3 rounded mb-4">
      <h3 className="font-semibold mb-2">Equipment Categories</h3>
      <ul className="space-y-1 max-h-96 overflow-y-auto">
         <li>
            <button
              onClick={() => onSelectCategory(null)} // Allow deselecting
              className={`text-blue-600 hover:text-blue-800 hover:underline w-full text-left ${!selectedCategory ? 'font-bold' : ''}`}
            >
              All Equipment Types
            </button>
          </li>
        {categories.map((category) => (
          <li key={category}>
            <button
              onClick={() => onSelectCategory(category)}
              className={`text-blue-600 hover:text-blue-800 hover:underline w-full text-left ${selectedCategory === category ? 'font-bold' : ''}`}
            >
              {category.charAt(0).toUpperCase() + category.slice(1)}
            </button>
          </li>
        ))}
      </ul>
    </nav>
  );
};
export default EquipmentCategoryNav;
