import db from '../../../lib/db'; // Changed import

export default function handler(req, res) { // Removed async
  try {
    // Fetches distinct 'unit_type' values from the 'units' table.
    const stmt = db.prepare("SELECT DISTINCT unit_type FROM units WHERE unit_type IS NOT NULL AND unit_type <> '' ORDER BY unit_type ASC");
    const rows = stmt.all();

    // Transform the result into an array of strings
    const categories = rows.map(row => row.unit_type);

    res.status(200).json(categories);
  } catch (error) {
    console.error('Error fetching unit categories from database:', error);
    res.status(500).json({
      message: 'Error fetching unit categories',
      error: error.message,
      stack: process.env.NODE_ENV === 'development' ? error.stack : undefined,
    });
  }
  // No client.release() needed
}
