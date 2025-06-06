import React, { useEffect, useState } from 'react';

interface CategoryNavigationProps {
  selectedCategory: string | null;
  onSelectCategory: (category: string | null) => void;
}

const CategoryNavigation: React.FC<CategoryNavigationProps> = ({ selectedCategory, onSelectCategory }) => {
  const [categories, setCategories] = useState<string[]>([]);
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const fetchCategories = async () => {
      setIsLoading(true);
      setError(null);
      try {
        const response = await fetch('/api/meta/categories');
        if (!response.ok) {
          throw new Error(`Failed to fetch categories: ${response.statusText}`);
        }
        const data = await response.json();
        setCategories(data);
      } catch (err) {
        console.error(err);
        setError(err instanceof Error ? err.message : 'An unknown error occurred');
      } finally {
        setIsLoading(false);
      }
    };

    fetchCategories();
  }, []);

  if (isLoading) {
    return <div className="p-4">Loading categories...</div>;
  }

  if (error) {
    return <div className="p-4 text-red-500">Error: {error}</div>;
  }

  return (
    <nav className="p-4 bg-gray-100 w-full md:w-64 h-full">
      <h3 className="text-lg font-semibold mb-2">Unit Categories</h3>
      <ul>
        {/* Option to select all/clear filter */}
        <li key="all-categories" className="mb-1">
          <button
            onClick={() => onSelectCategory(null)}
            className={`w-full text-left px-2 py-1 rounded ${
              selectedCategory === null ? 'bg-blue-500 text-white' : 'hover:bg-gray-200'
            }`}
          >
            All Units
          </button>
        </li>
        {categories.map((category) => (
          <li key={category} className="mb-1">
            <button
              onClick={() => onSelectCategory(category)}
              className={`w-full text-left px-2 py-1 rounded ${
                selectedCategory === category ? 'bg-blue-500 text-white' : 'hover:bg-gray-200'
              }`}
            >
              {category}
            </button>
          </li>
        ))}
      </ul>
    </nav>
  );
};

export default CategoryNavigation;
