// battletech-editor-app/components/compendium/UnitCategoryNav.tsx
import React, { useState, useEffect } from 'react';
import { getMetadata } from '../../services/mockApiService';

interface UnitCategoryNavProps {
  onSelectCategory: (category: string | null) => void;
  selectedCategory: string | null;
}

const UnitCategoryNav: React.FC<UnitCategoryNavProps> = ({ onSelectCategory, selectedCategory }) => {
  const [categories, setCategories] = useState<string[]>([]);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const fetchCategories = async () => {
      try {
        setLoading(true);
        const data = await getMetadata('/mockdata/mockUnitCategories.json');
        setCategories(data);
        setError(null); // Clear any previous error
      } catch (e) {
        if (e instanceof Error) {
          setError(e.message);
        } else {
          setError('An unknown error occurred');
        }
        console.error("Failed to fetch unit categories:", e);
      } finally {
        setLoading(false);
      }
    };

    fetchCategories();
  }, []);

  if (loading) return <p>Loading unit categories...</p>;
  if (error) return <p className="text-red-500">Error loading unit categories: {error}</p>;
  if (!categories.length) return <p>No unit categories found.</p>;

  return (
    <nav className="bg-gray-100 p-3 rounded">
      <h3 className="font-semibold mb-2">Unit Categories</h3>
      <ul className="space-y-1">
         <li>
            <button
              onClick={() => onSelectCategory(null)} // Allow deselecting/showing all
              className={`text-blue-600 hover:text-blue-800 hover:underline w-full text-left ${!selectedCategory ? 'font-bold' : ''}`}
            >
              All Units
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

export default UnitCategoryNav;
